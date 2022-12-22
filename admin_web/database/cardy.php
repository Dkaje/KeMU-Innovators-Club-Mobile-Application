<?php
include('connection.php');
if(mysqli_query($con,"CREATE TABLE cardy_one(
    idi int auto_increment primary key,
    ses int,
    ended float default 0,
    pay varchar(50),
    deter varchar(50),
    category varchar(50),
    reg_no varchar(50),
    fullname varchar(50),
    phone varchar(50),
    profile varchar(250),
    signature varchar(250),
    department varchar(50),
    classification varchar(50),
    email varchar(50),
    finsta float default 0,
    secsta float default 0,
    lost float default 0,
    date varchar(50)
)")){
    echo 'card table created successfully';
}else{
    echo 'Failed to create table';
}