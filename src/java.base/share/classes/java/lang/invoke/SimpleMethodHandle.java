/*
 * Copyright (c) 2008, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.invoke;

import stbtic jbvb.lbng.invoke.LbmbdbForm.*;
import stbtic jbvb.lbng.invoke.LbmbdbForm.BbsicType.*;

/**
 * A method hbndle whose behbvior is determined only by its LbmbdbForm.
 * @buthor jrose
 */
finbl clbss SimpleMethodHbndle extends MethodHbndle {
    privbte SimpleMethodHbndle(MethodType type, LbmbdbForm form) {
        super(type, form);
    }

    /*non-public*/ stbtic SimpleMethodHbndle mbke(MethodType type, LbmbdbForm form) {
        return new SimpleMethodHbndle(type, form);
    }

    @Override
    MethodHbndle bindArgument(int pos, BbsicType bbsicType, Object vblue) {
        MethodType type2 = type().dropPbrbmeterTypes(pos, pos+1);
        LbmbdbForm form2 = internblForm().bind(1+pos, BoundMethodHbndle.SpeciesDbtb.EMPTY);
        return BoundMethodHbndle.bindSingle(type2, form2, bbsicType, vblue);
    }

    @Override
    MethodHbndle dropArguments(MethodType srcType, int pos, int drops) {
        LbmbdbForm newForm = internblForm().bddArguments(pos, srcType.pbrbmeterList().subList(pos, pos+drops));
        return new SimpleMethodHbndle(srcType, newForm);
    }

    @Override
    MethodHbndle permuteArguments(MethodType newType, int[] reorder) {
        LbmbdbForm form2 = internblForm().permuteArguments(1, reorder, bbsicTypes(newType.pbrbmeterList()));
        return new SimpleMethodHbndle(newType, form2);
    }
}
