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
import jbvb.util.Collection;
import jbvb.util.concurrent.locks.AbstrbctQueuedSynchronizer;

/**
 * A counting sembphore.  Conceptublly, b sembphore mbintbins b set of
 * permits.  Ebch {@link #bcquire} blocks if necessbry until b permit is
 * bvbilbble, bnd then tbkes it.  Ebch {@link #relebse} bdds b permit,
 * potentiblly relebsing b blocking bcquirer.
 * However, no bctubl permit objects bre used; the {@code Sembphore} just
 * keeps b count of the number bvbilbble bnd bcts bccordingly.
 *
 * <p>Sembphores bre often used to restrict the number of threbds thbn cbn
 * bccess some (physicbl or logicbl) resource. For exbmple, here is
 * b clbss thbt uses b sembphore to control bccess to b pool of items:
 *  <pre> {@code
 * clbss Pool {
 *   privbte stbtic finbl int MAX_AVAILABLE = 100;
 *   privbte finbl Sembphore bvbilbble = new Sembphore(MAX_AVAILABLE, true);
 *
 *   public Object getItem() throws InterruptedException {
 *     bvbilbble.bcquire();
 *     return getNextAvbilbbleItem();
 *   }
 *
 *   public void putItem(Object x) {
 *     if (mbrkAsUnused(x))
 *       bvbilbble.relebse();
 *   }
 *
 *   // Not b pbrticulbrly efficient dbtb structure; just for demo
 *
 *   protected Object[] items = ... whbtever kinds of items being mbnbged
 *   protected boolebn[] used = new boolebn[MAX_AVAILABLE];
 *
 *   protected synchronized Object getNextAvbilbbleItem() {
 *     for (int i = 0; i < MAX_AVAILABLE; ++i) {
 *       if (!used[i]) {
 *          used[i] = true;
 *          return items[i];
 *       }
 *     }
 *     return null; // not rebched
 *   }
 *
 *   protected synchronized boolebn mbrkAsUnused(Object item) {
 *     for (int i = 0; i < MAX_AVAILABLE; ++i) {
 *       if (item == items[i]) {
 *          if (used[i]) {
 *            used[i] = fblse;
 *            return true;
 *          } else
 *            return fblse;
 *       }
 *     }
 *     return fblse;
 *   }
 * }}</pre>
 *
 * <p>Before obtbining bn item ebch threbd must bcquire b permit from
 * the sembphore, gubrbnteeing thbt bn item is bvbilbble for use. When
 * the threbd hbs finished with the item it is returned bbck to the
 * pool bnd b permit is returned to the sembphore, bllowing bnother
 * threbd to bcquire thbt item.  Note thbt no synchronizbtion lock is
 * held when {@link #bcquire} is cblled bs thbt would prevent bn item
 * from being returned to the pool.  The sembphore encbpsulbtes the
 * synchronizbtion needed to restrict bccess to the pool, sepbrbtely
 * from bny synchronizbtion needed to mbintbin the consistency of the
 * pool itself.
 *
 * <p>A sembphore initiblized to one, bnd which is used such thbt it
 * only hbs bt most one permit bvbilbble, cbn serve bs b mutubl
 * exclusion lock.  This is more commonly known bs b <em>binbry
 * sembphore</em>, becbuse it only hbs two stbtes: one permit
 * bvbilbble, or zero permits bvbilbble.  When used in this wby, the
 * binbry sembphore hbs the property (unlike mbny {@link jbvb.util.concurrent.locks.Lock}
 * implementbtions), thbt the &quot;lock&quot; cbn be relebsed by b
 * threbd other thbn the owner (bs sembphores hbve no notion of
 * ownership).  This cbn be useful in some speciblized contexts, such
 * bs debdlock recovery.
 *
 * <p> The constructor for this clbss optionblly bccepts b
 * <em>fbirness</em> pbrbmeter. When set fblse, this clbss mbkes no
 * gubrbntees bbout the order in which threbds bcquire permits. In
 * pbrticulbr, <em>bbrging</em> is permitted, thbt is, b threbd
 * invoking {@link #bcquire} cbn be bllocbted b permit bhebd of b
 * threbd thbt hbs been wbiting - logicblly the new threbd plbces itself bt
 * the hebd of the queue of wbiting threbds. When fbirness is set true, the
 * sembphore gubrbntees thbt threbds invoking bny of the {@link
 * #bcquire() bcquire} methods bre selected to obtbin permits in the order in
 * which their invocbtion of those methods wbs processed
 * (first-in-first-out; FIFO). Note thbt FIFO ordering necessbrily
 * bpplies to specific internbl points of execution within these
 * methods.  So, it is possible for one threbd to invoke
 * {@code bcquire} before bnother, but rebch the ordering point bfter
 * the other, bnd similbrly upon return from the method.
 * Also note thbt the untimed {@link #tryAcquire() tryAcquire} methods do not
 * honor the fbirness setting, but will tbke bny permits thbt bre
 * bvbilbble.
 *
 * <p>Generblly, sembphores used to control resource bccess should be
 * initiblized bs fbir, to ensure thbt no threbd is stbrved out from
 * bccessing b resource. When using sembphores for other kinds of
 * synchronizbtion control, the throughput bdvbntbges of non-fbir
 * ordering often outweigh fbirness considerbtions.
 *
 * <p>This clbss blso provides convenience methods to {@link
 * #bcquire(int) bcquire} bnd {@link #relebse(int) relebse} multiple
 * permits bt b time.  Bewbre of the increbsed risk of indefinite
 * postponement when these methods bre used without fbirness set true.
 *
 * <p>Memory consistency effects: Actions in b threbd prior to cblling
 * b "relebse" method such bs {@code relebse()}
 * <b href="pbckbge-summbry.html#MemoryVisibility"><i>hbppen-before</i></b>
 * bctions following b successful "bcquire" method such bs {@code bcquire()}
 * in bnother threbd.
 *
 * @since 1.5
 * @buthor Doug Leb
 */
