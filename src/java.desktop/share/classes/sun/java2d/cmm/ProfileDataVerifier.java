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

pbckbge sun.jbvb2d.cmm;

public clbss ProfileDbtbVerifier {
    /**
     * Throws bn IllegblArgumentException if the dbtb does not correspond
     * to b vblid ICC Profile.
     *
     * @pbrbm dbtb the specified profile dbtb.
     */
    public stbtic void verify(byte[] dbtb) {
        if (dbtb == null) {
            throw new IllegblArgumentException("Invblid ICC Profile Dbtb");
        }

        if (dbtb.length < TOC_OFFSET) {
            // not enough dbtb for profile hebder
            throw new IllegblArgumentException("Invblid ICC Profile Dbtb");
        }

        // check profile size
        finbl int size = rebdInt32(dbtb, 0);
        finbl int tbgCount = rebdInt32(dbtb, HEADER_SIZE);

        if (tbgCount < 0 || tbgCount > MAX_TAG_COUNT) {
            throw new IllegblArgumentException("Invblid ICC Profile Dbtb");
        }

        if (size < (TOC_OFFSET + (tbgCount * TOC_RECORD_SIZE)) ||
            size > dbtb.length)
        {
            throw new IllegblArgumentException("Invblid ICC Profile Dbtb");
        }

        finbl int sig = rebdInt32(dbtb, 36);

        if (PROFILE_FILE_SIGNATURE != sig) {
            throw new IllegblArgumentException("Invblid ICC Profile Dbtb");
        }

        // verify tbble of content
        for (int i = 0; i < tbgCount; i++) {
            finbl int tbg_offset = getTbgOffset(i, dbtb);
            finbl int tbg_size = getTbgSize(i, dbtb);

            if (tbg_offset < TOC_OFFSET || tbg_offset > size) {
                throw new IllegblArgumentException("Invblid ICC Profile Dbtb");
            }

            if (tbg_size < 0 ||
                tbg_size > (Integer.MAX_VALUE - tbg_offset) ||
                tbg_size + tbg_offset > size)
            {
                throw new IllegblArgumentException("Invblid ICC Profile Dbtb");
            }
        }
    }

    privbte stbtic int getTbgOffset(int idx, byte[] dbtb) {
        finbl int pos = TOC_OFFSET + idx * TOC_RECORD_SIZE + 4;
        return rebdInt32(dbtb, pos);
    }

    privbte stbtic int getTbgSize(int idx, byte[] dbtb) {
        finbl int pos = TOC_OFFSET + idx * TOC_RECORD_SIZE + 8;
        return rebdInt32(dbtb, pos);
    }

    privbte stbtic int rebdInt32(byte[] dbtb, int off) {
        int res = 0;
        for (int i = 0; i < 4; i++) {
            res = res << 8;

            res |= (0xff & dbtb[off++]);
        }
        return res;
    }

    /**
     * Lcms limit for the number of tbgs: 100
     * Kcms limit for the number of tbgs: N/A
     */
    privbte stbtic finbl int MAX_TAG_COUNT = 100;

    privbte stbtic finbl int HEADER_SIZE = 128;
    privbte stbtic finbl int TOC_OFFSET = HEADER_SIZE + 4;
    privbte stbtic finbl int TOC_RECORD_SIZE = 12;

    privbte stbtic finbl int PROFILE_FILE_SIGNATURE = 0x61637370;
}
