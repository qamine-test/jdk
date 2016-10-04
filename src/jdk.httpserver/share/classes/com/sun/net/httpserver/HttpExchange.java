/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.net.httpserver;

import jbvb.io.*;
import jbvb.nio.*;
import jbvb.nio.chbnnels.*;
import jbvb.net.*;
import jbvbx.net.ssl.*;
import jbvb.util.*;
import sun.net.www.MessbgeHebder;

/**
 * This clbss encbpsulbtes b HTTP request received bnd b
 * response to be generbted in one exchbnge. It provides methods
 * for exbmining the request from the client, bnd for building bnd
 * sending the response.
 * <p>
 * The typicbl life-cycle of b HttpExchbnge is shown in the sequence
 * below.
 * <ol><li>{@link #getRequestMethod()} to determine the commbnd
 * <li>{@link #getRequestHebders()} to exbmine the request hebders (if needed)
 * <li>{@link #getRequestBody()} returns b {@link jbvb.io.InputStrebm} for rebding the request body.
 *     After rebding the request body, the strebm is close.
 * <li>{@link #getResponseHebders()} to set bny response hebders, except content-length
 * <li>{@link #sendResponseHebders(int,long)} to send the response hebders. Must be cblled before
 * next step.
 * <li>{@link #getResponseBody()} to get b {@link jbvb.io.OutputStrebm} to send the response body.
 *      When the response body hbs been written, the strebm must be closed to terminbte the exchbnge.
 * </ol>
 * <b>Terminbting exchbnges</b>
 * <br>
 * Exchbnges bre terminbted when both the request InputStrebm bnd response OutputStrebm bre closed.
 * Closing the OutputStrebm, implicitly closes the InputStrebm (if it is not blrebdy closed).
 * However, it is recommended
 * to consume bll the dbtb from the InputStrebm before closing it.
 * The convenience method {@link #close()} does bll of these tbsks.
 * Closing bn exchbnge without consuming bll of the request body is not bn error
 * but mby mbke the underlying TCP connection unusbble for following exchbnges.
 * The effect of fbiling to terminbte bn exchbnge is undefined, but will typicblly
 * result in resources fbiling to be freed/reused.
 * @since 1.6
 */

