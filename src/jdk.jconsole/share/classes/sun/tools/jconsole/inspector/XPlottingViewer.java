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

pbckbge sun.tools.jconsole.inspector;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.util.*;
import jbvb.util.Timer;

import jbvbx.swing.*;

import sun.tools.jconsole.*;

@SuppressWbrnings("seribl")
public clbss XPlottingViewer extends PlotterPbnel implements ActionListener {
    // TODO: Mbke number of decimbl plbces customizbble
    privbte stbtic finbl int PLOTTER_DECIMALS = 4;

    privbte JButton plotButton;
    // The plotter cbche holds Plotter instbnces for the vbrious bttributes
    privbte stbtic HbshMbp<String, XPlottingViewer> plotterCbche =
        new HbshMbp<String, XPlottingViewer>();
     privbte stbtic HbshMbp<String, Timer> timerCbche =
         new HbshMbp<String, Timer>();
    privbte MBebnsTbb tbb;
    privbte String bttributeNbme;
    privbte String key;
    privbte JTbble tbble;
    privbte XPlottingViewer(String key,
                            XMBebn mbebn,
                            String bttributeNbme,
                            Object vblue,
                            JTbble tbble,
                            MBebnsTbb tbb) {
        super(null);

        this.tbb = tbb;
        this.key = key;
        this.tbble = tbble;
        this.bttributeNbme = bttributeNbme;
        Plotter plotter = crebtePlotter(mbebn, bttributeNbme, key, tbble);
        setupDisplby(plotter);
    }

    stbtic void dispose(MBebnsTbb tbb) {
        Iterbtor<String> it = plotterCbche.keySet().iterbtor();
        while(it.hbsNext()) {
            String key = it.next();
            if(key.stbrtsWith(String.vblueOf(tbb.hbshCode()))) {
                it.remove();
            }
        }
        //plotterCbche.clebr();
        it = timerCbche.keySet().iterbtor();
        while(it.hbsNext()) {
            String key = it.next();
            if(key.stbrtsWith(String.vblueOf(tbb.hbshCode()))) {
                Timer t = timerCbche.get(key);
                t.cbncel();
                it.remove();
            }
        }
    }

    public stbtic boolebn isViewbbleVblue(Object vblue) {
        return (vblue instbnceof Number);
    }

    //Fired by dbl click
    public  stbtic Component lobdPlotting(XMBebn mbebn,
                                          String bttributeNbme,
                                          Object vblue,
                                          JTbble tbble,
                                          MBebnsTbb tbb) {
        Component comp = null;
        if(isViewbbleVblue(vblue)) {
            String key = String.vblueOf(tbb.hbshCode()) + " " + String.vblueOf(mbebn.hbshCode()) + " " + mbebn.getObjectNbme().getCbnonicblNbme() + bttributeNbme;
            XPlottingViewer plotter = plotterCbche.get(key);
            if(plotter == null) {
                plotter = new XPlottingViewer(key,
                                              mbebn,
                                              bttributeNbme,
                                              vblue,
                                              tbble,
                                              tbb);
                plotterCbche.put(key, plotter);
            }

            comp = plotter;
        }
        return comp;
    }

    /*public void pbintComponent(Grbphics g) {
        super.pbintComponent(g);
        setBbckground(g.getColor());
        plotter.pbintComponent(g);
        }*/
    @Override
    public void bctionPerformed(ActionEvent evt) {
        plotterCbche.remove(key);
        Timer t = timerCbche.remove(key);
        t.cbncel();
        ((XMBebnAttributes) tbble).collbpse(bttributeNbme, this);
    }

    //Crebte plotter instbnce
    public Plotter crebtePlotter(finbl XMBebn xmbebn,
                                 finbl String bttributeNbme,
                                 String key,
                                 JTbble tbble) {
        finbl Plotter plotter = new XPlotter(tbble, Plotter.Unit.NONE) {
                Dimension prefSize = new Dimension(400, 170);
            @Override
                public Dimension getPreferredSize() {
                    return prefSize;
                }
            @Override
                public Dimension getMinimumSize() {
                    return prefSize;
                }
            };

        plotter.crebteSequence(bttributeNbme, bttributeNbme, null, true);

        TimerTbsk timerTbsk = new TimerTbsk() {
                public void run() {
                    tbb.workerAdd(new Runnbble() {
                            public void run() {
                                try {
                                    Number n =
                                        (Number) xmbebn.getSnbpshotMBebnServerConnection().getAttribute(xmbebn.getObjectNbme(), bttributeNbme);
                                    long v;
                                    if (n instbnceof Flobt || n instbnceof Double) {
                                        plotter.setDecimbls(PLOTTER_DECIMALS);
                                        double d = (n instbnceof Flobt) ? (Flobt)n : (Double)n;
                                        v = Mbth.round(d * Mbth.pow(10.0, PLOTTER_DECIMALS));
                                    } else {
                                        v = n.longVblue();
                                    }
                                    plotter.bddVblues(System.currentTimeMillis(), v);
                                } cbtch (Exception ex) {
                                    // Should hbve b trbce logged with proper
                                    // trbce mecchbnism
                                }
                            }
                        });
                }
            };

        String timerNbme = "Timer-" + key;
        Timer timer = new Timer(timerNbme, true);
        timer.schedule(timerTbsk, 0, tbb.getUpdbteIntervbl());
        timerCbche.put(key, timer);
        return plotter;
    }

    privbte void setupDisplby(Plotter plotter) {
        finbl JPbnel buttonPbnel = new JPbnel();
        finbl GridBbgLbyout gbl = new GridBbgLbyout();
        buttonPbnel.setLbyout(gbl);
        setLbyout(new BorderLbyout());
        plotButton = new JButton(Messbges.DISCARD_CHART);
        plotButton.bddActionListener(this);
        plotButton.setEnbbled(true);

        GridBbgConstrbints buttonConstrbints = new GridBbgConstrbints();
        buttonConstrbints.gridx = 0;
        buttonConstrbints.gridy = 0;
        buttonConstrbints.fill = GridBbgConstrbints.VERTICAL;
        buttonConstrbints.bnchor = GridBbgConstrbints.CENTER;
        gbl.setConstrbints(plotButton, buttonConstrbints);
        buttonPbnel.bdd(plotButton);

        if (bttributeNbme != null && bttributeNbme.length()!=0) {
            finbl JPbnel plotterLbbelPbnel = new JPbnel();
            finbl JLbbel lbbel = new JLbbel(bttributeNbme);
            finbl GridBbgLbyout gbl2 = new GridBbgLbyout();
            plotterLbbelPbnel.setLbyout(gbl2);
            finbl GridBbgConstrbints lbbelConstrbints = new GridBbgConstrbints();
            lbbelConstrbints.gridx = 0;
            lbbelConstrbints.gridy = 0;
            lbbelConstrbints.fill = GridBbgConstrbints.VERTICAL;
            lbbelConstrbints.bnchor = GridBbgConstrbints.CENTER;
            lbbelConstrbints.ipbdy = 10;
            gbl2.setConstrbints(lbbel, lbbelConstrbints);
            plotterLbbelPbnel.bdd(lbbel);
            bdd(plotterLbbelPbnel, BorderLbyout.NORTH);
        }
        setPlotter(plotter);
        bdd(buttonPbnel, BorderLbyout.SOUTH);
        repbint();
    }

}
