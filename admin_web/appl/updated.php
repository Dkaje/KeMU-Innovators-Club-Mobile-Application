<?php
include('../database/connection.php');
$venue=$_POST['venue'];
$theme=$_POST['theme'];
    $response = mysqli_query($con,"SELECT * FROM summer where theme='$theme' and status!=0 order by entry_date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['sm'] = $row['sm'];
            $index['evt'] = $row['evt'];
            $index['ses'] = $row['ses'];
            $index['term'] = $row['term'];
            $index['theme'] = $row['theme'];
            $index['venue'] = $row['venue'];
            $index['land'] = $row['land'];
            $index['site'] = $row['site'];
            $index['date'] = $row['date'];
            $index['time'] = $row['time'];
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
            $index['comment'] = $row['comment'];
            if ($row['pay'] == 0) {
                $index['pay'] = 'Pending';
            }else {
                $index['pay'] = 'Paid';
            }
            if ($row['approval'] == 0) {
                $index['approval'] = 'Pending';
            } elseif ($row['approval'] == 1) {
                $index['approval'] = 'Approved';
            } else {
                $index['approval'] = 'Rejected';
            }
            if ($row['org'] == 0) {
                $index['org'] = 'Pending';
            }else {
                $index['org'] = 'Confirmed';
            }
            $index['org_no'] = $row['org_no'];
            $index['org_phone'] = $row['org_phone'];
            $index['org_name'] = $row['org_name'];
            $index['club'] = $row['club'];
            if ($row['pat'] == 0) {
                $index['pat'] = 'Pending';
            }else {
                $index['pat'] = 'Forwarded';
            }
            if ($row['closure'] == 0) {
                $index['closure'] = 'Pending';
            }else {
                $index['closure'] = 'Expired';
            }
            $index['entry_date'] = $row['entry_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Record";
    }
    echo json_encode($results);
