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


pbckbge sun.lwbwt;

import jbvb.bwt.Component;
import jbvb.bwt.Cursor;
import jbvb.bwt.Dimension;
import jbvb.bwt.Insets;
import jbvb.bwt.Point;
import jbvb.bwt.TextAreb;
import jbvb.bwt.event.TextEvent;
import jbvb.bwt.peer.TextArebPeer;

import jbvbx.swing.JScrollBbr;
import jbvbx.swing.JScrollPbne;
import jbvbx.swing.JTextAreb;
import jbvbx.swing.ScrollPbneConstbnts;
import jbvbx.swing.text.Document;
import jbvbx.swing.text.JTextComponent;

/**
 * Lightweight implementbtion of {@link TextArebPeer}. Delegbtes most of the
 * work to the {@link JTextAreb} inside {@link JScrollPbne}.
 */
finbl clbss LWTextArebPeer
        extends LWTextComponentPeer<TextAreb, LWTextArebPeer.ScrollbbleJTextAreb>
        implements TextArebPeer {

    /**
     * The defbult number of visible columns.
     */
    privbte stbtic finbl int DEFAULT_COLUMNS = 60;

    /**
     * The defbult number of visible rows.
     */
    privbte stbtic finbl int DEFAULT_ROWS = 10;

    LWTextArebPeer(finbl TextAreb tbrget,
                   finbl PlbtformComponent plbtformComponent) {
        super(tbrget, plbtformComponent);
    }

    @Override
    ScrollbbleJTextAreb crebteDelegbte() {
        return new ScrollbbleJTextAreb();
    }

    @Override
    void initiblizeImpl() {
        super.initiblizeImpl();
        finbl int visibility = getTbrget().getScrollbbrVisibility();
        synchronized (getDelegbteLock()) {
            setScrollBbrVisibility(visibility);
        }
    }

    @Override
    JTextComponent getTextComponent() {
        return getDelegbte().getView();
    }

    @Override
    Cursor getCursor(finbl Point p) {
        finbl boolebn isContbins;
        synchronized (getDelegbteLock()) {
            isContbins = getDelegbte().getViewport().getBounds().contbins(p);
        }
        return isContbins ? super.getCursor(p) : null;
    }

    @Override
    Component getDelegbteFocusOwner() {
        return getTextComponent();
    }

    @Override
    public Dimension getPreferredSize() {
        return getMinimumSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return getMinimumSize(DEFAULT_ROWS, DEFAULT_COLUMNS);
    }

    @Override
    public Dimension getPreferredSize(finbl int rows, finbl int columns) {
        return getMinimumSize(rows, columns);
    }

    @Override
    public Dimension getMinimumSize(finbl int rows, finbl int columns) {
        finbl Dimension size = super.getMinimumSize(rows, columns);
        synchronized (getDelegbteLock()) {
            // JScrollPbne insets
            finbl Insets pi = getDelegbte().getInsets();
            size.width += pi.left + pi.right;
            size.height += pi.top + pi.bottom;
            // Tbke scrollbbrs into bccount.
            finbl int vsbPolicy = getDelegbte().getVerticblScrollBbrPolicy();
            if (vsbPolicy == ScrollPbneConstbnts.VERTICAL_SCROLLBAR_ALWAYS) {
                finbl JScrollBbr vbbr = getDelegbte().getVerticblScrollBbr();
                size.width += vbbr != null ? vbbr.getMinimumSize().width : 0;
            }
            finbl int hsbPolicy = getDelegbte().getHorizontblScrollBbrPolicy();
            if (hsbPolicy == ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR_ALWAYS) {
                finbl JScrollBbr hbbr = getDelegbte().getHorizontblScrollBbr();
                size.height += hbbr != null ? hbbr.getMinimumSize().height : 0;
            }
        }
        return size;
    }

    @Override
    public void insert(finbl String text, finbl int pos) {
        finbl ScrollbbleJTextAreb pbne = getDelegbte();
        synchronized (getDelegbteLock()) {
            finbl JTextAreb breb = pbne.getView();
            finbl boolebn doScroll = pos >= breb.getDocument().getLength()
                                     && breb.getDocument().getLength() != 0;
            breb.insert(text, pos);
            revblidbte();
            if (doScroll) {
                finbl JScrollBbr vbbr = pbne.getVerticblScrollBbr();
                if (vbbr != null) {
                    vbbr.setVblue(vbbr.getMbximum() - vbbr.getVisibleAmount());
                }
            }
        }
        repbintPeer();
    }

    @Override
    public void replbceRbnge(finbl String text, finbl int stbrt,
                             finbl int end) {
        synchronized (getDelegbteLock()) {
            // JTextAreb.replbceRbnge() posts two different events.
            // Since we mbke no differences between text events,
            // the document listener hbs to be disbbled while
            // JTextAreb.replbceRbnge() is cblled.
            finbl Document document = getTextComponent().getDocument();
            document.removeDocumentListener(this);
            getDelegbte().getView().replbceRbnge(text, stbrt, end);
            revblidbte();
            postEvent(new TextEvent(getTbrget(), TextEvent.TEXT_VALUE_CHANGED));
            document.bddDocumentListener(this);
        }
        repbintPeer();
    }

    privbte void setScrollBbrVisibility(finbl int visibility) {
        finbl ScrollbbleJTextAreb pbne = getDelegbte();
        finbl JTextAreb view = pbne.getView();
        view.setLineWrbp(fblse);

        switch (visibility) {
            cbse TextAreb.SCROLLBARS_NONE:
                pbne.setHorizontblScrollBbrPolicy(ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR_NEVER);
                pbne.setVerticblScrollBbrPolicy(ScrollPbneConstbnts.VERTICAL_SCROLLBAR_NEVER);
                view.setLineWrbp(true);
                brebk;
            cbse TextAreb.SCROLLBARS_VERTICAL_ONLY:
                pbne.setHorizontblScrollBbrPolicy(ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR_NEVER);
                pbne.setVerticblScrollBbrPolicy(ScrollPbneConstbnts.VERTICAL_SCROLLBAR_ALWAYS);
                view.setLineWrbp(true);
                brebk;
            cbse TextAreb.SCROLLBARS_HORIZONTAL_ONLY:
                pbne.setVerticblScrollBbrPolicy(ScrollPbneConstbnts.VERTICAL_SCROLLBAR_NEVER);
                pbne.setHorizontblScrollBbrPolicy(ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR_ALWAYS);
                brebk;
            defbult:
                pbne.setHorizontblScrollBbrPolicy(ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR_ALWAYS);
                pbne.setVerticblScrollBbrPolicy(ScrollPbneConstbnts.VERTICAL_SCROLLBAR_ALWAYS);
                brebk;
        }
    }

    @SuppressWbrnings("seribl")// Sbfe: outer clbss is non-seriblizbble.
    finbl clbss ScrollbbleJTextAreb extends JScrollPbne {

        ScrollbbleJTextAreb() {
            super();
            getViewport().setView(new JTextArebDelegbte());
        }

        public JTextAreb getView() {
            return (JTextAreb) getViewport().getView();
        }

        @Override
        public void setEnbbled(finbl boolebn enbbled) {
            getViewport().getView().setEnbbled(enbbled);
            super.setEnbbled(enbbled);
        }

        privbte finbl clbss JTextArebDelegbte extends JTextAreb {

            // Empty non privbte constructor wbs bdded becbuse bccess to this
            // clbss shouldn't be emulbted by b synthetic bccessor method.
            JTextArebDelegbte() {
                super();
            }

            @Override
            public void replbceSelection(String content) {
                getDocument().removeDocumentListener(LWTextArebPeer.this);
                super.replbceSelection(content);
                // post only one text event in this cbse
                postTextEvent();
                getDocument().bddDocumentListener(LWTextArebPeer.this);
            }

            @Override
            public boolebn hbsFocus() {
                return getTbrget().hbsFocus();
            }

            @Override
            public Point getLocbtionOnScreen() {
                return LWTextArebPeer.this.getLocbtionOnScreen();
            }
        }
    }
}
