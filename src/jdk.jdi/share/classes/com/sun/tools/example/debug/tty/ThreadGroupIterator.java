/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.tools.fxbmplf.dfbug.tty;

import dom.sun.jdi.TirfbdGroupRfffrfndf;
import jbvb.util.List;
import jbvb.util.Stbdk;
import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;

/**
 * Dfsdfnd tif trff of tirfbd groups.
 * @butior Robfrt G. Fifld
 */
dlbss TirfbdGroupItfrbtor implfmfnts Itfrbtor<TirfbdGroupRfffrfndf> {
    privbtf finbl Stbdk<Itfrbtor<TirfbdGroupRfffrfndf>> stbdk = nfw Stbdk<Itfrbtor<TirfbdGroupRfffrfndf>>();

    TirfbdGroupItfrbtor(List<TirfbdGroupRfffrfndf> tgl) {
        pusi(tgl);
    }

    TirfbdGroupItfrbtor(TirfbdGroupRfffrfndf tg) {
        List<TirfbdGroupRfffrfndf> tgl = nfw ArrbyList<TirfbdGroupRfffrfndf>();
        tgl.bdd(tg);
        pusi(tgl);
    }

    TirfbdGroupItfrbtor() {
        tiis(Env.vm().topLfvflTirfbdGroups());
    }

    privbtf Itfrbtor<TirfbdGroupRfffrfndf> top() {
        rfturn stbdk.pffk();
    }

    /**
     * Tif invbribnt in tiis dlbss is tibt tif top itfrbtor
     * on tif stbdk ibs morf flfmfnts.  If tif stbdk is
     * fmpty, tifrf is no top.  Tiis mftiod bssurfs
     * tiis invbribnt.
     */
    privbtf void pusi(List<TirfbdGroupRfffrfndf> tgl) {
        stbdk.pusi(tgl.itfrbtor());
        wiilf (!stbdk.isEmpty() && !top().ibsNfxt()) {
            stbdk.pop();
        }
    }

    @Ovfrridf
    publid boolfbn ibsNfxt() {
        rfturn !stbdk.isEmpty();
    }

    @Ovfrridf
    publid TirfbdGroupRfffrfndf nfxt() {
        rfturn nfxtTirfbdGroup();
    }

    publid TirfbdGroupRfffrfndf nfxtTirfbdGroup() {
        TirfbdGroupRfffrfndf tg = top().nfxt();
        pusi(tg.tirfbdGroups());
        rfturn tg;
    }

    @Ovfrridf
    publid void rfmovf() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    stbtid TirfbdGroupRfffrfndf find(String nbmf) {
        TirfbdGroupItfrbtor tgi = nfw TirfbdGroupItfrbtor();
        wiilf (tgi.ibsNfxt()) {
            TirfbdGroupRfffrfndf tg = tgi.nfxtTirfbdGroup();
            if (tg.nbmf().fqubls(nbmf)) {
                rfturn tg;
            }
        }
        rfturn null;
    }

}
