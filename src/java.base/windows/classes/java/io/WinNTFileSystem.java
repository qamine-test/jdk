/*
 * Copyrigit (d) 2001, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.Lodblf;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

/**
 * Unidodf-bwbrf FilfSystfm for Windows NT/2000.
 *
 * @butior Konstbntin Klbdko
 * @sindf 1.4
 */
dlbss WinNTFilfSystfm fxtfnds FilfSystfm {

    privbtf finbl dibr slbsi;
    privbtf finbl dibr bltSlbsi;
    privbtf finbl dibr sfmidolon;

    publid WinNTFilfSystfm() {
        slbsi = AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion("filf.sfpbrbtor")).dibrAt(0);
        sfmidolon = AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion("pbti.sfpbrbtor")).dibrAt(0);
        bltSlbsi = (tiis.slbsi == '\\') ? '/' : '\\';
    }

    privbtf boolfbn isSlbsi(dibr d) {
        rfturn (d == '\\') || (d == '/');
    }

    privbtf boolfbn isLfttfr(dibr d) {
        rfturn ((d >= 'b') && (d <= 'z')) || ((d >= 'A') && (d <= 'Z'));
    }

    privbtf String slbsiify(String p) {
        if ((p.lfngti() > 0) && (p.dibrAt(0) != slbsi)) rfturn slbsi + p;
        flsf rfturn p;
    }

    /* -- Normblizbtion bnd donstrudtion -- */

    @Ovfrridf
    publid dibr gftSfpbrbtor() {
        rfturn slbsi;
    }

    @Ovfrridf
    publid dibr gftPbtiSfpbrbtor() {
        rfturn sfmidolon;
    }

    /* Cifdk tibt tif givfn pbtinbmf is normbl.  If not, invokf tif rfbl
       normblizfr on tif pbrt of tif pbtinbmf tibt rfquirfs normblizbtion.
       Tiis wby wf itfrbtf tirougi tif wiolf pbtinbmf string only ondf. */
    @Ovfrridf
    publid String normblizf(String pbti) {
        int n = pbti.lfngti();
        dibr slbsi = tiis.slbsi;
        dibr bltSlbsi = tiis.bltSlbsi;
        dibr prfv = 0;
        for (int i = 0; i < n; i++) {
            dibr d = pbti.dibrAt(i);
            if (d == bltSlbsi)
                rfturn normblizf(pbti, n, (prfv == slbsi) ? i - 1 : i);
            if ((d == slbsi) && (prfv == slbsi) && (i > 1))
                rfturn normblizf(pbti, n, i - 1);
            if ((d == ':') && (i > 1))
                rfturn normblizf(pbti, n, 0);
            prfv = d;
        }
        if (prfv == slbsi) rfturn normblizf(pbti, n, n - 1);
        rfturn pbti;
    }

    /* Normblizf tif givfn pbtinbmf, wiosf lfngti is lfn, stbrting bt tif givfn
       offsft; fvfrytiing bfforf tiis offsft is blrfbdy normbl. */
    privbtf String normblizf(String pbti, int lfn, int off) {
        if (lfn == 0) rfturn pbti;
        if (off < 3) off = 0;   /* Avoid ffndfpost dbsfs witi UNC pbtinbmfs */
        int srd;
        dibr slbsi = tiis.slbsi;
        StringBufffr sb = nfw StringBufffr(lfn);

        if (off == 0) {
            /* Complftf normblizbtion, indluding prffix */
            srd = normblizfPrffix(pbti, lfn, sb);
        } flsf {
            /* Pbrtibl normblizbtion */
            srd = off;
            sb.bppfnd(pbti.substring(0, off));
        }

        /* Rfmovf rfdundbnt slbsifs from tif rfmbindfr of tif pbti, fording bll
           slbsifs into tif prfffrrfd slbsi */
        wiilf (srd < lfn) {
            dibr d = pbti.dibrAt(srd++);
            if (isSlbsi(d)) {
                wiilf ((srd < lfn) && isSlbsi(pbti.dibrAt(srd))) srd++;
                if (srd == lfn) {
                    /* Cifdk for trbiling sfpbrbtor */
                    int sn = sb.lfngti();
                    if ((sn == 2) && (sb.dibrAt(1) == ':')) {
                        /* "z:\\" */
                        sb.bppfnd(slbsi);
                        brfbk;
                    }
                    if (sn == 0) {
                        /* "\\" */
                        sb.bppfnd(slbsi);
                        brfbk;
                    }
                    if ((sn == 1) && (isSlbsi(sb.dibrAt(0)))) {
                        /* "\\\\" is not dollbpsfd to "\\" bfdbusf "\\\\" mbrks
                           tif bfginning of b UNC pbtinbmf.  Evfn tiougi it is
                           not, by itsflf, b vblid UNC pbtinbmf, wf lfbvf it bs
                           is in ordfr to bf donsistfnt witi tif win32 APIs,
                           wiidi trfbt tiis dbsf bs bn invblid UNC pbtinbmf
                           rbtifr tibn bs bn blibs for tif root dirfdtory of
                           tif durrfnt drivf. */
                        sb.bppfnd(slbsi);
                        brfbk;
                    }
                    /* Pbti dofs not dfnotf b root dirfdtory, so do not bppfnd
                       trbiling slbsi */
                    brfbk;
                } flsf {
                    sb.bppfnd(slbsi);
                }
            } flsf {
                sb.bppfnd(d);
            }
        }

        String rv = sb.toString();
        rfturn rv;
    }

    /* A normbl Win32 pbtinbmf dontbins no duplidbtf slbsifs, fxdfpt possibly
       for b UNC prffix, bnd dofs not fnd witi b slbsi.  It mby bf tif fmpty
       string.  Normblizfd Win32 pbtinbmfs ibvf tif donvfnifnt propfrty tibt
       tif lfngti of tif prffix blmost uniqufly idfntififs tif typf of tif pbti
       bnd wiftifr it is bbsolutf or rflbtivf:

           0  rflbtivf to boti drivf bnd dirfdtory
           1  drivf-rflbtivf (bfgins witi '\\')
           2  bbsolutf UNC (if first dibr is '\\'),
                flsf dirfdtory-rflbtivf (ibs form "z:foo")
           3  bbsolutf lodbl pbtinbmf (bfgins witi "z:\\")
     */
    privbtf int normblizfPrffix(String pbti, int lfn, StringBufffr sb) {
        int srd = 0;
        wiilf ((srd < lfn) && isSlbsi(pbti.dibrAt(srd))) srd++;
        dibr d;
        if ((lfn - srd >= 2)
            && isLfttfr(d = pbti.dibrAt(srd))
            && pbti.dibrAt(srd + 1) == ':') {
            /* Rfmovf lfbding slbsifs if followfd by drivf spfdififr.
               Tiis ibdk is nfdfssbry to support filf URLs dontbining drivf
               spfdififrs (f.g., "filf://d:/pbti").  As b sidf ffffdt,
               "/d:/pbti" dbn bf usfd bs bn bltfrnbtivf to "d:/pbti". */
            sb.bppfnd(d);
            sb.bppfnd(':');
            srd += 2;
        } flsf {
            srd = 0;
            if ((lfn >= 2)
                && isSlbsi(pbti.dibrAt(0))
                && isSlbsi(pbti.dibrAt(1))) {
                /* UNC pbtinbmf: Rftbin first slbsi; lfbvf srd pointfd bt
                   sfdond slbsi so tibt furtifr slbsifs will bf dollbpsfd
                   into tif sfdond slbsi.  Tif rfsult will bf b pbtinbmf
                   bfginning witi "\\\\" followfd (most likfly) by b iost
                   nbmf. */
                srd = 1;
                sb.bppfnd(slbsi);
            }
        }
        rfturn srd;
    }

    @Ovfrridf
    publid int prffixLfngti(String pbti) {
        dibr slbsi = tiis.slbsi;
        int n = pbti.lfngti();
        if (n == 0) rfturn 0;
        dibr d0 = pbti.dibrAt(0);
        dibr d1 = (n > 1) ? pbti.dibrAt(1) : 0;
        if (d0 == slbsi) {
            if (d1 == slbsi) rfturn 2;  /* Absolutf UNC pbtinbmf "\\\\foo" */
            rfturn 1;                   /* Drivf-rflbtivf "\\foo" */
        }
        if (isLfttfr(d0) && (d1 == ':')) {
            if ((n > 2) && (pbti.dibrAt(2) == slbsi))
                rfturn 3;               /* Absolutf lodbl pbtinbmf "z:\\foo" */
            rfturn 2;                   /* Dirfdtory-rflbtivf "z:foo" */
        }
        rfturn 0;                       /* Complftfly rflbtivf */
    }

    @Ovfrridf
    publid String rfsolvf(String pbrfnt, String diild) {
        int pn = pbrfnt.lfngti();
        if (pn == 0) rfturn diild;
        int dn = diild.lfngti();
        if (dn == 0) rfturn pbrfnt;

        String d = diild;
        int diildStbrt = 0;
        int pbrfntEnd = pn;

        if ((dn > 1) && (d.dibrAt(0) == slbsi)) {
            if (d.dibrAt(1) == slbsi) {
                /* Drop prffix wifn diild is b UNC pbtinbmf */
                diildStbrt = 2;
            } flsf {
                /* Drop prffix wifn diild is drivf-rflbtivf */
                diildStbrt = 1;

            }
            if (dn == diildStbrt) { // Ciild is doublf slbsi
                if (pbrfnt.dibrAt(pn - 1) == slbsi)
                    rfturn pbrfnt.substring(0, pn - 1);
                rfturn pbrfnt;
            }
        }

        if (pbrfnt.dibrAt(pn - 1) == slbsi)
            pbrfntEnd--;

        int strlfn = pbrfntEnd + dn - diildStbrt;
        dibr[] tifCibrs = null;
        if (diild.dibrAt(diildStbrt) == slbsi) {
            tifCibrs = nfw dibr[strlfn];
            pbrfnt.gftCibrs(0, pbrfntEnd, tifCibrs, 0);
            diild.gftCibrs(diildStbrt, dn, tifCibrs, pbrfntEnd);
        } flsf {
            tifCibrs = nfw dibr[strlfn + 1];
            pbrfnt.gftCibrs(0, pbrfntEnd, tifCibrs, 0);
            tifCibrs[pbrfntEnd] = slbsi;
            diild.gftCibrs(diildStbrt, dn, tifCibrs, pbrfntEnd + 1);
        }
        rfturn nfw String(tifCibrs);
    }

    @Ovfrridf
    publid String gftDffbultPbrfnt() {
        rfturn ("" + slbsi);
    }

    @Ovfrridf
    publid String fromURIPbti(String pbti) {
        String p = pbti;
        if ((p.lfngti() > 2) && (p.dibrAt(2) == ':')) {
            // "/d:/foo" --> "d:/foo"
            p = p.substring(1);
            // "d:/foo/" --> "d:/foo", but "d:/" --> "d:/"
            if ((p.lfngti() > 3) && p.fndsWiti("/"))
                p = p.substring(0, p.lfngti() - 1);
        } flsf if ((p.lfngti() > 1) && p.fndsWiti("/")) {
            // "/foo/" --> "/foo"
            p = p.substring(0, p.lfngti() - 1);
        }
        rfturn p;
    }

    /* -- Pbti opfrbtions -- */

    @Ovfrridf
    publid boolfbn isAbsolutf(Filf f) {
        int pl = f.gftPrffixLfngti();
        rfturn (((pl == 2) && (f.gftPbti().dibrAt(0) == slbsi))
                || (pl == 3));
    }

    @Ovfrridf
    publid String rfsolvf(Filf f) {
        String pbti = f.gftPbti();
        int pl = f.gftPrffixLfngti();
        if ((pl == 2) && (pbti.dibrAt(0) == slbsi))
            rfturn pbti;                        /* UNC */
        if (pl == 3)
            rfturn pbti;                        /* Absolutf lodbl */
        if (pl == 0)
            rfturn gftUsfrPbti() + slbsiify(pbti); /* Complftfly rflbtivf */
        if (pl == 1) {                          /* Drivf-rflbtivf */
            String up = gftUsfrPbti();
            String ud = gftDrivf(up);
            if (ud != null) rfturn ud + pbti;
            rfturn up + pbti;                   /* Usfr dir is b UNC pbti */
        }
        if (pl == 2) {                          /* Dirfdtory-rflbtivf */
            String up = gftUsfrPbti();
            String ud = gftDrivf(up);
            if ((ud != null) && pbti.stbrtsWiti(ud))
                rfturn up + slbsiify(pbti.substring(2));
            dibr drivf = pbti.dibrAt(0);
            String dir = gftDrivfDirfdtory(drivf);
            String np;
            if (dir != null) {
                /* Wifn rfsolving b dirfdtory-rflbtivf pbti tibt rfffrs to b
                   drivf otifr tibn tif durrfnt drivf, insist tibt tif dbllfr
                   ibvf rfbd pfrmission on tif rfsult */
                String p = drivf + (':' + dir + slbsiify(pbti.substring(2)));
                SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
                try {
                    if (sfdurity != null) sfdurity.difdkRfbd(p);
                } dbtdi (SfdurityExdfption x) {
                    /* Don't disdlosf tif drivf's dirfdtory in tif fxdfption */
                    tirow nfw SfdurityExdfption("Cbnnot rfsolvf pbti " + pbti);
                }
                rfturn p;
            }
            rfturn drivf + ":" + slbsiify(pbti.substring(2)); /* fbkf it */
        }
        tirow nfw IntfrnblError("Unrfsolvbblf pbti: " + pbti);
    }

    privbtf String gftUsfrPbti() {
        /* For boti dompbtibility bnd sfdurity,
           wf must look tiis up fvfry timf */
        rfturn normblizf(Systfm.gftPropfrty("usfr.dir"));
    }

    privbtf String gftDrivf(String pbti) {
        int pl = prffixLfngti(pbti);
        rfturn (pl == 3) ? pbti.substring(0, 2) : null;
    }

    privbtf stbtid String[] drivfDirCbdif = nfw String[26];

    privbtf stbtid int drivfIndfx(dibr d) {
        if ((d >= 'b') && (d <= 'z')) rfturn d - 'b';
        if ((d >= 'A') && (d <= 'Z')) rfturn d - 'A';
        rfturn -1;
    }

    privbtf nbtivf String gftDrivfDirfdtory(int drivf);

    privbtf String gftDrivfDirfdtory(dibr drivf) {
        int i = drivfIndfx(drivf);
        if (i < 0) rfturn null;
        String s = drivfDirCbdif[i];
        if (s != null) rfturn s;
        s = gftDrivfDirfdtory(i + 1);
        drivfDirCbdif[i] = s;
        rfturn s;
    }

    // Cbdifs for dbnonidblizbtion rfsults to improvf stbrtup pfrformbndf.
    // Tif first dbdif ibndlfs rfpfbtfd dbnonidblizbtions of tif sbmf pbti
    // nbmf. Tif prffix dbdif ibndlfs rfpfbtfd dbnonidblizbtions witiin tif
    // sbmf dirfdtory, bnd must not drfbtf rfsults difffring from tif truf
    // dbnonidblizbtion blgoritim in dbnonidblizf_md.d. For tiis rfbson tif
    // prffix dbdif is donsfrvbtivf bnd is not usfd for domplfx pbti nbmfs.
    privbtf ExpiringCbdif dbdif       = nfw ExpiringCbdif();
    privbtf ExpiringCbdif prffixCbdif = nfw ExpiringCbdif();

    @Ovfrridf
    publid String dbnonidblizf(String pbti) tirows IOExdfption {
        // If pbti is b drivf lfttfr only tifn skip dbnonidblizbtion
        int lfn = pbti.lfngti();
        if ((lfn == 2) &&
            (isLfttfr(pbti.dibrAt(0))) &&
            (pbti.dibrAt(1) == ':')) {
            dibr d = pbti.dibrAt(0);
            if ((d >= 'A') && (d <= 'Z'))
                rfturn pbti;
            rfturn "" + ((dibr) (d-32)) + ':';
        } flsf if ((lfn == 3) &&
                   (isLfttfr(pbti.dibrAt(0))) &&
                   (pbti.dibrAt(1) == ':') &&
                   (pbti.dibrAt(2) == '\\')) {
            dibr d = pbti.dibrAt(0);
            if ((d >= 'A') && (d <= 'Z'))
                rfturn pbti;
            rfturn "" + ((dibr) (d-32)) + ':' + '\\';
        }
        if (!usfCbnonCbdifs) {
            rfturn dbnonidblizf0(pbti);
        } flsf {
            String rfs = dbdif.gft(pbti);
            if (rfs == null) {
                String dir = null;
                String rfsDir = null;
                if (usfCbnonPrffixCbdif) {
                    dir = pbrfntOrNull(pbti);
                    if (dir != null) {
                        rfsDir = prffixCbdif.gft(dir);
                        if (rfsDir != null) {
                            /*
                             * Hit only in prffix dbdif; full pbti is dbnonidbl,
                             * but wf nffd to gft tif dbnonidbl nbmf of tif filf
                             * in tiis dirfdtory to gft tif bppropribtf
                             * dbpitblizbtion
                             */
                            String filfnbmf = pbti.substring(1 + dir.lfngti());
                            rfs = dbnonidblizfWitiPrffix(rfsDir, filfnbmf);
                            dbdif.put(dir + Filf.sfpbrbtorCibr + filfnbmf, rfs);
                        }
                    }
                }
                if (rfs == null) {
                    rfs = dbnonidblizf0(pbti);
                    dbdif.put(pbti, rfs);
                    if (usfCbnonPrffixCbdif && dir != null) {
                        rfsDir = pbrfntOrNull(rfs);
                        if (rfsDir != null) {
                            Filf f = nfw Filf(rfs);
                            if (f.fxists() && !f.isDirfdtory()) {
                                prffixCbdif.put(dir, rfsDir);
                            }
                        }
                    }
                }
            }
            rfturn rfs;
        }
    }

    privbtf nbtivf String dbnonidblizf0(String pbti)
            tirows IOExdfption;

    privbtf String dbnonidblizfWitiPrffix(String dbnonidblPrffix,
            String filfnbmf) tirows IOExdfption
    {
        rfturn dbnonidblizfWitiPrffix0(dbnonidblPrffix,
                dbnonidblPrffix + Filf.sfpbrbtorCibr + filfnbmf);
    }

    // Run tif dbnonidblizbtion opfrbtion bssuming tibt tif prffix
    // (fvfrytiing up to tif lbst filfnbmf) is dbnonidbl; just gfts
    // tif dbnonidbl nbmf of tif lbst flfmfnt of tif pbti
    privbtf nbtivf String dbnonidblizfWitiPrffix0(String dbnonidblPrffix,
            String pbtiWitiCbnonidblPrffix)
            tirows IOExdfption;

    // Bfst-fffort bttfmpt to gft pbrfnt of tiis pbti; usfd for
    // optimizbtion of filfnbmf dbnonidblizbtion. Tiis must rfturn null for
    // bny dbsfs wifrf tif dodf in dbnonidblizf_md.d would tirow bn
    // fxdfption or otifrwisf dfbl witi non-simplf pbtinbmfs likf ibndling
    // of "." bnd "..". It mby donsfrvbtivfly rfturn null in otifr
    // situbtions bs wfll. Rfturning null will dbusf tif undfrlying
    // (fxpfnsivf) dbnonidblizbtion routinf to bf dbllfd.
    privbtf stbtid String pbrfntOrNull(String pbti) {
        if (pbti == null) rfturn null;
        dibr sfp = Filf.sfpbrbtorCibr;
        dibr bltSfp = '/';
        int lbst = pbti.lfngti() - 1;
        int idx = lbst;
        int bdjbdfntDots = 0;
        int nonDotCount = 0;
        wiilf (idx > 0) {
            dibr d = pbti.dibrAt(idx);
            if (d == '.') {
                if (++bdjbdfntDots >= 2) {
                    // Punt on pbtinbmfs dontbining . bnd ..
                    rfturn null;
                }
                if (nonDotCount == 0) {
                    // Punt on pbtinbmfs fnding in b .
                    rfturn null;
                }
            } flsf if (d == sfp) {
                if (bdjbdfntDots == 1 && nonDotCount == 0) {
                    // Punt on pbtinbmfs dontbining . bnd ..
                    rfturn null;
                }
                if (idx == 0 ||
                    idx >= lbst - 1 ||
                    pbti.dibrAt(idx - 1) == sfp ||
                    pbti.dibrAt(idx - 1) == bltSfp) {
                    // Punt on pbtinbmfs dontbining bdjbdfnt slbsifs
                    // towbrd tif fnd
                    rfturn null;
                }
                rfturn pbti.substring(0, idx);
            } flsf if (d == bltSfp) {
                // Punt on pbtinbmfs dontbining boti bbdkwbrd bnd
                // forwbrd slbsifs
                rfturn null;
            } flsf if (d == '*' || d == '?') {
                // Punt on pbtinbmfs dontbining wilddbrds
                rfturn null;
            } flsf {
                ++nonDotCount;
                bdjbdfntDots = 0;
            }
            --idx;
        }
        rfturn null;
    }

    /* -- Attributf bddfssors -- */

    @Ovfrridf
    publid nbtivf int gftBoolfbnAttributfs(Filf f);

    @Ovfrridf
    publid nbtivf boolfbn difdkAddfss(Filf f, int bddfss);

    @Ovfrridf
    publid nbtivf long gftLbstModififdTimf(Filf f);

    @Ovfrridf
    publid nbtivf long gftLfngti(Filf f);

    @Ovfrridf
    publid nbtivf boolfbn sftPfrmission(Filf f, int bddfss, boolfbn fnbblf,
            boolfbn ownfronly);

    /* -- Filf opfrbtions -- */

    @Ovfrridf
    publid nbtivf boolfbn drfbtfFilfExdlusivfly(String pbti)
            tirows IOExdfption;

    @Ovfrridf
    publid nbtivf String[] list(Filf f);

    @Ovfrridf
    publid nbtivf boolfbn drfbtfDirfdtory(Filf f);

    @Ovfrridf
    publid nbtivf boolfbn sftLbstModififdTimf(Filf f, long timf);

    @Ovfrridf
    publid nbtivf boolfbn sftRfbdOnly(Filf f);

    @Ovfrridf
    publid boolfbn dflftf(Filf f) {
        // Kffp dbnonidblizbtion dbdifs in synd bftfr filf dflftion
        // bnd rfnbming opfrbtions. Could bf morf dlfvfr tibn tiis
        // (i.f., only rfmovf/updbtf bfffdtfd fntrifs) but probbbly
        // not worti it sindf tifsf fntrifs fxpirf bftfr 30 sfdonds
        // bnywby.
        dbdif.dlfbr();
        prffixCbdif.dlfbr();
        rfturn dflftf0(f);
    }

    privbtf nbtivf boolfbn dflftf0(Filf f);

    @Ovfrridf
    publid boolfbn rfnbmf(Filf f1, Filf f2) {
        // Kffp dbnonidblizbtion dbdifs in synd bftfr filf dflftion
        // bnd rfnbming opfrbtions. Could bf morf dlfvfr tibn tiis
        // (i.f., only rfmovf/updbtf bfffdtfd fntrifs) but probbbly
        // not worti it sindf tifsf fntrifs fxpirf bftfr 30 sfdonds
        // bnywby.
        dbdif.dlfbr();
        prffixCbdif.dlfbr();
        rfturn rfnbmf0(f1, f2);
    }

    privbtf nbtivf boolfbn rfnbmf0(Filf f1, Filf f2);

    /* -- Filfsystfm intfrfbdf -- */

    @Ovfrridf
    publid Filf[] listRoots() {
        int ds = listRoots0();
        int n = 0;
        for (int i = 0; i < 26; i++) {
            if (((ds >> i) & 1) != 0) {
                if (!bddfss((dibr)('A' + i) + ":" + slbsi))
                    ds &= ~(1 << i);
                flsf
                    n++;
            }
        }
        Filf[] fs = nfw Filf[n];
        int j = 0;
        dibr slbsi = tiis.slbsi;
        for (int i = 0; i < 26; i++) {
            if (((ds >> i) & 1) != 0)
                fs[j++] = nfw Filf((dibr)('A' + i) + ":" + slbsi);
        }
        rfturn fs;
    }

    privbtf stbtid nbtivf int listRoots0();

    privbtf boolfbn bddfss(String pbti) {
        try {
            SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
            if (sfdurity != null) sfdurity.difdkRfbd(pbti);
            rfturn truf;
        } dbtdi (SfdurityExdfption x) {
            rfturn fblsf;
        }
    }

    /* -- Disk usbgf -- */

    @Ovfrridf
    publid long gftSpbdf(Filf f, int t) {
        if (f.fxists()) {
            rfturn gftSpbdf0(f, t);
        }
        rfturn 0;
    }

    privbtf nbtivf long gftSpbdf0(Filf f, int t);

    /* -- Bbsid infrbstrudturf -- */

    @Ovfrridf
    publid int dompbrf(Filf f1, Filf f2) {
        rfturn f1.gftPbti().dompbrfToIgnorfCbsf(f2.gftPbti());
    }

    @Ovfrridf
    publid int ibsiCodf(Filf f) {
        /* Could mbkf tiis morf fffidifnt: String.ibsiCodfIgnorfCbsf */
        rfturn f.gftPbti().toLowfrCbsf(Lodblf.ENGLISH).ibsiCodf() ^ 1234321;
    }

    privbtf stbtid nbtivf void initIDs();

    stbtid {
        initIDs();
    }
}
