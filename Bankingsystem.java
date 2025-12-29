import java.io.*;
import java.util.Scanner;

class Account {
    int balance = 3000;

    public synchronized void withdraw(int amount, String atmName) {
        if (balance >= amount) {
            int temp = balance;
            temp -= amount;

            try {
                Thread.sleep(1000); 
            } catch (Exception e) {
                System.out.println(e);
            }

            balance = temp;
            log(atmName + " withdraw Rs." + amount + ", Remaining: Rs." + balance);
        } else {
            log(atmName + " failed to withdraw Rs." + amount + " (Insufficient Balance)");
        }
    }

    public void log(String msg) {
        try {
            FileWriter fw = new FileWriter("C:\\Users\\admin\\Documents\\log.txt", true);
            fw.write("**** TRANSACTION RECEIPT ****\n");
            fw.write(msg);
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Logging error");
        }
    }

    public void save() {
        try {
            FileWriter fw = new FileWriter("C:\\Users\\admin\\Documents\\account.txt");
            fw.write("Final Balance: Rs." + balance + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Save error");
        }
    }
}

class ATM extends Thread {
    Account acc;
    int amount;

    ATM(Account acc, int amount, String name) {
        super(name);
        this.acc = acc;
        this.amount = amount;
    }

    public void run() {
        acc.withdraw(amount, getName());
    }
}

public class Bankingsystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the withdrawal amount in ATM-1: ");
        int wd1 = sc.nextInt();

        System.out.print("Enter the withdrawal amount in ATM-2: ");
        int wd2 = sc.nextInt();

        Account acc = new Account();

        ATM atm1 = new ATM(acc, wd1, "ATM-1");
        ATM atm2 = new ATM(acc, wd2, "ATM-2");

        atm1.start();
        atm2.start();

        try {
            atm1.join();
            atm2.join();
        } catch (Exception e) {
            System.out.println("Thread error");
        }



        System.out.println("All ATM operations done.");
    }
}