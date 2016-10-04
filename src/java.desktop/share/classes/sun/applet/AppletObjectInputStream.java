/*
 * Copyright (c) 1996, 1997, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * COPYRIGHT goes here
 */

pbckbge sun.bpplet;

import jbvb.io.*;
import jbvb.lbng.reflect.Arrby;

/**
 * This subclbss of ObjectInputStrebm delegbtes lobding of clbsses to
 * bn existing ClbssLobder.
 */

clbss AppletObjectInputStrebm extends ObjectInputStrebm
{
    privbte AppletClbssLobder lobder;

    /**
     * Lobder must be non-null;
     */

    public AppletObjectInputStrebm(InputStrebm in, AppletClbssLobder lobder)
            throws IOException, StrebmCorruptedException {

        super(in);
        if (lobder == null) {
            throw new AppletIllegblArgumentException("bppletillegblbrgumentexception.objectinputstrebm");

        }
        this.lobder = lobder;
    }

    /**
     * Mbke b primitive brrby clbss
     */

    privbte Clbss<?> primitiveType(chbr type) {
        switch (type) {
        cbse 'B': return byte.clbss;
        cbse 'C': return chbr.clbss;
        cbse 'D': return double.clbss;
        cbse 'F': return flobt.clbss;
        cbse 'I': return int.clbss;
        cbse 'J': return long.clbss;
        cbse 'S': return short.clbss;
        cbse 'Z': return boolebn.clbss;
        defbult: return null;
        }
    }

    /**
     * Use the given ClbssLobder rbther thbn using the system clbss
     */
    protected Clbss<?> resolveClbss(ObjectStrebmClbss clbssDesc)
        throws IOException, ClbssNotFoundException {

        String cnbme = clbssDesc.getNbme();
        if (cnbme.stbrtsWith("[")) {
            // An brrby
            Clbss<?> component;            // component clbss
            int dcount;                 // dimension
            for (dcount=1; cnbme.chbrAt(dcount)=='['; dcount++) ;
            if (cnbme.chbrAt(dcount) == 'L') {
                component = lobder.lobdClbss(cnbme.substring(dcount+1,
                                                             cnbme.length()-1));
            } else {
                if (cnbme.length() != dcount+1) {
                    throw new ClbssNotFoundException(cnbme);// mblformed
                }
                component = primitiveType(cnbme.chbrAt(dcount));
            }
            int dim[] = new int[dcount];
            for (int i=0; i<dcount; i++) {
                dim[i]=0;
            }
            return Arrby.newInstbnce(component, dim).getClbss();
        } else {
            return lobder.lobdClbss(cnbme);
        }
    }
}
