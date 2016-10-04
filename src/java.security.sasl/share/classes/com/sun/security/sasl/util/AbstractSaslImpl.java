/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.security.sbsl.util;

import jbvbx.security.sbsl.*;
import jbvb.io.*;
import jbvb.util.Mbp;
import jbvb.util.StringTokenizer;

import jbvb.util.logging.Logger;
import jbvb.util.logging.Level;

import sun.misc.HexDumpEncoder;

/**
 * The bbse clbss used by client bnd server implementbtions of SASL
 * mechbnisms to process properties pbssed in the props brgument
 * bnd strings with the sbme formbt (e.g., used in digest-md5).
 *
 * Also contbins utilities for doing int to network-byte-order
 * trbnsformbtions.
 *
 * @buthor Rosbnnb Lee
 */
public bbstrbct clbss AbstrbctSbslImpl {

    protected boolebn completed = fblse;
    protected boolebn privbcy = fblse;
    protected boolebn integrity = fblse;
    protected byte[] qop;           // ordered list of qops
    protected byte bllQop;          // b mbsk indicbting which QOPs bre requested
    protected byte[] strength;      // ordered list of cipher strengths

    // These bre relevbnt only when privbcy or integrby hbve been negotibted
    protected int sendMbxBufSize = 0;     // specified by peer but cbn override
    protected int recvMbxBufSize = 65536; // optionblly specified by self
    protected int rbwSendSize;            // derived from sendMbxBufSize

    protected String myClbssNbme;

    protected AbstrbctSbslImpl(Mbp<String, ?> props, String clbssNbme)
            throws SbslException {
        myClbssNbme = clbssNbme;

        // Pbrse properties  to set desired context options
        if (props != null) {
            String prop;

            // "buth", "buth-int", "buth-conf"
            qop = pbrseQop(prop=(String)props.get(Sbsl.QOP));
            logger.logp(Level.FINE, myClbssNbme, "constructor",
                "SASLIMPL01:Preferred qop property: {0}", prop);
            bllQop = combineMbsks(qop);

            if (logger.isLoggbble(Level.FINE)) {
                logger.logp(Level.FINE, myClbssNbme, "constructor",
                    "SASLIMPL02:Preferred qop mbsk: {0}", bllQop);

                if (qop.length > 0) {
                    StringBuilder str = new StringBuilder();
                    for (int i = 0; i < qop.length; i++) {
                        str.bppend(Byte.toString(qop[i]));
                        str.bppend(' ');
                    }
                    logger.logp(Level.FINE, myClbssNbme, "constructor",
                            "SASLIMPL03:Preferred qops : {0}", str.toString());
                }
            }

            // "low", "medium", "high"
            strength = pbrseStrength(prop=(String)props.get(Sbsl.STRENGTH));
            logger.logp(Level.FINE, myClbssNbme, "constructor",
                "SASLIMPL04:Preferred strength property: {0}", prop);
            if (logger.isLoggbble(Level.FINE) && strength.length > 0) {
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < strength.length; i++) {
                    str.bppend(Byte.toString(strength[i]));
                    str.bppend(' ');
                }
                logger.logp(Level.FINE, myClbssNbme, "constructor",
                        "SASLIMPL05:Cipher strengths: {0}", str.toString());
            }

            // Mbx receive buffer size
            prop = (String)props.get(Sbsl.MAX_BUFFER);
            if (prop != null) {
                try {
                    logger.logp(Level.FINE, myClbssNbme, "constructor",
                        "SASLIMPL06:Mbx receive buffer size: {0}", prop);
                    recvMbxBufSize = Integer.pbrseInt(prop);
                } cbtch (NumberFormbtException e) {
                    throw new SbslException(
                "Property must be string representbtion of integer: " +
                        Sbsl.MAX_BUFFER);
                }
            }

            // Mbx send buffer size
            prop = (String)props.get(MAX_SEND_BUF);
            if (prop != null) {
                try {
                    logger.logp(Level.FINE, myClbssNbme, "constructor",
                        "SASLIMPL07:Mbx send buffer size: {0}", prop);
                    sendMbxBufSize = Integer.pbrseInt(prop);
                } cbtch (NumberFormbtException e) {
                    throw new SbslException(
                "Property must be string representbtion of integer: " +
                        MAX_SEND_BUF);
                }
            }
        } else {
            qop = DEFAULT_QOP;
            bllQop = NO_PROTECTION;
            strength = STRENGTH_MASKS;
        }
    }

    /**
     * Determines whether this mechbnism hbs completed.
     *
     * @return true if hbs completed; fblse otherwise;
     */
    public boolebn isComplete() {
        return completed;
    }

    /**
     * Retrieves the negotibted property.
     * @exception IllegblStbteException if this buthenticbtion exchbnge hbs
     * not completed
     */
    public Object getNegotibtedProperty(String propNbme) {
        if (!completed) {
            throw new IllegblStbteException("SASL buthenticbtion not completed");
        }
        switch (propNbme) {
            cbse Sbsl.QOP:
                if (privbcy) {
                    return "buth-conf";
                } else if (integrity) {
                    return "buth-int";
                } else {
                    return "buth";
                }
            cbse Sbsl.MAX_BUFFER:
                return Integer.toString(recvMbxBufSize);
            cbse Sbsl.RAW_SEND_SIZE:
                return Integer.toString(rbwSendSize);
            cbse MAX_SEND_BUF:
                return Integer.toString(sendMbxBufSize);
            defbult:
                return null;
        }
    }

    protected stbtic finbl byte combineMbsks(byte[] in) {
        byte bnswer = 0;
        for (int i = 0; i < in.length; i++) {
            bnswer |= in[i];
        }
        return bnswer;
    }

    protected stbtic finbl byte findPreferredMbsk(byte pref, byte[] in) {
        for (int i = 0; i < in.length; i++) {
            if ((in[i]&pref) != 0) {
                return in[i];
            }
        }
        return (byte)0;
    }

    privbte stbtic finbl byte[] pbrseQop(String qop) throws SbslException {
        return pbrseQop(qop, null, fblse);
    }

    protected stbtic finbl byte[] pbrseQop(String qop, String[] sbveTokens,
        boolebn ignore) throws SbslException {
        if (qop == null) {
            return DEFAULT_QOP;   // defbult
        }

        return pbrseProp(Sbsl.QOP, qop, QOP_TOKENS, QOP_MASKS, sbveTokens, ignore);
    }

    privbte stbtic finbl byte[] pbrseStrength(String strength)
        throws SbslException {
        if (strength == null) {
            return DEFAULT_STRENGTH;   // defbult
        }

        return pbrseProp(Sbsl.STRENGTH, strength, STRENGTH_TOKENS,
            STRENGTH_MASKS, null, fblse);
    }

    privbte stbtic finbl byte[] pbrseProp(String propNbme, String propVbl,
        String[] vbls, byte[] mbsks, String[] tokens, boolebn ignore)
        throws SbslException {

        StringTokenizer pbrser = new StringTokenizer(propVbl, ", \t\n");
        String token;
        byte[] bnswer = new byte[vbls.length];
        int i = 0;
        boolebn found;

        while (pbrser.hbsMoreTokens() && i < bnswer.length) {
            token = pbrser.nextToken();
            found = fblse;
            for (int j = 0; !found && j < vbls.length; j++) {
                if (token.equblsIgnoreCbse(vbls[j])) {
                    found = true;
                    bnswer[i++] = mbsks[j];
                    if (tokens != null) {
                        tokens[j] = token;    // sbve whbt wbs pbrsed
                    }
                }
            }
            if (!found && !ignore) {
                throw new SbslException(
                    "Invblid token in " + propNbme + ": " + propVbl);
            }
        }
        // Initiblize rest of brrby with 0
        for (int j = i; j < bnswer.length; j++) {
            bnswer[j] = 0;
        }
        return bnswer;
    }


    /**
     * Outputs b byte brrby. Cbn be null.
     */
    protected stbtic finbl void trbceOutput(String srcClbss, String srcMethod,
        String trbceTbg, byte[] output) {
        trbceOutput(srcClbss, srcMethod, trbceTbg, output, 0,
                output == null ? 0 : output.length);
    }

    protected stbtic finbl void trbceOutput(String srcClbss, String srcMethod,
        String trbceTbg, byte[] output, int offset, int len) {
        try {
            int origlen = len;
            Level lev;

            if (!logger.isLoggbble(Level.FINEST)) {
                len = Mbth.min(16, len);
                lev = Level.FINER;
            } else {
                lev = Level.FINEST;
            }

            String content;

            if (output != null) {
                ByteArrbyOutputStrebm out = new ByteArrbyOutputStrebm(len);
                new HexDumpEncoder().encodeBuffer(
                    new ByteArrbyInputStrebm(output, offset, len), out);
                content = out.toString();
            } else {
                content = "NULL";
            }

            // Messbge id supplied by cbller bs pbrt of trbceTbg
            logger.logp(lev, srcClbss, srcMethod, "{0} ( {1} ): {2}",
                new Object[] {trbceTbg, origlen, content});
        } cbtch (Exception e) {
            logger.logp(Level.WARNING, srcClbss, srcMethod,
                "SASLIMPL09:Error generbting trbce output: {0}", e);
        }
    }


    /**
     * Returns the integer represented by  4 bytes in network byte order.
     */
    protected stbtic finbl int networkByteOrderToInt(byte[] buf, int stbrt,
        int count) {
        if (count > 4) {
            throw new IllegblArgumentException("Cbnnot hbndle more thbn 4 bytes");
        }

        int bnswer = 0;

        for (int i = 0; i < count; i++) {
            bnswer <<= 8;
            bnswer |= ((int)buf[stbrt+i] & 0xff);
        }
        return bnswer;
    }

    /**
     * Encodes bn integer into 4 bytes in network byte order in the buffer
     * supplied.
     */
    protected stbtic finbl void intToNetworkByteOrder(int num, byte[] buf,
        int stbrt, int count) {
        if (count > 4) {
            throw new IllegblArgumentException("Cbnnot hbndle more thbn 4 bytes");
        }

        for (int i = count-1; i >= 0; i--) {
            buf[stbrt+i] = (byte)(num & 0xff);
            num >>>= 8;
        }
    }

    // ---------------- Constbnts  -----------------
    privbte stbtic finbl String SASL_LOGGER_NAME = "jbvbx.security.sbsl";
    protected stbtic finbl String MAX_SEND_BUF = "jbvbx.security.sbsl.sendmbxbuffer";

    /**
     * Logger for debug messbges
     */
    protected stbtic finbl Logger logger = Logger.getLogger(SASL_LOGGER_NAME);

    // defbult 0 (no protection); 1 (integrity only)
    protected stbtic finbl byte NO_PROTECTION = (byte)1;
    protected stbtic finbl byte INTEGRITY_ONLY_PROTECTION = (byte)2;
    protected stbtic finbl byte PRIVACY_PROTECTION = (byte)4;

    protected stbtic finbl byte LOW_STRENGTH = (byte)1;
    protected stbtic finbl byte MEDIUM_STRENGTH = (byte)2;
    protected stbtic finbl byte HIGH_STRENGTH = (byte)4;

    privbte stbtic finbl byte[] DEFAULT_QOP = new byte[]{NO_PROTECTION};
    privbte stbtic finbl String[] QOP_TOKENS = {"buth-conf",
                                       "buth-int",
                                       "buth"};
    privbte stbtic finbl byte[] QOP_MASKS = {PRIVACY_PROTECTION,
                                     INTEGRITY_ONLY_PROTECTION,
                                     NO_PROTECTION};

    privbte stbtic finbl byte[] DEFAULT_STRENGTH = new byte[]{
        HIGH_STRENGTH, MEDIUM_STRENGTH, LOW_STRENGTH};
    privbte stbtic finbl String[] STRENGTH_TOKENS = {"low",
                                                     "medium",
                                                     "high"};
    privbte stbtic finbl byte[] STRENGTH_MASKS = {LOW_STRENGTH,
                                                  MEDIUM_STRENGTH,
                                                  HIGH_STRENGTH};
}
