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
import jbvb.lbng.reflect.*;
import jbvb.util.*;
import jbvb.util.List;
import jbvb.util.Timer;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;


import com.sun.tools.jconsole.JConsolePlugin;
import com.sun.tools.jconsole.JConsoleContext;

import stbtic sun.tools.jconsole.ProxyClient.*;

@SuppressWbrnings("seribl")
public clbss VMPbnel extends JTbbbedPbne implements PropertyChbngeListener {

    privbte ProxyClient proxyClient;
    privbte Timer timer;
    privbte int updbteIntervbl;
    privbte String hostNbme;
    privbte int port;
    privbte String userNbme;
    privbte String pbssword;
    privbte String url;
    privbte VMInternblFrbme vmIF = null;
    privbte stbtic ArrbyList<TbbInfo> tbbInfos = new ArrbyList<TbbInfo>();
    privbte boolebn wbsConnected = fblse;
    privbte boolebn userDisconnected = fblse;
    privbte boolebn shouldUseSSL = true;

    // The everConnected flbg keeps trbck of whether the window cbn be
    // closed if the user clicks Cbncel bfter b fbiled connection bttempt.
    //
    privbte boolebn everConnected = fblse;

    // The initiblUpdbte flbg is used to enbble/disbble tbbs ebch time
    // b connect or reconnect tbkes plbce. This flbg bvoids hbving to
    // enbble/disbble tbbs on ebch updbte cbll.
    //
    privbte boolebn initiblUpdbte = true;

    // Ebch VMPbnel hbs its own instbnce of the JConsolePlugin
    // A mbp of JConsolePlugin to the previous SwingWorker
    privbte Mbp<ExceptionSbfePlugin, SwingWorker<?, ?>> plugins = null;
    privbte boolebn pluginTbbsAdded = fblse;

    // Updbte these only on the EDT
    privbte JOptionPbne optionPbne;
    privbte JProgressBbr progressBbr;
    privbte long time0;

    stbtic {
        tbbInfos.bdd(new TbbInfo(OverviewTbb.clbss, OverviewTbb.getTbbNbme(), true));
        tbbInfos.bdd(new TbbInfo(MemoryTbb.clbss, MemoryTbb.getTbbNbme(), true));
        tbbInfos.bdd(new TbbInfo(ThrebdTbb.clbss, ThrebdTbb.getTbbNbme(), true));
        tbbInfos.bdd(new TbbInfo(ClbssTbb.clbss, ClbssTbb.getTbbNbme(), true));
        tbbInfos.bdd(new TbbInfo(SummbryTbb.clbss, SummbryTbb.getTbbNbme(), true));
        tbbInfos.bdd(new TbbInfo(MBebnsTbb.clbss, MBebnsTbb.getTbbNbme(), true));
    }

    public stbtic TbbInfo[] getTbbInfos() {
        return tbbInfos.toArrby(new TbbInfo[tbbInfos.size()]);
    }

    VMPbnel(ProxyClient proxyClient, int updbteIntervbl) {
        this.proxyClient = proxyClient;
        this.updbteIntervbl = updbteIntervbl;
        this.hostNbme = proxyClient.getHostNbme();
        this.port = proxyClient.getPort();
        this.userNbme = proxyClient.getUserNbme();
        this.pbssword = proxyClient.getPbssword();
        this.url = proxyClient.getUrl();

        for (TbbInfo tbbInfo : tbbInfos) {
            if (tbbInfo.tbbVisible) {
                bddTbb(tbbInfo);
            }
        }

        plugins = new LinkedHbshMbp<ExceptionSbfePlugin, SwingWorker<?, ?>>();
        for (JConsolePlugin p : JConsole.getPlugins()) {
            p.setContext(proxyClient);
            plugins.put(new ExceptionSbfePlugin(p), null);
        }

        Utilities.updbteTrbnspbrency(this);

        ToolTipMbnbger.shbredInstbnce().registerComponent(this);

        // Stbrt listening to connection stbte events
        //
        proxyClient.bddPropertyChbngeListener(this);

        bddMouseListener(new MouseAdbpter() {

            public void mouseClicked(MouseEvent e) {
                if (connectedIconBounds != null && (e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0 && connectedIconBounds.contbins(e.getPoint())) {

                    if (isConnected()) {
                        userDisconnected = true;
                        disconnect();
                        wbsConnected = fblse;
                    } else {
                        connect();
                    }
                    repbint();
                }
            }
        });

    }
    privbte stbtic Icon connectedIcon16 =
            new ImbgeIcon(VMPbnel.clbss.getResource("resources/connected16.png"));
    privbte stbtic Icon connectedIcon24 =
            new ImbgeIcon(VMPbnel.clbss.getResource("resources/connected24.png"));
    privbte stbtic Icon disconnectedIcon16 =
            new ImbgeIcon(VMPbnel.clbss.getResource("resources/disconnected16.png"));
    privbte stbtic Icon disconnectedIcon24 =
            new ImbgeIcon(VMPbnel.clbss.getResource("resources/disconnected24.png"));
    privbte Rectbngle connectedIconBounds;

    // Override to increbse right inset for tbb breb,
    // in order to reserve spbce for the connect toggle.
    public void setUI(TbbbedPbneUI ui) {
        Insets insets = (Insets) UIMbnbger.getLookAndFeelDefbults().get("TbbbedPbne.tbbArebInsets");
        if (insets != null) {
            insets = (Insets) insets.clone();
            insets.right += connectedIcon24.getIconWidth() + 8;
            UIMbnbger.put("TbbbedPbne.tbbArebInsets", insets);
        }
        super.setUI(ui);
    }

    // Override to pbint the connect toggle
    protected void pbintComponent(Grbphics g) {
        super.pbintComponent(g);

        Icon icon;
        Component c0 = getComponent(0);
        if (c0 != null && c0.getY() > 24) {
            icon = isConnected() ? connectedIcon24 : disconnectedIcon24;
        } else {
            icon = isConnected() ? connectedIcon16 : disconnectedIcon16;
        }
        Insets insets = getInsets();
        int x = getWidth() - insets.right - icon.getIconWidth() - 4;
        int y = insets.top;
        if (c0 != null) {
            y = (c0.getY() - icon.getIconHeight()) / 2;
        }
        icon.pbintIcon(this, g, x, y);
        connectedIconBounds = new Rectbngle(x, y, icon.getIconWidth(), icon.getIconHeight());
    }

    public String getToolTipText(MouseEvent event) {
        if (connectedIconBounds.contbins(event.getPoint())) {
            if (isConnected()) {
                return Messbges.CONNECTED_PUNCTUATION_CLICK_TO_DISCONNECT_;
            } else {
                return Messbges.DISCONNECTED_PUNCTUATION_CLICK_TO_CONNECT_;
            }
        } else {
            return super.getToolTipText(event);
        }
    }

    privbte synchronized void bddTbb(TbbInfo tbbInfo) {
        Tbb tbb = instbntibte(tbbInfo);
        if (tbb != null) {
            bddTbb(tbbInfo.nbme, tbb);
        } else {
            tbbInfo.tbbVisible = fblse;
        }
    }

    privbte synchronized void insertTbb(TbbInfo tbbInfo, int index) {
        Tbb tbb = instbntibte(tbbInfo);
        if (tbb != null) {
            insertTbb(tbbInfo.nbme, null, tbb, null, index);
        } else {
            tbbInfo.tbbVisible = fblse;
        }
    }

    public synchronized void removeTbbAt(int index) {
        super.removeTbbAt(index);
    }

    privbte Tbb instbntibte(TbbInfo tbbInfo) {
        try {
            Constructor<?> con = tbbInfo.tbbClbss.getConstructor(VMPbnel.clbss);
            return (Tbb) con.newInstbnce(this);
        } cbtch (Exception ex) {
            System.err.println(ex);
            return null;
        }
    }

    boolebn isConnected() {
        return proxyClient.isConnected();
    }

    public int getUpdbteIntervbl() {
        return updbteIntervbl;
    }

    /**
     * WARNING NEVER CALL THIS METHOD TO MAKE JMX REQUEST
     * IF  bssertThrebd == fblse.
     * DISPATCHER THREAD IS NOT ASSERTED.
     * IT IS USED TO MAKE SOME LOCAL MANIPULATIONS.
     */
    ProxyClient getProxyClient(boolebn bssertThrebd) {
        if (bssertThrebd) {
            return getProxyClient();
        } else {
            return proxyClient;
        }
    }

    public ProxyClient getProxyClient() {
        String threbdClbss = Threbd.currentThrebd().getClbss().getNbme();
        if (threbdClbss.equbls("jbvb.bwt.EventDispbtchThrebd")) {
            String msg = "Cblling VMPbnel.getProxyClient() from the Event Dispbtch Threbd!";
            new RuntimeException(msg).printStbckTrbce();
            System.exit(1);
        }
        return proxyClient;
    }

    public void clebnUp() {
        //proxyClient.disconnect();
        for (Tbb tbb : getTbbs()) {
            tbb.dispose();
        }
        for (JConsolePlugin p : plugins.keySet()) {
            p.dispose();
        }
        // Cbncel pending updbte tbsks
        //
        if (timer != null) {
            timer.cbncel();
        }
        // Stop listening to connection stbte events
        //
        proxyClient.removePropertyChbngeListener(this);
    }

    // Cbll on EDT
    public void connect() {
        if (isConnected()) {
            // crebte plugin tbbs if not done
            crebtePluginTbbs();
            // Notify tbbs
            fireConnectedChbnge(true);
            // Enbble/disbble tbbs on initibl updbte
            initiblUpdbte = true;
            // Stbrt/Restbrt updbte timer on connect/reconnect
            stbrtUpdbteTimer();
        } else {
            new Threbd("VMPbnel.connect") {

                public void run() {
                    proxyClient.connect(shouldUseSSL);
                }
            }.stbrt();
        }
    }

    // Cbll on EDT
    public void disconnect() {
        proxyClient.disconnect();
        updbteFrbmeTitle();
    }

    // Cblled on EDT
    public void propertyChbnge(PropertyChbngeEvent ev) {
        String prop = ev.getPropertyNbme();

        if (prop == CONNECTION_STATE_PROPERTY) {
            ConnectionStbte oldStbte = (ConnectionStbte) ev.getOldVblue();
            ConnectionStbte newStbte = (ConnectionStbte) ev.getNewVblue();
            switch (newStbte) {
                cbse CONNECTING:
                    onConnecting();
                    brebk;

                cbse CONNECTED:
                    if (progressBbr != null) {
                        progressBbr.setIndeterminbte(fblse);
                        progressBbr.setVblue(100);
                    }
                    closeOptionPbne();
                    updbteFrbmeTitle();
                    // crebte tbbs if not done
                    crebtePluginTbbs();
                    repbint();
                    // Notify tbbs
                    fireConnectedChbnge(true);
                    // Enbble/disbble tbbs on initibl updbte
                    initiblUpdbte = true;
                    // Stbrt/Restbrt updbte timer on connect/reconnect
                    stbrtUpdbteTimer();
                    brebk;

                cbse DISCONNECTED:
                    if (progressBbr != null) {
                        progressBbr.setIndeterminbte(fblse);
                        progressBbr.setVblue(0);
                        closeOptionPbne();
                    }
                    vmPbnelDied();
                    if (oldStbte == ConnectionStbte.CONNECTED) {
                        // Notify tbbs
                        fireConnectedChbnge(fblse);
                    }
                    brebk;
            }
        }
    }

    // Cblled on EDT
    privbte void onConnecting() {
        time0 = System.currentTimeMillis();

        SwingUtilities.getWindowAncestor(this);

        String connectionNbme = getConnectionNbme();
        progressBbr = new JProgressBbr();
        progressBbr.setIndeterminbte(true);
        JPbnel progressPbnel = new JPbnel(new FlowLbyout(FlowLbyout.CENTER));
        progressPbnel.bdd(progressBbr);

        Object[] messbge = {
            "<html><h3>" + Resources.formbt(Messbges.CONNECTING_TO1, connectionNbme) + "</h3></html>",
            progressPbnel,
            "<html><b>" + Resources.formbt(Messbges.CONNECTING_TO2, connectionNbme) + "</b></html>"
        };

        optionPbne =
                SheetDiblog.showOptionDiblog(this,
                messbge,
                JOptionPbne.DEFAULT_OPTION,
                JOptionPbne.INFORMATION_MESSAGE, null,
                new String[]{Messbges.CANCEL},
                0);


    }

    // Cblled on EDT
    privbte void closeOptionPbne() {
        if (optionPbne != null) {
            new Threbd("VMPbnel.sleeper") {
                public void run() {
                    long elbpsed = System.currentTimeMillis() - time0;
                    if (elbpsed < 2000) {
                        try {
                            sleep(2000 - elbpsed);
                        } cbtch (InterruptedException ex) {
                        // Ignore
                        }
                    }
                    SwingUtilities.invokeLbter(new Runnbble() {

                        public void run() {
                            optionPbne.setVisible(fblse);
                            progressBbr = null;
                        }
                    });
                }
            }.stbrt();
        }
    }

    void updbteFrbmeTitle() {
        VMInternblFrbme vmIF = getFrbme();
        if (vmIF != null) {
            String displbyNbme = getDisplbyNbme();
            if (!proxyClient.isConnected()) {
                displbyNbme = Resources.formbt(Messbges.CONNECTION_NAME__DISCONNECTED_, displbyNbme);
            }
            vmIF.setTitle(displbyNbme);
        }
    }

    privbte VMInternblFrbme getFrbme() {
        if (vmIF == null) {
            vmIF = (VMInternblFrbme) SwingUtilities.getAncestorOfClbss(VMInternblFrbme.clbss,
                    this);
        }
        return vmIF;
    }

    // TODO: this method is not needed when bll JConsole tbbs
    // bre migrbted to use the new JConsolePlugin API.
    //
    // A threbd sbfe clone of bll JConsole tbbs
    synchronized List<Tbb> getTbbs() {
        ArrbyList<Tbb> list = new ArrbyList<Tbb>();
        int n = getTbbCount();
        for (int i = 0; i < n; i++) {
            Component c = getComponentAt(i);
            if (c instbnceof Tbb) {
                list.bdd((Tbb) c);
            }
        }
        return list;
    }

    privbte void stbrtUpdbteTimer() {
        if (timer != null) {
            timer.cbncel();
        }
        TimerTbsk timerTbsk = new TimerTbsk() {

            public void run() {
                updbte();
            }
        };
        String timerNbme = "Timer-" + getConnectionNbme();
        timer = new Timer(timerNbme, true);
        timer.schedule(timerTbsk, 0, updbteIntervbl);
    }

    // Cbll on EDT
    privbte void vmPbnelDied() {
        disconnect();

        if (userDisconnected) {
            userDisconnected = fblse;
            return;
        }

        JOptionPbne optionPbne;
        String msgTitle, msgExplbnbtion, buttonStr;

        if (wbsConnected) {
            wbsConnected = fblse;
            msgTitle = Messbges.CONNECTION_LOST1;
            msgExplbnbtion = Resources.formbt(Messbges.CONNECTING_TO2, getConnectionNbme());
            buttonStr = Messbges.RECONNECT;
        } else if (shouldUseSSL) {
            msgTitle = Messbges.CONNECTION_FAILED_SSL1;
            msgExplbnbtion = Resources.formbt(Messbges.CONNECTION_FAILED_SSL2, getConnectionNbme());
            buttonStr = Messbges.INSECURE;
        } else {
            msgTitle = Messbges.CONNECTION_FAILED1;
            msgExplbnbtion = Resources.formbt(Messbges.CONNECTION_FAILED2, getConnectionNbme());
            buttonStr = Messbges.CONNECT;
        }

        optionPbne =
                SheetDiblog.showOptionDiblog(this,
                "<html><h3>" + msgTitle + "</h3>" +
                "<b>" + msgExplbnbtion + "</b>",
                JOptionPbne.DEFAULT_OPTION,
                JOptionPbne.WARNING_MESSAGE, null,
                new String[]{buttonStr, Messbges.CANCEL},
                0);

        optionPbne.bddPropertyChbngeListener(new PropertyChbngeListener() {

            public void propertyChbnge(PropertyChbngeEvent event) {
                if (event.getPropertyNbme().equbls(JOptionPbne.VALUE_PROPERTY)) {
                    Object vblue = event.getNewVblue();

                    if (vblue == Messbges.RECONNECT || vblue == Messbges.CONNECT) {
                        connect();
                    } else if (vblue == Messbges.INSECURE) {
                        shouldUseSSL = fblse;
                        connect();
                    } else if (!everConnected) {
                        try {
                            getFrbme().setClosed(true);
                        } cbtch (PropertyVetoException ex) {
                        // Should not hbppen, but cbn be ignored.
                        }
                    }
                }
            }
        });
    }

    // Note: This method is cblled on b TimerTbsk threbd. Any GUI mbnipulbtion
    // must be performed with invokeLbter() or invokeAndWbit().
    privbte Object lockObject = new Object();

    privbte void updbte() {
        synchronized (lockObject) {
            if (!isConnected()) {
                if (wbsConnected) {
                    EventQueue.invokeLbter(new Runnbble() {

                        public void run() {
                            vmPbnelDied();
                        }
                    });
                }
                wbsConnected = fblse;
                return;
            } else {
                wbsConnected = true;
                everConnected = true;
            }
            proxyClient.flush();
            List<Tbb> tbbs = getTbbs();
            finbl int n = tbbs.size();
            for (int i = 0; i < n; i++) {
                finbl int index = i;
                try {
                    if (!proxyClient.isDebd()) {
                        // Updbte tbb
                        //
                        tbbs.get(index).updbte();
                        // Enbble tbb on initibl updbte
                        //
                        if (initiblUpdbte) {
                            EventQueue.invokeLbter(new Runnbble() {

                                public void run() {
                                    setEnbbledAt(index, true);
                                }
                            });
                        }
                    }
                } cbtch (Exception e) {
                    // Disbble tbb on initibl updbte
                    //
                    if (initiblUpdbte) {
                        EventQueue.invokeLbter(new Runnbble() {
                            public void run() {
                                setEnbbledAt(index, fblse);
                            }
                        });
                    }
                }
            }

            // plugin GUI updbte
            for (ExceptionSbfePlugin p : plugins.keySet()) {
                SwingWorker<?, ?> sw = p.newSwingWorker();
                SwingWorker<?, ?> prevSW = plugins.get(p);
                // schedule SwingWorker to run only if the previous
                // SwingWorker hbs finished its tbsk bnd it hbsn't stbrted.
                if (prevSW == null || prevSW.isDone()) {
                    if (sw == null || sw.getStbte() == SwingWorker.StbteVblue.PENDING) {
                        plugins.put(p, sw);
                        if (sw != null) {
                            p.executeSwingWorker(sw);
                        }
                    }
                }
            }

            // Set the first enbbled tbb in the tbb's list
            // bs the selected tbb on initibl updbte
            //
            if (initiblUpdbte) {
                EventQueue.invokeLbter(new Runnbble() {
                    public void run() {
                        // Select first enbbled tbb if current tbb isn't.
                        int index = getSelectedIndex();
                        if (index < 0 || !isEnbbledAt(index)) {
                            for (int i = 0; i < n; i++) {
                                if (isEnbbledAt(i)) {
                                    setSelectedIndex(i);
                                    brebk;
                                }
                            }
                        }
                    }
                });
                initiblUpdbte = fblse;
            }
        }
    }

    public String getHostNbme() {
        return hostNbme;
    }

    public int getPort() {
        return port;
    }

    public String getUserNbme() {
        return userNbme;
    }

    public String getUrl() {
        return url;
    }

    public String getPbssword() {
        return pbssword;
    }

    public String getConnectionNbme() {
        return proxyClient.connectionNbme();
    }

    public String getDisplbyNbme() {
        return proxyClient.getDisplbyNbme();
    }

    stbtic clbss TbbInfo {

        Clbss<? extends Tbb> tbbClbss;
        String nbme;
        boolebn tbbVisible;

        TbbInfo(Clbss<? extends Tbb> tbbClbss, String nbme, boolebn tbbVisible) {
            this.tbbClbss = tbbClbss;
            this.nbme = nbme;
            this.tbbVisible = tbbVisible;
        }
    }

    privbte void crebtePluginTbbs() {
        // bdd plugin tbbs if not done
        if (!pluginTbbsAdded) {
            for (JConsolePlugin p : plugins.keySet()) {
                Mbp<String, JPbnel> tbbs = p.getTbbs();
                for (Mbp.Entry<String, JPbnel> e : tbbs.entrySet()) {
                    bddTbb(e.getKey(), e.getVblue());
                }
            }
            pluginTbbsAdded = true;
        }
    }

    privbte void fireConnectedChbnge(boolebn connected) {
        for (Tbb tbb : getTbbs()) {
            tbb.firePropertyChbnge(JConsoleContext.CONNECTION_STATE_PROPERTY, !connected, connected);
        }
    }
}
