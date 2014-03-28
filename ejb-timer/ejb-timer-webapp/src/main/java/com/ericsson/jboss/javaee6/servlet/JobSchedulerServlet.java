package com.ericsson.jboss.javaee6.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kapx.jboss.jee6.jobs.api.TimerJob;
import com.kapx.jboss.jee6.jobs.impl.TimerJobImpl;
import com.kapx.jboss.jee6.model.FileSearchTaskRequest;
import com.kapx.jboss.jee6.timer.api.SchedulerService;

@WebServlet(name = "JobSchedulerServlet", urlPatterns = { "/JobSchedulerServlet" })
public class JobSchedulerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(JobSchedulerServlet.class.getName());

	@EJB
	private SchedulerService schedulerService;

	public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final PrintWriter writer = response.getWriter();
		writer.write("\nJobControllerServlet invoked ...");

		LOG.info("JobControllerServlet invoked ...");

		final String jobId = "FileSearchJob";
		final String jobDesc = "This is a File Search Job...  ";
		final FileSearchTaskRequest taskRequest = new FileSearchTaskRequest();

		final TimerJob<FileSearchTaskRequest> timerJob = new TimerJobImpl(jobId, jobDesc, taskRequest);
		final ScheduleExpression expression = createExpressionForSpecificTime();

		LOG.info("Scheduler expression {} ", expression);

		writer.write("\nScheduler expression \n" + expression);
		schedulerService.createTimer(timerJob, expression, false);
		writer.close();
	}

	private ScheduleExpression createExpressionForSpecificTime() {
		// one minute delay to start the scheduling
		final long timeInterval = 60000;

		// start time with one minute delay
		final long timeMillis = System.currentTimeMillis() + timeInterval;

		final ScheduleExpression expression = new ScheduleExpression();
		// the time when scheduler will trigger..
		expression.start(new Date(timeMillis));
		// runs every hour
		expression.hour("*");
		// runs every 2 minutes interval
		expression.minute("*/3");
		// marked zero means seconds not considered
		expression.second(0);

		return expression;
	}

	@SuppressWarnings("unused")
	private ScheduleExpression createExpressionDateRange() {
		final ScheduleExpression expression = new ScheduleExpression();
		// trigger between 20 to 27th of every month
		expression.dayOfMonth("20-27");
		// trigger at 16th hour of the date i.e. 16:00 PM
		expression.hour("16");
		// runs every minute
		expression.minute("*");
		// runs every 15 seconds
		expression.second("*/15");

		return expression;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
