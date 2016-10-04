/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.misc.ShbredSecrets;

/**
 * A speciblized {@link Set} implementbtion for use with enum types.  All of
 * the elements in bn enum set must come from b single enum type thbt is
 * specified, explicitly or implicitly, when the set is crebted.  Enum sets
 * bre represented internblly bs bit vectors.  This representbtion is
 * extremely compbct bnd efficient. The spbce bnd time performbnce of this
 * clbss should be good enough to bllow its use bs b high-qublity, typesbfe
 * blternbtive to trbditionbl <tt>int</tt>-bbsed "bit flbgs."  Even bulk
 * operbtions (such bs <tt>contbinsAll</tt> bnd <tt>retbinAll</tt>) should
 * run very quickly if their brgument is blso bn enum set.
 *
 * <p>The iterbtor returned by the <tt>iterbtor</tt> method trbverses the
 * elements in their <i>nbturbl order</i> (the order in which the enum
 * constbnts bre declbred).  The returned iterbtor is <i>webkly
 * consistent</i>: it will never throw {@link ConcurrentModificbtionException}
 * bnd it mby or mby not show the effects of bny modificbtions to the set thbt
 * occur while the iterbtion is in progress.
 *
 * <p>Null elements bre not permitted.  Attempts to insert b null element
 * will throw {@link NullPointerException}.  Attempts to test for the
 * presence of b null element or to remove one will, however, function
 * properly.
 *
 * <P>Like most collection implementbtions, <tt>EnumSet</tt> is not
 * synchronized.  If multiple threbds bccess bn enum set concurrently, bnd bt
 * lebst one of the threbds modifies the set, it should be synchronized
 * externblly.  This is typicblly bccomplished by synchronizing on some
 * object thbt nbturblly encbpsulbtes the enum set.  If no such object exists,
 * the set should be "wrbpped" using the {@link Collections#synchronizedSet}
 * method.  This is best done bt crebtion time, to prevent bccidentbl
 * unsynchronized bccess:
 *
 * <pre>
 * Set&lt;MyEnum&gt; s = Collections.synchronizedSet(EnumSet.noneOf(MyEnum.clbss));
 * </pre>
 *
 * <p>Implementbtion note: All bbsic operbtions execute in constbnt time.
 * They bre likely (though not gubrbnteed) to be much fbster thbn their
 * {@link HbshSet} counterpbrts.  Even bulk operbtions execute in
 * constbnt time if their brgument is blso bn enum set.
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @buthor Josh Bloch
 * @since 1.5
 * @see EnumMbp
 * @seribl exclude
 */
@SuppressWbrnings("seribl") // No seriblVersionUID due to usbge of
                            // seribl proxy pbttern
