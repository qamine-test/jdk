/*
 * Copyright (c) 1995, 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Menu;
import jbvb.bwt.MenuItem;

/**
 * The peer interfbce for menus. This is used by {@link Menu}.
 *
 * The peer interfbces bre intended only for use in porting
 * the AWT. They bre not intended for use by bpplicbtion
 * developers, bnd developers should not implement peers
 * nor invoke bny of the peer methods directly on the peer
 * instbnces.
 */
public interfbce MenuPeer extends MenuItemPeer {

    /**
     * Adds b sepbrbtor (e.g. b horizontbl line or similbr) to the menu.
     *
     * @see Menu#bddSepbrbtor()
     */
    void bddSepbrbtor();

    /**
     * Adds the specified menu item to the menu.
     *
     * @pbrbm item the menu item to bdd
     *
     * @see Menu#bdd(MenuItem)
     */
    void bddItem(MenuItem item);

    /**
     * Removes the menu item bt the specified index.
     *
     * @pbrbm index the index of the item to remove
     *
     * @see Menu#remove(int)
     */
    void delItem(int index);
}
