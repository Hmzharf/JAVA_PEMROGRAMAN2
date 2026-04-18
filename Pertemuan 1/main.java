import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input data
        System.out.print("Enter your NIM: ");
        String nim = scanner.nextLine();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your UTS score: ");
        double uts = scanner.nextDouble();
        System.out.print("Enter your UAS score: ");
        double uas = scanner.nextDouble();

        // Process data
        double finalGrade = (uts + uas) / 2;
        String grade = (finalGrade >= 80) ? "A" : (finalGrade >= 65) ? "B" : "C";

        // Output data
        System.out.println("==== Student Data ====");
        System.out.println("NIM: " + nim);
        System.out.println("Name: " + name);
        System.out.println("UTS Score: " + uts);
        System.out.println("UAS Score: " + uas);
        System.out.println("Final Grade: " + finalGrade);
        System.out.println("Grade: " + grade);

        // Close scanner
        scanner.close();
    }
}