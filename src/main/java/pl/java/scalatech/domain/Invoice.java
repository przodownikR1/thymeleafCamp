package pl.java.scalatech.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString(callSuper=true,exclude="products")
public class Invoice extends AbstractEntity{


   /* @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    private User issuingAnInvoice;
    @OneToOne
    private User recipientOfInvoice;
    */
    private String name;
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="invoiceId")
    private List<Product> products;
    private boolean paid;
    @Column(name="date_of_invoice",nullable=false)
    private Date dateOfInvoice;
    @Enumerated(EnumType.STRING)
    @Column(name="invoice_type")
    private InvoiceType invoiceType;

}
