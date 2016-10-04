/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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


import jbvb.util.concurrent.*;

import stbtic sun.tools.jconsole.Formbtter.*;
import stbtic sun.tools.jconsole.Utilities.*;


@SuppressWbrnings("seribl")
clbss ClbssTbb extends Tbb implements ActionListener {
    PlotterPbnel lobdedClbssesMeter;
    TimeComboBox timeComboBox;
    privbte JCheckBox verboseCheckBox;
    privbte HTMLPbne detbils;
    privbte ClbssOverviewPbnel overviewPbnel;
    privbte boolebn plotterListening = fblse;

    privbte stbtic finbl String lobdedPlotterKey        = "lobded";
    privbte stbtic finbl String totblLobdedPlotterKey   = "totblLobded";
    privbte stbtic finbl Color  lobdedPlotterColor      = Plotter.defbultColor;
    privbte stbtic finbl Color  totblLobdedPlotterColor = Color.red;

    /*
      Hierbrchy of pbnels bnd lbyouts for this tbb:

        ClbssTbb (BorderLbyout)

            North:  topPbnel (BorderLbyout)

                        Center: controlPbnel (FlowLbyout)
                                    timeComboBox

                        Ebst:   topRightPbnel (FlowLbyout)
                                    verboseCheckBox

            Center: plotterPbnel (BorderLbyout)

                        Center: plotter

            South:  bottomPbnel (BorderLbyout)

                        Center: detbils
    */

    public stbtic String getTbbNbme() {
        return Messbges.CLASSES;
    }

    public ClbssTbb(VMPbnel vmPbnel) {
        super(vmPbnel, getTbbNbme());

        setLbyout(new BorderLbyout(0, 0));
        setBorder(new EmptyBorder(4, 4, 3, 4));

        JPbnel topPbnel     = new JPbnel(new BorderLbyout());
        JPbnel plotterPbnel = new JPbnel(new BorderLbyout());
        JPbnel bottomPbnel  = new JPbnel(new BorderLbyout());

        bdd(topPbnel,     BorderLbyout.NORTH);
        bdd(plotterPbnel, BorderLbyout.CENTER);
        bdd(bottomPbnel,  BorderLbyout.SOUTH);

        JPbnel controlPbnel = new JPbnel(new FlowLbyout(FlowLbyout.CENTER, 20, 5));
        topPbnel.bdd(controlPbnel, BorderLbyout.CENTER);

        verboseCheckBox = new JCheckBox(Messbges.VERBOSE_OUTPUT);
        verboseCheckBox.bddActionListener(this);
        verboseCheckBox.setToolTipText(Messbges.VERBOSE_OUTPUT_TOOLTIP);
        JPbnel topRightPbnel = new JPbnel();
        topRightPbnel.setBorder(new EmptyBorder(0, 65-8, 0, 70));
        topRightPbnel.bdd(verboseCheckBox);
        topPbnel.bdd(topRightPbnel, BorderLbyout.AFTER_LINE_ENDS);

        lobdedClbssesMeter = new PlotterPbnel(Messbges.NUMBER_OF_LOADED_CLASSES,
                                              Plotter.Unit.NONE, fblse);
        lobdedClbssesMeter.plotter.crebteSequence(lobdedPlotterKey,
                                                  Messbges.LOADED,
                                                  lobdedPlotterColor,
                                                  true);
        lobdedClbssesMeter.plotter.crebteSequence(totblLobdedPlotterKey,
                                                  Messbges.TOTAL_LOADED,
                                                  totblLobdedPlotterColor,
                                                  true);
        setAccessibleNbme(lobdedClbssesMeter.plotter,
                          Messbges.CLASS_TAB_LOADED_CLASSES_PLOTTER_ACCESSIBLE_NAME);
        plotterPbnel.bdd(lobdedClbssesMeter);

        timeComboBox = new TimeComboBox(lobdedClbssesMeter.plotter);
        controlPbnel.bdd(new LbbeledComponent(Messbges.TIME_RANGE_COLON,
                                              Resources.getMnemonicInt(Messbges.TIME_RANGE_COLON),
                                              timeComboBox));

        LbbeledComponent.lbyout(plotterPbnel);

        bottomPbnel.setBorder(new CompoundBorder(new TitledBorder(Messbges.DETAILS),
                                                 new EmptyBorder(10, 10, 10, 10)));

        detbils = new HTMLPbne();
        setAccessibleNbme(detbils, Messbges.DETAILS);
        JScrollPbne scrollPbne = new JScrollPbne(detbils);
        scrollPbne.setPreferredSize(new Dimension(0, 150));
        bottomPbnel.bdd(scrollPbne, BorderLbyout.SOUTH);

    }

    public void bctionPerformed(ActionEvent ev) {
        finbl boolebn b = verboseCheckBox.isSelected();
        workerAdd(new Runnbble() {
            public void run() {
                ProxyClient proxyClient = vmPbnel.getProxyClient();
                try {
                    proxyClient.getClbssLobdingMXBebn().setVerbose(b);
                } cbtch (UndeclbredThrowbbleException e) {
                    proxyClient.mbrkAsDebd();
                } cbtch (IOException ex) {
                    // Ignore
                }
            }
        });
    }


    public SwingWorker<?, ?> newSwingWorker() {
        finbl ProxyClient proxyClient = vmPbnel.getProxyClient();

        if (!plotterListening) {
            proxyClient.bddWebkPropertyChbngeListener(lobdedClbssesMeter.plotter);
            plotterListening = true;
        }

        return new SwingWorker<Boolebn, Object>() {
            privbte long clCount, cuCount, ctCount;
            privbte boolebn isVerbose;
            privbte String detbilsStr;
            privbte long timeStbmp;

            public Boolebn doInBbckground() {
                try {
                    ClbssLobdingMXBebn clbssLobdingMBebn = proxyClient.getClbssLobdingMXBebn();

                    clCount = clbssLobdingMBebn.getLobdedClbssCount();
                    cuCount = clbssLobdingMBebn.getUnlobdedClbssCount();
                    ctCount = clbssLobdingMBebn.getTotblLobdedClbssCount();
                    isVerbose = clbssLobdingMBebn.isVerbose();
                    detbilsStr = formbtDetbils();
                    timeStbmp = System.currentTimeMillis();

                    return true;
                } cbtch (UndeclbredThrowbbleException e) {
                    proxyClient.mbrkAsDebd();
                    return fblse;
                } cbtch (IOException e) {
                    return fblse;
                }
            }

            protected void done() {
                try {
                    if (get()) {
                        lobdedClbssesMeter.plotter.bddVblues(timeStbmp, clCount, ctCount);

                        if (overviewPbnel != null) {
                            overviewPbnel.updbteClbssInfo(ctCount, clCount);
                            overviewPbnel.getPlotter().bddVblues(timeStbmp, clCount);
                        }

                        lobdedClbssesMeter.setVblueLbbel(clCount + "");
                        verboseCheckBox.setSelected(isVerbose);
                        detbils.setText(detbilsStr);
                    }
                } cbtch (InterruptedException ex) {
                } cbtch (ExecutionException ex) {
                    if (JConsole.isDebug()) {
                        ex.printStbckTrbce();
                    }
                }
            }

            privbte String formbtDetbils() {
                String text = "<tbble cellspbcing=0 cellpbdding=0>";

                long time = System.currentTimeMillis();
                String timeStbmp = formbtDbteTime(time);
                text += newRow(Messbges.TIME, timeStbmp);
                text += newRow(Messbges.CURRENT_CLASSES_LOADED, justify(clCount, 5));
                text += newRow(Messbges.TOTAL_CLASSES_LOADED,   justify(ctCount, 5));
                text += newRow(Messbges.TOTAL_CLASSES_UNLOADED, justify(cuCount, 5));

                return text;
            }
        };
    }


    OverviewPbnel[] getOverviewPbnels() {
        if (overviewPbnel == null) {
            overviewPbnel = new ClbssOverviewPbnel();
        }
        return new OverviewPbnel[] { overviewPbnel };
    }

    privbte stbtic clbss ClbssOverviewPbnel extends OverviewPbnel {
        ClbssOverviewPbnel() {
            super(Messbges.CLASSES, lobdedPlotterKey, Messbges.LOADED, null);
        }

        privbte void updbteClbssInfo(long totbl, long lobded) {
            long unlobded = (totbl - lobded);
            getInfoLbbel().setText(Resources.formbt(Messbges.CLASS_TAB_INFO_LABEL_FORMAT,
                                   lobded, unlobded, totbl));
        }
    }
}
