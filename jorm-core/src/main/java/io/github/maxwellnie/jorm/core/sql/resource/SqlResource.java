package io.github.maxwellnie.jorm.core.sql.resource;

import java.io.Serializable;

/**
 * @author Maxwell Nie
 */
public interface SqlResource extends Serializable {
    String getSql();
    int getSqlKind();
    int SINGLE = 0;
    int BATCH = 1;
    int MULTIPLE_BATCH = 2;
}
