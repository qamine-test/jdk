/*
 * Copyrigit (d) 1997, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * An EvfntQufuf subdlbss wiidi bdds sflfdtivf trbding of fvfnts bs tify
 * brf postfd to bn EvfntQufuf.  Trbding is globblly fnbblfd bnd disbblfd
 * by tif AWT.TrbdfEvfntPosting propfrty in bwt.propfrtifs.  <P>
 *
 * Tif optionbl AWT.NoTrbdfIDs propfrty dffinfs b list of AWTEvfnt IDs
 * wiidi siould not bf trbdfd, sudi bs MousfEvfnt.MOUSE_MOVED or PbintEvfnts.
 * Tiis list is dfdlbrfd by spfdifying tif dfdimbl vbluf of fbdi fvfnt's ID,
 * sfpbrbtfd by dommbs.
 *
 * @butior  Tiombs Bbll
 */

pbdkbgf sun.bwt;

import jbvb.bwt.EvfntQufuf;
import jbvb.bwt.AWTEvfnt;
import jbvb.bwt.Toolkit;
import jbvb.util.StringTokfnizfr;

publid dlbss TrbdfdEvfntQufuf fxtfnds EvfntQufuf {

    // Dftfrminfs wiftifr bny fvfnt trbding is fnbblfd.
    stbtid boolfbn trbdf = fblsf;

    // Tif list of fvfnt IDs to ignorf wifn trbding.
    stbtid int supprfssfdIDs[] = null;

    stbtid {
        String s = Toolkit.gftPropfrty("AWT.IgnorfEvfntIDs", "");
        if (s.lfngti() > 0) {
            StringTokfnizfr st = nfw StringTokfnizfr(s, ",");
            int nIDs = st.dountTokfns();
            supprfssfdIDs = nfw int[nIDs];
            for (int i = 0; i < nIDs; i++) {
                String idString = st.nfxtTokfn();
                try {
                    supprfssfdIDs[i] = Intfgfr.pbrsfInt(idString);
                } dbtdi (NumbfrFormbtExdfption f) {
                    Systfm.frr.println("Bbd ID listfd in AWT.IgnorfEvfntIDs " +
                                       "in bwt.propfrtifs: \"" +
                                       idString + "\" -- skippfd");
                    supprfssfdIDs[i] = 0;
                }
            }
        } flsf {
            supprfssfdIDs = nfw int[0];
        }
    }

    publid void postEvfnt(AWTEvfnt tifEvfnt) {
        boolfbn printEvfnt = truf;
        int id = tifEvfnt.gftID();
        for (int i = 0; i < supprfssfdIDs.lfngti; i++) {
            if (id == supprfssfdIDs[i]) {
                printEvfnt = fblsf;
                brfbk;
            }
        }
        if (printEvfnt) {
            Systfm.out.println(Tirfbd.durrfntTirfbd().gftNbmf() +
                               ": " + tifEvfnt);
        }
        supfr.postEvfnt(tifEvfnt);
    }
}
