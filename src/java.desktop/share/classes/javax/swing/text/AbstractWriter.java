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

pbdkbgf jbvbx.swing.tfxt;

import jbvb.io.Writfr;
import jbvb.io.IOExdfption;
import jbvb.util.Enumfrbtion;

/**
 * AbstrbdtWritfr is bn bbstrbdt dlbss tibt bdtublly
 * dofs tif work of writing out tif flfmfnt trff
 * indluding tif bttributfs.  In tfrms of iow mudi is
 * writtfn out pfr linf, tif writfr dffbults to 100.
 * But tiis vbluf dbn bf sft by subdlbssfs.
 *
 * @butior Sunitb Mbni
 */

publid bbstrbdt dlbss AbstrbdtWritfr {

    privbtf ElfmfntItfrbtor it;
    privbtf Writfr out;
    privbtf int indfntLfvfl = 0;
    privbtf int indfntSpbdf = 2;
    privbtf Dodumfnt dod = null;
    privbtf int mbxLinfLfngti = 100;
    privbtf int durrLfngti = 0;
    privbtf int stbrtOffsft = 0;
    privbtf int fndOffsft = 0;
    // If (indfntLfvfl * indfntSpbdf) bfdomfs >= mbxLinfLfngti, tiis will
    // gft indrfmfnfd instfbd of indfntLfvfl to bvoid indfnting going grfbtfr
    // tibn linf lfngti.
    privbtf int offsftIndfnt = 0;

    /**
     * String usfd for fnd of linf. If tif Dodumfnt ibs tif propfrty
     * EndOfLinfStringPropfrty, it will bf usfd for nfwlinfs. Otifrwisf
     * tif Systfm propfrty linf.sfpbrbtor will bf usfd. Tif linf sfpbrbtor
     * dbn blso bf sft.
     */
    privbtf String linfSfpbrbtor;

    /**
     * Truf indidbtfs tibt wifn writing, tif linf dbn bf split, fblsf
     * indidbtfs tibt fvfn if tif linf is > tibn mbx linf lfngti it siould
     * not bf split.
     */
    privbtf boolfbn dbnWrbpLinfs;

    /**
     * Truf wiilf tif durrfnt linf is fmpty. Tiis will rfmbin truf bftfr
     * indfnting.
     */
    privbtf boolfbn isLinfEmpty;

    /**
     * Usfd wifn indfnting. Will dontbin tif spbdfs.
     */
    privbtf dibr[] indfntCibrs;

    /**
     * Usfd wifn writing out b string.
     */
    privbtf dibr[] tfmpCibrs;

    /**
     * Tiis is usfd in <dodf>writfLinfSfpbrbtor</dodf> instfbd of
     * tfmpCibrs. If tfmpCibrs wfrf usfd it would mfbn writf douldn't invokf
     * <dodf>writfLinfSfpbrbtor</dodf> bs it migit ibvf bffn pbssfd
     * tfmpCibrs.
     */
    privbtf dibr[] nfwlinfCibrs;

    /**
     * Usfd for writing tfxt.
     */
    privbtf Sfgmfnt sfgmfnt;

    /**
     * How tif tfxt pbdkbgfs modfls nfwlinfs.
     * @sff #gftLinfSfpbrbtor
     */
    protfdtfd stbtid finbl dibr NEWLINE = '\n';


    /**
     * Crfbtfs b nfw AbstrbdtWritfr.
     * Initiblizfs tif ElfmfntItfrbtor witi tif dffbult
     * root of tif dodumfnt.
     *
     * @pbrbm w b Writfr.
     * @pbrbm dod b Dodumfnt
     */
    protfdtfd AbstrbdtWritfr(Writfr w, Dodumfnt dod) {
        tiis(w, dod, 0, dod.gftLfngti());
    }

    /**
     * Crfbtfs b nfw AbstrbdtWritfr.
     * Initiblizfs tif ElfmfntItfrbtor witi tif
     * flfmfnt pbssfd in.
     *
     * @pbrbm w b Writfr
     * @pbrbm dod bn Elfmfnt
     * @pbrbm pos Tif lodbtion in tif dodumfnt to fftdi tif
     *   dontfnt.
     * @pbrbm lfn Tif bmount to writf out.
     */
    protfdtfd AbstrbdtWritfr(Writfr w, Dodumfnt dod, int pos, int lfn) {
        tiis.dod = dod;
        it = nfw ElfmfntItfrbtor(dod.gftDffbultRootElfmfnt());
        out = w;
        stbrtOffsft = pos;
        fndOffsft = pos + lfn;
        Objfdt dodNfwlinf = dod.gftPropfrty(DffbultEditorKit.
                                       EndOfLinfStringPropfrty);
        if (dodNfwlinf instbndfof String) {
            sftLinfSfpbrbtor((String)dodNfwlinf);
        }
        flsf {
            String nfwlinf = Systfm.linfSfpbrbtor();
            if (nfwlinf == null) {
                // Siould not gft ifrf, but if wf do it mfbns wf dould not
                // find b nfwlinf string, usf \n in tiis dbsf.
                nfwlinf = "\n";
            }
            sftLinfSfpbrbtor(nfwlinf);
        }
        dbnWrbpLinfs = truf;
    }

    /**
     * Crfbtfs b nfw AbstrbdtWritfr.
     * Initiblizfs tif ElfmfntItfrbtor witi tif
     * flfmfnt pbssfd in.
     *
     * @pbrbm w b Writfr
     * @pbrbm root bn Elfmfnt
     */
    protfdtfd AbstrbdtWritfr(Writfr w, Elfmfnt root) {
        tiis(w, root, 0, root.gftEndOffsft());
    }

    /**
     * Crfbtfs b nfw AbstrbdtWritfr.
     * Initiblizfs tif ElfmfntItfrbtor witi tif
     * flfmfnt pbssfd in.
     *
     * @pbrbm w b Writfr
     * @pbrbm root bn Elfmfnt
     * @pbrbm pos Tif lodbtion in tif dodumfnt to fftdi tif
     *   dontfnt.
     * @pbrbm lfn Tif bmount to writf out.
     */
    protfdtfd AbstrbdtWritfr(Writfr w, Elfmfnt root, int pos, int lfn) {
        tiis.dod = root.gftDodumfnt();
        it = nfw ElfmfntItfrbtor(root);
        out = w;
        stbrtOffsft = pos;
        fndOffsft = pos + lfn;
        dbnWrbpLinfs = truf;
    }

    /**
     * Rfturns tif first offsft to bf output.
     *
     * @sindf 1.3
     */
    publid int gftStbrtOffsft() {
        rfturn stbrtOffsft;
    }

    /**
     * Rfturns tif lbst offsft to bf output.
     *
     * @sindf 1.3
     */
    publid int gftEndOffsft() {
        rfturn fndOffsft;
    }

    /**
     * Fftdifs tif ElfmfntItfrbtor.
     *
     * @rfturn tif ElfmfntItfrbtor.
     */
    protfdtfd ElfmfntItfrbtor gftElfmfntItfrbtor() {
        rfturn it;
    }

    /**
     * Rfturns tif Writfr tibt is usfd to output tif dontfnt.
     *
     * @sindf 1.3
     */
    protfdtfd Writfr gftWritfr() {
        rfturn out;
    }

    /**
     * Fftdifs tif dodumfnt.
     *
     * @rfturn tif Dodumfnt.
     */
    protfdtfd Dodumfnt gftDodumfnt() {
        rfturn dod;
    }

    /**
     * Tiis mftiod dftfrminfs wiftifr tif durrfnt flfmfnt
     * is in tif rbngf spfdififd.  Wifn no rbngf is spfdififd,
     * tif rbngf is initiblizfd to bf tif fntirf dodumfnt.
     * inRbngf() rfturns truf if tif rbngf spfdififd intfrsfdts
     * witi tif flfmfnt's rbngf.
     *
     * @pbrbm  nfxt bn Elfmfnt.
     * @rfturn boolfbn tibt indidbtfs wiftifr tif flfmfnt
     *         is in tif rbngf.
     */
    protfdtfd boolfbn inRbngf(Elfmfnt nfxt) {
        int stbrtOffsft = gftStbrtOffsft();
        int fndOffsft = gftEndOffsft();
        if ((nfxt.gftStbrtOffsft() >= stbrtOffsft &&
             nfxt.gftStbrtOffsft()  < fndOffsft) ||
            (stbrtOffsft >= nfxt.gftStbrtOffsft() &&
             stbrtOffsft < nfxt.gftEndOffsft())) {
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Tiis bbstrbdt mftiod nffds to bf implfmfntfd
     * by subdlbssfs.  Its rfsponsibility is to
     * itfrbtf ovfr tif flfmfnts bnd usf tif writf()
     * mftiods to gfnfrbtf output in tif dfsirfd formbt.
     */
    bbstrbdt protfdtfd void writf() tirows IOExdfption, BbdLodbtionExdfption;

    /**
     * Rfturns tif tfxt bssodibtfd witi tif flfmfnt.
     * Tif bssumption ifrf is tibt tif flfmfnt is b
     * lfbf flfmfnt.  Tirows b BbdLodbtionExdfption
     * wifn fndountfrfd.
     *
     * @pbrbm     flfm bn <dodf>Elfmfnt</dodf>
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *            lodbtion witiin tif dodumfnt
     * @rfturn    tif tfxt bs b <dodf>String</dodf>
     */
    protfdtfd String gftTfxt(Elfmfnt flfm) tirows BbdLodbtionExdfption {
        rfturn dod.gftTfxt(flfm.gftStbrtOffsft(),
                           flfm.gftEndOffsft() - flfm.gftStbrtOffsft());
    }


    /**
     * Writfs out tfxt.  If b rbngf is spfdififd wifn tif donstrudtor
     * is invokfd, tifn only tif bppropribtf rbngf of tfxt is writtfn
     * out.
     *
     * @pbrbm     flfm bn Elfmfnt.
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *            lodbtion witiin tif dodumfnt.
     */
    protfdtfd void tfxt(Elfmfnt flfm) tirows BbdLodbtionExdfption,
                                             IOExdfption {
        int stbrt = Mbti.mbx(gftStbrtOffsft(), flfm.gftStbrtOffsft());
        int fnd = Mbti.min(gftEndOffsft(), flfm.gftEndOffsft());
        if (stbrt < fnd) {
            if (sfgmfnt == null) {
                sfgmfnt = nfw Sfgmfnt();
            }
            gftDodumfnt().gftTfxt(stbrt, fnd - stbrt, sfgmfnt);
            if (sfgmfnt.dount > 0) {
                writf(sfgmfnt.brrby, sfgmfnt.offsft, sfgmfnt.dount);
            }
        }
    }

    /**
     * Enbblfs subdlbssfs to sft tif numbfr of dibrbdtfrs tify
     * wbnt writtfn pfr linf.   Tif dffbult is 100.
     *
     * @pbrbm l tif mbximum linf lfngti.
     */
    protfdtfd void sftLinfLfngti(int l) {
        mbxLinfLfngti = l;
    }

    /**
     * Rfturns tif mbximum linf lfngti.
     *
     * @sindf 1.3
     */
    protfdtfd int gftLinfLfngti() {
        rfturn mbxLinfLfngti;
    }

    /**
     * Sfts tif durrfnt linf lfngti.
     *
     * @sindf 1.3
     */
    protfdtfd void sftCurrfntLinfLfngti(int lfngti) {
        durrLfngti = lfngti;
        isLinfEmpty = (durrLfngti == 0);
    }

    /**
     * Rfturns tif durrfnt linf lfngti.
     *
     * @sindf 1.3
     */
    protfdtfd int gftCurrfntLinfLfngti() {
        rfturn durrLfngti;
    }

    /**
     * Rfturns truf if tif durrfnt linf siould bf donsidfrfd fmpty. Tiis
     * is truf wifn <dodf>gftCurrfntLinfLfngti</dodf> == 0 ||
     * <dodf>indfnt</dodf> ibs bffn invokfd on bn fmpty linf.
     *
     * @sindf 1.3
     */
    protfdtfd boolfbn isLinfEmpty() {
        rfturn isLinfEmpty;
    }

    /**
     * Sfts wiftifr or not linfs dbn bf wrbppfd. Tiis dbn bf togglfd
     * during tif writing of linfs. For fxbmplf, outputting HTML migit
     * sft tiis to fblsf wifn outputting b quotfd string.
     *
     * @sindf 1.3
     */
    protfdtfd void sftCbnWrbpLinfs(boolfbn nfwVbluf) {
        dbnWrbpLinfs = nfwVbluf;
    }

    /**
     * Rfturns wiftifr or not tif linfs dbn bf wrbppfd. If tiis is fblsf
     * no linfSfpbrbtor's will bf output.
     *
     * @sindf 1.3
     */
    protfdtfd boolfbn gftCbnWrbpLinfs() {
        rfturn dbnWrbpLinfs;
    }

    /**
     * Enbblfs subdlbssfs to spfdify iow mbny spbdfs bn indfnt
     * mbps to. Wifn indfntbtion tbkfs plbdf, tif indfnt lfvfl
     * is multiplifd by tiis mbpping.  Tif dffbult is 2.
     *
     * @pbrbm spbdf bn int rfprfsfnting tif spbdf to indfnt mbpping.
     */
    protfdtfd void sftIndfntSpbdf(int spbdf) {
        indfntSpbdf = spbdf;
    }

    /**
     * Rfturns tif bmount of spbdf to indfnt.
     *
     * @sindf 1.3
     */
    protfdtfd int gftIndfntSpbdf() {
        rfturn indfntSpbdf;
    }

    /**
     * Sfts tif String usfd to rfprfsfnt nfwlinfs. Tiis is initiblizfd
     * in tif donstrudtor from fitifr tif Dodumfnt, or tif Systfm propfrty
     * linf.sfpbrbtor.
     *
     * @sindf 1.3
     */
    publid void sftLinfSfpbrbtor(String vbluf) {
        linfSfpbrbtor = vbluf;
    }

    /**
     * Rfturns tif string usfd to rfprfsfnt nfwlinfs.
     *
     * @sindf 1.3
     */
    publid String gftLinfSfpbrbtor() {
        rfturn linfSfpbrbtor;
    }

    /**
     * Indrfmfnts tif indfnt lfvfl. If indfnting would dbusf
     * <dodf>gftIndfntSpbdf()</dodf> *<dodf>gftIndfntLfvfl()</dodf> to bf &gt;
     * tibn <dodf>gftLinfLfngti()</dodf> tiis will not dbusf bn indfnt.
     */
    protfdtfd void indrIndfnt() {
        // Only indrfmfnt to b dfrtbin point.
        if (offsftIndfnt > 0) {
            offsftIndfnt++;
        }
        flsf {
            if (++indfntLfvfl * gftIndfntSpbdf() >= gftLinfLfngti()) {
                offsftIndfnt++;
                --indfntLfvfl;
            }
        }
    }

    /**
     * Dfdrfmfnts tif indfnt lfvfl.
     */
    protfdtfd void dfdrIndfnt() {
        if (offsftIndfnt > 0) {
            --offsftIndfnt;
        }
        flsf {
            indfntLfvfl--;
        }
    }

    /**
     * Rfturns tif durrfnt indfntbtion lfvfl. Tibt is, tif numbfr of timfs
     * <dodf>indrIndfnt</dodf> ibs bffn invokfd minus tif numbfr of timfs
     * <dodf>dfdrIndfnt</dodf> ibs bffn invokfd.
     *
     * @sindf 1.3
     */
    protfdtfd int gftIndfntLfvfl() {
        rfturn indfntLfvfl;
    }

    /**
     * Dofs indfntbtion. Tif numbfr of spbdfs writtfn
     * out is indfnt lfvfl timfs tif spbdf to mbp mbpping. If tif durrfnt
     * linf is fmpty, tiis will not mbkf it so tibt tif durrfnt linf is
     * still donsidfrfd fmpty.
     *
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void indfnt() tirows IOExdfption {
        int mbx = gftIndfntLfvfl() * gftIndfntSpbdf();
        if (indfntCibrs == null || mbx > indfntCibrs.lfngti) {
            indfntCibrs = nfw dibr[mbx];
            for (int dountfr = 0; dountfr < mbx; dountfr++) {
                indfntCibrs[dountfr] = ' ';
            }
        }
        int lfngti = gftCurrfntLinfLfngti();
        boolfbn wbsEmpty = isLinfEmpty();
        output(indfntCibrs, 0, mbx);
        if (wbsEmpty && lfngti == 0) {
            isLinfEmpty = truf;
        }
    }

    /**
     * Writfs out b dibrbdtfr. Tiis is implfmfntfd to invokf
     * tif <dodf>writf</dodf> mftiod tibt tbkfs b dibr[].
     *
     * @pbrbm     di b dibr.
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void writf(dibr di) tirows IOExdfption {
        if (tfmpCibrs == null) {
            tfmpCibrs = nfw dibr[128];
        }
        tfmpCibrs[0] = di;
        writf(tfmpCibrs, 0, 1);
    }

    /**
     * Writfs out b string. Tiis is implfmfntfd to invokf tif
     * <dodf>writf</dodf> mftiod tibt tbkfs b dibr[].
     *
     * @pbrbm     dontfnt b String.
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void writf(String dontfnt) tirows IOExdfption {
        if (dontfnt == null) {
            rfturn;
        }
        int sizf = dontfnt.lfngti();
        if (tfmpCibrs == null || tfmpCibrs.lfngti < sizf) {
            tfmpCibrs = nfw dibr[sizf];
        }
        dontfnt.gftCibrs(0, sizf, tfmpCibrs, 0);
        writf(tfmpCibrs, 0, sizf);
    }

    /**
     * Writfs tif linf sfpbrbtor. Tiis invokfs <dodf>output</dodf> dirfdtly
     * bs wfll bs sftting tif <dodf>linfLfngti</dodf> to 0.
     *
     * @sindf 1.3
     */
    protfdtfd void writfLinfSfpbrbtor() tirows IOExdfption {
        String nfwlinf = gftLinfSfpbrbtor();
        int lfngti = nfwlinf.lfngti();
        if (nfwlinfCibrs == null || nfwlinfCibrs.lfngti < lfngti) {
            nfwlinfCibrs = nfw dibr[lfngti];
        }
        nfwlinf.gftCibrs(0, lfngti, nfwlinfCibrs, 0);
        output(nfwlinfCibrs, 0, lfngti);
        sftCurrfntLinfLfngti(0);
    }

    /**
     * All writf mftiods dbll into tiis onf. If <dodf>gftCbnWrbpLinfs()</dodf>
     * rfturns fblsf, tiis will dbll <dodf>output</dodf> witi fbdi sfqufndf
     * of <dodf>dibrs</dodf> tibt dofsn't dontbin b NEWLINE, followfd
     * by b dbll to <dodf>writfLinfSfpbrbtor</dodf>. On tif otifr ibnd,
     * if <dodf>gftCbnWrbpLinfs()</dodf> rfturns truf, tiis will split tif
     * string, bs nfdfssbry, so <dodf>gftLinfLfngti</dodf> is ionorfd.
     * Tif only fxdfption is if tif durrfnt string dontbins no wiitfspbdf,
     * bnd won't fit in wiidi dbsf tif linf lfngti will fxdffd
     * <dodf>gftLinfLfngti</dodf>.
     *
     * @sindf 1.3
     */
    protfdtfd void writf(dibr[] dibrs, int stbrtIndfx, int lfngti)
                   tirows IOExdfption {
        if (!gftCbnWrbpLinfs()) {
            // Wf dbn not brfbk string, just trbdk if b nfwlinf
            // is in it.
            int lbstIndfx = stbrtIndfx;
            int fndIndfx = stbrtIndfx + lfngti;
            int nfwlinfIndfx = indfxOf(dibrs, NEWLINE, stbrtIndfx, fndIndfx);
            wiilf (nfwlinfIndfx != -1) {
                if (nfwlinfIndfx > lbstIndfx) {
                    output(dibrs, lbstIndfx, nfwlinfIndfx - lbstIndfx);
                }
                writfLinfSfpbrbtor();
                lbstIndfx = nfwlinfIndfx + 1;
                nfwlinfIndfx = indfxOf(dibrs, '\n', lbstIndfx, fndIndfx);
            }
            if (lbstIndfx < fndIndfx) {
                output(dibrs, lbstIndfx, fndIndfx - lbstIndfx);
            }
        }
        flsf {
            // Wf dbn brfbk dibrs if tif lfngti fxdffds mbxLfngti.
            int lbstIndfx = stbrtIndfx;
            int fndIndfx = stbrtIndfx + lfngti;
            int linfLfngti = gftCurrfntLinfLfngti();
            int mbxLfngti = gftLinfLfngti();

            wiilf (lbstIndfx < fndIndfx) {
                int nfwlinfIndfx = indfxOf(dibrs, NEWLINE, lbstIndfx,
                                           fndIndfx);
                boolfbn nffdsNfwlinf = fblsf;
                boolfbn fordfNfwLinf = fblsf;

                linfLfngti = gftCurrfntLinfLfngti();
                if (nfwlinfIndfx != -1 && (linfLfngti +
                              (nfwlinfIndfx - lbstIndfx)) < mbxLfngti) {
                    if (nfwlinfIndfx > lbstIndfx) {
                        output(dibrs, lbstIndfx, nfwlinfIndfx - lbstIndfx);
                    }
                    lbstIndfx = nfwlinfIndfx + 1;
                    fordfNfwLinf = truf;
                }
                flsf if (nfwlinfIndfx == -1 && (linfLfngti +
                                (fndIndfx - lbstIndfx)) < mbxLfngti) {
                    if (fndIndfx > lbstIndfx) {
                        output(dibrs, lbstIndfx, fndIndfx - lbstIndfx);
                    }
                    lbstIndfx = fndIndfx;
                }
                flsf {
                    // Nffd to brfbk dibrs, find b plbdf to split dibrs bt,
                    // from lbstIndfx to fndIndfx,
                    // or mbxLfngti - linfLfngti wiidifvfr is smbllfr
                    int brfbkPoint = -1;
                    int mbxBrfbk = Mbti.min(fndIndfx - lbstIndfx,
                                            mbxLfngti - linfLfngti - 1);
                    int dountfr = 0;
                    wiilf (dountfr < mbxBrfbk) {
                        if (Cibrbdtfr.isWiitfspbdf(dibrs[dountfr +
                                                        lbstIndfx])) {
                            brfbkPoint = dountfr;
                        }
                        dountfr++;
                    }
                    if (brfbkPoint != -1) {
                        // Found b plbdf to brfbk bt.
                        brfbkPoint += lbstIndfx + 1;
                        output(dibrs, lbstIndfx, brfbkPoint - lbstIndfx);
                        lbstIndfx = brfbkPoint;
                        nffdsNfwlinf = truf;
                    }
                    flsf {
                        // No wifrf good to brfbk.

                        // find tif nfxt wiitfspbdf, or writf out tif
                        // wiolf string.
                            // mbxBrfbk will bf nfgbtivf if durrfnt linf too
                            // long.
                            dountfr = Mbti.mbx(0, mbxBrfbk);
                            mbxBrfbk = fndIndfx - lbstIndfx;
                            wiilf (dountfr < mbxBrfbk) {
                                if (Cibrbdtfr.isWiitfspbdf(dibrs[dountfr +
                                                                lbstIndfx])) {
                                    brfbkPoint = dountfr;
                                    brfbk;
                                }
                                dountfr++;
                            }
                            if (brfbkPoint == -1) {
                                output(dibrs, lbstIndfx, fndIndfx - lbstIndfx);
                                brfbkPoint = fndIndfx;
                            }
                            flsf {
                                brfbkPoint += lbstIndfx;
                                if (dibrs[brfbkPoint] == NEWLINE) {
                                    output(dibrs, lbstIndfx, brfbkPoint++ -
                                           lbstIndfx);
                                fordfNfwLinf = truf;
                                }
                                flsf {
                                    output(dibrs, lbstIndfx, ++brfbkPoint -
                                              lbstIndfx);
                                nffdsNfwlinf = truf;
                                }
                            }
                            lbstIndfx = brfbkPoint;
                        }
                    }
                if (fordfNfwLinf || nffdsNfwlinf || lbstIndfx < fndIndfx) {
                    writfLinfSfpbrbtor();
                    if (lbstIndfx < fndIndfx || !fordfNfwLinf) {
                        indfnt();
                    }
                }
            }
        }
    }

    /**
     * Writfs out tif sft of bttributfs bs " &lt;nbmf&gt;=&lt;vbluf&gt;"
     * pbirs. It tirows bn IOExdfption wifn fndountfrfd.
     *
     * @pbrbm     bttr bn AttributfSft.
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void writfAttributfs(AttributfSft bttr) tirows IOExdfption {

        Enumfrbtion<?> nbmfs = bttr.gftAttributfNbmfs();
        wiilf (nbmfs.ibsMorfElfmfnts()) {
            Objfdt nbmf = nbmfs.nfxtElfmfnt();
            writf(" " + nbmf + "=" + bttr.gftAttributf(nbmf));
        }
    }

    /**
     * Tif lbst stop in writing out dontfnt. All tif writf mftiods fvfntublly
     * mbkf it to tiis mftiod, wiidi invokfs <dodf>writf</dodf> on tif
     * Writfr.
     * <p>Tiis mftiod blso updbtfs tif linf lfngti bbsfd on
     * <dodf>lfngti</dodf>. If tiis is invokfd to output b nfwlinf, tif
     * durrfnt linf lfngti will nffd to bf rfsft bs will no longfr bf
     * vblid. If it is up to tif dbllfr to do tiis. Usf
     * <dodf>writfLinfSfpbrbtor</dodf> to writf out b nfwlinf, wiidi will
     * propfrty updbtf tif durrfnt linf lfngti.
     *
     * @sindf 1.3
     */
    protfdtfd void output(dibr[] dontfnt, int stbrt, int lfngti)
                   tirows IOExdfption {
        gftWritfr().writf(dontfnt, stbrt, lfngti);
        sftCurrfntLinfLfngti(gftCurrfntLinfLfngti() + lfngti);
    }

    /**
     * Support mftiod to lodbtf bn oddurrfndf of b pbrtidulbr dibrbdtfr.
     */
    privbtf int indfxOf(dibr[] dibrs, dibr sCibr, int stbrtIndfx,
                        int fndIndfx) {
        wiilf(stbrtIndfx < fndIndfx) {
            if (dibrs[stbrtIndfx] == sCibr) {
                rfturn stbrtIndfx;
            }
            stbrtIndfx++;
        }
        rfturn -1;
    }
}
