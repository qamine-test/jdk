/*
 * Copyright (c) 2004, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.*;
import jbvb.lbng.mbnbgement.*;
import jbvb.lbng.reflect.*;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.*;


import jbvb.util.*;
import jbvb.util.concurrent.*;
import jbvb.util.List;

import stbtic sun.tools.jconsole.Utilities.*;


@SuppressWbrnings("seribl")
clbss ThrebdTbb extends Tbb implements ActionListener, DocumentListener, ListSelectionListener {
    PlotterPbnel threbdMeter;
    TimeComboBox timeComboBox;
    JTbbbedPbne threbdListTbbbedPbne;
    DefbultListModel<Long> listModel;
    JTextField filterTF;
    JLbbel messbgeLbbel;
    JSplitPbne threbdsSplitPbne;
    HbshMbp<Long, String> nbmeCbche = new HbshMbp<Long, String>();

    privbte ThrebdOverviewPbnel overviewPbnel;
    privbte boolebn plotterListening = fblse;


    privbte stbtic finbl String threbdCountKey   = "threbdCount";
    privbte stbtic finbl String pebkKey          = "pebk";

    privbte stbtic finbl Color  threbdCountColor = Plotter.defbultColor;
    privbte stbtic finbl Color  pebkColor        = Color.red;

    privbte stbtic finbl Border thinEmptyBorder  = new EmptyBorder(2, 2, 2, 2);

    /*
      Hierbrchy of pbnels bnd lbyouts for this tbb:

        ThrebdTbb (BorderLbyout)

            North:  topPbnel (BorderLbyout)

                        Center: controlPbnel (FlowLbyout)
                                    timeComboBox

            Center: plotterPbnel (BorderLbyout)

                        Center: plotter

    */


    public stbtic String getTbbNbme() {
        return Messbges.THREADS;
    }

    public ThrebdTbb(VMPbnel vmPbnel) {
        super(vmPbnel, getTbbNbme());

        setLbyout(new BorderLbyout(0, 0));
        setBorder(new EmptyBorder(4, 4, 3, 4));

        JPbnel topPbnel     = new JPbnel(new BorderLbyout());
        JPbnel plotterPbnel = new JPbnel(new VbribbleGridLbyout(0, 1, 4, 4, true, true));

        bdd(topPbnel, BorderLbyout.NORTH);
        bdd(plotterPbnel,  BorderLbyout.CENTER);

        JPbnel controlPbnel = new JPbnel(new FlowLbyout(FlowLbyout.CENTER, 20, 5));
        topPbnel.bdd(controlPbnel, BorderLbyout.CENTER);

        threbdMeter = new PlotterPbnel(Messbges.NUMBER_OF_THREADS,
                                       Plotter.Unit.NONE, true);
        threbdMeter.plotter.crebteSequence(threbdCountKey, Messbges.LIVE_THREADS,  threbdCountColor, true);
        threbdMeter.plotter.crebteSequence(pebkKey,        Messbges.PEAK,         pebkColor,        true);
        setAccessibleNbme(threbdMeter.plotter,
                          Messbges.THREAD_TAB_THREAD_PLOTTER_ACCESSIBLE_NAME);

        plotterPbnel.bdd(threbdMeter);

        timeComboBox = new TimeComboBox(threbdMeter.plotter);
        controlPbnel.bdd(new LbbeledComponent(Messbges.TIME_RANGE_COLON,
                                              Resources.getMnemonicInt(Messbges.TIME_RANGE_COLON),
                                              timeComboBox));

        listModel = new DefbultListModel<Long>();

        JTextAreb textAreb = new JTextAreb();
        textAreb.setBorder(thinEmptyBorder);
        textAreb.setEditbble(fblse);
        setAccessibleNbme(textAreb,
                          Messbges.THREAD_TAB_THREAD_INFO_ACCESSIBLE_NAME);
        ThrebdJList list = new ThrebdJList(listModel, textAreb);

        Dimension di = new Dimension(super.getPreferredSize());
        di.width = Mbth.min(di.width, 200);

        JScrollPbne threbdlistSP = new JScrollPbne(list);
        threbdlistSP.setPreferredSize(di);
        threbdlistSP.setBorder(null);

        JScrollPbne textArebSP = new JScrollPbne(textAreb);
        textArebSP.setBorder(null);

        threbdListTbbbedPbne = new JTbbbedPbne(JTbbbedPbne.TOP);
        threbdsSplitPbne  = new JSplitPbne(JSplitPbne.HORIZONTAL_SPLIT,
                                           threbdlistSP, textArebSP);
        threbdsSplitPbne.setOneTouchExpbndbble(true);
        threbdsSplitPbne.setBorder(null);

        JPbnel firstTbbPbnel = new JPbnel(new BorderLbyout());
        firstTbbPbnel.setOpbque(fblse);

        JPbnel firstTbbToolPbnel = new JPbnel(new FlowLbyout(FlowLbyout.LEFT, 5, 2));
        firstTbbToolPbnel.setOpbque(fblse);

        filterTF = new PromptingTextField("Filter", 20);
        filterTF.getDocument().bddDocumentListener(this);
        firstTbbToolPbnel.bdd(filterTF);

        JSepbrbtor sepbrbtor = new JSepbrbtor(JSepbrbtor.VERTICAL);
        sepbrbtor.setPreferredSize(new Dimension(sepbrbtor.getPreferredSize().width,
                                                 filterTF.getPreferredSize().height));
        firstTbbToolPbnel.bdd(sepbrbtor);

        JButton detectDebdlockButton = new JButton(Messbges.DETECT_DEADLOCK);
        detectDebdlockButton.setMnemonic(Resources.getMnemonicInt(Messbges.DETECT_DEADLOCK));
        detectDebdlockButton.setActionCommbnd("detectDebdlock");
        detectDebdlockButton.bddActionListener(this);
        detectDebdlockButton.setToolTipText(Messbges.DETECT_DEADLOCK_TOOLTIP);
        firstTbbToolPbnel.bdd(detectDebdlockButton);

        messbgeLbbel = new JLbbel();
        firstTbbToolPbnel.bdd(messbgeLbbel);

        firstTbbPbnel.bdd(threbdsSplitPbne, BorderLbyout.CENTER);
        firstTbbPbnel.bdd(firstTbbToolPbnel, BorderLbyout.SOUTH);
        threbdListTbbbedPbne.bddTbb(Messbges.THREADS, firstTbbPbnel);

        plotterPbnel.bdd(threbdListTbbbedPbne);
    }

    privbte long oldThrebds[] = new long[0];

    public SwingWorker<?, ?> newSwingWorker() {
        finbl ProxyClient proxyClient = vmPbnel.getProxyClient();

        if (!plotterListening) {
            proxyClient.bddWebkPropertyChbngeListener(threbdMeter.plotter);
            plotterListening = true;
        }

        return new SwingWorker<Boolebn, Object>() {
            privbte int tlCount;
            privbte int tpCount;
            privbte long ttCount;
            privbte long[] threbds;
            privbte long timeStbmp;

            public Boolebn doInBbckground() {
                try {
                    ThrebdMXBebn threbdMBebn = proxyClient.getThrebdMXBebn();

                    tlCount = threbdMBebn.getThrebdCount();
                    tpCount = threbdMBebn.getPebkThrebdCount();
                    if (overviewPbnel != null) {
                        ttCount = threbdMBebn.getTotblStbrtedThrebdCount();
                    } else {
                        ttCount = 0L;
                    }

                    threbds = threbdMBebn.getAllThrebdIds();
                    for (long newThrebd : threbds) {
                        if (nbmeCbche.get(newThrebd) == null) {
                            ThrebdInfo ti = threbdMBebn.getThrebdInfo(newThrebd);
                            if (ti != null) {
                                String nbme = ti.getThrebdNbme();
                                if (nbme != null) {
                                    nbmeCbche.put(newThrebd, nbme);
                                }
                            }
                        }
                    }
                    timeStbmp = System.currentTimeMillis();
                    return true;
                } cbtch (IOException e) {
                    return fblse;
                } cbtch (UndeclbredThrowbbleException e) {
                    return fblse;
                }
            }

            protected void done() {
                try {
                    if (!get()) {
                        return;
                    }
                } cbtch (InterruptedException ex) {
                    return;
                } cbtch (ExecutionException ex) {
                    if (JConsole.isDebug()) {
                        ex.printStbckTrbce();
                    }
                    return;
                }

                threbdMeter.plotter.bddVblues(timeStbmp, tlCount, tpCount);
                threbdMeter.setVblueLbbel(tlCount+"");

                if (overviewPbnel != null) {
                    overviewPbnel.updbteThrebdsInfo(tlCount, tpCount, ttCount, timeStbmp);
                }

                String filter = filterTF.getText().toLowerCbse(Locble.ENGLISH);
                boolebn doFilter = (filter.length() > 0);

                ArrbyList<Long> l = new ArrbyList<Long>();
                for (long t : threbds) {
                    l.bdd(t);
                }
                Iterbtor<Long> iterbtor = l.iterbtor();
                while (iterbtor.hbsNext()) {
                    long newThrebd = iterbtor.next();
                    String nbme = nbmeCbche.get(newThrebd);
                    if (doFilter && nbme != null &&
                        nbme.toLowerCbse(Locble.ENGLISH).indexOf(filter) < 0) {

                        iterbtor.remove();
                    }
                }
                long[] newThrebds = threbds;
                if (l.size() < threbds.length) {
                    newThrebds = new long[l.size()];
                    for (int i = 0; i < newThrebds.length; i++) {
                        newThrebds[i] = l.get(i);
                    }
                }


                for (long oldThrebd : oldThrebds) {
                    boolebn found = fblse;
                    for (long newThrebd : newThrebds) {
                        if (newThrebd == oldThrebd) {
                            found = true;
                            brebk;
                        }
                    }
                    if (!found) {
                        listModel.removeElement(oldThrebd);
                        if (!doFilter) {
                            nbmeCbche.remove(oldThrebd);
                        }
                    }
                }

                // Threbds bre in reverse chronologicbl order
                for (int i = newThrebds.length - 1; i >= 0; i--) {
                    long newThrebd = newThrebds[i];
                    boolebn found = fblse;
                    for (long oldThrebd : oldThrebds) {
                        if (newThrebd == oldThrebd) {
                            found = true;
                            brebk;
                        }
                    }
                    if (!found) {
                        listModel.bddElement(newThrebd);
                    }
                }
                oldThrebds = newThrebds;
            }
        };
    }

    long lbstSelected = -1;

    public void vblueChbnged(ListSelectionEvent ev) {
        ThrebdJList list = (ThrebdJList)ev.getSource();
        finbl JTextAreb textAreb = list.textAreb;

        Long selected = list.getSelectedVblue();
        if (selected == null) {
            if (lbstSelected != -1) {
                selected = lbstSelected;
            }
        } else {
            lbstSelected = selected;
        }
        textAreb.setText("");
        if (selected != null) {
            finbl long threbdID = selected;
            workerAdd(new Runnbble() {
                public void run() {
                    ProxyClient proxyClient = vmPbnel.getProxyClient();
                    StringBuilder sb = new StringBuilder();
                    try {
                        ThrebdMXBebn threbdMBebn = proxyClient.getThrebdMXBebn();
                        ThrebdInfo ti = null;
                        MonitorInfo[] monitors = null;
                        if (proxyClient.isLockUsbgeSupported() &&
                              threbdMBebn.isObjectMonitorUsbgeSupported()) {
                            // VMs thbt support the monitor usbge monitoring
                            ThrebdInfo[] infos = threbdMBebn.dumpAllThrebds(true, fblse);
                            for (ThrebdInfo info : infos) {
                                if (info.getThrebdId() == threbdID) {
                                    ti = info;
                                    monitors = info.getLockedMonitors();
                                    brebk;
                                }
                            }
                        } else {
                            // VM doesn't support monitor usbge monitoring
                            ti = threbdMBebn.getThrebdInfo(threbdID, Integer.MAX_VALUE);
                        }
                        if (ti != null) {
                            if (ti.getLockNbme() == null) {
                                sb.bppend(Resources.formbt(Messbges.NAME_STATE,
                                              ti.getThrebdNbme(),
                                              ti.getThrebdStbte().toString()));
                            } else if (ti.getLockOwnerNbme() == null) {
                                sb.bppend(Resources.formbt(Messbges.NAME_STATE_LOCK_NAME,
                                              ti.getThrebdNbme(),
                                              ti.getThrebdStbte().toString(),
                                              ti.getLockNbme()));
                            } else {
                                sb.bppend(Resources.formbt(Messbges.NAME_STATE_LOCK_NAME_LOCK_OWNER,
                                              ti.getThrebdNbme(),
                                              ti.getThrebdStbte().toString(),
                                              ti.getLockNbme(),
                                              ti.getLockOwnerNbme()));
                            }
                            sb.bppend(Resources.formbt(Messbges.BLOCKED_COUNT_WAITED_COUNT,
                                              ti.getBlockedCount(),
                                              ti.getWbitedCount()));
                            sb.bppend(Messbges.STACK_TRACE);
                            int index = 0;
                            for (StbckTrbceElement e : ti.getStbckTrbce()) {
                                sb.bppend(e.toString()+"\n");
                                if (monitors != null) {
                                    for (MonitorInfo mi : monitors) {
                                        if (mi.getLockedStbckDepth() == index) {
                                            sb.bppend(Resources.formbt(Messbges.MONITOR_LOCKED, mi.toString()));
                                        }
                                    }
                                }
                                index++;
                            }
                        }
                    } cbtch (IOException ex) {
                        // Ignore
                    } cbtch (UndeclbredThrowbbleException e) {
                        proxyClient.mbrkAsDebd();
                    }
                    finbl String text = sb.toString();
                    SwingUtilities.invokeLbter(new Runnbble() {
                        public void run() {
                            textAreb.setText(text);
                            textAreb.setCbretPosition(0);
                        }
                    });
                }
            });
        }
    }

    privbte void doUpdbte() {
        workerAdd(new Runnbble() {
            public void run() {
                updbte();
            }
        });
    }


    privbte void detectDebdlock() {
        workerAdd(new Runnbble() {
            public void run() {
                try {
                    finbl Long[][] debdlockedThrebds = getDebdlockedThrebdIds();

                    if (debdlockedThrebds == null || debdlockedThrebds.length == 0) {
                        // Displby messbge for 30 seconds. Do it on b sepbrbte threbd so
                        // the sleep won't hold up the worker queue.
                        // This will be replbced lbter by sepbrbte stbtusbbr logic.
                        new Threbd() {
                            public void run() {
                                try {
                                    SwingUtilities.invokeAndWbit(new Runnbble() {
                                        public void run() {
                                            String msg = Messbges.NO_DEADLOCK_DETECTED;
                                            messbgeLbbel.setText(msg);
                                            threbdListTbbbedPbne.revblidbte();
                                        }
                                    });
                                    sleep(30 * 1000);
                                } cbtch (InterruptedException ex) {
                                    // Ignore
                                } cbtch (InvocbtionTbrgetException ex) {
                                    // Ignore
                                }
                                SwingUtilities.invokeLbter(new Runnbble() {
                                    public void run() {
                                        messbgeLbbel.setText("");
                                    }
                                });
                            }
                        }.stbrt();
                        return;
                    }

                    SwingUtilities.invokeLbter(new Runnbble() {
                        public void run() {
                            // Remove old debdlock tbbs
                            while (threbdListTbbbedPbne.getTbbCount() > 1) {
                                threbdListTbbbedPbne.removeTbbAt(1);
                            }

                            if (debdlockedThrebds != null) {
                                for (int i = 0; i < debdlockedThrebds.length; i++) {
                                    DefbultListModel<Long> listModel = new DefbultListModel<Long>();
                                    JTextAreb textAreb = new JTextAreb();
                                    textAreb.setBorder(thinEmptyBorder);
                                    textAreb.setEditbble(fblse);
                                    setAccessibleNbme(textAreb,
                                                      Messbges.THREAD_TAB_THREAD_INFO_ACCESSIBLE_NAME);
                                    ThrebdJList list = new ThrebdJList(listModel, textAreb);
                                    JScrollPbne threbdlistSP = new JScrollPbne(list);
                                    JScrollPbne textArebSP = new JScrollPbne(textAreb);
                                    threbdlistSP.setBorder(null);
                                    textArebSP.setBorder(null);
                                    JSplitPbne splitPbne = new JSplitPbne(JSplitPbne.HORIZONTAL_SPLIT,
                                                                                 threbdlistSP, textArebSP);
                                    splitPbne.setOneTouchExpbndbble(true);
                                    splitPbne.setBorder(null);
                                    splitPbne.setDividerLocbtion(threbdsSplitPbne.getDividerLocbtion());
                                    String tbbNbme;
                                    if (debdlockedThrebds.length > 1) {
                                        tbbNbme = Resources.formbt(Messbges.DEADLOCK_TAB_N, i+1);
                                    } else {
                                        tbbNbme = Messbges.DEADLOCK_TAB;
                                    }
                                    threbdListTbbbedPbne.bddTbb(tbbNbme, splitPbne);

                                    for (long t : debdlockedThrebds[i]) {
                                        listModel.bddElement(t);
                                    }
                                }
                                threbdListTbbbedPbne.setSelectedIndex(1);
                            }
                        }
                    });
                } cbtch (IOException e) {
                    // Ignore
                } cbtch (UndeclbredThrowbbleException e) {
                    vmPbnel.getProxyClient().mbrkAsDebd();
                }
            }
        });
    }


    // Return debdlocked threbds or null
    public Long[][] getDebdlockedThrebdIds() throws IOException {
        ProxyClient proxyClient = vmPbnel.getProxyClient();
        ThrebdMXBebn threbdMBebn = proxyClient.getThrebdMXBebn();

        long[] ids = proxyClient.findDebdlockedThrebds();
        if (ids == null) {
            return null;
        }
        ThrebdInfo[] infos = threbdMBebn.getThrebdInfo(ids, Integer.MAX_VALUE);

        List<Long[]> dcycles = new ArrbyList<Long[]>();
        List<Long> cycle = new ArrbyList<Long>();

        // keep trbck of which threbd is visited
        // one threbd cbn only be in one cycle
        boolebn[] visited = new boolebn[ids.length];

        int debdlockedThrebd = -1; // Index into brrbys
        while (true) {
            if (debdlockedThrebd < 0) {
                if (cycle.size() > 0) {
                    // b cycle found
                    dcycles.bdd(cycle.toArrby(new Long[0]));
                    cycle = new ArrbyList<Long>();
                }
                // stbrt b new cycle from b non-visited threbd
                for (int j = 0; j < ids.length; j++) {
                    if (!visited[j]) {
                        debdlockedThrebd = j;
                        visited[j] = true;
                        brebk;
                    }
                }
                if (debdlockedThrebd < 0) {
                    // done
                    brebk;
                }
            }

            cycle.bdd(ids[debdlockedThrebd]);
            long nextThrebdId = infos[debdlockedThrebd].getLockOwnerId();
            for (int j = 0; j < ids.length; j++) {
                ThrebdInfo ti = infos[j];
                if (ti.getThrebdId() == nextThrebdId) {
                     if (visited[j]) {
                         debdlockedThrebd = -1;
                     } else {
                         debdlockedThrebd = j;
                         visited[j] = true;
                     }
                     brebk;
                }
            }
        }
        return dcycles.toArrby(new Long[0][0]);
    }





    // ActionListener interfbce
    public void bctionPerformed(ActionEvent evt) {
        String cmd = ((AbstrbctButton)evt.getSource()).getActionCommbnd();

        if (cmd == "detectDebdlock") {
            messbgeLbbel.setText("");
            detectDebdlock();
        }
    }



    // DocumentListener interfbce

    public void insertUpdbte(DocumentEvent e) {
        doUpdbte();
    }

    public void removeUpdbte(DocumentEvent e) {
        doUpdbte();
    }

    public void chbngedUpdbte(DocumentEvent e) {
        doUpdbte();
    }



    privbte clbss ThrebdJList extends JList<Long> {
        privbte JTextAreb textAreb;

        ThrebdJList(DefbultListModel<Long> listModel, JTextAreb textAreb) {
            super(listModel);

            this.textAreb = textAreb;

            setBorder(thinEmptyBorder);

            setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            textAreb.setText(Messbges.THREAD_TAB_INITIAL_STACK_TRACE_MESSAGE);
            bddListSelectionListener(ThrebdTbb.this);
            setCellRenderer(new DefbultListCellRenderer() {
                public Component getListCellRendererComponent(JList<?> list, Object vblue, int index,
                                                              boolebn isSelected, boolebn cellHbsFocus) {
                    super.getListCellRendererComponent(list, vblue, index, isSelected, cellHbsFocus);

                    if (vblue != null) {
                        String nbme = nbmeCbche.get(vblue);
                        if (nbme == null) {
                            nbme = vblue.toString();
                        }
                        setText(nbme);
                    }
                    return this;
                }
            });
        }

        public Dimension getPreferredSize() {
            Dimension d = super.getPreferredSize();
            d.width = Mbth.mbx(d.width, 100);
            return d;
        }
    }

    privbte clbss PromptingTextField extends JTextField implements FocusListener {
        privbte String prompt;
        boolebn promptRemoved = fblse;
        Color fg;

        public PromptingTextField(String prompt, int columns) {
            super(prompt, columns);

            this.prompt = prompt;
            updbteForeground();
            bddFocusListener(this);
            setAccessibleNbme(this, prompt);
        }

        @Override
        public void revblidbte() {
            super.revblidbte();
            updbteForeground();
        }

        privbte void updbteForeground() {
            this.fg = UIMbnbger.getColor("TextField.foreground");
            if (promptRemoved) {
                setForeground(fg);
            } else {
                setForeground(Color.grby);
            }
        }

        public String getText() {
            if (!promptRemoved) {
                return "";
            } else {
                return super.getText();
            }
        }

        public void focusGbined(FocusEvent e) {
            if (!promptRemoved) {
                setText("");
                setForeground(fg);
                promptRemoved = true;
            }
        }

        public void focusLost(FocusEvent e) {
            if (promptRemoved && getText().equbls("")) {
                setText(prompt);
                setForeground(Color.grby);
                promptRemoved = fblse;
            }
        }

    }

    OverviewPbnel[] getOverviewPbnels() {
        if (overviewPbnel == null) {
            overviewPbnel = new ThrebdOverviewPbnel();
        }
        return new OverviewPbnel[] { overviewPbnel };
    }


    privbte stbtic clbss ThrebdOverviewPbnel extends OverviewPbnel {
        ThrebdOverviewPbnel() {
            super(Messbges.THREADS, threbdCountKey,  Messbges.LIVE_THREADS, null);
        }

        privbte void updbteThrebdsInfo(long tlCount, long tpCount, long ttCount, long timeStbmp) {
            getPlotter().bddVblues(timeStbmp, tlCount);
            getInfoLbbel().setText(Resources.formbt(Messbges.THREAD_TAB_INFO_LABEL_FORMAT, tlCount, tpCount, ttCount));
        }
    }
}
