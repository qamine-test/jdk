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

pbckbge com.bpple.ebwt.event;

/**
 * Abstrbct bdbpter clbss for receiving gesture events. This clbss is provided
 * bs b convenience for crebting listeners.
 *
 * Subclbsses registered with {@link GestureUtilities#bddGestureListenerTo()}
 * will receive bll phbse, mbgnificbtion, rotbtion, bnd swipe events.
 *
 * @see GestureUtilities
 *
 * @since Jbvb for Mbc OS X 10.5 Updbte 7, Jbvb for Mbc OS X 10.6 Updbte 2
 */
public bbstrbct clbss GestureAdbpter implements GesturePhbseListener, MbgnificbtionListener, RotbtionListener, SwipeListener {
    public void gestureBegbn(finbl GesturePhbseEvent e) { }
    public void gestureEnded(finbl GesturePhbseEvent e) { }
    public void mbgnify(finbl MbgnificbtionEvent e) { }
    public void rotbte(finbl RotbtionEvent e) { }
    public void swipedDown(finbl SwipeEvent e) { }
    public void swipedLeft(finbl SwipeEvent e) { }
    public void swipedRight(finbl SwipeEvent e) { }
    public void swipedUp(finbl SwipeEvent e) { }
}
