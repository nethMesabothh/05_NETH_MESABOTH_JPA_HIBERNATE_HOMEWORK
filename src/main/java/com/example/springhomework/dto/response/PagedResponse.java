package com.example.springhomework.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagedResponse<T> {
  private T items;
  private Pagination pagination;
}
