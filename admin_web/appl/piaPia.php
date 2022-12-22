<?php
include('../database/connection.php');
$expiry=$_POST['expiry'];
$status=$_POST['status'];
$reg_no=$_POST['reg_no'];
    $response = mysqli_query($con,"SELECT * FROM issued where reg_no='$reg_no' and status='$status' and expiry='$expiry' order by entry_date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['iss'] = $row['iss'];
            $index['idi'] = $row['idi'];
            $index['ses'] = $row['ses'];
            $index['reg_no'] = $row['reg_no'];
            $index['fullname'] = $row['fullname'];
            $index['phone'] = $row['phone'];
            $index['profile'] = $row['profile'];
            $index['signature'] = $row['signature'];
            $index['department'] = $row['department'];
            $index['classification'] = $row['classification'];
            $index['issue_date'] = $row['issue_date'];
            $index['expiry_date'] = $row['expiry_date'];
            $index['category'] = $row['category'];
            if($row['status']==1){
                $index['status'] = 'Issued';
            }else{
                $index['status'] = 'Yet';
            }
            if($row['ended']==1){
                $index['ended'] = 'Ended';
            }else{
                $index['ended'] = 'Active';
            }
            if($row['expiry']==1){
                $index['expiry'] = 'Expired';
            }else{
                $index['expiry'] = 'Active';
            }
            $index['entry_date'] = $row['entry_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Record found";
    }
    echo json_encode($results);