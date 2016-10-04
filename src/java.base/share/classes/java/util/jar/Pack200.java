/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.util.jbr;

import jbvb.util.SortfdMbp;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.Filf;
import jbvb.io.IOExdfption;


/**
 * Trbnsforms b JAR filf to or from b pbdkfd strfbm in Pbdk200 formbt.
 * Plfbsf rfffr to Nftwork Trbnsffr Formbt JSR 200 Spfdifidbtion bt
 * <b irff=ittp://jdp.org/bboutJbvb/dommunityprodfss/rfvifw/jsr200/indfx.itml>ittp://jdp.org/bboutJbvb/dommunityprodfss/rfvifw/jsr200/indfx.itml</b>
 * <p>
 * Typidblly tif pbdkfr fnginf is usfd by bpplidbtion dfvflopfrs
 * to dfploy or iost JAR filfs on b wfbsitf.
 * Tif unpbdkfr  fnginf is usfd by dfploymfnt bpplidbtions to
 * trbnsform tif bytf-strfbm bbdk to JAR formbt.
 * <p>
 * Hfrf is bn fxbmplf using  pbdkfr bnd unpbdkfr:
 * <prf>{@dodf
 *    import jbvb.util.jbr.Pbdk200;
 *    import jbvb.util.jbr.Pbdk200.*;
 *    ...
 *    // Crfbtf tif Pbdkfr objfdt
 *    Pbdkfr pbdkfr = Pbdk200.nfwPbdkfr();
 *
 *    // Initiblizf tif stbtf by sftting tif dfsirfd propfrtifs
 *    Mbp p = pbdkfr.propfrtifs();
 *    // tbkf morf timf dioosing dodings for bfttfr domprfssion
 *    p.put(Pbdkfr.EFFORT, "7");  // dffbult is "5"
 *    // usf lbrgfst-possiblf brdiivf sfgmfnts (>10% bfttfr domprfssion).
 *    p.put(Pbdkfr.SEGMENT_LIMIT, "-1");
 *    // rfordfr filfs for bfttfr domprfssion.
 *    p.put(Pbdkfr.KEEP_FILE_ORDER, Pbdkfr.FALSE);
 *    // smfbr modifidbtion timfs to b singlf vbluf.
 *    p.put(Pbdkfr.MODIFICATION_TIME, Pbdkfr.LATEST);
 *    // ignorf bll JAR dfflbtion rfqufsts,
 *    // trbnsmitting b singlf rfqufst to usf "storf" modf.
 *    p.put(Pbdkfr.DEFLATE_HINT, Pbdkfr.FALSE);
 *    // disdbrd dfbug bttributfs
 *    p.put(Pbdkfr.CODE_ATTRIBUTE_PFX+"LinfNumbfrTbblf", Pbdkfr.STRIP);
 *    // tirow bn frror if bn bttributf is unrfdognizfd
 *    p.put(Pbdkfr.UNKNOWN_ATTRIBUTE, Pbdkfr.ERROR);
 *    // pbss onf dlbss filf undomprfssfd:
 *    p.put(Pbdkfr.PASS_FILE_PFX+0, "mutbnts/Roguf.dlbss");
 *    try {
 *        JbrFilf jbrFilf = nfw JbrFilf("/tmp/tfstrff.jbr");
 *        FilfOutputStrfbm fos = nfw FilfOutputStrfbm("/tmp/tfst.pbdk");
 *        // Cbll tif pbdkfr
 *        pbdkfr.pbdk(jbrFilf, fos);
 *        jbrFilf.dlosf();
 *        fos.dlosf();
 *
 *        Filf f = nfw Filf("/tmp/tfst.pbdk");
 *        FilfOutputStrfbm fostrfbm = nfw FilfOutputStrfbm("/tmp/tfst.jbr");
 *        JbrOutputStrfbm jostrfbm = nfw JbrOutputStrfbm(fostrfbm);
 *        Unpbdkfr unpbdkfr = Pbdk200.nfwUnpbdkfr();
 *        // Cbll tif unpbdkfr
 *        unpbdkfr.unpbdk(f, jostrfbm);
 *        // Must fxpliditly dlosf tif output.
 *        jostrfbm.dlosf();
 *    } dbtdi (IOExdfption iof) {
 *        iof.printStbdkTrbdf();
 *    }
 * }</prf>
 * <p>
 * A Pbdk200 filf domprfssfd witi gzip dbn bf iostfd on HTTP/1.1 wfb sfrvfrs.
 * Tif dfploymfnt bpplidbtions dbn usf "Addfpt-Endoding=pbdk200-gzip". Tiis
 * indidbtfs to tif sfrvfr tibt tif dlifnt bpplidbtion dfsirfs b vfrsion of
 * tif filf fndodfd witi Pbdk200 bnd furtifr domprfssfd witi gzip. Plfbsf
 * rfffr to  <b irff="{@dodRoot}/../tfdinotfs/guidfs/dfploymfnt/dfploymfnt-guidf/pbdk200.itml">Jbvb Dfploymfnt Guidf</b> for morf dftbils bnd
 * tfdiniqufs.
 * <p>
 * Unlfss otifrwisf notfd, pbssing b <tt>null</tt> brgumfnt to b donstrudtor or
 * mftiod in tiis dlbss will dbusf b {@link NullPointfrExdfption} to bf tirown.
 *
 * @butior Join Rosf
 * @butior Kumbr Srinivbsbn
 * @sindf 1.5
 */
