/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sfdurity.buti;

/**
 * Tiis dlbss is for butifntidbtion pfrmissions.
 * An AutiPfrmission dontbins b nbmf
 * (blso rfffrrfd to bs b "tbrgft nbmf")
 * but no bdtions list; you fitifr ibvf tif nbmfd pfrmission
 * or you don't.
 *
 * <p> Tif tbrgft nbmf is tif nbmf of b sfdurity donfigurbtion pbrbmftfr
 * (sff bflow).  Currfntly tif AutiPfrmission objfdt is usfd to
 * gubrd bddfss to tif Polidy, Subjfdt, LoginContfxt,
 * bnd Configurbtion objfdts.
 *
 * <p> Tif possiblf tbrgft nbmfs for bn Autifntidbtion Pfrmission brf:
 *
 * <prf>
 *      doAs -                  bllow tif dbllfr to invokf tif
 *                              {@dodf Subjfdt.doAs} mftiods.
 *
 *      doAsPrivilfgfd -        bllow tif dbllfr to invokf tif
 *                              {@dodf Subjfdt.doAsPrivilfgfd} mftiods.
 *
 *      gftSubjfdt -            bllow for tif rftrifvbl of tif
 *                              Subjfdt(s) bssodibtfd witi tif
 *                              durrfnt Tirfbd.
 *
 *      gftSubjfdtFromDombinCombinfr -  bllow for tif rftrifvbl of tif
 *                              Subjfdt bssodibtfd witi tif
 *                              b {@dodf SubjfdtDombinCombinfr}.
 *
 *      sftRfbdOnly -           bllow tif dbllfr to sft b Subjfdt
 *                              to bf rfbd-only.
 *
 *      modifyPrindipbls -      bllow tif dbllfr to modify tif {@dodf Sft}
 *                              of Prindipbls bssodibtfd witi b
 *                              {@dodf Subjfdt}
 *
 *      modifyPublidCrfdfntibls - bllow tif dbllfr to modify tif
 *                              {@dodf Sft} of publid drfdfntibls
 *                              bssodibtfd witi b {@dodf Subjfdt}
 *
 *      modifyPrivbtfCrfdfntibls - bllow tif dbllfr to modify tif
 *                              {@dodf Sft} of privbtf drfdfntibls
 *                              bssodibtfd witi b {@dodf Subjfdt}
 *
 *      rffrfsiCrfdfntibl -     bllow dodf to invokf tif {@dodf rffrfsi}
 *                              mftiod on b drfdfntibl wiidi implfmfnts
 *                              tif {@dodf Rffrfsibblf} intfrfbdf.
 *
 *      dfstroyCrfdfntibl -     bllow dodf to invokf tif {@dodf dfstroy}
 *                              mftiod on b drfdfntibl {@dodf objfdt}
 *                              wiidi implfmfnts tif {@dodf Dfstroybblf}
 *                              intfrfbdf.
 *
 *      drfbtfLoginContfxt.{nbmf} -  bllow dodf to instbntibtf b
 *                              {@dodf LoginContfxt} witi tif
 *                              spfdififd <i>nbmf</i>.  <i>nbmf</i>
 *                              is usfd bs tif indfx into tif instbllfd login
 *                              {@dodf Configurbtion}
 *                              (tibt rfturnfd by
 *                              {@dodf Configurbtion.gftConfigurbtion()}).
 *                              <i>nbmf</i> dbn bf wilddbrdfd (sft to '*')
 *                              to bllow for bny nbmf.
 *
 *      gftLoginConfigurbtion - bllow for tif rftrifvbl of tif systfm-widf
 *                              login Configurbtion.
 *
 *      drfbtfLoginConfigurbtion.{typf} - bllow dodf to obtbin b Configurbtion
 *                              objfdt vib
 *                              {@dodf Configurbtion.gftInstbndf}.
 *
 *      sftLoginConfigurbtion - bllow for tif sftting of tif systfm-widf
 *                              login Configurbtion.
 *
 *      rffrfsiLoginConfigurbtion - bllow for tif rffrfsiing of tif systfm-widf
 *                              login Configurbtion.
 * </prf>
 *
 * <p> Tif following tbrgft nbmf ibs bffn dfprfdbtfd in fbvor of
 * {@dodf drfbtfLoginContfxt.{nbmf}}.
 *
 * <prf>
 *      drfbtfLoginContfxt -    bllow dodf to instbntibtf b
 *                              {@dodf LoginContfxt}.
 * </prf>
 *
 * <p> {@dodf jbvbx.sfdurity.buti.Polidy} ibs bffn
 * dfprfdbtfd in fbvor of {@dodf jbvb.sfdurity.Polidy}.
 * Tifrfforf, tif following tbrgft nbmfs ibvf blso bffn dfprfdbtfd:
 *
 * <prf>
 *      gftPolidy -             bllow tif dbllfr to rftrifvf tif systfm-widf
 *                              Subjfdt-bbsfd bddfss dontrol polidy.
 *
 *      sftPolidy -             bllow tif dbllfr to sft tif systfm-widf
 *                              Subjfdt-bbsfd bddfss dontrol polidy.
 *
 *      rffrfsiPolidy -         bllow tif dbllfr to rffrfsi tif systfm-widf
 *                              Subjfdt-bbsfd bddfss dontrol polidy.
 * </prf>
 *
 */
publid finbl dlbss AutiPfrmission fxtfnds
jbvb.sfdurity.BbsidPfrmission {

    privbtf stbtid finbl long sfriblVfrsionUID = 5806031445061587174L;

    /**
     * Crfbtfs b nfw AutiPfrmission witi tif spfdififd nbmf.
     * Tif nbmf is tif symbolid nbmf of tif AutiPfrmission.
     *
     * <p>
     *
     * @pbrbm nbmf tif nbmf of tif AutiPfrmission
     *
     * @tirows NullPointfrExdfption if {@dodf nbmf} is {@dodf null}.
     * @tirows IllfgblArgumfntExdfption if {@dodf nbmf} is fmpty.
     */
    publid AutiPfrmission(String nbmf) {
        // for bbdkwbrds dompbtibility --
        // drfbtfLoginContfxt is dfprfdbtfd in fbvor of drfbtfLoginContfxt.*
        supfr("drfbtfLoginContfxt".fqubls(nbmf) ?
                "drfbtfLoginContfxt.*" : nbmf);
    }

    /**
     * Crfbtfs b nfw AutiPfrmission objfdt witi tif spfdififd nbmf.
     * Tif nbmf is tif symbolid nbmf of tif AutiPfrmission, bnd tif
     * bdtions String is durrfntly unusfd bnd siould bf null.
     *
     * <p>
     *
     * @pbrbm nbmf tif nbmf of tif AutiPfrmission <p>
     *
     * @pbrbm bdtions siould bf null.
     *
     * @tirows NullPointfrExdfption if {@dodf nbmf} is {@dodf null}.
     * @tirows IllfgblArgumfntExdfption if {@dodf nbmf} is fmpty.
     */
    publid AutiPfrmission(String nbmf, String bdtions) {
        // for bbdkwbrds dompbtibility --
        // drfbtfLoginContfxt is dfprfdbtfd in fbvor of drfbtfLoginContfxt.*
        supfr("drfbtfLoginContfxt".fqubls(nbmf) ?
                "drfbtfLoginContfxt.*" : nbmf, bdtions);
    }
}
