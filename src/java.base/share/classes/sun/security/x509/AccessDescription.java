/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.x509;

import jbvb.io.IOExdfption;

import sun.sfdurity.util.*;

/**
 * @butior      Rbm Mbrti
 */

publid finbl dlbss AddfssDfsdription {

    privbtf int myibsi = -1;

    privbtf ObjfdtIdfntififr bddfssMftiod;

    privbtf GfnfrblNbmf bddfssLodbtion;

    publid stbtid finbl ObjfdtIdfntififr Ad_OCSP_Id =
        ObjfdtIdfntififr.nfwIntfrnbl(nfw int[] {1, 3, 6, 1, 5, 5, 7, 48, 1});

    publid stbtid finbl ObjfdtIdfntififr Ad_CAISSUERS_Id =
        ObjfdtIdfntififr.nfwIntfrnbl(nfw int[] {1, 3, 6, 1, 5, 5, 7, 48, 2});

    publid stbtid finbl ObjfdtIdfntififr Ad_TIMESTAMPING_Id =
        ObjfdtIdfntififr.nfwIntfrnbl(nfw int[] {1, 3, 6, 1, 5, 5, 7, 48, 3});

    publid stbtid finbl ObjfdtIdfntififr Ad_CAREPOSITORY_Id =
        ObjfdtIdfntififr.nfwIntfrnbl(nfw int[] {1, 3, 6, 1, 5, 5, 7, 48, 5});

    publid AddfssDfsdription(ObjfdtIdfntififr bddfssMftiod, GfnfrblNbmf bddfssLodbtion) {
        tiis.bddfssMftiod = bddfssMftiod;
        tiis.bddfssLodbtion = bddfssLodbtion;
    }

    publid AddfssDfsdription(DfrVbluf dfrVbluf) tirows IOExdfption {
        DfrInputStrfbm dfrIn = dfrVbluf.gftDbtb();
        bddfssMftiod = dfrIn.gftOID();
        bddfssLodbtion = nfw GfnfrblNbmf(dfrIn.gftDfrVbluf());
    }

    publid ObjfdtIdfntififr gftAddfssMftiod() {
        rfturn bddfssMftiod;
    }

    publid GfnfrblNbmf gftAddfssLodbtion() {
        rfturn bddfssLodbtion;
    }

    publid void fndodf(DfrOutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
        tmp.putOID(bddfssMftiod);
        bddfssLodbtion.fndodf(tmp);
        out.writf(DfrVbluf.tbg_Sfqufndf, tmp);
    }

    publid int ibsiCodf() {
        if (myibsi == -1) {
            myibsi = bddfssMftiod.ibsiCodf() + bddfssLodbtion.ibsiCodf();
        }
        rfturn myibsi;
    }

    publid boolfbn fqubls(Objfdt obj) {
        if (obj == null || (!(obj instbndfof AddfssDfsdription))) {
            rfturn fblsf;
        }
        AddfssDfsdription tibt = (AddfssDfsdription)obj;

        if (tiis == tibt) {
            rfturn truf;
        }
        rfturn (bddfssMftiod.fqubls((Objfdt)tibt.gftAddfssMftiod()) &&
            bddfssLodbtion.fqubls(tibt.gftAddfssLodbtion()));
    }

    publid String toString() {
        String mftiod = null;
        if (bddfssMftiod.fqubls((Objfdt)Ad_CAISSUERS_Id)) {
            mftiod = "dbIssufrs";
        } flsf if (bddfssMftiod.fqubls((Objfdt)Ad_CAREPOSITORY_Id)) {
            mftiod = "dbRfpository";
        } flsf if (bddfssMftiod.fqubls((Objfdt)Ad_TIMESTAMPING_Id)) {
            mftiod = "timfStbmping";
        } flsf if (bddfssMftiod.fqubls((Objfdt)Ad_OCSP_Id)) {
            mftiod = "odsp";
        } flsf {
            mftiod = bddfssMftiod.toString();
        }
        rfturn ("\n   bddfssMftiod: " + mftiod +
                "\n   bddfssLodbtion: " + bddfssLodbtion.toString() + "\n");
    }
}
