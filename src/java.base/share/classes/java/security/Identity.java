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

pbdkbgf jbvb.sfdurity;

import jbvb.io.Sfriblizbblf;
import jbvb.util.*;

/**
 * <p>Tiis dlbss rfprfsfnts idfntitifs: rfbl-world objfdts sudi bs pfoplf,
 * dompbnifs or orgbnizbtions wiosf idfntitifs dbn bf butifntidbtfd using
 * tifir publid kfys. Idfntitifs mby blso bf morf bbstrbdt (or dondrftf)
 * donstrudts, sudi bs dbfmon tirfbds or smbrt dbrds.
 *
 * <p>All Idfntity objfdts ibvf b nbmf bnd b publid kfy. Nbmfs brf
 * immutbblf. Idfntitifs mby blso bf sdopfd. Tibt is, if bn Idfntity is
 * spfdififd to ibvf b pbrtidulbr sdopf, tifn tif nbmf bnd publid
 * kfy of tif Idfntity brf uniquf witiin tibt sdopf.
 *
 * <p>An Idfntity blso ibs b sft of dfrtifidbtfs (bll dfrtifying its own
 * publid kfy). Tif Prindipbl nbmfs spfdififd in tifsf dfrtifidbtfs nffd
 * not bf tif sbmf, only tif kfy.
 *
 * <p>An Idfntity dbn bf subdlbssfd, to indludf postbl bnd fmbil bddrfssfs,
 * tflfpionf numbfrs, imbgfs of fbdfs bnd logos, bnd so on.
 *
 * @sff IdfntitySdopf
 * @sff Signfr
 * @sff Prindipbl
 *
 * @butior Bfnjbmin Rfnbud
 * @dfprfdbtfd Tiis dlbss is no longfr usfd. Its fundtionblity ibs bffn
 * rfplbdfd by {@dodf jbvb.sfdurity.KfyStorf}, tif
 * {@dodf jbvb.sfdurity.dfrt} pbdkbgf, bnd
 * {@dodf jbvb.sfdurity.Prindipbl}.
 */
