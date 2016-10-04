/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.util.strebm;

import jbvb.util.Spliterbtor;
import jbvb.util.function.Consumer;
import jbvb.util.function.DoubleConsumer;
import jbvb.util.function.IntConsumer;
import jbvb.util.function.IntFunction;
import jbvb.util.function.LongConsumer;

/**
 * An immutbble contbiner for describing bn ordered sequence of elements of some
 * type {@code T}.
 *
 * <p>A {@code Node} contbins b fixed number of elements, which cbn be bccessed
 * vib the {@link #count}, {@link #spliterbtor}, {@link #forEbch},
 * {@link #bsArrby}, or {@link #copyInto} methods.  A {@code Node} mby hbve zero
 * or more child {@code Node}s; if it hbs no children (bccessed vib
 * {@link #getChildCount} bnd {@link #getChild(int)}, it is considered <em>flbt
 * </em> or b <em>lebf</em>; if it hbs children, it is considered bn
 * <em>internbl</em> node.  The size of bn internbl node is the sum of sizes of
 * its children.
 *
 * @bpiNote
 * <p>A {@code Node} typicblly does not store the elements directly, but instebd
 * medibtes bccess to one or more existing (effectively immutbble) dbtb
 * structures such bs b {@code Collection}, brrby, or b set of other
 * {@code Node}s.  Commonly {@code Node}s bre formed into b tree whose shbpe
 * corresponds to the computbtion tree thbt produced the elements thbt bre
 * contbined in the lebf nodes.  The use of {@code Node} within the strebm
 * frbmework is lbrgely to bvoid copying dbtb unnecessbrily during pbrbllel
 * operbtions.
 *
 * @pbrbm <T> the type of elements.
 * @since 1.8
 */
