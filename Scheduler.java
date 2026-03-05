
public abstract class Scheduler {

  private ProcessList processes;

  public Scheduler() {
    this.processes = new ProcessList();
  }

  public void addProcess(Process process) {
    this.processes.add(process);
  }

  protected ProcessList getProcesses() {
    return this.processes;
  }

  public abstract void tick();

  public boolean isDone() {
    return this.processes.isEmpty();
  }
}
