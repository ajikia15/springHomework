package ge.ibsu.demo.repositories;

import ge.ibsu.demo.dto.projections.AddressPostalCodeView;
import ge.ibsu.demo.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findOneByAddress(String address);

    @Query("From Address")
    List<AddressPostalCodeView> findAllAddressPostalCode();

}
