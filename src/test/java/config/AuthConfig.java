package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
    "system:properties",
    "classpath:config/user.properties"
})

public interface AuthConfig extends Config {
    String name();
    String password();
}