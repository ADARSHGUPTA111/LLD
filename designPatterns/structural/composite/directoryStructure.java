/**
 * Composite pattern is used in cases where we have a tree-like hierarchy of similar elements.
 * In this example, there are multiple instances of folders and files in a tree-like directory.
 * So we implement them from same interface and same methods are available to them.
 * Composite elements are elements with children, while leaf elements have no children.
 * We store a link of children/parent element in each element so that we are able to navigate through the tree.
 */

import java.util.*;

// Abstraction Layer
interface FileSystemComponent {
    void listContents();
    int getSize();
}

// Concrete Implementation: File (leaf)
class File implements FileSystemComponent {
    private String name;
    private int size;

    public File(String fileName, int fileSize) {
        this.name = fileName;
        this.size = fileSize;
    }

    @Override
    public void listContents() {
        System.out.println("File: " + name);
    }

    @Override
    public int getSize() {
        return size;
    }
}

// Concrete Implementation: Directory
class Directory implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> contents;

    public Directory(String dirName) {
        this.name = dirName;
        this.contents = new ArrayList<>();
    }

    public void addComponent(FileSystemComponent component) {
        contents.add(component);
    }

    @Override
    public void listContents() {
        System.out.println("Directory: " + name);
        for (FileSystemComponent component : contents) {
            component.listContents();
        }
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent component : contents) {
            totalSize += component.getSize();
        }
        return totalSize;
    }
}

public class directorystructure {
    public static void main(String[] args) {
        Directory root = new Directory("Root");

        FileSystemComponent file1 = new File("Document.txt", 100);
        FileSystemComponent file2 = new File("Image.jpg", 200);

        Directory subDir = new Directory("Subdirectory");
        FileSystemComponent file3 = new File("Data.csv", 150);

        subDir.addComponent(file3);

        root.addComponent(file1);
        root.addComponent(file2);
        root.addComponent(subDir);

        // List contents and calculate total size for the directory structure
        root.listContents();
        int totalSize = root.getSize();
        System.out.println("Total Size: " + totalSize + " KB");

        // Clean up memory (consider using automatic memory management in a real application)
        // No need to explicitly delete objects in Java
    }
}