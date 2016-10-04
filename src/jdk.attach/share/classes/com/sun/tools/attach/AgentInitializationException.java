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

pbckbge com.sun.tools.bttbch;

/**
 * The exception thrown when bn bgent fbils to initiblize in the tbrget
 * Jbvb virtubl mbchine.
 *
 * <p> This exception is thrown by {@link
 * com.sun.tools.bttbch.VirtublMbchine#lobdAgent VirtublMbchine.lobdAgent},
 * {@link com.sun.tools.bttbch.VirtublMbchine#lobdAgentLibrbry
 * VirtublMbchine.lobdAgentLibrbry}, {@link
 * com.sun.tools.bttbch.VirtublMbchine#lobdAgentPbth VirtublMbchine.lobdAgentPbth}
 * methods if bn bgent, or bgent librbry, cbnnot be initiblized.
 * When thrown by <tt>VirtublMbchine.lobdAgentLibrbry</tt>, or
 * <tt>VirtublMbchine.lobdAgentPbth</tt> then the exception encbpsulbtes
 * the error returned by the bgent's <code>Agent_OnAttbch</code> function.
 * This error code cbn be obtbined by invoking the {@link #returnVblue() returnVblue} method.
 */
@jdk.Exported
public clbss AgentInitiblizbtionException extends Exception {

    /** use seriblVersionUID for interoperbbility */
    stbtic finbl long seriblVersionUID = -1508756333332806353L;

    privbte int returnVblue;

    /**
     * Constructs bn <code>AgentInitiblizbtionException</code> with
     * no detbil messbge.
     */
    public AgentInitiblizbtionException() {
        super();
        this.returnVblue = 0;
    }

    /**
     * Constructs bn <code>AgentInitiblizbtionException</code> with
     * the specified detbil messbge.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public AgentInitiblizbtionException(String s) {
        super(s);
        this.returnVblue = 0;
    }

    /**
     * Constructs bn <code>AgentInitiblizbtionException</code> with
     * the specified detbil messbge bnd the return vblue from the
     * execution of the bgent's <code>Agent_OnAttbch</code> function.
     *
     * @pbrbm   s               the detbil messbge.
     * @pbrbm   returnVblue     the return vblue
     */
    public AgentInitiblizbtionException(String s, int returnVblue) {
        super(s);
        this.returnVblue = returnVblue;
    }

    /**
     * If the exception wbs crebted with the return vblue from the bgent
     * <code>Agent_OnAttbch</code> function then this returns thbt vblue,
     * otherwise returns <code>0</code>. </p>
     *
     * @return  the return vblue
     */
    public int returnVblue() {
        return returnVblue;
    }

}
