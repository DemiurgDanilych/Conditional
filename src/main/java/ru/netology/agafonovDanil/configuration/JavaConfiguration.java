package ru.netology.agafonovDanil.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.agafonovDanil.systemProfile.DevProfile;
import ru.netology.agafonovDanil.systemProfile.ProductionProfile;
import ru.netology.agafonovDanil.systemProfile.SystemProfile;


@Configuration
public class JavaConfiguration {

    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev",havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", matchIfMissing = true, havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
