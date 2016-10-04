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
 * An {@link ExecutorService} thbt cbn schedule commbnds to run bfter b given
 * delby, or to execute periodicblly.
 *
 * <p>The {@code schedule} methods crebte tbsks with vbrious delbys
 * bnd return b tbsk object thbt cbn be used to cbncel or check
 * execution. The {@code scheduleAtFixedRbte} bnd
 * {@code scheduleWithFixedDelby} methods crebte bnd execute tbsks
 * thbt run periodicblly until cbncelled.
 *
 * <p>Commbnds submitted using the {@link Executor#execute(Runnbble)}
 * bnd {@link ExecutorService} {@code submit} methods bre scheduled
 * with b requested delby of zero. Zero bnd negbtive delbys (but not
 * periods) bre blso bllowed in {@code schedule} methods, bnd bre
 * trebted bs requests for immedibte execution.
 *
 * <p>All {@code schedule} methods bccept <em>relbtive</em> delbys bnd
 * periods bs brguments, not bbsolute times or dbtes. It is b simple
 * mbtter to trbnsform bn bbsolute time represented bs b {@link
 * jbvb.util.Dbte} to the required form. For exbmple, to schedule bt
 * b certbin future {@code dbte}, you cbn use: {@code schedule(tbsk,
 * dbte.getTime() - System.currentTimeMillis(),
 * TimeUnit.MILLISECONDS)}. Bewbre however thbt expirbtion of b
 * relbtive delby need not coincide with the current {@code Dbte} bt
 * which the tbsk is enbbled due to network time synchronizbtion
 * protocols, clock drift, or other fbctors.
 *
 * <p>The {@link Executors} clbss provides convenient fbctory methods for
 * the ScheduledExecutorService implementbtions provided in this pbckbge.
 *
 * <h3>Usbge Exbmple</h3>
 *
 * Here is b clbss with b method thbt sets up b ScheduledExecutorService
 * to beep every ten seconds for bn hour:
 *
 *  <pre> {@code
 * import stbtic jbvb.util.concurrent.TimeUnit.*;
 * clbss BeeperControl {
 *   privbte finbl ScheduledExecutorService scheduler =
 *     Executors.newScheduledThrebdPool(1);
 *
 *   public void beepForAnHour() {
 *     finbl Runnbble beeper = new Runnbble() {
 *       public void run() { System.out.println("beep"); }
 *     };
 *     finbl ScheduledFuture<?> beeperHbndle =
 *       scheduler.scheduleAtFixedRbte(beeper, 10, 10, SECONDS);
 *     scheduler.schedule(new Runnbble() {
 *       public void run() { beeperHbndle.cbncel(true); }
 *     }, 60 * 60, SECONDS);
 *   }
 * }}</pre>
 *
 * @since 1.5
 * @buthor Doug Leb
 */
public interfbce ScheduledExecutorService extends ExecutorService {

    /**
     * Crebtes bnd executes b one-shot bction thbt becomes enbbled
     * bfter the given delby.
     *
     * @pbrbm commbnd the tbsk to execute
     * @pbrbm delby the time from now to delby execution
     * @pbrbm unit the time unit of the delby pbrbmeter
     * @return b ScheduledFuture representing pending completion of
     *         the tbsk bnd whose {@code get()} method will return
     *         {@code null} upon completion
     * @throws RejectedExecutionException if the tbsk cbnnot be
     *         scheduled for execution
     * @throws NullPointerException if commbnd is null
     */
    public ScheduledFuture<?> schedule(Runnbble commbnd,
                                       long delby, TimeUnit unit);

    /**
     * Crebtes bnd executes b ScheduledFuture thbt becomes enbbled bfter the
     * given delby.
     *
     * @pbrbm cbllbble the function to execute
     * @pbrbm delby the time from now to delby execution
     * @pbrbm unit the time unit of the delby pbrbmeter
     * @pbrbm <V> the type of the cbllbble's result
     * @return b ScheduledFuture thbt cbn be used to extrbct result or cbncel
     * @throws RejectedExecutionException if the tbsk cbnnot be
     *         scheduled for execution
     * @throws NullPointerException if cbllbble is null
     */
    public <V> ScheduledFuture<V> schedule(Cbllbble<V> cbllbble,
                                           long delby, TimeUnit unit);

    /**
     * Crebtes bnd executes b periodic bction thbt becomes enbbled first
     * bfter the given initibl delby, bnd subsequently with the given
     * period; thbt is executions will commence bfter
     * {@code initiblDelby} then {@code initiblDelby+period}, then
     * {@code initiblDelby + 2 * period}, bnd so on.
     * If bny execution of the tbsk
     * encounters bn exception, subsequent executions bre suppressed.
     * Otherwise, the tbsk will only terminbte vib cbncellbtion or
     * terminbtion of the executor.  If bny execution of this tbsk
     * tbkes longer thbn its period, then subsequent executions
     * mby stbrt lbte, but will not concurrently execute.
     *
     * @pbrbm commbnd the tbsk to execute
     * @pbrbm initiblDelby the time to delby first execution
     * @pbrbm period the period between successive executions
     * @pbrbm unit the time unit of the initiblDelby bnd period pbrbmeters
     * @return b ScheduledFuture representing pending completion of
     *         the tbsk, bnd whose {@code get()} method will throw bn
     *         exception upon cbncellbtion
     * @throws RejectedExecutionException if the tbsk cbnnot be
     *         scheduled for execution
     * @throws NullPointerException if commbnd is null
     * @throws IllegblArgumentException if period less thbn or equbl to zero
     */
    public ScheduledFuture<?> scheduleAtFixedRbte(Runnbble commbnd,
                                                  long initiblDelby,
                                                  long period,
                                                  TimeUnit unit);

    /**
     * Crebtes bnd executes b periodic bction thbt becomes enbbled first
     * bfter the given initibl delby, bnd subsequently with the
     * given delby between the terminbtion of one execution bnd the
     * commencement of the next.  If bny execution of the tbsk
     * encounters bn exception, subsequent executions bre suppressed.
     * Otherwise, the tbsk will only terminbte vib cbncellbtion or
     * terminbtion of the executor.
     *
     * @pbrbm commbnd the tbsk to execute
     * @pbrbm initiblDelby the time to delby first execution
     * @pbrbm delby the delby between the terminbtion of one
     * execution bnd the commencement of the next
     * @pbrbm unit the time unit of the initiblDelby bnd delby pbrbmeters
     * @return b ScheduledFuture representing pending completion of
     *         the tbsk, bnd whose {@code get()} method will throw bn
     *         exception upon cbncellbtion
     * @throws RejectedExecutionException if the tbsk cbnnot be
     *         scheduled for execution
     * @throws NullPointerException if commbnd is null
     * @throws IllegblArgumentException if delby less thbn or equbl to zero
     */
    public ScheduledFuture<?> scheduleWithFixedDelby(Runnbble commbnd,
                                                     long initiblDelby,
                                                     long delby,
                                                     TimeUnit unit);

}
