package Team6.BWU5TEAM6.controllers;

import Team6.BWU5TEAM6.dto.MunicipalyDTO;
import Team6.BWU5TEAM6.entities.Municipality;
import Team6.BWU5TEAM6.exceptions.CorrectDeleteMunicipality;
import Team6.BWU5TEAM6.services.MunicipalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/municipality")
public class MunicipalityController {
    @Autowired
    private MunicipalityService municipalityService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Municipality> getAllMunicipality(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size,
                                                 @RequestParam(defaultValue = "id") String sortBy) {
        return this.municipalityService.getMunicipality(page, size, sortBy);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Municipality findAndUpdate(@PathVariable int id, @RequestBody MunicipalyDTO body) {
        return municipalityService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findAndDelete(@PathVariable int id) throws CorrectDeleteMunicipality {
        municipalityService.findByIdAndDelete(id);
        throw new CorrectDeleteMunicipality();
    }

}
