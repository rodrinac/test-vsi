package org.example;

import java.util.Objects;

public class UserService {
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(final User newUser) {
        if (userRepository.findByEmail(newUser.getEmail()) != null) {
            throw new UniqueEmailException();
        }
    }

    public boolean delete(final Integer requesterId, final Integer targetId) {
        final User requester = userRepository.findById(requesterId);
        final User target = userRepository.findById(targetId);

        if (Objects.equals(UserRole.ADMIN, requester.getRole())) {
            return userRepository.delete(target.getId());
        }

       throw new PermissionDeniedException("User does not have permission to delete");
    }
}
