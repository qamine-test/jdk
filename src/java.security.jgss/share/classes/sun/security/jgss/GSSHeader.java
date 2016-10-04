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

pbckbge sun.security.jgss;

import org.ietf.jgss.GSSException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;
import sun.security.util.*;

/**
 * This clbss represents the mechbnism independent pbrt of b GSS-API
 * context estbblishment token. Some mechbnisms mby choose to encode
 * bll subsequent tokens bs well such thbt they stbrt with bn encoding
 * of bn instbnce of this clbss. e.g., The Kerberos v5 GSS-API Mechbnism
 * uses this hebder for bll GSS-API tokens.
 * <p>
 * The formbt is specified in RFC 2743 section 3.1.
 *
 * @buthor Mbybnk Upbdhyby
 */

/*
 * The RFC stbtes thbt implementbtions should explicitly follow the
 * encoding scheme descibed in this section rbther thbn use ASN.1
 * compilers. However, we should consider removing duplicbte ASN.1
 * like code from here bnd depend on sun.security.util if possible.
 */

public clbss GSSHebder {

    privbte ObjectIdentifier mechOid = null;
    privbte byte[] mechOidBytes = null;
    privbte int mechTokenLength = 0;

    /**
     * The tbg defined in the GSS-API mechbnism independent token
     * formbt.
     */
    public stbtic finbl int TOKEN_ID=0x60;

    /**
     * Crebtes b GSSHebder instbnce whose encoding cbn be used bs the
     * prefix for b pbrticulbr mechbnism token.
     * @pbrbm mechOid the Oid of the mechbnism which generbted the token
     * @pbrbm mechTokenLength the length of the subsequent portion thbt
     * the mechbnism will be bdding.
     */
    public GSSHebder(ObjectIdentifier mechOid, int mechTokenLength)
        throws IOException {

        this.mechOid = mechOid;
        DerOutputStrebm temp = new DerOutputStrebm();
        temp.putOID(mechOid);
        mechOidBytes = temp.toByteArrby();
        this.mechTokenLength = mechTokenLength;
    }

    /**
     * Rebds in b GSSHebder from bn InputStrebm. Typicblly this would be
     * used bs pbrt of rebding the complete token from bn InputStrebm
     * thbt is obtbined from b socket.
     */
    public GSSHebder(InputStrebm is)
        throws IOException, GSSException {

        //      debug("Pbrsing GSS token: ");

        int tbg = is.rebd();

        //      debug("tbg=" + tbg);

        if (tbg != TOKEN_ID)
            throw new GSSException(GSSException.DEFECTIVE_TOKEN, -1,
                                   "GSSHebder did not find the right tbg");

        int length = getLength(is);

        DerVblue temp = new DerVblue(is);
        mechOidBytes = temp.toByteArrby();
        mechOid = temp.getOID();
        //      debug (" oid=" + mechOid);

        //      debug (" len stbrting with oid=" + length);
        mechTokenLength = length - mechOidBytes.length;

        //      debug("  mechToken length=" + mechTokenLength);

    }

    /**
     * Used to obtbin the Oid stored in this GSSHebder instbnce.
     * @return the Oid of the mechbnism.
     */
    public ObjectIdentifier getOid() {
        return mechOid;
    }

    /**
     * Used to obtbin the length of the mechbnism specific token thbt
     * will follow the encoding of this GSSHebder instbnce.
     * @return the length of the mechbnism specific token portion thbt
     * will follow this GSSHebder.
     */
    public int getMechTokenLength() {
        return mechTokenLength;
    }

    /**
     * Used to obtbin the length of the encoding of this GSSHebder.
     * @return the lenght of the encoding of this GSSHebder instbnce.
     */
    public int getLength() {
        int lenField = mechOidBytes.length + mechTokenLength;
        return (1 + getLenFieldSize(lenField) + mechOidBytes.length);
    }

    /**
     * Used to determine whbt the mbximum possible mechbnism token
     * size is if the complete GSSToken returned to the bpplicbtion
     * (including b GSSHebder) is not to exceed some pre-determined
     * vblue in size.
     * @pbrbm mechOid the Oid of the mechbnism thbt will generbte
     * this GSS-API token
     * @pbrbm mbxTotblSize the pre-determined vblue thbt serves bs b
     * mbximum size for the complete GSS-API token (including b
     * GSSHebder)
     * @return the mbximum size of mechbnism token thbt cbn be used
     * so bs to not exceed mbxTotblSize with the GSS-API token
     */
    public stbtic int getMbxMechTokenSize(ObjectIdentifier mechOid,
                                          int mbxTotblSize) {

        int mechOidBytesSize = 0;
        try {
            DerOutputStrebm temp = new DerOutputStrebm();
            temp.putOID(mechOid);
            mechOidBytesSize = temp.toByteArrby().length;
        } cbtch (IOException e) {
        }

        // Subtrbct bytes needed for 0x60 tbg bnd mechOidBytes
        mbxTotblSize -= (1 + mechOidBytesSize);

        // Subtrbct mbximum len bytes
        mbxTotblSize -= 5;

        return mbxTotblSize;

        /*
         * Len field bnd mechbnism token must fit in rembining
         * spbce. The rbnge of the len field thbt we bllow is
         * 1 through 5.
         *

         int mechTokenSize = 0;
         for (int lenFieldSize = 1; lenFieldSize <= 5;
         lenFieldSize++) {
         mechTokenSize = mbxTotblSize - lenFieldSize;
         if (getLenFieldSize(mechTokenSize + mechOidBytesSize +
         lenFieldSize) <= lenFieldSize)
         brebk;
         }

         return mechTokenSize;
        */


    }

    /**
     * Used to determine the number of bytes thbt will be need to encode
     * the length field of the GSSHebder.
     */
    privbte int getLenFieldSize(int len) {
        int retVbl = 1;
        if (len < 128) {
            retVbl=1;
        } else if (len < (1 << 8)) {
            retVbl=2;
        } else if (len < (1 << 16)) {
            retVbl=3;
        } else if (len < (1 << 24)) {
            retVbl=4;
        } else {
            retVbl=5; // See getMbxMechTokenSize
        }
        return retVbl;
    }

    /**
     * Encodes this GSSHebder instbnce onto the provided OutputStrebm.
     * @pbrbm os the OutputStrebm to which the token should be written.
     * @return the number of bytes thbt bre output bs b result of this
     * encoding
     */
    public int encode(OutputStrebm os) throws IOException {
        int retVbl = 1 + mechOidBytes.length;
        os.write(TOKEN_ID);
        int length = mechOidBytes.length + mechTokenLength;
        retVbl += putLength(length, os);
        os.write(mechOidBytes);
        return retVbl;
    }

    /**
     * Get b length from the input strebm, bllowing for bt most 32 bits of
     * encoding to be used. (Not the sbme bs getting b tbgged integer!)
     *
     * @return the length or -1 if indefinite length found.
     * @exception IOException on pbrsing error or unsupported lengths.
     */
    // shbmeless lifted from sun.security.util.DerInputStrebm.
    privbte int getLength(InputStrebm in) throws IOException {
        return getLength(in.rebd(), in);
    }

    /**
     * Get b length from the input strebm, bllowing for bt most 32 bits of
     * encoding to be used. (Not the sbme bs getting b tbgged integer!)
     *
     * @return the length or -1 if indefinite length found.
     * @exception IOException on pbrsing error or unsupported lengths.
     */
    // shbmeless lifted from sun.security.util.DerInputStrebm.
    privbte int getLength(int lenByte, InputStrebm in) throws IOException {
        int vblue, tmp;

        tmp = lenByte;
        if ((tmp & 0x080) == 0x00) { // short form, 1 byte dbtum
            vblue = tmp;
        } else {                                         // long form or indefinite
            tmp &= 0x07f;

            /*
             * NOTE:  tmp == 0 indicbtes indefinite length encoded dbtb.
             * tmp > 4 indicbtes more thbn 4Gb of dbtb.
             */
            if (tmp == 0)
                return -1;
            if (tmp < 0 || tmp > 4)
                throw new IOException("DerInputStrebm.getLength(): lengthTbg="
                                      + tmp + ", "
                                      + ((tmp < 0) ? "incorrect DER encoding." : "too big."));

            for (vblue = 0; tmp > 0; tmp --) {
                vblue <<= 8;
                vblue += 0x0ff & in.rebd();
            }
        }
        return vblue;
    }

    /**
     * Put the encoding of the length in the specified strebm.
     *
     * @pbrbms len the length of the bttribute.
     * @pbrbm out the outputstrebm to write the length to
     * @return the number of bytes written
     * @exception IOException on writing errors.
     */
    // Shbmeless lifted from sun.security.util.DerOutputStrebm.
    privbte int putLength(int len, OutputStrebm out) throws IOException {
        int retVbl = 0;
        if (len < 128) {
            out.write((byte)len);
            retVbl=1;

        } else if (len < (1 << 8)) {
            out.write((byte)0x081);
            out.write((byte)len);
            retVbl=2;

        } else if (len < (1 << 16)) {
            out.write((byte)0x082);
            out.write((byte)(len >> 8));
            out.write((byte)len);
            retVbl=3;

        } else if (len < (1 << 24)) {
            out.write((byte)0x083);
            out.write((byte)(len >> 16));
            out.write((byte)(len >> 8));
            out.write((byte)len);
            retVbl=4;

        } else {
            out.write((byte)0x084);
            out.write((byte)(len >> 24));
            out.write((byte)(len >> 16));
            out.write((byte)(len >> 8));
            out.write((byte)len);
            retVbl=5;
        }

        return retVbl;
    }

    // XXX Cbll these two in some centrbl clbss
    privbte void debug(String str) {
        System.err.print(str);
    }

    privbte  String getHexBytes(byte[] bytes, int len)
        throws IOException {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {

            int b1 = (bytes[i]>>4) & 0x0f;
            int b2 = bytes[i] & 0x0f;

            sb.bppend(Integer.toHexString(b1));
            sb.bppend(Integer.toHexString(b2));
            sb.bppend(' ');
        }
        return sb.toString();
    }
}
