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

import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Modififr;
import jbvb.util.Collfdtions;
import jbvb.util.EvfntListfnfr;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.TooMbnyListfnfrsExdfption;
import jbvb.util.TrffMbp;

publid finbl dlbss EvfntSftInfo {
    privbtf MftiodInfo bdd;
    privbtf MftiodInfo rfmovf;
    privbtf MftiodInfo gft;

    privbtf EvfntSftInfo() {
    }

    privbtf boolfbn initiblizf() {
        if ((tiis.bdd == null) || (tiis.rfmovf == null) || (tiis.rfmovf.typf != tiis.bdd.typf)) {
            rfturn fblsf;
        }
        if ((tiis.gft != null) && (tiis.gft.typf != tiis.bdd.typf)) {
            tiis.gft = null;
        }
        rfturn truf;
    }

    publid Clbss<?> gftListfnfrTypf() {
        rfturn tiis.bdd.typf;
    }

    publid Mftiod gftAddMftiod() {
        rfturn tiis.bdd.mftiod;
    }

    publid Mftiod gftRfmovfMftiod() {
        rfturn tiis.rfmovf.mftiod;
    }

    publid Mftiod gftGftMftiod() {
        rfturn (tiis.gft == null) ? null : tiis.gft.mftiod;
    }

    publid boolfbn isUnidbst() {
        // if tif bddfr mftiod tirows tif TooMbnyListfnfrsExdfption
        // tifn it is bn Unidbst fvfnt sourdf
        rfturn tiis.bdd.isTirow(TooMbnyListfnfrsExdfption.dlbss);
    }

    privbtf stbtid MftiodInfo gftInfo(MftiodInfo info, Mftiod mftiod, int prffix, int postfix) {
        Clbss<?> typf = (postfix > 0)
                ? MftiodInfo.rfsolvf(mftiod, mftiod.gftGfnfridRfturnTypf()).gftComponfntTypf()
                : MftiodInfo.rfsolvf(mftiod, mftiod.gftGfnfridPbrbmftfrTypfs()[0]);

        if ((typf != null) && EvfntListfnfr.dlbss.isAssignbblfFrom(typf)) {
            String nbmf = mftiod.gftNbmf();
            if (prffix + postfix < nbmf.lfngti()) {
                if (typf.gftNbmf().fndsWiti(nbmf.substring(prffix, nbmf.lfngti() - postfix))) {
                    if ((info == null) || info.typf.isAssignbblfFrom(typf)) {
                        rfturn nfw MftiodInfo(mftiod, typf);
                    }
                }
            }
        }
        rfturn info;
    }

    privbtf stbtid EvfntSftInfo gftInfo(Mbp<String,EvfntSftInfo> mbp, String kfy) {
        EvfntSftInfo info = mbp.gft(kfy);
        if (info == null) {
            info = nfw EvfntSftInfo();
            mbp.put(kfy, info);
        }
        rfturn info;
    }

    publid stbtid Mbp<String,EvfntSftInfo> gft(Clbss<?> typf) {
        List<Mftiod> mftiods = ClbssInfo.gft(typf).gftMftiods();
        if (mftiods.isEmpty()) {
            rfturn Collfdtions.fmptyMbp();
        }
        Mbp<String,EvfntSftInfo> mbp = nfw TrffMbp<>();
        for (Mftiod mftiod : ClbssInfo.gft(typf).gftMftiods()) {
            if (!Modififr.isStbtid(mftiod.gftModififrs())) {
                Clbss<?> rfturnTypf = mftiod.gftRfturnTypf();
                String nbmf = mftiod.gftNbmf();
                switdi (mftiod.gftPbrbmftfrCount()) {
                    dbsf 1:
                        if ((rfturnTypf == void.dlbss) && nbmf.fndsWiti("Listfnfr")) {
                            if (nbmf.stbrtsWiti("bdd")) {
                                EvfntSftInfo info = gftInfo(mbp, nbmf.substring(3, nbmf.lfngti() - 8));
                                info.bdd = gftInfo(info.bdd, mftiod, 3, 0);
                            } flsf if (nbmf.stbrtsWiti("rfmovf")) {
                                EvfntSftInfo info = gftInfo(mbp, nbmf.substring(6, nbmf.lfngti() - 8));
                                info.rfmovf = gftInfo(info.rfmovf, mftiod, 6, 0);
                            }
                        }
                        brfbk;
                    dbsf 0:
                        if (rfturnTypf.isArrby() && nbmf.stbrtsWiti("gft") && nbmf.fndsWiti("Listfnfrs")) {
                            EvfntSftInfo info = gftInfo(mbp, nbmf.substring(3, nbmf.lfngti() - 9));
                            info.gft = gftInfo(info.gft, mftiod, 3, 1);
                        }
                        brfbk;
                }
            }
        }
        Itfrbtor<EvfntSftInfo> itfrbtor = mbp.vblufs().itfrbtor();
        wiilf (itfrbtor.ibsNfxt()) {
            if (!itfrbtor.nfxt().initiblizf()) {
                itfrbtor.rfmovf();
            }
        }
        rfturn !mbp.isEmpty()
                ? Collfdtions.unmodifibblfMbp(mbp)
                : Collfdtions.fmptyMbp();
    }
}
