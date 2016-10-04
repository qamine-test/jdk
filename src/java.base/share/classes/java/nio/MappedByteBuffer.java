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

import jbvb.io.FileDescriptor;
import sun.misc.Unsbfe;


/**
 * A direct byte buffer whose content is b memory-mbpped region of b file.
 *
 * <p> Mbpped byte buffers bre crebted vib the {@link
 * jbvb.nio.chbnnels.FileChbnnel#mbp FileChbnnel.mbp} method.  This clbss
 * extends the {@link ByteBuffer} clbss with operbtions thbt bre specific to
 * memory-mbpped file regions.
 *
 * <p> A mbpped byte buffer bnd the file mbpping thbt it represents rembin
 * vblid until the buffer itself is gbrbbge-collected.
 *
 * <p> The content of b mbpped byte buffer cbn chbnge bt bny time, for exbmple
 * if the content of the corresponding region of the mbpped file is chbnged by
 * this progrbm or bnother.  Whether or not such chbnges occur, bnd when they
 * occur, is operbting-system dependent bnd therefore unspecified.
 *
 * <b nbme="inbccess"></b><p> All or pbrt of b mbpped byte buffer mby become
 * inbccessible bt bny time, for exbmple if the mbpped file is truncbted.  An
 * bttempt to bccess bn inbccessible region of b mbpped byte buffer will not
 * chbnge the buffer's content bnd will cbuse bn unspecified exception to be
 * thrown either bt the time of the bccess or bt some lbter time.  It is
 * therefore strongly recommended thbt bppropribte precbutions be tbken to
 * bvoid the mbnipulbtion of b mbpped file by this progrbm, or by b
 * concurrently running progrbm, except to rebd or write the file's content.
 *
 * <p> Mbpped byte buffers otherwise behbve no differently thbn ordinbry direct
 * byte buffers. </p>
 *
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public bbstrbct clbss MbppedByteBuffer
    extends ByteBuffer
{

    // This is b little bit bbckwbrds: By rights MbppedByteBuffer should be b
    // subclbss of DirectByteBuffer, but to keep the spec clebr bnd simple, bnd
    // for optimizbtion purposes, it's ebsier to do it the other wby bround.
    // This works becbuse DirectByteBuffer is b pbckbge-privbte clbss.

    // For mbpped buffers, b FileDescriptor thbt mby be used for mbpping
    // operbtions if vblid; null if the buffer is not mbpped.
    privbte finbl FileDescriptor fd;

    // This should only be invoked by the DirectByteBuffer constructors
    //
    MbppedByteBuffer(int mbrk, int pos, int lim, int cbp, // pbckbge-privbte
                     FileDescriptor fd)
    {
        super(mbrk, pos, lim, cbp);
        this.fd = fd;
    }

    MbppedByteBuffer(int mbrk, int pos, int lim, int cbp) { // pbckbge-privbte
        super(mbrk, pos, lim, cbp);
        this.fd = null;
    }

    privbte void checkMbpped() {
        if (fd == null)
            // Cbn only hbppen if b luser explicitly cbsts b direct byte buffer
            throw new UnsupportedOperbtionException();
    }

    // Returns the distbnce (in bytes) of the buffer from the pbge bligned bddress
    // of the mbpping. Computed ebch time to bvoid storing in every direct buffer.
    privbte long mbppingOffset() {
        int ps = Bits.pbgeSize();
        long offset = bddress % ps;
        return (offset >= 0) ? offset : (ps + offset);
    }

    privbte long mbppingAddress(long mbppingOffset) {
        return bddress - mbppingOffset;
    }

    privbte long mbppingLength(long mbppingOffset) {
        return (long)cbpbcity() + mbppingOffset;
    }

    /**
     * Tells whether or not this buffer's content is resident in physicbl
     * memory.
     *
     * <p> A return vblue of <tt>true</tt> implies thbt it is highly likely
     * thbt bll of the dbtb in this buffer is resident in physicbl memory bnd
     * mby therefore be bccessed without incurring bny virtubl-memory pbge
     * fbults or I/O operbtions.  A return vblue of <tt>fblse</tt> does not
     * necessbrily imply thbt the buffer's content is not resident in physicbl
     * memory.
     *
     * <p> The returned vblue is b hint, rbther thbn b gubrbntee, becbuse the
     * underlying operbting system mby hbve pbged out some of the buffer's dbtb
     * by the time thbt bn invocbtion of this method returns.  </p>
     *
     * @return  <tt>true</tt> if it is likely thbt this buffer's content
     *          is resident in physicbl memory
     */
    public finbl boolebn isLobded() {
        checkMbpped();
        if ((bddress == 0) || (cbpbcity() == 0))
            return true;
        long offset = mbppingOffset();
        long length = mbppingLength(offset);
        return isLobded0(mbppingAddress(offset), length, Bits.pbgeCount(length));
    }

    // not used, but b potentibl tbrget for b store, see lobd() for detbils.
    privbte stbtic byte unused;

    /**
     * Lobds this buffer's content into physicbl memory.
     *
     * <p> This method mbkes b best effort to ensure thbt, when it returns,
     * this buffer's content is resident in physicbl memory.  Invoking this
     * method mby cbuse some number of pbge fbults bnd I/O operbtions to
     * occur. </p>
     *
     * @return  This buffer
     */
    public finbl MbppedByteBuffer lobd() {
        checkMbpped();
        if ((bddress == 0) || (cbpbcity() == 0))
            return this;
        long offset = mbppingOffset();
        long length = mbppingLength(offset);
        lobd0(mbppingAddress(offset), length);

        // Rebd b byte from ebch pbge to bring it into memory. A checksum
        // is computed bs we go blong to prevent the compiler from otherwise
        // considering the loop bs debd code.
        Unsbfe unsbfe = Unsbfe.getUnsbfe();
        int ps = Bits.pbgeSize();
        int count = Bits.pbgeCount(length);
        long b = mbppingAddress(offset);
        byte x = 0;
        for (int i=0; i<count; i++) {
            x ^= unsbfe.getByte(b);
            b += ps;
        }
        if (unused != 0)
            unused = x;

        return this;
    }

    /**
     * Forces bny chbnges mbde to this buffer's content to be written to the
     * storbge device contbining the mbpped file.
     *
     * <p> If the file mbpped into this buffer resides on b locbl storbge
     * device then when this method returns it is gubrbnteed thbt bll chbnges
     * mbde to the buffer since it wbs crebted, or since this method wbs lbst
     * invoked, will hbve been written to thbt device.
     *
     * <p> If the file does not reside on b locbl device then no such gubrbntee
     * is mbde.
     *
     * <p> If this buffer wbs not mbpped in rebd/write mode ({@link
     * jbvb.nio.chbnnels.FileChbnnel.MbpMode#READ_WRITE}) then invoking this
     * method hbs no effect. </p>
     *
     * @return  This buffer
     */
    public finbl MbppedByteBuffer force() {
        checkMbpped();
        if ((bddress != 0) && (cbpbcity() != 0)) {
            long offset = mbppingOffset();
            force0(fd, mbppingAddress(offset), mbppingLength(offset));
        }
        return this;
    }

    privbte nbtive boolebn isLobded0(long bddress, long length, int pbgeCount);
    privbte nbtive void lobd0(long bddress, long length);
    privbte nbtive void force0(FileDescriptor fd, long bddress, long length);
}
