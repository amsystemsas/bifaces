package com.amsystem.bifaces.user.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Title: UserProfile.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */

@Entity
@Table(name="ROLE")
public class UserProfile implements Serializable{

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDROLE")
    private Integer roleId;

	@Column(name="TYPE", length=20, unique=true, nullable=false)
    private String type;

    @Column(name="DESCRIPTION", length=45, nullable=true)
    private String description;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfile that = (UserProfile) o;

        if (!roleId.equals(that.roleId)) return false;
        if (!type.equals(that.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "roleId=" + roleId +
                ", type='" + type + '\'' +
                '}';
    }
}

