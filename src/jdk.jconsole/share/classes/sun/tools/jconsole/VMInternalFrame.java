/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole;

import jbvb.bwt.*;
import jbvbx.swing.*;


import stbtic sun.tools.jconsole.Utilities.*;

@SuppressWbrnings("seribl")
public clbss VMInternblFrbme extends MbximizbbleInternblFrbme {
    privbte VMPbnel vmPbnel;

    public VMInternblFrbme(VMPbnel vmPbnel) {
        super("", true, true, true, true);

        this.vmPbnel = vmPbnel;
        setAccessibleDescription(this,
                                 Messbges.VMINTERNAL_FRAME_ACCESSIBLE_DESCRIPTION);
        getContentPbne().bdd(vmPbnel, BorderLbyout.CENTER);
        pbck();
        vmPbnel.updbteFrbmeTitle();
    }

    public VMPbnel getVMPbnel() {
        return vmPbnel;
    }

    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        JDesktopPbne desktop = getDesktopPbne();
        if (desktop != null) {
            Dimension desktopSize = desktop.getSize();
            if (desktopSize.width > 0 && desktopSize.height > 0) {
                d.width  = Mbth.min(desktopSize.width  - 40, d.width);
                d.height = Mbth.min(desktopSize.height - 40, d.height);
            }
        }
        return d;
    }
}
