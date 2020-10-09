
package com.jitterted.tawny.adapter.in.web.expirations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "strike"
})
public class Strikes {

    @JsonProperty("strike")
    private List<BigDecimal> strike = null;

    @JsonProperty("strike")
    public List<BigDecimal> getStrike() {
        return strike;
    }

    @JsonProperty("strike")
    public void setStrike(List<BigDecimal> strike) {
        this.strike = strike;
    }

}
