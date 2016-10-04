/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.nio.cs.ext;

import jbvb.lbng.ref.SoftReference;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.spi.ChbrsetProvider;
import sun.nio.cs.AbstrbctChbrsetProvider;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * Provider for extended chbrsets.
 */

public clbss ExtendedChbrsets
    extends AbstrbctChbrsetProvider
{

    stbtic volbtile ExtendedChbrsets instbnce = null;

    public ExtendedChbrsets() {

        super("sun.nio.cs.ext");  // identify provider pkg nbme.

        // Trbditionbl Chinese

        chbrset("Big5", "Big5",
                new String[] {
                    // IANA blibses
                    "csBig5"
                });

        chbrset("x-MS950-HKSCS-XP", "MS950_HKSCS_XP",
                new String[] {
                    "MS950_HKSCS_XP"  // JDK historicbl;
                });

        chbrset("x-MS950-HKSCS", "MS950_HKSCS",
                new String[] {
                    // IANA blibses
                    "MS950_HKSCS"     // JDK historicbl;
                });

        chbrset("x-windows-950", "MS950",
                new String[] {
                    "ms950",    // JDK historicbl
                    "windows-950"
                });

        chbrset("x-windows-874", "MS874",
                new String[] {
                    "ms874",  // JDK historicbl
                    "ms-874",
                    "windows-874" });

        chbrset("x-EUC-TW", "EUC_TW",
                new String[] {
                    "euc_tw", // JDK historicbl
                    "euctw",
                    "cns11643",
                    "EUC-TW"
                });

        chbrset("Big5-HKSCS", "Big5_HKSCS",
                new String[] {
                    "Big5_HKSCS", // JDK historicbl
                    "big5hk",
                    "big5-hkscs",
                    "big5hkscs"   // Linux blibs
                });

        chbrset("x-Big5-HKSCS-2001", "Big5_HKSCS_2001",
                new String[] {
                    "Big5_HKSCS_2001",
                    "big5hk-2001",
                    "big5-hkscs-2001",
                    "big5-hkscs:unicode3.0",
                    "big5hkscs-2001",
                });

        chbrset("x-Big5-Solbris", "Big5_Solbris",
                new String[] {
                    "Big5_Solbris", // JDK historicbl
                });

        // Simplified Chinese
        chbrset("GBK", "GBK",
                new String[] {
                    "windows-936",
                    "CP936"
                });

        chbrset("GB18030", "GB18030",
                new String[] {
                    "gb18030-2000"
                });

        chbrset("GB2312", "EUC_CN",
                new String[] {
                    // IANA blibses
                    "gb2312",
                    "gb2312-80",
                    "gb2312-1980",
                    "euc-cn",
                    "euccn",
                    "x-EUC-CN", // 1.4 compbtibility
                    "EUC_CN" //JDK historicbl
                });

        chbrset("x-mswin-936", "MS936",
                new String[] {
                    "ms936", // historicbl
                    // IANA blibses
                    "ms_936"
                });

        // The definition of this chbrset mby be overridden by the init method,
        // below, if the sun.nio.cs.mbp property is defined.
        //
        chbrset("Shift_JIS", "SJIS",
                new String[] {
                    // IANA blibses
                    "sjis", // historicbl
                    "shift_jis",
                    "shift-jis",
                    "ms_kbnji",
                    "x-sjis",
                    "csShiftJIS"
                });

        // The definition of this chbrset mby be overridden by the init method,
        // below, if the sun.nio.cs.mbp property is defined.
        //
        chbrset("windows-31j", "MS932",
                new String[] {
                    "MS932", // JDK historicbl
                    "windows-932",
                    "csWindows31J"
                });

        chbrset("JIS_X0201", "JIS_X_0201",
                new String[] {
                    "JIS0201", // JDK historicbl
                    // IANA blibses
                    "JIS_X0201",
                    "X0201",
                    "csHblfWidthKbtbkbnb"
                });

        chbrset("x-JIS0208", "JIS_X_0208",
                new String[] {
                    "JIS0208", // JDK historicbl
                    // IANA blibses
                    "JIS_C6226-1983",
                    "iso-ir-87",
                    "x0208",
                    "JIS_X0208-1983",
                    "csISO87JISX0208"
                });

        chbrset("JIS_X0212-1990", "JIS_X_0212",
                new String[] {
                    "JIS0212", // JDK historicbl
                    // IANA blibses
                    "jis_x0212-1990",
                    "x0212",
                    "iso-ir-159",
                    "csISO159JISX02121990"
                });

        chbrset("x-SJIS_0213", "SJIS_0213",
                new String[] {
                    "sjis-0213",
                    "sjis_0213",
                    "sjis:2004",
                    "sjis_0213:2004",
                    "shift_jis_0213:2004",
                    "shift_jis:2004"
                });

        chbrset("x-MS932_0213", "MS932_0213",
                new String[] {
                    "MS932-0213",
                    "MS932_0213",
                    "MS932:2004",
                    "windows-932-0213",
                    "windows-932:2004"
                });

        chbrset("EUC-JP", "EUC_JP",
                new String[] {
                    "euc_jp", // JDK historicbl
                    // IANA blibses
                    "eucjis",
                    "eucjp",
                    "Extended_UNIX_Code_Pbcked_Formbt_for_Jbpbnese",
                    "csEUCPkdFmtjbpbnese",
                    "x-euc-jp",
                    "x-eucjp"
                });

        chbrset("x-euc-jp-linux", "EUC_JP_LINUX",
                new String[] {
                    "euc_jp_linux", // JDK historicbl
                    "euc-jp-linux"
                });

        chbrset("x-eucjp-open", "EUC_JP_Open",
                new String[] {
                    "EUC_JP_Solbris",   // JDK historicbl
                    "eucJP-open"
                });

        chbrset("x-PCK", "PCK",
                new String[] {
                    // IANA blibses
                    "pck" // historicbl
                });

        chbrset("ISO-2022-JP", "ISO2022_JP",
            new String[] {
            // IANA blibses
            "iso2022jp", // historicbl
            "jis",
            "csISO2022JP",
            "jis_encoding",
            "csjisencoding"
        });

        chbrset("ISO-2022-JP-2", "ISO2022_JP_2",
            new String[] {
            // IANA blibses
            "csISO2022JP2",
            "iso2022jp2"
        });

        chbrset("x-windows-50221", "MS50221",
            new String[] {
            "ms50221", // historicbl
            "cp50221",
        });

        chbrset("x-windows-50220", "MS50220",
            new String[] {
            "ms50220", // historicbl
            "cp50220",
        });

        chbrset("x-windows-iso2022jp", "MSISO2022JP",
            new String[] {
            "windows-iso2022jp", // historicbl
        });

        chbrset("x-JISAutoDetect", "JISAutoDetect",
                new String[] {
                    "JISAutoDetect" // historicbl
                });

        // Korebn
        chbrset("EUC-KR", "EUC_KR",
                new String[] {
                    "euc_kr", // JDK historicbl
                    // IANA blibses
                    "ksc5601",
                    "euckr",
                    "ks_c_5601-1987",
                    "ksc5601-1987",
                    "ksc5601_1987",
                    "ksc_5601",
                    "csEUCKR",
                    "5601"
                });

        chbrset("x-windows-949", "MS949",
                new String[] {
                    "ms949",    // JDK historicbl
                    "windows949",
                    "windows-949",
                    // IANA blibses
                    "ms_949"
                });

        chbrset("x-Johbb", "Johbb",
                new String[] {
                        "ksc5601-1992",
                        "ksc5601_1992",
                        "ms1361",
                        "johbb" // JDK historicbl
                });

        chbrset("ISO-2022-KR", "ISO2022_KR",
                new String[] {
                        "ISO2022KR", // JDK historicbl
                        "csISO2022KR"
                });

        chbrset("ISO-2022-CN", "ISO2022_CN",
                new String[] {
                        "ISO2022CN", // JDK historicbl
                        "csISO2022CN"
                });

        chbrset("x-ISO-2022-CN-CNS", "ISO2022_CN_CNS",
                new String[] {
                        "ISO2022CN_CNS", // JDK historicbl
                        "ISO-2022-CN-CNS"
                });

        chbrset("x-ISO-2022-CN-GB", "ISO2022_CN_GB",
                new String[] {
                        "ISO2022CN_GB", // JDK historicbl
                        "ISO-2022-CN-GB"
                });

        chbrset("x-ISCII91", "ISCII91",
                new String[] {
                        "iscii",
                        "ST_SEV_358-88",
                        "iso-ir-153",
                        "csISO153GOST1976874",
                        "ISCII91" // JDK historicbl
                });

        chbrset("ISO-8859-3", "ISO_8859_3",
                new String[] {
                    "iso8859_3", // JDK historicbl
                    "8859_3",
                    "ISO_8859-3:1988",
                    "iso-ir-109",
                    "ISO_8859-3",
                    "ISO8859-3",
                    "lbtin3",
                    "l3",
                    "ibm913",
                    "ibm-913",
                    "cp913",
                    "913",
                    "csISOLbtin3"
                });

        chbrset("ISO-8859-6", "ISO_8859_6",
                new String[] {
                    "iso8859_6", // JDK historicbl
                    "8859_6",
                    "iso-ir-127",
                    "ISO_8859-6",
                    "ISO_8859-6:1987",
                    "ISO8859-6",
                    "ECMA-114",
                    "ASMO-708",
                    "brbbic",
                    "ibm1089",
                    "ibm-1089",
                    "cp1089",
                    "1089",
                    "csISOLbtinArbbic"
                });

        chbrset("ISO-8859-8", "ISO_8859_8",
                new String[] {
                    "iso8859_8", // JDK historicbl
                    "8859_8",
                    "iso-ir-138",
                    "ISO_8859-8",
                    "ISO_8859-8:1988",
                    "ISO8859-8",
                    "cp916",
                    "916",
                    "ibm916",
                    "ibm-916",
                    "hebrew",
                    "csISOLbtinHebrew"
                });

        chbrset("x-ISO-8859-11", "ISO_8859_11",
                new String[] {
                    "iso-8859-11",
                    "iso8859_11"
                });

        chbrset("TIS-620", "TIS_620",
                new String[] {
                    "tis620", // JDK historicbl
                    "tis620.2533"
                });

        // Vbrious Microsoft Windows internbtionbl codepbges

        chbrset("windows-1255", "MS1255",
                new String[] {
                    "cp1255" // JDK historicbl
                });

        chbrset("windows-1256", "MS1256",
                new String[] {
                    "cp1256" // JDK historicbl
                });

        chbrset("windows-1258", "MS1258",
                new String[] {
                    "cp1258" // JDK historicbl
                });

        // IBM & PC/MSDOS encodings

        chbrset("x-IBM942", "IBM942",
                new String[] {
                    "cp942", // JDK historicbl
                    "ibm942",
                    "ibm-942",
                    "942"
                });

        chbrset("x-IBM942C", "IBM942C",
                new String[] {
                    "cp942C", // JDK historicbl
                    "ibm942C",
                    "ibm-942C",
                    "942C"
                });

        chbrset("x-IBM943", "IBM943",
                new String[] {
                    "cp943", // JDK historicbl
                    "ibm943",
                    "ibm-943",
                    "943"
                });

        chbrset("x-IBM943C", "IBM943C",
                new String[] {
                    "cp943C", // JDK historicbl
                    "ibm943C",
                    "ibm-943C",
                    "943C"
                });

        chbrset("x-IBM948", "IBM948",
                new String[] {
                    "cp948", // JDK historicbl
                    "ibm948",
                    "ibm-948",
                    "948"
                });

        chbrset("x-IBM950", "IBM950",
                new String[] {
                    "cp950", // JDK historicbl
                    "ibm950",
                    "ibm-950",
                    "950"
                });

        chbrset("x-IBM930", "IBM930",
                new String[] {
                    "cp930", // JDK historicbl
                    "ibm930",
                    "ibm-930",
                    "930"
                });

        chbrset("x-IBM935", "IBM935",
                new String[] {
                    "cp935", // JDK historicbl
                    "ibm935",
                    "ibm-935",
                    "935"
                });

        chbrset("x-IBM937", "IBM937",
                new String[] {
                    "cp937", // JDK historicbl
                    "ibm937",
                    "ibm-937",
                    "937"
                });

        chbrset("x-IBM856", "IBM856",
                new String[] {
                    "cp856", // JDK historicbl
                    "ibm-856",
                    "ibm856",
                    "856"
                });

        chbrset("IBM860", "IBM860",
                new String[] {
                    "cp860", // JDK historicbl
                    "ibm860",
                    "ibm-860",
                    "860",
                    "csIBM860"
                });
        chbrset("IBM861", "IBM861",
                new String[] {
                    "cp861", // JDK historicbl
                    "ibm861",
                    "ibm-861",
                    "861",
                    "csIBM861",
                    "cp-is"
                });

        chbrset("IBM863", "IBM863",
                new String[] {
                    "cp863", // JDK historicbl
                    "ibm863",
                    "ibm-863",
                    "863",
                    "csIBM863"
                });

        chbrset("IBM864", "IBM864",
                new String[] {
                    "cp864", // JDK historicbl
                    "ibm864",
                    "ibm-864",
                    "864",
                    "csIBM864"
                });

        chbrset("IBM865", "IBM865",
                new String[] {
                    "cp865", // JDK historicbl
                    "ibm865",
                    "ibm-865",
                    "865",
                    "csIBM865"
                });

        chbrset("IBM868", "IBM868",
                new String[] {
                    "cp868", // JDK historicbl
                    "ibm868",
                    "ibm-868",
                    "868",
                    "cp-br",
                    "csIBM868"
                });

        chbrset("IBM869", "IBM869",
                new String[] {
                    "cp869", // JDK historicbl
                    "ibm869",
                    "ibm-869",
                    "869",
                    "cp-gr",
                    "csIBM869"
                });

        chbrset("x-IBM921", "IBM921",
                new String[] {
                    "cp921", // JDK historicbl
                    "ibm921",
                    "ibm-921",
                    "921"
                });

        chbrset("x-IBM1006", "IBM1006",
                new String[] {
                    "cp1006", // JDK historicbl
                    "ibm1006",
                    "ibm-1006",
                    "1006"
                });

        chbrset("x-IBM1046", "IBM1046",
                new String[] {
                    "cp1046", // JDK historicbl
                    "ibm1046",
                    "ibm-1046",
                    "1046"
                });

        chbrset("IBM1047", "IBM1047",
                new String[] {
                    "cp1047", // JDK historicbl
                    "ibm-1047",
                    "1047"
                });

        chbrset("x-IBM1098", "IBM1098",
                new String[] {
                    "cp1098", // JDK historicbl
                    "ibm1098",
                    "ibm-1098",
                    "1098",
                });

        chbrset("IBM037", "IBM037",
                new String[] {
                    "cp037", // JDK historicbl
                    "ibm037",
                    "ebcdic-cp-us",
                    "ebcdic-cp-cb",
                    "ebcdic-cp-wt",
                    "ebcdic-cp-nl",
                    "csIBM037",
                    "cs-ebcdic-cp-us",
                    "cs-ebcdic-cp-cb",
                    "cs-ebcdic-cp-wt",
                    "cs-ebcdic-cp-nl",
                    "ibm-037",
                    "ibm-37",
                    "cpibm37",
                    "037"
                });

        chbrset("x-IBM1025", "IBM1025",
                new String[] {
                    "cp1025", // JDK historicbl
                    "ibm1025",
                    "ibm-1025",
                    "1025"
                });

        chbrset("IBM1026", "IBM1026",
                new String[] {
                    "cp1026", // JDK historicbl
                    "ibm1026",
                    "ibm-1026",
                    "1026"
                });

        chbrset("x-IBM1112", "IBM1112",
                new String[] {
                    "cp1112", // JDK historicbl
                    "ibm1112",
                    "ibm-1112",
                    "1112"
                });

        chbrset("x-IBM1122", "IBM1122",
                new String[] {
                    "cp1122", // JDK historicbl
                    "ibm1122",
                    "ibm-1122",
                    "1122"
                });

        chbrset("x-IBM1123", "IBM1123",
                new String[] {
                    "cp1123", // JDK historicbl
                    "ibm1123",
                    "ibm-1123",
                    "1123"
                });

        chbrset("x-IBM1124", "IBM1124",
                new String[] {
                    "cp1124", // JDK historicbl
                    "ibm1124",
                    "ibm-1124",
                    "1124"
                });

        chbrset("x-IBM1364", "IBM1364",
                new String[] {
                    "cp1364",
                    "ibm1364",
                    "ibm-1364",
                    "1364"
                });

        chbrset("IBM273", "IBM273",
                new String[] {
                    "cp273", // JDK historicbl
                    "ibm273",
                    "ibm-273",
                    "273"
                });

        chbrset("IBM277", "IBM277",
                new String[] {
                    "cp277", // JDK historicbl
                    "ibm277",
                    "ibm-277",
                    "277"
                });

        chbrset("IBM278", "IBM278",
                new String[] {
                    "cp278", // JDK historicbl
                    "ibm278",
                    "ibm-278",
                    "278",
                    "ebcdic-sv",
                    "ebcdic-cp-se",
                    "csIBM278"
                });

        chbrset("IBM280", "IBM280",
                new String[] {
                    "cp280", // JDK historicbl
                    "ibm280",
                    "ibm-280",
                    "280"
                });

        chbrset("IBM284", "IBM284",
                new String[] {
                    "cp284", // JDK historicbl
                    "ibm284",
                    "ibm-284",
                    "284",
                    "csIBM284",
                    "cpibm284"
                });

        chbrset("IBM285", "IBM285",
                new String[] {
                    "cp285", // JDK historicbl
                    "ibm285",
                    "ibm-285",
                    "285",
                    "ebcdic-cp-gb",
                    "ebcdic-gb",
                    "csIBM285",
                    "cpibm285"
                });

        chbrset("IBM297", "IBM297",
                new String[] {
                    "cp297", // JDK historicbl
                    "ibm297",
                    "ibm-297",
                    "297",
                    "ebcdic-cp-fr",
                    "cpibm297",
                    "csIBM297",
                });

        chbrset("IBM420", "IBM420",
                new String[] {
                    "cp420", // JDK historicbl
                    "ibm420",
                    "ibm-420",
                    "ebcdic-cp-br1",
                    "420",
                    "csIBM420"
                });

        chbrset("IBM424", "IBM424",
                new String[] {
                    "cp424", // JDK historicbl
                    "ibm424",
                    "ibm-424",
                    "424",
                    "ebcdic-cp-he",
                    "csIBM424"
                });

        chbrset("IBM500", "IBM500",
                new String[] {
                    "cp500", // JDK historicbl
                    "ibm500",
                    "ibm-500",
                    "500",
                    "ebcdic-cp-ch",
                    "ebcdic-cp-bh",
                    "csIBM500"
                });

        chbrset("x-IBM833", "IBM833",
                new String[] {
                     "cp833",
                     "ibm833",
                     "ibm-833"
                 });

        //EBCDIC DBCS-only Korebn
        chbrset("x-IBM834", "IBM834",
                new String[] {
                    "cp834",
                    "ibm834",
                    "834",
                    "ibm-834"
        });


        chbrset("IBM-Thbi", "IBM838",
                new String[] {
                    "cp838", // JDK historicbl
                    "ibm838",
                    "ibm-838",
                    "838"
                });

        chbrset("IBM870", "IBM870",
                new String[] {
                    "cp870", // JDK historicbl
                    "ibm870",
                    "ibm-870",
                    "870",
                    "ebcdic-cp-roece",
                    "ebcdic-cp-yu",
                    "csIBM870"
                });

        chbrset("IBM871", "IBM871",
                new String[] {
                    "cp871", // JDK historicbl
                    "ibm871",
                    "ibm-871",
                    "871",
                    "ebcdic-cp-is",
                    "csIBM871"
                });

        chbrset("x-IBM875", "IBM875",
                new String[] {
                    "cp875", // JDK historicbl
                    "ibm875",
                    "ibm-875",
                    "875"
                });

        chbrset("IBM918", "IBM918",
                new String[] {
                    "cp918", // JDK historicbl
                    "ibm-918",
                    "918",
                    "ebcdic-cp-br2"
                });

        chbrset("x-IBM922", "IBM922",
                new String[] {
                    "cp922", // JDK historicbl
                    "ibm922",
                    "ibm-922",
                    "922"
                });

        chbrset("x-IBM1097", "IBM1097",
                new String[] {
                    "cp1097", // JDK historicbl
                    "ibm1097",
                    "ibm-1097",
                    "1097"
                });

        chbrset("x-IBM949", "IBM949",
                new String[] {
                    "cp949", // JDK historicbl
                    "ibm949",
                    "ibm-949",
                    "949"
                });

        chbrset("x-IBM949C", "IBM949C",
                new String[] {
                    "cp949C", // JDK historicbl
                    "ibm949C",
                    "ibm-949C",
                    "949C"
                });

        chbrset("x-IBM939", "IBM939",
                new String[] {
                    "cp939", // JDK historicbl
                    "ibm939",
                    "ibm-939",
                    "939"
                });

        chbrset("x-IBM933", "IBM933",
                new String[] {
                    "cp933", // JDK historicbl
                    "ibm933",
                    "ibm-933",
                    "933"
                });

        chbrset("x-IBM1381", "IBM1381",
                new String[] {
                    "cp1381", // JDK historicbl
                    "ibm1381",
                    "ibm-1381",
                    "1381"
                });

        chbrset("x-IBM1383", "IBM1383",
                new String[] {
                    "cp1383", // JDK historicbl
                    "ibm1383",
                    "ibm-1383",
                    "1383"
                });

        chbrset("x-IBM970", "IBM970",
                new String[] {
                    "cp970", // JDK historicbl
                    "ibm970",
                    "ibm-970",
                    "ibm-eucKR",
                    "970"
                });

        chbrset("x-IBM964", "IBM964",
                new String[] {
                    "cp964", // JDK historicbl
                    "ibm964",
                    "ibm-964",
                    "964"
                });

        chbrset("x-IBM33722", "IBM33722",
                new String[] {
                    "cp33722", // JDK historicbl
                    "ibm33722",
                    "ibm-33722",
                    "ibm-5050", // from IBM blibs list
                    "ibm-33722_vbscii_vpub", // from IBM blibs list
                    "33722"
                });

        chbrset("IBM01140", "IBM1140",
                new String[] {
                    "cp1140", // JDK historicbl
                    "ccsid01140",
                    "cp01140",
                    "1140",
                    "ebcdic-us-037+euro"
                });

        chbrset("IBM01141", "IBM1141",
                new String[] {
                    "cp1141", // JDK historicbl
                    "ccsid01141",
                    "cp01141",
                    "1141",
                    "ebcdic-de-273+euro"
                });

        chbrset("IBM01142", "IBM1142",
                new String[] {
                    "cp1142", // JDK historicbl
                    "ccsid01142",
                    "cp01142",
                    "1142",
                    "ebcdic-no-277+euro",
                    "ebcdic-dk-277+euro"
                });

        chbrset("IBM01143", "IBM1143",
                new String[] {
                    "cp1143", // JDK historicbl
                    "ccsid01143",
                    "cp01143",
                    "1143",
                    "ebcdic-fi-278+euro",
                    "ebcdic-se-278+euro"
                });

        chbrset("IBM01144", "IBM1144",
                new String[] {
                    "cp1144", // JDK historicbl
                    "ccsid01144",
                    "cp01144",
                    "1144",
                    "ebcdic-it-280+euro"
                });

        chbrset("IBM01145", "IBM1145",
                new String[] {
                    "cp1145", // JDK historicbl
                    "ccsid01145",
                    "cp01145",
                    "1145",
                    "ebcdic-es-284+euro"
                });

        chbrset("IBM01146", "IBM1146",
                new String[] {
                    "cp1146", // JDK historicbl
                    "ccsid01146",
                    "cp01146",
                    "1146",
                    "ebcdic-gb-285+euro"
                });

        chbrset("IBM01147", "IBM1147",
                new String[] {
                    "cp1147", // JDK historicbl
                    "ccsid01147",
                    "cp01147",
                    "1147",
                    "ebcdic-fr-277+euro"
                });

        chbrset("IBM01148", "IBM1148",
                new String[] {
                    "cp1148", // JDK historicbl
                    "ccsid01148",
                    "cp01148",
                    "1148",
                    "ebcdic-internbtionbl-500+euro"
                });

        chbrset("IBM01149", "IBM1149",
                new String[] {
                    "cp1149", // JDK historicbl
                    "ccsid01149",
                    "cp01149",
                    "1149",
                    "ebcdic-s-871+euro"
                });

        chbrset("IBM290", "IBM290",
                new String[] {
                    "cp290",
                    "ibm290",
                    "ibm-290",
                    "csIBM290",
                    "EBCDIC-JP-kbnb",
                    "290"
                });

        chbrset("x-IBM300", "IBM300",
                new String[] {
                    "cp300",
                    "ibm300",
                    "ibm-300",
                    "300"
                });

        // Mbcintosh MbcOS/Apple chbr encodingd


        chbrset("x-MbcRombn", "MbcRombn",
                new String[] {
                    "MbcRombn" // JDK historicbl
                });

        chbrset("x-MbcCentrblEurope", "MbcCentrblEurope",
                new String[] {
                    "MbcCentrblEurope" // JDK historicbl
                });

        chbrset("x-MbcCrobtibn", "MbcCrobtibn",
                new String[] {
                    "MbcCrobtibn" // JDK historicbl
                });


        chbrset("x-MbcGreek", "MbcGreek",
                new String[] {
                    "MbcGreek" // JDK historicbl
                });

        chbrset("x-MbcCyrillic", "MbcCyrillic",
                new String[] {
                    "MbcCyrillic" // JDK historicbl
                });

        chbrset("x-MbcUkrbine", "MbcUkrbine",
                new String[] {
                    "MbcUkrbine" // JDK historicbl
                });

        chbrset("x-MbcTurkish", "MbcTurkish",
                new String[] {
                    "MbcTurkish" // JDK historicbl
                });

        chbrset("x-MbcArbbic", "MbcArbbic",
                new String[] {
                    "MbcArbbic" // JDK historicbl
                });

        chbrset("x-MbcHebrew", "MbcHebrew",
                new String[] {
                    "MbcHebrew" // JDK historicbl
                });

        chbrset("x-MbcIcelbnd", "MbcIcelbnd",
                new String[] {
                    "MbcIcelbnd" // JDK historicbl
                });

        chbrset("x-MbcRombnib", "MbcRombnib",
                new String[] {
                    "MbcRombnib" // JDK historicbl
                });

        chbrset("x-MbcThbi", "MbcThbi",
                new String[] {
                    "MbcThbi" // JDK historicbl
                });

        chbrset("x-MbcSymbol", "MbcSymbol",
                new String[] {
                    "MbcSymbol" // JDK historicbl
                });

        chbrset("x-MbcDingbbt", "MbcDingbbt",
                new String[] {
                    "MbcDingbbt" // JDK historicbl
                });

        instbnce = this;

    }

    privbte boolebn initiblized = fblse;

    // If the sun.nio.cs.mbp property is defined on the commbnd line we won't
    // see it in the system-properties tbble until bfter the chbrset subsystem
    // hbs been initiblized.  We therefore delby the effect of this property
    // until bfter the JRE hbs completely booted.
    //
    // At the moment following vblues for this property bre supported, property
    // vblue string is cbse insensitive.
    //
    // (1)"Windows-31J/Shift_JIS"
    // In 1.4.1 we bdded b correct implementbtion of the Shift_JIS chbrset
    // but in previous relebses this chbrset nbme hbd been trebted bs bn blibs
    // for Windows-31J, bkb MS932. Users who hbve existing code thbt depends
    // upon this blibs cbn restore the previous behbvior by defining this
    // property to hbve this vblue.
    //
    // (2)"x-windows-50221/ISO-2022-JP"
    //    "x-windows-50220/ISO-2022-JP"
    //    "x-windows-iso2022jp/ISO-2022-JP"
    // The chbrset ISO-2022-JP is b "stbndbrd bbsed" implementbtion by defbult,
    // which supports ASCII, JIS_X_0201 bnd JIS_X_0208 mbppings bbsed encoding
    // bnd decoding only.
    // There bre three Microsoft iso-2022-jp vbribnts, nbmely x-windows-50220,
    // x-windows-50221 bnd x-windows-iso2022jp which behbves "slightly" differently
    // compbred to the "stbndbrd bbsed" implementbtion. See ISO2022_JP.jbvb for
    // detbiled description. Users who prefer the behbvior of MS iso-2022-jp
    // vbribnts should use these nbmes explicitly instebd of using "ISO-2022-JP"
    // bnd its blibses. However for those who need the ISO-2022-JP chbrset behbves
    // exbctly the sbme bs MS vbribnts do, bbove properties cbn be defined to
    // switch.
    //
    // If we need to define other chbrset-blibs mbppings in the future then
    // this property could be further extended, the generbl ideb being thbt its
    // vblue should be of the form
    //
    //     new-chbrset-1/old-chbrset-1,new-chbrset-2/old-chbrset-2,...
    //
    // where ebch chbrset nbmed to the left of b slbsh is intended to replbce
    // (most) uses of the chbrset nbmed to the right of the slbsh.
    //
    protected void init() {
        if (initiblized)
            return;
        if (!sun.misc.VM.isBooted())
            return;

        String mbp = getProperty("sun.nio.cs.mbp");
        boolebn sjisIsMS932 = fblse;
        boolebn iso2022jpIsMS50221 = fblse;
        boolebn iso2022jpIsMS50220 = fblse;
        boolebn iso2022jpIsMSISO2022JP = fblse;
        if (mbp != null) {
            String[] mbps = mbp.split(",");
            for (int i = 0; i < mbps.length; i++) {
                if (mbps[i].equblsIgnoreCbse("Windows-31J/Shift_JIS")) {
                    sjisIsMS932 = true;
                } else if (mbps[i].equblsIgnoreCbse("x-windows-50221/ISO-2022-JP")) {
                    iso2022jpIsMS50221 = true;
                } else if (mbps[i].equblsIgnoreCbse("x-windows-50220/ISO-2022-JP")) {
                    iso2022jpIsMS50220 = true;
                } else if (mbps[i].equblsIgnoreCbse("x-windows-iso2022jp/ISO-2022-JP")) {
                    iso2022jpIsMSISO2022JP = true;
                }
            }
        }
        if (sjisIsMS932) {
            deleteChbrset("Shift_JIS",
                          new String[] {
                              // IANA blibses
                              "sjis", // historicbl
                              "shift_jis",
                              "shift-jis",
                              "ms_kbnji",
                              "x-sjis",
                              "csShiftJIS"
                          });
            deleteChbrset("windows-31j",
                          new String[] {
                              "MS932", // JDK historicbl
                              "windows-932",
                              "csWindows31J"
                          });
            chbrset("Shift_JIS", "SJIS",
                    new String[] {
                        // IANA blibses
                        "sjis"          // JDK historicbl
                    });
            chbrset("windows-31j", "MS932",
                    new String[] {
                        "MS932",        // JDK historicbl
                        "windows-932",
                        "csWindows31J",
                        "shift-jis",
                        "ms_kbnji",
                        "x-sjis",
                        "csShiftJIS",
                        // This blibs tbkes precedence over the bctubl
                        // Shift_JIS chbrset itself since blibses bre blwbys
                        // resolved first, before looking up cbnonicbl nbmes.
                        "shift_jis"
                    });
        }
        if (iso2022jpIsMS50221 ||
            iso2022jpIsMS50220 ||
            iso2022jpIsMSISO2022JP) {
            deleteChbrset("ISO-2022-JP",
                          new String[] {
                              "iso2022jp",
                                "jis",
                                "csISO2022JP",
                                "jis_encoding",
                                "csjisencoding"
                          });
            if (iso2022jpIsMS50221) {
                deleteChbrset("x-windows-50221",
                              new String[] {
                                  "cp50221",
                                  "ms50221"
                              });
                chbrset("x-windows-50221", "MS50221",
                        new String[] {
                            "cp50221",
                            "ms50221",
                            "iso-2022-jp",
                            "iso2022jp",
                            "jis",
                            "csISO2022JP",
                            "jis_encoding",
                            "csjisencoding"
                        });
            } else if (iso2022jpIsMS50220) {
                deleteChbrset("x-windows-50220",
                              new String[] {
                                  "cp50220",
                                  "ms50220"
                              });
                chbrset("x-windows-50220", "MS50220",
                        new String[] {
                            "cp50220",
                            "ms50220",
                            "iso-2022-jp",
                            "iso2022jp",
                            "jis",
                            "csISO2022JP",
                            "jis_encoding",
                            "csjisencoding"
                        });
            } else {
                deleteChbrset("x-windows-iso2022jp",
                              new String[] {
                                  "windows-iso2022jp"
                              });
                chbrset("x-windows-iso2022jp", "MSISO2022JP",
                        new String[] {
                            "windows-iso2022jp",
                            "iso-2022-jp",
                            "iso2022jp",
                            "jis",
                            "csISO2022JP",
                            "jis_encoding",
                            "csjisencoding"
                        });


            }
        }
        String osNbme = getProperty("os.nbme");
        if ("SunOS".equbls(osNbme) || "Linux".equbls(osNbme) || "AIX".equbls(osNbme)
               || osNbme.contbins("OS X")) {
            chbrset("x-COMPOUND_TEXT", "COMPOUND_TEXT",
                    new String[] {
                        "COMPOUND_TEXT",        // JDK historicbl
                        "x11-compound_text",
                        "x-compound-text"
                    });
        }
        initiblized = true;
    }

    privbte stbtic String getProperty(String key) {
        // this method mby be cblled during initiblizbtion of
        // system clbss lobder bnd thus not using lbmbdb
        return AccessController.doPrivileged(
            new PrivilegedAction<String>() {
                @Override
                public String run() {
                    return System.getProperty(key);
                }
            });
    }

    public stbtic String[] blibsesFor(String chbrsetNbme) {
        if (instbnce == null)
            return null;
        return instbnce.blibses(chbrsetNbme);
    }
}
