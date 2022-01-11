package enums;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Enum for the age category
 */
public enum AgeCategory {

    @JsonProperty("baby")
    BABY("baby"),

    @JsonProperty("kid")
    KID("kid"),

    @JsonProperty("teen")
    TEEN("teen"),

    @JsonProperty("young adult")
    YOUNG_ADULT("young adult");

    private final String value;

    AgeCategory(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
