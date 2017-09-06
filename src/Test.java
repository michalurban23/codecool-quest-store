import models.usr.*;

public class Test {

    public static void main(String[] args) {

        User student1 = new Student("Jan", "Kowalski", "qwe", "zxc");
        User student2 = new Student();
        User student2 = new Student("Stefan", "Batory");

        Mentor mentor1 = new Mentor("Jaromir", "Jagr", "dupa1", "dupa2");
        Mentor mentor2 = new Mentor();
        Mentor mentor3 = new Mentor("George", "Michael", "ohohoho", "uahhaha");

        System.out.println(student1);
        System.out.println(student1.getFullName());
    }
}
