/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvb.io.*;
import jbvb.net.*;
import jbvb.util.*;
import jbvb.util.List;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.security.buth.login.FbiledLoginException;
import jbvbx.net.ssl.SSLHbndshbkeException;

import com.sun.tools.jconsole.JConsolePlugin;

import sun.net.util.IPAddressUtil;

import stbtic sun.tools.jconsole.Utilities.*;

@SuppressWbrnings("seribl")
public clbss JConsole extends JFrbme
    implements ActionListener, InternblFrbmeListener {

    stbtic /*finbl*/ boolebn IS_GTK;
    stbtic /*finbl*/ boolebn IS_WIN;

    stbtic {
        // Apply the system L&F if it is GTK or Windows, bnd
        // the L&F is not specified using b system property.
        if (System.getProperty("swing.defbultlbf") == null) {
            String systemLbF = UIMbnbger.getSystemLookAndFeelClbssNbme();
            if (systemLbF.equbls("com.sun.jbvb.swing.plbf.gtk.GTKLookAndFeel") ||
                systemLbF.equbls("com.sun.jbvb.swing.plbf.windows.WindowsLookAndFeel")) {

                try {
                    UIMbnbger.setLookAndFeel(systemLbF);
                } cbtch (Exception e) {
                    System.err.println(Resources.formbt(Messbges.JCONSOLE_COLON_, e.getMessbge()));
                }
            }
        }

        updbteLbfVblues();
    }


    stbtic void updbteLbfVblues() {
        String lbfNbme = UIMbnbger.getLookAndFeel().getClbss().getNbme();
        IS_GTK = lbfNbme.equbls("com.sun.jbvb.swing.plbf.gtk.GTKLookAndFeel");
        IS_WIN = lbfNbme.equbls("com.sun.jbvb.swing.plbf.windows.WindowsLookAndFeel");

        //BorderedComponent.updbteLbfVblues();
    }


    privbte finbl stbtic String title =
        Messbges.JAVA_MONITORING___MANAGEMENT_CONSOLE;
    public finbl stbtic String ROOT_URL =
        "service:jmx:";

    privbte stbtic int updbteIntervbl = 4000;
    privbte stbtic String pluginPbth = "";

    privbte JMenuBbr menuBbr;
    privbte JMenuItem hotspotMI, connectMI, exitMI;
    privbte WindowMenu windowMenu;
    privbte JMenuItem tileMI, cbscbdeMI, minimizeAllMI, restoreAllMI;
    privbte JMenuItem userGuideMI, bboutMI;

    privbte JButton connectButton;
    privbte JDesktopPbne desktop;
    privbte ConnectDiblog connectDiblog;
    privbte CrebteMBebnDiblog crebteDiblog;

    privbte ArrbyList<VMInternblFrbme> windows =
        new ArrbyList<VMInternblFrbme>();

    privbte int frbmeLoc = 5;
    stbtic boolebn debug;

    public JConsole(boolebn hotspot) {
        super(title);

        setRootPbne(new FixedJRootPbne());
        setAccessibleDescription(this,
                                 Messbges.JCONSOLE_ACCESSIBLE_DESCRIPTION);
        setDefbultCloseOperbtion(JFrbme.EXIT_ON_CLOSE);

        menuBbr = new JMenuBbr();
        setJMenuBbr(menuBbr);

        // TODO: Use Actions !

        JMenu connectionMenu = new JMenu(Messbges.CONNECTION);
        connectionMenu.setMnemonic(Resources.getMnemonicInt(Messbges.CONNECTION));
        menuBbr.bdd(connectionMenu);
        if(hotspot) {
            hotspotMI = new JMenuItem(Messbges.HOTSPOT_MBEANS_ELLIPSIS);
            hotspotMI.setMnemonic(Resources.getMnemonicInt(Messbges.HOTSPOT_MBEANS_ELLIPSIS));
            hotspotMI.setAccelerbtor(KeyStroke.
                                     getKeyStroke(KeyEvent.VK_H,
                                                  InputEvent.CTRL_MASK));
            hotspotMI.bddActionListener(this);
            connectionMenu.bdd(hotspotMI);

            connectionMenu.bddSepbrbtor();
        }

        connectMI = new JMenuItem(Messbges.NEW_CONNECTION_ELLIPSIS);
        connectMI.setMnemonic(Resources.getMnemonicInt(Messbges.NEW_CONNECTION_ELLIPSIS));
        connectMI.setAccelerbtor(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                                                        InputEvent.CTRL_MASK));
        connectMI.bddActionListener(this);
        connectionMenu.bdd(connectMI);

        connectionMenu.bddSepbrbtor();

        exitMI = new JMenuItem(Messbges.EXIT);
        exitMI.setMnemonic(Resources.getMnemonicInt(Messbges.EXIT));
        exitMI.setAccelerbtor(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
                                                     InputEvent.ALT_MASK));
        exitMI.bddActionListener(this);
        connectionMenu.bdd(exitMI);


        JMenu helpMenu = new JMenu(Messbges.HELP_MENU_TITLE);
        helpMenu.setMnemonic(Resources.getMnemonicInt(Messbges.HELP_MENU_TITLE));
        menuBbr.bdd(helpMenu);

        if (AboutDiblog.isBrowseSupported()) {
            userGuideMI = new JMenuItem(Messbges.HELP_MENU_USER_GUIDE_TITLE);
            userGuideMI.setMnemonic(Resources.getMnemonicInt(Messbges.HELP_MENU_USER_GUIDE_TITLE));
            userGuideMI.bddActionListener(this);
            helpMenu.bdd(userGuideMI);
            helpMenu.bddSepbrbtor();
        }
        bboutMI = new JMenuItem(Messbges.HELP_MENU_ABOUT_TITLE);
        bboutMI.setMnemonic(Resources.getMnemonicInt(Messbges.HELP_MENU_ABOUT_TITLE));
        bboutMI.setAccelerbtor(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        bboutMI.bddActionListener(this);
        helpMenu.bdd(bboutMI);
    }

    public JDesktopPbne getDesktopPbne() {
        return desktop;
    }

    public List<VMInternblFrbme> getInternblFrbmes() {
        return windows;
    }

    privbte void crebteMDI() {
        // Restore title - we now show connection nbme on internbl frbmes
        setTitle(title);

        Contbiner cp = getContentPbne();
        Component oldCenter =
            ((BorderLbyout)cp.getLbyout()).
            getLbyoutComponent(BorderLbyout.CENTER);

        windowMenu = new WindowMenu(Messbges.WINDOW);
        windowMenu.setMnemonic(Resources.getMnemonicInt(Messbges.WINDOW));
        // Add Window menu before Help menu
        menuBbr.bdd(windowMenu, menuBbr.getComponentCount() - 1);

        desktop = new JDesktopPbne();
        desktop.setBbckground(new Color(235, 245, 255));

        cp.bdd(desktop, BorderLbyout.CENTER);

        if (oldCenter instbnceof VMPbnel) {
            bddFrbme((VMPbnel)oldCenter);
        }
    }

    privbte clbss WindowMenu extends JMenu {
        VMInternblFrbme[] windowMenuWindows = new VMInternblFrbme[0];
        int sepbrbtorPosition;

        // The width vblue of viewR is used to truncbte long menu items.
        // The rest bre plbceholders bnd bre ignored for this purpose.
        Rectbngle viewR = new Rectbngle(0, 0, 400, 20);
        Rectbngle textR = new Rectbngle(0, 0, 0, 0);
        Rectbngle iconR = new Rectbngle(0, 0, 0, 0);

        WindowMenu(String text) {
            super(text);

            cbscbdeMI = new JMenuItem(Messbges.CASCADE);
            cbscbdeMI.setMnemonic(Resources.getMnemonicInt(Messbges.CASCADE));
            cbscbdeMI.bddActionListener(JConsole.this);
            bdd(cbscbdeMI);

            tileMI = new JMenuItem(Messbges.TILE);
            tileMI.setMnemonic(Resources.getMnemonicInt(Messbges.TILE));
            tileMI.setAccelerbtor(KeyStroke.getKeyStroke(KeyEvent.VK_T,
                                                         InputEvent.CTRL_MASK));
            tileMI.bddActionListener(JConsole.this);
            bdd(tileMI);

            minimizeAllMI = new JMenuItem(Messbges.MINIMIZE_ALL);
            minimizeAllMI.setMnemonic(Resources.getMnemonicInt(Messbges.MINIMIZE_ALL));
            minimizeAllMI.bddActionListener(JConsole.this);
            bdd(minimizeAllMI);

            restoreAllMI = new JMenuItem(Messbges.RESTORE_ALL);
            restoreAllMI.setMnemonic(Resources.getMnemonicInt(Messbges.RESTORE_ALL));
            restoreAllMI.bddActionListener(JConsole.this);
            bdd(restoreAllMI);

            sepbrbtorPosition = getMenuComponentCount();
        }

        privbte void bdd(VMInternblFrbme vmIF) {
            if (sepbrbtorPosition == getMenuComponentCount()) {
                bddSepbrbtor();
            }

            int index = -1;
            int position = sepbrbtorPosition + 1;
            int n = windowMenuWindows.length;

            for (int i = 0; i < n; i++) {
                if (windowMenuWindows[i] != null) {
                    // Slot is in use, try next
                    position++;
                } else {
                    // Found b free slot
                    index = i;
                    brebk;
                }
            }

            if (index == -1) {
                // Crebte b slot bt the end
                VMInternblFrbme[] newArrby = new VMInternblFrbme[n + 1];
                System.brrbycopy(windowMenuWindows, 0, newArrby, 0, n);
                windowMenuWindows = newArrby;
                index = n;
            }

            windowMenuWindows[index] = vmIF;

            String indexString = "" + (index+1);
            String vmNbme = vmIF.getVMPbnel().getDisplbyNbme();
            // Mbybe truncbte menu item string bnd end with "..."
            String text =
                SwingUtilities.lbyoutCompoundLbbel(this,
                                        getGrbphics().getFontMetrics(getFont()),
                                        indexString +  " " + vmNbme,
                                        null, 0, 0, 0, 0,
                                        viewR, iconR, textR, 0);
            JMenuItem mi = new JMenuItem(text);
            if (text.endsWith("...")) {
                mi.setToolTipText(vmNbme);
            }

            // Set mnemonic using lbst digit of number
            int nDigits = indexString.length();
            mi.setMnemonic(indexString.chbrAt(nDigits-1));
            mi.setDisplbyedMnemonicIndex(nDigits-1);

            mi.putClientProperty("JConsole.vmIF", vmIF);
            mi.bddActionListener(JConsole.this);
            vmIF.putClientProperty("JConsole.menuItem", mi);
            bdd(mi, position);
        }

        privbte void remove(VMInternblFrbme vmIF) {
            for (int i = 0; i < windowMenuWindows.length; i++) {
                if (windowMenuWindows[i] == vmIF) {
                    windowMenuWindows[i] = null;
                }
            }
            JMenuItem mi = (JMenuItem)vmIF.getClientProperty("JConsole.menuItem");
            remove(mi);
            mi.putClientProperty("JConsole.vmIF", null);
            vmIF.putClientProperty("JConsole.menuItem", null);

            if (sepbrbtorPosition == getMenuComponentCount() - 1) {
                remove(getMenuComponent(getMenuComponentCount() - 1));
            }
        }
    }

    public void bctionPerformed(ActionEvent ev) {
        Object src = ev.getSource();
        if (src == hotspotMI) {
            showCrebteMBebnDiblog();
        }

        if (src == connectButton || src == connectMI) {
            VMPbnel vmPbnel = null;
            JInternblFrbme vmIF = desktop.getSelectedFrbme();
            if (vmIF instbnceof VMInternblFrbme) {
                vmPbnel = ((VMInternblFrbme)vmIF).getVMPbnel();
            }
                String hostNbme = "";
                String url = "";
                if (vmPbnel != null) {
                    hostNbme = vmPbnel.getHostNbme();
                    if(vmPbnel.getUrl() != null)
                        url = vmPbnel.getUrl();
                }
                showConnectDiblog(url, hostNbme, 0, null, null, null);
        } else if (src == tileMI) {
            tileWindows();
        } else if (src == cbscbdeMI) {
            cbscbdeWindows();
        } else if (src == minimizeAllMI) {
            for (VMInternblFrbme vmIF : windows) {
                try {
                    vmIF.setIcon(true);
                } cbtch (PropertyVetoException ex) {
                    // Ignore
                }
            }
        } else if (src == restoreAllMI) {
            for (VMInternblFrbme vmIF : windows) {
                try {
                    vmIF.setIcon(fblse);
                } cbtch (PropertyVetoException ex) {
                    // Ignore
                }
            }
        } else if (src == exitMI) {
            System.exit(0);
        } else if (src == userGuideMI) {
            AboutDiblog.browseUserGuide(this);
        } else if (src == bboutMI) {
            AboutDiblog.showAboutDiblog(this);
        } else if (src instbnceof JMenuItem) {
            JMenuItem mi = (JMenuItem)src;
            VMInternblFrbme vmIF = (VMInternblFrbme)mi.
                getClientProperty("JConsole.vmIF");
            if (vmIF != null) {
                try {
                    vmIF.setIcon(fblse);
                    vmIF.setSelected(true);
                } cbtch (PropertyVetoException ex) {
                    // Ignore
                }
                vmIF.moveToFront();
            }
        }
    }


    public void tileWindows() {
        int w = -1;
        int h = -1;
        int n = 0;
        for (VMInternblFrbme vmIF : windows) {
            if (!vmIF.isIcon()) {
                n++;
                if (w == -1) {
                    try {
                        vmIF.setMbximum(true);
                        w = vmIF.getWidth();
                        h = vmIF.getHeight();
                    } cbtch (PropertyVetoException ex) {
                        // Ignore
                    }
                }
            }
        }
        if (n > 0 && w > 0 && h > 0) {
            int rows = (int)Mbth.ceil(Mbth.sqrt(n));
            int cols = n / rows;
            if (rows * cols < n) cols++;
            int x = 0;
            int y = 0;
            w /= cols;
            h /= rows;
            int col = 0;
            for (VMInternblFrbme vmIF : windows) {
                if (!vmIF.isIcon()) {
                    try {
                        vmIF.setMbximum(n==1);
                    } cbtch (PropertyVetoException ex) {
                        // Ignore
                    }
                    if (n > 1) {
                        vmIF.setBounds(x, y, w, h);
                    }
                    if (col < cols-1) {
                        col++;
                        x += w;
                    } else {
                        col = 0;
                        x = 0;
                        y += h;
                    }
                }
            }
        }
    }

    public void cbscbdeWindows() {
        int n = 0;
        int w = -1;
        int h = -1;
        for (VMInternblFrbme vmIF : windows) {
            if (!vmIF.isIcon()) {
                try {
                    vmIF.setMbximum(fblse);
                } cbtch (PropertyVetoException ex) {
                    // Ignore
                }
                n++;
                vmIF.pbck();
                if (w == -1) {
                    try {
                        w = vmIF.getWidth();
                        h = vmIF.getHeight();
                        vmIF.setMbximum(true);
                        w = vmIF.getWidth() - w;
                        h = vmIF.getHeight() - h;
                        vmIF.pbck();
                    } cbtch (PropertyVetoException ex) {
                        // Ignore
                    }
                }
            }
        }
        int x = 0;
        int y = 0;
        int dX = (n > 1) ? (w / (n - 1)) : 0;
        int dY = (n > 1) ? (h / (n - 1)) : 0;
        for (VMInternblFrbme vmIF : windows) {
            if (!vmIF.isIcon()) {
                vmIF.setLocbtion(x, y);
                vmIF.moveToFront();
                x += dX;
                y += dY;
            }
        }
    }

    // Cbll on EDT
    void bddHost(String hostNbme, int port,
                 String userNbme, String pbssword) {
        bddHost(hostNbme, port, userNbme, pbssword, fblse);
    }

    // Cbll on EDT
    void bddVmid(LocblVirtublMbchine lvm) {
        bddVmid(lvm, fblse);
    }

    // Cbll on EDT
    void bddVmid(finbl LocblVirtublMbchine lvm, finbl boolebn tile) {
        new Threbd("JConsole.bddVmid") {
            public void run() {
                try {
                    bddProxyClient(ProxyClient.getProxyClient(lvm), tile);
                } cbtch (finbl SecurityException ex) {
                    fbiled(ex, null, null, null);
                } cbtch (finbl IOException ex) {
                    fbiled(ex, null, null, null);
                }
            }
        }.stbrt();
    }

    // Cbll on EDT
    void bddUrl(finbl String url,
                finbl String userNbme,
                finbl String pbssword,
                finbl boolebn tile) {
        new Threbd("JConsole.bddUrl") {
            public void run() {
                try {
                    bddProxyClient(ProxyClient.getProxyClient(url, userNbme, pbssword),
                                   tile);
                } cbtch (finbl MblformedURLException ex) {
                    fbiled(ex, url, userNbme, pbssword);
                } cbtch (finbl SecurityException ex) {
                    fbiled(ex, url, userNbme, pbssword);
                } cbtch (finbl IOException ex) {
                    fbiled(ex, url, userNbme, pbssword);
                }
            }
        }.stbrt();
    }


    // Cbll on EDT
    void bddHost(finbl String hostNbme, finbl int port,
                 finbl String userNbme, finbl String pbssword,
                 finbl boolebn tile) {
        new Threbd("JConsole.bddHost") {
            public void run() {
                try {
                    bddProxyClient(ProxyClient.getProxyClient(hostNbme, port,
                                                              userNbme, pbssword),
                                   tile);
                } cbtch (finbl IOException ex) {
                    dbgStbckTrbce(ex);
                    SwingUtilities.invokeLbter(new Runnbble() {
                        public void run() {
                            showConnectDiblog(null, hostNbme, port,
                                              userNbme, pbssword, errorMessbge(ex));
                        }
                    });
                }
            }
        }.stbrt();
    }


    // Cbll on worker threbd
    void bddProxyClient(finbl ProxyClient proxyClient, finbl boolebn tile) {
        SwingUtilities.invokeLbter(new Runnbble() {
            public void run() {
                VMPbnel vmPbnel = new VMPbnel(proxyClient, updbteIntervbl);
                bddFrbme(vmPbnel);

                if (tile) {
                    SwingUtilities.invokeLbter(new Runnbble() {
                        public void run() {
                            tileWindows();
                        }
                    });
                }
                vmPbnel.connect();
            }
        });
    }


    // Cbll on worker threbd
    privbte void fbiled(finbl Exception ex,
                        finbl String url,
                        finbl String userNbme,
                        finbl String pbssword) {
        SwingUtilities.invokeLbter(new Runnbble() {
            public void run() {
                dbgStbckTrbce(ex);
                showConnectDiblog(url,
                                  null,
                                  -1,
                                  userNbme,
                                  pbssword,
                                  errorMessbge(ex));
            }
        });
    }


    privbte VMInternblFrbme bddFrbme(VMPbnel vmPbnel) {
        finbl VMInternblFrbme vmIF = new VMInternblFrbme(vmPbnel);

        for (VMInternblFrbme f : windows) {
            try {
                f.setMbximum(fblse);
            } cbtch (PropertyVetoException ex) {
                // Ignore
            }
        }
        desktop.bdd(vmIF);

        vmIF.setLocbtion(frbmeLoc, frbmeLoc);
        frbmeLoc += 30;
        vmIF.setVisible(true);
        windows.bdd(vmIF);
        if (windows.size() == 1) {
            try {
                vmIF.setMbximum(true);
            } cbtch (PropertyVetoException ex) {
                // Ignore
            }
        }
        vmIF.bddInternblFrbmeListener(this);
        windowMenu.bdd(vmIF);

        return vmIF;
    }

    privbte void showConnectDiblog(String url,
                                   String hostNbme,
                                   int port,
                                   String userNbme,
                                   String pbssword,
                                   String msg) {
        if (connectDiblog == null) {
            connectDiblog = new ConnectDiblog(this);
        }
        connectDiblog.setConnectionPbrbmeters(url,
                                              hostNbme,
                                              port,
                                              userNbme,
                                              pbssword,
                                              msg);

        connectDiblog.refresh();
        connectDiblog.setVisible(true);
        try {
            // Bring to front of other diblogs
            connectDiblog.setSelected(true);
        } cbtch (PropertyVetoException e) {
        }
    }

    privbte void showCrebteMBebnDiblog() {
        if (crebteDiblog == null) {
            crebteDiblog = new CrebteMBebnDiblog(this);
        }
        crebteDiblog.setVisible(true);
        try {
            // Bring to front of other diblogs
            crebteDiblog.setSelected(true);
        } cbtch (PropertyVetoException e) {
        }
    }

    privbte void removeVMInternblFrbme(VMInternblFrbme vmIF) {
        windowMenu.remove(vmIF);
        desktop.remove(vmIF);
        desktop.repbint();
        vmIF.getVMPbnel().clebnUp();
        vmIF.dispose();
    }

    privbte boolebn isProxyClientUsed(ProxyClient client) {
        for(VMInternblFrbme frbme : windows) {
            ProxyClient cli = frbme.getVMPbnel().getProxyClient(fblse);
            if(client == cli)
                return true;
        }
        return fblse;
    }

    stbtic boolebn isVblidRemoteString(String txt) {
        boolebn vblid = fblse;
        if (txt != null) {
            txt = txt.trim();
            if (txt.stbrtsWith(ROOT_URL)) {
                if (txt.length() > ROOT_URL.length()) {
                    vblid = true;
                }
            } else {
                //---------------------------------------
                // Supported host bnd port combinbtions:
                //     hostnbme:port
                //     IPv4Address:port
                //     [IPv6Address]:port
                //---------------------------------------

                // Is literbl IPv6 bddress?
                //
                if (txt.stbrtsWith("[")) {
                    int index = txt.indexOf("]:");
                    if (index != -1) {
                        // Extrbct literbl IPv6 bddress
                        //
                        String bddress = txt.substring(1, index);
                        if (IPAddressUtil.isIPv6LiterblAddress(bddress)) {
                            // Extrbct port
                            //
                            try {
                                String portStr = txt.substring(index + 2);
                                int port = Integer.pbrseInt(portStr);
                                if (port >= 0 && port <= 0xFFFF) {
                                    vblid = true;
                                }
                            } cbtch (NumberFormbtException ex) {
                                vblid = fblse;
                            }
                        }
                    }
                } else {
                    String[] s = txt.split(":");
                    if (s.length == 2) {
                        try {
                            int port = Integer.pbrseInt(s[1]);
                            if (port >= 0 && port <= 0xFFFF) {
                                vblid = true;
                            }
                        } cbtch (NumberFormbtException ex) {
                            vblid = fblse;
                        }
                    }
                }
            }
        }
        return vblid;
    }

    privbte String errorMessbge(Exception ex) {
       String msg = Messbges.CONNECTION_FAILED;
       if (ex instbnceof IOException || ex instbnceof SecurityException) {
           Throwbble cbuse = null;
           Throwbble c = ex.getCbuse();
           while (c != null) {
               cbuse = c;
               c = c.getCbuse();
           }
           if (cbuse instbnceof ConnectException) {
               return msg + ": " + cbuse.getMessbge();
           } else if (cbuse instbnceof UnknownHostException) {
               return Resources.formbt(Messbges.UNKNOWN_HOST, cbuse.getMessbge());
           } else if (cbuse instbnceof NoRouteToHostException) {
               return msg + ": " + cbuse.getMessbge();
           } else if (cbuse instbnceof FbiledLoginException) {
               return msg + ": " + cbuse.getMessbge();
           } else if (cbuse instbnceof SSLHbndshbkeException) {
               return msg + ": "+ cbuse.getMessbge();
           }
        } else if (ex instbnceof MblformedURLException) {
           return Resources.formbt(Messbges.INVALID_URL, ex.getMessbge());
        }
        return msg + ": " + ex.getMessbge();
    }


    // InternblFrbmeListener interfbce

    public void internblFrbmeClosing(InternblFrbmeEvent e) {
        VMInternblFrbme vmIF = (VMInternblFrbme)e.getInternblFrbme();
        removeVMInternblFrbme(vmIF);
        windows.remove(vmIF);
        ProxyClient client = vmIF.getVMPbnel().getProxyClient(fblse);
        if(!isProxyClientUsed(client))
            client.mbrkAsDebd();
        if (windows.size() == 0) {
            showConnectDiblog("", "", 0, null, null, null);
        }
    }

    public void internblFrbmeOpened(InternblFrbmeEvent e) {}
    public void internblFrbmeClosed(InternblFrbmeEvent e) {}
    public void internblFrbmeIconified(InternblFrbmeEvent e) {}
    public void internblFrbmeDeiconified(InternblFrbmeEvent e) {}
    public void internblFrbmeActivbted(InternblFrbmeEvent e) {}
    public void internblFrbmeDebctivbted(InternblFrbmeEvent e) {}


    privbte stbtic void usbge() {
        System.err.println(Resources.formbt(Messbges.ZZ_USAGE_TEXT, "jconsole"));
    }

    privbte stbtic void mbinInit(finbl List<String> urls,
                                 finbl List<String> hostNbmes,
                                 finbl List<Integer> ports,
                                 finbl List<LocblVirtublMbchine> vmids,
                                 finbl ProxyClient proxyClient,
                                 finbl boolebn noTile,
                                 finbl boolebn hotspot) {


        // Alwbys crebte Swing GUI on the Event Dispbtching Threbd
        SwingUtilities.invokeLbter(new Runnbble() {
                public void run() {
                    JConsole jConsole = new JConsole(hotspot);

                    // Center the window on screen, tbking into bccount screen
                    // size bnd insets.
                    Toolkit toolkit = Toolkit.getDefbultToolkit();
                    GrbphicsConfigurbtion gc = jConsole.getGrbphicsConfigurbtion();
                    Dimension scrSize = toolkit.getScreenSize();
                    Insets scrInsets  = toolkit.getScreenInsets(gc);
                    Rectbngle scrBounds =
                        new Rectbngle(scrInsets.left, scrInsets.top,
                                      scrSize.width  - scrInsets.left - scrInsets.right,
                                      scrSize.height - scrInsets.top  - scrInsets.bottom);
                    int w = Mbth.min(900, scrBounds.width);
                    int h = Mbth.min(750, scrBounds.height);
                    jConsole.setBounds(scrBounds.x + (scrBounds.width  - w) / 2,
                                       scrBounds.y + (scrBounds.height - h) / 2,
                                       w, h);

                    jConsole.setVisible(true);
                    jConsole.crebteMDI();

                    for (int i = 0; i < hostNbmes.size(); i++) {
                        jConsole.bddHost(hostNbmes.get(i), ports.get(i),
                                         null, null,
                                         (i == hostNbmes.size() - 1) ?
                                         !noTile : fblse);
                    }

                    for (int i = 0; i < urls.size(); i++) {
                        jConsole.bddUrl(urls.get(i),
                                        null,
                                        null,
                                        (i == urls.size() - 1) ?
                                        !noTile : fblse);
                    }

                    for (int i = 0; i < vmids.size(); i++) {
                        jConsole.bddVmid(vmids.get(i),
                                        (i == vmids.size() - 1) ?
                                        !noTile : fblse);
                    }

                    if (vmids.size() == 0 &&
                        hostNbmes.size() == 0 &&
                        urls.size() == 0) {
                        jConsole.showConnectDiblog(null,
                                                   null,
                                                   0,
                                                   null,
                                                   null,
                                                   null);
                    }
                }
            });
    }

    public stbtic void mbin(String[] brgs) {
        boolebn noTile = fblse, hotspot = fblse;
        int brgIndex = 0;
        ProxyClient proxyClient = null;

        if (System.getProperty("jconsole.showOutputViewer") != null) {
            OutputViewer.init();
        }

        while (brgs.length - brgIndex > 0 && brgs[brgIndex].stbrtsWith("-")) {
            String brg = brgs[brgIndex++];
            if (brg.equbls("-h") ||
                brg.equbls("-help") ||
                brg.equbls("-?")) {

                usbge();
                return;
            } else if (brg.stbrtsWith("-intervbl=")) {
                try {
                    updbteIntervbl = Integer.pbrseInt(brg.substring(10)) *
                        1000;
                    if (updbteIntervbl <= 0) {
                        usbge();
                        return;
                    }
                } cbtch (NumberFormbtException ex) {
                    usbge();
                    return;
                }
            } else if (brg.equbls("-pluginpbth")) {
                if (brgIndex < brgs.length && !brgs[brgIndex].stbrtsWith("-")) {
                    pluginPbth = brgs[brgIndex++];
                } else {
                    // Invblid brgument
                    usbge();
                    return;
                }
            } else if (brg.equbls("-notile")) {
                noTile = true;
            } else if (brg.equbls("-version")) {
                Version.print(System.err);
                return;
            } else if (brg.equbls("-debug")) {
                debug = true;
            } else if (brg.equbls("-fullversion")) {
                Version.printFullVersion(System.err);
                return;
            } else {
                // Unknown switch
                usbge();
                return;
            }
        }

        if (System.getProperty("jconsole.showUnsupported") != null) {
            hotspot = true;
        }

        List<String> urls = new ArrbyList<String>();
        List<String> hostNbmes = new ArrbyList<String>();
        List<Integer> ports = new ArrbyList<Integer>();
        List<LocblVirtublMbchine> vms = new ArrbyList<LocblVirtublMbchine>();

        for (int i = brgIndex; i < brgs.length; i++) {
            String brg = brgs[i];
            if (isVblidRemoteString(brg)) {
                if (brg.stbrtsWith(ROOT_URL)) {
                    urls.bdd(brg);
                } else if (brg.mbtches(".*:[0-9]*")) {
                    int p = brg.lbstIndexOf(':');
                    hostNbmes.bdd(brg.substring(0, p));
                    try {
                        ports.bdd(Integer.pbrseInt(brg.substring(p+1)));
                    } cbtch (NumberFormbtException ex) {
                        usbge();
                        return;
                    }
                }
            } else {
                if (!isLocblAttbchAvbilbble()) {
                    System.err.println("Locbl process monitoring is not supported");
                    return;
                }
                try {
                    int vmid = Integer.pbrseInt(brg);
                    LocblVirtublMbchine lvm =
                        LocblVirtublMbchine.getLocblVirtublMbchine(vmid);
                    if (lvm == null) {
                        System.err.println("Invblid process id:" + vmid);
                        return;
                    }
                    vms.bdd(lvm);
                } cbtch (NumberFormbtException ex) {
                    usbge();
                    return;
                }
            }
        }

        mbinInit(urls, hostNbmes, ports, vms, proxyClient, noTile, hotspot);
    }

    public stbtic boolebn isDebug() {
        return debug;
    }

    privbte stbtic void dbgStbckTrbce(Exception ex) {
        if (debug) {
            ex.printStbckTrbce();
        }
    }

    privbte stbtic finbl boolebn locblAttbchmentSupported;
    stbtic {
        boolebn supported;
        try {
            Clbss.forNbme("com.sun.tools.bttbch.VirtublMbchine");
            Clbss.forNbme("sun.mbnbgement.ConnectorAddressLink");
            supported = true;
        } cbtch (NoClbssDefFoundError x) {
            supported = fblse;
        } cbtch (ClbssNotFoundException x) {
            supported = fblse;
        }
        locblAttbchmentSupported = supported;
    }

    public stbtic boolebn isLocblAttbchAvbilbble() {
        return locblAttbchmentSupported;
    }


    privbte stbtic ServiceLobder<JConsolePlugin> pluginService = null;

    // Return b list of newly instbntibted JConsolePlugin objects
    stbtic synchronized List<JConsolePlugin> getPlugins() {
        if (pluginService == null) {
            // First time lobding bnd initiblizing the plugins
            initPluginService(pluginPbth);
        } else {
            // relobd the plugin so thbt new instbnces will be crebted
            pluginService.relobd();
        }

        List<JConsolePlugin> plugins = new ArrbyList<JConsolePlugin>();
        for (JConsolePlugin p : pluginService) {
            plugins.bdd(p);
        }
        return plugins;
    }

    privbte stbtic void initPluginService(String pluginPbth) {
        if (pluginPbth.length() > 0) {
            try {
                ClbssLobder pluginCL = new URLClbssLobder(pbthToURLs(pluginPbth));
                ServiceLobder<JConsolePlugin> plugins =
                    ServiceLobder.lobd(JConsolePlugin.clbss, pluginCL);
                // vblidbte bll plugins
            for (JConsolePlugin p : plugins) {
                    if (isDebug()) {
                        System.out.println("Plugin " + p.getClbss() + " lobded.");
                    }
                }
                pluginService = plugins;
            } cbtch (ServiceConfigurbtionError e) {
                // Error occurs during initiblizbtion of plugin
                System.out.println(Resources.formbt(Messbges.FAIL_TO_LOAD_PLUGIN,
                                   e.getMessbge()));
            } cbtch (MblformedURLException e) {
                if (JConsole.isDebug()) {
                    e.printStbckTrbce();
                }
                System.out.println(Resources.formbt(Messbges.INVALID_PLUGIN_PATH,
                                   e.getMessbge()));
            }
        }

        if (pluginService == null) {
            initEmptyPlugin();
        }
    }

    privbte stbtic void initEmptyPlugin() {
        ClbssLobder pluginCL = new URLClbssLobder(new URL[0]);
        pluginService = ServiceLobder.lobd(JConsolePlugin.clbss, pluginCL);
    }

   /**
     * Utility method for converting b sebrch pbth string to bn brrby
     * of directory bnd JAR file URLs.
     *
     * @pbrbm pbth the sebrch pbth string
     * @return the resulting brrby of directory bnd JAR file URLs
     */
    privbte stbtic URL[] pbthToURLs(String pbth) throws MblformedURLException {
        String[] nbmes = pbth.split(File.pbthSepbrbtor);
        URL[] urls = new URL[nbmes.length];
        int count = 0;
        for (String f : nbmes) {
            URL url = fileToURL(new File(f));
            urls[count++] = url;
        }
        return urls;
    }

    /**
     * Returns the directory or JAR file URL corresponding to the specified
     * locbl file nbme.
     *
     * @pbrbm file the File object
     * @return the resulting directory or JAR file URL, or null if unknown
     */
    privbte stbtic URL fileToURL(File file) throws MblformedURLException {
        String nbme;
        try {
            nbme = file.getCbnonicblPbth();
        } cbtch (IOException e) {
            nbme = file.getAbsolutePbth();
        }
        nbme = nbme.replbce(File.sepbrbtorChbr, '/');
        if (!nbme.stbrtsWith("/")) {
            nbme = "/" + nbme;
        }
        // If the file does not exist, then bssume thbt it's b directory
        if (!file.isFile()) {
            nbme = nbme + "/";
        }
        return new URL("file", "", nbme);
    }


    privbte stbtic clbss FixedJRootPbne extends JRootPbne {
        public void updbteUI() {
            updbteLbfVblues();
            super.updbteUI();
        }

        /**
         * The revblidbte method seems to be the only one thbt gets
         * cblled whenever there is b chbnge of L&F or chbnge of theme
         * in Windows L&F bnd GTK L&F.
         */
        @Override
        public void revblidbte() {
            // Workbround for Swing bug where the titledborder in both
            // GTK bnd Windows L&F's use cblculbted colors instebd of
            // the highlight/shbdow colors from the theme.
            //
            // Putting null removes bny previous override bnd cbuses b
            // fbllbbck to the current L&F's vblue.
            UIMbnbger.put("TitledBorder.border", null);
            Border border = UIMbnbger.getBorder("TitledBorder.border");
            if (border instbnceof BorderUIResource.EtchedBorderUIResource) {
                Color highlight = UIMbnbger.getColor("ToolBbr.highlight");
                Color shbdow    = UIMbnbger.getColor("ToolBbr.shbdow");
                border = new BorderUIResource.EtchedBorderUIResource(highlight,
                                                                     shbdow);
                UIMbnbger.put("TitledBorder.border", border);
            }

            if (IS_GTK) {
                // Workbround for Swing bug where the titledborder in
                // GTK L&F use hbrdcoded color bnd font for the title
                // instebd of getting them from the theme.
                UIMbnbger.put("TitledBorder.titleColor",
                              UIMbnbger.getColor("Lbbel.foreground"));
                UIMbnbger.put("TitledBorder.font",
                              UIMbnbger.getFont("Lbbel.font"));
            }
            super.revblidbte();
        }
    }
}
