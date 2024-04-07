package no.ntnu.opsys2024.jonasolsen;

import java.util.ArrayList;

/**
 * Represents a preemptive priority scheduling algorithm.
 */

public class PpsScheduler {
    ArrayList<Process> processes;

    /**
     * Initializes the parameters.
     * @param processes A list of processes with predetermined arrival times, burst times
     *                  and priority levels.
     */
    public PpsScheduler(Process[] processes) {
        // Bubble sort the list by arrival time (just in case user input is not in chronological order)
        for (int i = 0; i < processes.length; i++) {
            boolean isDone = true;
            for (int j = 0 ; j < processes.length -1; j++) {
                if (processes[j].getArrivalTime() > processes[j+1].getArrivalTime()) {
                    Process temp = processes[j];
                    processes[j] = processes[j+1];
                    processes[j+1] = temp;
                    isDone = false;
                }
            }
            if (isDone) {
                break;
            }
        }

        //Convert Process[] to ArrayList[]
        //NOTE: This was not necessary in the final solution. A Process[] would have also been ok.
        this.processes = new ArrayList<>();
        for (int i = 0; i < processes.length; i++) {
            this.processes.add(processes[i]);
        }

        //Runs the algorithm.
        this.run();
    }

    /**
     * Represents the algorithm itself.
     *
     * The output is the average waiting time and average turnaround time for processes running
     * on the CPU.
     */
    private void run() {

        //Mechanism for simulating time.
        int timer = 0;

        //Process currently running on the CPU.
        Process runningProcess = null;


        boolean finished = false;
        while(!finished) {

            int finishedProcesses = 0;

            //For-loop represents one time interval.
            for (Process p: this.processes) {

                //Assert if process is finished running. Go on to next process if true.
                if (p.isFinishedRunning()) {
                    finishedProcesses++;

                    //Assert if all processes are finished running. Break loop if true.
                    if (finishedProcesses == this.processes.size()) {
                        finished = true;
                        break;
                    }

                    continue;
                }

                //Executes if a process is running or is in the queue.
                if (p.getArrivalTime() <= timer) {

                    //Make sure there is a process running.
                    if (runningProcess == null) {
                        runningProcess = p;
                    } else {

                        //If a process has a higher priority than the running process
                        //We preempt the CPU and switch to running the new process.
                        if (p.getPriority() < runningProcess.getPriority()) {
                            runningProcess = p;
                        } else if (p == runningProcess){
                            //If p is the running process we change nothing.
                        }else {//If the process has lower priority it waits in the queue.
                            p.incrementWaitingTime();
                        }
                    }
                }

            }

            //If all processes are not finished:
            if (!finished) {

                //Increment the timer by 1 interval
                timer++;

                //Simulates running the process for one interval. False result means the CPU is idling.
                if (runningProcess != null) {

                    //Reduce the running process time to complete by 1 interval.
                    runningProcess.reduceTimeToComplete();
                    runningProcess.incrementTimeRunning();


                    //Check if the running process is finished running.
                    if (runningProcess.getTimeToComplete() == 0) {
                        runningProcess.setFinishedRunning(true);
                        runningProcess.setCompletionTime(timer);

                        //Remove the process.
                        runningProcess = null;
                    }
                }



            }

        }

        //Calculate and present average waiting time and average turnaround time.

        int[] turnAroundTimes = new int[processes.size()];
        int[] waitingTimes = new int[processes.size()];

        int i = 0;
        for (Process p : processes) {
            turnAroundTimes[i] = p.getCompletionTime() - p.getArrivalTime();
            waitingTimes[i] = p.getWaitingTime();
            i++;
        }

        int totalWaitingTime = 0;
        for (int j = 0; j < waitingTimes.length; j++) {
            totalWaitingTime = totalWaitingTime + waitingTimes[j];
        }
        float averageWaitingTime = (float) totalWaitingTime / (float) waitingTimes.length;

        int totalTurnAroundTime = 0;
        for (int j = 0; j < turnAroundTimes.length ; j++) {
            totalTurnAroundTime = totalTurnAroundTime + turnAroundTimes[j];
        }
        float averageTurnAroundTime = (float) totalTurnAroundTime / (float) turnAroundTimes.length;

        System.out.println("Average waiting time: " + averageWaitingTime + "\n"
                + "Average turnaround time: " + averageTurnAroundTime);

    }

}
