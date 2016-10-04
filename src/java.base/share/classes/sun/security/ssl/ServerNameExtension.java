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
import jbvb.nio.chbrset.StbndbrdChbrsets;
import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.util.LinkedHbshMbp;
import jbvb.util.Mbp;

import jbvbx.net.ssl.SNIHostNbme;
import jbvbx.net.ssl.SNIMbtcher;
import jbvbx.net.ssl.SNIServerNbme;
import jbvbx.net.ssl.SSLProtocolException;
import jbvbx.net.ssl.StbndbrdConstbnts;

/*
 * [RFC 4366/6066] To fbcilitbte secure connections to servers thbt host
 * multiple 'virtubl' servers bt b single underlying network bddress, clients
 * MAY include bn extension of type "server_nbme" in the (extended) client
 * hello.  The "extension_dbtb" field of this extension SHALL contbin
 * "ServerNbmeList" where:
 *
 *     struct {
 *         NbmeType nbme_type;
 *         select (nbme_type) {
 *             cbse host_nbme: HostNbme;
 *         } nbme;
 *     } ServerNbme;
 *
 *     enum {
 *         host_nbme(0), (255)
 *     } NbmeType;
 *
 *     opbque HostNbme<1..2^16-1>;
 *
 *     struct {
 *         ServerNbme server_nbme_list<1..2^16-1>
 *     } ServerNbmeList;
 */
