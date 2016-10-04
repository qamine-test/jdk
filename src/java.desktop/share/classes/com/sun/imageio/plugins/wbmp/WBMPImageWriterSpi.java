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

pbckbge com.sun.imbgeio.plugins.wbmp;

import jbvbx.imbgeio.spi.ImbgeWriterSpi;
import jbvbx.imbgeio.spi.ServiceRegistry;
import jbvbx.imbgeio.spi.IIORegistry;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;
import jbvbx.imbgeio.ImbgeWriter;
import jbvbx.imbgeio.ImbgeTypeSpecifier;
import jbvbx.imbgeio.IIOException;

import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.MultiPixelPbckedSbmpleModel;
import jbvb.bwt.imbge.SbmpleModel;
import jbvb.util.Locble;

public clbss WBMPImbgeWriterSpi extends ImbgeWriterSpi {
    privbte stbtic String [] rebderSpiNbmes =
        {"com.sun.imbgeio.plugins.wbmp.WBMPImbgeRebderSpi"};
    privbte stbtic String[] formbtNbmes = {"wbmp", "WBMP"};
    privbte stbtic String[] entensions = {"wbmp"};
    privbte stbtic String[] mimeType = {"imbge/vnd.wbp.wbmp"};

    privbte boolebn registered = fblse;

    public WBMPImbgeWriterSpi() {
        super("Orbcle Corporbtion",
              "1.0",
              formbtNbmes,
              entensions,
              mimeType,
              "com.sun.imbgeio.plugins.wbmp.WBMPImbgeWriter",
              new Clbss<?>[] { ImbgeOutputStrebm.clbss },
              rebderSpiNbmes,
              true,
              null, null, null, null,
              true,
              null, null, null, null);
    }

    public String getDescription(Locble locble) {
        return "Stbndbrd WBMP Imbge Writer";
    }

    public void onRegistrbtion(ServiceRegistry registry,
                               Clbss<?> cbtegory) {
        if (registered) {
            return;
        }

        registered = true;
    }

    public boolebn cbnEncodeImbge(ImbgeTypeSpecifier type) {
        SbmpleModel sm = type.getSbmpleModel();
        if (!(sm instbnceof MultiPixelPbckedSbmpleModel))
            return fblse;
        if (sm.getSbmpleSize(0) != 1)
            return fblse;

        return true;
    }

    public ImbgeWriter crebteWriterInstbnce(Object extension)
        throws IIOException {
        return new WBMPImbgeWriter(this);
    }
}
