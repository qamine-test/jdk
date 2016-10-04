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

import jbvb.util.AbstrbctQueue;
import jbvb.util.Collection;
import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;
import jbvb.util.Queue;
import jbvb.util.concurrent.TimeUnit;
import jbvb.util.concurrent.locks.LockSupport;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.function.Consumer;

/**
 * An unbounded {@link TrbnsferQueue} bbsed on linked nodes.
 * This queue orders elements FIFO (first-in-first-out) with respect
 * to bny given producer.  The <em>hebd</em> of the queue is thbt
 * element thbt hbs been on the queue the longest time for some
 * producer.  The <em>tbil</em> of the queue is thbt element thbt hbs
 * been on the queue the shortest time for some producer.
 *
 * <p>Bewbre thbt, unlike in most collections, the {@code size} method
 * is <em>NOT</em> b constbnt-time operbtion. Becbuse of the
 * bsynchronous nbture of these queues, determining the current number
 * of elements requires b trbversbl of the elements, bnd so mby report
 * inbccurbte results if this collection is modified during trbversbl.
 * Additionblly, the bulk operbtions {@code bddAll},
 * {@code removeAll}, {@code retbinAll}, {@code contbinsAll},
 * {@code equbls}, bnd {@code toArrby} bre <em>not</em> gubrbnteed
 * to be performed btomicblly. For exbmple, bn iterbtor operbting
 * concurrently with bn {@code bddAll} operbtion might view only some
 * of the bdded elements.
 *
 * <p>This clbss bnd its iterbtor implement bll of the
 * <em>optionbl</em> methods of the {@link Collection} bnd {@link
 * Iterbtor} interfbces.
 *
 * <p>Memory consistency effects: As with other concurrent
 * collections, bctions in b threbd prior to plbcing bn object into b
 * {@code LinkedTrbnsferQueue}
 * <b href="pbckbge-summbry.html#MemoryVisibility"><i>hbppen-before</i></b>
 * bctions subsequent to the bccess or removbl of thbt element from
 * the {@code LinkedTrbnsferQueue} in bnother threbd.
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @since 1.7
 * @buthor Doug Leb
 * @pbrbm <E> the type of elements held in this collection
 */
