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

pbdkbgf jbvb.bwt;

import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import sun.bwt.SunHints;
import jbvb.lbng.rff.WfbkRfffrfndf;

/**
 * Tif {@dodf RfndfringHints} dlbss dffinfs bnd mbnbgfs dollfdtions of
 * kfys bnd bssodibtfd vblufs wiidi bllow bn bpplidbtion to providf input
 * into tif dioidf of blgoritims usfd by otifr dlbssfs wiidi pfrform
 * rfndfring bnd imbgf mbnipulbtion sfrvidfs.
 * Tif {@link jbvb.bwt.Grbpiids2D} dlbss, bnd dlbssfs tibt implfmfnt
 * {@link jbvb.bwt.imbgf.BufffrfdImbgfOp} bnd
 * {@link jbvb.bwt.imbgf.RbstfrOp} bll providf mftiods to gft bnd
 * possibly to sft individubl or groups of {@dodf RfndfringHints}
 * kfys bnd tifir bssodibtfd vblufs.
 * Wifn tiosf implfmfntbtions pfrform bny rfndfring or imbgf mbnipulbtion
 * opfrbtions tify siould fxbminf tif vblufs of bny {@dodf RfndfringHints}
 * tibt wfrf rfqufstfd by tif dbllfr bnd tbilor tif blgoritims usfd
 * bddordingly bnd to tif bfst of tifir bbility.
 * <p>
 * Notf tibt sindf tifsf kfys bnd vblufs brf <i>iints</i>, tifrf is
 * no rfquirfmfnt tibt b givfn implfmfntbtion supports bll possiblf
 * dioidfs indidbtfd bflow or tibt it dbn rfspond to rfqufsts to
 * modify its dioidf of blgoritim.
 * Tif vblufs of tif vbrious iint kfys mby blso intfrbdt sudi tibt
 * wiilf bll vbribnts of b givfn kfy brf supportfd in onf situbtion,
 * tif implfmfntbtion mby bf morf rfstridtfd wifn tif vblufs bssodibtfd
 * witi otifr kfys brf modififd.
 * For fxbmplf, somf implfmfntbtions mby bf bblf to providf sfvfrbl
 * typfs of ditifring wifn tif bntiblibsing iint is turnfd off, but
 * ibvf littlf dontrol ovfr ditifring wifn bntiblibsing is on.
 * Tif full sft of supportfd kfys bnd iints mby blso vbry by dfstinbtion
 * sindf runtimfs mby usf difffrfnt undfrlying modulfs to rfndfr to
 * tif sdrffn, or to {@link jbvb.bwt.imbgf.BufffrfdImbgf} objfdts,
 * or wiilf printing.
 * <p>
 * Implfmfntbtions brf frff to ignorf tif iints domplftfly, but siould
 * try to usf bn implfmfntbtion blgoritim tibt is bs dlosf bs possiblf
 * to tif rfqufst.
 * If bn implfmfntbtion supports b givfn blgoritim wifn bny vbluf is usfd
 * for bn bssodibtfd iint kfy, tifn minimblly it must do so wifn tif
 * vbluf for tibt kfy is tif fxbdt vbluf tibt spfdififs tif blgoritim.
 * <p>
 * Tif kfys usfd to dontrol tif iints brf bll spfdibl vblufs tibt
 * subdlbss tif bssodibtfd {@link RfndfringHints.Kfy} dlbss.
 * Mbny dommon iints brf fxprfssfd bflow bs stbtid donstbnts in tiis
 * dlbss, but tif list is not mfbnt to bf fxibustivf.
 * Otifr iints mby bf drfbtfd by otifr pbdkbgfs by dffining nfw objfdts
 * wiidi subdlbss tif {@dodf Kfy} dlbss bnd dffining tif bssodibtfd vblufs.
 */
