import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class Simulation {
    List<Job> jobs;
    double averageWT;
    double averageTT;

    Scheduler scheduler;

    public Simulation(List<Job> jobs, Scheduler scheduler) {
        this.jobs = jobs;
        this.scheduler = scheduler;
    }

    public void run() {
        var processes = jobs.stream()
                .collect(
                        Collectors.groupingBy(
                                Job::arrivalTime, Collectors.mapping(Job::process, Collectors.toList())));
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

        IO.println();
        for (Job job : jobs) {
            IO.println(
                    "Completion time for: "
                            + job.process().getName()
                            + " was "
                            + (job.process().getTurnAroundTime() + job.arrivalTime()));
            IO.println(
                    "Waiting time for: " + job.process().getName() + " was " + job.process().getWaiting());
            IO.println(
                    "Turnaround time for: "
                            + job.process().getName()
                            + " was "
                            + job.process().getTurnAroundTime());
            IO.println("-".repeat(50));
        }
        IO.println(
                "Average Waiting Time: "
                        + jobs.stream().mapToInt(j -> j.process().getWaiting()).average().getAsDouble());
        IO.println(
                "Average Turnaround Time: "
                        + jobs.stream().mapToInt(j -> j.process().getTurnAroundTime()).average().getAsDouble());
    }

    public void runTableComps() {
        var processes = jobs.stream()
                .collect(
                        Collectors.groupingBy(
                                Job::arrivalTime, Collectors.mapping(Job::process, Collectors.toList())));
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

        this.averageWT = jobs.stream().mapToInt(j -> j.process().getWaiting()).average().getAsDouble();
        this.averageTT = jobs.stream().mapToInt(j -> j.process().getTurnAroundTime()).average().getAsDouble();

    }

    public double getAverageWT() {
        BigDecimal bd = BigDecimal.valueOf(averageWT);
        bd = bd.setScale(1, RoundingMode.DOWN);
        return bd.doubleValue();
    }

    public double getAverageTT() {
        BigDecimal bd = BigDecimal.valueOf(averageTT);
        bd = bd.setScale(1, RoundingMode.DOWN);
        return bd.doubleValue();

    }
}
