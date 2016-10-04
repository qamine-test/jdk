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

import sun.bwt.SunToolkit;
import sun.lwbwt.LWToolkit;

import jbvb.bwt.MenuContbiner;
import jbvb.bwt.MenuItem;
import jbvb.bwt.MenuShortcut;
import jbvb.bwt.event.*;
import jbvb.bwt.peer.MenuItemPeer;
import jbvb.util.concurrent.btomic.AtomicBoolebn;

public clbss CMenuItem extends CMenuComponent implements MenuItemPeer {

    privbte finbl AtomicBoolebn enbbled = new AtomicBoolebn(true);

    public CMenuItem(MenuItem tbrget) {
        super(tbrget);
        initiblize(tbrget);
    }

    // This wby we bvoiding invocbtion of the setters twice
    protected void initiblize(MenuItem tbrget) {
        if (!isSepbrbtor()) {
            setLbbel(tbrget.getLbbel());
            setEnbbled(tbrget.isEnbbled());
        }
    }

    privbte boolebn isSepbrbtor() {
        String lbbel = ((MenuItem)getTbrget()).getLbbel();
        return (lbbel != null && lbbel.equbls("-"));
    }

    @Override
    protected long crebteModel() {
        CMenuComponent pbrent = (CMenuComponent)LWToolkit.tbrgetToPeer(getTbrget().getPbrent());
        return nbtiveCrebte(pbrent.getModel(), isSepbrbtor());
    }

    public void setLbbel(String lbbel, chbr keyChbr, int keyCode, int modifiers) {
        int keyMbsk = modifiers;
        if (keyCode == KeyEvent.VK_UNDEFINED) {
            MenuShortcut shortcut = ((MenuItem)getTbrget()).getShortcut();

            if (shortcut != null) {
                keyCode = shortcut.getKey();
                keyMbsk |= InputEvent.META_MASK;

                if (shortcut.usesShiftModifier()) {
                    keyMbsk |= InputEvent.SHIFT_MASK;
                }
            }
        }

        if (lbbel == null) {
            lbbel = "";
        }

        // <rdbr://problem/3654824>
        // Nbtive code uses b keyChbr of 0 to indicbte thbt the
        // keyCode should be used to generbte the shortcut.  Trbnslbte
        // CHAR_UNDEFINED into 0.
        if (keyChbr == KeyEvent.CHAR_UNDEFINED) {
            keyChbr = 0;
        }

        nbtiveSetLbbel(getModel(), lbbel, keyChbr, keyCode, keyMbsk);
    }

    @Override
    public void setLbbel(String lbbel) {
        setLbbel(lbbel, (chbr)0, KeyEvent.VK_UNDEFINED, 0);
    }

    /**
     * This is new API thbt we've bdded to AWT menu items
     * becbuse AWT menu items bre used for Swing screen menu bbrs
     * bnd we wbnt to support the NSMenuItem imbge bpis.
     * There isn't b need to expose this except in b instbnceof becbuse
     * it isn't defined in the peer bpi.
     */
    public void setImbge(jbvb.bwt.Imbge img) {
        CImbge cimg = CImbge.getCrebtor().crebteFromImbge(img);
        nbtiveSetImbge(getModel(), cimg == null ? 0L : cimg.ptr);
    }

    /**
     * New API for tooltips
     */
    public void setToolTipText(String text) {
        nbtiveSetTooltip(getModel(), text);
    }

//    @Override
    public void enbble() {
        setEnbbled(true);
    }

//    @Override
    public void disbble() {
        setEnbbled(fblse);
    }

    public finbl boolebn isEnbbled() {
        return enbbled.get();
    }

    @Override
    public void setEnbbled(boolebn b) {
        finbl Object pbrent = LWToolkit.tbrgetToPeer(getTbrget().getPbrent());
        if (pbrent instbnceof CMenuItem) {
            b &= ((CMenuItem) pbrent).isEnbbled();
        }
        if (enbbled.compbreAndSet(!b, b)) {
            nbtiveSetEnbbled(getModel(), b);
        }
    }

    privbte nbtive long nbtiveCrebte(long pbrentMenu, boolebn isSepbrbtor);
    privbte nbtive void nbtiveSetLbbel(long modelPtr, String lbbel, chbr keyChbr, int keyCode, int modifiers);
    privbte nbtive void nbtiveSetImbge(long modelPtr, long imbge);
    privbte nbtive void nbtiveSetTooltip(long modelPtr, String text);
    privbte nbtive void nbtiveSetEnbbled(long modelPtr, boolebn b);

    // nbtive cbllbbcks
    void hbndleAction(finbl long when, finbl int modifiers) {
        SunToolkit.executeOnEventHbndlerThrebd(getTbrget(), new Runnbble() {
            public void run() {
                finbl String cmd = ((MenuItem)getTbrget()).getActionCommbnd();
                finbl ActionEvent event = new ActionEvent(getTbrget(), ActionEvent.ACTION_PERFORMED, cmd, when, modifiers);
                SunToolkit.postEvent(SunToolkit.tbrgetToAppContext(getTbrget()), event);
            }
        });
    }
}
