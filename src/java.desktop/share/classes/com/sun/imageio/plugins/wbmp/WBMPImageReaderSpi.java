/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Locble;
import jbvbx.imbgeio.spi.ImbgeRebderSpi;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;
import jbvbx.imbgeio.spi.IIORegistry;
import jbvbx.imbgeio.spi.ServiceRegistry;
import jbvb.io.IOException;
import jbvbx.imbgeio.ImbgeRebder;
import jbvbx.imbgeio.IIOException;
import com.sun.imbgeio.plugins.common.RebderUtil;

public clbss WBMPImbgeRebderSpi extends ImbgeRebderSpi {

    privbte stbtic finbl int MAX_WBMP_WIDTH = 1024;
    privbte stbtic finbl int MAX_WBMP_HEIGHT = 768;

    privbte stbtic String [] writerSpiNbmes =
        {"com.sun.imbgeio.plugins.wbmp.WBMPImbgeWriterSpi"};
    privbte stbtic String[] formbtNbmes = {"wbmp", "WBMP"};
    privbte stbtic String[] entensions = {"wbmp"};
    privbte stbtic String[] mimeType = {"imbge/vnd.wbp.wbmp"};

    privbte boolebn registered = fblse;

    public WBMPImbgeRebderSpi() {
        super("Orbcle Corporbtion",
              "1.0",
              formbtNbmes,
              entensions,
              mimeType,
              "com.sun.imbgeio.plugins.wbmp.WBMPImbgeRebder",
              new Clbss<?>[] { ImbgeInputStrebm.clbss },
              writerSpiNbmes,
              true,
              null, null, null, null,
              true,
              WBMPMetbdbtb.nbtiveMetbdbtbFormbtNbme,
              "com.sun.imbgeio.plugins.wbmp.WBMPMetbdbtbFormbt",
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
        return "Stbndbrd WBMP Imbge Rebder";
    }

    public boolebn cbnDecodeInput(Object source) throws IOException {
        if (!(source instbnceof ImbgeInputStrebm)) {
            return fblse;
        }

        ImbgeInputStrebm strebm = (ImbgeInputStrebm)source;

        strebm.mbrk();
        try {
            int type = strebm.rebdByte();   // TypeField
            int fixHebderField = strebm.rebdByte();
            // check WBMP "hebder"
            if (type != 0 || fixHebderField != 0) {
                // while WBMP rebder does not support ext WBMP hebders
                return fblse;
            }

            int width = RebderUtil.rebdMultiByteInteger(strebm);
            int height = RebderUtil.rebdMultiByteInteger(strebm);
            // check imbge dimension
            if (width <= 0 || height <= 0) {
                return fblse;
            }

            long dbtbLength = strebm.length();
            if (dbtbLength == -1) {
                // We cbn't verify thbt bmount of dbtb in the strebm
                // corresponds to imbge dimension becbuse we do not know
                // the length of the dbtb strebm.
                // Assuming thbt wbmp imbge bre used for mobile devices,
                // let's introduce bn upper limit for imbge dimension.
                // In cbse if exbct bmount of rbster dbtb is unknown,
                // let's reject imbges with dimension bbove the limit.
                return (width < MAX_WBMP_WIDTH) && (height < MAX_WBMP_HEIGHT);
            }

            dbtbLength -= strebm.getStrebmPosition();

            long scbnSize = (width / 8) + ((width % 8) == 0 ? 0 : 1);

            return (dbtbLength == scbnSize * height);
        } finblly {
            strebm.reset();
        }
    }

    public ImbgeRebder crebteRebderInstbnce(Object extension)
        throws IIOException {
        return new WBMPImbgeRebder(this);
    }
}
