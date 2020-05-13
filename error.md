com.sun.xml.internal.bind.v2.model.core不存在

这是因为jdk中的rt.jar包与maven不匹配导致的，maven识别不到这个rt.jar包，解决办法：

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <source>1.8</source>
        <target>1.8</target>
        <compilerArguments>
            <verbose />
            <bootclasspath>${java.home}/lib/rt.jar</bootclasspath>
        </compilerArguments>
    </configuration>
</plugin>