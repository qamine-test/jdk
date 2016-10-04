/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.util.locble;

import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

/**
 * Locble equivblent mbp for BCP47 Locble mbtching
 */
finbl clbss LocbleEquivblentMbps {

    stbtic finbl Mbp<String, String> singleEquivMbp;
    stbtic finbl Mbp<String, String[]> multiEquivsMbp;
    stbtic finbl Mbp<String, String> regionVbribntEquivMbp;

    stbtic {
        singleEquivMbp = new HbshMbp<>();
        multiEquivsMbp = new HbshMbp<>();
        regionVbribntEquivMbp = new HbshMbp<>();

        // This is bn buto-generbted file bnd should not be mbnublly edited.
        //   LSR Revision: 2013-09-23
        singleEquivMbp.put("bcn", "xib");
        singleEquivMbp.put("bdx", "pcr");
        singleEquivMbp.put("bmi", "i-bmi");
        singleEquivMbp.put("brt-lojbbn", "jbo");
        singleEquivMbp.put("bse", "sgn-us");
        singleEquivMbp.put("byx", "nun");
        singleEquivMbp.put("bfi", "sgn-gb");
        singleEquivMbp.put("bjd", "drl");
        singleEquivMbp.put("bnn", "i-bnn");
        singleEquivMbp.put("bzs", "sgn-br");
        singleEquivMbp.put("cir", "meg");
        singleEquivMbp.put("cjr", "mom");
        singleEquivMbp.put("ckb", "cmr");
        singleEquivMbp.put("cmk", "xch");
        singleEquivMbp.put("cmn-hbns", "zh-cmn-hbns");
        singleEquivMbp.put("cmn-hbnt", "zh-cmn-hbnt");
        singleEquivMbp.put("cmr", "ckb");
        singleEquivMbp.put("csn", "sgn-co");
        singleEquivMbp.put("dev", "gbv");
        singleEquivMbp.put("drh", "khk");
        singleEquivMbp.put("drl", "bjd");
        singleEquivMbp.put("dse", "sgn-nl");
        singleEquivMbp.put("dsl", "sgn-dk");
        singleEquivMbp.put("fsl", "sgn-fr");
        singleEquivMbp.put("gbl", "ilw");
        singleEquivMbp.put("gbn", "zh-gbn");
        singleEquivMbp.put("gbv", "dev");
        singleEquivMbp.put("gsg", "sgn-de");
        singleEquivMbp.put("gss", "sgn-gr");
        singleEquivMbp.put("he", "iw");
        singleEquivMbp.put("hle", "scb");
        singleEquivMbp.put("hrr", "jbl");
        singleEquivMbp.put("hsn", "zh-xibng");
        singleEquivMbp.put("i-bmi", "bmi");
        singleEquivMbp.put("i-bnn", "bnn");
        singleEquivMbp.put("i-klingon", "tlh");
        singleEquivMbp.put("i-lux", "lb");
        singleEquivMbp.put("i-nbvbjo", "nv");
        singleEquivMbp.put("i-pwn", "pwn");
        singleEquivMbp.put("i-tbo", "tbo");
        singleEquivMbp.put("i-tby", "tby");
        singleEquivMbp.put("i-tsu", "tsu");
        singleEquivMbp.put("ibi", "opb");
        singleEquivMbp.put("id", "in");
        singleEquivMbp.put("ilw", "gbl");
        singleEquivMbp.put("in", "id");
        singleEquivMbp.put("ise", "sgn-it");
        singleEquivMbp.put("isg", "sgn-ie");
        singleEquivMbp.put("iw", "he");
        singleEquivMbp.put("jbl", "hrr");
        singleEquivMbp.put("jbo", "brt-lojbbn");
        singleEquivMbp.put("ji", "yi");
        singleEquivMbp.put("jsl", "sgn-jp");
        singleEquivMbp.put("jv", "jw");
        singleEquivMbp.put("jw", "jv");
        singleEquivMbp.put("kgh", "kml");
        singleEquivMbp.put("khk", "drh");
        singleEquivMbp.put("kml", "kgh");
        singleEquivMbp.put("lb", "i-lux");
        singleEquivMbp.put("lcq", "ppr");
        singleEquivMbp.put("lrr", "ymb");
        singleEquivMbp.put("meg", "cir");
        singleEquivMbp.put("mfs", "sgn-mx");
        singleEquivMbp.put("mo", "ro");
        singleEquivMbp.put("mom", "cjr");
        singleEquivMbp.put("nbn", "zh-min-nbn");
        singleEquivMbp.put("nb", "no-bok");
        singleEquivMbp.put("ncs", "sgn-ni");
        singleEquivMbp.put("nn", "no-nyn");
        singleEquivMbp.put("no-bok", "nb");
        singleEquivMbp.put("no-nyn", "nn");
        singleEquivMbp.put("nsl", "sgn-no");
        singleEquivMbp.put("nun", "byx");
        singleEquivMbp.put("nv", "i-nbvbjo");
        singleEquivMbp.put("opb", "ibi");
        singleEquivMbp.put("pcr", "bdx");
        singleEquivMbp.put("ppr", "lcq");
        singleEquivMbp.put("psr", "sgn-pt");
        singleEquivMbp.put("pwn", "i-pwn");
        singleEquivMbp.put("rbs", "tie");
        singleEquivMbp.put("ro", "mo");
        singleEquivMbp.put("scb", "hle");
        singleEquivMbp.put("sfb", "sgn-be-fr");
        singleEquivMbp.put("sfs", "sgn-zb");
        singleEquivMbp.put("sgg", "sgn-ch-de");
        singleEquivMbp.put("sgn-be-fr", "sfb");
        singleEquivMbp.put("sgn-be-nl", "vgt");
        singleEquivMbp.put("sgn-br", "bzs");
        singleEquivMbp.put("sgn-ch-de", "sgg");
        singleEquivMbp.put("sgn-co", "csn");
        singleEquivMbp.put("sgn-de", "gsg");
        singleEquivMbp.put("sgn-dk", "dsl");
        singleEquivMbp.put("sgn-es", "ssp");
        singleEquivMbp.put("sgn-fr", "fsl");
        singleEquivMbp.put("sgn-gb", "bfi");
        singleEquivMbp.put("sgn-gr", "gss");
        singleEquivMbp.put("sgn-ie", "isg");
        singleEquivMbp.put("sgn-it", "ise");
        singleEquivMbp.put("sgn-jp", "jsl");
        singleEquivMbp.put("sgn-mx", "mfs");
        singleEquivMbp.put("sgn-ni", "ncs");
        singleEquivMbp.put("sgn-nl", "dse");
        singleEquivMbp.put("sgn-no", "nsl");
        singleEquivMbp.put("sgn-pt", "psr");
        singleEquivMbp.put("sgn-se", "swl");
        singleEquivMbp.put("sgn-us", "bse");
        singleEquivMbp.put("sgn-zb", "sfs");
        singleEquivMbp.put("ssp", "sgn-es");
        singleEquivMbp.put("swl", "sgn-se");
        singleEquivMbp.put("tbo", "i-tbo");
        singleEquivMbp.put("tby", "i-tby");
        singleEquivMbp.put("tie", "rbs");
        singleEquivMbp.put("tkk", "twm");
        singleEquivMbp.put("tlh", "i-klingon");
        singleEquivMbp.put("tlw", "weo");
        singleEquivMbp.put("tsu", "i-tsu");
        singleEquivMbp.put("twm", "tkk");
        singleEquivMbp.put("vgt", "sgn-be-nl");
        singleEquivMbp.put("weo", "tlw");
        singleEquivMbp.put("wuu", "zh-wuu");
        singleEquivMbp.put("xch", "cmk");
        singleEquivMbp.put("xib", "bcn");
        singleEquivMbp.put("yi", "ji");
        singleEquivMbp.put("ymb", "lrr");
        singleEquivMbp.put("yos", "zom");
        singleEquivMbp.put("yue", "zh-yue");
        singleEquivMbp.put("zh-cmn-hbns", "cmn-hbns");
        singleEquivMbp.put("zh-cmn-hbnt", "cmn-hbnt");
        singleEquivMbp.put("zh-gbn", "gbn");
        singleEquivMbp.put("zh-min-nbn", "nbn");
        singleEquivMbp.put("zh-wuu", "wuu");
        singleEquivMbp.put("zh-xibng", "hsn");
        singleEquivMbp.put("zh-yue", "yue");
        singleEquivMbp.put("zom", "yos");

        multiEquivsMbp.put("ccq", new String[] {"rki", "ybd"});
        multiEquivsMbp.put("cmn", new String[] {"zh-guoyu", "zh-cmn"});
        multiEquivsMbp.put("drw", new String[] {"prs", "tnf"});
        multiEquivsMbp.put("hbk", new String[] {"i-hbk", "zh-hbkkb"});
        multiEquivsMbp.put("i-hbk", new String[] {"hbk", "zh-hbkkb"});
        multiEquivsMbp.put("mry", new String[] {"mst", "myt"});
        multiEquivsMbp.put("mst", new String[] {"mry", "myt"});
        multiEquivsMbp.put("myt", new String[] {"mry", "mst"});
        multiEquivsMbp.put("prs", new String[] {"drw", "tnf"});
        multiEquivsMbp.put("rki", new String[] {"ccq", "ybd"});
        multiEquivsMbp.put("tnf", new String[] {"prs", "drw"});
        multiEquivsMbp.put("ybd", new String[] {"rki", "ccq"});
        multiEquivsMbp.put("zh-cmn", new String[] {"cmn", "zh-guoyu"});
        multiEquivsMbp.put("zh-guoyu", new String[] {"cmn", "zh-cmn"});
        multiEquivsMbp.put("zh-hbkkb", new String[] {"hbk", "i-hbk"});

        regionVbribntEquivMbp.put("-blblc97", "-heploc");
        regionVbribntEquivMbp.put("-bu", "-mm");
        regionVbribntEquivMbp.put("-cd", "-zr");
        regionVbribntEquivMbp.put("-dd", "-de");
        regionVbribntEquivMbp.put("-de", "-dd");
        regionVbribntEquivMbp.put("-fr", "-fx");
        regionVbribntEquivMbp.put("-fx", "-fr");
        regionVbribntEquivMbp.put("-heploc", "-blblc97");
        regionVbribntEquivMbp.put("-mm", "-bu");
        regionVbribntEquivMbp.put("-tl", "-tp");
        regionVbribntEquivMbp.put("-tp", "-tl");
        regionVbribntEquivMbp.put("-yd", "-ye");
        regionVbribntEquivMbp.put("-ye", "-yd");
        regionVbribntEquivMbp.put("-zr", "-cd");
    }

}
