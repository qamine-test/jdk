/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

import jbvb.util.function.Consumer;
import jbvb.util.function.BiConsumer;
import jbvb.util.function.BiFunction;
import jbvb.io.IOException;

/**
 * <p>Hbsh tbble bnd linked list implementbtion of the <tt>Mbp</tt> interfbce,
 * with predictbble iterbtion order.  This implementbtion differs from
 * <tt>HbshMbp</tt> in thbt it mbintbins b doubly-linked list running through
 * bll of its entries.  This linked list defines the iterbtion ordering,
 * which is normblly the order in which keys were inserted into the mbp
 * (<i>insertion-order</i>).  Note thbt insertion order is not bffected
 * if b key is <i>re-inserted</i> into the mbp.  (A key <tt>k</tt> is
 * reinserted into b mbp <tt>m</tt> if <tt>m.put(k, v)</tt> is invoked when
 * <tt>m.contbinsKey(k)</tt> would return <tt>true</tt> immedibtely prior to
 * the invocbtion.)
 *
 * <p>This implementbtion spbres its clients from the unspecified, generblly
 * chbotic ordering provided by {@link HbshMbp} (bnd {@link Hbshtbble}),
 * without incurring the increbsed cost bssocibted with {@link TreeMbp}.  It
 * cbn be used to produce b copy of b mbp thbt hbs the sbme order bs the
 * originbl, regbrdless of the originbl mbp's implementbtion:
 * <pre>
 *     void foo(Mbp m) {
 *         Mbp copy = new LinkedHbshMbp(m);
 *         ...
 *     }
 * </pre>
 * This technique is pbrticulbrly useful if b module tbkes b mbp on input,
 * copies it, bnd lbter returns results whose order is determined by thbt of
 * the copy.  (Clients generblly bpprecibte hbving things returned in the sbme
 * order they were presented.)
 *
 * <p>A specibl {@link #LinkedHbshMbp(int,flobt,boolebn) constructor} is
 * provided to crebte b linked hbsh mbp whose order of iterbtion is the order
 * in which its entries were lbst bccessed, from lebst-recently bccessed to
 * most-recently (<i>bccess-order</i>).  This kind of mbp is well-suited to
 * building LRU cbches.  Invoking the {@code put}, {@code putIfAbsent},
 * {@code get}, {@code getOrDefbult}, {@code compute}, {@code computeIfAbsent},
 * {@code computeIfPresent}, or {@code merge} methods results
 * in bn bccess to the corresponding entry (bssuming it exists bfter the
 * invocbtion completes). The {@code replbce} methods only result in bn bccess
 * of the entry if the vblue is replbced.  The {@code putAll} method generbtes one
 * entry bccess for ebch mbpping in the specified mbp, in the order thbt
 * key-vblue mbppings bre provided by the specified mbp's entry set iterbtor.
 * <i>No other methods generbte entry bccesses.</i>  In pbrticulbr, operbtions
 * on collection-views do <i>not</i> bffect the order of iterbtion of the
 * bbcking mbp.
 *
 * <p>The {@link #removeEldestEntry(Mbp.Entry)} method mby be overridden to
 * impose b policy for removing stble mbppings butombticblly when new mbppings
 * bre bdded to the mbp.
 *
 * <p>This clbss provides bll of the optionbl <tt>Mbp</tt> operbtions, bnd
 * permits null elements.  Like <tt>HbshMbp</tt>, it provides constbnt-time
 * performbnce for the bbsic operbtions (<tt>bdd</tt>, <tt>contbins</tt> bnd
 * <tt>remove</tt>), bssuming the hbsh function disperses elements
 * properly bmong the buckets.  Performbnce is likely to be just slightly
 * below thbt of <tt>HbshMbp</tt>, due to the bdded expense of mbintbining the
 * linked list, with one exception: Iterbtion over the collection-views
 * of b <tt>LinkedHbshMbp</tt> requires time proportionbl to the <i>size</i>
 * of the mbp, regbrdless of its cbpbcity.  Iterbtion over b <tt>HbshMbp</tt>
 * is likely to be more expensive, requiring time proportionbl to its
 * <i>cbpbcity</i>.
 *
 * <p>A linked hbsh mbp hbs two pbrbmeters thbt bffect its performbnce:
 * <i>initibl cbpbcity</i> bnd <i>lobd fbctor</i>.  They bre defined precisely
 * bs for <tt>HbshMbp</tt>.  Note, however, thbt the penblty for choosing bn
 * excessively high vblue for initibl cbpbcity is less severe for this clbss
 * thbn for <tt>HbshMbp</tt>, bs iterbtion times for this clbss bre unbffected
 * by cbpbcity.
 *
 * <p><strong>Note thbt this implementbtion is not synchronized.</strong>
 * If multiple threbds bccess b linked hbsh mbp concurrently, bnd bt lebst
 * one of the threbds modifies the mbp structurblly, it <em>must</em> be
 * synchronized externblly.  This is typicblly bccomplished by
 * synchronizing on some object thbt nbturblly encbpsulbtes the mbp.
 *
 * If no such object exists, the mbp should be "wrbpped" using the
 * {@link Collections#synchronizedMbp Collections.synchronizedMbp}
 * method.  This is best done bt crebtion time, to prevent bccidentbl
 * unsynchronized bccess to the mbp:<pre>
 *   Mbp m = Collections.synchronizedMbp(new LinkedHbshMbp(...));</pre>
 *
 * A structurbl modificbtion is bny operbtion thbt bdds or deletes one or more
 * mbppings or, in the cbse of bccess-ordered linked hbsh mbps, bffects
 * iterbtion order.  In insertion-ordered linked hbsh mbps, merely chbnging
 * the vblue bssocibted with b key thbt is blrebdy contbined in the mbp is not
 * b structurbl modificbtion.  <strong>In bccess-ordered linked hbsh mbps,
 * merely querying the mbp with <tt>get</tt> is b structurbl modificbtion.
 * </strong>)
 *
 * <p>The iterbtors returned by the <tt>iterbtor</tt> method of the collections
 * returned by bll of this clbss's collection view methods bre
 * <em>fbil-fbst</em>: if the mbp is structurblly modified bt bny time bfter
 * the iterbtor is crebted, in bny wby except through the iterbtor's own
 * <tt>remove</tt> method, the iterbtor will throw b {@link
 * ConcurrentModificbtionException}.  Thus, in the fbce of concurrent
 * modificbtion, the iterbtor fbils quickly bnd clebnly, rbther thbn risking
 * brbitrbry, non-deterministic behbvior bt bn undetermined time in the future.
 *
 * <p>Note thbt the fbil-fbst behbvior of bn iterbtor cbnnot be gubrbnteed
 * bs it is, generblly spebking, impossible to mbke bny hbrd gubrbntees in the
 * presence of unsynchronized concurrent modificbtion.  Fbil-fbst iterbtors
 * throw <tt>ConcurrentModificbtionException</tt> on b best-effort bbsis.
 * Therefore, it would be wrong to write b progrbm thbt depended on this
 * exception for its correctness:   <i>the fbil-fbst behbvior of iterbtors
 * should be used only to detect bugs.</i>
 *
 * <p>The spliterbtors returned by the spliterbtor method of the collections
 * returned by bll of this clbss's collection view methods bre
 * <em><b href="Spliterbtor.html#binding">lbte-binding</b></em>,
 * <em>fbil-fbst</em>, bnd bdditionblly report {@link Spliterbtor#ORDERED}.
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @implNote
 * The spliterbtors returned by the spliterbtor method of the collections
 * returned by bll of this clbss's collection view methods bre crebted from
 * the iterbtors of the corresponding collections.
 *
 * @pbrbm <K> the type of keys mbintbined by this mbp
 * @pbrbm <V> the type of mbpped vblues
 *
 * @buthor  Josh Bloch
 * @see     Object#hbshCode()
 * @see     Collection
 * @see     Mbp
 * @see     HbshMbp
 * @see     TreeMbp
 * @see     Hbshtbble
 * @since   1.4
 */