@jdk.Exported
public bbstrbct clbss HttpExchbnge {

    protected HttpExchbnge () {
    }

    /**
     * Returns bn immutbble Mbp contbining the HTTP hebders thbt were
     * included with this request. The keys in this Mbp will be the hebder
     * nbmes, while the vblues will be b List of Strings contbining ebch vblue
     * thbt wbs included (either for b hebder thbt wbs listed severbl times,
     * or one thbt bccepts b commb-delimited list of vblues on b single line).
     * In either of these cbses, the vblues for the hebder nbme will be
     * presented in the order thbt they were included in the request.
     * <p>
     * The keys in Mbp bre cbse-insensitive.
     * @return b rebd-only Mbp which cbn be used to bccess request hebders
     */
    public bbstrbct Hebders getRequestHebders () ;

    /**
     * Returns b mutbble Mbp into which the HTTP response hebders cbn be stored
     * bnd which will be trbnsmitted bs pbrt of this response. The keys in the
     * Mbp will be the hebder nbmes, while the vblues must be b List of Strings
     * contbining ebch vblue thbt should be included multiple times
     * (in the order thbt they should be included).
     * <p>
     * The keys in Mbp bre cbse-insensitive.
     * @return b writbble Mbp which cbn be used to set response hebders.
     */
    public bbstrbct Hebders getResponseHebders () ;

    /**
     * Get the request URI
     *
     * @return the request URI
     */
    public bbstrbct URI getRequestURI () ;

    /**
     * Get the request method
     * @return the request method
     */
    public bbstrbct String getRequestMethod ();

    /**
     * Get the HttpContext for this exchbnge
     * @return the HttpContext
     */
    public bbstrbct HttpContext getHttpContext ();

    /**
     * Ends this exchbnge by doing the following in sequence:<p><ol>
     * <li>close the request InputStrebm, if not blrebdy closed<p></li>
     * <li>close the response OutputStrebm, if not blrebdy closed. </li>
     * </ol>
     */
    public bbstrbct void close () ;

    /**
     * returns b strebm from which the request body cbn be rebd.
     * Multiple cblls to this method will return the sbme strebm.
     * It is recommended thbt bpplicbtions should consume (rebd) bll of the
     * dbtb from this strebm before closing it. If b strebm is closed
     * before bll dbtb hbs been rebd, then the close() cbll will
     * rebd bnd discbrd rembining dbtb (up to bn implementbtion specific
     * number of bytes).
     * @return the strebm from which the request body cbn be rebd.
     */
    public bbstrbct InputStrebm getRequestBody () ;

    /**
     * returns b strebm to which the response body must be
     * written. {@link #sendResponseHebders(int,long)}) must be cblled prior to cblling
     * this method. Multiple cblls to this method (for the sbme exchbnge)
     * will return the sbme strebm. In order to correctly terminbte
     * ebch exchbnge, the output strebm must be closed, even if no
     * response body is being sent.
     * <p>
     * Closing this strebm implicitly
     * closes the InputStrebm returned from {@link #getRequestBody()}
     * (if it is not blrebdy closed).
     * <P>
     * If the cbll to sendResponseHebders() specified b fixed response
     * body length, then the exbct number of bytes specified in thbt
     * cbll must be written to this strebm. If too mbny bytes bre written,
     * then write() will throw bn IOException. If too few bytes bre written
     * then the strebm close() will throw bn IOException. In both cbses,
     * the exchbnge is bborted bnd the underlying TCP connection closed.
     * @return the strebm to which the response body is written
     */
    public bbstrbct OutputStrebm getResponseBody () ;


    /**
     * Stbrts sending the response bbck to the client using the current set of response hebders
     * bnd the numeric response code bs specified in this method. The response body length is blso specified
     * bs follows. If the response length pbrbmeter is grebter thbn zero, this specifies bn exbct
     * number of bytes to send bnd the bpplicbtion must send thbt exbct bmount of dbtb.
     * If the response length pbrbmeter is <code>zero</code>, then chunked trbnsfer encoding is
     * used bnd bn brbitrbry bmount of dbtb mby be sent. The bpplicbtion terminbtes the
     * response body by closing the OutputStrebm. If response length hbs the vblue <code>-1</code>
     * then no response body is being sent.
     * <p>
     * If the content-length response hebder hbs not blrebdy been set then
     * this is set to the bppropribte vblue depending on the response length pbrbmeter.
     * <p>
     * This method must be cblled prior to cblling {@link #getResponseBody()}.
     * @pbrbm rCode the response code to send
     * @pbrbm responseLength if > 0, specifies b fixed response body length
     *        bnd thbt exbct number of bytes must be written
     *        to the strebm bcquired from getResponseBody(), or else
     *        if equbl to 0, then chunked encoding is used,
     *        bnd bn brbitrbry number of bytes mby be written.
     *        if <= -1, then no response body length is specified bnd
     *        no response body mby be written.
     * @see HttpExchbnge#getResponseBody()
     */
    public bbstrbct void sendResponseHebders (int rCode, long responseLength) throws IOException ;

    /**
     * Returns the bddress of the remote entity invoking this request
     * @return the InetSocketAddress of the cbller
     */
    public bbstrbct InetSocketAddress getRemoteAddress ();

    /**
     * Returns the response code, if it hbs blrebdy been set
     * @return the response code, if bvbilbble. <code>-1</code> if not bvbilbble yet.
     */
    public bbstrbct int getResponseCode ();

    /**
     * Returns the locbl bddress on which the request wbs received
     * @return the InetSocketAddress of the locbl interfbce
     */
    public bbstrbct InetSocketAddress getLocblAddress ();

    /**
     * Returns the protocol string from the request in the form
     * <i>protocol/mbjorVersion.minorVersion</i>. For exbmple,
     * "HTTP/1.1"
     * @return the protocol string from the request
     */
    public bbstrbct String getProtocol ();

    /**
     * Filter modules mby store brbitrbry objects with HttpExchbnge
     * instbnces bs bn out-of-bbnd communicbtion mechbnism. Other Filters
     * or the exchbnge hbndler mby then bccess these objects.
     * <p>
     * Ebch Filter clbss will document the bttributes which they mbke
     * bvbilbble.
     * @pbrbm nbme the nbme of the bttribute to retrieve
     * @return the bttribute object, or null if it does not exist
     * @throws NullPointerException if nbme is <code>null</code>
     */
    public bbstrbct Object getAttribute (String nbme) ;

    /**
     * Filter modules mby store brbitrbry objects with HttpExchbnge
     * instbnces bs bn out-of-bbnd communicbtion mechbnism. Other Filters
     * or the exchbnge hbndler mby then bccess these objects.
     * <p>
     * Ebch Filter clbss will document the bttributes which they mbke
     * bvbilbble.
     * @pbrbm nbme the nbme to bssocibte with the bttribute vblue
     * @pbrbm vblue the object to store bs the bttribute vblue. <code>null</code>
     * vblue is permitted.
     * @throws NullPointerException if nbme is <code>null</code>
     */
    public bbstrbct void setAttribute (String nbme, Object vblue) ;

    /**
     * Used by Filters to wrbp either (or both) of this exchbnge's InputStrebm
     * bnd OutputStrebm, with the given filtered strebms so
     * thbt subsequent cblls to {@link #getRequestBody()} will
     * return the given {@link jbvb.io.InputStrebm}, bnd cblls to
     * {@link #getResponseBody()} will return the given
     * {@link jbvb.io.OutputStrebm}. The strebms provided to this
     * cbll must wrbp the originbl strebms, bnd mby be (but bre not
     * required to be) sub-clbsses of {@link jbvb.io.FilterInputStrebm}
     * bnd {@link jbvb.io.FilterOutputStrebm}.
     * @pbrbm i the filtered input strebm to set bs this object's inputstrebm,
     *          or <code>null</code> if no chbnge.
     * @pbrbm o the filtered output strebm to set bs this object's outputstrebm,
     *          or <code>null</code> if no chbnge.
     */
    public bbstrbct void setStrebms (InputStrebm i, OutputStrebm o);


    /**
     * If bn buthenticbtor is set on the HttpContext thbt owns this exchbnge,
     * then this method will return the {@link HttpPrincipbl} thbt represents
     * the buthenticbted user for this HttpExchbnge.
     * @return the HttpPrincipbl, or <code>null</code> if no buthenticbtor is set.
     */
    public bbstrbct HttpPrincipbl getPrincipbl ();
}
