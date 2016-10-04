/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.debug.tty;

/**
 * <p> This clbss represents the <code>ResourceBundle</code>
 * for the following pbckbge(s):
 *
 * <ol>
 * <li> com.sun.tools.exbmple.debug.tty
 * </ol>
 *
 */
public clbss TTYResources_zh_CN extends jbvb.util.ListResourceBundle {


    /**
     * Returns the contents of this <code>ResourceBundle</code>.
     *
     * <p>
     *
     * @return the contents of this <code>ResourceBundle</code>.
     */
    @Override
    public Object[][] getContents() {
        Object[][] temp = new Object[][] {
        // NOTE: The vblue strings in this file contbining "{0}" bre
        //       processed by the jbvb.text.MessbgeFormbt clbss.  Any
        //       single quotes bppebring in these strings need to be
        //       doubled up.
        //
        // LOCALIZE THIS
        {"** clbsses list **", "** \u7C7B\u5217\u8868 **\n{0}"},
        {"** fields list **", "** \u5B57\u6BB5\u5217\u8868 **\n{0}"},
        {"** methods list **", "** \u65B9\u6CD5\u5217\u8868 **\n{0}"},
        {"*** Rebding commbnds from", "*** \u6B63\u5728\u4ECE{0}\u8BFB\u53D6\u547D\u4EE4"},
        {"All threbds resumed.", "\u5DF2\u6062\u590D\u6240\u6709\u7EBF\u7A0B\u3002"},
        {"All threbds suspended.", "\u5DF2\u6302\u8D77\u6240\u6709\u7EBF\u7A0B\u3002"},
        {"Argument is not defined for connector:", "\u6CA1\u6709\u4E3A\u8FDE\u63A5\u5668{1}\u5B9A\u4E49\u53C2\u6570{0}"},
        {"Arguments mbtch no method", "\u53C2\u6570\u4E0D\u4E0E\u4EFB\u4F55\u65B9\u6CD5\u5339\u914D"},
        {"Arrby:", "\u6570\u7EC4: {0}"},
        {"Arrby element is not b method", "\u6570\u7EC4\u5143\u7D20\u4E0D\u662F\u65B9\u6CD5"},
        {"Arrby index must be b integer type", "\u6570\u7EC4\u7D22\u5F15\u5FC5\u987B\u4E3A\u6574\u6570\u7C7B\u578B"},
        {"bbse directory:", "\u57FA\u76EE\u5F55: {0}"},
        {"bootclbsspbth:", "\u5F15\u5BFC\u7C7B\u8DEF\u5F84: {0}"},
        {"Brebkpoint hit:", "\u65AD\u70B9\u547D\u4E2D: "},
        {"brebkpoint", "\u65AD\u70B9{0}"},
        {"Brebkpoints set:", "\u65AD\u70B9\u96C6:"},
        {"Brebkpoints cbn be locbted only in clbsses.", "\u65AD\u70B9\u53EA\u80FD\u4F4D\u4E8E\u7C7B\u4E2D\u3002{0}\u662F\u63A5\u53E3\u6216\u6570\u7EC4\u3002"},
        {"Cbn only trbce", "\u53EA\u80FD\u8DDF\u8E2A 'methods', 'method exit' \u6216 'method exits'"},
        {"cbnnot redefine existing connection", "{0}\u65E0\u6CD5\u91CD\u65B0\u5B9A\u4E49\u73B0\u6709\u8FDE\u63A5"},
        {"Cbnnot bssign to b method invocbtion", "\u65E0\u6CD5\u5206\u914D\u5230\u65B9\u6CD5\u8C03\u7528"},
        {"Cbnnot specify commbnd line with connector:", "\u65E0\u6CD5\u6307\u5B9A\u5E26\u6709\u8FDE\u63A5\u5668\u7684\u547D\u4EE4\u884C: {0}"},
        {"Cbnnot specify tbrget vm brguments with connector:", "\u65E0\u6CD5\u6307\u5B9A\u5E26\u6709\u8FDE\u63A5\u5668\u7684\u76EE\u6807 VM \u53C2\u6570: {0}"},
        {"Clbss contbining field must be specified.", "\u5FC5\u987B\u6307\u5B9A\u5305\u542B\u5B57\u6BB5\u7684\u7C7B\u3002"},
        {"Clbss:", "\u7C7B: {0}"},
        {"Clbssic VM no longer supported.", "\u4E0D\u518D\u652F\u6301\u7ECF\u5178 VM\u3002"},
        {"clbsspbth:", "\u7C7B\u8DEF\u5F84: {0}"},
        {"colon mbrk", ":"},
        {"colon spbce", ": "},
        {"Commbnd is not supported on the tbrget VM", "\u76EE\u6807 VM \u4E0D\u652F\u6301\u547D\u4EE4 ''{0}''"},
        {"Commbnd is not supported on b rebd-only VM connection", "\u53EA\u8BFB VM \u8FDE\u63A5\u4E0D\u652F\u6301\u547D\u4EE4 ''{0}''"},
        {"Commbnd not vblid until the VM is stbrted with the run commbnd", "\u5728\u4F7F\u7528 ''run'' \u547D\u4EE4\u542F\u52A8 VM \u524D, \u547D\u4EE4 ''{0}'' \u662F\u65E0\u6548\u7684"},
        {"Condition must be boolebn", "\u6761\u4EF6\u5FC5\u987B\u662F\u5E03\u5C14\u578B"},
        {"Connector bnd Trbnsport nbme", "  \u8FDE\u63A5\u5668: {0}, \u4F20\u8F93: {1}"},
        {"Connector brgument nodefbult", "    \u53C2\u6570: {0} (\u65E0\u9ED8\u8BA4\u503C)"},
        {"Connector brgument defbult", "    \u53C2\u6570: {0}, \u9ED8\u8BA4\u503C: {1}"},
        {"Connector description", "    \u8BF4\u660E: {0}"},
        {"Connector required brgument nodefbult", "    \u6240\u9700\u7684\u53C2\u6570: {0} (\u65E0\u9ED8\u8BA4\u503C)"},
        {"Connector required brgument defbult", "    \u6240\u9700\u7684\u53C2\u6570: {0}, \u9ED8\u8BA4\u503C: {1}"},
        {"Connectors bvbilbble", "\u53EF\u7528\u8FDE\u63A5\u5668\u4E3A:"},
        {"Constbnt is not b method", "\u5E38\u91CF\u4E0D\u662F\u65B9\u6CD5"},
        {"Could not open:", "\u65E0\u6CD5\u6253\u5F00: {0}"},
        {"Current method is nbtive", "\u5F53\u524D\u65B9\u6CD5\u4E3A\u672C\u673A\u65B9\u6CD5"},
        {"Current threbd died. Execution continuing...", "\u5F53\u524D\u7EBF\u7A0B{0}\u5DF2\u6210\u4E3A\u6B7B\u7EBF\u7A0B\u3002\u7EE7\u7EED\u6267\u884C..."},
        {"Current threbd isnt suspended.", "\u5F53\u524D\u7EBF\u7A0B\u672A\u6302\u8D77\u3002"},
        {"Current threbd not set.", "\u5F53\u524D\u7EBF\u7A0B\u672A\u8BBE\u7F6E\u3002"},
        {"dbgtrbce flbg vblue must be bn integer:", "dbgtrbce \u6807\u8BB0\u503C\u5FC5\u987B\u4E3A\u6574\u6570: {0}"},
        {"Deferring.", "\u6B63\u5728\u5EF6\u8FDF{0}\u3002\n\u5C06\u5728\u52A0\u8F7D\u7C7B\u540E\u8BBE\u7F6E\u3002"},
        {"End of stbck.", "\u5806\u6808\u7ED3\u675F\u3002"},
        {"Error popping frbme", "\u4F7F\u5E27\u51FA\u6808\u65F6\u51FA\u9519 - {0}"},
        {"Error rebding file", "\u8BFB\u53D6 ''{0}'' \u65F6\u51FA\u9519 - {1}"},
        {"Error redefining clbss to file", "\u5C06{0}\u91CD\u65B0\u5B9A\u4E49\u4E3A{1}\u65F6\u51FA\u9519 - {2}"},
        {"exceptionSpec bll", "\u6240\u6709{0}"},
        {"exceptionSpec cbught", "\u6355\u83B7\u7684{0}"},
        {"exceptionSpec uncbught", "\u672A\u6355\u83B7\u7684{0}"},
        {"Exception in expression:", "\u8868\u8FBE\u5F0F\u4E2D\u51FA\u73B0\u5F02\u5E38\u9519\u8BEF: {0}"},
        {"Exception occurred cbught", "\u51FA\u73B0\u5F02\u5E38\u9519\u8BEF: {0} (\u5C06\u5728\u4EE5\u4E0B\u4F4D\u7F6E\u6355\u83B7: {1})"},
        {"Exception occurred uncbught", "\u51FA\u73B0\u5F02\u5E38\u9519\u8BEF: {0} (\u672A\u6355\u83B7)"},
        {"Exceptions cbught:", "\u51FA\u73B0\u8FD9\u4E9B\u5F02\u5E38\u9519\u8BEF\u65F6\u4E2D\u65AD:"},
        {"expr is null", "{0} = \u7A7A\u503C"},
        {"expr is vblue", "{0} = {1}"},
        {"expr is vblue <collected>", "  {0} = {1} <\u5DF2\u6536\u96C6>"},
        {"Expression cbnnot be void", "\u8868\u8FBE\u5F0F\u4E0D\u80FD\u4E3A\u7A7A"},
        {"Expression must evblubte to bn object", "\u8868\u8FBE\u5F0F\u7684\u8BA1\u7B97\u7ED3\u679C\u5FC5\u987B\u4E3A\u5BF9\u8C61"},
        {"extends:", "\u6269\u5C55: {0}"},
        {"Fbiled rebding output", "\u65E0\u6CD5\u8BFB\u53D6\u5B50 Jbvb \u89E3\u91CA\u5668\u7684\u8F93\u51FA\u3002"},
        {"Fbtbl error", "\u81F4\u547D\u9519\u8BEF:"},
        {"Field bccess encountered before bfter", "\u5B57\u6BB5 ({0}) \u4E3A{1}, \u5C06\u4E3A{2}: "},
        {"Field bccess encountered", "\u9047\u5230\u5B57\u6BB5 ({0}) \u8BBF\u95EE: "},
        {"Field to unwbtch not specified", "\u672A\u6307\u5B9A\u8981\u53D6\u6D88\u76D1\u89C6\u7684\u5B57\u6BB5\u3002"},
        {"Field to wbtch not specified", "\u672A\u6307\u5B9A\u8981\u76D1\u89C6\u7684\u5B57\u6BB5\u3002"},
        {"GC Disbbled for", "\u5DF2\u5BF9{0}\u7981\u7528 GC:"},
        {"GC Enbbled for", "\u5DF2\u5BF9{0}\u542F\u7528 GC:"},
        {"grouping begin chbrbcter", "{"},
        {"grouping end chbrbcter", "}"},
        {"Illegbl Argument Exception", "\u975E\u6CD5\u53C2\u6570\u5F02\u5E38\u9519\u8BEF"},
        {"Illegbl connector brgument", "\u975E\u6CD5\u8FDE\u63A5\u5668\u53C2\u6570: {0}"},
        {"implementor:", "\u5B9E\u73B0\u8005: {0}"},
        {"implements:", "\u5B9E\u73B0: {0}"},
        {"Initiblizing prognbme", "\u6B63\u5728\u521D\u59CB\u5316{0}..."},
        {"Input strebm closed.", "\u8F93\u5165\u6D41\u5DF2\u5173\u95ED\u3002"},
        {"Interfbce:", "\u63A5\u53E3: {0}"},
        {"Internbl debugger error.", "\u5185\u90E8\u8C03\u8BD5\u5668\u9519\u8BEF\u3002"},
        {"Internbl error: null ThrebdInfo crebted", "\u5185\u90E8\u9519\u8BEF: \u521B\u5EFA\u4E86\u7A7A\u503C ThrebdInfo"},
        {"Internbl error; unbble to set", "\u5185\u90E8\u9519\u8BEF; \u65E0\u6CD5\u8BBE\u7F6E{0}"},
        {"Internbl exception during operbtion:", "\u64CD\u4F5C\u671F\u95F4\u51FA\u73B0\u5185\u90E8\u5F02\u5E38\u9519\u8BEF:\n    {0}"},
        {"Internbl exception:", "\u5185\u90E8\u5F02\u5E38\u9519\u8BEF:"},
        {"Invblid brgument type nbme", "\u53C2\u6570\u7C7B\u578B\u540D\u79F0\u65E0\u6548"},
        {"Invblid bssignment syntbx", "\u8D4B\u503C\u8BED\u6CD5\u65E0\u6548"},
        {"Invblid commbnd syntbx", "\u547D\u4EE4\u8BED\u6CD5\u65E0\u6548"},
        {"Invblid connect type", "\u8FDE\u63A5\u7C7B\u578B\u65E0\u6548"},
        {"Invblid consecutive invocbtions", "\u8FDE\u7EED\u8C03\u7528\u65E0\u6548"},
        {"Invblid exception object", "\u5F02\u5E38\u9519\u8BEF\u5BF9\u8C61\u65E0\u6548"},
        {"Invblid method specificbtion:", "\u65B9\u6CD5\u89C4\u8303\u65E0\u6548: {0}"},
        {"Invblid option on clbss commbnd", "\u7C7B\u547D\u4EE4\u7684\u9009\u9879\u65E0\u6548"},
        {"invblid option", "\u9009\u9879\u65E0\u6548: {0}"},
        {"Invblid threbd stbtus.", "\u7EBF\u7A0B\u72B6\u6001\u65E0\u6548\u3002"},
        {"Invblid trbnsport nbme:", "\u4F20\u8F93\u540D\u79F0\u65E0\u6548: {0}"},
        {"I/O exception occurred:", "\u51FA\u73B0 I/O \u5F02\u5E38\u9519\u8BEF: {0}"},
        {"is bn bmbiguous method nbme in", "\"{0}\" \u5728 \"{1}\" \u4E2D\u662F\u4E0D\u660E\u786E\u7684\u65B9\u6CD5\u540D\u79F0"},
        {"is bn invblid line number for",  "{0,number,integer} \u662F{1}\u7684\u65E0\u6548\u884C\u53F7"},
        {"is not b vblid clbss nbme", "\"{0}\" \u4E0D\u662F\u6709\u6548\u7684\u7C7B\u540D\u3002"},
        {"is not b vblid field nbme", "\"{0}\" \u4E0D\u662F\u6709\u6548\u7684\u5B57\u6BB5\u540D\u3002"},
        {"is not b vblid id or clbss nbme", "\"{0}\" \u4E0D\u662F\u6709\u6548\u7684 ID \u6216\u7C7B\u540D\u3002"},
        {"is not b vblid line number or method nbme for", "\"{0}\" \u4E0D\u662F\u7C7B \"{1}\" \u7684\u6709\u6548\u884C\u53F7\u6216\u65B9\u6CD5\u540D"},
        {"is not b vblid method nbme", "\"{0}\" \u4E0D\u662F\u6709\u6548\u7684\u65B9\u6CD5\u540D\u3002"},
        {"is not b vblid threbd id", "\"{0}\" \u4E0D\u662F\u6709\u6548\u7684\u7EBF\u7A0B ID\u3002"},
        {"is not b vblid threbdgroup nbme", "\"{0}\" \u4E0D\u662F\u6709\u6548\u7684\u7EBF\u7A0B\u7EC4\u540D\u79F0\u3002"},
        {"jdb prompt with no current threbd", "> "},
        {"jdb prompt threbd nbme bnd current stbck frbme", "{0}[{1,number,integer}] "},
        {"killed", "{0}\u5DF2\u7EC8\u6B62"},
        {"killing threbd:", "\u6B63\u5728\u7EC8\u6B62\u7EBF\u7A0B: {0}"},
        {"Line number informbtion not bvbilbble for", "\u6B64\u4F4D\u7F6E\u7684\u6E90\u884C\u53F7\u4E0D\u53EF\u7528\u3002"},
        {"line number", ":{0,number,integer}"},
        {"list field typenbme bnd nbme", "{0} {1}\n"},
        {"list field typenbme bnd nbme inherited", "{0} {1} (\u7EE7\u627F\u81EA{2})\n"},
        {"list field typenbme bnd nbme hidden", "{0} {1} (\u9690\u85CF)\n"},
        {"Listening bt bddress:", "\u76D1\u542C\u5730\u5740: {0}"},
        {"Locbl vbribble informbtion not bvbilbble.", "\u672C\u5730\u53D8\u91CF\u4FE1\u606F\u4E0D\u53EF\u7528\u3002\u8BF7\u4F7F\u7528 -g \u7F16\u8BD1\u4EE5\u751F\u6210\u53D8\u91CF\u4FE1\u606F"},
        {"Locbl vbribbles:", "\u672C\u5730\u53D8\u91CF:"},
        {"<locbtion unbvbilbble>", "<\u4F4D\u7F6E\u4E0D\u53EF\u7528>"},
        {"locbtion", "\"\u7EBF\u7A0B={0}\", {1}"},
        {"locbtionString", "{0}.{1}(), \u884C={2,number,integer} bci={3,number,integer}"},
        {"Mbin clbss bnd brguments must be specified", "\u5FC5\u987B\u6307\u5B9A\u4E3B\u7C7B\u548C\u53C2\u6570"},
        {"Method brguments:", "\u65B9\u6CD5\u53C2\u6570:"},
        {"Method entered:", "\u5DF2\u8FDB\u5165\u65B9\u6CD5: "},
        {"Method exited:",  "\u5DF2\u9000\u51FA\u65B9\u6CD5"},
        {"Method exitedVblue:", "\u5DF2\u9000\u51FA\u65B9\u6CD5: \u8FD4\u56DE\u503C = {0}, "},
        {"Method is overlobded; specify brguments", "\u5DF2\u91CD\u8F7D\u65B9\u6CD5{0}; \u8BF7\u6307\u5B9A\u53C2\u6570"},
        {"minus version", "\u8FD9\u662F{0}\u7248\u672C {1,number,integer}.{2,number,integer} (Jbvb SE \u7248\u672C {3})"},
        {"Monitor informbtion for threbd", "\u76D1\u89C6\u7EBF\u7A0B{0}\u7684\u4FE1\u606F:"},
        {"Monitor informbtion for expr", "\u76D1\u89C6{0} ({1}) \u7684\u4FE1\u606F:"},
        {"More thbn one clbss nbmed", "\u591A\u4E2A\u7C7B\u7684\u540D\u79F0\u4E3A: ''{0}''"},
        {"nbtive method", "\u672C\u673A\u65B9\u6CD5"},
        {"nested:", "\u5D4C\u5957: {0}"},
        {"No bttbch bddress specified.", "\u672A\u6307\u5B9A\u9644\u52A0\u5730\u5740\u3002"},
        {"No brebkpoints set.", "\u672A\u8BBE\u7F6E\u65AD\u70B9\u3002"},
        {"No clbss nbmed", "\u6CA1\u6709\u540D\u4E3A ''{0}'' \u7684\u7C7B"},
        {"No clbss specified.", "\u672A\u6307\u5B9A\u7C7B\u3002"},
        {"No clbsspbth specified.", "\u672A\u6307\u5B9A\u7C7B\u8DEF\u5F84\u3002"},
        {"No code bt line", "{1}\u4E2D\u7684\u884C {0,number,integer} \u5904\u6CA1\u6709\u4EE3\u7801"},
        {"No connect specificbtion.", "\u6CA1\u6709\u8FDE\u63A5\u89C4\u8303\u3002"},
        {"No connector nbmed:", "\u6CA1\u6709\u540D\u4E3A{0}\u7684\u8FDE\u63A5\u5668"},
        {"No current threbd", "\u6CA1\u6709\u5F53\u524D\u7EBF\u7A0B"},
        {"No defbult threbd specified:", "\u672A\u6307\u5B9A\u9ED8\u8BA4\u7EBF\u7A0B: \u8BF7\u5148\u4F7F\u7528 \"threbd\" \u547D\u4EE4\u3002"},
        {"No exception object specified.", "\u672A\u6307\u5B9A\u5F02\u5E38\u9519\u8BEF\u5BF9\u8C61\u3002"},
        {"No exceptions cbught.", "\u672A\u6355\u83B7\u5230\u5F02\u5E38\u9519\u8BEF\u3002"},
        {"No expression specified.", "\u672A\u6307\u5B9A\u8868\u8FBE\u5F0F\u3002"},
        {"No field in", "{1}\u4E2D\u6CA1\u6709\u5B57\u6BB5{0}"},
        {"No frbmes on the current cbll stbck", "\u5F53\u524D\u8C03\u7528\u5806\u6808\u4E0A\u6CA1\u6709\u5E27"},
        {"No linenumber informbtion for", "{0}\u6CA1\u6709\u884C\u53F7\u4FE1\u606F\u3002\u8BF7\u5C1D\u8BD5\u5728\u542F\u7528\u8C03\u8BD5\u7684\u60C5\u51B5\u4E0B\u7F16\u8BD1\u3002"},
        {"No locbl vbribbles", "\u6CA1\u6709\u672C\u5730\u53D8\u91CF"},
        {"No method in", "{1}\u4E2D\u6CA1\u6709\u65B9\u6CD5{0}"},
        {"No method specified.", "\u672A\u6307\u5B9A\u65B9\u6CD5\u3002"},
        {"No monitor numbered:", "\u6CA1\u6709\u7F16\u53F7\u4E3A {0} \u7684\u76D1\u89C6\u5668"},
        {"No monitors owned", "  \u4E0D\u62E5\u6709\u76D1\u89C6\u5668"},
        {"No object specified.", "\u672A\u6307\u5B9A\u5BF9\u8C61\u3002"},
        {"No objects specified.", "\u672A\u6307\u5B9A\u5BF9\u8C61\u3002"},
        {"No sbve index specified.", "\u672A\u6307\u5B9A\u4FDD\u5B58\u7D22\u5F15\u3002"},
        {"No sbved vblues", "\u6CA1\u6709\u4FDD\u5B58\u7684\u503C"},
        {"No source informbtion bvbilbble for:", "\u6CA1\u6709\u53EF\u7528\u4E8E{0}\u7684\u6E90\u4FE1\u606F"},
        {"No sourcedebugextension specified", "\u672A\u6307\u5B9A SourceDebugExtension"},
        {"No sourcepbth specified.", "\u672A\u6307\u5B9A\u6E90\u8DEF\u5F84\u3002"},
        {"No threbd specified.", "\u672A\u6307\u5B9A\u7EBF\u7A0B\u3002"},
        {"No VM connected", "\u672A\u8FDE\u63A5 VM"},
        {"No wbiters", "  \u6CA1\u6709\u7B49\u5F85\u8FDB\u7A0B"},
        {"not b clbss", "{0}\u4E0D\u662F\u7C7B"},
        {"Not b monitor number:", "\u4E0D\u662F\u76D1\u89C6\u5668\u7F16\u53F7: ''{0}''"},
        {"not found (try the full nbme)", "\u627E\u4E0D\u5230{0} (\u8BF7\u5C1D\u8BD5\u4F7F\u7528\u5168\u540D)"},
        {"Not found:", "\u627E\u4E0D\u5230: {0}"},
        {"not found", "\u627E\u4E0D\u5230{0}"},
        {"Not owned", "  \u4E0D\u62E5\u6709"},
        {"Not wbiting for b monitor", "  \u672A\u7B49\u5F85\u76D1\u89C6\u5668"},
        {"Nothing suspended.", "\u672A\u6302\u8D77\u4EFB\u4F55\u5BF9\u8C61\u3002"},
        {"object description bnd hex id", "({0}){1}"},
        {"Operbtion is not supported on the tbrget VM", "\u76EE\u6807 VM \u4E0D\u652F\u6301\u8BE5\u64CD\u4F5C"},
        {"operbtion not yet supported", "\u5C1A\u4E0D\u652F\u6301\u8BE5\u64CD\u4F5C"},
        {"Owned by:", "  \u62E5\u6709\u8005: {0}, \u6761\u76EE\u8BA1\u6570: {1,number,integer}"},
        {"Owned monitor:", "  \u62E5\u6709\u7684\u76D1\u89C6\u5668: {0}"},
        {"Pbrse exception:", "\u89E3\u6790\u5F02\u5E38\u9519\u8BEF: {0}"},
        {"printbrebkpointcommbndusbge", "\u7528\u6CD5: {0} <clbss>:<line_number> \u6216\n       {1} <clbss>.<method_nbme>[(brgument_type,...)]"},
        {"Removed:", "\u5DF2\u5220\u9664: {0}"},
        {"Requested stbck frbme is no longer bctive:", "\u8BF7\u6C42\u7684\u5806\u6808\u5E27\u4E0D\u518D\u6709\u6548: {0,number,integer}"},
        {"run <brgs> commbnd is vblid only with lbunched VMs", "'run <brgs>' \u547D\u4EE4\u4EC5\u5BF9\u542F\u52A8\u7684 VM \u6709\u6548"},
        {"run", "\u8FD0\u884C{0}"},
        {"sbved", "{0}\u5DF2\u4FDD\u5B58"},
        {"Set deferred", "\u8BBE\u7F6E\u5EF6\u8FDF\u7684{0}"},
        {"Set", "\u8BBE\u7F6E{0}"},
        {"Source file not found:", "\u627E\u4E0D\u5230\u6E90\u6587\u4EF6: {0}"},
        {"source line number bnd line", "{0,number,integer}    {1}"},
        {"source line number current line bnd line", "{0,number,integer} => {1}"},
        {"sourcedebugextension", "SourceDebugExtension -- {0}"},
        {"Specify clbss bnd method", "\u6307\u5B9A\u7C7B\u548C\u65B9\u6CD5"},
        {"Specify clbsses to redefine", "\u6307\u5B9A\u8981\u91CD\u65B0\u5B9A\u4E49\u7684\u7C7B"},
        {"Specify file nbme for clbss", "\u6307\u5B9A\u7C7B{0}\u7684\u6587\u4EF6\u540D"},
        {"stbck frbme dump with pc", "  [{0,number,integer}] {1}.{2} ({3}), pc = {4}"},
        {"stbck frbme dump", "  [{0,number,integer}] {1}.{2} ({3})"},
        {"Step completed:", "\u5DF2\u5B8C\u6210\u7684\u6B65\u9AA4: "},
        {"Stopping due to deferred brebkpoint errors.", "\u7531\u4E8E\u5EF6\u8FDF\u65AD\u70B9\u9519\u8BEF\u800C\u505C\u6B62\u3002\n"},
        {"subclbss:", "\u5B50\u7C7B: {0}"},
        {"subinterfbce:", "\u5B50\u63A5\u53E3: {0}"},
        {"tbb", "\t{0}"},
        {"Tbrget VM fbiled to initiblize.", "\u65E0\u6CD5\u521D\u59CB\u5316\u76EE\u6807 VM\u3002"},
        {"The bpplicbtion exited", "\u5E94\u7528\u7A0B\u5E8F\u5DF2\u9000\u51FA"},
        {"The bpplicbtion hbs been disconnected", "\u5E94\u7528\u7A0B\u5E8F\u5DF2\u65AD\u5F00\u8FDE\u63A5"},
        {"The gc commbnd is no longer necessbry.", "\u4E0D\u518D\u9700\u8981 'gc' \u547D\u4EE4\u3002\n\u6240\u6709\u5BF9\u8C61\u5DF2\u7167\u5E38\u8FDB\u884C\u5783\u573E\u6536\u96C6\u3002\u8BF7\u4F7F\u7528 'enbblegc' \u548C 'disbblegc'\n\u547D\u4EE4\u6765\u63A7\u5236\u5404\u4E2A\u5BF9\u8C61\u7684\u5783\u573E\u6536\u96C6\u3002"},
        {"The lobd commbnd is no longer supported.", "\u4E0D\u518D\u652F\u6301 'lobd' \u547D\u4EE4\u3002"},
        {"The memory commbnd is no longer supported.", "\u4E0D\u518D\u652F\u6301 'memory' \u547D\u4EE4\u3002"},
        {"The VM does not use pbths", "VM \u4E0D\u4F7F\u7528\u8DEF\u5F84"},
        {"Threbd is not running (no stbck).", "\u7EBF\u7A0B\u672A\u8FD0\u884C (\u6CA1\u6709\u5806\u6808)\u3002"},
        {"Threbd number not specified.", "\u672A\u6307\u5B9A\u7EBF\u7A0B\u7F16\u53F7\u3002"},
        {"Threbd:", "{0}:"},
        {"Threbd Group:", "\u7EC4{0}:"},
        {"Threbd description nbme unknownStbtus BP",  "  {0} {1}\u672A\u77E5 (\u5728\u65AD\u70B9\u5904)"},
        {"Threbd description nbme unknownStbtus",     "  {0} {1}\u672A\u77E5"},
        {"Threbd description nbme zombieStbtus BP",   "  {0} {1}\u5904\u4E8E\u50F5\u6B7B\u72B6\u6001 (\u5728\u65AD\u70B9\u5904)"},
        {"Threbd description nbme zombieStbtus",      "  {0} {1}\u5904\u4E8E\u50F5\u6B7B\u72B6\u6001"},
        {"Threbd description nbme runningStbtus BP",  "  {0} {1}\u6B63\u5728\u8FD0\u884C (\u5728\u65AD\u70B9\u5904)"},
        {"Threbd description nbme runningStbtus",     "  {0} {1}\u6B63\u5728\u8FD0\u884C"},
        {"Threbd description nbme sleepingStbtus BP", "  {0} {1}\u6B63\u5728\u4F11\u7720 (\u5728\u65AD\u70B9\u5904)"},
        {"Threbd description nbme sleepingStbtus",    "  {0} {1}\u6B63\u5728\u4F11\u7720"},
        {"Threbd description nbme wbitingStbtus BP",  "  {0} {1}\u6B63\u5728\u7B49\u5F85\u76D1\u89C6\u5668 (\u5728\u65AD\u70B9\u5904)"},
        {"Threbd description nbme wbitingStbtus",     "  {0} {1}\u6B63\u5728\u7B49\u5F85\u76D1\u89C6\u5668"},
        {"Threbd description nbme condWbitstbtus BP", "  {0} {1}\u6B63\u5728\u6267\u884C\u6761\u4EF6\u7B49\u5F85 (\u5728\u65AD\u70B9\u5904)"},
        {"Threbd description nbme condWbitstbtus",    "  {0} {1}\u6B63\u5728\u6267\u884C\u6761\u4EF6\u7B49\u5F85"},
        {"Threbd hbs been resumed", "\u5DF2\u6062\u590D\u7EBF\u7A0B"},
        {"Threbd not suspended", "\u672A\u6302\u8D77\u7EBF\u7A0B"},
        {"threbd group number description nbme", "{0,number,integer}\u3002{1} {2}"},
        {"Threbdgroup nbme not specified.", "\u672A\u6307\u5B9A\u7EBF\u7A0B\u7EC4\u540D\u3002"},
        {"Threbds must be suspended", "\u5FC5\u987B\u6302\u8D77\u7EBF\u7A0B"},
        {"trbce method exit in effect for", "\u6B63\u5728\u5BF9{0}\u5B9E\u884C trbce method exit"},
        {"trbce method exits in effect", "\u6B63\u5728\u5B9E\u884C trbce method exits"},
        {"trbce methods in effect", "\u6B63\u5728\u5B9E\u884C trbce methods"},
        {"trbce go method exit in effect for", "\u6B63\u5728\u5BF9{0}\u5B9E\u884C trbce go method exit"},
        {"trbce go method exits in effect", "\u6B63\u5728\u5B9E\u884C trbce go method exits"},
        {"trbce go methods in effect", "\u6B63\u5728\u5B9E\u884C trbce go methods"},
        {"trbce not in effect", "\u672A\u5B9E\u884C trbce"},
        {"Unbble to bttbch to tbrget VM.", "\u65E0\u6CD5\u9644\u52A0\u5230\u76EE\u6807 VM\u3002"},
        {"Unbble to displby process output:", "\u65E0\u6CD5\u663E\u793A\u8FDB\u7A0B\u8F93\u51FA: {0}"},
        {"Unbble to lbunch tbrget VM.", "\u65E0\u6CD5\u542F\u52A8\u76EE\u6807 VM\u3002"},
        {"Unbble to set deferred", "\u65E0\u6CD5\u8BBE\u7F6E\u5EF6\u8FDF\u7684{0}: {1}"},
        {"Unbble to set mbin clbss bnd brguments", "\u65E0\u6CD5\u8BBE\u7F6E\u4E3B\u7C7B\u548C\u53C2\u6570"},
        {"Unbble to set", "\u65E0\u6CD5\u8BBE\u7F6E{0}: {1}"},
        {"Unexpected event type", "\u610F\u5916\u7684\u4E8B\u4EF6\u7C7B\u578B: {0}"},
        {"unknown", "\u672A\u77E5"},
        {"Unmonitoring", "\u53D6\u6D88\u76D1\u89C6{0} "},
        {"Unrecognized commbnd.  Try help...", "\u65E0\u6CD5\u8BC6\u522B\u7684\u547D\u4EE4: ''{0}''\u3002\u8BF7\u5C1D\u8BD5\u83B7\u5F97\u5E2E\u52A9..."},
        {"Usbge: cbtch exception", "\u7528\u6CD5: cbtch [uncbught|cbught|bll] <clbss id>|<clbss pbttern>"},
        {"Usbge: ignore exception", "\u7528\u6CD5: ignore [uncbught|cbught|bll] <clbss id>|<clbss pbttern>"},
        {"Usbge: down [n frbmes]", "\u7528\u6CD5: down [n frbmes]"},
        {"Usbge: kill <threbd id> <throwbble>", "\u7528\u6CD5: kill <threbd id> <throwbble>"},
        {"Usbge: rebd <commbnd-filenbme>", "\u7528\u6CD5: rebd <commbnd-filenbme>"},
        {"Usbge: unmonitor <monitor#>", "\u7528\u6CD5: unmonitor <monitor#>"},
        {"Usbge: up [n frbmes]", "\u7528\u6CD5: up [n frbmes]"},
        {"Use jbvb minus X to see", "\u4F7F\u7528 'jbvb -X' \u53EF\u4EE5\u67E5\u770B\u53EF\u7528\u7684\u975E\u6807\u51C6\u9009\u9879"},
        {"Use stop bt to set b brebkpoint bt b line number", "\u4F7F\u7528 'stop bt' \u53EF\u4EE5\u5728\u884C\u53F7\u5904\u8BBE\u7F6E\u65AD\u70B9"},
        {"VM blrebdy running. use cont to continue bfter events.", "VM \u5DF2\u5728\u8FD0\u884C\u3002\u8BF7\u4F7F\u7528 'cont' \u4EE5\u5728\u4E8B\u4EF6\u7ED3\u675F\u540E\u7EE7\u7EED\u3002"},
        {"VM Stbrted:", "VM \u5DF2\u542F\u52A8: "},
        {"vmstbrtexception", "VM \u542F\u52A8\u5F02\u5E38\u9519\u8BEF: {0}"},
        {"Wbiting for monitor:", "   \u6B63\u5728\u7B49\u5F85\u76D1\u89C6\u5668: {0}"},
        {"Wbiting threbd:", " \u6B63\u5728\u7B49\u5F85\u7EBF\u7A0B: {0}"},
        {"wbtch bccesses of", "\u76D1\u89C6{0}.{1}\u7684\u8BBF\u95EE"},
        {"wbtch modificbtion of", "\u76D1\u89C6{0}.{1}\u7684\u4FEE\u6539"},
        {"zz help text",
             "** \u547D\u4EE4\u5217\u8868 **\nconnectors                -- \u5217\u51FA\u6B64 VM \u4E2D\u53EF\u7528\u7684\u8FDE\u63A5\u5668\u548C\u4F20\u8F93\n\nrun [clbss [brgs]]        -- \u5F00\u59CB\u6267\u884C\u5E94\u7528\u7A0B\u5E8F\u7684\u4E3B\u7C7B\n\nthrebds [threbdgroup]     -- \u5217\u51FA\u7EBF\u7A0B\nthrebd <threbd id>        -- \u8BBE\u7F6E\u9ED8\u8BA4\u7EBF\u7A0B\nsuspend [threbd id(s)]    -- \u6302\u8D77\u7EBF\u7A0B (\u9ED8\u8BA4\u503C: bll)\nresume [threbd id(s)]     -- \u6062\u590D\u7EBF\u7A0B (\u9ED8\u8BA4\u503C: bll)\nwhere [<threbd id> | bll] -- \u8F6C\u50A8\u7EBF\u7A0B\u7684\u5806\u6808\nwherei [<threbd id> | bll]-- \u8F6C\u50A8\u7EBF\u7A0B\u7684\u5806\u6808, \u4EE5\u53CA pc \u4FE1\u606F\nup [n frbmes]             -- \u4E0A\u79FB\u7EBF\u7A0B\u7684\u5806\u6808\ndown [n frbmes]           -- \u4E0B\u79FB\u7EBF\u7A0B\u7684\u5806\u6808\nkill <threbd id> <expr>   -- \u7EC8\u6B62\u5177\u6709\u7ED9\u5B9A\u7684\u5F02\u5E38\u9519\u8BEF\u5BF9\u8C61\u7684\u7EBF\u7A0B\ninterrupt <threbd id>     -- \u4E2D\u65AD\u7EBF\u7A0B\n\nprint <expr>              -- \u8F93\u51FA\u8868\u8FBE\u5F0F\u7684\u503C\ndump <expr>               -- \u8F93\u51FA\u6240\u6709\u5BF9\u8C61\u4FE1\u606F\nevbl <expr>               -- \u5BF9\u8868\u8FBE\u5F0F\u6C42\u503C (\u4E0E print \u76F8\u540C)\nset <lvblue> = <expr>     -- \u5411\u5B57\u6BB5/\u53D8\u91CF/\u6570\u7EC4\u5143\u7D20\u5206\u914D\u65B0\u503C\nlocbls                    -- \u8F93\u51FA\u5F53\u524D\u5806\u6808\u5E27\u4E2D\u7684\u6240\u6709\u672C\u5730\u53D8\u91CF\n\nclbsses                   -- \u5217\u51FA\u5F53\u524D\u5DF2\u77E5\u7684\u7C7B\nclbss <clbss id>          -- \u663E\u793A\u5DF2\u547D\u540D\u7C7B\u7684\u8BE6\u7EC6\u8D44\u6599\nmethods <clbss id>        -- \u5217\u51FA\u7C7B\u7684\u65B9\u6CD5\nfields <clbss id>         -- \u5217\u51FA\u7C7B\u7684\u5B57\u6BB5\n\nthrebdgroups              -- \u5217\u51FA\u7EBF\u7A0B\u7EC4\nthrebdgroup <nbme>        -- \u8BBE\u7F6E\u5F53\u524D\u7EBF\u7A0B\u7EC4\n\nstop in <clbss id>.<method>[(brgument_type,...)]\n                          -- \u5728\u65B9\u6CD5\u4E2D\u8BBE\u7F6E\u65AD\u70B9\nstop bt <clbss id>:<line> -- \u5728\u884C\u4E2D\u8BBE\u7F6E\u65AD\u70B9\nclebr <clbss id>.<method>[(brgument_type,...)]\n                          -- \u6E05\u9664\u65B9\u6CD5\u4E2D\u7684\u65AD\u70B9\nclebr <clbss id>:<line>   -- \u6E05\u9664\u884C\u4E2D\u7684\u65AD\u70B9\nclebr                     -- \u5217\u51FA\u65AD\u70B9\ncbtch [uncbught|cbught|bll] <clbss id>|<clbss pbttern>\n                          -- \u51FA\u73B0\u6307\u5B9A\u7684\u5F02\u5E38\u9519\u8BEF\u65F6\u4E2D\u65AD\nignore [uncbught|cbught|bll] <clbss id>|<clbss pbttern>\n                          -- \u5BF9\u4E8E\u6307\u5B9A\u7684\u5F02\u5E38\u9519\u8BEF, \u53D6\u6D88 'cbtch'\nwbtch [bccess|bll] <clbss id>.<field nbme>\n                          -- \u76D1\u89C6\u5BF9\u5B57\u6BB5\u7684\u8BBF\u95EE/\u4FEE\u6539\nunwbtch [bccess|bll] <clbss id>.<field nbme>\n                          -- \u505C\u6B62\u76D1\u89C6\u5BF9\u5B57\u6BB5\u7684\u8BBF\u95EE/\u4FEE\u6539\ntrbce [go] methods [threbd]\n                          -- \u8DDF\u8E2A\u65B9\u6CD5\u8FDB\u5165\u548C\u9000\u51FA\u3002\n                          -- \u9664\u975E\u6307\u5B9A 'go', \u5426\u5219\u6302\u8D77\u6240\u6709\u7EBF\u7A0B\ntrbce [go] method exit | exits [threbd]\n                          -- \u8DDF\u8E2A\u5F53\u524D\u65B9\u6CD5\u7684\u9000\u51FA, \u6216\u8005\u6240\u6709\u65B9\u6CD5\u7684\u9000\u51FA\n                          -- \u9664\u975E\u6307\u5B9A 'go', \u5426\u5219\u6302\u8D77\u6240\u6709\u7EBF\u7A0B\nuntrbce [methods]         -- \u505C\u6B62\u8DDF\u8E2A\u65B9\u6CD5\u8FDB\u5165\u548C/\u6216\u9000\u51FA\nstep                      -- \u6267\u884C\u5F53\u524D\u884C\nstep up                   -- \u4E00\u76F4\u6267\u884C, \u76F4\u5230\u5F53\u524D\u65B9\u6CD5\u8FD4\u56DE\u5230\u5176\u8C03\u7528\u65B9\nstepi                     -- \u6267\u884C\u5F53\u524D\u6307\u4EE4\n\u4E0B\u4E00\u6B65                      -- \u6B65\u8FDB\u4E00\u884C (\u6B65\u8FC7\u8C03\u7528)\ncont                      -- \u4ECE\u65AD\u70B9\u5904\u7EE7\u7EED\u6267\u884C\n\nlist [line number|method] -- \u8F93\u51FA\u6E90\u4EE3\u7801\nuse (\u6216 sourcepbth) [source file pbth]\n                          -- \u663E\u793A\u6216\u66F4\u6539\u6E90\u8DEF\u5F84\nexclude [<clbss pbttern>, ... | \"none\"]\n                          -- \u5BF9\u4E8E\u6307\u5B9A\u7684\u7C7B, \u4E0D\u62A5\u544A\u6B65\u9AA4\u6216\u65B9\u6CD5\u4E8B\u4EF6\nclbsspbth                 -- \u4ECE\u76EE\u6807 VM \u8F93\u51FA\u7C7B\u8DEF\u5F84\u4FE1\u606F\n\nmonitor <commbnd>         -- \u6BCF\u6B21\u7A0B\u5E8F\u505C\u6B62\u65F6\u6267\u884C\u547D\u4EE4\nmonitor                   -- \u5217\u51FA\u76D1\u89C6\u5668\nunmonitor <monitor#>      -- \u5220\u9664\u76D1\u89C6\u5668\nrebd <filenbme>           -- \u8BFB\u53D6\u5E76\u6267\u884C\u547D\u4EE4\u6587\u4EF6\n\nlock <expr>               -- \u8F93\u51FA\u5BF9\u8C61\u7684\u9501\u4FE1\u606F\nthrebdlocks [threbd id]   -- \u8F93\u51FA\u7EBF\u7A0B\u7684\u9501\u4FE1\u606F\n\npop                       -- \u901A\u8FC7\u5F53\u524D\u5E27\u51FA\u6808, \u4E14\u5305\u542B\u5F53\u524D\u5E27\nreenter                   -- \u4E0E pop \u76F8\u540C, \u4F46\u91CD\u65B0\u8FDB\u5165\u5F53\u524D\u5E27\nredefine <clbss id> <clbss file nbme>\n                          -- \u91CD\u65B0\u5B9A\u4E49\u7C7B\u7684\u4EE3\u7801\n\ndisbblegc <expr>          -- \u7981\u6B62\u5BF9\u8C61\u7684\u5783\u573E\u6536\u96C6\nenbblegc <expr>           -- \u5141\u8BB8\u5BF9\u8C61\u7684\u5783\u573E\u6536\u96C6\n\n!!                        -- \u91CD\u590D\u6267\u884C\u6700\u540E\u4E00\u4E2A\u547D\u4EE4\n<n> <commbnd>             -- \u5C06\u547D\u4EE4\u91CD\u590D\u6267\u884C n \u6B21\n# <commbnd>               -- \u653E\u5F03 (\u65E0\u64CD\u4F5C)\nhelp (\u6216 ?)               -- \u5217\u51FA\u547D\u4EE4\nversion                   -- \u8F93\u51FA\u7248\u672C\u4FE1\u606F\nexit (\u6216 quit)            -- \u9000\u51FA\u8C03\u8BD5\u5668\n\n<clbss id>: \u5E26\u6709\u7A0B\u5E8F\u5305\u9650\u5B9A\u7B26\u7684\u5B8C\u6574\u7C7B\u540D\n<clbss pbttern>: \u5E26\u6709\u524D\u5BFC\u6216\u5C3E\u968F\u901A\u914D\u7B26 ('*') \u7684\u7C7B\u540D\n<threbd id>: 'threbds' \u547D\u4EE4\u4E2D\u62A5\u544A\u7684\u7EBF\u7A0B\u7F16\u53F7\n<expr>: Jbvb(TM) \u7F16\u7A0B\u8BED\u8A00\u8868\u8FBE\u5F0F\u3002\n\u652F\u6301\u5927\u591A\u6570\u5E38\u89C1\u8BED\u6CD5\u3002\n\n\u53EF\u4EE5\u5C06\u542F\u52A8\u547D\u4EE4\u7F6E\u4E8E \"jdb.ini\" \u6216 \".jdbrc\" \u4E2D\n\u4F4D\u4E8E user.home \u6216 user.dir \u4E2D"},
        {"zz usbge text",
             "\u7528\u6CD5: {0} <options> <clbss> <brguments>\n\n\u5176\u4E2D, \u9009\u9879\u5305\u62EC:\n    -help             \u8F93\u51FA\u6B64\u6D88\u606F\u5E76\u9000\u51FA\n    -sourcepbth <\u7531 \"{1}\" \u5206\u9694\u7684\u76EE\u5F55>\n                      \u8981\u5728\u5176\u4E2D\u67E5\u627E\u6E90\u6587\u4EF6\u7684\u76EE\u5F55\n    -bttbch <bddress>\n                      \u4F7F\u7528\u6807\u51C6\u8FDE\u63A5\u5668\u9644\u52A0\u5230\u6307\u5B9A\u5730\u5740\u5904\u6B63\u5728\u8FD0\u884C\u7684 VM\n    -listen <bddress>\n                      \u7B49\u5F85\u6B63\u5728\u8FD0\u884C\u7684 VM \u4F7F\u7528\u6807\u51C6\u8FDE\u63A5\u5668\u5728\u6307\u5B9A\u5730\u5740\u5904\u8FDE\u63A5\n    -listenbny\n                      \u7B49\u5F85\u6B63\u5728\u8FD0\u884C\u7684 VM \u4F7F\u7528\u6807\u51C6\u8FDE\u63A5\u5668\u5728\u4EFB\u4F55\u53EF\u7528\u5730\u5740\u5904\u8FDE\u63A5\n    -lbunch\n                      \u7ACB\u5373\u542F\u52A8 VM \u800C\u4E0D\u662F\u7B49\u5F85 ''run'' \u547D\u4EE4\n    -listconnectors   \u5217\u51FA\u6B64 VM \u4E2D\u7684\u53EF\u7528\u8FDE\u63A5\u5668\n    -connect <connector-nbme>:<nbme1>=<vblue1>,...\n                      \u4F7F\u7528\u6240\u5217\u53C2\u6570\u503C\u901A\u8FC7\u6307\u5B9A\u7684\u8FDE\u63A5\u5668\u8FDE\u63A5\u5230\u76EE\u6807 VM\n    -dbgtrbce [flbgs] \u8F93\u51FA\u4FE1\u606F\u4F9B\u8C03\u8BD5{0}\n    -tclient          \u5728 HotSpot(TM) \u5BA2\u6237\u673A\u7F16\u8BD1\u5668\u4E2D\u8FD0\u884C\u5E94\u7528\u7A0B\u5E8F\n    -tserver          \u5728 HotSpot(TM) \u670D\u52A1\u5668\u7F16\u8BD1\u5668\u4E2D\u8FD0\u884C\u5E94\u7528\u7A0B\u5E8F\n\n\u8F6C\u53D1\u5230\u88AB\u8C03\u8BD5\u8FDB\u7A0B\u7684\u9009\u9879:\n    -v -verbose[:clbss|gc|jni]\n                      \u542F\u7528\u8BE6\u7EC6\u6A21\u5F0F\n    -D<nbme>=<vblue>  \u8BBE\u7F6E\u7CFB\u7EDF\u5C5E\u6027\n    -clbsspbth <\u7531 \"{1}\" \u5206\u9694\u7684\u76EE\u5F55>\n                      \u5217\u51FA\u8981\u5728\u5176\u4E2D\u67E5\u627E\u7C7B\u7684\u76EE\u5F55\n    -X<option>        \u975E\u6807\u51C6\u76EE\u6807 VM \u9009\u9879\n\n<clbss> \u662F\u8981\u5F00\u59CB\u8C03\u8BD5\u7684\u7C7B\u7684\u540D\u79F0\n<brguments> \u662F\u4F20\u9012\u5230 <clbss> \u7684 mbin() \u65B9\u6CD5\u7684\u53C2\u6570\n\n\u8981\u83B7\u5F97\u547D\u4EE4\u7684\u5E2E\u52A9, \u8BF7\u5728{0}\u63D0\u793A\u4E0B\u952E\u5165 ''help''"},
        // END OF MATERIAL TO LOCALIZE
        };

        return temp;
    }
}
