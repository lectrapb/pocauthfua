package co.com.bancolombia.security.auth0.repository;


import co.com.bancolombia.security.auth0.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogDataRepository extends JpaRepository<LogEntity, Long> {

    @Query(value = " SELECT *  FROM logs ORDER BY log_id DESC LIMIT:limit", nativeQuery = true)
    List<LogEntity> findLogsByOrderNative(@Param("limit") int limit);

    @Query(value ="SELECT   COUNT(log_id) FROM logs", nativeQuery = true)
    Integer findSize();

    @Modifying
    @Query(value = "TRUNCATE TABLE logs", nativeQuery = true)
     int dropTable();

}
