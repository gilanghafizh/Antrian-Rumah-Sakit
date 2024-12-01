//Muhammad Gilang Hafizh - 235150707111011 - Project Akhir ASD (TI - D)
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Patient {
    private String name;
    private int severity; 

    public Patient(String name, int severity) {
        this.name = name;
        this.severity = severity;
    }

    public String getName() {
        return name;
    }

    public int getSeverity() {
        return severity;
    }

    @Override
    public String toString() {
        return "Nama: " + name + ", Tingkat Kegawatdaruratan: " + severity;
    }
}

class HospitalQueue {
    private Queue<Patient> queue;

    public HospitalQueue() {
        queue = new LinkedList<>();
    }

    public void addPatient(String name, int severity) {
        queue.add(new Patient(name, severity));
    }

    public void sortBySeverity() {
        LinkedList<Patient> list = new LinkedList<>(queue);
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).getSeverity() > list.get(j + 1).getSeverity()) {
                    Patient temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        queue = new LinkedList<>(list); 
    }

    public void sortByName() {
        LinkedList<Patient> list = new LinkedList<>(queue);
        int n = list.size();
        for (int i = 1; i < n; i++) {
            Patient key = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j).getName().compareToIgnoreCase(key.getName()) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
        queue = new LinkedList<>(list); 
    }

    public void printPatients() {
        for (Patient patient : queue) {
            System.out.println(patient);
        }
    }
}

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HospitalQueue hospitalQueue = new HospitalQueue();

        System.out.println("Masukkan jumlah pasien:");
        int n = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < n; i++) {
            System.out.println("Masukkan nama pasien " + (i + 1) + ":");
            String name = scanner.nextLine();
            System.out.println("Masukkan tingkat kegawatdaruratan pasien (1=Gawat, 5=Ringan):");
            int severity = scanner.nextInt();
            scanner.nextLine(); 
            hospitalQueue.addPatient(name, severity);
        }

        System.out.println("\nPilih metode pengurutan:");
        System.out.println("1. Urutkan berdasarkan tingkat kegawatdaruratan (Bubble Sort)");
        System.out.println("2. Urutkan berdasarkan nama pasien (Insertion Sort)");
        int choice = scanner.nextInt();

        System.out.println("\nDaftar pasien sebelum pengurutan:");
        hospitalQueue.printPatients();

        switch (choice) {
            case 1:
                hospitalQueue.sortBySeverity();
                System.out.println("\nDaftar pasien setelah diurutkan berdasarkan tingkat kegawatdaruratan:");
                break;
            case 2:
                hospitalQueue.sortByName();
                System.out.println("\nDaftar pasien setelah diurutkan berdasarkan nama:");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                return;
        }

        hospitalQueue.printPatients();
        scanner.close();
    }
}
