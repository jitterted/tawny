
package com.jitterted.tawny.adapter.in.web.expirations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "expiration"
})
public class Expirations {

    @JsonProperty("expiration")
    private List<Expiration> expiration = null;

    @JsonProperty("expiration")
    public List<Expiration> getExpiration() {
        return expiration;
    }

    @JsonProperty("expiration")
    public void setExpiration(List<Expiration> expiration) {
        this.expiration = expiration;
    }

}
