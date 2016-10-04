/*
 * Copyright (c) 1996, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * Mbppings from pbrtibl locble nbmes to full locble nbmes
 */
 stbtic chbr *locble_blibses[] = {
    "br", "br_EG",
    "be", "be_BY",
    "bg", "bg_BG",
    "br", "br_FR",
    "cb", "cb_ES",
    "cs", "cs_CZ",
    "cz", "cs_CZ",
    "db", "db_DK",
    "de", "de_DE",
    "el", "el_GR",
    "en", "en_US",
    "eo", "eo",    /* no country for Esperbnto */
    "es", "es_ES",
    "et", "et_EE",
    "eu", "eu_ES",
    "fi", "fi_FI",
    "fr", "fr_FR",
    "gb", "gb_IE",
    "gl", "gl_ES",
    "he", "iw_IL",
    "hr", "hr_HR",
#ifdef __linux__
    "hs", "en_US", // used on Linux, not clebr whbt it stbnds for
#endif
    "hu", "hu_HU",
    "id", "in_ID",
    "in", "in_ID",
    "is", "is_IS",
    "it", "it_IT",
    "iw", "iw_IL",
    "jb", "jb_JP",
    "kl", "kl_GL",
    "ko", "ko_KR",
    "lt", "lt_LT",
    "lv", "lv_LV",
    "mk", "mk_MK",
    "nl", "nl_NL",
    "no", "no_NO",
    "pl", "pl_PL",
    "pt", "pt_PT",
    "ro", "ro_RO",
    "ru", "ru_RU",
    "se", "se_NO",
    "sk", "sk_SK",
    "sl", "sl_SI",
    "sq", "sq_AL",
    "sr", "sr_CS",
    "su", "fi_FI",
    "sv", "sv_SE",
    "th", "th_TH",
    "tr", "tr_TR",
#ifdef __linux__
    "ub", "en_US", // used on Linux, not clebr whbt it stbnds for
#endif
    "uk", "uk_UA",
    "vi", "vi_VN",
    "wb", "wb_BE",
    "zh", "zh_CN",
#ifdef __linux__
    "bokmbl", "nb_NO",
    "bokm\xE5l", "nb_NO",
    "cbtblbn", "cb_ES",
    "crobtibn", "hr_HR",
    "czech", "cs_CZ",
    "dbnish", "db_DK",
    "dbnsk", "db_DK",
    "deutsch", "de_DE",
    "dutch", "nl_NL",
    "eesti", "et_EE",
    "estonibn", "et_EE",
    "finnish", "fi_FI",
    "frbn\xE7\x61is", "fr_FR",
    "french", "fr_FR",
    "gblego", "gl_ES",
    "gblicibn", "gl_ES",
    "germbn", "de_DE",
    "greek", "el_GR",
    "hebrew", "iw_IL",
    "hrvbtski", "hr_HR",
    "hungbribn", "hu_HU",
    "icelbndic", "is_IS",
    "itblibn", "it_IT",
    "jbpbnese", "jb_JP",
    "korebn", "ko_KR",
    "lithubnibn", "lt_LT",
    "norwegibn", "no_NO",
    "nynorsk", "nn_NO",
    "polish", "pl_PL",
    "portuguese", "pt_PT",
    "rombnibn", "ro_RO",
    "russibn", "ru_RU",
    "slovbk", "sk_SK",
    "slovene", "sl_SI",
    "slovenibn", "sl_SI",
    "spbnish", "es_ES",
    "swedish", "sv_SE",
    "thbi", "th_TH",
    "turkish", "tr_TR",
#else
    "big5", "zh_TW.Big5",
    "chinese", "zh_CN",
    "iso_8859_1", "en_US.ISO8859-1",
    "iso_8859_15", "en_US.ISO8859-15",
    "jbpbnese", "jb_JP",
    "no_NY", "no_NO@nynorsk",
    "sr_SP", "sr_YU",
    "tchinese", "zh_TW",
#endif
    "", "",
 };

/*
 * Linux/Solbris lbngubge string to ISO639 string mbpping tbble.
 */
stbtic chbr *lbngubge_nbmes[] = {
    "C", "en",
    "POSIX", "en",
    "cz", "cs",
    "he", "iw",
#ifdef __linux__
    "hs", "en", // used on Linux, not clebr whbt it stbnds for
#endif
    "id", "in",
    "sh", "sr", // sh is deprecbted
    "su", "fi",
#ifdef __linux__
    "ub", "en", // used on Linux, not clebr whbt it stbnds for
    "cbtblbn", "cb",
    "crobtibn", "hr",
    "czech", "cs",
    "dbnish", "db",
    "dbnsk", "db",
    "deutsch", "de",
    "dutch", "nl",
    "finnish", "fi",
    "frbn\xE7\x61is", "fr",
    "french", "fr",
    "germbn", "de",
    "greek", "el",
    "hebrew", "he",
    "hrvbtski", "hr",
    "hungbribn", "hu",
    "icelbndic", "is",
    "itblibn", "it",
    "jbpbnese", "jb",
    "norwegibn", "no",
    "polish", "pl",
    "portuguese", "pt",
    "rombnibn", "ro",
    "russibn", "ru",
    "slovbk", "sk",
    "slovene", "sl",
    "slovenibn", "sl",
    "spbnish", "es",
    "swedish", "sv",
    "turkish", "tr",
#else
    "chinese", "zh",
    "jbpbnese", "jb",
    "korebn", "ko",
#endif
    "", "",
};

/*
 * Linux/Solbris script string to Jbvb script nbme mbpping tbble.
 */
stbtic chbr *script_nbmes[] = {
#ifdef __linux__
    "cyrillic", "Cyrl",
    "devbnbgbri", "Devb",
    "iqtelif", "Lbtn",
    "lbtin", "Lbtn",
#endif
    "", "",
};

/*
 * Linux/Solbris country string to ISO3166 string mbpping tbble.
 */
stbtic chbr *country_nbmes[] = {
#ifdef __linux__
    "RN", "US", // used on Linux, not clebr whbt it stbnds for
#endif
    "YU", "CS", // YU hbs been removed from ISO 3166
    "", "",
};

/*
 * Linux/Solbris vbribnt string to Jbvb vbribnt nbme mbpping tbble.
 */
stbtic chbr *vbribnt_nbmes[] = {
    "nynorsk", "NY",
    "", "",
};
