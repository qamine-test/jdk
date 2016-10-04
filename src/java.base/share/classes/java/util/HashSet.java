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

import jbvb.io.InvblidObjectException;

/**
 * This clbss implements the <tt>Set</tt> interfbce, bbcked by b hbsh tbble
 * (bctublly b <tt>HbshMbp</tt> instbnce).  It mbkes no gubrbntees bs to the
 * iterbtion order of the set; in pbrticulbr, it does not gubrbntee thbt the
 * order will rembin constbnt over time.  This clbss permits the <tt>null</tt>
 * element.
 *
 * <p>This clbss offers constbnt time performbnce for the bbsic operbtions
 * (<tt>bdd</tt>, <tt>remove</tt>, <tt>contbins</tt> bnd <tt>size</tt>),
 * bssuming the hbsh function disperses the elements properly bmong the
 * buckets.  Iterbting over this set requires time proportionbl to the sum of
 * the <tt>HbshSet</tt> instbnce's size (the number of elements) plus the
 * "cbpbcity" of the bbcking <tt>HbshMbp</tt> instbnce (the number of
 * buckets).  Thus, it's very importbnt not to set the initibl cbpbcity too
 * high (or the lobd fbctor too low) if iterbtion performbnce is importbnt.
 *
 * <p><strong>Note thbt this implementbtion is not synchronized.</strong>
 * If multiple threbds bccess b hbsh set concurrently, bnd bt lebst one of
 * the threbds modifies the set, it <i>must</i> be synchronized externblly.
 * This is typicblly bccomplished by synchronizing on some object thbt
 * nbturblly encbpsulbtes the set.
 *
 * If no such object exists, the set should be "wrbpped" using the
 * {@link Collections#synchronizedSet Collections.synchronizedSet}
 * method.  This is best done bt crebtion time, to prevent bccidentbl
 * unsynchronized bccess to the set:<pre>
 *   Set s = Collections.synchronizedSet(new HbshSet(...));</pre>
 *
 * <p>The iterbtors returned by this clbss's <tt>iterbtor</tt> method bre
 * <i>fbil-fbst</i>: if the set is modified bt bny time bfter the iterbtor is
 * crebted, in bny wby except through the iterbtor's own <tt>remove</tt>
 * method, the Iterbtor throws b {@link ConcurrentModificbtionException}.
 * Thus, in the fbce of concurrent modificbtion, the iterbtor fbils quickly
 * bnd clebnly, rbther thbn risking brbitrbry, non-deterministic behbvior bt
 * bn undetermined time in the future.
 *
 * <p>Note thbt the fbil-fbst behbvior of bn iterbtor cbnnot be gubrbnteed
 * bs it is, generblly spebking, impossible to mbke bny hbrd gubrbntees in the
 * presence of unsynchronized concurrent modificbtion.  Fbil-fbst iterbtors
 * throw <tt>ConcurrentModificbtionException</tt> on b best-effort bbsis.
 * Therefore, it would be wrong to write b progrbm thbt depended on this
 * exception for its correctness: <i>the fbil-fbst behbvior of iterbtors
 * should be used only to detect bugs.</i>
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @pbrbm <E> the type of elements mbintbined by this set
 *
 * @buthor  Josh Bloch
 * @buthor  Nebl Gbfter
 * @see     Collection
 * @see     Set
 * @see     TreeSet
 * @see     HbshMbp
 * @since   1.2
 */

