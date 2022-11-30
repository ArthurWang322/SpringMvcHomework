package tw.thirdteam.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import tw.thirdteam.model.IMemberService;
import tw.thirdteam.model.Member;

@Controller
public class LoginCheck {
	
	@Autowired
	private IMemberService memberService;

	@GetMapping("/logintest")
	public String mainAction() {
		return "Login";
	}

	@PostMapping("/login.do")
	public String loginAction(HttpSession session, Member member) {
		member = memberService.login(member.getMemberemail(), member.getMemberpassword());
		if (member == null) {
			session.setAttribute("status", "failed");

		} else {
			if (member.getMemberlevel().equals("一般會員")) {
				session.setAttribute("membername", member.getMembername());
				session.setAttribute("memberid", member.getMemberid());
				return "Index";
			}
			if (member.getMemberlevel().equals("停用")) {
				session.setAttribute("memberstatus", "limitmember");
				return "Login";
			}
			if (member.getMemberlevel().equals("管理人員")) {
				session.setAttribute("membername", member.getMembername());
				return "StaffManageMember";
			}
		}
		return "Login";
	}
}
