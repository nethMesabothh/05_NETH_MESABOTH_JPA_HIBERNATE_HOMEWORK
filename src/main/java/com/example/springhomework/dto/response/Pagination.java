package com.example.springhomework.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pagination {
  private long totalElements;
  private int currentPage;
  private int pageSize;
  private int totalPages;
}
