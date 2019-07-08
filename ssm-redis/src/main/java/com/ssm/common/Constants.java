package com.ssm.common;

import com.alibaba.fastjson.serializer.SerializeConfig;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {

    public static final int SUCCESS = 200;

    public static final int TOKEN_INVALID= 20001;

    public static final int UNKNOW_ERROR = 1000;
    public static final int SESSION_TIMEOUT = 1001;
    public static final int RESOURCE_NOT_EXIST = 1002;
    /**
     * 业务异常
     */
    public static final int BUSSINESS_ERROR = 1003;
    /**
     * 缺少参数
     */
    public static final int PARAMETER_MISSING = 1004;
    public static final int RESOURCE_DUPLICATE = 1005;
    public static final int SESSION_EXIST = 1006;
    public static final int RESOURCE_IS_EXIST = 1007;
    /**
     * 数据权限错误,无权操作数据
     */
    public static final int DATA_AUTH = 1008;
    /**
     * 参数校验错误
     */
    public static final int PARAMETER_ERROR = 1009;
    /**
     * 数据重复处理错误提示
     */
    public static final int REPEAT_DISPOSE_ERROR = 1010;

    /**
     * 默认每页条数
     */
    public static final int pageSize = 10;
    /**
     * 允许上传的文件类型
     */
    public static final List<String> fileTypes = Arrays.asList("jpg", "jpeg", "bmp", "png", "gif", "csv", "bin", "apk", "svg", "svgz", "xls", "xlsx");

    public static SerializeConfig config = new SerializeConfig();

    /**
     * pc端windows系统
     */
    public static final String PC_AGENT_WINDOWS = "Windows";

    /**
     * pc端mac
     */
    public static final String PC_AGENT_MAC = "Macintosh";

    /**
     * 移动端安卓设备
     */
    public static final String M_AGENT_ANDROID = "Android";

    /**
     * 移动端苹果设备
     */
    public static final String[] M_AGENT_IPHONE = {"mac os x", "iphone", "ipad", "ipod", "os"};


    /**
     * pc端 session key 前缀
     */
    public static final String PC_KEY_START = "PC";

    /**
     * 移动端 session key 前缀
     */
    public static final String M_KEY_START = "MOBILE";

    /**
     * 设备类型开始符
     */
    public static final String START_KEY_TYPE = "startKeyType";

    /**
     * 客户端类型
     */
    public static final String USER_AGENT = "user-agent";

    /**
     * 角色缓存前缀
     */
    public static final String CACHED_REDIS_ROLE = "ADMIN_AUTH_ROLE_ID_";

    /**
     * 接口缓存前缀
     */
    public static final String CACHED_REDIS_INTERFACE = "ADMIN_AUTH_INTERFACE_ID_";

    //用户TOKEN前缀
    public static final String CACHED_REDIS_TOKEN = "AUTH_TOKEN_ID_";

    public static boolean isInited = false;

    public static final String CACHED_REDIS_MODULARS_LOGIN_USER_ORG = "LOGIN_USER_ORG_MODULAR_";

    public static void setIsInited(boolean flag) {
        Constants.isInited = flag;
    }

    /**
     * 配置文件
     */
    public static Map<String, String> properties = new HashMap<>();

    /**
     * 根目录
     */
    public static String ROOT_PATH = null;
    /**
     * 图片虚拟目录
     */
    public static String IMAGE_RESULT_PATH = null;
    /**
     * 图片保存目录
     */
    public static String IMAGE_SAVE_PATH = null;
}
