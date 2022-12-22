<?php
include('../database/connection.php');
$cate=$_POST['category'];
    $response = mysqli_query($con,"SELECT * FROM cardy where category!='$cate' and category!='TRIMESTER RENEWAL' order by date desc");
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['id'] = $row['id'];
            $index['category'] = $row['category'];
            $index['fees'] = $row['fees'];
            $index['description'] = $row['description'];
            $index['date'] = $row['date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No card Record";
    }
    echo json_encode($results);
