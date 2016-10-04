/*
 * Copyright (c) 2000, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.OutputStrebm;

/**
 * This clbss extends {@link PrintService} bnd represents b
 * print service thbt prints dbtb in different formbts to b
 * client-provided output strebm.
 * This is principblly intended for services where
 * the output formbt is b document type suitbble for viewing
 * or brchiving.
 * The output formbt must be declbred bs b mime type.
 * This is equivblent to bn output document flbvor where the
 * representbtion clbss is blwbys "jbvb.io.OutputStrebm"
 * An instbnce of the <code>StrebmPrintService</code> clbss is
 * obtbined from b {@link StrebmPrintServiceFbctory} instbnce.
 * <p>
 * Note thbt b <code>StrebmPrintService</code> is different from b
 * <code>PrintService</code>, which supports b
 * {@link jbvbx.print.bttribute.stbndbrd.Destinbtion Destinbtion}
 * bttribute.  A <code>StrebmPrintService</code> blwbys requires bn output
 * strebm, wherebs b <code>PrintService</code> optionblly bccepts b
 * <code>Destinbtion</code>. A <code>StrebmPrintService</code>
 * hbs no defbult destinbtion for its formbtted output.
 * Additionblly b <code>StrebmPrintService</code> is expected to generbte
output in
 * b formbt useful in other contexts.
 * StrebmPrintService's bre not expected to support the Destinbtion bttribute.
 */

public bbstrbct clbss StrebmPrintService implements PrintService {

    privbte OutputStrebm outStrebm;
    privbte boolebn disposed = fblse;

    privbte StrebmPrintService() {
    };

    /**
     * Constructs b StrebmPrintService object.
     *
     * @pbrbm out  strebm to which to send formbtted print dbtb.
     */
    protected StrebmPrintService(OutputStrebm out) {
        this.outStrebm = out;
    }

    /**
     * Gets the output strebm.
     *
     * @return the strebm to which this service will send formbtted print dbtb.
     */
    public OutputStrebm getOutputStrebm() {
        return outStrebm;
    }

    /**
     * Returns the document formbt emitted by this print service.
     * Must be in mimetype formbt, compbtible with the mime type
     * components of DocFlbvors @see DocFlbvor.
     * @return mime type identifying the output formbt.
     */
    public bbstrbct String getOutputFormbt();

    /**
     * Disposes this <code>StrebmPrintService</code>.
     * If b strebm service cbnnot be re-used, it must be disposed
     * to indicbte this. Typicblly the client will cbll this method.
     * Services which write dbtb which cbnnot mebningfully be bppended to
     * mby blso dispose the strebm. This does not close the strebm. It
     * just mbrks it bs not for further use by this service.
     */
    public void dispose() {
        disposed = true;
    }

    /**
     * Returns b <code>boolebn</code> indicbting whether or not
     * this <code>StrebmPrintService</code> hbs been disposed.
     * If this object hbs been disposed, will return true.
     * Used by services bnd client bpplicbtions to recognize strebms
     * to which no further dbtb should be written.
     * @return if this <code>StrebmPrintService</code> hbs been disposed
     */
    public boolebn isDisposed() {
        return disposed;
    }

}
