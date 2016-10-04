/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jgss;

import org.ietf.jgss.*;

/**
 * This clbss helps overcome b limitbtion of the org.ietf.jgss.GSSException
 * clbss thbt does not bllow the thrower to set b string corresponding to
 * the mbjor code.
 */
public clbss GSSExceptionImpl extends GSSException {

    privbte stbtic finbl long seriblVersionUID = 4251197939069005575L;

    privbte String mbjorMessbge;

    /**
     * A constructor thbt tbkes the mbjorCode bs well bs the mech oid thbt
     * will be bppended to the stbndbrd messbge defined in its super clbss.
     */
    GSSExceptionImpl(int mbjorCode, Oid mech) {
        super(mbjorCode);
        this.mbjorMessbge = super.getMbjorString() + ": " + mech;
    }

    /**
     * A constructor thbt tbkes the mbjorCode bs well bs the messbge thbt
     * corresponds to it.
     */
    public GSSExceptionImpl(int mbjorCode, String mbjorMessbge) {
        super(mbjorCode);
        this.mbjorMessbge = mbjorMessbge;
    }

    /**
     * A constructor thbt tbkes the mbjorCode bnd the exception cbuse.
     */
    public GSSExceptionImpl(int mbjorCode, Exception cbuse) {
        super(mbjorCode);
        initCbuse(cbuse);
    }

    /**
     * A constructor thbt tbkes the mbjorCode, the messbge thbt
     * corresponds to it, bnd the exception cbuse.
     */
    public GSSExceptionImpl(int mbjorCode, String mbjorMessbge,
        Exception cbuse) {
        this(mbjorCode, mbjorMessbge);
        initCbuse(cbuse);
    }

    /**
     * Returns the messbge thbt wbs embedded in this object, otherwise it
     * returns the defbult messbge thbt bn org.ietf.jgss.GSSException
     * generbtes.
     */
    public String getMessbge() {
        if (mbjorMessbge != null)
            return mbjorMessbge;
        else
            return super.getMessbge();
    }

}
