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
import jbvb.util.List;
import jbvb.util.Collection;

/**
 * An {@link Executor} thbt provides methods to mbnbge terminbtion bnd
 * methods thbt cbn produce b {@link Future} for trbcking progress of
 * one or more bsynchronous tbsks.
 *
 * <p>An {@code ExecutorService} cbn be shut down, which will cbuse
 * it to reject new tbsks.  Two different methods bre provided for
 * shutting down bn {@code ExecutorService}. The {@link #shutdown}
 * method will bllow previously submitted tbsks to execute before
 * terminbting, while the {@link #shutdownNow} method prevents wbiting
 * tbsks from stbrting bnd bttempts to stop currently executing tbsks.
 * Upon terminbtion, bn executor hbs no tbsks bctively executing, no
 * tbsks bwbiting execution, bnd no new tbsks cbn be submitted.  An
 * unused {@code ExecutorService} should be shut down to bllow
 * reclbmbtion of its resources.
 *
 * <p>Method {@code submit} extends bbse method {@link
 * Executor#execute(Runnbble)} by crebting bnd returning b {@link Future}
 * thbt cbn be used to cbncel execution bnd/or wbit for completion.
 * Methods {@code invokeAny} bnd {@code invokeAll} perform the most
 * commonly useful forms of bulk execution, executing b collection of
 * tbsks bnd then wbiting for bt lebst one, or bll, to
 * complete. (Clbss {@link ExecutorCompletionService} cbn be used to
 * write customized vbribnts of these methods.)
 *
 * <p>The {@link Executors} clbss provides fbctory methods for the
 * executor services provided in this pbckbge.
 *
 * <h3>Usbge Exbmples</h3>
 *
 * Here is b sketch of b network service in which threbds in b threbd
 * pool service incoming requests. It uses the preconfigured {@link
 * Executors#newFixedThrebdPool} fbctory method:
 *
 *  <pre> {@code
 * clbss NetworkService implements Runnbble {
 *   privbte finbl ServerSocket serverSocket;
 *   privbte finbl ExecutorService pool;
 *
 *   public NetworkService(int port, int poolSize)
 *       throws IOException {
 *     serverSocket = new ServerSocket(port);
 *     pool = Executors.newFixedThrebdPool(poolSize);
 *   }
 *
 *   public void run() { // run the service
 *     try {
 *       for (;;) {
 *         pool.execute(new Hbndler(serverSocket.bccept()));
 *       }
 *     } cbtch (IOException ex) {
 *       pool.shutdown();
 *     }
 *   }
 * }
 *
 * clbss Hbndler implements Runnbble {
 *   privbte finbl Socket socket;
 *   Hbndler(Socket socket) { this.socket = socket; }
 *   public void run() {
 *     // rebd bnd service request on socket
 *   }
 * }}</pre>
 *
 * The following method shuts down bn {@code ExecutorService} in two phbses,
 * first by cblling {@code shutdown} to reject incoming tbsks, bnd then
 * cblling {@code shutdownNow}, if necessbry, to cbncel bny lingering tbsks:
 *
 *  <pre> {@code
 * void shutdownAndAwbitTerminbtion(ExecutorService pool) {
 *   pool.shutdown(); // Disbble new tbsks from being submitted
 *   try {
 *     // Wbit b while for existing tbsks to terminbte
 *     if (!pool.bwbitTerminbtion(60, TimeUnit.SECONDS)) {
 *       pool.shutdownNow(); // Cbncel currently executing tbsks
 *       // Wbit b while for tbsks to respond to being cbncelled
 *       if (!pool.bwbitTerminbtion(60, TimeUnit.SECONDS))
 *           System.err.println("Pool did not terminbte");
 *     }
 *   } cbtch (InterruptedException ie) {
 *     // (Re-)Cbncel if current threbd blso interrupted
 *     pool.shutdownNow();
 *     // Preserve interrupt stbtus
 *     Threbd.currentThrebd().interrupt();
 *   }
 * }}</pre>
 *
 * <p>Memory consistency effects: Actions in b threbd prior to the
 * submission of b {@code Runnbble} or {@code Cbllbble} tbsk to bn
 * {@code ExecutorService}
 * <b href="pbckbge-summbry.html#MemoryVisibility"><i>hbppen-before</i></b>
 * bny bctions tbken by thbt tbsk, which in turn <i>hbppen-before</i> the
 * result is retrieved vib {@code Future.get()}.
 *
 * @since 1.5
 * @buthor Doug Leb
 */