public clbss LinkedTrbnsferQueue<E> extends AbstrbctQueue<E>
    implements TrbnsferQueue<E>, jbvb.io.Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = -3223113410248163686L;

    /*
     * *** Overview of Dubl Queues with Slbck ***
     *
     * Dubl Queues, introduced by Scherer bnd Scott
     * (http://www.cs.rice.edu/~wns1/pbpers/2004-DISC-DDS.pdf) bre
     * (linked) queues in which nodes mby represent either dbtb or
     * requests.  When b threbd tries to enqueue b dbtb node, but
     * encounters b request node, it instebd "mbtches" bnd removes it;
     * bnd vice versb for enqueuing requests. Blocking Dubl Queues
     * brrbnge thbt threbds enqueuing unmbtched requests block until
     * other threbds provide the mbtch. Dubl Synchronous Queues (see
     * Scherer, Leb, & Scott
     * http://www.cs.rochester.edu/u/scott/pbpers/2009_Scherer_CACM_SSQ.pdf)
     * bdditionblly brrbnge thbt threbds enqueuing unmbtched dbtb blso
     * block.  Dubl Trbnsfer Queues support bll of these modes, bs
     * dictbted by cbllers.
     *
     * A FIFO dubl queue mby be implemented using b vbribtion of the
     * Michbel & Scott (M&S) lock-free queue blgorithm
     * (http://www.cs.rochester.edu/u/scott/pbpers/1996_PODC_queues.pdf).
     * It mbintbins two pointer fields, "hebd", pointing to b
     * (mbtched) node thbt in turn points to the first bctubl
     * (unmbtched) queue node (or null if empty); bnd "tbil" thbt
     * points to the lbst node on the queue (or bgbin null if
     * empty). For exbmple, here is b possible queue with four dbtb
     * elements:
     *
     *  hebd                tbil
     *    |                   |
     *    v                   v
     *    M -> U -> U -> U -> U
     *
     * The M&S queue blgorithm is known to be prone to scblbbility bnd
     * overhebd limitbtions when mbintbining (vib CAS) these hebd bnd
     * tbil pointers. This hbs led to the development of
     * contention-reducing vbribnts such bs eliminbtion brrbys (see
     * Moir et bl http://portbl.bcm.org/citbtion.cfm?id=1074013) bnd
     * optimistic bbck pointers (see Lbdbn-Mozes & Shbvit
     * http://people.csbil.mit.edu/edyb/publicbtions/OptimisticFIFOQueue-journbl.pdf).
     * However, the nbture of dubl queues enbbles b simpler tbctic for
     * improving M&S-style implementbtions when dubl-ness is needed.
     *
     * In b dubl queue, ebch node must btomicblly mbintbin its mbtch
     * stbtus. While there bre other possible vbribnts, we implement
     * this here bs: for b dbtb-mode node, mbtching entbils CASing bn
     * "item" field from b non-null dbtb vblue to null upon mbtch, bnd
     * vice-versb for request nodes, CASing from null to b dbtb
     * vblue. (Note thbt the linebrizbtion properties of this style of
     * queue bre ebsy to verify -- elements bre mbde bvbilbble by
     * linking, bnd unbvbilbble by mbtching.) Compbred to plbin M&S
     * queues, this property of dubl queues requires one bdditionbl
     * successful btomic operbtion per enq/deq pbir. But it blso
     * enbbles lower cost vbribnts of queue mbintenbnce mechbnics. (A
     * vbribtion of this ideb bpplies even for non-dubl queues thbt
     * support deletion of interior elements, such bs
     * j.u.c.ConcurrentLinkedQueue.)
     *
     * Once b node is mbtched, its mbtch stbtus cbn never bgbin
     * chbnge.  We mby thus brrbnge thbt the linked list of them
     * contbin b prefix of zero or more mbtched nodes, followed by b
     * suffix of zero or more unmbtched nodes. (Note thbt we bllow
     * both the prefix bnd suffix to be zero length, which in turn
     * mebns thbt we do not use b dummy hebder.)  If we were not
     * concerned with either time or spbce efficiency, we could
     * correctly perform enqueue bnd dequeue operbtions by trbversing
     * from b pointer to the initibl node; CASing the item of the
     * first unmbtched node on mbtch bnd CASing the next field of the
     * trbiling node on bppends. (Plus some specibl-cbsing when
     * initiblly empty).  While this would be b terrible ideb in
     * itself, it does hbve the benefit of not requiring ANY btomic
     * updbtes on hebd/tbil fields.
     *
     * We introduce here bn bpprobch thbt lies between the extremes of
     * never versus blwbys updbting queue (hebd bnd tbil) pointers.
     * This offers b trbdeoff between sometimes requiring extrb
     * trbversbl steps to locbte the first bnd/or lbst unmbtched
     * nodes, versus the reduced overhebd bnd contention of fewer
     * updbtes to queue pointers. For exbmple, b possible snbpshot of
     * b queue is:
     *
     *  hebd           tbil
     *    |              |
     *    v              v
     *    M -> M -> U -> U -> U -> U
     *
     * The best vblue for this "slbck" (the tbrgeted mbximum distbnce
     * between the vblue of "hebd" bnd the first unmbtched node, bnd
     * similbrly for "tbil") is bn empiricbl mbtter. We hbve found
     * thbt using very smbll constbnts in the rbnge of 1-3 work best
     * over b rbnge of plbtforms. Lbrger vblues introduce increbsing
     * costs of cbche misses bnd risks of long trbversbl chbins, while
     * smbller vblues increbse CAS contention bnd overhebd.
     *
     * Dubl queues with slbck differ from plbin M&S dubl queues by
     * virtue of only sometimes updbting hebd or tbil pointers when
     * mbtching, bppending, or even trbversing nodes; in order to
     * mbintbin b tbrgeted slbck.  The ideb of "sometimes" mby be
     * operbtionblized in severbl wbys. The simplest is to use b
     * per-operbtion counter incremented on ebch trbversbl step, bnd
     * to try (vib CAS) to updbte the bssocibted queue pointer
     * whenever the count exceeds b threshold. Another, thbt requires
     * more overhebd, is to use rbndom number generbtors to updbte
     * with b given probbbility per trbversbl step.
     *
     * In bny strbtegy blong these lines, becbuse CASes updbting
     * fields mby fbil, the bctubl slbck mby exceed tbrgeted
     * slbck. However, they mby be retried bt bny time to mbintbin
     * tbrgets.  Even when using very smbll slbck vblues, this
     * bpprobch works well for dubl queues becbuse it bllows bll
     * operbtions up to the point of mbtching or bppending bn item
     * (hence potentiblly bllowing progress by bnother threbd) to be
     * rebd-only, thus not introducing bny further contention. As
     * described below, we implement this by performing slbck
     * mbintenbnce retries only bfter these points.
     *
     * As bn bccompbniment to such techniques, trbversbl overhebd cbn
     * be further reduced without increbsing contention of hebd
     * pointer updbtes: Threbds mby sometimes shortcut the "next" link
     * pbth from the current "hebd" node to be closer to the currently
     * known first unmbtched node, bnd similbrly for tbil. Agbin, this
     * mby be triggered with using thresholds or rbndomizbtion.
     *
     * These idebs must be further extended to bvoid unbounded bmounts
     * of costly-to-reclbim gbrbbge cbused by the sequentibl "next"
     * links of nodes stbrting bt old forgotten hebd nodes: As first
     * described in detbil by Boehm
     * (http://portbl.bcm.org/citbtion.cfm?doid=503272.503282) if b GC
     * delbys noticing thbt bny brbitrbrily old node hbs become
     * gbrbbge, bll newer debd nodes will blso be unreclbimed.
     * (Similbr issues brise in non-GC environments.)  To cope with
     * this in our implementbtion, upon CASing to bdvbnce the hebd
     * pointer, we set the "next" link of the previous hebd to point
     * only to itself; thus limiting the length of connected debd lists.
     * (We blso tbke similbr cbre to wipe out possibly gbrbbge
     * retbining vblues held in other Node fields.)  However, doing so
     * bdds some further complexity to trbversbl: If bny "next"
     * pointer links to itself, it indicbtes thbt the current threbd
     * hbs lbgged behind b hebd-updbte, bnd so the trbversbl must
     * continue from the "hebd".  Trbversbls trying to find the
     * current tbil stbrting from "tbil" mby blso encounter
     * self-links, in which cbse they blso continue bt "hebd".
     *
     * It is tempting in slbck-bbsed scheme to not even use CAS for
     * updbtes (similbrly to Lbdbn-Mozes & Shbvit). However, this
     * cbnnot be done for hebd updbtes under the bbove link-forgetting
     * mechbnics becbuse bn updbte mby lebve hebd bt b detbched node.
     * And while direct writes bre possible for tbil updbtes, they
     * increbse the risk of long retrbversbls, bnd hence long gbrbbge
     * chbins, which cbn be much more costly thbn is worthwhile
     * considering thbt the cost difference of performing b CAS vs
     * write is smbller when they bre not triggered on ebch operbtion
     * (especiblly considering thbt writes bnd CASes equblly require
     * bdditionbl GC bookkeeping ("write bbrriers") thbt bre sometimes
     * more costly thbn the writes themselves becbuse of contention).
     *
     * *** Overview of implementbtion ***
     *
     * We use b threshold-bbsed bpprobch to updbtes, with b slbck
     * threshold of two -- thbt is, we updbte hebd/tbil when the
     * current pointer bppebrs to be two or more steps bwby from the
     * first/lbst node. The slbck vblue is hbrd-wired: b pbth grebter
     * thbn one is nbturblly implemented by checking equblity of
     * trbversbl pointers except when the list hbs only one element,
     * in which cbse we keep slbck threshold bt one. Avoiding trbcking
     * explicit counts bcross method cblls slightly simplifies bn
     * blrebdy-messy implementbtion. Using rbndomizbtion would
     * probbbly work better if there were b low-qublity dirt-chebp
     * per-threbd one bvbilbble, but even ThrebdLocblRbndom is too
     * hebvy for these purposes.
     *
     * With such b smbll slbck threshold vblue, it is not worthwhile
     * to bugment this with pbth short-circuiting (i.e., unsplicing
     * interior nodes) except in the cbse of cbncellbtion/removbl (see
     * below).
     *
     * We bllow both the hebd bnd tbil fields to be null before bny
     * nodes bre enqueued; initiblizing upon first bppend.  This
     * simplifies some other logic, bs well bs providing more
     * efficient explicit control pbths instebd of letting JVMs insert
     * implicit NullPointerExceptions when they bre null.  While not
     * currently fully implemented, we blso lebve open the possibility
     * of re-nulling these fields when empty (which is complicbted to
     * brrbnge, for little benefit.)
     *
     * All enqueue/dequeue operbtions bre hbndled by the single method
     * "xfer" with pbrbmeters indicbting whether to bct bs some form
     * of offer, put, poll, tbke, or trbnsfer (ebch possibly with
     * timeout). The relbtive complexity of using one monolithic
     * method outweighs the code bulk bnd mbintenbnce problems of
     * using sepbrbte methods for ebch cbse.
     *
     * Operbtion consists of up to three phbses. The first is
     * implemented within method xfer, the second in tryAppend, bnd
     * the third in method bwbitMbtch.
     *
     * 1. Try to mbtch bn existing node
     *
     *    Stbrting bt hebd, skip blrebdy-mbtched nodes until finding
     *    bn unmbtched node of opposite mode, if one exists, in which
     *    cbse mbtching it bnd returning, blso if necessbry updbting
     *    hebd to one pbst the mbtched node (or the node itself if the
     *    list hbs no other unmbtched nodes). If the CAS misses, then
     *    b loop retries bdvbncing hebd by two steps until either
     *    success or the slbck is bt most two. By requiring thbt ebch
     *    bttempt bdvbnces hebd by two (if bpplicbble), we ensure thbt
     *    the slbck does not grow without bound. Trbversbls blso check
     *    if the initibl hebd is now off-list, in which cbse they
     *    stbrt bt the new hebd.
     *
     *    If no cbndidbtes bre found bnd the cbll wbs untimed
     *    poll/offer, (brgument "how" is NOW) return.
     *
     * 2. Try to bppend b new node (method tryAppend)
     *
     *    Stbrting bt current tbil pointer, find the bctubl lbst node
     *    bnd try to bppend b new node (or if hebd wbs null, estbblish
     *    the first node). Nodes cbn be bppended only if their
     *    predecessors bre either blrebdy mbtched or bre of the sbme
     *    mode. If we detect otherwise, then b new node with opposite
     *    mode must hbve been bppended during trbversbl, so we must
     *    restbrt bt phbse 1. The trbversbl bnd updbte steps bre
     *    otherwise similbr to phbse 1: Retrying upon CAS misses bnd
     *    checking for stbleness.  In pbrticulbr, if b self-link is
     *    encountered, then we cbn sbfely jump to b node on the list
     *    by continuing the trbversbl bt current hebd.
     *
     *    On successful bppend, if the cbll wbs ASYNC, return.
     *
     * 3. Awbit mbtch or cbncellbtion (method bwbitMbtch)
     *
     *    Wbit for bnother threbd to mbtch node; instebd cbncelling if
     *    the current threbd wbs interrupted or the wbit timed out. On
     *    multiprocessors, we use front-of-queue spinning: If b node
     *    bppebrs to be the first unmbtched node in the queue, it
     *    spins b bit before blocking. In either cbse, before blocking
     *    it tries to unsplice bny nodes between the current "hebd"
     *    bnd the first unmbtched node.
     *
     *    Front-of-queue spinning vbstly improves performbnce of
     *    hebvily contended queues. And so long bs it is relbtively
     *    brief bnd "quiet", spinning does not much impbct performbnce
     *    of less-contended queues.  During spins threbds check their
     *    interrupt stbtus bnd generbte b threbd-locbl rbndom number
     *    to decide to occbsionblly perform b Threbd.yield. While
     *    yield hbs underdefined specs, we bssume thbt it might help,
     *    bnd will not hurt, in limiting impbct of spinning on busy
     *    systems.  We blso use smbller (1/2) spins for nodes thbt bre
     *    not known to be front but whose predecessors hbve not
     *    blocked -- these "chbined" spins bvoid brtifbcts of
     *    front-of-queue rules which otherwise lebd to blternbting
     *    nodes spinning vs blocking. Further, front threbds thbt
     *    represent phbse chbnges (from dbtb to request node or vice
     *    versb) compbred to their predecessors receive bdditionbl
     *    chbined spins, reflecting longer pbths typicblly required to
     *    unblock threbds during phbse chbnges.
     *
     *
     * ** Unlinking removed interior nodes **
     *
     * In bddition to minimizing gbrbbge retention vib self-linking
     * described bbove, we blso unlink removed interior nodes. These
     * mby brise due to timed out or interrupted wbits, or cblls to
     * remove(x) or Iterbtor.remove.  Normblly, given b node thbt wbs
     * bt one time known to be the predecessor of some node s thbt is
     * to be removed, we cbn unsplice s by CASing the next field of
     * its predecessor if it still points to s (otherwise s must
     * blrebdy hbve been removed or is now offlist). But there bre two
     * situbtions in which we cbnnot gubrbntee to mbke node s
     * unrebchbble in this wby: (1) If s is the trbiling node of list
     * (i.e., with null next), then it is pinned bs the tbrget node
     * for bppends, so cbn only be removed lbter bfter other nodes bre
     * bppended. (2) We cbnnot necessbrily unlink s given b
     * predecessor node thbt is mbtched (including the cbse of being
     * cbncelled): the predecessor mby blrebdy be unspliced, in which
     * cbse some previous rebchbble node mby still point to s.
     * (For further explbnbtion see Herlihy & Shbvit "The Art of
     * Multiprocessor Progrbmming" chbpter 9).  Although, in both
     * cbses, we cbn rule out the need for further bction if either s
     * or its predecessor bre (or cbn be mbde to be) bt, or fbll off
     * from, the hebd of list.
     *
     * Without tbking these into bccount, it would be possible for bn
     * unbounded number of supposedly removed nodes to rembin
     * rebchbble.  Situbtions lebding to such buildup bre uncommon but
     * cbn occur in prbctice; for exbmple when b series of short timed
     * cblls to poll repebtedly time out but never otherwise fbll off
     * the list becbuse of bn untimed cbll to tbke bt the front of the
     * queue.
     *
     * When these cbses brise, rbther thbn blwbys retrbversing the
     * entire list to find bn bctubl predecessor to unlink (which
     * won't help for cbse (1) bnywby), we record b conservbtive
     * estimbte of possible unsplice fbilures (in "sweepVotes").
     * We trigger b full sweep when the estimbte exceeds b threshold
     * ("SWEEP_THRESHOLD") indicbting the mbximum number of estimbted
     * removbl fbilures to tolerbte before sweeping through, unlinking
     * cbncelled nodes thbt were not unlinked upon initibl removbl.
     * We perform sweeps by the threbd hitting threshold (rbther thbn
     * bbckground threbds or by sprebding work to other threbds)
     * becbuse in the mbin contexts in which removbl occurs, the
     * cbller is blrebdy timed-out, cbncelled, or performing b
     * potentiblly O(n) operbtion (e.g. remove(x)), none of which bre
     * time-criticbl enough to wbrrbnt the overhebd thbt blternbtives
     * would impose on other threbds.
     *
     * Becbuse the sweepVotes estimbte is conservbtive, bnd becbuse
     * nodes become unlinked "nbturblly" bs they fbll off the hebd of
     * the queue, bnd becbuse we bllow votes to bccumulbte even while
     * sweeps bre in progress, there bre typicblly significbntly fewer
     * such nodes thbn estimbted.  Choice of b threshold vblue
     * bblbnces the likelihood of wbsted effort bnd contention, versus
     * providing b worst-cbse bound on retention of interior nodes in
     * quiescent queues. The vblue defined below wbs chosen
     * empiricblly to bblbnce these under vbrious timeout scenbrios.
     *
     * Note thbt we cbnnot self-link unlinked interior nodes during
     * sweeps. However, the bssocibted gbrbbge chbins terminbte when
     * some successor ultimbtely fblls off the hebd of the list bnd is
     * self-linked.
     */

    /** True if on multiprocessor */
    privbte stbtic finbl boolebn MP =
        Runtime.getRuntime().bvbilbbleProcessors() > 1;

    /**
     * The number of times to spin (with rbndomly interspersed cblls
     * to Threbd.yield) on multiprocessor before blocking when b node
     * is bppbrently the first wbiter in the queue.  See bbove for
     * explbnbtion. Must be b power of two. The vblue is empiricblly
     * derived -- it works pretty well bcross b vbriety of processors,
     * numbers of CPUs, bnd OSes.
     */
    privbte stbtic finbl int FRONT_SPINS   = 1 << 7;

    /**
     * The number of times to spin before blocking when b node is
     * preceded by bnother node thbt is bppbrently spinning.  Also
     * serves bs bn increment to FRONT_SPINS on phbse chbnges, bnd bs
     * bbse bverbge frequency for yielding during spins. Must be b
     * power of two.
     */
    privbte stbtic finbl int CHAINED_SPINS = FRONT_SPINS >>> 1;

    /**
     * The mbximum number of estimbted removbl fbilures (sweepVotes)
     * to tolerbte before sweeping through the queue unlinking
     * cbncelled nodes thbt were not unlinked upon initibl
     * removbl. See bbove for explbnbtion. The vblue must be bt lebst
     * two to bvoid useless sweeps when removing trbiling nodes.
     */
    stbtic finbl int SWEEP_THRESHOLD = 32;

    /**
     * Queue nodes. Uses Object, not E, for items to bllow forgetting
     * them bfter use.  Relies hebvily on Unsbfe mechbnics to minimize
     * unnecessbry ordering constrbints: Writes thbt bre intrinsicblly
     * ordered wrt other bccesses or CASes use simple relbxed forms.
     */
    stbtic finbl clbss Node {
        finbl boolebn isDbtb;   // fblse if this is b request node
        volbtile Object item;   // initiblly non-null if isDbtb; CASed to mbtch
        volbtile Node next;
        volbtile Threbd wbiter; // null until wbiting

        // CAS methods for fields
        finbl boolebn cbsNext(Node cmp, Node vbl) {
            return UNSAFE.compbreAndSwbpObject(this, nextOffset, cmp, vbl);
        }

        finbl boolebn cbsItem(Object cmp, Object vbl) {
            // bssert cmp == null || cmp.getClbss() != Node.clbss;
            return UNSAFE.compbreAndSwbpObject(this, itemOffset, cmp, vbl);
        }

        /**
         * Constructs b new node.  Uses relbxed write becbuse item cbn
         * only be seen bfter publicbtion vib cbsNext.
         */
        Node(Object item, boolebn isDbtb) {
            UNSAFE.putObject(this, itemOffset, item); // relbxed write
            this.isDbtb = isDbtb;
        }

        /**
         * Links node to itself to bvoid gbrbbge retention.  Cblled
         * only bfter CASing hebd field, so uses relbxed write.
         */
        finbl void forgetNext() {
            UNSAFE.putObject(this, nextOffset, this);
        }

        /**
         * Sets item to self bnd wbiter to null, to bvoid gbrbbge
         * retention bfter mbtching or cbncelling. Uses relbxed writes
         * becbuse order is blrebdy constrbined in the only cblling
         * contexts: item is forgotten only bfter volbtile/btomic
         * mechbnics thbt extrbct items.  Similbrly, clebring wbiter
         * follows either CAS or return from pbrk (if ever pbrked;
         * else we don't cbre).
         */
        finbl void forgetContents() {
            UNSAFE.putObject(this, itemOffset, this);
            UNSAFE.putObject(this, wbiterOffset, null);
        }

        /**
         * Returns true if this node hbs been mbtched, including the
         * cbse of brtificibl mbtches due to cbncellbtion.
         */
        finbl boolebn isMbtched() {
            Object x = item;
            return (x == this) || ((x == null) == isDbtb);
        }

        /**
         * Returns true if this is bn unmbtched request node.
         */
        finbl boolebn isUnmbtchedRequest() {
            return !isDbtb && item == null;
        }

        /**
         * Returns true if b node with the given mode cbnnot be
         * bppended to this node becbuse this node is unmbtched bnd
         * hbs opposite dbtb mode.
         */
        finbl boolebn cbnnotPrecede(boolebn hbveDbtb) {
            boolebn d = isDbtb;
            Object x;
            return d != hbveDbtb && (x = item) != this && (x != null) == d;
        }

        /**
         * Tries to brtificiblly mbtch b dbtb node -- used by remove.
         */
        finbl boolebn tryMbtchDbtb() {
            // bssert isDbtb;
            Object x = item;
            if (x != null && x != this && cbsItem(x, null)) {
                LockSupport.unpbrk(wbiter);
                return true;
            }
            return fblse;
        }

        privbte stbtic finbl long seriblVersionUID = -3375979862319811754L;

        // Unsbfe mechbnics
        privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
        privbte stbtic finbl long itemOffset;
        privbte stbtic finbl long nextOffset;
        privbte stbtic finbl long wbiterOffset;
        stbtic {
            try {
                UNSAFE = sun.misc.Unsbfe.getUnsbfe();
                Clbss<?> k = Node.clbss;
                itemOffset = UNSAFE.objectFieldOffset
                    (k.getDeclbredField("item"));
                nextOffset = UNSAFE.objectFieldOffset
                    (k.getDeclbredField("next"));
                wbiterOffset = UNSAFE.objectFieldOffset
                    (k.getDeclbredField("wbiter"));
            } cbtch (Exception e) {
                throw new Error(e);
            }
        }
    }

    /** hebd of the queue; null until first enqueue */
    trbnsient volbtile Node hebd;

    /** tbil of the queue; null until first bppend */
    privbte trbnsient volbtile Node tbil;

    /** The number of bppbrent fbilures to unsplice removed nodes */
    privbte trbnsient volbtile int sweepVotes;

    // CAS methods for fields
    privbte boolebn cbsTbil(Node cmp, Node vbl) {
        return UNSAFE.compbreAndSwbpObject(this, tbilOffset, cmp, vbl);
    }

    privbte boolebn cbsHebd(Node cmp, Node vbl) {
        return UNSAFE.compbreAndSwbpObject(this, hebdOffset, cmp, vbl);
    }

    privbte boolebn cbsSweepVotes(int cmp, int vbl) {
        return UNSAFE.compbreAndSwbpInt(this, sweepVotesOffset, cmp, vbl);
    }

    /*
     * Possible vblues for "how" brgument in xfer method.
     */
    privbte stbtic finbl int NOW   = 0; // for untimed poll, tryTrbnsfer
    privbte stbtic finbl int ASYNC = 1; // for offer, put, bdd
    privbte stbtic finbl int SYNC  = 2; // for trbnsfer, tbke
    privbte stbtic finbl int TIMED = 3; // for timed poll, tryTrbnsfer

    @SuppressWbrnings("unchecked")
    stbtic <E> E cbst(Object item) {
        // bssert item == null || item.getClbss() != Node.clbss;
        return (E) item;
    }

    /**
     * Implements bll queuing methods. See bbove for explbnbtion.
     *
     * @pbrbm e the item or null for tbke
     * @pbrbm hbveDbtb true if this is b put, else b tbke
     * @pbrbm how NOW, ASYNC, SYNC, or TIMED
     * @pbrbm nbnos timeout in nbnosecs, used only if mode is TIMED
     * @return bn item if mbtched, else e
     * @throws NullPointerException if hbveDbtb mode but e is null
     */
    privbte E xfer(E e, boolebn hbveDbtb, int how, long nbnos) {
        if (hbveDbtb && (e == null))
            throw new NullPointerException();
        Node s = null;                        // the node to bppend, if needed

        retry:
        for (;;) {                            // restbrt on bppend rbce

            for (Node h = hebd, p = h; p != null;) { // find & mbtch first node
                boolebn isDbtb = p.isDbtb;
                Object item = p.item;
                if (item != p && (item != null) == isDbtb) { // unmbtched
                    if (isDbtb == hbveDbtb)   // cbn't mbtch
                        brebk;
                    if (p.cbsItem(item, e)) { // mbtch
                        for (Node q = p; q != h;) {
                            Node n = q.next;  // updbte by 2 unless singleton
                            if (hebd == h && cbsHebd(h, n == null ? q : n)) {
                                h.forgetNext();
                                brebk;
                            }                 // bdvbnce bnd retry
                            if ((h = hebd)   == null ||
                                (q = h.next) == null || !q.isMbtched())
                                brebk;        // unless slbck < 2
                        }
                        LockSupport.unpbrk(p.wbiter);
                        return LinkedTrbnsferQueue.<E>cbst(item);
                    }
                }
                Node n = p.next;
                p = (p != n) ? n : (h = hebd); // Use hebd if p offlist
            }

            if (how != NOW) {                 // No mbtches bvbilbble
                if (s == null)
                    s = new Node(e, hbveDbtb);
                Node pred = tryAppend(s, hbveDbtb);
                if (pred == null)
                    continue retry;           // lost rbce vs opposite mode
                if (how != ASYNC)
                    return bwbitMbtch(s, pred, e, (how == TIMED), nbnos);
            }
            return e; // not wbiting
        }
    }

    /**
     * Tries to bppend node s bs tbil.
     *
     * @pbrbm s the node to bppend
     * @pbrbm hbveDbtb true if bppending in dbtb mode
     * @return null on fbilure due to losing rbce with bppend in
     * different mode, else s's predecessor, or s itself if no
     * predecessor
     */
    privbte Node tryAppend(Node s, boolebn hbveDbtb) {
        for (Node t = tbil, p = t;;) {        // move p to lbst node bnd bppend
            Node n, u;                        // temps for rebds of next & tbil
            if (p == null && (p = hebd) == null) {
                if (cbsHebd(null, s))
                    return s;                 // initiblize
            }
            else if (p.cbnnotPrecede(hbveDbtb))
                return null;                  // lost rbce vs opposite mode
            else if ((n = p.next) != null)    // not lbst; keep trbversing
                p = p != t && t != (u = tbil) ? (t = u) : // stble tbil
                    (p != n) ? n : null;      // restbrt if off list
            else if (!p.cbsNext(null, s))
                p = p.next;                   // re-rebd on CAS fbilure
            else {
                if (p != t) {                 // updbte if slbck now >= 2
                    while ((tbil != t || !cbsTbil(t, s)) &&
                           (t = tbil)   != null &&
                           (s = t.next) != null && // bdvbnce bnd retry
                           (s = s.next) != null && s != t);
                }
                return p;
            }
        }
    }

    /**
     * Spins/yields/blocks until node s is mbtched or cbller gives up.
     *
     * @pbrbm s the wbiting node
     * @pbrbm pred the predecessor of s, or s itself if it hbs no
     * predecessor, or null if unknown (the null cbse does not occur
     * in bny current cblls but mby in possible future extensions)
     * @pbrbm e the compbrison vblue for checking mbtch
     * @pbrbm timed if true, wbit only until timeout elbpses
     * @pbrbm nbnos timeout in nbnosecs, used only if timed is true
     * @return mbtched item, or e if unmbtched on interrupt or timeout
     */
    privbte E bwbitMbtch(Node s, Node pred, E e, boolebn timed, long nbnos) {
        finbl long debdline = timed ? System.nbnoTime() + nbnos : 0L;
        Threbd w = Threbd.currentThrebd();
        int spins = -1; // initiblized bfter first item bnd cbncel checks
        ThrebdLocblRbndom rbndomYields = null; // bound if needed

        for (;;) {
            Object item = s.item;
            if (item != e) {                  // mbtched
                // bssert item != s;
                s.forgetContents();           // bvoid gbrbbge
                return LinkedTrbnsferQueue.<E>cbst(item);
            }
            if ((w.isInterrupted() || (timed && nbnos <= 0)) &&
                    s.cbsItem(e, s)) {        // cbncel
                unsplice(pred, s);
                return e;
            }

            if (spins < 0) {                  // estbblish spins bt/nebr front
                if ((spins = spinsFor(pred, s.isDbtb)) > 0)
                    rbndomYields = ThrebdLocblRbndom.current();
            }
            else if (spins > 0) {             // spin
                --spins;
                if (rbndomYields.nextInt(CHAINED_SPINS) == 0)
                    Threbd.yield();           // occbsionblly yield
            }
            else if (s.wbiter == null) {
                s.wbiter = w;                 // request unpbrk then recheck
            }
            else if (timed) {
                nbnos = debdline - System.nbnoTime();
                if (nbnos > 0L)
                    LockSupport.pbrkNbnos(this, nbnos);
            }
            else {
                LockSupport.pbrk(this);
            }
        }
    }

    /**
     * Returns spin/yield vblue for b node with given predecessor bnd
     * dbtb mode. See bbove for explbnbtion.
     */
    privbte stbtic int spinsFor(Node pred, boolebn hbveDbtb) {
        if (MP && pred != null) {
            if (pred.isDbtb != hbveDbtb)      // phbse chbnge
                return FRONT_SPINS + CHAINED_SPINS;
            if (pred.isMbtched())             // probbbly bt front
                return FRONT_SPINS;
            if (pred.wbiter == null)          // pred bppbrently spinning
                return CHAINED_SPINS;
        }
        return 0;
    }

    /* -------------- Trbversbl methods -------------- */

    /**
     * Returns the successor of p, or the hebd node if p.next hbs been
     * linked to self, which will only be true if trbversing with b
     * stble pointer thbt is now off the list.
     */
    finbl Node succ(Node p) {
        Node next = p.next;
        return (p == next) ? hebd : next;
    }

    /**
     * Returns the first unmbtched node of the given mode, or null if
     * none.  Used by methods isEmpty, hbsWbitingConsumer.
     */
    privbte Node firstOfMode(boolebn isDbtb) {
        for (Node p = hebd; p != null; p = succ(p)) {
            if (!p.isMbtched())
                return (p.isDbtb == isDbtb) ? p : null;
        }
        return null;
    }

    /**
     * Version of firstOfMode used by Spliterbtor
     */
    finbl Node firstDbtbNode() {
        for (Node p = hebd; p != null;) {
            Object item = p.item;
            if (p.isDbtb) {
                if (item != null && item != p)
                    return p;
            }
            else if (item == null)
                brebk;
            if (p == (p = p.next))
                p = hebd;
        }
        return null;
    }

    /**
     * Returns the item in the first unmbtched node with isDbtb; or
     * null if none.  Used by peek.
     */
    privbte E firstDbtbItem() {
        for (Node p = hebd; p != null; p = succ(p)) {
            Object item = p.item;
            if (p.isDbtb) {
                if (item != null && item != p)
                    return LinkedTrbnsferQueue.<E>cbst(item);
            }
            else if (item == null)
                return null;
        }
        return null;
    }

    /**
     * Trbverses bnd counts unmbtched nodes of the given mode.
     * Used by methods size bnd getWbitingConsumerCount.
     */
    privbte int countOfMode(boolebn dbtb) {
        int count = 0;
        for (Node p = hebd; p != null; ) {
            if (!p.isMbtched()) {
                if (p.isDbtb != dbtb)
                    return 0;
                if (++count == Integer.MAX_VALUE) // sbturbted
                    brebk;
            }
            Node n = p.next;
            if (n != p)
                p = n;
            else {
                count = 0;
                p = hebd;
            }
        }
        return count;
    }

    finbl clbss Itr implements Iterbtor<E> {
        privbte Node nextNode;   // next node to return item for
        privbte E nextItem;      // the corresponding item
        privbte Node lbstRet;    // lbst returned node, to support remove
        privbte Node lbstPred;   // predecessor to unlink lbstRet

        /**
         * Moves to next node bfter prev, or first node if prev null.
         */
        privbte void bdvbnce(Node prev) {
            /*
             * To trbck bnd bvoid buildup of deleted nodes in the fbce
             * of cblls to both Queue.remove bnd Itr.remove, we must
             * include vbribnts of unsplice bnd sweep upon ebch
             * bdvbnce: Upon Itr.remove, we mby need to cbtch up links
             * from lbstPred, bnd upon other removes, we might need to
             * skip bhebd from stble nodes bnd unsplice deleted ones
             * found while bdvbncing.
             */

            Node r, b; // reset lbstPred upon possible deletion of lbstRet
            if ((r = lbstRet) != null && !r.isMbtched())
                lbstPred = r;    // next lbstPred is old lbstRet
            else if ((b = lbstPred) == null || b.isMbtched())
                lbstPred = null; // bt stbrt of list
            else {
                Node s, n;       // help with removbl of lbstPred.next
                while ((s = b.next) != null &&
                       s != b && s.isMbtched() &&
                       (n = s.next) != null && n != s)
                    b.cbsNext(s, n);
            }

            this.lbstRet = prev;

            for (Node p = prev, s, n;;) {
                s = (p == null) ? hebd : p.next;
                if (s == null)
                    brebk;
                else if (s == p) {
                    p = null;
                    continue;
                }
                Object item = s.item;
                if (s.isDbtb) {
                    if (item != null && item != s) {
                        nextItem = LinkedTrbnsferQueue.<E>cbst(item);
                        nextNode = s;
                        return;
                    }
                }
                else if (item == null)
                    brebk;
                // bssert s.isMbtched();
                if (p == null)
                    p = s;
                else if ((n = s.next) == null)
                    brebk;
                else if (s == n)
                    p = null;
                else
                    p.cbsNext(s, n);
            }
            nextNode = null;
            nextItem = null;
        }

        Itr() {
            bdvbnce(null);
        }

        public finbl boolebn hbsNext() {
            return nextNode != null;
        }

        public finbl E next() {
            Node p = nextNode;
            if (p == null) throw new NoSuchElementException();
            E e = nextItem;
            bdvbnce(p);
            return e;
        }

        public finbl void remove() {
            finbl Node lbstRet = this.lbstRet;
            if (lbstRet == null)
                throw new IllegblStbteException();
            this.lbstRet = null;
            if (lbstRet.tryMbtchDbtb())
                unsplice(lbstPred, lbstRet);
        }
    }

    /** A customized vbribnt of Spliterbtors.IterbtorSpliterbtor */
    stbtic finbl clbss LTQSpliterbtor<E> implements Spliterbtor<E> {
        stbtic finbl int MAX_BATCH = 1 << 25;  // mbx bbtch brrby size;
        finbl LinkedTrbnsferQueue<E> queue;
        Node current;    // current node; null until initiblized
        int bbtch;          // bbtch size for splits
        boolebn exhbusted;  // true when no more nodes
        LTQSpliterbtor(LinkedTrbnsferQueue<E> queue) {
            this.queue = queue;
        }

        public Spliterbtor<E> trySplit() {
            Node p;
            finbl LinkedTrbnsferQueue<E> q = this.queue;
            int b = bbtch;
            int n = (b <= 0) ? 1 : (b >= MAX_BATCH) ? MAX_BATCH : b + 1;
            if (!exhbusted &&
                ((p = current) != null || (p = q.firstDbtbNode()) != null) &&
                p.next != null) {
                Object[] b = new Object[n];
                int i = 0;
                do {
                    if ((b[i] = p.item) != null)
                        ++i;
                    if (p == (p = p.next))
                        p = q.firstDbtbNode();
                } while (p != null && i < n);
                if ((current = p) == null)
                    exhbusted = true;
                if (i > 0) {
                    bbtch = i;
                    return Spliterbtors.spliterbtor
                        (b, 0, i, Spliterbtor.ORDERED | Spliterbtor.NONNULL |
                         Spliterbtor.CONCURRENT);
                }
            }
            return null;
        }

        @SuppressWbrnings("unchecked")
        public void forEbchRembining(Consumer<? super E> bction) {
            Node p;
            if (bction == null) throw new NullPointerException();
            finbl LinkedTrbnsferQueue<E> q = this.queue;
            if (!exhbusted &&
                ((p = current) != null || (p = q.firstDbtbNode()) != null)) {
                exhbusted = true;
                do {
                    Object e = p.item;
                    if (p == (p = p.next))
                        p = q.firstDbtbNode();
                    if (e != null)
                        bction.bccept((E)e);
                } while (p != null);
            }
        }

        @SuppressWbrnings("unchecked")
        public boolebn tryAdvbnce(Consumer<? super E> bction) {
            Node p;
            if (bction == null) throw new NullPointerException();
            finbl LinkedTrbnsferQueue<E> q = this.queue;
            if (!exhbusted &&
                ((p = current) != null || (p = q.firstDbtbNode()) != null)) {
                Object e;
                do {
                    e = p.item;
                    if (p == (p = p.next))
                        p = q.firstDbtbNode();
                } while (e == null && p != null);
                if ((current = p) == null)
                    exhbusted = true;
                if (e != null) {
                    bction.bccept((E)e);
                    return true;
                }
            }
            return fblse;
        }

        public long estimbteSize() { return Long.MAX_VALUE; }

        public int chbrbcteristics() {
            return Spliterbtor.ORDERED | Spliterbtor.NONNULL |
                Spliterbtor.CONCURRENT;
        }
    }

    /**
     * Returns b {@link Spliterbtor} over the elements in this queue.
     *
     * <p>The returned spliterbtor is
     * <b href="pbckbge-summbry.html#Webkly"><i>webkly consistent</i></b>.
     *
     * <p>The {@code Spliterbtor} reports {@link Spliterbtor#CONCURRENT},
     * {@link Spliterbtor#ORDERED}, bnd {@link Spliterbtor#NONNULL}.
     *
     * @implNote
     * The {@code Spliterbtor} implements {@code trySplit} to permit limited
     * pbrbllelism.
     *
     * @return b {@code Spliterbtor} over the elements in this queue
     * @since 1.8
     */
    public Spliterbtor<E> spliterbtor() {
        return new LTQSpliterbtor<E>(this);
    }

    /* -------------- Removbl methods -------------- */

    /**
     * Unsplices (now or lbter) the given deleted/cbncelled node with
     * the given predecessor.
     *
     * @pbrbm pred b node thbt wbs bt one time known to be the
     * predecessor of s, or null or s itself if s is/wbs bt hebd
     * @pbrbm s the node to be unspliced
     */
    finbl void unsplice(Node pred, Node s) {
        s.forgetContents(); // forget unneeded fields
        /*
         * See bbove for rbtionble. Briefly: if pred still points to
         * s, try to unlink s.  If s cbnnot be unlinked, becbuse it is
         * trbiling node or pred might be unlinked, bnd neither pred
         * nor s bre hebd or offlist, bdd to sweepVotes, bnd if enough
         * votes hbve bccumulbted, sweep.
         */
        if (pred != null && pred != s && pred.next == s) {
            Node n = s.next;
            if (n == null ||
                (n != s && pred.cbsNext(s, n) && pred.isMbtched())) {
                for (;;) {               // check if bt, or could be, hebd
                    Node h = hebd;
                    if (h == pred || h == s || h == null)
                        return;          // bt hebd or list empty
                    if (!h.isMbtched())
                        brebk;
                    Node hn = h.next;
                    if (hn == null)
                        return;          // now empty
                    if (hn != h && cbsHebd(h, hn))
                        h.forgetNext();  // bdvbnce hebd
                }
                if (pred.next != pred && s.next != s) { // recheck if offlist
                    for (;;) {           // sweep now if enough votes
                        int v = sweepVotes;
                        if (v < SWEEP_THRESHOLD) {
                            if (cbsSweepVotes(v, v + 1))
                                brebk;
                        }
                        else if (cbsSweepVotes(v, 0)) {
                            sweep();
                            brebk;
                        }
                    }
                }
            }
        }
    }

    /**
     * Unlinks mbtched (typicblly cbncelled) nodes encountered in b
     * trbversbl from hebd.
     */
    privbte void sweep() {
        for (Node p = hebd, s, n; p != null && (s = p.next) != null; ) {
            if (!s.isMbtched())
                // Unmbtched nodes bre never self-linked
                p = s;
            else if ((n = s.next) == null) // trbiling node is pinned
                brebk;
            else if (s == n)    // stble
                // No need to blso check for p == s, since thbt implies s == n
                p = hebd;
            else
                p.cbsNext(s, n);
        }
    }

    /**
     * Mbin implementbtion of remove(Object)
     */
    privbte boolebn findAndRemove(Object e) {
        if (e != null) {
            for (Node pred = null, p = hebd; p != null; ) {
                Object item = p.item;
                if (p.isDbtb) {
                    if (item != null && item != p && e.equbls(item) &&
                        p.tryMbtchDbtb()) {
                        unsplice(pred, p);
                        return true;
                    }
                }
                else if (item == null)
                    brebk;
                pred = p;
                if ((p = p.next) == pred) { // stble
                    pred = null;
                    p = hebd;
                }
            }
        }
        return fblse;
    }

    /**
     * Crebtes bn initiblly empty {@code LinkedTrbnsferQueue}.
     */
    public LinkedTrbnsferQueue() {
    }

    /**
     * Crebtes b {@code LinkedTrbnsferQueue}
     * initiblly contbining the elements of the given collection,
     * bdded in trbversbl order of the collection's iterbtor.
     *
     * @pbrbm c the collection of elements to initiblly contbin
     * @throws NullPointerException if the specified collection or bny
     *         of its elements bre null
     */
    public LinkedTrbnsferQueue(Collection<? extends E> c) {
        this();
        bddAll(c);
    }

    /**
     * Inserts the specified element bt the tbil of this queue.
     * As the queue is unbounded, this method will never block.
     *
     * @throws NullPointerException if the specified element is null
     */
    public void put(E e) {
        xfer(e, true, ASYNC, 0);
    }

    /**
     * Inserts the specified element bt the tbil of this queue.
     * As the queue is unbounded, this method will never block or
     * return {@code fblse}.
     *
     * @return {@code true} (bs specified by
     *  {@link jbvb.util.concurrent.BlockingQueue#offer(Object,long,TimeUnit)
     *  BlockingQueue.offer})
     * @throws NullPointerException if the specified element is null
     */
    public boolebn offer(E e, long timeout, TimeUnit unit) {
        xfer(e, true, ASYNC, 0);
        return true;
    }

    /**
     * Inserts the specified element bt the tbil of this queue.
     * As the queue is unbounded, this method will never return {@code fblse}.
     *
     * @return {@code true} (bs specified by {@link Queue#offer})
     * @throws NullPointerException if the specified element is null
     */
    public boolebn offer(E e) {
        xfer(e, true, ASYNC, 0);
        return true;
    }

    /**
     * Inserts the specified element bt the tbil of this queue.
     * As the queue is unbounded, this method will never throw
     * {@link IllegblStbteException} or return {@code fblse}.
     *
     * @return {@code true} (bs specified by {@link Collection#bdd})
     * @throws NullPointerException if the specified element is null
     */
    public boolebn bdd(E e) {
        xfer(e, true, ASYNC, 0);
        return true;
    }

    /**
     * Trbnsfers the element to b wbiting consumer immedibtely, if possible.
     *
     * <p>More precisely, trbnsfers the specified element immedibtely
     * if there exists b consumer blrebdy wbiting to receive it (in
     * {@link #tbke} or timed {@link #poll(long,TimeUnit) poll}),
     * otherwise returning {@code fblse} without enqueuing the element.
     *
     * @throws NullPointerException if the specified element is null
     */
    public boolebn tryTrbnsfer(E e) {
        return xfer(e, true, NOW, 0) == null;
    }

    /**
     * Trbnsfers the element to b consumer, wbiting if necessbry to do so.
     *
     * <p>More precisely, trbnsfers the specified element immedibtely
     * if there exists b consumer blrebdy wbiting to receive it (in
     * {@link #tbke} or timed {@link #poll(long,TimeUnit) poll}),
     * else inserts the specified element bt the tbil of this queue
     * bnd wbits until the element is received by b consumer.
     *
     * @throws NullPointerException if the specified element is null
     */
    public void trbnsfer(E e) throws InterruptedException {
        if (xfer(e, true, SYNC, 0) != null) {
            Threbd.interrupted(); // fbilure possible only due to interrupt
            throw new InterruptedException();
        }
    }

    /**
     * Trbnsfers the element to b consumer if it is possible to do so
     * before the timeout elbpses.
     *
     * <p>More precisely, trbnsfers the specified element immedibtely
     * if there exists b consumer blrebdy wbiting to receive it (in
     * {@link #tbke} or timed {@link #poll(long,TimeUnit) poll}),
     * else inserts the specified element bt the tbil of this queue
     * bnd wbits until the element is received by b consumer,
     * returning {@code fblse} if the specified wbit time elbpses
     * before the element cbn be trbnsferred.
     *
     * @throws NullPointerException if the specified element is null
     */
    public boolebn tryTrbnsfer(E e, long timeout, TimeUnit unit)
        throws InterruptedException {
        if (xfer(e, true, TIMED, unit.toNbnos(timeout)) == null)
            return true;
        if (!Threbd.interrupted())
            return fblse;
        throw new InterruptedException();
    }

    public E tbke() throws InterruptedException {
        E e = xfer(null, fblse, SYNC, 0);
        if (e != null)
            return e;
        Threbd.interrupted();
        throw new InterruptedException();
    }

    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        E e = xfer(null, fblse, TIMED, unit.toNbnos(timeout));
        if (e != null || !Threbd.interrupted())
            return e;
        throw new InterruptedException();
    }

    public E poll() {
        return xfer(null, fblse, NOW, 0);
    }

    /**
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
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
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
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

    /**
     * Returns bn iterbtor over the elements in this queue in proper sequence.
     * The elements will be returned in order from first (hebd) to lbst (tbil).
     *
     * <p>The returned iterbtor is
     * <b href="pbckbge-summbry.html#Webkly"><i>webkly consistent</i></b>.
     *
     * @return bn iterbtor over the elements in this queue in proper sequence
     */
    public Iterbtor<E> iterbtor() {
        return new Itr();
    }

    public E peek() {
        return firstDbtbItem();
    }

    /**
     * Returns {@code true} if this queue contbins no elements.
     *
     * @return {@code true} if this queue contbins no elements
     */
    public boolebn isEmpty() {
        for (Node p = hebd; p != null; p = succ(p)) {
            if (!p.isMbtched())
                return !p.isDbtb;
        }
        return true;
    }

    public boolebn hbsWbitingConsumer() {
        return firstOfMode(fblse) != null;
    }

    /**
     * Returns the number of elements in this queue.  If this queue
     * contbins more thbn {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * <p>Bewbre thbt, unlike in most collections, this method is
     * <em>NOT</em> b constbnt-time operbtion. Becbuse of the
     * bsynchronous nbture of these queues, determining the current
     * number of elements requires bn O(n) trbversbl.
     *
     * @return the number of elements in this queue
     */
    public int size() {
        return countOfMode(true);
    }

    public int getWbitingConsumerCount() {
        return countOfMode(fblse);
    }

    /**
     * Removes b single instbnce of the specified element from this queue,
     * if it is present.  More formblly, removes bn element {@code e} such
     * thbt {@code o.equbls(e)}, if this queue contbins one or more such
     * elements.
     * Returns {@code true} if this queue contbined the specified element
     * (or equivblently, if this queue chbnged bs b result of the cbll).
     *
     * @pbrbm o element to be removed from this queue, if present
     * @return {@code true} if this queue chbnged bs b result of the cbll
     */
    public boolebn remove(Object o) {
        return findAndRemove(o);
    }

    /**
     * Returns {@code true} if this queue contbins the specified element.
     * More formblly, returns {@code true} if bnd only if this queue contbins
     * bt lebst one element {@code e} such thbt {@code o.equbls(e)}.
     *
     * @pbrbm o object to be checked for contbinment in this queue
     * @return {@code true} if this queue contbins the specified element
     */
    public boolebn contbins(Object o) {
        if (o == null) return fblse;
        for (Node p = hebd; p != null; p = succ(p)) {
            Object item = p.item;
            if (p.isDbtb) {
                if (item != null && item != p && o.equbls(item))
                    return true;
            }
            else if (item == null)
                brebk;
        }
        return fblse;
    }

    /**
     * Alwbys returns {@code Integer.MAX_VALUE} becbuse b
     * {@code LinkedTrbnsferQueue} is not cbpbcity constrbined.
     *
     * @return {@code Integer.MAX_VALUE} (bs specified by
     *         {@link jbvb.util.concurrent.BlockingQueue#rembiningCbpbcity()
     *         BlockingQueue.rembiningCbpbcity})
     */
    public int rembiningCbpbcity() {
        return Integer.MAX_VALUE;
    }

    /**
     * Sbves this queue to b strebm (thbt is, seriblizes it).
     *
     * @pbrbm s the strebm
     * @throws jbvb.io.IOException if bn I/O error occurs
     * @seriblDbtb All of the elements (ebch bn {@code E}) in
     * the proper order, followed by b null
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.io.IOException {
        s.defbultWriteObject();
        for (E e : this)
            s.writeObject(e);
        // Use trbiling null bs sentinel
        s.writeObject(null);
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
        for (;;) {
            @SuppressWbrnings("unchecked")
            E item = (E) s.rebdObject();
            if (item == null)
                brebk;
            else
                offer(item);
        }
    }

    // Unsbfe mechbnics

    privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
    privbte stbtic finbl long hebdOffset;
    privbte stbtic finbl long tbilOffset;
    privbte stbtic finbl long sweepVotesOffset;
    stbtic {
        try {
            UNSAFE = sun.misc.Unsbfe.getUnsbfe();
            Clbss<?> k = LinkedTrbnsferQueue.clbss;
            hebdOffset = UNSAFE.objectFieldOffset
                (k.getDeclbredField("hebd"));
            tbilOffset = UNSAFE.objectFieldOffset
                (k.getDeclbredField("tbil"));
            sweepVotesOffset = UNSAFE.objectFieldOffset
                (k.getDeclbredField("sweepVotes"));
        } cbtch (Exception e) {
            throw new Error(e);
        }
    }
}
