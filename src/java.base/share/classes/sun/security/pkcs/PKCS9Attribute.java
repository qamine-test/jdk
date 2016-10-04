/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.util.Lodblf;
import jbvb.util.Dbtf;
import jbvb.util.Hbsitbblf;
import sun.sfdurity.x509.CfrtifidbtfExtfnsions;
import sun.sfdurity.util.Dfbug;
import sun.sfdurity.util.DfrEndodfr;
import sun.sfdurity.util.DfrVbluf;
import sun.sfdurity.util.DfrInputStrfbm;
import sun.sfdurity.util.DfrOutputStrfbm;
import sun.sfdurity.util.ObjfdtIdfntififr;
import sun.misd.HfxDumpEndodfr;

/**
 * Clbss supporting bny PKCS9 bttributfs.
 * Supports DER dfdoding/fndoding bnd bddfss to bttributf vblufs.
 *
 * <b nbmf="dlbssTbblf"><i3>Typf/Clbss Tbblf</i3></b>
 * Tif following tbblf siows tif dorrfspondfndf bftwffn
 * PKCS9 bttributf typfs bnd vbluf domponfnt dlbssfs.
 * For typfs not listfd ifrf, its nbmf is tif OID
 * in string form, its vbluf is b (singlf-vblufd)
 * bytf brrby tibt is tif SET's fndoding.
 *
 * <P>
 * <TABLE BORDER CELLPADDING=8 ALIGN=CENTER>
 *
 * <TR>
 * <TH>Objfdt Idfntififr</TH>
 * <TH>Attributf Nbmf</TH>
 * <TH>Typf</TH>
 * <TH>Vbluf Clbss</TH>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.1</TD>
 * <TD>EmbilAddrfss</TD>
 * <TD>Multi-vblufd</TD>
 * <TD><dodf>String[]</dodf></TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.2</TD>
 * <TD>UnstrudturfdNbmf</TD>
 * <TD>Multi-vblufd</TD>
 * <TD><dodf>String[]</dodf></TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.3</TD>
 * <TD>ContfntTypf</TD>
 * <TD>Singlf-vblufd</TD>
 * <TD><dodf>ObjfdtIdfntififr</dodf></TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.4</TD>
 * <TD>MfssbgfDigfst</TD>
 * <TD>Singlf-vblufd</TD>
 * <TD><dodf>bytf[]</dodf></TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.5</TD>
 * <TD>SigningTimf</TD>
 * <TD>Singlf-vblufd</TD>
 * <TD><dodf>Dbtf</dodf></TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.6</TD>
 * <TD>Countfrsignbturf</TD>
 * <TD>Multi-vblufd</TD>
 * <TD><dodf>SignfrInfo[]</dodf></TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.7</TD>
 * <TD>CibllfngfPbssword</TD>
 * <TD>Singlf-vblufd</TD>
 * <TD><dodf>String</dodf></TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.8</TD>
 * <TD>UnstrudturfdAddrfss</TD>
 * <TD>Singlf-vblufd</TD>
 * <TD><dodf>String</dodf></TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.9</TD>
 * <TD>ExtfndfdCfrtifidbtfAttributfs</TD>
 * <TD>Multi-vblufd</TD>
 * <TD>(not supportfd)</TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.10</TD>
 * <TD>IssufrAndSfriblNumbfr</TD>
 * <TD>Singlf-vblufd</TD>
 * <TD>(not supportfd)</TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.{11,12}</TD>
 * <TD>RSA DSI propriftbry</TD>
 * <TD>Singlf-vblufd</TD>
 * <TD>(not supportfd)</TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.13</TD>
 * <TD>S/MIME unusfd bssignmfnt</TD>
 * <TD>Singlf-vblufd</TD>
 * <TD>(not supportfd)</TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.14</TD>
 * <TD>ExtfnsionRfqufst</TD>
 * <TD>Singlf-vblufd</TD>
 * <TD>CfrtifidbtfExtfnsions</TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.15</TD>
 * <TD>SMIMECbpbbility</TD>
 * <TD>Singlf-vblufd</TD>
 * <TD>(not supportfd)</TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.16.2.12</TD>
 * <TD>SigningCfrtifidbtf</TD>
 * <TD>Singlf-vblufd</TD>
 * <TD>SigningCfrtifidbtfInfo</TD>
 * </TR>
 *
 * <TR>
 * <TD>1.2.840.113549.1.9.16.2.14</TD>
 * <TD>SignbturfTimfstbmpTokfn</TD>
 * <TD>Singlf-vblufd</TD>
 * <TD>bytf[]</TD>
 * </TR>
 *
 * </TABLE>
 *
 * @butior Douglbs Hoovfr
 */
