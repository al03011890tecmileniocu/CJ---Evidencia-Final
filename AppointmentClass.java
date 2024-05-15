class Appointment {
    String reason;
    String date;
    String doctor;
    String patient;

    Appointment(String reason, String date, String doctor, String patient) {
        this.reason = reason;
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
    }

    @Override
    public String toString() {
        return reason + "," + date + "," + doctor + "," + patient;
    }
}
