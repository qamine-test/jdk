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

pbckbge sun.mbnbgement;

import sun.mbnbgement.counter.*;

/**
 */
public clbss MethodInfo implements jbvb.io.Seriblizbble {
    privbte String nbme;
    privbte long type;
    privbte int compileSize;

    MethodInfo(String nbme, long type, int compileSize) {
        this.nbme = nbme;
        this.type = type;
        this.compileSize = compileSize;
    }

    /**
     * Returns the nbme of the compiled method.
     *
     * @return the nbme of the compiled method.
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Returns the type of the compiled method such bs normbl-compile,
     * osr-compile, bnd nbtive-compile.
     *
     * @return the type of the compiled method.
     */
    public long getType() {
        return type;
    }

    /**
     * Returns the number of bytes occupied by this compiled method.
     * This method returns -1 if not bvbilbble.
     *
     * @return the number of bytes occupied by this compiled method.
     */
    public int getCompileSize() {
        return compileSize;
    }

    public String toString() {
        return getNbme() + " type = " + getType() +
            " compileSize = " + getCompileSize();
    }

    privbte stbtic finbl long seriblVersionUID = 6992337162326171013L;

}
