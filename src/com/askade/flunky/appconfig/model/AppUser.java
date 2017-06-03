package com.askade.flunky.appconfig.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by AdrianIonita on 4/29/2017.
 */
@Entity
@Table (name = "xxknl_app_users")
public class AppUser {

    private String username;
    private String password;
    private boolean enabled;
    private Set<AppUserRole> appUserRoles = new HashSet<AppUserRole>(0);

    @Column(name = "username", unique = true, nullable = false)
    @Id
    public String getUsername() {
        return username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    @Column(name = "enabled", columnDefinition = "TINYINT", length = 1)
    public boolean isEnabled() {
        return enabled;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appUser", targetEntity = AppUserRole.class)
    public Set<AppUserRole> getAppUserRoles() {
        return appUserRoles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAppUserRoles(Set<AppUserRole> appUserRoles) {
        this.appUserRoles = appUserRoles;
    }
}
