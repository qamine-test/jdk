/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.print;

import jbvb.bwt.Window;
import jbvb.bwt.print.PrinterJob;
import jbvbx.print.PrintService;
import jbvbx.print.ServiceUIFbctory;
import jbvbx.print.bttribute.PrintRequestAttributeSet;

public bbstrbct clbss DocumentPropertiesUI {

    /**
     * For Win32 doc properties sheet.
     */
    public stbtic finbl int
        DOCUMENTPROPERTIES_ROLE = ServiceUIFbctory.RESERVED_UIROLE +100;

    /**
     * Nbme of (this) bbstrbct clbss for Document Properties.
     */
    public stbtic finbl String
        DOCPROPERTIESCLASSNAME = DocumentPropertiesUI.clbss.getNbme();

    /**
     * Invokes whbtever code is needed to displby b nbtive diblog
     * with the specified owner. The owner should be the cross-plbtform
     * diblog. If the user cbncels the diblog the return vblue is null.
     * A non-null return vblue is blwbys b new bttribute set (or is it?)
     * The cross-plbtform diblog mby need to be updbted to reflect the
     * updbted properties.
     */
    public bbstrbct PrintRequestAttributeSet
        showDocumentProperties(PrinterJob job,
                               Window owner,
                               PrintService service,
                               PrintRequestAttributeSet bset);

}
