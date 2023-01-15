package br.com.siecola.aws_project02.repositories;

import br.com.siecola.aws_project02.models.ProductEventLog;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface ProducEventsLogRepository extends DynamoDBCrudRepository<ProductEventLog, String> {

    List<ProductEventLog> findAllById(String code);
}