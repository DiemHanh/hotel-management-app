package User;

import TestBase.TestBaseUser;
import lombok.extern.slf4j.Slf4j;
import models.user.BookingInformation;
import models.user.CreditCard;
import models.user.UserInformation;
import org.testng.annotations.Test;
import page.user.BookingPage;
import page.user.PaymentPage;
import page.user.RoomDetailPage;
import page.user.RoomListPage;
import utils.Constant;
import utils.FakerData;

@Slf4j
public class TC04 extends TestBaseUser {
    RoomListPage roomListPage = new RoomListPage();
    RoomDetailPage roomDetailPage = new RoomDetailPage();
    BookingPage bookingPage = new BookingPage();
    PaymentPage paymentPage = new PaymentPage();

    UserInformation userInfo = FakerData.generateRandomUser();
    BookingInformation bookingInfo = FakerData.generateBookingInformation();
    CreditCard validCard = Constant.DEFAULT_CREDIT_CARD;

    private CreditCard wrongNumberCreditCard = new CreditCard(
            faker.finance().creditCard().replaceAll("-", ""),
            faker.name().fullName(),
            faker.number().digits(4),
            faker.number().digits(3)
    );

    private CreditCard wrongNameCreditCard = new CreditCard(
            validCard.getCardNumber(),
            faker.name().fullName(),
            faker.number().digits(4),
            faker.number().digits(3)
    );

    private CreditCard wrongDateCreditCard = new CreditCard(
            validCard.getCardNumber(),
            validCard.getCardName(),
            faker.number().digits(4),
            faker.number().digits(3)
    );

    private CreditCard wrongCVVCreditCard = new CreditCard(
            validCard.getCardNumber(),
            validCard.getCardName(),
            validCard.getCardDate(),
            faker.number().digits(3)
    );

    private void verifyPaymentError(CreditCard card, String expectedError) {
        paymentPage.submitPaymentBooking(card);
        sa.assertEquals(paymentPage.getErrorMsg(), expectedError);
    }

    @Test
    public void TC04() {
        homePage.openRoomsPage();

        // open random room at detail page
        roomListPage.openRoomDetailByIndex(faker.number().numberBetween(1, 6));

        // enter booking info
        roomDetailPage.submitBookingInformation(bookingInfo);

        // enter personal info and checkbox
        bookingPage.submitUserInfo(userInfo);

        log.info("Verify credit card not exists");
        verifyPaymentError(
                wrongNumberCreditCard,
                "CreditCard not exists !!!"
        );

        log.info("Verify input wrong name");
        verifyPaymentError(
                wrongNameCreditCard,
                "Wrong CreditCard information !!!"
        );

        log.info("Verify input wrong date");
        verifyPaymentError(
                wrongDateCreditCard,
                "Wrong CreditCard information !!!"
        );

        log.info("Verify input wrong cvv");
        verifyPaymentError(
                wrongCVVCreditCard,
                "Wrong CreditCard information !!!"
        );

        sa.assertAll();
    }
}
