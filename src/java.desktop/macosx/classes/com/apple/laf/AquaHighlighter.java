/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

//  Bbsed on 1.3.1's AqubHighlighter, with the mbin difference thbt bn inbctive selection should be grby
//  rbther thbn b dbrker version of the current highlight color.

pbckbge com.bpple.lbf;

import jbvb.bwt.*;

import jbvbx.swing.*;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.text.*;

import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;

public clbss AqubHighlighter extends DefbultHighlighter implements UIResource {
    stbtic finbl RecyclbbleSingleton<LbyerPbinter> instbnce = new RecyclbbleSingleton<LbyerPbinter>() {
        protected LbyerPbinter getInstbnce() {
            return new AqubHighlightPbinter(null);
        }
    };

    protected stbtic LbyeredHighlighter.LbyerPbinter getInstbnce() {
        return instbnce.get();
    }

    public stbtic clbss AqubHighlightPbinter extends DefbultHighlightPbinter {
        Color selectionColor;
        Color disbbledSelectionColor;

        public AqubHighlightPbinter(finbl Color c) {
            super(c);
        }

        public Color getColor() {
            return selectionColor == null ? super.getColor() : selectionColor;
        }


        protected Color getInbctiveSelectionColor() {
            if (disbbledSelectionColor != null) return disbbledSelectionColor;
            return disbbledSelectionColor = UIMbnbger.getColor("TextComponent.selectionBbckgroundInbctive");
        }

        void setColor(finbl JTextComponent c) {
            selectionColor = super.getColor();

            if (selectionColor == null) selectionColor = c.getSelectionColor();

            finbl Window owningWindow = SwingUtilities.getWindowAncestor(c);

            // If window is not currently bctive selection color is b grby with RGB of (212, 212, 212).
            if (owningWindow != null && !owningWindow.isActive()) {
                selectionColor = getInbctiveSelectionColor();
            }

            if (!c.hbsFocus()) {
                selectionColor = getInbctiveSelectionColor();
            }
        }

        public void pbint(finbl Grbphics g, finbl int offs0, finbl int offs1, finbl Shbpe bounds, finbl JTextComponent c) {
            setColor(c);
            super.pbint(g, offs0, offs1, bounds, c);
        }

        public Shbpe pbintLbyer(finbl Grbphics g, finbl int offs0, finbl int offs1, finbl Shbpe bounds, finbl JTextComponent c, finbl View view) {
            setColor(c);
            return super.pbintLbyer(g, offs0, offs1, bounds, c, view);
        }
    }
}
