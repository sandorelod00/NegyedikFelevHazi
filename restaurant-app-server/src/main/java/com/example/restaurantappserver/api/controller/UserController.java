package com.example.restaurantappserver.api.controller;

import com.example.restaurantappserver.JwtUtils;
import com.example.restaurantappserver.api.dto.*;
import com.example.restaurantappserver.api.service.SecurityService;
import com.example.restaurantappserver.api.service.UserDetailsImpl;
import com.example.restaurantappserver.api.service.UserService;
import com.example.restaurantappserver.entity.Role;
import com.example.restaurantappserver.entity.User;
import com.example.restaurantappserver.enums.ERole;
import com.example.restaurantappserver.repo.RoleRepository;
import com.example.restaurantappserver.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/user")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {


    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    private final UserService userService;

    @PostMapping("/registration")
    public String registration(@RequestBody UserDto userDto){
        userService.save(userDto);
        return "Success";
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        ResponseEntity<JwtResponse> ok = ResponseEntity.ok(new JwtResponse(jwt,"Bearer",
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
        return ok;
    }

    @PostMapping("/signup")
    public boolean registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUserName(signUpRequest.getUserName())) {
            return false;
        }


        // Create new user's account
        User user = new User();
        user.setUserName(signUpRequest.getUserName());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        List<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER);
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR);
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER);
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return true;
    }

    /**@RequestMapping("/login")
    public TokenDto login(@RequestBody User user, HttpServletRequest request) {
        TokenDto token = new TokenDto();

        if(user.getUserName().equals("frog") && user.getPassword().equals("password1")){
          String stringToken = Base64.getEncoder().encode((user.getUserName() + ":" + user.getPassword()).getBytes()).toString();
          token.setType("Basic");
          token.setToken(stringToken);
          return token;
        }
        token.setType("none");
        token.setToken("none");
        return token;
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");

        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }*/
}