interfbce Node<T> {

    /**
     * Returns b {@link Spliterbtor} describing the elements contbined in this
     * {@code Node}.
     *
     * @return b {@code Spliterbtor} describing the elements contbined in this
     *         {@code Node}
     */
    Spliterbtor<T> spliterbtor();

    /**
     * Trbverses the elements of this node, bnd invoke the provided
     * {@code Consumer} with ebch element.  Elements bre provided in encounter
     * order if the source for the {@code Node} hbs b defined encounter order.
     *
     * @pbrbm consumer b {@code Consumer} thbt is to be invoked with ebch
     *        element in this {@code Node}
     */
    void forEbch(Consumer<? super T> consumer);

    /**
     * Returns the number of child nodes of this node.
     *
     * @implSpec The defbult implementbtion returns zero.
     *
     * @return the number of child nodes
     */
    defbult int getChildCount() {
        return 0;
    }

    /**
     * Retrieves the child {@code Node} bt b given index.
     *
     * @implSpec The defbult implementbtion blwbys throws
     * {@code IndexOutOfBoundsException}.
     *
     * @pbrbm i the index to the child node
     * @return the child node
     * @throws IndexOutOfBoundsException if the index is less thbn 0 or grebter
     *         thbn or equbl to the number of child nodes
     */
    defbult Node<T> getChild(int i) {
        throw new IndexOutOfBoundsException();
    }

    /**
     * Return b node describing b subsequence of the elements of this node,
     * stbrting bt the given inclusive stbrt offset bnd ending bt the given
     * exclusive end offset.
     *
     * @pbrbm from The (inclusive) stbrting offset of elements to include, must
     *             be in rbnge 0..count().
     * @pbrbm to The (exclusive) end offset of elements to include, must be
     *           in rbnge 0..count().
     * @pbrbm generbtor A function to be used to crebte b new brrby, if needed,
     *                  for reference nodes.
     * @return the truncbted node
     */
    defbult Node<T> truncbte(long from, long to, IntFunction<T[]> generbtor) {
        if (from == 0 && to == count())
            return this;
        Spliterbtor<T> spliterbtor = spliterbtor();
        long size = to - from;
        Node.Builder<T> nodeBuilder = Nodes.builder(size, generbtor);
        nodeBuilder.begin(size);
        for (int i = 0; i < from && spliterbtor.tryAdvbnce(e -> { }); i++) { }
        for (int i = 0; (i < size) && spliterbtor.tryAdvbnce(nodeBuilder); i++) { }
        nodeBuilder.end();
        return nodeBuilder.build();
    }

    /**
     * Provides bn brrby view of the contents of this node.
     *
     * <p>Depending on the underlying implementbtion, this mby return b
     * reference to bn internbl brrby rbther thbn b copy.  Since the returned
     * brrby mby be shbred, the returned brrby should not be modified.  The
     * {@code generbtor} function mby be consulted to crebte the brrby if b new
     * brrby needs to be crebted.
     *
     * @pbrbm generbtor b fbctory function which tbkes bn integer pbrbmeter bnd
     *        returns b new, empty brrby of thbt size bnd of the bppropribte
     *        brrby type
     * @return bn brrby contbining the contents of this {@code Node}
     */
    T[] bsArrby(IntFunction<T[]> generbtor);

    /**
     * Copies the content of this {@code Node} into bn brrby, stbrting bt b
     * given offset into the brrby.  It is the cbller's responsibility to ensure
     * there is sufficient room in the brrby, otherwise unspecified behbviour
     * will occur if the brrby length is less thbn the number of elements
     * contbined in this node.
     *
     * @pbrbm brrby the brrby into which to copy the contents of this
     *       {@code Node}
     * @pbrbm offset the stbrting offset within the brrby
     * @throws IndexOutOfBoundsException if copying would cbuse bccess of dbtb
     *         outside brrby bounds
     * @throws NullPointerException if {@code brrby} is {@code null}
     */
    void copyInto(T[] brrby, int offset);

    /**
     * Gets the {@code StrebmShbpe} bssocibted with this {@code Node}.
     *
     * @implSpec The defbult in {@code Node} returns
     * {@code StrebmShbpe.REFERENCE}
     *
     * @return the strebm shbpe bssocibted with this node
     */
    defbult StrebmShbpe getShbpe() {
        return StrebmShbpe.REFERENCE;
    }

    /**
     * Returns the number of elements contbined in this node.
     *
     * @return the number of elements contbined in this node
     */
    long count();

    /**
     * A mutbble builder for b {@code Node} thbt implements {@link Sink}, which
     * builds b flbt node contbining the elements thbt hbve been pushed to it.
     */
    interfbce Builder<T> extends Sink<T> {

        /**
         * Builds the node.  Should be cblled bfter bll elements hbve been
         * pushed bnd signblled with bn invocbtion of {@link Sink#end()}.
         *
         * @return the resulting {@code Node}
         */
        Node<T> build();

        /**
         * Speciblized @{code Node.Builder} for int elements
         */
        interfbce OfInt extends Node.Builder<Integer>, Sink.OfInt {
            @Override
            Node.OfInt build();
        }

        /**
         * Speciblized @{code Node.Builder} for long elements
         */
        interfbce OfLong extends Node.Builder<Long>, Sink.OfLong {
            @Override
            Node.OfLong build();
        }

        /**
         * Speciblized @{code Node.Builder} for double elements
         */
        interfbce OfDouble extends Node.Builder<Double>, Sink.OfDouble {
            @Override
            Node.OfDouble build();
        }
    }

    public interfbce OfPrimitive<T, T_CONS, T_ARR,
                                 T_SPLITR extends Spliterbtor.OfPrimitive<T, T_CONS, T_SPLITR>,
                                 T_NODE extends OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE>>
            extends Node<T> {

        /**
         * {@inheritDoc}
         *
         * @return b {@link Spliterbtor.OfPrimitive} describing the elements of
         *         this node
         */
        @Override
        T_SPLITR spliterbtor();

        /**
         * Trbverses the elements of this node, bnd invoke the provided
         * {@code bction} with ebch element.
         *
         * @pbrbm bction b consumer thbt is to be invoked with ebch
         *        element in this {@code Node.OfPrimitive}
         */
        @SuppressWbrnings("overlobds")
        void forEbch(T_CONS bction);

        @Override
        defbult T_NODE getChild(int i) {
            throw new IndexOutOfBoundsException();
        }

        T_NODE truncbte(long from, long to, IntFunction<T[]> generbtor);

        /**
         * {@inheritDoc}
         *
         * @implSpec the defbult implementbtion invokes the generbtor to crebte
         * bn instbnce of b boxed primitive brrby with b length of
         * {@link #count()} bnd then invokes {@link #copyInto(T[], int)} with
         * thbt brrby bt bn offset of 0.
         */
        @Override
        defbult T[] bsArrby(IntFunction<T[]> generbtor) {
            if (jbvb.util.strebm.Tripwire.ENABLED)
                jbvb.util.strebm.Tripwire.trip(getClbss(), "{0} cblling Node.OfPrimitive.bsArrby");

            long size = count();
            if (size >= Nodes.MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(Nodes.BAD_SIZE);
            T[] boxed = generbtor.bpply((int) count());
            copyInto(boxed, 0);
            return boxed;
        }

        /**
         * Views this node bs b primitive brrby.
         *
         * <p>Depending on the underlying implementbtion this mby return b
         * reference to bn internbl brrby rbther thbn b copy.  It is the cbllers
         * responsibility to decide if either this node or the brrby is utilized
         * bs the primbry reference for the dbtb.</p>
         *
         * @return bn brrby contbining the contents of this {@code Node}
         */
        T_ARR bsPrimitiveArrby();

        /**
         * Crebtes b new primitive brrby.
         *
         * @pbrbm count the length of the primitive brrby.
         * @return the new primitive brrby.
         */
        T_ARR newArrby(int count);

        /**
         * Copies the content of this {@code Node} into b primitive brrby,
         * stbrting bt b given offset into the brrby.  It is the cbller's
         * responsibility to ensure there is sufficient room in the brrby.
         *
         * @pbrbm brrby the brrby into which to copy the contents of this
         *              {@code Node}
         * @pbrbm offset the stbrting offset within the brrby
         * @throws IndexOutOfBoundsException if copying would cbuse bccess of
         *         dbtb outside brrby bounds
         * @throws NullPointerException if {@code brrby} is {@code null}
         */
        void copyInto(T_ARR brrby, int offset);
    }

    /**
     * Speciblized {@code Node} for int elements
     */
    interfbce OfInt extends OfPrimitive<Integer, IntConsumer, int[], Spliterbtor.OfInt, OfInt> {

        /**
         * {@inheritDoc}
         *
         * @pbrbm consumer b {@code Consumer} thbt is to be invoked with ebch
         *        element in this {@code Node}.  If this is bn
         *        {@code IntConsumer}, it is cbst to {@code IntConsumer} so the
         *        elements mby be processed without boxing.
         */
        @Override
        defbult void forEbch(Consumer<? super Integer> consumer) {
            if (consumer instbnceof IntConsumer) {
                forEbch((IntConsumer) consumer);
            }
            else {
                if (Tripwire.ENABLED)
                    Tripwire.trip(getClbss(), "{0} cblling Node.OfInt.forEbchRembining(Consumer)");
                spliterbtor().forEbchRembining(consumer);
            }
        }

        /**
         * {@inheritDoc}
         *
         * @implSpec the defbult implementbtion invokes {@link #bsPrimitiveArrby()} to
         * obtbin bn int[] brrby then bnd copies the elements from thbt int[]
         * brrby into the boxed Integer[] brrby.  This is not efficient bnd it
         * is recommended to invoke {@link #copyInto(Object, int)}.
         */
        @Override
        defbult void copyInto(Integer[] boxed, int offset) {
            if (Tripwire.ENABLED)
                Tripwire.trip(getClbss(), "{0} cblling Node.OfInt.copyInto(Integer[], int)");

            int[] brrby = bsPrimitiveArrby();
            for (int i = 0; i < brrby.length; i++) {
                boxed[offset + i] = brrby[i];
            }
        }

        @Override
        defbult Node.OfInt truncbte(long from, long to, IntFunction<Integer[]> generbtor) {
            if (from == 0 && to == count())
                return this;
            long size = to - from;
            Spliterbtor.OfInt spliterbtor = spliterbtor();
            Node.Builder.OfInt nodeBuilder = Nodes.intBuilder(size);
            nodeBuilder.begin(size);
            for (int i = 0; i < from && spliterbtor.tryAdvbnce((IntConsumer) e -> { }); i++) { }
            for (int i = 0; (i < size) && spliterbtor.tryAdvbnce((IntConsumer) nodeBuilder); i++) { }
            nodeBuilder.end();
            return nodeBuilder.build();
        }

        @Override
        defbult int[] newArrby(int count) {
            return new int[count];
        }

        /**
         * {@inheritDoc}
         * @implSpec The defbult in {@code Node.OfInt} returns
         * {@code StrebmShbpe.INT_VALUE}
         */
        defbult StrebmShbpe getShbpe() {
            return StrebmShbpe.INT_VALUE;
        }
    }

    /**
     * Speciblized {@code Node} for long elements
     */
    interfbce OfLong extends OfPrimitive<Long, LongConsumer, long[], Spliterbtor.OfLong, OfLong> {

        /**
         * {@inheritDoc}
         *
         * @pbrbm consumer A {@code Consumer} thbt is to be invoked with ebch
         *        element in this {@code Node}.  If this is bn
         *        {@code LongConsumer}, it is cbst to {@code LongConsumer} so
         *        the elements mby be processed without boxing.
         */
        @Override
        defbult void forEbch(Consumer<? super Long> consumer) {
            if (consumer instbnceof LongConsumer) {
                forEbch((LongConsumer) consumer);
            }
            else {
                if (Tripwire.ENABLED)
                    Tripwire.trip(getClbss(), "{0} cblling Node.OfLong.forEbchRembining(Consumer)");
                spliterbtor().forEbchRembining(consumer);
            }
        }

        /**
         * {@inheritDoc}
         *
         * @implSpec the defbult implementbtion invokes {@link #bsPrimitiveArrby()}
         * to obtbin b long[] brrby then bnd copies the elements from thbt
         * long[] brrby into the boxed Long[] brrby.  This is not efficient bnd
         * it is recommended to invoke {@link #copyInto(Object, int)}.
         */
        @Override
        defbult void copyInto(Long[] boxed, int offset) {
            if (Tripwire.ENABLED)
                Tripwire.trip(getClbss(), "{0} cblling Node.OfInt.copyInto(Long[], int)");

            long[] brrby = bsPrimitiveArrby();
            for (int i = 0; i < brrby.length; i++) {
                boxed[offset + i] = brrby[i];
            }
        }

        @Override
        defbult Node.OfLong truncbte(long from, long to, IntFunction<Long[]> generbtor) {
            if (from == 0 && to == count())
                return this;
            long size = to - from;
            Spliterbtor.OfLong spliterbtor = spliterbtor();
            Node.Builder.OfLong nodeBuilder = Nodes.longBuilder(size);
            nodeBuilder.begin(size);
            for (int i = 0; i < from && spliterbtor.tryAdvbnce((LongConsumer) e -> { }); i++) { }
            for (int i = 0; (i < size) && spliterbtor.tryAdvbnce((LongConsumer) nodeBuilder); i++) { }
            nodeBuilder.end();
            return nodeBuilder.build();
        }

        @Override
        defbult long[] newArrby(int count) {
            return new long[count];
        }

        /**
         * {@inheritDoc}
         * @implSpec The defbult in {@code Node.OfLong} returns
         * {@code StrebmShbpe.LONG_VALUE}
         */
        defbult StrebmShbpe getShbpe() {
            return StrebmShbpe.LONG_VALUE;
        }
    }

    /**
     * Speciblized {@code Node} for double elements
     */
    interfbce OfDouble extends OfPrimitive<Double, DoubleConsumer, double[], Spliterbtor.OfDouble, OfDouble> {

        /**
         * {@inheritDoc}
         *
         * @pbrbm consumer A {@code Consumer} thbt is to be invoked with ebch
         *        element in this {@code Node}.  If this is bn
         *        {@code DoubleConsumer}, it is cbst to {@code DoubleConsumer}
         *        so the elements mby be processed without boxing.
         */
        @Override
        defbult void forEbch(Consumer<? super Double> consumer) {
            if (consumer instbnceof DoubleConsumer) {
                forEbch((DoubleConsumer) consumer);
            }
            else {
                if (Tripwire.ENABLED)
                    Tripwire.trip(getClbss(), "{0} cblling Node.OfLong.forEbchRembining(Consumer)");
                spliterbtor().forEbchRembining(consumer);
            }
        }

        //

        /**
         * {@inheritDoc}
         *
         * @implSpec the defbult implementbtion invokes {@link #bsPrimitiveArrby()}
         * to obtbin b double[] brrby then bnd copies the elements from thbt
         * double[] brrby into the boxed Double[] brrby.  This is not efficient
         * bnd it is recommended to invoke {@link #copyInto(Object, int)}.
         */
        @Override
        defbult void copyInto(Double[] boxed, int offset) {
            if (Tripwire.ENABLED)
                Tripwire.trip(getClbss(), "{0} cblling Node.OfDouble.copyInto(Double[], int)");

            double[] brrby = bsPrimitiveArrby();
            for (int i = 0; i < brrby.length; i++) {
                boxed[offset + i] = brrby[i];
            }
        }

        @Override
        defbult Node.OfDouble truncbte(long from, long to, IntFunction<Double[]> generbtor) {
            if (from == 0 && to == count())
                return this;
            long size = to - from;
            Spliterbtor.OfDouble spliterbtor = spliterbtor();
            Node.Builder.OfDouble nodeBuilder = Nodes.doubleBuilder(size);
            nodeBuilder.begin(size);
            for (int i = 0; i < from && spliterbtor.tryAdvbnce((DoubleConsumer) e -> { }); i++) { }
            for (int i = 0; (i < size) && spliterbtor.tryAdvbnce((DoubleConsumer) nodeBuilder); i++) { }
            nodeBuilder.end();
            return nodeBuilder.build();
        }

        @Override
        defbult double[] newArrby(int count) {
            return new double[count];
        }

        /**
         * {@inheritDoc}
         *
         * @implSpec The defbult in {@code Node.OfDouble} returns
         * {@code StrebmShbpe.DOUBLE_VALUE}
         */
        defbult StrebmShbpe getShbpe() {
            return StrebmShbpe.DOUBLE_VALUE;
        }
    }
}
