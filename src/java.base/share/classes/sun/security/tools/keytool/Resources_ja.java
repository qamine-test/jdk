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

pbckbge sun.security.tools.keytool;

/**
 * <p> This clbss represents the <code>ResourceBundle</code>
 * for the keytool.
 *
 */
public clbss Resources_jb extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {
        {"NEWLINE", "\n"},
        {"STAR",
                "*******************************************"},
        {"STARNN",
                "*******************************************\n\n"},

        // keytool: Help pbrt
        {".OPTION.", " [OPTION]..."},
        {"Options.", "\u30AA\u30D7\u30B7\u30E7\u30F3:"},
        {"Use.keytool.help.for.bll.bvbilbble.commbnds",
                 "\u4F7F\u7528\u53EF\u80FD\u306A\u3059\u3079\u3066\u306E\u30B3\u30DE\u30F3\u30C9\u306B\u3064\u3044\u3066\u306F\"keytool -help\"\u3092\u4F7F\u7528\u3057\u3066\u304F\u3060\u3055\u3044"},
        {"Key.bnd.Certificbte.Mbnbgement.Tool",
                 "\u30AD\u30FC\u304A\u3088\u3073\u8A3C\u660E\u66F8\u7BA1\u7406\u30C4\u30FC\u30EB"},
        {"Commbnds.", "\u30B3\u30DE\u30F3\u30C9:"},
        {"Use.keytool.commbnd.nbme.help.for.usbge.of.commbnd.nbme",
                "commbnd_nbme\u306E\u4F7F\u7528\u65B9\u6CD5\u306B\u3064\u3044\u3066\u306F\"keytool -commbnd_nbme -help\"\u3092\u4F7F\u7528\u3057\u3066\u304F\u3060\u3055\u3044"},
        // keytool: help: commbnds
        {"Generbtes.b.certificbte.request",
                "\u8A3C\u660E\u66F8\u30EA\u30AF\u30A8\u30B9\u30C8\u3092\u751F\u6210\u3057\u307E\u3059"}, //-certreq
        {"Chbnges.bn.entry.s.blibs",
                "\u30A8\u30F3\u30C8\u30EA\u306E\u5225\u540D\u3092\u5909\u66F4\u3057\u307E\u3059"}, //-chbngeblibs
        {"Deletes.bn.entry",
                "\u30A8\u30F3\u30C8\u30EA\u3092\u524A\u9664\u3057\u307E\u3059"}, //-delete
        {"Exports.certificbte",
                "\u8A3C\u660E\u66F8\u3092\u30A8\u30AF\u30B9\u30DD\u30FC\u30C8\u3057\u307E\u3059"}, //-exportcert
        {"Generbtes.b.key.pbir",
                "\u9375\u30DA\u30A2\u3092\u751F\u6210\u3057\u307E\u3059"}, //-genkeypbir
        {"Generbtes.b.secret.key",
                "\u79D8\u5BC6\u9375\u3092\u751F\u6210\u3057\u307E\u3059"}, //-genseckey
        {"Generbtes.certificbte.from.b.certificbte.request",
                "\u8A3C\u660E\u66F8\u30EA\u30AF\u30A8\u30B9\u30C8\u304B\u3089\u8A3C\u660E\u66F8\u3092\u751F\u6210\u3057\u307E\u3059"}, //-gencert
        {"Generbtes.CRL", "CRL\u3092\u751F\u6210\u3057\u307E\u3059"}, //-gencrl
        {"Generbted.keyAlgNbme.secret.key",
                "{0}\u79D8\u5BC6\u9375\u3092\u751F\u6210\u3057\u307E\u3057\u305F"}, //-genseckey
        {"Generbted.keysize.bit.keyAlgNbme.secret.key",
                "{0}\u30D3\u30C3\u30C8{1}\u79D8\u5BC6\u9375\u3092\u751F\u6210\u3057\u307E\u3057\u305F"}, //-genseckey
        {"Imports.entries.from.b.JDK.1.1.x.style.identity.dbtbbbse",
                "JDK 1.1.x-style\u30A2\u30A4\u30C7\u30F3\u30C6\u30A3\u30C6\u30A3\u30FB\u30C7\u30FC\u30BF\u30D9\u30FC\u30B9\u304B\u3089\u30A8\u30F3\u30C8\u30EA\u3092\u30A4\u30F3\u30DD\u30FC\u30C8\u3057\u307E\u3059"}, //-identitydb
        {"Imports.b.certificbte.or.b.certificbte.chbin",
                "\u8A3C\u660E\u66F8\u307E\u305F\u306F\u8A3C\u660E\u66F8\u30C1\u30A7\u30FC\u30F3\u3092\u30A4\u30F3\u30DD\u30FC\u30C8\u3057\u307E\u3059"}, //-importcert
        {"Imports.b.pbssword",
                "\u30D1\u30B9\u30EF\u30FC\u30C9\u3092\u30A4\u30F3\u30DD\u30FC\u30C8\u3057\u307E\u3059"}, //-importpbss
        {"Imports.one.or.bll.entries.from.bnother.keystore",
                "\u5225\u306E\u30AD\u30FC\u30B9\u30C8\u30A2\u304B\u30891\u3064\u307E\u305F\u306F\u3059\u3079\u3066\u306E\u30A8\u30F3\u30C8\u30EA\u3092\u30A4\u30F3\u30DD\u30FC\u30C8\u3057\u307E\u3059"}, //-importkeystore
        {"Clones.b.key.entry",
                "\u9375\u30A8\u30F3\u30C8\u30EA\u306E\u30AF\u30ED\u30FC\u30F3\u3092\u4F5C\u6210\u3057\u307E\u3059"}, //-keyclone
        {"Chbnges.the.key.pbssword.of.bn.entry",
                "\u30A8\u30F3\u30C8\u30EA\u306E\u9375\u30D1\u30B9\u30EF\u30FC\u30C9\u3092\u5909\u66F4\u3057\u307E\u3059"}, //-keypbsswd
        {"Lists.entries.in.b.keystore",
                "\u30AD\u30FC\u30B9\u30C8\u30A2\u5185\u306E\u30A8\u30F3\u30C8\u30EA\u3092\u30EA\u30B9\u30C8\u3057\u307E\u3059"}, //-list
        {"Prints.the.content.of.b.certificbte",
                "\u8A3C\u660E\u66F8\u306E\u5185\u5BB9\u3092\u51FA\u529B\u3057\u307E\u3059"}, //-printcert
        {"Prints.the.content.of.b.certificbte.request",
                "\u8A3C\u660E\u66F8\u30EA\u30AF\u30A8\u30B9\u30C8\u306E\u5185\u5BB9\u3092\u51FA\u529B\u3057\u307E\u3059"}, //-printcertreq
        {"Prints.the.content.of.b.CRL.file",
                "CRL\u30D5\u30A1\u30A4\u30EB\u306E\u5185\u5BB9\u3092\u51FA\u529B\u3057\u307E\u3059"}, //-printcrl
        {"Generbtes.b.self.signed.certificbte",
                "\u81EA\u5DF1\u7F72\u540D\u578B\u8A3C\u660E\u66F8\u3092\u751F\u6210\u3057\u307E\u3059"}, //-selfcert
        {"Chbnges.the.store.pbssword.of.b.keystore",
                "\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u30B9\u30C8\u30A2\u30FB\u30D1\u30B9\u30EF\u30FC\u30C9\u3092\u5909\u66F4\u3057\u307E\u3059"}, //-storepbsswd
        // keytool: help: options
        {"blibs.nbme.of.the.entry.to.process",
                "\u51E6\u7406\u3059\u308B\u30A8\u30F3\u30C8\u30EA\u306E\u5225\u540D"}, //-blibs
        {"destinbtion.blibs",
                "\u51FA\u529B\u5148\u306E\u5225\u540D"}, //-destblibs
        {"destinbtion.key.pbssword",
                "\u51FA\u529B\u5148\u30AD\u30FC\u306E\u30D1\u30B9\u30EF\u30FC\u30C9"}, //-destkeypbss
        {"destinbtion.keystore.nbme",
                "\u51FA\u529B\u5148\u30AD\u30FC\u30B9\u30C8\u30A2\u540D"}, //-destkeystore
        {"destinbtion.keystore.pbssword.protected",
                "\u51FA\u529B\u5148\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u4FDD\u8B77\u5BFE\u8C61\u30D1\u30B9\u30EF\u30FC\u30C9"}, //-destprotected
        {"destinbtion.keystore.provider.nbme",
                "\u51FA\u529B\u5148\u30AD\u30FC\u30B9\u30C8\u30A2\u30FB\u30D7\u30ED\u30D0\u30A4\u30C0\u540D"}, //-destprovidernbme
        {"destinbtion.keystore.pbssword",
                "\u51FA\u529B\u5148\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u30D1\u30B9\u30EF\u30FC\u30C9"}, //-deststorepbss
        {"destinbtion.keystore.type",
                "\u51FA\u529B\u5148\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u30BF\u30A4\u30D7"}, //-deststoretype
        {"distinguished.nbme",
                "\u8B58\u5225\u540D"}, //-dnbme
        {"X.509.extension",
                "X.509\u62E1\u5F35"}, //-ext
        {"output.file.nbme",
                "\u51FA\u529B\u30D5\u30A1\u30A4\u30EB\u540D"}, //-file bnd -outfile
        {"input.file.nbme",
                "\u5165\u529B\u30D5\u30A1\u30A4\u30EB\u540D"}, //-file bnd -infile
        {"key.blgorithm.nbme",
                "\u9375\u30A2\u30EB\u30B4\u30EA\u30BA\u30E0\u540D"}, //-keyblg
        {"key.pbssword",
                "\u9375\u306E\u30D1\u30B9\u30EF\u30FC\u30C9"}, //-keypbss
        {"key.bit.size",
                "\u9375\u306E\u30D3\u30C3\u30C8\u30FB\u30B5\u30A4\u30BA"}, //-keysize
        {"keystore.nbme",
                "\u30AD\u30FC\u30B9\u30C8\u30A2\u540D"}, //-keystore
        {"new.pbssword",
                "\u65B0\u898F\u30D1\u30B9\u30EF\u30FC\u30C9"}, //-new
        {"do.not.prompt",
                "\u30D7\u30ED\u30F3\u30D7\u30C8\u3092\u8868\u793A\u3057\u306A\u3044"}, //-noprompt
        {"pbssword.through.protected.mechbnism",
                "\u4FDD\u8B77\u30E1\u30AB\u30CB\u30BA\u30E0\u306B\u3088\u308B\u30D1\u30B9\u30EF\u30FC\u30C9"}, //-protected
        {"provider.brgument",
                "\u30D7\u30ED\u30D0\u30A4\u30C0\u5F15\u6570"}, //-providerbrg
        {"provider.clbss.nbme",
                "\u30D7\u30ED\u30D0\u30A4\u30C0\u30FB\u30AF\u30E9\u30B9\u540D"}, //-providerclbss
        {"provider.nbme",
                "\u30D7\u30ED\u30D0\u30A4\u30C0\u540D"}, //-providernbme
        {"provider.clbsspbth",
                "\u30D7\u30ED\u30D0\u30A4\u30C0\u30FB\u30AF\u30E9\u30B9\u30D1\u30B9"}, //-providerpbth
        {"output.in.RFC.style",
                "RFC\u30B9\u30BF\u30A4\u30EB\u306E\u51FA\u529B"}, //-rfc
        {"signbture.blgorithm.nbme",
                "\u7F72\u540D\u30A2\u30EB\u30B4\u30EA\u30BA\u30E0\u540D"}, //-sigblg
        {"source.blibs",
                "\u30BD\u30FC\u30B9\u5225\u540D"}, //-srcblibs
        {"source.key.pbssword",
                "\u30BD\u30FC\u30B9\u30FB\u30AD\u30FC\u306E\u30D1\u30B9\u30EF\u30FC\u30C9"}, //-srckeypbss
        {"source.keystore.nbme",
                "\u30BD\u30FC\u30B9\u30FB\u30AD\u30FC\u30B9\u30C8\u30A2\u540D"}, //-srckeystore
        {"source.keystore.pbssword.protected",
                "\u30BD\u30FC\u30B9\u30FB\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u4FDD\u8B77\u5BFE\u8C61\u30D1\u30B9\u30EF\u30FC\u30C9"}, //-srcprotected
        {"source.keystore.provider.nbme",
                "\u30BD\u30FC\u30B9\u30FB\u30AD\u30FC\u30B9\u30C8\u30A2\u30FB\u30D7\u30ED\u30D0\u30A4\u30C0\u540D"}, //-srcprovidernbme
        {"source.keystore.pbssword",
                "\u30BD\u30FC\u30B9\u30FB\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u30D1\u30B9\u30EF\u30FC\u30C9"}, //-srcstorepbss
        {"source.keystore.type",
                "\u30BD\u30FC\u30B9\u30FB\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u30BF\u30A4\u30D7"}, //-srcstoretype
        {"SSL.server.host.bnd.port",
                "SSL\u30B5\u30FC\u30D0\u30FC\u306E\u30DB\u30B9\u30C8\u3068\u30DD\u30FC\u30C8"}, //-sslserver
        {"signed.jbr.file",
                "\u7F72\u540D\u4ED8\u304DJAR\u30D5\u30A1\u30A4\u30EB"}, //=jbrfile
        {"certificbte.vblidity.stbrt.dbte.time",
                "\u8A3C\u660E\u66F8\u306E\u6709\u52B9\u958B\u59CB\u65E5\u6642"}, //-stbrtdbte
        {"keystore.pbssword",
                "\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u30D1\u30B9\u30EF\u30FC\u30C9"}, //-storepbss
        {"keystore.type",
                "\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u30BF\u30A4\u30D7"}, //-storetype
        {"trust.certificbtes.from.cbcerts",
                "cbcerts\u304B\u3089\u306E\u8A3C\u660E\u66F8\u3092\u4FE1\u983C\u3059\u308B"}, //-trustcbcerts
        {"verbose.output",
                "\u8A73\u7D30\u51FA\u529B"}, //-v
        {"vblidity.number.of.dbys",
                "\u59A5\u5F53\u6027\u65E5\u6570"}, //-vblidity
        {"Seribl.ID.of.cert.to.revoke",
                 "\u5931\u52B9\u3059\u308B\u8A3C\u660E\u66F8\u306E\u30B7\u30EA\u30A2\u30EBID"}, //-id
        // keytool: Running pbrt
        {"keytool.error.", "keytool\u30A8\u30E9\u30FC: "},
        {"Illegbl.option.", "\u4E0D\u6B63\u306A\u30AA\u30D7\u30B7\u30E7\u30F3:  "},
        {"Illegbl.vblue.", "\u4E0D\u6B63\u306A\u5024: "},
        {"Unknown.pbssword.type.", "\u4E0D\u660E\u306A\u30D1\u30B9\u30EF\u30FC\u30C9\u30FB\u30BF\u30A4\u30D7: "},
        {"Cbnnot.find.environment.vbribble.",
                "\u74B0\u5883\u5909\u6570\u304C\u898B\u3064\u304B\u308A\u307E\u305B\u3093: "},
        {"Cbnnot.find.file.", "\u30D5\u30A1\u30A4\u30EB\u304C\u898B\u3064\u304B\u308A\u307E\u305B\u3093: "},
        {"Commbnd.option.flbg.needs.bn.brgument.", "\u30B3\u30DE\u30F3\u30C9\u30FB\u30AA\u30D7\u30B7\u30E7\u30F3{0}\u306B\u306F\u5F15\u6570\u304C\u5FC5\u8981\u3067\u3059\u3002"},
        {"Wbrning.Different.store.bnd.key.pbsswords.not.supported.for.PKCS12.KeyStores.Ignoring.user.specified.commbnd.vblue.",
                "\u8B66\u544A: PKCS12\u30AD\u30FC\u30B9\u30C8\u30A2\u3067\u306F\u3001\u30B9\u30C8\u30A2\u306E\u30D1\u30B9\u30EF\u30FC\u30C9\u3068\u9375\u306E\u30D1\u30B9\u30EF\u30FC\u30C9\u304C\u7570\u306A\u308B\u72B6\u6CC1\u306F\u30B5\u30DD\u30FC\u30C8\u3055\u308C\u307E\u305B\u3093\u3002\u30E6\u30FC\u30B6\u30FC\u304C\u6307\u5B9A\u3057\u305F{0}\u306E\u5024\u306F\u7121\u8996\u3057\u307E\u3059\u3002"},
        {".keystore.must.be.NONE.if.storetype.is.{0}",
                "-storetype\u304C{0}\u306E\u5834\u5408\u3001-keystore\u306FNONE\u3067\u3042\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059"},
        {"Too.mbny.retries.progrbm.terminbted",
                 "\u518D\u8A66\u884C\u304C\u591A\u3059\u304E\u307E\u3059\u3002\u30D7\u30ED\u30B0\u30E9\u30E0\u304C\u7D42\u4E86\u3057\u307E\u3057\u305F"},
        {".storepbsswd.bnd.keypbsswd.commbnds.not.supported.if.storetype.is.{0}",
                "-storetype\u304C{0}\u306E\u5834\u5408\u3001-storepbsswd\u30B3\u30DE\u30F3\u30C9\u304A\u3088\u3073-keypbsswd\u30B3\u30DE\u30F3\u30C9\u306F\u30B5\u30DD\u30FC\u30C8\u3055\u308C\u307E\u305B\u3093"},
        {".keypbsswd.commbnds.not.supported.if.storetype.is.PKCS12",
                "-storetype\u304CPKCS12\u306E\u5834\u5408\u3001-keypbsswd\u30B3\u30DE\u30F3\u30C9\u306F\u30B5\u30DD\u30FC\u30C8\u3055\u308C\u307E\u305B\u3093"},
        {".keypbss.bnd.new.cbn.not.be.specified.if.storetype.is.{0}",
                "-storetype\u304C{0}\u306E\u5834\u5408\u3001-keypbss\u3068-new\u306F\u6307\u5B9A\u3067\u304D\u307E\u305B\u3093"},
        {"if.protected.is.specified.then.storepbss.keypbss.bnd.new.must.not.be.specified",
                "-protected\u304C\u6307\u5B9A\u3055\u308C\u3066\u3044\u308B\u5834\u5408\u3001-storepbss\u3001-keypbss\u304A\u3088\u3073-new\u306F\u6307\u5B9A\u3067\u304D\u307E\u305B\u3093"},
        {"if.srcprotected.is.specified.then.srcstorepbss.bnd.srckeypbss.must.not.be.specified",
                "-srcprotected\u304C\u6307\u5B9A\u3055\u308C\u3066\u3044\u308B\u5834\u5408\u3001-srcstorepbss\u304A\u3088\u3073-srckeypbss\u306F\u6307\u5B9A\u3067\u304D\u307E\u305B\u3093"},
        {"if.keystore.is.not.pbssword.protected.then.storepbss.keypbss.bnd.new.must.not.be.specified",
                "\u30AD\u30FC\u30B9\u30C8\u30A2\u304C\u30D1\u30B9\u30EF\u30FC\u30C9\u3067\u4FDD\u8B77\u3055\u308C\u3066\u3044\u306A\u3044\u5834\u5408\u3001-storepbss\u3001-keypbss\u304A\u3088\u3073-new\u306F\u6307\u5B9A\u3067\u304D\u307E\u305B\u3093"},
        {"if.source.keystore.is.not.pbssword.protected.then.srcstorepbss.bnd.srckeypbss.must.not.be.specified",
                "\u30BD\u30FC\u30B9\u30FB\u30AD\u30FC\u30B9\u30C8\u30A2\u304C\u30D1\u30B9\u30EF\u30FC\u30C9\u3067\u4FDD\u8B77\u3055\u308C\u3066\u3044\u306A\u3044\u5834\u5408\u3001-srcstorepbss\u304A\u3088\u3073-srckeypbss\u306F\u6307\u5B9A\u3067\u304D\u307E\u305B\u3093"},
        {"Illegbl.stbrtdbte.vblue", "stbrtdbte\u5024\u304C\u7121\u52B9\u3067\u3059"},
        {"Vblidity.must.be.grebter.thbn.zero",
                "\u59A5\u5F53\u6027\u306F\u30BC\u30ED\u3088\u308A\u5927\u304D\u3044\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059"},
        {"provNbme.not.b.provider", "{0}\u306F\u30D7\u30ED\u30D0\u30A4\u30C0\u3067\u306F\u3042\u308A\u307E\u305B\u3093"},
        {"Usbge.error.no.commbnd.provided", "\u4F7F\u7528\u30A8\u30E9\u30FC: \u30B3\u30DE\u30F3\u30C9\u304C\u6307\u5B9A\u3055\u308C\u3066\u3044\u307E\u305B\u3093"},
        {"Source.keystore.file.exists.but.is.empty.", "\u30BD\u30FC\u30B9\u30FB\u30AD\u30FC\u30B9\u30C8\u30A2\u30FB\u30D5\u30A1\u30A4\u30EB\u306F\u3001\u5B58\u5728\u3057\u307E\u3059\u304C\u7A7A\u3067\u3059: "},
        {"Plebse.specify.srckeystore", "-srckeystore\u3092\u6307\u5B9A\u3057\u3066\u304F\u3060\u3055\u3044"},
        {"Must.not.specify.both.v.bnd.rfc.with.list.commbnd",
                "'list'\u30B3\u30DE\u30F3\u30C9\u306B-v\u3068-rfc\u306E\u4E21\u65B9\u3092\u6307\u5B9A\u3059\u308B\u3053\u3068\u306F\u3067\u304D\u307E\u305B\u3093"},
        {"Key.pbssword.must.be.bt.lebst.6.chbrbcters",
                "\u9375\u306E\u30D1\u30B9\u30EF\u30FC\u30C9\u306F6\u6587\u5B57\u4EE5\u4E0A\u3067\u3042\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059"},
        {"New.pbssword.must.be.bt.lebst.6.chbrbcters",
                "\u65B0\u898F\u30D1\u30B9\u30EF\u30FC\u30C9\u306F6\u6587\u5B57\u4EE5\u4E0A\u3067\u3042\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059"},
        {"Keystore.file.exists.but.is.empty.",
                "\u30AD\u30FC\u30B9\u30C8\u30A2\u30FB\u30D5\u30A1\u30A4\u30EB\u306F\u5B58\u5728\u3057\u307E\u3059\u304C\u3001\u7A7A\u3067\u3059: "},
        {"Keystore.file.does.not.exist.",
                "\u30AD\u30FC\u30B9\u30C8\u30A2\u30FB\u30D5\u30A1\u30A4\u30EB\u306F\u5B58\u5728\u3057\u307E\u305B\u3093: "},
        {"Must.specify.destinbtion.blibs", "\u51FA\u529B\u5148\u306E\u5225\u540D\u3092\u6307\u5B9A\u3059\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059"},
        {"Must.specify.blibs", "\u5225\u540D\u3092\u6307\u5B9A\u3059\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059"},
        {"Keystore.pbssword.must.be.bt.lebst.6.chbrbcters",
                "\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u30D1\u30B9\u30EF\u30FC\u30C9\u306F6\u6587\u5B57\u4EE5\u4E0A\u3067\u3042\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059"},
        {"Enter.the.pbssword.to.be.stored.",
                "\u4FDD\u5B58\u3059\u308B\u30D1\u30B9\u30EF\u30FC\u30C9\u3092\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044:  "},
        {"Enter.keystore.pbssword.", "\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u30D1\u30B9\u30EF\u30FC\u30C9\u3092\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044:  "},
        {"Enter.source.keystore.pbssword.", "\u30BD\u30FC\u30B9\u30FB\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u30D1\u30B9\u30EF\u30FC\u30C9\u3092\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044:  "},
        {"Enter.destinbtion.keystore.pbssword.", "\u51FA\u529B\u5148\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u30D1\u30B9\u30EF\u30FC\u30C9\u3092\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044:  "},
        {"Keystore.pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
         "\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u30D1\u30B9\u30EF\u30FC\u30C9\u304C\u77ED\u3059\u304E\u307E\u3059 - 6\u6587\u5B57\u4EE5\u4E0A\u306B\u3057\u3066\u304F\u3060\u3055\u3044"},
        {"Unknown.Entry.Type", "\u4E0D\u660E\u306A\u30A8\u30F3\u30C8\u30EA\u30FB\u30BF\u30A4\u30D7"},
        {"Too.mbny.fbilures.Alibs.not.chbnged", "\u969C\u5BB3\u304C\u591A\u3059\u304E\u307E\u3059\u3002\u5225\u540D\u306F\u5909\u66F4\u3055\u308C\u307E\u305B\u3093"},
        {"Entry.for.blibs.blibs.successfully.imported.",
                 "\u5225\u540D{0}\u306E\u30A8\u30F3\u30C8\u30EA\u306E\u30A4\u30F3\u30DD\u30FC\u30C8\u306B\u6210\u529F\u3057\u307E\u3057\u305F\u3002"},
        {"Entry.for.blibs.blibs.not.imported.", "\u5225\u540D{0}\u306E\u30A8\u30F3\u30C8\u30EA\u306F\u30A4\u30F3\u30DD\u30FC\u30C8\u3055\u308C\u307E\u305B\u3093\u3067\u3057\u305F\u3002"},
        {"Problem.importing.entry.for.blibs.blibs.exception.Entry.for.blibs.blibs.not.imported.",
                 "\u5225\u540D{0}\u306E\u30A8\u30F3\u30C8\u30EA\u306E\u30A4\u30F3\u30DD\u30FC\u30C8\u4E2D\u306B\u554F\u984C\u304C\u767A\u751F\u3057\u307E\u3057\u305F: {1}\u3002\n\u5225\u540D{0}\u306E\u30A8\u30F3\u30C8\u30EA\u306F\u30A4\u30F3\u30DD\u30FC\u30C8\u3055\u308C\u307E\u305B\u3093\u3067\u3057\u305F\u3002"},
        {"Import.commbnd.completed.ok.entries.successfully.imported.fbil.entries.fbiled.or.cbncelled",
                 "\u30A4\u30F3\u30DD\u30FC\u30C8\u30FB\u30B3\u30DE\u30F3\u30C9\u304C\u5B8C\u4E86\u3057\u307E\u3057\u305F: {0}\u4EF6\u306E\u30A8\u30F3\u30C8\u30EA\u306E\u30A4\u30F3\u30DD\u30FC\u30C8\u304C\u6210\u529F\u3057\u307E\u3057\u305F\u3002{1}\u4EF6\u306E\u30A8\u30F3\u30C8\u30EA\u306E\u30A4\u30F3\u30DD\u30FC\u30C8\u304C\u5931\u6557\u3057\u305F\u304B\u53D6\u308A\u6D88\u3055\u308C\u307E\u3057\u305F"},
        {"Wbrning.Overwriting.existing.blibs.blibs.in.destinbtion.keystore",
                 "\u8B66\u544A: \u51FA\u529B\u5148\u30AD\u30FC\u30B9\u30C8\u30A2\u5185\u306E\u65E2\u5B58\u306E\u5225\u540D{0}\u3092\u4E0A\u66F8\u304D\u3057\u3066\u3044\u307E\u3059"},
        {"Existing.entry.blibs.blibs.exists.overwrite.no.",
                 "\u65E2\u5B58\u306E\u30A8\u30F3\u30C8\u30EA\u306E\u5225\u540D{0}\u304C\u5B58\u5728\u3057\u3066\u3044\u307E\u3059\u3002\u4E0A\u66F8\u304D\u3057\u307E\u3059\u304B\u3002[\u3044\u3044\u3048]:  "},
        {"Too.mbny.fbilures.try.lbter", "\u969C\u5BB3\u304C\u591A\u3059\u304E\u307E\u3059 - \u5F8C\u3067\u5B9F\u884C\u3057\u3066\u304F\u3060\u3055\u3044"},
        {"Certificbtion.request.stored.in.file.filenbme.",
                "\u8A3C\u660E\u66F8\u30EA\u30AF\u30A8\u30B9\u30C8\u304C\u30D5\u30A1\u30A4\u30EB<{0}>\u306B\u4FDD\u5B58\u3055\u308C\u307E\u3057\u305F"},
        {"Submit.this.to.your.CA", "\u3053\u308C\u3092CA\u306B\u63D0\u51FA\u3057\u3066\u304F\u3060\u3055\u3044"},
        {"if.blibs.not.specified.destblibs.bnd.srckeypbss.must.not.be.specified",
            "\u5225\u540D\u3092\u6307\u5B9A\u3057\u306A\u3044\u5834\u5408\u3001\u51FA\u529B\u5148\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u5225\u540D\u304A\u3088\u3073\u30BD\u30FC\u30B9\u30FB\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u30D1\u30B9\u30EF\u30FC\u30C9\u306F\u6307\u5B9A\u3067\u304D\u307E\u305B\u3093"},
        {"The.destinbtion.pkcs12.keystore.hbs.different.storepbss.bnd.keypbss.Plebse.retry.with.destkeypbss.specified.",
            "\u51FA\u529B\u5148pkcs12\u30AD\u30FC\u30B9\u30C8\u30A2\u306B\u3001\u7570\u306A\u308Bstorepbss\u304A\u3088\u3073keypbss\u304C\u3042\u308A\u307E\u3059\u3002-destkeypbss\u3092\u6307\u5B9A\u3057\u3066\u518D\u8A66\u884C\u3057\u3066\u304F\u3060\u3055\u3044\u3002"},
        {"Certificbte.stored.in.file.filenbme.",
                "\u8A3C\u660E\u66F8\u304C\u30D5\u30A1\u30A4\u30EB<{0}>\u306B\u4FDD\u5B58\u3055\u308C\u307E\u3057\u305F"},
        {"Certificbte.reply.wbs.instblled.in.keystore",
                "\u8A3C\u660E\u66F8\u5FDC\u7B54\u304C\u30AD\u30FC\u30B9\u30C8\u30A2\u306B\u30A4\u30F3\u30B9\u30C8\u30FC\u30EB\u3055\u308C\u307E\u3057\u305F"},
        {"Certificbte.reply.wbs.not.instblled.in.keystore",
                "\u8A3C\u660E\u66F8\u5FDC\u7B54\u304C\u30AD\u30FC\u30B9\u30C8\u30A2\u306B\u30A4\u30F3\u30B9\u30C8\u30FC\u30EB\u3055\u308C\u307E\u305B\u3093\u3067\u3057\u305F"},
        {"Certificbte.wbs.bdded.to.keystore",
                "\u8A3C\u660E\u66F8\u304C\u30AD\u30FC\u30B9\u30C8\u30A2\u306B\u8FFD\u52A0\u3055\u308C\u307E\u3057\u305F"},
        {"Certificbte.wbs.not.bdded.to.keystore",
                "\u8A3C\u660E\u66F8\u304C\u30AD\u30FC\u30B9\u30C8\u30A2\u306B\u8FFD\u52A0\u3055\u308C\u307E\u305B\u3093\u3067\u3057\u305F"},
        {".Storing.ksfnbme.", "[{0}\u3092\u683C\u7D0D\u4E2D]"},
        {"blibs.hbs.no.public.key.certificbte.",
                "{0}\u306B\u306F\u516C\u958B\u9375(\u8A3C\u660E\u66F8)\u304C\u3042\u308A\u307E\u305B\u3093"},
        {"Cbnnot.derive.signbture.blgorithm",
                "\u7F72\u540D\u30A2\u30EB\u30B4\u30EA\u30BA\u30E0\u3092\u53D6\u5F97\u3067\u304D\u307E\u305B\u3093"},
        {"Alibs.blibs.does.not.exist",
                "\u5225\u540D<{0}>\u306F\u5B58\u5728\u3057\u307E\u305B\u3093"},
        {"Alibs.blibs.hbs.no.certificbte",
                "\u5225\u540D<{0}>\u306B\u306F\u8A3C\u660E\u66F8\u304C\u3042\u308A\u307E\u305B\u3093"},
        {"Key.pbir.not.generbted.blibs.blibs.blrebdy.exists",
                "\u9375\u30DA\u30A2\u306F\u751F\u6210\u3055\u308C\u307E\u305B\u3093\u3067\u3057\u305F\u3002\u5225\u540D<{0}>\u306F\u3059\u3067\u306B\u5B58\u5728\u3057\u307E\u3059"},
        {"Generbting.keysize.bit.keyAlgNbme.key.pbir.bnd.self.signed.certificbte.sigAlgNbme.with.b.vblidity.of.vblidblity.dbys.for",
                "{3}\u65E5\u9593\u6709\u52B9\u306A{0}\u30D3\u30C3\u30C8\u306E{1}\u306E\u9375\u30DA\u30A2\u3068\u81EA\u5DF1\u7F72\u540D\u578B\u8A3C\u660E\u66F8({2})\u3092\u751F\u6210\u3057\u3066\u3044\u307E\u3059\n\t\u30C7\u30A3\u30EC\u30AF\u30C8\u30EA\u540D: {4}"},
        {"Enter.key.pbssword.for.blibs.", "<{0}>\u306E\u9375\u30D1\u30B9\u30EF\u30FC\u30C9\u3092\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044"},
        {".RETURN.if.sbme.bs.keystore.pbssword.",
                "\t(\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u30D1\u30B9\u30EF\u30FC\u30C9\u3068\u540C\u3058\u5834\u5408\u306FRETURN\u3092\u62BC\u3057\u3066\u304F\u3060\u3055\u3044):  "},
        {"Key.pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
                "\u9375\u306E\u30D1\u30B9\u30EF\u30FC\u30C9\u304C\u77ED\u3059\u304E\u307E\u3059 - 6\u6587\u5B57\u4EE5\u4E0A\u3092\u6307\u5B9A\u3057\u3066\u304F\u3060\u3055\u3044"},
        {"Too.mbny.fbilures.key.not.bdded.to.keystore",
                "\u969C\u5BB3\u304C\u591A\u3059\u304E\u307E\u3059 - \u9375\u306F\u30AD\u30FC\u30B9\u30C8\u30A2\u306B\u8FFD\u52A0\u3055\u308C\u307E\u305B\u3093\u3067\u3057\u305F"},
        {"Destinbtion.blibs.dest.blrebdy.exists",
                "\u51FA\u529B\u5148\u306E\u5225\u540D<{0}>\u306F\u3059\u3067\u306B\u5B58\u5728\u3057\u307E\u3059"},
        {"Pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
                "\u30D1\u30B9\u30EF\u30FC\u30C9\u304C\u77ED\u3059\u304E\u307E\u3059 - 6\u6587\u5B57\u4EE5\u4E0A\u3092\u6307\u5B9A\u3057\u3066\u304F\u3060\u3055\u3044"},
        {"Too.mbny.fbilures.Key.entry.not.cloned",
                "\u969C\u5BB3\u304C\u591A\u3059\u304E\u307E\u3059\u3002\u9375\u30A8\u30F3\u30C8\u30EA\u306E\u30AF\u30ED\u30FC\u30F3\u306F\u4F5C\u6210\u3055\u308C\u307E\u305B\u3093\u3067\u3057\u305F"},
        {"key.pbssword.for.blibs.", "<{0}>\u306E\u9375\u306E\u30D1\u30B9\u30EF\u30FC\u30C9"},
        {"Keystore.entry.for.id.getNbme.blrebdy.exists",
                "<{0}>\u306E\u30AD\u30FC\u30B9\u30C8\u30A2\u30FB\u30A8\u30F3\u30C8\u30EA\u306F\u3059\u3067\u306B\u5B58\u5728\u3057\u307E\u3059"},
        {"Crebting.keystore.entry.for.id.getNbme.",
                "<{0}>\u306E\u30AD\u30FC\u30B9\u30C8\u30A2\u30FB\u30A8\u30F3\u30C8\u30EA\u3092\u4F5C\u6210\u4E2D..."},
        {"No.entries.from.identity.dbtbbbse.bdded",
                "\u30A2\u30A4\u30C7\u30F3\u30C6\u30A3\u30C6\u30A3\u30FB\u30C7\u30FC\u30BF\u30D9\u30FC\u30B9\u304B\u3089\u8FFD\u52A0\u3055\u308C\u305F\u30A8\u30F3\u30C8\u30EA\u306F\u3042\u308A\u307E\u305B\u3093"},
        {"Alibs.nbme.blibs", "\u5225\u540D: {0}"},
        {"Crebtion.dbte.keyStore.getCrebtionDbte.blibs.",
                "\u4F5C\u6210\u65E5: {0,dbte}"},
        {"blibs.keyStore.getCrebtionDbte.blibs.",
                "{0},{1,dbte}, "},
        {"blibs.", "{0}, "},
        {"Entry.type.type.", "\u30A8\u30F3\u30C8\u30EA\u30FB\u30BF\u30A4\u30D7: {0}"},
        {"Certificbte.chbin.length.", "\u8A3C\u660E\u66F8\u30C1\u30A7\u30FC\u30F3\u306E\u9577\u3055: "},
        {"Certificbte.i.1.", "\u8A3C\u660E\u66F8[{0,number,integer}]:"},
        {"Certificbte.fingerprint.SHA1.", "\u8A3C\u660E\u66F8\u306E\u30D5\u30A3\u30F3\u30AC\u30D7\u30EA\u30F3\u30C8(SHA1): "},
        {"Keystore.type.", "\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u30BF\u30A4\u30D7: "},
        {"Keystore.provider.", "\u30AD\u30FC\u30B9\u30C8\u30A2\u30FB\u30D7\u30ED\u30D0\u30A4\u30C0: "},
        {"Your.keystore.contbins.keyStore.size.entry",
                "\u30AD\u30FC\u30B9\u30C8\u30A2\u306B\u306F{0,number,integer}\u30A8\u30F3\u30C8\u30EA\u304C\u542B\u307E\u308C\u307E\u3059"},
        {"Your.keystore.contbins.keyStore.size.entries",
                "\u30AD\u30FC\u30B9\u30C8\u30A2\u306B\u306F{0,number,integer}\u30A8\u30F3\u30C8\u30EA\u304C\u542B\u307E\u308C\u307E\u3059"},
        {"Fbiled.to.pbrse.input", "\u5165\u529B\u306E\u69CB\u6587\u89E3\u6790\u306B\u5931\u6557\u3057\u307E\u3057\u305F"},
        {"Empty.input", "\u5165\u529B\u304C\u3042\u308A\u307E\u305B\u3093"},
        {"Not.X.509.certificbte", "X.509\u8A3C\u660E\u66F8\u3067\u306F\u3042\u308A\u307E\u305B\u3093"},
        {"blibs.hbs.no.public.key", "{0}\u306B\u306F\u516C\u958B\u9375\u304C\u3042\u308A\u307E\u305B\u3093"},
        {"blibs.hbs.no.X.509.certificbte", "{0}\u306B\u306FX.509\u8A3C\u660E\u66F8\u304C\u3042\u308A\u307E\u305B\u3093"},
        {"New.certificbte.self.signed.", "\u65B0\u3057\u3044\u8A3C\u660E\u66F8(\u81EA\u5DF1\u7F72\u540D\u578B):"},
        {"Reply.hbs.no.certificbtes", "\u5FDC\u7B54\u306B\u306F\u8A3C\u660E\u66F8\u304C\u3042\u308A\u307E\u305B\u3093"},
        {"Certificbte.not.imported.blibs.blibs.blrebdy.exists",
                "\u8A3C\u660E\u66F8\u306F\u30A4\u30F3\u30DD\u30FC\u30C8\u3055\u308C\u307E\u305B\u3093\u3067\u3057\u305F\u3002\u5225\u540D<{0}>\u306F\u3059\u3067\u306B\u5B58\u5728\u3057\u307E\u3059"},
        {"Input.not.bn.X.509.certificbte", "\u5165\u529B\u306FX.509\u8A3C\u660E\u66F8\u3067\u306F\u3042\u308A\u307E\u305B\u3093"},
        {"Certificbte.blrebdy.exists.in.keystore.under.blibs.trustblibs.",
                "\u8A3C\u660E\u66F8\u306F\u3001\u5225\u540D<{0}>\u306E\u30AD\u30FC\u30B9\u30C8\u30A2\u306B\u3059\u3067\u306B\u5B58\u5728\u3057\u307E\u3059"},
        {"Do.you.still.wbnt.to.bdd.it.no.",
                "\u8FFD\u52A0\u3057\u307E\u3059\u304B\u3002[\u3044\u3044\u3048]:  "},
        {"Certificbte.blrebdy.exists.in.system.wide.CA.keystore.under.blibs.trustblibs.",
                "\u8A3C\u660E\u66F8\u306F\u3001\u5225\u540D<{0}>\u306E\u30B7\u30B9\u30C6\u30E0\u898F\u6A21\u306ECA\u30AD\u30FC\u30B9\u30C8\u30A2\u5185\u306B\u3059\u3067\u306B\u5B58\u5728\u3057\u307E\u3059"},
        {"Do.you.still.wbnt.to.bdd.it.to.your.own.keystore.no.",
                "\u30AD\u30FC\u30B9\u30C8\u30A2\u306B\u8FFD\u52A0\u3057\u307E\u3059\u304B\u3002 [\u3044\u3044\u3048]:  "},
        {"Trust.this.certificbte.no.", "\u3053\u306E\u8A3C\u660E\u66F8\u3092\u4FE1\u983C\u3057\u307E\u3059\u304B\u3002 [\u3044\u3044\u3048]:  "},
        {"YES", "\u306F\u3044"},
        {"New.prompt.", "\u65B0\u898F{0}: "},
        {"Pbsswords.must.differ", "\u30D1\u30B9\u30EF\u30FC\u30C9\u306F\u7570\u306A\u3063\u3066\u3044\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059"},
        {"Re.enter.new.prompt.", "\u65B0\u898F{0}\u3092\u518D\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044: "},
        {"Re.enter.pbsspword.", "\u30D1\u30B9\u30EF\u30FC\u30C9\u3092\u518D\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044: "},
        {"Re.enter.new.pbssword.", "\u65B0\u898F\u30D1\u30B9\u30EF\u30FC\u30C9\u3092\u518D\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044: "},
        {"They.don.t.mbtch.Try.bgbin", "\u4E00\u81F4\u3057\u307E\u305B\u3093\u3002\u3082\u3046\u4E00\u5EA6\u5B9F\u884C\u3057\u3066\u304F\u3060\u3055\u3044"},
        {"Enter.prompt.blibs.nbme.", "{0}\u306E\u5225\u540D\u3092\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044:  "},
        {"Enter.new.blibs.nbme.RETURN.to.cbncel.import.for.this.entry.",
                 "\u65B0\u3057\u3044\u5225\u540D\u3092\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044\t(\u3053\u306E\u30A8\u30F3\u30C8\u30EA\u306E\u30A4\u30F3\u30DD\u30FC\u30C8\u3092\u53D6\u308A\u6D88\u3059\u5834\u5408\u306FRETURN\u3092\u62BC\u3057\u3066\u304F\u3060\u3055\u3044):  "},
        {"Enter.blibs.nbme.", "\u5225\u540D\u3092\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044:  "},
        {".RETURN.if.sbme.bs.for.otherAlibs.",
                "\t(<{0}>\u3068\u540C\u3058\u5834\u5408\u306FRETURN\u3092\u62BC\u3057\u3066\u304F\u3060\u3055\u3044)"},
        {".PATTERN.printX509Cert",
                "\u6240\u6709\u8005: {0}\n\u767A\u884C\u8005: {1}\n\u30B7\u30EA\u30A2\u30EB\u756A\u53F7: {2}\n\u6709\u52B9\u671F\u9593\u306E\u958B\u59CB\u65E5: {3}\u7D42\u4E86\u65E5: {4}\n\u8A3C\u660E\u66F8\u306E\u30D5\u30A3\u30F3\u30AC\u30D7\u30EA\u30F3\u30C8:\n\t MD5:  {5}\n\t SHA1: {6}\n\t SHA256: {7}\n\t \u7F72\u540D\u30A2\u30EB\u30B4\u30EA\u30BA\u30E0\u540D: {8}\n\t \u30D0\u30FC\u30B8\u30E7\u30F3: {9}"},
        {"Whbt.is.your.first.bnd.lbst.nbme.",
                "\u59D3\u540D\u3092\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044\u3002"},
        {"Whbt.is.the.nbme.of.your.orgbnizbtionbl.unit.",
                "\u7D44\u7E54\u5358\u4F4D\u540D\u3092\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044\u3002"},
        {"Whbt.is.the.nbme.of.your.orgbnizbtion.",
                "\u7D44\u7E54\u540D\u3092\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044\u3002"},
        {"Whbt.is.the.nbme.of.your.City.or.Locblity.",
                "\u90FD\u5E02\u540D\u307E\u305F\u306F\u5730\u57DF\u540D\u3092\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044\u3002"},
        {"Whbt.is.the.nbme.of.your.Stbte.or.Province.",
                "\u90FD\u9053\u5E9C\u770C\u540D\u3092\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044\u3002"},
        {"Whbt.is.the.two.letter.country.code.for.this.unit.",
                "\u3053\u306E\u5358\u4F4D\u306B\u8A72\u5F53\u3059\u308B2\u6587\u5B57\u306E\u56FD\u30B3\u30FC\u30C9\u3092\u5165\u529B\u3057\u3066\u304F\u3060\u3055\u3044\u3002"},
        {"Is.nbme.correct.", "{0}\u3067\u3088\u308D\u3057\u3044\u3067\u3059\u304B\u3002"},
        {"no", "\u3044\u3044\u3048"},
        {"yes", "\u306F\u3044"},
        {"y", "y"},
        {".defbultVblue.", "  [{0}]:  "},
        {"Alibs.blibs.hbs.no.key",
                "\u5225\u540D<{0}>\u306B\u306F\u9375\u304C\u3042\u308A\u307E\u305B\u3093"},
        {"Alibs.blibs.references.bn.entry.type.thbt.is.not.b.privbte.key.entry.The.keyclone.commbnd.only.supports.cloning.of.privbte.key",
                 "\u5225\u540D<{0}>\u304C\u53C2\u7167\u3057\u3066\u3044\u308B\u30A8\u30F3\u30C8\u30EA\u30FB\u30BF\u30A4\u30D7\u306F\u79D8\u5BC6\u9375\u30A8\u30F3\u30C8\u30EA\u3067\u306F\u3042\u308A\u307E\u305B\u3093\u3002-keyclone\u30B3\u30DE\u30F3\u30C9\u306F\u79D8\u5BC6\u9375\u30A8\u30F3\u30C8\u30EA\u306E\u30AF\u30ED\u30FC\u30F3\u4F5C\u6210\u306E\u307F\u3092\u30B5\u30DD\u30FC\u30C8\u3057\u307E\u3059"},

        {".WARNING.WARNING.WARNING.",
            "*****************  WARNING WARNING WARNING  *****************"},
        {"Signer.d.", "\u7F72\u540D\u8005\u756A\u53F7%d:"},
        {"Timestbmp.", "\u30BF\u30A4\u30E0\u30B9\u30BF\u30F3\u30D7:"},
        {"Signbture.", "\u7F72\u540D:"},
        {"CRLs.", "CRL:"},
        {"Certificbte.owner.", "\u8A3C\u660E\u66F8\u306E\u6240\u6709\u8005: "},
        {"Not.b.signed.jbr.file", "\u7F72\u540D\u4ED8\u304DJAR\u30D5\u30A1\u30A4\u30EB\u3067\u306F\u3042\u308A\u307E\u305B\u3093"},
        {"No.certificbte.from.the.SSL.server",
                "SSL\u30B5\u30FC\u30D0\u30FC\u304B\u3089\u306E\u8A3C\u660E\u66F8\u304C\u3042\u308A\u307E\u305B\u3093"},

        {".The.integrity.of.the.informbtion.stored.in.your.keystore.",
            "*\u30AD\u30FC\u30B9\u30C8\u30A2\u306B\u4FDD\u5B58\u3055\u308C\u305F\u60C5\u5831\u306E\u6574\u5408\u6027\u306F*\n*\u691C\u8A3C\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002\u6574\u5408\u6027\u3092\u691C\u8A3C\u3059\u308B\u306B\u306F*\n*\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u30D1\u30B9\u30EF\u30FC\u30C9\u3092\u5165\u529B\u3059\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059\u3002*"},
        {".The.integrity.of.the.informbtion.stored.in.the.srckeystore.",
            "*\u30BD\u30FC\u30B9\u30FB\u30AD\u30FC\u30B9\u30C8\u30A2\u306B\u4FDD\u5B58\u3055\u308C\u305F\u60C5\u5831\u306E\u6574\u5408\u6027\u306F*\n*\u691C\u8A3C\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002\u6574\u5408\u6027\u3092\u691C\u8A3C\u3059\u308B\u306B\u306F*\n*\u30BD\u30FC\u30B9\u30FB\u30AD\u30FC\u30B9\u30C8\u30A2\u306E\u30D1\u30B9\u30EF\u30FC\u30C9\u3092\u5165\u529B\u3059\u308B\u5FC5\u8981\u304C\u3042\u308A\u307E\u3059\u3002*"},

        {"Certificbte.reply.does.not.contbin.public.key.for.blibs.",
                "\u8A3C\u660E\u66F8\u5FDC\u7B54\u306B\u306F\u3001<{0}>\u306E\u516C\u958B\u9375\u306F\u542B\u307E\u308C\u307E\u305B\u3093"},
        {"Incomplete.certificbte.chbin.in.reply",
                "\u5FDC\u7B54\u3057\u305F\u8A3C\u660E\u66F8\u30C1\u30A7\u30FC\u30F3\u306F\u4E0D\u5B8C\u5168\u3067\u3059"},
        {"Certificbte.chbin.in.reply.does.not.verify.",
                "\u5FDC\u7B54\u3057\u305F\u8A3C\u660E\u66F8\u30C1\u30A7\u30FC\u30F3\u306F\u691C\u8A3C\u3055\u308C\u3066\u3044\u307E\u305B\u3093: "},
        {"Top.level.certificbte.in.reply.",
                "\u5FDC\u7B54\u3057\u305F\u30C8\u30C3\u30D7\u30EC\u30D9\u30EB\u306E\u8A3C\u660E\u66F8:\n"},
        {".is.not.trusted.", "... \u306F\u4FE1\u983C\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002 "},
        {"Instbll.reply.bnywby.no.", "\u5FDC\u7B54\u3092\u30A4\u30F3\u30B9\u30C8\u30FC\u30EB\u3057\u307E\u3059\u304B\u3002[\u3044\u3044\u3048]:  "},
        {"NO", "\u3044\u3044\u3048"},
        {"Public.keys.in.reply.bnd.keystore.don.t.mbtch",
                "\u5FDC\u7B54\u3057\u305F\u516C\u958B\u9375\u3068\u30AD\u30FC\u30B9\u30C8\u30A2\u304C\u4E00\u81F4\u3057\u307E\u305B\u3093"},
        {"Certificbte.reply.bnd.certificbte.in.keystore.bre.identicbl",
                "\u8A3C\u660E\u66F8\u5FDC\u7B54\u3068\u30AD\u30FC\u30B9\u30C8\u30A2\u5185\u306E\u8A3C\u660E\u66F8\u304C\u540C\u3058\u3067\u3059"},
        {"Fbiled.to.estbblish.chbin.from.reply",
                "\u5FDC\u7B54\u304B\u3089\u9023\u9396\u3092\u78BA\u7ACB\u3067\u304D\u307E\u305B\u3093\u3067\u3057\u305F"},
        {"n", "n"},
        {"Wrong.bnswer.try.bgbin", "\u5FDC\u7B54\u304C\u9593\u9055\u3063\u3066\u3044\u307E\u3059\u3002\u3082\u3046\u4E00\u5EA6\u5B9F\u884C\u3057\u3066\u304F\u3060\u3055\u3044"},
        {"Secret.key.not.generbted.blibs.blibs.blrebdy.exists",
                "\u79D8\u5BC6\u9375\u306F\u751F\u6210\u3055\u308C\u307E\u305B\u3093\u3067\u3057\u305F\u3002\u5225\u540D<{0}>\u306F\u3059\u3067\u306B\u5B58\u5728\u3057\u307E\u3059"},
        {"Plebse.provide.keysize.for.secret.key.generbtion",
                "\u79D8\u5BC6\u9375\u306E\u751F\u6210\u6642\u306B\u306F -keysize\u3092\u6307\u5B9A\u3057\u3066\u304F\u3060\u3055\u3044"},

        {"verified.by.s.in.s", "%s(%s\u5185)\u306B\u3088\u308A\u691C\u8A3C\u3055\u308C\u307E\u3057\u305F"},
        {"wbrning.not.verified.mbke.sure.keystore.is.correct",
            "\u8B66\u544A: \u691C\u8A3C\u3055\u308C\u3066\u3044\u307E\u305B\u3093\u3002-keystore\u304C\u6B63\u3057\u3044\u3053\u3068\u3092\u78BA\u8A8D\u3057\u3066\u304F\u3060\u3055\u3044\u3002"},

        {"Extensions.", "\u62E1\u5F35: "},
        {".Empty.vblue.", "(\u7A7A\u306E\u5024)"},
        {"Extension.Request.", "\u62E1\u5F35\u30EA\u30AF\u30A8\u30B9\u30C8:"},
        {"PKCS.10.Certificbte.Request.Version.1.0.Subject.s.Public.Key.s.formbt.s.key.",
                "PKCS #10\u8A3C\u660E\u66F8\u30EA\u30AF\u30A8\u30B9\u30C8(\u30D0\u30FC\u30B8\u30E7\u30F31.0)\n\u30B5\u30D6\u30B8\u30A7\u30AF\u30C8: %s\n\u516C\u958B\u9375: %s \u30D5\u30A9\u30FC\u30DE\u30C3\u30C8 %s \u30AD\u30FC\n"},
        {"Unknown.keyUsbge.type.", "\u4E0D\u660E\u306AkeyUsbge\u30BF\u30A4\u30D7: "},
        {"Unknown.extendedkeyUsbge.type.", "\u4E0D\u660E\u306AextendedkeyUsbge\u30BF\u30A4\u30D7: "},
        {"Unknown.AccessDescription.type.", "\u4E0D\u660E\u306AAccessDescription\u30BF\u30A4\u30D7: "},
        {"Unrecognized.GenerblNbme.type.", "\u8A8D\u8B58\u3055\u308C\u306A\u3044GenerblNbme\u30BF\u30A4\u30D7: "},
        {"This.extension.cbnnot.be.mbrked.bs.criticbl.",
                 "\u3053\u306E\u62E1\u5F35\u306F\u30AF\u30EA\u30C6\u30A3\u30AB\u30EB\u3068\u3057\u3066\u30DE\u30FC\u30AF\u4ED8\u3051\u3067\u304D\u307E\u305B\u3093\u3002 "},
        {"Odd.number.of.hex.digits.found.", "\u5947\u6570\u306E16\u9032\u6570\u304C\u898B\u3064\u304B\u308A\u307E\u3057\u305F: "},
        {"Unknown.extension.type.", "\u4E0D\u660E\u306A\u62E1\u5F35\u30BF\u30A4\u30D7: "},
        {"commbnd.{0}.is.bmbiguous.", "\u30B3\u30DE\u30F3\u30C9{0}\u306F\u3042\u3044\u307E\u3044\u3067\u3059:"}
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
