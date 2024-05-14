package com.turkcell.orderservice.core.configuration.events;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderCreatedEvent {
    private int id;
    private LocalDateTime date;



}
