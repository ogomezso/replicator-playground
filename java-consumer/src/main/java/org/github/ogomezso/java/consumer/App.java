package org.github.ogomezso.java.consumer;

import java.io.FileNotFoundException;

import org.github.ogomezso.java.consumer.config.AppConfig;
import org.github.ogomezso.java.consumer.config.ConfigHandler;
import org.github.ogomezso.java.consumer.infrastructure.kafka.UsersAdapter;
import org.github.ogomezso.java.consumer.infrastructure.kafka.UsersService;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        ConfigHandler configHandler = new ConfigHandler();
        AppConfig config = configHandler.getAppConfig(args[0]);

        UsersAdapter usersAdapter = new UsersService(config);
        usersAdapter.pollMessages();
    }
}
