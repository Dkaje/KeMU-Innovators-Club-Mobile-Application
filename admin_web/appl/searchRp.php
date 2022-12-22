<?php
include('../database/connection.php');
$user_id=$_POST['user_id'];
$status=$_POST['status'];
$ended=$_POST['ended'];
    $response = mysqli_query($con,"SELECT * FROM report where user_id='$user_id' and status='$status' and ended='$ended' order by entry_date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['rep'] = $row['rep'];
            $index['ses'] = $row['ses'];
            $index['user_id'] = $row['user_id'];
            $index['username'] = $row['username'];
            $index['userphone'] = $row['userphone'];
            $index['term'] = $row['term'];
            $index['year'] = $row['year'];
            $index['report'] = $row['report'];
            if($row['status']==1){
                $index['status'] = 'Active';
            }else{
                $index['status'] = 'Pending';
            }
            $index['entry_date'] = $row['entry_date'];
            if($row['ended']==1){
                $index['ended'] = 'Ended';
            }else{
                $index['ended'] = 'Active';
            }
            $index['ending'] = $row['ending'];
            $index['end_date'] = $row['end_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Reported Session";
    }
    echo json_encode($results);