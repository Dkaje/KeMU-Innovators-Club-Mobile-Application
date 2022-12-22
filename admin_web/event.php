<?php
include 'database/connection.php';
error_reporting(0);

include('header.php');

?>
<div class="dropdown" style="float: left;">
    <button class="btn btn-primary btn-xs" type="button"
        data-toggle="dropdown">EVENTS</button>
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
                                            <th>TERM</th>
                                            <th>eventTheme</th>
                                            <th>eventVenue</th>
                                            <th>eventDate</th>
                                            <th>charge</th>
                                            <th>maxMembers</th>
                                            <th>registered</th>
                                            <th>openSlots</th>
                                            <th>status</th>
                                            <th>entryDate</th>
                                        </tr>

                                    </thead>
                                            <tbody>
                                            <?php
                                                $query_run = mysqli_query($con, "SELECT * FROM event");
                                                $i = 1;
                                                if (mysqli_num_rows($query_run) > 0) {

                                                    while ($row = mysqli_fetch_assoc($query_run)) {
                                                ?>
                                                <tr>
                                                    <td><?php echo $i; ?></td>
                                                    <td><?php echo $row['term'].' '.$row['year']; ?></td>
                                                    <td><?php echo $row['theme']; ?></td>
                                                    <td><?php echo $row['venue'].'/'.$row['land'].'/'.$row['site']; ?></td>
                                                    <td><?php echo $row['date'].' '.$row['time']; ?></td>
                                                    <td>Kes<?php echo $row['money']; ?></td>
                                                    <td><?php echo $row['opened']; ?></td>
                                                    <td><?php echo round(($row['opened']-$row['member']),0); ?></td>
                                                    <td><?php echo $row['member']; ?></td>
                                                    <td><?php if($row['approval']==0){echo 'Pending';}elseif($row['approval']==1){echo 'Approved';}else{echo 'Rejected';}; ?></td>
                                                    <td><?php echo $row['entry_date']; ?></td>
                                                
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