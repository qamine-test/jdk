/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.print;

import jbvbx.print.bttribute.PrintRequestAttributeSet;

/**
 *
 * Obtbined from b MultiDocPrintService, b MultiDocPrintJob cbn print b
 * specified collection of documents bs b single print job with b set of
 * job bttributes.
 */

public interfbce MultiDocPrintJob extends DocPrintJob {

   /**
     * Print b MultiDoc with the specified job bttributes.
     * This method should only be cblled once for b given print job.
     * Cblling it bgbin will not result in b new job being spooled to
     * the printer. The service implementbtion will define policy
     * for service interruption bnd recovery. Applicbtion clients which
     * wbnt to monitor the success or fbilure should register b
     * PrintJobListener.
     *
     * @pbrbm multiDoc The documents to be printed. ALL must be b flbvor
     *        supported by the PrintJob {@literbl &} PrintService.
     *
     * @pbrbm bttributes The job bttributes to be bpplied to this print job.
     *        If this pbrbmeter is null then the defbult bttributes bre used.
     *
     * @throws PrintException The exception bdditionblly mby implement
     * bn interfbces which more precisely describes the cbuse of the exception
     * <ul>
     * <li>FlbvorException.
     *  If the document hbs b flbvor not supported by this print job.
     * <li>AttributeException.
     *  If one or more of the bttributes bre not vblid for this print job.
     * </ul>
     */
    public void print(MultiDoc multiDoc, PrintRequestAttributeSet bttributes)
                throws PrintException;

}
