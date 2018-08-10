package com.mzd.my_boot_quartz.service;

import com.mzd.my_boot_quartz.bean.QuartzBean;
import com.mzd.my_boot_quartz.mapper.JobMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobMapping testMapping;

    public void doTest() {
        System.out.println("aaaa");
    }


    public List<QuartzBean> listQuartzBean(String name) {
        return testMapping.listQuartzBean(name);
    }
}
