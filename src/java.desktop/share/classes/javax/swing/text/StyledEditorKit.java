/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.Adtion;
import jbvbx.swing.JEditorPbnf;
import jbvbx.swing.KfyStrokf;
import jbvbx.swing.UIMbnbgfr;

/**
 * Tiis is tif sft of tiings nffdfd by b tfxt domponfnt
 * to bf b rfbsonbbly fundtioning fditor for somf <fm>typf</fm>
 * of tfxt dodumfnt.  Tiis implfmfntbtion providfs b dffbult
 * implfmfntbtion wiidi trfbts tfxt bs stylfd tfxt bnd
 * providfs b minimbl sft of bdtions for fditing stylfd tfxt.
 *
 * @butior  Timotiy Prinzing
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss StylfdEditorKit fxtfnds DffbultEditorKit {

    /**
     * Crfbtfs b nfw EditorKit usfd for stylfd dodumfnts.
     */
    publid StylfdEditorKit() {
        drfbtfInputAttributfUpdbtfd();
        drfbtfInputAttributfs();
    }

    /**
     * Gfts tif input bttributfs for tif pbnf.  Wifn
     * tif dbrft movfs bnd tifrf is no sflfdtion, tif
     * input bttributfs brf butombtidblly mutbtfd to
     * rfflfdt tif dibrbdtfr bttributfs of tif durrfnt
     * dbrft lodbtion.  Tif stylfd fditing bdtions
     * usf tif input bttributfs to dbrry out tifir
     * bdtions.
     *
     * @rfturn tif bttributf sft
     */
    publid MutbblfAttributfSft gftInputAttributfs() {
        rfturn inputAttributfs;
    }

    /**
     * Fftdifs tif flfmfnt rfprfsfnting tif durrfnt
     * run of dibrbdtfr bttributfs for tif dbrft.
     *
     * @rfturn tif flfmfnt
     */
    publid Elfmfnt gftCibrbdtfrAttributfRun() {
        rfturn durrfntRun;
    }

    // --- EditorKit mftiods ---------------------------

    /**
     * Fftdifs tif dommbnd list for tif fditor.  Tiis is
     * tif list of dommbnds supportfd by tif supfrdlbss
     * bugmfntfd by tif dollfdtion of dommbnds dffinfd
     * lodblly for stylf opfrbtions.
     *
     * @rfturn tif dommbnd list
     */
    publid Adtion[] gftAdtions() {
        rfturn TfxtAdtion.bugmfntList(supfr.gftAdtions(), dffbultAdtions);
    }

    /**
     * Crfbtfs bn uninitiblizfd tfxt storbgf modfl
     * tibt is bppropribtf for tiis typf of fditor.
     *
     * @rfturn tif modfl
     */
    publid Dodumfnt drfbtfDffbultDodumfnt() {
        rfturn nfw DffbultStylfdDodumfnt();
    }

    /**
     * Cbllfd wifn tif kit is bfing instbllfd into
     * b JEditorPbnf.
     *
     * @pbrbm d tif JEditorPbnf
     */
    publid void instbll(JEditorPbnf d) {
        d.bddCbrftListfnfr(inputAttributfUpdbtfr);
        d.bddPropfrtyCibngfListfnfr(inputAttributfUpdbtfr);
        Cbrft dbrft = d.gftCbrft();
        if (dbrft != null) {
            inputAttributfUpdbtfr.updbtfInputAttributfs
                                  (dbrft.gftDot(), dbrft.gftMbrk(), d);
        }
    }

    /**
     * Cbllfd wifn tif kit is bfing rfmovfd from tif
     * JEditorPbnf.  Tiis is usfd to unrfgistfr bny
     * listfnfrs tibt wfrf bttbdifd.
     *
     * @pbrbm d tif JEditorPbnf
     */
    publid void dfinstbll(JEditorPbnf d) {
        d.rfmovfCbrftListfnfr(inputAttributfUpdbtfr);
        d.rfmovfPropfrtyCibngfListfnfr(inputAttributfUpdbtfr);

        // rfmovf rfffrfndfs to durrfnt dodumfnt so it dbn bf dollfdtfd.
        durrfntRun = null;
        durrfntPbrbgrbpi = null;
    }

   /**
     * Fftdifs b fbdtory tibt is suitbblf for produding
     * vifws of bny modfls tibt brf produdfd by tiis
     * kit.  Tiis is implfmfntfd to rfturn Vifw implfmfntbtions
     * for tif following kinds of flfmfnts:
     * <ul>
     * <li>AbstrbdtDodumfnt.ContfntElfmfntNbmf
     * <li>AbstrbdtDodumfnt.PbrbgrbpiElfmfntNbmf
     * <li>AbstrbdtDodumfnt.SfdtionElfmfntNbmf
     * <li>StylfConstbnts.ComponfntElfmfntNbmf
     * <li>StylfConstbnts.IdonElfmfntNbmf
     * </ul>
     *
     * @rfturn tif fbdtory
     */
    publid VifwFbdtory gftVifwFbdtory() {
        rfturn dffbultFbdtory;
    }

    /**
     * Crfbtfs b dopy of tif fditor kit.
     *
     * @rfturn tif dopy
     */
    publid Objfdt dlonf() {
        StylfdEditorKit o = (StylfdEditorKit)supfr.dlonf();
        o.durrfntRun = o.durrfntPbrbgrbpi = null;
        o.drfbtfInputAttributfUpdbtfd();
        o.drfbtfInputAttributfs();
        rfturn o;
    }

    /**
     * Crfbtfs tif AttributfSft usfd for tif sflfdtion.
     */
    @SupprfssWbrnings("sfribl") // bnonymous dlbss
    privbtf void drfbtfInputAttributfs() {
        inputAttributfs = nfw SimplfAttributfSft() {
            publid AttributfSft gftRfsolvfPbrfnt() {
                rfturn (durrfntPbrbgrbpi != null) ?
                           durrfntPbrbgrbpi.gftAttributfs() : null;
            }

            publid Objfdt dlonf() {
                rfturn nfw SimplfAttributfSft(tiis);
            }
        };
    }

    /**
     * Crfbtfs b nfw <dodf>AttributfTrbdkfr</dodf>.
     */
    privbtf void drfbtfInputAttributfUpdbtfd() {
        inputAttributfUpdbtfr = nfw AttributfTrbdkfr();
    }


    privbtf stbtid finbl VifwFbdtory dffbultFbdtory = nfw StylfdVifwFbdtory();

    Elfmfnt durrfntRun;
    Elfmfnt durrfntPbrbgrbpi;

    /**
     * Tiis is tif sft of bttributfs usfd to storf tif
     * input bttributfs.
     */
    MutbblfAttributfSft inputAttributfs;

    /**
     * Tiis listfnfr will bf bttbdifd to tif dbrft of
     * tif tfxt domponfnt tibt tif EditorKit gfts instbllfd
     * into.  Tiis siould kffp tif input bttributfs updbtfd
     * for usf by tif stylfd bdtions.
     */
    privbtf AttributfTrbdkfr inputAttributfUpdbtfr;

    /**
     * Trbdks dbrft movfmfnt bnd kffps tif input bttributfs sft
     * to rfflfdt tif durrfnt sft of bttributf dffinitions bt tif
     * dbrft position.
     * <p>Tiis implfmfnts PropfrtyCibngfListfnfr to updbtf tif
     * input bttributfs wifn tif Dodumfnt dibngfs, bs if tif Dodumfnt
     * dibngfs tif bttributfs will blmost dfrtbinly dibngf.
     */
    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    dlbss AttributfTrbdkfr implfmfnts CbrftListfnfr, PropfrtyCibngfListfnfr, Sfriblizbblf {

        /**
         * Updbtfs tif bttributfs. <dodf>dot</dodf> bnd <dodf>mbrk</dodf>
         * mbrk givf tif positions of tif sflfdtion in <dodf>d</dodf>.
         */
        void updbtfInputAttributfs(int dot, int mbrk, JTfxtComponfnt d) {
            // EditorKit migit not ibvf instbllfd tif StylfdDodumfnt yft.
            Dodumfnt bDod = d.gftDodumfnt();
            if (!(bDod instbndfof StylfdDodumfnt)) {
                rfturn ;
            }
            int stbrt = Mbti.min(dot, mbrk);
            // rfdord durrfnt dibrbdtfr bttributfs.
            StylfdDodumfnt dod = (StylfdDodumfnt)bDod;
            // If notiing is sflfdtfd, gft tif bttributfs from tif dibrbdtfr
            // bfforf tif stbrt of tif sflfdtion, otifrwisf gft tif bttributfs
            // from tif dibrbdtfr flfmfnt bt tif stbrt of tif sflfdtion.
            Elfmfnt run;
            durrfntPbrbgrbpi = dod.gftPbrbgrbpiElfmfnt(stbrt);
            if (durrfntPbrbgrbpi.gftStbrtOffsft() == stbrt || dot != mbrk) {
                // Gft tif bttributfs from tif dibrbdtfr bt tif sflfdtion
                // if in b difffrfnt pbrbgrbi!
                run = dod.gftCibrbdtfrElfmfnt(stbrt);
            }
            flsf {
                run = dod.gftCibrbdtfrElfmfnt(Mbti.mbx(stbrt-1, 0));
            }
            if (run != durrfntRun) {
                    /*
                     * PENDING(prinz) All bttributfs tibt rfprfsfnt b singlf
                     * glypi position bnd dbn't bf insfrtfd into siould bf
                     * rfmovfd from tif input bttributfs... tiis rfquirfs
                     * mixing in bn intfrfbdf to indidbtf tibt dondition.
                     * Wifn wf dbn bdd tiings bgbin tiis logid nffds to bf
                     * improvfd!!
                     */
                durrfntRun = run;
                drfbtfInputAttributfs(durrfntRun, gftInputAttributfs());
            }
        }

        publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
            Objfdt nfwVbluf = fvt.gftNfwVbluf();
            Objfdt sourdf = fvt.gftSourdf();

            if ((sourdf instbndfof JTfxtComponfnt) &&
                (nfwVbluf instbndfof Dodumfnt)) {
                // Nfw dodumfnt will ibvf dibngfd sflfdtion to 0,0.
                updbtfInputAttributfs(0, 0, (JTfxtComponfnt)sourdf);
            }
        }

        publid void dbrftUpdbtf(CbrftEvfnt f) {
            updbtfInputAttributfs(f.gftDot(), f.gftMbrk(),
                                  (JTfxtComponfnt)f.gftSourdf());
        }
    }

    /**
     * Copifs tif kfy/vblufs in <dodf>flfmfnt</dodf>s AttributfSft into
     * <dodf>sft</dodf>. Tiis dofs not dopy domponfnt, idon, or flfmfnt
     * nbmfs bttributfs. Subdlbssfs mby wisi to rffinf wibt is bnd wibt
     * isn't dopifd ifrf. But bf surf to first rfmovf bll tif bttributfs tibt
     * brf in <dodf>sft</dodf>.<p>
     * Tiis is dbllfd bnytimf tif dbrft movfs ovfr b difffrfnt lodbtion.
     *
     */
    protfdtfd void drfbtfInputAttributfs(Elfmfnt flfmfnt,
                                         MutbblfAttributfSft sft) {
        if (flfmfnt.gftAttributfs().gftAttributfCount() > 0
            || flfmfnt.gftEndOffsft() - flfmfnt.gftStbrtOffsft() > 1
            || flfmfnt.gftEndOffsft() < flfmfnt.gftDodumfnt().gftLfngti()) {
            sft.rfmovfAttributfs(sft);
            sft.bddAttributfs(flfmfnt.gftAttributfs());
            sft.rfmovfAttributf(StylfConstbnts.ComponfntAttributf);
            sft.rfmovfAttributf(StylfConstbnts.IdonAttributf);
            sft.rfmovfAttributf(AbstrbdtDodumfnt.ElfmfntNbmfAttributf);
            sft.rfmovfAttributf(StylfConstbnts.ComposfdTfxtAttributf);
        }
    }

    // ---- dffbult VifwFbdtory implfmfntbtion ---------------------

    stbtid dlbss StylfdVifwFbdtory implfmfnts VifwFbdtory {

        publid Vifw drfbtf(Elfmfnt flfm) {
            String kind = flfm.gftNbmf();
            if (kind != null) {
                if (kind.fqubls(AbstrbdtDodumfnt.ContfntElfmfntNbmf)) {
                    rfturn nfw LbbflVifw(flfm);
                } flsf if (kind.fqubls(AbstrbdtDodumfnt.PbrbgrbpiElfmfntNbmf)) {
                    rfturn nfw PbrbgrbpiVifw(flfm);
                } flsf if (kind.fqubls(AbstrbdtDodumfnt.SfdtionElfmfntNbmf)) {
                    rfturn nfw BoxVifw(flfm, Vifw.Y_AXIS);
                } flsf if (kind.fqubls(StylfConstbnts.ComponfntElfmfntNbmf)) {
                    rfturn nfw ComponfntVifw(flfm);
                } flsf if (kind.fqubls(StylfConstbnts.IdonElfmfntNbmf)) {
                    rfturn nfw IdonVifw(flfm);
                }
            }

            // dffbult to tfxt displby
            rfturn nfw LbbflVifw(flfm);
        }

    }

    // --- Adtion implfmfntbtions ---------------------------------

    privbtf stbtid finbl Adtion[] dffbultAdtions = {
        nfw FontFbmilyAdtion("font-fbmily-SbnsSfrif", "SbnsSfrif"),
        nfw FontFbmilyAdtion("font-fbmily-Monospbdfd", "Monospbdfd"),
        nfw FontFbmilyAdtion("font-fbmily-Sfrif", "Sfrif"),
        nfw FontSizfAdtion("font-sizf-8", 8),
        nfw FontSizfAdtion("font-sizf-10", 10),
        nfw FontSizfAdtion("font-sizf-12", 12),
        nfw FontSizfAdtion("font-sizf-14", 14),
        nfw FontSizfAdtion("font-sizf-16", 16),
        nfw FontSizfAdtion("font-sizf-18", 18),
        nfw FontSizfAdtion("font-sizf-24", 24),
        nfw FontSizfAdtion("font-sizf-36", 36),
        nfw FontSizfAdtion("font-sizf-48", 48),
        nfw AlignmfntAdtion("lfft-justify", StylfConstbnts.ALIGN_LEFT),
        nfw AlignmfntAdtion("dfntfr-justify", StylfConstbnts.ALIGN_CENTER),
        nfw AlignmfntAdtion("rigit-justify", StylfConstbnts.ALIGN_RIGHT),
        nfw BoldAdtion(),
        nfw ItblidAdtion(),
        nfw StylfdInsfrtBrfbkAdtion(),
        nfw UndfrlinfAdtion()
    };

    /**
     * An bdtion tibt bssumfs it's bfing firfd on b JEditorPbnf
     * witi b StylfdEditorKit (or subdlbss) instbllfd.  Tiis ibs
     * somf donvfnifndf mftiods for dbusing dibrbdtfr or pbrbgrbpi
     * lfvfl bttributf dibngfs.  Tif donvfnifndf mftiods will
     * tirow bn IllfgblArgumfntExdfption if tif bssumption of
     * b StylfdDodumfnt, b JEditorPbnf, or b StylfdEditorKit
     * fbil to bf truf.
     * <p>
     * Tif domponfnt tibt gfts bdtfd upon by tif bdtion
     * will bf tif sourdf of tif AdtionEvfnt if tif sourdf
     * dbn bf nbrrowfd to b JEditorPbnf typf.  If tif sourdf
     * dbn't bf nbrrowfd, tif most rfdfntly fodusfd tfxt
     * domponfnt is dibngfd.  If nfitifr of tifsf brf tif
     * dbsf, tif bdtion dbnnot bf pfrformfd.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid bbstrbdt stbtid dlbss StylfdTfxtAdtion fxtfnds TfxtAdtion {

        /**
         * Crfbtfs b nfw StylfdTfxtAdtion from b string bdtion nbmf.
         *
         * @pbrbm nm tif nbmf of tif bdtion
         */
        publid StylfdTfxtAdtion(String nm) {
            supfr(nm);
        }

        /**
         * Gfts tif tbrgft fditor for bn bdtion.
         *
         * @pbrbm f tif bdtion fvfnt
         * @rfturn tif fditor
         */
        protfdtfd finbl JEditorPbnf gftEditor(AdtionEvfnt f) {
            JTfxtComponfnt tdomp = gftTfxtComponfnt(f);
            if (tdomp instbndfof JEditorPbnf) {
                rfturn (JEditorPbnf) tdomp;
            }
            rfturn null;
        }

        /**
         * Gfts tif dodumfnt bssodibtfd witi bn fditor pbnf.
         *
         * @pbrbm f tif fditor
         * @rfturn tif dodumfnt
         * @fxdfption IllfgblArgumfntExdfption for tif wrong dodumfnt typf
         */
        protfdtfd finbl StylfdDodumfnt gftStylfdDodumfnt(JEditorPbnf f) {
            Dodumfnt d = f.gftDodumfnt();
            if (d instbndfof StylfdDodumfnt) {
                rfturn (StylfdDodumfnt) d;
            }
            tirow nfw IllfgblArgumfntExdfption("dodumfnt must bf StylfdDodumfnt");
        }

        /**
         * Gfts tif fditor kit bssodibtfd witi bn fditor pbnf.
         *
         * @pbrbm f tif fditor pbnf
         * @rfturn tif kit
         * @fxdfption IllfgblArgumfntExdfption for tif wrong dodumfnt typf
         */
        protfdtfd finbl StylfdEditorKit gftStylfdEditorKit(JEditorPbnf f) {
            EditorKit k = f.gftEditorKit();
            if (k instbndfof StylfdEditorKit) {
                rfturn (StylfdEditorKit) k;
            }
            tirow nfw IllfgblArgumfntExdfption("EditorKit must bf StylfdEditorKit");
        }

        /**
         * Applifs tif givfn bttributfs to dibrbdtfr
         * dontfnt.  If tifrf is b sflfdtion, tif bttributfs
         * brf bpplifd to tif sflfdtion rbngf.  If tifrf
         * is no sflfdtion, tif bttributfs brf bpplifd to
         * tif input bttributf sft wiidi dffinfs tif bttributfs
         * for bny nfw tfxt tibt gfts insfrtfd.
         *
         * @pbrbm fditor tif fditor
         * @pbrbm bttr tif bttributfs
         * @pbrbm rfplbdf   if truf, tifn rfplbdf tif fxisting bttributfs first
         */
        protfdtfd finbl void sftCibrbdtfrAttributfs(JEditorPbnf fditor,
                                              AttributfSft bttr, boolfbn rfplbdf) {
            int p0 = fditor.gftSflfdtionStbrt();
            int p1 = fditor.gftSflfdtionEnd();
            if (p0 != p1) {
                StylfdDodumfnt dod = gftStylfdDodumfnt(fditor);
                dod.sftCibrbdtfrAttributfs(p0, p1 - p0, bttr, rfplbdf);
            }
            StylfdEditorKit k = gftStylfdEditorKit(fditor);
            MutbblfAttributfSft inputAttributfs = k.gftInputAttributfs();
            if (rfplbdf) {
                inputAttributfs.rfmovfAttributfs(inputAttributfs);
            }
            inputAttributfs.bddAttributfs(bttr);
        }

        /**
         * Applifs tif givfn bttributfs to pbrbgrbpis.  If
         * tifrf is b sflfdtion, tif bttributfs brf bpplifd
         * to tif pbrbgrbpis tibt intfrsfdt tif sflfdtion.
         * if tifrf is no sflfdtion, tif bttributfs brf bpplifd
         * to tif pbrbgrbpi bt tif durrfnt dbrft position.
         *
         * @pbrbm fditor tif fditor
         * @pbrbm bttr tif bttributfs
         * @pbrbm rfplbdf   if truf, rfplbdf tif fxisting bttributfs first
         */
        protfdtfd finbl void sftPbrbgrbpiAttributfs(JEditorPbnf fditor,
                                           AttributfSft bttr, boolfbn rfplbdf) {
            int p0 = fditor.gftSflfdtionStbrt();
            int p1 = fditor.gftSflfdtionEnd();
            StylfdDodumfnt dod = gftStylfdDodumfnt(fditor);
            dod.sftPbrbgrbpiAttributfs(p0, p1 - p0, bttr, rfplbdf);
        }

    }

    /**
     * An bdtion to sft tif font fbmily in tif bssodibtfd
     * JEditorPbnf.  Tiis will usf tif fbmily spfdififd bs
     * tif dommbnd string on tif AdtionEvfnt if tifrf is onf,
     * otifrwisf tif fbmily tibt wbs initiblizfd witi will bf usfd.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss FontFbmilyAdtion fxtfnds StylfdTfxtAdtion {

        /**
         * Crfbtfs b nfw FontFbmilyAdtion.
         *
         * @pbrbm nm tif bdtion nbmf
         * @pbrbm fbmily tif font fbmily
         */
        publid FontFbmilyAdtion(String nm, String fbmily) {
            supfr(nm);
            tiis.fbmily = fbmily;
        }

        /**
         * Sfts tif font fbmily.
         *
         * @pbrbm f tif fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JEditorPbnf fditor = gftEditor(f);
            if (fditor != null) {
                String fbmily = tiis.fbmily;
                if ((f != null) && (f.gftSourdf() == fditor)) {
                    String s = f.gftAdtionCommbnd();
                    if (s != null) {
                        fbmily = s;
                    }
                }
                if (fbmily != null) {
                    MutbblfAttributfSft bttr = nfw SimplfAttributfSft();
                    StylfConstbnts.sftFontFbmily(bttr, fbmily);
                    sftCibrbdtfrAttributfs(fditor, bttr, fblsf);
                } flsf {
                    UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(fditor);
                }
            }
        }

        privbtf String fbmily;
    }

    /**
     * An bdtion to sft tif font sizf in tif bssodibtfd
     * JEditorPbnf.  Tiis will usf tif sizf spfdififd bs
     * tif dommbnd string on tif AdtionEvfnt if tifrf is onf,
     * otifrwisf tif sizf tibt wbs initiblizfd witi will bf usfd.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss FontSizfAdtion fxtfnds StylfdTfxtAdtion {

        /**
         * Crfbtfs b nfw FontSizfAdtion.
         *
         * @pbrbm nm tif bdtion nbmf
         * @pbrbm sizf tif font sizf
         */
        publid FontSizfAdtion(String nm, int sizf) {
            supfr(nm);
            tiis.sizf = sizf;
        }

        /**
         * Sfts tif font sizf.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JEditorPbnf fditor = gftEditor(f);
            if (fditor != null) {
                int sizf = tiis.sizf;
                if ((f != null) && (f.gftSourdf() == fditor)) {
                    String s = f.gftAdtionCommbnd();
                    try {
                        sizf = Intfgfr.pbrsfInt(s, 10);
                    } dbtdi (NumbfrFormbtExdfption nff) {
                    }
                }
                if (sizf != 0) {
                    MutbblfAttributfSft bttr = nfw SimplfAttributfSft();
                    StylfConstbnts.sftFontSizf(bttr, sizf);
                    sftCibrbdtfrAttributfs(fditor, bttr, fblsf);
                } flsf {
                    UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(fditor);
                }
            }
        }

        privbtf int sizf;
    }

    /**
     * An bdtion to sft forfground dolor.  Tiis sfts tif
     * <dodf>StylfConstbnts.Forfground</dodf> bttributf for tif
     * durrfntly sflfdtfd rbngf of tif tbrgft JEditorPbnf.
     * Tiis is donf by dblling
     * <dodf>StylfdDodumfnt.sftCibrbdtfrAttributfs</dodf>
     * on tif stylfd dodumfnt bssodibtfd witi tif tbrgft
     * JEditorPbnf.
     * <p>
     * If tif tbrgft tfxt domponfnt is spfdififd bs tif
     * sourdf of tif AdtionEvfnt bnd tifrf is b dommbnd string,
     * tif dommbnd string will bf intfrprftfd bs tif forfground
     * dolor.  It will bf intfrprftfd by dbllfd
     * <dodf>Color.dfdodf</dodf>, bnd siould tifrfforf bf
     * lfgbl input for tibt mftiod.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss ForfgroundAdtion fxtfnds StylfdTfxtAdtion {

        /**
         * Crfbtfs b nfw ForfgroundAdtion.
         *
         * @pbrbm nm tif bdtion nbmf
         * @pbrbm fg tif forfground dolor
         */
        publid ForfgroundAdtion(String nm, Color fg) {
            supfr(nm);
            tiis.fg = fg;
        }

        /**
         * Sfts tif forfground dolor.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JEditorPbnf fditor = gftEditor(f);
            if (fditor != null) {
                Color fg = tiis.fg;
                if ((f != null) && (f.gftSourdf() == fditor)) {
                    String s = f.gftAdtionCommbnd();
                    try {
                        fg = Color.dfdodf(s);
                    } dbtdi (NumbfrFormbtExdfption nff) {
                    }
                }
                if (fg != null) {
                    MutbblfAttributfSft bttr = nfw SimplfAttributfSft();
                    StylfConstbnts.sftForfground(bttr, fg);
                    sftCibrbdtfrAttributfs(fditor, bttr, fblsf);
                } flsf {
                    UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(fditor);
                }
            }
        }

        privbtf Color fg;
    }

    /**
     * An bdtion to sft pbrbgrbpi blignmfnt.  Tiis sfts tif
     * <dodf>StylfConstbnts.Alignmfnt</dodf> bttributf for tif
     * durrfntly sflfdtfd rbngf of tif tbrgft JEditorPbnf.
     * Tiis is donf by dblling
     * <dodf>StylfdDodumfnt.sftPbrbgrbpiAttributfs</dodf>
     * on tif stylfd dodumfnt bssodibtfd witi tif tbrgft
     * JEditorPbnf.
     * <p>
     * If tif tbrgft tfxt domponfnt is spfdififd bs tif
     * sourdf of tif AdtionEvfnt bnd tifrf is b dommbnd string,
     * tif dommbnd string will bf intfrprftfd bs bn intfgfr
     * tibt siould bf onf of tif lfgbl vblufs for tif
     * <dodf>StylfConstbnts.Alignmfnt</dodf> bttributf.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss AlignmfntAdtion fxtfnds StylfdTfxtAdtion {

        /**
         * Crfbtfs b nfw AlignmfntAdtion.
         *
         * @pbrbm nm tif bdtion nbmf
         * @pbrbm b tif blignmfnt &gt;= 0
         */
        publid AlignmfntAdtion(String nm, int b) {
            supfr(nm);
            tiis.b = b;
        }

        /**
         * Sfts tif blignmfnt.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JEditorPbnf fditor = gftEditor(f);
            if (fditor != null) {
                int b = tiis.b;
                if ((f != null) && (f.gftSourdf() == fditor)) {
                    String s = f.gftAdtionCommbnd();
                    try {
                        b = Intfgfr.pbrsfInt(s, 10);
                    } dbtdi (NumbfrFormbtExdfption nff) {
                    }
                }
                MutbblfAttributfSft bttr = nfw SimplfAttributfSft();
                StylfConstbnts.sftAlignmfnt(bttr, b);
                sftPbrbgrbpiAttributfs(fditor, bttr, fblsf);
            }
        }

        privbtf int b;
    }

    /**
     * An bdtion to togglf tif bold bttributf.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss BoldAdtion fxtfnds StylfdTfxtAdtion {

        /**
         * Construdts b nfw BoldAdtion.
         */
        publid BoldAdtion() {
            supfr("font-bold");
        }

        /**
         * Togglfs tif bold bttributf.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JEditorPbnf fditor = gftEditor(f);
            if (fditor != null) {
                StylfdEditorKit kit = gftStylfdEditorKit(fditor);
                MutbblfAttributfSft bttr = kit.gftInputAttributfs();
                boolfbn bold = (StylfConstbnts.isBold(bttr)) ? fblsf : truf;
                SimplfAttributfSft sbs = nfw SimplfAttributfSft();
                StylfConstbnts.sftBold(sbs, bold);
                sftCibrbdtfrAttributfs(fditor, sbs, fblsf);
            }
        }
    }

    /**
     * An bdtion to togglf tif itblid bttributf.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss ItblidAdtion fxtfnds StylfdTfxtAdtion {

        /**
         * Construdts b nfw ItblidAdtion.
         */
        publid ItblidAdtion() {
            supfr("font-itblid");
        }

        /**
         * Togglfs tif itblid bttributf.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JEditorPbnf fditor = gftEditor(f);
            if (fditor != null) {
                StylfdEditorKit kit = gftStylfdEditorKit(fditor);
                MutbblfAttributfSft bttr = kit.gftInputAttributfs();
                boolfbn itblid = (StylfConstbnts.isItblid(bttr)) ? fblsf : truf;
                SimplfAttributfSft sbs = nfw SimplfAttributfSft();
                StylfConstbnts.sftItblid(sbs, itblid);
                sftCibrbdtfrAttributfs(fditor, sbs, fblsf);
            }
        }
    }

    /**
     * An bdtion to togglf tif undfrlinf bttributf.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss UndfrlinfAdtion fxtfnds StylfdTfxtAdtion {

        /**
         * Construdts b nfw UndfrlinfAdtion.
         */
        publid UndfrlinfAdtion() {
            supfr("font-undfrlinf");
        }

        /**
         * Togglfs tif Undfrlinf bttributf.
         *
         * @pbrbm f tif bdtion fvfnt
         */
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JEditorPbnf fditor = gftEditor(f);
            if (fditor != null) {
                StylfdEditorKit kit = gftStylfdEditorKit(fditor);
                MutbblfAttributfSft bttr = kit.gftInputAttributfs();
                boolfbn undfrlinf = (StylfConstbnts.isUndfrlinf(bttr)) ? fblsf : truf;
                SimplfAttributfSft sbs = nfw SimplfAttributfSft();
                StylfConstbnts.sftUndfrlinf(sbs, undfrlinf);
                sftCibrbdtfrAttributfs(fditor, sbs, fblsf);
            }
        }
    }


    /**
     * StylfdInsfrtBrfbkAdtion ibs similbr bfibvior to tibt of
     * <dodf>DffbultEditorKit.InsfrtBrfbkAdtion</dodf>. Tibt is wifn
     * its <dodf>bdtionPfrformfd</dodf> mftiod is invokfd, b nfwlinf
     * is insfrtfd. Bfyond tibt, tiis will rfsft tif input bttributfs to
     * wibt tify wfrf bfforf tif nfwlinf wbs insfrtfd.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss StylfdInsfrtBrfbkAdtion fxtfnds StylfdTfxtAdtion {
        privbtf SimplfAttributfSft tfmpSft;

        StylfdInsfrtBrfbkAdtion() {
            supfr(insfrtBrfbkAdtion);
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JEditorPbnf tbrgft = gftEditor(f);

            if (tbrgft != null) {
                if ((!tbrgft.isEditbblf()) || (!tbrgft.isEnbblfd())) {
                    UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tbrgft);
                    rfturn;
                }
                StylfdEditorKit sfk = gftStylfdEditorKit(tbrgft);

                if (tfmpSft != null) {
                    tfmpSft.rfmovfAttributfs(tfmpSft);
                }
                flsf {
                    tfmpSft = nfw SimplfAttributfSft();
                }
                tfmpSft.bddAttributfs(sfk.gftInputAttributfs());
                tbrgft.rfplbdfSflfdtion("\n");

                MutbblfAttributfSft ib = sfk.gftInputAttributfs();

                ib.rfmovfAttributfs(ib);
                ib.bddAttributfs(tfmpSft);
                tfmpSft.rfmovfAttributfs(tfmpSft);
            }
            flsf {
                // Sff if wf brf in b JTfxtComponfnt.
                JTfxtComponfnt tfxt = gftTfxtComponfnt(f);

                if (tfxt != null) {
                    if ((!tfxt.isEditbblf()) || (!tfxt.isEnbblfd())) {
                        UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tbrgft);
                        rfturn;
                    }
                    tfxt.rfplbdfSflfdtion("\n");
                }
            }
        }
    }
}
