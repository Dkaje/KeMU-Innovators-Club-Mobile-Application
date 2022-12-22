<?php
include('connection.php');
if(mysqli_query($con,"CREATE TABLE payment(
    pay varchar(50) primary key,
    deter varchar(50),
    ses int,
    term varchar(50),
    year varchar(10),
    mpesa varchar(50),
    fees float,
    reg_no varchar(50),
    fullname varchar(50),
    phone varchar(50),
    status float default 0,
    expiry float default 0,
    remarks text,
    date varchar(50)
)")){
    echo 'payment table created successfully';
}else{
    echo 'Failed to create table';
}