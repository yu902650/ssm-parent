package com.test.jsonToObject.diff;

import java.util.List;

/**
 * Created by bobo on 2019/4/15 21:23
 */
public class TxlDataDTO {
    List<TxlBaseInfoDTO> txlList;

    public List<TxlBaseInfoDTO> getTxlList() {
        return txlList;
    }

    public void setTxlList(List<TxlBaseInfoDTO> txlList) {
        this.txlList = txlList;
    }
}
