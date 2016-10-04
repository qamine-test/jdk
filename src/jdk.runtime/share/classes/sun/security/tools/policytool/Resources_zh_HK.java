/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.tools.policytool;

/**
 * <p> This clbss represents the <code>ResourceBundle</code>
 * for the policytool.
 *
 */
public clbss Resources_zh_HK extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {
        {"NEWLINE", "\n"},
        {"Wbrning.A.public.key.for.blibs.signers.i.does.not.exist.Mbke.sure.b.KeyStore.is.properly.configured.",
                "\u8B66\u544A: \u5225\u540D {0} \u7684\u516C\u958B\u91D1\u9470\u4E0D\u5B58\u5728\u3002\u8ACB\u78BA\u5B9A\u91D1\u9470\u5132\u5B58\u5EAB\u914D\u7F6E\u6B63\u78BA\u3002"},
        {"Wbrning.Clbss.not.found.clbss", "\u8B66\u544A: \u627E\u4E0D\u5230\u985E\u5225 {0}"},
        {"Wbrning.Invblid.brgument.s.for.constructor.brg",
                "\u8B66\u544A: \u7121\u6548\u7684\u5EFA\u69CB\u5B50\u5F15\u6578: {0}"},
        {"Illegbl.Principbl.Type.type", "\u7121\u6548\u7684 Principbl \u985E\u578B: {0}"},
        {"Illegbl.option.option", "\u7121\u6548\u7684\u9078\u9805: {0}"},
        {"Usbge.policytool.options.", "\u7528\u6CD5: policytool [options]"},
        {".file.file.policy.file.locbtion",
                "  [-file <file>]    \u539F\u5247\u6A94\u6848\u4F4D\u7F6E"},
        {"New", "\u65B0\u589E"},
        {"Open", "\u958B\u555F"},
        {"Sbve", "\u5132\u5B58"},
        {"Sbve.As", "\u53E6\u5B58\u65B0\u6A94"},
        {"View.Wbrning.Log", "\u6AA2\u8996\u8B66\u544A\u8A18\u9304"},
        {"Exit", "\u7D50\u675F"},
        {"Add.Policy.Entry", "\u65B0\u589E\u539F\u5247\u9805\u76EE"},
        {"Edit.Policy.Entry", "\u7DE8\u8F2F\u539F\u5247\u9805\u76EE"},
        {"Remove.Policy.Entry", "\u79FB\u9664\u539F\u5247\u9805\u76EE"},
        {"Edit", "\u7DE8\u8F2F"},
        {"Retbin", "\u4FDD\u7559"},

        {"Wbrning.File.nbme.mby.include.escbped.bbckslbsh.chbrbcters.It.is.not.necessbry.to.escbpe.bbckslbsh.chbrbcters.the.tool.escbpes",
            "\u8B66\u544A: \u6A94\u6848\u540D\u7A31\u5305\u542B\u9041\u96E2\u53CD\u659C\u7DDA\u5B57\u5143\u3002\u4E0D\u9700\u8981\u9041\u96E2\u53CD\u659C\u7DDA\u5B57\u5143 (\u64B0\u5BEB\u539F\u5247\u5167\u5BB9\u81F3\u6C38\u4E45\u5B58\u653E\u5340\u6642\u9700\u8981\u5DE5\u5177\u9041\u96E2\u5B57\u5143)\u3002\n\n\u6309\u4E00\u4E0B\u300C\u4FDD\u7559\u300D\u4EE5\u4FDD\u7559\u8F38\u5165\u7684\u540D\u7A31\uFF0C\u6216\u6309\u4E00\u4E0B\u300C\u7DE8\u8F2F\u300D\u4EE5\u7DE8\u8F2F\u540D\u7A31\u3002"},

        {"Add.Public.Key.Alibs", "\u65B0\u589E\u516C\u958B\u91D1\u9470\u5225\u540D"},
        {"Remove.Public.Key.Alibs", "\u79FB\u9664\u516C\u958B\u91D1\u9470\u5225\u540D"},
        {"File", "\u6A94\u6848"},
        {"KeyStore", "\u91D1\u9470\u5132\u5B58\u5EAB"},
        {"Policy.File.", "\u539F\u5247\u6A94\u6848: "},
        {"Could.not.open.policy.file.policyFile.e.toString.",
                "\u7121\u6CD5\u958B\u555F\u539F\u5247\u6A94\u6848: {0}: {1}"},
        {"Policy.Tool", "\u539F\u5247\u5DE5\u5177"},
        {"Errors.hbve.occurred.while.opening.the.policy.configurbtion.View.the.Wbrning.Log.for.more.informbtion.",
                "\u958B\u555F\u539F\u5247\u8A18\u7F6E\u6642\u767C\u751F\u932F\u8AA4\u3002\u8ACB\u6AA2\u8996\u8B66\u544A\u8A18\u9304\u4EE5\u53D6\u5F97\u66F4\u591A\u7684\u8CC7\u8A0A"},
        {"Error", "\u932F\u8AA4"},
        {"OK", "\u78BA\u5B9A"},
        {"Stbtus", "\u72C0\u614B"},
        {"Wbrning", "\u8B66\u544A"},
        {"Permission.",
                "\u6B0A\u9650:                                                       "},
        {"Principbl.Type.", "Principbl \u985E\u578B: "},
        {"Principbl.Nbme.", "Principbl \u540D\u7A31: "},
        {"Tbrget.Nbme.",
                "\u76EE\u6A19\u540D\u7A31:                                                    "},
        {"Actions.",
                "\u52D5\u4F5C:                                                             "},
        {"OK.to.overwrite.existing.file.filenbme.",
                "\u78BA\u8A8D\u8986\u5BEB\u73FE\u5B58\u7684\u6A94\u6848 {0}\uFF1F"},
        {"Cbncel", "\u53D6\u6D88"},
        {"CodeBbse.", "CodeBbse:"},
        {"SignedBy.", "SignedBy:"},
        {"Add.Principbl", "\u65B0\u589E Principbl"},
        {"Edit.Principbl", "\u7DE8\u8F2F Principbl"},
        {"Remove.Principbl", "\u79FB\u9664 Principbl"},
        {"Principbls.", "Principbl:"},
        {".Add.Permission", "  \u65B0\u589E\u6B0A\u9650"},
        {".Edit.Permission", "  \u7DE8\u8F2F\u6B0A\u9650"},
        {"Remove.Permission", "\u79FB\u9664\u6B0A\u9650"},
        {"Done", "\u5B8C\u6210"},
        {"KeyStore.URL.", "\u91D1\u9470\u5132\u5B58\u5EAB URL: "},
        {"KeyStore.Type.", "\u91D1\u9470\u5132\u5B58\u5EAB\u985E\u578B:"},
        {"KeyStore.Provider.", "\u91D1\u9470\u5132\u5B58\u5EAB\u63D0\u4F9B\u8005:"},
        {"KeyStore.Pbssword.URL.", "\u91D1\u9470\u5132\u5B58\u5EAB\u5BC6\u78BC URL: "},
        {"Principbls", "Principbl"},
        {".Edit.Principbl.", "  \u7DE8\u8F2F Principbl: "},
        {".Add.New.Principbl.", "  \u65B0\u589E Principbl: "},
        {"Permissions", "\u6B0A\u9650"},
        {".Edit.Permission.", "  \u7DE8\u8F2F\u6B0A\u9650:"},
        {".Add.New.Permission.", "  \u65B0\u589E\u6B0A\u9650:"},
        {"Signed.By.", "\u7C3D\u7F72\u4EBA: "},
        {"Cbnnot.Specify.Principbl.with.b.Wildcbrd.Clbss.without.b.Wildcbrd.Nbme",
            "\u6C92\u6709\u842C\u7528\u5B57\u5143\u540D\u7A31\uFF0C\u7121\u6CD5\u6307\u5B9A\u542B\u6709\u842C\u7528\u5B57\u5143\u985E\u5225\u7684 Principbl"},
        {"Cbnnot.Specify.Principbl.without.b.Nbme",
            "\u6C92\u6709\u540D\u7A31\uFF0C\u7121\u6CD5\u6307\u5B9A Principbl"},
        {"Permission.bnd.Tbrget.Nbme.must.hbve.b.vblue",
                "\u6B0A\u9650\u53CA\u76EE\u6A19\u540D\u7A31\u5FC5\u9808\u6709\u4E00\u500B\u503C\u3002"},
        {"Remove.this.Policy.Entry.", "\u79FB\u9664\u9019\u500B\u539F\u5247\u9805\u76EE\uFF1F"},
        {"Overwrite.File", "\u8986\u5BEB\u6A94\u6848"},
        {"Policy.successfully.written.to.filenbme",
                "\u539F\u5247\u6210\u529F\u5BEB\u5165\u81F3 {0}"},
        {"null.filenbme", "\u7A7A\u503C\u6A94\u540D"},
        {"Sbve.chbnges.", "\u5132\u5B58\u8B8A\u66F4\uFF1F"},
        {"Yes", "\u662F"},
        {"No", "\u5426"},
        {"Policy.Entry", "\u539F\u5247\u9805\u76EE"},
        {"Sbve.Chbnges", "\u5132\u5B58\u8B8A\u66F4"},
        {"No.Policy.Entry.selected", "\u6C92\u6709\u9078\u53D6\u539F\u5247\u9805\u76EE"},
        {"Unbble.to.open.KeyStore.ex.toString.",
                "\u7121\u6CD5\u958B\u555F\u91D1\u9470\u5132\u5B58\u5EAB: {0}"},
        {"No.principbl.selected", "\u672A\u9078\u53D6 Principbl"},
        {"No.permission.selected", "\u6C92\u6709\u9078\u53D6\u6B0A\u9650"},
        {"nbme", "\u540D\u7A31"},
        {"configurbtion.type", "\u7D44\u614B\u985E\u578B"},
        {"environment.vbribble.nbme", "\u74B0\u5883\u8B8A\u6578\u540D\u7A31"},
        {"librbry.nbme", "\u7A0B\u5F0F\u5EAB\u540D\u7A31"},
        {"pbckbge.nbme", "\u5957\u88DD\u7A0B\u5F0F\u540D\u7A31"},
        {"policy.type", "\u539F\u5247\u985E\u578B"},
        {"property.nbme", "\u5C6C\u6027\u540D\u7A31"},
        {"provider.nbme", "\u63D0\u4F9B\u8005\u540D\u7A31"},
        {"Principbl.List", "Principbl \u6E05\u55AE"},
        {"Permission.List", "\u6B0A\u9650\u6E05\u55AE"},
        {"Code.Bbse", "\u4EE3\u78BC\u57FA\u6E96"},
        {"KeyStore.U.R.L.", "\u91D1\u9470\u5132\u5B58\u5EAB URL:"},
        {"KeyStore.Pbssword.U.R.L.", "\u91D1\u9470\u5132\u5B58\u5EAB\u5BC6\u78BC URL:"}
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
