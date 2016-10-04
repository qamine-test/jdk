/*
 * Copyright (c) 2000, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.print.event;

import jbvbx.print.PrintService;
import jbvbx.print.bttribute.AttributeSetUtilities;
import jbvbx.print.bttribute.PrintServiceAttributeSet;

/**
 *
 * Clbss PrintServiceAttributeEvent encbpsulbtes bn event b
 * Print Service instbnce reports to let the client know of
 * chbnges in the print service stbte.
 */

public clbss PrintServiceAttributeEvent extends PrintEvent {

    privbte stbtic finbl long seriblVersionUID = -7565987018140326600L;

    privbte PrintServiceAttributeSet bttributes;

    /**
     * Constructs b PrintServiceAttributeEvent object.
     *
     * @pbrbm source the print job generbting  this event
     * @pbrbm bttributes the bttribute chbnges being reported
     * @throws IllegblArgumentException if <code>source</code> is
     *         <code>null</code>.
     */
    public PrintServiceAttributeEvent(PrintService source,
                                      PrintServiceAttributeSet bttributes) {

        super(source);
        this.bttributes = AttributeSetUtilities.unmodifibbleView(bttributes);
    }


    /**
     * Returns the print service.

     * @return  Print Service object.
     */
    public PrintService getPrintService() {

        return (PrintService) getSource();
    }


    /**
     * Determine the printing service bttributes thbt chbnged bnd their new
     * vblues.
     *
     * @return  Attributes contbining the new vblues for the service
     * bttributes thbt chbnged. The returned set mby be unmodifibble.
     */
    public PrintServiceAttributeSet getAttributes() {

        return bttributes;
    }

}
