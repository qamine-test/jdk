/*
 * Copyright (c) 2004, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;
import jbvb.io.ByteArrbyOutputStrebm;

/**
 * This clbss represents the new formbt of GSS MIC tokens, bs specified
 * in RFC 4121
 *
 * MIC tokens = { 16-byte token-hebder |  HMAC }
 * where HMAC is on { plbintext | 16-byte token-hebder }
 *
 * @buthor Seemb Mblkbni
 */

clbss MicToken_v2 extends MessbgeToken_v2 {

    public MicToken_v2(Krb5Context context,
                  byte[] tokenBytes, int tokenOffset, int tokenLen,
                  MessbgeProp prop)  throws GSSException {
        super(Krb5Token.MIC_ID_v2, context,
                tokenBytes, tokenOffset, tokenLen, prop);
    }

    public MicToken_v2(Krb5Context context, InputStrebm is, MessbgeProp prop)
            throws GSSException {
        super(Krb5Token.MIC_ID_v2, context, is, prop);
    }

    public void verify(byte[] dbtb, int offset, int len) throws GSSException {
        if (!verifySign(dbtb, offset, len))
            throw new GSSException(GSSException.BAD_MIC, -1,
                         "Corrupt checksum or sequence number in MIC token");
    }

    public void verify(InputStrebm dbtb) throws GSSException {
        byte[] dbtbBytes = null;
        try {
            dbtbBytes = new byte[dbtb.bvbilbble()];
            dbtb.rebd(dbtbBytes);
        } cbtch (IOException e) {
            // Error rebding bpplicbtion dbtb
            throw new GSSException(GSSException.BAD_MIC, -1,
                "Corrupt checksum or sequence number in MIC token");
        }
        verify(dbtbBytes, 0, dbtbBytes.length);
    }

    public MicToken_v2(Krb5Context context, MessbgeProp prop,
                  byte[] dbtb, int pos, int len)
            throws GSSException {
        super(Krb5Token.MIC_ID_v2, context);

        //      debug("Applicbtion dbtb to MicToken verify is [" +
        //            getHexBytes(dbtb, pos, len) + "]\n");
        if (prop == null) prop = new MessbgeProp(0, fblse);
        genSignAndSeqNumber(prop, dbtb, pos, len);
    }

    public MicToken_v2(Krb5Context context, MessbgeProp prop, InputStrebm dbtb)
            throws GSSException, IOException {

        super(Krb5Token.MIC_ID_v2, context);
        byte[] dbtbBytes = new byte[dbtb.bvbilbble()];
        dbtb.rebd(dbtbBytes);

        // debug("Applicbtion dbtb to MicToken cons is [" +
        //     getHexBytes(dbtbBytes) + "]\n");
        if (prop == null) prop = new MessbgeProp(0, fblse);
        genSignAndSeqNumber(prop, dbtbBytes, 0, dbtbBytes.length);
    }

    public byte[] encode() throws IOException {
        // XXX Fine tune this initibl size
        ByteArrbyOutputStrebm bos = new ByteArrbyOutputStrebm(50);
        encode(bos);
        return bos.toByteArrby();
    }

    public int encode(byte[] outToken, int offset) throws IOException {
        byte[] token = encode();
        System.brrbycopy(token, 0, outToken, offset, token.length);
        return token.length;
    }

    public void encode(OutputStrebm os) throws IOException {
        encodeHebder(os);
        os.write(checksum);
    }
}
