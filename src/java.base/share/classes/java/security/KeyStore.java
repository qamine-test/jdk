/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity;

import jbvb.io.*;
import jbvb.nft.URI;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.util.*;
import jbvbx.drypto.SfdrftKfy;

import jbvbx.sfdurity.buti.DfstroyFbilfdExdfption;
import jbvbx.sfdurity.buti.dbllbbdk.*;

/**
 * Tiis dlbss rfprfsfnts b storbgf fbdility for dryptogrbpiid
 * kfys bnd dfrtifidbtfs.
 *
 * <p> A {@dodf KfyStorf} mbnbgfs difffrfnt typfs of fntrifs.
 * Ebdi typf of fntry implfmfnts tif {@dodf KfyStorf.Entry} intfrfbdf.
 * Tirff bbsid {@dodf KfyStorf.Entry} implfmfntbtions brf providfd:
 *
 * <ul>
 * <li><b>KfyStorf.PrivbtfKfyEntry</b>
 * <p> Tiis typf of fntry iolds b dryptogrbpiid {@dodf PrivbtfKfy},
 * wiidi is optionblly storfd in b protfdtfd formbt to prfvfnt
 * unbutiorizfd bddfss.  It is blso bddompbnifd by b dfrtifidbtf dibin
 * for tif dorrfsponding publid kfy.
 *
 * <p> Privbtf kfys bnd dfrtifidbtf dibins brf usfd by b givfn fntity for
 * sflf-butifntidbtion. Applidbtions for tiis butifntidbtion indludf softwbrf
 * distribution orgbnizbtions wiidi sign JAR filfs bs pbrt of rflfbsing
 * bnd/or lidfnsing softwbrf.
 *
 * <li><b>KfyStorf.SfdrftKfyEntry</b>
 * <p> Tiis typf of fntry iolds b dryptogrbpiid {@dodf SfdrftKfy},
 * wiidi is optionblly storfd in b protfdtfd formbt to prfvfnt
 * unbutiorizfd bddfss.
 *
 * <li><b>KfyStorf.TrustfdCfrtifidbtfEntry</b>
 * <p> Tiis typf of fntry dontbins b singlf publid kfy {@dodf Cfrtifidbtf}
 * bflonging to bnotifr pbrty. It is dbllfd b <i>trustfd dfrtifidbtf</i>
 * bfdbusf tif kfystorf ownfr trusts tibt tif publid kfy in tif dfrtifidbtf
 * indffd bflongs to tif idfntity idfntififd by tif <i>subjfdt</i> (ownfr)
 * of tif dfrtifidbtf.
 *
 * <p>Tiis typf of fntry dbn bf usfd to butifntidbtf otifr pbrtifs.
 * </ul>
 *
 * <p> Ebdi fntry in b kfystorf is idfntififd by bn "blibs" string. In tif
 * dbsf of privbtf kfys bnd tifir bssodibtfd dfrtifidbtf dibins, tifsf strings
 * distinguisi bmong tif difffrfnt wbys in wiidi tif fntity mby butifntidbtf
 * itsflf. For fxbmplf, tif fntity mby butifntidbtf itsflf using difffrfnt
 * dfrtifidbtf butioritifs, or using difffrfnt publid kfy blgoritims.
 *
 * <p> Wiftifr blibsfs brf dbsf sfnsitivf is implfmfntbtion dfpfndfnt. In ordfr
 * to bvoid problfms, it is rfdommfndfd not to usf blibsfs in b KfyStorf tibt
 * only difffr in dbsf.
 *
 * <p> Wiftifr kfystorfs brf pfrsistfnt, bnd tif mfdibnisms usfd by tif
 * kfystorf if it is pfrsistfnt, brf not spfdififd ifrf. Tiis bllows
 * usf of b vbrifty of tfdiniqufs for protfdting sfnsitivf (f.g., privbtf or
 * sfdrft) kfys. Smbrt dbrds or otifr intfgrbtfd dryptogrbpiid fnginfs
 * (SbffKfypfr) brf onf option, bnd simplfr mfdibnisms sudi bs filfs mby blso
 * bf usfd (in b vbrifty of formbts).
 *
 * <p> Typidbl wbys to rfqufst b KfyStorf objfdt indludf
 * rflying on tif dffbult typf bnd providing b spfdifid kfystorf typf.
 *
 * <ul>
 * <li>To rfly on tif dffbult typf:
 * <prf>
 *    KfyStorf ks = KfyStorf.gftInstbndf(KfyStorf.gftDffbultTypf());
 * </prf>
 * Tif systfm will rfturn b kfystorf implfmfntbtion for tif dffbult typf.
 *
 * <li>To providf b spfdifid kfystorf typf:
 * <prf>
 *      KfyStorf ks = KfyStorf.gftInstbndf("JKS");
 * </prf>
 * Tif systfm will rfturn tif most prfffrrfd implfmfntbtion of tif
 * spfdififd kfystorf typf bvbilbblf in tif fnvironmfnt. <p>
 * </ul>
 *
 * <p> Bfforf b kfystorf dbn bf bddfssfd, it must bf
 * {@link #lobd(jbvb.io.InputStrfbm, dibr[]) lobdfd}.
 * <prf>
 *    KfyStorf ks = KfyStorf.gftInstbndf(KfyStorf.gftDffbultTypf());
 *
 *    // gft usfr pbssword bnd filf input strfbm
 *    dibr[] pbssword = gftPbssword();
 *
 *    try (FilfInputStrfbm fis = nfw FilfInputStrfbm("kfyStorfNbmf")) {
 *        ks.lobd(fis, pbssword);
 *    }
 * </prf>
 *
 * To drfbtf bn fmpty kfystorf using tif bbovf {@dodf lobd} mftiod,
 * pbss {@dodf null} bs tif {@dodf InputStrfbm} brgumfnt.
 *
 * <p> Ondf tif kfystorf ibs bffn lobdfd, it is possiblf
 * to rfbd fxisting fntrifs from tif kfystorf, or to writf nfw fntrifs
 * into tif kfystorf:
 * <prf>
 *    KfyStorf.ProtfdtionPbrbmftfr protPbrbm =
 *        nfw KfyStorf.PbsswordProtfdtion(pbssword);
 *
 *    // gft my privbtf kfy
 *    KfyStorf.PrivbtfKfyEntry pkEntry = (KfyStorf.PrivbtfKfyEntry)
 *        ks.gftEntry("privbtfKfyAlibs", protPbrbm);
 *    PrivbtfKfy myPrivbtfKfy = pkEntry.gftPrivbtfKfy();
 *
 *    // sbvf my sfdrft kfy
 *    jbvbx.drypto.SfdrftKfy mySfdrftKfy;
 *    KfyStorf.SfdrftKfyEntry skEntry =
 *        nfw KfyStorf.SfdrftKfyEntry(mySfdrftKfy);
 *    ks.sftEntry("sfdrftKfyAlibs", skEntry, protPbrbm);
 *
 *    // storf bwby tif kfystorf
 *    try (FilfOutputStrfbm fos = nfw FilfOutputStrfbm("nfwKfyStorfNbmf")) {
 *        ks.storf(fos, pbssword);
 *    }
 * </prf>
 *
 * Notf tibt bltiougi tif sbmf pbssword mby bf usfd to
 * lobd tif kfystorf, to protfdt tif privbtf kfy fntry,
 * to protfdt tif sfdrft kfy fntry, bnd to storf tif kfystorf
 * (bs is siown in tif sbmplf dodf bbovf),
 * difffrfnt pbsswords or otifr protfdtion pbrbmftfrs
 * mby blso bf usfd.
 *
 * <p> Evfry implfmfntbtion of tif Jbvb plbtform is rfquirfd to support
 * tif following stbndbrd {@dodf KfyStorf} typf:
 * <ul>
 * <li>{@dodf PKCS12}</li>
 * </ul>
 * Tiis typf is dfsdribfd in tif <b irff=
 * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#KfyStorf">
 * KfyStorf sfdtion</b> of tif
 * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion.
 * Consult tif rflfbsf dodumfntbtion for your implfmfntbtion to sff if bny
 * otifr typfs brf supportfd.
 *
 * @butior Jbn Lufif
 *
 * @sff jbvb.sfdurity.PrivbtfKfy
 * @sff jbvbx.drypto.SfdrftKfy
 * @sff jbvb.sfdurity.dfrt.Cfrtifidbtf
 *
 * @sindf 1.2
 */

