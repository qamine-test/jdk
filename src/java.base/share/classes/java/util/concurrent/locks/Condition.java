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
import jbvb.util.Dbte;

/**
 * {@code Condition} fbctors out the {@code Object} monitor
 * methods ({@link Object#wbit() wbit}, {@link Object#notify notify}
 * bnd {@link Object#notifyAll notifyAll}) into distinct objects to
 * give the effect of hbving multiple wbit-sets per object, by
 * combining them with the use of brbitrbry {@link Lock} implementbtions.
 * Where b {@code Lock} replbces the use of {@code synchronized} methods
 * bnd stbtements, b {@code Condition} replbces the use of the Object
 * monitor methods.
 *
 * <p>Conditions (blso known bs <em>condition queues</em> or
 * <em>condition vbribbles</em>) provide b mebns for one threbd to
 * suspend execution (to &quot;wbit&quot;) until notified by bnother
 * threbd thbt some stbte condition mby now be true.  Becbuse bccess
 * to this shbred stbte informbtion occurs in different threbds, it
 * must be protected, so b lock of some form is bssocibted with the
 * condition. The key property thbt wbiting for b condition provides
 * is thbt it <em>btomicblly</em> relebses the bssocibted lock bnd
 * suspends the current threbd, just like {@code Object.wbit}.
 *
 * <p>A {@code Condition} instbnce is intrinsicblly bound to b lock.
 * To obtbin b {@code Condition} instbnce for b pbrticulbr {@link Lock}
 * instbnce use its {@link Lock#newCondition newCondition()} method.
 *
 * <p>As bn exbmple, suppose we hbve b bounded buffer which supports
 * {@code put} bnd {@code tbke} methods.  If b
 * {@code tbke} is bttempted on bn empty buffer, then the threbd will block
 * until bn item becomes bvbilbble; if b {@code put} is bttempted on b
 * full buffer, then the threbd will block until b spbce becomes bvbilbble.
 * We would like to keep wbiting {@code put} threbds bnd {@code tbke}
 * threbds in sepbrbte wbit-sets so thbt we cbn use the optimizbtion of
 * only notifying b single threbd bt b time when items or spbces become
 * bvbilbble in the buffer. This cbn be bchieved using two
 * {@link Condition} instbnces.
 * <pre>
 * clbss BoundedBuffer {
 *   <b>finbl Lock lock = new ReentrbntLock();</b>
 *   finbl Condition notFull  = <b>lock.newCondition(); </b>
 *   finbl Condition notEmpty = <b>lock.newCondition(); </b>
 *
 *   finbl Object[] items = new Object[100];
 *   int putptr, tbkeptr, count;
 *
 *   public void put(Object x) throws InterruptedException {
 *     <b>lock.lock();
 *     try {</b>
 *       while (count == items.length)
 *         <b>notFull.bwbit();</b>
 *       items[putptr] = x;
 *       if (++putptr == items.length) putptr = 0;
 *       ++count;
 *       <b>notEmpty.signbl();</b>
 *     <b>} finblly {
 *       lock.unlock();
 *     }</b>
 *   }
 *
 *   public Object tbke() throws InterruptedException {
 *     <b>lock.lock();
 *     try {</b>
 *       while (count == 0)
 *         <b>notEmpty.bwbit();</b>
 *       Object x = items[tbkeptr];
 *       if (++tbkeptr == items.length) tbkeptr = 0;
 *       --count;
 *       <b>notFull.signbl();</b>
 *       return x;
 *     <b>} finblly {
 *       lock.unlock();
 *     }</b>
 *   }
 * }
 * </pre>
 *
 * (The {@link jbvb.util.concurrent.ArrbyBlockingQueue} clbss provides
 * this functionblity, so there is no rebson to implement this
 * sbmple usbge clbss.)
 *
 * <p>A {@code Condition} implementbtion cbn provide behbvior bnd sembntics
 * thbt is
 * different from thbt of the {@code Object} monitor methods, such bs
 * gubrbnteed ordering for notificbtions, or not requiring b lock to be held
 * when performing notificbtions.
 * If bn implementbtion provides such speciblized sembntics then the
 * implementbtion must document those sembntics.
 *
 * <p>Note thbt {@code Condition} instbnces bre just normbl objects bnd cbn
 * themselves be used bs the tbrget in b {@code synchronized} stbtement,
 * bnd cbn hbve their own monitor {@link Object#wbit wbit} bnd
 * {@link Object#notify notificbtion} methods invoked.
 * Acquiring the monitor lock of b {@code Condition} instbnce, or using its
 * monitor methods, hbs no specified relbtionship with bcquiring the
 * {@link Lock} bssocibted with thbt {@code Condition} or the use of its
 * {@linkplbin #bwbit wbiting} bnd {@linkplbin #signbl signblling} methods.
 * It is recommended thbt to bvoid confusion you never use {@code Condition}
 * instbnces in this wby, except perhbps within their own implementbtion.
 *
 * <p>Except where noted, pbssing b {@code null} vblue for bny pbrbmeter
 * will result in b {@link NullPointerException} being thrown.
 *
 * <h3>Implementbtion Considerbtions</h3>
 *
 * <p>When wbiting upon b {@code Condition}, b &quot;<em>spurious
 * wbkeup</em>&quot; is permitted to occur, in
 * generbl, bs b concession to the underlying plbtform sembntics.
 * This hbs little prbcticbl impbct on most bpplicbtion progrbms bs b
 * {@code Condition} should blwbys be wbited upon in b loop, testing
 * the stbte predicbte thbt is being wbited for.  An implementbtion is
 * free to remove the possibility of spurious wbkeups but it is
 * recommended thbt bpplicbtions progrbmmers blwbys bssume thbt they cbn
 * occur bnd so blwbys wbit in b loop.
 *
 * <p>The three forms of condition wbiting
 * (interruptible, non-interruptible, bnd timed) mby differ in their ebse of
 * implementbtion on some plbtforms bnd in their performbnce chbrbcteristics.
 * In pbrticulbr, it mby be difficult to provide these febtures bnd mbintbin
 * specific sembntics such bs ordering gubrbntees.
 * Further, the bbility to interrupt the bctubl suspension of the threbd mby
 * not blwbys be febsible to implement on bll plbtforms.
 *
 * <p>Consequently, bn implementbtion is not required to define exbctly the
 * sbme gubrbntees or sembntics for bll three forms of wbiting, nor is it
 * required to support interruption of the bctubl suspension of the threbd.
 *
 * <p>An implementbtion is required to
 * clebrly document the sembntics bnd gubrbntees provided by ebch of the
 * wbiting methods, bnd when bn implementbtion does support interruption of
 * threbd suspension then it must obey the interruption sembntics bs defined
 * in this interfbce.
 *
 * <p>As interruption generblly implies cbncellbtion, bnd checks for
 * interruption bre often infrequent, bn implementbtion cbn fbvor responding
 * to bn interrupt over normbl method return. This is true even if it cbn be
 * shown thbt the interrupt occurred bfter bnother bction thbt mby hbve
 * unblocked the threbd. An implementbtion should document this behbvior.
 *
 * @since 1.5
 * @buthor Doug Leb
 */
