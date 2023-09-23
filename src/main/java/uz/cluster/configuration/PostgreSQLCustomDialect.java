package uz.cluster.configuration;

import org.hibernate.dialect.PostgreSQL10Dialect;

import java.sql.Types;

public class PostgreSQLCustomDialect extends PostgreSQL10Dialect {
    public PostgreSQLCustomDialect() {
        super();
        this.registerHibernateType(
                Types.STRUCT, String.class.getName()
        );
//        this.registerHibernateType();
    }
}
