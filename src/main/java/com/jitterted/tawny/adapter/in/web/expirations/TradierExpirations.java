package com.jitterted.tawny.adapter.in.web.expirations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "expirations"
})
public class TradierExpirations {

    @JsonProperty("expirations")
    private Expirations expirations;

    @JsonProperty("expirations")
    public Expirations getExpirations() {
        return expirations;
    }

    @JsonProperty("expirations")
    public void setExpirations(Expirations expirations) {
        this.expirations = expirations;
    }

}
