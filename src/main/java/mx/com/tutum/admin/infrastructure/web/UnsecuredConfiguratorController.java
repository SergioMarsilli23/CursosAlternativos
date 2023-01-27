package mx.com.tutum.admin.infrastructure.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import mx.com.tutum.Application;


@Controller
@RequestMapping("/admin")
public class UnsecuredConfiguratorController {
	
	//@Autowired
	//private UserAuthService userAuthService;

	@GetMapping(path = {"", "/"}, produces = MediaType.TEXT_HTML_VALUE)
	public String getConfigurador(Model model) {
		model.addAttribute("cache", "?" + Application.cacheTimeStamp);
		
		return "admin/main";
	}
	
	@GetMapping(path = "/login", produces = MediaType.TEXT_HTML_VALUE)
	public String getAcceder(Model model) {
		model.addAttribute("cache", "?" + Application.cacheTimeStamp);
		
		return "admin/login";
	}
	
	@GetMapping(path = "/index", produces = MediaType.TEXT_HTML_VALUE)
	public String getInicio(Model model) {
		model.addAttribute("cache", "?" + Application.cacheTimeStamp);
		model.addAttribute("usuario", "ADMIN");
		
		return "admin/index";
	}
	
	/*@PostMapping(path = {"","/"}, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String postConfigurador(@RequestBody MultiValueMap<String, String> datosAcceso) {
		String usuario = datosAcceso.getFirst("usuario");
		String contrasena = datosAcceso.getFirst("contrasena");
		
		try {
			DatosAutenticacion datos = userAuthService.authUserWithRoles(usuario, contrasena);
			
			if (datos != null && datos.getRoles() != null && datos.getRoles().contains("ROLE_ADMIN")) {
				return "configurator/principal";
			} else {
				return "redirect:/configurador?error";
				//throw new BadCredentialsException("Usuario no permitido");
			}
		} catch (Exception e) {
			return "redirect:/configurador?error";
		}
	}*/
	
}
