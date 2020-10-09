package com.jitterted.tawny.domain;

import java.time.LocalDate;

public record Contract(String underlyingSymbol, // Primitive Obsession: could be a record
                       String contractType,     // Primitive Obsession: should be an enum
                       LocalDate expirationDate,
                       int strikePrice) {       // Primitive Obsession: maybe a record?
// TODO: add compact constructor for validation
}
