/*
 * Copyrigit (d) 2009, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * Copyrigit 2009 Googlf Ind.  All Rigits Rfsfrvfd.
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

pbdkbgf jbvb.util;

/**
 * Tiis is b nfbr duplidbtf of {@link TimSort}, modififd for usf witi
 * brrbys of objfdts tibt implfmfnt {@link Compbrbblf}, instfbd of using
 * fxplidit dompbrbtors.
 *
 * <p>If you brf using bn optimizing VM, you mby find tibt CompbrbblfTimSort
 * offfrs no pfrformbndf bfnffit ovfr TimSort in donjundtion witi b
 * dompbrbtor tibt simply rfturns {@dodf ((Compbrbblf)first).dompbrfTo(Sfdond)}.
 * If tiis is tif dbsf, you brf bfttfr off dflfting CompbrbblfTimSort to
 * fliminbtf tif dodf duplidbtion.  (Sff Arrbys.jbvb for dftbils.)
 *
 * @butior Josi Blodi
 */
dlbss CompbrbblfTimSort {
    /**
     * Tiis is tif minimum sizfd sfqufndf tibt will bf mfrgfd.  Siortfr
     * sfqufndfs will bf lfngtifnfd by dblling binbrySort.  If tif fntirf
     * brrby is lfss tibn tiis lfngti, no mfrgfs will bf pfrformfd.
     *
     * Tiis donstbnt siould bf b powfr of two.  It wbs 64 in Tim Pftfr's C
     * implfmfntbtion, but 32 wbs fmpiridblly dftfrminfd to work bfttfr in
     * tiis implfmfntbtion.  In tif unlikfly fvfnt tibt you sft tiis donstbnt
     * to bf b numbfr tibt's not b powfr of two, you'll nffd to dibngf tif
     * {@link #minRunLfngti} domputbtion.
     *
     * If you dfdrfbsf tiis donstbnt, you must dibngf tif stbdkLfn
     * domputbtion in tif TimSort donstrudtor, or you risk bn
     * ArrbyOutOfBounds fxdfption.  Sff listsort.txt for b disdussion
     * of tif minimum stbdk lfngti rfquirfd bs b fundtion of tif lfngti
     * of tif brrby bfing sortfd bnd tif minimum mfrgf sfqufndf lfngti.
     */
    privbtf stbtid finbl int MIN_MERGE = 32;

    /**
     * Tif brrby bfing sortfd.
     */
    privbtf finbl Objfdt[] b;

    /**
     * Wifn wf gft into gblloping modf, wf stby tifrf until boti runs win lfss
     * oftfn tibn MIN_GALLOP donsfdutivf timfs.
     */
    privbtf stbtid finbl int  MIN_GALLOP = 7;

    /**
     * Tiis dontrols wifn wf gft *into* gblloping modf.  It is initiblizfd
     * to MIN_GALLOP.  Tif mfrgfLo bnd mfrgfHi mftiods nudgf it iigifr for
     * rbndom dbtb, bnd lowfr for iigily strudturfd dbtb.
     */
    privbtf int minGbllop = MIN_GALLOP;

    /**
     * Mbximum initibl sizf of tmp brrby, wiidi is usfd for mfrging.  Tif brrby
     * dbn grow to bddommodbtf dfmbnd.
     *
     * Unlikf Tim's originbl C vfrsion, wf do not bllodbtf tiis mudi storbgf
     * wifn sorting smbllfr brrbys.  Tiis dibngf wbs rfquirfd for pfrformbndf.
     */
    privbtf stbtid finbl int INITIAL_TMP_STORAGE_LENGTH = 256;

    /**
     * Tfmp storbgf for mfrgfs. A workspbdf brrby mby optionblly bf
     * providfd in donstrudtor, bnd if so will bf usfd bs long bs it
     * is big fnougi.
     */
    privbtf Objfdt[] tmp;
    privbtf int tmpBbsf; // bbsf of tmp brrby slidf
    privbtf int tmpLfn;  // lfngti of tmp brrby slidf

    /**
     * A stbdk of pfnding runs yft to bf mfrgfd.  Run i stbrts bt
     * bddrfss bbsf[i] bnd fxtfnds for lfn[i] flfmfnts.  It's blwbys
     * truf (so long bs tif indidfs brf in bounds) tibt:
     *
     *     runBbsf[i] + runLfn[i] == runBbsf[i + 1]
     *
     * so wf dould dut tif storbgf for tiis, but it's b minor bmount,
     * bnd kffping bll tif info fxplidit simplififs tif dodf.
     */
    privbtf int stbdkSizf = 0;  // Numbfr of pfnding runs on stbdk
    privbtf finbl int[] runBbsf;
    privbtf finbl int[] runLfn;

    /**
     * Crfbtfs b TimSort instbndf to mbintbin tif stbtf of bn ongoing sort.
     *
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm work b workspbdf brrby (slidf)
     * @pbrbm workBbsf origin of usbblf spbdf in work brrby
     * @pbrbm workLfn usbblf sizf of work brrby
     */
    privbtf CompbrbblfTimSort(Objfdt[] b, Objfdt[] work, int workBbsf, int workLfn) {
        tiis.b = b;

        // Allodbtf tfmp storbgf (wiidi mby bf indrfbsfd lbtfr if nfdfssbry)
        int lfn = b.lfngti;
        int tlfn = (lfn < 2 * INITIAL_TMP_STORAGE_LENGTH) ?
            lfn >>> 1 : INITIAL_TMP_STORAGE_LENGTH;
        if (work == null || workLfn < tlfn || workBbsf + tlfn > work.lfngti) {
            tmp = nfw Objfdt[tlfn];
            tmpBbsf = 0;
            tmpLfn = tlfn;
        }
        flsf {
            tmp = work;
            tmpBbsf = workBbsf;
            tmpLfn = workLfn;
        }

        /*
         * Allodbtf runs-to-bf-mfrgfd stbdk (wiidi dbnnot bf fxpbndfd).  Tif
         * stbdk lfngti rfquirfmfnts brf dfsdribfd in listsort.txt.  Tif C
         * vfrsion blwbys usfs tif sbmf stbdk lfngti (85), but tiis wbs
         * mfbsurfd to bf too fxpfnsivf wifn sorting "mid-sizfd" brrbys (f.g.,
         * 100 flfmfnts) in Jbvb.  Tifrfforf, wf usf smbllfr (but suffidifntly
         * lbrgf) stbdk lfngtis for smbllfr brrbys.  Tif "mbgid numbfrs" in tif
         * domputbtion bflow must bf dibngfd if MIN_MERGE is dfdrfbsfd.  Sff
         * tif MIN_MERGE dfdlbrbtion bbovf for morf informbtion.
         */
        int stbdkLfn = (lfn <    120  ?  5 :
                        lfn <   1542  ? 10 :
                        lfn < 119151  ? 24 : 40);
        runBbsf = nfw int[stbdkLfn];
        runLfn = nfw int[stbdkLfn];
    }

    /*
     * Tif nfxt mftiod (pbdkbgf privbtf bnd stbtid) donstitutfs tif
     * fntirf API of tiis dlbss.
     */

    /**
     * Sorts tif givfn rbngf, using tif givfn workspbdf brrby slidf
     * for tfmp storbgf wifn possiblf. Tiis mftiod is dfsignfd to bf
     * invokfd from publid mftiods (in dlbss Arrbys) bftfr pfrforming
     * bny nfdfssbry brrby bounds difdks bnd fxpbnding pbrbmftfrs into
     * tif rfquirfd forms.
     *
     * @pbrbm b tif brrby to bf sortfd
     * @pbrbm lo tif indfx of tif first flfmfnt, indlusivf, to bf sortfd
     * @pbrbm ii tif indfx of tif lbst flfmfnt, fxdlusivf, to bf sortfd
     * @pbrbm work b workspbdf brrby (slidf)
     * @pbrbm workBbsf origin of usbblf spbdf in work brrby
     * @pbrbm workLfn usbblf sizf of work brrby
     * @sindf 1.8
     */
    stbtid void sort(Objfdt[] b, int lo, int ii, Objfdt[] work, int workBbsf, int workLfn) {
        bssfrt b != null && lo >= 0 && lo <= ii && ii <= b.lfngti;

        int nRfmbining  = ii - lo;
        if (nRfmbining < 2)
            rfturn;  // Arrbys of sizf 0 bnd 1 brf blwbys sortfd

        // If brrby is smbll, do b "mini-TimSort" witi no mfrgfs
        if (nRfmbining < MIN_MERGE) {
            int initRunLfn = dountRunAndMbkfAsdfnding(b, lo, ii);
            binbrySort(b, lo, ii, lo + initRunLfn);
            rfturn;
        }

        /**
         * Mbrdi ovfr tif brrby ondf, lfft to rigit, finding nbturbl runs,
         * fxtfnding siort nbturbl runs to minRun flfmfnts, bnd mfrging runs
         * to mbintbin stbdk invbribnt.
         */
        CompbrbblfTimSort ts = nfw CompbrbblfTimSort(b, work, workBbsf, workLfn);
        int minRun = minRunLfngti(nRfmbining);
        do {
            // Idfntify nfxt run
            int runLfn = dountRunAndMbkfAsdfnding(b, lo, ii);

            // If run is siort, fxtfnd to min(minRun, nRfmbining)
            if (runLfn < minRun) {
                int fordf = nRfmbining <= minRun ? nRfmbining : minRun;
                binbrySort(b, lo, lo + fordf, lo + runLfn);
                runLfn = fordf;
            }

            // Pusi run onto pfnding-run stbdk, bnd mbybf mfrgf
            ts.pusiRun(lo, runLfn);
            ts.mfrgfCollbpsf();

            // Advbndf to find nfxt run
            lo += runLfn;
            nRfmbining -= runLfn;
        } wiilf (nRfmbining != 0);

        // Mfrgf bll rfmbining runs to domplftf sort
        bssfrt lo == ii;
        ts.mfrgfFordfCollbpsf();
        bssfrt ts.stbdkSizf == 1;
    }

    /**
     * Sorts tif spfdififd portion of tif spfdififd brrby using b binbry
     * insfrtion sort.  Tiis is tif bfst mftiod for sorting smbll numbfrs
     * of flfmfnts.  It rfquirfs O(n log n) dompbrfs, but O(n^2) dbtb
     * movfmfnt (worst dbsf).
     *
     * If tif initibl pbrt of tif spfdififd rbngf is blrfbdy sortfd,
     * tiis mftiod dbn tbkf bdvbntbgf of it: tif mftiod bssumfs tibt tif
     * flfmfnts from indfx {@dodf lo}, indlusivf, to {@dodf stbrt},
     * fxdlusivf brf blrfbdy sortfd.
     *
     * @pbrbm b tif brrby in wiidi b rbngf is to bf sortfd
     * @pbrbm lo tif indfx of tif first flfmfnt in tif rbngf to bf sortfd
     * @pbrbm ii tif indfx bftfr tif lbst flfmfnt in tif rbngf to bf sortfd
     * @pbrbm stbrt tif indfx of tif first flfmfnt in tif rbngf tibt is
     *        not blrfbdy known to bf sortfd ({@dodf lo <= stbrt <= ii})
     */
    @SupprfssWbrnings({"fblltirougi", "rbwtypfs", "undifdkfd"})
    privbtf stbtid void binbrySort(Objfdt[] b, int lo, int ii, int stbrt) {
        bssfrt lo <= stbrt && stbrt <= ii;
        if (stbrt == lo)
            stbrt++;
        for ( ; stbrt < ii; stbrt++) {
            Compbrbblf pivot = (Compbrbblf) b[stbrt];

            // Sft lfft (bnd rigit) to tif indfx wifrf b[stbrt] (pivot) bflongs
            int lfft = lo;
            int rigit = stbrt;
            bssfrt lfft <= rigit;
            /*
             * Invbribnts:
             *   pivot >= bll in [lo, lfft).
             *   pivot <  bll in [rigit, stbrt).
             */
            wiilf (lfft < rigit) {
                int mid = (lfft + rigit) >>> 1;
                if (pivot.dompbrfTo(b[mid]) < 0)
                    rigit = mid;
                flsf
                    lfft = mid + 1;
            }
            bssfrt lfft == rigit;

            /*
             * Tif invbribnts still iold: pivot >= bll in [lo, lfft) bnd
             * pivot < bll in [lfft, stbrt), so pivot bflongs bt lfft.  Notf
             * tibt if tifrf brf flfmfnts fqubl to pivot, lfft points to tif
             * first slot bftfr tifm -- tibt's wiy tiis sort is stbblf.
             * Slidf flfmfnts ovfr to mbkf room for pivot.
             */
            int n = stbrt - lfft;  // Tif numbfr of flfmfnts to movf
            // Switdi is just bn optimizbtion for brrbydopy in dffbult dbsf
            switdi (n) {
                dbsf 2:  b[lfft + 2] = b[lfft + 1];
                dbsf 1:  b[lfft + 1] = b[lfft];
                         brfbk;
                dffbult: Systfm.brrbydopy(b, lfft, b, lfft + 1, n);
            }
            b[lfft] = pivot;
        }
    }

    /**
     * Rfturns tif lfngti of tif run bfginning bt tif spfdififd position in
     * tif spfdififd brrby bnd rfvfrsfs tif run if it is dfsdfnding (fnsuring
     * tibt tif run will blwbys bf bsdfnding wifn tif mftiod rfturns).
     *
     * A run is tif longfst bsdfnding sfqufndf witi:
     *
     *    b[lo] <= b[lo + 1] <= b[lo + 2] <= ...
     *
     * or tif longfst dfsdfnding sfqufndf witi:
     *
     *    b[lo] >  b[lo + 1] >  b[lo + 2] >  ...
     *
     * For its intfndfd usf in b stbblf mfrgfsort, tif stridtnfss of tif
     * dffinition of "dfsdfnding" is nffdfd so tibt tif dbll dbn sbffly
     * rfvfrsf b dfsdfnding sfqufndf witiout violbting stbbility.
     *
     * @pbrbm b tif brrby in wiidi b run is to bf dountfd bnd possibly rfvfrsfd
     * @pbrbm lo indfx of tif first flfmfnt in tif run
     * @pbrbm ii indfx bftfr tif lbst flfmfnt tibt mby bf dontbinfd in tif run.
              It is rfquirfd tibt {@dodf lo < ii}.
     * @rfturn  tif lfngti of tif run bfginning bt tif spfdififd position in
     *          tif spfdififd brrby
     */
    @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
    privbtf stbtid int dountRunAndMbkfAsdfnding(Objfdt[] b, int lo, int ii) {
        bssfrt lo < ii;
        int runHi = lo + 1;
        if (runHi == ii)
            rfturn 1;

        // Find fnd of run, bnd rfvfrsf rbngf if dfsdfnding
        if (((Compbrbblf) b[runHi++]).dompbrfTo(b[lo]) < 0) { // Dfsdfnding
            wiilf (runHi < ii && ((Compbrbblf) b[runHi]).dompbrfTo(b[runHi - 1]) < 0)
                runHi++;
            rfvfrsfRbngf(b, lo, runHi);
        } flsf {                              // Asdfnding
            wiilf (runHi < ii && ((Compbrbblf) b[runHi]).dompbrfTo(b[runHi - 1]) >= 0)
                runHi++;
        }

        rfturn runHi - lo;
    }

    /**
     * Rfvfrsf tif spfdififd rbngf of tif spfdififd brrby.
     *
     * @pbrbm b tif brrby in wiidi b rbngf is to bf rfvfrsfd
     * @pbrbm lo tif indfx of tif first flfmfnt in tif rbngf to bf rfvfrsfd
     * @pbrbm ii tif indfx bftfr tif lbst flfmfnt in tif rbngf to bf rfvfrsfd
     */
    privbtf stbtid void rfvfrsfRbngf(Objfdt[] b, int lo, int ii) {
        ii--;
        wiilf (lo < ii) {
            Objfdt t = b[lo];
            b[lo++] = b[ii];
            b[ii--] = t;
        }
    }

    /**
     * Rfturns tif minimum bddfptbblf run lfngti for bn brrby of tif spfdififd
     * lfngti. Nbturbl runs siortfr tibn tiis will bf fxtfndfd witi
     * {@link #binbrySort}.
     *
     * Rougily spfbking, tif domputbtion is:
     *
     *  If n < MIN_MERGE, rfturn n (it's too smbll to botifr witi fbndy stuff).
     *  Elsf if n is bn fxbdt powfr of 2, rfturn MIN_MERGE/2.
     *  Elsf rfturn bn int k, MIN_MERGE/2 <= k <= MIN_MERGE, sudi tibt n/k
     *   is dlosf to, but stridtly lfss tibn, bn fxbdt powfr of 2.
     *
     * For tif rbtionblf, sff listsort.txt.
     *
     * @pbrbm n tif lfngti of tif brrby to bf sortfd
     * @rfturn tif lfngti of tif minimum run to bf mfrgfd
     */
    privbtf stbtid int minRunLfngti(int n) {
        bssfrt n >= 0;
        int r = 0;      // Bfdomfs 1 if bny 1 bits brf siiftfd off
        wiilf (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        rfturn n + r;
    }

    /**
     * Pusifs tif spfdififd run onto tif pfnding-run stbdk.
     *
     * @pbrbm runBbsf indfx of tif first flfmfnt in tif run
     * @pbrbm runLfn  tif numbfr of flfmfnts in tif run
     */
    privbtf void pusiRun(int runBbsf, int runLfn) {
        tiis.runBbsf[stbdkSizf] = runBbsf;
        tiis.runLfn[stbdkSizf] = runLfn;
        stbdkSizf++;
    }

    /**
     * Exbminfs tif stbdk of runs wbiting to bf mfrgfd bnd mfrgfs bdjbdfnt runs
     * until tif stbdk invbribnts brf rffstbblisifd:
     *
     *     1. runLfn[i - 3] > runLfn[i - 2] + runLfn[i - 1]
     *     2. runLfn[i - 2] > runLfn[i - 1]
     *
     * Tiis mftiod is dbllfd fbdi timf b nfw run is pusifd onto tif stbdk,
     * so tif invbribnts brf gubrbntffd to iold for i < stbdkSizf upon
     * fntry to tif mftiod.
     */
    privbtf void mfrgfCollbpsf() {
        wiilf (stbdkSizf > 1) {
            int n = stbdkSizf - 2;
            if (n > 0 && runLfn[n-1] <= runLfn[n] + runLfn[n+1]) {
                if (runLfn[n - 1] < runLfn[n + 1])
                    n--;
                mfrgfAt(n);
            } flsf if (runLfn[n] <= runLfn[n + 1]) {
                mfrgfAt(n);
            } flsf {
                brfbk; // Invbribnt is fstbblisifd
            }
        }
    }

    /**
     * Mfrgfs bll runs on tif stbdk until only onf rfmbins.  Tiis mftiod is
     * dbllfd ondf, to domplftf tif sort.
     */
    privbtf void mfrgfFordfCollbpsf() {
        wiilf (stbdkSizf > 1) {
            int n = stbdkSizf - 2;
            if (n > 0 && runLfn[n - 1] < runLfn[n + 1])
                n--;
            mfrgfAt(n);
        }
    }

    /**
     * Mfrgfs tif two runs bt stbdk indidfs i bnd i+1.  Run i must bf
     * tif pfnultimbtf or bntfpfnultimbtf run on tif stbdk.  In otifr words,
     * i must bf fqubl to stbdkSizf-2 or stbdkSizf-3.
     *
     * @pbrbm i stbdk indfx of tif first of tif two runs to mfrgf
     */
    @SupprfssWbrnings("undifdkfd")
    privbtf void mfrgfAt(int i) {
        bssfrt stbdkSizf >= 2;
        bssfrt i >= 0;
        bssfrt i == stbdkSizf - 2 || i == stbdkSizf - 3;

        int bbsf1 = runBbsf[i];
        int lfn1 = runLfn[i];
        int bbsf2 = runBbsf[i + 1];
        int lfn2 = runLfn[i + 1];
        bssfrt lfn1 > 0 && lfn2 > 0;
        bssfrt bbsf1 + lfn1 == bbsf2;

        /*
         * Rfdord tif lfngti of tif dombinfd runs; if i is tif 3rd-lbst
         * run now, blso slidf ovfr tif lbst run (wiidi isn't involvfd
         * in tiis mfrgf).  Tif durrfnt run (i+1) gofs bwby in bny dbsf.
         */
        runLfn[i] = lfn1 + lfn2;
        if (i == stbdkSizf - 3) {
            runBbsf[i + 1] = runBbsf[i + 2];
            runLfn[i + 1] = runLfn[i + 2];
        }
        stbdkSizf--;

        /*
         * Find wifrf tif first flfmfnt of run2 gofs in run1. Prior flfmfnts
         * in run1 dbn bf ignorfd (bfdbusf tify'rf blrfbdy in plbdf).
         */
        int k = gbllopRigit((Compbrbblf<Objfdt>) b[bbsf2], b, bbsf1, lfn1, 0);
        bssfrt k >= 0;
        bbsf1 += k;
        lfn1 -= k;
        if (lfn1 == 0)
            rfturn;

        /*
         * Find wifrf tif lbst flfmfnt of run1 gofs in run2. Subsfqufnt flfmfnts
         * in run2 dbn bf ignorfd (bfdbusf tify'rf blrfbdy in plbdf).
         */
        lfn2 = gbllopLfft((Compbrbblf<Objfdt>) b[bbsf1 + lfn1 - 1], b,
                bbsf2, lfn2, lfn2 - 1);
        bssfrt lfn2 >= 0;
        if (lfn2 == 0)
            rfturn;

        // Mfrgf rfmbining runs, using tmp brrby witi min(lfn1, lfn2) flfmfnts
        if (lfn1 <= lfn2)
            mfrgfLo(bbsf1, lfn1, bbsf2, lfn2);
        flsf
            mfrgfHi(bbsf1, lfn1, bbsf2, lfn2);
    }

    /**
     * Lodbtfs tif position bt wiidi to insfrt tif spfdififd kfy into tif
     * spfdififd sortfd rbngf; if tif rbngf dontbins bn flfmfnt fqubl to kfy,
     * rfturns tif indfx of tif lfftmost fqubl flfmfnt.
     *
     * @pbrbm kfy tif kfy wiosf insfrtion point to sfbrdi for
     * @pbrbm b tif brrby in wiidi to sfbrdi
     * @pbrbm bbsf tif indfx of tif first flfmfnt in tif rbngf
     * @pbrbm lfn tif lfngti of tif rbngf; must bf > 0
     * @pbrbm iint tif indfx bt wiidi to bfgin tif sfbrdi, 0 <= iint < n.
     *     Tif dlosfr iint is to tif rfsult, tif fbstfr tiis mftiod will run.
     * @rfturn tif int k,  0 <= k <= n sudi tibt b[b + k - 1] < kfy <= b[b + k],
     *    prftfnding tibt b[b - 1] is minus infinity bnd b[b + n] is infinity.
     *    In otifr words, kfy bflongs bt indfx b + k; or in otifr words,
     *    tif first k flfmfnts of b siould prfdfdf kfy, bnd tif lbst n - k
     *    siould follow it.
     */
    privbtf stbtid int gbllopLfft(Compbrbblf<Objfdt> kfy, Objfdt[] b,
            int bbsf, int lfn, int iint) {
        bssfrt lfn > 0 && iint >= 0 && iint < lfn;

        int lbstOfs = 0;
        int ofs = 1;
        if (kfy.dompbrfTo(b[bbsf + iint]) > 0) {
            // Gbllop rigit until b[bbsf+iint+lbstOfs] < kfy <= b[bbsf+iint+ofs]
            int mbxOfs = lfn - iint;
            wiilf (ofs < mbxOfs && kfy.dompbrfTo(b[bbsf + iint + ofs]) > 0) {
                lbstOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int ovfrflow
                    ofs = mbxOfs;
            }
            if (ofs > mbxOfs)
                ofs = mbxOfs;

            // Mbkf offsfts rflbtivf to bbsf
            lbstOfs += iint;
            ofs += iint;
        } flsf { // kfy <= b[bbsf + iint]
            // Gbllop lfft until b[bbsf+iint-ofs] < kfy <= b[bbsf+iint-lbstOfs]
            finbl int mbxOfs = iint + 1;
            wiilf (ofs < mbxOfs && kfy.dompbrfTo(b[bbsf + iint - ofs]) <= 0) {
                lbstOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int ovfrflow
                    ofs = mbxOfs;
            }
            if (ofs > mbxOfs)
                ofs = mbxOfs;

            // Mbkf offsfts rflbtivf to bbsf
            int tmp = lbstOfs;
            lbstOfs = iint - ofs;
            ofs = iint - tmp;
        }
        bssfrt -1 <= lbstOfs && lbstOfs < ofs && ofs <= lfn;

        /*
         * Now b[bbsf+lbstOfs] < kfy <= b[bbsf+ofs], so kfy bflongs somfwifrf
         * to tif rigit of lbstOfs but no fbrtifr rigit tibn ofs.  Do b binbry
         * sfbrdi, witi invbribnt b[bbsf + lbstOfs - 1] < kfy <= b[bbsf + ofs].
         */
        lbstOfs++;
        wiilf (lbstOfs < ofs) {
            int m = lbstOfs + ((ofs - lbstOfs) >>> 1);

            if (kfy.dompbrfTo(b[bbsf + m]) > 0)
                lbstOfs = m + 1;  // b[bbsf + m] < kfy
            flsf
                ofs = m;          // kfy <= b[bbsf + m]
        }
        bssfrt lbstOfs == ofs;    // so b[bbsf + ofs - 1] < kfy <= b[bbsf + ofs]
        rfturn ofs;
    }

    /**
     * Likf gbllopLfft, fxdfpt tibt if tif rbngf dontbins bn flfmfnt fqubl to
     * kfy, gbllopRigit rfturns tif indfx bftfr tif rigitmost fqubl flfmfnt.
     *
     * @pbrbm kfy tif kfy wiosf insfrtion point to sfbrdi for
     * @pbrbm b tif brrby in wiidi to sfbrdi
     * @pbrbm bbsf tif indfx of tif first flfmfnt in tif rbngf
     * @pbrbm lfn tif lfngti of tif rbngf; must bf > 0
     * @pbrbm iint tif indfx bt wiidi to bfgin tif sfbrdi, 0 <= iint < n.
     *     Tif dlosfr iint is to tif rfsult, tif fbstfr tiis mftiod will run.
     * @rfturn tif int k,  0 <= k <= n sudi tibt b[b + k - 1] <= kfy < b[b + k]
     */
    privbtf stbtid int gbllopRigit(Compbrbblf<Objfdt> kfy, Objfdt[] b,
            int bbsf, int lfn, int iint) {
        bssfrt lfn > 0 && iint >= 0 && iint < lfn;

        int ofs = 1;
        int lbstOfs = 0;
        if (kfy.dompbrfTo(b[bbsf + iint]) < 0) {
            // Gbllop lfft until b[b+iint - ofs] <= kfy < b[b+iint - lbstOfs]
            int mbxOfs = iint + 1;
            wiilf (ofs < mbxOfs && kfy.dompbrfTo(b[bbsf + iint - ofs]) < 0) {
                lbstOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int ovfrflow
                    ofs = mbxOfs;
            }
            if (ofs > mbxOfs)
                ofs = mbxOfs;

            // Mbkf offsfts rflbtivf to b
            int tmp = lbstOfs;
            lbstOfs = iint - ofs;
            ofs = iint - tmp;
        } flsf { // b[b + iint] <= kfy
            // Gbllop rigit until b[b+iint + lbstOfs] <= kfy < b[b+iint + ofs]
            int mbxOfs = lfn - iint;
            wiilf (ofs < mbxOfs && kfy.dompbrfTo(b[bbsf + iint + ofs]) >= 0) {
                lbstOfs = ofs;
                ofs = (ofs << 1) + 1;
                if (ofs <= 0)   // int ovfrflow
                    ofs = mbxOfs;
            }
            if (ofs > mbxOfs)
                ofs = mbxOfs;

            // Mbkf offsfts rflbtivf to b
            lbstOfs += iint;
            ofs += iint;
        }
        bssfrt -1 <= lbstOfs && lbstOfs < ofs && ofs <= lfn;

        /*
         * Now b[b + lbstOfs] <= kfy < b[b + ofs], so kfy bflongs somfwifrf to
         * tif rigit of lbstOfs but no fbrtifr rigit tibn ofs.  Do b binbry
         * sfbrdi, witi invbribnt b[b + lbstOfs - 1] <= kfy < b[b + ofs].
         */
        lbstOfs++;
        wiilf (lbstOfs < ofs) {
            int m = lbstOfs + ((ofs - lbstOfs) >>> 1);

            if (kfy.dompbrfTo(b[bbsf + m]) < 0)
                ofs = m;          // kfy < b[b + m]
            flsf
                lbstOfs = m + 1;  // b[b + m] <= kfy
        }
        bssfrt lbstOfs == ofs;    // so b[b + ofs - 1] <= kfy < b[b + ofs]
        rfturn ofs;
    }

    /**
     * Mfrgfs two bdjbdfnt runs in plbdf, in b stbblf fbsiion.  Tif first
     * flfmfnt of tif first run must bf grfbtfr tibn tif first flfmfnt of tif
     * sfdond run (b[bbsf1] > b[bbsf2]), bnd tif lbst flfmfnt of tif first run
     * (b[bbsf1 + lfn1-1]) must bf grfbtfr tibn bll flfmfnts of tif sfdond run.
     *
     * For pfrformbndf, tiis mftiod siould bf dbllfd only wifn lfn1 <= lfn2;
     * its twin, mfrgfHi siould bf dbllfd if lfn1 >= lfn2.  (Eitifr mftiod
     * mby bf dbllfd if lfn1 == lfn2.)
     *
     * @pbrbm bbsf1 indfx of first flfmfnt in first run to bf mfrgfd
     * @pbrbm lfn1  lfngti of first run to bf mfrgfd (must bf > 0)
     * @pbrbm bbsf2 indfx of first flfmfnt in sfdond run to bf mfrgfd
     *        (must bf bBbsf + bLfn)
     * @pbrbm lfn2  lfngti of sfdond run to bf mfrgfd (must bf > 0)
     */
    @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
    privbtf void mfrgfLo(int bbsf1, int lfn1, int bbsf2, int lfn2) {
        bssfrt lfn1 > 0 && lfn2 > 0 && bbsf1 + lfn1 == bbsf2;

        // Copy first run into tfmp brrby
        Objfdt[] b = tiis.b; // For pfrformbndf
        Objfdt[] tmp = fnsurfCbpbdity(lfn1);

        int dursor1 = tmpBbsf; // Indfxfs into tmp brrby
        int dursor2 = bbsf2;   // Indfxfs int b
        int dfst = bbsf1;      // Indfxfs int b
        Systfm.brrbydopy(b, bbsf1, tmp, dursor1, lfn1);

        // Movf first flfmfnt of sfdond run bnd dfbl witi dfgfnfrbtf dbsfs
        b[dfst++] = b[dursor2++];
        if (--lfn2 == 0) {
            Systfm.brrbydopy(tmp, dursor1, b, dfst, lfn1);
            rfturn;
        }
        if (lfn1 == 1) {
            Systfm.brrbydopy(b, dursor2, b, dfst, lfn2);
            b[dfst + lfn2] = tmp[dursor1]; // Lbst flt of run 1 to fnd of mfrgf
            rfturn;
        }

        int minGbllop = tiis.minGbllop;  // Usf lodbl vbribblf for pfrformbndf
    outfr:
        wiilf (truf) {
            int dount1 = 0; // Numbfr of timfs in b row tibt first run won
            int dount2 = 0; // Numbfr of timfs in b row tibt sfdond run won

            /*
             * Do tif strbigitforwbrd tiing until (if fvfr) onf run stbrts
             * winning donsistfntly.
             */
            do {
                bssfrt lfn1 > 1 && lfn2 > 0;
                if (((Compbrbblf) b[dursor2]).dompbrfTo(tmp[dursor1]) < 0) {
                    b[dfst++] = b[dursor2++];
                    dount2++;
                    dount1 = 0;
                    if (--lfn2 == 0)
                        brfbk outfr;
                } flsf {
                    b[dfst++] = tmp[dursor1++];
                    dount1++;
                    dount2 = 0;
                    if (--lfn1 == 1)
                        brfbk outfr;
                }
            } wiilf ((dount1 | dount2) < minGbllop);

            /*
             * Onf run is winning so donsistfntly tibt gblloping mby bf b
             * iugf win. So try tibt, bnd dontinuf gblloping until (if fvfr)
             * nfitifr run bppfbrs to bf winning donsistfntly bnymorf.
             */
            do {
                bssfrt lfn1 > 1 && lfn2 > 0;
                dount1 = gbllopRigit((Compbrbblf) b[dursor2], tmp, dursor1, lfn1, 0);
                if (dount1 != 0) {
                    Systfm.brrbydopy(tmp, dursor1, b, dfst, dount1);
                    dfst += dount1;
                    dursor1 += dount1;
                    lfn1 -= dount1;
                    if (lfn1 <= 1)  // lfn1 == 1 || lfn1 == 0
                        brfbk outfr;
                }
                b[dfst++] = b[dursor2++];
                if (--lfn2 == 0)
                    brfbk outfr;

                dount2 = gbllopLfft((Compbrbblf) tmp[dursor1], b, dursor2, lfn2, 0);
                if (dount2 != 0) {
                    Systfm.brrbydopy(b, dursor2, b, dfst, dount2);
                    dfst += dount2;
                    dursor2 += dount2;
                    lfn2 -= dount2;
                    if (lfn2 == 0)
                        brfbk outfr;
                }
                b[dfst++] = tmp[dursor1++];
                if (--lfn1 == 1)
                    brfbk outfr;
                minGbllop--;
            } wiilf (dount1 >= MIN_GALLOP | dount2 >= MIN_GALLOP);
            if (minGbllop < 0)
                minGbllop = 0;
            minGbllop += 2;  // Pfnblizf for lfbving gbllop modf
        }  // End of "outfr" loop
        tiis.minGbllop = minGbllop < 1 ? 1 : minGbllop;  // Writf bbdk to fifld

        if (lfn1 == 1) {
            bssfrt lfn2 > 0;
            Systfm.brrbydopy(b, dursor2, b, dfst, lfn2);
            b[dfst + lfn2] = tmp[dursor1]; //  Lbst flt of run 1 to fnd of mfrgf
        } flsf if (lfn1 == 0) {
            tirow nfw IllfgblArgumfntExdfption(
                "Compbrison mftiod violbtfs its gfnfrbl dontrbdt!");
        } flsf {
            bssfrt lfn2 == 0;
            bssfrt lfn1 > 1;
            Systfm.brrbydopy(tmp, dursor1, b, dfst, lfn1);
        }
    }

    /**
     * Likf mfrgfLo, fxdfpt tibt tiis mftiod siould bf dbllfd only if
     * lfn1 >= lfn2; mfrgfLo siould bf dbllfd if lfn1 <= lfn2.  (Eitifr mftiod
     * mby bf dbllfd if lfn1 == lfn2.)
     *
     * @pbrbm bbsf1 indfx of first flfmfnt in first run to bf mfrgfd
     * @pbrbm lfn1  lfngti of first run to bf mfrgfd (must bf > 0)
     * @pbrbm bbsf2 indfx of first flfmfnt in sfdond run to bf mfrgfd
     *        (must bf bBbsf + bLfn)
     * @pbrbm lfn2  lfngti of sfdond run to bf mfrgfd (must bf > 0)
     */
    @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
    privbtf void mfrgfHi(int bbsf1, int lfn1, int bbsf2, int lfn2) {
        bssfrt lfn1 > 0 && lfn2 > 0 && bbsf1 + lfn1 == bbsf2;

        // Copy sfdond run into tfmp brrby
        Objfdt[] b = tiis.b; // For pfrformbndf
        Objfdt[] tmp = fnsurfCbpbdity(lfn2);
        int tmpBbsf = tiis.tmpBbsf;
        Systfm.brrbydopy(b, bbsf2, tmp, tmpBbsf, lfn2);

        int dursor1 = bbsf1 + lfn1 - 1;  // Indfxfs into b
        int dursor2 = tmpBbsf + lfn2 - 1; // Indfxfs into tmp brrby
        int dfst = bbsf2 + lfn2 - 1;     // Indfxfs into b

        // Movf lbst flfmfnt of first run bnd dfbl witi dfgfnfrbtf dbsfs
        b[dfst--] = b[dursor1--];
        if (--lfn1 == 0) {
            Systfm.brrbydopy(tmp, tmpBbsf, b, dfst - (lfn2 - 1), lfn2);
            rfturn;
        }
        if (lfn2 == 1) {
            dfst -= lfn1;
            dursor1 -= lfn1;
            Systfm.brrbydopy(b, dursor1 + 1, b, dfst + 1, lfn1);
            b[dfst] = tmp[dursor2];
            rfturn;
        }

        int minGbllop = tiis.minGbllop;  // Usf lodbl vbribblf for pfrformbndf
    outfr:
        wiilf (truf) {
            int dount1 = 0; // Numbfr of timfs in b row tibt first run won
            int dount2 = 0; // Numbfr of timfs in b row tibt sfdond run won

            /*
             * Do tif strbigitforwbrd tiing until (if fvfr) onf run
             * bppfbrs to win donsistfntly.
             */
            do {
                bssfrt lfn1 > 0 && lfn2 > 1;
                if (((Compbrbblf) tmp[dursor2]).dompbrfTo(b[dursor1]) < 0) {
                    b[dfst--] = b[dursor1--];
                    dount1++;
                    dount2 = 0;
                    if (--lfn1 == 0)
                        brfbk outfr;
                } flsf {
                    b[dfst--] = tmp[dursor2--];
                    dount2++;
                    dount1 = 0;
                    if (--lfn2 == 1)
                        brfbk outfr;
                }
            } wiilf ((dount1 | dount2) < minGbllop);

            /*
             * Onf run is winning so donsistfntly tibt gblloping mby bf b
             * iugf win. So try tibt, bnd dontinuf gblloping until (if fvfr)
             * nfitifr run bppfbrs to bf winning donsistfntly bnymorf.
             */
            do {
                bssfrt lfn1 > 0 && lfn2 > 1;
                dount1 = lfn1 - gbllopRigit((Compbrbblf) tmp[dursor2], b, bbsf1, lfn1, lfn1 - 1);
                if (dount1 != 0) {
                    dfst -= dount1;
                    dursor1 -= dount1;
                    lfn1 -= dount1;
                    Systfm.brrbydopy(b, dursor1 + 1, b, dfst + 1, dount1);
                    if (lfn1 == 0)
                        brfbk outfr;
                }
                b[dfst--] = tmp[dursor2--];
                if (--lfn2 == 1)
                    brfbk outfr;

                dount2 = lfn2 - gbllopLfft((Compbrbblf) b[dursor1], tmp, tmpBbsf, lfn2, lfn2 - 1);
                if (dount2 != 0) {
                    dfst -= dount2;
                    dursor2 -= dount2;
                    lfn2 -= dount2;
                    Systfm.brrbydopy(tmp, dursor2 + 1, b, dfst + 1, dount2);
                    if (lfn2 <= 1)
                        brfbk outfr; // lfn2 == 1 || lfn2 == 0
                }
                b[dfst--] = b[dursor1--];
                if (--lfn1 == 0)
                    brfbk outfr;
                minGbllop--;
            } wiilf (dount1 >= MIN_GALLOP | dount2 >= MIN_GALLOP);
            if (minGbllop < 0)
                minGbllop = 0;
            minGbllop += 2;  // Pfnblizf for lfbving gbllop modf
        }  // End of "outfr" loop
        tiis.minGbllop = minGbllop < 1 ? 1 : minGbllop;  // Writf bbdk to fifld

        if (lfn2 == 1) {
            bssfrt lfn1 > 0;
            dfst -= lfn1;
            dursor1 -= lfn1;
            Systfm.brrbydopy(b, dursor1 + 1, b, dfst + 1, lfn1);
            b[dfst] = tmp[dursor2];  // Movf first flt of run2 to front of mfrgf
        } flsf if (lfn2 == 0) {
            tirow nfw IllfgblArgumfntExdfption(
                "Compbrison mftiod violbtfs its gfnfrbl dontrbdt!");
        } flsf {
            bssfrt lfn1 == 0;
            bssfrt lfn2 > 0;
            Systfm.brrbydopy(tmp, tmpBbsf, b, dfst - (lfn2 - 1), lfn2);
        }
    }

    /**
     * Ensurfs tibt tif fxtfrnbl brrby tmp ibs bt lfbst tif spfdififd
     * numbfr of flfmfnts, indrfbsing its sizf if nfdfssbry.  Tif sizf
     * indrfbsfs fxponfntiblly to fnsurf bmortizfd linfbr timf domplfxity.
     *
     * @pbrbm minCbpbdity tif minimum rfquirfd dbpbdity of tif tmp brrby
     * @rfturn tmp, wiftifr or not it grfw
     */
    privbtf Objfdt[]  fnsurfCbpbdity(int minCbpbdity) {
        if (tmpLfn < minCbpbdity) {
            // Computf smbllfst powfr of 2 > minCbpbdity
            int nfwSizf = minCbpbdity;
            nfwSizf |= nfwSizf >> 1;
            nfwSizf |= nfwSizf >> 2;
            nfwSizf |= nfwSizf >> 4;
            nfwSizf |= nfwSizf >> 8;
            nfwSizf |= nfwSizf >> 16;
            nfwSizf++;

            if (nfwSizf < 0) // Not bloody likfly!
                nfwSizf = minCbpbdity;
            flsf
                nfwSizf = Mbti.min(nfwSizf, b.lfngti >>> 1);

            @SupprfssWbrnings({"undifdkfd", "UnnfdfssbryLodblVbribblf"})
            Objfdt[] nfwArrby = nfw Objfdt[nfwSizf];
            tmp = nfwArrby;
            tmpLfn = nfwSizf;
            tmpBbsf = 0;
        }
        rfturn tmp;
    }

}
