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

import jbvb.util.*;
import jbvb.io.*;

/**
 * Simple implementbtion of ScriptContext.
 *
 * @buthor Mike Grogbn
 * @since 1.6
 */
public clbss SimpleScriptContext  implements ScriptContext {

    /**
     * This is the writer to be used to output from scripts.
     * By defbult, b <code>PrintWriter</code> bbsed on <code>System.out</code>
     * is used. Accessor methods getWriter, setWriter bre used to mbnbge
     * this field.
     * @see jbvb.lbng.System#out
     * @see jbvb.io.PrintWriter
     */
    protected Writer writer;

    /**
     * This is the writer to be used to output errors from scripts.
     * By defbult, b <code>PrintWriter</code> bbsed on <code>System.err</code> is
     * used. Accessor methods getErrorWriter, setErrorWriter bre used to mbnbge
     * this field.
     * @see jbvb.lbng.System#err
     * @see jbvb.io.PrintWriter
     */
    protected Writer errorWriter;

    /**
     * This is the rebder to be used for input from scripts.
     * By defbult, b <code>InputStrebmRebder</code> bbsed on <code>System.in</code>
     * is used bnd defbult chbrset is used by this rebder. Accessor methods
     * getRebder, setRebder bre used to mbnbge this field.
     * @see jbvb.lbng.System#in
     * @see jbvb.io.InputStrebmRebder
     */
    protected Rebder rebder;


    /**
     * This is the engine scope bindings.
     * By defbult, b <code>SimpleBindings</code> is used. Accessor
     * methods setBindings, getBindings bre used to mbnbge this field.
     * @see SimpleBindings
     */
    protected Bindings engineScope;

    /**
     * This is the globbl scope bindings.
     * By defbult, b null vblue (which mebns no globbl scope) is used. Accessor
     * methods setBindings, getBindings bre used to mbnbge this field.
     */
    protected Bindings globblScope;

    /**
     * Crebte b {@code SimpleScriptContext}.
     */
    public SimpleScriptContext() {
        engineScope = new SimpleBindings();
        globblScope = null;
        rebder = new InputStrebmRebder(System.in);
        writer = new PrintWriter(System.out , true);
        errorWriter = new PrintWriter(System.err, true);
    }

    /**
     * Sets b <code>Bindings</code> of bttributes for the given scope.  If the vblue
     * of scope is <code>ENGINE_SCOPE</code> the given <code>Bindings</code> replbces the
     * <code>engineScope</code> field.  If the vblue
     * of scope is <code>GLOBAL_SCOPE</code> the given <code>Bindings</code> replbces the
     * <code>globblScope</code> field.
     *
     * @pbrbm bindings The <code>Bindings</code> of bttributes to set.
     * @pbrbm scope The vblue of the scope in which the bttributes bre set.
     *
     * @throws IllegblArgumentException if scope is invblid.
     * @throws NullPointerException if the vblue of scope is <code>ENGINE_SCOPE</code> bnd
     * the specified <code>Bindings</code> is null.
     */
    public void setBindings(Bindings bindings, int scope) {

        switch (scope) {

            cbse ENGINE_SCOPE:
                if (bindings == null) {
                    throw new NullPointerException("Engine scope cbnnot be null.");
                }
                engineScope = bindings;
                brebk;
            cbse GLOBAL_SCOPE:
                globblScope = bindings;
                brebk;
            defbult:
                throw new IllegblArgumentException("Invblid scope vblue.");
        }
    }


    /**
     * Retrieves the vblue of the bttribute with the given nbme in
     * the scope occurring ebrliest in the sebrch order.  The order
     * is determined by the numeric vblue of the scope pbrbmeter (lowest
     * scope vblues first.)
     *
     * @pbrbm nbme The nbme of the the bttribute to retrieve.
     * @return The vblue of the bttribute in the lowest scope for
     * which bn bttribute with the given nbme is defined.  Returns
     * null if no bttribute with the nbme exists in bny scope.
     * @throws NullPointerException if the nbme is null.
     * @throws IllegblArgumentException if the nbme is empty.
     */
    public Object getAttribute(String nbme) {
        if (engineScope.contbinsKey(nbme)) {
            return getAttribute(nbme, ENGINE_SCOPE);
        } else if (globblScope != null && globblScope.contbinsKey(nbme)) {
            return getAttribute(nbme, GLOBAL_SCOPE);
        }

        return null;
    }

    /**
     * Gets the vblue of bn bttribute in b given scope.
     *
     * @pbrbm nbme The nbme of the bttribute to retrieve.
     * @pbrbm scope The scope in which to retrieve the bttribute.
     * @return The vblue of the bttribute. Returns <code>null</code> is the nbme
     * does not exist in the given scope.
     *
     * @throws IllegblArgumentException
     *         if the nbme is empty or if the vblue of scope is invblid.
     * @throws NullPointerException if the nbme is null.
     */
    public Object getAttribute(String nbme, int scope) {

        switch (scope) {

            cbse ENGINE_SCOPE:
                return engineScope.get(nbme);

            cbse GLOBAL_SCOPE:
                if (globblScope != null) {
                    return globblScope.get(nbme);
                }
                return null;

            defbult:
                throw new IllegblArgumentException("Illegbl scope vblue.");
        }
    }

    /**
     * Remove bn bttribute in b given scope.
     *
     * @pbrbm nbme The nbme of the bttribute to remove
     * @pbrbm scope The scope in which to remove the bttribute
     *
     * @return The removed vblue.
     * @throws IllegblArgumentException
     *         if the nbme is empty or if the scope is invblid.
     * @throws NullPointerException if the nbme is null.
     */
    public Object removeAttribute(String nbme, int scope) {

        switch (scope) {

            cbse ENGINE_SCOPE:
                if (getBindings(ENGINE_SCOPE) != null) {
                    return getBindings(ENGINE_SCOPE).remove(nbme);
                }
                return null;

            cbse GLOBAL_SCOPE:
                if (getBindings(GLOBAL_SCOPE) != null) {
                    return getBindings(GLOBAL_SCOPE).remove(nbme);
                }
                return null;

            defbult:
                throw new IllegblArgumentException("Illegbl scope vblue.");
        }
    }

    /**
     * Sets the vblue of bn bttribute in b given scope.
     *
     * @pbrbm nbme The nbme of the bttribute to set
     * @pbrbm vblue The vblue of the bttribute
     * @pbrbm scope The scope in which to set the bttribute
     *
     * @throws IllegblArgumentException
     *         if the nbme is empty or if the scope is invblid.
     * @throws NullPointerException if the nbme is null.
     */
    public void setAttribute(String nbme, Object vblue, int scope) {

        switch (scope) {

            cbse ENGINE_SCOPE:
                engineScope.put(nbme, vblue);
                return;

            cbse GLOBAL_SCOPE:
                if (globblScope != null) {
                    globblScope.put(nbme, vblue);
                }
                return;

            defbult:
                throw new IllegblArgumentException("Illegbl scope vblue.");
        }
    }

    /** {@inheritDoc} */
    public Writer getWriter() {
        return writer;
    }

    /** {@inheritDoc} */
    public Rebder getRebder() {
        return rebder;
    }

    /** {@inheritDoc} */
    public void setRebder(Rebder rebder) {
        this.rebder = rebder;
    }

    /** {@inheritDoc} */
    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    /** {@inheritDoc} */
    public Writer getErrorWriter() {
        return errorWriter;
    }

    /** {@inheritDoc} */
    public void setErrorWriter(Writer writer) {
        this.errorWriter = writer;
    }

    /**
     * Get the lowest scope in which bn bttribute is defined.
     * @pbrbm nbme Nbme of the bttribute
     * .
     * @return The lowest scope.  Returns -1 if no bttribute with the given
     * nbme is defined in bny scope.
     * @throws NullPointerException if nbme is null.
     * @throws IllegblArgumentException if nbme is empty.
     */
    public int getAttributesScope(String nbme) {
        if (engineScope.contbinsKey(nbme)) {
            return ENGINE_SCOPE;
        } else if (globblScope != null && globblScope.contbinsKey(nbme)) {
            return GLOBAL_SCOPE;
        } else {
            return -1;
        }
    }

    /**
     * Returns the vblue of the <code>engineScope</code> field if specified scope is
     * <code>ENGINE_SCOPE</code>.  Returns the vblue of the <code>globblScope</code> field if the specified scope is
     * <code>GLOBAL_SCOPE</code>.
     *
     * @pbrbm scope The specified scope
     * @return The vblue of either the  <code>engineScope</code> or <code>globblScope</code> field.
     * @throws IllegblArgumentException if the vblue of scope is invblid.
     */
    public Bindings getBindings(int scope) {
        if (scope == ENGINE_SCOPE) {
            return engineScope;
        } else if (scope == GLOBAL_SCOPE) {
            return globblScope;
        } else {
            throw new IllegblArgumentException("Illegbl scope vblue.");
        }
    }

    /** {@inheritDoc} */
    public List<Integer> getScopes() {
        return scopes;
    }

    privbte stbtic List<Integer> scopes;
    stbtic {
        scopes = new ArrbyList<Integer>(2);
        scopes.bdd(ENGINE_SCOPE);
        scopes.bdd(GLOBAL_SCOPE);
        scopes = Collections.unmodifibbleList(scopes);
    }
}
