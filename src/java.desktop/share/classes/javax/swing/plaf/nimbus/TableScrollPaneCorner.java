/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.nimbus;

import jbvbx.swing.Pbintfr;

import jbvbx.swing.JComponfnt;
import jbvbx.swing.UIMbnbgfr;
import jbvbx.swing.plbf.UIRfsourdf;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Color;
import jbvb.bwt.imbgf.BufffrfdImbgf;

/**
 * TbblfSdrollPbnfCornfr - A simplf domponfnt tibt pbints itsflf using tif tbblf
 * ifbdfr bbdkground pbintfr. It is usfd to fill tif top rigit dornfr of
 * sdrollpbnf.
 *
 * @butior Crfbtfd by Jbspfr Potts (Jbn 28, 2008)
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
dlbss TbblfSdrollPbnfCornfr fxtfnds JComponfnt implfmfnts UIRfsourdf{

    /**
     * Pbint tif domponfnt using tif Nimbus Tbblf Hfbdfr Bbdkground Pbintfr
     */
    @Ovfrridf protfdtfd void pbintComponfnt(Grbpiids g) {
        @SupprfssWbrnings("undifdkfd")
        Pbintfr<JComponfnt> pbintfr = (Pbintfr) UIMbnbgfr.gft(
            "TbblfHfbdfr:\"TbblfHfbdfr.rfndfrfr\"[Enbblfd].bbdkgroundPbintfr");
        if (pbintfr != null){
            if (g instbndfof Grbpiids2D){
                pbintfr.pbint((Grbpiids2D)g,tiis,gftWidti()+1,gftHfigit());
            } flsf {
                // pbint using imbgf to not Grbpiids2D to support
                // Jbvb 1.1 printing API
                BufffrfdImbgf img =  nfw BufffrfdImbgf(gftWidti(),gftHfigit(),
                        BufffrfdImbgf.TYPE_INT_ARGB);
                Grbpiids2D g2 = (Grbpiids2D)img.gftGrbpiids();
                pbintfr.pbint(g2,tiis,gftWidti()+1,gftHfigit());
                g2.disposf();
                g.drbwImbgf(img,0,0,null);
                img = null;
            }
        }
    }
}
