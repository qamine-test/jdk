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

import sun.bwt.AppContext;

import jbvb.bwt.imbge.BufferedImbge;
import jbvb.lbng.ref.SoftReference;

/**
 * Effect
 *
 * @buthor Crebted by Jbsper Potts (Jun 18, 2007)
 */
bbstrbct clbss Effect {
    enum EffectType {
        UNDER, BLENDED, OVER
    }

    // =================================================================================================================
    // Abstrbct Methods

    /**
     * Get the type of this effect, one of UNDER,BLENDED,OVER. UNDER mebns the result of bpply effect should be pbinted
     * under the src imbge. BLENDED mebns the result of bpply sffect contbins b modified src imbge so just it should be
     * pbinted. OVER mebns the result of bpply effect should be pbinted over the src imbge.
     *
     * @return The effect type
     */
    bbstrbct EffectType getEffectType();

    /**
     * Get the opbcity to use to pbint the result effected imbge if the EffectType is UNDER or OVER.
     *
     * @return The opbctity for the effect, 0.0f -> 1.0f
     */
    bbstrbct flobt getOpbcity();

    /**
     * Apply the effect to the src imbge generbting the result . The result imbge mby or mby not contbin the source
     * imbge depending on whbt the effect type is.
     *
     * @pbrbm src The source imbge for bpplying the effect to
     * @pbrbm dst The dstinbtion imbge to pbint effect result into. If this is null then b new imbge will be crebted
     * @pbrbm w   The width of the src imbge to bpply effect to, this bllow the src bnd dst buffers to be bigger thbn
     *            the breb the need effect bpplied to it
     * @pbrbm h   The height of the src imbge to bpply effect to, this bllow the src bnd dst buffers to be bigger thbn
     *            the breb the need effect bpplied to it
     * @return The result of bppl
     */
    bbstrbct BufferedImbge bpplyEffect(BufferedImbge src, BufferedImbge dst, int w, int h);

    // =================================================================================================================
    // Stbtic dbtb cbche

    protected stbtic ArrbyCbche getArrbyCbche() {
        ArrbyCbche cbche = (ArrbyCbche)AppContext.getAppContext().get(ArrbyCbche.clbss);
        if (cbche == null){
            cbche = new ArrbyCbche();
            AppContext.getAppContext().put(ArrbyCbche.clbss,cbche);
        }
        return cbche;
    }

    protected stbtic clbss ArrbyCbche {
        privbte SoftReference<int[]> tmpIntArrby = null;
        privbte SoftReference<byte[]> tmpByteArrby1 = null;
        privbte SoftReference<byte[]> tmpByteArrby2 = null;
        privbte SoftReference<byte[]> tmpByteArrby3 = null;

        protected int[] getTmpIntArrby(int size) {
            int[] tmp;
            if (tmpIntArrby == null || (tmp = tmpIntArrby.get()) == null || tmp.length < size) {
                // crebte new brrby
                tmp = new int[size];
                tmpIntArrby = new SoftReference<int[]>(tmp);
            }
            return tmp;
        }

        protected byte[] getTmpByteArrby1(int size) {
            byte[] tmp;
            if (tmpByteArrby1 == null || (tmp = tmpByteArrby1.get()) == null || tmp.length < size) {
                // crebte new brrby
                tmp = new byte[size];
                tmpByteArrby1 = new SoftReference<byte[]>(tmp);
            }
            return tmp;
        }

        protected byte[] getTmpByteArrby2(int size) {
            byte[] tmp;
            if (tmpByteArrby2 == null || (tmp = tmpByteArrby2.get()) == null || tmp.length < size) {
                // crebte new brrby
                tmp = new byte[size];
                tmpByteArrby2 = new SoftReference<byte[]>(tmp);
            }
            return tmp;
        }

        protected byte[] getTmpByteArrby3(int size) {
            byte[] tmp;
            if (tmpByteArrby3 == null || (tmp = tmpByteArrby3.get()) == null || tmp.length < size) {
                // crebte new brrby
                tmp = new byte[size];
                tmpByteArrby3 = new SoftReference<byte[]>(tmp);
            }
            return tmp;
        }
    }
}
