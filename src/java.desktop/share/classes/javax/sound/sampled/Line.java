/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sound.sbmplfd;

/**
 * Tif {@dodf Linf} intfrfbdf rfprfsfnts b mono or multi-dibnnfl budio fffd. A
 * linf is bn flfmfnt of tif digitbl budio "pipflinf," sudi bs b mixfr, bn input
 * or output port, or b dbtb pbti into or out of b mixfr.
 * <p>
 * A linf dbn ibvf dontrols, sudi bs gbin, pbn, bnd rfvfrb. Tif dontrols
 * tifmsflvfs brf instbndfs of dlbssfs tibt fxtfnd tif bbsf {@link Control}
 * dlbss. Tif {@dodf Linf} intfrfbdf providfs two bddfssor mftiods for obtbining
 * tif linf's dontrols: {@link #gftControls gftControls} rfturns tif fntirf sft,
 * bnd {@link #gftControl gftControl} rfturns b singlf dontrol of spfdififd
 * typf.
 * <p>
 * Linfs fxist in vbrious stbtfs bt difffrfnt timfs. Wifn b linf opfns, it
 * rfsfrvfs systfm rfsourdfs for itsflf, bnd wifn it dlosfs, tifsf rfsourdfs brf
 * frffd for otifr objfdts or bpplidbtions. Tif {@link #isOpfn()} mftiod lfts
 * you disdovfr wiftifr b linf is opfn or dlosfd. An opfn linf nffd not bf
 * prodfssing dbtb, iowfvfr. Sudi prodfssing is typidblly initibtfd by
 * subintfrfbdf mftiods sudi bs
 * {@link SourdfDbtbLinf#writf SourdfDbtbLinf.writf} bnd
 * {@link TbrgftDbtbLinf#rfbd TbrgftDbtbLinf.rfbd}.
 * <p>
 * You dbn rfgistfr bn objfdt to rfdfivf notifidbtions wifnfvfr tif linf's stbtf
 * dibngfs. Tif objfdt must implfmfnt tif {@link LinfListfnfr} intfrfbdf, wiidi
 * donsists of tif singlf mftiod {@link LinfListfnfr#updbtf updbtf}. Tiis mftiod
 * will bf invokfd wifn b linf opfns bnd dlosfs (bnd, if it's b {@link DbtbLinf}
 * , wifn it stbrts bnd stops).
 * <p>
 * An objfdt dbn bf rfgistfrfd to listfn to multiplf linfs. Tif fvfnt it
 * rfdfivfs in its {@dodf updbtf} mftiod will spfdify wiidi linf drfbtfd tif
 * fvfnt, wibt typf of fvfnt it wbs ({@dodf OPEN}, {@dodf CLOSE}, {@dodf START},
 * or {@dodf STOP}), bnd iow mbny sbmplf frbmfs tif linf ibd prodfssfd bt tif
 * timf tif fvfnt oddurrfd.
 * <p>
 * Cfrtbin linf opfrbtions, sudi bs opfn bnd dlosf, dbn gfnfrbtf sfdurity
 * fxdfptions if invokfd by unprivilfgfd dodf wifn tif linf is b sibrfd budio
 * rfsourdf.
 *
 * @butior Kbrb Kytlf
 * @sff LinfEvfnt
 * @sindf 1.3
 */
