package com.amsystem.bifaces.user.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Title: User.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */

@Entity
@Table(name="USER")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUSER")
    private Integer userId;

    @NotEmpty
    @Column(name="USERNAME", unique=true, nullable=false)
    private String userName;

    @NotEmpty
    @Column(name="PASSWORD", nullable=false)
    private String password;

    @NotEmpty
    @Column(name="FIRSTNAME", nullable=false)
    private String firstName;

    @NotEmpty
    @Column(name="LASTNAME", nullable=false)
    private String lastName;

    @NotEmpty
    @Column(name="EMAIL", nullable=false)
    private String email;

    @Column(name = "INITIALDATE", nullable = false)
    private Date expeditionDate;

    @Column(name = "FINALDATE", nullable = true)
    private Date expirationDate;

    @NotNull
    @Column(name="EXPIRABLE", nullable=false)
    private int expirable;

    @Column(name="STATUS", nullable=false)
    private int status;

    @NotEmpty
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "IDUSER")},
            inverseJoinColumns = {@JoinColumn(name = "IDROLE")})
    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getExpeditionDate() {
        return expeditionDate;
    }

    public void setExpeditionDate(Date expeditionDate) {
        this.expeditionDate = expeditionDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getExpirable() {
        return expirable;
    }

    public void setExpirable(int expirable) {
        this.expirable = expirable;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }

    /*
     * DO-NOT-INCLUDE passwords in toString function.
     * It is done here just for convenience purpose.
     */
    @Override
    public String toString() {
        return "User [id=" + userId + ", userName=" + userName + ", password=" + password
                + ", firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + email + "]";
    }



}