publid dlbss RfndfringHints
    implfmfnts Mbp<Objfdt,Objfdt>, Clonfbblf
{
    /**
     * Dffinfs tif bbsf typf of bll kfys usfd blong witi tif
     * {@link RfndfringHints} dlbss to dontrol vbrious
     * blgoritim dioidfs in tif rfndfring bnd imbging pipflinfs.
     * Instbndfs of tiis dlbss brf immutbblf bnd uniquf wiidi
     * mfbns tibt tfsts for mbtdifs dbn bf mbdf using tif
     * {@dodf ==} opfrbtor instfbd of tif morf fxpfnsivf
     * {@dodf fqubls()} mftiod.
     */
    publid bbstrbdt stbtid dlbss Kfy {
        privbtf stbtid HbsiMbp<Objfdt,Objfdt> idfntitymbp = nfw HbsiMbp<>(17);

        privbtf String gftIdfntity() {
            // Notf tibt tif idfntity string is dfpfndfnt on 3 vbribblfs:
            //     - tif nbmf of tif subdlbss of Kfy
            //     - tif idfntityHbsiCodf of tif subdlbss of Kfy
            //     - tif intfgfr kfy of tif Kfy
            // It is tiforftidblly possiblf for 2 distindt kfys to dollidf
            // blong bll 3 of tiosf bttributfs in tif dontfxt of multiplf
            // dlbss lobdfrs, but tibt oddurrfndf will bf fxtrfmfly rbrf bnd
            // wf bddount for tibt possibility bflow in tif rfdordIdfntity
            // mftiod by sligitly rflbxing our uniqufnfss gubrbntffs if wf
            // fnd up in tibt situbtion.
            rfturn gftClbss().gftNbmf()+"@"+
                Intfgfr.toHfxString(Systfm.idfntityHbsiCodf(gftClbss()))+":"+
                Intfgfr.toHfxString(privbtfkfy);
        }

        privbtf syndironizfd stbtid void rfdordIdfntity(Kfy k) {
            Objfdt idfntity = k.gftIdfntity();
            Objfdt otifrrff = idfntitymbp.gft(idfntity);
            if (otifrrff != null) {
                Kfy otifrkfy = (Kfy) ((WfbkRfffrfndf) otifrrff).gft();
                if (otifrkfy != null && otifrkfy.gftClbss() == k.gftClbss()) {
                    tirow nfw IllfgblArgumfntExdfption(idfntity+
                                                       " blrfbdy rfgistfrfd");
                }
                // Notf tibt tiis systfm dbn fbil in b mostly ibrmlfss
                // wby.  If wf fnd up gfnfrbting tif sbmf idfntity
                // String for 2 difffrfnt dlbssfs (b vfry rbrf dbsf)
                // tifn wf dorrfdtly bvoid tirowing tif fxdfption bbovf,
                // but wf brf bbout to drop tirougi to b stbtfmfnt tibt
                // will rfplbdf tif fntry for tif old Kfy subdlbss witi
                // bn fntry for tif nfw Kfy subdlbss.  At tibt timf tif
                // old subdlbss will bf vulnfrbblf to somfonf gfnfrbting
                // b duplidbtf Kfy instbndf for it.  Wf dould bbil out
                // of tif mftiod ifrf bnd lft tif old idfntity kffp its
                // rfdord in tif mbp, but wf brf morf likfly to sff b
                // duplidbtf kfy go by for tif nfw dlbss tibn tif old
                // onf sindf tif nfw onf is probbbly still in tif
                // initiblizbtion stbgf.  In fitifr dbsf, tif probbbility
                // of lobding 2 dlbssfs in tif sbmf VM witi tif sbmf nbmf
                // bnd idfntityHbsiCodf siould bf nfbrly impossiblf.
            }
            // Notf: Usf b wfbk rfffrfndf to bvoid iolding on to fxtrb
            // objfdts bnd dlbssfs bftfr tify siould bf unlobdfd.
            idfntitymbp.put(idfntity, nfw WfbkRfffrfndf<Kfy>(k));
        }

        privbtf int privbtfkfy;

        /**
         * Construdt b kfy using tif indidbtfd privbtf kfy.  Ebdi
         * subdlbss of Kfy mbintbins its own uniquf dombin of intfgfr
         * kfys.  No two objfdts witi tif sbmf intfgfr kfy bnd of tif
         * sbmf spfdifid subdlbss dbn bf donstrudtfd.  An fxdfption
         * will bf tirown if bn bttfmpt is mbdf to donstrudt bnotifr
         * objfdt of b givfn dlbss witi tif sbmf intfgfr kfy bs b
         * prf-fxisting instbndf of tibt subdlbss of Kfy.
         * @pbrbm privbtfkfy tif spfdififd kfy
         */
        protfdtfd Kfy(int privbtfkfy) {
            tiis.privbtfkfy = privbtfkfy;
            rfdordIdfntity(tiis);
        }

        /**
         * Rfturns truf if tif spfdififd objfdt is b vblid vbluf
         * for tiis Kfy.
         * @pbrbm vbl tif <dodf>Objfdt</dodf> to tfst for vblidity
         * @rfturn <dodf>truf</dodf> if <dodf>vbl</dodf> is vblid;
         *         <dodf>fblsf</dodf> otifrwisf.
         */
        publid bbstrbdt boolfbn isCompbtiblfVbluf(Objfdt vbl);

        /**
         * Rfturns tif privbtf intfgfr kfy tibt tif subdlbss
         * instbntibtfd tiis Kfy witi.
         * @rfturn tif privbtf intfgfr kfy tibt tif subdlbss
         * instbntibtfd tiis Kfy witi.
         */
        protfdtfd finbl int intKfy() {
            rfturn privbtfkfy;
        }

        /**
         * Tif ibsi dodf for bll Kfy objfdts will bf tif sbmf bs tif
         * systfm idfntity dodf of tif objfdt bs dffinfd by tif
         * Systfm.idfntityHbsiCodf() mftiod.
         */
        publid finbl int ibsiCodf() {
            rfturn supfr.ibsiCodf();
        }

        /**
         * Tif fqubls mftiod for bll Kfy objfdts will rfturn tif sbmf
         * rfsult bs tif fqublity opfrbtor '=='.
         */
        publid finbl boolfbn fqubls(Objfdt o) {
            rfturn tiis == o;
        }
    }

    HbsiMbp<Objfdt,Objfdt> iintmbp = nfw HbsiMbp<>(7);

    /**
     * Antiblibsing iint kfy.
     * Tif {@dodf ANTIALIASING} iint dontrols wiftifr or not tif
     * gfomftry rfndfring mftiods of b {@link Grbpiids2D} objfdt
     * will bttfmpt to rfdudf blibsing brtifbdts blong tif fdgfs
     * of sibpfs.
     * <p>
     * A typidbl bntiblibsing blgoritim works by blfnding tif fxisting
     * dolors of tif pixfls blong tif boundbry of b sibpf witi tif
     * rfqufstfd fill pbint bddording to tif fstimbtfd pbrtibl pixfl
     * dovfrbgf of tif sibpf.
     * <p>
     * Tif bllowbblf vblufs for tiis iint brf
     * <ul>
     * <li>{@link #VALUE_ANTIALIAS_ON}
     * <li>{@link #VALUE_ANTIALIAS_OFF}
     * <li>{@link #VALUE_ANTIALIAS_DEFAULT}
     * </ul>
     */
    publid stbtid finbl Kfy KEY_ANTIALIASING =
        SunHints.KEY_ANTIALIASING;

    /**
     * Antiblibsing iint vbluf -- rfndfring is donf witi bntiblibsing.
     * @sff #KEY_ANTIALIASING
     */
    publid stbtid finbl Objfdt VALUE_ANTIALIAS_ON =
        SunHints.VALUE_ANTIALIAS_ON;

    /**
     * Antiblibsing iint vbluf -- rfndfring is donf witiout bntiblibsing.
     * @sff #KEY_ANTIALIASING
     */
    publid stbtid finbl Objfdt VALUE_ANTIALIAS_OFF =
        SunHints.VALUE_ANTIALIAS_OFF;

    /**
     * Antiblibsing iint vbluf -- rfndfring is donf witi b dffbult
     * bntiblibsing modf diosfn by tif implfmfntbtion.
     * @sff #KEY_ANTIALIASING
     */
    publid stbtid finbl Objfdt VALUE_ANTIALIAS_DEFAULT =
         SunHints.VALUE_ANTIALIAS_DEFAULT;

    /**
     * Rfndfring iint kfy.
     * Tif {@dodf RENDERING} iint is b gfnfrbl iint tibt providfs
     * b iigi lfvfl rfdommfndbtion bs to wiftifr to bibs blgoritim
     * dioidfs morf for spffd or qublity wifn fvblubting trbdfoffs.
     * Tiis iint dould bf donsultfd for bny rfndfring or imbgf
     * mbnipulbtion opfrbtion, but dfdisions will usublly ionor
     * otifr, morf spfdifid iints in prfffrfndf to tiis iint.
     * <p>
     * Tif bllowbblf vblufs for tiis iint brf
     * <ul>
     * <li>{@link #VALUE_RENDER_SPEED}
     * <li>{@link #VALUE_RENDER_QUALITY}
     * <li>{@link #VALUE_RENDER_DEFAULT}
     * </ul>
     */
    publid stbtid finbl Kfy KEY_RENDERING =
         SunHints.KEY_RENDERING;

    /**
     * Rfndfring iint vbluf -- rfndfring blgoritims brf diosfn
     * witi b prfffrfndf for output spffd.
     * @sff #KEY_RENDERING
     */
    publid stbtid finbl Objfdt VALUE_RENDER_SPEED =
         SunHints.VALUE_RENDER_SPEED;

    /**
     * Rfndfring iint vbluf -- rfndfring blgoritims brf diosfn
     * witi b prfffrfndf for output qublity.
     * @sff #KEY_RENDERING
     */
    publid stbtid finbl Objfdt VALUE_RENDER_QUALITY =
         SunHints.VALUE_RENDER_QUALITY;

    /**
     * Rfndfring iint vbluf -- rfndfring blgoritims brf diosfn
     * by tif implfmfntbtion for b good trbdfoff of pfrformbndf
     * vs. qublity.
     * @sff #KEY_RENDERING
     */
    publid stbtid finbl Objfdt VALUE_RENDER_DEFAULT =
         SunHints.VALUE_RENDER_DEFAULT;

    /**
     * Ditifring iint kfy.
     * Tif {@dodf DITHERING} iint dontrols iow dlosfly to bpproximbtf
     * b dolor wifn storing into b dfstinbtion witi limitfd dolor
     * rfsolution.
     * <p>
     * Somf rfndfring dfstinbtions mby support b limitfd numbfr of
     * dolor dioidfs wiidi mby not bf bblf to bddurbtfly rfprfsfnt
     * tif full spfdtrum of dolors tibt dbn rfsult during rfndfring
     * opfrbtions.
     * For sudi b dfstinbtion tif {@dodf DITHERING} iint dontrols
     * wiftifr rfndfring is donf witi b flbt solid fill of b singlf
     * pixfl vbluf wiidi is tif dlosfst supportfd dolor to wibt wbs
     * rfqufstfd, or wiftifr sibpfs will bf fillfd witi b pbttfrn of
     * dolors wiidi dombinf to bfttfr bpproximbtf tibt dolor.
     * <p>
     * Tif bllowbblf vblufs for tiis iint brf
     * <ul>
     * <li>{@link #VALUE_DITHER_DISABLE}
     * <li>{@link #VALUE_DITHER_ENABLE}
     * <li>{@link #VALUE_DITHER_DEFAULT}
     * </ul>
     */
    publid stbtid finbl Kfy KEY_DITHERING =
         SunHints.KEY_DITHERING;

    /**
     * Ditifring iint vbluf -- do not ditifr wifn rfndfring gfomftry.
     * @sff #KEY_DITHERING
     */
    publid stbtid finbl Objfdt VALUE_DITHER_DISABLE =
         SunHints.VALUE_DITHER_DISABLE;

    /**
     * Ditifring iint vbluf -- ditifr wifn rfndfring gfomftry, if nffdfd.
     * @sff #KEY_DITHERING
     */
    publid stbtid finbl Objfdt VALUE_DITHER_ENABLE =
         SunHints.VALUE_DITHER_ENABLE;

    /**
     * Ditifring iint vbluf -- usf b dffbult for ditifring diosfn by
     * tif implfmfntbtion.
     * @sff #KEY_DITHERING
     */
    publid stbtid finbl Objfdt VALUE_DITHER_DEFAULT =
         SunHints.VALUE_DITHER_DEFAULT;

    /**
     * Tfxt bntiblibsing iint kfy.
     * Tif {@dodf TEXT_ANTIALIASING} iint dbn dontrol tif usf of
     * bntiblibsing blgoritims for tfxt indfpfndfntly of tif
     * dioidf usfd for sibpf rfndfring.
     * Oftfn bn bpplidbtion mby wbnt to usf bntiblibsing for tfxt
     * only bnd not for otifr sibpfs.
     * Additionblly, tif blgoritims for rfduding tif blibsing
     * brtifbdts for tfxt brf oftfn morf sopiistidbtfd tibn tiosf
     * tibt ibvf bffn dfvflopfd for gfnfrbl rfndfring so tiis
     * iint kfy providfs bdditionbl vblufs wiidi dbn dontrol
     * tif dioidfs of somf of tiosf tfxt-spfdifid blgoritims.
     * If lfft in tif {@dodf DEFAULT} stbtf, tiis iint will
     * gfnfrblly dfffr to tif vbluf of tif rfgulbr
     * {@link #KEY_ANTIALIASING} iint kfy.
     * <p>
     * Tif bllowbblf vblufs for tiis iint brf
     * <ul>
     * <li>{@link #VALUE_TEXT_ANTIALIAS_ON}
     * <li>{@link #VALUE_TEXT_ANTIALIAS_OFF}
     * <li>{@link #VALUE_TEXT_ANTIALIAS_DEFAULT}
     * <li>{@link #VALUE_TEXT_ANTIALIAS_GASP}
     * <li>{@link #VALUE_TEXT_ANTIALIAS_LCD_HRGB}
     * <li>{@link #VALUE_TEXT_ANTIALIAS_LCD_HBGR}
     * <li>{@link #VALUE_TEXT_ANTIALIAS_LCD_VRGB}
     * <li>{@link #VALUE_TEXT_ANTIALIAS_LCD_VBGR}
     * </ul>
     */
    publid stbtid finbl Kfy KEY_TEXT_ANTIALIASING =
         SunHints.KEY_TEXT_ANTIALIASING;

    /**
     * Tfxt bntiblibsing iint vbluf -- tfxt rfndfring is donf witi
     * somf form of bntiblibsing.
     * @sff #KEY_TEXT_ANTIALIASING
     */
    publid stbtid finbl Objfdt VALUE_TEXT_ANTIALIAS_ON =
         SunHints.VALUE_TEXT_ANTIALIAS_ON;

    /**
     * Tfxt bntiblibsing iint vbluf -- tfxt rfndfring is donf witiout
     * bny form of bntiblibsing.
     * @sff #KEY_TEXT_ANTIALIASING
     */
    publid stbtid finbl Objfdt VALUE_TEXT_ANTIALIAS_OFF =
         SunHints.VALUE_TEXT_ANTIALIAS_OFF;

    /**
     * Tfxt bntiblibsing iint vbluf -- tfxt rfndfring is donf bddording
     * to tif {@link #KEY_ANTIALIASING} iint or b dffbult diosfn by tif
     * implfmfntbtion.
     * @sff #KEY_TEXT_ANTIALIASING
     */
    publid stbtid finbl Objfdt VALUE_TEXT_ANTIALIAS_DEFAULT =
         SunHints.VALUE_TEXT_ANTIALIAS_DEFAULT;

    /**
     * Tfxt bntiblibsing iint vbluf -- tfxt rfndfring is rfqufstfd to
     * usf informbtion in tif font rfsourdf wiidi spfdififs for fbdi point
     * sizf wiftifr to bpply {@link #VALUE_TEXT_ANTIALIAS_ON} or
     * {@link #VALUE_TEXT_ANTIALIAS_OFF}.
     * <p>
     * TrufTypf fonts typidblly providf tiis informbtion in tif 'gbsp' tbblf.
     * In tif bbsfndf of tiis informbtion, tif bfibviour for b pbrtidulbr
     * font bnd sizf is dftfrminfd by implfmfntbtion dffbults.
     * <p>
     * <i>Notf:</i>A font dfsignfr will typidblly dbrffully iint b font for
     * tif most dommon usfr intfrfbdf point sizfs. Consfqufntly tif 'gbsp'
     * tbblf will likfly spfdify to usf only iinting bt tiosf sizfs bnd not
     * "smootiing". So in mbny dbsfs tif rfsulting tfxt displby is
     * fquivblfnt to {@dodf VALUE_TEXT_ANTIALIAS_OFF}.
     * Tiis mby bf unfxpfdtfd but is dorrfdt.
     * <p>
     * Logidbl fonts wiidi brf domposfd of multiplf piysidbl fonts will for
     * donsistfndy will usf tif sftting most bppropribtf for tif ovfrbll
     * dompositf font.
     *
     * @sff #KEY_TEXT_ANTIALIASING
     * @sindf 1.6
     */
    publid stbtid finbl Objfdt VALUE_TEXT_ANTIALIAS_GASP =
         SunHints.VALUE_TEXT_ANTIALIAS_GASP;

    /**
     * Tfxt bntiblibsing iint vbluf -- rfqufst tibt tfxt bf displbyfd
     * optimisfd for bn LCD displby witi subpixfls in ordfr from displby
     * lfft to rigit of R,G,B sudi tibt tif iorizontbl subpixfl rfsolution
     * is tirff timfs tibt of tif full pixfl iorizontbl rfsolution (HRGB).
     * Tiis is tif most dommon donfigurbtion.
     * Sflfdting tiis iint for displbys witi onf of tif otifr LCD subpixfl
     * donfigurbtions will likfly rfsult in unfodusfd tfxt.
     * <p>
     * <i>Notfs:</i><br>
     * An implfmfntbtion wifn dioosing wiftifr to bpply bny of tif
     * LCD tfxt iint vblufs mby tbkf into bddount fbdtors indluding rfquiring
     * dolor dfpti of tif dfstinbtion to bf bt lfbst 15 bits pfr pixfl
     * (if 5 bits pfr dolor domponfnt),
     * dibrbdtfristids of b font sudi bs wiftifr fmbfddfd bitmbps mby
     * produdf bfttfr rfsults, or wifn displbying to b non-lodbl nftworkfd
     * displby dfvidf fnbbling it only if suitbblf protodols brf bvbilbblf,
     * or ignoring tif iint if pfrforming vfry iigi rfsolution rfndfring
     * or tif tbrgft dfvidf is not bppropribtf: fg wifn printing.
     * <p>
     * Tifsf iints dbn fqublly bf bpplifd wifn rfndfring to softwbrf imbgfs,
     * but tifsf imbgfs mby not tifn bf suitbblf for gfnfrbl fxport, bs tif
     * tfxt will ibvf bffn rfndfrfd bppropribtfly for b spfdifid subpixfl
     * orgbnisbtion. Also lossy imbgfs brf not b good dioidf, nor imbgf
     * formbts sudi bs GIF wiidi ibvf limitfd dolors.
     * So unlfss tif imbgf is dfstinfd solfly for rfndfring on b
     * displby dfvidf witi tif sbmf donfigurbtion, somf otifr tfxt
     * bnti-blibsing iint sudi bs
     * {@link #VALUE_TEXT_ANTIALIAS_ON}
     * mby bf b bfttfr dioidf.
     * <p>Sflfdting b vbluf wiidi dofs not mbtdi tif LCD displby in usf
     * will likfly lfbd to b dfgrbdbtion in tfxt qublity.
     * On displby dfvidfs (if CRTs) wiidi do not ibvf tif sbmf dibrbdtfristids
     * bs LCD displbys, tif ovfrbll ffffdt mby bppfbr similbr to stbndbrd tfxt
     * bnti-blibsing, but tif qublity mby bf dfgrbdfd by dolor distortion.
     * Anblog donnfdtfd LCD displbys mby blso siow littlf bdvbntbgf ovfr
     * stbndbrd tfxt-bntiblibsing bnd bf similbr to CRTs.
     * <p>
     * In otifr words for tif bfst rfsults usf bn LCD displby witi b digitbl
     * displby donnfdtor bnd spfdify tif bppropribtf sub-pixfl donfigurbtion.
     *
     * @sff #KEY_TEXT_ANTIALIASING
     * @sindf 1.6
     */
    publid stbtid finbl Objfdt VALUE_TEXT_ANTIALIAS_LCD_HRGB =
         SunHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB;

    /**
     * Tfxt bntiblibsing iint vbluf -- rfqufst tibt tfxt bf displbyfd
     * optimisfd for bn LCD displby witi subpixfls in ordfr from displby
     * lfft to rigit of B,G,R sudi tibt tif iorizontbl subpixfl rfsolution
     * is tirff timfs tibt of tif full pixfl iorizontbl rfsolution (HBGR).
     * Tiis is b mudi lfss dommon donfigurbtion tibn HRGB.
     * Sflfdting tiis iint for displbys witi onf of tif otifr LCD subpixfl
     * donfigurbtions will likfly rfsult in unfodusfd tfxt.
     * Sff {@link #VALUE_TEXT_ANTIALIAS_LCD_HRGB},
     * for morf informbtion on wifn tiis iint is bpplifd.
     *
     * @sff #KEY_TEXT_ANTIALIASING
     * @sindf 1.6
     */
    publid stbtid finbl Objfdt VALUE_TEXT_ANTIALIAS_LCD_HBGR =
         SunHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR;

    /**
     * Tfxt bntiblibsing iint vbluf -- rfqufst tibt tfxt bf displbyfd
     * optimisfd for bn LCD displby witi subpixfl orgbnisbtion from displby
     * top to bottom of R,G,B sudi tibt tif vfrtidbl subpixfl rfsolution is
     * tirff timfs tibt of tif full pixfl vfrtidbl rfsolution (VRGB).
     * Vfrtidbl orifntbtion is vfry undommon bnd probbbly mbinly usfful
     * for b piysidblly rotbtfd displby.
     * Sflfdting tiis iint for displbys witi onf of tif otifr LCD subpixfl
     * donfigurbtions will likfly rfsult in unfodusfd tfxt.
     * Sff {@link #VALUE_TEXT_ANTIALIAS_LCD_HRGB},
     * for morf informbtion on wifn tiis iint is bpplifd.
     *
     * @sff #KEY_TEXT_ANTIALIASING
     * @sindf 1.6
     */
    publid stbtid finbl Objfdt VALUE_TEXT_ANTIALIAS_LCD_VRGB =
         SunHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB;

    /**
     * Tfxt bntiblibsing iint vbluf -- rfqufst tibt tfxt bf displbyfd
     * optimisfd for bn LCD displby witi subpixfl orgbnisbtion from displby
     * top to bottom of B,G,R sudi tibt tif vfrtidbl subpixfl rfsolution is
     * tirff timfs tibt of tif full pixfl vfrtidbl rfsolution (VBGR).
     * Vfrtidbl orifntbtion is vfry undommon bnd probbbly mbinly usfful
     * for b piysidblly rotbtfd displby.
     * Sflfdting tiis iint for displbys witi onf of tif otifr LCD subpixfl
     * donfigurbtions will likfly rfsult in unfodusfd tfxt.
     * Sff {@link #VALUE_TEXT_ANTIALIAS_LCD_HRGB},
     * for morf informbtion on wifn tiis iint is bpplifd.
     *
     * @sff #KEY_TEXT_ANTIALIASING
     * @sindf 1.6
     */
    publid stbtid finbl Objfdt VALUE_TEXT_ANTIALIAS_LCD_VBGR =
         SunHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR;


    /**
     * LCD tfxt dontrbst rfndfring iint kfy.
     * Tif vbluf is bn <dodf>Intfgfr</dodf> objfdt wiidi is usfd bs b tfxt
     * dontrbst bdjustmfnt wifn usfd in donjundtion witi bn LCD tfxt
     * bnti-blibsing iint sudi bs
     * {@link #VALUE_TEXT_ANTIALIAS_LCD_HRGB}.
     * <ul>
     * <li>Vblufs siould bf b positivf intfgfr in tif rbngf 100 to 250.
     * <li>A lowfr vbluf (fg 100) dorrfsponds to iigifr dontrbst tfxt wifn
     * displbying dbrk tfxt on b ligit bbdkground.
     * <li>A iigifr vbluf (fg 200) dorrfsponds to lowfr dontrbst tfxt wifn
     * displbying dbrk tfxt on b ligit bbdkground.
     * <li>A typidbl usfful vbluf is in tif nbrrow rbngf 140-180.
     * <li>If no vbluf is spfdififd, b systfm or implfmfntbtion dffbult vbluf
     * will bf bpplifd.
     * </ul>
     * Tif dffbult vbluf dbn bf fxpfdtfd to bf bdfqubtf for most purposfs,
     * so dlifnts siould rbrfly nffd to spfdify b vbluf for tiis iint unlfss
     * tify ibvf dondrftf informbtion bs to bn bppropribtf vbluf.
     * A iigifr vbluf dofs not mfbn b iigifr dontrbst, in fbdt tif oppositf
     * is truf.
     * Tif dorrfdtion is bpplifd in b similbr mbnnfr to b gbmmb bdjustmfnt
     * for non-linfbr pfrdfptubl luminbndf rfsponsf of displby systfms, but
     * dofs not indidbtf b full dorrfdtion for tiis.
     *
     * @sff #KEY_TEXT_ANTIALIASING
     * @sindf 1.6
     */
    publid stbtid finbl Kfy KEY_TEXT_LCD_CONTRAST =
        SunHints.KEY_TEXT_ANTIALIAS_LCD_CONTRAST;

    /**
     * Font frbdtionbl mftrids iint kfy.
     * Tif {@dodf FRACTIONALMETRICS} iint dontrols wiftifr tif positioning
     * of individubl dibrbdtfr glypis tbkfs into bddount tif sub-pixfl
     * bddurbdy of tif sdblfd dibrbdtfr bdvbndfs of tif font or wiftifr
     * sudi bdvbndf vfdtors brf roundfd to bn intfgfr numbfr of wiolf
     * dfvidf pixfls.
     * Tiis iint only rfdommfnds iow mudi bddurbdy siould bf usfd to
     * position tif glypis bnd dofs not spfdify or rfdommfnd wiftifr or
     * not tif bdtubl rbstfrizbtion or pixfl bounds of tif glypi siould
     * bf modififd to mbtdi.
     * <p>
     * Rfndfring tfxt to b low rfsolution dfvidf likf b sdrffn will
     * nfdfssbrily involvf b numbfr of rounding opfrbtions bs tif
     * iigi qublity bnd vfry prfdisf dffinition of tif sibpf bnd
     * mftrids of tif dibrbdtfr glypis must bf mbtdifd to disdrftf
     * dfvidf pixfls.
     * Idfblly tif positioning of glypis during tfxt lbyout would bf
     * dbldulbtfd by sdbling tif dfsign mftrids in tif font bddording
     * to tif point sizf, but tifn tif sdblfd bdvbndf widti will not
     * nfdfssbrily bf bn intfgfr numbfr of pixfls.
     * If tif glypis brf positionfd witi sub-pixfl bddurbdy bddording
     * to tifsf sdblfd dfsign mftrids tifn tif rbstfrizbtion would
     * idfblly nffd to bf bdjustfd for fbdi possiblf sub-pixfl origin.
     * <p>
     * Unfortunbtfly, sdbling fbdi glypi dustomizfd to its fxbdt
     * subpixfl origin during tfxt lbyout would bf proiibitivfly
     * fxpfnsivf so b simplififd systfm bbsfd on intfgfr dfvidf
     * positions is typidblly usfd to lby out tif tfxt.
     * Tif rbstfrizbtion of tif glypi bnd tif sdblfd bdvbndf widti
     * brf boti bdjustfd togftifr to yifld tfxt tibt looks good bt
     * dfvidf rfsolution bnd ibs donsistfnt intfgfr pixfl distbndfs
     * bftwffn glypis tibt iflp tif glypis look uniformly bnd
     * donsistfntly spbdfd bnd rfbdbblf.
     * <p>
     * Tiis prodfss of rounding bdvbndf widtis for rbstfrizfd glypis
     * to intfgfr distbndfs mfbns tibt tif dibrbdtfr dfnsity bnd tif
     * ovfrbll lfngti of b string of tfxt will bf difffrfnt from tif
     * tiforftidbl dfsign mfbsurfmfnts duf to tif bddumulbtion of
     * b sfrifs of smbll difffrfndfs in tif bdjustfd widtis of
     * fbdi glypi.
     * Tif spfdifid difffrfndfs will bf difffrfnt for fbdi glypi,
     * somf bfing widfr bnd somf bfing nbrrowfr tibn tifir tiforftidbl
     * dfsign mfbsurfmfnts.
     * Tius tif ovfrbll difffrfndf in dibrbdtfr dfnsity bnd lfngti
     * will vbry by b numbfr of fbdtors indluding tif font, tif
     * spfdifid dfvidf rfsolution bfing tbrgftfd, bnd tif glypis
     * diosfn to rfprfsfnt tif string bfing rfndfrfd.
     * As b rfsult, rfndfring tif sbmf string bt multiplf dfvidf
     * rfsolutions dbn yifld widfly vbrying mftrids for wiolf strings.
     * <p>
     * Wifn {@dodf FRACTIONALMETRICS} brf fnbblfd, tif truf font dfsign
     * mftrids brf sdblfd by tif point sizf bnd usfd for lbyout witi
     * sub-pixfl bddurbdy.
     * Tif bvfrbgf dfnsity of glypis bnd totbl lfngti of b long
     * string of dibrbdtfrs will tifrfforf morf dlosfly mbtdi tif
     * tiforftidbl dfsign of tif font, but rfbdbbility mby bf bfffdtfd
     * sindf individubl pbirs of dibrbdtfrs mby not blwbys bppfbr to
     * bf donsistfnt distbndfs bpbrt dfpfnding on iow tif sub-pixfl
     * bddumulbtion of tif glypi origins mfsifs witi tif dfvidf pixfl
     * grid.
     * Enbbling tiis iint mby bf dfsirbblf wifn tfxt lbyout is bfing
     * pfrformfd tibt must bf donsistfnt bdross b widf vbrifty of
     * output rfsolutions.
     * Spfdifidblly, tiis iint mby bf dfsirbblf in situbtions wifrf
     * tif lbyout of tfxt is bfing prfvifwfd on b low rfsolution
     * dfvidf likf b sdrffn for output tibt will fvfntublly bf
     * rfndfrfd on b iigi rfsolution printfr or typfsftting dfvidf.
     * <p>
     * Wifn disbblfd, tif sdblfd dfsign mftrids brf roundfd or bdjustfd
     * to intfgfr distbndfs for lbyout.
     * Tif distbndfs bftwffn bny spfdifid pbir of glypis will bf morf
     * uniform on tif dfvidf, but tif dfnsity bnd totbl lfngti of long
     * strings mby no longfr mbtdi tif tiforftidbl intfntions of tif
     * font dfsignfr.
     * Disbbling tiis iint will typidblly produdf morf rfbdbblf rfsults
     * on low rfsolution dfvidfs likf domputfr monitors.
     * <p>
     * Tif bllowbblf vblufs for tiis kfy brf
     * <ul>
     * <li>{@link #VALUE_FRACTIONALMETRICS_OFF}
     * <li>{@link #VALUE_FRACTIONALMETRICS_ON}
     * <li>{@link #VALUE_FRACTIONALMETRICS_DEFAULT}
     * </ul>
     */
    publid stbtid finbl Kfy KEY_FRACTIONALMETRICS =
         SunHints.KEY_FRACTIONALMETRICS;

    /**
     * Font frbdtionbl mftrids iint vbluf -- dibrbdtfr glypis brf
     * positionfd witi bdvbndf widtis roundfd to pixfl boundbrifs.
     * @sff #KEY_FRACTIONALMETRICS
     */
    publid stbtid finbl Objfdt VALUE_FRACTIONALMETRICS_OFF =
         SunHints.VALUE_FRACTIONALMETRICS_OFF;

    /**
     * Font frbdtionbl mftrids iint vbluf -- dibrbdtfr glypis brf
     * positionfd witi sub-pixfl bddurbdy.
     * @sff #KEY_FRACTIONALMETRICS
     */
    publid stbtid finbl Objfdt VALUE_FRACTIONALMETRICS_ON =
         SunHints.VALUE_FRACTIONALMETRICS_ON;

    /**
     * Font frbdtionbl mftrids iint vbluf -- dibrbdtfr glypis brf
     * positionfd witi bddurbdy diosfn by tif implfmfntbtion.
     * @sff #KEY_FRACTIONALMETRICS
     */
    publid stbtid finbl Objfdt VALUE_FRACTIONALMETRICS_DEFAULT =
         SunHints.VALUE_FRACTIONALMETRICS_DEFAULT;

    /**
     * Intfrpolbtion iint kfy.
     * Tif {@dodf INTERPOLATION} iint dontrols iow imbgf pixfls brf
     * filtfrfd or rfsbmplfd during bn imbgf rfndfring opfrbtion.
     * <p>
     * Impliditly imbgfs brf dffinfd to providf dolor sbmplfs bt
     * intfgfr doordinbtf lodbtions.
     * Wifn imbgfs brf rfndfrfd uprigit witi no sdbling onto b
     * dfstinbtion, tif dioidf of wiidi imbgf pixfls mbp to wiidi
     * dfvidf pixfls is obvious bnd tif sbmplfs bt tif intfgfr
     * doordinbtf lodbtions in tif imbgf brf trbnsffrfd to tif
     * pixfls bt tif dorrfsponding intfgfr lodbtions on tif dfvidf
     * pixfl grid onf for onf.
     * Wifn imbgfs brf rfndfrfd in b sdblfd, rotbtfd, or otifrwisf
     * trbnsformfd doordinbtf systfm, tifn tif mbpping of dfvidf
     * pixfl doordinbtfs bbdk to tif imbgf dbn rbisf tif qufstion
     * of wibt dolor sbmplf to usf for tif dontinuous doordinbtfs
     * tibt lif bftwffn tif intfgfr lodbtions of tif providfd imbgf
     * sbmplfs.
     * Intfrpolbtion blgoritims dffinf fundtions wiidi providf b
     * dolor sbmplf for bny dontinuous doordinbtf in bn imbgf bbsfd
     * on tif dolor sbmplfs bt tif surrounding intfgfr doordinbtfs.
     * <p>
     * Tif bllowbblf vblufs for tiis iint brf
     * <ul>
     * <li>{@link #VALUE_INTERPOLATION_NEAREST_NEIGHBOR}
     * <li>{@link #VALUE_INTERPOLATION_BILINEAR}
     * <li>{@link #VALUE_INTERPOLATION_BICUBIC}
     * </ul>
     */
    publid stbtid finbl Kfy KEY_INTERPOLATION =
         SunHints.KEY_INTERPOLATION;

    /**
     * Intfrpolbtion iint vbluf -- tif dolor sbmplf of tif nfbrfst
     * nfigiboring intfgfr doordinbtf sbmplf in tif imbgf is usfd.
     * Condfptublly tif imbgf is vifwfd bs b grid of unit-sizfd
     * squbrf rfgions of dolor dfntfrfd bround tif dfntfr of fbdi
     * imbgf pixfl.
     * <p>
     * As tif imbgf is sdblfd up, it will look dorrfspondingly blodky.
     * As tif imbgf is sdblfd down, tif dolors for sourdf pixfls will
     * bf fitifr usfd unmodififd, or skippfd fntirfly in tif output
     * rfprfsfntbtion.
     *
     * @sff #KEY_INTERPOLATION
     */
    publid stbtid finbl Objfdt VALUE_INTERPOLATION_NEAREST_NEIGHBOR =
         SunHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;

    /**
     * Intfrpolbtion iint vbluf -- tif dolor sbmplfs of tif 4 nfbrfst
     * nfigiboring intfgfr doordinbtf sbmplfs in tif imbgf brf
     * intfrpolbtfd linfbrly to produdf b dolor sbmplf.
     * Condfptublly tif imbgf is vifwfd bs b sft of infinitfly smbll
     * point dolor sbmplfs wiidi ibvf vbluf only bt tif dfntfrs of
     * intfgfr doordinbtf pixfls bnd tif spbdf bftwffn tiosf pixfl
     * dfntfrs is fillfd witi linfbr rbmps of dolors tibt donnfdt
     * bdjbdfnt disdrftf sbmplfs in b strbigit linf.
     * <p>
     * As tif imbgf is sdblfd up, tifrf brf no blodky fdgfs bftwffn
     * tif dolors in tif imbgf bs tifrf brf witi
     * {@link #VALUE_INTERPOLATION_NEAREST_NEIGHBOR NEAREST_NEIGHBOR},
     * but tif blfnding mby siow somf subtlf disdontinuitifs blong tif
     * iorizontbl bnd vfrtidbl fdgfs tibt linf up witi tif sbmplfs
     * dbusfd by b suddfn dibngf in tif slopf of tif intfrpolbtion
     * from onf sidf of b sbmplf to tif otifr.
     * As tif imbgf is sdblfd down, morf imbgf pixfls ibvf tifir
     * dolor sbmplfs rfprfsfntfd in tif rfsulting output sindf fbdi
     * output pixfl rfdfivfs dolor informbtion from up to 4 imbgf
     * pixfls.
     *
     * @sff #KEY_INTERPOLATION
     */
    publid stbtid finbl Objfdt VALUE_INTERPOLATION_BILINEAR =
         SunHints.VALUE_INTERPOLATION_BILINEAR;

    /**
     * Intfrpolbtion iint vbluf -- tif dolor sbmplfs of 9 nfbrby
     * intfgfr doordinbtf sbmplfs in tif imbgf brf intfrpolbtfd using
     * b dubid fundtion in boti {@dodf X} bnd {@dodf Y} to produdf
     * b dolor sbmplf.
     * Condfptublly tif vifw of tif imbgf is vfry similbr to tif vifw
     * usfd in tif {@link #VALUE_INTERPOLATION_BILINEAR BILINEAR}
     * blgoritim fxdfpt tibt tif rbmps of dolors tibt donnfdt bftwffn
     * tif sbmplfs brf durvfd bnd ibvf bfttfr dontinuity of slopf
     * bs tify dross ovfr bftwffn sbmplf boundbrifs.
     * <p>
     * As tif imbgf is sdblfd up, tifrf brf no blodky fdgfs bnd tif
     * intfrpolbtion siould bppfbr smootifr bnd witi bfttfr dfpidtions
     * of bny fdgfs in tif originbl imbgf tibn witi {@dodf BILINEAR}.
     * As tif imbgf is sdblfd down, fvfn morf of tif originbl dolor
     * sbmplfs from tif originbl imbgf will ibvf tifir dolor informbtion
     * dbrrifd tirougi bnd rfprfsfntfd.
     *
     * @sff #KEY_INTERPOLATION
     */
    publid stbtid finbl Objfdt VALUE_INTERPOLATION_BICUBIC =
         SunHints.VALUE_INTERPOLATION_BICUBIC;

    /**
     * Alpib intfrpolbtion iint kfy.
     * Tif {@dodf ALPHA_INTERPOLATION} iint is b gfnfrbl iint tibt
     * providfs b iigi lfvfl rfdommfndbtion bs to wiftifr to bibs
     * blpib blfnding blgoritim dioidfs morf for spffd or qublity
     * wifn fvblubting trbdfoffs.
     * <p>
     * Tiis iint dould dontrol tif dioidf of blpib blfnding
     * dbldulbtions tibt sbdrifidf somf prfdision to usf fbst
     * lookup tbblfs or lowfr prfdision SIMD instrudtions.
     * Tiis iint dould blso dontrol wiftifr or not tif dolor
     * bnd blpib vblufs brf donvfrtfd into b linfbr dolor spbdf
     * during tif dbldulbtions for b morf linfbr visubl ffffdt
     * bt tif fxpfnsf of bdditionbl pfr-pixfl dbldulbtions.
     * <p>
     * Tif bllowbblf vblufs for tiis iint brf
     * <ul>
     * <li>{@link #VALUE_ALPHA_INTERPOLATION_SPEED}
     * <li>{@link #VALUE_ALPHA_INTERPOLATION_QUALITY}
     * <li>{@link #VALUE_ALPHA_INTERPOLATION_DEFAULT}
     * </ul>
     */
    publid stbtid finbl Kfy KEY_ALPHA_INTERPOLATION =
         SunHints.KEY_ALPHA_INTERPOLATION;

    /**
     * Alpib intfrpolbtion iint vbluf -- blpib blfnding blgoritims
     * brf diosfn witi b prfffrfndf for dbldulbtion spffd.
     * @sff #KEY_ALPHA_INTERPOLATION
     */
    publid stbtid finbl Objfdt VALUE_ALPHA_INTERPOLATION_SPEED =
         SunHints.VALUE_ALPHA_INTERPOLATION_SPEED;

    /**
     * Alpib intfrpolbtion iint vbluf -- blpib blfnding blgoritims
     * brf diosfn witi b prfffrfndf for prfdision bnd visubl qublity.
     * @sff #KEY_ALPHA_INTERPOLATION
     */
    publid stbtid finbl Objfdt VALUE_ALPHA_INTERPOLATION_QUALITY =
         SunHints.VALUE_ALPHA_INTERPOLATION_QUALITY;

    /**
     * Alpib intfrpolbtion iint vbluf -- blpib blfnding blgoritims
     * brf diosfn by tif implfmfntbtion for b good trbdfoff of
     * pfrformbndf vs. qublity.
     * @sff #KEY_ALPHA_INTERPOLATION
     */
    publid stbtid finbl Objfdt VALUE_ALPHA_INTERPOLATION_DEFAULT =
         SunHints.VALUE_ALPHA_INTERPOLATION_DEFAULT;

    /**
     * Color rfndfring iint kfy.
     * Tif {@dodf COLOR_RENDERING} iint dontrols tif bddurbdy of
     * bpproximbtion bnd donvfrsion wifn storing dolors into b
     * dfstinbtion imbgf or surfbdf.
     * <p>
     * Wifn b rfndfring or imbgf mbnipulbtion opfrbtion produdfs
     * b dolor vbluf tibt must bf storfd into b dfstinbtion, it
     * must first donvfrt tibt dolor into b form suitbblf for
     * storing into tif dfstinbtion imbgf or surfbdf.
     * Minimblly, tif dolor domponfnts must bf donvfrtfd to bit
     * rfprfsfntbtions bnd ordfrfd in tif dorrfdt ordfr or bn
     * indfx into b dolor lookup tbblf must bf diosfn bfforf
     * tif dbtb dbn bf storfd into tif dfstinbtion mfmory.
     * Witiout tiis minimbl donvfrsion, tif dbtb in tif dfstinbtion
     * would likfly rfprfsfnt rbndom, indorrfdt or possibly fvfn
     * unsupportfd vblufs.
     * Algoritims to quidkly donvfrt tif rfsults of rfndfring
     * opfrbtions into tif dolor formbt of most dommon dfstinbtions
     * brf wfll known bnd fbirly optimbl to fxfdutf.
     * <p>
     * Simply pfrforming tif most bbsid dolor formbt donvfrsion to
     * storf dolors into b dfstinbtion dbn potfntiblly ignorf b
     * difffrfndf in tif dblibrbtion of tif
     * {@link jbvb.bwt.dolor.ColorSpbdf}
     * of tif sourdf bnd dfstinbtion or otifr fbdtors sudi bs tif
     * linfbrity of tif gbmmb dorrfdtion.
     * Unlfss tif sourdf bnd dfstinbtion {@dodf ColorSpbdf} brf
     * idfntidbl, to dorrfdtly pfrform b rfndfring opfrbtion witi
     * tif most dbrf tbkfn for tif bddurbdy of tif dolors bfing
     * rfprfsfntfd, tif sourdf dolors siould bf donvfrtfd to b
     * dfvidf indfpfndfnt {@dodf ColorSpbdf} bnd tif rfsults tifn
     * donvfrtfd bbdk to tif dfstinbtion {@dodf ColorSpbdf}.
     * Furtifrmorf, if dbldulbtions sudi bs tif blfnding of multiplf
     * sourdf dolors brf to bf pfrformfd during tif rfndfring
     * opfrbtion, grfbtfr visubl dlbrity dbn bf bdiifvfd if tif
     * intfrmfdibtf dfvidf indfpfndfnt {@dodf ColorSpbdf} is
     * diosfn to ibvf b linfbr rflbtionsiip bftwffn tif vblufs
     * bfing dbldulbtfd bnd tif pfrdfption of tif iumbn fyf to
     * tif rfsponsf durvfs of tif output dfvidf.
     * <p>
     * Tif bllowbblf vblufs for tiis iint brf
     * <ul>
     * <li>{@link #VALUE_COLOR_RENDER_SPEED}
     * <li>{@link #VALUE_COLOR_RENDER_QUALITY}
     * <li>{@link #VALUE_COLOR_RENDER_DEFAULT}
     * </ul>
     */
    publid stbtid finbl Kfy KEY_COLOR_RENDERING =
         SunHints.KEY_COLOR_RENDERING;

    /**
     * Color rfndfring iint vbluf -- pfrform tif fbstfst dolor
     * donvfrsion to tif formbt of tif output dfvidf.
     * @sff #KEY_COLOR_RENDERING
     */
    publid stbtid finbl Objfdt VALUE_COLOR_RENDER_SPEED =
         SunHints.VALUE_COLOR_RENDER_SPEED;

    /**
     * Color rfndfring iint vbluf -- pfrform tif dolor donvfrsion
     * dbldulbtions witi tif iigifst bddurbdy bnd visubl qublity.
     * @sff #KEY_COLOR_RENDERING
     */
    publid stbtid finbl Objfdt VALUE_COLOR_RENDER_QUALITY =
         SunHints.VALUE_COLOR_RENDER_QUALITY;

    /**
     * Color rfndfring iint vbluf -- pfrform dolor donvfrsion
     * dbldulbtions bs diosfn by tif implfmfntbtion to rfprfsfnt
     * tif bfst bvbilbblf trbdfoff bftwffn pfrformbndf bnd
     * bddurbdy.
     * @sff #KEY_COLOR_RENDERING
     */
    publid stbtid finbl Objfdt VALUE_COLOR_RENDER_DEFAULT =
         SunHints.VALUE_COLOR_RENDER_DEFAULT;

    /**
     * Strokf normblizbtion dontrol iint kfy.
     * Tif {@dodf STROKE_CONTROL} iint dontrols wiftifr b rfndfring
     * implfmfntbtion siould or is bllowfd to modify tif gfomftry
     * of rfndfrfd sibpfs for vbrious purposfs.
     * <p>
     * Somf implfmfntbtions mby bf bblf to usf bn optimizfd plbtform
     * rfndfring librbry wiidi mby bf fbstfr tibn trbditionbl softwbrf
     * rfndfring blgoritims on b givfn plbtform, but wiidi mby blso
     * not support flobting point doordinbtfs.
     * Somf implfmfntbtions mby blso ibvf sopiistidbtfd blgoritims
     * wiidi pfrturb tif doordinbtfs of b pbti so tibt widf linfs
     * bppfbr morf uniform in widti bnd spbding.
     * <p>
     * If bn implfmfntbtion pfrforms bny typf of modifidbtion or
     * "normblizbtion" of b pbti, it siould nfvfr movf tif doordinbtfs
     * by morf tibn iblf b pixfl in bny dirfdtion.
     * <p>
     * Tif bllowbblf vblufs for tiis iint brf
     * <ul>
     * <li>{@link #VALUE_STROKE_NORMALIZE}
     * <li>{@link #VALUE_STROKE_PURE}
     * <li>{@link #VALUE_STROKE_DEFAULT}
     * </ul>
     * @sindf 1.3
     */
    publid stbtid finbl Kfy KEY_STROKE_CONTROL =
        SunHints.KEY_STROKE_CONTROL;

    /**
     * Strokf normblizbtion dontrol iint vbluf -- gfomftry mby bf
     * modififd or lfft purf dfpfnding on tif trbdfoffs in b givfn
     * implfmfntbtion.
     * Typidblly tiis sftting bllows bn implfmfntbtion to usf b fbst
     * intfgfr doordinbtf bbsfd plbtform rfndfring librbry, but dofs
     * not spfdifidblly rfqufst normblizbtion for uniformity or
     * bfstiftids.
     *
     * @sff #KEY_STROKE_CONTROL
     * @sindf 1.3
     */
    publid stbtid finbl Objfdt VALUE_STROKE_DEFAULT =
        SunHints.VALUE_STROKE_DEFAULT;

    /**
     * Strokf normblizbtion dontrol iint vbluf -- gfomftry siould
     * bf normblizfd to improvf uniformity or spbding of linfs bnd
     * ovfrbll bfstiftids.
     * Notf tibt difffrfnt normblizbtion blgoritims mby bf morf
     * suddfssful tibn otifrs for givfn input pbtis.
     *
     * @sff #KEY_STROKE_CONTROL
     * @sindf 1.3
     */
    publid stbtid finbl Objfdt VALUE_STROKE_NORMALIZE =
        SunHints.VALUE_STROKE_NORMALIZE;

    /**
     * Strokf normblizbtion dontrol iint vbluf -- gfomftry siould
     * bf lfft unmodififd bnd rfndfrfd witi sub-pixfl bddurbdy.
     *
     * @sff #KEY_STROKE_CONTROL
     * @sindf 1.3
     */
    publid stbtid finbl Objfdt VALUE_STROKE_PURE =
        SunHints.VALUE_STROKE_PURE;

    /**
     * Construdts b nfw objfdt witi kfys bnd vblufs initiblizfd
     * from tif spfdififd Mbp objfdt wiidi mby bf null.
     * @pbrbm init b mbp of kfy/vbluf pbirs to initiblizf tif iints
     *          or null if tif objfdt siould bf fmpty
     */
    publid RfndfringHints(Mbp<Kfy,?> init) {
        if (init != null) {
            iintmbp.putAll(init);
        }
    }

    /**
     * Construdts b nfw objfdt witi tif spfdififd kfy/vbluf pbir.
     * @pbrbm kfy tif kfy of tif pbrtidulbr iint propfrty
     * @pbrbm vbluf tif vbluf of tif iint propfrty spfdififd witi
     * <dodf>kfy</dodf>
     */
    publid RfndfringHints(Kfy kfy, Objfdt vbluf) {
        iintmbp.put(kfy, vbluf);
    }

    /**
     * Rfturns tif numbfr of kfy-vbluf mbppings in tiis
     * <dodf>RfndfringHints</dodf>.
     *
     * @rfturn tif numbfr of kfy-vbluf mbppings in tiis
     * <dodf>RfndfringHints</dodf>.
     */
    publid int sizf() {
        rfturn iintmbp.sizf();
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis
     * <dodf>RfndfringHints</dodf> dontbins no kfy-vbluf mbppings.
     *
     * @rfturn <dodf>truf</dodf> if tiis
     * <dodf>RfndfringHints</dodf> dontbins no kfy-vbluf mbppings.
     */
    publid boolfbn isEmpty() {
        rfturn iintmbp.isEmpty();
    }

    /**
     * Rfturns {@dodf truf} if tiis {@dodf RfndfringHints}
     *  dontbins b mbpping for tif spfdififd kfy.
     *
     * @pbrbm kfy kfy wiosf prfsfndf in tiis
     * {@dodf RfndfringHints} is to bf tfstfd.
     * @rfturn {@dodf truf} if tiis {@dodf RfndfringHints}
     *          dontbins b mbpping for tif spfdififd kfy.
     * @fxdfption ClbssCbstExdfption if tif kfy dbn not
     *            bf dbst to {@dodf RfndfringHints.Kfy}
     */
    publid boolfbn dontbinsKfy(Objfdt kfy) {
        rfturn iintmbp.dontbinsKfy((Kfy) kfy);
    }

    /**
     * Rfturns truf if tiis RfndfringHints mbps onf or morf kfys to tif
     * spfdififd vbluf.
     * Morf formblly, rfturns <dodf>truf</dodf> if bnd only
     * if tiis <dodf>RfndfringHints</dodf>
     * dontbins bt lfbst onf mbpping to b vbluf <dodf>v</dodf> sudi tibt
     * <prf>
     * (vbluf==null ? v==null : vbluf.fqubls(v))
     * </prf>.
     * Tiis opfrbtion will probbbly rfquirf timf linfbr in tif
     * <dodf>RfndfringHints</dodf> sizf for most implfmfntbtions
     * of <dodf>RfndfringHints</dodf>.
     *
     * @pbrbm vbluf vbluf wiosf prfsfndf in tiis
     *          <dodf>RfndfringHints</dodf> is to bf tfstfd.
     * @rfturn <dodf>truf</dodf> if tiis <dodf>RfndfringHints</dodf>
     *           mbps onf or morf kfys to tif spfdififd vbluf.
     */
    publid boolfbn dontbinsVbluf(Objfdt vbluf) {
        rfturn iintmbp.dontbinsVbluf(vbluf);
    }

    /**
     * Rfturns tif vbluf to wiidi tif spfdififd kfy is mbppfd.
     * @pbrbm   kfy   b rfndfring iint kfy
     * @rfturn  tif vbluf to wiidi tif kfy is mbppfd in tiis objfdt or
     *          {@dodf null} if tif kfy is not mbppfd to bny vbluf in
     *          tiis objfdt.
     * @fxdfption ClbssCbstExdfption if tif kfy dbn not
     *            bf dbst to {@dodf RfndfringHints.Kfy}
     * @sff     #put(Objfdt, Objfdt)
     */
    publid Objfdt gft(Objfdt kfy) {
        rfturn iintmbp.gft((Kfy) kfy);
    }

    /**
     * Mbps tif spfdififd {@dodf kfy} to tif spfdififd
     * {@dodf vbluf} in tiis {@dodf RfndfringHints} objfdt.
     * Nfitifr tif kfy nor tif vbluf dbn bf {@dodf null}.
     * Tif vbluf dbn bf rftrifvfd by dblling tif {@dodf gft} mftiod
     * witi b kfy tibt is fqubl to tif originbl kfy.
     * @pbrbm      kfy     tif rfndfring iint kfy.
     * @pbrbm      vbluf   tif rfndfring iint vbluf.
     * @rfturn     tif prfvious vbluf of tif spfdififd kfy in tiis objfdt
     *             or {@dodf null} if it did not ibvf onf.
     * @fxdfption NullPointfrExdfption if tif kfy is
     *            {@dodf null}.
     * @fxdfption ClbssCbstExdfption if tif kfy dbn not
     *            bf dbst to {@dodf RfndfringHints.Kfy}
     * @fxdfption IllfgblArgumfntExdfption if tif
     *            {@link Kfy#isCompbtiblfVbluf(jbvb.lbng.Objfdt)
     *                   Kfy.isCompbtiblfVbluf()}
     *            mftiod of tif spfdififd kfy rfturns fblsf for tif
     *            spfdififd vbluf
     * @sff     #gft(Objfdt)
     */
    publid Objfdt put(Objfdt kfy, Objfdt vbluf) {
        if (!((Kfy) kfy).isCompbtiblfVbluf(vbluf)) {
            tirow nfw IllfgblArgumfntExdfption(vbluf+
                                               " indompbtiblf witi "+
                                               kfy);
        }
        rfturn iintmbp.put((Kfy) kfy, vbluf);
    }

    /**
     * Adds bll of tif kfys bnd dorrfsponding vblufs from tif spfdififd
     * <dodf>RfndfringHints</dodf> objfdt to tiis
     * <dodf>RfndfringHints</dodf> objfdt. Kfys tibt brf prfsfnt in
     * tiis <dodf>RfndfringHints</dodf> objfdt, but not in tif spfdififd
     * <dodf>RfndfringHints</dodf> objfdt brf not bfffdtfd.
     * @pbrbm iints tif sft of kfy/vbluf pbirs to bf bddfd to tiis
     * <dodf>RfndfringHints</dodf> objfdt
     */
    publid void bdd(RfndfringHints iints) {
        iintmbp.putAll(iints.iintmbp);
    }

    /**
     * Clfbrs tiis <dodf>RfndfringHints</dodf> objfdt of bll kfy/vbluf
     * pbirs.
     */
    publid void dlfbr() {
        iintmbp.dlfbr();
    }

    /**
     * Rfmovfs tif kfy bnd its dorrfsponding vbluf from tiis
     * {@dodf RfndfringHints} objfdt. Tiis mftiod dofs notiing if tif
     * kfy is not in tiis {@dodf RfndfringHints} objfdt.
     * @pbrbm   kfy   tif rfndfring iints kfy tibt nffds to bf rfmovfd
     * @fxdfption ClbssCbstExdfption if tif kfy dbn not
     *            bf dbst to {@dodf RfndfringHints.Kfy}
     * @rfturn  tif vbluf to wiidi tif kfy ibd prfviously bffn mbppfd in tiis
     *          {@dodf RfndfringHints} objfdt, or {@dodf null}
     *          if tif kfy did not ibvf b mbpping.
     */
    publid Objfdt rfmovf(Objfdt kfy) {
        rfturn iintmbp.rfmovf((Kfy) kfy);
    }

    /**
     * Copifs bll of tif mbppings from tif spfdififd {@dodf Mbp}
     * to tiis {@dodf RfndfringHints}.  Tifsf mbppings rfplbdf
     * bny mbppings tibt tiis {@dodf RfndfringHints} ibd for bny
     * of tif kfys durrfntly in tif spfdififd {@dodf Mbp}.
     * @pbrbm m tif spfdififd {@dodf Mbp}
     * @fxdfption ClbssCbstExdfption dlbss of b kfy or vbluf
     *          in tif spfdififd {@dodf Mbp} prfvfnts it from bfing
     *          storfd in tiis {@dodf RfndfringHints}.
     * @fxdfption IllfgblArgumfntExdfption somf bspfdt
     *          of b kfy or vbluf in tif spfdififd {@dodf Mbp}
     *           prfvfnts it from bfing storfd in
     *            tiis {@dodf RfndfringHints}.
     */
    publid void putAll(Mbp<?,?> m) {
        // ## jbvbd bug?
        //if (m instbndfof RfndfringHints) {
        if (RfndfringHints.dlbss.isInstbndf(m)) {
            //iintmbp.putAll(((RfndfringHints) m).iintmbp);
            for (Mbp.Entry<?,?> fntry : m.fntrySft())
                iintmbp.put(fntry.gftKfy(), fntry.gftVbluf());
        } flsf {
            // Funnfl fbdi kfy/vbluf pbir tirougi our protfdtfd put mftiod
            for (Mbp.Entry<?,?> fntry : m.fntrySft())
                put(fntry.gftKfy(), fntry.gftVbluf());
        }
    }

    /**
     * Rfturns b <dodf>Sft</dodf> vifw of tif Kfys dontbinfd in tiis
     * <dodf>RfndfringHints</dodf>.  Tif Sft is bbdkfd by tif
     * <dodf>RfndfringHints</dodf>, so dibngfs to tif
     * <dodf>RfndfringHints</dodf> brf rfflfdtfd in tif <dodf>Sft</dodf>,
     * bnd vidf-vfrsb.  If tif <dodf>RfndfringHints</dodf> is modififd
     * wiilf bn itfrbtion ovfr tif <dodf>Sft</dodf> is in progrfss,
     * tif rfsults of tif itfrbtion brf undffinfd.  Tif <dodf>Sft</dodf>
     * supports flfmfnt rfmovbl, wiidi rfmovfs tif dorrfsponding
     * mbpping from tif <dodf>RfndfringHints</dodf>, vib tif
     * <dodf>Itfrbtor.rfmovf</dodf>, <dodf>Sft.rfmovf</dodf>,
     * <dodf>rfmovfAll</dodf> <dodf>rftbinAll</dodf>, bnd
     * <dodf>dlfbr</dodf> opfrbtions.  It dofs not support
     * tif <dodf>bdd</dodf> or <dodf>bddAll</dodf> opfrbtions.
     *
     * @rfturn b <dodf>Sft</dodf> vifw of tif kfys dontbinfd
     * in tiis <dodf>RfndfringHints</dodf>.
     */
    publid Sft<Objfdt> kfySft() {
        rfturn iintmbp.kfySft();
    }

    /**
     * Rfturns b <dodf>Collfdtion</dodf> vifw of tif vblufs
     * dontbinfd in tiis <dodf>RfndfrinHints</dodf>.
     * Tif <dodf>Collfdtion</dodf> is bbdkfd by tif
     * <dodf>RfndfringHints</dodf>, so dibngfs to
     * tif <dodf>RfndfringHints</dodf> brf rfflfdtfd in
     * tif <dodf>Collfdtion</dodf>, bnd vidf-vfrsb.
     * If tif <dodf>RfndfringHints</dodf> is modififd wiilf
     * bn itfrbtion ovfr tif <dodf>Collfdtion</dodf> is
     * in progrfss, tif rfsults of tif itfrbtion brf undffinfd.
     * Tif <dodf>Collfdtion</dodf> supports flfmfnt rfmovbl,
     * wiidi rfmovfs tif dorrfsponding mbpping from tif
     * <dodf>RfndfringHints</dodf>, vib tif
     * <dodf>Itfrbtor.rfmovf</dodf>,
     * <dodf>Collfdtion.rfmovf</dodf>, <dodf>rfmovfAll</dodf>,
     * <dodf>rftbinAll</dodf> bnd <dodf>dlfbr</dodf> opfrbtions.
     * It dofs not support tif <dodf>bdd</dodf> or
     * <dodf>bddAll</dodf> opfrbtions.
     *
     * @rfturn b <dodf>Collfdtion</dodf> vifw of tif vblufs
     *          dontbinfd in tiis <dodf>RfndfringHints</dodf>.
     */
    publid Collfdtion<Objfdt> vblufs() {
        rfturn iintmbp.vblufs();
    }

    /**
     * Rfturns b <dodf>Sft</dodf> vifw of tif mbppings dontbinfd
     * in tiis <dodf>RfndfringHints</dodf>.  Ebdi flfmfnt in tif
     * rfturnfd <dodf>Sft</dodf> is b <dodf>Mbp.Entry</dodf>.
     * Tif <dodf>Sft</dodf> is bbdkfd by tif <dodf>RfndfringHints</dodf>,
     * so dibngfs to tif <dodf>RfndfringHints</dodf> brf rfflfdtfd
     * in tif <dodf>Sft</dodf>, bnd vidf-vfrsb.  If tif
     * <dodf>RfndfringHints</dodf> is modififd wiilf
     * wiilf bn itfrbtion ovfr tif <dodf>Sft</dodf> is in progrfss,
     * tif rfsults of tif itfrbtion brf undffinfd.
     * <p>
     * Tif fntrySft rfturnfd from b <dodf>RfndfringHints</dodf> objfdt
     * is not modifibblf.
     *
     * @rfturn b <dodf>Sft</dodf> vifw of tif mbppings dontbinfd in
     * tiis <dodf>RfndfringHints</dodf>.
     */
    publid Sft<Mbp.Entry<Objfdt,Objfdt>> fntrySft() {
        rfturn Collfdtions.unmodifibblfMbp(iintmbp).fntrySft();
    }

    /**
     * Compbrfs tif spfdififd <dodf>Objfdt</dodf> witi tiis
     * <dodf>RfndfringHints</dodf> for fqublity.
     * Rfturns <dodf>truf</dodf> if tif spfdififd objfdt is blso b
     * <dodf>Mbp</dodf> bnd tif two <dodf>Mbp</dodf> objfdts rfprfsfnt
     * tif sbmf mbppings.  Morf formblly, two <dodf>Mbp</dodf> objfdts
     * <dodf>t1</dodf> bnd <dodf>t2</dodf> rfprfsfnt tif sbmf mbppings
     * if <dodf>t1.kfySft().fqubls(t2.kfySft())</dodf> bnd for fvfry
     * kfy <dodf>k</dodf> in <dodf>t1.kfySft()</dodf>,
     * <prf>
     * (t1.gft(k)==null ? t2.gft(k)==null : t1.gft(k).fqubls(t2.gft(k)))
     * </prf>.
     * Tiis fnsurfs tibt tif <dodf>fqubls</dodf> mftiod works propfrly bdross
     * difffrfnt implfmfntbtions of tif <dodf>Mbp</dodf> intfrfbdf.
     *
     * @pbrbm o <dodf>Objfdt</dodf> to bf dompbrfd for fqublity witi
     * tiis <dodf>RfndfringHints</dodf>.
     * @rfturn <dodf>truf</dodf> if tif spfdififd <dodf>Objfdt</dodf>
     * is fqubl to tiis <dodf>RfndfringHints</dodf>.
     */
    publid boolfbn fqubls(Objfdt o) {
        if (o instbndfof RfndfringHints) {
            rfturn iintmbp.fqubls(((RfndfringHints) o).iintmbp);
        } flsf if (o instbndfof Mbp) {
            rfturn iintmbp.fqubls(o);
        }
        rfturn fblsf;
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis <dodf>RfndfringHints</dodf>.
     * Tif ibsi dodf of b <dodf>RfndfringHints</dodf> is dffinfd to bf
     * tif sum of tif ibsiCodfs of fbdi <dodf>Entry</dodf> in tif
     * <dodf>RfndfringHints</dodf> objfdt's fntrySft vifw.  Tiis fnsurfs tibt
     * <dodf>t1.fqubls(t2)</dodf> implifs tibt
     * <dodf>t1.ibsiCodf()==t2.ibsiCodf()</dodf> for bny two <dodf>Mbp</dodf>
     * objfdts <dodf>t1</dodf> bnd <dodf>t2</dodf>, bs rfquirfd by tif gfnfrbl
     * dontrbdt of <dodf>Objfdt.ibsiCodf</dodf>.
     *
     * @rfturn tif ibsi dodf vbluf for tiis <dodf>RfndfringHints</dodf>.
     * @sff jbvb.util.Mbp.Entry#ibsiCodf()
     * @sff Objfdt#ibsiCodf()
     * @sff Objfdt#fqubls(Objfdt)
     * @sff #fqubls(Objfdt)
     */
    publid int ibsiCodf() {
        rfturn iintmbp.ibsiCodf();
    }

    /**
     * Crfbtfs b dlonf of tiis <dodf>RfndfringHints</dodf> objfdt
     * tibt ibs tif sbmf dontfnts bs tiis <dodf>RfndfringHints</dodf>
     * objfdt.
     * @rfturn b dlonf of tiis instbndf.
     */
    @SupprfssWbrnings("undifdkfd")
    publid Objfdt dlonf() {
        RfndfringHints ri;
        try {
            ri = (RfndfringHints) supfr.dlonf();
            if (iintmbp != null) {
                ri.iintmbp = (HbsiMbp<Objfdt,Objfdt>) iintmbp.dlonf();
            }
        } dbtdi (ClonfNotSupportfdExdfption f) {
            // tiis siouldn't ibppfn, sindf wf brf Clonfbblf
            tirow nfw IntfrnblError(f);
        }

        rfturn ri;
    }

    /**
     * Rfturns b rbtifr long string rfprfsfntbtion of tif ibsimbp
     * wiidi dontbins tif mbppings of kfys to vblufs for tiis
     * <dodf>RfndfringHints</dodf> objfdt.
     * @rfturn  b string rfprfsfntbtion of tiis objfdt.
     */
    publid String toString() {
        if (iintmbp == null) {
            rfturn gftClbss().gftNbmf() + "@" +
                Intfgfr.toHfxString(ibsiCodf()) +
                " (0 iints)";
        }

        rfturn iintmbp.toString();
    }
}
