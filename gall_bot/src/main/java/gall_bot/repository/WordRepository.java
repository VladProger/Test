package gall_bot.repository;

import gall_bot.DBWorker;
import gall_bot.dto.WordDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WordRepository {
    private DBWorker dbWorker;

    public boolean save(WordDto wordDto) {
        try {
            dbWorker = new DBWorker();
            Connection connection=dbWorker.getConnection();
            return connection.createStatement().execute(
                    String.format("insert into words(word_id, word, word_theme) values ('%d', '%s', '%d') ", wordDto.getWordId(),
                            wordDto.getWord(), wordDto.getWordTheme()));

        } catch (SQLException e) {
            System.err.println("Failed to save word with Id = " + wordDto.getWordId() + e);
        }
        return false;
    }

    public String getWordById(Integer id){
        try{
            dbWorker = new DBWorker();
            Connection connection=dbWorker.getConnection();
            ResultSet resultSet= connection.createStatement().executeQuery("SELECT word\n" +
                    "FROM words WHERE word_id = "+id);
            resultSet.next();
            return resultSet.getString("word");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


//public List<WordDto>getAllWords() throws ClassNotFoundException, SQLException {
////    List<WordDto> words = new ArrayList<>();
////    WordDto wordDto = null;
//    dbWorker = new DBWorker();
//    Connection connection = dbWorker.getConnection();
//    Statement stm;
//    stm=connection.createStatement();
//    String sql="SELECT word From words";
//    ResultSet resultSet;
//    resultSet=stm.executeQuery(sql);
//    ArrayList<WordDto>wordList=new ArrayList<>();
//    while (resultSet.next()){
//        WordDto wordDto = new WordDto(resultSet.getString("word"));
//        wordList.add(wordDto);
//    }
//    return wordList;
//
//}



//    public String getList(Integer id){
//        try{
//            dbWorker=new DBWorker();
//            Connection connection=dbWorker.getConnection();
//           // for (int i=0; i<76; i++)
//            ResultSet resultSet= connection.createStatement().executeQuery("SELECT word\n" +
//                    "FROM words WHERE word_id = "+id);
//            resultSet.next();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}
