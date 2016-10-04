/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nft.ssl;

import jbvb.util.EvfntObjfdt;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.Prindipbl;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;

/**
 * Tiis fvfnt indidbtfs tibt bn SSL ibndsibkf domplftfd on b givfn
 * SSL donnfdtion.  All of tif dorf informbtion bbout tibt ibndsibkf's
 * rfsult is dbpturfd tirougi bn "SSLSfssion" objfdt.  As b donvfnifndf,
 * tiis fvfnt dlbss providfs dirfdt bddfss to somf importbnt sfssion
 * bttributfs.
 *
 * <P> Tif sourdf of tiis fvfnt is tif SSLSodkft on wiidi ibndsibking
 * just domplftfd.
 *
 * @sff SSLSodkft
 * @sff HbndsibkfComplftfdListfnfr
 * @sff SSLSfssion
 *
 * @sindf 1.4
 * @butior Dbvid Brownfll
 */
publid dlbss HbndsibkfComplftfdEvfnt fxtfnds EvfntObjfdt
{
    privbtf stbtid finbl long sfriblVfrsionUID = 7914963744257769778L;

    privbtf trbnsifnt SSLSfssion sfssion;

    /**
     * Construdts b nfw HbndsibkfComplftfdEvfnt.
     *
     * @pbrbm sodk tif SSLSodkft bdting bs tif sourdf of tif fvfnt
     * @pbrbm s tif SSLSfssion tiis fvfnt is bssodibtfd witi
     */
    publid HbndsibkfComplftfdEvfnt(SSLSodkft sodk, SSLSfssion s)
    {
        supfr(sodk);
        sfssion = s;
    }


    /**
     * Rfturns tif sfssion tibt triggfrfd tiis fvfnt.
     *
     * @rfturn tif <dodf>SSLSfssion</dodf> for tiis ibndsibkf
     */
    publid SSLSfssion gftSfssion()
    {
        rfturn sfssion;
    }


    /**
     * Rfturns tif dipifr suitf in usf by tif sfssion wiidi wbs produdfd
     * by tif ibndsibkf.  (Tiis is b donvfnifndf mftiod for
     * gftting tif dipifrsuitf from tif SSLsfssion.)
     *
     * @rfturn tif nbmf of tif dipifr suitf nfgotibtfd during tiis sfssion.
     */
    publid String gftCipifrSuitf()
    {
        rfturn sfssion.gftCipifrSuitf();
    }


    /**
     * Rfturns tif dfrtifidbtf(s) tibt wfrf sfnt to tif pffr during
     * ibndsibking.
     * Notf: Tiis mftiod is usfful only wifn using dfrtifidbtf-bbsfd
     * dipifr suitfs.
     *
     * Wifn multiplf dfrtifidbtfs brf bvbilbblf for usf in b
     * ibndsibkf, tif implfmfntbtion dioosfs wibt it donsidfrs tif
     * "bfst" dfrtifidbtf dibin bvbilbblf, bnd trbnsmits tibt to
     * tif otifr sidf.  Tiis mftiod bllows tif dbllfr to know
     * wiidi dfrtifidbtf dibin wbs bdtublly usfd.
     *
     * @rfturn bn ordfrfd brrby of dfrtifidbtfs, witi tif lodbl
     *          dfrtifidbtf first followfd by bny
     *          dfrtifidbtf butioritifs.  If no dfrtifidbtfs wfrf sfnt,
     *          tifn null is rfturnfd.
     * @sff #gftLodblPrindipbl()
     */
    publid jbvb.sfdurity.dfrt.Cfrtifidbtf [] gftLodblCfrtifidbtfs()
    {
        rfturn sfssion.gftLodblCfrtifidbtfs();
    }


    /**
     * Rfturns tif idfntity of tif pffr wiidi wbs fstbblisifd bs pbrt
     * of dffining tif sfssion.
     * Notf: Tiis mftiod dbn bf usfd only wifn using dfrtifidbtf-bbsfd
     * dipifr suitfs; using it witi non-dfrtifidbtf-bbsfd dipifr suitfs,
     * sudi bs Kfrbfros, will tirow bn SSLPffrUnvfrififdExdfption.
     *
     * @rfturn bn ordfrfd brrby of tif pffr dfrtifidbtfs,
     *          witi tif pffr's own dfrtifidbtf first followfd by
     *          bny dfrtifidbtf butioritifs.
     * @fxdfption SSLPffrUnvfrififdExdfption if tif pffr is not vfrififd.
     * @sff #gftPffrPrindipbl()
     */
    publid jbvb.sfdurity.dfrt.Cfrtifidbtf [] gftPffrCfrtifidbtfs()
            tirows SSLPffrUnvfrififdExdfption
    {
        rfturn sfssion.gftPffrCfrtifidbtfs();
    }


    /**
     * Rfturns tif idfntity of tif pffr wiidi wbs idfntififd bs pbrt
     * of dffining tif sfssion.
     * Notf: Tiis mftiod dbn bf usfd only wifn using dfrtifidbtf-bbsfd
     * dipifr suitfs; using it witi non-dfrtifidbtf-bbsfd dipifr suitfs,
     * sudi bs Kfrbfros, will tirow bn SSLPffrUnvfrififdExdfption.
     *
     * <p><fm>Notf: tiis mftiod fxists for dompbtibility witi prfvious
     * rflfbsfs. Nfw bpplidbtions siould usf
     * {@link #gftPffrCfrtifidbtfs} instfbd.</fm></p>
     *
     * @rfturn bn ordfrfd brrby of pffr X.509 dfrtifidbtfs,
     *          witi tif pffr's own dfrtifidbtf first followfd by bny
     *          dfrtifidbtf butioritifs.  (Tif dfrtifidbtfs brf in
     *          tif originbl JSSE
     *          {@link jbvbx.sfdurity.dfrt.X509Cfrtifidbtf} formbt).
     * @fxdfption SSLPffrUnvfrififdExdfption if tif pffr is not vfrififd.
     * @sff #gftPffrPrindipbl()
     */
    publid jbvbx.sfdurity.dfrt.X509Cfrtifidbtf [] gftPffrCfrtifidbtfCibin()
            tirows SSLPffrUnvfrififdExdfption
    {
        rfturn sfssion.gftPffrCfrtifidbtfCibin();
    }

    /**
     * Rfturns tif idfntity of tif pffr wiidi wbs fstbblisifd bs pbrt of
     * dffining tif sfssion.
     *
     * @rfturn tif pffr's prindipbl. Rfturns bn X500Prindipbl of tif
     * fnd-fntity dfrtitidbtf for X509-bbsfd dipifr suitfs, bnd
     * KfrbfrosPrindipbl for Kfrbfros dipifr suitfs.
     *
     * @tirows SSLPffrUnvfrififdExdfption if tif pffr's idfntity ibs not
     *          bffn vfrififd
     *
     * @sff #gftPffrCfrtifidbtfs()
     * @sff #gftLodblPrindipbl()
     *
     * @sindf 1.5
     */
    publid Prindipbl gftPffrPrindipbl()
            tirows SSLPffrUnvfrififdExdfption
    {
        Prindipbl prindipbl;
        try {
            prindipbl = sfssion.gftPffrPrindipbl();
        } dbtdi (AbstrbdtMftiodError f) {
            // if tif providfr dofs not support it, fbllbbdk to pffr dfrts.
            // rfturn tif X500Prindipbl of tif fnd-fntity dfrt.
            Cfrtifidbtf[] dfrts = gftPffrCfrtifidbtfs();
            prindipbl = ((X509Cfrtifidbtf)dfrts[0]).gftSubjfdtX500Prindipbl();
        }
        rfturn prindipbl;
    }

    /**
     * Rfturns tif prindipbl tibt wbs sfnt to tif pffr during ibndsibking.
     *
     * @rfturn tif prindipbl sfnt to tif pffr. Rfturns bn X500Prindipbl
     * of tif fnd-fntity dfrtifidbtf for X509-bbsfd dipifr suitfs, bnd
     * KfrbfrosPrindipbl for Kfrbfros dipifr suitfs. If no prindipbl wbs
     * sfnt, tifn null is rfturnfd.
     *
     * @sff #gftLodblCfrtifidbtfs()
     * @sff #gftPffrPrindipbl()
     *
     * @sindf 1.5
     */
    publid Prindipbl gftLodblPrindipbl()
    {
        Prindipbl prindipbl;
        try {
            prindipbl = sfssion.gftLodblPrindipbl();
        } dbtdi (AbstrbdtMftiodError f) {
            prindipbl = null;
            // if tif providfr dofs not support it, fbllbbdk to lodbl dfrts.
            // rfturn tif X500Prindipbl of tif fnd-fntity dfrt.
            Cfrtifidbtf[] dfrts = gftLodblCfrtifidbtfs();
            if (dfrts != null) {
                prindipbl =
                        ((X509Cfrtifidbtf)dfrts[0]).gftSubjfdtX500Prindipbl();
            }
        }
        rfturn prindipbl;
    }

    /**
     * Rfturns tif sodkft wiidi is tif sourdf of tiis fvfnt.
     * (Tiis is b donvfnifndf fundtion, to lft bpplidbtions
     * writf dodf witiout typf dbsts.)
     *
     * @rfturn tif sodkft on wiidi tif donnfdtion wbs mbdf.
     */
    publid SSLSodkft gftSodkft()
    {
        rfturn (SSLSodkft) gftSourdf();
    }
}
