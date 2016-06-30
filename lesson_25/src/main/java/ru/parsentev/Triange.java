package ru.parsentev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.*;

/**
 * @author parsentev
 * @since 20.06.2016
 */
@Component
@Primary
public class Triange implements Paint {
    private static final Logger log = getLogger(Triange.class);

    @Override
    public void draw() {
        System.out.println("draw triangle");
    }
}