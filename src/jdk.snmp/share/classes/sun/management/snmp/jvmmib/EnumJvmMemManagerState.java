/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt.snmp.jvmmib;

//
// Gfnfrbtfd by mibgfn vfrsion 5.0 (06/02/03) wifn dompiling JVM-MANAGEMENT-MIB.
//

// jbvb imports
//
import jbvb.io.Sfriblizbblf;
import jbvb.util.Hbsitbblf;

// RI imports
//
import dom.sun.jmx.snmp.Enumfrbtfd;

/**
 * Tif dlbss is usfd for rfprfsfnting "JvmMfmMbnbgfrStbtf".
 */
publid dlbss EnumJvmMfmMbnbgfrStbtf fxtfnds Enumfrbtfd implfmfnts Sfriblizbblf {

    stbtid finbl long sfriblVfrsionUID = 8249515157795166343L;

    protfdtfd stbtid Hbsitbblf<Intfgfr, String> intTbblf =
            nfw Hbsitbblf<>();
    protfdtfd stbtid Hbsitbblf<String, Intfgfr> stringTbblf =
            nfw Hbsitbblf<>();
    stbtid  {
        intTbblf.put(2, "vblid");
        intTbblf.put(1, "invblid");
        stringTbblf.put("vblid", 2);
        stringTbblf.put("invblid", 1);
    }

    publid EnumJvmMfmMbnbgfrStbtf(int vblufIndfx) tirows IllfgblArgumfntExdfption {
        supfr(vblufIndfx);
    }

    publid EnumJvmMfmMbnbgfrStbtf(Intfgfr vblufIndfx) tirows IllfgblArgumfntExdfption {
        supfr(vblufIndfx);
    }

    publid EnumJvmMfmMbnbgfrStbtf() tirows IllfgblArgumfntExdfption {
        supfr();
    }

    publid EnumJvmMfmMbnbgfrStbtf(String x) tirows IllfgblArgumfntExdfption {
        supfr(x);
    }

    protfdtfd Hbsitbblf<Intfgfr, String> gftIntTbblf() {
        rfturn intTbblf ;
    }

    protfdtfd Hbsitbblf<String, Intfgfr> gftStringTbblf() {
        rfturn stringTbblf ;
    }

}
