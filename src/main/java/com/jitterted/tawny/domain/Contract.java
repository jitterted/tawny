package com.jitterted.tawny.domain;

import java.time.OffsetDateTime;

public record Contract(String underlyingSymbol,
                       String contractType,
                       OffsetDateTime expirationDate,
                       int strikePrice) {
}
