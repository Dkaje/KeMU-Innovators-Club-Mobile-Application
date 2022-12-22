<?php
include('connection.php');
if(mysqli_query($con,"CREATE TABLE cardy(
    id int auto_increment primary key,
    category varchar(50),
    fees float,
    description varchar(250),
    date varchar(50)
)")){
    echo 'card table created successfully';
}else{
    echo 'Failed to create table';
}