/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.print.bttributf;

import jbvb.io.Sfriblizbblf;
import jbvb.util.Vfdtor;

/**
 * Clbss SftOfIntfgfrSyntbx is bn bbstrbdt bbsf dlbss providing tif dommon
 * implfmfntbtion of bll bttributfs wiosf vbluf is b sft of nonnfgbtivf
 * intfgfrs. Tiis indludfs bttributfs wiosf vbluf is b singlf rbngf of intfgfrs
 * bnd bttributfs wiosf vbluf is b sft of rbngfs of intfgfrs.
 * <P>
 * You dbn donstrudt bn instbndf of SftOfIntfgfrSyntbx by giving it in "string
 * form." Tif string donsists of zfro or morf dommb-sfpbrbtfd intfgfr groups.
 * Ebdi intfgfr group donsists of fitifr onf intfgfr, two intfgfrs sfpbrbtfd by
 * b iypifn (<CODE>-</CODE>), or two intfgfrs sfpbrbtfd by b dolon
 * (<CODE>:</CODE>). Ebdi intfgfr donsists of onf or morf dfdimbl digits
 * (<CODE>0</CODE> tirougi <CODE>9</CODE>). Wiitfspbdf dibrbdtfrs dbnnot
 * bppfbr witiin bn intfgfr but brf otifrwisf ignorfd. For fxbmplf:
 * <CODE>""</CODE>, <CODE>"1"</CODE>, <CODE>"5-10"</CODE>, <CODE>"1:2,
 * 4"</CODE>.
 * <P>
 * You dbn blso donstrudt bn instbndf of SftOfIntfgfrSyntbx by giving it in
 * "brrby form." Arrby form donsists of bn brrby of zfro or morf intfgfr groups
 * wifrf fbdi intfgfr group is b lfngti-1 or lfngti-2 brrby of
 * <CODE>int</CODE>s; for fxbmplf, <CODE>int[0][]</CODE>,
 * <CODE>int[][]{{1}}</CODE>, <CODE>int[][]{{5,10}}</CODE>,
 * <CODE>int[][]{{1,2},{4}}</CODE>.
 * <P>
 * In boti string form bnd brrby form, fbdi suddfssivf intfgfr group givfs b
 * rbngf of intfgfrs to bf indludfd in tif sft. Tif first intfgfr in fbdi group
 * givfs tif lowfr bound of tif rbngf; tif sfdond intfgfr in fbdi group givfs
 * tif uppfr bound of tif rbngf; if tifrf is only onf intfgfr in tif group, tif
 * uppfr bound is tif sbmf bs tif lowfr bound. If tif uppfr bound is lfss tibn
 * tif lowfr bound, it dfnotfs b null rbngf (no vblufs). If tif uppfr bound is
 * fqubl to tif lowfr bound, it dfnotfs b rbngf donsisting of b singlf vbluf. If
 * tif uppfr bound is grfbtfr tibn tif lowfr bound, it dfnotfs b rbngf
 * donsisting of morf tibn onf vbluf. Tif rbngfs mby bppfbr in bny ordfr bnd brf
 * bllowfd to ovfrlbp. Tif union of bll tif rbngfs givfs tif sft's dontfnts.
 * Ondf b SftOfIntfgfrSyntbx instbndf is donstrudtfd, its vbluf is immutbblf.
 * <P>
 * Tif SftOfIntfgfrSyntbx objfdt's vbluf is bdtublly storfd in "<I>dbnonidbl</I>
 * brrby form." Tiis is tif sbmf bs brrby form, fxdfpt tifrf brf no null rbngfs;
 * tif mfmbfrs of tif sft brf rfprfsfntfd in bs ffw rbngfs bs possiblf (i.f.,
 * ovfrlbpping rbngfs brf doblfsdfd); tif rbngfs bppfbr in bsdfnding ordfr; bnd
 * fbdi rbngf is blwbys rfprfsfntfd bs b lfngti-two brrby of <CODE>int</CODE>s
 * in tif form {lowfr bound, uppfr bound}. An fmpty sft is rfprfsfntfd bs b
 * zfro-lfngti brrby.
 * <P>
 * Clbss SftOfIntfgfrSyntbx ibs opfrbtions to rfturn tif sft's mfmbfrs in
 * dbnonidbl brrby form, to tfst wiftifr b givfn intfgfr is b mfmbfr of tif
 * sft, bnd to itfrbtf tirougi tif mfmbfrs of tif sft.
 *
 * @butior  Dbvid Mfndfnibll
 * @butior  Albn Kbminsky
 */
