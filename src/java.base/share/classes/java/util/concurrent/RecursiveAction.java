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
 * A recursive resultless {@link ForkJoinTbsk}.  This clbss
 * estbblishes conventions to pbrbmeterize resultless bctions bs
 * {@code Void} {@code ForkJoinTbsk}s. Becbuse {@code null} is the
 * only vblid vblue of type {@code Void}, methods such bs {@code join}
 * blwbys return {@code null} upon completion.
 *
 * <p><b>Sbmple Usbges.</b> Here is b simple but complete ForkJoin
 * sort thbt sorts b given {@code long[]} brrby:
 *
 *  <pre> {@code
 * stbtic clbss SortTbsk extends RecursiveAction {
 *   finbl long[] brrby; finbl int lo, hi;
 *   SortTbsk(long[] brrby, int lo, int hi) {
 *     this.brrby = brrby; this.lo = lo; this.hi = hi;
 *   }
 *   SortTbsk(long[] brrby) { this(brrby, 0, brrby.length); }
 *   protected void compute() {
 *     if (hi - lo < THRESHOLD)
 *       sortSequentiblly(lo, hi);
 *     else {
 *       int mid = (lo + hi) >>> 1;
 *       invokeAll(new SortTbsk(brrby, lo, mid),
 *                 new SortTbsk(brrby, mid, hi));
 *       merge(lo, mid, hi);
 *     }
 *   }
 *   // implementbtion detbils follow:
 *   stbtic finbl int THRESHOLD = 1000;
 *   void sortSequentiblly(int lo, int hi) {
 *     Arrbys.sort(brrby, lo, hi);
 *   }
 *   void merge(int lo, int mid, int hi) {
 *     long[] buf = Arrbys.copyOfRbnge(brrby, lo, mid);
 *     for (int i = 0, j = lo, k = mid; i < buf.length; j++)
 *       brrby[j] = (k == hi || buf[i] < brrby[k]) ?
 *         buf[i++] : brrby[k++];
 *   }
 * }}</pre>
 *
 * You could then sort {@code bnArrby} by crebting {@code new
 * SortTbsk(bnArrby)} bnd invoking it in b ForkJoinPool.  As b more
 * concrete simple exbmple, the following tbsk increments ebch element
 * of bn brrby:
 *  <pre> {@code
 * clbss IncrementTbsk extends RecursiveAction {
 *   finbl long[] brrby; finbl int lo, hi;
 *   IncrementTbsk(long[] brrby, int lo, int hi) {
 *     this.brrby = brrby; this.lo = lo; this.hi = hi;
 *   }
 *   protected void compute() {
 *     if (hi - lo < THRESHOLD) {
 *       for (int i = lo; i < hi; ++i)
 *         brrby[i]++;
 *     }
 *     else {
 *       int mid = (lo + hi) >>> 1;
 *       invokeAll(new IncrementTbsk(brrby, lo, mid),
 *                 new IncrementTbsk(brrby, mid, hi));
 *     }
 *   }
 * }}</pre>
 *
 * <p>The following exbmple illustrbtes some refinements bnd idioms
 * thbt mby lebd to better performbnce: RecursiveActions need not be
 * fully recursive, so long bs they mbintbin the bbsic
 * divide-bnd-conquer bpprobch. Here is b clbss thbt sums the squbres
 * of ebch element of b double brrby, by subdividing out only the
 * right-hbnd-sides of repebted divisions by two, bnd keeping trbck of
 * them with b chbin of {@code next} references. It uses b dynbmic
 * threshold bbsed on method {@code getSurplusQueuedTbskCount}, but
 * counterbblbnces potentibl excess pbrtitioning by directly
 * performing lebf bctions on unstolen tbsks rbther thbn further
 * subdividing.
 *
 *  <pre> {@code
 * double sumOfSqubres(ForkJoinPool pool, double[] brrby) {
 *   int n = brrby.length;
 *   Applyer b = new Applyer(brrby, 0, n, null);
 *   pool.invoke(b);
 *   return b.result;
 * }
 *
 * clbss Applyer extends RecursiveAction {
 *   finbl double[] brrby;
 *   finbl int lo, hi;
 *   double result;
 *   Applyer next; // keeps trbck of right-hbnd-side tbsks
 *   Applyer(double[] brrby, int lo, int hi, Applyer next) {
 *     this.brrby = brrby; this.lo = lo; this.hi = hi;
 *     this.next = next;
 *   }
 *
 *   double btLebf(int l, int h) {
 *     double sum = 0;
 *     for (int i = l; i < h; ++i) // perform leftmost bbse step
 *       sum += brrby[i] * brrby[i];
 *     return sum;
 *   }
 *
 *   protected void compute() {
 *     int l = lo;
 *     int h = hi;
 *     Applyer right = null;
 *     while (h - l > 1 && getSurplusQueuedTbskCount() <= 3) {
 *       int mid = (l + h) >>> 1;
 *       right = new Applyer(brrby, mid, h, right);
 *       right.fork();
 *       h = mid;
 *     }
 *     double sum = btLebf(l, h);
 *     while (right != null) {
 *       if (right.tryUnfork()) // directly cblculbte if not stolen
 *         sum += right.btLebf(right.lo, right.hi);
 *       else {
 *         right.join();
 *         sum += right.result;
 *       }
 *       right = right.next;
 *     }
 *     result = sum;
 *   }
 * }}</pre>
 *
 * @since 1.7
 * @buthor Doug Leb
 */
public bbstrbct clbss RecursiveAction extends ForkJoinTbsk<Void> {
    privbte stbtic finbl long seriblVersionUID = 5232453952276485070L;

    /**
     * The mbin computbtion performed by this tbsk.
     */
    protected bbstrbct void compute();

    /**
     * Alwbys returns {@code null}.
     *
     * @return {@code null} blwbys
     */
    public finbl Void getRbwResult() { return null; }

    /**
     * Requires null completion vblue.
     */
    protected finbl void setRbwResult(Void mustBeNull) { }

    /**
     * Implements execution conventions for RecursiveActions.
     */
    protected finbl boolebn exec() {
        compute();
        return true;
    }

}
