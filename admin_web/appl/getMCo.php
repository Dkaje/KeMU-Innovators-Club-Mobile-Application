<?php
include('../database/connection.php');
$status=$_POST['status'];
    $response = mysqli_query($con,"SELECT * FROM contri where status='$status' order by date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['pay'] = $row['pay'];
            $index['ses'] = $row['ses'];
            $index['term'] = $row['term'];
            $index['sm'] = $row['event'];
            $index['mpesa'] = $row['mpesa'];
            $index['money'] = $row['money'];
            $index['reg_no'] = $row['reg_no'];
            $index['fullname'] = $row['fullname'];
            $index['phone'] = $row['phone'];
            if ($row['status'] == 0) {
                $index['status'] = 'Pending';
            } elseif ($row['status'] == 1) {
                $index['status'] = 'Approved';
            } else {
                $index['status'] = 'Rejected';
            }
            if ($row['expiry'] == 0) {
                $index['expiry'] = 'Pending';
            } elseif ($row['expiry'] == 3) {
                $index['expiry'] = 'Issued';
            } else {
                $index['expiry'] = 'Expired';
            }
            $index['remarks'] = $row['remarks'];
            $index['date'] = $row['date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Record";
    }
    echo json_encode($results);
