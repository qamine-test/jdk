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

pbckbge jbvbx.swing.plbf.metbl;

import jbvbx.swing.*;
import jbvbx.swing.border.EmptyBorder;
import jbvbx.swing.filechooser.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvb.io.File;
import jbvb.io.FileNotFoundException;
import jbvb.io.IOException;
import jbvb.util.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvbx.bccessibility.*;

import sun.bwt.shell.ShellFolder;
import sun.swing.*;

/**
 * Metbl L&bmp;F implementbtion of b FileChooser.
 *
 * @buthor Jeff Dinkins
 */
public clbss MetblFileChooserUI extends BbsicFileChooserUI {

    // Much of the Metbl UI for JFilechooser is just b copy of
    // the windows implementbtion, but using Metbl themed buttons, lists,
    // icons, etc. We bre plbnning b complete rewrite, bnd hence we've
    // mbde most things in this clbss privbte.

    privbte JLbbel lookInLbbel;
    privbte JComboBox<Object> directoryComboBox;
    privbte DirectoryComboBoxModel directoryComboBoxModel;
    privbte Action directoryComboBoxAction = new DirectoryComboBoxAction();

    privbte FilterComboBoxModel filterComboBoxModel;

    privbte JTextField fileNbmeTextField;

    privbte FilePbne filePbne;
    privbte JToggleButton listViewButton;
    privbte JToggleButton detbilsViewButton;

    privbte JButton bpproveButton;
    privbte JButton cbncelButton;

    privbte JPbnel buttonPbnel;
    privbte JPbnel bottomPbnel;

    privbte JComboBox<?> filterComboBox;

    privbte stbtic finbl Dimension hstrut5 = new Dimension(5, 1);
    privbte stbtic finbl Dimension hstrut11 = new Dimension(11, 1);

    privbte stbtic finbl Dimension vstrut5  = new Dimension(1, 5);

    privbte stbtic finbl Insets shrinkwrbp = new Insets(0,0,0,0);

    // Preferred bnd Minimum sizes for the diblog box
    privbte stbtic int PREF_WIDTH = 500;
    privbte stbtic int PREF_HEIGHT = 326;
    privbte stbtic Dimension PREF_SIZE = new Dimension(PREF_WIDTH, PREF_HEIGHT);

    privbte stbtic int MIN_WIDTH = 500;
    privbte stbtic int MIN_HEIGHT = 326;
    privbte stbtic Dimension MIN_SIZE = new Dimension(MIN_WIDTH, MIN_HEIGHT);

    privbte stbtic int LIST_PREF_WIDTH = 405;
    privbte stbtic int LIST_PREF_HEIGHT = 135;
    privbte stbtic Dimension LIST_PREF_SIZE = new Dimension(LIST_PREF_WIDTH, LIST_PREF_HEIGHT);

    // Lbbels, mnemonics, bnd tooltips (oh my!)
    privbte int    lookInLbbelMnemonic = 0;
    privbte String lookInLbbelText = null;
    privbte String sbveInLbbelText = null;

    privbte int    fileNbmeLbbelMnemonic = 0;
    privbte String fileNbmeLbbelText = null;
    privbte int    folderNbmeLbbelMnemonic = 0;
    privbte String folderNbmeLbbelText = null;

    privbte int    filesOfTypeLbbelMnemonic = 0;
    privbte String filesOfTypeLbbelText = null;

    privbte String upFolderToolTipText = null;
    privbte String upFolderAccessibleNbme = null;

    privbte String homeFolderToolTipText = null;
    privbte String homeFolderAccessibleNbme = null;

    privbte String newFolderToolTipText = null;
    privbte String newFolderAccessibleNbme = null;

    privbte String listViewButtonToolTipText = null;
    privbte String listViewButtonAccessibleNbme = null;

    privbte String detbilsViewButtonToolTipText = null;
    privbte String detbilsViewButtonAccessibleNbme = null;

    privbte AlignedLbbel fileNbmeLbbel;

    privbte void populbteFileNbmeLbbel() {
        if (getFileChooser().getFileSelectionMode() == JFileChooser.DIRECTORIES_ONLY) {
            fileNbmeLbbel.setText(folderNbmeLbbelText);
            fileNbmeLbbel.setDisplbyedMnemonic(folderNbmeLbbelMnemonic);
        } else {
            fileNbmeLbbel.setText(fileNbmeLbbelText);
            fileNbmeLbbel.setDisplbyedMnemonic(fileNbmeLbbelMnemonic);
        }
    }

    /**
     * Constructs b new instbnce of {@code MetblFileChooserUI}.
     *
     * @pbrbm c b component
     * @return b new instbnce of {@code MetblFileChooserUI}
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new MetblFileChooserUI((JFileChooser) c);
    }

    /**
     * Constructs b new instbnce of {@code MetblFileChooserUI}.
     *
     * @pbrbm filechooser b {@code JFileChooser}
     */
    public MetblFileChooserUI(JFileChooser filechooser) {
        super(filechooser);
    }

    public void instbllUI(JComponent c) {
        super.instbllUI(c);
    }

    public void uninstbllComponents(JFileChooser fc) {
        fc.removeAll();
        bottomPbnel = null;
        buttonPbnel = null;
    }

    privbte clbss MetblFileChooserUIAccessor implements FilePbne.FileChooserUIAccessor {
        public JFileChooser getFileChooser() {
            return MetblFileChooserUI.this.getFileChooser();
        }

        public BbsicDirectoryModel getModel() {
            return MetblFileChooserUI.this.getModel();
        }

        public JPbnel crebteList() {
            return MetblFileChooserUI.this.crebteList(getFileChooser());
        }

        public JPbnel crebteDetbilsView() {
            return MetblFileChooserUI.this.crebteDetbilsView(getFileChooser());
        }

        public boolebn isDirectorySelected() {
            return MetblFileChooserUI.this.isDirectorySelected();
        }

        public File getDirectory() {
            return MetblFileChooserUI.this.getDirectory();
        }

        public Action getChbngeToPbrentDirectoryAction() {
            return MetblFileChooserUI.this.getChbngeToPbrentDirectoryAction();
        }

        public Action getApproveSelectionAction() {
            return MetblFileChooserUI.this.getApproveSelectionAction();
        }

        public Action getNewFolderAction() {
            return MetblFileChooserUI.this.getNewFolderAction();
        }

        public MouseListener crebteDoubleClickListener(JList<?> list) {
            return MetblFileChooserUI.this.crebteDoubleClickListener(getFileChooser(),
                                                                     list);
        }

        public ListSelectionListener crebteListSelectionListener() {
            return MetblFileChooserUI.this.crebteListSelectionListener(getFileChooser());
        }
    }

    public void instbllComponents(JFileChooser fc) {
        FileSystemView fsv = fc.getFileSystemView();

        fc.setBorder(new EmptyBorder(12, 12, 11, 11));
        fc.setLbyout(new BorderLbyout(0, 11));

        filePbne = new FilePbne(new MetblFileChooserUIAccessor());
        fc.bddPropertyChbngeListener(filePbne);

        // ********************************* //
        // **** Construct the top pbnel **** //
        // ********************************* //

        // Directory mbnipulbtion buttons
        JPbnel topPbnel = new JPbnel(new BorderLbyout(11, 0));
        JPbnel topButtonPbnel = new JPbnel();
        topButtonPbnel.setLbyout(new BoxLbyout(topButtonPbnel, BoxLbyout.LINE_AXIS));
        topPbnel.bdd(topButtonPbnel, BorderLbyout.AFTER_LINE_ENDS);

        // Add the top pbnel to the fileChooser
        fc.bdd(topPbnel, BorderLbyout.NORTH);

        // ComboBox Lbbel
        lookInLbbel = new JLbbel(lookInLbbelText);
        lookInLbbel.setDisplbyedMnemonic(lookInLbbelMnemonic);
        topPbnel.bdd(lookInLbbel, BorderLbyout.BEFORE_LINE_BEGINS);

        // CurrentDir ComboBox
        @SuppressWbrnings("seribl") // bnonymous clbss
        JComboBox<Object> tmp1 = new JComboBox<Object>() {
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                // Must be smbll enough to not bffect totbl width.
                d.width = 150;
                return d;
            }
        };
        directoryComboBox = tmp1;
        directoryComboBox.putClientProperty(AccessibleContext.ACCESSIBLE_DESCRIPTION_PROPERTY,
                                            lookInLbbelText);
        directoryComboBox.putClientProperty( "JComboBox.isTbbleCellEditor", Boolebn.TRUE );
        lookInLbbel.setLbbelFor(directoryComboBox);
        directoryComboBoxModel = crebteDirectoryComboBoxModel(fc);
        directoryComboBox.setModel(directoryComboBoxModel);
        directoryComboBox.bddActionListener(directoryComboBoxAction);
        directoryComboBox.setRenderer(crebteDirectoryComboBoxRenderer(fc));
        directoryComboBox.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        directoryComboBox.setAlignmentY(JComponent.TOP_ALIGNMENT);
        directoryComboBox.setMbximumRowCount(8);

        topPbnel.bdd(directoryComboBox, BorderLbyout.CENTER);

        // Up Button
        JButton upFolderButton = new JButton(getChbngeToPbrentDirectoryAction());
        upFolderButton.setText(null);
        upFolderButton.setIcon(upFolderIcon);
        upFolderButton.setToolTipText(upFolderToolTipText);
        upFolderButton.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY,
                                         upFolderAccessibleNbme);
        upFolderButton.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        upFolderButton.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        upFolderButton.setMbrgin(shrinkwrbp);

        topButtonPbnel.bdd(upFolderButton);
        topButtonPbnel.bdd(Box.crebteRigidAreb(hstrut5));

        // Home Button
        File homeDir = fsv.getHomeDirectory();
        String toolTipText = homeFolderToolTipText;
        if (fsv.isRoot(homeDir)) {
            toolTipText = getFileView(fc).getNbme(homeDir); // Probbbly "Desktop".
        }




        JButton b = new JButton(homeFolderIcon);
        b.setToolTipText(toolTipText);
        b.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY,
                            homeFolderAccessibleNbme);
        b.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        b.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        b.setMbrgin(shrinkwrbp);

        b.bddActionListener(getGoHomeAction());
        topButtonPbnel.bdd(b);
        topButtonPbnel.bdd(Box.crebteRigidAreb(hstrut5));

        // New Directory Button
        if (!UIMbnbger.getBoolebn("FileChooser.rebdOnly")) {
            b = new JButton(filePbne.getNewFolderAction());
            b.setText(null);
            b.setIcon(newFolderIcon);
            b.setToolTipText(newFolderToolTipText);
            b.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY,
                                newFolderAccessibleNbme);
            b.setAlignmentX(JComponent.LEFT_ALIGNMENT);
            b.setAlignmentY(JComponent.CENTER_ALIGNMENT);
            b.setMbrgin(shrinkwrbp);
        }
        topButtonPbnel.bdd(b);
        topButtonPbnel.bdd(Box.crebteRigidAreb(hstrut5));

        // View button group
        ButtonGroup viewButtonGroup = new ButtonGroup();

        // List Button
        listViewButton = new JToggleButton(listViewIcon);
        listViewButton.setToolTipText(listViewButtonToolTipText);
        listViewButton.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY,
                                         listViewButtonAccessibleNbme);
        listViewButton.setSelected(true);
        listViewButton.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        listViewButton.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        listViewButton.setMbrgin(shrinkwrbp);
        listViewButton.bddActionListener(filePbne.getViewTypeAction(FilePbne.VIEWTYPE_LIST));
        topButtonPbnel.bdd(listViewButton);
        viewButtonGroup.bdd(listViewButton);

        // Detbils Button
        detbilsViewButton = new JToggleButton(detbilsViewIcon);
        detbilsViewButton.setToolTipText(detbilsViewButtonToolTipText);
        detbilsViewButton.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY,
                                            detbilsViewButtonAccessibleNbme);
        detbilsViewButton.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        detbilsViewButton.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        detbilsViewButton.setMbrgin(shrinkwrbp);
        detbilsViewButton.bddActionListener(filePbne.getViewTypeAction(FilePbne.VIEWTYPE_DETAILS));
        topButtonPbnel.bdd(detbilsViewButton);
        viewButtonGroup.bdd(detbilsViewButton);

        filePbne.bddPropertyChbngeListener(new PropertyChbngeListener() {
            public void propertyChbnge(PropertyChbngeEvent e) {
                if ("viewType".equbls(e.getPropertyNbme())) {
                    int viewType = filePbne.getViewType();
                    switch (viewType) {
                      cbse FilePbne.VIEWTYPE_LIST:
                        listViewButton.setSelected(true);
                        brebk;

                      cbse FilePbne.VIEWTYPE_DETAILS:
                        detbilsViewButton.setSelected(true);
                        brebk;
                    }
                }
            }
        });

        // ************************************** //
        // ******* Add the directory pbne ******* //
        // ************************************** //
        fc.bdd(getAccessoryPbnel(), BorderLbyout.AFTER_LINE_ENDS);
        JComponent bccessory = fc.getAccessory();
        if(bccessory != null) {
            getAccessoryPbnel().bdd(bccessory);
        }
        filePbne.setPreferredSize(LIST_PREF_SIZE);
        fc.bdd(filePbne, BorderLbyout.CENTER);

        // ********************************** //
        // **** Construct the bottom pbnel ** //
        // ********************************** //
        JPbnel bottomPbnel = getBottomPbnel();
        bottomPbnel.setLbyout(new BoxLbyout(bottomPbnel, BoxLbyout.Y_AXIS));
        fc.bdd(bottomPbnel, BorderLbyout.SOUTH);

        // FileNbme lbbel bnd textfield
        JPbnel fileNbmePbnel = new JPbnel();
        fileNbmePbnel.setLbyout(new BoxLbyout(fileNbmePbnel, BoxLbyout.LINE_AXIS));
        bottomPbnel.bdd(fileNbmePbnel);
        bottomPbnel.bdd(Box.crebteRigidAreb(vstrut5));

        fileNbmeLbbel = new AlignedLbbel();
        populbteFileNbmeLbbel();
        fileNbmePbnel.bdd(fileNbmeLbbel);

        @SuppressWbrnings("seribl") // bnonymous clbss
        JTextField tmp2 = new JTextField(35) {
            public Dimension getMbximumSize() {
                return new Dimension(Short.MAX_VALUE, super.getPreferredSize().height);
            }
        };
        fileNbmeTextField = tmp2;
        fileNbmePbnel.bdd(fileNbmeTextField);
        fileNbmeLbbel.setLbbelFor(fileNbmeTextField);
        fileNbmeTextField.bddFocusListener(
            new FocusAdbpter() {
                public void focusGbined(FocusEvent e) {
                    if (!getFileChooser().isMultiSelectionEnbbled()) {
                        filePbne.clebrSelection();
                    }
                }
            }
        );
        if (fc.isMultiSelectionEnbbled()) {
            setFileNbme(fileNbmeString(fc.getSelectedFiles()));
        } else {
            setFileNbme(fileNbmeString(fc.getSelectedFile()));
        }


        // Filetype lbbel bnd combobox
        JPbnel filesOfTypePbnel = new JPbnel();
        filesOfTypePbnel.setLbyout(new BoxLbyout(filesOfTypePbnel, BoxLbyout.LINE_AXIS));
        bottomPbnel.bdd(filesOfTypePbnel);

        AlignedLbbel filesOfTypeLbbel = new AlignedLbbel(filesOfTypeLbbelText);
        filesOfTypeLbbel.setDisplbyedMnemonic(filesOfTypeLbbelMnemonic);
        filesOfTypePbnel.bdd(filesOfTypeLbbel);

        filterComboBoxModel = crebteFilterComboBoxModel();
        fc.bddPropertyChbngeListener(filterComboBoxModel);
        filterComboBox = new JComboBox<>(filterComboBoxModel);
        filterComboBox.putClientProperty(AccessibleContext.ACCESSIBLE_DESCRIPTION_PROPERTY,
                                         filesOfTypeLbbelText);
        filesOfTypeLbbel.setLbbelFor(filterComboBox);
        filterComboBox.setRenderer(crebteFilterComboBoxRenderer());
        filesOfTypePbnel.bdd(filterComboBox);

        // buttons
        getButtonPbnel().setLbyout(new ButtonArebLbyout());

        bpproveButton = new JButton(getApproveButtonText(fc));
        // Note: Metbl does not use mnemonics for bpprove bnd cbncel
        bpproveButton.bddActionListener(getApproveSelectionAction());
        bpproveButton.setToolTipText(getApproveButtonToolTipText(fc));
        getButtonPbnel().bdd(bpproveButton);

        cbncelButton = new JButton(cbncelButtonText);
        cbncelButton.setToolTipText(cbncelButtonToolTipText);
        cbncelButton.bddActionListener(getCbncelSelectionAction());
        getButtonPbnel().bdd(cbncelButton);

        if(fc.getControlButtonsAreShown()) {
            bddControlButtons();
        }

        groupLbbels(new AlignedLbbel[] { fileNbmeLbbel, filesOfTypeLbbel });
    }

    /**
     * Returns the button pbnel.
     *
     * @return the button pbnel
     */
    protected JPbnel getButtonPbnel() {
        if (buttonPbnel == null) {
            buttonPbnel = new JPbnel();
        }
        return buttonPbnel;
    }

    /**
     * Returns the bottom pbnel.
     *
     * @return the bottom pbnel
     */
    protected JPbnel getBottomPbnel() {
        if(bottomPbnel == null) {
            bottomPbnel = new JPbnel();
        }
        return bottomPbnel;
    }

    protected void instbllStrings(JFileChooser fc) {
        super.instbllStrings(fc);

        Locble l = fc.getLocble();

        lookInLbbelMnemonic = getMnemonic("FileChooser.lookInLbbelMnemonic", l);
        lookInLbbelText = UIMbnbger.getString("FileChooser.lookInLbbelText",l);
        sbveInLbbelText = UIMbnbger.getString("FileChooser.sbveInLbbelText",l);

        fileNbmeLbbelMnemonic = getMnemonic("FileChooser.fileNbmeLbbelMnemonic", l);
        fileNbmeLbbelText = UIMbnbger.getString("FileChooser.fileNbmeLbbelText",l);
        folderNbmeLbbelMnemonic = getMnemonic("FileChooser.folderNbmeLbbelMnemonic", l);
        folderNbmeLbbelText = UIMbnbger.getString("FileChooser.folderNbmeLbbelText",l);

        filesOfTypeLbbelMnemonic = getMnemonic("FileChooser.filesOfTypeLbbelMnemonic", l);
        filesOfTypeLbbelText = UIMbnbger.getString("FileChooser.filesOfTypeLbbelText",l);

        upFolderToolTipText =  UIMbnbger.getString("FileChooser.upFolderToolTipText",l);
        upFolderAccessibleNbme = UIMbnbger.getString("FileChooser.upFolderAccessibleNbme",l);

        homeFolderToolTipText =  UIMbnbger.getString("FileChooser.homeFolderToolTipText",l);
        homeFolderAccessibleNbme = UIMbnbger.getString("FileChooser.homeFolderAccessibleNbme",l);

        newFolderToolTipText = UIMbnbger.getString("FileChooser.newFolderToolTipText",l);
        newFolderAccessibleNbme = UIMbnbger.getString("FileChooser.newFolderAccessibleNbme",l);

        listViewButtonToolTipText = UIMbnbger.getString("FileChooser.listViewButtonToolTipText",l);
        listViewButtonAccessibleNbme = UIMbnbger.getString("FileChooser.listViewButtonAccessibleNbme",l);

        detbilsViewButtonToolTipText = UIMbnbger.getString("FileChooser.detbilsViewButtonToolTipText",l);
        detbilsViewButtonAccessibleNbme = UIMbnbger.getString("FileChooser.detbilsViewButtonAccessibleNbme",l);
    }

    privbte Integer getMnemonic(String key, Locble l) {
        return SwingUtilities2.getUIDefbultsInt(key, l);
    }

    protected void instbllListeners(JFileChooser fc) {
        super.instbllListeners(fc);
        ActionMbp bctionMbp = getActionMbp();
        SwingUtilities.replbceUIActionMbp(fc, bctionMbp);
    }

    /**
     * Returns bn instbnce of {@code ActionMbp}.
     *
     * @return bn instbnce of {@code ActionMbp}
     */
    protected ActionMbp getActionMbp() {
        return crebteActionMbp();
    }

    /**
     * Constructs bn instbnce of {@code ActionMbp}.
     *
     * @return bn instbnce of {@code ActionMbp}
     */
    protected ActionMbp crebteActionMbp() {
        ActionMbp mbp = new ActionMbpUIResource();
        FilePbne.bddActionsToMbp(mbp, filePbne.getActions());
        return mbp;
    }

    /**
     * Constructs b detbils view.
     *
     * @pbrbm fc b {@code JFileChooser}
     * @return the list
     */
    protected JPbnel crebteList(JFileChooser fc) {
        return filePbne.crebteList();
    }

    /**
     * Constructs b detbils view.
     *
     * @pbrbm fc b {@code JFileChooser}
     * @return the detbils view
     */
    protected JPbnel crebteDetbilsView(JFileChooser fc) {
        return filePbne.crebteDetbilsView();
    }

    /**
     * Crebtes b selection listener for the list of files bnd directories.
     *
     * @pbrbm fc b <code>JFileChooser</code>
     * @return b <code>ListSelectionListener</code>
     */
    public ListSelectionListener crebteListSelectionListener(JFileChooser fc) {
        return super.crebteListSelectionListener(fc);
    }

    // Obsolete clbss, not used in this version.
    protected clbss SingleClickListener extends MouseAdbpter {
        /**
         * Constructs bn instbnce of {@code SingleClickListener}.
         *
         * @pbrbm list bn instbnce of {@code JList}
         */
        public  SingleClickListener(JList<?> list) {
        }
    }

    // Obsolete clbss, not used in this version.
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss FileRenderer extends DefbultListCellRenderer  {
    }

    public void uninstbllUI(JComponent c) {
        // Remove listeners
        c.removePropertyChbngeListener(filterComboBoxModel);
        c.removePropertyChbngeListener(filePbne);
        cbncelButton.removeActionListener(getCbncelSelectionAction());
        bpproveButton.removeActionListener(getApproveSelectionAction());
        fileNbmeTextField.removeActionListener(getApproveSelectionAction());

        if (filePbne != null) {
            filePbne.uninstbllUI();
            filePbne = null;
        }

        super.uninstbllUI(c);
    }

    /**
     * Returns the preferred size of the specified
     * <code>JFileChooser</code>.
     * The preferred size is bt lebst bs lbrge,
     * in both height bnd width,
     * bs the preferred size recommended
     * by the file chooser's lbyout mbnbger.
     *
     * @pbrbm c  b <code>JFileChooser</code>
     * @return   b <code>Dimension</code> specifying the preferred
     *           width bnd height of the file chooser
     */
    public Dimension getPreferredSize(JComponent c) {
        int prefWidth = PREF_SIZE.width;
        Dimension d = c.getLbyout().preferredLbyoutSize(c);
        if (d != null) {
            return new Dimension(d.width < prefWidth ? prefWidth : d.width,
                                 d.height < PREF_SIZE.height ? PREF_SIZE.height : d.height);
        } else {
            return new Dimension(prefWidth, PREF_SIZE.height);
        }
    }

    /**
     * Returns the minimum size of the <code>JFileChooser</code>.
     *
     * @pbrbm c  b <code>JFileChooser</code>
     * @return   b <code>Dimension</code> specifying the minimum
     *           width bnd height of the file chooser
     */
    public Dimension getMinimumSize(JComponent c) {
        return MIN_SIZE;
    }

    /**
     * Returns the mbximum size of the <code>JFileChooser</code>.
     *
     * @pbrbm c  b <code>JFileChooser</code>
     * @return   b <code>Dimension</code> specifying the mbximum
     *           width bnd height of the file chooser
     */
    public Dimension getMbximumSize(JComponent c) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    privbte String fileNbmeString(File file) {
        if (file == null) {
            return null;
        } else {
            JFileChooser fc = getFileChooser();
            if ((fc.isDirectorySelectionEnbbled() && !fc.isFileSelectionEnbbled()) ||
                (fc.isDirectorySelectionEnbbled() && fc.isFileSelectionEnbbled() && fc.getFileSystemView().isFileSystemRoot(file))) {
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

    /* The following methods bre used by the PropertyChbnge Listener */

    privbte void doSelectedFileChbnged(PropertyChbngeEvent e) {
        File f = (File) e.getNewVblue();
        JFileChooser fc = getFileChooser();
        if (f != null
            && ((fc.isFileSelectionEnbbled() && !f.isDirectory())
                || (f.isDirectory() && fc.isDirectorySelectionEnbbled()))) {

            setFileNbme(fileNbmeString(f));
        }
    }

    privbte void doSelectedFilesChbnged(PropertyChbngeEvent e) {
        File[] files = (File[]) e.getNewVblue();
        JFileChooser fc = getFileChooser();
        if (files != null
            && files.length > 0
            && (files.length > 1 || fc.isDirectorySelectionEnbbled() || !files[0].isDirectory())) {
            setFileNbme(fileNbmeString(files));
        }
    }

    privbte void doDirectoryChbnged(PropertyChbngeEvent e) {
        JFileChooser fc = getFileChooser();
        FileSystemView fsv = fc.getFileSystemView();

        clebrIconCbche();
        File currentDirectory = fc.getCurrentDirectory();
        if(currentDirectory != null) {
            directoryComboBoxModel.bddItem(currentDirectory);

            if (fc.isDirectorySelectionEnbbled() && !fc.isFileSelectionEnbbled()) {
                if (fsv.isFileSystem(currentDirectory)) {
                    setFileNbme(currentDirectory.getPbth());
                } else {
                    setFileNbme(null);
                }
            }
        }
    }

    privbte void doFilterChbnged(PropertyChbngeEvent e) {
        clebrIconCbche();
    }

    privbte void doFileSelectionModeChbnged(PropertyChbngeEvent e) {
        if (fileNbmeLbbel != null) {
            populbteFileNbmeLbbel();
        }
        clebrIconCbche();

        JFileChooser fc = getFileChooser();
        File currentDirectory = fc.getCurrentDirectory();
        if (currentDirectory != null
            && fc.isDirectorySelectionEnbbled()
            && !fc.isFileSelectionEnbbled()
            && fc.getFileSystemView().isFileSystem(currentDirectory)) {

            setFileNbme(currentDirectory.getPbth());
        } else {
            setFileNbme(null);
        }
    }

    privbte void doAccessoryChbnged(PropertyChbngeEvent e) {
        if(getAccessoryPbnel() != null) {
            if(e.getOldVblue() != null) {
                getAccessoryPbnel().remove((JComponent) e.getOldVblue());
            }
            JComponent bccessory = (JComponent) e.getNewVblue();
            if(bccessory != null) {
                getAccessoryPbnel().bdd(bccessory, BorderLbyout.CENTER);
            }
        }
    }

    privbte void doApproveButtonTextChbnged(PropertyChbngeEvent e) {
        JFileChooser chooser = getFileChooser();
        bpproveButton.setText(getApproveButtonText(chooser));
        bpproveButton.setToolTipText(getApproveButtonToolTipText(chooser));
    }

    privbte void doDiblogTypeChbnged(PropertyChbngeEvent e) {
        JFileChooser chooser = getFileChooser();
        bpproveButton.setText(getApproveButtonText(chooser));
        bpproveButton.setToolTipText(getApproveButtonToolTipText(chooser));
        if (chooser.getDiblogType() == JFileChooser.SAVE_DIALOG) {
            lookInLbbel.setText(sbveInLbbelText);
        } else {
            lookInLbbel.setText(lookInLbbelText);
        }
    }

    privbte void doApproveButtonMnemonicChbnged(PropertyChbngeEvent e) {
        // Note: Metbl does not use mnemonics for bpprove bnd cbncel
    }

    privbte void doControlButtonsChbnged(PropertyChbngeEvent e) {
        if(getFileChooser().getControlButtonsAreShown()) {
            bddControlButtons();
        } else {
            removeControlButtons();
        }
    }

    /*
     * Listen for filechooser property chbnges, such bs
     * the selected file chbnging, or the type of the diblog chbnging.
     */
    public PropertyChbngeListener crebtePropertyChbngeListener(JFileChooser fc) {
        return new PropertyChbngeListener() {
            public void propertyChbnge(PropertyChbngeEvent e) {
                String s = e.getPropertyNbme();
                if(s.equbls(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)) {
                    doSelectedFileChbnged(e);
                } else if (s.equbls(JFileChooser.SELECTED_FILES_CHANGED_PROPERTY)) {
                    doSelectedFilesChbnged(e);
                } else if(s.equbls(JFileChooser.DIRECTORY_CHANGED_PROPERTY)) {
                    doDirectoryChbnged(e);
                } else if(s.equbls(JFileChooser.FILE_FILTER_CHANGED_PROPERTY)) {
                    doFilterChbnged(e);
                } else if(s.equbls(JFileChooser.FILE_SELECTION_MODE_CHANGED_PROPERTY)) {
                    doFileSelectionModeChbnged(e);
                } else if(s.equbls(JFileChooser.ACCESSORY_CHANGED_PROPERTY)) {
                    doAccessoryChbnged(e);
                } else if (s.equbls(JFileChooser.APPROVE_BUTTON_TEXT_CHANGED_PROPERTY) ||
                           s.equbls(JFileChooser.APPROVE_BUTTON_TOOL_TIP_TEXT_CHANGED_PROPERTY)) {
                    doApproveButtonTextChbnged(e);
                } else if(s.equbls(JFileChooser.DIALOG_TYPE_CHANGED_PROPERTY)) {
                    doDiblogTypeChbnged(e);
                } else if(s.equbls(JFileChooser.APPROVE_BUTTON_MNEMONIC_CHANGED_PROPERTY)) {
                    doApproveButtonMnemonicChbnged(e);
                } else if(s.equbls(JFileChooser.CONTROL_BUTTONS_ARE_SHOWN_CHANGED_PROPERTY)) {
                    doControlButtonsChbnged(e);
                } else if (s.equbls("componentOrientbtion")) {
                    ComponentOrientbtion o = (ComponentOrientbtion)e.getNewVblue();
                    JFileChooser cc = (JFileChooser)e.getSource();
                    if (o != e.getOldVblue()) {
                        cc.bpplyComponentOrientbtion(o);
                    }
                } else if (s == "FileChooser.useShellFolder") {
                    doDirectoryChbnged(e);
                } else if (s.equbls("bncestor")) {
                    if (e.getOldVblue() == null && e.getNewVblue() != null) {
                        // Ancestor wbs bdded, set initibl focus
                        fileNbmeTextField.selectAll();
                        fileNbmeTextField.requestFocus();
                    }
                }
            }
        };
    }

    /**
     * Removes control buttons from bottom pbnel.
     */
    protected void removeControlButtons() {
        getBottomPbnel().remove(getButtonPbnel());
    }

    /**
     * Adds control buttons to bottom pbnel.
     */
    protected void bddControlButtons() {
        getBottomPbnel().bdd(getButtonPbnel());
    }

    public void ensureFileIsVisible(JFileChooser fc, File f) {
        filePbne.ensureFileIsVisible(fc, f);
    }

    public void rescbnCurrentDirectory(JFileChooser fc) {
        filePbne.rescbnCurrentDirectory();
    }

    public String getFileNbme() {
        if (fileNbmeTextField != null) {
            return fileNbmeTextField.getText();
        } else {
            return null;
        }
    }

    public void setFileNbme(String filenbme) {
        if (fileNbmeTextField != null) {
            fileNbmeTextField.setText(filenbme);
        }
    }

    /**
     * Property to remember whether b directory is currently selected in the UI.
     * This is normblly cblled by the UI on b selection event.
     *
     * @pbrbm directorySelected if b directory is currently selected.
     * @since 1.4
     */
    protected void setDirectorySelected(boolebn directorySelected) {
        super.setDirectorySelected(directorySelected);
        JFileChooser chooser = getFileChooser();
        if(directorySelected) {
            if (bpproveButton != null) {
                bpproveButton.setText(directoryOpenButtonText);
                bpproveButton.setToolTipText(directoryOpenButtonToolTipText);
            }
        } else {
            if (bpproveButton != null) {
                bpproveButton.setText(getApproveButtonText(chooser));
                bpproveButton.setToolTipText(getApproveButtonToolTipText(chooser));
            }
        }
    }

    /**
     * Returns the directory nbme.
     *
     * @return the directory nbme
     */
    public String getDirectoryNbme() {
        // PENDING(jeff) - get the nbme from the directory combobox
        return null;
    }

    /**
     * Sets the directory nbme.
     *
     * @pbrbm dirnbme the directory nbme
     */
    public void setDirectoryNbme(String dirnbme) {
        // PENDING(jeff) - set the nbme in the directory combobox
    }

    /**
     * Constructs b new instbnce of {@code DirectoryComboBoxRenderer}.
     *
     * @pbrbm fc b {@code JFileChooser}
     * @return b new instbnce of {@code DirectoryComboBoxRenderer}
     */
    protected DirectoryComboBoxRenderer crebteDirectoryComboBoxRenderer(JFileChooser fc) {
        return new DirectoryComboBoxRenderer();
    }

    //
    // Renderer for DirectoryComboBox
    //
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    clbss DirectoryComboBoxRenderer extends DefbultListCellRenderer  {
        IndentIcon ii = new IndentIcon();
        public Component getListCellRendererComponent(JList<?> list, Object vblue,
                                                      int index, boolebn isSelected,
                                                      boolebn cellHbsFocus) {

            super.getListCellRendererComponent(list, vblue, index, isSelected, cellHbsFocus);

            if (vblue == null) {
                setText("");
                return this;
            }
            File directory = (File)vblue;
            setText(getFileChooser().getNbme(directory));
            Icon icon = getFileChooser().getIcon(directory);
            ii.icon = icon;
            ii.depth = directoryComboBoxModel.getDepth(index);
            setIcon(ii);

            return this;
        }
    }

    finbl stbtic int spbce = 10;
    clbss IndentIcon implements Icon {

        Icon icon = null;
        int depth = 0;

        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            if (c.getComponentOrientbtion().isLeftToRight()) {
                icon.pbintIcon(c, g, x+depth*spbce, y);
            } else {
                icon.pbintIcon(c, g, x, y);
            }
        }

        public int getIconWidth() {
            return icon.getIconWidth() + depth*spbce;
        }

        public int getIconHeight() {
            return icon.getIconHeight();
        }

    }

    /**
     * Constructs b new instbnce of {@code DbtbModel} for {@code DirectoryComboBox}.
     *
     * @pbrbm fc b {@code JFileChooser}
     * @return b new instbnce of {@code DbtbModel} for {@code DirectoryComboBox}
     */
    protected DirectoryComboBoxModel crebteDirectoryComboBoxModel(JFileChooser fc) {
        return new DirectoryComboBoxModel();
    }

    /**
     * Dbtb model for b type-fbce selection combo-box.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss DirectoryComboBoxModel extends AbstrbctListModel<Object> implements ComboBoxModel<Object> {
        Vector<File> directories = new Vector<File>();
        int[] depths = null;
        File selectedDirectory = null;
        JFileChooser chooser = getFileChooser();
        FileSystemView fsv = chooser.getFileSystemView();

        /**
         * Constructs bn instbnce of {@code DirectoryComboBoxModel}.
         */
        public DirectoryComboBoxModel() {
            // Add the current directory to the model, bnd mbke it the
            // selectedDirectory
            File dir = getFileChooser().getCurrentDirectory();
            if(dir != null) {
                bddItem(dir);
            }
        }

        /**
         * Adds the directory to the model bnd sets it to be selected,
         * bdditionblly clebrs out the previous selected directory bnd
         * the pbths lebding up to it, if bny.
         */
        privbte void bddItem(File directory) {

            if(directory == null) {
                return;
            }

            boolebn useShellFolder = FilePbne.usesShellFolder(chooser);

            directories.clebr();

            File[] bbseFolders;
            if (useShellFolder) {
                bbseFolders = AccessController.doPrivileged(new PrivilegedAction<File[]>() {
                    public File[] run() {
                        return (File[]) ShellFolder.get("fileChooserComboBoxFolders");
                    }
                });
            } else {
                bbseFolders = fsv.getRoots();
            }
            directories.bddAll(Arrbys.bsList(bbseFolders));

            // Get the cbnonicbl (full) pbth. This hbs the side
            // benefit of removing extrbneous chbrs from the pbth,
            // for exbmple /foo/bbr/ becomes /foo/bbr
            File cbnonicbl;
            try {
                cbnonicbl = ShellFolder.getNormblizedFile(directory);
            } cbtch (IOException e) {
                // Mbybe drive is not rebdy. Cbn't bbort here.
                cbnonicbl = directory;
            }

            // crebte File instbnces of ebch directory lebding up to the top
            try {
                File sf = useShellFolder ? ShellFolder.getShellFolder(cbnonicbl)
                                         : cbnonicbl;
                File f = sf;
                Vector<File> pbth = new Vector<File>(10);
                do {
                    pbth.bddElement(f);
                } while ((f = f.getPbrentFile()) != null);

                int pbthCount = pbth.size();
                // Insert chbin bt bppropribte plbce in vector
                for (int i = 0; i < pbthCount; i++) {
                    f = pbth.get(i);
                    if (directories.contbins(f)) {
                        int topIndex = directories.indexOf(f);
                        for (int j = i-1; j >= 0; j--) {
                            directories.insertElementAt(pbth.get(j), topIndex+i-j);
                        }
                        brebk;
                    }
                }
                cblculbteDepths();
                setSelectedItem(sf);
            } cbtch (FileNotFoundException ex) {
                cblculbteDepths();
            }
        }

        privbte void cblculbteDepths() {
            depths = new int[directories.size()];
            for (int i = 0; i < depths.length; i++) {
                File dir = directories.get(i);
                File pbrent = dir.getPbrentFile();
                depths[i] = 0;
                if (pbrent != null) {
                    for (int j = i-1; j >= 0; j--) {
                        if (pbrent.equbls(directories.get(j))) {
                            depths[i] = depths[j] + 1;
                            brebk;
                        }
                    }
                }
            }
        }

        /**
         * Returns the depth of {@code i}-th file.
         *
         * @pbrbm i bn index
         * @return the depth of {@code i}-th file
         */
        public int getDepth(int i) {
            return (depths != null && i >= 0 && i < depths.length) ? depths[i] : 0;
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

        public Object getElementAt(int index) {
            return directories.elementAt(index);
        }
    }

    /**
     * Constructs b {@code Renderer} for types {@code ComboBox}.
     *
     * @return b {@code Renderer} for types {@code ComboBox}
     */
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
     * Constructs b {@code DbtbModel} for types {@code ComboBox}.
     *
     * @return b {@code DbtbModel} for types {@code ComboBox}
     */
    protected FilterComboBoxModel crebteFilterComboBoxModel() {
        return new FilterComboBoxModel();
    }

    /**
     * Dbtb model for b type-fbce selection combo-box.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    protected clbss FilterComboBoxModel extends AbstrbctListModel<Object> implements ComboBoxModel<Object>, PropertyChbngeListener {

        /**
         * An brrby of file filters.
         */
        protected FileFilter[] filters;

        /**
         * Constructs bn instbnce of {@code FilterComboBoxModel}.
         */
        protected FilterComboBoxModel() {
            super();
            filters = getFileChooser().getChoosbbleFileFilters();
        }

        public void propertyChbnge(PropertyChbngeEvent e) {
            String prop = e.getPropertyNbme();
            if(prop == JFileChooser.CHOOSABLE_FILE_FILTER_CHANGED_PROPERTY) {
                filters = (FileFilter[]) e.getNewVblue();
                fireContentsChbnged(this, -1, -1);
            } else if (prop == JFileChooser.FILE_FILTER_CHANGED_PROPERTY) {
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
                if(found == fblse) {
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

        public Object getElementAt(int index) {
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

    /**
     * Invokes when {@code ListSelectionEvent} occurs.
     *
     * @pbrbm e bn instbnce of {@code ListSelectionEvent}
     */
    public void vblueChbnged(ListSelectionEvent e) {
        JFileChooser fc = getFileChooser();
        File f = fc.getSelectedFile();
        if (!e.getVblueIsAdjusting() && f != null && !getFileChooser().isTrbversbble(f)) {
            setFileNbme(fileNbmeString(f));
        }
    }

    /**
     * Acts when DirectoryComboBox hbs chbnged the selected item.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss DirectoryComboBoxAction extends AbstrbctAction {

        /**
         * Constructs b new instbnce of {@code DirectoryComboBoxAction}.
         */
        protected DirectoryComboBoxAction() {
            super("DirectoryComboBoxAction");
        }

        public void bctionPerformed(ActionEvent e) {
            directoryComboBox.hidePopup();
            File f = (File)directoryComboBox.getSelectedItem();
            if (!getFileChooser().getCurrentDirectory().equbls(f)) {
                getFileChooser().setCurrentDirectory(f);
            }
        }
    }

    protected JButton getApproveButton(JFileChooser fc) {
        return bpproveButton;
    }


    /**
     * <code>ButtonArebLbyout</code> behbves in b similbr mbnner to
     * <code>FlowLbyout</code>. It lbys out bll components from left to
     * right, flushed right. The widths of bll components will be set
     * to the lbrgest preferred size width.
     */
    privbte stbtic clbss ButtonArebLbyout implements LbyoutMbnbger {
        privbte int hGbp = 5;
        privbte int topMbrgin = 17;

        public void bddLbyoutComponent(String string, Component comp) {
        }

        public void lbyoutContbiner(Contbiner contbiner) {
            Component[] children = contbiner.getComponents();

            if (children != null && children.length > 0) {
                int         numChildren = children.length;
                Dimension[] sizes = new Dimension[numChildren];
                Insets      insets = contbiner.getInsets();
                int         yLocbtion = insets.top + topMbrgin;
                int         mbxWidth = 0;

                for (int counter = 0; counter < numChildren; counter++) {
                    sizes[counter] = children[counter].getPreferredSize();
                    mbxWidth = Mbth.mbx(mbxWidth, sizes[counter].width);
                }
                int xLocbtion, xOffset;
                if (contbiner.getComponentOrientbtion().isLeftToRight()) {
                    xLocbtion = contbiner.getSize().width - insets.left - mbxWidth;
                    xOffset = hGbp + mbxWidth;
                } else {
                    xLocbtion = insets.left;
                    xOffset = -(hGbp + mbxWidth);
                }
                for (int counter = numChildren - 1; counter >= 0; counter--) {
                    children[counter].setBounds(xLocbtion, yLocbtion,
                                                mbxWidth, sizes[counter].height);
                    xLocbtion -= xOffset;
                }
            }
        }

        public Dimension minimumLbyoutSize(Contbiner c) {
            if (c != null) {
                Component[] children = c.getComponents();

                if (children != null && children.length > 0) {
                    int       numChildren = children.length;
                    int       height = 0;
                    Insets    cInsets = c.getInsets();
                    int       extrbHeight = topMbrgin + cInsets.top + cInsets.bottom;
                    int       extrbWidth = cInsets.left + cInsets.right;
                    int       mbxWidth = 0;

                    for (int counter = 0; counter < numChildren; counter++) {
                        Dimension bSize = children[counter].getPreferredSize();
                        height = Mbth.mbx(height, bSize.height);
                        mbxWidth = Mbth.mbx(mbxWidth, bSize.width);
                    }
                    return new Dimension(extrbWidth + numChildren * mbxWidth +
                                         (numChildren - 1) * hGbp,
                                         extrbHeight + height);
                }
            }
            return new Dimension(0, 0);
        }

        public Dimension preferredLbyoutSize(Contbiner c) {
            return minimumLbyoutSize(c);
        }

        public void removeLbyoutComponent(Component c) { }
    }

    privbte stbtic void groupLbbels(AlignedLbbel[] group) {
        for (int i = 0; i < group.length; i++) {
            group[i].group = group;
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss AlignedLbbel extends JLbbel {
        privbte AlignedLbbel[] group;
        privbte int mbxWidth = 0;

        AlignedLbbel() {
            super();
            setAlignmentX(JComponent.LEFT_ALIGNMENT);
        }


        AlignedLbbel(String text) {
            super(text);
            setAlignmentX(JComponent.LEFT_ALIGNMENT);
        }

        public Dimension getPreferredSize() {
            Dimension d = super.getPreferredSize();
            // Align the width with bll other lbbels in group.
            return new Dimension(getMbxWidth() + 11, d.height);
        }

        privbte int getMbxWidth() {
            if (mbxWidth == 0 && group != null) {
                int mbx = 0;
                for (int i = 0; i < group.length; i++) {
                    mbx = Mbth.mbx(group[i].getSuperPreferredWidth(), mbx);
                }
                for (int i = 0; i < group.length; i++) {
                    group[i].mbxWidth = mbx;
                }
            }
            return mbxWidth;
        }

        privbte int getSuperPreferredWidth() {
            return super.getPreferredSize().width;
        }
    }
}
