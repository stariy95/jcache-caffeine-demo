package org.apache.cayenne.demo.jcache;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.access.dbsync.SchemaUpdateStrategyFactory;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.datasource.DataSourceBuilder;
import org.apache.cayenne.demo.jcache.model.Artist;
import org.apache.cayenne.jcache.JCacheModule;
import org.apache.cayenne.query.ObjectSelect;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        ServerRuntime runtime = createRuntime();
        ObjectContext context = runtime.newContext();

        // Running query with a local cache managed by the Caffeine
        ObjectSelect.query(Artist.class)
                .localCache()
                // could specify a cache group name, to use specific settings instead of defaults
                //.localCache("testGroup")
                .select(context);
    }

    private static ServerRuntime createRuntime() {
        ServerRuntime runtime = ServerRuntime.builder()
                // providing configuration for the Caffeine
                // see config reference: https://github.com/ben-manes/caffeine/blob/master/jcache/src/main/resources/reference.conf
                .addModule(b -> JCacheModule.contributeJCacheProviderConfig(b, "classpath:cache.conf"))

                // setting up demo parts
                .addConfig("cayenne-project.xml")
                .addModule(b -> b.bind(SchemaUpdateStrategyFactory.class).to(CreateSchemaStrategy.class))
                .dataSource(DataSourceBuilder
                        .url("jdbc:derby:derbyDB;create=true")
                        .driver(org.apache.derby.jdbc.EmbeddedDriver.class.getName())
                        .pool(1, 5)
                        .build())
                .build();
        // insert some demo data
        insertData(runtime);
        return runtime;
    }

    private static void insertData(ServerRuntime runtime) {
        ObjectContext context = runtime.newContext();

        Artist artist = context.newObject(Artist.class);
        artist.setName("Test 1");
        artist.setDateOfBirth(LocalDate.of(1900, 1, 1));

        context.commitChanges();
    }
}
