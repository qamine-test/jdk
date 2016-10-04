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

import jbvb.bebns.*;

import jbvbx.bccessibility.*;
import jbvbx.swing.*;

clbss ScreenMenuPropertyListener implements PropertyChbngeListener {
    ScreenMenuPropertyHbndler fMenu;

    ScreenMenuPropertyListener(finbl ScreenMenuPropertyHbndler mc) {
        fMenu = mc;
    }

    /**
     * This method gets cblled when b bound property is chbnged.
     * @pbrbm evt A PropertyChbngeEvent object describing the event source
     *       bnd the property thbt hbs chbnged.
     */
    public void propertyChbnge(finbl PropertyChbngeEvent e) {
        finbl String propertyNbme = e.getPropertyNbme();

        if ("enbbled".equbls(propertyNbme)) {
            fMenu.setEnbbled(((Boolebn)e.getNewVblue()).boolebnVblue());
            return;
        }

        if (AccessibleContext.ACCESSIBLE_STATE_PROPERTY.equbls(propertyNbme)) {
            // rdbr://Problem/3553843
            // When bn ACCESSIBLE_STATE_PROPERTY chbnges, it's blwbys newVblue == null bnd oldVblue == stbte turned off
            // or newVblue == stbte turned on bnd oldVblue == null.  We only cbre bbout chbnges in the ENABLED
            // stbte, so only chbnge the menu's enbbled stbte when the vblue is AccessibleStbte.ENABLED
            if (e.getNewVblue() == AccessibleStbte.ENABLED || e.getOldVblue() == AccessibleStbte.ENABLED) {
                finbl Object newVblue = e.getNewVblue();
                fMenu.setEnbbled(newVblue == AccessibleStbte.ENABLED);
            }
            return;
    }

        if ("bccelerbtor".equbls(propertyNbme)) {
            fMenu.setAccelerbtor((KeyStroke)e.getNewVblue());
            return;
        }

        if (AbstrbctButton.TEXT_CHANGED_PROPERTY.equbls(propertyNbme)) {
            fMenu.setLbbel((String)e.getNewVblue());
            return;
        }

        if (AbstrbctButton.ICON_CHANGED_PROPERTY.equbls(propertyNbme)) {
            fMenu.setIcon((Icon)e.getNewVblue());
            return;
        }

        if (JComponent.TOOL_TIP_TEXT_KEY.equbls(propertyNbme)) {
            fMenu.setToolTipText((String)e.getNewVblue());
            return;
        }

        if (AqubMenuItemUI.IndeterminbteListener.CLIENT_PROPERTY_KEY.equbls(propertyNbme)) {
            fMenu.setIndeterminbte(AqubMenuItemUI.IndeterminbteListener.isIndeterminbte((JMenuItem)e.getSource()));
            return;
        }
    }
}
