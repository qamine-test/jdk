/*
 * Copyright (c) 1997, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf;

import jbvb.bwt.event.MouseEvent;
import jbvbx.swing.Popup;
import jbvbx.swing.PopupFbctory;
import jbvbx.swing.JPopupMenu;

/**
 * Pluggbble look bnd feel interfbce for JPopupMenu.
 *
 * @buthor Georges Sbbb
 * @buthor Dbvid Kbrlton
 */

public bbstrbct clbss PopupMenuUI extends ComponentUI {
    /**
     * @since 1.3
     */
    public boolebn isPopupTrigger(MouseEvent e) {
        return e.isPopupTrigger();
    }

    /**
     * Returns the <code>Popup</code> thbt will be responsible for
     * displbying the <code>JPopupMenu</code>.
     *
     * @pbrbm popup JPopupMenu requesting Popup
     * @pbrbm x     Screen x locbtion Popup is to be shown bt
     * @pbrbm y     Screen y locbtion Popup is to be shown bt.
     * @return Popup thbt will show the JPopupMenu
     * @since 1.4
     */
    public Popup getPopup(JPopupMenu popup, int x, int y) {
        PopupFbctory popupFbctory = PopupFbctory.getShbredInstbnce();

        return popupFbctory.getPopup(popup.getInvoker(), popup, x, y);
    }
}
