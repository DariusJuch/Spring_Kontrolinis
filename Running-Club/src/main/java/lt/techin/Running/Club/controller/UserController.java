package lt.techin.Running.Club.controller;


import jakarta.validation.Valid;
import lt.techin.Running.Club.dto.UserMapper;
import lt.techin.Running.Club.dto.UserRequestDTO;
import lt.techin.Running.Club.dto.UserResponseDTO;
import lt.techin.Running.Club.model.User;
import lt.techin.Running.Club.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserController(UserService userService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("/users")
  public ResponseEntity<List<UserResponseDTO>> getUser() {
    return ResponseEntity.ok(UserMapper.toUserResponseDTOList(userService.findAllUsers()));

  }

  @GetMapping("/users/{id}")
  public ResponseEntity<?> getUser(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails, Authentication authentication) {
    Optional<User> foundUser = userService.findById(id);
    boolean isAdmin = ((authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(a -> a.equalsIgnoreCase("ROLE_ADMIN"))));

    if (!isAdmin) {
      if (((User) authentication.getPrincipal()).getId() != id) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Can't see other user");
      }
    }


    if (foundUser.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(UserMapper.toUserResponseDTO(foundUser.get()));
  }

  @PostMapping("/users")
  public ResponseEntity<UserResponseDTO> addUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
    User user = UserMapper.toUser(userRequestDTO);
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    User savedUser = userService.saveUser(user);

    return ResponseEntity.created(
                    ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(savedUser.getId())
                            .toUri())
            .body(UserMapper.toUserResponseDTO(savedUser));
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable long id, Authentication authentication) {

    boolean isAdmin = ((authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(a -> a.equalsIgnoreCase("ROLE_ADMIN"))));

    if (!isAdmin) {
      if (((User) authentication.getPrincipal()).getId() != id) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Can't see other user");
      }
    }

    if (!userService.existUserById(id)) {
      return ResponseEntity.notFound().build();
    }
    userService.deleteUserById(id);
    return ResponseEntity.noContent().build();
  }
}
