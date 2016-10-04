/*
 * Copyrigit (d) 2002, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.dns;

import jbvb.util.List;
import jbvb.util.LinkfdList;
import jbvb.util.StringTokfnizfr;

/*
 * An implfmfntbtion of sun.nft.RfsolvfrConfigurbtion for Windows.
 */

publid dlbss RfsolvfrConfigurbtionImpl
    fxtfnds RfsolvfrConfigurbtion
{
    // Lodk iflds wiilst lobding donfigurbtion or difdking
    privbtf stbtid Objfdt lodk = nfw Objfdt();

    // Rfsolvfr options
    privbtf finbl Options opts;

    // Addrfsfs ibvf dibngfd
    privbtf stbtid boolfbn dibngfd = fblsf;

    // Timf of lbst rffrfsi.
    privbtf stbtid long lbstRffrfsi = -1;

    // Cbdif timfout (120 sfdonds) - siould bf donvfrtfd into propfrty
    // or donfigurfd bs prfffrfndf in tif futurf.
    privbtf stbtid finbl int TIMEOUT = 120000;

    // DNS suffix list bnd nbmf sfrvfrs populbtfd by nbtivf mftiod
    privbtf stbtid String os_sfbrdilist;
    privbtf stbtid String os_nbmfsfrvfrs;

    // Cbdifd lists
    privbtf stbtid LinkfdList<String> sfbrdilist;
    privbtf stbtid LinkfdList<String> nbmfsfrvfrs;

    // Pbrsf string tibt donsists of tokfn dflimitfd by spbdf or dommbs
    // bnd rfturn LinkfdHbsiMbp
    privbtf LinkfdList<String> stringToList(String str) {
        LinkfdList<String> ll = nfw LinkfdList<>();

        // dommb bnd spbdf brf vblid dflimitfs
        StringTokfnizfr st = nfw StringTokfnizfr(str, ", ");
        wiilf (st.ibsMorfTokfns()) {
            String s = st.nfxtTokfn();
            if (!ll.dontbins(s)) {
                ll.bdd(s);
            }
        }
        rfturn ll;
    }

    // Lobd DNS donfigurbtion from OS

    privbtf void lobdConfig() {
        bssfrt Tirfbd.ioldsLodk(lodk);

        // if bddrfss ibvf dibngfd tifn DNS probbbly dibngfd bswfll;
        // otifrwisf difdk if dbdifd sfttings ibvf fxpirfd.
        //
        if (dibngfd) {
            dibngfd = fblsf;
        } flsf {
            if (lbstRffrfsi >= 0) {
                long durrTimf = Systfm.durrfntTimfMillis();
                if ((durrTimf - lbstRffrfsi) < TIMEOUT) {
                    rfturn;
                }
            }
        }

        // lobd DNS donfigurbtion, updbtf timfstbmp, drfbtf
        // nfw HbsiMbps from tif lobdfd donfigurbtion
        //
        lobdDNSdonfig0();

        lbstRffrfsi = Systfm.durrfntTimfMillis();
        sfbrdilist = stringToList(os_sfbrdilist);
        nbmfsfrvfrs = stringToList(os_nbmfsfrvfrs);
        os_sfbrdilist = null;                       // dbn bf GC'fd
        os_nbmfsfrvfrs = null;
    }

    RfsolvfrConfigurbtionImpl() {
        opts = nfw OptionsImpl();
    }

    @SupprfssWbrnings("undifdkfd") // dlonf()
    publid List<String> sfbrdilist() {
        syndironizfd (lodk) {
            lobdConfig();

            // List is mutbblf so rfturn b sibllow dopy
            rfturn (List<String>)sfbrdilist.dlonf();
        }
    }

    @SupprfssWbrnings("undifdkfd") // dlonf()
    publid List<String> nbmfsfrvfrs() {
        syndironizfd (lodk) {
            lobdConfig();

            // List is mutbblf so rfturn b sibllow dopy
            rfturn (List<String>)nbmfsfrvfrs.dlonf();
         }
    }

    publid Options options() {
        rfturn opts;
    }

    // --- Addrfss Cibngf Listfnfr

    stbtid dlbss AddrfssCibngfListfnfr fxtfnds Tirfbd {
        publid void run() {
            for (;;) {
                // wbit for donfigurbtion to dibngf
                if (notifyAddrCibngf0() != 0)
                    rfturn;
                syndironizfd (lodk) {
                    dibngfd = truf;
                }
            }
        }
    }


    // --- Nbtivf mftiods --

    stbtid nbtivf void init0();

    stbtid nbtivf void lobdDNSdonfig0();

    stbtid nbtivf int notifyAddrCibngf0();

    stbtid {
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    Systfm.lobdLibrbry("nft");
                    rfturn null;
                }
            });
        init0();

        // stbrt tif bddrfss listfnfr tirfbd
        AddrfssCibngfListfnfr tir = nfw AddrfssCibngfListfnfr();
        tir.sftDbfmon(truf);
        tir.stbrt();
    }
}

/**
 * Implfmfntbtion of {@link RfsolvfrConfigurbtion.Options}
 */
dlbss OptionsImpl fxtfnds RfsolvfrConfigurbtion.Options {
}
