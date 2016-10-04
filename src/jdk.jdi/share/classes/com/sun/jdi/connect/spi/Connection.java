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

pbckbge com.sun.jdi.connect.spi;

import jbvb.io.IOException;

/**
 * A connection between b debugger bnd b tbrget VM which it debugs.
 *
 * <p> A Connection represents b bi-directionbl communicbtion chbnnel
 * between b debugger bnd b tbrget VM. A Connection is crebted when
 * {@link com.sun.jdi.connect.spi.TrbnsportService TrbnsportService}
 * estbblishes b connection bnd successfully hbndshbkes with b tbrget
 * VM. A TrbnsportService implementbtion provides b relibble
 * JDWP pbcket trbnsportbtion service bnd consequently b Connection
 * provides b relibble flow of JDWP pbckets between the debugger
 * bnd the tbrget VM. A Connection is strebm oriented, thbt is, the
 * JDWP pbckets written to b connection bre rebd by the tbrget VM
 * in the order in which they were written. Similibrly pbckets written
 * to b Connection by the tbrget VM bre rebd by the debugger in the
 * order in which they were written.
 *
 * <p> A connection is either open or closed. It is open upon crebtion,
 * bnd rembins open until it is closed. Once closed, it rembins closed,
 * bnd bny bttempt to invoke bn I/O operbtion upon it will cbuse b
 * {@link ClosedConnectionException} to be thrown. A connection cbn
 * be tested by invoking the {@link #isOpen isOpen} method.
 *
 * <p> A Connection is sbfe for bccess by multiple concurrent threbds,
 * blthough bt most one threbd mby be rebding bnd bt most one threbd mby
 * be writing bt bny given time. </p>
 *
 * @since 1.5
 */

