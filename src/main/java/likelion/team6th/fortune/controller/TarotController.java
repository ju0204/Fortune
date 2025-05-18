package likelion.team6th.fortune.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import likelion.team6th.fortune.dto.TarotDTO;
import likelion.team6th.fortune.service.TarotService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TarotController {
	
	private final TarotService tarotService;
	
	@PostMapping("/admin/tarot/add")
	public TarotDTO registerTarots(@RequestBody TarotDTO tarotDTO) {
	    TarotDTO result = tarotService.registerTarots(tarotDTO);
	    return result;
	}
	
	@PostMapping("/admin/tarot/update/{id}")
	public TarotDTO updateTarots(@PathVariable("id") Long id, @RequestBody TarotDTO tarotDTO) {
	
		TarotDTO result = tarotService.updateTarot(id, tarotDTO);
		
		return result;
	}
	
	@PostMapping("/admin/tarot/delete/{id}")
	public ResponseEntity<String> deletePost(@PathVariable("id") Long id) {
        boolean deleted = tarotService.deleteTarot(id);
        if (deleted) {
            return ResponseEntity.ok("Tarot deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarot not found.");
        }
    }
	
	
}
