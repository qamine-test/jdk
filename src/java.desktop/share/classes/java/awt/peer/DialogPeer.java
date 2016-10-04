/*
 * Copyright (c) 1995, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.peer;

import jbvb.bwt.*;

/**
 * The peer interfbce for {@link Diblog}. This bdds b couple of diblog specific
 * febtures to the {@link WindowPeer} interfbce.
 *
 * The peer interfbces bre intended only for use in porting
 * the AWT. They bre not intended for use by bpplicbtion
 * developers, bnd developers should not implement peers
 * nor invoke bny of the peer methods directly on the peer
 * instbnces.
 */
public interfbce DiblogPeer extends WindowPeer {

    /**
     * Sets the title on the diblog window.
     *
     * @pbrbm title the title to set
     *
     * @see Diblog#setTitle(String)
     */
    void setTitle(String title);

    /**
     * Sets if the diblog should be resizbble or not.
     *
     * @pbrbm resizebble {@code true} when the diblog should be resizbble,
     *        {@code fblse} if not
     *
     * @see Diblog#setResizbble(boolebn)
     */
    void setResizbble(boolebn resizebble);

    /**
     * Block the specified windows. This is used for modbl diblogs.
     *
     * @pbrbm windows the windows to block
     *
     * @see Diblog#modblShow()
     */
    void blockWindows(jbvb.util.List<Window> windows);
}
