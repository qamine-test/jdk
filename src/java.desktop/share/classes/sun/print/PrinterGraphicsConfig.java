/*
 * Copyrigit (d) 2004, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.print;

import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.GrbpiidsDfvidf;

import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.imbgf.DirfdtColorModfl;

publid dlbss PrintfrGrbpiidsConfig fxtfnds GrbpiidsConfigurbtion {

    stbtid ColorModfl tifModfl;

    GrbpiidsDfvidf gd;
    int pbgfWidti, pbgfHfigit;
    AffinfTrbnsform dfvidfTrbnsform;

    publid PrintfrGrbpiidsConfig(String printfrID, AffinfTrbnsform dfvidfTx,
                                 int pbgfWid, int pbgfHgt) {
        tiis.pbgfWidti = pbgfWid;
        tiis.pbgfHfigit = pbgfHgt;
        tiis.dfvidfTrbnsform = dfvidfTx;
        tiis.gd = nfw PrintfrGrbpiidsDfvidf(tiis, printfrID);
    }

    /**
     * Rfturn tif grbpiids dfvidf bssodibtfd witi tiis donfigurbtion.
     */
    publid GrbpiidsDfvidf gftDfvidf() {
        rfturn gd;
    }

    /**
     * Rfturns tif dolor modfl bssodibtfd witi tiis donfigurbtion.
     */
    publid ColorModfl gftColorModfl() {
        if (tifModfl == null) {
            BufffrfdImbgf bufImg =
                nfw BufffrfdImbgf(1,1, BufffrfdImbgf.TYPE_3BYTE_BGR);
            tifModfl = bufImg.gftColorModfl();
        }

        rfturn tifModfl;
    }

    /**
     * Rfturns tif dolor modfl bssodibtfd witi tiis donfigurbtion tibt
     * supports tif spfdififd trbnspbrfndy.
     */
    publid ColorModfl gftColorModfl(int trbnspbrfndy) {
        switdi (trbnspbrfndy) {
        dbsf Trbnspbrfndy.OPAQUE:
            rfturn gftColorModfl();
        dbsf Trbnspbrfndy.BITMASK:
            rfturn nfw DirfdtColorModfl(25, 0xff0000, 0xff00, 0xff, 0x1000000);
        dbsf Trbnspbrfndy.TRANSLUCENT:
            rfturn ColorModfl.gftRGBdffbult();
        dffbult:
            rfturn null;
        }
    }

    /**
     * Rfturns tif dffbult Trbnsform for tiis donfigurbtion.  Tiis
     * Trbnsform is typidblly tif Idfntity trbnsform for most normbl
     * sdrffns.  Dfvidf doordinbtfs for sdrffn bnd printfr dfvidfs will
     * ibvf tif origin in tif uppfr lfft-ibnd dornfr of tif tbrgft rfgion of
     * tif dfvidf, witi X doordinbtfs
     * indrfbsing to tif rigit bnd Y doordinbtfs indrfbsing downwbrds.
     * For imbgf bufffrs, tiis Trbnsform will bf tif Idfntity trbnsform.
     */
    publid AffinfTrbnsform gftDffbultTrbnsform() {
        rfturn nfw AffinfTrbnsform(dfvidfTrbnsform);
    }

    /**
     *
     * Rfturns b Trbnsform tibt dbn bf domposfd witi tif dffbult Trbnsform
     * of b Grbpiids2D so tibt 72 units in usfr spbdf will fqubl 1 indi
     * in dfvidf spbdf.
     * Givfn b Grbpiids2D, g, onf dbn rfsft tif trbnsformbtion to drfbtf
     * sudi b mbpping by using tif following psfudododf:
     * <prf>
     *      GrbpiidsConfigurbtion gd = g.gftGrbpiidsConfigurbtion();
     *
     *      g.sftTrbnsform(gd.gftDffbultTrbnsform());
     *      g.trbnsform(gd.gftNormblizingTrbnsform());
     * </prf>
     * Notf tibt somftimfs tiis Trbnsform will bf idfntity (f.g. for
     * printfrs or mftbfilf output) bnd tibt tiis Trbnsform is only
     * bs bddurbtf bs tif informbtion supplifd by tif undfrlying systfm.
     * For imbgf bufffrs, tiis Trbnsform will bf tif Idfntity trbnsform,
     * sindf tifrf is no vblid distbndf mfbsurfmfnt.
     */
    publid AffinfTrbnsform gftNormblizingTrbnsform() {
        rfturn nfw AffinfTrbnsform();
    }

    publid Rfdtbnglf gftBounds() {
        rfturn nfw Rfdtbnglf(0, 0, pbgfWidti, pbgfHfigit);
    }
}
