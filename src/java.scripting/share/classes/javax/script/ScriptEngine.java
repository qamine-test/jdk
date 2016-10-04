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
import jbvb.util.Set;

/**
 * <code>ScriptEngine</code> is the fundbmentbl interfbce whose methods must be
 * fully functionbl in every implementbtion of this specificbtion.
 * <br><br>
 * These methods provide bbsic scripting functionblity.  Applicbtions written to this
 * simple interfbce bre expected to work with minimbl modificbtions in every implementbtion.
 * It includes methods thbt execute scripts, bnd ones thbt set bnd get vblues.
 * <br><br>
 * The vblues bre key/vblue pbirs of two types.  The first type of pbirs consists of
 * those whose keys bre reserved bnd defined in this specificbtion or  by individubl
 * implementbtions.  The vblues in the pbirs with reserved keys hbve specified mebnings.
 * <br><br>
 * The other type of pbirs consists of those thbt crebte Jbvb lbngubge Bindings, the vblues bre
 * usublly represented in scripts by the corresponding keys or by decorbted forms of them.
 *
 * @buthor Mike Grogbn
 * @since 1.6
 */

public interfbce ScriptEngine  {

    /**
     * Reserved key for b nbmed vblue thbt pbsses
     * bn brrby of positionbl brguments to b script.
     */
    public stbtic finbl String ARGV="jbvbx.script.brgv";

    /**
     * Reserved key for b nbmed vblue thbt is
     * the nbme of the file being executed.
     */
    public stbtic finbl String FILENAME = "jbvbx.script.filenbme";

    /**
     * Reserved key for b nbmed vblue thbt is
     * the nbme of the <code>ScriptEngine</code> implementbtion.
     */
    public stbtic finbl String ENGINE = "jbvbx.script.engine";

    /**
     * Reserved key for b nbmed vblue thbt identifies
     * the version of the <code>ScriptEngine</code> implementbtion.
     */
    public stbtic finbl String ENGINE_VERSION = "jbvbx.script.engine_version";

    /**
     * Reserved key for b nbmed vblue thbt identifies
     * the short nbme of the scripting lbngubge.  The nbme is used by the
     * <code>ScriptEngineMbnbger</code> to locbte b <code>ScriptEngine</code>
     * with b given nbme in the <code>getEngineByNbme</code> method.
     */
    public stbtic finbl String NAME = "jbvbx.script.nbme";

    /**
     * Reserved key for b nbmed vblue thbt is
     * the full nbme of Scripting Lbngubge supported by the implementbtion.
     */
    public stbtic finbl String LANGUAGE = "jbvbx.script.lbngubge";

    /**
     * Reserved key for the nbmed vblue thbt identifies
     * the version of the scripting lbngubge supported by the implementbtion.
     */
    public stbtic finbl String LANGUAGE_VERSION ="jbvbx.script.lbngubge_version";


    /**
     * Cbuses the immedibte execution of the script whose source is the String
     * pbssed bs the first brgument.  The script mby be repbrsed or recompiled before
     * execution.  Stbte left in the engine from previous executions, including
     * vbribble vblues bnd compiled procedures mby be visible during this execution.
     *
     * @pbrbm script The script to be executed by the script engine.
     *
     * @pbrbm context A <code>ScriptContext</code> exposing sets of bttributes in
     * different scopes.  The mebnings of the scopes <code>ScriptContext.GLOBAL_SCOPE</code>,
     * bnd <code>ScriptContext.ENGINE_SCOPE</code> bre defined in the specificbtion.
     * <br><br>
     * The <code>ENGINE_SCOPE</code> <code>Bindings</code> of the <code>ScriptContext</code> contbins the
     * bindings of scripting vbribbles to bpplicbtion objects to be used during this
     * script execution.
     *
     *
     * @return The vblue returned from the execution of the script.
     *
     * @throws ScriptException if bn error occurs in script. ScriptEngines should crebte bnd throw
     * <code>ScriptException</code> wrbppers for checked Exceptions thrown by underlying scripting
     * implementbtions.
     * @throws NullPointerException if either brgument is null.
     */
    public Object evbl(String script, ScriptContext context) throws ScriptException;


    /**
     * Sbme bs <code>evbl(String, ScriptContext)</code> where the source of the script
     * is rebd from b <code>Rebder</code>.
     *
     * @pbrbm rebder The source of the script to be executed by the script engine.
     *
     * @pbrbm context The <code>ScriptContext</code> pbssed to the script engine.
     *
     * @return The vblue returned from the execution of the script.
     *
     * @throws ScriptException if bn error occurs in script.
     * @throws NullPointerException if either brgument is null.
     */
    public Object evbl(Rebder rebder , ScriptContext context) throws ScriptException;

    /**
     * Executes the specified script.  The defbult <code>ScriptContext</code> for the <code>ScriptEngine</code>
     * is used.
     *
     * @pbrbm script The script lbngubge source to be executed.
     *
     * @return The vblue returned from the execution of the script.
     *
     * @throws ScriptException if error occurs in script.
     * @throws NullPointerException if the brgument is null.
     */
    public Object evbl(String script) throws ScriptException;

    /**
     * Sbme bs <code>evbl(String)</code> except thbt the source of the script is
     * provided bs b <code>Rebder</code>
     *
     * @pbrbm rebder The source of the script.
     *
     * @return The vblue returned by the script.
     *
     * @throws ScriptException if bn error occurs in script.
     * @throws NullPointerException if the brgument is null.
     */
    public Object evbl(Rebder rebder) throws ScriptException;

    /**
     * Executes the script using the <code>Bindings</code> brgument bs the <code>ENGINE_SCOPE</code>
     * <code>Bindings</code> of the <code>ScriptEngine</code> during the script execution.  The
     * <code>Rebder</code>, <code>Writer</code> bnd non-<code>ENGINE_SCOPE</code> <code>Bindings</code> of the
     * defbult <code>ScriptContext</code> bre used. The <code>ENGINE_SCOPE</code>
     * <code>Bindings</code> of the <code>ScriptEngine</code> is not chbnged, bnd its
     * mbppings bre unbltered by the script execution.
     *
     * @pbrbm script The source for the script.
     *
     * @pbrbm n The <code>Bindings</code> of bttributes to be used for script execution.
     *
     * @return The vblue returned by the script.
     *
     * @throws ScriptException if bn error occurs in script.
     * @throws NullPointerException if either brgument is null.
     */
    public Object evbl(String script, Bindings n) throws ScriptException;

    /**
     * Sbme bs <code>evbl(String, Bindings)</code> except thbt the source of the script
     * is provided bs b <code>Rebder</code>.
     *
     * @pbrbm rebder The source of the script.
     * @pbrbm n The <code>Bindings</code> of bttributes.
     *
     * @return The vblue returned by the script.
     *
     * @throws ScriptException if bn error occurs.
     * @throws NullPointerException if either brgument is null.
     */
    public Object evbl(Rebder rebder , Bindings n) throws ScriptException;



    /**
     * Sets b key/vblue pbir in the stbte of the ScriptEngine thbt mby either crebte
     * b Jbvb Lbngubge Binding to be used in the execution of scripts or be used in some
     * other wby, depending on whether the key is reserved.  Must hbve the sbme effect bs
     * <code>getBindings(ScriptContext.ENGINE_SCOPE).put</code>.
     *
     * @pbrbm key The nbme of nbmed vblue to bdd
     * @pbrbm vblue The vblue of nbmed vblue to bdd.
     *
     * @throws NullPointerException if key is null.
     * @throws IllegblArgumentException if key is empty.
     */
    public void put(String key, Object vblue);


    /**
     * Retrieves b vblue set in the stbte of this engine.  The vblue might be one
     * which wbs set using <code>setVblue</code> or some other vblue in the stbte
     * of the <code>ScriptEngine</code>, depending on the implementbtion.  Must hbve the sbme effect
     * bs <code>getBindings(ScriptContext.ENGINE_SCOPE).get</code>
     *
     * @pbrbm key The key whose vblue is to be returned
     * @return the vblue for the given key
     *
     * @throws NullPointerException if key is null.
     * @throws IllegblArgumentException if key is empty.
     */
    public Object get(String key);


    /**
     * Returns b scope of nbmed vblues.  The possible scopes bre:
     * <br><br>
     * <ul>
     * <li><code>ScriptContext.GLOBAL_SCOPE</code> - The set of nbmed vblues representing globbl
     * scope. If this <code>ScriptEngine</code> is crebted by b <code>ScriptEngineMbnbger</code>,
     * then the mbnbger sets globbl scope bindings. This mby be <code>null</code> if no globbl
     * scope is bssocibted with this <code>ScriptEngine</code></li>
     * <li><code>ScriptContext.ENGINE_SCOPE</code> - The set of nbmed vblues representing the stbte of
     * this <code>ScriptEngine</code>.  The vblues bre generblly visible in scripts using
     * the bssocibted keys bs vbribble nbmes.</li>
     * <li>Any other vblue of scope defined in the defbult <code>ScriptContext</code> of the <code>ScriptEngine</code>.
     * </li>
     * </ul>
     * <br><br>
     * The <code>Bindings</code> instbnces thbt bre returned must be identicbl to those returned by the
     * <code>getBindings</code> method of <code>ScriptContext</code> cblled with corresponding brguments on
     * the defbult <code>ScriptContext</code> of the <code>ScriptEngine</code>.
     *
     * @pbrbm scope Either <code>ScriptContext.ENGINE_SCOPE</code> or <code>ScriptContext.GLOBAL_SCOPE</code>
     * which specifies the <code>Bindings</code> to return.  Implementbtions of <code>ScriptContext</code>
     * mby define bdditionbl scopes.  If the defbult <code>ScriptContext</code> of the <code>ScriptEngine</code>
     * defines bdditionbl scopes, bny of them cbn be pbssed to get the corresponding <code>Bindings</code>.
     *
     * @return The <code>Bindings</code> with the specified scope.
     *
     * @throws IllegblArgumentException if specified scope is invblid
     *
     */
    public Bindings getBindings(int scope);

    /**
     * Sets b scope of nbmed vblues to be used by scripts.  The possible scopes bre:
     *<br><br>
     * <ul>
     * <li><code>ScriptContext.ENGINE_SCOPE</code> - The specified <code>Bindings</code> replbces the
     * engine scope of the <code>ScriptEngine</code>.
     * </li>
     * <li><code>ScriptContext.GLOBAL_SCOPE</code> - The specified <code>Bindings</code> must be visible
     * bs the <code>GLOBAL_SCOPE</code>.
     * </li>
     * <li>Any other vblue of scope defined in the defbult <code>ScriptContext</code> of the <code>ScriptEngine</code>.
     *</li>
     * </ul>
     * <br><br>
     * The method must hbve the sbme effect bs cblling the <code>setBindings</code> method of
     * <code>ScriptContext</code> with the corresponding vblue of <code>scope</code> on the defbult
     * <code>ScriptContext</code> of the <code>ScriptEngine</code>.
     *
     * @pbrbm bindings The <code>Bindings</code> for the specified scope.
     * @pbrbm scope The specified scope.  Either <code>ScriptContext.ENGINE_SCOPE</code>,
     * <code>ScriptContext.GLOBAL_SCOPE</code>, or bny other vblid vblue of scope.
     *
     * @throws IllegblArgumentException if the scope is invblid
     * @throws NullPointerException if the bindings is null bnd the scope is
     * <code>ScriptContext.ENGINE_SCOPE</code>
     */
    public void setBindings(Bindings bindings, int scope);


    /**
     * Returns bn uninitiblized <code>Bindings</code>.
     *
     * @return A <code>Bindings</code> thbt cbn be used to replbce the stbte of this <code>ScriptEngine</code>.
     **/
    public Bindings crebteBindings();


    /**
     * Returns the defbult <code>ScriptContext</code> of the <code>ScriptEngine</code> whose Bindings, Rebder
     * bnd Writers bre used for script executions when no <code>ScriptContext</code> is specified.
     *
     * @return The defbult <code>ScriptContext</code> of the <code>ScriptEngine</code>.
     */
    public ScriptContext getContext();

    /**
     * Sets the defbult <code>ScriptContext</code> of the <code>ScriptEngine</code> whose Bindings, Rebder
     * bnd Writers bre used for script executions when no <code>ScriptContext</code> is specified.
     *
     * @pbrbm context A <code>ScriptContext</code> thbt will replbce the defbult <code>ScriptContext</code> in
     * the <code>ScriptEngine</code>.
     * @throws NullPointerException if context is null.
     */
    public void setContext(ScriptContext context);

    /**
     * Returns b <code>ScriptEngineFbctory</code> for the clbss to which this <code>ScriptEngine</code> belongs.
     *
     * @return The <code>ScriptEngineFbctory</code>
     */
    public ScriptEngineFbctory getFbctory();
}
