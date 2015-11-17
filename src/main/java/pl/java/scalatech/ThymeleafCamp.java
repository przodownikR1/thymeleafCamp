package pl.java.scalatech;

import static com.google.common.collect.Lists.newArrayList;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.h2.server.web.WebServlet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.domain.Company;
import pl.java.scalatech.domain.Invoice;
import pl.java.scalatech.domain.InvoiceType;
import pl.java.scalatech.domain.Product;
import pl.java.scalatech.domain.Role;
import pl.java.scalatech.domain.User;
import pl.java.scalatech.repository.CompanyRepository;
import pl.java.scalatech.repository.InvoiceRepository;
import pl.java.scalatech.repository.ProductRepository;
import pl.java.scalatech.repository.RoleRepository;
import pl.java.scalatech.repository.UserRepository;

@SpringBootApplication
@Slf4j
public class ThymeleafCamp {

    List<Role> roles = newArrayList(Role.builder().role("USER").build(), Role.builder().role("ADMIN").build(), Role.builder().role("BUSINESS").build());
    List<Product> products = newArrayList(Product.builder().availability(true).name("hammer").price(BigDecimal.TEN).build(),
            Product.builder().availability(false).name("spoon").price(BigDecimal.valueOf(34, 2)).build(),
            Product.builder().availability(false).name("gun").price(BigDecimal.valueOf(123)).build(),
            Product.builder().availability(true).name("fork").price(BigDecimal.valueOf(23)).build(),
            Product.builder().availability(true).name("car").price(BigDecimal.valueOf(12345)).build());
    List<Company> company = Lists.newArrayList(Company.builder().name("Samsung").location("korea").build(),
            Company.builder().name("Everlast").location("usa").build(), Company.builder().name("yamaha").location("japan").build());
    final Random r = new Random();

    @Bean
    CommandLineRunner initCompany(CompanyRepository companyRepository) {
        return (evt) -> company.forEach(a -> {
            Company loaded = companyRepository.save(a);
            log.info("{}", loaded);
        });
    }

    @Bean
    CommandLineRunner initProduct(ProductRepository productRepository) {
        return (evt) -> products.forEach(a -> {
            Product loaded = productRepository.save(a);
            log.info("{}", loaded);
        });
    }

    @Bean
    CommandLineRunner initRole(RoleRepository roleRepository) {
        return (evt) -> roles.forEach(a -> {
            Role loaded = roleRepository.save(a);
            log.info("++++++ roles !!!{}", loaded);
        });
    }

    @Bean
    CommandLineRunner initInvoice(InvoiceRepository invoiceRepository, ProductRepository productRepository) {
        return (evt) -> Lists.newArrayList(

        Invoice.builder().dateOfInvoice(new Date()).invoiceType(InvoiceType.VAT).name("tools").paid(false)
                .products(newArrayList(productRepository.findByName("hammer").get())).build(),
                Invoice.builder().dateOfInvoice(new Date()).invoiceType(InvoiceType.VAT).name("grocery").paid(false)
                        .products(newArrayList(productRepository.findByName("spoon").get(), productRepository.findByName("gun").get())).build(),
                Invoice.builder().dateOfInvoice(new Date()).invoiceType(InvoiceType.VAT).name("shopping").paid(true)
                        .products(newArrayList(productRepository.findByName("spoon").get(), productRepository.findByName("fork").get())).build(),
                Invoice.builder().dateOfInvoice(new Date()).invoiceType(InvoiceType.VAT).name("petrolStation").paid(true)
                        .products(newArrayList(productRepository.findByName("car").get())).build())
                .forEach(a -> {
                    Invoice loaded = invoiceRepository.save(a);
                    log.info("{}", loaded);
                });
    }

    @Bean
    CommandLineRunner initUser(UserRepository userRepository, CompanyRepository companyRepository, RoleRepository roleRepository,
            InvoiceRepository invoiceRepository) {
        return (evt) -> Lists.newArrayList(User.builder().company(companyRepository.findOne(1l)).email("przodownik@tlen.pl").firstname("slawek")
                .lastName("borowiec").login("przodownik").password("123").ownInvoice(Lists.newArrayList(invoiceRepository.findByName("grocery").get())).build(),

        User.builder().company(companyRepository.findOne(2l)).email("borowiecA@tlen.pl").firstname("agnieszka").lastName("borowiec").login("poka")
                .password("123").ownInvoice(Lists.newArrayList(invoiceRepository.findByName("grocery").get())).build(),

        User.builder().company(companyRepository.findOne(3l)).email("bbak@tlen.pl").firstname("kalina").lastName("borowiec").login("bak").password("123")

        .ownInvoice(Lists.newArrayList(invoiceRepository.findByName("petrolStation").get(),
                invoiceRepository.findByName("tools").get())).build(),

        User.builder().company(companyRepository.findOne(2l)).email("pkowalski@google.com").firstname("piotr").lastName("kowalski").login("kowalski")
                .password("123")
                .ownInvoice(Lists.newArrayList(invoiceRepository.findByName("shopping").get()))
                .build()).forEach(a -> {
                    Role role = roleRepository.findByRole("USER").get();
                    log.info("++++!  role {}",role);
                    User loaded = userRepository.save(a);//TODO !??
                    loaded.setRoles(newArrayList(role));
                    loaded = userRepository.save(loaded);

                    log.info("{}", loaded);
                });
    }

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        registration.addInitParameter("webAllowOthers", "true");
        return registration;
    }

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafCamp.class, args);
    }
}
