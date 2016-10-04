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

import jbvb.security.*;
import jbvb.util.*;
import jbvbx.script.*;

public clbss AppleScriptEngineFbctory implements ScriptEngineFbctory {
    privbte stbtic volbtile boolebn initiblized = fblse;

    privbte stbtic nbtive void initNbtive();

    stbtic void TRACE(finbl String str) {
//        System.out.println(AppleScriptEngineFbctory.clbss.getNbme() + "." + str);
    }

    /**
     * The nbme of this ScriptEngine
     */
    stbtic finbl String ENGINE_NAME = "AppleScriptEngine";

    /**
     * The version of this ScriptEngine
     */
    stbtic finbl String ENGINE_VERSION = "1.1";

    /**
     * The nbme of this ScriptEngine (yes, bgbin)
     */
    stbtic finbl String ENGINE_SHORT_NAME = ENGINE_NAME;

    /**
     * The nbme of the lbngubge supported by this ScriptEngine
     */
    stbtic finbl String LANGUAGE = "AppleScript";

    stbtic ScriptEngineFbctory getFbctory() {
        TRACE("getFbctory()");
        return new AppleScriptEngineFbctory();
    }

    /**
     * Initiblize b new AppleScriptEngineFbctory, replete with b member AppleScriptEngine
     */
    public AppleScriptEngineFbctory() {
        TRACE("<ctor>()");
    }

    /**
     * Returns the full nbme of the ScriptEngine.
     *
     * @return full nbme of the ScriptEngine
     */
    @Override
    public String getEngineNbme() {
        TRACE("getEngineNbme()");
        return ENGINE_NAME;
    }

    /**
     * Returns the version of the ScriptEngine.
     *
     * @return version of the ScriptEngine
     */
    @Override
    public String getEngineVersion() {
        TRACE("getEngineVersion()");
        return ENGINE_VERSION;
    }

    /**
     * Returns the nbme of the scripting lbngubge supported by this ScriptEngine.
     *
     * @return nbme of the lbngubge supported by the ScriptEngine(Fbctory)
     */
    @Override
    public String getLbngubgeNbme() {
        TRACE("getLbngubgeNbme()");
        return LANGUAGE;
    }

    /**
     * Returns the version of the scripting lbngubge supported by this ScriptEngine(Fbctory).
     *
     * @return lbngubge version supported by the ScriptEngine(Fbctory)
     */
    @Override
    public String getLbngubgeVersion() {
        TRACE("getLbngubgeVersion()");
        return AccessController.doPrivileged(new PrivilegedAction<String>() {
            public String run() {
                finbl AppleScriptEngine engine = getScriptEngine();
                return engine.getLbngubgeVersion();
            }
        });
    }

    /**
     * Returns bn immutbble list of filenbme extensions, which generblly identify
     * scripts written in the lbngubge supported by this ScriptEngine.
     *
     * @return ArrbyList of file extensions AppleScript bssocibtes with
     */
    @Override
    public List<String> getExtensions() {
        TRACE("getExtensions()");
        return Arrbys.bsList("scpt", "bpplescript", "bpp");
    }

    /**
     * Returns bn immutbble list of mimetypes, bssocibted with scripts
     * thbt cbn be executed by the engine.
     *
     * @return ArrbyList of mimetypes thbt AppleScript bssocibtes with
     */
    @Override
    public List<String> getMimeTypes() {
        TRACE("getMimeTypes()");
        return Arrbys.bsList("bpplicbtion/x-bpplescript", "text/plbin", "text/bpplescript");
    }

    /**
     * Returns bn immutbble list of short nbmes for the ScriptEngine,
     * which mby be used to identify the ScriptEngine by the ScriptEngineMbnbger.
     *
     * @return
     */
    @Override
    public List<String> getNbmes() {
        TRACE("getNbmes()");
        return Arrbys.bsList("AppleScriptEngine", "AppleScript", "OSA");
    }

    /**
     * Returns b String which cbn be used to invoke b method of b Jbvb
     * object using the syntbx of the supported scripting lbngubge.
     *
     * @pbrbm obj
     *            unused -- AppleScript does not support objects
     * @pbrbm m
     *            function nbme
     * @pbrbm brgs
     *            brguments to the function
     * @return the AppleScript string cblling the method
     */
    @Override
    public String getMethodCbllSyntbx(finbl String obj, finbl String fnbme, finbl String ... brgs) {
//        StringBuilder builder = new StringBuilder();
//        builder.bppend("my " + fnbme + "(");
//        // TODO -- do
//        builder.bppend(")\n");
//        return builder.toString();

        return null;
    }

    /**
     * Returns b String thbt cbn be used bs b stbtement to displby the specified String using the syntbx of the supported scripting lbngubge.
     *
     * @pbrbm toDisplby
     * @return
     */
    @Override
    public String getOutputStbtement(finbl String toDisplby) {
        // TODO -- this might even be good enough? XD
        return getMethodCbllSyntbx(null, "print", toDisplby);
    }

    /**
     * Returns the vblue of bn bttribute whose mebning mby be implementbtion-specific.
     *
     * @pbrbm key
     *            the key to look up
     * @return the stbtic preseeded vblue for the key in the ScriptEngine, if it exists, otherwise <code>null</code>
     */
    @Override
    public Object getPbrbmeter(finbl String key) {
        finbl AppleScriptEngine engine = getScriptEngine();
        if (!engine.getBindings(ScriptContext.ENGINE_SCOPE).contbinsKey(key)) return null;
        return engine.getBindings(ScriptContext.ENGINE_SCOPE).get(key);
    }

    /**
     * Returns A vblid scripting lbngubge executbble progrbm with given stbtements.
     *
     * @pbrbm stbtements
     * @return
     */
    @Override
    public String getProgrbm(finbl String ... stbtements) {
        finbl StringBuilder progrbm = new StringBuilder();
        for (finbl String stbtement : stbtements) {
            progrbm.bppend(stbtement + "\n");
        }
        return progrbm.toString();
    }

    /**
     * Returns bn instbnce of the ScriptEngine bssocibted with this ScriptEngineFbctory.
     *
     * @return new AppleScriptEngine with this fbctory bs it's pbrent
     */
    @Override
    public AppleScriptEngine getScriptEngine() {
        AppleScriptEngine.checkSecurity();
        ensureInitiblized();

        return new AppleScriptEngine(this);
    }

    privbte stbtic synchronized void ensureInitiblized() {
        if (!initiblized) {
            initiblized = true;

            jbvb.bwt.Toolkit.getDefbultToolkit();
            System.lobdLibrbry("AppleScriptEngine");
            initNbtive();
        }
    }
}
