package kr.kina.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.kina.domain.MemberVO;
import kr.kina.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	private final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService service;
	
	@RequestMapping(value="/signUp", method=RequestMethod.GET)
	public void signUpGET() throws Exception {
		log.info("SignUp GET ..");
	}
	
	/**  ȸ������ : POST 
	 *   @param	  MemberVO��ü, Model��ü
	 *   @return  String xxx.jsp
	 */
	@RequestMapping(value="/signUp", method=RequestMethod.POST)
	public void signUpPOST(MemberVO member, RedirectAttributes rttr) throws Exception {
		log.info("SignUp POST ..");
		service.signUp(member);
		
		rttr.addAttribute("msg", "SUCCESS");
	}
	
	/**
	 * 	 �α��� : GET
	 */
	@RequestMapping(value="/fLogin", method=RequestMethod.GET)
	public void loginGET() throws Exception {
		log.info("Login GET ..");
	}
	
	/**
	 *	�α���  : POST  
	 */
	@RequestMapping(value="/fLogin", method=RequestMethod.POST)
	public String loginPOST(String id, String password,
						  RedirectAttributes attr, HttpServletRequest request) throws Exception {

		service.login(id, password);
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		attr.addFlashAttribute("msg", "Login");
		
		return "redirect:/";
	}
	
	/**
	 *  �α׾ƿ�  
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session)throws Exception {
		session.invalidate();
		return "redirect:/";
	}
	
	
	/**
	 *	���̹��� : GET 
	 */
	@RequestMapping(value="/myMusic", method=RequestMethod.GET)
	public String myMusicGET(HttpSession session, RedirectAttributes attr) throws Exception {
		log.info("myMusic GET.. ");
		
		if(session.getAttribute("id") != null){
			return "redirect:/";
		}
		return null;
	}
	
	
	

	
	
}
