package controller;

import service.ComparatorPrice;
import service.ProductManager;

import javax.jws.soap.SOAPBinding;
import java.util.Scanner;

public class Menu {
    public Menu() {
        ProductManager productManager = new ProductManager();
        ComparatorPrice comparatorPrice = new ComparatorPrice();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("----CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM----");
            System.out.println("1.Xem danh sách");
            System.out.println("2.Thêm mới");
            System.out.println("3.Cập nhật");
            System.out.println("4.Xoá");
            System.out.println("5.Sắp xếp");
            System.out.println("6.Tìm sản phẩm giá cao nhất");
            System.out.println("7.Đọc từ file");
            System.out.println("8.Ghi vào file");
            System.out.println("9.Thoát");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    productManager.displayProduct();
                    break;
                case 2:
                    productManager.addProduct();
                    break;
                case 3:
                    productManager.editProduct();
                    break;
                case 4:
                    productManager.deleteProduct();
                    break;
                case 5:
                    productManager.sortProduct(comparatorPrice);
                    break;
                case 6:
                    productManager.findMaxPrice(comparatorPrice);
                    break;
                case 7:
                    productManager.importData();
                    break;
                case 8:
                    productManager.exportData();
                    break;
                case 9:
                    System.exit(0);
            }
        } while (true);
    }
}
