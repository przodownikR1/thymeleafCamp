package pl.java.scalatech.repo;

import static com.google.common.collect.Lists.newArrayList;

import java.math.BigDecimal;
import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.config.TestJpaSelectorConfig;
import pl.java.scalatech.domain.Invoice;
import pl.java.scalatech.domain.InvoiceType;
import pl.java.scalatech.domain.Product;
import pl.java.scalatech.repository.InvoiceRepository;
import pl.java.scalatech.repository.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestJpaSelectorConfig.class)
@Slf4j
@Transactional
public class RepoTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    Product spoon, gun;

    @Before
    public void init() {
        spoon = Product.builder().availability(false).name("spoon").price(BigDecimal.valueOf(34, 2)).build();
        gun = Product.builder().availability(false).name("gun").price(BigDecimal.valueOf(123)).build();
    }

    @Test
    public void shouldRepoWork() {
        Assertions.assertThat(productRepository).isNotNull();
        Assertions.assertThat(invoiceRepository).isNotNull();
        productRepository.save(spoon);
        productRepository.save(gun);
        Invoice invoice = Invoice.builder().dateOfInvoice(new Date()).invoiceType(InvoiceType.VAT).name("grocery").paid(false)
                .products(newArrayList(productRepository.findByName("spoon").get(), productRepository.findByName("gun").get())).build();
        Invoice loaded = invoiceRepository.save(invoice);
        Invoice result = invoiceRepository.findOne(loaded.getId());
        log.info(" {}",result.getProducts());

    }

}
