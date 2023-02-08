package com.balicak.TeknikServis.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale
{
    private Integer SALE_ID;
    @NonNull
    private Integer PRICE;
    @NonNull
    private String NOTE;
    @NonNull
    private Integer PRODUCT_ID;
    @NonNull
    private boolean IS_SOLD;

}
