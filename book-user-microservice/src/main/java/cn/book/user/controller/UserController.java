package cn.book.user.controller;

import java.io.OutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.book.comm.constVar.Const;
import cn.book.comm.pojo.SysResult;
import cn.book.comm.pojo.User;
import cn.book.comm.util.CookieUtils;
import cn.book.comm.util.VerifyCode;
import cn.book.user.service.UserService;

/**
 * �����û����ҵ������
 * 
 * @author tarena
 *
 */
@RestController
@RequestMapping("user/manage")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * ���ܵ�¼����
	 */
	@RequestMapping("login")
	public SysResult doLogin(HttpServletRequest request, HttpServletResponse response, User user, HttpSession session) {
		// ��֤��У��
//		String sessionValiStr = (String) session.getAttribute("ValiStr");
//		if (sessionValiStr == null || ValiStr == null || !sessionValiStr.toLowerCase().equals(ValiStr.toLowerCase())) {
//			return SysResult.build(Const.Failed, "��֤�����!", null);
//		}
		String ticket = userService.doLogin(user);
		if (StringUtils.isNotEmpty(ticket)) {
			CookieUtils.setCookie(request, response, Const.EM_TICKET, ticket);
			return SysResult.ok();
		} else {
			return SysResult.build(Const.Failed, "", null);
		}
	}

	/**
	 * ��¼��֤
	 * 
	 * @param ticket
	 * @return
	 */
	@RequestMapping("query/{ticket}")
	public SysResult queryTicket(@PathVariable String ticket,HttpServletRequest req,HttpServletResponse resp) {
		String userJson = userService.queryTicket(ticket);
		if (!StringUtils.isNotEmpty(userJson)) {
			return SysResult.build(Const.Failed, "", null);
		} else {
			CookieUtils.setCookie(req, resp, "user", userJson, 0, true);
			return SysResult.build(Const.Success, "��¼�ɹ�", userJson);
		}
	}

	/**
	 * ע��
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("save")
	public SysResult save(User user,String ValiStr, HttpSession session) {
		// ��֤��У��
		String sessionValiStr = (String) session.getAttribute("ValiStr");
		if (sessionValiStr == null || ValiStr == null || !sessionValiStr.toLowerCase().equals(ValiStr.toLowerCase())) {
			return SysResult.build(Const.Failed, "��֤�����!", null);
		}
		try {
			userService.save(user);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(Const.Failed, e.getMessage(), null);
		}
	}

	/**
	 * ��֤�û���������
	 * 
	 * @param userName
	 * @return
	 */
	@RequestMapping("checkUserName")
	public SysResult checkUserName(String userName) {
		int exist = userService.checkUserName(userName);
		if (exist == 1)
			return SysResult.build(Const.Failed, "������", null);
		return SysResult.ok();
	}

	/**
	 * �ǳ�
	 * 
	 * @param session
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("logout")
	public SysResult logout(HttpSession session, HttpServletRequest req, HttpServletResponse resp) {
		session.invalidate();
		CookieUtils.deleteCookie(req, resp, Const.EM_TICKET);
		return SysResult.ok();
	}

	@RequestMapping("valiImg")
	public void getValiImg(HttpServletResponse response, OutputStream out, HttpSession session) {
		// reponse�����������ʹ�û���
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		// ������֤��
		VerifyCode vc = new VerifyCode();
		vc.drawImage(out);
		// ����֤����ӵ�session��
		String code = vc.getCode();
		session.setAttribute(Const.ValiStr, code);
		System.out.println("ValiStr:" + code);
	}
}
