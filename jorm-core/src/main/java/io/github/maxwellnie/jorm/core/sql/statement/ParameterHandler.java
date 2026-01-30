package io.github.maxwellnie.jorm.core.sql.statement;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Maxwell Nie
 */
@FunctionalInterface
public
interface ParameterHandler<S extends Statement, P> {
    void parameterize(S statement, P parameters) throws SQLException;
}
