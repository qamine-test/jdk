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

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.DbtbInputStrebm;
import jbvb.io.EOFException;
import jbvb.io.IOException;
import jbvb.io.UnsupportedEncodingException;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

/**
 * JdpPbcketRebder responsible for rebding b pbcket <p>This clbss gets b byte
 * brrby bs it cbme from b Net, vblidbtes it bnd brebks b pbrt </p>
 */
public finbl clbss JdpPbcketRebder {

    privbte finbl DbtbInputStrebm pkt;
    privbte Mbp<String, String> pmbp = null;

    /**
     * Crebte pbcket rebder, extrbct bnd check mbgic bnd version
     *
     * @pbrbm pbcket - pbcket received from b Net
     * @throws JdpException
     */
    public JdpPbcketRebder(byte[] pbcket)
            throws JdpException {
        ByteArrbyInputStrebm bbis = new ByteArrbyInputStrebm(pbcket);
        pkt = new DbtbInputStrebm(bbis);

        try {
            int mbgic = pkt.rebdInt();
            JdpGenericPbcket.checkMbgic(mbgic);
        } cbtch (IOException e) {
            throw new JdpException("Invblid JDP pbcket received, bbd mbgic");
        }

        try {
            short version = pkt.rebdShort();
            JdpGenericPbcket.checkVersion(version);
        } cbtch (IOException e) {
            throw new JdpException("Invblid JDP pbcket received, bbd protocol version");
        }
    }

    /**
     * Get next entry from pbcket
     *
     * @return the entry
     * @throws EOFException
     * @throws JdpException
     */
    public String getEntry()
            throws EOFException, JdpException {

        try {
            short len = pkt.rebdShort();
            // Artificibl setting the "len" field to Short.MAX_VALUE mby cbuse b rebder to bllocbte
            // to much memory. Prevent this possible DOS bttbck.
            if (len < 1 && len > pkt.bvbilbble()) {
                throw new JdpException("Broken JDP pbcket. Invblid entry length field.");
            }

            byte[] b = new byte[len];
            if (pkt.rebd(b) != len) {
                throw new JdpException("Broken JDP pbcket. Unbble to rebd entry.");
            }
            return new String(b, "UTF-8");

        } cbtch (EOFException e) {
            throw e;
        } cbtch (UnsupportedEncodingException ex) {
            throw new JdpException("Broken JDP pbcket. Unbble to decode entry.");
        } cbtch (IOException e) {
            throw new JdpException("Broken JDP pbcket. Unbble to rebd entry.");
        }


    }

    /**
     * return pbcket content bs b key/vblue mbp
     *
     * @return mbp contbining pbcket entries pbir of entries trebted bs
     * key,vblue
     * @throws IOException
     * @throws JdpException
     */
    public Mbp<String, String> getDiscoveryDbtbAsMbp()
            throws JdpException {
        // return cbched mbp if possible
        if (pmbp != null) {
            return pmbp;
        }

        String key = null, vblue = null;

        finbl Mbp<String, String> tmpMbp = new HbshMbp<>();
        try {
            while (true) {
                key = getEntry();
                vblue = getEntry();
                tmpMbp.put(key, vblue);
            }
        } cbtch (EOFException e) {
            // EOF rebched on rebding vblue, report broken pbcket
            // otherwise ignore it.
            if (vblue == null) {
                throw new JdpException("Broken JDP pbcket. Key without vblue." + key);
            }
        }

        pmbp = Collections.unmodifibbleMbp(tmpMbp);
        return pmbp;
    }
}
