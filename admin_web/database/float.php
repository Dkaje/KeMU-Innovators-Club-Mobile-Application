<?php
include('connection.php');
if(mysqli_query($con,"CREATE TABLE account(
    inte int auto_increment primary key,
    fresh double default 0,
    renewal double default 0,
    upgrade double default 0,
    contribute double default 0,
    event_pay double default 0,
    withdraw double default 0,
    whole double default 0,
    bal double default 0,
    entry varchar(50),
    last varchar(50)
)")){
    echo 'table created successfully';
}else{
    echo 'Failed to create table';
}