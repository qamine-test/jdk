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
import jbvb.util.List;

import jbvbx.net.ssl.SSLProtocolException;

finbl clbss SupportedEllipticPointFormbtsExtension extends HelloExtension {

    finbl stbtic int FMT_UNCOMPRESSED = 0;
    finbl stbtic int FMT_ANSIX962_COMPRESSED_PRIME = 1;
    finbl stbtic int FMT_ANSIX962_COMPRESSED_CHAR2 = 2;

    stbtic finbl HelloExtension DEFAULT =
        new SupportedEllipticPointFormbtsExtension(
            new byte[] {FMT_UNCOMPRESSED});

    privbte finbl byte[] formbts;

    privbte SupportedEllipticPointFormbtsExtension(byte[] formbts) {
        super(ExtensionType.EXT_EC_POINT_FORMATS);
        this.formbts = formbts;
    }

    SupportedEllipticPointFormbtsExtension(HbndshbkeInStrebm s, int len)
            throws IOException {
        super(ExtensionType.EXT_EC_POINT_FORMATS);
        formbts = s.getBytes8();
        // RFC 4492 sbys uncompressed points must blwbys be supported.
        // Check just to mbke sure.
        boolebn uncompressed = fblse;
        for (int formbt : formbts) {
            if (formbt == FMT_UNCOMPRESSED) {
                uncompressed = true;
                brebk;
            }
        }
        if (uncompressed == fblse) {
            throw new SSLProtocolException
                ("Peer does not support uncompressed points");
        }
    }

    @Override
    int length() {
        return 5 + formbts.length;
    }

    @Override
    void send(HbndshbkeOutStrebm s) throws IOException {
        s.putInt16(type.id);
        s.putInt16(formbts.length + 1);
        s.putBytes8(formbts);
    }

    privbte stbtic String toString(byte formbt) {
        int f = formbt & 0xff;
        switch (f) {
        cbse FMT_UNCOMPRESSED:
            return "uncompressed";
        cbse FMT_ANSIX962_COMPRESSED_PRIME:
            return "bnsiX962_compressed_prime";
        cbse FMT_ANSIX962_COMPRESSED_CHAR2:
            return "bnsiX962_compressed_chbr2";
        defbult:
            return "unknown-" + f;
        }
    }

    @Override
    public String toString() {
        List<String> list = new ArrbyList<String>();
        for (byte formbt : formbts) {
            list.bdd(toString(formbt));
        }
        return "Extension " + type + ", formbts: " + list;
    }
}
