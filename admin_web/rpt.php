<?php
include 'database/connection.php';
error_reporting(0);

include('header.php');

?>
<div class="dropdown" style="float: left;">
    <button class="btn btn-primary btn-xs" type="button"
        data-toggle="dropdown">Session Reporting</button>
</div><br>
                    <!-- Page Heading -->
                    <h1 class="h3 mb-4 text-gray-800"></h1>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <table class="table table-striped table-bordered table-hover"
                                            id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>user_id</th>
                                            <th>userName</th>
                                            <th>userPhone</th>
                                            <th>TERM</th>
                                            <th>sessStart</th>
                                            <th>reportDate</th>
                                            <th>Status</th>
                                            <th>timedSessionEnding</th>
                                            <th>dateEnded</th>
                                        </tr>
                                    </thead>
                                            <tbody>
                                            <?php
                                                $query_run = mysqli_query($con, "SELECT * FROM report");
                                                $i = 1;
                                                if (mysqli_num_rows($query_run) > 0) {

                                                    while ($row = mysqli_fetch_assoc($query_run)) {
                                                ?>
                                                <tr>
                                                    <td><?php echo $i; ?></td>
                                                    <td><?php echo $row['user_id']; ?></td>
                                                    <td><?php echo $row['username']; ?></td>
                                                    <td><?php echo $row['userphone']; ?></td>
                                                    <td><?php echo $row['term'].' '.$row['year']; ?></td>
                                                    <td><?php echo $row['report']; ?></td>
                                                    <td><?php echo $row['entry_date']; ?></td>
                                                    <td><?php if($row['ended']==0){echo 'Active';}else{echo 'Ended';}; ?></td>
                                                    <td><?php echo $row['ending']; ?></td>
                                                    <td><?php if($row['ended']==0){echo ' ';}else{echo $row['end_date'];}; ?></td>
                                                
                                                </tr>
                                                <?php $i = $i + 1;
                                                    }
                                                } ?>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                    <?php
include('footer.php');

?>