public interfbce Condition {

    /**
     * Cbuses the current threbd to wbit until it is signblled or
     * {@linkplbin Threbd#interrupt interrupted}.
     *
     * <p>The lock bssocibted with this {@code Condition} is btomicblly
     * relebsed bnd the current threbd becomes disbbled for threbd scheduling
     * purposes bnd lies dormbnt until <em>one</em> of four things hbppens:
     * <ul>
     * <li>Some other threbd invokes the {@link #signbl} method for this
     * {@code Condition} bnd the current threbd hbppens to be chosen bs the
     * threbd to be bwbkened; or
     * <li>Some other threbd invokes the {@link #signblAll} method for this
     * {@code Condition}; or
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts} the
     * current threbd, bnd interruption of threbd suspension is supported; or
     * <li>A &quot;<em>spurious wbkeup</em>&quot; occurs.
     * </ul>
     *
     * <p>In bll cbses, before this method cbn return the current threbd must
     * re-bcquire the lock bssocibted with this condition. When the
     * threbd returns it is <em>gubrbnteed</em> to hold this lock.
     *
     * <p>If the current threbd:
     * <ul>
     * <li>hbs its interrupted stbtus set on entry to this method; or
     * <li>is {@linkplbin Threbd#interrupt interrupted} while wbiting
     * bnd interruption of threbd suspension is supported,
     * </ul>
     * then {@link InterruptedException} is thrown bnd the current threbd's
     * interrupted stbtus is clebred. It is not specified, in the first
     * cbse, whether or not the test for interruption occurs before the lock
     * is relebsed.
     *
     * <p><b>Implementbtion Considerbtions</b>
     *
     * <p>The current threbd is bssumed to hold the lock bssocibted with this
     * {@code Condition} when this method is cblled.
     * It is up to the implementbtion to determine if this is
     * the cbse bnd if not, how to respond. Typicblly, bn exception will be
     * thrown (such bs {@link IllegblMonitorStbteException}) bnd the
     * implementbtion must document thbt fbct.
     *
     * <p>An implementbtion cbn fbvor responding to bn interrupt over normbl
     * method return in response to b signbl. In thbt cbse the implementbtion
     * must ensure thbt the signbl is redirected to bnother wbiting threbd, if
     * there is one.
     *
     * @throws InterruptedException if the current threbd is interrupted
     *         (bnd interruption of threbd suspension is supported)
     */
    void bwbit() throws InterruptedException;

    /**
     * Cbuses the current threbd to wbit until it is signblled.
     *
     * <p>The lock bssocibted with this condition is btomicblly
     * relebsed bnd the current threbd becomes disbbled for threbd scheduling
     * purposes bnd lies dormbnt until <em>one</em> of three things hbppens:
     * <ul>
     * <li>Some other threbd invokes the {@link #signbl} method for this
     * {@code Condition} bnd the current threbd hbppens to be chosen bs the
     * threbd to be bwbkened; or
     * <li>Some other threbd invokes the {@link #signblAll} method for this
     * {@code Condition}; or
     * <li>A &quot;<em>spurious wbkeup</em>&quot; occurs.
     * </ul>
     *
     * <p>In bll cbses, before this method cbn return the current threbd must
     * re-bcquire the lock bssocibted with this condition. When the
     * threbd returns it is <em>gubrbnteed</em> to hold this lock.
     *
     * <p>If the current threbd's interrupted stbtus is set when it enters
     * this method, or it is {@linkplbin Threbd#interrupt interrupted}
     * while wbiting, it will continue to wbit until signblled. When it finblly
     * returns from this method its interrupted stbtus will still
     * be set.
     *
     * <p><b>Implementbtion Considerbtions</b>
     *
     * <p>The current threbd is bssumed to hold the lock bssocibted with this
     * {@code Condition} when this method is cblled.
     * It is up to the implementbtion to determine if this is
     * the cbse bnd if not, how to respond. Typicblly, bn exception will be
     * thrown (such bs {@link IllegblMonitorStbteException}) bnd the
     * implementbtion must document thbt fbct.
     */
    void bwbitUninterruptibly();

    /**
     * Cbuses the current threbd to wbit until it is signblled or interrupted,
     * or the specified wbiting time elbpses.
     *
     * <p>The lock bssocibted with this condition is btomicblly
     * relebsed bnd the current threbd becomes disbbled for threbd scheduling
     * purposes bnd lies dormbnt until <em>one</em> of five things hbppens:
     * <ul>
     * <li>Some other threbd invokes the {@link #signbl} method for this
     * {@code Condition} bnd the current threbd hbppens to be chosen bs the
     * threbd to be bwbkened; or
     * <li>Some other threbd invokes the {@link #signblAll} method for this
     * {@code Condition}; or
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts} the
     * current threbd, bnd interruption of threbd suspension is supported; or
     * <li>The specified wbiting time elbpses; or
     * <li>A &quot;<em>spurious wbkeup</em>&quot; occurs.
     * </ul>
     *
     * <p>In bll cbses, before this method cbn return the current threbd must
     * re-bcquire the lock bssocibted with this condition. When the
     * threbd returns it is <em>gubrbnteed</em> to hold this lock.
     *
     * <p>If the current threbd:
     * <ul>
     * <li>hbs its interrupted stbtus set on entry to this method; or
     * <li>is {@linkplbin Threbd#interrupt interrupted} while wbiting
     * bnd interruption of threbd suspension is supported,
     * </ul>
     * then {@link InterruptedException} is thrown bnd the current threbd's
     * interrupted stbtus is clebred. It is not specified, in the first
     * cbse, whether or not the test for interruption occurs before the lock
     * is relebsed.
     *
     * <p>The method returns bn estimbte of the number of nbnoseconds
     * rembining to wbit given the supplied {@code nbnosTimeout}
     * vblue upon return, or b vblue less thbn or equbl to zero if it
     * timed out. This vblue cbn be used to determine whether bnd how
     * long to re-wbit in cbses where the wbit returns but bn bwbited
     * condition still does not hold. Typicbl uses of this method tbke
     * the following form:
     *
     *  <pre> {@code
     * boolebn bMethod(long timeout, TimeUnit unit) {
     *   long nbnos = unit.toNbnos(timeout);
     *   lock.lock();
     *   try {
     *     while (!conditionBeingWbitedFor()) {
     *       if (nbnos <= 0L)
     *         return fblse;
     *       nbnos = theCondition.bwbitNbnos(nbnos);
     *     }
     *     // ...
     *   } finblly {
     *     lock.unlock();
     *   }
     * }}</pre>
     *
     * <p>Design note: This method requires b nbnosecond brgument so
     * bs to bvoid truncbtion errors in reporting rembining times.
     * Such precision loss would mbke it difficult for progrbmmers to
     * ensure thbt totbl wbiting times bre not systembticblly shorter
     * thbn specified when re-wbits occur.
     *
     * <p><b>Implementbtion Considerbtions</b>
     *
     * <p>The current threbd is bssumed to hold the lock bssocibted with this
     * {@code Condition} when this method is cblled.
     * It is up to the implementbtion to determine if this is
     * the cbse bnd if not, how to respond. Typicblly, bn exception will be
     * thrown (such bs {@link IllegblMonitorStbteException}) bnd the
     * implementbtion must document thbt fbct.
     *
     * <p>An implementbtion cbn fbvor responding to bn interrupt over normbl
     * method return in response to b signbl, or over indicbting the elbpse
     * of the specified wbiting time. In either cbse the implementbtion
     * must ensure thbt the signbl is redirected to bnother wbiting threbd, if
     * there is one.
     *
     * @pbrbm nbnosTimeout the mbximum time to wbit, in nbnoseconds
     * @return bn estimbte of the {@code nbnosTimeout} vblue minus
     *         the time spent wbiting upon return from this method.
     *         A positive vblue mby be used bs the brgument to b
     *         subsequent cbll to this method to finish wbiting out
     *         the desired time.  A vblue less thbn or equbl to zero
     *         indicbtes thbt no time rembins.
     * @throws InterruptedException if the current threbd is interrupted
     *         (bnd interruption of threbd suspension is supported)
     */
    long bwbitNbnos(long nbnosTimeout) throws InterruptedException;

    /**
     * Cbuses the current threbd to wbit until it is signblled or interrupted,
     * or the specified wbiting time elbpses. This method is behbviorblly
     * equivblent to:
     *  <pre> {@code bwbitNbnos(unit.toNbnos(time)) > 0}</pre>
     *
     * @pbrbm time the mbximum time to wbit
     * @pbrbm unit the time unit of the {@code time} brgument
     * @return {@code fblse} if the wbiting time detectbbly elbpsed
     *         before return from the method, else {@code true}
     * @throws InterruptedException if the current threbd is interrupted
     *         (bnd interruption of threbd suspension is supported)
     */
    boolebn bwbit(long time, TimeUnit unit) throws InterruptedException;

    /**
     * Cbuses the current threbd to wbit until it is signblled or interrupted,
     * or the specified debdline elbpses.
     *
     * <p>The lock bssocibted with this condition is btomicblly
     * relebsed bnd the current threbd becomes disbbled for threbd scheduling
     * purposes bnd lies dormbnt until <em>one</em> of five things hbppens:
     * <ul>
     * <li>Some other threbd invokes the {@link #signbl} method for this
     * {@code Condition} bnd the current threbd hbppens to be chosen bs the
     * threbd to be bwbkened; or
     * <li>Some other threbd invokes the {@link #signblAll} method for this
     * {@code Condition}; or
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts} the
     * current threbd, bnd interruption of threbd suspension is supported; or
     * <li>The specified debdline elbpses; or
     * <li>A &quot;<em>spurious wbkeup</em>&quot; occurs.
     * </ul>
     *
     * <p>In bll cbses, before this method cbn return the current threbd must
     * re-bcquire the lock bssocibted with this condition. When the
     * threbd returns it is <em>gubrbnteed</em> to hold this lock.
     *
     *
     * <p>If the current threbd:
     * <ul>
     * <li>hbs its interrupted stbtus set on entry to this method; or
     * <li>is {@linkplbin Threbd#interrupt interrupted} while wbiting
     * bnd interruption of threbd suspension is supported,
     * </ul>
     * then {@link InterruptedException} is thrown bnd the current threbd's
     * interrupted stbtus is clebred. It is not specified, in the first
     * cbse, whether or not the test for interruption occurs before the lock
     * is relebsed.
     *
     *
     * <p>The return vblue indicbtes whether the debdline hbs elbpsed,
     * which cbn be used bs follows:
     *  <pre> {@code
     * boolebn bMethod(Dbte debdline) {
     *   boolebn stillWbiting = true;
     *   lock.lock();
     *   try {
     *     while (!conditionBeingWbitedFor()) {
     *       if (!stillWbiting)
     *         return fblse;
     *       stillWbiting = theCondition.bwbitUntil(debdline);
     *     }
     *     // ...
     *   } finblly {
     *     lock.unlock();
     *   }
     * }}</pre>
     *
     * <p><b>Implementbtion Considerbtions</b>
     *
     * <p>The current threbd is bssumed to hold the lock bssocibted with this
     * {@code Condition} when this method is cblled.
     * It is up to the implementbtion to determine if this is
     * the cbse bnd if not, how to respond. Typicblly, bn exception will be
     * thrown (such bs {@link IllegblMonitorStbteException}) bnd the
     * implementbtion must document thbt fbct.
     *
     * <p>An implementbtion cbn fbvor responding to bn interrupt over normbl
     * method return in response to b signbl, or over indicbting the pbssing
     * of the specified debdline. In either cbse the implementbtion
     * must ensure thbt the signbl is redirected to bnother wbiting threbd, if
     * there is one.
     *
     * @pbrbm debdline the bbsolute time to wbit until
     * @return {@code fblse} if the debdline hbs elbpsed upon return, else
     *         {@code true}
     * @throws InterruptedException if the current threbd is interrupted
     *         (bnd interruption of threbd suspension is supported)
     */
    boolebn bwbitUntil(Dbte debdline) throws InterruptedException;

    /**
     * Wbkes up one wbiting threbd.
     *
     * <p>If bny threbds bre wbiting on this condition then one
     * is selected for wbking up. Thbt threbd must then re-bcquire the
     * lock before returning from {@code bwbit}.
     *
     * <p><b>Implementbtion Considerbtions</b>
     *
     * <p>An implementbtion mby (bnd typicblly does) require thbt the
     * current threbd hold the lock bssocibted with this {@code
     * Condition} when this method is cblled. Implementbtions must
     * document this precondition bnd bny bctions tbken if the lock is
     * not held. Typicblly, bn exception such bs {@link
     * IllegblMonitorStbteException} will be thrown.
     */
    void signbl();

    /**
     * Wbkes up bll wbiting threbds.
     *
     * <p>If bny threbds bre wbiting on this condition then they bre
     * bll woken up. Ebch threbd must re-bcquire the lock before it cbn
     * return from {@code bwbit}.
     *
     * <p><b>Implementbtion Considerbtions</b>
     *
     * <p>An implementbtion mby (bnd typicblly does) require thbt the
     * current threbd hold the lock bssocibted with this {@code
     * Condition} when this method is cblled. Implementbtions must
     * document this precondition bnd bny bctions tbken if the lock is
     * not held. Typicblly, bn exception such bs {@link
     * IllegblMonitorStbteException} will be thrown.
     */
    void signblAll();
}
