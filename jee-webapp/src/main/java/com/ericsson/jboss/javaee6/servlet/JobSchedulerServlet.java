package com.ericsson.jboss.javaee6.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.Timer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.jboss.javaee6.ejb.JobScheduler;
import com.ericsson.jboss.javaee6.jobs.FileCollectionJob;
import com.ericsson.jboss.javaee6.jobs.Job;

@WebServlet(name = "JobSchedulerServlet", urlPatterns = { "/JobSchedulerServlet" })
public class JobSchedulerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private JobScheduler scheduler;

	private static final Logger LOG = LoggerFactory.getLogger(JobSchedulerServlet.class.getName());

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.write("JobControllerServlet invoked to start and timer expression for 7 seconds ROP...");
		writer.close();

		Job fileCollectionJob = createFileCollectionJob();

		ScheduleExpression expression = createExpression();
		final Timer timer7Sec = scheduler.startTimer(expression, false, fileCollectionJob);

		try {
			TimeUnit.SECONDS.sleep(45);
			LOG.info("45 seconds up... time to cancel the timer...");
		} catch (InterruptedException e) {
			LOG.error("exception while waiting for timer to sleep...");
		}

		scheduler.stopTimer(timer7Sec);
	}

	private ScheduleExpression createExpression() {
		final ScheduleExpression expression = new ScheduleExpression();
		expression.second("*/7");
		expression.minute("*");
		expression.hour("*");
		LOG.info("creating timer expression for 7 seconds ROP...");
		return expression;
	}

	private Job createFileCollectionJob() {
		return new FileCollectionJob();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
