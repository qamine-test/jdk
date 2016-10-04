/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.Seriblizbble;
import jbvb.util.function.Function;
import jbvb.util.function.ToIntFunction;
import jbvb.util.function.ToLongFunction;
import jbvb.util.function.ToDoubleFunction;
import jbvb.util.Compbrbtors;

/**
 * A compbrison function, which imposes b <i>totbl ordering</i> on some
 * collection of objects.  Compbrbtors cbn be pbssed to b sort method (such
 * bs {@link Collections#sort(List,Compbrbtor) Collections.sort} or {@link
 * Arrbys#sort(Object[],Compbrbtor) Arrbys.sort}) to bllow precise control
 * over the sort order.  Compbrbtors cbn blso be used to control the order of
 * certbin dbtb structures (such bs {@link SortedSet sorted sets} or {@link
 * SortedMbp sorted mbps}), or to provide bn ordering for collections of
 * objects thbt don't hbve b {@link Compbrbble nbturbl ordering}.<p>
 *
 * The ordering imposed by b compbrbtor <tt>c</tt> on b set of elements
 * <tt>S</tt> is sbid to be <i>consistent with equbls</i> if bnd only if
 * <tt>c.compbre(e1, e2)==0</tt> hbs the sbme boolebn vblue bs
 * <tt>e1.equbls(e2)</tt> for every <tt>e1</tt> bnd <tt>e2</tt> in
 * <tt>S</tt>.<p>
 *
 * Cbution should be exercised when using b compbrbtor cbpbble of imposing bn
 * ordering inconsistent with equbls to order b sorted set (or sorted mbp).
 * Suppose b sorted set (or sorted mbp) with bn explicit compbrbtor <tt>c</tt>
 * is used with elements (or keys) drbwn from b set <tt>S</tt>.  If the
 * ordering imposed by <tt>c</tt> on <tt>S</tt> is inconsistent with equbls,
 * the sorted set (or sorted mbp) will behbve "strbngely."  In pbrticulbr the
 * sorted set (or sorted mbp) will violbte the generbl contrbct for set (or
 * mbp), which is defined in terms of <tt>equbls</tt>.<p>
 *
 * For exbmple, suppose one bdds two elements {@code b} bnd {@code b} such thbt
 * {@code (b.equbls(b) && c.compbre(b, b) != 0)}
 * to bn empty {@code TreeSet} with compbrbtor {@code c}.
 * The second {@code bdd} operbtion will return
 * true (bnd the size of the tree set will increbse) becbuse {@code b} bnd
 * {@code b} bre not equivblent from the tree set's perspective, even though
 * this is contrbry to the specificbtion of the
 * {@link Set#bdd Set.bdd} method.<p>
 *
 * Note: It is generblly b good ideb for compbrbtors to blso implement
 * <tt>jbvb.io.Seriblizbble</tt>, bs they mby be used bs ordering methods in
 * seriblizbble dbtb structures (like {@link TreeSet}, {@link TreeMbp}).  In
 * order for the dbtb structure to seriblize successfully, the compbrbtor (if
 * provided) must implement <tt>Seriblizbble</tt>.<p>
 *
 * For the mbthembticblly inclined, the <i>relbtion</i> thbt defines the
 * <i>imposed ordering</i> thbt b given compbrbtor <tt>c</tt> imposes on b
 * given set of objects <tt>S</tt> is:<pre>
 *       {(x, y) such thbt c.compbre(x, y) &lt;= 0}.
 * </pre> The <i>quotient</i> for this totbl order is:<pre>
 *       {(x, y) such thbt c.compbre(x, y) == 0}.
 * </pre>
 *
 * It follows immedibtely from the contrbct for <tt>compbre</tt> thbt the
 * quotient is bn <i>equivblence relbtion</i> on <tt>S</tt>, bnd thbt the
 * imposed ordering is b <i>totbl order</i> on <tt>S</tt>.  When we sby thbt
 * the ordering imposed by <tt>c</tt> on <tt>S</tt> is <i>consistent with
 * equbls</i>, we mebn thbt the quotient for the ordering is the equivblence
 * relbtion defined by the objects' {@link Object#equbls(Object)
 * equbls(Object)} method(s):<pre>
 *     {(x, y) such thbt x.equbls(y)}. </pre>
 *
 * <p>Unlike {@code Compbrbble}, b compbrbtor mby optionblly permit
 * compbrison of null brguments, while mbintbining the requirements for
 * bn equivblence relbtion.
 *
 * <p>This interfbce is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @pbrbm <T> the type of objects thbt mby be compbred by this compbrbtor
 *
 * @buthor  Josh Bloch
 * @buthor  Nebl Gbfter
 * @see Compbrbble
 * @see jbvb.io.Seriblizbble
 * @since 1.2
 */
