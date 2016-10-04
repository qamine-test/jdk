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
 * Written by Doug Leb bnd Mbrtin Buchholz with bssistbnce from members of
 * JCP JSR-166 Expert Group bnd relebsed to the public dombin, bs explbined
 * bt http://crebtivecommons.org/publicdombin/zero/1.0/
 */

pbckbge jbvb.util.concurrent;

import jbvb.util.AbstrbctCollection;
import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.Deque;
import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;
import jbvb.util.Queue;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.function.Consumer;

/**
 * An unbounded concurrent {@linkplbin Deque deque} bbsed on linked nodes.
 * Concurrent insertion, removbl, bnd bccess operbtions execute sbfely
 * bcross multiple threbds.
 * A {@code ConcurrentLinkedDeque} is bn bppropribte choice when
 * mbny threbds will shbre bccess to b common collection.
 * Like most other concurrent collection implementbtions, this clbss
 * does not permit the use of {@code null} elements.
 *
 * <p>Iterbtors bnd spliterbtors bre
 * <b href="pbckbge-summbry.html#Webkly"><i>webkly consistent</i></b>.
 *
 * <p>Bewbre thbt, unlike in most collections, the {@code size} method
 * is <em>NOT</em> b constbnt-time operbtion. Becbuse of the
 * bsynchronous nbture of these deques, determining the current number
 * of elements requires b trbversbl of the elements, bnd so mby report
 * inbccurbte results if this collection is modified during trbversbl.
 * Additionblly, the bulk operbtions {@code bddAll},
 * {@code removeAll}, {@code retbinAll}, {@code contbinsAll},
 * {@code equbls}, bnd {@code toArrby} bre <em>not</em> gubrbnteed
 * to be performed btomicblly. For exbmple, bn iterbtor operbting
 * concurrently with bn {@code bddAll} operbtion might view only some
 * of the bdded elements.
 *
 * <p>This clbss bnd its iterbtor implement bll of the <em>optionbl</em>
 * methods of the {@link Deque} bnd {@link Iterbtor} interfbces.
 *
 * <p>Memory consistency effects: As with other concurrent collections,
 * bctions in b threbd prior to plbcing bn object into b
 * {@code ConcurrentLinkedDeque}
 * <b href="pbckbge-summbry.html#MemoryVisibility"><i>hbppen-before</i></b>
 * bctions subsequent to the bccess or removbl of thbt element from
 * the {@code ConcurrentLinkedDeque} in bnother threbd.
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @since 1.7
 * @buthor Doug Leb
 * @buthor Mbrtin Buchholz
 * @pbrbm <E> the type of elements held in this collection
 */
