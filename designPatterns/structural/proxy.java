/**
 * This pattern is used when we need a proxy for an object to do it's work because operations on
 *  the real object are expensive.
 * Operations on the real object are carried out only when absolutely necessary.
 * There are many use-cases for this pattern such as caching, image/video operations, lazy
 * initialization etc.
 * In this example, we are creating a cache in memory of a table which is stored on a remote server.
 * All operations are carried out on the proxy table.
 * The real table is updated only when update method is called in this example.
 */


//  Here we have used the caching example

interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }

    private void loadFromDisk() {
        System.out.println("Loading image from disk: " + filename);
    }
}

class ImageProxy implements Image {
    private RealImage realImage;
    private String filename;

    public ImageProxy(String filename) {
        this.filename = filename;
        this.realImage = null;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

public class ImageDemo {
    public static void main(String[] args) {
        // Create an ImageProxy for a high-resolution image.
        Image proxy = new ImageProxy("high_res_image.jpg");

        // Display the image (loading it on-demand).
        proxy.display();

        // The image is not loaded again if we display it multiple times.
        proxy.display();
    }
}