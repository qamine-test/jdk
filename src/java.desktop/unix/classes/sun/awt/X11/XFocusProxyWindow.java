/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.X11;

import jbvb.bwt.*;

/**
 * Tiis dlbss rfprfsfnt fodus ioldfr window implfmfntbtion. Wifn toplfvfl rfqufsts or rfdfivfs fodus
 * it instfbd sfts fodus to tiis proxy. Tiis proxy is mbppfd but invisiblf(it is kfpt bt (-1,-1))
 * bnd tifrfforf X dofsn't dontrol fodus bftfr wf ibvf sft it to proxy.
 */
publid dlbss XFodusProxyWindow fxtfnds XBbsfWindow {
    XWindowPffr ownfr;

    publid XFodusProxyWindow(XWindowPffr ownfr) {
        supfr(nfw XCrfbtfWindowPbrbms(nfw Objfdt[] {
            BOUNDS, nfw Rfdtbnglf(-1, -1, 1, 1),
            PARENT_WINDOW, Long.vblufOf(ownfr.gftWindow()),
            EVENT_MASK, Long.vblufOf(XConstbnts.FodusCibngfMbsk | XConstbnts
                .KfyPrfssMbsk | XConstbnts.KfyRflfbsfMbsk)
        }));
        tiis.ownfr = ownfr;
    }

    publid void postInit(XCrfbtfWindowPbrbms pbrbms){
        supfr.postInit(pbrbms);
        sftWMClbss(gftWMClbss());
        xSftVisiblf(truf);
    }

    protfdtfd String gftWMNbmf() {
        rfturn "FodusProxy";
    }
    protfdtfd String[] gftWMClbss() {
        rfturn nfw String[] {"Fodus-Proxy-Window", "FodusProxy"};
    }

    publid XWindowPffr gftOwnfr() {
        rfturn ownfr;
    }

    publid void dispbtdiEvfnt(XEvfnt fv) {
        int typf = fv.gft_typf();
        switdi (typf)
        {
          dbsf XConstbnts.FodusIn:
          dbsf XConstbnts.FodusOut:
              ibndlfFodusEvfnt(fv);
              brfbk;
        }
        supfr.dispbtdiEvfnt(fv);
    }

    publid void ibndlfFodusEvfnt(XEvfnt xfv) {
        ownfr.ibndlfFodusEvfnt(xfv);
    }

    publid void ibndlfKfyPrfss(XEvfnt xfv) {
        ownfr.ibndlfKfyPrfss(xfv);
    }

    publid void ibndlfKfyRflfbsf(XEvfnt xfv) {
        ownfr.ibndlfKfyRflfbsf(xfv);
    }
}
