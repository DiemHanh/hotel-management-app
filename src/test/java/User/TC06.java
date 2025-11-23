package User;

import TestBase.TestBaseUser;
import Listeners.TestListener;
import models.user.Room;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.user.RoomDetailPage;
import page.user.RoomListPage;

import java.util.List;

@Listeners(TestListener.class)
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

        Room roomDetail = roomDetailPage.getRoomDetail();

        sa.assertEquals(randomRoom, roomDetail);
        sa.assertAll();
    }
}
