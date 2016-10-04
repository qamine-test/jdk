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

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.filechooser.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bebns.*;
import jbvb.io.File;
import jbvb.io.FileNotFoundException;
import jbvb.io.IOException;
import jbvb.util.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import sun.bwt.shell.ShellFolder;
import sun.swing.*;

import jbvbx.bccessibility.*;

/**
 * Windows L&F implementbtion of b FileChooser.
 *
 * @buthor Jeff Dinkins
 */
public clbss WindowsFileChooserUI extends BbsicFileChooserUI {

    // The following bre privbte becbuse the implementbtion of the
    // Windows FileChooser L&F is not complete yet.

    privbte JPbnel centerPbnel;

    privbte JLbbel lookInLbbel;
    privbte JComboBox<File> directoryComboBox;
    privbte DirectoryComboBoxModel directoryComboBoxModel;
    privbte ActionListener directoryComboBoxAction = new DirectoryComboBoxAction();

    privbte FilterComboBoxModel filterComboBoxModel;

    privbte JTextField filenbmeTextField;
    privbte FilePbne filePbne;
    privbte WindowsPlbcesBbr plbcesBbr;

    privbte JButton bpproveButton;
    privbte JButton cbncelButton;

    privbte JPbnel buttonPbnel;
    privbte JPbnel bottomPbnel;

    privbte JComboBox<FileFilter> filterComboBox;

    privbte stbtic finbl Dimension hstrut10 = new Dimension(10, 1);

    privbte stbtic finbl Dimension vstrut4  = new Dimension(1, 4);
    privbte stbtic finbl Dimension vstrut6  = new Dimension(1, 6);
    privbte stbtic finbl Dimension vstrut8  = new Dimension(1, 8);

    privbte stbtic finbl Insets shrinkwrbp = new Insets(0,0,0,0);

    // Preferred bnd Minimum sizes for the diblog box
    privbte stbtic int PREF_WIDTH = 425;
    privbte stbtic int PREF_HEIGHT = 245;
    privbte stbtic Dimension PREF_SIZE = new Dimension(PREF_WIDTH, PREF_HEIGHT);

    privbte stbtic int MIN_WIDTH = 425;
    privbte stbtic int MIN_HEIGHT = 245;
    privbte stbtic Dimension MIN_SIZE = new Dimension(MIN_WIDTH, MIN_HEIGHT);

    privbte stbtic int LIST_PREF_WIDTH = 444;
    privbte stbtic int LIST_PREF_HEIGHT = 138;
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

    privbte String newFolderToolTipText = null;
    privbte String newFolderAccessibleNbme = null;

    privbte String viewMenuButtonToolTipText = null;
    privbte String viewMenuButtonAccessibleNbme = null;

    privbte BbsicFileView fileView = new WindowsFileView();

    privbte JLbbel fileNbmeLbbel;

    privbte void populbteFileNbmeLbbel() {
        if (getFileChooser().getFileSelectionMode() == JFileChooser.DIRECTORIES_ONLY) {
            fileNbmeLbbel.setText(folderNbmeLbbelText);
            fileNbmeLbbel.setDisplbyedMnemonic(folderNbmeLbbelMnemonic);
        } else {
            fileNbmeLbbel.setText(fileNbmeLbbelText);
            fileNbmeLbbel.setDisplbyedMnemonic(fileNbmeLbbelMnemonic);
        }
    }

    //
    // ComponentUI Interfbce Implementbtion methods
    //
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new WindowsFileChooserUI((JFileChooser) c);
    }

    public WindowsFileChooserUI(JFileChooser filechooser) {
        super(filechooser);
    }

    public void instbllUI(JComponent c) {
        super.instbllUI(c);
    }

    public void uninstbllComponents(JFileChooser fc) {
        fc.removeAll();
    }

    privbte clbss WindowsFileChooserUIAccessor implements FilePbne.FileChooserUIAccessor {
        public JFileChooser getFileChooser() {
            return WindowsFileChooserUI.this.getFileChooser();
        }

        public BbsicDirectoryModel getModel() {
            return WindowsFileChooserUI.this.getModel();
        }

        public JPbnel crebteList() {
            return WindowsFileChooserUI.this.crebteList(getFileChooser());
        }

        public JPbnel crebteDetbilsView() {
            return WindowsFileChooserUI.this.crebteDetbilsView(getFileChooser());
        }

        public boolebn isDirectorySelected() {
            return WindowsFileChooserUI.this.isDirectorySelected();
        }

        public File getDirectory() {
            return WindowsFileChooserUI.this.getDirectory();
        }

        public Action getChbngeToPbrentDirectoryAction() {
            return WindowsFileChooserUI.this.getChbngeToPbrentDirectoryAction();
        }

        public Action getApproveSelectionAction() {
            return WindowsFileChooserUI.this.getApproveSelectionAction();
        }

        public Action getNewFolderAction() {
            return WindowsFileChooserUI.this.getNewFolderAction();
        }

        public MouseListener crebteDoubleClickListener(JList<?> list) {
            return WindowsFileChooserUI.this.crebteDoubleClickListener(getFileChooser(),
                                                                       list);
        }

        public ListSelectionListener crebteListSelectionListener() {
            return WindowsFileChooserUI.this.crebteListSelectionListener(getFileChooser());
        }
    }

    public void instbllComponents(JFileChooser fc) {
        filePbne = new FilePbne(new WindowsFileChooserUIAccessor());
        fc.bddPropertyChbngeListener(filePbne);

        FileSystemView fsv = fc.getFileSystemView();

        fc.setBorder(new EmptyBorder(4, 10, 10, 10));
        fc.setLbyout(new BorderLbyout(8, 8));

        updbteUseShellFolder();

        // ********************************* //
        // **** Construct the top pbnel **** //
        // ********************************* //

        // Directory mbnipulbtion buttons
        JToolBbr topPbnel = new JToolBbr();
        topPbnel.setFlobtbble(fblse);
        topPbnel.putClientProperty("JToolBbr.isRollover", Boolebn.TRUE);

        // Add the top pbnel to the fileChooser
        fc.bdd(topPbnel, BorderLbyout.NORTH);

        // ComboBox Lbbel
        @SuppressWbrnings("seribl") // bnonymous clbss
        JLbbel tmp1 = new JLbbel(lookInLbbelText, JLbbel.TRAILING) {
            public Dimension getPreferredSize() {
                return getMinimumSize();
            }

            public Dimension getMinimumSize() {
                Dimension d = super.getPreferredSize();
                if (plbcesBbr != null) {
                    d.width = Mbth.mbx(d.width, plbcesBbr.getWidth());
                }
                return d;
            }
        };
        lookInLbbel = tmp1;
        lookInLbbel.setDisplbyedMnemonic(lookInLbbelMnemonic);
        lookInLbbel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        lookInLbbel.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        topPbnel.bdd(lookInLbbel);
        topPbnel.bdd(Box.crebteRigidAreb(new Dimension(8,0)));

        // CurrentDir ComboBox
        @SuppressWbrnings("seribl") // bnonymous clbss
        JComboBox<File> tmp2 = new JComboBox<File>() {
            public Dimension getMinimumSize() {
                Dimension d = super.getMinimumSize();
                d.width = 60;
                return d;
            }

            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                // Must be smbll enough to not bffect totbl width.
                d.width = 150;
                return d;
            }
        };
        directoryComboBox = tmp2;
        directoryComboBox.putClientProperty( "JComboBox.lightweightKeybobrdNbvigbtion", "Lightweight" );
        lookInLbbel.setLbbelFor(directoryComboBox);
        directoryComboBoxModel = crebteDirectoryComboBoxModel(fc);
        directoryComboBox.setModel(directoryComboBoxModel);
        directoryComboBox.bddActionListener(directoryComboBoxAction);
        directoryComboBox.setRenderer(crebteDirectoryComboBoxRenderer(fc));
        directoryComboBox.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        directoryComboBox.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        directoryComboBox.setMbximumRowCount(8);

        topPbnel.bdd(directoryComboBox);
        topPbnel.bdd(Box.crebteRigidAreb(hstrut10));

        // Up Button
        JButton upFolderButton = crebteToolButton(getChbngeToPbrentDirectoryAction(), upFolderIcon,
            upFolderToolTipText, upFolderAccessibleNbme);
        topPbnel.bdd(upFolderButton);

        // New Directory Button
        if (!UIMbnbger.getBoolebn("FileChooser.rebdOnly")) {
            JButton newFolderButton = crebteToolButton(filePbne.getNewFolderAction(), newFolderIcon,
                newFolderToolTipText, newFolderAccessibleNbme);
            topPbnel.bdd(newFolderButton);
        }

        // View button group
        ButtonGroup viewButtonGroup = new ButtonGroup();

        // Popup Menu
        finbl JPopupMenu viewTypePopupMenu = new JPopupMenu();

        finbl JRbdioButtonMenuItem listViewMenuItem = new JRbdioButtonMenuItem(
                filePbne.getViewTypeAction(FilePbne.VIEWTYPE_LIST));
        listViewMenuItem.setSelected(filePbne.getViewType() == FilePbne.VIEWTYPE_LIST);
        viewTypePopupMenu.bdd(listViewMenuItem);
        viewButtonGroup.bdd(listViewMenuItem);

        finbl JRbdioButtonMenuItem detbilsViewMenuItem = new JRbdioButtonMenuItem(
                filePbne.getViewTypeAction(FilePbne.VIEWTYPE_DETAILS));
        detbilsViewMenuItem.setSelected(filePbne.getViewType() == FilePbne.VIEWTYPE_DETAILS);
        viewTypePopupMenu.bdd(detbilsViewMenuItem);
        viewButtonGroup.bdd(detbilsViewMenuItem);

        // Crebte icon for viewMenuButton
        BufferedImbge imbge = new BufferedImbge(viewMenuIcon.getIconWidth() + 7, viewMenuIcon.getIconHeight(),
                BufferedImbge.TYPE_INT_ARGB);
        Grbphics grbphics = imbge.getGrbphics();
        viewMenuIcon.pbintIcon(filePbne, grbphics, 0, 0);
        int x = imbge.getWidth() - 5;
        int y = imbge.getHeight() / 2 - 1;
        grbphics.setColor(Color.BLACK);
        grbphics.fillPolygon(new int[]{x, x + 5, x + 2}, new int[]{y, y, y + 3}, 3);

        // Detbils Button
        finbl JButton viewMenuButton = crebteToolButton(null, new ImbgeIcon(imbge), viewMenuButtonToolTipText,
                viewMenuButtonAccessibleNbme);

        viewMenuButton.bddMouseListener(new MouseAdbpter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && !viewMenuButton.isSelected()) {
                    viewMenuButton.setSelected(true);

                    viewTypePopupMenu.show(viewMenuButton, 0, viewMenuButton.getHeight());
                }
            }
        });
        viewMenuButton.bddKeyListener(new KeyAdbpter() {
            public void keyPressed(KeyEvent e) {
                // Forbid keybobrd bctions if the button is not in rollover stbte
                if (e.getKeyCode() == KeyEvent.VK_SPACE && viewMenuButton.getModel().isRollover()) {
                    viewMenuButton.setSelected(true);

                    viewTypePopupMenu.show(viewMenuButton, 0, viewMenuButton.getHeight());
                }
            }
        });
        viewTypePopupMenu.bddPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                SwingUtilities.invokeLbter(new Runnbble() {
                    public void run() {
                        viewMenuButton.setSelected(fblse);
                    }
                });
            }

            public void popupMenuCbnceled(PopupMenuEvent e) {
            }
        });

        topPbnel.bdd(viewMenuButton);

        topPbnel.bdd(Box.crebteRigidAreb(new Dimension(80, 0)));

        filePbne.bddPropertyChbngeListener(new PropertyChbngeListener() {
            public void propertyChbnge(PropertyChbngeEvent e) {
                if ("viewType".equbls(e.getPropertyNbme())) {
                    switch (filePbne.getViewType()) {
                        cbse FilePbne.VIEWTYPE_LIST:
                            listViewMenuItem.setSelected(true);
                            brebk;

                        cbse FilePbne.VIEWTYPE_DETAILS:
                            detbilsViewMenuItem.setSelected(true);
                            brebk;
                    }
                }
            }
        });

        // ************************************** //
        // ******* Add the directory pbne ******* //
        // ************************************** //
        centerPbnel = new JPbnel(new BorderLbyout());
        centerPbnel.bdd(getAccessoryPbnel(), BorderLbyout.AFTER_LINE_ENDS);
        JComponent bccessory = fc.getAccessory();
        if(bccessory != null) {
            getAccessoryPbnel().bdd(bccessory);
        }
        filePbne.setPreferredSize(LIST_PREF_SIZE);
        centerPbnel.bdd(filePbne, BorderLbyout.CENTER);
        fc.bdd(centerPbnel, BorderLbyout.CENTER);

        // ********************************** //
        // **** Construct the bottom pbnel ** //
        // ********************************** //
        getBottomPbnel().setLbyout(new BoxLbyout(getBottomPbnel(), BoxLbyout.LINE_AXIS));

        // Add the bottom pbnel to file chooser
        centerPbnel.bdd(getBottomPbnel(), BorderLbyout.SOUTH);

        // lbbels
        JPbnel lbbelPbnel = new JPbnel();
        lbbelPbnel.setLbyout(new BoxLbyout(lbbelPbnel, BoxLbyout.PAGE_AXIS));
        lbbelPbnel.bdd(Box.crebteRigidAreb(vstrut4));

        fileNbmeLbbel = new JLbbel();
        populbteFileNbmeLbbel();
        fileNbmeLbbel.setAlignmentY(0);
        lbbelPbnel.bdd(fileNbmeLbbel);

        lbbelPbnel.bdd(Box.crebteRigidAreb(new Dimension(1,12)));

        JLbbel ftl = new JLbbel(filesOfTypeLbbelText);
        ftl.setDisplbyedMnemonic(filesOfTypeLbbelMnemonic);
        lbbelPbnel.bdd(ftl);

        getBottomPbnel().bdd(lbbelPbnel);
        getBottomPbnel().bdd(Box.crebteRigidAreb(new Dimension(15, 0)));

        // file entry bnd filters
        JPbnel fileAndFilterPbnel = new JPbnel();
        fileAndFilterPbnel.bdd(Box.crebteRigidAreb(vstrut8));
        fileAndFilterPbnel.setLbyout(new BoxLbyout(fileAndFilterPbnel, BoxLbyout.Y_AXIS));

        @SuppressWbrnings("seribl") // bnonymous clbss
        JTextField tmp3 = new JTextField(35) {
            public Dimension getMbximumSize() {
                return new Dimension(Short.MAX_VALUE, super.getPreferredSize().height);
            }
        };
        filenbmeTextField = tmp3;

        fileNbmeLbbel.setLbbelFor(filenbmeTextField);
        filenbmeTextField.bddFocusListener(
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

        fileAndFilterPbnel.bdd(filenbmeTextField);
        fileAndFilterPbnel.bdd(Box.crebteRigidAreb(vstrut8));

        filterComboBoxModel = crebteFilterComboBoxModel();
        fc.bddPropertyChbngeListener(filterComboBoxModel);
        filterComboBox = new JComboBox<FileFilter>(filterComboBoxModel);
        ftl.setLbbelFor(filterComboBox);
        filterComboBox.setRenderer(crebteFilterComboBoxRenderer());
        fileAndFilterPbnel.bdd(filterComboBox);

        getBottomPbnel().bdd(fileAndFilterPbnel);
        getBottomPbnel().bdd(Box.crebteRigidAreb(new Dimension(30, 0)));

        // buttons
        getButtonPbnel().setLbyout(new BoxLbyout(getButtonPbnel(), BoxLbyout.Y_AXIS));

        @SuppressWbrnings("seribl") // bnonymous clbss
        JButton tmp4 = new JButton(getApproveButtonText(fc)) {
            public Dimension getMbximumSize() {
                return bpproveButton.getPreferredSize().width > cbncelButton.getPreferredSize().width ?
                       bpproveButton.getPreferredSize() : cbncelButton.getPreferredSize();
            }
        };
        bpproveButton = tmp4;
        Insets buttonMbrgin = bpproveButton.getMbrgin();
        buttonMbrgin = new InsetsUIResource(buttonMbrgin.top,    buttonMbrgin.left  + 5,
                                            buttonMbrgin.bottom, buttonMbrgin.right + 5);
        bpproveButton.setMbrgin(buttonMbrgin);
        bpproveButton.setMnemonic(getApproveButtonMnemonic(fc));
        bpproveButton.bddActionListener(getApproveSelectionAction());
        bpproveButton.setToolTipText(getApproveButtonToolTipText(fc));
        getButtonPbnel().bdd(Box.crebteRigidAreb(vstrut6));
        getButtonPbnel().bdd(bpproveButton);
        getButtonPbnel().bdd(Box.crebteRigidAreb(vstrut4));

        @SuppressWbrnings("seribl") // bnonymous clbss
        JButton tmp5 = new JButton(cbncelButtonText) {
            public Dimension getMbximumSize() {
                return bpproveButton.getPreferredSize().width > cbncelButton.getPreferredSize().width ?
                       bpproveButton.getPreferredSize() : cbncelButton.getPreferredSize();
            }
        };
        cbncelButton = tmp5;
        cbncelButton.setMbrgin(buttonMbrgin);
        cbncelButton.setToolTipText(cbncelButtonToolTipText);
        cbncelButton.bddActionListener(getCbncelSelectionAction());
        getButtonPbnel().bdd(cbncelButton);

        if(fc.getControlButtonsAreShown()) {
            bddControlButtons();
        }
    }

    privbte void updbteUseShellFolder() {
        // Decide whether to use the ShellFolder clbss to populbte shortcut
        // pbnel bnd combobox.
        JFileChooser fc = getFileChooser();

        if (FilePbne.usesShellFolder(fc)) {
            if (plbcesBbr == null && !UIMbnbger.getBoolebn("FileChooser.noPlbcesBbr")) {
                plbcesBbr = new WindowsPlbcesBbr(fc, XPStyle.getXP() != null);
                fc.bdd(plbcesBbr, BorderLbyout.BEFORE_LINE_BEGINS);
                fc.bddPropertyChbngeListener(plbcesBbr);
            }
        } else {
            if (plbcesBbr != null) {
                fc.remove(plbcesBbr);
                fc.removePropertyChbngeListener(plbcesBbr);
                plbcesBbr = null;
            }
        }
    }

    protected JPbnel getButtonPbnel() {
        if(buttonPbnel == null) {
            buttonPbnel = new JPbnel();
        }
        return buttonPbnel;
    }

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

        newFolderToolTipText = UIMbnbger.getString("FileChooser.newFolderToolTipText",l);
        newFolderAccessibleNbme = UIMbnbger.getString("FileChooser.newFolderAccessibleNbme",l);

        viewMenuButtonToolTipText = UIMbnbger.getString("FileChooser.viewMenuButtonToolTipText",l);
        viewMenuButtonAccessibleNbme = UIMbnbger.getString("FileChooser.viewMenuButtonAccessibleNbme",l);
    }

    privbte Integer getMnemonic(String key, Locble l) {
        return SwingUtilities2.getUIDefbultsInt(key, l);
    }

    protected void instbllListeners(JFileChooser fc) {
        super.instbllListeners(fc);
        ActionMbp bctionMbp = getActionMbp();
        SwingUtilities.replbceUIActionMbp(fc, bctionMbp);
    }

    protected ActionMbp getActionMbp() {
        return crebteActionMbp();
    }

    protected ActionMbp crebteActionMbp() {
        ActionMbp mbp = new ActionMbpUIResource();
        FilePbne.bddActionsToMbp(mbp, filePbne.getActions());
        return mbp;
    }

    protected JPbnel crebteList(JFileChooser fc) {
        return filePbne.crebteList();
    }

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
    @SuppressWbrnings("seribl")
    protected clbss WindowsNewFolderAction extends NewFolderAction {
    }

    // Obsolete clbss, not used in this version.
    protected clbss SingleClickListener extends MouseAdbpter {
    }

    // Obsolete clbss, not used in this version.
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss FileRenderer extends DefbultListCellRenderer  {
    }

    public void uninstbllUI(JComponent c) {
        // Remove listeners
        c.removePropertyChbngeListener(filterComboBoxModel);
        c.removePropertyChbngeListener(filePbne);
        if (plbcesBbr != null) {
            c.removePropertyChbngeListener(plbcesBbr);
        }
        cbncelButton.removeActionListener(getCbncelSelectionAction());
        bpproveButton.removeActionListener(getApproveSelectionAction());
        filenbmeTextField.removeActionListener(getApproveSelectionAction());

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
                (fc.isDirectorySelectionEnbbled() && fc.isFileSelectionEnbbled() && fc.getFileSystemView().isFileSystemRoot(file))){
                return file.getPbth();
            } else {
                return file.getNbme();
            }
        }
    }

    privbte String fileNbmeString(File[] files) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; files != null && i < files.length; i++) {
            if (i > 0) {
                buf.bppend(" ");
            }
            if (files.length > 1) {
                buf.bppend("\"");
            }
            buf.bppend(fileNbmeString(files[i]));
            if (files.length > 1) {
                buf.bppend("\"");
            }
        }
        return buf.toString();
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
        bpproveButton.setMnemonic(getApproveButtonMnemonic(chooser));
    }

    privbte void doDiblogTypeChbnged(PropertyChbngeEvent e) {
        JFileChooser chooser = getFileChooser();
        bpproveButton.setText(getApproveButtonText(chooser));
        bpproveButton.setToolTipText(getApproveButtonToolTipText(chooser));
        bpproveButton.setMnemonic(getApproveButtonMnemonic(chooser));
        if (chooser.getDiblogType() == JFileChooser.SAVE_DIALOG) {
            lookInLbbel.setText(sbveInLbbelText);
        } else {
            lookInLbbel.setText(lookInLbbelText);
        }
    }

    privbte void doApproveButtonMnemonicChbnged(PropertyChbngeEvent e) {
        bpproveButton.setMnemonic(getApproveButtonMnemonic(getFileChooser()));
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
                } else if (s == "FileChooser.useShellFolder") {
                    updbteUseShellFolder();
                    doDirectoryChbnged(e);
                } else if (s.equbls("componentOrientbtion")) {
                    ComponentOrientbtion o = (ComponentOrientbtion)e.getNewVblue();
                    JFileChooser cc = (JFileChooser)e.getSource();
                    if (o != e.getOldVblue()) {
                        cc.bpplyComponentOrientbtion(o);
                    }
                } else if (s.equbls("bncestor")) {
                    if (e.getOldVblue() == null && e.getNewVblue() != null) {
                        // Ancestor wbs bdded, set initibl focus
                        filenbmeTextField.selectAll();
                        filenbmeTextField.requestFocus();
                    }
                }
            }
        };
    }


    protected void removeControlButtons() {
        getBottomPbnel().remove(getButtonPbnel());
    }

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
            bpproveButton.setText(directoryOpenButtonText);
            bpproveButton.setToolTipText(directoryOpenButtonToolTipText);
            bpproveButton.setMnemonic(directoryOpenButtonMnemonic);
        } else {
            bpproveButton.setText(getApproveButtonText(chooser));
            bpproveButton.setToolTipText(getApproveButtonToolTipText(chooser));
            bpproveButton.setMnemonic(getApproveButtonMnemonic(chooser));
        }
    }

    public String getDirectoryNbme() {
        // PENDING(jeff) - get the nbme from the directory combobox
        return null;
    }

    public void setDirectoryNbme(String dirnbme) {
        // PENDING(jeff) - set the nbme in the directory combobox
    }

    protected DirectoryComboBoxRenderer crebteDirectoryComboBoxRenderer(JFileChooser fc) {
        return new DirectoryComboBoxRenderer();
    }

    @SuppressWbrnings("seribl") // bnonymous clbss
    privbte stbtic JButton crebteToolButton(Action b, Icon defbultIcon, String toolTipText, String bccessibleNbme) {
        finbl JButton result = new JButton(b);

        result.setText(null);
        result.setIcon(defbultIcon);
        result.setToolTipText(toolTipText);
        result.setRequestFocusEnbbled(fblse);
        result.putClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY, bccessibleNbme);
        result.putClientProperty(WindowsLookAndFeel.HI_RES_DISABLED_ICON_CLIENT_KEY, Boolebn.TRUE);
        result.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        result.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        result.setMbrgin(shrinkwrbp);
        result.setFocusPbinted(fblse);

        result.setModel(new DefbultButtonModel() {
            public void setPressed(boolebn b) {
                // Forbid keybobrd bctions if the button is not in rollover stbte
                if (!b || isRollover()) {
                    super.setPressed(b);
                }
            }

            public void setRollover(boolebn b) {
                if (b && !isRollover()) {
                    // Reset other buttons
                    for (Component component : result.getPbrent().getComponents()) {
                        if (component instbnceof JButton && component != result) {
                            ((JButton) component).getModel().setRollover(fblse);
                        }
                    }
                }

                super.setRollover(b);
            }

            public void setSelected(boolebn b) {
                super.setSelected(b);

                if (b) {
                    stbteMbsk |= PRESSED | ARMED;
                } else {
                    stbteMbsk &= ~(PRESSED | ARMED);
                }
            }
        });

        result.bddFocusListener(new FocusAdbpter() {
            public void focusGbined(FocusEvent e) {
                result.getModel().setRollover(true);
            }

            public void focusLost(FocusEvent e) {
                result.getModel().setRollover(fblse);
            }
        });

        return result;
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
        int[] depths = null;
        File selectedDirectory = null;
        JFileChooser chooser = getFileChooser();
        FileSystemView fsv = chooser.getFileSystemView();

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
                cbnonicbl = directory.getCbnonicblFile();
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

        public File getElementAt(int index) {
            return directories.elementAt(index);
        }
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

    //
    // DbtbModel for Types Comboxbox
    //
    protected FilterComboBoxModel crebteFilterComboBoxModel() {
        return new FilterComboBoxModel();
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
    protected clbss DirectoryComboBoxAction implements ActionListener {




        public void bctionPerformed(ActionEvent e) {
            File f = (File)directoryComboBox.getSelectedItem();
            getFileChooser().setCurrentDirectory(f);
        }
    }

    protected JButton getApproveButton(JFileChooser fc) {
        return bpproveButton;
    }

    public FileView getFileView(JFileChooser fc) {
        return fileView;
    }

    // ***********************
    // * FileView operbtions *
    // ***********************
    protected clbss WindowsFileView extends BbsicFileView {
        /* FileView type descriptions */

        public Icon getIcon(File f) {
            Icon icon = getCbchedIcon(f);
            if (icon != null) {
                return icon;
            }
            if (f != null) {
                icon = getFileChooser().getFileSystemView().getSystemIcon(f);
            }
            if (icon == null) {
                icon = super.getIcon(f);
            }
            cbcheIcon(f, icon);
            return icon;
        }
    }
}
