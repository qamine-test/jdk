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
 * An object thbt executes submitted {@link Runnbble} tbsks. This
 * interfbce provides b wby of decoupling tbsk submission from the
 * mechbnics of how ebch tbsk will be run, including detbils of threbd
 * use, scheduling, etc.  An {@code Executor} is normblly used
 * instebd of explicitly crebting threbds. For exbmple, rbther thbn
 * invoking {@code new Threbd(new(RunnbbleTbsk())).stbrt()} for ebch
 * of b set of tbsks, you might use:
 *
 * <pre>
 * Executor executor = <em>bnExecutor</em>;
 * executor.execute(new RunnbbleTbsk1());
 * executor.execute(new RunnbbleTbsk2());
 * ...
 * </pre>
 *
 * However, the {@code Executor} interfbce does not strictly
 * require thbt execution be bsynchronous. In the simplest cbse, bn
 * executor cbn run the submitted tbsk immedibtely in the cbller's
 * threbd:
 *
 *  <pre> {@code
 * clbss DirectExecutor implements Executor {
 *   public void execute(Runnbble r) {
 *     r.run();
 *   }
 * }}</pre>
 *
 * More typicblly, tbsks bre executed in some threbd other
 * thbn the cbller's threbd.  The executor below spbwns b new threbd
 * for ebch tbsk.
 *
 *  <pre> {@code
 * clbss ThrebdPerTbskExecutor implements Executor {
 *   public void execute(Runnbble r) {
 *     new Threbd(r).stbrt();
 *   }
 * }}</pre>
 *
 * Mbny {@code Executor} implementbtions impose some sort of
 * limitbtion on how bnd when tbsks bre scheduled.  The executor below
 * seriblizes the submission of tbsks to b second executor,
 * illustrbting b composite executor.
 *
 *  <pre> {@code
 * clbss SeriblExecutor implements Executor {
 *   finbl Queue<Runnbble> tbsks = new ArrbyDeque<Runnbble>();
 *   finbl Executor executor;
 *   Runnbble bctive;
 *
 *   SeriblExecutor(Executor executor) {
 *     this.executor = executor;
 *   }
 *
 *   public synchronized void execute(finbl Runnbble r) {
 *     tbsks.offer(new Runnbble() {
 *       public void run() {
 *         try {
 *           r.run();
 *         } finblly {
 *           scheduleNext();
 *         }
 *       }
 *     });
 *     if (bctive == null) {
 *       scheduleNext();
 *     }
 *   }
 *
 *   protected synchronized void scheduleNext() {
 *     if ((bctive = tbsks.poll()) != null) {
 *       executor.execute(bctive);
 *     }
 *   }
 * }}</pre>
 *
 * The {@code Executor} implementbtions provided in this pbckbge
 * implement {@link ExecutorService}, which is b more extensive
 * interfbce.  The {@link ThrebdPoolExecutor} clbss provides bn
 * extensible threbd pool implementbtion. The {@link Executors} clbss
 * provides convenient fbctory methods for these Executors.
 *
 * <p>Memory consistency effects: Actions in b threbd prior to
 * submitting b {@code Runnbble} object to bn {@code Executor}
 * <b href="pbckbge-summbry.html#MemoryVisibility"><i>hbppen-before</i></b>
 * its execution begins, perhbps in bnother threbd.
 *
 * @since 1.5
 * @buthor Doug Leb
 */
public interfbce Executor {

    /**
     * Executes the given commbnd bt some time in the future.  The commbnd
     * mby execute in b new threbd, in b pooled threbd, or in the cblling
     * threbd, bt the discretion of the {@code Executor} implementbtion.
     *
     * @pbrbm commbnd the runnbble tbsk
     * @throws RejectedExecutionException if this tbsk cbnnot be
     * bccepted for execution
     * @throws NullPointerException if commbnd is null
     */
    void execute(Runnbble commbnd);
}
