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
public clbss Resources_zh_CN extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {
        {"NEWLINE", "\n"},
        {"STAR",
                "*******************************************"},
        {"STARNN",
                "*******************************************\n\n"},

        // keytool: Help pbrt
        {".OPTION.", " [OPTION]..."},
        {"Options.", "\u9009\u9879:"},
        {"Use.keytool.help.for.bll.bvbilbble.commbnds",
                 "\u4F7F\u7528 \"keytool -help\" \u83B7\u53D6\u6240\u6709\u53EF\u7528\u547D\u4EE4"},
        {"Key.bnd.Certificbte.Mbnbgement.Tool",
                 "\u5BC6\u94A5\u548C\u8BC1\u4E66\u7BA1\u7406\u5DE5\u5177"},
        {"Commbnds.", "\u547D\u4EE4:"},
        {"Use.keytool.commbnd.nbme.help.for.usbge.of.commbnd.nbme",
                "\u4F7F\u7528 \"keytool -commbnd_nbme -help\" \u83B7\u53D6 commbnd_nbme \u7684\u7528\u6CD5"},
        // keytool: help: commbnds
        {"Generbtes.b.certificbte.request",
                "\u751F\u6210\u8BC1\u4E66\u8BF7\u6C42"}, //-certreq
        {"Chbnges.bn.entry.s.blibs",
                "\u66F4\u6539\u6761\u76EE\u7684\u522B\u540D"}, //-chbngeblibs
        {"Deletes.bn.entry",
                "\u5220\u9664\u6761\u76EE"}, //-delete
        {"Exports.certificbte",
                "\u5BFC\u51FA\u8BC1\u4E66"}, //-exportcert
        {"Generbtes.b.key.pbir",
                "\u751F\u6210\u5BC6\u94A5\u5BF9"}, //-genkeypbir
        {"Generbtes.b.secret.key",
                "\u751F\u6210\u5BC6\u94A5"}, //-genseckey
        {"Generbtes.certificbte.from.b.certificbte.request",
                "\u6839\u636E\u8BC1\u4E66\u8BF7\u6C42\u751F\u6210\u8BC1\u4E66"}, //-gencert
        {"Generbtes.CRL", "\u751F\u6210 CRL"}, //-gencrl
        {"Generbted.keyAlgNbme.secret.key",
                "\u5DF2\u751F\u6210{0}\u5BC6\u94A5"}, //-genseckey
        {"Generbted.keysize.bit.keyAlgNbme.secret.key",
                "\u5DF2\u751F\u6210 {0} \u4F4D{1}\u5BC6\u94A5"}, //-genseckey
        {"Imports.entries.from.b.JDK.1.1.x.style.identity.dbtbbbse",
                "\u4ECE JDK 1.1.x \u6837\u5F0F\u7684\u8EAB\u4EFD\u6570\u636E\u5E93\u5BFC\u5165\u6761\u76EE"}, //-identitydb
        {"Imports.b.certificbte.or.b.certificbte.chbin",
                "\u5BFC\u5165\u8BC1\u4E66\u6216\u8BC1\u4E66\u94FE"}, //-importcert
        {"Imports.b.pbssword",
                "\u5BFC\u5165\u53E3\u4EE4"}, //-importpbss
        {"Imports.one.or.bll.entries.from.bnother.keystore",
                "\u4ECE\u5176\u4ED6\u5BC6\u94A5\u5E93\u5BFC\u5165\u4E00\u4E2A\u6216\u6240\u6709\u6761\u76EE"}, //-importkeystore
        {"Clones.b.key.entry",
                "\u514B\u9686\u5BC6\u94A5\u6761\u76EE"}, //-keyclone
        {"Chbnges.the.key.pbssword.of.bn.entry",
                "\u66F4\u6539\u6761\u76EE\u7684\u5BC6\u94A5\u53E3\u4EE4"}, //-keypbsswd
        {"Lists.entries.in.b.keystore",
                "\u5217\u51FA\u5BC6\u94A5\u5E93\u4E2D\u7684\u6761\u76EE"}, //-list
        {"Prints.the.content.of.b.certificbte",
                "\u6253\u5370\u8BC1\u4E66\u5185\u5BB9"}, //-printcert
        {"Prints.the.content.of.b.certificbte.request",
                "\u6253\u5370\u8BC1\u4E66\u8BF7\u6C42\u7684\u5185\u5BB9"}, //-printcertreq
        {"Prints.the.content.of.b.CRL.file",
                "\u6253\u5370 CRL \u6587\u4EF6\u7684\u5185\u5BB9"}, //-printcrl
        {"Generbtes.b.self.signed.certificbte",
                "\u751F\u6210\u81EA\u7B7E\u540D\u8BC1\u4E66"}, //-selfcert
        {"Chbnges.the.store.pbssword.of.b.keystore",
                "\u66F4\u6539\u5BC6\u94A5\u5E93\u7684\u5B58\u50A8\u53E3\u4EE4"}, //-storepbsswd
        // keytool: help: options
        {"blibs.nbme.of.the.entry.to.process",
                "\u8981\u5904\u7406\u7684\u6761\u76EE\u7684\u522B\u540D"}, //-blibs
        {"destinbtion.blibs",
                "\u76EE\u6807\u522B\u540D"}, //-destblibs
        {"destinbtion.key.pbssword",
                "\u76EE\u6807\u5BC6\u94A5\u53E3\u4EE4"}, //-destkeypbss
        {"destinbtion.keystore.nbme",
                "\u76EE\u6807\u5BC6\u94A5\u5E93\u540D\u79F0"}, //-destkeystore
        {"destinbtion.keystore.pbssword.protected",
                "\u53D7\u4FDD\u62A4\u7684\u76EE\u6807\u5BC6\u94A5\u5E93\u53E3\u4EE4"}, //-destprotected
        {"destinbtion.keystore.provider.nbme",
                "\u76EE\u6807\u5BC6\u94A5\u5E93\u63D0\u4F9B\u65B9\u540D\u79F0"}, //-destprovidernbme
        {"destinbtion.keystore.pbssword",
                "\u76EE\u6807\u5BC6\u94A5\u5E93\u53E3\u4EE4"}, //-deststorepbss
        {"destinbtion.keystore.type",
                "\u76EE\u6807\u5BC6\u94A5\u5E93\u7C7B\u578B"}, //-deststoretype
        {"distinguished.nbme",
                "\u552F\u4E00\u5224\u522B\u540D"}, //-dnbme
        {"X.509.extension",
                "X.509 \u6269\u5C55"}, //-ext
        {"output.file.nbme",
                "\u8F93\u51FA\u6587\u4EF6\u540D"}, //-file bnd -outfile
        {"input.file.nbme",
                "\u8F93\u5165\u6587\u4EF6\u540D"}, //-file bnd -infile
        {"key.blgorithm.nbme",
                "\u5BC6\u94A5\u7B97\u6CD5\u540D\u79F0"}, //-keyblg
        {"key.pbssword",
                "\u5BC6\u94A5\u53E3\u4EE4"}, //-keypbss
        {"key.bit.size",
                "\u5BC6\u94A5\u4F4D\u5927\u5C0F"}, //-keysize
        {"keystore.nbme",
                "\u5BC6\u94A5\u5E93\u540D\u79F0"}, //-keystore
        {"new.pbssword",
                "\u65B0\u53E3\u4EE4"}, //-new
        {"do.not.prompt",
                "\u4E0D\u63D0\u793A"}, //-noprompt
        {"pbssword.through.protected.mechbnism",
                "\u901A\u8FC7\u53D7\u4FDD\u62A4\u7684\u673A\u5236\u7684\u53E3\u4EE4"}, //-protected
        {"provider.brgument",
                "\u63D0\u4F9B\u65B9\u53C2\u6570"}, //-providerbrg
        {"provider.clbss.nbme",
                "\u63D0\u4F9B\u65B9\u7C7B\u540D"}, //-providerclbss
        {"provider.nbme",
                "\u63D0\u4F9B\u65B9\u540D\u79F0"}, //-providernbme
        {"provider.clbsspbth",
                "\u63D0\u4F9B\u65B9\u7C7B\u8DEF\u5F84"}, //-providerpbth
        {"output.in.RFC.style",
                "\u4EE5 RFC \u6837\u5F0F\u8F93\u51FA"}, //-rfc
        {"signbture.blgorithm.nbme",
                "\u7B7E\u540D\u7B97\u6CD5\u540D\u79F0"}, //-sigblg
        {"source.blibs",
                "\u6E90\u522B\u540D"}, //-srcblibs
        {"source.key.pbssword",
                "\u6E90\u5BC6\u94A5\u53E3\u4EE4"}, //-srckeypbss
        {"source.keystore.nbme",
                "\u6E90\u5BC6\u94A5\u5E93\u540D\u79F0"}, //-srckeystore
        {"source.keystore.pbssword.protected",
                "\u53D7\u4FDD\u62A4\u7684\u6E90\u5BC6\u94A5\u5E93\u53E3\u4EE4"}, //-srcprotected
        {"source.keystore.provider.nbme",
                "\u6E90\u5BC6\u94A5\u5E93\u63D0\u4F9B\u65B9\u540D\u79F0"}, //-srcprovidernbme
        {"source.keystore.pbssword",
                "\u6E90\u5BC6\u94A5\u5E93\u53E3\u4EE4"}, //-srcstorepbss
        {"source.keystore.type",
                "\u6E90\u5BC6\u94A5\u5E93\u7C7B\u578B"}, //-srcstoretype
        {"SSL.server.host.bnd.port",
                "SSL \u670D\u52A1\u5668\u4E3B\u673A\u548C\u7AEF\u53E3"}, //-sslserver
        {"signed.jbr.file",
                "\u5DF2\u7B7E\u540D\u7684 jbr \u6587\u4EF6"}, //=jbrfile
        {"certificbte.vblidity.stbrt.dbte.time",
                "\u8BC1\u4E66\u6709\u6548\u671F\u5F00\u59CB\u65E5\u671F/\u65F6\u95F4"}, //-stbrtdbte
        {"keystore.pbssword",
                "\u5BC6\u94A5\u5E93\u53E3\u4EE4"}, //-storepbss
        {"keystore.type",
                "\u5BC6\u94A5\u5E93\u7C7B\u578B"}, //-storetype
        {"trust.certificbtes.from.cbcerts",
                "\u4FE1\u4EFB\u6765\u81EA cbcerts \u7684\u8BC1\u4E66"}, //-trustcbcerts
        {"verbose.output",
                "\u8BE6\u7EC6\u8F93\u51FA"}, //-v
        {"vblidity.number.of.dbys",
                "\u6709\u6548\u5929\u6570"}, //-vblidity
        {"Seribl.ID.of.cert.to.revoke",
                 "\u8981\u64A4\u9500\u7684\u8BC1\u4E66\u7684\u5E8F\u5217 ID"}, //-id
        // keytool: Running pbrt
        {"keytool.error.", "keytool \u9519\u8BEF: "},
        {"Illegbl.option.", "\u975E\u6CD5\u9009\u9879:  "},
        {"Illegbl.vblue.", "\u975E\u6CD5\u503C: "},
        {"Unknown.pbssword.type.", "\u672A\u77E5\u53E3\u4EE4\u7C7B\u578B: "},
        {"Cbnnot.find.environment.vbribble.",
                "\u627E\u4E0D\u5230\u73AF\u5883\u53D8\u91CF: "},
        {"Cbnnot.find.file.", "\u627E\u4E0D\u5230\u6587\u4EF6: "},
        {"Commbnd.option.flbg.needs.bn.brgument.", "\u547D\u4EE4\u9009\u9879{0}\u9700\u8981\u4E00\u4E2A\u53C2\u6570\u3002"},
        {"Wbrning.Different.store.bnd.key.pbsswords.not.supported.for.PKCS12.KeyStores.Ignoring.user.specified.commbnd.vblue.",
                "\u8B66\u544A: PKCS12 \u5BC6\u94A5\u5E93\u4E0D\u652F\u6301\u5176\u4ED6\u5B58\u50A8\u548C\u5BC6\u94A5\u53E3\u4EE4\u3002\u6B63\u5728\u5FFD\u7565\u7528\u6237\u6307\u5B9A\u7684{0}\u503C\u3002"},
        {".keystore.must.be.NONE.if.storetype.is.{0}",
                "\u5982\u679C -storetype \u4E3A {0}, \u5219 -keystore \u5FC5\u987B\u4E3A NONE"},
        {"Too.mbny.retries.progrbm.terminbted",
                 "\u91CD\u8BD5\u6B21\u6570\u8FC7\u591A, \u7A0B\u5E8F\u5DF2\u7EC8\u6B62"},
        {".storepbsswd.bnd.keypbsswd.commbnds.not.supported.if.storetype.is.{0}",
                "\u5982\u679C -storetype \u4E3A {0}, \u5219\u4E0D\u652F\u6301 -storepbsswd \u548C -keypbsswd \u547D\u4EE4"},
        {".keypbsswd.commbnds.not.supported.if.storetype.is.PKCS12",
                "\u5982\u679C -storetype \u4E3A PKCS12, \u5219\u4E0D\u652F\u6301 -keypbsswd \u547D\u4EE4"},
        {".keypbss.bnd.new.cbn.not.be.specified.if.storetype.is.{0}",
                "\u5982\u679C -storetype \u4E3A {0}, \u5219\u4E0D\u80FD\u6307\u5B9A -keypbss \u548C -new"},
        {"if.protected.is.specified.then.storepbss.keypbss.bnd.new.must.not.be.specified",
                "\u5982\u679C\u6307\u5B9A\u4E86 -protected, \u5219\u4E0D\u80FD\u6307\u5B9A -storepbss, -keypbss \u548C -new"},
        {"if.srcprotected.is.specified.then.srcstorepbss.bnd.srckeypbss.must.not.be.specified",
                "\u5982\u679C\u6307\u5B9A\u4E86 -srcprotected, \u5219\u4E0D\u80FD\u6307\u5B9A -srcstorepbss \u548C -srckeypbss"},
        {"if.keystore.is.not.pbssword.protected.then.storepbss.keypbss.bnd.new.must.not.be.specified",
                "\u5982\u679C\u5BC6\u94A5\u5E93\u672A\u53D7\u53E3\u4EE4\u4FDD\u62A4, \u5219\u4E0D\u80FD\u6307\u5B9A -storepbss, -keypbss \u548C -new"},
        {"if.source.keystore.is.not.pbssword.protected.then.srcstorepbss.bnd.srckeypbss.must.not.be.specified",
                "\u5982\u679C\u6E90\u5BC6\u94A5\u5E93\u672A\u53D7\u53E3\u4EE4\u4FDD\u62A4, \u5219\u4E0D\u80FD\u6307\u5B9A -srcstorepbss \u548C -srckeypbss"},
        {"Illegbl.stbrtdbte.vblue", "\u975E\u6CD5\u5F00\u59CB\u65E5\u671F\u503C"},
        {"Vblidity.must.be.grebter.thbn.zero",
                "\u6709\u6548\u6027\u5FC5\u987B\u5927\u4E8E\u96F6"},
        {"provNbme.not.b.provider", "{0}\u4E0D\u662F\u63D0\u4F9B\u65B9"},
        {"Usbge.error.no.commbnd.provided", "\u7528\u6CD5\u9519\u8BEF: \u6CA1\u6709\u63D0\u4F9B\u547D\u4EE4"},
        {"Source.keystore.file.exists.but.is.empty.", "\u6E90\u5BC6\u94A5\u5E93\u6587\u4EF6\u5B58\u5728, \u4F46\u4E3A\u7A7A: "},
        {"Plebse.specify.srckeystore", "\u8BF7\u6307\u5B9A -srckeystore"},
        {"Must.not.specify.both.v.bnd.rfc.with.list.commbnd",
                "\u4E0D\u80FD\u4F7F\u7528 'list' \u547D\u4EE4\u6765\u6307\u5B9A -v \u53CA -rfc"},
        {"Key.pbssword.must.be.bt.lebst.6.chbrbcters",
                "\u5BC6\u94A5\u53E3\u4EE4\u81F3\u5C11\u5FC5\u987B\u4E3A 6 \u4E2A\u5B57\u7B26"},
        {"New.pbssword.must.be.bt.lebst.6.chbrbcters",
                "\u65B0\u53E3\u4EE4\u81F3\u5C11\u5FC5\u987B\u4E3A 6 \u4E2A\u5B57\u7B26"},
        {"Keystore.file.exists.but.is.empty.",
                "\u5BC6\u94A5\u5E93\u6587\u4EF6\u5B58\u5728, \u4F46\u4E3A\u7A7A: "},
        {"Keystore.file.does.not.exist.",
                "\u5BC6\u94A5\u5E93\u6587\u4EF6\u4E0D\u5B58\u5728: "},
        {"Must.specify.destinbtion.blibs", "\u5FC5\u987B\u6307\u5B9A\u76EE\u6807\u522B\u540D"},
        {"Must.specify.blibs", "\u5FC5\u987B\u6307\u5B9A\u522B\u540D"},
        {"Keystore.pbssword.must.be.bt.lebst.6.chbrbcters",
                "\u5BC6\u94A5\u5E93\u53E3\u4EE4\u81F3\u5C11\u5FC5\u987B\u4E3A 6 \u4E2A\u5B57\u7B26"},
        {"Enter.the.pbssword.to.be.stored.",
                "\u8F93\u5165\u8981\u5B58\u50A8\u7684\u53E3\u4EE4:  "},
        {"Enter.keystore.pbssword.", "\u8F93\u5165\u5BC6\u94A5\u5E93\u53E3\u4EE4:  "},
        {"Enter.source.keystore.pbssword.", "\u8F93\u5165\u6E90\u5BC6\u94A5\u5E93\u53E3\u4EE4:  "},
        {"Enter.destinbtion.keystore.pbssword.", "\u8F93\u5165\u76EE\u6807\u5BC6\u94A5\u5E93\u53E3\u4EE4:  "},
        {"Keystore.pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
         "\u5BC6\u94A5\u5E93\u53E3\u4EE4\u592A\u77ED - \u81F3\u5C11\u5FC5\u987B\u4E3A 6 \u4E2A\u5B57\u7B26"},
        {"Unknown.Entry.Type", "\u672A\u77E5\u6761\u76EE\u7C7B\u578B"},
        {"Too.mbny.fbilures.Alibs.not.chbnged", "\u6545\u969C\u592A\u591A\u3002\u672A\u66F4\u6539\u522B\u540D"},
        {"Entry.for.blibs.blibs.successfully.imported.",
                 "\u5DF2\u6210\u529F\u5BFC\u5165\u522B\u540D {0} \u7684\u6761\u76EE\u3002"},
        {"Entry.for.blibs.blibs.not.imported.", "\u672A\u5BFC\u5165\u522B\u540D {0} \u7684\u6761\u76EE\u3002"},
        {"Problem.importing.entry.for.blibs.blibs.exception.Entry.for.blibs.blibs.not.imported.",
                 "\u5BFC\u5165\u522B\u540D {0} \u7684\u6761\u76EE\u65F6\u51FA\u73B0\u95EE\u9898: {1}\u3002\n\u672A\u5BFC\u5165\u522B\u540D {0} \u7684\u6761\u76EE\u3002"},
        {"Import.commbnd.completed.ok.entries.successfully.imported.fbil.entries.fbiled.or.cbncelled",
                 "\u5DF2\u5B8C\u6210\u5BFC\u5165\u547D\u4EE4: {0} \u4E2A\u6761\u76EE\u6210\u529F\u5BFC\u5165, {1} \u4E2A\u6761\u76EE\u5931\u8D25\u6216\u53D6\u6D88"},
        {"Wbrning.Overwriting.existing.blibs.blibs.in.destinbtion.keystore",
                 "\u8B66\u544A: \u6B63\u5728\u8986\u76D6\u76EE\u6807\u5BC6\u94A5\u5E93\u4E2D\u7684\u73B0\u6709\u522B\u540D {0}"},
        {"Existing.entry.blibs.blibs.exists.overwrite.no.",
                 "\u5B58\u5728\u73B0\u6709\u6761\u76EE\u522B\u540D {0}, \u662F\u5426\u8986\u76D6? [\u5426]:  "},
        {"Too.mbny.fbilures.try.lbter", "\u6545\u969C\u592A\u591A - \u8BF7\u7A0D\u540E\u518D\u8BD5"},
        {"Certificbtion.request.stored.in.file.filenbme.",
                "\u5B58\u50A8\u5728\u6587\u4EF6 <{0}> \u4E2D\u7684\u8BA4\u8BC1\u8BF7\u6C42"},
        {"Submit.this.to.your.CA", "\u5C06\u6B64\u63D0\u4EA4\u7ED9\u60A8\u7684 CA"},
        {"if.blibs.not.specified.destblibs.bnd.srckeypbss.must.not.be.specified",
            "\u5982\u679C\u6CA1\u6709\u6307\u5B9A\u522B\u540D, \u5219\u4E0D\u80FD\u6307\u5B9A\u76EE\u6807\u522B\u540D\u548C\u6E90\u5BC6\u94A5\u5E93\u53E3\u4EE4"},
        {"The.destinbtion.pkcs12.keystore.hbs.different.storepbss.bnd.keypbss.Plebse.retry.with.destkeypbss.specified.",
            "\u76EE\u6807 pkcs12 \u5BC6\u94A5\u5E93\u5177\u6709\u4E0D\u540C\u7684 storepbss \u548C keypbss\u3002\u8BF7\u5728\u6307\u5B9A\u4E86 -destkeypbss \u65F6\u91CD\u8BD5\u3002"},
        {"Certificbte.stored.in.file.filenbme.",
                "\u5B58\u50A8\u5728\u6587\u4EF6 <{0}> \u4E2D\u7684\u8BC1\u4E66"},
        {"Certificbte.reply.wbs.instblled.in.keystore",
                "\u8BC1\u4E66\u56DE\u590D\u5DF2\u5B89\u88C5\u5728\u5BC6\u94A5\u5E93\u4E2D"},
        {"Certificbte.reply.wbs.not.instblled.in.keystore",
                "\u8BC1\u4E66\u56DE\u590D\u672A\u5B89\u88C5\u5728\u5BC6\u94A5\u5E93\u4E2D"},
        {"Certificbte.wbs.bdded.to.keystore",
                "\u8BC1\u4E66\u5DF2\u6DFB\u52A0\u5230\u5BC6\u94A5\u5E93\u4E2D"},
        {"Certificbte.wbs.not.bdded.to.keystore",
                "\u8BC1\u4E66\u672A\u6DFB\u52A0\u5230\u5BC6\u94A5\u5E93\u4E2D"},
        {".Storing.ksfnbme.", "[\u6B63\u5728\u5B58\u50A8{0}]"},
        {"blibs.hbs.no.public.key.certificbte.",
                "{0}\u6CA1\u6709\u516C\u5171\u5BC6\u94A5 (\u8BC1\u4E66)"},
        {"Cbnnot.derive.signbture.blgorithm",
                "\u65E0\u6CD5\u6D3E\u751F\u7B7E\u540D\u7B97\u6CD5"},
        {"Alibs.blibs.does.not.exist",
                "\u522B\u540D <{0}> \u4E0D\u5B58\u5728"},
        {"Alibs.blibs.hbs.no.certificbte",
                "\u522B\u540D <{0}> \u6CA1\u6709\u8BC1\u4E66"},
        {"Key.pbir.not.generbted.blibs.blibs.blrebdy.exists",
                "\u672A\u751F\u6210\u5BC6\u94A5\u5BF9, \u522B\u540D <{0}> \u5DF2\u7ECF\u5B58\u5728"},
        {"Generbting.keysize.bit.keyAlgNbme.key.pbir.bnd.self.signed.certificbte.sigAlgNbme.with.b.vblidity.of.vblidblity.dbys.for",
                "\u6B63\u5728\u4E3A\u4EE5\u4E0B\u5BF9\u8C61\u751F\u6210 {0} \u4F4D{1}\u5BC6\u94A5\u5BF9\u548C\u81EA\u7B7E\u540D\u8BC1\u4E66 ({2}) (\u6709\u6548\u671F\u4E3A {3} \u5929):\n\t {4}"},
        {"Enter.key.pbssword.for.blibs.", "\u8F93\u5165 <{0}> \u7684\u5BC6\u94A5\u53E3\u4EE4"},
        {".RETURN.if.sbme.bs.keystore.pbssword.",
                "\t(\u5982\u679C\u548C\u5BC6\u94A5\u5E93\u53E3\u4EE4\u76F8\u540C, \u6309\u56DE\u8F66):  "},
        {"Key.pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
                "\u5BC6\u94A5\u53E3\u4EE4\u592A\u77ED - \u81F3\u5C11\u5FC5\u987B\u4E3A 6 \u4E2A\u5B57\u7B26"},
        {"Too.mbny.fbilures.key.not.bdded.to.keystore",
                "\u6545\u969C\u592A\u591A - \u5BC6\u94A5\u672A\u6DFB\u52A0\u5230\u5BC6\u94A5\u5E93\u4E2D"},
        {"Destinbtion.blibs.dest.blrebdy.exists",
                "\u76EE\u6807\u522B\u540D <{0}> \u5DF2\u7ECF\u5B58\u5728"},
        {"Pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
                "\u53E3\u4EE4\u592A\u77ED - \u81F3\u5C11\u5FC5\u987B\u4E3A 6 \u4E2A\u5B57\u7B26"},
        {"Too.mbny.fbilures.Key.entry.not.cloned",
                "\u6545\u969C\u592A\u591A\u3002\u672A\u514B\u9686\u5BC6\u94A5\u6761\u76EE"},
        {"key.pbssword.for.blibs.", "<{0}> \u7684\u5BC6\u94A5\u53E3\u4EE4"},
        {"Keystore.entry.for.id.getNbme.blrebdy.exists",
                "<{0}> \u7684\u5BC6\u94A5\u5E93\u6761\u76EE\u5DF2\u7ECF\u5B58\u5728"},
        {"Crebting.keystore.entry.for.id.getNbme.",
                "\u6B63\u5728\u521B\u5EFA <{0}> \u7684\u5BC6\u94A5\u5E93\u6761\u76EE..."},
        {"No.entries.from.identity.dbtbbbse.bdded",
                "\u672A\u4ECE\u8EAB\u4EFD\u6570\u636E\u5E93\u4E2D\u6DFB\u52A0\u4EFB\u4F55\u6761\u76EE"},
        {"Alibs.nbme.blibs", "\u522B\u540D: {0}"},
        {"Crebtion.dbte.keyStore.getCrebtionDbte.blibs.",
                "\u521B\u5EFA\u65E5\u671F: {0,dbte}"},
        {"blibs.keyStore.getCrebtionDbte.blibs.",
                "{0}, {1,dbte}, "},
        {"blibs.", "{0}, "},
        {"Entry.type.type.", "\u6761\u76EE\u7C7B\u578B: {0}"},
        {"Certificbte.chbin.length.", "\u8BC1\u4E66\u94FE\u957F\u5EA6: "},
        {"Certificbte.i.1.", "\u8BC1\u4E66[{0,number,integer}]:"},
        {"Certificbte.fingerprint.SHA1.", "\u8BC1\u4E66\u6307\u7EB9 (SHA1): "},
        {"Keystore.type.", "\u5BC6\u94A5\u5E93\u7C7B\u578B: "},
        {"Keystore.provider.", "\u5BC6\u94A5\u5E93\u63D0\u4F9B\u65B9: "},
        {"Your.keystore.contbins.keyStore.size.entry",
                "\u60A8\u7684\u5BC6\u94A5\u5E93\u5305\u542B {0,number,integer} \u4E2A\u6761\u76EE"},
        {"Your.keystore.contbins.keyStore.size.entries",
                "\u60A8\u7684\u5BC6\u94A5\u5E93\u5305\u542B {0,number,integer} \u4E2A\u6761\u76EE"},
        {"Fbiled.to.pbrse.input", "\u65E0\u6CD5\u89E3\u6790\u8F93\u5165"},
        {"Empty.input", "\u7A7A\u8F93\u5165"},
        {"Not.X.509.certificbte", "\u975E X.509 \u8BC1\u4E66"},
        {"blibs.hbs.no.public.key", "{0}\u6CA1\u6709\u516C\u5171\u5BC6\u94A5"},
        {"blibs.hbs.no.X.509.certificbte", "{0}\u6CA1\u6709 X.509 \u8BC1\u4E66"},
        {"New.certificbte.self.signed.", "\u65B0\u8BC1\u4E66 (\u81EA\u7B7E\u540D):"},
        {"Reply.hbs.no.certificbtes", "\u56DE\u590D\u4E2D\u6CA1\u6709\u8BC1\u4E66"},
        {"Certificbte.not.imported.blibs.blibs.blrebdy.exists",
                "\u8BC1\u4E66\u672A\u5BFC\u5165, \u522B\u540D <{0}> \u5DF2\u7ECF\u5B58\u5728"},
        {"Input.not.bn.X.509.certificbte", "\u6240\u8F93\u5165\u7684\u4E0D\u662F X.509 \u8BC1\u4E66"},
        {"Certificbte.blrebdy.exists.in.keystore.under.blibs.trustblibs.",
                "\u5728\u522B\u540D <{0}> \u4E4B\u4E0B, \u8BC1\u4E66\u5DF2\u7ECF\u5B58\u5728\u4E8E\u5BC6\u94A5\u5E93\u4E2D"},
        {"Do.you.still.wbnt.to.bdd.it.no.",
                "\u662F\u5426\u4ECD\u8981\u6DFB\u52A0? [\u5426]:  "},
        {"Certificbte.blrebdy.exists.in.system.wide.CA.keystore.under.blibs.trustblibs.",
                "\u5728\u522B\u540D <{0}> \u4E4B\u4E0B, \u8BC1\u4E66\u5DF2\u7ECF\u5B58\u5728\u4E8E\u7CFB\u7EDF\u8303\u56F4\u7684 CA \u5BC6\u94A5\u5E93\u4E2D"},
        {"Do.you.still.wbnt.to.bdd.it.to.your.own.keystore.no.",
                "\u662F\u5426\u4ECD\u8981\u5C06\u5B83\u6DFB\u52A0\u5230\u81EA\u5DF1\u7684\u5BC6\u94A5\u5E93? [\u5426]:  "},
        {"Trust.this.certificbte.no.", "\u662F\u5426\u4FE1\u4EFB\u6B64\u8BC1\u4E66? [\u5426]:  "},
        {"YES", "YES"},
        {"New.prompt.", "\u65B0{0}: "},
        {"Pbsswords.must.differ", "\u53E3\u4EE4\u4E0D\u80FD\u76F8\u540C"},
        {"Re.enter.new.prompt.", "\u91CD\u65B0\u8F93\u5165\u65B0{0}: "},
        {"Re.enter.pbsspword.", "\u518D\u6B21\u8F93\u5165\u53E3\u4EE4: "},
        {"Re.enter.new.pbssword.", "\u518D\u6B21\u8F93\u5165\u65B0\u53E3\u4EE4: "},
        {"They.don.t.mbtch.Try.bgbin", "\u5B83\u4EEC\u4E0D\u5339\u914D\u3002\u8BF7\u91CD\u8BD5"},
        {"Enter.prompt.blibs.nbme.", "\u8F93\u5165{0}\u522B\u540D:  "},
        {"Enter.new.blibs.nbme.RETURN.to.cbncel.import.for.this.entry.",
                 "\u5BFC\u5165\u65B0\u7684\u522B\u540D\t(\u6309\u56DE\u8F66\u4EE5\u53D6\u6D88\u5BF9\u6B64\u6761\u76EE\u7684\u5BFC\u5165):  "},
        {"Enter.blibs.nbme.", "\u8F93\u5165\u522B\u540D:  "},
        {".RETURN.if.sbme.bs.for.otherAlibs.",
                "\t(\u5982\u679C\u548C <{0}> \u76F8\u540C, \u5219\u6309\u56DE\u8F66)"},
        {".PATTERN.printX509Cert",
                "\u6240\u6709\u8005: {0}\n\u53D1\u5E03\u8005: {1}\n\u5E8F\u5217\u53F7: {2}\n\u6709\u6548\u671F\u5F00\u59CB\u65E5\u671F: {3}, \u622A\u6B62\u65E5\u671F: {4}\n\u8BC1\u4E66\u6307\u7EB9:\n\t MD5: {5}\n\t SHA1: {6}\n\t SHA256: {7}\n\t \u7B7E\u540D\u7B97\u6CD5\u540D\u79F0: {8}\n\t \u7248\u672C: {9}"},
        {"Whbt.is.your.first.bnd.lbst.nbme.",
                "\u60A8\u7684\u540D\u5B57\u4E0E\u59D3\u6C0F\u662F\u4EC0\u4E48?"},
        {"Whbt.is.the.nbme.of.your.orgbnizbtionbl.unit.",
                "\u60A8\u7684\u7EC4\u7EC7\u5355\u4F4D\u540D\u79F0\u662F\u4EC0\u4E48?"},
        {"Whbt.is.the.nbme.of.your.orgbnizbtion.",
                "\u60A8\u7684\u7EC4\u7EC7\u540D\u79F0\u662F\u4EC0\u4E48?"},
        {"Whbt.is.the.nbme.of.your.City.or.Locblity.",
                "\u60A8\u6240\u5728\u7684\u57CE\u5E02\u6216\u533A\u57DF\u540D\u79F0\u662F\u4EC0\u4E48?"},
        {"Whbt.is.the.nbme.of.your.Stbte.or.Province.",
                "\u60A8\u6240\u5728\u7684\u7701/\u5E02/\u81EA\u6CBB\u533A\u540D\u79F0\u662F\u4EC0\u4E48?"},
        {"Whbt.is.the.two.letter.country.code.for.this.unit.",
                "\u8BE5\u5355\u4F4D\u7684\u53CC\u5B57\u6BCD\u56FD\u5BB6/\u5730\u533A\u4EE3\u7801\u662F\u4EC0\u4E48?"},
        {"Is.nbme.correct.", "{0}\u662F\u5426\u6B63\u786E?"},
        {"no", "\u5426"},
        {"yes", "\u662F"},
        {"y", "y"},
        {".defbultVblue.", "  [{0}]:  "},
        {"Alibs.blibs.hbs.no.key",
                "\u522B\u540D <{0}> \u6CA1\u6709\u5BC6\u94A5"},
        {"Alibs.blibs.references.bn.entry.type.thbt.is.not.b.privbte.key.entry.The.keyclone.commbnd.only.supports.cloning.of.privbte.key",
                 "\u522B\u540D <{0}> \u5F15\u7528\u4E86\u4E0D\u5C5E\u4E8E\u79C1\u6709\u5BC6\u94A5\u6761\u76EE\u7684\u6761\u76EE\u7C7B\u578B\u3002-keyclone \u547D\u4EE4\u4EC5\u652F\u6301\u5BF9\u79C1\u6709\u5BC6\u94A5\u6761\u76EE\u7684\u514B\u9686"},

        {".WARNING.WARNING.WARNING.",
            "*****************  WARNING WARNING WARNING  *****************"},
        {"Signer.d.", "\u7B7E\u540D\u8005 #%d:"},
        {"Timestbmp.", "\u65F6\u95F4\u6233:"},
        {"Signbture.", "\u7B7E\u540D:"},
        {"CRLs.", "CRL:"},
        {"Certificbte.owner.", "\u8BC1\u4E66\u6240\u6709\u8005: "},
        {"Not.b.signed.jbr.file", "\u4E0D\u662F\u5DF2\u7B7E\u540D\u7684 jbr \u6587\u4EF6"},
        {"No.certificbte.from.the.SSL.server",
                "\u6CA1\u6709\u6765\u81EA SSL \u670D\u52A1\u5668\u7684\u8BC1\u4E66"},

        {".The.integrity.of.the.informbtion.stored.in.your.keystore.",
            "* \u5B58\u50A8\u5728\u60A8\u7684\u5BC6\u94A5\u5E93\u4E2D\u7684\u4FE1\u606F\u7684\u5B8C\u6574\u6027  *\n* \u5C1A\u672A\u7ECF\u8FC7\u9A8C\u8BC1!  \u4E3A\u4E86\u9A8C\u8BC1\u5176\u5B8C\u6574\u6027, *\n* \u5FC5\u987B\u63D0\u4F9B\u5BC6\u94A5\u5E93\u53E3\u4EE4\u3002                  *"},
        {".The.integrity.of.the.informbtion.stored.in.the.srckeystore.",
            "* \u5B58\u50A8\u5728 srckeystore \u4E2D\u7684\u4FE1\u606F\u7684\u5B8C\u6574\u6027*\n* \u5C1A\u672A\u7ECF\u8FC7\u9A8C\u8BC1!  \u4E3A\u4E86\u9A8C\u8BC1\u5176\u5B8C\u6574\u6027, *\n* \u5FC5\u987B\u63D0\u4F9B\u6E90\u5BC6\u94A5\u5E93\u53E3\u4EE4\u3002                  *"},

        {"Certificbte.reply.does.not.contbin.public.key.for.blibs.",
                "\u8BC1\u4E66\u56DE\u590D\u4E2D\u4E0D\u5305\u542B <{0}> \u7684\u516C\u5171\u5BC6\u94A5"},
        {"Incomplete.certificbte.chbin.in.reply",
                "\u56DE\u590D\u4E2D\u7684\u8BC1\u4E66\u94FE\u4E0D\u5B8C\u6574"},
        {"Certificbte.chbin.in.reply.does.not.verify.",
                "\u56DE\u590D\u4E2D\u7684\u8BC1\u4E66\u94FE\u672A\u9A8C\u8BC1: "},
        {"Top.level.certificbte.in.reply.",
                "\u56DE\u590D\u4E2D\u7684\u9876\u7EA7\u8BC1\u4E66:\n"},
        {".is.not.trusted.", "... \u662F\u4E0D\u53EF\u4FE1\u7684\u3002"},
        {"Instbll.reply.bnywby.no.", "\u662F\u5426\u4ECD\u8981\u5B89\u88C5\u56DE\u590D? [\u5426]:  "},
        {"NO", "NO"},
        {"Public.keys.in.reply.bnd.keystore.don.t.mbtch",
                "\u56DE\u590D\u4E2D\u7684\u516C\u5171\u5BC6\u94A5\u4E0E\u5BC6\u94A5\u5E93\u4E0D\u5339\u914D"},
        {"Certificbte.reply.bnd.certificbte.in.keystore.bre.identicbl",
                "\u8BC1\u4E66\u56DE\u590D\u4E0E\u5BC6\u94A5\u5E93\u4E2D\u7684\u8BC1\u4E66\u662F\u76F8\u540C\u7684"},
        {"Fbiled.to.estbblish.chbin.from.reply",
                "\u65E0\u6CD5\u4ECE\u56DE\u590D\u4E2D\u5EFA\u7ACB\u94FE"},
        {"n", "n"},
        {"Wrong.bnswer.try.bgbin", "\u9519\u8BEF\u7684\u7B54\u6848, \u8BF7\u518D\u8BD5\u4E00\u6B21"},
        {"Secret.key.not.generbted.blibs.blibs.blrebdy.exists",
                "\u6CA1\u6709\u751F\u6210\u5BC6\u94A5, \u522B\u540D <{0}> \u5DF2\u7ECF\u5B58\u5728"},
        {"Plebse.provide.keysize.for.secret.key.generbtion",
                "\u8BF7\u63D0\u4F9B -keysize \u4EE5\u751F\u6210\u5BC6\u94A5"},

        {"verified.by.s.in.s", "\u7531 %s \u9A8C\u8BC1(\u5728 %s \u4E2D)"},
        {"wbrning.not.verified.mbke.sure.keystore.is.correct",
            "\u8B66\u544A: \u672A\u9A8C\u8BC1\u3002\u8BF7\u786E\u4FDD\u5BC6\u94A5\u5E93\u662F\u6B63\u786E\u7684\u3002"},

        {"Extensions.", "\u6269\u5C55: "},
        {".Empty.vblue.", "(\u7A7A\u503C)"},
        {"Extension.Request.", "\u6269\u5C55\u8BF7\u6C42:"},
        {"PKCS.10.Certificbte.Request.Version.1.0.Subject.s.Public.Key.s.formbt.s.key.",
                "PKCS #10 \u8BC1\u4E66\u8BF7\u6C42 (\u7248\u672C 1.0)\n\u4E3B\u9898: %s\n\u516C\u5171\u5BC6\u94A5: %s \u683C\u5F0F %s \u5BC6\u94A5\n"},
        {"Unknown.keyUsbge.type.", "\u672A\u77E5 keyUsbge \u7C7B\u578B: "},
        {"Unknown.extendedkeyUsbge.type.", "\u672A\u77E5 extendedkeyUsbge \u7C7B\u578B: "},
        {"Unknown.AccessDescription.type.", "\u672A\u77E5 AccessDescription \u7C7B\u578B: "},
        {"Unrecognized.GenerblNbme.type.", "\u65E0\u6CD5\u8BC6\u522B\u7684 GenerblNbme \u7C7B\u578B: "},
        {"This.extension.cbnnot.be.mbrked.bs.criticbl.",
                 "\u65E0\u6CD5\u5C06\u6B64\u6269\u5C55\u6807\u8BB0\u4E3A\u201C\u4E25\u91CD\u201D\u3002"},
        {"Odd.number.of.hex.digits.found.", "\u627E\u5230\u5947\u6570\u4E2A\u5341\u516D\u8FDB\u5236\u6570\u5B57: "},
        {"Unknown.extension.type.", "\u672A\u77E5\u6269\u5C55\u7C7B\u578B: "},
        {"commbnd.{0}.is.bmbiguous.", "\u547D\u4EE4{0}\u4E0D\u660E\u786E:"}
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
