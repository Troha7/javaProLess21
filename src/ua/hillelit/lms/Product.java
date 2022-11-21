package ua.hillelit.lms;

import java.time.LocalDate;
import java.util.Objects;

public class Product {

  private final String typ;
  private Integer price;

  private final boolean discount;
  private final LocalDate createDate;

  public Product(String typ, Integer price, boolean discount, LocalDate createDate) {
    this.typ = typ;
    this.price = price;
    this.discount = discount;
    this.createDate = createDate;
  }

  @Override
  public String toString() {
    return "{" +
        "typ='" + typ + '\'' +
        ", prise=" + price +
        ", discount=" + discount +
        ", createDate=" + createDate +
        '}';
  }

  public String getTyp() {
    return typ;
  }

  public Integer getPrice() {
    return price;
  }

  public boolean isDiscount() {
    return discount;
  }

  public LocalDate getCreateDate() {
    return createDate;
  }

  public void setPrice(Integer prise) {
    this.price = prise;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Product product = (Product) o;

    if (discount != product.discount) {
      return false;
    }
    if (!Objects.equals(typ, product.typ)) {
      return false;
    }
    return Objects.equals(price, product.price);
  }

  @Override
  public int hashCode() {
    int result = typ != null ? typ.hashCode() : 0;
    result = 31 * result + (price != null ? price.hashCode() : 0);
    result = 31 * result + (discount ? 1 : 0);
    return result;
  }
}
