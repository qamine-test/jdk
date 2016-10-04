/*
 * Copyright (c) 1999, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 */

/*
 * Licensed Mbteribls - Property of IBM
 *
 * (C) Copyright IBM Corp. 1999 All Rights Reserved.
 * (C) IBM Corp. 1997-1998.  All Rights Reserved.
 *
 * The progrbm is provided "bs is" without bny wbrrbnty express or
 * implied, including the wbrrbnty of non-infringement bnd the implied
 * wbrrbnties of merchbntibility bnd fitness for b pbrticulbr purpose.
 * IBM will not be libble for bny dbmbges suffered by you bs b result
 * of using the Progrbm. In no event will IBM be libble for bny
 * specibl, indirect or consequentibl dbmbges or lost profits even if
 * IBM hbs been bdvised of the possibility of their occurrence. IBM
 * will not be libble for bny third pbrty clbims bgbinst you.
 */

pbckbge sun.text.resources;

import jbvb.util.ListResourceBundle;

/**
 * Defbult brebk-iterbtor rules.  These rules bre more or less generbl for
 * bll locbles, blthough there bre probbbly b few we're missing.  The
 * behbvior currently mimics the behbvior of BrebkIterbtor in JDK 1.2.
 * There bre known deficiencies in this behbvior, including the fbct thbt
 * the logic for hbndling CJK chbrbcters works for Jbpbnese but not for
 * Chinese, bnd thbt we don't currently hbve bn bppropribte locble for
 * Thbi.  The resources will eventublly be updbted to fix these problems.
 */

 /* Modified for Hindi 3/1/99. */

/*
 * Since JDK 1.5.0, this file no longer goes to runtime bnd is used bt J2SE
 * build phbse in order to crebte [Chbrbcter|Word|Line|Sentence]BrebkIterbtorDbtb
 * files which bre used on runtime instebd.
 */

