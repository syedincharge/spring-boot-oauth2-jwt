package com.rizvi.spring.config;

import com.rizvi.spring.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
public class ConfigOAuth2 extends AuthorizationServerConfigurerAdapter {

    private String clientId = "syed";
    private String clientSecret = "syed-secret-key";
    private String privateKey ="-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEowIBAAKCAQEA2r7+L5+KShc7FpPi+akTpD5dK7ENrMo9CQlWjvffMZR8vkze\n" +
            "F2NziKUTYhTXnM9VMA/g9xKt9NlXB93JjJHauw5p6CXTCYw2LNORCxFYNV9kc8xD\n" +
            "67/YdZ6PnFNulF/8YHX3IP+bwuZWypm8qHitExz43Kqfj5LoktWLlJvRF7otB6yg\n" +
            "iSAfae3kZD+00nGS2A4upqUwIyVrmAHGjHWcEvShhecac7Oep9fGLOqp0GCnh7UW\n" +
            "AUIH7bUfl9ycujq5CAalCZKUuKnoQYpKw1h7l6f7xLQhlPc1Zpyi55Zj9xNVF+90\n" +
            "4m1nrEpzAcPvF/B9pICMDd1QBcMEGRau1BPcZQIDAQABAoIBAHTYNrC7mMPw2sg5\n" +
            "oLFZZjuCcIRJJBPbR3UPPuRJdThnp6aDKtL1dZ5VByCKH44MleWBZdq0aKV0fu+d\n" +
            "we5LbroifeHaanscIUgajsyJrEN+PAs63kEMjhRt6SpIyrWI/CeOe75tqwcVwOCh\n" +
            "9EtNgq3F+QzuOyMXBxJ/7PoprSvtcvGqTIAV0Dqd+0ZBIZGJZ33wfUL/K5cat8Ny\n" +
            "PAvhrJ+GNwvoSaqauP6TWuijvSrE3df+kZpqY73Yssl8W5o/XyzkFGtXnMAIVxFn\n" +
            "yB85ChxC12dEiFaDJULiLbR1cx45UTGK4q2oqvgcsshPaXvSgdzLtyIGzzUsVRMW\n" +
            "seeAFNUCgYEA9Mm2l5hfJ0LMhYuLeY7og90785C7C4qbOWgk7J1bv0ygzkVCAoz9\n" +
            "NVmGyFpTFbKmJIiFO7bCzwvkfenVIPLp20my+Y9V8BX1SYBY1iuB04ZazljW8vZN\n" +
            "Yp6M+GDxZu7EeYyUxNzvIxaW5wH0zScMnExZZqG0qj6J/hjxzKKd0/MCgYEA5MPs\n" +
            "Q4cpYSlurrVHGoE5/9b3cbzzcqiCP3sAgUl/Myb/MJIQPJVICrZeM8T0zcnk5HDz\n" +
            "Ekrvj4XPIpbCPrvodxlIKD0M3iVtzVE4Z/Te/Z48C8J2gXHJ0ZE9te3mgdBTP+J2\n" +
            "ygWZs3ahsJIUKsmr6uKnT62JLNThECAwyBXCnEcCgYAzVdolEJ9j7G5X01v8siuw\n" +
            "+8SG3+NaGEdVUrHrrVSAWeGfYIbnYgn4Fj5d5hNmiYCuSLMoQru4EBZrEY7QyiCh\n" +
            "DGOsUpMDNpeqQnBe+eDQK2z/uUHa0iyIche+PxZhL9GkbAcHAg5knWgoKhDctUO/\n" +
            "w4M8Ms1gppzp3PFYqQHQYQKBgA3SqNwLtrxgPIfh/EHlvRNwcdM1j6BF/ljHnegy\n" +
            "ALWejZzptoFyo7/B9T87j28/28zR5l4cmQ0bEMEOSv5vbESGCo4j4jsqF9BoL/hY\n" +
            "G4FHpmkvfumjboZzOD9d+KevZthdXsAmRntJl8uLiBxtwy2WNHXkDn3e/xpyV4A/\n" +
            "kFrRAoGBANrp7gK+goQtLnXkHMibBUx3i+SbGYkLPqdr4R3UNPqw3lnPdqkWlMgG\n" +
            "9HAR5XFuiQPCqH+GrH74zAFxlO/Epgp2QGbsmTNHRHwE5eNB2xwQfHFovK4nact8\n" +
            "rdeMLdCLwr1Eh8CcuQ71CXWmvfcJq9lGo697n4J26SA7yMR7NY++\n" +
            "-----END RSA PRIVATE KEY-----";
    private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2r7+L5+KShc7FpPi+akT\n" +
            "pD5dK7ENrMo9CQlWjvffMZR8vkzeF2NziKUTYhTXnM9VMA/g9xKt9NlXB93JjJHa\n" +
            "uw5p6CXTCYw2LNORCxFYNV9kc8xD67/YdZ6PnFNulF/8YHX3IP+bwuZWypm8qHit\n" +
            "Exz43Kqfj5LoktWLlJvRF7otB6ygiSAfae3kZD+00nGS2A4upqUwIyVrmAHGjHWc\n" +
            "EvShhecac7Oep9fGLOqp0GCnh7UWAUIH7bUfl9ycujq5CAalCZKUuKnoQYpKw1h7\n" +
            "l6f7xLQhlPc1Zpyi55Zj9xNVF+904m1nrEpzAcPvF/B9pICMDd1QBcMEGRau1BPc\n" +
            "ZQIDAQAB\n" +
            "-----END PUBLIC KEY-----";


    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UsersService userDetailsService;

    @Bean
    public JwtAccessTokenConverter tokenEnhancer(){

        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }

    public JwtTokenStore tokenStore(){
        return new JwtTokenStore(tokenEnhancer());
    }

//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
//        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
//                .accessTokenConverter(tokenEnhancer());
//    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception{
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write" )
                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);
    }

//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"),
//                "mypass".toCharArray());
//        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));
//        return converter;
//    }
//
//    @Bean
//    @Primary
//    public DefaultTokenServices tokenServices() {
//        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(tokenStore());
//        defaultTokenServices.setSupportRefreshToken(true);
//        defaultTokenServices.setReuseRefreshToken(false);
//        return defaultTokenServices;
//    }

}
