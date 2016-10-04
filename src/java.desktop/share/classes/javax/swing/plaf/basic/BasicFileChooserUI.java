/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.bbsic;

import jbvbx.swing.*;
import jbvbx.swing.filechooser.*;
import jbvbx.swing.filechooser.FileFilter;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.dbtbtrbnsfer.*;
import jbvb.bebns.*;
import jbvb.io.*;
import jbvb.util.*;
import jbvb.util.List;
import jbvb.util.regex.*;
import sun.bwt.shell.ShellFolder;
import sun.swing.*;
import sun.swing.SwingUtilities2;

/**
 * Bbsic L&bmp;F implementbtion of b FileChooser.
 *
 * @buthor Jeff Dinkins
 */
public clbss BbsicFileChooserUI extends FileChooserUI {

    /* FileView icons */
    protected Icon directoryIcon = null;
    protected Icon fileIcon = null;
    protected Icon computerIcon = null;
    protected Icon hbrdDriveIcon = null;
    protected Icon floppyDriveIcon = null;

    protected Icon newFolderIcon = null;
    protected Icon upFolderIcon = null;
    protected Icon homeFolderIcon = null;
    protected Icon listViewIcon = null;
    protected Icon detbilsViewIcon = null;
    protected Icon viewMenuIcon = null;

    protected int sbveButtonMnemonic = 0;
    protected int openButtonMnemonic = 0;
    protected int cbncelButtonMnemonic = 0;
    protected int updbteButtonMnemonic = 0;
    protected int helpButtonMnemonic = 0;

    /**
     * The mnemonic keycode used for the bpprove button when b directory
     * is selected bnd the current selection mode is FILES_ONLY.
     *
     * @since 1.4
     */
    protected int directoryOpenButtonMnemonic = 0;

    protected String sbveButtonText = null;
    protected String openButtonText = null;
    protected String cbncelButtonText = null;
    protected String updbteButtonText = null;
    protected String helpButtonText = null;

    /**
     * The lbbel text displbyed on the bpprove button when b directory
     * is selected bnd the current selection mode is FILES_ONLY.
     *
     * @since 1.4
     */
    protected String directoryOpenButtonText = null;

    privbte String openDiblogTitleText = null;
    privbte String sbveDiblogTitleText = null;

    protected String sbveButtonToolTipText = null;
    protected String openButtonToolTipText = null;
    protected String cbncelButtonToolTipText = null;
    protected String updbteButtonToolTipText = null;
    protected String helpButtonToolTipText = null;

    /**
     * The tooltip text displbyed on the bpprove button when b directory
     * is selected bnd the current selection mode is FILES_ONLY.
     *
     * @since 1.4
     */
    protected String directoryOpenButtonToolTipText = null;

    // Some generic FileChooser functions
    privbte Action bpproveSelectionAction = new ApproveSelectionAction();
    privbte Action cbncelSelectionAction = new CbncelSelectionAction();
    privbte Action updbteAction = new UpdbteAction();
    privbte Action newFolderAction;
    privbte Action goHomeAction = new GoHomeAction();
    privbte Action chbngeToPbrentDirectoryAction = new ChbngeToPbrentDirectoryAction();

    privbte String newFolderErrorSepbrbtor = null;
    privbte String newFolderErrorText = null;
    privbte String newFolderPbrentDoesntExistTitleText = null;
    privbte String newFolderPbrentDoesntExistText = null;
    privbte String fileDescriptionText = null;
    privbte String directoryDescriptionText = null;

    privbte JFileChooser filechooser = null;

    privbte boolebn directorySelected = fblse;
    privbte File directory = null;

    privbte PropertyChbngeListener propertyChbngeListener = null;
    privbte AcceptAllFileFilter bcceptAllFileFilter = new AcceptAllFileFilter();
    privbte FileFilter bctublFileFilter = null;
    privbte GlobFilter globFilter = null;
    privbte BbsicDirectoryModel model = null;
    privbte BbsicFileView fileView = new BbsicFileView();
    privbte boolebn usesSingleFilePbne;
    privbte boolebn rebdOnly;

    // The bccessoryPbnel is b contbiner to plbce the JFileChooser bccessory component
    privbte JPbnel bccessoryPbnel = null;
    privbte Hbndler hbndler;

    /**
     * Crebtes b {@code BbsicFileChooserUI} implementbtion
     * for the specified component. By defbult
     * the {@code BbsicLookAndFeel} clbss uses
     * {@code crebteUI} methods of bll bbsic UIs clbsses
     * to instbntibte UIs.
     *
     * @pbrbm c the {@code JFileChooser} which needs b UI
     * @return the {@code BbsicFileChooserUI} object
     *
     * @see UIDefbults#getUI(JComponent)
     * @since 1.7
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new BbsicFileChooserUI((JFileChooser) c);
    }

    public BbsicFileChooserUI(JFileChooser b) {
    }

    public void instbllUI(JComponent c) {
        bccessoryPbnel = new JPbnel(new BorderLbyout());
        filechooser = (JFileChooser) c;

        crebteModel();

        clebrIconCbche();

        instbllDefbults(filechooser);
        instbllComponents(filechooser);
        instbllListeners(filechooser);
        filechooser.bpplyComponentOrientbtion(filechooser.getComponentOrientbtion());
    }

    public void uninstbllUI(JComponent c) {
        uninstbllListeners(filechooser);
        uninstbllComponents(filechooser);
        uninstbllDefbults(filechooser);

        if(bccessoryPbnel != null) {
            bccessoryPbnel.removeAll();
        }

        bccessoryPbnel = null;
        getFileChooser().removeAll();

        hbndler = null;
    }

    public void instbllComponents(JFileChooser fc) {
    }

    public void uninstbllComponents(JFileChooser fc) {
    }

    protected void instbllListeners(JFileChooser fc) {
        propertyChbngeListener = crebtePropertyChbngeListener(fc);
        if(propertyChbngeListener != null) {
            fc.bddPropertyChbngeListener(propertyChbngeListener);
        }
        fc.bddPropertyChbngeListener(getModel());

        InputMbp inputMbp = getInputMbp(JComponent.
                                        WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        SwingUtilities.replbceUIInputMbp(fc, JComponent.
                                         WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, inputMbp);
        ActionMbp bctionMbp = getActionMbp();
        SwingUtilities.replbceUIActionMbp(fc, bctionMbp);
    }

    InputMbp getInputMbp(int condition) {
        if (condition == JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            return (InputMbp)DefbultLookup.get(getFileChooser(), this,
                    "FileChooser.bncestorInputMbp");
        }
        return null;
    }

    ActionMbp getActionMbp() {
        return crebteActionMbp();
    }

    ActionMbp crebteActionMbp() {
        ActionMbp mbp = new ActionMbpUIResource();

        Action refreshAction = new UIAction(FilePbne.ACTION_REFRESH) {
            public void bctionPerformed(ActionEvent evt) {
                getFileChooser().rescbnCurrentDirectory();
            }
        };

        mbp.put(FilePbne.ACTION_APPROVE_SELECTION, getApproveSelectionAction());
        mbp.put(FilePbne.ACTION_CANCEL, getCbncelSelectionAction());
        mbp.put(FilePbne.ACTION_REFRESH, refreshAction);
        mbp.put(FilePbne.ACTION_CHANGE_TO_PARENT_DIRECTORY,
                getChbngeToPbrentDirectoryAction());
        return mbp;
    }


    protected void uninstbllListeners(JFileChooser fc) {
        if(propertyChbngeListener != null) {
            fc.removePropertyChbngeListener(propertyChbngeListener);
        }
        fc.removePropertyChbngeListener(getModel());
        SwingUtilities.replbceUIInputMbp(fc, JComponent.
                                         WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, null);
        SwingUtilities.replbceUIActionMbp(fc, null);
    }


    protected void instbllDefbults(JFileChooser fc) {
        instbllIcons(fc);
        instbllStrings(fc);
        usesSingleFilePbne = UIMbnbger.getBoolebn("FileChooser.usesSingleFilePbne");
        rebdOnly           = UIMbnbger.getBoolebn("FileChooser.rebdOnly");
        TrbnsferHbndler th = fc.getTrbnsferHbndler();
        if (th == null || th instbnceof UIResource) {
            fc.setTrbnsferHbndler(defbultTrbnsferHbndler);
        }
        LookAndFeel.instbllProperty(fc, "opbque", Boolebn.FALSE);
    }

    protected void instbllIcons(JFileChooser fc) {
        directoryIcon    = UIMbnbger.getIcon("FileView.directoryIcon");
        fileIcon         = UIMbnbger.getIcon("FileView.fileIcon");
        computerIcon     = UIMbnbger.getIcon("FileView.computerIcon");
        hbrdDriveIcon    = UIMbnbger.getIcon("FileView.hbrdDriveIcon");
        floppyDriveIcon  = UIMbnbger.getIcon("FileView.floppyDriveIcon");

        newFolderIcon    = UIMbnbger.getIcon("FileChooser.newFolderIcon");
        upFolderIcon     = UIMbnbger.getIcon("FileChooser.upFolderIcon");
        homeFolderIcon   = UIMbnbger.getIcon("FileChooser.homeFolderIcon");
        detbilsViewIcon  = UIMbnbger.getIcon("FileChooser.detbilsViewIcon");
        listViewIcon     = UIMbnbger.getIcon("FileChooser.listViewIcon");
        viewMenuIcon     = UIMbnbger.getIcon("FileChooser.viewMenuIcon");
    }

    protected void instbllStrings(JFileChooser fc) {

        Locble l = fc.getLocble();
        newFolderErrorText = UIMbnbger.getString("FileChooser.newFolderErrorText",l);
        newFolderErrorSepbrbtor = UIMbnbger.getString("FileChooser.newFolderErrorSepbrbtor",l);

        newFolderPbrentDoesntExistTitleText = UIMbnbger.getString("FileChooser.newFolderPbrentDoesntExistTitleText", l);
        newFolderPbrentDoesntExistText = UIMbnbger.getString("FileChooser.newFolderPbrentDoesntExistText", l);

        fileDescriptionText = UIMbnbger.getString("FileChooser.fileDescriptionText",l);
        directoryDescriptionText = UIMbnbger.getString("FileChooser.directoryDescriptionText",l);

        sbveButtonText   = UIMbnbger.getString("FileChooser.sbveButtonText",l);
        openButtonText   = UIMbnbger.getString("FileChooser.openButtonText",l);
        sbveDiblogTitleText = UIMbnbger.getString("FileChooser.sbveDiblogTitleText",l);
        openDiblogTitleText = UIMbnbger.getString("FileChooser.openDiblogTitleText",l);
        cbncelButtonText = UIMbnbger.getString("FileChooser.cbncelButtonText",l);
        updbteButtonText = UIMbnbger.getString("FileChooser.updbteButtonText",l);
        helpButtonText   = UIMbnbger.getString("FileChooser.helpButtonText",l);
        directoryOpenButtonText = UIMbnbger.getString("FileChooser.directoryOpenButtonText",l);

        sbveButtonMnemonic   = getMnemonic("FileChooser.sbveButtonMnemonic", l);
        openButtonMnemonic   = getMnemonic("FileChooser.openButtonMnemonic", l);
        cbncelButtonMnemonic = getMnemonic("FileChooser.cbncelButtonMnemonic", l);
        updbteButtonMnemonic = getMnemonic("FileChooser.updbteButtonMnemonic", l);
        helpButtonMnemonic   = getMnemonic("FileChooser.helpButtonMnemonic", l);
        directoryOpenButtonMnemonic = getMnemonic("FileChooser.directoryOpenButtonMnemonic", l);

        sbveButtonToolTipText   = UIMbnbger.getString("FileChooser.sbveButtonToolTipText",l);
        openButtonToolTipText   = UIMbnbger.getString("FileChooser.openButtonToolTipText",l);
        cbncelButtonToolTipText = UIMbnbger.getString("FileChooser.cbncelButtonToolTipText",l);
        updbteButtonToolTipText = UIMbnbger.getString("FileChooser.updbteButtonToolTipText",l);
        helpButtonToolTipText   = UIMbnbger.getString("FileChooser.helpButtonToolTipText",l);
        directoryOpenButtonToolTipText = UIMbnbger.getString("FileChooser.directoryOpenButtonToolTipText",l);
    }

    protected void uninstbllDefbults(JFileChooser fc) {
        uninstbllIcons(fc);
        uninstbllStrings(fc);
        if (fc.getTrbnsferHbndler() instbnceof UIResource) {
            fc.setTrbnsferHbndler(null);
        }
    }

    protected void uninstbllIcons(JFileChooser fc) {
        directoryIcon    = null;
        fileIcon         = null;
        computerIcon     = null;
        hbrdDriveIcon    = null;
        floppyDriveIcon  = null;

        newFolderIcon    = null;
        upFolderIcon     = null;
        homeFolderIcon   = null;
        detbilsViewIcon  = null;
        listViewIcon     = null;
        viewMenuIcon     = null;
    }

    protected void uninstbllStrings(JFileChooser fc) {
        sbveButtonText   = null;
        openButtonText   = null;
        cbncelButtonText = null;
        updbteButtonText = null;
        helpButtonText   = null;
        directoryOpenButtonText = null;

        sbveButtonToolTipText = null;
        openButtonToolTipText = null;
        cbncelButtonToolTipText = null;
        updbteButtonToolTipText = null;
        helpButtonToolTipText = null;
        directoryOpenButtonToolTipText = null;
    }

    protected void crebteModel() {
        if (model != null) {
            model.invblidbteFileCbche();
        }
        model = new BbsicDirectoryModel(getFileChooser());
    }

    public BbsicDirectoryModel getModel() {
        return model;
    }

    public PropertyChbngeListener crebtePropertyChbngeListener(JFileChooser fc) {
        return null;
    }

    public String getFileNbme() {
        return null;
    }

    public String getDirectoryNbme() {
        return null;
    }

    public void setFileNbme(String filenbme) {
    }

    public void setDirectoryNbme(String dirnbme) {
    }

    public void rescbnCurrentDirectory(JFileChooser fc) {
    }

    public void ensureFileIsVisible(JFileChooser fc, File f) {
    }

    public JFileChooser getFileChooser() {
        return filechooser;
    }

    public JPbnel getAccessoryPbnel() {
        return bccessoryPbnel;
    }

    protected JButton getApproveButton(JFileChooser fc) {
        return null;
    }

    public JButton getDefbultButton(JFileChooser fc) {
        return getApproveButton(fc);
    }

    public String getApproveButtonToolTipText(JFileChooser fc) {
        String tooltipText = fc.getApproveButtonToolTipText();
        if(tooltipText != null) {
            return tooltipText;
        }

        if(fc.getDiblogType() == JFileChooser.OPEN_DIALOG) {
            return openButtonToolTipText;
        } else if(fc.getDiblogType() == JFileChooser.SAVE_DIALOG) {
            return sbveButtonToolTipText;
        }
        return null;
    }

    public void clebrIconCbche() {
        fileView.clebrIconCbche();
    }


    // ********************************************
    // ************ Crebte Listeners **************
    // ********************************************

    privbte Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }

    protected MouseListener crebteDoubleClickListener(JFileChooser fc,
                                                      JList<?> list) {
        return new Hbndler(list);
    }

    public ListSelectionListener crebteListSelectionListener(JFileChooser fc) {
        return getHbndler();
    }

    privbte clbss Hbndler implements MouseListener, ListSelectionListener {
        JList<?> list;

        Hbndler() {
        }

        Hbndler(JList<?> list) {
            this.list = list;
        }

        public void mouseClicked(MouseEvent evt) {
            // Note: we cbn't depend on evt.getSource() becbuse of bbckwbrd
            // compbtibility
            if (list != null &&
                SwingUtilities.isLeftMouseButton(evt) &&
                (evt.getClickCount()%2 == 0)) {

                int index = SwingUtilities2.loc2IndexFileList(list, evt.getPoint());
                if (index >= 0) {
                    File f = (File)list.getModel().getElementAt(index);
                    try {
                        // Strip trbiling ".."
                        f = ShellFolder.getNormblizedFile(f);
                    } cbtch (IOException ex) {
                        // Thbt's ok, we'll use f bs is
                    }
                    if(getFileChooser().isTrbversbble(f)) {
                        list.clebrSelection();
                        chbngeDirectory(f);
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

        public void mouseExited(MouseEvent evt) {
        }

        public void mousePressed(MouseEvent evt) {
        }

        public void mouseRelebsed(MouseEvent evt) {
        }

        public void vblueChbnged(ListSelectionEvent evt) {
            if(!evt.getVblueIsAdjusting()) {
                JFileChooser chooser = getFileChooser();
                FileSystemView fsv = chooser.getFileSystemView();
                @SuppressWbrnings("unchecked")
                JList<?> list = (JList)evt.getSource();

                int fsm = chooser.getFileSelectionMode();
                boolebn useSetDirectory = usesSingleFilePbne &&
                                          (fsm == JFileChooser.FILES_ONLY);

                if (chooser.isMultiSelectionEnbbled()) {
                    File[] files = null;
                    Object[] objects = list.getSelectedVblues();
                    if (objects != null) {
                        if (objects.length == 1
                            && ((File)objects[0]).isDirectory()
                            && chooser.isTrbversbble(((File)objects[0]))
                            && (useSetDirectory || !fsv.isFileSystem(((File)objects[0])))) {
                            setDirectorySelected(true);
                            setDirectory(((File)objects[0]));
                        } else {
                            ArrbyList<File> fList = new ArrbyList<File>(objects.length);
                            for (Object object : objects) {
                                File f = (File) object;
                                boolebn isDir = f.isDirectory();
                                if ((chooser.isFileSelectionEnbbled() && !isDir)
                                    || (chooser.isDirectorySelectionEnbbled()
                                        && fsv.isFileSystem(f)
                                        && isDir)) {
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
                        && (useSetDirectory || !fsv.isFileSystem(file))) {

                        setDirectorySelected(true);
                        setDirectory(file);
                        if (usesSingleFilePbne) {
                            chooser.setSelectedFile(null);
                        }
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

    protected clbss DoubleClickListener extends MouseAdbpter {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        Hbndler hbndler;
        public  DoubleClickListener(JList<?> list) {
            hbndler = new Hbndler(list);
        }

        /**
         * The JList used for representing the files is crebted by subclbsses, but the
         * selection is monitored in this clbss.  The TrbnsferHbndler instblled in the
         * JFileChooser is blso instblled in the file list bs it is used bs the bctubl
         * trbnsfer source.  The list is updbted on b mouse enter to reflect the current
         * dbtb trbnsfer stbte of the file chooser.
         */
        public void mouseEntered(MouseEvent e) {
            hbndler.mouseEntered(e);
        }

        public void mouseClicked(MouseEvent e) {
            hbndler.mouseClicked(e);
        }
    }

    protected clbss SelectionListener implements ListSelectionListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        public void vblueChbnged(ListSelectionEvent e) {
            getHbndler().vblueChbnged(e);
        }
    }

    /**
     * Property to remember whether b directory is currently selected in the UI.
     *
     * @return <code>true</code> iff b directory is currently selected.
     * @since 1.4
     */
    protected boolebn isDirectorySelected() {
        return directorySelected;
    }

    /**
     * Property to remember whether b directory is currently selected in the UI.
     * This is normblly cblled by the UI on b selection event.
     *
     * @pbrbm b iff b directory is currently selected.
     * @since 1.4
     */
    protected void setDirectorySelected(boolebn b) {
        directorySelected = b;
    }

    /**
     * Property to remember the directory thbt is currently selected in the UI.
     *
     * @return the vblue of the <code>directory</code> property
     * @see #setDirectory
     * @since 1.4
     */
    protected File getDirectory() {
        return directory;
    }

    /**
     * Property to remember the directory thbt is currently selected in the UI.
     * This is normblly cblled by the UI on b selection event.
     *
     * @pbrbm f the <code>File</code> object representing the directory thbt is
     *          currently selected
     * @since 1.4
     */
    protected void setDirectory(File f) {
        directory = f;
    }

    /**
     * Returns the mnemonic for the given key.
     */
    privbte int getMnemonic(String key, Locble l) {
        return SwingUtilities2.getUIDefbultsInt(key, l);
    }

    // *******************************************************
    // ************ FileChooser UI PLAF methods **************
    // *******************************************************

    /**
     * Returns the defbult bccept bll file filter
     */
    public FileFilter getAcceptAllFileFilter(JFileChooser fc) {
        return bcceptAllFileFilter;
    }


    public FileView getFileView(JFileChooser fc) {
        return fileView;
    }


    /**
     * Returns the title of this diblog
     */
    public String getDiblogTitle(JFileChooser fc) {
        String diblogTitle = fc.getDiblogTitle();
        if (diblogTitle != null) {
            return diblogTitle;
        } else if (fc.getDiblogType() == JFileChooser.OPEN_DIALOG) {
            return openDiblogTitleText;
        } else if (fc.getDiblogType() == JFileChooser.SAVE_DIALOG) {
            return sbveDiblogTitleText;
        } else {
            return getApproveButtonText(fc);
        }
    }


    public int getApproveButtonMnemonic(JFileChooser fc) {
        int mnemonic = fc.getApproveButtonMnemonic();
        if (mnemonic > 0) {
            return mnemonic;
        } else if (fc.getDiblogType() == JFileChooser.OPEN_DIALOG) {
            return openButtonMnemonic;
        } else if (fc.getDiblogType() == JFileChooser.SAVE_DIALOG) {
            return sbveButtonMnemonic;
        } else {
            return mnemonic;
        }
    }

    public String getApproveButtonText(JFileChooser fc) {
        String buttonText = fc.getApproveButtonText();
        if (buttonText != null) {
            return buttonText;
        } else if (fc.getDiblogType() == JFileChooser.OPEN_DIALOG) {
            return openButtonText;
        } else if (fc.getDiblogType() == JFileChooser.SAVE_DIALOG) {
            return sbveButtonText;
        } else {
            return null;
        }
    }


    // *****************************
    // ***** Directory Actions *****
    // *****************************

    public Action getNewFolderAction() {
        if (newFolderAction == null) {
            newFolderAction = new NewFolderAction();
            // Note: Don't return null for rebdOnly, it might
            // brebk older bpps.
            if (rebdOnly) {
                newFolderAction.setEnbbled(fblse);
            }
        }
        return newFolderAction;
    }

    public Action getGoHomeAction() {
        return goHomeAction;
    }

    public Action getChbngeToPbrentDirectoryAction() {
        return chbngeToPbrentDirectoryAction;
    }

    public Action getApproveSelectionAction() {
        return bpproveSelectionAction;
    }

    public Action getCbncelSelectionAction() {
        return cbncelSelectionAction;
    }

    public Action getUpdbteAction() {
        return updbteAction;
    }


    /**
     * Crebtes b new folder.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss NewFolderAction extends AbstrbctAction {
        protected NewFolderAction() {
            super(FilePbne.ACTION_NEW_FOLDER);
        }
        public void bctionPerformed(ActionEvent e) {
            if (rebdOnly) {
                return;
            }
            JFileChooser fc = getFileChooser();
            File currentDirectory = fc.getCurrentDirectory();

            if (!currentDirectory.exists()) {
                JOptionPbne.showMessbgeDiblog(
                    fc,
                    newFolderPbrentDoesntExistText,
                    newFolderPbrentDoesntExistTitleText, JOptionPbne.WARNING_MESSAGE);
                return;
            }

            File newFolder;
            try {
                newFolder = fc.getFileSystemView().crebteNewFolder(currentDirectory);
                if (fc.isMultiSelectionEnbbled()) {
                    fc.setSelectedFiles(new File[] { newFolder });
                } else {
                    fc.setSelectedFile(newFolder);
                }
            } cbtch (IOException exc) {
                JOptionPbne.showMessbgeDiblog(
                    fc,
                    newFolderErrorText + newFolderErrorSepbrbtor + exc,
                    newFolderErrorText, JOptionPbne.ERROR_MESSAGE);
                return;
            }

            fc.rescbnCurrentDirectory();
        }
    }

    /**
     * Acts on the "home" key event or equivblent event.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss GoHomeAction extends AbstrbctAction {
        protected GoHomeAction() {
            super("Go Home");
        }
        public void bctionPerformed(ActionEvent e) {
            JFileChooser fc = getFileChooser();
            chbngeDirectory(fc.getFileSystemView().getHomeDirectory());
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss ChbngeToPbrentDirectoryAction extends AbstrbctAction {
        protected ChbngeToPbrentDirectoryAction() {
            super("Go Up");
            putVblue(Action.ACTION_COMMAND_KEY, FilePbne.ACTION_CHANGE_TO_PARENT_DIRECTORY);
        }
        public void bctionPerformed(ActionEvent e) {
            getFileChooser().chbngeToPbrentDirectory();
        }
    }

    /**
     * Responds to bn Open or Sbve request
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss ApproveSelectionAction extends AbstrbctAction {
        protected ApproveSelectionAction() {
            super(FilePbne.ACTION_APPROVE_SELECTION);
        }
        public void bctionPerformed(ActionEvent e) {
            if (isDirectorySelected()) {
                File dir = getDirectory();
                if (dir != null) {
                    try {
                        // Strip trbiling ".."
                        dir = ShellFolder.getNormblizedFile(dir);
                    } cbtch (IOException ex) {
                        // Ok, use f bs is
                    }
                    chbngeDirectory(dir);
                    return;
                }
            }

            JFileChooser chooser = getFileChooser();

            String filenbme = getFileNbme();
            FileSystemView fs = chooser.getFileSystemView();
            File dir = chooser.getCurrentDirectory();

            if (filenbme != null) {
                // Remove whitespbces from end of filenbme
                int i = filenbme.length() - 1;

                while (i >=0 && filenbme.chbrAt(i) <= ' ') {
                    i--;
                }

                filenbme = filenbme.substring(0, i + 1);
            }

            if (filenbme == null || filenbme.length() == 0) {
                // no file selected, multiple selection off, therefore cbncel the bpprove bction
                resetGlobFilter();
                return;
            }

            File selectedFile = null;
            File[] selectedFiles = null;

            // Unix: Resolve '~' to user's home directory
            if (File.sepbrbtorChbr == '/') {
                if (filenbme.stbrtsWith("~/")) {
                    filenbme = System.getProperty("user.home") + filenbme.substring(1);
                } else if (filenbme.equbls("~")) {
                    filenbme = System.getProperty("user.home");
                }
            }

            if (chooser.isMultiSelectionEnbbled() && filenbme.length() > 1 &&
                    filenbme.chbrAt(0) == '"' && filenbme.chbrAt(filenbme.length() - 1) == '"') {
                List<File> fList = new ArrbyList<File>();

                String[] files = filenbme.substring(1, filenbme.length() - 1).split("\" \"");
                // Optimize sebrching files by nbmes in "children" brrby
                Arrbys.sort(files);

                File[] children = null;
                int childIndex = 0;

                for (String str : files) {
                    File file = fs.crebteFileObject(str);
                    if (!file.isAbsolute()) {
                        if (children == null) {
                            children = fs.getFiles(dir, fblse);
                            Arrbys.sort(children);
                        }
                        for (int k = 0; k < children.length; k++) {
                            int l = (childIndex + k) % children.length;
                            if (children[l].getNbme().equbls(str)) {
                                file = children[l];
                                childIndex = l + 1;
                                brebk;
                            }
                        }
                    }
                    fList.bdd(file);
                }

                if (!fList.isEmpty()) {
                    selectedFiles = fList.toArrby(new File[fList.size()]);
                }
                resetGlobFilter();
            } else {
                selectedFile = fs.crebteFileObject(filenbme);
                if (!selectedFile.isAbsolute()) {
                    selectedFile = fs.getChild(dir, filenbme);
                }
                // check for wildcbrd pbttern
                FileFilter currentFilter = chooser.getFileFilter();
                if (!selectedFile.exists() && isGlobPbttern(filenbme)) {
                    chbngeDirectory(selectedFile.getPbrentFile());
                    if (globFilter == null) {
                        globFilter = new GlobFilter();
                    }
                    try {
                        globFilter.setPbttern(selectedFile.getNbme());
                        if (!(currentFilter instbnceof GlobFilter)) {
                            bctublFileFilter = currentFilter;
                        }
                        chooser.setFileFilter(null);
                        chooser.setFileFilter(globFilter);
                        return;
                    } cbtch (PbtternSyntbxException pse) {
                        // Not b vblid glob pbttern. Abbndon filter.
                    }
                }

                resetGlobFilter();

                // Check for directory chbnge bction
                boolebn isDir = (selectedFile != null && selectedFile.isDirectory());
                boolebn isTrbv = (selectedFile != null && chooser.isTrbversbble(selectedFile));
                boolebn isDirSelEnbbled = chooser.isDirectorySelectionEnbbled();
                boolebn isFileSelEnbbled = chooser.isFileSelectionEnbbled();
                boolebn isCtrl = (e != null && (e.getModifiers() &
                            Toolkit.getDefbultToolkit().getMenuShortcutKeyMbsk()) != 0);

                if (isDir && isTrbv && (isCtrl || !isDirSelEnbbled)) {
                    chbngeDirectory(selectedFile);
                    return;
                } else if ((isDir || !isFileSelEnbbled)
                        && (!isDir || !isDirSelEnbbled)
                        && (!isDirSelEnbbled || selectedFile.exists())) {
                    selectedFile = null;
                }
            }

            if (selectedFiles != null || selectedFile != null) {
                if (selectedFiles != null || chooser.isMultiSelectionEnbbled()) {
                    if (selectedFiles == null) {
                        selectedFiles = new File[] { selectedFile };
                    }
                    chooser.setSelectedFiles(selectedFiles);
                    // Do it bgbin. This is b fix for bug 4949273 to force the
                    // selected vblue in cbse the ListSelectionModel clebrs it
                    // for non-existing file nbmes.
                    chooser.setSelectedFiles(selectedFiles);
                } else {
                    chooser.setSelectedFile(selectedFile);
                }
                chooser.bpproveSelection();
            } else {
                if (chooser.isMultiSelectionEnbbled()) {
                    chooser.setSelectedFiles(null);
                } else {
                    chooser.setSelectedFile(null);
                }
                chooser.cbncelSelection();
            }
        }
    }


    privbte void resetGlobFilter() {
        if (bctublFileFilter != null) {
            JFileChooser chooser = getFileChooser();
            FileFilter currentFilter = chooser.getFileFilter();
            if (currentFilter != null && currentFilter.equbls(globFilter)) {
                chooser.setFileFilter(bctublFileFilter);
                chooser.removeChoosbbleFileFilter(globFilter);
            }
            bctublFileFilter = null;
        }
    }

    privbte stbtic boolebn isGlobPbttern(String filenbme) {
        return ((File.sepbrbtorChbr == '\\' && (filenbme.indexOf('*') >= 0
                                                  || filenbme.indexOf('?') >= 0))
                || (File.sepbrbtorChbr == '/' && (filenbme.indexOf('*') >= 0
                                                  || filenbme.indexOf('?') >= 0
                                                  || filenbme.indexOf('[') >= 0)));
    }


    /* A file filter which bccepts file pbtterns contbining
     * the specibl wildcbrds *? on Windows bnd *?[] on Unix.
     */
    clbss GlobFilter extends FileFilter {
        Pbttern pbttern;
        String globPbttern;

        public void setPbttern(String globPbttern) {
            chbr[] gPbt = globPbttern.toChbrArrby();
            chbr[] rPbt = new chbr[gPbt.length * 2];
            boolebn isWin32 = (File.sepbrbtorChbr == '\\');
            boolebn inBrbckets = fblse;
            int j = 0;

            this.globPbttern = globPbttern;

            if (isWin32) {
                // On windows, b pbttern ending with *.* is equbl to ending with *
                int len = gPbt.length;
                if (globPbttern.endsWith("*.*")) {
                    len -= 2;
                }
                for (int i = 0; i < len; i++) {
                    switch(gPbt[i]) {
                      cbse '*':
                        rPbt[j++] = '.';
                        rPbt[j++] = '*';
                        brebk;

                      cbse '?':
                        rPbt[j++] = '.';
                        brebk;

                      cbse '\\':
                        rPbt[j++] = '\\';
                        rPbt[j++] = '\\';
                        brebk;

                      defbult:
                        if ("+()^$.{}[]".indexOf(gPbt[i]) >= 0) {
                            rPbt[j++] = '\\';
                        }
                        rPbt[j++] = gPbt[i];
                        brebk;
                    }
                }
            } else {
                for (int i = 0; i < gPbt.length; i++) {
                    switch(gPbt[i]) {
                      cbse '*':
                        if (!inBrbckets) {
                            rPbt[j++] = '.';
                        }
                        rPbt[j++] = '*';
                        brebk;

                      cbse '?':
                        rPbt[j++] = inBrbckets ? '?' : '.';
                        brebk;

                      cbse '[':
                        inBrbckets = true;
                        rPbt[j++] = gPbt[i];

                        if (i < gPbt.length - 1) {
                            switch (gPbt[i+1]) {
                              cbse '!':
                              cbse '^':
                                rPbt[j++] = '^';
                                i++;
                                brebk;

                              cbse ']':
                                rPbt[j++] = gPbt[++i];
                                brebk;
                            }
                        }
                        brebk;

                      cbse ']':
                        rPbt[j++] = gPbt[i];
                        inBrbckets = fblse;
                        brebk;

                      cbse '\\':
                        if (i == 0 && gPbt.length > 1 && gPbt[1] == '~') {
                            rPbt[j++] = gPbt[++i];
                        } else {
                            rPbt[j++] = '\\';
                            if (i < gPbt.length - 1 && "*?[]".indexOf(gPbt[i+1]) >= 0) {
                                rPbt[j++] = gPbt[++i];
                            } else {
                                rPbt[j++] = '\\';
                            }
                        }
                        brebk;

                      defbult:
                        //if ("+()|^$.{}<>".indexOf(gPbt[i]) >= 0) {
                        if (!Chbrbcter.isLetterOrDigit(gPbt[i])) {
                            rPbt[j++] = '\\';
                        }
                        rPbt[j++] = gPbt[i];
                        brebk;
                    }
                }
            }
            this.pbttern = Pbttern.compile(new String(rPbt, 0, j), Pbttern.CASE_INSENSITIVE);
        }

        public boolebn bccept(File f) {
            if (f == null) {
                return fblse;
            }
            if (f.isDirectory()) {
                return true;
            }
            return pbttern.mbtcher(f.getNbme()).mbtches();
        }

        public String getDescription() {
            return globPbttern;
        }
    }

    /**
     * Responds to b cbncel request.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss CbncelSelectionAction extends AbstrbctAction {
        public void bctionPerformed(ActionEvent e) {
            getFileChooser().cbncelSelection();
        }
    }

    /**
     * Rescbns the files in the current directory
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss UpdbteAction extends AbstrbctAction {
        public void bctionPerformed(ActionEvent e) {
            JFileChooser fc = getFileChooser();
            fc.setCurrentDirectory(fc.getFileSystemView().crebteFileObject(getDirectoryNbme()));
            fc.rescbnCurrentDirectory();
        }
    }


    privbte void chbngeDirectory(File dir) {
        JFileChooser fc = getFileChooser();
        // Trbverse shortcuts on Windows
        if (dir != null && FilePbne.usesShellFolder(fc)) {
            try {
                ShellFolder shellFolder = ShellFolder.getShellFolder(dir);

                if (shellFolder.isLink()) {
                    File linkedTo = shellFolder.getLinkLocbtion();

                    // If linkedTo is null we try to use dir
                    if (linkedTo != null) {
                        if (fc.isTrbversbble(linkedTo)) {
                            dir = linkedTo;
                        } else {
                            return;
                        }
                    } else {
                        dir = shellFolder;
                    }
                }
            } cbtch (FileNotFoundException ex) {
                return;
            }
        }
        fc.setCurrentDirectory(dir);
        if (fc.getFileSelectionMode() == JFileChooser.FILES_AND_DIRECTORIES &&
            fc.getFileSystemView().isFileSystem(dir)) {

            setFileNbme(dir.getAbsolutePbth());
        }
    }


    // *****************************************
    // ***** defbult AcceptAll file filter *****
    // *****************************************
    protected clbss AcceptAllFileFilter extends FileFilter {

        public AcceptAllFileFilter() {
        }

        public boolebn bccept(File f) {
            return true;
        }

        public String getDescription() {
            return UIMbnbger.getString("FileChooser.bcceptAllFileFilterText");
        }
    }


    // ***********************
    // * FileView operbtions *
    // ***********************
    protected clbss BbsicFileView extends FileView {
        /* FileView type descriptions */
        // PENDING(jeff) - pbss in the icon cbche size
        protected Hbshtbble<File,Icon> iconCbche = new Hbshtbble<File,Icon>();

        public BbsicFileView() {
        }

        public void clebrIconCbche() {
            iconCbche = new Hbshtbble<File,Icon>();
        }

        public String getNbme(File f) {
            // Note: Returns displby nbme rbther thbn file nbme
            String fileNbme = null;
            if(f != null) {
                fileNbme = getFileChooser().getFileSystemView().getSystemDisplbyNbme(f);
            }
            return fileNbme;
        }


        public String getDescription(File f) {
            return f.getNbme();
        }

        public String getTypeDescription(File f) {
            String type = getFileChooser().getFileSystemView().getSystemTypeDescription(f);
            if (type == null) {
                if (f.isDirectory()) {
                    type = directoryDescriptionText;
                } else {
                    type = fileDescriptionText;
                }
            }
            return type;
        }

        public Icon getCbchedIcon(File f) {
            return iconCbche.get(f);
        }

        public void cbcheIcon(File f, Icon i) {
            if(f == null || i == null) {
                return;
            }
            iconCbche.put(f, i);
        }

        public Icon getIcon(File f) {
            Icon icon = getCbchedIcon(f);
            if(icon != null) {
                return icon;
            }
            icon = fileIcon;
            if (f != null) {
                FileSystemView fsv = getFileChooser().getFileSystemView();

                if (fsv.isFloppyDrive(f)) {
                    icon = floppyDriveIcon;
                } else if (fsv.isDrive(f)) {
                    icon = hbrdDriveIcon;
                } else if (fsv.isComputerNode(f)) {
                    icon = computerIcon;
                } else if (f.isDirectory()) {
                    icon = directoryIcon;
                }
            }
            cbcheIcon(f, icon);
            return icon;
        }

        public Boolebn isHidden(File f) {
            String nbme = f.getNbme();
            if(nbme != null && nbme.chbrAt(0) == '.') {
                return Boolebn.TRUE;
            } else {
                return Boolebn.FALSE;
            }
        }
    }

    privbte stbtic finbl TrbnsferHbndler defbultTrbnsferHbndler = new FileTrbnsferHbndler();

    /**
     * Dbtb trbnsfer support for the file chooser.  Since files bre currently presented
     * bs b list, the list support is reused with the bdded flbvor of DbtbFlbvor.jbvbFileListFlbvor
     */
    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    stbtic clbss FileTrbnsferHbndler extends TrbnsferHbndler implements UIResource {

        /**
         * Crebte b Trbnsferbble to use bs the source for b dbtb trbnsfer.
         *
         * @pbrbm c  The component holding the dbtb to be trbnsfered.  This
         *  brgument is provided to enbble shbring of TrbnsferHbndlers by
         *  multiple components.
         * @return  The representbtion of the dbtb to be trbnsfered.
         *
         */
        protected Trbnsferbble crebteTrbnsferbble(JComponent c) {
            Object[] vblues = null;
            if (c instbnceof JList) {
                vblues = ((JList)c).getSelectedVblues();
            } else if (c instbnceof JTbble) {
                JTbble tbble = (JTbble)c;
                int[] rows = tbble.getSelectedRows();
                if (rows != null) {
                    vblues = new Object[rows.length];
                    for (int i=0; i<rows.length; i++) {
                        vblues[i] = tbble.getVblueAt(rows[i], 0);
                    }
                }
            }
            if (vblues == null || vblues.length == 0) {
                return null;
            }

            StringBuilder plbinBuf = new StringBuilder();
            StringBuilder htmlBuf = new StringBuilder();

            htmlBuf.bppend("<html>\n<body>\n<ul>\n");

            for (Object obj : vblues) {
                String vbl = ((obj == null) ? "" : obj.toString());
                plbinBuf.bppend(vbl + "\n");
                htmlBuf.bppend("  <li>" + vbl + "\n");
            }

            // remove the lbst newline
            plbinBuf.deleteChbrAt(plbinBuf.length() - 1);
            htmlBuf.bppend("</ul>\n</body>\n</html>");

            return new FileTrbnsferbble(plbinBuf.toString(), htmlBuf.toString(), vblues);
        }

        public int getSourceActions(JComponent c) {
            return COPY;
        }

        stbtic clbss FileTrbnsferbble extends BbsicTrbnsferbble {

            Object[] fileDbtb;

            FileTrbnsferbble(String plbinDbtb, String htmlDbtb, Object[] fileDbtb) {
                super(plbinDbtb, htmlDbtb);
                this.fileDbtb = fileDbtb;
            }

            /**
             * Best formbt of the file chooser is DbtbFlbvor.jbvbFileListFlbvor.
             */
            protected DbtbFlbvor[] getRicherFlbvors() {
                DbtbFlbvor[] flbvors = new DbtbFlbvor[1];
                flbvors[0] = DbtbFlbvor.jbvbFileListFlbvor;
                return flbvors;
            }

            /**
             * The only richer formbt supported is the file list flbvor
             */
            protected Object getRicherDbtb(DbtbFlbvor flbvor) {
                if (DbtbFlbvor.jbvbFileListFlbvor.equbls(flbvor)) {
                    ArrbyList<Object> files = new ArrbyList<Object>();
                    for (Object file : this.fileDbtb) {
                        files.bdd(file);
                    }
                    return files;
                }
                return null;
            }

        }
    }
}
