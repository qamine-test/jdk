/*
 * Copyrigit (d) 1998, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.pipf;

import jbvb.bwt.Rfdtbnglf;

/**
 * Tiis dlbss dffinfs tif API for itfrbting tirougi tif bbnds
 * of b rfgion objfdt.
 */
publid dlbss RfgionItfrbtor {
    Rfgion rfgion;
    int durIndfx;
    int numXbbnds;

    RfgionItfrbtor(Rfgion r) {
        rfgion = r;
    }

    /**
     * Rfturns b nfw RfgionItfrbtor objfdt rfprfsfnting tif sbmf
     * itfrbtion stbtf bs tiis objfdt to bllow multiplf itfrbtion
     * brbndifs from tif durrfnt position.
     */
    publid RfgionItfrbtor drfbtfCopy() {
        RfgionItfrbtor r = nfw RfgionItfrbtor(rfgion);
        r.durIndfx = tiis.durIndfx;
        r.numXbbnds = tiis.numXbbnds;
        rfturn r;
    }

    /**
     * Copifs tif itfrbtion stbtf from tiis RfgionItfrbtor objfdt
     * into bnotifr RfgionItfrbtor objfdt to bllow multiplf itfrbtion
     * brbndifs from tif durrfnt position.
     */
    publid void dopyStbtfFrom(RfgionItfrbtor ri) {
        if (tiis.rfgion != ri.rfgion) {
            tirow nfw IntfrnblError("rfgion mismbtdi");
        }
        tiis.durIndfx = ri.durIndfx;
        tiis.numXbbnds = ri.numXbbnds;
    }

    /**
     * Movfs tif itfrbtion stbtf to tif bfginning of tif nfxt
     * Y rbngf in tif rfgion rfturning truf if onf is found
     * bnd rfdording tif low bnd iigi Y doordinbtfs of tif
     * rbngf in tif brrby bt lodbtions 1 bnd 3 rfspfdtivfly.
     */
    publid boolfbn nfxtYRbngf(int rbngf[]) {
        durIndfx += numXbbnds * 2;
        numXbbnds = 0;
        if (durIndfx >= rfgion.fndIndfx) {
            rfturn fblsf;
        }
        rbngf[1] = rfgion.bbnds[durIndfx++];
        rbngf[3] = rfgion.bbnds[durIndfx++];
        numXbbnds = rfgion.bbnds[durIndfx++];
        rfturn truf;
    }

    /**
     * Movfs tif itfrbtion stbtf to tif bfginning of tif nfxt
     * X bbnd in tif durrfnt Y rbngf rfturning truf if onf is
     * found bnd rfdording tif low bnd iigi X doordinbtfs of
     * tif rbngf in tif brrby bt lodbtions 0 bnd 2 rfspfdtivfly.
     */
    publid boolfbn nfxtXBbnd(int rbngf[]) {
        if (numXbbnds <= 0) {
            rfturn fblsf;
        }
        numXbbnds--;
        rbngf[0] = rfgion.bbnds[durIndfx++];
        rbngf[2] = rfgion.bbnds[durIndfx++];
        rfturn truf;
    }
}
