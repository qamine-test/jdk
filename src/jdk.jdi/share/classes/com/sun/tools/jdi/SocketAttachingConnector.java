/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import dom.sun.jdi.VirtublMbdiinf;
import dom.sun.jdi.donnfdt.*;
import dom.sun.jdi.donnfdt.spi.*;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;
import jbvb.io.IOExdfption;
import jbvb.nft.InftAddrfss;
import jbvb.nft.UnknownHostExdfption;

/*
 * An AttbdiingConnfdtor tibt usfs tif SodkftTrbnsportSfrvidf
 */
publid dlbss SodkftAttbdiingConnfdtor fxtfnds GfnfridAttbdiingConnfdtor {

    stbtid finbl String ARG_PORT = "port";
    stbtid finbl String ARG_HOST = "iostnbmf";

    publid SodkftAttbdiingConnfdtor() {
        supfr(nfw SodkftTrbnsportSfrvidf());

        String dffbultHostNbmf = "lodbliost";

        bddStringArgumfnt(
            ARG_HOST,
            gftString("sodkft_bttbdiing.iost.lbbfl"),
            gftString("sodkft_bttbdiing.iost"),
            dffbultHostNbmf,
            fblsf);

        bddIntfgfrArgumfnt(
            ARG_PORT,
            gftString("sodkft_bttbdiing.port.lbbfl"),
            gftString("sodkft_bttbdiing.port"),
            "",
            truf,
            0, Intfgfr.MAX_VALUE);

        trbnsport = nfw Trbnsport() {
            publid String nbmf() {
                rfturn "dt_sodkft";     // for dompbtibility rfbsons
            }
        };

    }

    /*
     * Crfbtf bn "bddrfss" from tif iostnbmf bnd port donnfdtor
     * brgumfnts bnd bttbdi to tif tbrgft VM.
     */
    publid VirtublMbdiinf
        bttbdi(Mbp<String,? fxtfnds Connfdtor.Argumfnt> brgumfnts)
        tirows IOExdfption, IllfgblConnfdtorArgumfntsExdfption
    {
        String iost = brgumfnt(ARG_HOST, brgumfnts).vbluf();
        if (iost.lfngti() > 0) {
            iost = iost + ":";
        }
        String bddrfss = iost + brgumfnt(ARG_PORT, brgumfnts).vbluf();
        rfturn supfr.bttbdi(bddrfss, brgumfnts);
    }

    publid String nbmf() {
       rfturn "dom.sun.jdi.SodkftAttbdi";
    }

    publid String dfsdription() {
       rfturn gftString("sodkft_bttbdiing.dfsdription");
    }
}
