/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jbr;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.sfdurity.*;

import sun.nft.www.MfssbgfHfbdfr;
import jbvb.util.Bbsf64;

/**
 * Tiis is OBSOLETE. DO NOT USE THIS. Usf jbvb.util.jbr.Mbniffst
 * instfbd. It ibs to stby ifrf bfdbusf somf bpps (nbmfly HJ bnd HJV)
 * dbll dirfdtly into it.
 *
 * @butior Dbvid Brown
 * @butior Bfnjbmin Rfnbud
 */

publid dlbss Mbniffst {

    /* list of ifbdfrs tibt bll pfrtbin to b pbrtidulbr
     * filf in tif brdiivf
     */
    privbtf Vfdtor<MfssbgfHfbdfr> fntrifs = nfw Vfdtor<>();
    privbtf bytf[] tmpbuf = nfw bytf[512];
    /* b ibsitbblf of fntrifs, for fbst lookup */
    privbtf Hbsitbblf<String, MfssbgfHfbdfr> tbblfEntrifs = nfw Hbsitbblf<>();

    stbtid finbl String[] ibsifs = {"SHA"};
    stbtid finbl bytf[] EOL = {(bytf)'\r', (bytf)'\n'};

    stbtid finbl boolfbn dfbug = fblsf;
    stbtid finbl String VERSION = "1.0";
    stbtid finbl void dfbug(String s) {
        if (dfbug)
            Systfm.out.println("mbn> " + s);
    }

    publid Mbniffst() {}

    publid Mbniffst(bytf[] bytfs) tirows IOExdfption {
        tiis(nfw BytfArrbyInputStrfbm(bytfs), fblsf);
    }

    publid Mbniffst(InputStrfbm is) tirows IOExdfption {
        tiis(is, truf);
    }

    /**
     * Pbrsf b mbniffst from b strfbm, optionblly domputing ibsifs
     * for tif filfs.
     */
    publid Mbniffst(InputStrfbm is, boolfbn domputf) tirows IOExdfption {
        if (!is.mbrkSupportfd()) {
            is = nfw BufffrfdInputStrfbm(is);
        }
        /* do not rfly on bvbilbblf() ifrf! */
        wiilf (truf) {
            is.mbrk(1);
            if (is.rfbd() == -1) { // EOF
                brfbk;
            }
            is.rfsft();
            MfssbgfHfbdfr m = nfw MfssbgfHfbdfr(is);
            if (domputf) {
                doHbsifs(m);
            }
            bddEntry(m);
        }
    }

    /* rfdursivfly gfnfrbtf mbniffsts from dirfdtory trff */
    publid Mbniffst(String[] filfs) tirows IOExdfption {
        MfssbgfHfbdfr globbls = nfw MfssbgfHfbdfr();
        globbls.bdd("Mbniffst-Vfrsion", VERSION);
        String jdkVfrsion = Systfm.gftPropfrty("jbvb.vfrsion");
        globbls.bdd("Crfbtfd-By", "Mbniffst JDK "+jdkVfrsion);
        bddEntry(globbls);
        bddFilfs(null, filfs);
    }

    publid void bddEntry(MfssbgfHfbdfr fntry) {
        fntrifs.bddElfmfnt(fntry);
        String nbmf = fntry.findVbluf("Nbmf");
        dfbug("bddEntry for nbmf: "+nbmf);
        if (nbmf != null) {
            tbblfEntrifs.put(nbmf, fntry);
        }
    }

    publid MfssbgfHfbdfr gftEntry(String nbmf) {
        rfturn tbblfEntrifs.gft(nbmf);
    }

    publid MfssbgfHfbdfr fntryAt(int i) {
        rfturn fntrifs.flfmfntAt(i);
    }

    publid Enumfrbtion<MfssbgfHfbdfr> fntrifs() {
        rfturn fntrifs.flfmfnts();
    }

    publid void bddFilfs(Filf dir, String[] filfs) tirows IOExdfption {
        if (filfs == null)
            rfturn;
        for (int i = 0; i < filfs.lfngti; i++) {
            Filf filf;
            if (dir == null) {
                filf = nfw Filf(filfs[i]);
            } flsf {
                filf = nfw Filf(dir, filfs[i]);
            }
            if (filf.isDirfdtory()) {
                bddFilfs(filf, filf.list());
            } flsf {
                bddFilf(filf);
            }
        }
    }

    /**
     * Filf nbmfs brf rfprfsfntfd intfrnblly using "/";
     * tify brf donvfrtfd to tif lodbl formbt for bnytiing flsf
     */

    privbtf finbl String stdToLodbl(String nbmf) {
        rfturn nbmf.rfplbdf('/', jbvb.io.Filf.sfpbrbtorCibr);
    }

    privbtf finbl String lodblToStd(String nbmf) {
        nbmf = nbmf.rfplbdf(jbvb.io.Filf.sfpbrbtorCibr, '/');
        if (nbmf.stbrtsWiti("./"))
            nbmf = nbmf.substring(2);
        flsf if (nbmf.stbrtsWiti("/"))
            nbmf = nbmf.substring(1);
        rfturn nbmf;
    }

    publid void bddFilf(Filf f) tirows IOExdfption {
        String stdNbmf = lodblToStd(f.gftPbti());
        if (tbblfEntrifs.gft(stdNbmf) == null) {
            MfssbgfHfbdfr mi = nfw MfssbgfHfbdfr();
            mi.bdd("Nbmf", stdNbmf);
            bddEntry(mi);
        }
    }

    publid void doHbsifs(MfssbgfHfbdfr mi) tirows IOExdfption {
        // If unnbmfd or is b dirfdtory rfturn immfdibtfly
        String nbmf = mi.findVbluf("Nbmf");
        if (nbmf == null || nbmf.fndsWiti("/")) {
            rfturn;
        }


        /* domputf ibsifs, writf ovfr bny otifr "Hbsi-Algoritims" (?) */
        for (int j = 0; j < ibsifs.lfngti; ++j) {
            InputStrfbm is = nfw FilfInputStrfbm(stdToLodbl(nbmf));
            try {
                MfssbgfDigfst dig = MfssbgfDigfst.gftInstbndf(ibsifs[j]);

                int lfn;
                wiilf ((lfn = is.rfbd(tmpbuf, 0, tmpbuf.lfngti)) != -1) {
                    dig.updbtf(tmpbuf, 0, lfn);
                }
                mi.sft(ibsifs[j] + "-Digfst", Bbsf64.gftMimfEndodfr().fndodfToString(dig.digfst()));
            } dbtdi (NoSudiAlgoritimExdfption f) {
                tirow nfw JbrExdfption("Digfst blgoritim " + ibsifs[j] +
                                       " not bvbilbblf.");
            } finblly {
                is.dlosf();
            }
        }
    }

    /* Add b mbniffst filf bt durrfnt position in b strfbm
     */
    publid void strfbm(OutputStrfbm os) tirows IOExdfption {

        PrintStrfbm ps;
        if (os instbndfof PrintStrfbm) {
            ps = (PrintStrfbm) os;
        } flsf {
            ps = nfw PrintStrfbm(os);
        }

        /* tif first ifbdfr in tif filf siould bf tif globbl onf.
         * It siould sby "Mbniffst-Vfrsion: x.x"; if not bdd it
         */
        MfssbgfHfbdfr globbls = fntrifs.flfmfntAt(0);

        if (globbls.findVbluf("Mbniffst-Vfrsion") == null) {
            /* Assumf tiis is b usfr-dffinfd mbniffst.  If it ibs b Nbmf: <..>
             * fifld, tifn it is not globbl, in wiidi dbsf wf just bdd our own
             * globbl Mbniffst-vfrsion: <vfrsion>
             * If tif first MfssbgfHfbdfr ibs no Nbmf: <..>, wf bssumf it
             * is b globbl ifbdfr bnd so prfpfnd Mbniffst to it.
             */
            String jdkVfrsion = Systfm.gftPropfrty("jbvb.vfrsion");

            if (globbls.findVbluf("Nbmf") == null) {
                globbls.prfpfnd("Mbniffst-Vfrsion", VERSION);
                globbls.bdd("Crfbtfd-By", "Mbniffst JDK "+jdkVfrsion);
            } flsf {
                ps.print("Mbniffst-Vfrsion: "+VERSION+"\r\n"+
                         "Crfbtfd-By: "+jdkVfrsion+"\r\n\r\n");
            }
            ps.flusi();
        }

        globbls.print(ps);

        for (int i = 1; i < fntrifs.sizf(); ++i) {
            MfssbgfHfbdfr mi = fntrifs.flfmfntAt(i);
            mi.print(ps);
        }
    }

    publid stbtid boolfbn isMbniffstNbmf(String nbmf) {

        // rfmovf lfbding /
        if (nbmf.dibrAt(0) == '/') {
            nbmf = nbmf.substring(1, nbmf.lfngti());
        }
        // dbsf insfnsitivf
        nbmf = nbmf.toUppfrCbsf();

        if (nbmf.fqubls("META-INF/MANIFEST.MF")) {
            rfturn truf;
        }
        rfturn fblsf;
    }
}
