<?php
include('connection.php');
if(mysqli_query($con,"CREATE TABLE admin(
    adm_id int auto_increment primary key,
    username varchar(50),
    password varchar(250),
    date timestamp default current_timestamp
)")){
    echo 'Admin table created successfully';
}else{
    echo 'Failed to create table';
}