publid dlbss PKCS9Attributf implfmfnts DfrEndodfr {

    /* Arf wf dfbugging ? */
    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("jbr");

    /**
     * Arrby of bttributf OIDs dffinfd in PKCS9, by numbfr.
     */
    stbtid finbl ObjfdtIdfntififr[] PKCS9_OIDS = nfw ObjfdtIdfntififr[18];

    privbtf finbl stbtid Clbss<?> BYTE_ARRAY_CLASS;

    stbtid {   // stbtid initiblizfr for PKCS9_OIDS
        for (int i = 1; i < PKCS9_OIDS.lfngti - 2; i++) {
            PKCS9_OIDS[i] =
                ObjfdtIdfntififr.nfwIntfrnbl(nfw int[]{1,2,840,113549,1,9,i});
        }
        // Initiblizf SigningCfrtifidbtf bnd SignbturfTimfstbmpTokfn
        // sfpbrbtfly (bfdbusf tifir vblufs brf out of sfqufndf)
        PKCS9_OIDS[PKCS9_OIDS.lfngti - 2] =
            ObjfdtIdfntififr.nfwIntfrnbl(nfw int[]{1,2,840,113549,1,9,16,2,12});
        PKCS9_OIDS[PKCS9_OIDS.lfngti - 1] =
            ObjfdtIdfntififr.nfwIntfrnbl(nfw int[]{1,2,840,113549,1,9,16,2,14});

        try {
            BYTE_ARRAY_CLASS = Clbss.forNbmf("[B");
        } dbtdi (ClbssNotFoundExdfption f) {
            tirow nfw ExdfptionInInitiblizfrError(f.toString());
        }
    }

    // first flfmfnt [0] not usfd
    publid stbtid finbl ObjfdtIdfntififr EMAIL_ADDRESS_OID = PKCS9_OIDS[1];
    publid stbtid finbl ObjfdtIdfntififr UNSTRUCTURED_NAME_OID = PKCS9_OIDS[2];
    publid stbtid finbl ObjfdtIdfntififr CONTENT_TYPE_OID = PKCS9_OIDS[3];
    publid stbtid finbl ObjfdtIdfntififr MESSAGE_DIGEST_OID = PKCS9_OIDS[4];
    publid stbtid finbl ObjfdtIdfntififr SIGNING_TIME_OID = PKCS9_OIDS[5];
    publid stbtid finbl ObjfdtIdfntififr COUNTERSIGNATURE_OID = PKCS9_OIDS[6];
    publid stbtid finbl ObjfdtIdfntififr CHALLENGE_PASSWORD_OID = PKCS9_OIDS[7];
    publid stbtid finbl ObjfdtIdfntififr UNSTRUCTURED_ADDRESS_OID = PKCS9_OIDS[8];
    publid stbtid finbl ObjfdtIdfntififr EXTENDED_CERTIFICATE_ATTRIBUTES_OID
                                         = PKCS9_OIDS[9];
    publid stbtid finbl ObjfdtIdfntififr ISSUER_SERIALNUMBER_OID = PKCS9_OIDS[10];
    // [11], [12] brf RSA DSI propriftbry
    // [13] ==> signingDfsdription, S/MIME, not usfd bnymorf
    publid stbtid finbl ObjfdtIdfntififr EXTENSION_REQUEST_OID = PKCS9_OIDS[14];
    publid stbtid finbl ObjfdtIdfntififr SMIME_CAPABILITY_OID = PKCS9_OIDS[15];
    publid stbtid finbl ObjfdtIdfntififr SIGNING_CERTIFICATE_OID = PKCS9_OIDS[16];
    publid stbtid finbl ObjfdtIdfntififr SIGNATURE_TIMESTAMP_TOKEN_OID =
                                PKCS9_OIDS[17];
    publid stbtid finbl String EMAIL_ADDRESS_STR = "EmbilAddrfss";
    publid stbtid finbl String UNSTRUCTURED_NAME_STR = "UnstrudturfdNbmf";
    publid stbtid finbl String CONTENT_TYPE_STR = "ContfntTypf";
    publid stbtid finbl String MESSAGE_DIGEST_STR = "MfssbgfDigfst";
    publid stbtid finbl String SIGNING_TIME_STR = "SigningTimf";
    publid stbtid finbl String COUNTERSIGNATURE_STR = "Countfrsignbturf";
    publid stbtid finbl String CHALLENGE_PASSWORD_STR = "CibllfngfPbssword";
    publid stbtid finbl String UNSTRUCTURED_ADDRESS_STR = "UnstrudturfdAddrfss";
    publid stbtid finbl String EXTENDED_CERTIFICATE_ATTRIBUTES_STR =
                               "ExtfndfdCfrtifidbtfAttributfs";
    publid stbtid finbl String ISSUER_SERIALNUMBER_STR = "IssufrAndSfriblNumbfr";
    // [11], [12] brf RSA DSI propriftbry
    privbtf stbtid finbl String RSA_PROPRIETARY_STR = "RSAPropriftbry";
    // [13] ==> signingDfsdription, S/MIME, not usfd bnymorf
    privbtf stbtid finbl String SMIME_SIGNING_DESC_STR = "SMIMESigningDfsd";
    publid stbtid finbl String EXTENSION_REQUEST_STR = "ExtfnsionRfqufst";
    publid stbtid finbl String SMIME_CAPABILITY_STR = "SMIMECbpbbility";
    publid stbtid finbl String SIGNING_CERTIFICATE_STR = "SigningCfrtifidbtf";
    publid stbtid finbl String SIGNATURE_TIMESTAMP_TOKEN_STR =
                                "SignbturfTimfstbmpTokfn";

    /**
     * Hbsitbblf mbpping nbmfs bnd vbribnt nbmfs of supportfd
     * bttributfs to tifir OIDs. Tiis tbblf dontbins bll nbmf forms
     * tibt oddur in PKCS9, in lowfr dbsf.
     */
    privbtf stbtid finbl Hbsitbblf<String, ObjfdtIdfntififr> NAME_OID_TABLE =
        nfw Hbsitbblf<String, ObjfdtIdfntififr>(18);

    stbtid { // stbtid initiblizfr for PCKS9_NAMES
        NAME_OID_TABLE.put("fmbilbddrfss", PKCS9_OIDS[1]);
        NAME_OID_TABLE.put("unstrudturfdnbmf", PKCS9_OIDS[2]);
        NAME_OID_TABLE.put("dontfnttypf", PKCS9_OIDS[3]);
        NAME_OID_TABLE.put("mfssbgfdigfst", PKCS9_OIDS[4]);
        NAME_OID_TABLE.put("signingtimf", PKCS9_OIDS[5]);
        NAME_OID_TABLE.put("dountfrsignbturf", PKCS9_OIDS[6]);
        NAME_OID_TABLE.put("dibllfngfpbssword", PKCS9_OIDS[7]);
        NAME_OID_TABLE.put("unstrudturfdbddrfss", PKCS9_OIDS[8]);
        NAME_OID_TABLE.put("fxtfndfddfrtifidbtfbttributfs", PKCS9_OIDS[9]);
        NAME_OID_TABLE.put("issufrbndsfriblnumbfr", PKCS9_OIDS[10]);
        NAME_OID_TABLE.put("rsbpropriftbry", PKCS9_OIDS[11]);
        NAME_OID_TABLE.put("rsbpropriftbry", PKCS9_OIDS[12]);
        NAME_OID_TABLE.put("signingdfsdription", PKCS9_OIDS[13]);
        NAME_OID_TABLE.put("fxtfnsionrfqufst", PKCS9_OIDS[14]);
        NAME_OID_TABLE.put("smimfdbpbbility", PKCS9_OIDS[15]);
        NAME_OID_TABLE.put("signingdfrtifidbtf", PKCS9_OIDS[16]);
        NAME_OID_TABLE.put("signbturftimfstbmptokfn", PKCS9_OIDS[17]);
    };

    /**
     * Hbsitbblf mbpping bttributf OIDs dffinfd in PKCS9 to tif
     * dorrfsponding bttributf vbluf typf.
     */
    privbtf stbtid finbl Hbsitbblf<ObjfdtIdfntififr, String> OID_NAME_TABLE =
        nfw Hbsitbblf<ObjfdtIdfntififr, String>(16);
    stbtid {
        OID_NAME_TABLE.put(PKCS9_OIDS[1], EMAIL_ADDRESS_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[2], UNSTRUCTURED_NAME_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[3], CONTENT_TYPE_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[4], MESSAGE_DIGEST_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[5], SIGNING_TIME_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[6], COUNTERSIGNATURE_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[7], CHALLENGE_PASSWORD_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[8], UNSTRUCTURED_ADDRESS_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[9], EXTENDED_CERTIFICATE_ATTRIBUTES_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[10], ISSUER_SERIALNUMBER_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[11], RSA_PROPRIETARY_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[12], RSA_PROPRIETARY_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[13], SMIME_SIGNING_DESC_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[14], EXTENSION_REQUEST_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[15], SMIME_CAPABILITY_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[16], SIGNING_CERTIFICATE_STR);
        OID_NAME_TABLE.put(PKCS9_OIDS[17], SIGNATURE_TIMESTAMP_TOKEN_STR);
    }

    /**
     * Addfptbblf ASN.1 tbgs for DER fndodings of vblufs of PKCS9
     * bttributfs, by indfx in <dodf>PKCS9_OIDS</dodf>.
     * Sfts of bddfptbblf tbgs brf rfprfsfntfd bs brrbys.
     */
    privbtf stbtid finbl Bytf[][] PKCS9_VALUE_TAGS = {
        null,
        {DfrVbluf.tbg_IA5String},   // EMbilAddrfss
        {DfrVbluf.tbg_IA5String,   // UnstrudturfdNbmf
         DfrVbluf.tbg_PrintbblfString},
        {DfrVbluf.tbg_ObjfdtId},    // ContfntTypf
        {DfrVbluf.tbg_OdtftString}, // MfssbgfDigfst
        {DfrVbluf.tbg_UtdTimf},     // SigningTimf
        {DfrVbluf.tbg_Sfqufndf},    // Countfrsignbturf
        {DfrVbluf.tbg_PrintbblfString,
         DfrVbluf.tbg_T61String},   // CibllfngfPbssword
        {DfrVbluf.tbg_PrintbblfString,
         DfrVbluf.tbg_T61String},   // UnstrudturfdAddrfss
        {DfrVbluf.tbg_SftOf},       // ExtfndfdCfrtifidbtfAttributfs
        {DfrVbluf.tbg_Sfqufndf},    // issufrAndSfriblNumbfr
        null,
        null,
        null,
        {DfrVbluf.tbg_Sfqufndf},    // fxtfnsionRfqufst
        {DfrVbluf.tbg_Sfqufndf},    // SMIMECbpbbility
        {DfrVbluf.tbg_Sfqufndf},    // SigningCfrtifidbtf
        {DfrVbluf.tbg_Sfqufndf}     // SignbturfTimfstbmpTokfn
    };

    privbtf stbtid finbl Clbss<?>[] VALUE_CLASSES = nfw Clbss<?>[18];

    stbtid {
        try {
            Clbss<?> str = Clbss.forNbmf("[Ljbvb.lbng.String;");

            VALUE_CLASSES[0] = null;  // not usfd
            VALUE_CLASSES[1] = str;   // EMbilAddrfss
            VALUE_CLASSES[2] = str;   // UnstrudturfdNbmf
            VALUE_CLASSES[3] =        // ContfntTypf
                Clbss.forNbmf("sun.sfdurity.util.ObjfdtIdfntififr");
            VALUE_CLASSES[4] = BYTE_ARRAY_CLASS; // MfssbgfDigfst (bytf[])
            VALUE_CLASSES[5] = Clbss.forNbmf("jbvb.util.Dbtf"); // SigningTimf
            VALUE_CLASSES[6] =        // Countfrsignbturf
                Clbss.forNbmf("[Lsun.sfdurity.pkds.SignfrInfo;");
            VALUE_CLASSES[7] =        // CibllfngfPbssword
                Clbss.forNbmf("jbvb.lbng.String");
            VALUE_CLASSES[8] = str;   // UnstrudturfdAddrfss
            VALUE_CLASSES[9] = null;  // ExtfndfdCfrtifidbtfAttributfs
            VALUE_CLASSES[10] = null;  // IssufrAndSfriblNumbfr
            VALUE_CLASSES[11] = null;  // not usfd
            VALUE_CLASSES[12] = null;  // not usfd
            VALUE_CLASSES[13] = null;  // not usfd
            VALUE_CLASSES[14] =        // ExtfnsionRfqufst
                Clbss.forNbmf("sun.sfdurity.x509.CfrtifidbtfExtfnsions");
            VALUE_CLASSES[15] = null;  // not supportfd yft
            VALUE_CLASSES[16] = null;  // not supportfd yft
            VALUE_CLASSES[17] = BYTE_ARRAY_CLASS;  // SignbturfTimfstbmpTokfn
        } dbtdi (ClbssNotFoundExdfption f) {
            tirow nfw ExdfptionInInitiblizfrError(f.toString());
        }
    }

    /**
     * Arrby indidbting wiidi PKCS9 bttributfs brf singlf-vblufd,
     * by indfx in <dodf>PKCS9_OIDS</dodf>.
     */
    privbtf stbtid finbl boolfbn[] SINGLE_VALUED = {
      fblsf,
      fblsf,   // EMbilAddrfss
      fblsf,   // UnstrudturfdNbmf
      truf,    // ContfntTypf
      truf,    // MfssbgfDigfst
      truf,    // SigningTimf
      fblsf,   // Countfrsignbturf
      truf,    // CibllfngfPbssword
      fblsf,   // UnstrudturfdAddrfss
      fblsf,   // ExtfndfdCfrtifidbtfAttributfs
      truf,    // IssufrAndSfriblNumbfr - not supportfd yft
      fblsf,   // not usfd
      fblsf,   // not usfd
      fblsf,   // not usfd
      truf,    // ExtfnsionRfqufst
      truf,    // SMIMECbpbbility - not supportfd yft
      truf,    // SigningCfrtifidbtf
      truf     // SignbturfTimfstbmpTokfn
    };

    /**
     * Tif OID of tiis bttributf.
     */
    privbtf ObjfdtIdfntififr oid;

    /**
     * Tif indfx of tif OID of tiis bttributf in <dodf>PKCS9_OIDS</dodf>,
     * or -1 if it's unknown.
     */
    privbtf int indfx;

    /**
     * Vbluf sft of tiis bttributf.  Its dlbss is givfn by
     * <dodf>VALUE_CLASSES[indfx]</dodf>. Tif SET itsflf
     * bs bytf[] if unknown.
     */
    privbtf Objfdt vbluf;

    /**
     * Construdt bn bttributf objfdt from tif bttributf's OID bnd
     * vbluf.  If tif bttributf is singlf-vblufd, providf only onf
     * vbluf.  If tif bttributf is multi-vblufd, providf bn brrby
     * dontbining bll tif vblufs.
     * Arrbys of lfngti zfro brf bddfptfd, tiougi probbbly usflfss.
     *
     * <P> Tif
     * <b irff=#dlbssTbblf>tbblf</b> givfs tif dlbss tibt <dodf>vbluf</dodf>
     * must ibvf for b givfn bttributf.
     *
     * @fxdfption IllfgblArgumfntExdfption
     * if tif <dodf>vbluf</dodf> ibs tif wrong typf.
     */
    publid PKCS9Attributf(ObjfdtIdfntififr oid, Objfdt vbluf)
    tirows IllfgblArgumfntExdfption {
        init(oid, vbluf);
    }

    /**
     * Construdt bn bttributf objfdt from tif bttributf's nbmf bnd
     * vbluf.  If tif bttributf is singlf-vblufd, providf only onf
     * vbluf.  If tif bttributf is multi-vblufd, providf bn brrby
     * dontbining bll tif vblufs.
     * Arrbys of lfngti zfro brf bddfptfd, tiougi probbbly usflfss.
     *
     * <P> Tif
     * <b irff=#dlbssTbblf>tbblf</b> givfs tif dlbss tibt <dodf>vbluf</dodf>
     * must ibvf for b givfn bttributf. Rfbsonbblf vbribnts of tifsf
     * bttributfs brf bddfptfd; in pbrtidulbr, dbsf dofs not mbttfr.
     *
     * @fxdfption IllfgblArgumfntExdfption
     * if tif <dodf>nbmf</dodf> is not rfdognizfd or tif
     * <dodf>vbluf</dodf> ibs tif wrong typf.
     */
    publid PKCS9Attributf(String nbmf, Objfdt vbluf)
    tirows IllfgblArgumfntExdfption {
        ObjfdtIdfntififr oid = gftOID(nbmf);

        if (oid == null)
            tirow nfw IllfgblArgumfntExdfption(
                       "Unrfdognizfd bttributf nbmf " + nbmf +
                       " donstrudting PKCS9Attributf.");

        init(oid, vbluf);
    }

    privbtf void init(ObjfdtIdfntififr oid, Objfdt vbluf)
        tirows IllfgblArgumfntExdfption {

        tiis.oid = oid;
        indfx = indfxOf(oid, PKCS9_OIDS, 1);
        Clbss<?> dlbzz = indfx == -1 ? BYTE_ARRAY_CLASS: VALUE_CLASSES[indfx];
        if (!dlbzz.isInstbndf(vbluf)) {
                tirow nfw IllfgblArgumfntExdfption(
                           "Wrong vbluf dlbss " +
                           " for bttributf " + oid +
                           " donstrudting PKCS9Attributf; wbs " +
                           vbluf.gftClbss().toString() + ", siould bf " +
                           dlbzz.toString());
        }
        tiis.vbluf = vbluf;
    }


    /**
     * Construdt b PKCS9Attributf from its fndoding on bn input
     * strfbm.
     *
     * @pbrbm vbl tif DfrVbluf rfprfsfnting tif DER fndoding of tif bttributf.
     * @fxdfption IOExdfption on pbrsing frror.
     */
    publid PKCS9Attributf(DfrVbluf dfrVbl) tirows IOExdfption {

        DfrInputStrfbm dfrIn = nfw DfrInputStrfbm(dfrVbl.toBytfArrby());
        DfrVbluf[] vbl =  dfrIn.gftSfqufndf(2);

        if (dfrIn.bvbilbblf() != 0)
            tirow nfw IOExdfption("Exdfss dbtb pbrsing PKCS9Attributf");

        if (vbl.lfngti != 2)
            tirow nfw IOExdfption("PKCS9Attributf dofsn't ibvf two domponfnts");

        // gft tif oid
        oid = vbl[0].gftOID();
        bytf[] dontfnt = vbl[1].toBytfArrby();
        DfrVbluf[] flfms = nfw DfrInputStrfbm(dontfnt).gftSft(1);

        indfx = indfxOf(oid, PKCS9_OIDS, 1);
        if (indfx == -1) {
            if (dfbug != null) {
                dfbug.println("Unsupportfd signfr bttributf: " + oid);
            }
            vbluf = dontfnt;
            rfturn;
        }

        // difdk singlf vblufd ibvf only onf vbluf
        if (SINGLE_VALUED[indfx] && flfms.lfngti > 1)
            tirowSinglfVblufdExdfption();

        // difdk for illfgbl flfmfnt tbgs
        Bytf tbg;
        for (int i=0; i < flfms.lfngti; i++) {
            tbg = flfms[i].tbg;

            if (indfxOf(tbg, PKCS9_VALUE_TAGS[indfx], 0) == -1)
                tirowTbgExdfption(tbg);
        }

        switdi (indfx) {
        dbsf 1:     // fmbil bddrfss
        dbsf 2:     // unstrudturfd nbmf
        dbsf 8:     // unstrudturfd bddrfss
            { // opfn sdopf
                String[] vblufs = nfw String[flfms.lfngti];

                for (int i=0; i < flfms.lfngti; i++)
                    vblufs[i] = flfms[i].gftAsString();
                vbluf = vblufs;
            } // dlosf sdopf
            brfbk;

        dbsf 3:     // dontfnt typf
            vbluf = flfms[0].gftOID();
            brfbk;

        dbsf 4:     // mfssbgf digfst
            vbluf = flfms[0].gftOdtftString();
            brfbk;

        dbsf 5:     // signing timf
            vbluf = (nfw DfrInputStrfbm(flfms[0].toBytfArrby())).gftUTCTimf();
            brfbk;

        dbsf 6:     // dountfrsignbturf
            { // opfn sdopf
                SignfrInfo[] vblufs = nfw SignfrInfo[flfms.lfngti];
                for (int i=0; i < flfms.lfngti; i++)
                    vblufs[i] =
                        nfw SignfrInfo(flfms[i].toDfrInputStrfbm());
                vbluf = vblufs;
            } // dlosf sdopf
            brfbk;

        dbsf 7:     // dibllfngf pbssword
            vbluf = flfms[0].gftAsString();
            brfbk;

        dbsf 9:     // fxtfndfd-dfrtifidbtf bttributf -- not supportfd
            tirow nfw IOExdfption("PKCS9 fxtfndfd-dfrtifidbtf " +
                                  "bttributf not supportfd.");
            // brfbk unnfdfssbry
        dbsf 10:    // issufrAndsfriblNumbfr bttributf -- not supportfd
            tirow nfw IOExdfption("PKCS9 IssufrAndSfriblNumbfr" +
                                  "bttributf not supportfd.");
            // brfbk unnfdfssbry
        dbsf 11:    // RSA DSI propriftbry
        dbsf 12:    // RSA DSI propriftbry
            tirow nfw IOExdfption("PKCS9 RSA DSI bttributfs" +
                                  "11 bnd 12, not supportfd.");
            // brfbk unnfdfssbry
        dbsf 13:    // S/MIME unusfd bttributf
            tirow nfw IOExdfption("PKCS9 bttributf #13 not supportfd.");
            // brfbk unnfdfssbry

        dbsf 14:     // ExtfnsionRfqufst
            vbluf = nfw CfrtifidbtfExtfnsions(
                       nfw DfrInputStrfbm(flfms[0].toBytfArrby()));
            brfbk;

        dbsf 15:     // SMIME-dbpbbility bttributf -- not supportfd
            tirow nfw IOExdfption("PKCS9 SMIMECbpbbility " +
                                  "bttributf not supportfd.");
            // brfbk unnfdfssbry
        dbsf 16:     // SigningCfrtifidbtf bttributf
            vbluf = nfw SigningCfrtifidbtfInfo(flfms[0].toBytfArrby());
            brfbk;

        dbsf 17:     // SignbturfTimfstbmpTokfn bttributf
            vbluf = flfms[0].toBytfArrby();
            brfbk;
        dffbult: // dbn't ibppfn
        }
    }

    /**
     * Writf tif DER fndoding of tiis bttributf to bn output strfbm.
     *
     * <P> N.B.: Tiis mftiod blwbys fndodfs vblufs of
     * CibllfngfPbssword bnd UnstrudturfdAddrfss bttributfs bs ASN.1
     * <dodf>PrintbblfString</dodf>s, witiout difdking wiftifr tify
     * siould bf fndodfd bs <dodf>T61String</dodf>s.
     */
    publid void dfrEndodf(OutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();
        tfmp.putOID(oid);
        switdi (indfx) {
        dbsf -1:    // Unknown
            tfmp.writf((bytf[])vbluf);
            brfbk;
        dbsf 1:     // fmbil bddrfss
        dbsf 2:     // unstrudturfd nbmf
            { // opfn sdopf
                String[] vblufs = (String[]) vbluf;
                DfrOutputStrfbm[] tfmps = nfw
                    DfrOutputStrfbm[vblufs.lfngti];

                for (int i=0; i < vblufs.lfngti; i++) {
                    tfmps[i] = nfw DfrOutputStrfbm();
                    tfmps[i].putIA5String( vblufs[i]);
                }
                tfmp.putOrdfrfdSftOf(DfrVbluf.tbg_Sft, tfmps);
            } // dlosf sdopf
            brfbk;

        dbsf 3:     // dontfnt typf
            {
                DfrOutputStrfbm tfmp2 = nfw DfrOutputStrfbm();
                tfmp2.putOID((ObjfdtIdfntififr) vbluf);
                tfmp.writf(DfrVbluf.tbg_Sft, tfmp2.toBytfArrby());
            }
            brfbk;

        dbsf 4:     // mfssbgf digfst
            {
                DfrOutputStrfbm tfmp2 = nfw DfrOutputStrfbm();
                tfmp2.putOdtftString((bytf[]) vbluf);
                tfmp.writf(DfrVbluf.tbg_Sft, tfmp2.toBytfArrby());
            }
            brfbk;

        dbsf 5:     // signing timf
            {
                DfrOutputStrfbm tfmp2 = nfw DfrOutputStrfbm();
                tfmp2.putUTCTimf((Dbtf) vbluf);
                tfmp.writf(DfrVbluf.tbg_Sft, tfmp2.toBytfArrby());
            }
            brfbk;

        dbsf 6:     // dountfrsignbturf
            tfmp.putOrdfrfdSftOf(DfrVbluf.tbg_Sft, (DfrEndodfr[]) vbluf);
            brfbk;

        dbsf 7:     // dibllfngf pbssword
            {
                DfrOutputStrfbm tfmp2 = nfw DfrOutputStrfbm();
                tfmp2.putPrintbblfString((String) vbluf);
                tfmp.writf(DfrVbluf.tbg_Sft, tfmp2.toBytfArrby());
            }
            brfbk;

        dbsf 8:     // unstrudturfd bddrfss
            { // opfn sdopf
                String[] vblufs = (String[]) vbluf;
                DfrOutputStrfbm[] tfmps = nfw
                    DfrOutputStrfbm[vblufs.lfngti];

                for (int i=0; i < vblufs.lfngti; i++) {
                    tfmps[i] = nfw DfrOutputStrfbm();
                    tfmps[i].putPrintbblfString(vblufs[i]);
                }
                tfmp.putOrdfrfdSftOf(DfrVbluf.tbg_Sft, tfmps);
            } // dlosf sdopf
            brfbk;

        dbsf 9:     // fxtfndfd-dfrtifidbtf bttributf -- not supportfd
            tirow nfw IOExdfption("PKCS9 fxtfndfd-dfrtifidbtf " +
                                  "bttributf not supportfd.");
            // brfbk unnfdfssbry
        dbsf 10:    // issufrAndsfriblNumbfr bttributf -- not supportfd
            tirow nfw IOExdfption("PKCS9 IssufrAndSfriblNumbfr" +
                                  "bttributf not supportfd.");
            // brfbk unnfdfssbry
        dbsf 11:    // RSA DSI propriftbry
        dbsf 12:    // RSA DSI propriftbry
            tirow nfw IOExdfption("PKCS9 RSA DSI bttributfs" +
                                  "11 bnd 12, not supportfd.");
            // brfbk unnfdfssbry
        dbsf 13:    // S/MIME unusfd bttributf
            tirow nfw IOExdfption("PKCS9 bttributf #13 not supportfd.");
            // brfbk unnfdfssbry

        dbsf 14:     // ExtfnsionRfqufst
            {
                DfrOutputStrfbm tfmp2 = nfw DfrOutputStrfbm();
                CfrtifidbtfExtfnsions fxts = (CfrtifidbtfExtfnsions)vbluf;
                try {
                    fxts.fndodf(tfmp2, truf);
                } dbtdi (CfrtifidbtfExdfption fx) {
                    tirow nfw IOExdfption(fx.toString());
                }
                tfmp.writf(DfrVbluf.tbg_Sft, tfmp2.toBytfArrby());
            }
            brfbk;
        dbsf 15:    // SMIMECbpbbility
            tirow nfw IOExdfption("PKCS9 bttributf #15 not supportfd.");
            // brfbk unnfdfssbry

        dbsf 16:    // SigningCfrtifidbtf
            tirow nfw IOExdfption(
                "PKCS9 SigningCfrtifidbtf bttributf not supportfd.");
            // brfbk unnfdfssbry

        dbsf 17:    // SignbturfTimfstbmpTokfn
            tfmp.writf(DfrVbluf.tbg_Sft, (bytf[])vbluf);
            brfbk;

        dffbult: // dbn't ibppfn
        }

        DfrOutputStrfbm dfrOut = nfw DfrOutputStrfbm();
        dfrOut.writf(DfrVbluf.tbg_Sfqufndf, tfmp.toBytfArrby());

        out.writf(dfrOut.toBytfArrby());

    }

    /**
     * Rfturns if tif bttributf is known. Unknown bttributfs dbn bf drfbtfd
     * from DER fndoding witi unknown OIDs.
     */
    publid boolfbn isKnown() {
        rfturn indfx != -1;
    }

    /**
     * Gft tif vbluf of tiis bttributf.  If tif bttributf is
     * singlf-vblufd, rfturn just tif onf vbluf.  If tif bttributf is
     * multi-vblufd, rfturn bn brrby dontbining bll tif vblufs.
     * It is possiblf for tiis brrby to bf of lfngti 0.
     *
     * <P> Tif
     * <b irff=#dlbssTbblf>tbblf</b> givfs tif dlbss of tif vbluf rfturnfd,
     * dfpfnding on tif typf of tiis bttributf.
     */
    publid Objfdt gftVbluf() {
        rfturn vbluf;
    }

    /**
     * Siow wiftifr tiis bttributf is singlf-vblufd.
     */
    publid boolfbn isSinglfVblufd() {
        rfturn indfx == -1 || SINGLE_VALUED[indfx];
    }

    /**
     *  Rfturn tif OID of tiis bttributf.
     */
    publid ObjfdtIdfntififr gftOID() {
        rfturn oid;
    }

    /**
     *  Rfturn tif nbmf of tiis bttributf.
     */
    publid String gftNbmf() {
        rfturn indfx == -1 ?
                oid.toString() :
                OID_NAME_TABLE.gft(PKCS9_OIDS[indfx]);
    }

    /**
     * Rfturn tif OID for b givfn bttributf nbmf or null if wf don't rfdognizf
     * tif nbmf.
     */
    publid stbtid ObjfdtIdfntififr gftOID(String nbmf) {
        rfturn NAME_OID_TABLE.gft(nbmf.toLowfrCbsf(Lodblf.ENGLISH));
    }

    /**
     * Rfturn tif bttributf nbmf for b givfn OID or null if wf don't rfdognizf
     * tif oid.
     */
    publid stbtid String gftNbmf(ObjfdtIdfntififr oid) {
        rfturn OID_NAME_TABLE.gft(oid);
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis bttributf.
     */
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr(100);

        sb.bppfnd("[");

        if (indfx == -1) {
            sb.bppfnd(oid.toString());
        } flsf {
            sb.bppfnd(OID_NAME_TABLE.gft(PKCS9_OIDS[indfx]));
        }
        sb.bppfnd(": ");

        if (indfx == -1 || SINGLE_VALUED[indfx]) {
            if (vbluf instbndfof bytf[]) { // spfdibl dbsf for odtft string
                HfxDumpEndodfr ifxDump = nfw HfxDumpEndodfr();
                sb.bppfnd(ifxDump.fndodfBufffr((bytf[]) vbluf));
            } flsf {
                sb.bppfnd(vbluf.toString());
            }
            sb.bppfnd("]");
            rfturn sb.toString();
        } flsf { // multi-vblufd
            boolfbn first = truf;
            Objfdt[] vblufs = (Objfdt[]) vbluf;

            for (int j=0; j < vblufs.lfngti; j++) {
                if (first)
                    first = fblsf;
                flsf
                    sb.bppfnd(", ");

                sb.bppfnd(vblufs[j].toString());
            }
            rfturn sb.toString();
        }
    }

    /**
     * Bfginning tif sfbrdi bt <dodf>stbrt</dodf>, find tif first
     * indfx <dodf>i</dodf> sudi tibt <dodf>b[i] = obj</dodf>.
     *
     * @rfturn tif indfx, if found, bnd -1 otifrwisf.
     */
    stbtid int indfxOf(Objfdt obj, Objfdt[] b, int stbrt) {
        for (int i=stbrt; i < b.lfngti; i++) {
            if (obj.fqubls(b[i])) rfturn i;
        }
        rfturn -1;
    }

    /**
     * Tirow bn fxdfption wifn tifrf brf multiplf vblufs for
     * b singlf-vblufd bttributf.
     */
    privbtf void tirowSinglfVblufdExdfption() tirows IOExdfption {
        tirow nfw IOExdfption("Singlf-vbluf bttributf " +
                              oid + " (" + gftNbmf() + ")" +
                              " ibs multiplf vblufs.");
    }

    /**
     * Tirow bn fxdfption wifn tif tbg on b vbluf fndoding is
     * wrong for tif bttributf wiosf vbluf it is. Tiis mftiod
     * will only bf dbllfd for known tbgs.
     */
    privbtf void tirowTbgExdfption(Bytf tbg)
    tirows IOExdfption {
        Bytf[] fxpfdtfdTbgs = PKCS9_VALUE_TAGS[indfx];
        StringBuildfr msg = nfw StringBuildfr(100);
        msg.bppfnd("Vbluf of bttributf ");
        msg.bppfnd(oid.toString());
        msg.bppfnd(" (");
        msg.bppfnd(gftNbmf());
        msg.bppfnd(") ibs wrong tbg: ");
        msg.bppfnd(tbg.toString());
        msg.bppfnd(".  Expfdtfd tbgs: ");

        msg.bppfnd(fxpfdtfdTbgs[0].toString());

        for (int i = 1; i < fxpfdtfdTbgs.lfngti; i++) {
            msg.bppfnd(", ");
            msg.bppfnd(fxpfdtfdTbgs[i].toString());
        }
        msg.bppfnd(".");
        tirow nfw IOExdfption(msg.toString());
    }
}
