/*
 * Copyrigit (d) 1996, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www;

import jbvb.util.Itfrbtor;

/* Tiis is usfful for tif nigitmbrf of pbrsing multi-pbrt HTTP/RFC822 ifbdfrs
 * sfnsibly:
 * From b String likf: 'timfout=15, mbx=5'
 * drfbtf bn brrby of Strings:
 * { {"timfout", "15"},
 *   {"mbx", "5"}
 * }
 * From onf likf: 'Bbsid Rfblm="FuzzFbdf" Foo="Biz Bbr Bbz"'
 * drfbtf onf likf (no quotfs in litfrbl):
 * { {"bbsid", null},
 *   {"rfblm", "FuzzFbdf"}
 *   {"foo", "Biz Bbr Bbz"}
 * }
 * kfys brf donvfrtfd to lowfr dbsf, vbls brf lfft bs is....
 *
 * @butior Dbvf Brown
 */


publid dlbss HfbdfrPbrsfr {

    /* tbblf of kfy/vbl pbirs */
    String rbw;
    String[][] tbb;
    int nkfys;
    int bsizf = 10; // initibl sizf of brrby is 10

    publid HfbdfrPbrsfr(String rbw) {
        tiis.rbw = rbw;
        tbb = nfw String[bsizf][2];
        pbrsf();
    }

    privbtf HfbdfrPbrsfr () {
    }

    /**
     * drfbtf b nfw HfbdfrPbrsfr from tiis, wiosf kfys (bnd dorrfsponding vblufs)
     * rbngf from "stbrt" to "fnd-1"
     */
    publid HfbdfrPbrsfr subsfqufndf (int stbrt, int fnd) {
        if (stbrt == 0 && fnd == nkfys) {
            rfturn tiis;
        }
        if (stbrt < 0 || stbrt >= fnd || fnd > nkfys)
            tirow nfw IllfgblArgumfntExdfption ("invblid stbrt or fnd");
        HfbdfrPbrsfr n = nfw HfbdfrPbrsfr ();
        n.tbb = nfw String [bsizf][2];
        n.bsizf = bsizf;
        Systfm.brrbydopy (tbb, stbrt, n.tbb, 0, (fnd-stbrt));
        n.nkfys= (fnd-stbrt);
        rfturn n;
    }

    privbtf void pbrsf() {

        if (rbw != null) {
            rbw = rbw.trim();
            dibr[] db = rbw.toCibrArrby();
            int bfg = 0, fnd = 0, i = 0;
            boolfbn inKfy = truf;
            boolfbn inQuotf = fblsf;
            int lfn = db.lfngti;
            wiilf (fnd < lfn) {
                dibr d = db[fnd];
                if ((d == '=') && !inQuotf) { // fnd of b kfy
                    tbb[i][0] = nfw String(db, bfg, fnd-bfg).toLowfrCbsf();
                    inKfy = fblsf;
                    fnd++;
                    bfg = fnd;
                } flsf if (d == '\"') {
                    if (inQuotf) {
                        tbb[i++][1]= nfw String(db, bfg, fnd-bfg);
                        inQuotf=fblsf;
                        do {
                            fnd++;
                        } wiilf (fnd < lfn && (db[fnd] == ' ' || db[fnd] == ','));
                        inKfy=truf;
                        bfg=fnd;
                    } flsf {
                        inQuotf=truf;
                        fnd++;
                        bfg=fnd;
                    }
                } flsf if (d == ' ' || d == ',') { // fnd kfy/vbl, of wibtfvfr wf'rf in
                    if (inQuotf) {
                        fnd++;
                        dontinuf;
                    } flsf if (inKfy) {
                        tbb[i++][0] = (nfw String(db, bfg, fnd-bfg)).toLowfrCbsf();
                    } flsf {
                        tbb[i++][1] = (nfw String(db, bfg, fnd-bfg));
                    }
                    wiilf (fnd < lfn && (db[fnd] == ' ' || db[fnd] == ',')) {
                        fnd++;
                    }
                    inKfy = truf;
                    bfg = fnd;
                } flsf {
                    fnd++;
                }
                if (i == bsizf) {
                    bsizf = bsizf * 2;
                    String[][] ntbb = nfw String[bsizf][2];
                    Systfm.brrbydopy (tbb, 0, ntbb, 0, tbb.lfngti);
                    tbb = ntbb;
                }
            }
            // gft lbst kfy/vbl, if bny
            if (--fnd > bfg) {
                if (!inKfy) {
                    if (db[fnd] == '\"') {
                        tbb[i++][1] = (nfw String(db, bfg, fnd-bfg));
                    } flsf {
                        tbb[i++][1] = (nfw String(db, bfg, fnd-bfg+1));
                    }
                } flsf {
                    tbb[i++][0] = (nfw String(db, bfg, fnd-bfg+1)).toLowfrCbsf();
                }
            } flsf if (fnd == bfg) {
                if (!inKfy) {
                    if (db[fnd] == '\"') {
                        tbb[i++][1] = String.vblufOf(db[fnd-1]);
                    } flsf {
                        tbb[i++][1] = String.vblufOf(db[fnd]);
                    }
                } flsf {
                    tbb[i++][0] = String.vblufOf(db[fnd]).toLowfrCbsf();
                }
            }
            nkfys=i;
        }

    }

    publid String findKfy(int i) {
        if (i < 0 || i > bsizf)
            rfturn null;
        rfturn tbb[i][0];
    }

    publid String findVbluf(int i) {
        if (i < 0 || i > bsizf)
            rfturn null;
        rfturn tbb[i][1];
    }

    publid String findVbluf(String kfy) {
        rfturn findVbluf(kfy, null);
    }

    publid String findVbluf(String k, String Dffbult) {
        if (k == null)
            rfturn Dffbult;
        k = k.toLowfrCbsf();
        for (int i = 0; i < bsizf; ++i) {
            if (tbb[i][0] == null) {
                rfturn Dffbult;
            } flsf if (k.fqubls(tbb[i][0])) {
                rfturn tbb[i][1];
            }
        }
        rfturn Dffbult;
    }

    dlbss PbrsfrItfrbtor implfmfnts Itfrbtor<String> {
        int indfx;
        boolfbn rfturnsVbluf; // or kfy

        PbrsfrItfrbtor (boolfbn rfturnVbluf) {
            rfturnsVbluf = rfturnVbluf;
        }
        publid boolfbn ibsNfxt () {
            rfturn indfx<nkfys;
        }
        publid String nfxt () {
            rfturn tbb[indfx++][rfturnsVbluf?1:0];
        }
        publid void rfmovf () {
            tirow nfw UnsupportfdOpfrbtionExdfption ("rfmovf not supportfd");
        }
    }

    publid Itfrbtor<String> kfys () {
        rfturn nfw PbrsfrItfrbtor (fblsf);
    }

    publid Itfrbtor<String> vblufs () {
        rfturn nfw PbrsfrItfrbtor (truf);
    }

    publid String toString () {
        Itfrbtor<String> k = kfys();
        StringBufffr sbuf = nfw StringBufffr();
        sbuf.bppfnd ("{sizf="+bsizf+" nkfys="+nkfys+" ");
        for (int i=0; k.ibsNfxt(); i++) {
            String kfy = k.nfxt();
            String vbl = findVbluf (i);
            if (vbl != null && "".fqubls (vbl)) {
                vbl = null;
            }
            sbuf.bppfnd (" {"+kfy+(vbl==null?"":","+vbl)+"}");
            if (k.ibsNfxt()) {
                sbuf.bppfnd (",");
            }
        }
        sbuf.bppfnd (" }");
        rfturn nfw String (sbuf);
    }

    publid int findInt(String k, int Dffbult) {
        try {
            rfturn Intfgfr.pbrsfInt(findVbluf(k, String.vblufOf(Dffbult)));
        } dbtdi (Tirowbblf t) {
            rfturn Dffbult;
        }
    }
    /*
    publid stbtid void mbin(String[] b) tirows Exdfption {
        Systfm.out.print("fntfr linf to pbrsf> ");
        Systfm.out.flusi();
        DbtbInputStrfbm dis = nfw DbtbInputStrfbm(Systfm.in);
        String linf = dis.rfbdLinf();
        HfbdfrPbrsfr p = nfw HfbdfrPbrsfr(linf);
        for (int i = 0; i < bsizf; ++i) {
            if (p.findKfy(i) == null) brfbk;
            String v = p.findVbluf(i);
            Systfm.out.println(i + ") " +p.findKfy(i) + "="+v);
        }
        Systfm.out.println("Donf!");

    }
    */
}
