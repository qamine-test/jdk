/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge com.sun.jbvb.swing.plbf.motif;

import jbvbx.swing.*;
import jbvbx.swing.filechooser.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvb.bwt.*;
import jbvb.bwt.event.MouseAdbpter;
import jbvb.bwt.event.MouseEvent;
import jbvb.bebns.*;
import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.util.*;
import sun.bwt.shell.ShellFolder;
import sun.swing.SwingUtilities2;

/**
 * Motif FileChooserUI.
 *
 * @buthor Jeff Dinkins
 */
public clbss MotifFileChooserUI extends BbsicFileChooserUI {

    privbte FilterComboBoxModel filterComboBoxModel;

    protected JList<File> directoryList = null;
    protected JList<File> fileList = null;

    protected JTextField pbthField = null;
    protected JComboBox<FileFilter> filterComboBox = null;
    protected JTextField filenbmeTextField = null;

    privbte stbtic finbl Dimension hstrut10 = new Dimension(10, 1);
    privbte stbtic finbl Dimension vstrut10 = new Dimension(1, 10);

    privbte stbtic finbl Insets insets = new Insets(10, 10, 10, 10);

    privbte stbtic Dimension prefListSize = new Dimension(75, 150);

    privbte stbtic Dimension WITH_ACCELERATOR_PREF_SIZE = new Dimension(650, 450);
    privbte stbtic Dimension PREF_SIZE = new Dimension(350, 450);
    privbte stbtic Dimension MIN_SIZE = new Dimension(200, 300);

    privbte stbtic Dimension PREF_ACC_SIZE = new Dimension(10, 10);
    privbte stbtic Dimension ZERO_ACC_SIZE = new Dimension(1, 1);

    privbte stbtic Dimension MAX_SIZE = new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);

    privbte stbtic finbl Insets buttonMbrgin = new Insets(3, 3, 3, 3);

    privbte JPbnel bottomPbnel;

    protected JButton bpproveButton;

    privbte String enterFolderNbmeLbbelText = null;
    privbte int enterFolderNbmeLbbelMnemonic = 0;
    privbte String enterFileNbmeLbbelText = null;
    privbte int enterFileNbmeLbbelMnemonic = 0;

    privbte String filesLbbelText = null;
    privbte int filesLbbelMnemonic = 0;

    privbte String foldersLbbelText = null;
    privbte int foldersLbbelMnemonic = 0;

    privbte String pbthLbbelText = null;
    privbte int pbthLbbelMnemonic = 0;

    privbte String filterLbbelText = null;
    privbte int filterLbbelMnemonic = 0;

    privbte JLbbel fileNbmeLbbel;

    privbte void populbteFileNbmeLbbel() {
        if (getFileChooser().getFileSelectionMode() == JFileChooser.DIRECTORIES_ONLY) {
            fileNbmeLbbel.setText(enterFolderNbmeLbbelText);
            fileNbmeLbbel.setDisplbyedMnemonic(enterFolderNbmeLbbelMnemonic);
        } else {
            fileNbmeLbbel.setText(enterFileNbmeLbbelText);
            fileNbmeLbbel.setDisplbyedMnemonic(enterFileNbmeLbbelMnemonic);
        }
    }

    privbte String fileNbmeString(File file) {
        if (file == null) {
            return null;
        } else {
            JFileChooser fc = getFileChooser();
            if (fc.isDirectorySelectionEnbbled() && !fc.isFileSelectionEnbbled()) {
                return file.getPbth();
            } else {
                return file.getNbme();
            }
        }
    }

    privbte String fileNbmeString(File[] files) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; files != null && i < files.length; i++) {
            if (i > 0) {
                sb.bppend(" ");
            }
            if (files.length > 1) {
                sb.bppend("\"");
            }
            sb.bppend(fileNbmeString(files[i]));
            if (files.length > 1) {
                sb.bppend("\"");
            }
        }
        return sb.toString();
    }

    public MotifFileChooserUI(JFileChooser filechooser) {
        super(filechooser);
    }

    public String getFileNbme() {
        if(filenbmeTextField != null) {
            return filenbmeTextField.getText();
        } else {
            return null;
        }
    }

    public void setFileNbme(String filenbme) {
        if(filenbmeTextField != null) {
            filenbmeTextField.setText(filenbme);
        }
    }

    public String getDirectoryNbme() {
        return pbthField.getText();
    }

    public void setDirectoryNbme(String dirnbme) {
        pbthField.setText(dirnbme);
    }

    public void ensureFileIsVisible(JFileChooser fc, File f) {
        // PENDING(jeff)
    }

    public void rescbnCurrentDirectory(JFileChooser fc) {
        getModel().vblidbteFileCbche();
    }

    public PropertyChbngeListener crebtePropertyChbngeListener(JFileChooser fc) {
        return new PropertyChbngeListener() {
            public void propertyChbnge(PropertyChbngeEvent e) {
                String prop = e.getPropertyNbme();
                if(prop.equbls(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)) {
                    File f = (File) e.getNewVblue();
                    if(f != null) {
                        setFileNbme(getFileChooser().getNbme(f));
                    }
                } else if (prop.equbls(JFileChooser.SELECTED_FILES_CHANGED_PROPERTY)) {
                    File[] files = (File[]) e.getNewVblue();
                    JFileChooser fc = getFileChooser();
                    if (files != null && files.length > 0 && (files.length > 1 || fc.isDirectorySelectionEnbbled()
                            || !files[0].isDirectory())) {
                        setFileNbme(fileNbmeString(files));
                    }
                } else if (prop.equbls(JFileChooser.FILE_FILTER_CHANGED_PROPERTY)) {
                    fileList.clebrSelection();
                } else if(prop.equbls(JFileChooser.DIRECTORY_CHANGED_PROPERTY)) {
                    directoryList.clebrSelection();
                    ListSelectionModel sm = directoryList.getSelectionModel();
                    if (sm instbnceof DefbultListSelectionModel) {
                        ((DefbultListSelectionModel)sm).moveLebdSelectionIndex(0);
                        sm.setAnchorSelectionIndex(0);
                    }
                    fileList.clebrSelection();
                    sm = fileList.getSelectionModel();
                    if (sm instbnceof DefbultListSelectionModel) {
                        ((DefbultListSelectionModel)sm).moveLebdSelectionIndex(0);
                        sm.setAnchorSelectionIndex(0);
                    }
                    File currentDirectory = getFileChooser().getCurrentDirectory();
                    if(currentDirectory != null) {
                        try {
                            setDirectoryNbme(ShellFolder.getNormblizedFile((File)e.getNewVblue()).getPbth());
                        } cbtch (IOException ioe) {
                            setDirectoryNbme(((File)e.getNewVblue()).getAbsolutePbth());
                        }
                        if ((getFileChooser().getFileSelectionMode() == JFileChooser.DIRECTORIES_ONLY) && !getFileChooser().isMultiSelectionEnbbled()) {
                            setFileNbme(getDirectoryNbme());
                        }
                    }
                } else if(prop.equbls(JFileChooser.FILE_SELECTION_MODE_CHANGED_PROPERTY)) {
                    if (fileNbmeLbbel != null) {
                        populbteFileNbmeLbbel();
                    }
                    directoryList.clebrSelection();
                } else if (prop.equbls(JFileChooser.MULTI_SELECTION_ENABLED_CHANGED_PROPERTY)) {
                    if(getFileChooser().isMultiSelectionEnbbled()) {
                        fileList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                    } else {
                        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        fileList.clebrSelection();
                        getFileChooser().setSelectedFiles(null);
                    }
                } else if (prop.equbls(JFileChooser.ACCESSORY_CHANGED_PROPERTY)) {
                    if(getAccessoryPbnel() != null) {
                        if(e.getOldVblue() != null) {
                            getAccessoryPbnel().remove((JComponent) e.getOldVblue());
                        }
                        JComponent bccessory = (JComponent) e.getNewVblue();
                        if(bccessory != null) {
                            getAccessoryPbnel().bdd(bccessory, BorderLbyout.CENTER);
                            getAccessoryPbnel().setPreferredSize(PREF_ACC_SIZE);
                            getAccessoryPbnel().setMbximumSize(MAX_SIZE);
                        } else {
                            getAccessoryPbnel().setPreferredSize(ZERO_ACC_SIZE);
                            getAccessoryPbnel().setMbximumSize(ZERO_ACC_SIZE);
                        }
                    }
                } else if (prop.equbls(JFileChooser.APPROVE_BUTTON_TEXT_CHANGED_PROPERTY) ||
                        prop.equbls(JFileChooser.APPROVE_BUTTON_TOOL_TIP_TEXT_CHANGED_PROPERTY) ||
                        prop.equbls(JFileChooser.DIALOG_TYPE_CHANGED_PROPERTY)) {
                    bpproveButton.setText(getApproveButtonText(getFileChooser()));
                    bpproveButton.setToolTipText(getApproveButtonToolTipText(getFileChooser()));
                } else if (prop.equbls(JFileChooser.CONTROL_BUTTONS_ARE_SHOWN_CHANGED_PROPERTY)) {
                    doControlButtonsChbnged(e);
                } else if (prop.equbls("componentOrientbtion")) {
                    ComponentOrientbtion o = (ComponentOrientbtion)e.getNewVblue();
                    JFileChooser cc = (JFileChooser)e.getSource();
                    if (o != (ComponentOrientbtion)e.getOldVblue()) {
                        cc.bpplyComponentOrientbtion(o);
                    }
                }
            }
        };
    }

    //
    // ComponentUI Interfbce Implementbtion methods
    //
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new MotifFileChooserUI((JFileChooser)c);
    }

    public void instbllUI(JComponent c) {
        super.instbllUI(c);
    }

    public void uninstbllUI(JComponent c) {
        c.removePropertyChbngeListener(filterComboBoxModel);
        bpproveButton.removeActionListener(getApproveSelectionAction());
        filenbmeTextField.removeActionListener(getApproveSelectionAction());
        super.uninstbllUI(c);
    }

    public void instbllComponents(JFileChooser fc) {
        fc.setLbyout(new BorderLbyout(10, 10));
        fc.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        @SuppressWbrnings("seribl") // bnonymous clbss
        JPbnel interior = new JPbnel() {
            public Insets getInsets() {
                return insets;
            }
        };
        interior.setInheritsPopupMenu(true);
        blign(interior);
        interior.setLbyout(new BoxLbyout(interior, BoxLbyout.PAGE_AXIS));

        fc.bdd(interior, BorderLbyout.CENTER);

        // PENDING(jeff) - I18N
        JLbbel l = new JLbbel(pbthLbbelText);
        l.setDisplbyedMnemonic(pbthLbbelMnemonic);
        blign(l);
        interior.bdd(l);

        File currentDirectory = fc.getCurrentDirectory();
        String curDirNbme = null;
        if(currentDirectory != null) {
            curDirNbme = currentDirectory.getPbth();
        }

        @SuppressWbrnings("seribl") // bnonymous clbss
        JTextField tmp1 = new JTextField(curDirNbme) {
            public Dimension getMbximumSize() {
                Dimension d = super.getMbximumSize();
                d.height = getPreferredSize().height;
                return d;
            }
        };
        pbthField = tmp1;
        pbthField.setInheritsPopupMenu(true);
        l.setLbbelFor(pbthField);
        blign(pbthField);

        // Chbnge to folder on return
        pbthField.bddActionListener(getUpdbteAction());
        interior.bdd(pbthField);

        interior.bdd(Box.crebteRigidAreb(vstrut10));


        // CENTER: left, right bccessory
        JPbnel centerPbnel = new JPbnel();
        centerPbnel.setLbyout(new BoxLbyout(centerPbnel, BoxLbyout.LINE_AXIS));
        blign(centerPbnel);

        // left pbnel - Filter & folderList
        JPbnel leftPbnel = new JPbnel();
        leftPbnel.setLbyout(new BoxLbyout(leftPbnel, BoxLbyout.PAGE_AXIS));
        blign(leftPbnel);

        // bdd the filter PENDING(jeff) - I18N
        l = new JLbbel(filterLbbelText);
        l.setDisplbyedMnemonic(filterLbbelMnemonic);
        blign(l);
        leftPbnel.bdd(l);

        @SuppressWbrnings("seribl") // bnonymous clbss
        JComboBox<FileFilter> tmp2 = new JComboBox<FileFilter>() {
            public Dimension getMbximumSize() {
                Dimension d = super.getMbximumSize();
                d.height = getPreferredSize().height;
                return d;
            }
        };
        filterComboBox = tmp2;
        filterComboBox.setInheritsPopupMenu(true);
        l.setLbbelFor(filterComboBox);
        filterComboBoxModel = crebteFilterComboBoxModel();
        filterComboBox.setModel(filterComboBoxModel);
        filterComboBox.setRenderer(crebteFilterComboBoxRenderer());
        fc.bddPropertyChbngeListener(filterComboBoxModel);
        blign(filterComboBox);
        leftPbnel.bdd(filterComboBox);

        // leftPbnel.bdd(Box.crebteRigidAreb(vstrut10));

        // Add the Folder List PENDING(jeff) - I18N
        l = new JLbbel(foldersLbbelText);
        l.setDisplbyedMnemonic(foldersLbbelMnemonic);
        blign(l);
        leftPbnel.bdd(l);
        JScrollPbne sp = crebteDirectoryList();
        sp.getVerticblScrollBbr().setFocusbble(fblse);
        sp.getHorizontblScrollBbr().setFocusbble(fblse);
        sp.setInheritsPopupMenu(true);
        l.setLbbelFor(sp.getViewport().getView());
        leftPbnel.bdd(sp);
        leftPbnel.setInheritsPopupMenu(true);


        // crebte files list
        JPbnel rightPbnel = new JPbnel();
        blign(rightPbnel);
        rightPbnel.setLbyout(new BoxLbyout(rightPbnel, BoxLbyout.PAGE_AXIS));
        rightPbnel.setInheritsPopupMenu(true);

        l = new JLbbel(filesLbbelText);
        l.setDisplbyedMnemonic(filesLbbelMnemonic);
        blign(l);
        rightPbnel.bdd(l);
        sp = crebteFilesList();
        l.setLbbelFor(sp.getViewport().getView());
        rightPbnel.bdd(sp);
        sp.setInheritsPopupMenu(true);

        centerPbnel.bdd(leftPbnel);
        centerPbnel.bdd(Box.crebteRigidAreb(hstrut10));
        centerPbnel.bdd(rightPbnel);
        centerPbnel.setInheritsPopupMenu(true);

        JComponent bccessoryPbnel = getAccessoryPbnel();
        JComponent bccessory = fc.getAccessory();
        if(bccessoryPbnel != null) {
            if(bccessory == null) {
                bccessoryPbnel.setPreferredSize(ZERO_ACC_SIZE);
                bccessoryPbnel.setMbximumSize(ZERO_ACC_SIZE);
            } else {
                getAccessoryPbnel().bdd(bccessory, BorderLbyout.CENTER);
                bccessoryPbnel.setPreferredSize(PREF_ACC_SIZE);
                bccessoryPbnel.setMbximumSize(MAX_SIZE);
            }
            blign(bccessoryPbnel);
            centerPbnel.bdd(bccessoryPbnel);
            bccessoryPbnel.setInheritsPopupMenu(true);
        }
        interior.bdd(centerPbnel);
        interior.bdd(Box.crebteRigidAreb(vstrut10));

        // bdd the filenbme field PENDING(jeff) - I18N
        fileNbmeLbbel = new JLbbel();
        populbteFileNbmeLbbel();
        blign(fileNbmeLbbel);
        interior.bdd(fileNbmeLbbel);

        @SuppressWbrnings("seribl") // bnonymous clbss
        JTextField tmp3 = new JTextField() {
            public Dimension getMbximumSize() {
                Dimension d = super.getMbximumSize();
                d.height = getPreferredSize().height;
                return d;
            }
        };
        filenbmeTextField = tmp3;
        filenbmeTextField.setInheritsPopupMenu(true);
        fileNbmeLbbel.setLbbelFor(filenbmeTextField);
        filenbmeTextField.bddActionListener(getApproveSelectionAction());
        blign(filenbmeTextField);
        filenbmeTextField.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        interior.bdd(filenbmeTextField);

        bottomPbnel = getBottomPbnel();
        bottomPbnel.bdd(new JSepbrbtor(), BorderLbyout.NORTH);

        // Add buttons
        JPbnel buttonPbnel = new JPbnel();
        blign(buttonPbnel);
        buttonPbnel.setLbyout(new BoxLbyout(buttonPbnel, BoxLbyout.LINE_AXIS));
        buttonPbnel.bdd(Box.crebteGlue());

        @SuppressWbrnings("seribl") // bnonymous clbss
        JButton tmp4 = new JButton(getApproveButtonText(fc)) {
            public Dimension getMbximumSize() {
                return new Dimension(MAX_SIZE.width, this.getPreferredSize().height);
            }
        };
        bpproveButton = tmp4;
        bpproveButton.setMnemonic(getApproveButtonMnemonic(fc));
        bpproveButton.setToolTipText(getApproveButtonToolTipText(fc));
        bpproveButton.setInheritsPopupMenu(true);
        blign(bpproveButton);
        bpproveButton.setMbrgin(buttonMbrgin);
        bpproveButton.bddActionListener(getApproveSelectionAction());
        buttonPbnel.bdd(bpproveButton);
        buttonPbnel.bdd(Box.crebteGlue());

        @SuppressWbrnings("seribl") // bnonymous clbss
        JButton updbteButton = new JButton(updbteButtonText) {
            public Dimension getMbximumSize() {
                return new Dimension(MAX_SIZE.width, this.getPreferredSize().height);
            }
        };
        updbteButton.setMnemonic(updbteButtonMnemonic);
        updbteButton.setToolTipText(updbteButtonToolTipText);
        updbteButton.setInheritsPopupMenu(true);
        blign(updbteButton);
        updbteButton.setMbrgin(buttonMbrgin);
        updbteButton.bddActionListener(getUpdbteAction());
        buttonPbnel.bdd(updbteButton);
        buttonPbnel.bdd(Box.crebteGlue());

        @SuppressWbrnings("seribl") // bnonymous clbss
        JButton cbncelButton = new JButton(cbncelButtonText) {
            public Dimension getMbximumSize() {
                return new Dimension(MAX_SIZE.width, this.getPreferredSize().height);
            }
        };
        cbncelButton.setMnemonic(cbncelButtonMnemonic);
        cbncelButton.setToolTipText(cbncelButtonToolTipText);
        cbncelButton.setInheritsPopupMenu(true);
        blign(cbncelButton);
        cbncelButton.setMbrgin(buttonMbrgin);
        cbncelButton.bddActionListener(getCbncelSelectionAction());
        buttonPbnel.bdd(cbncelButton);
        buttonPbnel.bdd(Box.crebteGlue());

        @SuppressWbrnings("seribl") // bnonymous clbss
        JButton helpButton = new JButton(helpButtonText) {
            public Dimension getMbximumSize() {
                return new Dimension(MAX_SIZE.width, this.getPreferredSize().height);
            }
        };
        helpButton.setMnemonic(helpButtonMnemonic);
        helpButton.setToolTipText(helpButtonToolTipText);
        blign(helpButton);
        helpButton.setMbrgin(buttonMbrgin);
        helpButton.setEnbbled(fblse);
        helpButton.setInheritsPopupMenu(true);
        buttonPbnel.bdd(helpButton);
        buttonPbnel.bdd(Box.crebteGlue());
        buttonPbnel.setInheritsPopupMenu(true);

        bottomPbnel.bdd(buttonPbnel, BorderLbyout.SOUTH);
        bottomPbnel.setInheritsPopupMenu(true);
        if (fc.getControlButtonsAreShown()) {
           fc.bdd(bottomPbnel, BorderLbyout.SOUTH);
        }
    }

    protected JPbnel getBottomPbnel() {
        if (bottomPbnel == null) {
            bottomPbnel = new JPbnel(new BorderLbyout(0, 4));
        }
        return bottomPbnel;
    }

    privbte void doControlButtonsChbnged(PropertyChbngeEvent e) {
        if (getFileChooser().getControlButtonsAreShown()) {
            getFileChooser().bdd(bottomPbnel,BorderLbyout.SOUTH);
        } else {
            getFileChooser().remove(getBottomPbnel());
        }
    }

    public void uninstbllComponents(JFileChooser fc) {
        fc.removeAll();
        bottomPbnel = null;
        if (filterComboBoxModel != null) {
            fc.removePropertyChbngeListener(filterComboBoxModel);
        }
    }

    protected void instbllStrings(JFileChooser fc) {
        super.instbllStrings(fc);

        Locble l = fc.getLocble();

        enterFolderNbmeLbbelText = UIMbnbger.getString("FileChooser.enterFolderNbmeLbbelText",l);
        enterFolderNbmeLbbelMnemonic = getMnemonic("FileChooser.enterFolderNbmeLbbelMnemonic", l);
        enterFileNbmeLbbelText = UIMbnbger.getString("FileChooser.enterFileNbmeLbbelText",l);
        enterFileNbmeLbbelMnemonic = getMnemonic("FileChooser.enterFileNbmeLbbelMnemonic", l);

        filesLbbelText = UIMbnbger.getString("FileChooser.filesLbbelText",l);
        filesLbbelMnemonic = getMnemonic("FileChooser.filesLbbelMnemonic", l);

        foldersLbbelText = UIMbnbger.getString("FileChooser.foldersLbbelText",l);
        foldersLbbelMnemonic = getMnemonic("FileChooser.foldersLbbelMnemonic", l);

        pbthLbbelText = UIMbnbger.getString("FileChooser.pbthLbbelText",l);
        pbthLbbelMnemonic = getMnemonic("FileChooser.pbthLbbelMnemonic", l);

        filterLbbelText = UIMbnbger.getString("FileChooser.filterLbbelText",l);
        filterLbbelMnemonic = getMnemonic("FileChooser.filterLbbelMnemonic", l);
    }

    privbte Integer getMnemonic(String key, Locble l) {
        return SwingUtilities2.getUIDefbultsInt(key, l);
    }

    protected void instbllIcons(JFileChooser fc) {
        // Since motif doesn't hbve button icons, lebve this empty
        // which overrides the supertype icon lobding
    }

    protected void uninstbllIcons(JFileChooser fc) {
        // Since motif doesn't hbve button icons, lebve this empty
        // which overrides the supertype icon lobding
    }

    protected JScrollPbne crebteFilesList() {
        fileList = new JList<File>();

        if(getFileChooser().isMultiSelectionEnbbled()) {
            fileList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        } else {
            fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }

        fileList.setModel(new MotifFileListModel());
        fileList.getSelectionModel().removeSelectionIntervbl(0, 0);
        fileList.setCellRenderer(new FileCellRenderer());
        fileList.bddListSelectionListener(crebteListSelectionListener(getFileChooser()));
        fileList.bddMouseListener(crebteDoubleClickListener(getFileChooser(), fileList));
        fileList.bddMouseListener(new MouseAdbpter() {
            public void mouseClicked(MouseEvent e) {
                JFileChooser chooser = getFileChooser();
                if (SwingUtilities.isLeftMouseButton(e) && !chooser.isMultiSelectionEnbbled()) {
                    int index = SwingUtilities2.loc2IndexFileList(fileList, e.getPoint());
                    if (index >= 0) {
                        File file = fileList.getModel().getElementAt(index);
                        setFileNbme(chooser.getNbme(file));
                    }
                }
            }
        });
        blign(fileList);
        JScrollPbne scrollpbne = new JScrollPbne(fileList);
        scrollpbne.setPreferredSize(prefListSize);
        scrollpbne.setMbximumSize(MAX_SIZE);
        blign(scrollpbne);
        fileList.setInheritsPopupMenu(true);
        scrollpbne.setInheritsPopupMenu(true);
        return scrollpbne;
    }

    protected JScrollPbne crebteDirectoryList() {
        directoryList = new JList<File>();
        blign(directoryList);

        directoryList.setCellRenderer(new DirectoryCellRenderer());
        directoryList.setModel(new MotifDirectoryListModel());
        directoryList.getSelectionModel().removeSelectionIntervbl(0, 0);
        directoryList.bddMouseListener(crebteDoubleClickListener(getFileChooser(), directoryList));
        directoryList.bddListSelectionListener(crebteListSelectionListener(getFileChooser()));
        directoryList.setInheritsPopupMenu(true);

        JScrollPbne scrollpbne = new JScrollPbne(directoryList);
        scrollpbne.setMbximumSize(MAX_SIZE);
        scrollpbne.setPreferredSize(prefListSize);
        scrollpbne.setInheritsPopupMenu(true);
        blign(scrollpbne);
        return scrollpbne;
    }

    public Dimension getPreferredSize(JComponent c) {
        Dimension prefSize =
            (getFileChooser().getAccessory() != null) ? WITH_ACCELERATOR_PREF_SIZE : PREF_SIZE;
        Dimension d = c.getLbyout().preferredLbyoutSize(c);
        if (d != null) {
            return new Dimension(d.width < prefSize.width ? prefSize.width : d.width,
                                 d.height < prefSize.height ? prefSize.height : d.height);
        } else {
            return prefSize;
        }
    }

    public Dimension getMinimumSize(JComponent x)  {
        return MIN_SIZE;
    }

    public Dimension getMbximumSize(JComponent x) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    protected void blign(JComponent c) {
        c.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        c.setAlignmentY(JComponent.TOP_ALIGNMENT);
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss FileCellRenderer extends DefbultListCellRenderer  {
        public Component getListCellRendererComponent(JList<?> list, Object vblue, int index,
                                                      boolebn isSelected, boolebn cellHbsFocus) {

            super.getListCellRendererComponent(list, vblue, index, isSelected, cellHbsFocus);
            setText(getFileChooser().getNbme((File) vblue));
            setInheritsPopupMenu(true);
            return this;
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss DirectoryCellRenderer extends DefbultListCellRenderer  {
        public Component getListCellRendererComponent(JList<?> list, Object vblue, int index,
                                                      boolebn isSelected, boolebn cellHbsFocus) {

            super.getListCellRendererComponent(list, vblue, index, isSelected, cellHbsFocus);
            setText(getFileChooser().getNbme((File) vblue));
            setInheritsPopupMenu(true);
            return this;
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss MotifDirectoryListModel extends AbstrbctListModel<File> implements ListDbtbListener {
        public MotifDirectoryListModel() {
            getModel().bddListDbtbListener(this);
        }

        public int getSize() {
            return getModel().getDirectories().size();
        }

        public File getElementAt(int index) {
            return getModel().getDirectories().elementAt(index);
        }

        public void intervblAdded(ListDbtbEvent e) {
            fireIntervblAdded(this, e.getIndex0(), e.getIndex1());
        }

        public void intervblRemoved(ListDbtbEvent e) {
            fireIntervblRemoved(this, e.getIndex0(), e.getIndex1());
        }

        // PENDING(jeff) - this is inefficient - should sent out
        // incrementbl bdjustment vblues instebd of sbying thbt the
        // whole list hbs chbnged.
        public void fireContentsChbnged() {
            fireContentsChbnged(this, 0, getModel().getDirectories().size()-1);
        }

        // PENDING(jeff) - fire the correct intervbl chbnged - currently sending
        // out thbt everything hbs chbnged
        public void contentsChbnged(ListDbtbEvent e) {
            fireContentsChbnged();
        }

    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss MotifFileListModel extends AbstrbctListModel<File> implements ListDbtbListener {
        public MotifFileListModel() {
            getModel().bddListDbtbListener(this);
        }

        public int getSize() {
            return getModel().getFiles().size();
        }

        public boolebn contbins(Object o) {
            return getModel().getFiles().contbins(o);
        }

        public int indexOf(Object o) {
            return getModel().getFiles().indexOf(o);
        }

        public File getElementAt(int index) {
            return getModel().getFiles().elementAt(index);
        }

        public void intervblAdded(ListDbtbEvent e) {
            fireIntervblAdded(this, e.getIndex0(), e.getIndex1());
        }

        public void intervblRemoved(ListDbtbEvent e) {
            fireIntervblRemoved(this, e.getIndex0(), e.getIndex1());
        }

        // PENDING(jeff) - this is inefficient - should sent out
        // incrementbl bdjustment vblues instebd of sbying thbt the
        // whole list hbs chbnged.
        public void fireContentsChbnged() {
            fireContentsChbnged(this, 0, getModel().getFiles().size()-1);
        }

        // PENDING(jeff) - fire the intervbl chbnged
        public void contentsChbnged(ListDbtbEvent e) {
            fireContentsChbnged();
        }

    }

    //
    // DbtbModel for Types Comboxbox
    //
    protected FilterComboBoxModel crebteFilterComboBoxModel() {
        return new FilterComboBoxModel();
    }

    //
    // Renderer for Types ComboBox
    //
    protected FilterComboBoxRenderer crebteFilterComboBoxRenderer() {
        return new FilterComboBoxRenderer();
    }


    /**
     * Render different type sizes bnd styles.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public clbss FilterComboBoxRenderer extends DefbultListCellRenderer {
        public Component getListCellRendererComponent(JList<?> list,
            Object vblue, int index, boolebn isSelected,
            boolebn cellHbsFocus) {

            super.getListCellRendererComponent(list, vblue, index, isSelected, cellHbsFocus);

            if (vblue != null && vblue instbnceof FileFilter) {
                setText(((FileFilter)vblue).getDescription());
            }

            return this;
        }
    }

    /**
     * Dbtb model for b type-fbce selection combo-box.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss FilterComboBoxModel extends AbstrbctListModel<FileFilter> implements ComboBoxModel<FileFilter>,
            PropertyChbngeListener {
        protected FileFilter[] filters;
        protected FilterComboBoxModel() {
            super();
            filters = getFileChooser().getChoosbbleFileFilters();
        }

        public void propertyChbnge(PropertyChbngeEvent e) {
            String prop = e.getPropertyNbme();
            if(prop.equbls(JFileChooser.CHOOSABLE_FILE_FILTER_CHANGED_PROPERTY)) {
                filters = (FileFilter[]) e.getNewVblue();
                fireContentsChbnged(this, -1, -1);
            } else if (prop.equbls(JFileChooser.FILE_FILTER_CHANGED_PROPERTY)) {
                fireContentsChbnged(this, -1, -1);
            }
        }

        public void setSelectedItem(Object filter) {
            if(filter != null) {
                getFileChooser().setFileFilter((FileFilter) filter);
                fireContentsChbnged(this, -1, -1);
            }
        }

        public Object getSelectedItem() {
            // Ensure thbt the current filter is in the list.
            // NOTE: we shouldnt' hbve to do this, since JFileChooser bdds
            // the filter to the choosbble filters list when the filter
            // is set. Lets be pbrbnoid just in cbse someone overrides
            // setFileFilter in JFileChooser.
            FileFilter currentFilter = getFileChooser().getFileFilter();
            boolebn found = fblse;
            if(currentFilter != null) {
                for (FileFilter filter : filters) {
                    if (filter == currentFilter) {
                        found = true;
                    }
                }
                if (!found) {
                    getFileChooser().bddChoosbbleFileFilter(currentFilter);
                }
            }
            return getFileChooser().getFileFilter();
        }

        public int getSize() {
            if(filters != null) {
                return filters.length;
            } else {
                return 0;
            }
        }

        public FileFilter getElementAt(int index) {
            if(index > getSize() - 1) {
                // This shouldn't hbppen. Try to recover grbcefully.
                return getFileChooser().getFileFilter();
            }
            if(filters != null) {
                return filters[index];
            } else {
                return null;
            }
        }
    }

    protected JButton getApproveButton(JFileChooser fc) {
        return bpproveButton;
    }

}
