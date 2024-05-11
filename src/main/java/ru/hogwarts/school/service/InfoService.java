package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
//@Profile("test")
public class InfoService {

    @Value("${server.port}")
    private String currentPort;

    private final Logger logger = LoggerFactory.getLogger(InfoService.class);

    public String getCurrentPort() {
        logger.info("current port: {}", currentPort);
        return currentPort;
    }


    public Integer getSum() {
        logger.info("был вызван метода подсчета суммы");
        return Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, Integer::sum);
    }
}
