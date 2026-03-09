import java.util.List;

public class Test {
    List<Job> getJobsCase1() {
        return List.of(
                new Job(0, new Process("p1", 8)),
                new Job(0, new Process("p2", 4)),
                new Job(0, new Process("p3", 2)),
                new Job(0, new Process("p4", 6)),
                new Job(0, new Process("p5", 3)));
    }

    List<Job> getJobsCase2() {
        return List.of(
                new Job(0, new Process("p1", 20)),
                new Job(1, new Process("p2", 2)),
                new Job(2, new Process("p3", 2)),
                new Job(3, new Process("p4", 1)),
                new Job(4, new Process("p5", 3)));
    }

    List<Job> getJobsCase3() {
        return List.of(
                new Job(0, new Process("p1", 20)),
                new Job(1, new Process("p2", 2)),
                new Job(2, new Process("p3", 2)),
                new Job(3, new Process("p4", 2)),
                new Job(4, new Process("p5", 2)),
                new Job(5, new Process("p6", 2)));
    }

    private StringBuilder sb1 = new StringBuilder();
    private StringBuilder sb2 = new StringBuilder();
    private StringBuilder sb3 = new StringBuilder();
    private static final String blue = "\u001B[34m";
    private static final String reset = "\u001B[0m";
    private Simulation sim;

    void testFirstComeFirstServeCase1(boolean compTable) {
        var s = new Simulation(getJobsCase1(), new FirstComeFirstServe());
        this.sim = s;
        if (compTable) {
            s.runTableComps();
        } else {
            IO.println(blue + "Running test First Come First Serve case 1: " + reset);
            s.run();
            IO.println("=".repeat(50));
        }
    }

    void testFirstComeFirstServeCase2(boolean compTable) {
        var s = new Simulation(getJobsCase2(), new FirstComeFirstServe());
        this.sim = s;
        if (compTable) {
            s.runTableComps();
        } else {
            IO.println(blue + "Running test First Come First Serve case 2: " + reset);
            s.run();
            IO.println("=".repeat(50));
        }
    }

    void testFirstComeFirstServeCase3(boolean compTable) {
        var s = new Simulation(getJobsCase3(), new FirstComeFirstServe());
        this.sim = s;
        if (compTable) {
            s.runTableComps();
        } else {
            IO.println(blue + "Running test First Come First Serve case 3: " + reset);
            s.run();
            IO.println("=".repeat(50));
        }
    }

    private void testShortestJobFirstCase1(boolean compTable) {
        var s = new Simulation(getJobsCase1(), new ShortestJobFirst());
        this.sim = s;
        if (compTable) {
            s.runTableComps();
        } else {
            IO.println(blue + "Running test Shortest Job First case 1: " + reset);
            s.run();
            IO.println("=".repeat(50));
        }
    }

    private void testShortestJobFirstCase2(boolean compTable) {
        var s = new Simulation(getJobsCase2(), new ShortestJobFirst());
        this.sim = s;
        if (compTable) {
            s.runTableComps();
        } else {
            IO.println(blue + "Running test Shortest Job First case 2: " + reset);
            s.run();
            IO.println("=".repeat(50));
        }
    }

    private void testShortestJobFirstCase3(boolean compTable) {
        var s = new Simulation(getJobsCase3(), new ShortestJobFirst());
        this.sim = s;
        if (compTable) {
            s.runTableComps();
        } else {
            IO.println(blue + "Running test Shortest Job First case 3: " + reset);
            s.run();
            IO.println("=".repeat(50));
        }
    }

    private void testShortestRemainingTimeFirstCase1(boolean compTable) {
        var s = new Simulation(getJobsCase1(), new ShortestRemainingTimeFirst());
        this.sim = s;
        if (compTable) {
            s.runTableComps();
        } else {
            IO.println(blue + "Running test Shortest Remaining Time First case 1: " + reset);
            s.run();
            IO.println("=".repeat(50));
        }
    }

    private void testShortestRemainingTimeFirstCase2(boolean compTable) {
        var s = new Simulation(getJobsCase2(), new ShortestRemainingTimeFirst());
        this.sim = s;
        if (compTable) {
            s.runTableComps();
        } else {
            IO.println(blue + "Running test Shortest Remaining Time First case 2: " + reset);
            s.run();
            IO.println("=".repeat(50));
        }
    }

