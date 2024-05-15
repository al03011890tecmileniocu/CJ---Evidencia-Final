import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static AppointmentManager appointmentManager = new AppointmentManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your role (doctor/nurse/patient): ");
        String role = scanner.nextLine();
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        User user;
        switch (role.toLowerCase()) {
            case "doctor":
                user = new Doctor(name);
                break;
            case "nurse":
                user = new Nurse(name);
                break;
            case "patient":
                user = new Patient(name);
                break;
            default:
                System.out.println("Invalid role");
                return;
        }

        while (true) {
            user.showMenu();
            System.out.println("Choose an option: ");
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    if (user instanceof Doctor || user instanceof Nurse) {
                        createAppointment(scanner);
                    } else if (user instanceof Patient) {
                        checkAppointments();
                    } else {
                        System.out.println("Invalid option");
                    }
                    break;
                case 2:
                    checkAppointments();
                    break;
                case 3:
                    if (user instanceof Doctor) {
                        removeAppointment(scanner);
                    } else {
                        System.out.println("Invalid option");
                    }
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void createAppointment(Scanner scanner) {
        try {
            System.out.println("Enter reason: ");
            String reason = scanner.nextLine();
            System.out.println("Enter date (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            System.out.println("Enter doctor name: ");
            String doctor = scanner.nextLine();
            System.out.println("Enter patient name: ");
            String patient = scanner.nextLine();

            Appointment appointment = new Appointment(reason, date, doctor, patient);
            appointmentManager.createAppointment(appointment);
            System.out.println("Appointment created.");
        } catch (IOException e) {
            System.out.println("Error creating appointment: " + e.getMessage());
        }
    }

    private static void checkAppointments() {
        try {
            List<Appointment> appointments = appointmentManager.checkAppointments();
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        } catch (IOException e) {
            System.out.println("Error checking appointments: " + e.getMessage());
        }
    }

    private static void removeAppointment(Scanner scanner) {
        try {
            System.out.println("Enter date (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            System.out.println("Enter doctor name: ");
            String doctor = scanner.nextLine();

            appointmentManager.removeAppointment(date, doctor);
            System.out.println("Appointment removed.");
        } catch (IOException e) {
            System.out.println("Error removing appointment: " + e.getMessage());
        }
    }
}
