package pl.java.scalatech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
  Optional<Product> findByName(String name);

}
