package api.todo_list_api.models;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;


@Entity
@Table(name = "users")
public class UserModel implements UserDetails, Serializable {


    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false, unique = true)
    @Email
    private String email; 

    @Column(name = "full_name")
    private String fullName; 

    @Column
    private String password; 
    
    @Column(name = "account_non_expired")
    private String accountNonExpired; 

    @Column(name = "account_non_locked")
    private String accountNonLocked; 

    @Column(name = "credentials_non_expired")
    private String credentialsNonExpired;

    @Column
    private Boolean enabled; 

    public UserModel() {
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; 
    }

    @Override
    public String getPassword() {
        return this.password; 
    }

    @Override
    public String getUsername() {
        return this.email; 
    }


    
}