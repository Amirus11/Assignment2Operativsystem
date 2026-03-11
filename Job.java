/**
 * A Job represents a process that is scheduled to run at a specific arrival time.
 *
 * @param arrivalTime The time at which the job arrives and is ready to be scheduled.
 * @param process The process associated with this job.
 */
public record Job(int arrivalTime, Process process) {}
