package io.github.maxwellnie.jorm.core.sql.statement;



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Maxwell Nie
 */
public abstract class BaseIntegratedStatement<T extends Statement> implements IntegratedStatement<T> {
    protected Connection connection;
    protected Configuration configuration;
    protected T statement;
    protected String sql;

    public BaseIntegratedStatement(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public IntegratedStatement<T> handleStatement(StatementHandler<T> handler) throws StatementException {
        validateStatement();
        if (handler != null) {
            try {
                handler.handle(statement);
            } catch (SQLException e) {
                throw new StatementException("Statement handle failed for SQL: " + sql, e);
            }
        }
        return this;
    }

    @Override
    public IntegratedStatement<T> setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    protected void validateStatement() throws StatementException {
        if (statement == null)
            throw new StatementException("Statement is null");
    }

    protected void validateSql() throws StatementException {
        if (sql == null)
            throw new StatementException("SQL is null");
        if (sql.isEmpty())
            throw new StatementException("SQL is empty");
    }

    protected void validateConnection() throws StatementException {
        if (connection == null)
            throw new StatementException("Connection is null");
    }

    @SuppressWarnings("unchecked")
    @Override
    public <P, R> R execute(Executor<T, P> executor, ResultParser<T, P, R> resultParser) throws SqlExecutionException, ResultParserException{
        validateStatement();
        if (executor == null){
            if (resultParser == null)
                throw new SqlExecutionException("Executor and Result parser is null");
            if(defaultExecute())
                return resultParser.parse(statement,null);
            else
                throw new ResultParserException("Cannot parse result for update operation");
        } else {
            try {
                if (resultParser == null)
                    return (R) executor.execute(statement, sql);
                return resultParser.parse(statement, executor.execute(statement, sql));
            } catch (SQLException e) {
                throw new SqlExecutionException("Statement execution failed for SQL: " + sql, e);
            }
        }
    }
    /**
     * @see Statement#execute(String)
     * @see java.sql.PreparedStatement#execute()
     * @return true if the first result is a ResultSet object; false if the first result is an update count or there is no result
     * @throws SqlExecutionException
     */
    protected abstract boolean defaultExecute() throws SqlExecutionException;
    @Override
    public <P> IntegratedStatement<T> parameterize(ParameterHandler<T, P> parametersHandler, P parameters) throws StatementException {
        validateStatement();
        if (parametersHandler != null) {
            try {
                parametersHandler.parameterize(statement, parameters);
            } catch (SQLException e) {
                throw new StatementException("Statement parameterization failed for SQL: " + sql, e);
            }
        }
        return this;
    }

    protected void applyConfiguration() throws SQLException {
        if (configuration != null) {
            validateStatement();
            if (configuration.getQueryTimeout() > 0)
                statement.setQueryTimeout(configuration.getQueryTimeout());
            if (configuration.getFetchSize() > 0)
                statement.setFetchSize(configuration.getFetchSize());
        }
    }

    @Override
    public T getStatement() {
        return statement;
    }

    @Override
    public String getSql() {
        return sql;
    }

    @Override
    public boolean isClosed() throws StatementException {
        if (statement == null)
            return true;
        try {
            return statement.isClosed();
        } catch (SQLException e) {
            throw new StatementException(e);
        }
    }

    @Override
    public void close() throws StatementException {
        if (statement != null) {
            try {
                statement.close();
                statement = null;
            } catch (SQLException e) {
                throw new StatementException(e);
            }
        }
    }
}
