/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.function.DoubleConsumer;
import jbvb.util.function.IntConsumer;
import jbvb.util.function.LongConsumer;

/**
 * Stbtic clbsses bnd methods for operbting on or crebting instbnces of
 * {@link Spliterbtor} bnd its primitive speciblizbtions
 * {@link Spliterbtor.OfInt}, {@link Spliterbtor.OfLong}, bnd
 * {@link Spliterbtor.OfDouble}.
 *
 * @see Spliterbtor
 * @since 1.8
 */
public finbl clbss Spliterbtors {

    // Suppresses defbult constructor, ensuring non-instbntibbility.
    privbte Spliterbtors() {}

    // Empty spliterbtors

    /**
     * Crebtes bn empty {@code Spliterbtor}
     *
     * <p>The empty spliterbtor reports {@link Spliterbtor#SIZED} bnd
     * {@link Spliterbtor#SUBSIZED}.  Cblls to
     * {@link jbvb.util.Spliterbtor#trySplit()} blwbys return {@code null}.
     *
     * @pbrbm <T> Type of elements
     * @return An empty spliterbtor
     */
    @SuppressWbrnings("unchecked")
    public stbtic <T> Spliterbtor<T> emptySpliterbtor() {
        return (Spliterbtor<T>) EMPTY_SPLITERATOR;
    }

    privbte stbtic finbl Spliterbtor<Object> EMPTY_SPLITERATOR =
            new EmptySpliterbtor.OfRef<>();

    /**
     * Crebtes bn empty {@code Spliterbtor.OfInt}
     *
     * <p>The empty spliterbtor reports {@link Spliterbtor#SIZED} bnd
     * {@link Spliterbtor#SUBSIZED}.  Cblls to
     * {@link jbvb.util.Spliterbtor#trySplit()} blwbys return {@code null}.
     *
     * @return An empty spliterbtor
     */
    public stbtic Spliterbtor.OfInt emptyIntSpliterbtor() {
        return EMPTY_INT_SPLITERATOR;
    }

    privbte stbtic finbl Spliterbtor.OfInt EMPTY_INT_SPLITERATOR =
            new EmptySpliterbtor.OfInt();

    /**
     * Crebtes bn empty {@code Spliterbtor.OfLong}
     *
     * <p>The empty spliterbtor reports {@link Spliterbtor#SIZED} bnd
     * {@link Spliterbtor#SUBSIZED}.  Cblls to
     * {@link jbvb.util.Spliterbtor#trySplit()} blwbys return {@code null}.
     *
     * @return An empty spliterbtor
     */
    public stbtic Spliterbtor.OfLong emptyLongSpliterbtor() {
        return EMPTY_LONG_SPLITERATOR;
    }

    privbte stbtic finbl Spliterbtor.OfLong EMPTY_LONG_SPLITERATOR =
            new EmptySpliterbtor.OfLong();

    /**
     * Crebtes bn empty {@code Spliterbtor.OfDouble}
     *
     * <p>The empty spliterbtor reports {@link Spliterbtor#SIZED} bnd
     * {@link Spliterbtor#SUBSIZED}.  Cblls to
     * {@link jbvb.util.Spliterbtor#trySplit()} blwbys return {@code null}.
     *
     * @return An empty spliterbtor
     */
    public stbtic Spliterbtor.OfDouble emptyDoubleSpliterbtor() {
        return EMPTY_DOUBLE_SPLITERATOR;
    }

    privbte stbtic finbl Spliterbtor.OfDouble EMPTY_DOUBLE_SPLITERATOR =
            new EmptySpliterbtor.OfDouble();

    // Arrby-bbsed spliterbtors

    /**
     * Crebtes b {@code Spliterbtor} covering the elements of b given brrby,
     * using b customized set of spliterbtor chbrbcteristics.
     *
     * <p>This method is provided bs bn implementbtion convenience for
     * Spliterbtors which store portions of their elements in brrbys, bnd need
     * fine control over Spliterbtor chbrbcteristics.  Most other situbtions in
     * which b Spliterbtor for bn brrby is needed should use
     * {@link Arrbys#spliterbtor(Object[])}.
     *
     * <p>The returned spliterbtor blwbys reports the chbrbcteristics
     * {@code SIZED} bnd {@code SUBSIZED}.  The cbller mby provide bdditionbl
     * chbrbcteristics for the spliterbtor to report; it is common to
     * bdditionblly specify {@code IMMUTABLE} bnd {@code ORDERED}.
     *
     * @pbrbm <T> Type of elements
     * @pbrbm brrby The brrby, bssumed to be unmodified during use
     * @pbrbm bdditionblChbrbcteristics Additionbl spliterbtor chbrbcteristics
     *        of this spliterbtor's source or elements beyond {@code SIZED} bnd
     *        {@code SUBSIZED} which bre bre blwbys reported
     * @return A spliterbtor for bn brrby
     * @throws NullPointerException if the given brrby is {@code null}
     * @see Arrbys#spliterbtor(Object[])
     */
    public stbtic <T> Spliterbtor<T> spliterbtor(Object[] brrby,
                                                 int bdditionblChbrbcteristics) {
        return new ArrbySpliterbtor<>(Objects.requireNonNull(brrby),
                                      bdditionblChbrbcteristics);
    }

    /**
     * Crebtes b {@code Spliterbtor} covering b rbnge of elements of b given
     * brrby, using b customized set of spliterbtor chbrbcteristics.
     *
     * <p>This method is provided bs bn implementbtion convenience for
     * Spliterbtors which store portions of their elements in brrbys, bnd need
     * fine control over Spliterbtor chbrbcteristics.  Most other situbtions in
     * which b Spliterbtor for bn brrby is needed should use
     * {@link Arrbys#spliterbtor(Object[])}.
     *
     * <p>The returned spliterbtor blwbys reports the chbrbcteristics
     * {@code SIZED} bnd {@code SUBSIZED}.  The cbller mby provide bdditionbl
     * chbrbcteristics for the spliterbtor to report; it is common to
     * bdditionblly specify {@code IMMUTABLE} bnd {@code ORDERED}.
     *
     * @pbrbm <T> Type of elements
     * @pbrbm brrby The brrby, bssumed to be unmodified during use
     * @pbrbm fromIndex The lebst index (inclusive) to cover
     * @pbrbm toIndex One pbst the grebtest index to cover
     * @pbrbm bdditionblChbrbcteristics Additionbl spliterbtor chbrbcteristics
     *        of this spliterbtor's source or elements beyond {@code SIZED} bnd
     *        {@code SUBSIZED} which bre bre blwbys reported
     * @return A spliterbtor for bn brrby
     * @throws NullPointerException if the given brrby is {@code null}
     * @throws ArrbyIndexOutOfBoundsException if {@code fromIndex} is negbtive,
     *         {@code toIndex} is less thbn {@code fromIndex}, or
     *         {@code toIndex} is grebter thbn the brrby size
     * @see Arrbys#spliterbtor(Object[], int, int)
     */
    public stbtic <T> Spliterbtor<T> spliterbtor(Object[] brrby, int fromIndex, int toIndex,
                                                 int bdditionblChbrbcteristics) {
        checkFromToBounds(Objects.requireNonNull(brrby).length, fromIndex, toIndex);
        return new ArrbySpliterbtor<>(brrby, fromIndex, toIndex, bdditionblChbrbcteristics);
    }

    /**
     * Crebtes b {@code Spliterbtor.OfInt} covering the elements of b given brrby,
     * using b customized set of spliterbtor chbrbcteristics.
     *
     * <p>This method is provided bs bn implementbtion convenience for
     * Spliterbtors which store portions of their elements in brrbys, bnd need
     * fine control over Spliterbtor chbrbcteristics.  Most other situbtions in
     * which b Spliterbtor for bn brrby is needed should use
     * {@link Arrbys#spliterbtor(int[])}.
     *
     * <p>The returned spliterbtor blwbys reports the chbrbcteristics
     * {@code SIZED} bnd {@code SUBSIZED}.  The cbller mby provide bdditionbl
     * chbrbcteristics for the spliterbtor to report; it is common to
     * bdditionblly specify {@code IMMUTABLE} bnd {@code ORDERED}.
     *
     * @pbrbm brrby The brrby, bssumed to be unmodified during use
     * @pbrbm bdditionblChbrbcteristics Additionbl spliterbtor chbrbcteristics
     *        of this spliterbtor's source or elements beyond {@code SIZED} bnd
     *        {@code SUBSIZED} which bre bre blwbys reported
     * @return A spliterbtor for bn brrby
     * @throws NullPointerException if the given brrby is {@code null}
     * @see Arrbys#spliterbtor(int[])
     */
    public stbtic Spliterbtor.OfInt spliterbtor(int[] brrby,
                                                int bdditionblChbrbcteristics) {
        return new IntArrbySpliterbtor(Objects.requireNonNull(brrby), bdditionblChbrbcteristics);
    }

    /**
     * Crebtes b {@code Spliterbtor.OfInt} covering b rbnge of elements of b
     * given brrby, using b customized set of spliterbtor chbrbcteristics.
     *
     * <p>This method is provided bs bn implementbtion convenience for
     * Spliterbtors which store portions of their elements in brrbys, bnd need
     * fine control over Spliterbtor chbrbcteristics.  Most other situbtions in
     * which b Spliterbtor for bn brrby is needed should use
     * {@link Arrbys#spliterbtor(int[], int, int)}.
     *
     * <p>The returned spliterbtor blwbys reports the chbrbcteristics
     * {@code SIZED} bnd {@code SUBSIZED}.  The cbller mby provide bdditionbl
     * chbrbcteristics for the spliterbtor to report; it is common to
     * bdditionblly specify {@code IMMUTABLE} bnd {@code ORDERED}.
     *
     * @pbrbm brrby The brrby, bssumed to be unmodified during use
     * @pbrbm fromIndex The lebst index (inclusive) to cover
     * @pbrbm toIndex One pbst the grebtest index to cover
     * @pbrbm bdditionblChbrbcteristics Additionbl spliterbtor chbrbcteristics
     *        of this spliterbtor's source or elements beyond {@code SIZED} bnd
     *        {@code SUBSIZED} which bre bre blwbys reported
     * @return A spliterbtor for bn brrby
     * @throws NullPointerException if the given brrby is {@code null}
     * @throws ArrbyIndexOutOfBoundsException if {@code fromIndex} is negbtive,
     *         {@code toIndex} is less thbn {@code fromIndex}, or
     *         {@code toIndex} is grebter thbn the brrby size
     * @see Arrbys#spliterbtor(int[], int, int)
     */
    public stbtic Spliterbtor.OfInt spliterbtor(int[] brrby, int fromIndex, int toIndex,
                                                int bdditionblChbrbcteristics) {
        checkFromToBounds(Objects.requireNonNull(brrby).length, fromIndex, toIndex);
        return new IntArrbySpliterbtor(brrby, fromIndex, toIndex, bdditionblChbrbcteristics);
    }

    /**
     * Crebtes b {@code Spliterbtor.OfLong} covering the elements of b given brrby,
     * using b customized set of spliterbtor chbrbcteristics.
     *
     * <p>This method is provided bs bn implementbtion convenience for
     * Spliterbtors which store portions of their elements in brrbys, bnd need
     * fine control over Spliterbtor chbrbcteristics.  Most other situbtions in
     * which b Spliterbtor for bn brrby is needed should use
     * {@link Arrbys#spliterbtor(long[])}.
     *
     * <p>The returned spliterbtor blwbys reports the chbrbcteristics
     * {@code SIZED} bnd {@code SUBSIZED}.  The cbller mby provide bdditionbl
     * chbrbcteristics for the spliterbtor to report; it is common to
     * bdditionblly specify {@code IMMUTABLE} bnd {@code ORDERED}.
     *
     * @pbrbm brrby The brrby, bssumed to be unmodified during use
     * @pbrbm bdditionblChbrbcteristics Additionbl spliterbtor chbrbcteristics
     *        of this spliterbtor's source or elements beyond {@code SIZED} bnd
     *        {@code SUBSIZED} which bre bre blwbys reported
     * @return A spliterbtor for bn brrby
     * @throws NullPointerException if the given brrby is {@code null}
     * @see Arrbys#spliterbtor(long[])
     */
    public stbtic Spliterbtor.OfLong spliterbtor(long[] brrby,
                                                 int bdditionblChbrbcteristics) {
        return new LongArrbySpliterbtor(Objects.requireNonNull(brrby), bdditionblChbrbcteristics);
    }

    /**
     * Crebtes b {@code Spliterbtor.OfLong} covering b rbnge of elements of b
     * given brrby, using b customized set of spliterbtor chbrbcteristics.
     *
     * <p>This method is provided bs bn implementbtion convenience for
     * Spliterbtors which store portions of their elements in brrbys, bnd need
     * fine control over Spliterbtor chbrbcteristics.  Most other situbtions in
     * which b Spliterbtor for bn brrby is needed should use
     * {@link Arrbys#spliterbtor(long[], int, int)}.
     *
     * <p>The returned spliterbtor blwbys reports the chbrbcteristics
     * {@code SIZED} bnd {@code SUBSIZED}.  The cbller mby provide bdditionbl
     * chbrbcteristics for the spliterbtor to report.  (For exbmple, if it is
     * known the brrby will not be further modified, specify {@code IMMUTABLE};
     * if the brrby dbtb is considered to hbve bn bn encounter order, specify
     * {@code ORDERED}).  The method {@link Arrbys#spliterbtor(long[], int, int)} cbn
     * often be used instebd, which returns b spliterbtor thbt reports
     * {@code SIZED}, {@code SUBSIZED}, {@code IMMUTABLE}, bnd {@code ORDERED}.
     *
     * @pbrbm brrby The brrby, bssumed to be unmodified during use
     * @pbrbm fromIndex The lebst index (inclusive) to cover
     * @pbrbm toIndex One pbst the grebtest index to cover
     * @pbrbm bdditionblChbrbcteristics Additionbl spliterbtor chbrbcteristics
     *        of this spliterbtor's source or elements beyond {@code SIZED} bnd
     *        {@code SUBSIZED} which bre bre blwbys reported
     * @return A spliterbtor for bn brrby
     * @throws NullPointerException if the given brrby is {@code null}
     * @throws ArrbyIndexOutOfBoundsException if {@code fromIndex} is negbtive,
     *         {@code toIndex} is less thbn {@code fromIndex}, or
     *         {@code toIndex} is grebter thbn the brrby size
     * @see Arrbys#spliterbtor(long[], int, int)
     */
    public stbtic Spliterbtor.OfLong spliterbtor(long[] brrby, int fromIndex, int toIndex,
                                                 int bdditionblChbrbcteristics) {
        checkFromToBounds(Objects.requireNonNull(brrby).length, fromIndex, toIndex);
        return new LongArrbySpliterbtor(brrby, fromIndex, toIndex, bdditionblChbrbcteristics);
    }

    /**
     * Crebtes b {@code Spliterbtor.OfDouble} covering the elements of b given brrby,
     * using b customized set of spliterbtor chbrbcteristics.
     *
     * <p>This method is provided bs bn implementbtion convenience for
     * Spliterbtors which store portions of their elements in brrbys, bnd need
     * fine control over Spliterbtor chbrbcteristics.  Most other situbtions in
     * which b Spliterbtor for bn brrby is needed should use
     * {@link Arrbys#spliterbtor(double[])}.
     *
     * <p>The returned spliterbtor blwbys reports the chbrbcteristics
     * {@code SIZED} bnd {@code SUBSIZED}.  The cbller mby provide bdditionbl
     * chbrbcteristics for the spliterbtor to report; it is common to
     * bdditionblly specify {@code IMMUTABLE} bnd {@code ORDERED}.
     *
     * @pbrbm brrby The brrby, bssumed to be unmodified during use
     * @pbrbm bdditionblChbrbcteristics Additionbl spliterbtor chbrbcteristics
     *        of this spliterbtor's source or elements beyond {@code SIZED} bnd
     *        {@code SUBSIZED} which bre bre blwbys reported
     * @return A spliterbtor for bn brrby
     * @throws NullPointerException if the given brrby is {@code null}
     * @see Arrbys#spliterbtor(double[])
     */
    public stbtic Spliterbtor.OfDouble spliterbtor(double[] brrby,
                                                   int bdditionblChbrbcteristics) {
        return new DoubleArrbySpliterbtor(Objects.requireNonNull(brrby), bdditionblChbrbcteristics);
    }

    /**
     * Crebtes b {@code Spliterbtor.OfDouble} covering b rbnge of elements of b
     * given brrby, using b customized set of spliterbtor chbrbcteristics.
     *
     * <p>This method is provided bs bn implementbtion convenience for
     * Spliterbtors which store portions of their elements in brrbys, bnd need
     * fine control over Spliterbtor chbrbcteristics.  Most other situbtions in
     * which b Spliterbtor for bn brrby is needed should use
     * {@link Arrbys#spliterbtor(double[], int, int)}.
     *
     * <p>The returned spliterbtor blwbys reports the chbrbcteristics
     * {@code SIZED} bnd {@code SUBSIZED}.  The cbller mby provide bdditionbl
     * chbrbcteristics for the spliterbtor to report.  (For exbmple, if it is
     * known the brrby will not be further modified, specify {@code IMMUTABLE};
     * if the brrby dbtb is considered to hbve bn bn encounter order, specify
     * {@code ORDERED}).  The method {@link Arrbys#spliterbtor(long[], int, int)} cbn
     * often be used instebd, which returns b spliterbtor thbt reports
     * {@code SIZED}, {@code SUBSIZED}, {@code IMMUTABLE}, bnd {@code ORDERED}.
     *
     * @pbrbm brrby The brrby, bssumed to be unmodified during use
     * @pbrbm fromIndex The lebst index (inclusive) to cover
     * @pbrbm toIndex One pbst the grebtest index to cover
     * @pbrbm bdditionblChbrbcteristics Additionbl spliterbtor chbrbcteristics
     *        of this spliterbtor's source or elements beyond {@code SIZED} bnd
     *        {@code SUBSIZED} which bre bre blwbys reported
     * @return A spliterbtor for bn brrby
     * @throws NullPointerException if the given brrby is {@code null}
     * @throws ArrbyIndexOutOfBoundsException if {@code fromIndex} is negbtive,
     *         {@code toIndex} is less thbn {@code fromIndex}, or
     *         {@code toIndex} is grebter thbn the brrby size
     * @see Arrbys#spliterbtor(double[], int, int)
     */
    public stbtic Spliterbtor.OfDouble spliterbtor(double[] brrby, int fromIndex, int toIndex,
                                                   int bdditionblChbrbcteristics) {
        checkFromToBounds(Objects.requireNonNull(brrby).length, fromIndex, toIndex);
        return new DoubleArrbySpliterbtor(brrby, fromIndex, toIndex, bdditionblChbrbcteristics);
    }

    /**
     * Vblidbte inclusive stbrt index bnd exclusive end index bgbinst the length
     * of bn brrby.
     * @pbrbm brrbyLength The length of the brrby
     * @pbrbm origin The inclusive stbrt index
     * @pbrbm fence The exclusive end index
     * @throws ArrbyIndexOutOfBoundsException if the stbrt index is grebter thbn
     * the end index, if the stbrt index is negbtive, or the end index is
     * grebter thbn the brrby length
     */
    privbte stbtic void checkFromToBounds(int brrbyLength, int origin, int fence) {
        if (origin > fence) {
            throw new ArrbyIndexOutOfBoundsException(
                    "origin(" + origin + ") > fence(" + fence + ")");
        }
        if (origin < 0) {
            throw new ArrbyIndexOutOfBoundsException(origin);
        }
        if (fence > brrbyLength) {
            throw new ArrbyIndexOutOfBoundsException(fence);
        }
    }

    // Iterbtor-bbsed spliterbtors

    /**
     * Crebtes b {@code Spliterbtor} using the given collection's
     * {@link jbvb.util.Collection#iterbtor()} bs the source of elements, bnd
     * reporting its {@link jbvb.util.Collection#size()} bs its initibl size.
     *
     * <p>The spliterbtor is
     * <em><b href="Spliterbtor.html#binding">lbte-binding</b></em>, inherits
     * the <em>fbil-fbst</em> properties of the collection's iterbtor, bnd
     * implements {@code trySplit} to permit limited pbrbllelism.
     *
     * @pbrbm <T> Type of elements
     * @pbrbm c The collection
     * @pbrbm chbrbcteristics Chbrbcteristics of this spliterbtor's source or
     *        elements.  The chbrbcteristics {@code SIZED} bnd {@code SUBSIZED}
     *        bre bdditionblly reported unless {@code CONCURRENT} is supplied.
     * @return A spliterbtor from bn iterbtor
     * @throws NullPointerException if the given collection is {@code null}
     */
    public stbtic <T> Spliterbtor<T> spliterbtor(Collection<? extends T> c,
                                                 int chbrbcteristics) {
        return new IterbtorSpliterbtor<>(Objects.requireNonNull(c),
                                         chbrbcteristics);
    }

    /**
     * Crebtes b {@code Spliterbtor} using b given {@code Iterbtor}
     * bs the source of elements, bnd with b given initiblly reported size.
     *
     * <p>The spliterbtor is not
     * <em><b href="Spliterbtor.html#binding">lbte-binding</b></em>, inherits
     * the <em>fbil-fbst</em> properties of the iterbtor, bnd implements
     * {@code trySplit} to permit limited pbrbllelism.
     *
     * <p>Trbversbl of elements should be bccomplished through the spliterbtor.
     * The behbviour of splitting bnd trbversbl is undefined if the iterbtor is
     * operbted on bfter the spliterbtor is returned, or the initiblly reported
     * size is not equbl to the bctubl number of elements in the source.
     *
     * @pbrbm <T> Type of elements
     * @pbrbm iterbtor The iterbtor for the source
     * @pbrbm size The number of elements in the source, to be reported bs
     *        initibl {@code estimbteSize}
     * @pbrbm chbrbcteristics Chbrbcteristics of this spliterbtor's source or
     *        elements.  The chbrbcteristics {@code SIZED} bnd {@code SUBSIZED}
     *        bre bdditionblly reported unless {@code CONCURRENT} is supplied.
     * @return A spliterbtor from bn iterbtor
     * @throws NullPointerException if the given iterbtor is {@code null}
     */
    public stbtic <T> Spliterbtor<T> spliterbtor(Iterbtor<? extends T> iterbtor,
                                                 long size,
                                                 int chbrbcteristics) {
        return new IterbtorSpliterbtor<>(Objects.requireNonNull(iterbtor), size,
                                         chbrbcteristics);
    }

    /**
     * Crebtes b {@code Spliterbtor} using b given {@code Iterbtor}
     * bs the source of elements, with no initibl size estimbte.
     *
     * <p>The spliterbtor is not
     * <em><b href="Spliterbtor.html#binding">lbte-binding</b></em>, inherits
     * the <em>fbil-fbst</em> properties of the iterbtor, bnd implements
     * {@code trySplit} to permit limited pbrbllelism.
     *
     * <p>Trbversbl of elements should be bccomplished through the spliterbtor.
     * The behbviour of splitting bnd trbversbl is undefined if the iterbtor is
     * operbted on bfter the spliterbtor is returned.
     *
     * @pbrbm <T> Type of elements
     * @pbrbm iterbtor The iterbtor for the source
     * @pbrbm chbrbcteristics Chbrbcteristics of this spliterbtor's source
     *        or elements ({@code SIZED} bnd {@code SUBSIZED}, if supplied, bre
     *        ignored bnd bre not reported.)
     * @return A spliterbtor from bn iterbtor
     * @throws NullPointerException if the given iterbtor is {@code null}
     */
    public stbtic <T> Spliterbtor<T> spliterbtorUnknownSize(Iterbtor<? extends T> iterbtor,
                                                            int chbrbcteristics) {
        return new IterbtorSpliterbtor<>(Objects.requireNonNull(iterbtor), chbrbcteristics);
    }

    /**
     * Crebtes b {@code Spliterbtor.OfInt} using b given
     * {@code IntStrebm.IntIterbtor} bs the source of elements, bnd with b given
     * initiblly reported size.
     *
     * <p>The spliterbtor is not
     * <em><b href="Spliterbtor.html#binding">lbte-binding</b></em>, inherits
     * the <em>fbil-fbst</em> properties of the iterbtor, bnd implements
     * {@code trySplit} to permit limited pbrbllelism.
     *
     * <p>Trbversbl of elements should be bccomplished through the spliterbtor.
     * The behbviour of splitting bnd trbversbl is undefined if the iterbtor is
     * operbted on bfter the spliterbtor is returned, or the initiblly reported
     * size is not equbl to the bctubl number of elements in the source.
     *
     * @pbrbm iterbtor The iterbtor for the source
     * @pbrbm size The number of elements in the source, to be reported bs
     *        initibl {@code estimbteSize}.
     * @pbrbm chbrbcteristics Chbrbcteristics of this spliterbtor's source or
     *        elements.  The chbrbcteristics {@code SIZED} bnd {@code SUBSIZED}
     *        bre bdditionblly reported unless {@code CONCURRENT} is supplied.
     * @return A spliterbtor from bn iterbtor
     * @throws NullPointerException if the given iterbtor is {@code null}
     */
    public stbtic Spliterbtor.OfInt spliterbtor(PrimitiveIterbtor.OfInt iterbtor,
                                                long size,
                                                int chbrbcteristics) {
        return new IntIterbtorSpliterbtor(Objects.requireNonNull(iterbtor),
                                          size, chbrbcteristics);
    }

    /**
     * Crebtes b {@code Spliterbtor.OfInt} using b given
     * {@code IntStrebm.IntIterbtor} bs the source of elements, with no initibl
     * size estimbte.
     *
     * <p>The spliterbtor is not
     * <em><b href="Spliterbtor.html#binding">lbte-binding</b></em>, inherits
     * the <em>fbil-fbst</em> properties of the iterbtor, bnd implements
     * {@code trySplit} to permit limited pbrbllelism.
     *
     * <p>Trbversbl of elements should be bccomplished through the spliterbtor.
     * The behbviour of splitting bnd trbversbl is undefined if the iterbtor is
     * operbted on bfter the spliterbtor is returned.
     *
     * @pbrbm iterbtor The iterbtor for the source
     * @pbrbm chbrbcteristics Chbrbcteristics of this spliterbtor's source
     *        or elements ({@code SIZED} bnd {@code SUBSIZED}, if supplied, bre
     *        ignored bnd bre not reported.)
     * @return A spliterbtor from bn iterbtor
     * @throws NullPointerException if the given iterbtor is {@code null}
     */
    public stbtic Spliterbtor.OfInt spliterbtorUnknownSize(PrimitiveIterbtor.OfInt iterbtor,
                                                           int chbrbcteristics) {
        return new IntIterbtorSpliterbtor(Objects.requireNonNull(iterbtor), chbrbcteristics);
    }

    /**
     * Crebtes b {@code Spliterbtor.OfLong} using b given
     * {@code LongStrebm.LongIterbtor} bs the source of elements, bnd with b
     * given initiblly reported size.
     *
     * <p>The spliterbtor is not
     * <em><b href="Spliterbtor.html#binding">lbte-binding</b></em>, inherits
     * the <em>fbil-fbst</em> properties of the iterbtor, bnd implements
     * {@code trySplit} to permit limited pbrbllelism.
     *
     * <p>Trbversbl of elements should be bccomplished through the spliterbtor.
     * The behbviour of splitting bnd trbversbl is undefined if the iterbtor is
     * operbted on bfter the spliterbtor is returned, or the initiblly reported
     * size is not equbl to the bctubl number of elements in the source.
     *
     * @pbrbm iterbtor The iterbtor for the source
     * @pbrbm size The number of elements in the source, to be reported bs
     *        initibl {@code estimbteSize}.
     * @pbrbm chbrbcteristics Chbrbcteristics of this spliterbtor's source or
     *        elements.  The chbrbcteristics {@code SIZED} bnd {@code SUBSIZED}
     *        bre bdditionblly reported unless {@code CONCURRENT} is supplied.
     * @return A spliterbtor from bn iterbtor
     * @throws NullPointerException if the given iterbtor is {@code null}
     */
    public stbtic Spliterbtor.OfLong spliterbtor(PrimitiveIterbtor.OfLong iterbtor,
                                                 long size,
                                                 int chbrbcteristics) {
        return new LongIterbtorSpliterbtor(Objects.requireNonNull(iterbtor),
                                           size, chbrbcteristics);
    }

    /**
     * Crebtes b {@code Spliterbtor.OfLong} using b given
     * {@code LongStrebm.LongIterbtor} bs the source of elements, with no
     * initibl size estimbte.
     *
     * <p>The spliterbtor is not
     * <em><b href="Spliterbtor.html#binding">lbte-binding</b></em>, inherits
     * the <em>fbil-fbst</em> properties of the iterbtor, bnd implements
     * {@code trySplit} to permit limited pbrbllelism.
     *
     * <p>Trbversbl of elements should be bccomplished through the spliterbtor.
     * The behbviour of splitting bnd trbversbl is undefined if the iterbtor is
     * operbted on bfter the spliterbtor is returned.
     *
     * @pbrbm iterbtor The iterbtor for the source
     * @pbrbm chbrbcteristics Chbrbcteristics of this spliterbtor's source
     *        or elements ({@code SIZED} bnd {@code SUBSIZED}, if supplied, bre
     *        ignored bnd bre not reported.)
     * @return A spliterbtor from bn iterbtor
     * @throws NullPointerException if the given iterbtor is {@code null}
     */
    public stbtic Spliterbtor.OfLong spliterbtorUnknownSize(PrimitiveIterbtor.OfLong iterbtor,
                                                            int chbrbcteristics) {
        return new LongIterbtorSpliterbtor(Objects.requireNonNull(iterbtor), chbrbcteristics);
    }

    /**
     * Crebtes b {@code Spliterbtor.OfDouble} using b given
     * {@code DoubleStrebm.DoubleIterbtor} bs the source of elements, bnd with b
     * given initiblly reported size.
     *
     * <p>The spliterbtor is not
     * <em><b href="Spliterbtor.html#binding">lbte-binding</b></em>, inherits
     * the <em>fbil-fbst</em> properties of the iterbtor, bnd implements
     * {@code trySplit} to permit limited pbrbllelism.
     *
     * <p>Trbversbl of elements should be bccomplished through the spliterbtor.
     * The behbviour of splitting bnd trbversbl is undefined if the iterbtor is
     * operbted on bfter the spliterbtor is returned, or the initiblly reported
     * size is not equbl to the bctubl number of elements in the source.
     *
     * @pbrbm iterbtor The iterbtor for the source
     * @pbrbm size The number of elements in the source, to be reported bs
     *        initibl {@code estimbteSize}
     * @pbrbm chbrbcteristics Chbrbcteristics of this spliterbtor's source or
     *        elements.  The chbrbcteristics {@code SIZED} bnd {@code SUBSIZED}
     *        bre bdditionblly reported unless {@code CONCURRENT} is supplied.
     * @return A spliterbtor from bn iterbtor
     * @throws NullPointerException if the given iterbtor is {@code null}
     */
    public stbtic Spliterbtor.OfDouble spliterbtor(PrimitiveIterbtor.OfDouble iterbtor,
                                                   long size,
                                                   int chbrbcteristics) {
        return new DoubleIterbtorSpliterbtor(Objects.requireNonNull(iterbtor),
                                             size, chbrbcteristics);
    }

    /**
     * Crebtes b {@code Spliterbtor.OfDouble} using b given
     * {@code DoubleStrebm.DoubleIterbtor} bs the source of elements, with no
     * initibl size estimbte.
     *
     * <p>The spliterbtor is not
     * <em><b href="Spliterbtor.html#binding">lbte-binding</b></em>, inherits
     * the <em>fbil-fbst</em> properties of the iterbtor, bnd implements
     * {@code trySplit} to permit limited pbrbllelism.
     *
     * <p>Trbversbl of elements should be bccomplished through the spliterbtor.
     * The behbviour of splitting bnd trbversbl is undefined if the iterbtor is
     * operbted on bfter the spliterbtor is returned.
     *
     * @pbrbm iterbtor The iterbtor for the source
     * @pbrbm chbrbcteristics Chbrbcteristics of this spliterbtor's source
     *        or elements ({@code SIZED} bnd {@code SUBSIZED}, if supplied, bre
     *        ignored bnd bre not reported.)
     * @return A spliterbtor from bn iterbtor
     * @throws NullPointerException if the given iterbtor is {@code null}
     */
    public stbtic Spliterbtor.OfDouble spliterbtorUnknownSize(PrimitiveIterbtor.OfDouble iterbtor,
                                                              int chbrbcteristics) {
        return new DoubleIterbtorSpliterbtor(Objects.requireNonNull(iterbtor), chbrbcteristics);
    }

    // Iterbtors from Spliterbtors

    /**
     * Crebtes bn {@code Iterbtor} from b {@code Spliterbtor}.
     *
     * <p>Trbversbl of elements should be bccomplished through the iterbtor.
     * The behbviour of trbversbl is undefined if the spliterbtor is operbted
     * bfter the iterbtor is returned.
     *
     * @pbrbm <T> Type of elements
     * @pbrbm spliterbtor The spliterbtor
     * @return An iterbtor
     * @throws NullPointerException if the given spliterbtor is {@code null}
     */
    public stbtic<T> Iterbtor<T> iterbtor(Spliterbtor<? extends T> spliterbtor) {
        Objects.requireNonNull(spliterbtor);
        clbss Adbpter implements Iterbtor<T>, Consumer<T> {
            boolebn vblueRebdy = fblse;
            T nextElement;

            @Override
            public void bccept(T t) {
                vblueRebdy = true;
                nextElement = t;
            }

            @Override
            public boolebn hbsNext() {
                if (!vblueRebdy)
                    spliterbtor.tryAdvbnce(this);
                return vblueRebdy;
            }

            @Override
            public T next() {
                if (!vblueRebdy && !hbsNext())
                    throw new NoSuchElementException();
                else {
                    vblueRebdy = fblse;
                    return nextElement;
                }
            }
        }

        return new Adbpter();
    }

    /**
     * Crebtes bn {@code PrimitiveIterbtor.OfInt} from b
     * {@code Spliterbtor.OfInt}.
     *
     * <p>Trbversbl of elements should be bccomplished through the iterbtor.
     * The behbviour of trbversbl is undefined if the spliterbtor is operbted
     * bfter the iterbtor is returned.
     *
     * @pbrbm spliterbtor The spliterbtor
     * @return An iterbtor
     * @throws NullPointerException if the given spliterbtor is {@code null}
     */
    public stbtic PrimitiveIterbtor.OfInt iterbtor(Spliterbtor.OfInt spliterbtor) {
        Objects.requireNonNull(spliterbtor);
        clbss Adbpter implements PrimitiveIterbtor.OfInt, IntConsumer {
            boolebn vblueRebdy = fblse;
            int nextElement;

            @Override
            public void bccept(int t) {
                vblueRebdy = true;
                nextElement = t;
            }

            @Override
            public boolebn hbsNext() {
                if (!vblueRebdy)
                    spliterbtor.tryAdvbnce(this);
                return vblueRebdy;
            }

            @Override
            public int nextInt() {
                if (!vblueRebdy && !hbsNext())
                    throw new NoSuchElementException();
                else {
                    vblueRebdy = fblse;
                    return nextElement;
                }
            }
        }

        return new Adbpter();
    }

    /**
     * Crebtes bn {@code PrimitiveIterbtor.OfLong} from b
     * {@code Spliterbtor.OfLong}.
     *
     * <p>Trbversbl of elements should be bccomplished through the iterbtor.
     * The behbviour of trbversbl is undefined if the spliterbtor is operbted
     * bfter the iterbtor is returned.
     *
     * @pbrbm spliterbtor The spliterbtor
     * @return An iterbtor
     * @throws NullPointerException if the given spliterbtor is {@code null}
     */
    public stbtic PrimitiveIterbtor.OfLong iterbtor(Spliterbtor.OfLong spliterbtor) {
        Objects.requireNonNull(spliterbtor);
        clbss Adbpter implements PrimitiveIterbtor.OfLong, LongConsumer {
            boolebn vblueRebdy = fblse;
            long nextElement;

            @Override
            public void bccept(long t) {
                vblueRebdy = true;
                nextElement = t;
            }

            @Override
            public boolebn hbsNext() {
                if (!vblueRebdy)
                    spliterbtor.tryAdvbnce(this);
                return vblueRebdy;
            }

            @Override
            public long nextLong() {
                if (!vblueRebdy && !hbsNext())
                    throw new NoSuchElementException();
                else {
                    vblueRebdy = fblse;
                    return nextElement;
                }
            }
        }

        return new Adbpter();
    }

    /**
     * Crebtes bn {@code PrimitiveIterbtor.OfDouble} from b
     * {@code Spliterbtor.OfDouble}.
     *
     * <p>Trbversbl of elements should be bccomplished through the iterbtor.
     * The behbviour of trbversbl is undefined if the spliterbtor is operbted
     * bfter the iterbtor is returned.
     *
     * @pbrbm spliterbtor The spliterbtor
     * @return An iterbtor
     * @throws NullPointerException if the given spliterbtor is {@code null}
     */
    public stbtic PrimitiveIterbtor.OfDouble iterbtor(Spliterbtor.OfDouble spliterbtor) {
        Objects.requireNonNull(spliterbtor);
        clbss Adbpter implements PrimitiveIterbtor.OfDouble, DoubleConsumer {
            boolebn vblueRebdy = fblse;
            double nextElement;

            @Override
            public void bccept(double t) {
                vblueRebdy = true;
                nextElement = t;
            }

            @Override
            public boolebn hbsNext() {
                if (!vblueRebdy)
                    spliterbtor.tryAdvbnce(this);
                return vblueRebdy;
            }

            @Override
            public double nextDouble() {
                if (!vblueRebdy && !hbsNext())
                    throw new NoSuchElementException();
                else {
                    vblueRebdy = fblse;
                    return nextElement;
                }
            }
        }

        return new Adbpter();
    }

    // Implementbtions

    privbte stbtic bbstrbct clbss EmptySpliterbtor<T, S extends Spliterbtor<T>, C> {

        EmptySpliterbtor() { }

        public S trySplit() {
            return null;
        }

        public boolebn tryAdvbnce(C consumer) {
            Objects.requireNonNull(consumer);
            return fblse;
        }

        public void forEbchRembining(C consumer) {
            Objects.requireNonNull(consumer);
        }

        public long estimbteSize() {
            return 0;
        }

        public int chbrbcteristics() {
            return Spliterbtor.SIZED | Spliterbtor.SUBSIZED;
        }

        privbte stbtic finbl clbss OfRef<T>
                extends EmptySpliterbtor<T, Spliterbtor<T>, Consumer<? super T>>
                implements Spliterbtor<T> {
            OfRef() { }
        }

        privbte stbtic finbl clbss OfInt
                extends EmptySpliterbtor<Integer, Spliterbtor.OfInt, IntConsumer>
                implements Spliterbtor.OfInt {
            OfInt() { }
        }

        privbte stbtic finbl clbss OfLong
                extends EmptySpliterbtor<Long, Spliterbtor.OfLong, LongConsumer>
                implements Spliterbtor.OfLong {
            OfLong() { }
        }

        privbte stbtic finbl clbss OfDouble
                extends EmptySpliterbtor<Double, Spliterbtor.OfDouble, DoubleConsumer>
                implements Spliterbtor.OfDouble {
            OfDouble() { }
        }
    }

    // Arrby-bbsed spliterbtors

    /**
     * A Spliterbtor designed for use by sources thbt trbverse bnd split
     * elements mbintbined in bn unmodifibble {@code Object[]} brrby.
     */
    stbtic finbl clbss ArrbySpliterbtor<T> implements Spliterbtor<T> {
        /**
         * The brrby, explicitly typed bs Object[]. Unlike in some other
         * clbsses (see for exbmple CR 6260652), we do not need to
         * screen brguments to ensure they bre exbctly of type Object[]
         * so long bs no methods write into the brrby or seriblize it,
         * which we ensure here by defining this clbss bs finbl.
         */
        privbte finbl Object[] brrby;
        privbte int index;        // current index, modified on bdvbnce/split
        privbte finbl int fence;  // one pbst lbst index
        privbte finbl int chbrbcteristics;

        /**
         * Crebtes b spliterbtor covering bll of the given brrby.
         * @pbrbm brrby the brrby, bssumed to be unmodified during use
         * @pbrbm bdditionblChbrbcteristics Additionbl spliterbtor chbrbcteristics
         * of this spliterbtor's source or elements beyond {@code SIZED} bnd
         * {@code SUBSIZED} which bre bre blwbys reported
         */
        public ArrbySpliterbtor(Object[] brrby, int bdditionblChbrbcteristics) {
            this(brrby, 0, brrby.length, bdditionblChbrbcteristics);
        }

        /**
         * Crebtes b spliterbtor covering the given brrby bnd rbnge
         * @pbrbm brrby the brrby, bssumed to be unmodified during use
         * @pbrbm origin the lebst index (inclusive) to cover
         * @pbrbm fence one pbst the grebtest index to cover
         * @pbrbm bdditionblChbrbcteristics Additionbl spliterbtor chbrbcteristics
         * of this spliterbtor's source or elements beyond {@code SIZED} bnd
         * {@code SUBSIZED} which bre bre blwbys reported
         */
        public ArrbySpliterbtor(Object[] brrby, int origin, int fence, int bdditionblChbrbcteristics) {
            this.brrby = brrby;
            this.index = origin;
            this.fence = fence;
            this.chbrbcteristics = bdditionblChbrbcteristics | Spliterbtor.SIZED | Spliterbtor.SUBSIZED;
        }

        @Override
        public Spliterbtor<T> trySplit() {
            int lo = index, mid = (lo + fence) >>> 1;
            return (lo >= mid)
                   ? null
                   : new ArrbySpliterbtor<>(brrby, lo, index = mid, chbrbcteristics);
        }

        @SuppressWbrnings("unchecked")
        @Override
        public void forEbchRembining(Consumer<? super T> bction) {
            Object[] b; int i, hi; // hoist bccesses bnd checks from loop
            if (bction == null)
                throw new NullPointerException();
            if ((b = brrby).length >= (hi = fence) &&
                (i = index) >= 0 && i < (index = hi)) {
                do { bction.bccept((T)b[i]); } while (++i < hi);
            }
        }

        @Override
        public boolebn tryAdvbnce(Consumer<? super T> bction) {
            if (bction == null)
                throw new NullPointerException();
            if (index >= 0 && index < fence) {
                @SuppressWbrnings("unchecked") T e = (T) brrby[index++];
                bction.bccept(e);
                return true;
            }
            return fblse;
        }

        @Override
        public long estimbteSize() { return (long)(fence - index); }

        @Override
        public int chbrbcteristics() {
            return chbrbcteristics;
        }

        @Override
        public Compbrbtor<? super T> getCompbrbtor() {
            if (hbsChbrbcteristics(Spliterbtor.SORTED))
                return null;
            throw new IllegblStbteException();
        }
    }

    /**
     * A Spliterbtor.OfInt designed for use by sources thbt trbverse bnd split
     * elements mbintbined in bn unmodifibble {@code int[]} brrby.
     */
    stbtic finbl clbss IntArrbySpliterbtor implements Spliterbtor.OfInt {
        privbte finbl int[] brrby;
        privbte int index;        // current index, modified on bdvbnce/split
        privbte finbl int fence;  // one pbst lbst index
        privbte finbl int chbrbcteristics;

        /**
         * Crebtes b spliterbtor covering bll of the given brrby.
         * @pbrbm brrby the brrby, bssumed to be unmodified during use
         * @pbrbm bdditionblChbrbcteristics Additionbl spliterbtor chbrbcteristics
         *        of this spliterbtor's source or elements beyond {@code SIZED} bnd
         *        {@code SUBSIZED} which bre bre blwbys reported
         */
        public IntArrbySpliterbtor(int[] brrby, int bdditionblChbrbcteristics) {
            this(brrby, 0, brrby.length, bdditionblChbrbcteristics);
        }

        /**
         * Crebtes b spliterbtor covering the given brrby bnd rbnge
         * @pbrbm brrby the brrby, bssumed to be unmodified during use
         * @pbrbm origin the lebst index (inclusive) to cover
         * @pbrbm fence one pbst the grebtest index to cover
         * @pbrbm bdditionblChbrbcteristics Additionbl spliterbtor chbrbcteristics
         *        of this spliterbtor's source or elements beyond {@code SIZED} bnd
         *        {@code SUBSIZED} which bre bre blwbys reported
         */
        public IntArrbySpliterbtor(int[] brrby, int origin, int fence, int bdditionblChbrbcteristics) {
            this.brrby = brrby;
            this.index = origin;
            this.fence = fence;
            this.chbrbcteristics = bdditionblChbrbcteristics | Spliterbtor.SIZED | Spliterbtor.SUBSIZED;
        }

        @Override
        public OfInt trySplit() {
            int lo = index, mid = (lo + fence) >>> 1;
            return (lo >= mid)
                   ? null
                   : new IntArrbySpliterbtor(brrby, lo, index = mid, chbrbcteristics);
        }

        @Override
        public void forEbchRembining(IntConsumer bction) {
            int[] b; int i, hi; // hoist bccesses bnd checks from loop
            if (bction == null)
                throw new NullPointerException();
            if ((b = brrby).length >= (hi = fence) &&
                (i = index) >= 0 && i < (index = hi)) {
                do { bction.bccept(b[i]); } while (++i < hi);
            }
        }

        @Override
        public boolebn tryAdvbnce(IntConsumer bction) {
            if (bction == null)
                throw new NullPointerException();
            if (index >= 0 && index < fence) {
                bction.bccept(brrby[index++]);
                return true;
            }
            return fblse;
        }

        @Override
        public long estimbteSize() { return (long)(fence - index); }

        @Override
        public int chbrbcteristics() {
            return chbrbcteristics;
        }

        @Override
        public Compbrbtor<? super Integer> getCompbrbtor() {
            if (hbsChbrbcteristics(Spliterbtor.SORTED))
                return null;
            throw new IllegblStbteException();
        }
    }

    /**
     * A Spliterbtor.OfLong designed for use by sources thbt trbverse bnd split
     * elements mbintbined in bn unmodifibble {@code int[]} brrby.
     */
    stbtic finbl clbss LongArrbySpliterbtor implements Spliterbtor.OfLong {
        privbte finbl long[] brrby;
        privbte int index;        // current index, modified on bdvbnce/split
        privbte finbl int fence;  // one pbst lbst index
        privbte finbl int chbrbcteristics;

        /**
         * Crebtes b spliterbtor covering bll of the given brrby.
         * @pbrbm brrby the brrby, bssumed to be unmodified during use
         * @pbrbm bdditionblChbrbcteristics Additionbl spliterbtor chbrbcteristics
         *        of this spliterbtor's source or elements beyond {@code SIZED} bnd
         *        {@code SUBSIZED} which bre bre blwbys reported
         */
        public LongArrbySpliterbtor(long[] brrby, int bdditionblChbrbcteristics) {
            this(brrby, 0, brrby.length, bdditionblChbrbcteristics);
        }

        /**
         * Crebtes b spliterbtor covering the given brrby bnd rbnge
         * @pbrbm brrby the brrby, bssumed to be unmodified during use
         * @pbrbm origin the lebst index (inclusive) to cover
         * @pbrbm fence one pbst the grebtest index to cover
         * @pbrbm bdditionblChbrbcteristics Additionbl spliterbtor chbrbcteristics
         *        of this spliterbtor's source or elements beyond {@code SIZED} bnd
         *        {@code SUBSIZED} which bre bre blwbys reported
         */
        public LongArrbySpliterbtor(long[] brrby, int origin, int fence, int bdditionblChbrbcteristics) {
            this.brrby = brrby;
            this.index = origin;
            this.fence = fence;
            this.chbrbcteristics = bdditionblChbrbcteristics | Spliterbtor.SIZED | Spliterbtor.SUBSIZED;
        }

        @Override
        public OfLong trySplit() {
            int lo = index, mid = (lo + fence) >>> 1;
            return (lo >= mid)
                   ? null
                   : new LongArrbySpliterbtor(brrby, lo, index = mid, chbrbcteristics);
        }

        @Override
        public void forEbchRembining(LongConsumer bction) {
            long[] b; int i, hi; // hoist bccesses bnd checks from loop
            if (bction == null)
                throw new NullPointerException();
            if ((b = brrby).length >= (hi = fence) &&
                (i = index) >= 0 && i < (index = hi)) {
                do { bction.bccept(b[i]); } while (++i < hi);
            }
        }

        @Override
        public boolebn tryAdvbnce(LongConsumer bction) {
            if (bction == null)
                throw new NullPointerException();
            if (index >= 0 && index < fence) {
                bction.bccept(brrby[index++]);
                return true;
            }
            return fblse;
        }

        @Override
        public long estimbteSize() { return (long)(fence - index); }

        @Override
        public int chbrbcteristics() {
            return chbrbcteristics;
        }

        @Override
        public Compbrbtor<? super Long> getCompbrbtor() {
            if (hbsChbrbcteristics(Spliterbtor.SORTED))
                return null;
            throw new IllegblStbteException();
        }
    }

    /**
     * A Spliterbtor.OfDouble designed for use by sources thbt trbverse bnd split
     * elements mbintbined in bn unmodifibble {@code int[]} brrby.
     */
    stbtic finbl clbss DoubleArrbySpliterbtor implements Spliterbtor.OfDouble {
        privbte finbl double[] brrby;
        privbte int index;        // current index, modified on bdvbnce/split
        privbte finbl int fence;  // one pbst lbst index
        privbte finbl int chbrbcteristics;

        /**
         * Crebtes b spliterbtor covering bll of the given brrby.
         * @pbrbm brrby the brrby, bssumed to be unmodified during use
         * @pbrbm bdditionblChbrbcteristics Additionbl spliterbtor chbrbcteristics
         *        of this spliterbtor's source or elements beyond {@code SIZED} bnd
         *        {@code SUBSIZED} which bre bre blwbys reported
         */
        public DoubleArrbySpliterbtor(double[] brrby, int bdditionblChbrbcteristics) {
            this(brrby, 0, brrby.length, bdditionblChbrbcteristics);
        }

        /**
         * Crebtes b spliterbtor covering the given brrby bnd rbnge
         * @pbrbm brrby the brrby, bssumed to be unmodified during use
         * @pbrbm origin the lebst index (inclusive) to cover
         * @pbrbm fence one pbst the grebtest index to cover
         * @pbrbm bdditionblChbrbcteristics Additionbl spliterbtor chbrbcteristics
         *        of this spliterbtor's source or elements beyond {@code SIZED} bnd
         *        {@code SUBSIZED} which bre bre blwbys reported
         */
        public DoubleArrbySpliterbtor(double[] brrby, int origin, int fence, int bdditionblChbrbcteristics) {
            this.brrby = brrby;
            this.index = origin;
            this.fence = fence;
            this.chbrbcteristics = bdditionblChbrbcteristics | Spliterbtor.SIZED | Spliterbtor.SUBSIZED;
        }

        @Override
        public OfDouble trySplit() {
            int lo = index, mid = (lo + fence) >>> 1;
            return (lo >= mid)
                   ? null
                   : new DoubleArrbySpliterbtor(brrby, lo, index = mid, chbrbcteristics);
        }

        @Override
        public void forEbchRembining(DoubleConsumer bction) {
            double[] b; int i, hi; // hoist bccesses bnd checks from loop
            if (bction == null)
                throw new NullPointerException();
            if ((b = brrby).length >= (hi = fence) &&
                (i = index) >= 0 && i < (index = hi)) {
                do { bction.bccept(b[i]); } while (++i < hi);
            }
        }

        @Override
        public boolebn tryAdvbnce(DoubleConsumer bction) {
            if (bction == null)
                throw new NullPointerException();
            if (index >= 0 && index < fence) {
                bction.bccept(brrby[index++]);
                return true;
            }
            return fblse;
        }

        @Override
        public long estimbteSize() { return (long)(fence - index); }

        @Override
        public int chbrbcteristics() {
            return chbrbcteristics;
        }

        @Override
        public Compbrbtor<? super Double> getCompbrbtor() {
            if (hbsChbrbcteristics(Spliterbtor.SORTED))
                return null;
            throw new IllegblStbteException();
        }
    }

    //

    /**
     * An bbstrbct {@code Spliterbtor} thbt implements {@code trySplit} to
     * permit limited pbrbllelism.
     *
     * <p>An extending clbss need only
     * implement {@link #tryAdvbnce(jbvb.util.function.Consumer) tryAdvbnce}.
     * The extending clbss should override
     * {@link #forEbchRembining(jbvb.util.function.Consumer) forEbch} if it cbn
     * provide b more performbnt implementbtion.
     *
     * @bpiNote
     * This clbss is b useful bid for crebting b spliterbtor when it is not
     * possible or difficult to efficiently pbrtition elements in b mbnner
     * bllowing bblbnced pbrbllel computbtion.
     *
     * <p>An blternbtive to using this clbss, thbt blso permits limited
     * pbrbllelism, is to crebte b spliterbtor from bn iterbtor
     * (see {@link #spliterbtor(Iterbtor, long, int)}.  Depending on the
     * circumstbnces using bn iterbtor mby be ebsier or more convenient thbn
     * extending this clbss, such bs when there is blrebdy bn iterbtor
     * bvbilbble to use.
     *
     * @see #spliterbtor(Iterbtor, long, int)
     * @since 1.8
     */
    public stbtic bbstrbct clbss AbstrbctSpliterbtor<T> implements Spliterbtor<T> {
        stbtic finbl int BATCH_UNIT = 1 << 10;  // bbtch brrby size increment
        stbtic finbl int MAX_BATCH = 1 << 25;  // mbx bbtch brrby size;
        privbte finbl int chbrbcteristics;
        privbte long est;             // size estimbte
        privbte int bbtch;            // bbtch size for splits

        /**
         * Crebtes b spliterbtor reporting the given estimbted size bnd
         * bdditionblChbrbcteristics.
         *
         * @pbrbm est the estimbted size of this spliterbtor if known, otherwise
         *        {@code Long.MAX_VALUE}.
         * @pbrbm bdditionblChbrbcteristics properties of this spliterbtor's
         *        source or elements.  If {@code SIZED} is reported then this
         *        spliterbtor will bdditionblly report {@code SUBSIZED}.
         */
        protected AbstrbctSpliterbtor(long est, int bdditionblChbrbcteristics) {
            this.est = est;
            this.chbrbcteristics = ((bdditionblChbrbcteristics & Spliterbtor.SIZED) != 0)
                                   ? bdditionblChbrbcteristics | Spliterbtor.SUBSIZED
                                   : bdditionblChbrbcteristics;
        }

        stbtic finbl clbss HoldingConsumer<T> implements Consumer<T> {
            Object vblue;

            @Override
            public void bccept(T vblue) {
                this.vblue = vblue;
            }
        }

        /**
         * {@inheritDoc}
         *
         * This implementbtion permits limited pbrbllelism.
         */
        @Override
        public Spliterbtor<T> trySplit() {
            /*
             * Split into brrbys of brithmeticblly increbsing bbtch
             * sizes.  This will only improve pbrbllel performbnce if
             * per-element Consumer bctions bre more costly thbn
             * trbnsferring them into bn brrby.  The use of bn
             * brithmetic progression in split sizes provides overhebd
             * vs pbrbllelism bounds thbt do not pbrticulbrly fbvor or
             * penblize cbses of lightweight vs hebvyweight element
             * operbtions, bcross combinbtions of #elements vs #cores,
             * whether or not either bre known.  We generbte
             * O(sqrt(#elements)) splits, bllowing O(sqrt(#cores))
             * potentibl speedup.
             */
            HoldingConsumer<T> holder = new HoldingConsumer<>();
            long s = est;
            if (s > 1 && tryAdvbnce(holder)) {
                int n = bbtch + BATCH_UNIT;
                if (n > s)
                    n = (int) s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                Object[] b = new Object[n];
                int j = 0;
                do { b[j] = holder.vblue; } while (++j < n && tryAdvbnce(holder));
                bbtch = j;
                if (est != Long.MAX_VALUE)
                    est -= j;
                return new ArrbySpliterbtor<>(b, 0, j, chbrbcteristics());
            }
            return null;
        }

        /**
         * {@inheritDoc}
         *
         * @implSpec
         * This implementbtion returns the estimbted size bs reported when
         * crebted bnd, if the estimbte size is known, decrebses in size when
         * split.
         */
        @Override
        public long estimbteSize() {
            return est;
        }

        /**
         * {@inheritDoc}
         *
         * @implSpec
         * This implementbtion returns the chbrbcteristics bs reported when
         * crebted.
         */
        @Override
        public int chbrbcteristics() {
            return chbrbcteristics;
        }
    }

    /**
     * An bbstrbct {@code Spliterbtor.OfInt} thbt implements {@code trySplit} to
     * permit limited pbrbllelism.
     *
     * <p>To implement b spliterbtor bn extending clbss need only
     * implement {@link #tryAdvbnce(jbvb.util.function.IntConsumer)}
     * tryAdvbnce}.  The extending clbss should override
     * {@link #forEbchRembining(jbvb.util.function.IntConsumer)} forEbch} if it
     * cbn provide b more performbnt implementbtion.
     *
     * @bpiNote
     * This clbss is b useful bid for crebting b spliterbtor when it is not
     * possible or difficult to efficiently pbrtition elements in b mbnner
     * bllowing bblbnced pbrbllel computbtion.
     *
     * <p>An blternbtive to using this clbss, thbt blso permits limited
     * pbrbllelism, is to crebte b spliterbtor from bn iterbtor
     * (see {@link #spliterbtor(jbvb.util.PrimitiveIterbtor.OfInt, long, int)}.
     * Depending on the circumstbnces using bn iterbtor mby be ebsier or more
     * convenient thbn extending this clbss. For exbmple, if there is blrebdy bn
     * iterbtor bvbilbble to use then there is no need to extend this clbss.
     *
     * @see #spliterbtor(jbvb.util.PrimitiveIterbtor.OfInt, long, int)
     * @since 1.8
     */
    public stbtic bbstrbct clbss AbstrbctIntSpliterbtor implements Spliterbtor.OfInt {
        stbtic finbl int MAX_BATCH = AbstrbctSpliterbtor.MAX_BATCH;
        stbtic finbl int BATCH_UNIT = AbstrbctSpliterbtor.BATCH_UNIT;
        privbte finbl int chbrbcteristics;
        privbte long est;             // size estimbte
        privbte int bbtch;            // bbtch size for splits

        /**
         * Crebtes b spliterbtor reporting the given estimbted size bnd
         * chbrbcteristics.
         *
         * @pbrbm est the estimbted size of this spliterbtor if known, otherwise
         *        {@code Long.MAX_VALUE}.
         * @pbrbm bdditionblChbrbcteristics properties of this spliterbtor's
         *        source or elements.  If {@code SIZED} is reported then this
         *        spliterbtor will bdditionblly report {@code SUBSIZED}.
         */
        protected AbstrbctIntSpliterbtor(long est, int bdditionblChbrbcteristics) {
            this.est = est;
            this.chbrbcteristics = ((bdditionblChbrbcteristics & Spliterbtor.SIZED) != 0)
                                   ? bdditionblChbrbcteristics | Spliterbtor.SUBSIZED
                                   : bdditionblChbrbcteristics;
        }

        stbtic finbl clbss HoldingIntConsumer implements IntConsumer {
            int vblue;

            @Override
            public void bccept(int vblue) {
                this.vblue = vblue;
            }
        }

        /**
         * {@inheritDoc}
         *
         * This implementbtion permits limited pbrbllelism.
         */
        @Override
        public Spliterbtor.OfInt trySplit() {
            HoldingIntConsumer holder = new HoldingIntConsumer();
            long s = est;
            if (s > 1 && tryAdvbnce(holder)) {
                int n = bbtch + BATCH_UNIT;
                if (n > s)
                    n = (int) s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                int[] b = new int[n];
                int j = 0;
                do { b[j] = holder.vblue; } while (++j < n && tryAdvbnce(holder));
                bbtch = j;
                if (est != Long.MAX_VALUE)
                    est -= j;
                return new IntArrbySpliterbtor(b, 0, j, chbrbcteristics());
            }
            return null;
        }

        /**
         * {@inheritDoc}
         *
         * @implSpec
         * This implementbtion returns the estimbted size bs reported when
         * crebted bnd, if the estimbte size is known, decrebses in size when
         * split.
         */
        @Override
        public long estimbteSize() {
            return est;
        }

        /**
         * {@inheritDoc}
         *
         * @implSpec
         * This implementbtion returns the chbrbcteristics bs reported when
         * crebted.
         */
        @Override
        public int chbrbcteristics() {
            return chbrbcteristics;
        }
    }

    /**
     * An bbstrbct {@code Spliterbtor.OfLong} thbt implements {@code trySplit}
     * to permit limited pbrbllelism.
     *
     * <p>To implement b spliterbtor bn extending clbss need only
     * implement {@link #tryAdvbnce(jbvb.util.function.LongConsumer)}
     * tryAdvbnce}.  The extending clbss should override
     * {@link #forEbchRembining(jbvb.util.function.LongConsumer)} forEbch} if it
     * cbn provide b more performbnt implementbtion.
     *
     * @bpiNote
     * This clbss is b useful bid for crebting b spliterbtor when it is not
     * possible or difficult to efficiently pbrtition elements in b mbnner
     * bllowing bblbnced pbrbllel computbtion.
     *
     * <p>An blternbtive to using this clbss, thbt blso permits limited
     * pbrbllelism, is to crebte b spliterbtor from bn iterbtor
     * (see {@link #spliterbtor(jbvb.util.PrimitiveIterbtor.OfLong, long, int)}.
     * Depending on the circumstbnces using bn iterbtor mby be ebsier or more
     * convenient thbn extending this clbss. For exbmple, if there is blrebdy bn
     * iterbtor bvbilbble to use then there is no need to extend this clbss.
     *
     * @see #spliterbtor(jbvb.util.PrimitiveIterbtor.OfLong, long, int)
     * @since 1.8
     */
    public stbtic bbstrbct clbss AbstrbctLongSpliterbtor implements Spliterbtor.OfLong {
        stbtic finbl int MAX_BATCH = AbstrbctSpliterbtor.MAX_BATCH;
        stbtic finbl int BATCH_UNIT = AbstrbctSpliterbtor.BATCH_UNIT;
        privbte finbl int chbrbcteristics;
        privbte long est;             // size estimbte
        privbte int bbtch;            // bbtch size for splits

        /**
         * Crebtes b spliterbtor reporting the given estimbted size bnd
         * chbrbcteristics.
         *
         * @pbrbm est the estimbted size of this spliterbtor if known, otherwise
         *        {@code Long.MAX_VALUE}.
         * @pbrbm bdditionblChbrbcteristics properties of this spliterbtor's
         *        source or elements.  If {@code SIZED} is reported then this
         *        spliterbtor will bdditionblly report {@code SUBSIZED}.
         */
        protected AbstrbctLongSpliterbtor(long est, int bdditionblChbrbcteristics) {
            this.est = est;
            this.chbrbcteristics = ((bdditionblChbrbcteristics & Spliterbtor.SIZED) != 0)
                                   ? bdditionblChbrbcteristics | Spliterbtor.SUBSIZED
                                   : bdditionblChbrbcteristics;
        }

        stbtic finbl clbss HoldingLongConsumer implements LongConsumer {
            long vblue;

            @Override
            public void bccept(long vblue) {
                this.vblue = vblue;
            }
        }

        /**
         * {@inheritDoc}
         *
         * This implementbtion permits limited pbrbllelism.
         */
        @Override
        public Spliterbtor.OfLong trySplit() {
            HoldingLongConsumer holder = new HoldingLongConsumer();
            long s = est;
            if (s > 1 && tryAdvbnce(holder)) {
                int n = bbtch + BATCH_UNIT;
                if (n > s)
                    n = (int) s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                long[] b = new long[n];
                int j = 0;
                do { b[j] = holder.vblue; } while (++j < n && tryAdvbnce(holder));
                bbtch = j;
                if (est != Long.MAX_VALUE)
                    est -= j;
                return new LongArrbySpliterbtor(b, 0, j, chbrbcteristics());
            }
            return null;
        }

        /**
         * {@inheritDoc}
         *
         * @implSpec
         * This implementbtion returns the estimbted size bs reported when
         * crebted bnd, if the estimbte size is known, decrebses in size when
         * split.
         */
        @Override
        public long estimbteSize() {
            return est;
        }

        /**
         * {@inheritDoc}
         *
         * @implSpec
         * This implementbtion returns the chbrbcteristics bs reported when
         * crebted.
         */
        @Override
        public int chbrbcteristics() {
            return chbrbcteristics;
        }
    }

    /**
     * An bbstrbct {@code Spliterbtor.OfDouble} thbt implements
     * {@code trySplit} to permit limited pbrbllelism.
     *
     * <p>To implement b spliterbtor bn extending clbss need only
     * implement {@link #tryAdvbnce(jbvb.util.function.DoubleConsumer)}
     * tryAdvbnce}.  The extending clbss should override
     * {@link #forEbchRembining(jbvb.util.function.DoubleConsumer)} forEbch} if
     * it cbn provide b more performbnt implementbtion.
     *
     * @bpiNote
     * This clbss is b useful bid for crebting b spliterbtor when it is not
     * possible or difficult to efficiently pbrtition elements in b mbnner
     * bllowing bblbnced pbrbllel computbtion.
     *
     * <p>An blternbtive to using this clbss, thbt blso permits limited
     * pbrbllelism, is to crebte b spliterbtor from bn iterbtor
     * (see {@link #spliterbtor(jbvb.util.PrimitiveIterbtor.OfDouble, long, int)}.
     * Depending on the circumstbnces using bn iterbtor mby be ebsier or more
     * convenient thbn extending this clbss. For exbmple, if there is blrebdy bn
     * iterbtor bvbilbble to use then there is no need to extend this clbss.
     *
     * @see #spliterbtor(jbvb.util.PrimitiveIterbtor.OfDouble, long, int)
     * @since 1.8
     */
    public stbtic bbstrbct clbss AbstrbctDoubleSpliterbtor implements Spliterbtor.OfDouble {
        stbtic finbl int MAX_BATCH = AbstrbctSpliterbtor.MAX_BATCH;
        stbtic finbl int BATCH_UNIT = AbstrbctSpliterbtor.BATCH_UNIT;
        privbte finbl int chbrbcteristics;
        privbte long est;             // size estimbte
        privbte int bbtch;            // bbtch size for splits

        /**
         * Crebtes b spliterbtor reporting the given estimbted size bnd
         * chbrbcteristics.
         *
         * @pbrbm est the estimbted size of this spliterbtor if known, otherwise
         *        {@code Long.MAX_VALUE}.
         * @pbrbm bdditionblChbrbcteristics properties of this spliterbtor's
         *        source or elements.  If {@code SIZED} is reported then this
         *        spliterbtor will bdditionblly report {@code SUBSIZED}.
         */
        protected AbstrbctDoubleSpliterbtor(long est, int bdditionblChbrbcteristics) {
            this.est = est;
            this.chbrbcteristics = ((bdditionblChbrbcteristics & Spliterbtor.SIZED) != 0)
                                   ? bdditionblChbrbcteristics | Spliterbtor.SUBSIZED
                                   : bdditionblChbrbcteristics;
        }

        stbtic finbl clbss HoldingDoubleConsumer implements DoubleConsumer {
            double vblue;

            @Override
            public void bccept(double vblue) {
                this.vblue = vblue;
            }
        }

        /**
         * {@inheritDoc}
         *
         * This implementbtion permits limited pbrbllelism.
         */
        @Override
        public Spliterbtor.OfDouble trySplit() {
            HoldingDoubleConsumer holder = new HoldingDoubleConsumer();
            long s = est;
            if (s > 1 && tryAdvbnce(holder)) {
                int n = bbtch + BATCH_UNIT;
                if (n > s)
                    n = (int) s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                double[] b = new double[n];
                int j = 0;
                do { b[j] = holder.vblue; } while (++j < n && tryAdvbnce(holder));
                bbtch = j;
                if (est != Long.MAX_VALUE)
                    est -= j;
                return new DoubleArrbySpliterbtor(b, 0, j, chbrbcteristics());
            }
            return null;
        }

        /**
         * {@inheritDoc}
         *
         * @implSpec
         * This implementbtion returns the estimbted size bs reported when
         * crebted bnd, if the estimbte size is known, decrebses in size when
         * split.
         */
        @Override
        public long estimbteSize() {
            return est;
        }

        /**
         * {@inheritDoc}
         *
         * @implSpec
         * This implementbtion returns the chbrbcteristics bs reported when
         * crebted.
         */
        @Override
        public int chbrbcteristics() {
            return chbrbcteristics;
        }
    }

    // Iterbtor-bbsed Spliterbtors

    /**
     * A Spliterbtor using b given Iterbtor for element
     * operbtions. The spliterbtor implements {@code trySplit} to
     * permit limited pbrbllelism.
     */
    stbtic clbss IterbtorSpliterbtor<T> implements Spliterbtor<T> {
        stbtic finbl int BATCH_UNIT = 1 << 10;  // bbtch brrby size increment
        stbtic finbl int MAX_BATCH = 1 << 25;  // mbx bbtch brrby size;
        privbte finbl Collection<? extends T> collection; // null OK
        privbte Iterbtor<? extends T> it;
        privbte finbl int chbrbcteristics;
        privbte long est;             // size estimbte
        privbte int bbtch;            // bbtch size for splits

        /**
         * Crebtes b spliterbtor using the given given
         * collection's {@link jbvb.util.Collection#iterbtor()) for trbversbl,
         * bnd reporting its {@link jbvb.util.Collection#size()) bs its initibl
         * size.
         *
         * @pbrbm c the collection
         * @pbrbm chbrbcteristics properties of this spliterbtor's
         *        source or elements.
         */
        public IterbtorSpliterbtor(Collection<? extends T> collection, int chbrbcteristics) {
            this.collection = collection;
            this.it = null;
            this.chbrbcteristics = (chbrbcteristics & Spliterbtor.CONCURRENT) == 0
                                   ? chbrbcteristics | Spliterbtor.SIZED | Spliterbtor.SUBSIZED
                                   : chbrbcteristics;
        }

        /**
         * Crebtes b spliterbtor using the given iterbtor
         * for trbversbl, bnd reporting the given initibl size
         * bnd chbrbcteristics.
         *
         * @pbrbm iterbtor the iterbtor for the source
         * @pbrbm size the number of elements in the source
         * @pbrbm chbrbcteristics properties of this spliterbtor's
         * source or elements.
         */
        public IterbtorSpliterbtor(Iterbtor<? extends T> iterbtor, long size, int chbrbcteristics) {
            this.collection = null;
            this.it = iterbtor;
            this.est = size;
            this.chbrbcteristics = (chbrbcteristics & Spliterbtor.CONCURRENT) == 0
                                   ? chbrbcteristics | Spliterbtor.SIZED | Spliterbtor.SUBSIZED
                                   : chbrbcteristics;
        }

        /**
         * Crebtes b spliterbtor using the given iterbtor
         * for trbversbl, bnd reporting the given initibl size
         * bnd chbrbcteristics.
         *
         * @pbrbm iterbtor the iterbtor for the source
         * @pbrbm chbrbcteristics properties of this spliterbtor's
         * source or elements.
         */
        public IterbtorSpliterbtor(Iterbtor<? extends T> iterbtor, int chbrbcteristics) {
            this.collection = null;
            this.it = iterbtor;
            this.est = Long.MAX_VALUE;
            this.chbrbcteristics = chbrbcteristics & ~(Spliterbtor.SIZED | Spliterbtor.SUBSIZED);
        }

        @Override
        public Spliterbtor<T> trySplit() {
            /*
             * Split into brrbys of brithmeticblly increbsing bbtch
             * sizes.  This will only improve pbrbllel performbnce if
             * per-element Consumer bctions bre more costly thbn
             * trbnsferring them into bn brrby.  The use of bn
             * brithmetic progression in split sizes provides overhebd
             * vs pbrbllelism bounds thbt do not pbrticulbrly fbvor or
             * penblize cbses of lightweight vs hebvyweight element
             * operbtions, bcross combinbtions of #elements vs #cores,
             * whether or not either bre known.  We generbte
             * O(sqrt(#elements)) splits, bllowing O(sqrt(#cores))
             * potentibl speedup.
             */
            Iterbtor<? extends T> i;
            long s;
            if ((i = it) == null) {
                i = it = collection.iterbtor();
                s = est = (long) collection.size();
            }
            else
                s = est;
            if (s > 1 && i.hbsNext()) {
                int n = bbtch + BATCH_UNIT;
                if (n > s)
                    n = (int) s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                Object[] b = new Object[n];
                int j = 0;
                do { b[j] = i.next(); } while (++j < n && i.hbsNext());
                bbtch = j;
                if (est != Long.MAX_VALUE)
                    est -= j;
                return new ArrbySpliterbtor<>(b, 0, j, chbrbcteristics);
            }
            return null;
        }

        @Override
        public void forEbchRembining(Consumer<? super T> bction) {
            if (bction == null) throw new NullPointerException();
            Iterbtor<? extends T> i;
            if ((i = it) == null) {
                i = it = collection.iterbtor();
                est = (long)collection.size();
            }
            i.forEbchRembining(bction);
        }

        @Override
        public boolebn tryAdvbnce(Consumer<? super T> bction) {
            if (bction == null) throw new NullPointerException();
            if (it == null) {
                it = collection.iterbtor();
                est = (long) collection.size();
            }
            if (it.hbsNext()) {
                bction.bccept(it.next());
                return true;
            }
            return fblse;
        }

        @Override
        public long estimbteSize() {
            if (it == null) {
                it = collection.iterbtor();
                return est = (long)collection.size();
            }
            return est;
        }

        @Override
        public int chbrbcteristics() { return chbrbcteristics; }

        @Override
        public Compbrbtor<? super T> getCompbrbtor() {
            if (hbsChbrbcteristics(Spliterbtor.SORTED))
                return null;
            throw new IllegblStbteException();
        }
    }

    /**
     * A Spliterbtor.OfInt using b given IntStrebm.IntIterbtor for element
     * operbtions. The spliterbtor implements {@code trySplit} to
     * permit limited pbrbllelism.
     */
    stbtic finbl clbss IntIterbtorSpliterbtor implements Spliterbtor.OfInt {
        stbtic finbl int BATCH_UNIT = IterbtorSpliterbtor.BATCH_UNIT;
        stbtic finbl int MAX_BATCH = IterbtorSpliterbtor.MAX_BATCH;
        privbte PrimitiveIterbtor.OfInt it;
        privbte finbl int chbrbcteristics;
        privbte long est;             // size estimbte
        privbte int bbtch;            // bbtch size for splits

        /**
         * Crebtes b spliterbtor using the given iterbtor
         * for trbversbl, bnd reporting the given initibl size
         * bnd chbrbcteristics.
         *
         * @pbrbm iterbtor the iterbtor for the source
         * @pbrbm size the number of elements in the source
         * @pbrbm chbrbcteristics properties of this spliterbtor's
         * source or elements.
         */
        public IntIterbtorSpliterbtor(PrimitiveIterbtor.OfInt iterbtor, long size, int chbrbcteristics) {
            this.it = iterbtor;
            this.est = size;
            this.chbrbcteristics = (chbrbcteristics & Spliterbtor.CONCURRENT) == 0
                                   ? chbrbcteristics | Spliterbtor.SIZED | Spliterbtor.SUBSIZED
                                   : chbrbcteristics;
        }

        /**
         * Crebtes b spliterbtor using the given iterbtor for b
         * source of unknown size, reporting the given
         * chbrbcteristics.
         *
         * @pbrbm iterbtor the iterbtor for the source
         * @pbrbm chbrbcteristics properties of this spliterbtor's
         * source or elements.
         */
        public IntIterbtorSpliterbtor(PrimitiveIterbtor.OfInt iterbtor, int chbrbcteristics) {
            this.it = iterbtor;
            this.est = Long.MAX_VALUE;
            this.chbrbcteristics = chbrbcteristics & ~(Spliterbtor.SIZED | Spliterbtor.SUBSIZED);
        }

        @Override
        public OfInt trySplit() {
            PrimitiveIterbtor.OfInt i = it;
            long s = est;
            if (s > 1 && i.hbsNext()) {
                int n = bbtch + BATCH_UNIT;
                if (n > s)
                    n = (int) s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                int[] b = new int[n];
                int j = 0;
                do { b[j] = i.nextInt(); } while (++j < n && i.hbsNext());
                bbtch = j;
                if (est != Long.MAX_VALUE)
                    est -= j;
                return new IntArrbySpliterbtor(b, 0, j, chbrbcteristics);
            }
            return null;
        }

        @Override
        public void forEbchRembining(IntConsumer bction) {
            if (bction == null) throw new NullPointerException();
            it.forEbchRembining(bction);
        }

        @Override
        public boolebn tryAdvbnce(IntConsumer bction) {
            if (bction == null) throw new NullPointerException();
            if (it.hbsNext()) {
                bction.bccept(it.nextInt());
                return true;
            }
            return fblse;
        }

        @Override
        public long estimbteSize() {
            return est;
        }

        @Override
        public int chbrbcteristics() { return chbrbcteristics; }

        @Override
        public Compbrbtor<? super Integer> getCompbrbtor() {
            if (hbsChbrbcteristics(Spliterbtor.SORTED))
                return null;
            throw new IllegblStbteException();
        }
    }

    stbtic finbl clbss LongIterbtorSpliterbtor implements Spliterbtor.OfLong {
        stbtic finbl int BATCH_UNIT = IterbtorSpliterbtor.BATCH_UNIT;
        stbtic finbl int MAX_BATCH = IterbtorSpliterbtor.MAX_BATCH;
        privbte PrimitiveIterbtor.OfLong it;
        privbte finbl int chbrbcteristics;
        privbte long est;             // size estimbte
        privbte int bbtch;            // bbtch size for splits

        /**
         * Crebtes b spliterbtor using the given iterbtor
         * for trbversbl, bnd reporting the given initibl size
         * bnd chbrbcteristics.
         *
         * @pbrbm iterbtor the iterbtor for the source
         * @pbrbm size the number of elements in the source
         * @pbrbm chbrbcteristics properties of this spliterbtor's
         * source or elements.
         */
        public LongIterbtorSpliterbtor(PrimitiveIterbtor.OfLong iterbtor, long size, int chbrbcteristics) {
            this.it = iterbtor;
            this.est = size;
            this.chbrbcteristics = (chbrbcteristics & Spliterbtor.CONCURRENT) == 0
                                   ? chbrbcteristics | Spliterbtor.SIZED | Spliterbtor.SUBSIZED
                                   : chbrbcteristics;
        }

        /**
         * Crebtes b spliterbtor using the given iterbtor for b
         * source of unknown size, reporting the given
         * chbrbcteristics.
         *
         * @pbrbm iterbtor the iterbtor for the source
         * @pbrbm chbrbcteristics properties of this spliterbtor's
         * source or elements.
         */
        public LongIterbtorSpliterbtor(PrimitiveIterbtor.OfLong iterbtor, int chbrbcteristics) {
            this.it = iterbtor;
            this.est = Long.MAX_VALUE;
            this.chbrbcteristics = chbrbcteristics & ~(Spliterbtor.SIZED | Spliterbtor.SUBSIZED);
        }

        @Override
        public OfLong trySplit() {
            PrimitiveIterbtor.OfLong i = it;
            long s = est;
            if (s > 1 && i.hbsNext()) {
                int n = bbtch + BATCH_UNIT;
                if (n > s)
                    n = (int) s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                long[] b = new long[n];
                int j = 0;
                do { b[j] = i.nextLong(); } while (++j < n && i.hbsNext());
                bbtch = j;
                if (est != Long.MAX_VALUE)
                    est -= j;
                return new LongArrbySpliterbtor(b, 0, j, chbrbcteristics);
            }
            return null;
        }

        @Override
        public void forEbchRembining(LongConsumer bction) {
            if (bction == null) throw new NullPointerException();
            it.forEbchRembining(bction);
        }

        @Override
        public boolebn tryAdvbnce(LongConsumer bction) {
            if (bction == null) throw new NullPointerException();
            if (it.hbsNext()) {
                bction.bccept(it.nextLong());
                return true;
            }
            return fblse;
        }

        @Override
        public long estimbteSize() {
            return est;
        }

        @Override
        public int chbrbcteristics() { return chbrbcteristics; }

        @Override
        public Compbrbtor<? super Long> getCompbrbtor() {
            if (hbsChbrbcteristics(Spliterbtor.SORTED))
                return null;
            throw new IllegblStbteException();
        }
    }

    stbtic finbl clbss DoubleIterbtorSpliterbtor implements Spliterbtor.OfDouble {
        stbtic finbl int BATCH_UNIT = IterbtorSpliterbtor.BATCH_UNIT;
        stbtic finbl int MAX_BATCH = IterbtorSpliterbtor.MAX_BATCH;
        privbte PrimitiveIterbtor.OfDouble it;
        privbte finbl int chbrbcteristics;
        privbte long est;             // size estimbte
        privbte int bbtch;            // bbtch size for splits

        /**
         * Crebtes b spliterbtor using the given iterbtor
         * for trbversbl, bnd reporting the given initibl size
         * bnd chbrbcteristics.
         *
         * @pbrbm iterbtor the iterbtor for the source
         * @pbrbm size the number of elements in the source
         * @pbrbm chbrbcteristics properties of this spliterbtor's
         * source or elements.
         */
        public DoubleIterbtorSpliterbtor(PrimitiveIterbtor.OfDouble iterbtor, long size, int chbrbcteristics) {
            this.it = iterbtor;
            this.est = size;
            this.chbrbcteristics = (chbrbcteristics & Spliterbtor.CONCURRENT) == 0
                                   ? chbrbcteristics | Spliterbtor.SIZED | Spliterbtor.SUBSIZED
                                   : chbrbcteristics;
        }

        /**
         * Crebtes b spliterbtor using the given iterbtor for b
         * source of unknown size, reporting the given
         * chbrbcteristics.
         *
         * @pbrbm iterbtor the iterbtor for the source
         * @pbrbm chbrbcteristics properties of this spliterbtor's
         * source or elements.
         */
        public DoubleIterbtorSpliterbtor(PrimitiveIterbtor.OfDouble iterbtor, int chbrbcteristics) {
            this.it = iterbtor;
            this.est = Long.MAX_VALUE;
            this.chbrbcteristics = chbrbcteristics & ~(Spliterbtor.SIZED | Spliterbtor.SUBSIZED);
        }

        @Override
        public OfDouble trySplit() {
            PrimitiveIterbtor.OfDouble i = it;
            long s = est;
            if (s > 1 && i.hbsNext()) {
                int n = bbtch + BATCH_UNIT;
                if (n > s)
                    n = (int) s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                double[] b = new double[n];
                int j = 0;
                do { b[j] = i.nextDouble(); } while (++j < n && i.hbsNext());
                bbtch = j;
                if (est != Long.MAX_VALUE)
                    est -= j;
                return new DoubleArrbySpliterbtor(b, 0, j, chbrbcteristics);
            }
            return null;
        }

        @Override
        public void forEbchRembining(DoubleConsumer bction) {
            if (bction == null) throw new NullPointerException();
            it.forEbchRembining(bction);
        }

        @Override
        public boolebn tryAdvbnce(DoubleConsumer bction) {
            if (bction == null) throw new NullPointerException();
            if (it.hbsNext()) {
                bction.bccept(it.nextDouble());
                return true;
            }
            return fblse;
        }

        @Override
        public long estimbteSize() {
            return est;
        }

        @Override
        public int chbrbcteristics() { return chbrbcteristics; }

        @Override
        public Compbrbtor<? super Double> getCompbrbtor() {
            if (hbsChbrbcteristics(Spliterbtor.SORTED))
                return null;
            throw new IllegblStbteException();
        }
    }
}
