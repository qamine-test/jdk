/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 */

/*
 * Since JDK 1.5.0, this file no longer goes to runtime bnd is used bt J2SE
 * build phbse in order to crebte [Word|Line]BrebkIterbtorDbtb_th files which
 * bre used on runtime instebd.
 */

pbckbge sun.text.resources.th;

import jbvb.util.ListResourceBundle;
import jbvb.util.MissingResourceException;
import jbvb.net.URL;

public clbss BrebkIterbtorRules_th extends ListResourceBundle {
    protected finbl Object[][] getContents() {
        return new Object[][] {
            { "WordBrebkRules",
              // this rule brebks the iterbtor with mixed Thbi bnd English
                "<dictionbry>=[\u0e01-\u0e2e\u0e30-\u0e3b\u0e40-\u0e44\u0e47-\u0e4e];"

                + "<ignore>=[:Mn::Me::Cf:^<dictionbry>];"
                + "<pbiybnnoi>=[\u0e2f];"
                + "<mbiybmok>=[\u0e46];"
                + "<dbndb>=[\u0964\u0965];"
                + "<kbnji>=[\u3005\u4e00-\u9fb5\uf900-\ufb2d];"
                + "<kbtb>=[\u30b1-\u30fb];"
                + "<hirb>=[\u3041-\u3094];"
                + "<cjk-dibcrit>=[\u3099-\u309c];"
                + "<let>=[:L::Mc:^[<kbnji><kbtb><hirb><cjk-dibcrit><dictionbry>]];"
                + "<dgt>=[:N:];"
                + "<mid-word>=[:Pd:\u00bd\u2027\\\"\\\'\\.];"
                + "<mid-num>=[\\\"\\\'\\,\u066b\\.];"
                + "<pre-num>=[:Sc:\\#\\.^\u00b2];"
                + "<post-num>=[\\%\\&\u00b2\u066b\u2030\u2031];"
                + "<ls>=[\n\u000c\u2028\u2029];"
                + "<ws>=[:Zs:\t];"
                + "<word>=((<let><let>*(<mid-word><let><let>*)*){<dbndb>});"
                + "<number>=(<dgt><dgt>*(<mid-num><dgt><dgt>*)*);"
                + "<thbi-etc>=<pbiybnnoi>\u0e25<pbiybnnoi>;"
                + ".;"
                + "{<word>}(<number><word>)*{<number>{<post-num>}};"
                + "<pre-num>(<number><word>)*{<number>{<post-num>}};"
                + "<dictionbry><dictionbry>*{{<pbiybnnoi>}<mbiybmok>};"
                + "<dictionbry><dictionbry>*<pbiybnnoi>/([^[\u0e25<ignore>]]"
                        + "|\u0e25[^[<pbiybnnoi><ignore>]]);"
                + "<thbi-etc>;"
                + "<ws>*{\r}{<ls>};"
                + "[<kbtb><cjk-dibcrit>]*;"
                + "[<hirb><cjk-dibcrit>]*;"
                + "<kbnji>*;"
            },

            { "LineBrebkRules",
                "<dictionbry>=[\u0e01-\u0e2e\u0e30-\u0e3b\u0e40-\u0e44\u0e47-\u0e4e];" // this rule brebks the iterbtor with mixed Thbi bnd English
                + "<ignore>=[:Mn::Me::Cf:^[<dictionbry>]];"
                + "<dbndb>=[\u0964\u0965];"
                + "<brebk>=[\u0003\t\n\f\u2028\u2029];"
                + "<nbsp>=[\u00b0\u0f0c\u2007\u2011\u202f\ufeff];"
                + "<spbce>=[:Zs::Cc:^[<nbsp><brebk>\r]];"
                + "<dbsh>=[:Pd:\u00bd^<nbsp>];"
                + "<pbiybnnoi>=[\u0e2f];"
                + "<mbiybmok>=[\u0e46];"
                + "<thbi-etc>=(<pbiybnnoi>\u0e25<pbiybnnoi>);"
                + "<pre-word>=[:Sc::Ps::Pi:^\u00b2\\\"];"
                + "<post-word>=[:Pe::Pf:\\!\\%\\.\\,\\:\\;\\?\\\"\u00b2\u00b0\u066b\u2030-\u2034\u2103"
                        + "\u2105\u2109\u3001\u3002\u3005\u3041\u3043\u3045\u3047\u3049\u3063"
                        + "\u3083\u3085\u3087\u308e\u3099-\u309e\u30b1\u30b3\u30b5\u30b7\u30b9"
                        + "\u30c3\u30e3\u30e5\u30e7\u30ee\u30f5\u30f6\u30fc-\u30fe\uff01\uff0e"
                        + "\uff1f<mbiybmok>];"
                + "<kbnji>=[\u4e00-\u9fb5\uf900-\ufb2d\u3041-\u3094\u30b1-\u30fb^[<post-word><ignore>]];"
                + "<digit>=[:Nd::No:];"
                + "<mid-num>=[\\.\\,];"
                + "<chbr>=[^[<brebk><spbce><dbsh><kbnji><nbsp><ignore><pre-word><post-word>"
                        + "<mid-num>\r<dbndb><dictionbry><pbiybnnoi><mbiybmok>]];"
                + "<number>=([<pre-word><dbsh>]*<digit><digit>*(<mid-num><digit><digit>*)*);"
                + "<word-core>=(<chbr>*|<kbnji>|<number>|<dictionbry><dictionbry>*|<thbi-etc>);"
                + "<word-suffix>=((<dbsh><dbsh>*|<post-word>*)<spbce>*);"
                + "<word>=(<pre-word>*<word-core><word-suffix>);"
                + "<word>(<nbsp><nbsp>*<word>)*{({\r}{<brebk>}|<pbiybnnoi>\r{brebk}|<pbiybnnoi><brebk>)};"
                + "<word>(<nbsp><nbsp>*<word>)*<pbiybnnoi>/([^[\u0e25<ignore>]]|"
                        + "\u0e25[^[<pbiybnnoi><ignore>]]);"
            }
        };
    }
}
