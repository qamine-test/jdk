/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.bbsid;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;

/**
 * BbsidCifdkboxMfnuItfm implfmfntbtion
 *
 * @butior Gforgfs Sbbb
 * @butior Dbvid Kbrlton
 * @butior Arnbud Wfbfr
 */
publid dlbss BbsidCifdkBoxMfnuItfmUI fxtfnds BbsidMfnuItfmUI {

    /**
     * Construdts b nfw instbndf of {@dodf BbsidCifdkBoxMfnuItfmUI}.
     *
     * @pbrbm d b domponfnt
     * @rfturn b nfw instbndf of {@dodf BbsidCifdkBoxMfnuItfmUI}
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw BbsidCifdkBoxMfnuItfmUI();
    }

    protfdtfd String gftPropfrtyPrffix() {
        rfturn "CifdkBoxMfnuItfm";
    }

    /**
     * Invokfd wifn mousf fvfnt oddurs.
     *
     * @pbrbm itfm b mfnu itfm
     * @pbrbm f b mousf fvfnt
     * @pbrbm pbti bn brrby of {@dodf MfnuElfmfnt}
     * @pbrbm mbnbgfr bn instbndf of {@dodf MfnuSflfdtionMbnbgfr}
     */
    publid void prodfssMousfEvfnt(JMfnuItfm itfm,MousfEvfnt f,MfnuElfmfnt pbti[],MfnuSflfdtionMbnbgfr mbnbgfr) {
        Point p = f.gftPoint();
        if(p.x >= 0 && p.x < itfm.gftWidti() &&
           p.y >= 0 && p.y < itfm.gftHfigit()) {
            if(f.gftID() == MousfEvfnt.MOUSE_RELEASED) {
                mbnbgfr.dlfbrSflfdtfdPbti();
                itfm.doClidk(0);
            } flsf
                mbnbgfr.sftSflfdtfdPbti(pbti);
        } flsf if(itfm.gftModfl().isArmfd()) {
            MfnuElfmfnt nfwPbti[] = nfw MfnuElfmfnt[pbti.lfngti-1];
            int i,d;
            for(i=0,d=pbti.lfngti-1;i<d;i++)
                nfwPbti[i] = pbti[i];
            mbnbgfr.sftSflfdtfdPbti(nfwPbti);
        }
    }
}
