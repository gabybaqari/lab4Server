package com.example.lab4.Order;


import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderInfo {

    private String name;
    private Address address;
    private List<String> phoneNumbers;
    private Date earliestTime;
    private Date latestTime;
    private String doorCode;

}
