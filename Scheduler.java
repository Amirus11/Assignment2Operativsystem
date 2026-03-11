import java.util.ArrayList;
import java.util.List;

/**
 * The {@link Scheduler} class represents a process scheduler. It provides utility for managing
 * processes. Imlementations of the scheduler (scheduling algorithms) define the behavior of the
 * {@link #tick()}.
 */
public abstract class Scheduler {
  private List<Process> processes;
  private Process lastTickedProcess;

  /** Creates a new Scheduler with an empty list of processes. */
  public Scheduler() {
    this.processes = new ArrayList<>();
  }

  /**
   * Adds a process to the Scheduler.
   *
   * @param process the process to be added
   */
  public void addProcess(Process process) {
    this.processes.add(process);
  }

  /**
   * Returns the last ticked process in the scheduler. This is the process that was ticked in the
   * last call to {@link #tickProcess(int)}.
   *
   * @return the last ticked process
   */
  public Process getLastTickedProcess() {
    return this.lastTickedProcess;
  }

  /**
   * Ticks the process that should be ticked according to the scheduling algorithm implemented by
   * the subclass. This method should call {@link #tickProcess(int)} with the index of the process
   * to be ticked.
   */
  public abstract void tick();

  /**
   * Ticks the process at the given index and updates the waiting time of all other processes. If
   * the ticked process is done after ticking, it is removed from the list of processes.
   *
   * @param i the index of the process to be ticked
   * @throws IndexOutOfBoundsException if {@code i} is less than 0 or greater than or equal to the
   *     number of processes in the scheduler
   */
  public void tickProcess(int i) {
    var p = this.processes.get(i);
    p.tickRun();
    this.lastTickedProcess = p;

    for (int j = i - 1; j >= 0; j--) {
      this.processes.get(j).tickWait();
    }
    for (int j = i + 1; j < this.processes.size(); j++) {
      this.processes.get(j).tickWait();
    }
    if (p.isDone()) {
      this.processes.remove(i);
    }
  }

  /**
   * Returns the index of the process with the shortest remaining burst time. If there are multiple
   * processes with the same shortest remaining burst time, the index of the first one is returned.
   *
   * @return the index of the process with the shortest remaining burst time
   */
  public int getShortesProcess() {
    var shortest = this.processes.get(0);
    var index = 0;
    for (int i = 1; i < this.processes.size(); i++) {
      var p = this.processes.get(i);
      if (p.getRemaingBurstTime() < shortest.getRemaingBurstTime()) {
        shortest = p;
        index = i;
      }
    }
    return index;
  }

  /**
   * Returns whether or not the scheduler is done, meaning that there are no more processes to
   * execute.
   *
   * @return {@code true} if the scheduler is done, {@code false} otherwise
   */
  public boolean isDone() {
    return this.processes.isEmpty();
  }
}
