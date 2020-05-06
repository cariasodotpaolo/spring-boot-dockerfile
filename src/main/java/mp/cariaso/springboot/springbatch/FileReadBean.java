package mp.cariaso.springboot.springbatch;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileReadBean {

    private String fullName;
    private String emailAddress;

    public FileReadBean(String fullName, String emailAddress) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
    }
}
