// Noncompliant Example
public class Main {
    if (loginSuccessful) {
        logger.severe("User login succeeded for: " + username); // Logs sensitive info
    } else {
        logger.severe("User login failed for: " + username);
    }
}



