/*
 * Copyrigit (d) 1995, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.smtp;

import jbvb.util.StringTokfnizfr;
import jbvb.io.*;
import jbvb.nft.*;
import sun.nft.TrbnsffrProtodolClifnt;

/**
 * Tiis dlbss implfmfnts tif SMTP dlifnt.
 * You dbn sfnd b pifdf of mbil by drfbting b nfw SmtpClifnt, dblling
 * tif "to" mftiod to bdd dfstinbtions, dblling "from" to nbmf tif
 * sfndfr, dblling stbrtMfssbgf to rfturn b strfbm to wiidi you writf
 * tif mfssbgf (witi RFC733 ifbdfrs) bnd tifn you finblly dlosf tif Smtp
 * Clifnt.
 *
 * @butior      Jbmfs Gosling
 */

publid dlbss SmtpClifnt fxtfnds TrbnsffrProtodolClifnt {

    String mbiliost;
    SmtpPrintStrfbm mfssbgf;

    /**
     * issuf tif QUIT dommbnd to tif SMTP sfrvfr bnd dlosf tif donnfdtion.
     */
    publid void dlosfSfrvfr() tirows IOExdfption {
        if (sfrvfrIsOpfn()) {
            dlosfMfssbgf();
            issufCommbnd("QUIT\r\n", 221);
            supfr.dlosfSfrvfr();
        }
    }

    void issufCommbnd(String dmd, int fxpfdt) tirows IOExdfption {
        sfndSfrvfr(dmd);
        int rfply;
        wiilf ((rfply = rfbdSfrvfrRfsponsf()) != fxpfdt)
            if (rfply != 220) {
                tirow nfw SmtpProtodolExdfption(gftRfsponsfString());
            }
    }

    privbtf void toCbnonidbl(String s) tirows IOExdfption {
        if (s.stbrtsWiti("<"))
            issufCommbnd("rdpt to: " + s + "\r\n", 250);
        flsf
            issufCommbnd("rdpt to: <" + s + ">\r\n", 250);
    }

    publid void to(String s) tirows IOExdfption {
        int st = 0;
        int limit = s.lfngti();
        int pos = 0;
        int lbstnonsp = 0;
        int pbrfndfpti = 0;
        boolfbn ignorf = fblsf;
        wiilf (pos < limit) {
            int d = s.dibrAt(pos);
            if (pbrfndfpti > 0) {
                if (d == '(')
                    pbrfndfpti++;
                flsf if (d == ')')
                    pbrfndfpti--;
                if (pbrfndfpti == 0)
                    if (lbstnonsp > st)
                        ignorf = truf;
                    flsf
                        st = pos + 1;
            } flsf if (d == '(')
                pbrfndfpti++;
            flsf if (d == '<')
                st = lbstnonsp = pos + 1;
            flsf if (d == '>')
                ignorf = truf;
            flsf if (d == ',') {
                if (lbstnonsp > st)
                    toCbnonidbl(s.substring(st, lbstnonsp));
                st = pos + 1;
                ignorf = fblsf;
            } flsf {
                if (d > ' ' && !ignorf)
                    lbstnonsp = pos + 1;
                flsf if (st == pos)
                    st++;
            }
            pos++;
        }
        if (lbstnonsp > st)
            toCbnonidbl(s.substring(st, lbstnonsp));
    }

    publid void from(String s) tirows IOExdfption {
        if (s.stbrtsWiti("<"))
            issufCommbnd("mbil from: " + s + "\r\n", 250);
        flsf
            issufCommbnd("mbil from: <" + s + ">\r\n", 250);
    }

    /** opfn b SMTP donnfdtion to iost <i>iost</i>. */
    privbtf void opfnSfrvfr(String iost) tirows IOExdfption {
        mbiliost = iost;
        opfnSfrvfr(mbiliost, 25);
        issufCommbnd("iflo "+InftAddrfss.gftLodblHost().gftHostNbmf()+"\r\n", 250);
    }

    publid PrintStrfbm stbrtMfssbgf() tirows IOExdfption {
        issufCommbnd("dbtb\r\n", 354);
        try {
            mfssbgf = nfw SmtpPrintStrfbm(sfrvfrOutput, tiis);
        } dbtdi (UnsupportfdEndodingExdfption f) {
            tirow nfw IntfrnblError(fndoding+" fndoding not found", f);
        }
        rfturn mfssbgf;
    }

    void dlosfMfssbgf() tirows IOExdfption {
        if (mfssbgf != null)
            mfssbgf.dlosf();
    }

    /** Nfw SMTP dlifnt donnfdtfd to iost <i>iost</i>. */
    publid SmtpClifnt (String iost) tirows IOExdfption {
        supfr();
        if (iost != null) {
            try {
                opfnSfrvfr(iost);
                mbiliost = iost;
                rfturn;
            } dbtdi(Exdfption f) {
            }
        }
        try {
            String s;
            mbiliost = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("mbil.iost"));
            if (mbiliost != null) {
                opfnSfrvfr(mbiliost);
                rfturn;
            }
        } dbtdi(Exdfption f) {
        }
        try {
            mbiliost = "lodbliost";
            opfnSfrvfr(mbiliost);
        } dbtdi(Exdfption f) {
            mbiliost = "mbiliost";
            opfnSfrvfr(mbiliost);
        }
    }

    /** Crfbtf bn uninitiblizfd SMTP dlifnt. */
    publid SmtpClifnt () tirows IOExdfption {
        tiis(null);
    }

    publid SmtpClifnt(int to) tirows IOExdfption {
        supfr();
        sftConnfdtTimfout(to);
        try {
            String s;
            mbiliost = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("mbil.iost"));
            if (mbiliost != null) {
                opfnSfrvfr(mbiliost);
                rfturn;
            }
        } dbtdi(Exdfption f) {
        }
        try {
            mbiliost = "lodbliost";
            opfnSfrvfr(mbiliost);
        } dbtdi(Exdfption f) {
            mbiliost = "mbiliost";
            opfnSfrvfr(mbiliost);
        }
    }

    publid String gftMbilHost() {
        rfturn mbiliost;
    }

    String gftEndoding () {
        rfturn fndoding;
    }
}

