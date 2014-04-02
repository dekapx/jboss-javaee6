package com.kapx.jboss.jee6.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.as.quickstarts.cluster.hasingleton.service.ejb.ServiceAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "ApplicationServlet", urlPatterns = { "/ApplicationServlet" })
public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationServlet.class);

	@EJB
	private ServiceAccess accessBean;

	public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final PrintWriter writer = response.getWriter();
		writer.write("\nApplicationServlet invoked ...");
		for (int i = 0; i < 4; i++) {
			LOG.info(" #{} - The service is active on node with name = {} ", i, accessBean.getNodeNameOfTimerService());
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public String getServletInfo() {
		return "ApplicationServlet";
	}

}
