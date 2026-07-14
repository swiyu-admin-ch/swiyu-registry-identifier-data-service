# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## 1.3.1

### Changed

- Update maven from 3.9.12 to 3.9.16
- Update org.apache.maven.plugins:maven-surefire-plugin from 3.5.5 to 3.5.6
- Update ch.qos.logback:logback-core from 1.5.36 to 1.5.37
- Update ch.qos.logback:logback-classic from 1.5.36 to 1.5.37
- Update com.diffplug.spotless:spotless-maven-plugin from 3.2.1 to 3.8.0
- Update com.tngtech.archunit:archunit-junit5 from 1.4.1 to 1.4.2

## 1.3.0

### Added

- Add support for local e2e tests

## 1.2.8

### Changed

- Update README.md file

## 1.2.7

### Fixed

- Override logback to 1.5.36 to resolve CVE-2026-13006 (expression injection in logback-core)

## 1.2.6

### Changed

- Updated jeap-spring-boot-parent to 33.11.0 to resolve all HIGH and CRITICAL findings

## 1.2.5

### Fixed

- Add `version` column to `DatastoreEntity` and `DidEntity` to align with write service schema change (EID-6361)

## 1.2.4

### Fixed

- Updated jeap-spring-boot-parent to 33.2.0 to resolve tomcat CVEs

## 1.2.3

### Fixed

- Update jeap-spring-boot-parent to 31.4.0 to resolve CVE CVE-2026-22732

## 1.2.2

### Fix

- Increase jeap-spring-boot-parent version to 30.19.0 to resovle CVE GHSA-72hv-8253-57qq

## 1.2.1

### Changed

- updated postgres from 15.15 to 17.8
- updated maven-surefire-plugin from 3.5.4 to 3.5.5
- updated com.diffplug.spotless:spotless-maven-plugin from 3.1.0 to 3.2.1

## 1.2.0

### Changed

- updated java version to 25 and jeap-spring-boot-parent to 30.16.0

## 1.1.16

### Changed

- updated jeap-spring-boot-parent to 30.15.0
- enabled support for detailed health metrics

## 1.1.15

### Added

- Add sonar plugin

## 1.1.14

### Changed

- Update postgres from 15.14 to 15.15
- Update com.diffplug.spotless:spotless-maven-plugin from 3.0.0 to 3.1.0
- Update ch.admin.bit.jeap:jeap-spring-boot-parent from 27.2.0 to 28.3.0


## 1.1.13

### Changed

- Update maven from 3.9.10 to 3.9.11
- Update maven-wrapper from 3.3.2 to 3.3.4
- Update maven-wrapper from 3.3.2 to 3.3.4
- Update maven-wrapper from 3.3.2 to 3.3.4
- Update postgres from 15.13 to 15.14
- Update org.apache.maven.plugins:maven-surefire-plugin from 3.5.2 to 3.5.4
- Update com.diffplug.spotless:spotless-maven-plugin from 2.45.0 to 3.0.0
- Update ch.admin.bit.jeap:jeap-spring-boot-parent from 26.68.0 to 27.2.0


## 1.1.12

### Other

- Added tests confirming that endpoints only expose datastore entries with status ACTIVE

## 1.1.11

### Changed

- Fix build info reporter to be included into metrics actuator

## 1.1.10

### Changed

- Update maven from 3.9.9 to 3.9.10
- Update com.diffplug.spotless:spotless-maven-plugin from 2.44.4 to 2.45.0
- Update ch.admin.bit.jeap:jeap-spring-boot-parent from 26.50.1 to 26.68.0

## 1.1.9

### Changed

- Downgrade maven-surefire-plugin due to archunit incompatibility

## 1.1.8

### Other

- Update Interface Summaries

## 1.1.7

### Changed

- Update ch.admin.bit.jeap:jeap-spring-boot-parent from 26.50.0 to 26.50.1

## 1.1.7

### Changed

- Downgrade maven-sunfire-plugin due to archunit incompatibility

## 1.1.6

### Changed

- Update maven from 3.9.6 to 3.9.9
- Update postgres from 15.8 to 15.13
- Update org.springframework.security:spring-security-crypto from 6.4.4 to 6.4.5
- Update com.tngtech.archunit:archunit-junit5 from 1.4.0 to 1.4.1
- Update ch.admin.bit.jeap:jeap-spring-boot-parent from 26.41.0 to 26.50.0

## 1.1.5

### Other

- Added spotless plugin

## 1.1.4

### Fixed

- Remove empty external configuration for openAPI

## 1.1.3

### Changed

- Move object to string output to clarify which output to expect in endpoints

## 1.1.2

### Changed

- internal improvements for local development

## 1.1.1

### Changed

- refactored code structure to be based on JEAP, since it is now open-sourced`

## 1.1.0

### Added

- Extending prometheus export with metrics for build

## 1.0.0

- Initial Release 
