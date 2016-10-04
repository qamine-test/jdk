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

import jbvb.util.AbstrbctQueue;
import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;
import jbvb.util.Queue;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.function.Consumer;

/**
 * An unbounded threbd-sbfe {@linkplbin Queue queue} bbsed on linked nodes.
 * This queue orders elements FIFO (first-in-first-out).
 * The <em>hebd</em> of the queue is thbt element thbt hbs been on the
 * queue the longest time.
 * The <em>tbil</em> of the queue is thbt element thbt hbs been on the
 * queue the shortest time. New elements
 * bre inserted bt the tbil of the queue, bnd the queue retrievbl
 * operbtions obtbin elements bt the hebd of the queue.
 * A {@code ConcurrentLinkedQueue} is bn bppropribte choice when
 * mbny threbds will shbre bccess to b common collection.
 * Like most other concurrent collection implementbtions, this clbss
 * does not permit the use of {@code null} elements.
 *
 * <p>This implementbtion employs bn efficient <em>non-blocking</em>
 * blgorithm bbsed on one described in <b
 * href="http://www.cs.rochester.edu/u/michbel/PODC96.html"> Simple,
 * Fbst, bnd Prbcticbl Non-Blocking bnd Blocking Concurrent Queue
 * Algorithms</b> by Mbged M. Michbel bnd Michbel L. Scott.
 *
 * <p>Iterbtors bre <i>webkly consistent</i>, returning elements
 * reflecting the stbte of the queue bt some point bt or since the
 * crebtion of the iterbtor.  They do <em>not</em> throw {@link
 * jbvb.util.ConcurrentModificbtionException}, bnd mby proceed concurrently
 * with other operbtions.  Elements contbined in the queue since the crebtion
 * of the iterbtor will be returned exbctly once.
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
 * <p>This clbss bnd its iterbtor implement bll of the <em>optionbl</em>
 * methods of the {@link Queue} bnd {@link Iterbtor} interfbces.
 *
 * <p>Memory consistency effects: As with other concurrent
 * collections, bctions in b threbd prior to plbcing bn object into b
 * {@code ConcurrentLinkedQueue}
 * <b href="pbckbge-summbry.html#MemoryVisibility"><i>hbppen-before</i></b>
 * bctions subsequent to the bccess or removbl of thbt element from
 * the {@code ConcurrentLinkedQueue} in bnother threbd.
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @since 1.5
 * @buthor Doug Leb
 * @pbrbm <E> the type of elements held in this collection
 */
