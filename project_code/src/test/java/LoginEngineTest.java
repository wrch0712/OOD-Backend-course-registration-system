import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LoginEngineTest {

    @Test
    void testLogin() throws Exception {
        // Setup
        // Run the test
        final User result = LoginEngine.login(0, "inputPassword");

        // Verify the results
    }

    @Test
    void testLogin_ThrowsSQLException() {
        // Setup
        // Run the test
        assertThrows(SQLException.class, () -> LoginEngine.login(0, "inputPassword"));
    }
}
