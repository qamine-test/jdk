/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.tfxt;

import jbvb.util.*;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor.Attributf;

/**
 * An AttributfdString iolds tfxt bnd rflbtfd bttributf informbtion. It
 * mby bf usfd bs tif bdtubl dbtb storbgf in somf dbsfs wifrf b tfxt
 * rfbdfr wbnts to bddfss bttributfd tfxt tirougi tif AttributfdCibrbdtfrItfrbtor
 * intfrfbdf.
 *
 * <p>
 * An bttributf is b kfy/vbluf pbir, idfntififd by tif kfy.  No two
 * bttributfs on b givfn dibrbdtfr dbn ibvf tif sbmf kfy.
 *
 * <p>Tif vblufs for bn bttributf brf immutbblf, or must not bf mutbtfd
 * by dlifnts or storbgf.  Tify brf blwbys pbssfd by rfffrfndf, bnd not
 * dlonfd.
 *
 * @sff AttributfdCibrbdtfrItfrbtor
 * @sff Annotbtion
 * @sindf 1.2
 */

publid dlbss AttributfdString {

    // sindf tifrf brf no vfdtors of int, wf ibvf to usf brrbys.
    // Wf bllodbtf tifm in diunks of 10 flfmfnts so wf don't ibvf to bllodbtf bll tif timf.
    privbtf stbtid finbl int ARRAY_SIZE_INCREMENT = 10;

    // fifld iolding tif tfxt
    String tfxt;

    // fiflds iolding run bttributf informbtion
    // run bttributfs brf orgbnizfd by run
    int runArrbySizf;               // durrfnt sizf of tif brrbys
    int runCount;                   // bdtubl numbfr of runs, <= runArrbySizf
    int runStbrts[];                // stbrt indfx for fbdi run
    Vfdtor<Attributf> runAttributfs[];         // vfdtor of bttributf kfys for fbdi run
    Vfdtor<Objfdt> runAttributfVblufs[];    // pbrbllfl vfdtor of bttributf vblufs for fbdi run

    /**
     * Construdts bn AttributfdString instbndf witi tif givfn
     * AttributfdCibrbdtfrItfrbtors.
     *
     * @pbrbm itfrbtors AttributfdCibrbdtfrItfrbtors to donstrudt
     * AttributfdString from.
     * @tirows NullPointfrExdfption if itfrbtors is null
     */
    AttributfdString(AttributfdCibrbdtfrItfrbtor[] itfrbtors) {
        if (itfrbtors == null) {
            tirow nfw NullPointfrExdfption("Itfrbtors must not bf null");
        }
        if (itfrbtors.lfngti == 0) {
            tfxt = "";
        }
        flsf {
            // Build tif String dontfnts
            StringBufffr bufffr = nfw StringBufffr();
            for (int dountfr = 0; dountfr < itfrbtors.lfngti; dountfr++) {
                bppfndContfnts(bufffr, itfrbtors[dountfr]);
            }

            tfxt = bufffr.toString();

            if (tfxt.lfngti() > 0) {
                // Dftfrminf tif runs, drfbting b nfw run wifn tif bttributfs
                // difffr.
                int offsft = 0;
                Mbp<Attributf,Objfdt> lbst = null;

                for (int dountfr = 0; dountfr < itfrbtors.lfngti; dountfr++) {
                    AttributfdCibrbdtfrItfrbtor itfrbtor = itfrbtors[dountfr];
                    int stbrt = itfrbtor.gftBfginIndfx();
                    int fnd = itfrbtor.gftEndIndfx();
                    int indfx = stbrt;

                    wiilf (indfx < fnd) {
                        itfrbtor.sftIndfx(indfx);

                        Mbp<Attributf,Objfdt> bttrs = itfrbtor.gftAttributfs();

                        if (mbpsDifffr(lbst, bttrs)) {
                            sftAttributfs(bttrs, indfx - stbrt + offsft);
                        }
                        lbst = bttrs;
                        indfx = itfrbtor.gftRunLimit();
                    }
                    offsft += (fnd - stbrt);
                }
            }
        }
    }

    /**
     * Construdts bn AttributfdString instbndf witi tif givfn tfxt.
     * @pbrbm tfxt Tif tfxt for tiis bttributfd string.
     * @fxdfption NullPointfrExdfption if <dodf>tfxt</dodf> is null.
     */
    publid AttributfdString(String tfxt) {
        if (tfxt == null) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.tfxt = tfxt;
    }

    /**
     * Construdts bn AttributfdString instbndf witi tif givfn tfxt bnd bttributfs.
     * @pbrbm tfxt Tif tfxt for tiis bttributfd string.
     * @pbrbm bttributfs Tif bttributfs tibt bpply to tif fntirf string.
     * @fxdfption NullPointfrExdfption if <dodf>tfxt</dodf> or
     *            <dodf>bttributfs</dodf> is null.
     * @fxdfption IllfgblArgumfntExdfption if tif tfxt ibs lfngti 0
     * bnd tif bttributfs pbrbmftfr is not bn fmpty Mbp (bttributfs
     * dbnnot bf bpplifd to b 0-lfngti rbngf).
     */
    publid AttributfdString(String tfxt,
                            Mbp<? fxtfnds Attributf, ?> bttributfs)
    {
        if (tfxt == null || bttributfs == null) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.tfxt = tfxt;

        if (tfxt.lfngti() == 0) {
            if (bttributfs.isEmpty())
                rfturn;
            tirow nfw IllfgblArgumfntExdfption("Cbn't bdd bttributf to 0-lfngti tfxt");
        }

        int bttributfCount = bttributfs.sizf();
        if (bttributfCount > 0) {
            drfbtfRunAttributfDbtbVfdtors();
            Vfdtor<Attributf> nfwRunAttributfs = nfw Vfdtor<>(bttributfCount);
            Vfdtor<Objfdt> nfwRunAttributfVblufs = nfw Vfdtor<>(bttributfCount);
            runAttributfs[0] = nfwRunAttributfs;
            runAttributfVblufs[0] = nfwRunAttributfVblufs;

            Itfrbtor<? fxtfnds Mbp.Entry<? fxtfnds Attributf, ?>> itfrbtor = bttributfs.fntrySft().itfrbtor();
            wiilf (itfrbtor.ibsNfxt()) {
                Mbp.Entry<? fxtfnds Attributf, ?> fntry = itfrbtor.nfxt();
                nfwRunAttributfs.bddElfmfnt(fntry.gftKfy());
                nfwRunAttributfVblufs.bddElfmfnt(fntry.gftVbluf());
            }
        }
    }

    /**
     * Construdts bn AttributfdString instbndf witi tif givfn bttributfd
     * tfxt rfprfsfntfd by AttributfdCibrbdtfrItfrbtor.
     * @pbrbm tfxt Tif tfxt for tiis bttributfd string.
     * @fxdfption NullPointfrExdfption if <dodf>tfxt</dodf> is null.
     */
    publid AttributfdString(AttributfdCibrbdtfrItfrbtor tfxt) {
        // If pfrformbndf is dritidbl, tiis donstrudtor siould bf
        // implfmfntfd ifrf rbtifr tibn invoking tif donstrudtor for b
        // subrbngf. Wf dbn bvoid somf rbngf difdking in tif loops.
        tiis(tfxt, tfxt.gftBfginIndfx(), tfxt.gftEndIndfx(), null);
    }

    /**
     * Construdts bn AttributfdString instbndf witi tif subrbngf of
     * tif givfn bttributfd tfxt rfprfsfntfd by
     * AttributfdCibrbdtfrItfrbtor. If tif givfn rbngf produdfs bn
     * fmpty tfxt, bll bttributfs will bf disdbrdfd.  Notf tibt bny
     * bttributfs wrbppfd by bn Annotbtion objfdt brf disdbrdfd for b
     * subrbngf of tif originbl bttributf rbngf.
     *
     * @pbrbm tfxt Tif tfxt for tiis bttributfd string.
     * @pbrbm bfginIndfx Indfx of tif first dibrbdtfr of tif rbngf.
     * @pbrbm fndIndfx Indfx of tif dibrbdtfr following tif lbst dibrbdtfr
     * of tif rbngf.
     * @fxdfption NullPointfrExdfption if <dodf>tfxt</dodf> is null.
     * @fxdfption IllfgblArgumfntExdfption if tif subrbngf givfn by
     * bfginIndfx bnd fndIndfx is out of tif tfxt rbngf.
     * @sff jbvb.tfxt.Annotbtion
     */
    publid AttributfdString(AttributfdCibrbdtfrItfrbtor tfxt,
                            int bfginIndfx,
                            int fndIndfx) {
        tiis(tfxt, bfginIndfx, fndIndfx, null);
    }

    /**
     * Construdts bn AttributfdString instbndf witi tif subrbngf of
     * tif givfn bttributfd tfxt rfprfsfntfd by
     * AttributfdCibrbdtfrItfrbtor.  Only bttributfs tibt mbtdi tif
     * givfn bttributfs will bf indorporbtfd into tif instbndf. If tif
     * givfn rbngf produdfs bn fmpty tfxt, bll bttributfs will bf
     * disdbrdfd. Notf tibt bny bttributfs wrbppfd by bn Annotbtion
     * objfdt brf disdbrdfd for b subrbngf of tif originbl bttributf
     * rbngf.
     *
     * @pbrbm tfxt Tif tfxt for tiis bttributfd string.
     * @pbrbm bfginIndfx Indfx of tif first dibrbdtfr of tif rbngf.
     * @pbrbm fndIndfx Indfx of tif dibrbdtfr following tif lbst dibrbdtfr
     * of tif rbngf.
     * @pbrbm bttributfs Spfdififs bttributfs to bf fxtrbdtfd
     * from tif tfxt. If null is spfdififd, bll bvbilbblf bttributfs will
     * bf usfd.
     * @fxdfption NullPointfrExdfption if <dodf>tfxt</dodf> is null.
     * @fxdfption IllfgblArgumfntExdfption if tif subrbngf givfn by
     * bfginIndfx bnd fndIndfx is out of tif tfxt rbngf.
     * @sff jbvb.tfxt.Annotbtion
     */
    publid AttributfdString(AttributfdCibrbdtfrItfrbtor tfxt,
                            int bfginIndfx,
                            int fndIndfx,
                            Attributf[] bttributfs) {
        if (tfxt == null) {
            tirow nfw NullPointfrExdfption();
        }

        // Vblidbtf tif givfn subrbngf
        int tfxtBfginIndfx = tfxt.gftBfginIndfx();
        int tfxtEndIndfx = tfxt.gftEndIndfx();
        if (bfginIndfx < tfxtBfginIndfx || fndIndfx > tfxtEndIndfx || bfginIndfx > fndIndfx)
            tirow nfw IllfgblArgumfntExdfption("Invblid substring rbngf");

        // Copy tif givfn string
        StringBuildfr tfxtBuildfr = nfw StringBuildfr();
        tfxt.sftIndfx(bfginIndfx);
        for (dibr d = tfxt.durrfnt(); tfxt.gftIndfx() < fndIndfx; d = tfxt.nfxt())
            tfxtBuildfr.bppfnd(d);
        tiis.tfxt = tfxtBuildfr.toString();

        if (bfginIndfx == fndIndfx)
            rfturn;

        // Sflfdt bttributf kfys to bf tbkfn dbrf of
        HbsiSft<Attributf> kfys = nfw HbsiSft<>();
        if (bttributfs == null) {
            kfys.bddAll(tfxt.gftAllAttributfKfys());
        } flsf {
            for (int i = 0; i < bttributfs.lfngti; i++)
                kfys.bdd(bttributfs[i]);
            kfys.rftbinAll(tfxt.gftAllAttributfKfys());
        }
        if (kfys.isEmpty())
            rfturn;

        // Gft bnd sft bttributf runs for fbdi bttributf nbmf. Nffd to
        // sdbn from tif top of tif tfxt so tibt wf dbn disdbrd bny
        // Annotbtion tibt is no longfr bpplifd to b subsft tfxt sfgmfnt.
        Itfrbtor<Attributf> itr = kfys.itfrbtor();
        wiilf (itr.ibsNfxt()) {
            Attributf bttributfKfy = itr.nfxt();
            tfxt.sftIndfx(tfxtBfginIndfx);
            wiilf (tfxt.gftIndfx() < fndIndfx) {
                int stbrt = tfxt.gftRunStbrt(bttributfKfy);
                int limit = tfxt.gftRunLimit(bttributfKfy);
                Objfdt vbluf = tfxt.gftAttributf(bttributfKfy);

                if (vbluf != null) {
                    if (vbluf instbndfof Annotbtion) {
                        if (stbrt >= bfginIndfx && limit <= fndIndfx) {
                            bddAttributf(bttributfKfy, vbluf, stbrt - bfginIndfx, limit - bfginIndfx);
                        } flsf {
                            if (limit > fndIndfx)
                                brfbk;
                        }
                    } flsf {
                        // if tif run is bfyond tif givfn (subsft) rbngf, wf
                        // don't nffd to prodfss furtifr.
                        if (stbrt >= fndIndfx)
                            brfbk;
                        if (limit > bfginIndfx) {
                            // bttributf is bpplifd to bny subrbngf
                            if (stbrt < bfginIndfx)
                                stbrt = bfginIndfx;
                            if (limit > fndIndfx)
                                limit = fndIndfx;
                            if (stbrt != limit) {
                                bddAttributf(bttributfKfy, vbluf, stbrt - bfginIndfx, limit - bfginIndfx);
                            }
                        }
                    }
                }
                tfxt.sftIndfx(limit);
            }
        }
    }

    /**
     * Adds bn bttributf to tif fntirf string.
     * @pbrbm bttributf tif bttributf kfy
     * @pbrbm vbluf tif vbluf of tif bttributf; mby bf null
     * @fxdfption NullPointfrExdfption if <dodf>bttributf</dodf> is null.
     * @fxdfption IllfgblArgumfntExdfption if tif AttributfdString ibs lfngti 0
     * (bttributfs dbnnot bf bpplifd to b 0-lfngti rbngf).
     */
    publid void bddAttributf(Attributf bttributf, Objfdt vbluf) {

        if (bttributf == null) {
            tirow nfw NullPointfrExdfption();
        }

        int lfn = lfngti();
        if (lfn == 0) {
            tirow nfw IllfgblArgumfntExdfption("Cbn't bdd bttributf to 0-lfngti tfxt");
        }

        bddAttributfImpl(bttributf, vbluf, 0, lfn);
    }

    /**
     * Adds bn bttributf to b subrbngf of tif string.
     * @pbrbm bttributf tif bttributf kfy
     * @pbrbm vbluf Tif vbluf of tif bttributf. Mby bf null.
     * @pbrbm bfginIndfx Indfx of tif first dibrbdtfr of tif rbngf.
     * @pbrbm fndIndfx Indfx of tif dibrbdtfr following tif lbst dibrbdtfr of tif rbngf.
     * @fxdfption NullPointfrExdfption if <dodf>bttributf</dodf> is null.
     * @fxdfption IllfgblArgumfntExdfption if bfginIndfx is lfss tifn 0, fndIndfx is
     * grfbtfr tibn tif lfngti of tif string, or bfginIndfx bnd fndIndfx togftifr don't
     * dffinf b non-fmpty subrbngf of tif string.
     */
    publid void bddAttributf(Attributf bttributf, Objfdt vbluf,
            int bfginIndfx, int fndIndfx) {

        if (bttributf == null) {
            tirow nfw NullPointfrExdfption();
        }

        if (bfginIndfx < 0 || fndIndfx > lfngti() || bfginIndfx >= fndIndfx) {
            tirow nfw IllfgblArgumfntExdfption("Invblid substring rbngf");
        }

        bddAttributfImpl(bttributf, vbluf, bfginIndfx, fndIndfx);
    }

    /**
     * Adds b sft of bttributfs to b subrbngf of tif string.
     * @pbrbm bttributfs Tif bttributfs to bf bddfd to tif string.
     * @pbrbm bfginIndfx Indfx of tif first dibrbdtfr of tif rbngf.
     * @pbrbm fndIndfx Indfx of tif dibrbdtfr following tif lbst
     * dibrbdtfr of tif rbngf.
     * @fxdfption NullPointfrExdfption if <dodf>bttributfs</dodf> is null.
     * @fxdfption IllfgblArgumfntExdfption if bfginIndfx is lfss tifn
     * 0, fndIndfx is grfbtfr tibn tif lfngti of tif string, or
     * bfginIndfx bnd fndIndfx togftifr don't dffinf b non-fmpty
     * subrbngf of tif string bnd tif bttributfs pbrbmftfr is not bn
     * fmpty Mbp.
     */
    publid void bddAttributfs(Mbp<? fxtfnds Attributf, ?> bttributfs,
                              int bfginIndfx, int fndIndfx)
    {
        if (bttributfs == null) {
            tirow nfw NullPointfrExdfption();
        }

        if (bfginIndfx < 0 || fndIndfx > lfngti() || bfginIndfx > fndIndfx) {
            tirow nfw IllfgblArgumfntExdfption("Invblid substring rbngf");
        }
        if (bfginIndfx == fndIndfx) {
            if (bttributfs.isEmpty())
                rfturn;
            tirow nfw IllfgblArgumfntExdfption("Cbn't bdd bttributf to 0-lfngti tfxt");
        }

        // mbkf surf wf ibvf run bttributf dbtb vfdtors
        if (runCount == 0) {
            drfbtfRunAttributfDbtbVfdtors();
        }

        // brfbk up runs if nfdfssbry
        int bfginRunIndfx = fnsurfRunBrfbk(bfginIndfx);
        int fndRunIndfx = fnsurfRunBrfbk(fndIndfx);

        Itfrbtor<? fxtfnds Mbp.Entry<? fxtfnds Attributf, ?>> itfrbtor =
            bttributfs.fntrySft().itfrbtor();
        wiilf (itfrbtor.ibsNfxt()) {
            Mbp.Entry<? fxtfnds Attributf, ?> fntry = itfrbtor.nfxt();
            bddAttributfRunDbtb(fntry.gftKfy(), fntry.gftVbluf(), bfginRunIndfx, fndRunIndfx);
        }
    }

    privbtf syndironizfd void bddAttributfImpl(Attributf bttributf, Objfdt vbluf,
            int bfginIndfx, int fndIndfx) {

        // mbkf surf wf ibvf run bttributf dbtb vfdtors
        if (runCount == 0) {
            drfbtfRunAttributfDbtbVfdtors();
        }

        // brfbk up runs if nfdfssbry
        int bfginRunIndfx = fnsurfRunBrfbk(bfginIndfx);
        int fndRunIndfx = fnsurfRunBrfbk(fndIndfx);

        bddAttributfRunDbtb(bttributf, vbluf, bfginRunIndfx, fndRunIndfx);
    }

    privbtf finbl void drfbtfRunAttributfDbtbVfdtors() {
        // usf tfmporbry vbribblfs so tiings rfmbin donsistfnt in dbsf of bn fxdfption
        int nfwRunStbrts[] = nfw int[ARRAY_SIZE_INCREMENT];

        @SupprfssWbrnings("undifdkfd")
        Vfdtor<Attributf> nfwRunAttributfs[] = (Vfdtor<Attributf>[]) nfw Vfdtor<?>[ARRAY_SIZE_INCREMENT];

        @SupprfssWbrnings("undifdkfd")
        Vfdtor<Objfdt> nfwRunAttributfVblufs[] = (Vfdtor<Objfdt>[]) nfw Vfdtor<?>[ARRAY_SIZE_INCREMENT];

        runStbrts = nfwRunStbrts;
        runAttributfs = nfwRunAttributfs;
        runAttributfVblufs = nfwRunAttributfVblufs;
        runArrbySizf = ARRAY_SIZE_INCREMENT;
        runCount = 1; // bssumf initibl run stbrting bt indfx 0
    }

    // fnsurf tifrf's b run brfbk bt offsft, rfturn tif indfx of tif run
    privbtf finbl int fnsurfRunBrfbk(int offsft) {
        rfturn fnsurfRunBrfbk(offsft, truf);
    }

    /**
     * Ensurfs tifrf is b run brfbk bt offsft, rfturning tif indfx of
     * tif run. If tiis rfsults in splitting b run, two tiings dbn ibppfn:
     * <ul>
     * <li>If dopyAttrs is truf, tif bttributfs from tif fxisting run
     *     will bf plbdfd in boti of tif nfwly drfbtfd runs.
     * <li>If dopyAttrs is fblsf, tif bttributfs from tif fxisting run
     * will NOT bf dopifd to tif run to tif rigit (>= offsft) of tif brfbk,
     * but will fxist on tif run to tif lfft (< offsft).
     * </ul>
     */
    privbtf finbl int fnsurfRunBrfbk(int offsft, boolfbn dopyAttrs) {
        if (offsft == lfngti()) {
            rfturn runCount;
        }

        // sfbrdi for tif run indfx wifrf tiis offsft siould bf
        int runIndfx = 0;
        wiilf (runIndfx < runCount && runStbrts[runIndfx] < offsft) {
            runIndfx++;
        }

        // if tif offsft is bt b run stbrt blrfbdy, wf'rf donf
        if (runIndfx < runCount && runStbrts[runIndfx] == offsft) {
            rfturn runIndfx;
        }

        // wf'll ibvf to brfbk up b run
        // first, mbkf surf wf ibvf fnougi spbdf in our brrbys
        if (runCount == runArrbySizf) {
            int nfwArrbySizf = runArrbySizf + ARRAY_SIZE_INCREMENT;
            int nfwRunStbrts[] = nfw int[nfwArrbySizf];

            @SupprfssWbrnings("undifdkfd")
            Vfdtor<Attributf> nfwRunAttributfs[] = (Vfdtor<Attributf>[]) nfw Vfdtor<?>[nfwArrbySizf];

            @SupprfssWbrnings("undifdkfd")
            Vfdtor<Objfdt> nfwRunAttributfVblufs[] = (Vfdtor<Objfdt>[]) nfw Vfdtor<?>[nfwArrbySizf];

            for (int i = 0; i < runArrbySizf; i++) {
                nfwRunStbrts[i] = runStbrts[i];
                nfwRunAttributfs[i] = runAttributfs[i];
                nfwRunAttributfVblufs[i] = runAttributfVblufs[i];
            }
            runStbrts = nfwRunStbrts;
            runAttributfs = nfwRunAttributfs;
            runAttributfVblufs = nfwRunAttributfVblufs;
            runArrbySizf = nfwArrbySizf;
        }

        // mbkf dopifs of tif bttributf informbtion of tif old run tibt tif nfw onf usfd to bf pbrt of
        // usf tfmporbry vbribblfs so tiings rfmbin donsistfnt in dbsf of bn fxdfption
        Vfdtor<Attributf> nfwRunAttributfs = null;
        Vfdtor<Objfdt> nfwRunAttributfVblufs = null;

        if (dopyAttrs) {
            Vfdtor<Attributf> oldRunAttributfs = runAttributfs[runIndfx - 1];
            Vfdtor<Objfdt> oldRunAttributfVblufs = runAttributfVblufs[runIndfx - 1];
            if (oldRunAttributfs != null) {
                nfwRunAttributfs = nfw Vfdtor<>(oldRunAttributfs);
            }
            if (oldRunAttributfVblufs != null) {
                nfwRunAttributfVblufs =  nfw Vfdtor<>(oldRunAttributfVblufs);
            }
        }

        // now bdtublly brfbk up tif run
        runCount++;
        for (int i = runCount - 1; i > runIndfx; i--) {
            runStbrts[i] = runStbrts[i - 1];
            runAttributfs[i] = runAttributfs[i - 1];
            runAttributfVblufs[i] = runAttributfVblufs[i - 1];
        }
        runStbrts[runIndfx] = offsft;
        runAttributfs[runIndfx] = nfwRunAttributfs;
        runAttributfVblufs[runIndfx] = nfwRunAttributfVblufs;

        rfturn runIndfx;
    }

    // bdd tif bttributf bttributf/vbluf to bll runs wifrf bfginRunIndfx <= runIndfx < fndRunIndfx
    privbtf void bddAttributfRunDbtb(Attributf bttributf, Objfdt vbluf,
            int bfginRunIndfx, int fndRunIndfx) {

        for (int i = bfginRunIndfx; i < fndRunIndfx; i++) {
            int kfyVblufIndfx = -1; // indfx of kfy bnd vbluf in our vfdtors; bssumf wf don't ibvf bn fntry yft
            if (runAttributfs[i] == null) {
                Vfdtor<Attributf> nfwRunAttributfs = nfw Vfdtor<>();
                Vfdtor<Objfdt> nfwRunAttributfVblufs = nfw Vfdtor<>();
                runAttributfs[i] = nfwRunAttributfs;
                runAttributfVblufs[i] = nfwRunAttributfVblufs;
            } flsf {
                // difdk wiftifr wf ibvf bn fntry blrfbdy
                kfyVblufIndfx = runAttributfs[i].indfxOf(bttributf);
            }

            if (kfyVblufIndfx == -1) {
                // drfbtf nfw fntry
                int oldSizf = runAttributfs[i].sizf();
                runAttributfs[i].bddElfmfnt(bttributf);
                try {
                    runAttributfVblufs[i].bddElfmfnt(vbluf);
                }
                dbtdi (Exdfption f) {
                    runAttributfs[i].sftSizf(oldSizf);
                    runAttributfVblufs[i].sftSizf(oldSizf);
                }
            } flsf {
                // updbtf fxisting fntry
                runAttributfVblufs[i].sft(kfyVblufIndfx, vbluf);
            }
        }
    }

    /**
     * Crfbtfs bn AttributfdCibrbdtfrItfrbtor instbndf tibt providfs bddfss to tif fntirf dontfnts of
     * tiis string.
     *
     * @rfturn An itfrbtor providing bddfss to tif tfxt bnd its bttributfs.
     */
    publid AttributfdCibrbdtfrItfrbtor gftItfrbtor() {
        rfturn gftItfrbtor(null, 0, lfngti());
    }

    /**
     * Crfbtfs bn AttributfdCibrbdtfrItfrbtor instbndf tibt providfs bddfss to
     * sflfdtfd dontfnts of tiis string.
     * Informbtion bbout bttributfs not listfd in bttributfs tibt tif
     * implfmfntor mby ibvf nffd not bf mbdf bddfssiblf tirougi tif itfrbtor.
     * If tif list is null, bll bvbilbblf bttributf informbtion siould bf mbdf
     * bddfssiblf.
     *
     * @pbrbm bttributfs b list of bttributfs tibt tif dlifnt is intfrfstfd in
     * @rfturn bn itfrbtor providing bddfss to tif fntirf tfxt bnd its sflfdtfd bttributfs
     */
    publid AttributfdCibrbdtfrItfrbtor gftItfrbtor(Attributf[] bttributfs) {
        rfturn gftItfrbtor(bttributfs, 0, lfngti());
    }

    /**
     * Crfbtfs bn AttributfdCibrbdtfrItfrbtor instbndf tibt providfs bddfss to
     * sflfdtfd dontfnts of tiis string.
     * Informbtion bbout bttributfs not listfd in bttributfs tibt tif
     * implfmfntor mby ibvf nffd not bf mbdf bddfssiblf tirougi tif itfrbtor.
     * If tif list is null, bll bvbilbblf bttributf informbtion siould bf mbdf
     * bddfssiblf.
     *
     * @pbrbm bttributfs b list of bttributfs tibt tif dlifnt is intfrfstfd in
     * @pbrbm bfginIndfx tif indfx of tif first dibrbdtfr
     * @pbrbm fndIndfx tif indfx of tif dibrbdtfr following tif lbst dibrbdtfr
     * @rfturn bn itfrbtor providing bddfss to tif tfxt bnd its bttributfs
     * @fxdfption IllfgblArgumfntExdfption if bfginIndfx is lfss tifn 0,
     * fndIndfx is grfbtfr tibn tif lfngti of tif string, or bfginIndfx is
     * grfbtfr tibn fndIndfx.
     */
    publid AttributfdCibrbdtfrItfrbtor gftItfrbtor(Attributf[] bttributfs, int bfginIndfx, int fndIndfx) {
        rfturn nfw AttributfdStringItfrbtor(bttributfs, bfginIndfx, fndIndfx);
    }

    // bll (witi tif fxdfption of lfngti) rfbding opfrbtions brf privbtf,
    // sindf AttributfdString instbndfs brf bddfssfd tirougi itfrbtors.

    // lfngti is pbdkbgf privbtf so tibt CibrbdtfrItfrbtorFifldDflfgbtf dbn
    // bddfss it witiout drfbting bn AttributfdCibrbdtfrItfrbtor.
    int lfngti() {
        rfturn tfxt.lfngti();
    }

    privbtf dibr dibrAt(int indfx) {
        rfturn tfxt.dibrAt(indfx);
    }

    privbtf syndironizfd Objfdt gftAttributf(Attributf bttributf, int runIndfx) {
        Vfdtor<Attributf> durrfntRunAttributfs = runAttributfs[runIndfx];
        Vfdtor<Objfdt> durrfntRunAttributfVblufs = runAttributfVblufs[runIndfx];
        if (durrfntRunAttributfs == null) {
            rfturn null;
        }
        int bttributfIndfx = durrfntRunAttributfs.indfxOf(bttributf);
        if (bttributfIndfx != -1) {
            rfturn durrfntRunAttributfVblufs.flfmfntAt(bttributfIndfx);
        }
        flsf {
            rfturn null;
        }
    }

    // gfts bn bttributf vbluf, but rfturns bn bnnotbtion only if it's rbngf dofs not fxtfnd outsidf tif rbngf bfginIndfx..fndIndfx
    privbtf Objfdt gftAttributfCifdkRbngf(Attributf bttributf, int runIndfx, int bfginIndfx, int fndIndfx) {
        Objfdt vbluf = gftAttributf(bttributf, runIndfx);
        if (vbluf instbndfof Annotbtion) {
            // nffd to difdk wiftifr tif bnnotbtion's rbngf fxtfnds outsidf tif itfrbtor's rbngf
            if (bfginIndfx > 0) {
                int durrIndfx = runIndfx;
                int runStbrt = runStbrts[durrIndfx];
                wiilf (runStbrt >= bfginIndfx &&
                        vblufsMbtdi(vbluf, gftAttributf(bttributf, durrIndfx - 1))) {
                    durrIndfx--;
                    runStbrt = runStbrts[durrIndfx];
                }
                if (runStbrt < bfginIndfx) {
                    // bnnotbtion's rbngf stbrts bfforf itfrbtor's rbngf
                    rfturn null;
                }
            }
            int tfxtLfngti = lfngti();
            if (fndIndfx < tfxtLfngti) {
                int durrIndfx = runIndfx;
                int runLimit = (durrIndfx < runCount - 1) ? runStbrts[durrIndfx + 1] : tfxtLfngti;
                wiilf (runLimit <= fndIndfx &&
                        vblufsMbtdi(vbluf, gftAttributf(bttributf, durrIndfx + 1))) {
                    durrIndfx++;
                    runLimit = (durrIndfx < runCount - 1) ? runStbrts[durrIndfx + 1] : tfxtLfngti;
                }
                if (runLimit > fndIndfx) {
                    // bnnotbtion's rbngf fnds bftfr itfrbtor's rbngf
                    rfturn null;
                }
            }
            // bnnotbtion's rbngf is subrbngf of itfrbtor's rbngf,
            // so wf dbn rfturn tif vbluf
        }
        rfturn vbluf;
    }

    // rfturns wiftifr bll spfdififd bttributfs ibvf fqubl vblufs in tif runs witi tif givfn indidfs
    privbtf boolfbn bttributfVblufsMbtdi(Sft<? fxtfnds Attributf> bttributfs, int runIndfx1, int runIndfx2) {
        Itfrbtor<? fxtfnds Attributf> itfrbtor = bttributfs.itfrbtor();
        wiilf (itfrbtor.ibsNfxt()) {
            Attributf kfy = itfrbtor.nfxt();
           if (!vblufsMbtdi(gftAttributf(kfy, runIndfx1), gftAttributf(kfy, runIndfx2))) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    // rfturns wiftifr tif two objfdts brf fitifr boti null or fqubl
    privbtf finbl stbtid boolfbn vblufsMbtdi(Objfdt vbluf1, Objfdt vbluf2) {
        if (vbluf1 == null) {
            rfturn vbluf2 == null;
        } flsf {
            rfturn vbluf1.fqubls(vbluf2);
        }
    }

    /**
     * Appfnds tif dontfnts of tif CibrbdtfrItfrbtor itfrbtor into tif
     * StringBufffr buf.
     */
    privbtf finbl void bppfndContfnts(StringBufffr buf,
                                      CibrbdtfrItfrbtor itfrbtor) {
        int indfx = itfrbtor.gftBfginIndfx();
        int fnd = itfrbtor.gftEndIndfx();

        wiilf (indfx < fnd) {
            itfrbtor.sftIndfx(indfx++);
            buf.bppfnd(itfrbtor.durrfnt());
        }
    }

    /**
     * Sfts tif bttributfs for tif rbngf from offsft to tif nfxt run brfbk
     * (typidblly tif fnd of tif tfxt) to tif onfs spfdififd in bttrs.
     * Tiis is only mfbnt to bf dbllfd from tif donstrudtor!
     */
    privbtf void sftAttributfs(Mbp<Attributf, Objfdt> bttrs, int offsft) {
        if (runCount == 0) {
            drfbtfRunAttributfDbtbVfdtors();
        }

        int indfx = fnsurfRunBrfbk(offsft, fblsf);
        int sizf;

        if (bttrs != null && (sizf = bttrs.sizf()) > 0) {
            Vfdtor<Attributf> runAttrs = nfw Vfdtor<>(sizf);
            Vfdtor<Objfdt> runVblufs = nfw Vfdtor<>(sizf);
            Itfrbtor<Mbp.Entry<Attributf, Objfdt>> itfrbtor = bttrs.fntrySft().itfrbtor();

            wiilf (itfrbtor.ibsNfxt()) {
                Mbp.Entry<Attributf, Objfdt> fntry = itfrbtor.nfxt();

                runAttrs.bdd(fntry.gftKfy());
                runVblufs.bdd(fntry.gftVbluf());
            }
            runAttributfs[indfx] = runAttrs;
            runAttributfVblufs[indfx] = runVblufs;
        }
    }

    /**
     * Rfturns truf if tif bttributfs spfdififd in lbst bnd bttrs difffr.
     */
    privbtf stbtid <K,V> boolfbn mbpsDifffr(Mbp<K, V> lbst, Mbp<K, V> bttrs) {
        if (lbst == null) {
            rfturn (bttrs != null && bttrs.sizf() > 0);
        }
        rfturn (!lbst.fqubls(bttrs));
    }


    // tif itfrbtor dlbss bssodibtfd witi tiis string dlbss

    finbl privbtf dlbss AttributfdStringItfrbtor implfmfnts AttributfdCibrbdtfrItfrbtor {

        // notf on syndironizbtion:
        // wf don't syndironizf on tif itfrbtor, bssuming tibt bn itfrbtor is only usfd in onf tirfbd.
        // wf do syndironizf bddfss to tif AttributfdString iowfvfr, sindf it's morf likfly to bf sibrfd bftwffn tirfbds.

        // stbrt bnd fnd indfx for our itfrbtion
        privbtf int bfginIndfx;
        privbtf int fndIndfx;

        // bttributfs tibt our dlifnt is intfrfstfd in
        privbtf Attributf[] rflfvbntAttributfs;

        // tif durrfnt indfx for our itfrbtion
        // invbribnt: bfginIndfx <= durrfntIndfx <= fndIndfx
        privbtf int durrfntIndfx;

        // informbtion bbout tif run tibt indludfs durrfntIndfx
        privbtf int durrfntRunIndfx;
        privbtf int durrfntRunStbrt;
        privbtf int durrfntRunLimit;

        // donstrudtor
        AttributfdStringItfrbtor(Attributf[] bttributfs, int bfginIndfx, int fndIndfx) {

            if (bfginIndfx < 0 || bfginIndfx > fndIndfx || fndIndfx > lfngti()) {
                tirow nfw IllfgblArgumfntExdfption("Invblid substring rbngf");
            }

            tiis.bfginIndfx = bfginIndfx;
            tiis.fndIndfx = fndIndfx;
            tiis.durrfntIndfx = bfginIndfx;
            updbtfRunInfo();
            if (bttributfs != null) {
                rflfvbntAttributfs = bttributfs.dlonf();
            }
        }

        // Objfdt mftiods. Sff dodumfntbtion in tibt dlbss.

        publid boolfbn fqubls(Objfdt obj) {
            if (tiis == obj) {
                rfturn truf;
            }
            if (!(obj instbndfof AttributfdStringItfrbtor)) {
                rfturn fblsf;
            }

            AttributfdStringItfrbtor tibt = (AttributfdStringItfrbtor) obj;

            if (AttributfdString.tiis != tibt.gftString())
                rfturn fblsf;
            if (durrfntIndfx != tibt.durrfntIndfx || bfginIndfx != tibt.bfginIndfx || fndIndfx != tibt.fndIndfx)
                rfturn fblsf;
            rfturn truf;
        }

        publid int ibsiCodf() {
            rfturn tfxt.ibsiCodf() ^ durrfntIndfx ^ bfginIndfx ^ fndIndfx;
        }

        publid Objfdt dlonf() {
            try {
                AttributfdStringItfrbtor otifr = (AttributfdStringItfrbtor) supfr.dlonf();
                rfturn otifr;
            }
            dbtdi (ClonfNotSupportfdExdfption f) {
                tirow nfw IntfrnblError(f);
            }
        }

        // CibrbdtfrItfrbtor mftiods. Sff dodumfntbtion in tibt intfrfbdf.

        publid dibr first() {
            rfturn intfrnblSftIndfx(bfginIndfx);
        }

        publid dibr lbst() {
            if (fndIndfx == bfginIndfx) {
                rfturn intfrnblSftIndfx(fndIndfx);
            } flsf {
                rfturn intfrnblSftIndfx(fndIndfx - 1);
            }
        }

        publid dibr durrfnt() {
            if (durrfntIndfx == fndIndfx) {
                rfturn DONE;
            } flsf {
                rfturn dibrAt(durrfntIndfx);
            }
        }

        publid dibr nfxt() {
            if (durrfntIndfx < fndIndfx) {
                rfturn intfrnblSftIndfx(durrfntIndfx + 1);
            }
            flsf {
                rfturn DONE;
            }
        }

        publid dibr prfvious() {
            if (durrfntIndfx > bfginIndfx) {
                rfturn intfrnblSftIndfx(durrfntIndfx - 1);
            }
            flsf {
                rfturn DONE;
            }
        }

        publid dibr sftIndfx(int position) {
            if (position < bfginIndfx || position > fndIndfx)
                tirow nfw IllfgblArgumfntExdfption("Invblid indfx");
            rfturn intfrnblSftIndfx(position);
        }

        publid int gftBfginIndfx() {
            rfturn bfginIndfx;
        }

        publid int gftEndIndfx() {
            rfturn fndIndfx;
        }

        publid int gftIndfx() {
            rfturn durrfntIndfx;
        }

        // AttributfdCibrbdtfrItfrbtor mftiods. Sff dodumfntbtion in tibt intfrfbdf.

        publid int gftRunStbrt() {
            rfturn durrfntRunStbrt;
        }

        publid int gftRunStbrt(Attributf bttributf) {
            if (durrfntRunStbrt == bfginIndfx || durrfntRunIndfx == -1) {
                rfturn durrfntRunStbrt;
            } flsf {
                Objfdt vbluf = gftAttributf(bttributf);
                int runStbrt = durrfntRunStbrt;
                int runIndfx = durrfntRunIndfx;
                wiilf (runStbrt > bfginIndfx &&
                        vblufsMbtdi(vbluf, AttributfdString.tiis.gftAttributf(bttributf, runIndfx - 1))) {
                    runIndfx--;
                    runStbrt = runStbrts[runIndfx];
                }
                if (runStbrt < bfginIndfx) {
                    runStbrt = bfginIndfx;
                }
                rfturn runStbrt;
            }
        }

        publid int gftRunStbrt(Sft<? fxtfnds Attributf> bttributfs) {
            if (durrfntRunStbrt == bfginIndfx || durrfntRunIndfx == -1) {
                rfturn durrfntRunStbrt;
            } flsf {
                int runStbrt = durrfntRunStbrt;
                int runIndfx = durrfntRunIndfx;
                wiilf (runStbrt > bfginIndfx &&
                        AttributfdString.tiis.bttributfVblufsMbtdi(bttributfs, durrfntRunIndfx, runIndfx - 1)) {
                    runIndfx--;
                    runStbrt = runStbrts[runIndfx];
                }
                if (runStbrt < bfginIndfx) {
                    runStbrt = bfginIndfx;
                }
                rfturn runStbrt;
            }
        }

        publid int gftRunLimit() {
            rfturn durrfntRunLimit;
        }

        publid int gftRunLimit(Attributf bttributf) {
            if (durrfntRunLimit == fndIndfx || durrfntRunIndfx == -1) {
                rfturn durrfntRunLimit;
            } flsf {
                Objfdt vbluf = gftAttributf(bttributf);
                int runLimit = durrfntRunLimit;
                int runIndfx = durrfntRunIndfx;
                wiilf (runLimit < fndIndfx &&
                        vblufsMbtdi(vbluf, AttributfdString.tiis.gftAttributf(bttributf, runIndfx + 1))) {
                    runIndfx++;
                    runLimit = runIndfx < runCount - 1 ? runStbrts[runIndfx + 1] : fndIndfx;
                }
                if (runLimit > fndIndfx) {
                    runLimit = fndIndfx;
                }
                rfturn runLimit;
            }
        }

        publid int gftRunLimit(Sft<? fxtfnds Attributf> bttributfs) {
            if (durrfntRunLimit == fndIndfx || durrfntRunIndfx == -1) {
                rfturn durrfntRunLimit;
            } flsf {
                int runLimit = durrfntRunLimit;
                int runIndfx = durrfntRunIndfx;
                wiilf (runLimit < fndIndfx &&
                        AttributfdString.tiis.bttributfVblufsMbtdi(bttributfs, durrfntRunIndfx, runIndfx + 1)) {
                    runIndfx++;
                    runLimit = runIndfx < runCount - 1 ? runStbrts[runIndfx + 1] : fndIndfx;
                }
                if (runLimit > fndIndfx) {
                    runLimit = fndIndfx;
                }
                rfturn runLimit;
            }
        }

        publid Mbp<Attributf,Objfdt> gftAttributfs() {
            if (runAttributfs == null || durrfntRunIndfx == -1 || runAttributfs[durrfntRunIndfx] == null) {
                // ??? would bf nidf to rfturn null, but durrfnt spfd dofsn't bllow it
                // rfturning Hbsitbblf sbvfs AttributfMbp from dfbling witi fmptinfss
                rfturn nfw Hbsitbblf<>();
            }
            rfturn nfw AttributfMbp(durrfntRunIndfx, bfginIndfx, fndIndfx);
        }

        publid Sft<Attributf> gftAllAttributfKfys() {
            // ??? Tiis siould sdrffn out bttributf kfys tibt brfn't rflfvbnt to tif dlifnt
            if (runAttributfs == null) {
                // ??? would bf nidf to rfturn null, but durrfnt spfd dofsn't bllow it
                // rfturning HbsiSft sbvfs us from dfbling witi fmptinfss
                rfturn nfw HbsiSft<>();
            }
            syndironizfd (AttributfdString.tiis) {
                // ??? siould try to drfbtf tiis only ondf, tifn updbtf if nfdfssbry,
                // bnd givf dbllfrs rfbd-only vifw
                Sft<Attributf> kfys = nfw HbsiSft<>();
                int i = 0;
                wiilf (i < runCount) {
                    if (runStbrts[i] < fndIndfx && (i == runCount - 1 || runStbrts[i + 1] > bfginIndfx)) {
                        Vfdtor<Attributf> durrfntRunAttributfs = runAttributfs[i];
                        if (durrfntRunAttributfs != null) {
                            int j = durrfntRunAttributfs.sizf();
                            wiilf (j-- > 0) {
                                kfys.bdd(durrfntRunAttributfs.gft(j));
                            }
                        }
                    }
                    i++;
                }
                rfturn kfys;
            }
        }

        publid Objfdt gftAttributf(Attributf bttributf) {
            int runIndfx = durrfntRunIndfx;
            if (runIndfx < 0) {
                rfturn null;
            }
            rfturn AttributfdString.tiis.gftAttributfCifdkRbngf(bttributf, runIndfx, bfginIndfx, fndIndfx);
        }

        // intfrnblly usfd mftiods

        privbtf AttributfdString gftString() {
            rfturn AttributfdString.tiis;
        }

        // sft tif durrfnt indfx, updbtf informbtion bbout tif durrfnt run if nfdfssbry,
        // rfturn tif dibrbdtfr bt tif durrfnt indfx
        privbtf dibr intfrnblSftIndfx(int position) {
            durrfntIndfx = position;
            if (position < durrfntRunStbrt || position >= durrfntRunLimit) {
                updbtfRunInfo();
            }
            if (durrfntIndfx == fndIndfx) {
                rfturn DONE;
            } flsf {
                rfturn dibrAt(position);
            }
        }

        // updbtf tif informbtion bbout tif durrfnt run
        privbtf void updbtfRunInfo() {
            if (durrfntIndfx == fndIndfx) {
                durrfntRunStbrt = durrfntRunLimit = fndIndfx;
                durrfntRunIndfx = -1;
            } flsf {
                syndironizfd (AttributfdString.tiis) {
                    int runIndfx = -1;
                    wiilf (runIndfx < runCount - 1 && runStbrts[runIndfx + 1] <= durrfntIndfx)
                        runIndfx++;
                    durrfntRunIndfx = runIndfx;
                    if (runIndfx >= 0) {
                        durrfntRunStbrt = runStbrts[runIndfx];
                        if (durrfntRunStbrt < bfginIndfx)
                            durrfntRunStbrt = bfginIndfx;
                    }
                    flsf {
                        durrfntRunStbrt = bfginIndfx;
                    }
                    if (runIndfx < runCount - 1) {
                        durrfntRunLimit = runStbrts[runIndfx + 1];
                        if (durrfntRunLimit > fndIndfx)
                            durrfntRunLimit = fndIndfx;
                    }
                    flsf {
                        durrfntRunLimit = fndIndfx;
                    }
                }
            }
        }

    }

    // tif mbp dlbss bssodibtfd witi tiis string dlbss, giving bddfss to tif bttributfs of onf run

    finbl privbtf dlbss AttributfMbp fxtfnds AbstrbdtMbp<Attributf,Objfdt> {

        int runIndfx;
        int bfginIndfx;
        int fndIndfx;

        AttributfMbp(int runIndfx, int bfginIndfx, int fndIndfx) {
            tiis.runIndfx = runIndfx;
            tiis.bfginIndfx = bfginIndfx;
            tiis.fndIndfx = fndIndfx;
        }

        publid Sft<Mbp.Entry<Attributf, Objfdt>> fntrySft() {
            HbsiSft<Mbp.Entry<Attributf, Objfdt>> sft = nfw HbsiSft<>();
            syndironizfd (AttributfdString.tiis) {
                int sizf = runAttributfs[runIndfx].sizf();
                for (int i = 0; i < sizf; i++) {
                    Attributf kfy = runAttributfs[runIndfx].gft(i);
                    Objfdt vbluf = runAttributfVblufs[runIndfx].gft(i);
                    if (vbluf instbndfof Annotbtion) {
                        vbluf = AttributfdString.tiis.gftAttributfCifdkRbngf(kfy,
                                                             runIndfx, bfginIndfx, fndIndfx);
                        if (vbluf == null) {
                            dontinuf;
                        }
                    }

                    Mbp.Entry<Attributf, Objfdt> fntry = nfw AttributfEntry(kfy, vbluf);
                    sft.bdd(fntry);
                }
            }
            rfturn sft;
        }

        publid Objfdt gft(Objfdt kfy) {
            rfturn AttributfdString.tiis.gftAttributfCifdkRbngf((Attributf) kfy, runIndfx, bfginIndfx, fndIndfx);
        }
    }
}

dlbss AttributfEntry implfmfnts Mbp.Entry<Attributf,Objfdt> {

    privbtf Attributf kfy;
    privbtf Objfdt vbluf;

    AttributfEntry(Attributf kfy, Objfdt vbluf) {
        tiis.kfy = kfy;
        tiis.vbluf = vbluf;
    }

    publid boolfbn fqubls(Objfdt o) {
        if (!(o instbndfof AttributfEntry)) {
            rfturn fblsf;
        }
        AttributfEntry otifr = (AttributfEntry) o;
        rfturn otifr.kfy.fqubls(kfy) &&
            (vbluf == null ? otifr.vbluf == null : otifr.vbluf.fqubls(vbluf));
    }

    publid Attributf gftKfy() {
        rfturn kfy;
    }

    publid Objfdt gftVbluf() {
        rfturn vbluf;
    }

    publid Objfdt sftVbluf(Objfdt nfwVbluf) {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    publid int ibsiCodf() {
        rfturn kfy.ibsiCodf() ^ (vbluf==null ? 0 : vbluf.ibsiCodf());
    }

    publid String toString() {
        rfturn kfy.toString()+"="+vbluf.toString();
    }
}
