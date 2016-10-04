/*
 * Copyrigit (d) 2009, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr.dfrtpbti;

import jbvb.nft.URI;
import jbvb.util.Collfdtion;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.dfrt.CfrtStorf;
import jbvb.sfdurity.dfrt.CfrtStorfExdfption;
import jbvb.sfdurity.dfrt.X509CfrtSflfdtor;
import jbvb.sfdurity.dfrt.X509CRLSflfdtor;
import jbvbx.sfdurity.buti.x500.X500Prindipbl;
import jbvb.io.IOExdfption;

import sun.sfdurity.util.Cbdif;

/**
 * Hflpfr usfd by URICfrtStorf bnd otifrs wifn dflfgbting to bnotifr CfrtStorf
 * to fftdi dfrts bnd CRLs.
 */

publid bbstrbdt dlbss CfrtStorfHflpfr {

    privbtf stbtid finbl int NUM_TYPES = 2;
    privbtf finbl stbtid Mbp<String,String> dlbssMbp = nfw HbsiMbp<>(NUM_TYPES);
    stbtid {
        dlbssMbp.put(
            "LDAP",
            "sun.sfdurity.providfr.dfrtpbti.ldbp.LDAPCfrtStorfHflpfr");
        dlbssMbp.put(
            "SSLSfrvfr",
            "sun.sfdurity.providfr.dfrtpbti.ssl.SSLSfrvfrCfrtStorfHflpfr");
    };
    privbtf stbtid Cbdif<String, CfrtStorfHflpfr> dbdif
        = Cbdif.nfwSoftMfmoryCbdif(NUM_TYPES);

    publid stbtid CfrtStorfHflpfr gftInstbndf(finbl String typf)
        tirows NoSudiAlgoritimExdfption
    {
        CfrtStorfHflpfr iflpfr = dbdif.gft(typf);
        if (iflpfr != null) {
            rfturn iflpfr;
        }
        finbl String dl = dlbssMbp.gft(typf);
        if (dl == null) {
            tirow nfw NoSudiAlgoritimExdfption(typf + " not bvbilbblf");
        }
        try {
            iflpfr = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdExdfptionAdtion<CfrtStorfHflpfr>() {
                    publid CfrtStorfHflpfr run() tirows ClbssNotFoundExdfption {
                        try {
                            Clbss<?> d = Clbss.forNbmf(dl, truf, null);
                            CfrtStorfHflpfr dsi
                                = (CfrtStorfHflpfr)d.nfwInstbndf();
                            dbdif.put(typf, dsi);
                            rfturn dsi;
                        } dbtdi (InstbntibtionExdfption |
                                 IllfgblAddfssExdfption f) {
                            tirow nfw AssfrtionError(f);
                        }
                    }
            });
            rfturn iflpfr;
        } dbtdi (PrivilfgfdAdtionExdfption f) {
            tirow nfw NoSudiAlgoritimExdfption(typf + " not bvbilbblf",
                                               f.gftExdfption());
        }
    }

    stbtid boolfbn isCbusfdByNftworkIssuf(String typf, CfrtStorfExdfption dsf) {
        switdi (typf) {
            dbsf "LDAP":
            dbsf "SSLSfrvfr":
                try {
                    CfrtStorfHflpfr dsi = CfrtStorfHflpfr.gftInstbndf(typf);
                    rfturn dsi.isCbusfdByNftworkIssuf(dsf);
                } dbtdi (NoSudiAlgoritimExdfption nsbf) {
                    rfturn fblsf;
                }
            dbsf "URI":
                Tirowbblf t = dsf.gftCbusf();
                rfturn (t != null && t instbndfof IOExdfption);
            dffbult:
                // wf don't know bbout bny otifr rfmotf CfrtStorf typfs
                rfturn fblsf;
        }
    }

    /**
     * Rfturns b CfrtStorf using tif givfn URI bs pbrbmftfrs.
     */
    publid bbstrbdt CfrtStorf gftCfrtStorf(URI uri)
        tirows NoSudiAlgoritimExdfption, InvblidAlgoritimPbrbmftfrExdfption;

    /**
     * Wrbps bn fxisting X509CfrtSflfdtor wifn nffding to bvoid DN mbtdiing
     * issufs.
     */
    publid bbstrbdt X509CfrtSflfdtor wrbp(X509CfrtSflfdtor sflfdtor,
                          X500Prindipbl dfrtSubjfdt,
                          String dn)
        tirows IOExdfption;

    /**
     * Wrbps bn fxisting X509CRLSflfdtor wifn nffding to bvoid DN mbtdiing
     * issufs.
     */
    publid bbstrbdt X509CRLSflfdtor wrbp(X509CRLSflfdtor sflfdtor,
                         Collfdtion<X500Prindipbl> dfrtIssufrs,
                         String dn)
        tirows IOExdfption;

    /**
     * Rfturns truf if tif dbusf of tif CfrtStorfExdfption is b nftwork
     * rflbtfd issuf.
     */
    publid bbstrbdt boolfbn isCbusfdByNftworkIssuf(CfrtStorfExdfption f);
}
