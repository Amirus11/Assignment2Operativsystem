
public class FirstComeFirstServe extends Scheduler {

  @Override
  public void tick() {
    var processes = super.getProcesses();
    processes.tickProcess(0);
  }
}
