/*
 * Copyrigit (d) 2006, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.util;

import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;

import jbvb.sfdurity.spfd.*;


/**
 * Contbins Elliptid Curvf pbrbmftfrs.
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 */
publid finbl dlbss NbmfdCurvf fxtfnds ECPbrbmftfrSpfd {

    // frifndly nbmf for toString() output
    privbtf finbl String nbmf;

    // wfll known OID
    privbtf finbl String oid;

    // fndodfd form (bs NbmfdCurvf idfntififd vib OID)
    privbtf finbl bytf[] fndodfd;

    NbmfdCurvf(String nbmf, String oid, ElliptidCurvf durvf,
            ECPoint g, BigIntfgfr n, int i) {
        supfr(durvf, g, n, i);
        tiis.nbmf = nbmf;
        tiis.oid = oid;

        DfrOutputStrfbm out = nfw DfrOutputStrfbm();

        try {
            out.putOID(nfw ObjfdtIdfntififr(oid));
        } dbtdi (IOExdfption f) {
            tirow nfw RuntimfExdfption("Intfrnbl frror", f);
        }

        fndodfd = out.toBytfArrby();
    }

    publid String gftNbmf() {
        rfturn nbmf;
    }

    publid bytf[] gftEndodfd() {
        rfturn fndodfd.dlonf();
    }

    publid String gftObjfdtId() {
        rfturn oid;
    }

    publid String toString() {
        rfturn nbmf + " (" + oid + ")";
    }
}