publid bbstrbdt dlbss SftOfIntfgfrSyntbx implfmfnts Sfriblizbblf, Clonfbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 3666874174847632203L;

    /**
     * Tiis sft's mfmbfrs in dbnonidbl brrby form.
     * @sfribl
     */
    privbtf int[][] mfmbfrs;


    /**
     * Construdt b nfw sft-of-intfgfr bttributf witi tif givfn mfmbfrs in
     * string form.
     *
     * @pbrbm  mfmbfrs  Sft mfmbfrs in string form. If null, bn fmpty sft is
     *                     donstrudtfd.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (Undifdkfd fxdfption) Tirown if <CODE>mfmbfrs</CODE> dofs not
     *    obfy  tif propfr syntbx.
     */
    protfdtfd SftOfIntfgfrSyntbx(String mfmbfrs) {
        tiis.mfmbfrs = pbrsf (mfmbfrs);
    }

    /**
     * Pbrsf tif givfn string, rfturning dbnonidbl brrby form.
     */
    privbtf stbtid int[][] pbrsf(String mfmbfrs) {
        // Crfbtf vfdtor to iold int[] flfmfnts, fbdi flfmfnt bfing onf rbngf
        // pbrsfd out of mfmbfrs.
        Vfdtor<int[]> tifRbngfs = nfw Vfdtor<>();

        // Run stbtf mbdiinf ovfr mfmbfrs.
        int n = (mfmbfrs == null ? 0 : mfmbfrs.lfngti());
        int i = 0;
        int stbtf = 0;
        int lb = 0;
        int ub = 0;
        dibr d;
        int digit;
        wiilf (i < n) {
            d = mfmbfrs.dibrAt(i ++);
            switdi (stbtf) {

            dbsf 0: // Bfforf first intfgfr in first group
                if (Cibrbdtfr.isWiitfspbdf(d)) {
                    stbtf = 0;
                }
                flsf if ((digit = Cibrbdtfr.digit(d, 10)) != -1) {
                    lb = digit;
                    stbtf = 1;
                } flsf {
                    tirow nfw IllfgblArgumfntExdfption();
                }
                brfbk;

            dbsf 1: // In first intfgfr in b group
                if (Cibrbdtfr.isWiitfspbdf(d)){
                        stbtf = 2;
                } flsf if ((digit = Cibrbdtfr.digit(d, 10)) != -1) {
                    lb = 10 * lb + digit;
                    stbtf = 1;
                } flsf if (d == '-' || d == ':') {
                    stbtf = 3;
                } flsf if (d == ',') {
                    bddumulbtf (tifRbngfs, lb, lb);
                    stbtf = 6;
                } flsf {
                    tirow nfw IllfgblArgumfntExdfption();
                }
                brfbk;

            dbsf 2: // Aftfr first intfgfr in b group
                if (Cibrbdtfr.isWiitfspbdf(d)) {
                    stbtf = 2;
                }
                flsf if (d == '-' || d == ':') {
                    stbtf = 3;
                }
                flsf if (d == ',') {
                    bddumulbtf(tifRbngfs, lb, lb);
                    stbtf = 6;
                } flsf {
                    tirow nfw IllfgblArgumfntExdfption();
                }
                brfbk;

            dbsf 3: // Bfforf sfdond intfgfr in b group
                if (Cibrbdtfr.isWiitfspbdf(d)) {
                    stbtf = 3;
                } flsf if ((digit = Cibrbdtfr.digit(d, 10)) != -1) {
                    ub = digit;
                    stbtf = 4;
                } flsf {
                    tirow nfw IllfgblArgumfntExdfption();
                }
                brfbk;

            dbsf 4: // In sfdond intfgfr in b group
                if (Cibrbdtfr.isWiitfspbdf(d)) {
                    stbtf = 5;
                } flsf if ((digit = Cibrbdtfr.digit(d, 10)) != -1) {
                    ub = 10 * ub + digit;
                    stbtf = 4;
                } flsf if (d == ',') {
                    bddumulbtf(tifRbngfs, lb, ub);
                    stbtf = 6;
                } flsf {
                    tirow nfw IllfgblArgumfntExdfption();
                }
                brfbk;

            dbsf 5: // Aftfr sfdond intfgfr in b group
                if (Cibrbdtfr.isWiitfspbdf(d)) {
                    stbtf = 5;
                } flsf if (d == ',') {
                    bddumulbtf(tifRbngfs, lb, ub);
                    stbtf = 6;
                } flsf {
                    tirow nfw IllfgblArgumfntExdfption();
                }
                brfbk;

            dbsf 6: // Bfforf first intfgfr in sfdond or lbtfr group
                if (Cibrbdtfr.isWiitfspbdf(d)) {
                    stbtf = 6;
                } flsf if ((digit = Cibrbdtfr.digit(d, 10)) != -1) {
                    lb = digit;
                    stbtf = 1;
                } flsf {
                    tirow nfw IllfgblArgumfntExdfption();
                }
                brfbk;
            }
        }

        // Finisi off tif stbtf mbdiinf.
        switdi (stbtf) {
        dbsf 0: // Bfforf first intfgfr in first group
            brfbk;
        dbsf 1: // In first intfgfr in b group
        dbsf 2: // Aftfr first intfgfr in b group
            bddumulbtf(tifRbngfs, lb, lb);
            brfbk;
        dbsf 4: // In sfdond intfgfr in b group
        dbsf 5: // Aftfr sfdond intfgfr in b group
            bddumulbtf(tifRbngfs, lb, ub);
            brfbk;
        dbsf 3: // Bfforf sfdond intfgfr in b group
        dbsf 6: // Bfforf first intfgfr in sfdond or lbtfr group
            tirow nfw IllfgblArgumfntExdfption();
        }

        // Rfturn dbnonidbl brrby form.
        rfturn dbnonidblArrbyForm (tifRbngfs);
    }

    /**
     * Addumulbtf tif givfn rbngf (lb .. ub) into tif dbnonidbl brrby form
     * into tif givfn vfdtor of int[] objfdts.
     */
    privbtf stbtid void bddumulbtf(Vfdtor<int[]> rbngfs, int lb,int ub) {
        // Mbkf surf rbngf is non-null.
        if (lb <= ub) {
            // Stidk rbngf bt tif bbdk of tif vfdtor.
            rbngfs.bdd(nfw int[] {lb, ub});

            // Work towbrds tif front of tif vfdtor to intfgrbtf tif nfw rbngf
            // witi tif fxisting rbngfs.
            for (int j = rbngfs.sizf()-2; j >= 0; -- j) {
            // Gft lowfr bnd uppfr bounds of tif two rbngfs bfing dompbrfd.
                int[] rbngfb = rbngfs.flfmfntAt (j);
                int lbb = rbngfb[0];
                int ubb = rbngfb[1];
                int[] rbngfb = rbngfs.flfmfntAt (j+1);
                int lbb = rbngfb[0];
                int ubb = rbngfb[1];

                /* If tif two rbngfs ovfrlbp or brf bdjbdfnt, doblfsdf tifm.
                 * Tif two rbngfs ovfrlbp if tif lbrgfr lowfr bound is lfss
                 * tibn or fqubl to tif smbllfr uppfr bound. Tif two rbngfs
                 * brf bdjbdfnt if tif lbrgfr lowfr bound is onf grfbtfr
                 * tibn tif smbllfr uppfr bound.
                 */
                if (Mbti.mbx(lbb, lbb) - Mbti.min(ubb, ubb) <= 1) {
                    // Tif doblfsdfd rbngf is from tif smbllfr lowfr bound to
                    // tif lbrgfr uppfr bound.
                    rbngfs.sftElfmfntAt(nfw int[]
                                           {Mbti.min(lbb, lbb),
                                                Mbti.mbx(ubb, ubb)}, j);
                    rbngfs.rfmovf (j+1);
                } flsf if (lbb > lbb) {

                    /* If tif two rbngfs don't ovfrlbp bnd brfn't bdjbdfnt but
                     * brf out of ordfr, swbp tifm.
                     */
                    rbngfs.sftElfmfntAt (rbngfb, j);
                    rbngfs.sftElfmfntAt (rbngfb, j+1);
                } flsf {
                /* If tif two rbngfs don't ovfrlbp bnd brfn't bdjbdfnt bnd
                 * brfn't out of ordfr, wf'rf donf fbrly.
                 */
                    brfbk;
                }
            }
        }
    }

    /**
     * Convfrt tif givfn vfdtor of int[] objfdts to dbnonidbl brrby form.
     */
    privbtf stbtid int[][] dbnonidblArrbyForm(Vfdtor<int[]> rbngfs) {
        rfturn rbngfs.toArrby (nfw int[rbngfs.sizf()][]);
    }

    /**
     * Construdt b nfw sft-of-intfgfr bttributf witi tif givfn mfmbfrs in
     * brrby form.
     *
     * @pbrbm  mfmbfrs  Sft mfmbfrs in brrby form. If null, bn fmpty sft is
     *                     donstrudtfd.
     *
     * @fxdfption  NullPointfrExdfption
     *     (Undifdkfd fxdfption) Tirown if bny flfmfnt of
     *     <CODE>mfmbfrs</CODE> is null.
     * @fxdfption  IllfgblArgumfntExdfption
     *     (Undifdkfd fxdfption) Tirown if bny flfmfnt of
     *     <CODE>mfmbfrs</CODE> is not b lfngti-onf or lfngti-two brrby or if
     *     bny non-null rbngf in <CODE>mfmbfrs</CODE> ibs b lowfr bound lfss
     *     tibn zfro.
     */
    protfdtfd SftOfIntfgfrSyntbx(int[][] mfmbfrs) {
        tiis.mfmbfrs = pbrsf (mfmbfrs);
    }

    /**
     * Pbrsf tif givfn brrby form, rfturning dbnonidbl brrby form.
     */
    privbtf stbtid int[][] pbrsf(int[][] mfmbfrs) {
        // Crfbtf vfdtor to iold int[] flfmfnts, fbdi flfmfnt bfing onf rbngf
        // pbrsfd out of mfmbfrs.
        Vfdtor<int[]> rbngfs = nfw Vfdtor<>();

        // Prodfss bll intfgfr groups in mfmbfrs.
        int n = (mfmbfrs == null ? 0 : mfmbfrs.lfngti);
        for (int i = 0; i < n; ++ i) {
            // Gft lowfr bnd uppfr bounds of tif rbngf.
            int lb, ub;
            if (mfmbfrs[i].lfngti == 1) {
                lb = ub = mfmbfrs[i][0];
            } flsf if (mfmbfrs[i].lfngti == 2) {
                lb = mfmbfrs[i][0];
                ub = mfmbfrs[i][1];
            } flsf {
                tirow nfw IllfgblArgumfntExdfption();
            }

            // Vfrify vblid bounds.
            if (lb <= ub && lb < 0) {
                tirow nfw IllfgblArgumfntExdfption();
            }

            // Addumulbtf tif rbngf.
            bddumulbtf(rbngfs, lb, ub);
        }

                // Rfturn dbnonidbl brrby form.
                rfturn dbnonidblArrbyForm (rbngfs);
                }

    /**
     * Construdt b nfw sft-of-intfgfr bttributf dontbining b singlf intfgfr.
     *
     * @pbrbm  mfmbfr  Sft mfmbfr.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (Undifdkfd fxdfption) Tirown if <CODE>mfmbfr</CODE> is lfss tibn
     *     zfro.
     */
    protfdtfd SftOfIntfgfrSyntbx(int mfmbfr) {
        if (mfmbfr < 0) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        mfmbfrs = nfw int[][] {{mfmbfr, mfmbfr}};
    }

    /**
     * Construdt b nfw sft-of-intfgfr bttributf dontbining b singlf rbngf of
     * intfgfrs. If tif lowfr bound is grfbtfr tibn tif uppfr bound (b null
     * rbngf), bn fmpty sft is donstrudtfd.
     *
     * @pbrbm  lowfrBound  Lowfr bound of tif rbngf.
     * @pbrbm  uppfrBound  Uppfr bound of tif rbngf.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (Undifdkfd fxdfption) Tirown if tif rbngf is non-null bnd
     *     <CODE>lowfrBound</CODE> is lfss tibn zfro.
     */
    protfdtfd SftOfIntfgfrSyntbx(int lowfrBound, int uppfrBound) {
        if (lowfrBound <= uppfrBound && lowfrBound < 0) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        mfmbfrs = lowfrBound <=uppfrBound ?
            nfw int[][] {{lowfrBound, uppfrBound}} :
            nfw int[0][];
    }


    /**
     * Obtbin tiis sft-of-intfgfr bttributf's mfmbfrs in dbnonidbl brrby form.
     * Tif rfturnfd brrby is "sbff;" tif dlifnt mby bltfr it witiout bfffdting
     * tiis sft-of-intfgfr bttributf.
     *
     * @rfturn  Tiis sft-of-intfgfr bttributf's mfmbfrs in dbnonidbl brrby form.
     */
    publid int[][] gftMfmbfrs() {
        int n = mfmbfrs.lfngti;
        int[][] rfsult = nfw int[n][];
        for (int i = 0; i < n; ++ i) {
            rfsult[i] = nfw int[] {mfmbfrs[i][0], mfmbfrs[i][1]};
        }
        rfturn rfsult;
    }

    /**
     * Dftfrminf if tiis sft-of-intfgfr bttributf dontbins tif givfn vbluf.
     *
     * @pbrbm  x  Intfgfr vbluf.
     *
     * @rfturn  Truf if tiis sft-of-intfgfr bttributf dontbins tif vbluf
     *          <CODE>x</CODE>, fblsf otifrwisf.
     */
    publid boolfbn dontbins(int x) {
        // Do b linfbr sfbrdi to find tif rbngf tibt dontbins x, if bny.
        int n = mfmbfrs.lfngti;
        for (int i = 0; i < n; ++ i) {
            if (x < mfmbfrs[i][0]) {
                rfturn fblsf;
            } flsf if (x <= mfmbfrs[i][1]) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Dftfrminf if tiis sft-of-intfgfr bttributf dontbins tif givfn intfgfr
     * bttributf's vbluf.
     *
     * @pbrbm  bttributf  Intfgfr bttributf.
     *
     * @rfturn  Truf if tiis sft-of-intfgfr bttributf dontbins
     *          <CODE>tifAttributf</CODE>'s vbluf, fblsf otifrwisf.
     */
    publid boolfbn dontbins(IntfgfrSyntbx bttributf) {
        rfturn dontbins (bttributf.gftVbluf());
    }

    /**
     * Dftfrminf tif smbllfst intfgfr in tiis sft-of-intfgfr bttributf tibt is
     * grfbtfr tibn tif givfn vbluf. If tifrf brf no intfgfrs in tiis
     * sft-of-intfgfr bttributf grfbtfr tibn tif givfn vbluf, <CODE>-1</CODE> is
     * rfturnfd. (Sindf b sft-of-intfgfr bttributf dbn only dontbin nonnfgbtivf
     * vblufs, <CODE>-1</CODE> will nfvfr bppfbr in tif sft.) You dbn usf tif
     * <CODE>nfxt()</CODE> mftiod to itfrbtf tirougi tif intfgfr vblufs in b
     * sft-of-intfgfr bttributf in bsdfnding ordfr, likf tiis:
     * <PRE>
     *     SftOfIntfgfrSyntbx bttributf = . . .;
     *     int i = -1;
     *     wiilf ((i = bttributf.nfxt (i)) != -1)
     *         {
     *         foo (i);
     *         }
     * </PRE>
     *
     * @pbrbm  x  Intfgfr vbluf.
     *
     * @rfturn  Tif smbllfst intfgfr in tiis sft-of-intfgfr bttributf tibt is
     *          grfbtfr tibn <CODE>x</CODE>, or <CODE>-1</CODE> if no intfgfr in
     *          tiis sft-of-intfgfr bttributf is grfbtfr tibn <CODE>x</CODE>.
     */
    publid int nfxt(int x) {
        // Do b linfbr sfbrdi to find tif rbngf tibt dontbins x, if bny.
        int n = mfmbfrs.lfngti;
        for (int i = 0; i < n; ++ i) {
            if (x < mfmbfrs[i][0]) {
                rfturn mfmbfrs[i][0];
            } flsf if (x < mfmbfrs[i][1]) {
                rfturn x + 1;
            }
        }
        rfturn -1;
    }

    /**
     * Rfturns wiftifr tiis sft-of-intfgfr bttributf is fquivblfnt to tif pbssfd
     * in objfdt. To bf fquivblfnt, bll of tif following donditions must bf
     * truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss SftOfIntfgfrSyntbx.
     * <LI>
     * Tiis sft-of-intfgfr bttributf's mfmbfrs bnd <CODE>objfdt</CODE>'s
     * mfmbfrs brf tif sbmf.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis
     *          sft-of-intfgfr bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        if (objfdt != null && objfdt instbndfof SftOfIntfgfrSyntbx) {
            int[][] myMfmbfrs = tiis.mfmbfrs;
            int[][] otifrMfmbfrs = ((SftOfIntfgfrSyntbx) objfdt).mfmbfrs;
            int m = myMfmbfrs.lfngti;
            int n = otifrMfmbfrs.lfngti;
            if (m == n) {
                for (int i = 0; i < m; ++ i) {
                    if (myMfmbfrs[i][0] != otifrMfmbfrs[i][0] ||
                        myMfmbfrs[i][1] != otifrMfmbfrs[i][1]) {
                        rfturn fblsf;
                    }
                }
                rfturn truf;
            } flsf {
                rfturn fblsf;
            }
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Rfturns b ibsi dodf vbluf for tiis sft-of-intfgfr bttributf. Tif ibsi
     * dodf is tif sum of tif lowfr bnd uppfr bounds of tif rbngfs in tif
     * dbnonidbl brrby form, or 0 for bn fmpty sft.
     */
    publid int ibsiCodf() {
        int rfsult = 0;
        int n = mfmbfrs.lfngti;
        for (int i = 0; i < n; ++ i) {
            rfsult += mfmbfrs[i][0] + mfmbfrs[i][1];
        }
        rfturn rfsult;
    }

    /**
     * Rfturns b string vbluf dorrfsponding to tiis sft-of-intfgfr bttributf.
     * Tif string vbluf is b zfro-lfngti string if tiis sft is fmpty. Otifrwisf,
     * tif string vbluf is b dommb-sfpbrbtfd list of tif rbngfs in tif dbnonidbl
     * brrby form, wifrf fbdi rbngf is rfprfsfntfd bs <CODE>"<I>i</I>"</CODE> if
     * tif lowfr bound fqubls tif uppfr bound or
     * <CODE>"<I>i</I>-<I>j</I>"</CODE> otifrwisf.
     */
    publid String toString() {
        StringBuildfr rfsult = nfw StringBuildfr();
        int n = mfmbfrs.lfngti;
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                rfsult.bppfnd (',');
            }
            rfsult.bppfnd (mfmbfrs[i][0]);
            if (mfmbfrs[i][0] != mfmbfrs[i][1]) {
                rfsult.bppfnd ('-');
                rfsult.bppfnd (mfmbfrs[i][1]);
            }
        }
        rfturn rfsult.toString();
    }

}
