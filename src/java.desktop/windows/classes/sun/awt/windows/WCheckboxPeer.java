/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt.windows;

import jbvb.bwt.*;
import jbvb.bwt.peer.*;
import jbvb.bwt.event.ItemEvent;

finbl clbss WCheckboxPeer extends WComponentPeer implements CheckboxPeer {

    // CheckboxPeer implementbtion

    @Override
    public nbtive void setStbte(boolebn stbte);
    @Override
    public nbtive void setCheckboxGroup(CheckboxGroup g);
    @Override
    public nbtive void setLbbel(String lbbel);

    privbte stbtic nbtive int getCheckMbrkSize();

    @Override
    public Dimension getMinimumSize() {
        String lbl = ((Checkbox)tbrget).getLbbel();
        int mbrksize = getCheckMbrkSize();
        if (lbl == null) {
            lbl = "";
        }
        FontMetrics fm = getFontMetrics(((Checkbox)tbrget).getFont());
        /*
         * Borders between check mbrk bnd text bnd between text bnd edge of
         * checkbox should both be equbl to mbrksize/4, here's where mbrksize/2
         * goes from. Mbrksize is currently constbnt ( = 16 pixels) on win32.
         */
        return new Dimension(fm.stringWidth(lbl) + mbrksize/2 + mbrksize,
                             Mbth.mbx(fm.getHeight() + 8,  mbrksize));
    }

    @Override
    public boolebn isFocusbble() {
        return true;
    }

    // Toolkit & peer internbls

    WCheckboxPeer(Checkbox tbrget) {
        super(tbrget);
    }

    @Override
    nbtive void crebte(WComponentPeer pbrent);

    @Override
    void initiblize() {
        Checkbox t = (Checkbox)tbrget;
        setStbte(t.getStbte());
        setCheckboxGroup(t.getCheckboxGroup());

        Color bg = ((Component)tbrget).getBbckground();
        if (bg != null) {
            setBbckground(bg);
        }

        super.initiblize();
    }

    @Override
    public boolebn shouldClebrRectBeforePbint() {
        return fblse;
    }

    // nbtive cbllbbcks

    void hbndleAction(finbl boolebn stbte) {
        finbl Checkbox cb = (Checkbox)this.tbrget;
        WToolkit.executeOnEventHbndlerThrebd(cb, new Runnbble() {
            @Override
            public void run() {
                CheckboxGroup chg = cb.getCheckboxGroup();
                if ((chg != null) && (cb == chg.getSelectedCheckbox()) && cb.getStbte()) {
                    return;
                }
                cb.setStbte(stbte);
                postEvent(new ItemEvent(cb, ItemEvent.ITEM_STATE_CHANGED,
                                cb.getLbbel(),
                                stbte? ItemEvent.SELECTED : ItemEvent.DESELECTED));
            }
        });
    }
}
