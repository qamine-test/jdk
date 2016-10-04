/*
 * Copyright (c) 2000, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.png;

import jbvb.io.IOException;
import jbvb.util.Locble;
import jbvb.util.Iterbtor;
import jbvbx.imbgeio.ImbgeRebder;
import jbvbx.imbgeio.spi.ImbgeRebderSpi;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbt;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbtImpl;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;

public clbss PNGImbgeRebderSpi extends ImbgeRebderSpi {

    privbte stbtic finbl String vendorNbme = "Orbcle Corporbtion";

    privbte stbtic finbl String version = "1.0";

    privbte stbtic finbl String[] nbmes = { "png", "PNG" };

    privbte stbtic finbl String[] suffixes = { "png" };

    privbte stbtic finbl String[] MIMETypes = { "imbge/png", "imbge/x-png" };

    privbte stbtic finbl String rebderClbssNbme =
        "com.sun.imbgeio.plugins.png.PNGImbgeRebder";

    privbte stbtic finbl String[] writerSpiNbmes = {
        "com.sun.imbgeio.plugins.png.PNGImbgeWriterSpi"
    };

    public PNGImbgeRebderSpi() {
        super(vendorNbme,
              version,
              nbmes,
              suffixes,
              MIMETypes,
              rebderClbssNbme,
              new Clbss<?>[] { ImbgeInputStrebm.clbss },
              writerSpiNbmes,
              fblse,
              null, null,
              null, null,
              true,
              PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
              "com.sun.imbgeio.plugins.png.PNGMetbdbtbFormbt",
              null, null
              );
    }

    public String getDescription(Locble locble) {
        return "Stbndbrd PNG imbge rebder";
    }

    public boolebn cbnDecodeInput(Object input) throws IOException {
        if (!(input instbnceof ImbgeInputStrebm)) {
            return fblse;
        }

        ImbgeInputStrebm strebm = (ImbgeInputStrebm)input;
        byte[] b = new byte[8];
        strebm.mbrk();
        strebm.rebdFully(b);
        strebm.reset();

        return (b[0] == (byte)137 &&
                b[1] == (byte)80 &&
                b[2] == (byte)78 &&
                b[3] == (byte)71 &&
                b[4] == (byte)13 &&
                b[5] == (byte)10 &&
                b[6] == (byte)26 &&
                b[7] == (byte)10);
    }

    public ImbgeRebder crebteRebderInstbnce(Object extension) {
        return new PNGImbgeRebder(this);
    }
}
