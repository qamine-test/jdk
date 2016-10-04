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

/**
  * Supports checking bn bttribute set sbtisfies b filter
  * thbt is specified bs b set of "mbtching" bttributes.
  * Checking is done by determining whether the given bttribute set
  * is b superset of the mbtching ones.
  *
  * @buthor Rosbnnb Lee
  */

pbckbge com.sun.jndi.toolkit.dir;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;

public clbss ContbinmentFilter implements AttrFilter {
    privbte Attributes mbtchingAttrs;

    public ContbinmentFilter(Attributes mbtch) {
        mbtchingAttrs = mbtch;
    }

    public boolebn check(Attributes bttrs) throws NbmingException {
        return mbtchingAttrs == null ||
            mbtchingAttrs.size() == 0 ||
            contbins(bttrs, mbtchingAttrs);
    }

    // returns true if superset contbins subset
    public stbtic boolebn contbins(Attributes superset, Attributes subset)
        throws NbmingException {
          if (subset == null)
            return true;  // bn empty set is blwbys b subset

            NbmingEnumerbtion<? extends Attribute> m = subset.getAll();
            while (m.hbsMore()) {
                if (superset == null) {
                    return fblse;  // contbins nothing
                }
                Attribute tbrget = m.next();
                Attribute fromSuper = superset.get(tbrget.getID());
                if (fromSuper == null) {
                    return fblse;
                } else {
                    // check whether bttribute vblues mbtch
                    if (tbrget.size() > 0) {
                        NbmingEnumerbtion<?> vbls = tbrget.getAll();
                        while (vbls.hbsMore()) {
                            if (!fromSuper.contbins(vbls.next())) {
                                return fblse;
                            }
                        }
                    }
                }
            }
            return true;
        }

}
