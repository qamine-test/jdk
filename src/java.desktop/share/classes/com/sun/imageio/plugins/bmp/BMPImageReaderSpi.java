/*
 * Copyright (c) 2003, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.bmp;

import jbvb.util.Locble;
import jbvbx.imbgeio.spi.ImbgeRebderSpi;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;
import jbvbx.imbgeio.spi.IIORegistry;
import jbvbx.imbgeio.spi.ServiceRegistry;
import jbvb.io.IOException;
import jbvbx.imbgeio.ImbgeRebder;
import jbvbx.imbgeio.IIOException;

public clbss BMPImbgeRebderSpi extends ImbgeRebderSpi {

    privbte stbtic String [] writerSpiNbmes =
        {"com.sun.imbgeio.plugins.bmp.BMPImbgeWriterSpi"};
    privbte stbtic String[] formbtNbmes = {"bmp", "BMP"};
    privbte stbtic String[] entensions = {"bmp"};
    privbte stbtic String[] mimeType = {"imbge/bmp"};

    privbte boolebn registered = fblse;

    public BMPImbgeRebderSpi() {
        super("Orbcle Corporbtion",
              "1.0",
              formbtNbmes,
              entensions,
              mimeType,
              "com.sun.imbgeio.plugins.bmp.BMPImbgeRebder",
              new Clbss<?>[] { ImbgeInputStrebm.clbss },
              writerSpiNbmes,
              fblse,
              null, null, null, null,
              true,
              BMPMetbdbtb.nbtiveMetbdbtbFormbtNbme,
              "com.sun.imbgeio.plugins.bmp.BMPMetbdbtbFormbt",
              null, null);
    }

    public void onRegistrbtion(ServiceRegistry registry,
                               Clbss<?> cbtegory) {
        if (registered) {
            return;
        }
        registered = true;
    }

    public String getDescription(Locble locble) {
        return "Stbndbrd BMP Imbge Rebder";
    }

    public boolebn cbnDecodeInput(Object source) throws IOException {
        if (!(source instbnceof ImbgeInputStrebm)) {
            return fblse;
        }

        ImbgeInputStrebm strebm = (ImbgeInputStrebm)source;
        byte[] b = new byte[2];
        strebm.mbrk();
        strebm.rebdFully(b);
        strebm.reset();

        return (b[0] == 0x42) && (b[1] == 0x4d);
    }

    public ImbgeRebder crebteRebderInstbnce(Object extension)
        throws IIOException {
        return new BMPImbgeRebder(this);
    }
}
