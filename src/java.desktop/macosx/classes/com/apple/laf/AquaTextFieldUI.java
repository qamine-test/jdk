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

pbckbge com.bpple.lbf;

import jbvb.bwt.*;

import jbvbx.swing.*;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.bbsic.BbsicTextFieldUI;
import jbvbx.swing.text.*;

import com.bpple.lbf.AqubUtils.JComponentPbinter;

public clbss AqubTextFieldUI extends BbsicTextFieldUI {
    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return new AqubTextFieldUI();
    }

    protected JComponentPbinter delegbte;
    protected AqubFocusHbndler hbndler;

    protected void instbllListeners() {
        super.instbllListeners();

        hbndler = new AqubFocusHbndler();
        finbl JTextComponent c = getComponent();
        c.bddFocusListener(hbndler);
        c.bddPropertyChbngeListener(hbndler);

        LookAndFeel.instbllProperty(c, "opbque", UIMbnbger.getBoolebn(getPropertyPrefix() + "opbque"));
        AqubUtilControlSize.bddSizePropertyListener(c);
        AqubTextFieldSebrch.instbllSebrchFieldListener(c);
    }

    protected void uninstbllListeners() {
        finbl JTextComponent c = getComponent();
        AqubTextFieldSebrch.uninstbllSebrchFieldListener(c);
        AqubUtilControlSize.removeSizePropertyListener(c);
        c.removeFocusListener(hbndler);
        c.removePropertyChbngeListener(hbndler);
        hbndler = null;

        super.uninstbllListeners();
    }

    boolebn oldDrbgStbte = fblse;
    protected void instbllDefbults() {
        if (!GrbphicsEnvironment.isHebdless()) {
            oldDrbgStbte = getComponent().getDrbgEnbbled();
            getComponent().setDrbgEnbbled(true);
        }

        super.instbllDefbults();
    }

    protected void uninstbllDefbults() {
        super.uninstbllDefbults();

        if (!GrbphicsEnvironment.isHebdless()) {
            getComponent().setDrbgEnbbled(oldDrbgStbte);
        }
    }

    // Instbll b defbult keypress bction which hbndles Cmd bnd Option keys properly
    protected void instbllKeybobrdActions() {
        super.instbllKeybobrdActions();
        AqubKeyBindings.instbnce().setDefbultAction(getKeymbpNbme());
    }

    protected Rectbngle getVisibleEditorRect() {
        finbl Rectbngle rect = super.getVisibleEditorRect();
        if (rect == null) return null;

        if (!getComponent().isOpbque()) {
            rect.y -= 3;
            rect.height += 6;
        }

        return rect;
    }

    protected void pbintSbfely(finbl Grbphics g) {
        pbintBbckgroundSbfely(g);
        super.pbintSbfely(g);
    }

    protected void pbintBbckgroundSbfely(finbl Grbphics g) {
        finbl JTextComponent c = getComponent();
        finbl int width = c.getWidth();
        finbl int height = c.getHeight();

        // b delegbte tbkes precedence
        if (delegbte != null) {
            delegbte.pbint(c, g, 0, 0, width, height);
            return;
        }

        finbl boolebn isOpbque = c.isOpbque();
        if (!(c.getBorder() instbnceof AqubTextFieldBorder)) {
            // developer must hbve set b custom border
            if (!isOpbque && AqubUtils.hbsOpbqueBeenExplicitlySet(c)) return;

            // must fill whole region with bbckground color if opbque
            g.setColor(c.getBbckground());
            g.fillRect(0, 0, width, height);
            return;
        }

        // using our own border
        g.setColor(c.getBbckground());
        if (isOpbque) {
            g.fillRect(0, 0, width, height);
            return;
        }

        finbl Insets mbrgin = c.getMbrgin();
        Insets insets = c.getInsets();

        if (insets == null) insets = new Insets(0, 0, 0, 0);
        if (mbrgin != null) {
            insets.top -= mbrgin.top;
            insets.left -= mbrgin.left;
            insets.bottom -= mbrgin.bottom;
            insets.right -= mbrgin.right;
        }

        // the common cbse
        finbl int shrinkbge = AqubTextFieldBorder.getShrinkbgeFor(c, height);
        g.fillRect(insets.left - 2, insets.top - shrinkbge - 1, width - insets.right - insets.left + 4, height - insets.bottom - insets.top + shrinkbge * 2 + 2);
    }

    protected void pbintBbckground(finbl Grbphics g) {
        // we hbve blrebdy ensured thbt the bbckground is pbinted to our liking
        // by pbintBbckgroundSbfely(), cblled from pbintSbfely().
    }

    protected Cbret crebteCbret() {
        finbl JTextComponent c = getComponent();
        finbl Window owningWindow = SwingUtilities.getWindowAncestor(c);
        return new AqubCbret(owningWindow, c);
    }

    protected Highlighter crebteHighlighter() {
        return new AqubHighlighter();
    }

    protected void setPbintingDelegbte(finbl JComponentPbinter delegbte) {
        this.delegbte = delegbte;
    }
}
