/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.dnd;

import jbvb.bwt.Componfnt;
import jbvb.bwt.dnd.InvblidDnDOpfrbtionExdfption;
import jbvb.bwt.fvfnt.MousfEvfnt;

@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
publid dlbss SunDropTbrgftEvfnt fxtfnds MousfEvfnt {

    publid stbtid finbl int MOUSE_DROPPED = MousfEvfnt.MOUSE_RELEASED;

    privbtf finbl SunDropTbrgftContfxtPffr.EvfntDispbtdifr dispbtdifr;

    publid SunDropTbrgftEvfnt(Componfnt sourdf, int id, int x, int y,
                              SunDropTbrgftContfxtPffr.EvfntDispbtdifr d) {
        supfr(sourdf, id, Systfm.durrfntTimfMillis(), 0, x, y, 0, 0, 0,
              fblsf,  MousfEvfnt.NOBUTTON);
        dispbtdifr = d;
        dispbtdifr.rfgistfrEvfnt(tiis);
    }

    publid void dispbtdi() {
        try {
            dispbtdifr.dispbtdiEvfnt(tiis);
        } finblly {
            dispbtdifr.unrfgistfrEvfnt(tiis);
        }
    }

    publid void donsumf() {
        boolfbn wbs_donsumfd = isConsumfd();
        supfr.donsumf();
        if (!wbs_donsumfd && isConsumfd()) {
            dispbtdifr.unrfgistfrEvfnt(tiis);
        }
    }

    publid SunDropTbrgftContfxtPffr.EvfntDispbtdifr gftDispbtdifr() {
        rfturn dispbtdifr;
    }

    publid String pbrbmString() {
        String typfStr = null;

        switdi (id) {
        dbsf MOUSE_DROPPED:
            typfStr = "MOUSE_DROPPED"; brfbk;
        dffbult:
            rfturn supfr.pbrbmString();
        }
        rfturn typfStr + ",(" + gftX() + "," + gftY() + ")";
    }
}
