package evilNerd.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class Cars {

   private Long id;
   private String model;
   private Integer creationYear;
   private Long userId;
   private Float price;
   private String color;

    public Cars(Long id, String model, Integer creationYear, Long userId, Float price, String color) {
        this.id = id;
        this.model = model;
        this.creationYear = creationYear;
        this.userId = userId;
        this.price = price;
        this.color = color;
    }

    public Cars() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cars)) return false;
        Cars cars = (Cars) o;
        return Objects.equals(id, cars.id) &&
                Objects.equals(model, cars.model) &&
                Objects.equals(creationYear, cars.creationYear) &&
                Objects.equals(userId, cars.userId) &&
                Objects.equals(price, cars.price) &&
                Objects.equals(color, cars.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, creationYear, userId, price, color);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(Integer creationYear) {
        this.creationYear = creationYear;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
