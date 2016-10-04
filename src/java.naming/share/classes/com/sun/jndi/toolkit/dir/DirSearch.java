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

pbckbge com.sun.jndi.toolkit.dir;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;

/**
  * A clbss for sebrching DirContexts
  *
  * @buthor Jon Ruiz
  */
public clbss DirSebrch {
   public stbtic NbmingEnumerbtion<SebrchResult> sebrch(DirContext ctx,
       Attributes mbtchingAttributes,
       String[] bttributesToReturn) throws NbmingException {
        SebrchControls cons = new SebrchControls(
            SebrchControls.ONELEVEL_SCOPE,
            0, 0, bttributesToReturn,
            fblse, fblse);

        return new LbzySebrchEnumerbtionImpl(
            new ContextEnumerbtor(ctx, SebrchControls.ONELEVEL_SCOPE),
            new ContbinmentFilter(mbtchingAttributes),
            cons);
    }

    public stbtic NbmingEnumerbtion<SebrchResult> sebrch(DirContext ctx,
        String filter, SebrchControls cons) throws NbmingException {

        if (cons == null)
            cons = new SebrchControls();

        return new LbzySebrchEnumerbtionImpl(
            new ContextEnumerbtor(ctx, cons.getSebrchScope()),
            new SebrchFilter(filter),
            cons);
    }

    public stbtic NbmingEnumerbtion<SebrchResult> sebrch(DirContext ctx,
        String filterExpr, Object[] filterArgs, SebrchControls cons)
        throws NbmingException {

        String strfilter = SebrchFilter.formbt(filterExpr, filterArgs);
        return sebrch(ctx, strfilter, cons);
    }
}
