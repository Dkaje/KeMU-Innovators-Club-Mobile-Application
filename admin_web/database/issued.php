<?php
include('connection.php');
if(mysqli_query($con,"CREATE TABLE issued(
    iss varchar(50) primary key,
    idi int,
    ses int,
    reg_no varchar(50),
    fullname varchar(50),
    phone varchar(50),
    profile varchar(250),
    signature varchar(250),
    department varchar(50),
    classification varchar(50),
    issue_date varchar(50),
    expiry_date varchar(50),
    category varchar(50),
    status float default 0,
    ended float default 0,
    expiry float default 0,
    entry_date varchar(50)
)")){
    echo 'table created successfully';
}else{
    echo 'Failed to create table';
}