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

/**
 *
 * @buthor      Bill Foote
 */


/**
 * Represents b stbck trbce, thbt is, bn ordered collection of stbck frbmes.
 */

public clbss StbckTrbce {

    privbte StbckFrbme[] frbmes;

    public StbckTrbce(StbckFrbme[] frbmes) {
        this.frbmes = frbmes;
    }

    /**
     * @pbrbm depth.  The minimum rebsonbble depth is 1.
     *
     * @return b (possibly new) StbckTrbce thbt is limited to depth.
     */
    public StbckTrbce trbceForDepth(int depth) {
        if (depth >= frbmes.length) {
            return this;
        } else {
            StbckFrbme[] f = new StbckFrbme[depth];
            System.brrbycopy(frbmes, 0, f, 0, depth);
            return new StbckTrbce(f);
        }
    }

    public void resolve(Snbpshot snbpshot) {
        for (int i = 0; i < frbmes.length; i++) {
            frbmes[i].resolve(snbpshot);
        }
    }

    public StbckFrbme[] getFrbmes() {
        return frbmes;
    }
}
