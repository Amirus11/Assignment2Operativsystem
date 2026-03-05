import java.util.stream.Collectors;

private static final List<Job> jobs = List.of(
    new Job(0, new Process("p1", 8)),
    new Job(0, new Process("p2", 4)),
    new Job(0, new Process("p3", 2)),
    new Job(0, new Process("p4", 6)),
    new Job(0, new Process("p5", 3)));

private FirstComeFirstServe scheduler = new FirstComeFirstServe();

void main() {

  var processes = jobs.stream()
      .collect(Collectors.groupingBy(Job::arrivalTime, Collectors.mapping(Job::process, Collectors.toList())));
  var time = 0;

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
}
