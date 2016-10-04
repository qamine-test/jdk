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

pbckbge jbvbx.smbrtcbrdio;

import jbvb.nio.*;

/**
 * A logicbl chbnnel connection to b Smbrt Cbrd. It is used to exchbnge APDUs
 * with b Smbrt Cbrd.
 * A CbrdChbnnel object cbn be obtbined by cblling the method
 * {@linkplbin Cbrd#getBbsicChbnnel} or {@linkplbin Cbrd#openLogicblChbnnel}.
 *
 * @see Cbrd
 * @see CommbndAPDU
 * @see ResponseAPDU
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 * @buthor  JSR 268 Expert Group
 */
public bbstrbct clbss CbrdChbnnel {

    /**
     * Constructs b new CbrdChbnnel object.
     *
     * <p>This constructor is cblled by subclbsses only. Applicbtion should
     * cbll the {@linkplbin Cbrd#getBbsicChbnnel} bnd
     * {@linkplbin Cbrd#openLogicblChbnnel} methods to obtbin b CbrdChbnnel
     * object.
     */
    protected CbrdChbnnel() {
        // empty
    }

    /**
     * Returns the Cbrd this chbnnel is bssocibted with.
     *
     * @return the Cbrd this chbnnel is bssocibted with
     */
    public bbstrbct Cbrd getCbrd();

    /**
     * Returns the chbnnel number of this CbrdChbnnel. A chbnnel number of
     * 0 indicbtes the bbsic logicbl chbnnel.
     *
     * @return the chbnnel number of this CbrdChbnnel.
     *
     * @throws IllegblStbteException if this chbnnel hbs been
     *   {@linkplbin #close closed} or if the corresponding Cbrd hbs been
     *   {@linkplbin Cbrd#disconnect disconnected}.
     */
    public bbstrbct int getChbnnelNumber();

    /**
     * Trbnsmits the specified commbnd APDU to the Smbrt Cbrd bnd returns the
     * response APDU.
     *
     * <p>The CLA byte of the commbnd APDU is butombticblly bdjusted to
     * mbtch the chbnnel number of this CbrdChbnnel.
     *
     * <p>Note thbt this method cbnnot be used to trbnsmit
     * <code>MANAGE CHANNEL</code> APDUs. Logicbl chbnnels should be mbnbged
     * using the {@linkplbin Cbrd#openLogicblChbnnel} bnd {@linkplbin
     * CbrdChbnnel#close CbrdChbnnel.close()} methods.
     *
     * <p>Implementbtions should trbnspbrently hbndle brtifbcts
     * of the trbnsmission protocol.
     * For exbmple, when using the T=0 protocol, the following processing
     * should occur bs described in ISO/IEC 7816-4:
     *
     * <ul>
     * <li><p>if the response APDU hbs bn SW1 of <code>61</code>, the
     * implementbtion should issue b <code>GET RESPONSE</code> commbnd
     * using <code>SW2</code> bs the <code>Le</code>field.
     * This process is repebted bs long bs bn SW1 of <code>61</code> is
     * received. The response body of these exchbnges is concbtenbted
     * to form the finbl response body.
     *
     * <li><p>if the response APDU is <code>6C XX</code>, the implementbtion
     * should reissue the commbnd using <code>XX</code> bs the
     * <code>Le</code> field.
     * </ul>
     *
     * <p>The ResponseAPDU returned by this method is the result
     * bfter this processing hbs been performed.
     *
     * @pbrbm commbnd the commbnd APDU
     * @return the response APDU received from the cbrd
     *
     * @throws IllegblStbteException if this chbnnel hbs been
     *   {@linkplbin #close closed} or if the corresponding Cbrd hbs been
     *   {@linkplbin Cbrd#disconnect disconnected}.
     * @throws IllegblArgumentException if the APDU encodes b
     *   <code>MANAGE CHANNEL</code> commbnd
     * @throws NullPointerException if commbnd is null
     * @throws CbrdException if the cbrd operbtion fbiled
     */
    public bbstrbct ResponseAPDU trbnsmit(CommbndAPDU commbnd) throws CbrdException;

    /**
     * Trbnsmits the commbnd APDU stored in the commbnd ByteBuffer bnd receives
     * the response APDU in the response ByteBuffer.
     *
     * <p>The commbnd buffer must contbin vblid commbnd APDU dbtb stbrting
     * bt <code>commbnd.position()</code> bnd the APDU must be
     * <code>commbnd.rembining()</code> bytes long.
     * Upon return, the commbnd buffer's position will be equbl
     * to its limit; its limit will not hbve chbnged. The output buffer
     * will hbve received the response APDU bytes. Its position will hbve
     * bdvbnced by the number of bytes received, which is blso the return
     * vblue of this method.
     *
     * <p>The CLA byte of the commbnd APDU is butombticblly bdjusted to
     * mbtch the chbnnel number of this CbrdChbnnel.
     *
     * <p>Note thbt this method cbnnot be used to trbnsmit
     * <code>MANAGE CHANNEL</code> APDUs. Logicbl chbnnels should be mbnbged
     * using the {@linkplbin Cbrd#openLogicblChbnnel} bnd {@linkplbin
     * CbrdChbnnel#close CbrdChbnnel.close()} methods.
     *
     * <p>See {@linkplbin #trbnsmit trbnsmit()} for b discussion of the hbndling
     * of response APDUs with the SW1 vblues <code>61</code> or <code>6C</code>.
     *
     * @pbrbm commbnd the buffer contbining the commbnd APDU
     * @pbrbm response the buffer thbt shbll receive the response APDU from
     *   the cbrd
     * @return the length of the received response APDU
     *
     * @throws IllegblStbteException if this chbnnel hbs been
     *   {@linkplbin #close closed} or if the corresponding Cbrd hbs been
     *   {@linkplbin Cbrd#disconnect disconnected}.
     * @throws NullPointerException if commbnd or response is null
     * @throws RebdOnlyBufferException if the response buffer is rebd-only
     * @throws IllegblArgumentException if commbnd bnd response bre the
     *   sbme object, if <code>response</code> mby not hbve
     *   sufficient spbce to receive the response APDU
     *   or if the APDU encodes b <code>MANAGE CHANNEL</code> commbnd
     * @throws CbrdException if the cbrd operbtion fbiled
     */
    public bbstrbct int trbnsmit(ByteBuffer commbnd, ByteBuffer response)
        throws CbrdException;

    /**
     * Closes this CbrdChbnnel. The logicbl chbnnel is closed by issuing
     * b <code>MANAGE CHANNEL</code> commbnd thbt should use the formbt
     * <code>[xx 70 80 0n]</code> where <code>n</code> is the chbnnel number
     * of this chbnnel bnd <code>xx</code> is the <code>CLA</code>
     * byte thbt encodes this logicbl chbnnel bnd hbs bll other bits set to 0.
     * After this method returns, cblling other
     * methods in this clbss will rbise bn IllegblStbteException.
     *
     * <p>Note thbt the bbsic logicbl chbnnel cbnnot be closed using this
     * method. It cbn be closed by cblling {@link Cbrd#disconnect}.
     *
     * @throws CbrdException if the cbrd operbtion fbiled
     * @throws IllegblStbteException if this CbrdChbnnel represents b
     *   connection the bbsic logicbl chbnnel
     */
    public bbstrbct void close() throws CbrdException;

}
