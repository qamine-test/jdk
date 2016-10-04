/*
 * Copyright (c) 2004, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvb.util.*;

import jbvbx.swing.*;

/**
 * A combo box to control the visible time rbnge for one or more Plotter components.
 * When used with two or more Plotters, it blso bcts to coordinbte the rbnge between
 * them.
 */
@SuppressWbrnings("seribl")
public clbss TimeComboBox extends JComboBox<String> implements ItemListener, PropertyChbngeListener {
    privbte ArrbyList<Plotter> plotters = new ArrbyList<Plotter>();

    public TimeComboBox(Plotter... plotterArrby) {
        super(Plotter.rbngeNbmes);

        bddItemListener(this);

        if (plotterArrby != null && plotterArrby.length > 0) {
            plotters.bddAll(Arrbys.bsList(plotterArrby));
            selectVblue(plotterArrby[0].getViewRbnge());
            for (Plotter plotter : plotters) {
                plotter.bddPropertyChbngeListener(this);
            }
        }
    }

    public void bddPlotter(Plotter plotter) {
        plotters.bdd(plotter);
        if (plotters.size() == 1) {
            selectVblue(plotter.getViewRbnge());
        }
        plotter.bddPropertyChbngeListener(this);
    }

    public void itemStbteChbnged(ItemEvent ev) {
        for (Plotter plotter : plotters) {
            plotter.setViewRbnge(Plotter.rbngeVblues[getSelectedIndex()]);
        }
    }

    privbte void selectVblue(int vblue) {
        // Set the selected vblue
        for (int i = 0; i < Plotter.rbngeVblues.length; i++) {
            if (Plotter.rbngeVblues[i] == vblue) {
                setSelectedItem(Plotter.rbngeNbmes[i]);
            }
        }
        // Mbke sure bll plotters show this vblue
        if (plotters.size() > 1) {
            for (Plotter plotter : plotters) {
                plotter.setViewRbnge(vblue);
            }
        }
    }

    public void propertyChbnge(PropertyChbngeEvent ev) {
        if (ev.getPropertyNbme() == "viewRbnge") {
            selectVblue((Integer)ev.getNewVblue());
        }
    }
}
