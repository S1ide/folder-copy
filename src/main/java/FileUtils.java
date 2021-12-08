import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;

class FileUtils {
    private static ArrayList<Path> outPaths;
    private static ArrayList<Path> inPaths;
    private static String mainDirectory;
    private static String destDirectory;

    public static void copyFolder(String sourceDirectory, String destinationDirectory) {
        try {
            mainDirectory = sourceDirectory;
            destDirectory = destinationDirectory;
            outPaths = new ArrayList<>();
            inPaths = new ArrayList<>();
            Files.walkFileTree(Paths.get(sourceDirectory), new MyFileVisitor());
            for (int i = 0; i < inPaths.size(); i++) {
                Files.createDirectories(outPaths.get(i));
                Files.copy(inPaths.get(i), outPaths.get(i), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static Path getPath(Path path) {
        String dir = path.toString().replace(mainDirectory, destDirectory);
        return Paths.get(dir);
    }

    public static class MyFileVisitor extends SimpleFileVisitor {

        @Override
        public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
            inPaths.add((Path) file);
            outPaths.add(getPath((Path) file));
            return FileVisitResult.CONTINUE;
        }
    }
}
