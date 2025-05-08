import java.util.Scanner;

public class SimpleSJF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;

        System.out.print("Enter number of processes: ");
        n = sc.nextInt();

        int[] bt = new int[n];  // Burst time
        int[] wt = new int[n];  // Waiting time
        int[] tat = new int[n]; // Turnaround time
        int[] p = new int[n];   // Process IDs

        // Input burst times
        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            bt[i] = sc.nextInt();
            p[i] = i + 1;
        }

        // Sort processes by burst time using simple bubble sort
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (bt[j] > bt[j + 1]) {
                    // Swap burst time
                    int temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;

                    // Swap process ID
                    int tempP = p[j];
                    p[j] = p[j + 1];
                    p[j + 1] = tempP;
                }
            }
        }

        // Calculate waiting time
        wt[0] = 0;
        for (int i = 1; i < n; i++) {
            wt[i] = wt[i - 1] + bt[i - 1];
        }

        // Calculate turnaround time
        for (int i = 0; i < n; i++) {
            tat[i] = wt[i] + bt[i];
        }

        // Display results
        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");
        int totalWT = 0, totalTAT = 0;
        for (int i = 0; i < n; i++) {
            System.out.println("P" + p[i] + "\t\t" + bt[i] + "\t\t" + wt[i] + "\t\t" + tat[i]);
            totalWT += wt[i];
            totalTAT += tat[i];
        }

        System.out.printf("\nAverage Waiting Time: %.2f", (float) totalWT / n);
        System.out.printf("\nAverage Turnaround Time: %.2f", (float) totalTAT / n);
    }
}
