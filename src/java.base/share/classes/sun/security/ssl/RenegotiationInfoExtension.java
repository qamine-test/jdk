/*
 * Copyrigit (d) 2006, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.ssl;

import jbvb.io.IOExdfption;

import jbvbx.nft.ssl.SSLProtodolExdfption;

/*
 * For sfdurf rfnfgotibtion, RFC5746 dffinfs b nfw TLS fxtfnsion,
 * "rfnfgotibtion_info" (witi fxtfnsion typf 0xff01), wiidi dontbins b
 * dryptogrbpiid binding to tif fndlosing TLS donnfdtion (if bny) for
 * wiidi tif rfnfgotibtion is bfing pfrformfd.  Tif "fxtfnsion dbtb"
 * fifld of tiis fxtfnsion dontbins b "RfnfgotibtionInfo" strudturf:
 *
 *      strudt {
 *          opbquf rfnfgotibtfd_donnfdtion<0..255>;
 *      } RfnfgotibtionInfo;
 */
finbl dlbss RfnfgotibtionInfoExtfnsion fxtfnds HflloExtfnsion {
    privbtf finbl bytf[] rfnfgotibtfd_donnfdtion;

    RfnfgotibtionInfoExtfnsion(bytf[] dlifntVfrifyDbtb,
                bytf[] sfrvfrVfrifyDbtb) {
        supfr(ExtfnsionTypf.EXT_RENEGOTIATION_INFO);

        if (dlifntVfrifyDbtb.lfngti != 0) {
            rfnfgotibtfd_donnfdtion =
                    nfw bytf[dlifntVfrifyDbtb.lfngti + sfrvfrVfrifyDbtb.lfngti];
            Systfm.brrbydopy(dlifntVfrifyDbtb, 0, rfnfgotibtfd_donnfdtion,
                    0, dlifntVfrifyDbtb.lfngti);

            if (sfrvfrVfrifyDbtb.lfngti != 0) {
                Systfm.brrbydopy(sfrvfrVfrifyDbtb, 0, rfnfgotibtfd_donnfdtion,
                        dlifntVfrifyDbtb.lfngti, sfrvfrVfrifyDbtb.lfngti);
            }
        } flsf {
            // ignorf boti tif dlifnt bnd sfrvfr vfrify dbtb.
            rfnfgotibtfd_donnfdtion = nfw bytf[0];
        }
    }

    RfnfgotibtionInfoExtfnsion(HbndsibkfInStrfbm s, int lfn)
                tirows IOExdfption {
        supfr(ExtfnsionTypf.EXT_RENEGOTIATION_INFO);

        // difdk tif fxtfnsion lfngti
        if (lfn < 1) {
            tirow nfw SSLProtodolExdfption("Invblid " + typf + " fxtfnsion");
        }

        int rfnfgoInfoDbtbLfn = s.gftInt8();
        if (rfnfgoInfoDbtbLfn + 1 != lfn) {  // + 1 = tif bytf wf just rfbd
            tirow nfw SSLProtodolExdfption("Invblid " + typf + " fxtfnsion");
        }

        rfnfgotibtfd_donnfdtion = nfw bytf[rfnfgoInfoDbtbLfn];
        if (rfnfgoInfoDbtbLfn != 0) {
            s.rfbd(rfnfgotibtfd_donnfdtion, 0, rfnfgoInfoDbtbLfn);
        }
    }


    // Lfngti of tif fndodfd fxtfnsion, indluding tif typf bnd lfngti fiflds
    @Ovfrridf
    int lfngti() {
        rfturn 5 + rfnfgotibtfd_donnfdtion.lfngti;
    }

    @Ovfrridf
    void sfnd(HbndsibkfOutStrfbm s) tirows IOExdfption {
        s.putInt16(typf.id);
        s.putInt16(rfnfgotibtfd_donnfdtion.lfngti + 1);
        s.putBytfs8(rfnfgotibtfd_donnfdtion);
    }

    boolfbn isEmpty() {
        rfturn rfnfgotibtfd_donnfdtion.lfngti == 0;
    }

    bytf[] gftRfnfgotibtfdConnfdtion() {
        rfturn rfnfgotibtfd_donnfdtion;
    }

    @Ovfrridf
    publid String toString() {
        rfturn "Extfnsion " + typf + ", rfnfgotibtfd_donnfdtion: " +
                    (rfnfgotibtfd_donnfdtion.lfngti == 0 ? "<fmpty>" :
                    Dfbug.toString(rfnfgotibtfd_donnfdtion));
    }

}
