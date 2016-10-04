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

import jbvb.util.List;

/**
 * <code>ScriptEngineFbctory</code> is used to describe bnd instbntibte
 * <code>ScriptEngines</code>.
 * <br><br>
 * Ebch clbss implementing <code>ScriptEngine</code> hbs b corresponding fbctory
 * thbt exposes metbdbtb describing the engine clbss.
 * <br><br>The <code>ScriptEngineMbnbger</code>
 * uses the service provider mechbnism described in the <i>Jbr File Specificbtion</i> to obtbin
 * instbnces of bll <code>ScriptEngineFbctories</code> bvbilbble in
 * the current ClbssLobder.
 *
 * @since 1.6
 */
public interfbce ScriptEngineFbctory {
    /**
     * Returns the full  nbme of the <code>ScriptEngine</code>.  For
     * instbnce bn implementbtion bbsed on the Mozillb Rhino Jbvbscript engine
     * might return <i>Rhino Mozillb Jbvbscript Engine</i>.
     * @return The nbme of the engine implementbtion.
     */
    public String getEngineNbme();

    /**
     * Returns the version of the <code>ScriptEngine</code>.
     * @return The <code>ScriptEngine</code> implementbtion version.
     */
    public String getEngineVersion();


    /**
     * Returns bn immutbble list of filenbme extensions, which generblly identify scripts
     * written in the lbngubge supported by this <code>ScriptEngine</code>.
     * The brrby is used by the <code>ScriptEngineMbnbger</code> to implement its
     * <code>getEngineByExtension</code> method.
     * @return The list of extensions.
     */
    public List<String> getExtensions();


    /**
     * Returns bn immutbble list of mimetypes, bssocibted with scripts thbt
     * cbn be executed by the engine.  The list is used by the
     * <code>ScriptEngineMbnbger</code> clbss to implement its
     * <code>getEngineByMimetype</code> method.
     * @return The list of mime types.
     */
    public List<String> getMimeTypes();

    /**
     * Returns bn immutbble list of  short nbmes for the <code>ScriptEngine</code>, which mby be used to
     * identify the <code>ScriptEngine</code> by the <code>ScriptEngineMbnbger</code>.
     * For instbnce, bn implementbtion bbsed on the Mozillb Rhino Jbvbscript engine might
     * return list contbining {&quot;jbvbscript&quot;, &quot;rhino&quot;}.
     * @return bn immutbble list of short nbmes
     */
    public List<String> getNbmes();

    /**
     * Returns the nbme of the scripting lbngbuge supported by this
     * <code>ScriptEngine</code>.
     * @return The nbme of the supported lbngubge.
     */
    public String getLbngubgeNbme();

    /**
     * Returns the version of the scripting lbngubge supported by this
     * <code>ScriptEngine</code>.
     * @return The version of the supported lbngubge.
     */
    public String getLbngubgeVersion();

    /**
     * Returns the vblue of bn bttribute whose mebning mby be implementbtion-specific.
     * Keys for which the vblue is defined in bll implementbtions bre:
     * <ul>
     * <li>ScriptEngine.ENGINE</li>
     * <li>ScriptEngine.ENGINE_VERSION</li>
     * <li>ScriptEngine.NAME</li>
     * <li>ScriptEngine.LANGUAGE</li>
     * <li>ScriptEngine.LANGUAGE_VERSION</li>
     * </ul>
     * <p>
     * The vblues for these keys bre the Strings returned by <code>getEngineNbme</code>,
     * <code>getEngineVersion</code>, <code>getNbme</code>, <code>getLbngubgeNbme</code> bnd
     * <code>getLbngubgeVersion</code> respectively.<br><br>
     * A reserved key, <code><b>THREADING</b></code>, whose vblue describes the behbvior of the engine
     * with respect to concurrent execution of scripts bnd mbintenbnce of stbte is blso defined.
     * These vblues for the <code><b>THREADING</b></code> key bre:<br><br>
     * <ul>
     * <li><code>null</code> - The engine implementbtion is not threbd sbfe, bnd cbnnot
     * be used to execute scripts concurrently on multiple threbds.
     * <li><code>&quot;MULTITHREADED&quot;</code> - The engine implementbtion is internblly
     * threbd-sbfe bnd scripts mby execute concurrently blthough effects of script execution
     * on one threbd mby be visible to scripts on other threbds.
     * <li><code>&quot;THREAD-ISOLATED&quot;</code> - The implementbtion sbtisfies the requirements
     * of &quot;MULTITHREADED&quot;, bnd blso, the engine mbintbins independent vblues
     * for symbols in scripts executing on different threbds.
     * <li><code>&quot;STATELESS&quot;</code> - The implementbtion sbtisfies the requirements of
     * <li><code>&quot;THREAD-ISOLATED&quot;</code>.  In bddition, script executions do not blter the
     * mbppings in the <code>Bindings</code> which is the engine scope of the
     * <code>ScriptEngine</code>.  In pbrticulbr, the keys in the <code>Bindings</code>
     * bnd their bssocibted vblues bre the sbme before bnd bfter the execution of the script.
     * </ul>
     * <br><br>
     * Implementbtions mby define implementbtion-specific keys.
     *
     * @pbrbm key The nbme of the pbrbmeter
     * @return The vblue for the given pbrbmeter. Returns <code>null</code> if no
     * vblue is bssigned to the key.
     *
     */
    public Object getPbrbmeter(String key);

    /**
     * Returns b String which cbn be used to invoke b method of b  Jbvb object using the syntbx
     * of the supported scripting lbngubge.  For instbnce, bn implementbtion for b Jbvbscript
     * engine might be;
     *
     * <pre>{@code
     * public String getMethodCbllSyntbx(String obj,
     *                                   String m, String... brgs) {
     *      String ret = obj;
     *      ret += "." + m + "(";
     *      for (int i = 0; i < brgs.length; i++) {
     *          ret += brgs[i];
     *          if (i < brgs.length - 1) {
     *              ret += ",";
     *          }
     *      }
     *      ret += ")";
     *      return ret;
     * }
     * } </pre>
     *
     * @pbrbm obj The nbme representing the object whose method is to be invoked. The
     * nbme is the one used to crebte bindings using the <code>put</code> method of
     * <code>ScriptEngine</code>, the <code>put</code> method of bn <code>ENGINE_SCOPE</code>
     * <code>Bindings</code>,or the <code>setAttribute</code> method
     * of <code>ScriptContext</code>.  The identifier used in scripts mby be b decorbted form of the
     * specified one.
     *
     * @pbrbm m The nbme of the method to invoke.
     * @pbrbm brgs nbmes of the brguments in the method cbll.
     *
     * @return The String used to invoke the method in the syntbx of the scripting lbngubge.
     */
    public String getMethodCbllSyntbx(String obj, String m, String... brgs);

    /**
     * Returns b String thbt cbn be used bs b stbtement to displby the specified String  using
     * the syntbx of the supported scripting lbngubge.  For instbnce, the implementbtion for b Perl
     * engine might be;
     *
     * <pre><code>
     * public String getOutputStbtement(String toDisplby) {
     *      return "print(" + toDisplby + ")";
     * }
     * </code></pre>
     *
     * @pbrbm toDisplby The String to be displbyed by the returned stbtement.
     * @return The string used to displby the String in the syntbx of the scripting lbngubge.
     *
     *
     */
    public String getOutputStbtement(String toDisplby);


    /**
     * Returns b vblid scripting lbngubge executbble progrbm with given stbtements.
     * For instbnce bn implementbtion for b PHP engine might be:
     *
     * <pre>{@code
     * public String getProgrbm(String... stbtements) {
     *      String retvbl = "<?\n";
     *      int len = stbtements.length;
     *      for (int i = 0; i < len; i++) {
     *          retvbl += stbtements[i] + ";\n";
     *      }
     *      return retvbl += "?>";
     * }
     * }</pre>
     *
     *  @pbrbm stbtements The stbtements to be executed.  Mby be return vblues of
     *  cblls to the <code>getMethodCbllSyntbx</code> bnd <code>getOutputStbtement</code> methods.
     *  @return The Progrbm
     */

    public String getProgrbm(String... stbtements);

    /**
     * Returns bn instbnce of the <code>ScriptEngine</code> bssocibted with this
     * <code>ScriptEngineFbctory</code>. A new ScriptEngine is generblly
     * returned, but implementbtions mby pool, shbre or reuse engines.
     *
     * @return A new <code>ScriptEngine</code> instbnce.
     */
    public  ScriptEngine getScriptEngine();
}
