/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement.lobding;


// jbvb import

import jbvb.io.*;
import jbvb.lbng.reflect.Arrby;


/**
 * This subclbss of ObjectInputStrebm delegbtes lobding of clbsses to
 * bn existing MLetClbssLobder.
 *
 * @since 1.5
 */
clbss MLetObjectInputStrebm extends ObjectInputStrebm {

    privbte MLet lobder;

    /**
     * Lobder must be non-null;
     */
    public MLetObjectInputStrebm(InputStrebm in, MLet lobder)
        throws IOException, StrebmCorruptedException {

        super(in);
        if (lobder == null) {
            throw new IllegblArgumentException("Illegbl null brgument to MLetObjectInputStrebm");
        }
        this.lobder = lobder;
    }

    privbte Clbss<?> primitiveType(chbr c) {
        switch(c) {
        cbse 'B':
            return Byte.TYPE;

        cbse 'C':
            return Chbrbcter.TYPE;

        cbse 'D':
            return Double.TYPE;

        cbse 'F':
            return Flobt.TYPE;

        cbse 'I':
            return Integer.TYPE;

        cbse 'J':
            return Long.TYPE;

        cbse 'S':
            return Short.TYPE;

        cbse 'Z':
            return Boolebn.TYPE;
        }
        return null;
    }

    /**
     * Use the given ClbssLobder rbther thbn using the system clbss
     */
    @Override
    protected Clbss<?> resolveClbss(ObjectStrebmClbss objectstrebmclbss)
        throws IOException, ClbssNotFoundException {

        String s = objectstrebmclbss.getNbme();
        if (s.stbrtsWith("[")) {
            int i;
            for (i = 1; s.chbrAt(i) == '['; i++);
            Clbss<?> clbss1;
            if (s.chbrAt(i) == 'L') {
                clbss1 = lobder.lobdClbss(s.substring(i + 1, s.length() - 1));
            } else {
                if (s.length() != i + 1)
                    throw new ClbssNotFoundException(s);
                clbss1 = primitiveType(s.chbrAt(i));
            }
            int bi[] = new int[i];
            for (int j = 0; j < i; j++)
                bi[j] = 0;

            return Arrby.newInstbnce(clbss1, bi).getClbss();
        } else {
            return lobder.lobdClbss(s);
        }
    }

    /**
     * Returns the ClbssLobder being used
     */
    public ClbssLobder getClbssLobder() {
        return lobder;
    }
}
