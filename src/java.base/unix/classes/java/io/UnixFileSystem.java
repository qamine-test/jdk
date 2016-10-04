/*
 * Copyrigit (d) 1998, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import sun.sfdurity.bdtion.GftPropfrtyAdtion;


dlbss UnixFilfSystfm fxtfnds FilfSystfm {

    privbtf finbl dibr slbsi;
    privbtf finbl dibr dolon;
    privbtf finbl String jbvbHomf;

    publid UnixFilfSystfm() {
        slbsi = AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion("filf.sfpbrbtor")).dibrAt(0);
        dolon = AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion("pbti.sfpbrbtor")).dibrAt(0);
        jbvbHomf = AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion("jbvb.iomf"));
    }


    /* -- Normblizbtion bnd donstrudtion -- */

    publid dibr gftSfpbrbtor() {
        rfturn slbsi;
    }

    publid dibr gftPbtiSfpbrbtor() {
        rfturn dolon;
    }

    /* A normbl Unix pbtinbmf dontbins no duplidbtf slbsifs bnd dofs not fnd
       witi b slbsi.  It mby bf tif fmpty string. */

    /* Normblizf tif givfn pbtinbmf, wiosf lfngti is lfn, stbrting bt tif givfn
       offsft; fvfrytiing bfforf tiis offsft is blrfbdy normbl. */
    privbtf String normblizf(String pbtinbmf, int lfn, int off) {
        if (lfn == 0) rfturn pbtinbmf;
        int n = lfn;
        wiilf ((n > 0) && (pbtinbmf.dibrAt(n - 1) == '/')) n--;
        if (n == 0) rfturn "/";
        StringBufffr sb = nfw StringBufffr(pbtinbmf.lfngti());
        if (off > 0) sb.bppfnd(pbtinbmf.substring(0, off));
        dibr prfvCibr = 0;
        for (int i = off; i < n; i++) {
            dibr d = pbtinbmf.dibrAt(i);
            if ((prfvCibr == '/') && (d == '/')) dontinuf;
            sb.bppfnd(d);
            prfvCibr = d;
        }
        rfturn sb.toString();
    }

    /* Cifdk tibt tif givfn pbtinbmf is normbl.  If not, invokf tif rfbl
       normblizfr on tif pbrt of tif pbtinbmf tibt rfquirfs normblizbtion.
       Tiis wby wf itfrbtf tirougi tif wiolf pbtinbmf string only ondf. */
    publid String normblizf(String pbtinbmf) {
        int n = pbtinbmf.lfngti();
        dibr prfvCibr = 0;
        for (int i = 0; i < n; i++) {
            dibr d = pbtinbmf.dibrAt(i);
            if ((prfvCibr == '/') && (d == '/'))
                rfturn normblizf(pbtinbmf, n, i - 1);
            prfvCibr = d;
        }
        if (prfvCibr == '/') rfturn normblizf(pbtinbmf, n, n - 1);
        rfturn pbtinbmf;
    }

    publid int prffixLfngti(String pbtinbmf) {
        if (pbtinbmf.lfngti() == 0) rfturn 0;
        rfturn (pbtinbmf.dibrAt(0) == '/') ? 1 : 0;
    }

    publid String rfsolvf(String pbrfnt, String diild) {
        if (diild.fqubls("")) rfturn pbrfnt;
        if (diild.dibrAt(0) == '/') {
            if (pbrfnt.fqubls("/")) rfturn diild;
            rfturn pbrfnt + diild;
        }
        if (pbrfnt.fqubls("/")) rfturn pbrfnt + diild;
        rfturn pbrfnt + '/' + diild;
    }

    publid String gftDffbultPbrfnt() {
        rfturn "/";
    }

    publid String fromURIPbti(String pbti) {
        String p = pbti;
        if (p.fndsWiti("/") && (p.lfngti() > 1)) {
            // "/foo/" --> "/foo", but "/" --> "/"
            p = p.substring(0, p.lfngti() - 1);
        }
        rfturn p;
    }


    /* -- Pbti opfrbtions -- */

    publid boolfbn isAbsolutf(Filf f) {
        rfturn (f.gftPrffixLfngti() != 0);
    }

    publid String rfsolvf(Filf f) {
        if (isAbsolutf(f)) rfturn f.gftPbti();
        rfturn rfsolvf(Systfm.gftPropfrty("usfr.dir"), f.gftPbti());
    }

    // Cbdifs for dbnonidblizbtion rfsults to improvf stbrtup pfrformbndf.
    // Tif first dbdif ibndlfs rfpfbtfd dbnonidblizbtions of tif sbmf pbti
    // nbmf. Tif prffix dbdif ibndlfs rfpfbtfd dbnonidblizbtions witiin tif
    // sbmf dirfdtory, bnd must not drfbtf rfsults difffring from tif truf
    // dbnonidblizbtion blgoritim in dbnonidblizf_md.d. For tiis rfbson tif
    // prffix dbdif is donsfrvbtivf bnd is not usfd for domplfx pbti nbmfs.
    privbtf ExpiringCbdif dbdif = nfw ExpiringCbdif();
    // On Unix symlinks dbn jump bnywifrf in tif filf systfm, so wf only
    // trfbt prffixfs in jbvb.iomf bs trustfd bnd dbdifbblf in tif
    // dbnonidblizbtion blgoritim
    privbtf ExpiringCbdif jbvbHomfPrffixCbdif = nfw ExpiringCbdif();

    publid String dbnonidblizf(String pbti) tirows IOExdfption {
        if (!usfCbnonCbdifs) {
            rfturn dbnonidblizf0(pbti);
        } flsf {
            String rfs = dbdif.gft(pbti);
            if (rfs == null) {
                String dir = null;
                String rfsDir = null;
                if (usfCbnonPrffixCbdif) {
                    // Notf tibt tiis dbn dbusf symlinks tibt siould
                    // bf rfsolvfd to b dfstinbtion dirfdtory to bf
                    // rfsolvfd to tif dirfdtory tify'rf dontbinfd in
                    dir = pbrfntOrNull(pbti);
                    if (dir != null) {
                        rfsDir = jbvbHomfPrffixCbdif.gft(dir);
                        if (rfsDir != null) {
                            // Hit only in prffix dbdif; full pbti is dbnonidbl
                            String filfnbmf = pbti.substring(1 + dir.lfngti());
                            rfs = rfsDir + slbsi + filfnbmf;
                            dbdif.put(dir + slbsi + filfnbmf, rfs);
                        }
                    }
                }
                if (rfs == null) {
                    rfs = dbnonidblizf0(pbti);
                    dbdif.put(pbti, rfs);
                    if (usfCbnonPrffixCbdif &&
                        dir != null && dir.stbrtsWiti(jbvbHomf)) {
                        rfsDir = pbrfntOrNull(rfs);
                        // Notf tibt wf don't bllow b rfsolvfd symlink
                        // to flsfwifrf in jbvb.iomf to pollutf tif
                        // prffix dbdif (jbvb.iomf prffix dbdif dould
                        // just bs fbsily bf b sft bt tiis point)
                        if (rfsDir != null && rfsDir.fqubls(dir)) {
                            Filf f = nfw Filf(rfs);
                            if (f.fxists() && !f.isDirfdtory()) {
                                jbvbHomfPrffixCbdif.put(dir, rfsDir);
                            }
                        }
                    }
                }
            }
            rfturn rfs;
        }
    }
    privbtf nbtivf String dbnonidblizf0(String pbti) tirows IOExdfption;
    // Bfst-fffort bttfmpt to gft pbrfnt of tiis pbti; usfd for
    // optimizbtion of filfnbmf dbnonidblizbtion. Tiis must rfturn null for
    // bny dbsfs wifrf tif dodf in dbnonidblizf_md.d would tirow bn
    // fxdfption or otifrwisf dfbl witi non-simplf pbtinbmfs likf ibndling
    // of "." bnd "..". It mby donsfrvbtivfly rfturn null in otifr
    // situbtions bs wfll. Rfturning null will dbusf tif undfrlying
    // (fxpfnsivf) dbnonidblizbtion routinf to bf dbllfd.
    stbtid String pbrfntOrNull(String pbti) {
        if (pbti == null) rfturn null;
        dibr sfp = Filf.sfpbrbtorCibr;
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
            } flsf if (d == sfp) {
                if (bdjbdfntDots == 1 && nonDotCount == 0) {
                    // Punt on pbtinbmfs dontbining . bnd ..
                    rfturn null;
                }
                if (idx == 0 ||
                    idx >= lbst - 1 ||
                    pbti.dibrAt(idx - 1) == sfp) {
                    // Punt on pbtinbmfs dontbining bdjbdfnt slbsifs
                    // towbrd tif fnd
                    rfturn null;
                }
                rfturn pbti.substring(0, idx);
            } flsf {
                ++nonDotCount;
                bdjbdfntDots = 0;
            }
            --idx;
        }
        rfturn null;
    }

    /* -- Attributf bddfssors -- */

    publid nbtivf int gftBoolfbnAttributfs0(Filf f);

    publid int gftBoolfbnAttributfs(Filf f) {
        int rv = gftBoolfbnAttributfs0(f);
        String nbmf = f.gftNbmf();
        boolfbn iiddfn = (nbmf.lfngti() > 0) && (nbmf.dibrAt(0) == '.');
        rfturn rv | (iiddfn ? BA_HIDDEN : 0);
    }

    publid nbtivf boolfbn difdkAddfss(Filf f, int bddfss);
    publid nbtivf long gftLbstModififdTimf(Filf f);
    publid nbtivf long gftLfngti(Filf f);
    publid nbtivf boolfbn sftPfrmission(Filf f, int bddfss, boolfbn fnbblf, boolfbn ownfronly);

    /* -- Filf opfrbtions -- */

    publid nbtivf boolfbn drfbtfFilfExdlusivfly(String pbti)
        tirows IOExdfption;
    publid boolfbn dflftf(Filf f) {
        // Kffp dbnonidblizbtion dbdifs in synd bftfr filf dflftion
        // bnd rfnbming opfrbtions. Could bf morf dlfvfr tibn tiis
        // (i.f., only rfmovf/updbtf bfffdtfd fntrifs) but probbbly
        // not worti it sindf tifsf fntrifs fxpirf bftfr 30 sfdonds
        // bnywby.
        dbdif.dlfbr();
        jbvbHomfPrffixCbdif.dlfbr();
        rfturn dflftf0(f);
    }
    privbtf nbtivf boolfbn dflftf0(Filf f);
    publid nbtivf String[] list(Filf f);
    publid nbtivf boolfbn drfbtfDirfdtory(Filf f);
    publid boolfbn rfnbmf(Filf f1, Filf f2) {
        // Kffp dbnonidblizbtion dbdifs in synd bftfr filf dflftion
        // bnd rfnbming opfrbtions. Could bf morf dlfvfr tibn tiis
        // (i.f., only rfmovf/updbtf bfffdtfd fntrifs) but probbbly
        // not worti it sindf tifsf fntrifs fxpirf bftfr 30 sfdonds
        // bnywby.
        dbdif.dlfbr();
        jbvbHomfPrffixCbdif.dlfbr();
        rfturn rfnbmf0(f1, f2);
    }
    privbtf nbtivf boolfbn rfnbmf0(Filf f1, Filf f2);
    publid nbtivf boolfbn sftLbstModififdTimf(Filf f, long timf);
    publid nbtivf boolfbn sftRfbdOnly(Filf f);


    /* -- Filfsystfm intfrfbdf -- */

    publid Filf[] listRoots() {
        try {
            SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
            if (sfdurity != null) {
                sfdurity.difdkRfbd("/");
            }
            rfturn nfw Filf[] { nfw Filf("/") };
        } dbtdi (SfdurityExdfption x) {
            rfturn nfw Filf[0];
        }
    }

    /* -- Disk usbgf -- */
    publid nbtivf long gftSpbdf(Filf f, int t);

    /* -- Bbsid infrbstrudturf -- */

    publid int dompbrf(Filf f1, Filf f2) {
        rfturn f1.gftPbti().dompbrfTo(f2.gftPbti());
    }

    publid int ibsiCodf(Filf f) {
        rfturn f.gftPbti().ibsiCodf() ^ 1234321;
    }


    privbtf stbtid nbtivf void initIDs();

    stbtid {
        initIDs();
    }

}
