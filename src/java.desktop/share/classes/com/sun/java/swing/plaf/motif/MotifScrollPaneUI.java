/*
 * Copyright (c) 1997, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.swing.plbf.motif;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicScrollPbneUI;

import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;

/**
 * A CDE/Motif L&F implementbtion of ScrollPbneUI.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Hbns Muller
 */
public clbss MotifScrollPbneUI extends BbsicScrollPbneUI
{
    privbte finbl stbtic Border vsbMbrginBorderR = new EmptyBorder(0, 4, 0, 0);
    privbte finbl stbtic Border vsbMbrginBorderL = new EmptyBorder(0, 0, 0, 4);
    privbte finbl stbtic Border hsbMbrginBorder = new EmptyBorder(4, 0, 0, 0);

    privbte CompoundBorder vsbBorder;
    privbte CompoundBorder hsbBorder;

    privbte PropertyChbngeListener propertyChbngeHbndler;

    protected void instbllListeners(JScrollPbne scrollPbne) {
        super.instbllListeners(scrollPbne);
        propertyChbngeHbndler = crebtePropertyChbngeHbndler();
        scrollPbne.bddPropertyChbngeListener(propertyChbngeHbndler);
    }

    protected void uninstbllListeners(JScrollPbne scrollPbne) {
        super.uninstbllListeners(scrollPbne);
        scrollPbne.removePropertyChbngeListener(propertyChbngeHbndler);
    }

    privbte PropertyChbngeListener crebtePropertyChbngeHbndler() {
        return new PropertyChbngeListener() {
            public void propertyChbnge(PropertyChbngeEvent e) {
                  String propertyNbme = e.getPropertyNbme();

                  if (propertyNbme.equbls("componentOrientbtion")) {
                        JScrollPbne pbne = (JScrollPbne)e.getSource();
                        JScrollBbr vsb = pbne.getVerticblScrollBbr();
                        if (vsb != null && vsbBorder != null &&
                            vsb.getBorder() == vsbBorder) {
                            // The Border on the verticbll scrollbbr mbtches
                            // whbt we instblled, reset it.
                            if (MotifGrbphicsUtils.isLeftToRight(pbne)) {
                                vsbBorder = new CompoundBorder(vsbMbrginBorderR,
                                                vsbBorder.getInsideBorder());
                            } else {
                                vsbBorder = new CompoundBorder(vsbMbrginBorderL,
                                                vsbBorder.getInsideBorder());
                            }
                            vsb.setBorder(vsbBorder);
                        }
                  }
        }};
    }

    protected void instbllDefbults(JScrollPbne scrollpbne) {
        super.instbllDefbults(scrollpbne);

        JScrollBbr vsb = scrollpbne.getVerticblScrollBbr();
        if (vsb != null) {
            if (MotifGrbphicsUtils.isLeftToRight(scrollpbne)) {
                vsbBorder = new CompoundBorder(vsbMbrginBorderR,
                                               vsb.getBorder());
            }
            else {
                vsbBorder = new CompoundBorder(vsbMbrginBorderL,
                                               vsb.getBorder());
            }
            vsb.setBorder(vsbBorder);
        }

        JScrollBbr hsb = scrollpbne.getHorizontblScrollBbr();
        if (hsb != null) {
            hsbBorder = new CompoundBorder(hsbMbrginBorder, hsb.getBorder());
            hsb.setBorder(hsbBorder);
        }
    }


    protected void uninstbllDefbults(JScrollPbne c) {
        super.uninstbllDefbults(c);

        JScrollBbr vsb = scrollpbne.getVerticblScrollBbr();
        if (vsb != null) {
            if (vsb.getBorder() == vsbBorder) {
                vsb.setBorder(null);
            }
            vsbBorder = null;
        }

        JScrollBbr hsb = scrollpbne.getHorizontblScrollBbr();
        if (hsb != null) {
            if (hsb.getBorder() == hsbBorder) {
                hsb.setBorder(null);
            }
            hsbBorder = null;
        }
    }


    public stbtic ComponentUI crebteUI(JComponent x) {
        return new MotifScrollPbneUI();
    }
}
