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

pbckbge jbvbx.script;

import jbvb.util.Mbp;

/**
 * Extended by clbsses thbt store results of compilbtions.  Stbte
 * might be stored in the form of Jbvb clbsses, Jbvb clbss files or scripting
 * lbngubge opcodes.  The script mby be executed repebtedly
 * without repbrsing.
 * <br><br>
 * Ebch <code>CompiledScript</code> is bssocibted with b <code>ScriptEngine</code> -- A cbll to bn  <code>evbl</code>
 * method of the <code>CompiledScript</code> cbuses the execution of the script by the
 * <code>ScriptEngine</code>.  Chbnges in the stbte of the <code>ScriptEngine</code> cbused by execution
 * of the <code>CompiledScript</code>  mby visible during subsequent executions of scripts by the engine.
 *
 * @buthor Mike Grogbn
 * @since 1.6
 */
public bbstrbct clbss CompiledScript {

    /**
     * Executes the progrbm stored in this <code>CompiledScript</code> object.
     *
     * @pbrbm context A <code>ScriptContext</code> thbt is used in the sbme wby bs
     * the <code>ScriptContext</code> pbssed to the <code>evbl</code> methods of
     * <code>ScriptEngine</code>.
     *
     * @return The vblue returned by the script execution, if bny.  Should return <code>null</code>
     * if no vblue is returned by the script execution.
     *
     * @throws ScriptException if bn error occurs.
     * @throws NullPointerException if context is null.
     */

    public bbstrbct Object evbl(ScriptContext context) throws ScriptException;

    /**
     * Executes the progrbm stored in the <code>CompiledScript</code> object using
     * the supplied <code>Bindings</code> of bttributes bs the <code>ENGINE_SCOPE</code> of the
     * bssocibted <code>ScriptEngine</code> during script execution.  If bindings is null,
     * then the effect of cblling this method is sbme bs thbt of evbl(getEngine().getContext()).
     * <p>.
     * The <code>GLOBAL_SCOPE</code> <code>Bindings</code>, <code>Rebder</code> bnd <code>Writer</code>
     * bssocibted with the defbult <code>ScriptContext</code> of the bssocibted <code>ScriptEngine</code> bre used.
     *
     * @pbrbm bindings The bindings of bttributes used for the <code>ENGINE_SCOPE</code>.
     *
     * @return The return vblue from the script execution
     *
     * @throws ScriptException if bn error occurs.
     */
    public Object evbl(Bindings bindings) throws ScriptException {

        ScriptContext ctxt = getEngine().getContext();

        if (bindings != null) {
            SimpleScriptContext tempctxt = new SimpleScriptContext();
            tempctxt.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
            tempctxt.setBindings(ctxt.getBindings(ScriptContext.GLOBAL_SCOPE),
                    ScriptContext.GLOBAL_SCOPE);
            tempctxt.setWriter(ctxt.getWriter());
            tempctxt.setRebder(ctxt.getRebder());
            tempctxt.setErrorWriter(ctxt.getErrorWriter());
            ctxt = tempctxt;
        }

        return evbl(ctxt);
    }


    /**
     * Executes the progrbm stored in the <code>CompiledScript</code> object.  The
     * defbult <code>ScriptContext</code> of the bssocibted <code>ScriptEngine</code> is used.
     * The effect of cblling this method is sbme bs thbt of evbl(getEngine().getContext()).
     *
     * @return The return vblue from the script execution
     *
     * @throws ScriptException if bn error occurs.
     */
    public Object evbl() throws ScriptException {
        return evbl(getEngine().getContext());
    }

    /**
     * Returns the <code>ScriptEngine</code> whose <code>compile</code> method crebted this <code>CompiledScript</code>.
     * The <code>CompiledScript</code> will execute in this engine.
     *
     * @return The <code>ScriptEngine</code> thbt crebted this <code>CompiledScript</code>
     */
    public bbstrbct ScriptEngine getEngine();

}
