import java.util.*;

public class Vaccination {

    private static List<Child> children = new ArrayList<>();
    private static Map<Child, List<String>> vaccinationSchedule = new HashMap<>();
    private static Map<Child, List<String>> vaccinationReminders = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Child Vaccination Management System");

        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Register Child");
            System.out.println("2. Schedule Vaccination Appointment");
            System.out.println("3. View Vaccination Schedule");
            System.out.println("4. View Reminders");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerChild(scanner);
                    break;
                case 2:
                    scheduleVaccination(scanner);
                    break;
                case 3:
                    viewVaccinationSchedule(scanner);
                    break;
                case 4:
                    viewReminders(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void registerChild(Scanner scanner) {
        System.out.print("Enter child's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter child's age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Child child = new Child(name, age);
        children.add(child);
        vaccinationSchedule.put(child, new ArrayList<>());
        vaccinationReminders.put(child, new ArrayList<>());

        System.out.println("Child registered successfully!");
    }

    private static void scheduleVaccination(Scanner scanner) {
        Child child = selectChild(scanner);
        if (child == null) return;

        System.out.print("Enter the vaccination name: ");
        String vaccine = scanner.nextLine();
        System.out.print("Enter the appointment date (dd-mm-yyyy): ");
        String date = scanner.nextLine();

        vaccinationSchedule.get(child).add(vaccine + " on " + date);
        vaccinationReminders.get(child).add(vaccine + " on " + date);

        System.out.println("Vaccination appointment scheduled successfully!");
    }

    private static void viewVaccinationSchedule(Scanner scanner) {
        Child child = selectChild(scanner);
        if (child == null) return;

        List<String> schedule = vaccinationSchedule.get(child);
        if (schedule.isEmpty()) {
            System.out.println("No vaccination appointments scheduled for " + child.getName());
        } else {
            System.out.println("Vaccination schedule for " + child.getName() + ":");
            for (String appointment : schedule) {
                System.out.println(appointment);
            }
        }
    }

    private static void viewReminders(Scanner scanner) {
        if (children.isEmpty()) {
            System.out.println("No children registered. Please register a child first.");
            return;
        }

        System.out.println("Reminders for all children:");
        for (Child child : children) {
            List<String> reminders = vaccinationReminders.get(child);
            if (!reminders.isEmpty()) {
                System.out.println("Reminders for " + child.getName() + ":");
                for (String reminder : reminders) {
                    System.out.println(reminder);
                }
            } else {
                System.out.println("No reminders available for " + child.getName());
            }
        }
    }

    private static Child selectChild(Scanner scanner) {
        if (children.isEmpty()) {
            System.out.println("No children registered. Please register a child first.");
            return null;
        }

        while (true) {
            System.out.println("Select a child:");
            for (int i = 0; i < children.size(); i++) {
                System.out.println((i + 1) + ". " + children.get(i).getName());
            }

            int childIndex = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline

            if (childIndex >= 0 && childIndex < children.size()) {
                return children.get(childIndex);
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
    }
}

class Child {
    private String name;
    private int age;

    public Child(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
