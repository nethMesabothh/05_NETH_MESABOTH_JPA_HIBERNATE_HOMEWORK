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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @Operation(summary = "Get product by ID", description = "Fetches a product using its unique ID. Returns 404 if not found.")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Product found"),
          @ApiResponse(responseCode = "404", description = "Product not found")
  })
  @GetMapping("{id}")
  public ResponseEntity<APIResponseProduct<Product>> getProductById(@PathVariable Long id) {
    return productService.getProductById(id);
  }

  @Operation(summary = "Update product by ID", description = "Updates an existing product with the given ID using the request body. Returns the updated product.")
  @PutMapping("{id}")
  public ResponseEntity<APIResponseProduct<Product>> updateProductById(@PathVariable Long id, @Valid @RequestBody APIRequestProduct request) {
    return productService.updateProductById(request, id);
  }

  @Operation(summary = "Delete product by ID", description = "Deletes a product by its ID. Returns HTTP 200 if the product is successfully deleted.")
  @DeleteMapping("{id}")
  public ResponseEntity<APIResponseProduct<Product>> deleteProductById(@PathVariable Long id) {
    return productService.deleteProductById(id);
  }

  @Operation(summary = "Get all products (paginated)", description = "Returns a paginated list of all products. Accepts page and size as query parameters.")
  @GetMapping()
  public ResponseEntity<APIResponseProduct<PagedResponse<List<Product>>>> getAllProducts(
          @RequestParam(defaultValue = "1") int page,
          @RequestParam(defaultValue = "10") int size) {
    return productService.getAllProducts(page, size);
  }

  @Operation(summary = "Create a new product", description = "Accepts a product request payload and creates a new product. Returns the created product.")
  @PostMapping()
  public ResponseEntity<APIResponseProduct<Product>> createProduct(@Valid @RequestBody APIRequestProduct request) {
    return productService.createProduct(request);
  }

  @Operation(summary = "Search products by name", description = "Returns a list of products that contain the given name (case-insensitive partial match).")
  @GetMapping("/search")
  public ResponseEntity<APIResponseProduct<List<Product>>> searchByName(@RequestParam String name) {
    return productService.searchByName(name);
  }

  @Operation(summary = "Get low stock products", description = "Returns a list of products with quantity less than the specified threshold.")
  @GetMapping("/low-stock")
  public ResponseEntity<APIResponseProduct<List<Product>>> searchByQuantity(@RequestParam int quantity) {
    return productService.searchByQuantityLessThan(quantity);
  }
}
