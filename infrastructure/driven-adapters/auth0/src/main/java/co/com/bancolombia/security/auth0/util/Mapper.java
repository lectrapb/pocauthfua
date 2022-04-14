package co.com.bancolombia.security.auth0.util;

import co.com.bancolombia.security.auth0.entity.LogEntity;
import co.com.bancolombia.security.model.logs.Log;


public class Mapper {

    public static Log toModel(LogEntity entity){

         return  Log.builder()
                 .id(entity.getId())
                 .messageId(entity.getMessageId())
                 .appId(entity.getAppId())
                 .status(entity.getStatus())
                 .action(entity.getAction())
                 .data(entity.getData())
                 .create(entity.getCreate())
                 .build();
    }

    public static LogEntity toEntity(Log model){

        LogEntity modelEntity = new LogEntity();
        modelEntity.setId(model.getId());
        modelEntity.setMessageId(model.getMessageId());
        modelEntity.setAppId(model.getAppId());
        modelEntity.setStatus(model.getStatus());
        modelEntity.setAction(model.getAction());
        modelEntity.setData(model.getData());
        return  modelEntity;
    }
}
