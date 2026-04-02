import java.io.File;
import java.util.Scanner;

public class FileOrganizer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Java File Organizer ===");
        System.out.print("Enter the folder path to organize: ");
        String folderPath = scanner.nextLine().trim();

        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Error: Invalid folder path!");
            return;
        }

        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("Folder is empty.");
            return;
        }

        for (File file : files) {
            if (file.isFile()) {
                String extension = "";
                int i = file.getName().lastIndexOf('.');
                if (i > 0) extension = file.getName().substring(i+1);

                File newFolder = new File(folder, extension.isEmpty() ? "others" : extension);
                if (!newFolder.exists()) newFolder.mkdirs();

                boolean success = file.renameTo(new File(newFolder, file.getName()));
                if (success) {
                    System.out.println("Moved: " + file.getName() + " → " + newFolder.getName());
                } else {
                    System.out.println("Failed to move: " + file.getName());
                }
            }
        }

        System.out.println("✅ Files organized successfully!");
    }
}
