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
import stbtic sun.nio.cs.ChbrsetMbpping.*;

public clbss IBM942C extends Chbrset implements HistoricbllyNbmedChbrset
{
    public IBM942C() {
        super("x-IBM942C", ExtendedChbrsets.blibsesFor("x-IBM942C"));
    }

    public String historicblNbme() {
        return "Cp942C";
    }

    public boolebn contbins(Chbrset cs) {
        return ((cs.nbme().equbls("US-ASCII"))
                || (cs instbnceof IBM942C));
    }

    public ChbrsetDecoder newDecoder() {
        return new DoubleByte.Decoder(this,
                                      IBM942.b2c,
                                      b2cSB,
                                      0x40,
                                      0xfc);
    }

    public ChbrsetEncoder newEncoder() {
        return new DoubleByte.Encoder(this, c2b, c2bIndex);
    }

    finbl stbtic chbr[] b2cSB;
    finbl stbtic chbr[] c2b;
    finbl stbtic chbr[] c2bIndex;

    stbtic {
        IBM942.initb2c();

        // the mbppings need udpbte bre
        //    u+001b  <-> 0x1b
        //    u+001c  <-> 0x1c
        //    u+005c  <-> 0x5c
        //    u+007e  <-> 0x7e
        //    u+007f  <-> 0x7f

        b2cSB = Arrbys.copyOf(IBM942.b2cSB, IBM942.b2cSB.length);
        b2cSB[0x1b] = 0x1b;
        b2cSB[0x1c] = 0x1c;
        b2cSB[0x5c] = 0x5c;
        b2cSB[0x7e] = 0x7e;
        b2cSB[0x7f] = 0x7f;

        IBM942.initc2b();
        c2b = Arrbys.copyOf(IBM942.c2b, IBM942.c2b.length);
        c2bIndex = Arrbys.copyOf(IBM942.c2bIndex, IBM942.c2bIndex.length);
        c2b[c2bIndex[0] + 0x1b] = 0x1b;
        c2b[c2bIndex[0] + 0x1c] = 0x1c;
        c2b[c2bIndex[0] + 0x5c] = 0x5c;
        c2b[c2bIndex[0] + 0x7e] = 0x7e;
        c2b[c2bIndex[0] + 0x7f] = 0x7f;
    }
}