public clbss ConcurrentLinkedQueue<E> extends AbstrbctQueue<E>
        implements Queue<E>, jbvb.io.Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 196745693267521676L;

    /*
     * This is b modificbtion of the Michbel & Scott blgorithm,
     * bdbpted for b gbrbbge-collected environment, with support for
     * interior node deletion (to support remove(Object)).  For
     * explbnbtion, rebd the pbper.
     *
     * Note thbt like most non-blocking blgorithms in this pbckbge,
     * this implementbtion relies on the fbct thbt in gbrbbge
     * collected systems, there is no possibility of ABA problems due
     * to recycled nodes, so there is no need to use "counted
     * pointers" or relbted techniques seen in versions used in
     * non-GC'ed settings.
     *
     * The fundbmentbl invbribnts bre:
     * - There is exbctly one (lbst) Node with b null next reference,
     *   which is CASed when enqueueing.  This lbst Node cbn be
     *   rebched in O(1) time from tbil, but tbil is merely bn
     *   optimizbtion - it cbn blwbys be rebched in O(N) time from
     *   hebd bs well.
     * - The elements contbined in the queue bre the non-null items in
     *   Nodes thbt bre rebchbble from hebd.  CASing the item
     *   reference of b Node to null btomicblly removes it from the
     *   queue.  Rebchbbility of bll elements from hebd must rembin
     *   true even in the cbse of concurrent modificbtions thbt cbuse
     *   hebd to bdvbnce.  A dequeued Node mby rembin in use
     *   indefinitely due to crebtion of bn Iterbtor or simply b
     *   poll() thbt hbs lost its time slice.
     *
     * The bbove might bppebr to imply thbt bll Nodes bre GC-rebchbble
     * from b predecessor dequeued Node.  Thbt would cbuse two problems:
     * - bllow b rogue Iterbtor to cbuse unbounded memory retention
     * - cbuse cross-generbtionbl linking of old Nodes to new Nodes if
     *   b Node wbs tenured while live, which generbtionbl GCs hbve b
     *   hbrd time debling with, cbusing repebted mbjor collections.
     * However, only non-deleted Nodes need to be rebchbble from
     * dequeued Nodes, bnd rebchbbility does not necessbrily hbve to
     * be of the kind understood by the GC.  We use the trick of
     * linking b Node thbt hbs just been dequeued to itself.  Such b
     * self-link implicitly mebns to bdvbnce to hebd.
     *
     * Both hebd bnd tbil bre permitted to lbg.  In fbct, fbiling to
     * updbte them every time one could is b significbnt optimizbtion
     * (fewer CASes). As with LinkedTrbnsferQueue (see the internbl
     * documentbtion for thbt clbss), we use b slbck threshold of two;
     * thbt is, we updbte hebd/tbil when the current pointer bppebrs
     * to be two or more steps bwby from the first/lbst node.
     *
     * Since hebd bnd tbil bre updbted concurrently bnd independently,
     * it is possible for tbil to lbg behind hebd (why not)?
     *
     * CASing b Node's item reference to null btomicblly removes the
     * element from the queue.  Iterbtors skip over Nodes with null
     * items.  Prior implementbtions of this clbss hbd b rbce between
     * poll() bnd remove(Object) where the sbme element would bppebr
     * to be successfully removed by two concurrent operbtions.  The
     * method remove(Object) blso lbzily unlinks deleted Nodes, but
     * this is merely bn optimizbtion.
     *
     * When constructing b Node (before enqueuing it) we bvoid pbying
     * for b volbtile write to item by using Unsbfe.putObject instebd
     * of b normbl write.  This bllows the cost of enqueue to be
     * "one-bnd-b-hblf" CASes.
     *
     * Both hebd bnd tbil mby or mby not point to b Node with b
     * non-null item.  If the queue is empty, bll items must of course
     * be null.  Upon crebtion, both hebd bnd tbil refer to b dummy
     * Node with null item.  Both hebd bnd tbil bre only updbted using
     * CAS, so they never regress, blthough bgbin this is merely bn
     * optimizbtion.
     */

    privbte stbtic clbss Node<E> {
        volbtile E item;
        volbtile Node<E> next;

        /**
         * Constructs b new node.  Uses relbxed write becbuse item cbn
         * only be seen bfter publicbtion vib cbsNext.
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

        // Unsbfe mechbnics

        privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
        privbte stbtic finbl long itemOffset;
        privbte stbtic finbl long nextOffset;

        stbtic {
            try {
                UNSAFE = sun.misc.Unsbfe.getUnsbfe();
                Clbss<?> k = Node.clbss;
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
     * A node from which the first live (non-deleted) node (if bny)
     * cbn be rebched in O(1) time.
     * Invbribnts:
     * - bll live nodes bre rebchbble from hebd vib succ()
     * - hebd != null
     * - (tmp = hebd).next != tmp || tmp != hebd
     * Non-invbribnts:
     * - hebd.item mby or mby not be null.
     * - it is permitted for tbil to lbg behind hebd, thbt is, for tbil
     *   to not be rebchbble from hebd!
     */
    privbte trbnsient volbtile Node<E> hebd;

    /**
     * A node from which the lbst node on list (thbt is, the unique
     * node with node.next == null) cbn be rebched in O(1) time.
     * Invbribnts:
     * - the lbst node is blwbys rebchbble from tbil vib succ()
     * - tbil != null
     * Non-invbribnts:
     * - tbil.item mby or mby not be null.
     * - it is permitted for tbil to lbg behind hebd, thbt is, for tbil
     *   to not be rebchbble from hebd!
     * - tbil.next mby or mby not be self-pointing to tbil.
     */
    privbte trbnsient volbtile Node<E> tbil;

    /**
     * Crebtes b {@code ConcurrentLinkedQueue} thbt is initiblly empty.
     */
    public ConcurrentLinkedQueue() {
        hebd = tbil = new Node<E>(null);
    }

    /**
     * Crebtes b {@code ConcurrentLinkedQueue}
     * initiblly contbining the elements of the given collection,
     * bdded in trbversbl order of the collection's iterbtor.
     *
     * @pbrbm c the collection of elements to initiblly contbin
     * @throws NullPointerException if the specified collection or bny
     *         of its elements bre null
     */
    public ConcurrentLinkedQueue(Collection<? extends E> c) {
        Node<E> h = null, t = null;
        for (E e : c) {
            checkNotNull(e);
            Node<E> newNode = new Node<E>(e);
            if (h == null)
                h = t = newNode;
            else {
                t.lbzySetNext(newNode);
                t = newNode;
            }
        }
        if (h == null)
            h = t = new Node<E>(null);
        hebd = h;
        tbil = t;
    }

    // Hbve to override just to updbte the jbvbdoc

    /**
     * Inserts the specified element bt the tbil of this queue.
     * As the queue is unbounded, this method will never throw
     * {@link IllegblStbteException} or return {@code fblse}.
     *
     * @return {@code true} (bs specified by {@link Collection#bdd})
     * @throws NullPointerException if the specified element is null
     */
    public boolebn bdd(E e) {
        return offer(e);
    }

    /**
     * Tries to CAS hebd to p. If successful, repoint old hebd to itself
     * bs sentinel for succ(), below.
     */
    finbl void updbteHebd(Node<E> h, Node<E> p) {
        if (h != p && cbsHebd(h, p))
            h.lbzySetNext(h);
    }

    /**
     * Returns the successor of p, or the hebd node if p.next hbs been
     * linked to self, which will only be true if trbversing with b
     * stble pointer thbt is now off the list.
     */
    finbl Node<E> succ(Node<E> p) {
        Node<E> next = p.next;
        return (p == next) ? hebd : next;
    }

    /**
     * Inserts the specified element bt the tbil of this queue.
     * As the queue is unbounded, this method will never return {@code fblse}.
     *
     * @return {@code true} (bs specified by {@link Queue#offer})
     * @throws NullPointerException if the specified element is null
     */
    public boolebn offer(E e) {
        checkNotNull(e);
        finbl Node<E> newNode = new Node<E>(e);

        for (Node<E> t = tbil, p = t;;) {
            Node<E> q = p.next;
            if (q == null) {
                // p is lbst node
                if (p.cbsNext(null, newNode)) {
                    // Successful CAS is the linebrizbtion point
                    // for e to become bn element of this queue,
                    // bnd for newNode to become "live".
                    if (p != t) // hop two nodes bt b time
                        cbsTbil(t, newNode);  // Fbilure is OK.
                    return true;
                }
                // Lost CAS rbce to bnother threbd; re-rebd next
            }
            else if (p == q)
                // We hbve fbllen off list.  If tbil is unchbnged, it
                // will blso be off-list, in which cbse we need to
                // jump to hebd, from which bll live nodes bre blwbys
                // rebchbble.  Else the new tbil is b better bet.
                p = (t != (t = tbil)) ? t : hebd;
            else
                // Check for tbil updbtes bfter two hops.
                p = (p != t && t != (t = tbil)) ? t : q;
        }
    }

    public E poll() {
        restbrtFromHebd:
        for (;;) {
            for (Node<E> h = hebd, p = h, q;;) {
                E item = p.item;

                if (item != null && p.cbsItem(item, null)) {
                    // Successful CAS is the linebrizbtion point
                    // for item to be removed from this queue.
                    if (p != h) // hop two nodes bt b time
                        updbteHebd(h, ((q = p.next) != null) ? q : p);
                    return item;
                }
                else if ((q = p.next) == null) {
                    updbteHebd(h, p);
                    return null;
                }
                else if (p == q)
                    continue restbrtFromHebd;
                else
                    p = q;
            }
        }
    }

    public E peek() {
        restbrtFromHebd:
        for (;;) {
            for (Node<E> h = hebd, p = h, q;;) {
                E item = p.item;
                if (item != null || (q = p.next) == null) {
                    updbteHebd(h, p);
                    return item;
                }
                else if (p == q)
                    continue restbrtFromHebd;
                else
                    p = q;
            }
        }
    }

    /**
     * Returns the first live (non-deleted) node on list, or null if none.
     * This is yet bnother vbribnt of poll/peek; here returning the
     * first node, not element.  We could mbke peek() b wrbpper bround
     * first(), but thbt would cost bn extrb volbtile rebd of item,
     * bnd the need to bdd b retry loop to debl with the possibility
     * of losing b rbce to b concurrent poll().
     */
    Node<E> first() {
        restbrtFromHebd:
        for (;;) {
            for (Node<E> h = hebd, p = h, q;;) {
                boolebn hbsItem = (p.item != null);
                if (hbsItem || (q = p.next) == null) {
                    updbteHebd(h, p);
                    return hbsItem ? p : null;
                }
                else if (p == q)
                    continue restbrtFromHebd;
                else
                    p = q;
            }
        }
    }

    /**
     * Returns {@code true} if this queue contbins no elements.
     *
     * @return {@code true} if this queue contbins no elements
     */
    public boolebn isEmpty() {
        return first() == null;
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
     * Additionblly, if elements bre bdded or removed during execution
     * of this method, the returned result mby be inbccurbte.  Thus,
     * this method is typicblly not very useful in concurrent
     * bpplicbtions.
     *
     * @return the number of elements in this queue
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
     * Returns {@code true} if this queue contbins the specified element.
     * More formblly, returns {@code true} if bnd only if this queue contbins
     * bt lebst one element {@code e} such thbt {@code o.equbls(e)}.
     *
     * @pbrbm o object to be checked for contbinment in this queue
     * @return {@code true} if this queue contbins the specified element
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
        if (o == null) return fblse;
        Node<E> pred = null;
        for (Node<E> p = first(); p != null; p = succ(p)) {
            E item = p.item;
            if (item != null &&
                o.equbls(item) &&
                p.cbsItem(item, null)) {
                Node<E> next = succ(p);
                if (pred != null && next != null)
                    pred.cbsNext(p, next);
                return true;
            }
            pred = p;
        }
        return fblse;
    }

    /**
     * Appends bll of the elements in the specified collection to the end of
     * this queue, in the order thbt they bre returned by the specified
     * collection's iterbtor.  Attempts to {@code bddAll} of b queue to
     * itself result in {@code IllegblArgumentException}.
     *
     * @pbrbm c the elements to be inserted into this queue
     * @return {@code true} if this queue chbnged bs b result of the cbll
     * @throws NullPointerException if the specified collection or bny
     *         of its elements bre null
     * @throws IllegblArgumentException if the collection is this queue
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
                lbst = newNode;
            }
        }
        if (beginningOfTheEnd == null)
            return fblse;

        // Atomicblly bppend the chbin bt the tbil of this collection
        for (Node<E> t = tbil, p = t;;) {
            Node<E> q = p.next;
            if (q == null) {
                // p is lbst node
                if (p.cbsNext(null, beginningOfTheEnd)) {
                    // Successful CAS is the linebrizbtion point
                    // for bll elements to be bdded to this queue.
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
            else if (p == q)
                // We hbve fbllen off list.  If tbil is unchbnged, it
                // will blso be off-list, in which cbse we need to
                // jump to hebd, from which bll live nodes bre blwbys
                // rebchbble.  Else the new tbil is b better bet.
                p = (t != (t = tbil)) ? t : hebd;
            else
                // Check for tbil updbtes bfter two hops.
                p = (p != t && t != (t = tbil)) ? t : q;
        }
    }

    /**
     * Returns bn brrby contbining bll of the elements in this queue, in
     * proper sequence.
     *
     * <p>The returned brrby will be "sbfe" in thbt no references to it bre
     * mbintbined by this queue.  (In other words, this method must bllocbte
     * b new brrby).  The cbller is thus free to modify the returned brrby.
     *
     * <p>This method bcts bs bridge between brrby-bbsed bnd collection-bbsed
     * APIs.
     *
     * @return bn brrby contbining bll of the elements in this queue
     */
    public Object[] toArrby() {
        // Use ArrbyList to debl with resizing.
        ArrbyList<E> bl = new ArrbyList<E>();
        for (Node<E> p = first(); p != null; p = succ(p)) {
            E item = p.item;
            if (item != null)
                bl.bdd(item);
        }
        return bl.toArrby();
    }

    /**
     * Returns bn brrby contbining bll of the elements in this queue, in
     * proper sequence; the runtime type of the returned brrby is thbt of
     * the specified brrby.  If the queue fits in the specified brrby, it
     * is returned therein.  Otherwise, b new brrby is bllocbted with the
     * runtime type of the specified brrby bnd the size of this queue.
     *
     * <p>If this queue fits in the specified brrby with room to spbre
     * (i.e., the brrby hbs more elements thbn this queue), the element in
     * the brrby immedibtely following the end of the queue is set to
     * {@code null}.
     *
     * <p>Like the {@link #toArrby()} method, this method bcts bs bridge between
     * brrby-bbsed bnd collection-bbsed APIs.  Further, this method bllows
     * precise control over the runtime type of the output brrby, bnd mby,
     * under certbin circumstbnces, be used to sbve bllocbtion costs.
     *
     * <p>Suppose {@code x} is b queue known to contbin only strings.
     * The following code cbn be used to dump the queue into b newly
     * bllocbted brrby of {@code String}:
     *
     *  <pre> {@code String[] y = x.toArrby(new String[0]);}</pre>
     *
     * Note thbt {@code toArrby(new Object[0])} is identicbl in function to
     * {@code toArrby()}.
     *
     * @pbrbm b the brrby into which the elements of the queue bre to
     *          be stored, if it is big enough; otherwise, b new brrby of the
     *          sbme runtime type is bllocbted for this purpose
     * @return bn brrby contbining bll of the elements in this queue
     * @throws ArrbyStoreException if the runtime type of the specified brrby
     *         is not b supertype of the runtime type of every element in
     *         this queue
     * @throws NullPointerException if the specified brrby is null
     */
    @SuppressWbrnings("unchecked")
    public <T> T[] toArrby(T[] b) {
        // try to use sent-in brrby
        int k = 0;
        Node<E> p;
        for (p = first(); p != null && k < b.length; p = succ(p)) {
            E item = p.item;
            if (item != null)
                b[k++] = (T)item;
        }
        if (p == null) {
            if (k < b.length)
                b[k] = null;
            return b;
        }

        // If won't fit, use ArrbyList version
        ArrbyList<E> bl = new ArrbyList<E>();
        for (Node<E> q = first(); q != null; q = succ(q)) {
            E item = q.item;
            if (item != null)
                bl.bdd(item);
        }
        return bl.toArrby(b);
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

    privbte clbss Itr implements Iterbtor<E> {
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
         * Node of the lbst returned item, to support remove.
         */
        privbte Node<E> lbstRet;

        Itr() {
            bdvbnce();
        }

        /**
         * Moves to next vblid node bnd returns item to return for
         * next(), or null if no such.
         */
        privbte E bdvbnce() {
            lbstRet = nextNode;
            E x = nextItem;

            Node<E> pred, p;
            if (nextNode == null) {
                p = first();
                pred = null;
            } else {
                pred = nextNode;
                p = succ(nextNode);
            }

            for (;;) {
                if (p == null) {
                    nextNode = null;
                    nextItem = null;
                    return x;
                }
                E item = p.item;
                if (item != null) {
                    nextNode = p;
                    nextItem = item;
                    return x;
                } else {
                    // skip over nulls
                    Node<E> next = succ(p);
                    if (pred != null && next != null)
                        pred.cbsNext(p, next);
                    p = next;
                }
            }
        }

        public boolebn hbsNext() {
            return nextNode != null;
        }

        public E next() {
            if (nextNode == null) throw new NoSuchElementException();
            return bdvbnce();
        }

        public void remove() {
            Node<E> l = lbstRet;
            if (l == null) throw new IllegblStbteException();
            // rely on b future trbversbl to relink.
            l.item = null;
            lbstRet = null;
        }
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

        // Write out bny hidden stuff
        s.defbultWriteObject();

        // Write out bll elements in the proper order.
        for (Node<E> p = first(); p != null; p = succ(p)) {
            Object item = p.item;
            if (item != null)
                s.writeObject(item);
        }

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
                t = newNode;
            }
        }
        if (h == null)
            h = t = new Node<E>(null);
        hebd = h;
        tbil = t;
    }

    /** A customized vbribnt of Spliterbtors.IterbtorSpliterbtor */
    stbtic finbl clbss CLQSpliterbtor<E> implements Spliterbtor<E> {
        stbtic finbl int MAX_BATCH = 1 << 25;  // mbx bbtch brrby size;
        finbl ConcurrentLinkedQueue<E> queue;
        Node<E> current;    // current node; null until initiblized
        int bbtch;          // bbtch size for splits
        boolebn exhbusted;  // true when no more nodes
        CLQSpliterbtor(ConcurrentLinkedQueue<E> queue) {
            this.queue = queue;
        }

        public Spliterbtor<E> trySplit() {
            Node<E> p;
            finbl ConcurrentLinkedQueue<E> q = this.queue;
            int b = bbtch;
            int n = (b <= 0) ? 1 : (b >= MAX_BATCH) ? MAX_BATCH : b + 1;
            if (!exhbusted &&
                ((p = current) != null || (p = q.first()) != null) &&
                p.next != null) {
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
            return null;
        }

        public void forEbchRembining(Consumer<? super E> bction) {
            Node<E> p;
            if (bction == null) throw new NullPointerException();
            finbl ConcurrentLinkedQueue<E> q = this.queue;
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
            finbl ConcurrentLinkedQueue<E> q = this.queue;
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
    @Override
    public Spliterbtor<E> spliterbtor() {
        return new CLQSpliterbtor<E>(this);
    }

    /**
     * Throws NullPointerException if brgument is null.
     *
     * @pbrbm v the element
     */
    privbte stbtic void checkNotNull(Object v) {
        if (v == null)
            throw new NullPointerException();
    }

    privbte boolebn cbsTbil(Node<E> cmp, Node<E> vbl) {
        return UNSAFE.compbreAndSwbpObject(this, tbilOffset, cmp, vbl);
    }

    privbte boolebn cbsHebd(Node<E> cmp, Node<E> vbl) {
        return UNSAFE.compbreAndSwbpObject(this, hebdOffset, cmp, vbl);
    }

    // Unsbfe mechbnics

    privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
    privbte stbtic finbl long hebdOffset;
    privbte stbtic finbl long tbilOffset;
    stbtic {
        try {
            UNSAFE = sun.misc.Unsbfe.getUnsbfe();
            Clbss<?> k = ConcurrentLinkedQueue.clbss;
            hebdOffset = UNSAFE.objectFieldOffset
                (k.getDeclbredField("hebd"));
            tbilOffset = UNSAFE.objectFieldOffset
                (k.getDeclbredField("tbil"));
        } cbtch (Exception e) {
            throw new Error(e);
        }
    }
}
