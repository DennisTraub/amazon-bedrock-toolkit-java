package tests.common;

import org.junit.jupiter.api.Assertions;

public class TestBase {
    protected void assertNotNullOrEmpty(String string) {
        Assertions.assertNotNull(string);
        Assertions.assertFalse(string.trim().isEmpty());
    }
}
