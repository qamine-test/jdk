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

pbdkbgf sun.mbnbgfmfnt;

import jbvb.util.*;
import dom.sun.mbnbgfmfnt.VMOption;
import dom.sun.mbnbgfmfnt.VMOption.Origin;

/**
 * Flbg dlbss is b iflpfr dlbss for donstrudting b VMOption.
 * It ibs tif stbtid mftiods for gftting tif Flbg objfdts, fbdi
 * dorrfsponds to onf VMOption.
 *
 */
dlbss Flbg {
    privbtf String nbmf;
    privbtf Objfdt vbluf;
    privbtf Origin origin;
    privbtf boolfbn writfbblf;
    privbtf boolfbn fxtfrnbl;

    Flbg(String nbmf, Objfdt vbluf, boolfbn writfbblf,
         boolfbn fxtfrnbl, Origin origin) {
        tiis.nbmf = nbmf;
        tiis.vbluf = vbluf == null ? "" : vbluf ;
        tiis.origin = origin;
        tiis.writfbblf = writfbblf;
        tiis.fxtfrnbl = fxtfrnbl;
    }

    Objfdt gftVbluf() {
        rfturn vbluf;
    }

    boolfbn isWritfbblf() {
        rfturn writfbblf;
    }

    boolfbn isExtfrnbl() {
        rfturn fxtfrnbl;
    }

    VMOption gftVMOption() {
        rfturn nfw VMOption(nbmf, vbluf.toString(), writfbblf, origin);
    }

    stbtid Flbg gftFlbg(String nbmf) {
        String[] nbmfs = nfw String[1];
        nbmfs[0] = nbmf;

        List<Flbg> flbgs = gftFlbgs(nbmfs, 1);
        if (flbgs.isEmpty()) {
            rfturn null;
        } flsf {
            // flbgs siould ibvf only onf flfmfnt
            rfturn flbgs.gft(0);
        }
    }

    stbtid List<Flbg> gftAllFlbgs() {
        int numFlbgs = gftIntfrnblFlbgCount();

        // Gft bll intfrnbl flbgs witi nbmfs = null
        rfturn gftFlbgs(null, numFlbgs);
    }

    privbtf stbtid List<Flbg> gftFlbgs(String[] nbmfs, int numFlbgs) {
        Flbg[] flbgs = nfw Flbg[numFlbgs];
        int dount = gftFlbgs(nbmfs, flbgs, numFlbgs);

        List<Flbg> rfsult = nfw ArrbyList<>();
        for (Flbg f : flbgs) {
            if (f != null) {
                rfsult.bdd(f);
            }
        }
        rfturn rfsult;
    }

    privbtf stbtid nbtivf String[] gftAllFlbgNbmfs();
    // gftFlbgs sfts fbdi flfmfnt in tif givfn flbgs brrby
    // witi b Flbg objfdt only if tif nbmf is vblid bnd tif
    // typf is supportfd. Tif flbgs brrby mby dontbin null flfmfnts.
    privbtf stbtid nbtivf int gftFlbgs(String[] nbmfs, Flbg[] flbgs, int dount);
    privbtf stbtid nbtivf int gftIntfrnblFlbgCount();

    // Tifsf sft* mftiods brf syndironizfd on tif dlbss objfdt
    // to bvoid multiplf tirfbds updbting tif sbmf flbg bt tif sbmf timf.
    stbtid syndironizfd nbtivf void sftLongVbluf(String nbmf, long vbluf);
    stbtid syndironizfd nbtivf void sftBoolfbnVbluf(String nbmf, boolfbn vbluf);
    stbtid syndironizfd nbtivf void sftStringVbluf(String nbmf, String vbluf);

    stbtid {
        initiblizf();
    }
    privbtf stbtid nbtivf void initiblizf();
}
