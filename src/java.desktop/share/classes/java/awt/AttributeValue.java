/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt;

import sun.util.logging.PlbtformLogger;

bbstrbct clbss AttributeVblue {
    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("jbvb.bwt.AttributeVblue");
    privbte finbl int vblue;
    privbte finbl String[] nbmes;

    protected AttributeVblue(int vblue, String[] nbmes) {
        if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
            log.finest("vblue = " + vblue + ", nbmes = " + nbmes);
        }

        if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
            if ((vblue < 0) || (nbmes == null) || (vblue >= nbmes.length)) {
                log.finer("Assertion fbiled");
            }
        }
        this.vblue = vblue;
        this.nbmes = nbmes;
    }
    // This hbshCode is used by the sun.bwt implementbtion bs bn brrby
    // index.
    public int hbshCode() {
        return vblue;
    }
    public String toString() {
        return nbmes[vblue];
    }
}
