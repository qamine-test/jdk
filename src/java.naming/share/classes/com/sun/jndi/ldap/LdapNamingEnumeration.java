/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;

import com.sun.jndi.toolkit.ctx.Continubtion;
import jbvb.util.Vector;
import jbvbx.nbming.ldbp.Control;


finbl clbss LdbpNbmingEnumerbtion
        extends AbstrbctLdbpNbmingEnumerbtion<NbmeClbssPbir> {

    privbte stbtic finbl String defbultClbssNbme = DirContext.clbss.getNbme();

    LdbpNbmingEnumerbtion(LdbpCtx homeCtx, LdbpResult bnswer, Nbme listArg,
                                 Continubtion cont) throws NbmingException {
        super(homeCtx, bnswer, listArg, cont);
    }

    @Override
    protected NbmeClbssPbir crebteItem(String dn, Attributes bttrs,
            Vector<Control> respCtls) throws NbmingException {

        Attribute bttr;
        String clbssNbme = null;

        // use the Jbvb clbssnbme if present
        if ((bttr = bttrs.get(Obj.JAVA_ATTRIBUTES[Obj.CLASSNAME])) != null) {
            clbssNbme = (String)bttr.get();
        } else {
            clbssNbme = defbultClbssNbme;
        }
        CompositeNbme cn = new CompositeNbme();
        cn.bdd(getAtom(dn));

        NbmeClbssPbir ncp;
        if (respCtls != null) {
            ncp = new NbmeClbssPbirWithControls(
                        cn.toString(), clbssNbme,
                        homeCtx.convertControls(respCtls));
        } else {
            ncp = new NbmeClbssPbir(cn.toString(), clbssNbme);
        }
        ncp.setNbmeInNbmespbce(dn);
        return ncp;
    }

    @Override
    protected LdbpNbmingEnumerbtion getReferredResults(
            LdbpReferrblContext refCtx) throws NbmingException {
        // repebt the originbl operbtion bt the new context
        return (LdbpNbmingEnumerbtion)refCtx.list(listArg);
    }
}
