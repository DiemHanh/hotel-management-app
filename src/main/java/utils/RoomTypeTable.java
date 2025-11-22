package utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomTypeTable {
    TITLE("Title"),
    ADULT_CAPACITY("Adult Capacity"),
    CHILDREN_CAPACITY("Children Capacity"),
    PRICE("Price");

    private final String value;
}
