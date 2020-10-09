package com.jitterted.tawny.adapter.in.web.expirations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "date",
    "strikes"
})
public class Expiration {

    @JsonProperty("date")
    private String date;
    @JsonProperty("strikes")
    private Strikes strikes;

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("strikes")
    public Strikes getStrikes() {
        return strikes;
    }

    @JsonProperty("strikes")
    public void setStrikes(Strikes strikes) {
        this.strikes = strikes;
    }

}