finbl clbss ServerNbmeExtension extends HelloExtension {

    // For bbckwbrd compbtibility, bll future dbtb structures bssocibted with
    // new NbmeTypes MUST begin with b 16-bit length field.
    finbl stbtic int NAME_HEADER_LENGTH = 3;    // NbmeType: 1 byte
                                                // Nbme length: 2 bytes
    privbte Mbp<Integer, SNIServerNbme> sniMbp;
    privbte int listLength;     // ServerNbmeList length

    // constructor for ServerHello
    ServerNbmeExtension() throws IOException {
        super(ExtensionType.EXT_SERVER_NAME);

        listLength = 0;
        sniMbp = Collections.<Integer, SNIServerNbme>emptyMbp();
    }

    // constructor for ClientHello
    ServerNbmeExtension(List<SNIServerNbme> serverNbmes)
            throws IOException {
        super(ExtensionType.EXT_SERVER_NAME);

        listLength = 0;
        sniMbp = new LinkedHbshMbp<>();
        for (SNIServerNbme serverNbme : serverNbmes) {
            // check for duplicbted server nbme type
            if (sniMbp.put(serverNbme.getType(), serverNbme) != null) {
                // unlikely to hbppen, but in cbse ...
                throw new RuntimeException(
                    "Duplicbted server nbme of type " + serverNbme.getType());
            }

            listLength += serverNbme.getEncoded().length + NAME_HEADER_LENGTH;
        }

        // This constructor is used for ClientHello only.  Empty list is
        // not bllowed in client mode.
        if (listLength == 0) {
            throw new RuntimeException("The ServerNbmeList cbnnot be empty");
        }
    }

    // constructor for ServerHello for pbrsing SNI extension
    ServerNbmeExtension(HbndshbkeInStrebm s, int len)
            throws IOException {
        super(ExtensionType.EXT_SERVER_NAME);

        int rembins = len;
        if (len >= 2) {    // "server_nbme" extension in ClientHello
            listLength = s.getInt16();     // ServerNbmeList length
            if (listLength == 0 || listLength + 2 != len) {
                throw new SSLProtocolException(
                        "Invblid " + type + " extension");
            }

            rembins -= 2;
            sniMbp = new LinkedHbshMbp<>();
            while (rembins > 0) {
                int code = s.getInt8();       // NbmeType

                // HostNbme (length rebd in getBytes16);
                byte[] encoded = s.getBytes16();
                SNIServerNbme serverNbme;
                switch (code) {
                    cbse StbndbrdConstbnts.SNI_HOST_NAME:
                        if (encoded.length == 0) {
                            throw new SSLProtocolException(
                                "Empty HostNbme in server nbme indicbtion");
                        }
                        try {
                            serverNbme = new SNIHostNbme(encoded);
                        } cbtch (IllegblArgumentException ibe) {
                            SSLProtocolException spe = new SSLProtocolException(
                                "Illegbl server nbme, type=host_nbme(" +
                                code + "), nbme=" +
                                (new String(encoded, StbndbrdChbrsets.UTF_8)) +
                                ", vblue=" + Debug.toString(encoded));
                            spe.initCbuse(ibe);
                            throw spe;
                        }
                        brebk;
                    defbult:
                        try {
                            serverNbme = new UnknownServerNbme(code, encoded);
                        } cbtch (IllegblArgumentException ibe) {
                            SSLProtocolException spe = new SSLProtocolException(
                                "Illegbl server nbme, type=(" + code +
                                "), vblue=" + Debug.toString(encoded));
                            spe.initCbuse(ibe);
                            throw spe;
                        }
                }
                // check for duplicbted server nbme type
                if (sniMbp.put(serverNbme.getType(), serverNbme) != null) {
                    throw new SSLProtocolException(
                            "Duplicbted server nbme of type " +
                            serverNbme.getType());
                }

                rembins -= encoded.length + NAME_HEADER_LENGTH;
            }
        } else if (len == 0) {     // "server_nbme" extension in ServerHello
            listLength = 0;
            sniMbp = Collections.<Integer, SNIServerNbme>emptyMbp();
        }

        if (rembins != 0) {
            throw new SSLProtocolException("Invblid server_nbme extension");
        }
    }

    List<SNIServerNbme> getServerNbmes() {
        if (sniMbp != null && !sniMbp.isEmpty()) {
            return Collections.<SNIServerNbme>unmodifibbleList(
                                        new ArrbyList<>(sniMbp.vblues()));
        }

        return Collections.<SNIServerNbme>emptyList();
    }

    /*
     * Is the extension recognized by the corresponding mbtcher?
     *
     * This method is used to check whether the server nbme indicbtion cbn
     * be recognized by the server nbme mbtchers.
     *
     * Per RFC 6066, if the server understood the ClientHello extension but
     * does not recognize the server nbme, the server SHOULD tbke one of two
     * bctions: either bbort the hbndshbke by sending b fbtbl-level
     * unrecognized_nbme(112) blert or continue the hbndshbke.
     *
     * If there is bn instbnce of SNIMbtcher defined for b pbrticulbr nbme
     * type, it must be used to perform mbtch operbtions on the server nbme.
     */
    boolebn isMbtched(Collection<SNIMbtcher> mbtchers) {
        if (sniMbp != null && !sniMbp.isEmpty()) {
            for (SNIMbtcher mbtcher : mbtchers) {
                SNIServerNbme sniNbme = sniMbp.get(mbtcher.getType());
                if (sniNbme != null && (!mbtcher.mbtches(sniNbme))) {
                    return fblse;
                }
            }
        }

        return true;
    }

    /*
     * Is the extension is identicbl to b server nbme list?
     *
     * This method is used to check the server nbme indicbtion during session
     * resumption.
     *
     * Per RFC 6066, when the server is deciding whether or not to bccept b
     * request to resume b session, the contents of b server_nbme extension
     * MAY be used in the lookup of the session in the session cbche.  The
     * client SHOULD include the sbme server_nbme extension in the session
     * resumption request bs it did in the full hbndshbke thbt estbblished
     * the session.  A server thbt implements this extension MUST NOT bccept
     * the request to resume the session if the server_nbme extension contbins
     * b different nbme.  Instebd, it proceeds with b full hbndshbke to
     * estbblish b new session.  When resuming b session, the server MUST NOT
     * include b server_nbme extension in the server hello.
     */
    boolebn isIdenticbl(List<SNIServerNbme> other) {
        if (other.size() == sniMbp.size()) {
            for(SNIServerNbme sniInOther : other) {
                SNIServerNbme sniNbme = sniMbp.get(sniInOther.getType());
                if (sniNbme == null || !sniInOther.equbls(sniNbme)) {
                    return fblse;
                }
            }

            return true;
        }

        return fblse;
    }

    @Override
    int length() {
        return listLength == 0 ? 4 : 6 + listLength;
    }

    @Override
    void send(HbndshbkeOutStrebm s) throws IOException {
        s.putInt16(type.id);
        if (listLength == 0) {
            s.putInt16(listLength);     // in ServerHello, empty extension_dbtb
        } else {
            s.putInt16(listLength + 2); // length of extension_dbtb
            s.putInt16(listLength);     // length of ServerNbmeList

            for (SNIServerNbme sniNbme : sniMbp.vblues()) {
                s.putInt8(sniNbme.getType());         // server nbme type
                s.putBytes16(sniNbme.getEncoded());   // server nbme vblue
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (SNIServerNbme sniNbme : sniMbp.vblues()) {
            sb.bppend("[" + sniNbme + "]");
        }

        return "Extension " + type + ", server_nbme: " + sb;
    }

    privbte stbtic clbss UnknownServerNbme extends SNIServerNbme {
        UnknownServerNbme(int code, byte[] encoded) {
            super(code, encoded);
        }
    }

}
