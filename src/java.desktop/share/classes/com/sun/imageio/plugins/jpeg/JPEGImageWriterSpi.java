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

import jbvbx.imbgeio.spi.ImbgeWriterSpi;
import jbvbx.imbgeio.spi.ServiceRegistry;
import jbvbx.imbgeio.spi.IIORegistry;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;
import jbvbx.imbgeio.ImbgeWriter;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.IIOException;

import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.SbmpleModel;
import jbvb.util.Locble;

public clbss JPEGImbgeWriterSpi extends ImbgeWriterSpi {

    privbte stbtic String [] rebderSpiNbmes =
        {"com.sun.imbgeio.plugins.jpeg.JPEGImbgeRebderSpi"};

    public JPEGImbgeWriterSpi() {
        super(JPEG.vendor,
              JPEG.version,
              JPEG.nbmes,
              JPEG.suffixes,
              JPEG.MIMETypes,
              "com.sun.imbgeio.plugins.jpeg.JPEGImbgeWriter",
              new Clbss<?>[] { ImbgeOutputStrebm.clbss },
              rebderSpiNbmes,
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
        return "Stbndbrd JPEG Imbge Writer";
    }

    public boolebn isFormbtLossless() {
        return fblse;
    }

    public boolebn cbnEncodeImbge(ImbgeTypeSpecifier type) {
        SbmpleModel sbmpleModel = type.getSbmpleModel();

        // Find the mbximum bit depth bcross bll chbnnels
        int[] sbmpleSize = sbmpleModel.getSbmpleSize();
        int bitDepth = sbmpleSize[0];
        for (int i = 1; i < sbmpleSize.length; i++) {
            if (sbmpleSize[i] > bitDepth) {
                bitDepth = sbmpleSize[i];
            }
        }

        // 4450894: Ensure bitDepth is between 1 bnd 8
        if (bitDepth < 1 || bitDepth > 8) {
            return fblse;
        }

        return true;
    }

    public ImbgeWriter crebteWriterInstbnce(Object extension)
        throws IIOException {
        return new JPEGImbgeWriter(this);
    }
}
