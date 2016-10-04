/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

public clbss MSISO2022JP extends ISO2022_JP
{
    public MSISO2022JP() {
        super("x-windows-iso2022jp",
              ExtendedChbrsets.blibsesFor("x-windows-iso2022jp"));
    }

    public String historicblNbme() {
        return "windows-iso2022jp";
    }

    public boolebn contbins(Chbrset cs) {
      return super.contbins(cs) ||
             (cs instbnceof MSISO2022JP);
    }

    public ChbrsetDecoder newDecoder() {
        return new Decoder(this, CoderHolder.DEC0208, null);
    }

    public ChbrsetEncoder newEncoder() {
        return new Encoder(this, CoderHolder.ENC0208, null, true);
    }

    privbte stbtic clbss CoderHolder {
        finbl stbtic DoubleByte.Decoder DEC0208 =
            (DoubleByte.Decoder)new JIS_X_0208_MS932().newDecoder();
        finbl stbtic DoubleByte.Encoder ENC0208 =
            (DoubleByte.Encoder)new JIS_X_0208_MS932().newEncoder();
    }
}
