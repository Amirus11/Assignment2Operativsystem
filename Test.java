import java.util.List;
import java.util.function.Function;

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

    private static final String blue = "\u001B[34m";
    private static final String reset = "\u001B[0m";

    private Simulation[] simFCFS = {
            new Simulation(getJobsCase1(), new FirstComeFirstServe()),
            new Simulation(getJobsCase2(), new FirstComeFirstServe()),
            new Simulation(getJobsCase3(), new FirstComeFirstServe()) };

    private Simulation[] simSJF = {
            new Simulation(getJobsCase1(), new ShortestJobFirst()),
            new Simulation(getJobsCase2(), new ShortestJobFirst()),
            new Simulation(getJobsCase3(), new ShortestJobFirst()) };

    private Simulation[] simSRTF = {
            new Simulation(getJobsCase1(), new ShortestRemainingTimeFirst()),
            new Simulation(getJobsCase2(), new ShortestRemainingTimeFirst()),
            new Simulation(getJobsCase3(), new ShortestRemainingTimeFirst()) };

    void testFCFS(boolean print) {
        for (int i = 0; i < simFCFS.length; i++) {
            if (print) {
                IO.println(blue + "Running test First Come First Serve case " + (i + 1) + ": " + reset);
                simFCFS[i].run(true);
                IO.println("=".repeat(50));
            } else {
                simFCFS[i].run(false);
            }

        }
    }

    void testSJF(boolean print) {
        for (int i = 0; i < simSJF.length; i++) {
            if (print) {
                IO.println(blue + "Running test Shortest Job First " + (i + 1) + ": " + reset);
                simSJF[i].run(true);
                IO.println("=".repeat(50));
            } else {
                simSJF[i].run(false);
            }
        }
    }

    void testSRTF(boolean print) {
        for (int i = 0; i < simSRTF.length; i++) {
            if (print) {
                IO.println(blue + "Running test Shortest Remaining Time First " + (i + 1) + ": " + reset);
                simSRTF[i].run(true);
                IO.println("=".repeat(50));
            } else {
                simSRTF[i].run(false);
            }
        }
    }

    void main(boolean printStats, boolean printTable) {
        testFCFS(printStats);
        testSJF(printStats);
        testSRTF(printStats);
        if (printTable) {
            printCompTable("Average waiting time", Simulation::getAverageWT);
            printCompTable("Average turnaround time", Simulation::getAverageTT);
        }
    }

    void moveCursor(int row, int column) {
        String mc = "\u001B[" + row + ";" + column + "H";
        IO.print(mc);
    }

    void moveCursorToCol(int column) {
        String mc = "\u001B[" + column + "G";
        IO.print(mc);
    }

    void printCompTable(String title, Function<Simulation, Double> func) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append(blue + "============" + title + "============" + reset + "\n");
        sb1.append("Cases | FCFS | SJF  | SRTF | \n");
        IO.print(sb1);
        for (int i = 0; i < 3; i++) {
            moveCursorToCol(1);
            IO.print(i + "     | ");
            moveCursorToCol(9);
            IO.print(func.apply(simFCFS[i]));
            moveCursorToCol(14);
            IO.print("| " + func.apply(simSJF[i]));
            moveCursorToCol(21);
            IO.print("| " + func.apply(simSRTF[i]));
            moveCursorToCol(28);
            IO.println("|");

        }
        IO.println();
    }
}
