/**
 * 
 */
package com.maroy.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author MaRoy
 *
 */
@Entity
@Table(name="USER_DATA")
public class UserEntity implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="USER_ID")
	@GeneratedValue
	private Integer id;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="USER_ROLE")
	private String role;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String[] roles = this.role.split(",");
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(String role : roles){
			authorities.add(new GrantedAuthorityImpl(role));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
