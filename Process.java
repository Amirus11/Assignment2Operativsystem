public class Process {

  private String name;
  private final int burstTime;
  private int waitingTime;
  private int remainingBurst;

  /*
   * This is the constructor for the process class
   *
   * @param name the name of the process
   * 
   * @param burstTime the burst time of the process
   */
  public Process(String name, int burstTime) {
    this.name = name;
    this.burstTime = this.remainingBurst = burstTime;
    this.waitingTime = 0;
  }

  /*
   * This method is responsible for returning the name of the process
   */
  public String getName() {
    return name;
  }

  /*
   * The method is responsibe for returning the burst time
   */
  public int getBurstTime() {
    return burstTime;
  }

  /*
   * This method is responsible for returning the remaining burst time for a
   * process
   */
  public int getRemaingBurstTime() {
    return remainingBurst;
  }

  /*
   * This method is responsible for returning the waiting time
   */
  public int getWaiting() {
    return waitingTime;
  }

  /*
   * This method is responsible for returning the turnaround time
   */
  public int getTurnAroundTime() {
    return waitingTime + burstTime;
  }

  /*
   * The method is Responsible for lowering the remaining burst time each tick
   */
  public void tickRun() {
    remainingBurst--;
  }

  /*
   * This method is reponsible for increasing the waitingtime each tick
   */
  public void tickWait() {
    waitingTime++;
  }

  /*
   * This method is responsible for setting the remaining bursttime to zero when
   * the process is isDone
   */
  public Boolean isDone() {
    return remainingBurst == 0;
  }
}
