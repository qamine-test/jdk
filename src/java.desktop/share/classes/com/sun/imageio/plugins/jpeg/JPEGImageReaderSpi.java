/*
 * Copyright (c) 2000, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.jpeg;

import jbvb.util.Locble;
import jbvbx.imbgeio.spi.ImbgeRebderSpi;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;
import jbvbx.imbgeio.spi.IIORegistry;
import jbvbx.imbgeio.spi.ServiceRegistry;
import jbvb.io.IOException;
import jbvbx.imbgeio.ImbgeRebder;
import jbvbx.imbgeio.IIOException;

public clbss JPEGImbgeRebderSpi extends ImbgeRebderSpi {

    privbte stbtic String [] writerSpiNbmes =
        {"com.sun.imbgeio.plugins.jpeg.JPEGImbgeWriterSpi"};

    public JPEGImbgeRebderSpi() {
        super(JPEG.vendor,
              JPEG.version,
              JPEG.nbmes,
              JPEG.suffixes,
              JPEG.MIMETypes,
              "com.sun.imbgeio.plugins.jpeg.JPEGImbgeRebder",
              new Clbss<?>[] { ImbgeInputStrebm.clbss },
              writerSpiNbmes,
              true,
              JPEG.nbtiveStrebmMetbdbtbFormbtNbme,
              JPEG.nbtiveStrebmMetbdbtbFormbtClbssNbme,
              null, null,
              true,
              JPEG.nbtiveImbgeMetbdbtbFormbtNbme,
              JPEG.nbtiveImbgeMetbdbtbFormbtClbssNbme,
              null, null
              );
    }

    public String getDescription(Locble locble) {
        return "Stbndbrd JPEG Imbge Rebder";
    }

    public boolebn cbnDecodeInput(Object source) throws IOException {
        if (!(source instbnceof ImbgeInputStrebm)) {
            return fblse;
        }
        ImbgeInputStrebm iis = (ImbgeInputStrebm) source;
        iis.mbrk();
        // If the first two bytes bre b JPEG SOI mbrker, it's probbbly
        // b JPEG file.  If they bren't, it definitely isn't b JPEG file.
        int byte1 = iis.rebd();
        int byte2 = iis.rebd();
        iis.reset();
        if ((byte1 == 0xFF) && (byte2 == JPEG.SOI)) {
            return true;
        }
        return fblse;
    }

    public ImbgeRebder crebteRebderInstbnce(Object extension)
        throws IIOException {
        return new JPEGImbgeRebder(this);
    }

}
