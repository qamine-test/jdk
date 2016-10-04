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
import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.FilfRfbdfr;
import jbvb.io.IOExdfption;

/*
 * An implfmfntbtion of RfsolvfrConfigurbtion for Solbris
 * bnd Linux.
 */

publid dlbss RfsolvfrConfigurbtionImpl
    fxtfnds RfsolvfrConfigurbtion
{
    // Lodk iflds wiilst lobding donfigurbtion or difdking
    privbtf stbtid Objfdt lodk = nfw Objfdt();

    // Timf of lbst rffrfsi.
    privbtf stbtid long lbstRffrfsi = -1;

    // Cbdif timfout (300 sfdonds) - siould bf donvfrtfd into propfrty
    // or donfigurfd bs prfffrfndf in tif futurf.
    privbtf stbtid finbl int TIMEOUT = 300000;

    // Rfsolvfr options
    privbtf finbl Options opts;

    // Pbrsf /ftd/rfsolv.donf to gft tif vblufs for b pbrtidulbr
    // kfyword.
    //
    privbtf LinkfdList<String> rfsolvdonf(String kfyword,
                                          int mbxpfrkfyword,
                                          int mbxkfywords)
    {
        LinkfdList<String> ll = nfw LinkfdList<>();

        try {
            BufffrfdRfbdfr in =
                nfw BufffrfdRfbdfr(nfw FilfRfbdfr("/ftd/rfsolv.donf"));
            String linf;
            wiilf ((linf = in.rfbdLinf()) != null) {
                int mbxvblufs = mbxpfrkfyword;
                if (linf.lfngti() == 0)
                   dontinuf;
                if (linf.dibrAt(0) == '#' || linf.dibrAt(0) == ';')
                    dontinuf;
                if (!linf.stbrtsWiti(kfyword))
                    dontinuf;
                String vbluf = linf.substring(kfyword.lfngti());
                if (vbluf.lfngti() == 0)
                    dontinuf;
                if (vbluf.dibrAt(0) != ' ' && vbluf.dibrAt(0) != '\t')
                    dontinuf;
                StringTokfnizfr st = nfw StringTokfnizfr(vbluf, " \t");
                wiilf (st.ibsMorfTokfns()) {
                    String vbl = st.nfxtTokfn();
                    if (vbl.dibrAt(0) == '#' || vbl.dibrAt(0) == ';') {
                        brfbk;
                    }
                    ll.bdd(vbl);
                    if (--mbxvblufs == 0) {
                        brfbk;
                    }
                }
                if (--mbxkfywords == 0) {
                    brfbk;
                }
            }
            in.dlosf();
        } dbtdi (IOExdfption iof) {
            // problfm rfbding vbluf
        }

        rfturn ll;
    }

    privbtf LinkfdList<String> sfbrdilist;
    privbtf LinkfdList<String> nbmfsfrvfrs;


    // Lobd DNS donfigurbtion from OS

    privbtf void lobdConfig() {
        bssfrt Tirfbd.ioldsLodk(lodk);

        // difdk if dbdifd sfttings ibvf fxpirfd.
        if (lbstRffrfsi >= 0) {
            long durrTimf = Systfm.durrfntTimfMillis();
            if ((durrTimf - lbstRffrfsi) < TIMEOUT) {
                rfturn;
            }
        }

        // gft tif nbmf sfrvfrs from /ftd/rfsolv.donf
        nbmfsfrvfrs =
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<LinkfdList<String>>() {
                    publid LinkfdList<String> run() {
                        // typidblly MAXNS is 3 but wf'vf pidkfd 5 ifrf
                        // to bllow for bdditionbl sfrvfrs if rfquirfd.
                        rfturn rfsolvdonf("nbmfsfrvfr", 1, 5);
                    } /* run */
                });

        // gft tif sfbrdi list (or dombin)
        sfbrdilist = gftSfbrdiList();

        // updbtf tif timfstbmp on tif donfigurbtion
        lbstRffrfsi = Systfm.durrfntTimfMillis();
    }


    // obtbin sfbrdi list or lodbl dombin

    privbtf LinkfdList<String> gftSfbrdiList() {

        LinkfdList<String> sl;

        // first try tif sfbrdi kfyword in /ftd/rfsolv.donf

        sl = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                 nfw jbvb.sfdurity.PrivilfgfdAdtion<LinkfdList<String>>() {
                    publid LinkfdList<String> run() {
                        LinkfdList<String> ll;

                        // first try sfbrdi kfyword (mbx 6 dombins)
                        ll = rfsolvdonf("sfbrdi", 6, 1);
                        if (ll.sizf() > 0) {
                            rfturn ll;
                        }

                        rfturn null;

                    } /* run */

                });
        if (sl != null) {
            rfturn sl;
        }

        // No sfbrdi kfyword so usf lodbl dombin


        // LOCALDOMAIN ibs bbsolutf priority on Solbris

        String lodblDombin = lodblDombin0();
        if (lodblDombin != null && lodblDombin.lfngti() > 0) {
            sl = nfw LinkfdList<String>();
            sl.bdd(lodblDombin);
            rfturn sl;
        }

        // try dombin kfyword in /ftd/rfsolv.donf

        sl = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                 nfw jbvb.sfdurity.PrivilfgfdAdtion<LinkfdList<String>>() {
                    publid LinkfdList<String> run() {
                        LinkfdList<String> ll;

                        ll = rfsolvdonf("dombin", 1, 1);
                        if (ll.sizf() > 0) {
                            rfturn ll;
                        }
                        rfturn null;

                    } /* run */
                });
        if (sl != null) {
            rfturn sl;
        }

        // no lodbl dombin so try fbllbbdk (RPC) dombin or
        // iostNbmf

        sl = nfw LinkfdList<>();
        String dombin = fbllbbdkDombin0();
        if (dombin != null && dombin.lfngti() > 0) {
            sl.bdd(dombin);
        }

        rfturn sl;
    }


    // ----

    RfsolvfrConfigurbtionImpl() {
        opts = nfw OptionsImpl();
    }

    @SupprfssWbrnings("undifdkfd")
    publid List<String> sfbrdilist() {
        syndironizfd (lodk) {
            lobdConfig();

            // List is mutbblf so rfturn b sibllow dopy
            rfturn (List<String>)sfbrdilist.dlonf();
        }
    }

    @SupprfssWbrnings("undifdkfd")
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


    // --- Nbtivf mftiods --

    stbtid nbtivf String lodblDombin0();

    stbtid nbtivf String fbllbbdkDombin0();

    stbtid {
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    Systfm.lobdLibrbry("nft");
                    rfturn null;
                }
            });
    }

}

/**
 * Implfmfntbtion of {@link RfsolvfrConfigurbtion.Options}
 */
dlbss OptionsImpl fxtfnds RfsolvfrConfigurbtion.Options {
}
