package com.example.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.runner.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Member;
import com.example.model.ProductVO;
import com.example.service.TestService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	TestService testService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView test ( Locale locale ) {
		ModelAndView mav = new ModelAndView( "home" );
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
	
		mav.addObject("serverTime", formattedDate);
		return mav;
	}
	@RequestMapping(value = "/st", method = RequestMethod.GET)
	public String st(RedirectAttributes rttr){
		logger.info("st called but redirect to /st...");
		rttr.addFlashAttribute("msg", "Try once again!");
		return "redirect:/st";
	}
	@RequestMapping("/doE")
	public void doE(String msg){
		logger.info("doE called...."+msg);
	}
	@RequestMapping("/doJSON")
	@ResponseBody
	public ModelAndView doJSON(){
	/*	ProductVO vo1 = new ProductVO("난 정말 자바를 공부한적이 없다구요", 30000);
		ProductVO vo2 = new ProductVO("뇌를 자극하는 자바 프로그래밍", 40000);
		
		System.out.println( "객체 1 : " + vo1.getName() + "\n가격 : " + vo1.getPrice() );
		System.out.println( "객체 2 : " + vo2.getName() );
	*/
		ModelAndView mav = new ModelAndView( "form" );
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		ProductVO vo = new ProductVO();
		vo.setName("김연지");
		
		ProductVO vo1 = new ProductVO();
		
		ProductVO vo2 = new ProductVO();
		vo2.setName("송석우");
		
		list.add(vo);
		list.add(vo2);
		
		int i = 0;
		for ( ProductVO a : list ) {
			System.out.println( a.getName() );
			i++;
		}
		
		List<Map<String, Object>> name = testService.test();
		
		for ( int j = 0; j < name.size(); j++ ) {
			System.out.println( name.get(j) );
		}
		
//		System.out.println( name );
//		System.out.println( vo2.getName() );
		
		return mav;
		
	}
	
	@RequestMapping( value="test2", method = RequestMethod.GET )
	public void test2 (
			@PathVariable ( value="name" ) String name
			) {
		System.out.println( "test" );
		System.out.println( name );
	}
	
	@RequestMapping(value="/test", method=RequestMethod.POST)
	public ModelAndView test (
			@RequestParam(value="id") String id,
			@RequestParam(value="password") String password,
			@RequestParam(value="name") String name
			
			) {
		ModelAndView mav = new ModelAndView("member");
		
//		System.out.println( "비밀번호 : " + password );
//		System.out.println( "이름 : " + name );
		
		int result = testService.test1(id, password, name);
		
		if ( result > 0 ) {
			//success
			Member member = testService.get(id);
			// Member member = Service calling
			//model = Member.java
			mav.addObject("member", member);
			
			if ( member == null ) {
				mav.setViewName("fail");
			} else {
				mav.setViewName("member");
			}
			
		} else if ( result == 0 ) {
			//fail
			mav.setViewName("fail");
		}
		
//		System.out.println( "결과 : " + result );
		/*
		k = 0
		System.out.println(k++);
		*/
		return mav;
	}
}
