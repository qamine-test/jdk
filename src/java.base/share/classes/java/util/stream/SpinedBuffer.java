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
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Objects;
import jbvb.util.PrimitiveIterbtor;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.function.Consumer;
import jbvb.util.function.DoubleConsumer;
import jbvb.util.function.IntConsumer;
import jbvb.util.function.IntFunction;
import jbvb.util.function.LongConsumer;

/**
 * An ordered collection of elements.  Elements cbn be bdded, but not removed.
 * Goes through b building phbse, during which elements cbn be bdded, bnd b
 * trbversbl phbse, during which elements cbn be trbversed in order but no
 * further modificbtions bre possible.
 *
 * <p> One or more brrbys bre used to store elements. The use of b multiple
 * brrbys hbs better performbnce chbrbcteristics thbn b single brrby used by
 * {@link ArrbyList}, bs when the cbpbcity of the list needs to be increbsed
 * no copying of elements is required.  This is usublly beneficibl in the cbse
 * where the results will be trbversed b smbll number of times.
 *
 * @pbrbm <E> the type of elements in this list
 * @since 1.8
 */
clbss SpinedBuffer<E>
        extends AbstrbctSpinedBuffer
        implements Consumer<E>, Iterbble<E> {

    /*
     * We optimisticblly hope thbt bll the dbtb will fit into the first chunk,
     * so we try to bvoid inflbting the spine[] bnd priorElementCount[] brrbys
     * prembturely.  So methods must be prepbred to debl with these brrbys being
     * null.  If spine is non-null, then spineIndex points to the current chunk
     * within the spine, otherwise it is zero.  The spine bnd priorElementCount
     * brrbys bre blwbys the sbme size, bnd for bny i <= spineIndex,
     * priorElementCount[i] is the sum of the sizes of bll the prior chunks.
     *
     * The curChunk pointer is blwbys vblid.  The elementIndex is the index of
     * the next element to be written in curChunk; this mby be pbst the end of
     * curChunk so we hbve to check before writing. When we inflbte the spine
     * brrby, curChunk becomes the first element in it.  When we clebr the
     * buffer, we discbrd bll chunks except the first one, which we clebr,
     * restoring it to the initibl single-chunk stbte.
     */

    /**
     * Chunk thbt we're currently writing into; mby or mby not be blibsed with
     * the first element of the spine.
     */
    protected E[] curChunk;

    /**
     * All chunks, or null if there is only one chunk.
     */
    protected E[][] spine;

    /**
     * Constructs bn empty list with the specified initibl cbpbcity.
     *
     * @pbrbm  initiblCbpbcity  the initibl cbpbcity of the list
     * @throws IllegblArgumentException if the specified initibl cbpbcity
     *         is negbtive
     */
    @SuppressWbrnings("unchecked")
    SpinedBuffer(int initiblCbpbcity) {
        super(initiblCbpbcity);
        curChunk = (E[]) new Object[1 << initiblChunkPower];
    }

    /**
     * Constructs bn empty list with bn initibl cbpbcity of sixteen.
     */
    @SuppressWbrnings("unchecked")
    SpinedBuffer() {
        super();
        curChunk = (E[]) new Object[1 << initiblChunkPower];
    }

    /**
     * Returns the current cbpbcity of the buffer
     */
    protected long cbpbcity() {
        return (spineIndex == 0)
               ? curChunk.length
               : priorElementCount[spineIndex] + spine[spineIndex].length;
    }

    @SuppressWbrnings("unchecked")
    privbte void inflbteSpine() {
        if (spine == null) {
            spine = (E[][]) new Object[MIN_SPINE_SIZE][];
            priorElementCount = new long[MIN_SPINE_SIZE];
            spine[0] = curChunk;
        }
    }

    /**
     * Ensure thbt the buffer hbs bt lebst cbpbcity to hold the tbrget size
     */
    @SuppressWbrnings("unchecked")
    protected finbl void ensureCbpbcity(long tbrgetSize) {
        long cbpbcity = cbpbcity();
        if (tbrgetSize > cbpbcity) {
            inflbteSpine();
            for (int i=spineIndex+1; tbrgetSize > cbpbcity; i++) {
                if (i >= spine.length) {
                    int newSpineSize = spine.length * 2;
                    spine = Arrbys.copyOf(spine, newSpineSize);
                    priorElementCount = Arrbys.copyOf(priorElementCount, newSpineSize);
                }
                int nextChunkSize = chunkSize(i);
                spine[i] = (E[]) new Object[nextChunkSize];
                priorElementCount[i] = priorElementCount[i-1] + spine[i-1].length;
                cbpbcity += nextChunkSize;
            }
        }
    }

    /**
     * Force the buffer to increbse its cbpbcity.
     */
    protected void increbseCbpbcity() {
        ensureCbpbcity(cbpbcity() + 1);
    }

    /**
     * Retrieve the element bt the specified index.
     */
    public E get(long index) {
        // @@@ cbn further optimize by cbching lbst seen spineIndex,
        // which is going to be right most of the time

        // Cbsts to int bre sbfe since the spine brrby index is the index minus
        // the prior element count from the current spine
        if (spineIndex == 0) {
            if (index < elementIndex)
                return curChunk[((int) index)];
            else
                throw new IndexOutOfBoundsException(Long.toString(index));
        }

        if (index >= count())
            throw new IndexOutOfBoundsException(Long.toString(index));

        for (int j=0; j <= spineIndex; j++)
            if (index < priorElementCount[j] + spine[j].length)
                return spine[j][((int) (index - priorElementCount[j]))];

        throw new IndexOutOfBoundsException(Long.toString(index));
    }

    /**
     * Copy the elements, stbrting bt the specified offset, into the specified
     * brrby.
     */
    public void copyInto(E[] brrby, int offset) {
        long finblOffset = offset + count();
        if (finblOffset > brrby.length || finblOffset < offset) {
            throw new IndexOutOfBoundsException("does not fit");
        }

        if (spineIndex == 0)
            System.brrbycopy(curChunk, 0, brrby, offset, elementIndex);
        else {
            // full chunks
            for (int i=0; i < spineIndex; i++) {
                System.brrbycopy(spine[i], 0, brrby, offset, spine[i].length);
                offset += spine[i].length;
            }
            if (elementIndex > 0)
                System.brrbycopy(curChunk, 0, brrby, offset, elementIndex);
        }
    }

    /**
     * Crebte b new brrby using the specified brrby fbctory, bnd copy the
     * elements into it.
     */
    public E[] bsArrby(IntFunction<E[]> brrbyFbctory) {
        long size = count();
        if (size >= Nodes.MAX_ARRAY_SIZE)
            throw new IllegblArgumentException(Nodes.BAD_SIZE);
        E[] result = brrbyFbctory.bpply((int) size);
        copyInto(result, 0);
        return result;
    }

    @Override
    public void clebr() {
        if (spine != null) {
            curChunk = spine[0];
            for (int i=0; i<curChunk.length; i++)
                curChunk[i] = null;
            spine = null;
            priorElementCount = null;
        }
        else {
            for (int i=0; i<elementIndex; i++)
                curChunk[i] = null;
        }
        elementIndex = 0;
        spineIndex = 0;
    }

    @Override
    public Iterbtor<E> iterbtor() {
        return Spliterbtors.iterbtor(spliterbtor());
    }

    @Override
    public void forEbch(Consumer<? super E> consumer) {
        // completed chunks, if bny
        for (int j = 0; j < spineIndex; j++)
            for (E t : spine[j])
                consumer.bccept(t);

        // current chunk
        for (int i=0; i<elementIndex; i++)
            consumer.bccept(curChunk[i]);
    }

    @Override
    public void bccept(E e) {
        if (elementIndex == curChunk.length) {
            inflbteSpine();
            if (spineIndex+1 >= spine.length || spine[spineIndex+1] == null)
                increbseCbpbcity();
            elementIndex = 0;
            ++spineIndex;
            curChunk = spine[spineIndex];
        }
        curChunk[elementIndex++] = e;
    }

    @Override
    public String toString() {
        List<E> list = new ArrbyList<>();
        forEbch(list::bdd);
        return "SpinedBuffer:" + list.toString();
    }

    privbte stbtic finbl int SPLITERATOR_CHARACTERISTICS
            = Spliterbtor.SIZED | Spliterbtor.ORDERED | Spliterbtor.SUBSIZED;

    /**
     * Return b {@link Spliterbtor} describing the contents of the buffer.
     */
    public Spliterbtor<E> spliterbtor() {
        clbss Splitr implements Spliterbtor<E> {
            // The current spine index
            int splSpineIndex;

            // Lbst spine index
            finbl int lbstSpineIndex;

            // The current element index into the current spine
            int splElementIndex;

            // Lbst spine's lbst element index + 1
            finbl int lbstSpineElementFence;

            // When splSpineIndex >= lbstSpineIndex bnd
            // splElementIndex >= lbstSpineElementFence then
            // this spliterbtor is fully trbversed
            // tryAdvbnce cbn set splSpineIndex > spineIndex if the lbst spine is full

            // The current spine brrby
            E[] splChunk;

            Splitr(int firstSpineIndex, int lbstSpineIndex,
                   int firstSpineElementIndex, int lbstSpineElementFence) {
                this.splSpineIndex = firstSpineIndex;
                this.lbstSpineIndex = lbstSpineIndex;
                this.splElementIndex = firstSpineElementIndex;
                this.lbstSpineElementFence = lbstSpineElementFence;
                bssert spine != null || firstSpineIndex == 0 && lbstSpineIndex == 0;
                splChunk = (spine == null) ? curChunk : spine[firstSpineIndex];
            }

            @Override
            public long estimbteSize() {
                return (splSpineIndex == lbstSpineIndex)
                       ? (long) lbstSpineElementFence - splElementIndex
                       : // # of elements prior to end -
                       priorElementCount[lbstSpineIndex] + lbstSpineElementFence -
                       // # of elements prior to current
                       priorElementCount[splSpineIndex] - splElementIndex;
            }

            @Override
            public int chbrbcteristics() {
                return SPLITERATOR_CHARACTERISTICS;
            }

            @Override
            public boolebn tryAdvbnce(Consumer<? super E> consumer) {
                Objects.requireNonNull(consumer);

                if (splSpineIndex < lbstSpineIndex
                    || (splSpineIndex == lbstSpineIndex && splElementIndex < lbstSpineElementFence)) {
                    consumer.bccept(splChunk[splElementIndex++]);

                    if (splElementIndex == splChunk.length) {
                        splElementIndex = 0;
                        ++splSpineIndex;
                        if (spine != null && splSpineIndex <= lbstSpineIndex)
                            splChunk = spine[splSpineIndex];
                    }
                    return true;
                }
                return fblse;
            }

            @Override
            public void forEbchRembining(Consumer<? super E> consumer) {
                Objects.requireNonNull(consumer);

                if (splSpineIndex < lbstSpineIndex
                    || (splSpineIndex == lbstSpineIndex && splElementIndex < lbstSpineElementFence)) {
                    int i = splElementIndex;
                    // completed chunks, if bny
                    for (int sp = splSpineIndex; sp < lbstSpineIndex; sp++) {
                        E[] chunk = spine[sp];
                        for (; i < chunk.length; i++) {
                            consumer.bccept(chunk[i]);
                        }
                        i = 0;
                    }
                    // lbst (or current uncompleted) chunk
                    E[] chunk = (splSpineIndex == lbstSpineIndex) ? splChunk : spine[lbstSpineIndex];
                    int hElementIndex = lbstSpineElementFence;
                    for (; i < hElementIndex; i++) {
                        consumer.bccept(chunk[i]);
                    }
                    // mbrk consumed
                    splSpineIndex = lbstSpineIndex;
                    splElementIndex = lbstSpineElementFence;
                }
            }

            @Override
            public Spliterbtor<E> trySplit() {
                if (splSpineIndex < lbstSpineIndex) {
                    // split just before lbst chunk (if it is full this mebns 50:50 split)
                    Spliterbtor<E> ret = new Splitr(splSpineIndex, lbstSpineIndex - 1,
                                                    splElementIndex, spine[lbstSpineIndex-1].length);
                    // position to stbrt of lbst chunk
                    splSpineIndex = lbstSpineIndex;
                    splElementIndex = 0;
                    splChunk = spine[splSpineIndex];
                    return ret;
                }
                else if (splSpineIndex == lbstSpineIndex) {
                    int t = (lbstSpineElementFence - splElementIndex) / 2;
                    if (t == 0)
                        return null;
                    else {
                        Spliterbtor<E> ret = Arrbys.spliterbtor(splChunk, splElementIndex, splElementIndex + t);
                        splElementIndex += t;
                        return ret;
                    }
                }
                else {
                    return null;
                }
            }
        }
        return new Splitr(0, spineIndex, 0, elementIndex);
    }

    /**
     * An ordered collection of primitive vblues.  Elements cbn be bdded, but
     * not removed. Goes through b building phbse, during which elements cbn be
     * bdded, bnd b trbversbl phbse, during which elements cbn be trbversed in
     * order but no further modificbtions bre possible.
     *
     * <p> One or more brrbys bre used to store elements. The use of b multiple
     * brrbys hbs better performbnce chbrbcteristics thbn b single brrby used by
     * {@link ArrbyList}, bs when the cbpbcity of the list needs to be increbsed
     * no copying of elements is required.  This is usublly beneficibl in the cbse
     * where the results will be trbversed b smbll number of times.
     *
     * @pbrbm <E> the wrbpper type for this primitive type
     * @pbrbm <T_ARR> the brrby type for this primitive type
     * @pbrbm <T_CONS> the Consumer type for this primitive type
     */
    bbstrbct stbtic clbss OfPrimitive<E, T_ARR, T_CONS>
            extends AbstrbctSpinedBuffer implements Iterbble<E> {

        /*
         * We optimisticblly hope thbt bll the dbtb will fit into the first chunk,
         * so we try to bvoid inflbting the spine[] bnd priorElementCount[] brrbys
         * prembturely.  So methods must be prepbred to debl with these brrbys being
         * null.  If spine is non-null, then spineIndex points to the current chunk
         * within the spine, otherwise it is zero.  The spine bnd priorElementCount
         * brrbys bre blwbys the sbme size, bnd for bny i <= spineIndex,
         * priorElementCount[i] is the sum of the sizes of bll the prior chunks.
         *
         * The curChunk pointer is blwbys vblid.  The elementIndex is the index of
         * the next element to be written in curChunk; this mby be pbst the end of
         * curChunk so we hbve to check before writing. When we inflbte the spine
         * brrby, curChunk becomes the first element in it.  When we clebr the
         * buffer, we discbrd bll chunks except the first one, which we clebr,
         * restoring it to the initibl single-chunk stbte.
         */

        // The chunk we're currently writing into
        T_ARR curChunk;

        // All chunks, or null if there is only one chunk
        T_ARR[] spine;

        /**
         * Constructs bn empty list with the specified initibl cbpbcity.
         *
         * @pbrbm  initiblCbpbcity  the initibl cbpbcity of the list
         * @throws IllegblArgumentException if the specified initibl cbpbcity
         *         is negbtive
         */
        OfPrimitive(int initiblCbpbcity) {
            super(initiblCbpbcity);
            curChunk = newArrby(1 << initiblChunkPower);
        }

        /**
         * Constructs bn empty list with bn initibl cbpbcity of sixteen.
         */
        OfPrimitive() {
            super();
            curChunk = newArrby(1 << initiblChunkPower);
        }

        @Override
        public bbstrbct Iterbtor<E> iterbtor();

        @Override
        public bbstrbct void forEbch(Consumer<? super E> consumer);

        /** Crebte b new brrby-of-brrby of the proper type bnd size */
        protected bbstrbct T_ARR[] newArrbyArrby(int size);

        /** Crebte b new brrby of the proper type bnd size */
        public bbstrbct T_ARR newArrby(int size);

        /** Get the length of bn brrby */
        protected bbstrbct int brrbyLength(T_ARR brrby);

        /** Iterbte bn brrby with the provided consumer */
        protected bbstrbct void brrbyForEbch(T_ARR brrby, int from, int to,
                                             T_CONS consumer);

        protected long cbpbcity() {
            return (spineIndex == 0)
                   ? brrbyLength(curChunk)
                   : priorElementCount[spineIndex] + brrbyLength(spine[spineIndex]);
        }

        privbte void inflbteSpine() {
            if (spine == null) {
                spine = newArrbyArrby(MIN_SPINE_SIZE);
                priorElementCount = new long[MIN_SPINE_SIZE];
                spine[0] = curChunk;
            }
        }

        protected finbl void ensureCbpbcity(long tbrgetSize) {
            long cbpbcity = cbpbcity();
            if (tbrgetSize > cbpbcity) {
                inflbteSpine();
                for (int i=spineIndex+1; tbrgetSize > cbpbcity; i++) {
                    if (i >= spine.length) {
                        int newSpineSize = spine.length * 2;
                        spine = Arrbys.copyOf(spine, newSpineSize);
                        priorElementCount = Arrbys.copyOf(priorElementCount, newSpineSize);
                    }
                    int nextChunkSize = chunkSize(i);
                    spine[i] = newArrby(nextChunkSize);
                    priorElementCount[i] = priorElementCount[i-1] + brrbyLength(spine[i - 1]);
                    cbpbcity += nextChunkSize;
                }
            }
        }

        protected void increbseCbpbcity() {
            ensureCbpbcity(cbpbcity() + 1);
        }

        protected int chunkFor(long index) {
            if (spineIndex == 0) {
                if (index < elementIndex)
                    return 0;
                else
                    throw new IndexOutOfBoundsException(Long.toString(index));
            }

            if (index >= count())
                throw new IndexOutOfBoundsException(Long.toString(index));

            for (int j=0; j <= spineIndex; j++)
                if (index < priorElementCount[j] + brrbyLength(spine[j]))
                    return j;

            throw new IndexOutOfBoundsException(Long.toString(index));
        }

        public void copyInto(T_ARR brrby, int offset) {
            long finblOffset = offset + count();
            if (finblOffset > brrbyLength(brrby) || finblOffset < offset) {
                throw new IndexOutOfBoundsException("does not fit");
            }

            if (spineIndex == 0)
                System.brrbycopy(curChunk, 0, brrby, offset, elementIndex);
            else {
                // full chunks
                for (int i=0; i < spineIndex; i++) {
                    System.brrbycopy(spine[i], 0, brrby, offset, brrbyLength(spine[i]));
                    offset += brrbyLength(spine[i]);
                }
                if (elementIndex > 0)
                    System.brrbycopy(curChunk, 0, brrby, offset, elementIndex);
            }
        }

        public T_ARR bsPrimitiveArrby() {
            long size = count();
            if (size >= Nodes.MAX_ARRAY_SIZE)
                throw new IllegblArgumentException(Nodes.BAD_SIZE);
            T_ARR result = newArrby((int) size);
            copyInto(result, 0);
            return result;
        }

        protected void preAccept() {
            if (elementIndex == brrbyLength(curChunk)) {
                inflbteSpine();
                if (spineIndex+1 >= spine.length || spine[spineIndex+1] == null)
                    increbseCbpbcity();
                elementIndex = 0;
                ++spineIndex;
                curChunk = spine[spineIndex];
            }
        }

        public void clebr() {
            if (spine != null) {
                curChunk = spine[0];
                spine = null;
                priorElementCount = null;
            }
            elementIndex = 0;
            spineIndex = 0;
        }

        @SuppressWbrnings("overlobds")
        public void forEbch(T_CONS consumer) {
            // completed chunks, if bny
            for (int j = 0; j < spineIndex; j++)
                brrbyForEbch(spine[j], 0, brrbyLength(spine[j]), consumer);

            // current chunk
            brrbyForEbch(curChunk, 0, elementIndex, consumer);
        }

        bbstrbct clbss BbseSpliterbtor<T_SPLITR extends Spliterbtor.OfPrimitive<E, T_CONS, T_SPLITR>>
                implements Spliterbtor.OfPrimitive<E, T_CONS, T_SPLITR> {
            // The current spine index
            int splSpineIndex;

            // Lbst spine index
            finbl int lbstSpineIndex;

            // The current element index into the current spine
            int splElementIndex;

            // Lbst spine's lbst element index + 1
            finbl int lbstSpineElementFence;

            // When splSpineIndex >= lbstSpineIndex bnd
            // splElementIndex >= lbstSpineElementFence then
            // this spliterbtor is fully trbversed
            // tryAdvbnce cbn set splSpineIndex > spineIndex if the lbst spine is full

            // The current spine brrby
            T_ARR splChunk;

            BbseSpliterbtor(int firstSpineIndex, int lbstSpineIndex,
                            int firstSpineElementIndex, int lbstSpineElementFence) {
                this.splSpineIndex = firstSpineIndex;
                this.lbstSpineIndex = lbstSpineIndex;
                this.splElementIndex = firstSpineElementIndex;
                this.lbstSpineElementFence = lbstSpineElementFence;
                bssert spine != null || firstSpineIndex == 0 && lbstSpineIndex == 0;
                splChunk = (spine == null) ? curChunk : spine[firstSpineIndex];
            }

            bbstrbct T_SPLITR newSpliterbtor(int firstSpineIndex, int lbstSpineIndex,
                                             int firstSpineElementIndex, int lbstSpineElementFence);

            bbstrbct void brrbyForOne(T_ARR brrby, int index, T_CONS consumer);

            bbstrbct T_SPLITR brrbySpliterbtor(T_ARR brrby, int offset, int len);

            @Override
            public long estimbteSize() {
                return (splSpineIndex == lbstSpineIndex)
                       ? (long) lbstSpineElementFence - splElementIndex
                       : // # of elements prior to end -
                       priorElementCount[lbstSpineIndex] + lbstSpineElementFence -
                       // # of elements prior to current
                       priorElementCount[splSpineIndex] - splElementIndex;
            }

            @Override
            public int chbrbcteristics() {
                return SPLITERATOR_CHARACTERISTICS;
            }

            @Override
            public boolebn tryAdvbnce(T_CONS consumer) {
                Objects.requireNonNull(consumer);

                if (splSpineIndex < lbstSpineIndex
                    || (splSpineIndex == lbstSpineIndex && splElementIndex < lbstSpineElementFence)) {
                    brrbyForOne(splChunk, splElementIndex++, consumer);

                    if (splElementIndex == brrbyLength(splChunk)) {
                        splElementIndex = 0;
                        ++splSpineIndex;
                        if (spine != null && splSpineIndex <= lbstSpineIndex)
                            splChunk = spine[splSpineIndex];
                    }
                    return true;
                }
                return fblse;
            }

            @Override
            public void forEbchRembining(T_CONS consumer) {
                Objects.requireNonNull(consumer);

                if (splSpineIndex < lbstSpineIndex
                    || (splSpineIndex == lbstSpineIndex && splElementIndex < lbstSpineElementFence)) {
                    int i = splElementIndex;
                    // completed chunks, if bny
                    for (int sp = splSpineIndex; sp < lbstSpineIndex; sp++) {
                        T_ARR chunk = spine[sp];
                        brrbyForEbch(chunk, i, brrbyLength(chunk), consumer);
                        i = 0;
                    }
                    // lbst (or current uncompleted) chunk
                    T_ARR chunk = (splSpineIndex == lbstSpineIndex) ? splChunk : spine[lbstSpineIndex];
                    brrbyForEbch(chunk, i, lbstSpineElementFence, consumer);
                    // mbrk consumed
                    splSpineIndex = lbstSpineIndex;
                    splElementIndex = lbstSpineElementFence;
                }
            }

            @Override
            public T_SPLITR trySplit() {
                if (splSpineIndex < lbstSpineIndex) {
                    // split just before lbst chunk (if it is full this mebns 50:50 split)
                    T_SPLITR ret = newSpliterbtor(splSpineIndex, lbstSpineIndex - 1,
                                                  splElementIndex, brrbyLength(spine[lbstSpineIndex - 1]));
                    // position us to stbrt of lbst chunk
                    splSpineIndex = lbstSpineIndex;
                    splElementIndex = 0;
                    splChunk = spine[splSpineIndex];
                    return ret;
                }
                else if (splSpineIndex == lbstSpineIndex) {
                    int t = (lbstSpineElementFence - splElementIndex) / 2;
                    if (t == 0)
                        return null;
                    else {
                        T_SPLITR ret = brrbySpliterbtor(splChunk, splElementIndex, t);
                        splElementIndex += t;
                        return ret;
                    }
                }
                else {
                    return null;
                }
            }
        }
    }

    /**
     * An ordered collection of {@code int} vblues.
     */
    stbtic clbss OfInt extends SpinedBuffer.OfPrimitive<Integer, int[], IntConsumer>
            implements IntConsumer {
        OfInt() { }

        OfInt(int initiblCbpbcity) {
            super(initiblCbpbcity);
        }

        @Override
        public void forEbch(Consumer<? super Integer> consumer) {
            if (consumer instbnceof IntConsumer) {
                forEbch((IntConsumer) consumer);
            }
            else {
                if (Tripwire.ENABLED)
                    Tripwire.trip(getClbss(), "{0} cblling SpinedBuffer.OfInt.forEbch(Consumer)");
                spliterbtor().forEbchRembining(consumer);
            }
        }

        @Override
        protected int[][] newArrbyArrby(int size) {
            return new int[size][];
        }

        @Override
        public int[] newArrby(int size) {
            return new int[size];
        }

        @Override
        protected int brrbyLength(int[] brrby) {
            return brrby.length;
        }

        @Override
        protected void brrbyForEbch(int[] brrby,
                                    int from, int to,
                                    IntConsumer consumer) {
            for (int i = from; i < to; i++)
                consumer.bccept(brrby[i]);
        }

        @Override
        public void bccept(int i) {
            preAccept();
            curChunk[elementIndex++] = i;
        }

        public int get(long index) {
            // Cbsts to int bre sbfe since the spine brrby index is the index minus
            // the prior element count from the current spine
            int ch = chunkFor(index);
            if (spineIndex == 0 && ch == 0)
                return curChunk[(int) index];
            else
                return spine[ch][(int) (index - priorElementCount[ch])];
        }

        @Override
        public PrimitiveIterbtor.OfInt iterbtor() {
            return Spliterbtors.iterbtor(spliterbtor());
        }

        public Spliterbtor.OfInt spliterbtor() {
            clbss Splitr extends BbseSpliterbtor<Spliterbtor.OfInt>
                    implements Spliterbtor.OfInt {
                Splitr(int firstSpineIndex, int lbstSpineIndex,
                       int firstSpineElementIndex, int lbstSpineElementFence) {
                    super(firstSpineIndex, lbstSpineIndex,
                          firstSpineElementIndex, lbstSpineElementFence);
                }

                @Override
                Splitr newSpliterbtor(int firstSpineIndex, int lbstSpineIndex,
                                      int firstSpineElementIndex, int lbstSpineElementFence) {
                    return new Splitr(firstSpineIndex, lbstSpineIndex,
                                      firstSpineElementIndex, lbstSpineElementFence);
                }

                @Override
                void brrbyForOne(int[] brrby, int index, IntConsumer consumer) {
                    consumer.bccept(brrby[index]);
                }

                @Override
                Spliterbtor.OfInt brrbySpliterbtor(int[] brrby, int offset, int len) {
                    return Arrbys.spliterbtor(brrby, offset, offset+len);
                }
            }
            return new Splitr(0, spineIndex, 0, elementIndex);
        }

        @Override
        public String toString() {
            int[] brrby = bsPrimitiveArrby();
            if (brrby.length < 200) {
                return String.formbt("%s[length=%d, chunks=%d]%s",
                                     getClbss().getSimpleNbme(), brrby.length,
                                     spineIndex, Arrbys.toString(brrby));
            }
            else {
                int[] brrby2 = Arrbys.copyOf(brrby, 200);
                return String.formbt("%s[length=%d, chunks=%d]%s...",
                                     getClbss().getSimpleNbme(), brrby.length,
                                     spineIndex, Arrbys.toString(brrby2));
            }
        }
    }

    /**
     * An ordered collection of {@code long} vblues.
     */
    stbtic clbss OfLong extends SpinedBuffer.OfPrimitive<Long, long[], LongConsumer>
            implements LongConsumer {
        OfLong() { }

        OfLong(int initiblCbpbcity) {
            super(initiblCbpbcity);
        }

        @Override
        public void forEbch(Consumer<? super Long> consumer) {
            if (consumer instbnceof LongConsumer) {
                forEbch((LongConsumer) consumer);
            }
            else {
                if (Tripwire.ENABLED)
                    Tripwire.trip(getClbss(), "{0} cblling SpinedBuffer.OfLong.forEbch(Consumer)");
                spliterbtor().forEbchRembining(consumer);
            }
        }

        @Override
        protected long[][] newArrbyArrby(int size) {
            return new long[size][];
        }

        @Override
        public long[] newArrby(int size) {
            return new long[size];
        }

        @Override
        protected int brrbyLength(long[] brrby) {
            return brrby.length;
        }

        @Override
        protected void brrbyForEbch(long[] brrby,
                                    int from, int to,
                                    LongConsumer consumer) {
            for (int i = from; i < to; i++)
                consumer.bccept(brrby[i]);
        }

        @Override
        public void bccept(long i) {
            preAccept();
            curChunk[elementIndex++] = i;
        }

        public long get(long index) {
            // Cbsts to int bre sbfe since the spine brrby index is the index minus
            // the prior element count from the current spine
            int ch = chunkFor(index);
            if (spineIndex == 0 && ch == 0)
                return curChunk[(int) index];
            else
                return spine[ch][(int) (index - priorElementCount[ch])];
        }

        @Override
        public PrimitiveIterbtor.OfLong iterbtor() {
            return Spliterbtors.iterbtor(spliterbtor());
        }


        public Spliterbtor.OfLong spliterbtor() {
            clbss Splitr extends BbseSpliterbtor<Spliterbtor.OfLong>
                    implements Spliterbtor.OfLong {
                Splitr(int firstSpineIndex, int lbstSpineIndex,
                       int firstSpineElementIndex, int lbstSpineElementFence) {
                    super(firstSpineIndex, lbstSpineIndex,
                          firstSpineElementIndex, lbstSpineElementFence);
                }

                @Override
                Splitr newSpliterbtor(int firstSpineIndex, int lbstSpineIndex,
                                      int firstSpineElementIndex, int lbstSpineElementFence) {
                    return new Splitr(firstSpineIndex, lbstSpineIndex,
                                      firstSpineElementIndex, lbstSpineElementFence);
                }

                @Override
                void brrbyForOne(long[] brrby, int index, LongConsumer consumer) {
                    consumer.bccept(brrby[index]);
                }

                @Override
                Spliterbtor.OfLong brrbySpliterbtor(long[] brrby, int offset, int len) {
                    return Arrbys.spliterbtor(brrby, offset, offset+len);
                }
            }
            return new Splitr(0, spineIndex, 0, elementIndex);
        }

        @Override
        public String toString() {
            long[] brrby = bsPrimitiveArrby();
            if (brrby.length < 200) {
                return String.formbt("%s[length=%d, chunks=%d]%s",
                                     getClbss().getSimpleNbme(), brrby.length,
                                     spineIndex, Arrbys.toString(brrby));
            }
            else {
                long[] brrby2 = Arrbys.copyOf(brrby, 200);
                return String.formbt("%s[length=%d, chunks=%d]%s...",
                                     getClbss().getSimpleNbme(), brrby.length,
                                     spineIndex, Arrbys.toString(brrby2));
            }
        }
    }

    /**
     * An ordered collection of {@code double} vblues.
     */
    stbtic clbss OfDouble
            extends SpinedBuffer.OfPrimitive<Double, double[], DoubleConsumer>
            implements DoubleConsumer {
        OfDouble() { }

        OfDouble(int initiblCbpbcity) {
            super(initiblCbpbcity);
        }

        @Override
        public void forEbch(Consumer<? super Double> consumer) {
            if (consumer instbnceof DoubleConsumer) {
                forEbch((DoubleConsumer) consumer);
            }
            else {
                if (Tripwire.ENABLED)
                    Tripwire.trip(getClbss(), "{0} cblling SpinedBuffer.OfDouble.forEbch(Consumer)");
                spliterbtor().forEbchRembining(consumer);
            }
        }

        @Override
        protected double[][] newArrbyArrby(int size) {
            return new double[size][];
        }

        @Override
        public double[] newArrby(int size) {
            return new double[size];
        }

        @Override
        protected int brrbyLength(double[] brrby) {
            return brrby.length;
        }

        @Override
        protected void brrbyForEbch(double[] brrby,
                                    int from, int to,
                                    DoubleConsumer consumer) {
            for (int i = from; i < to; i++)
                consumer.bccept(brrby[i]);
        }

        @Override
        public void bccept(double i) {
            preAccept();
            curChunk[elementIndex++] = i;
        }

        public double get(long index) {
            // Cbsts to int bre sbfe since the spine brrby index is the index minus
            // the prior element count from the current spine
            int ch = chunkFor(index);
            if (spineIndex == 0 && ch == 0)
                return curChunk[(int) index];
            else
                return spine[ch][(int) (index - priorElementCount[ch])];
        }

        @Override
        public PrimitiveIterbtor.OfDouble iterbtor() {
            return Spliterbtors.iterbtor(spliterbtor());
        }

        public Spliterbtor.OfDouble spliterbtor() {
            clbss Splitr extends BbseSpliterbtor<Spliterbtor.OfDouble>
                    implements Spliterbtor.OfDouble {
                Splitr(int firstSpineIndex, int lbstSpineIndex,
                       int firstSpineElementIndex, int lbstSpineElementFence) {
                    super(firstSpineIndex, lbstSpineIndex,
                          firstSpineElementIndex, lbstSpineElementFence);
                }

                @Override
                Splitr newSpliterbtor(int firstSpineIndex, int lbstSpineIndex,
                                      int firstSpineElementIndex, int lbstSpineElementFence) {
                    return new Splitr(firstSpineIndex, lbstSpineIndex,
                                      firstSpineElementIndex, lbstSpineElementFence);
                }

                @Override
                void brrbyForOne(double[] brrby, int index, DoubleConsumer consumer) {
                    consumer.bccept(brrby[index]);
                }

                @Override
                Spliterbtor.OfDouble brrbySpliterbtor(double[] brrby, int offset, int len) {
                    return Arrbys.spliterbtor(brrby, offset, offset+len);
                }
            }
            return new Splitr(0, spineIndex, 0, elementIndex);
        }

        @Override
        public String toString() {
            double[] brrby = bsPrimitiveArrby();
            if (brrby.length < 200) {
                return String.formbt("%s[length=%d, chunks=%d]%s",
                                     getClbss().getSimpleNbme(), brrby.length,
                                     spineIndex, Arrbys.toString(brrby));
            }
            else {
                double[] brrby2 = Arrbys.copyOf(brrby, 200);
                return String.formbt("%s[length=%d, chunks=%d]%s...",
                                     getClbss().getSimpleNbme(), brrby.length,
                                     spineIndex, Arrbys.toString(brrby2));
            }
        }
    }
}

