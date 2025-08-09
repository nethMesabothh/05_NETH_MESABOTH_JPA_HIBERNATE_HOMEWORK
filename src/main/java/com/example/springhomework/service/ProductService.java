package com.example.springhomework.service;


import com.example.springhomework.dto.request.APIRequestProduct;
import com.example.springhomework.dto.response.APIResponseProduct;
import com.example.springhomework.dto.response.PagedResponse;
import com.example.springhomework.model.Product;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductService {
  ResponseEntity<APIResponseProduct<Product>> createProduct(@RequestBody APIRequestProduct request);

  ResponseEntity<APIResponseProduct<PagedResponse<List<Product>>>> getAllProducts(int page, int size);

  ResponseEntity<APIResponseProduct<Product>> getProductById(@RequestParam Long id);

  ResponseEntity<APIResponseProduct<Product>> updateProductById(@Valid APIRequestProduct request, Long id);

  ResponseEntity<APIResponseProduct<Product>> deleteProductById(Long id);

  ResponseEntity<APIResponseProduct<List<Product>>> searchByName(String name);

  ResponseEntity<APIResponseProduct<List<Product>>> searchByQuantityLessThan(int quantity);
}
