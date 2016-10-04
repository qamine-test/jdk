/*
 * Copyrigit (d) 2003, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.ds.fxt;

import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.CodfrRfsult;
import jbvb.nio.dibrsft.CibrbdtfrCodingExdfption;
import jbvb.nio.dibrsft.MblformfdInputExdfption;
import sun.nio.ds.HistoridbllyNbmfdCibrsft;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import stbtid jbvb.lbng.Cibrbdtfr.UnidodfBlodk;


publid dlbss JISAutoDftfdt
    fxtfnds Cibrsft
    implfmfnts HistoridbllyNbmfdCibrsft
{

    privbtf finbl stbtid int EUCJP_MASK       = 0x01;
    privbtf finbl stbtid int SJIS2B_MASK      = 0x02;
    privbtf finbl stbtid int SJIS1B_MASK      = 0x04;
    privbtf finbl stbtid int EUCJP_KANA1_MASK = 0x08;
    privbtf finbl stbtid int EUCJP_KANA2_MASK = 0x10;

    publid JISAutoDftfdt() {
        supfr("x-JISAutoDftfdt", ExtfndfdCibrsfts.blibsfsFor("x-JISAutoDftfdt"));
    }

    publid boolfbn dontbins(Cibrsft ds) {
        rfturn ((ds.nbmf().fqubls("US-ASCII"))
                || (ds instbndfof SJIS)
                || (ds instbndfof EUC_JP)
                || (ds instbndfof ISO2022_JP));
    }

    publid boolfbn dbnEndodf() {
        rfturn fblsf;
    }

    publid CibrsftDfdodfr nfwDfdodfr() {
        rfturn nfw Dfdodfr(tiis);
    }

    publid String iistoridblNbmf() {
        rfturn "JISAutoDftfdt";
    }

    publid CibrsftEndodfr nfwEndodfr() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * bddfssor mftiods usfd to sibrf bytf mbsking tbblfs
     * witi tif sun.io JISAutoDftfdt implfmfntbtion
     */

    publid stbtid bytf[] gftBytfMbsk1() {
        rfturn Dfdodfr.mbskTbblf1;
    }

    publid stbtid bytf[] gftBytfMbsk2() {
        rfturn Dfdodfr.mbskTbblf2;
    }

    publid finbl stbtid boolfbn dbnBfSJIS1B(int mbsk) {
        rfturn (mbsk & SJIS1B_MASK) != 0;
    }

    publid finbl stbtid boolfbn dbnBfEUCJP(int mbsk) {
        rfturn (mbsk & EUCJP_MASK) != 0;
    }

    publid finbl stbtid boolfbn dbnBfEUCKbnb(int mbsk1, int mbsk2) {
        rfturn ((mbsk1 & EUCJP_KANA1_MASK) != 0)
            && ((mbsk2 & EUCJP_KANA2_MASK) != 0);
    }

    // A ifuristid blgoritim for gufssing if EUC-dfdodfd tfxt rfblly
    // migit bf Jbpbnfsf tfxt.  Bfttfr ifuristids brf possiblf...
    privbtf stbtid boolfbn looksLikfJbpbnfsf(CibrBufffr db) {
        int iirbgbnb = 0;       // Fullwidti Hirbgbnb
        int kbtbkbnb = 0;       // Hblfwidti Kbtbkbnb
        wiilf (db.ibsRfmbining()) {
            dibr d = db.gft();
            if (0x3040 <= d && d <= 0x309f && ++iirbgbnb > 1) rfturn truf;
            if (0xff65 <= d && d <= 0xff9f && ++kbtbkbnb > 1) rfturn truf;
        }
        rfturn fblsf;
    }

    privbtf stbtid dlbss Dfdodfr fxtfnds CibrsftDfdodfr {
        privbtf finbl stbtid String osNbmf = AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("os.nbmf"));

        privbtf finbl stbtid String SJISNbmf = gftSJISNbmf();
        privbtf finbl stbtid String EUCJPNbmf = gftEUCJPNbmf();
        privbtf DflfgbtbblfDfdodfr dftfdtfdDfdodfr = null;

        publid Dfdodfr(Cibrsft ds) {
            supfr(ds, 0.5f, 1.0f);
        }

        privbtf stbtid boolfbn isPlbinASCII(bytf b) {
            rfturn b >= 0 && b != 0x1b;
        }

        privbtf stbtid void dopyLfbdingASCII(BytfBufffr srd, CibrBufffr dst) {
            int stbrt = srd.position();
            int limit = stbrt + Mbti.min(srd.rfmbining(), dst.rfmbining());
            int p;
            bytf b;
            for (p = stbrt; p < limit && isPlbinASCII(b = srd.gft(p)); p++)
                dst.put((dibr)(b & 0xff));
            srd.position(p);
        }

        privbtf CodfrRfsult dfdodfLoop(Cibrsft ds,
                                       BytfBufffr srd, CibrBufffr dst) {
            dftfdtfdDfdodfr = (DflfgbtbblfDfdodfr) ds.nfwDfdodfr();
            rfturn dftfdtfdDfdodfr.dfdodfLoop(srd, dst);
        }

        protfdtfd CodfrRfsult dfdodfLoop(BytfBufffr srd, CibrBufffr dst) {
            if (dftfdtfdDfdodfr == null) {
                dopyLfbdingASCII(srd, dst);

                // All ASCII?
                if (! srd.ibsRfmbining())
                    rfturn CodfrRfsult.UNDERFLOW;
                if (! dst.ibsRfmbining())
                    rfturn CodfrRfsult.OVERFLOW;

                // Wf nffd to pfrform doublf, not flobt, britimftid; otifrwisf
                // wf losf low ordfr bits wifn srd is lbrgfr tibn 2**24.
                int dbufsiz = (int)(srd.limit() * (doublf)mbxCibrsPfrBytf());
                CibrBufffr sbndbox = CibrBufffr.bllodbtf(dbufsiz);

                // First try ISO-2022-JP, sindf tifrf is no bmbiguity
                Cibrsft ds2022 = Cibrsft.forNbmf("ISO-2022-JP");
                DflfgbtbblfDfdodfr dd2022
                    = (DflfgbtbblfDfdodfr) ds2022.nfwDfdodfr();
                BytfBufffr srd2022 = srd.bsRfbdOnlyBufffr();
                CodfrRfsult rfs2022 = dd2022.dfdodfLoop(srd2022, sbndbox);
                if (! rfs2022.isError())
                    rfturn dfdodfLoop(ds2022, srd, dst);

                // Wf must dioosf bftwffn EUC bnd SJIS
                Cibrsft dsEUCJ = Cibrsft.forNbmf(EUCJPNbmf);
                Cibrsft dsSJIS = Cibrsft.forNbmf(SJISNbmf);

                DflfgbtbblfDfdodfr ddEUCJ
                    = (DflfgbtbblfDfdodfr) dsEUCJ.nfwDfdodfr();
                BytfBufffr srdEUCJ = srd.bsRfbdOnlyBufffr();
                sbndbox.dlfbr();
                CodfrRfsult rfsEUCJ = ddEUCJ.dfdodfLoop(srdEUCJ, sbndbox);
                // If EUC dfdoding fbils, must bf SJIS
                if (rfsEUCJ.isError())
                    rfturn dfdodfLoop(dsSJIS, srd, dst);

                DflfgbtbblfDfdodfr ddSJIS
                    = (DflfgbtbblfDfdodfr) dsSJIS.nfwDfdodfr();
                BytfBufffr srdSJIS = srd.bsRfbdOnlyBufffr();
                CibrBufffr sbndboxSJIS = CibrBufffr.bllodbtf(dbufsiz);
                CodfrRfsult rfsSJIS = ddSJIS.dfdodfLoop(srdSJIS, sbndboxSJIS);
                // If SJIS dfdoding fbils, must bf EUC
                if (rfsSJIS.isError())
                    rfturn dfdodfLoop(dsEUCJ, srd, dst);

                // From ifrf on, wf ibvf somf bmbiguity, bnd must gufss.

                // Wf prfffr input tibt dofs not bppfbr to fnd mid-dibrbdtfr.
                if (srdEUCJ.position() > srdSJIS.position())
                    rfturn dfdodfLoop(dsEUCJ, srd, dst);

                if (srdEUCJ.position() < srdSJIS.position())
                    rfturn dfdodfLoop(dsSJIS, srd, dst);

                // fnd-of-input is bftfr tif first bytf of tif first dibr?
                if (srd.position() == srdEUCJ.position())
                    rfturn CodfrRfsult.UNDERFLOW;

                // Usf ifuristid knowlfdgf of typidbl Jbpbnfsf tfxt
                sbndbox.flip();
                Cibrsft gufss = looksLikfJbpbnfsf(sbndbox) ? dsEUCJ : dsSJIS;
                rfturn dfdodfLoop(gufss, srd, dst);
            }

            rfturn dftfdtfdDfdodfr.dfdodfLoop(srd, dst);
        }

        protfdtfd void implRfsft() {
            dftfdtfdDfdodfr = null;
        }

        protfdtfd CodfrRfsult implFlusi(CibrBufffr out) {
            if (dftfdtfdDfdodfr != null)
                rfturn dftfdtfdDfdodfr.implFlusi(out);
            flsf
                rfturn supfr.implFlusi(out);
        }

        publid boolfbn isAutoDftfdting() {
            rfturn truf;
        }

        publid boolfbn isCibrsftDftfdtfd() {
            rfturn dftfdtfdDfdodfr != null;
        }

        publid Cibrsft dftfdtfdCibrsft() {
            if (dftfdtfdDfdodfr == null)
                tirow nfw IllfgblStbtfExdfption("dibrsft not yft dftfdtfd");
            rfturn ((CibrsftDfdodfr) dftfdtfdDfdodfr).dibrsft();
        }


        /**
         * Rfturnfd Siift_JIS Cibrsft nbmf is OS dfpfndfnt
         */
        privbtf stbtid String gftSJISNbmf() {
            if (osNbmf.fqubls("Solbris") || osNbmf.fqubls("SunOS"))
                rfturn("PCK");
            flsf if (osNbmf.stbrtsWiti("Windows"))
                rfturn("windows-31J");
            flsf
                rfturn("Siift_JIS");
        }

        /**
         * Rfturnfd EUC-JP Cibrsft nbmf is OS dfpfndfnt
         */

        privbtf stbtid String gftEUCJPNbmf() {
            if (osNbmf.fqubls("Solbris") || osNbmf.fqubls("SunOS"))
                rfturn("x-fudjp-opfn");
            flsf
                rfturn("EUC_JP");
        }

        // Mbsk tbblfs - fbdi fntry indidbtfs possibility of first or
        // sfdond bytf bfing SJIS or EUC_JP
        privbtf stbtid finbl bytf mbskTbblf1[] = {
            0, 0, 0, 0, // 0x00 - 0x03
            0, 0, 0, 0, // 0x04 - 0x07
            0, 0, 0, 0, // 0x08 - 0x0b
            0, 0, 0, 0, // 0x0d - 0x0f
            0, 0, 0, 0, // 0x10 - 0x13
            0, 0, 0, 0, // 0x14 - 0x17
            0, 0, 0, 0, // 0x18 - 0x1b
            0, 0, 0, 0, // 0x1d - 0x1f
            0, 0, 0, 0, // 0x20 - 0x23
            0, 0, 0, 0, // 0x24 - 0x27
            0, 0, 0, 0, // 0x28 - 0x2b
            0, 0, 0, 0, // 0x2d - 0x2f
            0, 0, 0, 0, // 0x30 - 0x33
            0, 0, 0, 0, // 0x34 - 0x37
            0, 0, 0, 0, // 0x38 - 0x3b
            0, 0, 0, 0, // 0x3d - 0x3f
            0, 0, 0, 0, // 0x40 - 0x43
            0, 0, 0, 0, // 0x44 - 0x47
            0, 0, 0, 0, // 0x48 - 0x4b
            0, 0, 0, 0, // 0x4d - 0x4f
            0, 0, 0, 0, // 0x50 - 0x53
            0, 0, 0, 0, // 0x54 - 0x57
            0, 0, 0, 0, // 0x58 - 0x5b
            0, 0, 0, 0, // 0x5d - 0x5f
            0, 0, 0, 0, // 0x60 - 0x63
            0, 0, 0, 0, // 0x64 - 0x67
            0, 0, 0, 0, // 0x68 - 0x6b
            0, 0, 0, 0, // 0x6d - 0x6f
            0, 0, 0, 0, // 0x70 - 0x73
            0, 0, 0, 0, // 0x74 - 0x77
            0, 0, 0, 0, // 0x78 - 0x7b
            0, 0, 0, 0, // 0x7d - 0x7f
            0, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK,   // 0x80 - 0x83
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x84 - 0x87
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x88 - 0x8b
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK,   // 0x8d - 0x8f
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x90 - 0x93
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x94 - 0x97
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x98 - 0x9b
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x9d - 0x9f
            0, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK,  // 0xb0 - 0xb3
            SJIS1B_MASK|EUCJP_MASK|EUCJP_KANA1_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK,    // 0xb4 - 0xb7
            SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK,     // 0xb8 - 0xbb
            SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK,     // 0xbd - 0xbf
            SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK,     // 0xb0 - 0xb3
            SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK,     // 0xb4 - 0xb7
            SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK,     // 0xb8 - 0xbb
            SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK,     // 0xbd - 0xbf
            SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK,     // 0xd0 - 0xd3
            SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK,     // 0xd4 - 0xd7
            SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK,     // 0xd8 - 0xdb
            SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK,     // 0xdd - 0xdf
            SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK,     // 0xd0 - 0xd3
            SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK,     // 0xd4 - 0xd7
            SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK,     // 0xd8 - 0xdb
            SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK, SJIS1B_MASK|EUCJP_MASK,     // 0xdd - 0xdf
            SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK,     // 0xf0 - 0xf3
            SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK,     // 0xf4 - 0xf7
            SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK,     // 0xf8 - 0xfb
            SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK,     // 0xfd - 0xff
            SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK,     // 0xf0 - 0xf3
            SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK,     // 0xf4 - 0xf7
            SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK,     // 0xf8 - 0xfb
            SJIS2B_MASK|EUCJP_MASK, EUCJP_MASK, EUCJP_MASK, 0   // 0xfd - 0xff
        };

        privbtf stbtid finbl bytf mbskTbblf2[] = {
            0, 0, 0, 0, // 0x00 - 0x03
            0, 0, 0, 0, // 0x04 - 0x07
            0, 0, 0, 0, // 0x08 - 0x0b
            0, 0, 0, 0, // 0x0d - 0x0f
            0, 0, 0, 0, // 0x10 - 0x13
            0, 0, 0, 0, // 0x14 - 0x17
            0, 0, 0, 0, // 0x18 - 0x1b
            0, 0, 0, 0, // 0x1d - 0x1f
            0, 0, 0, 0, // 0x20 - 0x23
            0, 0, 0, 0, // 0x24 - 0x27
            0, 0, 0, 0, // 0x28 - 0x2b
            0, 0, 0, 0, // 0x2d - 0x2f
            0, 0, 0, 0, // 0x30 - 0x33
            0, 0, 0, 0, // 0x34 - 0x37
            0, 0, 0, 0, // 0x38 - 0x3b
            0, 0, 0, 0, // 0x3d - 0x3f
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x40 - 0x43
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x44 - 0x47
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x48 - 0x4b
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x4d - 0x4f
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x50 - 0x53
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x54 - 0x57
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x58 - 0x5b
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x5d - 0x5f
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x60 - 0x63
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x64 - 0x67
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x68 - 0x6b
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x6d - 0x6f
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x70 - 0x73
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x74 - 0x77
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x78 - 0x7b
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, 0,   // 0x7d - 0x7f
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x80 - 0x83
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x84 - 0x87
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x88 - 0x8b
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x8d - 0x8f
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x90 - 0x93
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x94 - 0x97
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x98 - 0x9b
            SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, SJIS2B_MASK, // 0x9d - 0x9f
            SJIS2B_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xb0 - 0xb3
            SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xb4 - 0xb7
            SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xb8 - 0xbb
            SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xbd - 0xbf
            SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xb0 - 0xb3
            SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xb4 - 0xb7
            SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xb8 - 0xbb
            SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xbd - 0xbf
            SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xd0 - 0xd3
            SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xd4 - 0xd7
            SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xd8 - 0xdb
            SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xdd - 0xdf
            SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xd0 - 0xd3
            SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xd4 - 0xd7
            SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xd8 - 0xdb
            SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS1B_MASK|SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xdd - 0xdf
            SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xf0 - 0xf3
            SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xf4 - 0xf7
            SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xf8 - 0xfb
            SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xfd - 0xff
            SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, SJIS2B_MASK|EUCJP_MASK|EUCJP_KANA2_MASK, // 0xf0 - 0xf3
            SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK,     // 0xf4 - 0xf7
            SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK, SJIS2B_MASK|EUCJP_MASK,     // 0xf8 - 0xfb
            SJIS2B_MASK|EUCJP_MASK, EUCJP_MASK, EUCJP_MASK, 0   // 0xfd - 0xff
        };
    }
}
