<?php
include('connection.php');
if(mysqli_query($con,"CREATE TABLE event(
    evt int auto_increment primary key,
    ses int,
    term varchar(50),
    theme varchar(250),
    venue varchar(50),
    land varchar(50),
    site varchar(50),
    date varchar(50),
    time varchar(50), 
    money float,
    member float,
    opened float,
    status float default 0,
    approval float default 0,
    comment varchar(250),
    closure float default 0,
    entry_date varchar(50)
)")){
    echo 'table created successfully';
}else{
    echo 'Failed to create table';
}