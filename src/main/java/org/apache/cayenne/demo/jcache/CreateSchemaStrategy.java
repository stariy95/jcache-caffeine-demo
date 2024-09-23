package org.apache.cayenne.demo.jcache;

import org.apache.cayenne.access.dbsync.CreateIfNoSchemaStrategy;
import org.apache.cayenne.access.dbsync.DefaultSchemaUpdateStrategyFactory;
import org.apache.cayenne.access.dbsync.SchemaUpdateStrategy;

public class CreateSchemaStrategy extends DefaultSchemaUpdateStrategyFactory {

    @Override
    protected SchemaUpdateStrategy createDefaultStrategy() {
        return new CreateIfNoSchemaStrategy();
    }
}
