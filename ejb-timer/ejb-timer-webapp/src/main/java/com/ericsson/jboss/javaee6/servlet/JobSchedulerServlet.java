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

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
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
		final ScheduleExpression expression = new ScheduleExpression();
		// one minute delay to start the scheduling
		long timeInterval = 60000;
		// create the start time with one minute delay
		long timeMillis = System.currentTimeMillis() + timeInterval;
		Date date = new Date(timeMillis);
		LOG.info("Create time expression for {} " + date);
		expression.start(date);
		expression.hour("*");
		expression.minute("*/2"); // should run after every 2 minutes
		// if time interval is more then 60 seconds then no need to provide
		// value to seconds
		// expression.second("*");
		return expression;
	}

	@SuppressWarnings("unused")
	private ScheduleExpression createExpressionDateRange() {
		final ScheduleExpression expression = new ScheduleExpression();
		expression.dayOfMonth("20-27");
		expression.hour("16");
		expression.minute("*");
		expression.second("*/15");
		return expression;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
