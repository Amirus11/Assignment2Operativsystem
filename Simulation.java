import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@link Simulation} class represents a simulation of a process scheduler. It takes a list of
 * {@link Job}s and a {@link Scheduler} and runs the simulation by adding the processes to the
 * scheduler at the correct time and ticking the scheduler until all processes are done. After the
 * simulation is done, it calculates the average waiting time and average turnaround time of the
 * processes.
 *
 * @see Scheduler
 */
public class Simulation {
  private List<Job> jobs;
  private double averageWaitingTime;
  private double averageTurnaroundTime;
  private Scheduler scheduler;

  /**
   * Creates a new Simulation with the given list of {@link Job}s and {@link Scheduler}.
   *
   * @param jobs the list of jobs to be simulated
   * @param scheduler the scheduler to be used in the simulation
   */
  public Simulation(List<Job> jobs, Scheduler scheduler) {
    this.jobs = jobs;
    this.scheduler = scheduler;
  }

  /**
   * Runs the simulation by doing the following.
   *
   * <ol>
   *   <li>Adding the processes at the current time instance to the {@link Scheduler}
   *   <li>Running the {@link Scheduler#tick()}
   *   <li>Increases the time by one
   * </ol>
   *
   * This is repeated until the {@link Scheduler#isDone()} returns {@code true}.
   */
  public void run() {
    // grouping the jobs into Map<arrivalTime, List<Process>
    var processes =
        jobs.stream()
            .collect(
                Collectors.groupingBy(
                    Job::arrivalTime, Collectors.mapping(Job::process, Collectors.toList())));
    var time = 0;

    // Adding processes with arrival = 0 and running first tick
    for (Process p : processes.get(0)) {
      scheduler.addProcess(p);
    }
    scheduler.tick();
    time++;

    while (!scheduler.isDone()) {
      // adding proccesses to the scheduler that arrives at current time instant
      var ps = processes.get(time);
      if (ps != null) {
        for (Process p : ps) {
          scheduler.addProcess(p);
        }
      }

      // scheduler runs a given process for a burst time of 1
      scheduler.tick();
      time++;
    }

    this.averageWaitingTime =
        jobs.stream().mapToInt(j -> j.process().getWaiting()).average().getAsDouble();
    this.averageTurnaroundTime =
        jobs.stream().mapToInt(j -> j.process().getTurnAroundTime()).average().getAsDouble();
  }

  /**
   * Returns the average waiting time of the processes in the simulation. The value is rounded down
   * to one decimal place.
   *
   * @return the average waiting time of the processes in the simulation
   */
  public double getAverageWaitingTime() {
    BigDecimal bd = BigDecimal.valueOf(averageWaitingTime);
    bd = bd.setScale(1, RoundingMode.DOWN);
    return bd.doubleValue();
  }

  /**
   * Returns the average turnaround time of the processes in the simulation. The value is rounded
   * down to one decimal place.
   *
   * @return the average turnaround time of the processes in the simulation
   */
  public double getAverageTurnaroundTime() {
    BigDecimal bd = BigDecimal.valueOf(averageTurnaroundTime);
    bd = bd.setScale(1, RoundingMode.DOWN);
    return bd.doubleValue();
  }

  /**
   * Returns the list of {@link Job}s that were simulated in this simulation.
   *
   * @return the list of jobs that were simulated
   */
  public List<Job> getJobs() {
    return this.jobs;
  }
}
