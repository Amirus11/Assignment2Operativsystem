public class ShortestJobFirst extends Scheduler {
  private int currentJobRemaining = 0;
  private int currentJobIndex = 0;

  @Override
  public void tick() {
    var processes = super.getProcesses();
    if (currentJobRemaining <= 0) {
      currentJobIndex = processes.getShortesProcess();
      Process p = processes.getProcess(currentJobIndex);
      currentJobRemaining = p.getBurstTime();
    }
    processes.tickProcess(currentJobIndex);
    currentJobRemaining--;
  }
}