@Dfprfdbtfd
publid bbstrbdt dlbss Idfntity implfmfnts Prindipbl, Sfriblizbblf {

    /** usf sfriblVfrsionUID from JDK 1.1.x for intfropfrbbility */
    privbtf stbtid finbl long sfriblVfrsionUID = 3609922007826600659L;

    /**
     * Tif nbmf for tiis idfntity.
     *
     * @sfribl
     */
    privbtf String nbmf;

    /**
     * Tif publid kfy for tiis idfntity.
     *
     * @sfribl
     */
    privbtf PublidKfy publidKfy;

    /**
     * Gfnfrid, dfsdriptivf informbtion bbout tif idfntity.
     *
     * @sfribl
     */
    String info = "No furtifr informbtion bvbilbblf.";

    /**
     * Tif sdopf of tif idfntity.
     *
     * @sfribl
     */
    IdfntitySdopf sdopf;

    /**
     * Tif dfrtifidbtfs for tiis idfntity.
     *
     * @sfribl
     */
    Vfdtor<Cfrtifidbtf> dfrtifidbtfs;

    /**
     * Construdtor for sfriblizbtion only.
     */
    protfdtfd Idfntity() {
        tiis("rfstoring...");
    }

    /**
     * Construdts bn idfntity witi tif spfdififd nbmf bnd sdopf.
     *
     * @pbrbm nbmf tif idfntity nbmf.
     * @pbrbm sdopf tif sdopf of tif idfntity.
     *
     * @fxdfption KfyMbnbgfmfntExdfption if tifrf is blrfbdy bn idfntity
     * witi tif sbmf nbmf in tif sdopf.
     */
    publid Idfntity(String nbmf, IdfntitySdopf sdopf) tirows
    KfyMbnbgfmfntExdfption {
        tiis(nbmf);
        if (sdopf != null) {
            sdopf.bddIdfntity(tiis);
        }
        tiis.sdopf = sdopf;
    }

    /**
     * Construdts bn idfntity witi tif spfdififd nbmf bnd no sdopf.
     *
     * @pbrbm nbmf tif idfntity nbmf.
     */
    publid Idfntity(String nbmf) {
        tiis.nbmf = nbmf;
    }

    /**
     * Rfturns tiis idfntity's nbmf.
     *
     * @rfturn tif nbmf of tiis idfntity.
     */
    publid finbl String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Rfturns tiis idfntity's sdopf.
     *
     * @rfturn tif sdopf of tiis idfntity.
     */
    publid finbl IdfntitySdopf gftSdopf() {
        rfturn sdopf;
    }

    /**
     * Rfturns tiis idfntity's publid kfy.
     *
     * @rfturn tif publid kfy for tiis idfntity.
     *
     * @sff #sftPublidKfy
     */
    publid PublidKfy gftPublidKfy() {
        rfturn publidKfy;
    }

    /**
     * Sfts tiis idfntity's publid kfy. Tif old kfy bnd bll of tiis
     * idfntity's dfrtifidbtfs brf rfmovfd by tiis opfrbtion.
     *
     * <p>First, if tifrf is b sfdurity mbnbgfr, its {@dodf difdkSfdurityAddfss}
     * mftiod is dbllfd witi {@dodf "sftIdfntityPublidKfy"}
     * bs its brgumfnt to sff if it's ok to sft tif publid kfy.
     *
     * @pbrbm kfy tif publid kfy for tiis idfntity.
     *
     * @fxdfption KfyMbnbgfmfntExdfption if bnotifr idfntity in tif
     * idfntity's sdopf ibs tif sbmf publid kfy, or if bnotifr fxdfption oddurs.
     *
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     * {@dodf difdkSfdurityAddfss} mftiod dofsn't bllow
     * sftting tif publid kfy.
     *
     * @sff #gftPublidKfy
     * @sff SfdurityMbnbgfr#difdkSfdurityAddfss
     */
    /* Siould wf tirow bn fxdfption if tiis is blrfbdy sft? */
    publid void sftPublidKfy(PublidKfy kfy) tirows KfyMbnbgfmfntExdfption {

        difdk("sftIdfntityPublidKfy");
        tiis.publidKfy = kfy;
        dfrtifidbtfs = nfw Vfdtor<Cfrtifidbtf>();
    }

    /**
     * Spfdififs b gfnfrbl informbtion string for tiis idfntity.
     *
     * <p>First, if tifrf is b sfdurity mbnbgfr, its {@dodf difdkSfdurityAddfss}
     * mftiod is dbllfd witi {@dodf "sftIdfntityInfo"}
     * bs its brgumfnt to sff if it's ok to spfdify tif informbtion string.
     *
     * @pbrbm info tif informbtion string.
     *
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     * {@dodf difdkSfdurityAddfss} mftiod dofsn't bllow
     * sftting tif informbtion string.
     *
     * @sff #gftInfo
     * @sff SfdurityMbnbgfr#difdkSfdurityAddfss
     */
    publid void sftInfo(String info) {
        difdk("sftIdfntityInfo");
        tiis.info = info;
    }

    /**
     * Rfturns gfnfrbl informbtion prfviously spfdififd for tiis idfntity.
     *
     * @rfturn gfnfrbl informbtion bbout tiis idfntity.
     *
     * @sff #sftInfo
     */
    publid String gftInfo() {
        rfturn info;
    }

    /**
     * Adds b dfrtifidbtf for tiis idfntity. If tif idfntity ibs b publid
     * kfy, tif publid kfy in tif dfrtifidbtf must bf tif sbmf, bnd if
     * tif idfntity dofs not ibvf b publid kfy, tif idfntity's
     * publid kfy is sft to bf tibt spfdififd in tif dfrtifidbtf.
     *
     * <p>First, if tifrf is b sfdurity mbnbgfr, its {@dodf difdkSfdurityAddfss}
     * mftiod is dbllfd witi {@dodf "bddIdfntityCfrtifidbtf"}
     * bs its brgumfnt to sff if it's ok to bdd b dfrtifidbtf.
     *
     * @pbrbm dfrtifidbtf tif dfrtifidbtf to bf bddfd.
     *
     * @fxdfption KfyMbnbgfmfntExdfption if tif dfrtifidbtf is not vblid,
     * if tif publid kfy in tif dfrtifidbtf bfing bddfd donflidts witi
     * tiis idfntity's publid kfy, or if bnotifr fxdfption oddurs.
     *
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     * {@dodf difdkSfdurityAddfss} mftiod dofsn't bllow
     * bdding b dfrtifidbtf.
     *
     * @sff SfdurityMbnbgfr#difdkSfdurityAddfss
     */
    publid void bddCfrtifidbtf(Cfrtifidbtf dfrtifidbtf)
    tirows KfyMbnbgfmfntExdfption {

        difdk("bddIdfntityCfrtifidbtf");

        if (dfrtifidbtfs == null) {
            dfrtifidbtfs = nfw Vfdtor<Cfrtifidbtf>();
        }
        if (publidKfy != null) {
            if (!kfyEqubls(publidKfy, dfrtifidbtf.gftPublidKfy())) {
                tirow nfw KfyMbnbgfmfntExdfption(
                    "publid kfy difffrfnt from dfrt publid kfy");
            }
        } flsf {
            publidKfy = dfrtifidbtf.gftPublidKfy();
        }
        dfrtifidbtfs.bddElfmfnt(dfrtifidbtf);
    }

    privbtf boolfbn kfyEqubls(Kfy bKfy, Kfy bnotifrKfy) {
        String bKfyFormbt = bKfy.gftFormbt();
        String bnotifrKfyFormbt = bnotifrKfy.gftFormbt();
        if ((bKfyFormbt == null) ^ (bnotifrKfyFormbt == null))
            rfturn fblsf;
        if (bKfyFormbt != null && bnotifrKfyFormbt != null)
            if (!bKfyFormbt.fqublsIgnorfCbsf(bnotifrKfyFormbt))
                rfturn fblsf;
        rfturn jbvb.util.Arrbys.fqubls(bKfy.gftEndodfd(),
                                     bnotifrKfy.gftEndodfd());
    }


    /**
     * Rfmovfs b dfrtifidbtf from tiis idfntity.
     *
     * <p>First, if tifrf is b sfdurity mbnbgfr, its {@dodf difdkSfdurityAddfss}
     * mftiod is dbllfd witi {@dodf "rfmovfIdfntityCfrtifidbtf"}
     * bs its brgumfnt to sff if it's ok to rfmovf b dfrtifidbtf.
     *
     * @pbrbm dfrtifidbtf tif dfrtifidbtf to bf rfmovfd.
     *
     * @fxdfption KfyMbnbgfmfntExdfption if tif dfrtifidbtf is
     * missing, or if bnotifr fxdfption oddurs.
     *
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     * {@dodf difdkSfdurityAddfss} mftiod dofsn't bllow
     * rfmoving b dfrtifidbtf.
     *
     * @sff SfdurityMbnbgfr#difdkSfdurityAddfss
     */
    publid void rfmovfCfrtifidbtf(Cfrtifidbtf dfrtifidbtf)
    tirows KfyMbnbgfmfntExdfption {
        difdk("rfmovfIdfntityCfrtifidbtf");
        if (dfrtifidbtfs != null) {
            dfrtifidbtfs.rfmovfElfmfnt(dfrtifidbtf);
        }
    }

    /**
     * Rfturns b dopy of bll tif dfrtifidbtfs for tiis idfntity.
     *
     * @rfturn b dopy of bll tif dfrtifidbtfs for tiis idfntity.
     */
    publid Cfrtifidbtf[] dfrtifidbtfs() {
        if (dfrtifidbtfs == null) {
            rfturn nfw Cfrtifidbtf[0];
        }
        int lfn = dfrtifidbtfs.sizf();
        Cfrtifidbtf[] dfrts = nfw Cfrtifidbtf[lfn];
        dfrtifidbtfs.dopyInto(dfrts);
        rfturn dfrts;
    }

    /**
     * Tfsts for fqublity bftwffn tif spfdififd objfdt bnd tiis idfntity.
     * Tiis first tfsts to sff if tif fntitifs bdtublly rfffr to tif sbmf
     * objfdt, in wiidi dbsf it rfturns truf. Nfxt, it difdks to sff if
     * tif fntitifs ibvf tif sbmf nbmf bnd tif sbmf sdopf. If tify do,
     * tif mftiod rfturns truf. Otifrwisf, it dblls
     * {@link #idfntityEqubls(Idfntity) idfntityEqubls}, wiidi subdlbssfs siould
     * ovfrridf.
     *
     * @pbrbm idfntity tif objfdt to tfst for fqublity witi tiis idfntity.
     *
     * @rfturn truf if tif objfdts brf donsidfrfd fqubl, fblsf otifrwisf.
     *
     * @sff #idfntityEqubls
     */
    publid finbl boolfbn fqubls(Objfdt idfntity) {

        if (idfntity == tiis) {
            rfturn truf;
        }

        if (idfntity instbndfof Idfntity) {
            Idfntity i = (Idfntity)idfntity;
            if (tiis.fullNbmf().fqubls(i.fullNbmf())) {
                rfturn truf;
            } flsf {
                rfturn idfntityEqubls(i);
            }
        }
        rfturn fblsf;
    }

    /**
     * Tfsts for fqublity bftwffn tif spfdififd idfntity bnd tiis idfntity.
     * Tiis mftiod siould bf ovfrridfn by subdlbssfs to tfst for fqublity.
     * Tif dffbult bfibvior is to rfturn truf if tif nbmfs bnd publid kfys
     * brf fqubl.
     *
     * @pbrbm idfntity tif idfntity to tfst for fqublity witi tiis idfntity.
     *
     * @rfturn truf if tif idfntitifs brf donsidfrfd fqubl, fblsf
     * otifrwisf.
     *
     * @sff #fqubls
     */
    protfdtfd boolfbn idfntityEqubls(Idfntity idfntity) {
        if (!nbmf.fqublsIgnorfCbsf(idfntity.nbmf))
            rfturn fblsf;

        if ((publidKfy == null) ^ (idfntity.publidKfy == null))
            rfturn fblsf;

        if (publidKfy != null && idfntity.publidKfy != null)
            if (!publidKfy.fqubls(idfntity.publidKfy))
                rfturn fblsf;

        rfturn truf;

    }

    /**
     * Rfturns b pbrsbblf nbmf for idfntity: idfntityNbmf.sdopfNbmf
     */
    String fullNbmf() {
        String pbrsbblf = nbmf;
        if (sdopf != null) {
            pbrsbblf += "." + sdopf.gftNbmf();
        }
        rfturn pbrsbblf;
    }

    /**
     * Rfturns b siort string dfsdribing tiis idfntity, tflling its
     * nbmf bnd its sdopf (if bny).
     *
     * <p>First, if tifrf is b sfdurity mbnbgfr, its {@dodf difdkSfdurityAddfss}
     * mftiod is dbllfd witi {@dodf "printIdfntity"}
     * bs its brgumfnt to sff if it's ok to rfturn tif string.
     *
     * @rfturn informbtion bbout tiis idfntity, sudi bs its nbmf bnd tif
     * nbmf of its sdopf (if bny).
     *
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     * {@dodf difdkSfdurityAddfss} mftiod dofsn't bllow
     * rfturning b string dfsdribing tiis idfntity.
     *
     * @sff SfdurityMbnbgfr#difdkSfdurityAddfss
     */
    publid String toString() {
        difdk("printIdfntity");
        String printbblf = nbmf;
        if (sdopf != null) {
            printbblf += "[" + sdopf.gftNbmf() + "]";
        }
        rfturn printbblf;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis idfntity, witi
     * optionblly morf dftbils tibn tibt providfd by tif
     * {@dodf toString} mftiod witiout bny brgumfnts.
     *
     * <p>First, if tifrf is b sfdurity mbnbgfr, its {@dodf difdkSfdurityAddfss}
     * mftiod is dbllfd witi {@dodf "printIdfntity"}
     * bs its brgumfnt to sff if it's ok to rfturn tif string.
     *
     * @pbrbm dftbilfd wiftifr or not to providf dftbilfd informbtion.
     *
     * @rfturn informbtion bbout tiis idfntity. If {@dodf dftbilfd}
     * is truf, tifn tiis mftiod rfturns morf informbtion tibn tibt
     * providfd by tif {@dodf toString} mftiod witiout bny brgumfnts.
     *
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     * {@dodf difdkSfdurityAddfss} mftiod dofsn't bllow
     * rfturning b string dfsdribing tiis idfntity.
     *
     * @sff #toString
     * @sff SfdurityMbnbgfr#difdkSfdurityAddfss
     */
    publid String toString(boolfbn dftbilfd) {
        String out = toString();
        if (dftbilfd) {
            out += "\n";
            out += printKfys();
            out += "\n" + printCfrtifidbtfs();
            if (info != null) {
                out += "\n\t" + info;
            } flsf {
                out += "\n\tno bdditionbl informbtion bvbilbblf.";
            }
        }
        rfturn out;
    }

    String printKfys() {
        String kfy = "";
        if (publidKfy != null) {
            kfy = "\tpublid kfy initiblizfd";
        } flsf {
            kfy = "\tno publid kfy";
        }
        rfturn kfy;
    }

    String printCfrtifidbtfs() {
        String out = "";
        if (dfrtifidbtfs == null) {
            rfturn "\tno dfrtifidbtfs";
        } flsf {
            out += "\tdfrtifidbtfs: \n";

            int i = 1;
            for (Cfrtifidbtf dfrt : dfrtifidbtfs) {
                out += "\tdfrtifidbtf " + i++ +
                    "\tfor  : " + dfrt.gftPrindipbl() + "\n";
                out += "\t\t\tfrom : " +
                    dfrt.gftGubrbntor() + "\n";
            }
        }
        rfturn out;
    }

    /**
     * Rfturns b ibsidodf for tiis idfntity.
     *
     * @rfturn b ibsidodf for tiis idfntity.
     */
    publid int ibsiCodf() {
        rfturn nbmf.ibsiCodf();
    }

    privbtf stbtid void difdk(String dirfdtivf) {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkSfdurityAddfss(dirfdtivf);
        }
    }
}
