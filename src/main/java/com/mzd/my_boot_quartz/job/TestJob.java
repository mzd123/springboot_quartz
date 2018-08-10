package com.mzd.my_boot_quartz.job;

import java.io.Serializable;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * 实现序列化接口、防止重启应用出现quartz Couldn't retrieve job because a required class was not found 的问题
 */
public class TestJob implements Job, Serializable {

    private static final long serialVersionUID = 1L;

//    @Autowired
//    private TestService testService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //  testService.doTest();

        System.out.println("任务执行成功");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
