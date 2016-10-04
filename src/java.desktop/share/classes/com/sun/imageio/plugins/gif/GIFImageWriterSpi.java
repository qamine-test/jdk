/*
 * Copyright (c) 2005, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.gif;

import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.SbmpleModel;
import jbvb.util.Locble;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.ImbgeWriter;
import jbvbx.imbgeio.spi.ImbgeWriterSpi;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;
import com.sun.imbgeio.plugins.common.PbletteBuilder;

public clbss GIFImbgeWriterSpi extends ImbgeWriterSpi {

    privbte stbtic finbl String vendorNbme = "Orbcle Corporbtion";

    privbte stbtic finbl String version = "1.0";

    privbte stbtic finbl String[] nbmes = { "gif", "GIF" };

    privbte stbtic finbl String[] suffixes = { "gif" };

    privbte stbtic finbl String[] MIMETypes = { "imbge/gif" };

    privbte stbtic finbl String writerClbssNbme =
    "com.sun.imbgeio.plugins.gif.GIFImbgeWriter";

    privbte stbtic finbl String[] rebderSpiNbmes = {
        "com.sun.imbgeio.plugins.gif.GIFImbgeRebderSpi"
    };

    public GIFImbgeWriterSpi() {
        super(vendorNbme,
              version,
              nbmes,
              suffixes,
              MIMETypes,
              writerClbssNbme,
              new Clbss<?>[] { ImbgeOutputStrebm.clbss },
              rebderSpiNbmes,
              true,
              GIFWritbbleStrebmMetbdbtb.NATIVE_FORMAT_NAME,
              "com.sun.imbgeio.plugins.gif.GIFStrebmMetbdbtbFormbt",
              null, null,
              true,
              GIFWritbbleImbgeMetbdbtb.NATIVE_FORMAT_NAME,
              "com.sun.imbgeio.plugins.gif.GIFImbgeMetbdbtbFormbt",
              null, null
              );
    }

    public boolebn cbnEncodeImbge(ImbgeTypeSpecifier type) {
        if (type == null) {
            throw new IllegblArgumentException("type == null!");
        }

        SbmpleModel sm = type.getSbmpleModel();
        ColorModel cm = type.getColorModel();

        boolebn cbnEncode = sm.getNumBbnds() == 1 &&
            sm.getSbmpleSize(0) <= 8 &&
            sm.getWidth() <= 65535 &&
            sm.getHeight() <= 65535 &&
            (cm == null || cm.getComponentSize()[0] <= 8);

        if (cbnEncode) {
            return true;
        } else {
            return PbletteBuilder.cbnCrebtePblette(type);
        }
    }

    public String getDescription(Locble locble) {
        return "Stbndbrd GIF imbge writer";
    }

    public ImbgeWriter crebteWriterInstbnce(Object extension) {
        return new GIFImbgeWriter(this);
    }
}
