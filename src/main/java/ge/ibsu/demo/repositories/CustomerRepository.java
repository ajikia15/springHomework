package ge.ibsu.demo.repositories;

import ge.ibsu.demo.dto.CustomerInfo;
import ge.ibsu.demo.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("From Customer where active = :active and " +
            "concat(firstName, concat(' ', lastName)) like :searchValue")
    Page<Customer> search(@Param("active") Integer active, @Param("searchValue") String searchValue, Pageable pager);

    @Query(value = "select * from customer where active = :active and " +
            "concat(first_name, concat(' ', last_name)) like :searchValue",
            countQuery = "select count(*) from customer where active = :active and " +
                    "concat(first_name, concat(' ', last_name)) like :searchValue",
            nativeQuery = true)
    Page<Customer> searchWithNative(@Param("active") Integer active, @Param("searchValue") String searchValue, Pageable pager);

    @Modifying
    @Query("Update Customer c set c.active = :active where id = :id")
    void setActiveStatusForCustomer(@Param("id") Long id, @Param("active") Integer active);

    <T> List<T> findAllByActive(Integer active, Class<T> type);

    @Query("select new ge.ibsu.demo.dto.CustomerInfo(c.firstName, c.lastName, c.address.address) From Customer c where c.active = :active and " +
            "concat(c.firstName, concat(' ', c.lastName)) like :searchValue")
    Page<CustomerInfo> searchInfo(@Param("active") Integer active, @Param("searchValue") String searchValue, Pageable pager);
}
