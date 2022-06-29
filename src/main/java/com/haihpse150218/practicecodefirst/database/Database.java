package com.haihpse150218.practicecodefirst.database;

import com.haihpse150218.practicecodefirst.models.Product;
import com.haihpse150218.practicecodefirst.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.tools.jar.CommandLine;
//Connect with mysql using jpa
/* Docker
    -d = deamon : chay ngam
    --rm = remove after stop container
    -e environment variables
    -p = map port, "host's port":"container's port"
docker run -d --rm --name mysql-spring-boot-practiceCodeFirst
-e MYSQL_ROOT_PASSWORD=159357
-e MYSQL_USER=root
-e MYSQL_PASSWORD=159357
-e MYSQL_DATABASE=test_db
-p 3309:3306
--volume mysql-spring-boot-practiceCodeFirst:/var/lib/mysql
mysql:latest
mysql  -h localhost -P 3309 --protocol=tcp -u root -p
* */
@Configuration
// chua bean method goi ngay khi ung dung chay ( khoi tao database, evm)
public class Database {
    private static  final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository){
        //logger
        return new CommandLineRunner(){
            @Override
            public void run(String... args) throws Exception {
//                Product producA = new Product("Macbook pro 16 inch",2020, 1512.2, "");
//                Product producB = new Product("Macbook air 32 inch",2021, 2525.0, "");
//                logger.info("insert Data: "+ productRepository.save(producA));
//                logger.info("insert Data: "+ productRepository.save(producB));
            }
        };
    }
}
