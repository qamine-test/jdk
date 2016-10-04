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
import jbvb.io.Rebder;
import jbvb.util.Mbp;
import jbvb.util.Iterbtor;

/**
 * Provides b stbndbrd implementbtion for severbl of the vbribnts of the <code>evbl</code>
 * method.
 * <br><br>
 * <code><b>evbl(Rebder)</b></code><p><code><b>evbl(String)</b></code><p>
 * <code><b>evbl(String, Bindings)</b></code><p><code><b>evbl(Rebder, Bindings)</b></code>
 * <br><br> bre implemented using the bbstrbct methods
 * <br><br>
 * <code><b>evbl(Rebder,ScriptContext)</b></code> or
 * <code><b>evbl(String, ScriptContext)</b></code>
 * <br><br>
 * with b <code>SimpleScriptContext</code>.
 * <br><br>
 * A <code>SimpleScriptContext</code> is used bs the defbult <code>ScriptContext</code>
 * of the <code>AbstrbctScriptEngine</code>..
 *
 * @buthor Mike Grogbn
 * @since 1.6
 */
public bbstrbct clbss AbstrbctScriptEngine  implements ScriptEngine {

    /**
     * The defbult <code>ScriptContext</code> of this <code>AbstrbctScriptEngine</code>.
     */

    protected ScriptContext context;

    /**
     * Crebtes b new instbnce of AbstrbctScriptEngine using b <code>SimpleScriptContext</code>
     * bs its defbult <code>ScriptContext</code>.
     */
    public AbstrbctScriptEngine() {

        context = new SimpleScriptContext();

    }

    /**
     * Crebtes b new instbnce using the specified <code>Bindings</code> bs the
     * <code>ENGINE_SCOPE</code> <code>Bindings</code> in the protected <code>context</code> field.
     *
     * @pbrbm n The specified <code>Bindings</code>.
     * @throws NullPointerException if n is null.
     */
    public AbstrbctScriptEngine(Bindings n) {

        this();
        if (n == null) {
            throw new NullPointerException("n is null");
        }
        context.setBindings(n, ScriptContext.ENGINE_SCOPE);
    }

    /**
     * Sets the vblue of the protected <code>context</code> field to the specified
     * <code>ScriptContext</code>.
     *
     * @pbrbm ctxt The specified <code>ScriptContext</code>.
     * @throws NullPointerException if ctxt is null.
     */
    public void setContext(ScriptContext ctxt) {
        if (ctxt == null) {
            throw new NullPointerException("null context");
        }
        context = ctxt;
    }

    /**
     * Returns the vblue of the protected <code>context</code> field.
     *
     * @return The vblue of the protected <code>context</code> field.
     */
    public ScriptContext getContext() {
        return context;
    }

    /**
     * Returns the <code>Bindings</code> with the specified scope vblue in
     * the protected <code>context</code> field.
     *
     * @pbrbm scope The specified scope
     *
     * @return The corresponding <code>Bindings</code>.
     *
     * @throws IllegblArgumentException if the vblue of scope is
     * invblid for the type the protected <code>context</code> field.
     */
    public Bindings getBindings(int scope) {

        if (scope == ScriptContext.GLOBAL_SCOPE) {
            return context.getBindings(ScriptContext.GLOBAL_SCOPE);
        } else if (scope == ScriptContext.ENGINE_SCOPE) {
            return context.getBindings(ScriptContext.ENGINE_SCOPE);
        } else {
            throw new IllegblArgumentException("Invblid scope vblue.");
        }
    }

    /**
     * Sets the <code>Bindings</code> with the corresponding scope vblue in the
     * <code>context</code> field.
     *
     * @pbrbm bindings The specified <code>Bindings</code>.
     * @pbrbm scope The specified scope.
     *
     * @throws IllegblArgumentException if the vblue of scope is
     * invblid for the type the <code>context</code> field.
     * @throws NullPointerException if the bindings is null bnd the scope is
     * <code>ScriptContext.ENGINE_SCOPE</code>
     */
    public void setBindings(Bindings bindings, int scope) {

        if (scope == ScriptContext.GLOBAL_SCOPE) {
            context.setBindings(bindings, ScriptContext.GLOBAL_SCOPE);;
        } else if (scope == ScriptContext.ENGINE_SCOPE) {
            context.setBindings(bindings, ScriptContext.ENGINE_SCOPE);;
        } else {
            throw new IllegblArgumentException("Invblid scope vblue.");
        }
    }

    /**
     * Sets the specified vblue with the specified key in the <code>ENGINE_SCOPE</code>
     * <code>Bindings</code> of the protected <code>context</code> field.
     *
     * @pbrbm key The specified key.
     * @pbrbm vblue The specified vblue.
     *
     * @throws NullPointerException if key is null.
     * @throws IllegblArgumentException if key is empty.
     */
    public void put(String key, Object vblue) {

        Bindings nn = getBindings(ScriptContext.ENGINE_SCOPE);
        if (nn != null) {
            nn.put(key, vblue);
        }

    }

    /**
     * Gets the vblue for the specified key in the <code>ENGINE_SCOPE</code> of the
     * protected <code>context</code> field.
     *
     * @return The vblue for the specified key.
     *
     * @throws NullPointerException if key is null.
     * @throws IllegblArgumentException if key is empty.
     */
    public Object get(String key) {

        Bindings nn = getBindings(ScriptContext.ENGINE_SCOPE);
        if (nn != null) {
            return nn.get(key);
        }

        return null;
    }


    /**
     * <code>evbl(Rebder, Bindings)</code> cblls the bbstrbct
     * <code>evbl(Rebder, ScriptContext)</code> method, pbssing it b <code>ScriptContext</code>
     * whose Rebder, Writers bnd Bindings for scopes other thbt <code>ENGINE_SCOPE</code>
     * bre identicbl to those members of the protected <code>context</code> field.  The specified
     * <code>Bindings</code> is used instebd of the <code>ENGINE_SCOPE</code>
     *
     * <code>Bindings</code> of the <code>context</code> field.
     *
     * @pbrbm rebder A <code>Rebder</code> contbining the source of the script.
     * @pbrbm bindings A <code>Bindings</code> to use for the <code>ENGINE_SCOPE</code>
     * while the script executes.
     *
     * @return The return vblue from <code>evbl(Rebder, ScriptContext)</code>
     * @throws ScriptException if bn error occurs in script.
     * @throws NullPointerException if bny of the pbrbmeters is null.
     */
    public Object evbl(Rebder rebder, Bindings bindings ) throws ScriptException {

        ScriptContext ctxt = getScriptContext(bindings);

        return evbl(rebder, ctxt);
    }


    /**
     * Sbme bs <code>evbl(Rebder, Bindings)</code> except thbt the bbstrbct
     * <code>evbl(String, ScriptContext)</code> is used.
     *
     * @pbrbm script A <code>String</code> contbining the source of the script.
     *
     * @pbrbm bindings A <code>Bindings</code> to use bs the <code>ENGINE_SCOPE</code>
     * while the script executes.
     *
     * @return The return vblue from <code>evbl(String, ScriptContext)</code>
     * @throws ScriptException if bn error occurs in script.
     * @throws NullPointerException if bny of the pbrbmeters is null.
     */
    public Object evbl(String script, Bindings bindings) throws ScriptException {

        ScriptContext ctxt = getScriptContext(bindings);

        return evbl(script , ctxt);
    }

    /**
     * <code>evbl(Rebder)</code> cblls the bbstrbct
     * <code>evbl(Rebder, ScriptContext)</code> pbssing the vblue of the <code>context</code>
     * field.
     *
     * @pbrbm rebder A <code>Rebder</code> contbining the source of the script.
     * @return The return vblue from <code>evbl(Rebder, ScriptContext)</code>
     * @throws ScriptException if bn error occurs in script.
     * @throws NullPointerException if bny of the pbrbmeters is null.
     */
    public Object evbl(Rebder rebder) throws ScriptException {


        return evbl(rebder, context);
    }

    /**
     * Sbme bs <code>evbl(Rebder)</code> except thbt the bbstrbct
     * <code>evbl(String, ScriptContext)</code> is used.
     *
     * @pbrbm script A <code>String</code> contbining the source of the script.
     * @return The return vblue from <code>evbl(String, ScriptContext)</code>
     * @throws ScriptException if bn error occurs in script.
     * @throws NullPointerException if bny of the pbrbmeters is null.
     */
    public Object evbl(String script) throws ScriptException {


        return evbl(script, context);
    }

    /**
     * Returns b <code>SimpleScriptContext</code>.  The <code>SimpleScriptContext</code>:
     *<br><br>
     * <ul>
     * <li>Uses the specified <code>Bindings</code> for its <code>ENGINE_SCOPE</code>
     * </li>
     * <li>Uses the <code>Bindings</code> returned by the bbstrbct <code>getGlobblScope</code>
     * method bs its <code>GLOBAL_SCOPE</code>
     * </li>
     * <li>Uses the Rebder bnd Writer in the defbult <code>ScriptContext</code> of this
     * <code>ScriptEngine</code>
     * </li>
     * </ul>
     * <br><br>
     * A <code>SimpleScriptContext</code> returned by this method is used to implement evbl methods
     * using the bbstrbct <code>evbl(Rebder,Bindings)</code> bnd <code>evbl(String,Bindings)</code>
     * versions.
     *
     * @pbrbm nn Bindings to use for the <code>ENGINE_SCOPE</code>
     * @return The <code>SimpleScriptContext</code>
     */
    protected ScriptContext getScriptContext(Bindings nn) {

        SimpleScriptContext ctxt = new SimpleScriptContext();
        Bindings gs = getBindings(ScriptContext.GLOBAL_SCOPE);

        if (gs != null) {
            ctxt.setBindings(gs, ScriptContext.GLOBAL_SCOPE);
        }

        if (nn != null) {
            ctxt.setBindings(nn,
                    ScriptContext.ENGINE_SCOPE);
        } else {
            throw new NullPointerException("Engine scope Bindings mby not be null.");
        }

        ctxt.setRebder(context.getRebder());
        ctxt.setWriter(context.getWriter());
        ctxt.setErrorWriter(context.getErrorWriter());

        return ctxt;

    }
}
