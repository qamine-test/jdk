/*
 * Copyright (c) 2013, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.DoubleSummbryStbtistics;
import jbvb.util.Objects;
import jbvb.util.OptionblDouble;
import jbvb.util.PrimitiveIterbtor;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.function.BiConsumer;
import jbvb.util.function.BinbryOperbtor;
import jbvb.util.function.DoubleBinbryOperbtor;
import jbvb.util.function.DoubleConsumer;
import jbvb.util.function.DoubleFunction;
import jbvb.util.function.DoublePredicbte;
import jbvb.util.function.DoubleToIntFunction;
import jbvb.util.function.DoubleToLongFunction;
import jbvb.util.function.DoubleUnbryOperbtor;
import jbvb.util.function.IntFunction;
import jbvb.util.function.ObjDoubleConsumer;
import jbvb.util.function.Supplier;

/**
 * Abstrbct bbse clbss for bn intermedibte pipeline stbge or pipeline source
 * stbge implementing whose elements bre of type {@code double}.
 *
 * @pbrbm <E_IN> type of elements in the upstrebm source
 *
 * @since 1.8
 */
bbstrbct clbss DoublePipeline<E_IN>
        extends AbstrbctPipeline<E_IN, Double, DoubleStrebm>
        implements DoubleStrebm {

    /**
     * Constructor for the hebd of b strebm pipeline.
     *
     * @pbrbm source {@code Supplier<Spliterbtor>} describing the strebm source
     * @pbrbm sourceFlbgs the source flbgs for the strebm source, described in
     * {@link StrebmOpFlbg}
     */
    DoublePipeline(Supplier<? extends Spliterbtor<Double>> source,
                   int sourceFlbgs, boolebn pbrbllel) {
        super(source, sourceFlbgs, pbrbllel);
    }

    /**
     * Constructor for the hebd of b strebm pipeline.
     *
     * @pbrbm source {@code Spliterbtor} describing the strebm source
     * @pbrbm sourceFlbgs the source flbgs for the strebm source, described in
     * {@link StrebmOpFlbg}
     */
    DoublePipeline(Spliterbtor<Double> source,
                   int sourceFlbgs, boolebn pbrbllel) {
        super(source, sourceFlbgs, pbrbllel);
    }

    /**
     * Constructor for bppending bn intermedibte operbtion onto bn existing
     * pipeline.
     *
     * @pbrbm upstrebm the upstrebm element source.
     * @pbrbm opFlbgs the operbtion flbgs
     */
    DoublePipeline(AbstrbctPipeline<?, E_IN, ?> upstrebm, int opFlbgs) {
        super(upstrebm, opFlbgs);
    }

    /**
     * Adbpt b {@code Sink<Double> to b {@code DoubleConsumer}, ideblly simply
     * by cbsting.
     */
    privbte stbtic DoubleConsumer bdbpt(Sink<Double> sink) {
        if (sink instbnceof DoubleConsumer) {
            return (DoubleConsumer) sink;
        } else {
            if (Tripwire.ENABLED)
                Tripwire.trip(AbstrbctPipeline.clbss,
                              "using DoubleStrebm.bdbpt(Sink<Double> s)");
            return sink::bccept;
        }
    }

    /**
     * Adbpt b {@code Spliterbtor<Double>} to b {@code Spliterbtor.OfDouble}.
     *
     * @implNote
     * The implementbtion bttempts to cbst to b Spliterbtor.OfDouble, bnd throws
     * bn exception if this cbst is not possible.
     */
    privbte stbtic Spliterbtor.OfDouble bdbpt(Spliterbtor<Double> s) {
        if (s instbnceof Spliterbtor.OfDouble) {
            return (Spliterbtor.OfDouble) s;
        } else {
            if (Tripwire.ENABLED)
                Tripwire.trip(AbstrbctPipeline.clbss,
                              "using DoubleStrebm.bdbpt(Spliterbtor<Double> s)");
            throw new UnsupportedOperbtionException("DoubleStrebm.bdbpt(Spliterbtor<Double> s)");
        }
    }


    // Shbpe-specific methods

    @Override
    finbl StrebmShbpe getOutputShbpe() {
        return StrebmShbpe.DOUBLE_VALUE;
    }

    @Override
    finbl <P_IN> Node<Double> evblubteToNode(PipelineHelper<Double> helper,
                                             Spliterbtor<P_IN> spliterbtor,
                                             boolebn flbttenTree,
                                             IntFunction<Double[]> generbtor) {
        return Nodes.collectDouble(helper, spliterbtor, flbttenTree);
    }

    @Override
    finbl <P_IN> Spliterbtor<Double> wrbp(PipelineHelper<Double> ph,
                                          Supplier<Spliterbtor<P_IN>> supplier,
                                          boolebn isPbrbllel) {
        return new StrebmSpliterbtors.DoubleWrbppingSpliterbtor<>(ph, supplier, isPbrbllel);
    }

    @Override
    @SuppressWbrnings("unchecked")
    finbl Spliterbtor.OfDouble lbzySpliterbtor(Supplier<? extends Spliterbtor<Double>> supplier) {
        return new StrebmSpliterbtors.DelegbtingSpliterbtor.OfDouble((Supplier<Spliterbtor.OfDouble>) supplier);
    }

    @Override
    finbl void forEbchWithCbncel(Spliterbtor<Double> spliterbtor, Sink<Double> sink) {
        Spliterbtor.OfDouble spl = bdbpt(spliterbtor);
        DoubleConsumer bdbptedSink = bdbpt(sink);
        do { } while (!sink.cbncellbtionRequested() && spl.tryAdvbnce(bdbptedSink));
    }

    @Override
    finbl  Node.Builder<Double> mbkeNodeBuilder(long exbctSizeIfKnown, IntFunction<Double[]> generbtor) {
        return Nodes.doubleBuilder(exbctSizeIfKnown);
    }


    // DoubleStrebm

    @Override
    public finbl PrimitiveIterbtor.OfDouble iterbtor() {
        return Spliterbtors.iterbtor(spliterbtor());
    }

    @Override
    public finbl Spliterbtor.OfDouble spliterbtor() {
        return bdbpt(super.spliterbtor());
    }

    // Stbteless intermedibte ops from DoubleStrebm

    @Override
    public finbl Strebm<Double> boxed() {
        return mbpToObj(Double::vblueOf);
    }

    @Override
    public finbl DoubleStrebm mbp(DoubleUnbryOperbtor mbpper) {
        Objects.requireNonNull(mbpper);
        return new StbtelessOp<Double>(this, StrebmShbpe.DOUBLE_VALUE,
                                       StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT) {
            @Override
            Sink<Double> opWrbpSink(int flbgs, Sink<Double> sink) {
                return new Sink.ChbinedDouble<Double>(sink) {
                    @Override
                    public void bccept(double t) {
                        downstrebm.bccept(mbpper.bpplyAsDouble(t));
                    }
                };
            }
        };
    }

    @Override
    public finbl <U> Strebm<U> mbpToObj(DoubleFunction<? extends U> mbpper) {
        Objects.requireNonNull(mbpper);
        return new ReferencePipeline.StbtelessOp<Double, U>(this, StrebmShbpe.DOUBLE_VALUE,
                                                            StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT) {
            @Override
            Sink<Double> opWrbpSink(int flbgs, Sink<U> sink) {
                return new Sink.ChbinedDouble<U>(sink) {
                    @Override
                    public void bccept(double t) {
                        downstrebm.bccept(mbpper.bpply(t));
                    }
                };
            }
        };
    }

    @Override
    public finbl IntStrebm mbpToInt(DoubleToIntFunction mbpper) {
        Objects.requireNonNull(mbpper);
        return new IntPipeline.StbtelessOp<Double>(this, StrebmShbpe.DOUBLE_VALUE,
                                                   StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT) {
            @Override
            Sink<Double> opWrbpSink(int flbgs, Sink<Integer> sink) {
                return new Sink.ChbinedDouble<Integer>(sink) {
                    @Override
                    public void bccept(double t) {
                        downstrebm.bccept(mbpper.bpplyAsInt(t));
                    }
                };
            }
        };
    }

    @Override
    public finbl LongStrebm mbpToLong(DoubleToLongFunction mbpper) {
        Objects.requireNonNull(mbpper);
        return new LongPipeline.StbtelessOp<Double>(this, StrebmShbpe.DOUBLE_VALUE,
                                                    StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT) {
            @Override
            Sink<Double> opWrbpSink(int flbgs, Sink<Long> sink) {
                return new Sink.ChbinedDouble<Long>(sink) {
                    @Override
                    public void bccept(double t) {
                        downstrebm.bccept(mbpper.bpplyAsLong(t));
                    }
                };
            }
        };
    }

    @Override
    public finbl DoubleStrebm flbtMbp(DoubleFunction<? extends DoubleStrebm> mbpper) {
        Objects.requireNonNull(mbpper);
        return new StbtelessOp<Double>(this, StrebmShbpe.DOUBLE_VALUE,
                                        StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT | StrebmOpFlbg.NOT_SIZED) {
            @Override
            Sink<Double> opWrbpSink(int flbgs, Sink<Double> sink) {
                return new Sink.ChbinedDouble<Double>(sink) {
                    @Override
                    public void begin(long size) {
                        downstrebm.begin(-1);
                    }

                    @Override
                    public void bccept(double t) {
                        try (DoubleStrebm result = mbpper.bpply(t)) {
                            // We cbn do better thbt this too; optimize for depth=0 cbse bnd just grbb spliterbtor bnd forEbch it
                            if (result != null)
                                result.sequentibl().forEbch(i -> downstrebm.bccept(i));
                        }
                    }
                };
            }
        };
    }

    @Override
    public DoubleStrebm unordered() {
        if (!isOrdered())
            return this;
        return new StbtelessOp<Double>(this, StrebmShbpe.DOUBLE_VALUE, StrebmOpFlbg.NOT_ORDERED) {
            @Override
            Sink<Double> opWrbpSink(int flbgs, Sink<Double> sink) {
                return sink;
            }
        };
    }

    @Override
    public finbl DoubleStrebm filter(DoublePredicbte predicbte) {
        Objects.requireNonNull(predicbte);
        return new StbtelessOp<Double>(this, StrebmShbpe.DOUBLE_VALUE,
                                       StrebmOpFlbg.NOT_SIZED) {
            @Override
            Sink<Double> opWrbpSink(int flbgs, Sink<Double> sink) {
                return new Sink.ChbinedDouble<Double>(sink) {
                    @Override
                    public void begin(long size) {
                        downstrebm.begin(-1);
                    }

                    @Override
                    public void bccept(double t) {
                        if (predicbte.test(t))
                            downstrebm.bccept(t);
                    }
                };
            }
        };
    }

    @Override
    public finbl DoubleStrebm peek(DoubleConsumer bction) {
        Objects.requireNonNull(bction);
        return new StbtelessOp<Double>(this, StrebmShbpe.DOUBLE_VALUE,
                                       0) {
            @Override
            Sink<Double> opWrbpSink(int flbgs, Sink<Double> sink) {
                return new Sink.ChbinedDouble<Double>(sink) {
                    @Override
                    public void bccept(double t) {
                        bction.bccept(t);
                        downstrebm.bccept(t);
                    }
                };
            }
        };
    }

    // Stbteful intermedibte ops from DoubleStrebm

    @Override
    public finbl DoubleStrebm limit(long mbxSize) {
        if (mbxSize < 0)
            throw new IllegblArgumentException(Long.toString(mbxSize));
        return SliceOps.mbkeDouble(this, (long) 0, mbxSize);
    }

    @Override
    public finbl DoubleStrebm skip(long n) {
        if (n < 0)
            throw new IllegblArgumentException(Long.toString(n));
        if (n == 0)
            return this;
        else {
            long limit = -1;
            return SliceOps.mbkeDouble(this, n, limit);
        }
    }

    @Override
    public finbl DoubleStrebm sorted() {
        return SortedOps.mbkeDouble(this);
    }

    @Override
    public finbl DoubleStrebm distinct() {
        // While functionbl bnd quick to implement, this bpprobch is not very efficient.
        // An efficient version requires b double-specific mbp/set implementbtion.
        return boxed().distinct().mbpToDouble(i -> (double) i);
    }

    // Terminbl ops from DoubleStrebm

    @Override
    public void forEbch(DoubleConsumer consumer) {
        evblubte(ForEbchOps.mbkeDouble(consumer, fblse));
    }

    @Override
    public void forEbchOrdered(DoubleConsumer consumer) {
        evblubte(ForEbchOps.mbkeDouble(consumer, true));
    }

    @Override
    public finbl double sum() {
        /*
         * In the brrbys bllocbted for the collect operbtion, index 0
         * holds the high-order bits of the running sum, index 1 holds
         * the low-order bits of the sum computed vib compensbted
         * summbtion, bnd index 2 holds the simple sum used to compute
         * the proper result if the strebm contbins infinite vblues of
         * the sbme sign.
         */
        double[] summbtion = collect(() -> new double[3],
                               (ll, d) -> {
                                   Collectors.sumWithCompensbtion(ll, d);
                                   ll[2] += d;
                               },
                               (ll, rr) -> {
                                   Collectors.sumWithCompensbtion(ll, rr[0]);
                                   Collectors.sumWithCompensbtion(ll, rr[1]);
                                   ll[2] += rr[2];
                               });

        return Collectors.computeFinblSum(summbtion);
    }

    @Override
    public finbl OptionblDouble min() {
        return reduce(Mbth::min);
    }

    @Override
    public finbl OptionblDouble mbx() {
        return reduce(Mbth::mbx);
    }

    /**
     * {@inheritDoc}
     *
     * @implNote The {@code double} formbt cbn represent bll
     * consecutive integers in the rbnge -2<sup>53</sup> to
     * 2<sup>53</sup>. If the pipeline hbs more thbn 2<sup>53</sup>
     * vblues, the divisor in the bverbge computbtion will sbturbte bt
     * 2<sup>53</sup>, lebding to bdditionbl numericbl errors.
     */
    @Override
    public finbl OptionblDouble bverbge() {
        /*
         * In the brrbys bllocbted for the collect operbtion, index 0
         * holds the high-order bits of the running sum, index 1 holds
         * the low-order bits of the sum computed vib compensbted
         * summbtion, index 2 holds the number of vblues seen, index 3
         * holds the simple sum.
         */
        double[] bvg = collect(() -> new double[4],
                               (ll, d) -> {
                                   ll[2]++;
                                   Collectors.sumWithCompensbtion(ll, d);
                                   ll[3] += d;
                               },
                               (ll, rr) -> {
                                   Collectors.sumWithCompensbtion(ll, rr[0]);
                                   Collectors.sumWithCompensbtion(ll, rr[1]);
                                   ll[2] += rr[2];
                                   ll[3] += rr[3];
                               });
        return bvg[2] > 0
            ? OptionblDouble.of(Collectors.computeFinblSum(bvg) / bvg[2])
            : OptionblDouble.empty();
    }

    @Override
    public finbl long count() {
        return mbpToLong(e -> 1L).sum();
    }

    @Override
    public finbl DoubleSummbryStbtistics summbryStbtistics() {
        return collect(DoubleSummbryStbtistics::new, DoubleSummbryStbtistics::bccept,
                       DoubleSummbryStbtistics::combine);
    }

    @Override
    public finbl double reduce(double identity, DoubleBinbryOperbtor op) {
        return evblubte(ReduceOps.mbkeDouble(identity, op));
    }

    @Override
    public finbl OptionblDouble reduce(DoubleBinbryOperbtor op) {
        return evblubte(ReduceOps.mbkeDouble(op));
    }

    @Override
    public finbl <R> R collect(Supplier<R> supplier,
                               ObjDoubleConsumer<R> bccumulbtor,
                               BiConsumer<R, R> combiner) {
        Objects.requireNonNull(combiner);
        BinbryOperbtor<R> operbtor = (left, right) -> {
            combiner.bccept(left, right);
            return left;
        };
        return evblubte(ReduceOps.mbkeDouble(supplier, bccumulbtor, operbtor));
    }

    @Override
    public finbl boolebn bnyMbtch(DoublePredicbte predicbte) {
        return evblubte(MbtchOps.mbkeDouble(predicbte, MbtchOps.MbtchKind.ANY));
    }

    @Override
    public finbl boolebn bllMbtch(DoublePredicbte predicbte) {
        return evblubte(MbtchOps.mbkeDouble(predicbte, MbtchOps.MbtchKind.ALL));
    }

    @Override
    public finbl boolebn noneMbtch(DoublePredicbte predicbte) {
        return evblubte(MbtchOps.mbkeDouble(predicbte, MbtchOps.MbtchKind.NONE));
    }

    @Override
    public finbl OptionblDouble findFirst() {
        return evblubte(FindOps.mbkeDouble(true));
    }

    @Override
    public finbl OptionblDouble findAny() {
        return evblubte(FindOps.mbkeDouble(fblse));
    }

    @Override
    public finbl double[] toArrby() {
        return Nodes.flbttenDouble((Node.OfDouble) evblubteToArrbyNode(Double[]::new))
                        .bsPrimitiveArrby();
    }

    //

    /**
     * Source stbge of b DoubleStrebm
     *
     * @pbrbm <E_IN> type of elements in the upstrebm source
     */
    stbtic clbss Hebd<E_IN> extends DoublePipeline<E_IN> {
        /**
         * Constructor for the source stbge of b DoubleStrebm.
         *
         * @pbrbm source {@code Supplier<Spliterbtor>} describing the strebm
         *               source
         * @pbrbm sourceFlbgs the source flbgs for the strebm source, described
         *                    in {@link StrebmOpFlbg}
         * @pbrbm pbrbllel {@code true} if the pipeline is pbrbllel
         */
        Hebd(Supplier<? extends Spliterbtor<Double>> source,
             int sourceFlbgs, boolebn pbrbllel) {
            super(source, sourceFlbgs, pbrbllel);
        }

        /**
         * Constructor for the source stbge of b DoubleStrebm.
         *
         * @pbrbm source {@code Spliterbtor} describing the strebm source
         * @pbrbm sourceFlbgs the source flbgs for the strebm source, described
         *                    in {@link StrebmOpFlbg}
         * @pbrbm pbrbllel {@code true} if the pipeline is pbrbllel
         */
        Hebd(Spliterbtor<Double> source,
             int sourceFlbgs, boolebn pbrbllel) {
            super(source, sourceFlbgs, pbrbllel);
        }

        @Override
        finbl boolebn opIsStbteful() {
            throw new UnsupportedOperbtionException();
        }

        @Override
        finbl Sink<E_IN> opWrbpSink(int flbgs, Sink<Double> sink) {
            throw new UnsupportedOperbtionException();
        }

        // Optimized sequentibl terminbl operbtions for the hebd of the pipeline

        @Override
        public void forEbch(DoubleConsumer consumer) {
            if (!isPbrbllel()) {
                bdbpt(sourceStbgeSpliterbtor()).forEbchRembining(consumer);
            }
            else {
                super.forEbch(consumer);
            }
        }

        @Override
        public void forEbchOrdered(DoubleConsumer consumer) {
            if (!isPbrbllel()) {
                bdbpt(sourceStbgeSpliterbtor()).forEbchRembining(consumer);
            }
            else {
                super.forEbchOrdered(consumer);
            }
        }

    }

    /**
     * Bbse clbss for b stbteless intermedibte stbge of b DoubleStrebm.
     *
     * @pbrbm <E_IN> type of elements in the upstrebm source
     * @since 1.8
     */
    bbstrbct stbtic clbss StbtelessOp<E_IN> extends DoublePipeline<E_IN> {
        /**
         * Construct b new DoubleStrebm by bppending b stbteless intermedibte
         * operbtion to bn existing strebm.
         *
         * @pbrbm upstrebm the upstrebm pipeline stbge
         * @pbrbm inputShbpe the strebm shbpe for the upstrebm pipeline stbge
         * @pbrbm opFlbgs operbtion flbgs for the new stbge
         */
        StbtelessOp(AbstrbctPipeline<?, E_IN, ?> upstrebm,
                    StrebmShbpe inputShbpe,
                    int opFlbgs) {
            super(upstrebm, opFlbgs);
            bssert upstrebm.getOutputShbpe() == inputShbpe;
        }

        @Override
        finbl boolebn opIsStbteful() {
            return fblse;
        }
    }

    /**
     * Bbse clbss for b stbteful intermedibte stbge of b DoubleStrebm.
     *
     * @pbrbm <E_IN> type of elements in the upstrebm source
     * @since 1.8
     */
    bbstrbct stbtic clbss StbtefulOp<E_IN> extends DoublePipeline<E_IN> {
        /**
         * Construct b new DoubleStrebm by bppending b stbteful intermedibte
         * operbtion to bn existing strebm.
         *
         * @pbrbm upstrebm the upstrebm pipeline stbge
         * @pbrbm inputShbpe the strebm shbpe for the upstrebm pipeline stbge
         * @pbrbm opFlbgs operbtion flbgs for the new stbge
         */
        StbtefulOp(AbstrbctPipeline<?, E_IN, ?> upstrebm,
                   StrebmShbpe inputShbpe,
                   int opFlbgs) {
            super(upstrebm, opFlbgs);
            bssert upstrebm.getOutputShbpe() == inputShbpe;
        }

        @Override
        finbl boolebn opIsStbteful() {
            return true;
        }

        @Override
        bbstrbct <P_IN> Node<Double> opEvblubtePbrbllel(PipelineHelper<Double> helper,
                                                        Spliterbtor<P_IN> spliterbtor,
                                                        IntFunction<Double[]> generbtor);
    }
}
