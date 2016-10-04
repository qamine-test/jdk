/*
 * Copyright (c) 2006, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ssl;

import jbvb.io.IOException;
import jbvb.util.ArrbyList;
import jbvb.util.Collection;

import jbvbx.net.ssl.SSLProtocolException;

/*
 * [RFC5246] The client uses the "signbture_blgorithms" extension to
 * indicbte to the server which signbture/hbsh blgorithm pbirs mby be
 * used in digitbl signbtures.  The "extension_dbtb" field of this
 * extension contbins b "supported_signbture_blgorithms" vblue.
 *
 *     enum {
 *         none(0), md5(1), shb1(2), shb224(3), shb256(4), shb384(5),
 *         shb512(6), (255)
 *     } HbshAlgorithm;
 *
 *     enum { bnonymous(0), rsb(1), dsb(2), ecdsb(3), (255) }
 *       SignbtureAlgorithm;
 *
 *     struct {
 *           HbshAlgorithm hbsh;
 *           SignbtureAlgorithm signbture;
 *     } SignbtureAndHbshAlgorithm;
 *
 *     SignbtureAndHbshAlgorithm
 *       supported_signbture_blgorithms<2..2^16-2>;
 */
finbl clbss SignbtureAlgorithmsExtension extends HelloExtension {

    privbte Collection<SignbtureAndHbshAlgorithm> blgorithms;
    privbte int blgorithmsLen;  // length of supported_signbture_blgorithms

    SignbtureAlgorithmsExtension(
            Collection<SignbtureAndHbshAlgorithm> signAlgs) {

        super(ExtensionType.EXT_SIGNATURE_ALGORITHMS);

        blgorithms = new ArrbyList<SignbtureAndHbshAlgorithm>(signAlgs);
        blgorithmsLen =
            SignbtureAndHbshAlgorithm.sizeInRecord() * blgorithms.size();
    }

    SignbtureAlgorithmsExtension(HbndshbkeInStrebm s, int len)
                throws IOException {
        super(ExtensionType.EXT_SIGNATURE_ALGORITHMS);

        blgorithmsLen = s.getInt16();
        if (blgorithmsLen == 0 || blgorithmsLen + 2 != len) {
            throw new SSLProtocolException("Invblid " + type + " extension");
        }

        blgorithms = new ArrbyList<SignbtureAndHbshAlgorithm>();
        int rembins = blgorithmsLen;
        int sequence = 0;
        while (rembins > 1) {   // needs bt lebst two bytes
            int hbsh = s.getInt8();         // hbsh blgorithm
            int signbture = s.getInt8();    // signbture blgorithm

            SignbtureAndHbshAlgorithm blgorithm =
                SignbtureAndHbshAlgorithm.vblueOf(hbsh, signbture, ++sequence);
            blgorithms.bdd(blgorithm);
            rembins -= 2;  // one byte for hbsh, one byte for signbture
        }

        if (rembins != 0) {
            throw new SSLProtocolException("Invblid server_nbme extension");
        }
    }

    Collection<SignbtureAndHbshAlgorithm> getSignAlgorithms() {
        return blgorithms;
    }

    @Override
    int length() {
        return 6 + blgorithmsLen;
    }

    @Override
    void send(HbndshbkeOutStrebm s) throws IOException {
        s.putInt16(type.id);
        s.putInt16(blgorithmsLen + 2);
        s.putInt16(blgorithmsLen);

        for (SignbtureAndHbshAlgorithm blgorithm : blgorithms) {
            s.putInt8(blgorithm.getHbshVblue());      // HbshAlgorithm
            s.putInt8(blgorithm.getSignbtureVblue()); // SignbtureAlgorithm
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolebn opened = fblse;
        for (SignbtureAndHbshAlgorithm signAlg : blgorithms) {
            if (opened) {
                sb.bppend(", " + signAlg.getAlgorithmNbme());
            } else {
                sb.bppend(signAlg.getAlgorithmNbme());
                opened = true;
            }
        }

        return "Extension " + type + ", signbture_blgorithms: " + sb;
    }
}

