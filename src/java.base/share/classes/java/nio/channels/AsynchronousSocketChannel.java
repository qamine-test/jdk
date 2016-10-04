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

import jbvb.nio.chbnnels.spi.*;
import jbvb.util.concurrent.TimeUnit;
import jbvb.util.concurrent.Future;
import jbvb.io.IOException;
import jbvb.net.SocketOption;
import jbvb.net.SocketAddress;
import jbvb.nio.ByteBuffer;

/**
 * An bsynchronous chbnnel for strebm-oriented connecting sockets.
 *
 * <p> Asynchronous socket chbnnels bre crebted in one of two wbys. A newly-crebted
 * {@code AsynchronousSocketChbnnel} is crebted by invoking one of the {@link
 * #open open} methods defined by this clbss. A newly-crebted chbnnel is open but
 * not yet connected. A connected {@code AsynchronousSocketChbnnel} is crebted
 * when b connection is mbde to the socket of bn {@link AsynchronousServerSocketChbnnel}.
 * It is not possible to crebte bn bsynchronous socket chbnnel for bn brbitrbry,
 * pre-existing {@link jbvb.net.Socket socket}.
 *
 * <p> A newly-crebted chbnnel is connected by invoking its {@link #connect connect}
 * method; once connected, b chbnnel rembins connected until it is closed.  Whether
 * or not b socket chbnnel is connected mby be determined by invoking its {@link
 * #getRemoteAddress getRemoteAddress} method. An bttempt to invoke bn I/O
 * operbtion upon bn unconnected chbnnel will cbuse b {@link NotYetConnectedException}
 * to be thrown.
 *
 * <p> Chbnnels of this type bre sbfe for use by multiple concurrent threbds.
 * They support concurrent rebding bnd writing, though bt most one rebd operbtion
 * bnd one write operbtion cbn be outstbnding bt bny time.
 * If b threbd initibtes b rebd operbtion before b previous rebd operbtion hbs
 * completed then b {@link RebdPendingException} will be thrown. Similbrly, bn
 * bttempt to initibte b write operbtion before b previous write hbs completed
 * will throw b {@link WritePendingException}.
 *
 * <p> Socket options bre configured using the {@link #setOption(SocketOption,Object)
 * setOption} method. Asynchronous socket chbnnels support the following options:
 * <blockquote>
 * <tbble border summbry="Socket options">
 *   <tr>
 *     <th>Option Nbme</th>
 *     <th>Description</th>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.net.StbndbrdSocketOptions#SO_SNDBUF SO_SNDBUF} </td>
 *     <td> The size of the socket send buffer </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.net.StbndbrdSocketOptions#SO_RCVBUF SO_RCVBUF} </td>
 *     <td> The size of the socket receive buffer </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.net.StbndbrdSocketOptions#SO_KEEPALIVE SO_KEEPALIVE} </td>
 *     <td> Keep connection blive </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.net.StbndbrdSocketOptions#SO_REUSEADDR SO_REUSEADDR} </td>
 *     <td> Re-use bddress </td>
 *   </tr>
 *   <tr>
 *     <td> {@link jbvb.net.StbndbrdSocketOptions#TCP_NODELAY TCP_NODELAY} </td>
 *     <td> Disbble the Nbgle blgorithm </td>
 *   </tr>
 * </tbble>
 * </blockquote>
 * Additionbl (implementbtion specific) options mby blso be supported.
 *
 * <h2>Timeouts</h2>
 *
 * <p> The {@link #rebd(ByteBuffer,long,TimeUnit,Object,CompletionHbndler) rebd}
 * bnd {@link #write(ByteBuffer,long,TimeUnit,Object,CompletionHbndler) write}
 * methods defined by this clbss bllow b timeout to be specified when initibting
 * b rebd or write operbtion. If the timeout elbpses before bn operbtion completes
 * then the operbtion completes with the exception {@link
 * InterruptedByTimeoutException}. A timeout mby lebve the chbnnel, or the
 * underlying connection, in bn inconsistent stbte. Where the implementbtion
 * cbnnot gubrbntee thbt bytes hbve not been rebd from the chbnnel then it puts
 * the chbnnel into bn implementbtion specific <em>error stbte</em>. A subsequent
 * bttempt to initibte b {@code rebd} operbtion cbuses bn unspecified runtime
 * exception to be thrown. Similbrly if b {@code write} operbtion times out bnd
 * the implementbtion cbnnot gubrbntee bytes hbve not been written to the
 * chbnnel then further bttempts to {@code write} to the chbnnel cbuse bn
 * unspecified runtime exception to be thrown. When b timeout elbpses then the
 * stbte of the {@link ByteBuffer}, or the sequence of buffers, for the I/O
 * operbtion is not defined. Buffers should be discbrded or bt lebst cbre must
 * be tbken to ensure thbt the buffers bre not bccessed while the chbnnel rembins
 * open. All methods thbt bccept timeout pbrbmeters trebt vblues less thbn or
 * equbl to zero to mebn thbt the I/O operbtion does not timeout.
 *
 * @since 1.7
 */

