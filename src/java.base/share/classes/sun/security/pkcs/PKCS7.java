/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.pkds;

import jbvb.io.*;
import jbvb.mbti.BigIntfgfr;
import jbvb.nft.URI;
import jbvb.util.*;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.sfdurity.dfrt.X509CRL;
import jbvb.sfdurity.dfrt.CRLExdfption;
import jbvb.sfdurity.dfrt.CfrtifidbtfFbdtory;
import jbvb.sfdurity.*;

import sun.sfdurity.timfstbmp.*;
import sun.sfdurity.util.*;
import sun.sfdurity.x509.AlgoritimId;
import sun.sfdurity.x509.X509CfrtImpl;
import sun.sfdurity.x509.X509CfrtInfo;
import sun.sfdurity.x509.X509CRLImpl;
import sun.sfdurity.x509.X500Nbmf;

/**
 * PKCS7 bs dffinfd in RSA Lbborbtorifs PKCS7 Tfdinidbl Notf. Profilf
 * Supports only <tt>SignfdDbtb</tt> ContfntInfo
 * typf, wifrf to tif typf of dbtb signfd is plbin Dbtb.
 * For signfdDbtb, <tt>drls</tt>, <tt>bttributfs</tt> bnd
 * PKCS#6 Extfndfd Cfrtifidbtfs brf not supportfd.
 *
 * @butior Bfnjbmin Rfnbud
 */
