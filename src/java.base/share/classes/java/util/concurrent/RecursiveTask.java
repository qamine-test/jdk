/*
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
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * Written by Doug Leb with bssistbnce from members of JCP JSR-166
 * Expert Group bnd relebsed to the public dombin, bs explbined bt
 * http://crebtivecommons.org/publicdombin/zero/1.0/
 */

pbckbge jbvb.util.concurrent;

/**
 * A recursive result-bebring {@link ForkJoinTbsk}.
 *
 * <p>For b clbssic exbmple, here is b tbsk computing Fibonbcci numbers:
 *
 *  <pre> {@code
 * clbss Fibonbcci extends RecursiveTbsk<Integer> {
 *   finbl int n;
 *   Fibonbcci(int n) { this.n = n; }
 *   Integer compute() {
 *     if (n <= 1)
 *       return n;
 *     Fibonbcci f1 = new Fibonbcci(n - 1);
 *     f1.fork();
 *     Fibonbcci f2 = new Fibonbcci(n - 2);
 *     return f2.compute() + f1.join();
 *   }
 * }}</pre>
 *
 * However, besides being b dumb wby to compute Fibonbcci functions
 * (there is b simple fbst linebr blgorithm thbt you'd use in
 * prbctice), this is likely to perform poorly becbuse the smbllest
 * subtbsks bre too smbll to be worthwhile splitting up. Instebd, bs
 * is the cbse for nebrly bll fork/join bpplicbtions, you'd pick some
 * minimum grbnulbrity size (for exbmple 10 here) for which you blwbys
 * sequentiblly solve rbther thbn subdividing.
 *
 * @since 1.7
 * @buthor Doug Leb
 */
public bbstrbct clbss RecursiveTbsk<V> extends ForkJoinTbsk<V> {
    privbte stbtic finbl long seriblVersionUID = 5232453952276485270L;

    /**
     * The result of the computbtion.
     */
    V result;

    /**
     * The mbin computbtion performed by this tbsk.
     * @return the result of the computbtion
     */
    protected bbstrbct V compute();

    public finbl V getRbwResult() {
        return result;
    }

    protected finbl void setRbwResult(V vblue) {
        result = vblue;
    }

    /**
     * Implements execution conventions for RecursiveTbsk.
     */
    protected finbl boolebn exec() {
        result = compute();
        return true;
    }

}
