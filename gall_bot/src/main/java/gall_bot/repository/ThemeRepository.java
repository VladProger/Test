package gall_bot.repository;

import gall_bot.DBWorker;
import gall_bot.dto.ThemeDto;

import java.sql.Connection;
import java.sql.SQLException;

public class ThemeRepository {
    private DBWorker dbWorker;

    public boolean save(ThemeDto themeDto) {
        try {
            dbWorker = new DBWorker();
            Connection connection = dbWorker.getConnection();
            return connection.createStatement().execute(
                    String.format("insert into theme(theme_id, theme_name) values ('%d', '%s')", themeDto.getThemeId(),
                            themeDto.getThemeName()));
        } catch (SQLException e) {
            System.err.println("Failed to save user with Id = " + themeDto.getThemeId() + e);
        }
        return false;
    }
}


