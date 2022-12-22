<?php
include('connection.php');
if(mysqli_query($con,"CREATE TABLE mover(
    mv int auto_increment primary key,
    evt int,
    ses int,
    term varchar(50),
    theme varchar(250),
    venue varchar(250),
    date varchar(50),
    club varchar(50),
    members float,
    money float,
    summ double,
    pat_id varchar(50),
    pat_phone varchar(50),
    pat_name varchar(50),
    fund varchar(50) default 'Pending',
    closure float default 0,
    entry_date varchar(50)
)")){
    echo 'table created successfully';
}else{
    echo 'Failed to create table';
}