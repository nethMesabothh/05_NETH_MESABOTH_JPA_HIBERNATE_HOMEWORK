package com.example.springhomework.controller;

import com.example.springhomework.dto.request.APIRequestProduct;
import com.example.springhomework.dto.response.APIResponseProduct;
import com.example.springhomework.dto.response.PagedResponse;
import com.example.springhomework.model.Product;
import com.example.springhomework.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping()
  public ResponseEntity<APIResponseProduct<PagedResponse<List<Product>>>> getAllProducts(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
    return productService.getAllProducts(page, size);
  }

  @PostMapping()
  public ResponseEntity<APIResponseProduct<Product>> createProduct(@Valid @RequestBody APIRequestProduct request) {
    return productService.createProduct(request);
  }

  @GetMapping("{id}")
  public ResponseEntity<APIResponseProduct<Product>> getProductById(@PathVariable Long id) {
    return productService.getProductById(id);
  }

  @PutMapping("{id}")
  public ResponseEntity<APIResponseProduct<Product>> updateProductById(@PathVariable Long id, @Valid @RequestBody APIRequestProduct request) {
    return productService.updateProductById(request, id);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<APIResponseProduct<Product>> deleteProductById(@PathVariable Long id) {
    return productService.deleteProductById(id);
  }

  @GetMapping("/search")
  public ResponseEntity<APIResponseProduct<List<Product>>> searchByName(@RequestParam String name) {
    return productService.searchByName(name);
  }

  @GetMapping("/low-stock")
  public ResponseEntity<APIResponseProduct<List<Product>>> searchByQuantity(@RequestParam int quantity) {
    return productService.searchByQuantityLessThan(quantity);
  }

}
