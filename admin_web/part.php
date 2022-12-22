<?php
include 'database/connection.php';
error_reporting(0);

include('header.php');

?>
<div class="dropdown" style="float: left;">
    <button class="btn btn-primary btn-xs" type="button"
        data-toggle="dropdown">User Event Registration</button>
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
                                            <th>term</th>
                                            <th>eventTheme</th>
                                            <th>Location</th>
                                            <th>eventDate</th>
                                            <th>eventFee</th>
                                            <th>regNo</th>
                                            <th>fullname</th>
                                            <th>phone</th>
                                            <th>registerStatus</th>
                                            <th>userPayStatus</th>
                                            <th>financeStatus</th>
                                            <th>CLUB</th>
                                            <th>Entry</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                            <?php
                                                $query_run = mysqli_query($con, "SELECT * FROM summer");
                                                $i = 1;
                                                if (mysqli_num_rows($query_run) > 0) {

                                                    while ($row = mysqli_fetch_assoc($query_run)) {
                                                ?>
                                                <tr>
                                                    <td><?php echo $i; ?></td>
                                                    <td><?php echo $row['term']; ?></td>
                                                    <td><?php echo $row['theme']; ?></td>
                                                    <td><?php echo $row['venue'].'/'.$row['land'].'/'.$row['site']; ?></td>
                                                    <td><?php echo $row['date'].' '.$row['time']; ?></td>
                                                    <td>Kes<?php echo $row['money']; ?></td>
                                                    <td><?php echo $row['reg_no']; ?></td>
                                                    <td><?php echo $row['fullname']; ?></td>
                                                    <td><?php echo $row['phone']; ?></td>
                                                    <td><?php if($row['status']==0){echo 'ApprovalPending';}elseif($row['status']==1){echo 'Approved';}else{echo 'Rejected';}; ?></td>
                                                    <td><?php if($row['pay']==0){echo 'paymentPending';}else{echo 'Paid';}; ?></td>
                                                    <td><?php if($row['approval']==0){echo 'ApprovalPending';}elseif($row['approval']==1){echo 'Approved';}else{echo 'Rejected';}; ?></td>
                                                    <td><?php if($row['org']==0){echo '';}elseif($row['org']==1){echo $row['club'];}else{echo '';}; ?></td>
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