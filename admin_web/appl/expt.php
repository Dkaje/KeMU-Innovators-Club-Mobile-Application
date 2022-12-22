<?php
include('../database/connection.php');
    $response = mysqli_query($con,"SELECT * FROM expired order by entry_date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['expi'] = $row['expi'];
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
            $index['entry_date'] = $row['entry_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Record found";
    }
    echo json_encode($results);