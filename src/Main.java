
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Login\n2. Add user\n3. Exit");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                System.out.println("Enter password:");
                String password = scanner.nextLine();

                if (bank.isAdmin(username, password)) {
                    System.out.println("Logged in as admin.");
                    for (User user : bank.users) {
                        System.out.println("Username: " + user.username + ", Balance: " + user.balance);
                    }
                } else {
                    User user = bank.getUser(username);
                    if (user != null && user.password.equals(password)) {
                        System.out.println("Logged in as " + username + ".");
                        System.out.println("1. Deposit\n2. Withdraw\n3. Transfer\n4. Check balance");
                        int userOption = scanner.nextInt();
                        scanner.nextLine();

                        if (userOption == 1) {
                            System.out.println("Enter amount to deposit:");
                            double amount = scanner.nextDouble();
                            scanner.nextLine();
                            user.balance += amount;
                            System.out.println("Deposit successful. New balance: " + user.balance);
                        } else if (userOption == 2) {
                            System.out.println("Enter amount to withdraw:");
                            double amount = scanner.nextDouble();
                            scanner.nextLine();
                            if (user.balance >= amount) {
                                user.balance -= amount;
                                System.out.println("Withdrawal successful. New balance: " + user.balance);
                            } else {
                                System.out.println("Insufficient balance.");
                            }
                        } else if (userOption == 3) {
                            System.out.println("Enter username to transfer to:");
                            String toUsername = scanner.nextLine();
                            User toUser = bank.getUser(toUsername);
                            if (toUser != null) {
                                System.out.println("Enter amount to transfer:");
                                double amount = scanner.nextDouble();
                                scanner.nextLine();
                                if (user.balance >= amount) {
                                    user.balance -= amount;
                                    toUser.balance += amount;
                                    System.out.println("Transfer successful. New balance: " + user.balance);
                                } else {
                                    System.out.println("Insufficient balance.");
                                }
                            } else {
                                System.out.println("User not found.");
                            }
                        } else if (userOption == 4) {
                            System.out.println("Your balance: " + user.balance);
                        }
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                }
            } else if (option == 2) {
                System.out.println("Enter new username:");
                String username = scanner.nextLine();
                System.out.println("Enter new password:");
                String password = scanner.nextLine();
                bank.addUser(new User(username, password));
                System.out.println("User added successfully.");
            } else if (option == 3) {
                break;
            }
        }

        scanner.close();
    }
}
