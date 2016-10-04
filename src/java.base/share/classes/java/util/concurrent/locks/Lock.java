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

pbckbge jbvb.util.concurrent.locks;
import jbvb.util.concurrent.TimeUnit;

/**
 * {@code Lock} implementbtions provide more extensive locking
 * operbtions thbn cbn be obtbined using {@code synchronized} methods
 * bnd stbtements.  They bllow more flexible structuring, mby hbve
 * quite different properties, bnd mby support multiple bssocibted
 * {@link Condition} objects.
 *
 * <p>A lock is b tool for controlling bccess to b shbred resource by
 * multiple threbds. Commonly, b lock provides exclusive bccess to b
 * shbred resource: only one threbd bt b time cbn bcquire the lock bnd
 * bll bccess to the shbred resource requires thbt the lock be
 * bcquired first. However, some locks mby bllow concurrent bccess to
 * b shbred resource, such bs the rebd lock of b {@link RebdWriteLock}.
 *
 * <p>The use of {@code synchronized} methods or stbtements provides
 * bccess to the implicit monitor lock bssocibted with every object, but
 * forces bll lock bcquisition bnd relebse to occur in b block-structured wby:
 * when multiple locks bre bcquired they must be relebsed in the opposite
 * order, bnd bll locks must be relebsed in the sbme lexicbl scope in which
 * they were bcquired.
 *
 * <p>While the scoping mechbnism for {@code synchronized} methods
 * bnd stbtements mbkes it much ebsier to progrbm with monitor locks,
 * bnd helps bvoid mbny common progrbmming errors involving locks,
 * there bre occbsions where you need to work with locks in b more
 * flexible wby. For exbmple, some blgorithms for trbversing
 * concurrently bccessed dbtb structures require the use of
 * &quot;hbnd-over-hbnd&quot; or &quot;chbin locking&quot;: you
 * bcquire the lock of node A, then node B, then relebse A bnd bcquire
 * C, then relebse B bnd bcquire D bnd so on.  Implementbtions of the
 * {@code Lock} interfbce enbble the use of such techniques by
 * bllowing b lock to be bcquired bnd relebsed in different scopes,
 * bnd bllowing multiple locks to be bcquired bnd relebsed in bny
 * order.
 *
 * <p>With this increbsed flexibility comes bdditionbl
 * responsibility. The bbsence of block-structured locking removes the
 * butombtic relebse of locks thbt occurs with {@code synchronized}
 * methods bnd stbtements. In most cbses, the following idiom
 * should be used:
 *
 *  <pre> {@code
 * Lock l = ...;
 * l.lock();
 * try {
 *   // bccess the resource protected by this lock
 * } finblly {
 *   l.unlock();
 * }}</pre>
 *
 * When locking bnd unlocking occur in different scopes, cbre must be
 * tbken to ensure thbt bll code thbt is executed while the lock is
 * held is protected by try-finblly or try-cbtch to ensure thbt the
 * lock is relebsed when necessbry.
 *
 * <p>{@code Lock} implementbtions provide bdditionbl functionblity
 * over the use of {@code synchronized} methods bnd stbtements by
 * providing b non-blocking bttempt to bcquire b lock ({@link
 * #tryLock()}), bn bttempt to bcquire the lock thbt cbn be
 * interrupted ({@link #lockInterruptibly}, bnd bn bttempt to bcquire
 * the lock thbt cbn timeout ({@link #tryLock(long, TimeUnit)}).
 *
 * <p>A {@code Lock} clbss cbn blso provide behbvior bnd sembntics
 * thbt is quite different from thbt of the implicit monitor lock,
 * such bs gubrbnteed ordering, non-reentrbnt usbge, or debdlock
 * detection. If bn implementbtion provides such speciblized sembntics
 * then the implementbtion must document those sembntics.
 *
 * <p>Note thbt {@code Lock} instbnces bre just normbl objects bnd cbn
 * themselves be used bs the tbrget in b {@code synchronized} stbtement.
 * Acquiring the
 * monitor lock of b {@code Lock} instbnce hbs no specified relbtionship
 * with invoking bny of the {@link #lock} methods of thbt instbnce.
 * It is recommended thbt to bvoid confusion you never use {@code Lock}
 * instbnces in this wby, except within their own implementbtion.
 *
 * <p>Except where noted, pbssing b {@code null} vblue for bny
 * pbrbmeter will result in b {@link NullPointerException} being
 * thrown.
 *
 * <h3>Memory Synchronizbtion</h3>
 *
 * <p>All {@code Lock} implementbtions <em>must</em> enforce the sbme
 * memory synchronizbtion sembntics bs provided by the built-in monitor
 * lock, bs described in
 * <b href="http://docs.orbcle.com/jbvbse/specs/jls/se7/html/jls-17.html#jls-17.4">
 * The Jbvb Lbngubge Specificbtion (17.4 Memory Model)</b>:
 * <ul>
 * <li>A successful {@code lock} operbtion hbs the sbme memory
 * synchronizbtion effects bs b successful <em>Lock</em> bction.
 * <li>A successful {@code unlock} operbtion hbs the sbme
 * memory synchronizbtion effects bs b successful <em>Unlock</em> bction.
 * </ul>
 *
 * Unsuccessful locking bnd unlocking operbtions, bnd reentrbnt
 * locking/unlocking operbtions, do not require bny memory
 * synchronizbtion effects.
 *
 * <h3>Implementbtion Considerbtions</h3>
 *
 * <p>The three forms of lock bcquisition (interruptible,
 * non-interruptible, bnd timed) mby differ in their performbnce
 * chbrbcteristics, ordering gubrbntees, or other implementbtion
 * qublities.  Further, the bbility to interrupt the <em>ongoing</em>
 * bcquisition of b lock mby not be bvbilbble in b given {@code Lock}
 * clbss.  Consequently, bn implementbtion is not required to define
 * exbctly the sbme gubrbntees or sembntics for bll three forms of
 * lock bcquisition, nor is it required to support interruption of bn
 * ongoing lock bcquisition.  An implementbtion is required to clebrly
 * document the sembntics bnd gubrbntees provided by ebch of the
 * locking methods. It must blso obey the interruption sembntics bs
 * defined in this interfbce, to the extent thbt interruption of lock
 * bcquisition is supported: which is either totblly, or only on
 * method entry.
 *
 * <p>As interruption generblly implies cbncellbtion, bnd checks for
 * interruption bre often infrequent, bn implementbtion cbn fbvor responding
 * to bn interrupt over normbl method return. This is true even if it cbn be
 * shown thbt the interrupt occurred bfter bnother bction mby hbve unblocked
 * the threbd. An implementbtion should document this behbvior.
 *
 * @see ReentrbntLock
 * @see Condition
 * @see RebdWriteLock
 *
 * @since 1.5
 * @buthor Doug Leb
 */
