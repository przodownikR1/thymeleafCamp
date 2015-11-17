package pl.java.scalatech.service.invoice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.java.scalatech.repository.InvoiceRepository;
import pl.java.scalatech.service.invoice.InvoiceService;
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InvoiceServiceImpl implements InvoiceService{

    private final @NonNull InvoiceRepository invoiceRepository;
}
