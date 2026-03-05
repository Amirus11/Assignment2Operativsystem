import java.util.ArrayList;
import java.util.List;

public class ProcessList {
  private List<Process> processes;

  public ProcessList() {
    this.processes = new ArrayList<>();
  }

  public void add(Process p) {
    this.processes.add(p);
  }

  public Boolean isEmpty() {
    return this.processes.isEmpty();
  }

  public void tickProcess(int i) {
    var p = this.processes.get(i);
    p.tickRun();
    if (p.isDone()) {
      this.processes.remove(i);
    }
    for (int j = i - 1; j >= 0; j--) {
      p.tickWait();
    }
    for (int j = i + 1; j < this.processes.size(); j++) {
      p.tickWait();
    }
  }
}
