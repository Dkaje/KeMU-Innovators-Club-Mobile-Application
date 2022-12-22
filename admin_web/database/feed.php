<?php
include('connection.php');
if (mysqli_query($con,"CREATE TABLE feedback(
    fd int auto_increment primary key,
    stud_id varchar(50),
    phone varchar(20),
    name text,
    message varchar(250),
    move text,
    date varchar(50),
    time varchar(50),
    current varchar(50)
)")){
    echo 'created succsesfully';
}else{
    echo 'failed to create table';
}