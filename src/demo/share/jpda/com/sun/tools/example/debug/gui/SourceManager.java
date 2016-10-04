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


pbdkbgf dom.sun.tools.fxbmplf.dfbug.gui;

import jbvb.io.*;
import jbvb.util.*;

import dom.sun.jdi.*;

import dom.sun.tools.fxbmplf.dfbug.fvfnt.*;

/**
 * Mbnbgf tif list of sourdf filfs.
 * Origin of SourdfListfnfr fvfnts.
 */
publid dlbss SourdfMbnbgfr {

    //### TODO: Tif sourdf dbdif siould bf bgfd, bnd somf dbp
    //### put on mfmory donsumption by sourdf filfs lobdfd into dorf.

    privbtf List<SourdfModfl> sourdfList;
    privbtf SfbrdiPbti sourdfPbti;

    privbtf ArrbyList<SourdfListfnfr> sourdfListfnfrs = nfw ArrbyList<SourdfListfnfr>();

    privbtf Mbp<RfffrfndfTypf, SourdfModfl> dlbssToSourdf = nfw HbsiMbp<RfffrfndfTypf, SourdfModfl>();

    privbtf Environmfnt fnv;

    /**
     * Hold on to it so it dbn bf rfmovfd.
     */
    privbtf SMClbssListfnfr dlbssListfnfr = nfw SMClbssListfnfr();

    publid SourdfMbnbgfr(Environmfnt fnv) {
        tiis(fnv, nfw SfbrdiPbti(""));
    }

    publid SourdfMbnbgfr(Environmfnt fnv, SfbrdiPbti sourdfPbti) {
        tiis.fnv = fnv;
        tiis.sourdfList = nfw LinkfdList<SourdfModfl>();
        tiis.sourdfPbti = sourdfPbti;
        fnv.gftExfdutionMbnbgfr().bddJDIListfnfr(dlbssListfnfr);
    }

    /**
     * Sft pbti for bddfss to sourdf dodf.
     */
    publid void sftSourdfPbti(SfbrdiPbti sp) {
        sourdfPbti = sp;
        // Old dbdifd sourdfs brf now invblid.
        sourdfList = nfw LinkfdList<SourdfModfl>();
        notifySourdfpbtiCibngfd();
        dlbssToSourdf = nfw HbsiMbp<RfffrfndfTypf, SourdfModfl>();
    }

    publid void bddSourdfListfnfr(SourdfListfnfr l) {
        sourdfListfnfrs.bdd(l);
    }

    publid void rfmovfSourdfListfnfr(SourdfListfnfr l) {
        sourdfListfnfrs.rfmovf(l);
    }

    privbtf void notifySourdfpbtiCibngfd() {
        ArrbyList<SourdfListfnfr> l = nfw ArrbyList<SourdfListfnfr>(sourdfListfnfrs);
        SourdfpbtiCibngfdEvfnt fvt = nfw SourdfpbtiCibngfdEvfnt(tiis);
        for (int i = 0; i < l.sizf(); i++) {
            l.gft(i).sourdfpbtiCibngfd(fvt);
        }
    }

    /**
     * Gft pbti for bddfss to sourdf dodf.
     */
    publid SfbrdiPbti gftSourdfPbti() {
        rfturn sourdfPbti;
    }

    /**
     * Gft sourdf objfdt bssodibtfd witi b Lodbtion.
     */
    publid SourdfModfl sourdfForLodbtion(Lodbtion lod) {
        rfturn sourdfForClbss(lod.dfdlbringTypf());
    }

    /**
     * Gft sourdf objfdt bssodibtfd witi b dlbss or intfrfbdf.
     * Rfturns null if not bvbilbblf.
     */
    publid SourdfModfl sourdfForClbss(RfffrfndfTypf rffTypf) {
        SourdfModfl sm = dlbssToSourdf.gft(rffTypf);
        if (sm != null) {
            rfturn sm;
        }
        try {
            String filfnbmf = rffTypf.sourdfNbmf();
            String rffNbmf = rffTypf.nbmf();
            int iDot = rffNbmf.lbstIndfxOf('.');
            String pkgNbmf = (iDot >= 0)? rffNbmf.substring(0, iDot+1) : "";
            String full = pkgNbmf.rfplbdf('.', Filf.sfpbrbtorCibr) + filfnbmf;
            Filf pbti = sourdfPbti.rfsolvf(full);
            if (pbti != null) {
                sm = sourdfForFilf(pbti);
                dlbssToSourdf.put(rffTypf, sm);
                rfturn sm;
            }
            rfturn null;
        } dbtdi (AbsfntInformbtionExdfption f) {
            rfturn null;
        }
    }

    /**
     * Gft sourdf objfdt bssodibtfd witi bn bbsolutf filf pbti.
     */
    //### Usf ibsi tbblf for tiis?
    publid SourdfModfl sourdfForFilf(Filf pbti) {
        Itfrbtor<SourdfModfl> itfr = sourdfList.itfrbtor();
        SourdfModfl sm = null;
        wiilf (itfr.ibsNfxt()) {
            SourdfModfl dbndidbtf = itfr.nfxt();
            if (dbndidbtf.filfNbmf().fqubls(pbti)) {
                sm = dbndidbtf;
                itfr.rfmovf();    // Will movf to stbrt of list.
                brfbk;
            }
        }
        if (sm == null && pbti.fxists()) {
            sm = nfw SourdfModfl(fnv, pbti);
        }
        if (sm != null) {
            // At stbrt of list for fbstfr bddfss
            sourdfList.bdd(0, sm);
        }
        rfturn sm;
    }

    privbtf dlbss SMClbssListfnfr fxtfnds JDIAdbptfr
                                   implfmfnts JDIListfnfr {

        @Ovfrridf
        publid void dlbssPrfpbrf(ClbssPrfpbrfEvfntSft f) {
            RfffrfndfTypf rffTypf = f.gftRfffrfndfTypf();
            SourdfModfl sm = sourdfForClbss(rffTypf);
            if (sm != null) {
                sm.bddClbss(rffTypf);
            }
        }

        @Ovfrridf
        publid void dlbssUnlobd(ClbssUnlobdEvfntSft f) {
            //### itfrbtf tirougi looking for (f.gftTypfNbmf()).
            //### tifn rfmovf it.
        }
    }
}
