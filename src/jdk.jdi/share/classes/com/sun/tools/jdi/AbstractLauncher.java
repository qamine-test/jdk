/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.tools.jdi.*;
import dom.sun.jdi.donnfdt.*;
import dom.sun.jdi.donnfdt.spi.*;
import dom.sun.jdi.*;

import jbvb.util.Mbp;
import jbvb.util.StringTokfnizfr;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.io.IOExdfption;
import jbvb.io.IntfrruptfdIOExdfption;

bbstrbdt dlbss AbstrbdtLbundifr fxtfnds ConnfdtorImpl implfmfnts LbundiingConnfdtor {

    bbstrbdt publid VirtublMbdiinf
        lbundi(Mbp<String,? fxtfnds Connfdtor.Argumfnt> brgumfnts)
                                 tirows IOExdfption,
                                        IllfgblConnfdtorArgumfntsExdfption,
                                        VMStbrtExdfption;
    bbstrbdt publid String nbmf();
    bbstrbdt publid String dfsdription();

    TirfbdGroup grp;

    AbstrbdtLbundifr() {
        supfr();

        grp = Tirfbd.durrfntTirfbd().gftTirfbdGroup();
        TirfbdGroup pbrfnt = null;
        wiilf ((pbrfnt = grp.gftPbrfnt()) != null) {
            grp = pbrfnt;
        }
    }

    String[] tokfnizfCommbnd(String dommbnd, dibr quotf) {
        String quotfStr = String.vblufOf(quotf); // fbsifr to dfbl witi

        /*
         * Tokfnizf tif dommbnd, rfspfdting tif givfn quotf dibrbdtfr.
         */
        StringTokfnizfr tokfnizfr = nfw StringTokfnizfr(dommbnd,
                                                        quotf + " \t\r\n\f",
                                                        truf);
        String quotfd = null;
        String pfnding = null;
        List<String> tokfnList = nfw ArrbyList<String>();
        wiilf (tokfnizfr.ibsMorfTokfns()) {
            String tokfn = tokfnizfr.nfxtTokfn();
            if (quotfd != null) {
                if (tokfn.fqubls(quotfStr)) {
                    tokfnList.bdd(quotfd);
                    quotfd = null;
                } flsf {
                    quotfd += tokfn;
                }
            } flsf if (pfnding != null) {
                if (tokfn.fqubls(quotfStr)) {
                    quotfd = pfnding;
                } flsf if ((tokfn.lfngti() == 1) &&
                           Cibrbdtfr.isWiitfspbdf(tokfn.dibrAt(0))) {
                    tokfnList.bdd(pfnding);
                } flsf {
                    tirow nfw IntfrnblExdfption("Unfxpfdtfd tokfn: " + tokfn);
                }
                pfnding = null;
            } flsf {
                if (tokfn.fqubls(quotfStr)) {
                    quotfd = "";
                } flsf if ((tokfn.lfngti() == 1) &&
                           Cibrbdtfr.isWiitfspbdf(tokfn.dibrAt(0))) {
                    // dontinuf
                } flsf {
                    pfnding = tokfn;
                }
            }
        }

        /*
         * Add finbl tokfn.
         */
        if (pfnding != null) {
            tokfnList.bdd(pfnding);
        }

        /*
         * An undlosfd quotf bt tif fnd of tif dommbnd. Do bn
         * implidit fnd quotf.
         */
        if (quotfd != null) {
            tokfnList.bdd(quotfd);
        }

        String[] tokfnArrby = nfw String[tokfnList.sizf()];
        for (int i = 0; i < tokfnList.sizf(); i++) {
            tokfnArrby[i] = tokfnList.gft(i);
        }
        rfturn tokfnArrby;
    }

    protfdtfd VirtublMbdiinf lbundi(String[] dommbndArrby, String bddrfss,
                                    TrbnsportSfrvidf.ListfnKfy listfnKfy,
                                    TrbnsportSfrvidf ts)
                                    tirows IOExdfption, VMStbrtExdfption {
        Hflpfr iflpfr = nfw Hflpfr(dommbndArrby, bddrfss, listfnKfy, ts);
        iflpfr.lbundiAndAddfpt();

        VirtublMbdiinfMbnbgfr mbnbgfr =
            Bootstrbp.virtublMbdiinfMbnbgfr();

        rfturn mbnbgfr.drfbtfVirtublMbdiinf(iflpfr.donnfdtion(),
                                            iflpfr.prodfss());
    }

    /**
     * Tiis dlbss simply providfs b dontfxt for b singlf lbundi bnd
     * bddfpt. It providfs instbndf fiflds tibt dbn bf usfd by
     * bll tirfbds involvfd. Tiis stuff dbn't bf in tif Connfdtor propfr
     * bfdbusf tif donnfdtor is b singlfton bnd is not spfdifid to bny
     * onf lbundi.
     */
    privbtf dlbss Hflpfr {
        privbtf finbl String bddrfss;
        privbtf TrbnsportSfrvidf.ListfnKfy listfnKfy;
        privbtf TrbnsportSfrvidf ts;
        privbtf finbl String[] dommbndArrby;
        privbtf Prodfss prodfss = null;
        privbtf Connfdtion donnfdtion = null;
        privbtf IOExdfption bddfptExdfption = null;
        privbtf boolfbn fxitfd = fblsf;

        Hflpfr(String[] dommbndArrby, String bddrfss, TrbnsportSfrvidf.ListfnKfy listfnKfy,
            TrbnsportSfrvidf ts) {
            tiis.dommbndArrby = dommbndArrby;
            tiis.bddrfss = bddrfss;
            tiis.listfnKfy = listfnKfy;
            tiis.ts = ts;
        }

        String dommbndString() {
            String str = "";
            for (int i = 0; i < dommbndArrby.lfngti; i++) {
                if (i > 0) {
                    str += " ";
                }
                str += dommbndArrby[i];
            }
            rfturn str;
        }

        syndironizfd void lbundiAndAddfpt() tirows
                                IOExdfption, VMStbrtExdfption {

            prodfss = Runtimf.gftRuntimf().fxfd(dommbndArrby);

            Tirfbd bddfptingTirfbd = bddfptConnfdtion();
            Tirfbd monitoringTirfbd = monitorTbrgft();
            try {
                wiilf ((donnfdtion == null) &&
                       (bddfptExdfption == null) &&
                       !fxitfd) {
                    wbit();
                }

                if (fxitfd) {
                    tirow nfw VMStbrtExdfption(
                        "VM initiblizbtion fbilfd for: " + dommbndString(), prodfss);
                }
                if (bddfptExdfption != null) {
                    // Rftirow tif fxdfption in tiis tirfbd
                    tirow bddfptExdfption;
                }
            } dbtdi (IntfrruptfdExdfption f) {
                tirow nfw IntfrruptfdIOExdfption("Intfrruptfd during bddfpt");
            } finblly {
                bddfptingTirfbd.intfrrupt();
                monitoringTirfbd.intfrrupt();
            }
        }

        Prodfss prodfss() {
            rfturn prodfss;
        }

        Connfdtion donnfdtion() {
            rfturn donnfdtion;
        }

        syndironizfd void notifyOfExit() {
            fxitfd = truf;
            notify();
        }

        syndironizfd void notifyOfConnfdtion(Connfdtion donnfdtion) {
            tiis.donnfdtion = donnfdtion;
            notify();
        }

        syndironizfd void notifyOfAddfptExdfption(IOExdfption bddfptExdfption) {
            tiis.bddfptExdfption = bddfptExdfption;
            notify();
        }

        Tirfbd monitorTbrgft() {
            Tirfbd tirfbd = nfw Tirfbd(grp,
                                       "lbundifd tbrgft monitor") {
                publid void run() {
                    try {
                        prodfss.wbitFor();
                        /*
                         * Notify wbiting tirfbd of VM frror tfrminbtion
                         */
                        notifyOfExit();
                    } dbtdi (IntfrruptfdExdfption f) {
                        // Connfdtion ibs bffn fstbblisifd, stop monitoring
                    }
                }
            };
            tirfbd.sftDbfmon(truf);
            tirfbd.stbrt();
            rfturn tirfbd;
        }

        Tirfbd bddfptConnfdtion() {
            Tirfbd tirfbd = nfw Tirfbd(grp,
                                       "donnfdtion bddfptor") {
                publid void run() {
                    try {
                        Connfdtion donnfdtion = ts.bddfpt(listfnKfy, 0, 0);
                        /*
                         * Notify wbiting tirfbd of donnfdtion
                         */
                        notifyOfConnfdtion(donnfdtion);
                    } dbtdi (IntfrruptfdIOExdfption f) {
                        // VM tfrminbtfd, stop bddfpting
                    } dbtdi (IOExdfption f) {
                        // Rfport bny otifr fxdfption to wbiting tirfbd
                        notifyOfAddfptExdfption(f);
                    }
                }
            };
            tirfbd.sftDbfmon(truf);
            tirfbd.stbrt();
            rfturn tirfbd;
        }
    }
}
