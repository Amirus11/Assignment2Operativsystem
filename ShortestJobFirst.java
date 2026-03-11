/**
 * In the Shortest Job First (SJF) algorithm the process with the shortest burst time is executed
 * first. The process that has the longest burst time will have to wait until the shorter processes
 * finish their execution. This algorithm is non-preemptive, meaning that once a process starts
 * executing, it cannot be interrupted until it finishes.
 */
public class ShortestJobFirst extends Scheduler {
  private int currentJobIndex = 0;

  @Override
  public void tick() {
    if (super.getLastTickedProcess() == null || super.getLastTickedProcess().isDone()) {
      currentJobIndex = super.getShortesProcess();
    }
    super.tickProcess(currentJobIndex);
  }
}
