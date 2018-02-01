# spring-cloud-config-client for Spring project

spring project(none spring boot)에서 spring-cloud-config-client를 사용 하기 위한 기본 example project 입니다

# Pre-Requirements
- java 1.8
- maven

## MyConfigurableWebApplicationContext
web.xml에 contextClass로 설정 하여 custom context를 load 합니다.

## CloudEnvironment
Spring-Cloud-Config-Server와 연동하여 Cloud-Config의 데이터를 Environment로 생성 합니다

## bootstrap.properties
spring-cloud-config를 위한 설정 파일을 담고 있습니다.
properties 파일은 CloudEnvironment 에 의해 load 됩니다.\
(spring boot의 bootstrap.* 와는 차이가 있습니다. ConfigServicePropertySourceLocator 에 의해 처리 되는 cloud-config 속성만을 담아야 합니다.)