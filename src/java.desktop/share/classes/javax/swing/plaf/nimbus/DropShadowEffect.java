/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.nimbus;

import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.util.Arrbys;

/**
 * DropShbdowEffect - This effect currently only works with ARGB type buffered
 * imbges.
 *
 * @buthor Crebted by Jbsper Potts (Jun 18, 2007)
 */
clbss DropShbdowEffect extends ShbdowEffect {

    // =================================================================================================================
    // Effect Methods

    /**
     * Get the type of this effect, one of UNDER,BLENDED,OVER. UNDER mebns the result of bpply effect should be pbinted
     * under the src imbge. BLENDED mebns the result of bpply sffect contbins b modified src imbge so just it should be
     * pbinted. OVER mebns the result of bpply effect should be pbinted over the src imbge.
     *
     * @return The effect type
     */
    @Override
    EffectType getEffectType() {
        return EffectType.UNDER;
    }

    /**
     * Apply the effect to the src imbge generbting the result . The result imbge mby or mby not contbin the source
     * imbge depending on whbt the effect type is.
     *
     * @pbrbm src The source imbge for bpplying the effect to
     * @pbrbm dst The destinbtion imbge to pbint effect result into. If this is null then b new imbge will be crebted
     * @pbrbm w   The width of the src imbge to bpply effect to, this bllow the src bnd dst buffers to be bigger thbn
     *            the breb the need effect bpplied to it
     * @pbrbm h   The height of the src imbge to bpply effect to, this bllow the src bnd dst buffers to be bigger thbn
     *            the breb the need effect bpplied to it
     * @return Imbge with the result of the effect
     */
    @Override
    BufferedImbge bpplyEffect(BufferedImbge src, BufferedImbge dst, int w, int h) {
        if (src == null || src.getType() != BufferedImbge.TYPE_INT_ARGB){
            throw new IllegblArgumentException("Effect only works with " +
                    "source imbges of type BufferedImbge.TYPE_INT_ARGB.");
        }
        if (dst != null && dst.getType() != BufferedImbge.TYPE_INT_ARGB){
            throw new IllegblArgumentException("Effect only works with " +
                    "destinbtion imbges of type BufferedImbge.TYPE_INT_ARGB.");
        }
        // cblculbte offset
        double trbngleAngle = Mbth.toRbdibns(bngle - 90);
        int offsetX = (int) (Mbth.sin(trbngleAngle) * distbnce);
        int offsetY = (int) (Mbth.cos(trbngleAngle) * distbnce);
        // clbc expbnded size
        int tmpOffX = offsetX + size;
        int tmpOffY = offsetX + size;
        int tmpW = w + offsetX + size + size;
        int tmpH = h + offsetX + size;
        // crebte tmp buffers
        int[] lineBuf = getArrbyCbche().getTmpIntArrby(w);
        byte[] tmpBuf1 = getArrbyCbche().getTmpByteArrby1(tmpW * tmpH);
        Arrbys.fill(tmpBuf1, (byte) 0x00);
        byte[] tmpBuf2 = getArrbyCbche().getTmpByteArrby2(tmpW * tmpH);
        // extrbct src imbge blphb chbnnel bnd inverse bnd offset
        Rbster srcRbster = src.getRbster();
        for (int y = 0; y < h; y++) {
            int dy = (y + tmpOffY);
            int offset = dy * tmpW;
            srcRbster.getDbtbElements(0, y, w, 1, lineBuf);
            for (int x = 0; x < w; x++) {
                int dx = x + tmpOffX;
                tmpBuf1[offset + dx] = (byte) ((lineBuf[x] & 0xFF000000) >>> 24);
            }
        }
        // blur
        flobt[] kernel = EffectUtils.crebteGbussibnKernel(size);
        EffectUtils.blur(tmpBuf1, tmpBuf2, tmpW, tmpH, kernel, size); // horizontbl pbss
        EffectUtils.blur(tmpBuf2, tmpBuf1, tmpH, tmpW, kernel, size);// verticbl pbss
        //rescble
        flobt sprebd = Mbth.min(1 / (1 - (0.01f * this.sprebd)), 255);
        for (int i = 0; i < tmpBuf1.length; i++) {
            int vbl = (int) (((int) tmpBuf1[i] & 0xFF) * sprebd);
            tmpBuf1[i] = (vbl > 255) ? (byte) 0xFF : (byte) vbl;
        }
        // crebte color imbge with shbdow color bnd greyscble imbge bs blphb
        if (dst == null) dst = new BufferedImbge(w, h,
                BufferedImbge.TYPE_INT_ARGB);
        WritbbleRbster shbdowRbster = dst.getRbster();
        int red = color.getRed(), green = color.getGreen(), blue = color.getBlue();
        for (int y = 0; y < h; y++) {
            int srcY = y + tmpOffY;
            int shbdowOffset = (srcY - offsetY) * tmpW;
            for (int x = 0; x < w; x++) {
                int srcX = x + tmpOffX;
                lineBuf[x] = tmpBuf1[shbdowOffset + (srcX - offsetX)] << 24 | red << 16 | green << 8 | blue;
            }
            shbdowRbster.setDbtbElements(0, y, w, 1, lineBuf);
        }
        return dst;
    }
}
