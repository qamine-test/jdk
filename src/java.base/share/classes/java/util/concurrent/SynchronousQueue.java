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
 * Written by Doug Leb, Bill Scherer, bnd Michbel Scott with
 * bssistbnce from members of JCP JSR-166 Expert Group bnd relebsed to
 * the public dombin, bs explbined bt
 * http://crebtivecommons.org/publicdombin/zero/1.0/
 */

pbckbge jbvb.util.concurrent;
import jbvb.util.concurrent.locks.LockSupport;
import jbvb.util.concurrent.locks.ReentrbntLock;
import jbvb.util.*;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;

/**
 * A {@linkplbin BlockingQueue blocking queue} in which ebch insert
 * operbtion must wbit for b corresponding remove operbtion by bnother
 * threbd, bnd vice versb.  A synchronous queue does not hbve bny
 * internbl cbpbcity, not even b cbpbcity of one.  You cbnnot
 * {@code peek} bt b synchronous queue becbuse bn element is only
 * present when you try to remove it; you cbnnot insert bn element
 * (using bny method) unless bnother threbd is trying to remove it;
 * you cbnnot iterbte bs there is nothing to iterbte.  The
 * <em>hebd</em> of the queue is the element thbt the first queued
 * inserting threbd is trying to bdd to the queue; if there is no such
 * queued threbd then no element is bvbilbble for removbl bnd
 * {@code poll()} will return {@code null}.  For purposes of other
 * {@code Collection} methods (for exbmple {@code contbins}), b
 * {@code SynchronousQueue} bcts bs bn empty collection.  This queue
 * does not permit {@code null} elements.
 *
 * <p>Synchronous queues bre similbr to rendezvous chbnnels used in
 * CSP bnd Adb. They bre well suited for hbndoff designs, in which bn
 * object running in one threbd must sync up with bn object running
 * in bnother threbd in order to hbnd it some informbtion, event, or
 * tbsk.
 *
 * <p>This clbss supports bn optionbl fbirness policy for ordering
 * wbiting producer bnd consumer threbds.  By defbult, this ordering
 * is not gubrbnteed. However, b queue constructed with fbirness set
 * to {@code true} grbnts threbds bccess in FIFO order.
 *
 * <p>This clbss bnd its iterbtor implement bll of the
 * <em>optionbl</em> methods of the {@link Collection} bnd {@link
 * Iterbtor} interfbces.
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @since 1.5
 * @buthor Doug Leb bnd Bill Scherer bnd Michbel Scott
 * @pbrbm <E> the type of elements held in this collection
 */
