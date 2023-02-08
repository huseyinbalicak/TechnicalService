package com.balicak.TeknikServis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Proposal
{
    private Integer PRP_ID;
    @NonNull
    private Integer PRICE;
    @NonNull
    private String NOTE;
    @NonNull
    private Integer PRODUCT_ID;
    @NonNull
    private Integer USER_ID;
    @NonNull
    private Character APPROVAL;
    @NonNull
    private Timestamp PRP_DATE;
}