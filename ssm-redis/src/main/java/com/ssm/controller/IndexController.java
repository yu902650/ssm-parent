package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author bo bo
 * @date 2019/5/30 13:30
 * @desc
 */
@Controller("/bug")
public class IndexController {

    private static final int _1M = 1024 * 1024;

    @RequestMapping(value = "allocationMem",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse <String> allocationMem(@RequestBody AllocationMemReq allocationMemReq){

        BaseResponse<String> response = new BaseResponse<>();

        for (int i=0;i< allocationMemReq.getNum() ; i++){
            byte[] mem = new byte[allocationMemReq.getMem() * _1M];
        }

        response.setMessage("success");
        return response;
    }


    private class BaseResponse<T> {
        public void setMessage(T success) {
        }
    }

    private class AllocationMemReq {
        private int mem;
        private int num;

        public int getMem() {
            return mem;
        }

        public void setMem(int mem) {
            this.mem = mem;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
