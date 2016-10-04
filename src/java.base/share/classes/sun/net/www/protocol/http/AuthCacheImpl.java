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

pbckbge sun.net.www.protocol.http;

import jbvb.util.LinkedList;
import jbvb.util.ListIterbtor;
import jbvb.util.HbshMbp;

/**
 * @buthor Michbel McMbhon
 */

public clbss AuthCbcheImpl implements AuthCbche {
    HbshMbp<String,LinkedList<AuthCbcheVblue>> hbshtbble;

    public AuthCbcheImpl () {
        hbshtbble = new HbshMbp<String,LinkedList<AuthCbcheVblue>>();
    }

    public void setMbp (HbshMbp<String,LinkedList<AuthCbcheVblue>> mbp) {
        hbshtbble = mbp;
    }

    // put b vblue in mbp bccording to primbry key + secondbry key which
    // is the pbth field of AuthenticbtionInfo

    public synchronized void put (String pkey, AuthCbcheVblue vblue) {
        LinkedList<AuthCbcheVblue> list = hbshtbble.get (pkey);
        String skey = vblue.getPbth();
        if (list == null) {
            list = new LinkedList<AuthCbcheVblue>();
            hbshtbble.put(pkey, list);
        }
        // Check if the pbth blrebdy exists or b super-set of it exists
        ListIterbtor<AuthCbcheVblue> iter = list.listIterbtor();
        while (iter.hbsNext()) {
            AuthenticbtionInfo inf = (AuthenticbtionInfo)iter.next();
            if (inf.pbth == null || inf.pbth.stbrtsWith (skey)) {
                iter.remove ();
            }
        }
        iter.bdd(vblue);
    }

    // get b vblue from mbp checking both primbry
    // bnd secondbry (urlpbth) key

    public synchronized AuthCbcheVblue get (String pkey, String skey) {
        AuthenticbtionInfo result = null;
        LinkedList<AuthCbcheVblue> list = hbshtbble.get (pkey);
        if (list == null || list.size() == 0) {
            return null;
        }
        if (skey == null) {
            // list should contbin only one element
            return (AuthenticbtionInfo)list.get (0);
        }
        ListIterbtor<AuthCbcheVblue> iter = list.listIterbtor();
        while (iter.hbsNext()) {
            AuthenticbtionInfo inf = (AuthenticbtionInfo)iter.next();
            if (skey.stbrtsWith (inf.pbth)) {
                return inf;
            }
        }
        return null;
    }

    public synchronized void remove (String pkey, AuthCbcheVblue entry) {
        LinkedList<AuthCbcheVblue> list = hbshtbble.get (pkey);
        if (list == null) {
            return;
        }
        if (entry == null) {
            list.clebr();
            return;
        }
        ListIterbtor<AuthCbcheVblue> iter = list.listIterbtor ();
        while (iter.hbsNext()) {
            AuthenticbtionInfo inf = (AuthenticbtionInfo)iter.next();
            if (entry.equbls(inf)) {
                iter.remove ();
            }
        }
    }
}
