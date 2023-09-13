package service;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductManager {
    private ArrayList<Product> products;
    private final Scanner scanner;

    public ProductManager() {
        products = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    public void addProduct() {
        System.out.println("Nhập tên sản phẩm:");
        String name = scanner.nextLine();
        System.out.println("Nhập giá sản phẩm:");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Nhập số lượng sản phẩm:");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập mô tả sản phẩm:");
        String description = scanner.nextLine();
        Product product = new Product(name, price, quantity, description);
        products.add(product);
        System.err.println("Đã thêm sản phẩm!");
    }
    public void editProduct() {
        boolean check = true;
        int id;
        do {
            System.out.println("Nhập id của sản phẩm muốn sửa:");
            id = ExceptionManager.exceptionPositiveInteger();
            if (id < 1 || id > products.size()) {
                check = false;
                System.err.println("Không tìm thấy sản phẩm!");
            }
        } while (!check);
        Product product = findProductById(id);
        System.out.println("Nhập tên mới của sản phẩm:");
        String newName = scanner.nextLine();
        System.out.println("Nhập giá mới của sản phẩm:");
        double newPrice = Double.parseDouble(scanner.nextLine());
        System.out.println("Nhập số lượng mới sản phẩm:");
        int newQuantity = ExceptionManager.exceptionPositiveInteger();
        System.out.println("Nhập mô tả mới của sản phẩm:");
        String newDescription = scanner.nextLine();
        product.setName(newName);
        product.setPrice(newPrice);
        product.setQuantity(newQuantity);
        product.setDescription(newDescription);
        System.err.println("Đã sửa sản phẩm!");
    }
    public Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    public void deleteProduct() {
        boolean check = true;
        int id = 0;
        do {
            System.out.println("Nhập id của sản phẩm muốn xoá:");
            id = ExceptionManager.exceptionPositiveInteger();
            if (id < 1 || id > products.size()) {
                check = false;
                System.err.println("Không tìm thấy sản phẩm!");
            }
        } while (!check);
        Product product = findProductById(id);
        System.out.println("Nhập Y để xoá sản phẩm:");
        String select = scanner.nextLine();
        if (select.equals("Y")) {
            products.remove(product);
            System.err.println("Đã xoá sản phẩm!");
        }
    }
    public void sortProduct(ComparatorPrice comparatorPrice) {
        products.sort(comparatorPrice);
    }
    public void findMaxPrice(ComparatorPrice comparatorPrice) {
        if (products.isEmpty()) {
            System.err.println("Danh sách sản phẩm trống!");
        } else {
            sortProduct(comparatorPrice);
            System.out.println(products.get(products.size()-1));
        }
    }
    public void exportData() {
        try {
            File fileProducts = new File("src\\data\\products.csv");
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileProducts));
            for (Product p : products) {
                bw.write(p.getId() + "," + p.getName() + "," + p.getPrice() + "," + p.getQuantity()+ ","  + p.getDescription() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            String message = e.getMessage();
            System.out.println(message);
        }
    }
    public void importData() {
        ArrayList<String[]> listData = new ArrayList<>();
        try {
            File file = new File("src\\data\\products.csv");
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                listData.add(line.split(","));
            }
        } catch (IOException e) {
            String message = e.getMessage();
            System.out.println(message);
        }
        for (int i = 0; i < listData.size(); i++) {
            int id = Integer.parseInt(listData.get(i)[0]);
            if (Product.getIndex() < id) {
                Product.setIndex(id);
            }
            String name = listData.get(i)[1];
            double price = Double.parseDouble(listData.get(i)[2]);
            int quantity = Integer.parseInt(listData.get(i)[3]);
            String description = listData.get(i)[4];
            Product product = new Product(id, name, price, quantity, description);
            products.add(product);
        }
    }
    public void displayProduct() {
        if (products.isEmpty()) {
            System.out.println("Danh sách trống!");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }
}
