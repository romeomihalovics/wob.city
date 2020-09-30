package wob.city.database.dao.abstraction;

import wob.city.database.Database;
import wob.city.database.dto.QueryDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface Dao {
    default List<HashMap<String, Object>> runQuery(QueryDto queryDto) {
        List<HashMap<String, Object>> results = null;
        try(Connection connection = Database.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(queryDto.getQuery())){
                ResultSet resultSet;

                setPreparedStatementParams(preparedStatement, queryDto.getParams());

                resultSet = executeQuery(preparedStatement, queryDto.getQuery());

                results = checkAndReturnResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return results;
    }

    default void setPreparedStatementParams(PreparedStatement preparedStatement, List<Object> params) throws SQLException {
        int i = 1;
        for (Object param : params) {
            if (param instanceof String) {
                preparedStatement.setString(i, (String) param);
            } else if (param instanceof Integer) {
                preparedStatement.setInt(i, (Integer) param);
            } else if (param instanceof Boolean) {
                preparedStatement.setBoolean(i, (Boolean) param);
            } else if (param instanceof Double) {
                preparedStatement.setDouble(i, (Double) param);
            }
            i++;
        }
    }

    default ResultSet executeQuery(PreparedStatement preparedStatement, String query) throws SQLException {
        ResultSet resultSet = null;

        if (query.toLowerCase().startsWith("update") || query.toLowerCase().startsWith("insert")) {
            preparedStatement.executeUpdate();
        } else {
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
        }

        return  resultSet;
    }

    default List<HashMap<String, Object>> checkAndReturnResultSet(ResultSet resultSet) throws SQLException {
        List<HashMap<String, Object>> results = null;

        if (resultSet != null) {
            ResultSetMetaData metaData = resultSet.getMetaData();

            results = new ArrayList<>();
            int j = 0;
            while (resultSet.next()) {
                results.add(new HashMap<>());
                for (int k = 1; k <= metaData.getColumnCount(); k++) {
                    results.get(j).put(metaData.getColumnName(k), resultSet.getObject(k));
                }
                j++;
            }

            resultSet.close();
        }

        return results;
    }
}
