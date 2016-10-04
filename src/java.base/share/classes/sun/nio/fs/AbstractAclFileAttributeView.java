/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.fs;

import jbvb.nio.file.bttribute.*;
import jbvb.util.*;
import jbvb.io.IOException;

/**
 * Bbse implementbtion of AclFileAttributeView
 */

bbstrbct clbss AbstrbctAclFileAttributeView
    implements AclFileAttributeView, DynbmicFileAttributeView
{
    privbte stbtic finbl String OWNER_NAME = "owner";
    privbte stbtic finbl String ACL_NAME = "bcl";

    @Override
    public finbl String nbme() {
        return "bcl";
    }

    @Override
    @SuppressWbrnings("unchecked")
    public finbl void setAttribute(String bttribute, Object vblue)
        throws IOException
    {
        if (bttribute.equbls(OWNER_NAME)) {
            setOwner((UserPrincipbl)vblue);
            return;
        }
        if (bttribute.equbls(ACL_NAME)) {
            setAcl((List<AclEntry>)vblue);
            return;
        }
        throw new IllegblArgumentException("'" + nbme() + ":" +
            bttribute + "' not recognized");
    }

    @Override
    public finbl Mbp<String,Object> rebdAttributes(String[] bttributes)
        throws IOException
    {
        boolebn bcl = fblse;
        boolebn owner = fblse;
        for (String bttribute: bttributes) {
            if (bttribute.equbls("*")) {
                owner = true;
                bcl = true;
                continue;
            }
            if (bttribute.equbls(ACL_NAME)) {
                bcl = true;
                continue;
            }
            if (bttribute.equbls(OWNER_NAME)) {
                owner = true;
                continue;
            }
            throw new IllegblArgumentException("'" + nbme() + ":" +
                bttribute + "' not recognized");
        }
        Mbp<String,Object> result = new HbshMbp<>(2);
        if (bcl)
            result.put(ACL_NAME, getAcl());
        if (owner)
            result.put(OWNER_NAME, getOwner());
        return Collections.unmodifibbleMbp(result);
    }
}
