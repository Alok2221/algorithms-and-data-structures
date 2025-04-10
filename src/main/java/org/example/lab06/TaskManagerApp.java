package org.example.lab06;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManagerApp {
    private static final TaskManager TASK_MANAGER = new TaskManager();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = getMenuChoice();

            switch (choice) {
                case 1:
                    addNewTask();
                    break;
                case 2:
                    removeTask();
                    break;
                case 3:
                    saveTasksToFile();
                    running = false;
                    break;
                case 4:
                    completeTaskByIndex();
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n############################");
        System.out.println("Zadania do realizacji:");

        if (TASK_MANAGER.isEmpty()) {
            System.out.println("\u001B[32mBrak zadań do wyświetlenia.\u001B[0m");
        } else {
            System.out.print(TASK_MANAGER);
        }

        System.out.println("\nWybierz opcję:");
        System.out.println("1. Dodaj zadanie");
        System.out.println("2. Usuń zadanie");
        System.out.println("3. Zakończ i zapisz do pliku");
        System.out.println("4. Zakończ zadanie (po numerze)");
    }

    private static void completeTaskByIndex() {
        if (TASK_MANAGER.isEmpty()) {
            System.out.println("Brak zadań do oznaczenia jako zakończone.");
            return;
        }

        System.out.print("Podaj numer zadania do zakończenia: ");
        int index = SCANNER.nextInt();
        SCANNER.nextLine();

        List<Task> tasks = new ArrayList<>();
        for (Task task : TASK_MANAGER.getTaskTQueue()) {
            tasks.add(task);
        }

        if (index < 1 || index > tasks.size()) {
            System.out.println("Nieprawidłowy numer zadania.");
            return;
        }

        Task taskToComplete = tasks.get(index - 1);
        taskToComplete.setCompleted(true);
        System.out.println("Zadanie '" + taskToComplete.getName() + "' zostało oznaczone jako zakończone.");
    }

    private static int getMenuChoice() {
        System.out.print("Twój wybór: ");
        while (!SCANNER.hasNextInt()) {
            System.out.println("Proszę wprowadzić liczbę 1-3.");
            SCANNER.next();
        }
        return SCANNER.nextInt();
    }

    private static void addNewTask() {
        SCANNER.nextLine();
        System.out.print("Podaj nowe zadanie: ");
        String taskName = SCANNER.nextLine();

        System.out.print("Podaj opis zadania (opcjonalne): ");
        String description = SCANNER.nextLine();

        TASK_MANAGER.addTask(new Task(taskName, description));
        System.out.println("Dodano zadanie: " + taskName);
    }

    private static void removeTask() {
        if (TASK_MANAGER.isEmpty()) {
            System.out.println("Brak zadań do usunięcia.");
            return;
        }

        Task removedTask = TASK_MANAGER.deleteTask();
        System.out.println("Usunięto zadanie: " + removedTask.getName());
    }

    private static void saveTasksToFile() {
        if (TASK_MANAGER.isEmpty()) {
            System.out.println("Brak zadań do zapisania.");
            return;
        }

        String resourcesDir = "src/main/resources/";
        File directory = new File(resourcesDir);

        if (!directory.exists()) {
            boolean dirCreated = directory.mkdirs();
            if (!dirCreated) {
                System.out.println("Nie udało się utworzyć folderu resources.");
                return;
            }
        }

        String filePath = resourcesDir + "tasks.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            int counter = 1;
            for (Task task : TASK_MANAGER.getTaskTQueue()) {
                String[] taskLines = task.toString().split("\n");
                writer.write(counter++ + ". " + taskLines[0].trim());
                writer.newLine();

                for (int i = 1; i < taskLines.length; i++) {
                    writer.write("   " + taskLines[i].trim());
                    writer.newLine();
                }
                writer.newLine();
            }
            System.out.println("Zadania zostały zapisane do pliku: " + filePath);
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas zapisywania do pliku: " + e.getMessage());
        }
    }
}
