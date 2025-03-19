package az;

import az.Connection.DBConnection;
import az.Entities.Achievement;
import az.Entities.Contact;
import az.Entities.Projects;
import az.Entities.Skills;
import az.Service.AchievementsProcess;
import az.Service.ContactProcess;
import az.Service.ProjectsProcess;
import az.Service.SkillsProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scan = new Scanner(System.in);
    private static AchievementsProcess process = new AchievementsProcess();
    private static ProjectsProcess process1 = new ProjectsProcess();
    private static SkillsProcess process2 = new SkillsProcess();
    private static ContactProcess contact = new ContactProcess();

    public static void main(String[] args) {
//                  DBConnection.getConnect();
        while (true) {
            System.out.println("\tMenu");
            System.out.println("1-Achievement\n2-Projects\n3-Skills\n4-Contact\n5-Exit System");
            System.out.println("Select the operation you want: ");

            int choice = scan.nextInt();
            DBConnection.clearconsole();
            switch (choice) {
                case 1:
                    System.out.println("1-Add Achievements\n2-Get Achievements by ID\n3- Uptade Achievements\n" +
                            "4- Delete Achievements\n5- Find Achievements by letters");
                    System.out.println("Select the operation you want: ");
                    int choice1 = scan.nextInt();
                    switch (choice1) {
                        case 1 -> addAchievements();
                        case 2 -> process.getID();
                        case 3 -> uptadeAchievements();
                        case 4 -> process.delete();
                        case 5 -> process.findbyname();
                    }
                    return;
                case 2:
                    System.out.println("1-Add Projects\n2-Get Project by ID\n3- Uptade projects\n" +
                            "4- Delete Projects\n5- Find Projects by letters");
                    System.out.println("Select the operation you want: ");
                    int choice2 = scan.nextInt();
                    switch (choice2) {
                        case 1 -> addProjects();
                        case 2-> process1.getID();
                        case 3-> uptadeProjects();
                        case 4-> process1.delete();
                        case 5-> process1.findbyname();
                    }
                    return;
                case 3:
                    System.out.println("1-Add Skills\n2-Get Skills by ID\n3- Uptade Skills\n" +
                            "4- Delete Skills\n5- Find Skills by letters");
                    System.out.println("Select the operation you want: ");
                    int choice3 = scan.nextInt();
                    switch (choice3) {
                        case 1-> addSkills();
                        case 2-> process2.getID();
                        case 3-> uptadeSkills();
                        case 4-> process2.delete();
                        case 5-> process2.findbyname();
                    }
                    return;
                case 4:
                    System.out.println("1-Add Contact\n2-Get Conact by ID\n3- Uptade Conact\n" +
                            "4- Delete Contact\n5- Find Contact by letters");
                    System.out.println("Select the operation you want: ");
                    int choice4 = scan.nextInt();
                    switch (choice4) {
                        case 1->addContact();
                        case 2-> contact.getID();
                        case 3->uptadeContact();
                        case 4-> contact.delete();
                        case 5->contact.findbyname();
                    }
                    return;
                case 5:
                    System.out.println("Exiting System...");
                    return;
                default:
                    System.out.println("False choice, try again please");
            }
        }
    }

//                                  ***Add,Uptade processing***
    public static void addAchievements() {
        List<Achievement> list = new ArrayList<>();
        scan.nextLine();
        System.out.print("Enter Achievements title:");
        String title = scan.nextLine();

        System.out.print("Enter description:");
        String description = scan.nextLine();

        Achievement achievement = new Achievement(title, description);
        list.add(achievement);
        process.add(list);
    }

    public static void addProjects() {
        List<Projects> list = new ArrayList<>();
        scan.nextLine();
        System.out.print("Enter Projects name:");
        String name = scan.nextLine();

        System.out.print("Enter title:");
        String title = scan.nextLine();

        Projects projects = new Projects(name, title);
        list.add(projects);
        process1.add(list);
    }

    public static void uptadeAchievements() {
        System.out.print("Enter new ID:");
        int id = scan.nextInt();

        scan.nextLine();
        System.out.println("Enter new Achievements title:");
        String title = scan.nextLine();


        System.out.println("Enter new description:");
        String description = scan.nextLine();

        Achievement achievement = new Achievement(id, title, description);
        process.uptade(achievement);
    }

    public static void uptadeProjects(){
        System.out.print("Enter new ID:");
        int id = scan.nextInt();
        scan.nextLine();

        System.out.print("Enter new Project name:");
        String title = scan.nextLine();

        System.out.print("Enter new title:");
        String description = scan.nextLine();
        scan.nextLine();

        Projects projects = new Projects(id,title,description);
        process1.uptade(projects);
    }

    public static void addSkills(){
        List<Skills> list = new ArrayList<>();
        scan.nextLine();

        System.out.println("Enter Skills name:");
        String name = scan.nextLine();

        Skills skills = new Skills(name);
        list.add(skills);
        process2.add(list);
    }

    public static void uptadeSkills(){
        System.out.println("Enter Id: ");
        int id = scan.nextInt();

        scan.nextLine();
        System.out.println("Enter new Skills name:");
        String name = scan.nextLine();

        Skills skills = new Skills(id,name);
        process2.uptade(skills);
    }

    public static void addContact(){
        List<Contact> list = new ArrayList<>();
        System.out.println("Enter Contact name:");
        String name = scan.next();
        System.out.println("Enter Contact email: ");
        String email = scan.next();

        scan.nextLine();
        System.out.println("Enter Contact message: ");
        String message = scan.nextLine();

        Contact contact1 = new Contact(name,email,message);
        list.add(contact1);
        contact.add(list);
    }

    public static void uptadeContact(){
        System.out.println("Enter Contact ID:");
        int id = scan.nextInt();
        System.out.println("Enter new Contact name:");
        String name = scan.next();
        System.out.println("Enter new Contact email: ");
        String email = scan.next();

        scan.nextLine();
        System.out.println("Enter new Contact message: ");
        String message = scan.nextLine();

        Contact contact1 = new Contact(id,name,email,message);
        contact.uptade(contact1);
    }
}