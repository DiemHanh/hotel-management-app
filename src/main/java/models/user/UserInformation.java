package models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInformation {
    private String name;
    private String email;
    private String phone;
    private String address;


    @Override
    public String toString() {
        return "User {name='" + name + "', email=" + email + "', phone=" + phone + "', address=" + address + "}";
    }
}
