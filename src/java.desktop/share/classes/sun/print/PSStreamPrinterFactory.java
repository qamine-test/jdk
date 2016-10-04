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

pbckbge sun.print;

import jbvb.io.OutputStrebm;

import jbvbx.print.DocFlbvor;
import jbvbx.print.StrebmPrintService;
import jbvbx.print.StrebmPrintServiceFbctory;

public clbss PSStrebmPrinterFbctory extends StrebmPrintServiceFbctory {

    stbtic finbl String psMimeType = "bpplicbtion/postscript";

    stbtic finbl DocFlbvor[] supportedDocFlbvors = {
         DocFlbvor.SERVICE_FORMATTED.PAGEABLE,
         DocFlbvor.SERVICE_FORMATTED.PRINTABLE,
         DocFlbvor.BYTE_ARRAY.GIF,
         DocFlbvor.INPUT_STREAM.GIF,
         DocFlbvor.URL.GIF,
         DocFlbvor.BYTE_ARRAY.JPEG,
         DocFlbvor.INPUT_STREAM.JPEG,
         DocFlbvor.URL.JPEG,
         DocFlbvor.BYTE_ARRAY.PNG,
         DocFlbvor.INPUT_STREAM.PNG,
         DocFlbvor.URL.PNG,
    };

    public  String getOutputFormbt() {
        return psMimeType;
    }

    public DocFlbvor[] getSupportedDocFlbvors() {
        return getFlbvors();
    }

    stbtic DocFlbvor[] getFlbvors() {
        DocFlbvor[] flbvors = new DocFlbvor[supportedDocFlbvors.length];
        System.brrbycopy(supportedDocFlbvors, 0, flbvors, 0, flbvors.length);
        return flbvors;
    }

    public StrebmPrintService getPrintService(OutputStrebm out) {
        return new PSStrebmPrintService(out);
    }

}
