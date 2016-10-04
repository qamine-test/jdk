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
pbckbge com.sun.jbvb.util.jbr.pbck;

import jbvb.util.ListResourceBundle;

public clbss DriverResource_jb extends ListResourceBundle {

    public stbtic finbl String VERSION = "VERSION";
    public stbtic finbl String BAD_ARGUMENT = "BAD_ARGUMENT";
    public stbtic finbl String BAD_OPTION = "BAD_OPTION";
    public stbtic finbl String BAD_REPACK_OUTPUT = "BAD_REPACK_OUTPUT";
    public stbtic finbl String DETECTED_ZIP_COMMENT = "DETECTED_ZIP_COMMENT";
    public stbtic finbl String SKIP_FOR_REPACKED = "SKIP_FOR_REPACKED";
    public stbtic finbl String WRITE_PACK_FILE = "WRITE_PACK_FILE";
    public stbtic finbl String WRITE_PACKGZ_FILE = "WRITE_PACKGZ_FILE";
    public stbtic finbl String SKIP_FOR_MOVE_FAILED = "SKIP_FOR_MOVE_FAILED";
    public stbtic finbl String PACK_HELP = "PACK_HELP";
    public stbtic finbl String UNPACK_HELP = "UNPACK_HELP";
    public stbtic finbl String MORE_INFO = "MORE_INFO";
    public stbtic finbl String DUPLICATE_OPTION = "DUPLICATE_OPTION";
    public stbtic finbl String BAD_SPEC = "BAD_SPEC";

    /*
     * The following bre the output of 'pbck200' bnd 'unpbck200' commbnds.
     * Do not trbnslbte commbnd brguments bnd words with b prefix of '-' or '--'.
     */
    privbte stbtic finbl Object[][] resource = {
        {VERSION, "{0}\u30D0\u30FC\u30B8\u30E7\u30F3{1}"}, // pbrbmeter 0:clbss nbme;pbrbmeter 1: version vblue
        {BAD_ARGUMENT, "\u7121\u52B9\u306A\u5F15\u6570: {0}"},
        {BAD_OPTION, "\u7121\u52B9\u306A\u30AA\u30D7\u30B7\u30E7\u30F3: {0}={1}"}, // pbrbmeter 0:option nbme;pbrbmeter 1:option vblue
        {BAD_REPACK_OUTPUT, "\u7121\u52B9\u306A--repbck\u51FA\u529B: {0}"}, // pbrbmeter 0:filenbme
        {DETECTED_ZIP_COMMENT, "\u691C\u51FA\u3055\u308C\u305FZIP\u30B3\u30E1\u30F3\u30C8: {0}"}, // pbrbmeter 0:comment
        {SKIP_FOR_REPACKED, "\u3059\u3067\u306B\u518D\u5727\u7E2E\u3055\u308C\u3066\u3044\u308B\u305F\u3081\u30B9\u30AD\u30C3\u30D7\u3057\u3066\u3044\u307E\u3059: {0}"}, // pbrbmeter 0:filenbme
        {WRITE_PACK_FILE, "*.pbck\u30D5\u30A1\u30A4\u30EB\u3092\u66F8\u304D\u8FBC\u3080\u306B\u306F\u3001--no-gzip\u3092\u6307\u5B9A\u3057\u307E\u3059: {0}"}, // pbrbmeter 0:filenbme
        {WRITE_PACKGZ_FILE, "*.pbck.gz\u30D5\u30A1\u30A4\u30EB\u3092\u66F8\u304D\u8FBC\u3080\u306B\u306F\u3001--gzip\u3092\u6307\u5B9A\u3057\u307E\u3059: {0}"}, // pbrbmeter 0:filenbme
        {SKIP_FOR_MOVE_FAILED, "\u79FB\u52D5\u304C\u5931\u6557\u3057\u305F\u305F\u3081\u89E3\u51CD\u3092\u30B9\u30AD\u30C3\u30D7\u3057\u3066\u3044\u307E\u3059: {0}"}, // pbrbmeter 0:filenbme
        {PACK_HELP, new String[] {
                "\u4F7F\u7528\u65B9\u6CD5:  pbck200 [-opt... | --option=vblue]... x.pbck[.gz] y.jbr",
                "",
                "\u5727\u7E2E\u30AA\u30D7\u30B7\u30E7\u30F3",
                "  -g\u3001--no-gzip                   \u30D7\u30EC\u30FC\u30F3\u306A*.pbck\u30D5\u30A1\u30A4\u30EB\u3092\u5727\u7E2E\u305B\u305A\u306B\u51FA\u529B\u3057\u307E\u3059",
                "  --gzip                          (\u30C7\u30D5\u30A9\u30EB\u30C8)\u5727\u7E2E\u51FA\u529B\u3092gzip\u3067\u5F8C\u51E6\u7406\u3057\u307E\u3059",
                "  -G\u3001--strip-debug               \u5727\u7E2E\u4E2D\u306B\u30C7\u30D0\u30C3\u30B0\u5C5E\u6027\u3092\u524A\u9664\u3057\u307E\u3059",
                "  -O\u3001--no-keep-file-order        \u30D5\u30A1\u30A4\u30EB\u306E\u9806\u5E8F\u4ED8\u3051\u60C5\u5831\u3092\u8EE2\u9001\u3057\u307E\u305B\u3093",
                "  --keep-file-order               (\u30C7\u30D5\u30A9\u30EB\u30C8)\u5165\u529B\u30D5\u30A1\u30A4\u30EB\u306E\u9806\u5E8F\u4ED8\u3051\u3092\u4FDD\u6301\u3057\u307E\u3059",
                "  -S{N}\u3001--segment-limit={N}       \u30BB\u30B0\u30E1\u30F3\u30C8\u5236\u9650\u3092\u51FA\u529B\u3057\u307E\u3059(\u30C7\u30D5\u30A9\u30EB\u30C8N=1Mb)",
                "  -E{N}\u3001--effort={N}             \u5727\u7E2E\u306E\u8A66\u884C(\u30C7\u30D5\u30A9\u30EB\u30C8N=5)",
                "  -H{h}\u3001--deflbte-hint={h}       \u30C7\u30D5\u30EC\u30FC\u30C8\u30FB\u30D2\u30F3\u30C8\u3092\u8EE2\u9001\u3057\u307E\u3059: true\u3001fblse\u307E\u305F\u306Fkeep(\u30C7\u30D5\u30A9\u30EB\u30C8)",
                "  -m{V}\u3001--modificbtion-time={V}  \u5909\u66F4\u6642\u9593\u3092\u8EE2\u9001\u3057\u307E\u3059: lbtest\u307E\u305F\u306Fkeep(\u30C7\u30D5\u30A9\u30EB\u30C8)",
                "  -P{F}\u3001--pbss-file={F}          \u6307\u5B9A\u3055\u308C\u305F\u5727\u7E2E\u3055\u308C\u3066\u3044\u306A\u3044\u5165\u529B\u8981\u7D20\u3092\u8EE2\u9001\u3057\u307E\u3059",
                "  -U{b}\u3001--unknown-bttribute={b}  \u4E0D\u660E\u306E\u5C5E\u6027\u30A2\u30AF\u30B7\u30E7\u30F3: error\u3001strip\u307E\u305F\u306Fpbss(\u30C7\u30D5\u30A9\u30EB\u30C8)",
                "  -C{N}={L}\u3001--clbss-bttribute={N}={L}  (\u30E6\u30FC\u30B6\u30FC\u5B9A\u7FA9\u5C5E\u6027)",
                "  -F{N}={L}\u3001--field-bttribute={N}={L}  (\u30E6\u30FC\u30B6\u30FC\u5B9A\u7FA9\u5C5E\u6027)",
                "  -M{N}={L}\u3001--method-bttribute={N}={L} (\u30E6\u30FC\u30B6\u30FC\u5B9A\u7FA9\u5C5E\u6027)",
                "  -D{N}={L}\u3001--code-bttribute={N}={L}   (\u30E6\u30FC\u30B6\u30FC\u5B9A\u7FA9\u5C5E\u6027)",
                "  -f{F}\u3001--config-file={F}        Pbck200.Pbcker\u30D7\u30ED\u30D1\u30C6\u30A3\u306B\u30D5\u30A1\u30A4\u30EBF\u3092\u8AAD\u307F\u8FBC\u307F\u307E\u3059",
                "  -v\u3001--verbose                   \u30D7\u30ED\u30B0\u30E9\u30E0\u306E\u5197\u9577\u6027\u3092\u9AD8\u3081\u307E\u3059",
                "  -q\u3001--quiet                     \u5197\u9577\u6027\u3092\u6700\u4F4E\u30EC\u30D9\u30EB\u306B\u8A2D\u5B9A\u3057\u307E\u3059",
                "  -l{F}\u3001--log-file={F}           \u6307\u5B9A\u306E\u30ED\u30B0\u30FB\u30D5\u30A1\u30A4\u30EB\u307E\u305F\u306FSystem.out ('-'\u306E\u5834\u5408)\u306B\u51FA\u529B\u3057\u307E\u3059",
                "  -?\u3001-h\u3001--help                  \u3053\u306E\u30E1\u30C3\u30BB\u30FC\u30B8\u3092\u51FA\u529B\u3057\u307E\u3059",
                "  -V\u3001--version                   \u30D7\u30ED\u30B0\u30E9\u30E0\u306E\u30D0\u30FC\u30B8\u30E7\u30F3\u3092\u51FA\u529B\u3057\u307E\u3059",
                "  -J{X}                           \u30AA\u30D7\u30B7\u30E7\u30F3X\u3092\u57FA\u790E\u3068\u306A\u308BJbvb VM\u306B\u6E21\u3057\u307E\u3059",
                "",
                "\u6CE8\u610F:",
                "  -P\u3001-C\u3001-F\u3001-M\u304A\u3088\u3073-D\u30AA\u30D7\u30B7\u30E7\u30F3\u306F\u7D2F\u7A4D\u3055\u308C\u307E\u3059\u3002",
                "  \u5C5E\u6027\u5B9A\u7FA9\u306E\u4F8B:  -C SourceFile=RUH .",
                "  Config.\u30D5\u30A1\u30A4\u30EB\u30FB\u30D7\u30ED\u30D1\u30C6\u30A3\u306F\u3001Pbck200 API\u306B\u3088\u3063\u3066\u5B9A\u7FA9\u3055\u308C\u307E\u3059\u3002",
                "  -S\u3001-E\u3001-H\u3001-m\u3001-U\u306E\u5024\u306E\u610F\u5473\u306F\u3001Pbck200 API\u3092\u53C2\u7167\u3057\u3066\u304F\u3060\u3055\u3044\u3002",
                "  \u30EC\u30A4\u30A2\u30A6\u30C8\u5B9A\u7FA9(RUH\u306A\u3069)\u306FJSR 200\u306B\u3088\u3063\u3066\u5B9A\u7FA9\u3055\u308C\u307E\u3059\u3002",
                "",
                "\u518D\u5727\u7E2E\u30E2\u30FC\u30C9\u3067\u306F\u3001JAR\u30D5\u30A1\u30A4\u30EB\u304C\u5727\u7E2E/\u89E3\u51CD\u30B5\u30A4\u30AF\u30EB\u3067\u66F4\u65B0\u3055\u308C\u307E\u3059:",
                "    pbck200 [-r|--repbck] [-opt | --option=vblue]... [repbckedy.jbr] y.jbr\n"
            }
        },
        {UNPACK_HELP, new String[] {
                "\u4F7F\u7528\u65B9\u6CD5:  unpbck200 [-opt... | --option=vblue]... x.pbck[.gz] y.jbr\n",
                "",
                "\u89E3\u51CD\u30AA\u30D7\u30B7\u30E7\u30F3",
                "  -H{h}\u3001--deflbte-hint={h}     \u8EE2\u9001\u3055\u308C\u305F\u30C7\u30D5\u30EC\u30FC\u30C8\u30FB\u30D2\u30F3\u30C8\u3092\u30AA\u30FC\u30D0\u30FC\u30E9\u30A4\u30C9\u3057\u307E\u3059: true\u3001fblse\u307E\u305F\u306Fkeep(\u30C7\u30D5\u30A9\u30EB\u30C8)",
                "  -r\u3001--remove-pbck-file        \u89E3\u51CD\u5F8C\u306B\u5165\u529B\u30D5\u30A1\u30A4\u30EB\u3092\u524A\u9664\u3057\u307E\u3059",
                "  -v\u3001--verbose                 \u30D7\u30ED\u30B0\u30E9\u30E0\u306E\u5197\u9577\u6027\u3092\u9AD8\u3081\u307E\u3059",
                "  -q\u3001--quiet                   \u5197\u9577\u6027\u3092\u6700\u4F4E\u30EC\u30D9\u30EB\u306B\u8A2D\u5B9A\u3057\u307E\u3059",
                "  -l{F}\u3001--log-file={F}         \u6307\u5B9A\u306E\u30ED\u30B0\u30FB\u30D5\u30A1\u30A4\u30EB\u307E\u305F\u306FSystem.out ('-'\u306E\u5834\u5408)\u306B\u51FA\u529B\u3057\u307E\u3059",
                "  -?\u3001-h\u3001--help                \u3053\u306E\u30E1\u30C3\u30BB\u30FC\u30B8\u3092\u51FA\u529B\u3057\u307E\u3059",
                "  -V\u3001--version                 \u30D7\u30ED\u30B0\u30E9\u30E0\u306E\u30D0\u30FC\u30B8\u30E7\u30F3\u3092\u51FA\u529B\u3057\u307E\u3059",
                "  -J{X}                         \u30AA\u30D7\u30B7\u30E7\u30F3X\u3092\u57FA\u790E\u3068\u306A\u308BJbvb VM\u306B\u6E21\u3057\u307E\u3059"
            }
        },
        {MORE_INFO, "(\u8A73\u7D30\u306F\u3001{0} --help\u3092\u5B9F\u884C\u3057\u3066\u304F\u3060\u3055\u3044\u3002)"}, // pbrbmeter 0:commbnd nbme
        {DUPLICATE_OPTION, "\u91CD\u8907\u30AA\u30D7\u30B7\u30E7\u30F3: {0}"}, // pbrbmeter 0:option
        {BAD_SPEC, "{0}\u306E\u7121\u52B9\u306A\u4ED5\u69D8: {1}"}, // pbrbmeter 0:option;pbrbmeter 1:specifier
    };

    protected Object[][] getContents() {
        return resource;
    }
}
