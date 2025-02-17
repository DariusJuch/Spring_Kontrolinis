package lt.techin.Running.Club.security;

import lt.techin.Running.Club.model.User;
import lt.techin.Running.Club.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserService userService;

  @Autowired
  public UserDetailsServiceImpl(UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> foundUser = userService.findUserByUsername(username);

    if (foundUser.isEmpty()) {
      throw new UsernameNotFoundException(username);
    }
    return foundUser.get();
  }
}
