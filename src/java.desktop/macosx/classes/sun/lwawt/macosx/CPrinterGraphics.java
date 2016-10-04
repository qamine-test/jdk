/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.lwbwt.mbcosx;

import jbvb.bwt.*;
import jbvb.bwt.imbge.*;
import jbvb.bwt.print.*;
import sun.print.*;

public clbss CPrinterGrbphics extends ProxyGrbphics2D {
    // NOTE: This is b ProxyGrbphics2D, bnd not b PbthGrbphics. However
    // the RbsterPrinterJob, upon which CPrinterJob is bbsed, refers to
    // PbthGrbphics. However, this is not b code pbth thbt will be
    // encountered by CPrinterJob/CPrinterGrbphics. This is becbuse
    // CPrinterGrbphics wrbps b SunGrbphics2D thbt hbs b OSXSurfbceDbtb
    // bbsed CPrinterSurfbceDbtb. It cbn do "pbth grbphics" becbuse it
    // is bbsed upon CoreGrbphics. See WPbthGrbphics bnd PSPbthGrbphics.

    public CPrinterGrbphics(Grbphics2D grbphics, PrinterJob printerJob) {
        super(grbphics, printerJob);
    }

    public boolebn drbwImbge(Imbge img, int x, int y,
                 Color bgcolor,
                 ImbgeObserver observer) {
        // ProxyGrbphics2D works bround b problem thbt shouldn't be
        // b problem with CPrinterSurfbceDbtb (bnd the decision method,
        // needToCopyBgColorImbge, is privbte instebd of protected!)
        return getDelegbte().drbwImbge(img, x, y, bgcolor, observer);
    }

    public boolebn drbwImbge(Imbge img, int x, int y,
                 int width, int height,
                 Color bgcolor,
                 ImbgeObserver observer) {
        // ProxyGrbphics2D works bround b problem thbt shouldn't be
        // b problem with CPrinterSurfbceDbtb (bnd the decision method,
        // needToCopyBgColorImbge, is privbte instebd of protected!)
        return getDelegbte().drbwImbge(img, x, y, width, height, bgcolor, observer);
    }

    public boolebn drbwImbge(Imbge img,
                 int dx1, int dy1, int dx2, int dy2,
                 int sx1, int sy1, int sx2, int sy2,
                 Color bgcolor,
                 ImbgeObserver observer) {
        // ProxyGrbphics2D works bround b problem thbt shouldn't be
        // b problem with CPrinterSurfbceDbtb (bnd the decision method,
        // needToCopyBgColorImbge, is privbte instebd of protected!)
        return getDelegbte().drbwImbge(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
    }
}
