package com.example.springhomework.repository;

import com.example.springhomework.dto.request.APIRequestProduct;
import com.example.springhomework.dto.response.PagedResponse;
import com.example.springhomework.dto.response.Pagination;
import com.example.springhomework.model.Product;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class ProductRepository {

  @PersistenceContext
  private EntityManager em;

  public Product createProduct(@RequestBody APIRequestProduct request) {
    Product product = Product.builder()
            .name(request.getName())
            .price(request.getPrice())
            .quantity(request.getQuantity())
            .build();

    em.persist(product);

    return product;
  }

  public PagedResponse<List<Product>> getAllProducts(int page, int size) {
    int offset = (page - 1) * size;

    List<Product> items = em.createQuery("select p from Product p", Product.class).setFirstResult(offset).setMaxResults(size).getResultList();

    Long total = em.createQuery("select count(p) from Product p", Long.class).getSingleResult();

    int totalPages = (int) Math.ceil((double) total / size);

    Pagination pagination = new Pagination(total, totalPages, page, size);


    return new PagedResponse<>(items, pagination);
  }

  public Product getProductById(Long id) {
    return em.find(Product.class, id);
  }

  public Product updateProductById(@Valid APIRequestProduct request, Long id) {
    Product product = em.find(Product.class, id);

    if (em.contains(product)) {
      em.detach(product);
      product.setName(request.getName());
      product.setPrice(request.getPrice());
      product.setQuantity(request.getQuantity());
      return em.merge(product);
    }
    return null;
  }

  public Product deleteProductById(Long id) {
    Product product = em.find(Product.class, id);
    if (em.contains(product)) {
      em.remove(product);
      return product;
    }
    return null;
  }

  public List<Product> searchByName(String name) {
    return em.createQuery("""
                        SELECT p FROM Product p
                        WHERE LOWER(p.name) LIKE LOWER(:name)
                    """, Product.class)
            .setParameter("name", "%" + name + "%")
            .getResultList();
  }

  public List<Product> searchByQuantityLessThan(int quantity) {
    return em.createQuery("""
                        SELECT p FROM Product p
                        WHERE p.quantity < :quantity
                    """, Product.class)
            .setParameter("quantity", quantity)
            .getResultList();
  }
}
