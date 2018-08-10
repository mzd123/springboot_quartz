package com.mzd.my_boot_quartz.job;

import java.io.Serializable;

import com.mzd.my_boot_quartz.service.JobService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;


public class TestJob implements Job, Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private JobService jobService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        jobService.doTest();
        System.out.println("任务执行成功");
    }
}
