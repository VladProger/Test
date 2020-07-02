package gall_bot.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userName;
    private int score;
    private Long chatId;
    private String text;

    public UserDto(String text, Long chatId){
        this.text = text;
        this.chatId = chatId;
    }

}
