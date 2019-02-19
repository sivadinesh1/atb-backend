package com.squapl.sa.domain.security;

import com.squapl.sa.domain.User;

import javax.persistence.*;


@Entity
@Table(name="user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_role_id;

    public long getUser_role_id() {
		return user_role_id;
	}

	public void setUser_role_id(long user_role_id) {
		this.user_role_id = user_role_id;
	}

	public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public UserRole() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
