package co.com.bancolombia.security.model.logs.gateways;

import co.com.bancolombia.security.model.logs.Log;

import java.util.List;

public interface LogsRepository {

    List<Log> findOrderByIdLimitTo(int limit);

    Log save(Log log);

    String convertJson(Object object);

}
