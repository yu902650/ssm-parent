package com.ssm.common.aliconfig;

/**
 * 阿里支付平台接入参数配置类
 *
 * @author gaojunming
 */
public class Config {
    //商户UID
    public static final String SELLER_ID = "2088102169470215";
    /*以下为通用接入参数*/
    //开放平台应用的APPID
    public static final String APP_ID = "2016080100142192";
    //请求和签名使用的字符编码格式
    public static final String CHARSET = "UTF-8";
    //支付宝公钥
    public static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";
    //开发者应用私钥
    public static final String APP_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAN2tw80/8XA6vE6qLwIKlFCo8yEZ3+YMvwbWJJK/2QX+oTcriMC26XOGrMlXRIGPTWgS6UPLwArd8H1R4oHHBhx6teHjLyN0NBPBd5Zdzf9e4I9sKQNXOF4siury0eJVUWAQOps5s5k06vdpsBzjX+Sh3Zi3BANgBX6Wq4LszY8lAgMBAAECgYAPf6Se5weUVybvQQrEU8UG1qkY+VwkE2QPsE0iTXN47dKLrumCv8cWoqXV9dn+TOyWiCvy533nxJiZym3RsP6GQeEczEzcqCUMOtRamxU3HVoVz8oevnJjv4ieXLhMqCXCAWtreW4SJMlqid637PM8i9Oh1yzquvyrvq6nf7NpwQJBAPbPl/8OYt29KhrPK2Rth8Hjq5Lpq5MRbrE8maoOImJmmJ+lM1FgcFXF1hseSrhHbv9BuYfyiqcRDfznTnwhMI0CQQDl7qGkHnbINL2htWlUWC/0hrbS4ckKX80RzSZplGf7uLXUn9wSQNucPIWUOeRJ+6iHq0+NACgpgxfNXmXr/C75AkAtOMw4x4IF7usQDnV86TS+qeB+AqKVR9RoVRoytnRGY1YDqllEx87NaHpabapzKWOFEZ3U8vPEuLM/a4JN7CbhAkEAxKaygn43kwVghlR0kFmYMiehZ30KB6Bl8GqoBraRtH2GriJHAymLafVtPZfBwvDEc7w1nl9a+h4Plmq8+vbFaQJBAIzz0mA55/pkdIMUruAm8bsRmCeWhktMemyJPGjqs3wRz2y67V4DpVkh0vVI1osEw44/MfRIykAsZrItr/eIYYQ=";
    //支付宝网关
    public static final String URL = "https://openapi.alipaydev.com/gateway.do";//注意沙箱模式的支付宝网关和正式支付宝网关是不同的
}


