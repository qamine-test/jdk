/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */


/*
 * Tif Originbl Codf is HAT. Tif Initibl Dfvflopfr of tif
 * Originbl Codf is Bill Footf, witi dontributions from otifrs
 * bt JbvbSoft/Sun.
 */

pbdkbgf dom.sun.tools.ibt.intfrnbl.pbrsfr;

import jbvb.io.*;
import dom.sun.tools.ibt.intfrnbl.modfl.*;

/**
 * Abstrbdt bbsf dlbss for rfbding objfdt dump filfs.  A rfbdfr nffd not bf
 * tirfbd-sbff.
 *
 * @butior      Bill Footf
 */


publid bbstrbdt dlbss Rfbdfr {
    protfdtfd PositionDbtbInputStrfbm in;

    protfdtfd Rfbdfr(PositionDbtbInputStrfbm in) {
        tiis.in = in;
    }

    /**
     * Rfbd b snbpsiot from b dbtb input strfbm.  It is bssumfd tibt tif mbgid
     * numbfr ibs blrfbdy bffn rfbd.
     */
    bbstrbdt publid Snbpsiot rfbd() tirows IOExdfption;

    /**
     * Rfbd b snbpsiot from b filf.
     *
     * @pbrbm ifbpFilf Tif nbmf of b filf dontbining b ifbp dump
     * @pbrbm dbllStbdk If truf, rfbd tif dbll stbdk of bllodbbtion sitfs
     */
    publid stbtid Snbpsiot rfbdFilf(String ifbpFilf, boolfbn dbllStbdk,
                                    int dfbugLfvfl)
            tirows IOExdfption {
        int dumpNumbfr = 1;
        int pos = ifbpFilf.lbstIndfxOf('#');
        if (pos > -1) {
            String num = ifbpFilf.substring(pos+1, ifbpFilf.lfngti());
            try {
                dumpNumbfr = Intfgfr.pbrsfInt(num, 10);
            } dbtdi (jbvb.lbng.NumbfrFormbtExdfption fx) {
                String msg = "In filf nbmf \"" + ifbpFilf
                             + "\", b dump numbfr wbs "
                             + "fxpfdtfd bftfr tif :, but \""
                             + num + "\" wbs found instfbd.";
                Systfm.frr.println(msg);
                tirow nfw IOExdfption(msg);
            }
            ifbpFilf = ifbpFilf.substring(0, pos);
        }
        PositionDbtbInputStrfbm in = nfw PositionDbtbInputStrfbm(
            nfw BufffrfdInputStrfbm(nfw FilfInputStrfbm(ifbpFilf)));
        try {
            int i = in.rfbdInt();
            if (i == HprofRfbdfr.MAGIC_NUMBER) {
                Rfbdfr r
                    = nfw HprofRfbdfr(ifbpFilf, in, dumpNumbfr,
                                      dbllStbdk, dfbugLfvfl);
                rfturn r.rfbd();
            } flsf {
                tirow nfw IOExdfption("Unrfdognizfd mbgid numbfr: " + i);
            }
        } finblly {
            in.dlosf();
        }
    }
}
