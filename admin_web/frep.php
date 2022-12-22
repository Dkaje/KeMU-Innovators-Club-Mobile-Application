<?php
include 'database/connection.php';
error_reporting(0);

include('header.php');

?>
<div class="dropdown" style="float: left;">
    <button class="btn btn-primary btn-xs" type="button"
        data-toggle="dropdown">Viewing All Payments   <i class="fas fa-caret-down" style="color: #fff;"></i></button>
 <ul class="dropdown-menu alert panel-footer">
        <li>
        <a href="payment"class="btn btn-primary btn-xs">View All</a><br>
        <a href="frep"class="btn btn-secondary btn-xs">FreshCards</a><br>
        <a href="renp"class="btn btn-success btn-xs">Renewals</a><br>
        <a href="uppp"class="btn btn-danger btn-xs">Upgrading</a>
        </li>
    </ul></div><br>
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
                                            <th>payID</th>
                                            <th>cardType</th>
                                            <th>term</th>
                                            <th>mpesa</th>
                                            <th>amount</th>
                                            <th>regNo</th>
                                            <th>fullname</th>
                                            <th>phone</th>
                                            <th>Status</th>
                                            <th>Date</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                            <?php
                                                $query_run = mysqli_query($con, "SELECT * FROM payment where deter='F'");
                                                $i = 1;
                                                if (mysqli_num_rows($query_run) > 0) {

                                                    while ($row = mysqli_fetch_assoc($query_run)) {
                                                ?>
                                                <tr>
                                                    <td><?php echo $i; ?></td>
                                                    <td><?php echo $row['pay']; ?></td>
                                                    <td><?php if($row['deter']=='F'){echo 'FreshCard';}elseif($row['deter']=='R'){echo 'Renewal';}else{echo 'Upgrading';}; ?></td>
                                                    <td><?php echo $row['term'].' '.$row['year']; ?></td>
                                                    <td><?php echo $row['mpesa']; ?></td>
                                                    <td>Kes<?php echo $row['fees']; ?></td>
                                                    <td><?php echo $row['reg_no']; ?></td>
                                                    <td><?php echo $row['fullname']; ?></td>
                                                    <td><?php echo $row['phone']; ?></td>
                                                    <td><?php if($row['status']==0){echo 'ApprovalPending';}elseif($row['status']==1){echo 'Approved';}else{echo 'Rejected';}; ?></td>
                                                    <td><?php echo $row['date']; ?></td>
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