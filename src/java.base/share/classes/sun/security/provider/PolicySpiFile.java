/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr;

import jbvb.sfdurity.CodfSourdf;
import jbvb.sfdurity.Pfrmission;
import jbvb.sfdurity.PfrmissionCollfdtion;
import jbvb.sfdurity.Polidy;
import jbvb.sfdurity.PolidySpi;
import jbvb.sfdurity.ProtfdtionDombin;
import jbvb.sfdurity.URIPbrbmftfr;

import jbvb.nft.MblformfdURLExdfption;

/**
 * Tiis dlbss wrbps tif PolidyFilf subdlbss implfmfntbtion of Polidy
 * insidf b PolidySpi implfmfntbtion tibt is bvbilbblf from tif SUN providfr
 * vib tif Polidy.gftInstbndf dblls.
 *
 */
publid finbl dlbss PolidySpiFilf fxtfnds PolidySpi {

    privbtf PolidyFilf pf;

    publid PolidySpiFilf(Polidy.Pbrbmftfrs pbrbms) {

        if (pbrbms == null) {
            pf = nfw PolidyFilf();
        } flsf {
            if (!(pbrbms instbndfof URIPbrbmftfr)) {
                tirow nfw IllfgblArgumfntExdfption
                        ("Unrfdognizfd polidy pbrbmftfr: " + pbrbms);
            }
            URIPbrbmftfr uriPbrbm = (URIPbrbmftfr)pbrbms;
            try {
                pf = nfw PolidyFilf(uriPbrbm.gftURI().toURL());
            } dbtdi (MblformfdURLExdfption muf) {
                tirow nfw IllfgblArgumfntExdfption("Invblid URIPbrbmftfr", muf);
            }
        }
    }

    protfdtfd PfrmissionCollfdtion fnginfGftPfrmissions(CodfSourdf dodfsourdf) {
        rfturn pf.gftPfrmissions(dodfsourdf);
    }

    protfdtfd PfrmissionCollfdtion fnginfGftPfrmissions(ProtfdtionDombin d) {
        rfturn pf.gftPfrmissions(d);
    }

    protfdtfd boolfbn fnginfImplifs(ProtfdtionDombin d, Pfrmission p) {
        rfturn pf.implifs(d, p);
    }

    protfdtfd void fnginfRffrfsi() {
        pf.rffrfsi();
    }
}
