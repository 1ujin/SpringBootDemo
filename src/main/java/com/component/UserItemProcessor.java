package com.component;

import com.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class UserItemProcessor implements ItemProcessor<User, User> {

    private static final Logger log = LoggerFactory.getLogger(UserItemProcessor.class);

    @Override
    public User process(final User user) throws Exception {
        final long id = user.getId();
        final String transformedName = user.getName().toUpperCase(Locale.CHINA);
        final int age = user.getAge();

        final User transformedUser = new User(id, transformedName, age);

        log.info("Converting (" + user + ") into (" + transformedUser + ")");

        return transformedUser;
    }

}