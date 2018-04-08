
    create table activity (
       id bigint not null auto_increment,
        rate decimal(19,2),
        activity_type_id bigint,
        customer_id bigint,
        employee_id bigint,
        primary key (id)
    );

    create table activity_type (
       id bigint not null auto_increment,
        code varchar(255),
        description varchar(255),
        primary key (id)
    );

    create table customer (
       id bigint not null auto_increment,
        name varchar(255),
        primary key (id)
    );

    create table day (
       id bigint not null auto_increment,
         date date,
        hours double precision,
        note varchar(255),
        activity_id bigint,
        primary key (id)
    );

    create table employee (
       id bigint not null auto_increment,
        last_name varchar(255),
        name varchar(255),
        primary key (id)
    );

    alter table activity 
       add constraint FKr3eyl6gtlxqo765x6p6xbhvnr 
       foreign key (activity_type_id) 
       references activity_type (id);

    alter table activity 
       add constraint FKse269fvw3ft70lc8sf7mp5kpo 
       foreign key (customer_id) 
       references customer (id);

    alter table activity 
       add constraint FKm67enjapuo2hd7irnv1k2fx7t 
       foreign key (employee_id) 
       references employee (id);

    alter table day 
       add constraint FK8l4gq9q8sudslts51x3a4rsnk 
       foreign key (activity_id) 
       references activity (id);
