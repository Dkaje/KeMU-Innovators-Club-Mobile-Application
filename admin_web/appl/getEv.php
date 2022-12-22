<?php
include('../database/connection.php');
$sm=$_POST['sm'];
    $response = mysqli_query($con,"SELECT * FROM event where evt='$sm' order by entry_date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['evt'] = $row['evt'];
            $index['ses'] = $row['ses'];
            $index['term'] = $row['term'];
            $index['theme'] = $row['theme'];
            $index['venue'] = $row['venue'];
            $index['land'] = $row['land'];
            $index['site'] = $row['site'];
            $index['date'] = $row['date'];
            $index['member'] = $row['member'];
            $index['opened'] = $row['opened'];
            $index['money'] = $row['money'];
            $index['time'] = $row['time'];
            if($row['status']==1){
                $index['status'] = 'Active';
            }else{
                $index['status'] = 'Ended';
            }
            $index['comment']=$row['comment'];
            if($row['approval']==0){
                $index['approval'] = 'Pending';
            }elseif($row['approval']==1){
                $index['approval'] = 'Approved';
            }else{
                $index['approval'] = 'Rejected';
            }
            if($row['closure']==1){
                $index['closure'] = 'Ended';
            }else{
                $index['closure'] = 'Active';
            }
            $index['entry_date'] = $row['entry_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Event Record Record";
    }
    echo json_encode($results);