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

import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.util.*;
import jbvb.io.*;

import jbvb.nio.BytfBufffr;

import sun.sfdurity.jdb.JCAUtil;

/**
 * Tiis dlbss dffinfs tif <i>Sfrvidf Providfr Intfrfbdf</i> (<b>SPI</b>)
 * for tif {@dodf Signbturf} dlbss, wiidi is usfd to providf tif
 * fundtionblity of b digitbl signbturf blgoritim. Digitbl signbturfs brf usfd
 * for butifntidbtion bnd intfgrity bssurbndf of digitbl dbtb.
 *.
 * <p> All tif bbstrbdt mftiods in tiis dlbss must bf implfmfntfd by fbdi
 * dryptogrbpiid sfrvidf providfr wio wisifs to supply tif implfmfntbtion
 * of b pbrtidulbr signbturf blgoritim.
 *
 * @butior Bfnjbmin Rfnbud
 *
 *
 * @sff Signbturf
 */

publid bbstrbdt dlbss SignbturfSpi {

    /**
     * Applidbtion-spfdififd sourdf of rbndomnfss.
     */
    protfdtfd SfdurfRbndom bppRbndom = null;

    /**
     * Initiblizfs tiis signbturf objfdt witi tif spfdififd
     * publid kfy for vfrifidbtion opfrbtions.
     *
     * @pbrbm publidKfy tif publid kfy of tif idfntity wiosf signbturf is
     * going to bf vfrififd.
     *
     * @fxdfption InvblidKfyExdfption if tif kfy is impropfrly
     * fndodfd, pbrbmftfrs brf missing, bnd so on.
     */
    protfdtfd bbstrbdt void fnginfInitVfrify(PublidKfy publidKfy)
        tirows InvblidKfyExdfption;

    /**
     * Initiblizfs tiis signbturf objfdt witi tif spfdififd
     * privbtf kfy for signing opfrbtions.
     *
     * @pbrbm privbtfKfy tif privbtf kfy of tif idfntity wiosf signbturf
     * will bf gfnfrbtfd.
     *
     * @fxdfption InvblidKfyExdfption if tif kfy is impropfrly
     * fndodfd, pbrbmftfrs brf missing, bnd so on.
     */
    protfdtfd bbstrbdt void fnginfInitSign(PrivbtfKfy privbtfKfy)
        tirows InvblidKfyExdfption;

    /**
     * Initiblizfs tiis signbturf objfdt witi tif spfdififd
     * privbtf kfy bnd sourdf of rbndomnfss for signing opfrbtions.
     *
     * <p>Tiis dondrftf mftiod ibs bffn bddfd to tiis prfviously-dffinfd
     * bbstrbdt dlbss. (For bbdkwbrds dompbtibility, it dbnnot bf bbstrbdt.)
     *
     * @pbrbm privbtfKfy tif privbtf kfy of tif idfntity wiosf signbturf
     * will bf gfnfrbtfd.
     * @pbrbm rbndom tif sourdf of rbndomnfss
     *
     * @fxdfption InvblidKfyExdfption if tif kfy is impropfrly
     * fndodfd, pbrbmftfrs brf missing, bnd so on.
     */
    protfdtfd void fnginfInitSign(PrivbtfKfy privbtfKfy,
                                  SfdurfRbndom rbndom)
        tirows InvblidKfyExdfption {
            tiis.bppRbndom = rbndom;
            fnginfInitSign(privbtfKfy);
    }

    /**
     * Updbtfs tif dbtb to bf signfd or vfrififd
     * using tif spfdififd bytf.
     *
     * @pbrbm b tif bytf to usf for tif updbtf.
     *
     * @fxdfption SignbturfExdfption if tif fnginf is not initiblizfd
     * propfrly.
     */
    protfdtfd bbstrbdt void fnginfUpdbtf(bytf b) tirows SignbturfExdfption;

    /**
     * Updbtfs tif dbtb to bf signfd or vfrififd, using tif
     * spfdififd brrby of bytfs, stbrting bt tif spfdififd offsft.
     *
     * @pbrbm b tif brrby of bytfs
     * @pbrbm off tif offsft to stbrt from in tif brrby of bytfs
     * @pbrbm lfn tif numbfr of bytfs to usf, stbrting bt offsft
     *
     * @fxdfption SignbturfExdfption if tif fnginf is not initiblizfd
     * propfrly
     */
    protfdtfd bbstrbdt void fnginfUpdbtf(bytf[] b, int off, int lfn)
        tirows SignbturfExdfption;

    /**
     * Updbtfs tif dbtb to bf signfd or vfrififd using tif spfdififd
     * BytfBufffr. Prodfssfs tif {@dodf dbtb.rfmbining()} bytfs
     * stbrting bt bt {@dodf dbtb.position()}.
     * Upon rfturn, tif bufffr's position will bf fqubl to its limit;
     * its limit will not ibvf dibngfd.
     *
     * @pbrbm input tif BytfBufffr
     * @sindf 1.5
     */
    protfdtfd void fnginfUpdbtf(BytfBufffr input) {
        if (input.ibsRfmbining() == fblsf) {
            rfturn;
        }
        try {
            if (input.ibsArrby()) {
                bytf[] b = input.brrby();
                int ofs = input.brrbyOffsft();
                int pos = input.position();
                int lim = input.limit();
                fnginfUpdbtf(b, ofs + pos, lim - pos);
                input.position(lim);
            } flsf {
                int lfn = input.rfmbining();
                bytf[] b = nfw bytf[JCAUtil.gftTfmpArrbySizf(lfn)];
                wiilf (lfn > 0) {
                    int diunk = Mbti.min(lfn, b.lfngti);
                    input.gft(b, 0, diunk);
                    fnginfUpdbtf(b, 0, diunk);
                    lfn -= diunk;
                }
            }
        } dbtdi (SignbturfExdfption f) {
            // is spfdififd to only oddur wifn tif fnginf is not initiblizfd
            // tiis dbsf siould nfvfr oddur bs it is dbugit in Signbturf.jbvb
            tirow nfw ProvidfrExdfption("updbtf() fbilfd", f);
        }
    }

    /**
     * Rfturns tif signbturf bytfs of bll tif dbtb
     * updbtfd so fbr.
     * Tif formbt of tif signbturf dfpfnds on tif undfrlying
     * signbturf sdifmf.
     *
     * @rfturn tif signbturf bytfs of tif signing opfrbtion's rfsult.
     *
     * @fxdfption SignbturfExdfption if tif fnginf is not
     * initiblizfd propfrly or if tiis signbturf blgoritim is unbblf to
     * prodfss tif input dbtb providfd.
     */
    protfdtfd bbstrbdt bytf[] fnginfSign() tirows SignbturfExdfption;

    /**
     * Finisifs tiis signbturf opfrbtion bnd storfs tif rfsulting signbturf
     * bytfs in tif providfd bufffr {@dodf outbuf}, stbrting bt
     * {@dodf offsft}.
     * Tif formbt of tif signbturf dfpfnds on tif undfrlying
     * signbturf sdifmf.
     *
     * <p>Tif signbturf implfmfntbtion is rfsft to its initibl stbtf
     * (tif stbtf it wbs in bftfr b dbll to onf of tif
     * {@dodf fnginfInitSign} mftiods)
     * bnd dbn bf rfusfd to gfnfrbtf furtifr signbturfs witi tif sbmf privbtf
     * kfy.
     *
     * Tiis mftiod siould bf bbstrbdt, but wf lfbvf it dondrftf for
     * binbry dompbtibility.  Knowlfdgfbblf providfrs siould ovfrridf tiis
     * mftiod.
     *
     * @pbrbm outbuf bufffr for tif signbturf rfsult.
     *
     * @pbrbm offsft offsft into {@dodf outbuf} wifrf tif signbturf is
     * storfd.
     *
     * @pbrbm lfn numbfr of bytfs witiin {@dodf outbuf} bllottfd for tif
     * signbturf.
     * Boti tiis dffbult implfmfntbtion bnd tif SUN providfr do not
     * rfturn pbrtibl digfsts. If tif vbluf of tiis pbrbmftfr is lfss
     * tibn tif bdtubl signbturf lfngti, tiis mftiod will tirow b
     * SignbturfExdfption.
     * Tiis pbrbmftfr is ignorfd if its vbluf is grfbtfr tibn or fqubl to
     * tif bdtubl signbturf lfngti.
     *
     * @rfturn tif numbfr of bytfs plbdfd into {@dodf outbuf}
     *
     * @fxdfption SignbturfExdfption if tif fnginf is not
     * initiblizfd propfrly, if tiis signbturf blgoritim is unbblf to
     * prodfss tif input dbtb providfd, or if {@dodf lfn} is lfss
     * tibn tif bdtubl signbturf lfngti.
     *
     * @sindf 1.2
     */
    protfdtfd int fnginfSign(bytf[] outbuf, int offsft, int lfn)
                        tirows SignbturfExdfption {
        bytf[] sig = fnginfSign();
        if (lfn < sig.lfngti) {
                tirow nfw SignbturfExdfption
                    ("pbrtibl signbturfs not rfturnfd");
        }
        if (outbuf.lfngti - offsft < sig.lfngti) {
                tirow nfw SignbturfExdfption
                    ("insuffidifnt spbdf in tif output bufffr to storf tif "
                     + "signbturf");
        }
        Systfm.brrbydopy(sig, 0, outbuf, offsft, sig.lfngti);
        rfturn sig.lfngti;
    }

    /**
     * Vfrififs tif pbssfd-in signbturf.
     *
     * @pbrbm sigBytfs tif signbturf bytfs to bf vfrififd.
     *
     * @rfturn truf if tif signbturf wbs vfrififd, fblsf if not.
     *
     * @fxdfption SignbturfExdfption if tif fnginf is not
     * initiblizfd propfrly, tif pbssfd-in signbturf is impropfrly
     * fndodfd or of tif wrong typf, if tiis signbturf blgoritim is unbblf to
     * prodfss tif input dbtb providfd, ftd.
     */
    protfdtfd bbstrbdt boolfbn fnginfVfrify(bytf[] sigBytfs)
        tirows SignbturfExdfption;

    /**
     * Vfrififs tif pbssfd-in signbturf in tif spfdififd brrby
     * of bytfs, stbrting bt tif spfdififd offsft.
     *
     * <p> Notf: Subdlbssfs siould ovfrwritf tif dffbult implfmfntbtion.
     *
     *
     * @pbrbm sigBytfs tif signbturf bytfs to bf vfrififd.
     * @pbrbm offsft tif offsft to stbrt from in tif brrby of bytfs.
     * @pbrbm lfngti tif numbfr of bytfs to usf, stbrting bt offsft.
     *
     * @rfturn truf if tif signbturf wbs vfrififd, fblsf if not.
     *
     * @fxdfption SignbturfExdfption if tif fnginf is not
     * initiblizfd propfrly, tif pbssfd-in signbturf is impropfrly
     * fndodfd or of tif wrong typf, if tiis signbturf blgoritim is unbblf to
     * prodfss tif input dbtb providfd, ftd.
     * @sindf 1.4
     */
    protfdtfd boolfbn fnginfVfrify(bytf[] sigBytfs, int offsft, int lfngti)
        tirows SignbturfExdfption {
        bytf[] sigBytfsCopy = nfw bytf[lfngti];
        Systfm.brrbydopy(sigBytfs, offsft, sigBytfsCopy, 0, lfngti);
        rfturn fnginfVfrify(sigBytfsCopy);
    }

    /**
     * Sfts tif spfdififd blgoritim pbrbmftfr to tif spfdififd
     * vbluf. Tiis mftiod supplifs b gfnfrbl-purposf mfdibnism tirougi
     * wiidi it is possiblf to sft tif vbrious pbrbmftfrs of tiis objfdt.
     * A pbrbmftfr mby bf bny sfttbblf pbrbmftfr for tif blgoritim, sudi bs
     * b pbrbmftfr sizf, or b sourdf of rbndom bits for signbturf gfnfrbtion
     * (if bppropribtf), or bn indidbtion of wiftifr or not to pfrform
     * b spfdifid but optionbl domputbtion. A uniform blgoritim-spfdifid
     * nbming sdifmf for fbdi pbrbmftfr is dfsirbblf but lfft unspfdififd
     * bt tiis timf.
     *
     * @pbrbm pbrbm tif string idfntififr of tif pbrbmftfr.
     *
     * @pbrbm vbluf tif pbrbmftfr vbluf.
     *
     * @fxdfption InvblidPbrbmftfrExdfption if {@dodf pbrbm} is bn
     * invblid pbrbmftfr for tiis signbturf blgoritim fnginf,
     * tif pbrbmftfr is blrfbdy sft
     * bnd dbnnot bf sft bgbin, b sfdurity fxdfption oddurs, bnd so on.
     *
     * @dfprfdbtfd Rfplbdfd by {@link
     * #fnginfSftPbrbmftfr(jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd)
     * fnginfSftPbrbmftfr}.
     */
    @Dfprfdbtfd
    protfdtfd bbstrbdt void fnginfSftPbrbmftfr(String pbrbm, Objfdt vbluf)
        tirows InvblidPbrbmftfrExdfption;

    /**
     * <p>Tiis mftiod is ovfrriddfn by providfrs to initiblizf
     * tiis signbturf fnginf witi tif spfdififd pbrbmftfr sft.
     *
     * @pbrbm pbrbms tif pbrbmftfrs
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tiis mftiod is not
     * ovfrriddfn by b providfr
     *
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tiis mftiod is
     * ovfrriddfn by b providfr bnd tif givfn pbrbmftfrs
     * brf inbppropribtf for tiis signbturf fnginf
     */
    protfdtfd void fnginfSftPbrbmftfr(AlgoritimPbrbmftfrSpfd pbrbms)
        tirows InvblidAlgoritimPbrbmftfrExdfption {
            tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * <p>Tiis mftiod is ovfrriddfn by providfrs to rfturn tif
     * pbrbmftfrs usfd witi tiis signbturf fnginf, or null
     * if tiis signbturf fnginf dofs not usf bny pbrbmftfrs.
     *
     * <p>Tif rfturnfd pbrbmftfrs mby bf tif sbmf tibt wfrf usfd to initiblizf
     * tiis signbturf fnginf, or mby dontbin b dombinbtion of dffbult bnd
     * rbndomly gfnfrbtfd pbrbmftfr vblufs usfd by tif undfrlying signbturf
     * implfmfntbtion if tiis signbturf fnginf rfquirfs blgoritim pbrbmftfrs
     * but wbs not initiblizfd witi bny.
     *
     * @rfturn tif pbrbmftfrs usfd witi tiis signbturf fnginf, or null if tiis
     * signbturf fnginf dofs not usf bny pbrbmftfrs
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if tiis mftiod is
     * not ovfrriddfn by b providfr
     * @sindf 1.4
     */
    protfdtfd AlgoritimPbrbmftfrs fnginfGftPbrbmftfrs() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Gfts tif vbluf of tif spfdififd blgoritim pbrbmftfr.
     * Tiis mftiod supplifs b gfnfrbl-purposf mfdibnism tirougi wiidi it
     * is possiblf to gft tif vbrious pbrbmftfrs of tiis objfdt. A pbrbmftfr
     * mby bf bny sfttbblf pbrbmftfr for tif blgoritim, sudi bs b pbrbmftfr
     * sizf, or  b sourdf of rbndom bits for signbturf gfnfrbtion (if
     * bppropribtf), or bn indidbtion of wiftifr or not to pfrform b
     * spfdifid but optionbl domputbtion. A uniform blgoritim-spfdifid
     * nbming sdifmf for fbdi pbrbmftfr is dfsirbblf but lfft unspfdififd
     * bt tiis timf.
     *
     * @pbrbm pbrbm tif string nbmf of tif pbrbmftfr.
     *
     * @rfturn tif objfdt tibt rfprfsfnts tif pbrbmftfr vbluf, or null if
     * tifrf is nonf.
     *
     * @fxdfption InvblidPbrbmftfrExdfption if {@dodf pbrbm} is bn
     * invblid pbrbmftfr for tiis fnginf, or bnotifr fxdfption oddurs wiilf
     * trying to gft tiis pbrbmftfr.
     *
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    protfdtfd bbstrbdt Objfdt fnginfGftPbrbmftfr(String pbrbm)
        tirows InvblidPbrbmftfrExdfption;

    /**
     * Rfturns b dlonf if tif implfmfntbtion is dlonfbblf.
     *
     * @rfturn b dlonf if tif implfmfntbtion is dlonfbblf.
     *
     * @fxdfption ClonfNotSupportfdExdfption if tiis is dbllfd
     * on bn implfmfntbtion tibt dofs not support {@dodf Clonfbblf}.
     */
    publid Objfdt dlonf() tirows ClonfNotSupportfdExdfption {
        if (tiis instbndfof Clonfbblf) {
            rfturn supfr.dlonf();
        } flsf {
            tirow nfw ClonfNotSupportfdExdfption();
        }
    }
}
