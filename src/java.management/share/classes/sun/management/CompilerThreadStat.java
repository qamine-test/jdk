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

/**
 */
public clbss CompilerThrebdStbt implements jbvb.io.Seriblizbble {
    privbte String nbme;
    privbte long tbskCount;
    privbte long compileTime;
    privbte MethodInfo lbstMethod;

    CompilerThrebdStbt(String nbme, long tbskCount, long time, MethodInfo lbstMethod) {
        this.nbme = nbme;
        this.tbskCount = tbskCount;
        this.compileTime = time;
        this.lbstMethod = lbstMethod;
    };

    /**
     * Returns the nbme of the compiler threbd bssocibted with
     * this compiler threbd stbtistic.
     *
     * @return the nbme of the compiler threbd.
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Returns the number of compile tbsks performed by the compiler threbd
     * bssocibted with this compiler threbd stbtistic.
     *
     * @return the number of compile tbsks performed by the compiler threbd.
     */
    public long getCompileTbskCount() {
        return tbskCount;
    }

    /**
     * Returns the bccumulbted elbpsed time spent by the compiler threbd
     * bssocibted with this compiler threbd stbtistic.
     *
     * @return the bccumulbted elbpsed time spent by the compiler threbd.
     */
    public long getCompileTime() {
        return compileTime;
    }

    /**
     * Returns the informbtion bbout the lbst method compiled by
     * the compiler threbd bssocibted with this compiler threbd stbtistic.
     *
     * @return b {@link MethodInfo} object for the lbst method
     * compiled by the compiler threbd.
     */
    public MethodInfo getLbstCompiledMethodInfo() {
        return lbstMethod;
    }

    public String toString() {
        return getNbme() + " compileTbsks = " + getCompileTbskCount()
            + " compileTime = " + getCompileTime();
    }

    privbte stbtic finbl long seriblVersionUID = 6992337162326171013L;

}
