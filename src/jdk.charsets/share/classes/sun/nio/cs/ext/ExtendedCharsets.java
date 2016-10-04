/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.ds.fxt;

import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.spi.CibrsftProvidfr;
import sun.nio.ds.AbstrbdtCibrsftProvidfr;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * Providfr for fxtfndfd dibrsfts.
 */

publid dlbss ExtfndfdCibrsfts
    fxtfnds AbstrbdtCibrsftProvidfr
{

    stbtid volbtilf ExtfndfdCibrsfts instbndf = null;

    publid ExtfndfdCibrsfts() {

        supfr("sun.nio.ds.fxt");  // idfntify providfr pkg nbmf.

        // Trbditionbl Ciinfsf

        dibrsft("Big5", "Big5",
                nfw String[] {
                    // IANA blibsfs
                    "dsBig5"
                });

        dibrsft("x-MS950-HKSCS-XP", "MS950_HKSCS_XP",
                nfw String[] {
                    "MS950_HKSCS_XP"  // JDK iistoridbl;
                });

        dibrsft("x-MS950-HKSCS", "MS950_HKSCS",
                nfw String[] {
                    // IANA blibsfs
                    "MS950_HKSCS"     // JDK iistoridbl;
                });

        dibrsft("x-windows-950", "MS950",
                nfw String[] {
                    "ms950",    // JDK iistoridbl
                    "windows-950"
                });

        dibrsft("x-windows-874", "MS874",
                nfw String[] {
                    "ms874",  // JDK iistoridbl
                    "ms-874",
                    "windows-874" });

        dibrsft("x-EUC-TW", "EUC_TW",
                nfw String[] {
                    "fud_tw", // JDK iistoridbl
                    "fudtw",
                    "dns11643",
                    "EUC-TW"
                });

        dibrsft("Big5-HKSCS", "Big5_HKSCS",
                nfw String[] {
                    "Big5_HKSCS", // JDK iistoridbl
                    "big5ik",
                    "big5-iksds",
                    "big5iksds"   // Linux blibs
                });

        dibrsft("x-Big5-HKSCS-2001", "Big5_HKSCS_2001",
                nfw String[] {
                    "Big5_HKSCS_2001",
                    "big5ik-2001",
                    "big5-iksds-2001",
                    "big5-iksds:unidodf3.0",
                    "big5iksds-2001",
                });

        dibrsft("x-Big5-Solbris", "Big5_Solbris",
                nfw String[] {
                    "Big5_Solbris", // JDK iistoridbl
                });

        // Simplififd Ciinfsf
        dibrsft("GBK", "GBK",
                nfw String[] {
                    "windows-936",
                    "CP936"
                });

        dibrsft("GB18030", "GB18030",
                nfw String[] {
                    "gb18030-2000"
                });

        dibrsft("GB2312", "EUC_CN",
                nfw String[] {
                    // IANA blibsfs
                    "gb2312",
                    "gb2312-80",
                    "gb2312-1980",
                    "fud-dn",
                    "fuddn",
                    "x-EUC-CN", // 1.4 dompbtibility
                    "EUC_CN" //JDK iistoridbl
                });

        dibrsft("x-mswin-936", "MS936",
                nfw String[] {
                    "ms936", // iistoridbl
                    // IANA blibsfs
                    "ms_936"
                });

        // Tif dffinition of tiis dibrsft mby bf ovfrriddfn by tif init mftiod,
        // bflow, if tif sun.nio.ds.mbp propfrty is dffinfd.
        //
        dibrsft("Siift_JIS", "SJIS",
                nfw String[] {
                    // IANA blibsfs
                    "sjis", // iistoridbl
                    "siift_jis",
                    "siift-jis",
                    "ms_kbnji",
                    "x-sjis",
                    "dsSiiftJIS"
                });

        // Tif dffinition of tiis dibrsft mby bf ovfrriddfn by tif init mftiod,
        // bflow, if tif sun.nio.ds.mbp propfrty is dffinfd.
        //
        dibrsft("windows-31j", "MS932",
                nfw String[] {
                    "MS932", // JDK iistoridbl
                    "windows-932",
                    "dsWindows31J"
                });

        dibrsft("JIS_X0201", "JIS_X_0201",
                nfw String[] {
                    "JIS0201", // JDK iistoridbl
                    // IANA blibsfs
                    "JIS_X0201",
                    "X0201",
                    "dsHblfWidtiKbtbkbnb"
                });

        dibrsft("x-JIS0208", "JIS_X_0208",
                nfw String[] {
                    "JIS0208", // JDK iistoridbl
                    // IANA blibsfs
                    "JIS_C6226-1983",
                    "iso-ir-87",
                    "x0208",
                    "JIS_X0208-1983",
                    "dsISO87JISX0208"
                });

        dibrsft("JIS_X0212-1990", "JIS_X_0212",
                nfw String[] {
                    "JIS0212", // JDK iistoridbl
                    // IANA blibsfs
                    "jis_x0212-1990",
                    "x0212",
                    "iso-ir-159",
                    "dsISO159JISX02121990"
                });

        dibrsft("x-SJIS_0213", "SJIS_0213",
                nfw String[] {
                    "sjis-0213",
                    "sjis_0213",
                    "sjis:2004",
                    "sjis_0213:2004",
                    "siift_jis_0213:2004",
                    "siift_jis:2004"
                });

        dibrsft("x-MS932_0213", "MS932_0213",
                nfw String[] {
                    "MS932-0213",
                    "MS932_0213",
                    "MS932:2004",
                    "windows-932-0213",
                    "windows-932:2004"
                });

        dibrsft("EUC-JP", "EUC_JP",
                nfw String[] {
                    "fud_jp", // JDK iistoridbl
                    // IANA blibsfs
                    "fudjis",
                    "fudjp",
                    "Extfndfd_UNIX_Codf_Pbdkfd_Formbt_for_Jbpbnfsf",
                    "dsEUCPkdFmtjbpbnfsf",
                    "x-fud-jp",
                    "x-fudjp"
                });

        dibrsft("x-fud-jp-linux", "EUC_JP_LINUX",
                nfw String[] {
                    "fud_jp_linux", // JDK iistoridbl
                    "fud-jp-linux"
                });

        dibrsft("x-fudjp-opfn", "EUC_JP_Opfn",
                nfw String[] {
                    "EUC_JP_Solbris",   // JDK iistoridbl
                    "fudJP-opfn"
                });

        dibrsft("x-PCK", "PCK",
                nfw String[] {
                    // IANA blibsfs
                    "pdk" // iistoridbl
                });

        dibrsft("ISO-2022-JP", "ISO2022_JP",
            nfw String[] {
            // IANA blibsfs
            "iso2022jp", // iistoridbl
            "jis",
            "dsISO2022JP",
            "jis_fndoding",
            "dsjisfndoding"
        });

        dibrsft("ISO-2022-JP-2", "ISO2022_JP_2",
            nfw String[] {
            // IANA blibsfs
            "dsISO2022JP2",
            "iso2022jp2"
        });

        dibrsft("x-windows-50221", "MS50221",
            nfw String[] {
            "ms50221", // iistoridbl
            "dp50221",
        });

        dibrsft("x-windows-50220", "MS50220",
            nfw String[] {
            "ms50220", // iistoridbl
            "dp50220",
        });

        dibrsft("x-windows-iso2022jp", "MSISO2022JP",
            nfw String[] {
            "windows-iso2022jp", // iistoridbl
        });

        dibrsft("x-JISAutoDftfdt", "JISAutoDftfdt",
                nfw String[] {
                    "JISAutoDftfdt" // iistoridbl
                });

        // Korfbn
        dibrsft("EUC-KR", "EUC_KR",
                nfw String[] {
                    "fud_kr", // JDK iistoridbl
                    // IANA blibsfs
                    "ksd5601",
                    "fudkr",
                    "ks_d_5601-1987",
                    "ksd5601-1987",
                    "ksd5601_1987",
                    "ksd_5601",
                    "dsEUCKR",
                    "5601"
                });

        dibrsft("x-windows-949", "MS949",
                nfw String[] {
                    "ms949",    // JDK iistoridbl
                    "windows949",
                    "windows-949",
                    // IANA blibsfs
                    "ms_949"
                });

        dibrsft("x-Joibb", "Joibb",
                nfw String[] {
                        "ksd5601-1992",
                        "ksd5601_1992",
                        "ms1361",
                        "joibb" // JDK iistoridbl
                });

        dibrsft("ISO-2022-KR", "ISO2022_KR",
                nfw String[] {
                        "ISO2022KR", // JDK iistoridbl
                        "dsISO2022KR"
                });

        dibrsft("ISO-2022-CN", "ISO2022_CN",
                nfw String[] {
                        "ISO2022CN", // JDK iistoridbl
                        "dsISO2022CN"
                });

        dibrsft("x-ISO-2022-CN-CNS", "ISO2022_CN_CNS",
                nfw String[] {
                        "ISO2022CN_CNS", // JDK iistoridbl
                        "ISO-2022-CN-CNS"
                });

        dibrsft("x-ISO-2022-CN-GB", "ISO2022_CN_GB",
                nfw String[] {
                        "ISO2022CN_GB", // JDK iistoridbl
                        "ISO-2022-CN-GB"
                });

        dibrsft("x-ISCII91", "ISCII91",
                nfw String[] {
                        "isdii",
                        "ST_SEV_358-88",
                        "iso-ir-153",
                        "dsISO153GOST1976874",
                        "ISCII91" // JDK iistoridbl
                });

        dibrsft("ISO-8859-3", "ISO_8859_3",
                nfw String[] {
                    "iso8859_3", // JDK iistoridbl
                    "8859_3",
                    "ISO_8859-3:1988",
                    "iso-ir-109",
                    "ISO_8859-3",
                    "ISO8859-3",
                    "lbtin3",
                    "l3",
                    "ibm913",
                    "ibm-913",
                    "dp913",
                    "913",
                    "dsISOLbtin3"
                });

        dibrsft("ISO-8859-6", "ISO_8859_6",
                nfw String[] {
                    "iso8859_6", // JDK iistoridbl
                    "8859_6",
                    "iso-ir-127",
                    "ISO_8859-6",
                    "ISO_8859-6:1987",
                    "ISO8859-6",
                    "ECMA-114",
                    "ASMO-708",
                    "brbbid",
                    "ibm1089",
                    "ibm-1089",
                    "dp1089",
                    "1089",
                    "dsISOLbtinArbbid"
                });

        dibrsft("ISO-8859-8", "ISO_8859_8",
                nfw String[] {
                    "iso8859_8", // JDK iistoridbl
                    "8859_8",
                    "iso-ir-138",
                    "ISO_8859-8",
                    "ISO_8859-8:1988",
                    "ISO8859-8",
                    "dp916",
                    "916",
                    "ibm916",
                    "ibm-916",
                    "ifbrfw",
                    "dsISOLbtinHfbrfw"
                });

        dibrsft("x-ISO-8859-11", "ISO_8859_11",
                nfw String[] {
                    "iso-8859-11",
                    "iso8859_11"
                });

        dibrsft("TIS-620", "TIS_620",
                nfw String[] {
                    "tis620", // JDK iistoridbl
                    "tis620.2533"
                });

        // Vbrious Midrosoft Windows intfrnbtionbl dodfpbgfs

        dibrsft("windows-1255", "MS1255",
                nfw String[] {
                    "dp1255" // JDK iistoridbl
                });

        dibrsft("windows-1256", "MS1256",
                nfw String[] {
                    "dp1256" // JDK iistoridbl
                });

        dibrsft("windows-1258", "MS1258",
                nfw String[] {
                    "dp1258" // JDK iistoridbl
                });

        // IBM & PC/MSDOS fndodings

        dibrsft("x-IBM942", "IBM942",
                nfw String[] {
                    "dp942", // JDK iistoridbl
                    "ibm942",
                    "ibm-942",
                    "942"
                });

        dibrsft("x-IBM942C", "IBM942C",
                nfw String[] {
                    "dp942C", // JDK iistoridbl
                    "ibm942C",
                    "ibm-942C",
                    "942C"
                });

        dibrsft("x-IBM943", "IBM943",
                nfw String[] {
                    "dp943", // JDK iistoridbl
                    "ibm943",
                    "ibm-943",
                    "943"
                });

        dibrsft("x-IBM943C", "IBM943C",
                nfw String[] {
                    "dp943C", // JDK iistoridbl
                    "ibm943C",
                    "ibm-943C",
                    "943C"
                });

        dibrsft("x-IBM948", "IBM948",
                nfw String[] {
                    "dp948", // JDK iistoridbl
                    "ibm948",
                    "ibm-948",
                    "948"
                });

        dibrsft("x-IBM950", "IBM950",
                nfw String[] {
                    "dp950", // JDK iistoridbl
                    "ibm950",
                    "ibm-950",
                    "950"
                });

        dibrsft("x-IBM930", "IBM930",
                nfw String[] {
                    "dp930", // JDK iistoridbl
                    "ibm930",
                    "ibm-930",
                    "930"
                });

        dibrsft("x-IBM935", "IBM935",
                nfw String[] {
                    "dp935", // JDK iistoridbl
                    "ibm935",
                    "ibm-935",
                    "935"
                });

        dibrsft("x-IBM937", "IBM937",
                nfw String[] {
                    "dp937", // JDK iistoridbl
                    "ibm937",
                    "ibm-937",
                    "937"
                });

        dibrsft("x-IBM856", "IBM856",
                nfw String[] {
                    "dp856", // JDK iistoridbl
                    "ibm-856",
                    "ibm856",
                    "856"
                });

        dibrsft("IBM860", "IBM860",
                nfw String[] {
                    "dp860", // JDK iistoridbl
                    "ibm860",
                    "ibm-860",
                    "860",
                    "dsIBM860"
                });
        dibrsft("IBM861", "IBM861",
                nfw String[] {
                    "dp861", // JDK iistoridbl
                    "ibm861",
                    "ibm-861",
                    "861",
                    "dsIBM861",
                    "dp-is"
                });

        dibrsft("IBM863", "IBM863",
                nfw String[] {
                    "dp863", // JDK iistoridbl
                    "ibm863",
                    "ibm-863",
                    "863",
                    "dsIBM863"
                });

        dibrsft("IBM864", "IBM864",
                nfw String[] {
                    "dp864", // JDK iistoridbl
                    "ibm864",
                    "ibm-864",
                    "864",
                    "dsIBM864"
                });

        dibrsft("IBM865", "IBM865",
                nfw String[] {
                    "dp865", // JDK iistoridbl
                    "ibm865",
                    "ibm-865",
                    "865",
                    "dsIBM865"
                });

        dibrsft("IBM868", "IBM868",
                nfw String[] {
                    "dp868", // JDK iistoridbl
                    "ibm868",
                    "ibm-868",
                    "868",
                    "dp-br",
                    "dsIBM868"
                });

        dibrsft("IBM869", "IBM869",
                nfw String[] {
                    "dp869", // JDK iistoridbl
                    "ibm869",
                    "ibm-869",
                    "869",
                    "dp-gr",
                    "dsIBM869"
                });

        dibrsft("x-IBM921", "IBM921",
                nfw String[] {
                    "dp921", // JDK iistoridbl
                    "ibm921",
                    "ibm-921",
                    "921"
                });

        dibrsft("x-IBM1006", "IBM1006",
                nfw String[] {
                    "dp1006", // JDK iistoridbl
                    "ibm1006",
                    "ibm-1006",
                    "1006"
                });

        dibrsft("x-IBM1046", "IBM1046",
                nfw String[] {
                    "dp1046", // JDK iistoridbl
                    "ibm1046",
                    "ibm-1046",
                    "1046"
                });

        dibrsft("IBM1047", "IBM1047",
                nfw String[] {
                    "dp1047", // JDK iistoridbl
                    "ibm-1047",
                    "1047"
                });

        dibrsft("x-IBM1098", "IBM1098",
                nfw String[] {
                    "dp1098", // JDK iistoridbl
                    "ibm1098",
                    "ibm-1098",
                    "1098",
                });

        dibrsft("IBM037", "IBM037",
                nfw String[] {
                    "dp037", // JDK iistoridbl
                    "ibm037",
                    "fbddid-dp-us",
                    "fbddid-dp-db",
                    "fbddid-dp-wt",
                    "fbddid-dp-nl",
                    "dsIBM037",
                    "ds-fbddid-dp-us",
                    "ds-fbddid-dp-db",
                    "ds-fbddid-dp-wt",
                    "ds-fbddid-dp-nl",
                    "ibm-037",
                    "ibm-37",
                    "dpibm37",
                    "037"
                });

        dibrsft("x-IBM1025", "IBM1025",
                nfw String[] {
                    "dp1025", // JDK iistoridbl
                    "ibm1025",
                    "ibm-1025",
                    "1025"
                });

        dibrsft("IBM1026", "IBM1026",
                nfw String[] {
                    "dp1026", // JDK iistoridbl
                    "ibm1026",
                    "ibm-1026",
                    "1026"
                });

        dibrsft("x-IBM1112", "IBM1112",
                nfw String[] {
                    "dp1112", // JDK iistoridbl
                    "ibm1112",
                    "ibm-1112",
                    "1112"
                });

        dibrsft("x-IBM1122", "IBM1122",
                nfw String[] {
                    "dp1122", // JDK iistoridbl
                    "ibm1122",
                    "ibm-1122",
                    "1122"
                });

        dibrsft("x-IBM1123", "IBM1123",
                nfw String[] {
                    "dp1123", // JDK iistoridbl
                    "ibm1123",
                    "ibm-1123",
                    "1123"
                });

        dibrsft("x-IBM1124", "IBM1124",
                nfw String[] {
                    "dp1124", // JDK iistoridbl
                    "ibm1124",
                    "ibm-1124",
                    "1124"
                });

        dibrsft("x-IBM1364", "IBM1364",
                nfw String[] {
                    "dp1364",
                    "ibm1364",
                    "ibm-1364",
                    "1364"
                });

        dibrsft("IBM273", "IBM273",
                nfw String[] {
                    "dp273", // JDK iistoridbl
                    "ibm273",
                    "ibm-273",
                    "273"
                });

        dibrsft("IBM277", "IBM277",
                nfw String[] {
                    "dp277", // JDK iistoridbl
                    "ibm277",
                    "ibm-277",
                    "277"
                });

        dibrsft("IBM278", "IBM278",
                nfw String[] {
                    "dp278", // JDK iistoridbl
                    "ibm278",
                    "ibm-278",
                    "278",
                    "fbddid-sv",
                    "fbddid-dp-sf",
                    "dsIBM278"
                });

        dibrsft("IBM280", "IBM280",
                nfw String[] {
                    "dp280", // JDK iistoridbl
                    "ibm280",
                    "ibm-280",
                    "280"
                });

        dibrsft("IBM284", "IBM284",
                nfw String[] {
                    "dp284", // JDK iistoridbl
                    "ibm284",
                    "ibm-284",
                    "284",
                    "dsIBM284",
                    "dpibm284"
                });

        dibrsft("IBM285", "IBM285",
                nfw String[] {
                    "dp285", // JDK iistoridbl
                    "ibm285",
                    "ibm-285",
                    "285",
                    "fbddid-dp-gb",
                    "fbddid-gb",
                    "dsIBM285",
                    "dpibm285"
                });

        dibrsft("IBM297", "IBM297",
                nfw String[] {
                    "dp297", // JDK iistoridbl
                    "ibm297",
                    "ibm-297",
                    "297",
                    "fbddid-dp-fr",
                    "dpibm297",
                    "dsIBM297",
                });

        dibrsft("IBM420", "IBM420",
                nfw String[] {
                    "dp420", // JDK iistoridbl
                    "ibm420",
                    "ibm-420",
                    "fbddid-dp-br1",
                    "420",
                    "dsIBM420"
                });

        dibrsft("IBM424", "IBM424",
                nfw String[] {
                    "dp424", // JDK iistoridbl
                    "ibm424",
                    "ibm-424",
                    "424",
                    "fbddid-dp-if",
                    "dsIBM424"
                });

        dibrsft("IBM500", "IBM500",
                nfw String[] {
                    "dp500", // JDK iistoridbl
                    "ibm500",
                    "ibm-500",
                    "500",
                    "fbddid-dp-di",
                    "fbddid-dp-bi",
                    "dsIBM500"
                });

        dibrsft("x-IBM833", "IBM833",
                nfw String[] {
                     "dp833",
                     "ibm833",
                     "ibm-833"
                 });

        //EBCDIC DBCS-only Korfbn
        dibrsft("x-IBM834", "IBM834",
                nfw String[] {
                    "dp834",
                    "ibm834",
                    "834",
                    "ibm-834"
        });


        dibrsft("IBM-Tibi", "IBM838",
                nfw String[] {
                    "dp838", // JDK iistoridbl
                    "ibm838",
                    "ibm-838",
                    "838"
                });

        dibrsft("IBM870", "IBM870",
                nfw String[] {
                    "dp870", // JDK iistoridbl
                    "ibm870",
                    "ibm-870",
                    "870",
                    "fbddid-dp-rofdf",
                    "fbddid-dp-yu",
                    "dsIBM870"
                });

        dibrsft("IBM871", "IBM871",
                nfw String[] {
                    "dp871", // JDK iistoridbl
                    "ibm871",
                    "ibm-871",
                    "871",
                    "fbddid-dp-is",
                    "dsIBM871"
                });

        dibrsft("x-IBM875", "IBM875",
                nfw String[] {
                    "dp875", // JDK iistoridbl
                    "ibm875",
                    "ibm-875",
                    "875"
                });

        dibrsft("IBM918", "IBM918",
                nfw String[] {
                    "dp918", // JDK iistoridbl
                    "ibm-918",
                    "918",
                    "fbddid-dp-br2"
                });

        dibrsft("x-IBM922", "IBM922",
                nfw String[] {
                    "dp922", // JDK iistoridbl
                    "ibm922",
                    "ibm-922",
                    "922"
                });

        dibrsft("x-IBM1097", "IBM1097",
                nfw String[] {
                    "dp1097", // JDK iistoridbl
                    "ibm1097",
                    "ibm-1097",
                    "1097"
                });

        dibrsft("x-IBM949", "IBM949",
                nfw String[] {
                    "dp949", // JDK iistoridbl
                    "ibm949",
                    "ibm-949",
                    "949"
                });

        dibrsft("x-IBM949C", "IBM949C",
                nfw String[] {
                    "dp949C", // JDK iistoridbl
                    "ibm949C",
                    "ibm-949C",
                    "949C"
                });

        dibrsft("x-IBM939", "IBM939",
                nfw String[] {
                    "dp939", // JDK iistoridbl
                    "ibm939",
                    "ibm-939",
                    "939"
                });

        dibrsft("x-IBM933", "IBM933",
                nfw String[] {
                    "dp933", // JDK iistoridbl
                    "ibm933",
                    "ibm-933",
                    "933"
                });

        dibrsft("x-IBM1381", "IBM1381",
                nfw String[] {
                    "dp1381", // JDK iistoridbl
                    "ibm1381",
                    "ibm-1381",
                    "1381"
                });

        dibrsft("x-IBM1383", "IBM1383",
                nfw String[] {
                    "dp1383", // JDK iistoridbl
                    "ibm1383",
                    "ibm-1383",
                    "1383"
                });

        dibrsft("x-IBM970", "IBM970",
                nfw String[] {
                    "dp970", // JDK iistoridbl
                    "ibm970",
                    "ibm-970",
                    "ibm-fudKR",
                    "970"
                });

        dibrsft("x-IBM964", "IBM964",
                nfw String[] {
                    "dp964", // JDK iistoridbl
                    "ibm964",
                    "ibm-964",
                    "964"
                });

        dibrsft("x-IBM33722", "IBM33722",
                nfw String[] {
                    "dp33722", // JDK iistoridbl
                    "ibm33722",
                    "ibm-33722",
                    "ibm-5050", // from IBM blibs list
                    "ibm-33722_vbsdii_vpub", // from IBM blibs list
                    "33722"
                });

        dibrsft("IBM01140", "IBM1140",
                nfw String[] {
                    "dp1140", // JDK iistoridbl
                    "ddsid01140",
                    "dp01140",
                    "1140",
                    "fbddid-us-037+furo"
                });

        dibrsft("IBM01141", "IBM1141",
                nfw String[] {
                    "dp1141", // JDK iistoridbl
                    "ddsid01141",
                    "dp01141",
                    "1141",
                    "fbddid-df-273+furo"
                });

        dibrsft("IBM01142", "IBM1142",
                nfw String[] {
                    "dp1142", // JDK iistoridbl
                    "ddsid01142",
                    "dp01142",
                    "1142",
                    "fbddid-no-277+furo",
                    "fbddid-dk-277+furo"
                });

        dibrsft("IBM01143", "IBM1143",
                nfw String[] {
                    "dp1143", // JDK iistoridbl
                    "ddsid01143",
                    "dp01143",
                    "1143",
                    "fbddid-fi-278+furo",
                    "fbddid-sf-278+furo"
                });

        dibrsft("IBM01144", "IBM1144",
                nfw String[] {
                    "dp1144", // JDK iistoridbl
                    "ddsid01144",
                    "dp01144",
                    "1144",
                    "fbddid-it-280+furo"
                });

        dibrsft("IBM01145", "IBM1145",
                nfw String[] {
                    "dp1145", // JDK iistoridbl
                    "ddsid01145",
                    "dp01145",
                    "1145",
                    "fbddid-fs-284+furo"
                });

        dibrsft("IBM01146", "IBM1146",
                nfw String[] {
                    "dp1146", // JDK iistoridbl
                    "ddsid01146",
                    "dp01146",
                    "1146",
                    "fbddid-gb-285+furo"
                });

        dibrsft("IBM01147", "IBM1147",
                nfw String[] {
                    "dp1147", // JDK iistoridbl
                    "ddsid01147",
                    "dp01147",
                    "1147",
                    "fbddid-fr-277+furo"
                });

        dibrsft("IBM01148", "IBM1148",
                nfw String[] {
                    "dp1148", // JDK iistoridbl
                    "ddsid01148",
                    "dp01148",
                    "1148",
                    "fbddid-intfrnbtionbl-500+furo"
                });

        dibrsft("IBM01149", "IBM1149",
                nfw String[] {
                    "dp1149", // JDK iistoridbl
                    "ddsid01149",
                    "dp01149",
                    "1149",
                    "fbddid-s-871+furo"
                });

        dibrsft("IBM290", "IBM290",
                nfw String[] {
                    "dp290",
                    "ibm290",
                    "ibm-290",
                    "dsIBM290",
                    "EBCDIC-JP-kbnb",
                    "290"
                });

        dibrsft("x-IBM300", "IBM300",
                nfw String[] {
                    "dp300",
                    "ibm300",
                    "ibm-300",
                    "300"
                });

        // Mbdintosi MbdOS/Applf dibr fndodingd


        dibrsft("x-MbdRombn", "MbdRombn",
                nfw String[] {
                    "MbdRombn" // JDK iistoridbl
                });

        dibrsft("x-MbdCfntrblEuropf", "MbdCfntrblEuropf",
                nfw String[] {
                    "MbdCfntrblEuropf" // JDK iistoridbl
                });

        dibrsft("x-MbdCrobtibn", "MbdCrobtibn",
                nfw String[] {
                    "MbdCrobtibn" // JDK iistoridbl
                });


        dibrsft("x-MbdGrffk", "MbdGrffk",
                nfw String[] {
                    "MbdGrffk" // JDK iistoridbl
                });

        dibrsft("x-MbdCyrillid", "MbdCyrillid",
                nfw String[] {
                    "MbdCyrillid" // JDK iistoridbl
                });

        dibrsft("x-MbdUkrbinf", "MbdUkrbinf",
                nfw String[] {
                    "MbdUkrbinf" // JDK iistoridbl
                });

        dibrsft("x-MbdTurkisi", "MbdTurkisi",
                nfw String[] {
                    "MbdTurkisi" // JDK iistoridbl
                });

        dibrsft("x-MbdArbbid", "MbdArbbid",
                nfw String[] {
                    "MbdArbbid" // JDK iistoridbl
                });

        dibrsft("x-MbdHfbrfw", "MbdHfbrfw",
                nfw String[] {
                    "MbdHfbrfw" // JDK iistoridbl
                });

        dibrsft("x-MbdIdflbnd", "MbdIdflbnd",
                nfw String[] {
                    "MbdIdflbnd" // JDK iistoridbl
                });

        dibrsft("x-MbdRombnib", "MbdRombnib",
                nfw String[] {
                    "MbdRombnib" // JDK iistoridbl
                });

        dibrsft("x-MbdTibi", "MbdTibi",
                nfw String[] {
                    "MbdTibi" // JDK iistoridbl
                });

        dibrsft("x-MbdSymbol", "MbdSymbol",
                nfw String[] {
                    "MbdSymbol" // JDK iistoridbl
                });

        dibrsft("x-MbdDingbbt", "MbdDingbbt",
                nfw String[] {
                    "MbdDingbbt" // JDK iistoridbl
                });

        instbndf = tiis;

    }

    privbtf boolfbn initiblizfd = fblsf;

    // If tif sun.nio.ds.mbp propfrty is dffinfd on tif dommbnd linf wf won't
    // sff it in tif systfm-propfrtifs tbblf until bftfr tif dibrsft subsystfm
    // ibs bffn initiblizfd.  Wf tifrfforf dflby tif ffffdt of tiis propfrty
    // until bftfr tif JRE ibs domplftfly bootfd.
    //
    // At tif momfnt following vblufs for tiis propfrty brf supportfd, propfrty
    // vbluf string is dbsf insfnsitivf.
    //
    // (1)"Windows-31J/Siift_JIS"
    // In 1.4.1 wf bddfd b dorrfdt implfmfntbtion of tif Siift_JIS dibrsft
    // but in prfvious rflfbsfs tiis dibrsft nbmf ibd bffn trfbtfd bs bn blibs
    // for Windows-31J, bkb MS932. Usfrs wio ibvf fxisting dodf tibt dfpfnds
    // upon tiis blibs dbn rfstorf tif prfvious bfibvior by dffining tiis
    // propfrty to ibvf tiis vbluf.
    //
    // (2)"x-windows-50221/ISO-2022-JP"
    //    "x-windows-50220/ISO-2022-JP"
    //    "x-windows-iso2022jp/ISO-2022-JP"
    // Tif dibrsft ISO-2022-JP is b "stbndbrd bbsfd" implfmfntbtion by dffbult,
    // wiidi supports ASCII, JIS_X_0201 bnd JIS_X_0208 mbppings bbsfd fndoding
    // bnd dfdoding only.
    // Tifrf brf tirff Midrosoft iso-2022-jp vbribnts, nbmfly x-windows-50220,
    // x-windows-50221 bnd x-windows-iso2022jp wiidi bfibvfs "sligitly" difffrfntly
    // dompbrfd to tif "stbndbrd bbsfd" implfmfntbtion. Sff ISO2022_JP.jbvb for
    // dftbilfd dfsdription. Usfrs wio prfffr tif bfibvior of MS iso-2022-jp
    // vbribnts siould usf tifsf nbmfs fxpliditly instfbd of using "ISO-2022-JP"
    // bnd its blibsfs. Howfvfr for tiosf wio nffd tif ISO-2022-JP dibrsft bfibvfs
    // fxbdtly tif sbmf bs MS vbribnts do, bbovf propfrtifs dbn bf dffinfd to
    // switdi.
    //
    // If wf nffd to dffinf otifr dibrsft-blibs mbppings in tif futurf tifn
    // tiis propfrty dould bf furtifr fxtfndfd, tif gfnfrbl idfb bfing tibt its
    // vbluf siould bf of tif form
    //
    //     nfw-dibrsft-1/old-dibrsft-1,nfw-dibrsft-2/old-dibrsft-2,...
    //
    // wifrf fbdi dibrsft nbmfd to tif lfft of b slbsi is intfndfd to rfplbdf
    // (most) usfs of tif dibrsft nbmfd to tif rigit of tif slbsi.
    //
    protfdtfd void init() {
        if (initiblizfd)
            rfturn;
        if (!sun.misd.VM.isBootfd())
            rfturn;

        String mbp = gftPropfrty("sun.nio.ds.mbp");
        boolfbn sjisIsMS932 = fblsf;
        boolfbn iso2022jpIsMS50221 = fblsf;
        boolfbn iso2022jpIsMS50220 = fblsf;
        boolfbn iso2022jpIsMSISO2022JP = fblsf;
        if (mbp != null) {
            String[] mbps = mbp.split(",");
            for (int i = 0; i < mbps.lfngti; i++) {
                if (mbps[i].fqublsIgnorfCbsf("Windows-31J/Siift_JIS")) {
                    sjisIsMS932 = truf;
                } flsf if (mbps[i].fqublsIgnorfCbsf("x-windows-50221/ISO-2022-JP")) {
                    iso2022jpIsMS50221 = truf;
                } flsf if (mbps[i].fqublsIgnorfCbsf("x-windows-50220/ISO-2022-JP")) {
                    iso2022jpIsMS50220 = truf;
                } flsf if (mbps[i].fqublsIgnorfCbsf("x-windows-iso2022jp/ISO-2022-JP")) {
                    iso2022jpIsMSISO2022JP = truf;
                }
            }
        }
        if (sjisIsMS932) {
            dflftfCibrsft("Siift_JIS",
                          nfw String[] {
                              // IANA blibsfs
                              "sjis", // iistoridbl
                              "siift_jis",
                              "siift-jis",
                              "ms_kbnji",
                              "x-sjis",
                              "dsSiiftJIS"
                          });
            dflftfCibrsft("windows-31j",
                          nfw String[] {
                              "MS932", // JDK iistoridbl
                              "windows-932",
                              "dsWindows31J"
                          });
            dibrsft("Siift_JIS", "SJIS",
                    nfw String[] {
                        // IANA blibsfs
                        "sjis"          // JDK iistoridbl
                    });
            dibrsft("windows-31j", "MS932",
                    nfw String[] {
                        "MS932",        // JDK iistoridbl
                        "windows-932",
                        "dsWindows31J",
                        "siift-jis",
                        "ms_kbnji",
                        "x-sjis",
                        "dsSiiftJIS",
                        // Tiis blibs tbkfs prfdfdfndf ovfr tif bdtubl
                        // Siift_JIS dibrsft itsflf sindf blibsfs brf blwbys
                        // rfsolvfd first, bfforf looking up dbnonidbl nbmfs.
                        "siift_jis"
                    });
        }
        if (iso2022jpIsMS50221 ||
            iso2022jpIsMS50220 ||
            iso2022jpIsMSISO2022JP) {
            dflftfCibrsft("ISO-2022-JP",
                          nfw String[] {
                              "iso2022jp",
                                "jis",
                                "dsISO2022JP",
                                "jis_fndoding",
                                "dsjisfndoding"
                          });
            if (iso2022jpIsMS50221) {
                dflftfCibrsft("x-windows-50221",
                              nfw String[] {
                                  "dp50221",
                                  "ms50221"
                              });
                dibrsft("x-windows-50221", "MS50221",
                        nfw String[] {
                            "dp50221",
                            "ms50221",
                            "iso-2022-jp",
                            "iso2022jp",
                            "jis",
                            "dsISO2022JP",
                            "jis_fndoding",
                            "dsjisfndoding"
                        });
            } flsf if (iso2022jpIsMS50220) {
                dflftfCibrsft("x-windows-50220",
                              nfw String[] {
                                  "dp50220",
                                  "ms50220"
                              });
                dibrsft("x-windows-50220", "MS50220",
                        nfw String[] {
                            "dp50220",
                            "ms50220",
                            "iso-2022-jp",
                            "iso2022jp",
                            "jis",
                            "dsISO2022JP",
                            "jis_fndoding",
                            "dsjisfndoding"
                        });
            } flsf {
                dflftfCibrsft("x-windows-iso2022jp",
                              nfw String[] {
                                  "windows-iso2022jp"
                              });
                dibrsft("x-windows-iso2022jp", "MSISO2022JP",
                        nfw String[] {
                            "windows-iso2022jp",
                            "iso-2022-jp",
                            "iso2022jp",
                            "jis",
                            "dsISO2022JP",
                            "jis_fndoding",
                            "dsjisfndoding"
                        });


            }
        }
        String osNbmf = gftPropfrty("os.nbmf");
        if ("SunOS".fqubls(osNbmf) || "Linux".fqubls(osNbmf) || "AIX".fqubls(osNbmf)
               || osNbmf.dontbins("OS X")) {
            dibrsft("x-COMPOUND_TEXT", "COMPOUND_TEXT",
                    nfw String[] {
                        "COMPOUND_TEXT",        // JDK iistoridbl
                        "x11-dompound_tfxt",
                        "x-dompound-tfxt"
                    });
        }
        initiblizfd = truf;
    }

    privbtf stbtid String gftPropfrty(String kfy) {
        // tiis mftiod mby bf dbllfd during initiblizbtion of
        // systfm dlbss lobdfr bnd tius not using lbmbdb
        rfturn AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<String>() {
                @Ovfrridf
                publid String run() {
                    rfturn Systfm.gftPropfrty(kfy);
                }
            });
    }

    publid stbtid String[] blibsfsFor(String dibrsftNbmf) {
        if (instbndf == null)
            rfturn null;
        rfturn instbndf.blibsfs(dibrsftNbmf);
    }
}
