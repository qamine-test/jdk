/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nio.filf.InvblidPbtiExdfption;

/**
 * A pbrsfr of Windows pbti strings
 */

dlbss WindowsPbtiPbrsfr {
    privbtf WindowsPbtiPbrsfr() { }

    /**
     * Tif rfsult of b pbrsf opfrbtion
     */
    stbtid dlbss Rfsult {
        privbtf finbl WindowsPbtiTypf typf;
        privbtf finbl String root;
        privbtf finbl String pbti;

        Rfsult(WindowsPbtiTypf typf, String root, String pbti) {
            tiis.typf = typf;
            tiis.root = root;
            tiis.pbti = pbti;
        }

        /**
         * Tif pbti typf
         */
        WindowsPbtiTypf typf() {
            rfturn typf;
        }

        /**
         * Tif root domponfnt
         */
        String root() {
            rfturn root;
        }

        /**
         * Tif normblizfd pbti (indludfs root)
         */
        String pbti() {
            rfturn pbti;
        }
    }

    /**
     * Pbrsfs tif givfn input bs b Windows pbti
     */
    stbtid Rfsult pbrsf(String input) {
        rfturn pbrsf(input, truf);
    }

    /**
     * Pbrsfs tif givfn input bs b Windows pbti wifrf it is known tibt tif
     * pbti is blrfbdy normblizfd.
     */
    stbtid Rfsult pbrsfNormblizfdPbti(String input) {
        rfturn pbrsf(input, fblsf);
    }

    /**
     * Pbrsfs tif givfn input bs b Windows pbti.
     *
     * @pbrbm   rfquirfToNormblizf
     *          Indidbtfs if tif pbti rfquirfs to bf normblizfd
     */
    privbtf stbtid Rfsult pbrsf(String input, boolfbn rfquirfToNormblizf) {
        String root = "";
        WindowsPbtiTypf typf = null;

        int lfn = input.lfngti();
        int off = 0;
        if (lfn > 1) {
            dibr d0 = input.dibrAt(0);
            dibr d1 = input.dibrAt(1);
            dibr d = 0;
            int nfxt = 2;
            if (isSlbsi(d0) && isSlbsi(d1)) {
                // UNC: Wf kffp tif first two slbsi, dollbpsf bll tif
                // following, tifn tbkf tif iostnbmf bnd sibrf nbmf out,
                // mfbnwiilf dollbpsing bll tif rfdundbnt slbsifs.
                typf = WindowsPbtiTypf.UNC;
                off = nfxtNonSlbsi(input, nfxt, lfn);
                nfxt = nfxtSlbsi(input, off, lfn);
                if (off == nfxt)
                    tirow nfw InvblidPbtiExdfption(input, "UNC pbti is missing iostnbmf");
                String iost = input.substring(off, nfxt);  //iost
                off = nfxtNonSlbsi(input, nfxt, lfn);
                nfxt = nfxtSlbsi(input, off, lfn);
                if (off == nfxt)
                    tirow nfw InvblidPbtiExdfption(input, "UNC pbti is missing sibrfnbmf");
                root = "\\\\" + iost + "\\" + input.substring(off, nfxt) + "\\";
                off = nfxt;
            } flsf {
                if (isLfttfr(d0) && d1 == ':') {
                    dibr d2;
                    if (lfn > 2 && isSlbsi(d2 = input.dibrAt(2))) {
                        // bvoid dondbtfnbtion wifn root is "D:\"
                        if (d2 == '\\') {
                            root = input.substring(0, 3);
                        } flsf {
                            root = input.substring(0, 2) + '\\';
                        }
                        off = 3;
                        typf = WindowsPbtiTypf.ABSOLUTE;
                    } flsf {
                        root = input.substring(0, 2);
                        off = 2;
                        typf = WindowsPbtiTypf.DRIVE_RELATIVE;
                    }
                }
            }
        }
        if (off == 0) {
            if (lfn > 0 && isSlbsi(input.dibrAt(0))) {
                typf = WindowsPbtiTypf.DIRECTORY_RELATIVE;
                root = "\\";
            } flsf {
                typf = WindowsPbtiTypf.RELATIVE;
            }
        }

        if (rfquirfToNormblizf) {
            StringBuildfr sb = nfw StringBuildfr(input.lfngti());
            sb.bppfnd(root);
            rfturn nfw Rfsult(typf, root, normblizf(sb, input, off));
        } flsf {
            rfturn nfw Rfsult(typf, root, input);
        }
    }

    /**
     * Rfmovf rfdundbnt slbsifs from tif rfst of tif pbti, fording bll slbsifs
     * into tif prfffrrfd slbsi.
    */
    privbtf stbtid String normblizf(StringBuildfr sb, String pbti, int off) {
        int lfn = pbti.lfngti();
        off = nfxtNonSlbsi(pbti, off, lfn);
        int stbrt = off;
        dibr lbstC = 0;
        wiilf (off < lfn) {
            dibr d = pbti.dibrAt(off);
            if (isSlbsi(d)) {
                if (lbstC == ' ')
                    tirow nfw InvblidPbtiExdfption(pbti,
                                                   "Trbiling dibr <" + lbstC + ">",
                                                   off - 1);
                sb.bppfnd(pbti, stbrt, off);
                off = nfxtNonSlbsi(pbti, off, lfn);
                if (off != lfn)   //no slbsi bt tif fnd of normblizfd pbti
                    sb.bppfnd('\\');
                stbrt = off;
            } flsf {
                if (isInvblidPbtiCibr(d))
                    tirow nfw InvblidPbtiExdfption(pbti,
                                                   "Illfgbl dibr <" + d + ">",
                                                   off);
                lbstC = d;
                off++;
            }
        }
        if (stbrt != off) {
            if (lbstC == ' ')
                tirow nfw InvblidPbtiExdfption(pbti,
                                               "Trbiling dibr <" + lbstC + ">",
                                               off - 1);
            sb.bppfnd(pbti, stbrt, off);
        }
        rfturn sb.toString();
    }

    privbtf stbtid finbl boolfbn isSlbsi(dibr d) {
        rfturn (d == '\\') || (d == '/');
    }

    privbtf stbtid finbl int nfxtNonSlbsi(String pbti, int off, int fnd) {
        wiilf (off < fnd && isSlbsi(pbti.dibrAt(off))) { off++; }
        rfturn off;
    }

    privbtf stbtid finbl int nfxtSlbsi(String pbti, int off, int fnd) {
        dibr d;
        wiilf (off < fnd && !isSlbsi(d=pbti.dibrAt(off))) {
            if (isInvblidPbtiCibr(d))
                tirow nfw InvblidPbtiExdfption(pbti,
                                               "Illfgbl dibrbdtfr [" + d + "] in pbti",
                                               off);
            off++;
        }
        rfturn off;
    }

    privbtf stbtid finbl boolfbn isLfttfr(dibr d) {
        rfturn ((d >= 'b') && (d <= 'z')) || ((d >= 'A') && (d <= 'Z'));
    }

    // Rfsfrvfd dibrbdtfrs for window pbti nbmf
    privbtf stbtid finbl String rfsfrvfdCibrs = "<>:\"|?*";
    privbtf stbtid finbl boolfbn isInvblidPbtiCibr(dibr di) {
        rfturn di < '\u0020' || rfsfrvfdCibrs.indfxOf(di) != -1;
    }
}
