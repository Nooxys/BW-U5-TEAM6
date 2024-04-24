package Team6.BWU5TEAM6.controllers;

import Team6.BWU5TEAM6.dto.DistrictDTO;
import Team6.BWU5TEAM6.entities.District;
import Team6.BWU5TEAM6.exceptions.CorrectDeleteDistrict;
import Team6.BWU5TEAM6.services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<District> getAllDistrict(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id") String sortBy) {
        return this.districtService.getDistrict(page, size, sortBy);
    }

    @PutMapping("/{sigla}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public District findAndUpdate(@PathVariable int sigla, @RequestBody DistrictDTO body) {
        return districtService.findByIdAndUpdate(sigla, body);
    }

    @DeleteMapping("/{sigla}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findAndDelete(@PathVariable int sigla) throws CorrectDeleteDistrict {
        districtService.findByIdAndDelete(sigla);
        throw new CorrectDeleteDistrict();
    }


}
