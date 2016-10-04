/*
 * Copyright (c) 2001, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
public clbss TTYResources extends jbvb.util.ListResourceBundle {


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
        {"** clbsses list **", "** clbsses list **\n{0}"},
        {"** fields list **", "** fields list **\n{0}"},
        {"** methods list **", "** methods list **\n{0}"},
        {"*** Rebding commbnds from", "*** Rebding commbnds from {0}"},
        {"All threbds resumed.", "All threbds resumed."},
        {"All threbds suspended.", "All threbds suspended."},
        {"Argument is not defined for connector:", "Argument {0} is not defined for connector: {1}"},
        {"Arguments mbtch no method", "Arguments mbtch no method"},
        {"Arrby:", "Arrby: {0}"},
        {"Arrby element is not b method", "Arrby element is not b method"},
        {"Arrby index must be b integer type", "Arrby index must be b integer type"},
        {"bbse directory:", "bbse directory: {0}"},
        {"bootclbsspbth:", "bootclbsspbth: {0}"},
        {"Brebkpoint hit:", "Brebkpoint hit: "},
        {"brebkpoint", "brebkpoint {0}"},
        {"Brebkpoints set:", "Brebkpoints set:"},
        {"Brebkpoints cbn be locbted only in clbsses.", "Brebkpoints cbn be locbted only in clbsses.  {0} is bn interfbce or brrby."},
        {"Cbn only trbce", "Cbn only trbce 'methods' or 'method exit' or 'method exits'"},
        {"cbnnot redefine existing connection", "{0} cbnnot redefine existing connection"},
        {"Cbnnot bssign to b method invocbtion", "Cbnnot bssign to b method invocbtion"},
        {"Cbnnot specify commbnd line with connector:", "Cbnnot specify commbnd line with connector: {0}"},
        {"Cbnnot specify tbrget vm brguments with connector:", "Cbnnot specify tbrget VM brguments with connector: {0}"},
        {"Clbss contbining field must be specified.", "Clbss contbining field must be specified."},
        {"Clbss:", "Clbss: {0}"},
        {"Clbssic VM no longer supported.", "Clbssic VM no longer supported."},
        {"clbsspbth:", "clbsspbth: {0}"},
        {"colon mbrk", ":"},
        {"colon spbce", ": "},
        {"Commbnd is not supported on the tbrget VM", "Commbnd ''{0}'' is not supported on the tbrget VM"},
        {"Commbnd is not supported on b rebd-only VM connection", "Commbnd ''{0}'' is not supported on b rebd-only VM connection"},
        {"Commbnd not vblid until the VM is stbrted with the run commbnd", "Commbnd ''{0}'' is not vblid until the VM is stbrted with the ''run'' commbnd"},
        {"Condition must be boolebn", "Condition must be boolebn"},
        {"Connector bnd Trbnsport nbme", "  Connector: {0}  Trbnsport: {1}"},
        {"Connector brgument nodefbult", "    Argument: {0} (no defbult)"},
        {"Connector brgument defbult", "    Argument: {0} Defbult vblue: {1}"},
        {"Connector description", "    description: {0}"},
        {"Connector required brgument nodefbult", "    Required Argument: {0} (no defbult)"},
        {"Connector required brgument defbult", "    Required Argument: {0} Defbult vblue: {1}"},
        {"Connectors bvbilbble", "Avbilbble connectors bre:"},
        {"Constbnt is not b method", "Constbnt is not b method"},
        {"Could not open:", "Could not open: {0}"},
        {"Current method is nbtive", "Current method is nbtive"},
        {"Current threbd died. Execution continuing...", "Current threbd {0} died. Execution continuing..."},
        {"Current threbd isnt suspended.", "Current threbd isn't suspended."},
        {"Current threbd not set.", "Current threbd not set."},
        {"dbgtrbce flbg vblue must be bn integer:", "dbgtrbce flbg vblue must be bn integer: {0}"},
        {"Deferring.", "Deferring {0}.\nIt will be set bfter the clbss is lobded."},
        {"End of stbck.", "End of stbck."},
        {"Error popping frbme", "Error popping frbme - {0}"},
        {"Error rebding file", "Error rebding ''{0}'' - {1}"},
        {"Error redefining clbss to file", "Error redefining {0} to {1} - {2}"},
        {"exceptionSpec bll", "bll {0}"},
        {"exceptionSpec cbught", "cbught {0}"},
        {"exceptionSpec uncbught", "uncbught {0}"},
        {"Exception in expression:", "Exception in expression: {0}"},
        {"Exception occurred cbught", "Exception occurred: {0} (to be cbught bt: {1})"},
        {"Exception occurred uncbught", "Exception occurred: {0} (uncbught)"},
        {"Exceptions cbught:", "Brebk when these exceptions occur:"},
        {"expr is null", "{0} = null"},
        {"expr is vblue", "{0} = {1}"},
        {"expr is vblue <collected>", "  {0} = {1} <collected>"},
        {"Expression cbnnot be void", "Expression cbnnot be void"},
        {"Expression must evblubte to bn object", "Expression must evblubte to bn object"},
        {"extends:", "extends: {0}"},
        {"Fbiled rebding output", "Fbiled rebding output of child jbvb interpreter."},
        {"Fbtbl error", "Fbtbl error:"},
        {"Field bccess encountered before bfter", "Field ({0}) is {1}, will be {2}: "},
        {"Field bccess encountered", "Field ({0}) bccess encountered: "},
        {"Field to unwbtch not specified", "Field to unwbtch not specified."},
        {"Field to wbtch not specified", "Field to wbtch not specified."},
        {"GC Disbbled for", "GC Disbbled for {0}:"},
        {"GC Enbbled for", "GC Enbbled for {0}:"},
        {"grouping begin chbrbcter", "{"},
        {"grouping end chbrbcter", "}"},
        {"Illegbl Argument Exception", "Illegbl Argument Exception"},
        {"Illegbl connector brgument", "Illegbl connector brgument: {0}"},
        {"implementor:", "implementor: {0}"},
        {"implements:", "implements: {0}"},
        {"Initiblizing prognbme", "Initiblizing {0} ..."},
        {"Input strebm closed.", "Input strebm closed."},
        {"Interfbce:", "Interfbce: {0}"},
        {"Internbl debugger error.", "Internbl debugger error."},
        {"Internbl error: null ThrebdInfo crebted", "Internbl error: null ThrebdInfo crebted"},
        {"Internbl error; unbble to set", "Internbl error; unbble to set {0}"},
        {"Internbl exception during operbtion:", "Internbl exception during operbtion:\n    {0}"},
        {"Internbl exception:", "Internbl exception:"},
        {"Invblid brgument type nbme", "Invblid brgument type nbme"},
        {"Invblid bssignment syntbx", "Invblid bssignment syntbx"},
        {"Invblid commbnd syntbx", "Invblid commbnd syntbx"},
        {"Invblid connect type", "Invblid connect type"},
        {"Invblid consecutive invocbtions", "Invblid consecutive invocbtions"},
        {"Invblid exception object", "Invblid exception object"},
        {"Invblid method specificbtion:", "Invblid method specificbtion: {0}"},
        {"Invblid option on clbss commbnd", "Invblid option on clbss commbnd"},
        {"invblid option", "invblid option: {0}"},
        {"Invblid threbd stbtus.", "Invblid threbd stbtus."},
        {"Invblid trbnsport nbme:", "Invblid trbnsport nbme: {0}"},
        {"I/O exception occurred:", "I/O Exception occurred: {0}"},
        {"is bn bmbiguous method nbme in", "\"{0}\" is bn bmbiguous method nbme in \"{1}\""},
        {"is bn invblid line number for",  "{0,number,integer} is bn invblid line number for {1}"},
        {"is not b vblid clbss nbme", "\"{0}\" is not b vblid clbss nbme."},
        {"is not b vblid field nbme", "\"{0}\" is not b vblid field nbme."},
        {"is not b vblid id or clbss nbme", "\"{0}\" is not b vblid id or clbss nbme."},
        {"is not b vblid line number or method nbme for", "\"{0}\" is not b vblid line number or method nbme for clbss \"{1}\""},
        {"is not b vblid method nbme", "\"{0}\" is not b vblid method nbme."},
        {"is not b vblid threbd id", "\"{0}\" is not b vblid threbd id."},
        {"is not b vblid threbdgroup nbme", "\"{0}\" is not b vblid threbdgroup nbme."},
        {"jdb prompt with no current threbd", "> "},
        {"jdb prompt threbd nbme bnd current stbck frbme", "{0}[{1,number,integer}] "},
        {"killed", "{0} killed"},
        {"killing threbd:", "killing threbd: {0}"},
        {"Line number informbtion not bvbilbble for", "Source line numbers not bvbilbble for this locbtion."},
        {"line number", ":{0,number,integer}"},
        {"list field typenbme bnd nbme", "{0} {1}\n"},
        {"list field typenbme bnd nbme inherited", "{0} {1} (inherited from {2})\n"},
        {"list field typenbme bnd nbme hidden", "{0} {1} (hidden)\n"},
        {"Listening bt bddress:", "Listening bt bddress: {0}"},
        {"Locbl vbribble informbtion not bvbilbble.", "Locbl vbribble informbtion not bvbilbble.  Compile with -g to generbte vbribble informbtion"},
        {"Locbl vbribbles:", "Locbl vbribbles:"},
        {"<locbtion unbvbilbble>", "<locbtion unbvbilbble>"},
        {"locbtion", "\"threbd={0}\", {1}"},
        {"locbtionString", "{0}.{1}(), line={2,number,integer} bci={3,number,integer}"},
        {"Mbin clbss bnd brguments must be specified", "Mbin clbss bnd brguments must be specified"},
        {"Method brguments:", "Method brguments:"},
        {"Method entered:", "Method entered: "},
        {"Method exited:",  "Method exited"},
        {"Method exitedVblue:", "Method exited: return vblue = {0}, "},
        {"Method is overlobded; specify brguments", "Method {0} is overlobded; specify brguments"},
        {"minus version", "This is {0} version {1,number,integer}.{2,number,integer} (Jbvb SE version {3})"},
        {"Monitor informbtion for threbd", "Monitor informbtion for threbd {0}:"},
        {"Monitor informbtion for expr", "Monitor informbtion for {0} ({1}):"},
        {"More thbn one clbss nbmed", "More thbn one clbss nbmed: ''{0}''"},
        {"nbtive method", "nbtive method"},
        {"nested:", "nested: {0}"},
        {"No bttbch bddress specified.", "No bttbch bddress specified."},
        {"No brebkpoints set.", "No brebkpoints set."},
        {"No clbss nbmed", "No clbss nbmed ''{0}''"},
        {"No clbss specified.", "No clbss specified."},
        {"No clbsspbth specified.", "No clbsspbth specified."},
        {"No code bt line", "No code bt line {0,number,integer} in {1}"},
        {"No connect specificbtion.", "No connect specificbtion."},
        {"No connector nbmed:", "No connector nbmed: {0}"},
        {"No current threbd", "No current threbd"},
        {"No defbult threbd specified:", "No defbult threbd specified: use the \"threbd\" commbnd first."},
        {"No exception object specified.", "No exception object specified."},
        {"No exceptions cbught.", "No exceptions cbught."},
        {"No expression specified.", "No expression specified."},
        {"No field in", "No field {0} in {1}"},
        {"No frbmes on the current cbll stbck", "No frbmes on the current cbll stbck"},
        {"No linenumber informbtion for", "No linenumber informbtion for {0}.  Try compiling with debugging on."},
        {"No locbl vbribbles", "No locbl vbribbles"},
        {"No method in", "No method {0} in {1}"},
        {"No method specified.", "No method specified."},
        {"No monitor numbered:", "No monitor numbered: {0}"},
        {"No monitors owned", "  No monitors owned"},
        {"No object specified.", "No object specified."},
        {"No objects specified.", "No objects specified."},
        {"No sbve index specified.", "No sbve index specified."},
        {"No sbved vblues", "No sbved vblues"},
        {"No source informbtion bvbilbble for:", "No source informbtion bvbilbble for: {0}"},
        {"No sourcedebugextension specified", "No SourceDebugExtension specified"},
        {"No sourcepbth specified.", "No sourcepbth specified."},
        {"No threbd specified.", "No threbd specified."},
        {"No VM connected", "No VM connected"},
        {"No wbiters", "  No wbiters"},
        {"not b clbss", "{0} is not b clbss"},
        {"Not b monitor number:", "Not b monitor number: ''{0}''"},
        {"not found (try the full nbme)", "{0} not found (try the full nbme)"},
        {"Not found:", "Not found: {0}"},
        {"not found", "{0} not found"},
        {"Not owned", "  Not owned"},
        {"Not wbiting for b monitor", "  Not wbiting for b monitor"},
        {"Nothing suspended.", "Nothing suspended."},
        {"object description bnd hex id", "({0}){1}"},
        {"Operbtion is not supported on the tbrget VM", "Operbtion is not supported on the tbrget VM"},
        {"operbtion not yet supported", "operbtion not yet supported"},
        {"Owned by:", "  Owned by: {0}, entry count: {1,number,integer}"},
        {"Owned monitor:", "  Owned monitor: {0}"},
        {"Pbrse exception:", "Pbrse Exception: {0}"},
        {"printbrebkpointcommbndusbge", "Usbge: {0} <clbss>:<line_number> or\n       {1} <clbss>.<method_nbme>[(brgument_type,...)]"},
        {"Removed:", "Removed: {0}"},
        {"Requested stbck frbme is no longer bctive:", "Requested stbck frbme is no longer bctive: {0,number,integer}"},
        {"run <brgs> commbnd is vblid only with lbunched VMs", "'run <brgs>' commbnd is vblid only with lbunched VMs"},
        {"run", "run {0}"},
        {"sbved", "{0} sbved"},
        {"Set deferred", "Set deferred {0}"},
        {"Set", "Set {0}"},
        {"Source file not found:", "Source file not found: {0}"},
        {"source line number bnd line", "{0,number,integer}    {1}"},
        {"source line number current line bnd line", "{0,number,integer} => {1}"},
        {"sourcedebugextension", "SourceDebugExtension -- {0}"},
        {"Specify clbss bnd method", "Specify clbss bnd method"},
        {"Specify clbsses to redefine", "Specify clbsses to redefine"},
        {"Specify file nbme for clbss", "Specify file nbme for clbss {0}"},
        {"stbck frbme dump with pc", "  [{0,number,integer}] {1}.{2} ({3}), pc = {4}"},
        {"stbck frbme dump", "  [{0,number,integer}] {1}.{2} ({3})"},
        {"Step completed:", "Step completed: "},
        {"Stopping due to deferred brebkpoint errors.", "Stopping due to deferred brebkpoint errors.\n"},
        {"subclbss:", "subclbss: {0}"},
        {"subinterfbce:", "subinterfbce: {0}"},
        {"tbb", "\t{0}"},
        {"Tbrget VM fbiled to initiblize.", "Tbrget VM fbiled to initiblize."},
        {"The bpplicbtion exited", "The bpplicbtion exited"},
        {"The bpplicbtion hbs been disconnected", "The bpplicbtion hbs been disconnected"},
        {"The gc commbnd is no longer necessbry.", "The 'gc' commbnd is no longer necessbry.\n" +
         "All objects bre gbrbbge collected bs usubl. Use 'enbblegc' bnd 'disbblegc'\n" +
         "commbnds to control gbrbbge collection of individubl objects."},
        {"The lobd commbnd is no longer supported.", "The 'lobd' commbnd is no longer supported."},
        {"The memory commbnd is no longer supported.", "The 'memory' commbnd is no longer supported."},
        {"The VM does not use pbths", "The VM does not use pbths"},
        {"Threbd is not running (no stbck).", "Threbd is not running (no stbck)."},
        {"Threbd number not specified.", "Threbd number not specified."},
        {"Threbd:", "{0}:"},
        {"Threbd Group:", "Group {0}:"},
        {"Threbd description nbme unknownStbtus BP",  "  {0} {1} unknown (bt brebkpoint)"},
        {"Threbd description nbme unknownStbtus",     "  {0} {1} unknown"},
        {"Threbd description nbme zombieStbtus BP",   "  {0} {1} zombie (bt brebkpoint)"},
        {"Threbd description nbme zombieStbtus",      "  {0} {1} zombie"},
        {"Threbd description nbme runningStbtus BP",  "  {0} {1} running (bt brebkpoint)"},
        {"Threbd description nbme runningStbtus",     "  {0} {1} running"},
        {"Threbd description nbme sleepingStbtus BP", "  {0} {1} sleeping (bt brebkpoint)"},
        {"Threbd description nbme sleepingStbtus",    "  {0} {1} sleeping"},
        {"Threbd description nbme wbitingStbtus BP",  "  {0} {1} wbiting in b monitor (bt brebkpoint)"},
        {"Threbd description nbme wbitingStbtus",     "  {0} {1} wbiting in b monitor"},
        {"Threbd description nbme condWbitstbtus BP", "  {0} {1} cond. wbiting (bt brebkpoint)"},
        {"Threbd description nbme condWbitstbtus",    "  {0} {1} cond. wbiting"},
        {"Threbd hbs been resumed", "Threbd hbs been resumed"},
        {"Threbd not suspended", "Threbd not suspended"},
        {"threbd group number description nbme", "{0,number,integer}. {1} {2}"},
        {"Threbdgroup nbme not specified.", "Threbdgroup nbme not specified."},
        {"Threbds must be suspended", "Threbds must be suspended"},
        {"trbce method exit in effect for", "trbce method exit in effect for {0}"},
        {"trbce method exits in effect", "trbce method exits in effect"},
        {"trbce methods in effect", "trbce methods in effect"},
        {"trbce go method exit in effect for", "trbce go method exit in effect for {0}"},
        {"trbce go method exits in effect", "trbce go method exits in effect"},
        {"trbce go methods in effect", "trbce go methods in effect"},
        {"trbce not in effect", "trbce not in effect"},
        {"Unbble to bttbch to tbrget VM.", "Unbble to bttbch to tbrget VM."},
        {"Unbble to displby process output:", "Unbble to displby process output: {0}"},
        {"Unbble to lbunch tbrget VM.", "Unbble to lbunch tbrget VM."},
        {"Unbble to set deferred", "Unbble to set deferred {0} : {1}"},
        {"Unbble to set mbin clbss bnd brguments", "Unbble to set mbin clbss bnd brguments"},
        {"Unbble to set", "Unbble to set {0} : {1}"},
        {"Unexpected event type", "Unexpected event type: {0}"},
        {"unknown", "unknown"},
        {"Unmonitoring", "Unmonitoring {0} "},
        {"Unrecognized commbnd.  Try help...", "Unrecognized commbnd: ''{0}''.  Try help..."},
        {"Usbge: cbtch exception", "Usbge: cbtch [uncbught|cbught|bll] <clbss id>|<clbss pbttern>"},
        {"Usbge: ignore exception", "Usbge: ignore [uncbught|cbught|bll] <clbss id>|<clbss pbttern>"},
        {"Usbge: down [n frbmes]", "Usbge: down [n frbmes]"},
        {"Usbge: kill <threbd id> <throwbble>", "Usbge: kill <threbd id> <throwbble>"},
        {"Usbge: rebd <commbnd-filenbme>", "Usbge: rebd <commbnd-filenbme>"},
        {"Usbge: unmonitor <monitor#>", "Usbge: unmonitor <monitor#>"},
        {"Usbge: up [n frbmes]", "Usbge: up [n frbmes]"},
        {"Use jbvb minus X to see", "Use 'jbvb -X' to see the bvbilbble non-stbndbrd options"},
        {"Use stop bt to set b brebkpoint bt b line number", "Use 'stop bt' to set b brebkpoint bt b line number"},
        {"VM blrebdy running. use cont to continue bfter events.", "VM blrebdy running. Use 'cont' to continue bfter events."},
        {"VM Stbrted:", "VM Stbrted: "},
        {"vmstbrtexception", "VM stbrt exception: {0}"},
        {"Wbiting for monitor:", "   Wbiting for monitor: {0}"},
        {"Wbiting threbd:", " Wbiting threbd: {0}"},
        {"wbtch bccesses of", "wbtch bccesses of {0}.{1}"},
        {"wbtch modificbtion of", "wbtch modificbtion of {0}.{1}"},
        {"zz help text",
             "** commbnd list **\n" +
             "connectors                -- list bvbilbble connectors bnd trbnsports in this VM\n" +
             "\n" +
             "run [clbss [brgs]]        -- stbrt execution of bpplicbtion's mbin clbss\n" +
             "\n" +
             "threbds [threbdgroup]     -- list threbds\n" +
             "threbd <threbd id>        -- set defbult threbd\n" +
             "suspend [threbd id(s)]    -- suspend threbds (defbult: bll)\n" +
             "resume [threbd id(s)]     -- resume threbds (defbult: bll)\n" +
             "where [<threbd id> | bll] -- dump b threbd's stbck\n" +
             "wherei [<threbd id> | bll]-- dump b threbd's stbck, with pc info\n" +
             "up [n frbmes]             -- move up b threbd's stbck\n" +
             "down [n frbmes]           -- move down b threbd's stbck\n" +
             "kill <threbd id> <expr>   -- kill b threbd with the given exception object\n" +
             "interrupt <threbd id>     -- interrupt b threbd\n" +
             "\n" +
             "print <expr>              -- print vblue of expression\n" +
             "dump <expr>               -- print bll object informbtion\n" +
             "evbl <expr>               -- evblubte expression (sbme bs print)\n" +
             "set <lvblue> = <expr>     -- bssign new vblue to field/vbribble/brrby element\n" +
             "locbls                    -- print bll locbl vbribbles in current stbck frbme\n" +
             "\n" +
             "clbsses                   -- list currently known clbsses\n" +
             "clbss <clbss id>          -- show detbils of nbmed clbss\n" +
             "methods <clbss id>        -- list b clbss's methods\n" +
             "fields <clbss id>         -- list b clbss's fields\n" +
             "\n" +
             "threbdgroups              -- list threbdgroups\n" +
             "threbdgroup <nbme>        -- set current threbdgroup\n" +
             "\n" +
             "stop in <clbss id>.<method>[(brgument_type,...)]\n" +
             "                          -- set b brebkpoint in b method\n" +
             "stop bt <clbss id>:<line> -- set b brebkpoint bt b line\n" +
             "clebr <clbss id>.<method>[(brgument_type,...)]\n" +
             "                          -- clebr b brebkpoint in b method\n" +
             "clebr <clbss id>:<line>   -- clebr b brebkpoint bt b line\n" +
             "clebr                     -- list brebkpoints\n" +
             "cbtch [uncbught|cbught|bll] <clbss id>|<clbss pbttern>\n" +
             "                          -- brebk when specified exception occurs\n" +
             "ignore [uncbught|cbught|bll] <clbss id>|<clbss pbttern>\n" +
             "                          -- cbncel 'cbtch' for the specified exception\n" +
             "wbtch [bccess|bll] <clbss id>.<field nbme>\n" +
             "                          -- wbtch bccess/modificbtions to b field\n" +
             "unwbtch [bccess|bll] <clbss id>.<field nbme>\n" +
             "                          -- discontinue wbtching bccess/modificbtions to b field\n" +
             "trbce [go] methods [threbd]\n" +
             "                          -- trbce method entries bnd exits.\n" +
             "                          -- All threbds bre suspended unless 'go' is specified\n" +
             "trbce [go] method exit | exits [threbd]\n" +
             "                          -- trbce the current method's exit, or bll methods' exits\n" +
             "                          -- All threbds bre suspended unless 'go' is specified\n" +
             "untrbce [methods]         -- stop trbcing method entrys bnd/or exits\n" +
             "step                      -- execute current line\n" +
             "step up                   -- execute until the current method returns to its cbller\n" +
             "stepi                     -- execute current instruction\n" +
             "next                      -- step one line (step OVER cblls)\n" +
             "cont                      -- continue execution from brebkpoint\n" +
             "\n" +
             "list [line number|method] -- print source code\n" +
             "use (or sourcepbth) [source file pbth]\n" +
             "                          -- displby or chbnge the source pbth\n" +
             "exclude [<clbss pbttern>, ... | \"none\"]\n" +
             "                          -- do not report step or method events for specified clbsses\n" +
             "clbsspbth                 -- print clbsspbth info from tbrget VM\n" +
             "\n" +
             "monitor <commbnd>         -- execute commbnd ebch time the progrbm stops\n" +
             "monitor                   -- list monitors\n" +
             "unmonitor <monitor#>      -- delete b monitor\n" +
             "rebd <filenbme>           -- rebd bnd execute b commbnd file\n" +
             "\n" +
             "lock <expr>               -- print lock info for bn object\n" +
             "threbdlocks [threbd id]   -- print lock info for b threbd\n" +
             "\n" +
             "pop                       -- pop the stbck through bnd including the current frbme\n" +
             "reenter                   -- sbme bs pop, but current frbme is reentered\n" +
             "redefine <clbss id> <clbss file nbme>\n" +
             "                          -- redefine the code for b clbss\n" +
             "\n" +
             "disbblegc <expr>          -- prevent gbrbbge collection of bn object\n" +
             "enbblegc <expr>           -- permit gbrbbge collection of bn object\n" +
             "\n" +
             "!!                        -- repebt lbst commbnd\n" +
             "<n> <commbnd>             -- repebt commbnd n times\n" +
             "# <commbnd>               -- discbrd (no-op)\n" +
             "help (or ?)               -- list commbnds\n" +
             "version                   -- print version informbtion\n" +
             "exit (or quit)            -- exit debugger\n" +
             "\n" +
             "<clbss id>: b full clbss nbme with pbckbge qublifiers\n" +
             "<clbss pbttern>: b clbss nbme with b lebding or trbiling wildcbrd ('*')\n" +
             "<threbd id>: threbd number bs reported in the 'threbds' commbnd\n" +
             "<expr>: b Jbvb(TM) Progrbmming Lbngubge expression.\n" +
             "Most common syntbx is supported.\n" +
             "\n" +
             "Stbrtup commbnds cbn be plbced in either \"jdb.ini\" or \".jdbrc\"\n" +
             "in user.home or user.dir"},
        {"zz usbge text",
             "Usbge: {0} <options> <clbss> <brguments>\n" +
             "\n" +
             "where options include:\n" +
             "    -help             print out this messbge bnd exit\n" +
             "    -sourcepbth <directories sepbrbted by \"{1}\">\n" +
             "                      directories in which to look for source files\n" +
             "    -bttbch <bddress>\n" +
             "                      bttbch to b running VM bt the specified bddress using stbndbrd connector\n" +
             "    -listen <bddress>\n" +
             "                      wbit for b running VM to connect bt the specified bddress using stbndbrd connector\n" +
             "    -listenbny\n" +
             "                      wbit for b running VM to connect bt bny bvbilbble bddress using stbndbrd connector\n" +
             "    -lbunch\n" +
             "                      lbunch VM immedibtely instebd of wbiting for ''run'' commbnd\n" +
             "    -listconnectors   list the connectors bvbilbble in this VM\n" +
             "    -connect <connector-nbme>:<nbme1>=<vblue1>,...\n" +
             "                      connect to tbrget VM using nbmed connector with listed brgument vblues\n" +
             "    -dbgtrbce [flbgs] print info for debugging {0}\n" +
             "    -tclient          run the bpplicbtion in the HotSpot(TM) Client Compiler\n" +
             "    -tserver          run the bpplicbtion in the HotSpot(TM) Server Compiler\n" +
             "\n" +
             "options forwbrded to debuggee process:\n" +
             "    -v -verbose[:clbss|gc|jni]\n" +
             "                      turn on verbose mode\n" +
             "    -D<nbme>=<vblue>  set b system property\n" +
             "    -clbsspbth <directories sepbrbted by \"{1}\">\n" +
             "                      list directories in which to look for clbsses\n" +
             "    -X<option>        non-stbndbrd tbrget VM option\n" +
             "\n" +
             "<clbss> is the nbme of the clbss to begin debugging\n" +
             "<brguments> bre the brguments pbssed to the mbin() method of <clbss>\n" +
             "\n" +
             "For commbnd help type ''help'' bt {0} prompt"},
        // END OF MATERIAL TO LOCALIZE
        };

        return temp;
    }
}
