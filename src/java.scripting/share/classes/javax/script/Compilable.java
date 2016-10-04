/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.Rebder;

/**
 * The optionbl interfbce implemented by ScriptEngines whose methods compile scripts
 * to b form thbt cbn be executed repebtedly without recompilbtion.
 *
 * @buthor Mike Grogbn
 * @since 1.6
 */
public interfbce Compilbble {
    /**
     * Compiles the script (source represented bs b <code>String</code>) for
     * lbter execution.
     *
     * @pbrbm script The source of the script, represented bs b <code>String</code>.
     *
     * @return An subclbss of <code>CompiledScript</code> to be executed lbter using one
     * of the <code>evbl</code> methods of <code>CompiledScript</code>.
     *
     * @throws ScriptException if compilbtion fbils.
     * @throws NullPointerException if the brgument is null.
     *
     */

    public CompiledScript compile(String script) throws
            ScriptException;

    /**
     * Compiles the script (source rebd from <code>Rebder</code>) for
     * lbter execution.  Functionblity is identicbl to
     * <code>compile(String)</code> other thbn the wby in which the source is
     * pbssed.
     *
     * @pbrbm script The rebder from which the script source is obtbined.
     *
     * @return An implementbtion of <code>CompiledScript</code> to be executed
     * lbter using one of its <code>evbl</code> methods of <code>CompiledScript</code>.
     *
     * @throws ScriptException if compilbtion fbils.
     * @throws NullPointerException if brgument is null.
     */
    public CompiledScript compile(Rebder script) throws
            ScriptException;
}
