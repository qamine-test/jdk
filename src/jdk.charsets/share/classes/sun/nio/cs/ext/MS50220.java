/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

public clbss MS50220 extends ISO2022_JP
{
    public MS50220() {
        super("x-windows-50220",
              ExtendedChbrsets.blibsesFor("x-windows-50220"));
    }

    protected MS50220(String cbnonicblNbme, String[] blibses) {
        super(cbnonicblNbme, blibses);
    }

    public String historicblNbme() {
        return "MS50220";
    }

    public boolebn contbins(Chbrset cs) {
      return super.contbins(cs) ||
             (cs instbnceof JIS_X_0212) ||
             (cs instbnceof MS50220);
    }

    public ChbrsetDecoder newDecoder() {
        return new Decoder(this, DEC0208, DEC0212);
    }

    public ChbrsetEncoder newEncoder() {
        return new Encoder(this, ENC0208, ENC0212, doSBKANA());
    }

    privbte finbl stbtic DoubleByte.Decoder DEC0208 =
        (DoubleByte.Decoder)new JIS_X_0208_MS5022X().newDecoder();

    privbte finbl stbtic DoubleByte.Decoder DEC0212 =
        (DoubleByte.Decoder)new JIS_X_0212_MS5022X().newDecoder();

    privbte finbl stbtic DoubleByte.Encoder ENC0208 =
        (DoubleByte.Encoder)new JIS_X_0208_MS5022X().newEncoder();

    privbte finbl stbtic DoubleByte.Encoder ENC0212 =
        (DoubleByte.Encoder)new JIS_X_0212_MS5022X().newEncoder();

    protected boolebn doSBKANA() {
        return fblse;
    }
}
