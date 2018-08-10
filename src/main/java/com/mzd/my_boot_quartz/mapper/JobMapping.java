package com.mzd.my_boot_quartz.mapper;

import com.mzd.my_boot_quartz.bean.QuartzBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobMapping {

    List<QuartzBean> listQuartzBean(String name);
}
