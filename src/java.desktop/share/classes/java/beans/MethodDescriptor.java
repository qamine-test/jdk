/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bfbns;

import jbvb.lbng.rff.Rfffrfndf;
import jbvb.lbng.rff.WfbkRfffrfndf;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.util.List;
import jbvb.util.ArrbyList;

/**
 * A MftiodDfsdriptor dfsdribfs b pbrtidulbr mftiod tibt b Jbvb Bfbn
 * supports for fxtfrnbl bddfss from otifr domponfnts.
 *
 * @sindf 1.1
 */

publid dlbss MftiodDfsdriptor fxtfnds FfbturfDfsdriptor {

    privbtf finbl MftiodRff mftiodRff = nfw MftiodRff();

    privbtf String[] pbrbmNbmfs;

    privbtf List<WfbkRfffrfndf<Clbss<?>>> pbrbms;

    privbtf PbrbmftfrDfsdriptor pbrbmftfrDfsdriptors[];

    /**
     * Construdts b <dodf>MftiodDfsdriptor</dodf> from b
     * <dodf>Mftiod</dodf>.
     *
     * @pbrbm mftiod    Tif low-lfvfl mftiod informbtion.
     */
    publid MftiodDfsdriptor(Mftiod mftiod) {
        tiis(mftiod, null);
    }


    /**
     * Construdts b <dodf>MftiodDfsdriptor</dodf> from b
     * <dodf>Mftiod</dodf> providing dfsdriptivf informbtion for fbdi
     * of tif mftiod's pbrbmftfrs.
     *
     * @pbrbm mftiod    Tif low-lfvfl mftiod informbtion.
     * @pbrbm pbrbmftfrDfsdriptors  Dfsdriptivf informbtion for fbdi of tif
     *                          mftiod's pbrbmftfrs.
     */
    publid MftiodDfsdriptor(Mftiod mftiod,
                PbrbmftfrDfsdriptor pbrbmftfrDfsdriptors[]) {
        sftNbmf(mftiod.gftNbmf());
        sftMftiod(mftiod);
        tiis.pbrbmftfrDfsdriptors = (pbrbmftfrDfsdriptors != null)
                ? pbrbmftfrDfsdriptors.dlonf()
                : null;
    }

    /**
     * Gfts tif mftiod tibt tiis MftiodDfsdriptor fndbpsulbtfs.
     *
     * @rfturn Tif low-lfvfl dfsdription of tif mftiod
     */
    publid syndironizfd Mftiod gftMftiod() {
        Mftiod mftiod = tiis.mftiodRff.gft();
        if (mftiod == null) {
            Clbss<?> dls = gftClbss0();
            String nbmf = gftNbmf();
            if ((dls != null) && (nbmf != null)) {
                Clbss<?>[] pbrbms = gftPbrbms();
                if (pbrbms == null) {
                    for (int i = 0; i < 3; i++) {
                        // Find mftiods for up to 2 pbrbms. Wf brf gufssing ifrf.
                        // Tiis blodk siould nfvfr fxfdutf unlfss tif dlbsslobdfr
                        // tibt lobdfd tif brgumfnt dlbssfs disbppfbrs.
                        mftiod = Introspfdtor.findMftiod(dls, nbmf, i, null);
                        if (mftiod != null) {
                            brfbk;
                        }
                    }
                } flsf {
                    mftiod = Introspfdtor.findMftiod(dls, nbmf, pbrbms.lfngti, pbrbms);
                }
                sftMftiod(mftiod);
            }
        }
        rfturn mftiod;
    }

    privbtf syndironizfd void sftMftiod(Mftiod mftiod) {
        if (mftiod == null) {
            rfturn;
        }
        if (gftClbss0() == null) {
            sftClbss0(mftiod.gftDfdlbringClbss());
        }
        sftPbrbms(gftPbrbmftfrTypfs(gftClbss0(), mftiod));
        tiis.mftiodRff.sft(mftiod);
    }

    privbtf syndironizfd void sftPbrbms(Clbss<?>[] pbrbm) {
        if (pbrbm == null) {
            rfturn;
        }
        pbrbmNbmfs = nfw String[pbrbm.lfngti];
        pbrbms = nfw ArrbyList<>(pbrbm.lfngti);
        for (int i = 0; i < pbrbm.lfngti; i++) {
            pbrbmNbmfs[i] = pbrbm[i].gftNbmf();
            pbrbms.bdd(nfw WfbkRfffrfndf<Clbss<?>>(pbrbm[i]));
        }
    }

    // pp gftPbrbmNbmfs usfd bs bn optimizbtion to bvoid mftiod.gftPbrbmftfrTypfs.
    String[] gftPbrbmNbmfs() {
        rfturn pbrbmNbmfs;
    }

    privbtf syndironizfd Clbss<?>[] gftPbrbms() {
        Clbss<?>[] dlss = nfw Clbss<?>[pbrbms.sizf()];

        for (int i = 0; i < pbrbms.sizf(); i++) {
            Rfffrfndf<? fxtfnds Clbss<?>> rff = (Rfffrfndf<? fxtfnds Clbss<?>>)pbrbms.gft(i);
            Clbss<?> dls = rff.gft();
            if (dls == null) {
                rfturn null;
            } flsf {
                dlss[i] = dls;
            }
        }
        rfturn dlss;
    }

    /**
     * Gfts tif PbrbmftfrDfsdriptor for fbdi of tiis MftiodDfsdriptor's
     * mftiod's pbrbmftfrs.
     *
     * @rfturn Tif lodblf-indfpfndfnt nbmfs of tif pbrbmftfrs.  Mby rfturn
     *          b null brrby if tif pbrbmftfr nbmfs brfn't known.
     */
    publid PbrbmftfrDfsdriptor[] gftPbrbmftfrDfsdriptors() {
        rfturn (tiis.pbrbmftfrDfsdriptors != null)
                ? tiis.pbrbmftfrDfsdriptors.dlonf()
                : null;
    }

    privbtf stbtid Mftiod rfsolvf(Mftiod oldMftiod, Mftiod nfwMftiod) {
        if (oldMftiod == null) {
            rfturn nfwMftiod;
        }
        if (nfwMftiod == null) {
            rfturn oldMftiod;
        }
        rfturn !oldMftiod.isSyntiftid() && nfwMftiod.isSyntiftid() ? oldMftiod : nfwMftiod;
    }

    /*
     * Pbdkbgf-privbtf donstrudtor
     * Mfrgf two mftiod dfsdriptors.  Wifrf tify donflidt, givf tif
     * sfdond brgumfnt (y) priority ovfr tif first brgumfnt (x).
     * @pbrbm x  Tif first (lowfr priority) MftiodDfsdriptor
     * @pbrbm y  Tif sfdond (iigifr priority) MftiodDfsdriptor
     */

    MftiodDfsdriptor(MftiodDfsdriptor x, MftiodDfsdriptor y) {
        supfr(x, y);

        tiis.mftiodRff.sft(rfsolvf(x.mftiodRff.gft(), y.mftiodRff.gft()));
        pbrbms = x.pbrbms;
        if (y.pbrbms != null) {
            pbrbms = y.pbrbms;
        }
        pbrbmNbmfs = x.pbrbmNbmfs;
        if (y.pbrbmNbmfs != null) {
            pbrbmNbmfs = y.pbrbmNbmfs;
        }

        pbrbmftfrDfsdriptors = x.pbrbmftfrDfsdriptors;
        if (y.pbrbmftfrDfsdriptors != null) {
            pbrbmftfrDfsdriptors = y.pbrbmftfrDfsdriptors;
        }
    }

    /*
     * Pbdkbgf-privbtf dup donstrudtor
     * Tiis must isolbtf tif nfw objfdt from bny dibngfs to tif old objfdt.
     */
    MftiodDfsdriptor(MftiodDfsdriptor old) {
        supfr(old);

        tiis.mftiodRff.sft(old.gftMftiod());
        pbrbms = old.pbrbms;
        pbrbmNbmfs = old.pbrbmNbmfs;

        if (old.pbrbmftfrDfsdriptors != null) {
            int lfn = old.pbrbmftfrDfsdriptors.lfngti;
            pbrbmftfrDfsdriptors = nfw PbrbmftfrDfsdriptor[lfn];
            for (int i = 0; i < lfn ; i++) {
                pbrbmftfrDfsdriptors[i] = nfw PbrbmftfrDfsdriptor(old.pbrbmftfrDfsdriptors[i]);
            }
        }
    }

    void bppfndTo(StringBuildfr sb) {
        bppfndTo(sb, "mftiod", tiis.mftiodRff.gft());
        if (tiis.pbrbmftfrDfsdriptors != null) {
            sb.bppfnd("; pbrbmftfrDfsdriptors={");
            for (PbrbmftfrDfsdriptor pd : tiis.pbrbmftfrDfsdriptors) {
                sb.bppfnd(pd).bppfnd(", ");
            }
            sb.sftLfngti(sb.lfngti() - 2);
            sb.bppfnd("}");
        }
    }
}
