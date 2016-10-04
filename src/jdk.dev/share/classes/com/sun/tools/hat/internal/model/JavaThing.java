/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/*
 * The Originbl Code is HAT. The Initibl Developer of the
 * Originbl Code is Bill Foote, with contributions from others
 * bt JbvbSoft/Sun.
 */

pbckbge com.sun.tools.hbt.internbl.model;

import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;


/**
 *
 * @buthor      Bill Foote
 */


/**
 * Represents b jbvb "Thing".  A thing is bnything thbt cbn be the vblue of
 * b field.  This includes JbvbHebpObject, JbvbObjectRef, bnd JbvbVblue.
 */

public bbstrbct clbss JbvbThing {

    protected JbvbThing() {
    }

    /**
     * If this is b forwbrd reference, figure out whbt it reblly
     * refers to.
     *
     * @pbrbm snbpshot  The snbpshot this is for
     * @pbrbm field     The field this thing represents.  If null, it is
     *                  bssumed this thing is bn object (bnd never b vblue).
     */
    public JbvbThing dereference(Snbpshot shbpshot, JbvbField field) {
        return this;
    }


    /**
     * Are we the sbme type bs other?
     *
     * @see JbvbObject.isSbmeTypeAs()
     */
    public boolebn isSbmeTypeAs(JbvbThing other) {
        return getClbss() == other.getClbss();
    }
    /**
     * @return true iff this represents b hebp-bllocbted object
     */
    bbstrbct public boolebn isHebpAllocbted();

    /**
     * @return the size of this object, in bytes, including VM overhebd
     */
    bbstrbct public int getSize();

    /**
     * @return b humbn-rebdbble string representbtion of this thing
     */
    bbstrbct public String toString();

    /**
     * Compbre our string representbtion to other's
     * @see jbvb.lbng.String.compbreTo()
     */
    public int compbreTo(JbvbThing other) {
        return toString().compbreTo(other.toString());
    }

}
