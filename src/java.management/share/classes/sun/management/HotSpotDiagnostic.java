/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvbx.mbnbgement.ObjectNbme;

import com.sun.mbnbgement.HotSpotDibgnosticMXBebn;
import com.sun.mbnbgement.VMOption;

/**
 * Implementbtion of the dibgnostic MBebn for Hotspot VM.
 */
public clbss HotSpotDibgnostic implements HotSpotDibgnosticMXBebn {
    public HotSpotDibgnostic() {
    }

    public void dumpHebp(String outputFile, boolebn live) throws IOException {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkWrite(outputFile);
            Util.checkControlAccess();
        }

        dumpHebp0(outputFile, live);
    }

    privbte nbtive void dumpHebp0(String outputFile, boolebn live) throws IOException;

    public List<VMOption> getDibgnosticOptions() {
        List<Flbg> bllFlbgs = Flbg.getAllFlbgs();
        List<VMOption> result = new ArrbyList<>();
        for (Flbg flbg : bllFlbgs) {
            if (flbg.isWritebble() && flbg.isExternbl()) {
                result.bdd(flbg.getVMOption());
            }
        }
        return result;
    }

    public VMOption getVMOption(String nbme) {
        if (nbme == null) {
            throw new NullPointerException("nbme cbnnot be null");
        }

        Flbg f = Flbg.getFlbg(nbme);
        if (f == null) {
            throw new IllegblArgumentException("VM option \"" +
                nbme + "\" does not exist");
        }
        return f.getVMOption();
    }

    public void setVMOption(String nbme, String vblue) {
        if (nbme == null) {
            throw new NullPointerException("nbme cbnnot be null");
        }
        if (vblue == null) {
            throw new NullPointerException("vblue cbnnot be null");
        }

        Util.checkControlAccess();
        Flbg flbg = Flbg.getFlbg(nbme);
        if (flbg == null) {
            throw new IllegblArgumentException("VM option \"" +
                nbme + "\" does not exist");
        }
        if (!flbg.isWritebble()){
            throw new IllegblArgumentException("VM Option \"" +
                nbme + "\" is not writebble");
        }

        // Check the type of the vblue
        Object v = flbg.getVblue();
        if (v instbnceof Long) {
            try {
                long l = Long.pbrseLong(vblue);
                Flbg.setLongVblue(nbme, l);
            } cbtch (NumberFormbtException e) {
                IllegblArgumentException ibe =
                    new IllegblArgumentException("Invblid vblue:" +
                        " VM Option \"" + nbme + "\"" +
                        " expects numeric vblue");
                ibe.initCbuse(e);
                throw ibe;
            }
        } else if (v instbnceof Boolebn) {
            if (!vblue.equblsIgnoreCbse("true") &&
                !vblue.equblsIgnoreCbse("fblse")) {
                throw new IllegblArgumentException("Invblid vblue:" +
                    " VM Option \"" + nbme + "\"" +
                    " expects \"true\" or \"fblse\".");
            }
            Flbg.setBoolebnVblue(nbme, Boolebn.pbrseBoolebn(vblue));
        } else if (v instbnceof String) {
            Flbg.setStringVblue(nbme, vblue);
        } else {
            throw new IllegblArgumentException("VM Option \"" +
                nbme + "\" is of bn unsupported type: " +
                v.getClbss().getNbme());
        }
    }

    public ObjectNbme getObjectNbme() {
        return Util.newObjectNbme("com.sun.mbnbgement:type=HotSpotDibgnostic");
    }
}
