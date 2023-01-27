package mx.com.tutum.admin.infrastructure.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import mx.com.tutum.Application;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/secured")
public class SecuredConfiguratorController {
	
	@GetMapping(path = "/index", produces = MediaType.TEXT_HTML_VALUE)
	public String getIndex(Model model) {
		model.addAttribute("cache", "?" + Application.cacheTimeStamp);
		model.addAttribute("usuario", "ADMIN");
		
		return "admin/secured/index";
	}
	
	@GetMapping(path = "/student", produces = MediaType.TEXT_HTML_VALUE)
	public String getStudent(Model model) {
		model.addAttribute("cache", "?" + Application.cacheTimeStamp);
		model.addAttribute("usuario", "ADMIN");
		
		return "admin/secured/student";
	}
	
	@GetMapping(path = "/course", produces = MediaType.TEXT_HTML_VALUE)
	public String getCourse(Model model) {
		model.addAttribute("cache", "?" + Application.cacheTimeStamp);
		model.addAttribute("usuario", "ADMIN");
		
		return "admin/secured/course";
	}
	
	@GetMapping(path = "/score", produces = MediaType.TEXT_HTML_VALUE)
	public String getScore(Model model) {
		model.addAttribute("cache", "?" + Application.cacheTimeStamp);
		model.addAttribute("usuario", "ADMIN");
		
		return "admin/secured/score";
	}
	
	@GetMapping(path = "/extra", produces = MediaType.TEXT_HTML_VALUE)
	public String getExtra(Model model) {
		model.addAttribute("cache", "?" + Application.cacheTimeStamp);
		model.addAttribute("usuario", "ADMIN");
		
		return "admin/secured/extra";
	}
	
}
