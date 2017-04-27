package com.cobus.user.model;
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
    private Integer idRole;

	@Column(name="TYPE", length=15, unique=true, nullable=false)
    private String type;

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idRole == null) ? 0 : idRole.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof UserProfile))
            return false;
        UserProfile other = (UserProfile) obj;
        if (idRole == null) {
            if (other.idRole != null)
                return false;
        } else if (!idRole.equals(other.idRole))
            return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserProfile [id=" + idRole + ", type=" + type + "]";
    }

}

