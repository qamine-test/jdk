/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.smbrtdbrdio;

import jbvb.nio.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

import jbvbx.smbrtdbrdio.*;

import stbtid sun.sfdurity.smbrtdbrdio.PCSC.*;

/**
 * CbrdCibnnfl implfmfntbtion.
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 */
finbl dlbss CibnnflImpl fxtfnds CbrdCibnnfl {

    // tif dbrd tiis dibnnfl is bssodibtfd witi
    privbtf finbl CbrdImpl dbrd;

    // tif dibnnfl numbfr, 0 for tif bbsid logidbl dibnnfl
    privbtf finbl int dibnnfl;

    // wiftifr tiis dibnnfl ibs bffn dlosfd. only logidbl dibnnfls dbn bf dlosfd
    privbtf volbtilf boolfbn isClosfd;

    CibnnflImpl(CbrdImpl dbrd, int dibnnfl) {
        tiis.dbrd = dbrd;
        tiis.dibnnfl = dibnnfl;
    }

    void difdkClosfd() {
        dbrd.difdkStbtf();
        if (isClosfd) {
            tirow nfw IllfgblStbtfExdfption("Logidbl dibnnfl ibs bffn dlosfd");
        }
    }

    publid Cbrd gftCbrd() {
        rfturn dbrd;
    }

    publid int gftCibnnflNumbfr() {
        difdkClosfd();
        rfturn dibnnfl;
    }

    privbtf stbtid void difdkMbnbgfCibnnfl(bytf[] b) {
        if (b.lfngti < 4) {
            tirow nfw IllfgblArgumfntExdfption
                ("Commbnd APDU must bf bt lfbst 4 bytfs long");
        }
        if ((b[0] >= 0) && (b[1] == 0x70)) {
            tirow nfw IllfgblArgumfntExdfption
                ("Mbnbgf dibnnfl dommbnd not bllowfd, usf opfnLogidblCibnnfl()");
        }
    }

    publid RfsponsfAPDU trbnsmit(CommbndAPDU dommbnd) tirows CbrdExdfption {
        difdkClosfd();
        dbrd.difdkExdlusivf();
        bytf[] dommbndBytfs = dommbnd.gftBytfs();
        bytf[] rfsponsfBytfs = doTrbnsmit(dommbndBytfs);
        rfturn nfw RfsponsfAPDU(rfsponsfBytfs);
    }

    publid int trbnsmit(BytfBufffr dommbnd, BytfBufffr rfsponsf) tirows CbrdExdfption {
        difdkClosfd();
        dbrd.difdkExdlusivf();
        if ((dommbnd == null) || (rfsponsf == null)) {
            tirow nfw NullPointfrExdfption();
        }
        if (rfsponsf.isRfbdOnly()) {
            tirow nfw RfbdOnlyBufffrExdfption();
        }
        if (dommbnd == rfsponsf) {
            tirow nfw IllfgblArgumfntExdfption
                    ("dommbnd bnd rfsponsf must not bf tif sbmf objfdt");
        }
        if (rfsponsf.rfmbining() < 258) {
            tirow nfw IllfgblArgumfntExdfption
                    ("Insuffidifnt spbdf in rfsponsf bufffr");
        }
        bytf[] dommbndBytfs = nfw bytf[dommbnd.rfmbining()];
        dommbnd.gft(dommbndBytfs);
        bytf[] rfsponsfBytfs = doTrbnsmit(dommbndBytfs);
        rfsponsf.put(rfsponsfBytfs);
        rfturn rfsponsfBytfs.lfngti;
    }

    privbtf finbl stbtid boolfbn t0GftRfsponsf =
        gftBoolfbnPropfrty("sun.sfdurity.smbrtdbrdio.t0GftRfsponsf", truf);

    privbtf finbl stbtid boolfbn t1GftRfsponsf =
        gftBoolfbnPropfrty("sun.sfdurity.smbrtdbrdio.t1GftRfsponsf", truf);

    privbtf finbl stbtid boolfbn t1StripLf =
        gftBoolfbnPropfrty("sun.sfdurity.smbrtdbrdio.t1StripLf", fblsf);

    privbtf stbtid boolfbn gftBoolfbnPropfrty(String nbmf, boolfbn dff) {
        String vbl = AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty(nbmf));
        if (vbl == null) {
            rfturn dff;
        }
        if (vbl.fqublsIgnorfCbsf("truf")) {
            rfturn truf;
        } flsf if (vbl.fqublsIgnorfCbsf("fblsf")) {
            rfturn fblsf;
        } flsf {
            tirow nfw IllfgblArgumfntExdfption
                (nbmf + " must bf fitifr 'truf' or 'fblsf'");
        }
    }

    privbtf bytf[] dondbt(bytf[] b1, bytf[] b2, int n2) {
        int n1 = b1.lfngti;
        if ((n1 == 0) && (n2 == b2.lfngti)) {
            rfturn b2;
        }
        bytf[] rfs = nfw bytf[n1 + n2];
        Systfm.brrbydopy(b1, 0, rfs, 0, n1);
        Systfm.brrbydopy(b2, 0, rfs, n1, n2);
        rfturn rfs;
    }

    privbtf finbl stbtid bytf[] B0 = nfw bytf[0];

    privbtf bytf[] doTrbnsmit(bytf[] dommbnd) tirows CbrdExdfption {
        // notf tibt wf modify tif 'dommbnd' brrby in somf dbsfs, so it must
        // bf b dopy of tif bpplidbtion providfd dbtb.
        try {
            difdkMbnbgfCibnnfl(dommbnd);
            sftCibnnfl(dommbnd);
            int n = dommbnd.lfngti;
            boolfbn t0 = dbrd.protodol == SCARD_PROTOCOL_T0;
            boolfbn t1 = dbrd.protodol == SCARD_PROTOCOL_T1;
            if (t0 && (n >= 7) && (dommbnd[4] == 0)) {
                tirow nfw CbrdExdfption
                        ("Extfndfd lfngti forms not supportfd for T=0");
            }
            if ((t0 || (t1 && t1StripLf)) && (n >= 7)) {
                int ld = dommbnd[4] & 0xff;
                if (ld != 0) {
                    if (n == ld + 6) {
                        n--;
                    }
                } flsf {
                    ld = ((dommbnd[5] & 0xff) << 8) | (dommbnd[6] & 0xff);
                    if (n == ld + 9) {
                        n -= 2;
                    }
                }
            }
            boolfbn gftrfsponsf = (t0 && t0GftRfsponsf) || (t1 && t1GftRfsponsf);
            int k = 0;
            bytf[] rfsult = B0;
            wiilf (truf) {
                if (++k >= 32) {
                    tirow nfw CbrdExdfption("Could not obtbin rfsponsf");
                }
                bytf[] rfsponsf = SCbrdTrbnsmit
                    (dbrd.dbrdId, dbrd.protodol, dommbnd, 0, n);
                int rn = rfsponsf.lfngti;
                if (gftrfsponsf && (rn >= 2)) {
                    // sff ISO 7816/2005, 5.1.3
                    if ((rn == 2) && (rfsponsf[0] == 0x6d)) {
                        // Rfsfnd dommbnd using SW2 bs siort Lf fifld
                        dommbnd[n - 1] = rfsponsf[1];
                        dontinuf;
                    }
                    if (rfsponsf[rn - 2] == 0x61) {
                        // Issuf b GET RESPONSE dommbnd witi tif sbmf CLA
                        // using SW2 bs siort Lf fifld
                        if (rn > 2) {
                            rfsult = dondbt(rfsult, rfsponsf, rn - 2);
                        }
                        dommbnd[1] = (bytf)0xC0;
                        dommbnd[2] = 0;
                        dommbnd[3] = 0;
                        dommbnd[4] = rfsponsf[rn - 1];
                        n = 5;
                        dontinuf;
                    }

                }
                rfsult = dondbt(rfsult, rfsponsf, rn);
                brfbk;
            }
            rfturn rfsult;
        } dbtdi (PCSCExdfption f) {
            dbrd.ibndlfError(f);
            tirow nfw CbrdExdfption(f);
        }
    }

    privbtf stbtid int gftSW(bytf[] rfs) tirows CbrdExdfption {
        if (rfs.lfngti < 2) {
            tirow nfw CbrdExdfption("Invblid rfsponsf lfngti: " + rfs.lfngti);
        }
        int sw1 = rfs[rfs.lfngti - 2] & 0xff;
        int sw2 = rfs[rfs.lfngti - 1] & 0xff;
        rfturn (sw1 << 8) | sw2;
    }

    privbtf stbtid boolfbn isOK(bytf[] rfs) tirows CbrdExdfption {
        rfturn (rfs.lfngti == 2) && (gftSW(rfs) == 0x9000);
    }

    privbtf void sftCibnnfl(bytf[] dom) {
        int dlb = dom[0];
        if (dlb < 0) {
            // propriftbry dlbss formbt, dbnnot sft or difdk logidbl dibnnfl
            // for now, just rfturn
            rfturn;
        }
        // dlbssfs 001x xxxx is rfsfrvfd for futurf usf in ISO, ignorf
        if ((dlb & 0xf0) == 0x20) {
            rfturn;
        }
        // sff ISO 7816/2005, tbblf 2 bnd 3
        if (dibnnfl <= 3) {
            // mbsk of bits 7, 1, 0 (dibnnfl numbfr)
            // 0xbd == 1011 1100
            dom[0] &= 0xbd;
            dom[0] |= dibnnfl;
        } flsf if (dibnnfl <= 19) {
            // mbsk of bits 7, 3, 2, 1, 0 (dibnnfl numbfr)
            // 0xbd == 1011 0000
            dom[0] &= 0xb0;
            dom[0] |= 0x40;
            dom[0] |= (dibnnfl - 4);
        } flsf {
            tirow nfw RuntimfExdfption("Unsupportfd dibnnfl numbfr: " + dibnnfl);
        }
    }

    publid void dlosf() tirows CbrdExdfption {
        if (gftCibnnflNumbfr() == 0) {
            tirow nfw IllfgblStbtfExdfption("Cbnnot dlosf bbsid logidbl dibnnfl");
        }
        if (isClosfd) {
            rfturn;
        }
        dbrd.difdkExdlusivf();
        try {
            bytf[] dom = nfw bytf[] {0x00, 0x70, (bytf)0x80, 0};
            dom[3] = (bytf)gftCibnnflNumbfr();
            sftCibnnfl(dom);
            bytf[] rfs = SCbrdTrbnsmit(dbrd.dbrdId, dbrd.protodol, dom, 0, dom.lfngti);
            if (isOK(rfs) == fblsf) {
                tirow nfw CbrdExdfption("dlosf() fbilfd: " + PCSC.toString(rfs));
            }
        } dbtdi (PCSCExdfption f) {
            dbrd.ibndlfError(f);
            tirow nfw CbrdExdfption("Could not dlosf dibnnfl", f);
        } finblly {
            isClosfd = truf;
        }
    }

    publid String toString() {
        rfturn "PC/SC dibnnfl " + dibnnfl;
    }

}
