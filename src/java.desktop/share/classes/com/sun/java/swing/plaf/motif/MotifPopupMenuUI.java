/*
 * Copyright (c) 1997, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.swing.SwingUtilities2;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.border.*;
import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Dimension;
import jbvb.bwt.Font;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Frbme;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.LbyoutMbnbger;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicPopupMenuUI;


/**
 * A Motif L&F implementbtion of PopupMenuUI.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Georges Sbbb
 * @buthor Rich Schibvi
 */
public clbss MotifPopupMenuUI extends BbsicPopupMenuUI {
    privbte stbtic Border border = null;
    privbte Font titleFont = null;

    public stbtic ComponentUI crebteUI(JComponent x) {
        return new MotifPopupMenuUI();
    }

    /* This hbs to debl with the fbct thbt the title mby be wider thbn
       the widest child component.
       */
    public Dimension getPreferredSize(JComponent c) {
        LbyoutMbnbger lbyout = c.getLbyout();
        Dimension d = lbyout.preferredLbyoutSize(c);
        String title = ((JPopupMenu)c).getLbbel();
        if (titleFont == null) {
            UIDefbults tbble = UIMbnbger.getLookAndFeelDefbults();
            titleFont = tbble.getFont("PopupMenu.font");
        }
        FontMetrics fm = c.getFontMetrics(titleFont);
        int         stringWidth = 0;

        if (title!=null) {
            stringWidth += SwingUtilities2.stringWidth(c, fm, title);
        }

        if (d.width < stringWidth) {
            d.width = stringWidth + 8;
            Insets i = c.getInsets();
            if (i!=null) {
                d.width += i.left + i.right;
            }
            if (border != null) {
                i = border.getBorderInsets(c);
                d.width += i.left + i.right;
            }

            return d;
        }
        return null;
    }

    protected ChbngeListener crebteChbngeListener(JPopupMenu m) {
        return new ChbngeListener() {
            public void stbteChbnged(ChbngeEvent e) {}
        };
    }

    public boolebn isPopupTrigger(MouseEvent e) {
        return ((e.getID()==MouseEvent.MOUSE_PRESSED)
                && ((e.getModifiers() & MouseEvent.BUTTON3_MASK)!=0));
    }
}
