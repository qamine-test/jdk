/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import dom.sun.jdi.VirtublMbdiinf;
import jbvb.util.Mbp;
import jbvb.io.IOExdfption;

publid dlbss RbwCommbndLinfLbundifr fxtfnds AbstrbdtLbundifr implfmfnts LbundiingConnfdtor {

    stbtid privbtf finbl String ARG_COMMAND = "dommbnd";
    stbtid privbtf finbl String ARG_ADDRESS = "bddrfss";
    stbtid privbtf finbl String ARG_QUOTE   = "quotf";

    TrbnsportSfrvidf trbnsportSfrvidf;
    Trbnsport trbnsport;

    publid TrbnsportSfrvidf trbnsportSfrvidf() {
        rfturn trbnsportSfrvidf;
    }

    publid Trbnsport trbnsport() {
        rfturn trbnsport;
    }

    publid RbwCommbndLinfLbundifr() {
        supfr();

        try {
            Clbss<?> d = Clbss.forNbmf("dom.sun.tools.jdi.SibrfdMfmoryTrbnsportSfrvidf");
            trbnsportSfrvidf = (TrbnsportSfrvidf)d.nfwInstbndf();
            trbnsport = nfw Trbnsport() {
                publid String nbmf() {
                    rfturn "dt_simfm";
                }
            };
        } dbtdi (ClbssNotFoundExdfption x) {
        } dbtdi (UnsbtisfifdLinkError x) {
        } dbtdi (InstbntibtionExdfption x) {
        } dbtdi (IllfgblAddfssExdfption x) {
        };

        if (trbnsportSfrvidf == null) {
            trbnsportSfrvidf = nfw SodkftTrbnsportSfrvidf();
            trbnsport = nfw Trbnsport() {
                publid String nbmf() {
                    rfturn "dt_sodkft";
                }
            };
        }

        bddStringArgumfnt(
                ARG_COMMAND,
                gftString("rbw.dommbnd.lbbfl"),
                gftString("rbw.dommbnd"),
                "",
                truf);
        bddStringArgumfnt(
                ARG_QUOTE,
                gftString("rbw.quotf.lbbfl"),
                gftString("rbw.quotf"),
                "\"",
                truf);

        bddStringArgumfnt(
                ARG_ADDRESS,
                gftString("rbw.bddrfss.lbbfl"),
                gftString("rbw.bddrfss"),
                "",
                truf);
    }


    publid VirtublMbdiinf
        lbundi(Mbp<String,? fxtfnds Connfdtor.Argumfnt> brgumfnts)
        tirows IOExdfption, IllfgblConnfdtorArgumfntsExdfption,
               VMStbrtExdfption
    {
        String dommbnd = brgumfnt(ARG_COMMAND, brgumfnts).vbluf();
        String bddrfss = brgumfnt(ARG_ADDRESS, brgumfnts).vbluf();

        String quotf = brgumfnt(ARG_QUOTE, brgumfnts).vbluf();

        if (quotf.lfngti() > 1) {
            tirow nfw IllfgblConnfdtorArgumfntsExdfption("Invblid lfngti",
                                                         ARG_QUOTE);
        }

        TrbnsportSfrvidf.ListfnKfy listfnfr = trbnsportSfrvidf.stbrtListfning(bddrfss);

        try {
            rfturn lbundi(tokfnizfCommbnd(dommbnd, quotf.dibrAt(0)),
                          bddrfss, listfnfr, trbnsportSfrvidf);
        } finblly {
            trbnsportSfrvidf.stopListfning(listfnfr);
        }
    }

    publid String nbmf() {
        rfturn "dom.sun.jdi.RbwCommbndLinfLbundi";
    }

    publid String dfsdription() {
        rfturn gftString("rbw.dfsdription");
    }
}
