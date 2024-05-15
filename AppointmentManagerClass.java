import java.io.*;
import java.util.ArrayList;
import java.util.List;

class AppointmentManager {
    private static final String FILE_PATH = "appointments.csv";

    void createAppointment(Appointment appointment) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(appointment.toString());
            writer.newLine();
        }
    }

    List<Appointment> checkAppointments() throws IOException {
        List<Appointment> appointments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                appointments.add(new Appointment(parts[0], parts[1], parts[2], parts[3]));
            }
        }
        return appointments;
    }

    void removeAppointment(String date, String doctor) throws IOException {
        File inputFile = new File(FILE_PATH);
        File tempFile = new File("appointments_temp.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (!parts[1].equals(date) || !parts[2].equals(doctor)) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }

        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            throw new IOException("Could not remove appointment");
        }
    }
}
