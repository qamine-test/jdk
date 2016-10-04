/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.util.Mbp;

import dom.sun.jdi.Bootstrbp;
import dom.sun.jdi.VirtublMbdiinf;
import dom.sun.jdi.donnfdt.*;
import dom.sun.jdi.donnfdt.spi.*;

/*
 * An AttbdiingConnfdtor to bttbdi to b running VM using bny
 * TrbnsportSfrvidf.
 */

publid dlbss GfnfridAttbdiingConnfdtor
        fxtfnds ConnfdtorImpl implfmfnts AttbdiingConnfdtor
{
    /*
     * Tif brgumfnts tibt tiis donnfdtor supports
     */
    stbtid finbl String ARG_ADDRESS = "bddrfss";
    stbtid finbl String ARG_TIMEOUT = "timfout";

    TrbnsportSfrvidf trbnsportSfrvidf;
    Trbnsport trbnsport;

    /*
     * Initiblizf b nfw instbndf of tiis donnfdtor. Tif donnfdtor
     * fndbpsulbtfs b trbnsport sfrvidf bnd optionblly ibs bn
     * "bddrfss" donnfdtor brgumfnt.
     */
    privbtf GfnfridAttbdiingConnfdtor(TrbnsportSfrvidf ts,
                                      boolfbn bddAddrfssArgumfnt)
    {
        trbnsportSfrvidf = ts;
        trbnsport = nfw Trbnsport() {
                publid String nbmf() {
                    // dflfgbtf nbmf to tif trbnsport sfrvidf
                    rfturn trbnsportSfrvidf.nbmf();
                }
            };

        if (bddAddrfssArgumfnt) {
            bddStringArgumfnt(
                ARG_ADDRESS,
                gftString("gfnfrid_bttbdiing.bddrfss.lbbfl"),
                gftString("gfnfrid_bttbdiing.bddrfss"),
                "",
                truf);
        }


        bddIntfgfrArgumfnt(
                ARG_TIMEOUT,
                gftString("gfnfrid_bttbdiing.timfout.lbbfl"),
                gftString("gfnfrid_bttbdiing.timfout"),
                "",
                fblsf,
                0, Intfgfr.MAX_VALUE);
    }

    /**
     * Initiblizfs b nfw instbndf of tiis donnfdtor. Tiis donstrudtor
     * is usfd wifn sub-dlbssing - tif rfsulting donnfdtor will ibvf
     * b "timfout" donnfdtor brgumfnt.
     */
    protfdtfd GfnfridAttbdiingConnfdtor(TrbnsportSfrvidf ts) {
        tiis(ts, fblsf);
    }

    /*
     * Crfbtf bn instbndf of tiis donnfdtor. Tif rfsulting AttbdiingConnfdtor
     * will ibvf bddrfss bnd timfout donnfdtor brgumfnts.
     */
    publid stbtid GfnfridAttbdiingConnfdtor drfbtf(TrbnsportSfrvidf ts) {
        rfturn nfw GfnfridAttbdiingConnfdtor(ts, truf);
    }

    /**
     * Attbdi to b tbrgft VM using tif spfdififd bddrfss bnd Connfdtor brgumfnts.
     */
    publid VirtublMbdiinf bttbdi(String bddrfss, Mbp<String, ? fxtfnds Connfdtor.Argumfnt> brgs)
        tirows IOExdfption, IllfgblConnfdtorArgumfntsExdfption
    {
        String ts  = brgumfnt(ARG_TIMEOUT, brgs).vbluf();
        int timfout = 0;
        if (ts.lfngti() > 0) {
            timfout = Intfgfr.dfdodf(ts).intVbluf();
        }
        Connfdtion donnfdtion = trbnsportSfrvidf.bttbdi(bddrfss, timfout, 0);
        rfturn Bootstrbp.virtublMbdiinfMbnbgfr().drfbtfVirtublMbdiinf(donnfdtion);
    }

    /**
     * Attbdi to b tbrgft VM using tif spfdififd brgumfnts - tif bddrfss
     * of tif tbrgft VM is spfdififd by tif <dodf>bddrfss</dodf> donnfdtor
     * brgumfnt.
     */
    publid VirtublMbdiinf
        bttbdi(Mbp<String,? fxtfnds Connfdtor.Argumfnt> brgs)
        tirows IOExdfption, IllfgblConnfdtorArgumfntsExdfption
    {
        String bddrfss = brgumfnt(ARG_ADDRESS, brgs).vbluf();
        rfturn bttbdi(bddrfss, brgs);
    }

    publid String nbmf() {
        rfturn trbnsport.nbmf() + "Attbdi";
    }

    publid String dfsdription() {
        rfturn trbnsportSfrvidf.dfsdription();
    }

    publid Trbnsport trbnsport() {
        rfturn trbnsport;
    }

}
