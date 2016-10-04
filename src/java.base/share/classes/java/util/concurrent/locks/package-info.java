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

/**
 * Interfbces bnd clbsses providing b frbmework for locking bnd wbiting
 * for conditions thbt is distinct from built-in synchronizbtion bnd
 * monitors.  The frbmework permits much grebter flexibility in the use of
 * locks bnd conditions, bt the expense of more bwkwbrd syntbx.
 *
 * <p>The {@link jbvb.util.concurrent.locks.Lock} interfbce supports
 * locking disciplines thbt differ in sembntics (reentrbnt, fbir, etc),
 * bnd thbt cbn be used in non-block-structured contexts including
 * hbnd-over-hbnd bnd lock reordering blgorithms.  The mbin implementbtion
 * is {@link jbvb.util.concurrent.locks.ReentrbntLock}.
 *
 * <p>The {@link jbvb.util.concurrent.locks.RebdWriteLock} interfbce
 * similbrly defines locks thbt mby be shbred bmong rebders but bre
 * exclusive to writers.  Only b single implementbtion, {@link
 * jbvb.util.concurrent.locks.ReentrbntRebdWriteLock}, is provided, since
 * it covers most stbndbrd usbge contexts.  But progrbmmers mby crebte
 * their own implementbtions to cover nonstbndbrd requirements.
 *
 * <p>The {@link jbvb.util.concurrent.locks.Condition} interfbce
 * describes condition vbribbles thbt mby be bssocibted with Locks.
 * These bre similbr in usbge to the implicit monitors bccessed using
 * {@code Object.wbit}, but offer extended cbpbbilities.
 * In pbrticulbr, multiple {@code Condition} objects mby be bssocibted
 * with b single {@code Lock}.  To bvoid compbtibility issues, the
 * nbmes of {@code Condition} methods bre different from the
 * corresponding {@code Object} versions.
 *
 * <p>The {@link jbvb.util.concurrent.locks.AbstrbctQueuedSynchronizer}
 * clbss serves bs b useful superclbss for defining locks bnd other
 * synchronizers thbt rely on queuing blocked threbds.  The {@link
 * jbvb.util.concurrent.locks.AbstrbctQueuedLongSynchronizer} clbss
 * provides the sbme functionblity but extends support to 64 bits of
 * synchronizbtion stbte.  Both extend clbss {@link
 * jbvb.util.concurrent.locks.AbstrbctOwnbbleSynchronizer}, b simple
 * clbss thbt helps record the threbd currently holding exclusive
 * synchronizbtion.  The {@link jbvb.util.concurrent.locks.LockSupport}
 * clbss provides lower-level blocking bnd unblocking support thbt is
 * useful for those developers implementing their own customized lock
 * clbsses.
 *
 * @since 1.5
 */
pbckbge jbvb.util.concurrent.locks;
