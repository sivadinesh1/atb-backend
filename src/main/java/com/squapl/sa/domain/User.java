package com.squapl.sa.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.squapl.sa.domain.security.Authority;
import com.squapl.sa.domain.security.UserRole;

@Entity
@Table(name="user")
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId", nullable = false, updatable = false)
    private Long userId;
    
    //@Size(min=4, message = "Username has to be minimum 4 characters")  
    private String username;
    
//    @Size(min=7, message = "Password has to be minimum 7 characters")  
    private String password;
    private String firstName;
    private String lastName;
    



	

//    @Column(name = "email",  unique = true)
//    @Email(message = "Email Address is not a valid format")
    @Column(name = "email")
    private String email;
    
    //@Pattern(regexp = "(?:\\s+|)((0|(?:(\\+|)91))(?:\\s|-)*(?:(?:\\d(?:\\s|-)*\\d{9})|(?:\\d{2}(?:\\s|-)*\\d{8})|(?:\\d{3}(?:\\s|-)*\\d{7}))|\\d{10})(?:\\s+|)", message = "Phone number must be in form 91-XXXXX-XXXXX")
    private String phone;

    private boolean enabled=true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    
    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSocialid() {
		return socialid;
	}

	public void setSocialid(String socialid) {
		this.socialid = socialid;
	}

	public Date getMembersince() {
		return membersince;
	}

	public void setMembersince(Date membersince) {
		this.membersince = membersince;
	}

	public String getProfileimgurl() {
		return profileimgurl;
	}

	public void setProfileimgurl(String profileimgurl) {
		this.profileimgurl = profileimgurl;
	}




	private String source;
    private String socialid;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date membersince;
    private String profileimgurl;
    
    
    


	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
        userRoles.forEach(ur -> authorities.add(new Authority(ur.getRole().getName())));
        return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

    

 


}
