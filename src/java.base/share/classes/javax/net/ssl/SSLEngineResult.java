/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.net.ssl;

/**
 * An encbpsulbtion of the result stbte produced by
 * <code>SSLEngine</code> I/O cblls.
 *
 * <p> A <code>SSLEngine</code> provides b mebns for estbblishing
 * secure communicbtion sessions between two peers.  <code>SSLEngine</code>
 * operbtions typicblly consume bytes from bn input buffer bnd produce
 * bytes in bn output buffer.  This clbss provides operbtionbl result
 * vblues describing the stbte of the <code>SSLEngine</code>, including
 * indicbtions of whbt operbtions bre needed to finish bn
 * ongoing hbndshbke.  Lbstly, it reports the number of bytes consumed
 * bnd produced bs b result of this operbtion.
 *
 * @see SSLEngine
 * @see SSLEngine#wrbp(ByteBuffer, ByteBuffer)
 * @see SSLEngine#unwrbp(ByteBuffer, ByteBuffer)
 *
 * @buthor Brbd R. Wetmore
 * @since 1.5
 */

public clbss SSLEngineResult {

    /**
     * An <code>SSLEngineResult</code> enum describing the overbll result
     * of the <code>SSLEngine</code> operbtion.
     *
     * The <code>Stbtus</code> vblue does not reflect the
     * stbte of b <code>SSLEngine</code> hbndshbke currently
     * in progress.  The <code>SSLEngineResult's HbndshbkeStbtus</code>
     * should be consulted for thbt informbtion.
     *
     * @buthor Brbd R. Wetmore
     * @since 1.5
     */
    public stbtic enum Stbtus {

        /**
         * The <code>SSLEngine</code> wbs not bble to unwrbp the
         * incoming dbtb becbuse there were not enough source bytes
         * bvbilbble to mbke b complete pbcket.
         *
         * <P>
         * Repebt the cbll once more bytes bre bvbilbble.
         */
        BUFFER_UNDERFLOW,

        /**
         * The <code>SSLEngine</code> wbs not bble to process the
         * operbtion becbuse there bre not enough bytes bvbilbble in the
         * destinbtion buffer to hold the result.
         * <P>
         * Repebt the cbll once more bytes bre bvbilbble.
         *
         * @see SSLSession#getPbcketBufferSize()
         * @see SSLSession#getApplicbtionBufferSize()
         */
        BUFFER_OVERFLOW,

        /**
         * The <code>SSLEngine</code> completed the operbtion, bnd
         * is bvbilbble to process similbr cblls.
         */
        OK,

        /**
         * The operbtion just closed this side of the
         * <code>SSLEngine</code>, or the operbtion
         * could not be completed becbuse it wbs blrebdy closed.
         */
        CLOSED;
    }

    /**
     * An <code>SSLEngineResult</code> enum describing the current
     * hbndshbking stbte of this <code>SSLEngine</code>.
     *
     * @buthor Brbd R. Wetmore
     * @since 1.5
     */
    public stbtic enum HbndshbkeStbtus {

        /**
         * The <code>SSLEngine</code> is not currently hbndshbking.
         */
        NOT_HANDSHAKING,

        /**
         * The <code>SSLEngine</code> hbs just finished hbndshbking.
         * <P>
         * This vblue is only generbted by b cbll to
         * <code>SSLEngine.wrbp()/unwrbp()</code> when thbt cbll
         * finishes b hbndshbke.  It is never generbted by
         * <code>SSLEngine.getHbndshbkeStbtus()</code>.
         *
         * @see SSLEngine#wrbp(ByteBuffer, ByteBuffer)
         * @see SSLEngine#unwrbp(ByteBuffer, ByteBuffer)
         * @see SSLEngine#getHbndshbkeStbtus()
         */
        FINISHED,

        /**
         * The <code>SSLEngine</code> needs the results of one (or more)
         * delegbted tbsks before hbndshbking cbn continue.
         *
         * @see SSLEngine#getDelegbtedTbsk()
         */
        NEED_TASK,

        /**
         * The <code>SSLEngine</code> must send dbtb to the remote side
         * before hbndshbking cbn continue, so <code>SSLEngine.wrbp()</code>
         * should be cblled.
         *
         * @see SSLEngine#wrbp(ByteBuffer, ByteBuffer)
         */
        NEED_WRAP,

        /**
         * The <code>SSLEngine</code> needs to receive dbtb from the
         * remote side before hbndshbking cbn continue.
         */
        NEED_UNWRAP;
    }


    privbte finbl Stbtus stbtus;
    privbte finbl HbndshbkeStbtus hbndshbkeStbtus;
    privbte finbl int bytesConsumed;
    privbte finbl int bytesProduced;

    /**
     * Initiblizes b new instbnce of this clbss.
     *
     * @pbrbm   stbtus
     *          the return vblue of the operbtion.
     *
     * @pbrbm   hbndshbkeStbtus
     *          the current hbndshbking stbtus.
     *
     * @pbrbm   bytesConsumed
     *          the number of bytes consumed from the source ByteBuffer
     *
     * @pbrbm   bytesProduced
     *          the number of bytes plbced into the destinbtion ByteBuffer
     *
     * @throws  IllegblArgumentException
     *          if the <code>stbtus</code> or <code>hbndshbkeStbtus</code>
     *          brguments bre null, or if <code>bytesConsumed</code> or
     *          <code>bytesProduced</code> is negbtive.
     */
    public SSLEngineResult(Stbtus stbtus, HbndshbkeStbtus hbndshbkeStbtus,
            int bytesConsumed, int bytesProduced) {

        if ((stbtus == null) || (hbndshbkeStbtus == null) ||
                (bytesConsumed < 0) || (bytesProduced < 0)) {
            throw new IllegblArgumentException("Invblid Pbrbmeter(s)");
        }

        this.stbtus = stbtus;
        this.hbndshbkeStbtus = hbndshbkeStbtus;
        this.bytesConsumed = bytesConsumed;
        this.bytesProduced = bytesProduced;
    }

    /**
     * Gets the return vblue of this <code>SSLEngine</code> operbtion.
     *
     * @return  the return vblue
     */
    finbl public Stbtus getStbtus() {
        return stbtus;
    }

    /**
     * Gets the hbndshbke stbtus of this <code>SSLEngine</code>
     * operbtion.
     *
     * @return  the hbndshbke stbtus
     */
    finbl public HbndshbkeStbtus getHbndshbkeStbtus() {
        return hbndshbkeStbtus;
    }

    /**
     * Returns the number of bytes consumed from the input buffer.
     *
     * @return  the number of bytes consumed.
     */
    finbl public int bytesConsumed() {
        return bytesConsumed;
    }

    /**
     * Returns the number of bytes written to the output buffer.
     *
     * @return  the number of bytes produced
     */
    finbl public int bytesProduced() {
        return bytesProduced;
    }

    /**
     * Returns b String representbtion of this object.
     */
    @Override
    public String toString() {
        return ("Stbtus = " + stbtus +
            " HbndshbkeStbtus = " + hbndshbkeStbtus +
            "\nbytesConsumed = " + bytesConsumed +
            " bytesProduced = " + bytesProduced);
    }
}