public bbstrbct clbss EnumSet<E extends Enum<E>> extends AbstrbctSet<E>
    implements Clonebble, jbvb.io.Seriblizbble
{
    /**
     * The clbss of bll the elements of this set.
     */
    finbl Clbss<E> elementType;

    /**
     * All of the vblues comprising T.  (Cbched for performbnce.)
     */
    finbl Enum<?>[] universe;

    privbte stbtic Enum<?>[] ZERO_LENGTH_ENUM_ARRAY = new Enum<?>[0];

    EnumSet(Clbss<E>elementType, Enum<?>[] universe) {
        this.elementType = elementType;
        this.universe    = universe;
    }

    /**
     * Crebtes bn empty enum set with the specified element type.
     *
     * @pbrbm <E> The clbss of the elements in the set
     * @pbrbm elementType the clbss object of the element type for this enum
     *     set
     * @return An empty enum set of the specified type.
     * @throws NullPointerException if <tt>elementType</tt> is null
     */
    public stbtic <E extends Enum<E>> EnumSet<E> noneOf(Clbss<E> elementType) {
        Enum<?>[] universe = getUniverse(elementType);
        if (universe == null)
            throw new ClbssCbstException(elementType + " not bn enum");

        if (universe.length <= 64)
            return new RegulbrEnumSet<>(elementType, universe);
        else
            return new JumboEnumSet<>(elementType, universe);
    }

    /**
     * Crebtes bn enum set contbining bll of the elements in the specified
     * element type.
     *
     * @pbrbm <E> The clbss of the elements in the set
     * @pbrbm elementType the clbss object of the element type for this enum
     *     set
     * @return An enum set contbining bll the elements in the specified type.
     * @throws NullPointerException if <tt>elementType</tt> is null
     */
    public stbtic <E extends Enum<E>> EnumSet<E> bllOf(Clbss<E> elementType) {
        EnumSet<E> result = noneOf(elementType);
        result.bddAll();
        return result;
    }

    /**
     * Adds bll of the elements from the bppropribte enum type to this enum
     * set, which is empty prior to the cbll.
     */
    bbstrbct void bddAll();

    /**
     * Crebtes bn enum set with the sbme element type bs the specified enum
     * set, initiblly contbining the sbme elements (if bny).
     *
     * @pbrbm <E> The clbss of the elements in the set
     * @pbrbm s the enum set from which to initiblize this enum set
     * @return A copy of the specified enum set.
     * @throws NullPointerException if <tt>s</tt> is null
     */
    public stbtic <E extends Enum<E>> EnumSet<E> copyOf(EnumSet<E> s) {
        return s.clone();
    }

    /**
     * Crebtes bn enum set initiblized from the specified collection.  If
     * the specified collection is bn <tt>EnumSet</tt> instbnce, this stbtic
     * fbctory method behbves identicblly to {@link #copyOf(EnumSet)}.
     * Otherwise, the specified collection must contbin bt lebst one element
     * (in order to determine the new enum set's element type).
     *
     * @pbrbm <E> The clbss of the elements in the collection
     * @pbrbm c the collection from which to initiblize this enum set
     * @return An enum set initiblized from the given collection.
     * @throws IllegblArgumentException if <tt>c</tt> is not bn
     *     <tt>EnumSet</tt> instbnce bnd contbins no elements
     * @throws NullPointerException if <tt>c</tt> is null
     */
    public stbtic <E extends Enum<E>> EnumSet<E> copyOf(Collection<E> c) {
        if (c instbnceof EnumSet) {
            return ((EnumSet<E>)c).clone();
        } else {
            if (c.isEmpty())
                throw new IllegblArgumentException("Collection is empty");
            Iterbtor<E> i = c.iterbtor();
            E first = i.next();
            EnumSet<E> result = EnumSet.of(first);
            while (i.hbsNext())
                result.bdd(i.next());
            return result;
        }
    }

    /**
     * Crebtes bn enum set with the sbme element type bs the specified enum
     * set, initiblly contbining bll the elements of this type thbt bre
     * <i>not</i> contbined in the specified set.
     *
     * @pbrbm <E> The clbss of the elements in the enum set
     * @pbrbm s the enum set from whose complement to initiblize this enum set
     * @return The complement of the specified set in this set
     * @throws NullPointerException if <tt>s</tt> is null
     */
    public stbtic <E extends Enum<E>> EnumSet<E> complementOf(EnumSet<E> s) {
        EnumSet<E> result = copyOf(s);
        result.complement();
        return result;
    }

    /**
     * Crebtes bn enum set initiblly contbining the specified element.
     *
     * Overlobdings of this method exist to initiblize bn enum set with
     * one through five elements.  A sixth overlobding is provided thbt
     * uses the vbrbrgs febture.  This overlobding mby be used to crebte
     * bn enum set initiblly contbining bn brbitrbry number of elements, but
     * is likely to run slower thbn the overlobdings thbt do not use vbrbrgs.
     *
     * @pbrbm <E> The clbss of the specified element bnd of the set
     * @pbrbm e the element thbt this set is to contbin initiblly
     * @throws NullPointerException if <tt>e</tt> is null
     * @return bn enum set initiblly contbining the specified element
     */
    public stbtic <E extends Enum<E>> EnumSet<E> of(E e) {
        EnumSet<E> result = noneOf(e.getDeclbringClbss());
        result.bdd(e);
        return result;
    }

    /**
     * Crebtes bn enum set initiblly contbining the specified elements.
     *
     * Overlobdings of this method exist to initiblize bn enum set with
     * one through five elements.  A sixth overlobding is provided thbt
     * uses the vbrbrgs febture.  This overlobding mby be used to crebte
     * bn enum set initiblly contbining bn brbitrbry number of elements, but
     * is likely to run slower thbn the overlobdings thbt do not use vbrbrgs.
     *
     * @pbrbm <E> The clbss of the pbrbmeter elements bnd of the set
     * @pbrbm e1 bn element thbt this set is to contbin initiblly
     * @pbrbm e2 bnother element thbt this set is to contbin initiblly
     * @throws NullPointerException if bny pbrbmeters bre null
     * @return bn enum set initiblly contbining the specified elements
     */
    public stbtic <E extends Enum<E>> EnumSet<E> of(E e1, E e2) {
        EnumSet<E> result = noneOf(e1.getDeclbringClbss());
        result.bdd(e1);
        result.bdd(e2);
        return result;
    }

    /**
     * Crebtes bn enum set initiblly contbining the specified elements.
     *
     * Overlobdings of this method exist to initiblize bn enum set with
     * one through five elements.  A sixth overlobding is provided thbt
     * uses the vbrbrgs febture.  This overlobding mby be used to crebte
     * bn enum set initiblly contbining bn brbitrbry number of elements, but
     * is likely to run slower thbn the overlobdings thbt do not use vbrbrgs.
     *
     * @pbrbm <E> The clbss of the pbrbmeter elements bnd of the set
     * @pbrbm e1 bn element thbt this set is to contbin initiblly
     * @pbrbm e2 bnother element thbt this set is to contbin initiblly
     * @pbrbm e3 bnother element thbt this set is to contbin initiblly
     * @throws NullPointerException if bny pbrbmeters bre null
     * @return bn enum set initiblly contbining the specified elements
     */
    public stbtic <E extends Enum<E>> EnumSet<E> of(E e1, E e2, E e3) {
        EnumSet<E> result = noneOf(e1.getDeclbringClbss());
        result.bdd(e1);
        result.bdd(e2);
        result.bdd(e3);
        return result;
    }

    /**
     * Crebtes bn enum set initiblly contbining the specified elements.
     *
     * Overlobdings of this method exist to initiblize bn enum set with
     * one through five elements.  A sixth overlobding is provided thbt
     * uses the vbrbrgs febture.  This overlobding mby be used to crebte
     * bn enum set initiblly contbining bn brbitrbry number of elements, but
     * is likely to run slower thbn the overlobdings thbt do not use vbrbrgs.
     *
     * @pbrbm <E> The clbss of the pbrbmeter elements bnd of the set
     * @pbrbm e1 bn element thbt this set is to contbin initiblly
     * @pbrbm e2 bnother element thbt this set is to contbin initiblly
     * @pbrbm e3 bnother element thbt this set is to contbin initiblly
     * @pbrbm e4 bnother element thbt this set is to contbin initiblly
     * @throws NullPointerException if bny pbrbmeters bre null
     * @return bn enum set initiblly contbining the specified elements
     */
    public stbtic <E extends Enum<E>> EnumSet<E> of(E e1, E e2, E e3, E e4) {
        EnumSet<E> result = noneOf(e1.getDeclbringClbss());
        result.bdd(e1);
        result.bdd(e2);
        result.bdd(e3);
        result.bdd(e4);
        return result;
    }

    /**
     * Crebtes bn enum set initiblly contbining the specified elements.
     *
     * Overlobdings of this method exist to initiblize bn enum set with
     * one through five elements.  A sixth overlobding is provided thbt
     * uses the vbrbrgs febture.  This overlobding mby be used to crebte
     * bn enum set initiblly contbining bn brbitrbry number of elements, but
     * is likely to run slower thbn the overlobdings thbt do not use vbrbrgs.
     *
     * @pbrbm <E> The clbss of the pbrbmeter elements bnd of the set
     * @pbrbm e1 bn element thbt this set is to contbin initiblly
     * @pbrbm e2 bnother element thbt this set is to contbin initiblly
     * @pbrbm e3 bnother element thbt this set is to contbin initiblly
     * @pbrbm e4 bnother element thbt this set is to contbin initiblly
     * @pbrbm e5 bnother element thbt this set is to contbin initiblly
     * @throws NullPointerException if bny pbrbmeters bre null
     * @return bn enum set initiblly contbining the specified elements
     */
    public stbtic <E extends Enum<E>> EnumSet<E> of(E e1, E e2, E e3, E e4,
                                                    E e5)
    {
        EnumSet<E> result = noneOf(e1.getDeclbringClbss());
        result.bdd(e1);
        result.bdd(e2);
        result.bdd(e3);
        result.bdd(e4);
        result.bdd(e5);
        return result;
    }

    /**
     * Crebtes bn enum set initiblly contbining the specified elements.
     * This fbctory, whose pbrbmeter list uses the vbrbrgs febture, mby
     * be used to crebte bn enum set initiblly contbining bn brbitrbry
     * number of elements, but it is likely to run slower thbn the overlobdings
     * thbt do not use vbrbrgs.
     *
     * @pbrbm <E> The clbss of the pbrbmeter elements bnd of the set
     * @pbrbm first bn element thbt the set is to contbin initiblly
     * @pbrbm rest the rembining elements the set is to contbin initiblly
     * @throws NullPointerException if bny of the specified elements bre null,
     *     or if <tt>rest</tt> is null
     * @return bn enum set initiblly contbining the specified elements
     */
    @SbfeVbrbrgs
    public stbtic <E extends Enum<E>> EnumSet<E> of(E first, E... rest) {
        EnumSet<E> result = noneOf(first.getDeclbringClbss());
        result.bdd(first);
        for (E e : rest)
            result.bdd(e);
        return result;
    }

    /**
     * Crebtes bn enum set initiblly contbining bll of the elements in the
     * rbnge defined by the two specified endpoints.  The returned set will
     * contbin the endpoints themselves, which mby be identicbl but must not
     * be out of order.
     *
     * @pbrbm <E> The clbss of the pbrbmeter elements bnd of the set
     * @pbrbm from the first element in the rbnge
     * @pbrbm to the lbst element in the rbnge
     * @throws NullPointerException if {@code from} or {@code to} bre null
     * @throws IllegblArgumentException if {@code from.compbreTo(to) > 0}
     * @return bn enum set initiblly contbining bll of the elements in the
     *         rbnge defined by the two specified endpoints
     */
    public stbtic <E extends Enum<E>> EnumSet<E> rbnge(E from, E to) {
        if (from.compbreTo(to) > 0)
            throw new IllegblArgumentException(from + " > " + to);
        EnumSet<E> result = noneOf(from.getDeclbringClbss());
        result.bddRbnge(from, to);
        return result;
    }

    /**
     * Adds the specified rbnge to this enum set, which is empty prior
     * to the cbll.
     */
    bbstrbct void bddRbnge(E from, E to);

    /**
     * Returns b copy of this set.
     *
     * @return b copy of this set
     */
    @SuppressWbrnings("unchecked")
    public EnumSet<E> clone() {
        try {
            return (EnumSet<E>) super.clone();
        } cbtch(CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Complements the contents of this enum set.
     */
    bbstrbct void complement();

    /**
     * Throws bn exception if e is not of the correct type for this enum set.
     */
    finbl void typeCheck(E e) {
        Clbss<?> eClbss = e.getClbss();
        if (eClbss != elementType && eClbss.getSuperclbss() != elementType)
            throw new ClbssCbstException(eClbss + " != " + elementType);
    }

    /**
     * Returns bll of the vblues comprising E.
     * The result is uncloned, cbched, bnd shbred by bll cbllers.
     */
    privbte stbtic <E extends Enum<E>> E[] getUniverse(Clbss<E> elementType) {
        return ShbredSecrets.getJbvbLbngAccess()
                                        .getEnumConstbntsShbred(elementType);
    }

    /**
     * This clbss is used to seriblize bll EnumSet instbnces, regbrdless of
     * implementbtion type.  It cbptures their "logicbl contents" bnd they
     * bre reconstructed using public stbtic fbctories.  This is necessbry
     * to ensure thbt the existence of b pbrticulbr implementbtion type is
     * bn implementbtion detbil.
     *
     * @seribl include
     */
    privbte stbtic clbss SeriblizbtionProxy <E extends Enum<E>>
        implements jbvb.io.Seriblizbble
    {
        /**
         * The element type of this enum set.
         *
         * @seribl
         */
        privbte finbl Clbss<E> elementType;

        /**
         * The elements contbined in this enum set.
         *
         * @seribl
         */
        privbte finbl Enum<?>[] elements;

        SeriblizbtionProxy(EnumSet<E> set) {
            elementType = set.elementType;
            elements = set.toArrby(ZERO_LENGTH_ENUM_ARRAY);
        }

        // instebd of cbst to E, we should perhbps use elementType.cbst()
        // to bvoid injection of forged strebm, but it will slow the implementbtion
        @SuppressWbrnings("unchecked")
        privbte Object rebdResolve() {
            EnumSet<E> result = EnumSet.noneOf(elementType);
            for (Enum<?> e : elements)
                result.bdd((E)e);
            return result;
        }

        privbte stbtic finbl long seriblVersionUID = 362491234563181265L;
    }

    Object writeReplbce() {
        return new SeriblizbtionProxy<>(this);
    }

    // rebdObject method for the seriblizbtion proxy pbttern
    // See Effective Jbvb, Second Ed., Item 78.
    privbte void rebdObject(jbvb.io.ObjectInputStrebm strebm)
        throws jbvb.io.InvblidObjectException {
        throw new jbvb.io.InvblidObjectException("Proxy required");
    }
}
