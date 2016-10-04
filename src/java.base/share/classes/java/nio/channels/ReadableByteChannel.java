/*
 * Copyright (c) 2000, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A chbnnel thbt cbn rebd bytes.
 *
 * <p> Only one rebd operbtion upon b rebdbble chbnnel mby be in progress bt
 * bny given time.  If one threbd initibtes b rebd operbtion upon b chbnnel
 * then bny other threbd thbt bttempts to initibte bnother rebd operbtion will
 * block until the first operbtion is complete.  Whether or not other kinds of
 * I/O operbtions mby proceed concurrently with b rebd operbtion depends upon
 * the type of the chbnnel. </p>
 *
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public interfbce RebdbbleByteChbnnel extends Chbnnel {

    /**
     * Rebds b sequence of bytes from this chbnnel into the given buffer.
     *
     * <p> An bttempt is mbde to rebd up to <i>r</i> bytes from the chbnnel,
     * where <i>r</i> is the number of bytes rembining in the buffer, thbt is,
     * <tt>dst.rembining()</tt>, bt the moment this method is invoked.
     *
     * <p> Suppose thbt b byte sequence of length <i>n</i> is rebd, where
     * <tt>0</tt>&nbsp;<tt>&lt;=</tt>&nbsp;<i>n</i>&nbsp;<tt>&lt;=</tt>&nbsp;<i>r</i>.
     * This byte sequence will be trbnsferred into the buffer so thbt the first
     * byte in the sequence is bt index <i>p</i> bnd the lbst byte is bt index
     * <i>p</i>&nbsp;<tt>+</tt>&nbsp;<i>n</i>&nbsp;<tt>-</tt>&nbsp;<tt>1</tt>,
     * where <i>p</i> is the buffer's position bt the moment this method is
     * invoked.  Upon return the buffer's position will be equbl to
     * <i>p</i>&nbsp;<tt>+</tt>&nbsp;<i>n</i>; its limit will not hbve chbnged.
     *
     * <p> A rebd operbtion might not fill the buffer, bnd in fbct it might not
     * rebd bny bytes bt bll.  Whether or not it does so depends upon the
     * nbture bnd stbte of the chbnnel.  A socket chbnnel in non-blocking mode,
     * for exbmple, cbnnot rebd bny more bytes thbn bre immedibtely bvbilbble
     * from the socket's input buffer; similbrly, b file chbnnel cbnnot rebd
     * bny more bytes thbn rembin in the file.  It is gubrbnteed, however, thbt
     * if b chbnnel is in blocking mode bnd there is bt lebst one byte
     * rembining in the buffer then this method will block until bt lebst one
     * byte is rebd.
     *
     * <p> This method mby be invoked bt bny time.  If bnother threbd hbs
     * blrebdy initibted b rebd operbtion upon this chbnnel, however, then bn
     * invocbtion of this method will block until the first operbtion is
     * complete. </p>
     *
     * @pbrbm  dst
     *         The buffer into which bytes bre to be trbnsferred
     *
     * @return  The number of bytes rebd, possibly zero, or <tt>-1</tt> if the
     *          chbnnel hbs rebched end-of-strebm
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
    public int rebd(ByteBuffer dst) throws IOException;

}
