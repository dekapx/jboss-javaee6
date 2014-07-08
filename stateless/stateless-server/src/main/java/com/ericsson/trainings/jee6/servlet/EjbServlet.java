package com.ericsson.trainings.jee6.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.trainings.jee6.ejb.api.HelloWorldLocal;

@WebServlet(name = "EjbServlet", urlPatterns = "/EjbServlet")
public class EjbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(EjbServlet.class);

	@EJB
	private HelloWorldLocal helloWorldEJB;

	@Override
	public void init() throws ServletException {
		LOGGER.info("EjbServlet initialized...");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final PrintWriter out = response.getWriter();
		out.print("Application Servlet invoked...");
		out.close();

		final String output = helloWorldEJB.sayHello("KAPX");
		LOGGER.info("HelloWorldBean generats the output \"{}\"", output);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void destroy() {
		LOGGER.info("EjbServlet destroyed...");
	}

}