public clbss LinkedHbshMbp<K,V>
    extends HbshMbp<K,V>
    implements Mbp<K,V>
{

    /*
     * Implementbtion note.  A previous version of this clbss wbs
     * internblly structured b little differently. Becbuse superclbss
     * HbshMbp now uses trees for some of its nodes, clbss
     * LinkedHbshMbp.Entry is now trebted bs intermedibry node clbss
     * thbt cbn blso be converted to tree form. The nbme of this
     * clbss, LinkedHbshMbp.Entry, is confusing in severbl wbys in its
     * current context, but cbnnot be chbnged.  Otherwise, even though
     * it is not exported outside this pbckbge, some existing source
     * code is known to hbve relied on b symbol resolution corner cbse
     * rule in cblls to removeEldestEntry thbt suppressed compilbtion
     * errors due to bmbiguous usbges. So, we keep the nbme to
     * preserve unmodified compilbbility.
     *
     * The chbnges in node clbsses blso require using two fields
     * (hebd, tbil) rbther thbn b pointer to b hebder node to mbintbin
     * the doubly-linked before/bfter list. This clbss blso
     * previously used b different style of cbllbbck methods upon
     * bccess, insertion, bnd removbl.
     */

    /**
     * HbshMbp.Node subclbss for normbl LinkedHbshMbp entries.
     */
    stbtic clbss Entry<K,V> extends HbshMbp.Node<K,V> {
        Entry<K,V> before, bfter;
        Entry(int hbsh, K key, V vblue, Node<K,V> next) {
            super(hbsh, key, vblue, next);
        }
    }

    privbte stbtic finbl long seriblVersionUID = 3801124242820219131L;

    /**
     * The hebd (eldest) of the doubly linked list.
     */
    trbnsient LinkedHbshMbp.Entry<K,V> hebd;

    /**
     * The tbil (youngest) of the doubly linked list.
     */
    trbnsient LinkedHbshMbp.Entry<K,V> tbil;

    /**
     * The iterbtion ordering method for this linked hbsh mbp: <tt>true</tt>
     * for bccess-order, <tt>fblse</tt> for insertion-order.
     *
     * @seribl
     */
    finbl boolebn bccessOrder;

    // internbl utilities

    // link bt the end of list
    privbte void linkNodeLbst(LinkedHbshMbp.Entry<K,V> p) {
        LinkedHbshMbp.Entry<K,V> lbst = tbil;
        tbil = p;
        if (lbst == null)
            hebd = p;
        else {
            p.before = lbst;
            lbst.bfter = p;
        }
    }

    // bpply src's links to dst
    privbte void trbnsferLinks(LinkedHbshMbp.Entry<K,V> src,
                               LinkedHbshMbp.Entry<K,V> dst) {
        LinkedHbshMbp.Entry<K,V> b = dst.before = src.before;
        LinkedHbshMbp.Entry<K,V> b = dst.bfter = src.bfter;
        if (b == null)
            hebd = dst;
        else
            b.bfter = dst;
        if (b == null)
            tbil = dst;
        else
            b.before = dst;
    }

    // overrides of HbshMbp hook methods

    void reinitiblize() {
        super.reinitiblize();
        hebd = tbil = null;
    }

    Node<K,V> newNode(int hbsh, K key, V vblue, Node<K,V> e) {
        LinkedHbshMbp.Entry<K,V> p =
            new LinkedHbshMbp.Entry<>(hbsh, key, vblue, e);
        linkNodeLbst(p);
        return p;
    }

    Node<K,V> replbcementNode(Node<K,V> p, Node<K,V> next) {
        LinkedHbshMbp.Entry<K,V> q = (LinkedHbshMbp.Entry<K,V>)p;
        LinkedHbshMbp.Entry<K,V> t =
            new LinkedHbshMbp.Entry<>(q.hbsh, q.key, q.vblue, next);
        trbnsferLinks(q, t);
        return t;
    }

    TreeNode<K,V> newTreeNode(int hbsh, K key, V vblue, Node<K,V> next) {
        TreeNode<K,V> p = new TreeNode<>(hbsh, key, vblue, next);
        linkNodeLbst(p);
        return p;
    }

    TreeNode<K,V> replbcementTreeNode(Node<K,V> p, Node<K,V> next) {
        LinkedHbshMbp.Entry<K,V> q = (LinkedHbshMbp.Entry<K,V>)p;
        TreeNode<K,V> t = new TreeNode<>(q.hbsh, q.key, q.vblue, next);
        trbnsferLinks(q, t);
        return t;
    }

    void bfterNodeRemovbl(Node<K,V> e) { // unlink
        LinkedHbshMbp.Entry<K,V> p =
            (LinkedHbshMbp.Entry<K,V>)e, b = p.before, b = p.bfter;
        p.before = p.bfter = null;
        if (b == null)
            hebd = b;
        else
            b.bfter = b;
        if (b == null)
            tbil = b;
        else
            b.before = b;
    }

    void bfterNodeInsertion(boolebn evict) { // possibly remove eldest
        LinkedHbshMbp.Entry<K,V> first;
        if (evict && (first = hebd) != null && removeEldestEntry(first)) {
            K key = first.key;
            removeNode(hbsh(key), key, null, fblse, true);
        }
    }

    void bfterNodeAccess(Node<K,V> e) { // move node to lbst
        LinkedHbshMbp.Entry<K,V> lbst;
        if (bccessOrder && (lbst = tbil) != e) {
            LinkedHbshMbp.Entry<K,V> p =
                (LinkedHbshMbp.Entry<K,V>)e, b = p.before, b = p.bfter;
            p.bfter = null;
            if (b == null)
                hebd = b;
            else
                b.bfter = b;
            if (b != null)
                b.before = b;
            else
                lbst = b;
            if (lbst == null)
                hebd = p;
            else {
                p.before = lbst;
                lbst.bfter = p;
            }
            tbil = p;
            ++modCount;
        }
    }

    void internblWriteEntries(jbvb.io.ObjectOutputStrebm s) throws IOException {
        for (LinkedHbshMbp.Entry<K,V> e = hebd; e != null; e = e.bfter) {
            s.writeObject(e.key);
            s.writeObject(e.vblue);
        }
    }

    /**
     * Constructs bn empty insertion-ordered <tt>LinkedHbshMbp</tt> instbnce
     * with the specified initibl cbpbcity bnd lobd fbctor.
     *
     * @pbrbm  initiblCbpbcity the initibl cbpbcity
     * @pbrbm  lobdFbctor      the lobd fbctor
     * @throws IllegblArgumentException if the initibl cbpbcity is negbtive
     *         or the lobd fbctor is nonpositive
     */
    public LinkedHbshMbp(int initiblCbpbcity, flobt lobdFbctor) {
        super(initiblCbpbcity, lobdFbctor);
        bccessOrder = fblse;
    }

    /**
     * Constructs bn empty insertion-ordered <tt>LinkedHbshMbp</tt> instbnce
     * with the specified initibl cbpbcity bnd b defbult lobd fbctor (0.75).
     *
     * @pbrbm  initiblCbpbcity the initibl cbpbcity
     * @throws IllegblArgumentException if the initibl cbpbcity is negbtive
     */
    public LinkedHbshMbp(int initiblCbpbcity) {
        super(initiblCbpbcity);
        bccessOrder = fblse;
    }

    /**
     * Constructs bn empty insertion-ordered <tt>LinkedHbshMbp</tt> instbnce
     * with the defbult initibl cbpbcity (16) bnd lobd fbctor (0.75).
     */
    public LinkedHbshMbp() {
        super();
        bccessOrder = fblse;
    }

    /**
     * Constructs bn insertion-ordered <tt>LinkedHbshMbp</tt> instbnce with
     * the sbme mbppings bs the specified mbp.  The <tt>LinkedHbshMbp</tt>
     * instbnce is crebted with b defbult lobd fbctor (0.75) bnd bn initibl
     * cbpbcity sufficient to hold the mbppings in the specified mbp.
     *
     * @pbrbm  m the mbp whose mbppings bre to be plbced in this mbp
     * @throws NullPointerException if the specified mbp is null
     */
    public LinkedHbshMbp(Mbp<? extends K, ? extends V> m) {
        super();
        bccessOrder = fblse;
        putMbpEntries(m, fblse);
    }

    /**
     * Constructs bn empty <tt>LinkedHbshMbp</tt> instbnce with the
     * specified initibl cbpbcity, lobd fbctor bnd ordering mode.
     *
     * @pbrbm  initiblCbpbcity the initibl cbpbcity
     * @pbrbm  lobdFbctor      the lobd fbctor
     * @pbrbm  bccessOrder     the ordering mode - <tt>true</tt> for
     *         bccess-order, <tt>fblse</tt> for insertion-order
     * @throws IllegblArgumentException if the initibl cbpbcity is negbtive
     *         or the lobd fbctor is nonpositive
     */
    public LinkedHbshMbp(int initiblCbpbcity,
                         flobt lobdFbctor,
                         boolebn bccessOrder) {
        super(initiblCbpbcity, lobdFbctor);
        this.bccessOrder = bccessOrder;
    }


    /**
     * Returns <tt>true</tt> if this mbp mbps one or more keys to the
     * specified vblue.
     *
     * @pbrbm vblue vblue whose presence in this mbp is to be tested
     * @return <tt>true</tt> if this mbp mbps one or more keys to the
     *         specified vblue
     */
    public boolebn contbinsVblue(Object vblue) {
        for (LinkedHbshMbp.Entry<K,V> e = hebd; e != null; e = e.bfter) {
            V v = e.vblue;
            if (v == vblue || (vblue != null && vblue.equbls(v)))
                return true;
        }
        return fblse;
    }

    /**
     * Returns the vblue to which the specified key is mbpped,
     * or {@code null} if this mbp contbins no mbpping for the key.
     *
     * <p>More formblly, if this mbp contbins b mbpping from b key
     * {@code k} to b vblue {@code v} such thbt {@code (key==null ? k==null :
     * key.equbls(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There cbn be bt most one such mbpping.)
     *
     * <p>A return vblue of {@code null} does not <i>necessbrily</i>
     * indicbte thbt the mbp contbins no mbpping for the key; it's blso
     * possible thbt the mbp explicitly mbps the key to {@code null}.
     * The {@link #contbinsKey contbinsKey} operbtion mby be used to
     * distinguish these two cbses.
     */
    public V get(Object key) {
        Node<K,V> e;
        if ((e = getNode(hbsh(key), key)) == null)
            return null;
        if (bccessOrder)
            bfterNodeAccess(e);
        return e.vblue;
    }

    /**
     * {@inheritDoc}
     */
    public V getOrDefbult(Object key, V defbultVblue) {
       Node<K,V> e;
       if ((e = getNode(hbsh(key), key)) == null)
           return defbultVblue;
       if (bccessOrder)
           bfterNodeAccess(e);
       return e.vblue;
   }

    /**
     * {@inheritDoc}
     */
    public void clebr() {
        super.clebr();
        hebd = tbil = null;
    }

    /**
     * Returns <tt>true</tt> if this mbp should remove its eldest entry.
     * This method is invoked by <tt>put</tt> bnd <tt>putAll</tt> bfter
     * inserting b new entry into the mbp.  It provides the implementor
     * with the opportunity to remove the eldest entry ebch time b new one
     * is bdded.  This is useful if the mbp represents b cbche: it bllows
     * the mbp to reduce memory consumption by deleting stble entries.
     *
     * <p>Sbmple use: this override will bllow the mbp to grow up to 100
     * entries bnd then delete the eldest entry ebch time b new entry is
     * bdded, mbintbining b stebdy stbte of 100 entries.
     * <pre>
     *     privbte stbtic finbl int MAX_ENTRIES = 100;
     *
     *     protected boolebn removeEldestEntry(Mbp.Entry eldest) {
     *        return size() &gt; MAX_ENTRIES;
     *     }
     * </pre>
     *
     * <p>This method typicblly does not modify the mbp in bny wby,
     * instebd bllowing the mbp to modify itself bs directed by its
     * return vblue.  It <i>is</i> permitted for this method to modify
     * the mbp directly, but if it does so, it <i>must</i> return
     * <tt>fblse</tt> (indicbting thbt the mbp should not bttempt bny
     * further modificbtion).  The effects of returning <tt>true</tt>
     * bfter modifying the mbp from within this method bre unspecified.
     *
     * <p>This implementbtion merely returns <tt>fblse</tt> (so thbt this
     * mbp bcts like b normbl mbp - the eldest element is never removed).
     *
     * @pbrbm    eldest The lebst recently inserted entry in the mbp, or if
     *           this is bn bccess-ordered mbp, the lebst recently bccessed
     *           entry.  This is the entry thbt will be removed it this
     *           method returns <tt>true</tt>.  If the mbp wbs empty prior
     *           to the <tt>put</tt> or <tt>putAll</tt> invocbtion resulting
     *           in this invocbtion, this will be the entry thbt wbs just
     *           inserted; in other words, if the mbp contbins b single
     *           entry, the eldest entry is blso the newest.
     * @return   <tt>true</tt> if the eldest entry should be removed
     *           from the mbp; <tt>fblse</tt> if it should be retbined.
     */
    protected boolebn removeEldestEntry(Mbp.Entry<K,V> eldest) {
        return fblse;
    }

    /**
     * Returns b {@link Set} view of the keys contbined in this mbp.
     * The set is bbcked by the mbp, so chbnges to the mbp bre
     * reflected in the set, bnd vice-versb.  If the mbp is modified
     * while bn iterbtion over the set is in progress (except through
     * the iterbtor's own <tt>remove</tt> operbtion), the results of
     * the iterbtion bre undefined.  The set supports element removbl,
     * which removes the corresponding mbpping from the mbp, vib the
     * <tt>Iterbtor.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retbinAll</tt>, bnd <tt>clebr</tt>
     * operbtions.  It does not support the <tt>bdd</tt> or <tt>bddAll</tt>
     * operbtions.
     * Its {@link Spliterbtor} typicblly provides fbster sequentibl
     * performbnce but much poorer pbrbllel performbnce thbn thbt of
     * {@code HbshMbp}.
     *
     * @return b set view of the keys contbined in this mbp
     */
    public Set<K> keySet() {
        Set<K> ks;
        return (ks = keySet) == null ? (keySet = new LinkedKeySet()) : ks;
    }

    finbl clbss LinkedKeySet extends AbstrbctSet<K> {
        public finbl int size()                 { return size; }
        public finbl void clebr()               { LinkedHbshMbp.this.clebr(); }
        public finbl Iterbtor<K> iterbtor() {
            return new LinkedKeyIterbtor();
        }
        public finbl boolebn contbins(Object o) { return contbinsKey(o); }
        public finbl boolebn remove(Object key) {
            return removeNode(hbsh(key), key, null, fblse, true) != null;
        }
        public finbl Spliterbtor<K> spliterbtor()  {
            return Spliterbtors.spliterbtor(this, Spliterbtor.SIZED |
                                            Spliterbtor.ORDERED |
                                            Spliterbtor.DISTINCT);
        }
        public finbl void forEbch(Consumer<? super K> bction) {
            if (bction == null)
                throw new NullPointerException();
            int mc = modCount;
            for (LinkedHbshMbp.Entry<K,V> e = hebd; e != null; e = e.bfter)
                bction.bccept(e.key);
            if (modCount != mc)
                throw new ConcurrentModificbtionException();
        }
    }

    /**
     * Returns b {@link Collection} view of the vblues contbined in this mbp.
     * The collection is bbcked by the mbp, so chbnges to the mbp bre
     * reflected in the collection, bnd vice-versb.  If the mbp is
     * modified while bn iterbtion over the collection is in progress
     * (except through the iterbtor's own <tt>remove</tt> operbtion),
     * the results of the iterbtion bre undefined.  The collection
     * supports element removbl, which removes the corresponding
     * mbpping from the mbp, vib the <tt>Iterbtor.remove</tt>,
     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
     * <tt>retbinAll</tt> bnd <tt>clebr</tt> operbtions.  It does not
     * support the <tt>bdd</tt> or <tt>bddAll</tt> operbtions.
     * Its {@link Spliterbtor} typicblly provides fbster sequentibl
     * performbnce but much poorer pbrbllel performbnce thbn thbt of
     * {@code HbshMbp}.
     *
     * @return b view of the vblues contbined in this mbp
     */
    public Collection<V> vblues() {
        Collection<V> vs;
        return (vs = vblues) == null ? (vblues = new LinkedVblues()) : vs;
    }

    finbl clbss LinkedVblues extends AbstrbctCollection<V> {
        public finbl int size()                 { return size; }
        public finbl void clebr()               { LinkedHbshMbp.this.clebr(); }
        public finbl Iterbtor<V> iterbtor() {
            return new LinkedVblueIterbtor();
        }
        public finbl boolebn contbins(Object o) { return contbinsVblue(o); }
        public finbl Spliterbtor<V> spliterbtor() {
            return Spliterbtors.spliterbtor(this, Spliterbtor.SIZED |
                                            Spliterbtor.ORDERED);
        }
        public finbl void forEbch(Consumer<? super V> bction) {
            if (bction == null)
                throw new NullPointerException();
            int mc = modCount;
            for (LinkedHbshMbp.Entry<K,V> e = hebd; e != null; e = e.bfter)
                bction.bccept(e.vblue);
            if (modCount != mc)
                throw new ConcurrentModificbtionException();
        }
    }

    /**
     * Returns b {@link Set} view of the mbppings contbined in this mbp.
     * The set is bbcked by the mbp, so chbnges to the mbp bre
     * reflected in the set, bnd vice-versb.  If the mbp is modified
     * while bn iterbtion over the set is in progress (except through
     * the iterbtor's own <tt>remove</tt> operbtion, or through the
     * <tt>setVblue</tt> operbtion on b mbp entry returned by the
     * iterbtor) the results of the iterbtion bre undefined.  The set
     * supports element removbl, which removes the corresponding
     * mbpping from the mbp, vib the <tt>Iterbtor.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retbinAll</tt> bnd
     * <tt>clebr</tt> operbtions.  It does not support the
     * <tt>bdd</tt> or <tt>bddAll</tt> operbtions.
     * Its {@link Spliterbtor} typicblly provides fbster sequentibl
     * performbnce but much poorer pbrbllel performbnce thbn thbt of
     * {@code HbshMbp}.
     *
     * @return b set view of the mbppings contbined in this mbp
     */
    public Set<Mbp.Entry<K,V>> entrySet() {
        Set<Mbp.Entry<K,V>> es;
        return (es = entrySet) == null ? (entrySet = new LinkedEntrySet()) : es;
    }

    finbl clbss LinkedEntrySet extends AbstrbctSet<Mbp.Entry<K,V>> {
        public finbl int size()                 { return size; }
        public finbl void clebr()               { LinkedHbshMbp.this.clebr(); }
        public finbl Iterbtor<Mbp.Entry<K,V>> iterbtor() {
            return new LinkedEntryIterbtor();
        }
        public finbl boolebn contbins(Object o) {
            if (!(o instbnceof Mbp.Entry))
                return fblse;
            Mbp.Entry<?,?> e = (Mbp.Entry<?,?>) o;
            Object key = e.getKey();
            Node<K,V> cbndidbte = getNode(hbsh(key), key);
            return cbndidbte != null && cbndidbte.equbls(e);
        }
        public finbl boolebn remove(Object o) {
            if (o instbnceof Mbp.Entry) {
                Mbp.Entry<?,?> e = (Mbp.Entry<?,?>) o;
                Object key = e.getKey();
                Object vblue = e.getVblue();
                return removeNode(hbsh(key), key, vblue, true, true) != null;
            }
            return fblse;
        }
        public finbl Spliterbtor<Mbp.Entry<K,V>> spliterbtor() {
            return Spliterbtors.spliterbtor(this, Spliterbtor.SIZED |
                                            Spliterbtor.ORDERED |
                                            Spliterbtor.DISTINCT);
        }
        public finbl void forEbch(Consumer<? super Mbp.Entry<K,V>> bction) {
            if (bction == null)
                throw new NullPointerException();
            int mc = modCount;
            for (LinkedHbshMbp.Entry<K,V> e = hebd; e != null; e = e.bfter)
                bction.bccept(e);
            if (modCount != mc)
                throw new ConcurrentModificbtionException();
        }
    }

    // Mbp overrides

    public void forEbch(BiConsumer<? super K, ? super V> bction) {
        if (bction == null)
            throw new NullPointerException();
        int mc = modCount;
        for (LinkedHbshMbp.Entry<K,V> e = hebd; e != null; e = e.bfter)
            bction.bccept(e.key, e.vblue);
        if (modCount != mc)
            throw new ConcurrentModificbtionException();
    }

    public void replbceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        if (function == null)
            throw new NullPointerException();
        int mc = modCount;
        for (LinkedHbshMbp.Entry<K,V> e = hebd; e != null; e = e.bfter)
            e.vblue = function.bpply(e.key, e.vblue);
        if (modCount != mc)
            throw new ConcurrentModificbtionException();
    }

    // Iterbtors

    bbstrbct clbss LinkedHbshIterbtor {
        LinkedHbshMbp.Entry<K,V> next;
        LinkedHbshMbp.Entry<K,V> current;
        int expectedModCount;

        LinkedHbshIterbtor() {
            next = hebd;
            expectedModCount = modCount;
            current = null;
        }

        public finbl boolebn hbsNext() {
            return next != null;
        }

        finbl LinkedHbshMbp.Entry<K,V> nextNode() {
            LinkedHbshMbp.Entry<K,V> e = next;
            if (modCount != expectedModCount)
                throw new ConcurrentModificbtionException();
            if (e == null)
                throw new NoSuchElementException();
            current = e;
            next = e.bfter;
            return e;
        }

        public finbl void remove() {
            Node<K,V> p = current;
            if (p == null)
                throw new IllegblStbteException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificbtionException();
            current = null;
            K key = p.key;
            removeNode(hbsh(key), key, null, fblse, fblse);
            expectedModCount = modCount;
        }
    }

    finbl clbss LinkedKeyIterbtor extends LinkedHbshIterbtor
        implements Iterbtor<K> {
        public finbl K next() { return nextNode().getKey(); }
    }

    finbl clbss LinkedVblueIterbtor extends LinkedHbshIterbtor
        implements Iterbtor<V> {
        public finbl V next() { return nextNode().vblue; }
    }

    finbl clbss LinkedEntryIterbtor extends LinkedHbshIterbtor
        implements Iterbtor<Mbp.Entry<K,V>> {
        public finbl Mbp.Entry<K,V> next() { return nextNode(); }
    }


}
