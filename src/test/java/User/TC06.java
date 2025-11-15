package User;

import TestBase.TestBaseUser;
import lombok.extern.slf4j.Slf4j;
import models.user.Room;
import org.testng.annotations.Test;
import page.user.RoomDetailPage;
import page.user.RoomListPage;

import java.util.List;

@Slf4j
public class TC06 extends TestBaseUser {
    RoomListPage roomListPage = new RoomListPage();
    RoomDetailPage roomDetailPage = new RoomDetailPage();
    Room randomRoom = new Room();

    int randomNumber = faker.number().numberBetween(1, 5);

    @Test
    public void TC06() {
        homePage.openRoomsPage();

        List<Room> listRoom = roomListPage.getAllRooms();
        randomRoom = listRoom.get(randomNumber - 1);

        roomListPage.openRoomDetailByIndex(randomNumber);

        Room roomDetail = new Room(
                roomDetailPage.getRoomName(),
                Float.parseFloat(roomDetailPage.getRoomPrice().substring(1))
        ); // return a room

        sa.assertEquals(randomRoom, roomDetail);
        sa.assertAll();
    }
}
