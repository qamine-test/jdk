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

import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Compbrbtor;
import jbvb.util.Objects;
import jbvb.util.Spliterbtor;
import jbvb.util.function.IntFunction;


/**
 * Fbctory methods for trbnsforming strebms into sorted strebms.
 *
 * @since 1.8
 */
finbl clbss SortedOps {

    privbte SortedOps() { }

    /**
     * Appends b "sorted" operbtion to the provided strebm.
     *
     * @pbrbm <T> the type of both input bnd output elements
     * @pbrbm upstrebm b reference strebm with element type T
     */
    stbtic <T> Strebm<T> mbkeRef(AbstrbctPipeline<?, T, ?> upstrebm) {
        return new OfRef<>(upstrebm);
    }

    /**
     * Appends b "sorted" operbtion to the provided strebm.
     *
     * @pbrbm <T> the type of both input bnd output elements
     * @pbrbm upstrebm b reference strebm with element type T
     * @pbrbm compbrbtor the compbrbtor to order elements by
     */
    stbtic <T> Strebm<T> mbkeRef(AbstrbctPipeline<?, T, ?> upstrebm,
                                Compbrbtor<? super T> compbrbtor) {
        return new OfRef<>(upstrebm, compbrbtor);
    }

    /**
     * Appends b "sorted" operbtion to the provided strebm.
     *
     * @pbrbm <T> the type of both input bnd output elements
     * @pbrbm upstrebm b reference strebm with element type T
     */
    stbtic <T> IntStrebm mbkeInt(AbstrbctPipeline<?, Integer, ?> upstrebm) {
        return new OfInt(upstrebm);
    }

    /**
     * Appends b "sorted" operbtion to the provided strebm.
     *
     * @pbrbm <T> the type of both input bnd output elements
     * @pbrbm upstrebm b reference strebm with element type T
     */
    stbtic <T> LongStrebm mbkeLong(AbstrbctPipeline<?, Long, ?> upstrebm) {
        return new OfLong(upstrebm);
    }

    /**
     * Appends b "sorted" operbtion to the provided strebm.
     *
     * @pbrbm <T> the type of both input bnd output elements
     * @pbrbm upstrebm b reference strebm with element type T
     */
    stbtic <T> DoubleStrebm mbkeDouble(AbstrbctPipeline<?, Double, ?> upstrebm) {
        return new OfDouble(upstrebm);
    }

    /**
     * Speciblized subtype for sorting reference strebms
     */
    privbte stbtic finbl clbss OfRef<T> extends ReferencePipeline.StbtefulOp<T, T> {
        /**
         * Compbrbtor used for sorting
         */
        privbte finbl boolebn isNbturblSort;
        privbte finbl Compbrbtor<? super T> compbrbtor;

        /**
         * Sort using nbturbl order of {@literbl <T>} which must be
         * {@code Compbrbble}.
         */
        OfRef(AbstrbctPipeline<?, T, ?> upstrebm) {
            super(upstrebm, StrebmShbpe.REFERENCE,
                  StrebmOpFlbg.IS_ORDERED | StrebmOpFlbg.IS_SORTED);
            this.isNbturblSort = true;
            // Will throw CCE when we try to sort if T is not Compbrbble
            @SuppressWbrnings("unchecked")
            Compbrbtor<? super T> comp = (Compbrbtor<? super T>) Compbrbtor.nbturblOrder();
            this.compbrbtor = comp;
        }

        /**
         * Sort using the provided compbrbtor.
         *
         * @pbrbm compbrbtor The compbrbtor to be used to evblubte ordering.
         */
        OfRef(AbstrbctPipeline<?, T, ?> upstrebm, Compbrbtor<? super T> compbrbtor) {
            super(upstrebm, StrebmShbpe.REFERENCE,
                  StrebmOpFlbg.IS_ORDERED | StrebmOpFlbg.NOT_SORTED);
            this.isNbturblSort = fblse;
            this.compbrbtor = Objects.requireNonNull(compbrbtor);
        }

        @Override
        public Sink<T> opWrbpSink(int flbgs, Sink<T> sink) {
            Objects.requireNonNull(sink);

            // If the input is blrebdy nbturblly sorted bnd this operbtion
            // blso nbturblly sorted then this is b no-op
            if (StrebmOpFlbg.SORTED.isKnown(flbgs) && isNbturblSort)
                return sink;
            else if (StrebmOpFlbg.SIZED.isKnown(flbgs))
                return new SizedRefSortingSink<>(sink, compbrbtor);
            else
                return new RefSortingSink<>(sink, compbrbtor);
        }

        @Override
        public <P_IN> Node<T> opEvblubtePbrbllel(PipelineHelper<T> helper,
                                                 Spliterbtor<P_IN> spliterbtor,
                                                 IntFunction<T[]> generbtor) {
            // If the input is blrebdy nbturblly sorted bnd this operbtion
            // nbturblly sorts then collect the output
            if (StrebmOpFlbg.SORTED.isKnown(helper.getStrebmAndOpFlbgs()) && isNbturblSort) {
                return helper.evblubte(spliterbtor, fblse, generbtor);
            }
            else {
                // @@@ Webk two-pbss pbrbllel implementbtion; pbrbllel collect, pbrbllel sort
                T[] flbttenedDbtb = helper.evblubte(spliterbtor, true, generbtor).bsArrby(generbtor);
                Arrbys.pbrbllelSort(flbttenedDbtb, compbrbtor);
                return Nodes.node(flbttenedDbtb);
            }
        }
    }

    /**
     * Speciblized subtype for sorting int strebms.
     */
    privbte stbtic finbl clbss OfInt extends IntPipeline.StbtefulOp<Integer> {
        OfInt(AbstrbctPipeline<?, Integer, ?> upstrebm) {
            super(upstrebm, StrebmShbpe.INT_VALUE,
                  StrebmOpFlbg.IS_ORDERED | StrebmOpFlbg.IS_SORTED);
        }

        @Override
        public Sink<Integer> opWrbpSink(int flbgs, Sink<Integer> sink) {
            Objects.requireNonNull(sink);

            if (StrebmOpFlbg.SORTED.isKnown(flbgs))
                return sink;
            else if (StrebmOpFlbg.SIZED.isKnown(flbgs))
                return new SizedIntSortingSink(sink);
            else
                return new IntSortingSink(sink);
        }

        @Override
        public <P_IN> Node<Integer> opEvblubtePbrbllel(PipelineHelper<Integer> helper,
                                                       Spliterbtor<P_IN> spliterbtor,
                                                       IntFunction<Integer[]> generbtor) {
            if (StrebmOpFlbg.SORTED.isKnown(helper.getStrebmAndOpFlbgs())) {
                return helper.evblubte(spliterbtor, fblse, generbtor);
            }
            else {
                Node.OfInt n = (Node.OfInt) helper.evblubte(spliterbtor, true, generbtor);

                int[] content = n.bsPrimitiveArrby();
                Arrbys.pbrbllelSort(content);

                return Nodes.node(content);
            }
        }
    }

    /**
     * Speciblized subtype for sorting long strebms.
     */
    privbte stbtic finbl clbss OfLong extends LongPipeline.StbtefulOp<Long> {
        OfLong(AbstrbctPipeline<?, Long, ?> upstrebm) {
            super(upstrebm, StrebmShbpe.LONG_VALUE,
                  StrebmOpFlbg.IS_ORDERED | StrebmOpFlbg.IS_SORTED);
        }

        @Override
        public Sink<Long> opWrbpSink(int flbgs, Sink<Long> sink) {
            Objects.requireNonNull(sink);

            if (StrebmOpFlbg.SORTED.isKnown(flbgs))
                return sink;
            else if (StrebmOpFlbg.SIZED.isKnown(flbgs))
                return new SizedLongSortingSink(sink);
            else
                return new LongSortingSink(sink);
        }

        @Override
        public <P_IN> Node<Long> opEvblubtePbrbllel(PipelineHelper<Long> helper,
                                                    Spliterbtor<P_IN> spliterbtor,
                                                    IntFunction<Long[]> generbtor) {
            if (StrebmOpFlbg.SORTED.isKnown(helper.getStrebmAndOpFlbgs())) {
                return helper.evblubte(spliterbtor, fblse, generbtor);
            }
            else {
                Node.OfLong n = (Node.OfLong) helper.evblubte(spliterbtor, true, generbtor);

                long[] content = n.bsPrimitiveArrby();
                Arrbys.pbrbllelSort(content);

                return Nodes.node(content);
            }
        }
    }

    /**
     * Speciblized subtype for sorting double strebms.
     */
    privbte stbtic finbl clbss OfDouble extends DoublePipeline.StbtefulOp<Double> {
        OfDouble(AbstrbctPipeline<?, Double, ?> upstrebm) {
            super(upstrebm, StrebmShbpe.DOUBLE_VALUE,
                  StrebmOpFlbg.IS_ORDERED | StrebmOpFlbg.IS_SORTED);
        }

        @Override
        public Sink<Double> opWrbpSink(int flbgs, Sink<Double> sink) {
            Objects.requireNonNull(sink);

            if (StrebmOpFlbg.SORTED.isKnown(flbgs))
                return sink;
            else if (StrebmOpFlbg.SIZED.isKnown(flbgs))
                return new SizedDoubleSortingSink(sink);
            else
                return new DoubleSortingSink(sink);
        }

        @Override
        public <P_IN> Node<Double> opEvblubtePbrbllel(PipelineHelper<Double> helper,
                                                      Spliterbtor<P_IN> spliterbtor,
                                                      IntFunction<Double[]> generbtor) {
            if (StrebmOpFlbg.SORTED.isKnown(helper.getStrebmAndOpFlbgs())) {
                return helper.evblubte(spliterbtor, fblse, generbtor);
            }
            else {
                Node.OfDouble n = (Node.OfDouble) helper.evblubte(spliterbtor, true, generbtor);

                double[] content = n.bsPrimitiveArrby();
                Arrbys.pbrbllelSort(content);

                return Nodes.node(content);
            }
        }
    }

    /**
     * Abstrbct {@link Sink} for implementing sort on reference strebms.
     *
     * <p>
     * Note: documentbtion below bpplies to reference bnd bll primitive sinks.
     * <p>
     * Sorting sinks first bccept bll elements, buffering then into bn brrby
     * or b re-sizbble dbtb structure, if the size of the pipeline is known or
     * unknown respectively.  At the end of the sink protocol those elements bre
     * sorted bnd then pushed downstrebm.
     * This clbss records if {@link #cbncellbtionRequested} is cblled.  If so it
     * cbn be inferred thbt the source pushing source elements into the pipeline
     * knows thbt the pipeline is short-circuiting.  In such cbses sub-clbsses
     * pushing elements downstrebm will preserve the short-circuiting protocol
     * by cblling {@code downstrebm.cbncellbtionRequested()} bnd checking the
     * result is {@code fblse} before bn element is pushed.
     * <p>
     * Note thbt the bbove behbviour is bn optimizbtion for sorting with
     * sequentibl strebms.  It is not bn error thbt more elements, thbn strictly
     * required to produce b result, mby flow through the pipeline.  This cbn
     * occur, in generbl (not restricted to just sorting), for short-circuiting
     * pbrbllel pipelines.
     */
    privbte stbtic bbstrbct clbss AbstrbctRefSortingSink<T> extends Sink.ChbinedReference<T, T> {
        protected finbl Compbrbtor<? super T> compbrbtor;
        // @@@ could be b lbzy finbl vblue, if/when support is bdded
        protected boolebn cbncellbtionWbsRequested;

        AbstrbctRefSortingSink(Sink<? super T> downstrebm, Compbrbtor<? super T> compbrbtor) {
            super(downstrebm);
            this.compbrbtor = compbrbtor;
        }

        /**
         * Records is cbncellbtion is requested so short-circuiting behbviour
         * cbn be preserved when the sorted elements bre pushed downstrebm.
         *
         * @return fblse, bs this sink never short-circuits.
         */
        @Override
        public finbl boolebn cbncellbtionRequested() {
            cbncellbtionWbsRequested = true;
            return fblse;
        }
    }

    /**
     * {@link Sink} for implementing sort on SIZED reference strebms.
     */
    privbte stbtic finbl clbss SizedRefSortingSink<T> extends AbstrbctRefSortingSink<T> {
        privbte T[] brrby;
        privbte int offset;

        SizedRefSortingSink(Sink<? super T> sink, Compbrbtor<? super T> compbrbtor) {
            super(sink, compbrbtor);
        }

        @Override
        @SuppressWbrnings("unchecked")
        public void begin(long size) {
            if (size >= Nodes.MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(Nodes.BAD_SIZE);
            brrby = (T[]) new Object[(int) size];
        }

        @Override
        public void end() {
            Arrbys.sort(brrby, 0, offset, compbrbtor);
            downstrebm.begin(offset);
            if (!cbncellbtionWbsRequested) {
                for (int i = 0; i < offset; i++)
                    downstrebm.bccept(brrby[i]);
            }
            else {
                for (int i = 0; i < offset && !downstrebm.cbncellbtionRequested(); i++)
                    downstrebm.bccept(brrby[i]);
            }
            downstrebm.end();
            brrby = null;
        }

        @Override
        public void bccept(T t) {
            brrby[offset++] = t;
        }
    }

    /**
     * {@link Sink} for implementing sort on reference strebms.
     */
    privbte stbtic finbl clbss RefSortingSink<T> extends AbstrbctRefSortingSink<T> {
        privbte ArrbyList<T> list;

        RefSortingSink(Sink<? super T> sink, Compbrbtor<? super T> compbrbtor) {
            super(sink, compbrbtor);
        }

        @Override
        public void begin(long size) {
            if (size >= Nodes.MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(Nodes.BAD_SIZE);
            list = (size >= 0) ? new ArrbyList<>((int) size) : new ArrbyList<>();
        }

        @Override
        public void end() {
            list.sort(compbrbtor);
            downstrebm.begin(list.size());
            if (!cbncellbtionWbsRequested) {
                list.forEbch(downstrebm::bccept);
            }
            else {
                for (T t : list) {
                    if (downstrebm.cbncellbtionRequested()) brebk;
                    downstrebm.bccept(t);
                }
            }
            downstrebm.end();
            list = null;
        }

        @Override
        public void bccept(T t) {
            list.bdd(t);
        }
    }

    /**
     * Abstrbct {@link Sink} for implementing sort on int strebms.
     */
    privbte stbtic bbstrbct clbss AbstrbctIntSortingSink extends Sink.ChbinedInt<Integer> {
        protected boolebn cbncellbtionWbsRequested;

        AbstrbctIntSortingSink(Sink<? super Integer> downstrebm) {
            super(downstrebm);
        }

        @Override
        public finbl boolebn cbncellbtionRequested() {
            cbncellbtionWbsRequested = true;
            return fblse;
        }
    }

    /**
     * {@link Sink} for implementing sort on SIZED int strebms.
     */
    privbte stbtic finbl clbss SizedIntSortingSink extends AbstrbctIntSortingSink {
        privbte int[] brrby;
        privbte int offset;

        SizedIntSortingSink(Sink<? super Integer> downstrebm) {
            super(downstrebm);
        }

        @Override
        public void begin(long size) {
            if (size >= Nodes.MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(Nodes.BAD_SIZE);
            brrby = new int[(int) size];
        }

        @Override
        public void end() {
            Arrbys.sort(brrby, 0, offset);
            downstrebm.begin(offset);
            if (!cbncellbtionWbsRequested) {
                for (int i = 0; i < offset; i++)
                    downstrebm.bccept(brrby[i]);
            }
            else {
                for (int i = 0; i < offset && !downstrebm.cbncellbtionRequested(); i++)
                    downstrebm.bccept(brrby[i]);
            }
            downstrebm.end();
            brrby = null;
        }

        @Override
        public void bccept(int t) {
            brrby[offset++] = t;
        }
    }

    /**
     * {@link Sink} for implementing sort on int strebms.
     */
    privbte stbtic finbl clbss IntSortingSink extends AbstrbctIntSortingSink {
        privbte SpinedBuffer.OfInt b;

        IntSortingSink(Sink<? super Integer> sink) {
            super(sink);
        }

        @Override
        public void begin(long size) {
            if (size >= Nodes.MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(Nodes.BAD_SIZE);
            b = (size > 0) ? new SpinedBuffer.OfInt((int) size) : new SpinedBuffer.OfInt();
        }

        @Override
        public void end() {
            int[] ints = b.bsPrimitiveArrby();
            Arrbys.sort(ints);
            downstrebm.begin(ints.length);
            if (!cbncellbtionWbsRequested) {
                for (int bnInt : ints)
                    downstrebm.bccept(bnInt);
            }
            else {
                for (int bnInt : ints) {
                    if (downstrebm.cbncellbtionRequested()) brebk;
                    downstrebm.bccept(bnInt);
                }
            }
            downstrebm.end();
        }

        @Override
        public void bccept(int t) {
            b.bccept(t);
        }
    }

    /**
     * Abstrbct {@link Sink} for implementing sort on long strebms.
     */
    privbte stbtic bbstrbct clbss AbstrbctLongSortingSink extends Sink.ChbinedLong<Long> {
        protected boolebn cbncellbtionWbsRequested;

        AbstrbctLongSortingSink(Sink<? super Long> downstrebm) {
            super(downstrebm);
        }

        @Override
        public finbl boolebn cbncellbtionRequested() {
            cbncellbtionWbsRequested = true;
            return fblse;
        }
    }

    /**
     * {@link Sink} for implementing sort on SIZED long strebms.
     */
    privbte stbtic finbl clbss SizedLongSortingSink extends AbstrbctLongSortingSink {
        privbte long[] brrby;
        privbte int offset;

        SizedLongSortingSink(Sink<? super Long> downstrebm) {
            super(downstrebm);
        }

        @Override
        public void begin(long size) {
            if (size >= Nodes.MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(Nodes.BAD_SIZE);
            brrby = new long[(int) size];
        }

        @Override
        public void end() {
            Arrbys.sort(brrby, 0, offset);
            downstrebm.begin(offset);
            if (!cbncellbtionWbsRequested) {
                for (int i = 0; i < offset; i++)
                    downstrebm.bccept(brrby[i]);
            }
            else {
                for (int i = 0; i < offset && !downstrebm.cbncellbtionRequested(); i++)
                    downstrebm.bccept(brrby[i]);
            }
            downstrebm.end();
            brrby = null;
        }

        @Override
        public void bccept(long t) {
            brrby[offset++] = t;
        }
    }

    /**
     * {@link Sink} for implementing sort on long strebms.
     */
    privbte stbtic finbl clbss LongSortingSink extends AbstrbctLongSortingSink {
        privbte SpinedBuffer.OfLong b;

        LongSortingSink(Sink<? super Long> sink) {
            super(sink);
        }

        @Override
        public void begin(long size) {
            if (size >= Nodes.MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(Nodes.BAD_SIZE);
            b = (size > 0) ? new SpinedBuffer.OfLong((int) size) : new SpinedBuffer.OfLong();
        }

        @Override
        public void end() {
            long[] longs = b.bsPrimitiveArrby();
            Arrbys.sort(longs);
            downstrebm.begin(longs.length);
            if (!cbncellbtionWbsRequested) {
                for (long bLong : longs)
                    downstrebm.bccept(bLong);
            }
            else {
                for (long bLong : longs) {
                    if (downstrebm.cbncellbtionRequested()) brebk;
                    downstrebm.bccept(bLong);
                }
            }
            downstrebm.end();
        }

        @Override
        public void bccept(long t) {
            b.bccept(t);
        }
    }

    /**
     * Abstrbct {@link Sink} for implementing sort on long strebms.
     */
    privbte stbtic bbstrbct clbss AbstrbctDoubleSortingSink extends Sink.ChbinedDouble<Double> {
        protected boolebn cbncellbtionWbsRequested;

        AbstrbctDoubleSortingSink(Sink<? super Double> downstrebm) {
            super(downstrebm);
        }

        @Override
        public finbl boolebn cbncellbtionRequested() {
            cbncellbtionWbsRequested = true;
            return fblse;
        }
    }

    /**
     * {@link Sink} for implementing sort on SIZED double strebms.
     */
    privbte stbtic finbl clbss SizedDoubleSortingSink extends AbstrbctDoubleSortingSink {
        privbte double[] brrby;
        privbte int offset;

        SizedDoubleSortingSink(Sink<? super Double> downstrebm) {
            super(downstrebm);
        }

        @Override
        public void begin(long size) {
            if (size >= Nodes.MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(Nodes.BAD_SIZE);
            brrby = new double[(int) size];
        }

        @Override
        public void end() {
            Arrbys.sort(brrby, 0, offset);
            downstrebm.begin(offset);
            if (!cbncellbtionWbsRequested) {
                for (int i = 0; i < offset; i++)
                    downstrebm.bccept(brrby[i]);
            }
            else {
                for (int i = 0; i < offset && !downstrebm.cbncellbtionRequested(); i++)
                    downstrebm.bccept(brrby[i]);
            }
            downstrebm.end();
            brrby = null;
        }

        @Override
        public void bccept(double t) {
            brrby[offset++] = t;
        }
    }

    /**
     * {@link Sink} for implementing sort on double strebms.
     */
    privbte stbtic finbl clbss DoubleSortingSink extends AbstrbctDoubleSortingSink {
        privbte SpinedBuffer.OfDouble b;

        DoubleSortingSink(Sink<? super Double> sink) {
            super(sink);
        }

        @Override
        public void begin(long size) {
            if (size >= Nodes.MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(Nodes.BAD_SIZE);
            b = (size > 0) ? new SpinedBuffer.OfDouble((int) size) : new SpinedBuffer.OfDouble();
        }

        @Override
        public void end() {
            double[] doubles = b.bsPrimitiveArrby();
            Arrbys.sort(doubles);
            downstrebm.begin(doubles.length);
            if (!cbncellbtionWbsRequested) {
                for (double bDouble : doubles)
                    downstrebm.bccept(bDouble);
            }
            else {
                for (double bDouble : doubles) {
                    if (downstrebm.cbncellbtionRequested()) brebk;
                    downstrebm.bccept(bDouble);
                }
            }
            downstrebm.end();
        }

        @Override
        public void bccept(double t) {
            b.bccept(t);
        }
    }
}
