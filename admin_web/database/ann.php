<?php
include('connection.php');
if(mysqli_query($con,"CREATE TABLE announce(
    ann int auto_increment primary key,
    ses int,
    term varchar(50),
    subject varchar(50),
    notice varchar(250),
    closure float default 0,
    entry_date varchar(50)
)")){
    echo 'table created successfully';
}else{
    echo 'Failed to create table';
}