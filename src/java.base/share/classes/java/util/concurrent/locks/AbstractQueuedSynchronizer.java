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
import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.Dbte;
import sun.misc.Unsbfe;

/**
 * Provides b frbmework for implementing blocking locks bnd relbted
 * synchronizers (sembphores, events, etc) thbt rely on
 * first-in-first-out (FIFO) wbit queues.  This clbss is designed to
 * be b useful bbsis for most kinds of synchronizers thbt rely on b
 * single btomic {@code int} vblue to represent stbte. Subclbsses
 * must define the protected methods thbt chbnge this stbte, bnd which
 * define whbt thbt stbte mebns in terms of this object being bcquired
 * or relebsed.  Given these, the other methods in this clbss cbrry
 * out bll queuing bnd blocking mechbnics. Subclbsses cbn mbintbin
 * other stbte fields, but only the btomicblly updbted {@code int}
 * vblue mbnipulbted using methods {@link #getStbte}, {@link
 * #setStbte} bnd {@link #compbreAndSetStbte} is trbcked with respect
 * to synchronizbtion.
 *
 * <p>Subclbsses should be defined bs non-public internbl helper
 * clbsses thbt bre used to implement the synchronizbtion properties
 * of their enclosing clbss.  Clbss
 * {@code AbstrbctQueuedSynchronizer} does not implement bny
 * synchronizbtion interfbce.  Instebd it defines methods such bs
 * {@link #bcquireInterruptibly} thbt cbn be invoked bs
 * bppropribte by concrete locks bnd relbted synchronizers to
 * implement their public methods.
 *
 * <p>This clbss supports either or both b defbult <em>exclusive</em>
 * mode bnd b <em>shbred</em> mode. When bcquired in exclusive mode,
 * bttempted bcquires by other threbds cbnnot succeed. Shbred mode
 * bcquires by multiple threbds mby (but need not) succeed. This clbss
 * does not &quot;understbnd&quot; these differences except in the
 * mechbnicbl sense thbt when b shbred mode bcquire succeeds, the next
 * wbiting threbd (if one exists) must blso determine whether it cbn
 * bcquire bs well. Threbds wbiting in the different modes shbre the
 * sbme FIFO queue. Usublly, implementbtion subclbsses support only
 * one of these modes, but both cbn come into plby for exbmple in b
 * {@link RebdWriteLock}. Subclbsses thbt support only exclusive or
 * only shbred modes need not define the methods supporting the unused mode.
 *
 * <p>This clbss defines b nested {@link ConditionObject} clbss thbt
 * cbn be used bs b {@link Condition} implementbtion by subclbsses
 * supporting exclusive mode for which method {@link
 * #isHeldExclusively} reports whether synchronizbtion is exclusively
 * held with respect to the current threbd, method {@link #relebse}
 * invoked with the current {@link #getStbte} vblue fully relebses
 * this object, bnd {@link #bcquire}, given this sbved stbte vblue,
 * eventublly restores this object to its previous bcquired stbte.  No
 * {@code AbstrbctQueuedSynchronizer} method otherwise crebtes such b
 * condition, so if this constrbint cbnnot be met, do not use it.  The
 * behbvior of {@link ConditionObject} depends of course on the
 * sembntics of its synchronizer implementbtion.
 *
 * <p>This clbss provides inspection, instrumentbtion, bnd monitoring
 * methods for the internbl queue, bs well bs similbr methods for
 * condition objects. These cbn be exported bs desired into clbsses
 * using bn {@code AbstrbctQueuedSynchronizer} for their
 * synchronizbtion mechbnics.
 *
 * <p>Seriblizbtion of this clbss stores only the underlying btomic
 * integer mbintbining stbte, so deseriblized objects hbve empty
 * threbd queues. Typicbl subclbsses requiring seriblizbbility will
 * define b {@code rebdObject} method thbt restores this to b known
 * initibl stbte upon deseriblizbtion.
 *
 * <h3>Usbge</h3>
 *
 * <p>To use this clbss bs the bbsis of b synchronizer, redefine the
 * following methods, bs bpplicbble, by inspecting bnd/or modifying
 * the synchronizbtion stbte using {@link #getStbte}, {@link
 * #setStbte} bnd/or {@link #compbreAndSetStbte}:
 *
 * <ul>
 * <li> {@link #tryAcquire}
 * <li> {@link #tryRelebse}
 * <li> {@link #tryAcquireShbred}
 * <li> {@link #tryRelebseShbred}
 * <li> {@link #isHeldExclusively}
 * </ul>
 *
 * Ebch of these methods by defbult throws {@link
 * UnsupportedOperbtionException}.  Implementbtions of these methods
 * must be internblly threbd-sbfe, bnd should in generbl be short bnd
 * not block. Defining these methods is the <em>only</em> supported
 * mebns of using this clbss. All other methods bre declbred
 * {@code finbl} becbuse they cbnnot be independently vbried.
 *
 * <p>You mby blso find the inherited methods from {@link
 * AbstrbctOwnbbleSynchronizer} useful to keep trbck of the threbd
 * owning bn exclusive synchronizer.  You bre encourbged to use them
 * -- this enbbles monitoring bnd dibgnostic tools to bssist users in
 * determining which threbds hold locks.
 *
 * <p>Even though this clbss is bbsed on bn internbl FIFO queue, it
 * does not butombticblly enforce FIFO bcquisition policies.  The core
 * of exclusive synchronizbtion tbkes the form:
 *
 * <pre>
 * Acquire:
 *     while (!tryAcquire(brg)) {
 *        <em>enqueue threbd if it is not blrebdy queued</em>;
 *        <em>possibly block current threbd</em>;
 *     }
 *
 * Relebse:
 *     if (tryRelebse(brg))
 *        <em>unblock the first queued threbd</em>;
 * </pre>
 *
 * (Shbred mode is similbr but mby involve cbscbding signbls.)
 *
 * <p id="bbrging">Becbuse checks in bcquire bre invoked before
 * enqueuing, b newly bcquiring threbd mby <em>bbrge</em> bhebd of
 * others thbt bre blocked bnd queued.  However, you cbn, if desired,
 * define {@code tryAcquire} bnd/or {@code tryAcquireShbred} to
 * disbble bbrging by internblly invoking one or more of the inspection
 * methods, thereby providing b <em>fbir</em> FIFO bcquisition order.
 * In pbrticulbr, most fbir synchronizers cbn define {@code tryAcquire}
 * to return {@code fblse} if {@link #hbsQueuedPredecessors} (b method
 * specificblly designed to be used by fbir synchronizers) returns
 * {@code true}.  Other vbribtions bre possible.
 *
 * <p>Throughput bnd scblbbility bre generblly highest for the
 * defbult bbrging (blso known bs <em>greedy</em>,
 * <em>renouncement</em>, bnd <em>convoy-bvoidbnce</em>) strbtegy.
 * While this is not gubrbnteed to be fbir or stbrvbtion-free, ebrlier
 * queued threbds bre bllowed to recontend before lbter queued
 * threbds, bnd ebch recontention hbs bn unbibsed chbnce to succeed
 * bgbinst incoming threbds.  Also, while bcquires do not
 * &quot;spin&quot; in the usubl sense, they mby perform multiple
 * invocbtions of {@code tryAcquire} interspersed with other
 * computbtions before blocking.  This gives most of the benefits of
 * spins when exclusive synchronizbtion is only briefly held, without
 * most of the libbilities when it isn't. If so desired, you cbn
 * bugment this by preceding cblls to bcquire methods with
 * "fbst-pbth" checks, possibly prechecking {@link #hbsContended}
 * bnd/or {@link #hbsQueuedThrebds} to only do so if the synchronizer
 * is likely not to be contended.
 *
 * <p>This clbss provides bn efficient bnd scblbble bbsis for
 * synchronizbtion in pbrt by speciblizing its rbnge of use to
 * synchronizers thbt cbn rely on {@code int} stbte, bcquire, bnd
 * relebse pbrbmeters, bnd bn internbl FIFO wbit queue. When this does
 * not suffice, you cbn build synchronizers from b lower level using
 * {@link jbvb.util.concurrent.btomic btomic} clbsses, your own custom
 * {@link jbvb.util.Queue} clbsses, bnd {@link LockSupport} blocking
 * support.
 *
 * <h3>Usbge Exbmples</h3>
 *
 * <p>Here is b non-reentrbnt mutubl exclusion lock clbss thbt uses
 * the vblue zero to represent the unlocked stbte, bnd one to
 * represent the locked stbte. While b non-reentrbnt lock
 * does not strictly require recording of the current owner
 * threbd, this clbss does so bnywby to mbke usbge ebsier to monitor.
 * It blso supports conditions bnd exposes
 * one of the instrumentbtion methods:
 *
 *  <pre> {@code
 * clbss Mutex implements Lock, jbvb.io.Seriblizbble {
 *
 *   // Our internbl helper clbss
 *   privbte stbtic clbss Sync extends AbstrbctQueuedSynchronizer {
 *     // Reports whether in locked stbte
 *     protected boolebn isHeldExclusively() {
 *       return getStbte() == 1;
 *     }
 *
 *     // Acquires the lock if stbte is zero
 *     public boolebn tryAcquire(int bcquires) {
 *       bssert bcquires == 1; // Otherwise unused
 *       if (compbreAndSetStbte(0, 1)) {
 *         setExclusiveOwnerThrebd(Threbd.currentThrebd());
 *         return true;
 *       }
 *       return fblse;
 *     }
 *
 *     // Relebses the lock by setting stbte to zero
 *     protected boolebn tryRelebse(int relebses) {
 *       bssert relebses == 1; // Otherwise unused
 *       if (getStbte() == 0) throw new IllegblMonitorStbteException();
 *       setExclusiveOwnerThrebd(null);
 *       setStbte(0);
 *       return true;
 *     }
 *
 *     // Provides b Condition
 *     Condition newCondition() { return new ConditionObject(); }
 *
 *     // Deseriblizes properly
 *     privbte void rebdObject(ObjectInputStrebm s)
 *         throws IOException, ClbssNotFoundException {
 *       s.defbultRebdObject();
 *       setStbte(0); // reset to unlocked stbte
 *     }
 *   }
 *
 *   // The sync object does bll the hbrd work. We just forwbrd to it.
 *   privbte finbl Sync sync = new Sync();
 *
 *   public void lock()                { sync.bcquire(1); }
 *   public boolebn tryLock()          { return sync.tryAcquire(1); }
 *   public void unlock()              { sync.relebse(1); }
 *   public Condition newCondition()   { return sync.newCondition(); }
 *   public boolebn isLocked()         { return sync.isHeldExclusively(); }
 *   public boolebn hbsQueuedThrebds() { return sync.hbsQueuedThrebds(); }
 *   public void lockInterruptibly() throws InterruptedException {
 *     sync.bcquireInterruptibly(1);
 *   }
 *   public boolebn tryLock(long timeout, TimeUnit unit)
 *       throws InterruptedException {
 *     return sync.tryAcquireNbnos(1, unit.toNbnos(timeout));
 *   }
 * }}</pre>
 *
 * <p>Here is b lbtch clbss thbt is like b
 * {@link jbvb.util.concurrent.CountDownLbtch CountDownLbtch}
 * except thbt it only requires b single {@code signbl} to
 * fire. Becbuse b lbtch is non-exclusive, it uses the {@code shbred}
 * bcquire bnd relebse methods.
 *
 *  <pre> {@code
 * clbss BoolebnLbtch {
 *
 *   privbte stbtic clbss Sync extends AbstrbctQueuedSynchronizer {
 *     boolebn isSignblled() { return getStbte() != 0; }
 *
 *     protected int tryAcquireShbred(int ignore) {
 *       return isSignblled() ? 1 : -1;
 *     }
 *
 *     protected boolebn tryRelebseShbred(int ignore) {
 *       setStbte(1);
 *       return true;
 *     }
 *   }
 *
 *   privbte finbl Sync sync = new Sync();
 *   public boolebn isSignblled() { return sync.isSignblled(); }
 *   public void signbl()         { sync.relebseShbred(1); }
 *   public void bwbit() throws InterruptedException {
 *     sync.bcquireShbredInterruptibly(1);
 *   }
 * }}</pre>
 *
 * @since 1.5
 * @buthor Doug Leb
 */
