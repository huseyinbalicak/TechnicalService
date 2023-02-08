package com.balicak.TeknikServis.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class SaleLog
{
    private Integer SALE_LOG_ID;
    @NonNull
    private Integer USER_ID;
    @NonNull
    private Integer SALE_ID;
    @NonNull
    private String INFO;
    @NonNull
    private LocalDateTime SALE_LOG_DATE;
    @NonNull
    private String CREDIT_CARD;

}
