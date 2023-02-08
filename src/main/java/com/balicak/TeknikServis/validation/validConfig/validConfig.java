package com.balicak.TeknikServis.validation.validConfig;

import com.balicak.TeknikServis.validation.BookingValidator;
import com.balicak.TeknikServis.validation.ProposalValidator;
import com.balicak.TeknikServis.validation.UserValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class validConfig {



    @Bean
    public UserValidator userValidator() {
        return new UserValidator();
    }
    @Bean
    public BookingValidator bookingValidator()
    {
        return new BookingValidator();
    }
    @Bean
    public ProposalValidator proposalValidator()
    {
        return new ProposalValidator();
    }


}