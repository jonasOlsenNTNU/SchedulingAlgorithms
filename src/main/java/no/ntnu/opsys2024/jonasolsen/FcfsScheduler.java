package no.ntnu.opsys2024.jonasolsen;

import java.util.Arrays;

public class FcfsScheduler {
    Process[] processes;

    public FcfsScheduler(Process[] p) {
        this.processes = p;


        // Bubble sort the list by arrival time (just in case the user input is not in chronological order)
        for (int i = 0; i < this.processes.length; i++) {
            boolean isDone = true;
            for (int j = 0 ; j < this.processes.length -1; j++) {
                if (this.processes[j].getArrivalTime() > this.processes[j+1].getArrivalTime()) {
                    Process temp = this.processes[j];
                    this.processes[j] = this.processes[j+1];
                    this.processes[j+1] = temp;
                    isDone = false;
                }
            }
            if (isDone) {
                break;
            }
        }



        //Calculate average waiting time and average turnaround time
        int[] turnAroundTimes = new int[this.processes.length];
        int[] waitingTimes = new int[this.processes.length];
        int[] completionTimes = new int[this.processes.length];

        for (int i = 0; i < processes.length; i++) {

            if (completionTimes[0] == 0) { //First process (marks the start of the calculations)
                int completionTime = this.processes[i].getArrivalTime() + this.processes[i].getBurstTime();
                completionTimes[i] = completionTime;
                turnAroundTimes[i] = completionTime - this.processes[i].getArrivalTime();
                waitingTimes[i] = turnAroundTimes[i] - this.processes[i].getBurstTime();
            } else { //Every process after

                //The process is first in the queue
                if (this.processes[i].getArrivalTime() >= completionTimes[i-1]) {
                    int completionTime = this.processes[i].getArrivalTime() + this.processes[i].getBurstTime();
                    completionTimes[i] = completionTime;
                    turnAroundTimes[i] = completionTime - this.processes[i].getArrivalTime();
                    waitingTimes[i] = turnAroundTimes[i] - this.processes[i].getBurstTime();
                 }
                //The process has to wait in the queue
                else {
                    int completionTime = completionTimes[i-1] + this.processes[i].getBurstTime();
                    completionTimes[i] = completionTime;
                    turnAroundTimes[i] = completionTime - this.processes[i].getArrivalTime();
                    waitingTimes[i] = turnAroundTimes[i] - this.processes[i].getBurstTime();
                }
            }
        }

        int totalWaitingTime = 0;
        for (int i = 0; i < waitingTimes.length; i++) {
            totalWaitingTime = totalWaitingTime + waitingTimes[i];
        }
        float averageWaitingTime = (float) totalWaitingTime / (float) waitingTimes.length;

        int totalTurnAroundTime = 0;
        for (int i = 0; i < turnAroundTimes.length ; i++) {
            totalTurnAroundTime = totalTurnAroundTime + turnAroundTimes[i];
        }
        float averageTurnAroundTime = (float) totalTurnAroundTime / (float) turnAroundTimes.length;

        System.out.println("Average waiting time: " + averageWaitingTime + "\n"
                            + "Average turnaround time: " + averageTurnAroundTime);

    }
}