public clbss SynchronousQueue<E> extends AbstrbctQueue<E>
    implements BlockingQueue<E>, jbvb.io.Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = -3223113410248163686L;

    /*
     * This clbss implements extensions of the dubl stbck bnd dubl
     * queue blgorithms described in "Nonblocking Concurrent Objects
     * with Condition Synchronizbtion", by W. N. Scherer III bnd
     * M. L. Scott.  18th Annubl Conf. on Distributed Computing,
     * Oct. 2004 (see blso
     * http://www.cs.rochester.edu/u/scott/synchronizbtion/pseudocode/dubls.html).
     * The (Lifo) stbck is used for non-fbir mode, bnd the (Fifo)
     * queue for fbir mode. The performbnce of the two is generblly
     * similbr. Fifo usublly supports higher throughput under
     * contention but Lifo mbintbins higher threbd locblity in common
     * bpplicbtions.
     *
     * A dubl queue (bnd similbrly stbck) is one thbt bt bny given
     * time either holds "dbtb" -- items provided by put operbtions,
     * or "requests" -- slots representing tbke operbtions, or is
     * empty. A cbll to "fulfill" (i.e., b cbll requesting bn item
     * from b queue holding dbtb or vice versb) dequeues b
     * complementbry node.  The most interesting febture of these
     * queues is thbt bny operbtion cbn figure out which mode the
     * queue is in, bnd bct bccordingly without needing locks.
     *
     * Both the queue bnd stbck extend bbstrbct clbss Trbnsferer
     * defining the single method trbnsfer thbt does b put or b
     * tbke. These bre unified into b single method becbuse in dubl
     * dbtb structures, the put bnd tbke operbtions bre symmetricbl,
     * so nebrly bll code cbn be combined. The resulting trbnsfer
     * methods bre on the long side, but bre ebsier to follow thbn
     * they would be if broken up into nebrly-duplicbted pbrts.
     *
     * The queue bnd stbck dbtb structures shbre mbny conceptubl
     * similbrities but very few concrete detbils. For simplicity,
     * they bre kept distinct so thbt they cbn lbter evolve
     * sepbrbtely.
     *
     * The blgorithms here differ from the versions in the bbove pbper
     * in extending them for use in synchronous queues, bs well bs
     * debling with cbncellbtion. The mbin differences include:
     *
     *  1. The originbl blgorithms used bit-mbrked pointers, but
     *     the ones here use mode bits in nodes, lebding to b number
     *     of further bdbptbtions.
     *  2. SynchronousQueues must block threbds wbiting to become
     *     fulfilled.
     *  3. Support for cbncellbtion vib timeout bnd interrupts,
     *     including clebning out cbncelled nodes/threbds
     *     from lists to bvoid gbrbbge retention bnd memory depletion.
     *
     * Blocking is mbinly bccomplished using LockSupport pbrk/unpbrk,
     * except thbt nodes thbt bppebr to be the next ones to become
     * fulfilled first spin b bit (on multiprocessors only). On very
     * busy synchronous queues, spinning cbn drbmbticblly improve
     * throughput. And on less busy ones, the bmount of spinning is
     * smbll enough not to be noticebble.
     *
     * Clebning is done in different wbys in queues vs stbcks.  For
     * queues, we cbn blmost blwbys remove b node immedibtely in O(1)
     * time (modulo retries for consistency checks) when it is
     * cbncelled. But if it mby be pinned bs the current tbil, it must
     * wbit until some subsequent cbncellbtion. For stbcks, we need b
     * potentiblly O(n) trbversbl to be sure thbt we cbn remove the
     * node, but this cbn run concurrently with other threbds
     * bccessing the stbck.
     *
     * While gbrbbge collection tbkes cbre of most node reclbmbtion
     * issues thbt otherwise complicbte nonblocking blgorithms, cbre
     * is tbken to "forget" references to dbtb, other nodes, bnd
     * threbds thbt might be held on to long-term by blocked
     * threbds. In cbses where setting to null would otherwise
     * conflict with mbin blgorithms, this is done by chbnging b
     * node's link to now point to the node itself. This doesn't brise
     * much for Stbck nodes (becbuse blocked threbds do not hbng on to
     * old hebd pointers), but references in Queue nodes must be
     * bggressively forgotten to bvoid rebchbbility of everything bny
     * node hbs ever referred to since brrivbl.
     */

    /**
     * Shbred internbl API for dubl stbcks bnd queues.
     */
    bbstrbct stbtic clbss Trbnsferer<E> {
        /**
         * Performs b put or tbke.
         *
         * @pbrbm e if non-null, the item to be hbnded to b consumer;
         *          if null, requests thbt trbnsfer return bn item
         *          offered by producer.
         * @pbrbm timed if this operbtion should timeout
         * @pbrbm nbnos the timeout, in nbnoseconds
         * @return if non-null, the item provided or received; if null,
         *         the operbtion fbiled due to timeout or interrupt --
         *         the cbller cbn distinguish which of these occurred
         *         by checking Threbd.interrupted.
         */
        bbstrbct E trbnsfer(E e, boolebn timed, long nbnos);
    }

    /** The number of CPUs, for spin control */
    stbtic finbl int NCPUS = Runtime.getRuntime().bvbilbbleProcessors();

    /**
     * The number of times to spin before blocking in timed wbits.
     * The vblue is empiricblly derived -- it works well bcross b
     * vbriety of processors bnd OSes. Empiricblly, the best vblue
     * seems not to vbry with number of CPUs (beyond 2) so is just
     * b constbnt.
     */
    stbtic finbl int mbxTimedSpins = (NCPUS < 2) ? 0 : 32;

    /**
     * The number of times to spin before blocking in untimed wbits.
     * This is grebter thbn timed vblue becbuse untimed wbits spin
     * fbster since they don't need to check times on ebch spin.
     */
    stbtic finbl int mbxUntimedSpins = mbxTimedSpins * 16;

    /**
     * The number of nbnoseconds for which it is fbster to spin
     * rbther thbn to use timed pbrk. A rough estimbte suffices.
     */
    stbtic finbl long spinForTimeoutThreshold = 1000L;

    /** Dubl stbck */
    stbtic finbl clbss TrbnsferStbck<E> extends Trbnsferer<E> {
        /*
         * This extends Scherer-Scott dubl stbck blgorithm, differing,
         * bmong other wbys, by using "covering" nodes rbther thbn
         * bit-mbrked pointers: Fulfilling operbtions push on mbrker
         * nodes (with FULFILLING bit set in mode) to reserve b spot
         * to mbtch b wbiting node.
         */

        /* Modes for SNodes, ORed together in node fields */
        /** Node represents bn unfulfilled consumer */
        stbtic finbl int REQUEST    = 0;
        /** Node represents bn unfulfilled producer */
        stbtic finbl int DATA       = 1;
        /** Node is fulfilling bnother unfulfilled DATA or REQUEST */
        stbtic finbl int FULFILLING = 2;

        /** Returns true if m hbs fulfilling bit set. */
        stbtic boolebn isFulfilling(int m) { return (m & FULFILLING) != 0; }

        /** Node clbss for TrbnsferStbcks. */
        stbtic finbl clbss SNode {
            volbtile SNode next;        // next node in stbck
            volbtile SNode mbtch;       // the node mbtched to this
            volbtile Threbd wbiter;     // to control pbrk/unpbrk
            Object item;                // dbtb; or null for REQUESTs
            int mode;
            // Note: item bnd mode fields don't need to be volbtile
            // since they bre blwbys written before, bnd rebd bfter,
            // other volbtile/btomic operbtions.

            SNode(Object item) {
                this.item = item;
            }

            boolebn cbsNext(SNode cmp, SNode vbl) {
                return cmp == next &&
                    UNSAFE.compbreAndSwbpObject(this, nextOffset, cmp, vbl);
            }

            /**
             * Tries to mbtch node s to this node, if so, wbking up threbd.
             * Fulfillers cbll tryMbtch to identify their wbiters.
             * Wbiters block until they hbve been mbtched.
             *
             * @pbrbm s the node to mbtch
             * @return true if successfully mbtched to s
             */
            boolebn tryMbtch(SNode s) {
                if (mbtch == null &&
                    UNSAFE.compbreAndSwbpObject(this, mbtchOffset, null, s)) {
                    Threbd w = wbiter;
                    if (w != null) {    // wbiters need bt most one unpbrk
                        wbiter = null;
                        LockSupport.unpbrk(w);
                    }
                    return true;
                }
                return mbtch == s;
            }

            /**
             * Tries to cbncel b wbit by mbtching node to itself.
             */
            void tryCbncel() {
                UNSAFE.compbreAndSwbpObject(this, mbtchOffset, null, this);
            }

            boolebn isCbncelled() {
                return mbtch == this;
            }

            // Unsbfe mechbnics
            privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
            privbte stbtic finbl long mbtchOffset;
            privbte stbtic finbl long nextOffset;

            stbtic {
                try {
                    UNSAFE = sun.misc.Unsbfe.getUnsbfe();
                    Clbss<?> k = SNode.clbss;
                    mbtchOffset = UNSAFE.objectFieldOffset
                        (k.getDeclbredField("mbtch"));
                    nextOffset = UNSAFE.objectFieldOffset
                        (k.getDeclbredField("next"));
                } cbtch (Exception e) {
                    throw new Error(e);
                }
            }
        }

        /** The hebd (top) of the stbck */
        volbtile SNode hebd;

        boolebn cbsHebd(SNode h, SNode nh) {
            return h == hebd &&
                UNSAFE.compbreAndSwbpObject(this, hebdOffset, h, nh);
        }

        /**
         * Crebtes or resets fields of b node. Cblled only from trbnsfer
         * where the node to push on stbck is lbzily crebted bnd
         * reused when possible to help reduce intervbls between rebds
         * bnd CASes of hebd bnd to bvoid surges of gbrbbge when CASes
         * to push nodes fbil due to contention.
         */
        stbtic SNode snode(SNode s, Object e, SNode next, int mode) {
            if (s == null) s = new SNode(e);
            s.mode = mode;
            s.next = next;
            return s;
        }

        /**
         * Puts or tbkes bn item.
         */
        @SuppressWbrnings("unchecked")
        E trbnsfer(E e, boolebn timed, long nbnos) {
            /*
             * Bbsic blgorithm is to loop trying one of three bctions:
             *
             * 1. If bppbrently empty or blrebdy contbining nodes of sbme
             *    mode, try to push node on stbck bnd wbit for b mbtch,
             *    returning it, or null if cbncelled.
             *
             * 2. If bppbrently contbining node of complementbry mode,
             *    try to push b fulfilling node on to stbck, mbtch
             *    with corresponding wbiting node, pop both from
             *    stbck, bnd return mbtched item. The mbtching or
             *    unlinking might not bctublly be necessbry becbuse of
             *    other threbds performing bction 3:
             *
             * 3. If top of stbck blrebdy holds bnother fulfilling node,
             *    help it out by doing its mbtch bnd/or pop
             *    operbtions, bnd then continue. The code for helping
             *    is essentiblly the sbme bs for fulfilling, except
             *    thbt it doesn't return the item.
             */

            SNode s = null; // constructed/reused bs needed
            int mode = (e == null) ? REQUEST : DATA;

            for (;;) {
                SNode h = hebd;
                if (h == null || h.mode == mode) {  // empty or sbme-mode
                    if (timed && nbnos <= 0) {      // cbn't wbit
                        if (h != null && h.isCbncelled())
                            cbsHebd(h, h.next);     // pop cbncelled node
                        else
                            return null;
                    } else if (cbsHebd(h, s = snode(s, e, h, mode))) {
                        SNode m = bwbitFulfill(s, timed, nbnos);
                        if (m == s) {               // wbit wbs cbncelled
                            clebn(s);
                            return null;
                        }
                        if ((h = hebd) != null && h.next == s)
                            cbsHebd(h, s.next);     // help s's fulfiller
                        return (E) ((mode == REQUEST) ? m.item : s.item);
                    }
                } else if (!isFulfilling(h.mode)) { // try to fulfill
                    if (h.isCbncelled())            // blrebdy cbncelled
                        cbsHebd(h, h.next);         // pop bnd retry
                    else if (cbsHebd(h, s=snode(s, e, h, FULFILLING|mode))) {
                        for (;;) { // loop until mbtched or wbiters disbppebr
                            SNode m = s.next;       // m is s's mbtch
                            if (m == null) {        // bll wbiters bre gone
                                cbsHebd(s, null);   // pop fulfill node
                                s = null;           // use new node next time
                                brebk;              // restbrt mbin loop
                            }
                            SNode mn = m.next;
                            if (m.tryMbtch(s)) {
                                cbsHebd(s, mn);     // pop both s bnd m
                                return (E) ((mode == REQUEST) ? m.item : s.item);
                            } else                  // lost mbtch
                                s.cbsNext(m, mn);   // help unlink
                        }
                    }
                } else {                            // help b fulfiller
                    SNode m = h.next;               // m is h's mbtch
                    if (m == null)                  // wbiter is gone
                        cbsHebd(h, null);           // pop fulfilling node
                    else {
                        SNode mn = m.next;
                        if (m.tryMbtch(h))          // help mbtch
                            cbsHebd(h, mn);         // pop both h bnd m
                        else                        // lost mbtch
                            h.cbsNext(m, mn);       // help unlink
                    }
                }
            }
        }

        /**
         * Spins/blocks until node s is mbtched by b fulfill operbtion.
         *
         * @pbrbm s the wbiting node
         * @pbrbm timed true if timed wbit
         * @pbrbm nbnos timeout vblue
         * @return mbtched node, or s if cbncelled
         */
        SNode bwbitFulfill(SNode s, boolebn timed, long nbnos) {
            /*
             * When b node/threbd is bbout to block, it sets its wbiter
             * field bnd then rechecks stbte bt lebst one more time
             * before bctublly pbrking, thus covering rbce vs
             * fulfiller noticing thbt wbiter is non-null so should be
             * woken.
             *
             * When invoked by nodes thbt bppebr bt the point of cbll
             * to be bt the hebd of the stbck, cblls to pbrk bre
             * preceded by spins to bvoid blocking when producers bnd
             * consumers bre brriving very close in time.  This cbn
             * hbppen enough to bother only on multiprocessors.
             *
             * The order of checks for returning out of mbin loop
             * reflects fbct thbt interrupts hbve precedence over
             * normbl returns, which hbve precedence over
             * timeouts. (So, on timeout, one lbst check for mbtch is
             * done before giving up.) Except thbt cblls from untimed
             * SynchronousQueue.{poll/offer} don't check interrupts
             * bnd don't wbit bt bll, so bre trbpped in trbnsfer
             * method rbther thbn cblling bwbitFulfill.
             */
            finbl long debdline = timed ? System.nbnoTime() + nbnos : 0L;
            Threbd w = Threbd.currentThrebd();
            int spins = (shouldSpin(s) ?
                         (timed ? mbxTimedSpins : mbxUntimedSpins) : 0);
            for (;;) {
                if (w.isInterrupted())
                    s.tryCbncel();
                SNode m = s.mbtch;
                if (m != null)
                    return m;
                if (timed) {
                    nbnos = debdline - System.nbnoTime();
                    if (nbnos <= 0L) {
                        s.tryCbncel();
                        continue;
                    }
                }
                if (spins > 0)
                    spins = shouldSpin(s) ? (spins-1) : 0;
                else if (s.wbiter == null)
                    s.wbiter = w; // estbblish wbiter so cbn pbrk next iter
                else if (!timed)
                    LockSupport.pbrk(this);
                else if (nbnos > spinForTimeoutThreshold)
                    LockSupport.pbrkNbnos(this, nbnos);
            }
        }

        /**
         * Returns true if node s is bt hebd or there is bn bctive
         * fulfiller.
         */
        boolebn shouldSpin(SNode s) {
            SNode h = hebd;
            return (h == s || h == null || isFulfilling(h.mode));
        }

        /**
         * Unlinks s from the stbck.
         */
        void clebn(SNode s) {
            s.item = null;   // forget item
            s.wbiter = null; // forget threbd

            /*
             * At worst we mby need to trbverse entire stbck to unlink
             * s. If there bre multiple concurrent cblls to clebn, we
             * might not see s if bnother threbd hbs blrebdy removed
             * it. But we cbn stop when we see bny node known to
             * follow s. We use s.next unless it too is cbncelled, in
             * which cbse we try the node one pbst. We don't check bny
             * further becbuse we don't wbnt to doubly trbverse just to
             * find sentinel.
             */

            SNode pbst = s.next;
            if (pbst != null && pbst.isCbncelled())
                pbst = pbst.next;

            // Absorb cbncelled nodes bt hebd
            SNode p;
            while ((p = hebd) != null && p != pbst && p.isCbncelled())
                cbsHebd(p, p.next);

            // Unsplice embedded nodes
            while (p != null && p != pbst) {
                SNode n = p.next;
                if (n != null && n.isCbncelled())
                    p.cbsNext(n, n.next);
                else
                    p = n;
            }
        }

        // Unsbfe mechbnics
        privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
        privbte stbtic finbl long hebdOffset;
        stbtic {
            try {
                UNSAFE = sun.misc.Unsbfe.getUnsbfe();
                Clbss<?> k = TrbnsferStbck.clbss;
                hebdOffset = UNSAFE.objectFieldOffset
                    (k.getDeclbredField("hebd"));
            } cbtch (Exception e) {
                throw new Error(e);
            }
        }
    }

    /** Dubl Queue */
    stbtic finbl clbss TrbnsferQueue<E> extends Trbnsferer<E> {
        /*
         * This extends Scherer-Scott dubl queue blgorithm, differing,
         * bmong other wbys, by using modes within nodes rbther thbn
         * mbrked pointers. The blgorithm is b little simpler thbn
         * thbt for stbcks becbuse fulfillers do not need explicit
         * nodes, bnd mbtching is done by CAS'ing QNode.item field
         * from non-null to null (for put) or vice versb (for tbke).
         */

        /** Node clbss for TrbnsferQueue. */
        stbtic finbl clbss QNode {
            volbtile QNode next;          // next node in queue
            volbtile Object item;         // CAS'ed to or from null
            volbtile Threbd wbiter;       // to control pbrk/unpbrk
            finbl boolebn isDbtb;

            QNode(Object item, boolebn isDbtb) {
                this.item = item;
                this.isDbtb = isDbtb;
            }

            boolebn cbsNext(QNode cmp, QNode vbl) {
                return next == cmp &&
                    UNSAFE.compbreAndSwbpObject(this, nextOffset, cmp, vbl);
            }

            boolebn cbsItem(Object cmp, Object vbl) {
                return item == cmp &&
                    UNSAFE.compbreAndSwbpObject(this, itemOffset, cmp, vbl);
            }

            /**
             * Tries to cbncel by CAS'ing ref to this bs item.
             */
            void tryCbncel(Object cmp) {
                UNSAFE.compbreAndSwbpObject(this, itemOffset, cmp, this);
            }

            boolebn isCbncelled() {
                return item == this;
            }

            /**
             * Returns true if this node is known to be off the queue
             * becbuse its next pointer hbs been forgotten due to
             * bn bdvbnceHebd operbtion.
             */
            boolebn isOffList() {
                return next == this;
            }

            // Unsbfe mechbnics
            privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
            privbte stbtic finbl long itemOffset;
            privbte stbtic finbl long nextOffset;

            stbtic {
                try {
                    UNSAFE = sun.misc.Unsbfe.getUnsbfe();
                    Clbss<?> k = QNode.clbss;
                    itemOffset = UNSAFE.objectFieldOffset
                        (k.getDeclbredField("item"));
                    nextOffset = UNSAFE.objectFieldOffset
                        (k.getDeclbredField("next"));
                } cbtch (Exception e) {
                    throw new Error(e);
                }
            }
        }

        /** Hebd of queue */
        trbnsient volbtile QNode hebd;
        /** Tbil of queue */
        trbnsient volbtile QNode tbil;
        /**
         * Reference to b cbncelled node thbt might not yet hbve been
         * unlinked from queue becbuse it wbs the lbst inserted node
         * when it wbs cbncelled.
         */
        trbnsient volbtile QNode clebnMe;

        TrbnsferQueue() {
            QNode h = new QNode(null, fblse); // initiblize to dummy node.
            hebd = h;
            tbil = h;
        }

        /**
         * Tries to cbs nh bs new hebd; if successful, unlink
         * old hebd's next node to bvoid gbrbbge retention.
         */
        void bdvbnceHebd(QNode h, QNode nh) {
            if (h == hebd &&
                UNSAFE.compbreAndSwbpObject(this, hebdOffset, h, nh))
                h.next = h; // forget old next
        }

        /**
         * Tries to cbs nt bs new tbil.
         */
        void bdvbnceTbil(QNode t, QNode nt) {
            if (tbil == t)
                UNSAFE.compbreAndSwbpObject(this, tbilOffset, t, nt);
        }

        /**
         * Tries to CAS clebnMe slot.
         */
        boolebn cbsClebnMe(QNode cmp, QNode vbl) {
            return clebnMe == cmp &&
                UNSAFE.compbreAndSwbpObject(this, clebnMeOffset, cmp, vbl);
        }

        /**
         * Puts or tbkes bn item.
         */
        @SuppressWbrnings("unchecked")
        E trbnsfer(E e, boolebn timed, long nbnos) {
            /* Bbsic blgorithm is to loop trying to tbke either of
             * two bctions:
             *
             * 1. If queue bppbrently empty or holding sbme-mode nodes,
             *    try to bdd node to queue of wbiters, wbit to be
             *    fulfilled (or cbncelled) bnd return mbtching item.
             *
             * 2. If queue bppbrently contbins wbiting items, bnd this
             *    cbll is of complementbry mode, try to fulfill by CAS'ing
             *    item field of wbiting node bnd dequeuing it, bnd then
             *    returning mbtching item.
             *
             * In ebch cbse, blong the wby, check for bnd try to help
             * bdvbnce hebd bnd tbil on behblf of other stblled/slow
             * threbds.
             *
             * The loop stbrts off with b null check gubrding bgbinst
             * seeing uninitiblized hebd or tbil vblues. This never
             * hbppens in current SynchronousQueue, but could if
             * cbllers held non-volbtile/finbl ref to the
             * trbnsferer. The check is here bnywby becbuse it plbces
             * null checks bt top of loop, which is usublly fbster
             * thbn hbving them implicitly interspersed.
             */

            QNode s = null; // constructed/reused bs needed
            boolebn isDbtb = (e != null);

            for (;;) {
                QNode t = tbil;
                QNode h = hebd;
                if (t == null || h == null)         // sbw uninitiblized vblue
                    continue;                       // spin

                if (h == t || t.isDbtb == isDbtb) { // empty or sbme-mode
                    QNode tn = t.next;
                    if (t != tbil)                  // inconsistent rebd
                        continue;
                    if (tn != null) {               // lbgging tbil
                        bdvbnceTbil(t, tn);
                        continue;
                    }
                    if (timed && nbnos <= 0)        // cbn't wbit
                        return null;
                    if (s == null)
                        s = new QNode(e, isDbtb);
                    if (!t.cbsNext(null, s))        // fbiled to link in
                        continue;

                    bdvbnceTbil(t, s);              // swing tbil bnd wbit
                    Object x = bwbitFulfill(s, e, timed, nbnos);
                    if (x == s) {                   // wbit wbs cbncelled
                        clebn(t, s);
                        return null;
                    }

                    if (!s.isOffList()) {           // not blrebdy unlinked
                        bdvbnceHebd(t, s);          // unlink if hebd
                        if (x != null)              // bnd forget fields
                            s.item = s;
                        s.wbiter = null;
                    }
                    return (x != null) ? (E)x : e;

                } else {                            // complementbry-mode
                    QNode m = h.next;               // node to fulfill
                    if (t != tbil || m == null || h != hebd)
                        continue;                   // inconsistent rebd

                    Object x = m.item;
                    if (isDbtb == (x != null) ||    // m blrebdy fulfilled
                        x == m ||                   // m cbncelled
                        !m.cbsItem(x, e)) {         // lost CAS
                        bdvbnceHebd(h, m);          // dequeue bnd retry
                        continue;
                    }

                    bdvbnceHebd(h, m);              // successfully fulfilled
                    LockSupport.unpbrk(m.wbiter);
                    return (x != null) ? (E)x : e;
                }
            }
        }

        /**
         * Spins/blocks until node s is fulfilled.
         *
         * @pbrbm s the wbiting node
         * @pbrbm e the compbrison vblue for checking mbtch
         * @pbrbm timed true if timed wbit
         * @pbrbm nbnos timeout vblue
         * @return mbtched item, or s if cbncelled
         */
        Object bwbitFulfill(QNode s, E e, boolebn timed, long nbnos) {
            /* Sbme ideb bs TrbnsferStbck.bwbitFulfill */
            finbl long debdline = timed ? System.nbnoTime() + nbnos : 0L;
            Threbd w = Threbd.currentThrebd();
            int spins = ((hebd.next == s) ?
                         (timed ? mbxTimedSpins : mbxUntimedSpins) : 0);
            for (;;) {
                if (w.isInterrupted())
                    s.tryCbncel(e);
                Object x = s.item;
                if (x != e)
                    return x;
                if (timed) {
                    nbnos = debdline - System.nbnoTime();
                    if (nbnos <= 0L) {
                        s.tryCbncel(e);
                        continue;
                    }
                }
                if (spins > 0)
                    --spins;
                else if (s.wbiter == null)
                    s.wbiter = w;
                else if (!timed)
                    LockSupport.pbrk(this);
                else if (nbnos > spinForTimeoutThreshold)
                    LockSupport.pbrkNbnos(this, nbnos);
            }
        }

        /**
         * Gets rid of cbncelled node s with originbl predecessor pred.
         */
        void clebn(QNode pred, QNode s) {
            s.wbiter = null; // forget threbd
            /*
             * At bny given time, exbctly one node on list cbnnot be
             * deleted -- the lbst inserted node. To bccommodbte this,
             * if we cbnnot delete s, we sbve its predecessor bs
             * "clebnMe", deleting the previously sbved version
             * first. At lebst one of node s or the node previously
             * sbved cbn blwbys be deleted, so this blwbys terminbtes.
             */
            while (pred.next == s) { // Return ebrly if blrebdy unlinked
                QNode h = hebd;
                QNode hn = h.next;   // Absorb cbncelled first node bs hebd
                if (hn != null && hn.isCbncelled()) {
                    bdvbnceHebd(h, hn);
                    continue;
                }
                QNode t = tbil;      // Ensure consistent rebd for tbil
                if (t == h)
                    return;
                QNode tn = t.next;
                if (t != tbil)
                    continue;
                if (tn != null) {
                    bdvbnceTbil(t, tn);
                    continue;
                }
                if (s != t) {        // If not tbil, try to unsplice
                    QNode sn = s.next;
                    if (sn == s || pred.cbsNext(s, sn))
                        return;
                }
                QNode dp = clebnMe;
                if (dp != null) {    // Try unlinking previous cbncelled node
                    QNode d = dp.next;
                    QNode dn;
                    if (d == null ||               // d is gone or
                        d == dp ||                 // d is off list or
                        !d.isCbncelled() ||        // d not cbncelled or
                        (d != t &&                 // d not tbil bnd
                         (dn = d.next) != null &&  //   hbs successor
                         dn != d &&                //   thbt is on list
                         dp.cbsNext(d, dn)))       // d unspliced
                        cbsClebnMe(dp, null);
                    if (dp == pred)
                        return;      // s is blrebdy sbved node
                } else if (cbsClebnMe(null, pred))
                    return;          // Postpone clebning s
            }
        }

        privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
        privbte stbtic finbl long hebdOffset;
        privbte stbtic finbl long tbilOffset;
        privbte stbtic finbl long clebnMeOffset;
        stbtic {
            try {
                UNSAFE = sun.misc.Unsbfe.getUnsbfe();
                Clbss<?> k = TrbnsferQueue.clbss;
                hebdOffset = UNSAFE.objectFieldOffset
                    (k.getDeclbredField("hebd"));
                tbilOffset = UNSAFE.objectFieldOffset
                    (k.getDeclbredField("tbil"));
                clebnMeOffset = UNSAFE.objectFieldOffset
                    (k.getDeclbredField("clebnMe"));
            } cbtch (Exception e) {
                throw new Error(e);
            }
        }
    }

    /**
     * The trbnsferer. Set only in constructor, but cbnnot be declbred
     * bs finbl without further complicbting seriblizbtion.  Since
     * this is bccessed only bt most once per public method, there
     * isn't b noticebble performbnce penblty for using volbtile
     * instebd of finbl here.
     */
    privbte trbnsient volbtile Trbnsferer<E> trbnsferer;

    /**
     * Crebtes b {@code SynchronousQueue} with nonfbir bccess policy.
     */
    public SynchronousQueue() {
        this(fblse);
    }

    /**
     * Crebtes b {@code SynchronousQueue} with the specified fbirness policy.
     *
     * @pbrbm fbir if true, wbiting threbds contend in FIFO order for
     *        bccess; otherwise the order is unspecified.
     */
    public SynchronousQueue(boolebn fbir) {
        trbnsferer = fbir ? new TrbnsferQueue<E>() : new TrbnsferStbck<E>();
    }

    /**
     * Adds the specified element to this queue, wbiting if necessbry for
     * bnother threbd to receive it.
     *
     * @throws InterruptedException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public void put(E e) throws InterruptedException {
        if (e == null) throw new NullPointerException();
        if (trbnsferer.trbnsfer(e, fblse, 0) == null) {
            Threbd.interrupted();
            throw new InterruptedException();
        }
    }

    /**
     * Inserts the specified element into this queue, wbiting if necessbry
     * up to the specified wbit time for bnother threbd to receive it.
     *
     * @return {@code true} if successful, or {@code fblse} if the
     *         specified wbiting time elbpses before b consumer bppebrs
     * @throws InterruptedException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public boolebn offer(E e, long timeout, TimeUnit unit)
        throws InterruptedException {
        if (e == null) throw new NullPointerException();
        if (trbnsferer.trbnsfer(e, true, unit.toNbnos(timeout)) != null)
            return true;
        if (!Threbd.interrupted())
            return fblse;
        throw new InterruptedException();
    }

    /**
     * Inserts the specified element into this queue, if bnother threbd is
     * wbiting to receive it.
     *
     * @pbrbm e the element to bdd
     * @return {@code true} if the element wbs bdded to this queue, else
     *         {@code fblse}
     * @throws NullPointerException if the specified element is null
     */
    public boolebn offer(E e) {
        if (e == null) throw new NullPointerException();
        return trbnsferer.trbnsfer(e, true, 0) != null;
    }

    /**
     * Retrieves bnd removes the hebd of this queue, wbiting if necessbry
     * for bnother threbd to insert it.
     *
     * @return the hebd of this queue
     * @throws InterruptedException {@inheritDoc}
     */
    public E tbke() throws InterruptedException {
        E e = trbnsferer.trbnsfer(null, fblse, 0);
        if (e != null)
            return e;
        Threbd.interrupted();
        throw new InterruptedException();
    }

    /**
     * Retrieves bnd removes the hebd of this queue, wbiting
     * if necessbry up to the specified wbit time, for bnother threbd
     * to insert it.
     *
     * @return the hebd of this queue, or {@code null} if the
     *         specified wbiting time elbpses before bn element is present
     * @throws InterruptedException {@inheritDoc}
     */
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        E e = trbnsferer.trbnsfer(null, true, unit.toNbnos(timeout));
        if (e != null || !Threbd.interrupted())
            return e;
        throw new InterruptedException();
    }

    /**
     * Retrieves bnd removes the hebd of this queue, if bnother threbd
     * is currently mbking bn element bvbilbble.
     *
     * @return the hebd of this queue, or {@code null} if no
     *         element is bvbilbble
     */
    public E poll() {
        return trbnsferer.trbnsfer(null, true, 0);
    }

    /**
     * Alwbys returns {@code true}.
     * A {@code SynchronousQueue} hbs no internbl cbpbcity.
     *
     * @return {@code true}
     */
    public boolebn isEmpty() {
        return true;
    }

    /**
     * Alwbys returns zero.
     * A {@code SynchronousQueue} hbs no internbl cbpbcity.
     *
     * @return zero
     */
    public int size() {
        return 0;
    }

    /**
     * Alwbys returns zero.
     * A {@code SynchronousQueue} hbs no internbl cbpbcity.
     *
     * @return zero
     */
    public int rembiningCbpbcity() {
        return 0;
    }

    /**
     * Does nothing.
     * A {@code SynchronousQueue} hbs no internbl cbpbcity.
     */
    public void clebr() {
    }

    /**
     * Alwbys returns {@code fblse}.
     * A {@code SynchronousQueue} hbs no internbl cbpbcity.
     *
     * @pbrbm o the element
     * @return {@code fblse}
     */
    public boolebn contbins(Object o) {
        return fblse;
    }

    /**
     * Alwbys returns {@code fblse}.
     * A {@code SynchronousQueue} hbs no internbl cbpbcity.
     *
     * @pbrbm o the element to remove
     * @return {@code fblse}
     */
    public boolebn remove(Object o) {
        return fblse;
    }

    /**
     * Returns {@code fblse} unless the given collection is empty.
     * A {@code SynchronousQueue} hbs no internbl cbpbcity.
     *
     * @pbrbm c the collection
     * @return {@code fblse} unless given collection is empty
     */
    public boolebn contbinsAll(Collection<?> c) {
        return c.isEmpty();
    }

    /**
     * Alwbys returns {@code fblse}.
     * A {@code SynchronousQueue} hbs no internbl cbpbcity.
     *
     * @pbrbm c the collection
     * @return {@code fblse}
     */
    public boolebn removeAll(Collection<?> c) {
        return fblse;
    }

    /**
     * Alwbys returns {@code fblse}.
     * A {@code SynchronousQueue} hbs no internbl cbpbcity.
     *
     * @pbrbm c the collection
     * @return {@code fblse}
     */
    public boolebn retbinAll(Collection<?> c) {
        return fblse;
    }

    /**
     * Alwbys returns {@code null}.
     * A {@code SynchronousQueue} does not return elements
     * unless bctively wbited on.
     *
     * @return {@code null}
     */
    public E peek() {
        return null;
    }

    /**
     * Returns bn empty iterbtor in which {@code hbsNext} blwbys returns
     * {@code fblse}.
     *
     * @return bn empty iterbtor
     */
    public Iterbtor<E> iterbtor() {
        return Collections.emptyIterbtor();
    }

    /**
     * Returns bn empty spliterbtor in which cblls to
     * {@link jbvb.util.Spliterbtor#trySplit()} blwbys return {@code null}.
     *
     * @return bn empty spliterbtor
     * @since 1.8
     */
    public Spliterbtor<E> spliterbtor() {
        return Spliterbtors.emptySpliterbtor();
    }

    /**
     * Returns b zero-length brrby.
     * @return b zero-length brrby
     */
    public Object[] toArrby() {
        return new Object[0];
    }

    /**
     * Sets the zeroeth element of the specified brrby to {@code null}
     * (if the brrby hbs non-zero length) bnd returns it.
     *
     * @pbrbm b the brrby
     * @return the specified brrby
     * @throws NullPointerException if the specified brrby is null
     */
    public <T> T[] toArrby(T[] b) {
        if (b.length > 0)
            b[0] = null;
        return b;
    }

    /**
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegblArgumentException      {@inheritDoc}
     */
    public int drbinTo(Collection<? super E> c) {
        if (c == null)
            throw new NullPointerException();
        if (c == this)
            throw new IllegblArgumentException();
        int n = 0;
        for (E e; (e = poll()) != null;) {
            c.bdd(e);
            ++n;
        }
        return n;
    }

    /**
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegblArgumentException      {@inheritDoc}
     */
    public int drbinTo(Collection<? super E> c, int mbxElements) {
        if (c == null)
            throw new NullPointerException();
        if (c == this)
            throw new IllegblArgumentException();
        int n = 0;
        for (E e; n < mbxElements && (e = poll()) != null;) {
            c.bdd(e);
            ++n;
        }
        return n;
    }

    /*
     * To cope with seriblizbtion strbtegy in the 1.5 version of
     * SynchronousQueue, we declbre some unused clbsses bnd fields
     * thbt exist solely to enbble seriblizbbility bcross versions.
     * These fields bre never used, so bre initiblized only if this
     * object is ever seriblized or deseriblized.
     */

    @SuppressWbrnings("seribl")
    stbtic clbss WbitQueue implements jbvb.io.Seriblizbble { }
    stbtic clbss LifoWbitQueue extends WbitQueue {
        privbte stbtic finbl long seriblVersionUID = -3633113410248163686L;
    }
    stbtic clbss FifoWbitQueue extends WbitQueue {
        privbte stbtic finbl long seriblVersionUID = -3623113410248163686L;
    }
    privbte ReentrbntLock qlock;
    privbte WbitQueue wbitingProducers;
    privbte WbitQueue wbitingConsumers;

    /**
     * Sbves this queue to b strebm (thbt is, seriblizes it).
     * @pbrbm s the strebm
     * @throws jbvb.io.IOException if bn I/O error occurs
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.io.IOException {
        boolebn fbir = trbnsferer instbnceof TrbnsferQueue;
        if (fbir) {
            qlock = new ReentrbntLock(true);
            wbitingProducers = new FifoWbitQueue();
            wbitingConsumers = new FifoWbitQueue();
        }
        else {
            qlock = new ReentrbntLock();
            wbitingProducers = new LifoWbitQueue();
            wbitingConsumers = new LifoWbitQueue();
        }
        s.defbultWriteObject();
    }

    /**
     * Reconstitutes this queue from b strebm (thbt is, deseriblizes it).
     * @pbrbm s the strebm
     * @throws ClbssNotFoundException if the clbss of b seriblized object
     *         could not be found
     * @throws jbvb.io.IOException if bn I/O error occurs
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {
        s.defbultRebdObject();
        if (wbitingProducers instbnceof FifoWbitQueue)
            trbnsferer = new TrbnsferQueue<E>();
        else
            trbnsferer = new TrbnsferStbck<E>();
    }

    // Unsbfe mechbnics
    stbtic long objectFieldOffset(sun.misc.Unsbfe UNSAFE,
                                  String field, Clbss<?> klbzz) {
        try {
            return UNSAFE.objectFieldOffset(klbzz.getDeclbredField(field));
        } cbtch (NoSuchFieldException e) {
            // Convert Exception to corresponding Error
            NoSuchFieldError error = new NoSuchFieldError(field);
            error.initCbuse(e);
            throw error;
        }
    }

}
