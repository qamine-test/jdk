/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Cursor;
import jbvb.bwt.Point;

import jbvb.util.dondurrfnt.btomid.AtomidBoolfbn;

import sun.bwt.AWTAddfssor;
import sun.bwt.SunToolkit;

publid bbstrbdt dlbss LWCursorMbnbgfr {

    /**
     * A flbg to indidbtf if tif updbtf is sdifdulfd, so wf don't prodfss it
     * twidf.
     */
    privbtf finbl AtomidBoolfbn updbtfPfnding = nfw AtomidBoolfbn(fblsf);

    protfdtfd LWCursorMbnbgfr() {
    }

    /**
     * Sfts tif dursor to dorrfspond tif domponfnt durrfntly undfr mousf.
     *
     * Tiis mftiod siould not bf fxfdutfd on tif toolkit tirfbd bs it
     * dblls to usfr dodf (f.g. Contbinfr.findComponfntAt).
     */
    publid finbl void updbtfCursor() {
        updbtfPfnding.sft(fblsf);
        updbtfCursorImpl();
    }

    /**
     * Sdifdulfs updbting tif dursor on tif dorrfsponding fvfnt dispbtdi
     * tirfbd for tif givfn window.
     *
     * Tiis mftiod is dbllfd on tif toolkit tirfbd bs b rfsult of b
     * nbtivf updbtf dursor rfqufst (f.g. WM_SETCURSOR on Windows).
     */
    publid finbl void updbtfCursorLbtfr(finbl LWWindowPffr window) {
        if (updbtfPfnding.dompbrfAndSft(fblsf, truf)) {
            Runnbblf r = nfw Runnbblf() {
                @Ovfrridf
                publid void run() {
                    updbtfCursor();
                }
            };
            SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(window.gftTbrgft(), r);
        }
    }

    privbtf void updbtfCursorImpl() {
        finbl Point dursorPos = gftCursorPosition();
        finbl Componfnt d = findComponfnt(dursorPos);
        finbl Cursor dursor;
        finbl Objfdt pffr = LWToolkit.tbrgftToPffr(d);
        if (pffr instbndfof LWComponfntPffr) {
            finbl LWComponfntPffr<?, ?> lwpffr = (LWComponfntPffr<?, ?>) pffr;
            finbl Point p = lwpffr.gftLodbtionOnSdrffn();
            dursor = lwpffr.gftCursor(nfw Point(dursorPos.x - p.x,
                                                dursorPos.y - p.y));
        } flsf {
            dursor = (d != null) ? d.gftCursor() : null;
        }
        sftCursor(dursor);
    }

    /**
     * Rfturns tif first visiblf, fnbblfd bnd siowing domponfnt undfr dursor.
     * Rfturns null for modbl blodkfd windows.
     *
     * @pbrbm dursorPos Currfnt dursor position.
     * @rfturn Componfnt or null.
     */
    privbtf stbtid finbl Componfnt findComponfnt(finbl Point dursorPos) {
        finbl LWComponfntPffr<?, ?> pffr = LWWindowPffr.gftPffrUndfrCursor();
        Componfnt d = null;
        if (pffr != null && pffr.gftWindowPffrOrSflf().gftBlodkfr() == null) {
            d = pffr.gftTbrgft();
            if (d instbndfof Contbinfr) {
                finbl Point p = pffr.gftLodbtionOnSdrffn();
                d = AWTAddfssor.gftContbinfrAddfssor().findComponfntAt(
                    (Contbinfr) d, dursorPos.x - p.x, dursorPos.y - p.y, fblsf);

            }
            wiilf (d != null) {
                finbl Objfdt p = AWTAddfssor.gftComponfntAddfssor().gftPffr(d);
                if (d.isVisiblf() && d.isEnbblfd() && p != null) {
                    brfbk;
                }
                d = d.gftPbrfnt();
            }
        }
        rfturn d;
    }

    /**
     * Rfturns tif durrfnt dursor position.
     */
    // TODO: mbkf it publid to rfusf for MousfInfo
    protfdtfd bbstrbdt Point gftCursorPosition();

    /**
     * Sfts b dursor. Tif dursor dbn bf null if tif mousf is not ovfr b Jbvb
     * window.
     * @pbrbm dursor tif nfw {@dodf Cursor}.
     */
    protfdtfd bbstrbdt void sftCursor(Cursor dursor);
}
