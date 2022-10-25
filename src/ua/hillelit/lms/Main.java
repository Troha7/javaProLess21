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

    System.out.println(">>> Typ [Book] Prise [>250]\n" + stream1(products));

    System.out.println(">>> Typ [Book] Discount [10%]\n" + stream2(products));

    System.out.println(">>> Typ [Book] MinPrise\n" + stream3(products));

    System.out.println(">>> 3 last CreateDate\n" + stream4(products));

    System.out.println(">>> Typ [Book] Prise [<=75] CreateDate [2022]\n"
        + "Sum Prise = " + stream5(products));

    System.out.println(">>> Map [Typ][ProductList]\n" + stream6(products));

  }

  private static Map<String, List<Product>> stream6(List<Product> products) {
    return products.stream()
        .map(Product::getTyp)
        .distinct()
        .collect(Collectors.toMap(kay -> kay, val -> valFilter(products, val)));
  }

  private static List<Product> valFilter(List<Product> products, String val) {
    return products.stream().filter(prod -> prod.getTyp().equals(val)).collect(
        Collectors.toList());
  }

  private static Integer stream5(List<Product> products) {
    List<Product> productList = products.stream()
        .filter(prod -> prod.getTyp().equals("Book"))
        .filter(prod -> prod.getPrise() <= 75)
        .filter(prod -> prod.getCreateDate().getYear() == 2022)
        .collect(Collectors.toList());
    return productList.stream()
        .mapToInt(Product::getPrise).sum();
  }

  private static List<Product> stream4(List<Product> products) {
    return products.stream()
        .sorted(Comparator.comparing(Product::getCreateDate, Comparator.reverseOrder()))
        .limit(3)
        .collect(Collectors.toList());
  }

  private static Product stream3(List<Product> products) {
    List<Product> productList = valFilter(products, "Book");
    return productList.stream()
        .min(Comparator.comparing(Product::getPrise))
        .orElseThrow(NoSuchElementException::new);
  }

  private static List<Product> stream2(List<Product> products) {
    return products.stream()
        .filter(prod -> prod.getTyp().equals("Book"))
        .filter(Product::isDiscount)
        .peek(prod -> prod.setPrise(prod.getPrise() - (prod.getPrise()) / 10))
        .collect(Collectors.toList());
  }

  private static List<Product> stream1(List<Product> products) {
    return products.stream()
        .filter(prod -> prod.getTyp().equals("Book"))
        .filter(prod -> prod.getPrise() > 250)
        .collect(Collectors.toList());
  }

}