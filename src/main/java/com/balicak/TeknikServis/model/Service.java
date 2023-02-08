package com.balicak.TeknikServis.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Service
{
    private Integer SERVICE_ID;
    private String SERVICE_NAME;
    private Integer DESKTOP;
    private Integer LAPTOP;
    private Integer MAC;
    private Integer DURATION;
}
