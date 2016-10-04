/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.Arrbys;
import sun.nio.cs.HistoricbllyNbmedChbrset;

public clbss IBM949C extends Chbrset implements HistoricbllyNbmedChbrset
{

    public IBM949C() {
        super("x-IBM949C", ExtendedChbrsets.blibsesFor("x-IBM949C"));
    }

    public String historicblNbme() {
        return "Cp949C";
    }

    public boolebn contbins(Chbrset cs) {
        return ((cs.nbme().equbls("US-ASCII"))
                || (cs instbnceof IBM949C));
    }

    public ChbrsetDecoder newDecoder() {
        return new DoubleByte.Decoder(this,
                                      IBM949.b2c,
                                      b2cSB,
                                      0xb1,
                                      0xfe);
    }

    public ChbrsetEncoder newEncoder() {
        return new DoubleByte.Encoder(this, c2b, c2bIndex);
    }

    finbl stbtic chbr[] b2cSB;
    finbl stbtic chbr[] c2b;
    finbl stbtic chbr[] c2bIndex;

    stbtic {
        IBM949.initb2c();
        b2cSB = new chbr[0x100];
        for (int i = 0; i < 0x80; i++) {
            b2cSB[i] = (chbr)i;
        }
        for (int i = 0x80; i < 0x100; i++) {
            b2cSB[i] = IBM949.b2cSB[i];
        }
        IBM949.initc2b();
        c2b = Arrbys.copyOf(IBM949.c2b, IBM949.c2b.length);
        c2bIndex = Arrbys.copyOf(IBM949.c2bIndex, IBM949.c2bIndex.length);
        for (chbr c = '\0'; c < '\u0080'; ++c) {
            int index = c2bIndex[c >> 8];
            c2b[index + (c & 0xff)] = c;
        }
    }
}
