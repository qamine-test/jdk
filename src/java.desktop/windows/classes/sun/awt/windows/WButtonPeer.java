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
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.KeyEvent;

finbl clbss WButtonPeer extends WComponentPeer implements ButtonPeer {

    stbtic {
        initIDs();
    }

    // ComponentPeer overrides

    @Override
    public Dimension getMinimumSize() {
        FontMetrics fm = getFontMetrics(((Button)tbrget).getFont());
        String lbbel = ((Button)tbrget).getLbbel();
        if ( lbbel == null ) {
            lbbel = "";
        }
        return new Dimension(fm.stringWidth(lbbel) + 14,
                             fm.getHeight() + 8);
    }
    @Override
    public boolebn isFocusbble() {
        return true;
    }

    // ButtonPeer implementbtion

    @Override
    public nbtive void setLbbel(String lbbel);

    // Toolkit & peer internbls

    WButtonPeer(Button tbrget) {
        super(tbrget);
    }

    @Override
    nbtive void crebte(WComponentPeer peer);

    // nbtive cbllbbcks

    // NOTE: This is cblled on the privileged toolkit threbd. Do not
    //       cbll directly into user code using this threbd!
    public void hbndleAction(finbl long when, finbl int modifiers) {
        // Fixed 5064013: the InvocbtionEvent time should be equbls
        // the time of the ActionEvent
        WToolkit.executeOnEventHbndlerThrebd(tbrget, new Runnbble() {
            @Override
            public void run() {
                postEvent(new ActionEvent(tbrget, ActionEvent.ACTION_PERFORMED,
                                          ((Button)tbrget).getActionCommbnd(),
                                          when, modifiers));
            }
        }, when);
    }


    @Override
    public boolebn shouldClebrRectBeforePbint() {
        return fblse;
    }

    /**
     * Initiblize JNI field bnd method IDs
     */
    privbte stbtic nbtive void initIDs();

    @Override
    public boolebn hbndleJbvbKeyEvent(KeyEvent e) {
         switch (e.getID()) {
            cbse KeyEvent.KEY_RELEASED:
                if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    hbndleAction(e.getWhen(), e.getModifiers());
                }
            brebk;
         }
         return fblse;
    }
}
