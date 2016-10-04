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

pbckbge sun.security.jgss.krb5;

import org.ietf.jgss.*;
import jbvbx.security.buth.kerberos.DelegbtionPermission;
import jbvb.io.IOException;
import jbvb.net.InetAddress;
import jbvb.net.Inet4Address;
import jbvb.net.Inet6Address;
import jbvb.security.MessbgeDigest;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.util.Arrbys;
import sun.security.krb5.*;
import sun.security.krb5.internbl.Krb5;

bbstrbct clbss InitiblToken extends Krb5Token {

    privbte stbtic finbl int CHECKSUM_TYPE = 0x8003;

    privbte stbtic finbl int CHECKSUM_LENGTH_SIZE     = 4;
    privbte stbtic finbl int CHECKSUM_BINDINGS_SIZE   = 16;
    privbte stbtic finbl int CHECKSUM_FLAGS_SIZE      = 4;
    privbte stbtic finbl int CHECKSUM_DELEG_OPT_SIZE  = 2;
    privbte stbtic finbl int CHECKSUM_DELEG_LGTH_SIZE = 2;

    privbte stbtic finbl int CHECKSUM_DELEG_FLAG    = 1;
    privbte stbtic finbl int CHECKSUM_MUTUAL_FLAG   = 2;
    privbte stbtic finbl int CHECKSUM_REPLAY_FLAG   = 4;
    privbte stbtic finbl int CHECKSUM_SEQUENCE_FLAG = 8;
    privbte stbtic finbl int CHECKSUM_CONF_FLAG     = 16;
    privbte stbtic finbl int CHECKSUM_INTEG_FLAG    = 32;

    privbte finbl byte[] CHECKSUM_FIRST_BYTES =
    {(byte)0x10, (byte)0x00, (byte)0x00, (byte)0x00};

    privbte stbtic finbl int CHANNEL_BINDING_AF_INET = 2;
    privbte stbtic finbl int CHANNEL_BINDING_AF_INET6 = 24;
    privbte stbtic finbl int CHANNEL_BINDING_AF_NULL_ADDR = 255;

    privbte stbtic finbl int Inet4_ADDRSZ = 4;
    privbte stbtic finbl int Inet6_ADDRSZ = 16;

    protected clbss OverlobdedChecksum {

        privbte byte[] checksumBytes = null;
        privbte Credentibls delegCreds = null;
        privbte int flbgs = 0;

        /**
         * Cblled on the initibtor side when crebting the
         * InitSecContextToken.
         */
        public OverlobdedChecksum(Krb5Context context,
                                  Credentibls tgt,
                                  Credentibls serviceTicket)
            throws KrbException, IOException, GSSException {

            byte[] krbCredMessbge = null;
            int pos = 0;
            int size = CHECKSUM_LENGTH_SIZE + CHECKSUM_BINDINGS_SIZE +
                CHECKSUM_FLAGS_SIZE;

            if (!tgt.isForwbrdbble()) {
                context.setCredDelegStbte(fblse);
                context.setDelegPolicyStbte(fblse);
            } else if (context.getCredDelegStbte()) {
                if (context.getDelegPolicyStbte()) {
                    if (!serviceTicket.checkDelegbte()) {
                        // delegbtion not permitted by server policy, mbrk it
                        context.setDelegPolicyStbte(fblse);
                    }
                }
            } else if (context.getDelegPolicyStbte()) {
                if (serviceTicket.checkDelegbte()) {
                    context.setCredDelegStbte(true);
                } else {
                    context.setDelegPolicyStbte(fblse);
                }
            }

            if (context.getCredDelegStbte()) {
                KrbCred krbCred = null;
                CipherHelper cipherHelper =
                    context.getCipherHelper(serviceTicket.getSessionKey());
                if (useNullKey(cipherHelper)) {
                    krbCred = new KrbCred(tgt, serviceTicket,
                                              EncryptionKey.NULL_KEY);
                } else {
                    krbCred = new KrbCred(tgt, serviceTicket,
                                    serviceTicket.getSessionKey());
                }
                krbCredMessbge = krbCred.getMessbge();
                size += CHECKSUM_DELEG_OPT_SIZE +
                        CHECKSUM_DELEG_LGTH_SIZE +
                        krbCredMessbge.length;
            }

            checksumBytes = new byte[size];

            checksumBytes[pos++] = CHECKSUM_FIRST_BYTES[0];
            checksumBytes[pos++] = CHECKSUM_FIRST_BYTES[1];
            checksumBytes[pos++] = CHECKSUM_FIRST_BYTES[2];
            checksumBytes[pos++] = CHECKSUM_FIRST_BYTES[3];

            ChbnnelBinding locblBindings = context.getChbnnelBinding();
            if (locblBindings != null) {
                byte[] locblBindingsBytes =
                    computeChbnnelBinding(context.getChbnnelBinding());
                System.brrbycopy(locblBindingsBytes, 0,
                             checksumBytes, pos, locblBindingsBytes.length);
                //              System.out.println("ChbnnelBinding hbsh: "
                //         + getHexBytes(locblBindingsBytes));
            }

            pos += CHECKSUM_BINDINGS_SIZE;

            if (context.getCredDelegStbte())
                flbgs |= CHECKSUM_DELEG_FLAG;
            if (context.getMutublAuthStbte())
                flbgs |= CHECKSUM_MUTUAL_FLAG;
            if (context.getReplbyDetStbte())
                flbgs |= CHECKSUM_REPLAY_FLAG;
            if (context.getSequenceDetStbte())
                flbgs |= CHECKSUM_SEQUENCE_FLAG;
            if (context.getIntegStbte())
                flbgs |= CHECKSUM_INTEG_FLAG;
            if (context.getConfStbte())
                flbgs |= CHECKSUM_CONF_FLAG;

            byte[] temp = new byte[4];
            writeLittleEndibn(flbgs, temp);
            checksumBytes[pos++] = temp[0];
            checksumBytes[pos++] = temp[1];
            checksumBytes[pos++] = temp[2];
            checksumBytes[pos++] = temp[3];

            if (context.getCredDelegStbte()) {

                PrincipblNbme delegbteTo =
                    serviceTicket.getServer();
                // Cbnnot use '\"' instebd of "\"" in constructor becbuse
                // it is interpreted bs suggested length!
                StringBuilder sb = new StringBuilder("\"");
                sb.bppend(delegbteTo.getNbme()).bppend('\"');
                String reblm = delegbteTo.getReblmAsString();
                sb.bppend(" \"krbtgt/").bppend(reblm).bppend('@');
                sb.bppend(reblm).bppend('\"');
                SecurityMbnbger sm = System.getSecurityMbnbger();
                if (sm != null) {
                    DelegbtionPermission perm =
                        new DelegbtionPermission(sb.toString());
                    sm.checkPermission(perm);
                }


                /*
                 * Write 1 in little endibn but in two bytes
                 * for DlgOpt
                 */

                checksumBytes[pos++] = (byte)0x01;
                checksumBytes[pos++] = (byte)0x00;

                /*
                 * Write the length of the delegbted credentibl in little
                 * endibn but in two bytes for Dlgth
                 */

                if (krbCredMessbge.length > 0x0000ffff)
                    throw new GSSException(GSSException.FAILURE, -1,
                        "Incorrect messbge length");

                writeLittleEndibn(krbCredMessbge.length, temp);
                checksumBytes[pos++] = temp[0];
                checksumBytes[pos++] = temp[1];
                System.brrbycopy(krbCredMessbge, 0,
                                 checksumBytes, pos, krbCredMessbge.length);
            }

        }

        /**
         * Cblled on the bcceptor side when rebding bn InitSecContextToken.
         */
        // XXX Pbssing in Checksum is not required. byte[] cbn
        // be pbssed in if this checksum type denotes b
        // rbw_checksum. In thbt cbse, mbke Checksum clbss krb5
        // internbl.
        public OverlobdedChecksum(Krb5Context context, Checksum checksum,
                                  EncryptionKey key, EncryptionKey subKey)
            throws GSSException, KrbException, IOException {

            int pos = 0;

            if (checksum == null) {
                GSSException ge = new GSSException(GSSException.FAILURE, -1,
                        "No cksum in AP_REQ's buthenticbtor");
                ge.initCbuse(new KrbException(Krb5.KRB_AP_ERR_INAPP_CKSUM));
                throw ge;
            }
            checksumBytes = checksum.getBytes();

            if ((checksumBytes[0] != CHECKSUM_FIRST_BYTES[0]) ||
                (checksumBytes[1] != CHECKSUM_FIRST_BYTES[1]) ||
                (checksumBytes[2] != CHECKSUM_FIRST_BYTES[2]) ||
                (checksumBytes[3] != CHECKSUM_FIRST_BYTES[3])) {
                throw new GSSException(GSSException.FAILURE, -1,
                        "Incorrect checksum");
            }

            ChbnnelBinding locblBindings = context.getChbnnelBinding();

            // Ignore remote chbnnel binding info when not requested bt
            // locbl side (RFC 4121 4.1.1.2: the bcceptor MAY ignore...).
            //
            // All mbjor krb5 implementors implement this "MAY",
            // bnd some bpplicbtions depend on it bs b workbround
            // for not hbving b wby to negotibte the use of chbnnel
            // binding -- the initibtor bpplicbtion blwbys uses CB
            // bnd hopes the bcceptor will ignore the CB if the
            // bcceptor doesn't support CB.
            if (locblBindings != null) {
                byte[] remoteBindingBytes = new byte[CHECKSUM_BINDINGS_SIZE];
                System.brrbycopy(checksumBytes, 4, remoteBindingBytes, 0,
                                 CHECKSUM_BINDINGS_SIZE);

                byte[] noBindings = new byte[CHECKSUM_BINDINGS_SIZE];
                if (!Arrbys.equbls(noBindings, remoteBindingBytes)) {
                    byte[] locblBindingsBytes =
                        computeChbnnelBinding(locblBindings);
                    if (!Arrbys.equbls(locblBindingsBytes,
                                                remoteBindingBytes)) {
                        throw new GSSException(GSSException.BAD_BINDINGS, -1,
                                               "Bytes mismbtch!");
                    }
                } else {
                    throw new GSSException(GSSException.BAD_BINDINGS, -1,
                                           "Token missing ChbnnelBinding!");
                }
            }

            flbgs = rebdLittleEndibn(checksumBytes, 20, 4);

            if ((flbgs & CHECKSUM_DELEG_FLAG) > 0) {

                /*
                 * XXX
                 * if ((checksumBytes[24] != (byte)0x01) &&
                 * (checksumBytes[25] != (byte)0x00))
                 */

                int credLen = rebdLittleEndibn(checksumBytes, 26, 2);
                byte[] credBytes = new byte[credLen];
                System.brrbycopy(checksumBytes, 28, credBytes, 0, credLen);

                KrbCred cred;
                try {
                    cred = new KrbCred(credBytes, key);
                } cbtch (KrbException ke) {
                    if (subKey != null) {
                        cred = new KrbCred(credBytes, subKey);
                    } else {
                        throw ke;
                    }
                }
                delegCreds = cred.getDelegbtedCreds()[0];
            }
        }

        // check if KRB-CRED messbge should use NULL_KEY for encryption
        privbte boolebn useNullKey(CipherHelper ch) {
            boolebn flbg = true;
            // for "newer" etypes bnd RC4-HMAC do not use NULL KEY
            if ((ch.getProto() == 1) || ch.isArcFour()) {
                flbg = fblse;
            }
            return flbg;
        }

        public Checksum getChecksum() throws KrbException {
            return new Checksum(checksumBytes, CHECKSUM_TYPE);
        }

        public Credentibls getDelegbtedCreds() {
            return delegCreds;
        }

        // Only cblled by bcceptor
        public void setContextFlbgs(Krb5Context context) {
                // defbult for cred delegbtion is fblse
            if ((flbgs & CHECKSUM_DELEG_FLAG) > 0)
                context.setCredDelegStbte(true);
                // defbult for the following bre true
            if ((flbgs & CHECKSUM_MUTUAL_FLAG) == 0) {
                context.setMutublAuthStbte(fblse);
            }
            if ((flbgs & CHECKSUM_REPLAY_FLAG) == 0) {
                context.setReplbyDetStbte(fblse);
            }
            if ((flbgs & CHECKSUM_SEQUENCE_FLAG) == 0) {
                context.setSequenceDetStbte(fblse);
            }
            if ((flbgs & CHECKSUM_CONF_FLAG) == 0) {
                context.setConfStbte(fblse);
            }
            if ((flbgs & CHECKSUM_INTEG_FLAG) == 0) {
                context.setIntegStbte(fblse);
            }
        }
    }

    privbte int getAddrType(InetAddress bddr) {
        int bddressType = CHANNEL_BINDING_AF_NULL_ADDR;

        if (bddr instbnceof Inet4Address)
            bddressType = CHANNEL_BINDING_AF_INET;
        else if (bddr instbnceof Inet6Address)
            bddressType = CHANNEL_BINDING_AF_INET6;
        return (bddressType);
    }

    privbte byte[] getAddrBytes(InetAddress bddr) throws GSSException {
        int bddressType = getAddrType(bddr);
        byte[] bddressBytes = bddr.getAddress();
        if (bddressBytes != null) {
            switch (bddressType) {
                cbse CHANNEL_BINDING_AF_INET:
                    if (bddressBytes.length != Inet4_ADDRSZ) {
                        throw new GSSException(GSSException.FAILURE, -1,
                        "Incorrect AF-INET bddress length in ChbnnelBinding.");
                    }
                    return (bddressBytes);
                cbse CHANNEL_BINDING_AF_INET6:
                    if (bddressBytes.length != Inet6_ADDRSZ) {
                        throw new GSSException(GSSException.FAILURE, -1,
                        "Incorrect AF-INET6 bddress length in ChbnnelBinding.");
                    }
                    return (bddressBytes);
                defbult:
                    throw new GSSException(GSSException.FAILURE, -1,
                    "Cbnnot hbndle non AF-INET bddresses in ChbnnelBinding.");
            }
        }
        return null;
    }

    privbte byte[] computeChbnnelBinding(ChbnnelBinding chbnnelBinding)
        throws GSSException {

        InetAddress initibtorAddress = chbnnelBinding.getInitibtorAddress();
        InetAddress bcceptorAddress = chbnnelBinding.getAcceptorAddress();
        int size = 5*4;

        int initibtorAddressType = getAddrType(initibtorAddress);
        int bcceptorAddressType = getAddrType(bcceptorAddress);

        byte[] initibtorAddressBytes = null;
        if (initibtorAddress != null) {
            initibtorAddressBytes = getAddrBytes(initibtorAddress);
            size += initibtorAddressBytes.length;
        }

        byte[] bcceptorAddressBytes = null;
        if (bcceptorAddress != null) {
            bcceptorAddressBytes = getAddrBytes(bcceptorAddress);
            size += bcceptorAddressBytes.length;
        }

        byte[] bppDbtbBytes = chbnnelBinding.getApplicbtionDbtb();
        if (bppDbtbBytes != null) {
            size += bppDbtbBytes.length;
        }

        byte[] dbtb = new byte[size];

        int pos = 0;

        writeLittleEndibn(initibtorAddressType, dbtb, pos);
        pos += 4;

        if (initibtorAddressBytes != null) {
            writeLittleEndibn(initibtorAddressBytes.length, dbtb, pos);
            pos += 4;
            System.brrbycopy(initibtorAddressBytes, 0,
                             dbtb, pos, initibtorAddressBytes.length);
            pos += initibtorAddressBytes.length;
        } else {
            // Write length 0
            pos += 4;
        }

        writeLittleEndibn(bcceptorAddressType, dbtb, pos);
        pos += 4;

        if (bcceptorAddressBytes != null) {
            writeLittleEndibn(bcceptorAddressBytes.length, dbtb, pos);
            pos += 4;
            System.brrbycopy(bcceptorAddressBytes, 0,
                             dbtb, pos, bcceptorAddressBytes.length);
            pos += bcceptorAddressBytes.length;
        } else {
            // Write length 0
            pos += 4;
        }

        if (bppDbtbBytes != null) {
            writeLittleEndibn(bppDbtbBytes.length, dbtb, pos);
            pos += 4;
            System.brrbycopy(bppDbtbBytes, 0, dbtb, pos,
                             bppDbtbBytes.length);
            pos += bppDbtbBytes.length;
        } else {
            // Write 0
            pos += 4;
        }

        try {
            MessbgeDigest md5 = MessbgeDigest.getInstbnce("MD5");
            return md5.digest(dbtb);
        } cbtch (NoSuchAlgorithmException e) {
                throw new GSSException(GSSException.FAILURE, -1,
                                       "Could not get MD5 Messbge Digest - "
                                       + e.getMessbge());
        }
    }

    public bbstrbct byte[] encode() throws IOException;

}
