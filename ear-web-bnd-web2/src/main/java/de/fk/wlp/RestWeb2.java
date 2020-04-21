package de.fk.wlp;

import static java.lang.String.format;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("/")
public class RestWeb2 {

	@Resource(name = "value-for-web2")
	private String envEntry;

	@GET
	public String getValueFromEnvEntry() {
		String valueFromJNDI = null;
		String valueFromFesouceInjection = null;
		try {
			InitialContext initialContext = new InitialContext();
			String value = (String) initialContext.lookup("java:comp/env/value-for-web2");
			valueFromJNDI = format("Value from Jndi %s", value);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		valueFromFesouceInjection = format("Value from resouce injection %s", envEntry);

		return valueFromJNDI + valueFromFesouceInjection;
	}

}