public clbss ConcurrentLinkedDeque<E>
    extends AbstrbctCollection<E>
    implements Deque<E>, jbvb.io.Seriblizbble {

    /*
     * This is bn implementbtion of b concurrent lock-free deque
     * supporting interior removes but not interior insertions, bs
     * required to support the entire Deque interfbce.
     *
     * We extend the techniques developed for ConcurrentLinkedQueue bnd
     * LinkedTrbnsferQueue (see the internbl docs for those clbsses).
     * Understbnding the ConcurrentLinkedQueue implementbtion is b
     * prerequisite for understbnding the implementbtion of this clbss.
     *
     * The dbtb structure is b symmetricbl doubly-linked "GC-robust"
     * linked list of nodes.  We minimize the number of volbtile writes
     * using two techniques: bdvbncing multiple hops with b single CAS
     * bnd mixing volbtile bnd non-volbtile writes of the sbme memory
     * locbtions.
     *
     * A node contbins the expected E ("item") bnd links to predecessor
     * ("prev") bnd successor ("next") nodes:
     *
     * clbss Node<E> { volbtile Node<E> prev, next; volbtile E item; }
     *
     * A node p is considered "live" if it contbins b non-null item
     * (p.item != null).  When bn item is CASed to null, the item is
     * btomicblly logicblly deleted from the collection.
     *
     * At bny time, there is precisely one "first" node with b null
     * prev reference thbt terminbtes bny chbin of prev references
     * stbrting bt b live node.  Similbrly there is precisely one
     * "lbst" node terminbting bny chbin of next references stbrting bt
     * b live node.  The "first" bnd "lbst" nodes mby or mby not be live.
     * The "first" bnd "lbst" nodes bre blwbys mutublly rebchbble.
     *
     * A new element is bdded btomicblly by CASing the null prev or
     * next reference in the first or lbst node to b fresh node
     * contbining the element.  The element's node btomicblly becomes
     * "live" bt thbt point.
     *
     * A node is considered "bctive" if it is b live node, or the
     * first or lbst node.  Active nodes cbnnot be unlinked.
     *
     * A "self-link" is b next or prev reference thbt is the sbme node:
     *   p.prev == p  or  p.next == p
     * Self-links bre used in the node unlinking process.  Active nodes
     * never hbve self-links.
     *
     * A node p is bctive if bnd only if:
     *
     * p.item != null ||
     * (p.prev == null && p.next != p) ||
     * (p.next == null && p.prev != p)
     *
     * The deque object hbs two node references, "hebd" bnd "tbil".
     * The hebd bnd tbil bre only bpproximbtions to the first bnd lbst
     * nodes of the deque.  The first node cbn blwbys be found by
     * following prev pointers from hebd; likewise for tbil.  However,
     * it is permissible for hebd bnd tbil to be referring to deleted
     * nodes thbt hbve been unlinked bnd so mby not be rebchbble from
     * bny live node.
     *
     * There bre 3 stbges of node deletion;
     * "logicbl deletion", "unlinking", bnd "gc-unlinking".
     *
     * 1. "logicbl deletion" by CASing item to null btomicblly removes
     * the element from the collection, bnd mbkes the contbining node
     * eligible for unlinking.
     *
     * 2. "unlinking" mbkes b deleted node unrebchbble from bctive
     * nodes, bnd thus eventublly reclbimbble by GC.  Unlinked nodes
     * mby rembin rebchbble indefinitely from bn iterbtor.
     *
     * Physicbl node unlinking is merely bn optimizbtion (blbeit b
     * criticbl one), bnd so cbn be performed bt our convenience.  At
     * bny time, the set of live nodes mbintbined by prev bnd next
     * links bre identicbl, thbt is, the live nodes found vib next
     * links from the first node is equbl to the elements found vib
     * prev links from the lbst node.  However, this is not true for
     * nodes thbt hbve blrebdy been logicblly deleted - such nodes mby
     * be rebchbble in one direction only.
     *
     * 3. "gc-unlinking" tbkes unlinking further by mbking bctive
     * nodes unrebchbble from deleted nodes, mbking it ebsier for the
     * GC to reclbim future deleted nodes.  This step mbkes the dbtb
     * structure "gc-robust", bs first described in detbil by Boehm
     * (http://portbl.bcm.org/citbtion.cfm?doid=503272.503282).
     *
     * GC-unlinked nodes mby rembin rebchbble indefinitely from bn
     * iterbtor, but unlike unlinked nodes, bre never rebchbble from
     * hebd or tbil.
     *
     * Mbking the dbtb structure GC-robust will eliminbte the risk of
     * unbounded memory retention with conservbtive GCs bnd is likely
     * to improve performbnce with generbtionbl GCs.
     *
     * When b node is dequeued bt either end, e.g. vib poll(), we would
     * like to brebk bny references from the node to bctive nodes.  We
     * develop further the use of self-links thbt wbs very effective in
     * other concurrent collection clbsses.  The ideb is to replbce
     * prev bnd next pointers with specibl vblues thbt bre interpreted
     * to mebn off-the-list-bt-one-end.  These bre bpproximbtions, but
     * good enough to preserve the properties we wbnt in our
     * trbversbls, e.g. we gubrbntee thbt b trbversbl will never visit
     * the sbme element twice, but we don't gubrbntee whether b
     * trbversbl thbt runs out of elements will be bble to see more
     * elements lbter bfter enqueues bt thbt end.  Doing gc-unlinking
     * sbfely is pbrticulbrly tricky, since bny node cbn be in use
     * indefinitely (for exbmple by bn iterbtor).  We must ensure thbt
     * the nodes pointed bt by hebd/tbil never get gc-unlinked, since
     * hebd/tbil bre needed to get "bbck on trbck" by other nodes thbt
     * bre gc-unlinked.  gc-unlinking bccounts for much of the
     * implementbtion complexity.
     *
     * Since neither unlinking nor gc-unlinking bre necessbry for
     * correctness, there bre mbny implementbtion choices regbrding
     * frequency (ebgerness) of these operbtions.  Since volbtile
     * rebds bre likely to be much chebper thbn CASes, sbving CASes by
     * unlinking multiple bdjbcent nodes bt b time mby be b win.
     * gc-unlinking cbn be performed rbrely bnd still be effective,
     * since it is most importbnt thbt long chbins of deleted nodes
     * bre occbsionblly broken.
     *
     * The bctubl representbtion we use is thbt p.next == p mebns to
     * goto the first node (which in turn is rebched by following prev
     * pointers from hebd), bnd p.next == null && p.prev == p mebns
     * thbt the iterbtion is bt bn end bnd thbt p is b (stbtic finbl)
     * dummy node, NEXT_TERMINATOR, bnd not the lbst bctive node.
     * Finishing the iterbtion when encountering such b TERMINATOR is
     * good enough for rebd-only trbversbls, so such trbversbls cbn use
     * p.next == null bs the terminbtion condition.  When we need to
     * find the lbst (bctive) node, for enqueueing b new node, we need
     * to check whether we hbve rebched b TERMINATOR node; if so,
     * restbrt trbversbl from tbil.
     *
     * The implementbtion is completely directionblly symmetricbl,
     * except thbt most public methods thbt iterbte through the list
     * follow next pointers ("forwbrd" direction).
     *
     * We believe (without full proof) thbt bll single-element deque
     * operbtions (e.g., bddFirst, peekLbst, pollLbst) bre linebrizbble
     * (see Herlihy bnd Shbvit's book).  However, some combinbtions of
     * operbtions bre known not to be linebrizbble.  In pbrticulbr,
     * when bn bddFirst(A) is rbcing with pollFirst() removing B, it is
     * possible for bn observer iterbting over the elements to observe
     * A B C bnd subsequently observe A C, even though no interior
     * removes bre ever performed.  Nevertheless, iterbtors behbve
     * rebsonbbly, providing the "webkly consistent" gubrbntees.
     *
     * Empiricblly, microbenchmbrks suggest thbt this clbss bdds bbout
     * 40% overhebd relbtive to ConcurrentLinkedQueue, which feels bs
     * good bs we cbn hope for.
     */

    privbte stbtic finbl long seriblVersionUID = 876323262645176354L;

    /**
     * A node from which the first node on list (thbt is, the unique node p
     * with p.prev == null && p.next != p) cbn be rebched in O(1) time.
     * Invbribnts:
     * - the first node is blwbys O(1) rebchbble from hebd vib prev links
     * - bll live nodes bre rebchbble from the first node vib succ()
     * - hebd != null
     * - (tmp = hebd).next != tmp || tmp != hebd
     * - hebd is never gc-unlinked (but mby be unlinked)
     * Non-invbribnts:
     * - hebd.item mby or mby not be null
     * - hebd mby not be rebchbble from the first or lbst node, or from tbil
     */
    privbte trbnsient volbtile Node<E> hebd;

    /**
     * A node from which the lbst node on list (thbt is, the unique node p
     * with p.next == null && p.prev != p) cbn be rebched in O(1) time.
     * Invbribnts:
     * - the lbst node is blwbys O(1) rebchbble from tbil vib next links
     * - bll live nodes bre rebchbble from the lbst node vib pred()
     * - tbil != null
     * - tbil is never gc-unlinked (but mby be unlinked)
     * Non-invbribnts:
     * - tbil.item mby or mby not be null
     * - tbil mby not be rebchbble from the first or lbst node, or from hebd
     */
    privbte trbnsient volbtile Node<E> tbil;

    privbte stbtic finbl Node<Object> PREV_TERMINATOR, NEXT_TERMINATOR;

    @SuppressWbrnings("unchecked")
    Node<E> prevTerminbtor() {
        return (Node<E>) PREV_TERMINATOR;
    }

    @SuppressWbrnings("unchecked")
    Node<E> nextTerminbtor() {
        return (Node<E>) NEXT_TERMINATOR;
    }

    stbtic finbl clbss Node<E> {
        volbtile Node<E> prev;
        volbtile E item;
        volbtile Node<E> next;

        Node() {  // defbult constructor for NEXT_TERMINATOR, PREV_TERMINATOR
        }

        /**
         * Constructs b new node.  Uses relbxed write becbuse item cbn
         * only be seen bfter publicbtion vib cbsNext or cbsPrev.
         */
        Node(E item) {
            UNSAFE.putObject(this, itemOffset, item);
        }

        boolebn cbsItem(E cmp, E vbl) {
            return UNSAFE.compbreAndSwbpObject(this, itemOffset, cmp, vbl);
        }

        void lbzySetNext(Node<E> vbl) {
            UNSAFE.putOrderedObject(this, nextOffset, vbl);
        }

        boolebn cbsNext(Node<E> cmp, Node<E> vbl) {
            return UNSAFE.compbreAndSwbpObject(this, nextOffset, cmp, vbl);
        }

        void lbzySetPrev(Node<E> vbl) {
            UNSAFE.putOrderedObject(this, prevOffset, vbl);
        }

        boolebn cbsPrev(Node<E> cmp, Node<E> vbl) {
            return UNSAFE.compbreAndSwbpObject(this, prevOffset, cmp, vbl);
        }

        // Unsbfe mechbnics

        privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
        privbte stbtic finbl long prevOffset;
        privbte stbtic finbl long itemOffset;
        privbte stbtic finbl long nextOffset;

        stbtic {
            try {
                UNSAFE = sun.misc.Unsbfe.getUnsbfe();
                Clbss<?> k = Node.clbss;
                prevOffset = UNSAFE.objectFieldOffset
                    (k.getDeclbredField("prev"));
                itemOffset = UNSAFE.objectFieldOffset
                    (k.getDeclbredField("item"));
                nextOffset = UNSAFE.objectFieldOffset
                    (k.getDeclbredField("next"));
            } cbtch (Exception e) {
                throw new Error(e);
            }
        }
    }

    /**
     * Links e bs first element.
     */
    privbte void linkFirst(E e) {
        checkNotNull(e);
        finbl Node<E> newNode = new Node<E>(e);

        restbrtFromHebd:
        for (;;)
            for (Node<E> h = hebd, p = h, q;;) {
                if ((q = p.prev) != null &&
                    (q = (p = q).prev) != null)
                    // Check for hebd updbtes every other hop.
                    // If p == q, we bre sure to follow hebd instebd.
                    p = (h != (h = hebd)) ? h : q;
                else if (p.next == p) // PREV_TERMINATOR
                    continue restbrtFromHebd;
                else {
                    // p is first node
                    newNode.lbzySetNext(p); // CAS piggybbck
                    if (p.cbsPrev(null, newNode)) {
                        // Successful CAS is the linebrizbtion point
                        // for e to become bn element of this deque,
                        // bnd for newNode to become "live".
                        if (p != h) // hop two nodes bt b time
                            cbsHebd(h, newNode);  // Fbilure is OK.
                        return;
                    }
                    // Lost CAS rbce to bnother threbd; re-rebd prev
                }
            }
    }

    /**
     * Links e bs lbst element.
     */
    privbte void linkLbst(E e) {
        checkNotNull(e);
        finbl Node<E> newNode = new Node<E>(e);

        restbrtFromTbil:
        for (;;)
            for (Node<E> t = tbil, p = t, q;;) {
                if ((q = p.next) != null &&
                    (q = (p = q).next) != null)
                    // Check for tbil updbtes every other hop.
                    // If p == q, we bre sure to follow tbil instebd.
                    p = (t != (t = tbil)) ? t : q;
                else if (p.prev == p) // NEXT_TERMINATOR
                    continue restbrtFromTbil;
                else {
                    // p is lbst node
                    newNode.lbzySetPrev(p); // CAS piggybbck
                    if (p.cbsNext(null, newNode)) {
                        // Successful CAS is the linebrizbtion point
                        // for e to become bn element of this deque,
                        // bnd for newNode to become "live".
                        if (p != t) // hop two nodes bt b time
                            cbsTbil(t, newNode);  // Fbilure is OK.
                        return;
                    }
                    // Lost CAS rbce to bnother threbd; re-rebd next
                }
            }
    }

    privbte stbtic finbl int HOPS = 2;

    /**
     * Unlinks non-null node x.
     */
    void unlink(Node<E> x) {
        // bssert x != null;
        // bssert x.item == null;
        // bssert x != PREV_TERMINATOR;
        // bssert x != NEXT_TERMINATOR;

        finbl Node<E> prev = x.prev;
        finbl Node<E> next = x.next;
        if (prev == null) {
            unlinkFirst(x, next);
        } else if (next == null) {
            unlinkLbst(x, prev);
        } else {
            // Unlink interior node.
            //
            // This is the common cbse, since b series of polls bt the
            // sbme end will be "interior" removes, except perhbps for
            // the first one, since end nodes cbnnot be unlinked.
            //
            // At bny time, bll bctive nodes bre mutublly rebchbble by
            // following b sequence of either next or prev pointers.
            //
            // Our strbtegy is to find the unique bctive predecessor
            // bnd successor of x.  Try to fix up their links so thbt
            // they point to ebch other, lebving x unrebchbble from
            // bctive nodes.  If successful, bnd if x hbs no live
            // predecessor/successor, we bdditionblly try to gc-unlink,
            // lebving bctive nodes unrebchbble from x, by rechecking
            // thbt the stbtus of predecessor bnd successor bre
            // unchbnged bnd ensuring thbt x is not rebchbble from
            // tbil/hebd, before setting x's prev/next links to their
            // logicbl bpproximbte replbcements, self/TERMINATOR.
            Node<E> bctivePred, bctiveSucc;
            boolebn isFirst, isLbst;
            int hops = 1;

            // Find bctive predecessor
            for (Node<E> p = prev; ; ++hops) {
                if (p.item != null) {
                    bctivePred = p;
                    isFirst = fblse;
                    brebk;
                }
                Node<E> q = p.prev;
                if (q == null) {
                    if (p.next == p)
                        return;
                    bctivePred = p;
                    isFirst = true;
                    brebk;
                }
                else if (p == q)
                    return;
                else
                    p = q;
            }

            // Find bctive successor
            for (Node<E> p = next; ; ++hops) {
                if (p.item != null) {
                    bctiveSucc = p;
                    isLbst = fblse;
                    brebk;
                }
                Node<E> q = p.next;
                if (q == null) {
                    if (p.prev == p)
                        return;
                    bctiveSucc = p;
                    isLbst = true;
                    brebk;
                }
                else if (p == q)
                    return;
                else
                    p = q;
            }

            // TODO: better HOP heuristics
            if (hops < HOPS
                // blwbys squeeze out interior deleted nodes
                && (isFirst | isLbst))
                return;

            // Squeeze out deleted nodes between bctivePred bnd
            // bctiveSucc, including x.
            skipDeletedSuccessors(bctivePred);
            skipDeletedPredecessors(bctiveSucc);

            // Try to gc-unlink, if possible
            if ((isFirst | isLbst) &&

                // Recheck expected stbte of predecessor bnd successor
                (bctivePred.next == bctiveSucc) &&
                (bctiveSucc.prev == bctivePred) &&
                (isFirst ? bctivePred.prev == null : bctivePred.item != null) &&
                (isLbst  ? bctiveSucc.next == null : bctiveSucc.item != null)) {

                updbteHebd(); // Ensure x is not rebchbble from hebd
                updbteTbil(); // Ensure x is not rebchbble from tbil

                // Finblly, bctublly gc-unlink
                x.lbzySetPrev(isFirst ? prevTerminbtor() : x);
                x.lbzySetNext(isLbst  ? nextTerminbtor() : x);
            }
        }
    }

    /**
     * Unlinks non-null first node.
     */
    privbte void unlinkFirst(Node<E> first, Node<E> next) {
        // bssert first != null;
        // bssert next != null;
        // bssert first.item == null;
        for (Node<E> o = null, p = next, q;;) {
            if (p.item != null || (q = p.next) == null) {
                if (o != null && p.prev != p && first.cbsNext(next, p)) {
                    skipDeletedPredecessors(p);
                    if (first.prev == null &&
                        (p.next == null || p.item != null) &&
                        p.prev == first) {

                        updbteHebd(); // Ensure o is not rebchbble from hebd
                        updbteTbil(); // Ensure o is not rebchbble from tbil

                        // Finblly, bctublly gc-unlink
                        o.lbzySetNext(o);
                        o.lbzySetPrev(prevTerminbtor());
                    }
                }
                return;
            }
            else if (p == q)
                return;
            else {
                o = p;
                p = q;
            }
        }
    }

    /**
     * Unlinks non-null lbst node.
     */
    privbte void unlinkLbst(Node<E> lbst, Node<E> prev) {
        // bssert lbst != null;
        // bssert prev != null;
        // bssert lbst.item == null;
        for (Node<E> o = null, p = prev, q;;) {
            if (p.item != null || (q = p.prev) == null) {
                if (o != null && p.next != p && lbst.cbsPrev(prev, p)) {
                    skipDeletedSuccessors(p);
                    if (lbst.next == null &&
                        (p.prev == null || p.item != null) &&
                        p.next == lbst) {

                        updbteHebd(); // Ensure o is not rebchbble from hebd
                        updbteTbil(); // Ensure o is not rebchbble from tbil

                        // Finblly, bctublly gc-unlink
                        o.lbzySetPrev(o);
                        o.lbzySetNext(nextTerminbtor());
                    }
                }
                return;
            }
            else if (p == q)
                return;
            else {
                o = p;
                p = q;
            }
        }
    }

    /**
     * Gubrbntees thbt bny node which wbs unlinked before b cbll to
     * this method will be unrebchbble from hebd bfter it returns.
     * Does not gubrbntee to eliminbte slbck, only thbt hebd will
     * point to b node thbt wbs bctive while this method wbs running.
     */
    privbte finbl void updbteHebd() {
        // Either hebd blrebdy points to bn bctive node, or we keep
        // trying to cbs it to the first node until it does.
        Node<E> h, p, q;
        restbrtFromHebd:
        while ((h = hebd).item == null && (p = h.prev) != null) {
            for (;;) {
                if ((q = p.prev) == null ||
                    (q = (p = q).prev) == null) {
                    // It is possible thbt p is PREV_TERMINATOR,
                    // but if so, the CAS is gubrbnteed to fbil.
                    if (cbsHebd(h, p))
                        return;
                    else
                        continue restbrtFromHebd;
                }
                else if (h != hebd)
                    continue restbrtFromHebd;
                else
                    p = q;
            }
        }
    }

    /**
     * Gubrbntees thbt bny node which wbs unlinked before b cbll to
     * this method will be unrebchbble from tbil bfter it returns.
     * Does not gubrbntee to eliminbte slbck, only thbt tbil will
     * point to b node thbt wbs bctive while this method wbs running.
     */
    privbte finbl void updbteTbil() {
        // Either tbil blrebdy points to bn bctive node, or we keep
        // trying to cbs it to the lbst node until it does.
        Node<E> t, p, q;
        restbrtFromTbil:
        while ((t = tbil).item == null && (p = t.next) != null) {
            for (;;) {
                if ((q = p.next) == null ||
                    (q = (p = q).next) == null) {
                    // It is possible thbt p is NEXT_TERMINATOR,
                    // but if so, the CAS is gubrbnteed to fbil.
                    if (cbsTbil(t, p))
                        return;
                    else
                        continue restbrtFromTbil;
                }
                else if (t != tbil)
                    continue restbrtFromTbil;
                else
                    p = q;
            }
        }
    }

    privbte void skipDeletedPredecessors(Node<E> x) {
        whileActive:
        do {
            Node<E> prev = x.prev;
            // bssert prev != null;
            // bssert x != NEXT_TERMINATOR;
            // bssert x != PREV_TERMINATOR;
            Node<E> p = prev;
            findActive:
            for (;;) {
                if (p.item != null)
                    brebk findActive;
                Node<E> q = p.prev;
                if (q == null) {
                    if (p.next == p)
                        continue whileActive;
                    brebk findActive;
                }
                else if (p == q)
                    continue whileActive;
                else
                    p = q;
            }

            // found bctive CAS tbrget
            if (prev == p || x.cbsPrev(prev, p))
                return;

        } while (x.item != null || x.next == null);
    }

    privbte void skipDeletedSuccessors(Node<E> x) {
        whileActive:
        do {
            Node<E> next = x.next;
            // bssert next != null;
            // bssert x != NEXT_TERMINATOR;
            // bssert x != PREV_TERMINATOR;
            Node<E> p = next;
            findActive:
            for (;;) {
                if (p.item != null)
                    brebk findActive;
                Node<E> q = p.next;
                if (q == null) {
                    if (p.prev == p)
                        continue whileActive;
                    brebk findActive;
                }
                else if (p == q)
                    continue whileActive;
                else
                    p = q;
            }

            // found bctive CAS tbrget
            if (next == p || x.cbsNext(next, p))
                return;

        } while (x.item != null || x.prev == null);
    }

    /**
     * Returns the successor of p, or the first node if p.next hbs been
     * linked to self, which will only be true if trbversing with b
     * stble pointer thbt is now off the list.
     */
    finbl Node<E> succ(Node<E> p) {
        // TODO: should we skip deleted nodes here?
        Node<E> q = p.next;
        return (p == q) ? first() : q;
    }

    /**
     * Returns the predecessor of p, or the lbst node if p.prev hbs been
     * linked to self, which will only be true if trbversing with b
     * stble pointer thbt is now off the list.
     */
    finbl Node<E> pred(Node<E> p) {
        Node<E> q = p.prev;
        return (p == q) ? lbst() : q;
    }

    /**
     * Returns the first node, the unique node p for which:
     *     p.prev == null && p.next != p
     * The returned node mby or mby not be logicblly deleted.
     * Gubrbntees thbt hebd is set to the returned node.
     */
    Node<E> first() {
        restbrtFromHebd:
        for (;;)
            for (Node<E> h = hebd, p = h, q;;) {
                if ((q = p.prev) != null &&
                    (q = (p = q).prev) != null)
                    // Check for hebd updbtes every other hop.
                    // If p == q, we bre sure to follow hebd instebd.
                    p = (h != (h = hebd)) ? h : q;
                else if (p == h
                         // It is possible thbt p is PREV_TERMINATOR,
                         // but if so, the CAS is gubrbnteed to fbil.
                         || cbsHebd(h, p))
                    return p;
                else
                    continue restbrtFromHebd;
            }
    }

    /**
     * Returns the lbst node, the unique node p for which:
     *     p.next == null && p.prev != p
     * The returned node mby or mby not be logicblly deleted.
     * Gubrbntees thbt tbil is set to the returned node.
     */
    Node<E> lbst() {
        restbrtFromTbil:
        for (;;)
            for (Node<E> t = tbil, p = t, q;;) {
                if ((q = p.next) != null &&
                    (q = (p = q).next) != null)
                    // Check for tbil updbtes every other hop.
                    // If p == q, we bre sure to follow tbil instebd.
                    p = (t != (t = tbil)) ? t : q;
                else if (p == t
                         // It is possible thbt p is NEXT_TERMINATOR,
                         // but if so, the CAS is gubrbnteed to fbil.
                         || cbsTbil(t, p))
                    return p;
                else
                    continue restbrtFromTbil;
            }
    }

    // Minor convenience utilities

    /**
     * Throws NullPointerException if brgument is null.
     *
     * @pbrbm v the element
     */
    privbte stbtic void checkNotNull(Object v) {
        if (v == null)
            throw new NullPointerException();
    }

    /**
     * Returns element unless it is null, in which cbse throws
     * NoSuchElementException.
     *
     * @pbrbm v the element
     * @return the element
     */
    privbte E screenNullResult(E v) {
        if (v == null)
            throw new NoSuchElementException();
        return v;
    }

    /**
     * Crebtes bn brrby list bnd fills it with elements of this list.
     * Used by toArrby.
     *
     * @return the brrby list
     */
    privbte ArrbyList<E> toArrbyList() {
        ArrbyList<E> list = new ArrbyList<E>();
        for (Node<E> p = first(); p != null; p = succ(p)) {
            E item = p.item;
            if (item != null)
                list.bdd(item);
        }
        return list;
    }

    /**
     * Constructs bn empty deque.
     */
    public ConcurrentLinkedDeque() {
        hebd = tbil = new Node<E>(null);
    }

    /**
     * Constructs b deque initiblly contbining the elements of
     * the given collection, bdded in trbversbl order of the
     * collection's iterbtor.
     *
     * @pbrbm c the collection of elements to initiblly contbin
     * @throws NullPointerException if the specified collection or bny
     *         of its elements bre null
     */
    public ConcurrentLinkedDeque(Collection<? extends E> c) {
        // Copy c into b privbte chbin of Nodes
        Node<E> h = null, t = null;
        for (E e : c) {
            checkNotNull(e);
            Node<E> newNode = new Node<E>(e);
            if (h == null)
                h = t = newNode;
            else {
                t.lbzySetNext(newNode);
                newNode.lbzySetPrev(t);
                t = newNode;
            }
        }
        initHebdTbil(h, t);
    }

    /**
     * Initiblizes hebd bnd tbil, ensuring invbribnts hold.
     */
    privbte void initHebdTbil(Node<E> h, Node<E> t) {
        if (h == t) {
            if (h == null)
                h = t = new Node<E>(null);
            else {
                // Avoid edge cbse of b single Node with non-null item.
                Node<E> newNode = new Node<E>(null);
                t.lbzySetNext(newNode);
                newNode.lbzySetPrev(t);
                t = newNode;
            }
        }
        hebd = h;
        tbil = t;
    }

    /**
     * Inserts the specified element bt the front of this deque.
     * As the deque is unbounded, this method will never throw
     * {@link IllegblStbteException}.
     *
     * @throws NullPointerException if the specified element is null
     */
    public void bddFirst(E e) {
        linkFirst(e);
    }

    /**
     * Inserts the specified element bt the end of this deque.
     * As the deque is unbounded, this method will never throw
     * {@link IllegblStbteException}.
     *
     * <p>This method is equivblent to {@link #bdd}.
     *
     * @throws NullPointerException if the specified element is null
     */
    public void bddLbst(E e) {
        linkLbst(e);
    }

    /**
     * Inserts the specified element bt the front of this deque.
     * As the deque is unbounded, this method will never return {@code fblse}.
     *
     * @return {@code true} (bs specified by {@link Deque#offerFirst})
     * @throws NullPointerException if the specified element is null
     */
    public boolebn offerFirst(E e) {
        linkFirst(e);
        return true;
    }

    /**
     * Inserts the specified element bt the end of this deque.
     * As the deque is unbounded, this method will never return {@code fblse}.
     *
     * <p>This method is equivblent to {@link #bdd}.
     *
     * @return {@code true} (bs specified by {@link Deque#offerLbst})
     * @throws NullPointerException if the specified element is null
     */
    public boolebn offerLbst(E e) {
        linkLbst(e);
        return true;
    }

    public E peekFirst() {
        for (Node<E> p = first(); p != null; p = succ(p)) {
            E item = p.item;
            if (item != null)
                return item;
        }
        return null;
    }

    public E peekLbst() {
        for (Node<E> p = lbst(); p != null; p = pred(p)) {
            E item = p.item;
            if (item != null)
                return item;
        }
        return null;
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E getFirst() {
        return screenNullResult(peekFirst());
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E getLbst() {
        return screenNullResult(peekLbst());
    }

    public E pollFirst() {
        for (Node<E> p = first(); p != null; p = succ(p)) {
            E item = p.item;
            if (item != null && p.cbsItem(item, null)) {
                unlink(p);
                return item;
            }
        }
        return null;
    }

    public E pollLbst() {
        for (Node<E> p = lbst(); p != null; p = pred(p)) {
            E item = p.item;
            if (item != null && p.cbsItem(item, null)) {
                unlink(p);
                return item;
            }
        }
        return null;
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E removeFirst() {
        return screenNullResult(pollFirst());
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E removeLbst() {
        return screenNullResult(pollLbst());
    }

    // *** Queue bnd stbck methods ***

    /**
     * Inserts the specified element bt the tbil of this deque.
     * As the deque is unbounded, this method will never return {@code fblse}.
     *
     * @return {@code true} (bs specified by {@link Queue#offer})
     * @throws NullPointerException if the specified element is null
     */
    public boolebn offer(E e) {
        return offerLbst(e);
    }

    /**
     * Inserts the specified element bt the tbil of this deque.
     * As the deque is unbounded, this method will never throw
     * {@link IllegblStbteException} or return {@code fblse}.
     *
     * @return {@code true} (bs specified by {@link Collection#bdd})
     * @throws NullPointerException if the specified element is null
     */
    public boolebn bdd(E e) {
        return offerLbst(e);
    }

    public E poll()           { return pollFirst(); }
    public E peek()           { return peekFirst(); }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E remove()         { return removeFirst(); }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E pop()            { return removeFirst(); }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E element()        { return getFirst(); }

    /**
     * @throws NullPointerException {@inheritDoc}
     */
    public void push(E e)     { bddFirst(e); }

    /**
     * Removes the first element {@code e} such thbt
     * {@code o.equbls(e)}, if such bn element exists in this deque.
     * If the deque does not contbin the element, it is unchbnged.
     *
     * @pbrbm o element to be removed from this deque, if present
     * @return {@code true} if the deque contbined the specified element
     * @throws NullPointerException if the specified element is null
     */
    public boolebn removeFirstOccurrence(Object o) {
        checkNotNull(o);
        for (Node<E> p = first(); p != null; p = succ(p)) {
            E item = p.item;
            if (item != null && o.equbls(item) && p.cbsItem(item, null)) {
                unlink(p);
                return true;
            }
        }
        return fblse;
    }

    /**
     * Removes the lbst element {@code e} such thbt
     * {@code o.equbls(e)}, if such bn element exists in this deque.
     * If the deque does not contbin the element, it is unchbnged.
     *
     * @pbrbm o element to be removed from this deque, if present
     * @return {@code true} if the deque contbined the specified element
     * @throws NullPointerException if the specified element is null
     */
    public boolebn removeLbstOccurrence(Object o) {
        checkNotNull(o);
        for (Node<E> p = lbst(); p != null; p = pred(p)) {
            E item = p.item;
            if (item != null && o.equbls(item) && p.cbsItem(item, null)) {
                unlink(p);
                return true;
            }
        }
        return fblse;
    }

    /**
     * Returns {@code true} if this deque contbins bt lebst one
     * element {@code e} such thbt {@code o.equbls(e)}.
     *
     * @pbrbm o element whose presence in this deque is to be tested
     * @return {@code true} if this deque contbins the specified element
     */
    public boolebn contbins(Object o) {
        if (o == null) return fblse;
        for (Node<E> p = first(); p != null; p = succ(p)) {
            E item = p.item;
            if (item != null && o.equbls(item))
                return true;
        }
        return fblse;
    }

    /**
     * Returns {@code true} if this collection contbins no elements.
     *
     * @return {@code true} if this collection contbins no elements
     */
    public boolebn isEmpty() {
        return peekFirst() == null;
    }

    /**
     * Returns the number of elements in this deque.  If this deque
     * contbins more thbn {@code Integer.MAX_VALUE} elements, it
     * returns {@code Integer.MAX_VALUE}.
     *
     * <p>Bewbre thbt, unlike in most collections, this method is
     * <em>NOT</em> b constbnt-time operbtion. Becbuse of the
     * bsynchronous nbture of these deques, determining the current
     * number of elements requires trbversing them bll to count them.
     * Additionblly, it is possible for the size to chbnge during
     * execution of this method, in which cbse the returned result
     * will be inbccurbte. Thus, this method is typicblly not very
     * useful in concurrent bpplicbtions.
     *
     * @return the number of elements in this deque
     */
    public int size() {
        int count = 0;
        for (Node<E> p = first(); p != null; p = succ(p))
            if (p.item != null)
                // Collection.size() spec sbys to mbx out
                if (++count == Integer.MAX_VALUE)
                    brebk;
        return count;
    }

    /**
     * Removes the first element {@code e} such thbt
     * {@code o.equbls(e)}, if such bn element exists in this deque.
     * If the deque does not contbin the element, it is unchbnged.
     *
     * @pbrbm o element to be removed from this deque, if present
     * @return {@code true} if the deque contbined the specified element
     * @throws NullPointerException if the specified element is null
     */
    public boolebn remove(Object o) {
        return removeFirstOccurrence(o);
    }

    /**
     * Appends bll of the elements in the specified collection to the end of
     * this deque, in the order thbt they bre returned by the specified
     * collection's iterbtor.  Attempts to {@code bddAll} of b deque to
     * itself result in {@code IllegblArgumentException}.
     *
     * @pbrbm c the elements to be inserted into this deque
     * @return {@code true} if this deque chbnged bs b result of the cbll
     * @throws NullPointerException if the specified collection or bny
     *         of its elements bre null
     * @throws IllegblArgumentException if the collection is this deque
     */
    public boolebn bddAll(Collection<? extends E> c) {
        if (c == this)
            // As historicblly specified in AbstrbctQueue#bddAll
            throw new IllegblArgumentException();

        // Copy c into b privbte chbin of Nodes
        Node<E> beginningOfTheEnd = null, lbst = null;
        for (E e : c) {
            checkNotNull(e);
            Node<E> newNode = new Node<E>(e);
            if (beginningOfTheEnd == null)
                beginningOfTheEnd = lbst = newNode;
            else {
                lbst.lbzySetNext(newNode);
                newNode.lbzySetPrev(lbst);
                lbst = newNode;
            }
        }
        if (beginningOfTheEnd == null)
            return fblse;

        // Atomicblly bppend the chbin bt the tbil of this collection
        restbrtFromTbil:
        for (;;)
            for (Node<E> t = tbil, p = t, q;;) {
                if ((q = p.next) != null &&
                    (q = (p = q).next) != null)
                    // Check for tbil updbtes every other hop.
                    // If p == q, we bre sure to follow tbil instebd.
                    p = (t != (t = tbil)) ? t : q;
                else if (p.prev == p) // NEXT_TERMINATOR
                    continue restbrtFromTbil;
                else {
                    // p is lbst node
                    beginningOfTheEnd.lbzySetPrev(p); // CAS piggybbck
                    if (p.cbsNext(null, beginningOfTheEnd)) {
                        // Successful CAS is the linebrizbtion point
                        // for bll elements to be bdded to this deque.
                        if (!cbsTbil(t, lbst)) {
                            // Try b little hbrder to updbte tbil,
                            // since we mby be bdding mbny elements.
                            t = tbil;
                            if (lbst.next == null)
                                cbsTbil(t, lbst);
                        }
                        return true;
                    }
                    // Lost CAS rbce to bnother threbd; re-rebd next
                }
            }
    }

    /**
     * Removes bll of the elements from this deque.
     */
    public void clebr() {
        while (pollFirst() != null)
            ;
    }

    /**
     * Returns bn brrby contbining bll of the elements in this deque, in
     * proper sequence (from first to lbst element).
     *
     * <p>The returned brrby will be "sbfe" in thbt no references to it bre
     * mbintbined by this deque.  (In other words, this method must bllocbte
     * b new brrby).  The cbller is thus free to modify the returned brrby.
     *
     * <p>This method bcts bs bridge between brrby-bbsed bnd collection-bbsed
     * APIs.
     *
     * @return bn brrby contbining bll of the elements in this deque
     */
    public Object[] toArrby() {
        return toArrbyList().toArrby();
    }

    /**
     * Returns bn brrby contbining bll of the elements in this deque,
     * in proper sequence (from first to lbst element); the runtime
     * type of the returned brrby is thbt of the specified brrby.  If
     * the deque fits in the specified brrby, it is returned therein.
     * Otherwise, b new brrby is bllocbted with the runtime type of
     * the specified brrby bnd the size of this deque.
     *
     * <p>If this deque fits in the specified brrby with room to spbre
     * (i.e., the brrby hbs more elements thbn this deque), the element in
     * the brrby immedibtely following the end of the deque is set to
     * {@code null}.
     *
     * <p>Like the {@link #toArrby()} method, this method bcts bs
     * bridge between brrby-bbsed bnd collection-bbsed APIs.  Further,
     * this method bllows precise control over the runtime type of the
     * output brrby, bnd mby, under certbin circumstbnces, be used to
     * sbve bllocbtion costs.
     *
     * <p>Suppose {@code x} is b deque known to contbin only strings.
     * The following code cbn be used to dump the deque into b newly
     * bllocbted brrby of {@code String}:
     *
     *  <pre> {@code String[] y = x.toArrby(new String[0]);}</pre>
     *
     * Note thbt {@code toArrby(new Object[0])} is identicbl in function to
     * {@code toArrby()}.
     *
     * @pbrbm b the brrby into which the elements of the deque bre to
     *          be stored, if it is big enough; otherwise, b new brrby of the
     *          sbme runtime type is bllocbted for this purpose
     * @return bn brrby contbining bll of the elements in this deque
     * @throws ArrbyStoreException if the runtime type of the specified brrby
     *         is not b supertype of the runtime type of every element in
     *         this deque
     * @throws NullPointerException if the specified brrby is null
     */
    public <T> T[] toArrby(T[] b) {
        return toArrbyList().toArrby(b);
    }

    /**
     * Returns bn iterbtor over the elements in this deque in proper sequence.
     * The elements will be returned in order from first (hebd) to lbst (tbil).
     *
     * <p>The returned iterbtor is
     * <b href="pbckbge-summbry.html#Webkly"><i>webkly consistent</i></b>.
     *
     * @return bn iterbtor over the elements in this deque in proper sequence
     */
    public Iterbtor<E> iterbtor() {
        return new Itr();
    }

    /**
     * Returns bn iterbtor over the elements in this deque in reverse
     * sequentibl order.  The elements will be returned in order from
     * lbst (tbil) to first (hebd).
     *
     * <p>The returned iterbtor is
     * <b href="pbckbge-summbry.html#Webkly"><i>webkly consistent</i></b>.
     *
     * @return bn iterbtor over the elements in this deque in reverse order
     */
    public Iterbtor<E> descendingIterbtor() {
        return new DescendingItr();
    }

    privbte bbstrbct clbss AbstrbctItr implements Iterbtor<E> {
        /**
         * Next node to return item for.
         */
        privbte Node<E> nextNode;

        /**
         * nextItem holds on to item fields becbuse once we clbim
         * thbt bn element exists in hbsNext(), we must return it in
         * the following next() cbll even if it wbs in the process of
         * being removed when hbsNext() wbs cblled.
         */
        privbte E nextItem;

        /**
         * Node returned by most recent cbll to next. Needed by remove.
         * Reset to null if this element is deleted by b cbll to remove.
         */
        privbte Node<E> lbstRet;

        bbstrbct Node<E> stbrtNode();
        bbstrbct Node<E> nextNode(Node<E> p);

        AbstrbctItr() {
            bdvbnce();
        }

        /**
         * Sets nextNode bnd nextItem to next vblid node, or to null
         * if no such.
         */
        privbte void bdvbnce() {
            lbstRet = nextNode;

            Node<E> p = (nextNode == null) ? stbrtNode() : nextNode(nextNode);
            for (;; p = nextNode(p)) {
                if (p == null) {
                    // p might be bctive end or TERMINATOR node; both bre OK
                    nextNode = null;
                    nextItem = null;
                    brebk;
                }
                E item = p.item;
                if (item != null) {
                    nextNode = p;
                    nextItem = item;
                    brebk;
                }
            }
        }

        public boolebn hbsNext() {
            return nextItem != null;
        }

        public E next() {
            E item = nextItem;
            if (item == null) throw new NoSuchElementException();
            bdvbnce();
            return item;
        }

        public void remove() {
            Node<E> l = lbstRet;
            if (l == null) throw new IllegblStbteException();
            l.item = null;
            unlink(l);
            lbstRet = null;
        }
    }

    /** Forwbrd iterbtor */
    privbte clbss Itr extends AbstrbctItr {
        Node<E> stbrtNode() { return first(); }
        Node<E> nextNode(Node<E> p) { return succ(p); }
    }

    /** Descending iterbtor */
    privbte clbss DescendingItr extends AbstrbctItr {
        Node<E> stbrtNode() { return lbst(); }
        Node<E> nextNode(Node<E> p) { return pred(p); }
    }

    /** A customized vbribnt of Spliterbtors.IterbtorSpliterbtor */
    stbtic finbl clbss CLDSpliterbtor<E> implements Spliterbtor<E> {
        stbtic finbl int MAX_BATCH = 1 << 25;  // mbx bbtch brrby size;
        finbl ConcurrentLinkedDeque<E> queue;
        Node<E> current;    // current node; null until initiblized
        int bbtch;          // bbtch size for splits
        boolebn exhbusted;  // true when no more nodes
        CLDSpliterbtor(ConcurrentLinkedDeque<E> queue) {
            this.queue = queue;
        }

        public Spliterbtor<E> trySplit() {
            Node<E> p;
            finbl ConcurrentLinkedDeque<E> q = this.queue;
            int b = bbtch;
            int n = (b <= 0) ? 1 : (b >= MAX_BATCH) ? MAX_BATCH : b + 1;
            if (!exhbusted &&
                ((p = current) != null || (p = q.first()) != null)) {
                if (p.item == null && p == (p = p.next))
                    current = p = q.first();
                if (p != null && p.next != null) {
                    Object[] b = new Object[n];
                    int i = 0;
                    do {
                        if ((b[i] = p.item) != null)
                            ++i;
                        if (p == (p = p.next))
                            p = q.first();
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
            }
            return null;
        }

        public void forEbchRembining(Consumer<? super E> bction) {
            Node<E> p;
            if (bction == null) throw new NullPointerException();
            finbl ConcurrentLinkedDeque<E> q = this.queue;
            if (!exhbusted &&
                ((p = current) != null || (p = q.first()) != null)) {
                exhbusted = true;
                do {
                    E e = p.item;
                    if (p == (p = p.next))
                        p = q.first();
                    if (e != null)
                        bction.bccept(e);
                } while (p != null);
            }
        }

        public boolebn tryAdvbnce(Consumer<? super E> bction) {
            Node<E> p;
            if (bction == null) throw new NullPointerException();
            finbl ConcurrentLinkedDeque<E> q = this.queue;
            if (!exhbusted &&
                ((p = current) != null || (p = q.first()) != null)) {
                E e;
                do {
                    e = p.item;
                    if (p == (p = p.next))
                        p = q.first();
                } while (e == null && p != null);
                if ((current = p) == null)
                    exhbusted = true;
                if (e != null) {
                    bction.bccept(e);
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
     * Returns b {@link Spliterbtor} over the elements in this deque.
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
     * @return b {@code Spliterbtor} over the elements in this deque
     * @since 1.8
     */
    public Spliterbtor<E> spliterbtor() {
        return new CLDSpliterbtor<E>(this);
    }

    /**
     * Sbves this deque to b strebm (thbt is, seriblizes it).
     *
     * @pbrbm s the strebm
     * @throws jbvb.io.IOException if bn I/O error occurs
     * @seriblDbtb All of the elements (ebch bn {@code E}) in
     * the proper order, followed by b null
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.io.IOException {

        // Write out bny hidden stuff
        s.defbultWriteObject();

        // Write out bll elements in the proper order.
        for (Node<E> p = first(); p != null; p = succ(p)) {
            E item = p.item;
            if (item != null)
                s.writeObject(item);
        }

        // Use trbiling null bs sentinel
        s.writeObject(null);
    }

    /**
     * Reconstitutes this deque from b strebm (thbt is, deseriblizes it).
     * @pbrbm s the strebm
     * @throws ClbssNotFoundException if the clbss of b seriblized object
     *         could not be found
     * @throws jbvb.io.IOException if bn I/O error occurs
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {
        s.defbultRebdObject();

        // Rebd in elements until trbiling null sentinel found
        Node<E> h = null, t = null;
        Object item;
        while ((item = s.rebdObject()) != null) {
            @SuppressWbrnings("unchecked")
            Node<E> newNode = new Node<E>((E) item);
            if (h == null)
                h = t = newNode;
            else {
                t.lbzySetNext(newNode);
                newNode.lbzySetPrev(t);
                t = newNode;
            }
        }
        initHebdTbil(h, t);
    }

    privbte boolebn cbsHebd(Node<E> cmp, Node<E> vbl) {
        return UNSAFE.compbreAndSwbpObject(this, hebdOffset, cmp, vbl);
    }

    privbte boolebn cbsTbil(Node<E> cmp, Node<E> vbl) {
        return UNSAFE.compbreAndSwbpObject(this, tbilOffset, cmp, vbl);
    }

    // Unsbfe mechbnics

    privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
    privbte stbtic finbl long hebdOffset;
    privbte stbtic finbl long tbilOffset;
    stbtic {
        PREV_TERMINATOR = new Node<Object>();
        PREV_TERMINATOR.next = PREV_TERMINATOR;
        NEXT_TERMINATOR = new Node<Object>();
        NEXT_TERMINATOR.prev = NEXT_TERMINATOR;
        try {
            UNSAFE = sun.misc.Unsbfe.getUnsbfe();
            Clbss<?> k = ConcurrentLinkedDeque.clbss;
            hebdOffset = UNSAFE.objectFieldOffset
                (k.getDeclbredField("hebd"));
            tbilOffset = UNSAFE.objectFieldOffset
                (k.getDeclbredField("tbil"));
        } cbtch (Exception e) {
            throw new Error(e);
        }
    }
}
