package week1.union_find;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;

public class SocialNetworkConnectivity {

    public static String earliestTimeOfFullConnectivity(int membersOnNetwork,
                                                        String csvLogsFileName) {
        if (membersOnNetwork < 2) throw new IllegalArgumentException("network cannot have less than 2 members");
        if (csvLogsFileName.isBlank()) throw new IllegalArgumentException("logs file name cannot be blank");

        In in = new In(csvLogsFileName);
        UF graph = new UF(membersOnNetwork);
        String earliestTimeOfFullConnectivity = "Full connectivity not achieved";

        while (in.hasNextLine()) {
            String[] nextConnection = in.readLine().split(",");
            if (nextConnection.length == 1)
                continue; // this is handled as empty line but in actual cases would be better to throw exception or log and continue
            graph.union(Integer.parseInt(nextConnection[1]), Integer.parseInt(nextConnection[2]));
            if (graph.count() == 1) {
                earliestTimeOfFullConnectivity = nextConnection[0];
                break;
            }
        }

        return earliestTimeOfFullConnectivity;
    }

    public static void main(String[] args) {
        // StdOut.println(System.getProperty("user.dir"));
        StdOut.println("Start running unit tests for class");
        assertThatThrows(() -> earliestTimeOfFullConnectivity(2,
                                                              "  "),
                         IllegalArgumentException.class, "logs file name cannot be blank");
        assertThatThrows(() -> earliestTimeOfFullConnectivity(1,
                                                              "week1/social-network-test1.txt"),
                         IllegalArgumentException.class, "network cannot have less than 2 members");

        String dateOfFullNetworkConnectivity = earliestTimeOfFullConnectivity(2,
                                                                              "week1/social-network-empty-lines.txt");
        assertThat("Full connectivity not achieved".equals(dateOfFullNetworkConnectivity));

        String dateOfFullNetworkConnectivity0 = earliestTimeOfFullConnectivity(3,
                                                                               "week1/social-network-not-enough-connections.txt");
        assertThat("Full connectivity not achieved".equals(dateOfFullNetworkConnectivity0));

        String dateOfFullNetworkConnectivity1 = earliestTimeOfFullConnectivity(8,
                                                                               "week1/social-network-two-big-groups-not-fully-connected.txt");
        assertThat("Full connectivity not achieved".equals(dateOfFullNetworkConnectivity1));

        String dateOfFullNetworkConnectivity2 = earliestTimeOfFullConnectivity(5,
                                                                               "week1/social-network-test1.txt");
        assertThat("2020-12-23".equals(dateOfFullNetworkConnectivity2));


        String dateOfFullNetworkConnectivity3 = earliestTimeOfFullConnectivity(8,
                                                                               "week1/social-network-test3.txt");
        assertThat("2020-12-26".equals(dateOfFullNetworkConnectivity3));
        StdOut.println("End of unit tests with no failures");
    }

    private static void assertThatThrows(Runnable command,
                                         Class<? extends RuntimeException> exception,
                                         String errorMessage) {
        try {
            command.run();
        }
        catch (Exception ex) {
            assertThat(ex.getClass() == exception);
            assertThat(ex.getMessage().equals(errorMessage));
        }
    }

    private static void assertThat(boolean assertion) {
        if (!assertion) throw new RuntimeException("Assertion failed");
    }
}
