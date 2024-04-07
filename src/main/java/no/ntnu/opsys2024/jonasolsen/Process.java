package no.ntnu.opsys2024.jonasolsen;

/**
 * Represents a process running on a CPU.
 */
public class Process {

    private int pID;
    private int arrivalTime;
    private int burstTime;
    private int priority;
    private int timeToComplete;
    private boolean finishedRunning;
    private int completionTime;
    private int timeRunning;
    private int waitingTime;

    public Process(int pID, int arrivalTime, int burstTime) {
        this.pID = pID;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = 0;
        this.timeToComplete = burstTime;
        this.finishedRunning = false;
        this.timeRunning = 0;
        this.waitingTime = 0;
    }
    public Process(int pID, int arrivalTime, int burstTime, int priority) {
        this.pID = pID;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.timeToComplete = burstTime;
        this.finishedRunning = false;
        this.timeRunning = 0;
        this.waitingTime = 0;
    }

    public int getpID() {
        return this.pID;
    }

    public int getArrivalTime() {
        return this.arrivalTime;
    }

    public int getBurstTime() {
        return this.burstTime;
    }

    public int getPriority() {
        return this.priority;
    }

    public void reduceTimeToComplete() {
        this.timeToComplete -= 1;
    }

    public int getTimeToComplete() {
        return this.timeToComplete;
    }

    public void setFinishedRunning(boolean status) {
        this.finishedRunning = status;
    }

    public boolean isFinishedRunning() {
        return finishedRunning;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public int getCompletionTime() {
        return this.completionTime;
    }

    public void incrementTimeRunning() {
        this.timeRunning++;
    }

    public int getTimeRunning() {
        return this.timeRunning;
    }

    public void incrementWaitingTime() {
        this.waitingTime++;
    }

    public int getWaitingTime() {
        return this.waitingTime;
    }
}
