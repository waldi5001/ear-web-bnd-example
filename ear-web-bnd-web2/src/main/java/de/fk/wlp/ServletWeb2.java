package de.fk.wlp;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;

public class ServletWeb2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Resource(name = "value-for-web2")
	private String envEntry;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			InitialContext initialContext = new InitialContext();
			String value = (String) initialContext.lookup("java:comp/env/value-for-web2");
			resp.getOutputStream().print(format("ValueFromJNDI %s ", value));
		} catch (NamingException e) {
			e.printStackTrace();
		}
		resp.getOutputStream().print(format("Value from Resouce Injection %s", envEntry));
	}
}
