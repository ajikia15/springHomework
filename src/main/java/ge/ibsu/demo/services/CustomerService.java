package ge.ibsu.demo.services;

import ge.ibsu.demo.dto.*;
import ge.ibsu.demo.entities.Address;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.repositories.CustomerRepository;
import ge.ibsu.demo.util.GeneralUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressService addressService;
    @Autowired
    public CustomerService(CustomerRepository customerRepository, AddressService addressService) {
        this.customerRepository = customerRepository;
        this.addressService = addressService;
    }
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
    public Customer getById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CUSTOMER_NOT_FOUND"));
    }
    @Transactional
    public Customer addEditCustomer(AddCustomer addCustomer, Long id) throws Exception {
        Customer customer = new Customer();
        if (id != null) {
            customer = getById(id);
        }

        GeneralUtil.getCopyOf(addCustomer, customer);
        if (id == null) {
            customer.setCreateDate(new Date());
        }

        Address address = addressService.getAddress(addCustomer.getAddress());
        customer.setAddress(address);

        return customerRepository.save(customer);
    }

    @Transactional
    public Boolean deleteCustomer(Long id) {
        Customer customer = getById(id);
        customerRepository.delete(customer);
        return true;
    }

    public Page<CustomerInfo> search(SearchCustomer searchCustomer, Paging paging) {
        String searchText = searchCustomer.getSearchText() != null ? "%" + searchCustomer.getSearchText() + "%" : "";
        Pageable pager = PageRequest.of(paging.getPage() - 1, paging.getSize(), Sort.by("id").descending());
        return customerRepository.searchInfo(searchCustomer.getActive(), searchText, pager);
    }

    @Transactional
    public void setActiveStatusToCustomer(Long id, Integer status) {
        customerRepository.setActiveStatusForCustomer(id, status);
    }

    public List<CustomerFullName> getActiveCustomers() {
        return customerRepository.findAllByActive(1, CustomerFullName.class);
    }
}
