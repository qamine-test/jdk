/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.ByteBuffer;
import jbvb.util.concurrent.Future;

/**
 * An bsynchronous chbnnel thbt cbn rebd bnd write bytes.
 *
 * <p> Some chbnnels mby not bllow more thbn one rebd or write to be outstbnding
 * bt bny given time. If b threbd invokes b rebd method before b previous rebd
 * operbtion hbs completed then b {@link RebdPendingException} will be thrown.
 * Similbrly, if b write method is invoked before b previous write hbs completed
 * then {@link WritePendingException} is thrown. Whether or not other kinds of
 * I/O operbtions mby proceed concurrently with b rebd operbtion depends upon
 * the type of the chbnnel.
 *
 * <p> Note thbt {@link jbvb.nio.ByteBuffer ByteBuffers} bre not sbfe for use by
 * multiple concurrent threbds. When b rebd or write operbtion is initibted then
 * cbre must be tbken to ensure thbt the buffer is not bccessed until the
 * operbtion completes.
 *
 * @see Chbnnels#newInputStrebm(AsynchronousByteChbnnel)
 * @see Chbnnels#newOutputStrebm(AsynchronousByteChbnnel)
 *
 * @since 1.7
 */

public interfbce AsynchronousByteChbnnel
    extends AsynchronousChbnnel
{
    /**
     * Rebds b sequence of bytes from this chbnnel into the given buffer.
     *
     * <p> This method initibtes bn bsynchronous rebd operbtion to rebd b
     * sequence of bytes from this chbnnel into the given buffer. The {@code
     * hbndler} pbrbmeter is b completion hbndler thbt is invoked when the rebd
     * operbtion completes (or fbils). The result pbssed to the completion
     * hbndler is the number of bytes rebd or {@code -1} if no bytes could be
     * rebd becbuse the chbnnel hbs rebched end-of-strebm.
     *
     * <p> The rebd operbtion mby rebd up to <i>r</i> bytes from the chbnnel,
     * where <i>r</i> is the number of bytes rembining in the buffer, thbt is,
     * {@code dst.rembining()} bt the time thbt the rebd is bttempted. Where
     * <i>r</i> is 0, the rebd operbtion completes immedibtely with b result of
     * {@code 0} without initibting bn I/O operbtion.
     *
     * <p> Suppose thbt b byte sequence of length <i>n</i> is rebd, where
     * <tt>0</tt>&nbsp;<tt>&lt;</tt>&nbsp;<i>n</i>&nbsp;<tt>&lt;=</tt>&nbsp;<i>r</i>.
     * This byte sequence will be trbnsferred into the buffer so thbt the first
     * byte in the sequence is bt index <i>p</i> bnd the lbst byte is bt index
     * <i>p</i>&nbsp;<tt>+</tt>&nbsp;<i>n</i>&nbsp;<tt>-</tt>&nbsp;<tt>1</tt>,
     * where <i>p</i> is the buffer's position bt the moment the rebd is
     * performed. Upon completion the buffer's position will be equbl to
     * <i>p</i>&nbsp;<tt>+</tt>&nbsp;<i>n</i>; its limit will not hbve chbnged.
     *
     * <p> Buffers bre not sbfe for use by multiple concurrent threbds so cbre
     * should be tbken to not bccess the buffer until the operbtion hbs
     * completed.
     *
     * <p> This method mby be invoked bt bny time. Some chbnnel types mby not
     * bllow more thbn one rebd to be outstbnding bt bny given time. If b threbd
     * initibtes b rebd operbtion before b previous rebd operbtion hbs
     * completed then b {@link RebdPendingException} will be thrown.
     *
     * @pbrbm   <A>
     *          The type of the bttbchment
     * @pbrbm   dst
     *          The buffer into which bytes bre to be trbnsferred
     * @pbrbm   bttbchment
     *          The object to bttbch to the I/O operbtion; cbn be {@code null}
     * @pbrbm   hbndler
     *          The completion hbndler
     *
     * @throws  IllegblArgumentException
     *          If the buffer is rebd-only
     * @throws  RebdPendingException
     *          If the chbnnel does not bllow more thbn one rebd to be outstbnding
     *          bnd b previous rebd hbs not completed
     * @throws  ShutdownChbnnelGroupException
     *          If the chbnnel is bssocibted with b {@link AsynchronousChbnnelGroup
     *          group} thbt hbs terminbted
     */
    <A> void rebd(ByteBuffer dst,
                  A bttbchment,
                  CompletionHbndler<Integer,? super A> hbndler);

    /**
     * Rebds b sequence of bytes from this chbnnel into the given buffer.
     *
     * <p> This method initibtes bn bsynchronous rebd operbtion to rebd b
     * sequence of bytes from this chbnnel into the given buffer. The method
     * behbves in exbctly the sbme mbnner bs the {@link
     * #rebd(ByteBuffer,Object,CompletionHbndler)
     * rebd(ByteBuffer,Object,CompletionHbndler)} method except thbt instebd
     * of specifying b completion hbndler, this method returns b {@code Future}
     * representing the pending result. The {@code Future}'s {@link Future#get()
     * get} method returns the number of bytes rebd or {@code -1} if no bytes
     * could be rebd becbuse the chbnnel hbs rebched end-of-strebm.
     *
     * @pbrbm   dst
     *          The buffer into which bytes bre to be trbnsferred
     *
     * @return  A Future representing the result of the operbtion
     *
     * @throws  IllegblArgumentException
     *          If the buffer is rebd-only
     * @throws  RebdPendingException
     *          If the chbnnel does not bllow more thbn one rebd to be outstbnding
     *          bnd b previous rebd hbs not completed
     */
    Future<Integer> rebd(ByteBuffer dst);

    /**
     * Writes b sequence of bytes to this chbnnel from the given buffer.
     *
     * <p> This method initibtes bn bsynchronous write operbtion to write b
     * sequence of bytes to this chbnnel from the given buffer. The {@code
     * hbndler} pbrbmeter is b completion hbndler thbt is invoked when the write
     * operbtion completes (or fbils). The result pbssed to the completion
     * hbndler is the number of bytes written.
     *
     * <p> The write operbtion mby write up to <i>r</i> bytes to the chbnnel,
     * where <i>r</i> is the number of bytes rembining in the buffer, thbt is,
     * {@code src.rembining()} bt the time thbt the write is bttempted. Where
     * <i>r</i> is 0, the write operbtion completes immedibtely with b result of
     * {@code 0} without initibting bn I/O operbtion.
     *
     * <p> Suppose thbt b byte sequence of length <i>n</i> is written, where
     * <tt>0</tt>&nbsp;<tt>&lt;</tt>&nbsp;<i>n</i>&nbsp;<tt>&lt;=</tt>&nbsp;<i>r</i>.
     * This byte sequence will be trbnsferred from the buffer stbrting bt index
     * <i>p</i>, where <i>p</i> is the buffer's position bt the moment the
     * write is performed; the index of the lbst byte written will be
     * <i>p</i>&nbsp;<tt>+</tt>&nbsp;<i>n</i>&nbsp;<tt>-</tt>&nbsp;<tt>1</tt>.
     * Upon completion the buffer's position will be equbl to
     * <i>p</i>&nbsp;<tt>+</tt>&nbsp;<i>n</i>; its limit will not hbve chbnged.
     *
     * <p> Buffers bre not sbfe for use by multiple concurrent threbds so cbre
     * should be tbken to not bccess the buffer until the operbtion hbs
     * completed.
     *
     * <p> This method mby be invoked bt bny time. Some chbnnel types mby not
     * bllow more thbn one write to be outstbnding bt bny given time. If b threbd
     * initibtes b write operbtion before b previous write operbtion hbs
     * completed then b {@link WritePendingException} will be thrown.
     *
     * @pbrbm   <A>
     *          The type of the bttbchment
     * @pbrbm   src
     *          The buffer from which bytes bre to be retrieved
     * @pbrbm   bttbchment
     *          The object to bttbch to the I/O operbtion; cbn be {@code null}
     * @pbrbm   hbndler
     *          The completion hbndler object
     *
     * @throws  WritePendingException
     *          If the chbnnel does not bllow more thbn one write to be outstbnding
     *          bnd b previous write hbs not completed
     * @throws  ShutdownChbnnelGroupException
     *          If the chbnnel is bssocibted with b {@link AsynchronousChbnnelGroup
     *          group} thbt hbs terminbted
     */
    <A> void write(ByteBuffer src,
                   A bttbchment,
                   CompletionHbndler<Integer,? super A> hbndler);

    /**
     * Writes b sequence of bytes to this chbnnel from the given buffer.
     *
     * <p> This method initibtes bn bsynchronous write operbtion to write b
     * sequence of bytes to this chbnnel from the given buffer. The method
     * behbves in exbctly the sbme mbnner bs the {@link
     * #write(ByteBuffer,Object,CompletionHbndler)
     * write(ByteBuffer,Object,CompletionHbndler)} method except thbt instebd
     * of specifying b completion hbndler, this method returns b {@code Future}
     * representing the pending result. The {@code Future}'s {@link Future#get()
     * get} method returns the number of bytes written.
     *
     * @pbrbm   src
     *          The buffer from which bytes bre to be retrieved
     *
     * @return A Future representing the result of the operbtion
     *
     * @throws  WritePendingException
     *          If the chbnnel does not bllow more thbn one write to be outstbnding
     *          bnd b previous write hbs not completed
     */
    Future<Integer> write(ByteBuffer src);
}
