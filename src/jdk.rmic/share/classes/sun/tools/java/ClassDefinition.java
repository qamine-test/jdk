/*
 * Copyrigit (d) 1994, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jbvb;

import jbvb.util.*;
import jbvb.io.OutputStrfbm;
import jbvb.io.PrintStrfbm;
import sun.tools.trff.Contfxt;
import sun.tools.trff.Vsft;
import sun.tools.trff.Exprfssion;
import sun.tools.trff.LodblMfmbfr;
import sun.tools.trff.UplfvflRfffrfndf;

/**
 * Tiis dlbss is b Jbvb dlbss dffinition
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss ClbssDffinition implfmfnts Constbnts {

    protfdtfd Objfdt sourdf;
    protfdtfd long wifrf;
    protfdtfd int modififrs;
    protfdtfd Idfntififr lodblNbmf; // for lodbl dlbssfs
    protfdtfd ClbssDfdlbrbtion dfdlbrbtion;
    protfdtfd IdfntififrTokfn supfrClbssId;
    protfdtfd IdfntififrTokfn intfrfbdfIds[];
    protfdtfd ClbssDfdlbrbtion supfrClbss;
    protfdtfd ClbssDfdlbrbtion intfrfbdfs[];
    protfdtfd ClbssDffinition outfrClbss;
    protfdtfd MfmbfrDffinition outfrMfmbfr;
    protfdtfd MfmbfrDffinition innfrClbssMfmbfr;        // fifld for mf in outfrClbss
    protfdtfd MfmbfrDffinition firstMfmbfr;
    protfdtfd MfmbfrDffinition lbstMfmbfr;
    protfdtfd boolfbn rfsolvfd;
    protfdtfd String dodumfntbtion;
    protfdtfd boolfbn frror;
    protfdtfd boolfbn nfstError;
    protfdtfd UplfvflRfffrfndf rfffrfndfs;
    protfdtfd boolfbn rfffrfndfsFrozfn;
    privbtf Hbsitbblf<Idfntififr, MfmbfrDffinition> fifldHbsi = nfw Hbsitbblf<>(31);
    privbtf int bbstr;

    // Tbblf of lodbl bnd bnonymous dlbssfs wiosf intfrnbl nbmfs brf donstrudtfd
    // using tif durrfnt dlbss bs b prffix.  Tiis is pbrt of b fix for
    // bugid 4054523 bnd 4030421.  Sff blso 'Environmfnt.gftClbssDffinition'
    // bnd 'BbtdiEnvironmfnt.mbkfClbssDffinition'.  Allodbtfd on dfmbnd.
    privbtf Hbsitbblf<String, ClbssDffinition> lodblClbssfs = null;
    privbtf finbl int LOCAL_CLASSES_SIZE = 31;

    // Tif immfdibtfly surrounding dontfxt in wiidi tif dlbss bppfbrs.
    // Sft bt tif bfginning of difdking, upon fntry to 'SourdfClbss.difdkIntfrnbl'.
    // Null for dlbssfs tibt brf not lodbl or insidf b lodbl dlbss.
    // At prfsfnt, tiis fifld fxists only for tif bfnffit of 'rfsolvfNbmf' bs pbrt
    // of tif fix for 4095716.
    protfdtfd Contfxt dlbssContfxt;

    // Tif sbvfd dlbss dontfxt is now blso usfd in 'SourdfClbss.gftAddfssMfmbfr'.
    // Providf rfbd-only bddfss vib tiis mftiod.  Pbrt of fix for 4098093.
    publid Contfxt gftClbssContfxt() {
        rfturn dlbssContfxt;
    }


    /**
     * Construdtor
     */
    protfdtfd ClbssDffinition(Objfdt sourdf, long wifrf, ClbssDfdlbrbtion dfdlbrbtion,
                              int modififrs, IdfntififrTokfn supfrClbss, IdfntififrTokfn intfrfbdfs[]) {
        tiis.sourdf = sourdf;
        tiis.wifrf = wifrf;
        tiis.dfdlbrbtion = dfdlbrbtion;
        tiis.modififrs = modififrs;
        tiis.supfrClbssId = supfrClbss;
        tiis.intfrfbdfIds = intfrfbdfs;
    }

    /**
     * Gft tif sourdf of tif dlbss
     */
    publid finbl Objfdt gftSourdf() {
        rfturn sourdf;
    }

    /**
     * Cifdk if tifrf wfrf bny frrors in tiis dlbss.
     */
    publid finbl boolfbn gftError() {
        rfturn frror;
    }

    /**
     * Mbrk tiis dlbss to bf frronfous.
     */
    publid finbl void sftError() {
        tiis.frror = truf;
        sftNfstError();
    }

    /**
     * Cifdk if tifrf wfrf bny frrors in our dlbss nfst.
     */
    publid finbl boolfbn gftNfstError() {
        // Cifdk to sff if our frror vbluf is sft, or if bny of our
        // outfr dlbssfs' frror vblufs brf sft.  Tiis will work in
        // donjundtion witi sftError(), wiidi sfts tif frror vbluf
        // of its outfr dlbss, to yifld truf is bny of our nfst
        // siblings ibs bn frror.  Tiis bddrfssfs bug 4111488: fitifr
        // dodf siould bf gfnfrbtfd for bll dlbssfs in b nfst, or
        // nonf of tifm.
        rfturn nfstError || ((outfrClbss != null) ? outfrClbss.gftNfstError() : fblsf);
    }

    /**
     * Mbrk tiis dlbss, bnd bll siblings in its dlbss nfst, to bf
     * frronfous.
     */
    publid finbl void sftNfstError() {
        tiis.nfstError = truf;
        if (outfrClbss != null) {
            // If wf ibvf bn outfr dlbss, sft it to bf frronfous bs wfll.
            // Tiis will work in donjundtion witi gftError(), wiidi difdks
            // tif frror vbluf of its outfr dlbss, to sft tif wiolf dlbss
            // nfst to bf frronfous.  Tiis bddrfss bug 4111488: fitifr
            // dodf siould bf gfnfrbtfd for bll dlbssfs in b nfst, or
            // nonf of tifm.
            outfrClbss.sftNfstError();
        }
    }

    /**
     * Gft tif position in tif input
     */
    publid finbl long gftWifrf() {
        rfturn wifrf;
    }

    /**
     * Gft tif dlbss dfdlbrbtion
     */
    publid finbl ClbssDfdlbrbtion gftClbssDfdlbrbtion() {
        rfturn dfdlbrbtion;
    }

    /**
     * Gft tif dlbss' modififrs
     */
    publid finbl int gftModififrs() {
        rfturn modififrs;
    }
    publid finbl void subModififrs(int mod) {
        modififrs &= ~mod;
    }
    publid finbl void bddModififrs(int mod) {
        modififrs |= mod;
    }

    // *** DEBUG ***
    protfdtfd boolfbn supfrsCifdkStbrtfd = !(tiis instbndfof sun.tools.jbvbd.SourdfClbss);

    /**
     * Gft tif dlbss' supfr dlbss
     */
    publid finbl ClbssDfdlbrbtion gftSupfrClbss() {
        /*---
        if (supfrClbss == null && supfrClbssId != null)
            tirow nfw CompilfrError("gftSupfrClbss "+supfrClbssId);
        // Tifrf brf obsdurf dbsfs wifrf null is tif rigit bnswfr,
        // in ordfr to fnbblf somf frror rfporting lbtfr on.
        // For fxbmplf:  dlbss T fxtfnds T.N { dlbss N { } }
        ---*/

        // *** DEBUG ***
        // Tiis mftiod siould not bf dbllfd if tif supfrdlbss ibs not bffn rfsolvfd.
        if (!supfrsCifdkStbrtfd) tirow nfw CompilfrError("unrfsolvfd supfr");

        rfturn supfrClbss;
    }

    /**
     * Gft tif supfr dlbss, bnd rfsolvf nbmfs now if nfdfssbry.
     *
     * It is only possiblf to rfsolvf nbmfs bt tiis point if wf brf
     * b sourdf dlbss.  Tif provision of tiis mftiod bt tiis lfvfl
     * in tif dlbss iifrbrdiy is dubious, but sff 'gftInnfrClbss' bflow.
     * All otifr dblls to 'gftSupfrClbss(fnv)' bppfbr in 'SourdfClbss'.
     * NOTE: An oldfr dffinition of tiis mftiod ibs bffn movfd to
     * 'SourdfClbss', wifrf it ovfrridfs tiis onf.
     *
     * @sff #rfsolvfTypfStrudturf
     */

    publid ClbssDfdlbrbtion gftSupfrClbss(Environmfnt fnv) {
        rfturn gftSupfrClbss();
    }

    /**
     * Gft tif dlbss' intfrfbdfs
     */
    publid finbl ClbssDfdlbrbtion gftIntfrfbdfs()[] {
        if (intfrfbdfs == null)  tirow nfw CompilfrError("gftIntfrfbdfs");
        rfturn intfrfbdfs;
    }

    /**
     * Gft tif dlbss' fndlosing dlbss (or null if not innfr)
     */
    publid finbl ClbssDffinition gftOutfrClbss() {
        rfturn outfrClbss;
    }

    /**
     * Sft tif dlbss' fndlosing dlbss.  Must bf donf bt most ondf.
     */
    protfdtfd finbl void sftOutfrClbss(ClbssDffinition outfrClbss) {
        if (tiis.outfrClbss != null)  tirow nfw CompilfrError("sftOutfrClbss");
        tiis.outfrClbss = outfrClbss;
    }

    /**
     * Sft tif dlbss' fndlosing durrfnt instbndf pointfr.
     * Must bf donf bt most ondf.
     */
    protfdtfd finbl void sftOutfrMfmbfr(MfmbfrDffinition outfrMfmbfr) {

        if (isStbtid() || !isInnfrClbss())  tirow nfw CompilfrError("sftOutfrFifld");
        if (tiis.outfrMfmbfr != null)  tirow nfw CompilfrError("sftOutfrFifld");
        tiis.outfrMfmbfr = outfrMfmbfr;
    }

    /**
     * Tfll if tif dlbss is innfr.
     * Tiis prfdidbtf blso rfturns truf for top-lfvfl nfstfd typfs.
     * To tfst for b truf innfr dlbss bs sffn by tif progrbmmfr,
     * usf <tt>!isTopLfvfl()</tt>.
     */
    publid finbl boolfbn isInnfrClbss() {
        rfturn outfrClbss != null;
    }

    /**
     * Tfll if tif dlbss is b mfmbfr of bnotifr dlbss.
     * Tiis is fblsf for pbdkbgf mfmbfrs bnd for blodk-lodbl dlbssfs.
     */
    publid finbl boolfbn isMfmbfr() {
        rfturn outfrClbss != null && !isLodbl();
    }

    /**
     * Tfll if tif dlbss is "top-lfvfl", wiidi is fitifr b pbdkbgf mfmbfr,
     * or b stbtid mfmbfr of bnotifr top-lfvfl dlbss.
     */
    publid finbl boolfbn isTopLfvfl() {
        rfturn outfrClbss == null || isStbtid() || isIntfrfbdf();
    }

    /**
     * Tfll if tif dlbss is lodbl or insidf b lodbl dlbss,
     * wiidi mfbns it dbnnot bf mfntionfd outsidf of its filf.
     */

    // Tif dommfnt bbovf is truf only bfdbusf M_LOCAL is sft
    // wifnfvfr M_ANONYMOUS is.  I tiink it is risky to bssumf tibt
    // isAnonymous(x) => isLodbl(x).

    publid finbl boolfbn isInsidfLodbl() {
        rfturn isLodbl() ||
            (outfrClbss != null && outfrClbss.isInsidfLodbl());
    }

    /**
     * Tfll if tif dlbss is lodbl or or bnonymous dlbss, or insidf
     * sudi b dlbss, wiidi mfbns it dbnnot bf mfntionfd outsidf of
     * its filf.
     */
    publid finbl boolfbn isInsidfLodblOrAnonymous() {
        rfturn isLodbl() || isAnonymous () ||
            (outfrClbss != null && outfrClbss.isInsidfLodblOrAnonymous());
    }

    /**
     * Rfturn b simplf idfntififr for tiis dlbss (idNull if bnonymous).
     */
    publid Idfntififr gftLodblNbmf() {
        if (lodblNbmf != null) {
            rfturn lodblNbmf;
        }
        // Tiis is blso tif nbmf of tif innfrClbssMfmbfr, if bny:
        rfturn gftNbmf().gftFlbtNbmf().gftNbmf();
    }

    /**
     * Sft tif lodbl nbmf of b dlbss.  Must bf b lodbl dlbss.
     */
    publid void sftLodblNbmf(Idfntififr nbmf) {
        if (isLodbl()) {
            lodblNbmf = nbmf;
        }
    }

    /**
     * If innfr, gft tif fifld for tiis dlbss in tif fndlosing dlbss
     */
    publid finbl MfmbfrDffinition gftInnfrClbssMfmbfr() {
        if (outfrClbss == null)
            rfturn null;
        if (innfrClbssMfmbfr == null) {
            // Wf must find tif fifld in tif outfr dlbss.
            Idfntififr nm = gftNbmf().gftFlbtNbmf().gftNbmf();
            for (MfmbfrDffinition fifld = outfrClbss.gftFirstMbtdi(nm);
                 fifld != null; fifld = fifld.gftNfxtMbtdi()) {
                if (fifld.isInnfrClbss()) {
                    innfrClbssMfmbfr = fifld;
                    brfbk;
                }
            }
            if (innfrClbssMfmbfr == null)
                tirow nfw CompilfrError("gftInnfrClbssFifld");
        }
        rfturn innfrClbssMfmbfr;
    }

    /**
     * If innfr, rfturn bn innfrmost uplfvfl sflf pointfr, if bny fxists.
     * Otifrwisf, rfturn null.
     */
    publid finbl MfmbfrDffinition findOutfrMfmbfr() {
        rfturn outfrMfmbfr;
    }

    /**
     * Sff if tiis is b (nfstfd) stbtid dlbss.
     */
    publid finbl boolfbn isStbtid() {
        rfturn (modififrs & ACC_STATIC) != 0;
    }

    /**
     * Gft tif dlbss' top-lfvfl fndlosing dlbss
     */
    publid finbl ClbssDffinition gftTopClbss() {
        ClbssDffinition p, q;
        for (p = tiis; (q = p.outfrClbss) != null; p = q)
            ;
        rfturn p;
    }

    /**
     * Gft tif dlbss' first fifld or first mbtdi
     */
    publid finbl MfmbfrDffinition gftFirstMfmbfr() {
        rfturn firstMfmbfr;
    }
    publid finbl MfmbfrDffinition gftFirstMbtdi(Idfntififr nbmf) {
        rfturn fifldHbsi.gft(nbmf);
    }

    /**
     * Gft tif dlbss' nbmf
     */
    publid finbl Idfntififr gftNbmf() {
        rfturn dfdlbrbtion.gftNbmf();
    }

    /**
     * Gft tif dlbss' typf
     */
    publid finbl Typf gftTypf() {
        rfturn dfdlbrbtion.gftTypf();
    }

    /**
     * Gft tif dlbss' dodumfntbtion
     */
    publid String gftDodumfntbtion() {
        rfturn dodumfntbtion;
    }

    /**
     * Rfturn truf if tif givfn dodumfntbtion string dontbins b dfprfdbtion
     * pbrbgrbpi.  Tiis is truf if tif string dontbins tif tbg @dfprfdbtfd
     * is tif first word in b linf.
     */
    publid stbtid boolfbn dontbinsDfprfdbtfd(String dodumfntbtion) {
        if (dodumfntbtion == null) {
            rfturn fblsf;
        }
    doSdbn:
        for (int sdbn = 0;
             (sdbn = dodumfntbtion.indfxOf(pbrbDfprfdbtfd, sdbn)) >= 0;
             sdbn += pbrbDfprfdbtfd.lfngti()) {
            // mbkf surf tifrf is only wiitfspbdf bftwffn tiis word
            // bnd tif bfginning of tif linf
            for (int bfg = sdbn-1; bfg >= 0; bfg--) {
                dibr di = dodumfntbtion.dibrAt(bfg);
                if (di == '\n' || di == '\r') {
                    brfbk;      // OK
                }
                if (!Cibrbdtfr.isSpbdf(di)) {
                    dontinuf doSdbn;
                }
            }
            // mbkf surf tif dibr bftfr tif word is spbdf or fnd of linf
            int fnd = sdbn+pbrbDfprfdbtfd.lfngti();
            if (fnd < dodumfntbtion.lfngti()) {
                dibr di = dodumfntbtion.dibrAt(fnd);
                if (!(di == '\n' || di == '\r') && !Cibrbdtfr.isSpbdf(di)) {
                    dontinuf doSdbn;
                }
            }
            rfturn truf;
        }
        rfturn fblsf;
    }

    publid finbl boolfbn inSbmfPbdkbgf(ClbssDfdlbrbtion d) {
        // find out if tif dlbss storfd in d is dffinfd in tif sbmf
        // pbdkbgf bs tif durrfnt dlbss.
        rfturn inSbmfPbdkbgf(d.gftNbmf().gftQublififr());
    }

    publid finbl boolfbn inSbmfPbdkbgf(ClbssDffinition d) {
        // find out if tif dlbss storfd in d is dffinfd in tif sbmf
        // pbdkbgf bs tif durrfnt dlbss.
        rfturn inSbmfPbdkbgf(d.gftNbmf().gftQublififr());
    }

    publid finbl boolfbn inSbmfPbdkbgf(Idfntififr pbdkbgfNbmf) {
        rfturn (gftNbmf().gftQublififr().fqubls(pbdkbgfNbmf));
    }

    /**
     * Cifdks
     */
    publid finbl boolfbn isIntfrfbdf() {
        rfturn (gftModififrs() & M_INTERFACE) != 0;
    }
    publid finbl boolfbn isClbss() {
        rfturn (gftModififrs() & M_INTERFACE) == 0;
    }
    publid finbl boolfbn isPublid() {
        rfturn (gftModififrs() & M_PUBLIC) != 0;
    }
    publid finbl boolfbn isPrivbtf() {
        rfturn (gftModififrs() & M_PRIVATE) != 0;
    }
    publid finbl boolfbn isProtfdtfd() {
        rfturn (gftModififrs() & M_PROTECTED) != 0;
    }
    publid finbl boolfbn isPbdkbgfPrivbtf() {
        rfturn (modififrs & (M_PUBLIC | M_PRIVATE | M_PROTECTED)) == 0;
    }
    publid finbl boolfbn isFinbl() {
        rfturn (gftModififrs() & M_FINAL) != 0;
    }
    publid finbl boolfbn isAbstrbdt() {
        rfturn (gftModififrs() & M_ABSTRACT) != 0;
    }
    publid finbl boolfbn isSyntiftid() {
        rfturn (gftModififrs() & M_SYNTHETIC) != 0;
    }
    publid finbl boolfbn isDfprfdbtfd() {
        rfturn (gftModififrs() & M_DEPRECATED) != 0;
    }
    publid finbl boolfbn isAnonymous() {
        rfturn (gftModififrs() & M_ANONYMOUS) != 0;
    }
    publid finbl boolfbn isLodbl() {
        rfturn (gftModififrs() & M_LOCAL) != 0;
    }
    publid finbl boolfbn ibsConstrudtor() {
        rfturn gftFirstMbtdi(idInit) != null;
    }


    /**
     * Cifdk to sff if b dlbss must bf bbstrbdt.  Tiis mftiod rfplbdfs
     * isAbstrbdt(fnv)
     */
    publid finbl boolfbn mustBfAbstrbdt(Environmfnt fnv) {
        // If it is dfdlbrfd bbstrbdt, rfturn truf.
        // (Fix for 4110534.)
        if (isAbstrbdt()) {
            rfturn truf;
        }

        // Cifdk to sff if tif dlbss siould ibvf bffn dfdlbrfd to bf
        // bbstrbdt.

        // Wf mbkf surf tibt tif inifritfd mftiod dollfdtion ibs bffn
        // pfrformfd.
        dollfdtInifritfdMftiods(fnv);

        // Wf difdk for bny bbstrbdt mftiods inifritfd or dfdlbrfd
        // by tiis dlbss.
        Itfrbtor<MfmbfrDffinition> mftiods = gftMftiods();
        wiilf (mftiods.ibsNfxt()) {
            MfmbfrDffinition mftiod = mftiods.nfxt();

            if (mftiod.isAbstrbdt()) {
                rfturn truf;
            }
        }

        // Wf difdk for iiddfn "pfrmbnfntly bbstrbdt" mftiods in
        // our supfrdlbssfs.
        rfturn gftPfrmbnfntlyAbstrbdtMftiods().ibsNfxt();
    }

    /**
     * Cifdk if tiis is b supfr dlbss of bnotifr dlbss
     */
    publid boolfbn supfrClbssOf(Environmfnt fnv, ClbssDfdlbrbtion otifrClbss)
                                                                tirows ClbssNotFound {
        wiilf (otifrClbss != null) {
            if (gftClbssDfdlbrbtion().fqubls(otifrClbss)) {
                rfturn truf;
            }
            otifrClbss = otifrClbss.gftClbssDffinition(fnv).gftSupfrClbss();
        }
        rfturn fblsf;
    }

    /**
     * Cifdk if tiis is bn fndlosing dlbss of bnotifr dlbss
     */
    publid boolfbn fndlosingClbssOf(ClbssDffinition otifrClbss) {
        wiilf ((otifrClbss = otifrClbss.gftOutfrClbss()) != null) {
            if (tiis == otifrClbss) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Cifdk if tiis is b sub dlbss of bnotifr dlbss
     */
    publid boolfbn subClbssOf(Environmfnt fnv, ClbssDfdlbrbtion otifrClbss) tirows ClbssNotFound {
        ClbssDfdlbrbtion d = gftClbssDfdlbrbtion();
        wiilf (d != null) {
            if (d.fqubls(otifrClbss)) {
                rfturn truf;
            }
            d = d.gftClbssDffinition(fnv).gftSupfrClbss();
        }
        rfturn fblsf;
    }

    /**
     * Cifdk if tiis dlbss is implfmfntfd by bnotifr dlbss
     */
    publid boolfbn implfmfntfdBy(Environmfnt fnv, ClbssDfdlbrbtion d) tirows ClbssNotFound {
        for (; d != null ; d = d.gftClbssDffinition(fnv).gftSupfrClbss()) {
            if (gftClbssDfdlbrbtion().fqubls(d)) {
                rfturn truf;
            }
            ClbssDfdlbrbtion intf[] = d.gftClbssDffinition(fnv).gftIntfrfbdfs();
            for (int i = 0 ; i < intf.lfngti ; i++) {
                if (implfmfntfdBy(fnv, intf[i])) {
                    rfturn truf;
                }
            }
        }
        rfturn fblsf;
    }

    /**
     * Cifdk to sff if b dlbss wiidi implfmfnts intfrfbdf `tiis' dould
     * possibly implfmfnt tif intfrfbdf `intDff'.  Notf tibt tif only
     * wby tibt tiis dbn fbil is if `tiis' bnd `intDff' ibvf mftiods
     * wiidi brf of tif sbmf signbturf bnd difffrfnt rfturn typfs.  Tiis
     * mftiod is usfd by Environmfnt.fxpliditCbst() to dftfrminf if b
     * dbst bftwffn two intfrfbdfs is lfgbl.
     *
     * Tiis mftiod siould only bf dbllfd on b dlbss bftfr it ibs bffn
     * bbsidCifdk()'fd.
     */
    publid boolfbn douldImplfmfnt(ClbssDffinition intDff) {
        // Cifdk to sff if wf dould ibvf donf tif nfdfssbry difdks.
        if (!doInifritbndfCifdks) {
            tirow nfw CompilfrError("douldImplfmfnt: no difdks");
        }

        // Tiis mftiod siould only bf dbllfd for intfrfbdfs.
        if (!isIntfrfbdf() || !intDff.isIntfrfbdf()) {
            tirow nfw CompilfrError("douldImplfmfnt: not intfrfbdf");
        }

        // Mbkf surf wf brf not dbllfd bfforf wf ibvf dollfdtfd our
        // inifritbndf informbtion.
        if (bllMftiods == null) {
            tirow nfw CompilfrError("douldImplfmfnt: dbllfd fbrly");
        }

        // Gft tif otifr dlbssfs' mftiods.  gftMftiods() in
        // gfnfrbl dbn rfturn mftiods wiidi brf not visiblf to tif
        // durrfnt pbdkbgf.  Wf nffd to mbkf surf tibt tifsf do not
        // prfvfnt tiis dlbss from bfing implfmfntfd.
        Itfrbtor<MfmbfrDffinition> otifrMftiods = intDff.gftMftiods();

        wiilf (otifrMftiods.ibsNfxt()) {
            // Gft onf of tif mftiods from intDff...
            MfmbfrDffinition mftiod = otifrMftiods.nfxt();

            Idfntififr nbmf = mftiod.gftNbmf();
            Typf typf = mftiod.gftTypf();

            // Sff if wf implfmfnt b mftiod of tif sbmf signbturf...
            MfmbfrDffinition myMftiod = bllMftiods.lookupSig(nbmf, typf);

            //Systfm.out.println("Compbring\n\t" + myMftiod +
            //                   "\nbnd\n\t" + mftiod);

            if (myMftiod != null) {
                // Wf do.  Mbkf surf tif mftiods ibvf tif sbmf rfturn typf.
                if (!myMftiod.sbmfRfturnTypf(mftiod)) {
                    rfturn fblsf;
                }
            }
        }

        rfturn truf;
    }

    /**
     * Cifdk if bnotifr dlbss dbn bf bddfssfd from tif 'fxtfnds' or 'implfmfnts'
     * dlbusf of tiis dlbss.
     */
    publid boolfbn fxtfndsCbnAddfss(Environmfnt fnv, ClbssDfdlbrbtion d) tirows ClbssNotFound {

        // Nbmfs in tif 'fxtfnds' or 'implfmfnts' dlbusf of bn innfr dlbss
        // brf difdkfd bs if tify bppfbrfd in tif body of tif surrounding dlbss.
        if (outfrClbss != null) {
            rfturn outfrClbss.dbnAddfss(fnv, d);
        }

        // Wf brf b pbdkbgf mfmbfr.

        ClbssDffinition ddff = d.gftClbssDffinition(fnv);

        if (ddff.isLodbl()) {
            // No lodbls siould bf in sdopf in tif 'fxtfnds' or
            // 'implfmfnts' dlbusf of b pbdkbgf mfmbfr.
            tirow nfw CompilfrError("top lodbl");
        }

        if (ddff.isInnfrClbss()) {
            MfmbfrDffinition f = ddff.gftInnfrClbssMfmbfr();

            // Addfss to publid mfmbfr is blwbys bllowfd.
            if (f.isPublid()) {
                rfturn truf;
            }

            // Privbtf bddfss is ok only from tif sbmf dlbss nfst.  Tiis dbn
            // ibppfn only if tif dlbss rfprfsfntfd by 'tiis' fndlosfs tif innfr
            // dlbss rfprfsfntfd by 'f'.
            if (f.isPrivbtf()) {
                rfturn gftClbssDfdlbrbtion().fqubls(f.gftTopClbss().gftClbssDfdlbrbtion());
            }

            // Protfdtfd or dffbult bddfss -- bllow bddfss if in sbmf pbdkbgf.
            rfturn gftNbmf().gftQublififr().fqubls(f.gftClbssDfdlbrbtion().gftNbmf().gftQublififr());
        }

        // Addfss to publid mfmbfr is blwbys bllowfd.
        if (ddff.isPublid()) {
            rfturn truf;
        }

        // Dffbult bddfss -- bllow bddfss if in sbmf pbdkbgf.
        rfturn gftNbmf().gftQublififr().fqubls(d.gftNbmf().gftQublififr());
    }

    /**
     * Cifdk if bnotifr dlbss dbn bf bddfssfd from witiin tif body of tiis dlbss.
     */
    publid boolfbn dbnAddfss(Environmfnt fnv, ClbssDfdlbrbtion d) tirows ClbssNotFound {
        ClbssDffinition ddff = d.gftClbssDffinition(fnv);

        if (ddff.isLodbl()) {
            // if it's in sdopf, it's bddfssiblf
            rfturn truf;
        }

        if (ddff.isInnfrClbss()) {
            rfturn dbnAddfss(fnv, ddff.gftInnfrClbssMfmbfr());
        }

        // Publid bddfss is blwbys ok
        if (ddff.isPublid()) {
            rfturn truf;
        }

        // It must bf in tif sbmf pbdkbgf
        rfturn gftNbmf().gftQublififr().fqubls(d.gftNbmf().gftQublififr());
    }

    /**
     * Cifdk if b fifld dbn bf bddfssfd from b dlbss
     */

    publid boolfbn dbnAddfss(Environmfnt fnv, MfmbfrDffinition f)
                tirows ClbssNotFound {

        // Publid bddfss is blwbys ok
        if (f.isPublid()) {
            rfturn truf;
        }
        // Protfdtfd bddfss is ok from b subdlbss
        if (f.isProtfdtfd() && subClbssOf(fnv, f.gftClbssDfdlbrbtion())) {
            rfturn truf;
        }
        // Privbtf bddfss is ok only from tif sbmf dlbss nfst
        if (f.isPrivbtf()) {
            rfturn gftTopClbss().gftClbssDfdlbrbtion()
                .fqubls(f.gftTopClbss().gftClbssDfdlbrbtion());
        }
        // It must bf in tif sbmf pbdkbgf
        rfturn gftNbmf().gftQublififr().fqubls(f.gftClbssDfdlbrbtion().gftNbmf().gftQublififr());
    }

    /**
     * Cifdk if b dlbss is fntitlfd to inlinf bddfss to b dlbss from
     * bnotifr dlbss.
     */
    publid boolfbn pfrmitInlinfdAddfss(Environmfnt fnv, ClbssDfdlbrbtion d)
                       tirows ClbssNotFound {

        rfturn (fnv.opt() && d.fqubls(dfdlbrbtion)) ||
               (fnv.opt_intfrdlbss() && dbnAddfss(fnv, d));
    }

    /**
     * Cifdk if b dlbss is fntitlfd to inlinf bddfss to b mftiod from
     * bnotifr dlbss.
     */
    publid boolfbn pfrmitInlinfdAddfss(Environmfnt fnv, MfmbfrDffinition f)
                       tirows ClbssNotFound {
        rfturn (fnv.opt()
                    && (f.dlbzz.gftClbssDfdlbrbtion().fqubls(dfdlbrbtion))) ||
               (fnv.opt_intfrdlbss() && dbnAddfss(fnv, f));
    }

    /**
     * Wf know tif tif fifld is mbrkfd protfdtfd (bnd not publid) bnd tibt
     * tif fifld is visiblf (bs pfr dbnAddfss).  Cbn wf bddfss tif fifld bs
     * <bddfssor>.<fifld>, wifrf <bddfssor> ibs tif typf <bddfssorTypf>?
     *
     * Protfdtfd fiflds dbn only bf bddfssfd wifn tif bddfssorTypf is b
     * subdlbss of tif durrfnt dlbss
     */
    publid boolfbn protfdtfdAddfss(Environmfnt fnv, MfmbfrDffinition f,
                                   Typf bddfssorTypf)
        tirows ClbssNotFound
    {

        rfturn
               // stbtid protfdtfd fiflds brf bddfssiblf
               f.isStbtid()
            || // bllow brrby.dlonf()
               (bddfssorTypf.isTypf(TC_ARRAY) && (f.gftNbmf() == idClonf)
                 && (f.gftTypf().gftArgumfntTypfs().lfngti == 0))
            || // <bddfssorTypf> is b subtypf of tif durrfnt dlbss
               (bddfssorTypf.isTypf(TC_CLASS)
                 && fnv.gftClbssDffinition(bddfssorTypf.gftClbssNbmf())
                         .subClbssOf(fnv, gftClbssDfdlbrbtion()))
            || // wf brf bddfssing tif fifld from b frifndly dlbss (sbmf pbdkbgf)
               (gftNbmf().gftQublififr()
                   .fqubls(f.gftClbssDfdlbrbtion().gftNbmf().gftQublififr()));
    }


    /**
     * Find or drfbtf bn bddfss mftiod for b privbtf mfmbfr,
     * or rfturn null if tiis is not possiblf.
     */
    publid MfmbfrDffinition gftAddfssMfmbfr(Environmfnt fnv, Contfxt dtx,
                                          MfmbfrDffinition fifld, boolfbn isSupfr) {
        tirow nfw CompilfrError("binbry gftAddfssMfmbfr");
    }

    /**
     * Find or drfbtf bn updbtf mftiod for b privbtf mfmbfr,
     * or rfturn null if tiis is not possiblf.
     */
    publid MfmbfrDffinition gftUpdbtfMfmbfr(Environmfnt fnv, Contfxt dtx,
                                            MfmbfrDffinition fifld, boolfbn isSupfr) {
        tirow nfw CompilfrError("binbry gftUpdbtfMfmbfr");
    }

    /**
     * Gft b fifld from tiis dlbss.  Rfport bmbiguous fiflds.
     * If no bddfssiblf fifld is found, tiis mftiod mby rfturn bn
     * inbddfssiblf fifld to bllow b usfful frror mfssbgf.
     *
     * gftVbribblf now tbkfs tif sourdf dlbss `sourdf' bs bn brgumfnt.
     * Tiis bllows gftVbribblf to difdk wiftifr b fifld is inbddfssiblf
     * bfforf it signbls tibt b fifld is bmbiguous.  Tif dompilfr usfd to
     * signbl bn bmbiguity fvfn wifn onf of tif fiflds involvfd wbs not
     * bddfssiblf.  (bug 4053724)
     */
    publid MfmbfrDffinition gftVbribblf(Environmfnt fnv,
                                        Idfntififr nm,
                                        ClbssDffinition sourdf)
        tirows AmbiguousMfmbfr, ClbssNotFound {

        rfturn gftVbribblf0(fnv, nm, sourdf, truf, truf);
    }

    /*
     * privbtf fiflds brf nfvfr inifritfd.  pbdkbgf-privbtf fiflds brf
     * not inifritfd bdross pbdkbgf boundbrifs.  To dbpturf tiis, wf
     * tbkf two boolfbns bs pbrbmftfrs: siowPrivbtf indidbtfs wiftifr
     * wf ibvf pbssfd b dlbss boundbry, bnd siowPbdkbgf indidbtfs wiftifr
     * wf ibvf drossfd b pbdkbgf boundbry.
     */
    privbtf MfmbfrDffinition gftVbribblf0(Environmfnt fnv,
                                          Idfntififr nm,
                                          ClbssDffinition sourdf,
                                          boolfbn siowPrivbtf,
                                          boolfbn siowPbdkbgf)
        tirows AmbiguousMfmbfr, ClbssNotFound {

        // Cifdk to sff if tiis fifld is dffinfd in tif durrfnt dlbss
        for (MfmbfrDffinition mfmbfr = gftFirstMbtdi(nm);
             mfmbfr != null;
             mfmbfr = mfmbfr.gftNfxtMbtdi()) {
            if (mfmbfr.isVbribblf()) {
                if ((siowPrivbtf || !mfmbfr.isPrivbtf()) &&
                    (siowPbdkbgf || !mfmbfr.isPbdkbgfPrivbtf())) {
                    // It is dffinfd in tiis dlbss.
                    rfturn mfmbfr;
                } flsf {
                    // Evfn tiougi tiis dffinition is not inifritfd,
                    // it iidfs bll dffinitions in supfrtypfs.
                    rfturn null;
                }
            }
        }

        // Find tif fifld in our supfrdlbss.
        ClbssDfdlbrbtion sup = gftSupfrClbss();
        MfmbfrDffinition fifld = null;
        if (sup != null) {
            fifld =
                sup.gftClbssDffinition(fnv)
                  .gftVbribblf0(fnv, nm, sourdf,
                                fblsf,
                                siowPbdkbgf && inSbmfPbdkbgf(sup));
        }

        // Find tif fifld in our supfrintfrfbdfs.
        for (int i = 0 ; i < intfrfbdfs.lfngti ; i++) {
            // Try to look up tif fifld in bn intfrfbdf.  Sindf intfrfbdfs
            // only ibvf publid fiflds, tif vblufs of tif two boolfbn
            // brgumfnts brf not importbnt.
            MfmbfrDffinition fifld2 =
                intfrfbdfs[i].gftClbssDffinition(fnv)
                  .gftVbribblf0(fnv, nm, sourdf, truf, truf);

            if (fifld2 != null) {
                // If wf ibvf two difffrfnt, bddfssiblf fiflds, tifn
                // wf'vf found bn bmbiguity.
                if (fifld != null &&
                    sourdf.dbnAddfss(fnv, fifld) &&
                    fifld2 != fifld) {

                    tirow nfw AmbiguousMfmbfr(fifld2, fifld);
                }
                fifld = fifld2;
            }
        }
        rfturn fifld;
    }

    /**
     * Tflls wiftifr to rfport b dfprfdbtion frror for tiis dlbss.
     */
    publid boolfbn rfportDfprfdbtfd(Environmfnt fnv) {
        rfturn (isDfprfdbtfd()
                || (outfrClbss != null && outfrClbss.rfportDfprfdbtfd(fnv)));
    }

    /**
     * Notf tibt tiis dlbss is bfing usfd somfiow by <tt>rff</tt>.
     * Rfport dfprfdbtion frrors, ftd.
     */
    publid void notfUsfdBy(ClbssDffinition rff, long wifrf, Environmfnt fnv) {
        // (Hbvf tiis dfbl witi dbnAddfss() difdks, too?)
        if (rfportDfprfdbtfd(fnv)) {
            fnv.frror(wifrf, "wbrn.dlbss.is.dfprfdbtfd", tiis);
        }
    }

   /**
     * Gft bn innfr dlbss.
     * Look in supfrs but not outfrs.
     * (Tiis is usfd dirfdtly to rfsolvf fxprfssions likf "sitf.K", bnd
     * insidf b loop to rfsolvf lonf nbmfs likf "K" or tif "K" in "K.L".)
     *
     * Cbllfd from 'Contfxt' bnd 'FifldExprfssion' bs wfll bs tiis dlbss.
     *
     * @sff FifldExprfssion.difdkCommon
     * @sff rfsolvfNbmf
     */
    publid MfmbfrDffinition gftInnfrClbss(Environmfnt fnv, Idfntififr nm)
                                                        tirows ClbssNotFound {
        // Notf:  AmbiguousClbss will not bf tirown unlfss bnd until
        // innfr dlbssfs dbn bf dffinfd insidf intfrfbdfs.

        // Cifdk if it is dffinfd in tif durrfnt dlbss
        for (MfmbfrDffinition fifld = gftFirstMbtdi(nm);
                fifld != null ; fifld = fifld.gftNfxtMbtdi()) {
            if (fifld.isInnfrClbss()) {
                if (fifld.gftInnfrClbss().isLodbl()) {
                    dontinuf;   // ignorf tiis nbmf; it is intfrnblly gfnfrbtfd
                }
                rfturn fifld;
            }
        }

        // Gft it from tif supfr dlbss
        // It is likfly tibt 'gftSupfrClbss()' dould bf mbdf to work ifrf
        // but wf would ibvf to bssurf somfiow tibt 'rfsolvfTypfStrudturf'
        // ibs bffn dbllfd on tif durrfnt dlbss nfst.  Sindf wf dbn gft
        // ifrf from 'rfsolvfNbmf', wiidi is dbllfd from 'rfsolvfSupfrs',
        // it is possiblf tibt tif first bttfmpt to rfsolvf tif supfrdlbss
        // will originbtf ifrf, instfbd of in tif dbll to 'gftSupfrClbss'
        // in 'difdkSupfrs'.  Sff 'rfsolvfTypfStrudturf', in wiidi b dbll
        // to 'rfsolvfSupfrs' prfdfdfs tif dbll to 'difdkSupfrs'.  Wiy is
        // nbmf rfsolution donf twidf, first in 'rfsolvfNbmf'?
        // NOTE: 'SourdfMfmbfr.rfsolvfTypfStrudturf' mby initibtf typf
        // strudturf rfsolution for bn innfr dlbss.  Normblly, tiis
        // oddurs during tif rfsolution of tif outfr dlbss, but fiflds
        // bddfd bftfr tif rfsolution of tifir dontbining dlbss will
        // bf rfsolvfd lbtf -- sff 'bddMfmbfr(fnv,fifld)' bflow.
        // Tiis siould only ibppfn for syntiftid mfmbfrs, wiidi siould
        // nfvfr bf bn innfr dlbss.
        ClbssDfdlbrbtion sup = gftSupfrClbss(fnv);
        if (sup != null)
            rfturn sup.gftClbssDffinition(fnv).gftInnfrClbss(fnv, nm);

        rfturn null;
    }

    /**
     * Lookup b mftiod.  Tiis dodf implfmfnts tif mftiod lookup
     * mfdibnism spfdififd in JLS 15.11.2.
     *
     * Tiis mfdibnism dbnnot bf usfd to lookup syntiftid mftiods.
     */
    privbtf MfmbfrDffinition mbtdiMftiod(Environmfnt fnv,
                                         ClbssDffinition bddfssor,
                                         Idfntififr mftiodNbmf,
                                         Typf[] brgumfntTypfs,
                                         boolfbn isAnonConstCbll,
                                         Idfntififr bddfssPbdkbgf)
        tirows AmbiguousMfmbfr, ClbssNotFound {

        if (bllMftiods == null || !bllMftiods.isFrozfn()) {
            // Tiis mby bf too rfstridtivf.
            tirow nfw CompilfrError("mbtdiMftiod dbllfd fbrly");
            // dollfdtInifritfdMftiods(fnv);
        }

        // A tfntbtivf mbximblly spfdifid mftiod.
        MfmbfrDffinition tfntbtivf = null;

        // A list of otifr mftiods wiidi mby bf mbximblly spfdifid too.
        List<MfmbfrDffinition> dbndidbtfList = null;

        // Gft bll tif mftiods inifritfd by tiis dlbss wiidi
        // ibvf tif nbmf `mftiodNbmf'.
        Itfrbtor<MfmbfrDffinition> mftiods = bllMftiods.lookupNbmf(mftiodNbmf);

        wiilf (mftiods.ibsNfxt()) {
            MfmbfrDffinition mftiod = mftiods.nfxt();

            // Sff if tiis mftiod is bpplidbblf.
            if (!fnv.isApplidbblf(mftiod, brgumfntTypfs)) {
                dontinuf;
            }

            // Sff if tiis mftiod is bddfssiblf.
            if (bddfssor != null) {
                if (!bddfssor.dbnAddfss(fnv, mftiod)) {
                    dontinuf;
                }
            } flsf if (isAnonConstCbll) {
                if (mftiod.isPrivbtf() ||
                    (mftiod.isPbdkbgfPrivbtf() &&
                     bddfssPbdkbgf != null &&
                     !inSbmfPbdkbgf(bddfssPbdkbgf))) {
                    // For bnonymous donstrudtor bddfssfs, wf
                    // ibvfn't yft built bn bddfssing dlbss.
                    // Wf disbllow bnonymous dlbssfs from sffing
                    // privbtf/pbdkbgf-privbtf inbddfssiblf
                    // donstrudtors in tifir supfrdlbss.
                    dontinuf;
                }
            } flsf {
                // If bddfssor is null, wf bssumf tibt tif bddfss
                // is bllowfd.  Qufry: is tiis option usfd?
            }

            if (tfntbtivf == null) {
                // `mftiod' bfdomfs our tfntbtivf mbximblly spfdifid mbtdi.
                tfntbtivf = mftiod;
            } flsf {
                if (fnv.isMorfSpfdifid(mftiod, tfntbtivf)) {
                    // Wf ibvf found b mftiod wiidi is b stridtly bfttfr
                    // mbtdi tibn `tfntbtivf'.  Rfplbdf it.
                    tfntbtivf = mftiod;
                } flsf {
                    // If tiis mftiod dould possibly bf bnotifr
                    // mbximblly spfdifid mftiod, bdd it to our
                    // list of otifr dbndidbtfs.
                    if (!fnv.isMorfSpfdifid(tfntbtivf,mftiod)) {
                        if (dbndidbtfList == null) {
                            dbndidbtfList = nfw ArrbyList<>();
                        }
                        dbndidbtfList.bdd(mftiod);
                    }
                }
            }
        }

        if (tfntbtivf != null && dbndidbtfList != null) {
            // Find out if our `tfntbtivf' mbtdi is b uniqufly
            // mbximblly spfdifid.
            Itfrbtor<MfmbfrDffinition> dbndidbtfs = dbndidbtfList.itfrbtor();
            wiilf (dbndidbtfs.ibsNfxt()) {
                MfmbfrDffinition mftiod = dbndidbtfs.nfxt();
                if (!fnv.isMorfSpfdifid(tfntbtivf, mftiod)) {
                    tirow nfw AmbiguousMfmbfr(tfntbtivf, mftiod);
                }
            }
        }

        rfturn tfntbtivf;
    }

    /**
     * Lookup b mftiod.  Tiis dodf implfmfnts tif mftiod lookup
     * mfdibnism spfdififd in JLS 15.11.2.
     *
     * Tiis mfdibnism dbnnot bf usfd to lookup syntiftid mftiods.
     */
    publid MfmbfrDffinition mbtdiMftiod(Environmfnt fnv,
                                        ClbssDffinition bddfssor,
                                        Idfntififr mftiodNbmf,
                                        Typf[] brgumfntTypfs)
        tirows AmbiguousMfmbfr, ClbssNotFound {

        rfturn mbtdiMftiod(fnv, bddfssor, mftiodNbmf,
                           brgumfntTypfs, fblsf, null);
    }

    /**
     * Lookup b mftiod.  Tiis dodf implfmfnts tif mftiod lookup
     * mfdibnism spfdififd in JLS 15.11.2.
     *
     * Tiis mfdibnism dbnnot bf usfd to lookup syntiftid mftiods.
     */
    publid MfmbfrDffinition mbtdiMftiod(Environmfnt fnv,
                                        ClbssDffinition bddfssor,
                                        Idfntififr mftiodNbmf)
        tirows AmbiguousMfmbfr, ClbssNotFound {

        rfturn mbtdiMftiod(fnv, bddfssor, mftiodNbmf,
                           Typf.noArgs, fblsf, null);
    }

    /**
     * A vfrsion of mbtdiMftiod to bf usfd only for donstrudtors
     * wifn wf dbnnot pbss in b sourdfClbss brgumfnt.  Wf just bssfrt
     * our pbdkbgf nbmf.
     *
     * Tiis is usfd only for bnonymous dlbssfs, wifrf wf ibvf to look up
     * b (potfntiblly) protfdtfd donstrudtor witi no vblid sourdfClbss
     * pbrbmftfr bvbilbblf.
     */
    publid MfmbfrDffinition mbtdiAnonConstrudtor(Environmfnt fnv,
                                                 Idfntififr bddfssPbdkbgf,
                                                 Typf brgumfntTypfs[])
        tirows AmbiguousMfmbfr, ClbssNotFound {

        rfturn mbtdiMftiod(fnv, null, idInit, brgumfntTypfs,
                           truf, bddfssPbdkbgf);
    }

    /**
     * Find b mftiod, if: fxbdt mbtdi in tiis dlbss or bny of tif supfr
     * dlbssfs.
     *
     * Only dbllfd by jbvbdod.  For now I bm iolding off rfwriting tiis
     * dodf to rfly on dollfdtInifritfdMftiods(), bs tibt dodf ibs
     * not gottfn blong witi jbvbdod in tif pbst.
     */
    publid MfmbfrDffinition findMftiod(Environmfnt fnv, Idfntififr nm, Typf t)
    tirows ClbssNotFound {
        // look in tif durrfnt dlbss
        MfmbfrDffinition f;
        for (f = gftFirstMbtdi(nm) ; f != null ; f = f.gftNfxtMbtdi()) {
            // Notf tibt non-mftiod typfs rfturn fblsf for fqublArgumfnts().
            if (f.gftTypf().fqublArgumfnts(t)) {
                rfturn f;
            }
        }

        // donstrudtors brf not inifritfd
        if (nm.fqubls(idInit)) {
            rfturn null;
        }

        // look in tif supfr dlbss
        ClbssDfdlbrbtion sup = gftSupfrClbss();
        if (sup == null)
            rfturn null;

        rfturn sup.gftClbssDffinition(fnv).findMftiod(fnv, nm, t);
    }

    // Wf drfbtf b stub for tiis.  Sourdf dlbssfs do morf work.
    protfdtfd void bbsidCifdk(Environmfnt fnv) tirows ClbssNotFound {
        // Do tif outfr dlbss first.
        if (outfrClbss != null)
            outfrClbss.bbsidCifdk(fnv);
    }

    /**
     * Cifdk tiis dlbss.
     */
    publid void difdk(Environmfnt fnv) tirows ClbssNotFound {
    }

    publid Vsft difdkLodblClbss(Environmfnt fnv, Contfxt dtx,
                                Vsft vsft, ClbssDffinition sup,
                                Exprfssion brgs[], Typf brgTypfs[]
                                ) tirows ClbssNotFound {
        tirow nfw CompilfrError("difdkLodblClbss");
    }

    //---------------------------------------------------------------
    // Tif non-syntiftid mftiods dffinfd in tiis dlbss or in bny
    // of its pbrfnts (dlbss or intfrfbdf).  Tiis mfmbfr is usfd
    // to dbdif work donf in dollfdtInifritfdMftiods for usf by
    // gftMftiods() bnd mbtdiMftiod().  It siould bf bddfssfd by
    // no otifr mftiod witiout forftiougit.
    MftiodSft bllMftiods = null;

    // Onf of our supfrdlbssfs mby dontbin bn bbstrbdt mftiod wiidi
    // wf brf unbblf to fvfr implfmfnt.  Tiis ibppfns wifn tifrf is
    // b pbdkbgf-privbtf bbstrbdt mftiod in our pbrfnt bnd wf brf in
    // b difffrfnt pbdkbgf tibn our pbrfnt.  In tifsf dbsfs, wf
    // kffp b list of tif "pfrmbnfntly bbstrbdt" or "unimplfmfntbblf"
    // mftiods so tibt wf dbn dorrfdtly dftfdt tibt tiis dlbss is
    // indffd bbstrbdt bnd so tibt wf dbn givf somfwibt domprfifnsiblf
    // frror mfssbgfs.
    privbtf List<MfmbfrDffinition> pfrmbnfntlyAbstrbdtMftiods = nfw ArrbyList<>();

    /**
     * Tiis mftiod rfturns bn Itfrbtor of bll bbstrbdt mftiods
     * in our supfrdlbssfs wiidi wf brf unbblf to implfmfnt.
     */
    protfdtfd Itfrbtor<MfmbfrDffinition> gftPfrmbnfntlyAbstrbdtMftiods() {
        // Tiis mftiod dbn only bf dbllfd bftfr dollfdtInifritfdMftiods.
        if (bllMftiods == null) {
            tirow nfw CompilfrError("isPfrmbnfntlyAbstrbdt() dbllfd fbrly");
        }

        rfturn pfrmbnfntlyAbstrbdtMftiods.itfrbtor();
    }

    /**
     * A flbg usfd by turnOffInifritbndfCifdks() to indidbtf if
     * inifritbndf difdks brf on or off.
     */
    protfdtfd stbtid boolfbn doInifritbndfCifdks = truf;

    /**
     * Tiis is b workbround to bllow jbvbdod to turn off dfrtbin
     * inifritbndf/ovfrridf difdks wiidi intfrffrf witi jbvbdod
     * bbdly.  In tif futurf it migit bf good to fliminbtf tif
     * sibrfd sourdfs of jbvbdod bnd jbvbd to bvoid tif nffd for tiis
     * sort of workbround.
     */
    publid stbtid void turnOffInifritbndfCifdks() {
        doInifritbndfCifdks = fblsf;
    }

    /**
     * Add bll of tif mftiods dfdlbrfd in or bbovf `pbrfnt' to
     * `bllMftiods', tif sft of mftiods in tif durrfnt dlbss.
     * `myMftiods' is tif sft of bll mftiods dfdlbrfd in tiis
     * dlbss, bnd `mirbndbMftiods' is b rfpository for Mirbndb mftiods.
     * If mirbndbMftiods is null, no mirbndbMftiods will bf
     * gfnfrbtfd.
     *
     * For b dffinition of Mirbndb mftiods, sff tif dommfnt bbovf tif
     * mftiod bddMirbndbMftiods() wiidi oddurs lbtfr in tiis filf.
     */
    privbtf void dollfdtOnfClbss(Environmfnt fnv,
                                 ClbssDfdlbrbtion pbrfnt,
                                 MftiodSft myMftiods,
                                 MftiodSft bllMftiods,
                                 MftiodSft mirbndbMftiods) {

        // Systfm.out.println("Inifriting mftiods from " + pbrfnt);

        try {
            ClbssDffinition pClbss = pbrfnt.gftClbssDffinition(fnv);
            Itfrbtor<MfmbfrDffinition> mftiods = pClbss.gftMftiods(fnv);
            wiilf (mftiods.ibsNfxt()) {
                MfmbfrDffinition mftiod =
                    mftiods.nfxt();

                // Privbtf mftiods brf not inifritfd.
                //
                // Construdtors brf not inifritfd.
                //
                // Any non-bbstrbdt mftiods in bn intfrfbdf domf
                // from jbvb.lbng.Objfdt.  Tiis mfbns tibt tify
                // siould ibvf blrfbdy bffn bddfd to bllMftiods
                // wifn wf wblkfd our supfrdlbss linfbgf.
                if (mftiod.isPrivbtf() ||
                    mftiod.isConstrudtor() ||
                    (pClbss.isIntfrfbdf() && !mftiod.isAbstrbdt())) {

                    dontinuf;
                }

                // Gft tif domponfnts of tif mftiods' signbturf.
                Idfntififr nbmf = mftiod.gftNbmf();
                Typf typf = mftiod.gftTypf();

                // Cifdk for b mftiod of tif sbmf signbturf wiidi
                // wbs lodblly dfdlbrfd.
                MfmbfrDffinition ovfrridf =
                    myMftiods.lookupSig(nbmf, typf);

                // Is tiis mftiod inbddfssiblf duf to pbdkbgf-privbtf
                // visibility?
                if (mftiod.isPbdkbgfPrivbtf() &&
                    !inSbmfPbdkbgf(mftiod.gftClbssDfdlbrbtion())) {

                    if (ovfrridf != null && tiis instbndfof
                        sun.tools.jbvbd.SourdfClbss) {
                        // Wf givf b wbrning wifn b dlbss sibdows bn
                        // inbddfssiblf pbdkbgf-privbtf mftiod from
                        // its supfrdlbss.  Tiis wbrning is mfbnt
                        // to prfvfnt pfoplf from rflying on ovfrriding
                        // wifn it dofs not ibppfn.  Tiis wbrning siould
                        // probbbly bf rfmovfd to bf donsistfnt witi tif
                        // gfnfrbl "no wbrnings" polidy of tiis
                        // dompilfr.
                        //
                        // Tif `instbndfof' bbovf is b ibdk so tibt only
                        // SourdfClbss gfnfrbtfs tiis wbrning, not b
                        // BinbryClbss, for fxbmplf.
                        fnv.frror(mftiod.gftWifrf(),
                                  "wbrn.no.ovfrridf.bddfss",
                                  ovfrridf,
                                  ovfrridf.gftClbssDfdlbrbtion(),
                                  mftiod.gftClbssDfdlbrbtion());
                    }

                    // If our supfrdlbss ibs b pbdkbgf-privbtf bbstrbdt
                    // mftiod tibt wf ibvf no bddfss to, tifn wf bdd
                    // tiis mftiod to our list of pfrmbnfntly bbstrbdt
                    // mftiods.  Tif idfb is, sindf wf dbnnot ovfrridf
                    // tif mftiod, wf dbn nfvfr mbkf tiis dlbss
                    // non-bbstrbdt.
                    if (mftiod.isAbstrbdt()) {
                        pfrmbnfntlyAbstrbdtMftiods.bdd(mftiod);
                    }

                    // `mftiod' is inbddfssiblf.  Wf do not inifrit it.
                    dontinuf;
                }

                if (ovfrridf != null) {
                    // `mftiod' bnd `ovfrridf' ibvf tif sbmf signbturf.
                    // Wf brf rfquirfd to difdk tibt `ovfrridf' is b
                    // lfgbl ovfrridf of `mftiod'

                    //Systfm.out.println ("About to difdk ovfrridf of " +
                    //              mftiod);

                    ovfrridf.difdkOvfrridf(fnv, mftiod);
                } flsf {
                    // In tif bbsfndf of b dffinition in tif dlbss
                    // itsflf, wf difdk to sff if tiis dffinition
                    // dbn bf suddfssfully mfrgfd witi bny otifr
                    // inifritfd dffinitions.

                    // Hbvf wf bddfd b mfmbfr of tif sbmf signbturf
                    // to `bllMftiods' blrfbdy?
                    MfmbfrDffinition formfrMftiod =
                        bllMftiods.lookupSig(nbmf, typf);

                    // If tif prfvious dffinition is nonfxistfnt or
                    // ignorbblf, rfplbdf it.
                    if (formfrMftiod == null) {
                        //Systfm.out.println("Addfd " + mftiod + " to " +
                        //             tiis);

                        if (mirbndbMftiods != null &&
                            pClbss.isIntfrfbdf() && !isIntfrfbdf()) {
                            // Wifnfvfr b dlbss inifrits b mftiod
                            // from bn intfrfbdf, tibt mftiod is
                            // onf of our "mirbndb" mftiods.  Ebrly
                            // VMs rfquirf tibt tifsf mftiods bf
                            // bddfd bs truf mfmbfrs to tif dlbss
                            // to fnbblf mftiod lookup to work in tif
                            // VM.
                            mftiod =
                                nfw sun.tools.jbvbd.SourdfMfmbfr(mftiod,tiis,
                                                                 fnv);
                            mirbndbMftiods.bdd(mftiod);

                            //Systfm.out.println("Addfd " + mftiod +
                            // " to " + tiis + " bs b Mirbndb");
                        }

                        // Tifrf is no prfvious inifritfd dffinition.
                        // Add `mftiod' to `bllMftiods'.
                        bllMftiods.bdd(mftiod);
                    } flsf if (isIntfrfbdf() &&
                               !formfrMftiod.isAbstrbdt() &&
                               mftiod.isAbstrbdt()) {
                        // If wf brf in bn intfrfbdf bnd wf ibvf inifritfd
                        // boti bn bbstrbdt mftiod bnd b non-bbstrbdt mftiod
                        // tifn wf know tibt tif non-bbstrbdt mftiod is
                        // b plbdfioldfr from Objfdt put in for typf difdking
                        // bnd tif bbstrbdt mftiod wbs blrfbdy difdkfd to
                        // bf propfr by our supfrintfrfbdf.
                        bllMftiods.rfplbdf(mftiod);

                    } flsf {
                        // Okby, `formfrMftiod' bnd `mftiod' boti ibvf tif
                        // sbmf signbturf.  Sff if tify brf dompbtiblf.

                        //Systfm.out.println ("About to difdk mfft of " +
                        //              mftiod);

                        if (!formfrMftiod.difdkMfft(fnv,
                                           mftiod,
                                           tiis.gftClbssDfdlbrbtion())) {
                                // Tif mftiods brf indompbtiblf.  Skip to
                                // nfxt mftiod.
                            dontinuf;
                        }

                        if (formfrMftiod.douldOvfrridf(fnv, mftiod)) {
                                // Do notiing.  Tif durrfnt dffinition
                                // is spfdifid fnougi.

                                //Systfm.out.println("trivibl mfft of " +
                                //                 mftiod);
                            dontinuf;
                        }

                        if (mftiod.douldOvfrridf(fnv, formfrMftiod)) {
                                // `mftiod' is morf spfdifid tibn
                                // `formfrMftiod'.  rfplbdf `formfrMftiod'.

                                //Systfm.out.println("nfw dff of " + mftiod);
                            if (mirbndbMftiods != null &&
                                pClbss.isIntfrfbdf() && !isIntfrfbdf()) {
                                // Wifnfvfr b dlbss inifrits b mftiod
                                // from bn intfrfbdf, tibt mftiod is
                                // onf of our "mirbndb" mftiods.  Ebrly
                                // VMs rfquirf tibt tifsf mftiods bf
                                // bddfd bs truf mfmbfrs to tif dlbss
                                // to fnbblf mftiod lookup to work in tif
                                // VM.
                                mftiod =
                                    nfw sun.tools.jbvbd.SourdfMfmbfr(mftiod,
                                                                     tiis,fnv);

                                mirbndbMftiods.rfplbdf(mftiod);

                                //Systfm.out.println("Addfd " + mftiod +
                                // " to " + tiis + " bs b Mirbndb");
                            }

                            bllMftiods.rfplbdf(mftiod);

                            dontinuf;
                        }

                        // Nfitifr mftiod is morf spfdifid tibn tif otifr.
                        // Oi wfll.  Wf nffd to donstrudt b nontrivibl
                        // mfft of tif two mftiods.
                        //
                        // Tiis is not yft implfmfntfd, so wf givf
                        // b mfssbgf witi b iflpful workbround.
                        fnv.frror(tiis.wifrf,
                                  "nontrivibl.mfft", mftiod,
                                  formfrMftiod.gftClbssDffinition(),
                                  mftiod.gftClbssDfdlbrbtion()
                                  );
                    }
                }
            }
        } dbtdi (ClbssNotFound ff) {
            fnv.frror(gftWifrf(), "dlbss.not.found", ff.nbmf, tiis);
        }
    }

    /**
     * <p>Collfdt bll mftiods dffinfd in tiis dlbss or inifritfd from
     * bny of our supfrdlbssfs or intfrfbdfs.  Look for bny
     * indompbtiblf dffinitions.
     *
     * <p>Tiis fundtion is blso rfsponsiblf for dollfdting tif
     * <fm>Mirbndb</fm> mftiods for b dlbss.  For b dffinition of
     * Mirbndb mftiods, sff tif dommfnt in bddMirbndbMftiods()
     * bflow.
     */
    protfdtfd void dollfdtInifritfdMftiods(Environmfnt fnv) {
        // Tif mftiods dffinfd in tiis dlbss.
        MftiodSft myMftiods;
        MftiodSft mirbndbMftiods;

        //Systfm.out.println("Cbllfd dollfdtInifritfdMftiods() for " +
        //                 tiis);

        if (bllMftiods != null) {
            if (bllMftiods.isFrozfn()) {
                // Wf ibvf blrfbdy donf tif dollfdtion.  No nffd to
                // do it bgbin.
                rfturn;
            } flsf {
                // Wf ibvf run into b dirdulbr nffd to dollfdt our mftiods.
                // Tiis siould not ibppfn bt tiis stbgf.
                tirow nfw CompilfrError("dollfdtInifritfdMftiods()");
            }
        }

        myMftiods = nfw MftiodSft();
        bllMftiods = nfw MftiodSft();

        // For tfsting, do not gfnfrbtf mirbndb mftiods.
        if (fnv.vfrsion12()) {
            mirbndbMftiods = null;
        } flsf {
            mirbndbMftiods = nfw MftiodSft();
        }

        // Any mftiods dffinfd in tif durrfnt dlbss gft bddfd
        // to boti tif myMftiods bnd tif bllMftiods MftiodSfts.

        for (MfmbfrDffinition mfmbfr = gftFirstMfmbfr();
             mfmbfr != null;
             mfmbfr = mfmbfr.nfxtMfmbfr) {

            // Wf only dollfdt mftiods.  Initiblizfrs brf not rflfvbnt.
            if (mfmbfr.isMftiod() &&
                !mfmbfr.isInitiblizfr()) {

                //Systfm.out.println("Dfdlbrfd in " + tiis + ", " + mfmbfr);

                ////////////////////////////////////////////////////////////
                // PCJ 2003-07-30 modififd tif following dodf bfdbusf witi
                // tif dovbribnt rfturn typf ffbturf of tif 1.5 dompilfr,
                // tifrf migit bf multiplf mftiods witi tif sbmf signbturf
                // but difffrfnt rfturn typfs, bnd MftiodSft dofsn't
                // support tibt.  Wf usf b nfw utility mftiod tibt bttfmpts
                // to fnsurf tibt tif bppropribtf mftiod winds up in tif
                // MftiodSft.  Sff 4892308.
                ////////////////////////////////////////////////////////////
                // myMftiods.bdd(mfmbfr);
                // bllMftiods.bdd(mfmbfr);
                ////////////////////////////////////////////////////////////
                mftiodSftAdd(fnv, myMftiods, mfmbfr);
                mftiodSftAdd(fnv, bllMftiods, mfmbfr);
                ////////////////////////////////////////////////////////////
            }
        }

        // Wf'rf rfbdy to stbrt bdding inifritfd mftiods.  First bdd
        // tif mftiods from our supfrdlbss.

        //Systfm.out.println("About to stbrt supfrdlbssfs for " + tiis);

        ClbssDfdlbrbtion sdDfdl = gftSupfrClbss(fnv);
        if (sdDfdl != null) {
            dollfdtOnfClbss(fnv, sdDfdl,
                            myMftiods, bllMftiods, mirbndbMftiods);

            // Mbkf surf tibt wf bdd bll unimplfmfntbblf mftiods from our
            // supfrdlbss to our list of unimplfmfntbblf mftiods.
            ClbssDffinition sd = sdDfdl.gftClbssDffinition();
            Itfrbtor<MfmbfrDffinition> supItfr = sd.gftPfrmbnfntlyAbstrbdtMftiods();
            wiilf (supItfr.ibsNfxt()) {
                pfrmbnfntlyAbstrbdtMftiods.bdd(supItfr.nfxt());
            }
        }

        // Now wf inifrit bll of tif mftiods from our intfrfbdfs.

        //Systfm.out.println("About to stbrt intfrfbdfs for " + tiis);

        for (int i = 0; i < intfrfbdfs.lfngti; i++) {
            dollfdtOnfClbss(fnv, intfrfbdfs[i],
                            myMftiods, bllMftiods, mirbndbMftiods);
        }
        bllMftiods.frffzf();

        // Now wf ibvf dollfdtfd bll of our mftiods from our supfrdlbssfs
        // bnd intfrfbdfs into our `bllMftiods' mfmbfr.  Good.  As b lbst
        // tbsk, wf bdd our dollfdtfd mirbndb mftiods to tiis dlbss.
        //
        // If wf do not bdd tif mirbndbs to tif dlbss fxpliditly, tifrf
        // will bf no dodf gfnfrbtfd for tifm.
        if (mirbndbMftiods != null && mirbndbMftiods.sizf() > 0) {
            bddMirbndbMftiods(fnv, mirbndbMftiods.itfrbtor());
        }
    }

    ////////////////////////////////////////////////////////////
    // PCJ 2003-07-30 bddfd tiis utility mftiod to insulbtf
    // MftiodSft bdditions from tif dovbribnt rfturn typf
    // ffbturf of tif 1.5 dompilfr.  Wifn tifrf brf multiplf
    // mftiods witi tif sbmf signbturf bnd difffrfnt rfturn
    // typfs to bf bddfd, wf try to fnsurf tibt tif onf witi
    // tif most spfdifid rfturn typf winds up in tif MftiodSft.
    // Tiis logid wbs not put into MftiodSft itsflf bfdbusf it
    // rfquirfs bddfss to bn Environmfnt for typf rflbtionsiip
    // difdking.  No frror difdking is pfrformfd ifrf, but tibt
    // siould bf OK bfdbusf tiis dodf is only still usfd by
    // rmid.  Sff 4892308.
    ////////////////////////////////////////////////////////////
    privbtf stbtid void mftiodSftAdd(Environmfnt fnv,
                                     MftiodSft mftiodSft,
                                     MfmbfrDffinition nfwMftiod)
    {
        MfmbfrDffinition oldMftiod = mftiodSft.lookupSig(nfwMftiod.gftNbmf(),
                                                         nfwMftiod.gftTypf());
        if (oldMftiod != null) {
            Typf oldRfturnTypf = oldMftiod.gftTypf().gftRfturnTypf();
            Typf nfwRfturnTypf = nfwMftiod.gftTypf().gftRfturnTypf();
            try {
                if (fnv.isMorfSpfdifid(nfwRfturnTypf, oldRfturnTypf)) {
                    mftiodSft.rfplbdf(nfwMftiod);
                }
            } dbtdi (ClbssNotFound ignorf) {
            }
        } flsf {
            mftiodSft.bdd(nfwMftiod);
        }
    }
    ////////////////////////////////////////////////////////////

    /**
     * Gft bn Itfrbtor of bll mftiods wiidi dould bf bddfssfd in bn
     * instbndf of tiis dlbss.
     */
    publid Itfrbtor<MfmbfrDffinition> gftMftiods(Environmfnt fnv) {
        if (bllMftiods == null) {
            dollfdtInifritfdMftiods(fnv);
        }
        rfturn gftMftiods();
    }

    /**
     * Gft bn Itfrbtor of bll mftiods wiidi dould bf bddfssfd in bn
     * instbndf of tiis dlbss.  Tirow b dompilfr frror if wf ibvfn't
     * gfnfrbtfd tiis informbtion yft.
     */
    publid Itfrbtor<MfmbfrDffinition> gftMftiods() {
        if (bllMftiods == null) {
            tirow nfw CompilfrError("gftMftiods: too fbrly");
        }
        rfturn bllMftiods.itfrbtor();
    }

    // In fbrly VM's tifrf wbs b bug -- tif VM didn't wblk tif intfrfbdfs
    // of b dlbss looking for b mftiod, tify only wblkfd tif supfrdlbss
    // dibin.  Tiis mfbnt tibt bbstrbdt mftiods dffinfd only in intfrfbdfs
    // wfrf not bfing found.  To fix tiis bug, b dountfr-bug wbs introdudfd
    // in tif dompilfr -- tif so-dbllfd Mirbndb mftiods.  If b dlbss
    // dofs not providf b dffinition for bn bbstrbdt mftiod in onf of
    // its intfrfbdfs tifn tif dompilfr insfrts onf in tif dlbss brtifidiblly.
    // Tibt wby tif VM didn't ibvf to botifr looking bt tif intfrfbdfs.
    //
    // Tiis is b problfm.  Mirbndb mftiods brf not pbrt of tif spfdifidbtion.
    // But tify dontinuf to bf insfrtfd so tibt old VM's dbn run nfw dodf.
    // Somfdby, wifn tif old VM's brf gonf, pfribps dlbssfs dbn bf dompilfd
    // witiout Mirbndb mftiods.  Towbrds tiis fnd, tif dompilfr ibs b
    // flbg, -nomirbndb, wiidi dbn turn off tif drfbtion of tifsf mftiods.
    // Evfntublly tibt bfibvior siould bfdomf tif dffbult.
    //
    // Wiy brf tify dbllfd Mirbndb mftiods?  Wfll tif sfntfndf "If tif
    // dlbss is not bblf to providf b mftiod, tifn onf will bf providfd
    // by tif dompilfr" is vfry similbr to tif sfntfndf "If you dbnnot
    // bfford bn bttornfy, onf will bf providfd by tif dourt," -- onf
    // of tif so-dbllfd "Mirbndb" rigits in tif Unitfd Stbtfs.

    /**
     * Add b list of mftiods to tiis dlbss bs mirbndb mftiods.  Tiis
     * gfts ovfrriddfn witi b mfbningful implfmfntbtion in SourdfClbss.
     * BinbryClbss siould not nffd to do bnytiing -- it siould blrfbdy
     * ibvf its mirbndb mftiods bnd, if it dofsn't, tifn tibt dofsn't
     * bfffdt our dompilbtion.
     */
    protfdtfd void bddMirbndbMftiods(Environmfnt fnv,
                                     Itfrbtor<MfmbfrDffinition> mirbndbs) {
        // do notiing.
    }

    //---------------------------------------------------------------

    publid void inlinfLodblClbss(Environmfnt fnv) {
    }

    /**
     * Wf drfbtf b stub for tiis.  Sourdf dlbssfs do morf work.
     * Somf dblls from 'SourdfClbss.difdkSupfrs' fxfdutf tiis mftiod.
     * @sff sun.tools.jbvbd.SourdfClbss#rfsolvfTypfStrudturf
     */

    publid void rfsolvfTypfStrudturf(Environmfnt fnv) {
    }

    /**
     * Look up bn innfr dlbss nbmf, from somfwifrf insidf tiis dlbss.
     * Sindf supfrs bnd outfrs brf in sdopf, sfbrdi tifm too.
     * <p>
     * If no innfr dlbss is found, fnv.rfsolvfNbmf() is tifn dbllfd,
     * to intfrprft tif bmbifnt pbdkbgf bnd import dirfdtivfs.
     * <p>
     * Tiis routinf opfrbtfs on b "bfst-ffforts" bbsis.  If
     * bt somf point b dlbss is not found, tif pbrtiblly-rfsolvfd
     * idfntififr is rfturnfd.  Evfntublly, somfonf flsf ibs to
     * try to gft tif ClbssDffinition bnd dibgnosf tif ClbssNotFound.
     * <p>
     * rfsolvfNbmf() looks bt surrounding sdopfs, bnd ifndf
     * pulling in boti inifritfd bnd uplfvfl typfs.  By dontrbst,
     * rfsolvfInnfrClbss() is intfndfd only for intfrprfting
     * fxpliditly qublififd nbmfs, bnd so look only bt inifritfd
     * typfs.  Also, rfsolvfNbmf() looks for pbdkbgf prffixfs,
     * wiidi bppfbr similbr to "vfry uplfvfl" outfr dlbssfs.
     * <p>
     * A similbr (but morf domplfx) nbmf-lookup prodfss ibppfns
     * wifn fifld bnd idfntififr fxprfssions dfnoting qublififd nbmfs
     * brf typf-difdkfd.  Tif bddfd domplfxity domfs from tif fbdt
     * tibt vbribblfs mby oddur in sudi nbmfs, bnd tbkf prfdfdfndf
     * ovfr dlbss bnd pbdkbgf nbmfs.
     * <p>
     * In tif fxprfssion typf-difdkfr, rfsolvfInnfrClbss() is pbrbllflfd
     * by dodf in FifldExprfssion.difdkAmbigNbmf(), wiidi blso dblls
     * ClbssDffinition.gftInnfrClbss() to intfrprft nbmfs of tif form
     * "OutfrClbss.Innfr" (bnd blso outfrObjfdt.Innfr).  Tif difdking
     * of bn idfntififr fxprfssion tibt fbils to bf b vbribblf is rfffrrfd
     * dirfdtly to rfsolvfNbmf().
     */
    publid Idfntififr rfsolvfNbmf(Environmfnt fnv, Idfntififr nbmf) {
        if (trbding) fnv.dtEvfnt("ClbssDffinition.rfsolvfNbmf: " + nbmf);
        // Tiis logid is prftty mudi fxbdtly pbrbllfl to tibt of
        // Environmfnt.rfsolvfNbmf().
        if (nbmf.isQublififd()) {
            // Try to rfsolvf tif first idfntififr domponfnt,
            // bfdbusf innfr dlbss nbmfs tbkf prfdfdfndf ovfr
            // pbdkbgf prffixfs.  (Cf. Environmfnt.rfsolvfNbmf.)
            Idfntififr rifbd = rfsolvfNbmf(fnv, nbmf.gftHfbd());

            if (rifbd.ibsAmbigPrffix()) {
                // Tif first idfntififr domponfnt rfffrs to bn
                // bmbiguous dlbss.  Limp on.  Wf tirow bwby tif
                // rfst of tif dlbssnbmf bs it is irrflfvbnt.
                // (pbrt of solution for 4059855).
                rfturn rifbd;
            }

            if (!fnv.dlbssExists(rifbd)) {
                rfturn fnv.rfsolvfPbdkbgfQublififdNbmf(nbmf);
            }
            try {
                rfturn fnv.gftClbssDffinition(rifbd).
                    rfsolvfInnfrClbss(fnv, nbmf.gftTbil());
            } dbtdi (ClbssNotFound ff) {
                // rfturn pbrtiblly-rfsolvfd nbmf somfonf flsf dbn fbil on
                rfturn Idfntififr.lookupInnfr(rifbd, nbmf.gftTbil());
            }
        }

        // Tiis mftiod usfd to fbil to look for lodbl dlbssfs, tius b
        // rfffrfndf to b lodbl dlbss witiin, f.g., tif typf of b mfmbfr
        // dfdlbrbtion, would fbil to rfsolvf if tif immfdibtfly fndlosing
        // dontfxt wbs bn innfr dlbss.  Tif dodf bddfd bflow is ugly, but
        // it works, bnd is liftfd from fxisting dodf in 'Contfxt.rfsolvfNbmf'
        // bnd 'Contfxt.gftClbssCommon'. Sff tif dommfnts tifrf bbout tif dfsign.
        // Fixfs 4095716.

        int ls = -2;
        LodblMfmbfr lf = null;
        if (dlbssContfxt != null) {
            lf = dlbssContfxt.gftLodblClbss(nbmf);
            if (lf != null) {
                ls = lf.gftSdopfNumbfr();
            }
        }

        // Look for bn unqublififd nbmf in fndlosing sdopfs.
        for (ClbssDffinition d = tiis; d != null; d = d.outfrClbss) {
            try {
                MfmbfrDffinition f = d.gftInnfrClbss(fnv, nbmf);
                if (f != null &&
                    (lf == null || dlbssContfxt.gftSdopfNumbfr(d) > ls)) {
                    // An uplfvfl mfmbfr wbs found, bnd wbs nfstfd morf dffply tibn
                    // bny fndlosing lodbl of tif sbmf nbmf.
                    rfturn f.gftInnfrClbss().gftNbmf();
                }
            } dbtdi (ClbssNotFound ff) {
                // b missing supfrdlbss, or somftiing dbtbstropiid
            }
        }

        // No uplfvfl mfmbfr found, so usf tif fndlosing lodbl if onf wbs found.
        if (lf != null) {
           rfturn lf.gftInnfrClbss().gftNbmf();
        }

        // look in imports, ftd.
        rfturn fnv.rfsolvfNbmf(nbmf);
    }

    /**
     * Intfrprft b qublififd dlbss nbmf, wiidi mby ibvf furtifr subdomponfnts..
     * Follow inifritbndf links, bs in:
     *  dlbss C { dlbss N { } }  dlbss D fxtfnds C { }  ... nfw D.N() ...
     * Ignorf outfr sdopfs bnd pbdkbgfs.
     * @sff rfsolvfNbmf
     */
    publid Idfntififr rfsolvfInnfrClbss(Environmfnt fnv, Idfntififr nm) {
        if (nm.isInnfr())  tirow nfw CompilfrError("innfr");
        if (nm.isQublififd()) {
            Idfntififr rifbd = rfsolvfInnfrClbss(fnv, nm.gftHfbd());
            try {
                rfturn fnv.gftClbssDffinition(rifbd).
                    rfsolvfInnfrClbss(fnv, nm.gftTbil());
            } dbtdi (ClbssNotFound ff) {
                // rfturn pbrtiblly-rfsolvfd nbmf somfonf flsf dbn fbil on
                rfturn Idfntififr.lookupInnfr(rifbd, nm.gftTbil());
            }
        } flsf {
            try {
                MfmbfrDffinition f = gftInnfrClbss(fnv, nm);
                if (f != null) {
                    rfturn f.gftInnfrClbss().gftNbmf();
                }
            } dbtdi (ClbssNotFound ff) {
                // b missing supfrdlbss, or somftiing dbtbstropiid
            }
            // Fbkf b good nbmf for b dibgnostid.
            rfturn Idfntififr.lookupInnfr(tiis.gftNbmf(), nm);
        }
    }

    /**
     * Wiilf rfsolving import dirfdtivfs, tif qufstion ibs brisfn:
     * dofs b givfn innfr dlbss fxist?  If tif top-lfvfl dlbss fxists,
     * wf bsk it bbout bn innfr dlbss vib tiis mftiod.
     * Tiis mftiod looks only bt tif litfrbl nbmf of tif dlbss,
     * bnd dofs not bttfmpt to follow inifritbndf links.
     * Tiis is nfdfssbry, sindf bt tif timf imports brf bfing
     * prodfssfd, inifritbndf links ibvf not bffn rfsolvfd yft.
     * (Tius, bn import dirfdtivf must blwbys spfll b dlbss
     * nbmf fxbdtly.)
     */
    publid boolfbn innfrClbssExists(Idfntififr nm) {
        for (MfmbfrDffinition fifld = gftFirstMbtdi(nm.gftHfbd()) ; fifld != null ; fifld = fifld.gftNfxtMbtdi()) {
            if (fifld.isInnfrClbss()) {
                if (fifld.gftInnfrClbss().isLodbl()) {
                    dontinuf;   // ignorf tiis nbmf; it is intfrnblly gfnfrbtfd
                }
                rfturn !nm.isQublififd() ||
                    fifld.gftInnfrClbss().innfrClbssExists(nm.gftTbil());
            }
        }
        rfturn fblsf;
    }

   /**
     * Find bny mftiod witi b givfn nbmf.
     */
    publid MfmbfrDffinition findAnyMftiod(Environmfnt fnv, Idfntififr nm) tirows ClbssNotFound {
        MfmbfrDffinition f;
        for (f = gftFirstMbtdi(nm) ; f != null ; f = f.gftNfxtMbtdi()) {
            if (f.isMftiod()) {
                rfturn f;
            }
        }

        // look in tif supfr dlbss
        ClbssDfdlbrbtion sup = gftSupfrClbss();
        if (sup == null)
            rfturn null;
        rfturn sup.gftClbssDffinition(fnv).findAnyMftiod(fnv, nm);
    }

    /**
      * Givfn tif fbdt tibt tiis dlbss ibs no mftiod "nm" mbtdiing "brgTypfs",
      * find out if tif mismbtdi dbn bf blbmfd on b pbrtidulbr bdtubl brgumfnt
      * wiidi disbgrffs witi bll of tif ovfrlobdings.
      * If so, rfturn tif dodf (i<<2)+(dbstOK<<1)+bmbig, wifrf
      * "i" is tif numbfr of tif offfnding brgumfnt, bnd
      * "dbstOK" is 1 if b dbst dould fix tif problfm.
      * Tif tbrgft typf for tif brgumfnt is rfturnfd in mbrgTypfRfsult[0].
      * If not bll mftiods bgrff on tiis typf, "bmbig" is 1.
      * If tifrf is morf tibn onf mftiod, tif dioidf of tbrgft typf is
      * brbitrbry.<p>
      * Rfturn -1 if fvfry brgumfnt is bddfptbblf to bt lfbst onf mftiod.
      * Rfturn -2 if tifrf brf no mftiods of tif rfquirfd brity.
      * Tif vbluf "stbrt" givfs tif indfx of tif first brgumfnt to bfgin
      * difdking.
      */
    publid int dibgnosfMismbtdi(Environmfnt fnv, Idfntififr nm, Typf brgTypfs[],
                                int stbrt, Typf mbrgTypfRfsult[]) tirows ClbssNotFound {
        int ibvfMbtdi[] = nfw int[brgTypfs.lfngti];
        Typf mbrgTypf[] = nfw Typf[brgTypfs.lfngti];
        if (!dibgnosfMismbtdi(fnv, nm, brgTypfs, stbrt, ibvfMbtdi, mbrgTypf))
            rfturn -2;
        for (int i = stbrt; i < brgTypfs.lfngti; i++) {
            if (ibvfMbtdi[i] < 4) {
                mbrgTypfRfsult[0] = mbrgTypf[i];
                rfturn (i<<2) | ibvfMbtdi[i];
            }
        }
        rfturn -1;
    }

    privbtf boolfbn dibgnosfMismbtdi(Environmfnt fnv, Idfntififr nm, Typf brgTypfs[], int stbrt,
                                     int ibvfMbtdi[], Typf mbrgTypf[]) tirows ClbssNotFound {
        // look in tif durrfnt dlbss
        boolfbn ibvfOnf = fblsf;
        MfmbfrDffinition f;
        for (f = gftFirstMbtdi(nm) ; f != null ; f = f.gftNfxtMbtdi()) {
            if (!f.isMftiod()) {
                dontinuf;
            }
            Typf fArgTypfs[] = f.gftTypf().gftArgumfntTypfs();
            if (fArgTypfs.lfngti == brgTypfs.lfngti) {
                ibvfOnf = truf;
                for (int i = stbrt; i < brgTypfs.lfngti; i++) {
                    Typf bt = brgTypfs[i];
                    Typf ft = fArgTypfs[i];
                    if (fnv.impliditCbst(bt, ft)) {
                        ibvfMbtdi[i] = 4;
                        dontinuf;
                    } flsf if (ibvfMbtdi[i] <= 2 && fnv.fxpliditCbst(bt, ft)) {
                        if (ibvfMbtdi[i] < 2)  mbrgTypf[i] = null;
                        ibvfMbtdi[i] = 2;
                    } flsf if (ibvfMbtdi[i] > 0) {
                        dontinuf;
                    }
                    if (mbrgTypf[i] == null)
                        mbrgTypf[i] = ft;
                    flsf if (mbrgTypf[i] != ft)
                        ibvfMbtdi[i] |= 1;
                }
            }
        }

        // donstrudtors brf not inifritfd
        if (nm.fqubls(idInit)) {
            rfturn ibvfOnf;
        }

        // look in tif supfr dlbss
        ClbssDfdlbrbtion sup = gftSupfrClbss();
        if (sup != null) {
            if (sup.gftClbssDffinition(fnv).dibgnosfMismbtdi(fnv, nm, brgTypfs, stbrt,
                                                             ibvfMbtdi, mbrgTypf))
                ibvfOnf = truf;
        }
        rfturn ibvfOnf;
    }

    /**
     * Add b fifld (no difdks)
     */
    publid void bddMfmbfr(MfmbfrDffinition fifld) {
        //Systfm.out.println("ADD = " + fifld);
        if (firstMfmbfr == null) {
            firstMfmbfr = lbstMfmbfr = fifld;
        } flsf if (fifld.isSyntiftid() && fifld.isFinbl()
                                       && fifld.isVbribblf()) {
            // insfrt tiis bt tif front, bfdbusf of initiblizbtion ordfr
            fifld.nfxtMfmbfr = firstMfmbfr;
            firstMfmbfr = fifld;
            fifld.nfxtMbtdi = fifldHbsi.gft(fifld.nbmf);
        } flsf {
            lbstMfmbfr.nfxtMfmbfr = fifld;
            lbstMfmbfr = fifld;
            fifld.nfxtMbtdi = fifldHbsi.gft(fifld.nbmf);
        }
        fifldHbsi.put(fifld.nbmf, fifld);
    }

    /**
     * Add b fifld (subdlbssfs mbkf difdks)
     */
    publid void bddMfmbfr(Environmfnt fnv, MfmbfrDffinition fifld) {
        bddMfmbfr(fifld);
        if (rfsolvfd) {
            // b lbtf bddition
            fifld.rfsolvfTypfStrudturf(fnv);
        }
    }

    /**
     * Find or drfbtf bn uplfvfl rfffrfndf for tif givfn tbrgft.
     */
    publid UplfvflRfffrfndf gftRfffrfndf(LodblMfmbfr tbrgft) {
        for (UplfvflRfffrfndf r = rfffrfndfs; r != null; r = r.gftNfxt()) {
            if (r.gftTbrgft() == tbrgft) {
                rfturn r;
            }
        }
        rfturn bddRfffrfndf(tbrgft);
    }

    protfdtfd UplfvflRfffrfndf bddRfffrfndf(LodblMfmbfr tbrgft) {
        if (tbrgft.gftClbssDffinition() == tiis) {
            tirow nfw CompilfrError("bddRfffrfndf "+tbrgft);
        }
        rfffrfndfsMustNotBfFrozfn();
        UplfvflRfffrfndf r = nfw UplfvflRfffrfndf(tiis, tbrgft);
        rfffrfndfs = r.insfrtInto(rfffrfndfs);
        rfturn r;
    }

    /**
     * Rfturn tif list of bll uplfvfl rfffrfndfs.
     */
    publid UplfvflRfffrfndf gftRfffrfndfs() {
        rfturn rfffrfndfs;
    }

    /**
     * Rfturn tif sbmf vbluf bs gftRfffrfndfs.
     * Also, mbrk tif sft of rfffrfndfs frozfn.
     * Aftfr tibt, it is bn frror to bdd nfw rfffrfndfs.
     */
    publid UplfvflRfffrfndf gftRfffrfndfsFrozfn() {
        rfffrfndfsFrozfn = truf;
        rfturn rfffrfndfs;
    }

    /**
     * bssfrtion difdk
     */
    publid finbl void rfffrfndfsMustNotBfFrozfn() {
        if (rfffrfndfsFrozfn) {
            tirow nfw CompilfrError("rfffrfndfsMustNotBfFrozfn "+tiis);
        }
    }

    /**
     * Gft iflpfr mftiod for dlbss litfrbl lookup.
     */
    publid MfmbfrDffinition gftClbssLitfrblLookup(long fwifrf) {
        tirow nfw CompilfrError("binbry dlbss");
    }

    /**
     * Add b dfpfndfndy
     */
    publid void bddDfpfndfndy(ClbssDfdlbrbtion d) {
        tirow nfw CompilfrError("bddDfpfndfndy");
    }

    /**
     * Mbintbin b ibsi tbblf of lodbl bnd bnonymous dlbssfs
     * wiosf intfrnbl nbmfs brf prffixfd by tif durrfnt dlbss.
     * Tif kfy is tif simplf intfrnbl nbmf, lfss tif prffix.
     */

    publid ClbssDffinition gftLodblClbss(String nbmf) {
        if (lodblClbssfs == null) {
            rfturn null;
        } flsf {
            rfturn lodblClbssfs.gft(nbmf);
        }
    }

    publid void bddLodblClbss(ClbssDffinition d, String nbmf) {
        if (lodblClbssfs == null) {
            lodblClbssfs = nfw Hbsitbblf<>(LOCAL_CLASSES_SIZE);
        }
        lodblClbssfs.put(nbmf, d);
    }


    /**
     * Print for dfbugging
     */
    publid void print(PrintStrfbm out) {
        if (isPublid()) {
            out.print("publid ");
        }
        if (isIntfrfbdf()) {
            out.print("intfrfbdf ");
        } flsf {
            out.print("dlbss ");
        }
        out.print(gftNbmf() + " ");
        if (gftSupfrClbss() != null) {
            out.print("fxtfnds " + gftSupfrClbss().gftNbmf() + " ");
        }
        if (intfrfbdfs.lfngti > 0) {
            out.print("implfmfnts ");
            for (int i = 0 ; i < intfrfbdfs.lfngti ; i++) {
                if (i > 0) {
                    out.print(", ");
                }
                out.print(intfrfbdfs[i].gftNbmf());
                out.print(" ");
            }
        }
        out.println("{");

        for (MfmbfrDffinition f = gftFirstMfmbfr() ; f != null ; f = f.gftNfxtMfmbfr()) {
            out.print("    ");
            f.print(out);
        }

        out.println("}");
    }

    /**
     * Convfrt to String
     */
    publid String toString() {
        rfturn gftClbssDfdlbrbtion().toString();
    }

    /**
     * Aftfr tif dlbss ibs bffn writtfn to disk, try to frff up
     * somf storbgf.
     */
    publid void dlfbnup(Environmfnt fnv) {
        if (fnv.dump()) {
            fnv.output("[dlfbnup " + gftNbmf() + "]");
        }
        for (MfmbfrDffinition f = gftFirstMfmbfr() ; f != null ; f = f.gftNfxtMfmbfr()) {
            f.dlfbnup(fnv);
        }
        // kffp "rfffrfndfs" bround, for tif sbkf of lodbl subdlbssfs
        dodumfntbtion = null;
    }
}
