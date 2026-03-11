/**
 * This class represents a single process with the provided name and burst time and includes methods
 * to execute it or make it wait.
 */
public class Process {
  private String name;
  private final int burstTime;
  private int waitingTime;
  private int remainingBurst;

  /**
   * Creates a new Process with the given name and burst time. The waiting time is initialized to 0
   * and the remaining burst time is set to the burst time.
   *
   * @param name the name of the process
   * @param burstTime the burst time of the process
   */
  public Process(String name, int burstTime) {
    this.name = name;
    this.burstTime = this.remainingBurst = burstTime;
    this.waitingTime = 0;
  }

  /**
   * Returns the name of the process.
   *
   * @return the name of the process.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the remaining burst time of the process.
   *
   * @return the remaining burst time of the process
   */
  public int getRemaingBurstTime() {
    return remainingBurst;
  }

  /**
   * Returns the waiting time of the process.
   *
   * @return the waiting time of the process
   */
  public int getWaiting() {
    return waitingTime;
  }

  /**
   * Returns the turnaround time of the process, which is the sum of the waiting time and the burst
   * time.
   *
   * @return the turnaround time of the process
   */
  public int getTurnAroundTime() {
    return waitingTime + burstTime;
  }

  /** Ticks the process by decrementing the remaining burst time by 1. */
  public void tickRun() {
    remainingBurst--;
  }

  /** Ticks the process by incrementing the waiting time by 1. */
  public void tickWait() {
    waitingTime++;
  }

  /**
   * Returns whether or not the process is done, meaning that its remaining burst time is 0.
   *
   * @return {@code true} if the process is done, and {@code false} otherwise
   */
  public Boolean isDone() {
    return remainingBurst == 0;
  }
}
