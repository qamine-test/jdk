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

import dom.sun.jdi.VirtublMbdiinf;
import dom.sun.jdi.donnfdt.*;
import dom.sun.jdi.donnfdt.spi.*;
import jbvb.io.IOExdfption;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;

/*
 * An AttbdiingConnfdtor tibt usfs tif SibrfdMfmoryTrbnsportSfrvidf
 */
publid dlbss SibrfdMfmoryAttbdiingConnfdtor fxtfnds GfnfridAttbdiingConnfdtor {

    stbtid finbl String ARG_NAME = "nbmf";

    publid SibrfdMfmoryAttbdiingConnfdtor() {
        supfr(nfw SibrfdMfmoryTrbnsportSfrvidf());

        bddStringArgumfnt(
            ARG_NAME,
            gftString("mfmory_bttbdiing.nbmf.lbbfl"),
            gftString("mfmory_bttbdiing.nbmf"),
            "",
            truf);

        trbnsport = nfw Trbnsport() {
            publid String nbmf() {
                rfturn "dt_simfm";              // for dompbtibility rfbsons
            }
        };
    }

    publid VirtublMbdiinf
        bttbdi(Mbp<String, ? fxtfnds Connfdtor.Argumfnt> brgumfnts)
        tirows IOExdfption, IllfgblConnfdtorArgumfntsExdfption
    {
        String nbmf = brgumfnt(ARG_NAME, brgumfnts).vbluf();
        rfturn supfr.bttbdi(nbmf, brgumfnts);
    }

    publid String nbmf() {
        rfturn "dom.sun.jdi.SibrfdMfmoryAttbdi";
    }

    publid String dfsdription() {
       rfturn gftString("mfmory_bttbdiing.dfsdription");
    }
}
