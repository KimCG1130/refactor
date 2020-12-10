# refactor
11.MyBatis+DAO
12.Service+Persistence(MyBatis+DAO)=11
13.Service+Persistence(MyBatis+DAO+Spring)>environment(dbPool)관리를 Spring에서 해주기 때문에 pool 삭제 가능
14.Annotation(jUnit으로 단일 테스트)

mybatis-config.xml= <typeAliases>(FQCN) 설정 MyBatis Framework 의 핵심 MetaData
UserMapper= Query(SQL)
jdbc.properties= db사용정보
