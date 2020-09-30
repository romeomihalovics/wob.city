package wob.city.database.dao.abstraction;

import wob.city.database.Database;
import wob.city.database.dto.QueryDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Dao {
    default boolean runTransaction(List<QueryDto> queries) {
        boolean successful = false;
        try(Connection connection = Database.getConnection()) {
            connection.setAutoCommit(false);
            try {
                HashMap<QueryDto, PreparedStatement> preparedStatements = setPreparedStatements(queries, connection);
                successful = executeTransaction(preparedStatements, connection);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return successful;
    }

    default HashMap<QueryDto, PreparedStatement> setPreparedStatements(List<QueryDto> queries, Connection connection) throws SQLException {
        HashMap<QueryDto, PreparedStatement>preparedStatements = new HashMap<>();
        for(QueryDto query : queries) {
            preparedStatements.put(query, connection.prepareStatement(query.getQuery()));
        }
        return preparedStatements;
    }

    default boolean executeTransaction(HashMap<QueryDto, PreparedStatement> preparedStatements, Connection connection) throws SQLException {
       boolean successful = false;
       Savepoint savepoint = connection.setSavepoint();
       try {
           commitTransactionEntries(preparedStatements, connection);
           successful = true;
       } catch (SQLException throwables) {
           connection.rollback(savepoint);
           throwables.printStackTrace();
       } finally {
           closeTransactionStatements(preparedStatements);
       }
        return successful;
    }

    default void commitTransactionEntries(HashMap<QueryDto, PreparedStatement> preparedStatements, Connection connection) throws SQLException {
        for (Map.Entry<QueryDto, PreparedStatement> entry : preparedStatements.entrySet()) {
            setPreparedStatementParams(entry.getValue(), entry.getKey().getParams());
            if((Integer) executeQuery(entry.getValue(), entry.getKey().getQuery()) == 0){
                throw new SQLException("No rows affected");
            }
            connection.commit();
        }
    }

    default void closeTransactionStatements(HashMap<QueryDto, PreparedStatement> preparedStatements) throws SQLException {
        for (Map.Entry<QueryDto, PreparedStatement> entry : preparedStatements.entrySet()) {
            entry.getValue().close();
        }
    }

    default List<HashMap<String, Object>> runQuery(QueryDto queryDto) {
        List<HashMap<String, Object>> results = null;
        try(Connection connection = Database.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(queryDto.getQuery())){
                ResultSet resultSet = null;

                setPreparedStatementParams(preparedStatement, queryDto.getParams());

                Object result = executeQuery(preparedStatement, queryDto.getQuery());
                if(result instanceof ResultSet) {
                    resultSet = (ResultSet) result;
                }

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

    default Object executeQuery(PreparedStatement preparedStatement, String query) throws SQLException {
        Object result;

        if (query.toLowerCase().startsWith("update") || query.toLowerCase().startsWith("insert")) {
            result = preparedStatement.executeUpdate();
        } else {
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
        }

        return  result;
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
