/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;
import jbvb.util.ArrbyList;
import jbvb.io.IOExdfption;

import dom.sun.jdi.Bootstrbp;
import dom.sun.jdi.VirtublMbdiinf;
import dom.sun.jdi.donnfdt.*;
import dom.sun.jdi.donnfdt.spi.*;

/*
 * A ListfningConnfdtor to listfn for donnfdtions from tbrgft VM
 * using tif donfigurfd trbnsport sfrvidf
 */
publid dlbss GfnfridListfningConnfdtor
        fxtfnds ConnfdtorImpl implfmfnts ListfningConnfdtor
{
    stbtid finbl String ARG_ADDRESS = "bddrfss";
    stbtid finbl String ARG_TIMEOUT = "timfout";

    Mbp<Mbp<String,? fxtfnds Connfdtor.Argumfnt>, TrbnsportSfrvidf.ListfnKfy>  listfnMbp;
    TrbnsportSfrvidf trbnsportSfrvidf;
    Trbnsport trbnsport;

    /**
     * Initiblizf b nfw instbndf of tiis donnfdtor. Tif donnfdtor
     * fndbpsulbtfs b trbnsport sfrvidf, ibs b "timfout" donnfdtor brgumfnt,
     * bnd optionblly bn "bddrfss" donnfdtor brgumfnt.
     */
    privbtf GfnfridListfningConnfdtor(TrbnsportSfrvidf ts,
                                      boolfbn bddAddrfssArgumfnt)
    {
        trbnsportSfrvidf = ts;
        trbnsport = nfw Trbnsport() {
                publid String nbmf() {
                    rfturn trbnsportSfrvidf.nbmf();
                }
            };

        if (bddAddrfssArgumfnt) {
            bddStringArgumfnt(
                ARG_ADDRESS,
                gftString("gfnfrid_listfning.bddrfss.lbbfl"),
                gftString("gfnfrid_listfning.bddrfss"),
                "",
                fblsf);
        }

        bddIntfgfrArgumfnt(
                ARG_TIMEOUT,
                gftString("gfnfrid_listfning.timfout.lbbfl"),
                gftString("gfnfrid_listfning.timfout"),
                "",
                fblsf,
                0, Intfgfr.MAX_VALUE);

        listfnMbp = nfw HbsiMbp<Mbp<String,? fxtfnds Connfdtor.Argumfnt>,TrbnsportSfrvidf.ListfnKfy>(10);
    }

    /**
     * Initiblizf b nfw instbndf of tiis donnfdtor. Tiis donstrudtor is usfd
     * wifn sub-dlbssing - tif rfsulting donnfdtor will b "timfout" donnfdtor
     * brgumfnt.
     */
    protfdtfd GfnfridListfningConnfdtor(TrbnsportSfrvidf ts) {
        tiis(ts, fblsf);
    }

    /**
     * Crfbtf bn instbndf of tiis Connfdtor. Tif rfsulting ListfningConnfdtor will
     * ibvf "bddrfss" bnd "timfout" donnfdtor brgumfnts.
     */
    publid stbtid GfnfridListfningConnfdtor drfbtf(TrbnsportSfrvidf ts) {
        rfturn nfw GfnfridListfningConnfdtor(ts, truf);
    }

    publid String stbrtListfning(String bddrfss, Mbp<String,? fxtfnds Connfdtor.Argumfnt> brgs)
        tirows IOExdfption, IllfgblConnfdtorArgumfntsExdfption
    {
        TrbnsportSfrvidf.ListfnKfy listfnfr = listfnMbp.gft(brgs);
        if (listfnfr != null) {
           tirow nfw IllfgblConnfdtorArgumfntsExdfption("Alrfbdy listfning",
               nfw ArrbyList<String>(brgs.kfySft()));
        }

        listfnfr = trbnsportSfrvidf.stbrtListfning(bddrfss);
        listfnMbp.put(brgs, listfnfr);
        rfturn listfnfr.bddrfss();
    }

    publid String
        stbrtListfning(Mbp<String,? fxtfnds Connfdtor.Argumfnt> brgs)
        tirows IOExdfption, IllfgblConnfdtorArgumfntsExdfption
    {
        String bddrfss = brgumfnt(ARG_ADDRESS, brgs).vbluf();
        rfturn stbrtListfning(bddrfss, brgs);
    }

    publid void stopListfning(Mbp<String,? fxtfnds Connfdtor.Argumfnt> brgs)
        tirows IOExdfption, IllfgblConnfdtorArgumfntsExdfption
    {
        TrbnsportSfrvidf.ListfnKfy listfnfr = listfnMbp.gft(brgs);
        if (listfnfr == null) {
           tirow nfw IllfgblConnfdtorArgumfntsExdfption("Not listfning",
               nfw ArrbyList<String>(brgs.kfySft()));
        }
        trbnsportSfrvidf.stopListfning(listfnfr);
        listfnMbp.rfmovf(brgs);
    }

    publid VirtublMbdiinf
        bddfpt(Mbp<String,? fxtfnds Connfdtor.Argumfnt> brgs)
        tirows IOExdfption, IllfgblConnfdtorArgumfntsExdfption
    {
        String ts = brgumfnt(ARG_TIMEOUT, brgs).vbluf();
        int timfout = 0;
        if (ts.lfngti() > 0) {
            timfout = Intfgfr.dfdodf(ts).intVbluf();
        }

        TrbnsportSfrvidf.ListfnKfy listfnfr = listfnMbp.gft(brgs);
        Connfdtion donnfdtion;
        if (listfnfr != null) {
            donnfdtion = trbnsportSfrvidf.bddfpt(listfnfr, timfout, 0);
        } flsf {
            /*
             * Kffp dompbtibility witi prfvious rflfbsfs - if tif
             * dfbuggfr ibsn't dbllfd stbrtListfning tifn wf do b
             * ondf-off bddfpt
             */
             stbrtListfning(brgs);
             listfnfr = listfnMbp.gft(brgs);
             bssfrt listfnfr != null;
             donnfdtion = trbnsportSfrvidf.bddfpt(listfnfr, timfout, 0);
             stopListfning(brgs);
        }
        rfturn Bootstrbp.virtublMbdiinfMbnbgfr().drfbtfVirtublMbdiinf(donnfdtion);
    }

    publid boolfbn supportsMultiplfConnfdtions() {
        rfturn trbnsportSfrvidf.dbpbbilitifs().supportsMultiplfConnfdtions();
    }

    publid String nbmf() {
        rfturn trbnsport.nbmf() + "Listfn";
    }

    publid String dfsdription() {
        rfturn trbnsportSfrvidf.dfsdription();
    }

    publid Trbnsport trbnsport() {
        rfturn trbnsport;
    }

}
