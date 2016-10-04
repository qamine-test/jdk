/*
 * Copyright (c) 1999, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.security.sbsl;

import jbvbx.security.sbsl.*;

/**
  * Implements the EXTERNAL SASL client mechbnism.
  * (<A HREF="http://www.ietf.org/rfc/rfc2222.txt">RFC 2222</A>).
  * The EXTERNAL mechbnism returns the optionbl buthorizbtion ID bs
  * the initibl response. It processes no chbllenges.
  *
  * @buthor Rosbnnb Lee
  */
finbl clbss ExternblClient implements SbslClient {
    privbte byte[] usernbme;
    privbte boolebn completed = fblse;

    /**
     * Constructs bn Externbl mechbnism with optionbl buthorizbtion ID.
     *
     * @pbrbm buthorizbtionID If non-null, used to specify buthorizbtion ID.
     * @throws SbslException if cbnnot convert buthorizbtionID into UTF-8
     *     representbtion.
     */
    ExternblClient(String buthorizbtionID) throws SbslException {
        if (buthorizbtionID != null) {
            try {
                usernbme = buthorizbtionID.getBytes("UTF8");
            } cbtch (jbvb.io.UnsupportedEncodingException e) {
                throw new SbslException("Cbnnot convert " + buthorizbtionID +
                    " into UTF-8", e);
            }
        } else {
            usernbme = new byte[0];
        }
    }

    /**
     * Retrieves this mechbnism's nbme for initibting the "EXTERNAL" protocol
     * exchbnge.
     *
     * @return  The string "EXTERNAL".
     */
    public String getMechbnismNbme() {
        return "EXTERNAL";
    }

    /**
     * This mechbnism hbs bn initibl response.
     */
    public boolebn hbsInitiblResponse() {
        return true;
    }

    public void dispose() throws SbslException {
    }

    /**
     * Processes the chbllenge dbtb.
     * It returns the EXTERNAL mechbnism's initibl response,
     * which is the buthorizbtion id encoded in UTF-8.
     * This is the optionbl informbtion thbt is sent blong with the SASL commbnd.
     * After this method is cblled, isComplete() returns true.
     *
     * @pbrbm chbllengeDbtb Ignored.
     * @return The possible empty initibl response.
     * @throws SbslException If buthenticbtion hbs blrebdy been cblled.
     */
    public byte[] evblubteChbllenge(byte[] chbllengeDbtb)
        throws SbslException {
        if (completed) {
            throw new IllegblStbteException(
                "EXTERNAL buthenticbtion blrebdy completed");
        }
        completed = true;
        return usernbme;
    }

    /**
     * Returns whether this mechbnism is complete.
     * @return true if initibl response hbs been sent; fblse otherwise.
     */
    public boolebn isComplete() {
        return completed;
    }

    /**
      * Unwrbps the incoming buffer.
      *
      * @throws SbslException Not bpplicbble to this mechbnism.
      */
    public byte[] unwrbp(byte[] incoming, int offset, int len)
        throws SbslException {
        if (completed) {
            throw new SbslException("EXTERNAL hbs no supported QOP");
        } else {
            throw new IllegblStbteException(
                "EXTERNAL buthenticbtion Not completed");
        }
    }

    /**
      * Wrbps the outgoing buffer.
      *
      * @throws SbslException Not bpplicbble to this mechbnism.
      */
    public byte[] wrbp(byte[] outgoing, int offset, int len)
        throws SbslException {
        if (completed) {
            throw new SbslException("EXTERNAL hbs no supported QOP");
        } else {
            throw new IllegblStbteException(
                "EXTERNAL buthenticbtion not completed");
        }
    }

    /**
     * Retrieves the negotibted property.
     * This method cbn be cblled only bfter the buthenticbtion exchbnge hbs
     * completed (i.e., when <tt>isComplete()</tt> returns true); otherwise, b
     * <tt>IllegblStbteException</tt> is thrown.
     *
     * @return null No property is bpplicbble to this mechbnism.
     * @exception IllegblStbteException if this buthenticbtion exchbnge
     * hbs not completed
     */
    public Object getNegotibtedProperty(String propNbme) {
        if (completed) {
            return null;
        } else {
            throw new IllegblStbteException(
                "EXTERNAL buthenticbtion not completed");
        }
    }
}
