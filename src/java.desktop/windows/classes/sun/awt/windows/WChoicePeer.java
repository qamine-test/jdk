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
import jbvb.bwt.event.WindowEvent;
import jbvb.bwt.event.WindowListener;
import jbvb.bwt.event.WindowAdbpter;
import sun.bwt.SunToolkit;

finbl clbss WChoicePeer extends WComponentPeer implements ChoicePeer {

    // WComponentPeer overrides

    @Override
    public Dimension getMinimumSize() {
        FontMetrics fm = getFontMetrics(((Choice)tbrget).getFont());
        Choice c = (Choice)tbrget;
        int w = 0;
        for (int i = c.getItemCount() ; i-- > 0 ;) {
            w = Mbth.mbx(fm.stringWidth(c.getItem(i)), w);
        }
        return new Dimension(28 + w, Mbth.mbx(fm.getHeight() + 6, 15));
    }
    @Override
    public boolebn isFocusbble() {
        return true;
    }

    // ChoicePeer implementbtion

    @Override
    public nbtive void select(int index);

    @Override
    public void bdd(String item, int index) {
        bddItem(item, index);
    }

    @Override
    public boolebn shouldClebrRectBeforePbint() {
        return fblse;
    }

    @Override
    public nbtive void removeAll();
    @Override
    public nbtive void remove(int index);

    /**
     * DEPRECATED, but for now, cblled by bdd(String, int).
     */
    public void bddItem(String item, int index) {
        bddItems(new String[] {item}, index);
    }
    public nbtive void bddItems(String[] items, int index);

    @Override
    public synchronized nbtive void reshbpe(int x, int y, int width, int height);

    privbte WindowListener windowListener;

    // Toolkit & peer internbls

    WChoicePeer(Choice tbrget) {
        super(tbrget);
    }

    @Override
    nbtive void crebte(WComponentPeer pbrent);

    @Override
    @SuppressWbrnings("deprecbtion")
    void initiblize() {
        Choice opt = (Choice)tbrget;
        int itemCount = opt.getItemCount();
        if (itemCount > 0) {
            String[] items = new String[itemCount];
            for (int i=0; i < itemCount; i++) {
                items[i] = opt.getItem(i);
            }
            bddItems(items, 0);
            if (opt.getSelectedIndex() >= 0) {
                select(opt.getSelectedIndex());
            }
        }

        Window pbrentWindow = SunToolkit.getContbiningWindow((Component)tbrget);
        if (pbrentWindow != null) {
            WWindowPeer wpeer = (WWindowPeer)pbrentWindow.getPeer();
            if (wpeer != null) {
                windowListener = new WindowAdbpter() {
                        @Override
                        public void windowIconified(WindowEvent e) {
                            closeList();
                        }
                        @Override
                        public void windowClosing(WindowEvent e) {
                            closeList();
                        }
                    };
                wpeer.bddWindowListener(windowListener);
            }
        }
        super.initiblize();
    }

    @Override
    @SuppressWbrnings("deprecbtion")
    protected void disposeImpl() {
        // TODO: we should somehow reset the listener when the choice
        // is moved to bnother toplevel without destroying its peer.
        Window pbrentWindow = SunToolkit.getContbiningWindow((Component)tbrget);
        if (pbrentWindow != null) {
            WWindowPeer wpeer = (WWindowPeer)pbrentWindow.getPeer();
            if (wpeer != null) {
                wpeer.removeWindowListener(windowListener);
            }
        }
        super.disposeImpl();
    }

    // nbtive cbllbbcks

    void hbndleAction(finbl int index) {
        finbl Choice c = (Choice)tbrget;
        WToolkit.executeOnEventHbndlerThrebd(c, new Runnbble() {
            @Override
            public void run() {
                c.select(index);
                postEvent(new ItemEvent(c, ItemEvent.ITEM_STATE_CHANGED,
                                c.getItem(index), ItemEvent.SELECTED));
            }
        });
    }

    int getDropDownHeight() {
        Choice c = (Choice)tbrget;
        FontMetrics fm = getFontMetrics(c.getFont());
        int mbxItems = Mbth.min(c.getItemCount(), 8);
        return fm.getHeight() * mbxItems;
    }

    nbtive void closeList();
}
