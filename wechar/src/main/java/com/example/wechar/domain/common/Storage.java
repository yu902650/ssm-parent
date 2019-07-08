package com.example.wechar.domain.common;

import com.example.wechar.domain.entity.wechar.Ticket;
import com.example.wechar.domain.entity.wechar.Token;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Storage {

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

    // 公众号相关
    public static Token token = new Token();
    public static Ticket ticket = new Ticket();
    public static Map<String, Document> authToken = new HashMap<>();

    // 用户认证的集合
    public static ConcurrentHashMap<String, String> authRecord = new ConcurrentHashMap<>();
    // 需要处理的MQTT任务
    public static ConcurrentLinkedQueue<String> mqttTask = new ConcurrentLinkedQueue<>();
    // 执行指令的记录,主要是应对mqtt重复问题
    public static ConcurrentHashMap<String, String> commandRecord = new ConcurrentHashMap<>();
    //保存设备温度 和 信号
    public static ConcurrentHashMap<String, String> deviceTempAndSingleRecord = new ConcurrentHashMap<>();
    // 支付回调
    public static ConcurrentHashMap<String, String> payCallBack = new ConcurrentHashMap<>();
}
