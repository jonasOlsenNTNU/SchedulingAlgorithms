package no.ntnu.opsys2024.jonasolsen;

import java.util.Scanner;

public class RunPPS {

    public static void main(String[] args) {
        RunPPS s = new RunPPS();

    }

    public RunPPS() {
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
            System.out.println("\n Enter priority: ");
            int priority = s.nextInt();
            processes[i] = new Process(i, arrivalTime, burstTime, priority);

        }

        PpsScheduler scheduler = new PpsScheduler(processes);
    }
}
