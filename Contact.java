public class Contact {
    private String firstName, lastName, phone, email;

    public Contact(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public String toCSV() {
        return firstName + "," + lastName + "," + phone + "," + email;
    }

    public static Contact fromCSV(String csv) {
        String[] parts = csv.split(",");
        if (parts.length == 4) {
            return new Contact(parts[0], parts[1], parts[2], parts[3]);
        }
        return null;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " | " + phone + " | " + email;
    }
}