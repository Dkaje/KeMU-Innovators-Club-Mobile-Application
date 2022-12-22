<?php
include('connection.php');
if(mysqli_query($con,"CREATE TABLE club(
    id int auto_increment primary key,
    club_name varchar(50),
    entry_date varchar(50)
)")){
    echo 'table created successfully';
}else{
    echo 'Failed to create table';
}