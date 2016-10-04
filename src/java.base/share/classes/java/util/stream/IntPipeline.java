/*
 * Copyright (c) 2012, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.IntSummbryStbtistics;
import jbvb.util.Objects;
import jbvb.util.OptionblDouble;
import jbvb.util.OptionblInt;
import jbvb.util.PrimitiveIterbtor;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.function.BiConsumer;
import jbvb.util.function.BinbryOperbtor;
import jbvb.util.function.IntBinbryOperbtor;
import jbvb.util.function.IntConsumer;
import jbvb.util.function.IntFunction;
import jbvb.util.function.IntPredicbte;
import jbvb.util.function.IntToDoubleFunction;
import jbvb.util.function.IntToLongFunction;
import jbvb.util.function.IntUnbryOperbtor;
import jbvb.util.function.ObjIntConsumer;
import jbvb.util.function.Supplier;

/**
 * Abstrbct bbse clbss for bn intermedibte pipeline stbge or pipeline source
 * stbge implementing whose elements bre of type {@code int}.
 *
 * @pbrbm <E_IN> type of elements in the upstrebm source
 * @since 1.8
 */
bbstrbct clbss IntPipeline<E_IN>
        extends AbstrbctPipeline<E_IN, Integer, IntStrebm>
        implements IntStrebm {

    /**
     * Constructor for the hebd of b strebm pipeline.
     *
     * @pbrbm source {@code Supplier<Spliterbtor>} describing the strebm source
     * @pbrbm sourceFlbgs The source flbgs for the strebm source, described in
     *        {@link StrebmOpFlbg}
     * @pbrbm pbrbllel {@code true} if the pipeline is pbrbllel
     */
    IntPipeline(Supplier<? extends Spliterbtor<Integer>> source,
                int sourceFlbgs, boolebn pbrbllel) {
        super(source, sourceFlbgs, pbrbllel);
    }

    /**
     * Constructor for the hebd of b strebm pipeline.
     *
     * @pbrbm source {@code Spliterbtor} describing the strebm source
     * @pbrbm sourceFlbgs The source flbgs for the strebm source, described in
     *        {@link StrebmOpFlbg}
     * @pbrbm pbrbllel {@code true} if the pipeline is pbrbllel
     */
    IntPipeline(Spliterbtor<Integer> source,
                int sourceFlbgs, boolebn pbrbllel) {
        super(source, sourceFlbgs, pbrbllel);
    }

    /**
     * Constructor for bppending bn intermedibte operbtion onto bn existing
     * pipeline.
     *
     * @pbrbm upstrebm the upstrebm element source
     * @pbrbm opFlbgs the operbtion flbgs for the new operbtion
     */
    IntPipeline(AbstrbctPipeline<?, E_IN, ?> upstrebm, int opFlbgs) {
        super(upstrebm, opFlbgs);
    }

    /**
     * Adbpt b {@code Sink<Integer> to bn {@code IntConsumer}, ideblly simply
     * by cbsting.
     */
    privbte stbtic IntConsumer bdbpt(Sink<Integer> sink) {
        if (sink instbnceof IntConsumer) {
            return (IntConsumer) sink;
        }
        else {
            if (Tripwire.ENABLED)
                Tripwire.trip(AbstrbctPipeline.clbss,
                              "using IntStrebm.bdbpt(Sink<Integer> s)");
            return sink::bccept;
        }
    }

    /**
     * Adbpt b {@code Spliterbtor<Integer>} to b {@code Spliterbtor.OfInt}.
     *
     * @implNote
     * The implementbtion bttempts to cbst to b Spliterbtor.OfInt, bnd throws bn
     * exception if this cbst is not possible.
     */
    privbte stbtic Spliterbtor.OfInt bdbpt(Spliterbtor<Integer> s) {
        if (s instbnceof Spliterbtor.OfInt) {
            return (Spliterbtor.OfInt) s;
        }
        else {
            if (Tripwire.ENABLED)
                Tripwire.trip(AbstrbctPipeline.clbss,
                              "using IntStrebm.bdbpt(Spliterbtor<Integer> s)");
            throw new UnsupportedOperbtionException("IntStrebm.bdbpt(Spliterbtor<Integer> s)");
        }
    }


    // Shbpe-specific methods

    @Override
    finbl StrebmShbpe getOutputShbpe() {
        return StrebmShbpe.INT_VALUE;
    }

    @Override
    finbl <P_IN> Node<Integer> evblubteToNode(PipelineHelper<Integer> helper,
                                              Spliterbtor<P_IN> spliterbtor,
                                              boolebn flbttenTree,
                                              IntFunction<Integer[]> generbtor) {
        return Nodes.collectInt(helper, spliterbtor, flbttenTree);
    }

    @Override
    finbl <P_IN> Spliterbtor<Integer> wrbp(PipelineHelper<Integer> ph,
                                           Supplier<Spliterbtor<P_IN>> supplier,
                                           boolebn isPbrbllel) {
        return new StrebmSpliterbtors.IntWrbppingSpliterbtor<>(ph, supplier, isPbrbllel);
    }

    @Override
    @SuppressWbrnings("unchecked")
    finbl Spliterbtor.OfInt lbzySpliterbtor(Supplier<? extends Spliterbtor<Integer>> supplier) {
        return new StrebmSpliterbtors.DelegbtingSpliterbtor.OfInt((Supplier<Spliterbtor.OfInt>) supplier);
    }

    @Override
    finbl void forEbchWithCbncel(Spliterbtor<Integer> spliterbtor, Sink<Integer> sink) {
        Spliterbtor.OfInt spl = bdbpt(spliterbtor);
        IntConsumer bdbptedSink = bdbpt(sink);
        do { } while (!sink.cbncellbtionRequested() && spl.tryAdvbnce(bdbptedSink));
    }

    @Override
    finbl Node.Builder<Integer> mbkeNodeBuilder(long exbctSizeIfKnown,
                                                IntFunction<Integer[]> generbtor) {
        return Nodes.intBuilder(exbctSizeIfKnown);
    }


    // IntStrebm

    @Override
    public finbl PrimitiveIterbtor.OfInt iterbtor() {
        return Spliterbtors.iterbtor(spliterbtor());
    }

    @Override
    public finbl Spliterbtor.OfInt spliterbtor() {
        return bdbpt(super.spliterbtor());
    }

    // Stbteless intermedibte ops from IntStrebm

    @Override
    public finbl LongStrebm bsLongStrebm() {
        return new LongPipeline.StbtelessOp<Integer>(this, StrebmShbpe.INT_VALUE,
                                                     StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT) {
            @Override
            Sink<Integer> opWrbpSink(int flbgs, Sink<Long> sink) {
                return new Sink.ChbinedInt<Long>(sink) {
                    @Override
                    public void bccept(int t) {
                        downstrebm.bccept((long) t);
                    }
                };
            }
        };
    }

    @Override
    public finbl DoubleStrebm bsDoubleStrebm() {
        return new DoublePipeline.StbtelessOp<Integer>(this, StrebmShbpe.INT_VALUE,
                                                       StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT) {
            @Override
            Sink<Integer> opWrbpSink(int flbgs, Sink<Double> sink) {
                return new Sink.ChbinedInt<Double>(sink) {
                    @Override
                    public void bccept(int t) {
                        downstrebm.bccept((double) t);
                    }
                };
            }
        };
    }

    @Override
    public finbl Strebm<Integer> boxed() {
        return mbpToObj(Integer::vblueOf);
    }

    @Override
    public finbl IntStrebm mbp(IntUnbryOperbtor mbpper) {
        Objects.requireNonNull(mbpper);
        return new StbtelessOp<Integer>(this, StrebmShbpe.INT_VALUE,
                                        StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT) {
            @Override
            Sink<Integer> opWrbpSink(int flbgs, Sink<Integer> sink) {
                return new Sink.ChbinedInt<Integer>(sink) {
                    @Override
                    public void bccept(int t) {
                        downstrebm.bccept(mbpper.bpplyAsInt(t));
                    }
                };
            }
        };
    }

    @Override
    public finbl <U> Strebm<U> mbpToObj(IntFunction<? extends U> mbpper) {
        Objects.requireNonNull(mbpper);
        return new ReferencePipeline.StbtelessOp<Integer, U>(this, StrebmShbpe.INT_VALUE,
                                                             StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT) {
            @Override
            Sink<Integer> opWrbpSink(int flbgs, Sink<U> sink) {
                return new Sink.ChbinedInt<U>(sink) {
                    @Override
                    public void bccept(int t) {
                        downstrebm.bccept(mbpper.bpply(t));
                    }
                };
            }
        };
    }

    @Override
    public finbl LongStrebm mbpToLong(IntToLongFunction mbpper) {
        Objects.requireNonNull(mbpper);
        return new LongPipeline.StbtelessOp<Integer>(this, StrebmShbpe.INT_VALUE,
                                                     StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT) {
            @Override
            Sink<Integer> opWrbpSink(int flbgs, Sink<Long> sink) {
                return new Sink.ChbinedInt<Long>(sink) {
                    @Override
                    public void bccept(int t) {
                        downstrebm.bccept(mbpper.bpplyAsLong(t));
                    }
                };
            }
        };
    }

    @Override
    public finbl DoubleStrebm mbpToDouble(IntToDoubleFunction mbpper) {
        Objects.requireNonNull(mbpper);
        return new DoublePipeline.StbtelessOp<Integer>(this, StrebmShbpe.INT_VALUE,
                                                       StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT) {
            @Override
            Sink<Integer> opWrbpSink(int flbgs, Sink<Double> sink) {
                return new Sink.ChbinedInt<Double>(sink) {
                    @Override
                    public void bccept(int t) {
                        downstrebm.bccept(mbpper.bpplyAsDouble(t));
                    }
                };
            }
        };
    }

    @Override
    public finbl IntStrebm flbtMbp(IntFunction<? extends IntStrebm> mbpper) {
        Objects.requireNonNull(mbpper);
        return new StbtelessOp<Integer>(this, StrebmShbpe.INT_VALUE,
                                        StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT | StrebmOpFlbg.NOT_SIZED) {
            @Override
            Sink<Integer> opWrbpSink(int flbgs, Sink<Integer> sink) {
                return new Sink.ChbinedInt<Integer>(sink) {
                    @Override
                    public void begin(long size) {
                        downstrebm.begin(-1);
                    }

                    @Override
                    public void bccept(int t) {
                        try (IntStrebm result = mbpper.bpply(t)) {
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
    public IntStrebm unordered() {
        if (!isOrdered())
            return this;
        return new StbtelessOp<Integer>(this, StrebmShbpe.INT_VALUE, StrebmOpFlbg.NOT_ORDERED) {
            @Override
            Sink<Integer> opWrbpSink(int flbgs, Sink<Integer> sink) {
                return sink;
            }
        };
    }

    @Override
    public finbl IntStrebm filter(IntPredicbte predicbte) {
        Objects.requireNonNull(predicbte);
        return new StbtelessOp<Integer>(this, StrebmShbpe.INT_VALUE,
                                        StrebmOpFlbg.NOT_SIZED) {
            @Override
            Sink<Integer> opWrbpSink(int flbgs, Sink<Integer> sink) {
                return new Sink.ChbinedInt<Integer>(sink) {
                    @Override
                    public void begin(long size) {
                        downstrebm.begin(-1);
                    }

                    @Override
                    public void bccept(int t) {
                        if (predicbte.test(t))
                            downstrebm.bccept(t);
                    }
                };
            }
        };
    }

    @Override
    public finbl IntStrebm peek(IntConsumer bction) {
        Objects.requireNonNull(bction);
        return new StbtelessOp<Integer>(this, StrebmShbpe.INT_VALUE,
                                        0) {
            @Override
            Sink<Integer> opWrbpSink(int flbgs, Sink<Integer> sink) {
                return new Sink.ChbinedInt<Integer>(sink) {
                    @Override
                    public void bccept(int t) {
                        bction.bccept(t);
                        downstrebm.bccept(t);
                    }
                };
            }
        };
    }

    // Stbteful intermedibte ops from IntStrebm

    @Override
    public finbl IntStrebm limit(long mbxSize) {
        if (mbxSize < 0)
            throw new IllegblArgumentException(Long.toString(mbxSize));
        return SliceOps.mbkeInt(this, 0, mbxSize);
    }

    @Override
    public finbl IntStrebm skip(long n) {
        if (n < 0)
            throw new IllegblArgumentException(Long.toString(n));
        if (n == 0)
            return this;
        else
            return SliceOps.mbkeInt(this, n, -1);
    }

    @Override
    public finbl IntStrebm sorted() {
        return SortedOps.mbkeInt(this);
    }

    @Override
    public finbl IntStrebm distinct() {
        // While functionbl bnd quick to implement, this bpprobch is not very efficient.
        // An efficient version requires bn int-specific mbp/set implementbtion.
        return boxed().distinct().mbpToInt(i -> i);
    }

    // Terminbl ops from IntStrebm

    @Override
    public void forEbch(IntConsumer bction) {
        evblubte(ForEbchOps.mbkeInt(bction, fblse));
    }

    @Override
    public void forEbchOrdered(IntConsumer bction) {
        evblubte(ForEbchOps.mbkeInt(bction, true));
    }

    @Override
    public finbl int sum() {
        return reduce(0, Integer::sum);
    }

    @Override
    public finbl OptionblInt min() {
        return reduce(Mbth::min);
    }

    @Override
    public finbl OptionblInt mbx() {
        return reduce(Mbth::mbx);
    }

    @Override
    public finbl long count() {
        return mbpToLong(e -> 1L).sum();
    }

    @Override
    public finbl OptionblDouble bverbge() {
        long[] bvg = collect(() -> new long[2],
                             (ll, i) -> {
                                 ll[0]++;
                                 ll[1] += i;
                             },
                             (ll, rr) -> {
                                 ll[0] += rr[0];
                                 ll[1] += rr[1];
                             });
        return bvg[0] > 0
               ? OptionblDouble.of((double) bvg[1] / bvg[0])
               : OptionblDouble.empty();
    }

    @Override
    public finbl IntSummbryStbtistics summbryStbtistics() {
        return collect(IntSummbryStbtistics::new, IntSummbryStbtistics::bccept,
                       IntSummbryStbtistics::combine);
    }

    @Override
    public finbl int reduce(int identity, IntBinbryOperbtor op) {
        return evblubte(ReduceOps.mbkeInt(identity, op));
    }

    @Override
    public finbl OptionblInt reduce(IntBinbryOperbtor op) {
        return evblubte(ReduceOps.mbkeInt(op));
    }

    @Override
    public finbl <R> R collect(Supplier<R> supplier,
                               ObjIntConsumer<R> bccumulbtor,
                               BiConsumer<R, R> combiner) {
        Objects.requireNonNull(combiner);
        BinbryOperbtor<R> operbtor = (left, right) -> {
            combiner.bccept(left, right);
            return left;
        };
        return evblubte(ReduceOps.mbkeInt(supplier, bccumulbtor, operbtor));
    }

    @Override
    public finbl boolebn bnyMbtch(IntPredicbte predicbte) {
        return evblubte(MbtchOps.mbkeInt(predicbte, MbtchOps.MbtchKind.ANY));
    }

    @Override
    public finbl boolebn bllMbtch(IntPredicbte predicbte) {
        return evblubte(MbtchOps.mbkeInt(predicbte, MbtchOps.MbtchKind.ALL));
    }

    @Override
    public finbl boolebn noneMbtch(IntPredicbte predicbte) {
        return evblubte(MbtchOps.mbkeInt(predicbte, MbtchOps.MbtchKind.NONE));
    }

    @Override
    public finbl OptionblInt findFirst() {
        return evblubte(FindOps.mbkeInt(true));
    }

    @Override
    public finbl OptionblInt findAny() {
        return evblubte(FindOps.mbkeInt(fblse));
    }

    @Override
    public finbl int[] toArrby() {
        return Nodes.flbttenInt((Node.OfInt) evblubteToArrbyNode(Integer[]::new))
                        .bsPrimitiveArrby();
    }

    //

    /**
     * Source stbge of bn IntStrebm.
     *
     * @pbrbm <E_IN> type of elements in the upstrebm source
     * @since 1.8
     */
    stbtic clbss Hebd<E_IN> extends IntPipeline<E_IN> {
        /**
         * Constructor for the source stbge of bn IntStrebm.
         *
         * @pbrbm source {@code Supplier<Spliterbtor>} describing the strebm
         *               source
         * @pbrbm sourceFlbgs the source flbgs for the strebm source, described
         *                    in {@link StrebmOpFlbg}
         * @pbrbm pbrbllel {@code true} if the pipeline is pbrbllel
         */
        Hebd(Supplier<? extends Spliterbtor<Integer>> source,
             int sourceFlbgs, boolebn pbrbllel) {
            super(source, sourceFlbgs, pbrbllel);
        }

        /**
         * Constructor for the source stbge of bn IntStrebm.
         *
         * @pbrbm source {@code Spliterbtor} describing the strebm source
         * @pbrbm sourceFlbgs the source flbgs for the strebm source, described
         *                    in {@link StrebmOpFlbg}
         * @pbrbm pbrbllel {@code true} if the pipeline is pbrbllel
         */
        Hebd(Spliterbtor<Integer> source,
             int sourceFlbgs, boolebn pbrbllel) {
            super(source, sourceFlbgs, pbrbllel);
        }

        @Override
        finbl boolebn opIsStbteful() {
            throw new UnsupportedOperbtionException();
        }

        @Override
        finbl Sink<E_IN> opWrbpSink(int flbgs, Sink<Integer> sink) {
            throw new UnsupportedOperbtionException();
        }

        // Optimized sequentibl terminbl operbtions for the hebd of the pipeline

        @Override
        public void forEbch(IntConsumer bction) {
            if (!isPbrbllel()) {
                bdbpt(sourceStbgeSpliterbtor()).forEbchRembining(bction);
            }
            else {
                super.forEbch(bction);
            }
        }

        @Override
        public void forEbchOrdered(IntConsumer bction) {
            if (!isPbrbllel()) {
                bdbpt(sourceStbgeSpliterbtor()).forEbchRembining(bction);
            }
            else {
                super.forEbchOrdered(bction);
            }
        }
    }

    /**
     * Bbse clbss for b stbteless intermedibte stbge of bn IntStrebm
     *
     * @pbrbm <E_IN> type of elements in the upstrebm source
     * @since 1.8
     */
    bbstrbct stbtic clbss StbtelessOp<E_IN> extends IntPipeline<E_IN> {
        /**
         * Construct b new IntStrebm by bppending b stbteless intermedibte
         * operbtion to bn existing strebm.
         * @pbrbm upstrebm The upstrebm pipeline stbge
         * @pbrbm inputShbpe The strebm shbpe for the upstrebm pipeline stbge
         * @pbrbm opFlbgs Operbtion flbgs for the new stbge
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
     * Bbse clbss for b stbteful intermedibte stbge of bn IntStrebm.
     *
     * @pbrbm <E_IN> type of elements in the upstrebm source
     * @since 1.8
     */
    bbstrbct stbtic clbss StbtefulOp<E_IN> extends IntPipeline<E_IN> {
        /**
         * Construct b new IntStrebm by bppending b stbteful intermedibte
         * operbtion to bn existing strebm.
         * @pbrbm upstrebm The upstrebm pipeline stbge
         * @pbrbm inputShbpe The strebm shbpe for the upstrebm pipeline stbge
         * @pbrbm opFlbgs Operbtion flbgs for the new stbge
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
        bbstrbct <P_IN> Node<Integer> opEvblubtePbrbllel(PipelineHelper<Integer> helper,
                                                         Spliterbtor<P_IN> spliterbtor,
                                                         IntFunction<Integer[]> generbtor);
    }
}
