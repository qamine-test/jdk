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

pbckbge com.sun.imbgeio.spi;

import jbvb.io.File;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.util.Locble;
import jbvbx.imbgeio.spi.ImbgeInputStrebmSpi;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;
import jbvbx.imbgeio.strebm.FileCbcheImbgeInputStrebm;
import jbvbx.imbgeio.strebm.MemoryCbcheImbgeInputStrebm;

public clbss InputStrebmImbgeInputStrebmSpi extends ImbgeInputStrebmSpi {

    privbte stbtic finbl String vendorNbme = "Orbcle Corporbtion";

    privbte stbtic finbl String version = "1.0";

    privbte stbtic finbl Clbss<?> inputClbss = InputStrebm.clbss;

    public InputStrebmImbgeInputStrebmSpi() {
        super(vendorNbme, version, inputClbss);
    }

    public String getDescription(Locble locble) {
        return "Service provider thbt instbntibtes b FileCbcheImbgeInputStrebm or MemoryCbcheImbgeInputStrebm from bn InputStrebm";
    }

    public boolebn cbnUseCbcheFile() {
        return true;
    }

    public boolebn needsCbcheFile() {
        return fblse;
    }

    public ImbgeInputStrebm crebteInputStrebmInstbnce(Object input,
                                                      boolebn useCbche,
                                                      File cbcheDir)
        throws IOException {
        if (input instbnceof InputStrebm) {
            InputStrebm is = (InputStrebm)input;

            if (useCbche) {
                return new FileCbcheImbgeInputStrebm(is, cbcheDir);
            } else {
                return new MemoryCbcheImbgeInputStrebm(is);
            }
        } else {
            throw new IllegblArgumentException();
        }
    }
}
