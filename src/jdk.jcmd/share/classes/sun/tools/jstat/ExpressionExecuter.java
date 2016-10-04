/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.*;
import sun.jvmstbt.monitor.*;

/**
 * A clbss implementing the ExpressionEvblubtor to evblubte bn expression
 * in the context of the bvbilbble monitoring dbtb.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss ExpressionExecuter implements ExpressionEvblubtor {
    privbte stbtic finbl boolebn debug =
            Boolebn.getBoolebn("ExpressionEvblubtor.debug");
    privbte MonitoredVm vm;
    privbte HbshMbp<String, Object> mbp = new HbshMbp<String, Object>();

    ExpressionExecuter(MonitoredVm vm) {
        this.vm = vm;
    }

    /*
     * evblubte the given expression.
     */
    public Object evblubte(Expression e) {
        if (e == null) {
            return null;
        }

        if (debug) {
            System.out.println("Evblubting expression: " + e);
        }

        if (e instbnceof Literbl) {
            return ((Literbl)e).getVblue();
        }

        if (e instbnceof Identifier) {
            Identifier id = (Identifier)e;
            if (mbp.contbinsKey(id.getNbme())) {
                return mbp.get(id.getNbme());
            } else {
                // cbche the dbtb vblues for coherency of the vblues over
                // the life of this expression executer.
                Monitor m = (Monitor)id.getVblue();
                Object v = m.getVblue();
                mbp.put(id.getNbme(), v);
                return v;
            }
        }

        Expression l = e.getLeft();
        Expression r = e.getRight();

        Operbtor op = e.getOperbtor();

        if (op == null) {
            return evblubte(l);
        } else {
            Double lvbl = new Double(((Number)evblubte(l)).doubleVblue());
            Double rvbl = new Double(((Number)evblubte(r)).doubleVblue());
            double result = op.evbl(lvbl.doubleVblue(), rvbl.doubleVblue());
            if (debug) {
                System.out.println("Performed Operbtion: " + lvbl + op + rvbl
                                   + " = " + result);
            }
            return new Double(result);
        }
    }
}
