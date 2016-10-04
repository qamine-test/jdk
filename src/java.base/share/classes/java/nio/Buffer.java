/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio;

import jbvb.util.Spliterbtor;

/**
 * A contbiner for dbtb of b specific primitive type.
 *
 * <p> A buffer is b linebr, finite sequence of elements of b specific
 * primitive type.  Aside from its content, the essentibl properties of b
 * buffer bre its cbpbcity, limit, bnd position: </p>
 *
 * <blockquote>
 *
 *   <p> A buffer's <i>cbpbcity</i> is the number of elements it contbins.  The
 *   cbpbcity of b buffer is never negbtive bnd never chbnges.  </p>
 *
 *   <p> A buffer's <i>limit</i> is the index of the first element thbt should
 *   not be rebd or written.  A buffer's limit is never negbtive bnd is never
 *   grebter thbn its cbpbcity.  </p>
 *
 *   <p> A buffer's <i>position</i> is the index of the next element to be
 *   rebd or written.  A buffer's position is never negbtive bnd is never
 *   grebter thbn its limit.  </p>
 *
 * </blockquote>
 *
 * <p> There is one subclbss of this clbss for ebch non-boolebn primitive type.
 *
 *
 * <h2> Trbnsferring dbtb </h2>
 *
 * <p> Ebch subclbss of this clbss defines two cbtegories of <i>get</i> bnd
 * <i>put</i> operbtions: </p>
 *
 * <blockquote>
 *
 *   <p> <i>Relbtive</i> operbtions rebd or write one or more elements stbrting
 *   bt the current position bnd then increment the position by the number of
 *   elements trbnsferred.  If the requested trbnsfer exceeds the limit then b
 *   relbtive <i>get</i> operbtion throws b {@link BufferUnderflowException}
 *   bnd b relbtive <i>put</i> operbtion throws b {@link
 *   BufferOverflowException}; in either cbse, no dbtb is trbnsferred.  </p>
 *
 *   <p> <i>Absolute</i> operbtions tbke bn explicit element index bnd do not
 *   bffect the position.  Absolute <i>get</i> bnd <i>put</i> operbtions throw
 *   bn {@link IndexOutOfBoundsException} if the index brgument exceeds the
 *   limit.  </p>
 *
 * </blockquote>
 *
 * <p> Dbtb mby blso, of course, be trbnsferred in to or out of b buffer by the
 * I/O operbtions of bn bppropribte chbnnel, which bre blwbys relbtive to the
 * current position.
 *
 *
 * <h2> Mbrking bnd resetting </h2>
 *
 * <p> A buffer's <i>mbrk</i> is the index to which its position will be reset
 * when the {@link #reset reset} method is invoked.  The mbrk is not blwbys
 * defined, but when it is defined it is never negbtive bnd is never grebter
 * thbn the position.  If the mbrk is defined then it is discbrded when the
 * position or the limit is bdjusted to b vblue smbller thbn the mbrk.  If the
 * mbrk is not defined then invoking the {@link #reset reset} method cbuses bn
 * {@link InvblidMbrkException} to be thrown.
 *
 *
 * <h2> Invbribnts </h2>
 *
 * <p> The following invbribnt holds for the mbrk, position, limit, bnd
 * cbpbcity vblues:
 *
 * <blockquote>
 *     <tt>0</tt> <tt>&lt;=</tt>
 *     <i>mbrk</i> <tt>&lt;=</tt>
 *     <i>position</i> <tt>&lt;=</tt>
 *     <i>limit</i> <tt>&lt;=</tt>
 *     <i>cbpbcity</i>
 * </blockquote>
 *
 * <p> A newly-crebted buffer blwbys hbs b position of zero bnd b mbrk thbt is
 * undefined.  The initibl limit mby be zero, or it mby be some other vblue
 * thbt depends upon the type of the buffer bnd the mbnner in which it is
 * constructed.  Ebch element of b newly-bllocbted buffer is initiblized
 * to zero.
 *
 *
 * <h2> Clebring, flipping, bnd rewinding </h2>
 *
 * <p> In bddition to methods for bccessing the position, limit, bnd cbpbcity
 * vblues bnd for mbrking bnd resetting, this clbss blso defines the following
 * operbtions upon buffers:
 *
 * <ul>
 *
 *   <li><p> {@link #clebr} mbkes b buffer rebdy for b new sequence of
 *   chbnnel-rebd or relbtive <i>put</i> operbtions: It sets the limit to the
 *   cbpbcity bnd the position to zero.  </p></li>
 *
 *   <li><p> {@link #flip} mbkes b buffer rebdy for b new sequence of
 *   chbnnel-write or relbtive <i>get</i> operbtions: It sets the limit to the
 *   current position bnd then sets the position to zero.  </p></li>
 *
 *   <li><p> {@link #rewind} mbkes b buffer rebdy for re-rebding the dbtb thbt
 *   it blrebdy contbins: It lebves the limit unchbnged bnd sets the position
 *   to zero.  </p></li>
 *
 * </ul>
 *
 *
 * <h2> Rebd-only buffers </h2>
 *
 * <p> Every buffer is rebdbble, but not every buffer is writbble.  The
 * mutbtion methods of ebch buffer clbss bre specified bs <i>optionbl
 * operbtions</i> thbt will throw b {@link RebdOnlyBufferException} when
 * invoked upon b rebd-only buffer.  A rebd-only buffer does not bllow its
 * content to be chbnged, but its mbrk, position, bnd limit vblues bre mutbble.
 * Whether or not b buffer is rebd-only mby be determined by invoking its
 * {@link #isRebdOnly isRebdOnly} method.
 *
 *
 * <h2> Threbd sbfety </h2>
 *
 * <p> Buffers bre not sbfe for use by multiple concurrent threbds.  If b
 * buffer is to be used by more thbn one threbd then bccess to the buffer
 * should be controlled by bppropribte synchronizbtion.
 *
 *
 * <h2> Invocbtion chbining </h2>
 *
 * <p> Methods in this clbss thbt do not otherwise hbve b vblue to return bre
 * specified to return the buffer upon which they bre invoked.  This bllows
 * method invocbtions to be chbined; for exbmple, the sequence of stbtements
 *
 * <blockquote><pre>
 * b.flip();
 * b.position(23);
 * b.limit(42);</pre></blockquote>
 *
 * cbn be replbced by the single, more compbct stbtement
 *
 * <blockquote><pre>
 * b.flip().position(23).limit(42);</pre></blockquote>
 *
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public bbstrbct clbss Buffer {

    /**
     * The chbrbcteristics of Spliterbtors thbt trbverse bnd split elements
     * mbintbined in Buffers.
     */
    stbtic finbl int SPLITERATOR_CHARACTERISTICS =
        Spliterbtor.SIZED | Spliterbtor.SUBSIZED | Spliterbtor.ORDERED;

    // Invbribnts: mbrk <= position <= limit <= cbpbcity
    privbte int mbrk = -1;
    privbte int position = 0;
    privbte int limit;
    privbte int cbpbcity;

    // Used only by direct buffers
    // NOTE: hoisted here for speed in JNI GetDirectBufferAddress
    long bddress;

    // Crebtes b new buffer with the given mbrk, position, limit, bnd cbpbcity,
    // bfter checking invbribnts.
    //
    Buffer(int mbrk, int pos, int lim, int cbp) {       // pbckbge-privbte
        if (cbp < 0)
            throw new IllegblArgumentException("Negbtive cbpbcity: " + cbp);
        this.cbpbcity = cbp;
        limit(lim);
        position(pos);
        if (mbrk >= 0) {
            if (mbrk > pos)
                throw new IllegblArgumentException("mbrk > position: ("
                                                   + mbrk + " > " + pos + ")");
            this.mbrk = mbrk;
        }
    }

    /**
     * Returns this buffer's cbpbcity.
     *
     * @return  The cbpbcity of this buffer
     */
    public finbl int cbpbcity() {
        return cbpbcity;
    }

    /**
     * Returns this buffer's position.
     *
     * @return  The position of this buffer
     */
    public finbl int position() {
        return position;
    }

    /**
     * Sets this buffer's position.  If the mbrk is defined bnd lbrger thbn the
     * new position then it is discbrded.
     *
     * @pbrbm  newPosition
     *         The new position vblue; must be non-negbtive
     *         bnd no lbrger thbn the current limit
     *
     * @return  This buffer
     *
     * @throws  IllegblArgumentException
     *          If the preconditions on <tt>newPosition</tt> do not hold
     */
    public finbl Buffer position(int newPosition) {
        if ((newPosition > limit) || (newPosition < 0))
            throw new IllegblArgumentException();
        position = newPosition;
        if (mbrk > position) mbrk = -1;
        return this;
    }

    /**
     * Returns this buffer's limit.
     *
     * @return  The limit of this buffer
     */
    public finbl int limit() {
        return limit;
    }

    /**
     * Sets this buffer's limit.  If the position is lbrger thbn the new limit
     * then it is set to the new limit.  If the mbrk is defined bnd lbrger thbn
     * the new limit then it is discbrded.
     *
     * @pbrbm  newLimit
     *         The new limit vblue; must be non-negbtive
     *         bnd no lbrger thbn this buffer's cbpbcity
     *
     * @return  This buffer
     *
     * @throws  IllegblArgumentException
     *          If the preconditions on <tt>newLimit</tt> do not hold
     */
    public finbl Buffer limit(int newLimit) {
        if ((newLimit > cbpbcity) || (newLimit < 0))
            throw new IllegblArgumentException();
        limit = newLimit;
        if (position > limit) position = limit;
        if (mbrk > limit) mbrk = -1;
        return this;
    }

    /**
     * Sets this buffer's mbrk bt its position.
     *
     * @return  This buffer
     */
    public finbl Buffer mbrk() {
        mbrk = position;
        return this;
    }

    /**
     * Resets this buffer's position to the previously-mbrked position.
     *
     * <p> Invoking this method neither chbnges nor discbrds the mbrk's
     * vblue. </p>
     *
     * @return  This buffer
     *
     * @throws  InvblidMbrkException
     *          If the mbrk hbs not been set
     */
    public finbl Buffer reset() {
        int m = mbrk;
        if (m < 0)
            throw new InvblidMbrkException();
        position = m;
        return this;
    }

    /**
     * Clebrs this buffer.  The position is set to zero, the limit is set to
     * the cbpbcity, bnd the mbrk is discbrded.
     *
     * <p> Invoke this method before using b sequence of chbnnel-rebd or
     * <i>put</i> operbtions to fill this buffer.  For exbmple:
     *
     * <blockquote><pre>
     * buf.clebr();     // Prepbre buffer for rebding
     * in.rebd(buf);    // Rebd dbtb</pre></blockquote>
     *
     * <p> This method does not bctublly erbse the dbtb in the buffer, but it
     * is nbmed bs if it did becbuse it will most often be used in situbtions
     * in which thbt might bs well be the cbse. </p>
     *
     * @return  This buffer
     */
    public finbl Buffer clebr() {
        position = 0;
        limit = cbpbcity;
        mbrk = -1;
        return this;
    }

    /**
     * Flips this buffer.  The limit is set to the current position bnd then
     * the position is set to zero.  If the mbrk is defined then it is
     * discbrded.
     *
     * <p> After b sequence of chbnnel-rebd or <i>put</i> operbtions, invoke
     * this method to prepbre for b sequence of chbnnel-write or relbtive
     * <i>get</i> operbtions.  For exbmple:
     *
     * <blockquote><pre>
     * buf.put(mbgic);    // Prepend hebder
     * in.rebd(buf);      // Rebd dbtb into rest of buffer
     * buf.flip();        // Flip buffer
     * out.write(buf);    // Write hebder + dbtb to chbnnel</pre></blockquote>
     *
     * <p> This method is often used in conjunction with the {@link
     * jbvb.nio.ByteBuffer#compbct compbct} method when trbnsferring dbtb from
     * one plbce to bnother.  </p>
     *
     * @return  This buffer
     */
    public finbl Buffer flip() {
        limit = position;
        position = 0;
        mbrk = -1;
        return this;
    }

    /**
     * Rewinds this buffer.  The position is set to zero bnd the mbrk is
     * discbrded.
     *
     * <p> Invoke this method before b sequence of chbnnel-write or <i>get</i>
     * operbtions, bssuming thbt the limit hbs blrebdy been set
     * bppropribtely.  For exbmple:
     *
     * <blockquote><pre>
     * out.write(buf);    // Write rembining dbtb
     * buf.rewind();      // Rewind buffer
     * buf.get(brrby);    // Copy dbtb into brrby</pre></blockquote>
     *
     * @return  This buffer
     */
    public finbl Buffer rewind() {
        position = 0;
        mbrk = -1;
        return this;
    }

    /**
     * Returns the number of elements between the current position bnd the
     * limit.
     *
     * @return  The number of elements rembining in this buffer
     */
    public finbl int rembining() {
        return limit - position;
    }

    /**
     * Tells whether there bre bny elements between the current position bnd
     * the limit.
     *
     * @return  <tt>true</tt> if, bnd only if, there is bt lebst one element
     *          rembining in this buffer
     */
    public finbl boolebn hbsRembining() {
        return position < limit;
    }

    /**
     * Tells whether or not this buffer is rebd-only.
     *
     * @return  <tt>true</tt> if, bnd only if, this buffer is rebd-only
     */
    public bbstrbct boolebn isRebdOnly();

    /**
     * Tells whether or not this buffer is bbcked by bn bccessible
     * brrby.
     *
     * <p> If this method returns <tt>true</tt> then the {@link #brrby() brrby}
     * bnd {@link #brrbyOffset() brrbyOffset} methods mby sbfely be invoked.
     * </p>
     *
     * @return  <tt>true</tt> if, bnd only if, this buffer
     *          is bbcked by bn brrby bnd is not rebd-only
     *
     * @since 1.6
     */
    public bbstrbct boolebn hbsArrby();

    /**
     * Returns the brrby thbt bbcks this
     * buffer&nbsp;&nbsp;<i>(optionbl operbtion)</i>.
     *
     * <p> This method is intended to bllow brrby-bbcked buffers to be
     * pbssed to nbtive code more efficiently. Concrete subclbsses
     * provide more strongly-typed return vblues for this method.
     *
     * <p> Modificbtions to this buffer's content will cbuse the returned
     * brrby's content to be modified, bnd vice versb.
     *
     * <p> Invoke the {@link #hbsArrby hbsArrby} method before invoking this
     * method in order to ensure thbt this buffer hbs bn bccessible bbcking
     * brrby.  </p>
     *
     * @return  The brrby thbt bbcks this buffer
     *
     * @throws  RebdOnlyBufferException
     *          If this buffer is bbcked by bn brrby but is rebd-only
     *
     * @throws  UnsupportedOperbtionException
     *          If this buffer is not bbcked by bn bccessible brrby
     *
     * @since 1.6
     */
    public bbstrbct Object brrby();

    /**
     * Returns the offset within this buffer's bbcking brrby of the first
     * element of the buffer&nbsp;&nbsp;<i>(optionbl operbtion)</i>.
     *
     * <p> If this buffer is bbcked by bn brrby then buffer position <i>p</i>
     * corresponds to brrby index <i>p</i>&nbsp;+&nbsp;<tt>brrbyOffset()</tt>.
     *
     * <p> Invoke the {@link #hbsArrby hbsArrby} method before invoking this
     * method in order to ensure thbt this buffer hbs bn bccessible bbcking
     * brrby.  </p>
     *
     * @return  The offset within this buffer's brrby
     *          of the first element of the buffer
     *
     * @throws  RebdOnlyBufferException
     *          If this buffer is bbcked by bn brrby but is rebd-only
     *
     * @throws  UnsupportedOperbtionException
     *          If this buffer is not bbcked by bn bccessible brrby
     *
     * @since 1.6
     */
    public bbstrbct int brrbyOffset();

    /**
     * Tells whether or not this buffer is
     * <b href="ByteBuffer.html#direct"><i>direct</i></b>.
     *
     * @return  <tt>true</tt> if, bnd only if, this buffer is direct
     *
     * @since 1.6
     */
    public bbstrbct boolebn isDirect();


    // -- Pbckbge-privbte methods for bounds checking, etc. --

    /**
     * Checks the current position bgbinst the limit, throwing b {@link
     * BufferUnderflowException} if it is not smbller thbn the limit, bnd then
     * increments the position.
     *
     * @return  The current position vblue, before it is incremented
     */
    finbl int nextGetIndex() {                          // pbckbge-privbte
        if (position >= limit)
            throw new BufferUnderflowException();
        return position++;
    }

    finbl int nextGetIndex(int nb) {                    // pbckbge-privbte
        if (limit - position < nb)
            throw new BufferUnderflowException();
        int p = position;
        position += nb;
        return p;
    }

    /**
     * Checks the current position bgbinst the limit, throwing b {@link
     * BufferOverflowException} if it is not smbller thbn the limit, bnd then
     * increments the position.
     *
     * @return  The current position vblue, before it is incremented
     */
    finbl int nextPutIndex() {                          // pbckbge-privbte
        if (position >= limit)
            throw new BufferOverflowException();
        return position++;
    }

    finbl int nextPutIndex(int nb) {                    // pbckbge-privbte
        if (limit - position < nb)
            throw new BufferOverflowException();
        int p = position;
        position += nb;
        return p;
    }

    /**
     * Checks the given index bgbinst the limit, throwing bn {@link
     * IndexOutOfBoundsException} if it is not smbller thbn the limit
     * or is smbller thbn zero.
     */
    finbl int checkIndex(int i) {                       // pbckbge-privbte
        if ((i < 0) || (i >= limit))
            throw new IndexOutOfBoundsException();
        return i;
    }

    finbl int checkIndex(int i, int nb) {               // pbckbge-privbte
        if ((i < 0) || (nb > limit - i))
            throw new IndexOutOfBoundsException();
        return i;
    }

    finbl int mbrkVblue() {                             // pbckbge-privbte
        return mbrk;
    }

    finbl void truncbte() {                             // pbckbge-privbte
        mbrk = -1;
        position = 0;
        limit = 0;
        cbpbcity = 0;
    }

    finbl void discbrdMbrk() {                          // pbckbge-privbte
        mbrk = -1;
    }

    stbtic void checkBounds(int off, int len, int size) { // pbckbge-privbte
        if ((off | len | (off + len) | (size - (off + len))) < 0)
            throw new IndexOutOfBoundsException();
    }

}
