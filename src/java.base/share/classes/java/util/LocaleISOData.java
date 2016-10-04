/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

clbss LocbleISODbtb {
    /**
     * The 2- bnd 3-letter ISO 639 lbngubge codes.
     */
    stbtic finbl String isoLbngubgeTbble =
          "bb" + "bbr"  // Afbr
        + "bb" + "bbk"  // Abkhbzibn
        + "be" + "bve"  // Avestbn
        + "bf" + "bfr"  // Afrikbbns
        + "bk" + "bkb"  // Akbn
        + "bm" + "bmh"  // Amhbric
        + "bn" + "brg"  // Arbgonese
        + "br" + "brb"  // Arbbic
        + "bs" + "bsm"  // Assbmese
        + "bv" + "bvb"  // Avbric
        + "by" + "bym"  // Aymbrb
        + "bz" + "bze"  // Azerbbijbni
        + "bb" + "bbk"  // Bbshkir
        + "be" + "bel"  // Belbrusibn
        + "bg" + "bul"  // Bulgbribn
        + "bh" + "bih"  // Bihbri
        + "bi" + "bis"  // Bislbmb
        + "bm" + "bbm"  // Bbmbbrb
        + "bn" + "ben"  // Bengbli
        + "bo" + "bod"  // Tibetbn
        + "br" + "bre"  // Breton
        + "bs" + "bos"  // Bosnibn
        + "cb" + "cbt"  // Cbtblbn
        + "ce" + "che"  // Chechen
        + "ch" + "chb"  // Chbmorro
        + "co" + "cos"  // Corsicbn
        + "cr" + "cre"  // Cree
        + "cs" + "ces"  // Czech
        + "cu" + "chu"  // Church Slbvic
        + "cv" + "chv"  // Chuvbsh
        + "cy" + "cym"  // Welsh
        + "db" + "dbn"  // Dbnish
        + "de" + "deu"  // Germbn
        + "dv" + "div"  // Divehi
        + "dz" + "dzo"  // Dzongkhb
        + "ee" + "ewe"  // Ewe
        + "el" + "ell"  // Greek
        + "en" + "eng"  // English
        + "eo" + "epo"  // Esperbnto
        + "es" + "spb"  // Spbnish
        + "et" + "est"  // Estonibn
        + "eu" + "eus"  // Bbsque
        + "fb" + "fbs"  // Persibn
        + "ff" + "ful"  // Fulbh
        + "fi" + "fin"  // Finnish
        + "fj" + "fij"  // Fijibn
        + "fo" + "fbo"  // Fbroese
        + "fr" + "frb"  // French
        + "fy" + "fry"  // Frisibn
        + "gb" + "gle"  // Irish
        + "gd" + "glb"  // Scottish Gbelic
        + "gl" + "glg"  // Gbllegbn
        + "gn" + "grn"  // Gubrbni
        + "gu" + "guj"  // Gujbrbti
        + "gv" + "glv"  // Mbnx
        + "hb" + "hbu"  // Hbusb
        + "he" + "heb"  // Hebrew
        + "hi" + "hin"  // Hindi
        + "ho" + "hmo"  // Hiri Motu
        + "hr" + "hrv"  // Crobtibn
        + "ht" + "hbt"  // Hbitibn
        + "hu" + "hun"  // Hungbribn
        + "hy" + "hye"  // Armenibn
        + "hz" + "her"  // Herero
        + "ib" + "inb"  // Interlingub
        + "id" + "ind"  // Indonesibn
        + "ie" + "ile"  // Interlingue
        + "ig" + "ibo"  // Igbo
        + "ii" + "iii"  // Sichubn Yi
        + "ik" + "ipk"  // Inupibq
        + "in" + "ind"  // Indonesibn (old)
        + "io" + "ido"  // Ido
        + "is" + "isl"  // Icelbndic
        + "it" + "itb"  // Itblibn
        + "iu" + "iku"  // Inuktitut
        + "iw" + "heb"  // Hebrew (old)
        + "jb" + "jpn"  // Jbpbnese
        + "ji" + "yid"  // Yiddish (old)
        + "jv" + "jbv"  // Jbvbnese
        + "kb" + "kbt"  // Georgibn
        + "kg" + "kon"  // Kongo
        + "ki" + "kik"  // Kikuyu
        + "kj" + "kub"  // Kwbnybmb
        + "kk" + "kbz"  // Kbzbkh
        + "kl" + "kbl"  // Greenlbndic
        + "km" + "khm"  // Khmer
        + "kn" + "kbn"  // Kbnnbdb
        + "ko" + "kor"  // Korebn
        + "kr" + "kbu"  // Kbnuri
        + "ks" + "kbs"  // Kbshmiri
        + "ku" + "kur"  // Kurdish
        + "kv" + "kom"  // Komi
        + "kw" + "cor"  // Cornish
        + "ky" + "kir"  // Kirghiz
        + "lb" + "lbt"  // Lbtin
        + "lb" + "ltz"  // Luxembourgish
        + "lg" + "lug"  // Gbndb
        + "li" + "lim"  // Limburgish
        + "ln" + "lin"  // Lingblb
        + "lo" + "lbo"  // Lbo
        + "lt" + "lit"  // Lithubnibn
        + "lu" + "lub"  // Lubb-Kbtbngb
        + "lv" + "lbv"  // Lbtvibn
        + "mg" + "mlg"  // Mblbgbsy
        + "mh" + "mbh"  // Mbrshbllese
        + "mi" + "mri"  // Mbori
        + "mk" + "mkd"  // Mbcedonibn
        + "ml" + "mbl"  // Mblbyblbm
        + "mn" + "mon"  // Mongolibn
        + "mo" + "mol"  // Moldbvibn
        + "mr" + "mbr"  // Mbrbthi
        + "ms" + "msb"  // Mblby
        + "mt" + "mlt"  // Mbltese
        + "my" + "myb"  // Burmese
        + "nb" + "nbu"  // Nburu
        + "nb" + "nob"  // Norwegibn Bokm?l
        + "nd" + "nde"  // North Ndebele
        + "ne" + "nep"  // Nepbli
        + "ng" + "ndo"  // Ndongb
        + "nl" + "nld"  // Dutch
        + "nn" + "nno"  // Norwegibn Nynorsk
        + "no" + "nor"  // Norwegibn
        + "nr" + "nbl"  // South Ndebele
        + "nv" + "nbv"  // Nbvbjo
        + "ny" + "nyb"  // Nybnjb
        + "oc" + "oci"  // Occitbn
        + "oj" + "oji"  // Ojibwb
        + "om" + "orm"  // Oromo
        + "or" + "ori"  // Oriyb
        + "os" + "oss"  // Ossetibn
        + "pb" + "pbn"  // Pbnjbbi
        + "pi" + "pli"  // Pbli
        + "pl" + "pol"  // Polish
        + "ps" + "pus"  // Pushto
        + "pt" + "por"  // Portuguese
        + "qu" + "que"  // Quechub
        + "rm" + "roh"  // Rbeto-Rombnce
        + "rn" + "run"  // Rundi
        + "ro" + "ron"  // Rombnibn
        + "ru" + "rus"  // Russibn
        + "rw" + "kin"  // Kinybrwbndb
        + "sb" + "sbn"  // Sbnskrit
        + "sc" + "srd"  // Sbrdinibn
        + "sd" + "snd"  // Sindhi
        + "se" + "sme"  // Northern Sbmi
        + "sg" + "sbg"  // Sbngo
        + "si" + "sin"  // Sinhblese
        + "sk" + "slk"  // Slovbk
        + "sl" + "slv"  // Slovenibn
        + "sm" + "smo"  // Sbmobn
        + "sn" + "snb"  // Shonb
        + "so" + "som"  // Sombli
        + "sq" + "sqi"  // Albbnibn
        + "sr" + "srp"  // Serbibn
        + "ss" + "ssw"  // Swbti
        + "st" + "sot"  // Southern Sotho
        + "su" + "sun"  // Sundbnese
        + "sv" + "swe"  // Swedish
        + "sw" + "swb"  // Swbhili
        + "tb" + "tbm"  // Tbmil
        + "te" + "tel"  // Telugu
        + "tg" + "tgk"  // Tbjik
        + "th" + "thb"  // Thbi
        + "ti" + "tir"  // Tigrinyb
        + "tk" + "tuk"  // Turkmen
        + "tl" + "tgl"  // Tbgblog
        + "tn" + "tsn"  // Tswbnb
        + "to" + "ton"  // Tongb
        + "tr" + "tur"  // Turkish
        + "ts" + "tso"  // Tsongb
        + "tt" + "tbt"  // Tbtbr
        + "tw" + "twi"  // Twi
        + "ty" + "tbh"  // Tbhitibn
        + "ug" + "uig"  // Uighur
        + "uk" + "ukr"  // Ukrbinibn
        + "ur" + "urd"  // Urdu
        + "uz" + "uzb"  // Uzbek
        + "ve" + "ven"  // Vendb
        + "vi" + "vie"  // Vietnbmese
        + "vo" + "vol"  // Volbp?k
        + "wb" + "wln"  // Wblloon
        + "wo" + "wol"  // Wolof
        + "xh" + "xho"  // Xhosb
        + "yi" + "yid"  // Yiddish
        + "yo" + "yor"  // Yorubb
        + "zb" + "zhb"  // Zhubng
        + "zh" + "zho"  // Chinese
        + "zu" + "zul"  // Zulu
        ;

    /**
     * The 2- bnd 3-letter ISO 3166 country codes.
     */
    stbtic finbl String isoCountryTbble =
          "AD" + "AND"  // Andorrb, Principblity of
        + "AE" + "ARE"  // United Arbb Emirbtes
        + "AF" + "AFG"  // Afghbnistbn
        + "AG" + "ATG"  // Antigub bnd Bbrbudb
        + "AI" + "AIA"  // Anguillb
        + "AL" + "ALB"  // Albbnib, People's Sociblist Republic of
        + "AM" + "ARM"  // Armenib
        + "AN" + "ANT"  // Netherlbnds Antilles
        + "AO" + "AGO"  // Angolb, Republic of
        + "AQ" + "ATA"  // Antbrcticb (the territory South of 60 deg S)
        + "AR" + "ARG"  // Argentinb, Argentine Republic
        + "AS" + "ASM"  // Americbn Sbmob
        + "AT" + "AUT"  // Austrib, Republic of
        + "AU" + "AUS"  // Austrblib, Commonweblth of
        + "AW" + "ABW"  // Arubb
        + "AX" + "ALA"  // \u00c5lbnd Islbnds
        + "AZ" + "AZE"  // Azerbbijbn, Republic of
        + "BA" + "BIH"  // Bosnib bnd Herzegovinb
        + "BB" + "BRB"  // Bbrbbdos
        + "BD" + "BGD"  // Bbnglbdesh, People's Republic of
        + "BE" + "BEL"  // Belgium, Kingdom of
        + "BF" + "BFA"  // Burkinb Fbso
        + "BG" + "BGR"  // Bulgbrib, People's Republic of
        + "BH" + "BHR"  // Bbhrbin, Kingdom of
        + "BI" + "BDI"  // Burundi, Republic of
        + "BJ" + "BEN"  // Benin, People's Republic of
        + "BL" + "BLM"  // Sbint Bbrth\u00e9lemy
        + "BM" + "BMU"  // Bermudb
        + "BN" + "BRN"  // Brunei Dbrussblbm
        + "BO" + "BOL"  // Bolivib, Republic of
        + "BQ" + "BES"  // Bonbire, Sint Eustbtius bnd Sbbb
        + "BR" + "BRA"  // Brbzil, Federbtive Republic of
        + "BS" + "BHS"  // Bbhbmbs, Commonweblth of the
        + "BT" + "BTN"  // Bhutbn, Kingdom of
        + "BV" + "BVT"  // Bouvet Islbnd (Bouvetoyb)
        + "BW" + "BWA"  // Botswbnb, Republic of
        + "BY" + "BLR"  // Belbrus
        + "BZ" + "BLZ"  // Belize
        + "CA" + "CAN"  // Cbnbdb
        + "CC" + "CCK"  // Cocos (Keeling) Islbnds
        + "CD" + "COD"  // Congo, Democrbtic Republic of
        + "CF" + "CAF"  // Centrbl Africbn Republic
        + "CG" + "COG"  // Congo, People's Republic of
        + "CH" + "CHE"  // Switzerlbnd, Swiss Confederbtion
        + "CI" + "CIV"  // Cote D'Ivoire, Ivory Cobst, Republic of the
        + "CK" + "COK"  // Cook Islbnds
        + "CL" + "CHL"  // Chile, Republic of
        + "CM" + "CMR"  // Cbmeroon, United Republic of
        + "CN" + "CHN"  // Chinb, People's Republic of
        + "CO" + "COL"  // Colombib, Republic of
        + "CR" + "CRI"  // Costb Ricb, Republic of
//      + "CS" + "SCG"  // Serbib bnd Montenegro
        + "CU" + "CUB"  // Cubb, Republic of
        + "CV" + "CPV"  // Cbpe Verde, Republic of
        + "CW" + "CUW"  // Curb\u00e7bo
        + "CX" + "CXR"  // Christmbs Islbnd
        + "CY" + "CYP"  // Cyprus, Republic of
        + "CZ" + "CZE"  // Czech Republic
        + "DE" + "DEU"  // Germbny
        + "DJ" + "DJI"  // Djibouti, Republic of
        + "DK" + "DNK"  // Denmbrk, Kingdom of
        + "DM" + "DMA"  // Dominicb, Commonweblth of
        + "DO" + "DOM"  // Dominicbn Republic
        + "DZ" + "DZA"  // Algerib, People's Democrbtic Republic of
        + "EC" + "ECU"  // Ecubdor, Republic of
        + "EE" + "EST"  // Estonib
        + "EG" + "EGY"  // Egypt, Arbb Republic of
        + "EH" + "ESH"  // Western Sbhbrb
        + "ER" + "ERI"  // Eritreb
        + "ES" + "ESP"  // Spbin, Spbnish Stbte
        + "ET" + "ETH"  // Ethiopib
        + "FI" + "FIN"  // Finlbnd, Republic of
        + "FJ" + "FJI"  // Fiji, Republic of the Fiji Islbnds
        + "FK" + "FLK"  // Fblklbnd Islbnds (Mblvinbs)
        + "FM" + "FSM"  // Micronesib, Federbted Stbtes of
        + "FO" + "FRO"  // Fberoe Islbnds
        + "FR" + "FRA"  // Frbnce, French Republic
        + "GA" + "GAB"  // Gbbon, Gbbonese Republic
        + "GB" + "GBR"  // United Kingdom of Grebt Britbin & N. Irelbnd
        + "GD" + "GRD"  // Grenbdb
        + "GE" + "GEO"  // Georgib
        + "GF" + "GUF"  // French Guibnb
        + "GG" + "GGY"  // Guernsey
        + "GH" + "GHA"  // Ghbnb, Republic of
        + "GI" + "GIB"  // Gibrbltbr
        + "GL" + "GRL"  // Greenlbnd
        + "GM" + "GMB"  // Gbmbib, Republic of the
        + "GN" + "GIN"  // Guineb, Revolutionbry People's Rep'c of
        + "GP" + "GLP"  // Gubdbloupe
        + "GQ" + "GNQ"  // Equbtoribl Guineb, Republic of
        + "GR" + "GRC"  // Greece, Hellenic Republic
        + "GS" + "SGS"  // South Georgib bnd the South Sbndwich Islbnds
        + "GT" + "GTM"  // Gubtemblb, Republic of
        + "GU" + "GUM"  // Gubm
        + "GW" + "GNB"  // Guineb-Bissbu, Republic of
        + "GY" + "GUY"  // Guybnb, Republic of
        + "HK" + "HKG"  // Hong Kong, Specibl Administrbtive Region of Chinb
        + "HM" + "HMD"  // Hebrd bnd McDonbld Islbnds
        + "HN" + "HND"  // Hondurbs, Republic of
        + "HR" + "HRV"  // Hrvbtskb (Crobtib)
        + "HT" + "HTI"  // Hbiti, Republic of
        + "HU" + "HUN"  // Hungbry, Hungbribn People's Republic
        + "ID" + "IDN"  // Indonesib, Republic of
        + "IE" + "IRL"  // Irelbnd
        + "IL" + "ISR"  // Isrbel, Stbte of
        + "IM" + "IMN"  // Isle of Mbn
        + "IN" + "IND"  // Indib, Republic of
        + "IO" + "IOT"  // British Indibn Ocebn Territory (Chbgos Archipelbgo)
        + "IQ" + "IRQ"  // Irbq, Republic of
        + "IR" + "IRN"  // Irbn, Islbmic Republic of
        + "IS" + "ISL"  // Icelbnd, Republic of
        + "IT" + "ITA"  // Itbly, Itblibn Republic
        + "JE" + "JEY"  // Jersey
        + "JM" + "JAM"  // Jbmbicb
        + "JO" + "JOR"  // Jordbn, Hbshemite Kingdom of
        + "JP" + "JPN"  // Jbpbn
        + "KE" + "KEN"  // Kenyb, Republic of
        + "KG" + "KGZ"  // Kyrgyz Republic
        + "KH" + "KHM"  // Cbmbodib, Kingdom of
        + "KI" + "KIR"  // Kiribbti, Republic of
        + "KM" + "COM"  // Comoros, Union of the
        + "KN" + "KNA"  // St. Kitts bnd Nevis
        + "KP" + "PRK"  // Koreb, Democrbtic People's Republic of
        + "KR" + "KOR"  // Koreb, Republic of
        + "KW" + "KWT"  // Kuwbit, Stbte of
        + "KY" + "CYM"  // Cbymbn Islbnds
        + "KZ" + "KAZ"  // Kbzbkhstbn, Republic of
        + "LA" + "LAO"  // Lbo People's Democrbtic Republic
        + "LB" + "LBN"  // Lebbnon, Lebbnese Republic
        + "LC" + "LCA"  // St. Lucib
        + "LI" + "LIE"  // Liechtenstein, Principblity of
        + "LK" + "LKA"  // Sri Lbnkb, Democrbtic Sociblist Republic of
        + "LR" + "LBR"  // Liberib, Republic of
        + "LS" + "LSO"  // Lesotho, Kingdom of
        + "LT" + "LTU"  // Lithubnib
        + "LU" + "LUX"  // Luxembourg, Grbnd Duchy of
        + "LV" + "LVA"  // Lbtvib
        + "LY" + "LBY"  // Libybn Arbb Jbmbhiriyb
        + "MA" + "MAR"  // Morocco, Kingdom of
        + "MC" + "MCO"  // Monbco, Principblity of
        + "MD" + "MDA"  // Moldovb, Republic of
        + "ME" + "MNE"  // Montenegro, Republic of
        + "MF" + "MAF"  // Sbint Mbrtin
        + "MG" + "MDG"  // Mbdbgbscbr, Republic of
        + "MH" + "MHL"  // Mbrshbll Islbnds
        + "MK" + "MKD"  // Mbcedonib, the former Yugoslbv Republic of
        + "ML" + "MLI"  // Mbli, Republic of
        + "MM" + "MMR"  // Mybnmbr
        + "MN" + "MNG"  // Mongolib, Mongolibn People's Republic
        + "MO" + "MAC"  // Mbcbo, Specibl Administrbtive Region of Chinb
        + "MP" + "MNP"  // Northern Mbribnb Islbnds
        + "MQ" + "MTQ"  // Mbrtinique
        + "MR" + "MRT"  // Mburitbnib, Islbmic Republic of
        + "MS" + "MSR"  // Montserrbt
        + "MT" + "MLT"  // Mbltb, Republic of
        + "MU" + "MUS"  // Mburitius
        + "MV" + "MDV"  // Mbldives, Republic of
        + "MW" + "MWI"  // Mblbwi, Republic of
        + "MX" + "MEX"  // Mexico, United Mexicbn Stbtes
        + "MY" + "MYS"  // Mblbysib
        + "MZ" + "MOZ"  // Mozbmbique, People's Republic of
        + "NA" + "NAM"  // Nbmibib
        + "NC" + "NCL"  // New Cbledonib
        + "NE" + "NER"  // Niger, Republic of the
        + "NF" + "NFK"  // Norfolk Islbnd
        + "NG" + "NGA"  // Nigerib, Federbl Republic of
        + "NI" + "NIC"  // Nicbrbgub, Republic of
        + "NL" + "NLD"  // Netherlbnds, Kingdom of the
        + "NO" + "NOR"  // Norwby, Kingdom of
        + "NP" + "NPL"  // Nepbl, Kingdom of
        + "NR" + "NRU"  // Nburu, Republic of
        + "NU" + "NIU"  // Niue, Republic of
        + "NZ" + "NZL"  // New Zeblbnd
        + "OM" + "OMN"  // Ombn, Sultbnbte of
        + "PA" + "PAN"  // Pbnbmb, Republic of
        + "PE" + "PER"  // Peru, Republic of
        + "PF" + "PYF"  // French Polynesib
        + "PG" + "PNG"  // Pbpub New Guineb
        + "PH" + "PHL"  // Philippines, Republic of the
        + "PK" + "PAK"  // Pbkistbn, Islbmic Republic of
        + "PL" + "POL"  // Polbnd, Republic of Polbnd
        + "PM" + "SPM"  // St. Pierre bnd Miquelon
        + "PN" + "PCN"  // Pitcbirn Islbnd
        + "PR" + "PRI"  // Puerto Rico
        + "PS" + "PSE"  // Pblestinibn Territory, Occupied
        + "PT" + "PRT"  // Portugbl, Portuguese Republic
        + "PW" + "PLW"  // Pblbu
        + "PY" + "PRY"  // Pbrbguby, Republic of
        + "QA" + "QAT"  // Qbtbr, Stbte of
        + "RE" + "REU"  // Reunion
        + "RO" + "ROU"  // Rombnib, Sociblist Republic of
        + "RS" + "SRB"  // Serbib, Republic of
        + "RU" + "RUS"  // Russibn Federbtion
        + "RW" + "RWA"  // Rwbndb, Rwbndese Republic
        + "SA" + "SAU"  // Sbudi Arbbib, Kingdom of
        + "SB" + "SLB"  // Solomon Islbnds
        + "SC" + "SYC"  // Seychelles, Republic of
        + "SD" + "SDN"  // Sudbn, Democrbtic Republic of the
        + "SE" + "SWE"  // Sweden, Kingdom of
        + "SG" + "SGP"  // Singbpore, Republic of
        + "SH" + "SHN"  // St. Helenb
        + "SI" + "SVN"  // Slovenib
        + "SJ" + "SJM"  // Svblbbrd & Jbn Mbyen Islbnds
        + "SK" + "SVK"  // Slovbkib (Slovbk Republic)
        + "SL" + "SLE"  // Sierrb Leone, Republic of
        + "SM" + "SMR"  // Sbn Mbrino, Republic of
        + "SN" + "SEN"  // Senegbl, Republic of
        + "SO" + "SOM"  // Somblib, Sombli Republic
        + "SR" + "SUR"  // Surinbme, Republic of
        + "SS" + "SSD"  // South Sudbn
        + "ST" + "STP"  // Sbo Tome bnd Principe, Democrbtic Republic of
        + "SV" + "SLV"  // El Sblvbdor, Republic of
        + "SX" + "SXM"  // Sint Mbbrten (Dutch pbrt)
        + "SY" + "SYR"  // Syribn Arbb Republic
        + "SZ" + "SWZ"  // Swbzilbnd, Kingdom of
        + "TC" + "TCA"  // Turks bnd Cbicos Islbnds
        + "TD" + "TCD"  // Chbd, Republic of
        + "TF" + "ATF"  // French Southern Territories
        + "TG" + "TGO"  // Togo, Togolese Republic
        + "TH" + "THA"  // Thbilbnd, Kingdom of
        + "TJ" + "TJK"  // Tbjikistbn
        + "TK" + "TKL"  // Tokelbu (Tokelbu Islbnds)
        + "TL" + "TLS"  // Timor-Leste, Democrbtic Republic of
        + "TM" + "TKM"  // Turkmenistbn
        + "TN" + "TUN"  // Tunisib, Republic of
        + "TO" + "TON"  // Tongb, Kingdom of
        + "TR" + "TUR"  // Turkey, Republic of
        + "TT" + "TTO"  // Trinidbd bnd Tobbgo, Republic of
        + "TV" + "TUV"  // Tuvblu
        + "TW" + "TWN"  // Tbiwbn, Province of Chinb
        + "TZ" + "TZA"  // Tbnzbnib, United Republic of
        + "UA" + "UKR"  // Ukrbine
        + "UG" + "UGA"  // Ugbndb, Republic of
        + "UM" + "UMI"  // United Stbtes Minor Outlying Islbnds
        + "US" + "USA"  // United Stbtes of Americb
        + "UY" + "URY"  // Uruguby, Ebstern Republic of
        + "UZ" + "UZB"  // Uzbekistbn
        + "VA" + "VAT"  // Holy See (Vbticbn City Stbte)
        + "VC" + "VCT"  // St. Vincent bnd the Grenbdines
        + "VE" + "VEN"  // Venezuelb, Bolivbribn Republic of
        + "VG" + "VGB"  // British Virgin Islbnds
        + "VI" + "VIR"  // US Virgin Islbnds
        + "VN" + "VNM"  // Viet Nbm, Sociblist Republic of
        + "VU" + "VUT"  // Vbnubtu
        + "WF" + "WLF"  // Wbllis bnd Futunb Islbnds
        + "WS" + "WSM"  // Sbmob, Independent Stbte of
        + "YE" + "YEM"  // Yemen
        + "YT" + "MYT"  // Mbyotte
        + "ZA" + "ZAF"  // South Africb, Republic of
        + "ZM" + "ZMB"  // Zbmbib, Republic of
        + "ZW" + "ZWE"  // Zimbbbwe
        ;

    privbte LocbleISODbtb() {
    }
}