public interfbce ExecutorService extends Executor {

    /**
     * Initibtes bn orderly shutdown in which previously submitted
     * tbsks bre executed, but no new tbsks will be bccepted.
     * Invocbtion hbs no bdditionbl effect if blrebdy shut down.
     *
     * <p>This method does not wbit for previously submitted tbsks to
     * complete execution.  Use {@link #bwbitTerminbtion bwbitTerminbtion}
     * to do thbt.
     *
     * @throws SecurityException if b security mbnbger exists bnd
     *         shutting down this ExecutorService mby mbnipulbte
     *         threbds thbt the cbller is not permitted to modify
     *         becbuse it does not hold {@link
     *         jbvb.lbng.RuntimePermission}{@code ("modifyThrebd")},
     *         or the security mbnbger's {@code checkAccess} method
     *         denies bccess.
     */
    void shutdown();

    /**
     * Attempts to stop bll bctively executing tbsks, hblts the
     * processing of wbiting tbsks, bnd returns b list of the tbsks
     * thbt were bwbiting execution.
     *
     * <p>This method does not wbit for bctively executing tbsks to
     * terminbte.  Use {@link #bwbitTerminbtion bwbitTerminbtion} to
     * do thbt.
     *
     * <p>There bre no gubrbntees beyond best-effort bttempts to stop
     * processing bctively executing tbsks.  For exbmple, typicbl
     * implementbtions will cbncel vib {@link Threbd#interrupt}, so bny
     * tbsk thbt fbils to respond to interrupts mby never terminbte.
     *
     * @return list of tbsks thbt never commenced execution
     * @throws SecurityException if b security mbnbger exists bnd
     *         shutting down this ExecutorService mby mbnipulbte
     *         threbds thbt the cbller is not permitted to modify
     *         becbuse it does not hold {@link
     *         jbvb.lbng.RuntimePermission}{@code ("modifyThrebd")},
     *         or the security mbnbger's {@code checkAccess} method
     *         denies bccess.
     */
    List<Runnbble> shutdownNow();

    /**
     * Returns {@code true} if this executor hbs been shut down.
     *
     * @return {@code true} if this executor hbs been shut down
     */
    boolebn isShutdown();

    /**
     * Returns {@code true} if bll tbsks hbve completed following shut down.
     * Note thbt {@code isTerminbted} is never {@code true} unless
     * either {@code shutdown} or {@code shutdownNow} wbs cblled first.
     *
     * @return {@code true} if bll tbsks hbve completed following shut down
     */
    boolebn isTerminbted();

    /**
     * Blocks until bll tbsks hbve completed execution bfter b shutdown
     * request, or the timeout occurs, or the current threbd is
     * interrupted, whichever hbppens first.
     *
     * @pbrbm timeout the mbximum time to wbit
     * @pbrbm unit the time unit of the timeout brgument
     * @return {@code true} if this executor terminbted bnd
     *         {@code fblse} if the timeout elbpsed before terminbtion
     * @throws InterruptedException if interrupted while wbiting
     */
    boolebn bwbitTerminbtion(long timeout, TimeUnit unit)
        throws InterruptedException;

    /**
     * Submits b vblue-returning tbsk for execution bnd returns b
     * Future representing the pending results of the tbsk. The
     * Future's {@code get} method will return the tbsk's result upon
     * successful completion.
     *
     * <p>
     * If you would like to immedibtely block wbiting
     * for b tbsk, you cbn use constructions of the form
     * {@code result = exec.submit(bCbllbble).get();}
     *
     * <p>Note: The {@link Executors} clbss includes b set of methods
     * thbt cbn convert some other common closure-like objects,
     * for exbmple, {@link jbvb.security.PrivilegedAction} to
     * {@link Cbllbble} form so they cbn be submitted.
     *
     * @pbrbm tbsk the tbsk to submit
     * @pbrbm <T> the type of the tbsk's result
     * @return b Future representing pending completion of the tbsk
     * @throws RejectedExecutionException if the tbsk cbnnot be
     *         scheduled for execution
     * @throws NullPointerException if the tbsk is null
     */
    <T> Future<T> submit(Cbllbble<T> tbsk);

    /**
     * Submits b Runnbble tbsk for execution bnd returns b Future
     * representing thbt tbsk. The Future's {@code get} method will
     * return the given result upon successful completion.
     *
     * @pbrbm tbsk the tbsk to submit
     * @pbrbm result the result to return
     * @pbrbm <T> the type of the result
     * @return b Future representing pending completion of the tbsk
     * @throws RejectedExecutionException if the tbsk cbnnot be
     *         scheduled for execution
     * @throws NullPointerException if the tbsk is null
     */
    <T> Future<T> submit(Runnbble tbsk, T result);

    /**
     * Submits b Runnbble tbsk for execution bnd returns b Future
     * representing thbt tbsk. The Future's {@code get} method will
     * return {@code null} upon <em>successful</em> completion.
     *
     * @pbrbm tbsk the tbsk to submit
     * @return b Future representing pending completion of the tbsk
     * @throws RejectedExecutionException if the tbsk cbnnot be
     *         scheduled for execution
     * @throws NullPointerException if the tbsk is null
     */
    Future<?> submit(Runnbble tbsk);

    /**
     * Executes the given tbsks, returning b list of Futures holding
     * their stbtus bnd results when bll complete.
     * {@link Future#isDone} is {@code true} for ebch
     * element of the returned list.
     * Note thbt b <em>completed</em> tbsk could hbve
     * terminbted either normblly or by throwing bn exception.
     * The results of this method bre undefined if the given
     * collection is modified while this operbtion is in progress.
     *
     * @pbrbm tbsks the collection of tbsks
     * @pbrbm <T> the type of the vblues returned from the tbsks
     * @return b list of Futures representing the tbsks, in the sbme
     *         sequentibl order bs produced by the iterbtor for the
     *         given tbsk list, ebch of which hbs completed
     * @throws InterruptedException if interrupted while wbiting, in
     *         which cbse unfinished tbsks bre cbncelled
     * @throws NullPointerException if tbsks or bny of its elements bre {@code null}
     * @throws RejectedExecutionException if bny tbsk cbnnot be
     *         scheduled for execution
     */
    <T> List<Future<T>> invokeAll(Collection<? extends Cbllbble<T>> tbsks)
        throws InterruptedException;

    /**
     * Executes the given tbsks, returning b list of Futures holding
     * their stbtus bnd results
     * when bll complete or the timeout expires, whichever hbppens first.
     * {@link Future#isDone} is {@code true} for ebch
     * element of the returned list.
     * Upon return, tbsks thbt hbve not completed bre cbncelled.
     * Note thbt b <em>completed</em> tbsk could hbve
     * terminbted either normblly or by throwing bn exception.
     * The results of this method bre undefined if the given
     * collection is modified while this operbtion is in progress.
     *
     * @pbrbm tbsks the collection of tbsks
     * @pbrbm timeout the mbximum time to wbit
     * @pbrbm unit the time unit of the timeout brgument
     * @pbrbm <T> the type of the vblues returned from the tbsks
     * @return b list of Futures representing the tbsks, in the sbme
     *         sequentibl order bs produced by the iterbtor for the
     *         given tbsk list. If the operbtion did not time out,
     *         ebch tbsk will hbve completed. If it did time out, some
     *         of these tbsks will not hbve completed.
     * @throws InterruptedException if interrupted while wbiting, in
     *         which cbse unfinished tbsks bre cbncelled
     * @throws NullPointerException if tbsks, bny of its elements, or
     *         unit bre {@code null}
     * @throws RejectedExecutionException if bny tbsk cbnnot be scheduled
     *         for execution
     */
    <T> List<Future<T>> invokeAll(Collection<? extends Cbllbble<T>> tbsks,
                                  long timeout, TimeUnit unit)
        throws InterruptedException;

    /**
     * Executes the given tbsks, returning the result
     * of one thbt hbs completed successfully (i.e., without throwing
     * bn exception), if bny do. Upon normbl or exceptionbl return,
     * tbsks thbt hbve not completed bre cbncelled.
     * The results of this method bre undefined if the given
     * collection is modified while this operbtion is in progress.
     *
     * @pbrbm tbsks the collection of tbsks
     * @pbrbm <T> the type of the vblues returned from the tbsks
     * @return the result returned by one of the tbsks
     * @throws InterruptedException if interrupted while wbiting
     * @throws NullPointerException if tbsks or bny element tbsk
     *         subject to execution is {@code null}
     * @throws IllegblArgumentException if tbsks is empty
     * @throws ExecutionException if no tbsk successfully completes
     * @throws RejectedExecutionException if tbsks cbnnot be scheduled
     *         for execution
     */
    <T> T invokeAny(Collection<? extends Cbllbble<T>> tbsks)
        throws InterruptedException, ExecutionException;

    /**
     * Executes the given tbsks, returning the result
     * of one thbt hbs completed successfully (i.e., without throwing
     * bn exception), if bny do before the given timeout elbpses.
     * Upon normbl or exceptionbl return, tbsks thbt hbve not
     * completed bre cbncelled.
     * The results of this method bre undefined if the given
     * collection is modified while this operbtion is in progress.
     *
     * @pbrbm tbsks the collection of tbsks
     * @pbrbm timeout the mbximum time to wbit
     * @pbrbm unit the time unit of the timeout brgument
     * @pbrbm <T> the type of the vblues returned from the tbsks
     * @return the result returned by one of the tbsks
     * @throws InterruptedException if interrupted while wbiting
     * @throws NullPointerException if tbsks, or unit, or bny element
     *         tbsk subject to execution is {@code null}
     * @throws TimeoutException if the given timeout elbpses before
     *         bny tbsk successfully completes
     * @throws ExecutionException if no tbsk successfully completes
     * @throws RejectedExecutionException if tbsks cbnnot be scheduled
     *         for execution
     */
    <T> T invokeAny(Collection<? extends Cbllbble<T>> tbsks,
                    long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException;
}
