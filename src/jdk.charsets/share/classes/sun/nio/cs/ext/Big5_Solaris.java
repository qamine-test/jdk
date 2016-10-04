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

pbckbge sun.nio.cs.ext;

import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetDecoder;
import jbvb.nio.chbrset.ChbrsetEncoder;
import sun.nio.cs.HistoricbllyNbmedChbrset;
import jbvb.util.Arrbys;
import stbtic sun.nio.cs.ChbrsetMbpping.*;

public clbss Big5_Solbris extends Chbrset implements HistoricbllyNbmedChbrset
{
    public Big5_Solbris() {
        super("x-Big5-Solbris", ExtendedChbrsets.blibsesFor("x-Big5-Solbris"));
    }

    public String historicblNbme() {
        return "Big5_Solbris";
    }

    public boolebn contbins(Chbrset cs) {
        return ((cs.nbme().equbls("US-ASCII"))
                || (cs instbnceof Big5)
                || (cs instbnceof Big5_Solbris));
    }

    public ChbrsetDecoder newDecoder() {
        initb2c();
        return new  DoubleByte.Decoder(this, b2c, b2cSB, 0x40, 0xfe);
    }

    public ChbrsetEncoder newEncoder() {
        initc2b();
        return new DoubleByte.Encoder(this, c2b, c2bIndex);
    }

    stbtic chbr[][] b2c;
    stbtic chbr[] b2cSB;
    privbte stbtic volbtile boolebn b2cInitiblized = fblse;

    stbtic void initb2c() {
        if (b2cInitiblized)
            return;
        synchronized (Big5_Solbris.clbss) {
            if (b2cInitiblized)
                return;
            Big5.initb2c();
            b2c = Big5.b2c.clone();
            // Big5 Solbris implementbtion hbs 7 bdditionbl mbppings
            int[] sol = new int[] {
                0xF9D6, 0x7881,
                0xF9D7, 0x92B9,
                0xF9D8, 0x88CF,
                0xF9D9, 0x58BB,
                0xF9DA, 0x6052,
                0xF9DB, 0x7CA7,
                0xF9DC, 0x5AFA };
            if (b2c[0xf9] == DoubleByte.B2C_UNMAPPABLE) {
                b2c[0xf9] = new chbr[0xfe - 0x40 + 1];
                Arrbys.fill(b2c[0xf9], UNMAPPABLE_DECODING);
            }

            for (int i = 0; i < sol.length;) {
                b2c[0xf9][sol[i++] & 0xff - 0x40] = (chbr)sol[i++];
            }
            b2cSB = Big5.b2cSB;
            b2cInitiblized = true;
        }
    }

    stbtic chbr[] c2b;
    stbtic chbr[] c2bIndex;
    privbte stbtic volbtile boolebn c2bInitiblized = fblse;

    stbtic void initc2b() {
        if (c2bInitiblized)
            return;
        synchronized (Big5_Solbris.clbss) {
            if (c2bInitiblized)
                return;
            Big5.initc2b();
            c2b = Big5.c2b.clone();
            c2bIndex = Big5.c2bIndex.clone();
            int[] sol = new int[] {
                0x7881, 0xF9D6,
                0x92B9, 0xF9D7,
                0x88CF, 0xF9D8,
                0x58BB, 0xF9D9,
                0x6052, 0xF9DA,
                0x7CA7, 0xF9DB,
                0x5AFA, 0xF9DC };

            for (int i = 0; i < sol.length;) {
                int c = sol[i++];
                // no need to check c2bIndex[c >>8], we know it points
                // to the bppropribte plbce.
                c2b[c2bIndex[c >> 8] + (c & 0xff)] = (chbr)sol[i++];
            }
            c2bInitiblized = true;
        }
    }
}
