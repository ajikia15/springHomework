package ge.ibsu.demo.controllers;

import ge.ibsu.demo.dto.*;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.services.CustomerService;
import ge.ibsu.demo.util.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@PreAuthorize("hasRole('CUSTOMER_ADMIN')")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //@PreAuthorize("hasAuthority('customer:read')")
    @RequestMapping(value = "/all", method = RequestMethod.GET,produces = {"application/json"})
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    //@PreAuthorize("hasAuthority('customer:read')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = {"application/json"})
    public Customer getById(@PathVariable Long id) {
        return customerService.getById(id);
    }

    //@PreAuthorize("hasAuthority('customer:add')")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"})
    public Customer add(@RequestBody AddCustomer addCustomer) throws Exception {
        GeneralUtil.checkRequiredProperties(addCustomer, Arrays.asList("firstName", "lastName", "address"));

        return customerService.addEditCustomer(addCustomer, null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/json"})
    public Boolean delete(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }

    //@PreAuthorize("hasAuthority('customer:add')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    public Customer edit(@RequestBody AddCustomer addCustomer, @PathVariable Long id) throws Exception {
        GeneralUtil.checkRequiredProperties(addCustomer, Arrays.asList("firstName", "lastName", "address"));
        return customerService.addEditCustomer(addCustomer, id);
    }

    //@PreAuthorize("hasAuthority('customer:read')")
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = {"application/json"})
    public Page<CustomerInfo> search(@RequestBody RequestData<SearchCustomer> rd) throws Exception {
        GeneralUtil.checkRequiredProperties(rd.getData(), Arrays.asList("active", "searchText"));
        return customerService.search(rd.getData(), rd.getPaging());
    }

    //@PreAuthorize("hasAuthority('customer:add')")
    @RequestMapping(value = "/{id}/status", method = RequestMethod.PUT, produces = {"application/json"})
    public Boolean editActiveStatus(@RequestBody CustomerStatus customerStatus, @PathVariable Long id) throws Exception {
        customerService.setActiveStatusToCustomer(id, customerStatus.getActive());
        return true;
    }

    //@PreAuthorize("hasAuthority('customer:read')")
    @RequestMapping(value = "/active", method = RequestMethod.GET,produces = {"application/json"})
    public List<CustomerFullName> getAllActive() {
        return customerService.getActiveCustomers();
    }
}
