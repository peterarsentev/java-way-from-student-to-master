package ru.parsentev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author parsentev
 * @since 20.06.2016
 */
@Component
public class Square implements Paint {
    private static final Logger log = getLogger(Square.class);

    @Override
    public void draw() {
        System.out.println("draw square");
    }
}