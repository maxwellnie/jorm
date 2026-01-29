package io.github.maxwellnie.jorm.core.sql.batch;

import io.github.maxwellnie.jorm.core.sql.Sql;

import java.util.List;

/**
 * @author Maxwell Nie
 */
public interface BatchSql extends Sql {
    String getSqlString();

    List<List<Object>> getParamsLists();
}
