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

## No profiles found in config file

```error
[ERROR] Failed to execute goal net.revelc.code.formatter:formatter-maven-plugin:2.13.0:format (etds-format-code) on project HymnFinder: No profiles found in config file -> [Help 1]
```

#### Solution

Wrap the settings in <Profile> tag.

Visit the [FormattingModelBuilder](https://upsource.jetbrains.com/idea-ce/file/idea-ce-4b94ba01122752d7576eb9d69638b6e89d1671b7/platform/code-style-api/src/com/intellij/formatting/FormattingModelBuilder.java?_ga=2.144095349.1972292233.1613953362-887384512.1579637476) implemented by the plugin.

## Fatal error compiling: invalid flag: --release

```error
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile) on project HymnFinder: Fatal error compiling: invalid flag: --release -> [Help 1]
```

#### Cause

When maven builds your project, it uses the default system JDK
(run `mvn -v` to [check which Java version](https://stackoverflow.com/questions/49105941/intellij-maven-project-fatal-error-compiling-invalid-flag-release) Maven is using).
JDK8 does
[not know](https://stackoverflow.com/questions/62298577/fatal-error-compiling-invalid-flag-release-java-8)
the option `--release`.

#### Solution 1

Temporarily change the value of your JAVA_HOME environment variable.

#### Solution 2

Specify [multiple Java versions](https://stackoverflow.com/questions/2503658/specify-jdk-for-maven-to-use) in your `.m2/settings.xml`.

## Cannot find symbol var

```log
Error:(145, 7) java: cannot find symbol
  symbol:   class var
  location: class org.improving.HymnFinder
```

#### Solution

```txt
Settings -> Build, Execution, Deployment -> Compiler -> Java Compiler
```

Then raise the Project Bytecode version to 11.

## No provider for Router!

```log
main.ts:12 NullInjectorError: R3InjectorError(AppModule)[Router -> Router -> Router]: 
  NullInjectorError: No provider for Router!
```

#### Solution

Add your list of routes to the RouterModule.

```typescript
const routes: Routes = [
  {path: 'sermons', component: SermonListComponent},
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
  ]
})
```

## Property 'id' has no initializer and is not definitely assigned in the constructor.

```log
Property 'id' has no initializer and is not definitely assigned in the constructor.
```

#### Solution 1

`Alt + Insert` to generate a constructor.

#### Solution 2

Disable "Strict Class Initialization".