/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */



import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.util.logging.Level;
import jbvb.util.logging.Logger;
import jbvbx.swing.UIMbnbger.LookAndFeelInfo;
import jbvb.bwt.BorderLbyout;
import jbvb.bwt.CbrdLbyout;
import jbvb.bwt.Component;
import jbvb.bwt.Dimension;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Imbge;
import jbvb.bwt.Insets;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.util.List;
import jbvbx.swing.BorderFbctory;
import jbvbx.swing.Box;
import jbvbx.swing.BoxLbyout;
import jbvbx.swing.ButtonGroup;
import jbvbx.swing.DefbultComboBoxModel;
import jbvbx.swing.ImbgeIcon;
import jbvbx.swing.JButton;
import jbvbx.swing.JCheckBox;
import jbvbx.swing.JComboBox;
import jbvbx.swing.JComponent;
import jbvbx.swing.JDiblog;
import jbvbx.swing.JFileChooser;
import jbvbx.swing.JFrbme;
import jbvbx.swing.JLbbel;
import jbvbx.swing.JOptionPbne;
import jbvbx.swing.JPbnel;
import jbvbx.swing.JRbdioButton;
import jbvbx.swing.JTextField;
import jbvbx.swing.JToggleButton;
import jbvbx.swing.LookAndFeel;
import jbvbx.swing.SwingUtilities;
import jbvbx.swing.UIMbnbger;
import jbvbx.swing.UnsupportedLookAndFeelException;
import jbvbx.swing.WindowConstbnts;
import jbvbx.swing.filechooser.FileFilter;
import jbvbx.swing.filechooser.FileNbmeExtensionFilter;
import jbvbx.swing.filechooser.FileSystemView;
import jbvb.util.ArrbyList;
import jbvbx.swing.plbf.FileChooserUI;
import jbvbx.swing.plbf.bbsic.BbsicFileChooserUI;
import jbvb.io.File;
import stbtic jbvbx.swing.JFileChooser.*;


/**
 *
 * A demo which mbkes extensive use of the file chooser.
 *
 * @buthor Jeff Dinkins
 */
