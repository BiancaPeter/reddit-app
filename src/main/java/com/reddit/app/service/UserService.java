package com.reddit.app.service;

import com.reddit.app.DTO.RegisterDTO;
import com.reddit.app.model.Role;
import com.reddit.app.model.RoleType;
import com.reddit.app.model.User;
import com.reddit.app.repository.RoleRepository;
import com.reddit.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterDTO newUser) {
        Optional<User> foundUserOptional = userRepository.findUserByUsername(newUser.getUsername());
        if (foundUserOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CREATED, "already exist");
        }
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        //daca exista rolul in baza de date atunci doar il luam
        //daca nu exista si este ADMIN sau USER, atunci il adaugam
        Role foundRole = roleRepository.findByRoleType(newUser.getRoleType());
        if (foundRole != null) {
            setRoleOfUser(user, foundRole);
        } else if (newUser.getRoleType().equals(RoleType.ROLE_ADMIN_SUBREDDIT) || newUser.getRoleType().equals(RoleType.ROLE_USER)) {
            Role newRole = roleService.addRole(newUser.getRoleType());
            setRoleOfUser(user, newRole);
        }
        return userRepository.save(user);
    }

    private static void setRoleOfUser(User user, Role role) {
        user.getRoleList().add(role);
        role.getUserList().add(user);
    }

    public User findLoggedInUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User foundUser = userRepository.findUserByUsername(userDetails.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        return foundUser;
    }

    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the user was not found"));
    }

}
