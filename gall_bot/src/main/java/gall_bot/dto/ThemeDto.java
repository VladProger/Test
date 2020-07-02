package gall_bot.dto;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ThemeDto {
    private int themeId;
    private String themeName;
}
