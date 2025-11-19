package models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingInformation {
    private LocalDate checkIndate;
    private LocalDate checkOutdate;
    private int adult;
    private int child;
}
