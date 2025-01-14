package com.alphadev.AlphaHotel.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails{
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message="Email is required")
    @Column(unique=true)
    private String username;
    
    @NotBlank(message="name is required")
    private String name;
    
    @NotBlank(message="phoneNumber is required")
    private String phoneNumber;
    
    @NotBlank(message="password is required")
    private String password;
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Booking> booking =new ArrayList<>();
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
        )
    private Set<Role> roles = new HashSet<>();
    
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert roles to GrantedAuthority objects for Spring Security
		return this.roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

}
