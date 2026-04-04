package org.example;

import org.entity.UserEntity;
import org.services.UserService;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ConsoleInterface {
    private static UserService userService = new UserService();

    private static void create(){
        String name, email;
        int age;
        LocalDateTime date = LocalDateTime.now();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name: ");
        name = scan.nextLine();
        System.out.println("Enter email: ");
        email = scan.nextLine();
        System.out.println("Enter age: ");
        age = scan.nextInt();

        UserEntity user = new UserEntity(name, email, age, date);
        userService.createUser(user);
    }

    private static void update() {
        int id, age;
        String name, email;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter id of line that you wanna update: ");
        id = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter name: ");
        name = scan.nextLine();
        System.out.println("Enter email: ");
        email = scan.nextLine();
        System.out.println("Enter age: ");
        age = scan.nextInt();
        LocalDateTime date = userService.getDateById(id);

        UserEntity user = new UserEntity(id, name, email, age, date);
        userService.updateUser(user);
    }

    private static void delete(){
        int id;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter id of line that you wanna update: ");
        id = scan.nextInt();
        userService.deleteUser(id);
    }

    public static void main(String[] args) throws SQLException {
        String command = "";

        while (!command.equals("end")) {
            System.out.println("Enter command(create/read/update/delete/end): ");
            Scanner in = new Scanner(System.in);
            command = in.nextLine();
            if (command.equals("create")) {
                create();
            } else if (command.equals("read")) {
                userService.printAll();
            } else if (command.equals("update")) {
                update();
            } else if (command.equals("delete")) {
                delete();
            }
        }
    }
}
