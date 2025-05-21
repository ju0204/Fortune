package likelion.team6th.fortune.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import likelion.team6th.fortune.dto.ZodiacAdminDTO;
import likelion.team6th.fortune.dto.ZodiacDTO;
import likelion.team6th.fortune.entity.Zodiac;
import likelion.team6th.fortune.service.ZodiacService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/zodiac")
public class ZodiacController {
	
	private final ZodiacService zodiacService;
	
	@GetMapping("/add")
	public String registerZodiac(Model map) {
		map.addAttribute(ZodiacDTO.ofNew());
	    return "admin/add-zodiac";
	}
	
	@PostMapping("/add")
	public String registerZodiac(@ModelAttribute ZodiacAdminDTO zodiacAdminDTO,Model map) {
		ZodiacAdminDTO result = zodiacService.registerZodiac(zodiacAdminDTO);
		map.addAttribute(result);
	    return "redirect:/admin/list";
	}
	
	@GetMapping("/update/{id}")
	public String updateZodiac(@PathVariable("id") Long id, Model map) {
		ZodiacDTO zodiacDTO = zodiacService.findById(id);
		map.addAttribute("result", zodiacDTO);
		
		return "admin/edit-zodiac";
	}
	
	@PostMapping("/update/{id}")
	public String updateZodiac(@PathVariable("id") Long id, @ModelAttribute ZodiacAdminDTO zodiacAdminDTO, Model map) {
	
		ZodiacAdminDTO result = zodiacService.updateZodiac(id, zodiacAdminDTO);
		map.addAttribute(result);
		
		return "redirect:/admin/list";
	}
	
	@PostMapping("/delete/{id}")
    public String deleteZodiac(@PathVariable("id") Long id, Model map) {
        boolean deleted = zodiacService.deleteZodiacById(id);
        map.addAttribute("deleted", deleted);
        return "redirect:/admin/list";
        
    }

}
