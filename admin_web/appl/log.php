<?php
include('../database/connection.php');
$reg_no = $_POST['reg_no'];
$password = md5($_POST['password']);
$theme = $_POST['theme'];
if($theme=='stu'){
$res=mysqli_query($con,"SELECT * from student where stud_id='$reg_no' and password='$password'");
if(mysqli_num_rows($res)===1){
    $row=mysqli_fetch_array($res);
    if($row['status']==0){
        $result['success']=0;
        $result['message']='Kindly wait for account approval';
    }elseif($row['status']==2){
        $result=array();
        $result['lion']=array();
        $tetra['remarks']=$row['remarks'];
        $result['success']=2;
        $result['message']='Account Rejected';
        array_push($result['lion'],$tetra);
    }else{
        $result=array();
        $result['lion']=array();
        $tetra['stud_id']=$row['stud_id'];
        $tetra['fname']=$row['fname'];
        $tetra['lname']=$row['lname'];
        $tetra['gender']=$row['gender'];
        $tetra['phone']=$row['phone'];
        $tetra['email']=$row['email'];
        $tetra['department']=$row['department'];
        $tetra['classification']=$row['classification'];
        $tetra['role']='Role';
        $tetra['date']=$row['date'];
        $result['success']=1;
        $result['message']='Login was successful';
        array_push($result['lion'],$tetra);
    }

}else{
$result['success']=0;
$result['message']='Invalid credentials';
}
}elseif($theme=='pat'){
    $res=mysqli_query($con,"SELECT * from official where stud_id='$reg_no' and password='$password' and role='Patron'");
    if(mysqli_num_rows($res)===1){
        $row=mysqli_fetch_array($res);
        if($row['status']==0){
            $result['success']=0;
            $result['message']='Kindly wait for account approval';
        }elseif($row['status']==2){
            $result=array();
            $result['lion']=array();
            $tetra['remarks']=$row['remarks'];
            $result['success']=2;
            $result['message']='Account Rejected';
            array_push($result['lion'],$tetra);
        }else{
            $result=array();
            $result['lion']=array();
            $tetra['stud_id']=$row['stud_id'];
            $tetra['fname']=$row['fname'];
            $tetra['lname']=$row['lname'];
            $tetra['gender']=$row['gender'];
            $tetra['phone']=$row['phone'];
            $tetra['email']=$row['email'];
            $tetra['department']=$row['department'];
            $tetra['classification']=$row['classification'];
            $tetra['role']=$row['role'];
            $tetra['date']=$row['date'];
            $result['success']=1;
            $result['message']='Login was successful';
            array_push($result['lion'],$tetra);
        }
    
    }else{
    $result['success']=0;
    $result['message']='Invalid credentials';
    }
}elseif($theme=='tre'){
    $res=mysqli_query($con,"SELECT * from official where stud_id='$reg_no' and password='$password' and role='Treasurer'");
    if(mysqli_num_rows($res)===1){
        $row=mysqli_fetch_array($res);
        if($row['status']==0){
            $result['success']=0;
            $result['message']='Kindly wait for account approval';
        }elseif($row['status']==2){
            $result=array();
            $result['lion']=array();
            $tetra['remarks']=$row['remarks'];
            $result['success']=2;
            $result['message']='Account Rejected';
            array_push($result['lion'],$tetra);
        }else{
            $result=array();
            $result['lion']=array();
            $tetra['stud_id']=$row['stud_id'];
            $tetra['fname']=$row['fname'];
            $tetra['lname']=$row['lname'];
            $tetra['gender']=$row['gender'];
            $tetra['phone']=$row['phone'];
            $tetra['email']=$row['email'];
            $tetra['department']=$row['department'];
            $tetra['classification']=$row['classification'];
            $tetra['role']=$row['role'];
            $tetra['date']=$row['date'];
            $result['success']=1;
            $result['message']='Login was successful';
            array_push($result['lion'],$tetra);
        }
    
    }else{
    $result['success']=0;
    $result['message']='Invalid credentials';
    }
}elseif($theme=='sec'){
    $res=mysqli_query($con,"SELECT * from official where stud_id='$reg_no' and password='$password' and role='Secretary'");
    if(mysqli_num_rows($res)===1){
        $row=mysqli_fetch_array($res);
        if($row['status']==0){
            $result['success']=0;
            $result['message']='Kindly wait for account approval';
        }elseif($row['status']==2){
            $result=array();
            $result['lion']=array();
            $tetra['remarks']=$row['remarks'];
            $result['success']=2;
            $result['message']='Account Rejected';
            array_push($result['lion'],$tetra);
        }else{
            $result=array();
            $result['lion']=array();
            $tetra['stud_id']=$row['stud_id'];
            $tetra['fname']=$row['fname'];
            $tetra['lname']=$row['lname'];
            $tetra['gender']=$row['gender'];
            $tetra['phone']=$row['phone'];
            $tetra['email']=$row['email'];
            $tetra['department']=$row['department'];
            $tetra['classification']=$row['classification'];
            $tetra['role']=$row['role'];
            $tetra['date']=$row['date'];
            $result['success']=1;
            $result['message']='Login was successful';
            array_push($result['lion'],$tetra);
        }
    
    }else{
    $result['success']=0;
    $result['message']='Invalid credentials';
    }
}elseif($theme=='org'){
    $res=mysqli_query($con,"SELECT * from official where stud_id='$reg_no' and password='$password' and role='Organization'");
    if(mysqli_num_rows($res)===1){
        $row=mysqli_fetch_array($res);
        if($row['status']==0){
            $result['success']=0;
            $result['message']='Kindly wait for account approval';
        }elseif($row['status']==2){
            $result=array();
            $result['lion']=array();
            $tetra['remarks']=$row['remarks'];
            $result['success']=2;
            $result['message']='Account Rejected';
            array_push($result['lion'],$tetra);
        }else{
            $result=array();
            $result['lion']=array();
            $tetra['stud_id']=$row['stud_id'];
            $tetra['fname']=$row['fname'];
            $tetra['lname']=$row['lname'];
            $tetra['gender']=$row['gender'];
            $tetra['phone']=$row['phone'];
            $tetra['email']=$row['email'];
            $tetra['department']=$row['department'];
            $tetra['classification']=$row['classification'];
            $tetra['role']=$row['role'];
            $tetra['date']=$row['date'];
            $result['success']=1;
            $result['message']='Login was successful';
            array_push($result['lion'],$tetra);
        }
    
    }else{
    $result['success']=0;
    $result['message']='Invalid credentials';
    }
}elseif($theme=='lec'){
    $res=mysqli_query($con,"SELECT * from lec where serial_no='$reg_no' and password='$password'");
    if(mysqli_num_rows($res)===1){
        $row=mysqli_fetch_array($res);
        if($row['status']==0){
            $result['success']=0;
            $result['message']='Kindly wait for account approval';
        }elseif($row['status']==2){
            $result=array();
            $result['lion']=array();
            $tetra['remarks']=$row['remarks'];
            $result['success']=2;
            $result['message']='Account Rejected';
            array_push($result['lion'],$tetra);
        }else{
            $result=array();
            $result['lion']=array();
            $tetra['stud_id']=$row['serial_no'];
            $tetra['fname']=$row['fname'];
            $tetra['lname']=$row['lname'];
            $tetra['gender']='Gender';
            $tetra['phone']=$row['phone'];
            $tetra['email']=$row['email'];
            $tetra['department']=$row['department'];
            $tetra['classification']='Classification';
            $tetra['role']='role';
            $tetra['date']=$row['date'];
            $result['success']=1;
            $result['message']='Login was successful';
            array_push($result['lion'],$tetra);
        }
    
    }else{
    $result['success']=0;
    $result['message']='Invalid credentials';
    }
}
echo json_encode($result);
mysqli_close($con);