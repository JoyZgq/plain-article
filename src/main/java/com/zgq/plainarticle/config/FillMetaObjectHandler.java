package com.zgq.plainarticle.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FillMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if(metaObject.hasSetter("createTime")){
            this.strictInsertFill(metaObject,"createTime", Date.class,new Date());
        }
        if(metaObject.hasSetter("updatedTime")){
            this.strictInsertFill(metaObject,"updatedTime", Date.class,new Date());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if(metaObject.hasSetter("updatedTime")){
            this.strictInsertFill(metaObject,"updatedTime", Date.class,new Date());
        }
    }
}

