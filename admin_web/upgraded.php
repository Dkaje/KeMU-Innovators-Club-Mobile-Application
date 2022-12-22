<?php
include 'database/connection.php';
error_reporting(0);

include('header.php');

?>
<div class="dropdown" style="float: left;">
    <button class="btn btn-primary btn-xs" type="button"
        data-toggle="dropdown">Upgrading Applications</button>
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
                                            <th>payID</td>
                                            <th>RegNo</th>
                                            <th>Fullname</th>
                                            <th>Phone</th>
                                            <th>Profile</th>
                                            <th>Signature</th>
                                            <th>Department</th>
                                            <th>Classification</th>
                                            <th>CardType</th>
                                            <th>FinanceStatus</th>
                                            <th>Issuance</th>
                                            <th>Expiry</th>
                                            <th>EntryDate</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                            <?php
                                                $query_run = mysqli_query($con, "SELECT * FROM cardy_one where deter='U'");
                                                $i = 1;
                                                if (mysqli_num_rows($query_run) > 0) {

                                                    while ($row = mysqli_fetch_assoc($query_run)) {
                                                ?>
                                                <tr>
                                                    <td><?php echo $i; ?></td>
                                                    <th><?php echo $row['pay'];?></td>
                                                    <td><?php echo $row['reg_no']; ?></td>
                                                    <td><?php echo $row['fullname']; ?></td>
                                                    <td><?php echo $row['phone']; ?></td>
                                                    <td><center><img src="appl/images/<?php echo $row['profile'];?>" style="border-radius:50px" width="100" height="100"></center></td>
                                                    <td><center><img src="appl/images/<?php echo $row['signature'];?>" style="border-radius:50px" width="100" height="100"></center></td>
                                                    <td><?php echo $row['department']; ?></td>
                                                    <td><?php echo $row['classification']; ?></td>
                                                    <td><?php echo $row['category']; ?></td>
                                                    <td><?php if($row['finsta']==0){echo 'ApprovalPending';}elseif($row['finsta']==1){echo 'Approved';}else{echo 'Rejected';}; ?></td>
                                                    <td><?php if($row['secsta']==0){echo 'notIssued';}else{echo 'Issued';} ?></td>
                                                    <td><?php if($row['lost']==0){echo 'Active';}else{echo 'Expired';} ?></td>
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