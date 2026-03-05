
import java.util.stream.Collectors;

void test(List<Job> jobs, Scheduler scheduler) {

  var processes = jobs.stream()
      .collect(Collectors.groupingBy(Job::arrivalTime, Collectors.mapping(Job::process, Collectors.toList())));
  var time = 0;

  // Adding processes with arrival = 0 and running first tick
  for (Process p : processes.get(0)) {
    scheduler.addProcess(p);
  }
  scheduler.tick();
  time++;

  while (!scheduler.isDone()) {
    // adding proccesses to the scheduler that arrives at current time instant
    var ps = processes.get(time);
    if (ps != null) {
      for (Process p : ps) {
        scheduler.addProcess(p);
      }
    }

    // scheduler runs a given process for a burst time of 1
    scheduler.tick();
    time++;
  }

  for (Job job : jobs) {

    IO.println("Completion time for: " + job.process().getName() + " was " + (job.process().getTurnAroundTime()
        + job.arrivalTime()));
    IO.println("Waiting time for: " + job.process().getName() + " was " + job.process().getWaiting());
    IO.println("Turnaround time for: " + job.process().getName() + " was " + job.process().getTurnAroundTime());
    IO.println("-".repeat(50));
  }
  IO.println("Average Waiting Time: " + jobs.stream().mapToInt(j -> j.process().getWaiting()).average().getAsDouble());
  IO.println("Average Turnaround Time: "
      + jobs.stream().mapToInt(j -> j.process().getTurnAroundTime()).average().getAsDouble());
}

private static final List<Job> jobsCase1 = List.of(
    new Job(0, new Process("p1", 8)),
    new Job(0, new Process("p2", 4)),
    new Job(0, new Process("p3", 2)),
    new Job(0, new Process("p4", 6)),
    new Job(0, new Process("p5", 3)));

private Scheduler schedulerFCFS = new FirstComeFirstServe();

void testFirstComeFirstServeCase1() {
  IO.println("Running test First Come First Serve case 1: ");
  test(jobsCase1, schedulerFCFS);
  IO.println("=".repeat(50));
}

void main() {
  testFirstComeFirstServeCase1();
}
