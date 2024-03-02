import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
public class RBTreeMeniu {
    public static void main(String[] args) {
        RBTree rbTree = new RBTree();
        Scanner scanner = new Scanner(System.in);
        creeazaFisier();
        Eveniment eveniment = new Eveniment("Eveniment de adunare a gunoiului", "12:00", "CorneliuCoposu", "Curatenie generala");
        citesteSiInsereazaDinFisier(rbTree);
        int choice;
        do {
            System.out.println("----- Meniu -----");
            System.out.println("1. Adăugare participant");
            System.out.println("2. Afișare arbore");
            System.out.println("3. Cautare participant");
            System.out.println("4. Ștergere participant");
            System.out.println("5. Afisare eveniment");
            System.out.println("6. Exit");
            System.out.print("Alegeți o opțiune: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consumăm newline-ul
            switch (choice) {
                case 1:
                    // Adăugare participant
                    System.out.print("Introduceți numele participantului: ");
                    String nume = scanner.nextLine();
                    System.out.print("Introduceți numărul de telefon: ");
                    String telefon = scanner.nextLine();
                    System.out.print("Introduceți vârsta: ");
                    int varsta = scanner.nextInt();
                    scanner.nextLine();  // Consumăm newline-ul

                    Participant participant = new Participant(nume, telefon, varsta);
                    RBNode node = new RBNode(participant);
                    rbTree.insertNode(node);
                    eveniment.arboreParticipanti=rbTree;
                    salveazaInFisierInsert(nume, telefon, varsta);
                    System.out.println("Participant adăugat cu succes!");
                    break;

                case 2:
                    // Afișare arbore
                    System.out.println("----- Arborele Red-Black -----");
                    rbTree.displayTree();
                    System.out.println("-----------------------------");
                    break;

                case 3:
                    // Căutare participant
                    System.out.print("Introduceți numele participantului de căutat: ");
                    String numeCautat = scanner.nextLine();
                    RBNode resultNode = rbTree.search(numeCautat);
                    if (resultNode != null && resultNode != rbTree.getNullNode() && resultNode.getData() != null) {
                        System.out.println("Participantul cu numele '" + numeCautat + "' a fost găsit: " + resultNode.getData());
                    } else {
                        System.out.println("Participantul cu numele '" + numeCautat + "' nu a fost găsit.");
                    }
                    break;
                case 4:
                    // Ștergere participant
                    System.out.print("Introduceți numele participantului de șters: ");
                    String numeSters = scanner.nextLine();
                    RBNode nodeSters = rbTree.search(numeSters);
                    if (nodeSters != null && nodeSters != rbTree.getNullNode() && nodeSters.getData() != null) {
                        rbTree.del(nodeSters);
                        salveazaFisierDelete(numeSters);
                        System.out.println("Participantul cu numele '" + numeSters + "' a fost șters.");
                    } else {
                        System.out.println("Participantul cu numele '" + numeSters + "' nu a fost găsit.");
                    }
                    eveniment.arboreParticipanti = rbTree;
                    break;

                case 5:
                    // Afisare eveniment
                    System.out.println("----- Eveniment -----");
                    System.out.println(eveniment);
                    System.out.println("---------------------");
                    break;
                case 6:
                    // Exit
                    System.out.println("La revedere!");
                    break;
                default:
                    System.out.println("Opțiune invalidă. Încercați din nou.");
                    break;
            }

        } while (choice != 6);
    }
    private static void salveazaInFisierInsert(String nume, String telefon, int varsta) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("participants.txt", true))) {
            fileWriter.write(nume + ", " + telefon + ", " + varsta);
            fileWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void citesteSiInsereazaDinFisier(RBTree rbTree) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader("participants.txt"))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                // Parsare linie și adăugare participant în arbore
                String[] parts = line.split(",");
                String nume = parts[0].trim();
                String telefon = parts[1].trim();
                int varsta = Integer.parseInt(parts[2].trim());

                Participant participant = new Participant(nume, telefon, varsta);
                RBNode node = new RBNode(participant);
                rbTree.insertNode(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void salveazaFisierDelete(String numeParticipant) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader("participants.txt"));
             BufferedWriter fileWriter = new BufferedWriter(new FileWriter("temp.txt"))) {

            String line;
            while ((line = fileReader.readLine()) != null) {
                if (!line.contains(numeParticipant)) {
                    fileWriter.write(line);
                    fileWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Excepție la citire/scriere fișier: " + e.getMessage());
        }

        // Înlocuirea fișierului original cu cel temporar
        Path originalPath = Path.of("participants.txt");
        Path tempPath = Path.of("temp.txt");

        try {
            Files.move(tempPath, originalPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Eroare la ștergerea participantului din fișier: " + e.getMessage());
        }
    }


    private static void creeazaFisier() {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("participants.txt", true))) {
            // Scrie un caracter pentru a forța crearea fișierului
            fileWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}