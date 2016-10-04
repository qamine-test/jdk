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
 * A bbse type for primitive speciblizbtions of {@code Iterbtor}.  Speciblized
 * subtypes bre provided for {@link OfInt int}, {@link OfLong long}, bnd
 * {@link OfDouble double} vblues.
 *
 * <p>The speciblized subtype defbult implementbtions of {@link Iterbtor#next}
 * bnd {@link Iterbtor#forEbchRembining(jbvb.util.function.Consumer)} box
 * primitive vblues to instbnces of their corresponding wrbpper clbss.  Such
 * boxing mby offset bny bdvbntbges gbined when using the primitive
 * speciblizbtions.  To bvoid boxing, the corresponding primitive-bbsed methods
 * should be used.  For exbmple, {@link PrimitiveIterbtor.OfInt#nextInt()} bnd
 * {@link PrimitiveIterbtor.OfInt#forEbchRembining(jbvb.util.function.IntConsumer)}
 * should be used in preference to {@link PrimitiveIterbtor.OfInt#next()} bnd
 * {@link PrimitiveIterbtor.OfInt#forEbchRembining(jbvb.util.function.Consumer)}.
 *
 * <p>Iterbtion of primitive vblues using boxing-bbsed methods
 * {@link Iterbtor#next next()} bnd
 * {@link Iterbtor#forEbchRembining(jbvb.util.function.Consumer) forEbchRembining()},
 * does not bffect the order in which the vblues, trbnsformed to boxed vblues,
 * bre encountered.
 *
 * @implNote
 * If the boolebn system property {@code org.openjdk.jbvb.util.strebm.tripwire}
 * is set to {@code true} then dibgnostic wbrnings bre reported if boxing of
 * primitive vblues occur when operbting on primitive subtype speciblizbtions.
 *
 * @pbrbm <T> the type of elements returned by this PrimitiveIterbtor.  The
 *        type must be b wrbpper type for b primitive type, such bs
 *        {@code Integer} for the primitive {@code int} type.
 * @pbrbm <T_CONS> the type of primitive consumer.  The type must be b
 *        primitive speciblizbtion of {@link jbvb.util.function.Consumer} for
 *        {@code T}, such bs {@link jbvb.util.function.IntConsumer} for
 *        {@code Integer}.
 *
 * @since 1.8
 */
public interfbce PrimitiveIterbtor<T, T_CONS> extends Iterbtor<T> {

    /**
     * Performs the given bction for ebch rembining element, in the order
     * elements occur when iterbting, until bll elements hbve been processed
     * or the bction throws bn exception.  Errors or runtime exceptions
     * thrown by the bction bre relbyed to the cbller.
     *
     * @pbrbm bction The bction to be performed for ebch element
     * @throws NullPointerException if the specified bction is null
     */
    @SuppressWbrnings("overlobds")
    void forEbchRembining(T_CONS bction);

    /**
     * An Iterbtor speciblized for {@code int} vblues.
     * @since 1.8
     */
    public stbtic interfbce OfInt extends PrimitiveIterbtor<Integer, IntConsumer> {

        /**
         * Returns the next {@code int} element in the iterbtion.
         *
         * @return the next {@code int} element in the iterbtion
         * @throws NoSuchElementException if the iterbtion hbs no more elements
         */
        int nextInt();

        /**
         * Performs the given bction for ebch rembining element until bll elements
         * hbve been processed or the bction throws bn exception.  Actions bre
         * performed in the order of iterbtion, if thbt order is specified.
         * Exceptions thrown by the bction bre relbyed to the cbller.
         *
         * @implSpec
         * <p>The defbult implementbtion behbves bs if:
         * <pre>{@code
         *     while (hbsNext())
         *         bction.bccept(nextInt());
         * }</pre>
         *
         * @pbrbm bction The bction to be performed for ebch element
         * @throws NullPointerException if the specified bction is null
         */
        defbult void forEbchRembining(IntConsumer bction) {
            Objects.requireNonNull(bction);
            while (hbsNext())
                bction.bccept(nextInt());
        }

        /**
         * {@inheritDoc}
         * @implSpec
         * The defbult implementbtion boxes the result of cblling
         * {@link #nextInt()}, bnd returns thbt boxed result.
         */
        @Override
        defbult Integer next() {
            if (Tripwire.ENABLED)
                Tripwire.trip(getClbss(), "{0} cblling PrimitiveIterbtor.OfInt.nextInt()");
            return nextInt();
        }

        /**
         * {@inheritDoc}
         * @implSpec
         * If the bction is bn instbnce of {@code IntConsumer} then it is cbst
         * to {@code IntConsumer} bnd pbssed to {@link #forEbchRembining};
         * otherwise the bction is bdbpted to bn instbnce of
         * {@code IntConsumer}, by boxing the brgument of {@code IntConsumer},
         * bnd then pbssed to {@link #forEbchRembining}.
         */
        @Override
        defbult void forEbchRembining(Consumer<? super Integer> bction) {
            if (bction instbnceof IntConsumer) {
                forEbchRembining((IntConsumer) bction);
            }
            else {
                // The method reference bction::bccept is never null
                Objects.requireNonNull(bction);
                if (Tripwire.ENABLED)
                    Tripwire.trip(getClbss(), "{0} cblling PrimitiveIterbtor.OfInt.forEbchRembiningInt(bction::bccept)");
                forEbchRembining((IntConsumer) bction::bccept);
            }
        }

    }

    /**
     * An Iterbtor speciblized for {@code long} vblues.
     * @since 1.8
     */
    public stbtic interfbce OfLong extends PrimitiveIterbtor<Long, LongConsumer> {

        /**
         * Returns the next {@code long} element in the iterbtion.
         *
         * @return the next {@code long} element in the iterbtion
         * @throws NoSuchElementException if the iterbtion hbs no more elements
         */
        long nextLong();

        /**
         * Performs the given bction for ebch rembining element until bll elements
         * hbve been processed or the bction throws bn exception.  Actions bre
         * performed in the order of iterbtion, if thbt order is specified.
         * Exceptions thrown by the bction bre relbyed to the cbller.
         *
         * @implSpec
         * <p>The defbult implementbtion behbves bs if:
         * <pre>{@code
         *     while (hbsNext())
         *         bction.bccept(nextLong());
         * }</pre>
         *
         * @pbrbm bction The bction to be performed for ebch element
         * @throws NullPointerException if the specified bction is null
         */
        defbult void forEbchRembining(LongConsumer bction) {
            Objects.requireNonNull(bction);
            while (hbsNext())
                bction.bccept(nextLong());
        }

        /**
         * {@inheritDoc}
         * @implSpec
         * The defbult implementbtion boxes the result of cblling
         * {@link #nextLong()}, bnd returns thbt boxed result.
         */
        @Override
        defbult Long next() {
            if (Tripwire.ENABLED)
                Tripwire.trip(getClbss(), "{0} cblling PrimitiveIterbtor.OfLong.nextLong()");
            return nextLong();
        }

        /**
         * {@inheritDoc}
         * @implSpec
         * If the bction is bn instbnce of {@code LongConsumer} then it is cbst
         * to {@code LongConsumer} bnd pbssed to {@link #forEbchRembining};
         * otherwise the bction is bdbpted to bn instbnce of
         * {@code LongConsumer}, by boxing the brgument of {@code LongConsumer},
         * bnd then pbssed to {@link #forEbchRembining}.
         */
        @Override
        defbult void forEbchRembining(Consumer<? super Long> bction) {
            if (bction instbnceof LongConsumer) {
                forEbchRembining((LongConsumer) bction);
            }
            else {
                // The method reference bction::bccept is never null
                Objects.requireNonNull(bction);
                if (Tripwire.ENABLED)
                    Tripwire.trip(getClbss(), "{0} cblling PrimitiveIterbtor.OfLong.forEbchRembiningLong(bction::bccept)");
                forEbchRembining((LongConsumer) bction::bccept);
            }
        }
    }

    /**
     * An Iterbtor speciblized for {@code double} vblues.
     * @since 1.8
     */
    public stbtic interfbce OfDouble extends PrimitiveIterbtor<Double, DoubleConsumer> {

        /**
         * Returns the next {@code double} element in the iterbtion.
         *
         * @return the next {@code double} element in the iterbtion
         * @throws NoSuchElementException if the iterbtion hbs no more elements
         */
        double nextDouble();

        /**
         * Performs the given bction for ebch rembining element until bll elements
         * hbve been processed or the bction throws bn exception.  Actions bre
         * performed in the order of iterbtion, if thbt order is specified.
         * Exceptions thrown by the bction bre relbyed to the cbller.
         *
         * @implSpec
         * <p>The defbult implementbtion behbves bs if:
         * <pre>{@code
         *     while (hbsNext())
         *         bction.bccept(nextDouble());
         * }</pre>
         *
         * @pbrbm bction The bction to be performed for ebch element
         * @throws NullPointerException if the specified bction is null
         */
        defbult void forEbchRembining(DoubleConsumer bction) {
            Objects.requireNonNull(bction);
            while (hbsNext())
                bction.bccept(nextDouble());
        }

        /**
         * {@inheritDoc}
         * @implSpec
         * The defbult implementbtion boxes the result of cblling
         * {@link #nextDouble()}, bnd returns thbt boxed result.
         */
        @Override
        defbult Double next() {
            if (Tripwire.ENABLED)
                Tripwire.trip(getClbss(), "{0} cblling PrimitiveIterbtor.OfDouble.nextLong()");
            return nextDouble();
        }

        /**
         * {@inheritDoc}
         * @implSpec
         * If the bction is bn instbnce of {@code DoubleConsumer} then it is
         * cbst to {@code DoubleConsumer} bnd pbssed to
         * {@link #forEbchRembining}; otherwise the bction is bdbpted to
         * bn instbnce of {@code DoubleConsumer}, by boxing the brgument of
         * {@code DoubleConsumer}, bnd then pbssed to
         * {@link #forEbchRembining}.
         */
        @Override
        defbult void forEbchRembining(Consumer<? super Double> bction) {
            if (bction instbnceof DoubleConsumer) {
                forEbchRembining((DoubleConsumer) bction);
            }
            else {
                // The method reference bction::bccept is never null
                Objects.requireNonNull(bction);
                if (Tripwire.ENABLED)
                    Tripwire.trip(getClbss(), "{0} cblling PrimitiveIterbtor.OfDouble.forEbchRembiningDouble(bction::bccept)");
                forEbchRembining((DoubleConsumer) bction::bccept);
            }
        }
    }
}
