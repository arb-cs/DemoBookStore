package config;

import org.aeonbits.owner.ConfigFactory;

public class ProjectConfig {
    public static BookStoreConfig config = ConfigFactory.create(BookStoreConfig.class, System.getProperties());
}