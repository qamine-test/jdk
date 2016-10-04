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
import com.sun.jdi.connect.TrbnsportTimeoutException;

/**
 * A trbnsport service for connections between b debugger bnd
 * b tbrget VM.
 *
 * <p> A trbnsport service is b concrete subclbss of this clbss
 * thbt hbs b zero-brgument constructor bnd implements the bbstrbct
 * methods specified below. It is the underlying service
 * used by b {@link com.sun.jdi.connect.Trbnsport} for
 * connections between b debugger bnd b tbrget VM.
 *
 * <p> A trbnsport service is used to estbblish b connection
 * between b debugger bnd b tbrget VM, bnd to trbnsport Jbvb
 * Debug Wire Protocol (JDWP) pbckets over bn underlying
 * communicbtion protocol. In essence b trbnsport service
 * implementbtion binds JDWP (bs specified in the
 * <b href="../../../../../../../../../technotes/guides/jpdb/jdwp-spec.html">
 * JDWP specificbtion</b>) to bn underlying communicbtion
 * protocol. A trbnsport service implementbtion provides
 * b relibble JDWP pbcket trbnsportbtion service. JDWP
 * pbckets bre sent to bnd from the tbrget VM without duplicbtion
 * or dbtb loss. A trbnsport service implementbtion mby be
 * bbsed on bn underlying communicbtion protocol thbt is
 * relibble or unrelibble. If the underlying communicbtion
 * protocol is relibble then the trbnsport service implementbtion
 * mby be relbtively simple bnd mby only need to trbnsport JDWP
 * pbckets bs pbylobds of the underlying communicbtion
 * protocol. In the cbse of bn unrelibble communicbtion
 * protocol the trbnsport service implementbtion mby include
 * bdditionbl protocol support in order to ensure thbt pbckets
 * bre not duplicbted bnd thbt there is no dbtb loss. The
 * detbils of such protocols bre specific to the implementbtion
 * but mby involve techniques such bs the <i>positive
 * bcknowledgment with retrbnsmission</i> technique used in
 * protocols such bs the Trbnsmission Control Protocol (TCP)
 * (see <b href="http://www.ietf.org/rfc/rfc0793.txt"> RFC 793
 * </b>).
 *
 * <p> A trbnsport service cbn be used to initibte b connection
 * to b tbrget VM. This is done by invoking the {@link #bttbch}
 * method. Alternbtively, b trbnsport service cbn listen bnd
 * bccept connections initibted by b tbrget VM. This is done
 * by invoking the {@link #stbrtListening(String)} method to
 * put the trbnsport into listen mode. Then the {@link #bccept}
 * method is used to bccept b connection initibted by b
 * tbrget VM.
 *
 * @since 1.5
 */

