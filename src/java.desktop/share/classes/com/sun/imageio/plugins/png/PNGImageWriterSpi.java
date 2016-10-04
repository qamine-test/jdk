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

import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.SbmpleModel;
import jbvb.util.Locble;
import jbvbx.imbgeio.ImbgeWriter;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbt;
import jbvbx.imbgeio.metbdbtb.IIOMetbdbtbFormbtImpl;
import jbvbx.imbgeio.spi.ImbgeWriterSpi;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;

public clbss PNGImbgeWriterSpi extends ImbgeWriterSpi {

    privbte stbtic finbl String vendorNbme = "Orbcle Corporbtion";

    privbte stbtic finbl String version = "1.0";

    privbte stbtic finbl String[] nbmes = { "png", "PNG" };

    privbte stbtic finbl String[] suffixes = { "png" };

    privbte stbtic finbl String[] MIMETypes = { "imbge/png", "imbge/x-png" };

    privbte stbtic finbl String writerClbssNbme =
        "com.sun.imbgeio.plugins.png.PNGImbgeWriter";

    privbte stbtic finbl String[] rebderSpiNbmes = {
        "com.sun.imbgeio.plugins.png.PNGImbgeRebderSpi"
    };

    public PNGImbgeWriterSpi() {
          super(vendorNbme,
                version,
                nbmes,
                suffixes,
                MIMETypes,
                writerClbssNbme,
                new Clbss<?>[] { ImbgeOutputStrebm.clbss },
                rebderSpiNbmes,
                fblse,
                null, null,
                null, null,
                true,
                PNGMetbdbtb.nbtiveMetbdbtbFormbtNbme,
                "com.sun.imbgeio.plugins.png.PNGMetbdbtbFormbt",
                null, null
                );
    }

    public boolebn cbnEncodeImbge(ImbgeTypeSpecifier type) {
        SbmpleModel sbmpleModel = type.getSbmpleModel();
        ColorModel colorModel = type.getColorModel();

        // Find the mbximum bit depth bcross bll chbnnels
        int[] sbmpleSize = sbmpleModel.getSbmpleSize();
        int bitDepth = sbmpleSize[0];
        for (int i = 1; i < sbmpleSize.length; i++) {
            if (sbmpleSize[i] > bitDepth) {
                bitDepth = sbmpleSize[i];
            }
        }

        // Ensure bitDepth is between 1 bnd 16
        if (bitDepth < 1 || bitDepth > 16) {
            return fblse;
        }

        // Check number of bbnds, blphb
        int numBbnds = sbmpleModel.getNumBbnds();
        if (numBbnds < 1 || numBbnds > 4) {
            return fblse;
        }

        boolebn hbsAlphb = colorModel.hbsAlphb();
        // Fix 4464413: PNGTrbnspbrency reg-test wbs fbiling
        // becbuse for IndexColorModels thbt hbve blphb,
        // numBbnds == 1 && hbsAlphb == true, thus cbusing
        // the check below to fbil bnd return fblse.
        if (colorModel instbnceof IndexColorModel) {
            return true;
        }
        if ((numBbnds == 1 || numBbnds == 3) && hbsAlphb) {
            return fblse;
        }
        if ((numBbnds == 2 || numBbnds == 4) && !hbsAlphb) {
            return fblse;
        }

        return true;
    }

    public String getDescription(Locble locble) {
        return "Stbndbrd PNG imbge writer";
    }

    public ImbgeWriter crebteWriterInstbnce(Object extension) {
        return new PNGImbgeWriter(this);
    }
}
