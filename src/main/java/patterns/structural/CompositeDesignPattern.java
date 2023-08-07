package patterns.structural;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CompositeDesignPattern {

    /**
     * Composite Design Pattern using File Structure as an example
     * Please run main method to see the result.
     */

    public static void main(String[] args) {
        final Object rootFolder = getRootFolder();
        printHierarchy(rootFolder);
    }

    public static Object getRootFolder() {

        final Folder rootFolder = new Folder("Root Folder", 0);

        final Document document1 = new Document("Document-1", 1);
        rootFolder.add(document1);

        final Folder folder1 = new Folder("Folder-1", 1);
        folder1.add(new Document("Document-1-1", 2));
        folder1.add(new Document("Document-1-2", 2));

        rootFolder.add(folder1);

        final Folder folder2 = new Folder("Folder-2", 1);

        rootFolder.add(folder2);

        return rootFolder;
    }

    public static void printHierarchy(Object object) {
        if (object instanceof Folder) {
            Folder folder = (Folder) object;
            printName(folder);
            folder.getDocumentComponents()
                    .forEach((d) -> printHierarchy(d));
        } else {
            Document document = (Document) object;
            printName(document);
        }
    }

    public static void printName(DocumentComponent documentComponent) {
        IntStream.rangeClosed(0, documentComponent.getDepth())
                .mapToObj(a -> " ")
                .forEach(System.err::print);
        System.err.println("|-" + documentComponent.getName());
    }

    public static abstract class DocumentComponent {
        private final String name;
        private final int depth;

        protected DocumentComponent(String name, int depth) {
            this.name = name;
            this.depth = depth;
        }

        public String getName() {
            return name;
        }

        public int getDepth() {
            return depth;
        }
    }

    public static class Folder extends DocumentComponent {

        private List<DocumentComponent> documentComponents = new ArrayList<>();

        protected Folder(String name, int depth) {
            super(name, depth);
        }

        public void add(DocumentComponent documentComponent) {
            documentComponents.add(documentComponent);
        }

        public List<DocumentComponent> getDocumentComponents() {
            return documentComponents;
        }
    }

    public static class Document extends DocumentComponent {

        protected Document(String name, int depth) {
            super(name, depth);
        }
    }
}
