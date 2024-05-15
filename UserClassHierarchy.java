abstract class User {
    String name;

    User(String name) {
        this.name = name;
    }

    abstract void showMenu();
}

class Doctor extends User {
    Doctor(String name) {
        super(name);
    }

    @Override
    void showMenu() {
        System.out.println("1. Create Appointment");
        System.out.println("2. Check Appointments");
        System.out.println("3. Remove Appointment");
    }
}

class Nurse extends User {
    Nurse(String name) {
        super(name);
    }

    @Override
    void showMenu() {
        System.out.println("1. Create Appointment");
        System.out.println("2. Check Appointments");
    }
}

class Patient extends User {
    Patient(String name) {
        super(name);
    }

    @Override
    void showMenu() {
        System.out.println("1. Check Appointments");
    }
}
