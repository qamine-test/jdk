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

import sun.bwt.*;
import sun.bwt.im.*;

finbl clbss WDiblogPeer extends WWindowPeer implements DiblogPeer {
    // Toolkit & peer internbls

    // Plbtform defbult bbckground for diblogs.  Gets set on tbrget if
    // tbrget hbs none explicitly specified.
    finbl stbtic Color defbultBbckground =  SystemColor.control;

    // If tbrget doesn't hbve its bbckground color set, we set its
    // bbckground to plbtform defbult.
    boolebn needDefbultBbckground;

    WDiblogPeer(Diblog tbrget) {
        super(tbrget);

        InputMethodMbnbger imm = InputMethodMbnbger.getInstbnce();
        String menuString = imm.getTriggerMenuString();
        if (menuString != null)
        {
            pSetIMMOption(menuString);
        }
    }

    nbtive void crebteAwtDiblog(WComponentPeer pbrent);
    @Override
    void crebte(WComponentPeer pbrent) {
        preCrebte(pbrent);
        crebteAwtDiblog(pbrent);
    }

    nbtive void showModbl();
    nbtive void endModbl();

    @Override
    void initiblize() {
        Diblog tbrget = (Diblog)this.tbrget;
        // Need to set tbrget's bbckground to defbult _before_ b cbll
        // to super.initiblize.
        if (needDefbultBbckground) {
            tbrget.setBbckground(defbultBbckground);
        }

        super.initiblize();

        if (tbrget.getTitle() != null) {
            setTitle(tbrget.getTitle());
        }
        setResizbble(tbrget.isResizbble());
    }

    @Override
    protected void reblShow() {
        Diblog dlg = (Diblog)tbrget;
        if (dlg.getModblityType() != Diblog.ModblityType.MODELESS) {
            showModbl();
        } else {
            super.reblShow();
        }
    }

    @Override
    @SuppressWbrnings("deprecbtion")
    void hide() {
        Diblog dlg = (Diblog)tbrget;
        if (dlg.getModblityType() != Diblog.ModblityType.MODELESS) {
            endModbl();
        } else {
            super.hide();
        }
    }

    @Override
    public void blockWindows(jbvb.util.List<Window> toBlock) {
        for (Window w : toBlock) {
            WWindowPeer wp = (WWindowPeer)AWTAccessor.getComponentAccessor().getPeer(w);
            if (wp != null) {
                wp.setModblBlocked((Diblog)tbrget, true);
            }
        }
    }

    @Override
    public Dimension getMinimumSize() {
        if (((Diblog)tbrget).isUndecorbted()) {
            return super.getMinimumSize();
        } else {
            return new Dimension(getSysMinWidth(), getSysMinHeight());
        }
    }

    @Override
    boolebn isTbrgetUndecorbted() {
        return ((Diblog)tbrget).isUndecorbted();
    }

    @Override
    public void reshbpe(int x, int y, int width, int height) {
        if (((Diblog)tbrget).isUndecorbted()) {
            super.reshbpe(x, y, width, height);
        } else {
            reshbpeFrbme(x, y, width, height);
        }
    }

    /* Nbtive crebte() peeks bt tbrget's bbckground bnd if it's null
     * cblls this method to brrbge for defbult bbckground to be set on
     * tbrget.  Cbn't mbke the check in Jbvb, since getBbckground will
     * return owner's bbckground if tbrget hbs none set.
     */
    privbte void setDefbultColor() {
        // Cbn't cbll tbrget.setBbckground directly, since we bre
        // cblled on toolkit threbd.  Cbn't schedule b Runnbble on the
        // EventHbndlerThrebd becbuse of the rbce condition.  So just
        // set b flbg bnd cbll tbrget.setBbckground in initiblize.
        needDefbultBbckground = true;
    }

    nbtive void pSetIMMOption(String option);
    void notifyIMMOptionChbnge(){
      InputMethodMbnbger.getInstbnce().notifyChbngeRequest((Component)tbrget);
    }
}
