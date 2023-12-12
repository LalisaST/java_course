package edu.hw10.task1;

public record MyRecord(
    @Max("1")
    @Min("0")
    int intValue,

    @Min("2")
    double doubleValue,

    @NotNull
    String stringValue

) {
}
