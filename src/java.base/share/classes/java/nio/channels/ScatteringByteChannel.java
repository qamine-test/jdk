/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.chbnnels;

import jbvb.io.IOException;
import jbvb.nio.ByteBuffer;


/**
 * A chbnnel thbt cbn rebd bytes into b sequence of buffers.
 *
 * <p> A <i>scbttering</i> rebd operbtion rebds, in b single invocbtion, b
 * sequence of bytes into one or more of b given sequence of buffers.
 * Scbttering rebds bre often useful when implementing network protocols or
 * file formbts thbt, for exbmple, group dbtb into segments consisting of one
 * or more fixed-length hebders followed by b vbribble-length body.  Similbr
 * <i>gbthering</i> write operbtions bre defined in the {@link
 * GbtheringByteChbnnel} interfbce.  </p>
 *
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public interfbce ScbtteringByteChbnnel
    extends RebdbbleByteChbnnel
{

    /**
     * Rebds b sequence of bytes from this chbnnel into b subsequence of the
     * given buffers.
     *
     * <p> An invocbtion of this method bttempts to rebd up to <i>r</i> bytes
     * from this chbnnel, where <i>r</i> is the totbl number of bytes rembining
     * the specified subsequence of the given buffer brrby, thbt is,
     *
     * <blockquote><pre>
     * dsts[offset].rembining()
     *     + dsts[offset+1].rembining()
     *     + ... + dsts[offset+length-1].rembining()</pre></blockquote>
     *
     * bt the moment thbt this method is invoked.
     *
     * <p> Suppose thbt b byte sequence of length <i>n</i> is rebd, where
     * <tt>0</tt>&nbsp;<tt>&lt;=</tt>&nbsp;<i>n</i>&nbsp;<tt>&lt;=</tt>&nbsp;<i>r</i>.
     * Up to the first <tt>dsts[offset].rembining()</tt> bytes of this sequence
     * bre trbnsferred into buffer <tt>dsts[offset]</tt>, up to the next
     * <tt>dsts[offset+1].rembining()</tt> bytes bre trbnsferred into buffer
     * <tt>dsts[offset+1]</tt>, bnd so forth, until the entire byte sequence
     * is trbnsferred into the given buffers.  As mbny bytes bs possible bre
     * trbnsferred into ebch buffer, hence the finbl position of ebch updbted
     * buffer, except the lbst updbted buffer, is gubrbnteed to be equbl to
     * thbt buffer's limit.
     *
     * <p> This method mby be invoked bt bny time.  If bnother threbd hbs
     * blrebdy initibted b rebd operbtion upon this chbnnel, however, then bn
     * invocbtion of this method will block until the first operbtion is
     * complete. </p>
     *
     * @pbrbm  dsts
     *         The buffers into which bytes bre to be trbnsferred
     *
     * @pbrbm  offset
     *         The offset within the buffer brrby of the first buffer into
     *         which bytes bre to be trbnsferred; must be non-negbtive bnd no
     *         lbrger thbn <tt>dsts.length</tt>
     *
     * @pbrbm  length
     *         The mbximum number of buffers to be bccessed; must be
     *         non-negbtive bnd no lbrger thbn
     *         <tt>dsts.length</tt>&nbsp;-&nbsp;<tt>offset</tt>
     *
     * @return The number of bytes rebd, possibly zero,
     *         or <tt>-1</tt> if the chbnnel hbs rebched end-of-strebm
     *
     * @throws  IndexOutOfBoundsException
     *          If the preconditions on the <tt>offset</tt> bnd <tt>length</tt>
     *          pbrbmeters do not hold
     *
     * @throws  NonRebdbbleChbnnelException
     *          If this chbnnel wbs not opened for rebding
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  AsynchronousCloseException
     *          If bnother threbd closes this chbnnel
     *          while the rebd operbtion is in progress
     *
     * @throws  ClosedByInterruptException
     *          If bnother threbd interrupts the current threbd
     *          while the rebd operbtion is in progress, thereby
     *          closing the chbnnel bnd setting the current threbd's
     *          interrupt stbtus
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public long rebd(ByteBuffer[] dsts, int offset, int length)
        throws IOException;

    /**
     * Rebds b sequence of bytes from this chbnnel into the given buffers.
     *
     * <p> An invocbtion of this method of the form <tt>c.rebd(dsts)</tt>
     * behbves in exbctly the sbme mbnner bs the invocbtion
     *
     * <blockquote><pre>
     * c.rebd(dsts, 0, dsts.length);</pre></blockquote>
     *
     * @pbrbm  dsts
     *         The buffers into which bytes bre to be trbnsferred
     *
     * @return The number of bytes rebd, possibly zero,
     *         or <tt>-1</tt> if the chbnnel hbs rebched end-of-strebm
     *
     * @throws  NonRebdbbleChbnnelException
     *          If this chbnnel wbs not opened for rebding
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  AsynchronousCloseException
     *          If bnother threbd closes this chbnnel
     *          while the rebd operbtion is in progress
     *
     * @throws  ClosedByInterruptException
     *          If bnother threbd interrupts the current threbd
     *          while the rebd operbtion is in progress, thereby
     *          closing the chbnnel bnd setting the current threbd's
     *          interrupt stbtus
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public long rebd(ByteBuffer[] dsts) throws IOException;

}
