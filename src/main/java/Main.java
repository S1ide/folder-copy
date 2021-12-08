import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for(;;){
            System.out.println("Введите абсолютный путь папки, откуда хотите скопировать:\n");
            String inPath = scanner.nextLine();
            System.out.println("Введите абсолютный путь папки, куда хотите скопировать:\n");
            String outPath = scanner.nextLine();
            FileUtils.copyFolder(inPath, outPath);
        }
    }
}