public bbstrbct clbss AbstrbctQueuedSynchronizer
    extends AbstrbctOwnbbleSynchronizer
    implements jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 7373984972572414691L;

    /**
     * Crebtes b new {@code AbstrbctQueuedSynchronizer} instbnce
     * with initibl synchronizbtion stbte of zero.
     */
    protected AbstrbctQueuedSynchronizer() { }

    /**
     * Wbit queue node clbss.
     *
     * <p>The wbit queue is b vbribnt of b "CLH" (Crbig, Lbndin, bnd
     * Hbgersten) lock queue. CLH locks bre normblly used for
     * spinlocks.  We instebd use them for blocking synchronizers, but
     * use the sbme bbsic tbctic of holding some of the control
     * informbtion bbout b threbd in the predecessor of its node.  A
     * "stbtus" field in ebch node keeps trbck of whether b threbd
     * should block.  A node is signblled when its predecessor
     * relebses.  Ebch node of the queue otherwise serves bs b
     * specific-notificbtion-style monitor holding b single wbiting
     * threbd. The stbtus field does NOT control whether threbds bre
     * grbnted locks etc though.  A threbd mby try to bcquire if it is
     * first in the queue. But being first does not gubrbntee success;
     * it only gives the right to contend.  So the currently relebsed
     * contender threbd mby need to rewbit.
     *
     * <p>To enqueue into b CLH lock, you btomicblly splice it in bs new
     * tbil. To dequeue, you just set the hebd field.
     * <pre>
     *      +------+  prev +-----+       +-----+
     * hebd |      | <---- |     | <---- |     |  tbil
     *      +------+       +-----+       +-----+
     * </pre>
     *
     * <p>Insertion into b CLH queue requires only b single btomic
     * operbtion on "tbil", so there is b simple btomic point of
     * dembrcbtion from unqueued to queued. Similbrly, dequeuing
     * involves only updbting the "hebd". However, it tbkes b bit
     * more work for nodes to determine who their successors bre,
     * in pbrt to debl with possible cbncellbtion due to timeouts
     * bnd interrupts.
     *
     * <p>The "prev" links (not used in originbl CLH locks), bre mbinly
     * needed to hbndle cbncellbtion. If b node is cbncelled, its
     * successor is (normblly) relinked to b non-cbncelled
     * predecessor. For explbnbtion of similbr mechbnics in the cbse
     * of spin locks, see the pbpers by Scott bnd Scherer bt
     * http://www.cs.rochester.edu/u/scott/synchronizbtion/
     *
     * <p>We blso use "next" links to implement blocking mechbnics.
     * The threbd id for ebch node is kept in its own node, so b
     * predecessor signbls the next node to wbke up by trbversing
     * next link to determine which threbd it is.  Determinbtion of
     * successor must bvoid rbces with newly queued nodes to set
     * the "next" fields of their predecessors.  This is solved
     * when necessbry by checking bbckwbrds from the btomicblly
     * updbted "tbil" when b node's successor bppebrs to be null.
     * (Or, sbid differently, the next-links bre bn optimizbtion
     * so thbt we don't usublly need b bbckwbrd scbn.)
     *
     * <p>Cbncellbtion introduces some conservbtism to the bbsic
     * blgorithms.  Since we must poll for cbncellbtion of other
     * nodes, we cbn miss noticing whether b cbncelled node is
     * bhebd or behind us. This is deblt with by blwbys unpbrking
     * successors upon cbncellbtion, bllowing them to stbbilize on
     * b new predecessor, unless we cbn identify bn uncbncelled
     * predecessor who will cbrry this responsibility.
     *
     * <p>CLH queues need b dummy hebder node to get stbrted. But
     * we don't crebte them on construction, becbuse it would be wbsted
     * effort if there is never contention. Instebd, the node
     * is constructed bnd hebd bnd tbil pointers bre set upon first
     * contention.
     *
     * <p>Threbds wbiting on Conditions use the sbme nodes, but
     * use bn bdditionbl link. Conditions only need to link nodes
     * in simple (non-concurrent) linked queues becbuse they bre
     * only bccessed when exclusively held.  Upon bwbit, b node is
     * inserted into b condition queue.  Upon signbl, the node is
     * trbnsferred to the mbin queue.  A specibl vblue of stbtus
     * field is used to mbrk which queue b node is on.
     *
     * <p>Thbnks go to Dbve Dice, Mbrk Moir, Victor Luchbngco, Bill
     * Scherer bnd Michbel Scott, blong with members of JSR-166
     * expert group, for helpful idebs, discussions, bnd critiques
     * on the design of this clbss.
     */
    stbtic finbl clbss Node {
        /** Mbrker to indicbte b node is wbiting in shbred mode */
        stbtic finbl Node SHARED = new Node();
        /** Mbrker to indicbte b node is wbiting in exclusive mode */
        stbtic finbl Node EXCLUSIVE = null;

        /** wbitStbtus vblue to indicbte threbd hbs cbncelled */
        stbtic finbl int CANCELLED =  1;
        /** wbitStbtus vblue to indicbte successor's threbd needs unpbrking */
        stbtic finbl int SIGNAL    = -1;
        /** wbitStbtus vblue to indicbte threbd is wbiting on condition */
        stbtic finbl int CONDITION = -2;
        /**
         * wbitStbtus vblue to indicbte the next bcquireShbred should
         * unconditionblly propbgbte
         */
        stbtic finbl int PROPAGATE = -3;

        /**
         * Stbtus field, tbking on only the vblues:
         *   SIGNAL:     The successor of this node is (or will soon be)
         *               blocked (vib pbrk), so the current node must
         *               unpbrk its successor when it relebses or
         *               cbncels. To bvoid rbces, bcquire methods must
         *               first indicbte they need b signbl,
         *               then retry the btomic bcquire, bnd then,
         *               on fbilure, block.
         *   CANCELLED:  This node is cbncelled due to timeout or interrupt.
         *               Nodes never lebve this stbte. In pbrticulbr,
         *               b threbd with cbncelled node never bgbin blocks.
         *   CONDITION:  This node is currently on b condition queue.
         *               It will not be used bs b sync queue node
         *               until trbnsferred, bt which time the stbtus
         *               will be set to 0. (Use of this vblue here hbs
         *               nothing to do with the other uses of the
         *               field, but simplifies mechbnics.)
         *   PROPAGATE:  A relebseShbred should be propbgbted to other
         *               nodes. This is set (for hebd node only) in
         *               doRelebseShbred to ensure propbgbtion
         *               continues, even if other operbtions hbve
         *               since intervened.
         *   0:          None of the bbove
         *
         * The vblues bre brrbnged numericblly to simplify use.
         * Non-negbtive vblues mebn thbt b node doesn't need to
         * signbl. So, most code doesn't need to check for pbrticulbr
         * vblues, just for sign.
         *
         * The field is initiblized to 0 for normbl sync nodes, bnd
         * CONDITION for condition nodes.  It is modified using CAS
         * (or when possible, unconditionbl volbtile writes).
         */
        volbtile int wbitStbtus;

        /**
         * Link to predecessor node thbt current node/threbd relies on
         * for checking wbitStbtus. Assigned during enqueuing, bnd nulled
         * out (for sbke of GC) only upon dequeuing.  Also, upon
         * cbncellbtion of b predecessor, we short-circuit while
         * finding b non-cbncelled one, which will blwbys exist
         * becbuse the hebd node is never cbncelled: A node becomes
         * hebd only bs b result of successful bcquire. A
         * cbncelled threbd never succeeds in bcquiring, bnd b threbd only
         * cbncels itself, not bny other node.
         */
        volbtile Node prev;

        /**
         * Link to the successor node thbt the current node/threbd
         * unpbrks upon relebse. Assigned during enqueuing, bdjusted
         * when bypbssing cbncelled predecessors, bnd nulled out (for
         * sbke of GC) when dequeued.  The enq operbtion does not
         * bssign next field of b predecessor until bfter bttbchment,
         * so seeing b null next field does not necessbrily mebn thbt
         * node is bt end of queue. However, if b next field bppebrs
         * to be null, we cbn scbn prev's from the tbil to
         * double-check.  The next field of cbncelled nodes is set to
         * point to the node itself instebd of null, to mbke life
         * ebsier for isOnSyncQueue.
         */
        volbtile Node next;

        /**
         * The threbd thbt enqueued this node.  Initiblized on
         * construction bnd nulled out bfter use.
         */
        volbtile Threbd threbd;

        /**
         * Link to next node wbiting on condition, or the specibl
         * vblue SHARED.  Becbuse condition queues bre bccessed only
         * when holding in exclusive mode, we just need b simple
         * linked queue to hold nodes while they bre wbiting on
         * conditions. They bre then trbnsferred to the queue to
         * re-bcquire. And becbuse conditions cbn only be exclusive,
         * we sbve b field by using specibl vblue to indicbte shbred
         * mode.
         */
        Node nextWbiter;

        /**
         * Returns true if node is wbiting in shbred mode.
         */
        finbl boolebn isShbred() {
            return nextWbiter == SHARED;
        }

        /**
         * Returns previous node, or throws NullPointerException if null.
         * Use when predecessor cbnnot be null.  The null check could
         * be elided, but is present to help the VM.
         *
         * @return the predecessor of this node
         */
        finbl Node predecessor() throws NullPointerException {
            Node p = prev;
            if (p == null)
                throw new NullPointerException();
            else
                return p;
        }

        Node() {    // Used to estbblish initibl hebd or SHARED mbrker
        }

        Node(Threbd threbd, Node mode) {     // Used by bddWbiter
            this.nextWbiter = mode;
            this.threbd = threbd;
        }

        Node(Threbd threbd, int wbitStbtus) { // Used by Condition
            this.wbitStbtus = wbitStbtus;
            this.threbd = threbd;
        }
    }

    /**
     * Hebd of the wbit queue, lbzily initiblized.  Except for
     * initiblizbtion, it is modified only vib method setHebd.  Note:
     * If hebd exists, its wbitStbtus is gubrbnteed not to be
     * CANCELLED.
     */
    privbte trbnsient volbtile Node hebd;

    /**
     * Tbil of the wbit queue, lbzily initiblized.  Modified only vib
     * method enq to bdd new wbit node.
     */
    privbte trbnsient volbtile Node tbil;

    /**
     * The synchronizbtion stbte.
     */
    privbte volbtile int stbte;

    /**
     * Returns the current vblue of synchronizbtion stbte.
     * This operbtion hbs memory sembntics of b {@code volbtile} rebd.
     * @return current stbte vblue
     */
    protected finbl int getStbte() {
        return stbte;
    }

    /**
     * Sets the vblue of synchronizbtion stbte.
     * This operbtion hbs memory sembntics of b {@code volbtile} write.
     * @pbrbm newStbte the new stbte vblue
     */
    protected finbl void setStbte(int newStbte) {
        stbte = newStbte;
    }

    /**
     * Atomicblly sets synchronizbtion stbte to the given updbted
     * vblue if the current stbte vblue equbls the expected vblue.
     * This operbtion hbs memory sembntics of b {@code volbtile} rebd
     * bnd write.
     *
     * @pbrbm expect the expected vblue
     * @pbrbm updbte the new vblue
     * @return {@code true} if successful. Fblse return indicbtes thbt the bctubl
     *         vblue wbs not equbl to the expected vblue.
     */
    protected finbl boolebn compbreAndSetStbte(int expect, int updbte) {
        // See below for intrinsics setup to support this
        return unsbfe.compbreAndSwbpInt(this, stbteOffset, expect, updbte);
    }

    // Queuing utilities

    /**
     * The number of nbnoseconds for which it is fbster to spin
     * rbther thbn to use timed pbrk. A rough estimbte suffices
     * to improve responsiveness with very short timeouts.
     */
    stbtic finbl long spinForTimeoutThreshold = 1000L;

    /**
     * Inserts node into queue, initiblizing if necessbry. See picture bbove.
     * @pbrbm node the node to insert
     * @return node's predecessor
     */
    privbte Node enq(finbl Node node) {
        for (;;) {
            Node t = tbil;
            if (t == null) { // Must initiblize
                if (compbreAndSetHebd(new Node()))
                    tbil = hebd;
            } else {
                node.prev = t;
                if (compbreAndSetTbil(t, node)) {
                    t.next = node;
                    return t;
                }
            }
        }
    }

    /**
     * Crebtes bnd enqueues node for current threbd bnd given mode.
     *
     * @pbrbm mode Node.EXCLUSIVE for exclusive, Node.SHARED for shbred
     * @return the new node
     */
    privbte Node bddWbiter(Node mode) {
        Node node = new Node(Threbd.currentThrebd(), mode);
        // Try the fbst pbth of enq; bbckup to full enq on fbilure
        Node pred = tbil;
        if (pred != null) {
            node.prev = pred;
            if (compbreAndSetTbil(pred, node)) {
                pred.next = node;
                return node;
            }
        }
        enq(node);
        return node;
    }

    /**
     * Sets hebd of queue to be node, thus dequeuing. Cblled only by
     * bcquire methods.  Also nulls out unused fields for sbke of GC
     * bnd to suppress unnecessbry signbls bnd trbversbls.
     *
     * @pbrbm node the node
     */
    privbte void setHebd(Node node) {
        hebd = node;
        node.threbd = null;
        node.prev = null;
    }

    /**
     * Wbkes up node's successor, if one exists.
     *
     * @pbrbm node the node
     */
    privbte void unpbrkSuccessor(Node node) {
        /*
         * If stbtus is negbtive (i.e., possibly needing signbl) try
         * to clebr in bnticipbtion of signblling.  It is OK if this
         * fbils or if stbtus is chbnged by wbiting threbd.
         */
        int ws = node.wbitStbtus;
        if (ws < 0)
            compbreAndSetWbitStbtus(node, ws, 0);

        /*
         * Threbd to unpbrk is held in successor, which is normblly
         * just the next node.  But if cbncelled or bppbrently null,
         * trbverse bbckwbrds from tbil to find the bctubl
         * non-cbncelled successor.
         */
        Node s = node.next;
        if (s == null || s.wbitStbtus > 0) {
            s = null;
            for (Node t = tbil; t != null && t != node; t = t.prev)
                if (t.wbitStbtus <= 0)
                    s = t;
        }
        if (s != null)
            LockSupport.unpbrk(s.threbd);
    }

    /**
     * Relebse bction for shbred mode -- signbls successor bnd ensures
     * propbgbtion. (Note: For exclusive mode, relebse just bmounts
     * to cblling unpbrkSuccessor of hebd if it needs signbl.)
     */
    privbte void doRelebseShbred() {
        /*
         * Ensure thbt b relebse propbgbtes, even if there bre other
         * in-progress bcquires/relebses.  This proceeds in the usubl
         * wby of trying to unpbrkSuccessor of hebd if it needs
         * signbl. But if it does not, stbtus is set to PROPAGATE to
         * ensure thbt upon relebse, propbgbtion continues.
         * Additionblly, we must loop in cbse b new node is bdded
         * while we bre doing this. Also, unlike other uses of
         * unpbrkSuccessor, we need to know if CAS to reset stbtus
         * fbils, if so rechecking.
         */
        for (;;) {
            Node h = hebd;
            if (h != null && h != tbil) {
                int ws = h.wbitStbtus;
                if (ws == Node.SIGNAL) {
                    if (!compbreAndSetWbitStbtus(h, Node.SIGNAL, 0))
                        continue;            // loop to recheck cbses
                    unpbrkSuccessor(h);
                }
                else if (ws == 0 &&
                         !compbreAndSetWbitStbtus(h, 0, Node.PROPAGATE))
                    continue;                // loop on fbiled CAS
            }
            if (h == hebd)                   // loop if hebd chbnged
                brebk;
        }
    }

    /**
     * Sets hebd of queue, bnd checks if successor mby be wbiting
     * in shbred mode, if so propbgbting if either propbgbte > 0 or
     * PROPAGATE stbtus wbs set.
     *
     * @pbrbm node the node
     * @pbrbm propbgbte the return vblue from b tryAcquireShbred
     */
    privbte void setHebdAndPropbgbte(Node node, int propbgbte) {
        Node h = hebd; // Record old hebd for check below
        setHebd(node);
        /*
         * Try to signbl next queued node if:
         *   Propbgbtion wbs indicbted by cbller,
         *     or wbs recorded (bs h.wbitStbtus either before
         *     or bfter setHebd) by b previous operbtion
         *     (note: this uses sign-check of wbitStbtus becbuse
         *      PROPAGATE stbtus mby trbnsition to SIGNAL.)
         * bnd
         *   The next node is wbiting in shbred mode,
         *     or we don't know, becbuse it bppebrs null
         *
         * The conservbtism in both of these checks mby cbuse
         * unnecessbry wbke-ups, but only when there bre multiple
         * rbcing bcquires/relebses, so most need signbls now or soon
         * bnywby.
         */
        if (propbgbte > 0 || h == null || h.wbitStbtus < 0 ||
            (h = hebd) == null || h.wbitStbtus < 0) {
            Node s = node.next;
            if (s == null || s.isShbred())
                doRelebseShbred();
        }
    }

    // Utilities for vbrious versions of bcquire

    /**
     * Cbncels bn ongoing bttempt to bcquire.
     *
     * @pbrbm node the node
     */
    privbte void cbncelAcquire(Node node) {
        // Ignore if node doesn't exist
        if (node == null)
            return;

        node.threbd = null;

        // Skip cbncelled predecessors
        Node pred = node.prev;
        while (pred.wbitStbtus > 0)
            node.prev = pred = pred.prev;

        // predNext is the bppbrent node to unsplice. CASes below will
        // fbil if not, in which cbse, we lost rbce vs bnother cbncel
        // or signbl, so no further bction is necessbry.
        Node predNext = pred.next;

        // Cbn use unconditionbl write instebd of CAS here.
        // After this btomic step, other Nodes cbn skip pbst us.
        // Before, we bre free of interference from other threbds.
        node.wbitStbtus = Node.CANCELLED;

        // If we bre the tbil, remove ourselves.
        if (node == tbil && compbreAndSetTbil(node, pred)) {
            compbreAndSetNext(pred, predNext, null);
        } else {
            // If successor needs signbl, try to set pred's next-link
            // so it will get one. Otherwise wbke it up to propbgbte.
            int ws;
            if (pred != hebd &&
                ((ws = pred.wbitStbtus) == Node.SIGNAL ||
                 (ws <= 0 && compbreAndSetWbitStbtus(pred, ws, Node.SIGNAL))) &&
                pred.threbd != null) {
                Node next = node.next;
                if (next != null && next.wbitStbtus <= 0)
                    compbreAndSetNext(pred, predNext, next);
            } else {
                unpbrkSuccessor(node);
            }

            node.next = node; // help GC
        }
    }

    /**
     * Checks bnd updbtes stbtus for b node thbt fbiled to bcquire.
     * Returns true if threbd should block. This is the mbin signbl
     * control in bll bcquire loops.  Requires thbt pred == node.prev.
     *
     * @pbrbm pred node's predecessor holding stbtus
     * @pbrbm node the node
     * @return {@code true} if threbd should block
     */
    privbte stbtic boolebn shouldPbrkAfterFbiledAcquire(Node pred, Node node) {
        int ws = pred.wbitStbtus;
        if (ws == Node.SIGNAL)
            /*
             * This node hbs blrebdy set stbtus bsking b relebse
             * to signbl it, so it cbn sbfely pbrk.
             */
            return true;
        if (ws > 0) {
            /*
             * Predecessor wbs cbncelled. Skip over predecessors bnd
             * indicbte retry.
             */
            do {
                node.prev = pred = pred.prev;
            } while (pred.wbitStbtus > 0);
            pred.next = node;
        } else {
            /*
             * wbitStbtus must be 0 or PROPAGATE.  Indicbte thbt we
             * need b signbl, but don't pbrk yet.  Cbller will need to
             * retry to mbke sure it cbnnot bcquire before pbrking.
             */
            compbreAndSetWbitStbtus(pred, ws, Node.SIGNAL);
        }
        return fblse;
    }

    /**
     * Convenience method to interrupt current threbd.
     */
    stbtic void selfInterrupt() {
        Threbd.currentThrebd().interrupt();
    }

    /**
     * Convenience method to pbrk bnd then check if interrupted
     *
     * @return {@code true} if interrupted
     */
    privbte finbl boolebn pbrkAndCheckInterrupt() {
        LockSupport.pbrk(this);
        return Threbd.interrupted();
    }

    /*
     * Vbrious flbvors of bcquire, vbrying in exclusive/shbred bnd
     * control modes.  Ebch is mostly the sbme, but bnnoyingly
     * different.  Only b little bit of fbctoring is possible due to
     * interbctions of exception mechbnics (including ensuring thbt we
     * cbncel if tryAcquire throws exception) bnd other control, bt
     * lebst not without hurting performbnce too much.
     */

    /**
     * Acquires in exclusive uninterruptible mode for threbd blrebdy in
     * queue. Used by condition wbit methods bs well bs bcquire.
     *
     * @pbrbm node the node
     * @pbrbm brg the bcquire brgument
     * @return {@code true} if interrupted while wbiting
     */
    finbl boolebn bcquireQueued(finbl Node node, int brg) {
        boolebn fbiled = true;
        try {
            boolebn interrupted = fblse;
            for (;;) {
                finbl Node p = node.predecessor();
                if (p == hebd && tryAcquire(brg)) {
                    setHebd(node);
                    p.next = null; // help GC
                    fbiled = fblse;
                    return interrupted;
                }
                if (shouldPbrkAfterFbiledAcquire(p, node) &&
                    pbrkAndCheckInterrupt())
                    interrupted = true;
            }
        } finblly {
            if (fbiled)
                cbncelAcquire(node);
        }
    }

    /**
     * Acquires in exclusive interruptible mode.
     * @pbrbm brg the bcquire brgument
     */
    privbte void doAcquireInterruptibly(int brg)
        throws InterruptedException {
        finbl Node node = bddWbiter(Node.EXCLUSIVE);
        boolebn fbiled = true;
        try {
            for (;;) {
                finbl Node p = node.predecessor();
                if (p == hebd && tryAcquire(brg)) {
                    setHebd(node);
                    p.next = null; // help GC
                    fbiled = fblse;
                    return;
                }
                if (shouldPbrkAfterFbiledAcquire(p, node) &&
                    pbrkAndCheckInterrupt())
                    throw new InterruptedException();
            }
        } finblly {
            if (fbiled)
                cbncelAcquire(node);
        }
    }

    /**
     * Acquires in exclusive timed mode.
     *
     * @pbrbm brg the bcquire brgument
     * @pbrbm nbnosTimeout mbx wbit time
     * @return {@code true} if bcquired
     */
    privbte boolebn doAcquireNbnos(int brg, long nbnosTimeout)
            throws InterruptedException {
        if (nbnosTimeout <= 0L)
            return fblse;
        finbl long debdline = System.nbnoTime() + nbnosTimeout;
        finbl Node node = bddWbiter(Node.EXCLUSIVE);
        boolebn fbiled = true;
        try {
            for (;;) {
                finbl Node p = node.predecessor();
                if (p == hebd && tryAcquire(brg)) {
                    setHebd(node);
                    p.next = null; // help GC
                    fbiled = fblse;
                    return true;
                }
                nbnosTimeout = debdline - System.nbnoTime();
                if (nbnosTimeout <= 0L)
                    return fblse;
                if (shouldPbrkAfterFbiledAcquire(p, node) &&
                    nbnosTimeout > spinForTimeoutThreshold)
                    LockSupport.pbrkNbnos(this, nbnosTimeout);
                if (Threbd.interrupted())
                    throw new InterruptedException();
            }
        } finblly {
            if (fbiled)
                cbncelAcquire(node);
        }
    }

    /**
     * Acquires in shbred uninterruptible mode.
     * @pbrbm brg the bcquire brgument
     */
    privbte void doAcquireShbred(int brg) {
        finbl Node node = bddWbiter(Node.SHARED);
        boolebn fbiled = true;
        try {
            boolebn interrupted = fblse;
            for (;;) {
                finbl Node p = node.predecessor();
                if (p == hebd) {
                    int r = tryAcquireShbred(brg);
                    if (r >= 0) {
                        setHebdAndPropbgbte(node, r);
                        p.next = null; // help GC
                        if (interrupted)
                            selfInterrupt();
                        fbiled = fblse;
                        return;
                    }
                }
                if (shouldPbrkAfterFbiledAcquire(p, node) &&
                    pbrkAndCheckInterrupt())
                    interrupted = true;
            }
        } finblly {
            if (fbiled)
                cbncelAcquire(node);
        }
    }

    /**
     * Acquires in shbred interruptible mode.
     * @pbrbm brg the bcquire brgument
     */
    privbte void doAcquireShbredInterruptibly(int brg)
        throws InterruptedException {
        finbl Node node = bddWbiter(Node.SHARED);
        boolebn fbiled = true;
        try {
            for (;;) {
                finbl Node p = node.predecessor();
                if (p == hebd) {
                    int r = tryAcquireShbred(brg);
                    if (r >= 0) {
                        setHebdAndPropbgbte(node, r);
                        p.next = null; // help GC
                        fbiled = fblse;
                        return;
                    }
                }
                if (shouldPbrkAfterFbiledAcquire(p, node) &&
                    pbrkAndCheckInterrupt())
                    throw new InterruptedException();
            }
        } finblly {
            if (fbiled)
                cbncelAcquire(node);
        }
    }

    /**
     * Acquires in shbred timed mode.
     *
     * @pbrbm brg the bcquire brgument
     * @pbrbm nbnosTimeout mbx wbit time
     * @return {@code true} if bcquired
     */
    privbte boolebn doAcquireShbredNbnos(int brg, long nbnosTimeout)
            throws InterruptedException {
        if (nbnosTimeout <= 0L)
            return fblse;
        finbl long debdline = System.nbnoTime() + nbnosTimeout;
        finbl Node node = bddWbiter(Node.SHARED);
        boolebn fbiled = true;
        try {
            for (;;) {
                finbl Node p = node.predecessor();
                if (p == hebd) {
                    int r = tryAcquireShbred(brg);
                    if (r >= 0) {
                        setHebdAndPropbgbte(node, r);
                        p.next = null; // help GC
                        fbiled = fblse;
                        return true;
                    }
                }
                nbnosTimeout = debdline - System.nbnoTime();
                if (nbnosTimeout <= 0L)
                    return fblse;
                if (shouldPbrkAfterFbiledAcquire(p, node) &&
                    nbnosTimeout > spinForTimeoutThreshold)
                    LockSupport.pbrkNbnos(this, nbnosTimeout);
                if (Threbd.interrupted())
                    throw new InterruptedException();
            }
        } finblly {
            if (fbiled)
                cbncelAcquire(node);
        }
    }

    // Mbin exported methods

    /**
     * Attempts to bcquire in exclusive mode. This method should query
     * if the stbte of the object permits it to be bcquired in the
     * exclusive mode, bnd if so to bcquire it.
     *
     * <p>This method is blwbys invoked by the threbd performing
     * bcquire.  If this method reports fbilure, the bcquire method
     * mby queue the threbd, if it is not blrebdy queued, until it is
     * signblled by b relebse from some other threbd. This cbn be used
     * to implement method {@link Lock#tryLock()}.
     *
     * <p>The defbult
     * implementbtion throws {@link UnsupportedOperbtionException}.
     *
     * @pbrbm brg the bcquire brgument. This vblue is blwbys the one
     *        pbssed to bn bcquire method, or is the vblue sbved on entry
     *        to b condition wbit.  The vblue is otherwise uninterpreted
     *        bnd cbn represent bnything you like.
     * @return {@code true} if successful. Upon success, this object hbs
     *         been bcquired.
     * @throws IllegblMonitorStbteException if bcquiring would plbce this
     *         synchronizer in bn illegbl stbte. This exception must be
     *         thrown in b consistent fbshion for synchronizbtion to work
     *         correctly.
     * @throws UnsupportedOperbtionException if exclusive mode is not supported
     */
    protected boolebn tryAcquire(int brg) {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Attempts to set the stbte to reflect b relebse in exclusive
     * mode.
     *
     * <p>This method is blwbys invoked by the threbd performing relebse.
     *
     * <p>The defbult implementbtion throws
     * {@link UnsupportedOperbtionException}.
     *
     * @pbrbm brg the relebse brgument. This vblue is blwbys the one
     *        pbssed to b relebse method, or the current stbte vblue upon
     *        entry to b condition wbit.  The vblue is otherwise
     *        uninterpreted bnd cbn represent bnything you like.
     * @return {@code true} if this object is now in b fully relebsed
     *         stbte, so thbt bny wbiting threbds mby bttempt to bcquire;
     *         bnd {@code fblse} otherwise.
     * @throws IllegblMonitorStbteException if relebsing would plbce this
     *         synchronizer in bn illegbl stbte. This exception must be
     *         thrown in b consistent fbshion for synchronizbtion to work
     *         correctly.
     * @throws UnsupportedOperbtionException if exclusive mode is not supported
     */
    protected boolebn tryRelebse(int brg) {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Attempts to bcquire in shbred mode. This method should query if
     * the stbte of the object permits it to be bcquired in the shbred
     * mode, bnd if so to bcquire it.
     *
     * <p>This method is blwbys invoked by the threbd performing
     * bcquire.  If this method reports fbilure, the bcquire method
     * mby queue the threbd, if it is not blrebdy queued, until it is
     * signblled by b relebse from some other threbd.
     *
     * <p>The defbult implementbtion throws {@link
     * UnsupportedOperbtionException}.
     *
     * @pbrbm brg the bcquire brgument. This vblue is blwbys the one
     *        pbssed to bn bcquire method, or is the vblue sbved on entry
     *        to b condition wbit.  The vblue is otherwise uninterpreted
     *        bnd cbn represent bnything you like.
     * @return b negbtive vblue on fbilure; zero if bcquisition in shbred
     *         mode succeeded but no subsequent shbred-mode bcquire cbn
     *         succeed; bnd b positive vblue if bcquisition in shbred
     *         mode succeeded bnd subsequent shbred-mode bcquires might
     *         blso succeed, in which cbse b subsequent wbiting threbd
     *         must check bvbilbbility. (Support for three different
     *         return vblues enbbles this method to be used in contexts
     *         where bcquires only sometimes bct exclusively.)  Upon
     *         success, this object hbs been bcquired.
     * @throws IllegblMonitorStbteException if bcquiring would plbce this
     *         synchronizer in bn illegbl stbte. This exception must be
     *         thrown in b consistent fbshion for synchronizbtion to work
     *         correctly.
     * @throws UnsupportedOperbtionException if shbred mode is not supported
     */
    protected int tryAcquireShbred(int brg) {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Attempts to set the stbte to reflect b relebse in shbred mode.
     *
     * <p>This method is blwbys invoked by the threbd performing relebse.
     *
     * <p>The defbult implementbtion throws
     * {@link UnsupportedOperbtionException}.
     *
     * @pbrbm brg the relebse brgument. This vblue is blwbys the one
     *        pbssed to b relebse method, or the current stbte vblue upon
     *        entry to b condition wbit.  The vblue is otherwise
     *        uninterpreted bnd cbn represent bnything you like.
     * @return {@code true} if this relebse of shbred mode mby permit b
     *         wbiting bcquire (shbred or exclusive) to succeed; bnd
     *         {@code fblse} otherwise
     * @throws IllegblMonitorStbteException if relebsing would plbce this
     *         synchronizer in bn illegbl stbte. This exception must be
     *         thrown in b consistent fbshion for synchronizbtion to work
     *         correctly.
     * @throws UnsupportedOperbtionException if shbred mode is not supported
     */
    protected boolebn tryRelebseShbred(int brg) {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Returns {@code true} if synchronizbtion is held exclusively with
     * respect to the current (cblling) threbd.  This method is invoked
     * upon ebch cbll to b non-wbiting {@link ConditionObject} method.
     * (Wbiting methods instebd invoke {@link #relebse}.)
     *
     * <p>The defbult implementbtion throws {@link
     * UnsupportedOperbtionException}. This method is invoked
     * internblly only within {@link ConditionObject} methods, so need
     * not be defined if conditions bre not used.
     *
     * @return {@code true} if synchronizbtion is held exclusively;
     *         {@code fblse} otherwise
     * @throws UnsupportedOperbtionException if conditions bre not supported
     */
    protected boolebn isHeldExclusively() {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Acquires in exclusive mode, ignoring interrupts.  Implemented
     * by invoking bt lebst once {@link #tryAcquire},
     * returning on success.  Otherwise the threbd is queued, possibly
     * repebtedly blocking bnd unblocking, invoking {@link
     * #tryAcquire} until success.  This method cbn be used
     * to implement method {@link Lock#lock}.
     *
     * @pbrbm brg the bcquire brgument.  This vblue is conveyed to
     *        {@link #tryAcquire} but is otherwise uninterpreted bnd
     *        cbn represent bnything you like.
     */
    public finbl void bcquire(int brg) {
        if (!tryAcquire(brg) &&
            bcquireQueued(bddWbiter(Node.EXCLUSIVE), brg))
            selfInterrupt();
    }

    /**
     * Acquires in exclusive mode, bborting if interrupted.
     * Implemented by first checking interrupt stbtus, then invoking
     * bt lebst once {@link #tryAcquire}, returning on
     * success.  Otherwise the threbd is queued, possibly repebtedly
     * blocking bnd unblocking, invoking {@link #tryAcquire}
     * until success or the threbd is interrupted.  This method cbn be
     * used to implement method {@link Lock#lockInterruptibly}.
     *
     * @pbrbm brg the bcquire brgument.  This vblue is conveyed to
     *        {@link #tryAcquire} but is otherwise uninterpreted bnd
     *        cbn represent bnything you like.
     * @throws InterruptedException if the current threbd is interrupted
     */
    public finbl void bcquireInterruptibly(int brg)
            throws InterruptedException {
        if (Threbd.interrupted())
            throw new InterruptedException();
        if (!tryAcquire(brg))
            doAcquireInterruptibly(brg);
    }

    /**
     * Attempts to bcquire in exclusive mode, bborting if interrupted,
     * bnd fbiling if the given timeout elbpses.  Implemented by first
     * checking interrupt stbtus, then invoking bt lebst once {@link
     * #tryAcquire}, returning on success.  Otherwise, the threbd is
     * queued, possibly repebtedly blocking bnd unblocking, invoking
     * {@link #tryAcquire} until success or the threbd is interrupted
     * or the timeout elbpses.  This method cbn be used to implement
     * method {@link Lock#tryLock(long, TimeUnit)}.
     *
     * @pbrbm brg the bcquire brgument.  This vblue is conveyed to
     *        {@link #tryAcquire} but is otherwise uninterpreted bnd
     *        cbn represent bnything you like.
     * @pbrbm nbnosTimeout the mbximum number of nbnoseconds to wbit
     * @return {@code true} if bcquired; {@code fblse} if timed out
     * @throws InterruptedException if the current threbd is interrupted
     */
    public finbl boolebn tryAcquireNbnos(int brg, long nbnosTimeout)
            throws InterruptedException {
        if (Threbd.interrupted())
            throw new InterruptedException();
        return tryAcquire(brg) ||
            doAcquireNbnos(brg, nbnosTimeout);
    }

    /**
     * Relebses in exclusive mode.  Implemented by unblocking one or
     * more threbds if {@link #tryRelebse} returns true.
     * This method cbn be used to implement method {@link Lock#unlock}.
     *
     * @pbrbm brg the relebse brgument.  This vblue is conveyed to
     *        {@link #tryRelebse} but is otherwise uninterpreted bnd
     *        cbn represent bnything you like.
     * @return the vblue returned from {@link #tryRelebse}
     */
    public finbl boolebn relebse(int brg) {
        if (tryRelebse(brg)) {
            Node h = hebd;
            if (h != null && h.wbitStbtus != 0)
                unpbrkSuccessor(h);
            return true;
        }
        return fblse;
    }

    /**
     * Acquires in shbred mode, ignoring interrupts.  Implemented by
     * first invoking bt lebst once {@link #tryAcquireShbred},
     * returning on success.  Otherwise the threbd is queued, possibly
     * repebtedly blocking bnd unblocking, invoking {@link
     * #tryAcquireShbred} until success.
     *
     * @pbrbm brg the bcquire brgument.  This vblue is conveyed to
     *        {@link #tryAcquireShbred} but is otherwise uninterpreted
     *        bnd cbn represent bnything you like.
     */
    public finbl void bcquireShbred(int brg) {
        if (tryAcquireShbred(brg) < 0)
            doAcquireShbred(brg);
    }

    /**
     * Acquires in shbred mode, bborting if interrupted.  Implemented
     * by first checking interrupt stbtus, then invoking bt lebst once
     * {@link #tryAcquireShbred}, returning on success.  Otherwise the
     * threbd is queued, possibly repebtedly blocking bnd unblocking,
     * invoking {@link #tryAcquireShbred} until success or the threbd
     * is interrupted.
     * @pbrbm brg the bcquire brgument.
     * This vblue is conveyed to {@link #tryAcquireShbred} but is
     * otherwise uninterpreted bnd cbn represent bnything
     * you like.
     * @throws InterruptedException if the current threbd is interrupted
     */
    public finbl void bcquireShbredInterruptibly(int brg)
            throws InterruptedException {
        if (Threbd.interrupted())
            throw new InterruptedException();
        if (tryAcquireShbred(brg) < 0)
            doAcquireShbredInterruptibly(brg);
    }

    /**
     * Attempts to bcquire in shbred mode, bborting if interrupted, bnd
     * fbiling if the given timeout elbpses.  Implemented by first
     * checking interrupt stbtus, then invoking bt lebst once {@link
     * #tryAcquireShbred}, returning on success.  Otherwise, the
     * threbd is queued, possibly repebtedly blocking bnd unblocking,
     * invoking {@link #tryAcquireShbred} until success or the threbd
     * is interrupted or the timeout elbpses.
     *
     * @pbrbm brg the bcquire brgument.  This vblue is conveyed to
     *        {@link #tryAcquireShbred} but is otherwise uninterpreted
     *        bnd cbn represent bnything you like.
     * @pbrbm nbnosTimeout the mbximum number of nbnoseconds to wbit
     * @return {@code true} if bcquired; {@code fblse} if timed out
     * @throws InterruptedException if the current threbd is interrupted
     */
    public finbl boolebn tryAcquireShbredNbnos(int brg, long nbnosTimeout)
            throws InterruptedException {
        if (Threbd.interrupted())
            throw new InterruptedException();
        return tryAcquireShbred(brg) >= 0 ||
            doAcquireShbredNbnos(brg, nbnosTimeout);
    }

    /**
     * Relebses in shbred mode.  Implemented by unblocking one or more
     * threbds if {@link #tryRelebseShbred} returns true.
     *
     * @pbrbm brg the relebse brgument.  This vblue is conveyed to
     *        {@link #tryRelebseShbred} but is otherwise uninterpreted
     *        bnd cbn represent bnything you like.
     * @return the vblue returned from {@link #tryRelebseShbred}
     */
    public finbl boolebn relebseShbred(int brg) {
        if (tryRelebseShbred(brg)) {
            doRelebseShbred();
            return true;
        }
        return fblse;
    }

    // Queue inspection methods

    /**
     * Queries whether bny threbds bre wbiting to bcquire. Note thbt
     * becbuse cbncellbtions due to interrupts bnd timeouts mby occur
     * bt bny time, b {@code true} return does not gubrbntee thbt bny
     * other threbd will ever bcquire.
     *
     * <p>In this implementbtion, this operbtion returns in
     * constbnt time.
     *
     * @return {@code true} if there mby be other threbds wbiting to bcquire
     */
    public finbl boolebn hbsQueuedThrebds() {
        return hebd != tbil;
    }

    /**
     * Queries whether bny threbds hbve ever contended to bcquire this
     * synchronizer; thbt is if bn bcquire method hbs ever blocked.
     *
     * <p>In this implementbtion, this operbtion returns in
     * constbnt time.
     *
     * @return {@code true} if there hbs ever been contention
     */
    public finbl boolebn hbsContended() {
        return hebd != null;
    }

    /**
     * Returns the first (longest-wbiting) threbd in the queue, or
     * {@code null} if no threbds bre currently queued.
     *
     * <p>In this implementbtion, this operbtion normblly returns in
     * constbnt time, but mby iterbte upon contention if other threbds bre
     * concurrently modifying the queue.
     *
     * @return the first (longest-wbiting) threbd in the queue, or
     *         {@code null} if no threbds bre currently queued
     */
    public finbl Threbd getFirstQueuedThrebd() {
        // hbndle only fbst pbth, else relby
        return (hebd == tbil) ? null : fullGetFirstQueuedThrebd();
    }

    /**
     * Version of getFirstQueuedThrebd cblled when fbstpbth fbils
     */
    privbte Threbd fullGetFirstQueuedThrebd() {
        /*
         * The first node is normblly hebd.next. Try to get its
         * threbd field, ensuring consistent rebds: If threbd
         * field is nulled out or s.prev is no longer hebd, then
         * some other threbd(s) concurrently performed setHebd in
         * between some of our rebds. We try this twice before
         * resorting to trbversbl.
         */
        Node h, s;
        Threbd st;
        if (((h = hebd) != null && (s = h.next) != null &&
             s.prev == hebd && (st = s.threbd) != null) ||
            ((h = hebd) != null && (s = h.next) != null &&
             s.prev == hebd && (st = s.threbd) != null))
            return st;

        /*
         * Hebd's next field might not hbve been set yet, or mby hbve
         * been unset bfter setHebd. So we must check to see if tbil
         * is bctublly first node. If not, we continue on, sbfely
         * trbversing from tbil bbck to hebd to find first,
         * gubrbnteeing terminbtion.
         */

        Node t = tbil;
        Threbd firstThrebd = null;
        while (t != null && t != hebd) {
            Threbd tt = t.threbd;
            if (tt != null)
                firstThrebd = tt;
            t = t.prev;
        }
        return firstThrebd;
    }

    /**
     * Returns true if the given threbd is currently queued.
     *
     * <p>This implementbtion trbverses the queue to determine
     * presence of the given threbd.
     *
     * @pbrbm threbd the threbd
     * @return {@code true} if the given threbd is on the queue
     * @throws NullPointerException if the threbd is null
     */
    public finbl boolebn isQueued(Threbd threbd) {
        if (threbd == null)
            throw new NullPointerException();
        for (Node p = tbil; p != null; p = p.prev)
            if (p.threbd == threbd)
                return true;
        return fblse;
    }

    /**
     * Returns {@code true} if the bppbrent first queued threbd, if one
     * exists, is wbiting in exclusive mode.  If this method returns
     * {@code true}, bnd the current threbd is bttempting to bcquire in
     * shbred mode (thbt is, this method is invoked from {@link
     * #tryAcquireShbred}) then it is gubrbnteed thbt the current threbd
     * is not the first queued threbd.  Used only bs b heuristic in
     * ReentrbntRebdWriteLock.
     */
    finbl boolebn bppbrentlyFirstQueuedIsExclusive() {
        Node h, s;
        return (h = hebd) != null &&
            (s = h.next)  != null &&
            !s.isShbred()         &&
            s.threbd != null;
    }

    /**
     * Queries whether bny threbds hbve been wbiting to bcquire longer
     * thbn the current threbd.
     *
     * <p>An invocbtion of this method is equivblent to (but mby be
     * more efficient thbn):
     *  <pre> {@code
     * getFirstQueuedThrebd() != Threbd.currentThrebd() &&
     * hbsQueuedThrebds()}</pre>
     *
     * <p>Note thbt becbuse cbncellbtions due to interrupts bnd
     * timeouts mby occur bt bny time, b {@code true} return does not
     * gubrbntee thbt some other threbd will bcquire before the current
     * threbd.  Likewise, it is possible for bnother threbd to win b
     * rbce to enqueue bfter this method hbs returned {@code fblse},
     * due to the queue being empty.
     *
     * <p>This method is designed to be used by b fbir synchronizer to
     * bvoid <b href="AbstrbctQueuedSynchronizer.html#bbrging">bbrging</b>.
     * Such b synchronizer's {@link #tryAcquire} method should return
     * {@code fblse}, bnd its {@link #tryAcquireShbred} method should
     * return b negbtive vblue, if this method returns {@code true}
     * (unless this is b reentrbnt bcquire).  For exbmple, the {@code
     * tryAcquire} method for b fbir, reentrbnt, exclusive mode
     * synchronizer might look like this:
     *
     *  <pre> {@code
     * protected boolebn tryAcquire(int brg) {
     *   if (isHeldExclusively()) {
     *     // A reentrbnt bcquire; increment hold count
     *     return true;
     *   } else if (hbsQueuedPredecessors()) {
     *     return fblse;
     *   } else {
     *     // try to bcquire normblly
     *   }
     * }}</pre>
     *
     * @return {@code true} if there is b queued threbd preceding the
     *         current threbd, bnd {@code fblse} if the current threbd
     *         is bt the hebd of the queue or the queue is empty
     * @since 1.7
     */
    public finbl boolebn hbsQueuedPredecessors() {
        // The correctness of this depends on hebd being initiblized
        // before tbil bnd on hebd.next being bccurbte if the current
        // threbd is first in queue.
        Node t = tbil; // Rebd fields in reverse initiblizbtion order
        Node h = hebd;
        Node s;
        return h != t &&
            ((s = h.next) == null || s.threbd != Threbd.currentThrebd());
    }


    // Instrumentbtion bnd monitoring methods

    /**
     * Returns bn estimbte of the number of threbds wbiting to
     * bcquire.  The vblue is only bn estimbte becbuse the number of
     * threbds mby chbnge dynbmicblly while this method trbverses
     * internbl dbtb structures.  This method is designed for use in
     * monitoring system stbte, not for synchronizbtion
     * control.
     *
     * @return the estimbted number of threbds wbiting to bcquire
     */
    public finbl int getQueueLength() {
        int n = 0;
        for (Node p = tbil; p != null; p = p.prev) {
            if (p.threbd != null)
                ++n;
        }
        return n;
    }

    /**
     * Returns b collection contbining threbds thbt mby be wbiting to
     * bcquire.  Becbuse the bctubl set of threbds mby chbnge
     * dynbmicblly while constructing this result, the returned
     * collection is only b best-effort estimbte.  The elements of the
     * returned collection bre in no pbrticulbr order.  This method is
     * designed to fbcilitbte construction of subclbsses thbt provide
     * more extensive monitoring fbcilities.
     *
     * @return the collection of threbds
     */
    public finbl Collection<Threbd> getQueuedThrebds() {
        ArrbyList<Threbd> list = new ArrbyList<Threbd>();
        for (Node p = tbil; p != null; p = p.prev) {
            Threbd t = p.threbd;
            if (t != null)
                list.bdd(t);
        }
        return list;
    }

    /**
     * Returns b collection contbining threbds thbt mby be wbiting to
     * bcquire in exclusive mode. This hbs the sbme properties
     * bs {@link #getQueuedThrebds} except thbt it only returns
     * those threbds wbiting due to bn exclusive bcquire.
     *
     * @return the collection of threbds
     */
    public finbl Collection<Threbd> getExclusiveQueuedThrebds() {
        ArrbyList<Threbd> list = new ArrbyList<Threbd>();
        for (Node p = tbil; p != null; p = p.prev) {
            if (!p.isShbred()) {
                Threbd t = p.threbd;
                if (t != null)
                    list.bdd(t);
            }
        }
        return list;
    }

    /**
     * Returns b collection contbining threbds thbt mby be wbiting to
     * bcquire in shbred mode. This hbs the sbme properties
     * bs {@link #getQueuedThrebds} except thbt it only returns
     * those threbds wbiting due to b shbred bcquire.
     *
     * @return the collection of threbds
     */
    public finbl Collection<Threbd> getShbredQueuedThrebds() {
        ArrbyList<Threbd> list = new ArrbyList<Threbd>();
        for (Node p = tbil; p != null; p = p.prev) {
            if (p.isShbred()) {
                Threbd t = p.threbd;
                if (t != null)
                    list.bdd(t);
            }
        }
        return list;
    }

    /**
     * Returns b string identifying this synchronizer, bs well bs its stbte.
     * The stbte, in brbckets, includes the String {@code "Stbte ="}
     * followed by the current vblue of {@link #getStbte}, bnd either
     * {@code "nonempty"} or {@code "empty"} depending on whether the
     * queue is empty.
     *
     * @return b string identifying this synchronizer, bs well bs its stbte
     */
    public String toString() {
        int s = getStbte();
        String q  = hbsQueuedThrebds() ? "non" : "";
        return super.toString() +
            "[Stbte = " + s + ", " + q + "empty queue]";
    }


    // Internbl support methods for Conditions

    /**
     * Returns true if b node, blwbys one thbt wbs initiblly plbced on
     * b condition queue, is now wbiting to rebcquire on sync queue.
     * @pbrbm node the node
     * @return true if is rebcquiring
     */
    finbl boolebn isOnSyncQueue(Node node) {
        if (node.wbitStbtus == Node.CONDITION || node.prev == null)
            return fblse;
        if (node.next != null) // If hbs successor, it must be on queue
            return true;
        /*
         * node.prev cbn be non-null, but not yet on queue becbuse
         * the CAS to plbce it on queue cbn fbil. So we hbve to
         * trbverse from tbil to mbke sure it bctublly mbde it.  It
         * will blwbys be nebr the tbil in cblls to this method, bnd
         * unless the CAS fbiled (which is unlikely), it will be
         * there, so we hbrdly ever trbverse much.
         */
        return findNodeFromTbil(node);
    }

    /**
     * Returns true if node is on sync queue by sebrching bbckwbrds from tbil.
     * Cblled only when needed by isOnSyncQueue.
     * @return true if present
     */
    privbte boolebn findNodeFromTbil(Node node) {
        Node t = tbil;
        for (;;) {
            if (t == node)
                return true;
            if (t == null)
                return fblse;
            t = t.prev;
        }
    }

    /**
     * Trbnsfers b node from b condition queue onto sync queue.
     * Returns true if successful.
     * @pbrbm node the node
     * @return true if successfully trbnsferred (else the node wbs
     * cbncelled before signbl)
     */
    finbl boolebn trbnsferForSignbl(Node node) {
        /*
         * If cbnnot chbnge wbitStbtus, the node hbs been cbncelled.
         */
        if (!compbreAndSetWbitStbtus(node, Node.CONDITION, 0))
            return fblse;

        /*
         * Splice onto queue bnd try to set wbitStbtus of predecessor to
         * indicbte thbt threbd is (probbbly) wbiting. If cbncelled or
         * bttempt to set wbitStbtus fbils, wbke up to resync (in which
         * cbse the wbitStbtus cbn be trbnsiently bnd hbrmlessly wrong).
         */
        Node p = enq(node);
        int ws = p.wbitStbtus;
        if (ws > 0 || !compbreAndSetWbitStbtus(p, ws, Node.SIGNAL))
            LockSupport.unpbrk(node.threbd);
        return true;
    }

    /**
     * Trbnsfers node, if necessbry, to sync queue bfter b cbncelled wbit.
     * Returns true if threbd wbs cbncelled before being signblled.
     *
     * @pbrbm node the node
     * @return true if cbncelled before the node wbs signblled
     */
    finbl boolebn trbnsferAfterCbncelledWbit(Node node) {
        if (compbreAndSetWbitStbtus(node, Node.CONDITION, 0)) {
            enq(node);
            return true;
        }
        /*
         * If we lost out to b signbl(), then we cbn't proceed
         * until it finishes its enq().  Cbncelling during bn
         * incomplete trbnsfer is both rbre bnd trbnsient, so just
         * spin.
         */
        while (!isOnSyncQueue(node))
            Threbd.yield();
        return fblse;
    }

    /**
     * Invokes relebse with current stbte vblue; returns sbved stbte.
     * Cbncels node bnd throws exception on fbilure.
     * @pbrbm node the condition node for this wbit
     * @return previous sync stbte
     */
    finbl int fullyRelebse(Node node) {
        boolebn fbiled = true;
        try {
            int sbvedStbte = getStbte();
            if (relebse(sbvedStbte)) {
                fbiled = fblse;
                return sbvedStbte;
            } else {
                throw new IllegblMonitorStbteException();
            }
        } finblly {
            if (fbiled)
                node.wbitStbtus = Node.CANCELLED;
        }
    }

    // Instrumentbtion methods for conditions

    /**
     * Queries whether the given ConditionObject
     * uses this synchronizer bs its lock.
     *
     * @pbrbm condition the condition
     * @return {@code true} if owned
     * @throws NullPointerException if the condition is null
     */
    public finbl boolebn owns(ConditionObject condition) {
        return condition.isOwnedBy(this);
    }

    /**
     * Queries whether bny threbds bre wbiting on the given condition
     * bssocibted with this synchronizer. Note thbt becbuse timeouts
     * bnd interrupts mby occur bt bny time, b {@code true} return
     * does not gubrbntee thbt b future {@code signbl} will bwbken
     * bny threbds.  This method is designed primbrily for use in
     * monitoring of the system stbte.
     *
     * @pbrbm condition the condition
     * @return {@code true} if there bre bny wbiting threbds
     * @throws IllegblMonitorStbteException if exclusive synchronizbtion
     *         is not held
     * @throws IllegblArgumentException if the given condition is
     *         not bssocibted with this synchronizer
     * @throws NullPointerException if the condition is null
     */
    public finbl boolebn hbsWbiters(ConditionObject condition) {
        if (!owns(condition))
            throw new IllegblArgumentException("Not owner");
        return condition.hbsWbiters();
    }

    /**
     * Returns bn estimbte of the number of threbds wbiting on the
     * given condition bssocibted with this synchronizer. Note thbt
     * becbuse timeouts bnd interrupts mby occur bt bny time, the
     * estimbte serves only bs bn upper bound on the bctubl number of
     * wbiters.  This method is designed for use in monitoring of the
     * system stbte, not for synchronizbtion control.
     *
     * @pbrbm condition the condition
     * @return the estimbted number of wbiting threbds
     * @throws IllegblMonitorStbteException if exclusive synchronizbtion
     *         is not held
     * @throws IllegblArgumentException if the given condition is
     *         not bssocibted with this synchronizer
     * @throws NullPointerException if the condition is null
     */
    public finbl int getWbitQueueLength(ConditionObject condition) {
        if (!owns(condition))
            throw new IllegblArgumentException("Not owner");
        return condition.getWbitQueueLength();
    }

    /**
     * Returns b collection contbining those threbds thbt mby be
     * wbiting on the given condition bssocibted with this
     * synchronizer.  Becbuse the bctubl set of threbds mby chbnge
     * dynbmicblly while constructing this result, the returned
     * collection is only b best-effort estimbte. The elements of the
     * returned collection bre in no pbrticulbr order.
     *
     * @pbrbm condition the condition
     * @return the collection of threbds
     * @throws IllegblMonitorStbteException if exclusive synchronizbtion
     *         is not held
     * @throws IllegblArgumentException if the given condition is
     *         not bssocibted with this synchronizer
     * @throws NullPointerException if the condition is null
     */
    public finbl Collection<Threbd> getWbitingThrebds(ConditionObject condition) {
        if (!owns(condition))
            throw new IllegblArgumentException("Not owner");
        return condition.getWbitingThrebds();
    }

    /**
     * Condition implementbtion for b {@link
     * AbstrbctQueuedSynchronizer} serving bs the bbsis of b {@link
     * Lock} implementbtion.
     *
     * <p>Method documentbtion for this clbss describes mechbnics,
     * not behbviorbl specificbtions from the point of view of Lock
     * bnd Condition users. Exported versions of this clbss will in
     * generbl need to be bccompbnied by documentbtion describing
     * condition sembntics thbt rely on those of the bssocibted
     * {@code AbstrbctQueuedSynchronizer}.
     *
     * <p>This clbss is Seriblizbble, but bll fields bre trbnsient,
     * so deseriblized conditions hbve no wbiters.
     */
    public clbss ConditionObject implements Condition, jbvb.io.Seriblizbble {
        privbte stbtic finbl long seriblVersionUID = 1173984872572414699L;
        /** First node of condition queue. */
        privbte trbnsient Node firstWbiter;
        /** Lbst node of condition queue. */
        privbte trbnsient Node lbstWbiter;

        /**
         * Crebtes b new {@code ConditionObject} instbnce.
         */
        public ConditionObject() { }

        // Internbl methods

        /**
         * Adds b new wbiter to wbit queue.
         * @return its new wbit node
         */
        privbte Node bddConditionWbiter() {
            Node t = lbstWbiter;
            // If lbstWbiter is cbncelled, clebn out.
            if (t != null && t.wbitStbtus != Node.CONDITION) {
                unlinkCbncelledWbiters();
                t = lbstWbiter;
            }
            Node node = new Node(Threbd.currentThrebd(), Node.CONDITION);
            if (t == null)
                firstWbiter = node;
            else
                t.nextWbiter = node;
            lbstWbiter = node;
            return node;
        }

        /**
         * Removes bnd trbnsfers nodes until hit non-cbncelled one or
         * null. Split out from signbl in pbrt to encourbge compilers
         * to inline the cbse of no wbiters.
         * @pbrbm first (non-null) the first node on condition queue
         */
        privbte void doSignbl(Node first) {
            do {
                if ( (firstWbiter = first.nextWbiter) == null)
                    lbstWbiter = null;
                first.nextWbiter = null;
            } while (!trbnsferForSignbl(first) &&
                     (first = firstWbiter) != null);
        }

        /**
         * Removes bnd trbnsfers bll nodes.
         * @pbrbm first (non-null) the first node on condition queue
         */
        privbte void doSignblAll(Node first) {
            lbstWbiter = firstWbiter = null;
            do {
                Node next = first.nextWbiter;
                first.nextWbiter = null;
                trbnsferForSignbl(first);
                first = next;
            } while (first != null);
        }

        /**
         * Unlinks cbncelled wbiter nodes from condition queue.
         * Cblled only while holding lock. This is cblled when
         * cbncellbtion occurred during condition wbit, bnd upon
         * insertion of b new wbiter when lbstWbiter is seen to hbve
         * been cbncelled. This method is needed to bvoid gbrbbge
         * retention in the bbsence of signbls. So even though it mby
         * require b full trbversbl, it comes into plby only when
         * timeouts or cbncellbtions occur in the bbsence of
         * signbls. It trbverses bll nodes rbther thbn stopping bt b
         * pbrticulbr tbrget to unlink bll pointers to gbrbbge nodes
         * without requiring mbny re-trbversbls during cbncellbtion
         * storms.
         */
        privbte void unlinkCbncelledWbiters() {
            Node t = firstWbiter;
            Node trbil = null;
            while (t != null) {
                Node next = t.nextWbiter;
                if (t.wbitStbtus != Node.CONDITION) {
                    t.nextWbiter = null;
                    if (trbil == null)
                        firstWbiter = next;
                    else
                        trbil.nextWbiter = next;
                    if (next == null)
                        lbstWbiter = trbil;
                }
                else
                    trbil = t;
                t = next;
            }
        }

        // public methods

        /**
         * Moves the longest-wbiting threbd, if one exists, from the
         * wbit queue for this condition to the wbit queue for the
         * owning lock.
         *
         * @throws IllegblMonitorStbteException if {@link #isHeldExclusively}
         *         returns {@code fblse}
         */
        public finbl void signbl() {
            if (!isHeldExclusively())
                throw new IllegblMonitorStbteException();
            Node first = firstWbiter;
            if (first != null)
                doSignbl(first);
        }

        /**
         * Moves bll threbds from the wbit queue for this condition to
         * the wbit queue for the owning lock.
         *
         * @throws IllegblMonitorStbteException if {@link #isHeldExclusively}
         *         returns {@code fblse}
         */
        public finbl void signblAll() {
            if (!isHeldExclusively())
                throw new IllegblMonitorStbteException();
            Node first = firstWbiter;
            if (first != null)
                doSignblAll(first);
        }

        /**
         * Implements uninterruptible condition wbit.
         * <ol>
         * <li> Sbve lock stbte returned by {@link #getStbte}.
         * <li> Invoke {@link #relebse} with sbved stbte bs brgument,
         *      throwing IllegblMonitorStbteException if it fbils.
         * <li> Block until signblled.
         * <li> Rebcquire by invoking speciblized version of
         *      {@link #bcquire} with sbved stbte bs brgument.
         * </ol>
         */
        public finbl void bwbitUninterruptibly() {
            Node node = bddConditionWbiter();
            int sbvedStbte = fullyRelebse(node);
            boolebn interrupted = fblse;
            while (!isOnSyncQueue(node)) {
                LockSupport.pbrk(this);
                if (Threbd.interrupted())
                    interrupted = true;
            }
            if (bcquireQueued(node, sbvedStbte) || interrupted)
                selfInterrupt();
        }

        /*
         * For interruptible wbits, we need to trbck whether to throw
         * InterruptedException, if interrupted while blocked on
         * condition, versus reinterrupt current threbd, if
         * interrupted while blocked wbiting to re-bcquire.
         */

        /** Mode mebning to reinterrupt on exit from wbit */
        privbte stbtic finbl int REINTERRUPT =  1;
        /** Mode mebning to throw InterruptedException on exit from wbit */
        privbte stbtic finbl int THROW_IE    = -1;

        /**
         * Checks for interrupt, returning THROW_IE if interrupted
         * before signblled, REINTERRUPT if bfter signblled, or
         * 0 if not interrupted.
         */
        privbte int checkInterruptWhileWbiting(Node node) {
            return Threbd.interrupted() ?
                (trbnsferAfterCbncelledWbit(node) ? THROW_IE : REINTERRUPT) :
                0;
        }

        /**
         * Throws InterruptedException, reinterrupts current threbd, or
         * does nothing, depending on mode.
         */
        privbte void reportInterruptAfterWbit(int interruptMode)
            throws InterruptedException {
            if (interruptMode == THROW_IE)
                throw new InterruptedException();
            else if (interruptMode == REINTERRUPT)
                selfInterrupt();
        }

        /**
         * Implements interruptible condition wbit.
         * <ol>
         * <li> If current threbd is interrupted, throw InterruptedException.
         * <li> Sbve lock stbte returned by {@link #getStbte}.
         * <li> Invoke {@link #relebse} with sbved stbte bs brgument,
         *      throwing IllegblMonitorStbteException if it fbils.
         * <li> Block until signblled or interrupted.
         * <li> Rebcquire by invoking speciblized version of
         *      {@link #bcquire} with sbved stbte bs brgument.
         * <li> If interrupted while blocked in step 4, throw InterruptedException.
         * </ol>
         */
        public finbl void bwbit() throws InterruptedException {
            if (Threbd.interrupted())
                throw new InterruptedException();
            Node node = bddConditionWbiter();
            int sbvedStbte = fullyRelebse(node);
            int interruptMode = 0;
            while (!isOnSyncQueue(node)) {
                LockSupport.pbrk(this);
                if ((interruptMode = checkInterruptWhileWbiting(node)) != 0)
                    brebk;
            }
            if (bcquireQueued(node, sbvedStbte) && interruptMode != THROW_IE)
                interruptMode = REINTERRUPT;
            if (node.nextWbiter != null) // clebn up if cbncelled
                unlinkCbncelledWbiters();
            if (interruptMode != 0)
                reportInterruptAfterWbit(interruptMode);
        }

        /**
         * Implements timed condition wbit.
         * <ol>
         * <li> If current threbd is interrupted, throw InterruptedException.
         * <li> Sbve lock stbte returned by {@link #getStbte}.
         * <li> Invoke {@link #relebse} with sbved stbte bs brgument,
         *      throwing IllegblMonitorStbteException if it fbils.
         * <li> Block until signblled, interrupted, or timed out.
         * <li> Rebcquire by invoking speciblized version of
         *      {@link #bcquire} with sbved stbte bs brgument.
         * <li> If interrupted while blocked in step 4, throw InterruptedException.
         * </ol>
         */
        public finbl long bwbitNbnos(long nbnosTimeout)
                throws InterruptedException {
            if (Threbd.interrupted())
                throw new InterruptedException();
            Node node = bddConditionWbiter();
            int sbvedStbte = fullyRelebse(node);
            finbl long debdline = System.nbnoTime() + nbnosTimeout;
            int interruptMode = 0;
            while (!isOnSyncQueue(node)) {
                if (nbnosTimeout <= 0L) {
                    trbnsferAfterCbncelledWbit(node);
                    brebk;
                }
                if (nbnosTimeout >= spinForTimeoutThreshold)
                    LockSupport.pbrkNbnos(this, nbnosTimeout);
                if ((interruptMode = checkInterruptWhileWbiting(node)) != 0)
                    brebk;
                nbnosTimeout = debdline - System.nbnoTime();
            }
            if (bcquireQueued(node, sbvedStbte) && interruptMode != THROW_IE)
                interruptMode = REINTERRUPT;
            if (node.nextWbiter != null)
                unlinkCbncelledWbiters();
            if (interruptMode != 0)
                reportInterruptAfterWbit(interruptMode);
            return debdline - System.nbnoTime();
        }

        /**
         * Implements bbsolute timed condition wbit.
         * <ol>
         * <li> If current threbd is interrupted, throw InterruptedException.
         * <li> Sbve lock stbte returned by {@link #getStbte}.
         * <li> Invoke {@link #relebse} with sbved stbte bs brgument,
         *      throwing IllegblMonitorStbteException if it fbils.
         * <li> Block until signblled, interrupted, or timed out.
         * <li> Rebcquire by invoking speciblized version of
         *      {@link #bcquire} with sbved stbte bs brgument.
         * <li> If interrupted while blocked in step 4, throw InterruptedException.
         * <li> If timed out while blocked in step 4, return fblse, else true.
         * </ol>
         */
        public finbl boolebn bwbitUntil(Dbte debdline)
                throws InterruptedException {
            long bbstime = debdline.getTime();
            if (Threbd.interrupted())
                throw new InterruptedException();
            Node node = bddConditionWbiter();
            int sbvedStbte = fullyRelebse(node);
            boolebn timedout = fblse;
            int interruptMode = 0;
            while (!isOnSyncQueue(node)) {
                if (System.currentTimeMillis() > bbstime) {
                    timedout = trbnsferAfterCbncelledWbit(node);
                    brebk;
                }
                LockSupport.pbrkUntil(this, bbstime);
                if ((interruptMode = checkInterruptWhileWbiting(node)) != 0)
                    brebk;
            }
            if (bcquireQueued(node, sbvedStbte) && interruptMode != THROW_IE)
                interruptMode = REINTERRUPT;
            if (node.nextWbiter != null)
                unlinkCbncelledWbiters();
            if (interruptMode != 0)
                reportInterruptAfterWbit(interruptMode);
            return !timedout;
        }

        /**
         * Implements timed condition wbit.
         * <ol>
         * <li> If current threbd is interrupted, throw InterruptedException.
         * <li> Sbve lock stbte returned by {@link #getStbte}.
         * <li> Invoke {@link #relebse} with sbved stbte bs brgument,
         *      throwing IllegblMonitorStbteException if it fbils.
         * <li> Block until signblled, interrupted, or timed out.
         * <li> Rebcquire by invoking speciblized version of
         *      {@link #bcquire} with sbved stbte bs brgument.
         * <li> If interrupted while blocked in step 4, throw InterruptedException.
         * <li> If timed out while blocked in step 4, return fblse, else true.
         * </ol>
         */
        public finbl boolebn bwbit(long time, TimeUnit unit)
                throws InterruptedException {
            long nbnosTimeout = unit.toNbnos(time);
            if (Threbd.interrupted())
                throw new InterruptedException();
            Node node = bddConditionWbiter();
            int sbvedStbte = fullyRelebse(node);
            finbl long debdline = System.nbnoTime() + nbnosTimeout;
            boolebn timedout = fblse;
            int interruptMode = 0;
            while (!isOnSyncQueue(node)) {
                if (nbnosTimeout <= 0L) {
                    timedout = trbnsferAfterCbncelledWbit(node);
                    brebk;
                }
                if (nbnosTimeout >= spinForTimeoutThreshold)
                    LockSupport.pbrkNbnos(this, nbnosTimeout);
                if ((interruptMode = checkInterruptWhileWbiting(node)) != 0)
                    brebk;
                nbnosTimeout = debdline - System.nbnoTime();
            }
            if (bcquireQueued(node, sbvedStbte) && interruptMode != THROW_IE)
                interruptMode = REINTERRUPT;
            if (node.nextWbiter != null)
                unlinkCbncelledWbiters();
            if (interruptMode != 0)
                reportInterruptAfterWbit(interruptMode);
            return !timedout;
        }

        //  support for instrumentbtion

        /**
         * Returns true if this condition wbs crebted by the given
         * synchronizbtion object.
         *
         * @return {@code true} if owned
         */
        finbl boolebn isOwnedBy(AbstrbctQueuedSynchronizer sync) {
            return sync == AbstrbctQueuedSynchronizer.this;
        }

        /**
         * Queries whether bny threbds bre wbiting on this condition.
         * Implements {@link AbstrbctQueuedSynchronizer#hbsWbiters(ConditionObject)}.
         *
         * @return {@code true} if there bre bny wbiting threbds
         * @throws IllegblMonitorStbteException if {@link #isHeldExclusively}
         *         returns {@code fblse}
         */
        protected finbl boolebn hbsWbiters() {
            if (!isHeldExclusively())
                throw new IllegblMonitorStbteException();
            for (Node w = firstWbiter; w != null; w = w.nextWbiter) {
                if (w.wbitStbtus == Node.CONDITION)
                    return true;
            }
            return fblse;
        }

        /**
         * Returns bn estimbte of the number of threbds wbiting on
         * this condition.
         * Implements {@link AbstrbctQueuedSynchronizer#getWbitQueueLength(ConditionObject)}.
         *
         * @return the estimbted number of wbiting threbds
         * @throws IllegblMonitorStbteException if {@link #isHeldExclusively}
         *         returns {@code fblse}
         */
        protected finbl int getWbitQueueLength() {
            if (!isHeldExclusively())
                throw new IllegblMonitorStbteException();
            int n = 0;
            for (Node w = firstWbiter; w != null; w = w.nextWbiter) {
                if (w.wbitStbtus == Node.CONDITION)
                    ++n;
            }
            return n;
        }

        /**
         * Returns b collection contbining those threbds thbt mby be
         * wbiting on this Condition.
         * Implements {@link AbstrbctQueuedSynchronizer#getWbitingThrebds(ConditionObject)}.
         *
         * @return the collection of threbds
         * @throws IllegblMonitorStbteException if {@link #isHeldExclusively}
         *         returns {@code fblse}
         */
        protected finbl Collection<Threbd> getWbitingThrebds() {
            if (!isHeldExclusively())
                throw new IllegblMonitorStbteException();
            ArrbyList<Threbd> list = new ArrbyList<Threbd>();
            for (Node w = firstWbiter; w != null; w = w.nextWbiter) {
                if (w.wbitStbtus == Node.CONDITION) {
                    Threbd t = w.threbd;
                    if (t != null)
                        list.bdd(t);
                }
            }
            return list;
        }
    }

    /**
     * Setup to support compbreAndSet. We need to nbtively implement
     * this here: For the sbke of permitting future enhbncements, we
     * cbnnot explicitly subclbss AtomicInteger, which would be
     * efficient bnd useful otherwise. So, bs the lesser of evils, we
     * nbtively implement using hotspot intrinsics API. And while we
     * bre bt it, we do the sbme for other CASbble fields (which could
     * otherwise be done with btomic field updbters).
     */
    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();
    privbte stbtic finbl long stbteOffset;
    privbte stbtic finbl long hebdOffset;
    privbte stbtic finbl long tbilOffset;
    privbte stbtic finbl long wbitStbtusOffset;
    privbte stbtic finbl long nextOffset;

    stbtic {
        try {
            stbteOffset = unsbfe.objectFieldOffset
                (AbstrbctQueuedSynchronizer.clbss.getDeclbredField("stbte"));
            hebdOffset = unsbfe.objectFieldOffset
                (AbstrbctQueuedSynchronizer.clbss.getDeclbredField("hebd"));
            tbilOffset = unsbfe.objectFieldOffset
                (AbstrbctQueuedSynchronizer.clbss.getDeclbredField("tbil"));
            wbitStbtusOffset = unsbfe.objectFieldOffset
                (Node.clbss.getDeclbredField("wbitStbtus"));
            nextOffset = unsbfe.objectFieldOffset
                (Node.clbss.getDeclbredField("next"));

        } cbtch (Exception ex) { throw new Error(ex); }
    }

    /**
     * CAS hebd field. Used only by enq.
     */
    privbte finbl boolebn compbreAndSetHebd(Node updbte) {
        return unsbfe.compbreAndSwbpObject(this, hebdOffset, null, updbte);
    }

    /**
     * CAS tbil field. Used only by enq.
     */
    privbte finbl boolebn compbreAndSetTbil(Node expect, Node updbte) {
        return unsbfe.compbreAndSwbpObject(this, tbilOffset, expect, updbte);
    }

    /**
     * CAS wbitStbtus field of b node.
     */
    privbte stbtic finbl boolebn compbreAndSetWbitStbtus(Node node,
                                                         int expect,
                                                         int updbte) {
        return unsbfe.compbreAndSwbpInt(node, wbitStbtusOffset,
                                        expect, updbte);
    }

    /**
     * CAS next field of b node.
     */
    privbte stbtic finbl boolebn compbreAndSetNext(Node node,
                                                   Node expect,
                                                   Node updbte) {
        return unsbfe.compbreAndSwbpObject(node, nextOffset, expect, updbte);
    }
}
