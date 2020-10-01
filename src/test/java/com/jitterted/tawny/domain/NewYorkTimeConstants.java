package com.jitterted.tawny.domain;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class NewYorkTimeConstants {
  private static final ZoneOffset NYC_ZONE_OFFSET = ZoneId.of("America/New_York").getRules().getOffset(LocalDateTime.now());
  public static final OffsetDateTime OCT_16_2020 = OffsetDateTime.of(2020, 10, 16, 16, 0, 0, 0, NYC_ZONE_OFFSET);
}