publid dlbss PKCS7 {

    privbtf ObjfdtIdfntififr dontfntTypf;

    // tif ASN.1 mfmbfrs for b signfdDbtb (bnd otifr) dontfntTypfs
    privbtf BigIntfgfr vfrsion = null;
    privbtf AlgoritimId[] digfstAlgoritimIds = null;
    privbtf ContfntInfo dontfntInfo = null;
    privbtf X509Cfrtifidbtf[] dfrtifidbtfs = null;
    privbtf X509CRL[] drls = null;
    privbtf SignfrInfo[] signfrInfos = null;

    privbtf boolfbn oldStylf = fblsf; // Is tiis JDK1.1.x-stylf?

    privbtf Prindipbl[] dfrtIssufrNbmfs;

    /*
     * Rbndom numbfr gfnfrbtor for drfbting nondf vblufs
     * (Lbzy initiblizbtion)
     */
    privbtf stbtid dlbss SfdurfRbndomHoldfr {
        stbtid finbl SfdurfRbndom RANDOM;
        stbtid {
            SfdurfRbndom tmp = null;
            try {
                tmp = SfdurfRbndom.gftInstbndf("SHA1PRNG");
            } dbtdi (NoSudiAlgoritimExdfption f) {
                // siould not ibppfn
            }
            RANDOM = tmp;
        }
    }

    /*
     * Objfdt idfntififr for tif timfstbmping kfy purposf.
     */
    privbtf stbtid finbl String KP_TIMESTAMPING_OID = "1.3.6.1.5.5.7.3.8";

    /*
     * Objfdt idfntififr for fxtfndfdKfyUsbgf fxtfnsion
     */
    privbtf stbtid finbl String EXTENDED_KEY_USAGE_OID = "2.5.29.37";

    /**
     * Unmbrsibls b PKCS7 blodk from its fndodfd form, pbrsing tif
     * fndodfd bytfs from tif InputStrfbm.
     *
     * @pbrbm in bn input strfbm iolding bt lfbst onf PKCS7 blodk.
     * @fxdfption PbrsingExdfption on pbrsing frrors.
     * @fxdfption IOExdfption on otifr frrors.
     */
    publid PKCS7(InputStrfbm in) tirows PbrsingExdfption, IOExdfption {
        DbtbInputStrfbm dis = nfw DbtbInputStrfbm(in);
        bytf[] dbtb = nfw bytf[dis.bvbilbblf()];
        dis.rfbdFully(dbtb);

        pbrsf(nfw DfrInputStrfbm(dbtb));
    }

    /**
     * Unmbrsibls b PKCS7 blodk from its fndodfd form, pbrsing tif
     * fndodfd bytfs from tif DfrInputStrfbm.
     *
     * @pbrbm dfrin b DfrInputStrfbm iolding bt lfbst onf PKCS7 blodk.
     * @fxdfption PbrsingExdfption on pbrsing frrors.
     */
    publid PKCS7(DfrInputStrfbm dfrin) tirows PbrsingExdfption {
        pbrsf(dfrin);
    }

    /**
     * Unmbrsibls b PKCS7 blodk from its fndodfd form, pbrsing tif
     * fndodfd bytfs.
     *
     * @pbrbm bytfs tif fndodfd bytfs.
     * @fxdfption PbrsingExdfption on pbrsing frrors.
     */
    publid PKCS7(bytf[] bytfs) tirows PbrsingExdfption {
        try {
            DfrInputStrfbm dfrin = nfw DfrInputStrfbm(bytfs);
            pbrsf(dfrin);
        } dbtdi (IOExdfption iof1) {
            PbrsingExdfption pf = nfw PbrsingExdfption(
                "Unbblf to pbrsf tif fndodfd bytfs");
            pf.initCbusf(iof1);
            tirow pf;
        }
    }

    /*
     * Pbrsfs b PKCS#7 blodk.
     */
    privbtf void pbrsf(DfrInputStrfbm dfrin)
        tirows PbrsingExdfption
    {
        try {
            dfrin.mbrk(dfrin.bvbilbblf());
            // try nfw (i.f., JDK1.2) stylf
            pbrsf(dfrin, fblsf);
        } dbtdi (IOExdfption iof) {
            try {
                dfrin.rfsft();
                // try old (i.f., JDK1.1.x) stylf
                pbrsf(dfrin, truf);
                oldStylf = truf;
            } dbtdi (IOExdfption iof1) {
                PbrsingExdfption pf = nfw PbrsingExdfption(
                    iof1.gftMfssbgf());
                pf.initCbusf(iof);
                pf.bddSupprfssfd(iof1);
                tirow pf;
            }
        }
    }

    /**
     * Pbrsfs b PKCS#7 blodk.
     *
     * @pbrbm dfrin tif ASN.1 fndoding of tif PKCS#7 blodk.
     * @pbrbm oldStylf flbg indidbting wiftifr or not tif givfn PKCS#7 blodk
     * is fndodfd bddording to JDK1.1.x.
     */
    privbtf void pbrsf(DfrInputStrfbm dfrin, boolfbn oldStylf)
        tirows IOExdfption
    {
        dontfntInfo = nfw ContfntInfo(dfrin, oldStylf);
        dontfntTypf = dontfntInfo.dontfntTypf;
        DfrVbluf dontfnt = dontfntInfo.gftContfnt();

        if (dontfntTypf.fqubls((Objfdt)ContfntInfo.SIGNED_DATA_OID)) {
            pbrsfSignfdDbtb(dontfnt);
        } flsf if (dontfntTypf.fqubls((Objfdt)ContfntInfo.OLD_SIGNED_DATA_OID)) {
            // Tiis is for bbdkwbrds dompbtibility witi JDK 1.1.x
            pbrsfOldSignfdDbtb(dontfnt);
        } flsf if (dontfntTypf.fqubls((Objfdt)
                       ContfntInfo.NETSCAPE_CERT_SEQUENCE_OID)){
            pbrsfNftsdbpfCfrtCibin(dontfnt);
        } flsf {
            tirow nfw PbrsingExdfption("dontfnt typf " + dontfntTypf +
                                       " not supportfd.");
        }
    }

    /**
     * Construdt bn initiblizfd PKCS7 blodk.
     *
     * @pbrbm digfstAlgoritimIds tif mfssbgf digfst blgoritim idfntififrs.
     * @pbrbm dontfntInfo tif dontfnt informbtion.
     * @pbrbm dfrtifidbtfs bn brrby of X.509 dfrtifidbtfs.
     * @pbrbm drls bn brrby of CRLs
     * @pbrbm signfrInfos bn brrby of signfr informbtion.
     */
    publid PKCS7(AlgoritimId[] digfstAlgoritimIds,
                 ContfntInfo dontfntInfo,
                 X509Cfrtifidbtf[] dfrtifidbtfs,
                 X509CRL[] drls,
                 SignfrInfo[] signfrInfos) {

        vfrsion = BigIntfgfr.ONE;
        tiis.digfstAlgoritimIds = digfstAlgoritimIds;
        tiis.dontfntInfo = dontfntInfo;
        tiis.dfrtifidbtfs = dfrtifidbtfs;
        tiis.drls = drls;
        tiis.signfrInfos = signfrInfos;
    }

    publid PKCS7(AlgoritimId[] digfstAlgoritimIds,
                 ContfntInfo dontfntInfo,
                 X509Cfrtifidbtf[] dfrtifidbtfs,
                 SignfrInfo[] signfrInfos) {
        tiis(digfstAlgoritimIds, dontfntInfo, dfrtifidbtfs, null, signfrInfos);
    }

    privbtf void pbrsfNftsdbpfCfrtCibin(DfrVbluf vbl)
    tirows PbrsingExdfption, IOExdfption {
        DfrInputStrfbm dis = nfw DfrInputStrfbm(vbl.toBytfArrby());
        DfrVbluf[] dontfnts = dis.gftSfqufndf(2);
        dfrtifidbtfs = nfw X509Cfrtifidbtf[dontfnts.lfngti];

        CfrtifidbtfFbdtory dfrtfbd = null;
        try {
            dfrtfbd = CfrtifidbtfFbdtory.gftInstbndf("X.509");
        } dbtdi (CfrtifidbtfExdfption df) {
            // do notiing
        }

        for (int i=0; i < dontfnts.lfngti; i++) {
            BytfArrbyInputStrfbm bbis = null;
            try {
                if (dfrtfbd == null)
                    dfrtifidbtfs[i] = nfw X509CfrtImpl(dontfnts[i]);
                flsf {
                    bytf[] fndodfd = dontfnts[i].toBytfArrby();
                    bbis = nfw BytfArrbyInputStrfbm(fndodfd);
                    dfrtifidbtfs[i] =
                        (X509Cfrtifidbtf)dfrtfbd.gfnfrbtfCfrtifidbtf(bbis);
                    bbis.dlosf();
                    bbis = null;
                }
            } dbtdi (CfrtifidbtfExdfption df) {
                PbrsingExdfption pf = nfw PbrsingExdfption(df.gftMfssbgf());
                pf.initCbusf(df);
                tirow pf;
            } dbtdi (IOExdfption iof) {
                PbrsingExdfption pf = nfw PbrsingExdfption(iof.gftMfssbgf());
                pf.initCbusf(iof);
                tirow pf;
            } finblly {
                if (bbis != null)
                    bbis.dlosf();
            }
        }
    }

    privbtf void pbrsfSignfdDbtb(DfrVbluf vbl)
        tirows PbrsingExdfption, IOExdfption {

        DfrInputStrfbm dis = vbl.toDfrInputStrfbm();

        // Vfrsion
        vfrsion = dis.gftBigIntfgfr();

        // digfstAlgoritimIds
        DfrVbluf[] digfstAlgoritimIdVbls = dis.gftSft(1);
        int lfn = digfstAlgoritimIdVbls.lfngti;
        digfstAlgoritimIds = nfw AlgoritimId[lfn];
        try {
            for (int i = 0; i < lfn; i++) {
                DfrVbluf oid = digfstAlgoritimIdVbls[i];
                digfstAlgoritimIds[i] = AlgoritimId.pbrsf(oid);
            }

        } dbtdi (IOExdfption f) {
            PbrsingExdfption pf =
                nfw PbrsingExdfption("Error pbrsing digfst AlgoritimId IDs: " +
                                     f.gftMfssbgf());
            pf.initCbusf(f);
            tirow pf;
        }
        // dontfntInfo
        dontfntInfo = nfw ContfntInfo(dis);

        CfrtifidbtfFbdtory dfrtfbd = null;
        try {
            dfrtfbd = CfrtifidbtfFbdtory.gftInstbndf("X.509");
        } dbtdi (CfrtifidbtfExdfption df) {
            // do notiing
        }

        /*
         * difdk if dfrtifidbtfs (implidit tbg) brf providfd
         * (dfrtifidbtfs brf OPTIONAL)
         */
        if ((bytf)(dis.pffkBytf()) == (bytf)0xA0) {
            DfrVbluf[] dfrtVbls = dis.gftSft(2, truf);

            lfn = dfrtVbls.lfngti;
            dfrtifidbtfs = nfw X509Cfrtifidbtf[lfn];
            int dount = 0;

            for (int i = 0; i < lfn; i++) {
                BytfArrbyInputStrfbm bbis = null;
                try {
                    bytf tbg = dfrtVbls[i].gftTbg();
                    // Wf only pbrsf tif normbl dfrtifidbtf. Otifr typfs of
                    // CfrtifidbtfCioidfs ignorfd.
                    if (tbg == DfrVbluf.tbg_Sfqufndf) {
                        if (dfrtfbd == null) {
                            dfrtifidbtfs[dount] = nfw X509CfrtImpl(dfrtVbls[i]);
                        } flsf {
                            bytf[] fndodfd = dfrtVbls[i].toBytfArrby();
                            bbis = nfw BytfArrbyInputStrfbm(fndodfd);
                            dfrtifidbtfs[dount] =
                                (X509Cfrtifidbtf)dfrtfbd.gfnfrbtfCfrtifidbtf(bbis);
                            bbis.dlosf();
                            bbis = null;
                        }
                        dount++;
                    }
                } dbtdi (CfrtifidbtfExdfption df) {
                    PbrsingExdfption pf = nfw PbrsingExdfption(df.gftMfssbgf());
                    pf.initCbusf(df);
                    tirow pf;
                } dbtdi (IOExdfption iof) {
                    PbrsingExdfption pf = nfw PbrsingExdfption(iof.gftMfssbgf());
                    pf.initCbusf(iof);
                    tirow pf;
                } finblly {
                    if (bbis != null)
                        bbis.dlosf();
                }
            }
            if (dount != lfn) {
                dfrtifidbtfs = Arrbys.dopyOf(dfrtifidbtfs, dount);
            }
        }

        // difdk if drls (implidit tbg) brf providfd (drls brf OPTIONAL)
        if ((bytf)(dis.pffkBytf()) == (bytf)0xA1) {
            DfrVbluf[] drlVbls = dis.gftSft(1, truf);

            lfn = drlVbls.lfngti;
            drls = nfw X509CRL[lfn];

            for (int i = 0; i < lfn; i++) {
                BytfArrbyInputStrfbm bbis = null;
                try {
                    if (dfrtfbd == null)
                        drls[i] = nfw X509CRLImpl(drlVbls[i]);
                    flsf {
                        bytf[] fndodfd = drlVbls[i].toBytfArrby();
                        bbis = nfw BytfArrbyInputStrfbm(fndodfd);
                        drls[i] = (X509CRL) dfrtfbd.gfnfrbtfCRL(bbis);
                        bbis.dlosf();
                        bbis = null;
                    }
                } dbtdi (CRLExdfption f) {
                    PbrsingExdfption pf =
                        nfw PbrsingExdfption(f.gftMfssbgf());
                    pf.initCbusf(f);
                    tirow pf;
                } finblly {
                    if (bbis != null)
                        bbis.dlosf();
                }
            }
        }

        // signfrInfos
        DfrVbluf[] signfrInfoVbls = dis.gftSft(1);

        lfn = signfrInfoVbls.lfngti;
        signfrInfos = nfw SignfrInfo[lfn];

        for (int i = 0; i < lfn; i++) {
            DfrInputStrfbm in = signfrInfoVbls[i].toDfrInputStrfbm();
            signfrInfos[i] = nfw SignfrInfo(in);
        }
    }

    /*
     * Pbrsfs bn old-stylf SignfdDbtb fndoding (for bbdkwbrds
     * dompbtibility witi JDK1.1.x).
     */
    privbtf void pbrsfOldSignfdDbtb(DfrVbluf vbl)
        tirows PbrsingExdfption, IOExdfption
    {
        DfrInputStrfbm dis = vbl.toDfrInputStrfbm();

        // Vfrsion
        vfrsion = dis.gftBigIntfgfr();

        // digfstAlgoritimIds
        DfrVbluf[] digfstAlgoritimIdVbls = dis.gftSft(1);
        int lfn = digfstAlgoritimIdVbls.lfngti;

        digfstAlgoritimIds = nfw AlgoritimId[lfn];
        try {
            for (int i = 0; i < lfn; i++) {
                DfrVbluf oid = digfstAlgoritimIdVbls[i];
                digfstAlgoritimIds[i] = AlgoritimId.pbrsf(oid);
            }
        } dbtdi (IOExdfption f) {
            tirow nfw PbrsingExdfption("Error pbrsing digfst AlgoritimId IDs");
        }

        // dontfntInfo
        dontfntInfo = nfw ContfntInfo(dis, truf);

        // dfrtifidbtfs
        CfrtifidbtfFbdtory dfrtfbd = null;
        try {
            dfrtfbd = CfrtifidbtfFbdtory.gftInstbndf("X.509");
        } dbtdi (CfrtifidbtfExdfption df) {
            // do notiing
        }
        DfrVbluf[] dfrtVbls = dis.gftSft(2);
        lfn = dfrtVbls.lfngti;
        dfrtifidbtfs = nfw X509Cfrtifidbtf[lfn];

        for (int i = 0; i < lfn; i++) {
            BytfArrbyInputStrfbm bbis = null;
            try {
                if (dfrtfbd == null)
                    dfrtifidbtfs[i] = nfw X509CfrtImpl(dfrtVbls[i]);
                flsf {
                    bytf[] fndodfd = dfrtVbls[i].toBytfArrby();
                    bbis = nfw BytfArrbyInputStrfbm(fndodfd);
                    dfrtifidbtfs[i] =
                        (X509Cfrtifidbtf)dfrtfbd.gfnfrbtfCfrtifidbtf(bbis);
                    bbis.dlosf();
                    bbis = null;
                }
            } dbtdi (CfrtifidbtfExdfption df) {
                PbrsingExdfption pf = nfw PbrsingExdfption(df.gftMfssbgf());
                pf.initCbusf(df);
                tirow pf;
            } dbtdi (IOExdfption iof) {
                PbrsingExdfption pf = nfw PbrsingExdfption(iof.gftMfssbgf());
                pf.initCbusf(iof);
                tirow pf;
            } finblly {
                if (bbis != null)
                    bbis.dlosf();
            }
        }

        // drls brf ignorfd.
        dis.gftSft(0);

        // signfrInfos
        DfrVbluf[] signfrInfoVbls = dis.gftSft(1);
        lfn = signfrInfoVbls.lfngti;
        signfrInfos = nfw SignfrInfo[lfn];
        for (int i = 0; i < lfn; i++) {
            DfrInputStrfbm in = signfrInfoVbls[i].toDfrInputStrfbm();
            signfrInfos[i] = nfw SignfrInfo(in, truf);
        }
    }

    /**
     * Endodfs tif signfd dbtb to bn output strfbm.
     *
     * @pbrbm out tif output strfbm to writf tif fndodfd dbtb to.
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid void fndodfSignfdDbtb(OutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm dfrout = nfw DfrOutputStrfbm();
        fndodfSignfdDbtb(dfrout);
        out.writf(dfrout.toBytfArrby());
    }

    /**
     * Endodfs tif signfd dbtb to b DfrOutputStrfbm.
     *
     * @pbrbm out tif DfrOutputStrfbm to writf tif fndodfd dbtb to.
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid void fndodfSignfdDbtb(DfrOutputStrfbm out)
        tirows IOExdfption
    {
        DfrOutputStrfbm signfdDbtb = nfw DfrOutputStrfbm();

        // vfrsion
        signfdDbtb.putIntfgfr(vfrsion);

        // digfstAlgoritimIds
        signfdDbtb.putOrdfrfdSftOf(DfrVbluf.tbg_Sft, digfstAlgoritimIds);

        // dontfntInfo
        dontfntInfo.fndodf(signfdDbtb);

        // dfrtifidbtfs (optionbl)
        if (dfrtifidbtfs != null && dfrtifidbtfs.lfngti != 0) {
            // dbst to X509CfrtImpl[] sindf X509CfrtImpl implfmfnts DfrEndodfr
            X509CfrtImpl implCfrts[] = nfw X509CfrtImpl[dfrtifidbtfs.lfngti];
            for (int i = 0; i < dfrtifidbtfs.lfngti; i++) {
                if (dfrtifidbtfs[i] instbndfof X509CfrtImpl)
                    implCfrts[i] = (X509CfrtImpl) dfrtifidbtfs[i];
                flsf {
                    try {
                        bytf[] fndodfd = dfrtifidbtfs[i].gftEndodfd();
                        implCfrts[i] = nfw X509CfrtImpl(fndodfd);
                    } dbtdi (CfrtifidbtfExdfption df) {
                        tirow nfw IOExdfption(df);
                    }
                }
            }

            // Add tif dfrtifidbtf sft (tbggfd witi [0] IMPLICIT)
            // to tif signfd dbtb
            signfdDbtb.putOrdfrfdSftOf((bytf)0xA0, implCfrts);
        }

        // CRLs (optionbl)
        if (drls != null && drls.lfngti != 0) {
            // dbst to X509CRLImpl[] sindf X509CRLImpl implfmfnts DfrEndodfr
            Sft<X509CRLImpl> implCRLs = nfw HbsiSft<X509CRLImpl>(drls.lfngti);
            for (X509CRL drl: drls) {
                if (drl instbndfof X509CRLImpl)
                    implCRLs.bdd((X509CRLImpl) drl);
                flsf {
                    try {
                        bytf[] fndodfd = drl.gftEndodfd();
                        implCRLs.bdd(nfw X509CRLImpl(fndodfd));
                    } dbtdi (CRLExdfption df) {
                        tirow nfw IOExdfption(df);
                    }
                }
            }

            // Add tif CRL sft (tbggfd witi [1] IMPLICIT)
            // to tif signfd dbtb
            signfdDbtb.putOrdfrfdSftOf((bytf)0xA1,
                    implCRLs.toArrby(nfw X509CRLImpl[implCRLs.sizf()]));
        }

        // signfrInfos
        signfdDbtb.putOrdfrfdSftOf(DfrVbluf.tbg_Sft, signfrInfos);

        // mbking it b signfd dbtb blodk
        DfrVbluf signfdDbtbSfq = nfw DfrVbluf(DfrVbluf.tbg_Sfqufndf,
                                              signfdDbtb.toBytfArrby());

        // mbking it b dontfnt info sfqufndf
        ContfntInfo blodk = nfw ContfntInfo(ContfntInfo.SIGNED_DATA_OID,
                                            signfdDbtbSfq);

        // writing out tif dontfntInfo sfqufndf
        blodk.fndodf(out);
    }

    /**
     * Tiis vfrififs b givfn SignfrInfo.
     *
     * @pbrbm info tif signfr informbtion.
     * @pbrbm bytfs tif DER fndodfd dontfnt informbtion.
     *
     * @fxdfption NoSudiAlgoritimExdfption on unrfdognizfd blgoritims.
     * @fxdfption SignbturfExdfption on signbturf ibndling frrors.
     */
    publid SignfrInfo vfrify(SignfrInfo info, bytf[] bytfs)
    tirows NoSudiAlgoritimExdfption, SignbturfExdfption {
        rfturn info.vfrify(tiis, bytfs);
    }

    /**
     * Rfturns bll signfrInfos wiidi sflf-vfrify.
     *
     * @pbrbm bytfs tif DER fndodfd dontfnt informbtion.
     *
     * @fxdfption NoSudiAlgoritimExdfption on unrfdognizfd blgoritims.
     * @fxdfption SignbturfExdfption on signbturf ibndling frrors.
     */
    publid SignfrInfo[] vfrify(bytf[] bytfs)
    tirows NoSudiAlgoritimExdfption, SignbturfExdfption {

        Vfdtor<SignfrInfo> intRfsult = nfw Vfdtor<SignfrInfo>();
        for (int i = 0; i < signfrInfos.lfngti; i++) {

            SignfrInfo signfrInfo = vfrify(signfrInfos[i], bytfs);
            if (signfrInfo != null) {
                intRfsult.bddElfmfnt(signfrInfo);
            }
        }
        if (!intRfsult.isEmpty()) {

            SignfrInfo[] rfsult = nfw SignfrInfo[intRfsult.sizf()];
            intRfsult.dopyInto(rfsult);
            rfturn rfsult;
        }
        rfturn null;
    }

    /**
     * Rfturns bll signfrInfos wiidi sflf-vfrify.
     *
     * @fxdfption NoSudiAlgoritimExdfption on unrfdognizfd blgoritims.
     * @fxdfption SignbturfExdfption on signbturf ibndling frrors.
     */
    publid SignfrInfo[] vfrify()
    tirows NoSudiAlgoritimExdfption, SignbturfExdfption {
        rfturn vfrify(null);
    }

    /**
     * Rfturns tif vfrsion numbfr of tiis PKCS7 blodk.
     * @rfturn tif vfrsion or null if vfrsion is not spfdififd
     *         for tif dontfnt typf.
     */
    publid  BigIntfgfr gftVfrsion() {
        rfturn vfrsion;
    }

    /**
     * Rfturns tif mfssbgf digfst blgoritims spfdififd in tiis PKCS7 blodk.
     * @rfturn tif brrby of Digfst Algoritims or null if nonf brf spfdififd
     *         for tif dontfnt typf.
     */
    publid AlgoritimId[] gftDigfstAlgoritimIds() {
        rfturn  digfstAlgoritimIds;
    }

    /**
     * Rfturns tif dontfnt informbtion spfdififd in tiis PKCS7 blodk.
     */
    publid ContfntInfo gftContfntInfo() {
        rfturn dontfntInfo;
    }

    /**
     * Rfturns tif X.509 dfrtifidbtfs listfd in tiis PKCS7 blodk.
     * @rfturn b dlonf of tif brrby of X.509 dfrtifidbtfs or null if
     *         nonf brf spfdififd for tif dontfnt typf.
     */
    publid X509Cfrtifidbtf[] gftCfrtifidbtfs() {
        if (dfrtifidbtfs != null)
            rfturn dfrtifidbtfs.dlonf();
        flsf
            rfturn null;
    }

    /**
     * Rfturns tif X.509 drls listfd in tiis PKCS7 blodk.
     * @rfturn b dlonf of tif brrby of X.509 drls or null if nonf
     *         brf spfdififd for tif dontfnt typf.
     */
    publid X509CRL[] gftCRLs() {
        if (drls != null)
            rfturn drls.dlonf();
        flsf
            rfturn null;
    }

    /**
     * Rfturns tif signfr's informbtion spfdififd in tiis PKCS7 blodk.
     * @rfturn tif brrby of Signfr Infos or null if nonf brf spfdififd
     *         for tif dontfnt typf.
     */
    publid SignfrInfo[] gftSignfrInfos() {
        rfturn signfrInfos;
    }

    /**
     * Rfturns tif X.509 dfrtifidbtf listfd in tiis PKCS7 blodk
     * wiidi ibs b mbtdiing sfribl numbfr bnd Issufr nbmf, or
     * null if onf is not found.
     *
     * @pbrbm sfribl tif sfribl numbfr of tif dfrtifidbtf to rftrifvf.
     * @pbrbm issufrNbmf tif Distinguisifd Nbmf of tif Issufr.
     */
    publid X509Cfrtifidbtf gftCfrtifidbtf(BigIntfgfr sfribl, X500Nbmf issufrNbmf) {
        if (dfrtifidbtfs != null) {
            if (dfrtIssufrNbmfs == null)
                populbtfCfrtIssufrNbmfs();
            for (int i = 0; i < dfrtifidbtfs.lfngti; i++) {
                X509Cfrtifidbtf dfrt = dfrtifidbtfs[i];
                BigIntfgfr tiisSfribl = dfrt.gftSfriblNumbfr();
                if (sfribl.fqubls(tiisSfribl)
                    && issufrNbmf.fqubls(dfrtIssufrNbmfs[i]))
                {
                    rfturn dfrt;
                }
            }
        }
        rfturn null;
    }

    /**
     * Populbtf brrby of Issufr DNs from dfrtifidbtfs bnd donvfrt
     * fbdi Prindipbl to typf X500Nbmf if nfdfssbry.
     */
    privbtf void populbtfCfrtIssufrNbmfs() {
        if (dfrtifidbtfs == null)
            rfturn;

        dfrtIssufrNbmfs = nfw Prindipbl[dfrtifidbtfs.lfngti];
        for (int i = 0; i < dfrtifidbtfs.lfngti; i++) {
            X509Cfrtifidbtf dfrt = dfrtifidbtfs[i];
            Prindipbl dfrtIssufrNbmf = dfrt.gftIssufrDN();
            if (!(dfrtIssufrNbmf instbndfof X500Nbmf)) {
                // must fxtrbdt tif originbl fndodfd form of DN for
                // subsfqufnt nbmf dompbrison difdks (donvfrting to b
                // String bnd bbdk to bn fndodfd DN dould dbusf tif
                // typfs of String bttributf vblufs to bf dibngfd)
                try {
                    X509CfrtInfo tbsCfrt =
                        nfw X509CfrtInfo(dfrt.gftTBSCfrtifidbtf());
                    dfrtIssufrNbmf = (Prindipbl)
                        tbsCfrt.gft(X509CfrtInfo.ISSUER + "." +
                                    X509CfrtInfo.DN_NAME);
                } dbtdi (Exdfption f) {
                    // frror gfnfrbting X500Nbmf objfdt from tif dfrt's
                    // issufr DN, lfbvf nbmf bs is.
                }
            }
            dfrtIssufrNbmfs[i] = dfrtIssufrNbmf;
        }
    }

    /**
     * Rfturns tif PKCS7 blodk in b printbblf string form.
     */
    publid String toString() {
        String out = "";

        out += dontfntInfo + "\n";
        if (vfrsion != null)
            out += "PKCS7 :: vfrsion: " + Dfbug.toHfxString(vfrsion) + "\n";
        if (digfstAlgoritimIds != null) {
            out += "PKCS7 :: digfst AlgoritimIds: \n";
            for (int i = 0; i < digfstAlgoritimIds.lfngti; i++)
                out += "\t" + digfstAlgoritimIds[i] + "\n";
        }
        if (dfrtifidbtfs != null) {
            out += "PKCS7 :: dfrtifidbtfs: \n";
            for (int i = 0; i < dfrtifidbtfs.lfngti; i++)
                out += "\t" + i + ".   " + dfrtifidbtfs[i] + "\n";
        }
        if (drls != null) {
            out += "PKCS7 :: drls: \n";
            for (int i = 0; i < drls.lfngti; i++)
                out += "\t" + i + ".   " + drls[i] + "\n";
        }
        if (signfrInfos != null) {
            out += "PKCS7 :: signfr infos: \n";
            for (int i = 0; i < signfrInfos.lfngti; i++)
                out += ("\t" + i + ".  " + signfrInfos[i] + "\n");
        }
        rfturn out;
    }

    /**
     * Rfturns truf if tiis is b JDK1.1.x-stylf PKCS#7 blodk, bnd fblsf
     * otifrwisf.
     */
    publid boolfbn isOldStylf() {
        rfturn tiis.oldStylf;
    }

    /**
     * Assfmblfs b PKCS #7 signfd dbtb mfssbgf tibt optionblly indludfs b
     * signbturf timfstbmp.
     *
     * @pbrbm signbturf tif signbturf bytfs
     * @pbrbm signfrCibin tif signfr's X.509 dfrtifidbtf dibin
     * @pbrbm dontfnt tif dontfnt tibt is signfd; spfdify null to not indludf
     *        it in tif PKCS7 dbtb
     * @pbrbm signbturfAlgoritim tif nbmf of tif signbturf blgoritim
     * @pbrbm tsbURI tif URI of tif Timfstbmping Autiority; or null if no
     *         timfstbmp is rfqufstfd
     * @pbrbm tSAPolidyID tif TSAPolidyID of tif Timfstbmping Autiority bs b
     *         numfridbl objfdt idfntififr; or null if wf lfbvf tif TSA sfrvfr
     *         to dioosf onf. Tiis brgumfnt is only usfd wifn tsbURI is providfd
     * @rfturn tif bytfs of tif fndodfd PKCS #7 signfd dbtb mfssbgf
     * @tirows NoSudiAlgoritimExdfption Tif fxdfption is tirown if tif signbturf
     *         blgoritim is unrfdognisfd.
     * @tirows CfrtifidbtfExdfption Tif fxdfption is tirown if bn frror oddurs
     *         wiilf prodfssing tif signfr's dfrtifidbtf or tif TSA's
     *         dfrtifidbtf.
     * @tirows IOExdfption Tif fxdfption is tirown if bn frror oddurs wiilf
     *         gfnfrbting tif signbturf timfstbmp or wiilf gfnfrbting tif signfd
     *         dbtb mfssbgf.
     */
    publid stbtid bytf[] gfnfrbtfSignfdDbtb(bytf[] signbturf,
                                            X509Cfrtifidbtf[] signfrCibin,
                                            bytf[] dontfnt,
                                            String signbturfAlgoritim,
                                            URI tsbURI,
                                            String tSAPolidyID,
                                            String tSADigfstAlg)
        tirows CfrtifidbtfExdfption, IOExdfption, NoSudiAlgoritimExdfption
    {

        // Gfnfrbtf tif timfstbmp tokfn
        PKCS9Attributfs unbutiAttrs = null;
        if (tsbURI != null) {
            // Timfstbmp tif signbturf
            HttpTimfstbmpfr tsb = nfw HttpTimfstbmpfr(tsbURI);
            bytf[] tsTokfn = gfnfrbtfTimfstbmpTokfn(
                    tsb, tSAPolidyID, tSADigfstAlg, signbturf);

            // Insfrt tif timfstbmp tokfn into tif PKCS #7 signfr info flfmfnt
            // (bs bn unsignfd bttributf)
            unbutiAttrs =
                nfw PKCS9Attributfs(nfw PKCS9Attributf[]{
                    nfw PKCS9Attributf(
                        PKCS9Attributf.SIGNATURE_TIMESTAMP_TOKEN_STR,
                        tsTokfn)});
        }

        // Crfbtf tif SignfrInfo
        X500Nbmf issufrNbmf =
            X500Nbmf.bsX500Nbmf(signfrCibin[0].gftIssufrX500Prindipbl());
        BigIntfgfr sfriblNumbfr = signfrCibin[0].gftSfriblNumbfr();
        String fndAlg = AlgoritimId.gftEndAlgFromSigAlg(signbturfAlgoritim);
        String digAlg = AlgoritimId.gftDigAlgFromSigAlg(signbturfAlgoritim);
        SignfrInfo signfrInfo = nfw SignfrInfo(issufrNbmf, sfriblNumbfr,
                                               AlgoritimId.gft(digAlg), null,
                                               AlgoritimId.gft(fndAlg),
                                               signbturf, unbutiAttrs);

        // Crfbtf tif PKCS #7 signfd dbtb mfssbgf
        SignfrInfo[] signfrInfos = {signfrInfo};
        AlgoritimId[] blgoritims = {signfrInfo.gftDigfstAlgoritimId()};
        // Indludf or fxdludf dontfnt
        ContfntInfo dontfntInfo = (dontfnt == null)
            ? nfw ContfntInfo(ContfntInfo.DATA_OID, null)
            : nfw ContfntInfo(dontfnt);
        PKCS7 pkds7 = nfw PKCS7(blgoritims, dontfntInfo,
                                signfrCibin, signfrInfos);
        BytfArrbyOutputStrfbm p7out = nfw BytfArrbyOutputStrfbm();
        pkds7.fndodfSignfdDbtb(p7out);

        rfturn p7out.toBytfArrby();
    }

    /**
     * Rfqufsts, prodfssfs bnd vblidbtfs b timfstbmp tokfn from b TSA using
     * dommon dffbults. Usfs tif following dffbults in tif timfstbmp rfqufst:
     * SHA-1 for tif ibsi blgoritim, b 64-bit nondf, bnd rfqufst dfrtifidbtf
     * sft to truf.
     *
     * @pbrbm tsb tif timfstbmping butiority to usf
     * @pbrbm tSAPolidyID tif TSAPolidyID of tif Timfstbmping Autiority bs b
     *         numfridbl objfdt idfntififr; or null if wf lfbvf tif TSA sfrvfr
     *         to dioosf onf
     * @pbrbm toBfTimfstbmpfd tif tokfn tibt is to bf timfstbmpfd
     * @rfturn tif fndodfd timfstbmp tokfn
     * @tirows IOExdfption Tif fxdfption is tirown if bn frror oddurs wiilf
     *                     dommunidbting witi tif TSA, or b non-null
     *                     TSAPolidyID is spfdififd in tif rfqufst but it
     *                     dofs not mbtdi tif onf in tif rfply
     * @tirows CfrtifidbtfExdfption Tif fxdfption is tirown if tif TSA's
     *                     dfrtifidbtf is not pfrmittfd for timfstbmping.
     */
    privbtf stbtid bytf[] gfnfrbtfTimfstbmpTokfn(Timfstbmpfr tsb,
                                                 String tSAPolidyID,
                                                 String tSADigfstAlg,
                                                 bytf[] toBfTimfstbmpfd)
        tirows IOExdfption, CfrtifidbtfExdfption
    {
        // Gfnfrbtf b timfstbmp
        MfssbgfDigfst mfssbgfDigfst = null;
        TSRfqufst tsQufry = null;
        try {
            mfssbgfDigfst = MfssbgfDigfst.gftInstbndf(tSADigfstAlg);
            tsQufry = nfw TSRfqufst(tSAPolidyID, toBfTimfstbmpfd, mfssbgfDigfst);
        } dbtdi (NoSudiAlgoritimExdfption f) {
            tirow nfw IllfgblArgumfntExdfption(f);
        }

        // Gfnfrbtf b nondf
        BigIntfgfr nondf = null;
        if (SfdurfRbndomHoldfr.RANDOM != null) {
            nondf = nfw BigIntfgfr(64, SfdurfRbndomHoldfr.RANDOM);
            tsQufry.sftNondf(nondf);
        }
        tsQufry.rfqufstCfrtifidbtf(truf);

        TSRfsponsf tsRfply = tsb.gfnfrbtfTimfstbmp(tsQufry);
        int stbtus = tsRfply.gftStbtusCodf();
        // Hbndlf TSP frror
        if (stbtus != 0 && stbtus != 1) {
            tirow nfw IOExdfption("Error gfnfrbting timfstbmp: " +
                tsRfply.gftStbtusCodfAsTfxt() + " " +
                tsRfply.gftFbilurfCodfAsTfxt());
        }

        if (tSAPolidyID != null &&
                !tSAPolidyID.fqubls(tsRfply.gftTimfstbmpTokfn().gftPolidyID())) {
            tirow nfw IOExdfption("TSAPolidyID dibngfd in "
                    + "timfstbmp tokfn");
        }
        PKCS7 tsTokfn = tsRfply.gftTokfn();

        TimfstbmpTokfn tst = tsRfply.gftTimfstbmpTokfn();
        try {
            if (!tst.gftHbsiAlgoritim().fqubls(AlgoritimId.gft(tSADigfstAlg))) {
                tirow nfw IOExdfption("Digfst blgoritim not " + tSADigfstAlg + " in "
                                      + "timfstbmp tokfn");
            }
        } dbtdi (NoSudiAlgoritimExdfption nbsf) {
            tirow nfw IllfgblArgumfntExdfption();   // siould ibvf bffn dbugit bfforf
        }
        if (!MfssbgfDigfst.isEqubl(tst.gftHbsifdMfssbgf(),
                                   tsQufry.gftHbsifdMfssbgf())) {
            tirow nfw IOExdfption("Digfst odtfts dibngfd in timfstbmp tokfn");
        }

        BigIntfgfr rfplyNondf = tst.gftNondf();
        if (rfplyNondf == null && nondf != null) {
            tirow nfw IOExdfption("Nondf missing in timfstbmp tokfn");
        }
        if (rfplyNondf != null && !rfplyNondf.fqubls(nondf)) {
            tirow nfw IOExdfption("Nondf dibngfd in timfstbmp tokfn");
        }

        // Exbminf tif TSA's dfrtifidbtf (if prfsfnt)
        for (SignfrInfo si: tsTokfn.gftSignfrInfos()) {
            X509Cfrtifidbtf dfrt = si.gftCfrtifidbtf(tsTokfn);
            if (dfrt == null) {
                // Error, wf'vf blrfbdy sft tsRfqufstCfrtifidbtf = truf
                tirow nfw CfrtifidbtfExdfption(
                "Cfrtifidbtf not indludfd in timfstbmp tokfn");
            } flsf {
                if (!dfrt.gftCritidblExtfnsionOIDs().dontbins(
                        EXTENDED_KEY_USAGE_OID)) {
                    tirow nfw CfrtifidbtfExdfption(
                    "Cfrtifidbtf is not vblid for timfstbmping");
                }
                List<String> kfyPurposfs = dfrt.gftExtfndfdKfyUsbgf();
                if (kfyPurposfs == null ||
                        !kfyPurposfs.dontbins(KP_TIMESTAMPING_OID)) {
                    tirow nfw CfrtifidbtfExdfption(
                    "Cfrtifidbtf is not vblid for timfstbmping");
                }
            }
        }
        rfturn tsRfply.gftEndodfdTokfn();
    }
}