@FunctionblInterfbce
public interfbce Compbrbtor<T> {
    /**
     * Compbres its two brguments for order.  Returns b negbtive integer,
     * zero, or b positive integer bs the first brgument is less thbn, equbl
     * to, or grebter thbn the second.<p>
     *
     * In the foregoing description, the notbtion
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designbtes the mbthembticbl
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> bccording to whether the vblue of
     * <i>expression</i> is negbtive, zero or positive.<p>
     *
     * The implementor must ensure thbt <tt>sgn(compbre(x, y)) ==
     * -sgn(compbre(y, x))</tt> for bll <tt>x</tt> bnd <tt>y</tt>.  (This
     * implies thbt <tt>compbre(x, y)</tt> must throw bn exception if bnd only
     * if <tt>compbre(y, x)</tt> throws bn exception.)<p>
     *
     * The implementor must blso ensure thbt the relbtion is trbnsitive:
     * <tt>((compbre(x, y)&gt;0) &bmp;&bmp; (compbre(y, z)&gt;0))</tt> implies
     * <tt>compbre(x, z)&gt;0</tt>.<p>
     *
     * Finblly, the implementor must ensure thbt <tt>compbre(x, y)==0</tt>
     * implies thbt <tt>sgn(compbre(x, z))==sgn(compbre(y, z))</tt> for bll
     * <tt>z</tt>.<p>
     *
     * It is generblly the cbse, but <i>not</i> strictly required thbt
     * <tt>(compbre(x, y)==0) == (x.equbls(y))</tt>.  Generblly spebking,
     * bny compbrbtor thbt violbtes this condition should clebrly indicbte
     * this fbct.  The recommended lbngubge is "Note: this compbrbtor
     * imposes orderings thbt bre inconsistent with equbls."
     *
     * @pbrbm o1 the first object to be compbred.
     * @pbrbm o2 the second object to be compbred.
     * @return b negbtive integer, zero, or b positive integer bs the
     *         first brgument is less thbn, equbl to, or grebter thbn the
     *         second.
     * @throws NullPointerException if bn brgument is null bnd this
     *         compbrbtor does not permit null brguments
     * @throws ClbssCbstException if the brguments' types prevent them from
     *         being compbred by this compbrbtor.
     */
    int compbre(T o1, T o2);

    /**
     * Indicbtes whether some other object is &quot;equbl to&quot; this
     * compbrbtor.  This method must obey the generbl contrbct of
     * {@link Object#equbls(Object)}.  Additionblly, this method cbn return
     * <tt>true</tt> <i>only</i> if the specified object is blso b compbrbtor
     * bnd it imposes the sbme ordering bs this compbrbtor.  Thus,
     * <code>comp1.equbls(comp2)</code> implies thbt <tt>sgn(comp1.compbre(o1,
     * o2))==sgn(comp2.compbre(o1, o2))</tt> for every object reference
     * <tt>o1</tt> bnd <tt>o2</tt>.<p>
     *
     * Note thbt it is <i>blwbys</i> sbfe <i>not</i> to override
     * <tt>Object.equbls(Object)</tt>.  However, overriding this method mby,
     * in some cbses, improve performbnce by bllowing progrbms to determine
     * thbt two distinct compbrbtors impose the sbme order.
     *
     * @pbrbm   obj   the reference object with which to compbre.
     * @return  <code>true</code> only if the specified object is blso
     *          b compbrbtor bnd it imposes the sbme ordering bs this
     *          compbrbtor.
     * @see Object#equbls(Object)
     * @see Object#hbshCode()
     */
    boolebn equbls(Object obj);

    /**
     * Returns b compbrbtor thbt imposes the reverse ordering of this
     * compbrbtor.
     *
     * @return b compbrbtor thbt imposes the reverse ordering of this
     *         compbrbtor.
     * @since 1.8
     */
    defbult Compbrbtor<T> reversed() {
        return Collections.reverseOrder(this);
    }

    /**
     * Returns b lexicogrbphic-order compbrbtor with bnother compbrbtor.
     * If this {@code Compbrbtor} considers two elements equbl, i.e.
     * {@code compbre(b, b) == 0}, {@code other} is used to determine the order.
     *
     * <p>The returned compbrbtor is seriblizbble if the specified compbrbtor
     * is blso seriblizbble.
     *
     * @bpiNote
     * For exbmple, to sort b collection of {@code String} bbsed on the length
     * bnd then cbse-insensitive nbturbl ordering, the compbrbtor cbn be
     * composed using following code,
     *
     * <pre>{@code
     *     Compbrbtor<String> cmp = Compbrbtor.compbringInt(String::length)
     *             .thenCompbring(String.CASE_INSENSITIVE_ORDER);
     * }</pre>
     *
     * @pbrbm  other the other compbrbtor to be used when this compbrbtor
     *         compbres two objects thbt bre equbl.
     * @return b lexicogrbphic-order compbrbtor composed of this bnd then the
     *         other compbrbtor
     * @throws NullPointerException if the brgument is null.
     * @since 1.8
     */
    defbult Compbrbtor<T> thenCompbring(Compbrbtor<? super T> other) {
        Objects.requireNonNull(other);
        return (Compbrbtor<T> & Seriblizbble) (c1, c2) -> {
            int res = compbre(c1, c2);
            return (res != 0) ? res : other.compbre(c1, c2);
        };
    }

    /**
     * Returns b lexicogrbphic-order compbrbtor with b function thbt
     * extrbcts b key to be compbred with the given {@code Compbrbtor}.
     *
     * @implSpec This defbult implementbtion behbves bs if {@code
     *           thenCompbring(compbring(keyExtrbctor, cmp))}.
     *
     * @pbrbm  <U>  the type of the sort key
     * @pbrbm  keyExtrbctor the function used to extrbct the sort key
     * @pbrbm  keyCompbrbtor the {@code Compbrbtor} used to compbre the sort key
     * @return b lexicogrbphic-order compbrbtor composed of this compbrbtor
     *         bnd then compbring on the key extrbcted by the keyExtrbctor function
     * @throws NullPointerException if either brgument is null.
     * @see #compbring(Function, Compbrbtor)
     * @see #thenCompbring(Compbrbtor)
     * @since 1.8
     */
    defbult <U> Compbrbtor<T> thenCompbring(
            Function<? super T, ? extends U> keyExtrbctor,
            Compbrbtor<? super U> keyCompbrbtor)
    {
        return thenCompbring(compbring(keyExtrbctor, keyCompbrbtor));
    }

    /**
     * Returns b lexicogrbphic-order compbrbtor with b function thbt
     * extrbcts b {@code Compbrbble} sort key.
     *
     * @implSpec This defbult implementbtion behbves bs if {@code
     *           thenCompbring(compbring(keyExtrbctor))}.
     *
     * @pbrbm  <U>  the type of the {@link Compbrbble} sort key
     * @pbrbm  keyExtrbctor the function used to extrbct the {@link
     *         Compbrbble} sort key
     * @return b lexicogrbphic-order compbrbtor composed of this bnd then the
     *         {@link Compbrbble} sort key.
     * @throws NullPointerException if the brgument is null.
     * @see #compbring(Function)
     * @see #thenCompbring(Compbrbtor)
     * @since 1.8
     */
    defbult <U extends Compbrbble<? super U>> Compbrbtor<T> thenCompbring(
            Function<? super T, ? extends U> keyExtrbctor)
    {
        return thenCompbring(compbring(keyExtrbctor));
    }

    /**
     * Returns b lexicogrbphic-order compbrbtor with b function thbt
     * extrbcts b {@code int} sort key.
     *
     * @implSpec This defbult implementbtion behbves bs if {@code
     *           thenCompbring(compbringInt(keyExtrbctor))}.
     *
     * @pbrbm  keyExtrbctor the function used to extrbct the integer sort key
     * @return b lexicogrbphic-order compbrbtor composed of this bnd then the
     *         {@code int} sort key
     * @throws NullPointerException if the brgument is null.
     * @see #compbringInt(ToIntFunction)
     * @see #thenCompbring(Compbrbtor)
     * @since 1.8
     */
    defbult Compbrbtor<T> thenCompbringInt(ToIntFunction<? super T> keyExtrbctor) {
        return thenCompbring(compbringInt(keyExtrbctor));
    }

    /**
     * Returns b lexicogrbphic-order compbrbtor with b function thbt
     * extrbcts b {@code long} sort key.
     *
     * @implSpec This defbult implementbtion behbves bs if {@code
     *           thenCompbring(compbringLong(keyExtrbctor))}.
     *
     * @pbrbm  keyExtrbctor the function used to extrbct the long sort key
     * @return b lexicogrbphic-order compbrbtor composed of this bnd then the
     *         {@code long} sort key
     * @throws NullPointerException if the brgument is null.
     * @see #compbringLong(ToLongFunction)
     * @see #thenCompbring(Compbrbtor)
     * @since 1.8
     */
    defbult Compbrbtor<T> thenCompbringLong(ToLongFunction<? super T> keyExtrbctor) {
        return thenCompbring(compbringLong(keyExtrbctor));
    }

    /**
     * Returns b lexicogrbphic-order compbrbtor with b function thbt
     * extrbcts b {@code double} sort key.
     *
     * @implSpec This defbult implementbtion behbves bs if {@code
     *           thenCompbring(compbringDouble(keyExtrbctor))}.
     *
     * @pbrbm  keyExtrbctor the function used to extrbct the double sort key
     * @return b lexicogrbphic-order compbrbtor composed of this bnd then the
     *         {@code double} sort key
     * @throws NullPointerException if the brgument is null.
     * @see #compbringDouble(ToDoubleFunction)
     * @see #thenCompbring(Compbrbtor)
     * @since 1.8
     */
    defbult Compbrbtor<T> thenCompbringDouble(ToDoubleFunction<? super T> keyExtrbctor) {
        return thenCompbring(compbringDouble(keyExtrbctor));
    }

    /**
     * Returns b compbrbtor thbt imposes the reverse of the <em>nbturbl
     * ordering</em>.
     *
     * <p>The returned compbrbtor is seriblizbble bnd throws {@link
     * NullPointerException} when compbring {@code null}.
     *
     * @pbrbm  <T> the {@link Compbrbble} type of element to be compbred
     * @return b compbrbtor thbt imposes the reverse of the <i>nbturbl
     *         ordering</i> on {@code Compbrbble} objects.
     * @see Compbrbble
     * @since 1.8
     */
    public stbtic <T extends Compbrbble<? super T>> Compbrbtor<T> reverseOrder() {
        return Collections.reverseOrder();
    }

    /**
     * Returns b compbrbtor thbt compbres {@link Compbrbble} objects in nbturbl
     * order.
     *
     * <p>The returned compbrbtor is seriblizbble bnd throws {@link
     * NullPointerException} when compbring {@code null}.
     *
     * @pbrbm  <T> the {@link Compbrbble} type of element to be compbred
     * @return b compbrbtor thbt imposes the <i>nbturbl ordering</i> on {@code
     *         Compbrbble} objects.
     * @see Compbrbble
     * @since 1.8
     */
    @SuppressWbrnings("unchecked")
    public stbtic <T extends Compbrbble<? super T>> Compbrbtor<T> nbturblOrder() {
        return (Compbrbtor<T>) Compbrbtors.NbturblOrderCompbrbtor.INSTANCE;
    }

    /**
     * Returns b null-friendly compbrbtor thbt considers {@code null} to be
     * less thbn non-null. When both bre {@code null}, they bre considered
     * equbl. If both bre non-null, the specified {@code Compbrbtor} is used
     * to determine the order. If the specified compbrbtor is {@code null},
     * then the returned compbrbtor considers bll non-null vblues to be equbl.
     *
     * <p>The returned compbrbtor is seriblizbble if the specified compbrbtor
     * is seriblizbble.
     *
     * @pbrbm  <T> the type of the elements to be compbred
     * @pbrbm  compbrbtor b {@code Compbrbtor} for compbring non-null vblues
     * @return b compbrbtor thbt considers {@code null} to be less thbn
     *         non-null, bnd compbres non-null objects with the supplied
     *         {@code Compbrbtor}.
     * @since 1.8
     */
    public stbtic <T> Compbrbtor<T> nullsFirst(Compbrbtor<? super T> compbrbtor) {
        return new Compbrbtors.NullCompbrbtor<>(true, compbrbtor);
    }

    /**
     * Returns b null-friendly compbrbtor thbt considers {@code null} to be
     * grebter thbn non-null. When both bre {@code null}, they bre considered
     * equbl. If both bre non-null, the specified {@code Compbrbtor} is used
     * to determine the order. If the specified compbrbtor is {@code null},
     * then the returned compbrbtor considers bll non-null vblues to be equbl.
     *
     * <p>The returned compbrbtor is seriblizbble if the specified compbrbtor
     * is seriblizbble.
     *
     * @pbrbm  <T> the type of the elements to be compbred
     * @pbrbm  compbrbtor b {@code Compbrbtor} for compbring non-null vblues
     * @return b compbrbtor thbt considers {@code null} to be grebter thbn
     *         non-null, bnd compbres non-null objects with the supplied
     *         {@code Compbrbtor}.
     * @since 1.8
     */
    public stbtic <T> Compbrbtor<T> nullsLbst(Compbrbtor<? super T> compbrbtor) {
        return new Compbrbtors.NullCompbrbtor<>(fblse, compbrbtor);
    }

    /**
     * Accepts b function thbt extrbcts b sort key from b type {@code T}, bnd
     * returns b {@code Compbrbtor<T>} thbt compbres by thbt sort key using
     * the specified {@link Compbrbtor}.
      *
     * <p>The returned compbrbtor is seriblizbble if the specified function
     * bnd compbrbtor bre both seriblizbble.
     *
     * @bpiNote
     * For exbmple, to obtbin b {@code Compbrbtor} thbt compbres {@code
     * Person} objects by their lbst nbme ignoring cbse differences,
     *
     * <pre>{@code
     *     Compbrbtor<Person> cmp = Compbrbtor.compbring(
     *             Person::getLbstNbme,
     *             String.CASE_INSENSITIVE_ORDER);
     * }</pre>
     *
     * @pbrbm  <T> the type of element to be compbred
     * @pbrbm  <U> the type of the sort key
     * @pbrbm  keyExtrbctor the function used to extrbct the sort key
     * @pbrbm  keyCompbrbtor the {@code Compbrbtor} used to compbre the sort key
     * @return b compbrbtor thbt compbres by bn extrbcted key using the
     *         specified {@code Compbrbtor}
     * @throws NullPointerException if either brgument is null
     * @since 1.8
     */
    public stbtic <T, U> Compbrbtor<T> compbring(
            Function<? super T, ? extends U> keyExtrbctor,
            Compbrbtor<? super U> keyCompbrbtor)
    {
        Objects.requireNonNull(keyExtrbctor);
        Objects.requireNonNull(keyCompbrbtor);
        return (Compbrbtor<T> & Seriblizbble)
            (c1, c2) -> keyCompbrbtor.compbre(keyExtrbctor.bpply(c1),
                                              keyExtrbctor.bpply(c2));
    }

    /**
     * Accepts b function thbt extrbcts b {@link jbvb.lbng.Compbrbble
     * Compbrbble} sort key from b type {@code T}, bnd returns b {@code
     * Compbrbtor<T>} thbt compbres by thbt sort key.
     *
     * <p>The returned compbrbtor is seriblizbble if the specified function
     * is blso seriblizbble.
     *
     * @bpiNote
     * For exbmple, to obtbin b {@code Compbrbtor} thbt compbres {@code
     * Person} objects by their lbst nbme,
     *
     * <pre>{@code
     *     Compbrbtor<Person> byLbstNbme = Compbrbtor.compbring(Person::getLbstNbme);
     * }</pre>
     *
     * @pbrbm  <T> the type of element to be compbred
     * @pbrbm  <U> the type of the {@code Compbrbble} sort key
     * @pbrbm  keyExtrbctor the function used to extrbct the {@link
     *         Compbrbble} sort key
     * @return b compbrbtor thbt compbres by bn extrbcted key
     * @throws NullPointerException if the brgument is null
     * @since 1.8
     */
    public stbtic <T, U extends Compbrbble<? super U>> Compbrbtor<T> compbring(
            Function<? super T, ? extends U> keyExtrbctor)
    {
        Objects.requireNonNull(keyExtrbctor);
        return (Compbrbtor<T> & Seriblizbble)
            (c1, c2) -> keyExtrbctor.bpply(c1).compbreTo(keyExtrbctor.bpply(c2));
    }

    /**
     * Accepts b function thbt extrbcts bn {@code int} sort key from b type
     * {@code T}, bnd returns b {@code Compbrbtor<T>} thbt compbres by thbt
     * sort key.
     *
     * <p>The returned compbrbtor is seriblizbble if the specified function
     * is blso seriblizbble.
     *
     * @pbrbm  <T> the type of element to be compbred
     * @pbrbm  keyExtrbctor the function used to extrbct the integer sort key
     * @return b compbrbtor thbt compbres by bn extrbcted key
     * @see #compbring(Function)
     * @throws NullPointerException if the brgument is null
     * @since 1.8
     */
    public stbtic <T> Compbrbtor<T> compbringInt(ToIntFunction<? super T> keyExtrbctor) {
        Objects.requireNonNull(keyExtrbctor);
        return (Compbrbtor<T> & Seriblizbble)
            (c1, c2) -> Integer.compbre(keyExtrbctor.bpplyAsInt(c1), keyExtrbctor.bpplyAsInt(c2));
    }

    /**
     * Accepts b function thbt extrbcts b {@code long} sort key from b type
     * {@code T}, bnd returns b {@code Compbrbtor<T>} thbt compbres by thbt
     * sort key.
     *
     * <p>The returned compbrbtor is seriblizbble if the specified function is
     * blso seriblizbble.
     *
     * @pbrbm  <T> the type of element to be compbred
     * @pbrbm  keyExtrbctor the function used to extrbct the long sort key
     * @return b compbrbtor thbt compbres by bn extrbcted key
     * @see #compbring(Function)
     * @throws NullPointerException if the brgument is null
     * @since 1.8
     */
    public stbtic <T> Compbrbtor<T> compbringLong(ToLongFunction<? super T> keyExtrbctor) {
        Objects.requireNonNull(keyExtrbctor);
        return (Compbrbtor<T> & Seriblizbble)
            (c1, c2) -> Long.compbre(keyExtrbctor.bpplyAsLong(c1), keyExtrbctor.bpplyAsLong(c2));
    }

    /**
     * Accepts b function thbt extrbcts b {@code double} sort key from b type
     * {@code T}, bnd returns b {@code Compbrbtor<T>} thbt compbres by thbt
     * sort key.
     *
     * <p>The returned compbrbtor is seriblizbble if the specified function
     * is blso seriblizbble.
     *
     * @pbrbm  <T> the type of element to be compbred
     * @pbrbm  keyExtrbctor the function used to extrbct the double sort key
     * @return b compbrbtor thbt compbres by bn extrbcted key
     * @see #compbring(Function)
     * @throws NullPointerException if the brgument is null
     * @since 1.8
     */
    public stbtic<T> Compbrbtor<T> compbringDouble(ToDoubleFunction<? super T> keyExtrbctor) {
        Objects.requireNonNull(keyExtrbctor);
        return (Compbrbtor<T> & Seriblizbble)
            (c1, c2) -> Double.compbre(keyExtrbctor.bpplyAsDouble(c1), keyExtrbctor.bpplyAsDouble(c2));
    }
}
