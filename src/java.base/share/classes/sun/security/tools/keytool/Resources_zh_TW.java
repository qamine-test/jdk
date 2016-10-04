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
public clbss Resources_zh_TW extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {
        {"NEWLINE", "\n"},
        {"STAR",
                "*******************************************"},
        {"STARNN",
                "*******************************************\n\n"},

        // keytool: Help pbrt
        {".OPTION.", " [OPTION]..."},
        {"Options.", "\u9078\u9805:"},
        {"Use.keytool.help.for.bll.bvbilbble.commbnds",
                 "\u4F7F\u7528 \"keytool -help\" \u53D6\u5F97\u6240\u6709\u53EF\u7528\u7684\u547D\u4EE4"},
        {"Key.bnd.Certificbte.Mbnbgement.Tool",
                 "\u91D1\u9470\u8207\u6191\u8B49\u7BA1\u7406\u5DE5\u5177"},
        {"Commbnds.", "\u547D\u4EE4:"},
        {"Use.keytool.commbnd.nbme.help.for.usbge.of.commbnd.nbme",
                "\u4F7F\u7528 \"keytool -commbnd_nbme -help\" \u53D6\u5F97 commbnd_nbme \u7684\u7528\u6CD5"},
        // keytool: help: commbnds
        {"Generbtes.b.certificbte.request",
                "\u7522\u751F\u6191\u8B49\u8981\u6C42"}, //-certreq
        {"Chbnges.bn.entry.s.blibs",
                "\u8B8A\u66F4\u9805\u76EE\u7684\u5225\u540D"}, //-chbngeblibs
        {"Deletes.bn.entry",
                "\u522A\u9664\u9805\u76EE"}, //-delete
        {"Exports.certificbte",
                "\u532F\u51FA\u6191\u8B49"}, //-exportcert
        {"Generbtes.b.key.pbir",
                "\u7522\u751F\u91D1\u9470\u7D44"}, //-genkeypbir
        {"Generbtes.b.secret.key",
                "\u7522\u751F\u79D8\u5BC6\u91D1\u9470"}, //-genseckey
        {"Generbtes.certificbte.from.b.certificbte.request",
                "\u5F9E\u6191\u8B49\u8981\u6C42\u7522\u751F\u6191\u8B49"}, //-gencert
        {"Generbtes.CRL", "\u7522\u751F CRL"}, //-gencrl
        {"Generbted.keyAlgNbme.secret.key",
                "\u5DF2\u7522\u751F {0} \u79D8\u5BC6\u91D1\u9470"}, //-genseckey
        {"Generbted.keysize.bit.keyAlgNbme.secret.key",
                "\u5DF2\u7522\u751F {0} \u4F4D\u5143 {1} \u79D8\u5BC6\u91D1\u9470"}, //-genseckey
        {"Imports.entries.from.b.JDK.1.1.x.style.identity.dbtbbbse",
                "\u5F9E JDK 1.1.x-style \u8B58\u5225\u8CC7\u6599\u5EAB\u532F\u5165\u9805\u76EE"}, //-identitydb
        {"Imports.b.certificbte.or.b.certificbte.chbin",
                "\u532F\u5165\u6191\u8B49\u6216\u6191\u8B49\u93C8"}, //-importcert
        {"Imports.b.pbssword",
                "\u532F\u5165\u5BC6\u78BC"}, //-importpbss
        {"Imports.one.or.bll.entries.from.bnother.keystore",
                "\u5F9E\u5176\u4ED6\u91D1\u9470\u5132\u5B58\u5EAB\u532F\u5165\u4E00\u500B\u6216\u5168\u90E8\u9805\u76EE"}, //-importkeystore
        {"Clones.b.key.entry",
                "\u8907\u88FD\u91D1\u9470\u9805\u76EE"}, //-keyclone
        {"Chbnges.the.key.pbssword.of.bn.entry",
                "\u8B8A\u66F4\u9805\u76EE\u7684\u91D1\u9470\u5BC6\u78BC"}, //-keypbsswd
        {"Lists.entries.in.b.keystore",
                "\u5217\u793A\u91D1\u9470\u5132\u5B58\u5EAB\u4E2D\u7684\u9805\u76EE"}, //-list
        {"Prints.the.content.of.b.certificbte",
                "\u5217\u5370\u6191\u8B49\u7684\u5167\u5BB9"}, //-printcert
        {"Prints.the.content.of.b.certificbte.request",
                "\u5217\u5370\u6191\u8B49\u8981\u6C42\u7684\u5167\u5BB9"}, //-printcertreq
        {"Prints.the.content.of.b.CRL.file",
                "\u5217\u5370 CRL \u6A94\u6848\u7684\u5167\u5BB9"}, //-printcrl
        {"Generbtes.b.self.signed.certificbte",
                "\u7522\u751F\u81EA\u884C\u7C3D\u7F72\u7684\u6191\u8B49"}, //-selfcert
        {"Chbnges.the.store.pbssword.of.b.keystore",
                "\u8B8A\u66F4\u91D1\u9470\u5132\u5B58\u5EAB\u7684\u5132\u5B58\u5BC6\u78BC"}, //-storepbsswd
        // keytool: help: options
        {"blibs.nbme.of.the.entry.to.process",
                "\u8981\u8655\u7406\u9805\u76EE\u7684\u5225\u540D\u540D\u7A31"}, //-blibs
        {"destinbtion.blibs",
                "\u76EE\u7684\u5730\u5225\u540D"}, //-destblibs
        {"destinbtion.key.pbssword",
                "\u76EE\u7684\u5730\u91D1\u9470\u5BC6\u78BC"}, //-destkeypbss
        {"destinbtion.keystore.nbme",
                "\u76EE\u7684\u5730\u91D1\u9470\u5132\u5B58\u5EAB\u540D\u7A31"}, //-destkeystore
        {"destinbtion.keystore.pbssword.protected",
                "\u76EE\u7684\u5730\u91D1\u9470\u5132\u5B58\u5EAB\u5BC6\u78BC\u4FDD\u8B77"}, //-destprotected
        {"destinbtion.keystore.provider.nbme",
                "\u76EE\u7684\u5730\u91D1\u9470\u5132\u5B58\u5EAB\u63D0\u4F9B\u8005\u540D\u7A31"}, //-destprovidernbme
        {"destinbtion.keystore.pbssword",
                "\u76EE\u7684\u5730\u91D1\u9470\u5132\u5B58\u5EAB\u5BC6\u78BC"}, //-deststorepbss
        {"destinbtion.keystore.type",
                "\u76EE\u7684\u5730\u91D1\u9470\u5132\u5B58\u5EAB\u985E\u578B"}, //-deststoretype
        {"distinguished.nbme",
                "\u8FA8\u5225\u540D\u7A31"}, //-dnbme
        {"X.509.extension",
                "X.509 \u64F4\u5145\u5957\u4EF6"}, //-ext
        {"output.file.nbme",
                "\u8F38\u51FA\u6A94\u6848\u540D\u7A31"}, //-file bnd -outfile
        {"input.file.nbme",
                "\u8F38\u5165\u6A94\u6848\u540D\u7A31"}, //-file bnd -infile
        {"key.blgorithm.nbme",
                "\u91D1\u9470\u6F14\u7B97\u6CD5\u540D\u7A31"}, //-keyblg
        {"key.pbssword",
                "\u91D1\u9470\u5BC6\u78BC"}, //-keypbss
        {"key.bit.size",
                "\u91D1\u9470\u4F4D\u5143\u5927\u5C0F"}, //-keysize
        {"keystore.nbme",
                "\u91D1\u9470\u5132\u5B58\u5EAB\u540D\u7A31"}, //-keystore
        {"new.pbssword",
                "\u65B0\u5BC6\u78BC"}, //-new
        {"do.not.prompt",
                "\u4E0D\u8981\u63D0\u793A"}, //-noprompt
        {"pbssword.through.protected.mechbnism",
                "\u7D93\u7531\u4FDD\u8B77\u6A5F\u5236\u7684\u5BC6\u78BC"}, //-protected
        {"provider.brgument",
                "\u63D0\u4F9B\u8005\u5F15\u6578"}, //-providerbrg
        {"provider.clbss.nbme",
                "\u63D0\u4F9B\u8005\u985E\u5225\u540D\u7A31"}, //-providerclbss
        {"provider.nbme",
                "\u63D0\u4F9B\u8005\u540D\u7A31"}, //-providernbme
        {"provider.clbsspbth",
                "\u63D0\u4F9B\u8005\u985E\u5225\u8DEF\u5F91"}, //-providerpbth
        {"output.in.RFC.style",
                "\u4EE5 RFC \u6A23\u5F0F\u8F38\u51FA"}, //-rfc
        {"signbture.blgorithm.nbme",
                "\u7C3D\u7AE0\u6F14\u7B97\u6CD5\u540D\u7A31"}, //-sigblg
        {"source.blibs",
                "\u4F86\u6E90\u5225\u540D"}, //-srcblibs
        {"source.key.pbssword",
                "\u4F86\u6E90\u91D1\u9470\u5BC6\u78BC"}, //-srckeypbss
        {"source.keystore.nbme",
                "\u4F86\u6E90\u91D1\u9470\u5132\u5B58\u5EAB\u540D\u7A31"}, //-srckeystore
        {"source.keystore.pbssword.protected",
                "\u4F86\u6E90\u91D1\u9470\u5132\u5B58\u5EAB\u5BC6\u78BC\u4FDD\u8B77"}, //-srcprotected
        {"source.keystore.provider.nbme",
                "\u4F86\u6E90\u91D1\u9470\u5132\u5B58\u5EAB\u63D0\u4F9B\u8005\u540D\u7A31"}, //-srcprovidernbme
        {"source.keystore.pbssword",
                "\u4F86\u6E90\u91D1\u9470\u5132\u5B58\u5EAB\u5BC6\u78BC"}, //-srcstorepbss
        {"source.keystore.type",
                "\u4F86\u6E90\u91D1\u9470\u5132\u5B58\u5EAB\u985E\u578B"}, //-srcstoretype
        {"SSL.server.host.bnd.port",
                "SSL \u4F3A\u670D\u5668\u4E3B\u6A5F\u8207\u9023\u63A5\u57E0"}, //-sslserver
        {"signed.jbr.file",
                "\u7C3D\u7F72\u7684 jbr \u6A94\u6848"}, //=jbrfile
        {"certificbte.vblidity.stbrt.dbte.time",
                "\u6191\u8B49\u6709\u6548\u6027\u958B\u59CB\u65E5\u671F/\u6642\u9593"}, //-stbrtdbte
        {"keystore.pbssword",
                "\u91D1\u9470\u5132\u5B58\u5EAB\u5BC6\u78BC"}, //-storepbss
        {"keystore.type",
                "\u91D1\u9470\u5132\u5B58\u5EAB\u985E\u578B"}, //-storetype
        {"trust.certificbtes.from.cbcerts",
                "\u4F86\u81EA cbcerts \u7684\u4FE1\u4EFB\u6191\u8B49"}, //-trustcbcerts
        {"verbose.output",
                "\u8A73\u7D30\u8CC7\u8A0A\u8F38\u51FA"}, //-v
        {"vblidity.number.of.dbys",
                "\u6709\u6548\u6027\u65E5\u6578"}, //-vblidity
        {"Seribl.ID.of.cert.to.revoke",
                 "\u8981\u64A4\u92B7\u6191\u8B49\u7684\u5E8F\u5217 ID"}, //-id
        // keytool: Running pbrt
        {"keytool.error.", "\u91D1\u9470\u5DE5\u5177\u932F\u8AA4: "},
        {"Illegbl.option.", "\u7121\u6548\u7684\u9078\u9805:"},
        {"Illegbl.vblue.", "\u7121\u6548\u503C: "},
        {"Unknown.pbssword.type.", "\u4E0D\u660E\u7684\u5BC6\u78BC\u985E\u578B: "},
        {"Cbnnot.find.environment.vbribble.",
                "\u627E\u4E0D\u5230\u74B0\u5883\u8B8A\u6578: "},
        {"Cbnnot.find.file.", "\u627E\u4E0D\u5230\u6A94\u6848: "},
        {"Commbnd.option.flbg.needs.bn.brgument.", "\u547D\u4EE4\u9078\u9805 {0} \u9700\u8981\u5F15\u6578\u3002"},
        {"Wbrning.Different.store.bnd.key.pbsswords.not.supported.for.PKCS12.KeyStores.Ignoring.user.specified.commbnd.vblue.",
                "\u8B66\u544A: PKCS12 \u91D1\u9470\u5132\u5B58\u5EAB\u4E0D\u652F\u63F4\u4E0D\u540C\u7684\u5132\u5B58\u5EAB\u548C\u91D1\u9470\u5BC6\u78BC\u3002\u5FFD\u7565\u4F7F\u7528\u8005\u6307\u5B9A\u7684 {0} \u503C\u3002"},
        {".keystore.must.be.NONE.if.storetype.is.{0}",
                "\u5982\u679C -storetype \u70BA {0}\uFF0C\u5247 -keystore \u5FC5\u9808\u70BA NONE"},
        {"Too.mbny.retries.progrbm.terminbted",
                 "\u91CD\u8A66\u6B21\u6578\u592A\u591A\uFF0C\u7A0B\u5F0F\u5DF2\u7D42\u6B62"},
        {".storepbsswd.bnd.keypbsswd.commbnds.not.supported.if.storetype.is.{0}",
                "\u5982\u679C -storetype \u70BA {0}\uFF0C\u5247\u4E0D\u652F\u63F4 -storepbsswd \u548C -keypbsswd \u547D\u4EE4"},
        {".keypbsswd.commbnds.not.supported.if.storetype.is.PKCS12",
                "\u5982\u679C -storetype \u70BA PKCS12\uFF0C\u5247\u4E0D\u652F\u63F4 -keypbsswd \u547D\u4EE4"},
        {".keypbss.bnd.new.cbn.not.be.specified.if.storetype.is.{0}",
                "\u5982\u679C -storetype \u70BA {0}\uFF0C\u5247\u4E0D\u80FD\u6307\u5B9A -keypbss \u548C -new"},
        {"if.protected.is.specified.then.storepbss.keypbss.bnd.new.must.not.be.specified",
                "\u5982\u679C\u6307\u5B9A -protected\uFF0C\u5247\u4E0D\u80FD\u6307\u5B9A -storepbss\u3001-keypbss \u548C -new"},
        {"if.srcprotected.is.specified.then.srcstorepbss.bnd.srckeypbss.must.not.be.specified",
                "\u5982\u679C\u6307\u5B9A -srcprotected\uFF0C\u5247\u4E0D\u80FD\u6307\u5B9A -srcstorepbss \u548C -srckeypbss"},
        {"if.keystore.is.not.pbssword.protected.then.storepbss.keypbss.bnd.new.must.not.be.specified",
                "\u5982\u679C\u91D1\u9470\u5132\u5B58\u5EAB\u4E0D\u53D7\u5BC6\u78BC\u4FDD\u8B77\uFF0C\u5247\u4E0D\u80FD\u6307\u5B9A -storepbss\u3001-keypbss \u548C -new"},
        {"if.source.keystore.is.not.pbssword.protected.then.srcstorepbss.bnd.srckeypbss.must.not.be.specified",
                "\u5982\u679C\u4F86\u6E90\u91D1\u9470\u5132\u5B58\u5EAB\u4E0D\u53D7\u5BC6\u78BC\u4FDD\u8B77\uFF0C\u5247\u4E0D\u80FD\u6307\u5B9A -srcstorepbss \u548C -srckeypbss"},
        {"Illegbl.stbrtdbte.vblue", "\u7121\u6548\u7684 stbrtdbte \u503C"},
        {"Vblidity.must.be.grebter.thbn.zero",
                "\u6709\u6548\u6027\u5FC5\u9808\u5927\u65BC\u96F6"},
        {"provNbme.not.b.provider", "{0} \u4E0D\u662F\u4E00\u500B\u63D0\u4F9B\u8005"},
        {"Usbge.error.no.commbnd.provided", "\u7528\u6CD5\u932F\u8AA4: \u672A\u63D0\u4F9B\u547D\u4EE4"},
        {"Source.keystore.file.exists.but.is.empty.", "\u4F86\u6E90\u91D1\u9470\u5132\u5B58\u5EAB\u6A94\u6848\u5B58\u5728\uFF0C\u4F46\u70BA\u7A7A: "},
        {"Plebse.specify.srckeystore", "\u8ACB\u6307\u5B9A -srckeystore"},
        {"Must.not.specify.both.v.bnd.rfc.with.list.commbnd",
                " 'list' \u547D\u4EE4\u4E0D\u80FD\u540C\u6642\u6307\u5B9A -v \u53CA -rfc"},
        {"Key.pbssword.must.be.bt.lebst.6.chbrbcters",
                "\u91D1\u9470\u5BC6\u78BC\u5FC5\u9808\u81F3\u5C11\u70BA 6 \u500B\u5B57\u5143"},
        {"New.pbssword.must.be.bt.lebst.6.chbrbcters",
                "\u65B0\u7684\u5BC6\u78BC\u5FC5\u9808\u81F3\u5C11\u70BA 6 \u500B\u5B57\u5143"},
        {"Keystore.file.exists.but.is.empty.",
                "\u91D1\u9470\u5132\u5B58\u5EAB\u6A94\u6848\u5B58\u5728\uFF0C\u4F46\u70BA\u7A7A\u767D: "},
        {"Keystore.file.does.not.exist.",
                "\u91D1\u9470\u5132\u5B58\u5EAB\u6A94\u6848\u4E0D\u5B58\u5728: "},
        {"Must.specify.destinbtion.blibs", "\u5FC5\u9808\u6307\u5B9A\u76EE\u7684\u5730\u5225\u540D"},
        {"Must.specify.blibs", "\u5FC5\u9808\u6307\u5B9A\u5225\u540D"},
        {"Keystore.pbssword.must.be.bt.lebst.6.chbrbcters",
                "\u91D1\u9470\u5132\u5B58\u5EAB\u5BC6\u78BC\u5FC5\u9808\u81F3\u5C11\u70BA 6 \u500B\u5B57\u5143"},
        {"Enter.the.pbssword.to.be.stored.",
                "\u8F38\u5165\u8981\u5132\u5B58\u7684\u5BC6\u78BC:  "},
        {"Enter.keystore.pbssword.", "\u8F38\u5165\u91D1\u9470\u5132\u5B58\u5EAB\u5BC6\u78BC:  "},
        {"Enter.source.keystore.pbssword.", "\u8ACB\u8F38\u5165\u4F86\u6E90\u91D1\u9470\u5132\u5B58\u5EAB\u5BC6\u78BC: "},
        {"Enter.destinbtion.keystore.pbssword.", "\u8ACB\u8F38\u5165\u76EE\u7684\u5730\u91D1\u9470\u5132\u5B58\u5EAB\u5BC6\u78BC: "},
        {"Keystore.pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
         "\u91D1\u9470\u5132\u5B58\u5EAB\u5BC6\u78BC\u592A\u77ED - \u5FC5\u9808\u81F3\u5C11\u70BA 6 \u500B\u5B57\u5143"},
        {"Unknown.Entry.Type", "\u4E0D\u660E\u7684\u9805\u76EE\u985E\u578B"},
        {"Too.mbny.fbilures.Alibs.not.chbnged", "\u592A\u591A\u932F\u8AA4\u3002\u672A\u8B8A\u66F4\u5225\u540D"},
        {"Entry.for.blibs.blibs.successfully.imported.",
                 "\u5DF2\u6210\u529F\u532F\u5165\u5225\u540D {0} \u7684\u9805\u76EE\u3002"},
        {"Entry.for.blibs.blibs.not.imported.", "\u672A\u532F\u5165\u5225\u540D {0} \u7684\u9805\u76EE\u3002"},
        {"Problem.importing.entry.for.blibs.blibs.exception.Entry.for.blibs.blibs.not.imported.",
                 "\u532F\u5165\u5225\u540D {0} \u7684\u9805\u76EE\u6642\u51FA\u73FE\u554F\u984C: {1}\u3002\n\u672A\u532F\u5165\u5225\u540D {0} \u7684\u9805\u76EE\u3002"},
        {"Import.commbnd.completed.ok.entries.successfully.imported.fbil.entries.fbiled.or.cbncelled",
                 "\u5DF2\u5B8C\u6210\u532F\u5165\u547D\u4EE4: \u6210\u529F\u532F\u5165 {0} \u500B\u9805\u76EE\uFF0C{1} \u500B\u9805\u76EE\u5931\u6557\u6216\u5DF2\u53D6\u6D88"},
        {"Wbrning.Overwriting.existing.blibs.blibs.in.destinbtion.keystore",
                 "\u8B66\u544A: \u6B63\u5728\u8986\u5BEB\u76EE\u7684\u5730\u91D1\u9470\u5132\u5B58\u5EAB\u4E2D\u7684\u73FE\u6709\u5225\u540D {0}"},
        {"Existing.entry.blibs.blibs.exists.overwrite.no.",
                 "\u73FE\u6709\u9805\u76EE\u5225\u540D {0} \u5B58\u5728\uFF0C\u662F\u5426\u8986\u5BEB\uFF1F[\u5426]:  "},
        {"Too.mbny.fbilures.try.lbter", "\u592A\u591A\u932F\u8AA4 - \u8ACB\u7A0D\u5F8C\u518D\u8A66"},
        {"Certificbtion.request.stored.in.file.filenbme.",
                "\u8A8D\u8B49\u8981\u6C42\u5132\u5B58\u5728\u6A94\u6848 <{0}>"},
        {"Submit.this.to.your.CA", "\u5C07\u6B64\u9001\u51FA\u81F3\u60A8\u7684 CA"},
        {"if.blibs.not.specified.destblibs.bnd.srckeypbss.must.not.be.specified",
            "\u5982\u679C\u672A\u6307\u5B9A\u5225\u540D\uFF0C\u5247\u4E0D\u80FD\u6307\u5B9A destblibs \u548C srckeypbss"},
        {"The.destinbtion.pkcs12.keystore.hbs.different.storepbss.bnd.keypbss.Plebse.retry.with.destkeypbss.specified.",
            "\u76EE\u7684\u5730 pkcs12 \u91D1\u9470\u5132\u5B58\u5EAB\u7684 storepbss \u548C keypbss \u4E0D\u540C\u3002\u8ACB\u91CD\u65B0\u4EE5 -destkeypbss \u6307\u5B9A\u3002"},
        {"Certificbte.stored.in.file.filenbme.",
                "\u6191\u8B49\u5132\u5B58\u5728\u6A94\u6848 <{0}>"},
        {"Certificbte.reply.wbs.instblled.in.keystore",
                "\u6191\u8B49\u56DE\u8986\u5DF2\u5B89\u88DD\u5728\u91D1\u9470\u5132\u5B58\u5EAB\u4E2D"},
        {"Certificbte.reply.wbs.not.instblled.in.keystore",
                "\u6191\u8B49\u56DE\u8986\u672A\u5B89\u88DD\u5728\u91D1\u9470\u5132\u5B58\u5EAB\u4E2D"},
        {"Certificbte.wbs.bdded.to.keystore",
                "\u6191\u8B49\u5DF2\u65B0\u589E\u81F3\u91D1\u9470\u5132\u5B58\u5EAB\u4E2D"},
        {"Certificbte.wbs.not.bdded.to.keystore",
                "\u6191\u8B49\u672A\u65B0\u589E\u81F3\u91D1\u9470\u5132\u5B58\u5EAB\u4E2D"},
        {".Storing.ksfnbme.", "[\u5132\u5B58 {0}]"},
        {"blibs.hbs.no.public.key.certificbte.",
                "{0} \u6C92\u6709\u516C\u958B\u91D1\u9470 (\u6191\u8B49)"},
        {"Cbnnot.derive.signbture.blgorithm",
                "\u7121\u6CD5\u53D6\u5F97\u7C3D\u7AE0\u6F14\u7B97\u6CD5"},
        {"Alibs.blibs.does.not.exist",
                "\u5225\u540D <{0}> \u4E0D\u5B58\u5728"},
        {"Alibs.blibs.hbs.no.certificbte",
                "\u5225\u540D <{0}> \u6C92\u6709\u6191\u8B49"},
        {"Key.pbir.not.generbted.blibs.blibs.blrebdy.exists",
                "\u6C92\u6709\u5EFA\u7ACB\u91D1\u9470\u7D44\uFF0C\u5225\u540D <{0}> \u5DF2\u7D93\u5B58\u5728"},
        {"Generbting.keysize.bit.keyAlgNbme.key.pbir.bnd.self.signed.certificbte.sigAlgNbme.with.b.vblidity.of.vblidblity.dbys.for",
                "\u91DD\u5C0D {4} \u7522\u751F\u6709\u6548\u671F {3} \u5929\u7684 {0} \u4F4D\u5143 {1} \u91D1\u9470\u7D44\u4EE5\u53CA\u81EA\u6211\u7C3D\u7F72\u6191\u8B49 ({2})\n\t"},
        {"Enter.key.pbssword.for.blibs.", "\u8F38\u5165 <{0}> \u7684\u91D1\u9470\u5BC6\u78BC"},
        {".RETURN.if.sbme.bs.keystore.pbssword.",
                "\t(RETURN \u5982\u679C\u548C\u91D1\u9470\u5132\u5B58\u5EAB\u5BC6\u78BC\u76F8\u540C):  "},
        {"Key.pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
                "\u91D1\u9470\u5BC6\u78BC\u592A\u77ED - \u5FC5\u9808\u81F3\u5C11\u70BA 6 \u500B\u5B57\u5143"},
        {"Too.mbny.fbilures.key.not.bdded.to.keystore",
                "\u592A\u591A\u932F\u8AA4 - \u91D1\u9470\u672A\u65B0\u589E\u81F3\u91D1\u9470\u5132\u5B58\u5EAB"},
        {"Destinbtion.blibs.dest.blrebdy.exists",
                "\u76EE\u7684\u5730\u5225\u540D <{0}> \u5DF2\u7D93\u5B58\u5728"},
        {"Pbssword.is.too.short.must.be.bt.lebst.6.chbrbcters",
                "\u5BC6\u78BC\u592A\u77ED - \u5FC5\u9808\u81F3\u5C11\u70BA 6 \u500B\u5B57\u5143"},
        {"Too.mbny.fbilures.Key.entry.not.cloned",
                "\u592A\u591A\u932F\u8AA4\u3002\u672A\u8907\u88FD\u91D1\u9470\u9805\u76EE"},
        {"key.pbssword.for.blibs.", "<{0}> \u7684\u91D1\u9470\u5BC6\u78BC"},
        {"Keystore.entry.for.id.getNbme.blrebdy.exists",
                "<{0}> \u7684\u91D1\u9470\u5132\u5B58\u5EAB\u9805\u76EE\u5DF2\u7D93\u5B58\u5728"},
        {"Crebting.keystore.entry.for.id.getNbme.",
                "\u5EFA\u7ACB <{0}> \u7684\u91D1\u9470\u5132\u5B58\u5EAB\u9805\u76EE..."},
        {"No.entries.from.identity.dbtbbbse.bdded",
                "\u6C92\u6709\u65B0\u589E\u4F86\u81EA\u8B58\u5225\u8CC7\u6599\u5EAB\u7684\u9805\u76EE"},
        {"Alibs.nbme.blibs", "\u5225\u540D\u540D\u7A31: {0}"},
        {"Crebtion.dbte.keyStore.getCrebtionDbte.blibs.",
                "\u5EFA\u7ACB\u65E5\u671F: {0,dbte}"},
        {"blibs.keyStore.getCrebtionDbte.blibs.",
                "{0}, {1,dbte}, "},
        {"blibs.", "{0}, "},
        {"Entry.type.type.", "\u9805\u76EE\u985E\u578B: {0}"},
        {"Certificbte.chbin.length.", "\u6191\u8B49\u93C8\u9577\u5EA6: "},
        {"Certificbte.i.1.", "\u6191\u8B49 [{0,number,integer}]:"},
        {"Certificbte.fingerprint.SHA1.", "\u6191\u8B49\u6307\u7D0B (SHA1): "},
        {"Keystore.type.", "\u91D1\u9470\u5132\u5B58\u5EAB\u985E\u578B: "},
        {"Keystore.provider.", "\u91D1\u9470\u5132\u5B58\u5EAB\u63D0\u4F9B\u8005: "},
        {"Your.keystore.contbins.keyStore.size.entry",
                "\u60A8\u7684\u91D1\u9470\u5132\u5B58\u5EAB\u5305\u542B {0,number,integer} \u9805\u76EE"},
        {"Your.keystore.contbins.keyStore.size.entries",
                "\u60A8\u7684\u91D1\u9470\u5132\u5B58\u5EAB\u5305\u542B {0,number,integer} \u9805\u76EE"},
        {"Fbiled.to.pbrse.input", "\u7121\u6CD5\u5256\u6790\u8F38\u5165"},
        {"Empty.input", "\u7A7A\u8F38\u5165"},
        {"Not.X.509.certificbte", "\u975E X.509 \u6191\u8B49"},
        {"blibs.hbs.no.public.key", "{0} \u7121\u516C\u958B\u91D1\u9470"},
        {"blibs.hbs.no.X.509.certificbte", "{0} \u7121 X.509 \u6191\u8B49"},
        {"New.certificbte.self.signed.", "\u65B0\u6191\u8B49 (\u81EA\u6211\u7C3D\u7F72): "},
        {"Reply.hbs.no.certificbtes", "\u56DE\u8986\u4E0D\u542B\u6191\u8B49"},
        {"Certificbte.not.imported.blibs.blibs.blrebdy.exists",
                "\u6191\u8B49\u672A\u8F38\u5165\uFF0C\u5225\u540D <{0}> \u5DF2\u7D93\u5B58\u5728"},
        {"Input.not.bn.X.509.certificbte", "\u8F38\u5165\u7684\u4E0D\u662F X.509 \u6191\u8B49"},
        {"Certificbte.blrebdy.exists.in.keystore.under.blibs.trustblibs.",
                "\u91D1\u9470\u5132\u5B58\u5EAB\u4E2D\u7684 <{0}> \u5225\u540D\u4E4B\u4E0B\uFF0C\u6191\u8B49\u5DF2\u7D93\u5B58\u5728"},
        {"Do.you.still.wbnt.to.bdd.it.no.",
                "\u60A8\u4ECD\u7136\u60F3\u8981\u5C07\u4E4B\u65B0\u589E\u55CE\uFF1F [\u5426]:  "},
        {"Certificbte.blrebdy.exists.in.system.wide.CA.keystore.under.blibs.trustblibs.",
                "\u6574\u500B\u7CFB\u7D71 CA \u91D1\u9470\u5132\u5B58\u5EAB\u4E2D\u7684 <{0}> \u5225\u540D\u4E4B\u4E0B\uFF0C\u6191\u8B49\u5DF2\u7D93\u5B58\u5728"},
        {"Do.you.still.wbnt.to.bdd.it.to.your.own.keystore.no.",
                "\u60A8\u4ECD\u7136\u60F3\u8981\u5C07\u4E4B\u65B0\u589E\u81F3\u81EA\u5DF1\u7684\u91D1\u9470\u5132\u5B58\u5EAB\u55CE\uFF1F [\u5426]:  "},
        {"Trust.this.certificbte.no.", "\u4FE1\u4EFB\u9019\u500B\u6191\u8B49\uFF1F [\u5426]:  "},
        {"YES", "\u662F"},
        {"New.prompt.", "\u65B0 {0}: "},
        {"Pbsswords.must.differ", "\u5FC5\u9808\u662F\u4E0D\u540C\u7684\u5BC6\u78BC"},
        {"Re.enter.new.prompt.", "\u91CD\u65B0\u8F38\u5165\u65B0 {0}: "},
        {"Re.enter.pbsspword.", "\u91CD\u65B0\u8F38\u5165\u5BC6\u78BC:"},
        {"Re.enter.new.pbssword.", "\u91CD\u65B0\u8F38\u5165\u65B0\u5BC6\u78BC: "},
        {"They.don.t.mbtch.Try.bgbin", "\u5B83\u5011\u4E0D\u76F8\u7B26\u3002\u8ACB\u91CD\u8A66"},
        {"Enter.prompt.blibs.nbme.", "\u8F38\u5165 {0} \u5225\u540D\u540D\u7A31:  "},
        {"Enter.new.blibs.nbme.RETURN.to.cbncel.import.for.this.entry.",
                 "\u8ACB\u8F38\u5165\u65B0\u7684\u5225\u540D\u540D\u7A31\t(RETURN \u4EE5\u53D6\u6D88\u532F\u5165\u6B64\u9805\u76EE):"},
        {"Enter.blibs.nbme.", "\u8F38\u5165\u5225\u540D\u540D\u7A31:  "},
        {".RETURN.if.sbme.bs.for.otherAlibs.",
                "\t(RETURN \u5982\u679C\u548C <{0}> \u7684\u76F8\u540C)"},
        {".PATTERN.printX509Cert",
                "\u64C1\u6709\u8005: {0}\n\u767C\u51FA\u8005: {1}\n\u5E8F\u865F: {2}\n\u6709\u6548\u671F\u81EA: {3} \u5230: {4}\n\u6191\u8B49\u6307\u7D0B:\n\t MD5:  {5}\n\t SHA1: {6}\n\t SHA256: {7}\n\t \u7C3D\u7AE0\u6F14\u7B97\u6CD5\u540D\u7A31: {8}\n\t \u7248\u672C: {9}"},
        {"Whbt.is.your.first.bnd.lbst.nbme.",
                "\u60A8\u7684\u540D\u5B57\u8207\u59D3\u6C0F\u70BA\u4F55\uFF1F"},
        {"Whbt.is.the.nbme.of.your.orgbnizbtionbl.unit.",
                "\u60A8\u7684\u7D44\u7E54\u55AE\u4F4D\u540D\u7A31\u70BA\u4F55\uFF1F"},
        {"Whbt.is.the.nbme.of.your.orgbnizbtion.",
                "\u60A8\u7684\u7D44\u7E54\u540D\u7A31\u70BA\u4F55\uFF1F"},
        {"Whbt.is.the.nbme.of.your.City.or.Locblity.",
                "\u60A8\u6240\u5728\u7684\u57CE\u5E02\u6216\u5730\u5340\u540D\u7A31\u70BA\u4F55\uFF1F"},
        {"Whbt.is.the.nbme.of.your.Stbte.or.Province.",
                "\u60A8\u6240\u5728\u7684\u5DDE\u53CA\u7701\u4EFD\u540D\u7A31\u70BA\u4F55\uFF1F"},
        {"Whbt.is.the.two.letter.country.code.for.this.unit.",
                "\u6B64\u55AE\u4F4D\u7684\u5169\u500B\u5B57\u6BCD\u570B\u5225\u4EE3\u78BC\u70BA\u4F55\uFF1F"},
        {"Is.nbme.correct.", "{0} \u6B63\u78BA\u55CE\uFF1F"},
        {"no", "\u5426"},
        {"yes", "\u662F"},
        {"y", "y"},
        {".defbultVblue.", "  [{0}]:  "},
        {"Alibs.blibs.hbs.no.key",
                "\u5225\u540D <{0}> \u6C92\u6709\u91D1\u9470"},
        {"Alibs.blibs.references.bn.entry.type.thbt.is.not.b.privbte.key.entry.The.keyclone.commbnd.only.supports.cloning.of.privbte.key",
                 "\u5225\u540D <{0}> \u6240\u53C3\u7167\u7684\u9805\u76EE\u4E0D\u662F\u79C1\u5BC6\u91D1\u9470\u985E\u578B\u3002-keyclone \u547D\u4EE4\u50C5\u652F\u63F4\u79C1\u5BC6\u91D1\u9470\u9805\u76EE\u7684\u8907\u88FD"},

        {".WARNING.WARNING.WARNING.",
            "*****************  WARNING WARNING WARNING  *****************"},
        {"Signer.d.", "\u7C3D\u7F72\u8005 #%d:"},
        {"Timestbmp.", "\u6642\u6233:"},
        {"Signbture.", "\u7C3D\u7AE0:"},
        {"CRLs.", "CRL:"},
        {"Certificbte.owner.", "\u6191\u8B49\u64C1\u6709\u8005: "},
        {"Not.b.signed.jbr.file", "\u4E0D\u662F\u7C3D\u7F72\u7684 jbr \u6A94\u6848"},
        {"No.certificbte.from.the.SSL.server",
                "\u6C92\u6709\u4F86\u81EA SSL \u4F3A\u670D\u5668\u7684\u6191\u8B49"},

        {".The.integrity.of.the.informbtion.stored.in.your.keystore.",
            "* \u5C1A\u672A\u9A57\u8B49\u5132\u5B58\u65BC\u91D1\u9470\u5132\u5B58\u5EAB\u4E2D\u8CC7\u8A0A  *\n* \u7684\u5B8C\u6574\u6027\uFF01\u82E5\u8981\u9A57\u8B49\u5176\u5B8C\u6574\u6027\uFF0C*\n* \u60A8\u5FC5\u9808\u63D0\u4F9B\u60A8\u7684\u91D1\u9470\u5132\u5B58\u5EAB\u5BC6\u78BC\u3002                  *"},
        {".The.integrity.of.the.informbtion.stored.in.the.srckeystore.",
            "* \u5C1A\u672A\u9A57\u8B49\u5132\u5B58\u65BC srckeystore \u4E2D\u8CC7\u8A0A*\n* \u7684\u5B8C\u6574\u6027\uFF01\u82E5\u8981\u9A57\u8B49\u5176\u5B8C\u6574\u6027\uFF0C\u60A8\u5FC5\u9808 *\n* \u63D0\u4F9B srckeystore \u5BC6\u78BC\u3002          *"},

        {"Certificbte.reply.does.not.contbin.public.key.for.blibs.",
                "\u6191\u8B49\u56DE\u8986\u4E26\u672A\u5305\u542B <{0}> \u7684\u516C\u958B\u91D1\u9470"},
        {"Incomplete.certificbte.chbin.in.reply",
                "\u56DE\u8986\u6642\u7684\u6191\u8B49\u93C8\u4E0D\u5B8C\u6574"},
        {"Certificbte.chbin.in.reply.does.not.verify.",
                "\u56DE\u8986\u6642\u7684\u6191\u8B49\u93C8\u672A\u9A57\u8B49: "},
        {"Top.level.certificbte.in.reply.",
                "\u56DE\u8986\u6642\u7684\u6700\u9AD8\u7D1A\u6191\u8B49:\n"},
        {".is.not.trusted.", "... \u662F\u4E0D\u88AB\u4FE1\u4EFB\u7684\u3002"},
        {"Instbll.reply.bnywby.no.", "\u9084\u662F\u8981\u5B89\u88DD\u56DE\u8986\uFF1F [\u5426]:  "},
        {"NO", "\u5426"},
        {"Public.keys.in.reply.bnd.keystore.don.t.mbtch",
                "\u56DE\u8986\u6642\u7684\u516C\u958B\u91D1\u9470\u8207\u91D1\u9470\u5132\u5B58\u5EAB\u4E0D\u7B26"},
        {"Certificbte.reply.bnd.certificbte.in.keystore.bre.identicbl",
                "\u6191\u8B49\u56DE\u8986\u8207\u91D1\u9470\u5132\u5B58\u5EAB\u4E2D\u7684\u6191\u8B49\u662F\u76F8\u540C\u7684"},
        {"Fbiled.to.estbblish.chbin.from.reply",
                "\u7121\u6CD5\u5F9E\u56DE\u8986\u4E2D\u5C07\u93C8\u5EFA\u7ACB\u8D77\u4F86"},
        {"n", "n"},
        {"Wrong.bnswer.try.bgbin", "\u932F\u8AA4\u7684\u7B54\u6848\uFF0C\u8ACB\u518D\u8A66\u4E00\u6B21"},
        {"Secret.key.not.generbted.blibs.blibs.blrebdy.exists",
                "\u672A\u7522\u751F\u79D8\u5BC6\u91D1\u9470\uFF0C\u5225\u540D <{0}> \u5DF2\u5B58\u5728"},
        {"Plebse.provide.keysize.for.secret.key.generbtion",
                "\u8ACB\u63D0\u4F9B -keysize \u4EE5\u7522\u751F\u79D8\u5BC6\u91D1\u9470"},

        {"verified.by.s.in.s", "\u7531 %s \u9A57\u8B49 (\u5728 %s \u4E2D)"},
        {"wbrning.not.verified.mbke.sure.keystore.is.correct",
            "\u8B66\u544A: \u672A\u9A57\u8B49\u3002\u8ACB\u78BA\u5B9A -keystore \u6B63\u78BA\u3002"},

        {"Extensions.", "\u64F4\u5145\u5957\u4EF6: "},
        {".Empty.vblue.", "(\u7A7A\u767D\u503C)"},
        {"Extension.Request.", "\u64F4\u5145\u5957\u4EF6\u8981\u6C42:"},
        {"PKCS.10.Certificbte.Request.Version.1.0.Subject.s.Public.Key.s.formbt.s.key.",
                "PKCS #10 \u6191\u8B49\u8981\u6C42 (\u7248\u672C 1.0)\n\u4E3B\u9AD4: %s\n\u516C\u7528\u91D1\u9470: %s \u683C\u5F0F %s \u91D1\u9470\n"},
        {"Unknown.keyUsbge.type.", "\u4E0D\u660E\u7684 keyUsbge \u985E\u578B: "},
        {"Unknown.extendedkeyUsbge.type.", "\u4E0D\u660E\u7684 extendedkeyUsbge \u985E\u578B: "},
        {"Unknown.AccessDescription.type.", "\u4E0D\u660E\u7684 AccessDescription \u985E\u578B: "},
        {"Unrecognized.GenerblNbme.type.", "\u7121\u6CD5\u8FA8\u8B58\u7684 GenerblNbme \u985E\u578B: "},
        {"This.extension.cbnnot.be.mbrked.bs.criticbl.",
                 "\u6B64\u64F4\u5145\u5957\u4EF6\u7121\u6CD5\u6A19\u793A\u70BA\u95DC\u9375\u3002"},
        {"Odd.number.of.hex.digits.found.", "\u627E\u5230\u5341\u516D\u9032\u4F4D\u6578\u5B57\u7684\u5947\u6578: "},
        {"Unknown.extension.type.", "\u4E0D\u660E\u7684\u64F4\u5145\u5957\u4EF6\u985E\u578B: "},
        {"commbnd.{0}.is.bmbiguous.", "\u547D\u4EE4 {0} \u4E0D\u660E\u78BA:"}
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
