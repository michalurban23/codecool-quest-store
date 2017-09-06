import java.util.UUID;
import models.usr.*;

public class Test {

    public static void main(String[] args) {

        User student1 = new Student("Jan", "Kowalski", "qwe", "zxc");
        User student2 = new Student();
        User student3 = new Student("Stefan", "Batory");

        User mentor1 = new Mentor("Jaromir", "Jagr", "dupa1", "dupa2");
        User mentor2 = new Mentor();
        User mentor3 = new Mentor("George", "Michael", "ohohoho", "uahhaha");

        User admin1 = new Admin("Kazimierz", "Wielki", "drewno", "cegła", UUID.randomUUID().toString());

        System.out.println(student1);
        System.out.println(student1.getFullName());
        System.out.println(" ");

        System.out.println(student2 + "\n");
        System.out.println(student3 + "\n");
        System.out.println(mentor1 + "\n");
        System.out.println(mentor2 + "\n");
        System.out.println(mentor3 + "\n");
        System.out.println(admin1 + "\n");
    }
}
