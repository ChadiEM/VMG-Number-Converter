# Description
Given a folder containing VMG format SMSs, the aim of this tool is to facilitate the task of converting numbers without country codes to numbers with country codes for archiving reasons.

Although it might only be useful to 0.000000001% of the world, I'm putting this here anyway, in case it helps someone.

# Compilation
```mvn clean compile assembly:single```

# Runtime
Run with:
```
java -jar inappender-1.0-SNAPSHOT-jar-with-dependencies.jar
    <source dir>
    <target dir>
    <from-pattern> <to-pattern>
    <from-pattern> <to-pattern>
    ...
```

For example,
```
java -jar inappender-1.0-SNAPSHOT-jar-with-dependencies.jar
    /home/chadi/Messages/predefsent/
    /home/chadi/ramdisk/
    "TEL:(70\d{6})" "TEL:+961$1"
```
Will transform all numbers starting with 70 and having 6 digits to the same number prefixed with +961.


# License
Code released under the MIT license.