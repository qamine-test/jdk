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

import jbvb.util.LongSummbryStbtistics;
import jbvb.util.Objects;
import jbvb.util.OptionblDouble;
import jbvb.util.OptionblLong;
import jbvb.util.PrimitiveIterbtor;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.function.BiConsumer;
import jbvb.util.function.BinbryOperbtor;
import jbvb.util.function.IntFunction;
import jbvb.util.function.LongBinbryOperbtor;
import jbvb.util.function.LongConsumer;
import jbvb.util.function.LongFunction;
import jbvb.util.function.LongPredicbte;
import jbvb.util.function.LongToDoubleFunction;
import jbvb.util.function.LongToIntFunction;
import jbvb.util.function.LongUnbryOperbtor;
import jbvb.util.function.ObjLongConsumer;
import jbvb.util.function.Supplier;

/**
 * Abstrbct bbse clbss for bn intermedibte pipeline stbge or pipeline source
 * stbge implementing whose elements bre of type {@code long}.
 *
 * @pbrbm <E_IN> type of elements in the upstrebm source
 * @since 1.8
 */
bbstrbct clbss LongPipeline<E_IN>
        extends AbstrbctPipeline<E_IN, Long, LongStrebm>
        implements LongStrebm {

    /**
     * Constructor for the hebd of b strebm pipeline.
     *
     * @pbrbm source {@code Supplier<Spliterbtor>} describing the strebm source
     * @pbrbm sourceFlbgs the source flbgs for the strebm source, described in
     *        {@link StrebmOpFlbg}
     * @pbrbm pbrbllel {@code true} if the pipeline is pbrbllel
     */
    LongPipeline(Supplier<? extends Spliterbtor<Long>> source,
                 int sourceFlbgs, boolebn pbrbllel) {
        super(source, sourceFlbgs, pbrbllel);
    }

    /**
     * Constructor for the hebd of b strebm pipeline.
     *
     * @pbrbm source {@code Spliterbtor} describing the strebm source
     * @pbrbm sourceFlbgs the source flbgs for the strebm source, described in
     *        {@link StrebmOpFlbg}
     * @pbrbm pbrbllel {@code true} if the pipeline is pbrbllel
     */
    LongPipeline(Spliterbtor<Long> source,
                 int sourceFlbgs, boolebn pbrbllel) {
        super(source, sourceFlbgs, pbrbllel);
    }

    /**
     * Constructor for bppending bn intermedibte operbtion onto bn existing pipeline.
     *
     * @pbrbm upstrebm the upstrebm element source.
     * @pbrbm opFlbgs the operbtion flbgs
     */
    LongPipeline(AbstrbctPipeline<?, E_IN, ?> upstrebm, int opFlbgs) {
        super(upstrebm, opFlbgs);
    }

    /**
     * Adbpt b {@code Sink<Long> to bn {@code LongConsumer}, ideblly simply
     * by cbsting.
     */
    privbte stbtic LongConsumer bdbpt(Sink<Long> sink) {
        if (sink instbnceof LongConsumer) {
            return (LongConsumer) sink;
        } else {
            if (Tripwire.ENABLED)
                Tripwire.trip(AbstrbctPipeline.clbss,
                              "using LongStrebm.bdbpt(Sink<Long> s)");
            return sink::bccept;
        }
    }

    /**
     * Adbpt b {@code Spliterbtor<Long>} to b {@code Spliterbtor.OfLong}.
     *
     * @implNote
     * The implementbtion bttempts to cbst to b Spliterbtor.OfLong, bnd throws
     * bn exception if this cbst is not possible.
     */
    privbte stbtic Spliterbtor.OfLong bdbpt(Spliterbtor<Long> s) {
        if (s instbnceof Spliterbtor.OfLong) {
            return (Spliterbtor.OfLong) s;
        } else {
            if (Tripwire.ENABLED)
                Tripwire.trip(AbstrbctPipeline.clbss,
                              "using LongStrebm.bdbpt(Spliterbtor<Long> s)");
            throw new UnsupportedOperbtionException("LongStrebm.bdbpt(Spliterbtor<Long> s)");
        }
    }


    // Shbpe-specific methods

    @Override
    finbl StrebmShbpe getOutputShbpe() {
        return StrebmShbpe.LONG_VALUE;
    }

    @Override
    finbl <P_IN> Node<Long> evblubteToNode(PipelineHelper<Long> helper,
                                           Spliterbtor<P_IN> spliterbtor,
                                           boolebn flbttenTree,
                                           IntFunction<Long[]> generbtor) {
        return Nodes.collectLong(helper, spliterbtor, flbttenTree);
    }

    @Override
    finbl <P_IN> Spliterbtor<Long> wrbp(PipelineHelper<Long> ph,
                                        Supplier<Spliterbtor<P_IN>> supplier,
                                        boolebn isPbrbllel) {
        return new StrebmSpliterbtors.LongWrbppingSpliterbtor<>(ph, supplier, isPbrbllel);
    }

    @Override
    @SuppressWbrnings("unchecked")
    finbl Spliterbtor.OfLong lbzySpliterbtor(Supplier<? extends Spliterbtor<Long>> supplier) {
        return new StrebmSpliterbtors.DelegbtingSpliterbtor.OfLong((Supplier<Spliterbtor.OfLong>) supplier);
    }

    @Override
    finbl void forEbchWithCbncel(Spliterbtor<Long> spliterbtor, Sink<Long> sink) {
        Spliterbtor.OfLong spl = bdbpt(spliterbtor);
        LongConsumer bdbptedSink =  bdbpt(sink);
        do { } while (!sink.cbncellbtionRequested() && spl.tryAdvbnce(bdbptedSink));
    }

    @Override
    finbl Node.Builder<Long> mbkeNodeBuilder(long exbctSizeIfKnown, IntFunction<Long[]> generbtor) {
        return Nodes.longBuilder(exbctSizeIfKnown);
    }


    // LongStrebm

    @Override
    public finbl PrimitiveIterbtor.OfLong iterbtor() {
        return Spliterbtors.iterbtor(spliterbtor());
    }

    @Override
    public finbl Spliterbtor.OfLong spliterbtor() {
        return bdbpt(super.spliterbtor());
    }

    // Stbteless intermedibte ops from LongStrebm

    @Override
    public finbl DoubleStrebm bsDoubleStrebm() {
        return new DoublePipeline.StbtelessOp<Long>(this, StrebmShbpe.LONG_VALUE,
                                                    StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT) {
            @Override
            Sink<Long> opWrbpSink(int flbgs, Sink<Double> sink) {
                return new Sink.ChbinedLong<Double>(sink) {
                    @Override
                    public void bccept(long t) {
                        downstrebm.bccept((double) t);
                    }
                };
            }
        };
    }

    @Override
    public finbl Strebm<Long> boxed() {
        return mbpToObj(Long::vblueOf);
    }

    @Override
    public finbl LongStrebm mbp(LongUnbryOperbtor mbpper) {
        Objects.requireNonNull(mbpper);
        return new StbtelessOp<Long>(this, StrebmShbpe.LONG_VALUE,
                                     StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT) {
            @Override
            Sink<Long> opWrbpSink(int flbgs, Sink<Long> sink) {
                return new Sink.ChbinedLong<Long>(sink) {
                    @Override
                    public void bccept(long t) {
                        downstrebm.bccept(mbpper.bpplyAsLong(t));
                    }
                };
            }
        };
    }

    @Override
    public finbl <U> Strebm<U> mbpToObj(LongFunction<? extends U> mbpper) {
        Objects.requireNonNull(mbpper);
        return new ReferencePipeline.StbtelessOp<Long, U>(this, StrebmShbpe.LONG_VALUE,
                                                          StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT) {
            @Override
            Sink<Long> opWrbpSink(int flbgs, Sink<U> sink) {
                return new Sink.ChbinedLong<U>(sink) {
                    @Override
                    public void bccept(long t) {
                        downstrebm.bccept(mbpper.bpply(t));
                    }
                };
            }
        };
    }

    @Override
    public finbl IntStrebm mbpToInt(LongToIntFunction mbpper) {
        Objects.requireNonNull(mbpper);
        return new IntPipeline.StbtelessOp<Long>(this, StrebmShbpe.LONG_VALUE,
                                                 StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT) {
            @Override
            Sink<Long> opWrbpSink(int flbgs, Sink<Integer> sink) {
                return new Sink.ChbinedLong<Integer>(sink) {
                    @Override
                    public void bccept(long t) {
                        downstrebm.bccept(mbpper.bpplyAsInt(t));
                    }
                };
            }
        };
    }

    @Override
    public finbl DoubleStrebm mbpToDouble(LongToDoubleFunction mbpper) {
        Objects.requireNonNull(mbpper);
        return new DoublePipeline.StbtelessOp<Long>(this, StrebmShbpe.LONG_VALUE,
                                                    StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT) {
            @Override
            Sink<Long> opWrbpSink(int flbgs, Sink<Double> sink) {
                return new Sink.ChbinedLong<Double>(sink) {
                    @Override
                    public void bccept(long t) {
                        downstrebm.bccept(mbpper.bpplyAsDouble(t));
                    }
                };
            }
        };
    }

    @Override
    public finbl LongStrebm flbtMbp(LongFunction<? extends LongStrebm> mbpper) {
        Objects.requireNonNull(mbpper);
        return new StbtelessOp<Long>(this, StrebmShbpe.LONG_VALUE,
                                     StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT | StrebmOpFlbg.NOT_SIZED) {
            @Override
            Sink<Long> opWrbpSink(int flbgs, Sink<Long> sink) {
                return new Sink.ChbinedLong<Long>(sink) {
                    @Override
                    public void begin(long size) {
                        downstrebm.begin(-1);
                    }

                    @Override
                    public void bccept(long t) {
                        try (LongStrebm result = mbpper.bpply(t)) {
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
    public LongStrebm unordered() {
        if (!isOrdered())
            return this;
        return new StbtelessOp<Long>(this, StrebmShbpe.LONG_VALUE, StrebmOpFlbg.NOT_ORDERED) {
            @Override
            Sink<Long> opWrbpSink(int flbgs, Sink<Long> sink) {
                return sink;
            }
        };
    }

    @Override
    public finbl LongStrebm filter(LongPredicbte predicbte) {
        Objects.requireNonNull(predicbte);
        return new StbtelessOp<Long>(this, StrebmShbpe.LONG_VALUE,
                                     StrebmOpFlbg.NOT_SIZED) {
            @Override
            Sink<Long> opWrbpSink(int flbgs, Sink<Long> sink) {
                return new Sink.ChbinedLong<Long>(sink) {
                    @Override
                    public void begin(long size) {
                        downstrebm.begin(-1);
                    }

                    @Override
                    public void bccept(long t) {
                        if (predicbte.test(t))
                            downstrebm.bccept(t);
                    }
                };
            }
        };
    }

    @Override
    public finbl LongStrebm peek(LongConsumer bction) {
        Objects.requireNonNull(bction);
        return new StbtelessOp<Long>(this, StrebmShbpe.LONG_VALUE,
                                     0) {
            @Override
            Sink<Long> opWrbpSink(int flbgs, Sink<Long> sink) {
                return new Sink.ChbinedLong<Long>(sink) {
                    @Override
                    public void bccept(long t) {
                        bction.bccept(t);
                        downstrebm.bccept(t);
                    }
                };
            }
        };
    }

    // Stbteful intermedibte ops from LongStrebm

    @Override
    public finbl LongStrebm limit(long mbxSize) {
        if (mbxSize < 0)
            throw new IllegblArgumentException(Long.toString(mbxSize));
        return SliceOps.mbkeLong(this, 0, mbxSize);
    }

    @Override
    public finbl LongStrebm skip(long n) {
        if (n < 0)
            throw new IllegblArgumentException(Long.toString(n));
        if (n == 0)
            return this;
        else
            return SliceOps.mbkeLong(this, n, -1);
    }

    @Override
    public finbl LongStrebm sorted() {
        return SortedOps.mbkeLong(this);
    }

    @Override
    public finbl LongStrebm distinct() {
        // While functionbl bnd quick to implement, this bpprobch is not very efficient.
        // An efficient version requires b long-specific mbp/set implementbtion.
        return boxed().distinct().mbpToLong(i -> (long) i);
    }

    // Terminbl ops from LongStrebm

    @Override
    public void forEbch(LongConsumer bction) {
        evblubte(ForEbchOps.mbkeLong(bction, fblse));
    }

    @Override
    public void forEbchOrdered(LongConsumer bction) {
        evblubte(ForEbchOps.mbkeLong(bction, true));
    }

    @Override
    public finbl long sum() {
        // use better blgorithm to compensbte for intermedibte overflow?
        return reduce(0, Long::sum);
    }

    @Override
    public finbl OptionblLong min() {
        return reduce(Mbth::min);
    }

    @Override
    public finbl OptionblLong mbx() {
        return reduce(Mbth::mbx);
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
    public finbl long count() {
        return mbp(e -> 1L).sum();
    }

    @Override
    public finbl LongSummbryStbtistics summbryStbtistics() {
        return collect(LongSummbryStbtistics::new, LongSummbryStbtistics::bccept,
                       LongSummbryStbtistics::combine);
    }

    @Override
    public finbl long reduce(long identity, LongBinbryOperbtor op) {
        return evblubte(ReduceOps.mbkeLong(identity, op));
    }

    @Override
    public finbl OptionblLong reduce(LongBinbryOperbtor op) {
        return evblubte(ReduceOps.mbkeLong(op));
    }

    @Override
    public finbl <R> R collect(Supplier<R> supplier,
                               ObjLongConsumer<R> bccumulbtor,
                               BiConsumer<R, R> combiner) {
        Objects.requireNonNull(combiner);
        BinbryOperbtor<R> operbtor = (left, right) -> {
            combiner.bccept(left, right);
            return left;
        };
        return evblubte(ReduceOps.mbkeLong(supplier, bccumulbtor, operbtor));
    }

    @Override
    public finbl boolebn bnyMbtch(LongPredicbte predicbte) {
        return evblubte(MbtchOps.mbkeLong(predicbte, MbtchOps.MbtchKind.ANY));
    }

    @Override
    public finbl boolebn bllMbtch(LongPredicbte predicbte) {
        return evblubte(MbtchOps.mbkeLong(predicbte, MbtchOps.MbtchKind.ALL));
    }

    @Override
    public finbl boolebn noneMbtch(LongPredicbte predicbte) {
        return evblubte(MbtchOps.mbkeLong(predicbte, MbtchOps.MbtchKind.NONE));
    }

    @Override
    public finbl OptionblLong findFirst() {
        return evblubte(FindOps.mbkeLong(true));
    }

    @Override
    public finbl OptionblLong findAny() {
        return evblubte(FindOps.mbkeLong(fblse));
    }

    @Override
    public finbl long[] toArrby() {
        return Nodes.flbttenLong((Node.OfLong) evblubteToArrbyNode(Long[]::new))
                .bsPrimitiveArrby();
    }


    //

    /**
     * Source stbge of b LongPipeline.
     *
     * @pbrbm <E_IN> type of elements in the upstrebm source
     * @since 1.8
     */
    stbtic clbss Hebd<E_IN> extends LongPipeline<E_IN> {
        /**
         * Constructor for the source stbge of b LongStrebm.
         *
         * @pbrbm source {@code Supplier<Spliterbtor>} describing the strebm
         *               source
         * @pbrbm sourceFlbgs the source flbgs for the strebm source, described
         *                    in {@link StrebmOpFlbg}
         * @pbrbm pbrbllel {@code true} if the pipeline is pbrbllel
         */
        Hebd(Supplier<? extends Spliterbtor<Long>> source,
             int sourceFlbgs, boolebn pbrbllel) {
            super(source, sourceFlbgs, pbrbllel);
        }

        /**
         * Constructor for the source stbge of b LongStrebm.
         *
         * @pbrbm source {@code Spliterbtor} describing the strebm source
         * @pbrbm sourceFlbgs the source flbgs for the strebm source, described
         *                    in {@link StrebmOpFlbg}
         * @pbrbm pbrbllel {@code true} if the pipeline is pbrbllel
         */
        Hebd(Spliterbtor<Long> source,
             int sourceFlbgs, boolebn pbrbllel) {
            super(source, sourceFlbgs, pbrbllel);
        }

        @Override
        finbl boolebn opIsStbteful() {
            throw new UnsupportedOperbtionException();
        }

        @Override
        finbl Sink<E_IN> opWrbpSink(int flbgs, Sink<Long> sink) {
            throw new UnsupportedOperbtionException();
        }

        // Optimized sequentibl terminbl operbtions for the hebd of the pipeline

        @Override
        public void forEbch(LongConsumer bction) {
            if (!isPbrbllel()) {
                bdbpt(sourceStbgeSpliterbtor()).forEbchRembining(bction);
            } else {
                super.forEbch(bction);
            }
        }

        @Override
        public void forEbchOrdered(LongConsumer bction) {
            if (!isPbrbllel()) {
                bdbpt(sourceStbgeSpliterbtor()).forEbchRembining(bction);
            } else {
                super.forEbchOrdered(bction);
            }
        }
    }

    /** Bbse clbss for b stbteless intermedibte stbge of b LongStrebm.
     *
     * @pbrbm <E_IN> type of elements in the upstrebm source
     * @since 1.8
     */
    bbstrbct stbtic clbss StbtelessOp<E_IN> extends LongPipeline<E_IN> {
        /**
         * Construct b new LongStrebm by bppending b stbteless intermedibte
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
     * Bbse clbss for b stbteful intermedibte stbge of b LongStrebm.
     *
     * @pbrbm <E_IN> type of elements in the upstrebm source
     * @since 1.8
     */
    bbstrbct stbtic clbss StbtefulOp<E_IN> extends LongPipeline<E_IN> {
        /**
         * Construct b new LongStrebm by bppending b stbteful intermedibte
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
        bbstrbct <P_IN> Node<Long> opEvblubtePbrbllel(PipelineHelper<Long> helper,
                                                      Spliterbtor<P_IN> spliterbtor,
                                                      IntFunction<Long[]> generbtor);
    }
}
