package Team6.BWU5TEAM6.services;

import Team6.BWU5TEAM6.dto.MunicipalyDTO;
import Team6.BWU5TEAM6.entities.Municipality;
import Team6.BWU5TEAM6.exceptions.NotFoundException;
import Team6.BWU5TEAM6.repositories.MunicipalityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MunicipalityService {
    @Autowired
    private MunicipalityDAO municipalityDAO;

    public Page<Municipality> getMunicipality(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.municipalityDAO.findAll(pageable);
    }

    public Municipality findById(int id) {
        return this.municipalityDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Municipality findByIdAndUpdate(int id, MunicipalyDTO newMunicipality) {
        Municipality municipality = this.findById(id);
        municipality.setName_municipality(newMunicipality.name_municipality());
        municipality.setName_district(newMunicipality.name_district());
        return this.municipalityDAO.save(municipality);
    }

    public void findByIdAndDelete(int id) {
        Municipality municipality = this.findById(id);
        this.municipalityDAO.delete(municipality);

    }

}
