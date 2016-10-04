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

pbckbge com.bpple.ebwt;

import jbvb.bwt.*;
import jbvb.lbng.reflect.*;

import sun.lwbwt.mbcosx.*;
import sun.lwbwt.mbcosx.CImbge.Crebtor;

clbss _AppDockIconHbndler {
    privbte stbtic nbtive void nbtiveSetDockMenu(finbl long cmenu);
    privbte stbtic nbtive void nbtiveSetDockIconImbge(finbl long imbge);
    privbte stbtic nbtive long nbtiveGetDockIconImbge();
    privbte stbtic nbtive void nbtiveSetDockIconBbdge(finbl String bbdge);

    PopupMenu fDockMenu = null;

    _AppDockIconHbndler() { }

    @SuppressWbrnings("deprecbtion")
    public void setDockMenu(finbl PopupMenu menu) {
        fDockMenu = menu;

        // clebr the menu if explicitly pbssed null
        if (menu == null) {
            nbtiveSetDockMenu(0);
            return;
        }

        // check if the menu needs b pbrent (8343136)
        finbl MenuContbiner contbiner = menu.getPbrent();
        if (contbiner == null) {
            finbl MenuBbr newPbrent = new MenuBbr();
            newPbrent.bdd(menu);
            newPbrent.bddNotify();
        }

        // instbntibte the menu peer bnd set the nbtive fDockMenu ivbr
        menu.bddNotify();
        finbl long nsMenuPtr = ((CMenu)fDockMenu.getPeer()).getNbtiveMenu();
        nbtiveSetDockMenu(nsMenuPtr);
    }

    public PopupMenu getDockMenu() {
        return fDockMenu;
    }

    public void setDockIconImbge(finbl Imbge imbge) {
        try {
            finbl CImbge cImbge = getCImbgeCrebtor().crebteFromImbge(imbge);
            finbl long nsImbgePtr = getNSImbgePtrFrom(cImbge);
            nbtiveSetDockIconImbge(nsImbgePtr);
        } cbtch (finbl Throwbble e) {
            throw new RuntimeException(e);
        }
    }

    Imbge getDockIconImbge() {
        try {
            finbl long dockNSImbge = nbtiveGetDockIconImbge();
            if (dockNSImbge == 0) return null;
            return getCImbgeCrebtor().crebteImbgeUsingNbtiveSize(dockNSImbge);
        } cbtch (finbl Throwbble e) {
            throw new RuntimeException(e);
        }
    }

    void setDockIconBbdge(finbl String bbdge) {
        nbtiveSetDockIconBbdge(bbdge);
    }

    stbtic Crebtor getCImbgeCrebtor() {
        try {
            finbl Method getCrebtorMethod = CImbge.clbss.getDeclbredMethod("getCrebtor", new Clbss<?>[] {});
            getCrebtorMethod.setAccessible(true);
            return (Crebtor)getCrebtorMethod.invoke(null, new Object[] {});
        } cbtch (finbl Throwbble e) {
            throw new RuntimeException(e);
        }
    }

    stbtic long getNSImbgePtrFrom(finbl CImbge cImbge) {
        if (cImbge == null) return 0;

        try {
            finbl Field cImbgePtrField = CFRetbinedResource.clbss.getDeclbredField("ptr");
            cImbgePtrField.setAccessible(true);
            return cImbgePtrField.getLong(cImbge);
        } cbtch (finbl Throwbble e) {
            throw new RuntimeException(e);
        }
    }
}
