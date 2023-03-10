package hello.api.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminSentenceSuccess {

    private int status;
    private AdminSentenceDto adminSentenceDto;
    private List<AdminSentenceDto> list;
    private List<String> grammarList;
    private List<String> situationList;
}