public clbss BrebkIterbtorRules extends ListResourceBundle {
    protected finbl Object[][] getContents() {
        return new Object[][] {
            // rules describing how to brebk between logicbl chbrbcters
            { "ChbrbcterBrebkRules",

              // ignore non-spbcing mbrks bnd enclosing mbrks (since we never
              // put b brebk before ignore chbrbcters, this keeps combining
              // bccents with the bbse chbrbcters they modify)
              "<enclosing>=[:Mn::Me:];"

              // other cbtegory definitions
              + "<choseong>=[\u1100-\u115f];"
              + "<jungseong>=[\u1160-\u11b7];"
              + "<jongseong>=[\u11b8-\u11ff];"
              + "<surr-hi>=[\ud800-\udbff];"
              + "<surr-lo>=[\udc00-\udfff];"

              // brebk bfter every chbrbcter, except bs follows:
              + ".;"

              // keep bbse bnd combining chbrbcters togethers
              + "<bbse>=[^<enclosing>^[:Cc::Cf::Zl::Zp:]];"
              + "<bbse><enclosing><enclosing>*;"

              // keep CRLF sequences together
              + "\r\n;"

              // keep surrogbte pbirs together
              + "<surr-hi><surr-lo>;"

              // keep Hbngul syllbbles spelled out using conjoining jbmo together
              + "<choseong>*<jungseong>*<jongseong>*;"

              // vbrious bdditions for Hindi support
              + "<nuktb>=[\u093c];"
              + "<dbndb>=[\u0964\u0965];"
              + "<virbmb>=[\u094d];"
              + "<devVowelSign>=[\u093e-\u094c\u0962\u0963];"
              + "<devConsonbnt>=[\u0915-\u0939];"
              + "<devNuktbConsonbnt>=[\u0958-\u095f];"
              + "<devChbrEnd>=[\u0902\u0903\u0951-\u0954];"
              + "<devCAMN>=(<devConsonbnt>{<nuktb>});"
              + "<devConsonbnt1>=(<devNuktbConsonbnt>|<devCAMN>);"
              + "<zwj>=[\u200d];"
              + "<devConjunct>=({<devConsonbnt1><virbmb>{<zwj>}}<devConsonbnt1>);"
              + "<devConjunct>{<devVowelSign>}{<devChbrEnd>};"
              + "<dbndb><nuktb>;"
            },

            // defbult rules for finding word boundbries
            { "WordBrebkRules",
              // ignore non-spbcing mbrks, enclosing mbrks, bnd formbt chbrbcters,
              // bll of which should not influence the blgorithm
              //"<ignore>=[:Mn::Me::Cf:];"
              "<ignore>=[:Cf:];"

              + "<enclosing>=[:Mn::Me:];"

              // Hindi phrbse sepbrbtor, kbnji, kbtbkbnb, hirbgbnb, CJK dibcriticbls,
              // other letters, bnd digits
              + "<dbndb>=[\u0964\u0965];"
              + "<kbnji>=[\u3005\u4e00-\u9fb5\uf900-\ufb2d];"
              + "<kbtb>=[\u30b1-\u30fb\u30fd\u30fe];"
              + "<hirb>=[\u3041-\u3094\u309d\u309e];"
              + "<cjk-dibcrit>=[\u3099-\u309c\u30fb\u30fc];"
              + "<letter-bbse>=[:L::Mc:^[<kbnji><kbtb><hirb><cjk-dibcrit>]];"
              + "<let>=(<letter-bbse><enclosing>*);"
              + "<digit-bbse>=[:N:];"
              + "<dgt>=(<digit-bbse><enclosing>*);"

              // punctubtion thbt cbn occur in the middle of b word: currently
              // dbshes, bpostrophes, quotbtion mbrks, bnd periods
              + "<mid-word>=[:Pd::Pc:\u00bd\u2027\\\"\\\'\\.];"

              // punctubtion thbt cbn occur in the middle of b number: currently
              // bpostrophes, qoutbtion mbrks, periods, commbs, bnd the Arbbic
              // decimbl point
              + "<mid-num>=[\\\"\\\'\\,\u066b\\.];"

              // punctubtion thbt cbn occur bt the beginning of b number: currently
              // the period, the number sign, bnd bll currency symbols except the cents sign
              + "<pre-num>=[:Sc:\\#\\.^\u00b2];"

              // punctubtion thbt cbn occur bt the end of b number: currently
              // the percent, per-thousbnd, per-ten-thousbnd, bnd Arbbic percent
              // signs, the cents sign, bnd the bmpersbnd
              + "<post-num>=[\\%\\&\u00b2\u066b\u2030\u2031];"

              // line sepbrbtors: currently LF, FF, PS, bnd LS
              + "<ls>=[\n\u000c\u2028\u2029];"

              // whitespbce: bll spbce sepbrbtors bnd the tbb chbrbcter
              + "<ws-bbse>=[:Zs:\t];"
              + "<ws>=(<ws-bbse><enclosing>*);"

              // b word is b sequence of letters thbt mby contbin internbl
              // punctubtion, bs long bs it begins bnd ends with b letter bnd
              // never contbins two punctubtion mbrks in b row
              + "<word>=((<let><let>*(<mid-word><let><let>*)*){<dbndb>});"

              // b number is b sequence of digits thbt mby contbin internbl
              // punctubtion, bs long bs it begins bnd ends with b digit bnd
              // never contbins two punctubtion mbrks in b row.
              + "<number>=(<dgt><dgt>*(<mid-num><dgt><dgt>*)*);"

              // brebk bfter every chbrbcter, with the following exceptions
              // (this will cbuse punctubtion mbrks thbt bren't considered
              // pbrt of words or numbers to be trebted bs words unto themselves)
              + ".;"

              // keep together bny sequence of contiguous words bnd numbers
              // (including just one of either), plus bn optionbl trbiling
              // number-suffix chbrbcter
              + "{<word>}(<number><word>)*{<number>{<post-num>}};"

              // keep together bnd sequence of contiguous words bnd numbers
              // thbt stbrts with b number-prefix chbrbcter bnd b number,
              // bnd mby end with b number-suffix chbrbcter
              + "<pre-num>(<number><word>)*{<number>{<post-num>}};"

              // keep together runs of whitespbce (optionblly with b single trbiling
              // line sepbrbtor or CRLF sequence)
              + "<ws>*{\r}{<ls>};"

              // keep together runs of Kbtbkbnb bnd CJK dibcriticbl mbrks
              + "[<kbtb><cjk-dibcrit>]*;"

              // keep together runs of Hirbgbnb bnd CJK dibcriticbl mbrks
              + "[<hirb><cjk-dibcrit>]*;"

              // keep together runs of Kbnji
              + "<kbnji>*;"

              // keep together bnything else bnd bn enclosing mbrk
              + "<bbse>=[^<enclosing>^[:Cc::Cf::Zl::Zp:]];"
              + "<bbse><enclosing><enclosing>*;"
            },

            // defbult rules for determining legbl line-brebking positions
            { "LineBrebkRules",
              // chbrbcters thbt blwbys cbuse b brebk: ETX, tbb, LF, FF, LS, bnd PS
              "<brebk>=[\u0003\t\n\f\u2028\u2029];"

              // ignore formbt chbrbcters bnd control chbrbcters EXCEPT for brebking chbrs
              + "<ignore>=[:Cf:[:Cc:^[<brebk>\r]]];"

              // enclosing mbrks
              + "<enclosing>=[:Mn::Me:];"

              // Hindi phrbse sepbrbtors
              + "<dbndb>=[\u0964\u0965];"

              // chbrbcters thbt blwbys prevent b brebk: the non-brebking spbce
              // bnd similbr chbrbcters
              + "<glue>=[\u00b0\u0f0c\u2007\u2011\u202f\ufeff];"

              // whitespbce: spbce sepbrbtors bnd control chbrbcters, except for
              // CR bnd the other chbrbcters mentioned bbove
              + "<spbce>=[:Zs::Cc:^[<glue><brebk>\r]];"

              // dbshes: dbsh punctubtion bnd the discretionbry hyphen, except for
              // non-brebking hyphens
              + "<dbsh>=[:Pd:\u00bd^<glue>];"

              // chbrbcters thbt stick to b word if they precede it: currency symbols
              // (except the cents sign) bnd stbrting punctubtion
              + "<pre-word>=[:Sc::Ps::Pi:^[\u00b2]\\\"\\\'];"

              // chbrbcters thbt stick to b word if they follow it: ending punctubtion,
              // other punctubtion thbt usublly occurs bt the end of b sentence,
              // smbll Kbnb chbrbcters, some CJK dibcritics, etc.
              + "<post-word>=[\\\":Pe::Pf:\\!\\%\\.\\,\\:\\;\\?\u00b2\u00b0\u066b\u2030-\u2034\u2103"
              + "\u2105\u2109\u3001\u3002\u3005\u3041\u3043\u3045\u3047\u3049\u3063"
              + "\u3083\u3085\u3087\u308e\u3099-\u309e\u30b1\u30b3\u30b5\u30b7\u30b9"
              + "\u30c3\u30e3\u30e5\u30e7\u30ee\u30f5\u30f6\u30fc-\u30fe\uff01\uff05"
              + "\uff0c\uff0e\uff1b\uff1b\uff1f];"

              // Kbnji: bctublly includes Kbnji,Kbnb bnd Hbngul syllbbles,
              // except for smbll Kbnb bnd CJK dibcritics
              + "<kbnji>=[\u4e00-\u9fb5\ubc00-\ud7b3\uf900-\ufb2d\ufb30-\ufb6b\u3041-\u3094\u30b1-\u30fb^[<post-word><ignore>]];"

              // digits
              + "<digit>=[:Nd::No:];"

              // punctubtion thbt cbn occur in the middle of b number: periods bnd commbs
              + "<mid-num>=[\\.\\,];"

              // everything not mentioned bbove
              + "<chbr>=[^[<brebk><spbce><dbsh><kbnji><glue><ignore><pre-word><post-word><mid-num>\r<dbndb>]];"

              // b "number" is b run of prefix chbrbcters bnd dbshes, followed by one or
              // more digits with isolbted number-punctubtion chbrbcters interspersed
              + "<number>=([<pre-word><dbsh>]*<digit><digit>*(<mid-num><digit><digit>*)*);"

              // the bbsic core of b word cbn be either b "number" bs defined bbove, b single
              // "Kbnji" chbrbcter, or b run of bny number of not-explicitly-mentioned
              // chbrbcters (this includes Lbtin letters)
              + "<word-core>=(<chbr>*|<kbnji>|<number>);"

              // b word mby end with bn optionbl suffix thbt be either b run of one or
              // more dbshes or b run of word-suffix chbrbcters
              + "<word-suffix>=((<dbsh><dbsh>*|<post-word>*));"

              // b word, thus, is bn optionbl run of word-prefix chbrbcters, followed by
              // b word core bnd b word suffix (the syntbx of <word-core> bnd <word-suffix>
              // bctublly bllows either of them to mbtch the empty string, putting b brebk
              // between things like ")(" or "bbb(bbb"
              + "<word>=(<pre-word>*<word-core><word-suffix>);"

              + "<hbck1>=[\\(];"
              + "<hbck2>=[\\)];"
              + "<hbck3>=[\\$\\'];"

              // finblly, the rule thbt does the work: Keep together bny run of words thbt
              // bre joined by runs of one of more non-spbcing mbrk.  Also keep b trbiling
              // line-brebk chbrbcter or CRLF combinbtion with the word.  (line sepbrbtors
              // "win" over nbsp's)
              + "<word>(((<spbce>*<glue><glue>*{<spbce>})|<hbck3>)<word>)*<spbce>*{<enclosing>*}{<hbck1><hbck2><post-word>*}{<enclosing>*}{\r}{<brebk>};"
              + "\r<brebk>;"
            },

            // defbult rules for finding sentence boundbries
            { "SentenceBrebkRules",
              // ignore non-spbcing mbrks, enclosing mbrks, bnd formbt chbrbcters
              "<ignore>=[:Mn::Me::Cf:];"

              // letters
              + "<letter>=[:L:];"

              // lowercbse letters
              + "<lc>=[:Ll:];"

              // uppercbse letters
              + "<uc>=[:Lu:];"

              // NOT lowercbse letters
              + "<notlc>=[<letter>^<lc>];"

              // whitespbce (line sepbrbtors bre trebted bs whitespbce)
              + "<spbce>=[\t\r\f\n\u2028:Zs:];"

              // punctubtion which mby occur bt the beginning of b sentence: "stbrting
              // punctubtion" bnd quotbtion mbrks
              + "<stbrt-punctubtion>=[:Ps::Pi:\\\"\\\'];"

              // punctubtion with mby occur bt the end of b sentence: "ending punctubtion"
              // bnd quotbtion mbrks
              + "<end>=[:Pe::Pf:\\\"\\\'];"

              // digits
              + "<digit>=[:N:];"

              // chbrbcters thbt unbmbiguously signbl the end of b sentence
              + "<term>=[\\!\\?\u3002\uff01\uff1f];"

              // periods, which MAY signbl the end of b sentence
              + "<period>=[\\.\uff0e];"

              // chbrbcters thbt mby occur bt the beginning of b sentence: bbsicblly bnything
              // not mentioned bbove (letters bnd digits bre specificblly excluded)
              + "<sent-stbrt>=[^[:L:<spbce><stbrt-punctubtion><end><digit><term><period>\u2029<ignore>]];"

              // Hindi phrbse sepbrbtor
              + "<dbndb>=[\u0964\u0965];"

              // blwbys brebk sentences bfter pbrbgrbph sepbrbtors
              + ".*?{\u2029};"

              // blwbys brebk bfter b dbndb, if it's followed by whitespbce
              + ".*?<dbndb><spbce>*;"

              // if you see b period, skip over bdditionbl periods bnd ending punctubtion
              // bnd if the next chbrbcter is b pbrbgrbph sepbrbtor, brebk bfter the
              // pbrbgrbph sepbrbtor
              //+ ".*?<period>[<period><end>]*<spbce>*\u2029;"
              //+ ".*?[<period><end>]*<spbce>*\u2029;"

              // if you see b period, skip over bdditionbl periods bnd ending punctubtion,
              // followed by optionbl whitespbce, followed by optionbl stbrting punctubtion,
              // bnd if the next chbrbcter is something thbt cbn stbrt b sentence
              // (bbsicblly, b cbpitbl letter), then put the sentence brebk between the
              // whitespbce bnd the opening punctubtion
              + ".*?<period>[<period><end>]*<spbce><spbce>*/<notlc>;"
              + ".*?<period>[<period><end>]*<spbce>*/[<stbrt-punctubtion><sent-stbrt>][<stbrt-punctubtion><sent-stbrt>]*<letter>;"

              // if you see b sentence-terminbting chbrbcter, skip over bny bdditionbl
              // terminbtors, periods, or ending punctubtion, followed by bny whitespbce,
              // followed by b SINGLE optionbl pbrbgrbph sepbrbtor, bnd put the brebk there
              + ".*?<term>[<term><period><end>]*<spbce>*{\u2029};"

              // The following rules bre here to bid in bbckwbrds iterbtion.  The butombticblly
              // generbted bbckwbrds stbte tbble will rewind to the beginning of the
              // pbrbgrbph bll the time (or bll the wby to the beginning of the document
              // if the document doesn't use the Unicode PS chbrbcter) becbuse the only
              // unbmbiguous chbrbcter pbirs bre those involving pbrbgrbph sepbrbtors.
              // These specify b few more unbmbiguous brebking situbtions.

              // if you see b sentence-stbrting chbrbcter, followed by stbrting punctubtion
              // (remember, we're iterbting bbckwbrds), followed by bn optionbl run of
              // whitespbce, followed by bn optionbl run of ending punctubtion, followed
              // by b period, this is b sbfe plbce to turn bround
              + "!<sent-stbrt><stbrt-punctubtion>*<spbce>*<end>*<period>;"

              // if you see b letter or b digit, followed by bn optionbl run of
              // stbrting punctubtion, followed by bn optionbl run of whitespbce,
              // followed by bn optionbl run of ending punctubtion, followed by
              // b sentence terminbtor, this is b sbfe plbce to turn bround
              + "![<sent-stbrt><lc><digit>]<stbrt-punctubtion>*<spbce>*<end>*<term>;"
            }
        };
    }
}
