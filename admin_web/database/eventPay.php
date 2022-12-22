<?php
include('connection.php');
if(mysqli_query($con,"CREATE TABLE contri(
    pay varchar(50) primary key,
    ses int,
    term varchar(50),
    mpesa varchar(50),
    money float,
    reg_no varchar(50),
    fullname varchar(50),
    phone varchar(50),
    status float default 0,
    expiry float default 0,
    remarks text,
    date varchar(50)
)")){
    echo 'table created successfully';
}else{
    echo 'Failed to create table';
}