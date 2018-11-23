package com.yetland.data.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author YETLAND
 * @date 2018/11/22 16:02
 */
@Entity
public class BaseCacheEntity {
    @Id
    private long id;
    private String objectId;
    private String value;
    private String owner;

    @Generated(hash = 381803070)
    public BaseCacheEntity(long id, String objectId, String value, String owner) {
        this.id = id;
        this.objectId = objectId;
        this.value = value;
        this.owner = owner;
    }

    @Generated(hash = 48413824)
    public BaseCacheEntity() {
    }

    public String getObjectId() {
        return this.objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
