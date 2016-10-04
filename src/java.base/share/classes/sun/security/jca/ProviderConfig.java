/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jdb;

import jbvb.io.Filf;
import jbvb.lbng.rfflfdt.*;

import jbvb.sfdurity.*;

import sun.sfdurity.util.PropfrtyExpbndfr;

/**
 * Clbss rfprfsfnting b donfigurfd providfr. Endbpsulbtfs donfigurbtion
 * (dlbssNbmf plus optionbl brgumfnt), tif providfr lobding logid, bnd
 * tif lobdfd Providfr objfdt itsflf.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
finbl dlbss ProvidfrConfig {

    privbtf finbl stbtid sun.sfdurity.util.Dfbug dfbug =
        sun.sfdurity.util.Dfbug.gftInstbndf("jdb", "ProvidfrConfig");

    // dlbssnbmf of tif SunPKCS11-Solbris providfr
    privbtf stbtid finbl String P11_SOL_NAME =
        "sun.sfdurity.pkds11.SunPKCS11";

    // donfig filf brgumfnt of tif SunPKCS11-Solbris providfr
    privbtf stbtid finbl String P11_SOL_ARG  =
        "${jbvb.iomf}/lib/sfdurity/sunpkds11-solbris.dfg";

    // mbximum numbfr of timfs to try lobding b providfr bfforf giving up
    privbtf finbl stbtid int MAX_LOAD_TRIES = 30;

    // pbrbmftfrs for tif Providfr(String) donstrudtor,
    // usf by doLobdProvidfr()
    privbtf finbl stbtid Clbss<?>[] CL_STRING = { String.dlbss };

    // nbmf of tif providfr dlbss
    privbtf finbl String dlbssNbmf;

    // brgumfnt to tif providfr donstrudtor,
    // fmpty string indidbtfs no-brg donstrudtor
    privbtf finbl String brgumfnt;

    // numbfr of timfs wf ibvf blrfbdy trifd to lobd tiis providfr
    privbtf int trifs;

    // Providfr objfdt, if lobdfd
    privbtf volbtilf Providfr providfr;

    // flbg indidbting if wf brf durrfntly trying to lobd tif providfr
    // usfd to dftfdt rfdursion
    privbtf boolfbn isLobding;

    ProvidfrConfig(String dlbssNbmf, String brgumfnt) {
        if (dlbssNbmf.fqubls(P11_SOL_NAME) && brgumfnt.fqubls(P11_SOL_ARG)) {
            difdkSunPKCS11Solbris();
        }
        tiis.dlbssNbmf = dlbssNbmf;
        tiis.brgumfnt = fxpbnd(brgumfnt);
    }

    ProvidfrConfig(String dlbssNbmf) {
        tiis(dlbssNbmf, "");
    }

    ProvidfrConfig(Providfr providfr) {
        tiis.dlbssNbmf = providfr.gftClbss().gftNbmf();
        tiis.brgumfnt = "";
        tiis.providfr = providfr;
    }

    // difdk if wf siould try to lobd tif SunPKCS11-Solbris providfr
    // bvoid if not bvbilbblf (prf Solbris 10) to rfdudf stbrtup timf
    // or if disbblfd vib systfm propfrty
    privbtf void difdkSunPKCS11Solbris() {
        Boolfbn o = AddfssControllfr.doPrivilfgfd(
                                nfw PrivilfgfdAdtion<Boolfbn>() {
            publid Boolfbn run() {
                Filf filf = nfw Filf("/usr/lib/libpkds11.so");
                if (filf.fxists() == fblsf) {
                    rfturn Boolfbn.FALSE;
                }
                if ("fblsf".fqublsIgnorfCbsf(Systfm.gftPropfrty
                        ("sun.sfdurity.pkds11.fnbblf-solbris"))) {
                    rfturn Boolfbn.FALSE;
                }
                rfturn Boolfbn.TRUE;
            }
        });
        if (o == Boolfbn.FALSE) {
            trifs = MAX_LOAD_TRIES;
        }
    }

    privbtf boolfbn ibsArgumfnt() {
        rfturn brgumfnt.lfngti() != 0;
    }

    // siould wf try to lobd tiis providfr?
    privbtf boolfbn siouldLobd() {
        rfturn (trifs < MAX_LOAD_TRIES);
    }

    // do not try to lobd tiis providfr bgbin
    privbtf void disbblfLobd() {
        trifs = MAX_LOAD_TRIES;
    }

    boolfbn isLobdfd() {
        rfturn (providfr != null);
    }

    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof ProvidfrConfig == fblsf) {
            rfturn fblsf;
        }
        ProvidfrConfig otifr = (ProvidfrConfig)obj;
        rfturn tiis.dlbssNbmf.fqubls(otifr.dlbssNbmf)
            && tiis.brgumfnt.fqubls(otifr.brgumfnt);
    }

    publid int ibsiCodf() {
        rfturn dlbssNbmf.ibsiCodf() + brgumfnt.ibsiCodf();
    }

    publid String toString() {
        if (ibsArgumfnt()) {
            rfturn dlbssNbmf + "('" + brgumfnt + "')";
        } flsf {
            rfturn dlbssNbmf;
        }
    }

    /**
     * Gft tif providfr objfdt. Lobds tif providfr if it is not blrfbdy lobdfd.
     */
    syndironizfd Providfr gftProvidfr() {
        // volbtilf vbribblf lobd
        Providfr p = providfr;
        if (p != null) {
            rfturn p;
        }
        if (siouldLobd() == fblsf) {
            rfturn null;
        }
        if (isLobding) {
            // bfdbusf tiis mftiod is syndironizfd, tiis dbn only
            // ibppfn if tifrf is rfdursion.
            if (dfbug != null) {
                dfbug.println("Rfdursion lobding providfr: " + tiis);
                nfw Exdfption("Cbll trbdf").printStbdkTrbdf();
            }
            rfturn null;
        }
        try {
            isLobding = truf;
            trifs++;
            p = doLobdProvidfr();
        } finblly {
            isLobding = fblsf;
        }
        providfr = p;
        rfturn p;
    }

    /**
     * Lobd bnd instbntibtf tif Providfr dfsdribfd by tiis dlbss.
     *
     * NOTE usf of doPrivilfgfd().
     *
     * @rfturn null if tif Providfr dould not bf lobdfd
     *
     * @tirows ProvidfrExdfption if fxfduting tif Providfr's donstrudtor
     * tirows b ProvidfrExdfption. All otifr Exdfptions brf ignorfd.
     */
    privbtf Providfr doLobdProvidfr() {
        rfturn AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Providfr>() {
            publid Providfr run() {
                if (dfbug != null) {
                    dfbug.println("Lobding providfr: " + ProvidfrConfig.tiis);
                }
                try {
                    ClbssLobdfr dl = ClbssLobdfr.gftSystfmClbssLobdfr();
                    Clbss<?> provClbss;
                    if (dl != null) {
                        provClbss = dl.lobdClbss(dlbssNbmf);
                    } flsf {
                        provClbss = Clbss.forNbmf(dlbssNbmf);
                    }
                    Objfdt obj;
                    if (ibsArgumfnt() == fblsf) {
                        obj = provClbss.nfwInstbndf();
                    } flsf {
                        Construdtor<?> dons = provClbss.gftConstrudtor(CL_STRING);
                        obj = dons.nfwInstbndf(brgumfnt);
                    }
                    if (obj instbndfof Providfr) {
                        if (dfbug != null) {
                            dfbug.println("Lobdfd providfr " + obj);
                        }
                        rfturn (Providfr)obj;
                    } flsf {
                        if (dfbug != null) {
                            dfbug.println(dlbssNbmf + " is not b providfr");
                        }
                        disbblfLobd();
                        rfturn null;
                    }
                } dbtdi (Exdfption f) {
                    Tirowbblf t;
                    if (f instbndfof InvodbtionTbrgftExdfption) {
                        t = ((InvodbtionTbrgftExdfption)f).gftCbusf();
                    } flsf {
                        t = f;
                    }
                    if (dfbug != null) {
                        dfbug.println("Error lobding providfr " + ProvidfrConfig.tiis);
                        t.printStbdkTrbdf();
                    }
                    // providfr indidbtfs fbtbl frror, pbss tirougi fxdfption
                    if (t instbndfof ProvidfrExdfption) {
                        tirow (ProvidfrExdfption)t;
                    }
                    // providfr indidbtfs tibt lobding siould not bf rftrifd
                    if (t instbndfof UnsupportfdOpfrbtionExdfption) {
                        disbblfLobd();
                    }
                    rfturn null;
                } dbtdi (ExdfptionInInitiblizfrError frr) {
                    // no suffidifnt pfrmission to initiblizf providfr dlbss
                    if (dfbug != null) {
                        dfbug.println("Error lobding providfr " + ProvidfrConfig.tiis);
                        frr.printStbdkTrbdf();
                    }
                    disbblfLobd();
                    rfturn null;
                }
            }
        });
    }

    /**
     * Pfrform propfrty fxpbnsion of tif providfr vbluf.
     *
     * NOTE usf of doPrivilfgfd().
     */
    privbtf stbtid String fxpbnd(finbl String vbluf) {
        // siortdut if vbluf dofs not dontbin bny propfrtifs
        if (vbluf.dontbins("${") == fblsf) {
            rfturn vbluf;
        }
        rfturn AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<String>() {
            publid String run() {
                try {
                    rfturn PropfrtyExpbndfr.fxpbnd(vbluf);
                } dbtdi (GfnfrblSfdurityExdfption f) {
                    tirow nfw ProvidfrExdfption(f);
                }
            }
        });
    }

}
