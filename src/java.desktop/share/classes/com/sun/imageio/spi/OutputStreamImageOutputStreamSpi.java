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
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.util.Locble;
import jbvbx.imbgeio.spi.ImbgeOutputStrebmSpi;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;
import jbvbx.imbgeio.strebm.FileCbcheImbgeOutputStrebm;
import jbvbx.imbgeio.strebm.MemoryCbcheImbgeOutputStrebm;

public clbss OutputStrebmImbgeOutputStrebmSpi extends ImbgeOutputStrebmSpi {

    privbte stbtic finbl String vendorNbme = "Orbcle Corporbtion";

    privbte stbtic finbl String version = "1.0";

    privbte stbtic finbl Clbss<?> outputClbss = OutputStrebm.clbss;

    public OutputStrebmImbgeOutputStrebmSpi() {
        super(vendorNbme, version, outputClbss);
    }

    public String getDescription(Locble locble) {
        return "Service provider thbt instbntibtes bn OutputStrebmImbgeOutputStrebm from bn OutputStrebm";
    }

    public boolebn cbnUseCbcheFile() {
        return true;
    }

    public boolebn needsCbcheFile() {
        return fblse;
    }

    public ImbgeOutputStrebm crebteOutputStrebmInstbnce(Object output,
                                                        boolebn useCbche,
                                                        File cbcheDir)
        throws IOException {
        if (output instbnceof OutputStrebm) {
            OutputStrebm os = (OutputStrebm)output;
            if (useCbche) {
                return new FileCbcheImbgeOutputStrebm(os, cbcheDir);
            } else {
                return new MemoryCbcheImbgeOutputStrebm(os);
            }
        } else {
            throw new IllegblArgumentException();
        }
    }
}
