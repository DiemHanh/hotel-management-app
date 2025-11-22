package utils;

import com.github.javafaker.Faker;
import models.admin.Room;
import models.admin.RoomType;
import models.user.BookingInformation;
import models.user.UserInformation;

public class FakerData {
    private static Faker faker = new Faker();

    public static Room generateRandomRoom() {
        int roomNumber = faker.number().numberBetween(10000, 99999);
        int floor = faker.number().numberBetween(1, 10);
        String description = faker.lorem().sentence(10);
        return new Room(roomNumber, null, floor, description, null);
    }

    public static RoomType generateRandomRoomType() {
        String title = String.format("%s %s %d",
                faker.address().cityName(),
                faker.options().option("Deluxe Suite", "Family Room", "Presidential Villa", "Ocean View Room"),
                faker.number().numberBetween(1, 9999));
        int price = faker.number().numberBetween(50, 500);
        int adultNumber = faker.number().numberBetween(1, 4);
        int childrenNumber = faker.number().numberBetween(1, 5);
        String description = faker.lorem().sentence(3);
        return new RoomType(title, price, adultNumber, childrenNumber, description);
    }

    public static UserInformation generateRandomUser() {
        return new UserInformation(
                faker.name().fullName(),
                faker.name().username() + "@yopmail.com",
                faker.numerify("0#########"),
                faker.address().streetAddress()
        );
    }

    public static BookingInformation generateBookingInformation() {
        return new BookingInformation(
                DateUtils.getToday(),
                DateUtils.getFollowingDay(DateUtils.getToday()),
                faker.number().numberBetween(1, 3),
                faker.number().numberBetween(1, 3)
        );
    }
}

