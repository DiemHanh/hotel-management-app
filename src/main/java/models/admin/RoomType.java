package models.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomType {
    private String title;
    private int price;
    private int adultCapacity;
    private int childrenCapacity;
    private String description;


}