package config;

import org.aeonbits.owner.ConfigFactory;

public class BookStore {
    public static BookStoreConfig config = ConfigFactory.create(BookStoreConfig.class, System.getProperties());
}