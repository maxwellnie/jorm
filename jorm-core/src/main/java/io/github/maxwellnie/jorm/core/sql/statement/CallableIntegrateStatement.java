package io.github.maxwellnie.jorm.core.sql.statement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Maxwell Nie
 */
public class CallableIntegrateStatement extends BaseIntegratedStatement<CallableStatement> {
    
    public CallableIntegrateStatement(Connection connection) {
        super(connection);
    }

    @Override
    public IntegratedStatement<CallableStatement> createStatement(String sql) throws StatementException {
        this.sql = sql;
        validateConnection();
        validateSql();
        try {
            if (configuration != null)
                statement = StatementUtils.callableStatement(connection, sql, configuration.getResultSetType(),
                        configuration.getResultSetConcurrency(), configuration.getHoldability());
            else
                statement = connection.prepareCall(sql);
            applyConfiguration();
        } catch (SQLException e) {
            throw new StatementException("Statement creation failed for SQL: " + sql, e);
        }
        return this;
    }

    @Override
    protected boolean defaultExecute() throws SqlExecutionException {
        try {
            return statement.execute();
        } catch (SQLException e) {
            throw new SqlExecutionException("Statement execution failed for SQL: " + sql, e);
        }
    }
}

