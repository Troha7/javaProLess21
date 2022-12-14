package ua.hillelit.lms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Main {

    private static final String BOOK = "Book";
    private static final String TOY = "Toy";

    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();

        products.add(new Product(BOOK, 260, false, LocalDate.of(2021, 8, 15)));
        products.add(new Product(BOOK, 300, true, LocalDate.of(2019, 10, 11)));
        products.add(new Product(BOOK, 100, true, LocalDate.of(2020, 12, 14)));
        products.add(new Product(BOOK, 70, false, LocalDate.of(2022, 4, 13)));
        products.add(new Product(BOOK, 50, false, LocalDate.of(2022, 9, 1)));

        products.add(new Product(TOY, 73, false, LocalDate.of(2022, 10, 12)));
        products.add(new Product(TOY, 10, false, LocalDate.of(2022, 7, 10)));

        System.out.println(">>> Typ [Book] Price [>250]\n" + stream1(products));

        System.out.println(">>> Typ [Book] Discount [10%]\n" + stream2(products));

        System.out.println(">>> Typ [Book] MinPrice\n" + stream3(products));

        System.out.println(">>> 3 last CreateDate\n" + stream4(products));

        System.out.println(">>> Typ [Book] Price [<=75] CreateDate [2022]\n"
                + "Sum Price = " + stream5(products));

        System.out.println(">>> Map [Typ][ProductList]\n" + stream6(products));

    }

    /**
     * @param products {@code List<Product>}
     * @return Map kay = productTyp, value = List<Product>.
     */
    private static Map<String, List<Product>> stream6(List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getTyp));
    }

    /**
     * @param products {@code List<Product>}
     *                 <p>If this list contains {@code BOOK}, {@code Price <= 75} and product added in this year.
     * @return filtered and changed {@code List<Product>}.
     */
    private static Integer stream5(List<Product> products) {
        return products.stream()
                .filter(prod -> prod.getTyp().equals(BOOK) && prod.getPrice() <= 75 && prod.getCreateDate().getYear() == 2022)
                .mapToInt(Product::getPrice).sum();
    }

    /**
     * @param products {@code List<Product>}
     * @return 3 last added products {@code List<Product>}.
     */
    private static List<Product> stream4(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparing(Product::getCreateDate, Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toList());
    }

    /**
     * @param products {@code List<Product>}
     * @return the cheapest {@code Product} with type {@code BOOK}.
     * @throws NoSuchElementException if product typ not found.
     */
    private static Product stream3(List<Product> products) {
        return products.stream()
                .filter(prod -> prod.getTyp().equals(BOOK))
                .min(Comparator.comparing(Product::getPrice))
                .orElseThrow(() -> new NoSuchElementException("Product [" + BOOK + "] not found"));
    }

    /**
     * @param products {@code List<Product>}
     *                 <p>If this list contains {@code BOOK} and {@code isDiscount == true}, then apply discount 10%.
     * @return filtered and changed {@code List<Product>}.
     */
    private static List<Product> stream2(List<Product> products) {
        return products.stream()
                .filter(prod -> prod.getTyp().equals(BOOK) && prod.isDiscount())
                .peek(prod -> prod.setPrice(prod.getPrice() - (prod.getPrice()) / 10))
                .collect(Collectors.toList());
    }

    /**
     * @param products {@code List<Product>}
     *                 <p>If this list contains {@code BOOK} and {@code Price > 250}.
     * @return filtered and changed {@code List<Product>}.
     */
    private static List<Product> stream1(List<Product> products) {
        return products.stream()
                .filter(prod -> prod.getTyp().equals(BOOK) && prod.getPrice() > 250)
                .collect(Collectors.toList());
    }

}