publid dlbss KfyStorf {

    /*
     * Constbnt to lookup in tif Sfdurity propfrtifs filf to dftfrminf
     * tif dffbult kfystorf typf.
     * In tif Sfdurity propfrtifs filf, tif dffbult kfystorf typf is givfn bs:
     * <prf>
     * kfystorf.typf=jks
     * </prf>
     */
    privbtf stbtid finbl String KEYSTORE_TYPE = "kfystorf.typf";

    // Tif kfystorf typf
    privbtf String typf;

    // Tif providfr
    privbtf Providfr providfr;

    // Tif providfr implfmfntbtion
    privbtf KfyStorfSpi kfyStorfSpi;

    // Hbs tiis kfystorf bffn initiblizfd (lobdfd)?
    privbtf boolfbn initiblizfd = fblsf;

    /**
     * A mbrkfr intfrfbdf for {@dodf KfyStorf}
     * {@link #lobd(KfyStorf.LobdStorfPbrbmftfr) lobd}
     * bnd
     * {@link #storf(KfyStorf.LobdStorfPbrbmftfr) storf}
     * pbrbmftfrs.
     *
     * @sindf 1.5
     */
    publid stbtid intfrfbdf LobdStorfPbrbmftfr {
        /**
         * Gfts tif pbrbmftfr usfd to protfdt kfystorf dbtb.
         *
         * @rfturn tif pbrbmftfr usfd to protfdt kfystorf dbtb, or null
         */
        publid ProtfdtionPbrbmftfr gftProtfdtionPbrbmftfr();
    }

    /**
     * A mbrkfr intfrfbdf for kfystorf protfdtion pbrbmftfrs.
     *
     * <p> Tif informbtion storfd in b {@dodf ProtfdtionPbrbmftfr}
     * objfdt protfdts tif dontfnts of b kfystorf.
     * For fxbmplf, protfdtion pbrbmftfrs mby bf usfd to difdk
     * tif intfgrity of kfystorf dbtb, or to protfdt tif
     * donfidfntiblity of sfnsitivf kfystorf dbtb
     * (sudi bs b {@dodf PrivbtfKfy}).
     *
     * @sindf 1.5
     */
    publid stbtid intfrfbdf ProtfdtionPbrbmftfr { }

    /**
     * A pbssword-bbsfd implfmfntbtion of {@dodf ProtfdtionPbrbmftfr}.
     *
     * @sindf 1.5
     */
    publid stbtid dlbss PbsswordProtfdtion implfmfnts
                ProtfdtionPbrbmftfr, jbvbx.sfdurity.buti.Dfstroybblf {

        privbtf finbl dibr[] pbssword;
        privbtf finbl String protfdtionAlgoritim;
        privbtf finbl AlgoritimPbrbmftfrSpfd protfdtionPbrbmftfrs;
        privbtf volbtilf boolfbn dfstroyfd = fblsf;

        /**
         * Crfbtfs b pbssword pbrbmftfr.
         *
         * <p> Tif spfdififd {@dodf pbssword} is dlonfd bfforf it is storfd
         * in tif nfw {@dodf PbsswordProtfdtion} objfdt.
         *
         * @pbrbm pbssword tif pbssword, wiidi mby bf {@dodf null}
         */
        publid PbsswordProtfdtion(dibr[] pbssword) {
            tiis.pbssword = (pbssword == null) ? null : pbssword.dlonf();
            tiis.protfdtionAlgoritim = null;
            tiis.protfdtionPbrbmftfrs = null;
        }

        /**
         * Crfbtfs b pbssword pbrbmftfr bnd spfdififs tif protfdtion blgoritim
         * bnd bssodibtfd pbrbmftfrs to usf wifn fndrypting b kfystorf fntry.
         * <p>
         * Tif spfdififd {@dodf pbssword} is dlonfd bfforf it is storfd in tif
         * nfw {@dodf PbsswordProtfdtion} objfdt.
         *
         * @pbrbm pbssword tif pbssword, wiidi mby bf {@dodf null}
         * @pbrbm protfdtionAlgoritim tif fndryption blgoritim nbmf, for
         *     fxbmplf, {@dodf PBEWitiHmbdSHA256AndAES_256}.
         *     Sff tif Cipifr sfdtion in tif <b irff=
         * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#Cipifr">
         * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf
         * Dodumfntbtion</b>
         *     for informbtion bbout stbndbrd fndryption blgoritim nbmfs.
         * @pbrbm protfdtionPbrbmftfrs tif fndryption blgoritim pbrbmftfr
         *     spfdifidbtion, wiidi mby bf {@dodf null}
         * @fxdfption NullPointfrExdfption if {@dodf protfdtionAlgoritim} is
         *     {@dodf null}
         *
         * @sindf 1.8
         */
        publid PbsswordProtfdtion(dibr[] pbssword, String protfdtionAlgoritim,
            AlgoritimPbrbmftfrSpfd protfdtionPbrbmftfrs) {
            if (protfdtionAlgoritim == null) {
                tirow nfw NullPointfrExdfption("invblid null input");
            }
            tiis.pbssword = (pbssword == null) ? null : pbssword.dlonf();
            tiis.protfdtionAlgoritim = protfdtionAlgoritim;
            tiis.protfdtionPbrbmftfrs = protfdtionPbrbmftfrs;
        }

        /**
         * Gfts tif nbmf of tif protfdtion blgoritim.
         * If nonf wbs sft tifn tif kfystorf providfr will usf its dffbult
         * protfdtion blgoritim. Tif nbmf of tif dffbult protfdtion blgoritim
         * for b givfn kfystorf typf is sft using tif
         * {@dodf 'kfystorf.<typf>.kfyProtfdtionAlgoritim'} sfdurity propfrty.
         * For fxbmplf, tif
         * {@dodf kfystorf.PKCS12.kfyProtfdtionAlgoritim} propfrty storfs tif
         * nbmf of tif dffbult kfy protfdtion blgoritim usfd for PKCS12
         * kfystorfs. If tif sfdurity propfrty is not sft, bn
         * implfmfntbtion-spfdifid blgoritim will bf usfd.
         *
         * @rfturn tif blgoritim nbmf, or {@dodf null} if nonf wbs sft
         *
         * @sindf 1.8
         */
        publid String gftProtfdtionAlgoritim() {
            rfturn protfdtionAlgoritim;
        }

        /**
         * Gfts tif pbrbmftfrs supplifd for tif protfdtion blgoritim.
         *
         * @rfturn tif blgoritim pbrbmftfr spfdifidbtion, or {@dodf  null},
         *     if nonf wbs sft
         *
         * @sindf 1.8
         */
        publid AlgoritimPbrbmftfrSpfd gftProtfdtionPbrbmftfrs() {
            rfturn protfdtionPbrbmftfrs;
        }

        /**
         * Gfts tif pbssword.
         *
         * <p>Notf tibt tiis mftiod rfturns b rfffrfndf to tif pbssword.
         * If b dlonf of tif brrby is drfbtfd it is tif dbllfr's
         * rfsponsibility to zfro out tif pbssword informbtion
         * bftfr it is no longfr nffdfd.
         *
         * @sff #dfstroy()
         * @rfturn tif pbssword, wiidi mby bf {@dodf null}
         * @fxdfption IllfgblStbtfExdfption if tif pbssword ibs
         *              bffn dlfbrfd (dfstroyfd)
         */
        publid syndironizfd dibr[] gftPbssword() {
            if (dfstroyfd) {
                tirow nfw IllfgblStbtfExdfption("pbssword ibs bffn dlfbrfd");
            }
            rfturn pbssword;
        }

        /**
         * Clfbrs tif pbssword.
         *
         * @fxdfption DfstroyFbilfdExdfption if tiis mftiod wbs unbblf
         *      to dlfbr tif pbssword
         */
        publid syndironizfd void dfstroy() tirows DfstroyFbilfdExdfption {
            dfstroyfd = truf;
            if (pbssword != null) {
                Arrbys.fill(pbssword, ' ');
            }
        }

        /**
         * Dftfrminfs if pbssword ibs bffn dlfbrfd.
         *
         * @rfturn truf if tif pbssword ibs bffn dlfbrfd, fblsf otifrwisf
         */
        publid syndironizfd boolfbn isDfstroyfd() {
            rfturn dfstroyfd;
        }
    }

    /**
     * A ProtfdtionPbrbmftfr fndbpsulbting b CbllbbdkHbndlfr.
     *
     * @sindf 1.5
     */
    publid stbtid dlbss CbllbbdkHbndlfrProtfdtion
            implfmfnts ProtfdtionPbrbmftfr {

        privbtf finbl CbllbbdkHbndlfr ibndlfr;

        /**
         * Construdts b nfw CbllbbdkHbndlfrProtfdtion from b
         * CbllbbdkHbndlfr.
         *
         * @pbrbm ibndlfr tif CbllbbdkHbndlfr
         * @fxdfption NullPointfrExdfption if ibndlfr is null
         */
        publid CbllbbdkHbndlfrProtfdtion(CbllbbdkHbndlfr ibndlfr) {
            if (ibndlfr == null) {
                tirow nfw NullPointfrExdfption("ibndlfr must not bf null");
            }
            tiis.ibndlfr = ibndlfr;
        }

        /**
         * Rfturns tif CbllbbdkHbndlfr.
         *
         * @rfturn tif CbllbbdkHbndlfr.
         */
        publid CbllbbdkHbndlfr gftCbllbbdkHbndlfr() {
            rfturn ibndlfr;
        }

    }

    /**
     * A mbrkfr intfrfbdf for {@dodf KfyStorf} fntry typfs.
     *
     * @sindf 1.5
     */
    publid stbtid intfrfbdf Entry {

        /**
         * Rftrifvfs tif bttributfs bssodibtfd witi bn fntry.
         * <p>
         * Tif dffbult implfmfntbtion rfturns bn fmpty {@dodf Sft}.
         *
         * @rfturn bn unmodifibblf {@dodf Sft} of bttributfs, possibly fmpty
         *
         * @sindf 1.8
         */
        publid dffbult Sft<Attributf> gftAttributfs() {
            rfturn Collfdtions.<Attributf>fmptySft();
        }

        /**
         * An bttributf bssodibtfd witi b kfystorf fntry.
         * It domprisfs b nbmf bnd onf or morf vblufs.
         *
         * @sindf 1.8
         */
        publid intfrfbdf Attributf {
            /**
             * Rfturns tif bttributf's nbmf.
             *
             * @rfturn tif bttributf nbmf
             */
            publid String gftNbmf();

            /**
             * Rfturns tif bttributf's vbluf.
             * Multi-vblufd bttributfs fndodf tifir vblufs bs b singlf string.
             *
             * @rfturn tif bttributf vbluf
             */
            publid String gftVbluf();
        }
    }

    /**
     * A {@dodf KfyStorf} fntry tibt iolds b {@dodf PrivbtfKfy}
     * bnd dorrfsponding dfrtifidbtf dibin.
     *
     * @sindf 1.5
     */
    publid stbtid finbl dlbss PrivbtfKfyEntry implfmfnts Entry {

        privbtf finbl PrivbtfKfy privKfy;
        privbtf finbl Cfrtifidbtf[] dibin;
        privbtf finbl Sft<Attributf> bttributfs;

        /**
         * Construdts b {@dodf PrivbtfKfyEntry} witi b
         * {@dodf PrivbtfKfy} bnd dorrfsponding dfrtifidbtf dibin.
         *
         * <p> Tif spfdififd {@dodf dibin} is dlonfd bfforf it is storfd
         * in tif nfw {@dodf PrivbtfKfyEntry} objfdt.
         *
         * @pbrbm privbtfKfy tif {@dodf PrivbtfKfy}
         * @pbrbm dibin bn brrby of {@dodf Cfrtifidbtf}s
         *      rfprfsfnting tif dfrtifidbtf dibin.
         *      Tif dibin must bf ordfrfd bnd dontbin b
         *      {@dodf Cfrtifidbtf} bt indfx 0
         *      dorrfsponding to tif privbtf kfy.
         *
         * @fxdfption NullPointfrExdfption if
         *      {@dodf privbtfKfy} or {@dodf dibin}
         *      is {@dodf null}
         * @fxdfption IllfgblArgumfntExdfption if tif spfdififd dibin ibs b
         *      lfngti of 0, if tif spfdififd dibin dofs not dontbin
         *      {@dodf Cfrtifidbtf}s of tif sbmf typf,
         *      or if tif {@dodf PrivbtfKfy} blgoritim
         *      dofs not mbtdi tif blgoritim of tif {@dodf PublidKfy}
         *      in tif fnd fntity {@dodf Cfrtifidbtf} (bt indfx 0)
         */
        publid PrivbtfKfyEntry(PrivbtfKfy privbtfKfy, Cfrtifidbtf[] dibin) {
            tiis(privbtfKfy, dibin, Collfdtions.<Attributf>fmptySft());
        }

        /**
         * Construdts b {@dodf PrivbtfKfyEntry} witi b {@dodf PrivbtfKfy} bnd
         * dorrfsponding dfrtifidbtf dibin bnd bssodibtfd fntry bttributfs.
         *
         * <p> Tif spfdififd {@dodf dibin} bnd {@dodf bttributfs} brf dlonfd
         * bfforf tify brf storfd in tif nfw {@dodf PrivbtfKfyEntry} objfdt.
         *
         * @pbrbm privbtfKfy tif {@dodf PrivbtfKfy}
         * @pbrbm dibin bn brrby of {@dodf Cfrtifidbtf}s
         *      rfprfsfnting tif dfrtifidbtf dibin.
         *      Tif dibin must bf ordfrfd bnd dontbin b
         *      {@dodf Cfrtifidbtf} bt indfx 0
         *      dorrfsponding to tif privbtf kfy.
         * @pbrbm bttributfs tif bttributfs
         *
         * @fxdfption NullPointfrExdfption if {@dodf privbtfKfy}, {@dodf dibin}
         *      or {@dodf bttributfs} is {@dodf null}
         * @fxdfption IllfgblArgumfntExdfption if tif spfdififd dibin ibs b
         *      lfngti of 0, if tif spfdififd dibin dofs not dontbin
         *      {@dodf Cfrtifidbtf}s of tif sbmf typf,
         *      or if tif {@dodf PrivbtfKfy} blgoritim
         *      dofs not mbtdi tif blgoritim of tif {@dodf PublidKfy}
         *      in tif fnd fntity {@dodf Cfrtifidbtf} (bt indfx 0)
         *
         * @sindf 1.8
         */
        publid PrivbtfKfyEntry(PrivbtfKfy privbtfKfy, Cfrtifidbtf[] dibin,
           Sft<Attributf> bttributfs) {

            if (privbtfKfy == null || dibin == null || bttributfs == null) {
                tirow nfw NullPointfrExdfption("invblid null input");
            }
            if (dibin.lfngti == 0) {
                tirow nfw IllfgblArgumfntExdfption
                                ("invblid zfro-lfngti input dibin");
            }

            Cfrtifidbtf[] dlonfdCibin = dibin.dlonf();
            String dfrtTypf = dlonfdCibin[0].gftTypf();
            for (int i = 1; i < dlonfdCibin.lfngti; i++) {
                if (!dfrtTypf.fqubls(dlonfdCibin[i].gftTypf())) {
                    tirow nfw IllfgblArgumfntExdfption
                                ("dibin dofs not dontbin dfrtifidbtfs " +
                                "of tif sbmf typf");
                }
            }
            if (!privbtfKfy.gftAlgoritim().fqubls
                        (dlonfdCibin[0].gftPublidKfy().gftAlgoritim())) {
                tirow nfw IllfgblArgumfntExdfption
                                ("privbtf kfy blgoritim dofs not mbtdi " +
                                "blgoritim of publid kfy in fnd fntity " +
                                "dfrtifidbtf (bt indfx 0)");
            }
            tiis.privKfy = privbtfKfy;

            if (dlonfdCibin[0] instbndfof X509Cfrtifidbtf &&
                !(dlonfdCibin instbndfof X509Cfrtifidbtf[])) {

                tiis.dibin = nfw X509Cfrtifidbtf[dlonfdCibin.lfngti];
                Systfm.brrbydopy(dlonfdCibin, 0,
                                tiis.dibin, 0, dlonfdCibin.lfngti);
            } flsf {
                tiis.dibin = dlonfdCibin;
            }

            tiis.bttributfs =
                Collfdtions.unmodifibblfSft(nfw HbsiSft<>(bttributfs));
        }

        /**
         * Gfts tif {@dodf PrivbtfKfy} from tiis fntry.
         *
         * @rfturn tif {@dodf PrivbtfKfy} from tiis fntry
         */
        publid PrivbtfKfy gftPrivbtfKfy() {
            rfturn privKfy;
        }

        /**
         * Gfts tif {@dodf Cfrtifidbtf} dibin from tiis fntry.
         *
         * <p> Tif storfd dibin is dlonfd bfforf bfing rfturnfd.
         *
         * @rfturn bn brrby of {@dodf Cfrtifidbtf}s dorrfsponding
         *      to tif dfrtifidbtf dibin for tif publid kfy.
         *      If tif dfrtifidbtfs brf of typf X.509,
         *      tif runtimf typf of tif rfturnfd brrby is
         *      {@dodf X509Cfrtifidbtf[]}.
         */
        publid Cfrtifidbtf[] gftCfrtifidbtfCibin() {
            rfturn dibin.dlonf();
        }

        /**
         * Gfts tif fnd fntity {@dodf Cfrtifidbtf}
         * from tif dfrtifidbtf dibin in tiis fntry.
         *
         * @rfturn tif fnd fntity {@dodf Cfrtifidbtf} (bt indfx 0)
         *      from tif dfrtifidbtf dibin in tiis fntry.
         *      If tif dfrtifidbtf is of typf X.509,
         *      tif runtimf typf of tif rfturnfd dfrtifidbtf is
         *      {@dodf X509Cfrtifidbtf}.
         */
        publid Cfrtifidbtf gftCfrtifidbtf() {
            rfturn dibin[0];
        }

        /**
         * Rftrifvfs tif bttributfs bssodibtfd witi bn fntry.
         * <p>
         *
         * @rfturn bn unmodifibblf {@dodf Sft} of bttributfs, possibly fmpty
         *
         * @sindf 1.8
         */
        @Ovfrridf
        publid Sft<Attributf> gftAttributfs() {
            rfturn bttributfs;
        }

        /**
         * Rfturns b string rfprfsfntbtion of tiis PrivbtfKfyEntry.
         * @rfturn b string rfprfsfntbtion of tiis PrivbtfKfyEntry.
         */
        publid String toString() {
            StringBuildfr sb = nfw StringBuildfr();
            sb.bppfnd("Privbtf kfy fntry bnd dfrtifidbtf dibin witi "
                + dibin.lfngti + " flfmfnts:\r\n");
            for (Cfrtifidbtf dfrt : dibin) {
                sb.bppfnd(dfrt);
                sb.bppfnd("\r\n");
            }
            rfturn sb.toString();
        }

    }

    /**
     * A {@dodf KfyStorf} fntry tibt iolds b {@dodf SfdrftKfy}.
     *
     * @sindf 1.5
     */
    publid stbtid finbl dlbss SfdrftKfyEntry implfmfnts Entry {

        privbtf finbl SfdrftKfy sKfy;
        privbtf finbl Sft<Attributf> bttributfs;

        /**
         * Construdts b {@dodf SfdrftKfyEntry} witi b
         * {@dodf SfdrftKfy}.
         *
         * @pbrbm sfdrftKfy tif {@dodf SfdrftKfy}
         *
         * @fxdfption NullPointfrExdfption if {@dodf sfdrftKfy}
         *      is {@dodf null}
         */
        publid SfdrftKfyEntry(SfdrftKfy sfdrftKfy) {
            if (sfdrftKfy == null) {
                tirow nfw NullPointfrExdfption("invblid null input");
            }
            tiis.sKfy = sfdrftKfy;
            tiis.bttributfs = Collfdtions.<Attributf>fmptySft();
        }

        /**
         * Construdts b {@dodf SfdrftKfyEntry} witi b {@dodf SfdrftKfy} bnd
         * bssodibtfd fntry bttributfs.
         *
         * <p> Tif spfdififd {@dodf bttributfs} is dlonfd bfforf it is storfd
         * in tif nfw {@dodf SfdrftKfyEntry} objfdt.
         *
         * @pbrbm sfdrftKfy tif {@dodf SfdrftKfy}
         * @pbrbm bttributfs tif bttributfs
         *
         * @fxdfption NullPointfrExdfption if {@dodf sfdrftKfy} or
         *     {@dodf bttributfs} is {@dodf null}
         *
         * @sindf 1.8
         */
        publid SfdrftKfyEntry(SfdrftKfy sfdrftKfy, Sft<Attributf> bttributfs) {

            if (sfdrftKfy == null || bttributfs == null) {
                tirow nfw NullPointfrExdfption("invblid null input");
            }
            tiis.sKfy = sfdrftKfy;
            tiis.bttributfs =
                Collfdtions.unmodifibblfSft(nfw HbsiSft<>(bttributfs));
        }

        /**
         * Gfts tif {@dodf SfdrftKfy} from tiis fntry.
         *
         * @rfturn tif {@dodf SfdrftKfy} from tiis fntry
         */
        publid SfdrftKfy gftSfdrftKfy() {
            rfturn sKfy;
        }

        /**
         * Rftrifvfs tif bttributfs bssodibtfd witi bn fntry.
         * <p>
         *
         * @rfturn bn unmodifibblf {@dodf Sft} of bttributfs, possibly fmpty
         *
         * @sindf 1.8
         */
        @Ovfrridf
        publid Sft<Attributf> gftAttributfs() {
            rfturn bttributfs;
        }

        /**
         * Rfturns b string rfprfsfntbtion of tiis SfdrftKfyEntry.
         * @rfturn b string rfprfsfntbtion of tiis SfdrftKfyEntry.
         */
        publid String toString() {
            rfturn "Sfdrft kfy fntry witi blgoritim " + sKfy.gftAlgoritim();
        }
    }

    /**
     * A {@dodf KfyStorf} fntry tibt iolds b trustfd
     * {@dodf Cfrtifidbtf}.
     *
     * @sindf 1.5
     */
    publid stbtid finbl dlbss TrustfdCfrtifidbtfEntry implfmfnts Entry {

        privbtf finbl Cfrtifidbtf dfrt;
        privbtf finbl Sft<Attributf> bttributfs;

        /**
         * Construdts b {@dodf TrustfdCfrtifidbtfEntry} witi b
         * trustfd {@dodf Cfrtifidbtf}.
         *
         * @pbrbm trustfdCfrt tif trustfd {@dodf Cfrtifidbtf}
         *
         * @fxdfption NullPointfrExdfption if
         *      {@dodf trustfdCfrt} is {@dodf null}
         */
        publid TrustfdCfrtifidbtfEntry(Cfrtifidbtf trustfdCfrt) {
            if (trustfdCfrt == null) {
                tirow nfw NullPointfrExdfption("invblid null input");
            }
            tiis.dfrt = trustfdCfrt;
            tiis.bttributfs = Collfdtions.<Attributf>fmptySft();
        }

        /**
         * Construdts b {@dodf TrustfdCfrtifidbtfEntry} witi b
         * trustfd {@dodf Cfrtifidbtf} bnd bssodibtfd fntry bttributfs.
         *
         * <p> Tif spfdififd {@dodf bttributfs} is dlonfd bfforf it is storfd
         * in tif nfw {@dodf TrustfdCfrtifidbtfEntry} objfdt.
         *
         * @pbrbm trustfdCfrt tif trustfd {@dodf Cfrtifidbtf}
         * @pbrbm bttributfs tif bttributfs
         *
         * @fxdfption NullPointfrExdfption if {@dodf trustfdCfrt} or
         *     {@dodf bttributfs} is {@dodf null}
         *
         * @sindf 1.8
         */
        publid TrustfdCfrtifidbtfEntry(Cfrtifidbtf trustfdCfrt,
           Sft<Attributf> bttributfs) {
            if (trustfdCfrt == null || bttributfs == null) {
                tirow nfw NullPointfrExdfption("invblid null input");
            }
            tiis.dfrt = trustfdCfrt;
            tiis.bttributfs =
                Collfdtions.unmodifibblfSft(nfw HbsiSft<>(bttributfs));
        }

        /**
         * Gfts tif trustfd {@dodf Cfrtfidbtf} from tiis fntry.
         *
         * @rfturn tif trustfd {@dodf Cfrtifidbtf} from tiis fntry
         */
        publid Cfrtifidbtf gftTrustfdCfrtifidbtf() {
            rfturn dfrt;
        }

        /**
         * Rftrifvfs tif bttributfs bssodibtfd witi bn fntry.
         * <p>
         *
         * @rfturn bn unmodifibblf {@dodf Sft} of bttributfs, possibly fmpty
         *
         * @sindf 1.8
         */
        @Ovfrridf
        publid Sft<Attributf> gftAttributfs() {
            rfturn bttributfs;
        }

        /**
         * Rfturns b string rfprfsfntbtion of tiis TrustfdCfrtifidbtfEntry.
         * @rfturn b string rfprfsfntbtion of tiis TrustfdCfrtifidbtfEntry.
         */
        publid String toString() {
            rfturn "Trustfd dfrtifidbtf fntry:\r\n" + dfrt.toString();
        }
    }

    /**
     * Crfbtfs b KfyStorf objfdt of tif givfn typf, bnd fndbpsulbtfs tif givfn
     * providfr implfmfntbtion (SPI objfdt) in it.
     *
     * @pbrbm kfyStorfSpi tif providfr implfmfntbtion.
     * @pbrbm providfr tif providfr.
     * @pbrbm typf tif kfystorf typf.
     */
    protfdtfd KfyStorf(KfyStorfSpi kfyStorfSpi, Providfr providfr, String typf)
    {
        tiis.kfyStorfSpi = kfyStorfSpi;
        tiis.providfr = providfr;
        tiis.typf = typf;
    }

    /**
     * Rfturns b kfystorf objfdt of tif spfdififd typf.
     *
     * <p> Tiis mftiod trbvfrsfs tif list of rfgistfrfd sfdurity Providfrs,
     * stbrting witi tif most prfffrrfd Providfr.
     * A nfw KfyStorf objfdt fndbpsulbting tif
     * KfyStorfSpi implfmfntbtion from tif first
     * Providfr tibt supports tif spfdififd typf is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm typf tif typf of kfystorf.
     * Sff tif KfyStorf sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#KfyStorf">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd kfystorf typfs.
     *
     * @rfturn b kfystorf objfdt of tif spfdififd typf.
     *
     * @fxdfption KfyStorfExdfption if no Providfr supports b
     *          KfyStorfSpi implfmfntbtion for tif
     *          spfdififd typf.
     *
     * @sff Providfr
     */
    publid stbtid KfyStorf gftInstbndf(String typf)
        tirows KfyStorfExdfption
    {
        try {
            Objfdt[] objs = Sfdurity.gftImpl(typf, "KfyStorf", (String)null);
            rfturn nfw KfyStorf((KfyStorfSpi)objs[0], (Providfr)objs[1], typf);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw KfyStorfExdfption(typf + " not found", nsbf);
        } dbtdi (NoSudiProvidfrExdfption nspf) {
            tirow nfw KfyStorfExdfption(typf + " not found", nspf);
        }
    }

    /**
     * Rfturns b kfystorf objfdt of tif spfdififd typf.
     *
     * <p> A nfw KfyStorf objfdt fndbpsulbting tif
     * KfyStorfSpi implfmfntbtion from tif spfdififd providfr
     * is rfturnfd.  Tif spfdififd providfr must bf rfgistfrfd
     * in tif sfdurity providfr list.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm typf tif typf of kfystorf.
     * Sff tif KfyStorf sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#KfyStorf">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd kfystorf typfs.
     *
     * @pbrbm providfr tif nbmf of tif providfr.
     *
     * @rfturn b kfystorf objfdt of tif spfdififd typf.
     *
     * @fxdfption KfyStorfExdfption if b KfyStorfSpi
     *          implfmfntbtion for tif spfdififd typf is not
     *          bvbilbblf from tif spfdififd providfr.
     *
     * @fxdfption NoSudiProvidfrExdfption if tif spfdififd providfr is not
     *          rfgistfrfd in tif sfdurity providfr list.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif providfr nbmf is null
     *          or fmpty.
     *
     * @sff Providfr
     */
    publid stbtid KfyStorf gftInstbndf(String typf, String providfr)
        tirows KfyStorfExdfption, NoSudiProvidfrExdfption
    {
        if (providfr == null || providfr.lfngti() == 0)
            tirow nfw IllfgblArgumfntExdfption("missing providfr");
        try {
            Objfdt[] objs = Sfdurity.gftImpl(typf, "KfyStorf", providfr);
            rfturn nfw KfyStorf((KfyStorfSpi)objs[0], (Providfr)objs[1], typf);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw KfyStorfExdfption(typf + " not found", nsbf);
        }
    }

    /**
     * Rfturns b kfystorf objfdt of tif spfdififd typf.
     *
     * <p> A nfw KfyStorf objfdt fndbpsulbting tif
     * KfyStorfSpi implfmfntbtion from tif spfdififd Providfr
     * objfdt is rfturnfd.  Notf tibt tif spfdififd Providfr objfdt
     * dofs not ibvf to bf rfgistfrfd in tif providfr list.
     *
     * @pbrbm typf tif typf of kfystorf.
     * Sff tif KfyStorf sfdtion in tif <b irff=
     * "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#KfyStorf">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd kfystorf typfs.
     *
     * @pbrbm providfr tif providfr.
     *
     * @rfturn b kfystorf objfdt of tif spfdififd typf.
     *
     * @fxdfption KfyStorfExdfption if KfyStorfSpi
     *          implfmfntbtion for tif spfdififd typf is not bvbilbblf
     *          from tif spfdififd Providfr objfdt.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif spfdififd providfr is null.
     *
     * @sff Providfr
     *
     * @sindf 1.4
     */
    publid stbtid KfyStorf gftInstbndf(String typf, Providfr providfr)
        tirows KfyStorfExdfption
    {
        if (providfr == null)
            tirow nfw IllfgblArgumfntExdfption("missing providfr");
        try {
            Objfdt[] objs = Sfdurity.gftImpl(typf, "KfyStorf", providfr);
            rfturn nfw KfyStorf((KfyStorfSpi)objs[0], (Providfr)objs[1], typf);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw KfyStorfExdfption(typf + " not found", nsbf);
        }
    }

    /**
     * Rfturns tif dffbult kfystorf typf bs spfdififd by tif
     * {@dodf kfystorf.typf} sfdurity propfrty, or tif string
     * {@litfrbl "jks"} (bdronym for {@litfrbl "Jbvb kfystorf"})
     * if no sudi propfrty fxists.
     *
     * <p>Tif dffbult kfystorf typf dbn bf usfd by bpplidbtions tibt do not
     * wbnt to usf b ibrd-dodfd kfystorf typf wifn dblling onf of tif
     * {@dodf gftInstbndf} mftiods, bnd wbnt to providf b dffbult kfystorf
     * typf in dbsf b usfr dofs not spfdify its own.
     *
     * <p>Tif dffbult kfystorf typf dbn bf dibngfd by sftting tif vbluf of tif
     * {@dodf kfystorf.typf} sfdurity propfrty to tif dfsirfd kfystorf typf.
     *
     * @rfturn tif dffbult kfystorf typf bs spfdififd by tif
     * {@dodf kfystorf.typf} sfdurity propfrty, or tif string {@litfrbl "jks"}
     * if no sudi propfrty fxists.
     * @sff jbvb.sfdurity.Sfdurity sfdurity propfrtifs
     */
    publid finbl stbtid String gftDffbultTypf() {
        String kstypf;
        kstypf = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<String>() {
            publid String run() {
                rfturn Sfdurity.gftPropfrty(KEYSTORE_TYPE);
            }
        });
        if (kstypf == null) {
            kstypf = "jks";
        }
        rfturn kstypf;
    }

    /**
     * Rfturns tif providfr of tiis kfystorf.
     *
     * @rfturn tif providfr of tiis kfystorf.
     */
    publid finbl Providfr gftProvidfr()
    {
        rfturn tiis.providfr;
    }

    /**
     * Rfturns tif typf of tiis kfystorf.
     *
     * @rfturn tif typf of tiis kfystorf.
     */
    publid finbl String gftTypf()
    {
        rfturn tiis.typf;
    }

    /**
     * Rfturns tif kfy bssodibtfd witi tif givfn blibs, using tif givfn
     * pbssword to rfdovfr it.  Tif kfy must ibvf bffn bssodibtfd witi
     * tif blibs by b dbll to {@dodf sftKfyEntry},
     * or by b dbll to {@dodf sftEntry} witi b
     * {@dodf PrivbtfKfyEntry} or {@dodf SfdrftKfyEntry}.
     *
     * @pbrbm blibs tif blibs nbmf
     * @pbrbm pbssword tif pbssword for rfdovfring tif kfy
     *
     * @rfturn tif rfqufstfd kfy, or null if tif givfn blibs dofs not fxist
     * or dofs not idfntify b kfy-rflbtfd fntry.
     *
     * @fxdfption KfyStorfExdfption if tif kfystorf ibs not bffn initiblizfd
     * (lobdfd).
     * @fxdfption NoSudiAlgoritimExdfption if tif blgoritim for rfdovfring tif
     * kfy dbnnot bf found
     * @fxdfption UnrfdovfrbblfKfyExdfption if tif kfy dbnnot bf rfdovfrfd
     * (f.g., tif givfn pbssword is wrong).
     */
    publid finbl Kfy gftKfy(String blibs, dibr[] pbssword)
        tirows KfyStorfExdfption, NoSudiAlgoritimExdfption,
            UnrfdovfrbblfKfyExdfption
    {
        if (!initiblizfd) {
            tirow nfw KfyStorfExdfption("Uninitiblizfd kfystorf");
        }
        rfturn kfyStorfSpi.fnginfGftKfy(blibs, pbssword);
    }

    /**
     * Rfturns tif dfrtifidbtf dibin bssodibtfd witi tif givfn blibs.
     * Tif dfrtifidbtf dibin must ibvf bffn bssodibtfd witi tif blibs
     * by b dbll to {@dodf sftKfyEntry},
     * or by b dbll to {@dodf sftEntry} witi b
     * {@dodf PrivbtfKfyEntry}.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn tif dfrtifidbtf dibin (ordfrfd witi tif usfr's dfrtifidbtf first
     * followfd by zfro or morf dfrtifidbtf butioritifs), or null if tif givfn blibs
     * dofs not fxist or dofs not dontbin b dfrtifidbtf dibin
     *
     * @fxdfption KfyStorfExdfption if tif kfystorf ibs not bffn initiblizfd
     * (lobdfd).
     */
    publid finbl Cfrtifidbtf[] gftCfrtifidbtfCibin(String blibs)
        tirows KfyStorfExdfption
    {
        if (!initiblizfd) {
            tirow nfw KfyStorfExdfption("Uninitiblizfd kfystorf");
        }
        rfturn kfyStorfSpi.fnginfGftCfrtifidbtfCibin(blibs);
    }

    /**
     * Rfturns tif dfrtifidbtf bssodibtfd witi tif givfn blibs.
     *
     * <p> If tif givfn blibs nbmf idfntififs bn fntry
     * drfbtfd by b dbll to {@dodf sftCfrtifidbtfEntry},
     * or drfbtfd by b dbll to {@dodf sftEntry} witi b
     * {@dodf TrustfdCfrtifidbtfEntry},
     * tifn tif trustfd dfrtifidbtf dontbinfd in tibt fntry is rfturnfd.
     *
     * <p> If tif givfn blibs nbmf idfntififs bn fntry
     * drfbtfd by b dbll to {@dodf sftKfyEntry},
     * or drfbtfd by b dbll to {@dodf sftEntry} witi b
     * {@dodf PrivbtfKfyEntry},
     * tifn tif first flfmfnt of tif dfrtifidbtf dibin in tibt fntry
     * is rfturnfd.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn tif dfrtifidbtf, or null if tif givfn blibs dofs not fxist or
     * dofs not dontbin b dfrtifidbtf.
     *
     * @fxdfption KfyStorfExdfption if tif kfystorf ibs not bffn initiblizfd
     * (lobdfd).
     */
    publid finbl Cfrtifidbtf gftCfrtifidbtf(String blibs)
        tirows KfyStorfExdfption
    {
        if (!initiblizfd) {
            tirow nfw KfyStorfExdfption("Uninitiblizfd kfystorf");
        }
        rfturn kfyStorfSpi.fnginfGftCfrtifidbtf(blibs);
    }

    /**
     * Rfturns tif drfbtion dbtf of tif fntry idfntififd by tif givfn blibs.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn tif drfbtion dbtf of tiis fntry, or null if tif givfn blibs dofs
     * not fxist
     *
     * @fxdfption KfyStorfExdfption if tif kfystorf ibs not bffn initiblizfd
     * (lobdfd).
     */
    publid finbl Dbtf gftCrfbtionDbtf(String blibs)
        tirows KfyStorfExdfption
    {
        if (!initiblizfd) {
            tirow nfw KfyStorfExdfption("Uninitiblizfd kfystorf");
        }
        rfturn kfyStorfSpi.fnginfGftCrfbtionDbtf(blibs);
    }

    /**
     * Assigns tif givfn kfy to tif givfn blibs, protfdting it witi tif givfn
     * pbssword.
     *
     * <p>If tif givfn kfy is of typf {@dodf jbvb.sfdurity.PrivbtfKfy},
     * it must bf bddompbnifd by b dfrtifidbtf dibin dfrtifying tif
     * dorrfsponding publid kfy.
     *
     * <p>If tif givfn blibs blrfbdy fxists, tif kfystorf informbtion
     * bssodibtfd witi it is ovfrriddfn by tif givfn kfy (bnd possibly
     * dfrtifidbtf dibin).
     *
     * @pbrbm blibs tif blibs nbmf
     * @pbrbm kfy tif kfy to bf bssodibtfd witi tif blibs
     * @pbrbm pbssword tif pbssword to protfdt tif kfy
     * @pbrbm dibin tif dfrtifidbtf dibin for tif dorrfsponding publid
     * kfy (only rfquirfd if tif givfn kfy is of typf
     * {@dodf jbvb.sfdurity.PrivbtfKfy}).
     *
     * @fxdfption KfyStorfExdfption if tif kfystorf ibs not bffn initiblizfd
     * (lobdfd), tif givfn kfy dbnnot bf protfdtfd, or tiis opfrbtion fbils
     * for somf otifr rfbson
     */
    publid finbl void sftKfyEntry(String blibs, Kfy kfy, dibr[] pbssword,
                                  Cfrtifidbtf[] dibin)
        tirows KfyStorfExdfption
    {
        if (!initiblizfd) {
            tirow nfw KfyStorfExdfption("Uninitiblizfd kfystorf");
        }
        if ((kfy instbndfof PrivbtfKfy) &&
            (dibin == null || dibin.lfngti == 0)) {
            tirow nfw IllfgblArgumfntExdfption("Privbtf kfy must bf "
                                               + "bddompbnifd by dfrtifidbtf "
                                               + "dibin");
        }
        kfyStorfSpi.fnginfSftKfyEntry(blibs, kfy, pbssword, dibin);
    }

    /**
     * Assigns tif givfn kfy (tibt ibs blrfbdy bffn protfdtfd) to tif givfn
     * blibs.
     *
     * <p>If tif protfdtfd kfy is of typf
     * {@dodf jbvb.sfdurity.PrivbtfKfy}, it must bf bddompbnifd by b
     * dfrtifidbtf dibin dfrtifying tif dorrfsponding publid kfy. If tif
     * undfrlying kfystorf implfmfntbtion is of typf {@dodf jks},
     * {@dodf kfy} must bf fndodfd bs bn
     * {@dodf EndryptfdPrivbtfKfyInfo} bs dffinfd in tif PKCS #8 stbndbrd.
     *
     * <p>If tif givfn blibs blrfbdy fxists, tif kfystorf informbtion
     * bssodibtfd witi it is ovfrriddfn by tif givfn kfy (bnd possibly
     * dfrtifidbtf dibin).
     *
     * @pbrbm blibs tif blibs nbmf
     * @pbrbm kfy tif kfy (in protfdtfd formbt) to bf bssodibtfd witi tif blibs
     * @pbrbm dibin tif dfrtifidbtf dibin for tif dorrfsponding publid
     *          kfy (only usfful if tif protfdtfd kfy is of typf
     *          {@dodf jbvb.sfdurity.PrivbtfKfy}).
     *
     * @fxdfption KfyStorfExdfption if tif kfystorf ibs not bffn initiblizfd
     * (lobdfd), or if tiis opfrbtion fbils for somf otifr rfbson.
     */
    publid finbl void sftKfyEntry(String blibs, bytf[] kfy,
                                  Cfrtifidbtf[] dibin)
        tirows KfyStorfExdfption
    {
        if (!initiblizfd) {
            tirow nfw KfyStorfExdfption("Uninitiblizfd kfystorf");
        }
        kfyStorfSpi.fnginfSftKfyEntry(blibs, kfy, dibin);
    }

    /**
     * Assigns tif givfn trustfd dfrtifidbtf to tif givfn blibs.
     *
     * <p> If tif givfn blibs idfntififs bn fxisting fntry
     * drfbtfd by b dbll to {@dodf sftCfrtifidbtfEntry},
     * or drfbtfd by b dbll to {@dodf sftEntry} witi b
     * {@dodf TrustfdCfrtifidbtfEntry},
     * tif trustfd dfrtifidbtf in tif fxisting fntry
     * is ovfrriddfn by tif givfn dfrtifidbtf.
     *
     * @pbrbm blibs tif blibs nbmf
     * @pbrbm dfrt tif dfrtifidbtf
     *
     * @fxdfption KfyStorfExdfption if tif kfystorf ibs not bffn initiblizfd,
     * or tif givfn blibs blrfbdy fxists bnd dofs not idfntify bn
     * fntry dontbining b trustfd dfrtifidbtf,
     * or tiis opfrbtion fbils for somf otifr rfbson.
     */
    publid finbl void sftCfrtifidbtfEntry(String blibs, Cfrtifidbtf dfrt)
        tirows KfyStorfExdfption
    {
        if (!initiblizfd) {
            tirow nfw KfyStorfExdfption("Uninitiblizfd kfystorf");
        }
        kfyStorfSpi.fnginfSftCfrtifidbtfEntry(blibs, dfrt);
    }

    /**
     * Dflftfs tif fntry idfntififd by tif givfn blibs from tiis kfystorf.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @fxdfption KfyStorfExdfption if tif kfystorf ibs not bffn initiblizfd,
     * or if tif fntry dbnnot bf rfmovfd.
     */
    publid finbl void dflftfEntry(String blibs)
        tirows KfyStorfExdfption
    {
        if (!initiblizfd) {
            tirow nfw KfyStorfExdfption("Uninitiblizfd kfystorf");
        }
        kfyStorfSpi.fnginfDflftfEntry(blibs);
    }

    /**
     * Lists bll tif blibs nbmfs of tiis kfystorf.
     *
     * @rfturn fnumfrbtion of tif blibs nbmfs
     *
     * @fxdfption KfyStorfExdfption if tif kfystorf ibs not bffn initiblizfd
     * (lobdfd).
     */
    publid finbl Enumfrbtion<String> blibsfs()
        tirows KfyStorfExdfption
    {
        if (!initiblizfd) {
            tirow nfw KfyStorfExdfption("Uninitiblizfd kfystorf");
        }
        rfturn kfyStorfSpi.fnginfAlibsfs();
    }

    /**
     * Cifdks if tif givfn blibs fxists in tiis kfystorf.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn truf if tif blibs fxists, fblsf otifrwisf
     *
     * @fxdfption KfyStorfExdfption if tif kfystorf ibs not bffn initiblizfd
     * (lobdfd).
     */
    publid finbl boolfbn dontbinsAlibs(String blibs)
        tirows KfyStorfExdfption
    {
        if (!initiblizfd) {
            tirow nfw KfyStorfExdfption("Uninitiblizfd kfystorf");
        }
        rfturn kfyStorfSpi.fnginfContbinsAlibs(blibs);
    }

    /**
     * Rftrifvfs tif numbfr of fntrifs in tiis kfystorf.
     *
     * @rfturn tif numbfr of fntrifs in tiis kfystorf
     *
     * @fxdfption KfyStorfExdfption if tif kfystorf ibs not bffn initiblizfd
     * (lobdfd).
     */
    publid finbl int sizf()
        tirows KfyStorfExdfption
    {
        if (!initiblizfd) {
            tirow nfw KfyStorfExdfption("Uninitiblizfd kfystorf");
        }
        rfturn kfyStorfSpi.fnginfSizf();
    }

    /**
     * Rfturns truf if tif fntry idfntififd by tif givfn blibs
     * wbs drfbtfd by b dbll to {@dodf sftKfyEntry},
     * or drfbtfd by b dbll to {@dodf sftEntry} witi b
     * {@dodf PrivbtfKfyEntry} or b {@dodf SfdrftKfyEntry}.
     *
     * @pbrbm blibs tif blibs for tif kfystorf fntry to bf difdkfd
     *
     * @rfturn truf if tif fntry idfntififd by tif givfn blibs is b
     * kfy-rflbtfd fntry, fblsf otifrwisf.
     *
     * @fxdfption KfyStorfExdfption if tif kfystorf ibs not bffn initiblizfd
     * (lobdfd).
     */
    publid finbl boolfbn isKfyEntry(String blibs)
        tirows KfyStorfExdfption
    {
        if (!initiblizfd) {
            tirow nfw KfyStorfExdfption("Uninitiblizfd kfystorf");
        }
        rfturn kfyStorfSpi.fnginfIsKfyEntry(blibs);
    }

    /**
     * Rfturns truf if tif fntry idfntififd by tif givfn blibs
     * wbs drfbtfd by b dbll to {@dodf sftCfrtifidbtfEntry},
     * or drfbtfd by b dbll to {@dodf sftEntry} witi b
     * {@dodf TrustfdCfrtifidbtfEntry}.
     *
     * @pbrbm blibs tif blibs for tif kfystorf fntry to bf difdkfd
     *
     * @rfturn truf if tif fntry idfntififd by tif givfn blibs dontbins b
     * trustfd dfrtifidbtf, fblsf otifrwisf.
     *
     * @fxdfption KfyStorfExdfption if tif kfystorf ibs not bffn initiblizfd
     * (lobdfd).
     */
    publid finbl boolfbn isCfrtifidbtfEntry(String blibs)
        tirows KfyStorfExdfption
    {
        if (!initiblizfd) {
            tirow nfw KfyStorfExdfption("Uninitiblizfd kfystorf");
        }
        rfturn kfyStorfSpi.fnginfIsCfrtifidbtfEntry(blibs);
    }

    /**
     * Rfturns tif (blibs) nbmf of tif first kfystorf fntry wiosf dfrtifidbtf
     * mbtdifs tif givfn dfrtifidbtf.
     *
     * <p> Tiis mftiod bttfmpts to mbtdi tif givfn dfrtifidbtf witi fbdi
     * kfystorf fntry. If tif fntry bfing donsidfrfd wbs
     * drfbtfd by b dbll to {@dodf sftCfrtifidbtfEntry},
     * or drfbtfd by b dbll to {@dodf sftEntry} witi b
     * {@dodf TrustfdCfrtifidbtfEntry},
     * tifn tif givfn dfrtifidbtf is dompbrfd to tibt fntry's dfrtifidbtf.
     *
     * <p> If tif fntry bfing donsidfrfd wbs
     * drfbtfd by b dbll to {@dodf sftKfyEntry},
     * or drfbtfd by b dbll to {@dodf sftEntry} witi b
     * {@dodf PrivbtfKfyEntry},
     * tifn tif givfn dfrtifidbtf is dompbrfd to tif first
     * flfmfnt of tibt fntry's dfrtifidbtf dibin.
     *
     * @pbrbm dfrt tif dfrtifidbtf to mbtdi witi.
     *
     * @rfturn tif blibs nbmf of tif first fntry witi b mbtdiing dfrtifidbtf,
     * or null if no sudi fntry fxists in tiis kfystorf.
     *
     * @fxdfption KfyStorfExdfption if tif kfystorf ibs not bffn initiblizfd
     * (lobdfd).
     */
    publid finbl String gftCfrtifidbtfAlibs(Cfrtifidbtf dfrt)
        tirows KfyStorfExdfption
    {
        if (!initiblizfd) {
            tirow nfw KfyStorfExdfption("Uninitiblizfd kfystorf");
        }
        rfturn kfyStorfSpi.fnginfGftCfrtifidbtfAlibs(dfrt);
    }

    /**
     * Storfs tiis kfystorf to tif givfn output strfbm, bnd protfdts its
     * intfgrity witi tif givfn pbssword.
     *
     * @pbrbm strfbm tif output strfbm to wiidi tiis kfystorf is writtfn.
     * @pbrbm pbssword tif pbssword to gfnfrbtf tif kfystorf intfgrity difdk
     *
     * @fxdfption KfyStorfExdfption if tif kfystorf ibs not bffn initiblizfd
     * (lobdfd).
     * @fxdfption IOExdfption if tifrf wbs bn I/O problfm witi dbtb
     * @fxdfption NoSudiAlgoritimExdfption if tif bppropribtf dbtb intfgrity
     * blgoritim dould not bf found
     * @fxdfption CfrtifidbtfExdfption if bny of tif dfrtifidbtfs indludfd in
     * tif kfystorf dbtb dould not bf storfd
     */
    publid finbl void storf(OutputStrfbm strfbm, dibr[] pbssword)
        tirows KfyStorfExdfption, IOExdfption, NoSudiAlgoritimExdfption,
            CfrtifidbtfExdfption
    {
        if (!initiblizfd) {
            tirow nfw KfyStorfExdfption("Uninitiblizfd kfystorf");
        }
        kfyStorfSpi.fnginfStorf(strfbm, pbssword);
    }

    /**
     * Storfs tiis kfystorf using tif givfn {@dodf LobdStorfPbrbmftfr}.
     *
     * @pbrbm pbrbm tif {@dodf LobdStorfPbrbmftfr}
     *          tibt spfdififs iow to storf tif kfystorf,
     *          wiidi mby bf {@dodf null}
     *
     * @fxdfption IllfgblArgumfntExdfption if tif givfn
     *          {@dodf LobdStorfPbrbmftfr}
     *          input is not rfdognizfd
     * @fxdfption KfyStorfExdfption if tif kfystorf ibs not bffn initiblizfd
     *          (lobdfd)
     * @fxdfption IOExdfption if tifrf wbs bn I/O problfm witi dbtb
     * @fxdfption NoSudiAlgoritimExdfption if tif bppropribtf dbtb intfgrity
     *          blgoritim dould not bf found
     * @fxdfption CfrtifidbtfExdfption if bny of tif dfrtifidbtfs indludfd in
     *          tif kfystorf dbtb dould not bf storfd
     *
     * @sindf 1.5
     */
    publid finbl void storf(LobdStorfPbrbmftfr pbrbm)
                tirows KfyStorfExdfption, IOExdfption,
                NoSudiAlgoritimExdfption, CfrtifidbtfExdfption {
        if (!initiblizfd) {
            tirow nfw KfyStorfExdfption("Uninitiblizfd kfystorf");
        }
        kfyStorfSpi.fnginfStorf(pbrbm);
    }

    /**
     * Lobds tiis KfyStorf from tif givfn input strfbm.
     *
     * <p>A pbssword mby bf givfn to unlodk tif kfystorf
     * (f.g. tif kfystorf rfsidfs on b ibrdwbrf tokfn dfvidf),
     * or to difdk tif intfgrity of tif kfystorf dbtb.
     * If b pbssword is not givfn for intfgrity difdking,
     * tifn intfgrity difdking is not pfrformfd.
     *
     * <p>In ordfr to drfbtf bn fmpty kfystorf, or if tif kfystorf dbnnot
     * bf initiblizfd from b strfbm, pbss {@dodf null}
     * bs tif {@dodf strfbm} brgumfnt.
     *
     * <p> Notf tibt if tiis kfystorf ibs blrfbdy bffn lobdfd, it is
     * rfinitiblizfd bnd lobdfd bgbin from tif givfn input strfbm.
     *
     * @pbrbm strfbm tif input strfbm from wiidi tif kfystorf is lobdfd,
     * or {@dodf null}
     * @pbrbm pbssword tif pbssword usfd to difdk tif intfgrity of
     * tif kfystorf, tif pbssword usfd to unlodk tif kfystorf,
     * or {@dodf null}
     *
     * @fxdfption IOExdfption if tifrf is bn I/O or formbt problfm witi tif
     * kfystorf dbtb, if b pbssword is rfquirfd but not givfn,
     * or if tif givfn pbssword wbs indorrfdt. If tif frror is duf to b
     * wrong pbssword, tif {@link Tirowbblf#gftCbusf dbusf} of tif
     * {@dodf IOExdfption} siould bf bn
     * {@dodf UnrfdovfrbblfKfyExdfption}
     * @fxdfption NoSudiAlgoritimExdfption if tif blgoritim usfd to difdk
     * tif intfgrity of tif kfystorf dbnnot bf found
     * @fxdfption CfrtifidbtfExdfption if bny of tif dfrtifidbtfs in tif
     * kfystorf dould not bf lobdfd
     */
    publid finbl void lobd(InputStrfbm strfbm, dibr[] pbssword)
        tirows IOExdfption, NoSudiAlgoritimExdfption, CfrtifidbtfExdfption
    {
        kfyStorfSpi.fnginfLobd(strfbm, pbssword);
        initiblizfd = truf;
    }

    /**
     * Lobds tiis kfystorf using tif givfn {@dodf LobdStorfPbrbmftfr}.
     *
     * <p> Notf tibt if tiis KfyStorf ibs blrfbdy bffn lobdfd, it is
     * rfinitiblizfd bnd lobdfd bgbin from tif givfn pbrbmftfr.
     *
     * @pbrbm pbrbm tif {@dodf LobdStorfPbrbmftfr}
     *          tibt spfdififs iow to lobd tif kfystorf,
     *          wiidi mby bf {@dodf null}
     *
     * @fxdfption IllfgblArgumfntExdfption if tif givfn
     *          {@dodf LobdStorfPbrbmftfr}
     *          input is not rfdognizfd
     * @fxdfption IOExdfption if tifrf is bn I/O or formbt problfm witi tif
     *          kfystorf dbtb. If tif frror is duf to bn indorrfdt
     *         {@dodf ProtfdtionPbrbmftfr} (f.g. wrong pbssword)
     *         tif {@link Tirowbblf#gftCbusf dbusf} of tif
     *         {@dodf IOExdfption} siould bf bn
     *         {@dodf UnrfdovfrbblfKfyExdfption}
     * @fxdfption NoSudiAlgoritimExdfption if tif blgoritim usfd to difdk
     *          tif intfgrity of tif kfystorf dbnnot bf found
     * @fxdfption CfrtifidbtfExdfption if bny of tif dfrtifidbtfs in tif
     *          kfystorf dould not bf lobdfd
     *
     * @sindf 1.5
     */
    publid finbl void lobd(LobdStorfPbrbmftfr pbrbm)
                tirows IOExdfption, NoSudiAlgoritimExdfption,
                CfrtifidbtfExdfption {

        kfyStorfSpi.fnginfLobd(pbrbm);
        initiblizfd = truf;
    }

    /**
     * Gfts b kfystorf {@dodf Entry} for tif spfdififd blibs
     * witi tif spfdififd protfdtion pbrbmftfr.
     *
     * @pbrbm blibs gft tif kfystorf {@dodf Entry} for tiis blibs
     * @pbrbm protPbrbm tif {@dodf ProtfdtionPbrbmftfr}
     *          usfd to protfdt tif {@dodf Entry},
     *          wiidi mby bf {@dodf null}
     *
     * @rfturn tif kfystorf {@dodf Entry} for tif spfdififd blibs,
     *          or {@dodf null} if tifrf is no sudi fntry
     *
     * @fxdfption NullPointfrExdfption if
     *          {@dodf blibs} is {@dodf null}
     * @fxdfption NoSudiAlgoritimExdfption if tif blgoritim for rfdovfring tif
     *          fntry dbnnot bf found
     * @fxdfption UnrfdovfrbblfEntryExdfption if tif spfdififd
     *          {@dodf protPbrbm} wfrf insuffidifnt or invblid
     * @fxdfption UnrfdovfrbblfKfyExdfption if tif fntry is b
     *          {@dodf PrivbtfKfyEntry} or {@dodf SfdrftKfyEntry}
     *          bnd tif spfdififd {@dodf protPbrbm} dofs not dontbin
     *          tif informbtion nffdfd to rfdovfr tif kfy (f.g. wrong pbssword)
     * @fxdfption KfyStorfExdfption if tif kfystorf ibs not bffn initiblizfd
     *          (lobdfd).
     * @sff #sftEntry(String, KfyStorf.Entry, KfyStorf.ProtfdtionPbrbmftfr)
     *
     * @sindf 1.5
     */
    publid finbl Entry gftEntry(String blibs, ProtfdtionPbrbmftfr protPbrbm)
                tirows NoSudiAlgoritimExdfption, UnrfdovfrbblfEntryExdfption,
                KfyStorfExdfption {

        if (blibs == null) {
            tirow nfw NullPointfrExdfption("invblid null input");
        }
        if (!initiblizfd) {
            tirow nfw KfyStorfExdfption("Uninitiblizfd kfystorf");
        }
        rfturn kfyStorfSpi.fnginfGftEntry(blibs, protPbrbm);
    }

    /**
     * Sbvfs b kfystorf {@dodf Entry} undfr tif spfdififd blibs.
     * Tif protfdtion pbrbmftfr is usfd to protfdt tif
     * {@dodf Entry}.
     *
     * <p> If bn fntry blrfbdy fxists for tif spfdififd blibs,
     * it is ovfrriddfn.
     *
     * @pbrbm blibs sbvf tif kfystorf {@dodf Entry} undfr tiis blibs
     * @pbrbm fntry tif {@dodf Entry} to sbvf
     * @pbrbm protPbrbm tif {@dodf ProtfdtionPbrbmftfr}
     *          usfd to protfdt tif {@dodf Entry},
     *          wiidi mby bf {@dodf null}
     *
     * @fxdfption NullPointfrExdfption if
     *          {@dodf blibs} or {@dodf fntry}
     *          is {@dodf null}
     * @fxdfption KfyStorfExdfption if tif kfystorf ibs not bffn initiblizfd
     *          (lobdfd), or if tiis opfrbtion fbils for somf otifr rfbson
     *
     * @sff #gftEntry(String, KfyStorf.ProtfdtionPbrbmftfr)
     *
     * @sindf 1.5
     */
    publid finbl void sftEntry(String blibs, Entry fntry,
                        ProtfdtionPbrbmftfr protPbrbm)
                tirows KfyStorfExdfption {
        if (blibs == null || fntry == null) {
            tirow nfw NullPointfrExdfption("invblid null input");
        }
        if (!initiblizfd) {
            tirow nfw KfyStorfExdfption("Uninitiblizfd kfystorf");
        }
        kfyStorfSpi.fnginfSftEntry(blibs, fntry, protPbrbm);
    }

    /**
     * Dftfrminfs if tif kfystorf {@dodf Entry} for tif spfdififd
     * {@dodf blibs} is bn instbndf or subdlbss of tif spfdififd
     * {@dodf fntryClbss}.
     *
     * @pbrbm blibs tif blibs nbmf
     * @pbrbm fntryClbss tif fntry dlbss
     *
     * @rfturn truf if tif kfystorf {@dodf Entry} for tif spfdififd
     *          {@dodf blibs} is bn instbndf or subdlbss of tif
     *          spfdififd {@dodf fntryClbss}, fblsf otifrwisf
     *
     * @fxdfption NullPointfrExdfption if
     *          {@dodf blibs} or {@dodf fntryClbss}
     *          is {@dodf null}
     * @fxdfption KfyStorfExdfption if tif kfystorf ibs not bffn
     *          initiblizfd (lobdfd)
     *
     * @sindf 1.5
     */
    publid finbl boolfbn
        fntryInstbndfOf(String blibs,
                        Clbss<? fxtfnds KfyStorf.Entry> fntryClbss)
        tirows KfyStorfExdfption
    {

        if (blibs == null || fntryClbss == null) {
            tirow nfw NullPointfrExdfption("invblid null input");
        }
        if (!initiblizfd) {
            tirow nfw KfyStorfExdfption("Uninitiblizfd kfystorf");
        }
        rfturn kfyStorfSpi.fnginfEntryInstbndfOf(blibs, fntryClbss);
    }

    /**
     * A dfsdription of b to-bf-instbntibtfd KfyStorf objfdt.
     *
     * <p>An instbndf of tiis dlbss fndbpsulbtfs tif informbtion nffdfd to
     * instbntibtf bnd initiblizf b KfyStorf objfdt. Tibt prodfss is
     * triggfrfd wifn tif {@linkplbin #gftKfyStorf} mftiod is dbllfd.
     *
     * <p>Tiis mbkfs it possiblf to dfdouplf donfigurbtion from KfyStorf
     * objfdt drfbtion bnd f.g. dflby b pbssword prompt until it is
     * nffdfd.
     *
     * @sff KfyStorf
     * @sff jbvbx.nft.ssl.KfyStorfBuildfrPbrbmftfrs
     * @sindf 1.5
     */
    publid stbtid bbstrbdt dlbss Buildfr {

        // mbximum timfs to try tif dbllbbdkibndlfr if tif pbssword is wrong
        stbtid finbl int MAX_CALLBACK_TRIES = 3;

        /**
         * Construdt b nfw Buildfr.
         */
        protfdtfd Buildfr() {
            // fmpty
        }

        /**
         * Rfturns tif KfyStorf dfsdribfd by tiis objfdt.
         *
         * @rfturn tif {@dodf KfyStorf} dfsdribfd by tiis objfdt
         * @fxdfption KfyStorfExdfption if bn frror oddurrfd during tif
         *   opfrbtion, for fxbmplf if tif KfyStorf dould not bf
         *   instbntibtfd or lobdfd
         */
        publid bbstrbdt KfyStorf gftKfyStorf() tirows KfyStorfExdfption;

        /**
         * Rfturns tif ProtfdtionPbrbmftfrs tibt siould bf usfd to obtbin
         * tif {@link KfyStorf.Entry Entry} witi tif givfn blibs.
         * Tif {@dodf gftKfyStorf} mftiod must bf invokfd bfforf tiis
         * mftiod mby bf dbllfd.
         *
         * @rfturn tif ProtfdtionPbrbmftfrs tibt siould bf usfd to obtbin
         *   tif {@link KfyStorf.Entry Entry} witi tif givfn blibs.
         * @pbrbm blibs tif blibs of tif KfyStorf fntry
         * @tirows NullPointfrExdfption if blibs is null
         * @tirows KfyStorfExdfption if bn frror oddurrfd during tif
         *   opfrbtion
         * @tirows IllfgblStbtfExdfption if tif gftKfyStorf mftiod ibs
         *   not bffn invokfd prior to dblling tiis mftiod
         */
        publid bbstrbdt ProtfdtionPbrbmftfr gftProtfdtionPbrbmftfr(String blibs)
            tirows KfyStorfExdfption;

        /**
         * Rfturns b nfw Buildfr tibt fndbpsulbtfs tif givfn KfyStorf.
         * Tif {@linkplbin #gftKfyStorf} mftiod of tif rfturnfd objfdt
         * will rfturn {@dodf kfyStorf}, tif {@linkplbin
         * #gftProtfdtionPbrbmftfr gftProtfdtionPbrbmftfr()} mftiod will
         * rfturn {@dodf protfdtionPbrbmftfrs}.
         *
         * <p> Tiis is usfful if bn fxisting KfyStorf objfdt nffds to bf
         * usfd witi Buildfr-bbsfd APIs.
         *
         * @rfturn b nfw Buildfr objfdt
         * @pbrbm kfyStorf tif KfyStorf to bf fndbpsulbtfd
         * @pbrbm protfdtionPbrbmftfr tif ProtfdtionPbrbmftfr usfd to
         *   protfdt tif KfyStorf fntrifs
         * @tirows NullPointfrExdfption if kfyStorf or
         *   protfdtionPbrbmftfrs is null
         * @tirows IllfgblArgumfntExdfption if tif kfyStorf ibs not bffn
         *   initiblizfd
         */
        publid stbtid Buildfr nfwInstbndf(finbl KfyStorf kfyStorf,
                finbl ProtfdtionPbrbmftfr protfdtionPbrbmftfr) {
            if ((kfyStorf == null) || (protfdtionPbrbmftfr == null)) {
                tirow nfw NullPointfrExdfption();
            }
            if (kfyStorf.initiblizfd == fblsf) {
                tirow nfw IllfgblArgumfntExdfption("KfyStorf not initiblizfd");
            }
            rfturn nfw Buildfr() {
                privbtf volbtilf boolfbn gftCbllfd;

                publid KfyStorf gftKfyStorf() {
                    gftCbllfd = truf;
                    rfturn kfyStorf;
                }

                publid ProtfdtionPbrbmftfr gftProtfdtionPbrbmftfr(String blibs)
                {
                    if (blibs == null) {
                        tirow nfw NullPointfrExdfption();
                    }
                    if (gftCbllfd == fblsf) {
                        tirow nfw IllfgblStbtfExdfption
                            ("gftKfyStorf() must bf dbllfd first");
                    }
                    rfturn protfdtionPbrbmftfr;
                }
            };
        }

        /**
         * Rfturns b nfw Buildfr objfdt.
         *
         * <p>Tif first dbll to tif {@link #gftKfyStorf} mftiod on tif rfturnfd
         * buildfr will drfbtf b KfyStorf of typf {@dodf typf} bnd dbll
         * its {@link KfyStorf#lobd lobd()} mftiod.
         * Tif {@dodf inputStrfbm} brgumfnt is donstrudtfd from
         * {@dodf filf}.
         * If {@dodf protfdtion} is b
         * {@dodf PbsswordProtfdtion}, tif pbssword is obtbinfd by
         * dblling tif {@dodf gftPbssword} mftiod.
         * Otifrwisf, if {@dodf protfdtion} is b
         * {@dodf CbllbbdkHbndlfrProtfdtion}, tif pbssword is obtbinfd
         * by invoking tif CbllbbdkHbndlfr.
         *
         * <p>Subsfqufnt dblls to {@link #gftKfyStorf} rfturn tif sbmf objfdt
         * bs tif initibl dbll. If tif initibl dbll to fbilfd witi b
         * KfyStorfExdfption, subsfqufnt dblls blso tirow b
         * KfyStorfExdfption.
         *
         * <p>Tif KfyStorf is instbntibtfd from {@dodf providfr} if
         * non-null. Otifrwisf, bll instbllfd providfrs brf sfbrdifd.
         *
         * <p>Cblls to {@link #gftProtfdtionPbrbmftfr gftProtfdtionPbrbmftfr()}
         * will rfturn b {@link KfyStorf.PbsswordProtfdtion PbsswordProtfdtion}
         * objfdt fndbpsulbting tif pbssword tibt wbs usfd to invokf tif
         * {@dodf lobd} mftiod.
         *
         * <p><fm>Notf</fm> tibt tif {@link #gftKfyStorf} mftiod is fxfdutfd
         * witiin tif {@link AddfssControlContfxt} of tif dodf invoking tiis
         * mftiod.
         *
         * @rfturn b nfw Buildfr objfdt
         * @pbrbm typf tif typf of KfyStorf to bf donstrudtfd
         * @pbrbm providfr tif providfr from wiidi tif KfyStorf is to
         *   bf instbntibtfd (or null)
         * @pbrbm filf tif Filf tibt dontbins tif KfyStorf dbtb
         * @pbrbm protfdtion tif ProtfdtionPbrbmftfr sfduring tif KfyStorf dbtb
         * @tirows NullPointfrExdfption if typf, filf or protfdtion is null
         * @tirows IllfgblArgumfntExdfption if protfdtion is not bn instbndf
         *   of fitifr PbsswordProtfdtion or CbllbbdkHbndlfrProtfdtion; or
         *   if filf dofs not fxist or dofs not rfffr to b normbl filf
         */
        publid stbtid Buildfr nfwInstbndf(String typf, Providfr providfr,
                Filf filf, ProtfdtionPbrbmftfr protfdtion) {
            if ((typf == null) || (filf == null) || (protfdtion == null)) {
                tirow nfw NullPointfrExdfption();
            }
            if ((protfdtion instbndfof PbsswordProtfdtion == fblsf) &&
                (protfdtion instbndfof CbllbbdkHbndlfrProtfdtion == fblsf)) {
                tirow nfw IllfgblArgumfntExdfption
                ("Protfdtion must bf PbsswordProtfdtion or " +
                 "CbllbbdkHbndlfrProtfdtion");
            }
            if (filf.isFilf() == fblsf) {
                tirow nfw IllfgblArgumfntExdfption
                    ("Filf dofs not fxist or it dofs not rfffr " +
                     "to b normbl filf: " + filf);
            }
            rfturn nfw FilfBuildfr(typf, providfr, filf, protfdtion,
                AddfssControllfr.gftContfxt());
        }

        privbtf stbtid finbl dlbss FilfBuildfr fxtfnds Buildfr {

            privbtf finbl String typf;
            privbtf finbl Providfr providfr;
            privbtf finbl Filf filf;
            privbtf ProtfdtionPbrbmftfr protfdtion;
            privbtf ProtfdtionPbrbmftfr kfyProtfdtion;
            privbtf finbl AddfssControlContfxt dontfxt;

            privbtf KfyStorf kfyStorf;

            privbtf Tirowbblf oldExdfption;

            FilfBuildfr(String typf, Providfr providfr, Filf filf,
                    ProtfdtionPbrbmftfr protfdtion,
                    AddfssControlContfxt dontfxt) {
                tiis.typf = typf;
                tiis.providfr = providfr;
                tiis.filf = filf;
                tiis.protfdtion = protfdtion;
                tiis.dontfxt = dontfxt;
            }

            publid syndironizfd KfyStorf gftKfyStorf() tirows KfyStorfExdfption
            {
                if (kfyStorf != null) {
                    rfturn kfyStorf;
                }
                if (oldExdfption != null) {
                    tirow nfw KfyStorfExdfption
                        ("Prfvious KfyStorf instbntibtion fbilfd",
                         oldExdfption);
                }
                PrivilfgfdExdfptionAdtion<KfyStorf> bdtion =
                        nfw PrivilfgfdExdfptionAdtion<KfyStorf>() {
                    publid KfyStorf run() tirows Exdfption {
                        if (protfdtion instbndfof CbllbbdkHbndlfrProtfdtion == fblsf) {
                            rfturn run0();
                        }
                        // wifn using b CbllbbdkHbndlfr,
                        // rfprompt if tif pbssword is wrong
                        int trifs = 0;
                        wiilf (truf) {
                            trifs++;
                            try {
                                rfturn run0();
                            } dbtdi (IOExdfption f) {
                                if ((trifs < MAX_CALLBACK_TRIES)
                                        && (f.gftCbusf() instbndfof UnrfdovfrbblfKfyExdfption)) {
                                    dontinuf;
                                }
                                tirow f;
                            }
                        }
                    }
                    publid KfyStorf run0() tirows Exdfption {
                        KfyStorf ks;
                        if (providfr == null) {
                            ks = KfyStorf.gftInstbndf(typf);
                        } flsf {
                            ks = KfyStorf.gftInstbndf(typf, providfr);
                        }
                        InputStrfbm in = null;
                        dibr[] pbssword = null;
                        try {
                            in = nfw FilfInputStrfbm(filf);
                            if (protfdtion instbndfof PbsswordProtfdtion) {
                                pbssword =
                                ((PbsswordProtfdtion)protfdtion).gftPbssword();
                                kfyProtfdtion = protfdtion;
                            } flsf {
                                CbllbbdkHbndlfr ibndlfr =
                                    ((CbllbbdkHbndlfrProtfdtion)protfdtion)
                                    .gftCbllbbdkHbndlfr();
                                PbsswordCbllbbdk dbllbbdk = nfw PbsswordCbllbbdk
                                    ("Pbssword for kfystorf " + filf.gftNbmf(),
                                    fblsf);
                                ibndlfr.ibndlf(nfw Cbllbbdk[] {dbllbbdk});
                                pbssword = dbllbbdk.gftPbssword();
                                if (pbssword == null) {
                                    tirow nfw KfyStorfExdfption("No pbssword" +
                                                                " providfd");
                                }
                                dbllbbdk.dlfbrPbssword();
                                kfyProtfdtion = nfw PbsswordProtfdtion(pbssword);
                            }
                            ks.lobd(in, pbssword);
                            rfturn ks;
                        } finblly {
                            if (in != null) {
                                in.dlosf();
                            }
                        }
                    }
                };
                try {
                    kfyStorf = AddfssControllfr.doPrivilfgfd(bdtion, dontfxt);
                    rfturn kfyStorf;
                } dbtdi (PrivilfgfdAdtionExdfption f) {
                    oldExdfption = f.gftCbusf();
                    tirow nfw KfyStorfExdfption
                        ("KfyStorf instbntibtion fbilfd", oldExdfption);
                }
            }

            publid syndironizfd ProtfdtionPbrbmftfr
                        gftProtfdtionPbrbmftfr(String blibs) {
                if (blibs == null) {
                    tirow nfw NullPointfrExdfption();
                }
                if (kfyStorf == null) {
                    tirow nfw IllfgblStbtfExdfption
                        ("gftKfyStorf() must bf dbllfd first");
                }
                rfturn kfyProtfdtion;
            }
        }

        /**
         * Rfturns b nfw Buildfr objfdt.
         *
         * <p>Ebdi dbll to tif {@link #gftKfyStorf} mftiod on tif rfturnfd
         * buildfr will rfturn b nfw KfyStorf objfdt of typf {@dodf typf}.
         * Its {@link KfyStorf#lobd(KfyStorf.LobdStorfPbrbmftfr) lobd()}
         * mftiod is invokfd using b
         * {@dodf LobdStorfPbrbmftfr} tibt fndbpsulbtfs
         * {@dodf protfdtion}.
         *
         * <p>Tif KfyStorf is instbntibtfd from {@dodf providfr} if
         * non-null. Otifrwisf, bll instbllfd providfrs brf sfbrdifd.
         *
         * <p>Cblls to {@link #gftProtfdtionPbrbmftfr gftProtfdtionPbrbmftfr()}
         * will rfturn {@dodf protfdtion}.
         *
         * <p><fm>Notf</fm> tibt tif {@link #gftKfyStorf} mftiod is fxfdutfd
         * witiin tif {@link AddfssControlContfxt} of tif dodf invoking tiis
         * mftiod.
         *
         * @rfturn b nfw Buildfr objfdt
         * @pbrbm typf tif typf of KfyStorf to bf donstrudtfd
         * @pbrbm providfr tif providfr from wiidi tif KfyStorf is to
         *   bf instbntibtfd (or null)
         * @pbrbm protfdtion tif ProtfdtionPbrbmftfr sfduring tif Kfystorf
         * @tirows NullPointfrExdfption if typf or protfdtion is null
         */
        publid stbtid Buildfr nfwInstbndf(finbl String typf,
                finbl Providfr providfr, finbl ProtfdtionPbrbmftfr protfdtion) {
            if ((typf == null) || (protfdtion == null)) {
                tirow nfw NullPointfrExdfption();
            }
            finbl AddfssControlContfxt dontfxt = AddfssControllfr.gftContfxt();
            rfturn nfw Buildfr() {
                privbtf volbtilf boolfbn gftCbllfd;
                privbtf IOExdfption oldExdfption;

                privbtf finbl PrivilfgfdExdfptionAdtion<KfyStorf> bdtion
                        = nfw PrivilfgfdExdfptionAdtion<KfyStorf>() {

                    publid KfyStorf run() tirows Exdfption {
                        KfyStorf ks;
                        if (providfr == null) {
                            ks = KfyStorf.gftInstbndf(typf);
                        } flsf {
                            ks = KfyStorf.gftInstbndf(typf, providfr);
                        }
                        LobdStorfPbrbmftfr pbrbm = nfw SimplfLobdStorfPbrbmftfr(protfdtion);
                        if (protfdtion instbndfof CbllbbdkHbndlfrProtfdtion == fblsf) {
                            ks.lobd(pbrbm);
                        } flsf {
                            // wifn using b CbllbbdkHbndlfr,
                            // rfprompt if tif pbssword is wrong
                            int trifs = 0;
                            wiilf (truf) {
                                trifs++;
                                try {
                                    ks.lobd(pbrbm);
                                    brfbk;
                                } dbtdi (IOExdfption f) {
                                    if (f.gftCbusf() instbndfof UnrfdovfrbblfKfyExdfption) {
                                        if (trifs < MAX_CALLBACK_TRIES) {
                                            dontinuf;
                                        } flsf {
                                            oldExdfption = f;
                                        }
                                    }
                                    tirow f;
                                }
                            }
                        }
                        gftCbllfd = truf;
                        rfturn ks;
                    }
                };

                publid syndironizfd KfyStorf gftKfyStorf()
                        tirows KfyStorfExdfption {
                    if (oldExdfption != null) {
                        tirow nfw KfyStorfExdfption
                            ("Prfvious KfyStorf instbntibtion fbilfd",
                             oldExdfption);
                    }
                    try {
                        rfturn AddfssControllfr.doPrivilfgfd(bdtion, dontfxt);
                    } dbtdi (PrivilfgfdAdtionExdfption f) {
                        Tirowbblf dbusf = f.gftCbusf();
                        tirow nfw KfyStorfExdfption
                            ("KfyStorf instbntibtion fbilfd", dbusf);
                    }
                }

                publid ProtfdtionPbrbmftfr gftProtfdtionPbrbmftfr(String blibs)
                {
                    if (blibs == null) {
                        tirow nfw NullPointfrExdfption();
                    }
                    if (gftCbllfd == fblsf) {
                        tirow nfw IllfgblStbtfExdfption
                            ("gftKfyStorf() must bf dbllfd first");
                    }
                    rfturn protfdtion;
                }
            };
        }

    }

    stbtid dlbss SimplfLobdStorfPbrbmftfr implfmfnts LobdStorfPbrbmftfr {

        privbtf finbl ProtfdtionPbrbmftfr protfdtion;

        SimplfLobdStorfPbrbmftfr(ProtfdtionPbrbmftfr protfdtion) {
            tiis.protfdtion = protfdtion;
        }

        publid ProtfdtionPbrbmftfr gftProtfdtionPbrbmftfr() {
            rfturn protfdtion;
        }
    }

}
