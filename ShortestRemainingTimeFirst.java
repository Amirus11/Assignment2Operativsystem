public class ShortestRemainingTimeFirst extends Scheduler {

  @Override
  public void tick() {
    var processes = super.getProcesses();
    var currentJobIndex = processes.getShortesProcess();
    processes.tickProcess(currentJobIndex);
  }
}
