public class Process {

  private String name;
  private final int burstTime;
  private int waitingTime;
  private int remainingBurst;

  public Process(String name, int burstTime) {
    this.name = name;
    this.burstTime = this.remainingBurst = burstTime;
    this.waitingTime = 0;
  }

  public String getName() {
    return name;
  }

  public int getBurstTime() {
    return burstTime;
  }

  public int getRemaingBurstTime() {
    return remainingBurst;
  }

  public int getWaiting() {
    return waitingTime;
  }

  public int getTurnAroundTime() {
    return waitingTime + burstTime;
  }

  public void tickRun() {
    remainingBurst--;
  }

  public void tickWait() {
    waitingTime++;
  }

  public Boolean isDone() {
    return remainingBurst == 0;
  }
}