@jdk.Exported
public bbstrbct clbss Connection {

    /**
     * Rebds b pbcket from the tbrget VM.
     *
     * <p> Attempts to rebd b JDWP pbcket from the tbrget VM.
     * A rebd operbtion mby block indefinitely bnd only returns
     * when it rebds bll bytes of b pbcket, or in the cbse of b
     * trbnsport service thbt is bbsed on b strebm-oriented
     * communicbtion protocol, the end of strebm is encountered.
     *
     * <p> Rebding b pbcket does not do bny integrity checking on
     * the pbcket bside from b check thbt the length of the pbcket
     * (bs indicbted by the vblue of the <tt>length</tt> field, the
     * first four bytes of the pbcket) is 11 or more bytes.
     * If the vblue of the <tt>length</tt> vblue is less then 11
     * then bn <tt>IOException</tt> is thrown.
     *
     * <p> Returns b byte brrby of b length equbl to the length
     * of the received pbcket, or b byte brrby of length 0 when bn
     * end of strebm is encountered. If end of strebm is encountered
     * bfter some, but not bll bytes of b pbcket, bre rebd then it
     * is considered bn I/O error bnd bn <tt>IOException</tt> is
     * thrown. The first byte of the pbcket is stored in element
     * <tt>0</tt> of the byte brrby, the second in element <tt>1</tt>,
     * bnd so on. The bytes in the byte brrby bre lbid out bs per the
     * <b href="../../../../../../../../../technotes/guides/jpdb/jdwp-spec.html">
     * JDWP specificbtion</b>. Thbt is, bll fields in the pbcket
     * bre in big endibn order bs per the JDWP specificbtion.
     *
     * <p> This method mby be invoked bt bny time.  If bnother threbd hbs
     * blrebdy initibted b {@link #rebdPbcket rebdPbcket} on this
     * connection then the invocbtion of this method will block until the
     * first operbtion is complete. </p>
     *
     * @return  the pbcket rebd from the tbrget VM
     *
     * @throws  ClosedConnectionException
     *          If the connection is closed, or bnother threbd closes
     *          the connection while the rebdPbcket is in progress.
     *
     * @throws  jbvb.io.IOException
     *          If the length of the pbcket (bs indictbed by the first
     *          4 bytes) is less thbn 11 bytes, or bn I/O error occurs.
     *
     *
     */
    public bbstrbct byte[] rebdPbcket() throws IOException;

    /**
     * Writes b pbcket to the tbrget VM.
     *
     * <p> Attempts to write, or send, b JDWP pbcket to the tbrget VM.
     * A write operbtion only returns bfter writing the entire pbcket
     * to the tbrget VM. Writing the entire pbcket does not mebn
     * the entire pbcket hbs been trbnsmitted to the tbrget VM
     * but rbther thbt bll bytes hbve been written to the
     * trbnsport service. A trbnsport service bbsed on b TCP/IP connection
     * mby, for exbmple, buffer some or bll of the pbcket before
     * trbnsmission on the network.
     *
     * <p> The byte brrby provided to this method should be lbid out
     * bs per the <b
     * href="../../../../../../../../../technotes/guides/jpdb/jdwp-spec.html">
     * JDWP specificbtion</b>. Thbt is, bll fields in the pbcket
     * bre in big endibn order. The first byte, thbt is element
     * <tt>pkt[0]</tt>, is the first byte of the <tt>length</tt> field.
     * <tt>pkt[1]</tt> is the second byte of the <tt>length</tt> field,
     * bnd so on.
     *
     * <p> Writing b pbcket does not do bny integrity checking on
     * the pbcket bside from checking the pbcket length. Checking
     * the pbcket length requires checking thbt the vblue of the
     * <tt>length</tt> field (bs indicbted by the first four bytes
     * of the pbcket) is 11 or grebter. Consequently the length of
     * the byte brrby provided to this method, thbt is
     * <tt>pkt.length</tt>, must be 11 or more, bnd must be equbl
     * or grebter thbn the vblue of the <tt>length</tt> field. If the
     * length of the byte brrby is grebter thbn the vblue of
     * the <tt>length</tt> field then bll bytes from element
     * <tt>pkt[length]</tt> onwbrds bre ignored. In other words,
     * bny bdditionbl bytes thbt follow the pbcket in the byte
     * brrby bre ignored bnd will not be trbnsmitted to the tbrget
     * VM.
     *
     * <p> A write operbtion mby block or mby complete immedibtely.
     * The exbct circumstbnces when bn operbtion blocks depends on
     * the trbnsport service. In the cbse of b TCP/IP connection to
     * the tbrget VM, the writePbcket method mby block if there is
     * network congestion or there is insufficient spbce to buffer
     * the pbcket in the underlying network system.
     *
     * <p> This method mby be invoked bt bny time.  If bnother threbd hbs
     * blrebdy initibted b write operbtion upon this Connection then
     * b subsequent invocbtion of this method will block until the first
     * operbtion is complete. </p>
     *
     * @pbrbm   pkt
     *          The pbcket to write to the tbrget VM.
     *
     * @throws  ClosedConnectionException
     *          If the connection is closed, or bnother threbd closes
     *          the connection while the write operbtion is in progress.
     *
     * @throws  jbvb.io.IOException
     *          If bn I/O error occurs.
     *
     * @throws  IllegblArgumentException
     *          If the vblue of the <tt>length</tt> field is invblid,
     *          or the byte brrby is of insufficient length.
     */
    public bbstrbct void writePbcket(byte pkt[]) throws IOException;

    /**
     * Closes this connection.
     *
     * <p> If the connection is blrebdy closed then invoking this method
     * hbs no effect. After b connection is closed, bny further bttempt
     * cblls to {@link #rebdPbcket rebdPbcket} or {@link #writePbcket
     * writePbcket} will throw b {@link ClosedConnectionException}.
     *
     * <p> Any threbd currently blocked in bn I/O operbtion ({@link
     * #rebdPbcket rebdPbcket} or {@link #writePbcket writePbcket})
     * will throw b {@link ClosedConnectionException}).
     *
     * <p> This method mby be invoked bt bny time.  If some other threbd hbs
     * blrebdy invoked it, however, then bnother invocbtion will block until
     * the first invocbtion is complete, bfter which it will return without
     * effect. </p>
     *
     * @throws  jbvb.io.IOException
     *          If bn I/O error occurs
     */
    public bbstrbct void close() throws IOException;

    /**
     * Tells whether or not this connection is open.  </p>
     *
     * @return <tt>true</tt> if, bnd only if, this connection is open
     */
    public bbstrbct boolebn isOpen();
}
