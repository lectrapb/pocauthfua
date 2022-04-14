package co.com.bancolombia.security.auth0.adapter;

import co.com.bancolombia.security.auth0.entity.LogEntity;
import co.com.bancolombia.security.auth0.repository.LogDataRepository;
import co.com.bancolombia.security.auth0.util.Mapper;
import co.com.bancolombia.security.model.logs.Log;
import co.com.bancolombia.security.model.logs.gateways.LogsRepository;
import co.com.bancolombia.security.model.util.Constant;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Repository
@AllArgsConstructor
public class LogAdapter implements LogsRepository {


    private final LogDataRepository repository;



    @Override
    public List<Log> findOrderByIdLimitTo(int limit) {
        List<LogEntity> logsEntity = (List<LogEntity>)repository.findLogsByOrderNative(limit);
        List<Log> logs = new ArrayList<>();
        logsEntity.forEach((data) ->{
            logs.add(Mapper.toModel(data));
        });
        return logs;
    }


    @Override
    @Transactional
    public Log save(Log log) {
        LogEntity logEntity = Mapper.toEntity(log);
        Integer sizeTable = repository.findSize();
        if(sizeTable > Constant.TOTAL_LOGS){
            repository.dropTable();
        }
        LogEntity response = repository.save(logEntity);
        return  Mapper.toModel(response);
    }

    public String convertJson(Object object){
        String castAsString = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
           castAsString =  objectMapper.writeValueAsString(object);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return castAsString;
    }
}
