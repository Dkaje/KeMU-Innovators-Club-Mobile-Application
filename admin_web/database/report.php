<?php
include('connection.php');
if(mysqli_query($con,"CREATE TABLE report(
    rep int auto_increment primary key,
    ses int,
    user_id varchar(50),
    username varchar(50),
    userphone varchar(50),
    term varchar(50),
    year varchar(10),
    report varchar(50),
    status float default 0,
    entry_date varchar(50),
    ended float default 0,
    ending varchar(50),
    end_date varchar(50)
)")){
    echo 'table created successfully';
}else{
    echo 'Failed to create table';
}