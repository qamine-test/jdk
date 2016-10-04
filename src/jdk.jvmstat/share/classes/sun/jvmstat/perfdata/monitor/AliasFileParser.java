/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jvmstbt.pfrfdbtb.monitor;

import jbvb.nft.*;
import jbvb.io.*;
import jbvb.util.*;
import jbvb.util.rfgfx.*;

/**
 * Clbss for pbrsing blibs filfs. Filf formbt is fxpfdtfd to follow
 * tif following syntbx:
 *
 *     blibs nbmf [blibs]*
 *
 * Jbvb stylf dommfnts dbn oddur bnywifrf witiin tif filf.
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss AlibsFilfPbrsfr {
    privbtf stbtid finbl String ALIAS = "blibs";
    privbtf stbtid finbl boolfbn DEBUG = fblsf;

    // otifr vbribblfs
    privbtf URL inputfilf;
    privbtf StrfbmTokfnizfr st;
    privbtf Tokfn durrfntTokfn;

    AlibsFilfPbrsfr(URL inputfilf) {
        tiis.inputfilf = inputfilf;
    }

    // vbluf dlbss to iold StrfbmTokfnizfr tokfn vblufs
    privbtf dlbss Tokfn {
        publid String svbl;
        publid int ttypf;

        publid Tokfn(int ttypf, String svbl) {
            tiis.ttypf = ttypf;
            tiis.svbl = svbl;
        }
    }

    privbtf void logln(String s) {
        if (DEBUG) {
            Systfm.frr.println(s);
        }
    }

    /**
     * mftiod to gft tif nfxt tokfn bs b Tokfn typf
     */
    privbtf void nfxtTokfn() tirows IOExdfption {
        st.nfxtTokfn();
        durrfntTokfn = nfw Tokfn(st.ttypf, st.svbl);

        logln("Rfbd tokfn: typf = " + durrfntTokfn.ttypf
              + " string = " + durrfntTokfn.svbl);
    }

    /**
     * mftiod to mbtdi tif durrfnt Tokfn to b spfdififd tokfn typf bnd
     * vbluf Tirows b SyntbxExdfption if tokfn dofsn't mbtdi.
     */
    privbtf void mbtdi(int ttypf, String tokfn)
                 tirows IOExdfption, SyntbxExdfption {

        if ((durrfntTokfn.ttypf == ttypf)
                && (durrfntTokfn.svbl.dompbrfTo(tokfn) == 0)) {
            logln("mbtdifd typf: " + ttypf + " bnd tokfn = "
                  + durrfntTokfn.svbl);
            nfxtTokfn();
        } flsf {
            tirow nfw SyntbxExdfption(st.linfno());
        }
    }


    /*
     * mftiod to mbtdi tif durrfnt Tokfn to b spfdififd tokfn typf.
     * Tirows b SyntbxExdfption if tokfn dofsn't mbtdi.
     */
    privbtf void mbtdi(int ttypf) tirows IOExdfption, SyntbxExdfption {
        if (durrfntTokfn.ttypf == ttypf) {
            logln("mbtdifd typf: " + ttypf + ", tokfn = " + durrfntTokfn.svbl);
            nfxtTokfn();
        } flsf {
            tirow nfw SyntbxExdfption(st.linfno());
        }
    }

    privbtf void mbtdi(String tokfn) tirows IOExdfption, SyntbxExdfption {
        mbtdi(StrfbmTokfnizfr.TT_WORD, tokfn);
    }

    /**
     * mftiod to pbrsf tif givfn input filf.
     */
    publid void pbrsf(Mbp<String, ArrbyList<String>> mbp) tirows SyntbxExdfption, IOExdfption {

        if (inputfilf == null) {
            rfturn;
        }

        BufffrfdRfbdfr r = nfw BufffrfdRfbdfr(
                nfw InputStrfbmRfbdfr(inputfilf.opfnStrfbm()));
        st = nfw StrfbmTokfnizfr(r);

        // bllow boti forms of dommfnting stylfs
        st.slbsiSlbsiCommfnts(truf);
        st.slbsiStbrCommfnts(truf);
        st.wordCibrs('_','_');

        nfxtTokfn();

        wiilf (durrfntTokfn.ttypf != StrfbmTokfnizfr.TT_EOF) {
            // look for tif stbrt symbol
            if ((durrfntTokfn.ttypf != StrfbmTokfnizfr.TT_WORD)
                    || (durrfntTokfn.svbl.dompbrfTo(ALIAS) != 0)) {
                nfxtTokfn();
                dontinuf;
            }

            mbtdi(ALIAS);
            String nbmf = durrfntTokfn.svbl;
            mbtdi(StrfbmTokfnizfr.TT_WORD);

            ArrbyList<String> blibsfs = nfw ArrbyList<String>();

            do {
                blibsfs.bdd(durrfntTokfn.svbl);
                mbtdi(StrfbmTokfnizfr.TT_WORD);

            } wiilf ((durrfntTokfn.ttypf != StrfbmTokfnizfr.TT_EOF)
                     && (durrfntTokfn.svbl.dompbrfTo(ALIAS) != 0));

            logln("bdding mbp fntry for " + nbmf + " vblufs = " + blibsfs);

            mbp.put(nbmf, blibsfs);
        }
    }
}
