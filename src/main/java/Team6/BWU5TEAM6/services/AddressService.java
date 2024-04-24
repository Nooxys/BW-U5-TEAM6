package Team6.BWU5TEAM6.services;

import Team6.BWU5TEAM6.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Team6.BWU5TEAM6.entities.Address;
import Team6.BWU5TEAM6.repositories.AddressDAO;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressDAO addressDAO;

    public List<Address> getAllAddresses() {
        return addressDAO.findAll();
    }

    public Address getAddressById(Long id) {
        return addressDAO.findById(id)
                .orElseThrow(() -> new BadRequestException("Address not found with ID: " + id));
    }

    public Address createAddress(Address address) {
        return addressDAO.save(address);

    }
    public Address updateAddress(Long id, Address addressDetails) {
        Address address = getAddressById(id);
        address.setStreet(addressDetails.getStreet());
        address.setCivicNumber(addressDetails.getCivicNumber());
        address.setLocality(addressDetails.getLocality());
        address.setPostalCode(addressDetails.getPostalCode());
        address.setMunicipality(addressDetails.getMunicipality());
        return addressDAO.save(address);
    }

    public void deleteAddress(Long id) {
        Address address = getAddressById(id);
        addressDAO.delete(address);
    }
}
