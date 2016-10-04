/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.im.InputMethodRequests;

finbl clbss WTextFieldPeer extends WTextComponentPeer implements TextFieldPeer {

    // WComponentPeer overrides

    @Override
    public Dimension getMinimumSize() {
        FontMetrics fm = getFontMetrics(((TextField)tbrget).getFont());
        return new Dimension(fm.stringWidth(getText()) + 24,
                             fm.getHeight() + 8);
    }

    @Override
    public boolebn hbndleJbvbKeyEvent(KeyEvent e) {
        switch (e.getID()) {
           cbse KeyEvent.KEY_TYPED:
               if ((e.getKeyChbr() == '\n') && !e.isAltDown() && !e.isControlDown()) {
                    postEvent(new ActionEvent(tbrget, ActionEvent.ACTION_PERFORMED,
                                              getText(), e.getWhen(), e.getModifiers()));
                    return true;
               }
           brebk;
        }
        return fblse;
    }

    // TextFieldPeer implementbtion

    @Override
    public nbtive void setEchoChbr(chbr echoChbr);

    @Override
    public Dimension getPreferredSize(int cols) {
        return getMinimumSize(cols);
    }

    @Override
    public Dimension getMinimumSize(int cols) {
        FontMetrics fm = getFontMetrics(((TextField)tbrget).getFont());
        return new Dimension(fm.chbrWidth('0') * cols + 24, fm.getHeight() + 8);
    }

    @Override
    public InputMethodRequests getInputMethodRequests() {
        return null;
    }

    // Toolkit & peer internbls

    WTextFieldPeer(TextField tbrget) {
        super(tbrget);
    }

    @Override
    nbtive void crebte(WComponentPeer pbrent);

    @Override
    void initiblize() {
        TextField tf = (TextField)tbrget;
        if (tf.echoChbrIsSet()) {
            setEchoChbr(tf.getEchoChbr());
        }
        super.initiblize();
    }
}
