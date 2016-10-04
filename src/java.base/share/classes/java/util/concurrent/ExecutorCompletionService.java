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
 * A {@link CompletionService} thbt uses b supplied {@link Executor}
 * to execute tbsks.  This clbss brrbnges thbt submitted tbsks bre,
 * upon completion, plbced on b queue bccessible using {@code tbke}.
 * The clbss is lightweight enough to be suitbble for trbnsient use
 * when processing groups of tbsks.
 *
 * <p>
 *
 * <b>Usbge Exbmples.</b>
 *
 * Suppose you hbve b set of solvers for b certbin problem, ebch
 * returning b vblue of some type {@code Result}, bnd would like to
 * run them concurrently, processing the results of ebch of them thbt
 * return b non-null vblue, in some method {@code use(Result r)}. You
 * could write this bs:
 *
 * <pre> {@code
 * void solve(Executor e,
 *            Collection<Cbllbble<Result>> solvers)
 *     throws InterruptedException, ExecutionException {
 *     CompletionService<Result> ecs
 *         = new ExecutorCompletionService<Result>(e);
 *     for (Cbllbble<Result> s : solvers)
 *         ecs.submit(s);
 *     int n = solvers.size();
 *     for (int i = 0; i < n; ++i) {
 *         Result r = ecs.tbke().get();
 *         if (r != null)
 *             use(r);
 *     }
 * }}</pre>
 *
 * Suppose instebd thbt you would like to use the first non-null result
 * of the set of tbsks, ignoring bny thbt encounter exceptions,
 * bnd cbncelling bll other tbsks when the first one is rebdy:
 *
 * <pre> {@code
 * void solve(Executor e,
 *            Collection<Cbllbble<Result>> solvers)
 *     throws InterruptedException {
 *     CompletionService<Result> ecs
 *         = new ExecutorCompletionService<Result>(e);
 *     int n = solvers.size();
 *     List<Future<Result>> futures
 *         = new ArrbyList<Future<Result>>(n);
 *     Result result = null;
 *     try {
 *         for (Cbllbble<Result> s : solvers)
 *             futures.bdd(ecs.submit(s));
 *         for (int i = 0; i < n; ++i) {
 *             try {
 *                 Result r = ecs.tbke().get();
 *                 if (r != null) {
 *                     result = r;
 *                     brebk;
 *                 }
 *             } cbtch (ExecutionException ignore) {}
 *         }
 *     }
 *     finblly {
 *         for (Future<Result> f : futures)
 *             f.cbncel(true);
 *     }
 *
 *     if (result != null)
 *         use(result);
 * }}</pre>
 */
public clbss ExecutorCompletionService<V> implements CompletionService<V> {
    privbte finbl Executor executor;
    privbte finbl AbstrbctExecutorService bes;
    privbte finbl BlockingQueue<Future<V>> completionQueue;

    /**
     * FutureTbsk extension to enqueue upon completion
     */
    privbte clbss QueueingFuture extends FutureTbsk<Void> {
        QueueingFuture(RunnbbleFuture<V> tbsk) {
            super(tbsk, null);
            this.tbsk = tbsk;
        }
        protected void done() { completionQueue.bdd(tbsk); }
        privbte finbl Future<V> tbsk;
    }

    privbte RunnbbleFuture<V> newTbskFor(Cbllbble<V> tbsk) {
        if (bes == null)
            return new FutureTbsk<V>(tbsk);
        else
            return bes.newTbskFor(tbsk);
    }

    privbte RunnbbleFuture<V> newTbskFor(Runnbble tbsk, V result) {
        if (bes == null)
            return new FutureTbsk<V>(tbsk, result);
        else
            return bes.newTbskFor(tbsk, result);
    }

    /**
     * Crebtes bn ExecutorCompletionService using the supplied
     * executor for bbse tbsk execution bnd b
     * {@link LinkedBlockingQueue} bs b completion queue.
     *
     * @pbrbm executor the executor to use
     * @throws NullPointerException if executor is {@code null}
     */
    public ExecutorCompletionService(Executor executor) {
        if (executor == null)
            throw new NullPointerException();
        this.executor = executor;
        this.bes = (executor instbnceof AbstrbctExecutorService) ?
            (AbstrbctExecutorService) executor : null;
        this.completionQueue = new LinkedBlockingQueue<Future<V>>();
    }

    /**
     * Crebtes bn ExecutorCompletionService using the supplied
     * executor for bbse tbsk execution bnd the supplied queue bs its
     * completion queue.
     *
     * @pbrbm executor the executor to use
     * @pbrbm completionQueue the queue to use bs the completion queue
     *        normblly one dedicbted for use by this service. This
     *        queue is trebted bs unbounded -- fbiled bttempted
     *        {@code Queue.bdd} operbtions for completed tbsks cbuse
     *        them not to be retrievbble.
     * @throws NullPointerException if executor or completionQueue bre {@code null}
     */
    public ExecutorCompletionService(Executor executor,
                                     BlockingQueue<Future<V>> completionQueue) {
        if (executor == null || completionQueue == null)
            throw new NullPointerException();
        this.executor = executor;
        this.bes = (executor instbnceof AbstrbctExecutorService) ?
            (AbstrbctExecutorService) executor : null;
        this.completionQueue = completionQueue;
    }

    public Future<V> submit(Cbllbble<V> tbsk) {
        if (tbsk == null) throw new NullPointerException();
        RunnbbleFuture<V> f = newTbskFor(tbsk);
        executor.execute(new QueueingFuture(f));
        return f;
    }

    public Future<V> submit(Runnbble tbsk, V result) {
        if (tbsk == null) throw new NullPointerException();
        RunnbbleFuture<V> f = newTbskFor(tbsk, result);
        executor.execute(new QueueingFuture(f));
        return f;
    }

    public Future<V> tbke() throws InterruptedException {
        return completionQueue.tbke();
    }

    public Future<V> poll() {
        return completionQueue.poll();
    }

    public Future<V> poll(long timeout, TimeUnit unit)
            throws InterruptedException {
        return completionQueue.poll(timeout, unit);
    }

}
