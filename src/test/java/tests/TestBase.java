package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestBase {
    protected void assertNotNullOrEmpty(String string) {
        assertNotNull(string);
        assertFalse(string.trim().isEmpty());
    }
}
