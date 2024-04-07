package no.ntnu.opsys2024.jonasolsen;

import java.util.Scanner;

/**
 * Starts the java application.
 *
 * Uses the first-come-first-served algorithm to generate output based on user input.
 */
public class RunFCFS {

    /**
     * Method for starting the application
     * @param args
     */
    public static void main(String[] args) {
        RunFCFS s = new RunFCFS();

    }

    public RunFCFS() {
        Scanner s = new Scanner(System.in);
        System.out.println("Type number of processes: ");
        int numberOfProcesses = s.nextInt();
        System.out.println("\n");

        Process[] processes = new Process[numberOfProcesses];

        for (int i = 0 ; i < numberOfProcesses ; i++) {
            System.out.println("\n Enter arrival time: ");
            int arrivalTime = s.nextInt();
            System.out.println("\n Enter burst time: ");
            int burstTime = s.nextInt();
            processes[i] = new Process(i, arrivalTime, burstTime);
        }
        FcfsScheduler scheduler = new FcfsScheduler(processes);
    }

}
