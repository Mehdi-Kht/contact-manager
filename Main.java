import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_NAME = "contacts.csv";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Contact Manager ===");
            System.out.println("1. Ajouter un contact");
            System.out.println("2. Afficher les contacts");
            System.out.println("3. Rechercher un contact");
            System.out.println("4. Quitter");
            System.out.print("Choix : ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    ajouterContact();
                    break;
                case "2":
                    afficherContacts();
                    break;
                case "3":
                    rechercherContact();
                    break;
                case "4":
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private static void ajouterContact() {
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Téléphone : ");
        String telephone = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();

        Contact contact = new Contact(prenom, nom, telephone, email);

        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(contact.toCSV());
            bw.newLine();
            System.out.println("Contact ajouté !");
        } catch (IOException e) {
            System.out.println("Erreur lors de l'enregistrement du contact.");
        }
    }

    private static void afficherContacts() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- Liste des contacts ---");
            while ((line = br.readLine()) != null) {
                Contact c = Contact.fromCSV(line);
                if (c != null) {
                    System.out.println(c);
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier.");
        }
    }

    private static void rechercherContact() {
        System.out.print("Nom à rechercher : ");
        String nomRecherche = scanner.nextLine().toLowerCase();

        boolean trouve = false;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                Contact c = Contact.fromCSV(line);
                if (c != null && c.toString().toLowerCase().contains(nomRecherche)) {
                    System.out.println(c);
                    trouve = true;
                }
            }
            if (!trouve) {
                System.out.println("Aucun contact trouvé.");
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la recherche.");
        }
    }
}