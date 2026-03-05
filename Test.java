List<Job> getJobsCase1() {
  return List.of(
      new Job(0, new Process("p1", 8)),
      new Job(0, new Process("p2", 4)),
      new Job(0, new Process("p3", 2)),
      new Job(0, new Process("p4", 6)),
      new Job(0, new Process("p5", 3)));
}

List<Job> getJobsCase2() {
  return List.of(
      new Job(0, new Process("p1", 20)),
      new Job(1, new Process("p2", 2)),
      new Job(2, new Process("p3", 2)),
      new Job(3, new Process("p4", 1)),
      new Job(4, new Process("p5", 3)));
}

List<Job> getJobsCase3() {
  return List.of(
      new Job(0, new Process("p1", 20)),
      new Job(1, new Process("p2", 2)),
      new Job(2, new Process("p3", 2)),
      new Job(3, new Process("p4", 2)),
      new Job(4, new Process("p5", 2)),
      new Job(5, new Process("p6", 2)));
}

private static final String blue = "\u001B[34m";
private static final String reset = "\u001B[0m";

void testFirstComeFirstServeCase1() {
  IO.println(blue + "Running test First Come First Serve case 1: " + reset);
  var s = new Simulation(getJobsCase1(), new FirstComeFirstServe());
  s.run();
  IO.println("=".repeat(50));
}

void testFirstComeFirstServeCase2() {
  IO.println(blue + "Running test First Come First Serve case 2: " + reset);
  var s = new Simulation(getJobsCase2(), new FirstComeFirstServe());
  s.run();
  IO.println("=".repeat(50));
}

void testFirstComeFirstServeCase3() {
  IO.println(blue + "Running test First Come First Serve case 3: " + reset);
  var s = new Simulation(getJobsCase3(), new FirstComeFirstServe());
  s.run();
  IO.println("=".repeat(50));
}

private void testShortestJobFirstCase1() {
  IO.println(blue + "Running test Shortest Job First case 1: " + reset);
  var s = new Simulation(getJobsCase1(), new ShortestJobFirst());
  s.run();
  IO.println("=".repeat(50));
}

private void testShortestJobFirstCase2() {
  IO.println(blue + "Running test Shortest Job First case 2: " + reset);
  var s = new Simulation(getJobsCase2(), new ShortestJobFirst());
  s.run();
  IO.println("=".repeat(50));
}

private void testShortestJobFirstCase3() {
  IO.println(blue + "Running test Shortest Job First case 3: " + reset);
  var s = new Simulation(getJobsCase3(), new ShortestJobFirst());
  s.run();
  IO.println("=".repeat(50));
}

private void testShortestRemainingTimeFirstCase1() {
  IO.println(blue + "Running test Shortest Remaining Time First case 1: " + reset);
  var s = new Simulation(getJobsCase1(), new ShortestRemainingTimeFirst());
  s.run();
  IO.println("=".repeat(50));
}

private void testShortestRemainingTimeFirstCase2() {
  IO.println(blue + "Running test Shortest Remaining Time First case 2: " + reset);
  var s = new Simulation(getJobsCase2(), new ShortestRemainingTimeFirst());
  s.run();
  IO.println("=".repeat(50));
}

private void testShortestRemainingTimeFirstCase3() {
  IO.println(blue + "Running test Shortest Remaining Time First case 3: " + reset);
  var s = new Simulation(getJobsCase3(), new ShortestRemainingTimeFirst());
  s.run();
  IO.println("=".repeat(50));
}

void main() {
  testFirstComeFirstServeCase1();
  testFirstComeFirstServeCase2();
  testFirstComeFirstServeCase3();
  testShortestJobFirstCase1();
  testShortestJobFirstCase2();
  testShortestJobFirstCase3();
  testShortestRemainingTimeFirstCase1();
  testShortestRemainingTimeFirstCase2();
  testShortestRemainingTimeFirstCase3();
}