    private void testShortestRemainingTimeFirstCase3(boolean compTable) {
        var s = new Simulation(getJobsCase3(), new ShortestRemainingTimeFirst());
        this.sim = s;
        if (compTable) {
            s.runTableComps();
        } else {
            IO.println(blue + "Running test Shortest Remaining Time First case 3: " + reset);
            s.run();
            IO.println("=".repeat(50));
        }
    }

    void main() {
        testFirstComeFirstServeCase1(false);
        testFirstComeFirstServeCase2(false);
        testFirstComeFirstServeCase3(false);
        testShortestJobFirstCase1(false);
        testShortestJobFirstCase2(false);
        testShortestJobFirstCase3(false);
        testShortestRemainingTimeFirstCase1(false);
        testShortestRemainingTimeFirstCase2(false);
        testShortestRemainingTimeFirstCase3(false);
    }

    void moveCursor(int row, int column) {
        String mc = "\u001B[" + row + ";" + column + "H";
        IO.print(mc);
    }

    void moveCursorToCol(int column) {
        String mc = "\u001B[" + column + "G";
        IO.print(mc);
    }

    void main2() {
        printWaitingTimeCompTable();
        printAverageTurnaroundTime();
    }

    void printWaitingTimeCompTable() {
        sb1.append(blue + "============" + "Average waiting Time" + "============" + reset + "\n");
        sb1.append("Cases | FCFS | SJF  | SRTF | \n");
        sb1.append("1     | ");
        IO.print(sb1);
        testFirstComeFirstServeCase1(true);
        IO.print(sim.getAverageWT());
        testShortestJobFirstCase1(true);
        moveCursorToCol(14);
        IO.print("| " + sim.getAverageWT());
        testShortestRemainingTimeFirstCase1(true);
        moveCursorToCol(21);
        IO.print("| " + sim.getAverageWT() + "  |");
        IO.println();
        IO.print("2     | ");
        testFirstComeFirstServeCase2(true);
        IO.print(sim.getAverageWT());
        testShortestJobFirstCase2(true);
        moveCursorToCol(14);
        IO.print("| " + sim.getAverageWT());
        testShortestRemainingTimeFirstCase2(true);
        moveCursorToCol(21);
        IO.print("| " + sim.getAverageWT() + "  |");
        IO.println();
        IO.print("3     | ");
        testFirstComeFirstServeCase3(true);
        IO.print(sim.getAverageWT());
        testShortestJobFirstCase3(true);
        moveCursorToCol(14);
        IO.print("| " + sim.getAverageWT());
        testShortestRemainingTimeFirstCase3(true);
        moveCursorToCol(21);
        IO.print("| " + sim.getAverageWT() + "  |");
        IO.println();
    }

    void printAverageTurnaroundTime() {
        IO.print(sb1);
        testFirstComeFirstServeCase1(true);
        IO.print(sim.getAverageTT());
        testShortestJobFirstCase1(true);
        moveCursorToCol(14);
        IO.print("| " + sim.getAverageTT());
        testShortestRemainingTimeFirstCase1(true);
        moveCursorToCol(21);
        IO.print("| " + sim.getAverageTT() + " |");
        IO.println();
        IO.print("2     | ");
        testFirstComeFirstServeCase2(true);
        IO.print(sim.getAverageTT());
        testShortestJobFirstCase2(true);
        moveCursorToCol(14);
        IO.print("| " + sim.getAverageTT());
        testShortestRemainingTimeFirstCase2(true);
        moveCursorToCol(21);
        IO.print("| " + sim.getAverageTT() + "  |");
        IO.println();
        IO.print("3     | ");
        testFirstComeFirstServeCase3(true);
        IO.print(sim.getAverageTT());
        testShortestJobFirstCase3(true);
        moveCursorToCol(14);
        IO.print("| " + sim.getAverageTT());
        testShortestRemainingTimeFirstCase3(true);
        moveCursorToCol(21);
        IO.print("| " + sim.getAverageTT() + "  |");
        IO.println();
    }
}
