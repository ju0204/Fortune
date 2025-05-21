package likelion.team6th.fortune.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import likelion.team6th.fortune.dto.TarotDTO;
import likelion.team6th.fortune.service.TarotService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TarotController {
	
private final TarotService tarotService;
	
	//추가 요청
	@PostMapping("/admin/tarot/add")
	public String registerTarots(@ModelAttribute TarotDTO tarotDTO, Model model) {
		
	    tarotService.registerTarots(tarotDTO);
	    return "redirect:/admin/list"; // 또는 보여줄 타임리프 페이지
	}

	//추가 페이지 열기
	@GetMapping("/admin/tarot/add")
	public String showRegisterTarots( Model model) {
	    
	    model.addAttribute("result", TarotDTO.ofNew());
	    
	    return "admin/add-taro";
	}
	
	//수정 요청 
	@PostMapping("/admin/tarot/update/{id}")
	public String updateTarots(@PathVariable("id") Long id, @ModelAttribute TarotDTO tarotDTO) {
	
		tarotService.updateTarot(id, tarotDTO);
		
		return "redirect:/admin/list";
	}
	
	//수정 페이지 이동 
	@GetMapping("/admin/tarot/update/{id}")
	public String showUpdateTarots(@PathVariable("id") Long id,Model model) {
		// 1. 서비스에서 id로 해당 타로 데이터를 가져옴
		TarotDTO tarotdto = tarotService.findById(id);
	    
		// 2. 모델에 담아 수정 폼에 전달
	    model.addAttribute("result", tarotdto);

	    return "admin/edit-taro";
	}
	
	@PostMapping("/admin/tarot/delete/{id}")
	public String deleteTarot(@PathVariable("id") Long id, Model map) {
        boolean deleted = tarotService.deleteTarot(id);
        map.addAttribute("deleted", deleted);
        return "redirect:/admin/list";
    }
	
	
}
