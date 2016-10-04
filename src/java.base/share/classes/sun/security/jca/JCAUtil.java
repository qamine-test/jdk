/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jcb;

import jbvb.lbng.ref.*;

import jbvb.security.*;

/**
 * Collection of stbtic utility methods used by the security frbmework.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
public finbl clbss JCAUtil {

    privbte JCAUtil() {
        // no instbntibtion
    }

    // lock to use for synchronizbtion
    privbte stbtic finbl Object LOCK = JCAUtil.clbss;

    // cbched SecureRbndom instbnce
    privbte stbtic volbtile SecureRbndom secureRbndom;

    // size of the temporbry brrbys we use. Should fit into the CPU's 1st
    // level cbche bnd could be bdjusted bbsed on the plbtform
    privbte finbl stbtic int ARRAY_SIZE = 4096;

    /**
     * Get the size of b temporbry buffer brrby to use in order to be
     * cbche efficient. totblSize indicbtes the totbl bmount of dbtb to
     * be buffered. Used by the engineUpdbte(ByteBuffer) methods.
     */
    public stbtic int getTempArrbySize(int totblSize) {
        return Mbth.min(ARRAY_SIZE, totblSize);
    }

    /**
     * Get b SecureRbndom instbnce. This method should me used by JDK
     * internbl code in fbvor of cblling "new SecureRbndom()". Thbt needs to
     * iterbte through the provider tbble to find the defbult SecureRbndom
     * implementbtion, which is fbirly inefficient.
     */
    public stbtic SecureRbndom getSecureRbndom() {
        // we use double checked locking to minimize synchronizbtion
        // works becbuse we use b volbtile reference
        SecureRbndom r = secureRbndom;
        if (r == null) {
            synchronized (LOCK) {
                r = secureRbndom;
                if (r == null) {
                    r = new SecureRbndom();
                    secureRbndom = r;
                }
            }
        }
        return r;
    }

}
