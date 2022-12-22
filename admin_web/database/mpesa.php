<?php
include('connection.php');
if(mysqli_query($con,"CREATE TABLE mpesa(
    pay varchar(50) primary key,
    mv int,
    ses int,
    term varchar(50),
    mpesa varchar(50),
    club_amt float,
    utility_token float,
    amount float,
    club varchar(50),
    pat_id varchar(50),
    pat_phone varchar(50),
    pat_name varchar(50),
    closure float default 0,
    entry_date varchar(50)
)")){
    echo 'table created successfully';
}else{
    echo 'Failed to create table';
}