import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * DirectoryManager
 */
public class DirectoryManager {
    private static class Directory {
        String name;
        Map<String, Directory> subdirectories;

        Directory(String directoryName) {
            this.name = directoryName;
            this.subdirectories = new HashMap<>();
        }
    }

    private static Directory root = new Directory("/");
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        DirectoryManager.createDirectory("fruits/apples/fiji");
        DirectoryManager.createDirectory("fruits/oranges/naval");
        DirectoryManager.createDirectory("fruits/apples/granny");
        DirectoryManager.createDirectory("veggies/carrots");
        DirectoryManager.moveDirectory("fruits/apples", "tmp");
        DirectoryManager.listDirectories();
       executeCommands();
    }

    private static void executeCommands()  {
       
        int input;

        while (true) {
            System.out.println();
            System.out.println("1. List Directories\n2. Create A Directory\n3. Move A Directory\n4. Delete A Directory\n5. Exit");
            System.out.print("Enter your choice: ");
            input =  scan.nextInt();

            switch (input) {
                case 1: 
                    listDirectories();
                    break;
                case 2: 
                    System.out.print("Enter new Directory name: ");
                    String newDirectory = scan.next();
                    createDirectory(newDirectory.trim());
                    break;
                case 3: 
                System.out.print("Enter Directory to move: ");
                String moveFromDirecotry = scan.next();
                System.out.print("Enter Directory to move into: ");
                String moveToDirecotry = scan.next();
                moveDirectory(moveFromDirecotry.trim(), moveToDirecotry.trim());
                break;
                case 4: 
                    System.out.print("Enter a Directory to  delete: ");
                    String removeDirectory = scan.next();
                    deleteDirectory(removeDirectory.trim());
                    break;
                case 5: 
                    exitMenu();
                    break;
                default:
                    invalidInput();
            }
        }
    }

    public static void invalidInput() {
        System.out.print("\nInvalid Input\n");
    }

    public static void moveDirectory(String moveFrom, String moveTo) {
        String [] dirs =  moveFrom.split("/");
        String dirToMove = dirs[dirs.length - 1];
        String [] moveIntoDirs =  moveTo.split("/");
        String dirInto = moveIntoDirs[moveIntoDirs.length - 1];
        Directory dirFrom = root;
        Directory dirTo = root;
        Boolean moved = false;
        Boolean dirFound = false;
        for(String dir : dirs) {
            if(dirFrom.subdirectories.containsKey(dirToMove)) {
                dirFrom = dirFrom.subdirectories.get(dir);
                dirFound = true;
                break;
            }
            dirFrom = dirFrom.subdirectories.get(dir);
        }
        if(dirFound) {
            createDirectory(moveTo);
            for(String dir : moveTo.split("/")) {
                if( dirTo.subdirectories.containsKey(dirInto)) {
                    dirTo = dirTo.subdirectories.get(dirInto);
                    dirTo.subdirectories.put(dirFrom.name, dirFrom);
                    moved = true;
                    break;
                }
                dirTo = dirTo.subdirectories.get(dir);
            }
        }
        if(moved == false) {
            System.out.println("Unable to find " + dirToMove + " Directory. No Directory was moved!" );
        }
        else {
            deleteDirectory(moveFrom);
            System.out.println("It was moved to under " + dirInto);
        }
    }

    public static Directory findDirectory(Directory current, String path) {
       for(String dir : path.split("/")) {
            current = current.subdirectories.get(dir);
            if(current == null) {
                return null;
            }
       } 
       return current;
    }

    public static void deleteDirectory(String path) {
        String [] dirs =  path.split("/");
        String dirToDelete = dirs[dirs.length - 1];
        Boolean deleted = false;
        Directory current = root;
        for(String dir : dirs) {
            if(current.subdirectories.containsKey(dirToDelete)) {
                current.subdirectories.remove(dirToDelete);
                deleted = true;
                System.out.println("The Directory " + dirToDelete + " was deleted");
                break;
            }
            current = current.subdirectories.get(dir);
        }
        if(deleted ==  false) {
            System.out.println("The Directory " + dirToDelete + " was not found!");
        }
    }

    public static void createDirectory(String path) {
        Directory current = root;
        for(String dir : path.split("/")) {
            current.subdirectories.putIfAbsent(dir, new Directory(dir));
            current = current.subdirectories.get(dir);
        }

    }

    public static void listDirectories() {
        listDirectories(root, 0);
    }

    public static void listDirectories(Directory current, int depth) {
        for (String dir : current.subdirectories.keySet()) {
            System.out.println("  ".repeat(depth) + dir + "/");
            listDirectories(current.subdirectories.get(dir), depth + 1);
        }
    }


    private static void exitMenu() {
        System.out.println("Exiting the Directory Manager Program.");
        System.exit(0);
    }
    
}