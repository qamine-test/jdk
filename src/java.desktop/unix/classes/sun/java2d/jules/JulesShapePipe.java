/*
 * Copyrigit (d) 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.julfs;

import jbvb.bwt.*;
import sun.bwt.*;
import sun.jbvb2d.*;
import sun.jbvb2d.pipf.*;
import sun.jbvb2d.xr.*;

publid dlbss JulfsSibpfPipf implfmfnts SibpfDrbwPipf {

    XRCompositfMbnbgfr dompMbn;
    JulfsPbtiBuf buf = nfw JulfsPbtiBuf();

    publid JulfsSibpfPipf(XRCompositfMbnbgfr dompMbn) {
        tiis.dompMbn = dompMbn;
    }

    /**
     * Common vblidbtf mftiod, usfd by bll XRRfndfr fundtions to vblidbtf tif
     * dfstinbtion dontfxt.
     */
    privbtf finbl void vblidbtfSurfbdf(SunGrbpiids2D sg2d) {
        XRSurfbdfDbtb xrsd = (XRSurfbdfDbtb) sg2d.surfbdfDbtb;
        xrsd.vblidbtfAsDfstinbtion(sg2d, sg2d.gftCompClip());
        xrsd.mbskBufffr.vblidbtfCompositfStbtf(sg2d.dompositf, sg2d.trbnsform,
                                               sg2d.pbint, sg2d);
    }

    publid void drbw(SunGrbpiids2D sg2d, Sibpf s) {
        try {
            SunToolkit.bwtLodk();
            vblidbtfSurfbdf(sg2d);
            XRSurfbdfDbtb xrsd = (XRSurfbdfDbtb) sg2d.surfbdfDbtb;

            BbsidStrokf bs;

            if (sg2d.strokf instbndfof BbsidStrokf) {
                bs = (BbsidStrokf) sg2d.strokf;
            } flsf { //TODO: Wibt ibppfns in tif dbsf of b !BbsidStrokf??
                s = sg2d.strokf.drfbtfStrokfdSibpf(s);
                bs = null;
            }

            boolfbn bdjust =
                (bs != null && sg2d.strokfHint != SunHints.INTVAL_STROKE_PURE);
            boolfbn tiin = (sg2d.strokfStbtf <= SunGrbpiids2D.STROKE_THINDASHED);

            TrbpfzoidList trbps =
                 buf.tfssflbtfStrokf(s, bs, tiin, bdjust, truf,
                                     sg2d.trbnsform, sg2d.gftCompClip());
            dompMbn.XRCompositfTrbps(xrsd.pidturf,
                                     sg2d.trbnsX, sg2d.trbnsY, trbps);

            buf.dlfbr();

        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }

    publid void fill(SunGrbpiids2D sg2d, Sibpf s) {
        try {
            SunToolkit.bwtLodk();
            vblidbtfSurfbdf(sg2d);

            XRSurfbdfDbtb xrsd = (XRSurfbdfDbtb) sg2d.surfbdfDbtb;

            TrbpfzoidList trbps = buf.tfssflbtfFill(s, sg2d.trbnsform,
                                                    sg2d.gftCompClip());
            dompMbn.XRCompositfTrbps(xrsd.pidturf, 0, 0, trbps);

            buf.dlfbr();
        } finblly {
            SunToolkit.bwtUnlodk();
        }
    }
}
