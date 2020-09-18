package wob.city.database.dao.abstraction;

import wob.city.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface Dao {
    default List<List<Object>> runQuery(String query, List<Object> params) {
        List<List<Object>> results = null;
        try(Connection connection = Database.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                ResultSet resultSet;

                setPreparedStatementParams(preparedStatement, params);

                resultSet = executeQuery(preparedStatement, query);

                results = checkAndReturnResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return results;
    }

    default void setPreparedStatementParams(PreparedStatement preparedStatement, List<Object> params) {
        try {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    default ResultSet executeQuery(PreparedStatement preparedStatement, String query) {
        ResultSet resultSet = null;
        try {
            if (query.toLowerCase().startsWith("update") || query.toLowerCase().startsWith("insert")) {
                preparedStatement.executeUpdate();
            } else {
                preparedStatement.execute();
                resultSet = preparedStatement.getResultSet();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  resultSet;
    }

    default List<List<Object>> checkAndReturnResultSet(ResultSet resultSet) {
        List<List<Object>> results = null;
        try {
            if (resultSet != null) {
                ResultSetMetaData metaData = resultSet.getMetaData();

                results = new ArrayList<>();
                int j = 0;
                while (resultSet.next()) {
                    results.add(new ArrayList<>());
                    for (int k = 1; k <= metaData.getColumnCount(); k++) {
                        results.get(j).add(resultSet.getObject(k));
                    }
                    j++;
                }

                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return results;
    }
}
