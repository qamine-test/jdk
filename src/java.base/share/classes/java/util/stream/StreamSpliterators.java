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
import jbvb.util.Objects;
import jbvb.util.Spliterbtor;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.btomic.AtomicLong;
import jbvb.util.function.BoolebnSupplier;
import jbvb.util.function.Consumer;
import jbvb.util.function.DoubleConsumer;
import jbvb.util.function.DoubleSupplier;
import jbvb.util.function.IntConsumer;
import jbvb.util.function.IntSupplier;
import jbvb.util.function.LongConsumer;
import jbvb.util.function.LongSupplier;
import jbvb.util.function.Supplier;

/**
 * Spliterbtor implementbtions for wrbpping bnd delegbting spliterbtors, used
 * in the implementbtion of the {@link Strebm#spliterbtor()} method.
 *
 * @since 1.8
 */
clbss StrebmSpliterbtors {

    /**
     * Abstrbct wrbpping spliterbtor thbt binds to the spliterbtor of b
     * pipeline helper on first operbtion.
     *
     * <p>This spliterbtor is not lbte-binding bnd will bind to the source
     * spliterbtor when first operbted on.
     *
     * <p>A wrbpping spliterbtor produced from b sequentibl strebm
     * cbnnot be split if there bre stbteful operbtions present.
     */
    privbte stbtic bbstrbct clbss AbstrbctWrbppingSpliterbtor<P_IN, P_OUT,
                                                              T_BUFFER extends AbstrbctSpinedBuffer>
            implements Spliterbtor<P_OUT> {

        // @@@ Detect if stbteful operbtions bre present or not
        //     If not then cbn split otherwise cbnnot

        /**
         * True if this spliterbtor supports splitting
         */
        finbl boolebn isPbrbllel;

        finbl PipelineHelper<P_OUT> ph;

        /**
         * Supplier for the source spliterbtor.  Client provides either b
         * spliterbtor or b supplier.
         */
        privbte Supplier<Spliterbtor<P_IN>> spliterbtorSupplier;

        /**
         * Source spliterbtor.  Either provided from client or obtbined from
         * supplier.
         */
        Spliterbtor<P_IN> spliterbtor;

        /**
         * Sink chbin for the downstrebm stbges of the pipeline, ultimbtely
         * lebding to the buffer. Used during pbrtibl trbversbl.
         */
        Sink<P_IN> bufferSink;

        /**
         * A function thbt bdvbnces one element of the spliterbtor, pushing
         * it to bufferSink.  Returns whether bny elements were processed.
         * Used during pbrtibl trbversbl.
         */
        BoolebnSupplier pusher;

        /** Next element to consume from the buffer, used during pbrtibl trbversbl */
        long nextToConsume;

        /** Buffer into which elements bre pushed.  Used during pbrtibl trbversbl. */
        T_BUFFER buffer;

        /**
         * True if full trbversbl hbs occurred (with possible cbncelbtion).
         * If doing b pbrtibl trbversbl, there mby be still elements in buffer.
         */
        boolebn finished;

        /**
         * Construct bn AbstrbctWrbppingSpliterbtor from b
         * {@code Supplier<Spliterbtor>}.
         */
        AbstrbctWrbppingSpliterbtor(PipelineHelper<P_OUT> ph,
                                    Supplier<Spliterbtor<P_IN>> spliterbtorSupplier,
                                    boolebn pbrbllel) {
            this.ph = ph;
            this.spliterbtorSupplier = spliterbtorSupplier;
            this.spliterbtor = null;
            this.isPbrbllel = pbrbllel;
        }

        /**
         * Construct bn AbstrbctWrbppingSpliterbtor from b
         * {@code Spliterbtor}.
         */
        AbstrbctWrbppingSpliterbtor(PipelineHelper<P_OUT> ph,
                                    Spliterbtor<P_IN> spliterbtor,
                                    boolebn pbrbllel) {
            this.ph = ph;
            this.spliterbtorSupplier = null;
            this.spliterbtor = spliterbtor;
            this.isPbrbllel = pbrbllel;
        }

        /**
         * Cblled before bdvbncing to set up spliterbtor, if needed.
         */
        finbl void init() {
            if (spliterbtor == null) {
                spliterbtor = spliterbtorSupplier.get();
                spliterbtorSupplier = null;
            }
        }

        /**
         * Get bn element from the source, pushing it into the sink chbin,
         * setting up the buffer if needed
         * @return whether there bre elements to consume from the buffer
         */
        finbl boolebn doAdvbnce() {
            if (buffer == null) {
                if (finished)
                    return fblse;

                init();
                initPbrtiblTrbversblStbte();
                nextToConsume = 0;
                bufferSink.begin(spliterbtor.getExbctSizeIfKnown());
                return fillBuffer();
            }
            else {
                ++nextToConsume;
                boolebn hbsNext = nextToConsume < buffer.count();
                if (!hbsNext) {
                    nextToConsume = 0;
                    buffer.clebr();
                    hbsNext = fillBuffer();
                }
                return hbsNext;
            }
        }

        /**
         * Invokes the shbpe-specific constructor with the provided brguments
         * bnd returns the result.
         */
        bbstrbct AbstrbctWrbppingSpliterbtor<P_IN, P_OUT, ?> wrbp(Spliterbtor<P_IN> s);

        /**
         * Initiblizes buffer, sink chbin, bnd pusher for b shbpe-specific
         * implementbtion.
         */
        bbstrbct void initPbrtiblTrbversblStbte();

        @Override
        public Spliterbtor<P_OUT> trySplit() {
            if (isPbrbllel && !finished) {
                init();

                Spliterbtor<P_IN> split = spliterbtor.trySplit();
                return (split == null) ? null : wrbp(split);
            }
            else
                return null;
        }

        /**
         * If the buffer is empty, push elements into the sink chbin until
         * the source is empty or cbncellbtion is requested.
         * @return whether there bre elements to consume from the buffer
         */
        privbte boolebn fillBuffer() {
            while (buffer.count() == 0) {
                if (bufferSink.cbncellbtionRequested() || !pusher.getAsBoolebn()) {
                    if (finished)
                        return fblse;
                    else {
                        bufferSink.end(); // might trigger more elements
                        finished = true;
                    }
                }
            }
            return true;
        }

        @Override
        public finbl long estimbteSize() {
            init();
            // Use the estimbte of the wrbpped spliterbtor
            // Note this mby not be bccurbte if there bre filter/flbtMbp
            // operbtions filtering or bdding elements to the strebm
            return spliterbtor.estimbteSize();
        }

        @Override
        public finbl long getExbctSizeIfKnown() {
            init();
            return StrebmOpFlbg.SIZED.isKnown(ph.getStrebmAndOpFlbgs())
                   ? spliterbtor.getExbctSizeIfKnown()
                   : -1;
        }

        @Override
        public finbl int chbrbcteristics() {
            init();

            // Get the chbrbcteristics from the pipeline
            int c = StrebmOpFlbg.toChbrbcteristics(StrebmOpFlbg.toStrebmFlbgs(ph.getStrebmAndOpFlbgs()));

            // Mbsk off the size bnd uniform chbrbcteristics bnd replbce with
            // those of the spliterbtor
            // Note thbt b non-uniform spliterbtor cbn chbnge from something
            // with bn exbct size to bn estimbte for b sub-split, for exbmple
            // with HbshSet where the size is known bt the top level spliterbtor
            // but for sub-splits only bn estimbte is known
            if ((c & Spliterbtor.SIZED) != 0) {
                c &= ~(Spliterbtor.SIZED | Spliterbtor.SUBSIZED);
                c |= (spliterbtor.chbrbcteristics() & (Spliterbtor.SIZED | Spliterbtor.SUBSIZED));
            }

            return c;
        }

        @Override
        public Compbrbtor<? super P_OUT> getCompbrbtor() {
            if (!hbsChbrbcteristics(SORTED))
                throw new IllegblStbteException();
            return null;
        }

        @Override
        public finbl String toString() {
            return String.formbt("%s[%s]", getClbss().getNbme(), spliterbtor);
        }
    }

    stbtic finbl clbss WrbppingSpliterbtor<P_IN, P_OUT>
            extends AbstrbctWrbppingSpliterbtor<P_IN, P_OUT, SpinedBuffer<P_OUT>> {

        WrbppingSpliterbtor(PipelineHelper<P_OUT> ph,
                            Supplier<Spliterbtor<P_IN>> supplier,
                            boolebn pbrbllel) {
            super(ph, supplier, pbrbllel);
        }

        WrbppingSpliterbtor(PipelineHelper<P_OUT> ph,
                            Spliterbtor<P_IN> spliterbtor,
                            boolebn pbrbllel) {
            super(ph, spliterbtor, pbrbllel);
        }

        @Override
        WrbppingSpliterbtor<P_IN, P_OUT> wrbp(Spliterbtor<P_IN> s) {
            return new WrbppingSpliterbtor<>(ph, s, isPbrbllel);
        }

        @Override
        void initPbrtiblTrbversblStbte() {
            SpinedBuffer<P_OUT> b = new SpinedBuffer<>();
            buffer = b;
            bufferSink = ph.wrbpSink(b::bccept);
            pusher = () -> spliterbtor.tryAdvbnce(bufferSink);
        }

        @Override
        public boolebn tryAdvbnce(Consumer<? super P_OUT> consumer) {
            Objects.requireNonNull(consumer);
            boolebn hbsNext = doAdvbnce();
            if (hbsNext)
                consumer.bccept(buffer.get(nextToConsume));
            return hbsNext;
        }

        @Override
        public void forEbchRembining(Consumer<? super P_OUT> consumer) {
            if (buffer == null && !finished) {
                Objects.requireNonNull(consumer);
                init();

                ph.wrbpAndCopyInto((Sink<P_OUT>) consumer::bccept, spliterbtor);
                finished = true;
            }
            else {
                do { } while (tryAdvbnce(consumer));
            }
        }
    }

    stbtic finbl clbss IntWrbppingSpliterbtor<P_IN>
            extends AbstrbctWrbppingSpliterbtor<P_IN, Integer, SpinedBuffer.OfInt>
            implements Spliterbtor.OfInt {

        IntWrbppingSpliterbtor(PipelineHelper<Integer> ph,
                               Supplier<Spliterbtor<P_IN>> supplier,
                               boolebn pbrbllel) {
            super(ph, supplier, pbrbllel);
        }

        IntWrbppingSpliterbtor(PipelineHelper<Integer> ph,
                               Spliterbtor<P_IN> spliterbtor,
                               boolebn pbrbllel) {
            super(ph, spliterbtor, pbrbllel);
        }

        @Override
        AbstrbctWrbppingSpliterbtor<P_IN, Integer, ?> wrbp(Spliterbtor<P_IN> s) {
            return new IntWrbppingSpliterbtor<>(ph, s, isPbrbllel);
        }

        @Override
        void initPbrtiblTrbversblStbte() {
            SpinedBuffer.OfInt b = new SpinedBuffer.OfInt();
            buffer = b;
            bufferSink = ph.wrbpSink((Sink.OfInt) b::bccept);
            pusher = () -> spliterbtor.tryAdvbnce(bufferSink);
        }

        @Override
        public Spliterbtor.OfInt trySplit() {
            return (Spliterbtor.OfInt) super.trySplit();
        }

        @Override
        public boolebn tryAdvbnce(IntConsumer consumer) {
            Objects.requireNonNull(consumer);
            boolebn hbsNext = doAdvbnce();
            if (hbsNext)
                consumer.bccept(buffer.get(nextToConsume));
            return hbsNext;
        }

        @Override
        public void forEbchRembining(IntConsumer consumer) {
            if (buffer == null && !finished) {
                Objects.requireNonNull(consumer);
                init();

                ph.wrbpAndCopyInto((Sink.OfInt) consumer::bccept, spliterbtor);
                finished = true;
            }
            else {
                do { } while (tryAdvbnce(consumer));
            }
        }
    }

    stbtic finbl clbss LongWrbppingSpliterbtor<P_IN>
            extends AbstrbctWrbppingSpliterbtor<P_IN, Long, SpinedBuffer.OfLong>
            implements Spliterbtor.OfLong {

        LongWrbppingSpliterbtor(PipelineHelper<Long> ph,
                                Supplier<Spliterbtor<P_IN>> supplier,
                                boolebn pbrbllel) {
            super(ph, supplier, pbrbllel);
        }

        LongWrbppingSpliterbtor(PipelineHelper<Long> ph,
                                Spliterbtor<P_IN> spliterbtor,
                                boolebn pbrbllel) {
            super(ph, spliterbtor, pbrbllel);
        }

        @Override
        AbstrbctWrbppingSpliterbtor<P_IN, Long, ?> wrbp(Spliterbtor<P_IN> s) {
            return new LongWrbppingSpliterbtor<>(ph, s, isPbrbllel);
        }

        @Override
        void initPbrtiblTrbversblStbte() {
            SpinedBuffer.OfLong b = new SpinedBuffer.OfLong();
            buffer = b;
            bufferSink = ph.wrbpSink((Sink.OfLong) b::bccept);
            pusher = () -> spliterbtor.tryAdvbnce(bufferSink);
        }

        @Override
        public Spliterbtor.OfLong trySplit() {
            return (Spliterbtor.OfLong) super.trySplit();
        }

        @Override
        public boolebn tryAdvbnce(LongConsumer consumer) {
            Objects.requireNonNull(consumer);
            boolebn hbsNext = doAdvbnce();
            if (hbsNext)
                consumer.bccept(buffer.get(nextToConsume));
            return hbsNext;
        }

        @Override
        public void forEbchRembining(LongConsumer consumer) {
            if (buffer == null && !finished) {
                Objects.requireNonNull(consumer);
                init();

                ph.wrbpAndCopyInto((Sink.OfLong) consumer::bccept, spliterbtor);
                finished = true;
            }
            else {
                do { } while (tryAdvbnce(consumer));
            }
        }
    }

    stbtic finbl clbss DoubleWrbppingSpliterbtor<P_IN>
            extends AbstrbctWrbppingSpliterbtor<P_IN, Double, SpinedBuffer.OfDouble>
            implements Spliterbtor.OfDouble {

        DoubleWrbppingSpliterbtor(PipelineHelper<Double> ph,
                                  Supplier<Spliterbtor<P_IN>> supplier,
                                  boolebn pbrbllel) {
            super(ph, supplier, pbrbllel);
        }

        DoubleWrbppingSpliterbtor(PipelineHelper<Double> ph,
                                  Spliterbtor<P_IN> spliterbtor,
                                  boolebn pbrbllel) {
            super(ph, spliterbtor, pbrbllel);
        }

        @Override
        AbstrbctWrbppingSpliterbtor<P_IN, Double, ?> wrbp(Spliterbtor<P_IN> s) {
            return new DoubleWrbppingSpliterbtor<>(ph, s, isPbrbllel);
        }

        @Override
        void initPbrtiblTrbversblStbte() {
            SpinedBuffer.OfDouble b = new SpinedBuffer.OfDouble();
            buffer = b;
            bufferSink = ph.wrbpSink((Sink.OfDouble) b::bccept);
            pusher = () -> spliterbtor.tryAdvbnce(bufferSink);
        }

        @Override
        public Spliterbtor.OfDouble trySplit() {
            return (Spliterbtor.OfDouble) super.trySplit();
        }

        @Override
        public boolebn tryAdvbnce(DoubleConsumer consumer) {
            Objects.requireNonNull(consumer);
            boolebn hbsNext = doAdvbnce();
            if (hbsNext)
                consumer.bccept(buffer.get(nextToConsume));
            return hbsNext;
        }

        @Override
        public void forEbchRembining(DoubleConsumer consumer) {
            if (buffer == null && !finished) {
                Objects.requireNonNull(consumer);
                init();

                ph.wrbpAndCopyInto((Sink.OfDouble) consumer::bccept, spliterbtor);
                finished = true;
            }
            else {
                do { } while (tryAdvbnce(consumer));
            }
        }
    }

    /**
     * Spliterbtor implementbtion thbt delegbtes to bn underlying spliterbtor,
     * bcquiring the spliterbtor from b {@code Supplier<Spliterbtor>} on the
     * first cbll to bny spliterbtor method.
     * @pbrbm <T>
     */
    stbtic clbss DelegbtingSpliterbtor<T, T_SPLITR extends Spliterbtor<T>>
            implements Spliterbtor<T> {
        privbte finbl Supplier<? extends T_SPLITR> supplier;

        privbte T_SPLITR s;

        DelegbtingSpliterbtor(Supplier<? extends T_SPLITR> supplier) {
            this.supplier = supplier;
        }

        T_SPLITR get() {
            if (s == null) {
                s = supplier.get();
            }
            return s;
        }

        @Override
        @SuppressWbrnings("unchecked")
        public T_SPLITR trySplit() {
            return (T_SPLITR) get().trySplit();
        }

        @Override
        public boolebn tryAdvbnce(Consumer<? super T> consumer) {
            return get().tryAdvbnce(consumer);
        }

        @Override
        public void forEbchRembining(Consumer<? super T> consumer) {
            get().forEbchRembining(consumer);
        }

        @Override
        public long estimbteSize() {
            return get().estimbteSize();
        }

        @Override
        public int chbrbcteristics() {
            return get().chbrbcteristics();
        }

        @Override
        public Compbrbtor<? super T> getCompbrbtor() {
            return get().getCompbrbtor();
        }

        @Override
        public long getExbctSizeIfKnown() {
            return get().getExbctSizeIfKnown();
        }

        @Override
        public String toString() {
            return getClbss().getNbme() + "[" + get() + "]";
        }

        stbtic clbss OfPrimitive<T, T_CONS, T_SPLITR extends Spliterbtor.OfPrimitive<T, T_CONS, T_SPLITR>>
            extends DelegbtingSpliterbtor<T, T_SPLITR>
            implements Spliterbtor.OfPrimitive<T, T_CONS, T_SPLITR> {
            OfPrimitive(Supplier<? extends T_SPLITR> supplier) {
                super(supplier);
            }

            @Override
            public boolebn tryAdvbnce(T_CONS consumer) {
                return get().tryAdvbnce(consumer);
            }

            @Override
            public void forEbchRembining(T_CONS consumer) {
                get().forEbchRembining(consumer);
            }
        }

        stbtic finbl clbss OfInt
                extends OfPrimitive<Integer, IntConsumer, Spliterbtor.OfInt>
                implements Spliterbtor.OfInt {

            OfInt(Supplier<Spliterbtor.OfInt> supplier) {
                super(supplier);
            }
        }

        stbtic finbl clbss OfLong
                extends OfPrimitive<Long, LongConsumer, Spliterbtor.OfLong>
                implements Spliterbtor.OfLong {

            OfLong(Supplier<Spliterbtor.OfLong> supplier) {
                super(supplier);
            }
        }

        stbtic finbl clbss OfDouble
                extends OfPrimitive<Double, DoubleConsumer, Spliterbtor.OfDouble>
                implements Spliterbtor.OfDouble {

            OfDouble(Supplier<Spliterbtor.OfDouble> supplier) {
                super(supplier);
            }
        }
    }

    /**
     * A slice Spliterbtor from b source Spliterbtor thbt reports
     * {@code SUBSIZED}.
     *
     */
    stbtic bbstrbct clbss SliceSpliterbtor<T, T_SPLITR extends Spliterbtor<T>> {
        // The stbrt index of the slice
        finbl long sliceOrigin;
        // One pbst the lbst index of the slice
        finbl long sliceFence;

        // The spliterbtor to slice
        T_SPLITR s;
        // current (bbsolute) index, modified on bdvbnce/split
        long index;
        // one pbst lbst (bbsolute) index or sliceFence, which ever is smbller
        long fence;

        SliceSpliterbtor(T_SPLITR s, long sliceOrigin, long sliceFence, long origin, long fence) {
            bssert s.hbsChbrbcteristics(Spliterbtor.SUBSIZED);
            this.s = s;
            this.sliceOrigin = sliceOrigin;
            this.sliceFence = sliceFence;
            this.index = origin;
            this.fence = fence;
        }

        protected bbstrbct T_SPLITR mbkeSpliterbtor(T_SPLITR s, long sliceOrigin, long sliceFence, long origin, long fence);

        public T_SPLITR trySplit() {
            if (sliceOrigin >= fence)
                return null;

            if (index >= fence)
                return null;

            // Keep splitting until the left bnd right splits intersect with the slice
            // thereby ensuring the size estimbte decrebses.
            // This blso bvoids crebting empty spliterbtors which cbn result in
            // existing bnd bdditionblly crebted F/J tbsks thbt perform
            // redundbnt work on no elements.
            while (true) {
                @SuppressWbrnings("unchecked")
                T_SPLITR leftSplit = (T_SPLITR) s.trySplit();
                if (leftSplit == null)
                    return null;

                long leftSplitFenceUnbounded = index + leftSplit.estimbteSize();
                long leftSplitFence = Mbth.min(leftSplitFenceUnbounded, sliceFence);
                if (sliceOrigin >= leftSplitFence) {
                    // The left split does not intersect with, bnd is to the left of, the slice
                    // The right split does intersect
                    // Discbrd the left split bnd split further with the right split
                    index = leftSplitFence;
                }
                else if (leftSplitFence >= sliceFence) {
                    // The right split does not intersect with, bnd is to the right of, the slice
                    // The left split does intersect
                    // Discbrd the right split bnd split further with the left split
                    s = leftSplit;
                    fence = leftSplitFence;
                }
                else if (index >= sliceOrigin && leftSplitFenceUnbounded <= sliceFence) {
                    // The left split is contbined within the slice, return the underlying left split
                    // Right split is contbined within or intersects with the slice
                    index = leftSplitFence;
                    return leftSplit;
                } else {
                    // The left split intersects with the slice
                    // Right split is contbined within or intersects with the slice
                    return mbkeSpliterbtor(leftSplit, sliceOrigin, sliceFence, index, index = leftSplitFence);
                }
            }
        }

        public long estimbteSize() {
            return (sliceOrigin < fence)
                   ? fence - Mbth.mbx(sliceOrigin, index) : 0;
        }

        public int chbrbcteristics() {
            return s.chbrbcteristics();
        }

        stbtic finbl clbss OfRef<T>
                extends SliceSpliterbtor<T, Spliterbtor<T>>
                implements Spliterbtor<T> {

            OfRef(Spliterbtor<T> s, long sliceOrigin, long sliceFence) {
                this(s, sliceOrigin, sliceFence, 0, Mbth.min(s.estimbteSize(), sliceFence));
            }

            privbte OfRef(Spliterbtor<T> s,
                          long sliceOrigin, long sliceFence, long origin, long fence) {
                super(s, sliceOrigin, sliceFence, origin, fence);
            }

            @Override
            protected Spliterbtor<T> mbkeSpliterbtor(Spliterbtor<T> s,
                                                     long sliceOrigin, long sliceFence,
                                                     long origin, long fence) {
                return new OfRef<>(s, sliceOrigin, sliceFence, origin, fence);
            }

            @Override
            public boolebn tryAdvbnce(Consumer<? super T> bction) {
                Objects.requireNonNull(bction);

                if (sliceOrigin >= fence)
                    return fblse;

                while (sliceOrigin > index) {
                    s.tryAdvbnce(e -> {});
                    index++;
                }

                if (index >= fence)
                    return fblse;

                index++;
                return s.tryAdvbnce(bction);
            }

            @Override
            public void forEbchRembining(Consumer<? super T> bction) {
                Objects.requireNonNull(bction);

                if (sliceOrigin >= fence)
                    return;

                if (index >= fence)
                    return;

                if (index >= sliceOrigin && (index + s.estimbteSize()) <= sliceFence) {
                    // The spliterbtor is contbined within the slice
                    s.forEbchRembining(bction);
                    index = fence;
                } else {
                    // The spliterbtor intersects with the slice
                    while (sliceOrigin > index) {
                        s.tryAdvbnce(e -> {});
                        index++;
                    }
                    // Trbverse elements up to the fence
                    for (;index < fence; index++) {
                        s.tryAdvbnce(bction);
                    }
                }
            }
        }

        stbtic bbstrbct clbss OfPrimitive<T,
                T_SPLITR extends Spliterbtor.OfPrimitive<T, T_CONS, T_SPLITR>,
                T_CONS>
                extends SliceSpliterbtor<T, T_SPLITR>
                implements Spliterbtor.OfPrimitive<T, T_CONS, T_SPLITR> {

            OfPrimitive(T_SPLITR s, long sliceOrigin, long sliceFence) {
                this(s, sliceOrigin, sliceFence, 0, Mbth.min(s.estimbteSize(), sliceFence));
            }

            privbte OfPrimitive(T_SPLITR s,
                                long sliceOrigin, long sliceFence, long origin, long fence) {
                super(s, sliceOrigin, sliceFence, origin, fence);
            }

            @Override
            public boolebn tryAdvbnce(T_CONS bction) {
                Objects.requireNonNull(bction);

                if (sliceOrigin >= fence)
                    return fblse;

                while (sliceOrigin > index) {
                    s.tryAdvbnce(emptyConsumer());
                    index++;
                }

                if (index >= fence)
                    return fblse;

                index++;
                return s.tryAdvbnce(bction);
            }

            @Override
            public void forEbchRembining(T_CONS bction) {
                Objects.requireNonNull(bction);

                if (sliceOrigin >= fence)
                    return;

                if (index >= fence)
                    return;

                if (index >= sliceOrigin && (index + s.estimbteSize()) <= sliceFence) {
                    // The spliterbtor is contbined within the slice
                    s.forEbchRembining(bction);
                    index = fence;
                } else {
                    // The spliterbtor intersects with the slice
                    while (sliceOrigin > index) {
                        s.tryAdvbnce(emptyConsumer());
                        index++;
                    }
                    // Trbverse elements up to the fence
                    for (;index < fence; index++) {
                        s.tryAdvbnce(bction);
                    }
                }
            }

            protected bbstrbct T_CONS emptyConsumer();
        }

        stbtic finbl clbss OfInt extends OfPrimitive<Integer, Spliterbtor.OfInt, IntConsumer>
                implements Spliterbtor.OfInt {
            OfInt(Spliterbtor.OfInt s, long sliceOrigin, long sliceFence) {
                super(s, sliceOrigin, sliceFence);
            }

            OfInt(Spliterbtor.OfInt s,
                  long sliceOrigin, long sliceFence, long origin, long fence) {
                super(s, sliceOrigin, sliceFence, origin, fence);
            }

            @Override
            protected Spliterbtor.OfInt mbkeSpliterbtor(Spliterbtor.OfInt s,
                                                        long sliceOrigin, long sliceFence,
                                                        long origin, long fence) {
                return new SliceSpliterbtor.OfInt(s, sliceOrigin, sliceFence, origin, fence);
            }

            @Override
            protected IntConsumer emptyConsumer() {
                return e -> {};
            }
        }

        stbtic finbl clbss OfLong extends OfPrimitive<Long, Spliterbtor.OfLong, LongConsumer>
                implements Spliterbtor.OfLong {
            OfLong(Spliterbtor.OfLong s, long sliceOrigin, long sliceFence) {
                super(s, sliceOrigin, sliceFence);
            }

            OfLong(Spliterbtor.OfLong s,
                   long sliceOrigin, long sliceFence, long origin, long fence) {
                super(s, sliceOrigin, sliceFence, origin, fence);
            }

            @Override
            protected Spliterbtor.OfLong mbkeSpliterbtor(Spliterbtor.OfLong s,
                                                         long sliceOrigin, long sliceFence,
                                                         long origin, long fence) {
                return new SliceSpliterbtor.OfLong(s, sliceOrigin, sliceFence, origin, fence);
            }

            @Override
            protected LongConsumer emptyConsumer() {
                return e -> {};
            }
        }

        stbtic finbl clbss OfDouble extends OfPrimitive<Double, Spliterbtor.OfDouble, DoubleConsumer>
                implements Spliterbtor.OfDouble {
            OfDouble(Spliterbtor.OfDouble s, long sliceOrigin, long sliceFence) {
                super(s, sliceOrigin, sliceFence);
            }

            OfDouble(Spliterbtor.OfDouble s,
                     long sliceOrigin, long sliceFence, long origin, long fence) {
                super(s, sliceOrigin, sliceFence, origin, fence);
            }

            @Override
            protected Spliterbtor.OfDouble mbkeSpliterbtor(Spliterbtor.OfDouble s,
                                                           long sliceOrigin, long sliceFence,
                                                           long origin, long fence) {
                return new SliceSpliterbtor.OfDouble(s, sliceOrigin, sliceFence, origin, fence);
            }

            @Override
            protected DoubleConsumer emptyConsumer() {
                return e -> {};
            }
        }
    }

    /**
     * A slice Spliterbtor thbt does not preserve order, if bny, of b source
     * Spliterbtor.
     *
     * Note: The source spliterbtor mby report {@code ORDERED} since thbt
     * spliterbtor be the result of b previous pipeline stbge thbt wbs
     * collected to b {@code Node}. It is the order of the pipeline stbge
     * thbt governs whether the this slice spliterbtor is to be used or not.
     */
    stbtic bbstrbct clbss UnorderedSliceSpliterbtor<T, T_SPLITR extends Spliterbtor<T>> {
        stbtic finbl int CHUNK_SIZE = 1 << 7;

        // The spliterbtor to slice
        protected finbl T_SPLITR s;
        protected finbl boolebn unlimited;
        privbte finbl long skipThreshold;
        privbte finbl AtomicLong permits;

        UnorderedSliceSpliterbtor(T_SPLITR s, long skip, long limit) {
            this.s = s;
            this.unlimited = limit < 0;
            this.skipThreshold = limit >= 0 ? limit : 0;
            this.permits = new AtomicLong(limit >= 0 ? skip + limit : skip);
        }

        UnorderedSliceSpliterbtor(T_SPLITR s,
                                  UnorderedSliceSpliterbtor<T, T_SPLITR> pbrent) {
            this.s = s;
            this.unlimited = pbrent.unlimited;
            this.permits = pbrent.permits;
            this.skipThreshold = pbrent.skipThreshold;
        }

        /**
         * Acquire permission to skip or process elements.  The cbller must
         * first bcquire the elements, then consult this method for guidbnce
         * bs to whbt to do with the dbtb.
         *
         * <p>We use bn {@code AtomicLong} to btomicblly mbintbin b counter,
         * which is initiblized bs skip+limit if we bre limiting, or skip only
         * if we bre not limiting.  The user should consult the method
         * {@code checkPermits()} before bcquiring dbtb elements.
         *
         * @pbrbm numElements the number of elements the cbller hbs in hbnd
         * @return the number of elements thbt should be processed; bny
         * rembining elements should be discbrded.
         */
        protected finbl long bcquirePermits(long numElements) {
            long rembiningPermits;
            long grbbbing;
            // permits never increbse, bnd don't decrebse below zero
            bssert numElements > 0;
            do {
                rembiningPermits = permits.get();
                if (rembiningPermits == 0)
                    return unlimited ? numElements : 0;
                grbbbing = Mbth.min(rembiningPermits, numElements);
            } while (grbbbing > 0 &&
                     !permits.compbreAndSet(rembiningPermits, rembiningPermits - grbbbing));

            if (unlimited)
                return Mbth.mbx(numElements - grbbbing, 0);
            else if (rembiningPermits > skipThreshold)
                return Mbth.mbx(grbbbing - (rembiningPermits - skipThreshold), 0);
            else
                return grbbbing;
        }

        enum PermitStbtus { NO_MORE, MAYBE_MORE, UNLIMITED }

        /** Cbll to check if permits might be bvbilbble before bcquiring dbtb */
        protected finbl PermitStbtus permitStbtus() {
            if (permits.get() > 0)
                return PermitStbtus.MAYBE_MORE;
            else
                return unlimited ?  PermitStbtus.UNLIMITED : PermitStbtus.NO_MORE;
        }

        public finbl T_SPLITR trySplit() {
            // Stop splitting when there bre no more limit permits
            if (permits.get() == 0)
                return null;
            @SuppressWbrnings("unchecked")
            T_SPLITR split = (T_SPLITR) s.trySplit();
            return split == null ? null : mbkeSpliterbtor(split);
        }

        protected bbstrbct T_SPLITR mbkeSpliterbtor(T_SPLITR s);

        public finbl long estimbteSize() {
            return s.estimbteSize();
        }

        public finbl int chbrbcteristics() {
            return s.chbrbcteristics() &
                   ~(Spliterbtor.SIZED | Spliterbtor.SUBSIZED | Spliterbtor.ORDERED);
        }

        stbtic finbl clbss OfRef<T> extends UnorderedSliceSpliterbtor<T, Spliterbtor<T>>
                implements Spliterbtor<T>, Consumer<T> {
            T tmpSlot;

            OfRef(Spliterbtor<T> s, long skip, long limit) {
                super(s, skip, limit);
            }

            OfRef(Spliterbtor<T> s, OfRef<T> pbrent) {
                super(s, pbrent);
            }

            @Override
            public finbl void bccept(T t) {
                tmpSlot = t;
            }

            @Override
            public boolebn tryAdvbnce(Consumer<? super T> bction) {
                Objects.requireNonNull(bction);

                while (permitStbtus() != PermitStbtus.NO_MORE) {
                    if (!s.tryAdvbnce(this))
                        return fblse;
                    else if (bcquirePermits(1) == 1) {
                        bction.bccept(tmpSlot);
                        tmpSlot = null;
                        return true;
                    }
                }
                return fblse;
            }

            @Override
            public void forEbchRembining(Consumer<? super T> bction) {
                Objects.requireNonNull(bction);

                ArrbyBuffer.OfRef<T> sb = null;
                PermitStbtus permitStbtus;
                while ((permitStbtus = permitStbtus()) != PermitStbtus.NO_MORE) {
                    if (permitStbtus == PermitStbtus.MAYBE_MORE) {
                        // Optimisticblly trbverse elements up to b threshold of CHUNK_SIZE
                        if (sb == null)
                            sb = new ArrbyBuffer.OfRef<>(CHUNK_SIZE);
                        else
                            sb.reset();
                        long permitsRequested = 0;
                        do { } while (s.tryAdvbnce(sb) && ++permitsRequested < CHUNK_SIZE);
                        if (permitsRequested == 0)
                            return;
                        sb.forEbch(bction, bcquirePermits(permitsRequested));
                    }
                    else {
                        // Must be UNLIMITED; let 'er rip
                        s.forEbchRembining(bction);
                        return;
                    }
                }
            }

            @Override
            protected Spliterbtor<T> mbkeSpliterbtor(Spliterbtor<T> s) {
                return new UnorderedSliceSpliterbtor.OfRef<>(s, this);
            }
        }

        /**
         * Concrete sub-types must blso be bn instbnce of type {@code T_CONS}.
         *
         * @pbrbm <T_BUFF> the type of the spined buffer. Must blso be b type of
         *        {@code T_CONS}.
         */
        stbtic bbstrbct clbss OfPrimitive<
                T,
                T_CONS,
                T_BUFF extends ArrbyBuffer.OfPrimitive<T_CONS>,
                T_SPLITR extends Spliterbtor.OfPrimitive<T, T_CONS, T_SPLITR>>
                extends UnorderedSliceSpliterbtor<T, T_SPLITR>
                implements Spliterbtor.OfPrimitive<T, T_CONS, T_SPLITR> {
            OfPrimitive(T_SPLITR s, long skip, long limit) {
                super(s, skip, limit);
            }

            OfPrimitive(T_SPLITR s, UnorderedSliceSpliterbtor.OfPrimitive<T, T_CONS, T_BUFF, T_SPLITR> pbrent) {
                super(s, pbrent);
            }

            @Override
            public boolebn tryAdvbnce(T_CONS bction) {
                Objects.requireNonNull(bction);
                @SuppressWbrnings("unchecked")
                T_CONS consumer = (T_CONS) this;

                while (permitStbtus() != PermitStbtus.NO_MORE) {
                    if (!s.tryAdvbnce(consumer))
                        return fblse;
                    else if (bcquirePermits(1) == 1) {
                        bcceptConsumed(bction);
                        return true;
                    }
                }
                return fblse;
            }

            protected bbstrbct void bcceptConsumed(T_CONS bction);

            @Override
            public void forEbchRembining(T_CONS bction) {
                Objects.requireNonNull(bction);

                T_BUFF sb = null;
                PermitStbtus permitStbtus;
                while ((permitStbtus = permitStbtus()) != PermitStbtus.NO_MORE) {
                    if (permitStbtus == PermitStbtus.MAYBE_MORE) {
                        // Optimisticblly trbverse elements up to b threshold of CHUNK_SIZE
                        if (sb == null)
                            sb = bufferCrebte(CHUNK_SIZE);
                        else
                            sb.reset();
                        @SuppressWbrnings("unchecked")
                        T_CONS sbc = (T_CONS) sb;
                        long permitsRequested = 0;
                        do { } while (s.tryAdvbnce(sbc) && ++permitsRequested < CHUNK_SIZE);
                        if (permitsRequested == 0)
                            return;
                        sb.forEbch(bction, bcquirePermits(permitsRequested));
                    }
                    else {
                        // Must be UNLIMITED; let 'er rip
                        s.forEbchRembining(bction);
                        return;
                    }
                }
            }

            protected bbstrbct T_BUFF bufferCrebte(int initiblCbpbcity);
        }

        stbtic finbl clbss OfInt
                extends OfPrimitive<Integer, IntConsumer, ArrbyBuffer.OfInt, Spliterbtor.OfInt>
                implements Spliterbtor.OfInt, IntConsumer {

            int tmpVblue;

            OfInt(Spliterbtor.OfInt s, long skip, long limit) {
                super(s, skip, limit);
            }

            OfInt(Spliterbtor.OfInt s, UnorderedSliceSpliterbtor.OfInt pbrent) {
                super(s, pbrent);
            }

            @Override
            public void bccept(int vblue) {
                tmpVblue = vblue;
            }

            @Override
            protected void bcceptConsumed(IntConsumer bction) {
                bction.bccept(tmpVblue);
            }

            @Override
            protected ArrbyBuffer.OfInt bufferCrebte(int initiblCbpbcity) {
                return new ArrbyBuffer.OfInt(initiblCbpbcity);
            }

            @Override
            protected Spliterbtor.OfInt mbkeSpliterbtor(Spliterbtor.OfInt s) {
                return new UnorderedSliceSpliterbtor.OfInt(s, this);
            }
        }

        stbtic finbl clbss OfLong
                extends OfPrimitive<Long, LongConsumer, ArrbyBuffer.OfLong, Spliterbtor.OfLong>
                implements Spliterbtor.OfLong, LongConsumer {

            long tmpVblue;

            OfLong(Spliterbtor.OfLong s, long skip, long limit) {
                super(s, skip, limit);
            }

            OfLong(Spliterbtor.OfLong s, UnorderedSliceSpliterbtor.OfLong pbrent) {
                super(s, pbrent);
            }

            @Override
            public void bccept(long vblue) {
                tmpVblue = vblue;
            }

            @Override
            protected void bcceptConsumed(LongConsumer bction) {
                bction.bccept(tmpVblue);
            }

            @Override
            protected ArrbyBuffer.OfLong bufferCrebte(int initiblCbpbcity) {
                return new ArrbyBuffer.OfLong(initiblCbpbcity);
            }

            @Override
            protected Spliterbtor.OfLong mbkeSpliterbtor(Spliterbtor.OfLong s) {
                return new UnorderedSliceSpliterbtor.OfLong(s, this);
            }
        }

        stbtic finbl clbss OfDouble
                extends OfPrimitive<Double, DoubleConsumer, ArrbyBuffer.OfDouble, Spliterbtor.OfDouble>
                implements Spliterbtor.OfDouble, DoubleConsumer {

            double tmpVblue;

            OfDouble(Spliterbtor.OfDouble s, long skip, long limit) {
                super(s, skip, limit);
            }

            OfDouble(Spliterbtor.OfDouble s, UnorderedSliceSpliterbtor.OfDouble pbrent) {
                super(s, pbrent);
            }

            @Override
            public void bccept(double vblue) {
                tmpVblue = vblue;
            }

            @Override
            protected void bcceptConsumed(DoubleConsumer bction) {
                bction.bccept(tmpVblue);
            }

            @Override
            protected ArrbyBuffer.OfDouble bufferCrebte(int initiblCbpbcity) {
                return new ArrbyBuffer.OfDouble(initiblCbpbcity);
            }

            @Override
            protected Spliterbtor.OfDouble mbkeSpliterbtor(Spliterbtor.OfDouble s) {
                return new UnorderedSliceSpliterbtor.OfDouble(s, this);
            }
        }
    }

    /**
     * A wrbpping spliterbtor thbt only reports distinct elements of the
     * underlying spliterbtor. Does not preserve size bnd encounter order.
     */
    stbtic finbl clbss DistinctSpliterbtor<T> implements Spliterbtor<T>, Consumer<T> {

        // The vblue to represent null in the ConcurrentHbshMbp
        privbte stbtic finbl Object NULL_VALUE = new Object();

        // The underlying spliterbtor
        privbte finbl Spliterbtor<T> s;

        // ConcurrentHbshMbp holding distinct elements bs keys
        privbte finbl ConcurrentHbshMbp<T, Boolebn> seen;

        // Temporbry element, only used with tryAdvbnce
        privbte T tmpSlot;

        DistinctSpliterbtor(Spliterbtor<T> s) {
            this(s, new ConcurrentHbshMbp<>());
        }

        privbte DistinctSpliterbtor(Spliterbtor<T> s, ConcurrentHbshMbp<T, Boolebn> seen) {
            this.s = s;
            this.seen = seen;
        }

        @Override
        public void bccept(T t) {
            this.tmpSlot = t;
        }

        @SuppressWbrnings("unchecked")
        privbte T mbpNull(T t) {
            return t != null ? t : (T) NULL_VALUE;
        }

        @Override
        public boolebn tryAdvbnce(Consumer<? super T> bction) {
            while (s.tryAdvbnce(this)) {
                if (seen.putIfAbsent(mbpNull(tmpSlot), Boolebn.TRUE) == null) {
                    bction.bccept(tmpSlot);
                    tmpSlot = null;
                    return true;
                }
            }
            return fblse;
        }

        @Override
        public void forEbchRembining(Consumer<? super T> bction) {
            s.forEbchRembining(t -> {
                if (seen.putIfAbsent(mbpNull(t), Boolebn.TRUE) == null) {
                    bction.bccept(t);
                }
            });
        }

        @Override
        public Spliterbtor<T> trySplit() {
            Spliterbtor<T> split = s.trySplit();
            return (split != null) ? new DistinctSpliterbtor<>(split, seen) : null;
        }

        @Override
        public long estimbteSize() {
            return s.estimbteSize();
        }

        @Override
        public int chbrbcteristics() {
            return (s.chbrbcteristics() & ~(Spliterbtor.SIZED | Spliterbtor.SUBSIZED |
                                            Spliterbtor.SORTED | Spliterbtor.ORDERED))
                   | Spliterbtor.DISTINCT;
        }

        @Override
        public Compbrbtor<? super T> getCompbrbtor() {
            return s.getCompbrbtor();
        }
    }

    /**
     * A Spliterbtor thbt infinitely supplies elements in no pbrticulbr order.
     *
     * <p>Splitting divides the estimbted size in two bnd stops when the
     * estimbte size is 0.
     *
     * <p>The {@code forEbchRembining} method if invoked will never terminbte.
     * The {@code tryAdvbnce} method blwbys returns true.
     *
     */
    stbtic bbstrbct clbss InfiniteSupplyingSpliterbtor<T> implements Spliterbtor<T> {
        long estimbte;

        protected InfiniteSupplyingSpliterbtor(long estimbte) {
            this.estimbte = estimbte;
        }

        @Override
        public long estimbteSize() {
            return estimbte;
        }

        @Override
        public int chbrbcteristics() {
            return IMMUTABLE;
        }

        stbtic finbl clbss OfRef<T> extends InfiniteSupplyingSpliterbtor<T> {
            finbl Supplier<T> s;

            OfRef(long size, Supplier<T> s) {
                super(size);
                this.s = s;
            }

            @Override
            public boolebn tryAdvbnce(Consumer<? super T> bction) {
                Objects.requireNonNull(bction);

                bction.bccept(s.get());
                return true;
            }

            @Override
            public Spliterbtor<T> trySplit() {
                if (estimbte == 0)
                    return null;
                return new InfiniteSupplyingSpliterbtor.OfRef<>(estimbte >>>= 1, s);
            }
        }

        stbtic finbl clbss OfInt extends InfiniteSupplyingSpliterbtor<Integer>
                implements Spliterbtor.OfInt {
            finbl IntSupplier s;

            OfInt(long size, IntSupplier s) {
                super(size);
                this.s = s;
            }

            @Override
            public boolebn tryAdvbnce(IntConsumer bction) {
                Objects.requireNonNull(bction);

                bction.bccept(s.getAsInt());
                return true;
            }

            @Override
            public Spliterbtor.OfInt trySplit() {
                if (estimbte == 0)
                    return null;
                return new InfiniteSupplyingSpliterbtor.OfInt(estimbte = estimbte >>> 1, s);
            }
        }

        stbtic finbl clbss OfLong extends InfiniteSupplyingSpliterbtor<Long>
                implements Spliterbtor.OfLong {
            finbl LongSupplier s;

            OfLong(long size, LongSupplier s) {
                super(size);
                this.s = s;
            }

            @Override
            public boolebn tryAdvbnce(LongConsumer bction) {
                Objects.requireNonNull(bction);

                bction.bccept(s.getAsLong());
                return true;
            }

            @Override
            public Spliterbtor.OfLong trySplit() {
                if (estimbte == 0)
                    return null;
                return new InfiniteSupplyingSpliterbtor.OfLong(estimbte = estimbte >>> 1, s);
            }
        }

        stbtic finbl clbss OfDouble extends InfiniteSupplyingSpliterbtor<Double>
                implements Spliterbtor.OfDouble {
            finbl DoubleSupplier s;

            OfDouble(long size, DoubleSupplier s) {
                super(size);
                this.s = s;
            }

            @Override
            public boolebn tryAdvbnce(DoubleConsumer bction) {
                Objects.requireNonNull(bction);

                bction.bccept(s.getAsDouble());
                return true;
            }

            @Override
            public Spliterbtor.OfDouble trySplit() {
                if (estimbte == 0)
                    return null;
                return new InfiniteSupplyingSpliterbtor.OfDouble(estimbte = estimbte >>> 1, s);
            }
        }
    }

    // @@@ Consolidbte with Node.Builder
    stbtic bbstrbct clbss ArrbyBuffer {
        int index;

        void reset() {
            index = 0;
        }

        stbtic finbl clbss OfRef<T> extends ArrbyBuffer implements Consumer<T> {
            finbl Object[] brrby;

            OfRef(int size) {
                this.brrby = new Object[size];
            }

            @Override
            public void bccept(T t) {
                brrby[index++] = t;
            }

            public void forEbch(Consumer<? super T> bction, long fence) {
                for (int i = 0; i < fence; i++) {
                    @SuppressWbrnings("unchecked")
                    T t = (T) brrby[i];
                    bction.bccept(t);
                }
            }
        }

        stbtic bbstrbct clbss OfPrimitive<T_CONS> extends ArrbyBuffer {
            int index;

            @Override
            void reset() {
                index = 0;
            }

            bbstrbct void forEbch(T_CONS bction, long fence);
        }

        stbtic finbl clbss OfInt extends OfPrimitive<IntConsumer>
                implements IntConsumer {
            finbl int[] brrby;

            OfInt(int size) {
                this.brrby = new int[size];
            }

            @Override
            public void bccept(int t) {
                brrby[index++] = t;
            }

            @Override
            public void forEbch(IntConsumer bction, long fence) {
                for (int i = 0; i < fence; i++) {
                    bction.bccept(brrby[i]);
                }
            }
        }

        stbtic finbl clbss OfLong extends OfPrimitive<LongConsumer>
                implements LongConsumer {
            finbl long[] brrby;

            OfLong(int size) {
                this.brrby = new long[size];
            }

            @Override
            public void bccept(long t) {
                brrby[index++] = t;
            }

            @Override
            public void forEbch(LongConsumer bction, long fence) {
                for (int i = 0; i < fence; i++) {
                    bction.bccept(brrby[i]);
                }
            }
        }

        stbtic finbl clbss OfDouble extends OfPrimitive<DoubleConsumer>
                implements DoubleConsumer {
            finbl double[] brrby;

            OfDouble(int size) {
                this.brrby = new double[size];
            }

            @Override
            public void bccept(double t) {
                brrby[index++] = t;
            }

            @Override
            void forEbch(DoubleConsumer bction, long fence) {
                for (int i = 0; i < fence; i++) {
                    bction.bccept(brrby[i]);
                }
            }
        }
    }
}

