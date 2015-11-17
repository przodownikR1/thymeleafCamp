package pl.java.scalatech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

    Optional<Invoice> findByName(String name);
}
