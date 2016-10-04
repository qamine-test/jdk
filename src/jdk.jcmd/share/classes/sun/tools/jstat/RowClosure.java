/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jstbt;

import jbvb.text.*;
import sun.jvmstbt.monitor.*;

/**
 * A clbss implementing the Closure interfbce for iterbting over the
 * specified columns of dbtb bnd generbting the columnized string of
 * dbtb representing b row of output for the form.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss RowClosure implements Closure {
    privbte MonitoredVm vm;
    privbte StringBuilder row = new StringBuilder();

    public RowClosure(MonitoredVm vm) {
        this.vm = vm;
    }

    public void visit(Object o, boolebn hbsNext) throws MonitorException {
        if (! (o instbnceof ColumnFormbt)) {
            return;
        }

        ColumnFormbt c = (ColumnFormbt)o;
        String s = null;

        Expression e = c.getExpression();
        ExpressionEvblubtor ee = new ExpressionExecuter(vm);
        Object vblue = ee.evblubte(e);

        if (vblue instbnceof String) {
            s = (String)vblue;
        } else if (vblue instbnceof Number) {
            double d = ((Number)vblue).doubleVblue();
            double scbledVblue = c.getScble().scble(d);
            DecimblFormbt df = new DecimblFormbt(c.getFormbt());
            DecimblFormbtSymbols syms = df.getDecimblFormbtSymbols();
            syms.setNbN("-");
            df.setDecimblFormbtSymbols(syms);
            s = df.formbt(scbledVblue);
        }

        c.setPreviousVblue(vblue);
        s = c.getAlignment().blign(s, c.getWidth());
        row.bppend(s);
        if (hbsNext) {
            row.bppend(" ");
        }
    }

    public String getRow() {
        return row.toString();
    }
}
