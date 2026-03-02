
public class Process {

  private String name;
  private int burstTime;
  private int waitingTime;
  private int remainingBurst;

  public Process(String name, int burstTime) {
    this.name = name;
    this.burstTime = this.remainingBurst = burstTime;
  }

  public String getName() {
    return name;
  }

  public int getBurstTime() {
    return burstTime;
  }

  public int getWaiting() {
    return waitingTime;
  }

  public int getTurnAroundTime() {
    return waitingTime + burstTime;
  }

}
