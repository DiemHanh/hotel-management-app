package models.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomType {
    private String title;
    private double price;
    private String description;
    private int adultCapacity;
    private int childrenCapacity;

    @Override
    public String toString() {
        return "RoomType{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", adultCapacity=" + adultCapacity +
                ", childrenCapacity=" + childrenCapacity +
                '}';
    }
}
