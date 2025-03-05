package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserRegistrationTest {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void testUniqueEmailRegistration() {
        final User existingUser = new User(
            "John Doe", 
            "john@example.com", 
            "123 Main St", 
            "555-1234"
        );

        when(userRepository.findByEmail("john@example.com"))
            .thenReturn(existingUser);

        final UniqueEmailException exception = assertThrows(
            UniqueEmailException.class, 
            () -> {
                User newUser = new User(
                    "Jane Doe", 
                    "john@example.com",
                    "456 Oak Rd", 
                    "555-5678"
                );
                userService.registerUser(newUser);
            }
        );

        assertEquals(
            "Email already exists", 
            exception.getMessage()
        );
    }

    @Test
    public void testAdminUserDeletion() {

        final User adminUser = new User(
            "Admin User", 
            "admin@example.com"
        );
        adminUser.setRole(UserRole.ADMIN);
        adminUser.setId(1);

        final User targetUser = new User(
            "Target User", 
            "target@example.com"
        );

        targetUser.setId(2);

        when(userRepository.findById(targetUser.getId()))
            .thenReturn(targetUser);
        when(userRepository.findById(adminUser.getId()))
            .thenReturn(adminUser);

        when(userRepository.delete(targetUser.getId()))
                .thenReturn(true);

        final boolean deletionResult = userService.delete(
            adminUser.getId(),
            targetUser.getId()
        );

        assertTrue(deletionResult);
        verify(userRepository).delete(targetUser.getId());
    }

    @Test
    public void testNonAdminDeletionPrevention() {
        final User nonAdminUser = new User(
            "Non-Admin User", 
            "nonadmin@example.com"
        );

        nonAdminUser.setRole(UserRole.REGULAR);
        nonAdminUser.setId(1);

        final User targetUser = new User(
            "Target User", 
            "target@example.com"
        );

        targetUser.setId(2);

        when(userRepository.findById(targetUser.getId()))
            .thenReturn(targetUser);
        when(userRepository.findById(nonAdminUser.getId()))
            .thenReturn(nonAdminUser);

        final PermissionDeniedException exception = assertThrows(
            PermissionDeniedException.class,
            () -> userService.delete(
                nonAdminUser.getId(),
                targetUser.getId()
            )
        );

        assertEquals(
            "User does not have permission to delete", 
            exception.getMessage()
        );
    }
}