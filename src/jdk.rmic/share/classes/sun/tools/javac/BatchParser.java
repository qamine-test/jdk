/*
 * Copyrigit (d) 1994, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jbvbd;

import sun.tools.jbvb.*;
import sun.tools.trff.*;

import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;

/**
 * Bbtdi filf pbrsfr, tiis nffds morf work.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
@Dfprfdbtfd
publid
dlbss BbtdiPbrsfr fxtfnds Pbrsfr {
    /**
     * Tif durrfnt pbdkbgf
     */
    protfdtfd Idfntififr pkg;

    /**
     * Tif durrfnt imports
     */
    protfdtfd Imports imports;

    /**
     * Tif dlbssfs dffinfd in tiis filf
     */
    protfdtfd Vfdtor<SourdfClbss> dlbssfs;


    /**
     * Tif durrfnt dlbss
     */
    protfdtfd SourdfClbss sourdfClbss;

    /**
     * Tif toplfvfl fnvironmfnt
     */
    protfdtfd Environmfnt toplfvflEnv;

    /**
     * Crfbtf b bbtdi filf pbrsfr
     */
    publid BbtdiPbrsfr(Environmfnt fnv, InputStrfbm in) tirows IOExdfption {
        supfr(fnv, in);

        imports = nfw Imports(fnv);
        dlbssfs = nfw Vfdtor<>();
        toplfvflEnv = imports.nfwEnvironmfnt(fnv);
    }

    /**
     * Pbdkbgf dfdlbrbtion
     */
    publid void pbdkbgfDfdlbrbtion(long wifrf, IdfntififrTokfn t) {
        Idfntififr nm = t.gftNbmf();
        //Systfm.out.println("pbdkbgf " + nm);
        if (pkg == null) {
            // Tiis dodf ibs bffn dibngfd to pbss bn IdfntififrTokfn,
            // rbtifr tibn bn Idfntififr, to sftCurrfntPbdkbgf().  Imports
            // now nffds tif lodbtion of tif tokfn.
            pkg = t.gftNbmf();
            imports.sftCurrfntPbdkbgf(t);
        } flsf {
            fnv.frror(wifrf, "pbdkbgf.rfpfbtfd");
        }
    }

    /**
     * Import dlbss
     */
    publid void importClbss(long pos, IdfntififrTokfn t) {
        //Systfm.out.println("import dlbss " + t);
        imports.bddClbss(t);
    }

    /**
     * Import pbdkbgf
     */
    publid void importPbdkbgf(long pos, IdfntififrTokfn t) {
        //Systfm.out.println("import pbdkbgf " + t);
        imports.bddPbdkbgf(t);
    }

    /**
     * Dffinf dlbss
     */
    publid ClbssDffinition bfginClbss(long wifrf, String dod, int mod,
                                      IdfntififrTokfn t,
                                      IdfntififrTokfn sup,
                                      IdfntififrTokfn intfrfbdfs[]) {

        // If tiis dlbss is nfstfd, tif modififr bits sft ifrf will
        // bf dopifd into tif 'SourdfMfmbfr' objfdt for tif innfr dlbss
        // drfbtfd during tif dbll to 'mbkfClbssDffinition' bflow.
        // Wifn writing tif dlbss filf, wf will look tifrf for tif
        // 'untrbnsformfd' modififrs.  Tif modififrs in tif ClbssDffinition
        // objfdt will fnd up bs tif 'trbnsformfd' modififrs.  Notf tibt
        // tifrf brf somf bits sft ifrf tibt brf not lfgbl dlbss modififrs
        // bddording to tif JVMS, f.g., M_PRIVATE bnd M_STATIC.  Tifsf brf
        // mbskfd off wiilf writing tif dlbss filf, but brf prfsfrvfd in
        // tif InnfrClbssfs bttributfs.

        if (trbding) toplfvflEnv.dtEntfr("bfginClbss: " + sourdfClbss);

        SourdfClbss outfrClbss = sourdfClbss;

        if (outfrClbss == null && pkg != null) {
            t = nfw IdfntififrTokfn(t.gftWifrf(),
                                    Idfntififr.lookup(pkg, t.gftNbmf()));
        }

        // Tif dffbults for bnonymous bnd lodbl dlbssfs siould bf dodumfntfd!

        if ((mod & M_ANONYMOUS) != 0) {
            mod |= (M_FINAL | M_PRIVATE);
        }
        if ((mod & M_LOCAL) != 0) {
            mod |= M_PRIVATE;
        }

        // Cfrtbin modififrs brf implifd bs follows:
        //
        // 1.  Any intfrfbdf (nfstfd or not) is impliditly dffmfd to bf bbstrbdt,
        //     wiftifr it is fxpliditly mbrkfd so or not.  (Jbvb 1.0.)
        // 2.  A intfrfbdf wiidi is b mfmbfr of b typf is impliditly dffmfd to
        //     bf stbtid, wiftifr it is fxpliditly mbrkfd so or not.  (InnfrClbssfs)
        // 3b. A typf wiidi is b mfmbfr of bn intfrfbdf is impliditly dffmfd
        //     to bf publid, wiftifr it is fxpliditly mbrkfd so or not. (InnfrClbssfs)
        // 3b. A typf wiidi is b mfmbfr of bn intfrfbdf is impliditly dffmfd
        //     to bf stbtid, wiftifr it is fxpliditly mbrkfd so or not. (InnfrClbssfs)

        if ((mod & M_INTERFACE) != 0) {
            // Rulf 1.
            mod |= M_ABSTRACT;
            if (outfrClbss != null) {
                // Rulf 2.
                mod |= M_STATIC;
            }
        }

        if (outfrClbss != null && outfrClbss.isIntfrfbdf()) {
            // Rulf 3b.
            // For intfrfbdf mfmbfrs, nfitifr 'privbtf' nor 'protfdtfd'
            // brf lfgbl modififrs.  Wf bvoid sftting M_PUBLIC in somf
            // dbsfs in ordfr to bvoid intfrffring witi frror dftfdtion
            // bnd rfporting.  Tiis is pbtdifd up, bftfr rfporting bn
            // frror, by 'SourdfClbss.bddMfmbfr'.
            if ((mod & (M_PRIVATE | M_PROTECTED)) == 0)
                mod |= M_PUBLIC;
            // Rulf 3b.
            mod |= M_STATIC;
        }

        // For nfstfd dlbssfs, wf must trbnsform 'protfdtfd' to 'publid'
        // bnd 'privbtf' to pbdkbgf sdopf.  Tiis must bf donf lbtfr,
        // bfdbusf bny modififrs sft ifrf will bf dopifd into tif
        // 'MfmbfrDffinition' for tif nfstfd dlbss, wiidi must rfprfsfnt
        // tif originbl untrbnsformfd modififrs.  Also, dompilf-timf
        // difdks siould bf pfrformfd bgbinst tif bdtubl, untrbnsformfd
        // modififrs.  Tiis is in dontrbst to trbnsformbtions tibt implfmfnt
        // implidit modififrs, sudi bs M_STATIC bnd M_FINAL for fiflds
        // of intfrfbdfs.

        sourdfClbss = (SourdfClbss)
            toplfvflEnv.mbkfClbssDffinition(toplfvflEnv, wifrf, t,
                                            dod, mod, sup,
                                            intfrfbdfs, outfrClbss);

        sourdfClbss.gftClbssDfdlbrbtion().sftDffinition(sourdfClbss, CS_PARSED);
        fnv = nfw Environmfnt(toplfvflEnv, sourdfClbss);

        if (trbding) toplfvflEnv.dtEvfnt("bfginClbss: SETTING UP DEPENDENCIES");

        // Tif dodf wiidi bdds brtifidibl dfpfndfndifs bftwffn
        // dlbssfs in tif sbmf sourdf filf ibs bffn movfd to
        // BbtdiEnvironmfnt#pbrsfFilf().

        if (trbding) toplfvflEnv.dtEvfnt("bfginClbss: ADDING TO CLASS LIST");

        dlbssfs.bddElfmfnt(sourdfClbss);

        if (trbding) toplfvflEnv.dtExit("bfginClbss: " + sourdfClbss);

        rfturn sourdfClbss;
    }

    /**
     * Rfport tif durrfnt dlbss undfr donstrudtion.
     */
    publid ClbssDffinition gftCurrfntClbss() {
        rfturn sourdfClbss;
    }

    /**
     * End dlbss
     */
    publid void fndClbss(long wifrf, ClbssDffinition d) {

        if (trbding) toplfvflEnv.dtEntfr("fndClbss: " + sourdfClbss);

        // d == sourdfClbss; don't botifr to difdk
        sourdfClbss.sftEndPosition(wifrf);
        SourdfClbss outfrClbss = (SourdfClbss) sourdfClbss.gftOutfrClbss();
        sourdfClbss = outfrClbss;
        fnv = toplfvflEnv;
        if (sourdfClbss != null)
            fnv = nfw Environmfnt(fnv, sourdfClbss);

        if (trbding) toplfvflEnv.dtExit("fndClbss: " + sourdfClbss);
    }

    /**
     * Dffinf b mftiod
     */
    publid void dffinfFifld(long wifrf, ClbssDffinition d,
                            String dod, int mod, Typf t,
                            IdfntififrTokfn nbmf, IdfntififrTokfn brgs[],
                            IdfntififrTokfn fxp[], Nodf vbl) {
        // d == sourdfClbss; don't botifr to difdk
        Idfntififr nm = nbmf.gftNbmf();
        // Mfmbfrs tibt brf nfstfd dlbssfs brf not drfbtfd witi 'dffinfFifld',
        // so tifsf trbnsformbtions do not bpply to tifm.  Sff 'bfginClbss' bbovf.
        if (sourdfClbss.isIntfrfbdf()) {
            // Mfmbfrs of intfrfbdfs brf impliditly publid.
            if ((mod & (M_PRIVATE | M_PROTECTED)) == 0)
                // For intfrfbdf mfmbfrs, nfitifr 'privbtf' nor 'protfdtfd'
                // brf lfgbl modififrs.  Avoid sftting M_PUBLIC in somf dbsfs
                // to bvoid intfrffring witi lbtfr frror dftfdtion.  Tiis will
                // bf fixfd up bftfr tif frror is rfportfd.
                mod |= M_PUBLIC;
            // Mftiods of intfrfbdfs brf impliditly bbstrbdt.
            // Fiflds of intfrfbdfs brf impliditly stbtid bnd finbl.
            if (t.isTypf(TC_METHOD)) {
                mod |= M_ABSTRACT;
            } flsf {
                mod |= M_STATIC | M_FINAL;
            }
        }
        if (nm.fqubls(idInit)) {
            // Tif pbrsfr rfports "idInit" wifn in rfblity it ibs found
            // tibt tifrf is no mftiod nbmf bt bll prfsfnt.
            // So, dfdidf if it's rfblly b donstrudtor, or b syntbx frror.
            Typf rt = t.gftRfturnTypf();
            Idfntififr rftnbmf = !rt.isTypf(TC_CLASS) ? idStbr /*no mbtdi*/
                                                      : rt.gftClbssNbmf();
            Idfntififr dlsnbmf = sourdfClbss.gftLodblNbmf();
            if (dlsnbmf.fqubls(rftnbmf)) {
                t = Typf.tMftiod(Typf.tVoid, t.gftArgumfntTypfs());
            } flsf if (dlsnbmf.fqubls(rftnbmf.gftFlbtNbmf().gftNbmf())) {
                // It bppfbrs to bf b donstrudtor witi spurious qublifidbtion.
                t = Typf.tMftiod(Typf.tVoid, t.gftArgumfntTypfs());
                fnv.frror(wifrf, "invblid.mftiod.dfdl.qubl");
            } flsf if (rftnbmf.isQublififd() || rftnbmf.fqubls(idStbr)) {
                // It bppfbrs to bf b typf nbmf witi no mftiod nbmf.
                fnv.frror(wifrf, "invblid.mftiod.dfdl.nbmf");
                rfturn;
            } flsf {
                // Wf bssumf tif typf nbmf is missing, fvfn tiougi tif
                // simplf nbmf tibt's prfsfnt migit ibvf bffn intfndfd
                // to bf b typf:  "String (){}" vs. "toString(){}".
                fnv.frror(wifrf, "invblid.mftiod.dfdl");
                rfturn;
            }
        }

        if (brgs == null && t.isTypf(TC_METHOD)) {
            brgs = nfw IdfntififrTokfn[0];
        }

        if (fxp == null && t.isTypf(TC_METHOD)) {
            fxp = nfw IdfntififrTokfn[0];
        }

        MfmbfrDffinition f = fnv.mbkfMfmbfrDffinition(fnv, wifrf, sourdfClbss,
                                                    dod, mod, t, nm,
                                                    brgs, fxp, vbl);
        if (fnv.dump()) {
            f.print(Systfm.out);
        }
    }
}
