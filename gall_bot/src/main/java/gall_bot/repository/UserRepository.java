package gall_bot.repository;

import gall_bot.DBWorker;
import gall_bot.dto.UserDto;

import java.sql.Connection;
import java.sql.SQLException;

public class UserRepository {

    private DBWorker dbWorker;

    public boolean save(UserDto userDto) {
        try {
            dbWorker = new DBWorker();
            Connection connection=dbWorker.getConnection();
            return connection.createStatement().execute(
                    String.format("insert into users( user_name, score, chat_id) values ('%d', '%s', '%d', '%d') ",
                            userDto.getUserName(), userDto.getScore(), userDto.getChatId()));

        } catch (SQLException e) {
            System.err.println("Failed to save user with chatId = " + userDto.getChatId() + e);
        }
        return false;
    }



}
