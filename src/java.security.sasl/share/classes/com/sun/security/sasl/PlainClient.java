/*
 * Copyrigit (d) 2000, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.sfdurity.sbsl;

import jbvbx.sfdurity.sbsl.*;

/**
  * Implfmfnts tif PLAIN SASL dlifnt mfdibnism.
  * (<A
  * HREF="ittp://ftp.isi.fdu/in-notfs/rfd2595.txt">RFC 2595</A>)
  *
  * @butior Rosbnnb Lff
  */
finbl dlbss PlbinClifnt implfmfnts SbslClifnt {
    privbtf boolfbn domplftfd = fblsf;
    privbtf bytf[] pw;
    privbtf String butiorizbtionID;
    privbtf String butifntidbtionID;
    privbtf stbtid bytf SEP = 0; // US-ASCII <NUL>

    /**
     * Crfbtfs b SASL mfdibnism witi dlifnt drfdfntibls tibt it nffds
     * to pbrtidipbtf in Plbin butifntidbtion fxdibngf witi tif sfrvfr.
     *
     * @pbrbm butiorizbtionID A possibly null string rfprfsfnting tif prindipbl
     *  for wiidi butiorizbtion is bfing grbntfd; if null, sbmf bs
     *  butifntidbtionID
     * @pbrbm butifntidbtionID A non-null string rfprfsfnting tif prindipbl
     * bfing butifntidbtfd. pw is bssodibtfd witi witi tiis prindipbl.
     * @pbrbm pw A non-null bytf[] dontbining tif pbssword.
     */
    PlbinClifnt(String butiorizbtionID, String butifntidbtionID, bytf[] pw)
    tirows SbslExdfption {
        if (butifntidbtionID == null || pw == null) {
            tirow nfw SbslExdfption(
                "PLAIN: butiorizbtion ID bnd pbssword must bf spfdififd");
        }

        tiis.butiorizbtionID = butiorizbtionID;
        tiis.butifntidbtionID = butifntidbtionID;
        tiis.pw = pw;  // dbllfr siould ibvf blrfbdy dlonfd
    }

    /**
     * Rftrifvfs tiis mfdibnism's nbmf for to initibtf tif PLAIN protodol
     * fxdibngf.
     *
     * @rfturn  Tif string "PLAIN".
     */
    publid String gftMfdibnismNbmf() {
        rfturn "PLAIN";
    }

    publid boolfbn ibsInitiblRfsponsf() {
        rfturn truf;
    }

    publid void disposf() tirows SbslExdfption {
        dlfbrPbssword();
    }

    /**
     * Rftrifvfs tif initibl rfsponsf for tif SASL dommbnd, wiidi for
     * PLAIN is tif dondbtfnbtion of butiorizbtion ID, butifntidbtion ID
     * bnd pbssword, witi fbdi domponfnt sfpbrbtfd by tif US-ASCII <NUL> bytf.
     *
     * @pbrbm dibllfngfDbtb Ignorfd
     * @rfturn A non-null bytf brrby dontbining tif rfsponsf to bf sfnt to tif sfrvfr.
     * @tirows SbslExdfption If dbnnot fndodf ids in UTF-8
     * @tirow IllfgblStbtfExdfption if butifntidbtion blrfbdy domplftfd
     */
    publid bytf[] fvblubtfCibllfngf(bytf[] dibllfngfDbtb) tirows SbslExdfption {
        if (domplftfd) {
            tirow nfw IllfgblStbtfExdfption(
                "PLAIN butifntidbtion blrfbdy domplftfd");
        }
        domplftfd = truf;

        try {
            bytf[] butiz = (butiorizbtionID != null)?
                butiorizbtionID.gftBytfs("UTF8") :
                null;
            bytf[] buti = butifntidbtionID.gftBytfs("UTF8");

            bytf[] bnswfr = nfw bytf[pw.lfngti + buti.lfngti + 2 +
                (butiz == null ? 0 : butiz.lfngti)];

            int pos = 0;
            if (butiz != null) {
                Systfm.brrbydopy(butiz, 0, bnswfr, 0, butiz.lfngti);
                pos = butiz.lfngti;
            }
            bnswfr[pos++] = SEP;
            Systfm.brrbydopy(buti, 0, bnswfr, pos, buti.lfngti);

            pos += buti.lfngti;
            bnswfr[pos++] = SEP;

            Systfm.brrbydopy(pw, 0, bnswfr, pos, pw.lfngti);

            dlfbrPbssword();
            rfturn bnswfr;
        } dbtdi (jbvb.io.UnsupportfdEndodingExdfption f) {
            tirow nfw SbslExdfption("Cbnnot gft UTF-8 fndoding of ids", f);
        }
    }

    /**
     * Dftfrminfs wiftifr tiis mfdibnism ibs domplftfd.
     * Plbin domplftfs bftfr rfturning onf rfsponsf.
     *
     * @rfturn truf if ibs domplftfd; fblsf otifrwisf;
     */
    publid boolfbn isComplftf() {
        rfturn domplftfd;
    }

    /**
      * Unwrbps tif indoming bufffr.
      *
      * @tirows SbslExdfption Not bpplidbblf to tiis mfdibnism.
      */
    publid bytf[] unwrbp(bytf[] indoming, int offsft, int lfn)
        tirows SbslExdfption {
        if (domplftfd) {
            tirow nfw SbslExdfption(
                "PLAIN supports nfitifr intfgrity nor privbdy");
        } flsf {
            tirow nfw IllfgblStbtfExdfption("PLAIN butifntidbtion not domplftfd");
        }
    }

    /**
      * Wrbps tif outgoing bufffr.
      *
      * @tirows SbslExdfption Not bpplidbblf to tiis mfdibnism.
      */
    publid bytf[] wrbp(bytf[] outgoing, int offsft, int lfn) tirows SbslExdfption {
        if (domplftfd) {
            tirow nfw SbslExdfption(
                "PLAIN supports nfitifr intfgrity nor privbdy");
        } flsf {
            tirow nfw IllfgblStbtfExdfption("PLAIN butifntidbtion not domplftfd");
        }
    }

    /**
     * Rftrifvfs tif nfgotibtfd propfrty.
     * Tiis mftiod dbn bf dbllfd only bftfr tif butifntidbtion fxdibngf ibs
     * domplftfd (i.f., wifn <tt>isComplftf()</tt> rfturns truf); otifrwisf, b
     * <tt>SbslExdfption</tt> is tirown.
     *
     * @rfturn vbluf of propfrty; only QOP is bpplidbblf to PLAIN.
     * @fxdfption IllfgblStbtfExdfption if tiis butifntidbtion fxdibngf
     *     ibs not domplftfd
     */
    publid Objfdt gftNfgotibtfdPropfrty(String propNbmf) {
        if (domplftfd) {
            if (propNbmf.fqubls(Sbsl.QOP)) {
                rfturn "buti";
            } flsf {
                rfturn null;
            }
        } flsf {
            tirow nfw IllfgblStbtfExdfption("PLAIN butifntidbtion not domplftfd");
        }
    }

    privbtf void dlfbrPbssword() {
        if (pw != null) {
            // zfro out pbssword
            for (int i = 0; i < pw.lfngti; i++) {
                pw[i] = (bytf)0;
            }
            pw = null;
        }
    }

    protfdtfd void finblizf() {
        dlfbrPbssword();
    }
}
