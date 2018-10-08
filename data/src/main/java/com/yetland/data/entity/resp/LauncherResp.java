package com.yetland.data.entity.resp;

import com.yetland.data.entity.BaseEntity;

import java.util.List;

/**
 * @author YETLAND
 * @date 2018/10/8 15:51
 */
public class LauncherResp<T extends BaseEntity> {
    private List<T> results;

    public List<T> getResults() {
        return results;
    }
}
