package com.mzd.my_boot_quartz.Controller;

import com.mzd.my_boot_quartz.bean.QuartzBean;
import com.mzd.my_boot_quartz.bean.Result;
import com.mzd.my_boot_quartz.service.JobService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobController {
    private final static Logger logger = LoggerFactory.getLogger(JobController.class);
    private final String baseuri = "/job";
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;
    @Autowired
    private JobService jobService;

    /**
     * 新增任务
     *
     * @param quartz
     * @return
     */
    @RequestMapping(value = baseuri + "/addschedule")
    public Result save(QuartzBean quartz) throws Exception {
        logger.info("新增任务");
        if (quartz.getOldJobGroup() != null) {
            JobKey key = new JobKey(quartz.getOldJobName(), quartz.getOldJobGroup());
            scheduler.deleteJob(key);
        }
        //获取.class
        Class cls = Class.forName(quartz.getJobClassName());
        cls.newInstance();
        //创建jobdetail
        JobDetail job = JobBuilder.newJob(cls).withIdentity(quartz.getJobName(),
                quartz.getJobGroup())
                //设置参数
                //.usingJobData("aa", "ceshi")
                //描述
                .withDescription(quartz.getDescription())
                .build();
        // 使用cron表达式
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression());
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + quartz.getJobName(), quartz.getJobGroup())
                .startNow()
                .withSchedule(cronScheduleBuilder)
                .build();
        //交由Scheduler安排触发
        scheduler.scheduleJob(job, trigger);
        return new Result("200", "", null);
    }

    /**
     * 获取任务列表
     *
     * @param name
     * @return
     */
    @RequestMapping(value = baseuri + "/getlist4schedule")
    public Result list(String name) {
        logger.info("任务列表");
        List<QuartzBean> list = jobService.listQuartzBean(name);
        return new Result("200", "", list);
    }

    /**
     * 立即执行
     *
     * @param quartz
     * @return
     */
    @RequestMapping(value = baseuri + "/doschedule")
    public Result trigger(QuartzBean quartz) throws Exception {
        logger.info("立即执行");
        JobKey key = new JobKey(quartz.getJobName(), quartz.getJobGroup());
        scheduler.triggerJob(key);
        return new Result("200", "", null);
    }

    /**
     * 暂停任务
     *
     * @param quartz
     * @return
     */
    @RequestMapping(value = baseuri + "/pauseschedule")
    public Result pause(QuartzBean quartz) throws Exception {
        logger.info("停止任务");
        JobKey key = new JobKey(quartz.getJobName(), quartz.getJobGroup());
        scheduler.pauseJob(key);
        return new Result("200", "", null);
    }

    /**
     * 从暂停中恢复过来
     *
     * @param quartz
     * @return
     */
    @RequestMapping(value = baseuri + "/recoverschedule")
    public Result resume(QuartzBean quartz) throws Exception {
        logger.info("恢复任务");
        JobKey key = new JobKey(quartz.getJobName(), quartz.getJobGroup());
        scheduler.resumeJob(key);
        return new Result("200", "", null);
    }

    /**
     * 删除任务
     *
     * @param quartz
     * @return
     */
    @RequestMapping(value = baseuri + "/deleteschedule")
    public Result remove(QuartzBean quartz) throws Exception {
        logger.info("删除任务");
        TriggerKey triggerKey = TriggerKey.triggerKey(quartz.getJobName(), quartz.getJobGroup());
        // 停止触发器
        scheduler.pauseTrigger(triggerKey);
        // 移除触发器
        scheduler.unscheduleJob(triggerKey);
        // 删除任务
        scheduler.deleteJob(JobKey.jobKey(quartz.getJobName(), quartz.getJobGroup()));
        logger.info("removeJob:" + JobKey.jobKey(quartz.getJobName()));
        return new Result("200", "", null);
    }


}
