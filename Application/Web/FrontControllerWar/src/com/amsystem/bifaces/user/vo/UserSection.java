package com.amsystem.bifaces.user.vo;

import com.amsystem.bifaces.user.UserOperation;
import com.amsystem.bifaces.user.bean.UserBean;
import com.amsystem.bifaces.user.model.User;
import com.amsystem.bifaces.user.model.UserProfile;
import com.amsystem.bifaces.util.ComponentOperation;
import com.amsystem.bifaces.util.OperationType;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Title: UserSection.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 31/12/2016.
 */

@ViewScoped
@ManagedBean(name = "userSectionVO")
public class UserSection implements Serializable {

    private List<User> allUsers;

    private User selectedUser;

    private List<User> filteredUsers;

    private List<String> selectedProfile;

    private OperationType operation;

    private boolean expirable;

    private static List<String> roleList;

    private static Map<String, UserProfile> roles;

    @ManagedProperty("#{userBean}")
    private UserBean userBean;

    @ManagedProperty("#{userOperation}")
    private UserOperation userOperation;

    @PostConstruct
    /**
     * Carga todos los roles y usuarios registrados en el sistema
     */
    public void init() {
        allUsers = userOperation.loadAllUsers();
        List<UserProfile> userProfiles = userOperation.allProfile();
        roles = new LinkedHashMap<>();
        roleList = new ArrayList<>();
        for (UserProfile up : userProfiles) {
            roles.put(up.getType(), up);
            roleList.add(up.getType());
        }

    }


    public void deleteUser() {
        userOperation.deleteUser(selectedUser);
        allUsers = userOperation.loadAllUsers();
    }

    public void saveUser() {
        /*
        if (operation == OperationType.CREATE) {
            selectedUser = new User();
        }
        */
        selectedUser.setFirstName(userBean.getFirstName());
        selectedUser.setLastName(userBean.getLastName());
        selectedUser.setEmail(userBean.getEmail());
        selectedUser.setUserName(userBean.getUserName());
        selectedUser.setPassword(userBean.getPassword());
        selectedUser.setExpirable(expirable == Boolean.TRUE ? 1 : 0);
        selectedUser.setExpeditionDate(new Date());
        selectedUser.setExpirationDate(userBean.getExpirationDate());
        HashSet<UserProfile> rol = new HashSet<UserProfile>();
        for (String userProfile : selectedProfile) {
            rol.add(roles.get(userProfile));
        }
        selectedUser.setUserProfiles(rol);

        if (operation == OperationType.CREATE) {
            userOperation.saveUser(selectedUser);
            allUsers = userOperation.loadAllUsers();
        } else {
            userOperation.updateUser(selectedUser);
        }

    }

    public void editUser() {
        operation = OperationType.EDIT;
        userBean.setFirstName(selectedUser.getFirstName());
        userBean.setLastName(selectedUser.getLastName());
        userBean.setEmail(selectedUser.getEmail());
        userBean.setUserName(selectedUser.getUserName());
        userBean.setPassword(selectedUser.getPassword());
        expirable = selectedUser.getExpirable() > 0 ? Boolean.TRUE : Boolean.FALSE;

        userBean.setExpeditionDate(selectedUser.getExpeditionDate());
        userBean.setExpirationDate(selectedUser.getExpirationDate());

        Iterator<UserProfile> iterator = selectedUser.getUserProfiles().iterator();
        selectedProfile = new ArrayList<>();
        while (iterator.hasNext()) {
            int i = 0;
            UserProfile nextProfile = iterator.next();
            selectedProfile.add(nextProfile.getType());
            i++;
        }

        if (userBean != null) {
            ComponentOperation.updateComponent("formUser:tabUserSetting");
            ComponentOperation.executeComponent("PF('widUserTool').show();");
        } else {

            //MessageUtil.showMessage("Error", ErrorType.ERROR);
        }
    }

    public void newUser() {
        operation = OperationType.CREATE;
        userBean = new UserBean();
        ComponentOperation.updateComponent("formUser:tabUserSetting");
        ComponentOperation.executeComponent("PF('widUserTool').show();");


    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }


    public List<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }

    public List<User> getFilteredUsers() {
        return filteredUsers;
    }

    public void setFilteredUsers(List<User> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public UserOperation getUserOperation() {
        return userOperation;
    }

    public void setUserOperation(UserOperation userOperation) {
        this.userOperation = userOperation;
    }

    public Map<String, UserProfile> getRoles() {
        return roles;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public boolean isExpirable() {
        return expirable;
    }

    public void setExpirable(boolean expirable) {
        this.expirable = expirable;
    }

    public List<String> getSelectedProfile() {
        return selectedProfile;
    }

    public void setSelectedProfile(List<String> selectedProfile) {
        this.selectedProfile = selectedProfile;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }

    public List<String> getRoleList() {
        return roleList;
    }


}
