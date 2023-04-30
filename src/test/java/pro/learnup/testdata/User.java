package pro.learnup.testdata;

import io.restassured.http.Header;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private ObjectId id;
    private String login;
    private String password;
    private String token;

    public Header getAuthHeader() {
        return new Header("Authorization", "Bearer " + this.getToken());
    }
}
