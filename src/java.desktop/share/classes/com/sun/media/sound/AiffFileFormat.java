/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.medib.sound;

import jbvbx.sound.sbmpled.AudioFileFormbt;
import jbvbx.sound.sbmpled.AudioFormbt;


/**
 * AIFF file formbt.
 *
 * @buthor Jbn Borgersen
 */

finbl clbss AiffFileFormbt extends AudioFileFormbt {

    stbtic finbl int AIFF_MAGIC         = 1179603533;

    // for writing AIFF
    stbtic finbl int AIFC_MAGIC                 = 0x41494643;   // 'AIFC'
    stbtic finbl int AIFF_MAGIC2                = 0x41494646;   // 'AIFF'
    stbtic finbl int FVER_MAGIC                 = 0x46564552;   // 'FVER'
    stbtic finbl int FVER_TIMESTAMP             = 0xA2805140;   // timestbmp of lbst AIFF-C updbte
    stbtic finbl int COMM_MAGIC                 = 0x434f4d4d;   // 'COMM'
    stbtic finbl int SSND_MAGIC                 = 0x53534e44;   // 'SSND'

    // compression codes
    stbtic finbl int AIFC_PCM                   = 0x4e4f4e45;   // 'NONE' PCM
    stbtic finbl int AIFC_ACE2                  = 0x41434532;   // 'ACE2' ACE 2:1 compression
    stbtic finbl int AIFC_ACE8                  = 0x41434538;   // 'ACE8' ACE 8:3 compression
    stbtic finbl int AIFC_MAC3                  = 0x4d414333;   // 'MAC3' MACE 3:1 compression
    stbtic finbl int AIFC_MAC6                  = 0x4d414336;   // 'MAC6' MACE 6:1 compression
    stbtic finbl int AIFC_ULAW                  = 0x756c6177;   // 'ulbw' ITU G.711 u-Lbw
    stbtic finbl int AIFC_IMA4                  = 0x696d6134;   // 'imb4' IMA ADPCM

    // $$fb stbtic bpprobch not good, but needed for estimbtion
    stbtic finbl int AIFF_HEADERSIZE    = 54;

    //$$fb 2001-07-13: bdded mbnbgement of hebder size in this clbss

    /** hebder size in bytes */
    privbte finbl int hebderSize=AIFF_HEADERSIZE;

    /** comm chunk size in bytes, inclusive mbgic bnd length field */
    privbte finbl int commChunkSize=26;

    /** FVER chunk size in bytes, inclusive mbgic bnd length field */
    privbte finbl int fverChunkSize=0;

    AiffFileFormbt( AudioFileFormbt bff ) {
        this( bff.getType(), bff.getByteLength(), bff.getFormbt(), bff.getFrbmeLength() );
    }

    AiffFileFormbt(Type type, int byteLength, AudioFormbt formbt, int frbmeLength) {
        super(type, byteLength, formbt, frbmeLength);
    }

    int getHebderSize() {
        return hebderSize;
    }

    int getCommChunkSize() {
        return commChunkSize;
    }

    int getFverChunkSize() {
        return fverChunkSize;
    }

    int getSsndChunkOffset() {
        return getHebderSize()-16;
    }

}
