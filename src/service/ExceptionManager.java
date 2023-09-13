package service;

import java.util.Scanner;

public class ExceptionManager {
    private static final Scanner sc = new Scanner(System.in) ;

    public static int exceptionQuantity() {
        int quantity = -1;
        do {
            System.out.println("Nhập số lượng sản phẩm");
            try {
                quantity = Integer.parseInt(sc.nextLine());
                if (quantity <= 0) {
                    quantity = -1;
                    System.out.println("Số lượng phải lớn hơn 0!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Chỉ được nhập số nguyên!");
            }

        } while (quantity == -1);
        return quantity;
    }
    public static double exceptionPrice() {
        double price = -1.0;
        do {
            System.out.println("Nhập giá sản phẩm: ");
            try {
                price = Double.parseDouble(sc.nextLine());
                if (price <= 0) {
                    price = -1;
                    System.err.println("Giá phải lớn hơn 0!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Chỉ được nhập số!");
            }

        } while (price == -1);
        return price;
    }
    public static int exceptionPositiveInteger() {
        int number = -1;
        do {
            try {
                number = Integer.parseInt(sc.nextLine());
                if (number <= 0) {
                    number = -1;
                    System.err.println("Nhập số nhỏ hơn 0!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Nhập sai định dạng!");
            }
        } while (number == -1);
        return number;
    }
    public static int exceptionChoice() {
        int choice = -1;
        do {
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice < 0) {
                    choice = -1;
                    System.err.println("Nhập số lớn hơn 0 mới được!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Nhập sai định dạng!");
            }
        } while (choice == -1);
        return choice;
    }
}
