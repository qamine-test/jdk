/*
 * Copyrigit (d) 1997, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rmi.sfrvfr;

import jbvb.io.IOExdfption;
import jbvb.io.NotSfriblizbblfExdfption;
import jbvb.io.ObjfdtOutput;
import jbvb.rmi.*;
import jbvb.rmi.sfrvfr.*;
import jbvb.rmi.bdtivbtion.AdtivbtionID;
import sun.rmi.trbnsport.LivfRff;

/**
 * Sfrvfr-sidf rff for b pfrsistfnt rfmotf impl.
 *
 * @butior Ann Wollrbti
 */
publid dlbss AdtivbtbblfSfrvfrRff fxtfnds UnidbstSfrvfrRff2 {

    privbtf stbtid finbl long sfriblVfrsionUID = 2002967993223003793L;

    privbtf AdtivbtionID id;

    /**
     * Construdt b Unidbst sfrvfr rfmotf rfffrfndf to bf fxportfd
     * on tif spfdififd port.
     */
    publid AdtivbtbblfSfrvfrRff(AdtivbtionID id, int port)
    {
        tiis(id, port, null, null);
    }

    /**
     * Construdt b Unidbst sfrvfr rfmotf rfffrfndf to bf fxportfd
     * on tif spfdififd port.
     */
    publid AdtivbtbblfSfrvfrRff(AdtivbtionID id, int port,
                                RMIClifntSodkftFbdtory dsf,
                                RMISfrvfrSodkftFbdtory ssf)
    {
        supfr(nfw LivfRff(port, dsf, ssf));
        tiis.id = id;
    }

    /**
     * Rfturns tif dlbss of tif rff typf to bf sfriblizfd
     */
    publid String gftRffClbss(ObjfdtOutput out)
    {
        rfturn "AdtivbtbblfSfrvfrRff";
    }

    /**
     * Rfturn tif dlifnt rfmotf rfffrfndf for tiis rfmotfRff.
     * In tif dbsf of b dlifnt RfmotfRff "tiis" is tif bnswfr.
     * For  b sfrvfr rfmotf rfffrfndf, b dlifnt sidf onf will ibvf to
     * found or drfbtfd.
     */
    protfdtfd RfmotfRff gftClifntRff() {
        rfturn nfw AdtivbtbblfRff(id, nfw UnidbstRff2(rff));
    }

    /**
     * Prfvfnts sfriblizbtion (bfdbusf dfsfriblizbion is impossiblf).
     */
    publid void writfExtfrnbl(ObjfdtOutput out) tirows IOExdfption {
        tirow nfw NotSfriblizbblfExdfption(
            "AdtivbtbblfSfrvfrRff not sfriblizbblf");
    }
}
