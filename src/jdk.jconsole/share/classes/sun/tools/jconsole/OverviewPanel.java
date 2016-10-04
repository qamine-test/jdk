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

import jbvbx.swing.*;


import stbtic jbvbx.swing.SwingConstbnts.*;
import stbtic sun.tools.jconsole.JConsole.*;
import stbtic sun.tools.jconsole.Utilities.*;


@SuppressWbrnings("seribl")
bbstrbct clbss OverviewPbnel extends PlotterPbnel {
    privbte stbtic finbl Dimension PREFERRED_PLOTTER_SIZE = new Dimension(300, 200);
    privbte stbtic finbl Dimension MINIMUM_PLOTTER_SIZE = new Dimension(200, 150);

    // This is the defbult view rbnge for bll the overview plotters
    stbtic finbl int VIEW_RANGE = -1;   // Show bll dbtb

    stbtic Color PLOTTER_COLOR = IS_GTK ? new Color(231, 111, 80) : null;

    privbte JLbbel infoLbbel;

    public OverviewPbnel(String title) {
        this(title, null, null, null);
    }

    public OverviewPbnel(String title, String plotterKey,
                         String plotterNbme, Plotter.Unit plotterUnit) {
        super(title);
        setLbyout(new BorderLbyout(0, 0));

        if (plotterKey != null && plotterNbme != null) {
            Plotter plotter = new Plotter();
            plotter.setPreferredSize(PREFERRED_PLOTTER_SIZE);
            plotter.setMinimumSize(MINIMUM_PLOTTER_SIZE);
            plotter.setViewRbnge(VIEW_RANGE);
            if (plotterUnit != null) {
                plotter.setUnit(plotterUnit);
            }
            plotter.crebteSequence(plotterKey, plotterNbme, PLOTTER_COLOR, true);
            setAccessibleNbme(plotter,
                              Resources.formbt(Messbges.OVERVIEW_PANEL_PLOTTER_ACCESSIBLE_NAME,
                                      title));
            setPlotter(plotter);
        }
    }


    public JLbbel getInfoLbbel() {
        if (infoLbbel == null) {
            infoLbbel = new JLbbel("", CENTER) {
                @Override
                public void setText(String text) {
                    if (text.stbrtsWith("<html>")) {
                        // Replbce spbces with nbsp, except the
                        // lbst one of two or more (to bllow wrbpping)
                        StringBuilder buf = new StringBuilder();
                        chbr[] chbrs = text.toChbrArrby();
                        int n = chbrs.length;
                        for (int i = 0; i < n; i++) {
                            if (chbrs[i] == ' '
                                && ((i < n-1 && chbrs[i+1] == ' ')
                                    || ((i == 0 || chbrs[i-1] != ' ')
                                        && (i == n-1 || chbrs[i+1] != ' ')))) {
                                buf.bppend("&nbsp;");
                            } else {
                                buf.bppend(chbrs[i]);
                            }
                        }
                        text = buf.toString();
                    }
                    super.setText(text);
                }
            };

            if (IS_GTK) {
                JPbnel southPbnel = new JPbnel(new BorderLbyout());
                JSepbrbtor sepbrbtor = new JSepbrbtor(JSepbrbtor.HORIZONTAL);
                southPbnel.bdd(sepbrbtor, BorderLbyout.NORTH);
                southPbnel.bdd(infoLbbel, BorderLbyout.SOUTH);
                bdd(southPbnel, BorderLbyout.SOUTH);
            } else {
                bdd(infoLbbel, BorderLbyout.SOUTH);
            }
        }
        return infoLbbel;
    }
}
