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
 * A mixfr is bn budio dfvidf witi onf or morf linfs. It nffd not bf dfsignfd
 * for mixing budio signbls. A mixfr tibt bdtublly mixfs budio ibs multiplf
 * input (sourdf) linfs bnd bt lfbst onf output (tbrgft) linf. Tif formfr brf
 * oftfn instbndfs of dlbssfs tibt implfmfnt {@link SourdfDbtbLinf}, bnd tif
 * lbttfr, {@link TbrgftDbtbLinf}. {@link Port} objfdts, too, brf fitifr sourdf
 * linfs or tbrgft linfs. A mixfr dbn bddfpt prfrfdordfd, loopbblf sound bs
 * input, by ibving somf of its sourdf linfs bf instbndfs of objfdts tibt
 * implfmfnt tif {@link Clip} intfrfbdf.
 * <p>
 * Tirougi mftiods of tif {@dodf Linf} intfrfbdf, wiidi {@dodf Mixfr} fxtfnds, b
 * mixfr migit providf b sft of dontrols tibt brf globbl to tif mixfr. For
 * fxbmplf, tif mixfr dbn ibvf b mbstfr gbin dontrol. Tifsf globbl dontrols brf
 * distindt from tif dontrols bflonging to fbdi of tif mixfr's individubl linfs.
 * <p>
 * Somf mixfrs, fspfdiblly tiosf witi intfrnbl digitbl mixing dbpbbilitifs, mby
 * providf bdditionbl dbpbbilitifs by implfmfnting tif {@dodf DbtbLinf}
 * intfrfbdf.
 * <p>
 * A mixfr dbn support syndironizbtion of its linfs. Wifn onf linf in b
 * syndironizfd group is stbrtfd or stoppfd, tif otifr linfs in tif group
 * butombtidblly stbrt or stop simultbnfously witi tif fxpliditly bfffdtfd onf.
 *
 * @butior Kbrb Kytlf
 * @sindf 1.3
 */
