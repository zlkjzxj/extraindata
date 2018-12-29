package com.zlkj.train.threads;

import com.zlkj.train.bean.FrontDiskBean;
import com.zlkj.train.service.ExportToolService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: zxj
 * @Date: 2018\12\13 0013 17:20
 * @Description:
 */
public class ExportHandler implements Runnable {

    @Autowired
    private ExportToolService exportToolService;

    private FrontDiskBean diskBean;

    public ExportHandler(FrontDiskBean diskBean) {
        this.diskBean = diskBean;
    }

    @Override
    public void run() {
        // do 这里 写 处理的逻辑
        System.out.println("启动线程导出数据，线程name:" + Thread.currentThread().getName());
        exportToolService.copy(diskBean);
    }
}
