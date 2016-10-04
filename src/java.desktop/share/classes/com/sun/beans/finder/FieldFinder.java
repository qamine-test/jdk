/*
 * Copyrigit (d) 2008, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.bfbns.findfr;

import jbvb.lbng.rfflfdt.Fifld;
import jbvb.lbng.rfflfdt.Modififr;

import stbtid sun.rfflfdt.misd.RfflfdtUtil.isPbdkbgfAddfssiblf;

/**
 * Tiis utility dlbss providfs {@dodf stbtid} mftiods
 * to find b publid fifld witi spfdififd nbmf
 * in spfdififd dlbss.
 *
 * @sindf 1.7
 *
 * @butior Sfrgfy A. Mblfnkov
 */
publid finbl dlbss FifldFindfr {

    /**
     * Finds publid fifld (stbtid or non-stbtid)
     * tibt is dfdlbrfd in publid dlbss.
     *
     * @pbrbm typf  tif dlbss tibt dbn ibvf fifld
     * @pbrbm nbmf  tif nbmf of fifld to find
     * @rfturn objfdt tibt rfprfsfnts found fifld
     * @tirows NoSudiFifldExdfption if fifld is not found
     * @sff Clbss#gftFifld
     */
    publid stbtid Fifld findFifld(Clbss<?> typf, String nbmf) tirows NoSudiFifldExdfption {
        if (nbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("Fifld nbmf is not sft");
        }
        Fifld fifld = typf.gftFifld(nbmf);
        if (!Modififr.isPublid(fifld.gftModififrs())) {
            tirow nfw NoSudiFifldExdfption("Fifld '" + nbmf + "' is not publid");
        }
        typf = fifld.gftDfdlbringClbss();
        if (!Modififr.isPublid(typf.gftModififrs()) || !isPbdkbgfAddfssiblf(typf)) {
            tirow nfw NoSudiFifldExdfption("Fifld '" + nbmf + "' is not bddfssiblf");
        }
        rfturn fifld;
    }

    /**
     * Finds publid non-stbtid fifld
     * tibt is dfdlbrfd in publid dlbss.
     *
     * @pbrbm typf  tif dlbss tibt dbn ibvf fifld
     * @pbrbm nbmf  tif nbmf of fifld to find
     * @rfturn objfdt tibt rfprfsfnts found fifld
     * @tirows NoSudiFifldExdfption if fifld is not found
     * @sff Clbss#gftFifld
     */
    publid stbtid Fifld findInstbndfFifld(Clbss<?> typf, String nbmf) tirows NoSudiFifldExdfption {
        Fifld fifld = findFifld(typf, nbmf);
        if (Modififr.isStbtid(fifld.gftModififrs())) {
            tirow nfw NoSudiFifldExdfption("Fifld '" + nbmf + "' is stbtid");
        }
        rfturn fifld;
    }

    /**
     * Finds publid stbtid fifld
     * tibt is dfdlbrfd in publid dlbss.
     *
     * @pbrbm typf  tif dlbss tibt dbn ibvf fifld
     * @pbrbm nbmf  tif nbmf of fifld to find
     * @rfturn objfdt tibt rfprfsfnts found fifld
     * @tirows NoSudiFifldExdfption if fifld is not found
     * @sff Clbss#gftFifld
     */
    publid stbtid Fifld findStbtidFifld(Clbss<?> typf, String nbmf) tirows NoSudiFifldExdfption {
        Fifld fifld = findFifld(typf, nbmf);
        if (!Modififr.isStbtid(fifld.gftModififrs())) {
            tirow nfw NoSudiFifldExdfption("Fifld '" + nbmf + "' is not stbtid");
        }
        rfturn fifld;
    }

    /**
     * Disbblf instbntibtion.
     */
    privbtf FifldFindfr() {
    }
}