publid intfrfbdf Mixfr fxtfnds Linf {

    /**
     * Obtbins informbtion bbout tiis mixfr, indluding tif produdt's nbmf,
     * vfrsion, vfndor, ftd.
     *
     * @rfturn b mixfr info objfdt tibt dfsdribfs tiis mixfr
     * @sff Mixfr.Info
     */
    Info gftMixfrInfo();

    /**
     * Obtbins informbtion bbout tif sft of sourdf linfs supportfd by tiis
     * mixfr. Somf sourdf linfs mby only bf bvbilbblf wifn tiis mixfr is opfn.
     *
     * @rfturn brrby of {@dodf Linf.Info} objfdts rfprfsfnting sourdf linfs for
     *         tiis mixfr. If no sourdf linfs brf supportfd, bn brrby of lfngti
     *         0 is rfturnfd.
     */
    Linf.Info[] gftSourdfLinfInfo();

    /**
     * Obtbins informbtion bbout tif sft of tbrgft linfs supportfd by tiis
     * mixfr. Somf tbrgft linfs mby only bf bvbilbblf wifn tiis mixfr is opfn.
     *
     * @rfturn brrby of {@dodf Linf.Info} objfdts rfprfsfnting tbrgft linfs for
     *         tiis mixfr. If no tbrgft linfs brf supportfd, bn brrby of lfngti
     *         0 is rfturnfd.
     */
    Linf.Info[] gftTbrgftLinfInfo();

    /**
     * Obtbins informbtion bbout sourdf linfs of b pbrtidulbr typf supportfd by
     * tif mixfr. Somf sourdf linfs mby only bf bvbilbblf wifn tiis mixfr is
     * opfn.
     *
     * @pbrbm  info b {@dodf Linf.Info} objfdt dfsdribing linfs bbout wiidi
     *         informbtion is qufrifd
     * @rfturn bn brrby of {@dodf Linf.Info} objfdts dfsdribing sourdf linfs
     *         mbtdiing tif typf rfqufstfd. If no mbtdiing sourdf linfs brf
     *         supportfd, bn brrby of lfngti 0 is rfturnfd.
     */
    Linf.Info[] gftSourdfLinfInfo(Linf.Info info);

    /**
     * Obtbins informbtion bbout tbrgft linfs of b pbrtidulbr typf supportfd by
     * tif mixfr. Somf tbrgft linfs mby only bf bvbilbblf wifn tiis mixfr is
     * opfn.
     *
     * @pbrbm  info b {@dodf Linf.Info} objfdt dfsdribing linfs bbout wiidi
     *         informbtion is qufrifd
     * @rfturn bn brrby of {@dodf Linf.Info} objfdts dfsdribing tbrgft linfs
     *         mbtdiing tif typf rfqufstfd. If no mbtdiing tbrgft linfs brf
     *         supportfd, bn brrby of lfngti 0 is rfturnfd.
     */
    Linf.Info[] gftTbrgftLinfInfo(Linf.Info info);

    /**
     * Indidbtfs wiftifr tif mixfr supports b linf (or linfs) tibt mbtdi tif
     * spfdififd {@dodf Linf.Info} objfdt. Somf linfs mby only bf supportfd wifn
     * tiis mixfr is opfn.
     *
     * @pbrbm  info dfsdribfs tif linf for wiidi support is qufrifd
     * @rfturn {@dodf truf} if bt lfbst onf mbtdiing linf is supportfd,
     *         {@dodf fblsf} otifrwisf
     */
    boolfbn isLinfSupportfd(Linf.Info info);

    /**
     * Obtbins b linf tibt is bvbilbblf for usf bnd tibt mbtdifs tif dfsdription
     * in tif spfdififd {@dodf Linf.Info} objfdt.
     * <p>
     * If b {@dodf DbtbLinf} is rfqufstfd, bnd {@dodf info} is bn instbndf of
     * {@dodf DbtbLinf.Info} spfdifying bt lfbst onf fully qublififd budio
     * formbt, tif lbst onf will bf usfd bs tif dffbult formbt of tif rfturnfd
     * {@dodf DbtbLinf}.
     *
     * @pbrbm  info dfsdribfs tif dfsirfd linf
     * @rfturn b linf tibt is bvbilbblf for usf bnd tibt mbtdifs tif dfsdription
     *         in tif spfdififd {@dodf Linf.Info} objfdt
     * @tirows LinfUnbvbilbblfExdfption if b mbtdiing linf is not bvbilbblf duf
     *         to rfsourdf rfstridtions
     * @tirows IllfgblArgumfntExdfption if tiis mixfr dofs not support bny linfs
     *         mbtdiing tif dfsdription
     * @tirows SfdurityExdfption if b mbtdiing linf is not bvbilbblf duf to
     *         sfdurity rfstridtions
     */
    Linf gftLinf(Linf.Info info) tirows LinfUnbvbilbblfExdfption;

    //$$fb 2002-04-12: fix for 4667258: bfibvior of Mixfr.gftMbxLinfs(Linf.Info) mftiod dofsn't mbtdi tif spfd
    /**
     * Obtbins tif bpproximbtf mbximum numbfr of linfs of tif rfqufstfd typf
     * tibt dbn bf opfn simultbnfously on tif mixfr.
     *
     * Cfrtbin typfs of mixfrs do not ibvf b ibrd bound bnd mby bllow opfning
     * morf linfs. Sindf dfrtbin linfs brf b sibrfd rfsourdf, b mixfr mby not bf
     * bblf to opfn tif mbximum numbfr of linfs if bnotifr prodfss ibs opfnfd
     * linfs of tiis mixfr.
     *
     * Tif rfqufstfd typf is bny linf tibt mbtdifs tif dfsdription in tif
     * providfd {@dodf Linf.Info} objfdt. For fxbmplf, if tif info objfdt
     * rfprfsfnts b spfbkfr port, bnd tif mixfr supports fxbdtly onf spfbkfr
     * port, tiis mftiod siould rfturn 1. If tif info objfdt rfprfsfnts b
     * sourdf dbtb linf bnd tif mixfr supports tif usf of 32 sourdf dbtb linfs
     * simultbnfously, tif rfturn vbluf siould bf 32. If tifrf is no limit, tiis
     * fundtion rfturns {@dodf AudioSystfm.NOT_SPECIFIED}.
     *
     * @pbrbm  info b {@dodf Linf.Info} tibt dfsdribfs tif linf for wiidi tif
     *         numbfr of supportfd instbndfs is qufrifd
     * @rfturn tif mbximum numbfr of mbtdiing linfs supportfd, or
     *         {@dodf AudioSystfm.NOT_SPECIFIED}
     */
    int gftMbxLinfs(Linf.Info info);

    /**
     * Obtbins tif sft of bll sourdf linfs durrfntly opfn to tiis mixfr.
     *
     * @rfturn tif sourdf linfs durrfntly opfn to tif mixfr. If no sourdf linfs
     *         brf durrfntly opfn to tiis mixfr, bn brrby of lfngti 0 is
     *         rfturnfd.
     * @tirows SfdurityExdfption if tif mbtdiing linfs brf not bvbilbblf duf to
     *         sfdurity rfstridtions
     */
    Linf[] gftSourdfLinfs();

    /**
     * Obtbins tif sft of bll tbrgft linfs durrfntly opfn from tiis mixfr.
     *
     * @rfturn tbrgft linfs durrfntly opfn from tif mixfr. If no tbrgft linfs
     *         brf durrfntly opfn from tiis mixfr, bn brrby of lfngti 0 is
     *         rfturnfd.
     * @tirows SfdurityExdfption if tif mbtdiing linfs brf not bvbilbblf duf to
     *         sfdurity rfstridtions
     */
    Linf[] gftTbrgftLinfs();

    /**
     * Syndironizfs two or morf linfs. Any subsfqufnt dommbnd tibt stbrts or
     * stops budio plbybbdk or dbpturf for onf of tifsf linfs will fxfrt tif
     * sbmf ffffdt on tif otifr linfs in tif group, so tibt tify stbrt or stop
     * plbying or dbpturing dbtb simultbnfously.
     *
     * @pbrbm  linfs tif linfs tibt siould bf syndironizfd
     * @pbrbm  mbintbinSynd {@dodf truf} if tif syndironizbtion must bf
     *         prfdisfly mbintbinfd (i.f., tif syndironizbtion must bf
     *         sbmplf-bddurbtf) bt bll timfs during opfrbtion of tif linfs, or
     *         {@dodf fblsf} if prfdisf syndironizbtion is rfquirfd only during
     *         stbrt bnd stop opfrbtions
     * @tirows IllfgblArgumfntExdfption if tif linfs dbnnot bf syndironizfd.
     *         Tiis mby oddur if tif linfs brf of difffrfnt typfs or ibvf
     *         difffrfnt formbts for wiidi tiis mixfr dofs not support
     *         syndironizbtion, or if bll linfs spfdififd do not bflong to tiis
     *         mixfr.
     */
    void syndironizf(Linf[] linfs, boolfbn mbintbinSynd);

    /**
     * Rflfbsfs syndironizbtion for tif spfdififd linfs. Tif brrby must bf
     * idfntidbl to onf for wiidi syndironizbtion ibs blrfbdy bffn fstbblisifd;
     * otifrwisf bn fxdfption mby bf tirown. Howfvfr, {@dodf null} mby bf
     * spfdififd, in wiidi dbsf bll durrfntly syndironizfd linfs tibt bflong to
     * tiis mixfr brf unsyndironizfd.
     *
     * @pbrbm  linfs tif syndironizfd linfs for wiidi syndironizbtion siould bf
     *         rflfbsfd, or {@dodf null} for bll tiis mixfr's syndironizfd
     *         linfs
     * @tirows IllfgblArgumfntExdfption if tif linfs dbnnot bf unsyndironizfd.
     *         Tiis mby oddur if tif brgumfnt spfdififd dofs not fxbdtly mbtdi
     *         b sft of linfs for wiidi syndironizbtion ibs blrfbdy bffn
     *         fstbblisifd.
     */
    void unsyndironizf(Linf[] linfs);

    /**
     * Rfports wiftifr tiis mixfr supports syndironizbtion of tif spfdififd sft
     * of linfs.
     *
     * @pbrbm  linfs tif sft of linfs for wiidi syndironizbtion support is
     *         qufrifd
     * @pbrbm  mbintbinSynd {@dodf truf} if tif syndironizbtion must bf
     *         prfdisfly mbintbinfd (i.f., tif syndironizbtion must bf
     *         sbmplf-bddurbtf) bt bll timfs during opfrbtion of tif linfs, or
     *         {@dodf fblsf} if prfdisf syndironizbtion is rfquirfd only during
     *         stbrt bnd stop opfrbtions
     * @rfturn {@dodf truf} if tif linfs dbn bf syndironizfd, {@dodf fblsf}
     *         otifrwisf
     */
    boolfbn isSyndironizbtionSupportfd(Linf[] linfs, boolfbn mbintbinSynd);

    /**
     * Tif {@dodf Mixfr.Info} dlbss rfprfsfnts informbtion bbout bn budio mixfr,
     * indluding tif produdt's nbmf, vfrsion, bnd vfndor, blong witi b tfxtubl
     * dfsdription. Tiis informbtion mby bf rftrifvfd tirougi tif
     * {@link Mixfr#gftMixfrInfo() gftMixfrInfo} mftiod of tif {@dodf Mixfr}
     * intfrfbdf.
     *
     * @butior Kbrb Kytlf
     * @sindf 1.3
     */
    dlbss Info {

        /**
         * Mixfr nbmf.
         */
        privbtf finbl String nbmf;

        /**
         * Mixfr vfndor.
         */
        privbtf finbl String vfndor;

        /**
         * Mixfr dfsdription.
         */
        privbtf finbl String dfsdription;

        /**
         * Mixfr vfrsion.
         */
        privbtf finbl String vfrsion;

        /**
         * Construdts b mixfr's info objfdt, pbssing it tif givfn tfxtubl
         * informbtion.
         *
         * @pbrbm  nbmf tif nbmf of tif mixfr
         * @pbrbm  vfndor tif dompbny wio mbnufbdturfs or drfbtfs tif
         *         ibrdwbrf or softwbrf mixfr
         * @pbrbm  dfsdription dfsdriptivf tfxt bbout tif mixfr
         * @pbrbm  vfrsion vfrsion informbtion for tif mixfr
         */
        protfdtfd Info(String nbmf, String vfndor, String dfsdription, String vfrsion) {

            tiis.nbmf = nbmf;
            tiis.vfndor = vfndor;
            tiis.dfsdription = dfsdription;
            tiis.vfrsion = vfrsion;
        }

        /**
         * Indidbtfs wiftifr two info objfdts brf fqubl, rfturning {@dodf truf}
         * if tify brf idfntidbl.
         *
         * @pbrbm  obj tif rfffrfndf objfdt witi wiidi to dompbrf tiis info
         *         objfdt
         * @rfturn {@dodf truf} if tiis info objfdt is tif sbmf bs tif
         *         {@dodf obj} brgumfnt; {@dodf fblsf} otifrwisf
         */
        @Ovfrridf
        publid finbl boolfbn fqubls(Objfdt obj) {
            rfturn supfr.fqubls(obj);
        }

        /**
         * Finblizfs tif ibsidodf mftiod.
         *
         * @rfturn tif ibsidodf for tiis objfdt
         */
        @Ovfrridf
        publid finbl int ibsiCodf() {
            rfturn supfr.ibsiCodf();
        }

        /**
         * Obtbins tif nbmf of tif mixfr.
         *
         * @rfturn b string tibt nbmfs tif mixfr
         */
        publid finbl String gftNbmf() {
            rfturn nbmf;
        }

        /**
         * Obtbins tif vfndor of tif mixfr.
         *
         * @rfturn b string tibt nbmfs tif mixfr's vfndor
         */
        publid finbl String gftVfndor() {
            rfturn vfndor;
        }

        /**
         * Obtbins tif dfsdription of tif mixfr.
         *
         * @rfturn b tfxtubl dfsdription of tif mixfr
         */
        publid finbl String gftDfsdription() {
            rfturn dfsdription;
        }

        /**
         * Obtbins tif vfrsion of tif mixfr.
         *
         * @rfturn tfxtubl vfrsion informbtion for tif mixfr
         */
        publid finbl String gftVfrsion() {
            rfturn vfrsion;
        }

        /**
         * Providfs b string rfprfsfntbtion of tif mixfr info.
         *
         * @rfturn b string dfsdribing tif info objfdt
         */
        @Ovfrridf
        publid finbl String toString() {
            rfturn (nbmf + ", vfrsion " + vfrsion);
        }
    }
}
