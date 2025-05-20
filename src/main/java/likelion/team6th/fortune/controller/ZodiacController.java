package likelion.team6th.fortune.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import likelion.team6th.fortune.dto.ZodiacAdminDTO;
import likelion.team6th.fortune.service.ZodiacService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/zodiac")
public class ZodiacController {
	
	private final ZodiacService zodiacService;
	
	@PostMapping("/add")
	public ZodiacAdminDTO registerTarots(@RequestBody ZodiacAdminDTO zodiacAdminDTO) {
		ZodiacAdminDTO result = zodiacService.registerZodiac(zodiacAdminDTO);
	    return result;
	}
	
	@PostMapping("/update/{id}")
	public ZodiacAdminDTO updateTarots(@PathVariable("id") Long id, @RequestBody ZodiacAdminDTO zodiacAdminDTO) {
	
		ZodiacAdminDTO result = zodiacService.updateZodiac(id, zodiacAdminDTO);
		
		return result;
	}
	
	@PostMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) {
        boolean deleted = zodiacService.deleteZodiacById(id);
        if (deleted) {
            return ResponseEntity.ok("Zodiac deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Zodiac not found.");
        }
    }

}
