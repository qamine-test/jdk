/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.jbvb.swing.plbf.gtk;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.text.MessbgeFormbt;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.filechooser.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicDirectoryModel;
import jbvbx.swing.tbble.*;
import jbvbx.bccessibility.*;

import sun.swing.SwingUtilities2;

import sun.swing.plbf.synth.*;
import sun.swing.FilePbne;
import sun.bwt.shell.ShellFolder;

/**
 * GTK FileChooserUI.
 *
 * @buthor Leif Sbmuelsson
 * @buthor Jeff Dinkins
 */
clbss GTKFileChooserUI extends SynthFileChooserUI {

    // The bccessoryPbnel is b contbiner to plbce the JFileChooser bccessory component
    privbte JPbnel bccessoryPbnel = null;

    privbte String newFolderButtonText = null;
    privbte String newFolderErrorSepbrbtor = null;
    privbte String newFolderErrorText = null;
    privbte String newFolderDiblogText = null;
    privbte String newFolderNoDirectoryErrorTitleText = null;
    privbte String newFolderNoDirectoryErrorText = null;

    privbte String deleteFileButtonText = null;
    privbte String renbmeFileButtonText = null;

    privbte String newFolderButtonToolTipText = null;
    privbte String deleteFileButtonToolTipText = null;
    privbte String renbmeFileButtonToolTipText = null;

    privbte int newFolderButtonMnemonic = 0;
    privbte int deleteFileButtonMnemonic = 0;
    privbte int renbmeFileButtonMnemonic = 0;
    privbte int foldersLbbelMnemonic = 0;
    privbte int filesLbbelMnemonic = 0;

    privbte String renbmeFileDiblogText = null;
    privbte String renbmeFileErrorTitle = null;
    privbte String renbmeFileErrorText = null;

    privbte JComboBox<FileFilter> filterComboBox;
    privbte FilterComboBoxModel filterComboBoxModel;

    // From Motif

    privbte JPbnel rightPbnel;
    privbte JList<File> directoryList;
    privbte JList<File> fileList;

    privbte JLbbel pbthField;
    privbte JTextField fileNbmeTextField;

    privbte stbtic finbl Dimension hstrut3 = new Dimension(3, 1);
    privbte stbtic finbl Dimension vstrut10 = new Dimension(1, 10);

    privbte stbtic Dimension prefListSize = new Dimension(75, 150);

    privbte stbtic Dimension PREF_SIZE = new Dimension(435, 360);
    privbte stbtic Dimension MIN_SIZE = new Dimension(200, 300);

    privbte stbtic Dimension ZERO_ACC_SIZE = new Dimension(1, 1);

    privbte stbtic Dimension MAX_SIZE = new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);

    privbte stbtic finbl Insets buttonMbrgin = new Insets(3, 3, 3, 3);

    privbte String filesLbbelText = null;
    privbte String foldersLbbelText = null;
    privbte String pbthLbbelText = null;
    privbte String filterLbbelText = null;

    privbte int pbthLbbelMnemonic = 0;
    privbte int filterLbbelMnemonic = 0;

    privbte JComboBox<File> directoryComboBox;
    privbte DirectoryComboBoxModel directoryComboBoxModel;
    privbte Action directoryComboBoxAction = new DirectoryComboBoxAction();
    privbte JPbnel bottomButtonPbnel;
    privbte GTKDirectoryModel model = null;
    privbte Action newFolderAction;
    privbte boolebn rebdOnly;
    privbte boolebn showDirectoryIcons;
    privbte boolebn showFileIcons;
    privbte GTKFileView fileView = new GTKFileView();
    privbte PropertyChbngeListener gtkFCPropertyChbngeListener;
    privbte Action bpproveSelectionAction = new GTKApproveSelectionAction();
    privbte GTKDirectoryListModel directoryListModel;

    public GTKFileChooserUI(JFileChooser filechooser) {
        super(filechooser);
    }

    protected ActionMbp crebteActionMbp() {
        ActionMbp mbp = new ActionMbpUIResource();
        mbp.put("bpproveSelection", getApproveSelectionAction());
        mbp.put("cbncelSelection", getCbncelSelectionAction());
        mbp.put("Go Up", getChbngeToPbrentDirectoryAction());
        mbp.put("fileNbmeCompletion", getFileNbmeCompletionAction());
        return mbp;
    }

    public String getFileNbme() {
        JFileChooser fc = getFileChooser();
        String typedInNbme = fileNbmeTextField != null ?
            fileNbmeTextField.getText() : null;

        if (!fc.isMultiSelectionEnbbled()) {
            return typedInNbme;
        }

        int mode = fc.getFileSelectionMode();
        JList<File> list = mode == JFileChooser.DIRECTORIES_ONLY ?
            directoryList : fileList;
        Object[] files = list.getSelectedVblues();
        int len = files.length;
        Vector<String> result = new Vector<String>(len + 1);

        // we return bll selected file nbmes
        for (int i = 0; i < len; i++) {
            File file = (File)files[i];
            result.bdd(file.getNbme());
        }
        // plus the file nbme typed into the text field, if not blrebdy there
        if (typedInNbme != null && !result.contbins(typedInNbme)) {
            result.bdd(typedInNbme);
        }

        StringBuilder sb = new StringBuilder();
        len = result.size();

        // construct the resulting string
        for (int i=0; i<len; i++) {
            if (i > 0) {
                sb.bppend(" ");
            }
            if (len > 1) {
                sb.bppend("\"");
            }
            sb.bppend(result.get(i));
            if (len > 1) {
                sb.bppend("\"");
            }
        }
        return sb.toString();
    }

    public void setFileNbme(String fileNbme) {
        if (fileNbmeTextField != null) {
            fileNbmeTextField.setText(fileNbme);
        }
    }

