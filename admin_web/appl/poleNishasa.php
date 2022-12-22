<?php
include('../database/connection.php');
$finsta=$_POST['finsta'];
$secsta=$_POST['secsta'];
    $response = mysqli_query($con,"SELECT * FROM cardy_one where finsta='$finsta' and secsta='$secsta' and lost=0 order by date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['idi'] = $row['idi'];
            $index['ses'] = $row['ses'];
            if($row['ended']==1){
                $index['ended'] = 'Ended';
            }else{
                $index['ended'] = 'Active';
            }
            $index['pay'] = $row['pay'];
            $index['deter'] = $row['deter'];
            $index['category'] = $row['category'];
            $index['reg_no'] = $row['reg_no'];
            $index['fullname'] = $row['fullname'];
            $index['phone'] = $row['phone'];
            $index['profile'] = $row['profile'];
            $index['signature'] = $row['signature'];
            $index['department'] = $row['department'];
            $index['classification'] = $row['classification'];
            $index['email'] = $row['email'];
            if($row['finsta']==1){
                $index['finsta'] = 'Approved';
            }elseif($row['finsta']==2){
                $index['finsta'] = 'Rejected';
            }else{
                $index['finsta'] = 'Pending';
            }
            if($row['secsta']==1){
                $index['secsta'] = 'Issued';
            }else{
                $index['secsta'] = 'Not Issued';
            }
            if($row['lost']==1){
                $index['lost'] = 'Expired';
            }else{
                $index['lost'] = 'Active';
            }
            $index['date'] = $row['date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Record found";
    }
    echo json_encode($results);