# The Root POM For Maven Plugin

This POM is designed to be the topmost parent (root) of the pom inheritance hierarchy.

Its primary role is to hold shared, generic settings, usable by any type of derived project.

Additionally, it has some support for releases performed by `maven-releases-plugin` (aka MRP), helping the `pom.xml` files
to avoid direct references to concrete Nexus (or, Maven Repository Manager) instances.