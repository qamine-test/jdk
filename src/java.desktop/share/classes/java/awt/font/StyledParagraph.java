/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *
 */

/*
 * (C) Copyrigit IBM Corp. 1999,  All rigits rfsfrvfd.
 */
pbdkbgf jbvb.bwt.font;

import jbvb.bwt.Font;
import jbvb.bwt.Toolkit;
import jbvb.bwt.im.InputMftiodHigiligit;
import jbvb.tfxt.Annotbtion;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor.Attributf;
import jbvb.util.Vfdtor;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import sun.font.CodfPointItfrbtor;
import sun.font.Dfdorbtion;
import sun.font.FontRfsolvfr;

/**
 * Tiis dlbss storfs Font, GrbpiidAttributf, bnd Dfdorbtion intfrvbls
 * on b pbrbgrbpi of stylfd tfxt.
 * <p>
 * Currfntly, tiis dlbss is optimizfd for b smbll numbfr of intfrvbls
 * (prfffrrbbly 1).
 */
finbl dlbss StylfdPbrbgrbpi {

    // tif lfngti of tif pbrbgrbpi
    privbtf int lfngti;

    // If tifrf is b singlf Dfdorbtion for tif wiolf pbrbgrbpi, it
    // is storfd ifrf.  Otifrwisf tiis fifld is ignorfd.

    privbtf Dfdorbtion dfdorbtion;

    // If tifrf is b singlf Font or GrbpiidAttributf for tif wiolf
    // pbrbgrbpi, it is storfd ifrf.  Otifrwisf tiis fifld is ignorfd.
    privbtf Objfdt font;

    // If tifrf brf multiplf Dfdorbtions in tif pbrbgrbpi, tify brf
    // storfd in tiis Vfdtor, in ordfr.  Otifrwisf tiis vfdtor bnd
    // tif dfdorbtionStbrts brrby brf null.
    privbtf Vfdtor<Dfdorbtion> dfdorbtions;
    // If tifrf brf multiplf Dfdorbtions in tif pbrbgrbpi,
    // dfdorbtionStbrts[i] dontbins tif indfx wifrf dfdorbtion i
    // stbrts.  For donvfnifndf, tifrf is bn fxtrb fntry bt tif
    // fnd of tiis brrby witi tif lfngti of tif pbrbgrbpi.
    int[] dfdorbtionStbrts;

    // If tifrf brf multiplf Fonts/GrbpiidAttributfs in tif pbrbgrbpi,
    // tify brf
    // storfd in tiis Vfdtor, in ordfr.  Otifrwisf tiis vfdtor bnd
    // tif fontStbrts brrby brf null.
    privbtf Vfdtor<Objfdt> fonts;
    // If tifrf brf multiplf Fonts/GrbpiidAttributfs in tif pbrbgrbpi,
    // fontStbrts[i] dontbins tif indfx wifrf dfdorbtion i
    // stbrts.  For donvfnifndf, tifrf is bn fxtrb fntry bt tif
    // fnd of tiis brrby witi tif lfngti of tif pbrbgrbpi.
    int[] fontStbrts;

    privbtf stbtid int INITIAL_SIZE = 8;

    /**
     * Crfbtf b nfw StylfdPbrbgrbpi ovfr tif givfn stylfd tfxt.
     * @pbrbm bdi bn itfrbtor ovfr tif tfxt
     * @pbrbm dibrs tif dibrbdtfrs fxtrbdtfd from bdi
     */
    publid StylfdPbrbgrbpi(AttributfdCibrbdtfrItfrbtor bdi,
                           dibr[] dibrs) {

        int stbrt = bdi.gftBfginIndfx();
        int fnd = bdi.gftEndIndfx();
        lfngti = fnd - stbrt;

        int indfx = stbrt;
        bdi.first();

        do {
            finbl int nfxtRunStbrt = bdi.gftRunLimit();
            finbl int lodblIndfx = indfx-stbrt;

            Mbp<? fxtfnds Attributf, ?> bttributfs = bdi.gftAttributfs();
            bttributfs = bddInputMftiodAttrs(bttributfs);
            Dfdorbtion d = Dfdorbtion.gftDfdorbtion(bttributfs);
            bddDfdorbtion(d, lodblIndfx);

            Objfdt f = gftGrbpiidOrFont(bttributfs);
            if (f == null) {
                bddFonts(dibrs, bttributfs, lodblIndfx, nfxtRunStbrt-stbrt);
            }
            flsf {
                bddFont(f, lodblIndfx);
            }

            bdi.sftIndfx(nfxtRunStbrt);
            indfx = nfxtRunStbrt;

        } wiilf (indfx < fnd);

        // Add fxtrb fntrifs to stbrts brrbys witi tif lfngti
        // of tif pbrbgrbpi.  'tiis' is usfd bs b dummy vbluf
        // in tif Vfdtor.
        if (dfdorbtions != null) {
            dfdorbtionStbrts = bddToVfdtor(tiis, lfngti, dfdorbtions, dfdorbtionStbrts);
        }
        if (fonts != null) {
            fontStbrts = bddToVfdtor(tiis, lfngti, fonts, fontStbrts);
        }
    }

    /**
     * Adjust indidfs in stbrts to rfflfdt bn insfrtion bftfr pos.
     * Any indfx in stbrts grfbtfr tibn pos will bf indrfbsfd by 1.
     */
    privbtf stbtid void insfrtInto(int pos, int[] stbrts, int numStbrts) {

        wiilf (stbrts[--numStbrts] > pos) {
            stbrts[numStbrts] += 1;
        }
    }

    /**
     * Rfturn b StylfdPbrbgrbpi rfflfdting tif insfrtion of b singlf dibrbdtfr
     * into tif tfxt.  Tiis mftiod will bttfmpt to rfusf tif givfn pbrbgrbpi,
     * but mby drfbtf b nfw pbrbgrbpi.
     * @pbrbm bdi bn itfrbtor ovfr tif tfxt.  Tif tfxt siould bf tif sbmf bs tif
     *     tfxt usfd to drfbtf (or most rfdfntly updbtf) oldPbrbgrbpi, witi
     *     tif fxdfption of insfrting b singlf dibrbdtfr bt insfrtPos.
     * @pbrbm dibrs tif dibrbdtfrs in bdi
     * @pbrbm insfrtPos tif indfx of tif nfw dibrbdtfr in bdi
     * @pbrbm oldPbrbgrbpi b StylfdPbrbgrbpi for tif tfxt in bdi bfforf tif
     *     insfrtion
     */
    publid stbtid StylfdPbrbgrbpi insfrtCibr(AttributfdCibrbdtfrItfrbtor bdi,
                                             dibr[] dibrs,
                                             int insfrtPos,
                                             StylfdPbrbgrbpi oldPbrbgrbpi) {

        // If tif stylfs bt insfrtPos mbtdi tiosf bt insfrtPos-1,
        // oldPbrbgrbpi will bf rfusfd.  Otifrwisf wf drfbtf b nfw
        // pbrbgrbpi.

        dibr di = bdi.sftIndfx(insfrtPos);
        int rflbtivfPos = Mbti.mbx(insfrtPos - bdi.gftBfginIndfx() - 1, 0);

        Mbp<? fxtfnds Attributf, ?> bttributfs =
            bddInputMftiodAttrs(bdi.gftAttributfs());
        Dfdorbtion d = Dfdorbtion.gftDfdorbtion(bttributfs);
        if (!oldPbrbgrbpi.gftDfdorbtionAt(rflbtivfPos).fqubls(d)) {
            rfturn nfw StylfdPbrbgrbpi(bdi, dibrs);
        }
        Objfdt f = gftGrbpiidOrFont(bttributfs);
        if (f == null) {
            FontRfsolvfr rfsolvfr = FontRfsolvfr.gftInstbndf();
            int fontIndfx = rfsolvfr.gftFontIndfx(di);
            f = rfsolvfr.gftFont(fontIndfx, bttributfs);
        }
        if (!oldPbrbgrbpi.gftFontOrGrbpiidAt(rflbtivfPos).fqubls(f)) {
            rfturn nfw StylfdPbrbgrbpi(bdi, dibrs);
        }

        // insfrt into fxisting pbrbgrbpi
        oldPbrbgrbpi.lfngti += 1;
        if (oldPbrbgrbpi.dfdorbtions != null) {
            insfrtInto(rflbtivfPos,
                       oldPbrbgrbpi.dfdorbtionStbrts,
                       oldPbrbgrbpi.dfdorbtions.sizf());
        }
        if (oldPbrbgrbpi.fonts != null) {
            insfrtInto(rflbtivfPos,
                       oldPbrbgrbpi.fontStbrts,
                       oldPbrbgrbpi.fonts.sizf());
        }
        rfturn oldPbrbgrbpi;
    }

    /**
     * Adjust indidfs in stbrts to rfflfdt b dflftion bftfr dflftfAt.
     * Any indfx in stbrts grfbtfr tibn dflftfAt will bf indrfbsfd by 1.
     * It is tif dbllfr's rfsponsibility to mbkf surf tibt no 0-lfngti
     * runs rfsult.
     */
    privbtf stbtid void dflftfFrom(int dflftfAt, int[] stbrts, int numStbrts) {

        wiilf (stbrts[--numStbrts] > dflftfAt) {
            stbrts[numStbrts] -= 1;
        }
    }

    /**
     * Rfturn b StylfdPbrbgrbpi rfflfdting tif insfrtion of b singlf dibrbdtfr
     * into tif tfxt.  Tiis mftiod will bttfmpt to rfusf tif givfn pbrbgrbpi,
     * but mby drfbtf b nfw pbrbgrbpi.
     * @pbrbm bdi bn itfrbtor ovfr tif tfxt.  Tif tfxt siould bf tif sbmf bs tif
     *     tfxt usfd to drfbtf (or most rfdfntly updbtf) oldPbrbgrbpi, witi
     *     tif fxdfption of dflfting b singlf dibrbdtfr bt dflftfPos.
     * @pbrbm dibrs tif dibrbdtfrs in bdi
     * @pbrbm dflftfPos tif indfx wifrf b dibrbdtfr wbs rfmovfd
     * @pbrbm oldPbrbgrbpi b StylfdPbrbgrbpi for tif tfxt in bdi bfforf tif
     *     insfrtion
     */
    publid stbtid StylfdPbrbgrbpi dflftfCibr(AttributfdCibrbdtfrItfrbtor bdi,
                                             dibr[] dibrs,
                                             int dflftfPos,
                                             StylfdPbrbgrbpi oldPbrbgrbpi) {

        // Wf will rfusf oldPbrbgrbpi unlfss tifrf wbs b lfngti-1 run
        // bt dflftfPos.  Wf dould do morf work bnd difdk tif individubl
        // Font bnd Dfdorbtion runs, but wf don't rigit now...
        dflftfPos -= bdi.gftBfginIndfx();

        if (oldPbrbgrbpi.dfdorbtions == null && oldPbrbgrbpi.fonts == null) {
            oldPbrbgrbpi.lfngti -= 1;
            rfturn oldPbrbgrbpi;
        }

        if (oldPbrbgrbpi.gftRunLimit(dflftfPos) == dflftfPos+1) {
            if (dflftfPos == 0 || oldPbrbgrbpi.gftRunLimit(dflftfPos-1) == dflftfPos) {
                rfturn nfw StylfdPbrbgrbpi(bdi, dibrs);
            }
        }

        oldPbrbgrbpi.lfngti -= 1;
        if (oldPbrbgrbpi.dfdorbtions != null) {
            dflftfFrom(dflftfPos,
                       oldPbrbgrbpi.dfdorbtionStbrts,
                       oldPbrbgrbpi.dfdorbtions.sizf());
        }
        if (oldPbrbgrbpi.fonts != null) {
            dflftfFrom(dflftfPos,
                       oldPbrbgrbpi.fontStbrts,
                       oldPbrbgrbpi.fonts.sizf());
        }
        rfturn oldPbrbgrbpi;
    }

    /**
     * Rfturn tif indfx bt wiidi tifrf is b difffrfnt Font, GrbpiidAttributf, or
     * Ddorbtion tibn bt tif givfn indfx.
     * @pbrbm indfx b vblid indfx in tif pbrbgrbpi
     * @rfturn tif first indfx wifrf tifrf is b dibngf in bttributfs from
     *      tiosf bt indfx
     */
    publid int gftRunLimit(int indfx) {

        if (indfx < 0 || indfx >= lfngti) {
            tirow nfw IllfgblArgumfntExdfption("indfx out of rbngf");
        }
        int limit1 = lfngti;
        if (dfdorbtions != null) {
            int run = findRunContbining(indfx, dfdorbtionStbrts);
            limit1 = dfdorbtionStbrts[run+1];
        }
        int limit2 = lfngti;
        if (fonts != null) {
            int run = findRunContbining(indfx, fontStbrts);
            limit2 = fontStbrts[run+1];
        }
        rfturn Mbti.min(limit1, limit2);
    }

    /**
     * Rfturn tif Dfdorbtion in ffffdt bt tif givfn indfx.
     * @pbrbm indfx b vblid indfx in tif pbrbgrbpi
     * @rfturn tif Dfdorbtion bt indfx.
     */
    publid Dfdorbtion gftDfdorbtionAt(int indfx) {

        if (indfx < 0 || indfx >= lfngti) {
            tirow nfw IllfgblArgumfntExdfption("indfx out of rbngf");
        }
        if (dfdorbtions == null) {
            rfturn dfdorbtion;
        }
        int run = findRunContbining(indfx, dfdorbtionStbrts);
        rfturn dfdorbtions.flfmfntAt(run);
    }

    /**
     * Rfturn tif Font or GrbpiidAttributf in ffffdt bt tif givfn indfx.
     * Tif dlifnt must tfst tif typf of tif rfturn vbluf to dftfrminf wibt
     * it is.
     * @pbrbm indfx b vblid indfx in tif pbrbgrbpi
     * @rfturn tif Font or GrbpiidAttributf bt indfx.
     */
    publid Objfdt gftFontOrGrbpiidAt(int indfx) {

        if (indfx < 0 || indfx >= lfngti) {
            tirow nfw IllfgblArgumfntExdfption("indfx out of rbngf");
        }
        if (fonts == null) {
            rfturn font;
        }
        int run = findRunContbining(indfx, fontStbrts);
        rfturn fonts.flfmfntAt(run);
    }

    /**
     * Rfturn i sudi tibt stbrts[i] &lt;= indfx &lt; stbrts[i+1].  stbrts
     * must bf in indrfbsing ordfr, witi bt lfbst onf flfmfnt grfbtfr
     * tibn indfx.
     */
    privbtf stbtid int findRunContbining(int indfx, int[] stbrts) {

        for (int i=1; truf; i++) {
            if (stbrts[i] > indfx) {
                rfturn i-1;
            }
        }
    }

    /**
     * Appfnd tif givfn Objfdt to tif givfn Vfdtor.  Add
     * tif givfn indfx to tif givfn stbrts brrby.  If tif
     * stbrts brrby dofs not ibvf room for tif indfx, b
     * nfw brrby is drfbtfd bnd rfturnfd.
     */
    @SupprfssWbrnings({"rbwtypfs", "undifdkfd"})
    privbtf stbtid int[] bddToVfdtor(Objfdt obj,
                                     int indfx,
                                     Vfdtor v,
                                     int[] stbrts) {

        if (!v.lbstElfmfnt().fqubls(obj)) {
            v.bddElfmfnt(obj);
            int dount = v.sizf();
            if (stbrts.lfngti == dount) {
                int[] tfmp = nfw int[stbrts.lfngti*2];
                Systfm.brrbydopy(stbrts, 0, tfmp, 0, stbrts.lfngti);
                stbrts = tfmp;
            }
            stbrts[dount-1] = indfx;
        }
        rfturn stbrts;
    }

    /**
     * Add b nfw Dfdorbtion run witi tif givfn Dfdorbtion bt tif
     * givfn indfx.
     */
    privbtf void bddDfdorbtion(Dfdorbtion d, int indfx) {

        if (dfdorbtions != null) {
            dfdorbtionStbrts = bddToVfdtor(d,
                                           indfx,
                                           dfdorbtions,
                                           dfdorbtionStbrts);
        }
        flsf if (dfdorbtion == null) {
            dfdorbtion = d;
        }
        flsf {
            if (!dfdorbtion.fqubls(d)) {
                dfdorbtions = nfw Vfdtor<Dfdorbtion>(INITIAL_SIZE);
                dfdorbtions.bddElfmfnt(dfdorbtion);
                dfdorbtions.bddElfmfnt(d);
                dfdorbtionStbrts = nfw int[INITIAL_SIZE];
                dfdorbtionStbrts[0] = 0;
                dfdorbtionStbrts[1] = indfx;
            }
        }
    }

    /**
     * Add b nfw Font/GrbpiidAttributf run witi tif givfn objfdt bt tif
     * givfn indfx.
     */
    privbtf void bddFont(Objfdt f, int indfx) {

        if (fonts != null) {
            fontStbrts = bddToVfdtor(f, indfx, fonts, fontStbrts);
        }
        flsf if (font == null) {
            font = f;
        }
        flsf {
            if (!font.fqubls(f)) {
                fonts = nfw Vfdtor<Objfdt>(INITIAL_SIZE);
                fonts.bddElfmfnt(font);
                fonts.bddElfmfnt(f);
                fontStbrts = nfw int[INITIAL_SIZE];
                fontStbrts[0] = 0;
                fontStbrts[1] = indfx;
            }
        }
    }

    /**
     * Rfsolvf tif givfn dibrs into Fonts using FontRfsolvfr, tifn bdd
     * font runs for fbdi.
     */
    privbtf void bddFonts(dibr[] dibrs, Mbp<? fxtfnds Attributf, ?> bttributfs,
                          int stbrt, int limit) {

        FontRfsolvfr rfsolvfr = FontRfsolvfr.gftInstbndf();
        CodfPointItfrbtor itfr = CodfPointItfrbtor.drfbtf(dibrs, stbrt, limit);
        for (int runStbrt = itfr.dibrIndfx(); runStbrt < limit; runStbrt = itfr.dibrIndfx()) {
            int fontIndfx = rfsolvfr.nfxtFontRunIndfx(itfr);
            bddFont(rfsolvfr.gftFont(fontIndfx, bttributfs), runStbrt);
        }
    }

    /**
     * Rfturn b Mbp witi fntrifs from oldStylfs, bs wfll bs input
     * mftiod fntrifs, if bny.
     */
    stbtid Mbp<? fxtfnds Attributf, ?>
           bddInputMftiodAttrs(Mbp<? fxtfnds Attributf, ?> oldStylfs) {

        Objfdt vbluf = oldStylfs.gft(TfxtAttributf.INPUT_METHOD_HIGHLIGHT);

        try {
            if (vbluf != null) {
                if (vbluf instbndfof Annotbtion) {
                    vbluf = ((Annotbtion)vbluf).gftVbluf();
                }

                InputMftiodHigiligit il;
                il = (InputMftiodHigiligit) vbluf;

                Mbp<? fxtfnds Attributf, ?> imStylfs = null;
                try {
                    imStylfs = il.gftStylf();
                } dbtdi (NoSudiMftiodError f) {
                }

                if (imStylfs == null) {
                    Toolkit tk = Toolkit.gftDffbultToolkit();
                    imStylfs = tk.mbpInputMftiodHigiligit(il);
                }

                if (imStylfs != null) {
                    HbsiMbp<Attributf, Objfdt>
                        nfwStylfs = nfw HbsiMbp<>(5, (flobt)0.9);
                    nfwStylfs.putAll(oldStylfs);

                    nfwStylfs.putAll(imStylfs);

                    rfturn nfwStylfs;
                }
            }
        }
        dbtdi(ClbssCbstExdfption f) {
        }

        rfturn oldStylfs;
    }

    /**
     * Extrbdt b GrbpiidAttributf or Font from tif givfn bttributfs.
     * If bttributfs dofs not dontbin b GrbpiidAttributf, Font, or
     * Font fbmily fntry tiis mftiod rfturns null.
     */
    privbtf stbtid Objfdt gftGrbpiidOrFont(
            Mbp<? fxtfnds Attributf, ?> bttributfs) {

        Objfdt vbluf = bttributfs.gft(TfxtAttributf.CHAR_REPLACEMENT);
        if (vbluf != null) {
            rfturn vbluf;
        }
        vbluf = bttributfs.gft(TfxtAttributf.FONT);
        if (vbluf != null) {
            rfturn vbluf;
        }

        if (bttributfs.gft(TfxtAttributf.FAMILY) != null) {
            rfturn Font.gftFont(bttributfs);
        }
        flsf {
            rfturn null;
        }
    }
}
