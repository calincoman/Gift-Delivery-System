package enums;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Enum for the types of score strategies
 */
public enum ScoreStrategyType {

    @JsonProperty("baby strategy")
    BABY_STRATEGY("baby strategy"),

    @JsonProperty("kid strategy")
    KID_STRATEGY("kid strategy"),

    @JsonProperty("teen strategy")
    TEEN_STRATEGY("teen strategy"),

    @JsonProperty("young adult strategy")
    YOUNG_ADULT_STRATEGY("young adult strategy");

    private final String value;

    ScoreStrategyType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
