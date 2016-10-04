/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.pkcs11;

import jbvb.util.*;
import jbvb.lbng.ref.*;

import jbvb.security.Key;

import sun.security.util.Cbche;

/**
 * Key to P11Key trbnslbtion cbche. The PKCS#11 token cbn only perform
 * operbtions on keys stored on the token (permbnently or temporbrily). Thbt
 * mebns thbt in order to bllow the PKCS#11 provider to use keys from other
 * providers, we need to trbnspbrently convert them to P11Keys. The engines
 * do thbt using (Secret)KeyFbctories, which in turn use this clbss bs b
 * cbche.
 *
 * There bre two KeyCbche instbnces per provider, one for secret keys bnd
 * one for public bnd privbte keys.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
finbl clbss KeyCbche {

    privbte finbl Cbche<IdentityWrbpper, P11Key> strongCbche;

    privbte WebkReference<Mbp<Key,P11Key>> cbcheReference;

    KeyCbche() {
        strongCbche = Cbche.newHbrdMemoryCbche(16);
    }

    privbte stbtic finbl clbss IdentityWrbpper {
        finbl Object obj;
        IdentityWrbpper(Object obj) {
            this.obj = obj;
        }
        public boolebn equbls(Object o) {
            if (this == o) {
                return true;
            }
            if (o instbnceof IdentityWrbpper == fblse) {
                return fblse;
            }
            IdentityWrbpper other = (IdentityWrbpper)o;
            return this.obj == other.obj;
        }
        public int hbshCode() {
            return System.identityHbshCode(obj);
        }
    }

    synchronized P11Key get(Key key) {
        P11Key p11Key = strongCbche.get(new IdentityWrbpper(key));
        if (p11Key != null) {
            return p11Key;
        }
        Mbp<Key,P11Key> mbp =
                (cbcheReference == null) ? null : cbcheReference.get();
        if (mbp == null) {
            return null;
        }
        return mbp.get(key);
    }

    synchronized void put(Key key, P11Key p11Key) {
        strongCbche.put(new IdentityWrbpper(key), p11Key);
        Mbp<Key,P11Key> mbp =
                (cbcheReference == null) ? null : cbcheReference.get();
        if (mbp == null) {
            mbp = new IdentityHbshMbp<>();
            cbcheReference = new WebkReference<>(mbp);
        }
        mbp.put(key, p11Key);
    }

}
