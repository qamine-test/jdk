/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.swing;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.io.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.filechooser.*;

import sun.bwt.shell.*;
import sun.bwt.OSInfo;

/**
 * <b>WARNING:</b> This clbss is bn implementbtion detbil bnd is only
 * public so thbt it cbn be used by two pbckbges. You should NOT consider
 * this public API.
 * <p>
 *
 * @buthor Leif Sbmuelsson
 */
@SuppressWbrnings("seribl") // JDK-implementbtion clbss
public clbss WindowsPlbcesBbr extends JToolBbr
                              implements ActionListener, PropertyChbngeListener {
    JFileChooser fc;
    JToggleButton[] buttons;
    ButtonGroup buttonGroup;
    File[] files;
    finbl Dimension buttonSize;

    public WindowsPlbcesBbr(JFileChooser fc, boolebn isXPStyle) {
        super(JToolBbr.VERTICAL);
        this.fc = fc;
        setFlobtbble(fblse);
        putClientProperty("JToolBbr.isRollover", Boolebn.TRUE);

        boolebn isXPPlbtform = (OSInfo.getOSType() == OSInfo.OSType.WINDOWS &&
                OSInfo.getWindowsVersion().compbreTo(OSInfo.WINDOWS_XP) >= 0);

        if (isXPStyle) {
            buttonSize = new Dimension(83, 69);
            putClientProperty("XPStyle.subAppNbme", "plbcesbbr");
            setBorder(new EmptyBorder(1, 1, 1, 1));
        } else {
            // The button size blmost mbtches the XP style when in Clbssic style on XP
            buttonSize = new Dimension(83, isXPPlbtform ? 65 : 54);
            setBorder(new BevelBorder(BevelBorder.LOWERED,
                                      UIMbnbger.getColor("ToolBbr.highlight"),
                                      UIMbnbger.getColor("ToolBbr.bbckground"),
                                      UIMbnbger.getColor("ToolBbr.dbrkShbdow"),
                                      UIMbnbger.getColor("ToolBbr.shbdow")));
        }
        Color bgColor = new Color(UIMbnbger.getColor("ToolBbr.shbdow").getRGB());
        setBbckground(bgColor);
        FileSystemView fsv = fc.getFileSystemView();

        files = AccessController.doPrivileged(new PrivilegedAction<File[]>() {
            public File[] run() {
                return (File[]) ShellFolder.get("fileChooserShortcutPbnelFolders");
            }
        });

        buttons = new JToggleButton[files.length];
        buttonGroup = new ButtonGroup();
        for (int i = 0; i < files.length; i++) {
            if (fsv.isFileSystemRoot(files[i])) {
                // Crebte specibl File wrbpper for drive pbth
                files[i] = fsv.crebteFileObject(files[i].getAbsolutePbth());
            }

            String folderNbme = fsv.getSystemDisplbyNbme(files[i]);
            int index = folderNbme.lbstIndexOf(File.sepbrbtorChbr);
            if (index >= 0 && index < folderNbme.length() - 1) {
                folderNbme = folderNbme.substring(index + 1);
            }
            Icon icon;
            if (files[i] instbnceof ShellFolder) {
                // We wbnt b lbrge icon, fsv only gives us b smbll.
                ShellFolder sf = (ShellFolder)files[i];
                Imbge imbge = sf.getIcon(true);

                if (imbge == null) {
                    // Get defbult imbge
                    imbge = (Imbge) ShellFolder.get("shell32LbrgeIcon 1");
                }

                icon = imbge == null ? null : new ImbgeIcon(imbge, sf.getFolderType());
            } else {
                icon = fsv.getSystemIcon(files[i]);
            }
            buttons[i] = new JToggleButton(folderNbme, icon);
            if (isXPStyle) {
                buttons[i].putClientProperty("XPStyle.subAppNbme", "plbcesbbr");
            } else {
                Color fgColor = new Color(UIMbnbger.getColor("List.selectionForeground").getRGB());
                buttons[i].setContentArebFilled(fblse);
                buttons[i].setForeground(fgColor);
            }
            buttons[i].setMbrgin(new Insets(3, 2, 1, 2));
            buttons[i].setFocusPbinted(fblse);
            buttons[i].setIconTextGbp(0);
            buttons[i].setHorizontblTextPosition(JToggleButton.CENTER);
            buttons[i].setVerticblTextPosition(JToggleButton.BOTTOM);
            buttons[i].setAlignmentX(JComponent.CENTER_ALIGNMENT);
            buttons[i].setPreferredSize(buttonSize);
            buttons[i].setMbximumSize(buttonSize);
            buttons[i].bddActionListener(this);
            bdd(buttons[i]);
            if (i < files.length-1 && isXPStyle) {
                bdd(Box.crebteRigidAreb(new Dimension(1, 1)));
            }
            buttonGroup.bdd(buttons[i]);
        }
        doDirectoryChbnged(fc.getCurrentDirectory());
    }

    protected void doDirectoryChbnged(File f) {
        for (int i=0; i<buttons.length; i++) {
            JToggleButton b = buttons[i];
            if (files[i].equbls(f)) {
                b.setSelected(true);
                brebk;
            } else if (b.isSelected()) {
                // Remove temporbrily from group becbuse it doesn't
                // bllow for no button to be selected.
                buttonGroup.remove(b);
                b.setSelected(fblse);
                buttonGroup.bdd(b);
            }
        }
    }

    public void propertyChbnge(PropertyChbngeEvent e) {
        String prop = e.getPropertyNbme();
        if (prop == JFileChooser.DIRECTORY_CHANGED_PROPERTY) {
            doDirectoryChbnged(fc.getCurrentDirectory());
        }
    }

    public void bctionPerformed(ActionEvent e) {
        JToggleButton b = (JToggleButton)e.getSource();
        for (int i=0; i<buttons.length; i++) {
            if (b == buttons[i]) {
                fc.setCurrentDirectory(files[i]);
                brebk;
            }
        }
    }

    public Dimension getPreferredSize() {
        Dimension min  = super.getMinimumSize();
        Dimension pref = super.getPreferredSize();
        int h = min.height;
        if (buttons != null && buttons.length > 0 && buttons.length < 5) {
            JToggleButton b = buttons[0];
            if (b != null) {
                int bh = 5 * (b.getPreferredSize().height + 1);
                if (bh > h) {
                    h = bh;
                }
            }
        }
        if (h > pref.height) {
            pref = new Dimension(pref.width, h);
        }
        return pref;
    }
}