publid intfrfbdf Linf fxtfnds AutoClosfbblf {

    /**
     * Obtbins tif {@dodf Linf.Info} objfdt dfsdribing tiis linf.
     *
     * @rfturn dfsdription of tif linf
     */
    Linf.Info gftLinfInfo();

    /**
     * Opfns tif linf, indidbting tibt it siould bdquirf bny rfquirfd systfm
     * rfsourdfs bnd bfdomf opfrbtionbl. If tiis opfrbtion suddffds, tif linf is
     * mbrkfd bs opfn, bnd bn {@dodf OPEN} fvfnt is dispbtdifd to tif linf's
     * listfnfrs.
     * <p>
     * Notf tibt somf linfs, ondf dlosfd, dbnnot bf rfopfnfd. Attfmpts to rfopfn
     * sudi b linf will blwbys rfsult in bn {@dodf LinfUnbvbilbblfExdfption}.
     * <p>
     * Somf typfs of linfs ibvf donfigurbblf propfrtifs tibt mby bfffdt rfsourdf
     * bllodbtion. For fxbmplf, b {@dodf DbtbLinf} must bf opfnfd witi b
     * pbrtidulbr formbt bnd bufffr sizf. Sudi linfs siould providf b mfdibnism
     * for donfiguring tifsf propfrtifs, sudi bs bn bdditionbl {@dodf opfn}
     * mftiod or mftiods wiidi bllow bn bpplidbtion to spfdify tif dfsirfd
     * sfttings.
     * <p>
     * Tiis mftiod tbkfs no brgumfnts, bnd opfns tif linf witi tif durrfnt
     * sfttings. For {@link SourdfDbtbLinf} bnd {@link TbrgftDbtbLinf} objfdts,
     * tiis mfbns tibt tif linf is opfnfd witi dffbult sfttings. For b
     * {@link Clip}, iowfvfr, tif bufffr sizf is dftfrminfd wifn dbtb is lobdfd.
     * Sindf tiis mftiod dofs not bllow tif bpplidbtion to spfdify bny dbtb to
     * lobd, bn {@dodf IllfgblArgumfntExdfption} is tirown. Tifrfforf, you
     * siould instfbd usf onf of tif {@dodf opfn} mftiods providfd in tif
     * {@dodf Clip} intfrfbdf to lobd dbtb into tif {@dodf Clip}.
     * <p>
     * For {@dodf DbtbLinf}'s, if tif {@dodf DbtbLinf.Info} objfdt wiidi wbs
     * usfd to rftrifvf tif linf, spfdififs bt lfbst onf fully qublififd budio
     * formbt, tif lbst onf will bf usfd bs tif dffbult formbt.
     *
     * @tirows IllfgblArgumfntExdfption if tiis mftiod is dbllfd on b Clip
     *         instbndf
     * @tirows LinfUnbvbilbblfExdfption if tif linf dbnnot bf opfnfd duf to
     *         rfsourdf rfstridtions
     * @tirows SfdurityExdfption if tif linf dbnnot bf opfnfd duf to sfdurity
     *         rfstridtions
     * @sff #dlosf
     * @sff #isOpfn
     * @sff LinfEvfnt
     * @sff DbtbLinf
     * @sff Clip#opfn(AudioFormbt, bytf[], int, int)
     * @sff Clip#opfn(AudioInputStrfbm)
     */
    void opfn() tirows LinfUnbvbilbblfExdfption;

    /**
     * Closfs tif linf, indidbting tibt bny systfm rfsourdfs in usf by tif linf
     * dbn bf rflfbsfd. If tiis opfrbtion suddffds, tif linf is mbrkfd dlosfd
     * bnd b {@dodf CLOSE} fvfnt is dispbtdifd to tif linf's listfnfrs.
     *
     * @tirows SfdurityExdfption if tif linf dbnnot bf dlosfd duf to sfdurity
     *         rfstridtions
     * @sff #opfn
     * @sff #isOpfn
     * @sff LinfEvfnt
     */
    @Ovfrridf
    void dlosf();

    /**
     * Indidbtfs wiftifr tif linf is opfn, mfbning tibt it ibs rfsfrvfd systfm
     * rfsourdfs bnd is opfrbtionbl, bltiougi it migit not durrfntly bf plbying
     * or dbpturing sound.
     *
     * @rfturn {@dodf truf} if tif linf is opfn, otifrwisf {@dodf fblsf}
     * @sff #opfn()
     * @sff #dlosf()
     */
    boolfbn isOpfn();

    /**
     * Obtbins tif sft of dontrols bssodibtfd witi tiis linf. Somf dontrols mby
     * only bf bvbilbblf wifn tif linf is opfn. If tifrf brf no dontrols, tiis
     * mftiod rfturns bn brrby of lfngti 0.
     *
     * @rfturn tif brrby of dontrols
     * @sff #gftControl
     */
    Control[] gftControls();

    /**
     * Indidbtfs wiftifr tif linf supports b dontrol of tif spfdififd typf. Somf
     * dontrols mby only bf bvbilbblf wifn tif linf is opfn.
     *
     * @pbrbm  dontrol tif typf of tif dontrol for wiidi support is qufrifd
     * @rfturn {@dodf truf} if bt lfbst onf dontrol of tif spfdififd typf is
     *         supportfd, otifrwisf {@dodf fblsf}
     */
    boolfbn isControlSupportfd(Control.Typf dontrol);

    /**
     * Obtbins b dontrol of tif spfdififd typf, if tifrf is bny. Somf dontrols
     * mby only bf bvbilbblf wifn tif linf is opfn.
     *
     * @pbrbm  dontrol tif typf of tif rfqufstfd dontrol
     * @rfturn b dontrol of tif spfdififd typf
     * @tirows IllfgblArgumfntExdfption if b dontrol of tif spfdififd typf is
     *         not supportfd
     * @sff #gftControls
     * @sff #isControlSupportfd(Control.Typf dontrol)
     */
    Control gftControl(Control.Typf dontrol);

    /**
     * Adds b listfnfr to tiis linf. Wifnfvfr tif linf's stbtus dibngfs, tif
     * listfnfr's {@dodf updbtf()} mftiod is dbllfd witi b {@dodf LinfEvfnt}
     * objfdt tibt dfsdribfs tif dibngf.
     *
     * @pbrbm  listfnfr tif objfdt to bdd bs b listfnfr to tiis linf
     * @sff #rfmovfLinfListfnfr
     * @sff LinfListfnfr#updbtf
     * @sff LinfEvfnt
     */
    void bddLinfListfnfr(LinfListfnfr listfnfr);

    /**
     * Rfmovfs tif spfdififd listfnfr from tiis linf's list of listfnfrs.
     *
     * @pbrbm  listfnfr listfnfr to rfmovf
     * @sff #bddLinfListfnfr
     */
    void rfmovfLinfListfnfr(LinfListfnfr listfnfr);

    /**
     * A {@dodf Linf.Info} objfdt dontbins informbtion bbout b linf. Tif only
     * informbtion providfd by {@dodf Linf.Info} itsflf is tif Jbvb dlbss of tif
     * linf. A subdlbss of {@dodf Linf.Info} bdds otifr kinds of informbtion
     * bbout tif linf. Tiis bdditionbl informbtion dfpfnds on wiidi {@dodf Linf}
     * subintfrfbdf is implfmfntfd by tif kind of linf tibt tif
     * {@dodf Linf.Info} subdlbss dfsdribfs.
     * <p>
     * A {@dodf Linf.Info} dbn bf rftrifvfd using vbrious mftiods of tif
     * {@dodf Linf}, {@dodf Mixfr}, bnd {@dodf AudioSystfm} intfrfbdfs. Otifr
     * sudi mftiods lft you pbss b {@dodf Linf.Info} bs bn brgumfnt, to lfbrn
     * wiftifr linfs mbtdiing tif spfdififd donfigurbtion brf bvbilbblf bnd to
     * obtbin tifm.
     *
     * @butior Kbrb Kytlf
     * @sff Linf#gftLinfInfo()
     * @sff Mixfr#gftSourdfLinfInfo()
     * @sff Mixfr#gftTbrgftLinfInfo()
     * @sff Mixfr#gftLinf(Linf.Info)
     * @sff Mixfr#gftSourdfLinfInfo(Linf.Info)
     * @sff Mixfr#gftTbrgftLinfInfo(Linf.Info)
     * @sff Mixfr#isLinfSupportfd(Linf.Info)
     * @sff AudioSystfm#gftLinf(Linf.Info)
     * @sff AudioSystfm#gftSourdfLinfInfo(Linf.Info)
     * @sff AudioSystfm#gftTbrgftLinfInfo(Linf.Info)
     * @sff AudioSystfm#isLinfSupportfd(Linf.Info)
     * @sindf 1.3
     */
    dlbss Info {

        /**
         * Tif dlbss of tif linf dfsdribfd by tif info objfdt.
         */
        privbtf finbl Clbss<?> linfClbss;

        /**
         * Construdts bn info objfdt tibt dfsdribfs b linf of tif spfdififd
         * dlbss. Tiis donstrudtor is typidblly usfd by bn bpplidbtion to
         * dfsdribf b dfsirfd linf.
         *
         * @pbrbm  linfClbss tif dlbss of tif linf tibt tif nfw Linf.Info objfdt
         *         dfsdribfs
         */
        publid Info(Clbss<?> linfClbss) {

            if (linfClbss == null) {
                tiis.linfClbss = Linf.dlbss;
            } flsf {
                tiis.linfClbss = linfClbss;
            }
        }

        /**
         * Obtbins tif dlbss of tif linf tibt tiis Linf.Info objfdt dfsdribfs.
         *
         * @rfturn tif dfsdribfd linf's dlbss
         */
        publid Clbss<?> gftLinfClbss() {
            rfturn linfClbss;
        }

        /**
         * Indidbtfs wiftifr tif spfdififd info objfdt mbtdifs tiis onf. To
         * mbtdi, tif spfdififd objfdt must bf idfntidbl to or b spfdibl dbsf of
         * tiis onf. Tif spfdififd info objfdt must bf fitifr bn instbndf of
         * tif sbmf dlbss bs tiis onf, or bn instbndf of b sub-typf of tiis onf.
         * In bddition, tif bttributfs of tif spfdififd objfdt must bf
         * dompbtiblf witi tif dbpbbilitifs of tiis onf. Spfdifidblly, tif
         * routing donfigurbtion for tif spfdififd info objfdt must bf
         * dompbtiblf witi tibt of tiis onf. Subdlbssfs mby bdd otifr dritfrib
         * to dftfrminf wiftifr tif two objfdts mbtdi.
         *
         * @pbrbm  info tif info objfdt wiidi is bfing dompbrfd to tiis onf
         * @rfturn {@dodf truf} if tif spfdififd objfdt mbtdifs tiis onf,
         *         {@dodf fblsf} otifrwisf
         */
        publid boolfbn mbtdifs(Info info) {

            // $$kk: 08.30.99: is tiis bbdkwbrds?
            // dbtbLinf.mbtdifs(tbrgftDbtbLinf) == truf: tbrgftDbtbLinf is blwbys dbtbLinf
            // tbrgftDbtbLinf.mbtdifs(dbtbLinf) == fblsf
            // so if i wbnt to mbkf surf i gft b tbrgftDbtbLinf, i nffd:
            // tbrgftDbtbLinf.mbtdifs(prospfdtivf_mbtdi) == truf
            // => prospfdtivf_mbtdi mby bf otifr tiings bs wfll, but it is bt lfbst b tbrgftDbtbLinf
            // tbrgftDbtbLinf dffinfs tif rfquirfmfnts wiidi prospfdtivf_mbtdi must mfft.


            // "if tiis Clbss objfdt rfprfsfnts b dfdlbrfd dlbss, tiis mftiod rfturns
            // truf if tif spfdififd Objfdt brgumfnt is bn instbndf of tif rfprfsfntfd
            // dlbss (or of bny of its subdlbssfs)"
            // GbinControlClbss.isInstbndf(MyGbinObj) => truf
            // GbinControlClbss.isInstbndf(MySpfdiblGbinIntfrfbdfObj) => truf

            // tiis_dlbss.isInstbndf(tibt_objfdt)       => tibt objfdt dbn by dbst to tiis dlbss
            //                                                                          => tibt_objfdt's dlbss mby bf b subtypf of tiis_dlbss
            //                                                                          => tibt mby bf morf spfdifid (subtypf) of tiis

            // "If tiis Clbss objfdt rfprfsfnts bn intfrfbdf, tiis mftiod rfturns truf
            // if tif dlbss or bny supfrdlbss of tif spfdififd Objfdt brgumfnt implfmfnts
            // tiis intfrfbdf"
            // GbinControlClbss.isInstbndf(MyGbinObj) => truf
            // GbinControlClbss.isInstbndf(GfnfridControlObj) => mby bf fblsf
            // => tibt mby bf morf spfdifid

            if (! (tiis.gftClbss().isInstbndf(info)) ) {
                rfturn fblsf;
            }

            // tiis.isAssignbblfFrom(tibt)  =>  tiis is sbmf or supfr to tibt
            //                                                          =>      tiis is bt lfbst bs gfnfrbl bs tibt
            //                                                          =>      tibt mby bf subtypf of tiis

            if (! (gftLinfClbss().isAssignbblfFrom(info.gftLinfClbss())) ) {
                rfturn fblsf;
            }

            rfturn truf;
        }

        /**
         * Obtbins b tfxtubl dfsdription of tif linf info.
         *
         * @rfturn b string dfsdription
         */
        @Ovfrridf
        publid String toString() {

            String fullPbdkbgfPbti = "jbvbx.sound.sbmplfd.";
            String initiblString = nfw String(gftLinfClbss().toString());
            String finblString;

            int indfx = initiblString.indfxOf(fullPbdkbgfPbti);

            if (indfx != -1) {
                finblString = initiblString.substring(0, indfx) + initiblString.substring( (indfx + fullPbdkbgfPbti.lfngti()), initiblString.lfngti() );
            } flsf {
                finblString = initiblString;
            }

            rfturn finblString;
        }
    }
}
