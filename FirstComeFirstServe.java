/**
 * In the First Come First Serve (FCFS) algorithm, the process that arrives first is executed first.
 * The process that arrives later will have to wait until the first process finishes its execution.
 * This algorithm is non-preemptive, meaning that once a process starts executing, it cannot be
 * interrupted until it finishes.
 */
public class FirstComeFirstServe extends Scheduler {
  @Override
  public void tick() {
    super.tickProcess(0);
  }
}