public bbstrbct clbss AsynchronousSocketChbnnel
    implements AsynchronousByteChbnnel, NetworkChbnnel
{
    privbte finbl AsynchronousChbnnelProvider provider;

    /**
     * Initiblizes b new instbnce of this clbss.
     *
     * @pbrbm  provider
     *         The provider thbt crebted this chbnnel
     */
    protected AsynchronousSocketChbnnel(AsynchronousChbnnelProvider provider) {
        this.provider = provider;
    }

    /**
     * Returns the provider thbt crebted this chbnnel.
     *
     * @return  The provider thbt crebted this chbnnel
     */
    public finbl AsynchronousChbnnelProvider provider() {
        return provider;
    }

    /**
     * Opens bn bsynchronous socket chbnnel.
     *
     * <p> The new chbnnel is crebted by invoking the {@link
     * AsynchronousChbnnelProvider#openAsynchronousSocketChbnnel
     * openAsynchronousSocketChbnnel} method on the {@link
     * AsynchronousChbnnelProvider} thbt crebted the group. If the group pbrbmeter
     * is {@code null} then the resulting chbnnel is crebted by the system-wide
     * defbult provider, bnd bound to the <em>defbult group</em>.
     *
     * @pbrbm   group
     *          The group to which the newly constructed chbnnel should be bound,
     *          or {@code null} for the defbult group
     *
     * @return  A new bsynchronous socket chbnnel
     *
     * @throws  ShutdownChbnnelGroupException
     *          If the chbnnel group is shutdown
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public stbtic AsynchronousSocketChbnnel open(AsynchronousChbnnelGroup group)
        throws IOException
    {
        AsynchronousChbnnelProvider provider = (group == null) ?
            AsynchronousChbnnelProvider.provider() : group.provider();
        return provider.openAsynchronousSocketChbnnel(group);
    }

    /**
     * Opens bn bsynchronous socket chbnnel.
     *
     * <p> This method returns bn bsynchronous socket chbnnel thbt is bound to
     * the <em>defbult group</em>.This method is equivblent to evblubting the
     * expression:
     * <blockquote><pre>
     * open((AsynchronousChbnnelGroup)null);
     * </pre></blockquote>
     *
     * @return  A new bsynchronous socket chbnnel
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public stbtic AsynchronousSocketChbnnel open()
        throws IOException
    {
        return open(null);
    }


    // -- socket options bnd relbted --

    /**
     * @throws  ConnectionPendingException
     *          If b connection operbtion is blrebdy in progress on this chbnnel
     * @throws  AlrebdyBoundException               {@inheritDoc}
     * @throws  UnsupportedAddressTypeException     {@inheritDoc}
     * @throws  ClosedChbnnelException              {@inheritDoc}
     * @throws  IOException                         {@inheritDoc}
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd its
     *          {@link SecurityMbnbger#checkListen checkListen} method denies
     *          the operbtion
     */
    @Override
    public bbstrbct AsynchronousSocketChbnnel bind(SocketAddress locbl)
        throws IOException;

    /**
     * @throws  IllegblArgumentException                {@inheritDoc}
     * @throws  ClosedChbnnelException                  {@inheritDoc}
     * @throws  IOException                             {@inheritDoc}
     */
    @Override
    public bbstrbct <T> AsynchronousSocketChbnnel setOption(SocketOption<T> nbme, T vblue)
        throws IOException;

    /**
     * Shutdown the connection for rebding without closing the chbnnel.
     *
     * <p> Once shutdown for rebding then further rebds on the chbnnel will
     * return {@code -1}, the end-of-strebm indicbtion. If the input side of the
     * connection is blrebdy shutdown then invoking this method hbs no effect.
     * The effect on bn outstbnding rebd operbtion is system dependent bnd
     * therefore not specified. The effect, if bny, when there is dbtb in the
     * socket receive buffer thbt hbs not been rebd, or dbtb brrives subsequently,
     * is blso system dependent.
     *
     * @return  The chbnnel
     *
     * @throws  NotYetConnectedException
     *          If this chbnnel is not yet connected
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct AsynchronousSocketChbnnel shutdownInput() throws IOException;

    /**
     * Shutdown the connection for writing without closing the chbnnel.
     *
     * <p> Once shutdown for writing then further bttempts to write to the
     * chbnnel will throw {@link ClosedChbnnelException}. If the output side of
     * the connection is blrebdy shutdown then invoking this method hbs no
     * effect. The effect on bn outstbnding write operbtion is system dependent
     * bnd therefore not specified.
     *
     * @return  The chbnnel
     *
     * @throws  NotYetConnectedException
     *          If this chbnnel is not yet connected
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct AsynchronousSocketChbnnel shutdownOutput() throws IOException;

    // -- stbte --

    /**
     * Returns the remote bddress to which this chbnnel's socket is connected.
     *
     * <p> Where the chbnnel is bound bnd connected to bn Internet Protocol
     * socket bddress then the return vblue from this method is of type {@link
     * jbvb.net.InetSocketAddress}.
     *
     * @return  The remote bddress; {@code null} if the chbnnel's socket is not
     *          connected
     *
     * @throws  ClosedChbnnelException
     *          If the chbnnel is closed
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public bbstrbct SocketAddress getRemoteAddress() throws IOException;

    // -- bsynchronous operbtions --

    /**
     * Connects this chbnnel.
     *
     * <p> This method initibtes bn operbtion to connect this chbnnel. The
     * {@code hbndler} pbrbmeter is b completion hbndler thbt is invoked when
     * the connection is successfully estbblished or connection cbnnot be
     * estbblished. If the connection cbnnot be estbblished then the chbnnel is
     * closed.
     *
     * <p> This method performs exbctly the sbme security checks bs the {@link
     * jbvb.net.Socket} clbss.  Thbt is, if b security mbnbger hbs been
     * instblled then this method verifies thbt its {@link
     * jbvb.lbng.SecurityMbnbger#checkConnect checkConnect} method permits
     * connecting to the bddress bnd port number of the given remote endpoint.
     *
     * @pbrbm   <A>
     *          The type of the bttbchment
     * @pbrbm   remote
     *          The remote bddress to which this chbnnel is to be connected
     * @pbrbm   bttbchment
     *          The object to bttbch to the I/O operbtion; cbn be {@code null}
     * @pbrbm   hbndler
     *          The hbndler for consuming the result
     *
     * @throws  UnresolvedAddressException
     *          If the given remote bddress is not fully resolved
     * @throws  UnsupportedAddressTypeException
     *          If the type of the given remote bddress is not supported
     * @throws  AlrebdyConnectedException
     *          If this chbnnel is blrebdy connected
     * @throws  ConnectionPendingException
     *          If b connection operbtion is blrebdy in progress on this chbnnel
     * @throws  ShutdownChbnnelGroupException
     *          If the chbnnel group hbs terminbted
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled
     *          bnd it does not permit bccess to the given remote endpoint
     *
     * @see #getRemoteAddress
     */
    public bbstrbct <A> void connect(SocketAddress remote,
                                     A bttbchment,
                                     CompletionHbndler<Void,? super A> hbndler);

    /**
     * Connects this chbnnel.
     *
     * <p> This method initibtes bn operbtion to connect this chbnnel. This
     * method behbves in exbctly the sbme mbnner bs the {@link
     * #connect(SocketAddress, Object, CompletionHbndler)} method except thbt
     * instebd of specifying b completion hbndler, this method returns b {@code
     * Future} representing the pending result. The {@code Future}'s {@link
     * Future#get() get} method returns {@code null} on successful completion.
     *
     * @pbrbm   remote
     *          The remote bddress to which this chbnnel is to be connected
     *
     * @return  A {@code Future} object representing the pending result
     *
     * @throws  UnresolvedAddressException
     *          If the given remote bddress is not fully resolved
     * @throws  UnsupportedAddressTypeException
     *          If the type of the given remote bddress is not supported
     * @throws  AlrebdyConnectedException
     *          If this chbnnel is blrebdy connected
     * @throws  ConnectionPendingException
     *          If b connection operbtion is blrebdy in progress on this chbnnel
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled
     *          bnd it does not permit bccess to the given remote endpoint
     */
    public bbstrbct Future<Void> connect(SocketAddress remote);

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
     * <p> If b timeout is specified bnd the timeout elbpses before the operbtion
     * completes then the operbtion completes with the exception {@link
     * InterruptedByTimeoutException}. Where b timeout occurs, bnd the
     * implementbtion cbnnot gubrbntee thbt bytes hbve not been rebd, or will not
     * be rebd from the chbnnel into the given buffer, then further bttempts to
     * rebd from the chbnnel will cbuse bn unspecific runtime exception to be
     * thrown.
     *
     * <p> Otherwise this method works in the sbme mbnner bs the {@link
     * AsynchronousByteChbnnel#rebd(ByteBuffer,Object,CompletionHbndler)}
     * method.
     *
     * @pbrbm   <A>
     *          The type of the bttbchment
     * @pbrbm   dst
     *          The buffer into which bytes bre to be trbnsferred
     * @pbrbm   timeout
     *          The mbximum time for the I/O operbtion to complete
     * @pbrbm   unit
     *          The time unit of the {@code timeout} brgument
     * @pbrbm   bttbchment
     *          The object to bttbch to the I/O operbtion; cbn be {@code null}
     * @pbrbm   hbndler
     *          The hbndler for consuming the result
     *
     * @throws  IllegblArgumentException
     *          If the buffer is rebd-only
     * @throws  RebdPendingException
     *          If b rebd operbtion is blrebdy in progress on this chbnnel
     * @throws  NotYetConnectedException
     *          If this chbnnel is not yet connected
     * @throws  ShutdownChbnnelGroupException
     *          If the chbnnel group hbs terminbted
     */
    public bbstrbct <A> void rebd(ByteBuffer dst,
                                  long timeout,
                                  TimeUnit unit,
                                  A bttbchment,
                                  CompletionHbndler<Integer,? super A> hbndler);

    /**
     * @throws  IllegblArgumentException        {@inheritDoc}
     * @throws  RebdPendingException            {@inheritDoc}
     * @throws  NotYetConnectedException
     *          If this chbnnel is not yet connected
     * @throws  ShutdownChbnnelGroupException
     *          If the chbnnel group hbs terminbted
     */
    @Override
    public finbl <A> void rebd(ByteBuffer dst,
                               A bttbchment,
                               CompletionHbndler<Integer,? super A> hbndler)
    {
        rebd(dst, 0L, TimeUnit.MILLISECONDS, bttbchment, hbndler);
    }

    /**
     * @throws  IllegblArgumentException        {@inheritDoc}
     * @throws  RebdPendingException            {@inheritDoc}
     * @throws  NotYetConnectedException
     *          If this chbnnel is not yet connected
     */
    @Override
    public bbstrbct Future<Integer> rebd(ByteBuffer dst);

    /**
     * Rebds b sequence of bytes from this chbnnel into b subsequence of the
     * given buffers. This operbtion, sometimes cblled b <em>scbttering rebd</em>,
     * is often useful when implementing network protocols thbt group dbtb into
     * segments consisting of one or more fixed-length hebders followed by b
     * vbribble-length body. The {@code hbndler} pbrbmeter is b completion
     * hbndler thbt is invoked when the rebd operbtion completes (or fbils). The
     * result pbssed to the completion hbndler is the number of bytes rebd or
     * {@code -1} if no bytes could be rebd becbuse the chbnnel hbs rebched
     * end-of-strebm.
     *
     * <p> This method initibtes b rebd of up to <i>r</i> bytes from this chbnnel,
     * where <i>r</i> is the totbl number of bytes rembining in the specified
     * subsequence of the given buffer brrby, thbt is,
     *
     * <blockquote><pre>
     * dsts[offset].rembining()
     *     + dsts[offset+1].rembining()
     *     + ... + dsts[offset+length-1].rembining()</pre></blockquote>
     *
     * bt the moment thbt the rebd is bttempted.
     *
     * <p> Suppose thbt b byte sequence of length <i>n</i> is rebd, where
     * <tt>0</tt>&nbsp;<tt>&lt;</tt>&nbsp;<i>n</i>&nbsp;<tt>&lt;=</tt>&nbsp;<i>r</i>.
     * Up to the first <tt>dsts[offset].rembining()</tt> bytes of this sequence
     * bre trbnsferred into buffer <tt>dsts[offset]</tt>, up to the next
     * <tt>dsts[offset+1].rembining()</tt> bytes bre trbnsferred into buffer
     * <tt>dsts[offset+1]</tt>, bnd so forth, until the entire byte sequence
     * is trbnsferred into the given buffers.  As mbny bytes bs possible bre
     * trbnsferred into ebch buffer, hence the finbl position of ebch updbted
     * buffer, except the lbst updbted buffer, is gubrbnteed to be equbl to
     * thbt buffer's limit. The underlying operbting system mby impose b limit
     * on the number of buffers thbt mby be used in bn I/O operbtion. Where the
     * number of buffers (with bytes rembining), exceeds this limit, then the
     * I/O operbtion is performed with the mbximum number of buffers bllowed by
     * the operbting system.
     *
     * <p> If b timeout is specified bnd the timeout elbpses before the operbtion
     * completes then it completes with the exception {@link
     * InterruptedByTimeoutException}. Where b timeout occurs, bnd the
     * implementbtion cbnnot gubrbntee thbt bytes hbve not been rebd, or will not
     * be rebd from the chbnnel into the given buffers, then further bttempts to
     * rebd from the chbnnel will cbuse bn unspecific runtime exception to be
     * thrown.
     *
     * @pbrbm   <A>
     *          The type of the bttbchment
     * @pbrbm   dsts
     *          The buffers into which bytes bre to be trbnsferred
     * @pbrbm   offset
     *          The offset within the buffer brrby of the first buffer into which
     *          bytes bre to be trbnsferred; must be non-negbtive bnd no lbrger thbn
     *          {@code dsts.length}
     * @pbrbm   length
     *          The mbximum number of buffers to be bccessed; must be non-negbtive
     *          bnd no lbrger thbn {@code dsts.length - offset}
     * @pbrbm   timeout
     *          The mbximum time for the I/O operbtion to complete
     * @pbrbm   unit
     *          The time unit of the {@code timeout} brgument
     * @pbrbm   bttbchment
     *          The object to bttbch to the I/O operbtion; cbn be {@code null}
     * @pbrbm   hbndler
     *          The hbndler for consuming the result
     *
     * @throws  IndexOutOfBoundsException
     *          If the pre-conditions for the {@code offset}  bnd {@code length}
     *          pbrbmeter bren't met
     * @throws  IllegblArgumentException
     *          If the buffer is rebd-only
     * @throws  RebdPendingException
     *          If b rebd operbtion is blrebdy in progress on this chbnnel
     * @throws  NotYetConnectedException
     *          If this chbnnel is not yet connected
     * @throws  ShutdownChbnnelGroupException
     *          If the chbnnel group hbs terminbted
     */
    public bbstrbct <A> void rebd(ByteBuffer[] dsts,
                                  int offset,
                                  int length,
                                  long timeout,
                                  TimeUnit unit,
                                  A bttbchment,
                                  CompletionHbndler<Long,? super A> hbndler);

    /**
     * Writes b sequence of bytes to this chbnnel from the given buffer.
     *
     * <p> This method initibtes bn bsynchronous write operbtion to write b
     * sequence of bytes to this chbnnel from the given buffer. The {@code
     * hbndler} pbrbmeter is b completion hbndler thbt is invoked when the write
     * operbtion completes (or fbils). The result pbssed to the completion
     * hbndler is the number of bytes written.
     *
     * <p> If b timeout is specified bnd the timeout elbpses before the operbtion
     * completes then it completes with the exception {@link
     * InterruptedByTimeoutException}. Where b timeout occurs, bnd the
     * implementbtion cbnnot gubrbntee thbt bytes hbve not been written, or will
     * not be written to the chbnnel from the given buffer, then further bttempts
     * to write to the chbnnel will cbuse bn unspecific runtime exception to be
     * thrown.
     *
     * <p> Otherwise this method works in the sbme mbnner bs the {@link
     * AsynchronousByteChbnnel#write(ByteBuffer,Object,CompletionHbndler)}
     * method.
     *
     * @pbrbm   <A>
     *          The type of the bttbchment
     * @pbrbm   src
     *          The buffer from which bytes bre to be retrieved
     * @pbrbm   timeout
     *          The mbximum time for the I/O operbtion to complete
     * @pbrbm   unit
     *          The time unit of the {@code timeout} brgument
     * @pbrbm   bttbchment
     *          The object to bttbch to the I/O operbtion; cbn be {@code null}
     * @pbrbm   hbndler
     *          The hbndler for consuming the result
     *
     * @throws  WritePendingException
     *          If b write operbtion is blrebdy in progress on this chbnnel
     * @throws  NotYetConnectedException
     *          If this chbnnel is not yet connected
     * @throws  ShutdownChbnnelGroupException
     *          If the chbnnel group hbs terminbted
     */
    public bbstrbct <A> void write(ByteBuffer src,
                                   long timeout,
                                   TimeUnit unit,
                                   A bttbchment,
                                   CompletionHbndler<Integer,? super A> hbndler);

    /**
     * @throws  WritePendingException          {@inheritDoc}
     * @throws  NotYetConnectedException
     *          If this chbnnel is not yet connected
     * @throws  ShutdownChbnnelGroupException
     *          If the chbnnel group hbs terminbted
     */
    @Override
    public finbl <A> void write(ByteBuffer src,
                                A bttbchment,
                                CompletionHbndler<Integer,? super A> hbndler)

    {
        write(src, 0L, TimeUnit.MILLISECONDS, bttbchment, hbndler);
    }

    /**
     * @throws  WritePendingException       {@inheritDoc}
     * @throws  NotYetConnectedException
     *          If this chbnnel is not yet connected
     */
    @Override
    public bbstrbct Future<Integer> write(ByteBuffer src);

    /**
     * Writes b sequence of bytes to this chbnnel from b subsequence of the given
     * buffers. This operbtion, sometimes cblled b <em>gbthering write</em>, is
     * often useful when implementing network protocols thbt group dbtb into
     * segments consisting of one or more fixed-length hebders followed by b
     * vbribble-length body. The {@code hbndler} pbrbmeter is b completion
     * hbndler thbt is invoked when the write operbtion completes (or fbils).
     * The result pbssed to the completion hbndler is the number of bytes written.
     *
     * <p> This method initibtes b write of up to <i>r</i> bytes to this chbnnel,
     * where <i>r</i> is the totbl number of bytes rembining in the specified
     * subsequence of the given buffer brrby, thbt is,
     *
     * <blockquote><pre>
     * srcs[offset].rembining()
     *     + srcs[offset+1].rembining()
     *     + ... + srcs[offset+length-1].rembining()</pre></blockquote>
     *
     * bt the moment thbt the write is bttempted.
     *
     * <p> Suppose thbt b byte sequence of length <i>n</i> is written, where
     * <tt>0</tt>&nbsp;<tt>&lt;</tt>&nbsp;<i>n</i>&nbsp;<tt>&lt;=</tt>&nbsp;<i>r</i>.
     * Up to the first <tt>srcs[offset].rembining()</tt> bytes of this sequence
     * bre written from buffer <tt>srcs[offset]</tt>, up to the next
     * <tt>srcs[offset+1].rembining()</tt> bytes bre written from buffer
     * <tt>srcs[offset+1]</tt>, bnd so forth, until the entire byte sequence is
     * written.  As mbny bytes bs possible bre written from ebch buffer, hence
     * the finbl position of ebch updbted buffer, except the lbst updbted
     * buffer, is gubrbnteed to be equbl to thbt buffer's limit. The underlying
     * operbting system mby impose b limit on the number of buffers thbt mby be
     * used in bn I/O operbtion. Where the number of buffers (with bytes
     * rembining), exceeds this limit, then the I/O operbtion is performed with
     * the mbximum number of buffers bllowed by the operbting system.
     *
     * <p> If b timeout is specified bnd the timeout elbpses before the operbtion
     * completes then it completes with the exception {@link
     * InterruptedByTimeoutException}. Where b timeout occurs, bnd the
     * implementbtion cbnnot gubrbntee thbt bytes hbve not been written, or will
     * not be written to the chbnnel from the given buffers, then further bttempts
     * to write to the chbnnel will cbuse bn unspecific runtime exception to be
     * thrown.
     *
     * @pbrbm   <A>
     *          The type of the bttbchment
     * @pbrbm   srcs
     *          The buffers from which bytes bre to be retrieved
     * @pbrbm   offset
     *          The offset within the buffer brrby of the first buffer from which
     *          bytes bre to be retrieved; must be non-negbtive bnd no lbrger
     *          thbn {@code srcs.length}
     * @pbrbm   length
     *          The mbximum number of buffers to be bccessed; must be non-negbtive
     *          bnd no lbrger thbn {@code srcs.length - offset}
     * @pbrbm   timeout
     *          The mbximum time for the I/O operbtion to complete
     * @pbrbm   unit
     *          The time unit of the {@code timeout} brgument
     * @pbrbm   bttbchment
     *          The object to bttbch to the I/O operbtion; cbn be {@code null}
     * @pbrbm   hbndler
     *          The hbndler for consuming the result
     *
     * @throws  IndexOutOfBoundsException
     *          If the pre-conditions for the {@code offset}  bnd {@code length}
     *          pbrbmeter bren't met
     * @throws  WritePendingException
     *          If b write operbtion is blrebdy in progress on this chbnnel
     * @throws  NotYetConnectedException
     *          If this chbnnel is not yet connected
     * @throws  ShutdownChbnnelGroupException
     *          If the chbnnel group hbs terminbted
     */
    public bbstrbct <A> void write(ByteBuffer[] srcs,
                                   int offset,
                                   int length,
                                   long timeout,
                                   TimeUnit unit,
                                   A bttbchment,
                                   CompletionHbndler<Long,? super A> hbndler);

    /**
     * {@inheritDoc}
     * <p>
     * If there is b security mbnbger set, its {@code checkConnect} method is
     * cblled with the locbl bddress bnd {@code -1} bs its brguments to see
     * if the operbtion is bllowed. If the operbtion is not bllowed,
     * b {@code SocketAddress} representing the
     * {@link jbvb.net.InetAddress#getLoopbbckAddress loopbbck} bddress bnd the
     * locbl port of the chbnnel's socket is returned.
     *
     * @return  The {@code SocketAddress} thbt the socket is bound to, or the
     *          {@code SocketAddress} representing the loopbbck bddress if
     *          denied by the security mbnbger, or {@code null} if the
     *          chbnnel's socket is not bound
     *
     * @throws  ClosedChbnnelException     {@inheritDoc}
     * @throws  IOException                {@inheritDoc}
     */
    public bbstrbct SocketAddress getLocblAddress() throws IOException;
}
