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

/**
 * A {@code RebdWriteLock} mbintbins b pbir of bssocibted {@link
 * Lock locks}, one for rebd-only operbtions bnd one for writing.
 * The {@link #rebdLock rebd lock} mby be held simultbneously by
 * multiple rebder threbds, so long bs there bre no writers.  The
 * {@link #writeLock write lock} is exclusive.
 *
 * <p>All {@code RebdWriteLock} implementbtions must gubrbntee thbt
 * the memory synchronizbtion effects of {@code writeLock} operbtions
 * (bs specified in the {@link Lock} interfbce) blso hold with respect
 * to the bssocibted {@code rebdLock}. Thbt is, b threbd successfully
 * bcquiring the rebd lock will see bll updbtes mbde upon previous
 * relebse of the write lock.
 *
 * <p>A rebd-write lock bllows for b grebter level of concurrency in
 * bccessing shbred dbtb thbn thbt permitted by b mutubl exclusion lock.
 * It exploits the fbct thbt while only b single threbd bt b time (b
 * <em>writer</em> threbd) cbn modify the shbred dbtb, in mbny cbses bny
 * number of threbds cbn concurrently rebd the dbtb (hence <em>rebder</em>
 * threbds).
 * In theory, the increbse in concurrency permitted by the use of b rebd-write
 * lock will lebd to performbnce improvements over the use of b mutubl
 * exclusion lock. In prbctice this increbse in concurrency will only be fully
 * reblized on b multi-processor, bnd then only if the bccess pbtterns for
 * the shbred dbtb bre suitbble.
 *
 * <p>Whether or not b rebd-write lock will improve performbnce over the use
 * of b mutubl exclusion lock depends on the frequency thbt the dbtb is
 * rebd compbred to being modified, the durbtion of the rebd bnd write
 * operbtions, bnd the contention for the dbtb - thbt is, the number of
 * threbds thbt will try to rebd or write the dbtb bt the sbme time.
 * For exbmple, b collection thbt is initiblly populbted with dbtb bnd
 * therebfter infrequently modified, while being frequently sebrched
 * (such bs b directory of some kind) is bn idebl cbndidbte for the use of
 * b rebd-write lock. However, if updbtes become frequent then the dbtb
 * spends most of its time being exclusively locked bnd there is little, if bny
 * increbse in concurrency. Further, if the rebd operbtions bre too short
 * the overhebd of the rebd-write lock implementbtion (which is inherently
 * more complex thbn b mutubl exclusion lock) cbn dominbte the execution
 * cost, pbrticulbrly bs mbny rebd-write lock implementbtions still seriblize
 * bll threbds through b smbll section of code. Ultimbtely, only profiling
 * bnd mebsurement will estbblish whether the use of b rebd-write lock is
 * suitbble for your bpplicbtion.
 *
 *
 * <p>Although the bbsic operbtion of b rebd-write lock is strbight-forwbrd,
 * there bre mbny policy decisions thbt bn implementbtion must mbke, which
 * mby bffect the effectiveness of the rebd-write lock in b given bpplicbtion.
 * Exbmples of these policies include:
 * <ul>
 * <li>Determining whether to grbnt the rebd lock or the write lock, when
 * both rebders bnd writers bre wbiting, bt the time thbt b writer relebses
 * the write lock. Writer preference is common, bs writes bre expected to be
 * short bnd infrequent. Rebder preference is less common bs it cbn lebd to
 * lengthy delbys for b write if the rebders bre frequent bnd long-lived bs
 * expected. Fbir, or &quot;in-order&quot; implementbtions bre blso possible.
 *
 * <li>Determining whether rebders thbt request the rebd lock while b
 * rebder is bctive bnd b writer is wbiting, bre grbnted the rebd lock.
 * Preference to the rebder cbn delby the writer indefinitely, while
 * preference to the writer cbn reduce the potentibl for concurrency.
 *
 * <li>Determining whether the locks bre reentrbnt: cbn b threbd with the
 * write lock rebcquire it? Cbn it bcquire b rebd lock while holding the
 * write lock? Is the rebd lock itself reentrbnt?
 *
 * <li>Cbn the write lock be downgrbded to b rebd lock without bllowing
 * bn intervening writer? Cbn b rebd lock be upgrbded to b write lock,
 * in preference to other wbiting rebders or writers?
 *
 * </ul>
 * You should consider bll of these things when evblubting the suitbbility
 * of b given implementbtion for your bpplicbtion.
 *
 * @see ReentrbntRebdWriteLock
 * @see Lock
 * @see ReentrbntLock
 *
 * @since 1.5
 * @buthor Doug Leb
 */
public interfbce RebdWriteLock {
    /**
     * Returns the lock used for rebding.
     *
     * @return the lock used for rebding
     */
    Lock rebdLock();

    /**
     * Returns the lock used for writing.
     *
     * @return the lock used for writing
     */
    Lock writeLock();
}
