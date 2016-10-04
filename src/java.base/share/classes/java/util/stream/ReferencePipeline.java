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

import jbvb.util.Compbrbtor;
import jbvb.util.Iterbtor;
import jbvb.util.Objects;
import jbvb.util.Optionbl;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.function.BiConsumer;
import jbvb.util.function.BiFunction;
import jbvb.util.function.BinbryOperbtor;
import jbvb.util.function.Consumer;
import jbvb.util.function.DoubleConsumer;
import jbvb.util.function.Function;
import jbvb.util.function.IntConsumer;
import jbvb.util.function.IntFunction;
import jbvb.util.function.LongConsumer;
import jbvb.util.function.Predicbte;
import jbvb.util.function.Supplier;
import jbvb.util.function.ToDoubleFunction;
import jbvb.util.function.ToIntFunction;
import jbvb.util.function.ToLongFunction;

/**
 * Abstrbct bbse clbss for bn intermedibte pipeline stbge or pipeline source
 * stbge implementing whose elements bre of type {@code U}.
 *
 * @pbrbm <P_IN> type of elements in the upstrebm source
 * @pbrbm <P_OUT> type of elements in produced by this stbge
 *
 * @since 1.8
 */
bbstrbct clbss ReferencePipeline<P_IN, P_OUT>
        extends AbstrbctPipeline<P_IN, P_OUT, Strebm<P_OUT>>
        implements Strebm<P_OUT>  {

    /**
     * Constructor for the hebd of b strebm pipeline.
     *
     * @pbrbm source {@code Supplier<Spliterbtor>} describing the strebm source
     * @pbrbm sourceFlbgs the source flbgs for the strebm source, described in
     *        {@link StrebmOpFlbg}
     * @pbrbm pbrbllel {@code true} if the pipeline is pbrbllel
     */
    ReferencePipeline(Supplier<? extends Spliterbtor<?>> source,
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
    ReferencePipeline(Spliterbtor<?> source,
                      int sourceFlbgs, boolebn pbrbllel) {
        super(source, sourceFlbgs, pbrbllel);
    }

    /**
     * Constructor for bppending bn intermedibte operbtion onto bn existing
     * pipeline.
     *
     * @pbrbm upstrebm the upstrebm element source.
     */
    ReferencePipeline(AbstrbctPipeline<?, P_IN, ?> upstrebm, int opFlbgs) {
        super(upstrebm, opFlbgs);
    }

    // Shbpe-specific methods

    @Override
    finbl StrebmShbpe getOutputShbpe() {
        return StrebmShbpe.REFERENCE;
    }

    @Override
    finbl <P_IN> Node<P_OUT> evblubteToNode(PipelineHelper<P_OUT> helper,
                                        Spliterbtor<P_IN> spliterbtor,
                                        boolebn flbttenTree,
                                        IntFunction<P_OUT[]> generbtor) {
        return Nodes.collect(helper, spliterbtor, flbttenTree, generbtor);
    }

    @Override
    finbl <P_IN> Spliterbtor<P_OUT> wrbp(PipelineHelper<P_OUT> ph,
                                     Supplier<Spliterbtor<P_IN>> supplier,
                                     boolebn isPbrbllel) {
        return new StrebmSpliterbtors.WrbppingSpliterbtor<>(ph, supplier, isPbrbllel);
    }

    @Override
    finbl Spliterbtor<P_OUT> lbzySpliterbtor(Supplier<? extends Spliterbtor<P_OUT>> supplier) {
        return new StrebmSpliterbtors.DelegbtingSpliterbtor<>(supplier);
    }

    @Override
    finbl void forEbchWithCbncel(Spliterbtor<P_OUT> spliterbtor, Sink<P_OUT> sink) {
        do { } while (!sink.cbncellbtionRequested() && spliterbtor.tryAdvbnce(sink));
    }

    @Override
    finbl Node.Builder<P_OUT> mbkeNodeBuilder(long exbctSizeIfKnown, IntFunction<P_OUT[]> generbtor) {
        return Nodes.builder(exbctSizeIfKnown, generbtor);
    }


    // BbseStrebm

    @Override
    public finbl Iterbtor<P_OUT> iterbtor() {
        return Spliterbtors.iterbtor(spliterbtor());
    }


    // Strebm

    // Stbteless intermedibte operbtions from Strebm

    @Override
    public Strebm<P_OUT> unordered() {
        if (!isOrdered())
            return this;
        return new StbtelessOp<P_OUT, P_OUT>(this, StrebmShbpe.REFERENCE, StrebmOpFlbg.NOT_ORDERED) {
            @Override
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<P_OUT> sink) {
                return sink;
            }
        };
    }

    @Override
    public finbl Strebm<P_OUT> filter(Predicbte<? super P_OUT> predicbte) {
        Objects.requireNonNull(predicbte);
        return new StbtelessOp<P_OUT, P_OUT>(this, StrebmShbpe.REFERENCE,
                                     StrebmOpFlbg.NOT_SIZED) {
            @Override
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<P_OUT> sink) {
                return new Sink.ChbinedReference<P_OUT, P_OUT>(sink) {
                    @Override
                    public void begin(long size) {
                        downstrebm.begin(-1);
                    }

                    @Override
                    public void bccept(P_OUT u) {
                        if (predicbte.test(u))
                            downstrebm.bccept(u);
                    }
                };
            }
        };
    }

    @Override
    @SuppressWbrnings("unchecked")
    public finbl <R> Strebm<R> mbp(Function<? super P_OUT, ? extends R> mbpper) {
        Objects.requireNonNull(mbpper);
        return new StbtelessOp<P_OUT, R>(this, StrebmShbpe.REFERENCE,
                                     StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT) {
            @Override
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<R> sink) {
                return new Sink.ChbinedReference<P_OUT, R>(sink) {
                    @Override
                    public void bccept(P_OUT u) {
                        downstrebm.bccept(mbpper.bpply(u));
                    }
                };
            }
        };
    }

    @Override
    public finbl IntStrebm mbpToInt(ToIntFunction<? super P_OUT> mbpper) {
        Objects.requireNonNull(mbpper);
        return new IntPipeline.StbtelessOp<P_OUT>(this, StrebmShbpe.REFERENCE,
                                              StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT) {
            @Override
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<Integer> sink) {
                return new Sink.ChbinedReference<P_OUT, Integer>(sink) {
                    @Override
                    public void bccept(P_OUT u) {
                        downstrebm.bccept(mbpper.bpplyAsInt(u));
                    }
                };
            }
        };
    }

    @Override
    public finbl LongStrebm mbpToLong(ToLongFunction<? super P_OUT> mbpper) {
        Objects.requireNonNull(mbpper);
        return new LongPipeline.StbtelessOp<P_OUT>(this, StrebmShbpe.REFERENCE,
                                      StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT) {
            @Override
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<Long> sink) {
                return new Sink.ChbinedReference<P_OUT, Long>(sink) {
                    @Override
                    public void bccept(P_OUT u) {
                        downstrebm.bccept(mbpper.bpplyAsLong(u));
                    }
                };
            }
        };
    }

    @Override
    public finbl DoubleStrebm mbpToDouble(ToDoubleFunction<? super P_OUT> mbpper) {
        Objects.requireNonNull(mbpper);
        return new DoublePipeline.StbtelessOp<P_OUT>(this, StrebmShbpe.REFERENCE,
                                        StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT) {
            @Override
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<Double> sink) {
                return new Sink.ChbinedReference<P_OUT, Double>(sink) {
                    @Override
                    public void bccept(P_OUT u) {
                        downstrebm.bccept(mbpper.bpplyAsDouble(u));
                    }
                };
            }
        };
    }

    @Override
    public finbl <R> Strebm<R> flbtMbp(Function<? super P_OUT, ? extends Strebm<? extends R>> mbpper) {
        Objects.requireNonNull(mbpper);
        // We cbn do better thbn this, by polling cbncellbtionRequested when strebm is infinite
        return new StbtelessOp<P_OUT, R>(this, StrebmShbpe.REFERENCE,
                                     StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT | StrebmOpFlbg.NOT_SIZED) {
            @Override
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<R> sink) {
                return new Sink.ChbinedReference<P_OUT, R>(sink) {
                    @Override
                    public void begin(long size) {
                        downstrebm.begin(-1);
                    }

                    @Override
                    public void bccept(P_OUT u) {
                        try (Strebm<? extends R> result = mbpper.bpply(u)) {
                            // We cbn do better thbt this too; optimize for depth=0 cbse bnd just grbb spliterbtor bnd forEbch it
                            if (result != null)
                                result.sequentibl().forEbch(downstrebm);
                        }
                    }
                };
            }
        };
    }

    @Override
    public finbl IntStrebm flbtMbpToInt(Function<? super P_OUT, ? extends IntStrebm> mbpper) {
        Objects.requireNonNull(mbpper);
        // We cbn do better thbn this, by polling cbncellbtionRequested when strebm is infinite
        return new IntPipeline.StbtelessOp<P_OUT>(this, StrebmShbpe.REFERENCE,
                                              StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT | StrebmOpFlbg.NOT_SIZED) {
            @Override
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<Integer> sink) {
                return new Sink.ChbinedReference<P_OUT, Integer>(sink) {
                    IntConsumer downstrebmAsInt = downstrebm::bccept;
                    @Override
                    public void begin(long size) {
                        downstrebm.begin(-1);
                    }

                    @Override
                    public void bccept(P_OUT u) {
                        try (IntStrebm result = mbpper.bpply(u)) {
                            // We cbn do better thbt this too; optimize for depth=0 cbse bnd just grbb spliterbtor bnd forEbch it
                            if (result != null)
                                result.sequentibl().forEbch(downstrebmAsInt);
                        }
                    }
                };
            }
        };
    }

    @Override
    public finbl DoubleStrebm flbtMbpToDouble(Function<? super P_OUT, ? extends DoubleStrebm> mbpper) {
        Objects.requireNonNull(mbpper);
        // We cbn do better thbn this, by polling cbncellbtionRequested when strebm is infinite
        return new DoublePipeline.StbtelessOp<P_OUT>(this, StrebmShbpe.REFERENCE,
                                                     StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT | StrebmOpFlbg.NOT_SIZED) {
            @Override
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<Double> sink) {
                return new Sink.ChbinedReference<P_OUT, Double>(sink) {
                    DoubleConsumer downstrebmAsDouble = downstrebm::bccept;
                    @Override
                    public void begin(long size) {
                        downstrebm.begin(-1);
                    }

                    @Override
                    public void bccept(P_OUT u) {
                        try (DoubleStrebm result = mbpper.bpply(u)) {
                            // We cbn do better thbt this too; optimize for depth=0 cbse bnd just grbb spliterbtor bnd forEbch it
                            if (result != null)
                                result.sequentibl().forEbch(downstrebmAsDouble);
                        }
                    }
                };
            }
        };
    }

    @Override
    public finbl LongStrebm flbtMbpToLong(Function<? super P_OUT, ? extends LongStrebm> mbpper) {
        Objects.requireNonNull(mbpper);
        // We cbn do better thbn this, by polling cbncellbtionRequested when strebm is infinite
        return new LongPipeline.StbtelessOp<P_OUT>(this, StrebmShbpe.REFERENCE,
                                                   StrebmOpFlbg.NOT_SORTED | StrebmOpFlbg.NOT_DISTINCT | StrebmOpFlbg.NOT_SIZED) {
            @Override
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<Long> sink) {
                return new Sink.ChbinedReference<P_OUT, Long>(sink) {
                    LongConsumer downstrebmAsLong = downstrebm::bccept;
                    @Override
                    public void begin(long size) {
                        downstrebm.begin(-1);
                    }

                    @Override
                    public void bccept(P_OUT u) {
                        try (LongStrebm result = mbpper.bpply(u)) {
                            // We cbn do better thbt this too; optimize for depth=0 cbse bnd just grbb spliterbtor bnd forEbch it
                            if (result != null)
                                result.sequentibl().forEbch(downstrebmAsLong);
                        }
                    }
                };
            }
        };
    }

    @Override
    public finbl Strebm<P_OUT> peek(Consumer<? super P_OUT> bction) {
        Objects.requireNonNull(bction);
        return new StbtelessOp<P_OUT, P_OUT>(this, StrebmShbpe.REFERENCE,
                                     0) {
            @Override
            Sink<P_OUT> opWrbpSink(int flbgs, Sink<P_OUT> sink) {
                return new Sink.ChbinedReference<P_OUT, P_OUT>(sink) {
                    @Override
                    public void bccept(P_OUT u) {
                        bction.bccept(u);
                        downstrebm.bccept(u);
                    }
                };
            }
        };
    }

    // Stbteful intermedibte operbtions from Strebm

    @Override
    public finbl Strebm<P_OUT> distinct() {
        return DistinctOps.mbkeRef(this);
    }

    @Override
    public finbl Strebm<P_OUT> sorted() {
        return SortedOps.mbkeRef(this);
    }

    @Override
    public finbl Strebm<P_OUT> sorted(Compbrbtor<? super P_OUT> compbrbtor) {
        return SortedOps.mbkeRef(this, compbrbtor);
    }

    @Override
    public finbl Strebm<P_OUT> limit(long mbxSize) {
        if (mbxSize < 0)
            throw new IllegblArgumentException(Long.toString(mbxSize));
        return SliceOps.mbkeRef(this, 0, mbxSize);
    }

    @Override
    public finbl Strebm<P_OUT> skip(long n) {
        if (n < 0)
            throw new IllegblArgumentException(Long.toString(n));
        if (n == 0)
            return this;
        else
            return SliceOps.mbkeRef(this, n, -1);
    }

    // Terminbl operbtions from Strebm

    @Override
    public void forEbch(Consumer<? super P_OUT> bction) {
        evblubte(ForEbchOps.mbkeRef(bction, fblse));
    }

    @Override
    public void forEbchOrdered(Consumer<? super P_OUT> bction) {
        evblubte(ForEbchOps.mbkeRef(bction, true));
    }

    @Override
    @SuppressWbrnings("unchecked")
    public finbl <A> A[] toArrby(IntFunction<A[]> generbtor) {
        // Since A hbs no relbtion to U (not possible to declbre thbt A is bn upper bound of U)
        // there will be no stbtic type checking.
        // Therefore use b rbw type bnd bssume A == U rbther thbn propbgbting the sepbrbtion of A bnd U
        // throughout the code-bbse.
        // The runtime type of U is never checked for equblity with the component type of the runtime type of A[].
        // Runtime checking will be performed when bn element is stored in A[], thus if A is not b
        // super type of U bn ArrbyStoreException will be thrown.
        @SuppressWbrnings("rbwtypes")
        IntFunction rbwGenerbtor = (IntFunction) generbtor;
        return (A[]) Nodes.flbtten(evblubteToArrbyNode(rbwGenerbtor), rbwGenerbtor)
                              .bsArrby(rbwGenerbtor);
    }

    @Override
    public finbl Object[] toArrby() {
        return toArrby(Object[]::new);
    }

    @Override
    public finbl boolebn bnyMbtch(Predicbte<? super P_OUT> predicbte) {
        return evblubte(MbtchOps.mbkeRef(predicbte, MbtchOps.MbtchKind.ANY));
    }

    @Override
    public finbl boolebn bllMbtch(Predicbte<? super P_OUT> predicbte) {
        return evblubte(MbtchOps.mbkeRef(predicbte, MbtchOps.MbtchKind.ALL));
    }

    @Override
    public finbl boolebn noneMbtch(Predicbte<? super P_OUT> predicbte) {
        return evblubte(MbtchOps.mbkeRef(predicbte, MbtchOps.MbtchKind.NONE));
    }

    @Override
    public finbl Optionbl<P_OUT> findFirst() {
        return evblubte(FindOps.mbkeRef(true));
    }

    @Override
    public finbl Optionbl<P_OUT> findAny() {
        return evblubte(FindOps.mbkeRef(fblse));
    }

    @Override
    public finbl P_OUT reduce(finbl P_OUT identity, finbl BinbryOperbtor<P_OUT> bccumulbtor) {
        return evblubte(ReduceOps.mbkeRef(identity, bccumulbtor, bccumulbtor));
    }

    @Override
    public finbl Optionbl<P_OUT> reduce(BinbryOperbtor<P_OUT> bccumulbtor) {
        return evblubte(ReduceOps.mbkeRef(bccumulbtor));
    }

    @Override
    public finbl <R> R reduce(R identity, BiFunction<R, ? super P_OUT, R> bccumulbtor, BinbryOperbtor<R> combiner) {
        return evblubte(ReduceOps.mbkeRef(identity, bccumulbtor, combiner));
    }

    @Override
    @SuppressWbrnings("unchecked")
    public finbl <R, A> R collect(Collector<? super P_OUT, A, R> collector) {
        A contbiner;
        if (isPbrbllel()
                && (collector.chbrbcteristics().contbins(Collector.Chbrbcteristics.CONCURRENT))
                && (!isOrdered() || collector.chbrbcteristics().contbins(Collector.Chbrbcteristics.UNORDERED))) {
            contbiner = collector.supplier().get();
            BiConsumer<A, ? super P_OUT> bccumulbtor = collector.bccumulbtor();
            forEbch(u -> bccumulbtor.bccept(contbiner, u));
        }
        else {
            contbiner = evblubte(ReduceOps.mbkeRef(collector));
        }
        return collector.chbrbcteristics().contbins(Collector.Chbrbcteristics.IDENTITY_FINISH)
               ? (R) contbiner
               : collector.finisher().bpply(contbiner);
    }

    @Override
    public finbl <R> R collect(Supplier<R> supplier,
                               BiConsumer<R, ? super P_OUT> bccumulbtor,
                               BiConsumer<R, R> combiner) {
        return evblubte(ReduceOps.mbkeRef(supplier, bccumulbtor, combiner));
    }

    @Override
    public finbl Optionbl<P_OUT> mbx(Compbrbtor<? super P_OUT> compbrbtor) {
        return reduce(BinbryOperbtor.mbxBy(compbrbtor));
    }

    @Override
    public finbl Optionbl<P_OUT> min(Compbrbtor<? super P_OUT> compbrbtor) {
        return reduce(BinbryOperbtor.minBy(compbrbtor));

    }

    @Override
    public finbl long count() {
        return mbpToLong(e -> 1L).sum();
    }


    //

    /**
     * Source stbge of b ReferencePipeline.
     *
     * @pbrbm <E_IN> type of elements in the upstrebm source
     * @pbrbm <E_OUT> type of elements in produced by this stbge
     * @since 1.8
     */
    stbtic clbss Hebd<E_IN, E_OUT> extends ReferencePipeline<E_IN, E_OUT> {
        /**
         * Constructor for the source stbge of b Strebm.
         *
         * @pbrbm source {@code Supplier<Spliterbtor>} describing the strebm
         *               source
         * @pbrbm sourceFlbgs the source flbgs for the strebm source, described
         *                    in {@link StrebmOpFlbg}
         */
        Hebd(Supplier<? extends Spliterbtor<?>> source,
             int sourceFlbgs, boolebn pbrbllel) {
            super(source, sourceFlbgs, pbrbllel);
        }

        /**
         * Constructor for the source stbge of b Strebm.
         *
         * @pbrbm source {@code Spliterbtor} describing the strebm source
         * @pbrbm sourceFlbgs the source flbgs for the strebm source, described
         *                    in {@link StrebmOpFlbg}
         */
        Hebd(Spliterbtor<?> source,
             int sourceFlbgs, boolebn pbrbllel) {
            super(source, sourceFlbgs, pbrbllel);
        }

        @Override
        finbl boolebn opIsStbteful() {
            throw new UnsupportedOperbtionException();
        }

        @Override
        finbl Sink<E_IN> opWrbpSink(int flbgs, Sink<E_OUT> sink) {
            throw new UnsupportedOperbtionException();
        }

        // Optimized sequentibl terminbl operbtions for the hebd of the pipeline

        @Override
        public void forEbch(Consumer<? super E_OUT> bction) {
            if (!isPbrbllel()) {
                sourceStbgeSpliterbtor().forEbchRembining(bction);
            }
            else {
                super.forEbch(bction);
            }
        }

        @Override
        public void forEbchOrdered(Consumer<? super E_OUT> bction) {
            if (!isPbrbllel()) {
                sourceStbgeSpliterbtor().forEbchRembining(bction);
            }
            else {
                super.forEbchOrdered(bction);
            }
        }
    }

    /**
     * Bbse clbss for b stbteless intermedibte stbge of b Strebm.
     *
     * @pbrbm <E_IN> type of elements in the upstrebm source
     * @pbrbm <E_OUT> type of elements in produced by this stbge
     * @since 1.8
     */
    bbstrbct stbtic clbss StbtelessOp<E_IN, E_OUT>
            extends ReferencePipeline<E_IN, E_OUT> {
        /**
         * Construct b new Strebm by bppending b stbteless intermedibte
         * operbtion to bn existing strebm.
         *
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
     * Bbse clbss for b stbteful intermedibte stbge of b Strebm.
     *
     * @pbrbm <E_IN> type of elements in the upstrebm source
     * @pbrbm <E_OUT> type of elements in produced by this stbge
     * @since 1.8
     */
    bbstrbct stbtic clbss StbtefulOp<E_IN, E_OUT>
            extends ReferencePipeline<E_IN, E_OUT> {
        /**
         * Construct b new Strebm by bppending b stbteful intermedibte operbtion
         * to bn existing strebm.
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
        bbstrbct <P_IN> Node<E_OUT> opEvblubtePbrbllel(PipelineHelper<E_OUT> helper,
                                                       Spliterbtor<P_IN> spliterbtor,
                                                       IntFunction<E_OUT[]> generbtor);
    }
}
