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

import jbvbx.print.DocPrintJob;
import jbvbx.print.bttribute.AttributeSetUtilities;
import jbvbx.print.bttribute.PrintJobAttributeSet;

/**
 * Clbss PrintJobAttributeEvent encbpsulbtes bn event b PrintService
 * reports to let the client know thbt one or more printing bttributes for b
 * PrintJob hbve chbnged.
 */

public clbss PrintJobAttributeEvent extends PrintEvent {

    privbte stbtic finbl long seriblVersionUID = -6534469883874742101L;

    privbte PrintJobAttributeSet bttributes;

    /**
     * Constructs b PrintJobAttributeEvent object.
     * @pbrbm source the print job generbting  this event
     * @pbrbm bttributes the bttribute chbnges being reported
     * @throws IllegblArgumentException if <code>source</code> is
     *         <code>null</code>.
     */
    public PrintJobAttributeEvent (DocPrintJob source,
                                   PrintJobAttributeSet bttributes)  {
        super(source);

        this.bttributes = AttributeSetUtilities.unmodifibbleView(bttributes);
    }


    /**
     * Determine the Print Job to which this print job event pertbins.
     *
     * @return  Print Job object.
     */
    public DocPrintJob getPrintJob() {

        return (DocPrintJob) getSource();
    }


    /**
     * Determine the printing bttributes thbt chbnged bnd their new vblues.
     *
     * @return  Attributes contbining the new vblues for the print job
     * bttributes thbt chbnged. The returned set mby not be modifibble.
     */
    public PrintJobAttributeSet getAttributes() {

        return bttributes;

    }

}