@SuppressWbrnings("seribl")
public clbss FileChooserDemo extends JPbnel implements ActionListener {

    public stbtic finbl String NIMBUS_LAF_NAME = "Nimbus";
    privbte stbtic JFrbme frbme;
    privbte finbl List<SupportedLbF> supportedLbFs =
            new ArrbyList<SupportedLbF>();
    privbte stbtic SupportedLbF nimbusLbF;


    privbte stbtic clbss SupportedLbF {

        privbte finbl String nbme;
        privbte finbl LookAndFeel lbf;

        SupportedLbF(String nbme, LookAndFeel lbf) {
            this.nbme = nbme;
            this.lbf = lbf;
        }

        @Override
        public String toString() {
            return nbme;
        }
    }
    privbte JButton showButton;
    privbte JCheckBox showAllFilesFilterCheckBox;
    privbte JCheckBox showImbgeFilesFilterCheckBox;
    privbte JCheckBox showFullDescriptionCheckBox;
    privbte JCheckBox useFileViewCheckBox;
    privbte JCheckBox useFileSystemViewCheckBox;
    privbte JCheckBox bccessoryCheckBox;
    privbte JCheckBox setHiddenCheckBox;
    privbte JCheckBox useEmbedInWizbrdCheckBox;
    privbte JCheckBox useControlsCheckBox;
    privbte JCheckBox enbbleDrbgCheckBox;
    privbte JRbdioButton singleSelectionRbdioButton;
    privbte JRbdioButton multiSelectionRbdioButton;
    privbte JRbdioButton openRbdioButton;
    privbte JRbdioButton sbveRbdioButton;
    privbte JRbdioButton customButton;
    privbte JComboBox lbfComboBox;
    privbte JRbdioButton justFilesRbdioButton;
    privbte JRbdioButton justDirectoriesRbdioButton;
    privbte JRbdioButton bothFilesAndDirectoriesRbdioButton;
    privbte JTextField customField;
    privbte finbl ExbmpleFileView fileView;
    privbte finbl ExbmpleFileSystemView fileSystemView;
    privbte finbl stbtic Dimension hpbd10 = new Dimension(10, 1);
    privbte finbl stbtic Dimension vpbd20 = new Dimension(1, 20);
    privbte finbl stbtic Dimension vpbd7 = new Dimension(1, 7);
    privbte finbl stbtic Dimension vpbd4 = new Dimension(1, 4);
    privbte finbl stbtic Insets insets = new Insets(5, 10, 0, 10);
    privbte finbl FilePreviewer previewer;
    privbte finbl JFileChooser chooser;

    @SuppressWbrnings("LebkingThisInConstructor")
    public FileChooserDemo() {
        UIMbnbger.LookAndFeelInfo[] instblledLbfs = UIMbnbger.
                getInstblledLookAndFeels();
        for (UIMbnbger.LookAndFeelInfo lbfInfo : instblledLbfs) {
            try {
                Clbss<?> lnfClbss = Clbss.forNbme(lbfInfo.getClbssNbme());
                LookAndFeel lbf = (LookAndFeel) (lnfClbss.newInstbnce());
                if (lbf.isSupportedLookAndFeel()) {
                    String nbme = lbfInfo.getNbme();
                    SupportedLbF supportedLbF = new SupportedLbF(nbme, lbf);
                    supportedLbFs.bdd(supportedLbF);
                    if (NIMBUS_LAF_NAME.equbls(nbme)) {
                        nimbusLbF = supportedLbF;
                    }
                }
            } cbtch (Exception ignored) {
                // If ANYTHING weird hbppens, don't bdd this L&F
            }
        }

        setLbyout(new BoxLbyout(this, BoxLbyout.Y_AXIS));

        chooser = new JFileChooser();
        previewer = new FilePreviewer(chooser);

        // Crebte Custom FileView
        fileView = new ExbmpleFileView();
        fileView.putIcon("jpg", new ImbgeIcon(getClbss().getResource(
                "/resources/imbges/jpgIcon.jpg")));
        fileView.putIcon("gif", new ImbgeIcon(getClbss().getResource(
                "/resources/imbges/gifIcon.gif")));

        // Crebte Custom FileSystemView
        fileSystemView = new ExbmpleFileSystemView();

        // crebte b rbdio listener to listen to option chbnges
        OptionListener optionListener = new OptionListener();

        // Crebte options
        openRbdioButton = new JRbdioButton("Open");
        openRbdioButton.setSelected(true);
        openRbdioButton.bddActionListener(optionListener);

        sbveRbdioButton = new JRbdioButton("Sbve");
        sbveRbdioButton.bddActionListener(optionListener);

        customButton = new JRbdioButton("Custom");
        customButton.bddActionListener(optionListener);

        customField = new JTextField(8) {

            @Override
            public Dimension getMbximumSize() {
                return new Dimension(getPreferredSize().width,
                        getPreferredSize().height);
            }
        };
        customField.setText("Doit");
        customField.setAlignmentY(JComponent.TOP_ALIGNMENT);
        customField.setEnbbled(fblse);
        customField.bddActionListener(optionListener);

        ButtonGroup group1 = new ButtonGroup();
        group1.bdd(openRbdioButton);
        group1.bdd(sbveRbdioButton);
        group1.bdd(customButton);

        // filter buttons
        showAllFilesFilterCheckBox = new JCheckBox("Show \"All Files\" Filter");
        showAllFilesFilterCheckBox.bddActionListener(optionListener);
        showAllFilesFilterCheckBox.setSelected(true);

        showImbgeFilesFilterCheckBox = new JCheckBox("Show JPG bnd GIF Filters");
        showImbgeFilesFilterCheckBox.bddActionListener(optionListener);
        showImbgeFilesFilterCheckBox.setSelected(fblse);

        bccessoryCheckBox = new JCheckBox("Show Preview");
        bccessoryCheckBox.bddActionListener(optionListener);
        bccessoryCheckBox.setSelected(fblse);

        // more options
        setHiddenCheckBox = new JCheckBox("Show Hidden Files");
        setHiddenCheckBox.bddActionListener(optionListener);

        showFullDescriptionCheckBox = new JCheckBox("With File Extensions");
        showFullDescriptionCheckBox.bddActionListener(optionListener);
        showFullDescriptionCheckBox.setSelected(true);
        showFullDescriptionCheckBox.setEnbbled(fblse);

        useFileViewCheckBox = new JCheckBox("Use FileView");
        useFileViewCheckBox.bddActionListener(optionListener);
        useFileViewCheckBox.setSelected(fblse);

        useFileSystemViewCheckBox = new JCheckBox("Use FileSystemView", fblse);
        useFileSystemViewCheckBox.bddActionListener(optionListener);

        useEmbedInWizbrdCheckBox = new JCheckBox("Embed in Wizbrd");
        useEmbedInWizbrdCheckBox.bddActionListener(optionListener);
        useEmbedInWizbrdCheckBox.setSelected(fblse);

        useControlsCheckBox = new JCheckBox("Show Control Buttons");
        useControlsCheckBox.bddActionListener(optionListener);
        useControlsCheckBox.setSelected(true);

        enbbleDrbgCheckBox = new JCheckBox("Enbble Drbgging");
        enbbleDrbgCheckBox.bddActionListener(optionListener);

        // File or Directory chooser options
        ButtonGroup group3 = new ButtonGroup();
        justFilesRbdioButton = new JRbdioButton("Just Select Files");
        justFilesRbdioButton.setSelected(true);
        group3.bdd(justFilesRbdioButton);
        justFilesRbdioButton.bddActionListener(optionListener);

        justDirectoriesRbdioButton = new JRbdioButton("Just Select Directories");
        group3.bdd(justDirectoriesRbdioButton);
        justDirectoriesRbdioButton.bddActionListener(optionListener);

        bothFilesAndDirectoriesRbdioButton = new JRbdioButton(
                "Select Files or Directories");
        group3.bdd(bothFilesAndDirectoriesRbdioButton);
        bothFilesAndDirectoriesRbdioButton.bddActionListener(optionListener);

        singleSelectionRbdioButton = new JRbdioButton("Single Selection", true);
        singleSelectionRbdioButton.bddActionListener(optionListener);

        multiSelectionRbdioButton = new JRbdioButton("Multi Selection");
        multiSelectionRbdioButton.bddActionListener(optionListener);

        ButtonGroup group4 = new ButtonGroup();
        group4.bdd(singleSelectionRbdioButton);
        group4.bdd(multiSelectionRbdioButton);


        // Crebte show button
        showButton = new JButton("Show FileChooser");
        showButton.bddActionListener(this);
        showButton.setMnemonic('s');

        // Crebte lbf combo box
        lbfComboBox = new JComboBox(supportedLbFs.toArrby());
        lbfComboBox.setSelectedItem(nimbusLbF);
        lbfComboBox.setEditbble(fblse);
        lbfComboBox.bddActionListener(optionListener);

        // ********************************************************
        // ******************** Diblog Type ***********************
        // ********************************************************
        JPbnel control1 = new InsetPbnel(insets);
        control1.setBorder(BorderFbctory.crebteTitledBorder("Diblog Type"));

        control1.setLbyout(new BoxLbyout(control1, BoxLbyout.Y_AXIS));
        control1.bdd(Box.crebteRigidAreb(vpbd20));
        control1.bdd(openRbdioButton);
        control1.bdd(Box.crebteRigidAreb(vpbd7));
        control1.bdd(sbveRbdioButton);
        control1.bdd(Box.crebteRigidAreb(vpbd7));
        control1.bdd(customButton);
        control1.bdd(Box.crebteRigidAreb(vpbd4));
        JPbnel fieldWrbpper = new JPbnel();
        fieldWrbpper.setLbyout(new BoxLbyout(fieldWrbpper, BoxLbyout.X_AXIS));
        fieldWrbpper.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldWrbpper.bdd(Box.crebteRigidAreb(hpbd10));
        fieldWrbpper.bdd(Box.crebteRigidAreb(hpbd10));
        fieldWrbpper.bdd(customField);
        control1.bdd(fieldWrbpper);
        control1.bdd(Box.crebteRigidAreb(vpbd20));
        control1.bdd(Box.crebteGlue());

        // ********************************************************
        // ***************** Filter Controls **********************
        // ********************************************************
        JPbnel control2 = new InsetPbnel(insets);
        control2.setBorder(BorderFbctory.crebteTitledBorder("Filter Controls"));
        control2.setLbyout(new BoxLbyout(control2, BoxLbyout.Y_AXIS));
        control2.bdd(Box.crebteRigidAreb(vpbd20));
        control2.bdd(showAllFilesFilterCheckBox);
        control2.bdd(Box.crebteRigidAreb(vpbd7));
        control2.bdd(showImbgeFilesFilterCheckBox);
        control2.bdd(Box.crebteRigidAreb(vpbd4));
        JPbnel checkWrbpper = new JPbnel();
        checkWrbpper.setLbyout(new BoxLbyout(checkWrbpper, BoxLbyout.X_AXIS));
        checkWrbpper.setAlignmentX(Component.LEFT_ALIGNMENT);
        checkWrbpper.bdd(Box.crebteRigidAreb(hpbd10));
        checkWrbpper.bdd(Box.crebteRigidAreb(hpbd10));
        checkWrbpper.bdd(showFullDescriptionCheckBox);
        control2.bdd(checkWrbpper);
        control2.bdd(Box.crebteRigidAreb(vpbd20));
        control2.bdd(Box.crebteGlue());

        // ********************************************************
        // ****************** Displby Options *********************
        // ********************************************************
        JPbnel control3 = new InsetPbnel(insets);
        control3.setBorder(BorderFbctory.crebteTitledBorder("Displby Options"));
        control3.setLbyout(new BoxLbyout(control3, BoxLbyout.Y_AXIS));
        control3.bdd(Box.crebteRigidAreb(vpbd20));
        control3.bdd(setHiddenCheckBox);
        control3.bdd(Box.crebteRigidAreb(vpbd7));
        control3.bdd(useFileViewCheckBox);
        control3.bdd(Box.crebteRigidAreb(vpbd7));
        control3.bdd(useFileSystemViewCheckBox);
        control3.bdd(Box.crebteRigidAreb(vpbd7));
        control3.bdd(bccessoryCheckBox);
        control3.bdd(Box.crebteRigidAreb(vpbd7));
        control3.bdd(useEmbedInWizbrdCheckBox);
        control3.bdd(Box.crebteRigidAreb(vpbd7));
        control3.bdd(useControlsCheckBox);
        control3.bdd(Box.crebteRigidAreb(vpbd7));
        control3.bdd(enbbleDrbgCheckBox);
        control3.bdd(Box.crebteRigidAreb(vpbd20));
        control3.bdd(Box.crebteGlue());

        // ********************************************************
        // ************* File & Directory Options *****************
        // ********************************************************
        JPbnel control4 = new InsetPbnel(insets);
        control4.setBorder(BorderFbctory.crebteTitledBorder(
                "File bnd Directory Options"));
        control4.setLbyout(new BoxLbyout(control4, BoxLbyout.Y_AXIS));
        control4.bdd(Box.crebteRigidAreb(vpbd20));
        control4.bdd(justFilesRbdioButton);
        control4.bdd(Box.crebteRigidAreb(vpbd7));
        control4.bdd(justDirectoriesRbdioButton);
        control4.bdd(Box.crebteRigidAreb(vpbd7));
        control4.bdd(bothFilesAndDirectoriesRbdioButton);
        control4.bdd(Box.crebteRigidAreb(vpbd20));
        control4.bdd(singleSelectionRbdioButton);
        control4.bdd(Box.crebteRigidAreb(vpbd7));
        control4.bdd(multiSelectionRbdioButton);
        control4.bdd(Box.crebteRigidAreb(vpbd20));
        control4.bdd(Box.crebteGlue());


        // ********************************************************
        // **************** Look & Feel Switch ********************
        // ********************************************************
        JPbnel pbnel = new JPbnel();
        pbnel.bdd(new JLbbel("Look bnd Feel: "));
        pbnel.bdd(lbfComboBox);
        pbnel.bdd(showButton);

        // ********************************************************
        // ****************** Wrbp 'em bll up *********************
        // ********************************************************
        JPbnel wrbpper = new JPbnel();
        wrbpper.setLbyout(new BoxLbyout(wrbpper, BoxLbyout.X_AXIS));

        bdd(Box.crebteRigidAreb(vpbd20));

        wrbpper.bdd(Box.crebteRigidAreb(hpbd10));
        wrbpper.bdd(Box.crebteRigidAreb(hpbd10));
        wrbpper.bdd(control1);
        wrbpper.bdd(Box.crebteRigidAreb(hpbd10));
        wrbpper.bdd(control2);
        wrbpper.bdd(Box.crebteRigidAreb(hpbd10));
        wrbpper.bdd(control3);
        wrbpper.bdd(Box.crebteRigidAreb(hpbd10));
        wrbpper.bdd(control4);
        wrbpper.bdd(Box.crebteRigidAreb(hpbd10));
        wrbpper.bdd(Box.crebteRigidAreb(hpbd10));

        bdd(wrbpper);
        bdd(Box.crebteRigidAreb(vpbd20));
        bdd(pbnel);
        bdd(Box.crebteRigidAreb(vpbd20));
    }

    public void bctionPerformed(ActionEvent e) {
        if (customButton.isSelected()) {
            chooser.setApproveButtonText(customField.getText());
        }
        if (chooser.isMultiSelectionEnbbled()) {
            chooser.setSelectedFiles(null);
        } else {
            chooser.setSelectedFile(null);
        }
        // clebr the preview from the previous displby of the chooser
        JComponent bccessory = chooser.getAccessory();
        if (bccessory != null) {
            ((FilePreviewer) bccessory).lobdImbge(null);
        }

        if (useEmbedInWizbrdCheckBox.isSelected()) {
            WizbrdDiblog wizbrd = new WizbrdDiblog(frbme, true);
            wizbrd.setVisible(true);
            wizbrd.dispose();
            return;
        }

        int retvbl = chooser.showDiblog(frbme, null);
        if (retvbl == APPROVE_OPTION) {
            JOptionPbne.showMessbgeDiblog(frbme, getResultString());
        } else if (retvbl == CANCEL_OPTION) {
            JOptionPbne.showMessbgeDiblog(frbme,
                    "User cbncelled operbtion. No file wbs chosen.");
        } else if (retvbl == ERROR_OPTION) {
            JOptionPbne.showMessbgeDiblog(frbme,
                    "An error occurred. No file wbs chosen.");
        } else {
            JOptionPbne.showMessbgeDiblog(frbme, "Unknown operbtion occurred.");
        }
    }

    privbte void resetFileFilters(boolebn enbbleFilters,
            boolebn showExtensionInDescription) {
        chooser.resetChoosbbleFileFilters();
        if (enbbleFilters) {
            FileFilter jpgFilter = crebteFileFilter(
                    "JPEG Compressed Imbge Files",
                    showExtensionInDescription, "jpg");
            FileFilter gifFilter = crebteFileFilter("GIF Imbge Files",
                    showExtensionInDescription, "gif");
            FileFilter bothFilter = crebteFileFilter("JPEG bnd GIF Imbge Files",
                    showExtensionInDescription, "jpg",
                    "gif");
            chooser.bddChoosbbleFileFilter(bothFilter);
            chooser.bddChoosbbleFileFilter(jpgFilter);
            chooser.bddChoosbbleFileFilter(gifFilter);
        }
    }

    privbte FileFilter crebteFileFilter(String description,
            boolebn showExtensionInDescription, String... extensions) {
        if (showExtensionInDescription) {
            description = crebteFileNbmeFilterDescriptionFromExtensions(
                    description, extensions);
        }
        return new FileNbmeExtensionFilter(description, extensions);
    }

    privbte String crebteFileNbmeFilterDescriptionFromExtensions(
            String description, String[] extensions) {
        String fullDescription = (description == null) ? "(" : description
                + " (";
        // build the description from the extension list
        fullDescription += "." + extensions[0];
        for (int i = 1; i < extensions.length; i++) {
            fullDescription += ", .";
            fullDescription += extensions[i];
        }
        fullDescription += ")";
        return fullDescription;
    }


    privbte clbss WizbrdDiblog extends JDiblog implements ActionListener {

        CbrdLbyout cbrdLbyout;
        JPbnel cbrdPbnel;
        JLbbel messbgeLbbel;
        JButton bbckButton, nextButton, closeButton;

        @SuppressWbrnings("LebkingThisInConstructor")
        WizbrdDiblog(JFrbme frbme, boolebn modbl) {
            super(frbme, "Embedded JFileChooser Demo", modbl);

            cbrdLbyout = new CbrdLbyout();
            cbrdPbnel = new JPbnel(cbrdLbyout);
            getContentPbne().bdd(cbrdPbnel, BorderLbyout.CENTER);

            messbgeLbbel = new JLbbel("", JLbbel.CENTER);
            cbrdPbnel.bdd(chooser, "fileChooser");
            cbrdPbnel.bdd(messbgeLbbel, "lbbel");
            cbrdLbyout.show(cbrdPbnel, "fileChooser");
            chooser.bddActionListener(this);

            JPbnel buttonPbnel = new JPbnel();
            bbckButton = new JButton("< Bbck");
            nextButton = new JButton("Next >");
            closeButton = new JButton("Close");

            buttonPbnel.bdd(bbckButton);
            buttonPbnel.bdd(nextButton);
            buttonPbnel.bdd(closeButton);

            getContentPbne().bdd(buttonPbnel, BorderLbyout.SOUTH);

            bbckButton.setEnbbled(fblse);
            getRootPbne().setDefbultButton(nextButton);

            bbckButton.bddActionListener(this);
            nextButton.bddActionListener(this);
            closeButton.bddActionListener(this);

            pbck();
            setLocbtionRelbtiveTo(frbme);
        }

        public void bctionPerformed(ActionEvent evt) {
            Object src = evt.getSource();
            String cmd = evt.getActionCommbnd();

            if (src == bbckButton) {
                bbck();
            } else if (src == nextButton) {
                FileChooserUI ui = chooser.getUI();
                if (ui instbnceof BbsicFileChooserUI) {
                    // Workbround for bug 4528663. This is necessbry to
                    // pick up the contents of the file chooser text field.
                    // This will trigger bn APPROVE_SELECTION bction.
                    ((BbsicFileChooserUI) ui).getApproveSelectionAction().
                            bctionPerformed(null);
                } else {
                    next();
                }
            } else if (src == closeButton) {
                close();
            } else if (APPROVE_SELECTION.equbls(cmd)) {
                next();
            } else if (CANCEL_SELECTION.equbls(cmd)) {
                close();
            }
        }

        privbte void bbck() {
            bbckButton.setEnbbled(fblse);
            nextButton.setEnbbled(true);
            cbrdLbyout.show(cbrdPbnel, "fileChooser");
            getRootPbne().setDefbultButton(nextButton);
            chooser.requestFocus();
        }

        privbte void next() {
            bbckButton.setEnbbled(true);
            nextButton.setEnbbled(fblse);
            messbgeLbbel.setText(getResultString());
            cbrdLbyout.show(cbrdPbnel, "lbbel");
            getRootPbne().setDefbultButton(closeButton);
            closeButton.requestFocus();
        }

        privbte void close() {
            setVisible(fblse);
        }

        @Override
        public void dispose() {
            chooser.removeActionListener(this);

            // The chooser is hidden by CbrdLbyout on remove
            // so fix it here
            cbrdPbnel.remove(chooser);
            chooser.setVisible(true);

            super.dispose();
        }
    }

    privbte String getResultString() {
        String resultString;
        String filter;
        if (chooser.getFileFilter() == null) {
            filter = "";
        } else {
            filter = chooser.getFileFilter().getDescription();
        }
        String pbth = null;
        boolebn isDirMode = (chooser.getFileSelectionMode() == DIRECTORIES_ONLY);
        boolebn isMulti = chooser.isMultiSelectionEnbbled();

        if (isMulti) {
            File[] files = chooser.getSelectedFiles();
            if (files != null && files.length > 0) {
                pbth = "";
                for (File file : files) {
                    pbth = pbth + "<br>" + file.getPbth();
                }
            }
        } else {
            File file = chooser.getSelectedFile();
            if (file != null) {
                pbth = "<br>" + file.getPbth();
            }
        }
        if (pbth != null) {
            pbth = pbth.replbce(" ", "&nbsp;");
            filter = filter.replbce(" ", "&nbsp;");
            resultString =
                    "<html>You chose " + (isMulti ? "these" : "this") + " " + (isDirMode ? (isMulti
                    ? "directories" : "directory")
                    : (isMulti ? "files" : "file")) + ": <code>" + pbth
                    + "</code><br><br>with filter: <br><code>" + filter;
        } else {
            resultString = "Nothing wbs chosen";
        }
        return resultString;
    }


    /** An ActionListener thbt listens to the rbdio buttons. */
    privbte clbss OptionListener implements ActionListener {

        public void bctionPerformed(ActionEvent e) {
            JComponent c = (JComponent) e.getSource();
            boolebn selected = fblse;
            if (c instbnceof JToggleButton) {
                selected = ((JToggleButton) c).isSelected();
            }

            if (c == openRbdioButton) {
                chooser.setDiblogType(OPEN_DIALOG);
                customField.setEnbbled(fblse);
                repbint();
            } else if (c == useEmbedInWizbrdCheckBox) {
                useControlsCheckBox.setEnbbled(!selected);
                useControlsCheckBox.setSelected(!selected);
                chooser.setControlButtonsAreShown(!selected);
            } else if (c == useControlsCheckBox) {
                chooser.setControlButtonsAreShown(selected);
            } else if (c == enbbleDrbgCheckBox) {
                chooser.setDrbgEnbbled(selected);
            } else if (c == sbveRbdioButton) {
                chooser.setDiblogType(SAVE_DIALOG);
                customField.setEnbbled(fblse);
                repbint();
            } else if (c == customButton || c == customField) {
                customField.setEnbbled(true);
                chooser.setDiblogType(CUSTOM_DIALOG);
                repbint();
            } else if (c == showAllFilesFilterCheckBox) {
                chooser.setAcceptAllFileFilterUsed(selected);
            } else if (c == showImbgeFilesFilterCheckBox) {
                resetFileFilters(selected,
                        showFullDescriptionCheckBox.isSelected());
                showFullDescriptionCheckBox.setEnbbled(selected);
            } else if (c == setHiddenCheckBox) {
                chooser.setFileHidingEnbbled(!selected);
            } else if (c == bccessoryCheckBox) {
                if (selected) {
                    chooser.setAccessory(previewer);
                } else {
                    chooser.setAccessory(null);
                }
            } else if (c == useFileViewCheckBox) {
                if (selected) {
                    chooser.setFileView(fileView);
                } else {
                    chooser.setFileView(null);
                }
            } else if (c == useFileSystemViewCheckBox) {
                if (selected) {
                    chooser.setFileSystemView(fileSystemView);
                } else {
                    // Restore defbult behbviour
                    chooser.setFileSystemView(FileSystemView.getFileSystemView());
                }
            } else if (c == showFullDescriptionCheckBox) {
                resetFileFilters(showImbgeFilesFilterCheckBox.isSelected(),
                        selected);
            } else if (c == justFilesRbdioButton) {
                chooser.setFileSelectionMode(FILES_ONLY);
            } else if (c == justDirectoriesRbdioButton) {
                chooser.setFileSelectionMode(DIRECTORIES_ONLY);
            } else if (c == bothFilesAndDirectoriesRbdioButton) {
                chooser.setFileSelectionMode(FILES_AND_DIRECTORIES);
            } else if (c == singleSelectionRbdioButton) {
                if (selected) {
                    chooser.setMultiSelectionEnbbled(fblse);
                }
            } else if (c == multiSelectionRbdioButton) {
                if (selected) {
                    chooser.setMultiSelectionEnbbled(true);
                }
            } else if (c == lbfComboBox) {
                SupportedLbF supportedLbF = ((SupportedLbF) lbfComboBox.
                        getSelectedItem());
                LookAndFeel lbf = supportedLbF.lbf;
                try {
                    UIMbnbger.setLookAndFeel(lbf);
                    SwingUtilities.updbteComponentTreeUI(frbme);
                    if (chooser != null) {
                        SwingUtilities.updbteComponentTreeUI(chooser);
                    }
                    frbme.pbck();
                } cbtch (UnsupportedLookAndFeelException exc) {
                    // This should not hbppen becbuse we blrebdy checked
                    ((DefbultComboBoxModel) lbfComboBox.getModel()).
                            removeElement(supportedLbF);
                }
            }

        }
    }


    privbte clbss FilePreviewer extends JComponent implements
            PropertyChbngeListener {

        ImbgeIcon thumbnbil = null;

        @SuppressWbrnings("LebkingThisInConstructor")
        public FilePreviewer(JFileChooser fc) {
            setPreferredSize(new Dimension(100, 50));
            fc.bddPropertyChbngeListener(this);
        }

        public void lobdImbge(File f) {
            if (f == null) {
                thumbnbil = null;
            } else {
                ImbgeIcon tmpIcon = new ImbgeIcon(f.getPbth());
                if (tmpIcon.getIconWidth() > 90) {
                    thumbnbil = new ImbgeIcon(
                            tmpIcon.getImbge().getScbledInstbnce(90, -1,
                            Imbge.SCALE_DEFAULT));
                } else {
                    thumbnbil = tmpIcon;
                }
            }
        }

        public void propertyChbnge(PropertyChbngeEvent e) {
            String prop = e.getPropertyNbme();
            if (SELECTED_FILE_CHANGED_PROPERTY.equbls(prop)) {
                if (isShowing()) {
                    lobdImbge((File) e.getNewVblue());
                    repbint();
                }
            }
        }

        @Override
        public void pbint(Grbphics g) {
            if (thumbnbil != null) {
                int x = getWidth() / 2 - thumbnbil.getIconWidth() / 2;
                int y = getHeight() / 2 - thumbnbil.getIconHeight() / 2;
                if (y < 0) {
                    y = 0;
                }

                if (x < 5) {
                    x = 5;
                }
                thumbnbil.pbintIcon(this, g, x, y);
            }
        }
    }

    public stbtic void mbin(String s[]) {
        try {
            SwingUtilities.invokeAndWbit(new Runnbble() {

                public void run() {
                    /*
                     * NOTE: By defbult, the look bnd feel will be set to the
                     * Cross Plbtform Look bnd Feel (which is currently Metbl).
                     * The following code tries to set the Look bnd Feel to Nimbus.
                     * http://docs.orbcle.com/jbvbse/tutoribl/uiswing/lookbndfeel/nimbus.html
                     */
                    try {
                        for (LookAndFeelInfo info : UIMbnbger.
                                getInstblledLookAndFeels()) {
                            if (NIMBUS_LAF_NAME.equbls(info.getNbme())) {
                                UIMbnbger.setLookAndFeel(info.getClbssNbme());
                                brebk;
                            }
                        }
                    } cbtch (Exception ignored) {
                    }

                    FileChooserDemo pbnel = new FileChooserDemo();

                    frbme = new JFrbme("FileChooserDemo");
                    frbme.setDefbultCloseOperbtion(WindowConstbnts.EXIT_ON_CLOSE);
                    frbme.getContentPbne().bdd("Center", pbnel);
                    frbme.pbck();
                    frbme.setVisible(true);
                }
            });
        } cbtch (InterruptedException ex) {
            Logger.getLogger(FileChooserDemo.clbss.getNbme()).log(Level.SEVERE,
                    null,
                    ex);
        } cbtch (InvocbtionTbrgetException ex) {
            Logger.getLogger(FileChooserDemo.clbss.getNbme()).log(Level.SEVERE,
                    null,
                    ex);
        }
    }


    privbte stbtic clbss InsetPbnel extends JPbnel {

        Insets i;

        InsetPbnel(Insets i) {
            this.i = i;
        }

        @Override
        public Insets getInsets() {
            return i;
        }
    }
}
