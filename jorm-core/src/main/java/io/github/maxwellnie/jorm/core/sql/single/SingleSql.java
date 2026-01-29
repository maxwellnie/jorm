package io.github.maxwellnie.jorm.core.sql.single;

import io.github.maxwellnie.jorm.core.sql.Sql;

import java.util.List;

/**
 * @author Maxwell Nie
 */
public interface SingleSql extends Sql {
    String getSqlString();

    List<Object> getSqlParameters();

    default String getSqlInfo() {
        return getSqlString();
    }
}
