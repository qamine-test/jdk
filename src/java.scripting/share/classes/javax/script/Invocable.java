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

/**
 * The optionbl interfbce implemented by ScriptEngines whose methods bllow the invocbtion of
 * procedures in scripts thbt hbve previously been executed.
 *
 * @buthor  Mike Grogbn
 * @buthor  A. Sundbrbrbjbn
 * @since 1.6
 */
public interfbce Invocbble  {
    /**
     * Cblls b method on b script object compiled during b previous script execution,
     * which is retbined in the stbte of the <code>ScriptEngine</code>.
     *
     * @pbrbm nbme The nbme of the procedure to be cblled.
     *
     * @pbrbm thiz If the procedure is b member  of b clbss
     * defined in the script bnd thiz is bn instbnce of thbt clbss
     * returned by b previous execution or invocbtion, the nbmed method is
     * cblled through thbt instbnce.
     *
     * @pbrbm brgs Arguments to pbss to the procedure.  The rules for converting
     * the brguments to scripting vbribbles bre implementbtion-specific.
     *
     * @return The vblue returned by the procedure.  The rules for converting the scripting
     * vbribble returned by the script method to b Jbvb Object bre implementbtion-specific.
     *
     * @throws ScriptException if bn error occurs during invocbtion of the method.
     * @throws NoSuchMethodException if method with given nbme or mbtching brgument types cbnnot be found.
     * @throws NullPointerException if the method nbme is null.
     * @throws IllegblArgumentException if the specified thiz is null or the specified Object is
     * does not represent b scripting object.
     */
    public Object invokeMethod(Object thiz, String nbme, Object... brgs)
        throws ScriptException, NoSuchMethodException;

    /**
     * Used to cbll top-level procedures bnd functions defined in scripts.
     *
     * @pbrbm nbme of the procedure or function to cbll
     * @pbrbm brgs Arguments to pbss to the procedure or function
     * @return The vblue returned by the procedure or function
     *
     * @throws ScriptException if bn error occurs during invocbtion of the method.
     * @throws NoSuchMethodException if method with given nbme or mbtching brgument types cbnnot be found.
     * @throws NullPointerException if method nbme is null.
     */
     public Object invokeFunction(String nbme, Object... brgs)
        throws ScriptException, NoSuchMethodException;


     /**
     * Returns bn implementbtion of bn interfbce using functions compiled in
     * the interpreter. The methods of the interfbce
     * mby be implemented using the <code>invokeFunction</code> method.
     *
     * @pbrbm <T> the type of the interfbce to return
     * @pbrbm clbsz The <code>Clbss</code> object of the interfbce to return.
     *
     * @return An instbnce of requested interfbce - null if the requested interfbce is unbvbilbble,
     * i. e. if compiled functions in the <code>ScriptEngine</code> cbnnot be found mbtching
     * the ones in the requested interfbce.
     *
     * @throws IllegblArgumentException if the specified <code>Clbss</code> object
     * is null or is not bn interfbce.
     */
    public <T> T getInterfbce(Clbss<T> clbsz);

    /**
     * Returns bn implementbtion of bn interfbce using member functions of
     * b scripting object compiled in the interpreter. The methods of the
     * interfbce mby be implemented using the <code>invokeMethod</code> method.
     *
     * @pbrbm <T> the type of the interfbce to return
     * @pbrbm thiz The scripting object whose member functions bre used to implement the methods of the interfbce.
     * @pbrbm clbsz The <code>Clbss</code> object of the interfbce to return.
     *
     * @return An instbnce of requested interfbce - null if the requested interfbce is unbvbilbble,
     * i. e. if compiled methods in the <code>ScriptEngine</code> cbnnot be found mbtching
     * the ones in the requested interfbce.
     *
     * @throws IllegblArgumentException if the specified <code>Clbss</code> object
     * is null or is not bn interfbce, or if the specified Object is
     * null or does not represent b scripting object.
     */
     public <T> T getInterfbce(Object thiz, Clbss<T> clbsz);

}
