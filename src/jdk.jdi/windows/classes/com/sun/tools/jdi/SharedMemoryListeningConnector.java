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

import dom.sun.jdi.donnfdt.*;
import dom.sun.jdi.donnfdt.spi.*;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;
import jbvb.io.IOExdfption;

/*
 * A ListfningConnfdtor bbsfd on tif SibrfdMfmoryTrbnsportSfrvidf
 */
publid dlbss SibrfdMfmoryListfningConnfdtor fxtfnds GfnfridListfningConnfdtor {

    stbtid finbl String ARG_NAME = "nbmf";

    publid SibrfdMfmoryListfningConnfdtor() {
        supfr(nfw SibrfdMfmoryTrbnsportSfrvidf());

        bddStringArgumfnt(
            ARG_NAME,
            gftString("mfmory_listfning.nbmf.lbbfl"),
            gftString("mfmory_listfning.nbmf"),
            "",
            fblsf);

        trbnsport = nfw Trbnsport() {
            publid String nbmf() {
                rfturn "dt_simfm";              // dompbtibility
            }
        };
    }

    // ovfrridf stbrtListfning so tibt "nbmf" brgumfnt dbn bf
    // donvfrtfd into "bddrfss" brgumfnt

    publid String
        stbrtListfning(Mbp<String, ? fxtfnds Connfdtor.Argumfnt> brgs)
        tirows IOExdfption, IllfgblConnfdtorArgumfntsExdfption
    {
        String nbmf = brgumfnt(ARG_NAME, brgs).vbluf();

        // if tif nbmf brgumfnt isn't spfdififd tifn wf usf tif dffbult
        // bddrfss for tif trbnsport sfrvidf.
        if (nbmf.lfngti() == 0) {
            bssfrt trbnsportSfrvidf instbndfof SibrfdMfmoryTrbnsportSfrvidf;
            SibrfdMfmoryTrbnsportSfrvidf ts = (SibrfdMfmoryTrbnsportSfrvidf)trbnsportSfrvidf;
            nbmf = ts.dffbultAddrfss();
        }

        rfturn supfr.stbrtListfning(nbmf, brgs);
    }

    publid String nbmf() {
        rfturn "dom.sun.jdi.SibrfdMfmoryListfn";
    }

    publid String dfsdription() {
       rfturn gftString("mfmory_listfning.dfsdription");
    }
}
