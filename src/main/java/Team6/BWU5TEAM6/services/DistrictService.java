package Team6.BWU5TEAM6.services;

import Team6.BWU5TEAM6.dto.DistrictDTO;
import Team6.BWU5TEAM6.entities.District;
import Team6.BWU5TEAM6.exceptions.NotFoundException;
import Team6.BWU5TEAM6.repositories.DistrictDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DistrictService {

    @Autowired
    private DistrictDAO districtDAO;

    public Page<District> getDistrict(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.districtDAO.findAll(pageable);
    }

    public District findById(int sigla) {
        return this.districtDAO.findById(sigla).orElseThrow(() -> new NotFoundException(sigla));
    }

    public District findByIdAndUpdate(int sigla, DistrictDTO newdistrict) {
        District district = this.findById(sigla);
        district.setDistrict(newdistrict.district());
        district.setRegion(newdistrict.region());
        return this.districtDAO.save(district);
    }

    public void findByIdAndDelete(int sigla) {
        District district = this.findById(sigla);
        this.districtDAO.delete(district);

    }


}
