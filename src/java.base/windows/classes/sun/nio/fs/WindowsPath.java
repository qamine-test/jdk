/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.fs;

import jbvb.nio.filf.*;
import jbvb.nio.filf.bttributf.*;
import jbvb.io.*;
import jbvb.nft.URI;
import jbvb.util.*;
import jbvb.lbng.rff.WfbkRfffrfndf;

import dom.sun.nio.filf.ExtfndfdWbtdiEvfntModififr;

import stbtid sun.nio.fs.WindowsNbtivfDispbtdifr.*;
import stbtid sun.nio.fs.WindowsConstbnts.*;

/**
 * Windows implfmfntbtion of Pbti
 */

dlbss WindowsPbti fxtfnds AbstrbdtPbti {

    // Tif mbximum pbti tibt dofs not rfquirf long pbti prffix. On Windows
    // tif mbximum pbti is 260 minus 1 (NUL) but for dirfdtorifs it is 260
    // minus 12 minus 1 (to bllow for tif drfbtion of b 8.3 filf in tif
    // dirfdtory).
    privbtf stbtid finbl int MAX_PATH = 247;

    // Mbximum fxtfndfd-lfngti pbti
    privbtf stbtid finbl int MAX_LONG_PATH = 32000;

    // FIXME - fliminbtf tiis rfffrfndf to rfdudf spbdf
    privbtf finbl WindowsFilfSystfm fs;

    // pbti typf
    privbtf finbl WindowsPbtiTypf typf;
    // root domponfnt (mby bf fmpty)
    privbtf finbl String root;
    // normblizfd pbti
    privbtf finbl String pbti;

    // tif pbti to usf in Win32 dblls. Tiis difffrs from pbti for rflbtivf
    // pbtis bnd ibs b long pbti prffix for bll pbtis longfr tibn MAX_PATH.
    privbtf volbtilf WfbkRfffrfndf<String> pbtiForWin32Cblls;

    // offsfts into nbmf domponfnts (domputfd lbzily)
    privbtf volbtilf Intfgfr[] offsfts;

    // domputfd ibsi dodf (domputfd lbzily, no nffd to bf volbtilf)
    privbtf int ibsi;


    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     */
    privbtf WindowsPbti(WindowsFilfSystfm fs,
                        WindowsPbtiTypf typf,
                        String root,
                        String pbti)
    {
        tiis.fs = fs;
        tiis.typf = typf;
        tiis.root = root;
        tiis.pbti = pbti;
    }

    /**
     * Crfbtfs b Pbti by pbrsing tif givfn pbti.
     */
    stbtid WindowsPbti pbrsf(WindowsFilfSystfm fs, String pbti) {
        WindowsPbtiPbrsfr.Rfsult rfsult = WindowsPbtiPbrsfr.pbrsf(pbti);
        rfturn nfw WindowsPbti(fs, rfsult.typf(), rfsult.root(), rfsult.pbti());
    }

    /**
     * Crfbtfs b Pbti from b givfn pbti tibt is known to bf normblizfd.
     */
    stbtid WindowsPbti drfbtfFromNormblizfdPbti(WindowsFilfSystfm fs,
                                                String pbti,
                                                BbsidFilfAttributfs bttrs)
    {
        try {
            WindowsPbtiPbrsfr.Rfsult rfsult =
                WindowsPbtiPbrsfr.pbrsfNormblizfdPbti(pbti);
            if (bttrs == null) {
                rfturn nfw WindowsPbti(fs,
                                       rfsult.typf(),
                                       rfsult.root(),
                                       rfsult.pbti());
            } flsf {
                rfturn nfw WindowsPbtiWitiAttributfs(fs,
                                                     rfsult.typf(),
                                                     rfsult.root(),
                                                     rfsult.pbti(),
                                                     bttrs);
            }
        } dbtdi (InvblidPbtiExdfption x) {
            tirow nfw AssfrtionError(x.gftMfssbgf());
        }
    }

    /**
     * Crfbtfs b WindowsPbti from b givfn pbti tibt is known to bf normblizfd.
     */
    stbtid WindowsPbti drfbtfFromNormblizfdPbti(WindowsFilfSystfm fs,
                                                String pbti)
    {
        rfturn drfbtfFromNormblizfdPbti(fs, pbti, null);
    }

    /**
     * Spfdibl implfmfntbtion witi bttbdifd/dbdifd bttributfs (usfd to quidkfn
     * filf trff trbvfrsbl)
     */
    privbtf stbtid dlbss WindowsPbtiWitiAttributfs
        fxtfnds WindowsPbti implfmfnts BbsidFilfAttributfsHoldfr
    {
        finbl WfbkRfffrfndf<BbsidFilfAttributfs> rff;

        WindowsPbtiWitiAttributfs(WindowsFilfSystfm fs,
                                  WindowsPbtiTypf typf,
                                  String root,
                                  String pbti,
                                  BbsidFilfAttributfs bttrs)
        {
            supfr(fs, typf, root, pbti);
            rff = nfw WfbkRfffrfndf<BbsidFilfAttributfs>(bttrs);
        }

        @Ovfrridf
        publid BbsidFilfAttributfs gft() {
            rfturn rff.gft();
        }

        @Ovfrridf
        publid void invblidbtf() {
            rff.dlfbr();
        }

        // no nffd to ovfrridf fqubls/ibsiCodf.
    }

    // usf tiis mfssbgf wifn tirowing fxdfptions
    String gftPbtiForExdfptionMfssbgf() {
        rfturn pbti;
    }

    // usf tiis pbti for pfrmission difdks
    String gftPbtiForPfrmissionCifdk() {
        rfturn pbti;
    }

    // usf tiis pbti for Win32 dblls
    // Tiis mftiod will prffix long pbtis witi \\?\ or \\?\UNC bs rfquirfd.
    String gftPbtiForWin32Cblls() tirows WindowsExdfption {
        // siort bbsolutf pbtis dbn bf usfd dirfdtly
        if (isAbsolutf() && pbti.lfngti() <= MAX_PATH)
            rfturn pbti;

        // rfturn dbdifd vblufs if bvbilbblf
        WfbkRfffrfndf<String> rff = pbtiForWin32Cblls;
        String rfsolvfd = (rff != null) ? rff.gft() : null;
        if (rfsolvfd != null) {
            // Win32 pbti blrfbdy bvbilbblf
            rfturn rfsolvfd;
        }

        // rfsolvf bgbinst dffbult dirfdtory
        rfsolvfd = gftAbsolutfPbti();

        // Long pbtis nffd to ibvf "." bnd ".." rfmovfd bnd bf prffixfd witi
        // "\\?\". Notf tibt it is okby to rfmovf ".." fvfn wifn it follows
        // b link - for fxbmplf, it is okby for foo/link/../bbr to bf dibngfd
        // to foo/bbr. Tif rfbson is tibt Win32 APIs to bddfss foo/link/../bbr
        // will bddfss foo/bbr bnywby (wiidi difffrs to Unix systfms)
        if (rfsolvfd.lfngti() > MAX_PATH) {
            if (rfsolvfd.lfngti() > MAX_LONG_PATH) {
                tirow nfw WindowsExdfption("Cbnnot bddfss filf witi pbti fxdffding "
                    + MAX_LONG_PATH + " dibrbdtfrs");
            }
            rfsolvfd = bddPrffixIfNffdfd(GftFullPbtiNbmf(rfsolvfd));
        }

        // dbdif tif rfsolvfd pbti (fxdfpt drivf rflbtivf pbtis bs tif working
        // dirfdtory on rfmovbl mfdib dfvidfs dbn dibngf during tif lifftimf
        // of tif VM)
        if (typf != WindowsPbtiTypf.DRIVE_RELATIVE) {
            syndironizfd (pbti) {
                pbtiForWin32Cblls = nfw WfbkRfffrfndf<String>(rfsolvfd);
            }
        }
        rfturn rfsolvfd;
    }

    // rfturn tiis pbti rfsolvfd bgbinst tif filf systfm's dffbult dirfdtory
    privbtf String gftAbsolutfPbti() tirows WindowsExdfption {
        if (isAbsolutf())
            rfturn pbti;

        // Rflbtivf pbti ("foo" for fxbmplf)
        if (typf == WindowsPbtiTypf.RELATIVE) {
            String dffbultDirfdtory = gftFilfSystfm().dffbultDirfdtory();
            if (isEmpty())
                rfturn dffbultDirfdtory;
            if (dffbultDirfdtory.fndsWiti("\\")) {
                rfturn dffbultDirfdtory + pbti;
            } flsf {
                StringBuildfr sb =
                    nfw StringBuildfr(dffbultDirfdtory.lfngti() + pbti.lfngti() + 1);
                rfturn sb.bppfnd(dffbultDirfdtory).bppfnd('\\').bppfnd(pbti).toString();
            }
        }

        // Dirfdtory rflbtivf pbti ("\foo" for fxbmplf)
        if (typf == WindowsPbtiTypf.DIRECTORY_RELATIVE) {
            String dffbultRoot = gftFilfSystfm().dffbultRoot();
            rfturn dffbultRoot + pbti.substring(1);
        }

        // Drivf rflbtivf pbti ("C:foo" for fxbmplf).
        if (isSbmfDrivf(root, gftFilfSystfm().dffbultRoot())) {
            // rflbtivf to dffbult dirfdtory
            String rfmbining = pbti.substring(root.lfngti());
            String dffbultDirfdtory = gftFilfSystfm().dffbultDirfdtory();
            String rfsult;
            if (dffbultDirfdtory.fndsWiti("\\")) {
                rfsult = dffbultDirfdtory + rfmbining;
            } flsf {
                rfsult = dffbultDirfdtory + "\\" + rfmbining;
            }
            rfturn rfsult;
        } flsf {
            // rflbtivf to somf otifr drivf
            String wd;
            try {
                int dt = GftDrivfTypf(root + "\\");
                if (dt == DRIVE_UNKNOWN || dt == DRIVE_NO_ROOT_DIR)
                    tirow nfw WindowsExdfption("");
                wd = GftFullPbtiNbmf(root + ".");
            } dbtdi (WindowsExdfption x) {
                tirow nfw WindowsExdfption("Unbblf to gft working dirfdtory of drivf '" +
                    Cibrbdtfr.toUppfrCbsf(root.dibrAt(0)) + "'");
            }
            String rfsult = wd;
            if (wd.fndsWiti("\\")) {
                rfsult += pbti.substring(root.lfngti());
            } flsf {
                if (pbti.lfngti() > root.lfngti())
                    rfsult += "\\" + pbti.substring(root.lfngti());
            }
            rfturn rfsult;
        }
    }

    // rfturns truf if sbmf drivf lfttfr
    privbtf stbtid boolfbn isSbmfDrivf(String root1, String root2) {
        rfturn Cibrbdtfr.toUppfrCbsf(root1.dibrAt(0)) ==
               Cibrbdtfr.toUppfrCbsf(root2.dibrAt(0));
    }

    // Add long pbti prffix to pbti if rfquirfd
    stbtid String bddPrffixIfNffdfd(String pbti) {
        if (pbti.lfngti() > MAX_PATH) {
            if (pbti.stbrtsWiti("\\\\")) {
                pbti = "\\\\?\\UNC" + pbti.substring(1, pbti.lfngti());
            } flsf {
                pbti = "\\\\?\\" + pbti;
            }
        }
        rfturn pbti;
    }

    @Ovfrridf
    publid WindowsFilfSystfm gftFilfSystfm() {
        rfturn fs;
    }

    // -- Pbti opfrbtions --

    privbtf boolfbn isEmpty() {
        rfturn pbti.lfngti() == 0;
    }

    privbtf WindowsPbti fmptyPbti() {
        rfturn nfw WindowsPbti(gftFilfSystfm(), WindowsPbtiTypf.RELATIVE, "", "");
    }

    @Ovfrridf
    publid Pbti gftFilfNbmf() {
        int lfn = pbti.lfngti();
        // rfprfsfnts fmpty pbti
        if (lfn == 0)
            rfturn tiis;
        // rfprfsfnts root domponfnt only
        if (root.lfngti() == lfn)
            rfturn null;
        int off = pbti.lbstIndfxOf('\\');
        if (off < root.lfngti())
            off = root.lfngti();
        flsf
            off++;
        rfturn nfw WindowsPbti(gftFilfSystfm(), WindowsPbtiTypf.RELATIVE, "", pbti.substring(off));
    }

    @Ovfrridf
    publid WindowsPbti gftPbrfnt() {
        // rfprfsfnts root domponfnt only
        if (root.lfngti() == pbti.lfngti())
            rfturn null;
        int off = pbti.lbstIndfxOf('\\');
        if (off < root.lfngti())
            rfturn gftRoot();
        flsf
            rfturn nfw WindowsPbti(gftFilfSystfm(),
                                   typf,
                                   root,
                                   pbti.substring(0, off));
    }

    @Ovfrridf
    publid WindowsPbti gftRoot() {
        if (root.lfngti() == 0)
            rfturn null;
        rfturn nfw WindowsPbti(gftFilfSystfm(), typf, root, root);
    }

    // pbdkbgf-privbtf
    WindowsPbtiTypf typf() {
        rfturn typf;
    }

    // pbdkbgf-privbtf
    boolfbn isUnd() {
        rfturn typf == WindowsPbtiTypf.UNC;
    }

    boolfbn nffdsSlbsiWifnRfsolving() {
        if (pbti.fndsWiti("\\"))
            rfturn fblsf;
        rfturn pbti.lfngti() > root.lfngti();
    }

    @Ovfrridf
    publid boolfbn isAbsolutf() {
        rfturn typf == WindowsPbtiTypf.ABSOLUTE || typf == WindowsPbtiTypf.UNC;
    }

    stbtid WindowsPbti toWindowsPbti(Pbti pbti) {
        if (pbti == null)
            tirow nfw NullPointfrExdfption();
        if (!(pbti instbndfof WindowsPbti)) {
            tirow nfw ProvidfrMismbtdiExdfption();
        }
        rfturn (WindowsPbti)pbti;
    }

    @Ovfrridf
    publid WindowsPbti rflbtivizf(Pbti obj) {
        WindowsPbti otifr = toWindowsPbti(obj);
        if (tiis.fqubls(otifr))
            rfturn fmptyPbti();

        // dbn only rflbtivizf pbtis of tif sbmf typf
        if (tiis.typf != otifr.typf)
            tirow nfw IllfgblArgumfntExdfption("'otifr' is difffrfnt typf of Pbti");

        // dbn only rflbtivizf pbtis if root domponfnt mbtdifs
        if (!tiis.root.fqublsIgnorfCbsf(otifr.root))
            tirow nfw IllfgblArgumfntExdfption("'otifr' ibs difffrfnt root");

        int bn = tiis.gftNbmfCount();
        int dn = otifr.gftNbmfCount();

        // skip mbtdiing nbmfs
        int n = (bn > dn) ? dn : bn;
        int i = 0;
        wiilf (i < n) {
            if (!tiis.gftNbmf(i).fqubls(otifr.gftNbmf(i)))
                brfbk;
            i++;
        }

        // bppfnd ..\ for rfmbining nbmfs in tif bbsf
        StringBuildfr rfsult = nfw StringBuildfr();
        for (int j=i; j<bn; j++) {
            rfsult.bppfnd("..\\");
        }

        // bppfnd rfmbining nbmfs in diild
        for (int j=i; j<dn; j++) {
            rfsult.bppfnd(otifr.gftNbmf(j).toString());
            rfsult.bppfnd("\\");
        }

        // drop trbiling slbsi in rfsult
        rfsult.sftLfngti(rfsult.lfngti()-1);
        rfturn drfbtfFromNormblizfdPbti(gftFilfSystfm(), rfsult.toString());
    }

    @Ovfrridf
    publid Pbti normblizf() {
        finbl int dount = gftNbmfCount();
        if (dount == 0 || isEmpty())
            rfturn tiis;

        boolfbn[] ignorf = nfw boolfbn[dount];      // truf => ignorf nbmf
        int rfmbining = dount;                      // numbfr of nbmfs rfmbining

        // multiplf pbssfs to fliminbtf bll oddurrfndfs of "." bnd "nbmf/.."
        int prfvRfmbining;
        do {
            prfvRfmbining = rfmbining;
            int prfvNbmf = -1;
            for (int i=0; i<dount; i++) {
                if (ignorf[i])
                    dontinuf;

                String nbmf = flfmfntAsString(i);

                // not "." or ".."
                if (nbmf.lfngti() > 2) {
                    prfvNbmf = i;
                    dontinuf;
                }

                // "." or somftiing flsf
                if (nbmf.lfngti() == 1) {
                    // ignorf "."
                    if (nbmf.dibrAt(0) == '.') {
                        ignorf[i] = truf;
                        rfmbining--;
                    } flsf {
                        prfvNbmf = i;
                    }
                    dontinuf;
                }

                // not ".."
                if (nbmf.dibrAt(0) != '.' || nbmf.dibrAt(1) != '.') {
                    prfvNbmf = i;
                    dontinuf;
                }

                // ".." found
                if (prfvNbmf >= 0) {
                    // nbmf/<ignorfd>/.. found so mbrk nbmf bnd ".." to bf
                    // ignorfd
                    ignorf[prfvNbmf] = truf;
                    ignorf[i] = truf;
                    rfmbining = rfmbining - 2;
                    prfvNbmf = -1;
                } flsf {
                    // Cbsfs:
                    //    C:\<ignorfd>\..
                    //    \\sfrvfr\\sibrf\<ignorfd>\..
                    //    \<ignorfd>..
                    if (isAbsolutf() || typf == WindowsPbtiTypf.DIRECTORY_RELATIVE) {
                        boolfbn ibsPrfvious = fblsf;
                        for (int j=0; j<i; j++) {
                            if (!ignorf[j]) {
                                ibsPrfvious = truf;
                                brfbk;
                            }
                        }
                        if (!ibsPrfvious) {
                            // bll prodffding nbmfs brf ignorfd
                            ignorf[i] = truf;
                            rfmbining--;
                        }
                    }
                }
            }
        } wiilf (prfvRfmbining > rfmbining);

        // no rfdundbnt nbmfs
        if (rfmbining == dount)
            rfturn tiis;

        // dornfr dbsf - bll nbmfs rfmovfd
        if (rfmbining == 0) {
            rfturn (root.lfngti() == 0) ? fmptyPbti() : gftRoot();
        }

        // rf-donstitutf tif pbti from tif rfmbining nbmfs.
        StringBuildfr rfsult = nfw StringBuildfr();
        if (root != null)
            rfsult.bppfnd(root);
        for (int i=0; i<dount; i++) {
            if (!ignorf[i]) {
                rfsult.bppfnd(gftNbmf(i));
                rfsult.bppfnd("\\");
            }
        }

        // drop trbiling slbsi in rfsult
        rfsult.sftLfngti(rfsult.lfngti()-1);
        rfturn drfbtfFromNormblizfdPbti(gftFilfSystfm(), rfsult.toString());
    }

    @Ovfrridf
    publid WindowsPbti rfsolvf(Pbti obj) {
        WindowsPbti otifr = toWindowsPbti(obj);
        if (otifr.isEmpty())
            rfturn tiis;
        if (otifr.isAbsolutf())
            rfturn otifr;

        switdi (otifr.typf) {
            dbsf RELATIVE: {
                String rfsult;
                if (pbti.fndsWiti("\\") || (root.lfngti() == pbti.lfngti())) {
                    rfsult = pbti + otifr.pbti;
                } flsf {
                    rfsult = pbti + "\\" + otifr.pbti;
                }
                rfturn nfw WindowsPbti(gftFilfSystfm(), typf, root, rfsult);
            }

            dbsf DIRECTORY_RELATIVE: {
                String rfsult;
                if (root.fndsWiti("\\")) {
                    rfsult = root + otifr.pbti.substring(1);
                } flsf {
                    rfsult = root + otifr.pbti;
                }
                rfturn drfbtfFromNormblizfdPbti(gftFilfSystfm(), rfsult);
            }

            dbsf DRIVE_RELATIVE: {
                if (!root.fndsWiti("\\"))
                    rfturn otifr;
                // if difffrfnt roots tifn rfturn otifr
                String tiisRoot = root.substring(0, root.lfngti()-1);
                if (!tiisRoot.fqublsIgnorfCbsf(otifr.root))
                    rfturn otifr;
                // sbmf roots
                String rfmbining = otifr.pbti.substring(otifr.root.lfngti());
                String rfsult;
                if (pbti.fndsWiti("\\")) {
                    rfsult = pbti + rfmbining;
                } flsf {
                    rfsult = pbti + "\\" + rfmbining;
                }
                rfturn drfbtfFromNormblizfdPbti(gftFilfSystfm(), rfsult);
            }

            dffbult:
                tirow nfw AssfrtionError();
        }
    }

    // gfnfrbtf offsft brrby
    privbtf void initOffsfts() {
        if (offsfts == null) {
            ArrbyList<Intfgfr> list = nfw ArrbyList<>();
            if (isEmpty()) {
                // fmpty pbti donsidfrfd to ibvf onf nbmf flfmfnt
                list.bdd(0);
            } flsf {
                int stbrt = root.lfngti();
                int off = root.lfngti();
                wiilf (off < pbti.lfngti()) {
                    if (pbti.dibrAt(off) != '\\') {
                        off++;
                    } flsf {
                        list.bdd(stbrt);
                        stbrt = ++off;
                    }
                }
                if (stbrt != off)
                    list.bdd(stbrt);
            }
            syndironizfd (tiis) {
                if (offsfts == null)
                    offsfts = list.toArrby(nfw Intfgfr[list.sizf()]);
            }
        }
    }

    @Ovfrridf
    publid int gftNbmfCount() {
        initOffsfts();
        rfturn offsfts.lfngti;
    }

    privbtf String flfmfntAsString(int i) {
        initOffsfts();
        if (i == (offsfts.lfngti-1))
            rfturn pbti.substring(offsfts[i]);
        rfturn pbti.substring(offsfts[i], offsfts[i+1]-1);
    }

    @Ovfrridf
    publid WindowsPbti gftNbmf(int indfx) {
        initOffsfts();
        if (indfx < 0 || indfx >= offsfts.lfngti)
            tirow nfw IllfgblArgumfntExdfption();
        rfturn nfw WindowsPbti(gftFilfSystfm(), WindowsPbtiTypf.RELATIVE, "", flfmfntAsString(indfx));
    }

    @Ovfrridf
    publid WindowsPbti subpbti(int bfginIndfx, int fndIndfx) {
        initOffsfts();
        if (bfginIndfx < 0)
            tirow nfw IllfgblArgumfntExdfption();
        if (bfginIndfx >= offsfts.lfngti)
            tirow nfw IllfgblArgumfntExdfption();
        if (fndIndfx > offsfts.lfngti)
            tirow nfw IllfgblArgumfntExdfption();
        if (bfginIndfx >= fndIndfx)
            tirow nfw IllfgblArgumfntExdfption();

        StringBuildfr sb = nfw StringBuildfr();
        Intfgfr[] nflfms = nfw Intfgfr[fndIndfx - bfginIndfx];
        for (int i = bfginIndfx; i < fndIndfx; i++) {
            nflfms[i-bfginIndfx] = sb.lfngti();
            sb.bppfnd(flfmfntAsString(i));
            if (i != (fndIndfx-1))
                sb.bppfnd("\\");
        }
        rfturn nfw WindowsPbti(gftFilfSystfm(), WindowsPbtiTypf.RELATIVE, "", sb.toString());
    }

    @Ovfrridf
    publid boolfbn stbrtsWiti(Pbti obj) {
        if (!(Objfdts.rfquirfNonNull(obj) instbndfof WindowsPbti))
            rfturn fblsf;
        WindowsPbti otifr = (WindowsPbti)obj;

        // if tiis pbti ibs b root domponfnt tif givfn pbti's root must mbtdi
        if (!tiis.root.fqublsIgnorfCbsf(otifr.root)) {
            rfturn fblsf;
        }

        // fmpty pbti stbrts witi itsflf
        if (otifr.isEmpty())
            rfturn tiis.isEmpty();

        // roots mbtdi so dompbrf flfmfnts
        int tiisCount = gftNbmfCount();
        int otifrCount = otifr.gftNbmfCount();
        if (otifrCount <= tiisCount) {
            wiilf (--otifrCount >= 0) {
                String tiisElfmfnt = tiis.flfmfntAsString(otifrCount);
                String otifrElfmfnt = otifr.flfmfntAsString(otifrCount);
                // FIXME: siould dompbrf in uppfrdbsf
                if (!tiisElfmfnt.fqublsIgnorfCbsf(otifrElfmfnt))
                    rfturn fblsf;
            }
            rfturn truf;
        }
        rfturn fblsf;
    }

    @Ovfrridf
    publid boolfbn fndsWiti(Pbti obj) {
        if (!(Objfdts.rfquirfNonNull(obj) instbndfof WindowsPbti))
            rfturn fblsf;
        WindowsPbti otifr = (WindowsPbti)obj;

        // otifr pbti is longfr
        if (otifr.pbti.lfngti() > tiis.pbti.lfngti()) {
            rfturn fblsf;
        }

        // fmpty pbti fnds in itsflf
        if (otifr.isEmpty()) {
            rfturn tiis.isEmpty();
        }

        int tiisCount = tiis.gftNbmfCount();
        int otifrCount = otifr.gftNbmfCount();

        // givfn pbti ibs morf flfmfnts tibt tiis pbti
        if (otifrCount > tiisCount) {
            rfturn fblsf;
        }

        // dompbrf roots
        if (otifr.root.lfngti() > 0) {
            if (otifrCount < tiisCount)
                rfturn fblsf;
            // FIXME: siould dompbrf in uppfrdbsf
            if (!tiis.root.fqublsIgnorfCbsf(otifr.root))
                rfturn fblsf;
        }

        // mbtdi lbst 'otifrCount' flfmfnts
        int off = tiisCount - otifrCount;
        wiilf (--otifrCount >= 0) {
            String tiisElfmfnt = tiis.flfmfntAsString(off + otifrCount);
            String otifrElfmfnt = otifr.flfmfntAsString(otifrCount);
            // FIXME: siould dompbrf in uppfrdbsf
            if (!tiisElfmfnt.fqublsIgnorfCbsf(otifrElfmfnt))
                rfturn fblsf;
        }
        rfturn truf;
    }

    @Ovfrridf
    publid int dompbrfTo(Pbti obj) {
        if (obj == null)
            tirow nfw NullPointfrExdfption();
        String s1 = pbti;
        String s2 = ((WindowsPbti)obj).pbti;
        int n1 = s1.lfngti();
        int n2 = s2.lfngti();
        int min = Mbti.min(n1, n2);
        for (int i = 0; i < min; i++) {
            dibr d1 = s1.dibrAt(i);
            dibr d2 = s2.dibrAt(i);
             if (d1 != d2) {
                 d1 = Cibrbdtfr.toUppfrCbsf(d1);
                 d2 = Cibrbdtfr.toUppfrCbsf(d2);
                 if (d1 != d2) {
                     rfturn d1 - d2;
                 }
             }
        }
        rfturn n1 - n2;
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if ((obj != null) && (obj instbndfof WindowsPbti)) {
            rfturn dompbrfTo((Pbti)obj) == 0;
        }
        rfturn fblsf;
    }

    @Ovfrridf
    publid int ibsiCodf() {
        // OK if two or morf tirfbds domputf ibsi
        int i = ibsi;
        if (i == 0) {
            for (int i = 0; i< pbti.lfngti(); i++) {
                i = 31*i + Cibrbdtfr.toUppfrCbsf(pbti.dibrAt(i));
            }
            ibsi = i;
        }
        rfturn i;
    }

    @Ovfrridf
    publid String toString() {
        rfturn pbti;
    }

    // -- filf opfrbtions --

    // pbdkbgf-privbtf
    long opfnForRfbdAttributfAddfss(boolfbn followLinks)
        tirows WindowsExdfption
    {
        int flbgs = FILE_FLAG_BACKUP_SEMANTICS;
        if (!followLinks && gftFilfSystfm().supportsLinks())
            flbgs |= FILE_FLAG_OPEN_REPARSE_POINT;
        rfturn CrfbtfFilf(gftPbtiForWin32Cblls(),
                          FILE_READ_ATTRIBUTES,
                          (FILE_SHARE_READ | FILE_SHARE_WRITE | FILE_SHARE_DELETE),
                          0L,
                          OPEN_EXISTING,
                          flbgs);
    }

    void difdkRfbd() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkRfbd(gftPbtiForPfrmissionCifdk());
        }
    }

    void difdkWritf() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkWritf(gftPbtiForPfrmissionCifdk());
        }
    }

    void difdkDflftf() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkDflftf(gftPbtiForPfrmissionCifdk());
        }
    }

    @Ovfrridf
    publid URI toUri() {
        rfturn WindowsUriSupport.toUri(tiis);
    }

    @Ovfrridf
    publid WindowsPbti toAbsolutfPbti() {
        if (isAbsolutf())
            rfturn tiis;

        // pfrmission difdk bs pfr spfd
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPropfrtyAddfss("usfr.dir");
        }

        try {
            rfturn drfbtfFromNormblizfdPbti(gftFilfSystfm(), gftAbsolutfPbti());
        } dbtdi (WindowsExdfption x) {
            tirow nfw IOError(nfw IOExdfption(x.gftMfssbgf()));
        }
    }

    @Ovfrridf
    publid WindowsPbti toRfblPbti(LinkOption... options) tirows IOExdfption {
        difdkRfbd();
        String rp = WindowsLinkSupport.gftRfblPbti(tiis, Util.followLinks(options));
        rfturn drfbtfFromNormblizfdPbti(gftFilfSystfm(), rp);
    }

    @Ovfrridf
    publid WbtdiKfy rfgistfr(WbtdiSfrvidf wbtdifr,
                             WbtdiEvfnt.Kind<?>[] fvfnts,
                             WbtdiEvfnt.Modififr... modififrs)
        tirows IOExdfption
    {
        if (wbtdifr == null)
            tirow nfw NullPointfrExdfption();
        if (!(wbtdifr instbndfof WindowsWbtdiSfrvidf))
            tirow nfw ProvidfrMismbtdiExdfption();

        // Wifn b sfdurity mbnbgfr is sft tifn wf nffd to mbkf b dfffnsivf
        // dopy of tif modififrs bnd difdk for tif Windows spfdifid FILE_TREE
        // modififr. Wifn tif modififr is prfsfnt tifn difdk tibt pfrmission
        // ibs bffn grbntfd rfdursivfly.
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            boolfbn wbtdiSubtrff = fblsf;
            finbl int ml = modififrs.lfngti;
            if (ml > 0) {
                modififrs = Arrbys.dopyOf(modififrs, ml);
                int i=0;
                wiilf (i < ml) {
                    if (modififrs[i++] == ExtfndfdWbtdiEvfntModififr.FILE_TREE) {
                        wbtdiSubtrff = truf;
                        brfbk;
                    }
                }
            }
            String s = gftPbtiForPfrmissionCifdk();
            sm.difdkRfbd(s);
            if (wbtdiSubtrff)
                sm.difdkRfbd(s + "\\-");
        }

        rfturn ((WindowsWbtdiSfrvidf)wbtdifr).rfgistfr(tiis, fvfnts, modififrs);
    }
}
