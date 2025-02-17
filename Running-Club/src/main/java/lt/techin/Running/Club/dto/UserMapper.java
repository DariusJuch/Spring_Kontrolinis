package lt.techin.Running.Club.dto;

import lt.techin.Running.Club.model.User;

import java.util.List;

public class UserMapper {

  public static List<UserRequestDTO> toUserRequestDTOList(List<User> users) {
    List<UserRequestDTO> result = users.stream()
            .map(user -> new UserRequestDTO(user.getUsername(), user.getPassword(), user.getRoles()))
            .toList();

    return result;
  }

  public static List<UserResponseDTO> toUserResponseDTOList(List<User> users) {
    List<UserResponseDTO> result = users.stream()
            .map(user -> new UserResponseDTO(user.getId(), user.getUsername(), user.getRoles()))
            .toList();

    return result;
  }

  public static UserRequestDTO toUserRequestDTO(User user) {
    return new UserRequestDTO(user.getUsername(), user.getPassword(), user.getRoles());
  }

  public static UserResponseDTO toUserResponseDTO(User user) {
    return new UserResponseDTO(user.getId(), user.getUsername(), user.getRoles());
  }

  public static User toUser(UserRequestDTO userRequestDTO) {
    User user = new User();
    user.setUsername(userRequestDTO.username());
    user.setPassword(userRequestDTO.password());
    user.setRoles(userRequestDTO.roles());

    return user;
  }

}
