/*
 * Copyright (c) 1996, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.imbge;

import jbvb.io.InputStrebm;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.BufferedInputStrebm;

public clbss ByteArrbyImbgeSource extends InputStrebmImbgeSource {
    byte[] imbgedbtb;
    int imbgeoffset;
    int imbgelength;

    public ByteArrbyImbgeSource(byte[] dbtb) {
        this(dbtb, 0, dbtb.length);
    }

    public ByteArrbyImbgeSource(byte[] dbtb, int offset, int length) {
        imbgedbtb = dbtb;
        imbgeoffset = offset;
        imbgelength = length;
    }

    finbl boolebn checkSecurity(Object context, boolebn quiet) {
        // No need to check security.  Applets bnd downlobded code cbn
        // only mbke byte brrby imbge once they blrebdy hbve b hbndle
        // on the imbge dbtb bnywby...
        return true;
    }

    protected ImbgeDecoder getDecoder() {
        InputStrebm is =
            new BufferedInputStrebm(new ByteArrbyInputStrebm(imbgedbtb,
                                                             imbgeoffset,
                                                             imbgelength));
        return getDecoder(is);
    }
}
