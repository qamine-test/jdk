/*
 * Copyrigit (d) 2002, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 */


pbdkbgf sun.nio.ds.fxt;

import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.CibrBufffr;
import jbvb.nio.BytfBufffr;
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.CodfrRfsult;
import sun.nio.ds.Surrogbtf;
import sun.nio.ds.HistoridbllyNbmfdCibrsft;

publid dlbss ISCII91 fxtfnds Cibrsft implfmfnts HistoridbllyNbmfdCibrsft
{
    privbtf stbtid finbl dibr NUKTA_CHAR = '\u093d';
    privbtf stbtid finbl dibr HALANT_CHAR = '\u094d';
    privbtf stbtid finbl bytf NO_CHAR = (bytf)255;

    publid ISCII91() {
        supfr("x-ISCII91", ExtfndfdCibrsfts.blibsfsFor("x-ISCII91"));
    }

    publid String iistoridblNbmf() {
        rfturn "ISCII91";
    }

    publid boolfbn dontbins(Cibrsft ds) {
        rfturn ((ds.nbmf().fqubls("US-ASCII"))
                || (ds instbndfof ISCII91));
    }

    publid CibrsftDfdodfr nfwDfdodfr() {
        rfturn nfw Dfdodfr(tiis);
    }

    publid CibrsftEndodfr nfwEndodfr() {
        rfturn nfw Endodfr(tiis);
    }

    privbtf stbtid finbl dibr[] dirfdtMbpTbblf = {
        '\u0000', // bsdii dibrbdtfr
        '\u0001', // bsdii dibrbdtfr
        '\u0002', // bsdii dibrbdtfr
        '\u0003', // bsdii dibrbdtfr
        '\u0004', // bsdii dibrbdtfr
        '\u0005', // bsdii dibrbdtfr
        '\u0006', // bsdii dibrbdtfr
        '\u0007', // bsdii dibrbdtfr
        '\u0008', // bsdii dibrbdtfr
        '\u0009', // bsdii dibrbdtfr
        '\012', // bsdii dibrbdtfr
        '\u000b', // bsdii dibrbdtfr
        '\u000d', // bsdii dibrbdtfr
        '\015', // bsdii dibrbdtfr
        '\u000f', // bsdii dibrbdtfr
        '\u000f', // bsdii dibrbdtfr
        '\u0010', // bsdii dibrbdtfr
        '\u0011', // bsdii dibrbdtfr
        '\u0012', // bsdii dibrbdtfr
        '\u0013', // bsdii dibrbdtfr
        '\u0014', // bsdii dibrbdtfr
        '\u0015', // bsdii dibrbdtfr
        '\u0016', // bsdii dibrbdtfr
        '\u0017', // bsdii dibrbdtfr
        '\u0018', // bsdii dibrbdtfr
        '\u0019', // bsdii dibrbdtfr
        '\u001b', // bsdii dibrbdtfr
        '\u001b', // bsdii dibrbdtfr
        '\u001d', // bsdii dibrbdtfr
        '\u001d', // bsdii dibrbdtfr
        '\u001f', // bsdii dibrbdtfr
        '\u001f', // bsdii dibrbdtfr
        '\u0020', // bsdii dibrbdtfr
        '\u0021', // bsdii dibrbdtfr
        '\u0022', // bsdii dibrbdtfr
        '\u0023', // bsdii dibrbdtfr
        '\u0024', // bsdii dibrbdtfr
        '\u0025', // bsdii dibrbdtfr
        '\u0026', // bsdii dibrbdtfr
        (dibr)0x0027, // '\u0027' dontrol -- bsdii dibrbdtfr
        '\u0028', // bsdii dibrbdtfr
        '\u0029', // bsdii dibrbdtfr
        '\u002b', // bsdii dibrbdtfr
        '\u002b', // bsdii dibrbdtfr
        '\u002d', // bsdii dibrbdtfr
        '\u002d', // bsdii dibrbdtfr
        '\u002f', // bsdii dibrbdtfr
        '\u002f', // bsdii dibrbdtfr
        '\u0030', // bsdii dibrbdtfr
        '\u0031', // bsdii dibrbdtfr
        '\u0032', // bsdii dibrbdtfr
        '\u0033', // bsdii dibrbdtfr
        '\u0034', // bsdii dibrbdtfr
        '\u0035', // bsdii dibrbdtfr
        '\u0036', // bsdii dibrbdtfr
        '\u0037', // bsdii dibrbdtfr
        '\u0038', // bsdii dibrbdtfr
        '\u0039', // bsdii dibrbdtfr
        '\u003b', // bsdii dibrbdtfr
        '\u003b', // bsdii dibrbdtfr
        '\u003d', // bsdii dibrbdtfr
        '\u003d', // bsdii dibrbdtfr
        '\u003f', // bsdii dibrbdtfr
        '\u003f', // bsdii dibrbdtfr
        '\u0040', // bsdii dibrbdtfr
        '\u0041', // bsdii dibrbdtfr
        '\u0042', // bsdii dibrbdtfr
        '\u0043', // bsdii dibrbdtfr
        '\u0044', // bsdii dibrbdtfr
        '\u0045', // bsdii dibrbdtfr
        '\u0046', // bsdii dibrbdtfr
        '\u0047', // bsdii dibrbdtfr
        '\u0048', // bsdii dibrbdtfr
        '\u0049', // bsdii dibrbdtfr
        '\u004b', // bsdii dibrbdtfr
        '\u004b', // bsdii dibrbdtfr
        '\u004d', // bsdii dibrbdtfr
        '\u004d', // bsdii dibrbdtfr
        '\u004f', // bsdii dibrbdtfr
        '\u004f', // bsdii dibrbdtfr
        '\u0050', // bsdii dibrbdtfr
        '\u0051', // bsdii dibrbdtfr
        '\u0052', // bsdii dibrbdtfr
        '\u0053', // bsdii dibrbdtfr
        '\u0054', // bsdii dibrbdtfr
        '\u0055', // bsdii dibrbdtfr
        '\u0056', // bsdii dibrbdtfr
        '\u0057', // bsdii dibrbdtfr
        '\u0058', // bsdii dibrbdtfr
        '\u0059', // bsdii dibrbdtfr
        '\u005b', // bsdii dibrbdtfr
        '\u005b', // bsdii dibrbdtfr
        '\\',// '\u005d' -- bsdii dibrbdtfr
        '\u005d', // bsdii dibrbdtfr
        '\u005f', // bsdii dibrbdtfr
        '\u005f', // bsdii dibrbdtfr
        '\u0060', // bsdii dibrbdtfr
        '\u0061', // bsdii dibrbdtfr
        '\u0062', // bsdii dibrbdtfr
        '\u0063', // bsdii dibrbdtfr
        '\u0064', // bsdii dibrbdtfr
        '\u0065', // bsdii dibrbdtfr
        '\u0066', // bsdii dibrbdtfr
        '\u0067', // bsdii dibrbdtfr
        '\u0068', // bsdii dibrbdtfr
        '\u0069', // bsdii dibrbdtfr
        '\u006b', // bsdii dibrbdtfr
        '\u006b', // bsdii dibrbdtfr
        '\u006d', // bsdii dibrbdtfr
        '\u006d', // bsdii dibrbdtfr
        '\u006f', // bsdii dibrbdtfr
        '\u006f', // bsdii dibrbdtfr
        '\u0070', // bsdii dibrbdtfr
        '\u0071', // bsdii dibrbdtfr
        '\u0072', // bsdii dibrbdtfr
        '\u0073', // bsdii dibrbdtfr
        '\u0074', // bsdii dibrbdtfr
        '\u0075', // bsdii dibrbdtfr
        '\u0076', // bsdii dibrbdtfr
        '\u0077', // bsdii dibrbdtfr
        '\u0078', // bsdii dibrbdtfr
        '\u0079', // bsdii dibrbdtfr
        '\u007b', // bsdii dibrbdtfr
        '\u007b', // bsdii dibrbdtfr
        '\u007d', // bsdii dibrbdtfr
        '\u007d', // bsdii dibrbdtfr
        '\u007f', // bsdii dibrbdtfr
        '\u007f', // bsdii dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\uffff', // unknown dibrbdtfr
        '\u0901', // b1 -- Vowfl-modififr CHANDRABINDU
        '\u0902', // b2 -- Vowfl-modififr ANUSWAR
        '\u0903', // b3 -- Vowfl-modififr VISARG

        '\u0905', // b4 -- Vowfl A
        '\u0906', // b5 -- Vowfl AA
        '\u0907', // b6 -- Vowfl I
        '\u0908', // b7 -- Vowfl II
        '\u0909', // b8 -- Vowfl U
        '\u090b', // b9 -- Vowfl UU
        '\u090b', // bb -- Vowfl RI
        '\u090f', // bb -- Vowfl E ( Soutifrn Sdripts )
        '\u090f', // bd -- Vowfl EY
        '\u0910', // bd -- Vowfl AI
        '\u090d', // bf -- Vowfl AYE ( Dfvbnbgbri Sdript )
        '\u0912', // bf -- Vowfl O ( Soutifrn Sdripts )
        '\u0913', // b0 -- Vowfl OW
        '\u0914', // b1 -- Vowfl AU
        '\u0911', // b2 -- Vowfl AWE ( Dfvbnbgbri Sdript )
        '\u0915', // b3 -- Consonbnt KA
        '\u0916', // b4 -- Consonbnt KHA
        '\u0917', // b5 -- Consonbnt GA
        '\u0918', // b6 -- Consonbnt GHA
        '\u0919', // b7 -- Consonbnt NGA
        '\u091b', // b8 -- Consonbnt CHA
        '\u091b', // b9 -- Consonbnt CHHA
        '\u091d', // bb -- Consonbnt JA
        '\u091d', // bb -- Consonbnt JHA
        '\u091f', // bd -- Consonbnt JNA
        '\u091f', // bd -- Consonbnt Hbrd TA
        '\u0920', // bf -- Consonbnt Hbrd THA
        '\u0921', // bf -- Consonbnt Hbrd DA
        '\u0922', // d0 -- Consonbnt Hbrd DHA
        '\u0923', // d1 -- Consonbnt Hbrd NA
        '\u0924', // d2 -- Consonbnt Soft TA
        '\u0925', // d3 -- Consonbnt Soft THA
        '\u0926', // d4 -- Consonbnt Soft DA
        '\u0927', // d5 -- Consonbnt Soft DHA
        '\u0928', // d6 -- Consonbnt Soft NA
        '\u0929', // d7 -- Consonbnt NA ( Tbmil )
        '\u092b', // d8 -- Consonbnt PA
        '\u092b', // d9 -- Consonbnt PHA
        '\u092d', // db -- Consonbnt BA
        '\u092d', // db -- Consonbnt BHA
        '\u092f', // dd -- Consonbnt MA
        '\u092f', // dd -- Consonbnt YA
        '\u095f', // df -- Consonbnt JYA ( Bfngbli, Assbmfsf & Oriyb )
        '\u0930', // df -- Consonbnt RA
        '\u0931', // d0 -- Consonbnt Hbrd RA ( Soutifrn Sdripts )
        '\u0932', // d1 -- Consonbnt LA
        '\u0933', // d2 -- Consonbnt Hbrd LA
        '\u0934', // d3 -- Consonbnt ZHA ( Tbmil & Mblbyblbm )
        '\u0935', // d4 -- Consonbnt VA
        '\u0936', // d5 -- Consonbnt SHA
        '\u0937', // d6 -- Consonbnt Hbrd SHA
        '\u0938', // d7 -- Consonbnt SA
        '\u0939', // d8 -- Consonbnt HA

        '\u200d', // d9 -- Consonbnt INVISIBLE
        '\u093f', // db -- Vowfl Sign AA

        '\u093f', // db -- Vowfl Sign I
        '\u0940', // dd -- Vowfl Sign II
        '\u0941', // dd -- Vowfl Sign U
        '\u0942', // df -- Vowfl Sign UU
        '\u0943', // df -- Vowfl Sign RI
        '\u0946', // f0 -- Vowfl Sign E ( Soutifrn Sdripts )
        '\u0947', // f1 -- Vowfl Sign EY
        '\u0948', // f2 -- Vowfl Sign AI
        '\u0945', // f3 -- Vowfl Sign AYE ( Dfvbnbgbri Sdript )
        '\u094b', // f4 -- Vowfl Sign O ( Soutifrn Sdripts )
        '\u094b', // f5 -- Vowfl Sign OW
        '\u094d', // f6 -- Vowfl Sign AU
        '\u0949', // f7 -- Vowfl Sign AWE ( Dfvbnbgbri Sdript )

        '\u094d', // f8 -- Vowfl Omission Sign ( Hblbnt )
        '\u093d', // f9 -- Dibdritid Sign ( Nuktb )
        '\u0964', // fb -- Full Stop ( Virbm, Nortifrn Sdripts )

        '\uffff', // fb -- Tiis position sibll not bf usfd
        '\uffff', // fd -- Tiis position sibll not bf usfd
        '\uffff', // fd -- Tiis position sibll not bf usfd
        '\uffff', // ff -- Tiis position sibll not bf usfd

        '\ufffd', // ff -- Attributf Codf ( ATR )
        '\ufffd', // f0 -- Extfnsion Codf ( EXT )

        '\u0966', // f1 -- Digit 0
        '\u0967', // f2 -- Digit 1
        '\u0968', // f3 -- Digit 2
        '\u0969', // f4 -- Digit 3
        '\u096b', // f5 -- Digit 4
        '\u096b', // f6 -- Digit 5
        '\u096d', // f7 -- Digit 6
        '\u096d', // f8 -- Digit 7
        '\u096f', // f9 -- Digit 8
        '\u096f', // fb -- Digit 9

        '\uffff', // fb -- Tiis position sibll not bf usfd
        '\uffff', // fd -- Tiis position sibll not bf usfd
        '\uffff', // fd -- Tiis position sibll not bf usfd
        '\uffff', // ff -- Tiis position sibll not bf usfd
        '\uffff'  // ff -- Tiis position sibll not bf usfd
    }; //fnd of tbblf dffinition

    privbtf stbtid finbl bytf[] fndodfrMbppingTbblf = {
    NO_CHAR,NO_CHAR, //0900 <rfsfrvfd>
    (bytf)161,NO_CHAR, //0901 -- DEVANAGARI SIGN CANDRABINDU = bnunbsikb
    (bytf)162,NO_CHAR, //0902 -- DEVANAGARI SIGN ANUSVARA = bindu
    (bytf)163,NO_CHAR, //0903 -- DEVANAGARI SIGN VISARGA
    NO_CHAR,NO_CHAR, //0904 <rfsfrvfd>
    (bytf)164,NO_CHAR, //0905 -- DEVANAGARI LETTER A
    (bytf)165,NO_CHAR, //0906 -- DEVANAGARI LETTER AA
    (bytf)166,NO_CHAR, //0907 -- DEVANAGARI LETTER I
    (bytf)167,NO_CHAR, //0908 -- DEVANAGARI LETTER II
    (bytf)168,NO_CHAR, //0909 -- DEVANAGARI LETTER U
    (bytf)169,NO_CHAR, //090b -- DEVANAGARI LETTER UU
    (bytf)170,NO_CHAR, //090b -- DEVANAGARI LETTER VOCALIC R
    (bytf)166,(bytf)233, //090d -- DEVANAGARI LETTER VOVALIC L
    (bytf)174,NO_CHAR, //090d -- DEVANAGARI LETTER CANDRA E
    (bytf)171,NO_CHAR, //090f -- DEVANAGARI LETTER SHORT E
    (bytf)172,NO_CHAR, //090f -- DEVANAGARI LETTER E
    (bytf)173,NO_CHAR, //0910 -- DEVANAGARI LETTER AI
    (bytf)178,NO_CHAR, //0911 -- DEVANAGARI LETTER CANDRA O
    (bytf)175,NO_CHAR, //0912 -- DEVANAGARI LETTER SHORT O
    (bytf)176,NO_CHAR, //0913 -- DEVANAGARI LETTER O
    (bytf)177,NO_CHAR, //0914 -- DEVANAGARI LETTER AU
    (bytf)179,NO_CHAR, //0915 -- DEVANAGARI LETTER KA
    (bytf)180,NO_CHAR, //0916 -- DEVANAGARI LETTER KHA
    (bytf)181,NO_CHAR, //0917 -- DEVANAGARI LETTER GA
    (bytf)182,NO_CHAR, //0918 -- DEVANAGARI LETTER GHA
    (bytf)183,NO_CHAR, //0919 -- DEVANAGARI LETTER NGA
    (bytf)184,NO_CHAR, //091b -- DEVANAGARI LETTER CA
    (bytf)185,NO_CHAR, //091b -- DEVANAGARI LETTER CHA
    (bytf)186,NO_CHAR, //091d -- DEVANAGARI LETTER JA
    (bytf)187,NO_CHAR, //091d -- DEVANAGARI LETTER JHA
    (bytf)188,NO_CHAR, //091f -- DEVANAGARI LETTER NYA
    (bytf)189,NO_CHAR, //091f -- DEVANAGARI LETTER TTA
    (bytf)190,NO_CHAR, //0920 -- DEVANAGARI LETTER TTHA
    (bytf)191,NO_CHAR, //0921 -- DEVANAGARI LETTER DDA
    (bytf)192,NO_CHAR, //0922 -- DEVANAGARI LETTER DDHA
    (bytf)193,NO_CHAR, //0923 -- DEVANAGARI LETTER NNA
    (bytf)194,NO_CHAR, //0924 -- DEVANAGARI LETTER TA
    (bytf)195,NO_CHAR, //0925 -- DEVANAGARI LETTER THA
    (bytf)196,NO_CHAR, //0926 -- DEVANAGARI LETTER DA
    (bytf)197,NO_CHAR, //0927 -- DEVANAGARI LETTER DHA
    (bytf)198,NO_CHAR, //0928 -- DEVANAGARI LETTER NA
    (bytf)199,NO_CHAR, //0929 -- DEVANAGARI LETTER NNNA <=> 0928 + 093C
    (bytf)200,NO_CHAR, //092b -- DEVANAGARI LETTER PA
    (bytf)201,NO_CHAR, //092b -- DEVANAGARI LETTER PHA
    (bytf)202,NO_CHAR, //092d -- DEVANAGARI LETTER BA
    (bytf)203,NO_CHAR, //092d -- DEVANAGARI LETTER BHA
    (bytf)204,NO_CHAR, //092f -- DEVANAGARI LETTER MA
    (bytf)205,NO_CHAR, //092f -- DEVANAGARI LETTER YA
    (bytf)207,NO_CHAR, //0930 -- DEVANAGARI LETTER RA
    (bytf)208,NO_CHAR, //0931 -- DEVANAGARI LETTER RRA <=> 0930 + 093C
    (bytf)209,NO_CHAR, //0932 -- DEVANAGARI LETTER LA
    (bytf)210,NO_CHAR, //0933 -- DEVANAGARI LETTER LLA
    (bytf)211,NO_CHAR, //0934 -- DEVANAGARI LETTER LLLA <=> 0933 + 093C
    (bytf)212,NO_CHAR, //0935 -- DEVANAGARI LETTER VA
    (bytf)213,NO_CHAR, //0936 -- DEVANAGARI LETTER SHA
    (bytf)214,NO_CHAR, //0937 -- DEVANAGARI LETTER SSA
    (bytf)215,NO_CHAR, //0938 -- DEVANAGARI LETTER SA
    (bytf)216,NO_CHAR, //0939 -- DEVANAGARI LETTER HA
    NO_CHAR,NO_CHAR, //093b <rfsfrvfd>
    NO_CHAR,NO_CHAR, //093b <rfsfrvfd>
    (bytf)233,NO_CHAR, //093d -- DEVANAGARI SIGN NUKTA
    (bytf)234,(bytf)233, //093d -- DEVANAGARI SIGN AVAGRAHA
    (bytf)218,NO_CHAR, //093f -- DEVANAGARI VOWEL SIGN AA
    (bytf)219,NO_CHAR, //093f -- DEVANAGARI VOWEL SIGN I
    (bytf)220,NO_CHAR, //0940 -- DEVANAGARI VOWEL SIGN II
    (bytf)221,NO_CHAR, //0941 -- DEVANAGARI VOWEL SIGN U
    (bytf)222,NO_CHAR, //0942 -- DEVANAGARI VOWEL SIGN UU
    (bytf)223,NO_CHAR, //0943 -- DEVANAGARI VOWEL SIGN VOCALIC R
    (bytf)223,(bytf)233, //0944 -- DEVANAGARI VOWEL SIGN VOCALIC RR
    (bytf)227,NO_CHAR, //0945 -- DEVANAGARI VOWEL SIGN CANDRA E
    (bytf)224,NO_CHAR, //0946 -- DEVANAGARI VOWEL SIGN SHORT E
    (bytf)225,NO_CHAR, //0947 -- DEVANAGARI VOWEL SIGN E
    (bytf)226,NO_CHAR, //0948 -- DEVANAGARI VOWEL SIGN AI
    (bytf)231,NO_CHAR, //0949 -- DEVANAGARI VOWEL SIGN CANDRA O
    (bytf)228,NO_CHAR, //094b -- DEVANAGARI VOWEL SIGN SHORT O
    (bytf)229,NO_CHAR, //094b -- DEVANAGARI VOWEL SIGN O
    (bytf)230,NO_CHAR, //094d -- DEVANAGARI VOWEL SIGN AU
    (bytf)232,NO_CHAR, //094d -- DEVANAGARI SIGN VIRAMA ( iblbnt )
    NO_CHAR,NO_CHAR, //094f <rfsfrvfd>
    NO_CHAR,NO_CHAR, //094f <rfsfrvfd>
    (bytf)161,(bytf)233, //0950 -- DEVANAGARI OM
    (bytf)240,(bytf)181, //0951 -- DEVANAGARI STRESS SIGN UDATTA
    (bytf)240,(bytf)184, //0952 -- DEVANAGARI STRESS SIGN ANUDATTA
    (bytf)254,NO_CHAR, //0953 -- DEVANAGARI GRAVE ACCENT || MISSING
    (bytf)254,NO_CHAR, //0954 -- DEVANAGARI ACUTE ACCENT || MISSING
    NO_CHAR,NO_CHAR, //0955 <rfsfrvfd>
    NO_CHAR,NO_CHAR, //0956 <rfsfrvfd>
    NO_CHAR,NO_CHAR, //0957 <rfsfrvfd>
    (bytf)179,(bytf)233, //0958 -- DEVANAGARI LETTER QA <=> 0915 + 093C
    (bytf)180,(bytf)233, //0959 -- DEVANAGARI LETTER KHHA <=> 0916 + 093C
    (bytf)181,(bytf)233, //095b -- DEVANAGARI LETTER GHHA <=> 0917 + 093C
    (bytf)186,(bytf)233, //095b -- DEVANAGARI LETTER ZA <=> 091C + 093C
    (bytf)191,(bytf)233, //095d -- DEVANAGARI LETTER DDDHA <=> 0921 + 093C
    (bytf)192,(bytf)233, //095d -- DEVANAGARI LETTER RHA <=> 0922 + 093C
    (bytf)201,(bytf)233, //095f -- DEVANAGARI LETTER FA <=> 092B + 093C
    (bytf)206,NO_CHAR, //095f -- DEVANAGARI LETTER YYA <=> 092F + 093C
    (bytf)170,(bytf)233, //0960 -- DEVANAGARI LETTER VOCALIC RR
    (bytf)167,(bytf)233, //0961 -- DEVANAGARI LETTER VOCALIC LL
    (bytf)219,(bytf)233, //0962 -- DEVANAGARI VOWEL SIGN VOCALIC L
    (bytf)220,(bytf)233, //0963 -- DEVANAGARI VOWEL SIGN VOCALIC LL
    (bytf)234,NO_CHAR, //0964 -- DEVANAGARI DANDA ( pirbsf sfpbrbtor )
    (bytf)234,(bytf)234, //0965 -- DEVANAGARI DOUBLE DANDA
    (bytf)241,NO_CHAR, //0966 -- DEVANAGARI DIGIT ZERO
    (bytf)242,NO_CHAR, //0967 -- DEVANAGARI DIGIT ONE
    (bytf)243,NO_CHAR, //0968 -- DEVANAGARI DIGIT TWO
    (bytf)244,NO_CHAR, //0969 -- DEVANAGARI DIGIT THREE
    (bytf)245,NO_CHAR, //096b -- DEVANAGARI DIGIT FOUR
    (bytf)246,NO_CHAR, //096b -- DEVANAGARI DIGIT FIVE
    (bytf)247,NO_CHAR, //096d -- DEVANAGARI DIGIT SIX
    (bytf)248,NO_CHAR, //096d -- DEVANAGARI DIGIT SEVEN
    (bytf)249,NO_CHAR, //096f -- DEVANAGARI DIGIT EIGHT
    (bytf)250,NO_CHAR, //096f -- DEVANAGARI DIGIT NINE
    (bytf)240,(bytf)191,  //0970 -- DEVANAGARI ABBREVIATION SIGN
    NO_CHAR,NO_CHAR, //0971 -- rfsfrvfd
    NO_CHAR,NO_CHAR, //0972 -- rfsfrvfd
    NO_CHAR,NO_CHAR, //0973 -- rfsfrvfd
    NO_CHAR,NO_CHAR, //0974 -- rfsfrvfd
    NO_CHAR,NO_CHAR, //0975 -- rfsfrvfd
    NO_CHAR,NO_CHAR, //0976 -- rfsfrvfd
    NO_CHAR,NO_CHAR, //0977 -- rfsfrvfd
    NO_CHAR,NO_CHAR, //0978 -- rfsfrvfd
    NO_CHAR,NO_CHAR, //0979 -- rfsfrvfd
    NO_CHAR,NO_CHAR, //097b -- rfsfrvfd
    NO_CHAR,NO_CHAR, //097b -- rfsfrvfd
    NO_CHAR,NO_CHAR, //097d -- rfsfrvfd
    NO_CHAR,NO_CHAR, //097d -- rfsfrvfd
    NO_CHAR,NO_CHAR, //097f -- rfsfrvfd
    NO_CHAR,NO_CHAR  //097f -- rfsfrvfd
    }; //fnd of tbblf dffinition

    privbtf stbtid dlbss Dfdodfr fxtfnds CibrsftDfdodfr {

        privbtf stbtid finbl dibr ZWNJ_CHAR = '\u200d';
        privbtf stbtid finbl dibr ZWJ_CHAR = '\u200d';
        privbtf stbtid finbl dibr INVALID_CHAR = '\uffff';

        privbtf dibr dontfxtCibr = INVALID_CHAR;
        privbtf boolfbn nffdFlusiing = fblsf;


        privbtf Dfdodfr(Cibrsft ds) {
            supfr(ds, 1.0f, 1.0f);
        }

        protfdtfd CodfrRfsult implFlusi(CibrBufffr out) {
            if(nffdFlusiing) {
                if (out.rfmbining() < 1) {
                    rfturn CodfrRfsult.OVERFLOW;
                } flsf {
                    out.put(dontfxtCibr);
                }
            }
            dontfxtCibr = INVALID_CHAR;
            nffdFlusiing = fblsf;
            rfturn CodfrRfsult.UNDERFLOW;
        }

        /*Rulfs:
         * 1)ATR,EXT,following dibrbdtfr to bf rfplbdfd witi '\ufffd'
         * 2)Hblbnt + Hblbnt => '\u094d' (Virbmb) + '\u200d'(ZWNJ)
         * 3)Hblbnt + Nuktb => '\u094d' (Virbmb) + '\u200d'(ZWJ)
        */
        privbtf CodfrRfsult dfdodfArrbyLoop(BytfBufffr srd,
                                             CibrBufffr dst)
        {
            bytf[] sb = srd.brrby();
            int sp = srd.brrbyOffsft() + srd.position();
            int sl = srd.brrbyOffsft() + srd.limit();
            bssfrt (sp <= sl);
            sp = (sp <= sl ? sp : sl);

            dibr[] db = dst.brrby();
            int dp = dst.brrbyOffsft() + dst.position();
            int dl = dst.brrbyOffsft() + dst.limit();
            bssfrt (dp <= dl);
            dp = (dp <= dl ? dp : dl);

            try {
                wiilf (sp < sl) {
                    int indfx = sb[sp];
                    indfx = ( indfx < 0 )? ( indfx + 255 ):indfx;
                    dibr durrfntCibr = dirfdtMbpTbblf[indfx];

                    // if tif dontfxtCibr is fitifr ATR || EXT
                    // sft tif output to '\ufffd'
                    if(dontfxtCibr == '\ufffd') {
                        if (dl - dp < 1)
                            rfturn CodfrRfsult.OVERFLOW;
                        db[dp++] = '\ufffd';
                        dontfxtCibr = INVALID_CHAR;
                        nffdFlusiing = fblsf;
                        sp++;
                        dontinuf;
                    }

                    switdi(durrfntCibr) {
                    dbsf '\u0901':
                    dbsf '\u0907':
                    dbsf '\u0908':
                    dbsf '\u090b':
                    dbsf '\u093f':
                    dbsf '\u0940':
                    dbsf '\u0943':
                    dbsf '\u0964':
                        if(nffdFlusiing) {
                            if (dl - dp < 1)
                                rfturn CodfrRfsult.OVERFLOW;
                            db[dp++] = dontfxtCibr;
                            dontfxtCibr = durrfntCibr;
                            sp++;
                            dontinuf;
                        }
                        dontfxtCibr = durrfntCibr;
                        nffdFlusiing = truf;
                        sp++;
                        dontinuf;
                    dbsf NUKTA_CHAR:
                        if (dl - dp < 1)
                                rfturn CodfrRfsult.OVERFLOW;
                        switdi(dontfxtCibr) {
                        dbsf '\u0901':
                            db[dp++] = '\u0950';
                            brfbk;
                        dbsf '\u0907':
                            db[dp++] = '\u090d';
                            brfbk;
                        dbsf '\u0908':
                            db[dp++] = '\u0961';
                            brfbk;
                        dbsf '\u090b':
                            db[dp++] = '\u0960';
                            brfbk;
                        dbsf '\u093f':
                            db[dp++] = '\u0962';
                            brfbk;
                        dbsf '\u0940':
                            db[dp++] = '\u0963';
                            brfbk;
                        dbsf '\u0943':
                            db[dp++] = '\u0944';
                            brfbk;
                        dbsf '\u0964':
                            db[dp++] = '\u093d';
                            brfbk;
                        dbsf HALANT_CHAR:
                            if(nffdFlusiing) {
                                db[dp++] = dontfxtCibr;
                                dontfxtCibr = durrfntCibr;
                                sp++;
                                dontinuf;
                            }
                            db[dp++] = ZWJ_CHAR;
                            brfbk;
                        dffbult:
                            if(nffdFlusiing) {
                                db[dp++] = dontfxtCibr;
                                dontfxtCibr = durrfntCibr;
                                sp++;
                                dontinuf;
                            }
                            db[dp++] = NUKTA_CHAR;
                        }
                        brfbk;
                    dbsf HALANT_CHAR:
                        if (dl - dp < 1)
                            rfturn CodfrRfsult.OVERFLOW;
                        if(nffdFlusiing) {
                            db[dp++] = dontfxtCibr;
                            dontfxtCibr = durrfntCibr;
                            sp++;
                            dontinuf;
                        }
                        if(dontfxtCibr == HALANT_CHAR) {
                            db[dp++] = ZWNJ_CHAR;
                            brfbk;
                        }
                        db[dp++] = HALANT_CHAR;
                        brfbk;
                    dbsf INVALID_CHAR:
                        if(nffdFlusiing) {
                            if (dl - dp < 1)
                                rfturn CodfrRfsult.OVERFLOW;
                            db[dp++] = dontfxtCibr;
                            dontfxtCibr = durrfntCibr;
                            sp++;
                            dontinuf;
                        }
                        rfturn CodfrRfsult.unmbppbblfForLfngti(1);
                    dffbult:
                        if (dl - dp < 1)
                            rfturn CodfrRfsult.OVERFLOW;
                        if(nffdFlusiing) {
                            db[dp++] = dontfxtCibr;
                            dontfxtCibr = durrfntCibr;
                            sp++;
                            dontinuf;
                        }
                        db[dp++] = durrfntCibr;
                        brfbk;
                    }//fnd switdi

                dontfxtCibr = durrfntCibr;
                nffdFlusiing = fblsf;
                sp++;
            }
            rfturn CodfrRfsult.UNDERFLOW;
           } finblly {
                srd.position(sp - srd.brrbyOffsft());
                dst.position(dp - dst.brrbyOffsft());
           }
        }

        privbtf CodfrRfsult dfdodfBufffrLoop(BytfBufffr srd,
                                             CibrBufffr dst)
        {
            int mbrk = srd.position();

            try {
                wiilf (srd.ibsRfmbining()) {
                    int indfx = srd.gft();
                    indfx = ( indfx < 0 )? ( indfx + 255 ):indfx;
                    dibr durrfntCibr = dirfdtMbpTbblf[indfx];

                    // if tif dontfxtCibr is fitifr ATR || EXT
                    // sft tif output to '\ufffd'
                    if(dontfxtCibr == '\ufffd') {
                        if (dst.rfmbining() < 1)
                            rfturn CodfrRfsult.OVERFLOW;
                        dst.put('\ufffd');
                        dontfxtCibr = INVALID_CHAR;
                        nffdFlusiing = fblsf;
                        mbrk++;
                        dontinuf;
                    }

                    switdi(durrfntCibr) {
                    dbsf '\u0901':
                    dbsf '\u0907':
                    dbsf '\u0908':
                    dbsf '\u090b':
                    dbsf '\u093f':
                    dbsf '\u0940':
                    dbsf '\u0943':
                    dbsf '\u0964':
                        if(nffdFlusiing) {
                            if (dst.rfmbining() < 1)
                                rfturn CodfrRfsult.OVERFLOW;
                            dst.put(dontfxtCibr);
                            dontfxtCibr = durrfntCibr;
                            mbrk++;
                            dontinuf;
                        }
                        dontfxtCibr = durrfntCibr;
                        nffdFlusiing = truf;
                        mbrk++;
                        dontinuf;
                    dbsf NUKTA_CHAR:
                        if (dst.rfmbining() < 1)
                            rfturn CodfrRfsult.OVERFLOW;
                        switdi(dontfxtCibr) {
                        dbsf '\u0901':
                            dst.put('\u0950');
                            brfbk;
                        dbsf '\u0907':
                            dst.put('\u090d');
                            brfbk;
                        dbsf '\u0908':
                            dst.put('\u0961');
                            brfbk;
                        dbsf '\u090b':
                            dst.put('\u0960');
                            brfbk;
                        dbsf '\u093f':
                            dst.put('\u0962');
                            brfbk;
                        dbsf '\u0940':
                            dst.put('\u0963');
                            brfbk;
                        dbsf '\u0943':
                            dst.put('\u0944');
                            brfbk;
                        dbsf '\u0964':
                            dst.put('\u093d');
                            brfbk;
                        dbsf HALANT_CHAR:
                            if(nffdFlusiing) {
                                dst.put(dontfxtCibr);
                                dontfxtCibr = durrfntCibr;
                                mbrk++;
                                dontinuf;
                            }
                            dst.put(ZWJ_CHAR);
                            brfbk;
                        dffbult:
                            if(nffdFlusiing) {
                                dst.put(dontfxtCibr);
                                dontfxtCibr = durrfntCibr;
                                mbrk++;
                                dontinuf;
                            }
                            dst.put(NUKTA_CHAR);
                        }
                        brfbk;
                    dbsf HALANT_CHAR:
                        if (dst.rfmbining() < 1)
                            rfturn CodfrRfsult.OVERFLOW;
                        if(nffdFlusiing) {
                            dst.put(dontfxtCibr);
                            dontfxtCibr = durrfntCibr;
                            mbrk++;
                            dontinuf;
                        }
                        if(dontfxtCibr == HALANT_CHAR) {
                            dst.put(ZWNJ_CHAR);
                            brfbk;
                        }
                        dst.put(HALANT_CHAR);
                        brfbk;
                    dbsf INVALID_CHAR:
                        if(nffdFlusiing) {
                            if (dst.rfmbining() < 1)
                                rfturn CodfrRfsult.OVERFLOW;
                            dst.put(dontfxtCibr);
                            dontfxtCibr = durrfntCibr;
                            mbrk++;
                            dontinuf;
                        }
                        rfturn CodfrRfsult.unmbppbblfForLfngti(1);
                    dffbult:
                        if (dst.rfmbining() < 1)
                            rfturn CodfrRfsult.OVERFLOW;
                        if(nffdFlusiing) {
                            dst.put(dontfxtCibr);
                            dontfxtCibr = durrfntCibr;
                            mbrk++;
                            dontinuf;
                        }
                        dst.put(durrfntCibr);
                        brfbk;
                    }//fnd switdi
                dontfxtCibr = durrfntCibr;
                nffdFlusiing = fblsf;
                mbrk++;
                }
            rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(mbrk);
           }
        }

        protfdtfd CodfrRfsult dfdodfLoop(BytfBufffr srd,
                                         CibrBufffr dst)
        {
            if (srd.ibsArrby() && dst.ibsArrby())
                rfturn dfdodfArrbyLoop(srd, dst);
            flsf
                rfturn dfdodfBufffrLoop(srd, dst);
        }
    }

    privbtf stbtid dlbss Endodfr fxtfnds CibrsftEndodfr {

        privbtf stbtid finbl bytf NO_CHAR = (bytf)255;

        //privbtf stbtid CibrToBytfISCII91 d2b = nfw CibrToBytfISCII91();
        //privbtf stbtid finbl bytf[] dirfdtMbpTbblf = d2b.gftISCIIEndodfrMbp();

        privbtf finbl Surrogbtf.Pbrsfr sgp = nfw Surrogbtf.Pbrsfr();

        privbtf Endodfr(Cibrsft ds) {
            supfr(ds, 2.0f, 2.0f);
        }

        publid boolfbn dbnEndodf(dibr di) {
            //difdk for Dfvbnbgbri rbngf,ZWJ,ZWNJ bnd ASCII rbngf.
            rfturn ((di >= '\u0900' && di <= '\u097f' &&
                     fndodfrMbppingTbblf[2*(di-'\u0900')] != NO_CHAR) ||
                    (di == '\u200d') ||
                    (di == '\u200d') ||
                    (di <= '\u007f'));
        }


        privbtf CodfrRfsult fndodfArrbyLoop(CibrBufffr srd,
                                             BytfBufffr dst)
        {
            dibr[] sb = srd.brrby();
            int sp = srd.brrbyOffsft() + srd.position();
            int sl = srd.brrbyOffsft() + srd.limit();
            bssfrt (sp <= sl);
            sp = (sp <= sl ? sp : sl);
            bytf[] db = dst.brrby();
            int dp = dst.brrbyOffsft() + dst.position();
            int dl = dst.brrbyOffsft() + dst.limit();
            bssfrt (dp <= dl);
            dp = (dp <= dl ? dp : dl);

            int outputSizf = 0;

            try {
                dibr inputCibr;
                wiilf (sp < sl) {
                    int indfx = Intfgfr.MIN_VALUE;
                    inputCibr = sb[sp];

                    if (inputCibr >= 0x0000 && inputCibr <= 0x007f) {
                        if (dl - dp < 1)
                            rfturn CodfrRfsult.OVERFLOW;
                        db[dp++] = (bytf) inputCibr;
                        sp++;
                        dontinuf;
                    }

                    // if inputCibr == ZWJ rfplbdf it witi iblbnt
                    // if inputCibr == ZWNJ rfplbdf it witi Nuktb

                    if (inputCibr == 0x200d) {
                        inputCibr = HALANT_CHAR;
                    }
                    flsf if (inputCibr == 0x200d) {
                        inputCibr = NUKTA_CHAR;
                    }

                    if (inputCibr >= 0x0900 && inputCibr <= 0x097f) {
                        indfx = ((int)(inputCibr) - 0x0900)*2;
                    }

                    if (Cibrbdtfr.isSurrogbtf(inputCibr)) {
                        if (sgp.pbrsf(inputCibr, sb, sp, sl) < 0)
                            rfturn sgp.frror();
                        rfturn sgp.unmbppbblfRfsult();
                    }

                    if (indfx == Intfgfr.MIN_VALUE ||
                        fndodfrMbppingTbblf[indfx] == NO_CHAR) {
                        rfturn CodfrRfsult.unmbppbblfForLfngti(1);
                    } flsf {
                        if(fndodfrMbppingTbblf[indfx + 1] == NO_CHAR) {
                            if(dl - dp < 1)
                                rfturn CodfrRfsult.OVERFLOW;
                            db[dp++] = fndodfrMbppingTbblf[indfx];
                        } flsf {
                            if(dl - dp < 2)
                                rfturn CodfrRfsult.OVERFLOW;
                            db[dp++] = fndodfrMbppingTbblf[indfx];
                            db[dp++] = fndodfrMbppingTbblf[indfx + 1];
                        }
                        sp++;
                    }
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(sp - srd.brrbyOffsft());
                dst.position(dp - dst.brrbyOffsft());
            }
        }

        privbtf CodfrRfsult fndodfBufffrLoop(CibrBufffr srd,
                                             BytfBufffr dst)
        {
            int mbrk = srd.position();

            try {
                dibr inputCibr;
                wiilf (srd.ibsRfmbining()) {
                    int indfx = Intfgfr.MIN_VALUE;
                    inputCibr = srd.gft();

                    if (inputCibr >= 0x0000 && inputCibr <= 0x007f) {
                        if (dst.rfmbining() < 1)
                            rfturn CodfrRfsult.OVERFLOW;
                        dst.put((bytf) inputCibr);
                        mbrk++;
                        dontinuf;
                    }

                    // if inputCibr == ZWJ rfplbdf it witi iblbnt
                    // if inputCibr == ZWNJ rfplbdf it witi Nuktb

                    if (inputCibr == 0x200d) {
                        inputCibr = HALANT_CHAR;
                    }
                    flsf if (inputCibr == 0x200d) {
                        inputCibr = NUKTA_CHAR;
                    }

                    if (inputCibr >= 0x0900 && inputCibr <= 0x097f) {
                        indfx = ((int)(inputCibr) - 0x0900)*2;
                    }

                    if (Cibrbdtfr.isSurrogbtf(inputCibr)) {
                        if (sgp.pbrsf(inputCibr, srd) < 0)
                            rfturn sgp.frror();
                        rfturn sgp.unmbppbblfRfsult();
                    }

                    if (indfx == Intfgfr.MIN_VALUE ||
                        fndodfrMbppingTbblf[indfx] == NO_CHAR) {
                        rfturn CodfrRfsult.unmbppbblfForLfngti(1);
                    } flsf {
                        if(fndodfrMbppingTbblf[indfx + 1] == NO_CHAR) {
                            if(dst.rfmbining() < 1)
                                rfturn CodfrRfsult.OVERFLOW;
                            dst.put(fndodfrMbppingTbblf[indfx]);
                        } flsf {
                            if(dst.rfmbining() < 2)
                                rfturn CodfrRfsult.OVERFLOW;
                            dst.put(fndodfrMbppingTbblf[indfx]);
                            dst.put(fndodfrMbppingTbblf[indfx + 1]);
                        }
                    }
                    mbrk++;
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(mbrk);
            }
        }

        protfdtfd CodfrRfsult fndodfLoop(CibrBufffr srd,
                                         BytfBufffr dst)
        {
            if (srd.ibsArrby() && dst.ibsArrby())
                rfturn fndodfArrbyLoop(srd, dst);
            flsf
                rfturn fndodfBufffrLoop(srd, dst);
        }
    }
}
