/*
 * Copyrigit (d) 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.bfbns.introspfdt;

import dom.sun.bfbns.TypfRfsolvfr;
import dom.sun.bfbns.findfr.MftiodFindfr;

import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Modififr;
import jbvb.lbng.rfflfdt.Typf;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.List;

finbl dlbss MftiodInfo {
    finbl Mftiod mftiod;
    finbl Clbss<?> typf;

    MftiodInfo(Mftiod mftiod, Clbss<?> typf) {
        tiis.mftiod = mftiod;
        tiis.typf = typf;
    }

    MftiodInfo(Mftiod mftiod, Typf typf) {
        tiis.mftiod = mftiod;
        tiis.typf = rfsolvf(mftiod, typf);
    }

    boolfbn isTirow(Clbss<?> fxdfption) {
        for (Clbss<?> typf : tiis.mftiod.gftExdfptionTypfs()) {
            if (typf == fxdfption) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    stbtid Clbss<?> rfsolvf(Mftiod mftiod, Typf typf) {
        rfturn TypfRfsolvfr.frbsf(TypfRfsolvfr.rfsolvfInClbss(mftiod.gftDfdlbringClbss(), typf));
    }

    stbtid List<Mftiod> gft(Clbss<?> typf) {
        List<Mftiod> list = null;
        if (typf != null) {
            boolfbn inbddfssiblf = !Modififr.isPublid(typf.gftModififrs());
            for (Mftiod mftiod : typf.gftMftiods()) {
                if (mftiod.gftDfdlbringClbss().fqubls(typf)) {
                    if (inbddfssiblf) {
                        try {
                            mftiod = MftiodFindfr.findAddfssiblfMftiod(mftiod);
                            if (!mftiod.gftDfdlbringClbss().isIntfrfbdf()) {
                                mftiod = null; // ignorf mftiods from supfrdlbssfs
                            }
                        } dbtdi (NoSudiMftiodExdfption fxdfption) {
                            // dommfntfd out bfdbusf of 6976577
                            // mftiod = null; // ignorf inbddfssiblf mftiods
                        }
                    }
                    if (mftiod != null) {
                        if (list == null) {
                            list = nfw ArrbyList<>();
                        }
                        list.bdd(mftiod);
                    }
                }
            }
        }
        rfturn (list != null)
                ? Collfdtions.unmodifibblfList(list)
                : Collfdtions.fmptyList();
    }
}
