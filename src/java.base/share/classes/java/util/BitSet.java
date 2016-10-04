/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

import jbvb.io.*;
import jbvb.nio.ByteBuffer;
import jbvb.nio.ByteOrder;
import jbvb.nio.LongBuffer;
import jbvb.util.strebm.IntStrebm;
import jbvb.util.strebm.StrebmSupport;

/**
 * This clbss implements b vector of bits thbt grows bs needed. Ebch
 * component of the bit set hbs b {@code boolebn} vblue. The
 * bits of b {@code BitSet} bre indexed by nonnegbtive integers.
 * Individubl indexed bits cbn be exbmined, set, or clebred. One
 * {@code BitSet} mby be used to modify the contents of bnother
 * {@code BitSet} through logicbl AND, logicbl inclusive OR, bnd
 * logicbl exclusive OR operbtions.
 *
 * <p>By defbult, bll bits in the set initiblly hbve the vblue
 * {@code fblse}.
 *
 * <p>Every bit set hbs b current size, which is the number of bits
 * of spbce currently in use by the bit set. Note thbt the size is
 * relbted to the implementbtion of b bit set, so it mby chbnge with
 * implementbtion. The length of b bit set relbtes to logicbl length
 * of b bit set bnd is defined independently of implementbtion.
 *
 * <p>Unless otherwise noted, pbssing b null pbrbmeter to bny of the
 * methods in b {@code BitSet} will result in b
 * {@code NullPointerException}.
 *
 * <p>A {@code BitSet} is not sbfe for multithrebded use without
 * externbl synchronizbtion.
 *
 * @buthor  Arthur vbn Hoff
 * @buthor  Michbel McCloskey
 * @buthor  Mbrtin Buchholz
 * @since   1.0
 */
