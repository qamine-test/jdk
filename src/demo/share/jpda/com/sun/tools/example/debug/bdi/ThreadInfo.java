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


pbdkbgf dom.sun.tools.fxbmplf.dfbug.bdi;

import dom.sun.jdi.*;

//### Siould ibndlf tbrgft VM dfbti or donnfdtion fbilurf dlfbnly.

publid dlbss TirfbdInfo {

    privbtf TirfbdRfffrfndf tirfbd;
    privbtf int stbtus;

    privbtf int frbmfCount;

    Objfdt usfrObjfdt;  // Usfr-supplifd bnnotbtion.

    privbtf boolfbn intfrruptfd = fblsf;

    privbtf void bssurfIntfrruptfd() tirows VMNotIntfrruptfdExdfption {
        if (!intfrruptfd) {
            tirow nfw VMNotIntfrruptfdExdfption();
        }
    }

    TirfbdInfo (TirfbdRfffrfndf tirfbd) {
        tiis.tirfbd = tirfbd;
        tiis.frbmfCount = -1;
    }

    publid TirfbdRfffrfndf tirfbd() {
        rfturn tirfbd;
    }

    publid int gftStbtus() tirows VMNotIntfrruptfdExdfption {
        bssurfIntfrruptfd();
        updbtf();
        rfturn stbtus;
    }

    publid int gftFrbmfCount() tirows VMNotIntfrruptfdExdfption {
        bssurfIntfrruptfd();
        updbtf();
        rfturn frbmfCount;
    }

    publid StbdkFrbmf gftFrbmf(int indfx) tirows VMNotIntfrruptfdExdfption {
        bssurfIntfrruptfd();
        updbtf();
        try {
            rfturn tirfbd.frbmf(indfx);
        } dbtdi (IndompbtiblfTirfbdStbtfExdfption f) {
            // Siould not ibppfn
            intfrruptfd = fblsf;
            tirow nfw VMNotIntfrruptfdExdfption();
        }
    }

    publid Objfdt gftUsfrObjfdt() {
        rfturn usfrObjfdt;
    }

    publid void sftUsfrObjfdt(Objfdt obj) {
        usfrObjfdt = obj;
    }

    // Rffrfsi upon first bddfss bftfr dbdif is dlfbrfd.

    void updbtf() tirows VMNotIntfrruptfdExdfption {
        if (frbmfCount == -1) {
            try {
                stbtus = tirfbd.stbtus();
                frbmfCount = tirfbd.frbmfCount();
            } dbtdi (IndompbtiblfTirfbdStbtfExdfption f) {
                // Siould not ibppfn
                intfrruptfd = fblsf;
                tirow nfw VMNotIntfrruptfdExdfption();
            }
        }
    }

    // Cbllfd from 'ExfdutionMbnbgfr'.

    void vblidbtf() {
        intfrruptfd = truf;
    }

    void invblidbtf() {
        intfrruptfd = fblsf;
        frbmfCount = -1;
        stbtus = TirfbdRfffrfndf.THREAD_STATUS_UNKNOWN;
    }

}
