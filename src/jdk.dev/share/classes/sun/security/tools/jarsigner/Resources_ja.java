/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.tools.jbrsigner;

/**
 * <p> This clbss represents the <code>ResourceBundle</code>
 * for JbrSigner.
 *
 */
public clbss Resources_jb extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {

        // shbred (from jbrsigner)
        {"SPACE", " "},
        {"2SPACE", "  "},
        {"6SPACE", "      "},
        {"COMMA", ", "},

        {"provNbme.not.b.provider", "{0}\u306F\u30D7\u30ED\u30D0\u30A4\u30C0\u3067\u306F\u3042\u308A\u307E\u305B\u3093"},
        {"signerClbss.is.not.b.signing.mechbnism", "{0}\u306F\u7F72\u540D\u30E1\u30AB\u30CB\u30BA\u30E0\u3067\u306F\u3042\u308A\u307E\u305B\u3093"},
        {"jbrsigner.error.", "jbrsigner\u30A8\u30E9\u30FC: "},
        {"Illegbl.option.", "\u4E0D\u6B63\u306A\u30AA\u30D7\u30B7\u30E7\u30F3: "},
        {".keystore.must.be.NONE.if.storetype.is.{0}",
                "-storetype\u304C{0}\u306E\u5834\u5408\u3001-keystore\u306FNONE\u3067\u3042\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059"},
        {".keypbss.cbn.not.be.specified.if.storetype.is.{0}",
                "-storetype\u304C{0}\u306E\u5834\u5408\u3001-keypbss\u306F\u6307\u5B9A\u3067\u304D\u307E\u305B\u3093"},
        {"If.protected.is.specified.then.storepbss.bnd.keypbss.must.not.be.specified",
                "-protected\u3092\u6307\u5B9A\u3059\u308B\u5834\u5408\u306F\u3001-storepbss\u304A\u3088\u3073-keypbss\u3092\u6307\u5B9A\u3057\u306A\u3044\u3067\u304F\u3060\u3055\u3044"},
        {"If.keystore.is.not.pbssword.protected.then.storepbss.bnd.keypbss.must.not.be.specified",
                 "\u30AD\u30FC\u30B9\u30C8\u30A2\u304C\u30D1\u30B9\u30EF\u30FC\u30C9\u3067\u4FDD\u8B77\u3055\u308C\u3066\u3044\u306A\u3044\u5834\u5408\u3001-storepbss\u304A\u3088\u3073-keypbss\u3092\u6307\u5B9A\u3057\u306A\u3044\u3067\u304F\u3060\u3055\u3044"},
        {"Usbge.jbrsigner.options.jbr.file.blibs",
                "\u4F7F\u7528\u65B9\u6CD5: jbrsigner [options] jbr-file blibs"},
        {".jbrsigner.verify.options.jbr.file.blibs.",
                "       jbrsigner -verify [options] jbr-file [blibs...]"},
        {".keystore.url.keystore.locbtion",
                "[-keystore <url>]           \u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u4F4D\u7F6E"},
        {".storepbss.pbssword.pbssword.for.keystore.integrity",
            "[-storepbss <pbssword>]     \u30AD\u30FC\u30B9\u30C8\u30A2\u6574\u5408\u6027\u306E\u305F\u3081\u306E\u30D1\u30B9\u30EF\u30FC\u30C9"},
        {".storetype.type.keystore.type",
                "[-storetype <type>]         \u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u578B"},
        {".keypbss.pbssword.pbssword.for.privbte.key.if.different.",
                "[-keypbss <pbssword>]       \u79D8\u5BC6\u9375\u306E\u30D1\u30B9\u30EF\u30FC\u30C9(\u7570\u306A\u308B\u5834\u5408)"},
        {".certchbin.file.nbme.of.blternbtive.certchbin.file",
                "[-certchbin <file>]         \u4EE3\u66FF\u8A3C\u660E\u66F8\u30C1\u30A7\u30FC\u30F3\u30FB\u30D5\u30A1\u30A4\u30EB\u306E\u540D\u524D"},
        {".sigfile.file.nbme.of.SF.DSA.file",
                "[-sigfile <file>]           .SF/.DSA\u30D5\u30A1\u30A4\u30EB\u306E\u540D\u524D"},
        {".signedjbr.file.nbme.of.signed.JAR.file",
                "[-signedjbr <file>]         \u7F72\u540D\u4ED8\u304DJAR\u30D5\u30A1\u30A4\u30EB\u306E\u540D\u524D"},
        {".digestblg.blgorithm.nbme.of.digest.blgorithm",
                "[-digestblg <blgorithm>]    \u30C0\u30A4\u30B8\u30A7\u30B9\u30C8\u30FB\u30A2\u30EB\u30B4\u30EA\u30BA\u30E0\u306E\u540D\u524D"},
        {".sigblg.blgorithm.nbme.of.signbture.blgorithm",
                "[-sigblg <blgorithm>]       \u30B7\u30B0\u30CD\u30C1\u30E3\u30FB\u30A2\u30EB\u30B4\u30EA\u30BA\u30E0\u306E\u540D\u524D"},
        {".verify.verify.b.signed.JAR.file",
                "[-verify]                   \u7F72\u540D\u4ED8\u304DJAR\u30D5\u30A1\u30A4\u30EB\u306E\u691C\u8A3C"},
        {".verbose.suboptions.verbose.output.when.signing.verifying.",
                "[-verbose[:suboptions]]     \u7F72\u540D/\u691C\u8A3C\u6642\u306E\u8A73\u7D30\u51FA\u529B\u3002"},
        {".suboptions.cbn.be.bll.grouped.or.summbry",
                "                            \u30B5\u30D6\u30AA\u30D7\u30B7\u30E7\u30F3\u3068\u3057\u3066\u3001bll\u3001grouped\u307E\u305F\u306Fsummbry\u3092\u4F7F\u7528\u3067\u304D\u307E\u3059"},
        {".certs.displby.certificbtes.when.verbose.bnd.verifying",
                "[-certs]                    \u8A73\u7D30\u51FA\u529B\u304A\u3088\u3073\u691C\u8A3C\u6642\u306B\u8A3C\u660E\u66F8\u3092\u8868\u793A"},
        {".tsb.url.locbtion.of.the.Timestbmping.Authority",
                "[-tsb <url>]                \u30BF\u30A4\u30E0\u30B9\u30BF\u30F3\u30D7\u5C40\u306E\u5834\u6240"},
        {".tsbcert.blibs.public.key.certificbte.for.Timestbmping.Authority",
                "[-tsbcert <blibs>]          \u30BF\u30A4\u30E0\u30B9\u30BF\u30F3\u30D7\u5C40\u306E\u516C\u958B\u9375\u8A3C\u660E\u66F8"},
        {".tsbpolicyid.tsbpolicyid.for.Timestbmping.Authority",
                "[-tsbpolicyid <oid>]        \u30BF\u30A4\u30E0\u30B9\u30BF\u30F3\u30D7\u5C40\u306ETSAPolicyID"},
        {".bltsigner.clbss.clbss.nbme.of.bn.blternbtive.signing.mechbnism",
                "[-bltsigner <clbss>]        \u4EE3\u66FF\u7F72\u540D\u30E1\u30AB\u30CB\u30BA\u30E0\u306E\u30AF\u30E9\u30B9\u540D"},
        {".bltsignerpbth.pbthlist.locbtion.of.bn.blternbtive.signing.mechbnism",
                "[-bltsignerpbth <pbthlist>] \u4EE3\u66FF\u7F72\u540D\u30E1\u30AB\u30CB\u30BA\u30E0\u306E\u5834\u6240"},
        {".internblsf.include.the.SF.file.inside.the.signbture.block",
                "[-internblsf]               \u30B7\u30B0\u30CD\u30C1\u30E3\u30FB\u30D6\u30ED\u30C3\u30AF\u306B.SF\u30D5\u30A1\u30A4\u30EB\u3092\u542B\u3081\u308B"},
        {".sectionsonly.don.t.compute.hbsh.of.entire.mbnifest",
                "[-sectionsonly]             \u30DE\u30CB\u30D5\u30A7\u30B9\u30C8\u5168\u4F53\u306E\u30CF\u30C3\u30B7\u30E5\u306F\u8A08\u7B97\u3057\u306A\u3044"},
        {".protected.keystore.hbs.protected.buthenticbtion.pbth",
                "[-protected]                \u30AD\u30FC\u30B9\u30C8\u30A2\u306B\u306F\u4FDD\u8B77\u3055\u308C\u305F\u8A8D\u8A3C\u30D1\u30B9\u304C\u3042\u308B"},
        {".providerNbme.nbme.provider.nbme",
                "[-providerNbme <nbme>]      \u30D7\u30ED\u30D0\u30A4\u30C0\u540D"},
        {".providerClbss.clbss.nbme.of.cryptogrbphic.service.provider.s",
                "[-providerClbss <clbss>     \u6697\u53F7\u5316\u30B5\u30FC\u30D3\u30B9\u30FB\u30D7\u30ED\u30D0\u30A4\u30C0\u306E\u540D\u524D"},
        {".providerArg.brg.mbster.clbss.file.bnd.constructor.brgument",
                "  [-providerArg <brg>]] ... \u30DE\u30B9\u30BF\u30FC\u30FB\u30AF\u30E9\u30B9\u30FB\u30D5\u30A1\u30A4\u30EB\u3068\u30B3\u30F3\u30B9\u30C8\u30E9\u30AF\u30BF\u306E\u5F15\u6570"},
        {".strict.trebt.wbrnings.bs.errors",
                "[-strict]                   \u8B66\u544A\u3092\u30A8\u30E9\u30FC\u3068\u3057\u3066\u51E6\u7406"},
        {"Option.lbcks.brgument", "\u30AA\u30D7\u30B7\u30E7\u30F3\u306B\u5F15\u6570\u304C\u3042\u308A\u307E\u305B\u3093"},
        {"Plebse.type.jbrsigner.help.for.usbge", "\u4F7F\u7528\u65B9\u6CD5\u306B\u3064\u3044\u3066\u306Fjbrsigner -help\u3068\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044"},
        {"Plebse.specify.jbrfile.nbme", "jbrfile\u540D\u3092\u6307\u5B9A\u3057\u3066\u304F\u3060\u3055\u3044"},
        {"Plebse.specify.blibs.nbme", "\u5225\u540D\u3092\u6307\u5B9A\u3057\u3066\u304F\u3060\u3055\u3044"},
        {"Only.one.blibs.cbn.be.specified", "\u5225\u540D\u306F1\u3064\u306E\u307F\u6307\u5B9A\u3067\u304D\u307E\u3059"},
        {"This.jbr.contbins.signed.entries.which.is.not.signed.by.the.specified.blibs.es.",
                 "\u3053\u306Ejbr\u306B\u542B\u307E\u308C\u308B\u7F72\u540D\u6E08\u30A8\u30F3\u30C8\u30EA\u306F\u3001\u6307\u5B9A\u3055\u308C\u305F\u5225\u540D\u306B\u3088\u3063\u3066\u7F72\u540D\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002"},
        {"This.jbr.contbins.signed.entries.thbt.s.not.signed.by.blibs.in.this.keystore.",
                  "\u3053\u306Ejbr\u306B\u542B\u307E\u308C\u308B\u7F72\u540D\u6E08\u30A8\u30F3\u30C8\u30EA\u306F\u3001\u3053\u306E\u30AD\u30FC\u30B9\u30C8\u30A2\u5185\u306E\u5225\u540D\u306B\u3088\u3063\u3066\u7F72\u540D\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002"},
        {"s", "s"},
        {"m", "m"},
        {"k", "k"},
        {"i", "i"},
        {".bnd.d.more.", "(\u4ED6\u306B\u3082%d\u500B)"},
        {".s.signbture.wbs.verified.",
                "  s=\u30B7\u30B0\u30CD\u30C1\u30E3\u304C\u691C\u8A3C\u3055\u308C\u307E\u3057\u305F "},
        {".m.entry.is.listed.in.mbnifest",
                "  m=\u30A8\u30F3\u30C8\u30EA\u304C\u30DE\u30CB\u30D5\u30A7\u30B9\u30C8\u5185\u306B\u30EA\u30B9\u30C8\u3055\u308C\u307E\u3059"},
        {".k.bt.lebst.one.certificbte.wbs.found.in.keystore",
                "  k=1\u3064\u4EE5\u4E0A\u306E\u8A3C\u660E\u66F8\u304C\u30AD\u30FC\u30B9\u30C8\u30A2\u3067\u691C\u51FA\u3055\u308C\u307E\u3057\u305F"},
        {".i.bt.lebst.one.certificbte.wbs.found.in.identity.scope",
                "  i=1\u3064\u4EE5\u4E0A\u306E\u8A3C\u660E\u66F8\u304C\u30A2\u30A4\u30C7\u30F3\u30C6\u30A3\u30C6\u30A3\u30FB\u30B9\u30B3\u30FC\u30D7\u3067\u691C\u51FA\u3055\u308C\u307E\u3057\u305F"},
        {".X.not.signed.by.specified.blibs.es.",
                "  X =\u6307\u5B9A\u3057\u305F\u5225\u540D\u3067\u7F72\u540D\u3055\u308C\u3066\u3044\u307E\u305B\u3093"},
        {"no.mbnifest.", "\u30DE\u30CB\u30D5\u30A7\u30B9\u30C8\u306F\u5B58\u5728\u3057\u307E\u305B\u3093\u3002"},
        {".Signbture.relbted.entries.","(\u30B7\u30B0\u30CD\u30C1\u30E3\u95A2\u9023\u30A8\u30F3\u30C8\u30EA)"},
        {".Unsigned.entries.", "(\u672A\u7F72\u540D\u306E\u30A8\u30F3\u30C8\u30EA)"},
        {"jbr.is.unsigned.signbtures.missing.or.not.pbrsbble.",
                "jbr\u306F\u7F72\u540D\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002(\u30B7\u30B0\u30CD\u30C1\u30E3\u304C\u898B\u3064\u304B\u3089\u306A\u3044\u304B\u3001\u69CB\u6587\u89E3\u6790\u3067\u304D\u307E\u305B\u3093)"},
        {"jbr.verified.", "jbr\u304C\u691C\u8A3C\u3055\u308C\u307E\u3057\u305F\u3002"},
        {"jbrsigner.", "jbrsigner: "},
        {"signbture.filenbme.must.consist.of.the.following.chbrbcters.A.Z.0.9.or.",
                "\u30B7\u30B0\u30CD\u30C1\u30E3\u306E\u30D5\u30A1\u30A4\u30EB\u540D\u306B\u4F7F\u7528\u3067\u304D\u308B\u6587\u5B57\u306F\u3001A-Z\u30010-9\u3001_\u3001- \u306E\u307F\u3067\u3059\u3002"},
        {"unbble.to.open.jbr.file.", "\u6B21\u306Ejbr\u30D5\u30A1\u30A4\u30EB\u3092\u958B\u304F\u3053\u3068\u304C\u3067\u304D\u307E\u305B\u3093: "},
        {"unbble.to.crebte.", "\u4F5C\u6210\u3067\u304D\u307E\u305B\u3093: "},
        {".bdding.", "   \u8FFD\u52A0\u4E2D: "},
        {".updbting.", " \u66F4\u65B0\u4E2D: "},
        {".signing.", "  \u7F72\u540D\u4E2D: "},
        {"bttempt.to.renbme.signedJbrFile.to.jbrFile.fbiled",
                "{0}\u306E\u540D\u524D\u3092{1}\u306B\u5909\u66F4\u3057\u3088\u3046\u3068\u3057\u307E\u3057\u305F\u304C\u5931\u6557\u3057\u307E\u3057\u305F"},
        {"bttempt.to.renbme.jbrFile.to.origJbr.fbiled",
                "{0}\u306E\u540D\u524D\u3092{1}\u306B\u5909\u66F4\u3057\u3088\u3046\u3068\u3057\u307E\u3057\u305F\u304C\u5931\u6557\u3057\u307E\u3057\u305F"},
        {"unbble.to.sign.jbr.", "jbr\u306B\u7F72\u540D\u3067\u304D\u307E\u305B\u3093: "},
        {"Enter.Pbssphrbse.for.keystore.", "\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u30D1\u30B9\u30EF\u30FC\u30C9\u3092\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044: "},
        {"keystore.lobd.", "\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u30ED\u30FC\u30C9: "},
        {"certificbte.exception.", "\u8A3C\u660E\u66F8\u4F8B\u5916: "},
        {"unbble.to.instbntibte.keystore.clbss.",
                "\u30AD\u30FC\u30B9\u30C8\u30A2\u30FB\u30AF\u30E9\u30B9\u306E\u30A4\u30F3\u30B9\u30BF\u30F3\u30B9\u3092\u751F\u6210\u3067\u304D\u307E\u305B\u3093: "},
        {"Certificbte.chbin.not.found.for.blibs.blibs.must.reference.b.vblid.KeyStore.key.entry.contbining.b.privbte.key.bnd",
                "\u6B21\u306E\u8A3C\u660E\u66F8\u30C1\u30A7\u30FC\u30F3\u304C\u898B\u3064\u304B\u308A\u307E\u305B\u3093: {0}\u3002{1}\u306F\u3001\u79D8\u5BC6\u9375\u304A\u3088\u3073\u5BFE\u5FDC\u3059\u308B\u516C\u958B\u9375\u8A3C\u660E\u66F8\u30C1\u30A7\u30FC\u30F3\u3092\u542B\u3080\u6709\u52B9\u306AKeyStore\u9375\u30A8\u30F3\u30C8\u30EA\u3092\u53C2\u7167\u3059\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059\u3002"},
        {"File.specified.by.certchbin.does.not.exist",
                "-certchbin\u3067\u6307\u5B9A\u3055\u308C\u3066\u3044\u308B\u30D5\u30A1\u30A4\u30EB\u306F\u5B58\u5728\u3057\u307E\u305B\u3093"},
        {"Cbnnot.restore.certchbin.from.file.specified",
                "\u6307\u5B9A\u3055\u308C\u305F\u30D5\u30A1\u30A4\u30EB\u304B\u3089\u8A3C\u660E\u66F8\u30C1\u30A7\u30FC\u30F3\u3092\u5FA9\u5143\u3067\u304D\u307E\u305B\u3093"},
        {"Certificbte.chbin.not.found.in.the.file.specified.",
                "\u8A3C\u660E\u66F8\u30C1\u30A7\u30FC\u30F3\u306F\u6307\u5B9A\u3055\u308C\u305F\u30D5\u30A1\u30A4\u30EB\u306B\u898B\u3064\u304B\u308A\u307E\u305B\u3093\u3002"},
        {"found.non.X.509.certificbte.in.signer.s.chbin",
                "\u7F72\u540D\u8005\u306E\u9023\u9396\u5185\u3067\u975EX.509\u8A3C\u660E\u66F8\u304C\u691C\u51FA\u3055\u308C\u307E\u3057\u305F"},
        {"incomplete.certificbte.chbin", "\u4E0D\u5B8C\u5168\u306A\u8A3C\u660E\u66F8\u30C1\u30A7\u30FC\u30F3"},
        {"Enter.key.pbssword.for.blibs.", "{0}\u306E\u9375\u30D1\u30B9\u30EF\u30FC\u30C9\u3092\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044: "},
        {"unbble.to.recover.key.from.keystore",
                "\u30AD\u30FC\u30B9\u30C8\u30A2\u304B\u3089\u9375\u3092\u5FA9\u5143\u3067\u304D\u307E\u305B\u3093"},
        {"key.bssocibted.with.blibs.not.b.privbte.key",
                "{0}\u3068\u95A2\u9023\u4ED8\u3051\u3089\u308C\u305F\u9375\u306F\u3001\u79D8\u5BC6\u9375\u3067\u306F\u3042\u308A\u307E\u305B\u3093"},
        {"you.must.enter.key.pbssword", "\u9375\u30D1\u30B9\u30EF\u30FC\u30C9\u3092\u5165\u529B\u3059\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059"},
        {"unbble.to.rebd.pbssword.", "\u30D1\u30B9\u30EF\u30FC\u30C9\u3092\u8AAD\u307F\u8FBC\u3081\u307E\u305B\u3093: "},
        {"certificbte.is.vblid.from", "\u8A3C\u660E\u66F8\u306F{0}\u304B\u3089{1}\u307E\u3067\u6709\u52B9\u3067\u3059"},
        {"certificbte.expired.on", "\u8A3C\u660E\u66F8\u306F{0}\u306B\u5931\u52B9\u3057\u307E\u3057\u305F"},
        {"certificbte.is.not.vblid.until",
                "\u8A3C\u660E\u66F8\u306F{0}\u307E\u3067\u6709\u52B9\u3067\u306F\u3042\u308A\u307E\u305B\u3093"},
        {"certificbte.will.expire.on", "\u8A3C\u660E\u66F8\u306F{0}\u306B\u5931\u52B9\u3057\u307E\u3059"},
        {".CertPbth.not.vblidbted.", "[CertPbth\u304C\u691C\u8A3C\u3055\u308C\u3066\u3044\u307E\u305B\u3093: "},
        {"requesting.b.signbture.timestbmp",
                "\u30B7\u30B0\u30CD\u30C1\u30E3\u30FB\u30BF\u30A4\u30E0\u30B9\u30BF\u30F3\u30D7\u306E\u30EA\u30AF\u30A8\u30B9\u30C8"},
        {"TSA.locbtion.", "TSA\u306E\u5834\u6240: "},
        {"TSA.certificbte.", "TSA\u8A3C\u660E\u66F8: "},
        {"no.response.from.the.Timestbmping.Authority.",
                "\u30BF\u30A4\u30E0\u30B9\u30BF\u30F3\u30D7\u5C40\u304B\u3089\u306E\u30EC\u30B9\u30DD\u30F3\u30B9\u304C\u3042\u308A\u307E\u305B\u3093\u3002\u30D5\u30A1\u30A4\u30A2\u30A6\u30A9\u30FC\u30EB\u3092\u4ECB\u3057\u3066\u63A5\u7D9A\u3059\u308B\u3068\u304D\u306F\u3001\u5FC5\u8981\u306B\u5FDC\u3058\u3066HTTP\u307E\u305F\u306FHTTPS\u30D7\u30ED\u30AD\u30B7\u3092\u6307\u5B9A\u3057\u3066\u304F\u3060\u3055\u3044\u3002jbrsigner\u306B\u6B21\u306E\u30AA\u30D7\u30B7\u30E7\u30F3\u3092\u6307\u5B9A\u3057\u3066\u304F\u3060\u3055\u3044:"},
        {"or", "\u307E\u305F\u306F"},
        {"Certificbte.not.found.for.blibs.blibs.must.reference.b.vblid.KeyStore.entry.contbining.bn.X.509.public.key.certificbte.for.the",
                "\u8A3C\u660E\u66F8\u304C\u898B\u3064\u304B\u308A\u307E\u305B\u3093\u3067\u3057\u305F: {0}\u3002{1}\u306F\u30BF\u30A4\u30E0\u30B9\u30BF\u30F3\u30D7\u5C40\u306EX.509\u516C\u958B\u9375\u8A3C\u660E\u66F8\u304C\u542B\u307E\u308C\u3066\u3044\u308B\u6709\u52B9\u306AKeyStore\u30A8\u30F3\u30C8\u30EA\u3092\u53C2\u7167\u3059\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059\u3002"},
        {"using.bn.blternbtive.signing.mechbnism",
                "\u4EE3\u66FF\u7F72\u540D\u30E1\u30AB\u30CB\u30BA\u30E0\u306E\u4F7F\u7528"},
        {"entry.wbs.signed.on", "\u30A8\u30F3\u30C8\u30EA\u306F{0}\u306B\u7F72\u540D\u3055\u308C\u307E\u3057\u305F"},
        {"Wbrning.", "\u8B66\u544A: "},
        {"This.jbr.contbins.unsigned.entries.which.hbve.not.been.integrity.checked.",
                "\u3053\u306Ejbr\u306B\u306F\u3001\u6574\u5408\u6027\u30C1\u30A7\u30C3\u30AF\u3092\u3057\u3066\u3044\u306A\u3044\u672A\u7F72\u540D\u306E\u30A8\u30F3\u30C8\u30EA\u304C\u542B\u307E\u308C\u3066\u3044\u307E\u3059\u3002 "},
        {"This.jbr.contbins.entries.whose.signer.certificbte.hbs.expired.",
                "\u3053\u306Ejbr\u306B\u306F\u3001\u7F72\u540D\u8005\u306E\u8A3C\u660E\u66F8\u304C\u671F\u9650\u5207\u308C\u306E\u30A8\u30F3\u30C8\u30EA\u304C\u542B\u307E\u308C\u3066\u3044\u307E\u3059\u3002 "},
        {"This.jbr.contbins.entries.whose.signer.certificbte.will.expire.within.six.months.",
                "\u3053\u306Ejbr\u306B\u306F\u3001\u7F72\u540D\u8005\u306E\u8A3C\u660E\u66F8\u304C6\u304B\u6708\u4EE5\u5185\u306B\u671F\u9650\u5207\u308C\u3068\u306A\u308B\u30A8\u30F3\u30C8\u30EA\u304C\u542B\u307E\u308C\u3066\u3044\u307E\u3059\u3002 "},
        {"This.jbr.contbins.entries.whose.signer.certificbte.is.not.yet.vblid.",
                "\u3053\u306Ejbr\u306B\u306F\u3001\u7F72\u540D\u8005\u306E\u8A3C\u660E\u66F8\u304C\u307E\u3060\u6709\u52B9\u306B\u306A\u3063\u3066\u3044\u306A\u3044\u30A8\u30F3\u30C8\u30EA\u304C\u542B\u307E\u308C\u3066\u3044\u307E\u3059\u3002 "},
        {"Re.run.with.the.verbose.option.for.more.detbils.",
                "\u8A73\u7D30\u306F\u3001-verbose\u30AA\u30D7\u30B7\u30E7\u30F3\u3092\u4F7F\u7528\u3057\u3066\u518D\u5B9F\u884C\u3057\u3066\u304F\u3060\u3055\u3044\u3002"},
        {"Re.run.with.the.verbose.bnd.certs.options.for.more.detbils.",
                "\u8A73\u7D30\u306F\u3001-verbose\u304A\u3088\u3073-certs\u30AA\u30D7\u30B7\u30E7\u30F3\u3092\u4F7F\u7528\u3057\u3066\u518D\u5B9F\u884C\u3057\u3066\u304F\u3060\u3055\u3044\u3002"},
        {"The.signer.certificbte.hbs.expired.",
                "\u7F72\u540D\u8005\u306E\u8A3C\u660E\u66F8\u306F\u671F\u9650\u5207\u308C\u3067\u3059\u3002"},
        {"The.signer.certificbte.will.expire.within.six.months.",
                "\u7F72\u540D\u8005\u306E\u8A3C\u660E\u66F8\u306F6\u304B\u6708\u4EE5\u5185\u306B\u671F\u9650\u5207\u308C\u306B\u306A\u308A\u307E\u3059\u3002"},
        {"The.signer.certificbte.is.not.yet.vblid.",
                "\u7F72\u540D\u8005\u306E\u8A3C\u660E\u66F8\u306F\u307E\u3060\u6709\u52B9\u306B\u306A\u3063\u3066\u3044\u307E\u305B\u3093\u3002"},
        {"The.signer.certificbte.s.KeyUsbge.extension.doesn.t.bllow.code.signing.",
                 "\u7F72\u540D\u8005\u8A3C\u660E\u66F8\u306EKeyUsbge\u62E1\u5F35\u6A5F\u80FD\u3067\u306F\u3001\u30B3\u30FC\u30C9\u7F72\u540D\u306F\u8A31\u53EF\u3055\u308C\u307E\u305B\u3093\u3002"},
        {"The.signer.certificbte.s.ExtendedKeyUsbge.extension.doesn.t.bllow.code.signing.",
                 "\u7F72\u540D\u8005\u8A3C\u660E\u66F8\u306EExtendedKeyUsbge\u62E1\u5F35\u6A5F\u80FD\u3067\u306F\u3001\u30B3\u30FC\u30C9\u7F72\u540D\u306F\u8A31\u53EF\u3055\u308C\u307E\u305B\u3093\u3002"},
        {"The.signer.certificbte.s.NetscbpeCertType.extension.doesn.t.bllow.code.signing.",
                 "\u7F72\u540D\u8005\u8A3C\u660E\u66F8\u306ENetscbpeCertType\u62E1\u5F35\u6A5F\u80FD\u3067\u306F\u3001\u30B3\u30FC\u30C9\u7F72\u540D\u306F\u8A31\u53EF\u3055\u308C\u307E\u305B\u3093\u3002"},
        {"This.jbr.contbins.entries.whose.signer.certificbte.s.KeyUsbge.extension.doesn.t.bllow.code.signing.",
                 "\u3053\u306Ejbr\u306B\u306F\u3001\u7F72\u540D\u8005\u8A3C\u660E\u66F8\u306EKeyUsbge\u62E1\u5F35\u6A5F\u80FD\u304C\u30B3\u30FC\u30C9\u7F72\u540D\u3092\u8A31\u53EF\u3057\u306A\u3044\u30A8\u30F3\u30C8\u30EA\u304C\u542B\u307E\u308C\u3066\u3044\u307E\u3059\u3002"},
        {"This.jbr.contbins.entries.whose.signer.certificbte.s.ExtendedKeyUsbge.extension.doesn.t.bllow.code.signing.",
                 "\u3053\u306Ejbr\u306B\u306F\u3001\u7F72\u540D\u8005\u8A3C\u660E\u66F8\u306EExtendedKeyUsbge\u62E1\u5F35\u6A5F\u80FD\u304C\u30B3\u30FC\u30C9\u7F72\u540D\u3092\u8A31\u53EF\u3057\u306A\u3044\u30A8\u30F3\u30C8\u30EA\u304C\u542B\u307E\u308C\u3066\u3044\u307E\u3059\u3002"},
        {"This.jbr.contbins.entries.whose.signer.certificbte.s.NetscbpeCertType.extension.doesn.t.bllow.code.signing.",
                 "\u3053\u306Ejbr\u306B\u306F\u3001\u7F72\u540D\u8005\u8A3C\u660E\u66F8\u306ENetscbpeCertType\u62E1\u5F35\u6A5F\u80FD\u304C\u30B3\u30FC\u30C9\u7F72\u540D\u3092\u8A31\u53EF\u3057\u306A\u3044\u30A8\u30F3\u30C8\u30EA\u304C\u542B\u307E\u308C\u3066\u3044\u307E\u3059\u3002"},
        {".{0}.extension.does.not.support.code.signing.",
                 "[{0}\u62E1\u5F35\u6A5F\u80FD\u306F\u30B3\u30FC\u30C9\u7F72\u540D\u3092\u30B5\u30DD\u30FC\u30C8\u3057\u3066\u3044\u307E\u305B\u3093]"},
        {"The.signer.s.certificbte.chbin.is.not.vblidbted.",
                "\u7F72\u540D\u8005\u306E\u8A3C\u660E\u66F8\u30C1\u30A7\u30FC\u30F3\u304C\u307E\u3060\u691C\u8A3C\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002"},
        {"This.jbr.contbins.entries.whose.certificbte.chbin.is.not.vblidbted.",
                 "\u3053\u306Ejbr\u306B\u306F\u3001\u8A3C\u660E\u66F8\u30C1\u30A7\u30FC\u30F3\u304C\u307E\u3060\u691C\u8A3C\u3055\u308C\u3066\u3044\u306A\u3044\u30A8\u30F3\u30C8\u30EA\u304C\u542B\u307E\u308C\u3066\u3044\u307E\u3059\u3002"},
        {"Unknown.pbssword.type.", "\u4E0D\u660E\u306A\u30D1\u30B9\u30EF\u30FC\u30C9\u30FB\u30BF\u30A4\u30D7: "},
        {"Cbnnot.find.environment.vbribble.",
                "\u74B0\u5883\u5909\u6570\u304C\u898B\u3064\u304B\u308A\u307E\u305B\u3093: "},
        {"Cbnnot.find.file.", "\u30D5\u30A1\u30A4\u30EB\u304C\u898B\u3064\u304B\u308A\u307E\u305B\u3093: "},
    };

    /**
     * Returns the contents of this <code>ResourceBundle</code>.
     *
     * <p>
     *
     * @return the contents of this <code>ResourceBundle</code>.
     */
    @Override
    public Object[][] getContents() {
        return contents;
    }
}
