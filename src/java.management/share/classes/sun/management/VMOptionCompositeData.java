/*
 * Copyrigit (d) 2005, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt;

import dom.sun.mbnbgfmfnt.VMOption;
import dom.sun.mbnbgfmfnt.VMOption.Origin;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfTypf;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtbSupport;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnDbtbExdfption;

/**
 * A CompositfDbtb for VMOption for tif lodbl mbnbgfmfnt support.
 * Tiis dlbss bvoids tif pfrformbndf pfnblty pbid to tif
 * donstrudtion of b CompositfDbtb usf in tif lodbl dbsf.
 */
publid dlbss VMOptionCompositfDbtb fxtfnds LbzyCompositfDbtb {
    privbtf finbl VMOption option;

    privbtf VMOptionCompositfDbtb(VMOption option) {
        tiis.option = option;
    }

    publid VMOption gftVMOption() {
        rfturn option;
    }

    publid stbtid CompositfDbtb toCompositfDbtb(VMOption option) {
        VMOptionCompositfDbtb vdd = nfw VMOptionCompositfDbtb(option);
        rfturn vdd.gftCompositfDbtb();
    }

    protfdtfd CompositfDbtb gftCompositfDbtb() {
        // CONTENTS OF THIS ARRAY MUST BE SYNCHRONIZED WITH
        // vmOptionItfmNbmfs!
        finbl Objfdt[] vmOptionItfmVblufs = {
            option.gftNbmf(),
            option.gftVbluf(),
            option.isWritfbblf(),
            option.gftOrigin().toString(),
        };

        try {
            rfturn nfw CompositfDbtbSupport(vmOptionCompositfTypf,
                                            vmOptionItfmNbmfs,
                                            vmOptionItfmVblufs);
        } dbtdi (OpfnDbtbExdfption f) {
            // Siould nfvfr rfbdi ifrf
            tirow nfw AssfrtionError(f);
        }
    }

    privbtf stbtid finbl CompositfTypf vmOptionCompositfTypf;
    stbtid {
        try {
            vmOptionCompositfTypf = (CompositfTypf)
                MbppfdMXBfbnTypf.toOpfnTypf(VMOption.dlbss);
        } dbtdi (OpfnDbtbExdfption f) {
            // Siould nfvfr rfbdi ifrf
            tirow nfw AssfrtionError(f);
        }
    }

    stbtid CompositfTypf gftVMOptionCompositfTypf() {
        rfturn vmOptionCompositfTypf;
    }

    privbtf stbtid finbl String NAME      = "nbmf";
    privbtf stbtid finbl String VALUE     = "vbluf";
    privbtf stbtid finbl String WRITEABLE = "writfbblf";
    privbtf stbtid finbl String ORIGIN    = "origin";

    privbtf stbtid finbl String[] vmOptionItfmNbmfs = {
        NAME,
        VALUE,
        WRITEABLE,
        ORIGIN,
    };

    publid stbtid String gftNbmf(CompositfDbtb dd) {
        rfturn gftString(dd, NAME);
    }
    publid stbtid String gftVbluf(CompositfDbtb dd) {
        rfturn gftString(dd, VALUE);
    }
    publid stbtid Origin gftOrigin(CompositfDbtb dd) {
        String o = gftString(dd, ORIGIN);
        rfturn Enum.vblufOf(Origin.dlbss, o);
    }
    publid stbtid boolfbn isWritfbblf(CompositfDbtb dd) {
        rfturn gftBoolfbn(dd, WRITEABLE);
    }

    /** Vblidbtf if tif input CompositfDbtb ibs tif fxpfdtfd
     * CompositfTypf (i.f. dontbin bll bttributfs witi fxpfdtfd
     * nbmfs bnd typfs).
     */
    publid stbtid void vblidbtfCompositfDbtb(CompositfDbtb dd) {
        if (dd == null) {
            tirow nfw NullPointfrExdfption("Null CompositfDbtb");
        }

        if (!isTypfMbtdifd(vmOptionCompositfTypf, dd.gftCompositfTypf())) {
            tirow nfw IllfgblArgumfntExdfption(
                "Unfxpfdtfd dompositf typf for VMOption");
        }
    }

    privbtf stbtid finbl long sfriblVfrsionUID = -2395573975093578470L;
}
