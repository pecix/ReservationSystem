package pl.petrusiewicz.ReservationSystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${config.minBookingTime}")
    private int minBookingTime;
    @Value("${config.maxBookingTime}")
    private int maxBookingTime;

    public int getMinBookingTime() {
        return minBookingTime;
    }

    public int getMaxBookingTime() {
        return maxBookingTime;
    }

}
