/*
 * Autopsy Forensic Browser
 *
 * Copyright 2018 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.experimental.autoingest;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;
import org.sleuthkit.autopsy.experimental.autoingest.AutoIngestMonitor.AutoIngestNodeState;

/**
 * A dashboard for monitoring the existing AutoIngestNodes and their status.
 */
final class AinStatusDashboard extends javax.swing.JPanel implements Observer {

    private final AutoIngestMonitor autoIngestMonitor;
    private final AinStatusPanel nodesPanel;

    /**
     * Creates new form AutoIngestNodeStatus
     */
    AinStatusDashboard(AutoIngestMonitor monitor) {
        initComponents();
        autoIngestMonitor = monitor;
        nodesPanel = new AinStatusPanel();
        nodesPanel.setSize(nodesPanel.getSize());
        nodeStatusScrollPane.add(nodesPanel);
        nodeStatusScrollPane.setViewportView(nodesPanel);
        refreshTables();
    }

    /**
     * Adds this panel as an observer of AutoIngestMonitor.
     */
    void startUp() {
        autoIngestMonitor.addObserver(this);
    }

    AutoIngestMonitor getMonitor() {
        return autoIngestMonitor;
    }

    AinStatusPanel getNodesStatusPanel() {
        return nodesPanel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        refreshButton = new javax.swing.JButton();
        clusterMetricsButton = new javax.swing.JButton();
        nodeStatusScrollPane = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(refreshButton, org.openide.util.NbBundle.getMessage(AinStatusDashboard.class, "AinStatusDashboard.refreshButton.text")); // NOI18N
        refreshButton.setToolTipText(org.openide.util.NbBundle.getMessage(AinStatusDashboard.class, "AinStatusDashboard.refreshButton.toolTipText")); // NOI18N
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(clusterMetricsButton, org.openide.util.NbBundle.getMessage(AinStatusDashboard.class, "AinStatusDashboard.clusterMetricsButton.text")); // NOI18N
        clusterMetricsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clusterMetricsButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(AinStatusDashboard.class, "AinStatusDashboard.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nodeStatusScrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 715, Short.MAX_VALUE)
                        .addComponent(clusterMetricsButton)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {clusterMetricsButton, refreshButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(nodeStatusScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(382, 382, 382)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshButton)
                    .addComponent(clusterMetricsButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        refreshTables();
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void refreshTables() {
        nodesPanel.refresh(autoIngestMonitor);
    }

    private void clusterMetricsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clusterMetricsButtonActionPerformed
        new AutoIngestMetricsDialog(this.getTopLevelAncestor());
    }//GEN-LAST:event_clusterMetricsButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clusterMetricsButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane nodeStatusScrollPane;
    private javax.swing.JButton refreshButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof AutoIngestNodeState) {
            EventQueue.invokeLater(() -> {
                refreshTables();
            });
        }
    }
}
