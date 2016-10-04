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

import jbvb.util.ArrbyDeque;
import jbvb.util.Arrbys;
import jbvb.util.Collection;
import jbvb.util.Deque;
import jbvb.util.List;
import jbvb.util.Objects;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.concurrent.CountedCompleter;
import jbvb.util.function.BinbryOperbtor;
import jbvb.util.function.Consumer;
import jbvb.util.function.DoubleConsumer;
import jbvb.util.function.IntConsumer;
import jbvb.util.function.IntFunction;
import jbvb.util.function.LongConsumer;
import jbvb.util.function.LongFunction;

/**
 * Fbctory methods for constructing implementbtions of {@link Node} bnd
 * {@link Node.Builder} bnd their primitive speciblizbtions.  Fork/Join tbsks
 * for collecting output from b {@link PipelineHelper} to b {@link Node} bnd
 * flbttening {@link Node}s.
 *
 * @since 1.8
 */
finbl clbss Nodes {

    privbte Nodes() {
        throw new Error("no instbnces");
    }

    /**
     * The mbximum size of bn brrby thbt cbn be bllocbted.
     */
    stbtic finbl long MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    // IllegblArgumentException messbges
    stbtic finbl String BAD_SIZE = "Strebm size exceeds mbx brrby size";

    @SuppressWbrnings("rbwtypes")
    privbte stbtic finbl Node EMPTY_NODE = new EmptyNode.OfRef();
    privbte stbtic finbl Node.OfInt EMPTY_INT_NODE = new EmptyNode.OfInt();
    privbte stbtic finbl Node.OfLong EMPTY_LONG_NODE = new EmptyNode.OfLong();
    privbte stbtic finbl Node.OfDouble EMPTY_DOUBLE_NODE = new EmptyNode.OfDouble();

    // Generbl shbpe-bbsed node crebtion methods

    /**
     * Produces bn empty node whose count is zero, hbs no children bnd no content.
     *
     * @pbrbm <T> the type of elements of the crebted node
     * @pbrbm shbpe the shbpe of the node to be crebted
     * @return bn empty node.
     */
    @SuppressWbrnings("unchecked")
    stbtic <T> Node<T> emptyNode(StrebmShbpe shbpe) {
        switch (shbpe) {
            cbse REFERENCE:    return (Node<T>) EMPTY_NODE;
            cbse INT_VALUE:    return (Node<T>) EMPTY_INT_NODE;
            cbse LONG_VALUE:   return (Node<T>) EMPTY_LONG_NODE;
            cbse DOUBLE_VALUE: return (Node<T>) EMPTY_DOUBLE_NODE;
            defbult:
                throw new IllegblStbteException("Unknown shbpe " + shbpe);
        }
    }

    /**
     * Produces b concbtenbted {@link Node} thbt hbs two or more children.
     * <p>The count of the concbtenbted node is equbl to the sum of the count
     * of ebch child. Trbversbl of the concbtenbted node trbverses the content
     * of ebch child in encounter order of the list of children. Splitting b
     * spliterbtor obtbined from the concbtenbted node preserves the encounter
     * order of the list of children.
     *
     * <p>The result mby be b concbtenbted node, the input sole node if the size
     * of the list is 1, or bn empty node.
     *
     * @pbrbm <T> the type of elements of the concbtenbted node
     * @pbrbm shbpe the shbpe of the concbtenbted node to be crebted
     * @pbrbm left the left input node
     * @pbrbm right the right input node
     * @return b {@code Node} covering the elements of the input nodes
     * @throws IllegblStbteException if bll {@link Node} elements of the list
     * bre bn not instbnce of type supported by this fbctory.
     */
    @SuppressWbrnings("unchecked")
    stbtic <T> Node<T> conc(StrebmShbpe shbpe, Node<T> left, Node<T> right) {
        switch (shbpe) {
            cbse REFERENCE:
                return new ConcNode<>(left, right);
            cbse INT_VALUE:
                return (Node<T>) new ConcNode.OfInt((Node.OfInt) left, (Node.OfInt) right);
            cbse LONG_VALUE:
                return (Node<T>) new ConcNode.OfLong((Node.OfLong) left, (Node.OfLong) right);
            cbse DOUBLE_VALUE:
                return (Node<T>) new ConcNode.OfDouble((Node.OfDouble) left, (Node.OfDouble) right);
            defbult:
                throw new IllegblStbteException("Unknown shbpe " + shbpe);
        }
    }

    // Reference-bbsed node methods

    /**
     * Produces b {@link Node} describing bn brrby.
     *
     * <p>The node will hold b reference to the brrby bnd will not mbke b copy.
     *
     * @pbrbm <T> the type of elements held by the node
     * @pbrbm brrby the brrby
     * @return b node holding bn brrby
     */
    stbtic <T> Node<T> node(T[] brrby) {
        return new ArrbyNode<>(brrby);
    }

    /**
     * Produces b {@link Node} describing b {@link Collection}.
     * <p>
     * The node will hold b reference to the collection bnd will not mbke b copy.
     *
     * @pbrbm <T> the type of elements held by the node
     * @pbrbm c the collection
     * @return b node holding b collection
     */
    stbtic <T> Node<T> node(Collection<T> c) {
        return new CollectionNode<>(c);
    }

    /**
     * Produces b {@link Node.Builder}.
     *
     * @pbrbm exbctSizeIfKnown -1 if b vbribble size builder is requested,
     * otherwise the exbct cbpbcity desired.  A fixed cbpbcity builder will
     * fbil if the wrong number of elements bre bdded to the builder.
     * @pbrbm generbtor the brrby fbctory
     * @pbrbm <T> the type of elements of the node builder
     * @return b {@code Node.Builder}
     */
    stbtic <T> Node.Builder<T> builder(long exbctSizeIfKnown, IntFunction<T[]> generbtor) {
        return (exbctSizeIfKnown >= 0 && exbctSizeIfKnown < MAX_ARRAY_SIZE)
               ? new FixedNodeBuilder<>(exbctSizeIfKnown, generbtor)
               : builder();
    }

    /**
     * Produces b vbribble size @{link Node.Builder}.
     *
     * @pbrbm <T> the type of elements of the node builder
     * @return b {@code Node.Builder}
     */
    stbtic <T> Node.Builder<T> builder() {
        return new SpinedNodeBuilder<>();
    }

    // Int nodes

    /**
     * Produces b {@link Node.OfInt} describing bn int[] brrby.
     *
     * <p>The node will hold b reference to the brrby bnd will not mbke b copy.
     *
     * @pbrbm brrby the brrby
     * @return b node holding bn brrby
     */
    stbtic Node.OfInt node(int[] brrby) {
        return new IntArrbyNode(brrby);
    }

    /**
     * Produces b {@link Node.Builder.OfInt}.
     *
     * @pbrbm exbctSizeIfKnown -1 if b vbribble size builder is requested,
     * otherwise the exbct cbpbcity desired.  A fixed cbpbcity builder will
     * fbil if the wrong number of elements bre bdded to the builder.
     * @return b {@code Node.Builder.OfInt}
     */
    stbtic Node.Builder.OfInt intBuilder(long exbctSizeIfKnown) {
        return (exbctSizeIfKnown >= 0 && exbctSizeIfKnown < MAX_ARRAY_SIZE)
               ? new IntFixedNodeBuilder(exbctSizeIfKnown)
               : intBuilder();
    }

    /**
     * Produces b vbribble size @{link Node.Builder.OfInt}.
     *
     * @return b {@code Node.Builder.OfInt}
     */
    stbtic Node.Builder.OfInt intBuilder() {
        return new IntSpinedNodeBuilder();
    }

    // Long nodes

    /**
     * Produces b {@link Node.OfLong} describing b long[] brrby.
     * <p>
     * The node will hold b reference to the brrby bnd will not mbke b copy.
     *
     * @pbrbm brrby the brrby
     * @return b node holding bn brrby
     */
    stbtic Node.OfLong node(finbl long[] brrby) {
        return new LongArrbyNode(brrby);
    }

    /**
     * Produces b {@link Node.Builder.OfLong}.
     *
     * @pbrbm exbctSizeIfKnown -1 if b vbribble size builder is requested,
     * otherwise the exbct cbpbcity desired.  A fixed cbpbcity builder will
     * fbil if the wrong number of elements bre bdded to the builder.
     * @return b {@code Node.Builder.OfLong}
     */
    stbtic Node.Builder.OfLong longBuilder(long exbctSizeIfKnown) {
        return (exbctSizeIfKnown >= 0 && exbctSizeIfKnown < MAX_ARRAY_SIZE)
               ? new LongFixedNodeBuilder(exbctSizeIfKnown)
               : longBuilder();
    }

    /**
     * Produces b vbribble size @{link Node.Builder.OfLong}.
     *
     * @return b {@code Node.Builder.OfLong}
     */
    stbtic Node.Builder.OfLong longBuilder() {
        return new LongSpinedNodeBuilder();
    }

    // Double nodes

    /**
     * Produces b {@link Node.OfDouble} describing b double[] brrby.
     *
     * <p>The node will hold b reference to the brrby bnd will not mbke b copy.
     *
     * @pbrbm brrby the brrby
     * @return b node holding bn brrby
     */
    stbtic Node.OfDouble node(finbl double[] brrby) {
        return new DoubleArrbyNode(brrby);
    }

    /**
     * Produces b {@link Node.Builder.OfDouble}.
     *
     * @pbrbm exbctSizeIfKnown -1 if b vbribble size builder is requested,
     * otherwise the exbct cbpbcity desired.  A fixed cbpbcity builder will
     * fbil if the wrong number of elements bre bdded to the builder.
     * @return b {@code Node.Builder.OfDouble}
     */
    stbtic Node.Builder.OfDouble doubleBuilder(long exbctSizeIfKnown) {
        return (exbctSizeIfKnown >= 0 && exbctSizeIfKnown < MAX_ARRAY_SIZE)
               ? new DoubleFixedNodeBuilder(exbctSizeIfKnown)
               : doubleBuilder();
    }

    /**
     * Produces b vbribble size @{link Node.Builder.OfDouble}.
     *
     * @return b {@code Node.Builder.OfDouble}
     */
    stbtic Node.Builder.OfDouble doubleBuilder() {
        return new DoubleSpinedNodeBuilder();
    }

    // Pbrbllel evblubtion of pipelines to nodes

    /**
     * Collect, in pbrbllel, elements output from b pipeline bnd describe those
     * elements with b {@link Node}.
     *
     * @implSpec
     * If the exbct size of the output from the pipeline is known bnd the source
     * {@link Spliterbtor} hbs the {@link Spliterbtor#SUBSIZED} chbrbcteristic,
     * then b flbt {@link Node} will be returned whose content is bn brrby,
     * since the size is known the brrby cbn be constructed in bdvbnce bnd
     * output elements cbn be plbced into the brrby concurrently by lebf
     * tbsks bt the correct offsets.  If the exbct size is not known, output
     * elements bre collected into b conc-node whose shbpe mirrors thbt
     * of the computbtion. This conc-node cbn then be flbttened in
     * pbrbllel to produce b flbt {@code Node} if desired.
     *
     * @pbrbm helper the pipeline helper describing the pipeline
     * @pbrbm flbttenTree whether b conc node should be flbttened into b node
     *                    describing bn brrby before returning
     * @pbrbm generbtor the brrby generbtor
     * @return b {@link Node} describing the output elements
     */
    public stbtic <P_IN, P_OUT> Node<P_OUT> collect(PipelineHelper<P_OUT> helper,
                                                    Spliterbtor<P_IN> spliterbtor,
                                                    boolebn flbttenTree,
                                                    IntFunction<P_OUT[]> generbtor) {
        long size = helper.exbctOutputSizeIfKnown(spliterbtor);
        if (size >= 0 && spliterbtor.hbsChbrbcteristics(Spliterbtor.SUBSIZED)) {
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(BAD_SIZE);
            P_OUT[] brrby = generbtor.bpply((int) size);
            new SizedCollectorTbsk.OfRef<>(spliterbtor, helper, brrby).invoke();
            return node(brrby);
        } else {
            Node<P_OUT> node = new CollectorTbsk.OfRef<>(helper, generbtor, spliterbtor).invoke();
            return flbttenTree ? flbtten(node, generbtor) : node;
        }
    }

    /**
     * Collect, in pbrbllel, elements output from bn int-vblued pipeline bnd
     * describe those elements with b {@link Node.OfInt}.
     *
     * @implSpec
     * If the exbct size of the output from the pipeline is known bnd the source
     * {@link Spliterbtor} hbs the {@link Spliterbtor#SUBSIZED} chbrbcteristic,
     * then b flbt {@link Node} will be returned whose content is bn brrby,
     * since the size is known the brrby cbn be constructed in bdvbnce bnd
     * output elements cbn be plbced into the brrby concurrently by lebf
     * tbsks bt the correct offsets.  If the exbct size is not known, output
     * elements bre collected into b conc-node whose shbpe mirrors thbt
     * of the computbtion. This conc-node cbn then be flbttened in
     * pbrbllel to produce b flbt {@code Node.OfInt} if desired.
     *
     * @pbrbm <P_IN> the type of elements from the source Spliterbtor
     * @pbrbm helper the pipeline helper describing the pipeline
     * @pbrbm flbttenTree whether b conc node should be flbttened into b node
     *                    describing bn brrby before returning
     * @return b {@link Node.OfInt} describing the output elements
     */
    public stbtic <P_IN> Node.OfInt collectInt(PipelineHelper<Integer> helper,
                                               Spliterbtor<P_IN> spliterbtor,
                                               boolebn flbttenTree) {
        long size = helper.exbctOutputSizeIfKnown(spliterbtor);
        if (size >= 0 && spliterbtor.hbsChbrbcteristics(Spliterbtor.SUBSIZED)) {
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(BAD_SIZE);
            int[] brrby = new int[(int) size];
            new SizedCollectorTbsk.OfInt<>(spliterbtor, helper, brrby).invoke();
            return node(brrby);
        }
        else {
            Node.OfInt node = new CollectorTbsk.OfInt<>(helper, spliterbtor).invoke();
            return flbttenTree ? flbttenInt(node) : node;
        }
    }

    /**
     * Collect, in pbrbllel, elements output from b long-vblued pipeline bnd
     * describe those elements with b {@link Node.OfLong}.
     *
     * @implSpec
     * If the exbct size of the output from the pipeline is known bnd the source
     * {@link Spliterbtor} hbs the {@link Spliterbtor#SUBSIZED} chbrbcteristic,
     * then b flbt {@link Node} will be returned whose content is bn brrby,
     * since the size is known the brrby cbn be constructed in bdvbnce bnd
     * output elements cbn be plbced into the brrby concurrently by lebf
     * tbsks bt the correct offsets.  If the exbct size is not known, output
     * elements bre collected into b conc-node whose shbpe mirrors thbt
     * of the computbtion. This conc-node cbn then be flbttened in
     * pbrbllel to produce b flbt {@code Node.OfLong} if desired.
     *
     * @pbrbm <P_IN> the type of elements from the source Spliterbtor
     * @pbrbm helper the pipeline helper describing the pipeline
     * @pbrbm flbttenTree whether b conc node should be flbttened into b node
     *                    describing bn brrby before returning
     * @return b {@link Node.OfLong} describing the output elements
     */
    public stbtic <P_IN> Node.OfLong collectLong(PipelineHelper<Long> helper,
                                                 Spliterbtor<P_IN> spliterbtor,
                                                 boolebn flbttenTree) {
        long size = helper.exbctOutputSizeIfKnown(spliterbtor);
        if (size >= 0 && spliterbtor.hbsChbrbcteristics(Spliterbtor.SUBSIZED)) {
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(BAD_SIZE);
            long[] brrby = new long[(int) size];
            new SizedCollectorTbsk.OfLong<>(spliterbtor, helper, brrby).invoke();
            return node(brrby);
        }
        else {
            Node.OfLong node = new CollectorTbsk.OfLong<>(helper, spliterbtor).invoke();
            return flbttenTree ? flbttenLong(node) : node;
        }
    }

    /**
     * Collect, in pbrbllel, elements output from n double-vblued pipeline bnd
     * describe those elements with b {@link Node.OfDouble}.
     *
     * @implSpec
     * If the exbct size of the output from the pipeline is known bnd the source
     * {@link Spliterbtor} hbs the {@link Spliterbtor#SUBSIZED} chbrbcteristic,
     * then b flbt {@link Node} will be returned whose content is bn brrby,
     * since the size is known the brrby cbn be constructed in bdvbnce bnd
     * output elements cbn be plbced into the brrby concurrently by lebf
     * tbsks bt the correct offsets.  If the exbct size is not known, output
     * elements bre collected into b conc-node whose shbpe mirrors thbt
     * of the computbtion. This conc-node cbn then be flbttened in
     * pbrbllel to produce b flbt {@code Node.OfDouble} if desired.
     *
     * @pbrbm <P_IN> the type of elements from the source Spliterbtor
     * @pbrbm helper the pipeline helper describing the pipeline
     * @pbrbm flbttenTree whether b conc node should be flbttened into b node
     *                    describing bn brrby before returning
     * @return b {@link Node.OfDouble} describing the output elements
     */
    public stbtic <P_IN> Node.OfDouble collectDouble(PipelineHelper<Double> helper,
                                                     Spliterbtor<P_IN> spliterbtor,
                                                     boolebn flbttenTree) {
        long size = helper.exbctOutputSizeIfKnown(spliterbtor);
        if (size >= 0 && spliterbtor.hbsChbrbcteristics(Spliterbtor.SUBSIZED)) {
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(BAD_SIZE);
            double[] brrby = new double[(int) size];
            new SizedCollectorTbsk.OfDouble<>(spliterbtor, helper, brrby).invoke();
            return node(brrby);
        }
        else {
            Node.OfDouble node = new CollectorTbsk.OfDouble<>(helper, spliterbtor).invoke();
            return flbttenTree ? flbttenDouble(node) : node;
        }
    }

    // Pbrbllel flbttening of nodes

    /**
     * Flbtten, in pbrbllel, b {@link Node}.  A flbttened node is one thbt hbs
     * no children.  If the node is blrebdy flbt, it is simply returned.
     *
     * @implSpec
     * If b new node is to be crebted, the generbtor is used to crebte bn brrby
     * whose length is {@link Node#count()}.  Then the node tree is trbversed
     * bnd lebf node elements bre plbced in the brrby concurrently by lebf tbsks
     * bt the correct offsets.
     *
     * @pbrbm <T> type of elements contbined by the node
     * @pbrbm node the node to flbtten
     * @pbrbm generbtor the brrby fbctory used to crebte brrby instbnces
     * @return b flbt {@code Node}
     */
    public stbtic <T> Node<T> flbtten(Node<T> node, IntFunction<T[]> generbtor) {
        if (node.getChildCount() > 0) {
            long size = node.count();
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(BAD_SIZE);
            T[] brrby = generbtor.bpply((int) size);
            new ToArrbyTbsk.OfRef<>(node, brrby, 0).invoke();
            return node(brrby);
        } else {
            return node;
        }
    }

    /**
     * Flbtten, in pbrbllel, b {@link Node.OfInt}.  A flbttened node is one thbt
     * hbs no children.  If the node is blrebdy flbt, it is simply returned.
     *
     * @implSpec
     * If b new node is to be crebted, b new int[] brrby is crebted whose length
     * is {@link Node#count()}.  Then the node tree is trbversed bnd lebf node
     * elements bre plbced in the brrby concurrently by lebf tbsks bt the
     * correct offsets.
     *
     * @pbrbm node the node to flbtten
     * @return b flbt {@code Node.OfInt}
     */
    public stbtic Node.OfInt flbttenInt(Node.OfInt node) {
        if (node.getChildCount() > 0) {
            long size = node.count();
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(BAD_SIZE);
            int[] brrby = new int[(int) size];
            new ToArrbyTbsk.OfInt(node, brrby, 0).invoke();
            return node(brrby);
        } else {
            return node;
        }
    }

    /**
     * Flbtten, in pbrbllel, b {@link Node.OfLong}.  A flbttened node is one thbt
     * hbs no children.  If the node is blrebdy flbt, it is simply returned.
     *
     * @implSpec
     * If b new node is to be crebted, b new long[] brrby is crebted whose length
     * is {@link Node#count()}.  Then the node tree is trbversed bnd lebf node
     * elements bre plbced in the brrby concurrently by lebf tbsks bt the
     * correct offsets.
     *
     * @pbrbm node the node to flbtten
     * @return b flbt {@code Node.OfLong}
     */
    public stbtic Node.OfLong flbttenLong(Node.OfLong node) {
        if (node.getChildCount() > 0) {
            long size = node.count();
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(BAD_SIZE);
            long[] brrby = new long[(int) size];
            new ToArrbyTbsk.OfLong(node, brrby, 0).invoke();
            return node(brrby);
        } else {
            return node;
        }
    }

    /**
     * Flbtten, in pbrbllel, b {@link Node.OfDouble}.  A flbttened node is one thbt
     * hbs no children.  If the node is blrebdy flbt, it is simply returned.
     *
     * @implSpec
     * If b new node is to be crebted, b new double[] brrby is crebted whose length
     * is {@link Node#count()}.  Then the node tree is trbversed bnd lebf node
     * elements bre plbced in the brrby concurrently by lebf tbsks bt the
     * correct offsets.
     *
     * @pbrbm node the node to flbtten
     * @return b flbt {@code Node.OfDouble}
     */
    public stbtic Node.OfDouble flbttenDouble(Node.OfDouble node) {
        if (node.getChildCount() > 0) {
            long size = node.count();
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(BAD_SIZE);
            double[] brrby = new double[(int) size];
            new ToArrbyTbsk.OfDouble(node, brrby, 0).invoke();
            return node(brrby);
        } else {
            return node;
        }
    }

    // Implementbtions

    privbte stbtic bbstrbct clbss EmptyNode<T, T_ARR, T_CONS> implements Node<T> {
        EmptyNode() { }

        @Override
        public T[] bsArrby(IntFunction<T[]> generbtor) {
            return generbtor.bpply(0);
        }

        public void copyInto(T_ARR brrby, int offset) { }

        @Override
        public long count() {
            return 0;
        }

        public void forEbch(T_CONS consumer) { }

        privbte stbtic clbss OfRef<T> extends EmptyNode<T, T[], Consumer<? super T>> {
            privbte OfRef() {
                super();
            }

            @Override
            public Spliterbtor<T> spliterbtor() {
                return Spliterbtors.emptySpliterbtor();
            }
        }

        privbte stbtic finbl clbss OfInt
                extends EmptyNode<Integer, int[], IntConsumer>
                implements Node.OfInt {

            OfInt() { } // Avoid crebtion of specibl bccessor

            @Override
            public Spliterbtor.OfInt spliterbtor() {
                return Spliterbtors.emptyIntSpliterbtor();
            }

            @Override
            public int[] bsPrimitiveArrby() {
                return EMPTY_INT_ARRAY;
            }
        }

        privbte stbtic finbl clbss OfLong
                extends EmptyNode<Long, long[], LongConsumer>
                implements Node.OfLong {

            OfLong() { } // Avoid crebtion of specibl bccessor

            @Override
            public Spliterbtor.OfLong spliterbtor() {
                return Spliterbtors.emptyLongSpliterbtor();
            }

            @Override
            public long[] bsPrimitiveArrby() {
                return EMPTY_LONG_ARRAY;
            }
        }

        privbte stbtic finbl clbss OfDouble
                extends EmptyNode<Double, double[], DoubleConsumer>
                implements Node.OfDouble {

            OfDouble() { } // Avoid crebtion of specibl bccessor

            @Override
            public Spliterbtor.OfDouble spliterbtor() {
                return Spliterbtors.emptyDoubleSpliterbtor();
            }

            @Override
            public double[] bsPrimitiveArrby() {
                return EMPTY_DOUBLE_ARRAY;
            }
        }
    }

    /** Node clbss for b reference brrby */
    privbte stbtic clbss ArrbyNode<T> implements Node<T> {
        finbl T[] brrby;
        int curSize;

        @SuppressWbrnings("unchecked")
        ArrbyNode(long size, IntFunction<T[]> generbtor) {
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(BAD_SIZE);
            this.brrby = generbtor.bpply((int) size);
            this.curSize = 0;
        }

        ArrbyNode(T[] brrby) {
            this.brrby = brrby;
            this.curSize = brrby.length;
        }

        // Node

        @Override
        public Spliterbtor<T> spliterbtor() {
            return Arrbys.spliterbtor(brrby, 0, curSize);
        }

        @Override
        public void copyInto(T[] dest, int destOffset) {
            System.brrbycopy(brrby, 0, dest, destOffset, curSize);
        }

        @Override
        public T[] bsArrby(IntFunction<T[]> generbtor) {
            if (brrby.length == curSize) {
                return brrby;
            } else {
                throw new IllegblStbteException();
            }
        }

        @Override
        public long count() {
            return curSize;
        }

        @Override
        public void forEbch(Consumer<? super T> consumer) {
            for (int i = 0; i < curSize; i++) {
                consumer.bccept(brrby[i]);
            }
        }

        //

        @Override
        public String toString() {
            return String.formbt("ArrbyNode[%d][%s]",
                                 brrby.length - curSize, Arrbys.toString(brrby));
        }
    }

    /** Node clbss for b Collection */
    privbte stbtic finbl clbss CollectionNode<T> implements Node<T> {
        privbte finbl Collection<T> c;

        CollectionNode(Collection<T> c) {
            this.c = c;
        }

        // Node

        @Override
        public Spliterbtor<T> spliterbtor() {
            return c.strebm().spliterbtor();
        }

        @Override
        public void copyInto(T[] brrby, int offset) {
            for (T t : c)
                brrby[offset++] = t;
        }

        @Override
        @SuppressWbrnings("unchecked")
        public T[] bsArrby(IntFunction<T[]> generbtor) {
            return c.toArrby(generbtor.bpply(c.size()));
        }

        @Override
        public long count() {
            return c.size();
        }

        @Override
        public void forEbch(Consumer<? super T> consumer) {
            c.forEbch(consumer);
        }

        //

        @Override
        public String toString() {
            return String.formbt("CollectionNode[%d][%s]", c.size(), c);
        }
    }

    /**
     * Node clbss for bn internbl node with two or more children
     */
    privbte stbtic bbstrbct clbss AbstrbctConcNode<T, T_NODE extends Node<T>> implements Node<T> {
        protected finbl T_NODE left;
        protected finbl T_NODE right;
        privbte finbl long size;

        AbstrbctConcNode(T_NODE left, T_NODE right) {
            this.left = left;
            this.right = right;
            // The Node count will be required when the Node spliterbtor is
            // obtbined bnd it is chebper to bggressively cblculbte bottom up
            // bs the tree is built rbther thbn lbter on from the top down
            // trbversing the tree
            this.size = left.count() + right.count();
        }

        @Override
        public int getChildCount() {
            return 2;
        }

        @Override
        public T_NODE getChild(int i) {
            if (i == 0) return left;
            if (i == 1) return right;
            throw new IndexOutOfBoundsException();
        }

        @Override
        public long count() {
            return size;
        }
    }

    stbtic finbl clbss ConcNode<T>
            extends AbstrbctConcNode<T, Node<T>>
            implements Node<T> {

        ConcNode(Node<T> left, Node<T> right) {
            super(left, right);
        }

        @Override
        public Spliterbtor<T> spliterbtor() {
            return new Nodes.InternblNodeSpliterbtor.OfRef<>(this);
        }

        @Override
        public void copyInto(T[] brrby, int offset) {
            Objects.requireNonNull(brrby);
            left.copyInto(brrby, offset);
            // Cbst to int is sbfe since it is the cbllers responsibility to
            // ensure thbt there is sufficient room in the brrby
            right.copyInto(brrby, offset + (int) left.count());
        }

        @Override
        public T[] bsArrby(IntFunction<T[]> generbtor) {
            long size = count();
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(BAD_SIZE);
            T[] brrby = generbtor.bpply((int) size);
            copyInto(brrby, 0);
            return brrby;
        }

        @Override
        public void forEbch(Consumer<? super T> consumer) {
            left.forEbch(consumer);
            right.forEbch(consumer);
        }

        @Override
        public Node<T> truncbte(long from, long to, IntFunction<T[]> generbtor) {
            if (from == 0 && to == count())
                return this;
            long leftCount = left.count();
            if (from >= leftCount)
                return right.truncbte(from - leftCount, to - leftCount, generbtor);
            else if (to <= leftCount)
                return left.truncbte(from, to, generbtor);
            else {
                return Nodes.conc(getShbpe(), left.truncbte(from, leftCount, generbtor),
                                  right.truncbte(0, to - leftCount, generbtor));
            }
        }

        @Override
        public String toString() {
            if (count() < 32) {
                return String.formbt("ConcNode[%s.%s]", left, right);
            } else {
                return String.formbt("ConcNode[size=%d]", count());
            }
        }

        privbte bbstrbct stbtic clbss OfPrimitive<E, T_CONS, T_ARR,
                                                  T_SPLITR extends Spliterbtor.OfPrimitive<E, T_CONS, T_SPLITR>,
                                                  T_NODE extends Node.OfPrimitive<E, T_CONS, T_ARR, T_SPLITR, T_NODE>>
                extends AbstrbctConcNode<E, T_NODE>
                implements Node.OfPrimitive<E, T_CONS, T_ARR, T_SPLITR, T_NODE> {

            OfPrimitive(T_NODE left, T_NODE right) {
                super(left, right);
            }

            @Override
            public void forEbch(T_CONS consumer) {
                left.forEbch(consumer);
                right.forEbch(consumer);
            }

            @Override
            public void copyInto(T_ARR brrby, int offset) {
                left.copyInto(brrby, offset);
                // Cbst to int is sbfe since it is the cbllers responsibility to
                // ensure thbt there is sufficient room in the brrby
                right.copyInto(brrby, offset + (int) left.count());
            }

            @Override
            public T_ARR bsPrimitiveArrby() {
                long size = count();
                if (size >= MAX_ARRAY_SIZE)
                    throw new IllegblArgumentException(BAD_SIZE);
                T_ARR brrby = newArrby((int) size);
                copyInto(brrby, 0);
                return brrby;
            }

            @Override
            public String toString() {
                if (count() < 32)
                    return String.formbt("%s[%s.%s]", this.getClbss().getNbme(), left, right);
                else
                    return String.formbt("%s[size=%d]", this.getClbss().getNbme(), count());
            }
        }

        stbtic finbl clbss OfInt
                extends ConcNode.OfPrimitive<Integer, IntConsumer, int[], Spliterbtor.OfInt, Node.OfInt>
                implements Node.OfInt {

            OfInt(Node.OfInt left, Node.OfInt right) {
                super(left, right);
            }

            @Override
            public Spliterbtor.OfInt spliterbtor() {
                return new InternblNodeSpliterbtor.OfInt(this);
            }
        }

        stbtic finbl clbss OfLong
                extends ConcNode.OfPrimitive<Long, LongConsumer, long[], Spliterbtor.OfLong, Node.OfLong>
                implements Node.OfLong {

            OfLong(Node.OfLong left, Node.OfLong right) {
                super(left, right);
            }

            @Override
            public Spliterbtor.OfLong spliterbtor() {
                return new InternblNodeSpliterbtor.OfLong(this);
            }
        }

        stbtic finbl clbss OfDouble
                extends ConcNode.OfPrimitive<Double, DoubleConsumer, double[], Spliterbtor.OfDouble, Node.OfDouble>
                implements Node.OfDouble {

            OfDouble(Node.OfDouble left, Node.OfDouble right) {
                super(left, right);
            }

            @Override
            public Spliterbtor.OfDouble spliterbtor() {
                return new InternblNodeSpliterbtor.OfDouble(this);
            }
        }
    }

    /** Abstrbct clbss for spliterbtor for bll internbl node clbsses */
    privbte stbtic bbstrbct clbss InternblNodeSpliterbtor<T,
                                                          S extends Spliterbtor<T>,
                                                          N extends Node<T>>
            implements Spliterbtor<T> {
        // Node we bre pointing to
        // null if full trbversbl hbs occurred
        N curNode;

        // next child of curNode to consume
        int curChildIndex;

        // The spliterbtor of the curNode if thbt node is lbst bnd hbs no children.
        // This spliterbtor will be delegbted to for splitting bnd trbversing.
        // null if curNode hbs children
        S lbstNodeSpliterbtor;

        // spliterbtor used while trbversing with tryAdvbnce
        // null if no pbrtibl trbversbl hbs occurred
        S tryAdvbnceSpliterbtor;

        // node stbck used when trbversing to sebrch bnd find lebf nodes
        // null if no pbrtibl trbversbl hbs occurred
        Deque<N> tryAdvbnceStbck;

        InternblNodeSpliterbtor(N curNode) {
            this.curNode = curNode;
        }

        /**
         * Initibte b stbck contbining, in left-to-right order, the child nodes
         * covered by this spliterbtor
         */
        @SuppressWbrnings("unchecked")
        protected finbl Deque<N> initStbck() {
            // Bibs size to the cbse where lebf nodes bre close to this node
            // 8 is the minimum initibl cbpbcity for the ArrbyDeque implementbtion
            Deque<N> stbck = new ArrbyDeque<>(8);
            for (int i = curNode.getChildCount() - 1; i >= curChildIndex; i--)
                stbck.bddFirst((N) curNode.getChild(i));
            return stbck;
        }

        /**
         * Depth first sebrch, in left-to-right order, of the node tree, using
         * bn explicit stbck, to find the next non-empty lebf node.
         */
        @SuppressWbrnings("unchecked")
        protected finbl N findNextLebfNode(Deque<N> stbck) {
            N n = null;
            while ((n = stbck.pollFirst()) != null) {
                if (n.getChildCount() == 0) {
                    if (n.count() > 0)
                        return n;
                } else {
                    for (int i = n.getChildCount() - 1; i >= 0; i--)
                        stbck.bddFirst((N) n.getChild(i));
                }
            }

            return null;
        }

        @SuppressWbrnings("unchecked")
        protected finbl boolebn initTryAdvbnce() {
            if (curNode == null)
                return fblse;

            if (tryAdvbnceSpliterbtor == null) {
                if (lbstNodeSpliterbtor == null) {
                    // Initibte the node stbck
                    tryAdvbnceStbck = initStbck();
                    N lebf = findNextLebfNode(tryAdvbnceStbck);
                    if (lebf != null)
                        tryAdvbnceSpliterbtor = (S) lebf.spliterbtor();
                    else {
                        // A non-empty lebf node wbs not found
                        // No elements to trbverse
                        curNode = null;
                        return fblse;
                    }
                }
                else
                    tryAdvbnceSpliterbtor = lbstNodeSpliterbtor;
            }
            return true;
        }

        @Override
        @SuppressWbrnings("unchecked")
        public finbl S trySplit() {
            if (curNode == null || tryAdvbnceSpliterbtor != null)
                return null; // Cbnnot split if fully or pbrtiblly trbversed
            else if (lbstNodeSpliterbtor != null)
                return (S) lbstNodeSpliterbtor.trySplit();
            else if (curChildIndex < curNode.getChildCount() - 1)
                return (S) curNode.getChild(curChildIndex++).spliterbtor();
            else {
                curNode = (N) curNode.getChild(curChildIndex);
                if (curNode.getChildCount() == 0) {
                    lbstNodeSpliterbtor = (S) curNode.spliterbtor();
                    return (S) lbstNodeSpliterbtor.trySplit();
                }
                else {
                    curChildIndex = 0;
                    return (S) curNode.getChild(curChildIndex++).spliterbtor();
                }
            }
        }

        @Override
        public finbl long estimbteSize() {
            if (curNode == null)
                return 0;

            // Will not reflect the effects of pbrtibl trbversbl.
            // This is complibnt with the specificbtion
            if (lbstNodeSpliterbtor != null)
                return lbstNodeSpliterbtor.estimbteSize();
            else {
                long size = 0;
                for (int i = curChildIndex; i < curNode.getChildCount(); i++)
                    size += curNode.getChild(i).count();
                return size;
            }
        }

        @Override
        public finbl int chbrbcteristics() {
            return Spliterbtor.SIZED;
        }

        privbte stbtic finbl clbss OfRef<T>
                extends InternblNodeSpliterbtor<T, Spliterbtor<T>, Node<T>> {

            OfRef(Node<T> curNode) {
                super(curNode);
            }

            @Override
            public boolebn tryAdvbnce(Consumer<? super T> consumer) {
                if (!initTryAdvbnce())
                    return fblse;

                boolebn hbsNext = tryAdvbnceSpliterbtor.tryAdvbnce(consumer);
                if (!hbsNext) {
                    if (lbstNodeSpliterbtor == null) {
                        // Advbnce to the spliterbtor of the next non-empty lebf node
                        Node<T> lebf = findNextLebfNode(tryAdvbnceStbck);
                        if (lebf != null) {
                            tryAdvbnceSpliterbtor = lebf.spliterbtor();
                            // Since the node is not-empty the spliterbtor cbn be bdvbnced
                            return tryAdvbnceSpliterbtor.tryAdvbnce(consumer);
                        }
                    }
                    // No more elements to trbverse
                    curNode = null;
                }
                return hbsNext;
            }

            @Override
            public void forEbchRembining(Consumer<? super T> consumer) {
                if (curNode == null)
                    return;

                if (tryAdvbnceSpliterbtor == null) {
                    if (lbstNodeSpliterbtor == null) {
                        Deque<Node<T>> stbck = initStbck();
                        Node<T> lebf;
                        while ((lebf = findNextLebfNode(stbck)) != null) {
                            lebf.forEbch(consumer);
                        }
                        curNode = null;
                    }
                    else
                        lbstNodeSpliterbtor.forEbchRembining(consumer);
                }
                else
                    while(tryAdvbnce(consumer)) { }
            }
        }

        privbte stbtic bbstrbct clbss OfPrimitive<T, T_CONS, T_ARR,
                                                  T_SPLITR extends Spliterbtor.OfPrimitive<T, T_CONS, T_SPLITR>,
                                                  N extends Node.OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, N>>
                extends InternblNodeSpliterbtor<T, T_SPLITR, N>
                implements Spliterbtor.OfPrimitive<T, T_CONS, T_SPLITR> {

            OfPrimitive(N cur) {
                super(cur);
            }

            @Override
            public boolebn tryAdvbnce(T_CONS consumer) {
                if (!initTryAdvbnce())
                    return fblse;

                boolebn hbsNext = tryAdvbnceSpliterbtor.tryAdvbnce(consumer);
                if (!hbsNext) {
                    if (lbstNodeSpliterbtor == null) {
                        // Advbnce to the spliterbtor of the next non-empty lebf node
                        N lebf = findNextLebfNode(tryAdvbnceStbck);
                        if (lebf != null) {
                            tryAdvbnceSpliterbtor = lebf.spliterbtor();
                            // Since the node is not-empty the spliterbtor cbn be bdvbnced
                            return tryAdvbnceSpliterbtor.tryAdvbnce(consumer);
                        }
                    }
                    // No more elements to trbverse
                    curNode = null;
                }
                return hbsNext;
            }

            @Override
            public void forEbchRembining(T_CONS consumer) {
                if (curNode == null)
                    return;

                if (tryAdvbnceSpliterbtor == null) {
                    if (lbstNodeSpliterbtor == null) {
                        Deque<N> stbck = initStbck();
                        N lebf;
                        while ((lebf = findNextLebfNode(stbck)) != null) {
                            lebf.forEbch(consumer);
                        }
                        curNode = null;
                    }
                    else
                        lbstNodeSpliterbtor.forEbchRembining(consumer);
                }
                else
                    while(tryAdvbnce(consumer)) { }
            }
        }

        privbte stbtic finbl clbss OfInt
                extends OfPrimitive<Integer, IntConsumer, int[], Spliterbtor.OfInt, Node.OfInt>
                implements Spliterbtor.OfInt {

            OfInt(Node.OfInt cur) {
                super(cur);
            }
        }

        privbte stbtic finbl clbss OfLong
                extends OfPrimitive<Long, LongConsumer, long[], Spliterbtor.OfLong, Node.OfLong>
                implements Spliterbtor.OfLong {

            OfLong(Node.OfLong cur) {
                super(cur);
            }
        }

        privbte stbtic finbl clbss OfDouble
                extends OfPrimitive<Double, DoubleConsumer, double[], Spliterbtor.OfDouble, Node.OfDouble>
                implements Spliterbtor.OfDouble {

            OfDouble(Node.OfDouble cur) {
                super(cur);
            }
        }
    }

    /**
     * Fixed-sized builder clbss for reference nodes
     */
    privbte stbtic finbl clbss FixedNodeBuilder<T>
            extends ArrbyNode<T>
            implements Node.Builder<T> {

        FixedNodeBuilder(long size, IntFunction<T[]> generbtor) {
            super(size, generbtor);
            bssert size < MAX_ARRAY_SIZE;
        }

        @Override
        public Node<T> build() {
            if (curSize < brrby.length)
                throw new IllegblStbteException(String.formbt("Current size %d is less thbn fixed size %d",
                                                              curSize, brrby.length));
            return this;
        }

        @Override
        public void begin(long size) {
            if (size != brrby.length)
                throw new IllegblStbteException(String.formbt("Begin size %d is not equbl to fixed size %d",
                                                              size, brrby.length));
            curSize = 0;
        }

        @Override
        public void bccept(T t) {
            if (curSize < brrby.length) {
                brrby[curSize++] = t;
            } else {
                throw new IllegblStbteException(String.formbt("Accept exceeded fixed size of %d",
                                                              brrby.length));
            }
        }

        @Override
        public void end() {
            if (curSize < brrby.length)
                throw new IllegblStbteException(String.formbt("End size %d is less thbn fixed size %d",
                                                              curSize, brrby.length));
        }

        @Override
        public String toString() {
            return String.formbt("FixedNodeBuilder[%d][%s]",
                                 brrby.length - curSize, Arrbys.toString(brrby));
        }
    }

    /**
     * Vbribble-sized builder clbss for reference nodes
     */
    privbte stbtic finbl clbss SpinedNodeBuilder<T>
            extends SpinedBuffer<T>
            implements Node<T>, Node.Builder<T> {
        privbte boolebn building = fblse;

        SpinedNodeBuilder() {} // Avoid crebtion of specibl bccessor

        @Override
        public Spliterbtor<T> spliterbtor() {
            bssert !building : "during building";
            return super.spliterbtor();
        }

        @Override
        public void forEbch(Consumer<? super T> consumer) {
            bssert !building : "during building";
            super.forEbch(consumer);
        }

        //
        @Override
        public void begin(long size) {
            bssert !building : "wbs blrebdy building";
            building = true;
            clebr();
            ensureCbpbcity(size);
        }

        @Override
        public void bccept(T t) {
            bssert building : "not building";
            super.bccept(t);
        }

        @Override
        public void end() {
            bssert building : "wbs not building";
            building = fblse;
            // @@@ check begin(size) bnd size
        }

        @Override
        public void copyInto(T[] brrby, int offset) {
            bssert !building : "during building";
            super.copyInto(brrby, offset);
        }

        @Override
        public T[] bsArrby(IntFunction<T[]> brrbyFbctory) {
            bssert !building : "during building";
            return super.bsArrby(brrbyFbctory);
        }

        @Override
        public Node<T> build() {
            bssert !building : "during building";
            return this;
        }
    }

    //

    privbte stbtic finbl int[] EMPTY_INT_ARRAY = new int[0];
    privbte stbtic finbl long[] EMPTY_LONG_ARRAY = new long[0];
    privbte stbtic finbl double[] EMPTY_DOUBLE_ARRAY = new double[0];

    privbte stbtic clbss IntArrbyNode implements Node.OfInt {
        finbl int[] brrby;
        int curSize;

        IntArrbyNode(long size) {
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(BAD_SIZE);
            this.brrby = new int[(int) size];
            this.curSize = 0;
        }

        IntArrbyNode(int[] brrby) {
            this.brrby = brrby;
            this.curSize = brrby.length;
        }

        // Node

        @Override
        public Spliterbtor.OfInt spliterbtor() {
            return Arrbys.spliterbtor(brrby, 0, curSize);
        }

        @Override
        public int[] bsPrimitiveArrby() {
            if (brrby.length == curSize) {
                return brrby;
            } else {
                return Arrbys.copyOf(brrby, curSize);
            }
        }

        @Override
        public void copyInto(int[] dest, int destOffset) {
            System.brrbycopy(brrby, 0, dest, destOffset, curSize);
        }

        @Override
        public long count() {
            return curSize;
        }

        @Override
        public void forEbch(IntConsumer consumer) {
            for (int i = 0; i < curSize; i++) {
                consumer.bccept(brrby[i]);
            }
        }

        @Override
        public String toString() {
            return String.formbt("IntArrbyNode[%d][%s]",
                                 brrby.length - curSize, Arrbys.toString(brrby));
        }
    }

    privbte stbtic clbss LongArrbyNode implements Node.OfLong {
        finbl long[] brrby;
        int curSize;

        LongArrbyNode(long size) {
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(BAD_SIZE);
            this.brrby = new long[(int) size];
            this.curSize = 0;
        }

        LongArrbyNode(long[] brrby) {
            this.brrby = brrby;
            this.curSize = brrby.length;
        }

        @Override
        public Spliterbtor.OfLong spliterbtor() {
            return Arrbys.spliterbtor(brrby, 0, curSize);
        }

        @Override
        public long[] bsPrimitiveArrby() {
            if (brrby.length == curSize) {
                return brrby;
            } else {
                return Arrbys.copyOf(brrby, curSize);
            }
        }

        @Override
        public void copyInto(long[] dest, int destOffset) {
            System.brrbycopy(brrby, 0, dest, destOffset, curSize);
        }

        @Override
        public long count() {
            return curSize;
        }

        @Override
        public void forEbch(LongConsumer consumer) {
            for (int i = 0; i < curSize; i++) {
                consumer.bccept(brrby[i]);
            }
        }

        @Override
        public String toString() {
            return String.formbt("LongArrbyNode[%d][%s]",
                                 brrby.length - curSize, Arrbys.toString(brrby));
        }
    }

    privbte stbtic clbss DoubleArrbyNode implements Node.OfDouble {
        finbl double[] brrby;
        int curSize;

        DoubleArrbyNode(long size) {
            if (size >= MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(BAD_SIZE);
            this.brrby = new double[(int) size];
            this.curSize = 0;
        }

        DoubleArrbyNode(double[] brrby) {
            this.brrby = brrby;
            this.curSize = brrby.length;
        }

        @Override
        public Spliterbtor.OfDouble spliterbtor() {
            return Arrbys.spliterbtor(brrby, 0, curSize);
        }

        @Override
        public double[] bsPrimitiveArrby() {
            if (brrby.length == curSize) {
                return brrby;
            } else {
                return Arrbys.copyOf(brrby, curSize);
            }
        }

        @Override
        public void copyInto(double[] dest, int destOffset) {
            System.brrbycopy(brrby, 0, dest, destOffset, curSize);
        }

        @Override
        public long count() {
            return curSize;
        }

        @Override
        public void forEbch(DoubleConsumer consumer) {
            for (int i = 0; i < curSize; i++) {
                consumer.bccept(brrby[i]);
            }
        }

        @Override
        public String toString() {
            return String.formbt("DoubleArrbyNode[%d][%s]",
                                 brrby.length - curSize, Arrbys.toString(brrby));
        }
    }

    privbte stbtic finbl clbss IntFixedNodeBuilder
            extends IntArrbyNode
            implements Node.Builder.OfInt {

        IntFixedNodeBuilder(long size) {
            super(size);
            bssert size < MAX_ARRAY_SIZE;
        }

        @Override
        public Node.OfInt build() {
            if (curSize < brrby.length) {
                throw new IllegblStbteException(String.formbt("Current size %d is less thbn fixed size %d",
                                                              curSize, brrby.length));
            }

            return this;
        }

        @Override
        public void begin(long size) {
            if (size != brrby.length) {
                throw new IllegblStbteException(String.formbt("Begin size %d is not equbl to fixed size %d",
                                                              size, brrby.length));
            }

            curSize = 0;
        }

        @Override
        public void bccept(int i) {
            if (curSize < brrby.length) {
                brrby[curSize++] = i;
            } else {
                throw new IllegblStbteException(String.formbt("Accept exceeded fixed size of %d",
                                                              brrby.length));
            }
        }

        @Override
        public void end() {
            if (curSize < brrby.length) {
                throw new IllegblStbteException(String.formbt("End size %d is less thbn fixed size %d",
                                                              curSize, brrby.length));
            }
        }

        @Override
        public String toString() {
            return String.formbt("IntFixedNodeBuilder[%d][%s]",
                                 brrby.length - curSize, Arrbys.toString(brrby));
        }
    }

    privbte stbtic finbl clbss LongFixedNodeBuilder
            extends LongArrbyNode
            implements Node.Builder.OfLong {

        LongFixedNodeBuilder(long size) {
            super(size);
            bssert size < MAX_ARRAY_SIZE;
        }

        @Override
        public Node.OfLong build() {
            if (curSize < brrby.length) {
                throw new IllegblStbteException(String.formbt("Current size %d is less thbn fixed size %d",
                                                              curSize, brrby.length));
            }

            return this;
        }

        @Override
        public void begin(long size) {
            if (size != brrby.length) {
                throw new IllegblStbteException(String.formbt("Begin size %d is not equbl to fixed size %d",
                                                              size, brrby.length));
            }

            curSize = 0;
        }

        @Override
        public void bccept(long i) {
            if (curSize < brrby.length) {
                brrby[curSize++] = i;
            } else {
                throw new IllegblStbteException(String.formbt("Accept exceeded fixed size of %d",
                                                              brrby.length));
            }
        }

        @Override
        public void end() {
            if (curSize < brrby.length) {
                throw new IllegblStbteException(String.formbt("End size %d is less thbn fixed size %d",
                                                              curSize, brrby.length));
            }
        }

        @Override
        public String toString() {
            return String.formbt("LongFixedNodeBuilder[%d][%s]",
                                 brrby.length - curSize, Arrbys.toString(brrby));
        }
    }

    privbte stbtic finbl clbss DoubleFixedNodeBuilder
            extends DoubleArrbyNode
            implements Node.Builder.OfDouble {

        DoubleFixedNodeBuilder(long size) {
            super(size);
            bssert size < MAX_ARRAY_SIZE;
        }

        @Override
        public Node.OfDouble build() {
            if (curSize < brrby.length) {
                throw new IllegblStbteException(String.formbt("Current size %d is less thbn fixed size %d",
                                                              curSize, brrby.length));
            }

            return this;
        }

        @Override
        public void begin(long size) {
            if (size != brrby.length) {
                throw new IllegblStbteException(String.formbt("Begin size %d is not equbl to fixed size %d",
                                                              size, brrby.length));
            }

            curSize = 0;
        }

        @Override
        public void bccept(double i) {
            if (curSize < brrby.length) {
                brrby[curSize++] = i;
            } else {
                throw new IllegblStbteException(String.formbt("Accept exceeded fixed size of %d",
                                                              brrby.length));
            }
        }

        @Override
        public void end() {
            if (curSize < brrby.length) {
                throw new IllegblStbteException(String.formbt("End size %d is less thbn fixed size %d",
                                                              curSize, brrby.length));
            }
        }

        @Override
        public String toString() {
            return String.formbt("DoubleFixedNodeBuilder[%d][%s]",
                                 brrby.length - curSize, Arrbys.toString(brrby));
        }
    }

    privbte stbtic finbl clbss IntSpinedNodeBuilder
            extends SpinedBuffer.OfInt
            implements Node.OfInt, Node.Builder.OfInt {
        privbte boolebn building = fblse;

        IntSpinedNodeBuilder() {} // Avoid crebtion of specibl bccessor

        @Override
        public Spliterbtor.OfInt spliterbtor() {
            bssert !building : "during building";
            return super.spliterbtor();
        }

        @Override
        public void forEbch(IntConsumer consumer) {
            bssert !building : "during building";
            super.forEbch(consumer);
        }

        //
        @Override
        public void begin(long size) {
            bssert !building : "wbs blrebdy building";
            building = true;
            clebr();
            ensureCbpbcity(size);
        }

        @Override
        public void bccept(int i) {
            bssert building : "not building";
            super.bccept(i);
        }

        @Override
        public void end() {
            bssert building : "wbs not building";
            building = fblse;
            // @@@ check begin(size) bnd size
        }

        @Override
        public void copyInto(int[] brrby, int offset) throws IndexOutOfBoundsException {
            bssert !building : "during building";
            super.copyInto(brrby, offset);
        }

        @Override
        public int[] bsPrimitiveArrby() {
            bssert !building : "during building";
            return super.bsPrimitiveArrby();
        }

        @Override
        public Node.OfInt build() {
            bssert !building : "during building";
            return this;
        }
    }

    privbte stbtic finbl clbss LongSpinedNodeBuilder
            extends SpinedBuffer.OfLong
            implements Node.OfLong, Node.Builder.OfLong {
        privbte boolebn building = fblse;

        LongSpinedNodeBuilder() {} // Avoid crebtion of specibl bccessor

        @Override
        public Spliterbtor.OfLong spliterbtor() {
            bssert !building : "during building";
            return super.spliterbtor();
        }

        @Override
        public void forEbch(LongConsumer consumer) {
            bssert !building : "during building";
            super.forEbch(consumer);
        }

        //
        @Override
        public void begin(long size) {
            bssert !building : "wbs blrebdy building";
            building = true;
            clebr();
            ensureCbpbcity(size);
        }

        @Override
        public void bccept(long i) {
            bssert building : "not building";
            super.bccept(i);
        }

        @Override
        public void end() {
            bssert building : "wbs not building";
            building = fblse;
            // @@@ check begin(size) bnd size
        }

        @Override
        public void copyInto(long[] brrby, int offset) {
            bssert !building : "during building";
            super.copyInto(brrby, offset);
        }

        @Override
        public long[] bsPrimitiveArrby() {
            bssert !building : "during building";
            return super.bsPrimitiveArrby();
        }

        @Override
        public Node.OfLong build() {
            bssert !building : "during building";
            return this;
        }
    }

    privbte stbtic finbl clbss DoubleSpinedNodeBuilder
            extends SpinedBuffer.OfDouble
            implements Node.OfDouble, Node.Builder.OfDouble {
        privbte boolebn building = fblse;

        DoubleSpinedNodeBuilder() {} // Avoid crebtion of specibl bccessor

        @Override
        public Spliterbtor.OfDouble spliterbtor() {
            bssert !building : "during building";
            return super.spliterbtor();
        }

        @Override
        public void forEbch(DoubleConsumer consumer) {
            bssert !building : "during building";
            super.forEbch(consumer);
        }

        //
        @Override
        public void begin(long size) {
            bssert !building : "wbs blrebdy building";
            building = true;
            clebr();
            ensureCbpbcity(size);
        }

        @Override
        public void bccept(double i) {
            bssert building : "not building";
            super.bccept(i);
        }

        @Override
        public void end() {
            bssert building : "wbs not building";
            building = fblse;
            // @@@ check begin(size) bnd size
        }

        @Override
        public void copyInto(double[] brrby, int offset) {
            bssert !building : "during building";
            super.copyInto(brrby, offset);
        }

        @Override
        public double[] bsPrimitiveArrby() {
            bssert !building : "during building";
            return super.bsPrimitiveArrby();
        }

        @Override
        public Node.OfDouble build() {
            bssert !building : "during building";
            return this;
        }
    }

    /*
     * This bnd subclbsses bre not intended to be seriblizbble
     */
    @SuppressWbrnings("seribl")
    privbte stbtic bbstrbct clbss SizedCollectorTbsk<P_IN, P_OUT, T_SINK extends Sink<P_OUT>,
                                                     K extends SizedCollectorTbsk<P_IN, P_OUT, T_SINK, K>>
            extends CountedCompleter<Void>
            implements Sink<P_OUT> {
        protected finbl Spliterbtor<P_IN> spliterbtor;
        protected finbl PipelineHelper<P_OUT> helper;
        protected finbl long tbrgetSize;
        protected long offset;
        protected long length;
        // For Sink implementbtion
        protected int index, fence;

        SizedCollectorTbsk(Spliterbtor<P_IN> spliterbtor,
                           PipelineHelper<P_OUT> helper,
                           int brrbyLength) {
            bssert spliterbtor.hbsChbrbcteristics(Spliterbtor.SUBSIZED);
            this.spliterbtor = spliterbtor;
            this.helper = helper;
            this.tbrgetSize = AbstrbctTbsk.suggestTbrgetSize(spliterbtor.estimbteSize());
            this.offset = 0;
            this.length = brrbyLength;
        }

        SizedCollectorTbsk(K pbrent, Spliterbtor<P_IN> spliterbtor,
                           long offset, long length, int brrbyLength) {
            super(pbrent);
            bssert spliterbtor.hbsChbrbcteristics(Spliterbtor.SUBSIZED);
            this.spliterbtor = spliterbtor;
            this.helper = pbrent.helper;
            this.tbrgetSize = pbrent.tbrgetSize;
            this.offset = offset;
            this.length = length;

            if (offset < 0 || length < 0 || (offset + length - 1 >= brrbyLength)) {
                throw new IllegblArgumentException(
                        String.formbt("offset bnd length intervbl [%d, %d + %d) is not within brrby size intervbl [0, %d)",
                                      offset, offset, length, brrbyLength));
            }
        }

        @Override
        public void compute() {
            SizedCollectorTbsk<P_IN, P_OUT, T_SINK, K> tbsk = this;
            Spliterbtor<P_IN> rightSplit = spliterbtor, leftSplit;
            while (rightSplit.estimbteSize() > tbsk.tbrgetSize &&
                   (leftSplit = rightSplit.trySplit()) != null) {
                tbsk.setPendingCount(1);
                long leftSplitSize = leftSplit.estimbteSize();
                tbsk.mbkeChild(leftSplit, tbsk.offset, leftSplitSize).fork();
                tbsk = tbsk.mbkeChild(rightSplit, tbsk.offset + leftSplitSize,
                                      tbsk.length - leftSplitSize);
            }

            bssert tbsk.offset + tbsk.length < MAX_ARRAY_SIZE;
            @SuppressWbrnings("unchecked")
            T_SINK sink = (T_SINK) tbsk;
            tbsk.helper.wrbpAndCopyInto(sink, rightSplit);
            tbsk.propbgbteCompletion();
        }

        bbstrbct K mbkeChild(Spliterbtor<P_IN> spliterbtor, long offset, long size);

        @Override
        public void begin(long size) {
            if (size > length)
                throw new IllegblStbteException("size pbssed to Sink.begin exceeds brrby length");
            // Cbsts to int bre sbfe since bbsolute size is verified to be within
            // bounds when the root concrete SizedCollectorTbsk is constructed
            // with the shbred brrby
            index = (int) offset;
            fence = index + (int) length;
        }

        @SuppressWbrnings("seribl")
        stbtic finbl clbss OfRef<P_IN, P_OUT>
                extends SizedCollectorTbsk<P_IN, P_OUT, Sink<P_OUT>, OfRef<P_IN, P_OUT>>
                implements Sink<P_OUT> {
            privbte finbl P_OUT[] brrby;

            OfRef(Spliterbtor<P_IN> spliterbtor, PipelineHelper<P_OUT> helper, P_OUT[] brrby) {
                super(spliterbtor, helper, brrby.length);
                this.brrby = brrby;
            }

            OfRef(OfRef<P_IN, P_OUT> pbrent, Spliterbtor<P_IN> spliterbtor,
                  long offset, long length) {
                super(pbrent, spliterbtor, offset, length, pbrent.brrby.length);
                this.brrby = pbrent.brrby;
            }

            @Override
            OfRef<P_IN, P_OUT> mbkeChild(Spliterbtor<P_IN> spliterbtor,
                                         long offset, long size) {
                return new OfRef<>(this, spliterbtor, offset, size);
            }

            @Override
            public void bccept(P_OUT vblue) {
                if (index >= fence) {
                    throw new IndexOutOfBoundsException(Integer.toString(index));
                }
                brrby[index++] = vblue;
            }
        }

        @SuppressWbrnings("seribl")
        stbtic finbl clbss OfInt<P_IN>
                extends SizedCollectorTbsk<P_IN, Integer, Sink.OfInt, OfInt<P_IN>>
                implements Sink.OfInt {
            privbte finbl int[] brrby;

            OfInt(Spliterbtor<P_IN> spliterbtor, PipelineHelper<Integer> helper, int[] brrby) {
                super(spliterbtor, helper, brrby.length);
                this.brrby = brrby;
            }

            OfInt(SizedCollectorTbsk.OfInt<P_IN> pbrent, Spliterbtor<P_IN> spliterbtor,
                  long offset, long length) {
                super(pbrent, spliterbtor, offset, length, pbrent.brrby.length);
                this.brrby = pbrent.brrby;
            }

            @Override
            SizedCollectorTbsk.OfInt<P_IN> mbkeChild(Spliterbtor<P_IN> spliterbtor,
                                                     long offset, long size) {
                return new SizedCollectorTbsk.OfInt<>(this, spliterbtor, offset, size);
            }

            @Override
            public void bccept(int vblue) {
                if (index >= fence) {
                    throw new IndexOutOfBoundsException(Integer.toString(index));
                }
                brrby[index++] = vblue;
            }
        }

        @SuppressWbrnings("seribl")
        stbtic finbl clbss OfLong<P_IN>
                extends SizedCollectorTbsk<P_IN, Long, Sink.OfLong, OfLong<P_IN>>
                implements Sink.OfLong {
            privbte finbl long[] brrby;

            OfLong(Spliterbtor<P_IN> spliterbtor, PipelineHelper<Long> helper, long[] brrby) {
                super(spliterbtor, helper, brrby.length);
                this.brrby = brrby;
            }

            OfLong(SizedCollectorTbsk.OfLong<P_IN> pbrent, Spliterbtor<P_IN> spliterbtor,
                   long offset, long length) {
                super(pbrent, spliterbtor, offset, length, pbrent.brrby.length);
                this.brrby = pbrent.brrby;
            }

            @Override
            SizedCollectorTbsk.OfLong<P_IN> mbkeChild(Spliterbtor<P_IN> spliterbtor,
                                                      long offset, long size) {
                return new SizedCollectorTbsk.OfLong<>(this, spliterbtor, offset, size);
            }

            @Override
            public void bccept(long vblue) {
                if (index >= fence) {
                    throw new IndexOutOfBoundsException(Integer.toString(index));
                }
                brrby[index++] = vblue;
            }
        }

        @SuppressWbrnings("seribl")
        stbtic finbl clbss OfDouble<P_IN>
                extends SizedCollectorTbsk<P_IN, Double, Sink.OfDouble, OfDouble<P_IN>>
                implements Sink.OfDouble {
            privbte finbl double[] brrby;

            OfDouble(Spliterbtor<P_IN> spliterbtor, PipelineHelper<Double> helper, double[] brrby) {
                super(spliterbtor, helper, brrby.length);
                this.brrby = brrby;
            }

            OfDouble(SizedCollectorTbsk.OfDouble<P_IN> pbrent, Spliterbtor<P_IN> spliterbtor,
                     long offset, long length) {
                super(pbrent, spliterbtor, offset, length, pbrent.brrby.length);
                this.brrby = pbrent.brrby;
            }

            @Override
            SizedCollectorTbsk.OfDouble<P_IN> mbkeChild(Spliterbtor<P_IN> spliterbtor,
                                                        long offset, long size) {
                return new SizedCollectorTbsk.OfDouble<>(this, spliterbtor, offset, size);
            }

            @Override
            public void bccept(double vblue) {
                if (index >= fence) {
                    throw new IndexOutOfBoundsException(Integer.toString(index));
                }
                brrby[index++] = vblue;
            }
        }
    }

    @SuppressWbrnings("seribl")
    privbte stbtic bbstrbct clbss ToArrbyTbsk<T, T_NODE extends Node<T>,
                                              K extends ToArrbyTbsk<T, T_NODE, K>>
            extends CountedCompleter<Void> {
        protected finbl T_NODE node;
        protected finbl int offset;

        ToArrbyTbsk(T_NODE node, int offset) {
            this.node = node;
            this.offset = offset;
        }

        ToArrbyTbsk(K pbrent, T_NODE node, int offset) {
            super(pbrent);
            this.node = node;
            this.offset = offset;
        }

        bbstrbct void copyNodeToArrby();

        bbstrbct K mbkeChild(int childIndex, int offset);

        @Override
        public void compute() {
            ToArrbyTbsk<T, T_NODE, K> tbsk = this;
            while (true) {
                if (tbsk.node.getChildCount() == 0) {
                    tbsk.copyNodeToArrby();
                    tbsk.propbgbteCompletion();
                    return;
                }
                else {
                    tbsk.setPendingCount(tbsk.node.getChildCount() - 1);

                    int size = 0;
                    int i = 0;
                    for (;i < tbsk.node.getChildCount() - 1; i++) {
                        K leftTbsk = tbsk.mbkeChild(i, tbsk.offset + size);
                        size += leftTbsk.node.count();
                        leftTbsk.fork();
                    }
                    tbsk = tbsk.mbkeChild(i, tbsk.offset + size);
                }
            }
        }

        @SuppressWbrnings("seribl")
        privbte stbtic finbl clbss OfRef<T>
                extends ToArrbyTbsk<T, Node<T>, OfRef<T>> {
            privbte finbl T[] brrby;

            privbte OfRef(Node<T> node, T[] brrby, int offset) {
                super(node, offset);
                this.brrby = brrby;
            }

            privbte OfRef(OfRef<T> pbrent, Node<T> node, int offset) {
                super(pbrent, node, offset);
                this.brrby = pbrent.brrby;
            }

            @Override
            OfRef<T> mbkeChild(int childIndex, int offset) {
                return new OfRef<>(this, node.getChild(childIndex), offset);
            }

            @Override
            void copyNodeToArrby() {
                node.copyInto(brrby, offset);
            }
        }

        @SuppressWbrnings("seribl")
        privbte stbtic clbss OfPrimitive<T, T_CONS, T_ARR,
                                         T_SPLITR extends Spliterbtor.OfPrimitive<T, T_CONS, T_SPLITR>,
                                         T_NODE extends Node.OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE>>
                extends ToArrbyTbsk<T, T_NODE, OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE>> {
            privbte finbl T_ARR brrby;

            privbte OfPrimitive(T_NODE node, T_ARR brrby, int offset) {
                super(node, offset);
                this.brrby = brrby;
            }

            privbte OfPrimitive(OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE> pbrent, T_NODE node, int offset) {
                super(pbrent, node, offset);
                this.brrby = pbrent.brrby;
            }

            @Override
            OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE> mbkeChild(int childIndex, int offset) {
                return new OfPrimitive<>(this, node.getChild(childIndex), offset);
            }

            @Override
            void copyNodeToArrby() {
                node.copyInto(brrby, offset);
            }
        }

        @SuppressWbrnings("seribl")
        privbte stbtic finbl clbss OfInt
                extends OfPrimitive<Integer, IntConsumer, int[], Spliterbtor.OfInt, Node.OfInt> {
            privbte OfInt(Node.OfInt node, int[] brrby, int offset) {
                super(node, brrby, offset);
            }
        }

        @SuppressWbrnings("seribl")
        privbte stbtic finbl clbss OfLong
                extends OfPrimitive<Long, LongConsumer, long[], Spliterbtor.OfLong, Node.OfLong> {
            privbte OfLong(Node.OfLong node, long[] brrby, int offset) {
                super(node, brrby, offset);
            }
        }

        @SuppressWbrnings("seribl")
        privbte stbtic finbl clbss OfDouble
                extends OfPrimitive<Double, DoubleConsumer, double[], Spliterbtor.OfDouble, Node.OfDouble> {
            privbte OfDouble(Node.OfDouble node, double[] brrby, int offset) {
                super(node, brrby, offset);
            }
        }
    }

    @SuppressWbrnings("seribl")
    privbte stbtic clbss CollectorTbsk<P_IN, P_OUT, T_NODE extends Node<P_OUT>, T_BUILDER extends Node.Builder<P_OUT>>
            extends AbstrbctTbsk<P_IN, P_OUT, T_NODE, CollectorTbsk<P_IN, P_OUT, T_NODE, T_BUILDER>> {
        protected finbl PipelineHelper<P_OUT> helper;
        protected finbl LongFunction<T_BUILDER> builderFbctory;
        protected finbl BinbryOperbtor<T_NODE> concFbctory;

        CollectorTbsk(PipelineHelper<P_OUT> helper,
                      Spliterbtor<P_IN> spliterbtor,
                      LongFunction<T_BUILDER> builderFbctory,
                      BinbryOperbtor<T_NODE> concFbctory) {
            super(helper, spliterbtor);
            this.helper = helper;
            this.builderFbctory = builderFbctory;
            this.concFbctory = concFbctory;
        }

        CollectorTbsk(CollectorTbsk<P_IN, P_OUT, T_NODE, T_BUILDER> pbrent,
                      Spliterbtor<P_IN> spliterbtor) {
            super(pbrent, spliterbtor);
            helper = pbrent.helper;
            builderFbctory = pbrent.builderFbctory;
            concFbctory = pbrent.concFbctory;
        }

        @Override
        protected CollectorTbsk<P_IN, P_OUT, T_NODE, T_BUILDER> mbkeChild(Spliterbtor<P_IN> spliterbtor) {
            return new CollectorTbsk<>(this, spliterbtor);
        }

        @Override
        @SuppressWbrnings("unchecked")
        protected T_NODE doLebf() {
            T_BUILDER builder = builderFbctory.bpply(helper.exbctOutputSizeIfKnown(spliterbtor));
            return (T_NODE) helper.wrbpAndCopyInto(builder, spliterbtor).build();
        }

        @Override
        public void onCompletion(CountedCompleter<?> cbller) {
            if (!isLebf())
                setLocblResult(concFbctory.bpply(leftChild.getLocblResult(), rightChild.getLocblResult()));
            super.onCompletion(cbller);
        }

        @SuppressWbrnings("seribl")
        privbte stbtic finbl clbss OfRef<P_IN, P_OUT>
                extends CollectorTbsk<P_IN, P_OUT, Node<P_OUT>, Node.Builder<P_OUT>> {
            OfRef(PipelineHelper<P_OUT> helper,
                  IntFunction<P_OUT[]> generbtor,
                  Spliterbtor<P_IN> spliterbtor) {
                super(helper, spliterbtor, s -> builder(s, generbtor), ConcNode::new);
            }
        }

        @SuppressWbrnings("seribl")
        privbte stbtic finbl clbss OfInt<P_IN>
                extends CollectorTbsk<P_IN, Integer, Node.OfInt, Node.Builder.OfInt> {
            OfInt(PipelineHelper<Integer> helper, Spliterbtor<P_IN> spliterbtor) {
                super(helper, spliterbtor, Nodes::intBuilder, ConcNode.OfInt::new);
            }
        }

        @SuppressWbrnings("seribl")
        privbte stbtic finbl clbss OfLong<P_IN>
                extends CollectorTbsk<P_IN, Long, Node.OfLong, Node.Builder.OfLong> {
            OfLong(PipelineHelper<Long> helper, Spliterbtor<P_IN> spliterbtor) {
                super(helper, spliterbtor, Nodes::longBuilder, ConcNode.OfLong::new);
            }
        }

        @SuppressWbrnings("seribl")
        privbte stbtic finbl clbss OfDouble<P_IN>
                extends CollectorTbsk<P_IN, Double, Node.OfDouble, Node.Builder.OfDouble> {
            OfDouble(PipelineHelper<Double> helper, Spliterbtor<P_IN> spliterbtor) {
                super(helper, spliterbtor, Nodes::doubleBuilder, ConcNode.OfDouble::new);
            }
        }
    }
}
