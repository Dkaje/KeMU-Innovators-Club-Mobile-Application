<?php
include('connection.php');
if(mysqli_query($con,"CREATE TABLE walk(
    wk int auto_increment primary key,
    evt int,
    ses int,
    term varchar(50),
    theme varchar(250),
    venue varchar(250),
    date varchar(50),
    club varchar(50),
    reg_no varchar(50),
    fullname varchar(50),
    phone varchar(50),
    org_no varchar(50),
    org_phone varchar(50),
    org_name varchar(50),
    pat_id varchar(50),
    pat_phone varchar(50),
    pat_name varchar(50),
    end varchar(50) default 'Pending',
    closure float default 0,
    entry_date varchar(50)
)")){
    echo 'table created successfully';
}else{
    echo 'Failed to create table';
}