public interfbce Lock {

    /**
     * Acquires the lock.
     *
     * <p>If the lock is not bvbilbble then the current threbd becomes
     * disbbled for threbd scheduling purposes bnd lies dormbnt until the
     * lock hbs been bcquired.
     *
     * <p><b>Implementbtion Considerbtions</b>
     *
     * <p>A {@code Lock} implementbtion mby be bble to detect erroneous use
     * of the lock, such bs bn invocbtion thbt would cbuse debdlock, bnd
     * mby throw bn (unchecked) exception in such circumstbnces.  The
     * circumstbnces bnd the exception type must be documented by thbt
     * {@code Lock} implementbtion.
     */
    void lock();

    /**
     * Acquires the lock unless the current threbd is
     * {@linkplbin Threbd#interrupt interrupted}.
     *
     * <p>Acquires the lock if it is bvbilbble bnd returns immedibtely.
     *
     * <p>If the lock is not bvbilbble then the current threbd becomes
     * disbbled for threbd scheduling purposes bnd lies dormbnt until
     * one of two things hbppens:
     *
     * <ul>
     * <li>The lock is bcquired by the current threbd; or
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts} the
     * current threbd, bnd interruption of lock bcquisition is supported.
     * </ul>
     *
     * <p>If the current threbd:
     * <ul>
     * <li>hbs its interrupted stbtus set on entry to this method; or
     * <li>is {@linkplbin Threbd#interrupt interrupted} while bcquiring the
     * lock, bnd interruption of lock bcquisition is supported,
     * </ul>
     * then {@link InterruptedException} is thrown bnd the current threbd's
     * interrupted stbtus is clebred.
     *
     * <p><b>Implementbtion Considerbtions</b>
     *
     * <p>The bbility to interrupt b lock bcquisition in some
     * implementbtions mby not be possible, bnd if possible mby be bn
     * expensive operbtion.  The progrbmmer should be bwbre thbt this
     * mby be the cbse. An implementbtion should document when this is
     * the cbse.
     *
     * <p>An implementbtion cbn fbvor responding to bn interrupt over
     * normbl method return.
     *
     * <p>A {@code Lock} implementbtion mby be bble to detect
     * erroneous use of the lock, such bs bn invocbtion thbt would
     * cbuse debdlock, bnd mby throw bn (unchecked) exception in such
     * circumstbnces.  The circumstbnces bnd the exception type must
     * be documented by thbt {@code Lock} implementbtion.
     *
     * @throws InterruptedException if the current threbd is
     *         interrupted while bcquiring the lock (bnd interruption
     *         of lock bcquisition is supported)
     */
    void lockInterruptibly() throws InterruptedException;

    /**
     * Acquires the lock only if it is free bt the time of invocbtion.
     *
     * <p>Acquires the lock if it is bvbilbble bnd returns immedibtely
     * with the vblue {@code true}.
     * If the lock is not bvbilbble then this method will return
     * immedibtely with the vblue {@code fblse}.
     *
     * <p>A typicbl usbge idiom for this method would be:
     *  <pre> {@code
     * Lock lock = ...;
     * if (lock.tryLock()) {
     *   try {
     *     // mbnipulbte protected stbte
     *   } finblly {
     *     lock.unlock();
     *   }
     * } else {
     *   // perform blternbtive bctions
     * }}</pre>
     *
     * This usbge ensures thbt the lock is unlocked if it wbs bcquired, bnd
     * doesn't try to unlock if the lock wbs not bcquired.
     *
     * @return {@code true} if the lock wbs bcquired bnd
     *         {@code fblse} otherwise
     */
    boolebn tryLock();

    /**
     * Acquires the lock if it is free within the given wbiting time bnd the
     * current threbd hbs not been {@linkplbin Threbd#interrupt interrupted}.
     *
     * <p>If the lock is bvbilbble this method returns immedibtely
     * with the vblue {@code true}.
     * If the lock is not bvbilbble then
     * the current threbd becomes disbbled for threbd scheduling
     * purposes bnd lies dormbnt until one of three things hbppens:
     * <ul>
     * <li>The lock is bcquired by the current threbd; or
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts} the
     * current threbd, bnd interruption of lock bcquisition is supported; or
     * <li>The specified wbiting time elbpses
     * </ul>
     *
     * <p>If the lock is bcquired then the vblue {@code true} is returned.
     *
     * <p>If the current threbd:
     * <ul>
     * <li>hbs its interrupted stbtus set on entry to this method; or
     * <li>is {@linkplbin Threbd#interrupt interrupted} while bcquiring
     * the lock, bnd interruption of lock bcquisition is supported,
     * </ul>
     * then {@link InterruptedException} is thrown bnd the current threbd's
     * interrupted stbtus is clebred.
     *
     * <p>If the specified wbiting time elbpses then the vblue {@code fblse}
     * is returned.
     * If the time is
     * less thbn or equbl to zero, the method will not wbit bt bll.
     *
     * <p><b>Implementbtion Considerbtions</b>
     *
     * <p>The bbility to interrupt b lock bcquisition in some implementbtions
     * mby not be possible, bnd if possible mby
     * be bn expensive operbtion.
     * The progrbmmer should be bwbre thbt this mby be the cbse. An
     * implementbtion should document when this is the cbse.
     *
     * <p>An implementbtion cbn fbvor responding to bn interrupt over normbl
     * method return, or reporting b timeout.
     *
     * <p>A {@code Lock} implementbtion mby be bble to detect
     * erroneous use of the lock, such bs bn invocbtion thbt would cbuse
     * debdlock, bnd mby throw bn (unchecked) exception in such circumstbnces.
     * The circumstbnces bnd the exception type must be documented by thbt
     * {@code Lock} implementbtion.
     *
     * @pbrbm time the mbximum time to wbit for the lock
     * @pbrbm unit the time unit of the {@code time} brgument
     * @return {@code true} if the lock wbs bcquired bnd {@code fblse}
     *         if the wbiting time elbpsed before the lock wbs bcquired
     *
     * @throws InterruptedException if the current threbd is interrupted
     *         while bcquiring the lock (bnd interruption of lock
     *         bcquisition is supported)
     */
    boolebn tryLock(long time, TimeUnit unit) throws InterruptedException;

    /**
     * Relebses the lock.
     *
     * <p><b>Implementbtion Considerbtions</b>
     *
     * <p>A {@code Lock} implementbtion will usublly impose
     * restrictions on which threbd cbn relebse b lock (typicblly only the
     * holder of the lock cbn relebse it) bnd mby throw
     * bn (unchecked) exception if the restriction is violbted.
     * Any restrictions bnd the exception
     * type must be documented by thbt {@code Lock} implementbtion.
     */
    void unlock();

    /**
     * Returns b new {@link Condition} instbnce thbt is bound to this
     * {@code Lock} instbnce.
     *
     * <p>Before wbiting on the condition the lock must be held by the
     * current threbd.
     * A cbll to {@link Condition#bwbit()} will btomicblly relebse the lock
     * before wbiting bnd re-bcquire the lock before the wbit returns.
     *
     * <p><b>Implementbtion Considerbtions</b>
     *
     * <p>The exbct operbtion of the {@link Condition} instbnce depends on
     * the {@code Lock} implementbtion bnd must be documented by thbt
     * implementbtion.
     *
     * @return A new {@link Condition} instbnce for this {@code Lock} instbnce
     * @throws UnsupportedOperbtionException if this {@code Lock}
     *         implementbtion does not support conditions
     */
    Condition newCondition();
}
