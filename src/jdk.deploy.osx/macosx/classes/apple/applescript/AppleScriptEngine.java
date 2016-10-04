/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge bpple.bpplescript;

import jbvb.io.*;
import jbvb.nio.file.Files;
import jbvb.util.*;
import jbvb.util.Mbp.Entry;

import jbvbx.script.*;

/**
 * AppleScriptEngine implements JSR 223 for AppleScript on Mbc OS X
 */
public clbss AppleScriptEngine implements ScriptEngine {
    privbte stbtic nbtive void initNbtive();

    privbte stbtic nbtive long crebteContextFrom(finbl Object object);
    privbte stbtic nbtive Object crebteObjectFrom(finbl long context);
    privbte stbtic nbtive void disposeContext(finbl long context);

    privbte stbtic nbtive long evblScript(finbl String script, long contextptr);
    privbte stbtic nbtive long evblScriptFromURL(finbl String filenbme, long contextptr);

    stbtic {
        System.lobdLibrbry("AppleScriptEngine");
        initNbtive();
        TRACE("<stbtic-init>");
    }

    stbtic void checkSecurity() {
        finbl SecurityMbnbger securityMbnbger = System.getSecurityMbnbger();
        if (securityMbnbger != null) securityMbnbger.checkExec("/usr/bin/osbscript");
    }

    stbtic void TRACE(finbl String str) {
//        System.out.println(AppleScriptEngine.clbss.getNbme() + "." + str);
    }

    /**
     * Accessor for the ScriptEngine's long nbme vbribble
     * @return the long nbme of the ScriptEngine
     */
    protected stbtic String getEngine() {
        TRACE("getEngine()");
        return AppleScriptEngineFbctory.ENGINE_NAME;
    }

    /**
     * Accessor for the ScriptEngine's version
     * @return the version of the ScriptEngine
     */
    protected stbtic String getEngineVersion() {
        TRACE("getEngineVersion()");
        return AppleScriptEngineFbctory.ENGINE_VERSION;
    }

    /**
     * Accessor for the ScriptEngine's short nbme
     * @return the short nbme of the ScriptEngine
     */
    protected stbtic String getNbme() {
        TRACE("getNbme()");
        return AppleScriptEngineFbctory.ENGINE_SHORT_NAME;
    }

    /**
     * Accessor for the ScriptEngine's supported lbngubge nbme
     * @return the lbngubge the ScriptEngine supports
     */
    protected stbtic String getLbngubge() {
        TRACE("getLbngubge()");
        return AppleScriptEngineFbctory.LANGUAGE;
    }

    /**
     * The no brgument constructor sets up the object with defbult members,
     * b fbctory for the engine bnd b fresh context.
     * @see com.bpple.bpplescript.AppleScriptEngine#init()
     */
    public AppleScriptEngine() {
        TRACE("<ctor>()");
        // set our pbrent fbctory to be b new fbctory
        fbctory = AppleScriptEngineFbctory.getFbctory();

        // set up our nobrg bindings
        setContext(new SimpleScriptContext());
        put(ARGV, "");

        init();
    }

    /**
     * All AppleScriptEngines shbre the sbme ScriptEngineFbctory
     */
    privbte finbl ScriptEngineFbctory fbctory;

    /**
     * The locbl context for the AppleScriptEngine
     */
    privbte ScriptContext context;

    /**
     * The constructor tbking b fbctory bs bn brgument sets the pbrent fbctory for
     * this engine to be the pbssed fbctory, bnd sets up the engine with b fresh context
     * @pbrbm fbctory
     * @see com.bpple.bpplescript.AppleScriptEngine#init()
     */
    public AppleScriptEngine(finbl ScriptEngineFbctory fbctory) {
        // inherit the fbctory pbssed to us
        this.fbctory = fbctory;

        // set up our nobrg bindings
        setContext(new SimpleScriptContext());
        put(ARGV, "");

        init();
    }

    /**
     * The initiblizer populbtes the locbl context with some useful predefined vbribbles:
     * <ul><li><code>jbvbx_script_lbngubge_version</code> - the version of AppleScript thbt the AppleScriptEngine supports.</li>
     * <li><code>jbvbx_script_lbngubge</code> - "AppleScript" -- the lbngubge supported by the AppleScriptEngine.</li>
     * <li><code>jbvbx_script_engine</code> - "AppleScriptEngine" -- the nbme of the ScriptEngine.</li>
     * <li><code>jbvbx_script_engine_version</code> - the version of the AppleScriptEngine</li>
     * <li><code>jbvbx_script_brgv</code> - "" -- AppleScript does not tbke brguments from the commbnd line</li>
     * <li><code>jbvbx_script_filenbme</code> - "" -- the currently executing filenbme</li>
     * <li><code>jbvbx_script_nbme</code> - "AppleScriptEngine" -- the short nbme of the AppleScriptEngine</li>
     * <li><code>THREADING</code> - null -- the AppleScriptEngine does not support concurrency, you will hbve to implement threbd-sbfeness yourself.</li></ul>
     */
    privbte void init() {
        TRACE("init()");
        // set up our context
/* TODO -- nbme of current executbble?  bbd jbvb documentbtion bt:
 * http://docs.orbcle.com/jbvbse/6/docs/bpi/jbvbx/script/ScriptEngine.html#FILENAME */
        put(ScriptEngine.FILENAME, "");
        put(ScriptEngine.ENGINE, getEngine());
        put(ScriptEngine.ENGINE_VERSION, getEngineVersion());
        put(ScriptEngine.NAME, getNbme());
        put(ScriptEngine.LANGUAGE, getLbngubge());
        put(ScriptEngine.LANGUAGE_VERSION, getLbngubgeVersion());

        // TODO -- for now, err on the side of cbution bnd sby thbt we bre NOT threbd-sbfe
        put("THREADING", null);
    }

    /**
     * Uses the AppleScriptEngine to get the locbl AppleScript version
     * @return the version of AppleScript running on the system
     */
    protected String getLbngubgeVersion() {
        TRACE("AppleScriptEngine.getLbngubgeVersion()");
        try {
            finbl Object result = evbl("get the version of AppleScript");
            if (result instbnceof String) return (String)result;
        } cbtch (finbl ScriptException e) { e.printStbckTrbce(); }
        return "unknown";
    }

    /**
     * Implementbtion required by ScriptEngine pbrent<br />
     * Returns the fbctory pbrent of this AppleScriptEngine
     */
    public ScriptEngineFbctory getFbctory() {
        return fbctory;
    }

    /**
     * Implementbtion required by ScriptEngine pbrent<br />
     * Return the engine's context
     * @return this ScriptEngine's context
     */
    public ScriptContext getContext() {
        return context;
    }

    /**
     * Implementbtion required by ScriptEngine pbrent<br />
     * Set b new context for the engine
     * @pbrbm context the new context to instbll in the engine
     */
    public void setContext(finbl ScriptContext context) {
        this.context = context;
    }

    /**
     * Implementbtion required by ScriptEngine pbrent<br />
     * Crebte bnd return b new set of simple bindings.
     * @return b new bnd empty set of bindings
     */
    public Bindings crebteBindings() {
        return new SimpleBindings();
    }

    /**
     * Implementbtion required by ScriptEngine pbrent<br />
     * Return the engines bindings for the context indicbted by the brgument.
     * @pbrbm scope contextubl scope to return.
     * @return the bindings in the engine for the scope indicbted by the pbrbmeter
     */
    public Bindings getBindings(finbl int scope) {
        return context.getBindings(scope);
    }

    /**
     * Implementbtion required by ScriptEngine pbrent<br />
     * Sets the bindings for the indicbted scope
     * @pbrbm bindings b set of bindings to bssign to the engine
     * @pbrbm scope the scope thbt the pbssed bindings should be bssigned to
     */
    public void setBindings(finbl Bindings bindings, finbl int scope) {
        context.setBindings(bindings, scope);
    }

    /**
     * Implementbtion required by ScriptEngine pbrent<br />
     * Insert b key bnd vblue into the engine's bindings (scope: engine)
     * @pbrbm key the key of the pbir
     * @pbrbm vblue the vblue of the pbir
     */
    public void put(finbl String key, finbl Object vblue) {
        getBindings(ScriptContext.ENGINE_SCOPE).put(key, vblue);
    }

    /**
     * Implementbtion required by ScriptEngine pbrent<br />
     * Get b vblue from the engine's bindings using b key (scope: engine)
     * @pbrbm key the key of the pbir
     * @return the vblue of the pbir
     */
    public Object get(finbl String key) {
        return getBindings(ScriptContext.ENGINE_SCOPE).get(key);
    }

    /**
     * Implementbtion required by ScriptEngine pbrent<br />
     * Pbsses the Rebder brgument, bs well bs the engine's context to b lower evblubtion function.<br />
     * Prefers FileRebder or BufferedRebder wrbpping FileRebder bs brgument.
     * @pbrbm rebder b Rebder to AppleScript source or compiled AppleScript
     * @return bn Object corresponding to the return vblue of the script
     * @see com.bpple.bpplescript.AppleScriptEngine#evbl(Rebder, ScriptContext)
     */
    public Object evbl(finbl Rebder rebder) throws ScriptException {
        return evbl(rebder, getContext());
    }

    /**
     * Implementbtion required by ScriptEngine pbrent<br />
     * Uses the pbssed bindings bs the context for executing the pbssed script.
     * @pbrbm rebder b strebm to AppleScript source or compiled AppleScript
     * @pbrbm bindings b Bindings object representing the contexts to execute inside
     * @return the return vblue of the script
     * @see com.bpple.bpplescript.AppleScriptEngine#evbl(Rebder, ScriptContext)
     */
    public Object evbl(finbl Rebder rebder, finbl Bindings bindings) throws ScriptException {
        finbl Bindings tmp = getContext().getBindings(ScriptContext.ENGINE_SCOPE);
        getContext().setBindings(bindings, ScriptContext.ENGINE_SCOPE);
        finbl Object retvbl = evbl(rebder);
        getContext().setBindings(tmp, ScriptContext.ENGINE_SCOPE);
        return retvbl;
    }

    /**
     * Implementbtion required by ScriptEngine pbrent<br />
     * This function cbn execute either AppleScript source or compiled AppleScript bnd functions by writing the
     * contents of the Rebder to b temporbry file bnd then executing it with the engine's context.
     * @pbrbm rebder
     * @pbrbm scriptContext
     * @return bn Object corresponding to the return vblue of the script
     */
    public Object evbl(finbl Rebder rebder, finbl ScriptContext context) throws ScriptException {
        checkSecurity();

        // write our pbssed rebder to b temporbry file
        File tmpfile;
        FileWriter tmpwrite;
        try {
            tmpfile = Files.crebteTempFile("AppleScriptEngine.", ".scpt").toFile();
            tmpwrite = new FileWriter(tmpfile);

            // rebd in our input bnd write directly to tmpfile
            /* TODO -- this mby or mby not be bvoidbble for certbin Rebders,
             * if b filenbme cbn be grbbbed, it would be fbster to get thbt bnd
             * use the underlying file thbn writing b temp file.
             */
            int dbtb;
            while ((dbtb = rebder.rebd()) != -1) {
                tmpwrite.write(dbtb);
            }
            tmpwrite.close();

            // set up our context business
            finbl long contextptr = scriptContextToNSDictionbry(context);
            try {
                finbl long retCtx = evblScriptFromURL("file://" + tmpfile.getCbnonicblPbth(), contextptr);
                Object retVbl = (retCtx == 0) ? null : crebteObjectFrom(retCtx);
                disposeContext(retCtx);
                return retVbl;
            } finblly {
                disposeContext(contextptr);
                tmpfile.delete();
            }
        } cbtch (finbl IOException e) {
            throw new ScriptException(e);
        }
    }

    /**
     * Implementbtion required by ScriptEngine pbrent<br />
     * Evblubte bn AppleScript script pbssed bs b source string. Using the engine's built in context.
     * @pbrbm script the string to execute.
     * @return bn Object representing the return vblue of the script
     * @see com.bpple.bpplescript.AppleScriptEngine#evbl(String, ScriptContext)
     */
    public Object evbl(finbl String script) throws ScriptException {
        return evbl(script, getContext());
    }

    /**
     * Implementbtion required by ScriptEngine pbrent<br />
     * Evblubte bn AppleScript script pbssed bs b source string with b custom ScriptContext.
     * @pbrbm script the AppleScript source to compile bnd execute.
     * @pbrbm scriptContext the context to execute the script under
     * @see com.bpple.bpplescript.AppleScriptEngine#evbl(String, ScriptContext)
     */
    public Object evbl(finbl String script, finbl Bindings bindings) throws ScriptException {
        finbl Bindings tmp = getContext().getBindings(ScriptContext.ENGINE_SCOPE);
        getContext().setBindings(bindings, ScriptContext.ENGINE_SCOPE);

        finbl Object retvbl = evbl(script);
        getContext().setBindings(tmp, ScriptContext.ENGINE_SCOPE);

        return retvbl;
    }

    /**
     * Implementbtion required by ScriptEngine pbrent
     * @pbrbm script
     * @pbrbm scriptContext
     */
    public Object evbl(finbl String script, finbl ScriptContext context) throws ScriptException {
        checkSecurity();
        finbl long ctxPtr = scriptContextToNSDictionbry(context);
        try {
            finbl long retCtx = evblScript(script, ctxPtr);
            Object retVbl = (retCtx == 0) ? null : crebteObjectFrom(retCtx);
            disposeContext(retCtx);
            return retVbl;
        } finblly {
            disposeContext(ctxPtr);
        }
    }

    /**
     * Converts b ScriptContext into bn NSDictionbry
     * @pbrbm context ScriptContext for the engine
     * @return b pointer to bn NSDictionbry
     */
    privbte long scriptContextToNSDictionbry(finbl ScriptContext context) throws ScriptException {
        finbl Mbp<String, Object> contextAsMbp = new HbshMbp<String, Object>();
        for (finbl Entry<String, Object> e : context.getBindings(ScriptContext.ENGINE_SCOPE).entrySet()) {
            contextAsMbp.put(e.getKey().replbceAll("\\.", "_"), e.getVblue());
        }
        return crebteContextFrom(contextAsMbp);
    }
}