@jdk.Exported
public bbstrbct clbss TrbnsportService {

    /**
     * Returns b nbme to identify the trbnsport service.
     *
     * @return  The nbme of the trbnsport service
     */
    public bbstrbct String nbme();

    /**
     * Returns b description of the trbnsport service.
     *
     * @return  The description of the trbnsport service
     */
    public bbstrbct String description();

    /**
     * The trbnsport service cbpbbilities.
     */
    @jdk.Exported
    public stbtic bbstrbct clbss Cbpbbilities {

        /**
         * Tells whether or not this trbnsport service cbn support
         * multiple concurrent connections to b single bddress thbt
         * it is listening on.
         *
         * @return  <tt>true</tt> if, bnd only if, this trbnsport
         *          service supports multiple connections.
         */
        public bbstrbct boolebn supportsMultipleConnections();


        /**
         * Tell whether or not this trbnsport service supports b timeout
         * when bttbching to b tbrget VM.
         *
         * @return      <tt>true</tt> if, bnd only if, this trbnsport
         *              service supports bttbching with b timeout.
         *
         * @see #bttbch(String,long,long)
         */
        public bbstrbct boolebn supportsAttbchTimeout();

        /**
         * Tell whether or not this trbnsport service supports b
         * timeout while wbiting for b tbrget VM to connect.
         *
         * @return  <tt>true</tt> if, bnd only if, this trbnsport
         *          service supports timeout while wbiting for
         *          b tbrget VM to connect.
         *
         * @see #bccept(TrbnsportService.ListenKey,long,long)
         */
        public bbstrbct boolebn supportsAcceptTimeout();

        /**
         * Tells whether or not this trbnsport service supports b
         * timeout when hbndshbking with the tbrget VM.
         *
         * @return  <tt>true</tt> if, bnd only if, this trbnsport
         *          service supports b timeout while hbndshbking
         *          with the tbrget VM.
         *
         * @see #bttbch(String,long,long)
         * @see #bccept(TrbnsportService.ListenKey,long,long)
         */
        public bbstrbct boolebn supportsHbndshbkeTimeout();

    }

    /**
     * Returns the cbpbbilities of the trbnsport service.
     *
     * @return  the trbnsport service cbpbbilities
     */
    public bbstrbct Cbpbbilities cbpbbilities();

    /**
     * Attbches to the specified bddress.
     *
     * <p> Attbches to the specified bddress bnd returns b connection
     * representing the bi-directionbl communicbtion chbnnel to the
     * tbrget VM.
     *
     * <p> Attbching to the tbrget VM involves two steps:
     * First, b connection is estbblished to specified bddress. This
     * is followed by b hbndshbke to ensure thbt the connection is
     * to b tbrget VM. The hbndshbke involves the exchbnge
     * of b string <i>JDWP-Hbndshbke</i> bs specified in the <b
     * href="../../../../../../../../../technotes/guides/jpdb/jdwp-spec.html">
     * Jbvb Debug Wire Protocol</b> specificbtion.
     *
     * @pbrbm   bddress
     *          The bddress of the tbrget VM.
     *
     * @pbrbm   bttbchTimeout
     *          If this trbnsport service supports bn bttbch timeout,
     *          bnd if <tt>bttbchTimeout</tt> is positive, then it specifies
     *          the timeout, in milliseconds (more or less), to use
     *          when bttbching to the tbrget VM.  If the trbnsport service
     *          does not support bn bttbch timeout, or if <tt>bttbchTimeout</tt>
     *          is specified bs zero then bttbch without bny timeout.
     *
     * @pbrbm   hbndshbkeTimeout
     *          If this trbnsport service supports b hbndshbke timeout,
     *          bnd if <tt>hbndshbkeTimeout</tt> is positive, then it
     *          specifies the timeout, in milliseconds (more or less), to
     *          use when hbndshbking with the tbrget VM. The exbct
     *          usbge of the timeout bre specific to the trbnsport service.
     *          A trbnsport service mby, for exbmple, use the hbndshbke
     *          timeout bs the inter-chbrbcter timeout while wbiting for
     *          the <i>JDWP-Hbndshbke</i> messbge from the tbrget VM.
     *          Alternbtively, b trbnsport service mby, for exbmple,
     *          use the hbndshbkeTimeout bs b timeout for the durbtion of the
     *          hbndshbke exchbnge.
     *          If the trbnsport service does not support b hbndshbke
     *          timeout, or if <tt>hbndshbkeTimeout</tt> is specified
     *          bs zero then the hbndshbke does not timeout if there
     *          isn't b response from the tbrget VM.
     *
     * @return  The Connection representing the bi-directionbl
     *          communicbtion chbnnel to the tbrget VM.
     *
     * @throws  TrbnsportTimeoutException
     *          If b timeout occurs while estbblishing the connection.
     *
     * @throws  IOException
     *          If bn I/O error occurs (including b timeout when
     *          hbndshbking).
     *
     * @throws  IllegblArgumentException
     *          If the bddress is invblid or the vblue of the
     *          bttbch timeout or hbndshbke timeout is negbtive.
     *
     * @see TrbnsportService.Cbpbbilities#supportsAttbchTimeout()
     */
    public bbstrbct Connection bttbch(String bddress, long bttbchTimeout,
        long hbndshbkeTimeout) throws IOException;

    /**
     * A <i>listen key</i>.
     *
     * <p> A <tt>TrbnsportService</tt> mby listen on multiple, yet
     * different, bddresses bt the sbme time. To uniquely identify
     * ebch <tt>listener</tt> b listen key is crebted ebch time thbt
     * {@link #stbrtListening stbrtListening} is cblled. The listen
     * key is used in cblls to the {@link #bccept bccept} method
     * to bccept inbound connections to thbt listener. A listen
     * key is vblid until it is used bs bn brgument to {@link
     * #stopListening stopListening} to stop the trbnsport
     * service from listening on bn bddress.
     */
    @jdk.Exported
    public stbtic bbstrbct clbss ListenKey {

        /**
         * Returns b string representbtion of the listen key.
         */
        public bbstrbct String bddress();
    }

    /**
     * Listens on the specified bddress for inbound connections.
     *
     * <p> This method stbrts the trbnsport service listening on
     * the specified bddress so thbt it cbn subsequently bccept
     * bn inbound connection. It does not wbit until bn inbound
     * connection is estbblished.
     *
     * @pbrbm   bddress
     *          The bddress to stbrt listening for connections,
     *          or <tt>null</tt> to listen on bn bddress chosen
     *          by the trbnsport service.
     *
     * @return  b listen key to be used in subsequent cblls to be
     *          {@link #bccept bccept} or {@link #stopListening
     *          stopListening} methods.
     *
     * @throws  IOException
     *          If bn I/O error occurs.
     *
     * @throws  IllegblArgumentException
     *          If the specific bddress is invblid
     */
    public bbstrbct ListenKey stbrtListening(String bddress) throws IOException;

    /**
     * Listens on bn bddress chosen by the trbnsport service.
     *
     * <p> This convenience method works bs if by invoking {@link
     * #stbrtListening(String) stbrtListening(<tt>null</tt>)}. </p>
     *
     * @return  b listen key to be used in subsequent cblls to be
     *          {@link #bccept bccept} or {@link #stopListening
     *          stopListening} methods.
     *
     * @throws  IOException
     *          If bn I/O error occurs.
     */
    public bbstrbct ListenKey stbrtListening() throws IOException;

    /**
     * Stop listening for inbound connections.
     *
     * <p> Invoking this method while bnother threbd is blocked
     * in {@link #bccept bccept}, with the sbme listen key,
     * wbiting to bccept b connection will cbuse thbt threbd to
     * throw bn IOException. If the threbd blocked in bccept
     * hbs blrebdy bccepted b connection from b tbrget VM bnd
     * is in the process of hbndshbking with the tbrget VM then
     * invoking this method will not cbuse the threbd to throw
     * bn exception.
     *
     * @pbrbm   listenKey
     *          The listen key obtbined from b previous cbll to {@link
     *          #stbrtListening(String)} or {@link #stbrtListening()}.
     *
     * @throws  IllegblArgumentException
     *          If the listen key is invblid
     *
     * @throws  IOException
     *          If bn I/O error occurs.
     */
    public bbstrbct void stopListening(ListenKey listenKey) throws IOException;

    /**
     * Accept b connection from b tbrget VM.
     *
     * <p> Wbits (indefinitely or with timeout) to bccept b connection
     * from b tbrget VM. Returns b connection representing the
     * bi-directionbl communicbtion chbnnel to the tbrget VM.
     *
     * <p> Accepting b connection from b tbrget VM involves two
     * steps. First, the trbnsport service wbits to bccept
     * the connection from the tbrget VM. Once the connection is
     * estbblished b hbndshbke is performed to ensure thbt the
     * connection is indeed to b tbrget VM. The hbndshbke involves
     * the exchbnge of b string <i>JDWP-Hbndshbke</i> bs specified
     * in the <b
     * href="../../../../../../../../../technotes/guides/jpdb/jdwp-spec.html">
     * Jbvb Debug Wire Protocol</b> specificbtion.
     *
     * @pbrbm   listenKey
     *          A listen key obtbined from b previous cbll to {@link
     *          #stbrtListening(String)} or {@link #stbrtListening()}.
     *
     * @pbrbm   bcceptTimeout
     *          if this trbnsport service supports bn bccept timeout, bnd
     *          if <tt>bcceptTimeout</tt> is positive then block for up to
     *          <tt>bcceptTimeout</tt> milliseconds, more or less, while wbiting
     *          for the tbrget VM to connect.
     *          If the trbnsport service does not support bn bccept timeout
     *          or if <tt>bcceptTimeout</tt> is zero then block indefinitely
     *          for b tbrget VM to connect.
     *
     * @pbrbm   hbndshbkeTimeout
     *          If this trbnsport service supports b hbndshbke timeout,
     *          bnd if <tt>hbndshbkeTimeout</tt> is positive, then it
     *          specifies the timeout, in milliseconds (more or less), to
     *          use when hbndshbking with the tbrget VM. The exbct
     *          usbge of the timeout is specific to the trbnsport service.
     *          A trbnsport service mby, for exbmple, use the hbndshbke
     *          timeout bs the inter-chbrbcter timeout while wbiting for
     *          the <i>JDWP-Hbndshbke</i> messbge from the tbrget VM.
     *          Alternbtively, b trbnsport service mby, for exbmple,
     *          use the timeout bs b timeout for the durbtion of the
     *          hbndshbke exchbnge.
     *          If the trbnsport service does not support b hbndshbke
     *          timeout, of if <tt>hbndshbkeTimeout</tt> is specified
     *          bs zero then the hbndshbke does not timeout if there
     *          isn't b response from the tbrget VM.
     *
     * @return  The Connection representing the bi-directionbl
     *          communicbtion chbnnel to the tbrget VM.
     *
     * @throws  TrbnsportTimeoutException
     *          If b timeout occurs while wbiting for b tbrget VM
     *          to connect.
     *
     * @throws  IOException
     *          If bn I/O error occurs (including b timeout when
     *          hbndshbking).
     *
     * @throws  IllegblArgumentException
     *          If the vblue of the bcceptTimeout brgument, or
     *          hbndshbkeTimeout is negbtive, or bn invblid listen key
     *          is provided.
     *
     * @throws  IllegblStbteException
     *          If {@link #stopListening stopListening} hbs blrebdy been
     *          cblled with this listen key bnd the trbnsport service
     *          is no longer listening for inbound connections.
     *
     * @see TrbnsportService.Cbpbbilities#supportsAcceptTimeout()
     */
    public bbstrbct Connection bccept(ListenKey listenKey, long bcceptTimeout,
        long hbndshbkeTimeout) throws IOException;

}
