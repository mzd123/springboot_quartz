package com.mzd.my_boot_quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.Serializable;


/**
 * 实现序列化接口、防止重启应用出现quartz Couldn't retrieve job because a required class was not found 的问题
 */
public class TestJob2 implements Job, Serializable {

    private static final long serialVersionUID = 1L;

//    @Autowired
//    private TestService testService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //  testService.doTest();

        System.out.println("任务执行成功2");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