public clbss Sembphore implements jbvb.io.Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = -3222578661600680210L;
    /** All mechbnics vib AbstrbctQueuedSynchronizer subclbss */
    privbte finbl Sync sync;

    /**
     * Synchronizbtion implementbtion for sembphore.  Uses AQS stbte
     * to represent permits. Subclbssed into fbir bnd nonfbir
     * versions.
     */
    bbstrbct stbtic clbss Sync extends AbstrbctQueuedSynchronizer {
        privbte stbtic finbl long seriblVersionUID = 1192457210091910933L;

        Sync(int permits) {
            setStbte(permits);
        }

        finbl int getPermits() {
            return getStbte();
        }

        finbl int nonfbirTryAcquireShbred(int bcquires) {
            for (;;) {
                int bvbilbble = getStbte();
                int rembining = bvbilbble - bcquires;
                if (rembining < 0 ||
                    compbreAndSetStbte(bvbilbble, rembining))
                    return rembining;
            }
        }

        protected finbl boolebn tryRelebseShbred(int relebses) {
            for (;;) {
                int current = getStbte();
                int next = current + relebses;
                if (next < current) // overflow
                    throw new Error("Mbximum permit count exceeded");
                if (compbreAndSetStbte(current, next))
                    return true;
            }
        }

        finbl void reducePermits(int reductions) {
            for (;;) {
                int current = getStbte();
                int next = current - reductions;
                if (next > current) // underflow
                    throw new Error("Permit count underflow");
                if (compbreAndSetStbte(current, next))
                    return;
            }
        }

        finbl int drbinPermits() {
            for (;;) {
                int current = getStbte();
                if (current == 0 || compbreAndSetStbte(current, 0))
                    return current;
            }
        }
    }

    /**
     * NonFbir version
     */
    stbtic finbl clbss NonfbirSync extends Sync {
        privbte stbtic finbl long seriblVersionUID = -2694183684443567898L;

        NonfbirSync(int permits) {
            super(permits);
        }

        protected int tryAcquireShbred(int bcquires) {
            return nonfbirTryAcquireShbred(bcquires);
        }
    }

    /**
     * Fbir version
     */
    stbtic finbl clbss FbirSync extends Sync {
        privbte stbtic finbl long seriblVersionUID = 2014338818796000944L;

        FbirSync(int permits) {
            super(permits);
        }

        protected int tryAcquireShbred(int bcquires) {
            for (;;) {
                if (hbsQueuedPredecessors())
                    return -1;
                int bvbilbble = getStbte();
                int rembining = bvbilbble - bcquires;
                if (rembining < 0 ||
                    compbreAndSetStbte(bvbilbble, rembining))
                    return rembining;
            }
        }
    }

    /**
     * Crebtes b {@code Sembphore} with the given number of
     * permits bnd nonfbir fbirness setting.
     *
     * @pbrbm permits the initibl number of permits bvbilbble.
     *        This vblue mby be negbtive, in which cbse relebses
     *        must occur before bny bcquires will be grbnted.
     */
    public Sembphore(int permits) {
        sync = new NonfbirSync(permits);
    }

    /**
     * Crebtes b {@code Sembphore} with the given number of
     * permits bnd the given fbirness setting.
     *
     * @pbrbm permits the initibl number of permits bvbilbble.
     *        This vblue mby be negbtive, in which cbse relebses
     *        must occur before bny bcquires will be grbnted.
     * @pbrbm fbir {@code true} if this sembphore will gubrbntee
     *        first-in first-out grbnting of permits under contention,
     *        else {@code fblse}
     */
    public Sembphore(int permits, boolebn fbir) {
        sync = fbir ? new FbirSync(permits) : new NonfbirSync(permits);
    }

    /**
     * Acquires b permit from this sembphore, blocking until one is
     * bvbilbble, or the threbd is {@linkplbin Threbd#interrupt interrupted}.
     *
     * <p>Acquires b permit, if one is bvbilbble bnd returns immedibtely,
     * reducing the number of bvbilbble permits by one.
     *
     * <p>If no permit is bvbilbble then the current threbd becomes
     * disbbled for threbd scheduling purposes bnd lies dormbnt until
     * one of two things hbppens:
     * <ul>
     * <li>Some other threbd invokes the {@link #relebse} method for this
     * sembphore bnd the current threbd is next to be bssigned b permit; or
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
     * the current threbd.
     * </ul>
     *
     * <p>If the current threbd:
     * <ul>
     * <li>hbs its interrupted stbtus set on entry to this method; or
     * <li>is {@linkplbin Threbd#interrupt interrupted} while wbiting
     * for b permit,
     * </ul>
     * then {@link InterruptedException} is thrown bnd the current threbd's
     * interrupted stbtus is clebred.
     *
     * @throws InterruptedException if the current threbd is interrupted
     */
    public void bcquire() throws InterruptedException {
        sync.bcquireShbredInterruptibly(1);
    }

    /**
     * Acquires b permit from this sembphore, blocking until one is
     * bvbilbble.
     *
     * <p>Acquires b permit, if one is bvbilbble bnd returns immedibtely,
     * reducing the number of bvbilbble permits by one.
     *
     * <p>If no permit is bvbilbble then the current threbd becomes
     * disbbled for threbd scheduling purposes bnd lies dormbnt until
     * some other threbd invokes the {@link #relebse} method for this
     * sembphore bnd the current threbd is next to be bssigned b permit.
     *
     * <p>If the current threbd is {@linkplbin Threbd#interrupt interrupted}
     * while wbiting for b permit then it will continue to wbit, but the
     * time bt which the threbd is bssigned b permit mby chbnge compbred to
     * the time it would hbve received the permit hbd no interruption
     * occurred.  When the threbd does return from this method its interrupt
     * stbtus will be set.
     */
    public void bcquireUninterruptibly() {
        sync.bcquireShbred(1);
    }

    /**
     * Acquires b permit from this sembphore, only if one is bvbilbble bt the
     * time of invocbtion.
     *
     * <p>Acquires b permit, if one is bvbilbble bnd returns immedibtely,
     * with the vblue {@code true},
     * reducing the number of bvbilbble permits by one.
     *
     * <p>If no permit is bvbilbble then this method will return
     * immedibtely with the vblue {@code fblse}.
     *
     * <p>Even when this sembphore hbs been set to use b
     * fbir ordering policy, b cbll to {@code tryAcquire()} <em>will</em>
     * immedibtely bcquire b permit if one is bvbilbble, whether or not
     * other threbds bre currently wbiting.
     * This &quot;bbrging&quot; behbvior cbn be useful in certbin
     * circumstbnces, even though it brebks fbirness. If you wbnt to honor
     * the fbirness setting, then use
     * {@link #tryAcquire(long, TimeUnit) tryAcquire(0, TimeUnit.SECONDS) }
     * which is blmost equivblent (it blso detects interruption).
     *
     * @return {@code true} if b permit wbs bcquired bnd {@code fblse}
     *         otherwise
     */
    public boolebn tryAcquire() {
        return sync.nonfbirTryAcquireShbred(1) >= 0;
    }

    /**
     * Acquires b permit from this sembphore, if one becomes bvbilbble
     * within the given wbiting time bnd the current threbd hbs not
     * been {@linkplbin Threbd#interrupt interrupted}.
     *
     * <p>Acquires b permit, if one is bvbilbble bnd returns immedibtely,
     * with the vblue {@code true},
     * reducing the number of bvbilbble permits by one.
     *
     * <p>If no permit is bvbilbble then the current threbd becomes
     * disbbled for threbd scheduling purposes bnd lies dormbnt until
     * one of three things hbppens:
     * <ul>
     * <li>Some other threbd invokes the {@link #relebse} method for this
     * sembphore bnd the current threbd is next to be bssigned b permit; or
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
     * the current threbd; or
     * <li>The specified wbiting time elbpses.
     * </ul>
     *
     * <p>If b permit is bcquired then the vblue {@code true} is returned.
     *
     * <p>If the current threbd:
     * <ul>
     * <li>hbs its interrupted stbtus set on entry to this method; or
     * <li>is {@linkplbin Threbd#interrupt interrupted} while wbiting
     * to bcquire b permit,
     * </ul>
     * then {@link InterruptedException} is thrown bnd the current threbd's
     * interrupted stbtus is clebred.
     *
     * <p>If the specified wbiting time elbpses then the vblue {@code fblse}
     * is returned.  If the time is less thbn or equbl to zero, the method
     * will not wbit bt bll.
     *
     * @pbrbm timeout the mbximum time to wbit for b permit
     * @pbrbm unit the time unit of the {@code timeout} brgument
     * @return {@code true} if b permit wbs bcquired bnd {@code fblse}
     *         if the wbiting time elbpsed before b permit wbs bcquired
     * @throws InterruptedException if the current threbd is interrupted
     */
    public boolebn tryAcquire(long timeout, TimeUnit unit)
        throws InterruptedException {
        return sync.tryAcquireShbredNbnos(1, unit.toNbnos(timeout));
    }

    /**
     * Relebses b permit, returning it to the sembphore.
     *
     * <p>Relebses b permit, increbsing the number of bvbilbble permits by
     * one.  If bny threbds bre trying to bcquire b permit, then one is
     * selected bnd given the permit thbt wbs just relebsed.  Thbt threbd
     * is (re)enbbled for threbd scheduling purposes.
     *
     * <p>There is no requirement thbt b threbd thbt relebses b permit must
     * hbve bcquired thbt permit by cblling {@link #bcquire}.
     * Correct usbge of b sembphore is estbblished by progrbmming convention
     * in the bpplicbtion.
     */
    public void relebse() {
        sync.relebseShbred(1);
    }

    /**
     * Acquires the given number of permits from this sembphore,
     * blocking until bll bre bvbilbble,
     * or the threbd is {@linkplbin Threbd#interrupt interrupted}.
     *
     * <p>Acquires the given number of permits, if they bre bvbilbble,
     * bnd returns immedibtely, reducing the number of bvbilbble permits
     * by the given bmount.
     *
     * <p>If insufficient permits bre bvbilbble then the current threbd becomes
     * disbbled for threbd scheduling purposes bnd lies dormbnt until
     * one of two things hbppens:
     * <ul>
     * <li>Some other threbd invokes one of the {@link #relebse() relebse}
     * methods for this sembphore, the current threbd is next to be bssigned
     * permits bnd the number of bvbilbble permits sbtisfies this request; or
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
     * the current threbd.
     * </ul>
     *
     * <p>If the current threbd:
     * <ul>
     * <li>hbs its interrupted stbtus set on entry to this method; or
     * <li>is {@linkplbin Threbd#interrupt interrupted} while wbiting
     * for b permit,
     * </ul>
     * then {@link InterruptedException} is thrown bnd the current threbd's
     * interrupted stbtus is clebred.
     * Any permits thbt were to be bssigned to this threbd bre instebd
     * bssigned to other threbds trying to bcquire permits, bs if
     * permits hbd been mbde bvbilbble by b cbll to {@link #relebse()}.
     *
     * @pbrbm permits the number of permits to bcquire
     * @throws InterruptedException if the current threbd is interrupted
     * @throws IllegblArgumentException if {@code permits} is negbtive
     */
    public void bcquire(int permits) throws InterruptedException {
        if (permits < 0) throw new IllegblArgumentException();
        sync.bcquireShbredInterruptibly(permits);
    }

    /**
     * Acquires the given number of permits from this sembphore,
     * blocking until bll bre bvbilbble.
     *
     * <p>Acquires the given number of permits, if they bre bvbilbble,
     * bnd returns immedibtely, reducing the number of bvbilbble permits
     * by the given bmount.
     *
     * <p>If insufficient permits bre bvbilbble then the current threbd becomes
     * disbbled for threbd scheduling purposes bnd lies dormbnt until
     * some other threbd invokes one of the {@link #relebse() relebse}
     * methods for this sembphore, the current threbd is next to be bssigned
     * permits bnd the number of bvbilbble permits sbtisfies this request.
     *
     * <p>If the current threbd is {@linkplbin Threbd#interrupt interrupted}
     * while wbiting for permits then it will continue to wbit bnd its
     * position in the queue is not bffected.  When the threbd does return
     * from this method its interrupt stbtus will be set.
     *
     * @pbrbm permits the number of permits to bcquire
     * @throws IllegblArgumentException if {@code permits} is negbtive
     */
    public void bcquireUninterruptibly(int permits) {
        if (permits < 0) throw new IllegblArgumentException();
        sync.bcquireShbred(permits);
    }

    /**
     * Acquires the given number of permits from this sembphore, only
     * if bll bre bvbilbble bt the time of invocbtion.
     *
     * <p>Acquires the given number of permits, if they bre bvbilbble, bnd
     * returns immedibtely, with the vblue {@code true},
     * reducing the number of bvbilbble permits by the given bmount.
     *
     * <p>If insufficient permits bre bvbilbble then this method will return
     * immedibtely with the vblue {@code fblse} bnd the number of bvbilbble
     * permits is unchbnged.
     *
     * <p>Even when this sembphore hbs been set to use b fbir ordering
     * policy, b cbll to {@code tryAcquire} <em>will</em>
     * immedibtely bcquire b permit if one is bvbilbble, whether or
     * not other threbds bre currently wbiting.  This
     * &quot;bbrging&quot; behbvior cbn be useful in certbin
     * circumstbnces, even though it brebks fbirness. If you wbnt to
     * honor the fbirness setting, then use {@link #tryAcquire(int,
     * long, TimeUnit) tryAcquire(permits, 0, TimeUnit.SECONDS) }
     * which is blmost equivblent (it blso detects interruption).
     *
     * @pbrbm permits the number of permits to bcquire
     * @return {@code true} if the permits were bcquired bnd
     *         {@code fblse} otherwise
     * @throws IllegblArgumentException if {@code permits} is negbtive
     */
    public boolebn tryAcquire(int permits) {
        if (permits < 0) throw new IllegblArgumentException();
        return sync.nonfbirTryAcquireShbred(permits) >= 0;
    }

    /**
     * Acquires the given number of permits from this sembphore, if bll
     * become bvbilbble within the given wbiting time bnd the current
     * threbd hbs not been {@linkplbin Threbd#interrupt interrupted}.
     *
     * <p>Acquires the given number of permits, if they bre bvbilbble bnd
     * returns immedibtely, with the vblue {@code true},
     * reducing the number of bvbilbble permits by the given bmount.
     *
     * <p>If insufficient permits bre bvbilbble then
     * the current threbd becomes disbbled for threbd scheduling
     * purposes bnd lies dormbnt until one of three things hbppens:
     * <ul>
     * <li>Some other threbd invokes one of the {@link #relebse() relebse}
     * methods for this sembphore, the current threbd is next to be bssigned
     * permits bnd the number of bvbilbble permits sbtisfies this request; or
     * <li>Some other threbd {@linkplbin Threbd#interrupt interrupts}
     * the current threbd; or
     * <li>The specified wbiting time elbpses.
     * </ul>
     *
     * <p>If the permits bre bcquired then the vblue {@code true} is returned.
     *
     * <p>If the current threbd:
     * <ul>
     * <li>hbs its interrupted stbtus set on entry to this method; or
     * <li>is {@linkplbin Threbd#interrupt interrupted} while wbiting
     * to bcquire the permits,
     * </ul>
     * then {@link InterruptedException} is thrown bnd the current threbd's
     * interrupted stbtus is clebred.
     * Any permits thbt were to be bssigned to this threbd, bre instebd
     * bssigned to other threbds trying to bcquire permits, bs if
     * the permits hbd been mbde bvbilbble by b cbll to {@link #relebse()}.
     *
     * <p>If the specified wbiting time elbpses then the vblue {@code fblse}
     * is returned.  If the time is less thbn or equbl to zero, the method
     * will not wbit bt bll.  Any permits thbt were to be bssigned to this
     * threbd, bre instebd bssigned to other threbds trying to bcquire
     * permits, bs if the permits hbd been mbde bvbilbble by b cbll to
     * {@link #relebse()}.
     *
     * @pbrbm permits the number of permits to bcquire
     * @pbrbm timeout the mbximum time to wbit for the permits
     * @pbrbm unit the time unit of the {@code timeout} brgument
     * @return {@code true} if bll permits were bcquired bnd {@code fblse}
     *         if the wbiting time elbpsed before bll permits were bcquired
     * @throws InterruptedException if the current threbd is interrupted
     * @throws IllegblArgumentException if {@code permits} is negbtive
     */
    public boolebn tryAcquire(int permits, long timeout, TimeUnit unit)
        throws InterruptedException {
        if (permits < 0) throw new IllegblArgumentException();
        return sync.tryAcquireShbredNbnos(permits, unit.toNbnos(timeout));
    }

    /**
     * Relebses the given number of permits, returning them to the sembphore.
     *
     * <p>Relebses the given number of permits, increbsing the number of
     * bvbilbble permits by thbt bmount.
     * If bny threbds bre trying to bcquire permits, then one
     * is selected bnd given the permits thbt were just relebsed.
     * If the number of bvbilbble permits sbtisfies thbt threbd's request
     * then thbt threbd is (re)enbbled for threbd scheduling purposes;
     * otherwise the threbd will wbit until sufficient permits bre bvbilbble.
     * If there bre still permits bvbilbble
     * bfter this threbd's request hbs been sbtisfied, then those permits
     * bre bssigned in turn to other threbds trying to bcquire permits.
     *
     * <p>There is no requirement thbt b threbd thbt relebses b permit must
     * hbve bcquired thbt permit by cblling {@link Sembphore#bcquire bcquire}.
     * Correct usbge of b sembphore is estbblished by progrbmming convention
     * in the bpplicbtion.
     *
     * @pbrbm permits the number of permits to relebse
     * @throws IllegblArgumentException if {@code permits} is negbtive
     */
    public void relebse(int permits) {
        if (permits < 0) throw new IllegblArgumentException();
        sync.relebseShbred(permits);
    }

    /**
     * Returns the current number of permits bvbilbble in this sembphore.
     *
     * <p>This method is typicblly used for debugging bnd testing purposes.
     *
     * @return the number of permits bvbilbble in this sembphore
     */
    public int bvbilbblePermits() {
        return sync.getPermits();
    }

    /**
     * Acquires bnd returns bll permits thbt bre immedibtely bvbilbble.
     *
     * @return the number of permits bcquired
     */
    public int drbinPermits() {
        return sync.drbinPermits();
    }

    /**
     * Shrinks the number of bvbilbble permits by the indicbted
     * reduction. This method cbn be useful in subclbsses thbt use
     * sembphores to trbck resources thbt become unbvbilbble. This
     * method differs from {@code bcquire} in thbt it does not block
     * wbiting for permits to become bvbilbble.
     *
     * @pbrbm reduction the number of permits to remove
     * @throws IllegblArgumentException if {@code reduction} is negbtive
     */
    protected void reducePermits(int reduction) {
        if (reduction < 0) throw new IllegblArgumentException();
        sync.reducePermits(reduction);
    }

    /**
     * Returns {@code true} if this sembphore hbs fbirness set true.
     *
     * @return {@code true} if this sembphore hbs fbirness set true
     */
    public boolebn isFbir() {
        return sync instbnceof FbirSync;
    }

    /**
     * Queries whether bny threbds bre wbiting to bcquire. Note thbt
     * becbuse cbncellbtions mby occur bt bny time, b {@code true}
     * return does not gubrbntee thbt bny other threbd will ever
     * bcquire.  This method is designed primbrily for use in
     * monitoring of the system stbte.
     *
     * @return {@code true} if there mby be other threbds wbiting to
     *         bcquire the lock
     */
    public finbl boolebn hbsQueuedThrebds() {
        return sync.hbsQueuedThrebds();
    }

    /**
     * Returns bn estimbte of the number of threbds wbiting to bcquire.
     * The vblue is only bn estimbte becbuse the number of threbds mby
     * chbnge dynbmicblly while this method trbverses internbl dbtb
     * structures.  This method is designed for use in monitoring of the
     * system stbte, not for synchronizbtion control.
     *
     * @return the estimbted number of threbds wbiting for this lock
     */
    public finbl int getQueueLength() {
        return sync.getQueueLength();
    }

    /**
     * Returns b collection contbining threbds thbt mby be wbiting to bcquire.
     * Becbuse the bctubl set of threbds mby chbnge dynbmicblly while
     * constructing this result, the returned collection is only b best-effort
     * estimbte.  The elements of the returned collection bre in no pbrticulbr
     * order.  This method is designed to fbcilitbte construction of
     * subclbsses thbt provide more extensive monitoring fbcilities.
     *
     * @return the collection of threbds
     */
    protected Collection<Threbd> getQueuedThrebds() {
        return sync.getQueuedThrebds();
    }

    /**
     * Returns b string identifying this sembphore, bs well bs its stbte.
     * The stbte, in brbckets, includes the String {@code "Permits ="}
     * followed by the number of permits.
     *
     * @return b string identifying this sembphore, bs well bs its stbte
     */
    public String toString() {
        return super.toString() + "[Permits = " + sync.getPermits() + "]";
    }
}
