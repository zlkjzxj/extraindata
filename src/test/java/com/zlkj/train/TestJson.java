package com.zlkj.train;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zlkj.train.bean.DiskBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zxj
 * @Date: 2018\12\24 0024 14:32
 * @Description:
 */
public class TestJson {
    public static void main(String[] args) {
        List<DiskBean> diskBean11 = new ArrayList<>();
        List<DiskBean> diskBean22 = new ArrayList<>();
        DiskBean diskBean = new DiskBean();
        diskBean.setDiskid("1");
        diskBean.setFreeSpace("1111");
        DiskBean diskBean1 = new DiskBean();
        diskBean1.setDiskid("2");
        diskBean1.setFreeSpace("2222");
        diskBean11.add(diskBean);
        diskBean22.add(diskBean);
        Map<String, List<DiskBean>> map = new HashMap<>();
        map.put("didks1", diskBean11);
        map.put("didks2", diskBean22);
        String xx = JSONObject.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
        System.out.println(xx);

    }
}
