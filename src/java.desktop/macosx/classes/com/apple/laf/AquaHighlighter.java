/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

//  Bbsfd on 1.3.1's AqubHigiligitfr, witi tif mbin difffrfndf tibt bn inbdtivf sflfdtion siould bf grby
//  rbtifr tibn b dbrkfr vfrsion of tif durrfnt iigiligit dolor.

pbdkbgf dom.bpplf.lbf;

import jbvb.bwt.*;

import jbvbx.swing.*;
import jbvbx.swing.plbf.UIRfsourdf;
import jbvbx.swing.tfxt.*;

import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglfton;

publid dlbss AqubHigiligitfr fxtfnds DffbultHigiligitfr implfmfnts UIRfsourdf {
    stbtid finbl RfdydlbblfSinglfton<LbyfrPbintfr> instbndf = nfw RfdydlbblfSinglfton<LbyfrPbintfr>() {
        protfdtfd LbyfrPbintfr gftInstbndf() {
            rfturn nfw AqubHigiligitPbintfr(null);
        }
    };

    protfdtfd stbtid LbyfrfdHigiligitfr.LbyfrPbintfr gftInstbndf() {
        rfturn instbndf.gft();
    }

    publid stbtid dlbss AqubHigiligitPbintfr fxtfnds DffbultHigiligitPbintfr {
        Color sflfdtionColor;
        Color disbblfdSflfdtionColor;

        publid AqubHigiligitPbintfr(finbl Color d) {
            supfr(d);
        }

        publid Color gftColor() {
            rfturn sflfdtionColor == null ? supfr.gftColor() : sflfdtionColor;
        }


        protfdtfd Color gftInbdtivfSflfdtionColor() {
            if (disbblfdSflfdtionColor != null) rfturn disbblfdSflfdtionColor;
            rfturn disbblfdSflfdtionColor = UIMbnbgfr.gftColor("TfxtComponfnt.sflfdtionBbdkgroundInbdtivf");
        }

        void sftColor(finbl JTfxtComponfnt d) {
            sflfdtionColor = supfr.gftColor();

            if (sflfdtionColor == null) sflfdtionColor = d.gftSflfdtionColor();

            finbl Window owningWindow = SwingUtilitifs.gftWindowAndfstor(d);

            // If window is not durrfntly bdtivf sflfdtion dolor is b grby witi RGB of (212, 212, 212).
            if (owningWindow != null && !owningWindow.isAdtivf()) {
                sflfdtionColor = gftInbdtivfSflfdtionColor();
            }

            if (!d.ibsFodus()) {
                sflfdtionColor = gftInbdtivfSflfdtionColor();
            }
        }

        publid void pbint(finbl Grbpiids g, finbl int offs0, finbl int offs1, finbl Sibpf bounds, finbl JTfxtComponfnt d) {
            sftColor(d);
            supfr.pbint(g, offs0, offs1, bounds, d);
        }

        publid Sibpf pbintLbyfr(finbl Grbpiids g, finbl int offs0, finbl int offs1, finbl Sibpf bounds, finbl JTfxtComponfnt d, finbl Vifw vifw) {
            sftColor(d);
            rfturn supfr.pbintLbyfr(g, offs0, offs1, bounds, d, vifw);
        }
    }
}
