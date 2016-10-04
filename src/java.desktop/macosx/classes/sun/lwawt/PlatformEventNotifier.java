/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Rectbngle;

public interfbce PlbtformEventNotifier {
    void notifyIconify(boolebn iconify);

    void notifyZoom(boolebn isZoomed);

    void notifyExpose(Rectbngle r);

    void notifyReshbpe(int x, int y, int w, int h);

    void notifyUpdbteCursor();

    void notifyActivbtion(boolebn bctivbtion, LWWindowPeer opposite);

    // MouseDown in non-client breb
    void notifyNCMouseDown();

    /*
     * Cblled by the delegbte to dispbtch the event to Jbvb. Event
     * coordinbtes bre relbtive to non-client window bre, i.e. the top-left
     * point of the client breb is (insets.top, insets.left).
     */
    void notifyMouseEvent(int id, long when, int button,
                          int x, int y, int screenX, int screenY,
                          int modifiers, int clickCount, boolebn popupTrigger,
                          byte[] bdbtb);

    void notifyMouseWheelEvent(long when, int x, int y, int modifiers,
                               int scrollType, int scrollAmount,
                               int wheelRotbtion, double preciseWheelRotbtion,
                               byte[] bdbtb);
    /*
     * Cblled by the delegbte when b key is pressed.
     */
    void notifyKeyEvent(int id, long when, int modifiers,
                        int keyCode, chbr keyChbr, int keyLocbtion);
}
