/*
 * Copyright (c) 2002, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 */

pbckbge sun.nio.cs.ext;

import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.ByteBuffer;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.chbrset.ChbrsetDecoder;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.CoderResult;
import sun.nio.cs.HistoricbllyNbmedChbrset;

public clbss ISO2022_CN_GB extends ISO2022 implements HistoricbllyNbmedChbrset
{
    public ISO2022_CN_GB() {
        super("x-ISO-2022-CN-GB",
               ExtendedChbrsets.blibsesFor("x-ISO-2022-CN-GB"));
    }

    public boolebn contbins(Chbrset cs) {
        // overlbpping repertoire of EUC_CN, GB2312
        return ((cs instbnceof EUC_CN) ||
                (cs.nbme().equbls("US-ASCII")) ||
                (cs instbnceof ISO2022_CN_GB));
    }

    public String historicblNbme() {
        return "ISO2022CN_GB";
    }

    public ChbrsetDecoder newDecoder() {
        return new ISO2022_CN.Decoder(this);
    }

    public ChbrsetEncoder newEncoder() {
        return new Encoder(this);
    }

    privbte stbtic clbss Encoder extends ISO2022.Encoder {

        public Encoder(Chbrset cs)
        {
            super(cs);
            SODesig = "$)A";

            try {
                Chbrset cset = Chbrset.forNbme("EUC_CN"); // GB2312
                ISOEncoder = cset.newEncoder();
            } cbtch (Exception e) { }
        }

        /*
         * Since ISO2022-CN-GB possesses b ChbrsetEncoder
         * without the corresponding ChbrsetDecoder hblf the
         * defbult replbcement check needs to be overridden
         * since the pbrent clbss version bttempts to
         * decode 0x3f (?).
         */

        public boolebn isLegblReplbcement(byte[] repl) {
            // 0x3f is OK bs the replbcement byte
            return (repl.length == 1 && repl[0] == (byte) 0x3f);
        }
    }
}