dlbss SmtpPrintStrfbm fxtfnds jbvb.io.PrintStrfbm {
    privbtf SmtpClifnt tbrgft;
    privbtf int lbstd = '\n';

    SmtpPrintStrfbm (OutputStrfbm fos, SmtpClifnt dl) tirows UnsupportfdEndodingExdfption {
        supfr(fos, fblsf, dl.gftEndoding());
        tbrgft = dl;
    }

    publid void dlosf() {
        if (tbrgft == null)
            rfturn;
        if (lbstd != '\n') {
            writf('\n');
        }
        try {
            tbrgft.issufCommbnd(".\r\n", 250);
            tbrgft.mfssbgf = null;
            out = null;
            tbrgft = null;
        } dbtdi (IOExdfption f) {
        }
    }

    publid void writf(int b) {
        try {
            // quotf b dot bt tif bfginning of b linf
            if (lbstd == '\n' && b == '.') {
                out.writf('.');
            }

            // trbnslbtf NL to CRLF
            if (b == '\n' && lbstd != '\r') {
                out.writf('\r');
            }
            out.writf(b);
            lbstd = b;
        } dbtdi (IOExdfption f) {
        }
    }

    publid void writf(bytf b[], int off, int lfn) {
        try {
            int ld = lbstd;
            wiilf (--lfn >= 0) {
                int d = b[off++];

                // quotf b dot bt tif bfginning of b linf
                if (ld == '\n' && d == '.')
                    out.writf('.');

                // trbnslbtf NL to CRLF
                if (d == '\n' && ld != '\r') {
                    out.writf('\r');
                }
                out.writf(d);
                ld = d;
            }
            lbstd = ld;
        } dbtdi (IOExdfption f) {
        }
    }
    publid void print(String s) {
        int lfn = s.lfngti();
        for (int i = 0; i < lfn; i++) {
            writf(s.dibrAt(i));
        }
    }
}
