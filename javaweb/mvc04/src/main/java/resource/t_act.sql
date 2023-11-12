drop table t_act;
create table t_act
(
    id      bigint primary key auto_increment,
    actno   varchar(255) not null default "",
    balance decimal(10, 2)
);

insert into t_act values(1, "act001", 50000),(null,"act002",0);