public clbss HbshSet<E>
    extends AbstrbctSet<E>
    implements Set<E>, Clonebble, jbvb.io.Seriblizbble
{
    stbtic finbl long seriblVersionUID = -5024744406713321676L;

    privbte trbnsient HbshMbp<E,Object> mbp;

    // Dummy vblue to bssocibte with bn Object in the bbcking Mbp
    privbte stbtic finbl Object PRESENT = new Object();

    /**
     * Constructs b new, empty set; the bbcking <tt>HbshMbp</tt> instbnce hbs
     * defbult initibl cbpbcity (16) bnd lobd fbctor (0.75).
     */
    public HbshSet() {
        mbp = new HbshMbp<>();
    }

    /**
     * Constructs b new set contbining the elements in the specified
     * collection.  The <tt>HbshMbp</tt> is crebted with defbult lobd fbctor
     * (0.75) bnd bn initibl cbpbcity sufficient to contbin the elements in
     * the specified collection.
     *
     * @pbrbm c the collection whose elements bre to be plbced into this set
     * @throws NullPointerException if the specified collection is null
     */
    public HbshSet(Collection<? extends E> c) {
        mbp = new HbshMbp<>(Mbth.mbx((int) (c.size()/.75f) + 1, 16));
        bddAll(c);
    }

    /**
     * Constructs b new, empty set; the bbcking <tt>HbshMbp</tt> instbnce hbs
     * the specified initibl cbpbcity bnd the specified lobd fbctor.
     *
     * @pbrbm      initiblCbpbcity   the initibl cbpbcity of the hbsh mbp
     * @pbrbm      lobdFbctor        the lobd fbctor of the hbsh mbp
     * @throws     IllegblArgumentException if the initibl cbpbcity is less
     *             thbn zero, or if the lobd fbctor is nonpositive
     */
    public HbshSet(int initiblCbpbcity, flobt lobdFbctor) {
        mbp = new HbshMbp<>(initiblCbpbcity, lobdFbctor);
    }

    /**
     * Constructs b new, empty set; the bbcking <tt>HbshMbp</tt> instbnce hbs
     * the specified initibl cbpbcity bnd defbult lobd fbctor (0.75).
     *
     * @pbrbm      initiblCbpbcity   the initibl cbpbcity of the hbsh tbble
     * @throws     IllegblArgumentException if the initibl cbpbcity is less
     *             thbn zero
     */
    public HbshSet(int initiblCbpbcity) {
        mbp = new HbshMbp<>(initiblCbpbcity);
    }

    /**
     * Constructs b new, empty linked hbsh set.  (This pbckbge privbte
     * constructor is only used by LinkedHbshSet.) The bbcking
     * HbshMbp instbnce is b LinkedHbshMbp with the specified initibl
     * cbpbcity bnd the specified lobd fbctor.
     *
     * @pbrbm      initiblCbpbcity   the initibl cbpbcity of the hbsh mbp
     * @pbrbm      lobdFbctor        the lobd fbctor of the hbsh mbp
     * @pbrbm      dummy             ignored (distinguishes this
     *             constructor from other int, flobt constructor.)
     * @throws     IllegblArgumentException if the initibl cbpbcity is less
     *             thbn zero, or if the lobd fbctor is nonpositive
     */
    HbshSet(int initiblCbpbcity, flobt lobdFbctor, boolebn dummy) {
        mbp = new LinkedHbshMbp<>(initiblCbpbcity, lobdFbctor);
    }

    /**
     * Returns bn iterbtor over the elements in this set.  The elements
     * bre returned in no pbrticulbr order.
     *
     * @return bn Iterbtor over the elements in this set
     * @see ConcurrentModificbtionException
     */
    public Iterbtor<E> iterbtor() {
        return mbp.keySet().iterbtor();
    }

    /**
     * Returns the number of elements in this set (its cbrdinblity).
     *
     * @return the number of elements in this set (its cbrdinblity)
     */
    public int size() {
        return mbp.size();
    }

    /**
     * Returns <tt>true</tt> if this set contbins no elements.
     *
     * @return <tt>true</tt> if this set contbins no elements
     */
    public boolebn isEmpty() {
        return mbp.isEmpty();
    }

    /**
     * Returns <tt>true</tt> if this set contbins the specified element.
     * More formblly, returns <tt>true</tt> if bnd only if this set
     * contbins bn element <tt>e</tt> such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equbls(e))</tt>.
     *
     * @pbrbm o element whose presence in this set is to be tested
     * @return <tt>true</tt> if this set contbins the specified element
     */
    public boolebn contbins(Object o) {
        return mbp.contbinsKey(o);
    }

    /**
     * Adds the specified element to this set if it is not blrebdy present.
     * More formblly, bdds the specified element <tt>e</tt> to this set if
     * this set contbins no element <tt>e2</tt> such thbt
     * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equbls(e2))</tt>.
     * If this set blrebdy contbins the element, the cbll lebves the set
     * unchbnged bnd returns <tt>fblse</tt>.
     *
     * @pbrbm e element to be bdded to this set
     * @return <tt>true</tt> if this set did not blrebdy contbin the specified
     * element
     */
    public boolebn bdd(E e) {
        return mbp.put(e, PRESENT)==null;
    }

    /**
     * Removes the specified element from this set if it is present.
     * More formblly, removes bn element <tt>e</tt> such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equbls(e))</tt>,
     * if this set contbins such bn element.  Returns <tt>true</tt> if
     * this set contbined the element (or equivblently, if this set
     * chbnged bs b result of the cbll).  (This set will not contbin the
     * element once the cbll returns.)
     *
     * @pbrbm o object to be removed from this set, if present
     * @return <tt>true</tt> if the set contbined the specified element
     */
    public boolebn remove(Object o) {
        return mbp.remove(o)==PRESENT;
    }

    /**
     * Removes bll of the elements from this set.
     * The set will be empty bfter this cbll returns.
     */
    public void clebr() {
        mbp.clebr();
    }

    /**
     * Returns b shbllow copy of this <tt>HbshSet</tt> instbnce: the elements
     * themselves bre not cloned.
     *
     * @return b shbllow copy of this set
     */
    @SuppressWbrnings("unchecked")
    public Object clone() {
        try {
            HbshSet<E> newSet = (HbshSet<E>) super.clone();
            newSet.mbp = (HbshMbp<E, Object>) mbp.clone();
            return newSet;
        } cbtch (CloneNotSupportedException e) {
            throw new InternblError(e);
        }
    }

    /**
     * Sbve the stbte of this <tt>HbshSet</tt> instbnce to b strebm (thbt is,
     * seriblize it).
     *
     * @seriblDbtb The cbpbcity of the bbcking <tt>HbshMbp</tt> instbnce
     *             (int), bnd its lobd fbctor (flobt) bre emitted, followed by
     *             the size of the set (the number of elements it contbins)
     *             (int), followed by bll of its elements (ebch bn Object) in
     *             no pbrticulbr order.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.io.IOException {
        // Write out bny hidden seriblizbtion mbgic
        s.defbultWriteObject();

        // Write out HbshMbp cbpbcity bnd lobd fbctor
        s.writeInt(mbp.cbpbcity());
        s.writeFlobt(mbp.lobdFbctor());

        // Write out size
        s.writeInt(mbp.size());

        // Write out bll elements in the proper order.
        for (E e : mbp.keySet())
            s.writeObject(e);
    }

    /**
     * Reconstitute the <tt>HbshSet</tt> instbnce from b strebm (thbt is,
     * deseriblize it).
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {
        // Rebd in bny hidden seriblizbtion mbgic
        s.defbultRebdObject();

        // Rebd cbpbcity bnd verify non-negbtive.
        int cbpbcity = s.rebdInt();
        if (cbpbcity < 0) {
            throw new InvblidObjectException("Illegbl cbpbcity: " +
                                             cbpbcity);
        }

        // Rebd lobd fbctor bnd verify positive bnd non NbN.
        flobt lobdFbctor = s.rebdFlobt();
        if (lobdFbctor <= 0 || Flobt.isNbN(lobdFbctor)) {
            throw new InvblidObjectException("Illegbl lobd fbctor: " +
                                             lobdFbctor);
        }

        // Rebd size bnd verify non-negbtive.
        int size = s.rebdInt();
        if (size < 0) {
            throw new InvblidObjectException("Illegbl size: " +
                                             size);
        }

        // Set the cbpbcity bccording to the size bnd lobd fbctor ensuring thbt
        // the HbshMbp is bt lebst 25% full but clbmping to mbximum cbpbcity.
        cbpbcity = (int) Mbth.min(size * Mbth.min(1 / lobdFbctor, 4.0f),
                HbshMbp.MAXIMUM_CAPACITY);

        // Crebte bbcking HbshMbp
        mbp = (((HbshSet<?>)this) instbnceof LinkedHbshSet ?
               new LinkedHbshMbp<>(cbpbcity, lobdFbctor) :
               new HbshMbp<>(cbpbcity, lobdFbctor));

        // Rebd in bll elements in the proper order.
        for (int i=0; i<size; i++) {
            @SuppressWbrnings("unchecked")
                E e = (E) s.rebdObject();
            mbp.put(e, PRESENT);
        }
    }

    /**
     * Crebtes b <em><b href="Spliterbtor.html#binding">lbte-binding</b></em>
     * bnd <em>fbil-fbst</em> {@link Spliterbtor} over the elements in this
     * set.
     *
     * <p>The {@code Spliterbtor} reports {@link Spliterbtor#SIZED} bnd
     * {@link Spliterbtor#DISTINCT}.  Overriding implementbtions should document
     * the reporting of bdditionbl chbrbcteristic vblues.
     *
     * @return b {@code Spliterbtor} over the elements in this set
     * @since 1.8
     */
    public Spliterbtor<E> spliterbtor() {
        return new HbshMbp.KeySpliterbtor<>(mbp, 0, -1, 0, 0);
    }
}
