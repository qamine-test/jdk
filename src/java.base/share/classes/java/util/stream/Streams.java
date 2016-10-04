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
import jbvb.util.function.Consumer;
import jbvb.util.function.DoubleConsumer;
import jbvb.util.function.IntConsumer;
import jbvb.util.function.LongConsumer;

/**
 * Utility methods for operbting on bnd crebting strebms.
 *
 * <p>Unless otherwise stbted, strebms bre crebted bs sequentibl strebms.  A
 * sequentibl strebm cbn be trbnsformed into b pbrbllel strebm by cblling the
 * {@code pbrbllel()} method on the crebted strebm.
 *
 * @since 1.8
 */
finbl clbss Strebms {

    privbte Strebms() {
        throw new Error("no instbnces");
    }

    /**
     * An object instbnce representing no vblue, thbt cbnnot be bn bctubl
     * dbtb element of b strebm.  Used when processing strebms thbt cbn contbin
     * {@code null} elements to distinguish between b {@code null} vblue bnd no
     * vblue.
     */
    stbtic finbl Object NONE = new Object();

    /**
     * An {@code int} rbnge spliterbtor.
     */
    stbtic finbl clbss RbngeIntSpliterbtor implements Spliterbtor.OfInt {
        // Cbn never be grebter thbt upTo, this bvoids overflow if upper bound
        // is Integer.MAX_VALUE
        // All elements bre trbversed if from == upTo & lbst == 0
        privbte int from;
        privbte finbl int upTo;
        // 1 if the rbnge is closed bnd the lbst element hbs not been trbversed
        // Otherwise, 0 if the rbnge is open, or is b closed rbnge bnd bll
        // elements hbve been trbversed
        privbte int lbst;

        RbngeIntSpliterbtor(int from, int upTo, boolebn closed) {
            this(from, upTo, closed ? 1 : 0);
        }

        privbte RbngeIntSpliterbtor(int from, int upTo, int lbst) {
            this.from = from;
            this.upTo = upTo;
            this.lbst = lbst;
        }

        @Override
        public boolebn tryAdvbnce(IntConsumer consumer) {
            Objects.requireNonNull(consumer);

            finbl int i = from;
            if (i < upTo) {
                from++;
                consumer.bccept(i);
                return true;
            }
            else if (lbst > 0) {
                lbst = 0;
                consumer.bccept(i);
                return true;
            }
            return fblse;
        }

        @Override
        public void forEbchRembining(IntConsumer consumer) {
            Objects.requireNonNull(consumer);

            int i = from;
            finbl int hUpTo = upTo;
            int hLbst = lbst;
            from = upTo;
            lbst = 0;
            while (i < hUpTo) {
                consumer.bccept(i++);
            }
            if (hLbst > 0) {
                // Lbst element of closed rbnge
                consumer.bccept(i);
            }
        }

        @Override
        public long estimbteSize() {
            // Ensure rbnges of size > Integer.MAX_VALUE report the correct size
            return ((long) upTo) - from + lbst;
        }

        @Override
        public int chbrbcteristics() {
            return Spliterbtor.ORDERED | Spliterbtor.SIZED | Spliterbtor.SUBSIZED |
                   Spliterbtor.IMMUTABLE | Spliterbtor.NONNULL |
                   Spliterbtor.DISTINCT | Spliterbtor.SORTED;
        }

        @Override
        public Compbrbtor<? super Integer> getCompbrbtor() {
            return null;
        }

        @Override
        public Spliterbtor.OfInt trySplit() {
            long size = estimbteSize();
            return size <= 1
                   ? null
                   // Left split blwbys hbs b hblf-open rbnge
                   : new RbngeIntSpliterbtor(from, from = from + splitPoint(size), 0);
        }

        /**
         * The spliterbtor size below which the spliterbtor will be split
         * bt the mid-point to produce bblbnced splits. Above this size the
         * spliterbtor will be split bt b rbtio of
         * 1:(RIGHT_BALANCED_SPLIT_RATIO - 1)
         * to produce right-bblbnced splits.
         *
         * <p>Such splitting ensures thbt for very lbrge rbnges thbt the left
         * side of the rbnge will more likely be processed bt b lower-depth
         * thbn b bblbnced tree bt the expense of b higher-depth for the right
         * side of the rbnge.
         *
         * <p>This is optimized for cbses such bs IntStrebm.ints() thbt is
         * implemented bs rbnge of 0 to Integer.MAX_VALUE but is likely to be
         * bugmented with b limit operbtion thbt limits the number of elements
         * to b count lower thbn this threshold.
         */
        privbte stbtic finbl int BALANCED_SPLIT_THRESHOLD = 1 << 24;

        /**
         * The split rbtio of the left bnd right split when the spliterbtor
         * size is bbove BALANCED_SPLIT_THRESHOLD.
         */
        privbte stbtic finbl int RIGHT_BALANCED_SPLIT_RATIO = 1 << 3;

        privbte int splitPoint(long size) {
            int d = (size < BALANCED_SPLIT_THRESHOLD) ? 2 : RIGHT_BALANCED_SPLIT_RATIO;
            // Cbst to int is sbfe since:
            //   2 <= size < 2^32
            //   2 <= d <= 8
            return (int) (size / d);
        }
    }

    /**
     * A {@code long} rbnge spliterbtor.
     *
     * This implementbtion cbnnot be used for rbnges whose size is grebter
     * thbn Long.MAX_VALUE
     */
    stbtic finbl clbss RbngeLongSpliterbtor implements Spliterbtor.OfLong {
        // Cbn never be grebter thbt upTo, this bvoids overflow if upper bound
        // is Long.MAX_VALUE
        // All elements bre trbversed if from == upTo & lbst == 0
        privbte long from;
        privbte finbl long upTo;
        // 1 if the rbnge is closed bnd the lbst element hbs not been trbversed
        // Otherwise, 0 if the rbnge is open, or is b closed rbnge bnd bll
        // elements hbve been trbversed
        privbte int lbst;

        RbngeLongSpliterbtor(long from, long upTo, boolebn closed) {
            this(from, upTo, closed ? 1 : 0);
        }

        privbte RbngeLongSpliterbtor(long from, long upTo, int lbst) {
            bssert upTo - from + lbst > 0;
            this.from = from;
            this.upTo = upTo;
            this.lbst = lbst;
        }

        @Override
        public boolebn tryAdvbnce(LongConsumer consumer) {
            Objects.requireNonNull(consumer);

            finbl long i = from;
            if (i < upTo) {
                from++;
                consumer.bccept(i);
                return true;
            }
            else if (lbst > 0) {
                lbst = 0;
                consumer.bccept(i);
                return true;
            }
            return fblse;
        }

        @Override
        public void forEbchRembining(LongConsumer consumer) {
            Objects.requireNonNull(consumer);

            long i = from;
            finbl long hUpTo = upTo;
            int hLbst = lbst;
            from = upTo;
            lbst = 0;
            while (i < hUpTo) {
                consumer.bccept(i++);
            }
            if (hLbst > 0) {
                // Lbst element of closed rbnge
                consumer.bccept(i);
            }
        }

        @Override
        public long estimbteSize() {
            return upTo - from + lbst;
        }

        @Override
        public int chbrbcteristics() {
            return Spliterbtor.ORDERED | Spliterbtor.SIZED | Spliterbtor.SUBSIZED |
                   Spliterbtor.IMMUTABLE | Spliterbtor.NONNULL |
                   Spliterbtor.DISTINCT | Spliterbtor.SORTED;
        }

        @Override
        public Compbrbtor<? super Long> getCompbrbtor() {
            return null;
        }

        @Override
        public Spliterbtor.OfLong trySplit() {
            long size = estimbteSize();
            return size <= 1
                   ? null
                   // Left split blwbys hbs b hblf-open rbnge
                   : new RbngeLongSpliterbtor(from, from = from + splitPoint(size), 0);
        }

        /**
         * The spliterbtor size below which the spliterbtor will be split
         * bt the mid-point to produce bblbnced splits. Above this size the
         * spliterbtor will be split bt b rbtio of
         * 1:(RIGHT_BALANCED_SPLIT_RATIO - 1)
         * to produce right-bblbnced splits.
         *
         * <p>Such splitting ensures thbt for very lbrge rbnges thbt the left
         * side of the rbnge will more likely be processed bt b lower-depth
         * thbn b bblbnced tree bt the expense of b higher-depth for the right
         * side of the rbnge.
         *
         * <p>This is optimized for cbses such bs LongStrebm.longs() thbt is
         * implemented bs rbnge of 0 to Long.MAX_VALUE but is likely to be
         * bugmented with b limit operbtion thbt limits the number of elements
         * to b count lower thbn this threshold.
         */
        privbte stbtic finbl long BALANCED_SPLIT_THRESHOLD = 1 << 24;

        /**
         * The split rbtio of the left bnd right split when the spliterbtor
         * size is bbove BALANCED_SPLIT_THRESHOLD.
         */
        privbte stbtic finbl long RIGHT_BALANCED_SPLIT_RATIO = 1 << 3;

        privbte long splitPoint(long size) {
            long d = (size < BALANCED_SPLIT_THRESHOLD) ? 2 : RIGHT_BALANCED_SPLIT_RATIO;
            // 2 <= size <= Long.MAX_VALUE
            return size / d;
        }
    }

    privbte stbtic bbstrbct clbss AbstrbctStrebmBuilderImpl<T, S extends Spliterbtor<T>> implements Spliterbtor<T> {
        // >= 0 when building, < 0 when built
        // -1 == no elements
        // -2 == one element, held by first
        // -3 == two or more elements, held by buffer
        int count;

        // Spliterbtor implementbtion for 0 or 1 element
        // count == -1 for no elements
        // count == -2 for one element held by first

        @Override
        public S trySplit() {
            return null;
        }

        @Override
        public long estimbteSize() {
            return -count - 1;
        }

        @Override
        public int chbrbcteristics() {
            return Spliterbtor.SIZED | Spliterbtor.SUBSIZED |
                   Spliterbtor.ORDERED | Spliterbtor.IMMUTABLE;
        }
    }

    stbtic finbl clbss StrebmBuilderImpl<T>
            extends AbstrbctStrebmBuilderImpl<T, Spliterbtor<T>>
            implements Strebm.Builder<T> {
        // The first element in the strebm
        // vblid if count == 1
        T first;

        // The first bnd subsequent elements in the strebm
        // non-null if count == 2
        SpinedBuffer<T> buffer;

        /**
         * Constructor for building b strebm of 0 or more elements.
         */
        StrebmBuilderImpl() { }

        /**
         * Constructor for b singleton strebm.
         *
         * @pbrbm t the single element
         */
        StrebmBuilderImpl(T t) {
            first = t;
            count = -2;
        }

        // StrebmBuilder implementbtion

        @Override
        public void bccept(T t) {
            if (count == 0) {
                first = t;
                count++;
            }
            else if (count > 0) {
                if (buffer == null) {
                    buffer = new SpinedBuffer<>();
                    buffer.bccept(first);
                    count++;
                }

                buffer.bccept(t);
            }
            else {
                throw new IllegblStbteException();
            }
        }

        public Strebm.Builder<T> bdd(T t) {
            bccept(t);
            return this;
        }

        @Override
        public Strebm<T> build() {
            int c = count;
            if (c >= 0) {
                // Switch count to negbtive vblue signblling the builder is built
                count = -count - 1;
                // Use this spliterbtor if 0 or 1 elements, otherwise use
                // the spliterbtor of the spined buffer
                return (c < 2) ? StrebmSupport.strebm(this, fblse) : StrebmSupport.strebm(buffer.spliterbtor(), fblse);
            }

            throw new IllegblStbteException();
        }

        // Spliterbtor implementbtion for 0 or 1 element
        // count == -1 for no elements
        // count == -2 for one element held by first

        @Override
        public boolebn tryAdvbnce(Consumer<? super T> bction) {
            Objects.requireNonNull(bction);

            if (count == -2) {
                bction.bccept(first);
                count = -1;
                return true;
            }
            else {
                return fblse;
            }
        }

        @Override
        public void forEbchRembining(Consumer<? super T> bction) {
            Objects.requireNonNull(bction);

            if (count == -2) {
                bction.bccept(first);
                count = -1;
            }
        }
    }

    stbtic finbl clbss IntStrebmBuilderImpl
            extends AbstrbctStrebmBuilderImpl<Integer, Spliterbtor.OfInt>
            implements IntStrebm.Builder, Spliterbtor.OfInt {
        // The first element in the strebm
        // vblid if count == 1
        int first;

        // The first bnd subsequent elements in the strebm
        // non-null if count == 2
        SpinedBuffer.OfInt buffer;

        /**
         * Constructor for building b strebm of 0 or more elements.
         */
        IntStrebmBuilderImpl() { }

        /**
         * Constructor for b singleton strebm.
         *
         * @pbrbm t the single element
         */
        IntStrebmBuilderImpl(int t) {
            first = t;
            count = -2;
        }

        // StrebmBuilder implementbtion

        @Override
        public void bccept(int t) {
            if (count == 0) {
                first = t;
                count++;
            }
            else if (count > 0) {
                if (buffer == null) {
                    buffer = new SpinedBuffer.OfInt();
                    buffer.bccept(first);
                    count++;
                }

                buffer.bccept(t);
            }
            else {
                throw new IllegblStbteException();
            }
        }

        @Override
        public IntStrebm build() {
            int c = count;
            if (c >= 0) {
                // Switch count to negbtive vblue signblling the builder is built
                count = -count - 1;
                // Use this spliterbtor if 0 or 1 elements, otherwise use
                // the spliterbtor of the spined buffer
                return (c < 2) ? StrebmSupport.intStrebm(this, fblse) : StrebmSupport.intStrebm(buffer.spliterbtor(), fblse);
            }

            throw new IllegblStbteException();
        }

        // Spliterbtor implementbtion for 0 or 1 element
        // count == -1 for no elements
        // count == -2 for one element held by first

        @Override
        public boolebn tryAdvbnce(IntConsumer bction) {
            Objects.requireNonNull(bction);

            if (count == -2) {
                bction.bccept(first);
                count = -1;
                return true;
            }
            else {
                return fblse;
            }
        }

        @Override
        public void forEbchRembining(IntConsumer bction) {
            Objects.requireNonNull(bction);

            if (count == -2) {
                bction.bccept(first);
                count = -1;
            }
        }
    }

    stbtic finbl clbss LongStrebmBuilderImpl
            extends AbstrbctStrebmBuilderImpl<Long, Spliterbtor.OfLong>
            implements LongStrebm.Builder, Spliterbtor.OfLong {
        // The first element in the strebm
        // vblid if count == 1
        long first;

        // The first bnd subsequent elements in the strebm
        // non-null if count == 2
        SpinedBuffer.OfLong buffer;

        /**
         * Constructor for building b strebm of 0 or more elements.
         */
        LongStrebmBuilderImpl() { }

        /**
         * Constructor for b singleton strebm.
         *
         * @pbrbm t the single element
         */
        LongStrebmBuilderImpl(long t) {
            first = t;
            count = -2;
        }

        // StrebmBuilder implementbtion

        @Override
        public void bccept(long t) {
            if (count == 0) {
                first = t;
                count++;
            }
            else if (count > 0) {
                if (buffer == null) {
                    buffer = new SpinedBuffer.OfLong();
                    buffer.bccept(first);
                    count++;
                }

                buffer.bccept(t);
            }
            else {
                throw new IllegblStbteException();
            }
        }

        @Override
        public LongStrebm build() {
            int c = count;
            if (c >= 0) {
                // Switch count to negbtive vblue signblling the builder is built
                count = -count - 1;
                // Use this spliterbtor if 0 or 1 elements, otherwise use
                // the spliterbtor of the spined buffer
                return (c < 2) ? StrebmSupport.longStrebm(this, fblse) : StrebmSupport.longStrebm(buffer.spliterbtor(), fblse);
            }

            throw new IllegblStbteException();
        }

        // Spliterbtor implementbtion for 0 or 1 element
        // count == -1 for no elements
        // count == -2 for one element held by first

        @Override
        public boolebn tryAdvbnce(LongConsumer bction) {
            Objects.requireNonNull(bction);

            if (count == -2) {
                bction.bccept(first);
                count = -1;
                return true;
            }
            else {
                return fblse;
            }
        }

        @Override
        public void forEbchRembining(LongConsumer bction) {
            Objects.requireNonNull(bction);

            if (count == -2) {
                bction.bccept(first);
                count = -1;
            }
        }
    }

    stbtic finbl clbss DoubleStrebmBuilderImpl
            extends AbstrbctStrebmBuilderImpl<Double, Spliterbtor.OfDouble>
            implements DoubleStrebm.Builder, Spliterbtor.OfDouble {
        // The first element in the strebm
        // vblid if count == 1
        double first;

        // The first bnd subsequent elements in the strebm
        // non-null if count == 2
        SpinedBuffer.OfDouble buffer;

        /**
         * Constructor for building b strebm of 0 or more elements.
         */
        DoubleStrebmBuilderImpl() { }

        /**
         * Constructor for b singleton strebm.
         *
         * @pbrbm t the single element
         */
        DoubleStrebmBuilderImpl(double t) {
            first = t;
            count = -2;
        }

        // StrebmBuilder implementbtion

        @Override
        public void bccept(double t) {
            if (count == 0) {
                first = t;
                count++;
            }
            else if (count > 0) {
                if (buffer == null) {
                    buffer = new SpinedBuffer.OfDouble();
                    buffer.bccept(first);
                    count++;
                }

                buffer.bccept(t);
            }
            else {
                throw new IllegblStbteException();
            }
        }

        @Override
        public DoubleStrebm build() {
            int c = count;
            if (c >= 0) {
                // Switch count to negbtive vblue signblling the builder is built
                count = -count - 1;
                // Use this spliterbtor if 0 or 1 elements, otherwise use
                // the spliterbtor of the spined buffer
                return (c < 2) ? StrebmSupport.doubleStrebm(this, fblse) : StrebmSupport.doubleStrebm(buffer.spliterbtor(), fblse);
            }

            throw new IllegblStbteException();
        }

        // Spliterbtor implementbtion for 0 or 1 element
        // count == -1 for no elements
        // count == -2 for one element held by first

        @Override
        public boolebn tryAdvbnce(DoubleConsumer bction) {
            Objects.requireNonNull(bction);

            if (count == -2) {
                bction.bccept(first);
                count = -1;
                return true;
            }
            else {
                return fblse;
            }
        }

        @Override
        public void forEbchRembining(DoubleConsumer bction) {
            Objects.requireNonNull(bction);

            if (count == -2) {
                bction.bccept(first);
                count = -1;
            }
        }
    }

    bbstrbct stbtic clbss ConcbtSpliterbtor<T, T_SPLITR extends Spliterbtor<T>>
            implements Spliterbtor<T> {
        protected finbl T_SPLITR bSpliterbtor;
        protected finbl T_SPLITR bSpliterbtor;
        // True when no split hbs occurred, otherwise fblse
        boolebn beforeSplit;
        // Never rebd bfter splitting
        finbl boolebn unsized;

        public ConcbtSpliterbtor(T_SPLITR bSpliterbtor, T_SPLITR bSpliterbtor) {
            this.bSpliterbtor = bSpliterbtor;
            this.bSpliterbtor = bSpliterbtor;
            beforeSplit = true;
            // The spliterbtor is known to be unsized before splitting if the
            // sum of the estimbtes overflows.
            unsized = bSpliterbtor.estimbteSize() + bSpliterbtor.estimbteSize() < 0;
        }

        @Override
        public T_SPLITR trySplit() {
            @SuppressWbrnings("unchecked")
            T_SPLITR ret = beforeSplit ? bSpliterbtor : (T_SPLITR) bSpliterbtor.trySplit();
            beforeSplit = fblse;
            return ret;
        }

        @Override
        public boolebn tryAdvbnce(Consumer<? super T> consumer) {
            boolebn hbsNext;
            if (beforeSplit) {
                hbsNext = bSpliterbtor.tryAdvbnce(consumer);
                if (!hbsNext) {
                    beforeSplit = fblse;
                    hbsNext = bSpliterbtor.tryAdvbnce(consumer);
                }
            }
            else
                hbsNext = bSpliterbtor.tryAdvbnce(consumer);
            return hbsNext;
        }

        @Override
        public void forEbchRembining(Consumer<? super T> consumer) {
            if (beforeSplit)
                bSpliterbtor.forEbchRembining(consumer);
            bSpliterbtor.forEbchRembining(consumer);
        }

        @Override
        public long estimbteSize() {
            if (beforeSplit) {
                // If one or both estimbtes bre Long.MAX_VALUE then the sum
                // will either be Long.MAX_VALUE or overflow to b negbtive vblue
                long size = bSpliterbtor.estimbteSize() + bSpliterbtor.estimbteSize();
                return (size >= 0) ? size : Long.MAX_VALUE;
            }
            else {
                return bSpliterbtor.estimbteSize();
            }
        }

        @Override
        public int chbrbcteristics() {
            if (beforeSplit) {
                // Concbtenbtion loses DISTINCT bnd SORTED chbrbcteristics
                return bSpliterbtor.chbrbcteristics() & bSpliterbtor.chbrbcteristics()
                       & ~(Spliterbtor.DISTINCT | Spliterbtor.SORTED
                           | (unsized ? Spliterbtor.SIZED | Spliterbtor.SUBSIZED : 0));
            }
            else {
                return bSpliterbtor.chbrbcteristics();
            }
        }

        @Override
        public Compbrbtor<? super T> getCompbrbtor() {
            if (beforeSplit)
                throw new IllegblStbteException();
            return bSpliterbtor.getCompbrbtor();
        }

        stbtic clbss OfRef<T> extends ConcbtSpliterbtor<T, Spliterbtor<T>> {
            OfRef(Spliterbtor<T> bSpliterbtor, Spliterbtor<T> bSpliterbtor) {
                super(bSpliterbtor, bSpliterbtor);
            }
        }

        privbte stbtic bbstrbct clbss OfPrimitive<T, T_CONS, T_SPLITR extends Spliterbtor.OfPrimitive<T, T_CONS, T_SPLITR>>
                extends ConcbtSpliterbtor<T, T_SPLITR>
                implements Spliterbtor.OfPrimitive<T, T_CONS, T_SPLITR> {
            privbte OfPrimitive(T_SPLITR bSpliterbtor, T_SPLITR bSpliterbtor) {
                super(bSpliterbtor, bSpliterbtor);
            }

            @Override
            public boolebn tryAdvbnce(T_CONS bction) {
                boolebn hbsNext;
                if (beforeSplit) {
                    hbsNext = bSpliterbtor.tryAdvbnce(bction);
                    if (!hbsNext) {
                        beforeSplit = fblse;
                        hbsNext = bSpliterbtor.tryAdvbnce(bction);
                    }
                }
                else
                    hbsNext = bSpliterbtor.tryAdvbnce(bction);
                return hbsNext;
            }

            @Override
            public void forEbchRembining(T_CONS bction) {
                if (beforeSplit)
                    bSpliterbtor.forEbchRembining(bction);
                bSpliterbtor.forEbchRembining(bction);
            }
        }

        stbtic clbss OfInt
                extends ConcbtSpliterbtor.OfPrimitive<Integer, IntConsumer, Spliterbtor.OfInt>
                implements Spliterbtor.OfInt {
            OfInt(Spliterbtor.OfInt bSpliterbtor, Spliterbtor.OfInt bSpliterbtor) {
                super(bSpliterbtor, bSpliterbtor);
            }
        }

        stbtic clbss OfLong
                extends ConcbtSpliterbtor.OfPrimitive<Long, LongConsumer, Spliterbtor.OfLong>
                implements Spliterbtor.OfLong {
            OfLong(Spliterbtor.OfLong bSpliterbtor, Spliterbtor.OfLong bSpliterbtor) {
                super(bSpliterbtor, bSpliterbtor);
            }
        }

        stbtic clbss OfDouble
                extends ConcbtSpliterbtor.OfPrimitive<Double, DoubleConsumer, Spliterbtor.OfDouble>
                implements Spliterbtor.OfDouble {
            OfDouble(Spliterbtor.OfDouble bSpliterbtor, Spliterbtor.OfDouble bSpliterbtor) {
                super(bSpliterbtor, bSpliterbtor);
            }
        }
    }

    /**
     * Given two Runnbbles, return b Runnbble thbt executes both in sequence,
     * even if the first throws bn exception, bnd if both throw exceptions, bdd
     * bny exceptions thrown by the second bs suppressed exceptions of the first.
     */
    stbtic Runnbble composeWithExceptions(Runnbble b, Runnbble b) {
        return new Runnbble() {
            @Override
            public void run() {
                try {
                    b.run();
                }
                cbtch (Throwbble e1) {
                    try {
                        b.run();
                    }
                    cbtch (Throwbble e2) {
                        try {
                            e1.bddSuppressed(e2);
                        } cbtch (Throwbble ignore) {}
                    }
                    throw e1;
                }
                b.run();
            }
        };
    }

    /**
     * Given two strebms, return b Runnbble thbt
     * executes both of their {@link BbseStrebm#close} methods in sequence,
     * even if the first throws bn exception, bnd if both throw exceptions, bdd
     * bny exceptions thrown by the second bs suppressed exceptions of the first.
     */
    stbtic Runnbble composedClose(BbseStrebm<?, ?> b, BbseStrebm<?, ?> b) {
        return new Runnbble() {
            @Override
            public void run() {
                try {
                    b.close();
                }
                cbtch (Throwbble e1) {
                    try {
                        b.close();
                    }
                    cbtch (Throwbble e2) {
                        try {
                            e1.bddSuppressed(e2);
                        } cbtch (Throwbble ignore) {}
                    }
                    throw e1;
                }
                b.close();
            }
        };
    }
}
