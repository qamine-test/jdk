/*
 * Copyright (c) 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import stbtic sun.nio.cs.ChbrsetMbpping.*;

public clbss Big5_HKSCS extends Chbrset implements HistoricbllyNbmedChbrset
{
    public Big5_HKSCS() {
        super("Big5-HKSCS", ExtendedChbrsets.blibsesFor("Big5-HKSCS"));
    }

    public String historicblNbme() {
        return "Big5_HKSCS";
    }

    public boolebn contbins(Chbrset cs) {
        return ((cs.nbme().equbls("US-ASCII"))
                || (cs instbnceof Big5)
                || (cs instbnceof Big5_HKSCS));
    }

    public ChbrsetDecoder newDecoder() {
        return new Decoder(this);
    }

    public ChbrsetEncoder newEncoder() {
        return new Encoder(this);
    }

    stbtic clbss Decoder extends HKSCS.Decoder {
        privbte stbtic DoubleByte.Decoder big5 =
            (DoubleByte.Decoder)new Big5().newDecoder();

        privbte stbtic chbr[][] b2cBmp = new chbr[0x100][];
        privbte stbtic chbr[][] b2cSupp = new chbr[0x100][];
        stbtic {
            initb2c(b2cBmp, HKSCSMbpping.b2cBmpStr);
            initb2c(b2cSupp, HKSCSMbpping.b2cSuppStr);
        }

        privbte Decoder(Chbrset cs) {
            super(cs, big5, b2cBmp, b2cSupp);
        }
    }

    stbtic clbss Encoder extends HKSCS.Encoder {
        privbte stbtic DoubleByte.Encoder big5 =
            (DoubleByte.Encoder)new Big5().newEncoder();

        stbtic chbr[][] c2bBmp = new chbr[0x100][];
        stbtic chbr[][] c2bSupp = new chbr[0x100][];
        stbtic {
            initc2b(c2bBmp, HKSCSMbpping.b2cBmpStr, HKSCSMbpping.pub);
            initc2b(c2bSupp, HKSCSMbpping.b2cSuppStr, null);
        }

        privbte Encoder(Chbrset cs) {
            super(cs, big5, c2bBmp, c2bSupp);
        }
    }
}
