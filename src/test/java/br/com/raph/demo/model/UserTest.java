package br.com.raph.demo.model;

import br.com.raph.demo.DemoApplication;
import br.com.raph.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
class UserTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave() {
        // Given
        User user = new User();
        user.setRole(Role.ROLE_USER);
        user.setName("test");

        // When
        User save = userRepository.save(user);

        // Then
        assertNotNull(user.getId());
        assertNotNull(user.getCreated_at());
    }
}