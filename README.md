# Cayenne JCache Demo

A small demo showcasing [JCache Cayenne module](https://cayenne.apache.org/docs/4.2/cayenne-guide/#ext-jcache) usage.

This demo uses [Caffeine](https://github.com/ben-manes/caffeine) as a JCache implementation.

## Setup Cayenne JCache

Just add `cayenne-jcache` module to your project, see [`pom.xml`](https://github.com/stariy95/jcache-caffeine-demo/blob/master/pom.xml):

```xml
<dependency>
    <groupId>org.apache.cayenne</groupId>
    <artifactId>cayenne-jcache</artifactId>
    <version>${cayenne.version}</version>
</dependency>
```

## Caffeine Configuration

See [`cache.conf`](https://github.com/stariy95/jcache-caffeine-demo/blob/master/src/main/resources/cache.conf) file for the config example,
[`Main.java`](https://github.com/stariy95/jcache-caffeine-demo/blob/master/src/main/java/org/apache/cayenne/demo/jcache/Main.java#L31) contains code that demonstrates how to use it.  
