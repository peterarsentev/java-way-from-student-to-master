package ru.parsentev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author parsentev
 * @since 15.06.2016
 */
@Component
public class Sheet {
    private static final Logger log = getLogger(Sheet.class);

    private final Paint paint;

    @Autowired
    public Sheet(Paint paint) {
        this.paint = paint;
    }

    public void doDraw() {
        this.clean();
        this.paint.draw();
    }

    public void clean() {
    }
}