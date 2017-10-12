import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * ***************************************************
 * Class Name: ClusterGUI 
 * Author: Chris Tisdale
 * **************************************************** 
 * Purpose of the class:
 * This class provides JFrame for the program. Prompts user for initial number of coords
 * after hitting run button, populates initial and final centroid coords as well as
 * to which cluster each state belongs.
 * **************************************************** 
 * September 12, 2017
 * *****************************************************
 */
public class ClusterGUI extends javax.swing.JFrame {

    /**
     * Creates new form ClusterGUI
     */
    public ClusterGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbNumClusters = new javax.swing.JLabel();
        tfClusters = new javax.swing.JTextField();
        bnRun = new javax.swing.JButton();
        lbInitialClusterCoords = new javax.swing.JLabel();
        lbFinalClusterCoords1 = new javax.swing.JLabel();
        lbClusters = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taFinalClusterCoords = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        taClusters = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        taInitialClusterCoords = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbNumClusters.setText("Number of Clusters:");

        bnRun.setText("Run");
        bnRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnRunActionPerformed(evt);
            }
        });

        lbInitialClusterCoords.setText("Initial Cluster Coords");

        lbFinalClusterCoords1.setText("Final Cluster Coords");

        lbClusters.setText("Clusters");

        taFinalClusterCoords.setEditable(false);
        taFinalClusterCoords.setColumns(20);
        taFinalClusterCoords.setRows(5);
        jScrollPane1.setViewportView(taFinalClusterCoords);

        taClusters.setEditable(false);
        taClusters.setColumns(20);
        taClusters.setRows(5);
        jScrollPane2.setViewportView(taClusters);

        taInitialClusterCoords.setEditable(false);
        taInitialClusterCoords.setColumns(20);
        taInitialClusterCoords.setRows(5);
        jScrollPane3.setViewportView(taInitialClusterCoords);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lbNumClusters)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfClusters, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bnRun, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbInitialClusterCoords))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbFinalClusterCoords1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbClusters))))))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNumClusters)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfClusters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bnRun)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbInitialClusterCoords)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbFinalClusterCoords1)
                        .addComponent(lbClusters)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
     * ***************************************************
     * Method Name: bnRunActionPerformed 
     * Author: Chris Tisdale
     * ***************************************************** 
     * Purpose of the Method: populates initial coords, final coords, and clusters
     * after the number of clusters have been entered. Reads input file and normalizes the data.
     * Randomly choose state to assign centroid to that state's coords. Assigns states to the nearest
     * centroid, then readjusts centroid by average of all states assigned to it,and if any state changes 
     * from their previous cluster, it runs until no cluster changes. More detail within comments below.
     * Return value: void
     * ***************************************************** 
     * Date: September 12, 2017
     * ****************************************************
     */
    private void bnRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnRunActionPerformed
        try {
            Calculate calc = new Calculate();
            ExcelReader excelReader = new ExcelReader();

            ArrayList<State> states = new ArrayList<>();
            int NUM_OF_CLUSTERS = Integer.parseInt(tfClusters.getText());
            double clusterCoords[][] = new double[NUM_OF_CLUSTERS][2]; // 2d array holding [x][y] coords of clusters
            Random rand = new Random();
            int clusterCountEmpty = 0; // tracks the number of clusters that have no state assigned to it (these default to 0,0)
            int clusterAdjustCount = 0; // tracks number of times clusters are adjusted
            boolean stateChangedCluster; // control variable for readjusting clusters
            // clear text areas for subsequent runs
            taInitialClusterCoords.setText("");
            taFinalClusterCoords.setText("");
            taClusters.setText("");

            //read input file into arrayLists
            ArrayList<String> stateNames = excelReader.columnToArrayListAsString("input.xlsx", 0);
            ArrayList<Double> population = excelReader.columnToArrayListAsDouble("input.xlsx", 1);
            ArrayList<Double> crime = excelReader.columnToArrayListAsDouble("input.xlsx", 2);
            ArrayList<Double> degree = excelReader.columnToArrayListAsDouble("input.xlsx", 3);
            ArrayList<Double> income = excelReader.columnToArrayListAsDouble("input.xlsx", 4);
            ArrayList<Double> crimeRate = new ArrayList<>();
            //ArrayList<Double> degreeAndIncome = new ArrayList<>();

            // the following normalizes degree and income to values between 0 and 1
            // set crimeRate to the rate of crime/population
            // set degreeAndIncome to the average of degree and income
            // normalizes crimeRate and degreeAndIncome to values between 0 and 1
            degree = calc.normalize(degree);
            income = calc.normalize(income);
            crimeRate = calc.normalize(crimeRate);
//            for (int i = 0; i < 50; i++) {
//                crimeRate.add(crime.get(i) / population.get(i));
//                degreeAndIncome.add(calc.calculateAverage(degree.get(i), income.get(i)));
//            }
//            crimeRate = calc.normalize(crimeRate);
//            degreeAndIncome = calc.normalize(degreeAndIncome);

            for (int i = 0; i < 50; i++) {
                crimeRate.add(crime.get(i) / population.get(i));
                //degreeAndIncome.add(calc.calculateAverage(degree.get(i), income.get(i)));
            }
            crimeRate = calc.normalize(crimeRate);
            //degreeAndIncome = calc.normalize(degreeAndIncome);

// ********************************************************************
// Write state names and x,y coords to output excel file
//        ExcelWriter excelWriter = new ExcelWriter();
//        excelWriter.setStringArrayListInColumn(stateNames, 0, "output.xlsx");
//        excelWriter.setDoubleArrayListInColumn(degree, 1, "output.xlsx");
//        excelWriter.setDoubleArrayListInColumn(income, 4, "output.xlsx");
//        excelWriter.setDoubleArrayListInColumn(crimeRate, 2, "output.xlsx");
//populate states list with values from other lists using constructor
            for (int i = 0; i < 50; i++) {
//                State tempState = new State(stateNames.get(i), degreeAndIncome.get(i), crimeRate.get(i));
                State tempState = new State(stateNames.get(i), degree.get(i), crimeRate.get(i));
                states.add(tempState);
            }
            
// INITIALIZE CLUSTERS            
// set the x,y coordinates of clusters to a random state's x,y coords
            for (int i = 0; i < NUM_OF_CLUSTERS; i++) {
                int index = rand.nextInt(states.size());
                clusterCoords[i][0] = states.get(index).getX();
                clusterCoords[i][1] = states.get(index).getY();
            }

// prints initial cluster coords
            for (int i = 0; i < NUM_OF_CLUSTERS; i++) {
                taInitialClusterCoords.append("Cluster " + i + ": " + clusterCoords[i][0] + ", " + clusterCoords[i][1] + "\n");
            }

// ASSIGN OBSERVATIONS TO NEAREST CLUSTER
// if a state changes which cluster it is nearest to, do again
            do {
                stateChangedCluster = false;
                for (int i = 0; i < 50; i++) { // for each state
                    int tempCluster = 0;
                    double minDistance = Double.MAX_VALUE;
                    for (int j = 0; j < NUM_OF_CLUSTERS; j++) { // for each cluster
                        // calc distance to clusters
                        double tempDistance = calc.calcDistance(states.get(i).getX(), states.get(i).getY(),
                                clusterCoords[j][0], clusterCoords[j][1]);
                        // set this distance to minDistance if it is less
                        // hold tempCluster int for later
                        if (tempDistance < minDistance) {
                            minDistance = tempDistance;
                            tempCluster = j;
                        }
                    } // end for NUM_OF_CLUSTERS

                    if (tempCluster == states.get(i).getCluster()) { // do nothing
                    } else { // update state's cluster and set stateChangedCluster to true to repeat loop
                        states.get(i).setCluster(tempCluster);
                        stateChangedCluster = true;
                        clusterAdjustCount++;
                    }
                    states.get(i).setDistanceToCluster(minDistance); // update distance to nearest cluster
                } // end for 50 states

// UPDATE CLUSTER CENTROIDS 
                // calculate the sum of all x and y values of states belonging to each cluster, as well as the number of states in each cluster
                if (stateChangedCluster) {
                    double[][] sumClusterCoords = new double[NUM_OF_CLUSTERS][3];
                    for (int i = 0; i < 50; i++) {
                        int index = states.get(i).getCluster();
                        sumClusterCoords[index][0] += states.get(i).getX(); // x val
                        sumClusterCoords[index][1] += states.get(i).getY(); // y val
                        sumClusterCoords[index][2]++; // count of states in that cluster
                    }
                    // update coords of a cluster to the average of x, y for all states in that cluster
                    for (int i = 0; i < NUM_OF_CLUSTERS; i++) {
                        clusterCoords[i][0] = (double) Math.round((sumClusterCoords[i][0] / sumClusterCoords[i][2]) * 10000) / 10000;
                        clusterCoords[i][1] = (double) Math.round((sumClusterCoords[i][1] / sumClusterCoords[i][2]) * 10000) / 10000;
                    }
                }
            } while (stateChangedCluster);

// prints final cluster coords
            for (int i = 0; i < NUM_OF_CLUSTERS; i++) {
                taFinalClusterCoords.append("Cluster " + i + ": " + clusterCoords[i][0] + ", " + clusterCoords[i][1] + "\n");
            }

// prints clusters with states that belong to that cluster
            for (int i = 0; i < NUM_OF_CLUSTERS; i++) {
                taClusters.append("CLUSTER " + i + "\n");
                for (int j = 0; j < 50; j++) {
                    if (states.get(j).getCluster() == i) {
                        taClusters.append("\t" + states.get(j).getName() + "\n");
                    }
                }
            }

//count and print number of empty clusters & number of times clusters were adjusted
            for (int i = 0; i < NUM_OF_CLUSTERS; i++) {
                if (clusterCoords[i][0] == 0 && clusterCoords[i][1] == 0) {
                    clusterCountEmpty++;
                }
            }
            taClusters.append("**************************************\n");
            taClusters.append("Clusters Empty:\t\t" + clusterCountEmpty + "\n");
            taClusters.append("Times adjusted:\t" + clusterAdjustCount);
        } catch (IOException ex) {
            Logger.getLogger(ClusterGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bnRunActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClusterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClusterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClusterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClusterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClusterGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnRun;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbClusters;
    private javax.swing.JLabel lbFinalClusterCoords1;
    private javax.swing.JLabel lbInitialClusterCoords;
    private javax.swing.JLabel lbNumClusters;
    private javax.swing.JTextArea taClusters;
    private javax.swing.JTextArea taFinalClusterCoords;
    private javax.swing.JTextArea taInitialClusterCoords;
    private javax.swing.JTextField tfClusters;
    // End of variables declaration//GEN-END:variables
}
