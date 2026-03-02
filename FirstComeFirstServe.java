import java.util.ArrayList;
import java.util.List;

public class FirstComeFirstServe {

  private List<Process> processes;
  private int time;

  public FirstComeFirstServe() {
    this.processes = new ArrayList<>();
  }

  public void addProcess(Process process) {
    processes.add(process);
  }

  public void tick() {
    time++;
  }

  public boolean isDone() {
    return false;
  }
}
