package wob.city.database.dao.abstraction;

import wob.city.database.Database;
import wob.city.database.dto.ParameterDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface Dao {
    default List<List<Object>> runQuery(String query, List<ParameterDto> params) {
        List<List<Object>> results = null;
        try(Connection connection = Database.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                ResultSet resultSet = null;

                int i = 1;
                for (ParameterDto param : params) {
                    if(param.getType().equals("String")){
                        preparedStatement.setString(i, (String) param.getValue());
                    }else if(param.getType().equals("Integer")){
                        preparedStatement.setInt(i, (Integer) param.getValue());
                    }
                    i++;
                }

                if (query.toLowerCase().startsWith("update") || query.toLowerCase().startsWith("insert")
                        || query.toLowerCase().startsWith("delete") || query.toLowerCase().startsWith("truncate")) {
                    preparedStatement.executeUpdate();
                } else {
                    preparedStatement.execute();
                    resultSet = preparedStatement.getResultSet();
                }

                if (resultSet != null) {
                    ResultSetMetaData metaData = resultSet.getMetaData();

                    results = new ArrayList<>();
                    int j = 0;
                    while(resultSet.next()) {
                        results.add(new ArrayList<>());
                        for (int k = 1; k <= metaData.getColumnCount(); k++) {
                            results.get(j).add(resultSet.getObject(k));
                        }
                        j++;
                    }

                    resultSet.close();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return results;
    }
}
