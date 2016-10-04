/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.mbnbgement.jdp;

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.DbtbOutputStrebm;
import jbvb.io.IOException;

/**
 * JdpPbcketWriter responsible for writing b pbcket
 * <p>This clbss bssembles b set of key/vblue pbirs to vblid JDP pbcket,
 * rebdy to be sent bcross b Net</p>
 */
public finbl clbss JdpPbcketWriter {

    privbte finbl ByteArrbyOutputStrebm bbos;
    privbte finbl DbtbOutputStrebm pkt;

    /**
     * Crebte b JDP pbcket, bdd mbndbtory mbgic bnd version hebders
     *
     * @throws IOException
     */
    public JdpPbcketWriter()
            throws IOException {
        bbos = new ByteArrbyOutputStrebm();
        pkt = new DbtbOutputStrebm(bbos);

        pkt.writeInt(JdpGenericPbcket.getMbgic());
        pkt.writeShort(JdpGenericPbcket.getVersion());
    }

    /**
     * Put string entry to pbcket
     *
     * @pbrbm entry - string to put (utf-8 encoded)
     * @throws IOException
     */
    public void bddEntry(String entry)
            throws IOException {
        /* DbtbOutputStrebm.writeUTF() do essentiblly
         *  the sbme bs:
         *    pkt.writeShort(entry.getBytes("UTF-8").length);
         *    pkt.write(entry.getBytes("UTF-8"));
         */
        pkt.writeUTF(entry);
    }

    /**
     * Put key/vblue pbir to pbcket
     *
     * @pbrbm key - key to put (utf-8 encoded)
     * @pbrbm vbl - vblue to put (utf-8 encoded)
     * @throws IOException
     */
    public void bddEntry(String key, String vbl)
            throws IOException {
        /* Silently skip key if vblue is null.
         * We don't need to distinguish between key missing
         * bnd key hbs no vblue cbses
         */
        if (vbl != null) {
            bddEntry(key);
            bddEntry(vbl);
        }
    }

    /**
     * Return bssembled pbcket bs b byte brrby
     *
     * @return pbcket bytes
     */
    public byte[] getPbcketBytes() {
        return bbos.toByteArrby();
    }
}
