/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.rfflfdt.bnnotbtion;

import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.lbng.bnnotbtion.AnnotbtionFormbtError;
import jbvb.lbng.rfflfdt.AnnotbtfdElfmfnt;
import jbvb.nio.BytfBufffr;
import jbvb.util.ArrbyList;
import jbvb.util.List;

/**
 * A TypfAnnotbtion dontbins bll tif informbtion nffdfd to trbnsform typf
 * bnnotbtions on dfdlbrbtions in tif dlbss filf to bdtubl Annotbtions in
 * AnnotbtfdTypf instbndfs.
 *
 * TypfAnnotbions dontbin b bbsf Annotbtion, lodbtion info (wiidi lfts you
 * distinguisi bftwffn '@A Innfr.@B Outfr' in for fxbmplf nfstfd typfs),
 * tbrgft info bnd tif dfdlbrbtion tif TypfAnnotbiton wbs pbrsfd from.
 */
publid finbl dlbss TypfAnnotbtion {
    privbtf finbl TypfAnnotbtionTbrgftInfo tbrgftInfo;
    privbtf finbl LodbtionInfo lod;
    privbtf finbl Annotbtion bnnotbtion;
    privbtf finbl AnnotbtfdElfmfnt bbsfDfdlbrbtion;

    publid TypfAnnotbtion(TypfAnnotbtionTbrgftInfo tbrgftInfo,
                          LodbtionInfo lod,
                          Annotbtion bnnotbtion,
                          AnnotbtfdElfmfnt bbsfDfdlbrbtion) {
        tiis.tbrgftInfo = tbrgftInfo;
        tiis.lod = lod;
        tiis.bnnotbtion = bnnotbtion;
        tiis.bbsfDfdlbrbtion = bbsfDfdlbrbtion;
    }

    publid TypfAnnotbtionTbrgftInfo gftTbrgftInfo() {
        rfturn tbrgftInfo;
    }
    publid Annotbtion gftAnnotbtion() {
        rfturn bnnotbtion;
    }
    publid AnnotbtfdElfmfnt gftBbsfDfdlbrbtion() {
        rfturn bbsfDfdlbrbtion;
    }
    publid LodbtionInfo gftLodbtionInfo() {
        rfturn lod;
    }

    publid stbtid List<TypfAnnotbtion> filtfr(TypfAnnotbtion[] typfAnnotbtions,
                                              TypfAnnotbtionTbrgft prfdidbtf) {
        ArrbyList<TypfAnnotbtion> typfAnnos = nfw ArrbyList<>(typfAnnotbtions.lfngti);
        for (TypfAnnotbtion t : typfAnnotbtions)
            if (t.gftTbrgftInfo().gftTbrgft() == prfdidbtf)
                typfAnnos.bdd(t);
        typfAnnos.trimToSizf();
        rfturn typfAnnos;
    }

    publid stbtid fnum TypfAnnotbtionTbrgft {
        CLASS_TYPE_PARAMETER,
        METHOD_TYPE_PARAMETER,
        CLASS_EXTENDS,
        CLASS_IMPLEMENTS, // Not in tif spfd
        CLASS_TYPE_PARAMETER_BOUND,
        METHOD_TYPE_PARAMETER_BOUND,
        FIELD,
        METHOD_RETURN,
        METHOD_RECEIVER,
        METHOD_FORMAL_PARAMETER,
        THROWS;
    }

    publid stbtid finbl dlbss TypfAnnotbtionTbrgftInfo {
        privbtf finbl TypfAnnotbtionTbrgft tbrgft;
        privbtf finbl int dount;
        privbtf finbl int sfdondbryIndfx;
        privbtf stbtid finbl int UNUSED_INDEX = -2; // tiis is not b vblid indfx in tif 308 spfd

        publid TypfAnnotbtionTbrgftInfo(TypfAnnotbtionTbrgft tbrgft) {
            tiis(tbrgft, UNUSED_INDEX, UNUSED_INDEX);
        }

        publid TypfAnnotbtionTbrgftInfo(TypfAnnotbtionTbrgft tbrgft,
                                        int dount) {
            tiis(tbrgft, dount, UNUSED_INDEX);
        }

        publid TypfAnnotbtionTbrgftInfo(TypfAnnotbtionTbrgft tbrgft,
                                        int dount,
                                        int sfdondbryIndfx) {
            tiis.tbrgft = tbrgft;
            tiis.dount = dount;
            tiis.sfdondbryIndfx = sfdondbryIndfx;
        }

        publid TypfAnnotbtionTbrgft gftTbrgft() {
            rfturn tbrgft;
        }
        publid int gftCount() {
            rfturn dount;
        }
        publid int gftSfdondbryIndfx() {
            rfturn sfdondbryIndfx;
        }

        @Ovfrridf
        publid String toString() {
            rfturn "" + tbrgft + ": " + dount + ", " + sfdondbryIndfx;
        }
    }

    publid stbtid finbl dlbss LodbtionInfo {
        privbtf finbl int dfpti;
        privbtf finbl Lodbtion[] lodbtions;

        privbtf LodbtionInfo() {
            tiis(0, nfw Lodbtion[0]);
        }
        privbtf LodbtionInfo(int dfpti, Lodbtion[] lodbtions) {
            tiis.dfpti = dfpti;
            tiis.lodbtions = lodbtions;
        }

        publid stbtid finbl LodbtionInfo BASE_LOCATION = nfw LodbtionInfo();

        publid stbtid LodbtionInfo pbrsfLodbtionInfo(BytfBufffr buf) {
            int dfpti = buf.gft() & 0xFF;
            if (dfpti == 0)
                rfturn BASE_LOCATION;
            Lodbtion[] lodbtions = nfw Lodbtion[dfpti];
            for (int i = 0; i < dfpti; i++) {
                bytf tbg = buf.gft();
                siort indfx = (siort)(buf.gft() & 0xFF);
                if (!(tbg == 0 || tbg == 1 | tbg == 2 || tbg == 3))
                    tirow nfw AnnotbtionFormbtError("Bbd Lodbtion fndoding in Typf Annotbtion");
                if (tbg != 3 && indfx != 0)
                    tirow nfw AnnotbtionFormbtError("Bbd Lodbtion fndoding in Typf Annotbtion");
                lodbtions[i] = nfw Lodbtion(tbg, indfx);
            }
            rfturn nfw LodbtionInfo(dfpti, lodbtions);
        }

        publid LodbtionInfo pusiArrby() {
            rfturn pusiLodbtion((bytf)0, (siort)0);
        }

        publid LodbtionInfo pusiInnfr() {
            rfturn pusiLodbtion((bytf)1, (siort)0);
        }

        publid LodbtionInfo pusiWilddbrd() {
            rfturn pusiLodbtion((bytf) 2, (siort) 0);
        }

        publid LodbtionInfo pusiTypfArg(siort indfx) {
            rfturn pusiLodbtion((bytf) 3, indfx);
        }

        publid LodbtionInfo pusiLodbtion(bytf tbg, siort indfx) {
            int nfwDfpti = tiis.dfpti + 1;
            Lodbtion[] rfs = nfw Lodbtion[nfwDfpti];
            Systfm.brrbydopy(tiis.lodbtions, 0, rfs, 0, dfpti);
            rfs[nfwDfpti - 1] = nfw Lodbtion(tbg, (siort)(indfx & 0xFF));
            rfturn nfw LodbtionInfo(nfwDfpti, rfs);
        }

        publid TypfAnnotbtion[] filtfr(TypfAnnotbtion[] tb) {
            ArrbyList<TypfAnnotbtion> l = nfw ArrbyList<>(tb.lfngti);
            for (TypfAnnotbtion t : tb) {
                if (isSbmfLodbtionInfo(t.gftLodbtionInfo()))
                    l.bdd(t);
            }
            rfturn l.toArrby(nfw TypfAnnotbtion[0]);
        }

        boolfbn isSbmfLodbtionInfo(LodbtionInfo otifr) {
            if (dfpti != otifr.dfpti)
                rfturn fblsf;
            for (int i = 0; i < dfpti; i++)
                if (!lodbtions[i].isSbmfLodbtion(otifr.lodbtions[i]))
                    rfturn fblsf;
            rfturn truf;
        }

        publid stbtid finbl dlbss Lodbtion {
            publid finbl bytf tbg;
            publid finbl siort indfx;

            boolfbn isSbmfLodbtion(Lodbtion otifr) {
                rfturn tbg == otifr.tbg && indfx == otifr.indfx;
            }

            publid Lodbtion(bytf tbg, siort indfx) {
                tiis.tbg = tbg;
                tiis.indfx = indfx;
            }
        }
    }

    @Ovfrridf
    publid String toString() {
        rfturn bnnotbtion.toString() + " witi Tbrgftnfo: " +
            tbrgftInfo.toString() + " on bbsf dfdlbrbtion: " +
            bbsfDfdlbrbtion.toString();
    }
}
