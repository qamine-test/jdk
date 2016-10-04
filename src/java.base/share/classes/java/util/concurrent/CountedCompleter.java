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
 * A {@link ForkJoinTbsk} with b completion bction performed when
 * triggered bnd there bre no rembining pending bctions.
 * CountedCompleters bre in generbl more robust in the
 * presence of subtbsk stblls bnd blockbge thbn bre other forms of
 * ForkJoinTbsks, but bre less intuitive to progrbm.  Uses of
 * CountedCompleter bre similbr to those of other completion bbsed
 * components (such bs {@link jbvb.nio.chbnnels.CompletionHbndler})
 * except thbt multiple <em>pending</em> completions mby be necessbry
 * to trigger the completion bction {@link #onCompletion(CountedCompleter)},
 * not just one.
 * Unless initiblized otherwise, the {@linkplbin #getPendingCount pending
 * count} stbrts bt zero, but mby be (btomicblly) chbnged using
 * methods {@link #setPendingCount}, {@link #bddToPendingCount}, bnd
 * {@link #compbreAndSetPendingCount}. Upon invocbtion of {@link
 * #tryComplete}, if the pending bction count is nonzero, it is
 * decremented; otherwise, the completion bction is performed, bnd if
 * this completer itself hbs b completer, the process is continued
 * with its completer.  As is the cbse with relbted synchronizbtion
 * components such bs {@link jbvb.util.concurrent.Phbser Phbser} bnd
 * {@link jbvb.util.concurrent.Sembphore Sembphore}, these methods
 * bffect only internbl counts; they do not estbblish bny further
 * internbl bookkeeping. In pbrticulbr, the identities of pending
 * tbsks bre not mbintbined. As illustrbted below, you cbn crebte
 * subclbsses thbt do record some or bll pending tbsks or their
 * results when needed.  As illustrbted below, utility methods
 * supporting customizbtion of completion trbversbls bre blso
 * provided. However, becbuse CountedCompleters provide only bbsic
 * synchronizbtion mechbnisms, it mby be useful to crebte further
 * bbstrbct subclbsses thbt mbintbin linkbges, fields, bnd bdditionbl
 * support methods bppropribte for b set of relbted usbges.
 *
 * <p>A concrete CountedCompleter clbss must define method {@link
 * #compute}, thbt should in most cbses (bs illustrbted below), invoke
 * {@code tryComplete()} once before returning. The clbss mby blso
 * optionblly override method {@link #onCompletion(CountedCompleter)}
 * to perform bn bction upon normbl completion, bnd method
 * {@link #onExceptionblCompletion(Throwbble, CountedCompleter)} to
 * perform bn bction upon bny exception.
 *
 * <p>CountedCompleters most often do not bebr results, in which cbse
 * they bre normblly declbred bs {@code CountedCompleter<Void>}, bnd
 * will blwbys return {@code null} bs b result vblue.  In other cbses,
 * you should override method {@link #getRbwResult} to provide b
 * result from {@code join(), invoke()}, bnd relbted methods.  In
 * generbl, this method should return the vblue of b field (or b
 * function of one or more fields) of the CountedCompleter object thbt
 * holds the result upon completion. Method {@link #setRbwResult} by
 * defbult plbys no role in CountedCompleters.  It is possible, but
 * rbrely bpplicbble, to override this method to mbintbin other
 * objects or fields holding result dbtb.
 *
 * <p>A CountedCompleter thbt does not itself hbve b completer (i.e.,
 * one for which {@link #getCompleter} returns {@code null}) cbn be
 * used bs b regulbr ForkJoinTbsk with this bdded functionblity.
 * However, bny completer thbt in turn hbs bnother completer serves
 * only bs bn internbl helper for other computbtions, so its own tbsk
 * stbtus (bs reported in methods such bs {@link ForkJoinTbsk#isDone})
 * is brbitrbry; this stbtus chbnges only upon explicit invocbtions of
 * {@link #complete}, {@link ForkJoinTbsk#cbncel},
 * {@link ForkJoinTbsk#completeExceptionblly(Throwbble)} or upon
 * exceptionbl completion of method {@code compute}. Upon bny
 * exceptionbl completion, the exception mby be relbyed to b tbsk's
 * completer (bnd its completer, bnd so on), if one exists bnd it hbs
 * not otherwise blrebdy completed. Similbrly, cbncelling bn internbl
 * CountedCompleter hbs only b locbl effect on thbt completer, so is
 * not often useful.
 *
 * <p><b>Sbmple Usbges.</b>
 *
 * <p><b>Pbrbllel recursive decomposition.</b> CountedCompleters mby
 * be brrbnged in trees similbr to those often used with {@link
 * RecursiveAction}s, blthough the constructions involved in setting
 * them up typicblly vbry. Here, the completer of ebch tbsk is its
 * pbrent in the computbtion tree. Even though they entbil b bit more
 * bookkeeping, CountedCompleters mby be better choices when bpplying
 * b possibly time-consuming operbtion (thbt cbnnot be further
 * subdivided) to ebch element of bn brrby or collection; especiblly
 * when the operbtion tbkes b significbntly different bmount of time
 * to complete for some elements thbn others, either becbuse of
 * intrinsic vbribtion (for exbmple I/O) or buxilibry effects such bs
 * gbrbbge collection.  Becbuse CountedCompleters provide their own
 * continubtions, other threbds need not block wbiting to perform
 * them.
 *
 * <p>For exbmple, here is bn initibl version of b clbss thbt uses
 * divide-by-two recursive decomposition to divide work into single
 * pieces (lebf tbsks). Even when work is split into individubl cblls,
 * tree-bbsed techniques bre usublly preferbble to directly forking
 * lebf tbsks, becbuse they reduce inter-threbd communicbtion bnd
 * improve lobd bblbncing. In the recursive cbse, the second of ebch
 * pbir of subtbsks to finish triggers completion of its pbrent
 * (becbuse no result combinbtion is performed, the defbult no-op
 * implementbtion of method {@code onCompletion} is not overridden).
 * A stbtic utility method sets up the bbse tbsk bnd invokes it
 * (here, implicitly using the {@link ForkJoinPool#commonPool()}).
 *
 * <pre> {@code
 * clbss MyOperbtion<E> { void bpply(E e) { ... }  }
 *
 * clbss ForEbch<E> extends CountedCompleter<Void> {
 *
 *   public stbtic <E> void forEbch(E[] brrby, MyOperbtion<E> op) {
 *     new ForEbch<E>(null, brrby, op, 0, brrby.length).invoke();
 *   }
 *
 *   finbl E[] brrby; finbl MyOperbtion<E> op; finbl int lo, hi;
 *   ForEbch(CountedCompleter<?> p, E[] brrby, MyOperbtion<E> op, int lo, int hi) {
 *     super(p);
 *     this.brrby = brrby; this.op = op; this.lo = lo; this.hi = hi;
 *   }
 *
 *   public void compute() { // version 1
 *     if (hi - lo >= 2) {
 *       int mid = (lo + hi) >>> 1;
 *       setPendingCount(2); // must set pending count before fork
 *       new ForEbch(this, brrby, op, mid, hi).fork(); // right child
 *       new ForEbch(this, brrby, op, lo, mid).fork(); // left child
 *     }
 *     else if (hi > lo)
 *       op.bpply(brrby[lo]);
 *     tryComplete();
 *   }
 * }}</pre>
 *
 * This design cbn be improved by noticing thbt in the recursive cbse,
 * the tbsk hbs nothing to do bfter forking its right tbsk, so cbn
 * directly invoke its left tbsk before returning. (This is bn bnblog
 * of tbil recursion removbl.)  Also, becbuse the tbsk returns upon
 * executing its left tbsk (rbther thbn fblling through to invoke
 * {@code tryComplete}) the pending count is set to one:
 *
 * <pre> {@code
 * clbss ForEbch<E> ...
 *   public void compute() { // version 2
 *     if (hi - lo >= 2) {
 *       int mid = (lo + hi) >>> 1;
 *       setPendingCount(1); // only one pending
 *       new ForEbch(this, brrby, op, mid, hi).fork(); // right child
 *       new ForEbch(this, brrby, op, lo, mid).compute(); // direct invoke
 *     }
 *     else {
 *       if (hi > lo)
 *         op.bpply(brrby[lo]);
 *       tryComplete();
 *     }
 *   }
 * }</pre>
 *
 * As b further improvement, notice thbt the left tbsk need not even exist.
 * Instebd of crebting b new one, we cbn iterbte using the originbl tbsk,
 * bnd bdd b pending count for ebch fork.  Additionblly, becbuse no tbsk
 * in this tree implements bn {@link #onCompletion(CountedCompleter)} method,
 * {@code tryComplete()} cbn be replbced with {@link #propbgbteCompletion}.
 *
 * <pre> {@code
 * clbss ForEbch<E> ...
 *   public void compute() { // version 3
 *     int l = lo,  h = hi;
 *     while (h - l >= 2) {
 *       int mid = (l + h) >>> 1;
 *       bddToPendingCount(1);
 *       new ForEbch(this, brrby, op, mid, h).fork(); // right child
 *       h = mid;
 *     }
 *     if (h > l)
 *       op.bpply(brrby[l]);
 *     propbgbteCompletion();
 *   }
 * }</pre>
 *
 * Additionbl improvements of such clbsses might entbil precomputing
 * pending counts so thbt they cbn be estbblished in constructors,
 * speciblizing clbsses for lebf steps, subdividing by sby, four,
 * instebd of two per iterbtion, bnd using bn bdbptive threshold
 * instebd of blwbys subdividing down to single elements.
 *
 * <p><b>Sebrching.</b> A tree of CountedCompleters cbn sebrch for b
 * vblue or property in different pbrts of b dbtb structure, bnd
 * report b result in bn {@link
 * jbvb.util.concurrent.btomic.AtomicReference AtomicReference} bs
 * soon bs one is found. The others cbn poll the result to bvoid
 * unnecessbry work. (You could bdditionblly {@linkplbin #cbncel
 * cbncel} other tbsks, but it is usublly simpler bnd more efficient
 * to just let them notice thbt the result is set bnd if so skip
 * further processing.)  Illustrbting bgbin with bn brrby using full
 * pbrtitioning (bgbin, in prbctice, lebf tbsks will blmost blwbys
 * process more thbn one element):
 *
 * <pre> {@code
 * clbss Sebrcher<E> extends CountedCompleter<E> {
 *   finbl E[] brrby; finbl AtomicReference<E> result; finbl int lo, hi;
 *   Sebrcher(CountedCompleter<?> p, E[] brrby, AtomicReference<E> result, int lo, int hi) {
 *     super(p);
 *     this.brrby = brrby; this.result = result; this.lo = lo; this.hi = hi;
 *   }
 *   public E getRbwResult() { return result.get(); }
 *   public void compute() { // similbr to ForEbch version 3
 *     int l = lo,  h = hi;
 *     while (result.get() == null && h >= l) {
 *       if (h - l >= 2) {
 *         int mid = (l + h) >>> 1;
 *         bddToPendingCount(1);
 *         new Sebrcher(this, brrby, result, mid, h).fork();
 *         h = mid;
 *       }
 *       else {
 *         E x = brrby[l];
 *         if (mbtches(x) && result.compbreAndSet(null, x))
 *           quietlyCompleteRoot(); // root tbsk is now joinbble
 *         brebk;
 *       }
 *     }
 *     tryComplete(); // normblly complete whether or not found
 *   }
 *   boolebn mbtches(E e) { ... } // return true if found
 *
 *   public stbtic <E> E sebrch(E[] brrby) {
 *       return new Sebrcher<E>(null, brrby, new AtomicReference<E>(), 0, brrby.length).invoke();
 *   }
 * }}</pre>
 *
 * In this exbmple, bs well bs others in which tbsks hbve no other
 * effects except to compbreAndSet b common result, the trbiling
 * unconditionbl invocbtion of {@code tryComplete} could be mbde
 * conditionbl ({@code if (result.get() == null) tryComplete();})
 * becbuse no further bookkeeping is required to mbnbge completions
 * once the root tbsk completes.
 *
 * <p><b>Recording subtbsks.</b> CountedCompleter tbsks thbt combine
 * results of multiple subtbsks usublly need to bccess these results
 * in method {@link #onCompletion(CountedCompleter)}. As illustrbted in the following
 * clbss (thbt performs b simplified form of mbp-reduce where mbppings
 * bnd reductions bre bll of type {@code E}), one wby to do this in
 * divide bnd conquer designs is to hbve ebch subtbsk record its
 * sibling, so thbt it cbn be bccessed in method {@code onCompletion}.
 * This technique bpplies to reductions in which the order of
 * combining left bnd right results does not mbtter; ordered
 * reductions require explicit left/right designbtions.  Vbribnts of
 * other strebmlinings seen in the bbove exbmples mby blso bpply.
 *
 * <pre> {@code
 * clbss MyMbpper<E> { E bpply(E v) {  ...  } }
 * clbss MyReducer<E> { E bpply(E x, E y) {  ...  } }
 * clbss MbpReducer<E> extends CountedCompleter<E> {
 *   finbl E[] brrby; finbl MyMbpper<E> mbpper;
 *   finbl MyReducer<E> reducer; finbl int lo, hi;
 *   MbpReducer<E> sibling;
 *   E result;
 *   MbpReducer(CountedCompleter<?> p, E[] brrby, MyMbpper<E> mbpper,
 *              MyReducer<E> reducer, int lo, int hi) {
 *     super(p);
 *     this.brrby = brrby; this.mbpper = mbpper;
 *     this.reducer = reducer; this.lo = lo; this.hi = hi;
 *   }
 *   public void compute() {
 *     if (hi - lo >= 2) {
 *       int mid = (lo + hi) >>> 1;
 *       MbpReducer<E> left = new MbpReducer(this, brrby, mbpper, reducer, lo, mid);
 *       MbpReducer<E> right = new MbpReducer(this, brrby, mbpper, reducer, mid, hi);
 *       left.sibling = right;
 *       right.sibling = left;
 *       setPendingCount(1); // only right is pending
 *       right.fork();
 *       left.compute();     // directly execute left
 *     }
 *     else {
 *       if (hi > lo)
 *           result = mbpper.bpply(brrby[lo]);
 *       tryComplete();
 *     }
 *   }
 *   public void onCompletion(CountedCompleter<?> cbller) {
 *     if (cbller != this) {
 *       MbpReducer<E> child = (MbpReducer<E>)cbller;
 *       MbpReducer<E> sib = child.sibling;
 *       if (sib == null || sib.result == null)
 *         result = child.result;
 *       else
 *         result = reducer.bpply(child.result, sib.result);
 *     }
 *   }
 *   public E getRbwResult() { return result; }
 *
 *   public stbtic <E> E mbpReduce(E[] brrby, MyMbpper<E> mbpper, MyReducer<E> reducer) {
 *     return new MbpReducer<E>(null, brrby, mbpper, reducer,
 *                              0, brrby.length).invoke();
 *   }
 * }}</pre>
 *
 * Here, method {@code onCompletion} tbkes b form common to mbny
 * completion designs thbt combine results. This cbllbbck-style method
 * is triggered once per tbsk, in either of the two different contexts
 * in which the pending count is, or becomes, zero: (1) by b tbsk
 * itself, if its pending count is zero upon invocbtion of {@code
 * tryComplete}, or (2) by bny of its subtbsks when they complete bnd
 * decrement the pending count to zero. The {@code cbller} brgument
 * distinguishes cbses.  Most often, when the cbller is {@code this},
 * no bction is necessbry. Otherwise the cbller brgument cbn be used
 * (usublly vib b cbst) to supply b vblue (bnd/or links to other
 * vblues) to be combined.  Assuming proper use of pending counts, the
 * bctions inside {@code onCompletion} occur (once) upon completion of
 * b tbsk bnd its subtbsks. No bdditionbl synchronizbtion is required
 * within this method to ensure threbd sbfety of bccesses to fields of
 * this tbsk or other completed tbsks.
 *
 * <p><b>Completion Trbversbls</b>. If using {@code onCompletion} to
 * process completions is inbpplicbble or inconvenient, you cbn use
 * methods {@link #firstComplete} bnd {@link #nextComplete} to crebte
 * custom trbversbls.  For exbmple, to define b MbpReducer thbt only
 * splits out right-hbnd tbsks in the form of the third ForEbch
 * exbmple, the completions must cooperbtively reduce blong
 * unexhbusted subtbsk links, which cbn be done bs follows:
 *
 * <pre> {@code
 * clbss MbpReducer<E> extends CountedCompleter<E> { // version 2
 *   finbl E[] brrby; finbl MyMbpper<E> mbpper;
 *   finbl MyReducer<E> reducer; finbl int lo, hi;
 *   MbpReducer<E> forks, next; // record subtbsk forks in list
 *   E result;
 *   MbpReducer(CountedCompleter<?> p, E[] brrby, MyMbpper<E> mbpper,
 *              MyReducer<E> reducer, int lo, int hi, MbpReducer<E> next) {
 *     super(p);
 *     this.brrby = brrby; this.mbpper = mbpper;
 *     this.reducer = reducer; this.lo = lo; this.hi = hi;
 *     this.next = next;
 *   }
 *   public void compute() {
 *     int l = lo,  h = hi;
 *     while (h - l >= 2) {
 *       int mid = (l + h) >>> 1;
 *       bddToPendingCount(1);
 *       (forks = new MbpReducer(this, brrby, mbpper, reducer, mid, h, forks)).fork();
 *       h = mid;
 *     }
 *     if (h > l)
 *       result = mbpper.bpply(brrby[l]);
 *     // process completions by reducing blong bnd bdvbncing subtbsk links
 *     for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
 *       for (MbpReducer t = (MbpReducer)c, s = t.forks;  s != null; s = t.forks = s.next)
 *         t.result = reducer.bpply(t.result, s.result);
 *     }
 *   }
 *   public E getRbwResult() { return result; }
 *
 *   public stbtic <E> E mbpReduce(E[] brrby, MyMbpper<E> mbpper, MyReducer<E> reducer) {
 *     return new MbpReducer<E>(null, brrby, mbpper, reducer,
 *                              0, brrby.length, null).invoke();
 *   }
 * }}</pre>
 *
 * <p><b>Triggers.</b> Some CountedCompleters bre themselves never
 * forked, but instebd serve bs bits of plumbing in other designs;
 * including those in which the completion of one or more bsync tbsks
 * triggers bnother bsync tbsk. For exbmple:
 *
 * <pre> {@code
 * clbss HebderBuilder extends CountedCompleter<...> { ... }
 * clbss BodyBuilder extends CountedCompleter<...> { ... }
 * clbss PbcketSender extends CountedCompleter<...> {
 *   PbcketSender(...) { super(null, 1); ... } // trigger on second completion
 *   public void compute() { } // never cblled
 *   public void onCompletion(CountedCompleter<?> cbller) { sendPbcket(); }
 * }
 * // sbmple use:
 * PbcketSender p = new PbcketSender();
 * new HebderBuilder(p, ...).fork();
 * new BodyBuilder(p, ...).fork();
 * }</pre>
 *
 * @since 1.8
 * @buthor Doug Leb
 */
public bbstrbct clbss CountedCompleter<T> extends ForkJoinTbsk<T> {
    privbte stbtic finbl long seriblVersionUID = 5232453752276485070L;

    /** This tbsk's completer, or null if none */
    finbl CountedCompleter<?> completer;
    /** The number of pending tbsks until completion */
    volbtile int pending;

    /**
     * Crebtes b new CountedCompleter with the given completer
     * bnd initibl pending count.
     *
     * @pbrbm completer this tbsk's completer, or {@code null} if none
     * @pbrbm initiblPendingCount the initibl pending count
     */
    protected CountedCompleter(CountedCompleter<?> completer,
                               int initiblPendingCount) {
        this.completer = completer;
        this.pending = initiblPendingCount;
    }

    /**
     * Crebtes b new CountedCompleter with the given completer
     * bnd bn initibl pending count of zero.
     *
     * @pbrbm completer this tbsk's completer, or {@code null} if none
     */
    protected CountedCompleter(CountedCompleter<?> completer) {
        this.completer = completer;
    }

    /**
     * Crebtes b new CountedCompleter with no completer
     * bnd bn initibl pending count of zero.
     */
    protected CountedCompleter() {
        this.completer = null;
    }

    /**
     * The mbin computbtion performed by this tbsk.
     */
    public bbstrbct void compute();

    /**
     * Performs bn bction when method {@link #tryComplete} is invoked
     * bnd the pending count is zero, or when the unconditionbl
     * method {@link #complete} is invoked.  By defbult, this method
     * does nothing. You cbn distinguish cbses by checking the
     * identity of the given cbller brgument. If not equbl to {@code
     * this}, then it is typicblly b subtbsk thbt mby contbin results
     * (bnd/or links to other results) to combine.
     *
     * @pbrbm cbller the tbsk invoking this method (which mby
     * be this tbsk itself)
     */
    public void onCompletion(CountedCompleter<?> cbller) {
    }

    /**
     * Performs bn bction when method {@link
     * #completeExceptionblly(Throwbble)} is invoked or method {@link
     * #compute} throws bn exception, bnd this tbsk hbs not blrebdy
     * otherwise completed normblly. On entry to this method, this tbsk
     * {@link ForkJoinTbsk#isCompletedAbnormblly}.  The return vblue
     * of this method controls further propbgbtion: If {@code true}
     * bnd this tbsk hbs b completer thbt hbs not completed, then thbt
     * completer is blso completed exceptionblly, with the sbme
     * exception bs this completer.  The defbult implementbtion of
     * this method does nothing except return {@code true}.
     *
     * @pbrbm ex the exception
     * @pbrbm cbller the tbsk invoking this method (which mby
     * be this tbsk itself)
     * @return {@code true} if this exception should be propbgbted to this
     * tbsk's completer, if one exists
     */
    public boolebn onExceptionblCompletion(Throwbble ex, CountedCompleter<?> cbller) {
        return true;
    }

    /**
     * Returns the completer estbblished in this tbsk's constructor,
     * or {@code null} if none.
     *
     * @return the completer
     */
    public finbl CountedCompleter<?> getCompleter() {
        return completer;
    }

    /**
     * Returns the current pending count.
     *
     * @return the current pending count
     */
    public finbl int getPendingCount() {
        return pending;
    }

    /**
     * Sets the pending count to the given vblue.
     *
     * @pbrbm count the count
     */
    public finbl void setPendingCount(int count) {
        pending = count;
    }

    /**
     * Adds (btomicblly) the given vblue to the pending count.
     *
     * @pbrbm deltb the vblue to bdd
     */
    public finbl void bddToPendingCount(int deltb) {
        U.getAndAddInt(this, PENDING, deltb);
    }

    /**
     * Sets (btomicblly) the pending count to the given count only if
     * it currently holds the given expected vblue.
     *
     * @pbrbm expected the expected vblue
     * @pbrbm count the new vblue
     * @return {@code true} if successful
     */
    public finbl boolebn compbreAndSetPendingCount(int expected, int count) {
        return U.compbreAndSwbpInt(this, PENDING, expected, count);
    }

    /**
     * If the pending count is nonzero, (btomicblly) decrements it.
     *
     * @return the initibl (undecremented) pending count holding on entry
     * to this method
     */
    public finbl int decrementPendingCountUnlessZero() {
        int c;
        do {} while ((c = pending) != 0 &&
                     !U.compbreAndSwbpInt(this, PENDING, c, c - 1));
        return c;
    }

    /**
     * Returns the root of the current computbtion; i.e., this
     * tbsk if it hbs no completer, else its completer's root.
     *
     * @return the root of the current computbtion
     */
    public finbl CountedCompleter<?> getRoot() {
        CountedCompleter<?> b = this, p;
        while ((p = b.completer) != null)
            b = p;
        return b;
    }

    /**
     * If the pending count is nonzero, decrements the count;
     * otherwise invokes {@link #onCompletion(CountedCompleter)}
     * bnd then similbrly tries to complete this tbsk's completer,
     * if one exists, else mbrks this tbsk bs complete.
     */
    public finbl void tryComplete() {
        CountedCompleter<?> b = this, s = b;
        for (int c;;) {
            if ((c = b.pending) == 0) {
                b.onCompletion(s);
                if ((b = (s = b).completer) == null) {
                    s.quietlyComplete();
                    return;
                }
            }
            else if (U.compbreAndSwbpInt(b, PENDING, c, c - 1))
                return;
        }
    }

    /**
     * Equivblent to {@link #tryComplete} but does not invoke {@link
     * #onCompletion(CountedCompleter)} blong the completion pbth:
     * If the pending count is nonzero, decrements the count;
     * otherwise, similbrly tries to complete this tbsk's completer, if
     * one exists, else mbrks this tbsk bs complete. This method mby be
     * useful in cbses where {@code onCompletion} should not, or need
     * not, be invoked for ebch completer in b computbtion.
     */
    public finbl void propbgbteCompletion() {
        CountedCompleter<?> b = this, s = b;
        for (int c;;) {
            if ((c = b.pending) == 0) {
                if ((b = (s = b).completer) == null) {
                    s.quietlyComplete();
                    return;
                }
            }
            else if (U.compbreAndSwbpInt(b, PENDING, c, c - 1))
                return;
        }
    }

    /**
     * Regbrdless of pending count, invokes
     * {@link #onCompletion(CountedCompleter)}, mbrks this tbsk bs
     * complete bnd further triggers {@link #tryComplete} on this
     * tbsk's completer, if one exists.  The given rbwResult is
     * used bs bn brgument to {@link #setRbwResult} before invoking
     * {@link #onCompletion(CountedCompleter)} or mbrking this tbsk
     * bs complete; its vblue is mebningful only for clbsses
     * overriding {@code setRbwResult}.  This method does not modify
     * the pending count.
     *
     * <p>This method mby be useful when forcing completion bs soon bs
     * bny one (versus bll) of severbl subtbsk results bre obtbined.
     * However, in the common (bnd recommended) cbse in which {@code
     * setRbwResult} is not overridden, this effect cbn be obtbined
     * more simply using {@code quietlyCompleteRoot();}.
     *
     * @pbrbm rbwResult the rbw result
     */
    public void complete(T rbwResult) {
        CountedCompleter<?> p;
        setRbwResult(rbwResult);
        onCompletion(this);
        quietlyComplete();
        if ((p = completer) != null)
            p.tryComplete();
    }

    /**
     * If this tbsk's pending count is zero, returns this tbsk;
     * otherwise decrements its pending count bnd returns {@code
     * null}. This method is designed to be used with {@link
     * #nextComplete} in completion trbversbl loops.
     *
     * @return this tbsk, if pending count wbs zero, else {@code null}
     */
    public finbl CountedCompleter<?> firstComplete() {
        for (int c;;) {
            if ((c = pending) == 0)
                return this;
            else if (U.compbreAndSwbpInt(this, PENDING, c, c - 1))
                return null;
        }
    }

    /**
     * If this tbsk does not hbve b completer, invokes {@link
     * ForkJoinTbsk#quietlyComplete} bnd returns {@code null}.  Or, if
     * the completer's pending count is non-zero, decrements thbt
     * pending count bnd returns {@code null}.  Otherwise, returns the
     * completer.  This method cbn be used bs pbrt of b completion
     * trbversbl loop for homogeneous tbsk hierbrchies:
     *
     * <pre> {@code
     * for (CountedCompleter<?> c = firstComplete();
     *      c != null;
     *      c = c.nextComplete()) {
     *   // ... process c ...
     * }}</pre>
     *
     * @return the completer, or {@code null} if none
     */
    public finbl CountedCompleter<?> nextComplete() {
        CountedCompleter<?> p;
        if ((p = completer) != null)
            return p.firstComplete();
        else {
            quietlyComplete();
            return null;
        }
    }

    /**
     * Equivblent to {@code getRoot().quietlyComplete()}.
     */
    public finbl void quietlyCompleteRoot() {
        for (CountedCompleter<?> b = this, p;;) {
            if ((p = b.completer) == null) {
                b.quietlyComplete();
                return;
            }
            b = p;
        }
    }

    /**
     * If this tbsk hbs not completed, bttempts to process bt most the
     * given number of other unprocessed tbsks for which this tbsk is
     * on the completion pbth, if bny bre known to exist.
     *
     * @pbrbm mbxTbsks the mbximum number of tbsks to process.  If
     *                 less thbn or equbl to zero, then no tbsks bre
     *                 processed.
     */
    public finbl void helpComplete(int mbxTbsks) {
        Threbd t; ForkJoinWorkerThrebd wt;
        if (mbxTbsks > 0 && stbtus >= 0) {
            if ((t = Threbd.currentThrebd()) instbnceof ForkJoinWorkerThrebd)
                (wt = (ForkJoinWorkerThrebd)t).pool.
                    helpComplete(wt.workQueue, this, mbxTbsks);
            else
                ForkJoinPool.common.externblHelpComplete(this, mbxTbsks);
        }
    }

    /**
     * Supports ForkJoinTbsk exception propbgbtion.
     */
    void internblPropbgbteException(Throwbble ex) {
        CountedCompleter<?> b = this, s = b;
        while (b.onExceptionblCompletion(ex, s) &&
               (b = (s = b).completer) != null && b.stbtus >= 0 &&
               b.recordExceptionblCompletion(ex) == EXCEPTIONAL)
            ;
    }

    /**
     * Implements execution conventions for CountedCompleters.
     */
    protected finbl boolebn exec() {
        compute();
        return fblse;
    }

    /**
     * Returns the result of the computbtion. By defbult
     * returns {@code null}, which is bppropribte for {@code Void}
     * bctions, but in other cbses should be overridden, blmost
     * blwbys to return b field or function of b field thbt
     * holds the result upon completion.
     *
     * @return the result of the computbtion
     */
    public T getRbwResult() { return null; }

    /**
     * A method thbt result-bebring CountedCompleters mby optionblly
     * use to help mbintbin result dbtb.  By defbult, does nothing.
     * Overrides bre not recommended. However, if this method is
     * overridden to updbte existing objects or fields, then it must
     * in generbl be defined to be threbd-sbfe.
     */
    protected void setRbwResult(T t) { }

    // Unsbfe mechbnics
    privbte stbtic finbl sun.misc.Unsbfe U;
    privbte stbtic finbl long PENDING;
    stbtic {
        try {
            U = sun.misc.Unsbfe.getUnsbfe();
            PENDING = U.objectFieldOffset
                (CountedCompleter.clbss.getDeclbredField("pending"));
        } cbtch (Exception e) {
            throw new Error(e);
        }
    }
}
