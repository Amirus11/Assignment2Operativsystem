/**
 * In the Shortes Remaining Time First (SRTF) algorithm the process with the shortest remaining
 * burst time is executed first. The process that has the longest remaining burst time will have to
 * wait until the shorter processes finish their execution. This algorithm is preemptive, meaning
 * that if a new process arrives with a shorter remaining burst time than the currently executing
 * process, the currently executing process will be interrupted and the new process will start
 * executing.
 */
public class ShortestRemainingTimeFirst extends Scheduler {
  @Override
  public void tick() {
    var currentJobIndex = super.getShortesProcess();
    super.tickProcess(currentJobIndex);
  }
}
