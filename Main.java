import java.util.stream.Collectors;

private List<Job> jobs = List.of(
    new Job(0, new Process("p1", 8)),
    new Job(0, new Process("p2", 4)),
    new Job(0, new Process("p3", 2)),
    new Job(0, new Process("p4", 6)),
    new Job(0, new Process("p5", 3)));

private FirstComeFirstServe jobExecutor = new FirstComeFirstServe();

void main() {

  var processes = jobs.stream()
      .collect(Collectors.groupingBy(Job::arrivalTime, Collectors.mapping(Job::process, Collectors.toList())));
  var time = 0;

  while (!jobExecutor.isDone()) {
    var ps = processes.get(time);
    if (ps != null) {
      for (Process p : ps) {
        jobExecutor.addProcess(p);
      }
    }
  }
}
