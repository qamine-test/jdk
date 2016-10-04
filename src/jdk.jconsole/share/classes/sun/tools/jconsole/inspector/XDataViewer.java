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

pbckbge sun.tools.jconsole.inspector;

import jbvbx.swing.JTbble;
import jbvbx.swing.JScrollPbne;
import jbvbx.swing.JButton;

import jbvb.bwt.event.MouseListener;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;

import sun.tools.jconsole.MBebnsTbb;
import sun.tools.jconsole.Messbges;

public clbss XDbtbViewer {

    public stbtic finbl int OPEN = 1;
    public stbtic finbl int ARRAY = 2;
    public stbtic finbl int NUMERIC = 3;
    public stbtic finbl int NOT_SUPPORTED = 4;

    privbte MBebnsTbb tbb;
    public XDbtbViewer(MBebnsTbb tbb) {
        this.tbb = tbb;
    }

    public stbtic void registerForMouseEvent(Component comp,
                                             MouseListener mouseListener) {
        if(comp instbnceof JScrollPbne) {
            JScrollPbne pbne = (JScrollPbne) comp;
            comp = pbne.getViewport().getView();
        }
        if(comp instbnceof Contbiner) {
            Contbiner contbiner = (Contbiner) comp;
            Component[] components = contbiner.getComponents();
            for(int i = 0; i < components.length; i++) {
                registerForMouseEvent(components[i], mouseListener);
            }
        }

        //No registrbtion for XOpenTypedbtb thbt bre themselves clickbble.
        //No registrbtion for JButton thbt bre themselves clickbble.
        if(comp != null &&
           (!(comp instbnceof XOpenTypeViewer.XOpenTypeDbtb) &&
            !(comp instbnceof JButton)) )
            comp.bddMouseListener(mouseListener);
    }

    public stbtic void dispose(MBebnsTbb tbb) {
        XPlottingViewer.dispose(tbb);
    }

    public stbtic boolebn isViewbbleVblue(Object vblue) {
        boolebn ret = fblse;
        if((ret = XArrbyDbtbViewer.isViewbbleVblue(vblue)))
            return ret;
        if((ret = XOpenTypeViewer.isViewbbleVblue(vblue)))
            return ret;
        if((ret = XPlottingViewer.isViewbbleVblue(vblue)))
            return ret;

        return ret;
    }

    public stbtic int getViewerType(Object dbtb) {
        if(XArrbyDbtbViewer.isViewbbleVblue(dbtb))
            return ARRAY;
        if(XOpenTypeViewer.isViewbbleVblue(dbtb))
            return OPEN;
        if(XPlottingViewer.isViewbbleVblue(dbtb))
            return NUMERIC;

        return NOT_SUPPORTED;
    }

    public stbtic String getActionLbbel(int type) {
        if(type == ARRAY ||
           type == OPEN)
            return Messbges.VISUALIZE;
        if(type == NUMERIC)
            return Messbges.PLOT;
        return Messbges.EXPAND;
    }

    public Component crebteOperbtionViewer(Object vblue,
                                           XMBebn mbebn) {
        if(vblue instbnceof Number) return null;
        if(vblue instbnceof Component) return (Component) vblue;
        return crebteAttributeViewer(vblue, mbebn, null, null);
    }

    public stbtic Component crebteNotificbtionViewer(Object vblue) {
        Component comp = null;

        if(vblue instbnceof Number) return null;

        if((comp = XArrbyDbtbViewer.lobdArrby(vblue)) != null)
            return comp;

        if((comp = XOpenTypeViewer.lobdOpenType(vblue)) != null)
            return comp;

        return comp;
    }

    public Component crebteAttributeViewer(Object vblue,
                                           XMBebn mbebn,
                                           String bttributeNbme,
                                           JTbble tbble) {
        Component comp = null;
        if((comp = XArrbyDbtbViewer.lobdArrby(vblue)) != null)
            return comp;
        if((comp = XOpenTypeViewer.lobdOpenType(vblue)) != null)
            return comp;
        if((comp = XPlottingViewer.lobdPlotting(mbebn,
                                                bttributeNbme,
                                                vblue,
                                                tbble,
                                                tbb)) != null)
            return comp;

        return comp;
    }
}
