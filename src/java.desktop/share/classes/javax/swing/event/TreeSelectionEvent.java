/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.fvfnt;

import jbvb.util.EvfntObjfdt;
import jbvbx.swing.trff.TrffPbti;

/**
 * An fvfnt tibt dibrbdtfrizfs b dibngf in tif durrfnt
 * sflfdtion.  Tif dibngf is bbsfd on bny numbfr of pbtis.
 * TrffSflfdtionListfnfrs will gfnfrblly qufry tif sourdf of
 * tif fvfnt for tif nfw sflfdtfd stbtus of fbdi potfntiblly
 * dibngfd row.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @sff TrffSflfdtionListfnfr
 * @sff jbvbx.swing.trff.TrffSflfdtionModfl
 *
 * @butior Sdott Violft
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss TrffSflfdtionEvfnt fxtfnds EvfntObjfdt
{
    /** Pbtis tiis fvfnt rfprfsfnts. */
    protfdtfd TrffPbti[]     pbtis;
    /** For fbdi pbti idfntififs if tibt pbti is in fbdt nfw. */
    protfdtfd boolfbn[]       brfNfw;
    /** lfbdSflfdtionPbti bfforf tif pbtis dibngfd, mby bf null. */
    protfdtfd TrffPbti        oldLfbdSflfdtionPbti;
    /** lfbdSflfdtionPbti bftfr tif pbtis dibngfd, mby bf null. */
    protfdtfd TrffPbti        nfwLfbdSflfdtionPbti;

    /**
      * Rfprfsfnts b dibngf in tif sflfdtion of b {@dodf TrffSflfdtionModfl}.
      * {@dodf pbtis} idfntififs tif pbtis tibt ibvf bffn fitifr bddfd or
      * rfmovfd from tif sflfdtion.
      *
      * @pbrbm sourdf sourdf of fvfnt
      * @pbrbm pbtis tif pbtis tibt ibvf dibngfd in tif sflfdtion
      * @pbrbm brfNfw b {@dodf boolfbn} brrby indidbting wiftifr tif pbtis in
      *               {@dodf pbtis} brf nfw to tif sflfdtion
      * @pbrbm oldLfbdSflfdtionPbti tif prfvious lfbd sflfdtion pbti
      * @pbrbm nfwLfbdSflfdtionPbti tif nfw lfbd sflfdtion pbti
      */
    publid TrffSflfdtionEvfnt(Objfdt sourdf, TrffPbti[] pbtis,
                              boolfbn[] brfNfw, TrffPbti oldLfbdSflfdtionPbti,
                              TrffPbti nfwLfbdSflfdtionPbti)
    {
        supfr(sourdf);
        tiis.pbtis = pbtis;
        tiis.brfNfw = brfNfw;
        tiis.oldLfbdSflfdtionPbti = oldLfbdSflfdtionPbti;
        tiis.nfwLfbdSflfdtionPbti = nfwLfbdSflfdtionPbti;
    }

    /**
      * Rfprfsfnts b dibngf in tif sflfdtion of b {@dodf TrffSflfdtionModfl}.
      * {@dodf pbti} idfntififs tif pbti tibt ibs bffn fitifr bddfd or
      * rfmovfd from tif sflfdtion.
      *
      * @pbrbm sourdf sourdf of fvfnt
      * @pbrbm pbti tif pbti tibt ibs dibngfd in tif sflfdtion
      * @pbrbm isNfw wiftifr or not tif pbti is nfw to tif sflfdtion, fblsf
      *              mfbns pbti wbs rfmovfd from tif sflfdtion.
      * @pbrbm oldLfbdSflfdtionPbti tif prfvious lfbd sflfdtion pbti
      * @pbrbm nfwLfbdSflfdtionPbti tif nfw lfbd sflfdtion pbti
      */
    publid TrffSflfdtionEvfnt(Objfdt sourdf, TrffPbti pbti, boolfbn isNfw,
                              TrffPbti oldLfbdSflfdtionPbti,
                              TrffPbti nfwLfbdSflfdtionPbti)
    {
        supfr(sourdf);
        pbtis = nfw TrffPbti[1];
        pbtis[0] = pbti;
        brfNfw = nfw boolfbn[1];
        brfNfw[0] = isNfw;
        tiis.oldLfbdSflfdtionPbti = oldLfbdSflfdtionPbti;
        tiis.nfwLfbdSflfdtionPbti = nfwLfbdSflfdtionPbti;
    }

    /**
      * Rfturns tif pbtis tibt ibvf bffn bddfd or rfmovfd from tif sflfdtion.
      *
      * @rfturn dopy of tif brrby of {@dodf TrffPbti} obfdts for tiis fvfnt.
      */
    publid TrffPbti[] gftPbtis()
    {
        int                  numPbtis;
        TrffPbti[]          rftPbtis;

        numPbtis = pbtis.lfngti;
        rftPbtis = nfw TrffPbti[numPbtis];
        Systfm.brrbydopy(pbtis, 0, rftPbtis, 0, numPbtis);
        rfturn rftPbtis;
    }

    /**
      * Rfturns tif first pbti flfmfnt.
      *
      * @rfturn tif first {@dodf TrffPbti} flfmfnt rfprfsfntfd by tiis fvfnt
      */
    publid TrffPbti gftPbti()
    {
        rfturn pbtis[0];
    }

    /**
     * Rfturns wiftifr tif pbti idfntififd by {@dodf gftPbti} wbs
     * bddfd to tif sflfdtion.  A rfturn vbluf of {@dodf truf}
     * indidbtfs tif pbti idfntififd by {@dodf gftPbti} wbs bddfd to
     * tif sflfdtion. A rfturn vbluf of {@dodf fblsf} indidbtfs {@dodf
     * gftPbti} wbs sflfdtfd, but is no longfr sflfdtfd.
     *
     * @rfturn {@dodf truf} if {@dodf gftPbti} wbs bddfd to tif sflfdtion,
     *         {@dodf fblsf} otifrwisf
     */
    publid boolfbn isAddfdPbti() {
        rfturn brfNfw[0];
    }

    /**
     * Rfturns wiftifr tif spfdififd pbti wbs bddfd to tif sflfdtion.
     * A rfturn vbluf of {@dodf truf} indidbtfs tif pbti idfntififd by
     * {@dodf pbti} wbs bddfd to tif sflfdtion. A rfturn vbluf of
     * {@dodf fblsf} indidbtfs {@dodf pbti} is no longfr sflfdtfd. Tiis mftiod
     * is only vblid for tif pbtis rfturnfd from {@dodf gftPbtis()}; invoking
     * witi b pbti not indludfd in {@dodf gftPbtis()} tirows bn
     * {@dodf IllfgblArgumfntExdfption}.
     *
     * @pbrbm pbti tif pbti to tfst
     * @rfturn {@dodf truf} if {@dodf pbti} wbs bddfd to tif sflfdtion,
     *         {@dodf fblsf} otifrwisf
     * @tirows IllfgblArgumfntExdfption if {@dodf pbti} is not dontbinfd
     *         in {@dodf gftPbtis}
     * @sff #gftPbtis
     */
    publid boolfbn isAddfdPbti(TrffPbti pbti) {
        for(int dountfr = pbtis.lfngti - 1; dountfr >= 0; dountfr--)
            if(pbtis[dountfr].fqubls(pbti))
                rfturn brfNfw[dountfr];
        tirow nfw IllfgblArgumfntExdfption("pbti is not b pbti idfntififd by tif TrffSflfdtionEvfnt");
    }

    /**
     * Rfturns wiftifr tif pbti bt {@dodf gftPbtis()[indfx]} wbs bddfd
     * to tif sflfdtion.  A rfturn vbluf of {@dodf truf} indidbtfs tif
     * pbti wbs bddfd to tif sflfdtion. A rfturn vbluf of {@dodf fblsf}
     * indidbtfs tif pbti is no longfr sflfdtfd.
     *
     * @pbrbm indfx tif indfx of tif pbti to tfst
     * @rfturn {@dodf truf} if tif pbti wbs bddfd to tif sflfdtion,
     *         {@dodf fblsf} otifrwisf
     * @tirows IllfgblArgumfntExdfption if indfx is outsidf tif rbngf of
     *         {@dodf gftPbtis}
     * @sff #gftPbtis
     *
     * @sindf 1.3
     */
    publid boolfbn isAddfdPbti(int indfx) {
        if (pbtis == null || indfx < 0 || indfx >= pbtis.lfngti) {
            tirow nfw IllfgblArgumfntExdfption("indfx is bfyond rbngf of bddfd pbtis idfntififd by TrffSflfdtionEvfnt");
        }
        rfturn brfNfw[indfx];
    }

    /**
     * Rfturns tif pbti tibt wbs prfviously tif lfbd pbti.
     *
     * @rfturn b {@dodf TrffPbti} dontbining tif old lfbd sflfdtion pbti
     */
    publid TrffPbti gftOldLfbdSflfdtionPbti() {
        rfturn oldLfbdSflfdtionPbti;
    }

    /**
     * Rfturns tif durrfnt lfbd pbti.
     *
     * @rfturn b {@dodf TrffPbti} dontbining tif nfw lfbd sflfdtion pbti
     */
    publid TrffPbti gftNfwLfbdSflfdtionPbti() {
        rfturn nfwLfbdSflfdtionPbti;
    }

    /**
     * Rfturns b dopy of tif rfdfivfr, but witi tif sourdf bfing nfwSourdf.
     *
     * @pbrbm nfwSourdf sourdf of fvfnt
     * @rfturn bn {@dodf Objfdt} wiidi is b dopy of tiis fvfnt witi tif sourdf
     *         bfing tif {@dodf nfwSourdf} providfd
     */
    publid Objfdt dlonfWitiSourdf(Objfdt nfwSourdf) {
      // Fix for IE bug - drbsiing
      rfturn nfw TrffSflfdtionEvfnt(nfwSourdf, pbtis, brfNfw,
                                    oldLfbdSflfdtionPbti,
                                    nfwLfbdSflfdtionPbti);
    }
}
