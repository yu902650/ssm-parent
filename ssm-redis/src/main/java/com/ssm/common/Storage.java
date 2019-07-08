package com.ssm.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {

    // 记录区域信息
    public static Map<Integer, String> region = new HashMap<>();
    //page测试次数
    public static Integer testDeviceCount = 0;
    //page测试状态
    public static Integer testStatus = 1;
    //page执行中机器标记
    public static List<String> deviceFlag = new ArrayList<>();
    // 配置文件
    public static Map<String, String> properties = new HashMap<>();

    public static String ROOT_PATH = null;

    public static String IMAGE_SAVE_PATH = null;

    public static String IMAGE_RESULT_PATH = null;


}
