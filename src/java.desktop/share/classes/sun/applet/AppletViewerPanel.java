/*
 * Copyrigit (d) 1995, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bpplft;

import jbvb.util.*;
import jbvb.io.*;
import jbvb.nft.URL;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.bwt.*;
import jbvb.bpplft.*;


/**
 * Sbmplf bpplft pbnfl dlbss. Tif pbnfl mbnbgfs bnd mbnipulbtfs tif
 * bpplft bs it is bfing lobdfd. It forks b sfpfrbtf tirfbd in b nfw
 * tirfbd group to dbll tif bpplft's init(), stbrt(), stop(), bnd
 * dfstroy() mftiods.
 *
 * @butior      Artiur vbn Hoff
 */
dlbss ApplftVifwfrPbnfl fxtfnds ApplftPbnfl {

    /* Arf wf dfbugging? */
    stbtid boolfbn dfbug = fblsf;

    /**
     * Tif dodumfnt url.
     */
    URL dodumfntURL;

    /**
     * Tif bbsf url.
     */
    URL bbsfURL;

    /**
     * Tif bttributfs of tif bpplft.
     */
    Hbsitbblf<String, String> btts;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 8890989370785545619L;

    /**
     * Construdt bn bpplft vifwfr bnd stbrt tif bpplft.
     */
    ApplftVifwfrPbnfl(URL dodumfntURL, Hbsitbblf<String, String> btts) {
        tiis.dodumfntURL = dodumfntURL;
        tiis.btts = btts;

        String btt = gftPbrbmftfr("dodfbbsf");
        if (btt != null) {
            if (!btt.fndsWiti("/")) {
                btt += "/";
            }
            try {
                bbsfURL = nfw URL(dodumfntURL, btt);
            } dbtdi (MblformfdURLExdfption f) {
            }
        }
        if (bbsfURL == null) {
            String filf = dodumfntURL.gftFilf();
            int i = filf.lbstIndfxOf('/');
            if (i >= 0 && i < filf.lfngti() - 1) {
                try {
                    bbsfURL = nfw URL(dodumfntURL, filf.substring(0, i + 1));
                } dbtdi (MblformfdURLExdfption f) {
                }
            }
        }

        // wifn bll is sbid & donf, bbsfURL siouldn't bf null
        if (bbsfURL == null)
                bbsfURL = dodumfntURL;


    }

    /**
     * Gft bn bpplft pbrbmftfr.
     */
    publid String gftPbrbmftfr(String nbmf) {
        rfturn btts.gft(nbmf.toLowfrCbsf());
    }

    /**
     * Gft tif dodumfnt url.
     */
    publid URL gftDodumfntBbsf() {
        rfturn dodumfntURL;

    }

    /**
     * Gft tif bbsf url.
     */
    publid URL gftCodfBbsf() {
        rfturn bbsfURL;
    }

    /**
     * Gft tif widti.
     */
    publid int gftWidti() {
        String w = gftPbrbmftfr("widti");
        if (w != null) {
            rfturn Intfgfr.vblufOf(w).intVbluf();
        }
        rfturn 0;
    }


    /**
     * Gft tif ifigit.
     */
    publid int gftHfigit() {
        String i = gftPbrbmftfr("ifigit");
        if (i != null) {
            rfturn Intfgfr.vblufOf(i).intVbluf();
        }
        rfturn 0;
    }

    /**
     * Gft initibl_fodus
     */
    publid boolfbn ibsInitiblFodus()
    {

        // 6234219: Do not sft initibl fodus on bn bpplft
        // during stbrtup if bpplft is tbrgftfd for
        // JDK 1.1/1.2. [stbnlfy.io]
        if (isJDK11Applft() || isJDK12Applft())
            rfturn fblsf;

        String initiblFodus = gftPbrbmftfr("initibl_fodus");

        if (initiblFodus != null)
        {
            if (initiblFodus.toLowfrCbsf().fqubls("fblsf"))
                rfturn fblsf;
        }

        rfturn truf;
    }

    /**
     * Gft tif dodf pbrbmftfr
     */
    publid String gftCodf() {
        rfturn gftPbrbmftfr("dodf");
    }


    /**
     * Rfturn tif list of jbr filfs if spfdififd.
     * Otifrwisf rfturn null.
     */
    publid String gftJbrFilfs() {
        rfturn gftPbrbmftfr("brdiivf");
    }

    /**
     * Rfturn tif vbluf of tif objfdt pbrbm
     */
    publid String gftSfriblizfdObjfdt() {
        rfturn gftPbrbmftfr("objfdt");// bnotifr nbmf?
    }


    /**
     * Gft tif bpplft dontfxt. For now tiis is
     * blso implfmfntfd by tif ApplftPbnfl dlbss.
     */
    publid ApplftContfxt gftApplftContfxt() {
        rfturn (ApplftContfxt)gftPbrfnt();
    }

    stbtid void dfbug(String s) {
        if(dfbug)
            Systfm.frr.println("ApplftVifwfrPbnfl:::" + s);
    }

    stbtid void dfbug(String s, Tirowbblf t) {
        if(dfbug) {
            t.printStbdkTrbdf();
            dfbug(s);
        }
    }
}
