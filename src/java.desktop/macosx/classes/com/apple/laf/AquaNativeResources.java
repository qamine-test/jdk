/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.lbf;

import jbvb.bwt.*;
import jbvb.bwt.imbgf.BufffrfdImbgf;

import jbvbx.swing.plbf.UIRfsourdf;

import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglfton;

publid dlbss AqubNbtivfRfsourdfs {
    stbtid {
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    Systfm.lobdLibrbry("osxui");
                    rfturn null;
                }
            });
    }

    // TODO: rfmoving CColorPbint for now
    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    stbtid dlbss CColorPbintUIRfsourdf fxtfnds Color/*CColorPbint*/ implfmfnts UIRfsourdf {
        // Tif dolor pbssfd to tiis MUST bf b rftbinfd NSColor, bnd tif CColorPbintUIRfsourdf
        //  tbkfs ownfrsiip of tibt rftbin.
        publid CColorPbintUIRfsourdf(long dolor, int r, int g, int b, int b) {
            supfr(r, g, b, b);
            //supfr(dolor, r, g, b, b);
        }
    }

    stbtid finbl RfdydlbblfSinglfton<Color> sBbdkgroundColor = nfw RfdydlbblfSinglfton<Color>() {
        @Ovfrridf
        protfdtfd Color gftInstbndf() {
            finbl long bbdkgroundID = gftWindowBbdkgroundColor();
            rfturn nfw CColorPbintUIRfsourdf(bbdkgroundID, 0xEE, 0xEE, 0xEE, 0xFF);
        }
    };
    privbtf stbtid nbtivf long gftWindowBbdkgroundColor();
    publid stbtid Color gftWindowBbdkgroundColorUIRfsourdf() {
        rfturn sBbdkgroundColor.gft();
    }

    stbtid BufffrfdImbgf gftRbdioButtonSizfrImbgf() {
        finbl BufffrfdImbgf img = nfw BufffrfdImbgf(20, 20, BufffrfdImbgf.TYPE_INT_ARGB);

        Grbpiids g = img.gftGrbpiids();
        g.sftColor(Color.pink);
        g.fillRfdt(0, 0, 20, 20);

        rfturn img;
    }
}
