import java.util.List;
import java.util.function.Function;

/**
 * The {@link Test} class represents a test suite for the process scheduling algorithms. It defines
 * several test cases with different set of jobs and defines simulations for each scheduling
 * algorithm.
 *
 * @see Simulation
 * @see Scheduler
 */
public class Test {
  private static List<Job> getJobsCase1() {
    return List.of(
        new Job(0, new Process("p1", 8)),
        new Job(0, new Process("p2", 4)),
        new Job(0, new Process("p3", 2)),
        new Job(0, new Process("p4", 6)),
        new Job(0, new Process("p5", 3)));
  }

  private static List<Job> getJobsCase2() {
    return List.of(
        new Job(0, new Process("p1", 20)),
        new Job(1, new Process("p2", 2)),
        new Job(2, new Process("p3", 2)),
        new Job(3, new Process("p4", 1)),
        new Job(4, new Process("p5", 3)));
  }

  private static List<Job> getJobsCase3() {
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
    new Simulation(getJobsCase3(), new FirstComeFirstServe())
  };

  private Simulation[] simSJF = {
    new Simulation(getJobsCase1(), new ShortestJobFirst()),
    new Simulation(getJobsCase2(), new ShortestJobFirst()),
    new Simulation(getJobsCase3(), new ShortestJobFirst())
  };

  private Simulation[] simSRTF = {
    new Simulation(getJobsCase1(), new ShortestRemainingTimeFirst()),
    new Simulation(getJobsCase2(), new ShortestRemainingTimeFirst()),
    new Simulation(getJobsCase3(), new ShortestRemainingTimeFirst())
  };

  /**
   * Runs the given simulations by calling the {@link Simulation#run()} method on each simulation in
   * the array.
   *
   * @param sims the array of simulations to be run
   */
  private static void runSimulations(Simulation[] sims) {
    for (int i = 0; i < sims.length; i++) {
      sims[i].run();
    }
  }

  /**
   * Prints the statistics for the given simulations.
   *
   * <p>Assuming the simulations inputed uses the same algorithm, but with different test cases.
   *
   * @param sims the array of simulations for which the statistics are to be printed
   * @param title the title to be printed before the statistics of each simulation
   */
  private static void printSimulationStats(Simulation[] sims, String title) {
    for (int i = 0; i < sims.length; i++) {
      IO.println(blue + "Running test " + title + " case " + (i + 1) + ": " + reset);
      for (Job job : sims[i].getJobs()) {
        IO.println(
            "Completion time for: "
                + job.process().getName()
                + " was "
                + (job.process().getTurnAroundTime() + job.arrivalTime()));
        IO.println(
            "Waiting time for: " + job.process().getName() + " was " + job.process().getWaiting());
        IO.println(
            "Turnaround time for: "
                + job.process().getName()
                + " was "
                + job.process().getTurnAroundTime());
        IO.println("-".repeat(50));
      }
      IO.println("Average Waiting Time: " + sims[i].getAverageWaitingTime());
      IO.println("Average Turnaround Time: " + sims[i].getAverageTurnaroundTime());
      IO.println("=".repeat(50));
      IO.println();
    }
  }

  /**
   * Runs the simulations for each scheduling algorithm and prints the statistics and comparison
   * table for average waiting time and average turnaround time.
   *
   * @param printStats whether to print the statistics for each simulation
   * @param printTable whether to print the comparison table for average waiting time and average
   *     turnaround time
   */
  void main(boolean printStats, boolean printTable) {
    runSimulations(simFCFS);
    runSimulations(simSJF);
    runSimulations(simSRTF);
    if (printStats) {
      printSimulationStats(simFCFS, "First Come First Serve");
      printSimulationStats(simSJF, "Shortest Job First");
      printSimulationStats(simSRTF, "Shortest Remaining Time");
    }
    if (printTable) {
      printCompTable("Average waiting time", Simulation::getAverageWaitingTime);
      printCompTable("Average turnaround time", Simulation::getAverageTurnaroundTime);
    }
  }

  /**
   * Moves the cursor to the given column in the console output. This is done by printing the ANSI
   * escape code for moving the cursor to the specified column.
   *
   * @param column the column number to which the cursor should be moved
   */
  private static void moveCursorToCol(int column) {
    String mc = "\u001B[" + column + "G";
    IO.print(mc);
  }

  /**
   * Prints a comparison table for the given title and function. The function is applied to each
   * simulation in the arrays of simulations for each scheduling algorithm, and the results are
   * printed in a tabular format.
   *
   * @param title the title to be printed before the comparison table
   * @param func the function to be applied to each simulation to get the value to be printed in the
   *     table
   */
  private void printCompTable(String title, Function<Simulation, Double> func) {
    StringBuilder sb1 = new StringBuilder();
    sb1.append(blue + "============" + title + "============" + reset + "\n");
    sb1.append("Cases | FCFS | SJF  | SRTF | \n");
    IO.print(sb1);
    for (int i = 0; i < 3; i++) {
      moveCursorToCol(1);
      IO.print((i + 1) + "     | ");
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
