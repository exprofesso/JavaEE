package evilNerd.domain;

import lombok.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;


@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data


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
