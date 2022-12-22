<?php
$host="localhost";
$user="root";
$pass="";
$con=new mysqli($host,$user,$pass);
if($con->connect_error){
    echo 'Failed to connect';
}
$sql='CREATE DATABASE kemussit';
if($con->query($sql)==TRUE){
    echo 'Database created successfully';
}else{
    echo 'Failed to create database';
}
$con->close();