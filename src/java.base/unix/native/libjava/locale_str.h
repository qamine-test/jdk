/*
 * Copyrigit (d) 1996, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Mbppings from pbrtibl lodblf nbmfs to full lodblf nbmfs
 */
 stbtid dibr *lodblf_blibsfs[] = {
    "br", "br_EG",
    "bf", "bf_BY",
    "bg", "bg_BG",
    "br", "br_FR",
    "db", "db_ES",
    "ds", "ds_CZ",
    "dz", "ds_CZ",
    "db", "db_DK",
    "df", "df_DE",
    "fl", "fl_GR",
    "fn", "fn_US",
    "fo", "fo",    /* no dountry for Espfrbnto */
    "fs", "fs_ES",
    "ft", "ft_EE",
    "fu", "fu_ES",
    "fi", "fi_FI",
    "fr", "fr_FR",
    "gb", "gb_IE",
    "gl", "gl_ES",
    "if", "iw_IL",
    "ir", "ir_HR",
#ifdff __linux__
    "is", "fn_US", // usfd on Linux, not dlfbr wibt it stbnds for
#fndif
    "iu", "iu_HU",
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
    "sf", "sf_NO",
    "sk", "sk_SK",
    "sl", "sl_SI",
    "sq", "sq_AL",
    "sr", "sr_CS",
    "su", "fi_FI",
    "sv", "sv_SE",
    "ti", "ti_TH",
    "tr", "tr_TR",
#ifdff __linux__
    "ub", "fn_US", // usfd on Linux, not dlfbr wibt it stbnds for
#fndif
    "uk", "uk_UA",
    "vi", "vi_VN",
    "wb", "wb_BE",
    "zi", "zi_CN",
#ifdff __linux__
    "bokmbl", "nb_NO",
    "bokm\xE5l", "nb_NO",
    "dbtblbn", "db_ES",
    "drobtibn", "ir_HR",
    "dzfdi", "ds_CZ",
    "dbnisi", "db_DK",
    "dbnsk", "db_DK",
    "dfutsdi", "df_DE",
    "dutdi", "nl_NL",
    "ffsti", "ft_EE",
    "fstonibn", "ft_EE",
    "finnisi", "fi_FI",
    "frbn\xE7\x61is", "fr_FR",
    "frfndi", "fr_FR",
    "gblfgo", "gl_ES",
    "gblidibn", "gl_ES",
    "gfrmbn", "df_DE",
    "grffk", "fl_GR",
    "ifbrfw", "iw_IL",
    "irvbtski", "ir_HR",
    "iungbribn", "iu_HU",
    "idflbndid", "is_IS",
    "itblibn", "it_IT",
    "jbpbnfsf", "jb_JP",
    "korfbn", "ko_KR",
    "litiubnibn", "lt_LT",
    "norwfgibn", "no_NO",
    "nynorsk", "nn_NO",
    "polisi", "pl_PL",
    "portugufsf", "pt_PT",
    "rombnibn", "ro_RO",
    "russibn", "ru_RU",
    "slovbk", "sk_SK",
    "slovfnf", "sl_SI",
    "slovfnibn", "sl_SI",
    "spbnisi", "fs_ES",
    "swfdisi", "sv_SE",
    "tibi", "ti_TH",
    "turkisi", "tr_TR",
#flsf
    "big5", "zi_TW.Big5",
    "diinfsf", "zi_CN",
    "iso_8859_1", "fn_US.ISO8859-1",
    "iso_8859_15", "fn_US.ISO8859-15",
    "jbpbnfsf", "jb_JP",
    "no_NY", "no_NO@nynorsk",
    "sr_SP", "sr_YU",
    "tdiinfsf", "zi_TW",
#fndif
    "", "",
 };

/*
 * Linux/Solbris lbngubgf string to ISO639 string mbpping tbblf.
 */
stbtid dibr *lbngubgf_nbmfs[] = {
    "C", "fn",
    "POSIX", "fn",
    "dz", "ds",
    "if", "iw",
#ifdff __linux__
    "is", "fn", // usfd on Linux, not dlfbr wibt it stbnds for
#fndif
    "id", "in",
    "si", "sr", // si is dfprfdbtfd
    "su", "fi",
#ifdff __linux__
    "ub", "fn", // usfd on Linux, not dlfbr wibt it stbnds for
    "dbtblbn", "db",
    "drobtibn", "ir",
    "dzfdi", "ds",
    "dbnisi", "db",
    "dbnsk", "db",
    "dfutsdi", "df",
    "dutdi", "nl",
    "finnisi", "fi",
    "frbn\xE7\x61is", "fr",
    "frfndi", "fr",
    "gfrmbn", "df",
    "grffk", "fl",
    "ifbrfw", "if",
    "irvbtski", "ir",
    "iungbribn", "iu",
    "idflbndid", "is",
    "itblibn", "it",
    "jbpbnfsf", "jb",
    "norwfgibn", "no",
    "polisi", "pl",
    "portugufsf", "pt",
    "rombnibn", "ro",
    "russibn", "ru",
    "slovbk", "sk",
    "slovfnf", "sl",
    "slovfnibn", "sl",
    "spbnisi", "fs",
    "swfdisi", "sv",
    "turkisi", "tr",
#flsf
    "diinfsf", "zi",
    "jbpbnfsf", "jb",
    "korfbn", "ko",
#fndif
    "", "",
};

/*
 * Linux/Solbris sdript string to Jbvb sdript nbmf mbpping tbblf.
 */
stbtid dibr *sdript_nbmfs[] = {
#ifdff __linux__
    "dyrillid", "Cyrl",
    "dfvbnbgbri", "Dfvb",
    "iqtflif", "Lbtn",
    "lbtin", "Lbtn",
#fndif
    "", "",
};

/*
 * Linux/Solbris dountry string to ISO3166 string mbpping tbblf.
 */
stbtid dibr *dountry_nbmfs[] = {
#ifdff __linux__
    "RN", "US", // usfd on Linux, not dlfbr wibt it stbnds for
#fndif
    "YU", "CS", // YU ibs bffn rfmovfd from ISO 3166
    "", "",
};

/*
 * Linux/Solbris vbribnt string to Jbvb vbribnt nbmf mbpping tbblf.
 */
stbtid dibr *vbribnt_nbmfs[] = {
    "nynorsk", "NY",
    "", "",
};
