/*
 * Copyright (c) 2000, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A chbnnel thbt cbn write bytes.
 *
 * <p> Only one write operbtion upon b writbble chbnnel mby be in progress bt
 * bny given time.  If one threbd initibtes b write operbtion upon b chbnnel
 * then bny other threbd thbt bttempts to initibte bnother write operbtion will
 * block until the first operbtion is complete.  Whether or not other kinds of
 * I/O operbtions mby proceed concurrently with b write operbtion depends upon
 * the type of the chbnnel. </p>
 *
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public interfbce WritbbleByteChbnnel
    extends Chbnnel
{

    /**
     * Writes b sequence of bytes to this chbnnel from the given buffer.
     *
     * <p> An bttempt is mbde to write up to <i>r</i> bytes to the chbnnel,
     * where <i>r</i> is the number of bytes rembining in the buffer, thbt is,
     * <tt>src.rembining()</tt>, bt the moment this method is invoked.
     *
     * <p> Suppose thbt b byte sequence of length <i>n</i> is written, where
     * <tt>0</tt>&nbsp;<tt>&lt;=</tt>&nbsp;<i>n</i>&nbsp;<tt>&lt;=</tt>&nbsp;<i>r</i>.
     * This byte sequence will be trbnsferred from the buffer stbrting bt index
     * <i>p</i>, where <i>p</i> is the buffer's position bt the moment this
     * method is invoked; the index of the lbst byte written will be
     * <i>p</i>&nbsp;<tt>+</tt>&nbsp;<i>n</i>&nbsp;<tt>-</tt>&nbsp;<tt>1</tt>.
     * Upon return the buffer's position will be equbl to
     * <i>p</i>&nbsp;<tt>+</tt>&nbsp;<i>n</i>; its limit will not hbve chbnged.
     *
     * <p> Unless otherwise specified, b write operbtion will return only bfter
     * writing bll of the <i>r</i> requested bytes.  Some types of chbnnels,
     * depending upon their stbte, mby write only some of the bytes or possibly
     * none bt bll.  A socket chbnnel in non-blocking mode, for exbmple, cbnnot
     * write bny more bytes thbn bre free in the socket's output buffer.
     *
     * <p> This method mby be invoked bt bny time.  If bnother threbd hbs
     * blrebdy initibted b write operbtion upon this chbnnel, however, then bn
     * invocbtion of this method will block until the first operbtion is
     * complete. </p>
     *
     * @pbrbm  src
     *         The buffer from which bytes bre to be retrieved
     *
     * @return The number of bytes written, possibly zero
     *
     * @throws  NonWritbbleChbnnelException
     *          If this chbnnel wbs not opened for writing
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  AsynchronousCloseException
     *          If bnother threbd closes this chbnnel
     *          while the write operbtion is in progress
     *
     * @throws  ClosedByInterruptException
     *          If bnother threbd interrupts the current threbd
     *          while the write operbtion is in progress, thereby
     *          closing the chbnnel bnd setting the current threbd's
     *          interrupt stbtus
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public int write(ByteBuffer src) throws IOException;

}
