package ua.hillelit.lms;

import java.time.LocalDate;
import java.util.Objects;

public class Product {

  private final String typ;
  private Integer prise;

  private final boolean discount;
  private final LocalDate createDate;

  public Product(String typ, Integer prise, boolean discount, LocalDate createDate) {
    this.typ = typ;
    this.prise = prise;
    this.discount = discount;
    this.createDate = createDate;
  }

  @Override
  public String toString() {
    return "{" +
        "typ='" + typ + '\'' +
        ", prise=" + prise +
        ", discount=" + discount +
        ", createDate=" + createDate +
        '}';
  }

  public String getTyp() {
    return typ;
  }

  public Integer getPrise() {
    return prise;
  }

  public boolean isDiscount() {
    return discount;
  }

  public LocalDate getCreateDate() {
    return createDate;
  }

  public void setPrise(Integer prise) {
    this.prise = prise;
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
    return Objects.equals(prise, product.prise);
  }

  @Override
  public int hashCode() {
    int result = typ != null ? typ.hashCode() : 0;
    result = 31 * result + (prise != null ? prise.hashCode() : 0);
    result = 31 * result + (discount ? 1 : 0);
    return result;
  }
}
