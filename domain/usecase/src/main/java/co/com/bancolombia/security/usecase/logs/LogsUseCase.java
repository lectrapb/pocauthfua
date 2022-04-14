package co.com.bancolombia.security.usecase.logs;

import co.com.bancolombia.security.model.logs.Log;
import co.com.bancolombia.security.model.logs.gateways.LogsRepository;
import co.com.bancolombia.security.model.session.SessionGlobals;
import co.com.bancolombia.security.model.util.Constant;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class LogsUseCase {


    private final LogsRepository repository;

    public void savelog(Log log){
        var messageId = SessionGlobals.messageId.get();
        var appId = SessionGlobals.appId.get();
        log.setMessageId(messageId);
        log.setAppId(appId);
        repository.save(log);
    }


    public List<Log> findAll(){

        return repository.findOrderByIdLimitTo(Constant.TOTAL_LOGS);
    }


    public String castToString(Object object){
        return  repository.convertJson(object);
    }
}
