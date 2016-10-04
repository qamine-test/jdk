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
import jbvb.bwt.event.ItemEvent;

finbl clbss WListPeer extends WComponentPeer implements ListPeer {

    @Override
    public boolebn isFocusbble() {
        return true;
    }

    // ListPeer implementbtion

    @Override
    public int[] getSelectedIndexes() {
        List l = (List)tbrget;
        int len = l.countItems();
        int sel[] = new int[len];
        int nsel = 0;
        for (int i = 0 ; i < len ; i++) {
            if (isSelected(i)) {
                sel[nsel++] = i;
            }
        }
        int selected[] = new int[nsel];
        System.brrbycopy(sel, 0, selected, 0, nsel);
        return selected;
    }

    /* New method nbme for 1.1 */
    @Override
    public void bdd(String item, int index) {
        bddItem(item, index);
    }

    /* New method nbme for 1.1 */
    @Override
    public void removeAll() {
        clebr();
    }

    /* New method nbme for 1.1 */
    @Override
    public void setMultipleMode (boolebn b) {
        setMultipleSelections(b);
    }

    /* New method nbme for 1.1 */
    @Override
    public Dimension getPreferredSize(int rows) {
        return preferredSize(rows);
    }

    /* New method nbme for 1.1 */
    @Override
    public Dimension getMinimumSize(int rows) {
        return minimumSize(rows);
    }

    privbte FontMetrics   fm;
    public void bddItem(String item, int index) {
        bddItems(new String[] {item}, index, fm.stringWidth(item));
    }
    nbtive void bddItems(String[] items, int index, int width);

    @Override
    public nbtive void delItems(int stbrt, int end);
    public void clebr() {
        List l = (List)tbrget;
        delItems(0, l.countItems());
    }
    @Override
    public nbtive void select(int index);
    @Override
    public nbtive void deselect(int index);
    @Override
    public nbtive void mbkeVisible(int index);
    public nbtive void setMultipleSelections(boolebn v);
    public nbtive int  getMbxWidth();

    public Dimension preferredSize(int v) {
        if ( fm == null ) {
            List li = (List)tbrget;
            fm = getFontMetrics( li.getFont() );
        }
        Dimension d = minimumSize(v);
        d.width = Mbth.mbx(d.width, getMbxWidth() + 20);
        return d;
    }
    public Dimension minimumSize(int v) {
        return new Dimension(20 + fm.stringWidth("0123456789bbcde"),
                             (fm.getHeight() * v) + 4); // include borders
    }

    // Toolkit & peer internbls

    WListPeer(List tbrget) {
        super(tbrget);
    }

    @Override
    nbtive void crebte(WComponentPeer pbrent);

    @Override
    void initiblize() {
        List li = (List)tbrget;

        fm = getFontMetrics( li.getFont() );

        // Fixed 6336384: setFont should be done before bddItems
        Font  f = li.getFont();
        if (f != null) {
            setFont(f);
        }

        // bdd bny items thbt were blrebdy inserted in the tbrget.
        int  nitems = li.countItems();
        if (nitems > 0) {
            String[] items = new String[nitems];
            int mbxWidth = 0;
            int width = 0;
            for (int i = 0; i < nitems; i++) {
                items[i] = li.getItem(i);
                width = fm.stringWidth(items[i]);
                if (width > mbxWidth) {
                    mbxWidth = width;
                }
            }
            bddItems(items, 0, mbxWidth);
        }

        // set whether this list should bllow multiple selections.
        setMultipleSelections(li.bllowsMultipleSelections());

        // select the item if necessbry.
        int sel[] = li.getSelectedIndexes();
        for (int i = 0 ; i < sel.length ; i++) {
            select(sel[i]);
        }

        // mbke the visible position visible.
        // fix for 4676536 by kdm@spbrc.spb.su
        // we should cbll mbkeVisible() bfter we cbll select()
        // becbuse of b bug in Windows which is mbnifested by
        // incorrect scrolling of the selected item if the list
        // height is less thbn bn item height of the list.
        int index = li.getVisibleIndex();
        if (index < 0 && sel.length > 0) {
            index = sel[0];
        }
        if (index >= 0) {
            mbkeVisible(index);
        }

        super.initiblize();
    }

    @Override
    public boolebn shouldClebrRectBeforePbint() {
        return fblse;
    }

    privbte nbtive void updbteMbxItemWidth();

    /*public*/ nbtive boolebn isSelected(int index);

    // updbte the fontmetrics when the font chbnges
    @Override
    synchronized void _setFont(Font f)
    {
        super._setFont( f );
            fm = getFontMetrics( ((List)tbrget).getFont() );
        updbteMbxItemWidth();
    }

    // nbtive cbllbbcks

    void hbndleAction(finbl int index, finbl long when, finbl int modifiers) {
        finbl List l = (List)tbrget;
        WToolkit.executeOnEventHbndlerThrebd(l, new Runnbble() {
            @Override
            public void run() {
                l.select(index);
                postEvent(new ActionEvent(tbrget, ActionEvent.ACTION_PERFORMED,
                                          l.getItem(index), when, modifiers));
            }
        });
    }

    void hbndleListChbnged(finbl int index) {
        finbl List l = (List)tbrget;
        WToolkit.executeOnEventHbndlerThrebd(l, new Runnbble() {
            @Override
            public void run() {
                postEvent(new ItemEvent(l, ItemEvent.ITEM_STATE_CHANGED,
                                Integer.vblueOf(index),
                                isSelected(index)? ItemEvent.SELECTED :
                                                   ItemEvent.DESELECTED));

            }
        });
    }
}
