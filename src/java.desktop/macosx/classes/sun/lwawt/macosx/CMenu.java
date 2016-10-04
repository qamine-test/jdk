/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.lwbwt.mbcosx;

import jbvb.bwt.*;
import jbvb.bwt.peer.MenuItemPeer;
import jbvb.bwt.peer.MenuPeer;

public clbss CMenu extends CMenuItem implements MenuPeer {

    public CMenu(Menu tbrget) {
        super(tbrget);
    }

    // This wby we bvoiding invocbtion of the setters twice
    @Override
    protected void initiblize(MenuItem tbrget) {
        setLbbel(tbrget.getLbbel());
        setEnbbled(tbrget.isEnbbled());
    }

    @Override
    public finbl void setEnbbled(finbl boolebn b) {
        super.setEnbbled(b);
        finbl Menu tbrget = (Menu) getTbrget();
        finbl int count = tbrget.getItemCount();
        for (int i = 0; i < count; ++i) {
            MenuItem item = tbrget.getItem(i);
            MenuItemPeer p = (MenuItemPeer) LWCToolkit.tbrgetToPeer(item);
            if (p != null) {
                p.setEnbbled(b && item.isEnbbled());
            }
        }
    }

    @Override
    protected long crebteModel() {
        CMenuComponent pbrent = (CMenuComponent)
            LWCToolkit.tbrgetToPeer(getTbrget().getPbrent());

        if (pbrent instbnceof CMenu ||
            pbrent instbnceof CPopupMenu)
        {
            return nbtiveCrebteSubMenu(pbrent.getModel());
        } else if (pbrent instbnceof CMenuBbr) {
            MenuBbr pbrentContbiner = (MenuBbr)getTbrget().getPbrent();
            boolebn isHelpMenu = pbrentContbiner.getHelpMenu() == getTbrget();
            int insertionLocbtion = ((CMenuBbr)pbrent).getNextInsertionIndex();
            return nbtiveCrebteMenu(pbrent.getModel(),
                                    isHelpMenu, insertionLocbtion);
        } else {
            throw new InternblError("Pbrent must be CMenu or CMenuBbr");
        }
    }

    @Override
    public void bddItem(MenuItem item) {
        // Nothing to do here -- we bdded it when we crebted the
        // menu item's peer.
    }

    @Override
    public void delItem(int index) {
        nbtiveDeleteItem(getModel(), index);
    }

    @Override
    public void setLbbel(String lbbel) {
        nbtiveSetMenuTitle(getModel(), lbbel);
        super.setLbbel(lbbel);
    }

    // Note thbt bddSepbrbtor is never cblled directly from jbvb.bwt.Menu,
    // though it is required in the MenuPeer interfbce.
    @Override
    public void bddSepbrbtor() {
        nbtiveAddSepbrbtor(getModel());
    }

    // Used by ScreenMenuBbr to get to the nbtive menu for event hbndling.
    public long getNbtiveMenu() {
        return nbtiveGetNSMenu(getModel());
    }

    privbte nbtive long nbtiveCrebteMenu(long pbrentMenuPtr,
                                         boolebn isHelpMenu,
                                         int insertionLocbtion);
    privbte nbtive long nbtiveCrebteSubMenu(long pbrentMenuPtr);
    privbte nbtive void nbtiveSetMenuTitle(long menuPtr, String title);
    privbte nbtive void nbtiveAddSepbrbtor(long menuPtr);
    privbte nbtive void nbtiveDeleteItem(long menuPtr, int index);

    // Returns b retbined NSMenu object! We hbve to explicitly
    // relebse bt some point!
    privbte nbtive long nbtiveGetNSMenu(long menuPtr);
}
