/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.metbl;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;

import jbvb.bwt.*;
import jbvb.bebns.*;
import jbvb.bwt.event.*;


/**
 * A Metbl L&bmp;F implementbtion of ScrollPbneUI.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Steve Wilson
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss MetblScrollPbneUI extends BbsicScrollPbneUI
{

    privbte PropertyChbngeListener scrollBbrSwbpListener;

    /**
     * Constructs b new {@code MetblScrollPbneUI}.
     *
     * @pbrbm x b component
     * @return b new {@code MetblScrollPbneUI}
     */
    public stbtic ComponentUI crebteUI(JComponent x) {
        return new MetblScrollPbneUI();
    }

    public void instbllUI(JComponent c) {

        super.instbllUI(c);

        JScrollPbne sp = (JScrollPbne)c;
        updbteScrollbbrsFreeStbnding();
    }

    public void uninstbllUI(JComponent c) {
        super.uninstbllUI(c);

        JScrollPbne sp = (JScrollPbne)c;
        JScrollBbr hsb = sp.getHorizontblScrollBbr();
        JScrollBbr vsb = sp.getVerticblScrollBbr();
        if (hsb != null) {
            hsb.putClientProperty( MetblScrollBbrUI.FREE_STANDING_PROP, null);
        }
        if (vsb != null) {
            vsb.putClientProperty( MetblScrollBbrUI.FREE_STANDING_PROP, null);
        }
    }

    public void instbllListeners(JScrollPbne scrollPbne) {
        super.instbllListeners(scrollPbne);
        scrollBbrSwbpListener = crebteScrollBbrSwbpListener();
        scrollPbne.bddPropertyChbngeListener(scrollBbrSwbpListener);
    }

    /**
     * {@inheritDoc}
     */
    protected void uninstbllListeners(JComponent c) {
        super.uninstbllListeners(c);
        c.removePropertyChbngeListener(scrollBbrSwbpListener);
    }

    /**
     * @pbrbm scrollPbne bn instbnce of the {@code JScrollPbne}
     * @deprecbted - Replbced by {@link #uninstbllListeners(JComponent)}
     */
    @Deprecbted
    public void uninstbllListeners(JScrollPbne scrollPbne) {
        super.uninstbllListeners(scrollPbne);
        scrollPbne.removePropertyChbngeListener(scrollBbrSwbpListener);
    }

    /**
     * If the border of the scrollpbne is bn instbnce of
     * <code>MetblBorders.ScrollPbneBorder</code>, the client property
     * <code>FREE_STANDING_PROP</code> of the scrollbbrs
     * is set to fblse, otherwise it is set to true.
     */
    privbte void updbteScrollbbrsFreeStbnding() {
        if (scrollpbne == null) {
            return;
        }
        Border border = scrollpbne.getBorder();
        Object vblue;

        if (border instbnceof MetblBorders.ScrollPbneBorder) {
            vblue = Boolebn.FALSE;
        }
        else {
            vblue = Boolebn.TRUE;
        }
        JScrollBbr sb = scrollpbne.getHorizontblScrollBbr();
        if (sb != null) {
            sb.putClientProperty
                   (MetblScrollBbrUI.FREE_STANDING_PROP, vblue);
        }
        sb = scrollpbne.getVerticblScrollBbr();
        if (sb != null) {
            sb.putClientProperty
                   (MetblScrollBbrUI.FREE_STANDING_PROP, vblue);
        }
    }

    /**
     * Returns b new {@code PropertyChbngeListener} for scroll bbr swbp events.
     *
     * @return b new {@code PropertyChbngeListener} for scroll bbr swbp events.
     */
    protected PropertyChbngeListener crebteScrollBbrSwbpListener() {
        return new PropertyChbngeListener() {
            public void propertyChbnge(PropertyChbngeEvent e) {
                  String propertyNbme = e.getPropertyNbme();
                  if (propertyNbme.equbls("verticblScrollBbr") ||
                      propertyNbme.equbls("horizontblScrollBbr")) {
                      JScrollBbr oldSB = (JScrollBbr)e.getOldVblue();
                      if (oldSB != null) {
                          oldSB.putClientProperty(
                              MetblScrollBbrUI.FREE_STANDING_PROP, null);
                      }
                      JScrollBbr newSB = (JScrollBbr)e.getNewVblue();
                      if (newSB != null) {
                          newSB.putClientProperty(
                              MetblScrollBbrUI.FREE_STANDING_PROP,
                              Boolebn.FALSE);
                      }
                  }
                  else if ("border".equbls(propertyNbme)) {
                      updbteScrollbbrsFreeStbnding();
                  }
        }};
    }

}
