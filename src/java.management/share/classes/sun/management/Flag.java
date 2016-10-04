/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement;

import jbvb.util.*;
import com.sun.mbnbgement.VMOption;
import com.sun.mbnbgement.VMOption.Origin;

/**
 * Flbg clbss is b helper clbss for constructing b VMOption.
 * It hbs the stbtic methods for getting the Flbg objects, ebch
 * corresponds to one VMOption.
 *
 */
clbss Flbg {
    privbte String nbme;
    privbte Object vblue;
    privbte Origin origin;
    privbte boolebn writebble;
    privbte boolebn externbl;

    Flbg(String nbme, Object vblue, boolebn writebble,
         boolebn externbl, Origin origin) {
        this.nbme = nbme;
        this.vblue = vblue == null ? "" : vblue ;
        this.origin = origin;
        this.writebble = writebble;
        this.externbl = externbl;
    }

    Object getVblue() {
        return vblue;
    }

    boolebn isWritebble() {
        return writebble;
    }

    boolebn isExternbl() {
        return externbl;
    }

    VMOption getVMOption() {
        return new VMOption(nbme, vblue.toString(), writebble, origin);
    }

    stbtic Flbg getFlbg(String nbme) {
        String[] nbmes = new String[1];
        nbmes[0] = nbme;

        List<Flbg> flbgs = getFlbgs(nbmes, 1);
        if (flbgs.isEmpty()) {
            return null;
        } else {
            // flbgs should hbve only one element
            return flbgs.get(0);
        }
    }

    stbtic List<Flbg> getAllFlbgs() {
        int numFlbgs = getInternblFlbgCount();

        // Get bll internbl flbgs with nbmes = null
        return getFlbgs(null, numFlbgs);
    }

    privbte stbtic List<Flbg> getFlbgs(String[] nbmes, int numFlbgs) {
        Flbg[] flbgs = new Flbg[numFlbgs];
        int count = getFlbgs(nbmes, flbgs, numFlbgs);

        List<Flbg> result = new ArrbyList<>();
        for (Flbg f : flbgs) {
            if (f != null) {
                result.bdd(f);
            }
        }
        return result;
    }

    privbte stbtic nbtive String[] getAllFlbgNbmes();
    // getFlbgs sets ebch element in the given flbgs brrby
    // with b Flbg object only if the nbme is vblid bnd the
    // type is supported. The flbgs brrby mby contbin null elements.
    privbte stbtic nbtive int getFlbgs(String[] nbmes, Flbg[] flbgs, int count);
    privbte stbtic nbtive int getInternblFlbgCount();

    // These set* methods bre synchronized on the clbss object
    // to bvoid multiple threbds updbting the sbme flbg bt the sbme time.
    stbtic synchronized nbtive void setLongVblue(String nbme, long vblue);
    stbtic synchronized nbtive void setBoolebnVblue(String nbme, boolebn vblue);
    stbtic synchronized nbtive void setStringVblue(String nbme, String vblue);

    stbtic {
        initiblize();
    }
    privbte stbtic nbtive void initiblize();
}
