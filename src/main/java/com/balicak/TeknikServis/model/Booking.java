package com.balicak.TeknikServis.model;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Booking
{
    private Integer B_ID;
    @NonNull
    private String NOTE;
    @NonNull
    private Integer USER_ID;
    @NonNull
    private Integer SERVICE_ID;
    private boolean IS_DONE;
    private Date BOOKING_DATE;

}
