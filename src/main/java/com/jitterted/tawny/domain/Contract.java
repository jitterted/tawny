package com.jitterted.tawny.domain;

import java.time.OffsetDateTime;

public record Contract(String underlyingSymbol, // Primitive Obsession: could be a record
                       String contractType,     // Primitive Obsession: should be an enum
                       OffsetDateTime expirationDate,
                       int strikePrice) {       // Primitive Obsession: maybe a record?
// TODO: add compact constructor for validation
}
