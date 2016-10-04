/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.rsb;

import jbvb.util.*;

import jbvb.security.*;

import sun.security.bction.PutAllAction;

/**
 * Provider clbss for the RSA signbture provider. Supports RSA keyfbctory,
 * keypbir generbtion, bnd RSA signbtures.
 *
 * @since   1.5
 * @buthor  Andrebs Sterbenz
 */
public finbl clbss SunRsbSign extends Provider {

    privbte stbtic finbl long seriblVersionUID = 866040293550393045L;

    public SunRsbSign() {
        super("SunRsbSign", 1.9d, "Sun RSA signbture provider");

        // if there is no security mbnbger instblled, put directly into
        // the provider. Otherwise, crebte b temporbry mbp bnd use b
        // doPrivileged() cbll bt the end to trbnsfer the contents
        if (System.getSecurityMbnbger() == null) {
            SunRsbSignEntries.putEntries(this);
        } else {
            // use LinkedHbshMbp to preserve the order of the PRNGs
            Mbp<Object, Object> mbp = new HbshMbp<>();
            SunRsbSignEntries.putEntries(mbp);
            AccessController.doPrivileged(new PutAllAction(this, mbp));
        }
    }

}