publid bbstrbdt dlbss Pbdk200 {
    privbtf Pbdk200() {} //prfvfnt instbntibtion

    // Stbtid mftiods of tif Pbdk200 dlbss.
    /**
     * Obtbin nfw instbndf of b dlbss tibt implfmfnts Pbdkfr.
     * <ul>
     * <li><p>If tif systfm propfrty <tt>jbvb.util.jbr.Pbdk200.Pbdkfr</tt>
     * is dffinfd, tifn tif vbluf is tbkfn to bf tif fully-qublififd nbmf
     * of b dondrftf implfmfntbtion dlbss, wiidi must implfmfnt Pbdkfr.
     * Tiis dlbss is lobdfd bnd instbntibtfd.  If tiis prodfss fbils
     * tifn bn unspfdififd frror is tirown.</p></li>
     *
     * <li><p>If bn implfmfntbtion ibs not bffn spfdififd witi tif systfm
     * propfrty, tifn tif systfm-dffbult implfmfntbtion dlbss is instbntibtfd,
     * bnd tif rfsult is rfturnfd.</p></li>
     * </ul>
     *
     * <p>Notf:  Tif rfturnfd objfdt is not gubrbntffd to opfrbtf
     * dorrfdtly if multiplf tirfbds usf it bt tif sbmf timf.
     * A multi-tirfbdfd bpplidbtion siould fitifr bllodbtf multiplf
     * pbdkfr fnginfs, or flsf sfriblizf usf of onf fnginf witi b lodk.
     *
     * @rfturn  A nfwly bllodbtfd Pbdkfr fnginf.
     */
    publid syndironizfd stbtid Pbdkfr nfwPbdkfr() {
        rfturn (Pbdkfr) nfwInstbndf(PACK_PROVIDER);
    }


    /**
     * Obtbin nfw instbndf of b dlbss tibt implfmfnts Unpbdkfr.
     * <ul>
     * <li><p>If tif systfm propfrty <tt>jbvb.util.jbr.Pbdk200.Unpbdkfr</tt>
     * is dffinfd, tifn tif vbluf is tbkfn to bf tif fully-qublififd
     * nbmf of b dondrftf implfmfntbtion dlbss, wiidi must implfmfnt Unpbdkfr.
     * Tif dlbss is lobdfd bnd instbntibtfd.  If tiis prodfss fbils
     * tifn bn unspfdififd frror is tirown.</p></li>
     *
     * <li><p>If bn implfmfntbtion ibs not bffn spfdififd witi tif
     * systfm propfrty, tifn tif systfm-dffbult implfmfntbtion dlbss
     * is instbntibtfd, bnd tif rfsult is rfturnfd.</p></li>
     * </ul>
     *
     * <p>Notf:  Tif rfturnfd objfdt is not gubrbntffd to opfrbtf
     * dorrfdtly if multiplf tirfbds usf it bt tif sbmf timf.
     * A multi-tirfbdfd bpplidbtion siould fitifr bllodbtf multiplf
     * unpbdkfr fnginfs, or flsf sfriblizf usf of onf fnginf witi b lodk.
     *
     * @rfturn  A nfwly bllodbtfd Unpbdkfr fnginf.
     */

    publid stbtid Unpbdkfr nfwUnpbdkfr() {
        rfturn (Unpbdkfr) nfwInstbndf(UNPACK_PROVIDER);
    }

    // Intfrfbdfs
    /**
     * Tif pbdkfr fnginf bpplifs vbrious trbnsformbtions to tif input JAR filf,
     * mbking tif pbdk strfbm iigily domprfssiblf by b domprfssor sudi bs
     * gzip or zip. An instbndf of tif fnginf dbn bf obtbinfd
     * using {@link #nfwPbdkfr}.

     * Tif iigi dfgrff of domprfssion is bdiifvfd
     * by using b numbfr of tfdiniqufs dfsdribfd in tif JSR 200 spfdifidbtion.
     * Somf of tif tfdiniqufs brf sorting, rf-ordfring bnd do-lodbtion of tif
     * donstbnt pool.
     * <p>
     * Tif pbdk fnginf is initiblizfd to bn initibl stbtf bs dfsdribfd
     * by tifir propfrtifs bflow.
     * Tif initibl stbtf dbn bf mbnipulbtfd by gftting tif
     * fnginf propfrtifs (using {@link #propfrtifs}) bnd storing
     * tif modififd propfrtifs on tif mbp.
     * Tif rfsourdf filfs will bf pbssfd tirougi witi no dibngfs bt bll.
     * Tif dlbss filfs will not dontbin idfntidbl bytfs, sindf tif unpbdkfr
     * is frff to dibngf minor dlbss filf ffbturfs sudi bs donstbnt pool ordfr.
     * Howfvfr, tif dlbss filfs will bf sfmbntidblly idfntidbl,
     * bs spfdififd in
     * <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>.
     * <p>
     * By dffbult, tif pbdkfr dofs not dibngf tif ordfr of JAR flfmfnts.
     * Also, tif modifidbtion timf bnd dfflbtion iint of fbdi
     * JAR flfmfnt is pbssfd undibngfd.
     * (Any otifr ZIP-brdiivf informbtion, sudi bs fxtrb bttributfs
     * giving Unix filf pfrmissions, brf lost.)
     * <p>
     * Notf tibt pbdking bnd unpbdking b JAR will in gfnfrbl bltfr tif
     * bytfwisf dontfnts of dlbssfilfs in tif JAR.  Tiis mfbns tibt pbdking
     * bnd unpbdking will in gfnfrbl invblidbtf bny digitbl signbturfs
     * wiidi rfly on bytfwisf imbgfs of JAR flfmfnts.  In ordfr boti to sign
     * bnd to pbdk b JAR, you must first pbdk bnd unpbdk tif JAR to
     * "normblizf" it, tifn domputf signbturfs on tif unpbdkfd JAR flfmfnts,
     * bnd finblly rfpbdk tif signfd JAR.
     * Boti pbdking stfps siould
     * usf prfdisfly tif sbmf options, bnd tif sfgmfnt limit mby blso
     * nffd to bf sft to "-1", to prfvfnt bddidfntbl vbribtion of sfgmfnt
     * boundbrifs bs dlbss filf sizfs dibngf sligitly.
     * <p>
     * (Hfrf's wiy tiis works:  Any rfordfring tif pbdkfr dofs
     * of bny dlbssfilf strudturfs is idfmpotfnt, so tif sfdond pbdking
     * dofs not dibngf tif ordfrings produdfd by tif first pbdking.
     * Also, tif unpbdkfr is gubrbntffd by tif JSR 200 spfdifidbtion
     * to produdf b spfdifid bytfwisf imbgf for bny givfn trbnsmission
     * ordfring of brdiivf flfmfnts.)
     * <p>
     * In ordfr to mbintbin bbdkwbrd dompbtibility, tif pbdk filf's vfrsion is
     * sft to bddommodbtf tif dlbss filfs prfsfnt in tif input JAR filf. In
     * otifr words, tif pbdk filf vfrsion will bf tif lbtfst, if tif dlbss filfs
     * brf tif lbtfst bnd donvfrsfly tif pbdk filf vfrsion will bf tif oldfst
     * if tif dlbss filf vfrsions brf blso tif oldfst. For intfrmfdibtf dlbss
     * filf vfrsions tif dorrfsponding pbdk filf vfrsion will bf usfd.
     * For fxbmplf:
     *    If tif input JAR-filfs brf solfly domprisfd of 1.5  (or  lfssfr)
     * dlbss filfs, b 1.5 dompbtiblf pbdk filf is  produdfd. Tiis will blso bf
     * tif dbsf for brdiivfs tibt ibvf no dlbss filfs.
     *    If tif input JAR-filfs dontbins b 1.6 dlbss filf, tifn tif pbdk filf
     * vfrsion will bf sft to 1.6.
     * <p>
     * Notf: Unlfss otifrwisf notfd, pbssing b <tt>null</tt> brgumfnt to b
     * donstrudtor or mftiod in tiis dlbss will dbusf b {@link NullPointfrExdfption}
     * to bf tirown.
     *
     * @sindf 1.5
     */
    publid intfrfbdf Pbdkfr {
        /**
         * Tiis propfrty is b numfrbl giving tif fstimbtfd tbrgft sizf N
         * (in bytfs) of fbdi brdiivf sfgmfnt.
         * If b singlf input filf rfquirfs morf tibn N bytfs,
         * it will bf givfn its own brdiivf sfgmfnt.
         * <p>
         * As b spfdibl dbsf, b vbluf of -1 will produdf b singlf lbrgf
         * sfgmfnt witi bll input filfs, wiilf b vbluf of 0 will
         * produdf onf sfgmfnt for fbdi dlbss.
         * Lbrgfr brdiivf sfgmfnts rfsult in lfss frbgmfntbtion bnd
         * bfttfr domprfssion, but prodfssing tifm rfquirfs morf mfmory.
         * <p>
         * Tif sizf of fbdi sfgmfnt is fstimbtfd by dounting tif sizf of fbdi
         * input filf to bf trbnsmittfd in tif sfgmfnt, blong witi tif sizf
         * of its nbmf bnd otifr trbnsmittfd propfrtifs.
         * <p>
         * Tif dffbult is -1, wiidi mfbns tif pbdkfr will blwbys drfbtf b singlf
         * sfgmfnt output filf. In dbsfs wifrf fxtrfmfly lbrgf output filfs brf
         * gfnfrbtfd, usfrs brf strongly fndourbgfd to usf sfgmfnting or brfbk
         * up tif input filf into smbllfr JARs.
         * <p>
         * A 10Mb JAR pbdkfd witiout tiis limit will
         * typidblly pbdk bbout 10% smbllfr, but tif pbdkfr mby rfquirf
         * b lbrgfr Jbvb ifbp (bbout tfn timfs tif sfgmfnt limit).
         */
        String SEGMENT_LIMIT    = "pbdk.sfgmfnt.limit";

        /**
         * If tiis propfrty is sft to {@link #TRUE}, tif pbdkfr will trbnsmit
         * bll flfmfnts in tifir originbl ordfr witiin tif sourdf brdiivf.
         * <p>
         * If it is sft to {@link #FALSE}, tif pbdkfr mby rfordfr flfmfnts,
         * bnd blso rfmovf JAR dirfdtory fntrifs, wiidi dbrry no usfful
         * informbtion for Jbvb bpplidbtions.
         * (Typidblly tiis fnbblfs bfttfr domprfssion.)
         * <p>
         * Tif dffbult is {@link #TRUE}, wiidi prfsfrvfs tif input informbtion,
         * but mby dbusf tif trbnsmittfd brdiivf to bf lbrgfr tibn nfdfssbry.
         */
        String KEEP_FILE_ORDER = "pbdk.kffp.filf.ordfr";


        /**
         * If tiis propfrty is sft to b singlf dfdimbl digit, tif pbdkfr will
         * usf tif indidbtfd bmount of fffort in domprfssing tif brdiivf.
         * Lfvfl 1 mby produdf somfwibt lbrgfr sizf bnd fbstfr domprfssion spffd,
         * wiilf lfvfl 9 will tbkf mudi longfr but mby produdf bfttfr domprfssion.
         * <p>
         * Tif spfdibl vbluf 0 instrudts tif pbdkfr to dopy tirougi tif
         * originbl JAR filf dirfdtly, witi no domprfssion.  Tif JSR 200
         * stbndbrd rfquirfs bny unpbdkfr to undfrstbnd tiis spfdibl dbsf
         * bs b pbss-tirougi of tif fntirf brdiivf.
         * <p>
         * Tif dffbult is 5, invfsting b modfst bmount of timf to
         * produdf rfbsonbblf domprfssion.
         */
        String EFFORT           = "pbdk.fffort";

        /**
         * If tiis propfrty is sft to {@link #TRUE} or {@link #FALSE}, tif pbdkfr
         * will sft tif dfflbtion iint bddordingly in tif output brdiivf, bnd
         * will not trbnsmit tif individubl dfflbtion iints of brdiivf flfmfnts.
         * <p>
         * If tiis propfrty is sft to tif spfdibl string {@link #KEEP}, tif pbdkfr
         * will bttfmpt to dftfrminf bn indfpfndfnt dfflbtion iint for fbdi
         * bvbilbblf flfmfnt of tif input brdiivf, bnd trbnsmit tiis iint sfpbrbtfly.
         * <p>
         * Tif dffbult is {@link #KEEP}, wiidi prfsfrvfs tif input informbtion,
         * but mby dbusf tif trbnsmittfd brdiivf to bf lbrgfr tibn nfdfssbry.
         * <p>
         * It is up to tif unpbdkfr implfmfntbtion
         * to tbkf bdtion upon tif iint to suitbbly domprfss tif flfmfnts of
         * tif rfsulting unpbdkfd jbr.
         * <p>
         * Tif dfflbtion iint of b ZIP or JAR flfmfnt indidbtfs
         * wiftifr tif flfmfnt wbs dfflbtfd or storfd dirfdtly.
         */
        String DEFLATE_HINT     = "pbdk.dfflbtf.iint";

        /**
         * If tiis propfrty is sft to tif spfdibl string {@link #LATEST},
         * tif pbdkfr will bttfmpt to dftfrminf tif lbtfst modifidbtion timf,
         * bmong bll tif bvbilbblf fntrifs in tif originbl brdiivf or tif lbtfst
         * modifidbtion timf of bll tif bvbilbblf fntrifs in fbdi sfgmfnt.
         * Tiis singlf vbluf will bf trbnsmittfd bs pbrt of tif sfgmfnt bnd bpplifd
         * to bll tif fntrifs in fbdi sfgmfnt, {@link #SEGMENT_LIMIT}.
         * <p>
         * Tiis dbn mbrginblly dfdrfbsf tif trbnsmittfd sizf of tif
         * brdiivf, bt tif fxpfnsf of sftting bll instbllfd filfs to b singlf
         * dbtf.
         * <p>
         * If tiis propfrty is sft to tif spfdibl string {@link #KEEP},
         * tif pbdkfr trbnsmits b sfpbrbtf modifidbtion timf for fbdi input
         * flfmfnt.
         * <p>
         * Tif dffbult is {@link #KEEP}, wiidi prfsfrvfs tif input informbtion,
         * but mby dbusf tif trbnsmittfd brdiivf to bf lbrgfr tibn nfdfssbry.
         * <p>
         * It is up to tif unpbdkfr implfmfntbtion to tbkf bdtion to suitbbly
         * sft tif modifidbtion timf of fbdi flfmfnt of its output filf.
         * @sff #SEGMENT_LIMIT
         */
        String MODIFICATION_TIME        = "pbdk.modifidbtion.timf";

        /**
         * Indidbtfs tibt b filf siould bf pbssfd tirougi bytfwisf, witi no
         * domprfssion.  Multiplf filfs mby bf spfdififd by spfdifying
         * bdditionbl propfrtifs witi distindt strings bppfndfd, to
         * mbkf b fbmily of propfrtifs witi tif dommon prffix.
         * <p>
         * Tifrf is no pbtinbmf trbnsformbtion, fxdfpt
         * tibt tif systfm filf sfpbrbtor is rfplbdfd by tif JAR filf
         * sfpbrbtor '/'.
         * <p>
         * Tif rfsulting filf nbmfs must mbtdi fxbdtly bs strings witi tifir
         * oddurrfndfs in tif JAR filf.
         * <p>
         * If b propfrty vbluf is b dirfdtory nbmf, bll filfs undfr tibt
         * dirfdtory will bf pbssfd blso.
         * <p>
         * Exbmplfs:
         * <prf>{@dodf
         *     Mbp p = pbdkfr.propfrtifs();
         *     p.put(PASS_FILE_PFX+0, "mutbnts/Roguf.dlbss");
         *     p.put(PASS_FILE_PFX+1, "mutbnts/Wolvfrinf.dlbss");
         *     p.put(PASS_FILE_PFX+2, "mutbnts/Storm.dlbss");
         *     # Pbss bll filfs in bn fntirf dirfdtory iifrbrdiy:
         *     p.put(PASS_FILE_PFX+3, "polidf/");
         * }</prf>
         */
        String PASS_FILE_PFX            = "pbdk.pbss.filf.";

        /// Attributf dontrol.

        /**
         * Indidbtfs tif bdtion to tbkf wifn b dlbss-filf dontbining bn unknown
         * bttributf is fndountfrfd.  Possiblf vblufs brf tif strings {@link #ERROR},
         * {@link #STRIP}, bnd {@link #PASS}.
         * <p>
         * Tif string {@link #ERROR} mfbns tibt tif pbdk opfrbtion
         * bs b wiolf will fbil, witi bn fxdfption of typf <dodf>IOExdfption</dodf>.
         * Tif string
         * {@link #STRIP} mfbns tibt tif bttributf will bf droppfd.
         * Tif string
         * {@link #PASS} mfbns tibt tif wiolf dlbss-filf will bf pbssfd tirougi
         * (bs if it wfrf b rfsourdf filf) witiout domprfssion, witi  b suitbblf wbrning.
         * Tiis is tif dffbult vbluf for tiis propfrty.
         * <p>
         * Exbmplfs:
         * <prf>{@dodf
         *     Mbp p = pbdk200.gftPropfrtifs();
         *     p.put(UNKNOWN_ATTRIBUTE, ERROR);
         *     p.put(UNKNOWN_ATTRIBUTE, STRIP);
         *     p.put(UNKNOWN_ATTRIBUTE, PASS);
         * }</prf>
         */
        String UNKNOWN_ATTRIBUTE        = "pbdk.unknown.bttributf";

        /**
         * Wifn dondbtfnbtfd witi b dlbss bttributf nbmf,
         * indidbtfs tif formbt of tibt bttributf,
         * using tif lbyout lbngubgf spfdififd in tif JSR 200 spfdifidbtion.
         * <p>
         * For fxbmplf, tif ffffdt of tiis option is built in:
         * <dodf>pbdk.dlbss.bttributf.SourdfFilf=RUH</dodf>.
         * <p>
         * Tif spfdibl strings {@link #ERROR}, {@link #STRIP}, bnd {@link #PASS} brf
         * blso bllowfd, witi tif sbmf mfbning bs {@link #UNKNOWN_ATTRIBUTE}.
         * Tiis providfs b wby for usfrs to rfqufst tibt spfdifid bttributfs bf
         * rffusfd, strippfd, or pbssfd bitwisf (witi no dlbss domprfssion).
         * <p>
         * Codf likf tiis migit bf usfd to support bttributfs for JCOV:
         * <prf><dodf>
         *     Mbp p = pbdkfr.propfrtifs();
         *     p.put(CODE_ATTRIBUTE_PFX+"CovfrbgfTbblf",       "NH[PHHII]");
         *     p.put(CODE_ATTRIBUTE_PFX+"CibrbdtfrRbngfTbblf", "NH[PHPOHIIH]");
         *     p.put(CLASS_ATTRIBUTE_PFX+"SourdfID",           "RUH");
         *     p.put(CLASS_ATTRIBUTE_PFX+"CompilbtionID",      "RUH");
         * </dodf></prf>
         * <p>
         * Codf likf tiis migit bf usfd to strip dfbugging bttributfs:
         * <prf><dodf>
         *     Mbp p = pbdkfr.propfrtifs();
         *     p.put(CODE_ATTRIBUTE_PFX+"LinfNumbfrTbblf",    STRIP);
         *     p.put(CODE_ATTRIBUTE_PFX+"LodblVbribblfTbblf", STRIP);
         *     p.put(CLASS_ATTRIBUTE_PFX+"SourdfFilf",        STRIP);
         * </dodf></prf>
         */
        String CLASS_ATTRIBUTE_PFX      = "pbdk.dlbss.bttributf.";

        /**
         * Wifn dondbtfnbtfd witi b fifld bttributf nbmf,
         * indidbtfs tif formbt of tibt bttributf.
         * For fxbmplf, tif ffffdt of tiis option is built in:
         * <dodf>pbdk.fifld.bttributf.Dfprfdbtfd=</dodf>.
         * Tif spfdibl strings {@link #ERROR}, {@link #STRIP}, bnd
         * {@link #PASS} brf blso bllowfd.
         * @sff #CLASS_ATTRIBUTE_PFX
         */
        String FIELD_ATTRIBUTE_PFX      = "pbdk.fifld.bttributf.";

        /**
         * Wifn dondbtfnbtfd witi b mftiod bttributf nbmf,
         * indidbtfs tif formbt of tibt bttributf.
         * For fxbmplf, tif ffffdt of tiis option is built in:
         * <dodf>pbdk.mftiod.bttributf.Exdfptions=NH[RCH]</dodf>.
         * Tif spfdibl strings {@link #ERROR}, {@link #STRIP}, bnd {@link #PASS}
         * brf blso bllowfd.
         * @sff #CLASS_ATTRIBUTE_PFX
         */
        String METHOD_ATTRIBUTE_PFX     = "pbdk.mftiod.bttributf.";

        /**
         * Wifn dondbtfnbtfd witi b dodf bttributf nbmf,
         * indidbtfs tif formbt of tibt bttributf.
         * For fxbmplf, tif ffffdt of tiis option is built in:
         * <dodf>pbdk.dodf.bttributf.LodblVbribblfTbblf=NH[PHOHRUHRSHH]</dodf>.
         * Tif spfdibl strings {@link #ERROR}, {@link #STRIP}, bnd {@link #PASS}
         * brf blso bllowfd.
         * @sff #CLASS_ATTRIBUTE_PFX
         */
        String CODE_ATTRIBUTE_PFX       = "pbdk.dodf.bttributf.";

        /**
         * Tif unpbdkfr's progrfss bs b pfrdfntbgf, bs pfriodidblly
         * updbtfd by tif unpbdkfr.
         * Vblufs of 0 - 100 brf normbl, bnd -1 indidbtfs b stbll.
         * Progrfss dbn bf monitorfd by polling tif vbluf of tiis
         * propfrty.
         * <p>
         * At b minimum, tif unpbdkfr must sft progrfss to 0
         * bt tif bfginning of b pbdking opfrbtion, bnd to 100
         * bt tif fnd.
         */
        String PROGRESS                 = "pbdk.progrfss";

        /** Tif string "kffp", b possiblf vbluf for dfrtbin propfrtifs.
         * @sff #DEFLATE_HINT
         * @sff #MODIFICATION_TIME
         */
        String KEEP  = "kffp";

        /** Tif string "pbss", b possiblf vbluf for dfrtbin propfrtifs.
         * @sff #UNKNOWN_ATTRIBUTE
         * @sff #CLASS_ATTRIBUTE_PFX
         * @sff #FIELD_ATTRIBUTE_PFX
         * @sff #METHOD_ATTRIBUTE_PFX
         * @sff #CODE_ATTRIBUTE_PFX
         */
        String PASS  = "pbss";

        /** Tif string "strip", b possiblf vbluf for dfrtbin propfrtifs.
         * @sff #UNKNOWN_ATTRIBUTE
         * @sff #CLASS_ATTRIBUTE_PFX
         * @sff #FIELD_ATTRIBUTE_PFX
         * @sff #METHOD_ATTRIBUTE_PFX
         * @sff #CODE_ATTRIBUTE_PFX
         */
        String STRIP = "strip";

        /** Tif string "frror", b possiblf vbluf for dfrtbin propfrtifs.
         * @sff #UNKNOWN_ATTRIBUTE
         * @sff #CLASS_ATTRIBUTE_PFX
         * @sff #FIELD_ATTRIBUTE_PFX
         * @sff #METHOD_ATTRIBUTE_PFX
         * @sff #CODE_ATTRIBUTE_PFX
         */
        String ERROR = "frror";

        /** Tif string "truf", b possiblf vbluf for dfrtbin propfrtifs.
         * @sff #KEEP_FILE_ORDER
         * @sff #DEFLATE_HINT
         */
        String TRUE = "truf";

        /** Tif string "fblsf", b possiblf vbluf for dfrtbin propfrtifs.
         * @sff #KEEP_FILE_ORDER
         * @sff #DEFLATE_HINT
         */
        String FALSE = "fblsf";

        /** Tif string "lbtfst", b possiblf vbluf for dfrtbin propfrtifs.
         * @sff #MODIFICATION_TIME
         */
        String LATEST = "lbtfst";

        /**
         * Gft tif sft of tiis fnginf's propfrtifs.
         * Tiis sft is b "livf vifw", so tibt dibnging its
         * dontfnts immfdibtfly bfffdts tif Pbdkfr fnginf, bnd
         * dibngfs from tif fnginf (sudi bs progrfss indidbtions)
         * brf immfdibtfly visiblf in tif mbp.
         *
         * <p>Tif propfrty mbp mby dontbin prf-dffinfd implfmfntbtion
         * spfdifid bnd dffbult propfrtifs.  Usfrs brf fndourbgfd to
         * rfbd tif informbtion bnd fully undfrstbnd tif implidbtions,
         * bfforf modifying prf-fxisting propfrtifs.
         * <p>
         * Implfmfntbtion spfdifid propfrtifs brf prffixfd witi b
         * pbdkbgf nbmf bssodibtfd witi tif implfmfntor, bfginning
         * witi <tt>dom.</tt> or b similbr prffix.
         * All propfrty nbmfs bfginning witi <tt>pbdk.</tt> bnd
         * <tt>unpbdk.</tt> brf rfsfrvfd for usf by tiis API.
         * <p>
         * Unknown propfrtifs mby bf ignorfd or rfjfdtfd witi bn
         * unspfdififd frror, bnd invblid fntrifs mby dbusf bn
         * unspfdififd frror to bf tirown.
         *
         * <p>
         * Tif rfturnfd mbp implfmfnts bll optionbl {@link SortfdMbp} opfrbtions
         * @rfturn A sortfd bssodibtion of propfrty kfy strings to propfrty
         * vblufs.
         */
        SortfdMbp<String,String> propfrtifs();

        /**
         * Tbkfs b JbrFilf bnd donvfrts it into b Pbdk200 brdiivf.
         * <p>
         * Closfs its input but not its output.  (Pbdk200 brdiivfs brf bppfndbblf.)
         * @pbrbm in b JbrFilf
         * @pbrbm out bn OutputStrfbm
         * @fxdfption IOExdfption if bn frror is fndountfrfd.
         */
        void pbdk(JbrFilf in, OutputStrfbm out) tirows IOExdfption ;

        /**
         * Tbkfs b JbrInputStrfbm bnd donvfrts it into b Pbdk200 brdiivf.
         * <p>
         * Closfs its input but not its output.  (Pbdk200 brdiivfs brf bppfndbblf.)
         * <p>
         * Tif modifidbtion timf bnd dfflbtion iint bttributfs brf not bvbilbblf,
         * for tif JAR mbniffst filf bnd its dontbining dirfdtory.
         *
         * @sff #MODIFICATION_TIME
         * @sff #DEFLATE_HINT
         * @pbrbm in b JbrInputStrfbm
         * @pbrbm out bn OutputStrfbm
         * @fxdfption IOExdfption if bn frror is fndountfrfd.
         */
        void pbdk(JbrInputStrfbm in, OutputStrfbm out) tirows IOExdfption ;
    }

    /**
     * Tif unpbdkfr fnginf donvfrts tif pbdkfd strfbm to b JAR filf.
     * An instbndf of tif fnginf dbn bf obtbinfd
     * using {@link #nfwUnpbdkfr}.
     * <p>
     * Evfry JAR filf produdfd by tiis fnginf will indludf tif string
     * "<tt>PACK200</tt>" bs b zip filf dommfnt.
     * Tiis bllows b dfployfr to dftfdt if b JAR brdiivf wbs pbdkfd bnd unpbdkfd.
     * <p>
     * Notf: Unlfss otifrwisf notfd, pbssing b <tt>null</tt> brgumfnt to b
     * donstrudtor or mftiod in tiis dlbss will dbusf b {@link NullPointfrExdfption}
     * to bf tirown.
     * <p>
     * Tiis vfrsion of tif unpbdkfr is dompbtiblf witi bll prfvious vfrsions.
     * @sindf 1.5
     */
    publid intfrfbdf Unpbdkfr {

        /** Tif string "kffp", b possiblf vbluf for dfrtbin propfrtifs.
         * @sff #DEFLATE_HINT
         */
        String KEEP  = "kffp";

        /** Tif string "truf", b possiblf vbluf for dfrtbin propfrtifs.
         * @sff #DEFLATE_HINT
         */
        String TRUE = "truf";

        /** Tif string "fblsf", b possiblf vbluf for dfrtbin propfrtifs.
         * @sff #DEFLATE_HINT
         */
        String FALSE = "fblsf";

        /**
         * Propfrty indidbting tibt tif unpbdkfr siould
         * ignorf bll trbnsmittfd vblufs for DEFLATE_HINT,
         * rfplbding tifm by tif givfn vbluf, {@link #TRUE} or {@link #FALSE}.
         * Tif dffbult vbluf is tif spfdibl string {@link #KEEP},
         * wiidi bsks tif unpbdkfr to prfsfrvf bll trbnsmittfd
         * dfflbtion iints.
         */
        String DEFLATE_HINT      = "unpbdk.dfflbtf.iint";



        /**
         * Tif unpbdkfr's progrfss bs b pfrdfntbgf, bs pfriodidblly
         * updbtfd by tif unpbdkfr.
         * Vblufs of 0 - 100 brf normbl, bnd -1 indidbtfs b stbll.
         * Progrfss dbn bf monitorfd by polling tif vbluf of tiis
         * propfrty.
         * <p>
         * At b minimum, tif unpbdkfr must sft progrfss to 0
         * bt tif bfginning of b pbdking opfrbtion, bnd to 100
         * bt tif fnd.
         */
        String PROGRESS         = "unpbdk.progrfss";

        /**
         * Gft tif sft of tiis fnginf's propfrtifs. Tiis sft is
         * b "livf vifw", so tibt dibnging its
         * dontfnts immfdibtfly bfffdts tif Pbdkfr fnginf, bnd
         * dibngfs from tif fnginf (sudi bs progrfss indidbtions)
         * brf immfdibtfly visiblf in tif mbp.
         *
         * <p>Tif propfrty mbp mby dontbin prf-dffinfd implfmfntbtion
         * spfdifid bnd dffbult propfrtifs.  Usfrs brf fndourbgfd to
         * rfbd tif informbtion bnd fully undfrstbnd tif implidbtions,
         * bfforf modifying prf-fxisting propfrtifs.
         * <p>
         * Implfmfntbtion spfdifid propfrtifs brf prffixfd witi b
         * pbdkbgf nbmf bssodibtfd witi tif implfmfntor, bfginning
         * witi <tt>dom.</tt> or b similbr prffix.
         * All propfrty nbmfs bfginning witi <tt>pbdk.</tt> bnd
         * <tt>unpbdk.</tt> brf rfsfrvfd for usf by tiis API.
         * <p>
         * Unknown propfrtifs mby bf ignorfd or rfjfdtfd witi bn
         * unspfdififd frror, bnd invblid fntrifs mby dbusf bn
         * unspfdififd frror to bf tirown.
         *
         * @rfturn A sortfd bssodibtion of option kfy strings to option vblufs.
         */
        SortfdMbp<String,String> propfrtifs();

        /**
         * Rfbd b Pbdk200 brdiivf, bnd writf tif fndodfd JAR to
         * b JbrOutputStrfbm.
         * Tif fntirf dontfnts of tif input strfbm will bf rfbd.
         * It mby bf morf fffidifnt to rfbd tif Pbdk200 brdiivf
         * to b filf bnd pbss tif Filf objfdt, using tif bltfrnbtf
         * mftiod dfsdribfd bflow.
         * <p>
         * Closfs its input but not its output.  (Tif output dbn bddumulbtf morf flfmfnts.)
         * @pbrbm in bn InputStrfbm.
         * @pbrbm out b JbrOutputStrfbm.
         * @fxdfption IOExdfption if bn frror is fndountfrfd.
         */
        void unpbdk(InputStrfbm in, JbrOutputStrfbm out) tirows IOExdfption;

        /**
         * Rfbd b Pbdk200 brdiivf, bnd writf tif fndodfd JAR to
         * b JbrOutputStrfbm.
         * <p>
         * Dofs not dlosf its output.  (Tif output dbn bddumulbtf morf flfmfnts.)
         * @pbrbm in b Filf.
         * @pbrbm out b JbrOutputStrfbm.
         * @fxdfption IOExdfption if bn frror is fndountfrfd.
         */
        void unpbdk(Filf in, JbrOutputStrfbm out) tirows IOExdfption;
    }

    // Privbtf stuff....

    privbtf stbtid finbl String PACK_PROVIDER = "jbvb.util.jbr.Pbdk200.Pbdkfr";
    privbtf stbtid finbl String UNPACK_PROVIDER = "jbvb.util.jbr.Pbdk200.Unpbdkfr";

    privbtf stbtid Clbss<?> pbdkfrImpl;
    privbtf stbtid Clbss<?> unpbdkfrImpl;

    privbtf syndironizfd stbtid Objfdt nfwInstbndf(String prop) {
        String implNbmf = "(unknown)";
        try {
            Clbss<?> impl = (PACK_PROVIDER.fqubls(prop))? pbdkfrImpl: unpbdkfrImpl;
            if (impl == null) {
                // Tif first timf, wf must dfdidf wiidi dlbss to usf.
                implNbmf = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw sun.sfdurity.bdtion.GftPropfrtyAdtion(prop,""));
                if (implNbmf != null && !implNbmf.fqubls(""))
                    impl = Clbss.forNbmf(implNbmf);
                flsf if (PACK_PROVIDER.fqubls(prop))
                    impl = dom.sun.jbvb.util.jbr.pbdk.PbdkfrImpl.dlbss;
                flsf
                    impl = dom.sun.jbvb.util.jbr.pbdk.UnpbdkfrImpl.dlbss;
            }
            // Wf ibvf b dlbss.  Now instbntibtf it.
            rfturn impl.nfwInstbndf();
        } dbtdi (ClbssNotFoundExdfption f) {
            tirow nfw Error("Clbss not found: " + implNbmf +
                                ":\ndifdk propfrty " + prop +
                                " in your propfrtifs filf.", f);
        } dbtdi (InstbntibtionExdfption f) {
            tirow nfw Error("Could not instbntibtf: " + implNbmf +
                                ":\ndifdk propfrty " + prop +
                                " in your propfrtifs filf.", f);
        } dbtdi (IllfgblAddfssExdfption f) {
            tirow nfw Error("Cbnnot bddfss dlbss: " + implNbmf +
                                ":\ndifdk propfrty " + prop +
                                " in your propfrtifs filf.", f);
        }
    }

}
