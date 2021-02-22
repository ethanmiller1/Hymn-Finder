# Common Errors

## Unknown column 'sermon0_.you_tube_infoid' in 'field list'

```warn
java.sql.SQLSyntaxErrorException: Unknown column 'sermon0_.you_tube_infoid' in 'field list'
```

#### Cause

JPA assumes you're following SQL naming conventions in your data tables, converting `YouTubeInfoID` to `you_tube_infoid`.
If you use case sensitive column name in @Column then it will convert camel case to snake case.

There are [several naming strategies](https://docs.jboss.org/hibernate/orm/3.5/javadocs/org/hibernate/cfg/NamingStrategy.html) configured by hibernate as well as the ability to use [custom naming strategies](https://www.baeldung.com/hibernate-naming-strategy).

All Known Implementing Classes:
* DefaultComponentSafeNamingStrategy
* DefaultNamingStrategy
* EJB3NamingStrategy
* ImprovedNamingStrategy

See [Naming Strategies in Hibernate](https://thorben-janssen.com/naming-strategies-in-hibernate-5/) for information on Implicit vs Physical naming strategies.

#### Solution 1

Use Hibernate's [PhysicalNamingStrategyStandardImpl](https://stackoverflow.com/questions/38794253/moving-spring-boot-1-3-to-1-4-hibernate-4-to-5-pascal-case-issues).

```yml
spring:
  jpa:
    hibernate:
      naming:
        implicit-strategy: org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```

#### Solution 2

Print your column name in [lower case](https://stackoverflow.com/questions/50567041/spring-boot-jpa-unknown-column-in-field-list). 
                                      
```java
@Column(name = "youtubeinfoid")
```

Hibernate will read it as one word, and columns in Mysql are case insensitive.