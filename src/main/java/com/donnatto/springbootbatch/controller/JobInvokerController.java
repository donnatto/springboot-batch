package com.donnatto.springbootbatch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobInvokerController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job processJob;

    @RequestMapping("/invokejob")
    public String handle() throws Exception {

        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(processJob, jobParameters);

        return "Batch job has been invoked";
    }

    @RequestMapping("/deletetasklet")
    public String delete() throws Exception {

        // Batch config file
        String[] batchConfig = { "batch-task.xml" };
        ApplicationContext context = new ClassPathXmlApplicationContext(batchConfig);

        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");

        // Job Bean
        Job job = (Job) context.getBean("deleteTask");

        try {
            JobExecution execution = jobLauncher.run(job, new JobParameters());
            System.out.println("Job Exit Status: " + execution.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Completed";
    }
}
