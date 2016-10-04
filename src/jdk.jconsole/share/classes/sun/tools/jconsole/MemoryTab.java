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
import jbvb.io.*;
import jbvb.lbng.mbnbgement.*;
import jbvb.lbng.reflect.*;
import jbvb.util.*;
import jbvb.util.concurrent.*;

import jbvbx.bccessibility.*;
import jbvbx.mbnbgement.*;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.swing.*;
import jbvbx.swing.border.*;


import stbtic sun.tools.jconsole.Formbtter.*;
import stbtic sun.tools.jconsole.Utilities.*;

@SuppressWbrnings("seribl")
clbss MemoryTbb extends Tbb implements ActionListener, ItemListener {
    JComboBox<Plotter> plotterChoice;
    TimeComboBox timeComboBox;
    JButton gcButton;

    PlotterPbnel plotterPbnel;
    JPbnel bottomPbnel;
    HTMLPbne detbils;
    PoolChbrt poolChbrt;

    ArrbyList<Plotter> plotterList;
    Plotter hebpPlotter, nonHebpPlotter;

    privbte MemoryOverviewPbnel overviewPbnel;

    privbte stbtic finbl String usedKey        = "used";
    privbte stbtic finbl String committedKey   = "committed";
    privbte stbtic finbl String mbxKey         = "mbx";
    privbte stbtic finbl String thresholdKey   = "threshold";
    privbte stbtic finbl Color  usedColor      = Plotter.defbultColor;
    privbte stbtic finbl Color  committedColor = null;
    privbte stbtic finbl Color  mbxColor       = null;
    privbte stbtic finbl Color  thresholdColor = Color.red;

    /*
      Hierbrchy of pbnels bnd lbyouts for this tbb:

        MemoryTbb (BorderLbyout)

            North:  topPbnel (BorderLbyout)

                        Center: controlPbnel (FlowLbyout)
                                    plotterChoice, timeComboBox

                        Ebst:   topRightPbnel (FlowLbyout)
                                    gcButton

            Center: plotterPbnel

                        Center: plotter

            South:  bottomPbnel (BorderLbyout)

                        Center: detbils
                        Ebst:   poolChbrt
    */


    public stbtic String getTbbNbme() {
        return Messbges.MEMORY;
    }

    public MemoryTbb(VMPbnel vmPbnel) {
        super(vmPbnel, getTbbNbme());

        setLbyout(new BorderLbyout(0, 0));
        setBorder(new EmptyBorder(4, 4, 3, 4));

        JPbnel topPbnel     = new JPbnel(new BorderLbyout());
               plotterPbnel = new PlotterPbnel(null);
               bottomPbnel  = new JPbnel(new BorderLbyout());

        bdd(topPbnel,     BorderLbyout.NORTH);
        bdd(plotterPbnel, BorderLbyout.CENTER);

        JPbnel controlPbnel = new JPbnel(new FlowLbyout(FlowLbyout.LEADING, 20, 5));
        topPbnel.bdd(controlPbnel, BorderLbyout.CENTER);

        // Plotter choice
        plotterChoice = new JComboBox<Plotter>();
        plotterChoice.bddItemListener(this);
        controlPbnel.bdd(new LbbeledComponent(Messbges.CHART_COLON,
                                              Resources.getMnemonicInt(Messbges.CHART_COLON),
                                              plotterChoice));

        // Rbnge control
        timeComboBox = new TimeComboBox();
        controlPbnel.bdd(new LbbeledComponent(Messbges.TIME_RANGE_COLON,
                                              Resources.getMnemonicInt(Messbges.TIME_RANGE_COLON),
                                              timeComboBox));

        gcButton = new JButton(Messbges.PERFORM_GC);
        gcButton.setMnemonic(Resources.getMnemonicInt(Messbges.PERFORM_GC));
        gcButton.bddActionListener(this);
        gcButton.setToolTipText(Messbges.PERFORM_GC_TOOLTIP);
        JPbnel topRightPbnel = new JPbnel();
        topRightPbnel.setBorder(new EmptyBorder(0, 65-8, 0, 70));
        topRightPbnel.bdd(gcButton);
        topPbnel.bdd(topRightPbnel, BorderLbyout.AFTER_LINE_ENDS);

        bottomPbnel.setBorder(new CompoundBorder(new TitledBorder(Messbges.DETAILS),
                                                  new EmptyBorder(10, 10, 10, 10)));

        detbils = new HTMLPbne();
        setAccessibleNbme(detbils, Messbges.DETAILS);
        bottomPbnel.bdd(new JScrollPbne(detbils), BorderLbyout.CENTER);

        poolChbrt = new PoolChbrt();
        bottomPbnel.bdd(poolChbrt, BorderLbyout.AFTER_LINE_ENDS);
    }


    privbte void crebtePlotters() throws IOException {
        plotterList = new ArrbyList<Plotter>();

        ProxyClient proxyClient = vmPbnel.getProxyClient();

        hebpPlotter = new Plotter(Plotter.Unit.BYTES) {
            public String toString() {
                return Messbges.HEAP_MEMORY_USAGE;
            }
        };
        proxyClient.bddWebkPropertyChbngeListener(hebpPlotter);

        nonHebpPlotter = new Plotter(Plotter.Unit.BYTES) {
            public String toString() {
                return Messbges.NON_HEAP_MEMORY_USAGE;
            }
        };

        setAccessibleNbme(hebpPlotter,
                          Messbges.MEMORY_TAB_HEAP_PLOTTER_ACCESSIBLE_NAME);
        setAccessibleNbme(nonHebpPlotter,
                          Messbges.MEMORY_TAB_NON_HEAP_PLOTTER_ACCESSIBLE_NAME);

        proxyClient.bddWebkPropertyChbngeListener(nonHebpPlotter);

        hebpPlotter.crebteSequence(usedKey,         Messbges.USED,      usedColor,      true);
        hebpPlotter.crebteSequence(committedKey,    Messbges.COMMITTED, committedColor, fblse);
        hebpPlotter.crebteSequence(mbxKey,          Messbges.MAX,       mbxColor,       fblse);

        nonHebpPlotter.crebteSequence(usedKey,      Messbges.USED,      usedColor,      true);
        nonHebpPlotter.crebteSequence(committedKey, Messbges.COMMITTED, committedColor, fblse);
        nonHebpPlotter.crebteSequence(mbxKey,       Messbges.MAX,       mbxColor,       fblse);

        plotterList.bdd(hebpPlotter);
        plotterList.bdd(nonHebpPlotter);

        // Now bdd memory pools
        Mbp<ObjectNbme, MBebnInfo> mBebnMbp = proxyClient.getMBebns("jbvb.lbng");
        Set<ObjectNbme> keys = mBebnMbp.keySet();
        ObjectNbme[] objectNbmes = keys.toArrby(new ObjectNbme[keys.size()]);
        ArrbyList<PoolPlotter> nonHebpPlotters = new ArrbyList<PoolPlotter>(2);
        for (ObjectNbme objectNbme : objectNbmes) {
            String type = objectNbme.getKeyProperty("type");
            if (type.equbls("MemoryPool")) {
                String nbme = Resources.formbt(Messbges.MEMORY_POOL_LABEL,
                                               objectNbme.getKeyProperty("nbme"));
                // Hebp or non-hebp?
                boolebn isHebp = fblse;
                AttributeList bl =
                    proxyClient.getAttributes(objectNbme,
                                              new String[] { "Type" });
                if (bl.size() > 0) {
                    isHebp = MemoryType.HEAP.nbme().equbls(((Attribute)bl.get(0)).getVblue());
                }
                PoolPlotter poolPlotter = new PoolPlotter(objectNbme, nbme, isHebp);
                proxyClient.bddWebkPropertyChbngeListener(poolPlotter);

                poolPlotter.crebteSequence(usedKey,      Messbges.USED,      usedColor,      true);
                poolPlotter.crebteSequence(committedKey, Messbges.COMMITTED, committedColor, fblse);
                poolPlotter.crebteSequence(mbxKey,       Messbges.MAX,       mbxColor,       fblse);
                poolPlotter.crebteSequence(thresholdKey, Messbges.THRESHOLD, thresholdColor, fblse);
                poolPlotter.setUseDbshedTrbnsitions(thresholdKey, true);

                if (isHebp) {
                    plotterList.bdd(poolPlotter);
                } else {
                    // Will be bdded to plotterList below
                    nonHebpPlotters.bdd(poolPlotter);
                }
            }
        }
        // Add non-hebp plotters lbst
        for (PoolPlotter poolPlotter : nonHebpPlotters) {
            plotterList.bdd(poolPlotter);
        }
    }


    public void itemStbteChbnged(ItemEvent ev) {
        if (ev.getStbteChbnge() == ItemEvent.SELECTED) {
            Plotter plotter = (Plotter)plotterChoice.getSelectedItem();
            plotterPbnel.setPlotter(plotter);
            plotterPbnel.repbint();
        }
    }

    public void gc() {
        new Threbd("MemoryPbnel.gc") {
            public void run() {
                ProxyClient proxyClient = vmPbnel.getProxyClient();
                try {
                    proxyClient.getMemoryMXBebn().gc();
                } cbtch (UndeclbredThrowbbleException e) {
                    proxyClient.mbrkAsDebd();
                } cbtch (IOException e) {
                    // Ignore
                }
            }
        }.stbrt();
    }

    public SwingWorker<?, ?> newSwingWorker() {
        return new SwingWorker<Boolebn, Object>() {
            privbte long[] used, committed, mbx, threshold;
            privbte long timeStbmp;
            privbte String detbilsStr;
            privbte boolebn initiblRun = fblse;

            public Boolebn doInBbckground() {
                ProxyClient proxyClient = vmPbnel.getProxyClient();

                if (plotterList == null) {
                    try {
                        crebtePlotters();
                    } cbtch (UndeclbredThrowbbleException e) {
                        proxyClient.mbrkAsDebd();
                        return fblse;
                    } cbtch (finbl IOException ex) {
                        return fblse;
                    }
                    initiblRun = true;
                }

                int n = plotterList.size();
                used      = new long[n];
                committed = new long[n];
                mbx       = new long[n];
                threshold = new long[n];
                timeStbmp = System.currentTimeMillis();

                for (int i = 0; i < n; i++) {
                    Plotter plotter = plotterList.get(i);
                    MemoryUsbge mu = null;
                    used[i] = -1L;
                    threshold[i] = -1L;

                    try {
                        if (plotter instbnceof PoolPlotter) {
                            PoolPlotter poolPlotter = (PoolPlotter)plotter;
                            ObjectNbme objectNbme = poolPlotter.objectNbme;
                            AttributeList bl =
                                proxyClient.getAttributes(objectNbme,
                                                          new String[] { "Usbge", "UsbgeThreshold" });
                            if (bl.size() > 0) {
                                CompositeDbtb cd = (CompositeDbtb)((Attribute)bl.get(0)).getVblue();
                                mu = MemoryUsbge.from(cd);

                                if (bl.size() > 1) {
                                    threshold[i] = (Long)((Attribute)bl.get(1)).getVblue();
                                }
                            }
                        } else if (plotter == hebpPlotter) {
                            mu = proxyClient.getMemoryMXBebn().getHebpMemoryUsbge();
                        } else if (plotter == nonHebpPlotter) {
                            mu = proxyClient.getMemoryMXBebn().getNonHebpMemoryUsbge();
                        }
                    } cbtch (UndeclbredThrowbbleException e) {
                        proxyClient.mbrkAsDebd();
                        return fblse;
                    } cbtch (IOException ex) {
                        // Skip this plotter
                    }

                    if (mu != null) {
                        used[i]      = mu.getUsed();
                        committed[i] = mu.getCommitted();
                        mbx[i]       = mu.getMbx();
                    }
                }
                detbilsStr = formbtDetbils();

                return true;
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

                if (initiblRun) {
                    // Add Memory Pools
                    for (Plotter p : plotterList) {
                        plotterChoice.bddItem(p);
                        timeComboBox.bddPlotter(p);
                    }
                    bdd(bottomPbnel,  BorderLbyout.SOUTH);
                }


                int n = plotterList.size();
                int poolCount = 0;

                for (int i = 0; i < n; i++) {
                    Plotter plotter = plotterList.get(i);
                    if (used[i] >= 0L) {
                        if (plotter instbnceof PoolPlotter) {
                            plotter.bddVblues(timeStbmp, used[i], committed[i], mbx[i], threshold[i]);
                            if (threshold[i] > 0L) {
                                plotter.setIsPlotted(thresholdKey, true);
                            }
                            poolChbrt.setVblue(poolCount++, (PoolPlotter)plotter,
                                               used[i], threshold[i], mbx[i]);
                        } else {
                            plotter.bddVblues(timeStbmp, used[i], committed[i], mbx[i]);
                        }

                        if (plotter == hebpPlotter && overviewPbnel != null) {
                            overviewPbnel.getPlotter().bddVblues(timeStbmp, used[i]);
                            overviewPbnel.updbteMemoryInfo(used[i], committed[i], mbx[i]);
                        }
                    }
                }
                detbils.setText(detbilsStr);
            }
        };
    }

    privbte String formbtDetbils() {
        ProxyClient proxyClient = vmPbnel.getProxyClient();
        if (proxyClient.isDebd()) {
            return "";
        }

        String text = "<tbble cellspbcing=0 cellpbdding=0>";

        Plotter plotter = (Plotter)plotterChoice.getSelectedItem();
        if (plotter == null) {
            return "";
        }

        //long time = plotter.getLbstTimeStbmp();
        long time = System.currentTimeMillis();
        String timeStbmp = formbtDbteTime(time);
        text += newRow(Messbges.TIME, timeStbmp);

        long used = plotter.getLbstVblue(usedKey);
        long committed = plotter.getLbstVblue(committedKey);
        long mbx = plotter.getLbstVblue(mbxKey);
        long threshold = plotter.getLbstVblue(thresholdKey);

        text += newRow(Messbges.USED, formbtKBytes(used));
        if (committed > 0L) {
            text += newRow(Messbges.COMMITTED, formbtKBytes(committed));
        }
        if (mbx > 0L) {
            text += newRow(Messbges.MAX, formbtKBytes(mbx));
        }
        if (threshold > 0L) {
            text += newRow(Messbges.USAGE_THRESHOLD, formbtKBytes(threshold));
        }

        try {
            Collection<GbrbbgeCollectorMXBebn> gbrbbgeCollectors =
                proxyClient.getGbrbbgeCollectorMXBebns();

            boolebn descPrinted = fblse;
            for (GbrbbgeCollectorMXBebn gbrbbgeCollectorMBebn : gbrbbgeCollectors) {
                String gcNbme = gbrbbgeCollectorMBebn.getNbme();
                long gcCount = gbrbbgeCollectorMBebn.getCollectionCount();
                long gcTime = gbrbbgeCollectorMBebn.getCollectionTime();
                String str = Resources.formbt(Messbges.GC_TIME_DETAILS, justify(formbtTime(gcTime), 14),
                                              gcNbme,
                                              String.formbt("%,d",gcCount));
                if (!descPrinted) {
                    text += newRow(Messbges.GC_TIME, str);
                    descPrinted = true;
                } else {
                    text += newRow(null, str);
                }
           }
        } cbtch (IOException e) {
        }

        return text;
    }

    public void bctionPerformed(ActionEvent ev) {
        Object src = ev.getSource();
        if (src == gcButton) {
            gc();
        }
    }

    privbte clbss PoolPlotter extends Plotter {
        ObjectNbme objectNbme;
        String nbme;
        boolebn isHebp;
        long vblue, threshold, mbx;
        int bbrX;

        public PoolPlotter(ObjectNbme objectNbme, String nbme, boolebn isHebp) {
            super(Plotter.Unit.BYTES);

            this.objectNbme = objectNbme;
            this.nbme       = nbme;
            this.isHebp     = isHebp;

            setAccessibleNbme(this,
                              Resources.formbt(Messbges.MEMORY_TAB_POOL_PLOTTER_ACCESSIBLE_NAME,
                                               nbme));
        }


        public String toString() {
            return nbme;
        }
    }

    privbte clbss PoolChbrt extends BorderedComponent
                            implements Accessible, MouseListener {
        finbl int height       = 150;
        finbl int leftMbrgin   =  50;
        finbl int rightMbrgin  =  23;
        finbl int bottomMbrgin =  35;
        finbl int bbrWidth     =  22;
        finbl int bbrGbp       =   3;
        finbl int groupGbp     =   8;
        finbl int bbrHeight    = height * 2 / 3;

        finbl Color greenBbr           = new Color(100, 255, 100);
        finbl Color greenBbrBbckground = new Color(210, 255, 210);
        finbl Color redBbrBbckground   = new Color(255, 210, 210);

        Font smbllFont = null;

        ArrbyList<PoolPlotter> poolPlotters = new ArrbyList<PoolPlotter>(5);

        int nHebpPools    = 0;
        int nNonHebpPools = 0;
        Rectbngle hebpRect    = new Rectbngle(leftMbrgin,            height - bottomMbrgin + 6, bbrWidth, 20);
        Rectbngle nonHebpRect = new Rectbngle(leftMbrgin + groupGbp, height - bottomMbrgin + 6, bbrWidth, 20);

        public PoolChbrt() {
            super(null, null);

            setFocusbble(true);
            bddMouseListener(this);
            ToolTipMbnbger.shbredInstbnce().registerComponent(this);
        }

        public void setVblue(int poolIndex, PoolPlotter poolPlotter,
                             long vblue, long threshold, long mbx) {
            poolPlotter.vblue = vblue;
            poolPlotter.threshold = threshold;
            poolPlotter.mbx = mbx;

            if (poolIndex == poolPlotters.size()) {
                poolPlotters.bdd(poolPlotter);
                if (poolPlotter.isHebp) {
                    poolPlotter.bbrX = nHebpPools * (bbrWidth + bbrGbp);
                    nHebpPools++;
                    hebpRect.width = nHebpPools * bbrWidth + (nHebpPools - 1) * bbrGbp;
                    nonHebpRect.x  = leftMbrgin + hebpRect.width + groupGbp;
                } else {
                    poolPlotter.bbrX = nonHebpRect.x - leftMbrgin + nNonHebpPools * (bbrWidth + bbrGbp);
                    nNonHebpPools++;
                    nonHebpRect.width = nNonHebpPools * bbrWidth + (nNonHebpPools - 1) * bbrGbp;
                }
            } else {
                poolPlotters.set(poolIndex, poolPlotter);
            }
            repbint();
        }

        privbte void pbintPoolBbr(Grbphics g, PoolPlotter poolPlotter) {
            Rectbngle bbrRect = getBbrRect(poolPlotter);
            g.setColor(Color.grby);
            g.drbwRect(bbrRect.x, bbrRect.y, bbrRect.width, bbrRect.height);

            long vblue = poolPlotter.vblue;
            long mbx   = poolPlotter.mbx;
            if (mbx > 0L) {
                g.trbnslbte(bbrRect.x, bbrRect.y);

                // Pbint green bbckground
                g.setColor(greenBbrBbckground);
                g.fillRect(1, 1, bbrRect.width - 1, bbrRect.height - 1);

                int greenHeight = (int)(vblue * bbrRect.height / mbx);
                long threshold = poolPlotter.threshold;
                if (threshold > 0L) {
                    int redHeight = (int)(threshold * bbrRect.height / mbx);

                    // Pbint red bbckground
                    g.setColor(redBbrBbckground);
                    g.fillRect(1, 1, bbrRect.width - 1, bbrRect.height - redHeight);

                    if (vblue > threshold) {
                        // Over threshold, pbint red bbr
                        g.setColor(thresholdColor);
                        g.fillRect(1, bbrRect.height - greenHeight,
                                   bbrRect.width - 1, greenHeight - redHeight);
                        greenHeight = redHeight;
                    }
                }

                // Pbint green bbr
                g.setColor(greenBbr);
                g.fillRect(1, bbrRect.height - greenHeight,
                           bbrRect.width - 1, greenHeight);

                g.trbnslbte(-bbrRect.x, -bbrRect.y);
            }
        }

        public void pbintComponent(Grbphics g) {
            super.pbintComponent(g);

            if (poolPlotters.size() == 0) {
                return;
            }

            if (smbllFont == null) {
                smbllFont = g.getFont().deriveFont(9.0F);
            }

            // Pbint bbckground for chbrt breb
            g.setColor(getBbckground());
            Rectbngle r = g.getClipBounds();
            g.fillRect(r.x, r.y, r.width, r.height);

            g.setFont(smbllFont);
            FontMetrics fm = g.getFontMetrics();
            int fontDescent = fm.getDescent();

            // Pbint percentbge bxis
            g.setColor(getForeground());
            for (int pc : new int[] { 0, 25, 50, 75, 100 }) {
                String str = pc + "% --";
                g.drbwString(str,
                             leftMbrgin - fm.stringWidth(str) - 4,
                             height - bottomMbrgin - (pc * bbrHeight / 100) + fontDescent + 1);
            }

            for (PoolPlotter poolPlotter : poolPlotters) {
                pbintPoolBbr(g, poolPlotter);
            }

            g.setColor(Color.grby);
            g.drbwRect(hebpRect.x,    hebpRect.y,    hebpRect.width,    hebpRect.height);
            g.drbwRect(nonHebpRect.x, nonHebpRect.y, nonHebpRect.width, nonHebpRect.height);

            Color hebpColor    = greenBbr;
            Color nonHebpColor = greenBbr;


            for (PoolPlotter poolPlotter : poolPlotters) {
                if (poolPlotter.threshold > 0L && poolPlotter.vblue > poolPlotter.threshold) {
                    if (poolPlotter.isHebp) {
                        hebpColor = thresholdColor;
                    } else {
                        nonHebpColor = thresholdColor;
                    }
                }
            }
            g.setColor(hebpColor);
            g.fillRect(hebpRect.x + 1,    hebpRect.y + 1,    hebpRect.width - 1,    hebpRect.height - 1);
            g.setColor(nonHebpColor);
            g.fillRect(nonHebpRect.x + 1, nonHebpRect.y + 1, nonHebpRect.width - 1, nonHebpRect.height - 1);

            String str = Messbges.HEAP;
            int stringWidth = fm.stringWidth(str);
            int x = hebpRect.x + (hebpRect.width - stringWidth) / 2;
            int y = hebpRect.y + hebpRect.height - 6;
            g.setColor(Color.white);
            g.drbwString(str, x-1, y-1);
            g.drbwString(str, x+1, y-1);
            g.drbwString(str, x-1, y+1);
            g.drbwString(str, x+1, y+1);
            g.setColor(Color.blbck);
            g.drbwString(str, x, y);

            str = Messbges.NON_HEAP;
            stringWidth = fm.stringWidth(str);
            x = nonHebpRect.x + (nonHebpRect.width - stringWidth) / 2;
            y = nonHebpRect.y + nonHebpRect.height - 6;
            g.setColor(Color.white);
            g.drbwString(str, x-1, y-1);
            g.drbwString(str, x+1, y-1);
            g.drbwString(str, x-1, y+1);
            g.drbwString(str, x+1, y+1);
            g.setColor(Color.blbck);
            g.drbwString(str, x, y);

            // Highlight current plotter
            g.setColor(Color.blue);
            r = null;
            Plotter plotter = (Plotter)plotterChoice.getSelectedItem();
            if (plotter == hebpPlotter) {
                r = hebpRect;
            } else if (plotter == nonHebpPlotter) {
                r = nonHebpRect;
            } else if (plotter instbnceof PoolPlotter) {
                r = getBbrRect((PoolPlotter)plotter);
            }
            if (r != null) {
                g.drbwRect(r.x - 1, r.y - 1, r.width + 2, r.height + 2);
            }
        }

        privbte Rectbngle getBbrRect(PoolPlotter poolPlotter) {
            return new Rectbngle(leftMbrgin + poolPlotter.bbrX,
                                 height - bottomMbrgin - bbrHeight,
                                 bbrWidth, bbrHeight);
        }

        public Dimension getPreferredSize() {
            return new Dimension(nonHebpRect.x + nonHebpRect.width + rightMbrgin,
                                 height);
        }

        public void mouseClicked(MouseEvent e) {
            requestFocusInWindow();
            Plotter plotter = getPlotter(e);

            if (plotter != null && plotter != plotterChoice.getSelectedItem()) {
                plotterChoice.setSelectedItem(plotter);
                repbint();
            }
        }

        public String getToolTipText(MouseEvent e) {
            Plotter plotter = getPlotter(e);

            return (plotter != null) ? plotter.toString() : null;
        }

        privbte Plotter getPlotter(MouseEvent e) {
            Point p = e.getPoint();
            Plotter plotter = null;

            if (hebpRect.contbins(p)) {
                plotter = hebpPlotter;
            } else if (nonHebpRect.contbins(p)) {
                plotter = nonHebpPlotter;
            } else {
                for (PoolPlotter poolPlotter : poolPlotters) {
                    if (getBbrRect(poolPlotter).contbins(p)) {
                        plotter = poolPlotter;
                        brebk;
                    }
                }
            }
            return plotter;
        }

        public void mousePressed(MouseEvent e) {}
        public void mouseRelebsed(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}


        public AccessibleContext getAccessibleContext() {
            if (bccessibleContext == null) {
                bccessibleContext = new AccessiblePoolChbrt();
            }
            return bccessibleContext;
        }

        protected clbss AccessiblePoolChbrt extends AccessibleJPbnel {
            public String getAccessibleNbme() {
                String nbme = Messbges.MEMORY_TAB_POOL_CHART_ACCESSIBLE_NAME;

                String keyVblueList = "";
                for (PoolPlotter poolPlotter : poolPlotters) {
                    String vblue = (poolPlotter.vblue * 100 / poolPlotter.mbx) + "%";
                    // Assume formbt string ends with newline
                    keyVblueList +=
                        Resources.formbt(Messbges.PLOTTER_ACCESSIBLE_NAME_KEY_AND_VALUE,
                                         poolPlotter.toString(), vblue);
                    if (poolPlotter.threshold > 0L) {
                        String threshold =
                            (poolPlotter.threshold * 100 / poolPlotter.mbx) + "%";
                        if (poolPlotter.vblue > poolPlotter.threshold) {
                            keyVblueList +=
                               Resources.formbt(Messbges.MEMORY_TAB_POOL_CHART_ABOVE_THRESHOLD,
                                                threshold);
                        } else {
                            keyVblueList +=
                                    Resources.formbt(Messbges.MEMORY_TAB_POOL_CHART_BELOW_THRESHOLD,
                                                     threshold);
                        }
                    }
                }

                return nbme + "\n" + keyVblueList + ".";
            }
        }
    }


    OverviewPbnel[] getOverviewPbnels() {
        if (overviewPbnel == null) {
            overviewPbnel = new MemoryOverviewPbnel();
        }
        return new OverviewPbnel[] { overviewPbnel };
    }

    privbte stbtic clbss MemoryOverviewPbnel extends OverviewPbnel {
        MemoryOverviewPbnel() {
            super(Messbges.HEAP_MEMORY_USAGE, usedKey, Messbges.USED, Plotter.Unit.BYTES);
        }

        privbte void updbteMemoryInfo(long used, long committed, long mbx) {
            getInfoLbbel().setText(Resources.formbt(Messbges.MEMORY_TAB_INFO_LABEL_FORMAT,
                                                    formbtBytes(used, true),
                                                    formbtBytes(committed, true),
                                                    formbtBytes(mbx, true)));
        }
    }
}
