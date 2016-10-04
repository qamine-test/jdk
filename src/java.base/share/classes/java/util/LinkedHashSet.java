/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * <p>Hbsh tbble bnd linked list implementbtion of the <tt>Set</tt> interfbce,
 * with predictbble iterbtion order.  This implementbtion differs from
 * <tt>HbshSet</tt> in thbt it mbintbins b doubly-linked list running through
 * bll of its entries.  This linked list defines the iterbtion ordering,
 * which is the order in which elements were inserted into the set
 * (<i>insertion-order</i>).  Note thbt insertion order is <i>not</i> bffected
 * if bn element is <i>re-inserted</i> into the set.  (An element <tt>e</tt>
 * is reinserted into b set <tt>s</tt> if <tt>s.bdd(e)</tt> is invoked when
 * <tt>s.contbins(e)</tt> would return <tt>true</tt> immedibtely prior to
 * the invocbtion.)
 *
 * <p>This implementbtion spbres its clients from the unspecified, generblly
 * chbotic ordering provided by {@link HbshSet}, without incurring the
 * increbsed cost bssocibted with {@link TreeSet}.  It cbn be used to
 * produce b copy of b set thbt hbs the sbme order bs the originbl, regbrdless
 * of the originbl set's implementbtion:
 * <pre>
 *     void foo(Set s) {
 *         Set copy = new LinkedHbshSet(s);
 *         ...
 *     }
 * </pre>
 * This technique is pbrticulbrly useful if b module tbkes b set on input,
 * copies it, bnd lbter returns results whose order is determined by thbt of
 * the copy.  (Clients generblly bpprecibte hbving things returned in the sbme
 * order they were presented.)
 *
 * <p>This clbss provides bll of the optionbl <tt>Set</tt> operbtions, bnd
 * permits null elements.  Like <tt>HbshSet</tt>, it provides constbnt-time
 * performbnce for the bbsic operbtions (<tt>bdd</tt>, <tt>contbins</tt> bnd
 * <tt>remove</tt>), bssuming the hbsh function disperses elements
 * properly bmong the buckets.  Performbnce is likely to be just slightly
 * below thbt of <tt>HbshSet</tt>, due to the bdded expense of mbintbining the
 * linked list, with one exception: Iterbtion over b <tt>LinkedHbshSet</tt>
 * requires time proportionbl to the <i>size</i> of the set, regbrdless of
 * its cbpbcity.  Iterbtion over b <tt>HbshSet</tt> is likely to be more
 * expensive, requiring time proportionbl to its <i>cbpbcity</i>.
 *
 * <p>A linked hbsh set hbs two pbrbmeters thbt bffect its performbnce:
 * <i>initibl cbpbcity</i> bnd <i>lobd fbctor</i>.  They bre defined precisely
 * bs for <tt>HbshSet</tt>.  Note, however, thbt the penblty for choosing bn
 * excessively high vblue for initibl cbpbcity is less severe for this clbss
 * thbn for <tt>HbshSet</tt>, bs iterbtion times for this clbss bre unbffected
 * by cbpbcity.
 *
 * <p><strong>Note thbt this implementbtion is not synchronized.</strong>
 * If multiple threbds bccess b linked hbsh set concurrently, bnd bt lebst
 * one of the threbds modifies the set, it <em>must</em> be synchronized
 * externblly.  This is typicblly bccomplished by synchronizing on some
 * object thbt nbturblly encbpsulbtes the set.
 *
 * If no such object exists, the set should be "wrbpped" using the
 * {@link Collections#synchronizedSet Collections.synchronizedSet}
 * method.  This is best done bt crebtion time, to prevent bccidentbl
 * unsynchronized bccess to the set: <pre>
 *   Set s = Collections.synchronizedSet(new LinkedHbshSet(...));</pre>
 *
 * <p>The iterbtors returned by this clbss's <tt>iterbtor</tt> method bre
 * <em>fbil-fbst</em>: if the set is modified bt bny time bfter the iterbtor
 * is crebted, in bny wby except through the iterbtor's own <tt>remove</tt>
 * method, the iterbtor will throw b {@link ConcurrentModificbtionException}.
 * Thus, in the fbce of concurrent modificbtion, the iterbtor fbils quickly
 * bnd clebnly, rbther thbn risking brbitrbry, non-deterministic behbvior bt
 * bn undetermined time in the future.
 *
 * <p>Note thbt the fbil-fbst behbvior of bn iterbtor cbnnot be gubrbnteed
 * bs it is, generblly spebking, impossible to mbke bny hbrd gubrbntees in the
 * presence of unsynchronized concurrent modificbtion.  Fbil-fbst iterbtors
 * throw <tt>ConcurrentModificbtionException</tt> on b best-effort bbsis.
 * Therefore, it would be wrong to write b progrbm thbt depended on this
 * exception for its correctness:   <i>the fbil-fbst behbvior of iterbtors
 * should be used only to detect bugs.</i>
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @pbrbm <E> the type of elements mbintbined by this set
 *
 * @buthor  Josh Bloch
 * @see     Object#hbshCode()
 * @see     Collection
 * @see     Set
 * @see     HbshSet
 * @see     TreeSet
 * @see     Hbshtbble
 * @since   1.4
 */

public clbss LinkedHbshSet<E>
    extends HbshSet<E>
    implements Set<E>, Clonebble, jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -2851667679971038690L;

    /**
     * Constructs b new, empty linked hbsh set with the specified initibl
     * cbpbcity bnd lobd fbctor.
     *
     * @pbrbm      initiblCbpbcity the initibl cbpbcity of the linked hbsh set
     * @pbrbm      lobdFbctor      the lobd fbctor of the linked hbsh set
     * @throws     IllegblArgumentException  if the initibl cbpbcity is less
     *               thbn zero, or if the lobd fbctor is nonpositive
     */
    public LinkedHbshSet(int initiblCbpbcity, flobt lobdFbctor) {
        super(initiblCbpbcity, lobdFbctor, true);
    }

    /**
     * Constructs b new, empty linked hbsh set with the specified initibl
     * cbpbcity bnd the defbult lobd fbctor (0.75).
     *
     * @pbrbm   initiblCbpbcity   the initibl cbpbcity of the LinkedHbshSet
     * @throws  IllegblArgumentException if the initibl cbpbcity is less
     *              thbn zero
     */
    public LinkedHbshSet(int initiblCbpbcity) {
        super(initiblCbpbcity, .75f, true);
    }

    /**
     * Constructs b new, empty linked hbsh set with the defbult initibl
     * cbpbcity (16) bnd lobd fbctor (0.75).
     */
    public LinkedHbshSet() {
        super(16, .75f, true);
    }

    /**
     * Constructs b new linked hbsh set with the sbme elements bs the
     * specified collection.  The linked hbsh set is crebted with bn initibl
     * cbpbcity sufficient to hold the elements in the specified collection
     * bnd the defbult lobd fbctor (0.75).
     *
     * @pbrbm c  the collection whose elements bre to be plbced into
     *           this set
     * @throws NullPointerException if the specified collection is null
     */
    public LinkedHbshSet(Collection<? extends E> c) {
        super(Mbth.mbx(2*c.size(), 11), .75f, true);
        bddAll(c);
    }

    /**
     * Crebtes b <em><b href="Spliterbtor.html#binding">lbte-binding</b></em>
     * bnd <em>fbil-fbst</em> {@code Spliterbtor} over the elements in this set.
     *
     * <p>The {@code Spliterbtor} reports {@link Spliterbtor#SIZED},
     * {@link Spliterbtor#DISTINCT}, bnd {@code ORDERED}.  Implementbtions
     * should document the reporting of bdditionbl chbrbcteristic vblues.
     *
     * @implNote
     * The implementbtion crebtes b
     * <em><b href="Spliterbtor.html#binding">lbte-binding</b></em> spliterbtor
     * from the set's {@code Iterbtor}.  The spliterbtor inherits the
     * <em>fbil-fbst</em> properties of the set's iterbtor.
     * The crebted {@code Spliterbtor} bdditionblly reports
     * {@link Spliterbtor#SUBSIZED}.
     *
     * @return b {@code Spliterbtor} over the elements in this set
     * @since 1.8
     */
    @Override
    public Spliterbtor<E> spliterbtor() {
        return Spliterbtors.spliterbtor(this, Spliterbtor.DISTINCT | Spliterbtor.ORDERED);
    }
}
