<?php
include('connection.php');
if(mysqli_query($con,"CREATE TABLE summer(
    sm int auto_increment primary key,
    evt int,
    ses int,
    term varchar(50),
    theme varchar(250),
    venue varchar(50),
    land varchar(50),
    site varchar(50),
    date varchar(50),
    time varchar(50),
    money float, 
    reg_no varchar(50),
    fullname varchar(50),
    phone varchar(50),
    status float default 0,
    comment varchar(250),
    pay float default 0,
    approval float default 0,
    org float default 0,
    org_no varchar(50),
    org_phone varchar(50),
    org_name varchar(50),
    club varchar(50) default 'Pending',
    pat float default 0,
    closure float default 0,
    entry_date varchar(50)
)")){
    echo 'table created successfully';
}else{
    echo 'Failed to create table';
}