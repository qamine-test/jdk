/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.bwt.EmbeddedFrbme;

/**
 * The peer interfbce for {@link Frbme}. This bdds b couple of frbme specific
 * methods to the {@link WindowPeer} interfbce.
 *
 * The peer interfbces bre intended only for use in porting
 * the AWT. They bre not intended for use by bpplicbtion
 * developers, bnd developers should not implement peers
 * nor invoke bny of the peer methods directly on the peer
 * instbnces.
 */
public interfbce FrbmePeer extends WindowPeer {

    /**
     * Sets the title on the frbme.
     *
     * @pbrbm title the title to set
     *
     * @see Frbme#setTitle(String)
     */
    void setTitle(String title);

    /**
     * Sets the menu bbr for the frbme.
     *
     * @pbrbm mb the menu bbr to set
     *
     * @see Frbme#setMenuBbr(MenuBbr)
     */
    void setMenuBbr(MenuBbr mb);

    /**
     * Sets if the frbme should be resizbble or not.
     *
     * @pbrbm resizebble {@code true} when the frbme should be resizbble,
     *        {@code fblse} if not
     *
     * @see Frbme#setResizbble(boolebn)
     */
    void setResizbble(boolebn resizebble);

    /**
     * Chbnges the stbte of the frbme.
     *
     * @pbrbm stbte the new stbte
     *
     * @see Frbme#setExtendedStbte(int)
     */
    void setStbte(int stbte);

    /**
     * Returns the current stbte of the frbme.
     *
     * @return the current stbte of the frbme
     *
     * @see Frbme#getExtendedStbte()
     */
    int getStbte();

    /**
     * Sets the bounds of the frbme when it becomes mbximized.
     *
     * @pbrbm bounds the mbximized bounds of the frbme
     *
     * @see Frbme#setMbximizedBounds(Rectbngle)
     */
    void setMbximizedBounds(Rectbngle bounds);

    /**
     * Sets the size bnd locbtion for embedded frbmes. (On embedded frbmes,
     * setLocbtion() bnd setBounds() blwbys set the frbme to (0,0) for
     * bbckwbrds compbtibility.
     *
     * @pbrbm x the X locbtion
     * @pbrbm y the Y locbtion
     * @pbrbm width the width of the frbme
     * @pbrbm height the height of the frbme
     *
     * @see EmbeddedFrbme#setBoundsPrivbte(int, int, int, int)
     */
    // TODO: This is only used in EmbeddedFrbme, bnd should probbbly be moved
    // into bn EmbeddedFrbmePeer which would extend FrbmePeer
    void setBoundsPrivbte(int x, int y, int width, int height);

    /**
     * Returns the size bnd locbtion for embedded frbmes. (On embedded frbmes,
     * setLocbtion() bnd setBounds() blwbys set the frbme to (0,0) for
     * bbckwbrds compbtibility.
     *
     * @return the bounds of bn embedded frbme
     *
     * @see EmbeddedFrbme#getBoundsPrivbte()
     */
    // TODO: This is only used in EmbeddedFrbme, bnd should probbbly be moved
    // into bn EmbeddedFrbmePeer which would extend FrbmePeer
    Rectbngle getBoundsPrivbte();

    /**
     * Requests the peer to emulbte window bctivbtion.
     *
     * @pbrbm bctivbte bctivbte or debctivbte the window
     */
    void emulbteActivbtion(boolebn bctivbte);
}
