package com.zlkj.train.confs;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zlkj.train.bean.FrmSysPara;
import com.zlkj.train.result.CodeMsg;
import com.zlkj.train.utils.Constant;
import com.zlkj.train.utils.HttpClientUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Component
@Order(value=0)
public class CommandLineRunnerListener implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(String... args) {

        logger.info("******************项目启动加载数据**********************");
        String content = null;
        JSONObject jsonObject = null;

        /*1.读取本地配置文件*/
        try {
            File jsonFile = new File(Constant.FILE_PATH);

            //判断配置文件是否存在，不存在把错误写入全局变量
            content = FileUtils.readFileToString(jsonFile);
        } catch (IOException e) {
            logger.error(CodeMsg.JSON_FIlE_NOT_EXIST.getMsg());
            Constant.GLOBAL_MESSAGE = CodeMsg.JSON_FIlE_NOT_EXIST.getMsg();
        }
        jsonObject = JSONObject.parseObject(content);
        //判断文件不为空且文件包含内容
        if (jsonObject != null && jsonObject.size() > 0) {

            Constant.HOST = jsonObject.getString("HOST");
            Constant.FILE_SUFFIX = jsonObject.getString("FILESUFFIX");
            Constant.IS_DELETE = jsonObject.getString("ISDELETE");

            /*2.通过配置文件的主机地址去查询后台接口是否能够调通*/
            String testDbUrl = Constant.HOST.concat(Constant.TEST_DB_URL);

            String result = HttpClientUtil.doPost(testDbUrl, new HashMap<>(), "UTF-8");
            JSONObject _result = JSONObject.parseObject(result);
            //这是为了防止如果连接失败返回的是status 404,所有要判断null
            if (_result.getString("code") != null && _result.getString("code").equals(CodeMsg.HTTP_SUCCESS.getCode())) {
                Constant.USE_DB = true;
                /*3.后台访问成功，更新配置文件参数*/
                updateProperties(jsonObject);
            }
            /* else {
             *//*后台访问失败，不连接数据库，直接跳转导出文件页面*//*
//                Constant.GLOBAL_MESSAGE = CodeMsg.VISIT_MANAGER_ERR.getMsg();
            }*/

        }

    }

    private void updateProperties(JSONObject oldFileJsonObject) {
        String getParamsUrl = Constant.HOST.concat(Constant.GET_PARAMS_URL);
        Map<String, String> map = new HashMap<>();
        map.put("xtlb", "01");
        map.put("cslx", "2");
        map.put("sfxs", "1");
//        map.put("xgjb", "9");

        String result = HttpClientUtil.doPost(getParamsUrl, map, "UTF-8");

        JSONObject _result = JSONObject.parseObject(result);
        if (!_result.getString("code").equals("200")) {
            Constant.GLOBAL_MESSAGE = CodeMsg.GET_PARAM_ERR.getMsg();
            return;
        }
        //获取返回的值data
        JSONArray returnJsonArray = _result.getJSONArray("data");

        JSONObject object = new JSONObject();

        List<FrmSysPara> list = JSONObject.parseArray(returnJsonArray.toJSONString(), FrmSysPara.class);
        //循环数据库查处来的值 添加到需要修改的实体内，并修改本地的值
        for (FrmSysPara frmSysPara : list) {
            if (frmSysPara.getGjz().equals("HOST")) {
                object.put(frmSysPara.getGjz(), frmSysPara.getMrz());
                Constant.HOST = frmSysPara.getMrz();
                continue;
            }
            if (frmSysPara.getGjz().equals("FILESUFFIX")) {
                object.put(frmSysPara.getGjz(), frmSysPara.getMrz());
                Constant.FILE_SUFFIX = frmSysPara.getMrz();
                continue;
            }
            if (frmSysPara.getGjz().equals("ISDELETE")) {
                object.put(frmSysPara.getGjz(), frmSysPara.getMrz());
                Constant.IS_DELETE = frmSysPara.getMrz();
            }
        }
        //循环原来的json值，把需要修改的重新放回实体
        Set<Map.Entry<String, Object>> set = oldFileJsonObject.entrySet();

        Iterator<Map.Entry<String, Object>> it = set.iterator();

        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if (!key.equals("HOST") && !key.equals("FILESUFFIX") && !key.equals("ISDELETE")) {
                object.put(key, value);
            }

        }
        //把查询到的参数写入本地json文件
        try {

            Path src = Paths.get(Constant.FILE_PATH);
            BufferedWriter writer = Files.newBufferedWriter(src, StandardCharsets.UTF_8
                    , StandardOpenOption.TRUNCATE_EXISTING); //TRUNCATE_EXISTING删除更新， APPEND追加
            writer.write(object.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
