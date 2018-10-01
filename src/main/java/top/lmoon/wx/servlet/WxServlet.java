package top.lmoon.wx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WxLocalApi;

import top.lmoon.mail.MailUtil;
import top.lmoon.util.ExceptionUtil;

public class WxServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");
        System.out.println("--signature:"+signature);
        System.out.println("--timestamp:"+timestamp);
        System.out.println("--nonce:"+nonce);
        System.out.println("--echostr:"+echostr);
        PrintWriter writer = resp.getWriter();
        try {
			if(!WxLocalApi.checkSignature(System.getenv("WX_TOKEN"), timestamp, nonce, signature)){
				writer.print("求放过。。。");
		        writer.flush();
				return;
			}
		} catch (AesException e) {
			System.out.println(ExceptionUtil.getExceptionMessage(e));
			MailUtil.asyncSendErrorEmail(e);
			writer.print("出错了。。。看日志吧。。。");
	        writer.flush();
			return;
		}
        writer.print(echostr);
        writer.flush();
    }
}
