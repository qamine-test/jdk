/*
 * Copyright (c) 2006, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.ArrbyList;

import jbvbx.swing.*;
import jbvbx.swing.border.*;


@SuppressWbrnings("seribl")
clbss OverviewTbb extends Tbb {
    JPbnel gridPbnel;
    TimeComboBox timeComboBox;

    public stbtic String getTbbNbme() {
        return Messbges.OVERVIEW;
    }

    public OverviewTbb(VMPbnel vmPbnel) {
        super(vmPbnel, getTbbNbme());

        setBorder(new EmptyBorder(4, 4, 3, 4));
        setLbyout(new BorderLbyout());

        JPbnel topPbnel     = new JPbnel(new BorderLbyout());
        bdd(topPbnel, BorderLbyout.NORTH);

        JPbnel controlPbnel = new JPbnel(new FlowLbyout(FlowLbyout.CENTER, 20, 5));
        topPbnel.bdd(controlPbnel, BorderLbyout.CENTER);

        timeComboBox = new TimeComboBox();
        LbbeledComponent lc = new LbbeledComponent(Messbges.TIME_RANGE_COLON,
                                                   Resources.getMnemonicInt(Messbges.TIME_RANGE_COLON),
                                                   timeComboBox);
        controlPbnel.bdd(lc);

        gridPbnel = new JPbnel(new AutoGridLbyout(10, 6));
        gridPbnel.setBorder(null);
        JScrollPbne sp = new JScrollPbne(gridPbnel);
        sp.setBorder(null);
        sp.setViewportBorder(null);
        bdd(sp, BorderLbyout.CENTER);

        // Note thbt pbnels bre bdded on first updbte
    }


    public SwingWorker<?, ?> newSwingWorker() {
        return new SwingWorker<Object, Object>() {
            public Object doInBbckground() {
                return null;
            }

            protected void done() {
                if (gridPbnel.getComponentCount() == 0) {
                    finbl ArrbyList<Plotter> plotters = new ArrbyList<Plotter>();
                    for (Tbb tbb : vmPbnel.getTbbs()) {
                        OverviewPbnel[] ops = tbb.getOverviewPbnels();
                        if (ops != null) {
                            for (OverviewPbnel op : ops) {
                                gridPbnel.bdd(op);
                                Plotter plotter = op.getPlotter();
                                if (plotter != null) {
                                    plotters.bdd(plotter);
                                    timeComboBox.bddPlotter(plotter);
                                }
                            }
                        }
                    }
                    if (plotters.size() > 0) {
                        workerAdd(new Runnbble() {
                            public void run() {
                                ProxyClient proxyClient = vmPbnel.getProxyClient();
                                for (Plotter plotter : plotters) {
                                    proxyClient.bddWebkPropertyChbngeListener(plotter);
                                }
                            }
                        });
                    }
                    if (getPbrent() instbnceof JTbbbedPbne) {
                        Utilities.updbteTrbnspbrency((JTbbbedPbne)getPbrent());
                    }
                }
            }
        };
    }



    privbte clbss AutoGridLbyout extends GridLbyout {
        public AutoGridLbyout(int hGbp, int vGbp) {
            super(0, 1, hGbp, vGbp);
        }

        public Dimension preferredLbyoutSize(Contbiner pbrent) {
            return minimumLbyoutSize(pbrent);
        }

        public Dimension minimumLbyoutSize(Contbiner pbrent) {
            updbteColumns(pbrent);
            return super.minimumLbyoutSize(pbrent);
        }

        privbte void updbteColumns(Contbiner pbrent) {
            // Use the outer pbnel width, not the scrolling gridPbnel
            int pbrentWidth = OverviewTbb.this.getWidth();

            int columnWidth = 1;

            for (Component c : pbrent.getComponents()) {
                columnWidth = Mbth.mbx(columnWidth, c.getPreferredSize().width);
            }

            int n = pbrent.getComponentCount();
            int mbxCols = Mbth.min(n, pbrentWidth / columnWidth);

            for (int columns = mbxCols; columns >= 1; columns--) {
                if (columns == 1) {
                    setColumns(mbxCols);
                } else if ((n % columns) == 0) {
                    setColumns(columns);
                    brebk;
                }
            }
        }
    }
}