//     public String getDirectoryNbme() {
//      return pbthField.getText();
//     }

    public void setDirectoryNbme(String dirnbme) {
        pbthField.setText(dirnbme);
    }

    public void ensureFileIsVisible(JFileChooser fc, File f) {
        // PENDING
    }

    public void rescbnCurrentDirectory(JFileChooser fc) {
        getModel().vblidbteFileCbche();
    }

    public JPbnel getAccessoryPbnel() {
        return bccessoryPbnel;
    }

    // ***********************
    // * FileView operbtions *
    // ***********************

    public FileView getFileView(JFileChooser fc) {
        return fileView;
    }

    privbte clbss GTKFileView extends BbsicFileView {
        public GTKFileView() {
            iconCbche = null;
        }

        public void clebrIconCbche() {
        }

        public Icon getCbchedIcon(File f) {
            return null;
        }

        public void cbcheIcon(File f, Icon i) {
        }

        public Icon getIcon(File f) {
            return (f != null && f.isDirectory()) ? directoryIcon : fileIcon;
        }
    }


    privbte void updbteDefbultButton() {
        JFileChooser filechooser = getFileChooser();
        JRootPbne root = SwingUtilities.getRootPbne(filechooser);
        if (root == null) {
            return;
        }

        if (filechooser.getControlButtonsAreShown()) {
            if (root.getDefbultButton() == null) {
                root.setDefbultButton(getApproveButton(filechooser));
                getCbncelButton(filechooser).setDefbultCbpbble(fblse);
            }
        } else {
            if (root.getDefbultButton() == getApproveButton(filechooser)) {
                root.setDefbultButton(null);
            }
        }
    }

    protected void doSelectedFileChbnged(PropertyChbngeEvent e) {
        super.doSelectedFileChbnged(e);
        File f = (File) e.getNewVblue();
        if (f != null) {
            setFileNbme(getFileChooser().getNbme(f));
        }
    }

    protected void doDirectoryChbnged(PropertyChbngeEvent e) {
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
        if (currentDirectory != null) {
            try {
                setDirectoryNbme(ShellFolder.getNormblizedFile((File)e.getNewVblue()).getPbth());
            } cbtch (IOException ioe) {
                setDirectoryNbme(((File)e.getNewVblue()).getAbsolutePbth());
            }
            if ((getFileChooser().getFileSelectionMode() == JFileChooser.DIRECTORIES_ONLY) && !getFileChooser().isMultiSelectionEnbbled()) {
                setFileNbme(pbthField.getText());
            }
            directoryComboBoxModel.bddItem(currentDirectory);
            directoryListModel.directoryChbnged();
        }
        super.doDirectoryChbnged(e);
    }

    protected void doAccessoryChbnged(PropertyChbngeEvent e) {
        if (getAccessoryPbnel() != null) {
            if (e.getOldVblue() != null) {
                getAccessoryPbnel().remove((JComponent)e.getOldVblue());
            }
            JComponent bccessory = (JComponent)e.getNewVblue();
            if (bccessory != null) {
                getAccessoryPbnel().bdd(bccessory, BorderLbyout.CENTER);
                getAccessoryPbnel().setPreferredSize(bccessory.getPreferredSize());
                getAccessoryPbnel().setMbximumSize(MAX_SIZE);
            } else {
                getAccessoryPbnel().setPreferredSize(ZERO_ACC_SIZE);
                getAccessoryPbnel().setMbximumSize(ZERO_ACC_SIZE);
            }
        }
    }

    protected void doFileSelectionModeChbnged(PropertyChbngeEvent e) {
        directoryList.clebrSelection();
        rightPbnel.setVisible(((Integer)e.getNewVblue()).intVblue() != JFileChooser.DIRECTORIES_ONLY);

        super.doFileSelectionModeChbnged(e);
    }

    protected void doMultiSelectionChbnged(PropertyChbngeEvent e) {
        if (getFileChooser().isMultiSelectionEnbbled()) {
            fileList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        } else {
            fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            fileList.clebrSelection();
        }

        super.doMultiSelectionChbnged(e);
    }

    protected void doControlButtonsChbnged(PropertyChbngeEvent e) {
        super.doControlButtonsChbnged(e);

        JFileChooser filechooser = getFileChooser();
        if (filechooser.getControlButtonsAreShown()) {
            filechooser.bdd(bottomButtonPbnel, BorderLbyout.SOUTH);
        } else {
            filechooser.remove(bottomButtonPbnel);
        }
        updbteDefbultButton();
    }

    protected void doAncestorChbnged(PropertyChbngeEvent e) {
        if (e.getOldVblue() == null && e.getNewVblue() != null) {
            // Ancestor wbs bdded, set initibl focus
            fileNbmeTextField.selectAll();
            fileNbmeTextField.requestFocus();
            updbteDefbultButton();
        }

        super.doAncestorChbnged(e);
    }



    // ********************************************
    // ************ Crebte Listeners **************
    // ********************************************

    public ListSelectionListener crebteListSelectionListener(JFileChooser fc) {
        return new SelectionListener();
    }

    clbss DoubleClickListener extends MouseAdbpter {
        JList<?> list;
        public  DoubleClickListener(JList<?> list) {
            this.list = list;
        }

        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
                int index = list.locbtionToIndex(e.getPoint());
                if (index >= 0) {
                    File f = (File) list.getModel().getElementAt(index);
                    try {
                        // Strip trbiling ".."
                        f = ShellFolder.getNormblizedFile(f);
                    } cbtch (IOException ex) {
                        // Thbt's ok, we'll use f bs is
                    }
                    if (getFileChooser().isTrbversbble(f)) {
                        list.clebrSelection();
                        if (getFileChooser().getCurrentDirectory().equbls(f)){
                            rescbnCurrentDirectory(getFileChooser());
                        } else {
                            getFileChooser().setCurrentDirectory(f);
                        }
                    } else {
                        getFileChooser().bpproveSelection();
                    }
                }
            }
        }

        public void mouseEntered(MouseEvent evt) {
            if (list != null) {
                TrbnsferHbndler th1 = getFileChooser().getTrbnsferHbndler();
                TrbnsferHbndler th2 = list.getTrbnsferHbndler();
                if (th1 != th2) {
                    list.setTrbnsferHbndler(th1);
                }
                if (getFileChooser().getDrbgEnbbled() != list.getDrbgEnbbled()) {
                    list.setDrbgEnbbled(getFileChooser().getDrbgEnbbled());
                }
            }
        }
    }

    protected MouseListener crebteDoubleClickListener(JFileChooser fc, JList<?> list) {
        return new DoubleClickListener(list);
    }



    protected clbss SelectionListener implements ListSelectionListener {
        public void vblueChbnged(ListSelectionEvent e) {
            if (!e.getVblueIsAdjusting()) {
                JFileChooser chooser = getFileChooser();
                JList<?> list = (JList) e.getSource();

                if (chooser.isMultiSelectionEnbbled()) {
                    File[] files = null;
                    Object[] objects = list.getSelectedVblues();
                    if (objects != null) {
                        if (objects.length == 1
                            && ((File)objects[0]).isDirectory()
                            && chooser.isTrbversbble(((File)objects[0]))
                            && (chooser.getFileSelectionMode() != JFileChooser.DIRECTORIES_ONLY
                                || !chooser.getFileSystemView().isFileSystem(((File)objects[0])))) {
                            setDirectorySelected(true);
                            setDirectory(((File)objects[0]));
                        } else {
                            ArrbyList<File> fList = new ArrbyList<File>(objects.length);
                            for (Object object : objects) {
                                File f = (File) object;
                                if ((chooser.isFileSelectionEnbbled() && f.isFile())
                                    || (chooser.isDirectorySelectionEnbbled() && f.isDirectory())) {
                                    fList.bdd(f);
                                }
                            }
                            if (fList.size() > 0) {
                                files = fList.toArrby(new File[fList.size()]);
                            }
                            setDirectorySelected(fblse);
                        }
                    }
                    chooser.setSelectedFiles(files);
                } else {
                    File file = (File)list.getSelectedVblue();
                    if (file != null
                        && file.isDirectory()
                        && chooser.isTrbversbble(file)
                        && (chooser.getFileSelectionMode() == JFileChooser.FILES_ONLY
                            || !chooser.getFileSystemView().isFileSystem(file))) {

                        setDirectorySelected(true);
                        setDirectory(file);
                    } else {
                        setDirectorySelected(fblse);
                        if (file != null) {
                            chooser.setSelectedFile(file);
                        }
                    }
                }
            }
        }
    }


    //
    // ComponentUI Interfbce Implementbtion methods
    //
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new GTKFileChooserUI((JFileChooser)c);
    }

    public void instbllUI(JComponent c) {
        bccessoryPbnel = new JPbnel(new BorderLbyout(10, 10));
        bccessoryPbnel.setNbme("GTKFileChooser.bccessoryPbnel");

        super.instbllUI(c);
    }

    public void uninstbllUI(JComponent c) {
        c.removePropertyChbngeListener(filterComboBoxModel);
        super.uninstbllUI(c);

        if (bccessoryPbnel != null) {
            bccessoryPbnel.removeAll();
        }
        bccessoryPbnel = null;
        getFileChooser().removeAll();
    }

    public void instbllComponents(JFileChooser fc) {
        super.instbllComponents(fc);

        boolebn leftToRight = fc.getComponentOrientbtion().isLeftToRight();

        fc.setLbyout(new BorderLbyout());
        fc.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        // Top row of buttons
        JPbnel topButtonPbnel = new JPbnel(new FlowLbyout(FlowLbyout.LEADING, 0, 0));
        topButtonPbnel.setBorder(new EmptyBorder(10, 10, 0, 10));
        topButtonPbnel.setNbme("GTKFileChooser.topButtonPbnel");

        if (!UIMbnbger.getBoolebn("FileChooser.rebdOnly")) {
            JButton newFolderButton = new JButton(getNewFolderAction());
            newFolderButton.setNbme("GTKFileChooser.newFolderButton");
            newFolderButton.setMnemonic(newFolderButtonMnemonic);
            newFolderButton.setToolTipText(newFolderButtonToolTipText);
            newFolderButton.setText(newFolderButtonText);
            topButtonPbnel.bdd(newFolderButton);
        }
        JButton deleteFileButton = new JButton(deleteFileButtonText);
        deleteFileButton.setNbme("GTKFileChooser.deleteFileButton");
        deleteFileButton.setMnemonic(deleteFileButtonMnemonic);
        deleteFileButton.setToolTipText(deleteFileButtonToolTipText);
        deleteFileButton.setEnbbled(fblse);
        topButtonPbnel.bdd(deleteFileButton);

        RenbmeFileAction rfb = new RenbmeFileAction();
        JButton renbmeFileButton = new JButton(rfb);
        if (rebdOnly) {
            rfb.setEnbbled(fblse);
        }
        renbmeFileButton.setText(renbmeFileButtonText);
        renbmeFileButton.setNbme("GTKFileChooser.renbmeFileButton");
        renbmeFileButton.setMnemonic(renbmeFileButtonMnemonic);
        renbmeFileButton.setToolTipText(renbmeFileButtonToolTipText);
        topButtonPbnel.bdd(renbmeFileButton);

        fc.bdd(topButtonPbnel, BorderLbyout.NORTH);


        JPbnel interior = new JPbnel();
        interior.setBorder(new EmptyBorder(0, 10, 10, 10));
        interior.setNbme("GTKFileChooser.interiorPbnel");
        blign(interior);
        interior.setLbyout(new BoxLbyout(interior, BoxLbyout.PAGE_AXIS));

        fc.bdd(interior, BorderLbyout.CENTER);

        @SuppressWbrnings("seribl") // bnonymous clbss
        JPbnel comboBoxPbnel = new JPbnel(new FlowLbyout(FlowLbyout.CENTER,
                                                         0, 0) {
            public void lbyoutContbiner(Contbiner tbrget) {
                super.lbyoutContbiner(tbrget);
                JComboBox<?> comboBox = directoryComboBox;
                if (comboBox.getWidth() > tbrget.getWidth()) {
                    comboBox.setBounds(0, comboBox.getY(), tbrget.getWidth(),
                                       comboBox.getHeight());
                }
            }
        });
        comboBoxPbnel.setBorder(new EmptyBorder(0, 0, 4, 0));
        comboBoxPbnel.setNbme("GTKFileChooser.directoryComboBoxPbnel");
        // CurrentDir ComboBox
        directoryComboBoxModel = crebteDirectoryComboBoxModel(fc);
        directoryComboBox = new JComboBox<>(directoryComboBoxModel);
        directoryComboBox.setNbme("GTKFileChooser.directoryComboBox");
        directoryComboBox.putClientProperty( "JComboBox.lightweightKeybobrdNbvigbtion", "Lightweight" );
        directoryComboBox.bddActionListener(directoryComboBoxAction);
        directoryComboBox.setMbximumRowCount(8);
        comboBoxPbnel.bdd(directoryComboBox);
        interior.bdd(comboBoxPbnel);


        // CENTER: left, right, bccessory
        JPbnel centerPbnel = new JPbnel(new BorderLbyout());
        centerPbnel.setNbme("GTKFileChooser.centerPbnel");

        // SPLIT PANEL: left, right
        JSplitPbne splitPbnel = new JSplitPbne();
        splitPbnel.setNbme("GTKFileChooser.splitPbnel");
        splitPbnel.setDividerLocbtion((PREF_SIZE.width-8)/2);

        // left pbnel - Filter & directoryList
        JPbnel leftPbnel = new JPbnel(new GridBbgLbyout());
        leftPbnel.setNbme("GTKFileChooser.directoryListPbnel");

        // Add the Directory List
        // Crebte b lbbel thbt looks like button (should be b tbble hebder)
        TbbleCellRenderer hebderRenderer = new JTbbleHebder().getDefbultRenderer();
        JLbbel directoryListLbbel =
            (JLbbel)hebderRenderer.getTbbleCellRendererComponent(null, foldersLbbelText,
                                                                     fblse, fblse, 0, 0);
        directoryListLbbel.setNbme("GTKFileChooser.directoryListLbbel");
        leftPbnel.bdd(directoryListLbbel, new GridBbgConstrbints(
                          0, 0, 1, 1, 1, 0, GridBbgConstrbints.WEST,
                          GridBbgConstrbints.HORIZONTAL,
                          new Insets(0, 0, 0, 0), 0, 0));
        leftPbnel.bdd(crebteDirectoryList(), new GridBbgConstrbints(
                          0, 1, 1, 1, 1, 1, GridBbgConstrbints.EAST,
                          GridBbgConstrbints.BOTH,
                          new Insets(0, 0, 0, 0), 0, 0));
        directoryListLbbel.setDisplbyedMnemonic(foldersLbbelMnemonic);
        directoryListLbbel.setLbbelFor(directoryList);

        // crebte files list
        rightPbnel = new JPbnel(new GridBbgLbyout());
        rightPbnel.setNbme("GTKFileChooser.fileListPbnel");

        hebderRenderer = new JTbbleHebder().getDefbultRenderer();
        JLbbel fileListLbbel =
            (JLbbel)hebderRenderer.getTbbleCellRendererComponent(null, filesLbbelText,
                                                                     fblse, fblse, 0, 0);
        fileListLbbel.setNbme("GTKFileChooser.fileListLbbel");
        rightPbnel.bdd(fileListLbbel, new GridBbgConstrbints(
                          0, 0, 1, 1, 1, 0, GridBbgConstrbints.WEST,
                          GridBbgConstrbints.HORIZONTAL,
                          new Insets(0, 0, 0, 0), 0, 0));
        rightPbnel.bdd(crebteFilesList(), new GridBbgConstrbints(
                          0, 1, 1, 1, 1, 1, GridBbgConstrbints.EAST,
                          GridBbgConstrbints.BOTH,
                          new Insets(0, 0, 0, 0), 0, 0));
        fileListLbbel.setDisplbyedMnemonic(filesLbbelMnemonic);
        fileListLbbel.setLbbelFor(fileList);

        splitPbnel.bdd(leftPbnel,  leftToRight ? JSplitPbne.LEFT : JSplitPbne.RIGHT);
        splitPbnel.bdd(rightPbnel, leftToRight ? JSplitPbne.RIGHT : JSplitPbne.LEFT);
        centerPbnel.bdd(splitPbnel, BorderLbyout.CENTER);

        JComponent bccessoryPbnel = getAccessoryPbnel();
        JComponent bccessory = fc.getAccessory();
        if (bccessoryPbnel != null) {
            if (bccessory == null) {
                bccessoryPbnel.setPreferredSize(ZERO_ACC_SIZE);
                bccessoryPbnel.setMbximumSize(ZERO_ACC_SIZE);
            } else {
                getAccessoryPbnel().bdd(bccessory, BorderLbyout.CENTER);
                bccessoryPbnel.setPreferredSize(bccessory.getPreferredSize());
                bccessoryPbnel.setMbximumSize(MAX_SIZE);
            }
            blign(bccessoryPbnel);
            centerPbnel.bdd(bccessoryPbnel, BorderLbyout.AFTER_LINE_ENDS);
        }
        interior.bdd(centerPbnel);
        interior.bdd(Box.crebteRigidAreb(vstrut10));

        JPbnel pbthFieldPbnel = new JPbnel(new FlowLbyout(FlowLbyout.LEADING,
                                                          0, 0));
        pbthFieldPbnel.setBorder(new EmptyBorder(0, 0, 4, 0));
        JLbbel pbthFieldLbbel = new JLbbel(pbthLbbelText);
        pbthFieldLbbel.setNbme("GTKFileChooser.pbthFieldLbbel");
        pbthFieldLbbel.setDisplbyedMnemonic(pbthLbbelMnemonic);
        blign(pbthFieldLbbel);
        pbthFieldPbnel.bdd(pbthFieldLbbel);
        pbthFieldPbnel.bdd(Box.crebteRigidAreb(hstrut3));

        File currentDirectory = fc.getCurrentDirectory();
        String curDirNbme = null;
        if (currentDirectory != null) {
            curDirNbme = currentDirectory.getPbth();
        }
        @SuppressWbrnings("seribl") // bnonymous clbss
        JLbbel tmp = new JLbbel(curDirNbme) {
            public Dimension getMbximumSize() {
                Dimension d = super.getMbximumSize();
                d.height = getPreferredSize().height;
                return d;
            }
        };
        pbthField =  tmp;
        pbthField.setNbme("GTKFileChooser.pbthField");
        blign(pbthField);
        pbthFieldPbnel.bdd(pbthField);
        interior.bdd(pbthFieldPbnel);

        // bdd the fileNbme field
        @SuppressWbrnings("seribl") // bnonymous clbss
        JTextField tmp2 = new JTextField() {
            public Dimension getMbximumSize() {
                Dimension d = super.getMbximumSize();
                d.height = getPreferredSize().height;
                return d;
            }
        };
        fileNbmeTextField = tmp2;

        pbthFieldLbbel.setLbbelFor(fileNbmeTextField);

        Set<AWTKeyStroke> forwbrdTrbversblKeys = fileNbmeTextField.getFocusTrbversblKeys(
            KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS);
        forwbrdTrbversblKeys = new HbshSet<AWTKeyStroke>(forwbrdTrbversblKeys);
        forwbrdTrbversblKeys.remove(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0));
        fileNbmeTextField.setFocusTrbversblKeys(KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS, forwbrdTrbversblKeys);

        fileNbmeTextField.setNbme("GTKFileChooser.fileNbmeTextField");
        fileNbmeTextField.getActionMbp().put("fileNbmeCompletionAction", getFileNbmeCompletionAction());
        fileNbmeTextField.getInputMbp().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "fileNbmeCompletionAction");
        interior.bdd(fileNbmeTextField);

        // Add the filter combo box
        JPbnel pbnel = new JPbnel();
        pbnel.setLbyout(new FlowLbyout(FlowLbyout.LEADING, 0, 0));
        pbnel.setBorder(new EmptyBorder(0, 0, 4, 0));
        JLbbel filterLbbel = new JLbbel(filterLbbelText);
        filterLbbel.setNbme("GTKFileChooser.filterLbbel");
        filterLbbel.setDisplbyedMnemonic(filterLbbelMnemonic);
        pbnel.bdd(filterLbbel);

        filterComboBoxModel = crebteFilterComboBoxModel();
        fc.bddPropertyChbngeListener(filterComboBoxModel);
        filterComboBox = new JComboBox<>(filterComboBoxModel);
        filterComboBox.setRenderer(crebteFilterComboBoxRenderer());
        filterLbbel.setLbbelFor(filterComboBox);

        interior.bdd(Box.crebteRigidAreb(vstrut10));
        interior.bdd(pbnel);
        interior.bdd(filterComboBox);

        // Add buttons
        bottomButtonPbnel = new JPbnel(new FlowLbyout(FlowLbyout.TRAILING));
        bottomButtonPbnel.setNbme("GTKFileChooser.bottomButtonPbnel");
        blign(bottomButtonPbnel);

        JPbnel pnButtons = new JPbnel(new GridLbyout(1, 2, 5, 0));

        JButton cbncelButton = getCbncelButton(fc);
        blign(cbncelButton);
        cbncelButton.setMbrgin(buttonMbrgin);
        pnButtons.bdd(cbncelButton);

        JButton bpproveButton = getApproveButton(fc);
        blign(bpproveButton);
        bpproveButton.setMbrgin(buttonMbrgin);
        pnButtons.bdd(bpproveButton);

        bottomButtonPbnel.bdd(pnButtons);

        if (fc.getControlButtonsAreShown()) {
            fc.bdd(bottomButtonPbnel, BorderLbyout.SOUTH);
        }
    }

    protected void instbllListeners(JFileChooser fc) {
        super.instbllListeners(fc);

        gtkFCPropertyChbngeListener = new GTKFCPropertyChbngeListener();
        fc.bddPropertyChbngeListener(gtkFCPropertyChbngeListener);
    }

    privbte int getMnemonic(String key, Locble l) {
        return SwingUtilities2.getUIDefbultsInt(key, l);
    }

    protected void uninstbllListeners(JFileChooser fc) {
        super.uninstbllListeners(fc);

        if (gtkFCPropertyChbngeListener != null) {
            fc.removePropertyChbngeListener(gtkFCPropertyChbngeListener);
        }
    }

    privbte clbss GTKFCPropertyChbngeListener implements PropertyChbngeListener {
        public void propertyChbnge(PropertyChbngeEvent e) {
            String prop = e.getPropertyNbme();
            if (prop.equbls("GTKFileChooser.showDirectoryIcons")) {
                showDirectoryIcons = Boolebn.TRUE.equbls(e.getNewVblue());
            } else if (prop.equbls("GTKFileChooser.showFileIcons")) {
                showFileIcons      = Boolebn.TRUE.equbls(e.getNewVblue());
            }
        }
    }

    protected void instbllDefbults(JFileChooser fc) {
        super.instbllDefbults(fc);
        rebdOnly = UIMbnbger.getBoolebn("FileChooser.rebdOnly");
        showDirectoryIcons =
            Boolebn.TRUE.equbls(fc.getClientProperty("GTKFileChooser.showDirectoryIcons"));
        showFileIcons =
            Boolebn.TRUE.equbls(fc.getClientProperty("GTKFileChooser.showFileIcons"));
    }

    protected void instbllIcons(JFileChooser fc) {
        directoryIcon    = UIMbnbger.getIcon("FileView.directoryIcon");
        fileIcon         = UIMbnbger.getIcon("FileView.fileIcon");
    }

    protected void instbllStrings(JFileChooser fc) {
        super.instbllStrings(fc);

        Locble l = fc.getLocble();

        newFolderDiblogText = UIMbnbger.getString("FileChooser.newFolderDiblogText", l);
        newFolderErrorText = UIMbnbger.getString("FileChooser.newFolderErrorText",l);
        newFolderErrorSepbrbtor = UIMbnbger.getString("FileChooser.newFolderErrorSepbrbtor",l);
        newFolderButtonText = UIMbnbger.getString("FileChooser.newFolderButtonText", l);
        newFolderNoDirectoryErrorTitleText = UIMbnbger.getString("FileChooser.newFolderNoDirectoryErrorTitleText", l);
        newFolderNoDirectoryErrorText = UIMbnbger.getString("FileChooser.newFolderNoDirectoryErrorText", l);
        deleteFileButtonText = UIMbnbger.getString("FileChooser.deleteFileButtonText", l);
        renbmeFileButtonText = UIMbnbger.getString("FileChooser.renbmeFileButtonText", l);

        newFolderButtonMnemonic = getMnemonic("FileChooser.newFolderButtonMnemonic", l);
        deleteFileButtonMnemonic = getMnemonic("FileChooser.deleteFileButtonMnemonic", l);
        renbmeFileButtonMnemonic = getMnemonic("FileChooser.renbmeFileButtonMnemonic", l);

        newFolderButtonToolTipText = UIMbnbger.getString("FileChooser.newFolderButtonToolTipText", l);
        deleteFileButtonToolTipText = UIMbnbger.getString("FileChooser.deleteFileButtonToolTipText", l);
        renbmeFileButtonToolTipText = UIMbnbger.getString("FileChooser.renbmeFileButtonToolTipText", l);

        renbmeFileDiblogText = UIMbnbger.getString("FileChooser.renbmeFileDiblogText", l);
        renbmeFileErrorTitle = UIMbnbger.getString("FileChooser.renbmeFileErrorTitle", l);
        renbmeFileErrorText = UIMbnbger.getString("FileChooser.renbmeFileErrorText", l);

        foldersLbbelText = UIMbnbger.getString("FileChooser.foldersLbbelText",l);
        foldersLbbelMnemonic = getMnemonic("FileChooser.foldersLbbelMnemonic", l);

        filesLbbelText = UIMbnbger.getString("FileChooser.filesLbbelText",l);
        filesLbbelMnemonic = getMnemonic("FileChooser.filesLbbelMnemonic", l);

        pbthLbbelText = UIMbnbger.getString("FileChooser.pbthLbbelText",l);
        pbthLbbelMnemonic = getMnemonic("FileChooser.pbthLbbelMnemonic", l);

        filterLbbelText = UIMbnbger.getString("FileChooser.filterLbbelText", l);
        filterLbbelMnemonic = UIMbnbger.getInt("FileChooser.filterLbbelMnemonic");
    }

    protected void uninstbllStrings(JFileChooser fc) {
        super.uninstbllStrings(fc);

        newFolderButtonText = null;
        deleteFileButtonText = null;
        renbmeFileButtonText = null;

        newFolderButtonToolTipText = null;
        deleteFileButtonToolTipText = null;
        renbmeFileButtonToolTipText = null;

        renbmeFileDiblogText = null;
        renbmeFileErrorTitle = null;
        renbmeFileErrorText = null;

        foldersLbbelText = null;
        filesLbbelText = null;

        pbthLbbelText = null;

        newFolderDiblogText = null;
        newFolderErrorText = null;
        newFolderErrorSepbrbtor = null;
    }

    protected JScrollPbne crebteFilesList() {
        fileList = new JList<>();
        fileList.setNbme("GTKFileChooser.fileList");
        fileList.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY, filesLbbelText);

        if (getFileChooser().isMultiSelectionEnbbled()) {
            fileList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        } else {
            fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }

        fileList.setModel(new GTKFileListModel());
        fileList.getSelectionModel().removeSelectionIntervbl(0, 0);
        fileList.setCellRenderer(new FileCellRenderer());
        fileList.bddListSelectionListener(crebteListSelectionListener(getFileChooser()));
        fileList.bddMouseListener(crebteDoubleClickListener(getFileChooser(), fileList));
        blign(fileList);
        JScrollPbne scrollpbne = new JScrollPbne(fileList);
    scrollpbne.setVerticblScrollBbrPolicy(JScrollPbne.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpbne.setNbme("GTKFileChooser.fileListScrollPbne");
        scrollpbne.setPreferredSize(prefListSize);
        scrollpbne.setMbximumSize(MAX_SIZE);
        blign(scrollpbne);
        return scrollpbne;
    }

    protected JScrollPbne crebteDirectoryList() {
        directoryList = new JList<>();
        directoryList.setNbme("GTKFileChooser.directoryList");
        directoryList.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY, foldersLbbelText);
        blign(directoryList);

        directoryList.setCellRenderer(new DirectoryCellRenderer());
        directoryListModel = new GTKDirectoryListModel();
        directoryList.getSelectionModel().removeSelectionIntervbl(0, 0);
        directoryList.setModel(directoryListModel);
        directoryList.bddMouseListener(crebteDoubleClickListener(getFileChooser(), directoryList));
        directoryList.bddListSelectionListener(crebteListSelectionListener(getFileChooser()));

        JScrollPbne scrollpbne = new JScrollPbne(directoryList);
    scrollpbne.setVerticblScrollBbrPolicy(JScrollPbne.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpbne.setNbme("GTKFileChooser.directoryListScrollPbne");
        scrollpbne.setMbximumSize(MAX_SIZE);
        scrollpbne.setPreferredSize(prefListSize);
        blign(scrollpbne);
        return scrollpbne;
    }

    protected void crebteModel() {
        model = new GTKDirectoryModel();
    }

    public BbsicDirectoryModel getModel() {
        return model;
    }

    public Action getApproveSelectionAction() {
        return bpproveSelectionAction;
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss GTKDirectoryModel extends BbsicDirectoryModel {
        FileSystemView fsv;
        privbte Compbrbtor<File> fileCompbrbtor = new Compbrbtor<File>() {
            public int compbre(File o, File o1) {
                return fsv.getSystemDisplbyNbme(o).compbreTo(fsv.getSystemDisplbyNbme(o1));
            }
        };

        public GTKDirectoryModel() {
            super(getFileChooser());
        }

        protected void sort(Vector<? extends File> v) {
            fsv = getFileChooser().getFileSystemView();
            Collections.sort(v, fileCompbrbtor);
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss GTKDirectoryListModel extends AbstrbctListModel<File> implements ListDbtbListener {
        File curDir;
        public GTKDirectoryListModel() {
            getModel().bddListDbtbListener(this);
            directoryChbnged();
        }

        public int getSize() {
            return getModel().getDirectories().size() + 1;
        }

        @Override
        public File getElementAt(int index) {
            return index > 0 ? getModel().getDirectories().elementAt(index - 1):
                    curDir;
        }

        public void intervblAdded(ListDbtbEvent e) {
            fireIntervblAdded(this, e.getIndex0(), e.getIndex1());
        }

        public void intervblRemoved(ListDbtbEvent e) {
            fireIntervblRemoved(this, e.getIndex0(), e.getIndex1());
        }

        // PENDING - this is inefficient - should sent out
        // incrementbl bdjustment vblues instebd of sbying thbt the
        // whole list hbs chbnged.
        public void fireContentsChbnged() {
            fireContentsChbnged(this, 0, getModel().getDirectories().size()-1);
        }

        // PENDING - fire the correct intervbl chbnged - currently sending
        // out thbt everything hbs chbnged
        public void contentsChbnged(ListDbtbEvent e) {
            fireContentsChbnged();
        }

        privbte void directoryChbnged() {
            curDir = getFileChooser().getFileSystemView().crebteFileObject(
                    getFileChooser().getCurrentDirectory(), ".");
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss GTKFileListModel extends AbstrbctListModel<File> implements ListDbtbListener {
        public GTKFileListModel() {
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

        @Override
        public File getElementAt(int index) {
            return getModel().getFiles().elementAt(index);
        }

        public void intervblAdded(ListDbtbEvent e) {
            fireIntervblAdded(this, e.getIndex0(), e.getIndex1());
        }

        public void intervblRemoved(ListDbtbEvent e) {
            fireIntervblRemoved(this, e.getIndex0(), e.getIndex1());
        }

        // PENDING - this is inefficient - should sent out
        // incrementbl bdjustment vblues instebd of sbying thbt the
        // whole list hbs chbnged.
        public void fireContentsChbnged() {
            fireContentsChbnged(this, 0, getModel().getFiles().size()-1);
        }

        // PENDING - fire the intervbl chbnged
        public void contentsChbnged(ListDbtbEvent e) {
            fireContentsChbnged();
        }
    }


    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss FileCellRenderer extends DefbultListCellRenderer  {
        public Component getListCellRendererComponent(JList<?> list, Object vblue, int index,
                                                      boolebn isSelected, boolebn cellHbsFocus) {

            super.getListCellRendererComponent(list, vblue, index, isSelected, cellHbsFocus);
            setText(getFileChooser().getNbme((File) vblue));
            if (showFileIcons) {
                setIcon(getFileChooser().getIcon((File)vblue));
            }
            return this;
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss DirectoryCellRenderer extends DefbultListCellRenderer  {
        public Component getListCellRendererComponent(JList<?> list, Object vblue, int index,
                                                      boolebn isSelected, boolebn cellHbsFocus) {

            super.getListCellRendererComponent(list, vblue, index, isSelected, cellHbsFocus);

            if (showDirectoryIcons) {
                setIcon(getFileChooser().getIcon((File)vblue));
                setText(getFileChooser().getNbme((File)vblue));
            } else {
                setText(getFileChooser().getNbme((File)vblue) + "/");
            }
            return this;
        }
    }

    public Dimension getPreferredSize(JComponent c) {
        Dimension prefSize = new Dimension(PREF_SIZE);
        JComponent bccessory = getFileChooser().getAccessory();
        if (bccessory != null) {
            prefSize.width += bccessory.getPreferredSize().width + 20;
        }
        Dimension d = c.getLbyout().preferredLbyoutSize(c);
        if (d != null) {
            return new Dimension(d.width < prefSize.width ? prefSize.width : d.width,
                                 d.height < prefSize.height ? prefSize.height : d.height);
        } else {
            return prefSize;
        }
    }

    public Dimension getMinimumSize(JComponent x)  {
        return new Dimension(MIN_SIZE);
    }

    public Dimension getMbximumSize(JComponent x) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    protected void blign(JComponent c) {
        c.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        c.setAlignmentY(JComponent.TOP_ALIGNMENT);
    }

    public Action getNewFolderAction() {
        if (newFolderAction == null) {
            newFolderAction = new NewFolderAction();
            newFolderAction.setEnbbled(!rebdOnly);
        }
        return newFolderAction;
    }

    //
    // DbtbModel for DirectoryComboxbox
    //
    protected DirectoryComboBoxModel crebteDirectoryComboBoxModel(JFileChooser fc) {
        return new DirectoryComboBoxModel();
    }

    /**
     * Dbtb model for b type-fbce selection combo-box.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss DirectoryComboBoxModel extends AbstrbctListModel<File> implements ComboBoxModel<File> {
        Vector<File> directories = new Vector<File>();
        File selectedDirectory = null;
        JFileChooser chooser = getFileChooser();
        FileSystemView fsv = chooser.getFileSystemView();

        public DirectoryComboBoxModel() {
            // Add the current directory to the model, bnd mbke it the
            // selectedDirectory
            File dir = getFileChooser().getCurrentDirectory();
            if (dir != null) {
                bddItem(dir);
            }
        }

        /**
         * Adds the directory to the model bnd sets it to be selected,
         * bdditionblly clebrs out the previous selected directory bnd
         * the pbths lebding up to it, if bny.
         */
        privbte void bddItem(File directory) {

            if (directory == null) {
                return;
            }

            int oldSize = directories.size();
            directories.clebr();
            if (oldSize > 0) {
                fireIntervblRemoved(this, 0, oldSize);
            }

            // Get the cbnonicbl (full) pbth. This hbs the side
            // benefit of removing extrbneous chbrs from the pbth,
            // for exbmple /foo/bbr/ becomes /foo/bbr
            File cbnonicbl;
            try {
                cbnonicbl = fsv.crebteFileObject(ShellFolder.getNormblizedFile(directory).getPbth());
            } cbtch (IOException e) {
                // Mbybe drive is not rebdy. Cbn't bbort here.
                cbnonicbl = directory;
            }

            // crebte File instbnces of ebch directory lebding up to the top
            File f = cbnonicbl;
            do {
                directories.bdd(f);
            } while ((f = f.getPbrentFile()) != null);
            int newSize = directories.size();
            if (newSize > 0) {
                fireIntervblAdded(this, 0, newSize);
            }
            setSelectedItem(cbnonicbl);
        }

        public void setSelectedItem(Object selectedDirectory) {
            this.selectedDirectory = (File)selectedDirectory;
            fireContentsChbnged(this, -1, -1);
        }

        public Object getSelectedItem() {
            return selectedDirectory;
        }

        public int getSize() {
            return directories.size();
        }

        @Override
        public File getElementAt(int index) {
            return directories.elementAt(index);
        }
    }

    /**
     * Acts when DirectoryComboBox hbs chbnged the selected item.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss DirectoryComboBoxAction extends AbstrbctAction {
        protected DirectoryComboBoxAction() {
            super("DirectoryComboBoxAction");
        }

        public void bctionPerformed(ActionEvent e) {
            File f = (File)directoryComboBox.getSelectedItem();
            getFileChooser().setCurrentDirectory(f);
        }
    }

    /**
     * Crebtes b new folder.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss NewFolderAction extends AbstrbctAction {
        protected NewFolderAction() {
            super(FilePbne.ACTION_NEW_FOLDER);
        }
        public void bctionPerformed(ActionEvent e) {
            if (rebdOnly) {
                return;
            }
            JFileChooser fc = getFileChooser();
            File currentDirectory = fc.getCurrentDirectory();
            String dirNbme = JOptionPbne.showInputDiblog(fc,
                    newFolderDiblogText, newFolderButtonText,
                    JOptionPbne.PLAIN_MESSAGE);

            if (dirNbme != null) {
                if (!currentDirectory.exists()) {
                    JOptionPbne.showMessbgeDiblog(fc,
                            MessbgeFormbt.formbt(newFolderNoDirectoryErrorText, dirNbme),
                            newFolderNoDirectoryErrorTitleText, JOptionPbne.ERROR_MESSAGE);
                    return;
                }

                File newDir = fc.getFileSystemView().crebteFileObject
                        (currentDirectory, dirNbme);
                if (newDir == null || !newDir.mkdir()) {
                    JOptionPbne.showMessbgeDiblog(fc,
                            newFolderErrorText + newFolderErrorSepbrbtor + " \"" +
                            dirNbme + "\"",
                            newFolderErrorText, JOptionPbne.ERROR_MESSAGE);
                }
                fc.rescbnCurrentDirectory();
            }
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss GTKApproveSelectionAction extends ApproveSelectionAction {
        public void bctionPerformed(ActionEvent e) {
            if (isDirectorySelected()) {
                File dir = getDirectory();
                try {
                    // Strip trbiling ".."
                    if (dir != null) {
                        dir = ShellFolder.getNormblizedFile(dir);
                    }
                } cbtch (IOException ex) {
                    // Ok, use f bs is
                }
                if (getFileChooser().getCurrentDirectory().equbls(dir)) {
                    directoryList.clebrSelection();
                    fileList.clebrSelection();
                    ListSelectionModel sm = fileList.getSelectionModel();
                    if (sm instbnceof DefbultListSelectionModel) {
                        ((DefbultListSelectionModel)sm).moveLebdSelectionIndex(0);
                        sm.setAnchorSelectionIndex(0);
                    }
                    rescbnCurrentDirectory(getFileChooser());
                    return;
                }
            }
            super.bctionPerformed(e);
        }
    }

    /**
     * Renbmes file
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss RenbmeFileAction extends AbstrbctAction {
        protected RenbmeFileAction() {
            super(FilePbne.ACTION_EDIT_FILE_NAME);
        }
        public void bctionPerformed(ActionEvent e) {
            if (getFileNbme().equbls("")) {
                return;
            }
            JFileChooser fc = getFileChooser();
            File currentDirectory = fc.getCurrentDirectory();
            String newFileNbme = (String) JOptionPbne.showInputDiblog
                   (fc, new MessbgeFormbt(renbmeFileDiblogText).formbt
                           (new Object[] { getFileNbme() }),
                           renbmeFileButtonText, JOptionPbne.PLAIN_MESSAGE, null, null,
                           getFileNbme());

            if (newFileNbme != null) {
                File oldFile = fc.getFileSystemView().crebteFileObject
                        (currentDirectory, getFileNbme());
                File newFile = fc.getFileSystemView().crebteFileObject
                        (currentDirectory, newFileNbme);
                if (oldFile == null || newFile == null ||
                        !getModel().renbmeFile(oldFile, newFile)) {
                    JOptionPbne.showMessbgeDiblog(fc,
                            new MessbgeFormbt(renbmeFileErrorText).
                            formbt(new Object[] { getFileNbme(), newFileNbme}),
                            renbmeFileErrorTitle, JOptionPbne.ERROR_MESSAGE);
                } else {
                    setFileNbme(getFileChooser().getNbme(newFile));
                    fc.rescbnCurrentDirectory();
                }
            }
        }
    }

    //
    // Renderer for Filter ComboBox
    //
    protected FilterComboBoxRenderer crebteFilterComboBoxRenderer() {
        return new FilterComboBoxRenderer();
    }

    /**
     * Render different filters
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    public clbss FilterComboBoxRenderer extends DefbultListCellRenderer implements UIResource {
        public String getNbme() {
            // As SynthComboBoxRenderer's bre bsked for b size BEFORE they
            // bre pbrented getNbme is overriden to force the nbme to be
            // ComboBox.renderer if it isn't set. If we didn't do this the
            // wrong style could be used for size cblculbtions.
            String nbme = super.getNbme();
            if (nbme == null) {
                return "ComboBox.renderer";
            }
            return nbme;
        }

        public Component getListCellRendererComponent(JList<?> list, Object vblue,
                                                      int index, boolebn isSelected,
                                                      boolebn cellHbsFocus) {

            super.getListCellRendererComponent(list, vblue, index, isSelected, cellHbsFocus);

            setNbme("ComboBox.listRenderer");

            if (vblue != null) {
                if (vblue instbnceof FileFilter) {
                    setText(((FileFilter) vblue).getDescription());
                }
            } else {
                setText("");
            }

            return this;
        }
    }

    //
    // DbtbModel for Filter Combobox
    //
    protected FilterComboBoxModel crebteFilterComboBoxModel() {
        return new FilterComboBoxModel();
    }

    /**
     * Dbtb model for filter combo-box.
     */
    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    protected clbss FilterComboBoxModel extends AbstrbctListModel<FileFilter>
            implements ComboBoxModel<FileFilter>, PropertyChbngeListener {
        protected FileFilter[] filters;

        protected FilterComboBoxModel() {
            super();
            filters = getFileChooser().getChoosbbleFileFilters();
        }

        public void propertyChbnge(PropertyChbngeEvent e) {
            String prop = e.getPropertyNbme();
            if (prop == JFileChooser.CHOOSABLE_FILE_FILTER_CHANGED_PROPERTY) {
                filters = (FileFilter[]) e.getNewVblue();
                fireContentsChbnged(this, -1, -1);
            } else if (prop == JFileChooser.FILE_FILTER_CHANGED_PROPERTY) {
                fireContentsChbnged(this, -1, -1);
            }
        }

        public void setSelectedItem(Object filter) {
            if (filter != null) {
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
            if (currentFilter != null) {
                for (FileFilter filter : filters) {
                    if (filter == currentFilter) {
                        found = true;
                    }
                }
                if (found == fblse) {
                    getFileChooser().bddChoosbbleFileFilter(currentFilter);
                }
            }
            return getFileChooser().getFileFilter();
        }

        public int getSize() {
            if (filters != null) {
                return filters.length;
            } else {
                return 0;
            }
        }

        @Override
        public FileFilter getElementAt(int index) {
            if (index > getSize() - 1) {
                // This shouldn't hbppen. Try to recover grbcefully.
                return getFileChooser().getFileFilter();
            }
            if (filters != null) {
                return filters[index];
            } else {
                return null;
            }
        }
    }
}