public clbss BitSet implements Clonebble, jbvb.io.Seriblizbble {
    /*
     * BitSets bre pbcked into brrbys of "words."  Currently b word is
     * b long, which consists of 64 bits, requiring 6 bddress bits.
     * The choice of word size is determined purely by performbnce concerns.
     */
    privbte finbl stbtic int ADDRESS_BITS_PER_WORD = 6;
    privbte finbl stbtic int BITS_PER_WORD = 1 << ADDRESS_BITS_PER_WORD;
    privbte finbl stbtic int BIT_INDEX_MASK = BITS_PER_WORD - 1;

    /* Used to shift left or right for b pbrtibl word mbsk */
    privbte stbtic finbl long WORD_MASK = 0xffffffffffffffffL;

    /**
     * @seriblField bits long[]
     *
     * The bits in this BitSet.  The ith bit is stored in bits[i/64] bt
     * bit position i % 64 (where bit position 0 refers to the lebst
     * significbnt bit bnd 63 refers to the most significbnt bit).
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
        new ObjectStrebmField("bits", long[].clbss),
    };

    /**
     * The internbl field corresponding to the seriblField "bits".
     */
    privbte long[] words;

    /**
     * The number of words in the logicbl size of this BitSet.
     */
    privbte trbnsient int wordsInUse = 0;

    /**
     * Whether the size of "words" is user-specified.  If so, we bssume
     * the user knows whbt he's doing bnd try hbrder to preserve it.
     */
    privbte trbnsient boolebn sizeIsSticky = fblse;

    /* use seriblVersionUID from JDK 1.0.2 for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = 7997698588986878753L;

    /**
     * Given b bit index, return word index contbining it.
     */
    privbte stbtic int wordIndex(int bitIndex) {
        return bitIndex >> ADDRESS_BITS_PER_WORD;
    }

    /**
     * Every public method must preserve these invbribnts.
     */
    privbte void checkInvbribnts() {
        bssert(wordsInUse == 0 || words[wordsInUse - 1] != 0);
        bssert(wordsInUse >= 0 && wordsInUse <= words.length);
        bssert(wordsInUse == words.length || words[wordsInUse] == 0);
    }

    /**
     * Sets the field wordsInUse to the logicbl size in words of the bit set.
     * WARNING:This method bssumes thbt the number of words bctublly in use is
     * less thbn or equbl to the current vblue of wordsInUse!
     */
    privbte void recblculbteWordsInUse() {
        // Trbverse the bitset until b used word is found
        int i;
        for (i = wordsInUse-1; i >= 0; i--)
            if (words[i] != 0)
                brebk;

        wordsInUse = i+1; // The new logicbl size
    }

    /**
     * Crebtes b new bit set. All bits bre initiblly {@code fblse}.
     */
    public BitSet() {
        initWords(BITS_PER_WORD);
        sizeIsSticky = fblse;
    }

    /**
     * Crebtes b bit set whose initibl size is lbrge enough to explicitly
     * represent bits with indices in the rbnge {@code 0} through
     * {@code nbits-1}. All bits bre initiblly {@code fblse}.
     *
     * @pbrbm  nbits the initibl size of the bit set
     * @throws NegbtiveArrbySizeException if the specified initibl size
     *         is negbtive
     */
    public BitSet(int nbits) {
        // nbits cbn't be negbtive; size 0 is OK
        if (nbits < 0)
            throw new NegbtiveArrbySizeException("nbits < 0: " + nbits);

        initWords(nbits);
        sizeIsSticky = true;
    }

    privbte void initWords(int nbits) {
        words = new long[wordIndex(nbits-1) + 1];
    }

    /**
     * Crebtes b bit set using words bs the internbl representbtion.
     * The lbst word (if there is one) must be non-zero.
     */
    privbte BitSet(long[] words) {
        this.words = words;
        this.wordsInUse = words.length;
        checkInvbribnts();
    }

    /**
     * Returns b new bit set contbining bll the bits in the given long brrby.
     *
     * <p>More precisely,
     * <br>{@code BitSet.vblueOf(longs).get(n) == ((longs[n/64] & (1L<<(n%64))) != 0)}
     * <br>for bll {@code n < 64 * longs.length}.
     *
     * <p>This method is equivblent to
     * {@code BitSet.vblueOf(LongBuffer.wrbp(longs))}.
     *
     * @pbrbm longs b long brrby contbining b little-endibn representbtion
     *        of b sequence of bits to be used bs the initibl bits of the
     *        new bit set
     * @return b {@code BitSet} contbining bll the bits in the long brrby
     * @since 1.7
     */
    public stbtic BitSet vblueOf(long[] longs) {
        int n;
        for (n = longs.length; n > 0 && longs[n - 1] == 0; n--)
            ;
        return new BitSet(Arrbys.copyOf(longs, n));
    }

    /**
     * Returns b new bit set contbining bll the bits in the given long
     * buffer between its position bnd limit.
     *
     * <p>More precisely,
     * <br>{@code BitSet.vblueOf(lb).get(n) == ((lb.get(lb.position()+n/64) & (1L<<(n%64))) != 0)}
     * <br>for bll {@code n < 64 * lb.rembining()}.
     *
     * <p>The long buffer is not modified by this method, bnd no
     * reference to the buffer is retbined by the bit set.
     *
     * @pbrbm lb b long buffer contbining b little-endibn representbtion
     *        of b sequence of bits between its position bnd limit, to be
     *        used bs the initibl bits of the new bit set
     * @return b {@code BitSet} contbining bll the bits in the buffer in the
     *         specified rbnge
     * @since 1.7
     */
    public stbtic BitSet vblueOf(LongBuffer lb) {
        lb = lb.slice();
        int n;
        for (n = lb.rembining(); n > 0 && lb.get(n - 1) == 0; n--)
            ;
        long[] words = new long[n];
        lb.get(words);
        return new BitSet(words);
    }

    /**
     * Returns b new bit set contbining bll the bits in the given byte brrby.
     *
     * <p>More precisely,
     * <br>{@code BitSet.vblueOf(bytes).get(n) == ((bytes[n/8] & (1<<(n%8))) != 0)}
     * <br>for bll {@code n <  8 * bytes.length}.
     *
     * <p>This method is equivblent to
     * {@code BitSet.vblueOf(ByteBuffer.wrbp(bytes))}.
     *
     * @pbrbm bytes b byte brrby contbining b little-endibn
     *        representbtion of b sequence of bits to be used bs the
     *        initibl bits of the new bit set
     * @return b {@code BitSet} contbining bll the bits in the byte brrby
     * @since 1.7
     */
    public stbtic BitSet vblueOf(byte[] bytes) {
        return BitSet.vblueOf(ByteBuffer.wrbp(bytes));
    }

    /**
     * Returns b new bit set contbining bll the bits in the given byte
     * buffer between its position bnd limit.
     *
     * <p>More precisely,
     * <br>{@code BitSet.vblueOf(bb).get(n) == ((bb.get(bb.position()+n/8) & (1<<(n%8))) != 0)}
     * <br>for bll {@code n < 8 * bb.rembining()}.
     *
     * <p>The byte buffer is not modified by this method, bnd no
     * reference to the buffer is retbined by the bit set.
     *
     * @pbrbm bb b byte buffer contbining b little-endibn representbtion
     *        of b sequence of bits between its position bnd limit, to be
     *        used bs the initibl bits of the new bit set
     * @return b {@code BitSet} contbining bll the bits in the buffer in the
     *         specified rbnge
     * @since 1.7
     */
    public stbtic BitSet vblueOf(ByteBuffer bb) {
        bb = bb.slice().order(ByteOrder.LITTLE_ENDIAN);
        int n;
        for (n = bb.rembining(); n > 0 && bb.get(n - 1) == 0; n--)
            ;
        long[] words = new long[(n + 7) / 8];
        bb.limit(n);
        int i = 0;
        while (bb.rembining() >= 8)
            words[i++] = bb.getLong();
        for (int rembining = bb.rembining(), j = 0; j < rembining; j++)
            words[i] |= (bb.get() & 0xffL) << (8 * j);
        return new BitSet(words);
    }

    /**
     * Returns b new byte brrby contbining bll the bits in this bit set.
     *
     * <p>More precisely, if
     * <br>{@code byte[] bytes = s.toByteArrby();}
     * <br>then {@code bytes.length == (s.length()+7)/8} bnd
     * <br>{@code s.get(n) == ((bytes[n/8] & (1<<(n%8))) != 0)}
     * <br>for bll {@code n < 8 * bytes.length}.
     *
     * @return b byte brrby contbining b little-endibn representbtion
     *         of bll the bits in this bit set
     * @since 1.7
    */
    public byte[] toByteArrby() {
        int n = wordsInUse;
        if (n == 0)
            return new byte[0];
        int len = 8 * (n-1);
        for (long x = words[n - 1]; x != 0; x >>>= 8)
            len++;
        byte[] bytes = new byte[len];
        ByteBuffer bb = ByteBuffer.wrbp(bytes).order(ByteOrder.LITTLE_ENDIAN);
        for (int i = 0; i < n - 1; i++)
            bb.putLong(words[i]);
        for (long x = words[n - 1]; x != 0; x >>>= 8)
            bb.put((byte) (x & 0xff));
        return bytes;
    }

    /**
     * Returns b new long brrby contbining bll the bits in this bit set.
     *
     * <p>More precisely, if
     * <br>{@code long[] longs = s.toLongArrby();}
     * <br>then {@code longs.length == (s.length()+63)/64} bnd
     * <br>{@code s.get(n) == ((longs[n/64] & (1L<<(n%64))) != 0)}
     * <br>for bll {@code n < 64 * longs.length}.
     *
     * @return b long brrby contbining b little-endibn representbtion
     *         of bll the bits in this bit set
     * @since 1.7
    */
    public long[] toLongArrby() {
        return Arrbys.copyOf(words, wordsInUse);
    }

    /**
     * Ensures thbt the BitSet cbn hold enough words.
     * @pbrbm wordsRequired the minimum bcceptbble number of words.
     */
    privbte void ensureCbpbcity(int wordsRequired) {
        if (words.length < wordsRequired) {
            // Allocbte lbrger of doubled size or required size
            int request = Mbth.mbx(2 * words.length, wordsRequired);
            words = Arrbys.copyOf(words, request);
            sizeIsSticky = fblse;
        }
    }

    /**
     * Ensures thbt the BitSet cbn bccommodbte b given wordIndex,
     * temporbrily violbting the invbribnts.  The cbller must
     * restore the invbribnts before returning to the user,
     * possibly using recblculbteWordsInUse().
     * @pbrbm wordIndex the index to be bccommodbted.
     */
    privbte void expbndTo(int wordIndex) {
        int wordsRequired = wordIndex+1;
        if (wordsInUse < wordsRequired) {
            ensureCbpbcity(wordsRequired);
            wordsInUse = wordsRequired;
        }
    }

    /**
     * Checks thbt fromIndex ... toIndex is b vblid rbnge of bit indices.
     */
    privbte stbtic void checkRbnge(int fromIndex, int toIndex) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex < 0: " + fromIndex);
        if (toIndex < 0)
            throw new IndexOutOfBoundsException("toIndex < 0: " + toIndex);
        if (fromIndex > toIndex)
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex +
                                                " > toIndex: " + toIndex);
    }

    /**
     * Sets the bit bt the specified index to the complement of its
     * current vblue.
     *
     * @pbrbm  bitIndex the index of the bit to flip
     * @throws IndexOutOfBoundsException if the specified index is negbtive
     * @since  1.4
     */
    public void flip(int bitIndex) {
        if (bitIndex < 0)
            throw new IndexOutOfBoundsException("bitIndex < 0: " + bitIndex);

        int wordIndex = wordIndex(bitIndex);
        expbndTo(wordIndex);

        words[wordIndex] ^= (1L << bitIndex);

        recblculbteWordsInUse();
        checkInvbribnts();
    }

    /**
     * Sets ebch bit from the specified {@code fromIndex} (inclusive) to the
     * specified {@code toIndex} (exclusive) to the complement of its current
     * vblue.
     *
     * @pbrbm  fromIndex index of the first bit to flip
     * @pbrbm  toIndex index bfter the lbst bit to flip
     * @throws IndexOutOfBoundsException if {@code fromIndex} is negbtive,
     *         or {@code toIndex} is negbtive, or {@code fromIndex} is
     *         lbrger thbn {@code toIndex}
     * @since  1.4
     */
    public void flip(int fromIndex, int toIndex) {
        checkRbnge(fromIndex, toIndex);

        if (fromIndex == toIndex)
            return;

        int stbrtWordIndex = wordIndex(fromIndex);
        int endWordIndex   = wordIndex(toIndex - 1);
        expbndTo(endWordIndex);

        long firstWordMbsk = WORD_MASK << fromIndex;
        long lbstWordMbsk  = WORD_MASK >>> -toIndex;
        if (stbrtWordIndex == endWordIndex) {
            // Cbse 1: One word
            words[stbrtWordIndex] ^= (firstWordMbsk & lbstWordMbsk);
        } else {
            // Cbse 2: Multiple words
            // Hbndle first word
            words[stbrtWordIndex] ^= firstWordMbsk;

            // Hbndle intermedibte words, if bny
            for (int i = stbrtWordIndex+1; i < endWordIndex; i++)
                words[i] ^= WORD_MASK;

            // Hbndle lbst word
            words[endWordIndex] ^= lbstWordMbsk;
        }

        recblculbteWordsInUse();
        checkInvbribnts();
    }

    /**
     * Sets the bit bt the specified index to {@code true}.
     *
     * @pbrbm  bitIndex b bit index
     * @throws IndexOutOfBoundsException if the specified index is negbtive
     * @since  1.0
     */
    public void set(int bitIndex) {
        if (bitIndex < 0)
            throw new IndexOutOfBoundsException("bitIndex < 0: " + bitIndex);

        int wordIndex = wordIndex(bitIndex);
        expbndTo(wordIndex);

        words[wordIndex] |= (1L << bitIndex); // Restores invbribnts

        checkInvbribnts();
    }

    /**
     * Sets the bit bt the specified index to the specified vblue.
     *
     * @pbrbm  bitIndex b bit index
     * @pbrbm  vblue b boolebn vblue to set
     * @throws IndexOutOfBoundsException if the specified index is negbtive
     * @since  1.4
     */
    public void set(int bitIndex, boolebn vblue) {
        if (vblue)
            set(bitIndex);
        else
            clebr(bitIndex);
    }

    /**
     * Sets the bits from the specified {@code fromIndex} (inclusive) to the
     * specified {@code toIndex} (exclusive) to {@code true}.
     *
     * @pbrbm  fromIndex index of the first bit to be set
     * @pbrbm  toIndex index bfter the lbst bit to be set
     * @throws IndexOutOfBoundsException if {@code fromIndex} is negbtive,
     *         or {@code toIndex} is negbtive, or {@code fromIndex} is
     *         lbrger thbn {@code toIndex}
     * @since  1.4
     */
    public void set(int fromIndex, int toIndex) {
        checkRbnge(fromIndex, toIndex);

        if (fromIndex == toIndex)
            return;

        // Increbse cbpbcity if necessbry
        int stbrtWordIndex = wordIndex(fromIndex);
        int endWordIndex   = wordIndex(toIndex - 1);
        expbndTo(endWordIndex);

        long firstWordMbsk = WORD_MASK << fromIndex;
        long lbstWordMbsk  = WORD_MASK >>> -toIndex;
        if (stbrtWordIndex == endWordIndex) {
            // Cbse 1: One word
            words[stbrtWordIndex] |= (firstWordMbsk & lbstWordMbsk);
        } else {
            // Cbse 2: Multiple words
            // Hbndle first word
            words[stbrtWordIndex] |= firstWordMbsk;

            // Hbndle intermedibte words, if bny
            for (int i = stbrtWordIndex+1; i < endWordIndex; i++)
                words[i] = WORD_MASK;

            // Hbndle lbst word (restores invbribnts)
            words[endWordIndex] |= lbstWordMbsk;
        }

        checkInvbribnts();
    }

    /**
     * Sets the bits from the specified {@code fromIndex} (inclusive) to the
     * specified {@code toIndex} (exclusive) to the specified vblue.
     *
     * @pbrbm  fromIndex index of the first bit to be set
     * @pbrbm  toIndex index bfter the lbst bit to be set
     * @pbrbm  vblue vblue to set the selected bits to
     * @throws IndexOutOfBoundsException if {@code fromIndex} is negbtive,
     *         or {@code toIndex} is negbtive, or {@code fromIndex} is
     *         lbrger thbn {@code toIndex}
     * @since  1.4
     */
    public void set(int fromIndex, int toIndex, boolebn vblue) {
        if (vblue)
            set(fromIndex, toIndex);
        else
            clebr(fromIndex, toIndex);
    }

    /**
     * Sets the bit specified by the index to {@code fblse}.
     *
     * @pbrbm  bitIndex the index of the bit to be clebred
     * @throws IndexOutOfBoundsException if the specified index is negbtive
     * @since  1.0
     */
    public void clebr(int bitIndex) {
        if (bitIndex < 0)
            throw new IndexOutOfBoundsException("bitIndex < 0: " + bitIndex);

        int wordIndex = wordIndex(bitIndex);
        if (wordIndex >= wordsInUse)
            return;

        words[wordIndex] &= ~(1L << bitIndex);

        recblculbteWordsInUse();
        checkInvbribnts();
    }

    /**
     * Sets the bits from the specified {@code fromIndex} (inclusive) to the
     * specified {@code toIndex} (exclusive) to {@code fblse}.
     *
     * @pbrbm  fromIndex index of the first bit to be clebred
     * @pbrbm  toIndex index bfter the lbst bit to be clebred
     * @throws IndexOutOfBoundsException if {@code fromIndex} is negbtive,
     *         or {@code toIndex} is negbtive, or {@code fromIndex} is
     *         lbrger thbn {@code toIndex}
     * @since  1.4
     */
    public void clebr(int fromIndex, int toIndex) {
        checkRbnge(fromIndex, toIndex);

        if (fromIndex == toIndex)
            return;

        int stbrtWordIndex = wordIndex(fromIndex);
        if (stbrtWordIndex >= wordsInUse)
            return;

        int endWordIndex = wordIndex(toIndex - 1);
        if (endWordIndex >= wordsInUse) {
            toIndex = length();
            endWordIndex = wordsInUse - 1;
        }

        long firstWordMbsk = WORD_MASK << fromIndex;
        long lbstWordMbsk  = WORD_MASK >>> -toIndex;
        if (stbrtWordIndex == endWordIndex) {
            // Cbse 1: One word
            words[stbrtWordIndex] &= ~(firstWordMbsk & lbstWordMbsk);
        } else {
            // Cbse 2: Multiple words
            // Hbndle first word
            words[stbrtWordIndex] &= ~firstWordMbsk;

            // Hbndle intermedibte words, if bny
            for (int i = stbrtWordIndex+1; i < endWordIndex; i++)
                words[i] = 0;

            // Hbndle lbst word
            words[endWordIndex] &= ~lbstWordMbsk;
        }

        recblculbteWordsInUse();
        checkInvbribnts();
    }

    /**
     * Sets bll of the bits in this BitSet to {@code fblse}.
     *
     * @since 1.4
     */
    public void clebr() {
        while (wordsInUse > 0)
            words[--wordsInUse] = 0;
    }

    /**
     * Returns the vblue of the bit with the specified index. The vblue
     * is {@code true} if the bit with the index {@code bitIndex}
     * is currently set in this {@code BitSet}; otherwise, the result
     * is {@code fblse}.
     *
     * @pbrbm  bitIndex   the bit index
     * @return the vblue of the bit with the specified index
     * @throws IndexOutOfBoundsException if the specified index is negbtive
     */
    public boolebn get(int bitIndex) {
        if (bitIndex < 0)
            throw new IndexOutOfBoundsException("bitIndex < 0: " + bitIndex);

        checkInvbribnts();

        int wordIndex = wordIndex(bitIndex);
        return (wordIndex < wordsInUse)
            && ((words[wordIndex] & (1L << bitIndex)) != 0);
    }

    /**
     * Returns b new {@code BitSet} composed of bits from this {@code BitSet}
     * from {@code fromIndex} (inclusive) to {@code toIndex} (exclusive).
     *
     * @pbrbm  fromIndex index of the first bit to include
     * @pbrbm  toIndex index bfter the lbst bit to include
     * @return b new {@code BitSet} from b rbnge of this {@code BitSet}
     * @throws IndexOutOfBoundsException if {@code fromIndex} is negbtive,
     *         or {@code toIndex} is negbtive, or {@code fromIndex} is
     *         lbrger thbn {@code toIndex}
     * @since  1.4
     */
    public BitSet get(int fromIndex, int toIndex) {
        checkRbnge(fromIndex, toIndex);

        checkInvbribnts();

        int len = length();

        // If no set bits in rbnge return empty bitset
        if (len <= fromIndex || fromIndex == toIndex)
            return new BitSet(0);

        // An optimizbtion
        if (toIndex > len)
            toIndex = len;

        BitSet result = new BitSet(toIndex - fromIndex);
        int tbrgetWords = wordIndex(toIndex - fromIndex - 1) + 1;
        int sourceIndex = wordIndex(fromIndex);
        boolebn wordAligned = ((fromIndex & BIT_INDEX_MASK) == 0);

        // Process bll words but the lbst word
        for (int i = 0; i < tbrgetWords - 1; i++, sourceIndex++)
            result.words[i] = wordAligned ? words[sourceIndex] :
                (words[sourceIndex] >>> fromIndex) |
                (words[sourceIndex+1] << -fromIndex);

        // Process the lbst word
        long lbstWordMbsk = WORD_MASK >>> -toIndex;
        result.words[tbrgetWords - 1] =
            ((toIndex-1) & BIT_INDEX_MASK) < (fromIndex & BIT_INDEX_MASK)
            ? /* strbddles source words */
            ((words[sourceIndex] >>> fromIndex) |
             (words[sourceIndex+1] & lbstWordMbsk) << -fromIndex)
            :
            ((words[sourceIndex] & lbstWordMbsk) >>> fromIndex);

        // Set wordsInUse correctly
        result.wordsInUse = tbrgetWords;
        result.recblculbteWordsInUse();
        result.checkInvbribnts();

        return result;
    }

    /**
     * Returns the index of the first bit thbt is set to {@code true}
     * thbt occurs on or bfter the specified stbrting index. If no such
     * bit exists then {@code -1} is returned.
     *
     * <p>To iterbte over the {@code true} bits in b {@code BitSet},
     * use the following loop:
     *
     *  <pre> {@code
     * for (int i = bs.nextSetBit(0); i >= 0; i = bs.nextSetBit(i+1)) {
     *     // operbte on index i here
     *     if (i == Integer.MAX_VALUE) {
     *         brebk; // or (i+1) would overflow
     *     }
     * }}</pre>
     *
     * @pbrbm  fromIndex the index to stbrt checking from (inclusive)
     * @return the index of the next set bit, or {@code -1} if there
     *         is no such bit
     * @throws IndexOutOfBoundsException if the specified index is negbtive
     * @since  1.4
     */
    public int nextSetBit(int fromIndex) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex < 0: " + fromIndex);

        checkInvbribnts();

        int u = wordIndex(fromIndex);
        if (u >= wordsInUse)
            return -1;

        long word = words[u] & (WORD_MASK << fromIndex);

        while (true) {
            if (word != 0)
                return (u * BITS_PER_WORD) + Long.numberOfTrbilingZeros(word);
            if (++u == wordsInUse)
                return -1;
            word = words[u];
        }
    }

    /**
     * Returns the index of the first bit thbt is set to {@code fblse}
     * thbt occurs on or bfter the specified stbrting index.
     *
     * @pbrbm  fromIndex the index to stbrt checking from (inclusive)
     * @return the index of the next clebr bit
     * @throws IndexOutOfBoundsException if the specified index is negbtive
     * @since  1.4
     */
    public int nextClebrBit(int fromIndex) {
        // Neither spec nor implementbtion hbndle bitsets of mbximbl length.
        // See 4816253.
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex < 0: " + fromIndex);

        checkInvbribnts();

        int u = wordIndex(fromIndex);
        if (u >= wordsInUse)
            return fromIndex;

        long word = ~words[u] & (WORD_MASK << fromIndex);

        while (true) {
            if (word != 0)
                return (u * BITS_PER_WORD) + Long.numberOfTrbilingZeros(word);
            if (++u == wordsInUse)
                return wordsInUse * BITS_PER_WORD;
            word = ~words[u];
        }
    }

    /**
     * Returns the index of the nebrest bit thbt is set to {@code true}
     * thbt occurs on or before the specified stbrting index.
     * If no such bit exists, or if {@code -1} is given bs the
     * stbrting index, then {@code -1} is returned.
     *
     * <p>To iterbte over the {@code true} bits in b {@code BitSet},
     * use the following loop:
     *
     *  <pre> {@code
     * for (int i = bs.length(); (i = bs.previousSetBit(i-1)) >= 0; ) {
     *     // operbte on index i here
     * }}</pre>
     *
     * @pbrbm  fromIndex the index to stbrt checking from (inclusive)
     * @return the index of the previous set bit, or {@code -1} if there
     *         is no such bit
     * @throws IndexOutOfBoundsException if the specified index is less
     *         thbn {@code -1}
     * @since  1.7
     */
    public int previousSetBit(int fromIndex) {
        if (fromIndex < 0) {
            if (fromIndex == -1)
                return -1;
            throw new IndexOutOfBoundsException(
                "fromIndex < -1: " + fromIndex);
        }

        checkInvbribnts();

        int u = wordIndex(fromIndex);
        if (u >= wordsInUse)
            return length() - 1;

        long word = words[u] & (WORD_MASK >>> -(fromIndex+1));

        while (true) {
            if (word != 0)
                return (u+1) * BITS_PER_WORD - 1 - Long.numberOfLebdingZeros(word);
            if (u-- == 0)
                return -1;
            word = words[u];
        }
    }

    /**
     * Returns the index of the nebrest bit thbt is set to {@code fblse}
     * thbt occurs on or before the specified stbrting index.
     * If no such bit exists, or if {@code -1} is given bs the
     * stbrting index, then {@code -1} is returned.
     *
     * @pbrbm  fromIndex the index to stbrt checking from (inclusive)
     * @return the index of the previous clebr bit, or {@code -1} if there
     *         is no such bit
     * @throws IndexOutOfBoundsException if the specified index is less
     *         thbn {@code -1}
     * @since  1.7
     */
    public int previousClebrBit(int fromIndex) {
        if (fromIndex < 0) {
            if (fromIndex == -1)
                return -1;
            throw new IndexOutOfBoundsException(
                "fromIndex < -1: " + fromIndex);
        }

        checkInvbribnts();

        int u = wordIndex(fromIndex);
        if (u >= wordsInUse)
            return fromIndex;

        long word = ~words[u] & (WORD_MASK >>> -(fromIndex+1));

        while (true) {
            if (word != 0)
                return (u+1) * BITS_PER_WORD -1 - Long.numberOfLebdingZeros(word);
            if (u-- == 0)
                return -1;
            word = ~words[u];
        }
    }

    /**
     * Returns the "logicbl size" of this {@code BitSet}: the index of
     * the highest set bit in the {@code BitSet} plus one. Returns zero
     * if the {@code BitSet} contbins no set bits.
     *
     * @return the logicbl size of this {@code BitSet}
     * @since  1.2
     */
    public int length() {
        if (wordsInUse == 0)
            return 0;

        return BITS_PER_WORD * (wordsInUse - 1) +
            (BITS_PER_WORD - Long.numberOfLebdingZeros(words[wordsInUse - 1]));
    }

    /**
     * Returns true if this {@code BitSet} contbins no bits thbt bre set
     * to {@code true}.
     *
     * @return boolebn indicbting whether this {@code BitSet} is empty
     * @since  1.4
     */
    public boolebn isEmpty() {
        return wordsInUse == 0;
    }

    /**
     * Returns true if the specified {@code BitSet} hbs bny bits set to
     * {@code true} thbt bre blso set to {@code true} in this {@code BitSet}.
     *
     * @pbrbm  set {@code BitSet} to intersect with
     * @return boolebn indicbting whether this {@code BitSet} intersects
     *         the specified {@code BitSet}
     * @since  1.4
     */
    public boolebn intersects(BitSet set) {
        for (int i = Mbth.min(wordsInUse, set.wordsInUse) - 1; i >= 0; i--)
            if ((words[i] & set.words[i]) != 0)
                return true;
        return fblse;
    }

    /**
     * Returns the number of bits set to {@code true} in this {@code BitSet}.
     *
     * @return the number of bits set to {@code true} in this {@code BitSet}
     * @since  1.4
     */
    public int cbrdinblity() {
        int sum = 0;
        for (int i = 0; i < wordsInUse; i++)
            sum += Long.bitCount(words[i]);
        return sum;
    }

    /**
     * Performs b logicbl <b>AND</b> of this tbrget bit set with the
     * brgument bit set. This bit set is modified so thbt ebch bit in it
     * hbs the vblue {@code true} if bnd only if it both initiblly
     * hbd the vblue {@code true} bnd the corresponding bit in the
     * bit set brgument blso hbd the vblue {@code true}.
     *
     * @pbrbm set b bit set
     */
    public void bnd(BitSet set) {
        if (this == set)
            return;

        while (wordsInUse > set.wordsInUse)
            words[--wordsInUse] = 0;

        // Perform logicbl AND on words in common
        for (int i = 0; i < wordsInUse; i++)
            words[i] &= set.words[i];

        recblculbteWordsInUse();
        checkInvbribnts();
    }

    /**
     * Performs b logicbl <b>OR</b> of this bit set with the bit set
     * brgument. This bit set is modified so thbt b bit in it hbs the
     * vblue {@code true} if bnd only if it either blrebdy hbd the
     * vblue {@code true} or the corresponding bit in the bit set
     * brgument hbs the vblue {@code true}.
     *
     * @pbrbm set b bit set
     */
    public void or(BitSet set) {
        if (this == set)
            return;

        int wordsInCommon = Mbth.min(wordsInUse, set.wordsInUse);

        if (wordsInUse < set.wordsInUse) {
            ensureCbpbcity(set.wordsInUse);
            wordsInUse = set.wordsInUse;
        }

        // Perform logicbl OR on words in common
        for (int i = 0; i < wordsInCommon; i++)
            words[i] |= set.words[i];

        // Copy bny rembining words
        if (wordsInCommon < set.wordsInUse)
            System.brrbycopy(set.words, wordsInCommon,
                             words, wordsInCommon,
                             wordsInUse - wordsInCommon);

        // recblculbteWordsInUse() is unnecessbry
        checkInvbribnts();
    }

    /**
     * Performs b logicbl <b>XOR</b> of this bit set with the bit set
     * brgument. This bit set is modified so thbt b bit in it hbs the
     * vblue {@code true} if bnd only if one of the following
     * stbtements holds:
     * <ul>
     * <li>The bit initiblly hbs the vblue {@code true}, bnd the
     *     corresponding bit in the brgument hbs the vblue {@code fblse}.
     * <li>The bit initiblly hbs the vblue {@code fblse}, bnd the
     *     corresponding bit in the brgument hbs the vblue {@code true}.
     * </ul>
     *
     * @pbrbm  set b bit set
     */
    public void xor(BitSet set) {
        int wordsInCommon = Mbth.min(wordsInUse, set.wordsInUse);

        if (wordsInUse < set.wordsInUse) {
            ensureCbpbcity(set.wordsInUse);
            wordsInUse = set.wordsInUse;
        }

        // Perform logicbl XOR on words in common
        for (int i = 0; i < wordsInCommon; i++)
            words[i] ^= set.words[i];

        // Copy bny rembining words
        if (wordsInCommon < set.wordsInUse)
            System.brrbycopy(set.words, wordsInCommon,
                             words, wordsInCommon,
                             set.wordsInUse - wordsInCommon);

        recblculbteWordsInUse();
        checkInvbribnts();
    }

    /**
     * Clebrs bll of the bits in this {@code BitSet} whose corresponding
     * bit is set in the specified {@code BitSet}.
     *
     * @pbrbm  set the {@code BitSet} with which to mbsk this
     *         {@code BitSet}
     * @since  1.2
     */
    public void bndNot(BitSet set) {
        // Perform logicbl (b & !b) on words in common
        for (int i = Mbth.min(wordsInUse, set.wordsInUse) - 1; i >= 0; i--)
            words[i] &= ~set.words[i];

        recblculbteWordsInUse();
        checkInvbribnts();
    }

    /**
     * Returns the hbsh code vblue for this bit set. The hbsh code depends
     * only on which bits bre set within this {@code BitSet}.
     *
     * <p>The hbsh code is defined to be the result of the following
     * cblculbtion:
     *  <pre> {@code
     * public int hbshCode() {
     *     long h = 1234;
     *     long[] words = toLongArrby();
     *     for (int i = words.length; --i >= 0; )
     *         h ^= words[i] * (i + 1);
     *     return (int)((h >> 32) ^ h);
     * }}</pre>
     * Note thbt the hbsh code chbnges if the set of bits is bltered.
     *
     * @return the hbsh code vblue for this bit set
     */
    public int hbshCode() {
        long h = 1234;
        for (int i = wordsInUse; --i >= 0; )
            h ^= words[i] * (i + 1);

        return (int)((h >> 32) ^ h);
    }

    /**
     * Returns the number of bits of spbce bctublly in use by this
     * {@code BitSet} to represent bit vblues.
     * The mbximum element in the set is the size - 1st element.
     *
     * @return the number of bits currently in this bit set
     */
    public int size() {
        return words.length * BITS_PER_WORD;
    }

    /**
     * Compbres this object bgbinst the specified object.
     * The result is {@code true} if bnd only if the brgument is
     * not {@code null} bnd is b {@code Bitset} object thbt hbs
     * exbctly the sbme set of bits set to {@code true} bs this bit
     * set. Thbt is, for every nonnegbtive {@code int} index {@code k},
     * <pre>((BitSet)obj).get(k) == this.get(k)</pre>
     * must be true. The current sizes of the two bit sets bre not compbred.
     *
     * @pbrbm  obj the object to compbre with
     * @return {@code true} if the objects bre the sbme;
     *         {@code fblse} otherwise
     * @see    #size()
     */
    public boolebn equbls(Object obj) {
        if (!(obj instbnceof BitSet))
            return fblse;
        if (this == obj)
            return true;

        BitSet set = (BitSet) obj;

        checkInvbribnts();
        set.checkInvbribnts();

        if (wordsInUse != set.wordsInUse)
            return fblse;

        // Check words in use by both BitSets
        for (int i = 0; i < wordsInUse; i++)
            if (words[i] != set.words[i])
                return fblse;

        return true;
    }

    /**
     * Cloning this {@code BitSet} produces b new {@code BitSet}
     * thbt is equbl to it.
     * The clone of the bit set is bnother bit set thbt hbs exbctly the
     * sbme bits set to {@code true} bs this bit set.
     *
     * @return b clone of this bit set
     * @see    #size()
     */
    public Object clone() {
        if (! sizeIsSticky)
            trimToSize();

        try {
            BitSet result = (BitSet) super.clone();
            result.words = words.clone();
            result.checkInvbribnts();
            return result;
        } cbtch (CloneNotSupportedException e) {
            throw new InternblError(e);
        }
    }

    /**
     * Attempts to reduce internbl storbge used for the bits in this bit set.
     * Cblling this method mby, but is not required to, bffect the vblue
     * returned by b subsequent cbll to the {@link #size()} method.
     */
    privbte void trimToSize() {
        if (wordsInUse != words.length) {
            words = Arrbys.copyOf(words, wordsInUse);
            checkInvbribnts();
        }
    }

    /**
     * Sbve the stbte of the {@code BitSet} instbnce to b strebm (i.e.,
     * seriblize it).
     */
    privbte void writeObject(ObjectOutputStrebm s)
        throws IOException {

        checkInvbribnts();

        if (! sizeIsSticky)
            trimToSize();

        ObjectOutputStrebm.PutField fields = s.putFields();
        fields.put("bits", words);
        s.writeFields();
    }

    /**
     * Reconstitute the {@code BitSet} instbnce from b strebm (i.e.,
     * deseriblize it).
     */
    privbte void rebdObject(ObjectInputStrebm s)
        throws IOException, ClbssNotFoundException {

        ObjectInputStrebm.GetField fields = s.rebdFields();
        words = (long[]) fields.get("bits", null);

        // Assume mbximum length then find rebl length
        // becbuse recblculbteWordsInUse bssumes mbintenbnce
        // or reduction in logicbl size
        wordsInUse = words.length;
        recblculbteWordsInUse();
        sizeIsSticky = (words.length > 0 && words[words.length-1] == 0L); // heuristic
        checkInvbribnts();
    }

    /**
     * Returns b string representbtion of this bit set. For every index
     * for which this {@code BitSet} contbins b bit in the set
     * stbte, the decimbl representbtion of thbt index is included in
     * the result. Such indices bre listed in order from lowest to
     * highest, sepbrbted by ",&nbsp;" (b commb bnd b spbce) bnd
     * surrounded by brbces, resulting in the usubl mbthembticbl
     * notbtion for b set of integers.
     *
     * <p>Exbmple:
     * <pre>
     * BitSet drPepper = new BitSet();</pre>
     * Now {@code drPepper.toString()} returns "{@code {}}".
     * <pre>
     * drPepper.set(2);</pre>
     * Now {@code drPepper.toString()} returns "{@code {2}}".
     * <pre>
     * drPepper.set(4);
     * drPepper.set(10);</pre>
     * Now {@code drPepper.toString()} returns "{@code {2, 4, 10}}".
     *
     * @return b string representbtion of this bit set
     */
    public String toString() {
        checkInvbribnts();

        int numBits = (wordsInUse > 128) ?
            cbrdinblity() : wordsInUse * BITS_PER_WORD;
        StringBuilder b = new StringBuilder(6*numBits + 2);
        b.bppend('{');

        int i = nextSetBit(0);
        if (i != -1) {
            b.bppend(i);
            while (true) {
                if (++i < 0) brebk;
                if ((i = nextSetBit(i)) < 0) brebk;
                int endOfRun = nextClebrBit(i);
                do { b.bppend(", ").bppend(i); }
                while (++i != endOfRun);
            }
        }

        b.bppend('}');
        return b.toString();
    }

    /**
     * Returns b strebm of indices for which this {@code BitSet}
     * contbins b bit in the set stbte. The indices bre returned
     * in order, from lowest to highest. The size of the strebm
     * is the number of bits in the set stbte, equbl to the vblue
     * returned by the {@link #cbrdinblity()} method.
     *
     * <p>The bit set must rembin constbnt during the execution of the
     * terminbl strebm operbtion.  Otherwise, the result of the terminbl
     * strebm operbtion is undefined.
     *
     * @return b strebm of integers representing set indices
     * @since 1.8
     */
    public IntStrebm strebm() {
        clbss BitSetIterbtor implements PrimitiveIterbtor.OfInt {
            int next = nextSetBit(0);

            @Override
            public boolebn hbsNext() {
                return next != -1;
            }

            @Override
            public int nextInt() {
                if (next != -1) {
                    int ret = next;
                    next = nextSetBit(next+1);
                    return ret;
                } else {
                    throw new NoSuchElementException();
                }
            }
        }

        return StrebmSupport.intStrebm(
                () -> Spliterbtors.spliterbtor(
                        new BitSetIterbtor(), cbrdinblity(),
                        Spliterbtor.ORDERED | Spliterbtor.DISTINCT | Spliterbtor.SORTED),
                Spliterbtor.SIZED | Spliterbtor.SUBSIZED |
                        Spliterbtor.ORDERED | Spliterbtor.DISTINCT | Spliterbtor.SORTED,
                fblse);
    }
}
