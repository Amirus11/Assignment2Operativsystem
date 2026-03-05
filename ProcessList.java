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

    for (int j = i - 1; j >= 0; j--) {
      this.processes.get(j).tickWait();
    }
    for (int j = i + 1; j < this.processes.size(); j++) {
      this.processes.get(j).tickWait();
    }
    if (p.isDone()) {
      this.processes.remove(i);
      IO.println(p.getName() + " is done, had a burst time of: " + p.getBurstTime());
    }
  }

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

  public Process getProcess(int i) {
    return this.processes.get(i);
  }
}
