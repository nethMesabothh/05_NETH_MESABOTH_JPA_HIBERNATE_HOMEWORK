package com.example.springhomework.dto.request;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIRequestProduct {

  @NotBlank(message = "Name is required")
  private String name;

  @NotNull(message = "Price is required")
  @DecimalMin(value = "0.0", inclusive = true, message = "must be greater than or equal 0")
  private Double price;

  @NotNull(message = "Quantity is required")
  @Min(value = 0, message = "must be greater than or equal 0")
  private Integer quantity;
}
