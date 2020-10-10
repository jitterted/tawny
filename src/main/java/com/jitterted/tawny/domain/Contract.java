package com.jitterted.tawny.domain;

import java.time.LocalDate;

public record Contract(String underlyingSymbol, // Primitive Obsession: could be a record
                       String contractType,     // Primitive Obsession: should be an enum
                       LocalDate expirationDate,// Primitive Obsession: could be Expiration VO
                       int strikePrice) {       // Primitive Obsession: maybe a record?
// TODO: add compact constructor for validation
}
