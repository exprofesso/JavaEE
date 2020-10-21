package evilNerd.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;


@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class Cars {

   private Long id;

   private String model;

   private Integer creationYear;

   private Long userId;

   private Float price;

   private String color;

   public String toString(){
      return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
   }



}
