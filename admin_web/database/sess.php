<?php
include('connection.php');
if(mysqli_query($con,"CREATE TABLE session(
    id int auto_increment primary key,
    term varchar(50),
    year varchar(10),
    report varchar(50),
    status float default 0,
    entry_date varchar(50),
    lec_id varchar(50),
    lec_name varchar(50),
    lec_phone varchar(50),
    ended float default 0,
    ending varchar(50),
    lec_ide varchar(50),
    lec_namee varchar(50),
    lec_phonee varchar(50),
    end_date varchar(50)
)")){
    echo 'table created successfully';
}else{
    echo 'Failed to create table';
}