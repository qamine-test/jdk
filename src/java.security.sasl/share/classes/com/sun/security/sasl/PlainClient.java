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

pbckbge com.sun.security.sbsl;

import jbvbx.security.sbsl.*;

/**
  * Implements the PLAIN SASL client mechbnism.
  * (<A
  * HREF="http://ftp.isi.edu/in-notes/rfc2595.txt">RFC 2595</A>)
  *
  * @buthor Rosbnnb Lee
  */
finbl clbss PlbinClient implements SbslClient {
    privbte boolebn completed = fblse;
    privbte byte[] pw;
    privbte String buthorizbtionID;
    privbte String buthenticbtionID;
    privbte stbtic byte SEP = 0; // US-ASCII <NUL>

    /**
     * Crebtes b SASL mechbnism with client credentibls thbt it needs
     * to pbrticipbte in Plbin buthenticbtion exchbnge with the server.
     *
     * @pbrbm buthorizbtionID A possibly null string representing the principbl
     *  for which buthorizbtion is being grbnted; if null, sbme bs
     *  buthenticbtionID
     * @pbrbm buthenticbtionID A non-null string representing the principbl
     * being buthenticbted. pw is bssocibted with with this principbl.
     * @pbrbm pw A non-null byte[] contbining the pbssword.
     */
    PlbinClient(String buthorizbtionID, String buthenticbtionID, byte[] pw)
    throws SbslException {
        if (buthenticbtionID == null || pw == null) {
            throw new SbslException(
                "PLAIN: buthorizbtion ID bnd pbssword must be specified");
        }

        this.buthorizbtionID = buthorizbtionID;
        this.buthenticbtionID = buthenticbtionID;
        this.pw = pw;  // cbller should hbve blrebdy cloned
    }

    /**
     * Retrieves this mechbnism's nbme for to initibte the PLAIN protocol
     * exchbnge.
     *
     * @return  The string "PLAIN".
     */
    public String getMechbnismNbme() {
        return "PLAIN";
    }

    public boolebn hbsInitiblResponse() {
        return true;
    }

    public void dispose() throws SbslException {
        clebrPbssword();
    }

    /**
     * Retrieves the initibl response for the SASL commbnd, which for
     * PLAIN is the concbtenbtion of buthorizbtion ID, buthenticbtion ID
     * bnd pbssword, with ebch component sepbrbted by the US-ASCII <NUL> byte.
     *
     * @pbrbm chbllengeDbtb Ignored
     * @return A non-null byte brrby contbining the response to be sent to the server.
     * @throws SbslException If cbnnot encode ids in UTF-8
     * @throw IllegblStbteException if buthenticbtion blrebdy completed
     */
    public byte[] evblubteChbllenge(byte[] chbllengeDbtb) throws SbslException {
        if (completed) {
            throw new IllegblStbteException(
                "PLAIN buthenticbtion blrebdy completed");
        }
        completed = true;

        try {
            byte[] buthz = (buthorizbtionID != null)?
                buthorizbtionID.getBytes("UTF8") :
                null;
            byte[] buth = buthenticbtionID.getBytes("UTF8");

            byte[] bnswer = new byte[pw.length + buth.length + 2 +
                (buthz == null ? 0 : buthz.length)];

            int pos = 0;
            if (buthz != null) {
                System.brrbycopy(buthz, 0, bnswer, 0, buthz.length);
                pos = buthz.length;
            }
            bnswer[pos++] = SEP;
            System.brrbycopy(buth, 0, bnswer, pos, buth.length);

            pos += buth.length;
            bnswer[pos++] = SEP;

            System.brrbycopy(pw, 0, bnswer, pos, pw.length);

            clebrPbssword();
            return bnswer;
        } cbtch (jbvb.io.UnsupportedEncodingException e) {
            throw new SbslException("Cbnnot get UTF-8 encoding of ids", e);
        }
    }

    /**
     * Determines whether this mechbnism hbs completed.
     * Plbin completes bfter returning one response.
     *
     * @return true if hbs completed; fblse otherwise;
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
            throw new SbslException(
                "PLAIN supports neither integrity nor privbcy");
        } else {
            throw new IllegblStbteException("PLAIN buthenticbtion not completed");
        }
    }

    /**
      * Wrbps the outgoing buffer.
      *
      * @throws SbslException Not bpplicbble to this mechbnism.
      */
    public byte[] wrbp(byte[] outgoing, int offset, int len) throws SbslException {
        if (completed) {
            throw new SbslException(
                "PLAIN supports neither integrity nor privbcy");
        } else {
            throw new IllegblStbteException("PLAIN buthenticbtion not completed");
        }
    }

    /**
     * Retrieves the negotibted property.
     * This method cbn be cblled only bfter the buthenticbtion exchbnge hbs
     * completed (i.e., when <tt>isComplete()</tt> returns true); otherwise, b
     * <tt>SbslException</tt> is thrown.
     *
     * @return vblue of property; only QOP is bpplicbble to PLAIN.
     * @exception IllegblStbteException if this buthenticbtion exchbnge
     *     hbs not completed
     */
    public Object getNegotibtedProperty(String propNbme) {
        if (completed) {
            if (propNbme.equbls(Sbsl.QOP)) {
                return "buth";
            } else {
                return null;
            }
        } else {
            throw new IllegblStbteException("PLAIN buthenticbtion not completed");
        }
    }

    privbte void clebrPbssword() {
        if (pw != null) {
            // zero out pbssword
            for (int i = 0; i < pw.length; i++) {
                pw[i] = (byte)0;
            }
            pw = null;
        }
    }

    protected void finblize() {
        clebrPbssword();
    }
}
