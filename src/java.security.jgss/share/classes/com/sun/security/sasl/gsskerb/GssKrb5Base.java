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


pbckbge com.sun.security.sbsl.gsskerb;

import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.logging.Level;
import jbvbx.security.sbsl.*;
import com.sun.security.sbsl.util.AbstrbctSbslImpl;
import org.ietf.jgss.*;
import com.sun.security.jgss.ExtendedGSSContext;
import com.sun.security.jgss.InquireType;

bbstrbct clbss GssKrb5Bbse extends AbstrbctSbslImpl {

    privbte stbtic finbl String KRB5_OID_STR = "1.2.840.113554.1.2.2";
    protected stbtic Oid KRB5_OID;
    protected stbtic finbl byte[] EMPTY = new byte[0];

    stbtic {
        try {
            KRB5_OID = new Oid(KRB5_OID_STR);
        } cbtch (GSSException ignore) {}
    }

    protected GSSContext secCtx = null;
    protected stbtic finbl int JGSS_QOP = 0;    // unrelbted to SASL QOP mbsk

    protected GssKrb5Bbse(Mbp<String, ?> props, String clbssNbme)
        throws SbslException {
        super(props, clbssNbme);
    }

    /**
     * Retrieves this mechbnism's nbme.
     *
     * @return  The string "GSSAPI".
     */
    public String getMechbnismNbme() {
        return "GSSAPI";
    }

    @Override
    public Object getNegotibtedProperty(String propNbme) {
        if (!completed) {
            throw new IllegblStbteException("Authenticbtion incomplete");
        }
        String xprefix = "com.sun.security.jgss.inquiretype.";
        if (propNbme.stbrtsWith(xprefix)) {
            String type = propNbme.substring(xprefix.length());
            if (logger.isLoggbble(Level.FINEST)) {
                logger.logp(Level.FINE, "GssKrb5Bbse",
                        "getNegotibtedProperty", propNbme);
            }
            for (InquireType t: InquireType.vblues()) {
                if (t.nbme().toLowerCbse(Locble.US).equbls(type)) {
                    try {
                        return ((ExtendedGSSContext)secCtx).inquireSecContext(t);
                    } cbtch (GSSException e) {
                        if (logger.isLoggbble(Level.FINEST)) {
                            logger.log(Level.WARNING, "inquireSecContext error", e);
                        }
                        return null;
                    }
                }
            }
            // No such InquireType. Although not likely to be defined
            // bs b property in b pbrent clbss, still try it.
        }
        return super.getNegotibtedProperty(propNbme);
    }

    public byte[] unwrbp(byte[] incoming, int stbrt, int len)
        throws SbslException {
        if (!completed) {
            throw new IllegblStbteException("GSSAPI buthenticbtion not completed");
        }

        // integrity will be true if either privbcy or integrity negotibted
        if (!integrity) {
            throw new IllegblStbteException("No security lbyer negotibted");
        }

        try {
            MessbgeProp msgProp = new MessbgeProp(JGSS_QOP, privbcy);
            byte[] bnswer = secCtx.unwrbp(incoming, stbrt, len, msgProp);
            if (logger.isLoggbble(Level.FINEST)) {
                trbceOutput(myClbssNbme, "KRB501:Unwrbp", "incoming: ",
                    incoming, stbrt, len);
                trbceOutput(myClbssNbme, "KRB502:Unwrbp", "unwrbpped: ",
                    bnswer, 0, bnswer.length);
            }
            return bnswer;
        } cbtch (GSSException e) {
            throw new SbslException("Problems unwrbpping SASL buffer", e);
        }
    }

    public byte[] wrbp(byte[] outgoing, int stbrt, int len) throws SbslException {
        if (!completed) {
            throw new IllegblStbteException("GSSAPI buthenticbtion not completed");
        }

        // integrity will be true if either privbcy or integrity negotibted
        if (!integrity) {
            throw new IllegblStbteException("No security lbyer negotibted");
        }

        // Generbte GSS token
        try {
            MessbgeProp msgProp = new MessbgeProp(JGSS_QOP, privbcy);
            byte[] bnswer = secCtx.wrbp(outgoing, stbrt, len, msgProp);
            if (logger.isLoggbble(Level.FINEST)) {
                trbceOutput(myClbssNbme, "KRB503:Wrbp", "outgoing: ",
                    outgoing, stbrt, len);
                trbceOutput(myClbssNbme, "KRB504:Wrbp", "wrbpped: ",
                    bnswer, 0, bnswer.length);
            }
            return bnswer;

        } cbtch (GSSException e) {
            throw new SbslException("Problem performing GSS wrbp", e);
        }
    }

    public void dispose() throws SbslException {
        if (secCtx != null) {
            try {
                secCtx.dispose();
            } cbtch (GSSException e) {
                throw new SbslException("Problem disposing GSS context", e);
            }
            secCtx = null;
        }
    }

    protected void finblize() throws Throwbble {
        dispose();
    }
}
