package testing;

public class Assertions {

    private Assertions() {
        throw new UnsupportedOperationException(
                "Suppress default constructor for noninstantiability");
    }

    public static void assertThatThrows(Runnable command,
                                        Class<? extends RuntimeException> exception) {
        assertThatThrows(command, exception, null);
    }

    public static void assertThatThrows(Runnable command,
                                        Class<? extends RuntimeException> exception,
                                        String errorMessage) {
        boolean wasThrown = false;
        try {
            command.run();
        }
        catch (Exception ex) {
            wasThrown = true;
            assertThat(ex.getClass() == exception);
            if (errorMessage == null)
                assertThat(ex.getMessage() == null);
            else
                assertThat(ex.getMessage().equals(errorMessage));
        }
        finally {
            assertThat(wasThrown);
        }
    }

    public static void assertThat(boolean assertion) {
        if (!assertion) throw new RuntimeException("Assertion failed");
    }
}
