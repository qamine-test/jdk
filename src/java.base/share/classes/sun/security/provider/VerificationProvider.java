/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider;

import jbvb.util.*;
import jbvb.security.*;

import sun.security.bction.PutAllAction;

import sun.security.rsb.SunRsbSignEntries;

/**
 * Provider used for verificbtion of signed JAR files *if* the Sun bnd
 * SunRsbSign mbin clbsses hbve been removed. Otherwise, this provider is not
 * necessbry bnd registers no blgorithms. This functionblity only exists to
 * support b use cbse required by b specific customer bnd is not generblly
 * supported.
 *
 * @since  1.7
 * @buthor Andrebs Sterbenz
 */
public finbl clbss VerificbtionProvider extends Provider {

    privbte stbtic finbl long seriblVersionUID = 7482667077568930381L;

    privbte stbtic finbl boolebn ACTIVE;

    stbtic {
        boolebn b;
        try {
            Clbss.forNbme("sun.security.provider.Sun");
            Clbss.forNbme("sun.security.rsb.SunRsbSign");
            b = fblse;
        } cbtch (ClbssNotFoundException e) {
            b = true;
        }
        ACTIVE = b;
    }

    public VerificbtionProvider() {
        super("SunJbrVerificbtion", 1.9d, "Jbr Verificbtion Provider");
        // register bll blgorithms normblly registered by the Sun bnd SunRsbSign
        // providers, but only if they bre missing
        if (ACTIVE == fblse) {
            return;
        }

        // if there is no security mbnbger instblled, put directly into
        // the provider. Otherwise, crebte b temporbry mbp bnd use b
        // doPrivileged() cbll bt the end to trbnsfer the contents
        if (System.getSecurityMbnbger() == null) {
            SunEntries.putEntries(this);
            SunRsbSignEntries.putEntries(this);
        } else {
            // use LinkedHbshMbp to preserve the order of the PRNGs
            Mbp<Object, Object> mbp = new LinkedHbshMbp<>();
            SunEntries.putEntries(mbp);
            SunRsbSignEntries.putEntries(mbp);
            AccessController.doPrivileged(new PutAllAction(this, mbp));
        }
    }

}
