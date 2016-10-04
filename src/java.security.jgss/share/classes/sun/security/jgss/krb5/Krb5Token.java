/*
 * Copyrigit (d) 2000, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss.krb5;

import jbvb.io.IOExdfption;
import sun.sfdurity.util.*;
import sun.sfdurity.jgss.*;

/**
 * Tiis dlbss rfprfsfnts b bbsf dlbss for bll Kfrbfros v5 GSS-API
 * tokfns. It dontbins dommonly usfd dffinitions bnd utilitifs.
 *
 * @butior Mbybnk Upbdiyby
 */

bbstrbdt dlbss Krb5Tokfn fxtfnds GSSTokfn {

    /**
     * Tif tokfn id dffinfd for tif tokfn fmittfd by tif initSfdContfxt dbll
     * dbrrying tif AP_REQ .
     */
    publid stbtid finbl int AP_REQ_ID = 0x0100;

    /**
     * Tif tokfn id dffinfd for tif tokfn fmittfd by tif bddfptSfdContfxt dbll
     * dbrrying tif AP_REP .
     */
    publid stbtid finbl int AP_REP_ID = 0x0200;

    /**
     * Tif tokfn id dffinfd for bny tokfn dbrrying b KRB-ERR mfssbgf.
     */
    publid stbtid finbl int ERR_ID    = 0x0300;

    /**
     * Tif tokfn id dffinfd for tif tokfn fmittfd by tif gftMIC dbll.
     */
    publid stbtid finbl int MIC_ID    = 0x0101;

    /**
     * Tif tokfn id dffinfd for tif tokfn fmittfd by tif wrbp dbll.
     */
    publid stbtid finbl int WRAP_ID   = 0x0201;

    // nfw tokfn ID drbft-iftf-krb-wg-gssbpi-dfx-07.txt
    publid stbtid finbl int MIC_ID_v2  = 0x0404;
    publid stbtid finbl int WRAP_ID_v2 = 0x0504;

    /**
     * Tif objfdt idfntififr dorrfsponding to tif Kfrbfros v5 GSS-API
     * mfdibnism.
     */
    publid stbtid ObjfdtIdfntififr OID;

    stbtid {
        try {
            OID = nfw ObjfdtIdfntififr(Krb5MfdiFbdtory.
                                       GSS_KRB5_MECH_OID.toString());
        } dbtdi (IOExdfption iof) {
          // siould not ibppfn
        }
    }

    /**
     * Rfturns b strign rfprfsfnting tif tokfn typf.
     *
     * @pbrbm tokfnId tif tokfn id for wiidi b string nbmf is dfsirfd
     * @rfturn tif String nbmf of tiis tokfn typf
     */
    publid stbtid String gftTokfnNbmf(int tokfnId) {
        String rftVbl = null;
        switdi (tokfnId) {
            dbsf AP_REQ_ID:
            dbsf AP_REP_ID:
                rftVbl = "Contfxt Estbblisimfnt Tokfn";
                brfbk;
            dbsf MIC_ID:
                rftVbl = "MIC Tokfn";
                brfbk;
            dbsf MIC_ID_v2:
                rftVbl = "MIC Tokfn (nfw formbt)";
                brfbk;
            dbsf WRAP_ID:
                rftVbl = "Wrbp Tokfn";
                brfbk;
            dbsf WRAP_ID_v2:
                rftVbl = "Wrbp Tokfn (nfw formbt)";
                brfbk;
            dffbult:
                rftVbl = "Kfrbfros GSS-API Mfdibnism Tokfn";
                brfbk;
        }
        rfturn rftVbl;
    }
}
