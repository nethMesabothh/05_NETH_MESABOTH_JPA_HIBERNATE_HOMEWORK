package com.example.springhomework.service.impl;

import com.example.springhomework.dto.request.APIRequestProduct;
import com.example.springhomework.dto.response.APIResponseProduct;
import com.example.springhomework.dto.response.PagedResponse;
import com.example.springhomework.exception.NotFoundException;
import com.example.springhomework.model.Product;
import com.example.springhomework.repository.ProductRepository;
import com.example.springhomework.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  public ResponseEntity<APIResponseProduct<Product>> createProduct(@RequestBody APIRequestProduct request) {
    Product product = productRepository.createProduct(request);

    APIResponseProduct<Product> response = new APIResponseProduct<>("Product created successfully", product, HttpStatus.CREATED, LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  public ResponseEntity<APIResponseProduct<PagedResponse<List<Product>>>> getAllProducts(int page, int size) {
    PagedResponse<List<Product>> products = productRepository.getAllProducts(page, size);

    APIResponseProduct<PagedResponse<List<Product>>> response = new APIResponseProduct<>("Fetch product successfully", products, HttpStatus.FOUND, LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.FOUND).body(response);
  }

  public ResponseEntity<APIResponseProduct<Product>> getProductById(@RequestParam Long id) {
    Product product = productRepository.getProductById(id);

    if (product == null) {
      throw new NotFoundException("Product id " + id + " not found");
    }

    APIResponseProduct<Product> response = new APIResponseProduct<>("Fetch product successfully", product, HttpStatus.FOUND, LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.FOUND).body(response);
  }

  @Override
  public ResponseEntity<APIResponseProduct<Product>> updateProductById(APIRequestProduct request, Long id) {

    Product product = productRepository.updateProductById(request, id);
    if (product == null) {
      throw new NotFoundException("Product id " + id + " not found");
    }


    APIResponseProduct<Product> response = new APIResponseProduct<>("Update product successfully", product, HttpStatus.OK, LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Override
  public ResponseEntity<APIResponseProduct<Product>> deleteProductById(Long id) {

    Product product = productRepository.deleteProductById(id);
    if (product == null) {
      throw new NotFoundException("Product id " + id + " not found");
    }


    APIResponseProduct<Product> response = new APIResponseProduct<>("Deleted product successfully", product, HttpStatus.OK, LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Override
  public ResponseEntity<APIResponseProduct<List<Product>>> searchByName(String name) {
    List<Product> products = productRepository.searchByName(name);

    if (products.isEmpty()) {
      throw new NotFoundException("Product not found");
    }

    APIResponseProduct<List<Product>> response = new APIResponseProduct<>(
            "Products found successfully",
            products,
            HttpStatus.OK,
            LocalDateTime.now()
    );

    return ResponseEntity.ok(response);
  }

  @Override
  public ResponseEntity<APIResponseProduct<List<Product>>> searchByQuantityLessThan(int quantity) {
    List<Product> products = productRepository.searchByQuantityLessThan(quantity);

    if (products.isEmpty()) {
      throw new NotFoundException("No product is less than " + quantity);
    }

    APIResponseProduct<List<Product>> response = new APIResponseProduct<>(
            "Products with quantity less than " + quantity,
            products,
            HttpStatus.OK,
            LocalDateTime.now()
    );

    return ResponseEntity.ok(response);
  }
}
